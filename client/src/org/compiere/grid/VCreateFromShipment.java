/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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
package org.compiere.grid;

import java.awt.event.*;
import java.beans.*;
import java.math.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.table.*;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.grid.ed.*;
import org.compiere.model.*;
import org.compiere.util.*;

/**
 *  Create Shipments Transactions - from PO Orders or AP Invoices
 *
 *  @author Jorg Janke
 *  @version  $Id: VCreateFromShipment.java,v 1.4 2006/07/30 00:51:28 jjanke Exp $
 */
public class VCreateFromShipment extends VCreateFrom implements VetoableChangeListener
{
	/**
	 *  Protected Constructor
	 *  @param mTab MTab
	 */
	VCreateFromShipment(GridTab mTab)
	{
		super (mTab);
	//	log.info( "VCreateFromShipment");
	}   //  VCreateFromShipment

	/**  Loaded Invoice             */
	private MInvoice		m_invoice = null;

	/**
	 *  Dynamic Init
	 *  @throws Exception if Lookups cannot be initialized
	 *  @return true if initialized
	 */
	protected boolean dynInit() throws Exception
	{
		log.config("");
		setTitle(Msg.getElement(Env.getCtx(), "M_InOut_ID", false) + " .. " + Msg.translate(Env.getCtx(), "CreateFrom"));

		parameterBankPanel.setVisible(false);
		shipmentLabel.setVisible(false);
		shipmentField.setVisible(false);

		//  load Locator
		int AD_Column_ID = 3537;            //  M_InOut.M_Locator_ID
		MLocatorLookup locator = new MLocatorLookup(Env.getCtx(), p_WindowNo);
		locatorField = new VLocator ("M_Locator_ID", true, false, true,	locator, p_WindowNo);

		initBPartner(false);
		bPartnerField.addVetoableChangeListener(this);
		return true;
	}   //  dynInit

