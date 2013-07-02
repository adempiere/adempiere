/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.adempiere.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MClient;
import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MInvoiceSchedule;
import org.compiere.model.MLocation;
import org.compiere.model.MOrder;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Language;

/**
 *	Generate Invoices
 *	
 *  @author Jorg Janke
 *  @author Trifon Trifonov
 */
public class InvoiceGenerateFromShipment extends SvrProcess {

	/**	Manual Selection		*/
	private boolean 	p_Selection = false;
	/**	Date Invoiced			*/
	private Timestamp	p_DateInvoiced = null;
	/**	Org						*/
	private int			p_AD_Org_ID = 0;
	/** BPartner				*/
	private int			p_C_BPartner_ID = 0;
	/** Order					*/
	private int			p_M_InOut_ID = 0;
	/** Consolidate				*/
	private boolean		p_ConsolidateDocument = true;
	/** Invoice Document Action	*/
	private String		p_docAction = DocAction.ACTION_Complete;
	
	/**	The current Shipment	*/
	private MInOut	 	m_ship = null;
	/** Number of Invoices		*/
	private int			m_created = 0;
	/**	Line Number				*/
	private int			m_line = 0;
	/**	Business Partner		*/
	private MBPartner	m_bp = null;
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("Selection"))
				p_Selection = "Y".equals(para[i].getParameter());
			else if (name.equals("DateInvoiced"))
				p_DateInvoiced = (Timestamp)para[i].getParameter();
			else if (name.equals("AD_Org_ID"))
				p_AD_Org_ID = para[i].getParameterAsInt();
			else if (name.equals("C_BPartner_ID"))
				p_C_BPartner_ID = para[i].getParameterAsInt();
			else if (name.equals("M_InOut_ID"))
				p_M_InOut_ID = para[i].getParameterAsInt();
			else if (name.equals("ConsolidateDocument"))
				p_ConsolidateDocument = "Y".equals(para[i].getParameter());
			else if (name.equals("DocAction"))
				p_docAction = (String)para[i].getParameter();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

		//	Login Date
		if (p_DateInvoiced == null)
			p_DateInvoiced = Env.getContextAsDate(getCtx(), "#Date");
		if (p_DateInvoiced == null)
			p_DateInvoiced = new Timestamp(System.currentTimeMillis());

		//	DocAction check
		if (!DocAction.ACTION_Complete.equals(p_docAction))
			p_docAction = DocAction.ACTION_Prepare;
	}

	/**
	 * 	Generate Invoices from Shipments
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt () throws Exception {
		log.info("Selection=" + p_Selection + ", DateInvoiced=" + p_DateInvoiced
			+ ", AD_Org_ID=" + p_AD_Org_ID + ", C_BPartner_ID=" + p_C_BPartner_ID
			+ ", M_InOut_ID=" + p_M_InOut_ID + ", DocAction=" + p_docAction 
			+ ", Consolidate=" + p_ConsolidateDocument);
		//
		String sql = null;
		if (p_Selection) {	//	VInvoiceGenFromShipment
			sql = "SELECT M_InOut.* FROM M_InOut, T_Selection "
				+ "WHERE M_InOut.DocStatus='CO' AND M_InOut.IsSOTrx='Y' "
				+ " AND M_InOut.M_InOut_ID = T_Selection.T_Selection_ID "
				+ " AND T_Selection.AD_PInstance_ID=? "
				+ "ORDER BY M_InOut.M_InOut_ID";
				//+ "ORDER BY M_InOut.M_Warehouse_ID, M_InOut.PriorityRule, M_InOut.C_BPartner_ID, M_InOut.C_BPartner_Location_ID, M_InOut.M_InOut_ID";
		} else {
			sql = "SELECT * FROM M_InOut o "
				+ "WHERE DocStatus IN('CO','CL') AND IsSOTrx='Y'";
			if (p_AD_Org_ID != 0)
				sql += " AND AD_Org_ID=?";
			if (p_C_BPartner_ID != 0)
				sql += " AND C_BPartner_ID=?";
			if (p_M_InOut_ID != 0)
				sql += " AND M_InOut_ID=?";
			//
			sql += " AND EXISTS (SELECT * FROM M_InOutLine ol "
				+ "WHERE o.M_InOut_ID=ol.M_InOut_ID AND ol.IsInvoiced='N') "
				+ "ORDER BY M_InOut_ID";
				//+ "ORDER BY M_Warehouse_ID, PriorityRule, C_BPartner_ID, C_BPartner_Location_ID, M_InOut_ID";
		}
		
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement (sql, get_TrxName());
			int index = 1;
			if (p_Selection) {
				pstmt.setInt(index, getAD_PInstance_ID());
			} else {
				if (p_AD_Org_ID != 0)
					pstmt.setInt(index++, p_AD_Org_ID);
				if (p_C_BPartner_ID != 0)
					pstmt.setInt(index++, p_C_BPartner_ID);
				if (p_M_InOut_ID != 0)
					pstmt.setInt(index++, p_M_InOut_ID);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql, e);
		}
		return generate( pstmt );
	}
	
	//@Trifon
	private String generate (PreparedStatement pstmt) {
		ResultSet rs = null;
		MInvoice invoice = null;
		try {
			rs = pstmt.executeQuery ();
			while (rs.next ()) {
				MInOut inOut = new MInOut(getCtx(), rs, get_TrxName());
				if (!inOut.isComplete()		//	ignore incomplete or reversals 
					|| inOut.getDocStatus().equals(MInOut.DOCSTATUS_Reversed)
				) {
					continue;
				}
				MOrder order = new MOrder(getCtx(), inOut.getC_Order_ID(), get_TrxName());
				
				//	New Invoice Location
				if (!p_ConsolidateDocument 
					|| (invoice != null && invoice.getC_BPartner_Location_ID() != order.getBill_Location_ID()) 
				) {
					invoice = completeInvoice( invoice );
				}
//				boolean completeOrder = MOrder.INVOICERULE_AfterOrderDelivered.equals(order.getInvoiceRule());
				
				//	Schedule After Delivery
				boolean doInvoice = false;
				if (MOrder.INVOICERULE_CustomerScheduleAfterDelivery.equals(order.getInvoiceRule())) {
					m_bp = new MBPartner (getCtx(), order.getBill_BPartner_ID(), null);
					if (m_bp.getC_InvoiceSchedule_ID() == 0) {
						log.warning("BPartner has no Schedule - set to After Delivery");
						order.setInvoiceRule(MOrder.INVOICERULE_AfterDelivery);
						order.save();
					} else {
						MInvoiceSchedule is = MInvoiceSchedule.get(getCtx(), m_bp.getC_InvoiceSchedule_ID(), get_TrxName());
						if (is.canInvoice(order.getDateOrdered(), order.getGrandTotal()))
							doInvoice = true;
						else
							continue;
					}
				} // CustomerScheduleAfterDelivery
				
				//	After Delivery - Invoice per Delivery
				if (doInvoice || MOrder.INVOICERULE_AfterDelivery.equals( order.getInvoiceRule() ) ) {
					MInOutLine[] shipLines = inOut.getLines(false);
					for (int j = 0; j < shipLines.length; j++) {
						MInOutLine inOutLine = shipLines[j];
						if (!inOutLine.isInvoiced()) {
							invoice = createInvoiceLineFromShipmentLine(invoice, order, inOut, inOutLine);
						}
					}
					m_line += 1000;
				}
				//	After Order Delivered, Immediate
				else {
					throw new AdempiereException("Not supported Invoice Rules[Immediate, AfterOrderDelivered]");
				}
			} // for all Shipments
		} catch (Exception e) {
			log.log(Level.SEVERE, "", e);
		} finally {
			DB.close(rs, pstmt);
			pstmt = null;
		}

		completeInvoice( invoice );
		return "@Created@ = " + m_created;
	}

	/**
	 * 	Create Invoice Line from Shipment Line
	 *	@param order order
	 *	@param inOut shipment header
	 *	@param inOutLine shipment line
	 */
	private MInvoice createInvoiceLineFromShipmentLine(MInvoice invoice, MOrder order, MInOut inOut, MInOutLine inOutLine) {
		if (invoice == null) {
			invoice = new MInvoice(inOut, p_DateInvoiced);
			if (!invoice.save())
				throw new IllegalStateException("Could not create Invoice (s)");
		}
		//	Create Shipment Comment Line
		if (m_ship == null || m_ship.getM_InOut_ID() != inOut.getM_InOut_ID()) {
			MDocType dt = MDocType.get(getCtx(), inOut.getC_DocType_ID());
			if (m_bp == null || m_bp.getC_BPartner_ID() != inOut.getC_BPartner_ID())
				m_bp = new MBPartner (getCtx(), inOut.getC_BPartner_ID(), get_TrxName());
			
			//	Reference: Delivery: 12345 - 12.12.12
			MClient client = MClient.get(getCtx(), order.getAD_Client_ID ());
			String AD_Language = client.getAD_Language();
			if (client.isMultiLingualDocument() && m_bp.getAD_Language() != null)
				AD_Language = m_bp.getAD_Language();
			if (AD_Language == null)
				AD_Language = Language.getBaseAD_Language();
			java.text.SimpleDateFormat format = DisplayType.getDateFormat (DisplayType.Date, Language.getLanguage(AD_Language));
			String referenceDescr = dt.getPrintName(m_bp.getAD_Language()) + ": " + inOut.getDocumentNo() + " - " + format.format(inOut.getMovementDate());
			m_ship = inOut;
			//
			MInvoiceLine descInvLine = new MInvoiceLine (invoice);
			descInvLine.setIsDescription(true);
			descInvLine.setDescription(referenceDescr);
			descInvLine.setLine(m_line + inOutLine.getLine() - 2);
			if (!descInvLine.save())
				throw new IllegalStateException("Could not create Invoice Comment Line (sh)");
			//	Optional Ship Address if not Bill Address
			if (order.getBill_Location_ID() != inOut.getC_BPartner_Location_ID()) {
				MLocation addr = MLocation.getBPLocation(getCtx(), inOut.getC_BPartner_Location_ID(), null);
				descInvLine = new MInvoiceLine (invoice);
				descInvLine.setIsDescription(true);
				descInvLine.setDescription(addr.toString());
				descInvLine.setLine(m_line + inOutLine.getLine() - 1);
				if (!descInvLine.save())
					throw new IllegalStateException("Could not create Invoice Comment Line 2 (sh)");
			}
		}
		//	
		MInvoiceLine invLine = new MInvoiceLine( invoice );
		invLine.setShipLine( inOutLine );
		if (inOutLine.sameOrderLineUOM())
			invLine.setQtyEntered( inOutLine.getQtyEntered() );
		else
			invLine.setQtyEntered( inOutLine.getMovementQty() );
		invLine.setQtyInvoiced( inOutLine.getMovementQty() );
		invLine.setLine(m_line + inOutLine.getLine());
		//@Trifon - special handling when ShipLine.ToBeInvoiced='N'
		String toBeInvoiced = inOutLine.get_ValueAsString( "ToBeInvoiced" );
		if ("N".equals( toBeInvoiced ) || "false".equals( toBeInvoiced )) {
			invLine.setPriceEntered( Env.ZERO );
			invLine.setPriceActual( Env.ZERO );
			invLine.setPriceList( Env.ZERO);
			invLine.setPriceLimit( Env.ZERO );
			//setC_Tax_ID(oLine.getC_Tax_ID());
			invLine.setLineNetAmt( Env.ZERO );
			invLine.setIsDescription( true );
		}
		if (!invLine.save())
			throw new IllegalStateException("Could not create Invoice Line (s)");
		//	Link
		inOutLine.setIsInvoiced(true);
		if (!inOutLine.save())
			throw new IllegalStateException("Could not update Shipment Line");
		
		log.fine(invLine.toString());
		
		return invoice;
	}
	
	/**
	 * 	Complete Invoice
	 */
	private MInvoice completeInvoice(MInvoice invoice) {
		if (invoice != null) {
			if (!invoice.processIt(p_docAction)) {
				log.warning("completeInvoice - failed: " + invoice);
				addLog("completeInvoice - failed: " + invoice); // Elaine 2008/11/25
			}
			invoice.save();
			//
			addLog(invoice.getC_Invoice_ID(), invoice.getDateInvoiced(), null, invoice.getDocumentNo());
			m_created++;
		}
		invoice = null;
		m_ship = null;
		m_line = 0;
		
		return invoice;
	}

}