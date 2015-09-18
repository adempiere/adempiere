/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2013 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpcya.com                                 *
 *****************************************************************************/
package org.compiere.pos;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.compiere.apps.ADialog;
import org.compiere.model.I_C_OrderLine;
import org.compiere.model.MBPartner;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MOrderTax;
import org.compiere.model.MPOS;
import org.compiere.model.MPayment;
import org.compiere.model.MPaymentProcessor;
import org.compiere.model.MProduct;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;

/**
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com Aug 30, 2015, 10:41:26 PM
 *
 */
public class CPOS {
	
	/**	POS Configuration		*/
	protected MPOS 				m_POS;
	/**	Current Order			*/
	protected MOrder			m_CurrentOrder;
	/** Sales Rep 				*/
	protected int				m_SalesRep_ID;
	/** Context					*/
	protected Properties		m_ctx = Env.getCtx();
	/**	Message					*/
	protected String 			msgLocator;
	/**	Today's (login) date	*/
	private Timestamp			m_today = Env.getContextAsDate(m_ctx, "#Date");
	
	/**	Logger					*/
	private CLogger 			log = CLogger.getCLogger(getClass());
	
	
	/**
	 * 	Set MPOS
	 *	@return true if found/set
	 */
	protected boolean setPOS() {
		MPOS[] poss = null;
		if (m_SalesRep_ID == 100)	//	superUser
			poss = getPOSs();
		else
			poss = getPOSs();
		//
		if (poss.length == 0) {
			msgLocator = "NoPOSForUser";
			return false;
		} else if (poss.length == 1) {
			m_POS = poss[0];
			return true;
		}
		//	
		return false;
	}	//	setMPOS

	/**
	 * Get Current Order
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return MOrder
	 */
	public MOrder getM_Order() {
		return m_CurrentOrder;
	}
	
	/**
	 * Get POS Configuration
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return MPOS
	 */
	public MPOS getM_POS() {
		return m_POS;
	}
	
	/**
	 * Get/create Order
		 *	@param pos MPOS
		 *	@param partner Business Partner
		 *	@param C_DocType_ID ID of document type
	 * 
	 * @return order or null
	 */
	protected MOrder createOrder(MBPartner partner, int C_DocType_ID) {
		MOrder order = new MOrder(Env.getCtx(), 0, null);
		order.setAD_Org_ID(m_POS.getAD_Org_ID());
		order.setIsSOTrx(true);
		order.setC_POS_ID(m_POS.getC_POS_ID());
		order.setM_Warehouse_ID(m_POS.getM_Warehouse_ID());
		if (C_DocType_ID != 0)
			order.setC_DocTypeTarget_ID(C_DocType_ID);
		else
			order.setC_DocTypeTarget_ID(MOrder.DocSubTypeSO_OnCredit);
		if (partner == null || partner.get_ID() == 0)
			partner = m_POS.getBPartner();
		if (partner == null || partner.get_ID() == 0) {
			throw new AdempierePOSException(Msg.getMsg(Env.getCtx(), "No BPartner for order"));
		}
		order.setBPartner(partner);
		//
		order.setM_PriceList_ID(m_POS.getM_PriceList_ID());
		order.setSalesRep_ID(m_POS.getSalesRep_ID());
		order.setPaymentRule(MOrder.PAYMENTRULE_Cash);
		if (!order.save()) {
			order = null;
			throw new AdempierePOSException(Msg.getMsg(Env.getCtx(), "Save order failed"));
		}
		
		return order;
	} // PosOrderModel	


	/**
	 * 	Get POSs for specific Sales Rep or all
	 *	@return array of POS
	 */
	protected MPOS[] getPOSs () {
		String pass_field = MPOS.COLUMNNAME_SalesRep_ID;
		int pass_ID = m_SalesRep_ID;
		if (m_SalesRep_ID == 100) {
			pass_field = MPOS.COLUMNNAME_AD_Client_ID;
			pass_ID = Env.getAD_Client_ID(m_ctx);
		}
		return MPOS.getAll(m_ctx, pass_field, pass_ID);
	}	//	getPOSs

