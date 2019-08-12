package com.klst.adempiere.einvoice;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.compiere.model.I_C_Tax;
import org.compiere.model.MBPBankAccount;
import org.compiere.model.MBPartner;
import org.compiere.model.MBank;
import org.compiere.model.MBankAccount;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MInvoiceTax;
import org.compiere.model.MOrg;
import org.compiere.model.MOrgInfo;
import org.compiere.model.MPaymentTerm;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.w3c.dom.Document;

import com.klst.einvoice.CoreInvoiceVatBreakdown;
import com.klst.einvoice.CreditTransfer;
import com.klst.einvoice.DirectDebit;
import com.klst.einvoice.PaymentCard;
import com.klst.einvoice.unece.uncefact.Amount;
import com.klst.einvoice.unece.uncefact.BICId;
import com.klst.einvoice.unece.uncefact.IBANId;
import com.klst.marshaller.AbstactTransformer;
import com.klst.untdid.codelist.PaymentMeansEnum;
import com.klst.untdid.codelist.TaxCategoryCode;

public abstract class AbstractEinvoice extends SvrProcess implements InterfaceEinvoice {

	private static final Logger LOG = Logger.getLogger(AbstractEinvoice.class.getName());
	
	static final String XRECHNUNG_12 = "urn:cen.eu:en16931:2017#compliant#urn:xoev-de:kosit:standard:xrechnung_1.2";
	
	/**
	 * factory method
	 * 
	 * @param schema
	 * @return InterfaceEinvoice object
	 */
	static public InterfaceEinvoice createEinvoice(String schema) {
		if(schema.equals(InterfaceEinvoice.UBL_SCHEMA_NAME)) {
			return new UblImpl();
		} else if(schema.equals(InterfaceEinvoice.CII_SCHEMA_NAME)) {
			return new CiiImpl();
		} else {
			LOG.warning("xmlSchema='" + schema+"'" + " error use "+InterfaceEinvoice.UBL_SCHEMA_NAME);
			return null;
		}
		
	}
	
	protected AbstactTransformer transformer; // Singleton
	protected MInvoice mInvoice; // the source AD object
	
	protected InterfaceMapping mapping;

	AbstractEinvoice() {
		super();
		mapping = createMapping();
	}
	
	/**
	 * the equivalent to adempiere invoice Document No
	 * 
	 * @return Invoice number
	 */
	abstract public String getDocumentNo();
	
	/**
	 * generic mapping adempiere invoice to UBL or CII
	 * 
	 * @param mInvoice
	 * @return object instance of (UBL)Invoice or CreditNote or CrossIndustryInvoice
	 */
	abstract Object mapToEModel(MInvoice mInvoice);
	
	abstract void setBuyerReference(String buyerReference);
	abstract void setTotals(Amount lineExtension, Amount taxExclusive, Amount taxInclusive, Amount payable, Amount taxTotal);
	abstract void mapByuer(String buyerName, int location_ID, int user_ID);
	abstract void mapSeller(String sellerName, int location_ID, int salesRep_ID, String companyID, String companyLegalForm, String taxCompanyId);
	abstract void setPaymentInstructions(PaymentMeansEnum code, String paymentMeansText, String remittanceInformation
			, CreditTransfer creditTransfer, PaymentCard paymentCard, DirectDebit directDebit);
	abstract void setPaymentTermsAndDate(String description, Timestamp ts);
	abstract CoreInvoiceVatBreakdown createVatBreakdown(Amount taxableAmount, Amount taxAmount, TaxCategoryCode codeEnum, BigDecimal percent);
	abstract void addVATBreakDown(CoreInvoiceVatBreakdown vatBreakdown);
	abstract void mapLine(MInvoiceLine line);
	// factory methods
	/**
	 * @return Interface CreditTransfer
	 * @see com.klst.einvoice.CreditTransferFactory
	 */
	abstract CreditTransfer createCreditTransfer(IBANId iban, String accountName, BICId bic);
	abstract CreditTransfer createCreditTransfer(String accountId, String accountName, BICId bic);
	/**
	 * @return Interface DirectDebit
	 * @see com.klst.einvoice.DirectDebitFactory
	 */
	abstract DirectDebit createDirectDebit(String mandateID, String bankAssignedCreditorID, IBANId iban);
	abstract DirectDebit createDirectDebit(String mandateID, String bankAssignedCreditorID, String debitedAccountID);

	public InterfaceMapping createMapping() {
		return new CustomizedMapping();
	}
	
	// mapping POReference -> BuyerReference 
	void mapBuyerReference() {
		setBuyerReference(mapping.mapBuyerReference(mInvoice));
	}
	
