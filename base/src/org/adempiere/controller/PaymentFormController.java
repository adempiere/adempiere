/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2019 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.adempiere.controller;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.controller.ed.CPaymentEditor;
import org.compiere.model.GridTab;
import org.compiere.model.MBankAccount;
import org.compiere.model.MConversionRate;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.MPayment;
import org.compiere.model.MPaymentValidate;
import org.compiere.model.MRole;
import org.compiere.model.X_C_Bank;
import org.compiere.model.X_C_Invoice;
import org.compiere.model.X_C_Order;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.compiere.util.ValueNamePair;

/**
 * A controller for the VPayment and WPayment dialogs.  These are 
 * helper functions that allow the user to take payments from customers
 * in place of opening the Payment window.
 * <p>
 * The controller attempts to identify the orders, invoices and payments 
 * involved but only as they relate to the calling document. The initial 
 * payment amount will be the open amount of all related invoices less 
 * any unallocated payments OR the difference between the Grand Total 
 * and all payments.  Payments can be taken in succession as partial 
 * payments or as a single payment or over-payment. Users should be 
 * cautioned to avoid taking payments using this method if the payment
 * amount is less than or equal to zero.
 * <p>
 * Cash payments are accepted as payment to a "Cash" bank account or cash
 * journal.
 * <p>
 * Payments are completed and allocated if a single invoice is identified
 * or can be found. If multiple invoices exist, the payment will have to be
 * allocated manually.
 * <p>
 * Online processes can be defined but these are only functioning for the
 * credit cards.  Payment processors have to be in place for this to
 * work.  Direct Deposit and Direct Debit do not have online processes 
 * enabled
 * 
 * 
 * @author Michael McKay, mckayERP@gmail.com
 * 		<br>Copied largely from previous work in VPayment and WPayment.
 * 		<br>Changed the logic to allow multiple payments
 *
 */
public class PaymentFormController {
	
	public static final String MSG_PaymentCreateConfirmation = "PaymentFormController: PaymentCreateConfirmation";
	public static final String MSG_PaymentError = "PaymentError";
	public static final String MSG_PaymentCreated = "PaymentCreated";
	public static final String MSG_PaymentProcessed = "PaymentProcessed";
	public static final String MSG_CashJournal = "PaymentFormController: CashJournal";
	public static final String MSG_CashJournalTip = "PaymentFormController: CashJournalTip";
	private static final String MSG_PaymentNotCreated = "PaymentFormController: PaymentNotCreated";
	private static final String MSG_PaymentNotCompletedAfterProcessApproval = "PaymentFormController: PaymentNotCompletedAfterProcessApproval";
	private static final String MSG_PaymentNotProcessed = "PaymentFormController: PaymentNotProcessed";
	private static final String MSG_NoBusinessPartnerFound = "PaymentFormController: NoBusinessPartnerFound";
	private static final String MSG_DocumentReverserdOrVoided = "PaymentFormController: DocumentReversedOrVoided";
	private static final String Msg_NoCashJournalSelected = "PaymentFormController: NoCashJournalSelected";
	private static final String MSG_NoCreditCardTypeSelected = "PaymentFormController: NoCreditCardTypeSelected";
	private static final String MSG_NoBPBankAccountSelected = "PaymentFormController: NoBPBankAccountSelected";
	private static final String MSG_ZeroPaymentAmount = "PaymentFormController: ZeroPaymentAmount";

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(PaymentFormController.class);

	
	/**	Window						*/
	private int                 windowNo = 0;
	/**	Tab							*/
	private GridTab        		gridTab;
	/** Is SOTrx					*/
	private boolean				isSOTrx = true;
	/** Doc Status					*/
	private String              docStatus = null;
	/** Only change the payment rule - don't accept payments */
	private boolean 			onlyChangePaymentRule;
	/** Invoice Currency              */
	private int	 				c_currency_id = 0;
	/** Client							*/
	private int                 ad_client_id = 0;
	/** Organization					*/
	private int                 ad_org_id = 0;
	/** Business Partner				*/
	private int                 c_bpartner_id = 0;
	/** Amount of payment				*/
	private BigDecimal			paymentAmount = Env.ZERO;
	/** Start Payment Rule          */
	private String				paymentRule = "";
	/** Start Payment Term          */
	private int					c_paymentTerm_id = 0;
	/** Start Acct Date             */
	private Timestamp			dateAcct = null;
	/** Placeholder payment. Used to find the list of available credit cards */
	private MPayment            payment = null;
	/** Start CreditCard            */
	private String              ccType = "";
	/** Start Bank Account			*/
	private int					c_bankAccount_id = 0;
	/** Starting CashBook              */
	private int                 cashBook_id = 0; // refers to c_bankAccount_id where BankType=Cash
	
