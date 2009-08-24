CREATE OR REPLACE VIEW RV_TRANSACTION
(M_TRANSACTION_ID, AD_CLIENT_ID, AD_ORG_ID, MOVEMENTTYPE, MOVEMENTDATE, 
 MOVEMENTQTY, M_ATTRIBUTESETINSTANCE_ID, M_ATTRIBUTESET_ID, SERNO, LOT, 
 M_LOT_ID, GUARANTEEDATE, M_PRODUCT_ID, VALUE, NAME, 
 DESCRIPTION, UPC, SKU, C_UOM_ID, M_PRODUCT_CATEGORY_ID, 
 CLASSIFICATION, WEIGHT, VOLUME, VERSIONNO, M_LOCATOR_ID, 
 M_WAREHOUSE_ID, X, Y, Z, M_INVENTORYLINE_ID, 
 M_INVENTORY_ID, M_MOVEMENTLINE_ID, M_MOVEMENT_ID, M_INOUTLINE_ID, M_INOUT_ID, 
 M_PRODUCTIONLINE_ID, M_PRODUCTIONPLAN_ID, M_PRODUCTION_ID, C_PROJECTISSUE_ID, C_PROJECT_ID, 
 LINE)
AS 
SELECT t.M_Transaction_ID, t.AD_Client_ID,t.AD_Org_ID,
    t.MovementType,t.MovementDate,t.MovementQty,
    -- Instance
    t.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,asi.GuaranteeDate,
    -- Product
    t.M_Product_ID,p.Value,p.Name,p.Description,p.UPC,p.SKU,
    p.C_UOM_ID,p.M_Product_Category_ID,p.Classification, p.Weight,p.Volume,p.VersionNo,
    -- Locator
    t.M_Locator_ID, l.M_Warehouse_ID, l.X, l.Y, l.Z,
    -- Inventory
    t.M_InventoryLine_ID,il.M_Inventory_ID,
    -- Movement
    t.M_MovementLine_ID,ml.M_Movement_ID,
    -- In/Out
    t.M_InOutLine_ID,iol.M_InOut_ID,
    -- Production
    t.M_ProductionLine_ID,prdl.M_ProductionPlan_ID,prdp.M_Production_ID,
    -- ProjectIssue
    t.C_ProjectIssue_ID,pjl.C_Project_ID,
    COALESCE(il.Line,ml.Line,iol.Line,prdl.Line,pjl.Line) AS Line
FROM M_Transaction t
  INNER JOIN M_Locator l ON (t.M_Locator_ID=l.M_Locator_ID)
  INNER JOIN M_Product p ON (t.M_Product_ID=p.M_Product_ID)
  LEFT OUTER JOIN M_AttributeSetInstance asi ON (t.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
  LEFT OUTER JOIN M_InventoryLine il ON (t.M_InventoryLine_ID=il.M_InventoryLine_ID)
  LEFT OUTER JOIN M_MovementLine ml ON (t.M_MovementLine_ID=ml.M_MovementLine_ID)
  LEFT OUTER JOIN M_InOutLine iol ON (t.M_InOutLine_ID=iol.M_InOutLine_ID)
  LEFT OUTER JOIN M_ProductionLine prdl ON (t.M_ProductionLine_ID=prdl.M_ProductionLine_ID)
  LEFT OUTER JOIN M_ProductionPlan prdp ON (prdl.M_ProductionPlan_ID=prdp.M_ProductionPlan_ID)
  LEFT OUTER JOIN C_ProjectIssue pjl ON (t.C_ProjectIssue_ID=pjl.C_ProjectIssue_ID);



