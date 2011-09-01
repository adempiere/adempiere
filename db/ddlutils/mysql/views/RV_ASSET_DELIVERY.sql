CREATE OR REPLACE VIEW RV_ASSET_DELIVERY
(A_ASSET_DELIVERY_ID, AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, 
 CREATEDBY, UPDATED, UPDATEDBY, A_ASSET_ID, A_ASSET_GROUP_ID, 
 M_PRODUCT_ID, GUARANTEEDATE, ASSETSERVICEDATE, C_BPARTNER_ID, AD_USER_ID, 
 MOVEMENTDATE, SERNO, LOT, VERSIONNO, M_INOUTLINE_ID, 
 EMAIL, MESSAGEID, DELIVERYCONFIRMATION, URL, REMOTE_ADDR, 
 REMOTE_HOST, REFERRER, DESCRIPTION)
AS 
SELECT ad.A_Asset_Delivery_ID, ad.AD_Client_ID, ad.AD_Org_ID, ad.IsActive, ad.Created, ad.CreatedBy, ad.Updated, ad.UpdatedBy,
  a.A_Asset_ID, a.A_Asset_Group_ID, a.M_Product_ID, 
  a.GuaranteeDate, a.AssetServiceDate,
  a.C_BPartner_ID, ad.AD_User_ID,
  ad.MovementDate, ad.SerNo, ad.Lot, ad.VersionNo,
  ad.M_InOutLine_ID, 
  ad.Email, ad.MessageID, ad.DeliveryConfirmation,
  ad.URL, ad.Remote_Addr, ad.Remote_Host, ad.Referrer,
  ad.Description
FROM A_Asset_Delivery ad
 INNER JOIN A_Asset a ON (a.A_Asset_ID=ad.A_Asset_ID);
