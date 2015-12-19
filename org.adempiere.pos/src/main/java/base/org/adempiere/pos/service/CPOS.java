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
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.pos.AdempierePOSException;
import org.adempiere.util.ProcessUtil;
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
import org.compiere.model.MPInstance;
import org.compiere.model.MPInstancePara;
import org.compiere.model.MPOS;
import org.compiere.model.MPayment;
import org.compiere.model.MPaymentProcessor;
import org.compiere.model.MPriceList;
import org.compiere.model.MPriceListVersion;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPrice;
import org.compiere.model.MSequence;
import org.compiere.model.MTax;
import org.compiere.model.MUser;
import org.compiere.model.MWarehouse;
import org.compiere.model.MWarehousePrice;
import org.compiere.model.Query;
import org.compiere.model.X_C_Order;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;
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
		ctx = Env.getCtx();
	}
	
	/**	POS Configuration		*/
	private MPOS 				entityPOS;
	/**	Current Order			*/
	private MOrder 				currentOrder;
	/** Sequence Doc 			*/
	private MSequence 			documentSequence;
	/**	The Business Partner	*/
	private MBPartner 			partner;
	/**	Price List Version		*/
	private int 				priceListVersionId;
	/** Context					*/
	protected Properties 		ctx = Env.getCtx();
	/**	Today's (login) date	*/
	private Timestamp 			today = Env.getContextAsDate(ctx, "#Date");
	/**	Order List				*/
	private ArrayList<Integer>  orderList;
	/**	Order List Position		*/
	private int 				recordPosition;
	/**	Is Payment Completed	*/
	private boolean 			isToPrint;
	/**	Logger					*/
	private CLogger 			log = CLogger.getCLogger(getClass());
	/**	Quantity Ordered		*/
	private BigDecimal 			quantity = BigDecimal.ZERO;
	/**	Price					*/
	private BigDecimal 			price = BigDecimal.ZERO;
	
	
	/**
	 * 	Set MPOS
	 * @param p_SalesRep_ID
	 * @return true if found/set
	 */
	public void setPOS(int p_SalesRep_ID) {
		//List<MPOS> poss = getPOSs(p_SalesRep_ID);
		List<MPOS> poss = getPOSByOrganization(Env.getAD_Org_ID(getCtx()));
		//
		if (poss.size() == 0) {
			throw new AdempierePOSException("@NoPOSForUser@");
		} else if (poss.size() == 1) {
			entityPOS = poss.get(0);
		}
	}	//	setMPOS
	
	/**
	 * Set POS
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @param pos
	 * @return void
	 */
	public void setM_POS(MPOS pos) {
		entityPOS = pos;
	}
	
	/**
	 * Validate if is Order Completed
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return boolean
	 */
	public boolean isCompleted() {
		if(!hasOrder()) {
			return false;
		}
		//	
		return currentOrder.isProcessed()
				&& currentOrder.getDocStatus()
				.equals(X_C_Order.DOCSTATUS_Completed);
	}
	
	/**
	 * Get Sequence
	 * @return
	 * @return int
	 */
	public int getAD_Sequence_ID() {
		return entityPOS.getAD_Sequence_ID();
	}
	
	/**
	 * Get Organization
	 * @return
	 * @return int
	 */
	public int getAD_Org_ID() {
		return entityPOS.getAD_Org_ID();
	}
	
	/**
	 * Validate if is voided
	 * @return
	 * @return boolean
	 */
	public boolean isVoided() {
		if(!hasOrder()) {
			return false;
		}
		//	
		return currentOrder.getDocStatus()
				.equals(X_C_Order.DOCSTATUS_Voided);
	}
	
	/**
	 * Validate if is drafted
	 * @return
	 * @return boolean
	 */
	public boolean isDrafted() {
		if(!hasOrder()) {
			return false;
		}
		//	
		return !isCompleted() 
				&& !isVoided() 
				&& currentOrder.getDocStatus()
				.equals(X_C_Order.DOCSTATUS_Drafted);
	}
	
	/**
	 * Validate if has lines
	 * @return
	 * @return boolean
	 */
	public boolean hasLines() {
		if(!hasOrder()) {
			return false;
		}
		//	
		return currentOrder.getLines().length > 0;
	}
	
	/**
	 * Validate if is POS Order
	 * @return
	 * @return boolean
	 */
	public boolean isPOSOrder() {
		if(!hasOrder()) {
			return false;
		}
		//	
		return getDocSubTypeSO()
				.equals(MOrder.DocSubTypeSO_POS);
	}
	
	/**
	 * Validate if is Credit Order
	 * @return
	 * @return boolean
	 */
	public boolean isCreditOrder() {
		if(!hasOrder()) {
			return false;
		}
		//	
		return getDocSubTypeSO()
				.equals(MOrder.DocSubTypeSO_OnCredit);
	}
	
	/**
	 * Validate if is Standard Order
	 * @return
	 * @return boolean
	 */
	public boolean isStandardOrder() {
		if(!hasOrder()) {
			return false;
		}
		//	
		return getDocSubTypeSO()
				.equals(MOrder.DocSubTypeSO_Standard);
	}
	
	/**
	 * Validate if is Prepay Order
	 * @return
	 * @return boolean
	 */
	public boolean isPrepayOrder() {
		if(!hasOrder()) {
			return false;
		}
		//	
		return getDocSubTypeSO()
				.equals(MOrder.DocSubTypeSO_Prepay);
	}
	
	/**
	 * Validate if is Standard Order
	 * @return
	 * @return boolean
	 */
	public boolean isWarehouseOrder() {
		if(!hasOrder()) {
			return false;
		}
		//	
		return getDocSubTypeSO()
				.equals(MOrder.DocSubTypeSO_Warehouse);
	}
	
	/**
	 * Valid date if is invoiced
	 * @return
	 * @return boolean
	 */
	public boolean isInvoiced() {
		if(!hasOrder()) {
			return false;
		}
		//	
		return currentOrder.isInvoiced();
	}
	
	/**
	 * Validate if is delivered
	 * @return
	 * @return boolean
	 */
	public boolean isDelivered() {
		if(!hasOrder()) {
			return false;
		}
		//	
		return currentOrder.isDelivered();
	}
	
	/**
	 * Get Document Sub Type SO
	 * @return
	 * @return String
	 */
	private String getDocSubTypeSO() {
		//	
		MDocType m_DocType = MDocType.get(getCtx(), getC_DocType_ID());
		if(m_DocType != null) {
			if(m_DocType.getDocSubTypeSO() != null) {
				return m_DocType.getDocSubTypeSO();
			}
		}
		//	
		return "";
	}
	
	/**
	 * Get Document Type from Order
	 * @return
	 * @return int
	 */
	public int getC_DocType_ID() {
		if(!hasOrder()) {
			return 0;
		}
		//	
		if(isCompleted()
				|| isVoided()) {
			return currentOrder.getC_DocType_ID();
		} else {
			return currentOrder.getC_DocTypeTarget_ID();
		}
	}
	
	/**
	 * Validate if is to print invoice
	 * @return
	 * @return boolean
	 */
	public boolean isToPrint() {
		return isToPrint;
	}
	
	/**
	 * Get Current Order
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return MOrder
	 */
	public MOrder getM_Order() {
		return currentOrder;
	}
	
	/**
	 * Has Order
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return boolean
	 */
	public boolean hasOrder() {
		return currentOrder != null
				&& currentOrder.getC_Order_ID() != 0;
	}
	
	/**
	 * Has Business Partner
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return boolean
	 */
	public boolean hasBPartner() {
		return partner != null;
	}
	
	/**
	 * Compare BP Name
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @param p_Name
	 * @return
	 * @return boolean
	 */
	public boolean compareBPName(String p_Name) {
		return partner.getName().equals(p_Name);
	}
	
	/**
	 * 	Get BPartner
	 *	@return C_BPartner_ID
	 */
	public int getC_BPartner_ID () {
		if (hasBPartner())
			return partner.getC_BPartner_ID();
		return 0;
	}	//	getC_BPartner_ID
	
	
	/**
	 * Get Business Partner Name
	 * @return
	 * @return String
	 */
	public String getBPName() {
		if (hasBPartner())
			return partner.getName();
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
			return currentOrder.getC_Currency_ID();
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
		return entityPOS.getAutoLogoutDelay();
	}
	
	/**
	 * Get Sales Rep. Name
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return String
	 */
	public String getSalesRepName() {
		MUser salesrep = MUser.get(ctx);
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
		return entityPOS.getSalesRep_ID();
	}
	
	/**
	 * Get POS Configuration
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return MPOS
	 */
	public MPOS getM_POS() {
		return entityPOS;
	}
	
	/**
	 * Get POS Name
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return String
	 */
	public String getPOSName() {
		return entityPOS.getName();
	}
	
	/**
	 * Get POS Identifier
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return int
	 */
	public int getC_POS_ID() {
		return entityPOS.getC_POS_ID();
	}
	
	/**
	 * 	New Order
	 *  @param p_C_BPartner_ID
	 */
	public void newOrder(int p_C_BPartner_ID) {
		log.info( "PosPanel.newOrder");
		currentOrder = null;
		int m_C_DocType_ID = entityPOS.getC_DocType_ID();
		//	Create Order
		createOrder(p_C_BPartner_ID, m_C_DocType_ID);
		//	
		reloadOrder();
	}	//	newOrder
	
	/**
	 * Set Custom Document Type
	 * @param p_C_DocTypeTarget_ID
	 * @return void
	 */
	public void setC_DocType_ID(int p_C_DocTypeTarget_ID) {
		//	Valid if has a Order
		if(!isDrafted())
			return;
		//	Set Document Type
		currentOrder.setC_DocTypeTarget_ID(p_C_DocTypeTarget_ID);
		//	Set Sequenced No
		String value = DB.getDocumentNo(getC_DocType_ID(), null, false, currentOrder);
		if (value != null) {
			currentOrder.setDocumentNo(value);
		}
		currentOrder.saveEx();
	}
	
	
	/**
	 * Get/create Order
	 *	@param p_C_BPartner_ID Business Partner
	 *	@param p_C_DocTypeTarget_ID ID of document type
	 */
	private void createOrder(int p_C_BPartner_ID, int p_C_DocTypeTarget_ID) {
		int m_Free_C_Order_ID = getFreeC_Order_ID();
		//	Change Values for new Order
		if(m_Free_C_Order_ID > 0) {
			currentOrder = new MOrder(Env.getCtx(), m_Free_C_Order_ID, null);
			currentOrder.setDateOrdered(getToday());
			currentOrder.setDateAcct(getToday());
			currentOrder.setDatePromised(getToday());
		} else {
			currentOrder = new MOrder(Env.getCtx(), 0, null);
		}
		currentOrder.setAD_Org_ID(entityPOS.getAD_Org_ID());
		currentOrder.setIsSOTrx(true);
		currentOrder.setC_POS_ID(entityPOS.getC_POS_ID());
		currentOrder.setM_Warehouse_ID(entityPOS.getM_Warehouse_ID());
		if (p_C_DocTypeTarget_ID != 0) {
			currentOrder.setC_DocTypeTarget_ID(p_C_DocTypeTarget_ID);
		} else {
			currentOrder.setC_DocTypeTarget_ID(MOrder.DocSubTypeSO_OnCredit);
		}
		//	Set BPartner
		setC_BPartner_ID(p_C_BPartner_ID);
		//	Add if is new
		if(m_Free_C_Order_ID < 0) {
			//	Add To List
			orderList.add(currentOrder.getC_Order_ID());
		}
		//  Add record
		reloadIndex(currentOrder.getC_Order_ID());
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
	 * Is BPartner Standard 
	 * @return boolean
	 */
	public boolean isBPartnerStandard() {
		int partnerId = currentOrder != null ? currentOrder.getC_BPartner_ID() : 0 ;
		if(entityPOS.getC_BPartnerCashTrx_ID() == partnerId)
			return true;
		else
			return false;
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
		if(isCompleted()
				|| isVoided())
			return;
		log.fine( "CPOS.setC_BPartner_ID=" + p_C_BPartner_ID);
		boolean isSamePOSPartner = false;
		//	Validate BPartner
		if (p_C_BPartner_ID == 0) {
			isSamePOSPartner = true;
			p_C_BPartner_ID = entityPOS.getC_BPartnerCashTrx_ID();
		}
		//	Get BPartner
		partner = MBPartner.get(ctx, p_C_BPartner_ID);
		if (partner == null || partner.get_ID() == 0) {
			throw new AdempierePOSException("POS.NoBPartnerForOrder");
		} else {
			log.info("CPOS.SetC_BPartner_ID -" + partner);
			currentOrder.setBPartner(partner);
			//	
			if (partner != null) {
				currentOrder.setBPartner(partner);
				//	
				MBPartnerLocation [] m_BPLocations = partner.getLocations(true);
				if(m_BPLocations.length > 0) {
					for(MBPartnerLocation loc : m_BPLocations) {
						if(loc.isBillTo())
							currentOrder.setBill_Location_ID(loc.getC_BPartner_Location_ID());
						if(loc.isShipTo())
							currentOrder.setShip_Location_ID(loc.getC_BPartner_Location_ID());
					}				
				}
			}
			//	Validate Same BPartner
			if(isSamePOSPartner) {
				currentOrder.setM_PriceList_ID(entityPOS.getM_PriceList_ID());
				currentOrder.setPaymentRule(MOrder.PAYMENTRULE_Cash);
			}
			//	Set Sales Representative
			currentOrder.setSalesRep_ID(entityPOS.getSalesRep_ID());
			//	Save Header
			currentOrder.saveEx();
			//	Load Price List Version
			MPriceListVersion plv = loadPriceListVersion(currentOrder.getM_PriceList_ID());
			MProductPrice[] ppList = plv.getProductPrice("AND EXISTS("
					+ "SELECT 1 "
					+ "FROM C_OrderLine ol "
					+ "WHERE ol.C_Order_ID = " + currentOrder.getC_Order_ID() + " "
					+ "AND ol.M_Product_ID = M_ProductPrice.M_Product_ID)");
			//	Update Lines
			MOrderLine[] lines = currentOrder.getLines();
			//	Delete if not exist in price list
			for (MOrderLine line : lines) {
				//	Verify if exist
				if(existInPriceList(line.getM_Product_ID(), ppList)) {
					line.setC_BPartner_ID(partner.getC_BPartner_ID());
					line.setC_BPartner_Location_ID(currentOrder.getC_BPartner_Location_ID());
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
	public List<MPOS> getPOSs (int p_SalesRep_ID) {
		String pass_field = MPOS.COLUMNNAME_SalesRep_ID;
		int pass_ID = p_SalesRep_ID;
		if (p_SalesRep_ID == 100) {
			pass_field = MPOS.COLUMNNAME_AD_Client_ID;
			pass_ID = Env.getAD_Client_ID(ctx);
		}
		return MPOS.getAll(ctx, pass_field, pass_ID , null);
	}	//	getPOSs


	/**
	 * 	Get POSs for specific Sales Rep or all
	 *	@return array of POS
	 */
	public List<MPOS> getPOSByOrganization (int orgId) {
		return MPOS.getByOrganization(ctx, orgId, null);
	}

	/**************************************************************************
	 * 	Get Today's date
	 *	@return date
	 */
	public Timestamp getToday() {
		return today;
	}	//	getToday
	
	/**
	 * @param p_C_Order_ID
	 */
	public void setOrder(int p_C_Order_ID) {
		currentOrder = new MOrder(ctx, p_C_Order_ID, null);
		if (p_C_Order_ID != 0) {
			loadPriceListVersion(currentOrder.getM_PriceList_ID());
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
		//	Valid if has a Order
		if(!isDrafted())
			return null;
		//	
		MOrderLine[] lines = currentOrder.getLines("AND C_OrderLine_ID = " + p_C_OrderLine_ID, "Line");
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
			m_TaxRate = MTax.get(ctx, line.getC_Tax_ID()).getRate();
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
	public MOrderLine createLine(MProduct product, BigDecimal p_QtyOrdered,
			BigDecimal p_PriceActual) {
		//	Valid Complete
		if (!isDrafted())
			return null;
		// catch Exceptions at order.getLines()
		MOrderLine[] lines = currentOrder.getLines(true, "Line");
		for (MOrderLine line : lines) {
			if (line.getM_Product_ID() == product.getM_Product_ID()) {
				//increase qty
				BigDecimal m_CurrentQty = line.getQtyEntered();
				BigDecimal m_CurrentPrice = line.getPriceEntered();
				BigDecimal m_TotalQty = m_CurrentQty.add(p_QtyOrdered);
				line.setQty(m_TotalQty);
				line.setPrice(m_CurrentPrice); //	sets List/limit
				line.saveEx();
				return line;
			}
		}
        //create new line
		MOrderLine line = new MOrderLine(currentOrder);
		line.setProduct(product);
		line.setQty(p_QtyOrdered);
		//	
		line.setPrice(); //	sets List/limit
		if ( p_PriceActual.compareTo(Env.ZERO) > 0 ) {
			line.setPrice(p_PriceActual);
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
				priceListVersionId, entityPOS.getM_Warehouse_ID(), null);
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
			MProduct m_Product = MProduct.get(ctx, p_M_Product_ID);
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
	 * 	Call Order void process 
	 *  Only if Order is "Drafted", "In Progress" or "Completed"
	 * 
	 *  @return true if order voided; false otherwise
	 */
	private boolean voidOrder() {
		if (!(currentOrder.getDocStatus().equals(MOrder.STATUS_Drafted)
				|| currentOrder.getDocStatus().equals(DocAction.STATUS_InProgress)
				|| currentOrder.getDocStatus().equals(DocAction.STATUS_Completed)))
			return false;
		
		// Standard way of voiding an order
		currentOrder.setDocAction(MOrder.DOCACTION_Void);
		if (currentOrder.processIt(MOrder.DOCACTION_Void) ) {
			currentOrder.setDocAction(MOrder.DOCACTION_None);
			currentOrder.setDocStatus(MOrder.STATUS_Voided);
			currentOrder.saveEx();
			return true;
		} else {
			return false;
		}
	} // cancelOrder
	
	/**
	 * Execute deleting an order
	 * If the order is in drafted status -> ask to delete it
	 * If the order is in completed status -> ask to void it it
	 * Otherwise, it must be done outside this class.
	 */
	public String cancelOrder() {
		String errorMsg = null;
		try {
			//	Get Index
			int currentIndex = orderList.indexOf(currentOrder.getC_Order_ID());
			if (!hasOrder()) {
				throw new AdempierePOSException("@POS.MustCreateOrder@");
			} else if (!isCompleted()) {
				//	Delete Order
				currentOrder.deleteEx(true);
			} else if (isCompleted()) {	
				voidOrder();
			} else {
				throw new AdempierePOSException("@POS.OrderIsNotProcessed@");
			}
			//	Remove from List
			if(currentIndex >= 0) {
				orderList.remove(currentIndex);
			}
			//	
			currentOrder = null;
			//	Change to Next
			if(hasRecord()){
				if(isFirstRecord()) {
					firstRecord();
				} else if(isLastRecord()) {
					lastRecord();
				} else {
					previousRecord();
				}
			}
		} catch(Exception e) {
			errorMsg = e.getMessage();
		}
		//	Default Return
		return errorMsg;
	} // cancelOrder
	
	/** 
	 * Delete one order line
	 * To erase one line from order
	 * 
	 */
	public void deleteLine (int C_OrderLine_ID) {
		if ( C_OrderLine_ID != -1 && currentOrder != null ) {
			for ( MOrderLine line : currentOrder.getLines(true, I_C_OrderLine.COLUMNNAME_M_Product_ID) ) {
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
		orderList = new ArrayList<Integer>();
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
				orderList.add(rs.getInt(1));
			}
		} catch(Exception e) {
			log.severe("SubOrder.listOrder: " + e + " -> " + sql);
		} finally {
			DB.close(rs);
			DB.close(pstm);
		}
		//	Seek Position
		if(hasRecord())
			recordPosition = orderList.size() -1;
		else 
			recordPosition = -1;
	}
	
	/**
	 * Verify if has record in list
	 * @return
	 * @return boolean
	 */
	public boolean hasRecord(){
		return !orderList.isEmpty();
	}
	
	/**
	 * Verify if is first record in list
	 * @return
	 * @return boolean
	 */
	public boolean isFirstRecord() {
		return recordPosition == 0;
	}
	
	/**
	 * Verify if is last record in list
	 * @return
	 * @return boolean
	 */
	public boolean isLastRecord() {
		return recordPosition == orderList.size() - 1;
	}
	
	/**
	 * Previous Record Order
	 */
	public void previousRecord() {
		if(recordPosition > 0) {
			setOrder(orderList.get(--recordPosition));
		}
	}

	/**
	 * Next Record Order
	 */
	public void nextRecord() {
		if(recordPosition < orderList.size() - 1) {
			setOrder(orderList.get(++recordPosition));
		}
	}
	
	/**
	 * Reload List Index
	 * @param p_C_Order_ID
	 * @return void
	 */
	public void reloadIndex(int p_C_Order_ID) {
		int position = orderList.indexOf(p_C_Order_ID);
		if(position >= 0) {
			recordPosition = position;
		}
	}
	
	/**
	 * Seek to last record
	 * @return void
	 */
	public void lastRecord() {
		recordPosition = orderList.size();
		if(recordPosition != 0) {
			--recordPosition;
		}
	}
	
	/**
	 * Seek to first record
	 * @return void
	 */
	public void firstRecord() {
		recordPosition = orderList.size();
		if(recordPosition != 0) {
			recordPosition = 0;
		}
	}
	
	/**
	 * Process Order
	 * For status "Drafted" or "In Progress": process order
	 * For status "Completed": do nothing as it can be pre payment or payment on credit
	 * @param trxName
	 * @param p_IsPrepayment
	 * @param p_IsPaid
	 * @return true if order processed or pre payment/on credit; otherwise false
	 * 
	 */
	public boolean processOrder(String trxName, boolean p_IsPrepayment, boolean p_IsPaid) {		
		//Returning orderCompleted to check for order completeness
		boolean orderCompleted = false;
		// check if order completed OK
		if (!isCompleted()) {	//	Complete Order
			//	Replace
			if(trxName == null) {
				trxName = currentOrder.get_TrxName();
			} else {
				currentOrder.set_TrxName(trxName);
			}
			isToPrint = true;
			//	Get value for Standard Order
			if(p_IsPrepayment) {
				//	Set Document Type
				currentOrder.setC_DocTypeTarget_ID(MOrder.DocSubTypeSO_Standard);
				isToPrint = false;
			}
			
			//	Force Delivery for POS not for Standard Order
			if(!currentOrder.getC_DocTypeTarget().getDocSubTypeSO()
				.equals(MOrder.DocSubTypeSO_Standard)) {				
				currentOrder.setDeliveryRule(X_C_Order.DELIVERYRULE_Force);
				currentOrder.setInvoiceRule(X_C_Order.INVOICERULE_AfterDelivery);
			}
				
			currentOrder.setDocAction(DocAction.ACTION_Complete);
			if (currentOrder.processIt(DocAction.ACTION_Complete) ) {
				currentOrder.saveEx();
				orderCompleted = true;
			} else {
				log.info( "Process Order FAILED " + currentOrder.getProcessMsg());
			}
		} else {	//	Default nothing
			orderCompleted = isCompleted();
			isToPrint = false;
		}
		
		//	Validate for generate Invoice and Shipment
		if(p_IsPaid
				&& !isInvoiced()
				&& !isDelivered()) {	//	Generate Invoice and Shipment
			generateShipment(trxName);
			generateInvoice(trxName);
			//	
			orderCompleted = true;
			isToPrint = true;
		}
		return orderCompleted;
	}	// processOrder
	
	/**
	 * Generate Shipment
	 * @param trxName
	 * @return void
	 */
	private void generateShipment(String trxName) {
		int AD_Process_ID = 199;  // HARDCODED    M_InOut_Generate - org.compiere.process.InOutGenerate
		MPInstance instance = new MPInstance(Env.getCtx(), AD_Process_ID, 0);
		if (!instance.save()) {
			throw new AdempiereException("ProcessNoInstance");
		}
		//	Insert Values
		DB.executeUpdateEx("INSERT INTO T_SELECTION(AD_PINSTANCE_ID, T_SELECTION_ID) Values(?, ?)", 
				new Object[]{instance.getAD_PInstance_ID(), getC_Order_ID()}, trxName);
		//	Add Lines
		ProcessInfo pi = new ProcessInfo ("VInOutGen", AD_Process_ID);
		pi.setAD_PInstance_ID (instance.getAD_PInstance_ID());
		pi.setClassName("org.compiere.process.InOutGenerate");

		//	Add Is Selection
		MPInstancePara para = new MPInstancePara(instance, 10);
		para.setParameter("Selection", "Y");
		if (!para.save()) {
			String msg = "No Selection Parameter added";  //  not translated
			log.log(Level.SEVERE, msg);
			throw new AdempiereException(msg);
		}
		//	Add Warehouse
		para = new MPInstancePara(instance, 20);
		para.setParameter("M_Warehouse_ID", getM_Warehouse_ID());
		if (!para.save()) {
			String msg = "No Selection Parameter added";  //  not translated
			log.log(Level.SEVERE, msg);
			throw new AdempiereException(msg);
		}
		//	Create Trx
		Trx trx = Trx.get(trxName, false);
		//	Start Process
		ProcessUtil.startJavaProcess(Env.getCtx(), pi, trx, false);
		if(pi.isError()) {
			throw new AdempiereException(pi.getSummary());
		}
	}
	
	/**
	 * Generate Invoice
	 * @param trxName
	 * @return void
	 */
	private void generateInvoice(String trxName) {
		int AD_Process_ID = 134;  // HARDCODED    C_InvoiceCreate - org.compiere.process.InvoiceGenerate
		MPInstance instance = new MPInstance(Env.getCtx(), AD_Process_ID, 0);
		if (!instance.save()) {
			throw new AdempiereException("ProcessNoInstance");
		}
		//	Insert Values
		DB.executeUpdateEx("INSERT INTO T_SELECTION(AD_PINSTANCE_ID, T_SELECTION_ID) Values(?, ?)", 
				new Object[]{instance.getAD_PInstance_ID(), getC_Order_ID()}, trxName);
		//	Add Lines
		ProcessInfo pi = new ProcessInfo ("", AD_Process_ID);
		pi.setAD_PInstance_ID (instance.getAD_PInstance_ID());
		pi.setClassName("org.compiere.process.InvoiceGenerate");

		//	Add Is Selection
		MPInstancePara para = new MPInstancePara(instance, 10);
		para.setParameter("Selection", "Y");
		if (!para.save()) {
			String msg = "No Selection Parameter added";  //  not translated
			log.log(Level.SEVERE, msg);
			throw new AdempiereException(msg);
		}
		//	For Document Action
		para = new MPInstancePara(instance, 20);
		para.setParameter("DocAction", "CO");
		if (!para.save())
		{
			String msg = "No DocAction Parameter added";  //  not translated
			log.log(Level.SEVERE, msg);
			throw new AdempiereException(msg);
		}
		//	Create Trx
		Trx trx = Trx.get(trxName, false);
		//	Start Process
		ProcessUtil.startJavaProcess(Env.getCtx(), pi, trx, false);
		if(pi.isError()) {
			throw new AdempiereException(pi.getSummary());
		}
	}
	
	/**
	 * Get Process Message
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return String
	 */
	public String getProcessMsg() {
		return currentOrder.getProcessMsg();
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
			currentOrder.setC_PaymentTerm_ID(p_C_PaymentTerm_ID);
		}
	}
	
	/**
	 * Get Payment term from order
	 * @return
	 * @return int
	 */
	public int getC_PaymentTerm_ID() {
		if(hasOrder()) {
			return currentOrder.getC_PaymentTerm_ID();
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
		for (MOrderTax tax : currentOrder.getTaxes(true)) {
			taxAmt = taxAmt.add(tax.getTaxAmt());
		}
		return taxAmt;
	}
	
	/**
	 * 	Gets Subtotal from Order
	 * 
	 */
	public BigDecimal getTotalLines() {
		return currentOrder.getGrandTotal().subtract(getTaxAmt());
	}
	
	/**
	 * Get Grand Total for current Order
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return BigDecimal
	 */
	public BigDecimal getGrandTotal() {
		return currentOrder.getGrandTotal();
	}
	
	/**
	 * Get Document No
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return String
	 */
	public String getDocumentNo() {
		return currentOrder.getDocumentNo();
	}
	
	/**
	 * Get Open Amount
	 * @return
	 * @return BigDecimal
	 */
	public BigDecimal getOpenAmt() {
		BigDecimal received = getPaidAmt();	
		return currentOrder.getGrandTotal().subtract(received);
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
		BigDecimal received = DB.getSQLValueBD(null, sql, currentOrder.getC_Invoice_ID(), currentOrder.getC_Order_ID());
		if ( received == null )
			received = Env.ZERO;
		
		sql = "SELECT sum(Amount) FROM C_CashLine WHERE C_Invoice_ID = ? ";
		BigDecimal cashline = DB.getSQLValueBD(null, sql, currentOrder.getC_Invoice_ID());
		if ( cashline != null )
			received = received.add(cashline);
		
		return received;
	}
	
	/**
	 * 	Load Order
	 */
	public void reloadOrder() {
		if (currentOrder == null) {
			
			if(recordPosition != -1
					&& recordPosition < orderList.size()) {
				setOrder(orderList.get(recordPosition));
			}
			//	
			return;
		}
		currentOrder.load(currentOrder.get_TrxName());
		currentOrder.getLines(true, "");
		partner = MBPartner.get(getCtx(), currentOrder.getC_BPartner_ID());
	}
	
	/**
	 * 	Get M_PriceList_Version_ID.
	 * 	Set Currency
	 *	@return plv
	 */
	public int getM_PriceList_Version_ID() {
		return priceListVersionId;
	}	//	getM_PriceList_Version_ID
	
	/**
	 * Load Price List Version from Price List
	 * @param p_M_PriceList_ID
	 * @return
	 * @return MPriceListVersion
	 */
	private MPriceListVersion loadPriceListVersion(int p_M_PriceList_ID) {
		priceListVersionId = 0;
		MPriceList pl = MPriceList.get(ctx, p_M_PriceList_ID, null);
		//
		MPriceListVersion plv = pl.getPriceListVersion (getToday());
		if (plv != null 
				&& plv.getM_PriceList_Version_ID() != 0) {
			priceListVersionId = plv.getM_PriceList_Version_ID();
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
		return entityPOS.getM_Warehouse_ID();
	}
	
	/**
	 * Valid Locator
	 * @return
	 * @return String
	 */
	public void validLocator() {
		MWarehouse warehouse = MWarehouse.get(ctx, getM_Warehouse_ID());
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
			MWarehouse.get(ctx, getM_Warehouse_ID()).getName();
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
			MDocType m_DocType = MDocType.get(getCtx(), currentOrder.getC_DocTypeTarget_ID());
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
					currentOrder.getAD_Client_ID (), currentOrder.getAD_Org_ID(), currentOrder.getC_Currency_ID (), amt, currentOrder.get_TrxName());
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
				.setParameters(currentOrder.getC_BPartner_ID())
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
	 * Get Context
	 * @author <a href="mailto:yamelsenih@gmail.com">Yamel Senih</a> Aug 31, 2015, 8:23:54 PM
	 * @return
	 * @return Properties
	 */
	public Properties getCtx() {
		return ctx;
	}
	
	/**
	 * Get POS Key Layout Identifier
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return int
	 */
	public int getOSKeyLayout_ID() {
		if(entityPOS != null) {
			return entityPOS.getOSK_KeyLayout_ID();
		}
		//	Default Return
		return 0;
	}
	
	/**
	 * Get Key Layout
	 * @return
	 * @return int
	 */
	public int getC_POSKeyLayout_ID() {
		if(entityPOS != null) {
			return entityPOS.getC_POSKeyLayout_ID();
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
		return entityPOS.isModifyPrice();
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
			m_C_Order_ID = currentOrder.getC_Order_ID();
		}
		//	Default
		return m_C_Order_ID;
	}
	
	/**
	 * Save Current Next Sequence
	 * @param trxName
	 * @return void
	 */
	public void saveNextSeq(String trxName){
		int next = documentSequence.getCurrentNext() + documentSequence.getIncrementNo();
		documentSequence.setCurrentNext(next);
		documentSequence.saveEx(trxName);
	}
	
	/**
	 * Get Sequence Document
	 * @param trxName
	 * @return String
	 */
	public String getSequenceDoc(String trxName){
		documentSequence = new MSequence(Env.getCtx(), entityPOS.getAD_Sequence_ID(), trxName);
		return documentSequence.getPrefix() + documentSequence.getCurrentNext();
	}
	
	/**
	 * Set Purchase Order Reference 
	 * @param documentNo
	 * @return void
	 */
	public void setPOReference(String documentNo) {
		String trxName = currentOrder.get_TrxName();
		Trx trx = Trx.get(trxName, true);
		currentOrder.setPOReference(documentNo);
		currentOrder.saveEx(trx.getTrxName());
		trx.close();
		
	}

	/**
	 * Get Quantity of Product
	 * @return quantity
	 */
	public BigDecimal getQty() {
		return quantity;
	}

	/**
	 * Set Quantity of Product
	 * @param m_qty
	 */
	public void setQuantity(BigDecimal qty) {
		this.quantity = qty;
	}

	/**
	 * Get Price of Product
	 * @return price
	 */
	public BigDecimal getPrice() {
		return this.price;
	}

	/**
	 * Set Price of Product
	 * @param price
	 */
	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}
	
	public boolean IsShowLineControl() {
		return true;
	}

	/**
	 * get if POS using a virtual Keyboard
	 * @return
	 */
	public boolean isVirtualKeyboard()
	{
		if (getOSKeyLayout_ID() > 0)
			return true;

		return false;
	}
}