	// Place holders for the user-entered values
	private String new_paymentRule = "";
	private Timestamp new_dateAcct = null;
	private int new_c_paymentTerm_id = 0;
	private int new_cashBook_id = 0;
	private String new_ccType = "";
	private String new_ccNumber = "";
	private String new_ccExpDate = "";
	private int new_c_bankAccount_id = 0;
	private int new_c_bp_bankAccount_id = 0;
	private BigDecimal new_paymentAmount = Env.ZERO;
	private String new_checkRoutingNumber = "";
	private String new_checkAccountNumber = "";
	private String new_checkNumber = "";
	private String new_ccName = "";

	/** EMU Currencies				*/
	private static Hashtable<Integer,KeyNamePair> currencies = null;	

	/** Controller error message	*/
	private String				errorMsg = "";

	private String onlineInfo = "";

	private HashMap<String, String> paymentRuleValues;

	// Holders for the currently selected values
	private ValueNamePair selectedPaymentRule;
	private KeyNamePair selectedPaymentTerm;
	private ValueNamePair selectedCreditCard;
	private KeyNamePair selectedBankAccount;
	private KeyNamePair selectedCashBook;
	
	/** The editor calling this controller */
	private CPaymentEditor editor;

	/** The payment document number generated once the payment is saved */
	private String paymentDocNumber;

	/** The order this payment will refer to */
	private int c_order_id;

	/** The invoice this payment will refer to */
	private int c_invoice_id;

	/** A list of invoices that may be associated with the order */
	private List<MInvoice> invoices = new ArrayList<MInvoice>();
	
	/** A list of payments that may be associated with the order */
	private List<MPayment> payments = new ArrayList<MPayment>();


	/** The total amount open on all the invoices */
	private BigDecimal invoiceAmountOpen = Env.ZERO;

	/** The grand total on the document */
	private BigDecimal grandTotal = Env.ZERO;

	/** The total amount paid on the document */
	private BigDecimal paidAmount = Env.ZERO;

	/** The amount paid that has been allocated */
	private BigDecimal paidAmountAllocated = Env.ZERO;

	/** The amount paid that remains unallocated */
	private BigDecimal paidAmountUnallocated = Env.ZERO;

	/** The total amount invoices related to this document */
	private BigDecimal invoiceAmount = Env.ZERO;

	/** A flag, true if the document is an order */
	private boolean isOrder = false;

	/** A flag, true if the document is an invoice */
	private boolean isInvoice = false;
	private boolean processOnline = false;
	private String errorInfo;
	

	/**
	 * 
	 */
	public PaymentFormController(CPaymentEditor editor, int WindowNo, GridTab tab, HashMap<String, String> buttonValues) {
		
		this.editor = editor;
		windowNo = WindowNo;
		gridTab = tab;
		paymentRuleValues = buttonValues;
		
	}
	
