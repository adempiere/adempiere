package org.adempiere.document.model;

public interface IDocumentLocation
{

	int getC_BPartner_ID();

	org.compiere.model.I_C_BPartner getC_BPartner();

	int getC_BPartner_Location_ID();

	org.compiere.model.I_C_BPartner_Location getC_BPartner_Location();

	int getAD_User_ID();

	org.compiere.model.I_AD_User getAD_User();

	String getBPartnerAddress();
	void setBPartnerAddress(String address);

}
