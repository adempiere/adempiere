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

import org.compiere.model.I_C_OrderLine;
import org.compiere.model.MBPartner;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MOrderTax;
import org.compiere.model.MPOS;
import org.compiere.model.MPayment;
import org.compiere.model.MPaymentProcessor;
import org.compiere.model.MPriceList;
import org.compiere.model.MPriceListVersion;
import org.compiere.model.MProduct;
import org.compiere.model.MUser;
import org.compiere.model.MWarehousePrice;
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
	private MOrder				m_CurrentOrder;
	/**	The Business Partner	*/
	private MBPartner			m_BPartner;
	/**	Price List Version		*/
	private int					m_M_PriceList_Version_ID;
	/**	Currency				*/
	private int					m_C_Currency_ID;
	/** Sales Rep 				*/
	protected int				m_SalesRep_ID;
	/** Context					*/
	protected Properties		m_ctx = Env.getCtx();
	/**	Message					*/
	protected String 			msgLocator;
	/**	Today's (login) date	*/
	private Timestamp			m_today = Env.getContextAsDate(m_ctx, "#Date");
	private boolean				isPrepayment = false;

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
	 * Has Order
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return boolean
	 */
	public boolean hasOrder() {
		return m_CurrentOrder != null;
	}
	
	/**
	 * Has Business Partner
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return boolean
	 */
	public boolean hasBPartner() {
		return m_BPartner != null;
	}
	
	/**
	 * Compare BP Name
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @param p_Name
	 * @return
	 * @return boolean
	 */
	public boolean compareBPName(String p_Name) {
		return m_BPartner.getName().equals(p_Name);
	}
	
	/**
	 * 	Get BPartner
	 *	@return C_BPartner_ID
	 */
	public int getC_BPartner_ID () {
		if (m_BPartner != null)
			return m_BPartner.getC_BPartner_ID();
		return 0;
	}	//	getC_BPartner_ID
	
	/**
	 * 	Get BPartner Location
	 *	@return C_BPartner_Location_ID
	 */
	public int getC_BPartner_Location_ID () {
//		if (m_bpartner != null) {
//			KeyNamePair pp = (KeyNamePair)f_location.getSelectedItem();
//			if (pp != null)
//				return pp.getKey();
//		}
		return 0;
	}	//	getC_BPartner_Location_ID
	
	/**
	 * 	Get BPartner Contact
	 *	@return AD_User_ID
	 */
	public int getAD_User_ID () {
		if (m_BPartner != null) {
			return m_SalesRep_ID;
		}
		return 0;
	}	//	getAD_User_ID
	
	/**
	 * Get Sales Rep. Name
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return String
	 */
	public String getSalesRepName() {
		MUser salesrep = MUser.get(m_ctx);
		if(salesrep == null) {
			return null;
		}
		//	Default Return
		return salesrep.getName();
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
	
	/***
	 * Get PayAmt 
	 */
	public BigDecimal getPayAmt(){
		String sql ="SELECT Sum(PayAmt) FROM C_Order o"
				+ " LEFT JOIN c_invoice i on i.c_order_ID = o.c_order_ID"
				+ " LEFT JOIN C_Payment p on p.c_invoice_ID = i.c_invoice_ID"
				+ " WHERE"
				+ " coalesce(invoiceopen(i.c_invoice_ID, 0), 0)  >= 0 and"
				+ " o.C_Order_ID = ?";
		BigDecimal received = DB.getSQLValueBD(null, sql, m_CurrentOrder.getC_Order_ID());
		if ( received == null )
			received = Env.ZERO;

		return received;
	}
	/**
	 * 	New Order
	 *   
	 */
	public void newOrder(boolean isDocType) {
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
		m_CurrentOrder = createOrder(m_BPartner, m_C_DocType_ID);
		//	
		reloadOrder();
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
		reloadOrder();
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
				if (PriceActual.compareTo(Env.ZERO) > 0) {
					lines[i].setPrice(PriceActual);
				}
				lines[i].saveEx();
				return lines[i];
			}
		}
        //create new line
		MOrderLine line = new MOrderLine(m_CurrentOrder);
		line.setProduct(product);
		line.setQty(QtyOrdered);
			
		line.setPrice(); //	sets List/limit
		if ( PriceActual.compareTo(Env.ZERO) > 0 ) {
			line.setPrice(PriceActual);
		}
		//	Save Line
		line.saveEx();
		return line;
			
	} //	createLine
	
	/**
	 * Get Product Price
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @param p_Product
	 * @return
	 * @return BigDecimal
	 */
	public BigDecimal getPrice(MProduct p_Product) {
		if (p_Product == null)
			return Env.ZERO;
		//
		MWarehousePrice result = MWarehousePrice.get (p_Product,
			m_M_PriceList_Version_ID, m_POS.getM_Warehouse_ID(), null);
		if (result != null) {
			return result.getPriceStd();
		}
		//	Default to return
		return Env.ZERO;

	}	//	setPrice
	
	/**
	 * Save Line
	 * 
	 * @return true if saved
	 */
	public String saveLine(int p_M_Product_ID, BigDecimal p_QtyOrdered) {
		String m_Error = null;
		try {
			MProduct m_Product = MProduct.get(m_ctx, p_M_Product_ID);
			if (m_Product == null)
				return "@No@ @InfoProduct@";
			BigDecimal PriceActual = getPrice(m_Product);
			//	Validate if exists a order
			if (hasOrder()) {
				createLine(m_Product, p_QtyOrdered, PriceActual);
			} else {
				return "@POS.MustCreateOrder@";
			}
		} catch (Exception e) {
			m_Error = e.getMessage();
		}
		//	
		return m_Error;
	} //	saveLine
	
	
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
								deleteLine(lines[i].getC_OrderLine_ID());
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
				
				return cancelOrder(); 
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
	 * For status "Drafted" or "In Progress": process order
	 * For status "Completed": do nothing as it can be pre payment or payment on credit
	 * 
	 * @return true if order processed or pre payment/on credit; otherwise false
	 * 
	 */
	public boolean processOrder(String trxName) {		
		//Returning orderCompleted to check for order completeness
		boolean orderCompleted = false;
		// check if order completed OK
		if (m_CurrentOrder.getDocStatus().equals(DocAction.STATUS_Drafted) 
				|| m_CurrentOrder.getDocStatus().equals(DocAction.STATUS_InProgress) ) {
			//	Replace
			if(trxName == null) {
				trxName = m_CurrentOrder.get_TrxName();
			} else {
				m_CurrentOrder.set_TrxName(trxName);
			}
			if(isPrepayment()) {
				; // TODO: implement Prepayment
			}
			m_CurrentOrder.setDocAction(DocAction.ACTION_Complete);
			if (m_CurrentOrder.processIt(DocAction.ACTION_Complete) ) {
				m_CurrentOrder.saveEx();
				orderCompleted = true;
			} else {
				log.info( "Process Order FAILED "+m_CurrentOrder.getProcessMsg());		
			}
		}
		else 
			if (m_CurrentOrder.getDocStatus().equals(DocAction.STATUS_Completed) ) { 
				orderCompleted = true;
			}
		return orderCompleted;
	}	// processOrder
	
	/**
	 * Process Order Without transaction name
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return boolean
	 */
	protected boolean processOrder() {
		return processOrder(null);
	}
	
	/**
	 * Get Process Message
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return String
	 */
	protected String getProcessMsg() {
		return m_CurrentOrder.getProcessMsg();
	}


	/**
	 * 	Gets Tax Amt from Order
	 * 
	 */
	protected BigDecimal getTaxAmt() {
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
	protected BigDecimal getTotalLines() {
		return m_CurrentOrder.getGrandTotal().subtract(getTaxAmt());
	}
	
	/**
	 * Get Grand Total for current Order
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return BigDecimal
	 */
	protected BigDecimal getGrandTotal() {
		return m_CurrentOrder.getGrandTotal();
	}
	
	/**
	 * Get Document No
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return String
	 */
	protected String getDocumentNo() {
		return m_CurrentOrder.getDocumentNo();
	}
	
	public BigDecimal getOpenAmt() {
		BigDecimal received = getPaidAmt();	
		return m_CurrentOrder.getGrandTotal().subtract(received);
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
	public void reloadOrder() {
		if (m_CurrentOrder == null)
			return;
		m_CurrentOrder.load(m_CurrentOrder.get_TrxName());
		m_CurrentOrder.getLines(true, "");
	}
	
	/**
	 * 	Get M_PriceList_Version_ID.
	 * 	Set Currency
	 *	@return plv
	 */
	public int getM_PriceList_Version_ID() {
		if (m_M_PriceList_Version_ID == 0) {
			int M_PriceList_ID = m_POS.getM_PriceList_ID();
			if (m_BPartner != null && m_BPartner.getM_PriceList_ID() != 0)
				M_PriceList_ID = m_BPartner.getM_PriceList_ID();
			//
			MPriceList pl = MPriceList.get(m_ctx, M_PriceList_ID, null);
			m_C_Currency_ID = pl.getC_Currency_ID();
			//
			MPriceListVersion plv = pl.getPriceListVersion (getToday());
			if (plv != null && plv.getM_PriceList_Version_ID() != 0)
				m_M_PriceList_Version_ID = plv.getM_PriceList_Version_ID();
		}
		//	Default Return
		return m_M_PriceList_Version_ID;
	}	//	getM_PriceList_Version_ID
	
	/**
	 * Get Warehouse Identifier
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return int
	 */
	public int getM_Warehouse_ID() {
		return m_POS.getM_Warehouse_ID();
	}
	
	/**
	 * 	Set BPartner
	 *	@param C_BPartner_ID id
	 */
	public void setC_BPartner_ID (int C_BPartner_ID) {
		log.fine( "PosSubCustomer.setC_BPartner_ID=" + C_BPartner_ID);
		if (C_BPartner_ID == 0)
			m_BPartner = null;
		else {
			m_BPartner = MBPartner.get(m_ctx, C_BPartner_ID);
			if (m_BPartner.get_ID() == 0)
				m_BPartner = null;
		}
		//	Sets Currency
		m_M_PriceList_Version_ID = 0;
		getM_PriceList_Version_ID();
	}	//	setC_BPartner_ID
	
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
	 *  Only if Order is "Drafted", "In Progress" or "Completed"
	 * 
	 *  @return true if order voided; false otherwise
	 */
	public boolean cancelOrder() {
		if (!(m_CurrentOrder.getDocStatus().equals(MOrder.STATUS_Drafted) 
				|| m_CurrentOrder.getDocStatus().equals(DocAction.STATUS_InProgress)
				|| m_CurrentOrder.getDocStatus().equals(DocAction.STATUS_Completed))) 
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
	
	/**
	 * Get POS Key Layout Identifier
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return int
	 */
	public int getOSKeyLayout_ID() {
		if(m_POS != null) {
			return m_POS.getOSK_KeyLayout_ID();
		}
		//	Default Return
		return 0;
	}
	
	/**
	 * Get Order Identifier
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return int
	 */
	protected int getC_Order_ID() {
		return m_CurrentOrder.getC_Order_ID();
	}
	
	public boolean isPrepayment() {
		return isPrepayment;
	}

	public void setPrepayment(boolean isPrepayment) {
		this.isPrepayment = isPrepayment;
	}
}