	/**************************************************************************
	 * 	Get Today's date
	 *	@return date
	 */
	public Timestamp getToday() {
		return m_today;
	}	//	getToday
	
	/**
	 * 	New Order
	 *   
	 */
	public void newOrder(MBPartner p_C_BPartner, boolean isDocType) {
		log.info( "PosPanel.newOrder");
		m_CurrentOrder = null;
		int m_C_DocType_ID = m_POS.getC_DocType_ID();
		int m_C_DocTypewholesale_ID = m_POS.getC_DocTypewholesale_ID();;
		if (m_C_DocTypewholesale_ID > 0) {
			//	Do you want to use the alternate Document type?
			if (isDocType) {
				m_C_DocType_ID = m_C_DocTypewholesale_ID;
			}
		}
		//	Create Order
		m_CurrentOrder = createOrder(p_C_BPartner, m_C_DocType_ID);
		//	
		updateInfo();
	}	//	newOrder

	/**
	 * @param m_c_order_id
	 */
	public void setOldOrder(int m_c_order_id) {
		deleteOrder();
		//	
		if ( m_c_order_id == 0 )
			m_CurrentOrder = null;
		else 
			m_CurrentOrder = new MOrder(m_ctx , m_c_order_id, null);
		//	
		updateInfo();
	}
	
	/**
	 * Update Order Info
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return void
	 */
	public void updateInfo() {
		// reload order
		reload();
	}
	
	/**
	 * @param m_c_order_id
	 */
	public void setOrder(int m_c_order_id) {
		if ( m_c_order_id == 0 )
			m_CurrentOrder = null;
		else
			m_CurrentOrder = new MOrder(m_ctx , m_c_order_id, null);
	}
	
	/**
	 * set BPartner and save
	 */
	public void setBPartner(MBPartner partner) {
		if (m_CurrentOrder.getDocStatus().equals(DocAction.STATUS_Drafted)) {
			if (partner == null || partner.get_ID() == 0) {
				throw new AdempierePOSException("no BPartner");
			} else {
				log.info("SubCurrentLine.getOrder -" + partner);
				m_CurrentOrder.setBPartner(partner);
				MOrderLine[] lineas = m_CurrentOrder.getLines();
				for (int i = 0; i < lineas.length; i++) {
					lineas[i].setC_BPartner_ID(partner.getC_BPartner_ID());
					lineas[i].setTax();
					lineas[i].save();
				}
				//	
				m_CurrentOrder.saveEx();
			}
		}

	}

