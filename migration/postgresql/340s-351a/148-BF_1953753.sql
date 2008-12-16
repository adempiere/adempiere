DROP VIEW m_product_substituterelated_v; 
CREATE OR 
REPLACE VIEW m_product_substituterelated_v AS 
SELECT s.ad_client_id,
    s.ad_org_id,
    s.isactive,
    s.created,
    s.createdby,
    s.updated,
    s.updatedby,
    s.m_product_id,
    s.substitute_id,
    s.description,
    'S'                             AS rowtype,
    (ms.qtyonhand - ms.qtyreserved) AS qtyavailable,
    mp.NAME,
    ms.qtyonhand,
    ms.qtyreserved,
    mpr.pricestd,
    mpr.m_pricelist_version_id,
    mw.m_warehouse_id,
    org.name as orgname
FROM m_substitute s 
    JOIN m_storage ms ON ms.m_product_id = s.substitute_id
    JOIN m_product mp ON ms.m_product_id = mp.m_product_id
    JOIN m_locator ml ON ms.m_locator_id = ml.m_locator_id
    JOIN m_warehouse mw ON ml.m_warehouse_id = mw.m_warehouse_id
    JOIN m_productprice mpr ON ms.m_product_id = mpr.m_product_id
    JOIN ad_org org ON org.ad_org_id = mw.ad_org_id
UNION 
SELECT r.ad_client_id,
    r.ad_org_id,
    r.isactive,
    r.created,
    r.createdby,
    r.updated,
    r.updatedby,
    r.m_product_id,
    r.relatedproduct_id             AS substitute_id,
    r.description,
    'R'                             AS rowtype,
    (ms.qtyonhand - ms.qtyreserved) AS qtyavailable,
    mp.NAME,
    ms.qtyonhand,
    ms.qtyreserved,
    mpr.pricestd,
    mpr.m_pricelist_version_id,
    mw.m_warehouse_id,
    org.name as orgname
FROM m_relatedproduct r 
    JOIN m_storage ms ON ms.m_product_id = r.relatedproduct_id
    JOIN m_product mp ON ms.m_product_id = mp.m_product_id
    JOIN m_locator ml ON ms.m_locator_id = ml.m_locator_id 
    JOIN m_warehouse mw ON ml.m_warehouse_id = mw.m_warehouse_id
    JOIN m_productprice mpr ON ms.m_product_id = mpr.m_product_id
    JOIN ad_org org ON org.ad_org_id = mw.ad_org_id;