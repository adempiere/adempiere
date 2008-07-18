-- DROP VIEW rv_pp_order_bomline;
CREATE OR REPLACE VIEW rv_pp_order_bomline AS 
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
obl.componenttype,
obl.m_product_id,
obl.c_uom_id,
round(obl.qtyrequiered, 4) AS qtyrequiered,
round(bomqtyreserved(obl.m_product_id, obl.m_warehouse_id, 0), 4) AS qtyreserved,
round(bomqtyavailable(obl.m_product_id, obl.m_warehouse_id, 0), 4) AS qtyavailable, 
round(bomqtyonhand(obl.m_product_id, obl.m_warehouse_id, 0), 4) AS qtyonhand,
obl.m_warehouse_id,
round(obl.qtybom, 4) AS qtybom,
obl.isqtypercentage,
round(obl.qtybatch, 4) AS qtybatch, 
CASE WHEN o.qtybatchs = 0 THEN 1 ELSE round(obl.qtyrequiered / o.qtybatchs, 4) END AS qtybatchsize
FROM pp_order_bomline obl
JOIN pp_order o ON o.pp_order_id = obl.pp_order_id;
