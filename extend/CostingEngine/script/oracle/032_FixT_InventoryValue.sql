DELETE FROM t_inventoryvalue;
ALTER TABLE t_inventoryvalue DROP  CONSTRAINT t_inventoryvalue_key;
DROP  INDEX  t_inventoryvalue_key;
ALTER TABLE t_inventoryvalue ADD CONSTRAINT t_inventoryvalue_key PRIMARY KEY (ad_pinstance_id, m_warehouse_id, m_product_id, m_attributesetinstance_id, m_costtype_id, m_costelement_id);