INSERT INTO AD_Element
(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, 
columnname, entitytype, name, printname, description, help, po_name, po_printname, po_description, po_help)
VALUES(52050, 0, 0, 'Y', now() , 100, now(), 100, 
'ReceiptFooterMsg', 'U', 'Receipt Footer Msg', 'Receipt Footer Msg', 'This message will be displayed at the bottom of a receipt when doing a sales or purchase', NULL, NULL, NULL, NULL, NULL);


INSERT INTO ad_column
(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, name,
description, help, version, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id,
ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic,
isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, 
ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql, mandatorylogic)
VALUES(52112, 0, 0, 'Y', now(), now(), 100, 100, 'ReceiptFooterMsg' , 'This message will be displayed at the bottom of a receipt when doing a sales or purchase', NULL, 0, 'U', 'ReceiptFooterMsg', 228, 10, NULL, NULL, 1024, 1, 'N', 'N', 'Y', 'Y', NULL, 
'N', 0, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 52050, NULL, 'N', 'N', NULL, NULL);
