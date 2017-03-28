
CREATE OR REPLACE VIEW rv_productionline
(m_productionline_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, m_productionplan_id, line, m_product_id, movementqty, m_locator_id, description,
m_attributesetinstance_id, processed, m_production_id, plannedqty, qtyused, isendproduct, qtyreserved, pickedqty, m_product_category_id, producttype, productvalue,
productname)
AS
SELECT pl.m_productionline_id, pl.ad_client_id, pl.ad_org_id, pl.isactive, pl.created, pl.createdby, pl.updated, pl.updatedby, pl.m_productionplan_id, pl.line, pl.m_product_id, pl.movementqty, pl.m_locator_id, pl.description, pl.m_attributesetinstance_id, pl.processed, pl.m_production_id, pl.plannedqty, pl.qtyused, pl.isendproduct, pl.qtyreserved, pl.pickedqty, p.m_product_category_id, p.producttype, p.value AS productvalue,
p.name AS productname
FROM m_productionline pl
JOIN m_product p ON pl.m_product_id = p.m_product_id;