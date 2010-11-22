CREATE OR REPLACE VIEW rv_pp_order_storage AS 
SELECT 
obl.ad_client_id,
obl.ad_org_id,
obl.createdby,
obl.updatedby,
obl.updated,
obl.created,
obl.isactive,
obl.pp_order_bom_id,
obl.pp_order_bomline_id,
obl.pp_order_id,
obl.iscritical,
obl.m_product_id,
( SELECT p.name FROM m_product p WHERE p.m_product_id = o.m_product_id) AS name,
obl.c_uom_id,
s.qtyonhand,
round(obl.qtyrequiered, 4) AS qtyrequiered, 
CASE WHEN o.qtybatchs = 0 THEN 1 ELSE round(obl.qtyrequiered / o.qtybatchs, 4) END AS qtybatchsize,
round(bomqtyreserved(obl.m_product_id,obl.m_warehouse_id, 0), 4) AS qtyreserved,
round(bomqtyavailable(obl.m_product_id, obl.m_warehouse_id,0), 4) AS qtyavailable,
obl.m_warehouse_id,
obl.qtybom,
obl.isqtypercentage,
round(obl.qtybatch, 4) AS qtybatch,
obl.m_attributesetinstance_id,
l.m_locator_id,
l.x,
l.y,
l.z
FROM pp_order_bomline obl
JOIN pp_order o ON o.pp_order_id = obl.pp_order_id
LEFT JOIN m_storage s ON s.m_product_id = obl.m_product_id AND s.qtyonhand <> 0 AND obl.m_warehouse_id = (( SELECT ld.m_warehouse_id FROM m_locator ld WHERE s.m_locator_id = ld.m_locator_id))
LEFT JOIN m_locator l ON l.m_locator_id = s.m_locator_id
ORDER BY obl.m_product_id;