	public boolean init() {

		//  Check the restrictions.
		//  We need a business partner - the payment, if any, will be made to 
		//  the same business partner.  If the BP can't be identified, its 
		//  a problem.
		if (gridTab.getValue("C_BPartner_ID") == null)
		{
			errorMsg = MSG_NoBusinessPartnerFound;
			return false;
		}

		// Set the dateAcct to today/now
		dateAcct = getDate();
		
		//  DocStatus
		docStatus = (String)gridTab.getValue("DocStatus");
		log.config(docStatus);
		if (docStatus == null)
			docStatus = "";

		//	Is the Trx Reversed / Voided ?  Don't accept payments referencing it.
		// SHouldn't be able to click the button but incase
		if (docStatus.equals("RE") || docStatus.equals("VO"))
		{
			
			errorMsg = MSG_DocumentReverserdOrVoided;
			return false;
			
		}
		
		paymentRule = (String)gridTab.getValue("PaymentRule");
		String payTypes = "KTSDBP";
		if (!payTypes.contains(paymentRule) || paymentRule == null || paymentRule.isEmpty())
		{
		
			// The payment rule is not allowed or unknown
			// Don't flag an error - allow the user to correct it
			paymentRule = X_C_Order.PAYMENTRULE_Check;
			
		}

		//  Sales transaction?
		isSOTrx = "Y".equals(Env.getContext(Env.getCtx(), windowNo, "IsSOTrx"));
		grandTotal = ((BigDecimal) gridTab.getValue("GrandTotal"));		

		//  Document is not complete - allow a change to the Payment Rule only
		//  OR a PO  OR the total is zero
		onlyChangePaymentRule = false;
		if (!docStatus.equals("CO") && !docStatus.equals("WP") 
			|| !isSOTrx && gridTab.getTableName().equals("C_Order") 
			|| grandTotal.compareTo(Env.ZERO) == 0)
		{
			
			onlyChangePaymentRule = true;
			
		}

		//	Get Data from Grid
		ad_client_id = ((Integer)gridTab.getValue("AD_Client_ID")).intValue();
		ad_org_id = ((Integer)gridTab.getValue("AD_Org_ID")).intValue();
		c_bpartner_id = ((Integer)gridTab.getValue("C_BPartner_ID")).intValue();
		c_currency_id = ((Integer)gridTab.getValue("C_Currency_ID")).intValue();
		
		if (gridTab.getValue("C_PaymentTerm_ID") != null)
		{

			c_paymentTerm_id = ((Integer)gridTab.getValue("C_PaymentTerm_ID")).intValue();
			
		}

		//  Since we are taking a payment, set the dateAcct to the current time
		dateAcct = new Timestamp(System.currentTimeMillis());
		
		if (currencies == null)
			loadCurrencies();

		//  Try to figure out what is owing based on incomplete info
		//  We are only looking at this document, not the Business Partner's 
		//  credit status and balance owing.
		
		//  Get Order and optionally Invoice
		isOrder = gridTab.getTableName().equals(X_C_Order.Table_Name);
		isInvoice = gridTab.getTableName().equals(X_C_Invoice.Table_Name);
		
		//  If this is an order or invoice, try to find the associated invoices and payments
		//  to determine the amounts yet to pay.
		if (isOrder)
		{
			// One order, many invoices and payments
			c_order_id = ((Integer)gridTab.getValue("C_Order_ID")).intValue();
			
			// Invoices
			c_invoice_id = 0;
			for (MInvoice inv : MInvoice.getOfOrder(Env.getCtx(), c_order_id, null))
			{
				if (!inv.isPaid())
					invoices.add(inv);
				// Adjust for credit memos
				invoiceAmountOpen = invoiceAmountOpen.add(inv.getOpenAmt(true, null));
				invoiceAmount = invoiceAmount.add(inv.getGrandTotal(true));
			}
			if (invoices.size() == 1)
				c_invoice_id = invoices.get(0).getC_Invoice_ID();
			
		}
		else if (isInvoice)
		{
			//  One invoice, one or no orders and possibly many payments
			//  The only connection between unallocated payments and the invoice
			//  is the order, otherwise, there is no way of knowing without
			//  involving the entire business partner account activity.  In the 
			//  payment dialog, we are just focused on this one document.
			c_invoice_id =  ((Integer)gridTab.getValue("C_Invoice_ID")).intValue();
			MInvoice invoice = new MInvoice(Env.getCtx(), c_invoice_id, null);
			invoices.add(invoice);
			invoiceAmount = invoice.getGrandTotal(true);
			invoiceAmountOpen = invoice.getOpenAmt(true, null);
			
			c_order_id = invoice.getC_Order_ID();
			
		}

		payments = new ArrayList<MPayment>();
		paidAmount = Env.ZERO;
		paidAmountAllocated = Env.ZERO;
		paidAmountUnallocated = Env.ZERO;

		if (c_order_id > 0)
		{
			// Payments
			for (MPayment pmt : MPayment.getOfOrder(Env.getCtx(), c_order_id, null))
			{
				payments.add(pmt);
				paidAmount = paidAmount.add(pmt.getPayAmt());
				if (pmt.isAllocated())
					paidAmountAllocated = paidAmountAllocated.add(pmt.getPayAmt());
				else
					paidAmountUnallocated = paidAmountUnallocated.add(pmt.getPayAmt());
			}				
		}

		// Order with no invoice - prepayment
		if (c_order_id > 0 && invoiceAmount.equals(Env.ZERO))
		{

			paymentAmount = grandTotal.subtract(paidAmount);
			
		}
		else // No order or a non-zero invoice amount.
		{
			paymentAmount = invoiceAmountOpen.subtract(paidAmountUnallocated);			
		}
				
		//  Create a dummy payment which is required to determine the payment processor
		//  and possible credit card types available.
		payment = new MPayment(Env.getCtx(), 0, null);
		payment.setAmount(c_currency_id, paymentAmount);

		return true;
	}

	/**
	 * @return the errorMsg or empty string
	 */
	public String getErrorMsg() {
		if (errorMsg == null || errorMsg.isEmpty())
			return "";
		
		String message = Msg.translate(Env.getCtx(), errorMsg);
		if (errorInfo != null && !errorInfo.isEmpty())
			message += " " + errorInfo;
		
		return message;
	}

