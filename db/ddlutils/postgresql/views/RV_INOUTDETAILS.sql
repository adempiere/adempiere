CREATE OR REPLACE VIEW RV_INOUTDETAILS
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, M_INOUT_ID, ISSOTRX, DOCUMENTNO, 
 DOCACTION, DOCSTATUS, POSTED, PROCESSED, C_DOCTYPE_ID, 
 DESCRIPTION, C_ORDER_ID, DATEORDERED, MOVEMENTTYPE, MOVEMENTDATE, 
 DATEACCT, C_BPARTNER_ID, C_BPARTNER_LOCATION_ID, AD_USER_ID, SALESREP_ID, 
 M_WAREHOUSE_ID, POREFERENCE, DELIVERYRULE, FREIGHTCOSTRULE, FREIGHTAMT, 
 DELIVERYVIARULE, M_SHIPPER_ID, PRIORITYRULE, DATEPRINTED, NOPACKAGES, 
 PICKDATE, SHIPDATE, TRACKINGNO, AD_ORGTRX_ID, C_PROJECT_ID, 
 C_CAMPAIGN_ID, C_ACTIVITY_ID, USER1_ID, USER2_ID, DATERECEIVED, 
 ISAPPROVED, ISINDISPUTE, M_INOUTLINE_ID, LINE, LINEDESCRIPTION, 
 C_ORDERLINE_ID, M_LOCATOR_ID, M_PRODUCT_ID, C_UOM_ID, M_ATTRIBUTESETINSTANCE_ID, 
 PRODUCTATTRIBUTE, M_ATTRIBUTESET_ID, M_LOT_ID, GUARANTEEDATE, LOT, 
 SERNO, MOVEMENTQTY, QTYENTERED, ISDESCRIPTION, CONFIRMEDQTY, 
 PICKEDQTY, SCRAPPEDQTY, TARGETQTY, LOCATORVALUE, X, 
 Y, Z)
AS 
SELECT h.AD_Client_ID, h.AD_Org_ID, l.IsActive, l.Created, l.CreatedBy, l.Updated, l.UpdatedBy,
  h.M_InOut_ID,
  h.IsSOTrx, h.DocumentNo, h.DocAction, h.DocStatus, h.Posted, h.Processed,
  h.C_DocType_ID, h.Description, h.C_Order_ID, h.DateOrdered,
  h.MovementType, h.MovementDate, h.DateAcct,
  h.C_BPartner_ID, h.C_BPartner_Location_ID, h.AD_User_ID,
  h.SalesRep_ID,
  h.M_Warehouse_ID,
  h.POReference, h.DeliveryRule, h.FreightCostRule, h.FreightAmt,
  h.DeliveryViaRule, h.M_Shipper_ID, -- h.C_CHARGE_ID, h.CHARGEAmt,
  h.PriorityRule, h.DatePrinted,
  h.NoPackages, h.PickDate, h.ShipDate, h.TrackingNo,
  h.AD_OrgTrx_ID, h.C_Project_ID, h.C_Campaign_ID, h.C_Activity_ID, h.User1_ID, h.User2_ID,
  h.DateReceived, h.IsApproved, h.IsInDispute,
  l.M_InOutLine_ID, l.Line, l.Description AS LineDescription,
  l.C_OrderLine_ID, l.M_Locator_ID,
  l.M_Product_ID, l.C_UOM_ID,
  l.M_AttributeSetInstance_ID, productAttribute(l.M_AttributeSetInstance_ID) AS ProductAttribute,
  pasi.M_AttributeSet_ID, pasi.M_Lot_ID, pasi.GuaranteeDate, pasi.Lot, pasi.SerNo,
  l.MovementQty, l.QtyEntered,
  l.IsDescription,
  l.ConfirmedQty, l.PickedQty, l.ScrappedQty, l.TargetQty,
  loc.Value AS LocatorValue, loc.X, loc.Y, loc.Z
FROM M_InOut h
  INNER JOIN M_InOutLine l ON (h.M_InOut_ID=l.M_InOut_ID)
  LEFT OUTER JOIN M_Locator loc ON (l.M_Locator_ID=loc.M_Locator_ID)
  LEFT OUTER JOIN M_AttributeSetInstance pasi ON (l.M_AttributeSetInstance_ID=pasi.M_AttributeSetInstance_ID);



