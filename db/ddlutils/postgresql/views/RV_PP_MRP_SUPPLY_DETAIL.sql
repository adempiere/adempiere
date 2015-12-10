-- View: rv_pp_mrp_detail_supply

-- DROP VIEW rv_pp_mrp_detail_supply;

CREATE OR REPLACE VIEW rv_pp_mrp_detail_supply AS
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
    supply.ordertype,
    supply.docstatus,
    supply.dateordered,
    supply.datepromised,
    supply.priority,
    supply.s_resource_id,
    supply.m_warehouse_id,
    supply.c_bpartner_id,
    supply.planner_id,
    supply.c_project_id,
    supply.c_projectphase_id,
    supply.c_projecttask_id,
    supply.datestartschedule,
    supply.datefinishschedule
   FROM pp_mrp_detail mrp_detail
     LEFT JOIN rv_pp_mrp supply ON supply.pp_mrp_id = mrp_detail.mrp_supply_id;
