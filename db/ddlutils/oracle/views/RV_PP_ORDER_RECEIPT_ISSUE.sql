CREATE OR REPLACE VIEW rv_pp_order_receipt_issue AS 
SELECT obl.pp_order_bomline_id,
obl.iscritical,
p.value,
obl.m_product_id,
mos.name AS productname,
mos.m_attributesetinstance_id,
asi.description AS instancename,
mos.c_uom_id,
u.name AS uomname,
obl.qtyrequiered,
obl.qtyreserved AS qtyreserved_order,
mos.qtyonhand,
mos.qtyreserved AS qtyreserved_storage,
mos.qtyavailable,
mos.m_locator_id,
mos.m_warehouse_id,
w.name AS warehousename,
mos.qtybom,
mos.isqtypercentage,
mos.qtybatch,
obl.componenttype,
mos.qtyrequiered - obl.qtydelivered AS qtyopen,
obl.pp_order_id
FROM rv_pp_order_storage mos
JOIN pp_order_bomline obl ON mos.pp_order_bomline_id = obl.pp_order_bomline_id
JOIN m_attributesetinstance asi ON mos.m_attributesetinstance_id = asi.m_attributesetinstance_id
JOIN c_uom u ON mos.c_uom_id = u.c_uom_id
JOIN m_product p ON mos.m_product_id = p.m_product_id
JOIN m_warehouse w ON mos.m_warehouse_id = w.m_warehouse_id;