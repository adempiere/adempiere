CREATE OR REPLACE VIEW PP_Order_Node_vt
AS
SELECT
onode.AD_Client_ID, onode.AD_Org_ID, onode.IsActive, onode.Created, onode.CreatedBy, onode.Updated, onode.UpdatedBy,
ont.AD_Language,
ont.name, c_bpartner_id, cost, datefinish, datefinishschedule, datestart, datestartschedule , 
ont.description, docaction, docstatus, duration, durationreal, durationrequiered, ont.help, ismilestone,
issubcontracting, movingtime, overlapunits, 
pp_order_id, pp_order_workflow_id,onode.pp_order_node_id, priority, qtydelivered, qtyrequiered , 
qtyscrap , queuingtime , s_resource_id , setuptime ,setuptimereal,  unitscycles ,  validfrom , validto , value , waitingtime , workingtime , yield 
FROM PP_Order_Node onode
LEFT JOIN PP_Order_Node_Trl ont ON (ont.PP_Order_Node_ID=onode.PP_Order_Node_ID);
