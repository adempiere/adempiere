CREATE OR REPLACE VIEW RV_COST
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, M_PRODUCT_ID, VALUE, NAME, 
 UPC, ISBOM, PRODUCTTYPE, M_PRODUCT_CATEGORY_ID, M_COSTTYPE_ID, 
 M_COSTELEMENT_ID, COSTELEMENTTYPE, COSTINGMETHOD, ISCALCULATED, C_ACCTSCHEMA_ID, 
 C_CURRENCY_ID, CURRENTCOSTPRICE, FUTURECOSTPRICE, DESCRIPTION)
AS 
SELECT 	c.AD_Client_ID, c.AD_Org_ID, c.IsActive, c.Created,c.CreatedBy,c.Updated,c.UpdatedBy,
    p.M_Product_ID, p.Value, p.Name, p.UPC, p.IsBOM, p.ProductType, p.M_Product_Category_ID,
    c.M_CostType_ID, 
    ce.M_CostElement_ID, ce.CostElementType, ce.CostingMethod, ce.IsCalculated, 
    acct.C_AcctSchema_ID, acct.C_Currency_ID,
    c.CurrentCostPrice, c.FutureCostPrice, c.Description
FROM M_Cost c
  INNER JOIN M_Product p ON (c.M_Product_ID=p.M_Product_ID)
  INNER JOIN M_CostElement ce ON (c.M_CostElement_ID=ce.M_CostElement_ID)
  INNER JOIN C_AcctSchema acct ON (c.C_AcctSchema_ID=acct.C_AcctSchema_ID);



