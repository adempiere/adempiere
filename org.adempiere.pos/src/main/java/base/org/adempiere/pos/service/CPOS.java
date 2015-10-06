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
package org.adempiere.pos.service;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.compiere.model.I_C_OrderLine;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MCurrency;
import org.compiere.model.MDocType;
import org.compiere.model.MInvoice;
import org.compiere.model.MLocator;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MOrderTax;
import org.compiere.model.MPOS;
import org.compiere.model.MPayment;
import org.compiere.model.MPaymentProcessor;
import org.compiere.model.MPriceList;
import org.compiere.model.MPriceListVersion;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPrice;
import org.compiere.model.MTax;
import org.compiere.model.MUser;
import org.compiere.model.MWarehouse;
import org.compiere.model.MWarehousePrice;
import org.compiere.model.Query;
import org.compiere.model.X_C_Order;
import org.adempiere.pos.AdempierePOSException;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.ValueNamePair;

/**
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * @author victor.perez@e-evolution.com , http://www.e-evolution.com
 */
public class CPOS {
	
	/**
	 * 
	 * *** Constructor ***
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 */
	public CPOS() {
		m_ctx = Env.getCtx();
	}
	
	/**	POS Configuration		*/
	private MPOS 				m_POS;
	/**	Current Order			*/
	private MOrder				m_CurrentOrder;
	/**	The Business Partner	*/
	private MBPartner			m_BPartner;
	/**	Price List Version		*/
	private int					m_M_PriceList_Version_ID;
	/** Context					*/
	protected Properties		m_ctx = Env.getCtx();
	/**	Today's (login) date	*/
	private Timestamp			m_today = Env.getContextAsDate(m_ctx, "#Date");
	/**	Order List				*/
	private ArrayList<Integer>	m_OrderList;
	/**	Order List Position		*/
	private int 				m_RecordPosition;
	/**	Logger					*/
	private CLogger 			log = CLogger.getCLogger(getClass());
	
	
	/**
	 * 	Set MPOS
	 * @param p_SalesRep_ID
	 * @return true if found/set
	 */
	public void setPOS(int p_SalesRep_ID) {
		MPOS[] poss = getPOSs(p_SalesRep_ID);
		//	
		if (poss.length == 0) {
			throw new AdempierePOSException("@NoPOSForUser@");
		} else if (poss.length == 1) {
			m_POS = poss[0];
		}
	}	//	setMPOS
	
	/**
	 * Set POS
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @param pos
	 * @return void
	 */
	public void setM_POS(MPOS pos) {
		m_POS = pos;
	}
	
	/**
	 * Validate if is Order Completed
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return boolean
	 */
	public boolean isCompleted() {
		return m_CurrentOrder.isProcessed() 
				&& m_CurrentOrder.getDocStatus()
				.equals(X_C_Order.DOCSTATUS_Completed);
	}
	
	/**
	 * Validate if has lines
	 * @return
	 * @return boolean
	 */
	public boolean hasLines() {
		return m_CurrentOrder.getLines().length > 0;
	}
	
	/**
	 * Validate if is voided
	 * @return
	 * @return boolean
	 */
	public boolean isVoided() {
		return m_CurrentOrder.getDocStatus()
				.equals(X_C_Order.DOCSTATUS_Voided);
	}
	
	/**
	 * Validate if is POS Order
	 * @return
	 * @return boolean
	 */
	public boolean isPOSOrder() {
		return m_CurrentOrder
			.getC_DocType().getDocSubTypeSO()
			.equalsIgnoreCase(MOrder.DocSubTypeSO_POS);
	}
	
	/**
	 * Validate if is Credit Order
	 * @return
	 * @return boolean
	 */
	public boolean isCreditOrder() {
		return m_CurrentOrder
				.getC_DocType().getDocSubTypeSO()
				.equalsIgnoreCase(MOrder.DocSubTypeSO_OnCredit);
	}
	
	/**
	 * Validate if is Standard Order
	 * @return
	 * @return boolean
	 */
	public boolean isStandardOrder() {
		return m_CurrentOrder
				.getC_DocType().getDocSubTypeSO()
				.equalsIgnoreCase(MOrder.DocSubTypeSO_Standard);
	}
	
