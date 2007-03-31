CREATE OR REPLACE VIEW M_INOUTCONFIRM_V
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, AD_LANGUAGE, M_INOUTCONFIRM_ID, DOCUMENTNO, 
 CONFIRMTYPE, ISAPPROVED, ISCANCELLED, DESCRIPTION, M_INOUT_ID, 
 SHIPDESCRIPTION, C_BPARTNER_ID, C_BPARTNER_LOCATION_ID, AD_USER_ID, SALESREP_ID, 
 C_DOCTYPE_ID, DOCUMENTTYPE, C_ORDER_ID, DATEORDERED, MOVEMENTDATE, 
 MOVEMENTTYPE, M_WAREHOUSE_ID, POREFERENCE, DELIVERYRULE, FREIGHTCOSTRULE, 
 DELIVERYVIARULE, M_SHIPPER_ID, PRIORITYRULE, PROCESSED)
AS 
SELECT ioc.AD_Client_ID, ioc.AD_Org_ID, ioc.IsActive, ioc.Created, ioc.CreatedBy, ioc.Updated, ioc.UpdatedBy,
  cast('en_US' as varchar2(6)) AS AD_Language,
  ioc.M_InOutConfirm_ID,
  ioc.DocumentNo, ioc.ConfirmType,
  ioc.IsApproved, ioc.IsCancelled, ioc.Description,
  --
  io.M_InOut_ID, io.Description AS ShipDescription,
  io.C_BPartner_ID, io.C_BPartner_Location_ID, io.AD_User_ID,
  io.SalesRep_ID, io.C_DocType_ID, dt.PrintName AS DocumentType,
  io.C_Order_ID, io.DateOrdered, io.MovementDate, io.MovementType, 
  io.M_Warehouse_ID, io.POReference,
  io.DeliveryRule, io.FreightCostRule,
  io.DeliveryViaRule, io.M_Shipper_ID, PriorityRule,
  ioc.Processed
FROM M_InOutConfirm ioc
  INNER JOIN M_InOut io ON (ioc.M_InOut_ID=io.M_InOut_ID)
  INNER JOIN C_DocType dt ON (io.C_DocType_ID=dt.C_DocType_ID);



