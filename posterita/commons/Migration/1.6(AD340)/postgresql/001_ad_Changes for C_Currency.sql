INSERT INTO AD_Element
(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, 
columnname, entitytype, name, printname, description, help, po_name, po_printname, po_description, po_help)
VALUES(52073, 0, 0, 'Y', TO_TIMESTAMP('2008-04-17 16:00:00','YYYY-MM-DD HH24:MI:SS'), 100, TO_TIMESTAMP('2008-04-17 16:00:00','YYYY-MM-DD HH24:MI:SS'), 100, 
'RoundOffFactor', 'U', 'Round Off Factor', 'Round Off Factor', 'Used to Round Off Payment Amount', NULL, NULL, NULL, NULL, NULL);

INSERT INTO ad_column
(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, name,
description, help, version, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id,
ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic,
isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, 
ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql, mandatorylogic)
VALUES(52074, 0, 0, 'Y', TO_TIMESTAMP('2008-04-17 16:00:00','YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2008-04-17 16:00:00','YYYY-MM-DD HH24:MI:SS'), 100, 100, 'RoundOffFactor' , 'Used to Round Off Payment Amount', NULL, 0, 'U', 'RoundOffFactor', 141, 22, NULL, NULL, 14, 1, 'N', 'N', 'Y', 'Y', NULL, 
'N', 0, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 52073, NULL, 'N', 'N', NULL, NULL);
