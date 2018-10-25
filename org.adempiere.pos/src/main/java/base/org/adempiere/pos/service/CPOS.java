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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Vector;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.pos.AdempierePOSException;
import org.adempiere.pos.command.CommandManager;
import org.adempiere.pos.util.POSTicketHandler;
import org.compiere.model.I_AD_User;
import org.compiere.model.I_C_OrderLine;
import org.compiere.model.MAllocationHdr;
import org.compiere.model.MAllocationLine;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MCashLine;
import org.compiere.model.MCurrency;
import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutConfirm;
import org.compiere.model.MInOutLineConfirm;
import org.compiere.model.MInvoice;
import org.compiere.model.MLocator;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MOrderTax;
import org.compiere.model.MPOS;
import org.compiere.model.MPOSKey;
import org.compiere.model.MPayment;
import org.compiere.model.MPaymentProcessor;
import org.compiere.model.MPriceList;
import org.compiere.model.MPriceListVersion;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPrice;
import org.compiere.model.MProductPricing;
import org.compiere.model.MSequence;
import org.compiere.model.MTax;
import org.compiere.model.MUser;
import org.compiere.model.MWarehouse;
import org.compiere.model.Query;
import org.compiere.model.X_C_Order;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.compiere.util.ValueNamePair;
import org.eevolution.service.dsl.ProcessBuilder;

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
		decimalFormat = DisplayType.getNumberFormat(DisplayType.Amount);
		dateFormat = DisplayType.getDateFormat(DisplayType.Date);
		today = Env.getContextAsDate(ctx, "#Date");
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
	/**	Price List Id			*/
	private int 				priceListId;
	/** Context					*/
	private Properties 			ctx;
	/**	Today's (login) date	*/
	private Timestamp 			today;
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
	/**	Quantity Ordered		*/
	private BigDecimal 			quantityAdded = BigDecimal.ZERO;
	/** Price Limit 			*/
	private BigDecimal 			priceLimit = BigDecimal.ZERO;
	/**	Price					*/
	private BigDecimal 			price = BigDecimal.ZERO;
	/**	Price List				*/
	private BigDecimal 			priceList = BigDecimal.ZERO;
	/**	% Discount		    	*/
	private BigDecimal 			discountPercentage = BigDecimal.ZERO;
	/** is new line **/
	private boolean				isAddQty = false;
	/** OrderLine id **/
	private int 				orderLineId = 0;
	/**	Format					*/
	private DecimalFormat 		decimalFormat;
	/**	Date Format				*/
	private SimpleDateFormat	dateFormat;
	/**	Window No				*/
	private int 							windowNo;
	
	/**
	 * 	Set MPOS
	 * @param salesRepId
	 * @return true if found/set
	 */
	public void setPOS(int salesRepId) {
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
	 * Get number format
	 * @return
	 * @return DecimalFormat
	 */
	public DecimalFormat getNumberFormat() {
		return decimalFormat;
	}
	
	/**
	 * Get Date Format
	 * @return
	 */
	public SimpleDateFormat getDateFormat() {
		return dateFormat;
	}
	
	/**
	 * Get Window Number
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return int
	 */
	public int getWindowNo() {
		return windowNo;
	}
	
	/**
	 * Set window No
	 * @param windowNo
	 */
	public void setWindowNo(int windowNo) {
		this.windowNo = windowNo;
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
				&& X_C_Order.DOCSTATUS_Completed.equals(currentOrder.getDocStatus());
	}
	
	/**
	 * Get Sequence
	 * @return
	 * @return int
	 */
	public int getAD_Sequence_ID() {
		if (entityPOS.getC_DocType_ID() > 0)
			return entityPOS.getC_DocType().getDocNoSequence_ID();
		else
			throw new AdempierePOSException("@C_POS_ID@ @C_DocType_ID @NotFound@");
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
	public boolean isClosed() {
		if(!hasOrder()) {
			return false;
		}
		//
		return X_C_Order.DOCSTATUS_Closed.equals(currentOrder.getDocStatus());
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
		return X_C_Order.DOCSTATUS_Voided.equals(currentOrder.getDocStatus());
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
				&& X_C_Order.DOCSTATUS_Drafted.equals(currentOrder.getDocStatus());
	}
	
	/**
	 * Validate if is "In Process"}
	 * @return
	 * @return boolean
	 */
	public boolean isInProgress() {
		if(!hasOrder()) {
			return false;
		}
		//	
		return !isCompleted() 
				&& !isVoided() 
				&& X_C_Order.DOCSTATUS_InProgress.equals(currentOrder.getDocStatus());
	}
	
	/**
	 * Validate if is "Invalid"}
	 * @return
	 * @return boolean
	 */
	public boolean isInvalid() {
		if(!hasOrder()) {
			return false;
		}
		//	
		return !isCompleted() 
				&& !isVoided() 
				&& X_C_Order.DOCSTATUS_Invalid.equals(currentOrder.getDocStatus());
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
		return MOrder.DocSubTypeSO_POS.equals(getDocSubTypeSO());
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
		return MOrder.DocSubTypeSO_OnCredit.equals(getDocSubTypeSO());
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
		return MOrder.DocSubTypeSO_Standard.equals(getDocSubTypeSO());
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
		return MOrder.DocSubTypeSO_Prepay.equals(getDocSubTypeSO());
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
		return MOrder.DocSubTypeSO_Warehouse.equals(getDocSubTypeSO());
	}

	/**
	 * Validate if is Return Material
	 * @return
	 * @return boolean
	 */
	public boolean isReturnMaterial() {
		if(!hasOrder()) {
			return false;
		}
		//
		return MOrder.DocSubTypeSO_RMA.equals(getDocSubTypeSO());
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
		MInvoice[] invoices = getOrder().getInvoices();
		boolean orderInvoiced = false;
		if (invoices != null && invoices.length > 0) {
			orderInvoiced = invoices[0].getDocStatus().equalsIgnoreCase(MInvoice.DOCSTATUS_Completed);
		}
	
		return currentOrder.isInvoiced() || orderInvoiced;
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
		MDocType docType = MDocType.get(getCtx(), getC_DocType_ID());
		if(docType != null) {
			if(docType.getDocSubTypeSO() != null) {
				return docType.getDocSubTypeSO();
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
	 * Validate if is to print invoice
	 * @return
	 * @return boolean
	 */
	public void setIsToPrint(boolean isToPrint) {
		this.isToPrint= isToPrint;
	}
	
	/**
	 * Get Current Order
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return MOrder
	 */
	public MOrder getOrder() {
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
	 * @param name
	 * @return
	 * @return boolean
	 */
	public boolean compareBPName(String name) {
		return partner.getName().equals(name);
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
	 * Get Bank Account Id
	 * @return
     */
	public int getC_BankAccount_ID()
	{
		return entityPOS.getC_BankAccount_ID();
	}
	
	
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
		if (hasBPartner()
				&& currentOrder != null) {
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
		return Env.getAD_User_ID(Env.getCtx());
	}	//	getAD_User_ID
	
	/**
	 * Get Auto Delay
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return int
	 */
	public int getAutoLogoutDelay() {
		if (entityPOS != null)
			return entityPOS.getAutoLogoutDelay();
		return 0;
	}

	/**
	 * Get PIN Entry Timeout
	 * @return
	 * @return int
	 */
	public int getPINEntryTimeout() {
		if (entityPOS != null)
			return entityPOS.getPINEntryTimeout();
		return 0;
	}

	/**
	 * Get Sales Rep. Name
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return String
	 */
	public String getSalesRepName() {
		if (currentOrder != null && currentOrder.getC_BPartner().getSalesRep_ID() !=0)
		{
			String salesRepName = currentOrder.getC_BPartner().getSalesRep().getName();
			return salesRepName;
		}
		MUser salesRep = MUser.get(ctx);
		if(salesRep == null) {
			return null;
		}
		//	Default Return
		return salesRep.getName();
	}
	
	/**
	 * Get Sales Representative
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return int
	 */
	public int getSalesRep_ID() {

		if (currentOrder != null && currentOrder.getC_BPartner().getSalesRep_ID() !=0)
			return currentOrder.getC_BPartner().getSalesRep_ID();
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
	 * Is Enable Product Lookup
	 * @return
     */
	public boolean isEnableProductLookup() {
		return entityPOS.isEnableProductLookup();
	}

	/**
	 * Is POS Required PIN
	 * @return
     */
	public boolean isRequiredPIN(){
		return entityPOS.isPOSRequiredPIN();
	}

	/**
	 * 	New Order
	 *  @param partnerId
	 */
	public void newOrder(int partnerId) {
		log.info( "PosPanel.newOrder");
		currentOrder = null;
		int docTypeId = entityPOS.getC_DocType_ID();
		//	Create Order
		createOrder(partnerId, docTypeId);
		//	
		reloadOrder();
	}	//	newOrder
	
	/**
	 * Set Custom Document Type
	 * @param docTypeTargetId
	 * @return void
	 */
	public void setC_DocType_ID(int docTypeTargetId) {
		//	Valid if has a Order
		if(!isDrafted())
			return;
		//	Set Document Type
		currentOrder.setC_DocTypeTarget_ID(docTypeTargetId);
		//	Set Sequenced No
		String value = DB.getDocumentNo(getC_DocType_ID(), null, false, currentOrder);
		if (value != null) {
			currentOrder.setDocumentNo(value);
		}
		currentOrder.saveEx();
	}
	
	
	/**
	 * Get/create Order
	 *	@param partnerId Business Partner
	 *	@param docTypeTargetId ID of document type
	 */
	private void createOrder(int partnerId, int docTypeTargetId) {
		int orderId = getFreeC_Order_ID();
		//	Change Values for new Order
		if(orderId > 0) {
			currentOrder = new MOrder(Env.getCtx(), orderId, null);
			currentOrder.setDateOrdered(getToday());
			currentOrder.setDateAcct(getToday());
			currentOrder.setDatePromised(getToday());
		} else {
			currentOrder = new MOrder(Env.getCtx(), 0, null);
		}
		currentOrder.setAD_Org_ID(entityPOS.getAD_Org_ID());
		currentOrder.setIsSOTrx(true);
		if (entityPOS.getM_PriceList_ID() > 0)
			currentOrder.setM_PriceList_ID(entityPOS.getM_PriceList_ID());
		if (entityPOS.getDeliveryRule() != null)
			currentOrder.setDeliveryRule(getDeliveryRule());
		if (entityPOS.getInvoiceRule() != null)
			currentOrder.setInvoiceRule(getInvoiceRule());
		currentOrder.setC_POS_ID(entityPOS.getC_POS_ID());
		currentOrder.setM_Warehouse_ID(entityPOS.getM_Warehouse_ID());
		if (docTypeTargetId != 0) {
			currentOrder.setC_DocTypeTarget_ID(docTypeTargetId);
		} else {
			currentOrder.setC_DocTypeTarget_ID(MOrder.DocSubTypeSO_OnCredit);
		}
		//	Set BPartner
		configureBPartner(partnerId);
		//	Add if is new
		if(orderId < 0) {
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
	 *  Configuration of Business Partner has priority over POS configuration
	 *	@param p_C_BPartner_ID id
	 */
	
	/**
	 * set BPartner and save
	 */
	public void configureBPartner(int partnerId) {
		//	Valid if has a Order
		if(isCompleted()
				|| isVoided())
			return;
		log.fine( "CPOS.setC_BPartner_ID=" + partnerId);
		boolean isSamePOSPartner = false;
		//	Validate BPartner
		if (partnerId == 0) {
			isSamePOSPartner = true;
			partnerId = entityPOS.getC_BPartnerCashTrx_ID();
		}
		//	Get BPartner
		partner = MBPartner.get(ctx, partnerId);
		if (partner == null || partner.get_ID() == 0) {
			throw new AdempierePOSException("POS.NoBPartnerForOrder");
		} else {
			log.info("CPOS.SetC_BPartner_ID -" + partner);
			currentOrder.setBPartner(partner);
			//	
			MBPartnerLocation [] partnerLocations = partner.getLocations(true);
			if(partnerLocations.length > 0) {
				for(MBPartnerLocation partnerLocation : partnerLocations) {
					if(partnerLocation.isBillTo())
						currentOrder.setBill_Location_ID(partnerLocation.getC_BPartner_Location_ID());
					if(partnerLocation.isShipTo())
						currentOrder.setShip_Location_ID(partnerLocation.getC_BPartner_Location_ID());
				}				
			}
			//	Validate Same BPartner
			if(isSamePOSPartner) {
				if(currentOrder.getPaymentRule()==null)
					currentOrder.setPaymentRule(MOrder.PAYMENTRULE_Cash);
			}
			//	Set Sales Representative
			if (currentOrder.getC_BPartner().getSalesRep_ID()!=0)
				currentOrder.setSalesRep_ID(currentOrder.getC_BPartner().getSalesRep_ID());
			else
				currentOrder.setSalesRep_ID(entityPOS.getSalesRep_ID());
			//	Save Header
			currentOrder.saveEx();
			//	Load Price List Version
			MPriceListVersion priceListVersion = loadPriceListVersion(currentOrder.getM_PriceList_ID());
			MProductPrice[] productPrices = priceListVersion.getProductPrice("AND EXISTS("
					+ "SELECT 1 "
					+ "FROM C_OrderLine ol "
					+ "WHERE ol.C_Order_ID = " + currentOrder.getC_Order_ID() + " "
					+ "AND ol.M_Product_ID = M_ProductPrice.M_Product_ID)");
			//	Update Lines
			MOrderLine[] lines = currentOrder.getLines();
			//	Delete if not exist in price list
			for (MOrderLine line : lines) {
				//	Verify if exist
				if(existInPriceList(line.getM_Product_ID(), productPrices)) {
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
	 * @param productId
	 * @param productPrices
	 * @return boolean
	 */
	private boolean existInPriceList(int productId, MProductPrice[] productPrices) {
		for(MProductPrice productPrice : productPrices) {
			if(productId == productPrice.getM_Product_ID()) {
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
	 * @param orderId
	 */
	public void setOrder(int orderId) {
		currentOrder = new MOrder(ctx, orderId, null);
		if (orderId != 0) {
			loadPriceListVersion(currentOrder.getM_PriceList_ID());
		}
		//	
		reloadOrder();
	}
	
	/**
	 * Update Line
	 * @param orderLineId
	 * @param qtyOrdered
	 * @param priceLimit
	 * @param priceEntered
	 * @param priceList
     * @param discountPercentage
	 * @return
     */
	public BigDecimal [] updateLine(int orderLineId,
									BigDecimal qtyOrdered,
									BigDecimal priceLimit,
									BigDecimal priceEntered,
									BigDecimal priceList, BigDecimal discountPercentage) {
		//	Valid if has a Order
		if(!isDrafted())
			return null;
		//	
		MOrderLine[] orderLines = currentOrder.getLines("AND C_OrderLine_ID = " + orderLineId, "Line");
		BigDecimal lineNetAmt = Env.ZERO;
		BigDecimal taxRate = Env.ZERO;
		BigDecimal grandTotal = Env.ZERO;
		
		//	Search Line
		for(MOrderLine orderLine : orderLines) {
			//	Valid No changes
			if(qtyOrdered.compareTo(orderLine.getQtyOrdered()) == 0
			&& priceEntered.compareTo(orderLine.getPriceEntered()) == 0
			&& discountPercentage.compareTo(orderLine.getDiscount()) == 0 ) {
				return null;
			}

			if (discountPercentage.compareTo(orderLine.getDiscount()) != 0) {
				BigDecimal discountAmount = orderLine.getPriceList().multiply(discountPercentage.divide(Env.ONEHUNDRED));
				priceEntered = orderLine.getPriceList().subtract(discountAmount);
			}

			orderLine.setPrice(priceEntered);
			orderLine.setQty(qtyOrdered);
			orderLine.setTax();
			orderLine.saveEx();
			//	Set Values for Grand Total
			lineNetAmt = orderLine.getLineNetAmt();
			taxRate = MTax.get(ctx, orderLine.getC_Tax_ID()).getRate();
			if(taxRate == null) {
				taxRate = Env.ZERO;
			} else {
				taxRate = taxRate
						.divide(Env.ONEHUNDRED);
			}
			//	Calculate Total
			grandTotal = lineNetAmt
						.add(lineNetAmt
								.multiply(taxRate));
		}
		//	Return Value
		return new BigDecimal[]{lineNetAmt, taxRate, grandTotal};
	}

	/**
	 * Create new Line
	 * @param product
	 * @param qtyOrdered
	 * @param productPricing
     * @return
     */
	public MOrderLine addOrUpdateLine(MProduct product, BigDecimal qtyOrdered, MProductPricing productPricing) {
		//	Valid Complete
		if (!isDrafted())
			return null;
		// catch Exceptions at order.getLines()
		MOrderLine[] lines = currentOrder.getLines(true, "Line");
		for (MOrderLine line : lines) {
			if (line.getM_Product_ID() == product.getM_Product_ID()) {
				//increase qty
				setOrderLineId(line.getC_OrderLine_ID());
				BigDecimal currentPrice = line.getPriceEntered();
				BigDecimal currentQty = line.getQtyEntered();
				BigDecimal totalQty = currentQty.add(qtyOrdered);
				//	Set or Add Qty
				line.setQty(isAddQty()? totalQty: qtyOrdered);
				line.setPrice(currentPrice); //	sets List/limit
				line.saveEx();
				return line;
			}
		}
        //create new line
		MOrderLine line = new MOrderLine(currentOrder);
		line.setProduct(product);
		line.setQty(qtyOrdered);
		//	
		line.setPrice(); //	sets List/limit
		if ( productPricing.getPriceStd().signum() > 0 ) {
			line.setPriceLimit(productPricing.getPriceLimit());
			line.setPrice(productPricing.getPriceStd());
			line.setPriceList(productPricing.getPriceList());
			setPriceLimit(productPricing.getPriceLimit());
			//setPrice(productPricing.getPriceStd());
			setPriceList(productPricing.getPriceList());
			BigDecimal percentageDiscount = line.getDiscount();
			setDiscountPercentage(percentageDiscount);
		}
		//	Save Line
		setOrderLineId(line.getC_OrderLine_ID());
		line.saveEx();
		return line;
			
	} //	addOrUpdateLine

	/**
	 *  Save Line
	 * @param productId
	 * @param qtyOrdered
     * @return
     */
	public String addOrUpdate(int productId, BigDecimal qtyOrdered) {
		String errorMessage = null;
		try {
			MProduct product = MProduct.get(ctx, productId);
			if (product == null)
				return "@No@ @InfoProduct@";

			MProductPricing productPricing = new MProductPricing(productId, getC_BPartner_ID() , qtyOrdered , true , null);
			productPricing.setM_PriceList_ID(getM_PriceList_ID());
			productPricing.calculatePrice();
			//	Validate if exists a order
			if (hasOrder()) {
				addOrUpdateLine(product, qtyOrdered, productPricing);
			} else {
				return "@POS.MustCreateOrder@";
			}
		} catch (Exception e) {
			errorMessage = e.getMessage();
		}
		//	
		return errorMessage;
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
	public void deleteLine (int orderLineId) {
		if ( orderLineId != -1 && currentOrder != null ) {
			for ( MOrderLine line : currentOrder.getLines(true, I_C_OrderLine.COLUMNNAME_M_Product_ID) ) {
				if ( line.getC_OrderLine_ID() == orderLineId ) {
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
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		orderList = new ArrayList<Integer>();
		try {
			//	Set Parameter
			preparedStatement= DB.prepareStatement(sql, null);
			preparedStatement.setInt (1, Env.getAD_Client_ID(Env.getCtx()));
			preparedStatement.setInt (2, getC_POS_ID());
			preparedStatement.setInt (3, getSalesRep_ID());
			//	Execute
			resultSet = preparedStatement.executeQuery();
			//	Add to List
			while(resultSet.next()){
				orderList.add(resultSet.getInt(1));
			}
		} catch(Exception e) {
			log.severe("SubOrder.listOrder: " + e + " -> " + sql);
		} finally {
			DB.close(resultSet);
			DB.close(preparedStatement);
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
	 * @param orderId
	 * @return void
	 */
	public void reloadIndex(int orderId) {
		int position = orderList.indexOf(orderId);
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
	 * @param isPrepayment
	 * @param isPaid
	 * @return true if order processed or pre payment/on credit; otherwise false
	 * 
	 */
	public boolean processOrder(String trxName, boolean isPrepayment, boolean isPaid) {
		//Returning orderCompleted to check for order completeness
		boolean orderCompleted = isCompleted();
		// check if order completed OK
		if (orderCompleted) {	//	Order already completed -> default nothing
			setIsToPrint(false);
		} else {	//	Complete Order
			//	Replace
			if(trxName == null) {
				trxName = currentOrder.get_TrxName();
			} else {
				currentOrder.set_TrxName(trxName);
			}
			// In case the Order is Invalid, set to In Progress; otherwise it will not be completed
			if (currentOrder.getDocStatus().equalsIgnoreCase(MOrder.STATUS_Invalid)) 
				currentOrder.setDocStatus(MOrder.STATUS_InProgress);
			//	Set Document Action
			currentOrder.setDocAction(DocAction.ACTION_Complete);
			if (currentOrder.processIt(DocAction.ACTION_Complete)) {
				currentOrder.saveEx();
				orderCompleted = true;
				setIsToPrint(true);
			} else {
				log.info( "Process Order FAILED " + currentOrder.getProcessMsg());
				currentOrder.saveEx();
				return orderCompleted;
			}
		}

		//	Validate for Invoice and Shipment generation (not for Standard Orders)
		if(isPaid && !isStandardOrder()) {
			if(!isDelivered()) // Based on Delivery Rule of POS Terminal or partner
				generateShipment(trxName);

			if(!isInvoiced()) {
				if (MOrder.INVOICERULE_Immediate.equals(currentOrder.getInvoiceRule())
				|| (MOrder.INVOICERULE_AfterDelivery.equals(currentOrder.getInvoiceRule()) && isDelivered()))
					generateInvoice(trxName); // Based on Invoice rule of POS Terminal or partner
			}
			orderCompleted = true;
		}
		return orderCompleted;
	}	// processOrder
	
	/**
	 * Generate Shipment
	 * @param trxName
	 * @return void
	 */
	private void generateShipment(String trxName) {
		List<Integer> selectionIds = new ArrayList<Integer>();
		selectionIds.add(getC_Order_ID());

		//Generate Return using InOutGenerate
		ProcessInfo processInfo = ProcessBuilder
				.create(getCtx())
				.process(199)
				.withTitle(Msg.parseTranslation(getCtx(), "@InOutGenerateGen@"))
				.withParameter(MInOut.COLUMNNAME_M_Warehouse_ID, getM_Warehouse_ID())
				.withParameter("Selection", true)
				.withSelectedRecordsIds(MOrder.Table_ID , selectionIds)
				.withoutTransactionClose()
				.execute(trxName);

		if(processInfo.isError())
			throw new AdempiereException(processInfo.getSummary());
	}
	
	/**
	 * Complete Return with transaction
	 */
	public void completeReturn() {
		Trx.run(new TrxRunnable() {
			public void run(String trxName) {
				String error = completeReturn(trxName);
				if(error != null) {
					throw new AdempiereException(error);
				}
				//	Print Ticket
				if (isToPrint()) {
					printTicket();
				}
			}
		});
	}
	
	/**
	 * Complete return material
	 * @param trxName
	 * @return String error Message
	 */
	private String completeReturn(String trxName) {
		if (isDrafted() || isInProgress() || isInvalid()) {
        	if (!processOrder(trxName, false, false)) {
        		return Msg.parseTranslation(getCtx(), " @ProcessRunError@. " 
        				+ "@order.no@: " + getDocumentNo()+ ". @Process@: " + CommandManager.COMPLETE_DOCUMENT);
        	}
        	// For certain documents, there is no further processing
        	String docSubTypeSO = getDocSubTypeSO();
        	if((docSubTypeSO.equals(MOrder.DocSubTypeSO_Standard) ||
        		docSubTypeSO.equals(MOrder.DocSubTypeSO_OnCredit) ||
        		docSubTypeSO.equals(MOrder.DocSubTypeSO_Warehouse)) 
        		&& isCompleted())
        		return "@POS.IsNotReturn@";
        }
		//	Create
		MOrder returnOrder = new MOrder(getCtx(), getC_Order_ID(), trxName);
        returnOrder.setInvoiceRule(MOrder.INVOICERULE_Immediate);
        returnOrder.setDeliveryRule(MOrder.DELIVERYRULE_Force);
        returnOrder.saveEx();
        List<Integer> selectionIds = new ArrayList<Integer>();
        selectionIds.add(returnOrder.get_ID());
        //Generate Return using InOutGenerate
        ProcessInfo processInfo = ProcessBuilder
                .create(getCtx())
                .process(199)
                .withParameter(MOrder.COLUMNNAME_M_Warehouse_ID, getM_Warehouse_ID())
                .withParameter("Selection", true)
                .withSelectedRecordsIds(MOrder.Table_ID , selectionIds)
                .withoutTransactionClose()
                .execute(trxName);

        if (processInfo.isError()) {
            return processInfo.getLogInfo();
        }
        //Force the confirmation
        for (MInOut customerReturn : returnOrder.getShipments()) {
            customerReturn.processIt(DocAction.ACTION_Complete);
            customerReturn.saveEx();

            for (MInOutConfirm confirm : customerReturn.getConfirmations(true)) {
                for (MInOutLineConfirm confirmLine : confirm.getLines(true)) {
                    confirmLine.setConfirmedQty(confirmLine.getTargetQty());
                    confirmLine.saveEx();
                }
                confirm.processIt(DocAction.ACTION_Complete);
                confirm.saveEx();
            }
        }

        MOrder sourceOrder = (MOrder) returnOrder.getRef_Order();
        if (sourceOrder != null && returnOrder.getC_Order_ID() > 0)
        {
           if (sourceOrder.getInvoices().length > 0) {
               //Generate Credit note InvoiceGenerate
               processInfo = ProcessBuilder
                       .create(getCtx())
                       .process(134)
                       .withTitle(processInfo.getTitle())
                       .withParameter("Selection", true)
                       .withSelectedRecordsIds(MOrder.Table_ID , selectionIds)
                       .withParameter(MInvoice.COLUMNNAME_DocAction, MInvoice.DOCACTION_Complete)
                       .withoutTransactionClose()
                       .execute(trxName);
               //	Validate Error
               if (processInfo.isError()) {
                   return processInfo.getLogInfo();
               }
           }
           else // if not exist invoice then return of payment
           {
               Timestamp today = new Timestamp(System.currentTimeMillis());
               // Create return payment
               MPayment payment = new MPayment(returnOrder.getCtx() ,  0 , returnOrder.get_TrxName());
               payment.setDateTrx(today);
               payment.setC_Order_ID(returnOrder.getC_Order_ID());
               payment.setC_BankAccount_ID(getC_BankAccount_ID());
               payment.setDateAcct(today);
               payment.addDescription(Msg.parseTranslation(returnOrder.getCtx(), " @C_Order_ID@ " + returnOrder.getDocumentNo()));
               payment.setIsReceipt(false);
               payment.setC_DocType_ID(MDocType.getDocType(MDocType.DOCBASETYPE_APPayment));
               payment.setAmount(returnOrder.getC_Currency_ID() , returnOrder.getGrandTotal());
               payment.setDocAction(DocAction.ACTION_Complete);
               payment.setDocStatus(DocAction.STATUS_Drafted);
               payment.setIsPrepayment(true);
               payment.saveEx();

               payment.processIt(DocAction.ACTION_Complete);
               payment.saveEx();

               returnOrder.setC_POS_ID(getC_POS_ID());
               returnOrder.saveEx();

               processInfo.addLog(0,null,null,payment.getDocumentInfo());
           }
        }
        setIsToPrint(true);
        //	Default return
        return null;
	}
	
	/**
	 * Generate Invoice
	 * @param trxName
	 * @return void
	 */
	private void generateInvoice(String trxName) {
		List<Integer> selectionIds = new ArrayList<Integer>();
		selectionIds.add(getC_Order_ID());

		//Generate InvoiceGenerate
		ProcessInfo processInfo = ProcessBuilder
				.create(getCtx())
				.process(134)
				.withTitle(Msg.parseTranslation(getCtx(), "@InvGenerateGen@"))
				.withParameter("Selection", true)
				.withParameter(MInvoice.COLUMNNAME_DocAction, DocAction.ACTION_Complete)
				.withSelectedRecordsIds(MOrder.Table_ID , selectionIds)
				.withoutTransactionClose()
				.execute(trxName);

		if(processInfo.isError())
			throw new AdempiereException(processInfo.getSummary());
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
	 * @param paymentTermId
	 * @return void
	 */
	public void setC_PaymentTerm_ID(int paymentTermId) {
		if(paymentTermId != 0
				&& hasOrder()
				&& !isCompleted()
				&& !isVoided()) {
			currentOrder.setC_PaymentTerm_ID(paymentTermId);
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
		if (currentOrder != null)
		return currentOrder.getGrandTotal().subtract(getTaxAmt());
		else return BigDecimal.ZERO;
	}
	
	/**
	 * Get Grand Total for current Order
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return BigDecimal
	 */
	public BigDecimal getGrandTotal() {
		if (currentOrder != null)
			return currentOrder.getGrandTotal();
		else
			return BigDecimal.ZERO;
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
	 * get Default Delivery Rule from POS Terminal
	 * @return
     */
	public String getDeliveryRule()
	{
		return entityPOS.getDeliveryRule();
	}

	/**
	 * get default invoice rule from POS Terminal
	 * @return
     */
	public String getInvoiceRule()
	{
		return entityPOS.getInvoiceRule();
	}

	/**
	 * Get Open Amount
	 * @return
	 * @return BigDecimal
	 */
	public BigDecimal getOpenAmt() {
		if (currentOrder != null) {
			return currentOrder.getGrandTotal().subtract(getAmountReceived());
		}
		return BigDecimal.ZERO;
	}

	/**
	 * Amount Received
	 * @return BigDecimal
     */
	public BigDecimal getAmountReceived()
	{
		BigDecimal totalPayAmt = Env.ZERO;
		for (MPayment payment : MPayment.getOfOrder(getOrder()))
			totalPayAmt = totalPayAmt.add(payment.getPayAmt());

		return totalPayAmt;
	}

	/**
	 * Verify if is Paid
	 * @return
	 * @return boolean
	 */
	public boolean isPaid() {
		return getOpenAmt().signum() == 0;
	}
	
	/**
	 * 	Gets Amount Paid for an Order
	 * 	It considers the allocated amounts via invoices and credit memos 
	 * and also prepayments
	 */
	public BigDecimal getPaidAmt() {
		BigDecimal receivedInvoices    = BigDecimal.ZERO;
		BigDecimal receivedPrePayments = BigDecimal.ZERO;
		BigDecimal receivedCash        = BigDecimal.ZERO;
		BigDecimal receivedTotal       = BigDecimal.ZERO;
		// entities
		final String 		payment = MPayment.Table_Name;
		final String 	 allocation = MAllocationHdr.Table_Name;
		final String allocationLine = MAllocationLine.Table_Name;
		final String 	   cashLine = MCashLine.Table_Name;
		// Column ids
		final String 		orderId = MPayment.COLUMNNAME_C_Order_ID;
		final String 	  invoiceId = MAllocationLine.COLUMNNAME_C_Invoice_ID;
		final String   allocationId = MAllocationHdr.COLUMNNAME_C_AllocationHdr_ID;
		// Columns
		final String 		 payAmt = MPayment.COLUMNNAME_PayAmt;
		final String documentStatus = MAllocationHdr.COLUMNNAME_DocStatus;
		final String 		 amount = MAllocationLine.COLUMNNAME_Amount;
		//	
		if (currentOrder != null) {
			// Prepayments
			StringBuilder whereClause =  new StringBuilder();
			whereClause.append( orderId ).append("=?").append(" AND ")
					.append( documentStatus ).append(" IN ('CO','CL') ");
			receivedPrePayments = new Query(getCtx(), payment , whereClause.toString(), null)
					.setClient_ID()
					.setParameters(currentOrder.getC_Order_ID())
					.sum( payAmt );
			if (receivedPrePayments == null)
				receivedPrePayments = Env.ZERO;
			// Invoices
			if(currentOrder.getC_Invoice_ID() > 1) {
				whereClause = new StringBuilder();
				whereClause
						.append("EXISTS (SELECT 1 FROM ")
						.append(	  allocation ).append(" WHERE ")
						.append(      allocation ).append(".").append( allocationId ).append("=")
						.append(  allocationLine ).append(".").append( allocationId ).append(" AND ")
						.append(      allocation ).append(".").append( documentStatus ).append(" IN ('CO','CL') AND ")
						.append(  allocationLine ).append(".").append( invoiceId ).append("=?)");
				receivedInvoices = new Query(getCtx() , allocationLine , whereClause.toString() , null)
						.setClient_ID()
						.setParameters(currentOrder.getC_Invoice_ID())
						.sum( amount );
				if (receivedInvoices == null)
					receivedInvoices = Env.ZERO;
				// Cash
				whereClause = new StringBuilder();
				whereClause.append( invoiceId ).append("=?");
				receivedCash =  new Query(getCtx() , cashLine , whereClause.toString() , null)
						.setClient_ID()
						.setParameters(currentOrder.getC_Invoice_ID())
						.sum( amount );
				if (receivedCash == null)
					receivedCash = Env.ZERO;			
			}
		}
		receivedTotal = receivedInvoices.add(receivedPrePayments).add(receivedCash);
		return receivedTotal;
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
		currentOrder.getLines(true, "Line");
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
	 * 	Get M_PriceList_Version_ID.
	 * 	Set Currency
	 *	@return plv
	 */
	public int getM_PriceList_ID() {
		return priceListId;
	}	//	getM_PriceList_ID

	/**
	 * Load Price List Version from Price List
	 * @param priceListId
	 * @return
	 * @return MPriceListVersion
	 */
	public MPriceListVersion loadPriceListVersion(int priceListId) {
		priceListVersionId = 0;
		this.priceListId = priceListId;
		MPriceList priceList = MPriceList.get(ctx, priceListId, null);
		//
		MPriceListVersion priceListVersion = priceList.getPriceListVersion (getToday());
		if (priceListVersion != null
				&& priceListVersion.getM_PriceList_Version_ID() != 0) {
			priceListVersionId = priceListVersion.getM_PriceList_Version_ID();
		}
		//	Default Return
		return priceListVersion;
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
	 * Get Date ordered
	 * @return
	 */
	public Timestamp getDateOrdered() {
		if(hasOrder()) {
			return currentOrder.getDateOrdered();
		}
		//	Default
		return null;
	}
	
	/**
	 * Get Currency Symbol
	 * @return
	 * @return String
	 */
	public String getCurSymbol() {
		int currencyId = getC_Currency_ID();
		if(currencyId > 0) {
			MCurrency currency = MCurrency.get(getCtx(), currencyId);
			if(currency != null) {
				return currency.getCurSymbol();
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
			MPaymentProcessor[] paymentProcessors = MPaymentProcessor.find (Env.getCtx(), null, null,
					currentOrder.getAD_Client_ID (), currentOrder.getAD_Org_ID(), currentOrder.getC_Currency_ID (), amt, currentOrder.get_TrxName());
			//
			HashMap<String,ValueNamePair> map = new HashMap<String,ValueNamePair>(); //	to eliminate duplicates
			for (int i = 0; i < paymentProcessors.length; i++) {
				if (paymentProcessors[i].isAcceptAMEX ())
					map.put (MPayment.CREDITCARDTYPE_Amex, getCreditCardPair (MPayment.CREDITCARDTYPE_Amex));
				if (paymentProcessors[i].isAcceptDiners ())
					map.put (MPayment.CREDITCARDTYPE_Diners, getCreditCardPair (MPayment.CREDITCARDTYPE_Diners));
				if (paymentProcessors[i].isAcceptDiscover ())
					map.put (MPayment.CREDITCARDTYPE_Discover, getCreditCardPair (MPayment.CREDITCARDTYPE_Discover));
				if (paymentProcessors[i].isAcceptMC ())
					map.put (MPayment.CREDITCARDTYPE_MasterCard, getCreditCardPair (MPayment.CREDITCARDTYPE_MasterCard));
				if (paymentProcessors[i].isAcceptCorporate ())
					map.put (MPayment.CREDITCARDTYPE_PurchaseCard, getCreditCardPair (MPayment.CREDITCARDTYPE_PurchaseCard));
				if (paymentProcessors[i].isAcceptVisa ())
					map.put (MPayment.CREDITCARDTYPE_Visa, getCreditCardPair (MPayment.CREDITCARDTYPE_Visa));
			} //	for all payment processors
			//
			ValueNamePair[] retValue = new ValueNamePair[map.size ()];
			map.values ().toArray (retValue);
			log.fine("getCreditCards - #" + retValue.length + " - Processors=" + paymentProcessors.length);
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
			List<MInvoice> invoiceList = new Query(Env.getCtx(), MInvoice.Table_Name, whereClause, null)
				.setParameters(currentOrder.getC_BPartner_ID())
				.list();
			//
			HashMap<String,ValueNamePair> map = new HashMap<String,ValueNamePair>(); //	to eliminate duplicates
			ValueNamePair valueNamePair;
			for (MInvoice invoice : invoiceList) {
				Integer id = invoice.getC_Invoice_ID();
				valueNamePair = new ValueNamePair(id.toString(), invoice.getDocumentNo() + " " + invoice.getOpenAmt().toString());
					map.put (invoice.getDocumentNo(), valueNamePair);
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
		documentSequence = new MSequence(Env.getCtx(), getAD_Sequence_ID() , trxName);
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
	 * @param qty
	 */
	public void setQty(BigDecimal qty) {
		this.quantity = qty;
	}
	
	/**
	 * Get Quantity of Product
	 * @return quantity
	 */
	public BigDecimal getQtyAdded() {
		BigDecimal quantityAddedReturn = quantityAdded;
		quantityAdded = Env.ZERO;
		setAddQty(false);
		return quantityAddedReturn;
	}

	/**
	 * Set Quantity of Product
	 * @param qtyAdded
	 */
	public void setQtyAdded(BigDecimal qtyAdded) {
		this.quantityAdded = qtyAdded;
	}
	
	/**
	 * Get Price of Product
	 * @return price
	 */
	public BigDecimal getPrice() {
		return this.price;
	}

	/**
	 * Get Price of Product
	 * @return price
	 */
	public BigDecimal getDiscountPercentage() {
		return this.discountPercentage;
	}


	public void setDiscountPercentage(BigDecimal discountPercentage)
	{
		this.discountPercentage = discountPercentage;
	}

	public void setPriceLimit(BigDecimal priceLimit)
	{
		this.priceLimit = priceLimit;
	}

	public BigDecimal getPriceLimit() {
		return this.priceLimit;
	}

	public BigDecimal getPriceList() {
		return this.priceList;
	}

	public void setPriceList(BigDecimal priceList) {
		this.priceList = priceList;
	}

	/**
	 * Set Price of Product
	 * @param price
	 */
	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}

	/**
	 * Set Price from Product Pricing
	 * @param productPricing
	 * @return void
	 */
	public void setPrice (MProductPricing productPricing)
	{
		setPriceLimit(productPricing.getPriceLimit());
		setPrice(productPricing.getPriceStd());
		setPriceList(productPricing.getPriceList());
	}

	/**
	 * 
	 * @return
	 * @return String
	 */
	public String getElectronicScales()
	{
		if (entityPOS != null)
			return entityPOS.getElectronicScales();
		return null;
	}

	public String getMeasureRequestCode()
	{
		if (entityPOS != null)
			return entityPOS.getMeasureRequestCode();
		return null;
	}

	public boolean isPresentElectronicScales()
	{
		if (getElectronicScales() != null)
			if (getElectronicScales().length() > 0)
				return true;
			else
				return false;
		else
			return false;
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

	/**
	 * Get Product Image
	 * Right now, it is only possible a two-stage lookup.
	 * A general lookup has to be implemented, where more than 2 stages are considered.
	 * @param productId
	 * @param posKeyLayoutId
	 * @return int
	 */
	public int getProductImageId(int productId , int posKeyLayoutId) {
		int imageId = 0;

		//	Valid Product
		if(productId == 0)
			return imageId;

		//	Get POS Key
		int m_C_POSKey_ID = DB.getSQLValue(null, "SELECT pk.C_POSKey_ID "
				+ "FROM C_POSKey pk "
				+ "WHERE pk.C_POSKeyLayout_ID = ? "
				+ "AND pk.M_Product_ID = ? "
				+ "AND pk.IsActive = 'Y'", posKeyLayoutId , productId);
		
		if(m_C_POSKey_ID > 0) {
			//	Valid POS Key
			MPOSKey key =  new MPOSKey(ctx, m_C_POSKey_ID, null);
			imageId = key.getAD_Image_ID();
		}
		else  {
			//	No record has been found for a product in the current Key Layout. Try it in the Subkey Layout.
			m_C_POSKey_ID = DB.getSQLValue(null, "SELECT pk2.C_POSKey_ID "
					+ "FROM C_POSKey pk1 "
					+ "INNER JOIN C_POSKey pk2 ON pk1.subkeylayout_id=pk2.c_poskeylayout_id AND pk1.subkeylayout_id IS NOT NULL "
					+ "WHERE pk2.M_Product_ID = ? "
					+ "AND pk1.IsActive = 'Y' AND pk2.IsActive = 'Y'", productId);
			//	Valid POS Key
			if(m_C_POSKey_ID > 0) {
				MPOSKey key =  new MPOSKey(ctx, m_C_POSKey_ID, null);
				imageId = key.getAD_Image_ID();
			}
		}
		return imageId;
	}

	/**
	 * Get Product ID from Order Line ID
	 * @param orderLineId
	 * @return
	 * @return int
	 */
	public int getM_Product_ID(int orderLineId) {
		return DB.getSQLValue(null, "SELECT ol.M_Product_ID "
				+ "FROM C_OrderLine ol "
				+ "WHERE ol.C_OrderLine_ID = ?", orderLineId);
	}

	/**
	 * Validate User PIN
	 * @param userPin
     */
	public boolean isValidUserPin(char[] userPin) {
		if(userPin==null || userPin.length==0)
			return false;
		MUser user = MUser.get(getCtx() ,getAD_User_ID());
		Optional<I_AD_User> optionalSuperVisor = Optional.of(user.getSupervisor());
		I_AD_User superVisor = optionalSuperVisor.orElseThrow(() -> new AdempierePOSException("@Supervisor@ @NotFound@"));
		Optional<String> superVisorName = Optional.ofNullable(superVisor.getName());
		if (superVisor.getUserPIN() == null || superVisor.getUserPIN().isEmpty())
			throw new AdempierePOSException("@Supervisor@ \"" + superVisorName.orElse("") + "\": @UserPIN@ @NotFound@");

		char[] correctPassword = superVisor.getUserPIN().toCharArray();
		boolean isCorrect = true;
		if (userPin.length != correctPassword.length) {
			isCorrect = false;
		} else {
			for (int i = 0; i < userPin.length; i++) {
				if (userPin[i] != correctPassword[i]) {
					isCorrect = false;
				}
			}
		}
		//Zero out the password.
		for (int i = 0; i < correctPassword.length; i++) {
			correctPassword[i] = 0;
		}

		return isCorrect;
	}

	/**
	 * Get Product Value from ID
	 * @param productId
	 * @return
	 * @return String
	 */
	public String getProductValue(int productId) {
		return DB.getSQLValueString(null, "SELECT Value FROM M_Product WHERE M_Product_ID = ? " , productId);
	}

	/**
	 * Get Product Name from ID
	 * @param productId
	 * @return
	 * @return String
	 */
	public String getProductName(int productId) {
		return DB.getSQLValueString(null, "SELECT name FROM M_Product WHERE M_Product_ID = ? " , productId);
	}

	/**
	 * Get Query for Product
	 * @param productCode
	 * @param warehouseId
	 * @param priceListId
	 * @param partnerId
	 * @return
	 * @return List<Vector<Object>>
	 */
	public static List<Vector<Object>> getQueryProduct(String productCode, int warehouseId , int priceListId , int partnerId) {
		ArrayList<Vector<Object>> rows = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT ON ( ProductPricing.M_Product_ID , p.Value, p.Name ) ProductPricing.M_Product_ID , p.Value, p.Name,")
				.append("   BomQtyAvailable(ProductPricing.M_Product_ID, ? , 0 ) AS QtyAvailable , PriceStd , PriceList")
					.append(" FROM M_Product p INNER JOIN (")
					.append("	SELECT pl.M_PriceList_ID , ValidFrom , 0 AS BreakValue , null AS C_BPartner_ID,")
					.append("   p.M_Product_ID,")
					.append("	bomPriceStd(p.M_Product_ID,plv.M_PriceList_Version_ID) AS PriceStd,")
					.append("	bomPriceList(p.M_Product_ID,plv.M_PriceList_Version_ID) AS PriceList,")
					.append("	bomPriceLimit(p.M_Product_ID,plv.M_PriceList_Version_ID) AS PriceLimit")
					.append("	FROM M_Product p")
					.append("	INNER JOIN M_ProductPrice pp ON (p.M_Product_ID=pp.M_Product_ID)")
					.append("	INNER JOIN M_PriceList_Version plv ON (pp.M_PriceList_Version_ID=plv.M_PriceList_Version_ID)")
					.append("	INNER JOIN M_PriceList pl ON (plv.M_PriceList_ID=pl.M_PriceList_ID)")
					.append("	WHERE pl.M_PriceList_ID=? AND plv.IsActive='Y' AND pp.IsActive='Y' ")
				.append("	UNION	")
					.append("	SELECT pl.M_PriceList_ID , plv.ValidFrom , pp.BreakValue , pp.C_BPartner_ID,")
					.append("   p.M_Product_ID,")
					.append("   pp.PriceStd, pp.PriceList, pp.PriceLimit")
					.append("	FROM M_Product p")
					.append("	INNER JOIN M_ProductPriceVendorBreak pp ON (p.M_Product_ID=pp.M_Product_ID)")
					.append("	INNER JOIN M_PriceList_Version plv ON (pp.M_PriceList_Version_ID=plv.M_PriceList_Version_ID)")
					.append("	INNER JOIN M_PriceList pl ON (plv.M_PriceList_ID=pl.M_PriceList_ID)")
					.append("	WHERE pl.M_PriceList_ID=? AND plv.IsActive='Y' AND pp.IsActive='Y'AND pp.BreakValue IN (0,1)")
					.append("  ORDER BY ValidFrom DESC, BreakValue DESC , C_BPartner_ID ASC")
					.append(") ProductPricing  ON (p.M_Product_ID=ProductPricing.M_Product_ID)")
				.append(" WHERE M_PriceList_ID=? AND ValidFrom <= getDate() ");
				if (partnerId > 0 )
					sql.append("AND (C_BPartner_ID IS NULL OR C_BPartner_ID =?) ");
				else
					sql.append( "AND C_BPartner_ID IS NULL ");

				sql.append("AND p.AD_Client_ID=? AND p.IsSold=? AND p.Discontinued=? ")
				.append("AND ")
				.append("(")
				.append("UPPER(p.Name)  LIKE UPPER('").append("%").append(productCode.replace(" ","%")).append("%").append("')")
				.append(" OR UPPER(p.Value) LIKE UPPER('").append("%").append(productCode.replace(" ","%")).append("%").append("')")
				.append(" OR UPPER(p.UPC)   LIKE UPPER('").append("%").append(productCode.replace(" ","%")).append("%").append("')")
				.append(" OR UPPER(p.SKU)   LIKE UPPER('").append("%").append(productCode.replace(" ","%")).append("%").append("')")
				.append(")");
		PreparedStatement statement = null;
		try{
			statement = DB.prepareStatement(sql.toString(), null);
			int count = 1;
			statement.setInt(count, warehouseId);
			count ++;
			statement.setInt(count, priceListId);
			count ++;
			statement.setInt(count, priceListId);
			count ++;
			statement.setInt(count, priceListId);
			count ++;
			if (partnerId > 0) {
				statement.setInt(count, partnerId);
				count++;
			}
			statement.setInt(count, Env.getAD_Client_ID(Env.getCtx()));
			count++;
			statement.setString(count, "Y");
			count++;
			statement.setString(count, "N");

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Vector<Object> columns = new Vector<>();
				Integer productId = resultSet.getInt(1);
				String  productValue = resultSet.getString(2).trim();
				String  productName = resultSet.getString(3).trim();
				String  qtyAvailable = resultSet.getBigDecimal(4) != null ? resultSet.getBigDecimal(4).toString().trim() : "0";
				String  priceStd = resultSet.getBigDecimal(5) != null ? resultSet.getBigDecimal(5).setScale(2, BigDecimal.ROUND_UP).toString().trim() :  "0";
				String  priceList = resultSet.getBigDecimal(6) != null ? resultSet.getBigDecimal(6).setScale(2, BigDecimal.ROUND_UP).toString().trim() : "0 ";
				columns.add(productId);
				columns.add(productValue);
				columns.add(productName);
				columns.add(qtyAvailable);
				columns.add(priceStd);
				columns.add(priceList);
				rows.add(columns);
			}

			DB.close(resultSet,statement);
			statement = null;
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		return rows;
	}

	/**
	 * 	Print Ticket
	 *
	 */
	public void printTicket() {
		if (!hasOrder())
			return;
		//	Print
		POSTicketHandler ticketHandler = POSTicketHandler.getTicketHandler(this);
		if(ticketHandler == null)
			return;
		//	
		ticketHandler.printTicket();
	}

	/**
	 * Set if the quantity is set or add to it
	 * @param isAddQty
	 * @return void
	 */
	public void setAddQty(boolean isAddQty) {
		this.isAddQty = isAddQty;
	}

	/**
	 * Verify if set Quatity or Add
	 * @return
	 * @return boolean
	 */
	public boolean isAddQty() {
		return isAddQty;
	}

	/**
	 * Get Order Line ID
	 * @return
	 * @return int
	 */
	public int getOrderLineId() {
		return orderLineId;
	}

	/**
	 * Set Order Line ID
	 * @param orderLineId
	 * @return void
	 */
	public void setOrderLineId(int orderLineId) {
		this.orderLineId = orderLineId;
	}
	
	/**
	 * Get Total Lines for view in POS with format
	 * @return
	 */
	public String getTotaLinesForView() {
		return getNumberFormat().format(getTotalLines());
	}
	
	/**
	 * Get Grand Total for view
	 * @return
	 */
	public String getGrandTotalForView() {
		return getNumberFormat().format(getGrandTotal());
	}
	
	/**
	 * Get Tax Amount for view
	 * @return
	 */
	public String getTaxAmtForView() {
		return getNumberFormat().format(getGrandTotal().subtract(getTotalLines()));
	}
	
	/**
	 * Get Date Ordered for view
	 * @return
	 */
	public String getDateOrderedForView() {
		return getDateFormat().format(getDateOrdered());
	}
	
	/**
	 * Get Class name for ticket handler
	 * @return
	 */
	public String getTicketHandlerClassName() {
		return entityPOS.getTicketClassName();
	}
	
	public String get_TrxName() {
		if(!hasOrder())
			return null;
		//	Default
		return currentOrder.get_TrxName();
	}
}
