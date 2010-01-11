DROP VIEW rv_pp_mrp;
CREATE OR REPLACE VIEW rv_pp_mrp AS 
SELECT 
mrp.pp_mrp_id,
mrp.ad_client_id,
mrp.ad_org_id,
mrp.created,
mrp.createdby,
mrp.isactive,
mrp.isavailable,
mrp.updated,
mrp.updatedby,
pp.ismps,
mrp.name,
mrp.description,
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
mrp.pp_order_bomline_id,
mrp.dd_order_id,
mrp.dd_orderline_id,
mrp.qty,
mrp.s_resource_id,
mrp.planner_id,
mrp.priority,
mrp.ordertype,
mrp.typemrp,
p.LowLevel,
mrp.C_BPartner_ID,
documentNo(mrp.pp_mrp_id) AS documentNo
FROM pp_mrp mrp
INNER JOIN M_Product p ON (mrp.M_Product_ID = p.M_Product_ID)
LEFT JOIN pp_product_planning pp ON (pp.m_product_id = mrp.m_product_id AND mrp.m_warehouse_id = pp.m_warehouse_id)
WHERE mrp.Qty<>0
UNION
SELECT 
0 ,
pp.ad_client_id,
pp.ad_org_id,
pp.created,
pp.createdby,
pp.isactive,
'Y',--mrp.isavailable
pp.updated,
pp.updatedby,
pp.ismps,
null, --name
null, --description
null, --mrp.c_order_id
null, --mrp.c_orderline_id
CURRENT_TIMESTAMP, --mrp.dateordered,
CURRENT_TIMESTAMP, --mrp.dateconfirm,
CURRENT_TIMESTAMP, --mrp.datepromised,
CURRENT_TIMESTAMP, --mrp.datestartschedule,
CURRENT_TIMESTAMP, --mrp.datefinishschedule,
CURRENT_TIMESTAMP, --mrp.datestart,
CURRENT_TIMESTAMP, --mrp.datesimulation,
'CO',  --mrp.docstatus,
null, --mrp.m_forecast_id,
null, --mrp.m_forecastline_id,
null, --mrp.value,
pp.m_product_id,
null, --mrp.m_requisition_id,
null, --mrp.m_requisitionline_id,
pp.m_warehouse_id,
null, --mrp.pp_order_id,
null, --pp_order_bomline_id
null, --mrp.dd_order_id,
null, --mrp.dd_orderline_id,
pp.safetystock - bomqtyonhand(pp.M_Product_ID,pp.M_Warehouse_ID, 0) AS qty, --mrp.qty,
pp.s_resource_id,
null, --planner_id
null, --mrp.priority,
'STK', --mrp.ordertype,
'D' , --mrp.typemrp,
p.LowLevel,
null, --C_BPartner_ID
'Safety Strock'   --documentNo(mrp.pp_mrp_id) AS documentNo
FROM pp_product_planning pp 
INNER JOIN M_Product p ON (pp.M_Product_ID = p.M_Product_ID)
WHERE bomqtyonhand(pp.M_Product_ID,pp.M_Warehouse_ID, 0) < pp.safetystock 
;