DROP VIEW PP_Order_BOMLine_vt;	
CREATE OR REPLACE VIEW PP_Order_BOMLine_vt
AS 
SELECT 
obl.AD_Client_ID, obl.AD_Org_ID, obl.IsActive, obl.Created, obl.CreatedBy, obl.Updated, obl.UpdatedBy,
oblt.AD_Language,
oblt.Description , obl.feature , obl.m_Product_ID, obl.backflushgroup ,obl.C_UOM_ID, obl.componentType, obl.datedelivered, obl.forecast, oblt.help , 
obl.iscritical, obl.issuemethod , obl.leadtimeoffset, obl.line, obl.m_attributesetinstance_id , obl.m_changenotice_id, obl.m_locator_id , obl.m_warehouse_id, 
obl.pp_order_bom_ID, obl.pp_order_bomline_ID,obl.pp_order_id, obl.qtydelivered, obl.qtypost, obl.qtyreject, obl.qtyscrap, obl.scrap , obl.validfrom, obl.validto , obl.assay, 
obl.ad_user_id,o.QtyBatchs,
round(obl.qtyrequiered, 4) AS qtyrequiered,
round(bomqtyreserved(obl.m_product_id, obl.m_warehouse_id, 0), 4) AS qtyreserved,
round(bomqtyavailable(obl.m_product_id, obl.m_warehouse_id, 0), 4) AS qtyavailable, 
round(bomqtyonhand(obl.m_product_id, obl.m_warehouse_id, 0), 4) AS qtyonhand,
round(obl.qtybom, 4) AS qtybom,
obl.isqtypercentage,
round(obl.qtybatch, 4) AS qtybatch, 
CASE WHEN o.qtybatchs = 0 THEN 1 ELSE round(obl.qtyrequiered / o.qtybatchs, 4) END AS qtybatchsize 
FROM PP_Order_BOMLine obl
INNER JOIN PP_Order o ON (o.PP_Order_ID=obl.PP_Order_ID)
LEFT JOIN PP_Order_BOMLine_Trl oblt ON (oblt.PP_Order_BOMLine_ID=obl.PP_Order_BOMLine_ID);
	
