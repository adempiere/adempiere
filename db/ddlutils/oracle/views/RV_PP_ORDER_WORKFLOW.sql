DROP VIEW rv_pp_order_workflow;
CREATE OR REPLACE VIEW rv_pp_order_workflow AS
SELECT 
n.ad_client_id,
n.ad_org_id, 
n.created, 
n.createdby, 
n.isactive,
n.updated, 
n.updatedby, 
owf.pp_order_workflow_id, 
n.name, 
n.pp_order_id, 
n.docstatus, 
n.value,
n.s_resource_id,
n.durationrequired AS durationrequired,
n.durationreal,
n.durationrequired - n.durationreal AS duration,
n.movingtime, 
n.waitingtime, 
n.setuptime, 
n.queuingtime, 
n.qtydelivered, 
n.qtyreject, 
n.qtyscrap, 
n.datestartschedule, 
n.datefinishschedule,
n.c_bpartner_id, 
n.description, 
n.ismilestone, 
n.issubcontracting
FROM pp_order_workflow owf
JOIN pp_order_node n ON n.pp_order_workflow_id = owf.pp_order_workflow_id;