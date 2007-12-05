CREATE OR REPLACE VIEW M_PRODUCT_SUBSTITUTERELATE_V AS
SELECT 
s.AD_Client_ID, s.AD_Org_ID, s.IsActive, s.Created, s.CreatedBy, s.Updated, s.UpdatedBy, s.m_product_id, s.substitute_id, s.description, 'S' as rowtype, (ms.qtyonhand - ms.qtyreserved) AS qtyavailable, ms.qtyonhand, ms.qtyreserved, mpr.pricestd, mpr.m_pricelist_version_id, mw.m_warehouse_id
FROM m_substitute s
JOIN m_storage ms ON ms.m_product_id = s.substitute_id
JOIN m_product mp ON ms.m_product_id = mp.m_product_id
JOIN m_locator ml ON ms.m_locator_id = ml.m_locator_id
JOIN m_warehouse mw ON ml.m_warehouse_id = mw.m_warehouse_id 
JOIN m_productprice mpr ON ms.m_product_id = mpr.m_product_id
UNION
SELECT r.ad_client_id, r.ad_org_id, r.m_product_id, r.relatedproduct_id, r.description, 'R' as rowtype, (ms.qtyonhand - ms.qtyreserved) AS qtyavailable, ms.qtyonhand, ms.qtyreserved, mpr.pricestd, mpr.m_pricelist_version_id, mw.m_warehouse_id
FROM m_relatedproduct r
JOIN m_storage ms ON ms.m_product_id = r.relatedproduct_id
JOIN m_product mp ON ms.m_product_id = mp.m_product_id
JOIN m_locator ml ON ms.m_locator_id = ml.m_locator_id
JOIN m_warehouse mw ON ml.m_warehouse_id = mw.m_warehouse_id 
JOIN m_productprice mpr ON ms.m_product_id = mpr.m_product_id;

COMMIT;