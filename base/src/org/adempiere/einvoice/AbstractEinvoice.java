package org.adempiere.einvoice;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.compiere.model.I_AD_User;
import org.compiere.model.I_C_Tax;
import org.compiere.model.MBPBankAccount;
import org.compiere.model.MBPartner;
import org.compiere.model.MBank;
import org.compiere.model.MBankAccount;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MInvoiceTax;
import org.compiere.model.MLocation;
import org.compiere.model.MOrg;
import org.compiere.model.MOrgInfo;
import org.compiere.model.MPaymentTerm;
import org.compiere.model.MUser;
import org.compiere.model.Obscure;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.w3c.dom.Document;

import com.klst.einvoice.BG23_VatBreakdown;
import com.klst.einvoice.CoreInvoice;
import com.klst.einvoice.CreditTransfer;
import com.klst.einvoice.DirectDebit;
import com.klst.einvoice.IContact;
import com.klst.einvoice.IContactFactory;
import com.klst.einvoice.PaymentCard;
import com.klst.einvoice.PostalAddress;
import com.klst.einvoice.PostalAddressFactory;
import com.klst.einvoice.unece.uncefact.Amount;
import com.klst.einvoice.unece.uncefact.BICId;
import com.klst.einvoice.unece.uncefact.IBANId;
import com.klst.marshaller.AbstactTransformer;
import com.klst.untdid.codelist.PaymentMeansEnum;
import com.klst.untdid.codelist.TaxCategoryCode;

public abstract class AbstractEinvoice extends SvrProcess implements InterfaceEinvoice {

private static final Logger LOG = Logger.getLogger(AbstractEinvoice.class.getName());
	
	static final String DEFAULT_PROFILE = CoreInvoice.PROFILE_XRECHNUNG;

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
	abstract BG23_VatBreakdown createVatBreakdown(Amount taxableAmount, Amount taxAmount, TaxCategoryCode codeEnum, BigDecimal percent);
	abstract void addVATBreakDown(BG23_VatBreakdown vatBreakdown);
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
	/**
	 * @return Interface BG-18 PAYMENT CARD INFORMATION
	 * @see com.klst.einvoice.PaymentCardFactory
	 */
	abstract PaymentCard createPaymentCard(String cardAccountID, String cardHolderName);

	public InterfaceMapping createMapping() {
		return new CustomizedMapping();
	}
	
	protected PostalAddress mapLocationToAddress(int location_ID, PostalAddressFactory addressFactory) {
		MLocation mLocation = new MLocation(Env.getCtx(), location_ID, get_TrxName());
		String countryCode = mLocation.getCountry().getCountryCode();
		String postalCode = mLocation.getPostal();
		String city = mLocation.getCity();
		String street = null;
		String a1 = mLocation.getAddress1();
		String a2 = mLocation.getAddress2();
		String a3 = mLocation.getAddress3();
		String a4 = mLocation.getAddress4();
		
		PostalAddress address = addressFactory.createAddress(countryCode, postalCode, city);
		if(a1!=null) address.setAddressLine1(a1);
		if(a2!=null) address.setAddressLine2(a2);
		if(a3!=null) address.setAddressLine3(a3);
//		if(a4!=null) address.setAdditionalStreet(a4); // TODO AD 4 lines -> UBL/CII 3 lines ??????????????????
		return address;
	}
	
