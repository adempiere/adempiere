CREATE OR REPLACE VIEW RV_ASSET_SUMMONTH
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, A_ASSET_ID, A_ASSET_GROUP_ID, M_PRODUCT_ID, 
 VALUE, NAME, DESCRIPTION, HELP, GUARANTEEDATE, 
 ASSETSERVICEDATE, C_BPARTNER_ID, AD_USER_ID, SERNO, LOT, 
 VERSIONNO, MOVEMENTDATE, DELIVERYCOUNT)
AS 
SELECT a.AD_Client_ID, a.AD_Org_ID, a.IsActive, a.Created, a.CreatedBy, a.Updated, a.UpdatedBy,
  a.A_Asset_ID, a.A_Asset_Group_ID, a.M_Product_ID, 
  a.Value, a.Name, a.Description, a.Help,
  a.GuaranteeDate, a.AssetServiceDate,
  a.C_BPartner_ID, a.AD_User_ID, a.SerNo, a.Lot, a.VersionNo,
  firstOf(ad.MovementDate, 'MM') AS MovementDate,
  COUNT(*) AS DeliveryCount
FROM A_Asset a
 INNER JOIN A_Asset_Delivery ad ON (a.A_Asset_ID=ad.A_Asset_ID)
GROUP BY a.AD_Client_ID, a.AD_Org_ID, a.IsActive, a.Created, a.CreatedBy, a.Updated, a.UpdatedBy,
  a.A_Asset_ID, a.A_Asset_Group_ID, a.M_Product_ID, 
  a.Value, a.Name, a.Description, a.Help,
  a.GuaranteeDate, a.AssetServiceDate,
  a.C_BPartner_ID, a.AD_User_ID, a.SerNo, a.Lot, a.VersionNo,
  firstOf(ad.MovementDate, 'MM');



