--DROP VIEW rv_pp_mrp;
CREATE OR REPLACE VIEW rv_pp_mrp AS 
SELECT 
mrp.ad_client_id,
mrp.ad_org_id,
mrp.created,
mrp.createdby,
mrp.isactive,
mrp.updated,
mrp.updatedby,
pp.ismps,
mrp.c_order_id,
mrp.c_orderline_id,
mrp.dateordered,
mrp.dateconfirm,
mrp.datepromised,
mrp.datestartschedule,
mrp.datefinishschedule,
mrp.datestart,
mrp.datesimulation,
mrp.docstatus,
mrp.m_forecast_id,
mrp.m_forecastline_id,
mrp.value,
mrp.m_product_id,
mrp.m_requisition_id,
mrp.m_requisitionline_id,
mrp.m_warehouse_id,
mrp.pp_order_id,
mrp.qty,
mrp.name,
mrp.s_resource_id,
mrp.priority,
mrp.ordertype,
mrp.typemrp,
documentNo(mrp.pp_mrp_id) AS documentNo
FROM pp_mrp mrp
LEFT JOIN pp_product_planning pp ON (pp.m_product_id = mrp.m_product_id AND mrp.m_warehouse_id = pp.m_warehouse_id)
WHERE mrp.Qty<>0
;
