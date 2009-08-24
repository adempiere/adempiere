CREATE OR REPLACE VIEW RV_STORAGE
(AD_CLIENT_ID, AD_ORG_ID, M_PRODUCT_ID, VALUE, NAME, 
 DESCRIPTION, UPC, SKU, C_UOM_ID, M_PRODUCT_CATEGORY_ID, 
 CLASSIFICATION, WEIGHT, VOLUME, VERSIONNO, GUARANTEEDAYS, 
 GUARANTEEDAYSMIN, M_LOCATOR_ID, M_WAREHOUSE_ID, X, Y, 
 Z, QTYONHAND, QTYRESERVED, QTYAVAILABLE, QTYORDERED, 
 DATELASTINVENTORY, M_ATTRIBUTESETINSTANCE_ID, M_ATTRIBUTESET_ID, SERNO, LOT, 
 M_LOT_ID, GUARANTEEDATE, SHELFLIFEDAYS, GOODFORDAYS, SHELFLIFEREMAININGPCT)
AS 
SELECT s.AD_Client_ID, s.AD_Org_ID,
    -- Product
    s.M_Product_ID, p.Value,p.Name, p.Description, p.UPC, p.SKU,
    p.C_UOM_ID, p.M_Product_Category_ID, p.Classification, p.Weight, p.Volume, p.VersionNo,
    p.GuaranteeDays, p.GuaranteeDaysMin,
    --  Locator
    s.M_Locator_ID, l.M_Warehouse_ID, l.X, l.Y, l.Z,
    -- Storage
    s.QtyOnHand, s.QtyReserved, s.QtyOnHand-s.QtyReserved AS QtyAvailable, 
    s.QtyOrdered, s.DateLastInventory,
    -- Instance
    s.M_AttributeSetInstance_ID, asi.M_AttributeSet_ID, asi.SerNo, asi.Lot, asi.M_Lot_ID,
    asi.GuaranteeDate,  -- see PAttributeInstance.java
    daysBetween(asi.GuaranteeDate,getdate()) AS ShelfLifeDays,
    daysBetween(asi.GuaranteeDate,getdate())-p.GuaranteeDaysMin AS GoodForDays,
    CASE WHEN COALESCE(p.GuaranteeDays,0)>0 
      THEN ROUND((daysBetween(asi.GuaranteeDate,getdate())/p.GuaranteeDays)*100,0) 
      ELSE NULL 
    END AS ShelfLifeRemainingPct
FROM M_Storage s
  INNER JOIN M_Locator l ON (s.M_Locator_ID=l.M_Locator_ID)
  INNER JOIN M_Product p ON (s.M_Product_ID=p.M_Product_ID)
  LEFT OUTER JOIN M_AttributeSetInstance asi ON (s.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID);



