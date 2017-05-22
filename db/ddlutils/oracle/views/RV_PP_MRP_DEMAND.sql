 DROP VIEW rv_pp_mrp_demand;
 CREATE OR REPLACE VIEW rv_pp_mrp_demand AS
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
     CASE
     WHEN ordertype = 'SOO' THEN (SELECT c_bpartner_location_id FROM C_Order o WHERE o.C_Order_ID=mrp.C_Order_ID)
     WHEN ordertype = 'DOO' THEN (SELECT c_bpartner_location_id FROM DD_Order o WHERE o.DD_Order_ID=mrp.DD_Order_ID)
     WHEN ordertype = 'MOP' THEN (SELECT MAX(c_bpartner_location_id) FROM c_bpartner_location bpl WHERE c_bpartner_location_id=mrp.c_bpartner_id AND bpl.IsShipTo='Y' AND bpl.IsActive='Y')
     END AS c_bpartner_location_id,
     CASE
     WHEN ordertype = 'SOO' THEN (SELECT m_shipper_id FROM C_Order o WHERE o.C_Order_ID=mrp.C_Order_ID)
     WHEN ordertype = 'DOO' THEN (SELECT m_shipper_id FROM DD_Order o WHERE o.DD_Order_ID=mrp.DD_Order_ID)
     WHEN ordertype = 'MOP' THEN (SELECT m_shipper_id FROM PP_Order o WHERE o.PP_Order_ID=mrp.PP_Order_ID) END AS m_shipper_id ,
     CASE
     WHEN ordertype = 'SOO' THEN NULL
     WHEN ordertype = 'DOO' THEN (SELECT TrackingNo FROM DD_Order o WHERE o.DD_Order_ID=mrp.DD_Order_ID)
     WHEN ordertype = 'MOP' THEN (SELECT TrackingNo FROM PP_Order o WHERE o.PP_Order_ID=mrp.PP_Order_ID) END AS TrackingNo ,
     CASE
     WHEN ordertype = 'SOO' THEN (SELECT deliveryrule FROM C_Order o WHERE o.C_Order_ID=mrp.C_Order_ID)
     WHEN ordertype = 'DOO' THEN (SELECT deliveryrule FROM DD_Order o WHERE o.DD_Order_ID=mrp.DD_Order_ID)
     WHEN ordertype = 'MOP' THEN (SELECT deliveryrule FROM PP_Order o WHERE o.PP_Order_ID=mrp.PP_Order_ID) END AS deliveryrule ,
     CASE
     WHEN ordertype = 'SOO' THEN (SELECT deliveryviarule FROM C_Order o WHERE o.C_Order_ID=mrp.C_Order_ID)
     WHEN ordertype = 'DOO' THEN (SELECT deliveryviarule FROM DD_Order o WHERE o.DD_Order_ID=mrp.DD_Order_ID)
     WHEN ordertype = 'MOP' THEN (SELECT deliveryviarule FROM PP_Order o WHERE o.PP_Order_ID=mrp.PP_Order_ID) END AS deliveryviarule ,
     CASE
     WHEN ordertype = 'SOO' THEN (SELECT M_FreightCategory_ID FROM C_Order o WHERE o.C_Order_ID=mrp.C_Order_ID)
     WHEN ordertype = 'DOO' THEN (SELECT M_FreightCategory_ID FROM DD_Order o WHERE o.DD_Order_ID=mrp.DD_Order_ID)
     WHEN ordertype = 'MOP' THEN (SELECT M_FreightCategory_ID FROM PP_Order o WHERE o.PP_Order_ID=mrp.PP_Order_ID) END AS M_FreightCategory_ID ,
     CASE
     WHEN ordertype = 'SOO' THEN (SELECT FreightCostRule FROM C_Order o WHERE o.C_Order_ID=mrp.C_Order_ID)
     WHEN ordertype = 'DOO' THEN (SELECT FreightCostRule FROM DD_Order o WHERE o.DD_Order_ID=mrp.DD_Order_ID)
     WHEN ordertype = 'MOP' THEN (SELECT FreightCostRule FROM PP_Order o WHERE o.PP_Order_ID=mrp.PP_Order_ID) END AS FreightCostRule ,
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
     mrp.datefinishschedule,
     p.Weight * mrp.qty AS weight,
     p.Volume * mrp.qty AS volume,
     bomqtyonhand (mrp.m_product_id, mrp.m_warehouse_id , 0)  AS qtyonhand,
     bomqtyordered (mrp.m_product_id, mrp.m_warehouse_id , 0 ) AS qtyordered,
     bomqtyreserved (mrp.m_product_id, mrp.m_warehouse_id , 0 ) AS qtyreserved,
     bomqtyavailable(mrp.m_product_id , mrp.m_warehouse_id,0) AS qtyavailable
    FROM rv_pp_mrp mrp
      JOIN m_product p ON p.m_product_id = mrp.m_product_id
   WHERE mrp.typemrp = 'D' AND mrp.qty > 0
   ORDER BY mrp.datepromised;
