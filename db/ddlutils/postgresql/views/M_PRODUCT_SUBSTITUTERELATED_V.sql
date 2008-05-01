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
    'S'                             AS ROWTYPE,
    (ms.qtyonhand - ms.qtyreserved) AS qtyavailable,
    mp.NAME,
    ms.qtyonhand,
    ms.qtyreserved,
    mpr.pricestd,
    mpr.m_pricelist_version_id,
    mw.m_warehouse_id,
    org.NAME AS orgname
FROM M_SUBSTITUTE s 
    JOIN M_STORAGE ms ON ms.m_product_id = s.substitute_id
    JOIN M_PRODUCT mp ON ms.m_product_id = mp.m_product_id
    JOIN M_LOCATOR ml ON ms.m_locator_id = ml.m_locator_id
    JOIN M_WAREHOUSE mw ON ml.m_warehouse_id = mw.m_warehouse_id
    JOIN M_PRODUCTPRICE mpr ON ms.m_product_id = mpr.m_product_id
    JOIN AD_ORG org ON org.ad_org_id = mw.ad_org_id
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
    'R'                             AS ROWTYPE,
    (ms.qtyonhand - ms.qtyreserved) AS qtyavailable,
    mp.NAME,
    ms.qtyonhand,
    ms.qtyreserved,
    mpr.pricestd,
    mpr.m_pricelist_version_id,
    mw.m_warehouse_id,
    org.NAME AS orgname
FROM M_RELATEDPRODUCT r 
    JOIN M_STORAGE ms ON ms.m_product_id = r.relatedproduct_id
    JOIN M_PRODUCT mp ON ms.m_product_id = mp.m_product_id
    JOIN M_LOCATOR ml ON ms.m_locator_id = ml.m_locator_id 
    JOIN M_WAREHOUSE mw ON ml.m_warehouse_id = mw.m_warehouse_id
    JOIN M_PRODUCTPRICE mpr ON ms.m_product_id = mpr.m_product_id
    JOIN AD_ORG org ON org.ad_org_id = mw.ad_org_id;