-- DROP VIEW IF EXISTS rv_productionline;
CREATE OR REPLACE VIEW rv_productionline AS
SELECT pl.m_productionline_id, pl.ad_client_id, pl.ad_org_id, pl.isactive, pl.created, pl.createdby, pl.updated, pl.updatedby, pl.m_productionplan_id, pl.line, pl.m_product_id, pl.movementqty, pl.m_locator_id, pl.description, pl.m_attributesetinstance_id, pl.processed, pl.m_production_id, pl.plannedqty, pl.qtyused, pl.isendproduct, pl.qtyreserved, pl.pickedqty, p.m_product_category_id, p.producttype, p.value AS productvalue,
p.name AS productname
FROM m_productionline pl
JOIN m_product p ON pl.m_product_id = p.m_product_id;
