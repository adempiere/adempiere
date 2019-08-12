package com.klst.adempiere.einvoice;

import org.compiere.model.MInvoice;
import org.w3c.dom.Document;

public interface InterfaceEinvoice {

	// SysConfig key
	public static final String XML_SCHEMA_NAME = "XML_EINVOICE_SCHEMA_NAME";
	// Values for the key, a part of schemaLocation
	public static final String UBL_SCHEMA_NAME = "os-UBL-2.1";
	public static final String CII_SCHEMA_NAME = "CrossIndustryInvoice:100";
	
	/**
	 * setup an appropriate transformer, aka marshaller
	 * <p>
	 * UBL uses different transformer for CreditNote and Invoice
	 * 
	 * @param isCreditNote
	 */
	void setupTransformer(boolean isCreditNote);
	
	/**
	 * create the xml e-invoice
	 * <p>
	 * in UBL it can be CreditNote or (UBL)Invoice
	 * 
	 * @param mInvoice
	 * @return the xml e-representation of the adempiere invoice
	 */
	byte[] tranformToXML(MInvoice mInvoice);
	
	/**
	 * transform xml data to Document Object Model (DOM)
	 * 
	 * @param xmlData
	 * @return org.w3c.dom.Document
	 * @throws Exception
	 * 
	 * <p>See also the <a href='http://www.w3.org/TR/2004/REC-DOM-Level-3-Core-20040407'>Document Object Model (DOM) Level 3 Core Specification</a>
	 */
	public Document tranformToDomDocument(byte[] xmlData) throws Exception;
	
}