	// mapping SellerGroup
	//         MOrg.name      -> Seller.name     
	//         Location       ->       Address
	//         Contact        ->       Contact
	void mapSellerGroup() {
		int mAD_Org_ID = mInvoice.getAD_Org_ID();
		int salesRep_ID = mInvoice.getSalesRep_ID();
		
		String sellerName = null;
		String companyID = null;
		String companyLegalForm = null;
		
		MOrg mOrg = new MOrg(Env.getCtx(), mAD_Org_ID, get_TrxName());
		sellerName = mOrg.getName();
		MOrgInfo mOrgInfo = MOrgInfo.get(Env.getCtx(), mAD_Org_ID, get_TrxName());	
		
		LOG.info("sellerName/MOrg.name:"+sellerName +
				" companyID:"+companyID +
				" companyLegalForm:"+companyLegalForm
				);

		// optional:
		String taxCompanyId = mOrgInfo.getTaxID(); // UStNr DE....
		
		mapSeller(sellerName, mOrgInfo.getC_Location_ID(), salesRep_ID, companyID, companyLegalForm, taxCompanyId);
	}

	// mapping BuyerGroup
	//         MBPartner.name -> Buyer.name     
	//         Location       ->       Address
	//         Contact        ->       Contact
	void mapBuyerGroup() {
		int mBP_ID = mInvoice.getC_BPartner_ID();
		int location_ID = mInvoice.getC_BPartner_Location().getC_Location_ID();
		int user_ID = mInvoice.getAD_User_ID();
		
		MBPartner mBPartner = new MBPartner(Env.getCtx(), mBP_ID, get_TrxName());
		String buyerName = mBPartner.getName();
		
		mapByuer(buyerName, location_ID, user_ID);
	}
	
	// IBAN of the organisation (aka seller/payee) needed for SEPA CreditTransfer
	String getOrgIBAN(MBankAccount mBankAccount) {
		return mBankAccount.getIBAN();
	}
	
	// get AccountId of the seller/payee
	MBankAccount getOrgBankAccount(int mAD_Org_ID ) {
		MOrgInfo mOrgInfo = MOrgInfo.get(Env.getCtx(), mAD_Org_ID, get_TrxName());	
		MBank mBank = new MBank(Env.getCtx(), mOrgInfo.getTransferBank_ID(), get_TrxName());
		final String sql = "SELECT MIN("+MBankAccount.COLUMNNAME_C_BankAccount_ID+")"
				+" FROM "+MBankAccount.Table_Name
				+" WHERE "+MBankAccount.COLUMNNAME_C_Bank_ID+"=?"
				+" AND "+MBankAccount.COLUMNNAME_IsActive+"=?"; 
		int bankAccount_ID = DB.getSQLValueEx(get_TrxName(), sql, mOrgInfo.getTransferBank_ID(), true);
		return new MBankAccount(Env.getCtx(), bankAccount_ID, get_TrxName());
	}
	
	// IBAN of the customer needed for SEPA DirectDebit
	String getCusomerIBAN(int partnerId) {
		List<MBPBankAccount> mBPBankAccountList = MBPBankAccount.getByPartner(Env.getCtx(), partnerId);
		mBPBankAccountList.forEach(mBPBankAccount -> {
			LOG.info("DirectDebit:"+mBPBankAccount.isDirectDebit() + " IBAN="+mBPBankAccount.getIBAN() + " "+mBPBankAccount);
		});
		if(mBPBankAccountList.isEmpty()) return null;
		
		return mBPBankAccountList.get(0).getIBAN(); // get the first one
	}
	
	// mapping PaymentGroup
	//         PaymentRule -> PaymentInstructions with CreditTransfer or DirectDebit 
	//        MPaymentTerm -> PaymentTerms
	void mapPaymentGroup() {
		MBankAccount orgBankAccount = getOrgBankAccount(mInvoice.getAD_Org_ID());
		String orgIBAN = getOrgIBAN(orgBankAccount);
		
		String customerIBAN = getCusomerIBAN(mInvoice.getC_BPartner_ID()); // IBAN of the customer		
		
		String remittanceInformation = "TODO Verwendungszweck";
		if(mInvoice.getPaymentRule().equals(MInvoice.PAYMENTRULE_OnCredit) 
		|| mInvoice.getPaymentRule().equals(MInvoice.PAYMENTRULE_DirectDeposit)) {
			IBANId payeeIban = new IBANId(orgIBAN);
			String paymentMeansText = null; // paymentMeansText : Text zur Zahlungsart
			CreditTransfer sepaCreditTransfer = createCreditTransfer(payeeIban, null, null);
			setPaymentInstructions(PaymentMeansEnum.CreditTransfer, null, remittanceInformation, sepaCreditTransfer, null, null);
		} else if(mInvoice.getPaymentRule().equals(MInvoice.PAYMENTRULE_DirectDebit)) {
			IBANId payerIban = new IBANId(customerIBAN);
			String mandateID = null; // paymentMeansText : Text zur Zahlungsart
			DirectDebit sepaDirectDebit = createDirectDebit(mandateID, null, payerIban);
			setPaymentInstructions(PaymentMeansEnum.SEPADirectDebit, null, remittanceInformation, null, null, sepaDirectDebit);
		} else {
			LOG.warning("TODO PaymentMeansCode: mInvoice.PaymentRule="+mInvoice.getPaymentRule());
		}

		MPaymentTerm mPaymentTerm = new MPaymentTerm(Env.getCtx(), mInvoice.getC_PaymentTerm_ID(), get_TrxName());
//		((Invoice)ublObject).addPaymentTerms("#SKONTO#TAGE=7#PROZENT=2.00#"); // de CIUS TODO
		setPaymentTermsAndDate(mPaymentTerm.getName(), (Timestamp)null); 
	}

