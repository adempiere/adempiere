CREATE OR REPLACE VIEW RV_ASSET_CUSTOMER
(A_ASSET_ID, AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, 
 CREATEDBY, UPDATED, UPDATEDBY, VALUE, NAME, 
 DESCRIPTION, HELP, A_ASSET_GROUP_ID, M_PRODUCT_ID, SERNO, 
 LOT, VERSIONNO, GUARANTEEDATE, ASSETSERVICEDATE, C_BPARTNER_ID, 
 C_BPARTNER_LOCATION_ID, AD_USER_ID, DELIVERYCOUNT)
AS 
SELECT A_Asset_ID, AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
  Value, Name, Description, Help, A_Asset_Group_ID, M_Product_ID, SerNo, Lot, VersionNo,
  GuaranteeDate, AssetServiceDate,
  C_BPartner_ID, C_BPartner_Location_ID, AD_User_ID,
  (SELECT COUNT(*) FROM A_Asset_Delivery ad WHERE a.A_Asset_ID=ad.A_Asset_ID) AS DeliveryCount
FROM A_Asset a
WHERE C_BPartner_ID IS NOT NULL;