	/**
	 * Create new Line
	 * 
	 * @return line or null
	 */
	public MOrderLine createLine(MProduct product, BigDecimal QtyOrdered,
			BigDecimal PriceActual) {
		
		if (!m_CurrentOrder.getDocStatus().equals(DocAction.STATUS_Drafted) )
			return null;
		//add new line or increase qty
		
		// catch Exceptions at order.getLines()
		int numLines = 0;
		MOrderLine[] lines = null;
		try {
			lines = m_CurrentOrder.getLines(null, "Line");
			numLines = lines.length;
			for (int i = 0; i < numLines; i++) {
				if (lines[i].getM_Product_ID() == product.getM_Product_ID()) {
					//increase qty
					BigDecimal current = lines[i].getQtyEntered();
					BigDecimal toadd = QtyOrdered;
					BigDecimal total = current.add(toadd);
					lines[i].setQty(total);
					lines[i].setPrice(); //	sets List/limit
					if ( PriceActual.compareTo(Env.ZERO) > 0 )
						lines[i].setPrice(PriceActual);
					lines[i].save();
					return lines[i];
				}
			}
		} catch (Exception e) {
			log.severe("Order lines cannot be created - " + e.getMessage());
		}

        //create new line
		MOrderLine line = new MOrderLine(m_CurrentOrder);
		line.setProduct(product);
		line.setQty(QtyOrdered);
			
		line.setPrice(); //	sets List/limit
		if ( PriceActual.compareTo(Env.ZERO) > 0 )
			line.setPrice(PriceActual);
		line.save();
		return line;
			
	} //	createLine
	
	
	/**
	 * Delete order from database
	 * 
	 * @return true if order deleted; otherwise false
	 */		
	public boolean deleteOrder () {
		//	Valid Null Pointer
		if(m_CurrentOrder == null)
			return false;
		//	
		if (m_CurrentOrder.getDocStatus().equals(DocAction.STATUS_Drafted)) {
				MOrderLine[] lines = m_CurrentOrder.getLines();
				if (lines != null) {
					int numLines = lines.length;
					if (numLines > 0)
						for (int i = numLines - 1; i >= 0; i--) {
							if (lines[i] != null)
								deleteLine(lines[i].getC_Order_ID());
						}
				}
				
				MOrderTax[] taxs = m_CurrentOrder.getTaxes(true);
				if (taxs != null) {
					int numTax = taxs.length;
					if (numTax > 0)
						for (int i = taxs.length - 1; i >= 0; i--) {
							if (taxs[i] != null)
								taxs[i].delete(true);
							taxs[i] = null;
						}
				}
				//	
				m_CurrentOrder.getLines(true, null);		// requery order
				
				return m_CurrentOrder.voidIt(); 
			}
		return false;
	} //	deleteOrder
	
	/** 
	 * Delete one order line
	 * To erase one line from order
	 * 
	 */
	public void deleteLine (int C_OrderLine_ID) {
		if ( C_OrderLine_ID != -1 ) {
			for ( MOrderLine line : m_CurrentOrder.getLines(true, I_C_OrderLine.COLUMNNAME_M_Product_ID) ) {
				if ( line.getC_OrderLine_ID() == C_OrderLine_ID ) {
					line.delete(true);	
				}
			}
		}
	} //	deleteLine

	/**
	 * 	Process Order
	 * Only for status "Drafted" or "In Progress"
	 * 
	 * @return true if order processed; otherwise false
	 * 
	 */
	public boolean processOrder() {		
		//Returning orderCompleted to check for order completeness
		boolean orderCompleted = false;
		// check if order completed OK
		if (m_CurrentOrder.getDocStatus().equals(DocAction.STATUS_Drafted) 
				|| m_CurrentOrder.getDocStatus().equals(DocAction.STATUS_InProgress) ) { 
			m_CurrentOrder.setDocAction(DocAction.ACTION_Complete);
			try {
				if (m_CurrentOrder.processIt(DocAction.ACTION_Complete) ) {
					m_CurrentOrder.saveEx();
				} else {
					log.info( "Process Order FAILED"+m_CurrentOrder.getProcessMsg());		
				}
			} catch (Exception e) {
				log.severe("Order can not be completed - " + e.getMessage());
			} finally { // When order failed convert it back to draft so it can be processed
				if(m_CurrentOrder.getDocStatus().equals(DocAction.STATUS_Invalid) ) {
					m_CurrentOrder.setDocStatus(DocAction.STATUS_Drafted);
				} else if(m_CurrentOrder.getDocStatus().equals(DocAction.STATUS_Completed) ) {
					orderCompleted = true;
					log.info( "SubCheckout - processOrder OK");	 
				} else {
					log.info( "SubCheckout - processOrder - unrecognized DocStatus"); 
				}					
			} // try-finally
		}
		return orderCompleted;
	}	// processOrder


	/**
	 * 	Gets Tax Amt from Order
	 * 
	 */
	public BigDecimal getTaxAmt() {
		BigDecimal taxAmt = Env.ZERO;
		for (MOrderTax tax : m_CurrentOrder.getTaxes(true)) {
			taxAmt = taxAmt.add(tax.getTaxAmt());
		}
		return taxAmt;
	}


