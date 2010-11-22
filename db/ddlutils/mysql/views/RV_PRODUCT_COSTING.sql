CREATE OR REPLACE VIEW RV_PRODUCT_COSTING
(M_PRODUCT_ID, C_ACCTSCHEMA_ID, VALUE, NAME, M_PRODUCT_CATEGORY_ID, 
 AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, CURRENTCOSTPRICE, FUTURECOSTPRICE, COSTSTANDARD, 
 COSTSTANDARDPOQTY, COSTSTANDARDPOAMT, COSTSTANDARDPODIFF, COSTSTANDARDCUMQTY, COSTSTANDARDCUMAMT, 
 COSTSTANDARDINVDIFF, COSTAVERAGE, COSTAVERAGECUMQTY, COSTAVERAGECUMAMT, TOTALINVQTY, 
 TOTALINVAMT, TOTALINVCOST, PRICELASTPO, PRICELASTINV)
AS 
SELECT  pc.M_Product_ID, pc.C_AcctSchema_ID, p.Value, p.Name, p.M_Product_Category_ID,
	pc.AD_Client_ID, pc.AD_Org_ID, pc.IsActive, pc.Created,pc.CreatedBy,pc.Updated,pc.UpdatedBy,
	pc.CurrentCostPrice,
  --  Standard Costing
	pc.FutureCostPrice, pc.CostStandard, 
	pc.CostStandardPOQty, pc.CostStandardPOAmt, 
	CASE WHEN pc.CostStandardPOQty=0 THEN 0 ELSE pc.CostStandardPOAmt/pc.CostStandardPOQty END AS CostStandardPODiff,
	pc.CostStandardCumQty, pc.CostStandardCumAmt,
	CASE WHEN pc.CostStandardCumQty=0 THEN 0 ELSE pc.CostStandardCumAmt/pc.CostStandardCumQty END AS CostStandardInvDiff,
  --  Average Costing
	pc.CostAverage,
	pc.CostAverageCumQty, pc.CostAverageCumAmt,
	pc.TotalInvQty, pc.TotalInvAmt,
	CASE WHEN pc.TotalInvQty=0 THEN 0 ELSE pc.TotalInvAmt/pc.TotalInvQty END AS TotalInvCost,
  --  LastPrice
	pc.PriceLastPO, pc.PriceLastInv
FROM M_Product_Costing pc
  INNER JOIN M_Product p ON (pc.M_Product_ID=p.M_Product_ID);



