package org.compiere.acct;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MDocType;

public interface IDoc {

	/**	AR Invoices - ARI       */
	public static final String DOCTYPE_ARInvoice = MDocType.DOCBASETYPE_ARInvoice;
	/**	AR Credit Memo          */
	public static final String DOCTYPE_ARCredit = "ARC";
	/**	AR Receipt              */
	public static final String DOCTYPE_ARReceipt = "ARR";
	/**	AR ProForma             */
	public static final String DOCTYPE_ARProForma = "ARF";
	/**	AP Invoices             */
	public static final String DOCTYPE_APInvoice = "API";
	/**	AP Credit Memo          */
	public static final String DOCTYPE_APCredit = "APC";
	/**	AP Payment              */
	public static final String DOCTYPE_APPayment = "APP";
	/**	CashManagement Bank Statement   */
	public static final String DOCTYPE_BankStatement = "CMB";
	/**	CashManagement Cash Journals    */
	public static final String DOCTYPE_CashJournal = "CMC";
	/**	CashManagement Allocations      */
	public static final String DOCTYPE_Allocation = "CMA";
	/** Material Shipment       */
	public static final String DOCTYPE_MatShipment = "MMS";
	/** Material Receipt        */
	public static final String DOCTYPE_MatReceipt = "MMR";
	/** Material Inventory      */
	public static final String DOCTYPE_MatInventory = "MMI";
	/** Material Movement       */
	public static final String DOCTYPE_MatMovement = "MMM";
	/** Material Production     */
	public static final String DOCTYPE_MatProduction = "MMP";
	/** Match Invoice           */
	public static final String DOCTYPE_MatMatchInv = "MXI";
	/** Match PO                */
	public static final String DOCTYPE_MatMatchPO = "MXP";
	/** GL Journal              */
	public static final String DOCTYPE_GLJournal = "GLJ";
	/** Purchase Order          */
	public static final String DOCTYPE_POrder = "POO";
	/** Sales Order             */
	public static final String DOCTYPE_SOrder = "SOO";
	/** Project Issue           */
	public static final String DOCTYPE_ProjectIssue = "PJI";
	/** Purchase Requisition    */
	public static final String DOCTYPE_PurchaseRequisition = "POR";
	//  Posting Status - AD_Reference_ID=234     //
	/**	Document Status         */
	public static final String STATUS_NotPosted = "N";
	/**	Document Status         */
	public static final String STATUS_NotBalanced = "b";
	/**	Document Status         */
	public static final String STATUS_NotConvertible = "c";
	/**	Document Status         */
	public static final String STATUS_PeriodClosed = "p";
	/**	Document Status         */
	public static final String STATUS_InvalidAccount = "i";
	/**	Document Status         */
	public static final String STATUS_PostPrepared = "y";
	/**	Document Status         */
	public static final String STATUS_Posted = "Y";
	/**	Document Status         */
	public static final String STATUS_Error = "E";

	/** No Currency in Document Indicator (-1)	*/
	public static final int  NO_CURRENCY = -2;
	
	/**
	 * 	Get Table Name
	 *	@return table name
	 */
	public abstract String get_TableName(); //	get_TableName

	/**
	 * 	Get Table ID
	 *	@return table id
	 */
	public abstract int get_Table_ID(); //	get_Table_ID

	/**
	 * 	Get Record_ID
	 *	@return record id
	 */
	public abstract int get_ID(); //	get_ID

	/**
	 *  Post Document.
	 *  <pre>
	 *  - try to lock document (Processed='Y' (AND Processing='N' AND Posted='N'))
	 * 		- if not ok - return false
	 *          - postlogic (for all Accounting Schema)
	 *              - create Fact lines
	 *          - postCommit
	 *              - commits Fact lines and Document & sets Processing = 'N'
	 *              - if error - create Note
	 *  </pre>
	 *  @param force if true ignore that locked
	 *  @param repost if true ignore that already posted
	 *  @return null if posted error otherwise
	 */
	public abstract String post(boolean force, boolean repost); //  post