	/**
	 *	Fill Currencies with EMU currencies
	 */
	private void loadCurrencies()
	{
		
		currencies = new Hashtable<Integer,KeyNamePair>(12);	//	Currenly only 10+1
		String SQL = "SELECT C_Currency_ID, ISO_Code FROM C_Currency "
			+ "WHERE (IsEMUMember='Y' AND EMUEntryDate<SysDate) OR IsEuro='Y' "
			+ "ORDER BY 2";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(SQL, null);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				int id = rs.getInt(1);
				String name = rs.getString(2);
				currencies.put(new Integer(id), new KeyNamePair(id, name));
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, SQL, e);
		}
	}	//	loadCurrencies

	public boolean isEMUCurrency() {
		
		if (currencies == null)
			loadCurrencies();
		
		//	Is the currency an EMU currency?
		Integer C_Currency_ID = new Integer(c_currency_id);
		return currencies.containsKey(C_Currency_ID);
	}

	public Hashtable<Integer, KeyNamePair> getCurrencies() {
		
		if (currencies == null)
			loadCurrencies();

		return currencies;
	}

	public KeyNamePair getCurrentCurrency() {
		
		if (currencies != null)
			return currencies.get(c_currency_id);
		
		return null;
	}

	public ArrayList<ValueNamePair> getPaymentRules() {
		
		ArrayList<ValueNamePair> rules = new ArrayList<ValueNamePair>();
		Object[] a = paymentRuleValues.keySet().toArray();
		selectedPaymentRule = null;
		
		for (int i = 0; i < a.length; i++)
		{			
			String PaymentRule = (String)a[i];		//	used for Panel selection
			if (X_C_Order.PAYMENTRULE_DirectDebit.equals(PaymentRule)			//	SO
				&& !isSOTrx)
				continue;
			else if (X_C_Order.PAYMENTRULE_DirectDeposit.equals(PaymentRule)	//	PO 
				&& isSOTrx)
				continue;
			
			else if (X_C_Order.PAYMENTRULE_Mixed.equals(PaymentRule)) // Mixed is not implemented
				continue;
                                                
			ValueNamePair pp = new ValueNamePair(PaymentRule, (String)paymentRuleValues.get(a[i]));
			rules.add(pp);
			if (PaymentRule.toString().equals(paymentRule))	//	to select
				selectedPaymentRule = pp;
		}
		
		return rules;

	}

	/**
	 * @return the selectedPaymentRule
	 */
	public ValueNamePair getSelectedPaymentRule() {
		return selectedPaymentRule;
	}

	public String whichPanel(ValueNamePair pp) {
		String s = pp.getValue().toLowerCase();
		if (X_C_Order.PAYMENTRULE_DirectDebit.equalsIgnoreCase(s))
			s = X_C_Order.PAYMENTRULE_DirectDeposit.toLowerCase();
		s += "Panel";
		return s;
	}
	
	/**
	 * Get the list of available payment terms
	 * @return
	 */
	public ArrayList<KeyNamePair> getPaymentTerms() {

		ArrayList<KeyNamePair> paymentTerms = new ArrayList<KeyNamePair>();
		
		selectedPaymentTerm = null;
		
		// 	Load Payment Terms
		String SQL = MRole.getDefault().addAccessSQL(
			"SELECT C_PaymentTerm_ID, Name FROM C_PaymentTerm WHERE IsActive='Y' ORDER BY Name",
			"C_PaymentTerm", MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(SQL, null);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				int key = rs.getInt(1);
				String name = rs.getString(2);
				KeyNamePair pp = new KeyNamePair(key, name);
				paymentTerms.add(pp);
				if (key == c_paymentTerm_id)
					selectedPaymentTerm = pp;
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException ept)
		{
			log.log(Level.SEVERE, SQL, ept);
		}

		return paymentTerms;
	}

	/**
	 * @return the selectedPaymentTerm
	 */
	public KeyNamePair getSelectedPaymentTerm() {
		return selectedPaymentTerm;
	}
	
	/**
	 * Get the list of business partner accounts
	 * @return a list of accounts
	 */
	public ArrayList<KeyNamePair> getBPAccounts() {
		
		ArrayList<KeyNamePair> accounts = new ArrayList<KeyNamePair>();
		
		// 	Load Accounts
		String SQL = "SELECT a.C_BP_BankAccount_ID, NVL(b.Name, ' ')||'_'||NVL(a.AccountNo, ' ') AS Acct "
			+ "FROM C_BP_BankAccount a"
			+ " LEFT OUTER JOIN C_Bank b ON (a.C_Bank_ID=b.C_Bank_ID) "
			+ "WHERE a.C_BPartner_ID=?"
			+ "AND a.IsActive='Y' AND a.IsACH='Y'";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(SQL, null);
			pstmt.setInt(1, c_bpartner_id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				int key = rs.getInt(1);
				String name = rs.getString(2);
				KeyNamePair pp = new KeyNamePair(key, name);
				accounts.add(pp);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException eac)
		{
			log.log(Level.SEVERE, SQL, eac);
		}

		return accounts;
	}
	
	/** 
	 * Get the list of available credit cards.
	 * @return a list of credit cards.
	 */
	public ArrayList<ValueNamePair> getCreditCards() {
		
		ArrayList<ValueNamePair> cards = new ArrayList<ValueNamePair>();

		if (payment == null)
			return cards;
		
		selectedCreditCard = null;
		//	Load Credit Cards
		ValueNamePair[] ccs = payment.getCreditCards();
		for (int i = 0; i < ccs.length; i++)
		{
			cards.add(ccs[i]);
			if (ccs[i].getValue().equals(ccType))
				selectedCreditCard = ccs[i];
		}

		return cards;
		
	}

	/**
	 * @return the selecteCreditCard
	 */
	public ValueNamePair getSelecteCreditCard() {
		return selectedCreditCard;
	}

	/**
	 * Get a list of bank accounts
	 * @return a list of bank accounts
	 */
	public ArrayList<KeyNamePair> getBankAccounts() {
		return getBankAccounts(false);
	}
	
	/**
	 * Get a set of bank accounts or cash journals
	 * @param cashJournal - set to true for cash journals, false for bank accounts
	 * @return a List of bank accounts that represent regular bank accounts or cash journals.
	 */
	private ArrayList<KeyNamePair> getBankAccounts(boolean cashJournal) {

		ArrayList<KeyNamePair> accounts = new ArrayList<KeyNamePair>();
		
		String bankType = X_C_Bank.BANKTYPE_Bank;
		if (cashJournal)
		{
			
			bankType = X_C_Bank.BANKTYPE_CashJournal;
		}
		
		//  Load Bank Accounts
		String SQL = MRole.getDefault().addAccessSQL(
			"SELECT C_BankAccount_ID, ba.accountno, IsDefault "
			+ "FROM C_BankAccount ba"
			+ " INNER JOIN C_Bank b ON (ba.C_Bank_ID=b.C_Bank_ID) "
			+ "WHERE b.IsActive='Y'"
			+ " AND b.BankType=?",
			"ba", MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO);
		selectedBankAccount = null;
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(SQL, null);
			pstmt.setString(1, bankType);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				int key = rs.getInt(1);
				String name = rs.getString(2);
				KeyNamePair pp = new KeyNamePair(key, name);
				accounts.add(pp);
				if (cashJournal)
				{
					
					if (key == c_bankAccount_id)
						selectedCashBook = pp;
					if (selectedCashBook == null && rs.getString(3).equals("Y"))    //  Default
						selectedCashBook = pp;

				}
				else
				{
					
					if (key == c_bankAccount_id)
						selectedBankAccount = pp;
					if (selectedBankAccount == null && rs.getString(3).equals("Y"))    //  Default
						selectedBankAccount = pp;
					
				}
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException ept)
		{
			log.log(Level.SEVERE, SQL, ept);
		}
		
		return accounts;
	}

	/**
	 * @return the selectedBankAccount
	 */
	public KeyNamePair getSelectedBankAccount() {
		return selectedBankAccount;
	}
	
	/**
	 * Get the cash books or cash journals
	 * @return
	 */
	public ArrayList<KeyNamePair> getCashBooks() {
		
		return getBankAccounts(true);
		
	}

	/**
	 * @return the selectedCashBook
	 */
	public KeyNamePair getSelectedCashBook() {
		return selectedCashBook;
	}

	/**
	 * Convert the current payment amount to a different currency
	 * @param currencyTo_id - the currency target
	 * @return the converted amount.
	 */
	public BigDecimal getConvertedAmount(int currencyTo_id) {

		return MConversionRate.convert(Env.getCtx(),
				paymentAmount, c_currency_id, currencyTo_id, ad_client_id, ad_org_id);
	}

	/**
	 * Use the changes to the fields to create a new payment and complete it. 
	 * @return
	 */
	public boolean saveChanges() {
				
		errorMsg = "";
		errorInfo = "";

		if (!checkMandatory()) // Also updates all values
			return false;
		
		// BF [ 1920179 ] perform the save in a trx's context.
		final boolean[] success = new boolean[] { false };
		final TrxRunnable r = new TrxRunnable() {

			public void run(String trxName) {
				success[0] = saveChangesInTrx(trxName);
			}
		};
		try {
			Trx.run(r);
		} catch (Throwable e) {
			success[0] = false;
			errorMsg = e.getLocalizedMessage();
		}
		if (payment != null)
			payment.set_TrxName(null);
		return success[0];
	}
	
	/**************************************************************************
	 *	Save Changes
	 *	@return true, if window can exit
	 */
	private boolean saveChangesInTrx(final String trxName)
	{
		
		String new_paymentRule = editor.getPaymentRule();
		log.info("Payment Rule: " + new_paymentRule);

		String payTypes = "KTSDBP";
		if (!payTypes.contains(new_paymentRule) || new_paymentRule == null || new_paymentRule.isEmpty())
		{
			
			throw new IllegalArgumentException("Payment Rule is null, empty or not recognized.");
			
		}

		/**********************
		 *	Save Values to mTab
		 */
		log.config("Saving changes");

		//  Payment Rule
		if (!new_paymentRule.equals(paymentRule))
			gridTab.setValue("PaymentRule", new_paymentRule);
		if (onlyChangePaymentRule)
		{
			return true;
		}		
		
		// On Credit - update the payment term
		if (new_paymentRule.equals(MOrder.PAYMENTRULE_OnCredit))
		{
			if (!new_paymentRule.equals(paymentRule))
				gridTab.setValue("PaymentRule", new_paymentRule);
			if (new_c_paymentTerm_id != c_paymentTerm_id)
				gridTab.setValue("C_PaymentTerm_ID", new_c_paymentTerm_id);
			return true;
		}
		
		//  Create a payment
		BigDecimal payAmount = new_paymentAmount;
			
		
		// Check the date
		if (new_dateAcct == null)
			new_dateAcct = dateAcct;

		if (isInvoice)
		{
			MInvoice invoice = invoices.get(0);
			if (invoice.isCreditMemo())
				payAmount = payAmount.negate();
		}
	
		// Info
		log.config("C_Order_ID=" + c_order_id + ", C_Invoice_ID=" + c_invoice_id + ", Amt=" + payAmount);		
		
		/***********************
		 *  Create Payments
		 */

		log.fine("Creating new Payment - " + new_paymentRule);

		//  Set Amount
		payment = new MPayment(Env.getCtx(), 0, trxName);
		
		payment.setC_BPartner_ID(c_bpartner_id);
		payment.setC_Invoice_ID(c_invoice_id);
		payment.setC_Order_ID(c_order_id);
		payment.setDateTrx(new_dateAcct);
		payment.setDateAcct(new_dateAcct);
		payment.setAmount(c_currency_id, payAmount);

		if (new_paymentRule.equals(MOrder.PAYMENTRULE_CreditCard))
		{
			payment.setCreditCard(MPayment.TRXTYPE_Sales, new_ccType,
				new_ccNumber, "", new_ccExpDate);
			payment.setA_Name(new_ccName);
		}
		else if (new_paymentRule.equals(MOrder.PAYMENTRULE_DirectDeposit)
			|| new_paymentRule.equals(MOrder.PAYMENTRULE_DirectDebit))
		{
			MBankAccount bpBankAccount = new MBankAccount(Env.getCtx(), new_c_bp_bankAccount_id, null);
			payment.setBankACH(new_c_bp_bankAccount_id, isSOTrx, new_paymentRule, 
				bpBankAccount.getBank().getRoutingNo(), bpBankAccount.getAccountNo());
		}
		else if (new_paymentRule.equals(MOrder.PAYMENTRULE_Check))
		{
			payment.setBankCheck(new_c_bankAccount_id, isSOTrx, new_checkRoutingNumber,
				new_checkAccountNumber, new_checkNumber);
		}
		else if (new_paymentRule.equals(MOrder.PAYMENTRULE_Cash))
		{
			// Get changes to cash amount
			payment.setBankCash(new_cashBook_id, isSOTrx, MPayment.TENDERTYPE_Cash);
		}
		
		if (!payment.save()) {
			log.warning("Unable to save draft payment! Payment deleted.");
			errorMsg = MSG_PaymentNotCreated;
			errorInfo = payment.getErrorMessage();
			payment.deleteEx(true);
			payment = null;
			return false;
		}
		
		boolean approved = false;
		if (processOnline )
		{
			log.config("Processing payment on-line");
			approved = payment.processOnline();
			onlineInfo = payment.getR_RespMsg() + " (" + payment.getR_AuthCode()
				+ ") ID=" + payment.getR_PnRef();
			processOnline = false;
			
			if (!approved)
			{
				log.warning("Unable to save process payment online! Payment (deleted): " + payment.toString());
				errorMsg = MSG_PaymentNotProcessed;
				errorInfo = payment.getErrorMessage();
				payment.delete(true);
				payment = null;
				return false;
			}
		}

		boolean ok = payment.processIt(DocAction.ACTION_Complete);
		
		if (!ok)
		{
			if (approved) 
			{ 
				//  This is a problem that will need human intervention
				//  The payment was processed through an online processor
				//  and approved, but the payment could not be completed.
				//  Save the payment, as it has a record of the online 
				//  process and let the user figure out what when wrong.
				errorMsg = MSG_PaymentNotCompletedAfterProcessApproval;
				payment.saveEx();
				log.severe("Payment approved online but not completed! Payment exists: " + payment.toString() );
			}
			else
			{
				//  No online process but something went wrong.  Delete the payment.
				//  Check the logs or any exceptions for info.
				log.warning("Unable to complete payment! Payment (deleted): " + payment.toString());
				errorMsg = MSG_PaymentNotCreated;
				errorInfo = payment.getErrorMessage();
				payment.delete(true);
				payment = null;
			}
			return false;
		}

		payment.saveEx();
		paymentDocNumber = payment.getDocumentNo();
		log.config("Payment completed: " + payment.toString());
				
		//	Set Payment
		gridTab.setValue("C_Payment_ID", new Integer(payment.getC_Payment_ID()));
		
		if (errorMsg.isEmpty())
			return true;
		else
			return false;
	}	//	saveChanges

	/**
	 * @return the paymentDocNumber
	 */
	public String getPaymentDocNumber() {
		return paymentDocNumber;
	}

	/**
	 *  Check Mandatory
	 *  @return true if all mandatory items are OK
	 */
	public boolean checkMandatory()
	{
		log.config( "");

		//  only Payment Rule
		if (onlyChangePaymentRule)
			return true;
		
		getValues();

		/***********************
		 *	Mandatory Data Check
		 */

		
		boolean dataOK = true;
		// paymentAmount
		editor.setMandatory(CPaymentEditor.FIELD_bAmount, true);
		editor.setMandatory(CPaymentEditor.FIELD_kAmount, true);
		editor.setMandatory(CPaymentEditor.FIELD_sAmount, true);
		editor.setMandatory(CPaymentEditor.FIELD_tAmount, true);
		if (new_paymentAmount.equals(Env.ZERO))
		{
			editor.setError(CPaymentEditor.FIELD_bAmount, true);
			editor.setError(CPaymentEditor.FIELD_kAmount, true);
			editor.setError(CPaymentEditor.FIELD_sAmount, true);
			editor.setError(CPaymentEditor.FIELD_tAmount, true);
			errorMsg = MSG_ZeroPaymentAmount;
			dataOK = false;
		}

		//	B (Cash)		(Currency)
		if (new_paymentRule.equals(MOrder.PAYMENTRULE_Cash))
		{
			editor.setMandatory(CPaymentEditor.FIELD_bCashBook, true);
			if (new_cashBook_id == 0)
			{
				editor.setError(CPaymentEditor.FIELD_bCashBook, true);
				errorMsg = Msg_NoCashJournalSelected;
				dataOK = false;
			}
			else
				editor.setError(CPaymentEditor.FIELD_bCashBook, false);
		}

		//	K (CreditCard)  Type, Number, Exp, Approval
		else if (new_paymentRule.equals(MOrder.PAYMENTRULE_CreditCard))
		{
			editor.setMandatory(CPaymentEditor.FIELD_kType, true);
			if (new_ccType.isEmpty())
			{
				errorMsg = MSG_NoCreditCardTypeSelected;
				editor.setError(CPaymentEditor.FIELD_kType, true);
				dataOK = false;
			}
			// Validation of the credit card number is moved to the payment processor.
			// Different payment processors can have different validation rules.
		}

		//	T (Transfer)	BPartner_Bank
		else if (new_paymentRule.equals(X_C_Order.PAYMENTRULE_DirectDeposit)
			|| new_paymentRule.equals(X_C_Order.PAYMENTRULE_DirectDebit))
		{
			editor.setMandatory(CPaymentEditor.FIELD_tAccount, true);
			if (new_c_bp_bankAccount_id == 0)
			{
				errorMsg = MSG_NoBPBankAccountSelected;
				editor.setError(CPaymentEditor.FIELD_tAccount, true); 
				dataOK = false;
			}
		}	//	Direct
		//      P (PaymentTerm) PaymentTerm 	 
        else if (new_paymentRule.equals(X_C_Order.PAYMENTRULE_OnCredit))
        {
            // ok
        	editor.setMandatory(CPaymentEditor.FIELD_pTerm, true);
        }
		//	S (Check)		(Currency) CheckNo, Routing
		else if (new_paymentRule.equals(MOrder.PAYMENTRULE_Check))
		{
			editor.setMandatory(CPaymentEditor.FIELD_sBankAccount, true);
			editor.setMandatory(CPaymentEditor.FIELD_sRouting, true);
			editor.setMandatory(CPaymentEditor.FIELD_sAccountNumber, true);
			editor.setMandatory(CPaymentEditor.FIELD_sCheckNumber, true);
			if (new_c_bankAccount_id == 0)
			{
				editor.setError(CPaymentEditor.FIELD_sBankAccount, true);
				errorMsg = "PaymentNoProcessor";
				dataOK = false;
			}

			errorMsg = MPaymentValidate.validateRoutingNo(new_checkRoutingNumber);
			if (!errorMsg.isEmpty())
			{
				editor.setError(CPaymentEditor.FIELD_sRouting, true);
				dataOK = false;
			}
			errorMsg = MPaymentValidate.validateAccountNo(new_checkAccountNumber);
			if (!errorMsg.isEmpty())
			{
				editor.setError(CPaymentEditor.FIELD_sAccountNumber, true);
				dataOK = false;
			}
			errorMsg = MPaymentValidate.validateCheckNo(new_checkNumber);
			if (!errorMsg.isEmpty())
			{
				editor.setError(CPaymentEditor.FIELD_sCheckNumber, true);
				dataOK = false;
			}
		}
		else
		{
			log.log(Level.SEVERE, "Unknown PaymentRule " + new_paymentRule);
			errorMsg = "Unknown PaymentRule " + new_paymentRule;
			return false;
		}

		//
		log.config("OK=" + dataOK);
		return dataOK;
	}   //  checkMandatory

	/**
	 * Process the payment online.  The results of the online payment
	 * can be read in {@link #getOnlineInfo()}.
	 * @return true if successful, false if there was an error.
	 */
	public boolean processOnline() {
		
		processOnline = true;
		
		return saveChanges();
		
	}

	/**
	 * @return the onlineInfo
	 */
	public String getOnlineInfo() {
		return onlineInfo;
	}

	private void getValues() {
		
		//	New Values
		new_dateAcct = dateAcct; 
		new_c_paymentTerm_id = c_paymentTerm_id;
		new_cashBook_id = cashBook_id;
		new_ccType = ccType;
		new_ccName = "";
		new_ccNumber = "";
		new_ccExpDate = "";
		new_c_bankAccount_id = 0;
		new_c_bp_bankAccount_id = 0;
		new_paymentAmount = Env.ZERO;
		new_checkRoutingNumber = "";
		new_checkAccountNumber = "";
		new_checkNumber = "";

		new_dateAcct = editor.getDateAcct(new_paymentRule); 
		new_paymentRule = editor.getPaymentRule();
		new_c_bankAccount_id = editor.getBankAccount(new_paymentRule);
		new_cashBook_id = editor.getCashBook(new_paymentRule);
		new_dateAcct = editor.getDateAcct(new_paymentRule);
		new_paymentAmount = editor.getPaymentAmount(new_paymentRule);
		new_ccType = editor.getCreditCardType(new_paymentRule);
		new_ccName = editor.getCreditCardName(new_paymentRule);
		new_ccNumber = editor.getCreditCardNumber(new_paymentRule);
		new_ccExpDate = editor.getCreditCardExpiry(new_paymentRule);
		new_c_bp_bankAccount_id = editor.getBPBankAccount(new_paymentRule);
		new_c_paymentTerm_id = editor.getPaymentTerm(new_paymentRule);
		new_checkAccountNumber = editor.getCheckAccountNumber(new_paymentRule);
		new_checkRoutingNumber = editor.getCheckRoutingNumber(new_paymentRule);
		new_checkNumber = editor.getCheckNumber(new_paymentRule);

	}

	/**
	 *  Get the current date/time
	 * @return the current date/time
	 */
	public Timestamp getDate() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * @return the paymentAmount
	 */
	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	/**
	 * 
	 * @return true if only the payment rule can change
	 */
	public boolean isOnlyChangePaymentRule() {
		return onlyChangePaymentRule;
	}

}