	protected IContact mapUserToContact(int user_ID, IContactFactory contactFactory) {
		MUser mUser = new MUser(Env.getCtx(), user_ID, get_TrxName());
		String contactName = mUser.getName();
		String contactTel = mUser.getPhone();
		String contactMail = mUser.getEMail();
		return contactFactory.createContact(contactName, contactTel, contactMail);
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
	
	PaymentCard getCusomerCard(int partnerId) {
		List<MBPBankAccount> mBPBankAccountList = MBPBankAccount.getByPartner(Env.getCtx(), partnerId);
		PaymentCard paymentCard = null;
		if(mBPBankAccountList.isEmpty()) return paymentCard;
		for(int i=0; i<mBPBankAccountList.size(); i++) {
			MBPBankAccount mBPBankAccount = mBPBankAccountList.get(i);
			if(mBPBankAccount.isDirectDebit()) {
				// this is not a card
			} else {
				I_AD_User adUser = mBPBankAccount.getAD_User();
				/* In accordance with card payments security standards an invoice should 
				 * never include a full card primary account number. 
				 * At the moment PCI Security Standards Council has defined that 
				 * the first 6 digits and last 4 digits are the maximum number of digits to be shown.
				 */
				String cardNumber = Obscure.obscure(mBPBankAccount.getCreditCardNumber());
				LOG.info("CreditCard:"+mBPBankAccount.getCreditCardType()+" "+cardNumber + " adUser:"+adUser);
				paymentCard = createPaymentCard(cardNumber, adUser==null? null : adUser.getName());
			}
		}
		return paymentCard;
	}
	// IBAN of the customer needed for SEPA DirectDebit
	String getCusomerIBAN(int partnerId) {
		List<MBPBankAccount> mBPBankAccountList = MBPBankAccount.getByPartner(Env.getCtx(), partnerId);
		String iban = null;
		if(mBPBankAccountList.isEmpty()) return iban;
		for(int i=0; i<mBPBankAccountList.size(); i++) {
			MBPBankAccount mBPBankAccount = mBPBankAccountList.get(i);
			if(mBPBankAccount.isDirectDebit()) {
				LOG.info("DirectDebit:"+mBPBankAccount.isDirectDebit() + " IBAN="+mBPBankAccount.getIBAN() + " "+mBPBankAccount);
				iban = mBPBankAccount.getIBAN();
			}
		}
		return iban;
	}
	
	// mapping PaymentGroup
	//         PaymentRule -> PaymentInstructions with CreditTransfer or DirectDebit 
	//        MPaymentTerm -> PaymentTerms
	void mapPaymentGroup() {
		MBankAccount orgBankAccount = getOrgBankAccount(mInvoice.getAD_Org_ID());
		String orgIBAN = getOrgIBAN(orgBankAccount);
		LOG.config("orgBankAccount:"+orgBankAccount + " Org_ID="+mInvoice.getAD_Org_ID() + " orgIBAN="+orgIBAN);
		
		String customerIBAN = getCusomerIBAN(mInvoice.getC_BPartner_ID()); // IBAN of the customer		
		
		String remittanceInformation = getDocumentNo();
/*
PAYMENTRULE Mapping: ==> BG-16 + 0..1 PAYMENT INSTRUCTIONS / ZAHLUNGSANWEISUNGEN

DirectDeposit = "T"; OnCredit = "P"; ==> BG-17 ++ 0..n CREDIT TRANSFER / ÜBERWEISUNG
CreditCard = "K";                    ==> BG-18 ++ 0..1 PAYMENT CARD INFORMATION / INFORMATIONEN ZUR ZAHLUNSKARTE
DirectDebit = "D";                   ==> BG-19 ++ 0..1 DIRECT DEBIT / LASTSCHRIFTVERFAHREN
Cash = "B";                          ==> ?
Check = "S";                         ==> ?
Mixed = "M";                         ==> ?

DirectDeposit / Direktüberweiseung , direkte Einzahlung

AD:	PaymentMeansEnum/UNTDID 4461:
B:	InCash 				(10),
S:	Cheque				(20),
P:	CreditTransfer 		(30),
T:	DebitTransfer 		(31),
	PaymentToBankAccount 	(42),
K:	BankCard 			(48),
D:	DirectDebit 		(49),
	StandingAgreement 	(57),
P:	SEPACreditTransfer 	(58),
D:	SEPADirectDebit 	(59),
	ClearingBetweenPartners (97);

Für PaymentMeansEnum.InCash gibt es keine kosit Beispiele
Für PaymentMeansEnum.Cheque gibt es keine kosit Beispiele

 */
		if(mInvoice.getPaymentRule().equals(MInvoice.PAYMENTRULE_OnCredit) 
		|| mInvoice.getPaymentRule().equals(MInvoice.PAYMENTRULE_DirectDeposit)) {
			IBANId payeeIban = new IBANId(orgIBAN);
			MBank mBank = orgBankAccount.getBank();
			BICId bicId = new BICId(mBank==null ? "" : mBank.getSwiftCode());
			CreditTransfer sepaCreditTransfer = createCreditTransfer(payeeIban, orgBankAccount.getName(), bicId);
			setPaymentInstructions(PaymentMeansEnum.CreditTransfer, null, remittanceInformation, sepaCreditTransfer, null, null);
		} else if(mInvoice.getPaymentRule().equals(MInvoice.PAYMENTRULE_DirectDebit)) {
			IBANId payerIban = new IBANId(customerIBAN);
			String mandateID = null;
			String paymentMeansText = null; // optional
			DirectDebit sepaDirectDebit = createDirectDebit(mandateID, null, payerIban);
			setPaymentInstructions(PaymentMeansEnum.SEPADirectDebit, paymentMeansText, remittanceInformation, null, null, sepaDirectDebit);
		} else if(mInvoice.getPaymentRule().equals(MInvoice.PAYMENTRULE_CreditCard)) {
			PaymentCard card = getCusomerCard(mInvoice.getC_BPartner_ID());
			setPaymentInstructions(PaymentMeansEnum.BankCard, null, remittanceInformation, null, card, null);
		} else if(mInvoice.getPaymentRule().equals(MInvoice.PAYMENTRULE_Cash)) {
			String paymentMeansText = "cash"; // Text zur Zahlungsart
			PaymentCard card = createPaymentCard("payed cash", null);
			setPaymentInstructions(PaymentMeansEnum.InCash, paymentMeansText, remittanceInformation, null, card, null);
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
			BG23_VatBreakdown vatBreakdown = createVatBreakdown(
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
