CREATE OR REPLACE VIEW M_TRANSACTION_V
(M_TRANSACTION_ID, AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, 
 CREATEDBY, UPDATED, UPDATEDBY, MOVEMENTTYPE, M_LOCATOR_ID, 
 M_PRODUCT_ID, MOVEMENTDATE, MOVEMENTQTY, M_INVENTORYLINE_ID, M_INVENTORY_ID, 
 M_MOVEMENTLINE_ID, M_MOVEMENT_ID, M_INOUTLINE_ID, M_INOUT_ID, M_PRODUCTIONLINE_ID, 
 M_PRODUCTION_ID, C_PROJECTISSUE_ID, C_PROJECT_ID, M_ATTRIBUTESETINSTANCE_ID)
AS 
SELECT M_Transaction_ID, 
  t.AD_Client_ID, t.AD_Org_ID, t.IsActive, t.Created,t.CreatedBy, t.Updated,t.UpdatedBy,
  t.MovementType, t.M_Locator_ID, t.M_Product_ID, t.MovementDate, t.MovementQty,
  t.M_InventoryLine_ID, i.M_Inventory_ID,
  t.M_MovementLine_ID, m.M_Movement_ID,
  t.M_InOutLine_ID, io.M_InOut_ID,
  t.M_ProductionLine_ID, pp.M_Production_ID,
  t.C_ProjectIssue_ID, pi.C_Project_ID,
  t.M_AttributeSetInstance_ID
FROM M_Transaction t
  LEFT OUTER JOIN M_InOutLine io ON (t.M_InOutLine_ID=io.M_InOutLine_ID AND t.M_AttributeSetInstance_ID=io.M_AttributeSetInstance_ID)
  LEFT OUTER JOIN M_MovementLine m ON (t.M_MovementLine_ID=m.M_MovementLine_ID AND t.M_AttributeSetInstance_ID=m.M_AttributeSetInstance_ID)
  LEFT OUTER JOIN M_InventoryLine i ON (t.M_InventoryLine_ID=i.M_InventoryLine_ID AND t.M_AttributeSetInstance_ID=i.M_AttributeSetInstance_ID)
  LEFT OUTER JOIN C_ProjectIssue pi ON (t.C_ProjectIssue_ID=pi.C_ProjectIssue_ID AND t.M_AttributeSetInstance_ID=pi.M_AttributeSetInstance_ID)
  LEFT OUTER JOIN M_ProductionLine pl ON (t.M_ProductionLine_ID=pl.M_ProductionLine_ID AND t.M_AttributeSetInstance_ID=pl.M_AttributeSetInstance_ID)
  LEFT OUTER JOIN M_ProductionPlan pp ON (pl.M_ProductionPlan_ID=pp.M_ProductionPlan_ID);
