package org.adempiere.document.model;

import org.compiere.model.I_M_Warehouse;

public interface IDocumentDeliveryLocation
{

	int getDropShip_BPartner_ID();

	org.compiere.model.I_C_BPartner getDropShip_BPartner();

	int getDropShip_Location_ID();

	org.compiere.model.I_C_BPartner_Location getDropShip_Location();

	int getDropShip_User_ID();

	org.compiere.model.I_AD_User getDropShip_User();

	int getM_Warehouse_ID();

	I_M_Warehouse getM_Warehouse();

	boolean isDropShip();

	String getDeliveryToAddress();

	void setDeliveryToAddress(String address);

}
