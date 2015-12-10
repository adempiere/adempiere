-- View: rv_pp_mrp_supply

-- DROP VIEW rv_pp_mrp_supply;

CREATE OR REPLACE VIEW rv_pp_mrp_supply AS
 SELECT mrp.ad_client_id,
    mrp.ad_org_id,
    mrp.created,
    mrp.createdby,
    mrp.updated,
    mrp.updatedby,
    mrp.isactive,
    mrp.pp_mrp_id,
    mrp.documentno,
    mrp.ordertype,
    mrp.docstatus,
    mrp.c_bpartner_id,
    mrp.planner_id,
    mrp.s_resource_id,
    mrp.m_warehouse_id,
    mrp.dateordered,
    mrp.datepromised,
    mrp.priority,
    mrp.m_product_id,
    p.m_attributesetinstance_id,
    p.sku,
    p.c_uom_id,
    p.issold,
    mrp.m_product_category_id,
    mrp.isbom,
    mrp.ispurchased,
    mrp.qty,
    mrp.ismps,
    mrp.isrequiredmrp,
    mrp.isrequireddrp,
    mrp.c_project_id,
    mrp.c_projectphase_id,
    mrp.c_projecttask_id,
    mrp.datestartschedule,
    mrp.datefinishschedule
   FROM rv_pp_mrp mrp
     JOIN m_product p ON p.m_product_id = mrp.m_product_id
  WHERE mrp.typemrp = 'S'::bpchar AND mrp.qty > 0::numeric
  ORDER BY mrp.datepromised;