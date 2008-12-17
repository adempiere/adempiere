CREATE OR REPLACE VIEW M_Product_SubstituteRelated_V AS
SELECT s.AD_Client_ID, s.AD_Org_ID, s.IsActive, s.Created, s.CreatedBy, s.Updated, s.UpdatedBy, s.m_product_id, s.substitute_id, s.description, 'S' AS ROWTYPE, (ms.qtyonhand - ms.qtyreserved) AS qtyavailable, ms.qtyonhand, ms.qtyreserved, mpr.pricestd, mpr.m_pricelist_version_id, mw.m_warehouse_id, mp.name, org.name as orgname
FROM M_SUBSTITUTE s
JOIN M_STORAGE ms ON ms.m_product_id = s.substitute_id
JOIN M_PRODUCT mp ON ms.m_product_id = mp.m_product_id
JOIN M_LOCATOR ml ON ms.m_locator_id = ml.m_locator_id
JOIN M_WAREHOUSE mw ON ml.m_warehouse_id = mw.m_warehouse_id 
JOIN M_PRODUCTPRICE mpr ON ms.m_product_id = mpr.m_product_id
JOIN ad_org org ON org.ad_org_id = mw.ad_org_id
UNION
SELECT r.ad_client_id, r.ad_org_id, r.IsActive, r.Created, r.CreatedBy, r.Updated, r.UpdatedBy, r.m_product_id, r.relatedproduct_id, r.description, 'R' AS ROWTYPE, (ms.qtyonhand - ms.qtyreserved) AS qtyavailable, ms.qtyonhand, ms.qtyreserved, mpr.pricestd, mpr.m_pricelist_version_id, mw.m_warehouse_id, mp.name, org.name as orgname
FROM M_RELATEDPRODUCT r
JOIN M_STORAGE ms ON ms.m_product_id = r.relatedproduct_id
JOIN M_PRODUCT mp ON ms.m_product_id = mp.m_product_id
JOIN M_LOCATOR ml ON ms.m_locator_id = ml.m_locator_id
JOIN M_WAREHOUSE mw ON ml.m_warehouse_id = mw.m_warehouse_id 
JOIN M_PRODUCTPRICE mpr ON ms.m_product_id = mpr.m_product_id
JOIN ad_org org ON org.ad_org_id = mw.ad_org_id;