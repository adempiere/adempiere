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
    t.M_ProductionLine_ID,prdl.M_ProductionPlan_ID,prdl.M_Production_ID,
    -- ProjectIssue
    t.C_ProjectIssue_ID,
    t.PP_Cost_Collector_ID,
    COALESCE(il.Line,ml.Line,iol.Line,prdl.Line,pjl.Line) AS Line,
    COALESCE(i.movementdate, m.movementdate, io.dateacct, prd.movementdate, pjl.movementdate, cc.dateacct) AS dateacct,
    COALESCE(i.documentno, m.documentno, io.documentno, prd.name, pj.value, cc.documentno) AS documentno,
    COALESCE(i.c_doctype_id, m.c_doctype_id, io.c_doctype_id, 0, 0, cc.c_doctype_id) AS c_doctype_id,
    -- DIMENSION
    COALESCE(pjl.C_Project_ID, iol.C_Project_ID , i.C_Project_ID, m.C_Project_ID,cc.C_Project_ID) AS C_Project_ID,
    COALESCE(i.C_Activity_ID,iol.C_Activity_ID,m.C_Activity_ID, cc.C_Activity_ID) AS C_Activity_ID,
    COALESCE(i.C_Campaign_ID,iol.C_Campaign_ID, m.C_Campaign_ID) AS C_Campaign_ID,
    0 AS C_Region_ID,
    COALESCE(il.CreatedBy, io.C_BPartner_ID, m.C_BPartner_ID ) AS C_BPartner_ID,
    COALESCE(i.User1_ID, iol.User1_ID, m.User1_ID , cc.User1_ID) AS User1_ID,
    COALESCE(i.User2_ID, iol.User2_ID, m.User2_ID , cc.User2_ID) AS User2_ID,
    COALESCE(i.User3_ID, iol.User1_ID, m.User3_ID , cc.User3_ID) AS User3_ID,
    COALESCE(i.User4_ID, iol.User4_ID, m.User4_ID , cc.User4_ID) AS User4_ID,
    p.lowlevel
FROM M_Transaction t
  INNER JOIN M_Locator l ON (t.M_Locator_ID=l.M_Locator_ID)
  INNER JOIN M_Product p ON (t.M_Product_ID=p.M_Product_ID)
  LEFT OUTER JOIN M_AttributeSetInstance asi ON (t.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID)
  LEFT OUTER JOIN M_InventoryLine il ON (t.M_InventoryLine_ID=il.M_InventoryLine_ID)
  LEFT OUTER JOIN M_Inventory i ON (il.m_inventory_id = i.m_inventory_id)
  LEFT OUTER JOIN M_MovementLine ml ON (t.M_MovementLine_ID=ml.M_MovementLine_ID)
  LEFT OUTER JOIN M_movement m ON (ml.m_movement_id = m.m_movement_id)
  LEFT OUTER JOIN M_InOutLine iol ON (t.M_InOutLine_ID=iol.M_InOutLine_ID)
  LEFT OUTER JOIN M_InOut io ON (iol.M_InOut_ID = io.M_InOut_ID)
  LEFT OUTER JOIN M_ProductionLine prdl ON (t.M_ProductionLine_ID=prdl.M_ProductionLine_ID)
  --LEFT OUTER JOIN M_ProductionPlan prdp ON (prdl.M_ProductionPlan_ID=prdp.M_ProductionPlan_ID)
  LEFT OUTER JOIN M_Production prd ON prdl.M_Production_ID = prd.M_Production_ID
  LEFT OUTER JOIN C_ProjectIssue pjl ON (t.C_ProjectIssue_ID=pjl.C_ProjectIssue_ID)
  LEFT OUTER JOIN C_Project pj ON pjl.C_Project_id = pj.C_Project_id
  LEFT OUTER JOIN PP_Cost_Collector cc ON t.PP_Cost_Collector_ID = cc.PP_Cost_Collector_ID;