	void mapDocumentTotals() {
		BigDecimal taxBaseAmt = BigDecimal.ZERO;
		BigDecimal taxAmt = BigDecimal.ZERO;
		MInvoiceTax[] mInvoiceTaxes = mInvoice.getTaxes(true); // MInvoiceTax[] getTaxes (boolean requery)
		for(int i=0; i<mInvoiceTaxes.length; i++) {
			MInvoiceTax mInvoiceTax = mInvoiceTaxes[i];
			LOG.info(mInvoiceTax.toString());
			taxBaseAmt = taxBaseAmt.add(mInvoiceTax.getTaxBaseAmt());
			taxAmt = taxAmt.add(mInvoiceTax.getTaxAmt());
		}
		setTotals(new Amount(mInvoice.getCurrencyISO(), mInvoice.getTotalLines()) // lineExtension
				, new Amount(mInvoice.getCurrencyISO(), taxBaseAmt) // taxExclusive
				, new Amount(mInvoice.getCurrencyISO(), taxBaseAmt.add(taxAmt)) // taxInclusive
				, new Amount(mInvoice.getCurrencyISO(), mInvoice.getGrandTotal()) // payable
				, new Amount(mInvoice.getCurrencyISO(), taxAmt) // taxTotal
				);
	}

	void mapVatBreakDownGroup() {
		List<MInvoiceTax> taxes = Arrays.asList(mInvoice.getTaxes(true));
		taxes.forEach(mInvoiceTax -> {
			I_C_Tax tax = mInvoiceTax.getC_Tax(); // mapping
			CoreInvoiceVatBreakdown vatBreakdown = createVatBreakdown(
					  new Amount(mInvoice.getCurrencyISO(), mInvoiceTax.getTaxBaseAmt()) // taxableAmount
					, new Amount(mInvoice.getCurrencyISO(), mInvoiceTax.getTaxAmt()) // taxAmount
					, TaxCategoryCode.StandardRate, tax.getRate());
			addVATBreakDown(vatBreakdown);
		});
		LOG.info("finished. "+taxes.size() + " vatBreakDowns.");
	}

	void mapLineGroup() {
		// 
		List<MInvoiceLine> invoiceLines = Arrays.asList(mInvoice.getLines());
		invoiceLines.forEach(invoiceLine -> {
//			LOG.info(invoiceLine.toString());
			if(BigDecimal.ZERO.compareTo(invoiceLine.getQtyInvoiced())==0) { 
				LOG.info("empty Line "+invoiceLine.toString());
			} else {
				mapLine(invoiceLine);
			}
		});
		LOG.info("finished. "+invoiceLines.size() + " lines.");
	}


	/*
	 * (non-Javadoc)
	 * @see com.klst.einvoice.InterfaceEinvoice#setupTransformer(boolean)
	 */
	@Override
	abstract public void setupTransformer(boolean isCreditNote);

	/*
	 * (non-Javadoc)
	 * @see com.klst.einvoice.InterfaceEinvoice#tranformToXML(org.compiere.model.MInvoice)
	 */
	@Override
	abstract public byte[] tranformToXML(MInvoice mInvoice);

	/*
	 * (non-Javadoc)
	 * @see com.klst.einvoice.InterfaceEinvoice#tranformToDomDocument(byte[])
	 */
	@Override
	// tks to https://stackoverflow.com/questions/21165871/how-to-convert-array-byte-to-org-w3c-dom-document
	public Document tranformToDomDocument(byte[] xmlData) throws Exception {
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    factory.setNamespaceAware(true);
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    return builder.parse(new ByteArrayInputStream(xmlData));
	}

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub		
	}

	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
