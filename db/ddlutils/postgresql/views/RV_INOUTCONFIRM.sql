CREATE OR REPLACE VIEW RV_INOUTCONFIRM
(M_INOUTCONFIRM_ID, AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, 
 CREATEDBY, UPDATED, UPDATEDBY, M_INOUT_ID, DOCUMENTNO, 
 CONFIRMTYPE, ISAPPROVED, ISCANCELLED, DESCRIPTION, PROCESSING, 
 PROCESSED, C_BPARTNER_ID, C_BPARTNER_LOCATION_ID, M_WAREHOUSE_ID, C_ORDER_ID, 
 ISSOTRX)
AS 
SELECT c.M_InOutConfirm_ID,
  c.AD_Client_ID, c.AD_Org_ID, c.IsActive, c.Created, c.CreatedBy, c.Updated, c.UpdatedBy,
  c.M_InOut_ID, c.DocumentNo, c.ConfirmType, c.IsApproved, c.IsCancelled,
  c.Description, c.Processing, c.Processed,
  i.C_BPartner_ID, i.C_BPartner_Location_ID, i.M_Warehouse_ID, i.C_Order_ID, i.IsSOTrx
FROM M_InOutConfirm c
  INNER JOIN M_InOut i ON (c.M_InOut_ID=i.M_InOut_ID);



