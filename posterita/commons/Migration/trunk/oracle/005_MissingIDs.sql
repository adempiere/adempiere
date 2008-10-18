-- Missing Ids for M_InventoryLine & AD_Role  
-- AD Element
INSERT INTO ad_element(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, columnname, entitytype, name, printname, description, help, po_name, po_printname, po_description, po_help)
  VALUES(52051, 0, 0, 'Y', sysdate, 100, sysdate, 100, 'IsDiscountUptoLimitPrice', 'U', 'IsDiscountUptoLimitPrice', 'IsDiscountUptoLimitPrice', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_element(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, columnname, entitytype, name, printname, description, help, po_name, po_printname, po_description, po_help)
  VALUES(52052, 0, 0, 'Y', sysdate, 100, sysdate, 100, 'IsDiscountAllowedOnTotal', 'U', 'IsDiscountAllowedOnTotal', 'IsDiscountAllowedOnTotal', NULL, NULL, NULL, NULL, NULL, NULL);

-- AD Column
INSERT INTO ad_column(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, name, description, help, version, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql, mandatorylogic, infofactoryclass)
  VALUES(52113, 0, 0, 'Y', sysdate, sysdate, 100, 100, 'IsDiscountUptoLimitPrice', NULL, NULL, 0, 'U', 'IsDiscountUptoLimitPrice', 156, 20, NULL, NULL, 1, NULL, 'N', 'N', 'Y', 'Y', NULL, 'N', NULL, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 52051, NULL, 'N', 'N', NULL, NULL, NULL);
INSERT INTO ad_column(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, name, description, help, version, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql, mandatorylogic, infofactoryclass)
  VALUES(52114, 0, 0, 'Y', sysdate, sysdate, 100, 100, 'IsDiscountAllowedOnTotal', NULL, NULL, 0, 'U', 'IsDiscountAllowedOnTotal', 156, 20, NULL, NULL, 1, NULL, 'N', 'N', 'Y', 'Y', NULL, 'N', NULL, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 52052, NULL, 'N', 'N', NULL, NULL, NULL);

-- AD Element
INSERT INTO ad_element(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, columnname, entitytype, name, printname, description, help, po_name, po_printname, po_description, po_help)
  VALUES(52053, 0, 0, 'Y', sysdate, 100, sysdate, 100, 'QtyCsv', 'U', 'QtyCsv', 'QtyCsv', NULL, NULL, NULL, NULL, NULL, NULL);

-- AD Column
INSERT INTO ad_column(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, name, description, help, version, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql, mandatorylogic, infofactoryclass)
  VALUES(52115, 0, 0, 'Y', sysdate, sysdate, 100, 100, 'QtyCsv', NULL, NULL, 0, 'U', 'QtyCsv', 322, 29, NULL, NULL, 131089, NULL, 'N', 'N', 'Y', 'Y', NULL, 'N', NULL, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 52053, NULL, 'N', 'N', NULL, NULL, NULL);
