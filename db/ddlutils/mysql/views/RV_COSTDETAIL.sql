CREATE OR REPLACE VIEW RV_COSTDETAIL
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, M_PRODUCT_ID, VALUE, NAME, 
 UPC, ISBOM, PRODUCTTYPE, M_PRODUCT_CATEGORY_ID, M_INOUTLINE_ID, 
 C_INVOICELINE_ID, M_ATTRIBUTESETINSTANCE_ID, M_ATTRIBUTESET_ID, LOT, SERNO, 
 C_ACCTSCHEMA_ID, C_CURRENCY_ID, AMT, QTY, DESCRIPTION, 
 PROCESSED)
AS 
SELECT 	c.AD_Client_ID, c.AD_Org_ID, c.IsActive, c.Created,c.CreatedBy,c.Updated,c.UpdatedBy,
    p.M_Product_ID, p.Value, p.Name, p.UPC, p.IsBOM, p.ProductType, p.M_Product_Category_ID,
    c.M_InOutLine_ID, c.C_InvoiceLine_ID,
    asi.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.Lot, asi.SerNo,
    acct.C_AcctSchema_ID, acct.C_Currency_ID,
    c.Amt, c.Qty, c.Description, Processed
FROM M_CostDetail c
  INNER JOIN M_Product p ON (c.M_Product_ID=p.M_Product_ID)
  INNER JOIN C_AcctSchema acct ON (c.C_AcctSchema_ID=acct.C_AcctSchema_ID)
  INNER JOIN M_AttributeSetInstance asi ON (c.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID);