	/**************************************************************************
	 *  Is the Source Document Balanced
	 *  @return true if (source) baanced
	 */
	public abstract boolean isBalanced(); //	isBalanced

	/**
	 *  Is Document convertible to currency and Conversion Type
	 *  @param acctSchema accounting schema
	 *  @return true, if vonvertable to accounting currency
	 */
	public abstract boolean isConvertible(MAcctSchema acctSchema); //	isConvertible

	/**
	 *  Calculate Period from DateAcct.
	 *  m_C_Period_ID is set to -1 of not open to 0 if not found
	 */
	public abstract void setPeriod(); //  setC_Period_ID

	/**
	 * 	Get C_Period_ID
	 *	@return period
	 */
	public abstract int getC_Period_ID(); //	getC_Period_ID

	/**
	 *	Is Period Open
	 *  @return true if period is open
	 */
	public abstract boolean isPeriodOpen(); //	isPeriodOpen

	/**	Amount Type - Invoice - Gross   */
	public static final int AMTTYPE_Gross = 0;
	/**	Amount Type - Invoice - Net   */
	public static final int AMTTYPE_Net = 1;
	/**	Amount Type - Invoice - Charge   */
	public static final int AMTTYPE_Charge = 2;

	/**
	 *	Get the Amount
	 *  (loaded in loadDocumentDetails)
	 *
	 *  @param AmtType see AMTTYPE_*
	 *  @return Amount
	 */
	public abstract BigDecimal getAmount(int AmtType); //	getAmount

	/**
	 *  Get Amount with index 0
	 *  @return Amount (primary document amount)
	 */
	public abstract BigDecimal getAmount(); //  getAmount

	/**
	 *  Get Quantity
	 *  @return Quantity
	 */
	public abstract BigDecimal getQty(); //  getQty

	/**	Account Type - Invoice - Charge  */
	public static final int ACCTTYPE_Charge = 0;
	/**	Account Type - Invoice - AR  */
	public static final int ACCTTYPE_C_Receivable = 1;
	/**	Account Type - Invoice - AP  */
	public static final int ACCTTYPE_V_Liability = 2;
	/**	Account Type - Invoice - AP Service  */
	public static final int ACCTTYPE_V_Liability_Services = 3;
	/**	Account Type - Invoice - AR Service  */
	public static final int ACCTTYPE_C_Receivable_Services = 4;
	/** Account Type - Payment - Unallocated */
	public static final int ACCTTYPE_UnallocatedCash = 10;
	/** Account Type - Payment - Transfer */
	public static final int ACCTTYPE_BankInTransit = 11;
	/** Account Type - Payment - Selection */
	public static final int ACCTTYPE_PaymentSelect = 12;
	/** Account Type - Payment - Prepayment */
	public static final int ACCTTYPE_C_Prepayment = 13;
	/** Account Type - Payment - Prepayment */
	public static final int ACCTTYPE_V_Prepayment = 14;
	/** Account Type - Cash     - Asset */
	public static final int ACCTTYPE_CashAsset = 20;
	/** Account Type - Cash     - Transfer */
	public static final int ACCTTYPE_CashTransfer = 21;
	/** Account Type - Cash     - Expense */
	public static final int ACCTTYPE_CashExpense = 22;
	/** Account Type - Cash     - Receipt */
	public static final int ACCTTYPE_CashReceipt = 23;
	/** Account Type - Cash     - Difference */
	public static final int ACCTTYPE_CashDifference = 24;
	/** Account Type - Allocation - Discount Expense (AR) */
	public static final int ACCTTYPE_DiscountExp = 30;
	/** Account Type - Allocation - Discount Revenue (AP) */
	public static final int ACCTTYPE_DiscountRev = 31;
	/** Account Type - Allocation  - Write Off */
	public static final int ACCTTYPE_WriteOff = 32;
	/** Account Type - Bank Statement - Asset  */
	public static final int ACCTTYPE_BankAsset = 40;
	/** Account Type - Bank Statement - Interest Revenue */
	public static final int ACCTTYPE_InterestRev = 41;
	/** Account Type - Bank Statement - Interest Exp  */
	public static final int ACCTTYPE_InterestExp = 42;
	/** Inventory Accounts  - Differnces	*/
	public static final int ACCTTYPE_InvDifferences = 50;
	/** Inventory Accounts - NIR		*/
	public static final int ACCTTYPE_NotInvoicedReceipts = 51;
	/** Project Accounts - Assets      	*/
	public static final int ACCTTYPE_ProjectAsset = 61;
	/** Project Accounts - WIP         	*/
	public static final int ACCTTYPE_ProjectWIP = 62;
	/** GL Accounts - PPV Offset		*/
	public static final int ACCTTYPE_PPVOffset = 101;
	/** GL Accounts - Commitment Offset	*/
	public static final int ACCTTYPE_CommitmentOffset = 111;

