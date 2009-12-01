CREATE OR REPLACE VIEW RV_STORAGE_PER_PRODUCT
AS
SELECT strg.AD_Client_ID, strg.AD_Org_ID
 , p.M_Product_ID, p.Value, p.Name
 , p.Description, p.UPC, p.SKU
 , p.C_UOM_ID, p.M_Product_Category_ID, p.Classification, p.Weight, p.Volume, p.VersionNo
 , p.GuaranteeDays, p.GuaranteeDaysMin
 , strg.SumQtyOnHand
FROM M_Product p
  INNER JOIN ( SELECT M_Product_ID, M_Locator_ID, SUM (QtyOnHand) as SumQtyOnHand
                      , AD_Client_ID, AD_Org_ID
               FROM RV_Storage
               GROUP BY M_Product_ID, M_Locator_ID, AD_Client_ID, AD_Org_ID) strg ON (p.M_Product_ID = strg.M_Product_ID)
  INNER JOIN M_Locator l ON (strg.M_Locator_ID=l.M_Locator_ID)
;

