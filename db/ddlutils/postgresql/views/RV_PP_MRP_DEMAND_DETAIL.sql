-- View: rv_pp_mrp_detail_demand

-- DROP VIEW rv_pp_mrp_detail_demand;

CREATE OR REPLACE VIEW rv_pp_mrp_detail_demand AS
 SELECT mrp_detail.ad_client_id,
    mrp_detail.ad_org_id,
    mrp_detail.created,
    mrp_detail.createdby,
    mrp_detail.updated,
    mrp_detail.updatedby,
    mrp_detail.isactive,
    mrp_detail.mrp_demand_id,
    mrp_detail.mrp_supply_id,
    mrp_detail.qty,
    demand.ordertype,
    demand.docstatus,
    demand.dateordered,
    demand.datepromised,
    demand.priority,
    demand.s_resource_id,
    demand.m_warehouse_id,
    demand.c_bpartner_id,
    demand.planner_id,
    demand.c_project_id,
    demand.c_projectphase_id,
    demand.c_projecttask_id,
    demand.datestartschedule,
    demand.datefinishschedule
   FROM pp_mrp_detail mrp_detail
     LEFT JOIN rv_pp_mrp demand ON demand.pp_mrp_id = mrp_detail.mrp_demand_id;