	/**
	 *	Get the Valid Combination id for Accounting Schema
	 *  @param AcctType see ACCTTYPE_*
	 *  @param as accounting schema
	 *  @return C_ValidCombination_ID
	 */
	public abstract int getValidCombination_ID(int AcctType, MAcctSchema as); //	getAccount_ID

	/**
	 *	Get the account for Accounting Schema
	 *  @param AcctType see ACCTTYPE_*
	 *  @param as accounting schema
	 *  @return Account
	 */
	public abstract MAccount getAccount(int AcctType, MAcctSchema as); //	getAccount

	/**
	 *  Get DocLine with ID
	 *  @param Record_ID Record ID
	 *  @return DocLine
	 */
	public abstract IDocLine getDocLine(int Record_ID); //  getDocLine

	/**
	 * 	Get AD_Client_ID
	 *	@return client
	 */
	public abstract int getAD_Client_ID(); //	getAD_Client_ID

	/**
	 * 	Get AD_Org_ID
	 *	@return org
	 */
	public abstract int getAD_Org_ID(); //	getAD_Org_ID

	/**
	 * 	Get Document No
	 *	@return document No
	 */
	public abstract String getDocumentNo(); //	getDocumentNo

	/**
	 * 	Get Description
	 *	@return Description
	 */
	public abstract String getDescription(); //	getDescription

	/**
	 * 	Get C_Currency_ID
	 *	@return currency
	 */
	public abstract int getC_Currency_ID(); //	getC_Currency_ID

	/**
	 * 	Set C_Currency_ID
	 *	@param C_Currency_ID id
	 */
	public abstract void setC_Currency_ID(int C_Currency_ID); //	setC_Currency_ID

	/**
	 * 	Is Multi Currency
	 *	@return mc
	 */
	public abstract boolean isMultiCurrency(); //	isMultiCurrency

	/**
	 * 	Is Tax Included
	 *	@return tax incl
	 */
	public abstract boolean isTaxIncluded(); //	isTaxIncluded

	/**
	 * 	Get C_ConversionType_ID
	 *	@return ConversionType
	 */
	public abstract int getC_ConversionType_ID(); //	getC_ConversionType_ID

	/**
	 * 	Get GL_Category_ID
	 *	@return categoory
	 */
	public abstract int getGL_Category_ID(); //	getGL_Category_ID

	/**
	 * 	Get GL_Category_ID
	 *	@return categoory
	 */
	public abstract int getGL_Budget_ID(); //	getGL_Budget_ID

	/**
	 * 	Get Accounting Date
	 *	@return currency
	 */
	public abstract Timestamp getDateAcct(); //	getDateAcct

	/**
	 * 	Get Document Date
	 *	@return currency
	 */
	public abstract Timestamp getDateDoc(); //	getDateDoc

	/**
	 * 	Is Document Posted
	 *	@return true if posted
	 */
	public abstract boolean isPosted(); //	isPosted

	/**
	 * 	Is Sales Trx
	 *	@return true if posted
	 */
	public abstract boolean isSOTrx(); //	isSOTrx

	/**
	 * 	Get C_DocType_ID
	 *	@return DocType
	 */
	public abstract int getC_DocType_ID(); //	getC_DocType_ID

	/**
	 * 	Get header level C_Charge_ID
	 *	@return Charge
	 */
	public abstract int getC_Charge_ID(); //	getC_Charge_ID

