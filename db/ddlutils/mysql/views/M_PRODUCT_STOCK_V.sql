CREATE OR REPLACE VIEW m_product_stock_v AS 
 SELECT ms.isactive, ms.created, ms.createdby, ms.updated, ms.updatedby, 
	ms.m_product_id, mp.value, mp.name, mp.help, 
	ms.qtyonhand - ms.qtyreserved AS qtyavailable, 
	ms.qtyonhand, ms.qtyreserved, 
	ms.qtyallocated, 
	mp.description, 
	mw.name AS warehouse, 
	mw.m_warehouse_id, 
	mw.ad_client_id, 
	mw.ad_org_id,
	mp.documentnote
   FROM m_storage ms
   JOIN m_product mp ON ms.m_product_id = mp.m_product_id
   JOIN m_locator ml ON ms.m_locator_id = ml.m_locator_id
   JOIN m_warehouse mw ON ml.m_warehouse_id = mw.m_warehouse_id
  ORDER BY mw.name;