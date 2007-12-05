-- [ 1823612 ] Product Info Screen Improvements
-- Author: fer_luck
-- Dictionary Additions
-- Feature Request: http://sourceforge.net/tracker/index.php?func=detail&aid=1823612&group_id=176962&atid=879335
--******************************************************--
-- NOTE: Don't forget to run the three processes:       --
-- 1 - Add missing translations in the language screen  --
-- 2 - Synchronize terminology                          --
-- 3 - Check sequences                                  --
--******************************************************--

-- new message
BEGIN;
INSERT INTO ad_message(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, value, msgtext, msgtip, msgtype, entitytype)
  VALUES(53001, 0, 0, 'Y', '2007-07-18 00:00:00.0', 100, '2007-07-18 00:00:00.0', 100, 'WarehouseStock', 'Item Availability in other Warehouses', NULL, 'M', 'D');

--add view to ad_table
INSERT INTO ad_table (ad_table_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, name, description, help, tablename, isview, accesslevel, entitytype, ad_window_id, ad_val_rule_id, loadseq, issecurityenabled, isdeleteable, ishighvolume, importtable, ischangelog, replicationtype, po_window_id, copycolumnsfromtable)
  VALUES(53011, 0, 0, 'Y', '2007-07-26 00:00:00.0', 100, '2007-07-26 00:00:00.0', 100, 'Product Stock at Warehouses', NULL, NULL, 'M_Product_Stock_V', 'Y', '1', 'D', NULL, NULL, NULL, 'N', 'Y', 'N', 'N', 'N', 'L', NULL, NULL);

--add columns to view in ad_column
INSERT INTO ad_column(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, name, description, help, version, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql, mandatorylogic)
  VALUES(53003, 0, 0, 'Y', '2007-07-26 01:20:59.0', '2007-07-26 01:20:59.0', 0, 0, 'Description', 'Optional short description of the record', 'A description is limited to 255 characters.', 0, 'D', 'Description', 53011, 10, NULL, NULL, 255, NULL, 'N', 'N', 'N', 'Y', NULL, 'N', NULL, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 275, NULL, 'N', 'N', NULL, NULL);
INSERT INTO ad_column(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, name, description, help, version, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql, mandatorylogic)
  VALUES(53004, 0, 0, 'Y', '2007-07-26 10:52:11.0', '2007-07-26 10:52:11.0', 0, 0, 'Product', 'Product, Service, Item', 'Identifies an item which is either purchased or sold in this organization.', 0, 'U', 'M_Product_ID', 53011, 30, NULL, NULL, 10, NULL, 'N', 'N', 'N', 'Y', NULL, 'N', 0, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 454, NULL, 'N', 'N', NULL, NULL);
INSERT INTO ad_column(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, name, description, help, version, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql, mandatorylogic)
  VALUES(53005, 0, 0, 'Y', '2007-07-26 15:01:05.0', '2007-07-26 00:00:00.0', 0, 0, 'Name', 'Alphanumeric identifier of the entity', 'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', 1, 'D', 'Name', 53011, 10, NULL, NULL, 60, NULL, 'N', 'N', 'Y', 'Y', NULL, 'Y', 1, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 469, NULL, 'N', 'N', NULL, NULL);
INSERT INTO ad_column(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, name, description, help, version, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql, mandatorylogic)
  VALUES(53006, 0, 0, 'Y', '2007-07-26 11:29:06.0', '2007-07-26 00:00:00.0', 0, 0, 'Available Quantity', 'Available Quantity (On Hand - Reserved)', 'Quantity available to promise = On Hand minus Reserved Quantity', 1, 'D', 'QtyAvailable', 53011, 29, NULL, NULL, 22, NULL, 'N', 'N', 'N', 'N', NULL, 'N', 0, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 2238, NULL, 'N', 'N', NULL, NULL);
INSERT INTO ad_column(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, name, description, help, version, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql, mandatorylogic)
  VALUES(53007, 0, 0, 'Y', '2007-07-26 00:00:00.0', '2007-07-26 00:00:00.0', 0, 0, 'On Hand Quantity', 'On Hand Quantity', 'The On Hand Quantity indicates the quantity of a product that is on hand in a warehouse.', 1, 'D', 'QtyOnHand', 53011, 29, NULL, NULL, 22, NULL, 'N', 'N', 'Y', 'N', NULL, 'N', NULL, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 530, NULL, 'N', 'N', NULL, NULL);