	/**
	 *  Init Details - load invoices not shipped
	 *  @param C_BPartner_ID BPartner
	 */
	protected void initBPDetails(int C_BPartner_ID)
	{
		log.config("C_BPartner_ID=" + C_BPartner_ID);

		//  load AP Invoice closed or complete
		invoiceField.removeActionListener(this);
		invoiceField.removeAllItems();
		//	None
		KeyNamePair pp = new KeyNamePair(0,"");
		invoiceField.addItem(pp);
		StringBuffer display = new StringBuffer("i.DocumentNo||' - '||")
			.append(DB.TO_CHAR("DateInvoiced", DisplayType.Date, Env.getAD_Language(Env.getCtx())))
			.append("|| ' - ' ||")
			.append(DB.TO_CHAR("GrandTotal", DisplayType.Amount, Env.getAD_Language(Env.getCtx())));
		//
		StringBuffer sql = new StringBuffer("SELECT i.C_Invoice_ID,").append(display)
		   .append(" FROM C_Invoice i "
		   + "WHERE i.C_BPartner_ID=? AND i.IsSOTrx='N' AND i.DocStatus IN ('CL','CO')"
		   + " AND i.C_Invoice_ID IN "
			   + "(SELECT il.C_Invoice_ID FROM C_InvoiceLine il"
			   + " LEFT OUTER JOIN M_MatchInv mi ON (il.C_InvoiceLine_ID=mi.C_InvoiceLine_ID) "
			   + "GROUP BY il.C_Invoice_ID,mi.C_InvoiceLine_ID,il.QtyInvoiced "
			   + "HAVING (il.QtyInvoiced<>SUM(mi.Qty) AND mi.C_InvoiceLine_ID IS NOT NULL)"
			   + " OR mi.C_InvoiceLine_ID IS NULL) "
		   + "ORDER BY i.DateInvoiced");

		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, C_BPartner_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				pp = new KeyNamePair(rs.getInt(1), rs.getString(2));
				invoiceField.addItem(pp);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		}
		invoiceField.setSelectedIndex(0);
		invoiceField.addActionListener(this);
	}   //  initBPDetails


	/**
	 *  Action Listener
	 *  @param e event
	 */
	public void actionPerformed(ActionEvent e)
	{
		super.actionPerformed(e);
		log.config("Action=" + e.getActionCommand());

		//  Order
		if (e.getSource().equals(orderField))
		{
			KeyNamePair pp = (KeyNamePair)orderField.getSelectedItem();
			if (pp == null || pp.getKey() == 0)
				;
			else
			{
				int C_Order_ID = pp.getKey();
				//  set Invoice and Shipment to Null
				invoiceField.setSelectedIndex(-1);
				shipmentField.setSelectedIndex(-1);
				loadOrder(C_Order_ID, false);
				m_invoice = null;
			}
		}
		//  Invoice
		else if (e.getSource().equals(invoiceField))
		{
			KeyNamePair pp = (KeyNamePair)invoiceField.getSelectedItem();
			if (pp == null || pp.getKey() == 0)
				;
			else
			{
				int C_Invoice_ID = pp.getKey();
				//  set Order and Shipment to Null
				orderField.setSelectedIndex(-1);
				shipmentField.setSelectedIndex(-1);
				loadInvoice(C_Invoice_ID);
			}
		}
	}   //  actionPerformed


	/**
	 *  Change Listener
	 *  @param e event
	 */
	public void vetoableChange (PropertyChangeEvent e)
	{
		log.config(e.getPropertyName() + "=" + e.getNewValue());

		//  BPartner - load Order/Invoice/Shipment
		if (e.getPropertyName().equals("C_BPartner_ID"))
		{
			int C_BPartner_ID = ((Integer)e.getNewValue()).intValue();
			initBPartnerOIS (C_BPartner_ID, false);
		}
		tableChanged(null);
	}   //  vetoableChange


	/**
	 *  Load Data - Invoice
	 *  @param C_Invoice_ID Invoice
	 */
	private void loadInvoice(int C_Invoice_ID) {
		log.config("C_Invoice_ID=" + C_Invoice_ID);
		m_invoice = new MInvoice(Env.getCtx(), C_Invoice_ID, null); // save
		p_order = null;

		Vector<Vector> data = new Vector<Vector>();
		StringBuffer sql = new StringBuffer("SELECT " // Entered UOM
				+ "l.QtyInvoiced-SUM(NVL(mi.Qty,0)),l.QtyEntered/l.QtyInvoiced,"
				+ " l.C_UOM_ID,COALESCE(uom.UOMSymbol,uom.Name)," // 3..4
				+ " l.M_Product_ID,p.Name, po.VendorProductNo, l.C_InvoiceLine_ID,l.Line," // 5..9
				+ " l.C_OrderLine_ID "
				+ " FROM C_InvoiceLine l "); // 10
		if (Env.isBaseLanguage(Env.getCtx(), "C_UOM"))
			sql.append(" LEFT OUTER JOIN C_UOM uom ON (l.C_UOM_ID=uom.C_UOM_ID)");
		else
			sql.append(" LEFT OUTER JOIN C_UOM_Trl uom ON (l.C_UOM_ID=uom.C_UOM_ID AND uom.AD_Language='")
				.append(Env.getAD_Language(Env.getCtx())).append("')");

		sql.append(" LEFT OUTER JOIN M_Product p ON (l.M_Product_ID=p.M_Product_ID)")
			.append(" INNER JOIN C_Invoice inv ON (l.C_Invoice_ID=inv.C_Invoice_ID)")
			.append(" LEFT OUTER JOIN M_Product_PO po ON (l.M_Product_ID = po.M_Product_ID AND inv.C_BPartner_ID = po.C_BPartner_ID)")
			.append(" LEFT OUTER JOIN M_MatchInv mi ON (l.C_InvoiceLine_ID=mi.C_InvoiceLine_ID)")
			
			.append(" WHERE l.C_Invoice_ID=? ")	
			.append("GROUP BY l.QtyInvoiced,l.QtyEntered/l.QtyInvoiced,"
				+ "l.C_UOM_ID,COALESCE(uom.UOMSymbol,uom.Name),"
				+ "l.M_Product_ID,p.Name, po.VendorProductNo, l.C_InvoiceLine_ID,l.Line,l.C_OrderLine_ID ")
			.append("ORDER BY l.Line");
		
		try {
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, C_Invoice_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Vector<Object> line = new Vector<Object>(7);
				line.add(new Boolean(false)); // 0-Selection
				BigDecimal qtyInvoiced = rs.getBigDecimal(1);
				BigDecimal multiplier = rs.getBigDecimal(2);
				BigDecimal qtyEntered = qtyInvoiced.multiply(multiplier);
				line.add(new Double(qtyEntered.doubleValue())); // 1-Qty
				KeyNamePair pp = new KeyNamePair(rs.getInt(3), rs.getString(4).trim());
				line.add(pp); // 2-UOM
				pp = new KeyNamePair(rs.getInt(5), rs.getString(6));
				line.add(pp); // 3-Product
				line.add(rs.getString(7));				// 4-VendorProductNo
				int C_OrderLine_ID = rs.getInt(10);
				if (rs.wasNull())
					line.add(null); // 5-Order
				else
					line.add(new KeyNamePair(C_OrderLine_ID, "."));
				line.add(null); // 6-Ship
				pp = new KeyNamePair(rs.getInt(8), rs.getString(9));
				line.add(pp); // 7-Invoice
				data.add(line);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			log.log(Level.SEVERE, sql.toString(), e);
		}
		loadTableOIS(data);
	} // loadInvoice

	/**
	 * List number of rows selected
	 */
	protected void info() {
		TableModel model = dataTable.getModel();
		int rows = model.getRowCount();
		int count = 0;
		for (int i = 0; i < rows; i++) {
			if (((Boolean) model.getValueAt(i, 0)).booleanValue())
				count++;
		}
		statusBar.setStatusLine(String.valueOf(count));
	} // info

	/**
	 * Save - create Shipments
	 * 
	 * @return true if saved
	 */
	protected boolean save() {
		log.config("");
		TableModel model = dataTable.getModel();
		int rows = model.getRowCount();
		if (rows == 0)
			return false;
		//
		Integer loc = (Integer) locatorField.getValue();
		if (loc == null || loc.intValue() == 0) {
			locatorField.setBackground(AdempierePLAF.getFieldBackground_Error());
			return false;
		}
		int M_Locator_ID = loc.intValue();
		// Get Shipment
		int M_InOut_ID = ((Integer) p_mTab.getValue("M_InOut_ID")).intValue();
		MInOut inout = new MInOut(Env.getCtx(), M_InOut_ID, null);
		log.config(inout + ", C_Locator_ID=" + M_Locator_ID);

		// Lines
		for (int i = 0; i < rows; i++) {
			if (((Boolean) model.getValueAt(i, 0)).booleanValue()) {
				// variable values
				Double d = (Double) model.getValueAt(i, 1); // 1-Qty
				BigDecimal QtyEntered = new BigDecimal(d.doubleValue());
				KeyNamePair pp = (KeyNamePair) model.getValueAt(i, 2); // 2-Product
				int C_UOM_ID = pp.getKey();
				pp = (KeyNamePair) model.getValueAt(i, 3); // 3-Product
				int M_Product_ID = pp.getKey();
				int C_OrderLine_ID = 0;
				pp = (KeyNamePair) model.getValueAt(i, 5); // 5-OrderLine
				if (pp != null)
					C_OrderLine_ID = pp.getKey();
				int C_InvoiceLine_ID = 0;
				MInvoiceLine il = null;
				pp = (KeyNamePair) model.getValueAt(i, 7); // 7-InvoiceLine
				if (pp != null)
					C_InvoiceLine_ID = pp.getKey();
				if (C_InvoiceLine_ID != 0)
					il = new MInvoiceLine (Env.getCtx(), C_InvoiceLine_ID, null);
				boolean isInvoiced = (C_InvoiceLine_ID != 0);
				//	Precision of Qty UOM
				int precision = 2;
				if (M_Product_ID != 0)
				{
					MProduct product = MProduct.get(Env.getCtx(), M_Product_ID);
					precision = product.getUOMPrecision();
				}
				QtyEntered = QtyEntered.setScale(precision, BigDecimal.ROUND_HALF_DOWN);
				//
				log.fine("Line QtyEntered=" + QtyEntered
					+ ", Product=" + M_Product_ID 
					+ ", OrderLine=" + C_OrderLine_ID + ", InvoiceLine=" + C_InvoiceLine_ID);

				//	Credit Memo - negative Qty
				if (m_invoice != null && m_invoice.isCreditMemo() )
					QtyEntered = QtyEntered.negate();
				
				//	Create new InOut Line
				MInOutLine iol = new MInOutLine (inout);
				iol.setM_Product_ID(M_Product_ID, C_UOM_ID);	//	Line UOM
				iol.setQty(QtyEntered);							//	Movement/Entered
				//
				MOrderLine ol = null;
				if (C_OrderLine_ID != 0)
				{
					iol.setC_OrderLine_ID(C_OrderLine_ID);
					ol = new MOrderLine (Env.getCtx(), C_OrderLine_ID, null);
				//	iol.setOrderLine(ol, M_Locator_ID, QtyEntered);
					if (ol.getQtyEntered().compareTo(ol.getQtyOrdered()) != 0)
					{
						iol.setMovementQty(QtyEntered
							.multiply(ol.getQtyOrdered())
							.divide(ol.getQtyEntered(), 12, BigDecimal.ROUND_HALF_UP));
						iol.setC_UOM_ID(ol.getC_UOM_ID());
					}
					iol.setM_AttributeSetInstance_ID(ol.getM_AttributeSetInstance_ID());
					iol.setDescription(ol.getDescription());
					//
					iol.setC_Project_ID(ol.getC_Project_ID());
					iol.setC_ProjectPhase_ID(ol.getC_ProjectPhase_ID());
					iol.setC_ProjectTask_ID(ol.getC_ProjectTask_ID());
					iol.setC_Activity_ID(ol.getC_Activity_ID());
					iol.setC_Campaign_ID(ol.getC_Campaign_ID());
					iol.setAD_OrgTrx_ID(ol.getAD_OrgTrx_ID());
					iol.setUser1_ID(ol.getUser1_ID());
					iol.setUser2_ID(ol.getUser2_ID());
				}
				else if (il != null)
				{
				//	iol.setInvoiceLine(il, M_Locator_ID, QtyEntered);
					if (il.getQtyEntered().compareTo(il.getQtyInvoiced()) != 0)
					{
						iol.setQtyEntered(QtyEntered
							.multiply(il.getQtyInvoiced())
							.divide(il.getQtyEntered(), 12, BigDecimal.ROUND_HALF_UP));
						iol.setC_UOM_ID(il.getC_UOM_ID());
					}
					iol.setDescription(il.getDescription());
					iol.setC_Project_ID(il.getC_Project_ID());
					iol.setC_ProjectPhase_ID(il.getC_ProjectPhase_ID());
					iol.setC_ProjectTask_ID(il.getC_ProjectTask_ID());
					iol.setC_Activity_ID(il.getC_Activity_ID());
					iol.setC_Campaign_ID(il.getC_Campaign_ID());
					iol.setAD_OrgTrx_ID(il.getAD_OrgTrx_ID());
					iol.setUser1_ID(il.getUser1_ID());
					iol.setUser2_ID(il.getUser2_ID());
				}
				//	Charge
				if (M_Product_ID == 0)
				{
					if (ol != null && ol.getC_Charge_ID() != 0)			//	from order
						iol.setC_Charge_ID(ol.getC_Charge_ID());
					else if (il != null && il.getC_Charge_ID() != 0)	//	from invoice
						iol.setC_Charge_ID(il.getC_Charge_ID());
				}
				//
				iol.setM_Locator_ID(M_Locator_ID);
				if (!iol.save())
					log.log(Level.SEVERE, "Line NOT created #" + i);
				//	Create Invoice Line Link
				else if (il != null)
				{
					il.setM_InOutLine_ID(iol.getM_InOutLine_ID());
					il.save();
				}
			}   //   if selected
		}   //  for all rows

		/**
		 *  Update Header
		 *  - if linked to another order/invoice - remove link
		 *  - if no link set it
		 */
		if (p_order != null && p_order.getC_Order_ID() != 0)
		{
			inout.setC_Order_ID (p_order.getC_Order_ID());
			inout.setAD_OrgTrx_ID(p_order.getAD_OrgTrx_ID());
			inout.setC_Project_ID(p_order.getC_Project_ID());
			inout.setC_Campaign_ID(p_order.getC_Campaign_ID());
			inout.setC_Activity_ID(p_order.getC_Activity_ID());
			inout.setUser1_ID(p_order.getUser1_ID());
			inout.setUser2_ID(p_order.getUser2_ID());
		}
		if (m_invoice != null && m_invoice.getC_Invoice_ID() != 0)
		{
			if (inout.getC_Order_ID() == 0)
				inout.setC_Order_ID (m_invoice.getC_Order_ID());
			inout.setC_Invoice_ID (m_invoice.getC_Invoice_ID());
			inout.setAD_OrgTrx_ID(m_invoice.getAD_OrgTrx_ID());
			inout.setC_Project_ID(m_invoice.getC_Project_ID());
			inout.setC_Campaign_ID(m_invoice.getC_Campaign_ID());
			inout.setC_Activity_ID(m_invoice.getC_Activity_ID());
			inout.setUser1_ID(m_invoice.getUser1_ID());
			inout.setUser2_ID(m_invoice.getUser2_ID());
		}
		inout.save();
		return true;
	}   //  save

}   //  VCreateFromShipment
