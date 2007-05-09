package org.compiere.acct;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MProduct;
import org.compiere.model.ProductCost;

public interface IDocLine {

	/**
	 *  Get Currency
	 *  @return c_Currency_ID
	 */
	public abstract int getC_Currency_ID(); //  getC_Currency_ID

	/**
	 *  Get Conversion Type
	 *  @return C_ConversionType_ID
	 */
	public abstract int getC_ConversionType_ID(); //  getC_ConversionType_ID

	/**
	 *  Set Amount (DR)
	 *  @param sourceAmt source amt
	 */
	public abstract void setAmount(BigDecimal sourceAmt); //  setAmounts

	/**
	 *  Set Amounts
	 *  @param amtSourceDr source amount dr
	 *  @param amtSourceCr source amount cr
	 */
	public abstract void setAmount(BigDecimal amtSourceDr,
			BigDecimal amtSourceCr); //  setAmounts

	/**
	 *  Set Converted Amounts
	 *  @param C_AcctSchema_ID acct schema
	 *  @param amtAcctDr acct amount dr
	 *  @param amtAcctCr acct amount cr
	 */
	public abstract void setConvertedAmt(int C_AcctSchema_ID,
			BigDecimal amtAcctDr, BigDecimal amtAcctCr); //  setConvertedAmt

	/**
	 *  Line Net Amount or Dr-Cr
	 *  @return balance
	 */
	public abstract BigDecimal getAmtSource(); //  getAmount

	/**
	 *  Get (Journal) Line Source Dr Amount
	 *  @return DR source amount
	 */
	public abstract BigDecimal getAmtSourceDr(); //  getAmtSourceDr

	/**
	 *  Get (Journal) Line Source Cr Amount
	 *  @return CR source amount
	 */
	public abstract BigDecimal getAmtSourceCr(); //  getAmtSourceCr

	/**
	 *  Line Journal Accounted Dr Amount
	 *  @return DR accounted amount
	 */
	public abstract BigDecimal getAmtAcctDr(); //  getAmtAcctDr

	/**
	 *  Line Journal Accounted Cr Amount
	 *  @return CR accounted amount
	 */
	public abstract BigDecimal getAmtAcctCr(); //  getAmtAccrCr

	/**
	 *  Charge Amount
	 *  @return charge amount
	 */
	public abstract BigDecimal getChargeAmt(); //  getChargeAmt

	/**
	 *  Set Product Amounts
	 *  @param LineNetAmt Line Net Amt
	 *  @param PriceList Price List
	 *  @param Qty Qty for discount calc
	 */
	public abstract void setAmount(BigDecimal LineNetAmt, BigDecimal PriceList,
			BigDecimal Qty); //  setAmounts

	/**
	 *  Line Discount
	 *  @return discount amount
	 */
	public abstract BigDecimal getDiscount(); //  getDiscount

	/**
	 *  Line List Amount
	 *  @return list amount
	 */
	public abstract BigDecimal getListAmount(); //  getListAmount

	/**
	 * 	Set Line Net Amt Difference
	 *	@param diff difference (to be subtracted)
	 */
	public abstract void setLineNetAmtDifference(BigDecimal diff); //	setLineNetAmtDifference

	/**************************************************************************
	 *  Set Accounting Date
	 *  @param dateAcct acct date
	 */
	public abstract void setDateAcct(Timestamp dateAcct); //  setDateAcct

	/**
	 *  Get Accounting Date
	 *  @return accounting date
	 */
	public abstract Timestamp getDateAcct(); //  getDateAcct

	/**
	 *  Set Document Date
	 *  @param dateDoc doc date
	 */
	public abstract void setDateDoc(Timestamp dateDoc); //  setDateDoc

	/**
	 *  Get Document Date
	 *  @return document date
	 */
	public abstract Timestamp getDateDoc(); //  getDateDoc

	/**************************************************************************
	 *  Set GL Journal Account
	 *  @param acct account
	 */
	public abstract void setAccount(MAccount acct); //  setAccount

	/**
	 *  Get GL Journal Account
	 *  @return account
	 */
	public abstract MAccount getAccount(); //  getAccount

	/**
	 *  Line Account from Product (or Charge).
	 *
	 *  @param  AcctType see ProductCost.ACCTTYPE_* (0..3)
	 *  @param as Accounting schema
	 *  @return Requested Product Account
	 */
	public abstract MAccount getAccount(int AcctType, MAcctSchema as); //  getAccount

	/**
	 *  Get Charge Account
	 *  @param as account schema
	 *  @param amount amount for expense(+)/revenue(-)
	 *  @return Charge Account or null
	 */
	public abstract MAccount getChargeAccount(MAcctSchema as, BigDecimal amount); //  getChargeAccount

	/**************************************************************************
	 *  Get (Journal) AcctSchema
	 *  @return C_AcctSchema_ID
	 */
	public abstract int getC_AcctSchema_ID(); //  getC_AcctSchema_ID

	/**
	 * 	Get Line ID
	 *	@return id
	 */
	public abstract int get_ID(); //	get_ID

	/**
	 * 	Get AD_Org_ID
	 *	@return org
	 */
	public abstract int getAD_Org_ID(); //	getAD_Org_ID

	/**
	 * 	Get Order AD_Org_ID
	 *	@return order org if defined
	 */
	public abstract int getOrder_Org_ID(); //	getOrder_Org_ID

	/**
	 *  Product
	 *  @return M_Product_ID
	 */
	public abstract int getM_Product_ID(); //  getM_Product_ID

	/**
	 * 	Is this an Item Product (vs. not a Service, a charge)
	 *	@return true if product
	 */
	public abstract boolean isItem(); //	isItem

