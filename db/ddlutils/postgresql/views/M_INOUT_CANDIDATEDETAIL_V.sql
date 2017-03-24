-- DROP VIEW M_InOut_CandidateDetail_v;
CREATE OR REPLACE VIEW M_InOut_CandidateDetail_v (
ad_client_id,
ad_org_id,
c_bpartner_id,
c_order_id,
documentno,
dateordered,
c_doctype_id,
poreference,
description,
salesrep_id,
m_warehouse_id,
totallines,
qtytodeliver,
c_orderline_id,
datepromised,
m_product_id,
c_charge_id,
qtyordered,
isunconfirmedinout,
unconfirmedLinesNoToShip,
deliveryrule,
qtyavailable
)
AS
SELECT o.ad_client_id,
o.ad_org_id,
o.c_bpartner_id,
o.c_order_id,
o.documentno,
o.dateordered,
o.c_doctype_id,
o.poreference,
o.description,
o.salesrep_id,
l.m_warehouse_id,
(l.qtyordered - l.qtydelivered) * l.priceactual AS totallines,
l.qtyordered - l.qtydelivered AS qtytodeliver,
l.c_orderline_id,
l.datepromised,
l.m_product_id,
l.c_charge_id,
l.qtyordered,
CASE
WHEN iol.unconfirmedLinesNoToShip IS NULL THEN 'N'
ELSE 'Y'
END AS isunconfirmedinout,
iol.unconfirmedLinesNoToShip,
o.deliveryrule,
storage.qtyavailable - l.qtyreserved AS qtyavailable
FROM c_order o
JOIN c_orderline l ON o.c_order_id = l.c_order_id
LEFT JOIN ( SELECT count(iol_1.m_inoutline_id) AS unconfirmedLinesNoToShip,
iol_1.c_orderline_id
FROM m_inoutline iol_1
JOIN m_inout io ON iol_1.m_inout_id = io.m_inout_id
WHERE io.docstatus IN('IP','WC')
GROUP BY iol_1.c_orderline_id) iol ON iol.c_orderline_id = l.c_orderline_id
LEFT JOIN ( SELECT sum(rv_storage_per_product.sumqtyonhand - rv_storage_per_product.sumqtyreserved) AS qtyavailable,
rv_storage_per_product.m_product_id,
rv_storage_per_product.m_warehouse_id
FROM rv_storage_per_product
GROUP BY rv_storage_per_product.m_product_id, rv_storage_per_product.m_warehouse_id) storage ON storage.m_product_id = l.m_product_id AND storage.m_warehouse_id = o.m_warehouse_id
WHERE o.docstatus = 'CO' AND o.isdelivered = 'N' AND (o.c_doctype_id IN ( SELECT c_doctype.c_doctype_id
FROM c_doctype
WHERE c_doctype.docbasetype = 'SOO' AND (c_doctype.docsubtypeso not in ('ON','OB' ,'WR')  ))) AND o.deliveryrule <> 'M' AND (l.m_product_id IS NULL OR (EXISTS ( SELECT p.value
FROM m_product p
WHERE l.m_product_id = p.m_product_id AND p.isexcludeautodelivery = 'N'))) AND l.qtyordered <> l.qtydelivered AND o.isdropship = 'N' AND (l.m_product_id IS NOT NULL OR l.c_charge_id IS NOT NULL) AND NOT (EXISTS ( SELECT iol_1.m_inoutline_id
FROM m_inoutline iol_1
JOIN m_inout io ON iol_1.m_inout_id = io.m_inout_id
WHERE iol_1.c_orderline_id = l.c_orderline_id AND (io.docstatus  in ('IP','WC' ))));