	/**
	 * 	Get SalesRep_ID
	 *	@return SalesRep
	 */
	public abstract int getSalesRep_ID(); //	getSalesRep_ID

	/**
	 * 	Get C_BankAccount_ID
	 *	@return BankAccount
	 */
	public abstract int getC_BankAccount_ID(); //	getC_BankAccount_ID

	/**
	 * 	Get C_CashBook_ID
	 *	@return CashBook
	 */
	public abstract int getC_CashBook_ID(); //	getC_CashBook_ID

	/**
	 * 	Get M_Warehouse_ID
	 *	@return Warehouse
	 */
	public abstract int getM_Warehouse_ID(); //	getM_Warehouse_ID

	/**
	 * 	Get C_BPartner_ID
	 *	@return BPartner
	 */
	public abstract int getC_BPartner_ID(); //	getC_BPartner_ID

	/**
	 * 	Get C_BPartner_Location_ID
	 *	@return BPartner Location
	 */
	public abstract int getC_BPartner_Location_ID(); //	getC_BPartner_Location_ID

	/**
	 * 	Get C_Project_ID
	 *	@return Project
	 */
	public abstract int getC_Project_ID(); //	getC_Project_ID

	/**
	 * 	Get C_SalesRegion_ID
	 *	@return Sales Region
	 */
	public abstract int getC_SalesRegion_ID(); //	getC_SalesRegion_ID

	/**
	 * 	Get C_SalesRegion_ID
	 *	@return Sales Region
	 */
	public abstract int getBP_C_SalesRegion_ID(); //	getBP_C_SalesRegion_ID

	/**
	 * 	Get C_Activity_ID
	 *	@return Activity
	 */
	public abstract int getC_Activity_ID(); //	getC_Activity_ID

	/**
	 * 	Get C_Campaign_ID
	 *	@return Campaign
	 */
	public abstract int getC_Campaign_ID(); //	getC_Campaign_ID

	/**
	 * 	Get M_Product_ID
	 *	@return Product
	 */
	public abstract int getM_Product_ID(); //	getM_Product_ID

	/**
	 * 	Get AD_OrgTrx_ID
	 *	@return Trx Org
	 */
	public abstract int getAD_OrgTrx_ID(); //	getAD_OrgTrx_ID

	/**
	 * 	Get C_LocFrom_ID
	 *	@return loc from
	 */
	public abstract int getC_LocFrom_ID(); //	getC_LocFrom_ID

	/**
	 * 	Get C_LocTo_ID
	 *	@return loc to
	 */
	public abstract int getC_LocTo_ID(); //	getC_LocTo_ID

	/**
	 * 	Get User1_ID
	 *	@return Campaign
	 */
	public abstract int getUser1_ID(); //	getUser1_ID

	/**
	 * 	Get User2_ID
	 *	@return Campaign
	 */
	public abstract int getUser2_ID(); //	getUser2_ID

	/**
	 * 	Get User Defined value
	 *	@return User defined
	 */
	public abstract int getValue(String ColumnName); //	getValue

	/**
	 *  Get Source Currency Balance - subtracts line (and tax) amounts from total - no rounding
	 *  @return positive amount, if total header is bigger than lines
	 */
	public abstract BigDecimal getBalance();

	/**
	 *  Create Facts (the accounting logic)
	 *  @param as accounting schema
	 *  @return Facts
	 */
	public abstract ArrayList<Fact> createFacts(MAcctSchema as);

	/**
	 * 	Get Context
	 *	@return context
	 */
	public Properties getCtx();

	/**************************************************************************
	 *  Load Document Type and GL Info.
	 * 	Set p_DocumentType and p_GL_Category_ID
	 * 	@return document type
	 */
	public String getDocumentType();
	
	/**
	 * 	Set C_SalesRegion_ID
	 *	@param C_SalesRegion_ID id
	 */
	public void setBP_C_SalesRegion_ID (int C_SalesRegion_ID);
	
	
	/*
	 * Get the facts
	 * @return ArrayList<Fact>
	 */
	public ArrayList<Fact> getFacts();
	
}