	/**
	 * Validate if is Prepay Order
	 * @return
	 * @return boolean
	 */
	public boolean isPrepayOrder() {
		return m_CurrentOrder
				.getC_DocType().getDocSubTypeSO()
				.equalsIgnoreCase(MOrder.DocSubTypeSO_Prepay);
	}

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
		if (hasBPartner())
			return m_BPartner.getC_BPartner_ID();
		return 0;
	}	//	getC_BPartner_ID
	
	
	/**
	 * Get Business Partner Name
	 * @return
	 * @return String
	 */
	public String getBPName() {
		if (hasBPartner())
			return m_BPartner.getName();
		return null;
	}
	
	/**
	 * Get Currency Identifier
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return int
	 */
	public int getC_Currency_ID() {
		if (hasBPartner()) {
			return m_CurrentOrder.getC_Currency_ID();
		}
		//	Default
		return 0;
	}
	
	/**
	 * 	Get BPartner Contact
	 *	@return AD_User_ID
	 */
	public int getAD_User_ID () {
		return 0;
	}	//	getAD_User_ID
	
	/**
	 * Get Auto Delay
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return int
	 */
	public int getAutoLogoutDelay() {
		return m_POS.getAutoLogoutDelay();
	}
	
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
	 * Get Sales Representative
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return int
	 */
	public int getSalesRep_ID() {
		return m_POS.getSalesRep_ID();
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
	 * Get POS Name
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return String
	 */
	public String getPOSName() {
		return m_POS.getName();
	}
	
	/**
	 * Get POS Identifier
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return int
	 */
	public int getC_POS_ID() {
		return m_POS.getC_POS_ID();
	}
	
	/**
	 * 	New Order
	 *  @param isDocType
	 *  @param p_C_BPartner_ID
	 */
	public void newOrder(boolean isDocType, int p_C_BPartner_ID) {
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
		createOrder(p_C_BPartner_ID, m_C_DocType_ID);
		//	
		reloadOrder();
	}	//	newOrder
	
	/**
	 * New Order
	 * @param isDocType
	 * @return void
	 */
	public void newOrder(boolean isDocType) {
		newOrder(isDocType, 0);
	}
	
	/**
	 * Get/create Order
	 *	@param p_C_BPartner_ID Business Partner
	 *	@param C_DocType_ID ID of document type
	 */
	public void createOrder(int p_C_BPartner_ID, int C_DocType_ID) {
		int m_Free_C_Order_ID = getFreeC_Order_ID();
		//	Change Values for new Order
		if(m_Free_C_Order_ID > 0) {
			m_CurrentOrder = new MOrder(Env.getCtx(), m_Free_C_Order_ID, null);
			m_CurrentOrder.setDateOrdered(getToday());
			m_CurrentOrder.setDateAcct(getToday());
			m_CurrentOrder.setDatePromised(getToday());
		} else {
			m_CurrentOrder = new MOrder(Env.getCtx(), 0, null);
		}
		m_CurrentOrder.setAD_Org_ID(m_POS.getAD_Org_ID());
		m_CurrentOrder.setIsSOTrx(true);
		m_CurrentOrder.setC_POS_ID(m_POS.getC_POS_ID());
		m_CurrentOrder.setM_Warehouse_ID(m_POS.getM_Warehouse_ID());
		if (C_DocType_ID != 0) {
			m_CurrentOrder.setC_DocTypeTarget_ID(C_DocType_ID);
		} else {
			m_CurrentOrder.setC_DocTypeTarget_ID(MOrder.DocSubTypeSO_OnCredit);
		}
		//	Set BPartner
		setC_BPartner_ID(p_C_BPartner_ID);
	} // PosOrderModel
	
	/**
	 * Find a free order and reuse
	 * @return
	 * @return int
	 */
	private int getFreeC_Order_ID() {
		return DB.getSQLValue(null, "SELECT o.C_Order_ID "
				+ "FROM C_Order o "
				+ "WHERE o.DocStatus = 'DR' "
				+ "AND o.C_POS_ID = ? "
				+ "AND o.SalesRep_ID = ? "
				+ "AND NOT EXISTS(SELECT 1 "
				+ "					FROM C_OrderLine ol "
				+ "					WHERE ol.C_Order_ID = o.C_Order_ID) "
				+ "ORDER BY o.Updated", 
				getC_POS_ID(), getSalesRep_ID());
	}
	
	/**
	 * 	Set BPartner, update price list and locations
	 *	@param p_C_BPartner_ID id
	 */
	
	/**
	 * set BPartner and save
	 */
	public void setC_BPartner_ID(int p_C_BPartner_ID) {
		//	Valid if has a Order
		if(!hasOrder() 
				|| isCompleted())
			return;
		log.fine( "CPOS.setC_BPartner_ID=" + p_C_BPartner_ID);
		boolean isSamePOSPartner = false;
		//	Validate BPartner
		if (p_C_BPartner_ID == 0) {
			isSamePOSPartner = true;
			p_C_BPartner_ID = m_POS.getC_BPartnerCashTrx_ID();
		}
		//	Get BPartner
		m_BPartner = MBPartner.get(m_ctx, p_C_BPartner_ID);
		if (m_BPartner == null || m_BPartner.get_ID() == 0) {
			throw new AdempierePOSException("POS.NoBPartnerForOrder");
		} else {
			log.info("CPOS.SetC_BPartner_ID -" + m_BPartner);
			m_CurrentOrder.setBPartner(m_BPartner);
			//	
			if (m_BPartner != null) {
				m_CurrentOrder.setBPartner(m_BPartner);
				//	
				MBPartnerLocation [] m_BPLocations = m_BPartner.getLocations(true);
				if(m_BPLocations.length > 0) {
					for(MBPartnerLocation loc : m_BPLocations) {
						if(loc.isBillTo())
							m_CurrentOrder.setBill_Location_ID(loc.getC_BPartner_Location_ID());	
						if(loc.isShipTo())
							m_CurrentOrder.setShip_Location_ID(loc.getC_BPartner_Location_ID());
					}				
				}
			}
			//	Validate Same BPartner
			if(isSamePOSPartner) {
				m_CurrentOrder.setM_PriceList_ID(m_POS.getM_PriceList_ID());
				m_CurrentOrder.setPaymentRule(MOrder.PAYMENTRULE_Cash);
			}
			//	Set Sales Representative
			m_CurrentOrder.setSalesRep_ID(m_POS.getSalesRep_ID());
			//	Save Header
			m_CurrentOrder.saveEx();
			//	Load Price List Version
			MPriceListVersion plv = loadPriceListVersion(m_CurrentOrder.getM_PriceList_ID());
			MProductPrice[] ppList = plv.getProductPrice("AND EXISTS("
					+ "SELECT 1 "
					+ "FROM C_OrderLine ol "
					+ "WHERE ol.C_Order_ID = " + m_CurrentOrder.getC_Order_ID() + " "
					+ "AND ol.M_Product_ID = M_ProductPrice.M_Product_ID)");
			//	Update Lines
			MOrderLine[] lines = m_CurrentOrder.getLines();
			//	Delete if not exist in price list
			for (MOrderLine line : lines) {
				//	Verify if exist
				if(existInPriceList(line.getM_Product_ID(), ppList)) {
					line.setC_BPartner_ID(m_BPartner.getC_BPartner_ID());
					line.setC_BPartner_Location_ID(m_CurrentOrder.getC_BPartner_Location_ID());
					line.setPrice();
					line.setTax();
					line.saveEx();
				} else {
					line.deleteEx(true);
				}
			}
		}
	}
	
	/**
	 * Verify if exist in price list
	 * @param p_M_Product_ID
	 * @param p_PPList
	 * @return
	 * @return boolean
	 */
	private boolean existInPriceList(int p_M_Product_ID, MProductPrice[] p_PPList) {
		for(MProductPrice pp : p_PPList) {
			if(p_M_Product_ID == pp.getM_Product_ID()) {
				return true;
			}
		}
		//	Default Return
		return false;
	}
	
	/**
	 * 	Get POSs for specific Sales Rep or all
	 *	@return array of POS
	 */
	public MPOS[] getPOSs (int p_SalesRep_ID) {
		String pass_field = MPOS.COLUMNNAME_SalesRep_ID;
		int pass_ID = p_SalesRep_ID;
		if (p_SalesRep_ID == 100) {
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
	 * @param p_C_Order_ID
	 */
	public void setOrder(int p_C_Order_ID) {
		if (p_C_Order_ID == 0) {
			m_CurrentOrder = null;
		} else {
			m_CurrentOrder = new MOrder(m_ctx , p_C_Order_ID, null);
		}
		//	
		reloadOrder();
	}
	
	/**
	 * Update Line
	 * @param p_C_OrderLine_ID
	 * @param p_QtyOrdered
	 * @param p_PriceEntered
	 * @param p_LineNetAmt
	 * @param p_GrandTotal
	 * @return BigDecimal []
	 */
	public BigDecimal [] updateLine(int p_C_OrderLine_ID, BigDecimal p_QtyOrdered, 
			BigDecimal p_PriceEntered) {
		//	Valid Complete
		if (isCompleted())
			return null;
		//	
		MOrderLine[] lines = m_CurrentOrder.getLines("AND C_OrderLine_ID = " + p_C_OrderLine_ID, "Line");
		BigDecimal m_LineNetAmt = Env.ZERO; 
		BigDecimal m_TaxRate = Env.ZERO;
		BigDecimal m_GrandTotal = Env.ZERO;
		//	Search Line
		for(MOrderLine line : lines) {
			//	Valid No changes
			if(p_QtyOrdered.doubleValue() == line.getQtyOrdered().doubleValue()
					&& p_PriceEntered.doubleValue() == line.getPriceEntered().doubleValue()) {
				return null;
			}
			line.setPrice(p_PriceEntered);
			line.setQty(p_QtyOrdered);
			line.setTax();
			line.saveEx();
			//	Set Values for Grand Total
			m_LineNetAmt = line.getLineNetAmt();
			m_TaxRate = MTax.get(m_ctx, line.getC_Tax_ID()).getRate();
			if(m_TaxRate == null) {
				m_TaxRate = Env.ZERO;
			} else {
				m_TaxRate = m_TaxRate
						.divide(Env.ONEHUNDRED);
			}
			//	Calculate Total
			m_GrandTotal = m_LineNetAmt
						.add(m_LineNetAmt
								.multiply(m_TaxRate));
		}
		//	Return Value
		return new BigDecimal[]{m_LineNetAmt, m_TaxRate, m_GrandTotal};
	}
	
	/**
	 * Create new Line
	 * @return line or null
	 */
	public MOrderLine createLine(MProduct product, BigDecimal QtyOrdered,
			BigDecimal PriceActual) {
		//	Valid Complete
		if (isCompleted())
			return null;
		// catch Exceptions at order.getLines()
		int numLines = 0;
		MOrderLine[] lines = m_CurrentOrder.getLines(null, "Line");
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
	public String add(int p_M_Product_ID, BigDecimal p_QtyOrdered) {
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
					line.deleteEx(true);	
				}
			}
		}
	} //	deleteLine

	/**
	 * Get Data List Order
	 */
	public void listOrder() {
		String sql = new String("SELECT o.C_Order_ID "
					+ "FROM C_Order o "
					+ "WHERE o.IsSOTrx='Y' "
					+ "AND o.Processed = 'N' "
					+ "AND o.AD_Client_ID = ? "
					+ "AND o.C_POS_ID = ? "
					+ "AND o.SalesRep_ID = ? "
					+ "ORDER BY o.Updated");
		PreparedStatement pstm = null;
		ResultSet rs = null;
		m_OrderList = new ArrayList<Integer>();
		try {
			//	Set Parameter
			pstm= DB.prepareStatement(sql, null);
			pstm.setInt (1, Env.getAD_Client_ID(Env.getCtx()));
			pstm.setInt (2, getC_POS_ID());
			pstm.setInt (3, getSalesRep_ID());
			//	Execute
			rs = pstm.executeQuery();
			//	Add to List
			while(rs.next()){
				m_OrderList.add(rs.getInt(1));
			}
		} catch(Exception e) {
			log.severe("SubOrder.listOrder: " + e + " -> " + sql);
		} finally {
			DB.close(rs);
			DB.close(pstm);
		}
		//	Seek Position
		m_RecordPosition = m_OrderList.size();
	}
	
	/**
	 * Verify if is first record in list
	 * @return
	 * @return boolean
	 */
	public boolean isFirstRecord() {
		return m_RecordPosition == 0;
	}
	
	/**
	 * Verify if is last record in list
	 * @return
	 * @return boolean
	 */
	public boolean isLastRecord() {
		return m_RecordPosition == m_OrderList.size() - 1;
	}
	
	/**
	 * Previous Record Order
	 */
	public void previousRecord() {
		if(m_RecordPosition > 0) {
			setOrder(m_OrderList.get(--m_RecordPosition));
		}
	}

	/**
	 * Next Record Order
	 */
	public void nextRecord() {
		if(m_RecordPosition < m_OrderList.size() - 1) {
			setOrder(m_OrderList.get(++m_RecordPosition));
		}
	}
	
	/**
	 * Reload List Index
	 * @param p_C_Order_ID
	 * @return void
	 */
	public void reloadIndex(int p_C_Order_ID) {
		int position = m_OrderList.indexOf(p_C_Order_ID);
		if(position >= 0) {
			m_RecordPosition = position;
		}
	}
	
	/**
	 * 	Process Order
	 * For status "Drafted" or "In Progress": process order
	 * For status "Completed": do nothing as it can be pre payment or payment on credit
	 * 
	 * @return true if order processed or pre payment/on credit; otherwise false
	 * 
	 */
	public boolean processOrder(String trxName, boolean p_IsPrepayment) {		
		//Returning orderCompleted to check for order completeness
		boolean orderCompleted = false;
		// check if order completed OK
		if (!isCompleted()) {
			//	Replace
			if(trxName == null) {
				trxName = m_CurrentOrder.get_TrxName();
			} else {
				m_CurrentOrder.set_TrxName(trxName);
			}
			if(p_IsPrepayment) {		
				m_CurrentOrder.setC_DocTypeTarget_ID(getStandardOrder_ID());
			}
			m_CurrentOrder.setDocAction(DocAction.ACTION_Complete);
			if (m_CurrentOrder.processIt(DocAction.ACTION_Complete) ) {
				m_CurrentOrder.saveEx();
				orderCompleted = true;
			} else {
				log.info( "Process Order FAILED " + m_CurrentOrder.getProcessMsg());		
			}
		} else {
			orderCompleted = isCompleted();
		}
		return orderCompleted;
	}	// processOrder
	
	/**
	 * Get Process Message
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return String
	 */
	public String getProcessMsg() {
		return m_CurrentOrder.getProcessMsg();
	}

	/**
	 * Set Payment Term and save orders
	 * @param p_C_PaymentTerm_ID
	 * @return void
	 */
	public void setC_PaymentTerm_ID(int p_C_PaymentTerm_ID) {
		if(p_C_PaymentTerm_ID != 0
				&& hasOrder()
				&& !isCompleted()
				&& !isVoided()) {
			m_CurrentOrder.setC_PaymentTerm_ID(p_C_PaymentTerm_ID);
		}
	}
	
	/**
	 * Get Payment term from order
	 * @return
	 * @return int
	 */
	public int getC_PaymentTerm_ID() {
		if(hasOrder()) {
			return m_CurrentOrder.getC_PaymentTerm_ID();
		}
		//	Default
		return 0;
	}

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
	public BigDecimal getTotalLines() {
		return m_CurrentOrder.getGrandTotal().subtract(getTaxAmt());
	}
	
	/**
	 * Get Grand Total for current Order
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return BigDecimal
	 */
	public BigDecimal getGrandTotal() {
		return m_CurrentOrder.getGrandTotal();
	}
	
	/**
	 * Get Document No
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return String
	 */
	public String getDocumentNo() {
		return m_CurrentOrder.getDocumentNo();
	}
	
	/**
	 * Get Open Amount
	 * @return
	 * @return BigDecimal
	 */
	public BigDecimal getOpenAmt() {
		BigDecimal received = getPaidAmt();	
		return m_CurrentOrder.getGrandTotal().subtract(received);
	}
	
	/**
	 * Verify if is Paid
	 * @return
	 * @return boolean
	 */
	public boolean isPaid() {
		return getOpenAmt().doubleValue() == 0;
	}
	
	/**
	 * 	Gets Amount Paid from Order
	 * 
	 */
	public BigDecimal getPaidAmt() {
		String sql = "SELECT sum(PayAmt) FROM C_Payment WHERE (C_Invoice_ID = ? OR C_Order_ID = ?) AND DocStatus IN ('CO','CL')";
		BigDecimal received = DB.getSQLValueBD(null, sql, m_CurrentOrder.getC_Invoice_ID(), m_CurrentOrder.getC_Order_ID());
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
	 */
	public void reloadOrder() {
		if (m_CurrentOrder == null)
			return;
		m_CurrentOrder.load(m_CurrentOrder.get_TrxName());
		m_CurrentOrder.getLines(true, "");
		m_BPartner = MBPartner.get(getCtx(), m_CurrentOrder.getC_BPartner_ID());
	}
	
	/**
	 * 	Get M_PriceList_Version_ID.
	 * 	Set Currency
	 *	@return plv
	 */
	public int getM_PriceList_Version_ID() {
		return m_M_PriceList_Version_ID;
	}	//	getM_PriceList_Version_ID
	
	/**
	 * Load Price List Version from Price List
	 * @param p_M_PriceList_ID
	 * @return
	 * @return MPriceListVersion
	 */
	private MPriceListVersion loadPriceListVersion(int p_M_PriceList_ID) {
		m_M_PriceList_Version_ID = 0;
		MPriceList pl = MPriceList.get(m_ctx, p_M_PriceList_ID, null);
		//
		MPriceListVersion plv = pl.getPriceListVersion (getToday());
		if (plv != null 
				&& plv.getM_PriceList_Version_ID() != 0) {
			m_M_PriceList_Version_ID = plv.getM_PriceList_Version_ID();
		}
		//	Default Return
		return plv;
	}
	
	/**
	 * Get Warehouse Identifier
	 * @return
	 * @return int
	 */
	public int getM_Warehouse_ID() {
		return m_POS.getM_Warehouse_ID();
	}
	
	/**
	 * Valid Locator
	 * @return
	 * @return String
	 */
	public void validLocator() {
		MWarehouse warehouse = MWarehouse.get(m_ctx, getM_Warehouse_ID());
		MLocator[] locators = warehouse.getLocators(true);
		for (MLocator mLocator : locators) {
			if (mLocator.isDefault()) {
				return ;
			}
		}

		throw new AdempierePOSException("@M_Locator_ID@ @default@ "
				+ "@not.found@ @M_Warehouse_ID@: " 
				+ warehouse.getName());
	}
	
	/**
	 * Get Warehouse Name
	 * @return
	 * @return String
	 */
	public String getWarehouseName() {
		if(getM_Warehouse_ID() > 0) {
			MWarehouse.get(m_ctx, getM_Warehouse_ID()).getName();
		}
		//	Default
		return "";
	}
	
	/**
	 * Get Document Type Name
	 * @return
	 * @return String
	 */
	public String getDocumentTypeName() {
		if(hasOrder()) {
			MDocType m_DocType = MDocType.get(getCtx(), m_CurrentOrder.getC_DocTypeTarget_ID());
			if(m_DocType != null) {
				return m_DocType.getName();
			}
		}
		//	Default None
		return "";
	}
	
	/**
	 * Get Currency Symbol
	 * @return
	 * @return String
	 */
	public String getCurSymbol() {
		int m_C_Currency_ID = getC_Currency_ID();
		if(m_C_Currency_ID > 0) {
			MCurrency m_Currency = MCurrency.get(getCtx(), m_C_Currency_ID);
			if(m_Currency != null) {
				return m_Currency.getCurSymbol();
			}
		}
		//	Default
		return "";
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
			String whereClause = "C_BPartner_ID = ? "
					+ "AND IsPaid ='N' "
					+ "AND EXISTS(SELECT 1 "
					+ "				FROM C_DocType dt "
					+ "				WHERE dt.C_DocType_ID = C_Invoice.C_DocType_ID "
					+ "				AND dt.DocBaseType ='ARC'"
					+ ")";
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
	 * Verify if can modify price
	 * @return
	 * @return boolean
	 */
	public boolean isModifyPrice() {
		return m_POS.isModifyPrice();
	}
	
	/**
	 * Get Order Identifier
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return int
	 */
	public int getC_Order_ID() {
		int m_C_Order_ID = 0;
		if(hasOrder()) {
			m_C_Order_ID = m_CurrentOrder.getC_Order_ID();
		}
		//	Default
		return m_C_Order_ID;
	}
	
	/**
	 * Get Standard Order ID
	 * @return int
	 */
	public int getStandardOrder_ID() {
		StringBuffer whereClause = new StringBuffer();
		whereClause.append(MDocType.COLUMNNAME_DocBaseType + "=?");
		whereClause.append(" AND " + MDocType.COLUMNNAME_DocSubTypeSO + "=?");
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(MDocType.DOCBASETYPE_SalesOrder);
		params.add(MDocType.DOCSUBTYPESO_StandardOrder);

		int C_DocType_ID = new Query(m_ctx, MDocType.Table_Name, 
				whereClause.toString(), 
				m_CurrentOrder.get_TrxName())
					.setParameters(params)	
					.setClient_ID()
					.setOnlyActiveRecords(true)
					.firstId();
		return C_DocType_ID;
	}
}