	/**
	 *  ASI
	 *  @return M_AttributeSetInstance_ID
	 */
	public abstract int getM_AttributeSetInstance_ID(); //  getM_AttributeSetInstance_ID

	/**
	 *  Get Warehouse Locator (from)
	 *  @return M_Locator_ID
	 */
	public abstract int getM_Locator_ID(); //  getM_Locator_ID

	/**
	 *  Get Warehouse Locator To
	 *  @return M_Locator_ID
	 */
	public abstract int getM_LocatorTo_ID(); //  getM_LocatorTo_ID

	/**
	 * 	Set Production BOM flag
	 *	@param productionBOM flag
	 */
	public abstract void setProductionBOM(boolean productionBOM); //	setProductionBOM

	/**
	 * 	Is this the BOM to be produced
	 *	@return true if BOM
	 */
	public abstract boolean isProductionBOM(); //	isProductionBOM

	/**
	 *  Get Production Plan
	 *  @return M_ProductionPlan_ID
	 */
	public abstract int getM_ProductionPlan_ID(); //  getM_ProductionPlan_ID

	/**
	 *  Get Order Line Reference
	 *  @return C_OrderLine_ID
	 */
	public abstract int getC_OrderLine_ID(); //  getC_OrderLine_ID

	/**
	 * 	Get C_LocFrom_ID
	 *	@return loc from
	 */
	public abstract int getC_LocFrom_ID(); //	getC_LocFrom_ID

	/**
	 * 	Set C_LocFrom_ID
	 *	@param C_LocFrom_ID loc from
	 */
	public abstract void setC_LocFrom_ID(int C_LocFrom_ID); //	setC_LocFrom_ID

	/**
	 * 	Get C_LocTo_ID
	 *	@return loc to
	 */
	public abstract int getC_LocTo_ID(); //	getC_LocTo_ID

	/**
	 * 	Set C_LocTo_ID
	 *	@param C_LocTo_ID loc to
	 */
	public abstract void setC_LocTo_ID(int C_LocTo_ID); //	setC_LocTo_ID

	/**
	 * 	Get Product Cost Info
	 *	@return product cost
	 */
	public abstract ProductCost getProductCost(); //	getProductCost

	/**
	 *  Get Total Product Costs
	 *  @param as accounting schema
	 *  @param AD_Org_ID trx org
	 *	@param zeroCostsOK zero/no costs are OK
	 *  @return costs
	 */
	public abstract BigDecimal getProductCosts(MAcctSchema as, int AD_Org_ID,
			boolean zeroCostsOK); //  getProductCosts

	/**
	 * 	Get Product 
	 *	@return product or null if no product
	 */
	public abstract MProduct getProduct(); //	getProduct

	/**
	 *  Get Revenue Recognition
	 *  @return C_RevenueRecognition_ID or 0
	 */
	public abstract int getC_RevenueRecognition_ID(); //  getC_RevenueRecognition_ID

	/**
	 *  Quantity UOM
	 *  @return Transaction or Storage M_UOM_ID
	 */
	public abstract int getC_UOM_ID(); //  getC_UOM

	/**
	 *  Quantity
	 *  @param qty transaction Qty
	 * 	@param isSOTrx SL order trx (i.e. negative qty)
	 */
	public abstract void setQty(BigDecimal qty, boolean isSOTrx); //  setQty

	/**
	 *  Quantity
	 *  @return transaction Qty
	 */
	public abstract BigDecimal getQty(); //  getQty

	/**
	 *  Description
	 *  @return doc line description
	 */
	public abstract String getDescription(); //	getDescription

	/**
	 *  Line Tax
	 *  @return C_Tax_ID
	 */
	public abstract int getC_Tax_ID(); //	getC_Tax_ID

	/**
	 *  Get Line Number
	 *  @return line no
	 */
	public abstract int getLine(); //  getLine

	/**
	 *  Get BPartner
	 *  @return C_BPartner_ID
	 */
	public abstract int getC_BPartner_ID(); //  getC_BPartner_ID

	/**
	 * 	Get C_BPartner_Location_ID
	 *	@return BPartner Location
	 */
	public abstract int getC_BPartner_Location_ID(); //	getC_BPartner_Location_ID

	/**
	 *  Get TrxOrg
	 *  @return AD_OrgTrx_ID
	 */
	public abstract int getAD_OrgTrx_ID(); //  getAD_OrgTrx_ID

	/**
	 *  Get SalesRegion.
	 *  - get Sales Region from BPartner
	 *  @return C_SalesRegion_ID
	 */
	public abstract int getC_SalesRegion_ID(); //  getC_SalesRegion_ID

	/**
	 *  Get Project
	 *  @return C_Project_ID
	 */
	public abstract int getC_Project_ID(); //  getC_Project_ID

	/**
	 *  Get Campaign
	 *  @return C_Campaign_ID
	 */
	public abstract int getC_Campaign_ID(); //  getC_Campaign_ID

	/**
	 *  Get Activity
	 *  @return C_Activity_ID
	 */
	public abstract int getC_Activity_ID(); //  getC_Activity_ID

	/**
	 *  Get User 1
	 *  @return user defined 1
	 */
	public abstract int getUser1_ID(); //  getUser1_ID

	/**
	 *  Get User 2
	 *  @return user defined 2
	 */
	public abstract int getUser2_ID(); //  getUser2_ID

	/**
	 *  Get User Defined Column
	 *  @param ColumnName column name
	 *  @return user defined column value
	 */
	public abstract int getValue(String ColumnName); //  getValue
	
	/**
	 * 	Get Period
	 * 	@return C_Period_ID
	 */
	public int getC_Period_ID();

}