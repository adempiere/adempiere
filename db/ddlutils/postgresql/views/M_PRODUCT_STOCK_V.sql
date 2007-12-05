--create views
CREATE OR REPLACE VIEW M_PRODUCT_STOCK_V
AS
SELECT 
ms.AD_Client_ID, ms.AD_Org_ID, ms.IsActive, ms.Created, ms.CreatedBy, ms.Updated, ms.UpdatedBy,
mp.value, mp.help, (ms.qtyonhand - ms.qtyreserved) AS qtyavailable, ms.qtyonhand, 
ms.qtyreserved, mp.description, mw.name as warehouse, mw.m_warehouse_id, mw.ad_client_id, 
mw.ad_org_id, mp.documentnote
FROM m_storage ms 
JOIN m_product mp ON ms.m_product_id = mp.m_product_id
JOIN m_locator ml ON ms.m_locator_id = ml.m_locator_id
JOIN m_warehouse mw ON ml.m_warehouse_id = mw.m_warehouse_id 
ORDER BY mw.name;