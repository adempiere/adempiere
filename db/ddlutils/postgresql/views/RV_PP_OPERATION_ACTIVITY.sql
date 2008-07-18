-- DROP VIEW rv_pp_operation_activity;
CREATE OR REPLACE VIEW rv_pp_operation_activity AS 
SELECT n.ad_client_id,
n.ad_org_id,
n.created,
n.createdby,
n.isactive,
n.updated,
n.updatedby,
n.pp_order_id,
n.docstatus,
n.value,
n.s_resource_id,
n.durationrequiered,
n.durationreal,
n.durationrequiered - n.durationreal AS duration,
n.qtydelivered,
n.qtyreject,
n.qtyscrap,
n.datestartschedule,
n.datefinishschedule
FROM pp_order_node n;
