-- Sequence Changes --
INSERT INTO AD_SEQUENCE(ad_sequence_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, NAME, description, vformat, isautosequence, incrementno, startno, currentnext, currentnextsys, isaudited, istableid, prefix, suffix, startnewyear)
  VALUES(52002, 0, 0, 'Y', now(), 0, now(), 0, 'U_BlackListCheque', 'Table U_BlackListCheque', NULL, 'Y', 1, 1000000, 1000000, 100, 'N', 'Y', NULL, NULL, 'N');
INSERT INTO AD_SEQUENCE(ad_sequence_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, NAME, description, vformat, isautosequence, incrementno, startno, currentnext, currentnextsys, isaudited, istableid, prefix, suffix, startnewyear)
  VALUES(52003, 0, 0, 'Y', now(), 0, now(), 0, 'U_Web_Properties', 'Table U_Web_Properties', NULL, 'Y', 1, 1000000, 1000000, 100, 'N', 'Y', NULL, NULL, 'N');
INSERT INTO AD_SEQUENCE(ad_sequence_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, NAME, description, vformat, isautosequence, incrementno, startno, currentnext, currentnextsys, isaudited, istableid, prefix, suffix, startnewyear)
  VALUES(52004, 0, 0, 'Y', now(), 0, now(), 0, 'U_RoleMenu', 'Table U_RoleMenu', NULL, 'Y', 1, 1000000, 1000000, 100, 'N', 'Y', NULL, NULL, 'N');
INSERT INTO AD_SEQUENCE(ad_sequence_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, NAME, description, vformat, isautosequence, incrementno, startno, currentnext, currentnextsys, isaudited, istableid, prefix, suffix, startnewyear)
  VALUES(52005, 0, 0, 'Y', now(), 0, now(), 0, 'U_WebMenu', 'Table U_WebMenu', NULL, 'Y', 1, 1000000, 1000000, 100, 'N', 'Y', NULL, NULL, 'N');

