INSERT INTO ad_element
(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, 
columnname, entitytype, name, printname, description, help, po_name, po_printname, po_description, po_help)
VALUES(52027, 0, 0, 'Y', TO_TIMESTAMP('2008-03-28 16:00:00','YYYY-MM-DD HH24:MI:SS'), 100, TO_TIMESTAMP('2008-03-28 16:00:00','YYYY-MM-DD HH24:MI:SS'), 100, 'isPresentForProduct', 
'U', 'isPresentForProduct', 'Present for Product', NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO ad_column
(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, name,
 description, help, version, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id,
 ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic,
isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, 
ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql, mandatorylogic)
  VALUES( 52071, 0, 0, 'Y', TO_TIMESTAMP('2008-03-28 16:00:00','YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2008-03-28 16:00:00','YYYY-MM-DD HH24:MI:SS'), 100, 100, 
'isPresentForProduct', 'Price List appears in Product Screen', '', 0, 'U', 'isPresentForProduct',
 255, 20, NULL, NULL, 1, NULL, 'N', 'N', 'N', 'Y', NULL, 
'N', 0, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 
 52027, NULL, 'N', 'N', NULL, NULL);

INSERT INTO ad_column 
(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby,
 name, description, help, version, entitytype, columnname, ad_table_id, ad_reference_id, 
ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, 
isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, 
valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, 
isalwaysupdateable, columnsql, mandatorylogic)
 VALUES(52072 , 
0, 0, 'Y', TO_TIMESTAMP('2008-03-28 16:00:00','YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2008-03-28 16:00:00','YYYY-MM-DD HH24:MI:SS'), 100, 100, 'Mandatory', 'Data entry is required in this column', 
'The field must have a value for the record to be saved to the database.', 0, 'U', 'IsMandatory', 
255, 20, NULL, NULL, 1, NULL, 'N', 'N', 'N', 'Y', NULL, 'N', 0, 'N', 'N', NULL, NULL, NULL, NULL, 'N',
 392, NULL, 'N', 'N', NULL, NULL);