INSERT INTO ad_column(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, name, description, help, version, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql, mandatorylogic)
  VALUES(53008, 0, 0, 'Y', '2007-07-26 18:21:48.0', '2007-07-26 00:00:00.0', 0, 0, 'Reserved Quantity', 'Reserved Quantity', 'The Reserved Quantity indicates the quantity of a product that is currently reserved.', 1, 'D', 'QtyReserved', 53011, 29, NULL, NULL, 22, NULL, 'N', 'N', 'N', 'Y', NULL, 'N', NULL, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 532, NULL, 'N', 'N', NULL, NULL);

--add view to ad_table
INSERT INTO ad_table (ad_table_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, name, description, help, tablename, isview, accesslevel, entitytype, ad_window_id, ad_val_rule_id, loadseq, issecurityenabled, isdeleteable, ishighvolume, importtable, ischangelog, replicationtype, po_window_id, copycolumnsfromtable)
  VALUES(53015, 0, 0, 'Y', '2007-07-26 00:00:00.0', 100, '2007-07-26 00:00:00.0', 100, 'Product Substitute with Stock Info', NULL, NULL, 'M_Product_SubstituteRelated_V', 'Y', '1', 'D', NULL, NULL, NULL, 'N', 'Y', 'N', 'N', 'N', 'L', NULL, NULL);

--add columns to view in ad_column
INSERT INTO ad_column(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, name, description, help, version, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql, mandatorylogic)
  VALUES(53023, 0, 0, 'Y', '2007-07-26 01:20:59.0', '2007-07-26 01:20:59.0', 0, 0, 'Description', 'Optional short description of the record', 'A description is limited to 255 characters.', 0, 'D', 'Description', 53015, 10, NULL, NULL, 255, NULL, 'N', 'N', 'N', 'Y', NULL, 'N', NULL, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 275, NULL, 'N', 'N', NULL, NULL);
INSERT INTO ad_column(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, name, description, help, version, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql, mandatorylogic)
  VALUES(53024, 0, 0, 'Y', '2007-07-26 10:52:11.0', '2007-07-26 10:52:11.0', 0, 0, 'Product', 'Product, Service, Item', 'Identifies an item which is either purchased or sold in this organization.', 0, 'U', 'M_Product_ID', 53015, 30, NULL, NULL, 10, NULL, 'N', 'N', 'N', 'Y', NULL, 'N', 0, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 454, NULL, 'N', 'N', NULL, NULL);
INSERT INTO ad_column(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, name, description, help, version, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql, mandatorylogic)
  VALUES(53025, 0, 0, 'Y', '2007-07-26 15:01:05.0', '2007-07-26 00:00:00.0', 0, 0, 'Standard Price', 'Standard Price', 'The Standard Price indicates the standard or normal price for a product on this price list', 1, 'D', 'PriceStd', 53015, 37, NULL, NULL, 60, NULL, 'N', 'N', 'Y', 'Y', NULL, 'Y', 1, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 957, NULL, 'N', 'N', NULL, NULL);
INSERT INTO ad_column(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, name, description, help, version, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql, mandatorylogic)
  VALUES(53026, 0, 0, 'Y', '2007-07-26 11:29:06.0', '2007-07-26 00:00:00.0', 0, 0, 'Available Quantity', 'Available Quantity (On Hand - Reserved)', 'Quantity available to promise = On Hand minus Reserved Quantity', 1, 'D', 'QtyAvailable', 53015, 29, NULL, NULL, 22, NULL, 'N', 'N', 'N', 'N', NULL, 'N', 0, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 2238, NULL, 'N', 'N', NULL, NULL);
INSERT INTO ad_column(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, name, description, help, version, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql, mandatorylogic)
  VALUES(53028, 0, 0, 'Y', '2007-07-26 18:21:48.0', '2007-07-26 00:00:00.0', 0, 0, 'Reserved Quantity', 'Reserved Quantity', 'The Reserved Quantity indicates the quantity of a product that is currently reserved.', 1, 'D', 'QtyReserved', 53015, 29, NULL, NULL, 22, NULL, 'N', 'N', 'N', 'Y', NULL, 'N', NULL, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 532, NULL, 'N', 'N', NULL, NULL);
INSERT INTO ad_column(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, name, description, help, version, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql, mandatorylogic)
  VALUES(53027, 0, 0, 'Y', '2007-07-26 00:00:00.0', '2007-07-26 00:00:00.0', 0, 0, 'On Hand Quantity', 'On Hand Quantity', 'The On Hand Quantity indicates the quantity of a product that is on hand in a warehouse.', 1, 'D', 'QtyOnHand', 53015, 29, NULL, NULL, 22, NULL, 'N', 'N', 'Y', 'N', NULL, 'N', NULL, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 530, NULL, 'N', 'N', NULL, NULL);

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
