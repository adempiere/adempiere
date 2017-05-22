-- DROP VIEW rv_storage_per_product;
CREATE VIEW rv_storage_per_product (
ad_client_id,
ad_org_id,
m_product_id,
value,
name,
description,
upc,
sku,
c_uom_id,
m_product_category_id,
classification,
weight,
volume,
versionno,
guaranteedays,
guaranteedaysmin,
sumqtyonhand,
sumqtyreserved,
m_warehouse_id
) AS
SELECT
strg.ad_client_id,
strg.ad_org_id,
p.m_product_id,
p.value,
p.name,
p.description,
p.upc,
p.sku,
p.c_uom_id,
p.m_product_category_id,
p.classification,
p.weight,
p.volume,
p.versionno,
p.guaranteedays,
p.guaranteedaysmin,
strg.sumqtyonhand,
strg.sumqtyreserved,
l.m_warehouse_id
FROM
m_product p
JOIN
(
SELECT
rv_storage.m_product_id,
rv_storage.m_locator_id,
SUM(rv_storage.qtyonhand) AS sumqtyonhand,
SUM(rv_storage.qtyreserved) AS sumqtyreserved,
rv_storage.ad_client_id,
rv_storage.ad_org_id
FROM
rv_storage
GROUP BY
rv_storage.m_product_id,
rv_storage.m_locator_id,
rv_storage.ad_client_id,
rv_storage.ad_org_id
) strg
ON
p.m_product_id = strg.m_product_id
JOIN
m_locator l
ON
strg.m_locator_id = l.m_locator_id;