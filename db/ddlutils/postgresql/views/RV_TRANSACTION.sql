--DROP VIEW RV_TRANSACTION;
CREATE OR REPLACE VIEW RV_TRANSACTION
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
    t.PP_Cost_Collector_ID,
    COALESCE(il.Line,ml.Line,iol.Line,prdl.Line,pjl.Line) AS Line,
    COALESCE(i.movementdate, m.movementdate, io.dateacct, prd.movementdate, pjl.movementdate, cc.dateacct) AS dateacct, 
    COALESCE(i.documentno, m.documentno, io.documentno, prd.name, pj.value, cc.documentno) AS documentno,
    COALESCE(i.c_doctype_id, m.c_doctype_id, io.c_doctype_id, 0, 0, cc.c_doctype_id) AS c_doctype_id   
FROM M_Transaction t
  INNER JOIN M_Locator l ON (t.M_Locator_ID=l.M_Locator_ID)
  INNER JOIN M_Product p ON (t.M_Product_ID=p.M_Product_ID)
  LEFT OUTER JOIN M_AttributeSetInstance asi ON (t.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
  LEFT OUTER JOIN M_InventoryLine il ON (t.M_InventoryLine_ID=il.M_InventoryLine_ID)
  LEFT OUTER JOIN m_inventory i ON (il.m_inventory_id = i.m_inventory_id)
  LEFT OUTER JOIN M_MovementLine ml ON (t.M_MovementLine_ID=ml.M_MovementLine_ID)
  LEFT OUTER JOIN m_movement m ON (ml.m_movement_id = m.m_movement_id)
  LEFT OUTER JOIN M_InOutLine iol ON (t.M_InOutLine_ID=iol.M_InOutLine_ID)
  LEFT OUTER JOIN m_inout io ON (iol.m_inout_id = io.m_inout_id)
  LEFT OUTER JOIN M_ProductionLine prdl ON (t.M_ProductionLine_ID=prdl.M_ProductionLine_ID)
  LEFT OUTER JOIN M_ProductionPlan prdp ON (prdl.M_ProductionPlan_ID=prdp.M_ProductionPlan_ID)
  LEFT OUTER JOIN m_production prd ON prdp.m_production_id = prd.m_production_id
  LEFT OUTER JOIN C_ProjectIssue pjl ON (t.C_ProjectIssue_ID=pjl.C_ProjectIssue_ID)
  LEFT OUTER JOIN c_project pj ON pjl.c_project_id = pj.c_project_id
  LEFT OUTER JOIN pp_cost_collector cc ON t.pp_cost_collector_id = cc.pp_cost_collector_id;