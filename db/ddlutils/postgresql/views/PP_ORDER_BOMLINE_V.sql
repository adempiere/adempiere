DROP VIEW  PP_Order_BOMLine_v;	
CREATE OR REPLACE VIEW PP_Order_BOMLine_v
AS 
SELECT obl.AD_Client_ID, obl.AD_Org_ID, obl.IsActive, obl.Created, obl.CreatedBy, obl.Updated, obl.UpdatedBy,
cast('en_US' as varchar) AS AD_Language,
obl.Description , feature , obl.M_Product_ID, obl.backflushgroup ,obl.C_UOM_ID, obl.componentType, obl.datedelivered, obl.forecast, obl.help ,
obl.iscritical, obl.issuemethod , obl.leadtimeoffset, obl.line, obl.m_attributesetinstance_id , obl.m_changenotice_id, obl.m_locator_id , obl.m_warehouse_id, 
obl.pp_order_bom_ID,obl.pp_order_bomLine_id,obl.pp_order_id, obl.qtydelivered, obl.qtypost, obl.qtyreject, obl.qtyscrap, obl.scrap , obl.validfrom, obl.validto , obl.assay, 
obl.ad_user_id,
round(obl.qtyrequiered, 4) AS qtyrequiered,
round(bomqtyreserved(obl.m_product_id, obl.m_warehouse_id, 0), 4) AS qtyreserved,
round(bomqtyavailable(obl.m_product_id, obl.m_warehouse_id, 0), 4) AS qtyavailable, 
round(bomqtyonhand(obl.m_product_id, obl.m_warehouse_id, 0), 4) AS qtyonhand,
round(obl.qtybom, 4) AS qtybom,
obl.isqtypercentage,
round(obl.qtybatch, 4) AS qtybatch, 
CASE WHEN o.qtybatchs = 0 THEN 1 ELSE round(obl.qtyrequiered / o.qtybatchs, 4) END AS qtybatchsize  
FROM PP_Order_BOMLine obl
INNER JOIN PP_Order o ON (o.PP_Order_ID=obl.PP_Order_ID);
