SET SQLBLANKLINES ON
SET DEFINE OFF

DROP VIEW RV_M_Transaction_Costing;
CREATE OR REPLACE VIEW RV_M_Transaction_Costing AS
SELECT 
t.M_Transaction_ID, t.AD_Client_ID,t.AD_Org_ID,
t.MovementType,t.MovementDate,t.MovementQty,
t.M_AttributeSetInstance_ID, t.M_AttributeSet_ID, t.SerNo, t.Lot, t.M_Lot_ID, t.GuaranteeDate,
p.M_Product_ID,p.Value,p.Name,p.Description,p.UPC,p.SKU,
p.C_UOM_ID,p.M_Product_Category_ID,p.Classification, p.Group1, p.Group2, p.Weight,p.Volume,p.VersionNo,
t.DocumentNo,
t.C_DocType_ID,
t.M_Locator_ID, 
t.X, t.Y, t.Z,
t.M_Warehouse_ID,
t.M_InventoryLine_ID,t.M_Inventory_ID,
t.M_MovementLine_ID,t.M_Movement_ID,
t.M_InOutLine_ID,t.M_InOut_ID,
t.M_ProductionLine_ID,t.M_ProductionPlan_ID,t.M_Production_ID,
t.C_ProjectIssue_ID,t.C_Project_ID,
t.PP_Cost_Collector_ID,
cd.C_AcctSchema_ID,
cd.M_CostType_ID,
cd.M_CostElement_ID,	
cd.CostAdjustmentDate,
cd.CostAdjustmentDateLL,
cd.DateAcct,
cd.CumulatedQty AS BeginningQtyBalance,
cd.CurrentCostPrice,
cd.CurrentCostPriceLL,
cd.isReversal,
cd.IsSOTrx,
cd.M_CostDetail_ID,
cd.CumulatedAmt + cd.CumulatedAmtLL AS BeginningBalance,
cd.qty,
(CASE WHEN cd.Qty<0 THEN  cd.Amt * -1 ELSE cd.Amt END) AS Amt,
(CASE WHEN cd.Qty<0 THEN  cd.AmtLL * -1 ELSE cd.AmtLL END) AS AmtLL,
(CASE WHEN cd.Qty<0 THEN  cd.CostAmt * -1 ELSE cd.CostAmt END) AS CostAmt,
(CASE WHEN cd.Qty<0 THEN  cd.CostAmtLL * -1 ELSE cd.CostAmtLL END) AS CostAmtLL,
(CASE WHEN cd.Qty<0 THEN  cd.CostAdjustment * -1 ELSE cd.CostAdjustment END) AS CostAdjustment,	
(CASE WHEN cd.Qty<0 THEN  cd.CostAdjustmentLL * -1 ELSE  cd.CostAdjustmentLL END) AS CostAdjustmentLL,
cd.CumulatedAmt,
cd.CumulatedAmtLL,
cd.CumulatedQty + Qty AS EndingQtyBalance,
cd.CumulatedAmt + 
cd.CumulatedAmtLL + 
(CASE WHEN cd.Qty<0 THEN  cd.CostAmt * -1 ELSE cd.CostAmt END) + 
(CASE WHEN cd.Qty<0 THEN  cd.CostAmtLL * -1 ELSE cd.CostAmtLL END) +
(CASE WHEN cd.Qty<0 THEN  cd.CostAdjustment * -1 ELSE cd.CostAdjustment END) + 
(CASE WHEN cd.Qty<0 THEN  cd.CostAdjustmentLL * -1 ELSE  cd.CostAdjustmentLL END) AS EndingBalance
FROM M_Product p 
INNER JOIN RV_Transaction t ON (t.M_Product_ID=p.M_Product_ID)
LEFT OUTER JOIN M_CostDetail cd ON (cd.M_Transaction_ID=t.M_Transaction_ID AND cd.M_Product_ID=p.M_Product_ID) 
LEFT OUTER JOIN M_CostType ct ON (ct.M_CostType_ID=cd.M_CostType_ID)
LEFT OUTER JOIN M_CostElement ce ON (ce.M_CostElement_ID=cd.M_CostElement_ID);


-- Nov 16, 2010 8:41:58 PM CST
-- Cost Engine
UPDATE AD_Column SET FieldLength=22,Updated=TO_DATE('2010-11-16 20:41:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59913
;

-- Nov 16, 2010 8:45:21 PM CST
-- Cost Engine
UPDATE AD_Tab SET OrderByClause='DateAcct , M_CostDetail_ID',Updated=TO_DATE('2010-11-16 20:45:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=748
;

-- Nov 17, 2010 1:53:31 AM CST
-- Cost Engine
UPDATE AD_PrintFormatItem SET SortNo=50,IsOrderBy='Y' WHERE AD_PrintFormatItem_ID=51588
;

-- Nov 17, 2010 1:53:31 AM CST
-- Cost Engine
UPDATE AD_PrintFormatItem SET SortNo=60,IsOrderBy='Y' WHERE AD_PrintFormatItem_ID=51589
;

-- Nov 17, 2010 1:54:58 AM CST
-- Cost Engine
UPDATE AD_Column SET DefaultValue='N',Updated=TO_DATE('2010-11-17 01:54:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59917
;

UPDATE M_CostElement SET IsDefault='N' WHERE IsDefault IS NULL;

-- Nov 17, 2010 1:55:11 AM CST
-- Cost Engine
ALTER TABLE M_CostElement MODIFY IsDefault CHAR(1) DEFAULT 'N'
;
