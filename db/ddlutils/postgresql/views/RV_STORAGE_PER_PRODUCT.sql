-- View: rv_storage_per_product

-- DROP VIEW rv_storage_per_product;

CREATE OR REPLACE VIEW rv_storage_per_product AS 
 SELECT strg.ad_client_id, strg.ad_org_id, p.m_product_id, p.value, p.name, p.description, p.upc, p.sku, p.c_uom_id, p.m_product_category_id, p.classification, p.weight, p.volume, p.versionno, p.guaranteedays, p.guaranteedaysmin, strg.sumqtyonhand
   FROM m_product p
   JOIN ( SELECT rv_storage.m_product_id, rv_storage.m_locator_id, sum(rv_storage.qtyonhand) AS sumqtyonhand, rv_storage.ad_client_id, rv_storage.ad_org_id
           FROM rv_storage
          GROUP BY rv_storage.m_product_id, rv_storage.m_locator_id, rv_storage.ad_client_id, rv_storage.ad_org_id) strg ON p.m_product_id = strg.m_product_id
   JOIN m_locator l ON strg.m_locator_id = l.m_locator_id;

ALTER TABLE rv_storage_per_product OWNER TO adempiere;
