DROP VIEW adempiere.m_product_substituterelated_v;

CREATE OR REPLACE VIEW adempiere.m_product_substituterelated_v AS 
         SELECT s.ad_client_id, s.ad_org_id, s.isactive, s.created, s.createdby, s.updated, s.updatedby, s.m_product_id, s.substitute_id, s.name AS description, 'S'::text AS rowtype, mp.value, mp.name, sum(ms.qtyonhand - ms.qtyreserved) AS qtyavailable, sum(ms.qtyonhand) AS qtyonhand, sum(ms.qtyreserved) AS qtyreserved, round(max(mpr.pricestd), 0) AS pricestd, mpr.m_pricelist_version_id, mw.m_warehouse_id, org.name AS orgname
           FROM adempiere.m_substitute s
      JOIN adempiere.m_storage ms ON ms.m_product_id = s.substitute_id
   JOIN adempiere.m_product mp ON ms.m_product_id = mp.m_product_id
   JOIN adempiere.m_locator ml ON ms.m_locator_id = ml.m_locator_id
   JOIN adempiere.m_warehouse mw ON ml.m_warehouse_id = mw.m_warehouse_id
   JOIN adempiere.m_productprice mpr ON ms.m_product_id = mpr.m_product_id
   JOIN adempiere.ad_org org ON org.ad_org_id = mw.ad_org_id
  GROUP BY s.ad_client_id, s.ad_org_id, s.isactive, s.created, s.createdby, s.updated, s.updatedby, s.m_product_id, s.substitute_id, s.name, mp.value, mw.m_warehouse_id, mpr.m_pricelist_version_id, org.name, mp.name
UNION 
         SELECT r.ad_client_id, r.ad_org_id, r.isactive, r.created, r.createdby, r.updated, r.updatedby, r.m_product_id, r.relatedproduct_id AS substitute_id, r.name AS description, 'R'::text AS rowtype, mp.value, mp.name, sum(ms.qtyonhand - ms.qtyreserved) AS qtyavailable, sum(ms.qtyonhand) AS qtyonhand, sum(ms.qtyreserved) AS qtyreserved, round(max(mpr.pricestd), 0) AS pricestd, mpr.m_pricelist_version_id, mw.m_warehouse_id, org.name AS orgname
           FROM adempiere.m_relatedproduct r
      JOIN adempiere.m_storage ms ON ms.m_product_id = r.relatedproduct_id
   JOIN adempiere.m_product mp ON ms.m_product_id = mp.m_product_id
   JOIN adempiere.m_locator ml ON ms.m_locator_id = ml.m_locator_id
   JOIN adempiere.m_warehouse mw ON ml.m_warehouse_id = mw.m_warehouse_id
   JOIN adempiere.m_productprice mpr ON ms.m_product_id = mpr.m_product_id
   JOIN adempiere.ad_org org ON org.ad_org_id = mw.ad_org_id
  GROUP BY r.ad_client_id, r.ad_org_id, r.isactive, r.created, r.createdby, r.updated, r.updatedby, r.m_product_id, r.relatedproduct_id, r.name, mw.m_warehouse_id, mpr.m_pricelist_version_id, org.name, mp.value, mp.name;

ALTER TABLE adempiere.m_product_substituterelated_v
  OWNER TO adempiere;

