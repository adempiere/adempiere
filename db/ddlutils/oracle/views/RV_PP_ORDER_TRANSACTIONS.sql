CREATE OR REPLACE VIEW rv_pp_order_transactions AS 
SELECT DISTINCT 
o.ad_client_id, 
o.ad_org_id, 
o.isactive, 
o.created, 
o.createdby, 
o.updatedby, 
o.updated, 
o.documentno, 
ol.m_product_id,
mt.m_locator_id,
mt.movementdate,
o.pp_order_id,
o.qtydelivered,
o.qtyscrap,
ol.qtydelivered AS qtydeliveredline,
o.qtydelivered * ol.qtybatch / 100 AS qtyissueshouldbe,
ol.qtyscrap AS qtyscrapline,
o.qtyscrap * ol.qtybatch / 100 AS qtyissuescrapshouldbe,
mt.createdby AS createdbyissue,
mt.updatedby AS updatedbyissue,
( SELECT sum(t.movementqty) AS sum FROM m_transaction t WHERE t.pp_cost_collector_id = cc.pp_cost_collector_id) AS qtytodeliver,
(o.qtydelivered + o.qtyscrap) * ol.qtybatch / 100 + (( SELECT sum(t.movementqty) AS sum FROM m_transaction t WHERE t.pp_cost_collector_id = cc.pp_cost_collector_id)) AS differenceqty,
o.issotrx,
o.dateordered
FROM pp_order o
JOIN pp_order_bomline ol ON ol.pp_order_id = o.pp_order_id
JOIN pp_cost_collector cc ON cc.pp_order_bomline_id = ol.pp_order_bomline_id
LEFT JOIN m_transaction mt ON mt.pp_cost_collector_id = cc.pp_cost_collector_id
;
