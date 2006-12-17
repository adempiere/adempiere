CREATE OR REPLACE VIEW RV_INOUTLINECONFIRM
(M_INOUTCONFIRM_ID, M_INOUTLINECONFIRM_ID, AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, 
 CREATED, CREATEDBY, UPDATED, UPDATEDBY, TARGETQTY, 
 CONFIRMEDQTY, DIFFERENCEQTY, SCRAPPEDQTY, DESCRIPTION, PROCESSED, 
 M_INOUT_ID, DOCUMENTNO, CONFIRMTYPE, ISAPPROVED, ISCANCELLED, 
 C_BPARTNER_ID, C_BPARTNER_LOCATION_ID, M_WAREHOUSE_ID, C_ORDER_ID, ISSOTRX, 
 M_INOUTLINE_ID, M_PRODUCT_ID, M_ATTRIBUTESETINSTANCE_ID, M_LOCATOR_ID)
AS 
SELECT cl.M_InOutConfirm_ID, cl.M_InOutLineConfirm_ID,
  cl.AD_Client_ID, cl.AD_Org_ID, cl.IsActive, cl.Created, cl.CreatedBy, cl.Updated, cl.UpdatedBy,
  cl.TargetQty, cl.ConfirmedQty, cl.DifferenceQty, cl.ScrappedQty,
  cl.Description, cl.Processed,
  c.M_InOut_ID, c.DocumentNo, c.ConfirmType, c.IsApproved, c.IsCancelled,
  i.C_BPartner_ID, i.C_BPartner_Location_ID, i.M_Warehouse_ID, i.C_Order_ID, i.IsSOTrx,
  cl.M_InOutLine_ID, il.M_Product_ID, il.M_AttributeSetInstance_ID, il.M_Locator_ID
FROM M_InOutLineConfirm cl
  INNER JOIN M_InOutConfirm c ON (cl.M_InOutConfirm_ID=c.M_InOutConfirm_ID)
  INNER JOIN M_InOut i ON (c.M_InOut_ID=i.M_InOut_ID)
  INNER JOIN M_InOutLine il ON (cl.M_InOutLine_ID=il.M_InOutLine_ID);