-- Element Changes --
INSERT INTO AD_ELEMENT(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, columnname, entitytype, NAME, printname, description, help, po_name, po_printname, po_description, po_help)
  VALUES(52001, 0, 0, 'Y', now(), 0, now(), 0, 'U_BlackListCheque_ID', 'D', 'U_BlackListCheque_ID', 'U_BlackListCheque_ID', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO AD_ELEMENT(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, columnname, entitytype, NAME, printname, description, help, po_name, po_printname, po_description, po_help)
  VALUES(52002, 0, 0, 'Y', now(), 0, now(), 0, 'BankName', 'D', 'BankName', 'BankName', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO AD_ELEMENT(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, columnname, entitytype, NAME, printname, description, help, po_name, po_printname, po_description, po_help)
  VALUES(52003, 0, 0, 'Y', now(), 0, now(), 0, 'ChequeNo', 'D', 'ChequeNo', 'ChequeNo', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO AD_ELEMENT(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, columnname, entitytype, NAME, printname, description, help, po_name, po_printname, po_description, po_help)
  VALUES(52004, 0, 0, 'Y', now(), 0, now(), 0, 'U_Web_Properties_ID', 'D', 'U_Web_Properties_ID', 'U_Web_Properties_ID', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO AD_ELEMENT(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, columnname, entitytype, NAME, printname, description, help, po_name, po_printname, po_description, po_help)
  VALUES(52005, 0, 0, 'Y', now(), 0, now(), 0, 'U_Key', 'D', 'U_Key', 'U_Key', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO AD_ELEMENT(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, columnname, entitytype, NAME, printname, description, help, po_name, po_printname, po_description, po_help)
  VALUES(52006, 0, 0, 'Y', now(), 0, now(), 0, 'U_Value', 'D', 'U_Value', 'U_Value', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO AD_ELEMENT(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, columnname, entitytype, NAME, printname, description, help, po_name, po_printname, po_description, po_help)
  VALUES(52007, 0, 0, 'Y', now(), 0, now(), 0, 'U_RoleMenu_ID', 'D', 'U_RoleMenu_ID', 'U_RoleMenu_ID', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO AD_ELEMENT(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, columnname, entitytype, NAME, printname, description, help, po_name, po_printname, po_description, po_help)
  VALUES(52008, 0, 0, 'Y', now(), 0, now(), 0, 'U_WebMenu_ID', 'D', 'U_WebMenu_ID', 'U_WebMenu_ID', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO AD_ELEMENT(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, columnname, entitytype, NAME, printname, description, help, po_name, po_printname, po_description, po_help)
  VALUES(52009, 0, 0, 'Y', now(), 0, now(), 0, 'MenuLink', 'D', 'MenuLink', 'MenuLink', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO AD_ELEMENT(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, columnname, entitytype, NAME, printname, description, help, po_name, po_printname, po_description, po_help)
  VALUES(52010, 0, 0, 'Y', now(), 0, now(), 0, 'Module', 'D', 'Module', 'Module', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO AD_ELEMENT(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, columnname, entitytype, NAME, printname, description, help, po_name, po_printname, po_description, po_help)
  VALUES(52011, 0, 0, 'Y', now(), 0, now(), 0, 'ParentMenu_ID', 'D', 'ParentMenu_ID', 'ParentMenu_ID', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO AD_ELEMENT(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, columnname, entitytype, NAME, printname, description, help, po_name, po_printname, po_description, po_help)
  VALUES(52012, 0, 0, 'Y', now(), 0, now(), 0, 'HasSubMenu', 'D', 'HasSubMenu', 'HasSubMenu', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO AD_ELEMENT(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, columnname, entitytype, NAME, printname, description, help, po_name, po_printname, po_description, po_help)
  VALUES(52013, 0, 0, 'Y', now(), 0, now(), 0, 'ImageLink', 'D', 'ImageLink', 'ImageLink', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO AD_ELEMENT(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, columnname, entitytype, NAME, printname, description, help, po_name, po_printname, po_description, po_help)
  VALUES(52014, 0, 0, 'Y', now(), 0, now(), 0, 'Position', 'D', 'Position', 'Position', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO AD_ELEMENT(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, columnname, entitytype, NAME, printname, description, help, po_name, po_printname, po_description, po_help)
  VALUES(52015, 0, 0, 'Y', now(), 0, now(), 0, 'CashDrawer', 'D', 'CashDrawer', 'CashDrawer', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO AD_ELEMENT(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, columnname, entitytype, NAME, printname, description, help, po_name, po_printname, po_description, po_help)
  VALUES(52016, 0, 0, 'Y', now(), 0, now(), 0, 'Sequence', 'D', 'Sequence', 'Sequence', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO AD_ELEMENT(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, columnname, entitytype, NAME, printname, description, help, po_name, po_printname, po_description, po_help)
  VALUES(52017, 0, 0, 'Y', now(), 0, now(), 0, 'Category', 'D', 'Category', 'Category', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO AD_ELEMENT(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, columnname, entitytype, NAME, printname, description, help, po_name, po_printname, po_description, po_help)
  VALUES(52018, 0, 0, 'Y', now(), 0, now(), 0, 'Group1', 'D', 'Group1', 'Group1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO AD_ELEMENT(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, columnname, entitytype, NAME, printname, description, help, po_name, po_printname, po_description, po_help)
  VALUES(52019, 0, 0, 'Y', now(), 0, now(), 0, 'Group2', 'D', 'Group2', 'Group2', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO AD_ELEMENT(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, columnname, entitytype, NAME, printname, description, help, po_name, po_printname, po_description, po_help)
  VALUES(52020, 0, 0, 'Y', now(), 0, now(), 0, 'OrderType', 'D', 'OrderType', 'OrderType', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO AD_ELEMENT(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, columnname, entitytype, NAME, printname, description, help, po_name, po_printname, po_description, po_help)
  VALUES(52021, 0, 0, 'Y', now(), 0, now(), 0, 'AmountTendered', 'D', 'AmountTendered', 'AmountTendered', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO AD_ELEMENT(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, columnname, entitytype, NAME, printname, description, help, po_name, po_printname, po_description, po_help)
  VALUES(52022, 0, 0, 'Y', now(), 0, now(), 0, 'AmountRefunded', 'D', 'AmountRefunded', 'AmountRefunded', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO AD_ELEMENT(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, columnname, entitytype, NAME, printname, description, help, po_name, po_printname, po_description, po_help)
  VALUES(52023, 0, 0, 'Y', now(), 0, now(), 0, 'UserPIN', 'D', 'UserPIN', 'UserPIN', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO AD_ELEMENT(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, columnname, entitytype, NAME, printname, description, help, po_name, po_printname, po_description, po_help)
  VALUES(52024, 0, 0, 'Y', now(), 0, now(), 0, 'UserDiscount', 'D', 'UserDiscount', 'UserDiscount', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO AD_ELEMENT(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, columnname, entitytype, NAME, printname, description, help, po_name, po_printname, po_description, po_help)
  VALUES(52025, 0, 0, 'Y', now(), 0, now(), 0, 'ClassName', 'D', 'ClassName', 'ClassName', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO AD_ELEMENT(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, columnname, entitytype, NAME, printname, description, help, po_name, po_printname, po_description, po_help)
  VALUES(52026, 0, 0, 'Y', now(), 0, now(), 0, 'Args', 'D', 'Args', 'Args', NULL, NULL, NULL, NULL, NULL, NULL);

-- Table Changes --
INSERT INTO AD_TABLE(ad_table_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, NAME, description, help, tablename, isview, accesslevel, entitytype, ad_window_id, ad_val_rule_id, loadseq, issecurityenabled, isdeleteable, ishighvolume, importtable, ischangelog, replicationtype, po_window_id, copycolumnsfromtable)
  VALUES(52000, 0, 0, 'Y', now(), 0, now(), 0, 'U_BlackListCheque', NULL, NULL, 'U_BlackListCheque', 'N', '3', 'D', NULL, NULL, NULL, 'N', 'Y', 'N', NULL, 'N', 'L', NULL, NULL);
INSERT INTO AD_TABLE(ad_table_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, NAME, description, help, tablename, isview, accesslevel, entitytype, ad_window_id, ad_val_rule_id, loadseq, issecurityenabled, isdeleteable, ishighvolume, importtable, ischangelog, replicationtype, po_window_id, copycolumnsfromtable)
  VALUES(52001, 0, 0, 'Y', now(), 0, now(), 0, 'U_Web_Properties', NULL, NULL, 'U_Web_Properties', 'N', '3', 'D', NULL, NULL, NULL, 'N', 'Y', 'N', NULL, 'N', 'L', NULL, NULL);
INSERT INTO AD_TABLE(ad_table_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, NAME, description, help, tablename, isview, accesslevel, entitytype, ad_window_id, ad_val_rule_id, loadseq, issecurityenabled, isdeleteable, ishighvolume, importtable, ischangelog, replicationtype, po_window_id, copycolumnsfromtable)
  VALUES(52002, 0, 0, 'Y', now(), 0, now(), 0, 'U_RoleMenu', NULL, NULL, 'U_RoleMenu', 'N', '3', 'D', NULL, NULL, NULL, 'N', 'Y', 'N', NULL, 'N', 'L', NULL, NULL);
INSERT INTO AD_TABLE(ad_table_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, NAME, description, help, tablename, isview, accesslevel, entitytype, ad_window_id, ad_val_rule_id, loadseq, issecurityenabled, isdeleteable, ishighvolume, importtable, ischangelog, replicationtype, po_window_id, copycolumnsfromtable)
  VALUES(52003, 0, 0, 'Y', now(), 0, now(), 0, 'U_WebMenu', NULL, NULL, 'U_WebMenu', 'N', '4', 'D', NULL, NULL, NULL, 'N', 'Y', 'N', NULL, 'N', 'L', NULL, NULL);

-- Reference changes --
INSERT INTO AD_REFERENCE(AD_REFERENCE_ID, AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, UPDATED, UPDATEDBY, NAME, DESCRIPTION, HELP, VALIDATIONTYPE, VFORMAT, ENTITYTYPE)
  VALUES(52000, 0, 0, 'Y', now(), 100, now(), 100, 'U_WebMenu_ID', NULL, NULL, 'T', NULL, 'D');

-- Column Changes --
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52011, 0, 0, 'Y', now(), now(), 0, 0, 'U_BlackListCheque_ID', NULL, NULL, 0, 'D', 'U_BlackListCheque_ID', 52000, 13, NULL, NULL, 22, NULL, 'Y', 'N', 'Y', 'N', NULL, 'N', 10, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 52001, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52012, 0, 0, 'Y', now(), now(), 0, 0, 'AD_Client_ID', NULL, NULL, 0, 'D', 'AD_Client_ID', 52000, 19, NULL, 129, 22, '@AD_Client_ID@', 'N', 'N', 'Y', 'N', NULL, 'N', 20, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 102, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52013, 0, 0, 'Y', now(), now(), 0, 0, 'AD_Org_ID', NULL, NULL, 0, 'D', 'AD_Org_ID', 52000, 19, NULL, 104, 22, '@AD_Org_ID@', 'N', 'N', 'Y', 'N', NULL, 'N', 30, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 113, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52014, 0, 0, 'Y', now(), now(), 0, 0, 'IsActive', NULL, NULL, 0, 'D', 'IsActive', 52000, 20, NULL, NULL, 1, '''Y'' ', 'N', 'N', 'Y', 'Y', NULL, 'N', 40, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 348, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52015, 0, 0, 'Y', now(), now(), 0, 0, 'Created', NULL, NULL, 0, 'D', 'Created', 52000, 16, NULL, NULL, 7, NULL, 'N', 'N', 'Y', 'N', NULL, 'N', 50, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 245, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52016, 0, 0, 'Y', now(), now(), 0, 0, 'CreatedBy', NULL, NULL, 0, 'D', 'CreatedBy', 52000, 18, 110, NULL, 10, NULL, 'N', 'N', 'Y', 'N', NULL, 'N', 60, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 246, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52017, 0, 0, 'Y', now(), now(), 0, 0, 'Updated', NULL, NULL, 0, 'D', 'Updated', 52000, 16, NULL, NULL, 7, NULL, 'N', 'N', 'Y', 'N', NULL, 'N', 70, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 607, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52018, 0, 0, 'Y', now(), now(), 0, 0, 'UpdatedBy', NULL, NULL, 0, 'D', 'UpdatedBy', 52000, 18, 110, NULL, 10, NULL, 'N', 'N', 'Y', 'N', NULL, 'N', 80, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 608, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52019, 0, 0, 'Y', now(), now(), 0, 0, 'BankName', NULL, NULL, 0, 'D', 'BankName', 52000, 10, NULL, NULL, 120, NULL, 'N', 'N', 'Y', 'Y', NULL, 'N', 90, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 52002, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52020, 0, 0, 'Y', now(), now(), 0, 0, 'ChequeNo', NULL, NULL, 0, 'D', 'ChequeNo', 52000, 10, NULL, NULL, 120, NULL, 'N', 'N', 'Y', 'Y', NULL, 'N', 100, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 52003, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52021, 0, 0, 'Y', now(), now(), 0, 0, 'U_Web_Properties_ID', NULL, NULL, 0, 'D', 'U_Web_Properties_ID', 52001, 13, NULL, NULL, 22, NULL, 'Y', 'N', 'Y', 'N', NULL, 'N', 10, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 52004, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52022, 0, 0, 'Y', now(), now(), 0, 0, 'AD_Client_ID', NULL, NULL, 0, 'D', 'AD_Client_ID', 52001, 19, NULL, 129, 22, '@AD_Client_ID@', 'N', 'N', 'Y', 'N', NULL, 'N', 20, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 102, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52023, 0, 0, 'Y', now(), now(), 0, 0, 'AD_Org_ID', NULL, NULL, 0, 'D', 'AD_Org_ID', 52001, 19, NULL, 104, 22, '@AD_Org_ID@', 'N', 'N', 'Y', 'N', NULL, 'N', 30, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 113, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52024, 0, 0, 'Y', now(), now(), 0, 0, 'IsActive', NULL, NULL, 0, 'D', 'IsActive', 52001, 20, NULL, NULL, 1, '''Y'' ', 'N', 'N', 'Y', 'Y', NULL, 'N', 40, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 348, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52025, 0, 0, 'Y', now(), now(), 0, 0, 'Created', NULL, NULL, 0, 'D', 'Created', 52001, 16, NULL, NULL, 7, NULL, 'N', 'N', 'Y', 'N', NULL, 'N', 50, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 245, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52026, 0, 0, 'Y', now(), now(), 0, 0, 'CreatedBy', NULL, NULL, 0, 'D', 'CreatedBy', 52001, 18, 110, NULL, NULL, NULL, 'N', 'N', 'Y', 'N', NULL, 'N', 60, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 246, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52027, 0, 0, 'Y', now(), now(), 0, 0, 'Updated', NULL, NULL, 0, 'D', 'Updated', 52001, 16, NULL, NULL, 7, NULL, 'N', 'N', 'Y', 'N', NULL, 'N', 70, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 607, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52028, 0, 0, 'Y', now(), now(), 0, 0, 'UpdatedBy', NULL, NULL, 0, 'D', 'UpdatedBy', 52001, 18, 110, NULL, NULL, NULL, 'N', 'N', 'Y', 'N', NULL, 'N', 80, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 608, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52029, 0, 0, 'Y', now(), now(), 0, 0, 'U_Key', NULL, NULL, 0, 'D', 'U_Key', 52001, 10, NULL, NULL, 240, NULL, 'N', 'N', 'Y', 'Y', NULL, 'N', 90, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 52005, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52030, 0, 0, 'Y', now(), now(), 0, 0, 'U_Value', NULL, NULL, 0, 'D', 'U_Value', 52001, 10, NULL, NULL, 240, NULL, 'N', 'N', 'Y', 'Y', NULL, 'N', 100, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 52006, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52031, 0, 0, 'Y', now(), now(), 0, 0, 'U_RoleMenu_ID', NULL, NULL, 0, 'D', 'U_RoleMenu_ID', 52002, 13, NULL, NULL, 22, NULL, 'Y', 'N', 'Y', 'N', NULL, 'N', 10, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 52007, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52032, 0, 0, 'Y', now(), now(), 0, 0, 'AD_Client_ID', NULL, NULL, 0, 'D', 'AD_Client_ID', 52002, 19, NULL, 129, 22, '@AD_Client_ID@', 'N', 'N', 'Y', 'N', NULL, 'N', 20, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 102, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52033, 0, 0, 'Y', now(), now(), 0, 0, 'AD_Org_ID', NULL, NULL, 0, 'D', 'AD_Org_ID', 52002, 19, NULL, 104, 22, '@AD_Org_ID@', 'N', 'N', 'Y', 'N', NULL, 'N', 30, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 113, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52034, 0, 0, 'Y', now(), now(), 0, 0, 'IsActive', NULL, NULL, 0, 'D', 'IsActive', 52002, 20, NULL, NULL, 1, '''Y'' ', 'N', 'N', 'Y', 'Y', NULL, 'N', 40, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 348, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52035, 0, 0, 'Y', now(), now(), 0, 0, 'Created', NULL, NULL, 0, 'D', 'Created', 52002, 16, NULL, NULL, 7, NULL, 'N', 'N', 'Y', 'N', NULL, 'N', 50, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 245, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52036, 0, 0, 'Y', now(), now(), 0, 0, 'CreatedBy', NULL, NULL, 0, 'D', 'CreatedBy', 52002, 18, 110, NULL, NULL, NULL, 'N', 'N', 'Y', 'N', NULL, 'N', 60, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 246, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52037, 0, 0, 'Y', now(), now(), 0, 0, 'Updated', NULL, NULL, 0, 'D', 'Updated', 52002, 16, NULL, NULL, 7, NULL, 'N', 'N', 'Y', 'N', NULL, 'N', 70, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 607, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52038, 0, 0, 'Y', now(), now(), 0, 0, 'UpdatedBy', NULL, NULL, 0, 'D', 'UpdatedBy', 52002, 18, 110, NULL, NULL, NULL, 'N', 'N', 'Y', 'N', NULL, 'N', 80, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 608, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52039, 0, 0, 'Y', now(), now(), 0, 0, 'AD_Role_ID', NULL, NULL, 0, 'D', 'AD_Role_ID', 52002, 19, NULL, NULL, 10, NULL, 'N', 'N', 'Y', 'Y', NULL, 'N', 90, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 123, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52040, 0, 0, 'Y', now(), now(), 0, 0, 'U_WebMenu_ID', NULL, NULL, 0, 'D', 'U_WebMenu_ID', 52002, 19, NULL, NULL, 10, NULL, 'N', 'N', 'Y', 'Y', NULL, 'N', 100, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 52008, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52041, 0, 0, 'Y', now(), now(), 0, 0, 'U_WebMenu_ID', NULL, NULL, 0, 'D', 'U_WebMenu_ID', 52003, 13, NULL, NULL, 22, NULL, 'Y', 'N', 'Y', 'N', NULL, 'N', 10, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 52008, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52042, 0, 0, 'Y', now(), now(), 0, 0, 'AD_Client_ID', NULL, NULL, 0, 'D', 'AD_Client_ID', 52003, 19, NULL, 129, 22, '@AD_Client_ID@', 'N', 'N', 'Y', 'N', NULL, 'N', 20, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 102, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52043, 0, 0, 'Y', now(), now(), 0, 0, 'AD_Org_ID', NULL, NULL, 0, 'D', 'AD_Org_ID', 52003, 19, NULL, 104, 22, '@AD_Org_ID@', 'N', 'N', 'Y', 'N', NULL, 'N', 30, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 113, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52044, 0, 0, 'Y', now(), now(), 0, 0, 'IsActive', NULL, NULL, 0, 'D', 'IsActive', 52003, 20, NULL, NULL, 1, '''Y'' ', 'N', 'N', 'Y', 'Y', NULL, 'N', 40, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 348, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52045, 0, 0, 'Y', now(), now(), 0, 0, 'Created', NULL, NULL, 0, 'D', 'Created', 52003, 16, NULL, NULL, 7, NULL, 'N', 'N', 'Y', 'N', NULL, 'N', 50, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 245, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52046, 0, 0, 'Y', now(), now(), 0, 0, 'CreatedBy', NULL, NULL, 0, 'D', 'CreatedBy', 52003, 18, 110, NULL, 10, NULL, 'N', 'N', 'Y', 'N', NULL, 'N', 60, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 246, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52047, 0, 0, 'Y', now(), now(), 0, 0, 'Updated', NULL, NULL, 0, 'D', 'Updated', 52003, 16, NULL, NULL, 7, NULL, 'N', 'N', 'Y', 'N', NULL, 'N', 70, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 607, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52048, 0, 0, 'Y', now(), now(), 0, 0, 'UpdatedBy', NULL, NULL, 0, 'D', 'UpdatedBy', 52003, 18, 110, NULL, 10, NULL, 'N', 'N', 'Y', 'N', NULL, 'N', 80, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 608, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52049, 0, 0, 'Y', now(), now(), 0, 0, 'Name', NULL, NULL, 0, 'D', 'Name', 52003, 10, NULL, NULL, 120, NULL, 'N', 'N', 'Y', 'Y', NULL, 'Y', 90, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 469, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52050, 0, 0, 'Y', now(), now(), 0, 0, 'MenuLink', NULL, NULL, 0, 'D', 'MenuLink', 52003, 10, NULL, NULL, 510, NULL, 'N', 'N', 'Y', 'Y', NULL, 'N', 100, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 52009, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52051, 0, 0, 'Y', now(), now(), 0, 0, 'Module', NULL, NULL, 0, 'D', 'Module', 52003, 10, NULL, NULL, 120, NULL, 'N', 'N', 'Y', 'Y', NULL, 'N', 110, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 52010, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52052, 0, 0, 'Y', now(), now(), 0, 0, 'ParentMenu_ID', NULL, NULL, 0, 'D', 'ParentMenu_ID', 52003, 18, 52000, NULL, 10, NULL, 'N', 'N', 'N', 'Y', NULL, 'N', 120, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 52011, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52053, 0, 0, 'Y', now(), now(), 0, 0, 'HasSubMenu', NULL, NULL, 0, 'D', 'HasSubMenu', 52003, 20, NULL, NULL, 1, '''N''', 'N', 'N', 'Y', 'Y', NULL, 'N', 130, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 52012, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52054, 0, 0, 'Y', now(), now(), 0, 0, 'Description', NULL, NULL, 0, 'D', 'Description', 52003, 10, NULL, NULL, 200, NULL, 'N', 'N', 'N', 'Y', NULL, 'N', 140, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 275, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52055, 0, 0, 'Y', now(), now(), 0, 0, 'ImageLink', NULL, NULL, 0, 'D', 'ImageLink', 52003, 10, NULL, NULL, 510, NULL, 'N', 'N', 'N', 'Y', NULL, 'N', 150, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 52013, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52056, 0, 0, 'Y', now(), now(), 0, 0, 'Position', NULL, NULL, 0, 'D', 'Position', 52003, 10, NULL, NULL, 10, NULL, 'N', 'N', 'N', 'Y', NULL, 'N', 160, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 52014, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52057, 0, 0, 'Y', now(), now(), 0, 0, 'Help', NULL, NULL, 0, 'D', 'Help', 52003, 10, NULL, NULL, 2000, NULL, 'N', 'N', 'N', 'Y', NULL, 'N', 170, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 326, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52058, 0, 0, 'Y', now(), now(), 0, 0, 'CashDrawer', NULL, NULL, 0, 'D', 'CashDrawer', 748, 10, NULL, NULL, 120, NULL, 'N', 'N', 'N', 'Y', NULL, 'N', 10, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 52015, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52059, 0, 0, 'Y', now(), now(), 0, 0, 'Sequence', NULL, NULL, 0, 'D', 'Sequence', 52003, 22, NULL, NULL, 22, NULL, 'N', 'N', 'N', 'Y', NULL, 'N', 10, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 52016, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52060, 0, 0, 'Y', now(), now(), 0, 0, 'Category', NULL, NULL, 0, 'D', 'Category', 52003, 10, NULL, NULL, 120, NULL, 'N', 'N', 'N', 'Y', NULL, 'N', 20, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 52017, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52061, 0, 0, 'Y', now(), now(), 0, 0, 'Group1', NULL, NULL, 0, 'D', 'Group1', 208, 10, NULL, NULL, 255, NULL, 'N', 'N', 'N', 'Y', NULL, 'N', 50, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 52018, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52062, 0, 0, 'Y', now(), now(), 0, 0, 'Group2', NULL, NULL, 0, 'D', 'Group2', 208, 10, NULL, NULL, 255, NULL, 'N', 'N', 'N', 'Y', NULL, 'N', 60, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 52019, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52063, 0, 0, 'Y', now(), now(), 0, 0, 'OrderType', NULL, NULL, 0, 'D', 'OrderType', 259, 10, NULL, NULL, 510, NULL, 'N', 'N', 'N', 'Y', NULL, 'N', 10, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 52020, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52064, 0, 0, 'Y', now(), now(), 0, 0, 'AmountTendered', NULL, NULL, 0, 'D', 'AmountTendered', 259, 22, NULL, NULL, 22, NULL, 'N', 'N', 'N', 'Y', NULL, 'N', 30, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 52021, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52065, 0, 0, 'Y', now(), now(), 0, 0, 'AmountRefunded', NULL, NULL, 0, 'D', 'AmountRefunded', 259, 22, NULL, NULL, 22, NULL, 'N', 'N', 'N', 'Y', NULL, 'N', 40, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 52022, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52066, 0, 0, 'Y', now(), now(), 0, 0, 'UserPIN', NULL, NULL, 0, 'D', 'UserPIN', 114, 10, NULL, NULL, 20, NULL, 'N', 'N', 'N', 'Y', NULL, 'N', 10, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 52023, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52067, 0, 0, 'Y', now(), now(), 0, 0, 'UserDiscount', NULL, NULL, 0, 'D', 'UserDiscount', 156, 22, NULL, NULL, 22, NULL, 'N', 'N', 'N', 'Y', NULL, 'N', 20, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 52024, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52068, 0, 0, 'Y', now(), now(), 0, 0, 'ClassName', NULL, NULL, 0, 'D', 'ClassName', 493, 10, NULL, NULL, 240, NULL, 'N', 'N', 'N', 'Y', NULL, 'N', 10, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 52025, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52069, 0, 0, 'Y', now(), now(), 0, 0, 'Args', NULL, NULL, 0, 'D', 'Args', 493, 10, NULL, NULL, 510, NULL, 'N', 'N', 'N', 'Y', NULL, 'N', 20, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 52026, NULL, 'N', 'N', NULL);
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, NAME, description, help, VERSION, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql)
  VALUES(52070, 0, 0, 'Y', now(), now(), 0, 0, 'C_POS_ID', NULL, NULL, 0, 'D', 'C_POS_ID', 259, 19, NULL, NULL, 10, NULL, 'N', 'N', 'N', 'Y', NULL, 'N', 20, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 2581, NULL, 'N', 'N', NULL);

-- AD_Ref_table Changes --

INSERT INTO AD_REF_TABLE(AD_REFERENCE_ID, AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, UPDATED, UPDATEDBY, AD_TABLE_ID, AD_KEY, AD_DISPLAY, ISVALUEDISPLAYED, WHERECLAUSE, ORDERBYCLAUSE, ENTITYTYPE)
  VALUES(52000, 0, 0, 'Y', now(), 100, now(), 100, 52003, 52041, 52049, 'N', NULL, NULL, 'D');

-- Process Changes --
INSERT INTO AD_PROCESS(ad_process_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, NAME, description, help, accesslevel, entitytype, procedurename, isreport, isdirectprint, ad_reportview_id, classname, statistic_count, statistic_seconds, ad_printformat_id, workflowvalue, ad_workflow_id, isbetafunctionality, isserverprocess, showhelp, jasperreport)
  VALUES(52003, 0, 0, 'Y', now(), 100, now(), 100, 'U_RoleMenu_Update', 'Update Role Menu', NULL, NULL, '3', 'D', NULL, 'N', 'N', NULL, 'org.adempiere.process.UpdateRoleMenu', 0, 0, NULL, NULL, NULL, 'N', 'N', 'Y', NULL);

INSERT INTO AD_PROCESS_PARA(ad_process_para_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, NAME, description, help, ad_process_id, seqno, ad_reference_id, ad_reference_value_id, ad_val_rule_id, columnname, iscentrallymaintained, fieldlength, ismandatory, isrange, defaultvalue, defaultvalue2, vformat, valuemin, valuemax, ad_element_id, entitytype)
  VALUES(52000, 0, 0, 'Y', now(), 100, now(), 100, 'Role', NULL, NULL, 52003, 10, 30, NULL, NULL, 'AD_Role_ID', 'Y', 0, 'Y', 'N', NULL, NULL, NULL, NULL, NULL, 123, 'D');

-- Process Access --
INSERT INTO AD_PROCESS_ACCESS(ad_process_id, ad_role_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, isreadwrite)
  VALUES(52003, 0, 0, 0, 'Y', now(), 100, now(), 100, 'Y');
INSERT INTO AD_PROCESS_ACCESS(ad_process_id, ad_role_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, isreadwrite)
  VALUES(52003, 102, 0, 0, 'Y', now(), 100, now(), 100, 'Y');
INSERT INTO AD_PROCESS_ACCESS(ad_process_id, ad_role_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, isreadwrite)
  VALUES(52003, 103, 0, 0, 'Y',now(), 100, now(), 100, 'Y');

-- Menu Changes --
INSERT INTO AD_MENU(ad_menu_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, NAME, updatedby, description, issummary, issotrx, isreadonly, action, ad_window_id, ad_workflow_id, ad_task_id, ad_process_id, ad_form_id, ad_workbench_id, entitytype)
  VALUES(52000, 0, 0, 'Y', now(), 100, now(), 'Update Role Menu', 100, NULL, 'N', 'N', 'N', 'P', NULL, NULL, NULL, 52003, NULL, NULL, 'D');
INSERT INTO AD_MENU(ad_menu_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, NAME, updatedby, description, issummary, issotrx, isreadonly, action, ad_window_id, ad_workflow_id, ad_task_id, ad_process_id, ad_form_id, ad_workbench_id, entitytype)
  VALUES(52001, 0, 0, 'Y', now(), 100, now(), 'Posterita', 100, NULL, 'Y', 'N', 'N', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'D');
INSERT INTO AD_TREENODEMM(ad_tree_id, node_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, parent_id, seqno)
  VALUES(10, 52000, 0, 0, 'Y', now(), 0, now(), 0, 52001, 0);
INSERT INTO AD_TREENODEMM(ad_tree_id, node_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, parent_id, seqno)
  VALUES(10, 52001, 0, 0, 'Y', now(), 0, now(), 0, 0, 10);




-- Message Changes --
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52003, 0, 0, 'Y', now(), 100, now(), 100, '1000_rupees', '1000 Rupees', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52004, 0, 0, 'Y', now(), 100, now(), 100, '100_dollars', '100 dollars', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52005, 0, 0, 'Y', now(), 100, now(), 100, '100_rupees', '100 Rupees', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52006, 0, 0, 'Y', now(), 100, now(), 100, '10_dollars', '10 dollars', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52007, 0, 0, 'Y', now(), 100, now(), 100, '10_rupees', '10 Rupees', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52008, 0, 0, 'Y', now(), 100, now(), 100, '1_cent', '1 cent', '1 cent', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52009, 0, 0, 'Y', now(), 100, now(), 100, '1_dollar', '1 dollar', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(520010, 0, 0, 'Y', now(), 100, now(), 100, '1_rupee', '1 Rupee', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52011, 0, 0, 'Y', now(), 100, now(), 100, '2000_rupees', '2000 Rupees', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52012, 0, 0, 'Y', now(), 100, now(), 100, '200_rupees', '200 Rupees', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52013, 0, 0, 'Y', now(), 100, now(), 100, '20_cents', '20 cents', '20 cents', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52014, 0, 0, 'Y', now(), 100, now(), 100, '20_dollars', '20 dollars', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52015, 0, 0, 'Y', now(), 100, now(), 100, '25_rupees', '25 Rupees', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52016, 0, 0, 'Y', now(), 100, now(), 100, '500_rupees', '500 Rupees', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52017, 0, 0, 'Y', now(), 100, now(), 100, '50_cents', '50 cents', '50 cents', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52018, 0, 0, 'Y', now(), 100, now(), 100, '50_dollars', '50 dollars', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52019, 0, 0, 'Y', now(), 100, now(), 100, '50_rupees', '50 Rupees', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52020, 0, 0, 'Y', now(), 100, now(), 100, '5_cents', '5 cents', '5 cents', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52021, 0, 0, 'Y', now(), 100, now(), 100, '5_dollars', '5 dollars', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52022, 0, 0, 'Y', now(), 100, now(), 100, '5_rupees', '5 Rupees', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52023, 0, 0, 'Y', now(), 100, now(), 100, 'Due0', 'Due 0', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52024, 0, 0, 'Y', now(), 100, now(), 100, 'Due0_30', 'Due 0-30', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52025, 0, 0, 'Y', now(), 100, now(), 100, 'account.type', 'Account Type', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52026, 0, 0, 'Y', now(), 100, now(), 100, 'accounts.receivable', 'Accounts Receivable-Trade', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52027, 0, 0, 'Y', now(), 100, now(), 100, 'activate', 'Activate', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52028, 0, 0, 'Y', now(), 100, now(), 100, 'activate.vendor', 'Activate Vendor', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52029, 0, 0, 'Y', now(), 100, now(), 100, 'actual.price', 'Actual Price', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52030, 0, 0, 'Y', now(), 100, now(), 100, 'add', 'Add', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52031, 0, 0, 'Y', now(), 100, now(), 100, 'add.black.listed.cheque', 'Add Black Listed Cheque', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52032, 0, 0, 'Y', now(), 100, now(), 100, 'add.customer.fidelity.card', 'Add Customer for fidelity card', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52033, 0, 0, 'Y', now(), 100, now(), 100, 'add.customer.for.fidelity.card', 'Add Customer for fidelity card', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52034, 0, 0, 'Y', now(), 100, now(), 100, 'add.record', 'Add Record', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52035, 0, 0, 'Y', now(), 100, now(), 100, 'add.to.cart', 'Add To Cart', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52036, 0, 0, 'Y', now(), 100, now(), 100, 'address', 'Address', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52037, 0, 0, 'Y', now(), 100, now(), 100, 'adjust.cash.book', 'Adjust Cash Book', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52038, 0, 0, 'Y', now(), 100, now(), 100, 'advanced', 'Advanced', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52039, 0, 0, 'Y', now(), 100, now(), 100, 'aging', 'Aging', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52040, 0, 0, 'Y', now(), 100, now(), 100, 'ajax.error', 'Some error occured while communicating with the server. Please try again.', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52041, 0, 0, 'Y', now(), 100, now(), 100, 'all', 'All', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52042, 0, 0, 'Y', now(), 100, now(), 100, 'all.products', 'All products', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52043, 0, 0, 'Y', now(), 100, now(), 100, 'amount.difference', 'Difference', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52044, 0, 0, 'Y', now(), 100, now(), 100, 'amount.expense', 'Expense', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52045, 0, 0, 'Y', now(), 100, now(), 100, 'amount.receipt', 'Receipt', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52046, 0, 0, 'Y', now(), 100, now(), 100, 'amount.refunded', 'Amount refunded', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52047, 0, 0, 'Y', now(), 100, now(), 100, 'amount.tendered', 'Amount Tendered', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52048, 0, 0, 'Y', now(), 100, now(), 100, 'amount.transfer', 'Transfer', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52049, 0, 0, 'Y', now(), 100, now(), 100, 'application.name', 'Application Name', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52050, 0, 0, 'Y', now(), 100, now(), 100, 'application.version', 'Application Version', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52051, 0, 0, 'Y', now(), 100, now(), 100, 'asset', 'Assets', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52052, 0, 0, 'Y', now(), 100, now(), 100, 'at.least.one', 'At least one', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52053, 0, 0, 'Y', now(), 100, now(), 100, 'attach.image', 'Attach Image', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52054, 0, 0, 'Y', now(), 100, now(), 100, 'attribute', 'Attribute', 'Attribute', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52055, 0, 0, 'Y', now(), 100, now(), 100, 'attribute.set', 'Attribute Set', 'Attribute Set', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52056, 0, 0, 'Y', now(), 100, now(), 100, 'available.menu', 'Available Menus', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52057, 0, 0, 'Y', now(), 100, now(), 100, 'back', 'Back', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52058, 0, 0, 'Y', now(), 100, now(), 100, 'barcode', 'Barcode', 'Barcode', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52059, 0, 0, 'Y', now(), 100, now(), 100, 'barcode.already.exists', 'Barcode already exists!', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52060, 0, 0, 'Y', now(), 100, now(), 100, 'black.listed.cheques', 'Black Listed Cheques', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52061, 0, 0, 'Y', now(), 100, now(), 100, 'bpartner.info', 'Business Partner Info', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52062, 0, 0, 'Y', now(), 100, now(), 100, 'bpartner.trx.details', 'Business Partner Trx Details', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52063, 0, 0, 'Y', now(), 100, now(), 100, 'brand', 'Brand', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52064, 0, 0, 'Y', now(), 100, now(), 100, 'cal.period.curr', 'Calculation Period And Curr.', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52065, 0, 0, 'Y', now(), 100, now(), 100, 'card', 'Card', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52066, 0, 0, 'Y', now(), 100, now(), 100, 'card.amount', 'Card Amount', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52067, 0, 0, 'Y', now(), 100, now(), 100, 'card.amt.entered', 'Card Amount Entered', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52068, 0, 0, 'Y', now(), 100, now(), 100, 'card.amt.tendered', 'Card amount tendered', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52069, 0, 0, 'Y', now(), 100, now(), 100, 'card.amt.total', 'Card Amount Total', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52070, 0, 0, 'Y', now(), 100, now(), 100, 'card.is.empty', 'The Cart is Empty !', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52071, 0, 0, 'Y', now(), 100, now(), 100, 'card.no', 'Card No', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52072, 0, 0, 'Y', now(), 100, now(), 100, 'card.total', 'Card Total', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52073, 0, 0, 'Y', now(), 100, now(), 100, 'cart.addmore', 'Add More', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52074, 0, 0, 'Y', now(), 100, now(), 100, 'cart.empty', 'Cart is empty!', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52075, 0, 0, 'Y', now(), 100, now(), 100, 'cart.has', 'Cart has', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52076, 0, 0, 'Y', now(), 100, now(), 100, 'cart.items', ' items', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52077, 0, 0, 'Y', now(), 100, now(), 100, 'cart.remove', 'Remove', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52078, 0, 0, 'Y', now(), 100, now(), 100, 'cash', 'Cash', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52079, 0, 0, 'Y', now(), 100, now(), 100, 'cash.amount', 'Cash Amount', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52080, 0, 0, 'Y', now(), 100, now(), 100, 'cash.book', 'Cash Book', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52081, 0, 0, 'Y', now(), 100, now(), 100, 'cash.book.adjust', 'The Cash Book has been adjusted.', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52082, 0, 0, 'Y', now(), 100, now(), 100, 'cash.book.adjusted', 'The Cash Book has been adjusted', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52083, 0, 0, 'Y', now(), 100, now(), 100, 'cash.book.history', 'Cash Book History', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52084, 0, 0, 'Y', now(), 100, now(), 100, 'cash.line.detail', 'Cash Line Details', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52085, 0, 0, 'Y', now(), 100, now(), 100, 'cash.payment', 'Cash Payment', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52086, 0, 0, 'Y', now(), 100, now(), 100, 'cash.receipt', 'Cash Receipt', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52087, 0, 0, 'Y', now(), 100, now(), 100, 'cash.refunded', 'Cash refunded', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52088, 0, 0, 'Y', now(), 100, now(), 100, 'cash.tendered', 'Cash tendered', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52089, 0, 0, 'Y', now(), 100, now(), 100, 'cash.to.transfer', 'Cash to transfer', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52090, 0, 0, 'Y', now(), 100, now(), 100, 'cash.total', 'Cash Total', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52091, 0, 0, 'Y', now(), 100, now(), 100, 'cellphone', 'Cellphone', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52092, 0, 0, 'Y', now(), 100, now(), 100, 'checkout', 'Checkout', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52093, 0, 0, 'Y', now(), 100, now(), 100, 'cheque', 'Cheque', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52094, 0, 0, 'Y', now(), 100, now(), 100, 'cheque.amount', 'Cheque Amount', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52095, 0, 0, 'Y', now(), 100, now(), 100, 'cheque.amt.entered', 'Cheque Amount Entered', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52096, 0, 0, 'Y', now(), 100, now(), 100, 'cheque.amt.tendered', 'Cheque amount tendered', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52097, 0, 0, 'Y', now(), 100, now(), 100, 'cheque.amt.total', 'Cheque Amount Total', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52098, 0, 0, 'Y', now(), 100, now(), 100, 'cheque.no', 'Cheque No', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52099, 0, 0, 'Y', now(), 100, now(), 100, 'cheque.total', 'Cheque Total', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52100, 0, 0, 'Y', now(), 100, now(), 100, 'choose.attribute', 'Choose Attribute', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52101, 0, 0, 'Y', now(), 100, now(), 100, 'choose.your.till', 'Please choose your till number', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52102, 0, 0, 'Y', now(), 100, now(), 100, 'clear', 'Clear', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52103, 0, 0, 'Y', now(), 100, now(), 100, 'close', 'Close', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52104, 0, 0, 'Y', now(), 100, now(), 100, 'close.till', 'Close Till', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52105, 0, 0, 'Y', now(), 100, now(), 100, 'closed', 'Closed', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52106, 0, 0, 'Y', now(), 100, now(), 100, 'closing.balance', 'Closing Balance', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52107, 0, 0, 'Y', now(), 100, now(), 100, 'cogs', 'CoGS', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52108, 0, 0, 'Y', now(), 100, now(), 100, 'colour', 'Colour', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52109, 0, 0, 'Y', now(), 100, now(), 100, 'commission.details', 'Commission Details', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52110, 0, 0, 'Y', now(), 100, now(), 100, 'completed', 'Completed', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52111, 0, 0, 'Y', now(), 100, now(), 100, 'continue', 'Continue', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52112, 0, 0, 'Y', now(), 100, now(), 100, 'create.bpartner', 'Create Business Partner', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52113, 0, 0, 'Y', now(), 100, now(), 100, 'create.garment', 'Create Garment', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52114, 0, 0, 'Y', now(), 100, now(), 100, 'create.payment', 'Create Payment', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52115, 0, 0, 'Y', now(), 100, now(), 100, 'credit.check', 'Credit Check', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52116, 0, 0, 'Y', now(), 100, now(), 100, 'credit.details', 'Credit Details', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52117, 0, 0, 'Y', now(), 100, now(), 100, 'credit.hold', 'Credit Hold', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52118, 0, 0, 'Y', now(), 100, now(), 100, 'credit.memo', 'Credit Memo', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52119, 0, 0, 'Y', now(), 100, now(), 100, 'credit.ok', 'Credit OK', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52120, 0, 0, 'Y', now(), 100, now(), 100, 'credit.order', 'Credit Order', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52121, 0, 0, 'Y', now(), 100, now(), 100, 'credit.order.discount', 'Credit Order Discount', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52122, 0, 0, 'Y', now(), 100, now(), 100, 'credit.stop', 'Credit Stop', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52123, 0, 0, 'Y', now(), 100, now(), 100, 'credit.watch', 'Credit Watch', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52124, 0, 0, 'Y', now(), 100, now(), 100, 'current.month', 'Current Month', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52125, 0, 0, 'Y', now(), 100, now(), 100, 'current.till.amount', 'Current Till Amount', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52126, 0, 0, 'Y', now(), 100, now(), 100, 'current.week', 'Current Week', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52127, 0, 0, 'Y', now(), 100, now(), 100, 'current.year', 'Current Year', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52128, 0, 0, 'Y', now(), 100, now(), 100, 'custom', 'Custom', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52129, 0, 0, 'Y', now(), 100, now(), 100, 'custom.sales.reports', 'Custom Sales Report', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52130, 0, 0, 'Y', now(), 100, now(), 100, 'customer', 'Customer', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52131, 0, 0, 'Y', now(), 100, now(), 100, 'customer.id', 'Customer ID', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52132, 0, 0, 'Y', now(), 100, now(), 100, 'customer.info', 'Customer Info', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52133, 0, 0, 'Y', now(), 100, now(), 100, 'customer.returned.order', 'Customer Returned Order', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52134, 0, 0, 'Y', now(), 100, now(), 100, 'customervendor', 'Customer/Vendor', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52135, 0, 0, 'Y', now(), 100, now(), 100, 'date.created', 'Date Created', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52136, 0, 0, 'Y', now(), 100, now(), 100, 'date.from', 'From', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52137, 0, 0, 'Y', now(), 100, now(), 100, 'date.ordered', 'Date Ordered', 'Date Ordered', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52138, 0, 0, 'Y', now(), 100, now(), 100, 'date.range', 'Date Range', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52139, 0, 0, 'Y', now(), 100, now(), 100, 'date.to', 'to', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52140, 0, 0, 'Y', now(), 100, now(), 100, 'deactivate', 'De-activate', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52141, 0, 0, 'Y', now(), 100, now(), 100, 'deactivate.vendor', 'Deactivate Vendor', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52142, 0, 0, 'Y', now(), 100, now(), 100, 'dealer.name', 'Dealer Name', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52143, 0, 0, 'Y', now(), 100, now(), 100, 'default', 'Default', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52144, 0, 0, 'Y', now(), 100, now(), 100, 'delete', 'Delete', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52145, 0, 0, 'Y', now(), 100, now(), 100, 'delete.selected', 'Delete Selected', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52146, 0, 0, 'Y', now(), 100, now(), 100, 'design', 'Design', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52147, 0, 0, 'Y', now(), 100, now(), 100, 'discount.amt', 'Discount', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52148, 0, 0, 'Y', now(), 100, now(), 100, 'discounted.price', 'Discounted Price', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52149, 0, 0, 'Y', now(), 100, now(), 100, 'display', 'Display', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52150, 0, 0, 'Y', now(), 100, now(), 100, 'display.all.records', 'Do you want to display all records?', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52151, 0, 0, 'Y', now(), 100, now(), 100, 'doc.basis.type', 'Doc Basis Type', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52152, 0, 0, 'Y', now(), 100, now(), 100, 'download.csv', 'Download CSV', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52153, 0, 0, 'Y', now(), 100, now(), 100, 'drafted', 'Drafted', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52154, 0, 0, 'Y', now(), 100, now(), 100, 'dunning.letters.printed.successfully', 'Dunning letters have been printed successfully', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52155, 0, 0, 'Y', now(), 100, now(), 100, 'edit', 'Edit', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52156, 0, 0, 'Y', now(), 100, now(), 100, 'edit.attribute', 'Edit Attribute', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52157, 0, 0, 'Y', now(), 100, now(), 100, 'edit.black.listed.cheque', 'Edit Black Listed Cheque', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52158, 0, 0, 'Y', now(), 100, now(), 100, 'edit.customer', 'Edit Customer', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52159, 0, 0, 'Y', now(), 100, now(), 100, 'edit.price', 'Edit Price', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52160, 0, 0, 'Y', now(), 100, now(), 100, 'edit.price.list', 'Edit Price List', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52161, 0, 0, 'Y', now(), 100, now(), 100, 'edit.product', 'Edit Product', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52162, 0, 0, 'Y', now(), 100, now(), 100, 'edit.related.products', 'Edit related products details', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52163, 0, 0, 'Y', now(), 100, now(), 100, 'edit.role', 'Edit Role', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52164, 0, 0, 'Y', now(), 100, now(), 100, 'edit.user', 'Edit User', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52165, 0, 0, 'Y', now(), 100, now(), 100, 'edit.vendor', 'Edit Vendor', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52166, 0, 0, 'Y', now(), 100, now(), 100, 'enable.printing', 'Enable printing', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52167, 0, 0, 'Y', now(), 100, now(), 100, 'enter', 'ENTER', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52168, 0, 0, 'Y', now(), 100, now(), 100, 'excl.vat', '(excl. VAT)', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52169, 0, 0, 'Y', now(), 100, now(), 100, 'fast.moving.item', 'Fast Moving Items Report', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52170, 0, 0, 'Y', now(), 100, now(), 100, 'fast.moving.item.current.month', 'Fast Moving Items Report (Current Month)', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52171, 0, 0, 'Y', now(), 100, now(), 100, 'fast.moving.item.today', 'Fast Moving Items Report (Today)', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52172, 0, 0, 'Y', now(), 100, now(), 100, 'filter.by', 'Filter By', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52173, 0, 0, 'Y', now(), 100, now(), 100, 'filter.type', 'Choose the type of filter', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52174, 0, 0, 'Y', now(), 100, now(), 100, 'first', 'First', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52175, 0, 0, 'Y', now(), 100, now(), 100, 'fixed', 'Fixed', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52176, 0, 0, 'Y', now(), 100, now(), 100, 'float.amt.change', 'This is the float amount for today. Please Enter float amount for the next day if needs to be changed.', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52177, 0, 0, 'Y', now(), 100, now(), 100, 'footer.copyright', 'All Contents ©', '2006 Tamak ICT', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52178, 0, 0, 'Y', now(), 100, now(), 100, 'found.none', 'Found None', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52179, 0, 0, 'Y', now(), 100, now(), 100, 'from', 'From', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52180, 0, 0, 'Y', now(), 100, now(), 100, 'garment.template', 'Garment Template', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52181, 0, 0, 'Y', now(), 100, now(), 100, 'generate.commission', 'Generate Commission', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52182, 0, 0, 'Y', now(), 100, now(), 100, 'goods.received.note', 'Goods Received Note', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52183, 0, 0, 'Y', now(), 100, now(), 100, 'goods.returned.note', 'Goods Returned Note', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52184, 0, 0, 'Y', now(), 100, now(), 100, 'hide.details', 'Hide Details', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52185, 0, 0, 'Y', now(), 100, now(), 100, 'import.black.listed', 'Import Black Listed Cheques', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52186, 0, 0, 'Y', now(), 100, now(), 100, 'import.blacklisted.message1', 'This utility is to import the a list of Black Listed Cheques from a csv file into the system,<br>The csv file should look like the one shown below including the header:', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52187, 0, 0, 'Y', now(), 100, now(), 100, 'import.list', 'Import List', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52188, 0, 0, 'Y', now(), 100, now(), 100, 'import.product.message', 'This utility is to import the products and Stock in Hand from a csv file into the system,<br> For importing the garment products, the CSV file name should containg the word ''Garment'' <br>The csv file should look like the one shown below including the header', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52189, 0, 0, 'Y', now(), 100, now(), 100, 'incl.vat', '(incl. VAT)', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52190, 0, 0, 'Y', now(), 100, now(), 100, 'inprogress', 'InProgress', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52191, 0, 0, 'Y', now(), 100, now(), 100, 'invalid', 'Invalid', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52192, 0, 0, 'Y', now(), 100, now(), 100, 'invoice.no', 'Invoice No', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52193, 0, 0, 'Y', now(), 100, now(), 100, 'invoke', 'Invoke', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52194, 0, 0, 'Y', now(), 100, now(), 100, 'invoke.customer.returned.order', 'Invoke', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52195, 0, 0, 'Y', now(), 100, now(), 100, 'invoke.partial', 'Invoke Partial POS Order', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52196, 0, 0, 'Y', now(), 100, now(), 100, 'items', 'Items', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52197, 0, 0, 'Y', now(), 100, now(), 100, 'last', 'Last', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52198, 0, 0, 'Y', now(), 100, now(), 100, 'last.2.months', 'Last 2 Months', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52199, 0, 0, 'Y', now(), 100, now(), 100, 'last.2.weeks', 'Last 2 Weeks', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52200, 0, 0, 'Y', now(), 100, now(), 100, 'last.3.months', 'Last 3 Months', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52201, 0, 0, 'Y', now(), 100, now(), 100, 'last.3.weeks', 'Last 3 Weeks', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52202, 0, 0, 'Y', now(), 100, now(), 100, 'last.6.month', 'Last 6 Months', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52203, 0, 0, 'Y', now(), 100, now(), 100, 'license.name', 'License Name', 'License Name', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52204, 0, 0, 'Y', now(), 100, now(), 100, 'license.valid', 'License Valid', 'License Valid', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52205, 0, 0, 'Y', now(), 100, now(), 100, 'licensed.distribution', 'Licensed Distribution', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52206, 0, 0, 'Y', now(), 100, now(), 100, 'licensed.module', 'Licensed Modules', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52207, 0, 0, 'Y', now(), 100, now(), 100, 'licensing.info', 'Licensing Information', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52208, 0, 0, 'Y', now(), 100, now(), 100, 'list', 'List', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52209, 0, 0, 'Y', now(), 100, now(), 100, 'login.home.all.contents', 'All content', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52210, 0, 0, 'Y', now(), 100, now(), 100, 'login.home.loginForgot', 'Forgot password?', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52211, 0, 0, 'Y', now(), 100, now(), 100, 'login.home.message1', 'Please enter your username and password', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52212, 0, 0, 'Y', now(), 100, now(), 100, 'login.home.message2', 'Please enter PIN', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52213, 0, 0, 'Y', now(), 100, now(), 100, 'login.home.password', 'Password', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52214, 0, 0, 'Y', now(), 100, now(), 100, 'login.home.username', 'Username', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52215, 0, 0, 'Y', now(), 100, now(), 100, 'login.password.backToLogin', ' ', ' Click Here to go back to the Login Screen', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52216, 0, 0, 'Y', now(), 100, now(), 100, 'login.password.passwordSent', 'Your password has been sent you should receive it in a few minutes', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52217, 0, 0, 'Y', now(), 100, now(), 100, 'marked.price', 'Marked Price', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52218, 0, 0, 'Y', now(), 100, now(), 100, 'max.active.users', 'Max Active Users', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52219, 0, 0, 'Y', now(), 100, now(), 100, 'max.sold.item', 'Max sold Item', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52220, 0, 0, 'Y', now(), 100, now(), 100, 'menus', 'Menus', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52221, 0, 0, 'Y', now(), 100, now(), 100, 'min.item.sold', 'Min Item Sold', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52222, 0, 0, 'Y', now(), 100, now(), 100, 'mixed', 'Mixed', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52223, 0, 0, 'Y', now(), 100, now(), 100, 'mobile', 'Moblie No', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52224, 0, 0, 'Y', now(), 100, now(), 100, 'model', 'Model', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52225, 0, 0, 'Y', now(), 100, now(), 100, 'month', 'Month', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52226, 0, 0, 'Y', now(), 100, now(), 100, 'net.amt', 'Net Amount', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52227, 0, 0, 'Y', now(), 100, now(), 100, 'net.cash.trx', 'Net Cash Trx', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52228, 0, 0, 'Y', now(), 100, now(), 100, 'net.transaction', 'Net Transaction', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52229, 0, 0, 'Y', now(), 100, now(), 100, 'new.attribute.value', 'New Attribute Value', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52230, 0, 0, 'Y', now(), 100, now(), 100, 'new.credit.order', 'New Credit Order', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52231, 0, 0, 'Y', now(), 100, now(), 100, 'new.customer', 'New Customer', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52232, 0, 0, 'Y', now(), 100, now(), 100, 'new.customer.return.order', 'New Customer Return Order', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52233, 0, 0, 'Y', now(), 100, now(), 100, 'new.goods.received.note', 'New Goods Receive Note', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52234, 0, 0, 'Y', now(), 100, now(), 100, 'new.goods.returned.note', 'New Goods Returned Note', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52235, 0, 0, 'Y', now(), 100, now(), 100, 'new.order', 'New Order', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52236, 0, 0, 'Y', now(), 100, now(), 100, 'new.price', 'New Price', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52237, 0, 0, 'Y', now(), 100, now(), 100, 'no', 'No', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52238, 0, 0, 'Y', now(), 100, now(), 100, 'no.customer.was.found.for', 'No customer was found for', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52239, 0, 0, 'Y', now(), 100, now(), 100, 'no.shipment', 'No Shipment', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52240, 0, 0, 'Y', now(), 100, now(), 100, 'no.vendor.was.found.for', 'No vendor was found for', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52241, 0, 0, 'Y', now(), 100, now(), 100, 'normal.template', 'Normal Template', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52242, 0, 0, 'Y', now(), 100, now(), 100, 'not.authorised.to.give.discount', 'It seems that you are not authorised to give discounts', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52243, 0, 0, 'Y', now(), 100, now(), 100, 'not.found', 'Not Found', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52244, 0, 0, 'Y', now(), 100, now(), 100, 'notes', 'Notes', 'Notes', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52245, 0, 0, 'Y', now(), 100, now(), 100, 'num.active.users', 'No of Active Users', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52246, 0, 0, 'Y', now(), 100, now(), 100, 'num.operations', 'Number of Operations', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52247, 0, 0, 'Y', now(), 100, now(), 100, 'old.attribute.value', 'Old Attribute Value', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52248, 0, 0, 'Y', now(), 100, now(), 100, 'open.invoices', 'Open Invoices', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52249, 0, 0, 'Y', now(), 100, now(), 100, 'opening.balance', 'Opening Balance', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52250, 0, 0, 'Y', now(), 100, now(), 100, 'order.no', 'Order No', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52251, 0, 0, 'Y', now(), 100, now(), 100, 'order.type', 'Order Type', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52252, 0, 0, 'Y', now(), 100, now(), 100, 'organisation.name', 'Organisations', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52253, 0, 0, 'Y', now(), 100, now(), 100, 'partial.pos.order', 'Partial POS Order', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52254, 0, 0, 'Y', now(), 100, now(), 100, 'partial.pos.order.history', 'Partial POS Order History', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52255, 0, 0, 'Y', now(), 100, now(), 100, 'payment.allocation', 'Payment Allocation', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52256, 0, 0, 'Y', now(), 100, now(), 100, 'payment.by', 'Payment By', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52257, 0, 0, 'Y', now(), 100, now(), 100, 'payment.details', 'Payment Details', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52258, 0, 0, 'Y', now(), 100, now(), 100, 'payment.num', 'Payment No', 'Payment No', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52259, 0, 0, 'Y', now(), 100, now(), 100, 'payment.status', 'Payment Status', 'Payment Status', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52260, 0, 0, 'Y', now(), 100, now(), 100, 'payment.terms', 'Payment Terms', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52261, 0, 0, 'Y', now(), 100, now(), 100, 'payment.type', 'Payment Type', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52262, 0, 0, 'Y', now(), 100, now(), 100, 'periodic.cash.details', 'Periodic Cash Book Details', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52263, 0, 0, 'Y', now(), 100, now(), 100, 'please.enter.pin', 'Please enter PIN', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52264, 0, 0, 'Y', now(), 100, now(), 100, 'pmenu.administration', 'Administration', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52265, 0, 0, 'Y', now(), 100, now(), 100, 'pmenu.cash.sales', 'Cash Sales', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52266, 0, 0, 'Y', now(), 100, now(), 100, 'pmenu.credit.sales', 'Credit Sales', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52267, 0, 0, 'Y', now(), 100, now(), 100, 'pmenu.performance.analysis', 'Performance Analysis', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52268, 0, 0, 'Y', now(), 100, now(), 100, 'pmenu.purchases', 'Purchases', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52269, 0, 0, 'Y', now(), 100, now(), 100, 'pmenu.stock', 'Stock', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52270, 0, 0, 'Y', now(), 100, now(), 100, 'pos.info', 'POS Info', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52271, 0, 0, 'Y', now(), 100, now(), 100, 'pos.info.current.month', 'POS Info  (Current Month)', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52272, 0, 0, 'Y', now(), 100, now(), 100, 'pos.info.custom', 'POS Info  (Custom)', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52273, 0, 0, 'Y', now(), 100, now(), 100, 'pos.info.today', 'POS Info (Today)', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52274, 0, 0, 'Y', now(), 100, now(), 100, 'pos.name', 'POS Name', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52275, 0, 0, 'Y', now(), 100, now(), 100, 'pos.order', 'POS Order', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52276, 0, 0, 'Y', now(), 100, now(), 100, 'pos.order.history', 'POS Order History', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52277, 0, 0, 'Y', now(), 100, now(), 100, 'posPassowrd.welcome', 'Welcome to Posterita POS', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52278, 0, 0, 'Y', now(), 100, now(), 100, 'postalAddr', 'Postal Address', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52279, 0, 0, 'Y', now(), 100, now(), 100, 'preference.themes', 'Themes', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52280, 0, 0, 'Y', now(), 100, now(), 100, 'preferences', 'Preferences', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52281, 0, 0, 'Y', now(), 100, now(), 100, 'prepared.order', 'Prepared Order', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52282, 0, 0, 'Y', now(), 100, now(), 100, 'prev', 'Prev', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52283, 0, 0, 'Y', now(), 100, now(), 100, 'print.barcode', 'Print Barcode', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52284, 0, 0, 'Y', now(), 100, now(), 100, 'print.dunning.letters', 'Print Dunning Letters', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52285, 0, 0, 'Y', now(), 100, now(), 100, 'print.fidelity.card', 'Print Fidelity Card', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52286, 0, 0, 'Y', now(), 100, now(), 100, 'print.order', 'Print Order', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52287, 0, 0, 'Y', now(), 100, now(), 100, 'print.pdf', 'Print PDF', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52288, 0, 0, 'Y', now(), 100, now(), 100, 'printer.enabled', 'Printer enabled', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52289, 0, 0, 'Y', now(), 100, now(), 100, 'printer.name', 'Printer name', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52290, 0, 0, 'Y', now(), 100, now(), 100, 'product.already.exists', 'Product name already exists!', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52291, 0, 0, 'Y', now(), 100, now(), 100, 'product.id', 'Product ID', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52292, 0, 0, 'Y', now(), 100, now(), 100, 'product.info', 'Product Info', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52293, 0, 0, 'Y', now(), 100, now(), 100, 'product.type.item', 'Item', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52294, 0, 0, 'Y', now(), 100, now(), 100, 'product.type.services', 'Services', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52295, 0, 0, 'Y', now(), 100, now(), 100, 'product.updated', 'The Products have been updated', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52296, 0, 0, 'Y', now(), 100, now(), 100, 'profit.margin', 'Profit Margin', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52297, 0, 0, 'Y', now(), 100, now(), 100, 'purchase.price', 'Purchase Price', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52298, 0, 0, 'Y', now(), 100, now(), 100, 'qty.received', 'Qty Received', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52299, 0, 0, 'Y', now(), 100, now(), 100, 'qty.returned.by.customer', 'Qty Returned By Customer', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52300, 0, 0, 'Y', now(), 100, now(), 100, 'qty.returned.to.supplier', 'Qty Returned to Supplier', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52301, 0, 0, 'Y', now(), 100, now(), 100, 'qty.sold', 'Qty Sold', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52302, 0, 0, 'Y', now(), 100, now(), 100, 'reason.message', 'Reason', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52303, 0, 0, 'Y', now(), 100, now(), 100, 'record.found', 'Records found', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52304, 0, 0, 'Y', now(), 100, now(), 100, 'records.per.page', 'records per page', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52305, 0, 0, 'Y', now(), 100, now(), 100, 'remote.printing', 'Remote printing', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52306, 0, 0, 'Y', now(), 100, now(), 100, 'remove.customer.fidelity.card', 'Remove Customer for fidelity card', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52307, 0, 0, 'Y', now(), 100, now(), 100, 'report.filter.settings', 'Report Filter Settings', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52308, 0, 0, 'Y', now(), 100, now(), 100, 'reprint', 'Reprint', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52309, 0, 0, 'Y', now(), 100, now(), 100, 'returned.order', 'Returned Order', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52310, 0, 0, 'Y', now(), 100, now(), 100, 'revenue', 'Revenue', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52311, 0, 0, 'Y', now(), 100, now(), 100, 'sales.details', 'Sales Details', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52312, 0, 0, 'Y', now(), 100, now(), 100, 'sales.order', 'Sales Order', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52313, 0, 0, 'Y', now(), 100, now(), 100, 'sales.price', 'Sales Price', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52314, 0, 0, 'Y', now(), 100, now(), 100, 'sales.reports', 'Sales Reports', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52315, 0, 0, 'Y', now(), 100, now(), 100, 'save', 'Save', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52316, 0, 0, 'Y', now(), 100, now(), 100, 'save.as.csv', 'Save as CSV', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52317, 0, 0, 'Y', now(), 100, now(), 100, 'search', 'Search', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52318, 0, 0, 'Y', now(), 100, now(), 100, 'search.customer', 'Search Customer', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52319, 0, 0, 'Y', now(), 100, now(), 100, 'search.customer.notfound', 'No customers were found for', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52320, 0, 0, 'Y', now(), 100, now(), 100, 'search.product', 'Search Product', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52321, 0, 0, 'Y', now(), 100, now(), 100, 'search.product.notfound', 'No products were found for:', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52322, 0, 0, 'Y', now(), 100, now(), 100, 'search.result.displaying', 'Displaying', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52323, 0, 0, 'Y', now(), 100, now(), 100, 'search.result.of', 'of', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52324, 0, 0, 'Y', now(), 100, now(), 100, 'search.result.to', 'to', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52325, 0, 0, 'Y', now(), 100, now(), 100, 'search.results', 'Search Results', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52326, 0, 0, 'Y', now(), 100, now(), 100, 'select', 'Select', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52327, 0, 0, 'Y', now(), 100, now(), 100, 'select.all', 'Select All', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52328, 0, 0, 'Y', now(), 100, now(), 100, 'select.bpartner.type', 'Select partner type', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52329, 0, 0, 'Y', now(), 100, now(), 100, 'select.range', 'Select Range', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52330, 0, 0, 'Y', now(), 100, now(), 100, 'selected.customers', 'Selected Customers', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52331, 0, 0, 'Y', now(), 100, now(), 100, 'settle.payment', 'Settle Payment', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52332, 0, 0, 'Y', now(), 100, now(), 100, 'settle.payment.message', 'NOTE:-The Cash Payments would have effect only when the cash book is closed', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52333, 0, 0, 'Y', now(), 100, now(), 100, 'shipment.no', 'Shipment No', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52334, 0, 0, 'Y', now(), 100, now(), 100, 'shipment.required', 'Shipment Required', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52335, 0, 0, 'Y', now(), 100, now(), 100, 'shipment.status', 'Shipment Status', 'Shipment Status', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52336, 0, 0, 'Y', now(), 100, now(), 100, 'show.details', 'Show Details', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52337, 0, 0, 'Y', now(), 100, now(), 100, 'size', 'Size', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52338, 0, 0, 'Y', now(), 100, now(), 100, 'slow.moving.item', 'Slow Moving Items', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52339, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.adjust.cashbook', 'Adjust Cash Book', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52340, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.adjust.inventory.id', 'Adjust Inventory', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52341, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.adjust.stock.id', 'Stock Adjustment', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52342, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.bpartner.sales.details', 'Business Partner Sales Details', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52343, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.bpartners', 'Business Partners', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52344, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.cash.sales', 'Cash Sales', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52345, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.cash.sales.customer.complusory', 'Cash Sales (Customer Compulsory)', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52346, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.cash.sales.history', 'Cash Sales History', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52347, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.cash.sales.multiple.payments', 'Cash Sales (Discount/Multiple Payments)', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52348, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.cashbook.history', 'Cash Book History', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52349, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.cashbook.report', 'Cash Book Report', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52350, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.check.repair.database.integrity', 'Check/Repair Database Integrity', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52351, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.close.till', 'Close Till', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52352, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.complete.prepared.order', 'Complete Prepared Order', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52353, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.create.unallocated.payment.id', 'Create General Payments', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52354, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.credit.memo.history.id', 'Credit Memo History', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52355, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.credit.sales', 'Credit Sales', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52356, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.credit.sales.history', 'Credit Sales History', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52357, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.creditmemo.from.creditorder.id', 'Invoke Credit Memo', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52358, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.current.money.in.terminal', 'Current Money in Terminal', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52359, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.customer.return.history.id', 'Customer Return History', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52360, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.customer.returned.order', 'Customer Returned Order', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52361, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.customers', 'Customers', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52362, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.document.history', 'Document History', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52363, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.dunning.letters', 'Dunning Letters', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52364, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.edit.product.attribute.value', 'Edit Product Attribute Value', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52365, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.fast.moving.items', 'Fast Moving Items', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52366, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.generate.commission', 'Generate Commission', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52367, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.goods.received.note', 'Goods Received Note', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52368, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.goods.received.note.history', 'Goods Received Note History', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52369, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.goods.returned.note', 'Goods Returned Note', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52370, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.goods.returned.note.history', 'Goods Returned Note History', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52371, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.inventory.history.id', 'Inventory History', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52372, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.invoke.customer.returned.order', 'Invoke Customer Returned Order', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52373, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.logout', 'Logout', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52374, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.open.cashdrawer', 'Open Cash Drawer', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52375, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.order.history', 'Order History', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52376, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.payment.allocation.history', 'Payment Allocations History', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52377, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.payment.term', 'Payment Term', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52378, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.performance.analysis.report', 'Performance Analysis Report', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52379, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.preferences', 'Preferences', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52380, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.prepare.order', 'Prepare Order', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52381, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.prepared.order.history', 'Prepared Order History', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52382, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.products', 'Products', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52383, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.quick.cash.sales', 'Quick Cash Sales', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52384, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.role', 'Role', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52385, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.sales.report.per.terminal', 'Sales Report per Terminal', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52386, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.settle.payment.credit.sales', 'Settle Payment on a Credit Sales', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(523847, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.slow.moving.items', 'Slow Moving Items', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52388, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.stock', 'Stock', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52389, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.stock.movement', 'Stock Movement', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52390, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.tax', 'Tax', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52391, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.users', 'Users', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52392, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.vendors', 'Vendors', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52393, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.view.last.generated.commission', 'Last Generated Commission', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52394, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.view.licensing', 'Licensing', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52395, 0, 0, 'Y', now(), 100, now(), 100, 'status', 'Status', 'Status', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52396, 0, 0, 'Y', now(), 100, now(), 100, 'stock.in.hand', 'Stock in Hand', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52397, 0, 0, 'Y', now(), 100, now(), 100, 'stock.inquiry', 'Stock Inquiry', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52398, 0, 0, 'Y', now(), 100, now(), 100, 'stock.movement', 'Stock Movement', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52399, 0, 0, 'Y', now(), 100, now(), 100, 'stock.movement.report', 'Stock Movement Report', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52400, 0, 0, 'Y', now(), 100, now(), 100, 'submit', 'Submit', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52401, 0, 0, 'Y', now(), 100, now(), 100, 'summary.by.periods', 'Summary By Period', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52402, 0, 0, 'Y', now(), 100, now(), 100, 'switch.to.paging', 'Switch to Paging', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52403, 0, 0, 'Y', now(), 100, now(), 100, 'tamak.pos', 'Posterita POS', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52404, 0, 0, 'Y', now(), 100, now(), 100, 'tax.credit', 'Tax Credit', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52405, 0, 0, 'Y', now(), 100, now(), 100, 'tax.due', 'Tax Due', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52406, 0, 0, 'Y', now(), 100, now(), 100, 'texttile.products.only', 'Textile Products Only', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52407, 0, 0, 'Y', now(), 100, now(), 100, 'the.cart.has', 'The Cart has', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52408, 0, 0, 'Y', now(), 100, now(), 100, 'till.balance.entered', 'Till Balance Entered', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52409, 0, 0, 'Y', now(), 100, now(), 100, 'till.management', 'Till Mgt', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52410, 0, 0, 'Y', now(), 100, now(), 100, 'till.no', 'Till No', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52411, 0, 0, 'Y', now(), 100, now(), 100, 'time.hour', 'h', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52412, 0, 0, 'Y', now(), 100, now(), 100, 'time.minute', 'm', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52413, 0, 0, 'Y', now(), 100, now(), 100, 'to', 'To', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52414, 0, 0, 'Y', now(), 100, now(), 100, 'today', 'Today', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52415, 0, 0, 'Y', now(), 100, now(), 100, 'total', 'Total', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52416, 0, 0, 'Y', now(), 100, now(), 100, 'total.price', 'Total Price', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52417, 0, 0, 'Y', now(), 100, now(), 100, 'trade.revenue', 'Trade Revenue', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52418, 0, 0, 'Y', now(), 100, now(), 100, 'unallocated.payments', 'Unallocated Payments', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52419, 0, 0, 'Y', now(), 100, now(), 100, 'update.details', 'Update Details', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52420, 0, 0, 'Y', now(), 100, now(), 100, 'user', 'User', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52421, 0, 0, 'Y', now(), 100, now(), 100, 'user.details', 'User''s Details', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52422, 0, 0, 'Y', now(), 100, now(), 100, 'user.info', 'Information', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52423, 0, 0, 'Y', now(), 100, now(), 100, 'vat', 'VAT', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52424, 0, 0, 'Y', now(), 100, now(), 100, 'vendor.details', 'Vendor Details', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52425, 0, 0, 'Y', now(), 100, now(), 100, 'vendor.ref', 'Vendor Ref', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52426, 0, 0, 'Y', now(), 100, now(), 100, 'vendors', 'Vendors', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52427, 0, 0, 'Y', now(), 100, now(), 100, 'view', 'View', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52428, 0, 0, 'Y', now(), 100, now(), 100, 'view.attributes', 'View Attributes', 'View Attributes', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52429, 0, 0, 'Y', now(), 100, now(), 100, 'view.by', 'View By', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52430, 0, 0, 'Y', now(), 100, now(), 100, 'view.info', 'View Info', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52431, 0, 0, 'Y', now(), 100, now(), 100, 'view.product.cart', 'View Product Cart', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52432, 0, 0, 'Y', now(), 100, now(), 100, 'view.report', 'View Report', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52433, 0, 0, 'Y', now(), 100, now(), 100, 'view.role', 'View Role', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52434, 0, 0, 'Y', now(), 100, now(), 100, 'view.vendor', 'View Vendor', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52435, 0, 0, 'Y', now(), 100, now(), 100, 'year', 'Year', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52436, 0, 0, 'Y', now(), 100, now(), 100, 'yes', 'Yes', ' ', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52437, 0, 0, 'Y', now(), 100, now(), 100, 'price.x', 'Price(excl. VAT)', 'Prix(excl. TVA)', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52438, 0, 0, 'Y', now(), 100, now(), 100, 'LogOut', 'Log Out', 'Sortir', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52439, 0, 0, 'Y', now(), 100, now(), 100, 'smenu.price.check', 'Price Check', 'Verifier Prix', 'I', 'D');
INSERT INTO AD_MESSAGE(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
  VALUES(52440, 0, 0, 'Y', now(), 100, now(), 100, 'next', 'Next', 'Suivant', 'I', 'D');

