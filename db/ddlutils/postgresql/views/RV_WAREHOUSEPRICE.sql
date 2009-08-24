CREATE OR REPLACE VIEW RV_WAREHOUSEPRICE
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, M_PRODUCT_ID, M_PRICELIST_VERSION_ID, M_WAREHOUSE_ID, 
 VALUE, NAME, UPC, SKU, C_UOM_ID, 
 UOMSYMBOL, PRICELIST, PRICESTD, MARGIN, PRICELIMIT, 
 WAREHOUSENAME, QTYAVAILABLE, QTYONHAND, QTYRESERVED, QTYORDERED, 
 ISINSTANCEATTRIBUTE)
AS 
SELECT w.AD_Client_ID, w.AD_Org_ID, 
    CASE WHEN p.Discontinued='N' THEN 'Y' ELSE 'N' END AS IsActive, 
    pr.Created, pr.CreatedBy, pr.Updated, pr.UpdatedBy,
    p.M_Product_ID, pr.M_PriceList_Version_ID, w.M_Warehouse_ID,
    p.Value, p.Name, p.UPC, p.SKU,
    uom.C_UOM_ID, uom.UOMSymbol,
    bomPriceList(p.M_Product_ID, pr.M_PriceList_Version_ID) AS PriceList, 
    bomPriceStd(p.M_Product_ID, pr.M_PriceList_Version_ID) AS PriceStd, 
    bomPriceStd(p.M_Product_ID, pr.M_PriceList_Version_ID)-bomPriceLimit(p.M_Product_ID, pr.M_PriceList_Version_ID) AS Margin, 
    bomPriceLimit(p.M_Product_ID, pr.M_PriceList_Version_ID) AS PriceLimit, 
    w.Name AS WarehouseName,
    bomQtyAvailable(p.M_Product_ID,w.M_Warehouse_ID,0) AS QtyAvailable, 
    bomQtyOnHand(p.M_Product_ID,w.M_Warehouse_ID,0) AS QtyOnHand, 
    bomQtyReserved(p.M_Product_ID,w.M_Warehouse_ID,0) AS QtyReserved, 
    bomQtyOrdered(p.M_Product_ID,w.M_Warehouse_ID,0) AS QtyOrdered, 
    COALESCE (pa.IsInstanceAttribute, 'N') AS IsInstanceAttribute
FROM M_Product p 
    INNER JOIN M_ProductPrice pr ON (p.M_Product_ID=pr.M_Product_ID)
    INNER JOIN C_UOM uom ON (p.C_UOM_ID=uom.C_UOM_ID)
    LEFT OUTER JOIN M_AttributeSet pa ON (p.M_AttributeSet_ID=pa.M_AttributeSet_ID)
    INNER JOIN M_Warehouse w ON (p.AD_Client_ID=w.AD_Client_ID)
WHERE p.IsSummary='N' AND p.IsActive='Y' AND pr.IsActive='Y' AND w.IsActive='Y';

--AND pr.M_PriceList_Version_ID=? 
--AND w.M_Warehouse_ID=?
--AND UPPER(p.Value) LIKE ? AND UPPER(p.Name) LIKE ? 
--AND UPPER(p.UPC) LIKE ? AND UPPER(p.SKU) LIKE ? 
--ORDER BY QtyAvailable DESC, Margin DESC;
