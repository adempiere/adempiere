CREATE OR REPLACE VIEW RV_COSTSUMMARY
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATEDBY, CREATED, 
 UPDATEDBY, UPDATED, M_PRODUCT_ID, VALUE, NAME, 
 UPC, ISBOM, PRODUCTTYPE, M_PRODUCT_CATEGORY_ID, M_COSTTYPE_ID, 
 C_ACCTSCHEMA_ID, C_CURRENCY_ID, CURRENTCOSTPRICE, FUTURECOSTPRICE)
AS 
SELECT 	c.AD_Client_ID, c.AD_Org_ID, c.IsActive, 0 AS CreatedBy,getdate() AS Created,0 AS UpdatedBy,getdate() AS Updated,
    p.M_Product_ID, p.Value, p.Name, p.UPC, p.IsBOM, p.ProductType, p.M_Product_Category_ID,
    c.M_CostType_ID, 
    acct.C_AcctSchema_ID, acct.C_Currency_ID,
    SUM(c.CurrentCostPrice) AS CurrentCostPrice, SUM(c.FutureCostPrice) AS FutureCostPrice
FROM M_Cost c
  INNER JOIN M_Product p ON (c.M_Product_ID=p.M_Product_ID)
  INNER JOIN C_AcctSchema acct ON (c.C_AcctSchema_ID=acct.C_AcctSchema_ID)
WHERE acct.M_CostType_ID=c.M_CostType_ID
GROUP BY c.AD_Client_ID, c.AD_Org_ID, c.IsActive, 
    p.M_Product_ID, p.Value, p.Name, p.UPC, p.IsBOM, p.ProductType, p.M_Product_Category_ID,
    c.M_CostType_ID, acct.C_AcctSchema_ID, acct.C_Currency_ID;



