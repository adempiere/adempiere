package org.adempiere.document.model;

public interface IDocumentBillLocation
{

	int getBill_BPartner_ID();

	org.compiere.model.I_C_BPartner getBill_BPartner();

	int getBill_Location_ID();

	org.compiere.model.I_C_BPartner_Location getBill_Location();

	int getBill_User_ID();

	org.compiere.model.I_AD_User getBill_User();

	String getBillToAddress();

	void setBillToAddress(String address);

}