	/**
	 * 	Gets Subtotal from Order
	 * 
	 */
	public BigDecimal getSubtotal() {
		return m_CurrentOrder.getGrandTotal().subtract(getTaxAmt());
	}

	/**
	 * 	Gets Amount Paid from Order
	 * 
	 */

	public BigDecimal getPaidAmt() {
		String sql = "SELECT sum(PayAmt) FROM C_Payment WHERE C_Order_ID = ? AND DocStatus IN ('CO','CL')";
		BigDecimal received = DB.getSQLValueBD(null, sql, m_CurrentOrder.getC_Order_ID());
		if ( received == null )
			received = Env.ZERO;
		
		sql = "SELECT sum(Amount) FROM C_CashLine WHERE C_Invoice_ID = ? ";
		BigDecimal cashline = DB.getSQLValueBD(null, sql, m_CurrentOrder.getC_Invoice_ID());
		if ( cashline != null )
			received = received.add(cashline);
		
		return received;
	}

	/**
	 * 	Load Order
	 * 
	 */
	public void reload() {
		if (m_CurrentOrder == null)
			return;
		m_CurrentOrder.load(m_CurrentOrder.get_TrxName());
		m_CurrentOrder.getLines(true, "");
	}

	/**
	 * 	Get Bank Data
	 * 
	 */
	public ValueNamePair[] getBank(){
		return DB.getValueNamePairs("SELECT C_Bank_ID, Name FROM C_Bank", true, null);
	}
	/**
	 * Duplicated from MPayment
	 * 	Get Accepted Credit Cards for amount
	 *	@param amt trx amount
	 *	@return credit cards
	 */
	public ValueNamePair[] getCreditCards (BigDecimal amt) {
		try {
			MPaymentProcessor[] m_mPaymentProcessors = MPaymentProcessor.find (Env.getCtx(), null, null, 
					m_CurrentOrder.getAD_Client_ID (), m_CurrentOrder.getAD_Org_ID(), m_CurrentOrder.getC_Currency_ID (), amt, m_CurrentOrder.get_TrxName());
			//
			HashMap<String,ValueNamePair> map = new HashMap<String,ValueNamePair>(); //	to eliminate duplicates
			for (int i = 0; i < m_mPaymentProcessors.length; i++) {
				if (m_mPaymentProcessors[i].isAcceptAMEX ())
					map.put (MPayment.CREDITCARDTYPE_Amex, getCreditCardPair (MPayment.CREDITCARDTYPE_Amex));
				if (m_mPaymentProcessors[i].isAcceptDiners ())
					map.put (MPayment.CREDITCARDTYPE_Diners, getCreditCardPair (MPayment.CREDITCARDTYPE_Diners));
				if (m_mPaymentProcessors[i].isAcceptDiscover ())
					map.put (MPayment.CREDITCARDTYPE_Discover, getCreditCardPair (MPayment.CREDITCARDTYPE_Discover));
				if (m_mPaymentProcessors[i].isAcceptMC ())
					map.put (MPayment.CREDITCARDTYPE_MasterCard, getCreditCardPair (MPayment.CREDITCARDTYPE_MasterCard));
				if (m_mPaymentProcessors[i].isAcceptCorporate ())
					map.put (MPayment.CREDITCARDTYPE_PurchaseCard, getCreditCardPair (MPayment.CREDITCARDTYPE_PurchaseCard));
				if (m_mPaymentProcessors[i].isAcceptVisa ())
					map.put (MPayment.CREDITCARDTYPE_Visa, getCreditCardPair (MPayment.CREDITCARDTYPE_Visa));
			} //	for all payment processors
			//
			ValueNamePair[] retValue = new ValueNamePair[map.size ()];
			map.values ().toArray (retValue);
			log.fine("getCreditCards - #" + retValue.length + " - Processors=" + m_mPaymentProcessors.length);
			return retValue;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}	//	getCreditCards


	/**
	 * 	Get Credit Notes
	 * 
	 */
	public ValueNamePair[] getCreditNotes () {
		try {
			String whereClause = "c_bpartner_ID =? and ispaid ='N' and c_doctype_ID in (select c_doctype_ID from c_doctype where docbasetype ='ARC')";
			List<MInvoice> cns = new Query(Env.getCtx(), MInvoice.Table_Name, whereClause, null)
				.setParameters(m_CurrentOrder.getC_BPartner_ID())
				.list();
			//
			HashMap<String,ValueNamePair> map = new HashMap<String,ValueNamePair>(); //	to eliminate duplicates
			ValueNamePair vpv;
			for (MInvoice inv : cns) {
				Integer ID = inv.getC_Invoice_ID();
				vpv = new ValueNamePair(ID.toString(), inv.getDocumentNo() + " " + inv.getOpenAmt().toString());
					map.put (inv.getDocumentNo(), vpv);
			} //	for all payment processors
			//
			ValueNamePair[] retValue = new ValueNamePair[map.size ()];
			map.values ().toArray (retValue);
			return retValue;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}	//	getCreditCards
	
	/**
	 * 
	 * Duplicated from MPayment
	 * 	Get Type and name pair
	 *	@param CreditCardType credit card Type
	 *	@return pair
	 */
	private ValueNamePair getCreditCardPair (String CreditCardType) {
		return new ValueNamePair (CreditCardType, getCreditCardName(CreditCardType));
	}	//	getCreditCardPair

	/**
	 * 
	 * Duplicated from MPayment
	 *	Get Name of Credit Card
	 * 	@param CreditCardType credit card type
	 *	@return Name
	 */
	public String getCreditCardName(String CreditCardType) {
		if (CreditCardType == null)
			return "--";
		else if (MPayment.CREDITCARDTYPE_MasterCard.equals(CreditCardType))
			return "MasterCard";
		else if (MPayment.CREDITCARDTYPE_Visa.equals(CreditCardType))
			return "Visa";
		else if (MPayment.CREDITCARDTYPE_Amex.equals(CreditCardType))
			return "Amex";
		else if (MPayment.CREDITCARDTYPE_ATM.equals(CreditCardType))
			return "ATM";
		else if (MPayment.CREDITCARDTYPE_Diners.equals(CreditCardType))
			return "Diners";
		else if (MPayment.CREDITCARDTYPE_Discover.equals(CreditCardType))
			return "Discover";
		else if (MPayment.CREDITCARDTYPE_PurchaseCard.equals(CreditCardType))
			return "PurchaseCard";
		return "?" + CreditCardType + "?";
	}	//	getCreditCardName
	


	/**
	 * 	Call Order void process 
	 *  Only if Order is "In Progress" or "Completed"
	 * 
	 *  @return true if order voided; false otherwise
	 */
	public boolean cancelOrder() {
		if (!m_CurrentOrder.getDocStatus().equals(MOrder.STATUS_Completed) 
				|| !m_CurrentOrder.getDocStatus().equals(DocAction.STATUS_InProgress)) 
			return false;
		
		// Standard way of voiding an order
		m_CurrentOrder.setDocAction(MOrder.DOCACTION_Void);
		if (m_CurrentOrder.processIt(MOrder.DOCACTION_Void) ) {
			m_CurrentOrder.setDocAction(MOrder.DOCACTION_None);
			m_CurrentOrder.setDocStatus(MOrder.STATUS_Voided);
			m_CurrentOrder.saveEx();
			return true;
		} else 
			return false;
	} // cancelOrder
	
	/**
	 * Get Context
	 * @author <a href="mailto:yamelsenih@gmail.com">Yamel Senih</a> Aug 31, 2015, 8:23:54 PM
	 * @return
	 * @return Properties
	 */
	public Properties getCtx() {
		return m_ctx;
	}
}