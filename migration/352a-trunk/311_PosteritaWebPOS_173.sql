-- 1.0-1.5/oracle/001_ad_changes.sql
-----------------------------
INSERT INTO ad_element
(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, 
columnname, entitytype, name, printname, description, help, po_name, po_printname, po_description, po_help)
VALUES(52027, 0, 0, 'Y', TO_DATE('2008-03-28 16:00:00','YYYY-MM-DD HH24:MI:SS'), 100, TO_DATE('2008-03-28 16:00:00','YYYY-MM-DD HH24:MI:SS'), 100, 'isPresentForProduct', 
'U', 'isPresentForProduct', 'Present for Product', NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO ad_column
(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, name,
 description, help, version, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id,
 ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic,
isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, 
ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql, mandatorylogic)
  VALUES( 52071, 0, 0, 'Y', TO_DATE('2008-03-28 16:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2008-03-28 16:00:00','YYYY-MM-DD HH24:MI:SS'), 100, 100, 
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
0, 0, 'Y', TO_DATE('2008-03-28 16:00:00','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2008-03-28 16:00:00','YYYY-MM-DD HH24:MI:SS'), 100, 100, 'Mandatory', 'Data entry is required in this column', 
'The field must have a value for the record to be saved to the database.', 0, 'U', 'IsMandatory', 
255, 20, NULL, NULL, 1, NULL, 'N', 'N', 'N', 'Y', NULL, 'N', 0, 'N', 'N', NULL, NULL, NULL, NULL, 'N',
 392, NULL, 'N', 'N', NULL, NULL);


-- 1.0-1.5/oracle/002_ad_message.sql
-----------------------------
INSERT INTO AD_MESSAGE
(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, 
updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
VALUES(52441 , 0, 0, 'Y', TO_DATE('2008-03-28 16:00:00','YYYY-MM-DD HH24:MI:SS'), 100, TO_DATE('2008-03-28 16:00:00','YYYY-MM-DD HH24:MI:SS'), 100, 
'smenu.price.list', 'Price List', ' ', 'I', 'D');  

INSERT INTO AD_MESSAGE
(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, 
updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
VALUES(52442 , 0, 0, 'Y', TO_DATE('2008-03-28 16:00:00','YYYY-MM-DD HH24:MI:SS'), 100, TO_DATE('2008-03-28 16:00:00','YYYY-MM-DD HH24:MI:SS'), 100, 
'pmenu.help', 'Help', ' ', 'I', 'D');  

INSERT INTO AD_MESSAGE
(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, 
updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
VALUES(52443 , 0, 0, 'Y', TO_DATE('2008-03-28 16:00:00','YYYY-MM-DD HH24:MI:SS'), 100, TO_DATE('2008-03-28 16:00:00','YYYY-MM-DD HH24:MI:SS'), 100, 
'list.price', 'List Price', ' ', 'I', 'D');  

INSERT INTO AD_MESSAGE
(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, 
updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
VALUES(52444 , 0, 0, 'Y', TO_DATE('2008-03-28 16:00:00','YYYY-MM-DD HH24:MI:SS'), 100, TO_DATE('2008-03-28 16:00:00','YYYY-MM-DD HH24:MI:SS'), 100, 
'standard.price', 'Standard Price', ' ', 'I', 'D');  

INSERT INTO AD_MESSAGE
(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, 
updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
VALUES(52445 , 0, 0, 'Y', TO_DATE('2008-03-28 16:00:00','YYYY-MM-DD HH24:MI:SS'), 100, TO_DATE('2008-03-28 16:00:00','YYYY-MM-DD HH24:MI:SS'), 100, 
'limit.price', 'Limit Price', ' ', 'I', 'D');  

INSERT INTO AD_MESSAGE
(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, 
updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
VALUES(52446 , 0, 0, 'Y', TO_DATE('2008-03-28 16:00:00','YYYY-MM-DD HH24:MI:SS'), 100, TO_DATE('2008-03-28 16:00:00','YYYY-MM-DD HH24:MI:SS'), 100, 
'tax.incl', 'Tax Incl', ' ', 'I', 'D');  

INSERT INTO AD_MESSAGE
(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, 
updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
VALUES(52447 , 0, 0, 'Y', TO_DATE('2008-03-28 16:00:00','YYYY-MM-DD HH24:MI:SS'), 100, TO_DATE('2008-03-28 16:00:00','YYYY-MM-DD HH24:MI:SS'), 100, 
'tax.excl', 'Tax Excl', ' ', 'I', 'D');  

INSERT INTO AD_MESSAGE
(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, 
updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
VALUES(52448 , 0, 0, 'Y', TO_DATE('2008-03-28 16:00:00','YYYY-MM-DD HH24:MI:SS'), 100, TO_DATE('2008-03-28 16:00:00','YYYY-MM-DD HH24:MI:SS'), 100, 
'type.of.price.list', 'Type of Price List', ' ', 'I', 'D'); 

INSERT INTO AD_MESSAGE
(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, 
updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
VALUES(52449 , 0, 0, 'Y', TO_DATE('2008-03-28 16:00:00','YYYY-MM-DD HH24:MI:SS'), 100, TO_DATE('2008-03-28 16:00:00','YYYY-MM-DD HH24:MI:SS'), 100, 
'purchase', 'Purchase', ' ', 'I', 'D'); 

INSERT INTO AD_MESSAGE
(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, 
updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
VALUES(52450 , 0, 0, 'Y', TO_DATE('2008-03-28 16:00:00','YYYY-MM-DD HH24:MI:SS'), 100, TO_DATE('2008-03-28 16:00:00','YYYY-MM-DD HH24:MI:SS'), 100, 
'sales', 'Sales', ' ', 'I', 'D'); 

INSERT INTO AD_MESSAGE
(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, 
updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
VALUES(52451 , 0, 0, 'Y', TO_DATE('2008-03-28 16:00:00','YYYY-MM-DD HH24:MI:SS'), 100, TO_DATE('2008-03-28 16:00:00','YYYY-MM-DD HH24:MI:SS'), 100, 
'isPresentForProduct', 'Present for Product', ' ', 'I', 'D'); 


-- 1.0-1.5/oracle/003_m_pricelist.sql
-----------------------------
ALTER TABLE m_pricelist  ADD ismandatory char(1) DEFAULT 'N';

ALTER TABLE m_pricelist  ADD ispresentforproduct char(1) DEFAULT 'N';


-- 1.6/oracle/001_ad_Changes for C_Currency.sql
-----------------------------
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


-- 1.6/oracle/002_ad_message.sql
-----------------------------
INSERT INTO AD_MESSAGE
(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, 
updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
VALUES(52467 , 0, 0, 'Y', TO_TIMESTAMP('2008-04-25 14:30:00','YYYY-MM-DD HH24:MI:SS'), 100, TO_TIMESTAMP('2008-04-25 14:30:00','YYYY-MM-DD HH24:MI:SS'), 100, 
'smenu.currency', 'Currency', ' ', 'I', 'D'); 

INSERT INTO AD_MESSAGE
(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, 
updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
VALUES(52468 , 0, 0, 'Y', TO_TIMESTAMP('2008-04-25 14:30:00','YYYY-MM-DD HH24:MI:SS'), 100, TO_TIMESTAMP('2008-04-25 14:30:00','YYYY-MM-DD HH24:MI:SS'), 100, 
'smenu.delete.price.on.pricelist', 'Delete Price On Price List', ' ', 'I', 'D'); 


-- 1.6/oracle/003_C_Currency.sql
-----------------------------
ALTER TABLE C_Currency ADD roundOffFactor NUMBER;


-- 1.7.0/oracle/001_MixedPayment.sql
-----------------------------
-- May 22, 2008 10:57:07 AM GMT+04:00
-- Create Mixed in the payment rules list
INSERT INTO AD_Ref_List (
AD_Client_ID,AD_Org_ID
,AD_Reference_ID,AD_Ref_List_ID
,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value
) VALUES (
0,0
,195,52000,TO_DATE('2008-05-22 10:55:30','YYYY-MM-DD HH24:MI:SS'),100,'D','Y'
,'Mixed',TO_DATE('2008-05-22 10:55:30','YYYY-MM-DD HH24:MI:SS'),100,'M')
;

-- May 22, 2008 10:57:11 AM GMT+04:00
-- Update the translation
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=52000 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- May 22, 2008 11:28:12 AM GMT+04:00
-- Creates new Validation rule so that rich client does not get Mixed rule
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID
,AD_Val_Rule_ID,Code
,Created,CreatedBy,EntityType,IsActive
,Name,Type,Updated,UpdatedBy
) VALUES (
0,0
,52033,'AD_Ref_List.Value <> ''M'''
,TO_DATE('2008-05-22 11:28:12','YYYY-MM-DD HH24:MI:SS'),100,'D','Y'
,'All_Payment Rule - No mixed','S',TO_DATE('2008-05-22 11:28:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- May 22, 2008 11:30:02 AM GMT+04:00
-- Updates all columns in AD where the payment rule list is used with the validation created earlier
UPDATE AD_Column SET AD_Val_Rule_ID=52033 WHERE AD_Reference_Value_ID=195 AND AD_Val_Rule_ID IS NULL
;

-- May 22, 2008 11:31:15 AM GMT+04:00
-- Updates all columns in AD where an existing validation rule exist and set it to ignore the Mixed rule
UPDATE AD_Val_Rule SET Code='AD_Ref_List.Value <> ''B'' AND AD_Ref_List.Value <> ''M''' WHERE AD_Val_Rule_ID=161
;


-- 1.7.0/oracle/002_Terminals_newTableStructure.sql
-----------------------------
-- Jun 2, 2008 9:19:48 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Table (AccessLevel,AD_Client_ID,AD_Org_ID,AD_Table_ID,CopyColumnsFromTable,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,LoadSeq,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES ('1',0,0,52004,'N',TO_DATE('2008-06-02 21:19:47','YYYY-MM-DD HH24:MI:SS'),100,'A','N','Y','N','Y','N','N','N',0,'POS Terminal','L','U_POSTerminal',TO_DATE('2008-06-02 21:19:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 2, 2008 9:19:48 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=52004 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Jun 2, 2008 9:21:23 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,52077,102,0,19,52004,129,'AD_Client_ID',TO_DATE('2008-06-02 21:21:22','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Client_ID@','Client/Tenant for this installation.','A',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','N','N','Y','Client',0,TO_DATE('2008-06-02 21:21:22','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 2, 2008 9:21:23 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52077 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 2, 2008 9:22:32 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,52078,113,0,19,52004,130,'AD_Org_ID',TO_DATE('2008-06-02 21:22:32','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Org_ID@','Organizational entity within client','A',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','N','N','Y','Organization',0,TO_DATE('2008-06-02 21:22:32','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 2, 2008 9:22:32 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52078 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;


-- Jun 2, 2008 9:23:51 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,52030,0,'AutoLock',TO_DATE('2008-06-02 21:23:51','YYYY-MM-DD HH24:MI:SS'),100,'Whether to automatically lock the terminal when till is closed','A','Y','Auto Lock','Auto Lock',TO_DATE('2008-06-02 21:23:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 2, 2008 9:23:51 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=52030 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jun 2, 2008 9:24:30 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,52079,52030,0,20,52004,'AutoLock',TO_DATE('2008-06-02 21:24:30','YYYY-MM-DD HH24:MI:SS'),100,'N','Whether to automatically lock the terminal when till is closed','A',1,'Y','N','N','N','N','Y','N','N','N','N','Y','Auto Lock',0,TO_DATE('2008-06-02 21:24:30','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 2, 2008 9:24:30 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52079 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 2, 2008 9:26:51 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,52031,0,'CashBookTransferType',TO_DATE('2008-06-02 21:26:51','YYYY-MM-DD HH24:MI:SS'),100,'Where the money in the cash book should be transfered to. Either a Bank Account or another Cash Book','A','Y','Cash Book Transfer Type','Cash Book Transfer Type',TO_DATE('2008-06-02 21:26:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 2, 2008 9:26:51 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=52031 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jun 2, 2008 9:29:14 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Reference (
AD_Client_ID,AD_Org_ID
,AD_Reference_ID,Created,CreatedBy
,Description,EntityType,IsActive
,Name,Updated,UpdatedBy,ValidationType
) VALUES (
0,0
,52002,TO_DATE('2008-06-02 21:29:14','YYYY-MM-DD HH24:MI:SS'),100
,'Where money should be transfered to','A','Y'
,'_TransferType',TO_DATE('2008-06-02 21:29:14','YYYY-MM-DD HH24:MI:SS'),100,'L')
;

-- Jun 2, 2008 9:29:14 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=52002 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- Jun 2, 2008 9:29:42 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Ref_List (
AD_Client_ID,AD_Org_ID
,AD_Reference_ID,AD_Ref_List_ID
,Created,CreatedBy,EntityType,IsActive
,Name,Updated,UpdatedBy,Value
) VALUES (
0,0
,52002,52001
,TO_DATE('2008-06-02 21:29:42','YYYY-MM-DD HH24:MI:SS'),100,'A','Y'
,'Bank Account',TO_DATE('2008-06-02 21:29:42','YYYY-MM-DD HH24:MI:SS'),100,'B')
;

-- Jun 2, 2008 9:29:42 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=52001 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Jun 2, 2008 9:29:51 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Ref_List (
AD_Client_ID,AD_Org_ID
,AD_Reference_ID,AD_Ref_List_ID
,Created,CreatedBy,EntityType,IsActive
,Name,Updated,UpdatedBy,Value
) VALUES (
0,0
,52002,52002
,TO_DATE('2008-06-02 21:29:51','YYYY-MM-DD HH24:MI:SS'),100,'A','Y'
,'CashBook',TO_DATE('2008-06-02 21:29:51','YYYY-MM-DD HH24:MI:SS'),100,'C')
;

-- Jun 2, 2008 9:29:51 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=52002 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Jun 2, 2008 9:30:13 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,52080,52031,0,17,52002,52004,'CashBookTransferType',TO_DATE('2008-06-02 21:30:13','YYYY-MM-DD HH24:MI:SS'),100,'Where the money in the cash book should be transfered to. Either a Bank Account or another Cash Book','A',1,'Y','N','N','N','N','Y','N','N','N','N','Y','Cash Book Transfer Type',0,TO_DATE('2008-06-02 21:30:13','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 2, 2008 9:30:13 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52080 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 2, 2008 9:32:22 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,52032,0,'CashTransferBankAccount_ID',TO_DATE('2008-06-02 21:32:22','YYYY-MM-DD HH24:MI:SS'),100,'Bank Account on which to transfer all Cash transactions','A','Y','Transfer Cash trx to','Transfer Cash trx to',TO_DATE('2008-06-02 21:32:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 2, 2008 9:32:22 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=52032 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;


-- Nov 6, 2008 9:02:15 AM EET
-- Posterita Integration
UPDATE AD_Ref_Table SET WhereClause='C_BankAccount.IsActive=''Y''',Updated=TO_DATE('2008-11-06 09:02:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53283
;

-- Jun 2, 2008 9:34:10 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (
AD_Client_ID
,AD_Column_ID,AD_Element_ID,AD_Org_ID
,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID
,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version
) VALUES (
0
,52081,52032,0
,18,53283,52004
,'CashTransferBankAccount_ID',TO_DATE('2008-06-02 21:34:10','YYYY-MM-DD HH24:MI:SS'),100,'Bank Account on which to transfer all Cash transactions','A',22,'Y','N','N','N','N','N','N','N','N','N','Y','Transfer Cash trx to',0,TO_DATE('2008-06-02 21:34:10','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 2, 2008 9:34:10 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52081 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 2, 2008 9:35:28 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,52033,0,'CashTransferCashBook_ID',TO_DATE('2008-06-02 21:35:28','YYYY-MM-DD HH24:MI:SS'),100,'Cash Book on which to transfer all Cash transactions','A','Y','Transfer Cash trx to','Transfer Cash trx to',TO_DATE('2008-06-02 21:35:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 2, 2008 9:35:28 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=52033 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jun 2, 2008 9:36:18 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Reference (
AD_Client_ID,AD_Org_ID
,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive
,Name,Updated,UpdatedBy,ValidationType
) VALUES (
0,0
,52004,TO_DATE('2008-06-02 21:36:18','YYYY-MM-DD HH24:MI:SS'),100,'A','Y'
,'C_CashBook',TO_DATE('2008-06-02 21:36:18','YYYY-MM-DD HH24:MI:SS'),100,'T')
;

-- Jun 2, 2008 9:36:18 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=52004 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- Jun 2, 2008 9:36:54 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Ref_Table (
AD_Client_ID,AD_Display
,AD_Key,AD_Org_ID
,AD_Reference_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,Updated,UpdatedBy
,WhereClause
) VALUES (
0,5268
,5260,0
,52004,408,TO_DATE('2008-06-02 21:36:54','YYYY-MM-DD HH24:MI:SS'),100,'A','Y','N',TO_DATE('2008-06-02 21:36:54','YYYY-MM-DD HH24:MI:SS'),100
,'C_CashBook.IsActive=''Y''')
;

-- Jun 2, 2008 9:37:11 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,52082,52033,0,18,52004,52004,'CashTransferCashBook_ID',TO_DATE('2008-06-02 21:37:11','YYYY-MM-DD HH24:MI:SS'),100,'Cash Book on which to transfer all Cash transactions','A',22,'Y','N','N','N','N','N','N','N','N','N','Y','Transfer Cash trx to',0,TO_DATE('2008-06-02 21:37:11','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 2, 2008 9:37:11 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52082 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 2, 2008 9:37:53 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,52083,1463,0,19,52004,'C_CashBook_ID',TO_DATE('2008-06-02 21:37:53','YYYY-MM-DD HH24:MI:SS'),100,'Cash Book for recording petty cash transactions','A',22,'The Cash Book identifies a unique cash book.  The cash book is used to record cash transactions.','Y','N','N','N','N','Y','N','N','N','N','Y','Cash Book',0,TO_DATE('2008-06-02 21:37:53','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 2, 2008 9:37:53 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52083 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 2, 2008 9:39:00 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,52034,0,'C_CashBPartner_ID',TO_DATE('2008-06-02 21:39:00','YYYY-MM-DD HH24:MI:SS'),100,'BPartner to be used for Cash transactions','A','Y','Cash BPartner','Cash BPartner',TO_DATE('2008-06-02 21:39:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 2, 2008 9:39:00 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=52034 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jun 2, 2008 9:39:32 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,52084,52034,0,18,173,52004,'C_CashBPartner_ID',TO_DATE('2008-06-02 21:39:32','YYYY-MM-DD HH24:MI:SS'),100,'BPartner to be used for Cash transactions','A',22,'Y','N','N','N','N','Y','N','N','N','N','Y','Cash BPartner',0,TO_DATE('2008-06-02 21:39:32','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 2, 2008 9:39:32 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52084 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 2, 2008 9:41:11 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,52035,0,'Check_BankAccount_ID',TO_DATE('2008-06-02 21:41:11','YYYY-MM-DD HH24:MI:SS'),100,'Bank Account to be used for processing Check transactions','A','Y','Check Bank Account','Check Bank Account',TO_DATE('2008-06-02 21:41:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 2, 2008 9:41:11 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=52035 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jun 2, 2008 9:41:34 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (
AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID
,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID
,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version
) VALUES (0,52085,52035,0
,18,53283,52004
,'Check_BankAccount_ID',TO_DATE('2008-06-02 21:41:34','YYYY-MM-DD HH24:MI:SS'),100,'Bank Account to be used for processing Check transactions','A',22,'Y','N','N','N','N','N','N','N','N','N','Y','Check Bank Account',0,TO_DATE('2008-06-02 21:41:34','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 2, 2008 9:41:34 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52085 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 2, 2008 9:42:51 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,52036,0,'CheckTransferBankAccount_ID',TO_DATE('2008-06-02 21:42:50','YYYY-MM-DD HH24:MI:SS'),100,'Bank account on which to transfer Check transactions','A','Y','Tranfer Check trx to','Transfer Check trx to',TO_DATE('2008-06-02 21:42:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 2, 2008 9:42:51 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=52036 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jun 2, 2008 9:43:16 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (
AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID
,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID
,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version
) VALUES (
0,52086,52036,0
,18,53283,52004
,'CheckTransferBankAccount_ID',TO_DATE('2008-06-02 21:43:15','YYYY-MM-DD HH24:MI:SS'),100,'Bank account on which to transfer Check transactions','A',22,'Y','N','N','N','N','N','N','N','N','N','Y','Tranfer Check trx to',0,TO_DATE('2008-06-02 21:43:15','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 2, 2008 9:43:16 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52086 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 2, 2008 9:44:12 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,52037,0,'CheckTransferType',TO_DATE('2008-06-02 21:44:12','YYYY-MM-DD HH24:MI:SS'),100,'A','Y','Check Transfer Type','Check Transfer Type',TO_DATE('2008-06-02 21:44:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 2, 2008 9:44:12 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=52037 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jun 2, 2008 9:44:39 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,52087,52037,0,17,52002,52004,'CheckTransferType',TO_DATE('2008-06-02 21:44:39','YYYY-MM-DD HH24:MI:SS'),100,'A',1,'Y','N','N','N','N','N','N','N','N','N','Y','Check Transfer Type',0,TO_DATE('2008-06-02 21:44:39','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 2, 2008 9:44:39 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52087 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 2, 2008 9:46:05 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,52038,0,'Card_BankAccount_ID',TO_DATE('2008-06-02 21:46:05','YYYY-MM-DD HH24:MI:SS'),100,'Bank Account on which card transactions will be processed','A','Y','Card Bank Account','Card Bank Account',TO_DATE('2008-06-02 21:46:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 2, 2008 9:46:05 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=52038 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jun 2, 2008 9:46:28 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (
AD_Client_ID
,AD_Column_ID,AD_Element_ID,AD_Org_ID
,AD_Reference_ID,AD_Reference_Value_ID
,AD_Table_ID
,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version
) VALUES (
0
,52088,52038,0
,18,53283
,52004
,'Card_BankAccount_ID',TO_DATE('2008-06-02 21:46:28','YYYY-MM-DD HH24:MI:SS'),100,'Bank Account on which card transactions will be processed','A',22,'Y','N','N','N','N','N','N','N','N','N','Y','Card Bank Account',0,TO_DATE('2008-06-02 21:46:28','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 2, 2008 9:46:28 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52088 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 2, 2008 9:47:33 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,52039,0,'CardTransferBankAccount_ID',TO_DATE('2008-06-02 21:47:33','YYYY-MM-DD HH24:MI:SS'),100,'Bank account on which to transfer Card transactions','A','Y','Transfer Card trx to','Transfer Card trx to',TO_DATE('2008-06-02 21:47:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 2, 2008 9:47:33 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=52039 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jun 2, 2008 9:47:59 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (
AD_Client_ID
,AD_Column_ID,AD_Element_ID,AD_Org_ID
,AD_Reference_ID,AD_Reference_Value_ID
,AD_Table_ID
,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version
) VALUES (
0
,52089,52039,0
,18,53283
,52004,'CardTransferBankAccount_ID',TO_DATE('2008-06-02 21:47:59','YYYY-MM-DD HH24:MI:SS'),100,'Bank account on which to transfer Card transactions','A',22,'Y','N','N','N','N','N','N','N','N','N','Y','Transfer Card trx to',0,TO_DATE('2008-06-02 21:47:59','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 2, 2008 9:47:59 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52089 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 2, 2008 9:48:55 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy
) VALUES (0,52040,0,'CardTransferCashBook_ID',TO_DATE('2008-06-02 21:48:55','YYYY-MM-DD HH24:MI:SS'),100,'Cash Book on which to transfer all Card transactions','A','Y','Transfer Card trx to','Transfer Card trx to',TO_DATE('2008-06-02 21:48:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 2, 2008 9:48:55 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=52040 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jun 2, 2008 9:49:35 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (
AD_Client_ID
,AD_Column_ID,AD_Element_ID,AD_Org_ID
,AD_Reference_ID,AD_Reference_Value_ID
,AD_Table_ID
,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version
) VALUES (
0
,52090,52040,0
,18,52004
,52004
,'CardTransferCashBook_ID',TO_DATE('2008-06-02 21:49:35','YYYY-MM-DD HH24:MI:SS'),100,'Cash Book on which to transfer all Card transactions','A',22,'Y','N','N','N','N','N','N','N','N','N','Y','Transfer Card trx to',0,TO_DATE('2008-06-02 21:49:35','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 2, 2008 9:49:35 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52090 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 2, 2008 9:50:21 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,52041,0,'CardTransferType',TO_DATE('2008-06-02 21:50:21','YYYY-MM-DD HH24:MI:SS'),100,'A','Y','Card Transfer Type','Card Transfer Type',TO_DATE('2008-06-02 21:50:21','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 2, 2008 9:50:21 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=52041 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jun 2, 2008 9:50:40 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,52091,52041,0,17,52002,52004,'CardTransferType',TO_DATE('2008-06-02 21:50:40','YYYY-MM-DD HH24:MI:SS'),100,'A',1,'Y','N','N','N','N','N','N','N','N','N','Y','Card Transfer Type',0,TO_DATE('2008-06-02 21:50:40','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 2, 2008 9:50:40 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52091 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 2, 2008 9:51:10 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,52092,245,0,16,52004,'Created',TO_DATE('2008-06-02 21:51:10','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','A',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','N','N','N','Created',0,TO_DATE('2008-06-02 21:51:10','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 2, 2008 9:51:10 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52092 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 2, 2008 9:52:01 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,52093,246,0,18,110,52004,'CreatedBy',TO_DATE('2008-06-02 21:52:01','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','A',22,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','N','N','N','Created By',0,TO_DATE('2008-06-02 21:52:01','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 2, 2008 9:52:01 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52093 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 2, 2008 9:53:40 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,52042,0,'C_TemplateBPartner_ID',TO_DATE('2008-06-02 21:53:39','YYYY-MM-DD HH24:MI:SS'),100,'BPartner that is to be used as template when new customers are created','A','Y','Template BPartner','Template BPartner',TO_DATE('2008-06-02 21:53:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 2, 2008 9:53:40 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=52042 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jun 2, 2008 9:54:16 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,52094,52042,0,18,173,52004,'C_TemplateBPartner_ID',TO_DATE('2008-06-02 21:54:16','YYYY-MM-DD HH24:MI:SS'),100,'BPartner that is to be used as template when new customers are created','A',22,'Y','N','N','N','N','N','N','N','N','N','Y','Template BPartner',0,TO_DATE('2008-06-02 21:54:16','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 2, 2008 9:54:16 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52094 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 2, 2008 9:55:01 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,52095,275,0,10,52004,'Description',TO_DATE('2008-06-02 21:55:01','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record','A',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','N','N','Y','Description',0,TO_DATE('2008-06-02 21:55:01','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 2, 2008 9:55:01 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52095 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 2, 2008 9:55:37 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,52096,326,0,14,52004,'Help',TO_DATE('2008-06-02 21:55:37','YYYY-MM-DD HH24:MI:SS'),100,'Comment or Hint','A',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','N','N','Y','Comment/Help',0,TO_DATE('2008-06-02 21:55:37','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 2, 2008 9:55:37 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52096 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 2, 2008 9:56:03 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,52097,348,0,20,52004,'IsActive',TO_DATE('2008-06-02 21:56:02','YYYY-MM-DD HH24:MI:SS'),100,'Y','The record is active in the system','A',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','N','N','N','N','N','Y','Active',0,TO_DATE('2008-06-02 21:56:02','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 2, 2008 9:56:03 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52097 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 2, 2008 9:56:55 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,52043,0,'LastLockTime',TO_DATE('2008-06-02 21:56:55','YYYY-MM-DD HH24:MI:SS'),100,'Last time at which the terminal was locked','A','Y','Last Lock Time','Last Lock Time',TO_DATE('2008-06-02 21:56:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 2, 2008 9:56:55 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=52043 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jun 2, 2008 9:57:13 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,52098,52043,0,16,52004,'LastLockTime',TO_DATE('2008-06-02 21:57:13','YYYY-MM-DD HH24:MI:SS'),100,'Last time at which the terminal was locked','A',7,'Y','N','N','N','N','N','N','N','N','N','Y','Last Lock Time',0,TO_DATE('2008-06-02 21:57:13','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 2, 2008 9:57:13 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52098 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 2, 2008 9:58:06 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,52044,0,'Locked',TO_DATE('2008-06-02 21:58:06','YYYY-MM-DD HH24:MI:SS'),100,'Whether the terminal is locked','A','Y','Locked','Locked',TO_DATE('2008-06-02 21:58:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 2, 2008 9:58:06 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=52044 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jun 2, 2008 9:58:26 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,52099,52044,0,20,52004,'Locked',TO_DATE('2008-06-02 21:58:26','YYYY-MM-DD HH24:MI:SS'),100,'N','Whether the terminal is locked','A',1,'Y','N','N','N','N','N','N','N','N','N','Y','Locked',0,TO_DATE('2008-06-02 21:58:26','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 2, 2008 9:58:26 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52099 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 2, 2008 9:59:13 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,52045,0,'LockTime',TO_DATE('2008-06-02 21:59:13','YYYY-MM-DD HH24:MI:SS'),100,'Time in minutes the terminal should be kept in a locked state.','A','Y','Lock Time','Lock Time',TO_DATE('2008-06-02 21:59:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 2, 2008 9:59:13 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=52045 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jun 2, 2008 9:59:40 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,ValueMin,Version) VALUES (0,52100,52045,0,11,52004,'LockTime',TO_DATE('2008-06-02 21:59:40','YYYY-MM-DD HH24:MI:SS'),100,'Time in minutes the terminal should be kept in a locked state.','A',10,'Y','N','N','N','N','N','N','N','N','N','Y','Lock Time',0,TO_DATE('2008-06-02 21:59:40','YYYY-MM-DD HH24:MI:SS'),100,'0',0)
;

-- Jun 2, 2008 9:59:40 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52100 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 2, 2008 10:00:25 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,52101,459,0,19,52004,'M_Warehouse_ID',TO_DATE('2008-06-02 22:00:24','YYYY-MM-DD HH24:MI:SS'),100,'Storage Warehouse and Service Point','A',22,'The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','N','N','N','N','N','N','N','N','N','Y','Warehouse',0,TO_DATE('2008-06-02 22:00:24','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 2, 2008 10:00:25 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52101 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 2, 2008 10:00:44 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,52102,469,0,10,52004,'Name',TO_DATE('2008-06-02 22:00:44','YYYY-MM-DD HH24:MI:SS'),100,'Alphanumeric identifier of the entity','A',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','N','N','N','N','N','N','N','Y','Name',0,TO_DATE('2008-06-02 22:00:44','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 2, 2008 10:00:44 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52102 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 2, 2008 10:02:07 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,52103,480,0,18,166,52004,'PO_PriceList_ID',TO_DATE('2008-06-02 22:02:07','YYYY-MM-DD HH24:MI:SS'),100,'Price List used by this Business Partner','A',22,'Identifies the price list used by a Vendor for products purchased by this organization.','Y','N','N','N','N','N','N','N','N','N','Y','Purchase Pricelist',0,TO_DATE('2008-06-02 22:02:07','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 2, 2008 10:02:07 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52103 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 2, 2008 10:02:37 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,52104,2051,0,10,52004,'PrinterName',TO_DATE('2008-06-02 22:02:37','YYYY-MM-DD HH24:MI:SS'),100,'Name of the Printer','A',60,'Internal (Opereating System) Name of the Printer; Please mote that the printer name may be different on different clients. Enter a printer name, which applies to ALL clients (e.g. printer on a server). <p>
If none is entered, the default printer is used. You specify your default printer when you log in. You can also change the default printer in Preferences.','Y','N','N','N','N','N','N','N','N','N','Y','Printer Name',0,TO_DATE('2008-06-02 22:02:37','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 2, 2008 10:02:37 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52104 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 2, 2008 10:03:16 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,52105,1063,0,18,316,52004,'SalesRep_ID',TO_DATE('2008-06-02 22:03:16','YYYY-MM-DD HH24:MI:SS'),100,'Sales Representative or Company Agent','A',22,'The Sales Representative indicates the Sales Rep for this Region.  Any Sales Rep must be a valid internal user.','Y','N','N','N','N','N','N','N','N','N','Y','Sales Representative',0,TO_DATE('2008-06-02 22:03:16','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 2, 2008 10:03:16 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52105 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 2, 2008 10:04:15 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,52046,0,'SO_PriceList_ID',TO_DATE('2008-06-02 22:04:15','YYYY-MM-DD HH24:MI:SS'),100,'A','Y','Sales Pricelist','Sales Pricelist',TO_DATE('2008-06-02 22:04:15','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 2, 2008 10:04:15 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=52046 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jun 2, 2008 10:04:46 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,52106,52046,0,18,166,52004,'SO_PriceList_ID',TO_DATE('2008-06-02 22:04:46','YYYY-MM-DD HH24:MI:SS'),100,'A',22,'Y','N','N','N','N','N','N','N','N','N','Y','Sales Pricelist',0,TO_DATE('2008-06-02 22:04:46','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 2, 2008 10:04:46 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52106 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 2, 2008 10:05:16 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,52047,0,'UnlockingTime',TO_DATE('2008-06-02 22:05:16','YYYY-MM-DD HH24:MI:SS'),100,'Time at which the terminal should be unlocked','A','Y','UnlockingTime','UnlockingTime',TO_DATE('2008-06-02 22:05:16','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 2, 2008 10:05:16 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=52047 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jun 2, 2008 10:05:34 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,52107,52047,0,16,52004,'UnlockingTime',TO_DATE('2008-06-02 22:05:34','YYYY-MM-DD HH24:MI:SS'),100,'Time at which the terminal should be unlocked','A',7,'Y','N','N','N','N','N','N','N','N','N','Y','UnlockingTime',0,TO_DATE('2008-06-02 22:05:34','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 2, 2008 10:05:34 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52107 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 2, 2008 10:05:58 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,52108,607,0,16,52004,'Updated',TO_DATE('2008-06-02 22:05:58','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','A',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','N','N','N','Updated',0,TO_DATE('2008-06-02 22:05:58','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 2, 2008 10:05:58 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52108 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 2, 2008 10:06:30 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,52109,608,0,18,110,52004,'UpdatedBy',TO_DATE('2008-06-02 22:06:30','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','A',22,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','N','N','N','N','N','Y','Updated By',0,TO_DATE('2008-06-02 22:06:30','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 2, 2008 10:06:30 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52109 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 2, 2008 10:29:01 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,52048,0,'U_POSTerminal_ID',TO_DATE('2008-06-02 22:29:01','YYYY-MM-DD HH24:MI:SS'),100,'A','Y','POS Terminal','POS Terminal',TO_DATE('2008-06-02 22:29:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 2, 2008 10:29:01 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=52048 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jun 2, 2008 10:29:54 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,52110,52048,0,13,52004,'U_POSTerminal_ID',TO_DATE('2008-06-02 22:29:54','YYYY-MM-DD HH24:MI:SS'),100,'A',22,'Y','N','N','N','Y','Y','N','N','N','N','N','POS Terminal',0,TO_DATE('2008-06-02 22:29:54','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 2, 2008 10:29:54 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52110 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 2, 2008 10:48:55 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,52049,0,'CheckTransferCashBook_ID',TO_DATE('2008-06-02 22:48:55','YYYY-MM-DD HH24:MI:SS'),100,'Cash Book on which to transfer all Check transactions','A','Y','Transfer Check trx to','Transfer Check trx to',TO_DATE('2008-06-02 22:48:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 2, 2008 10:48:55 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=52049 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jun 2, 2008 10:49:35 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,52111,52049,0,18,52004,52004,'CheckTransferCashBook_ID',TO_DATE('2008-06-02 22:49:35','YYYY-MM-DD HH24:MI:SS'),100,'Cash Book on which to transfer all Check transactions','A',22,'Y','N','N','N','N','N','N','N','N','N','Y','Transfer Check trx to',0,TO_DATE('2008-06-02 22:49:35','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jun 2, 2008 10:49:35 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52111 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 3, 2008 2:27:59 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Sequence (
AD_Client_ID,AD_Org_ID
,AD_Sequence_ID,Created,CreatedBy
,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy
) VALUES (
0,0
,52006,TO_DATE('2008-06-03 14:27:59','YYYY-MM-DD HH24:MI:SS'),100
,1000000,100,'Table U_POSTerminal',1,'Y','N','Y','Y','U_POSTerminal','N',1000000,TO_DATE('2008-06-03 14:27:59','YYYY-MM-DD HH24:MI:SS'),100)
;


-- 1.7.0/oracle/003_Terminals_TransferConfiguration.sql
-----------------------------
ALTER TABLE AD_OrgInfo ADD TransferBank_ID NUMBER(10);
ALTER TABLE AD_OrgInfo ADD TransferCashBook_ID NUMBER(10);

ALTER TABLE AD_OrgInfo
	ADD CONSTRAINT "cbank_adorginfo"
	FOREIGN KEY(TransferBank_ID)
	REFERENCES C_Bank(C_Bank_ID);


ALTER TABLE AD_OrgInfo
	ADD CONSTRAINT "ccashbook_adorginfo"
	FOREIGN KEY(TransferCashBook_ID)
	REFERENCES C_CashBook(C_CashBook_ID);




-- May 26, 2008 23:25:51 AM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,52028,0,'TransferBank_ID',TO_DATE('2008-05-26 23:25:50','YYYY-MM-DD HH24:MI:SS'),100,'Bank account depending on currency will be used from this bank for doing transfers','D','Y','Bank for transfers','Bank for transfers',TO_DATE('2008-05-26 23:25:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- May 26, 2008 23:25:51 AM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=52028 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- May 26, 2008 23:26:38 AM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,52001,TO_DATE('2008-05-26 23:26:38','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','C_Bank',TO_DATE('2008-05-26 23:26:38','YYYY-MM-DD HH24:MI:SS'),100,'T')
;

-- May 26, 2008 23:26:38 AM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=52001 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- May 26, 2008 23:28:08 AM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Ref_Table (AD_Client_ID,AD_Display,AD_Key,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,Updated,UpdatedBy,WhereClause) VALUES (0,3039,3031,0,52001,296,TO_DATE('2008-05-26 23:28:08','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N',TO_DATE('2008-05-26 23:28:08','YYYY-MM-DD HH24:MI:SS'),100,'C_Bank.IsOwnBank=''Y''')
;

-- May 26, 2008 23:28:33 AM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,52075,52028,0,18,52001,228,'TransferBank_ID',TO_DATE('2008-05-26 23:28:33','YYYY-MM-DD HH24:MI:SS'),100,'Bank account depending on currency will be used from this bank for doing transfers','D',22,'Y','N','N','N','N','N','N','N','N','N','Y','Bank for transfers',0,TO_DATE('2008-05-26 23:28:33','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- May 26, 2008 23:28:33 AM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52075 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;



-- Cash Book

-- May 26, 2008 23:29:40 AM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,52029,0,'TransferCashBook_ID',TO_DATE('2008-05-23 23:29:40','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','CashBook for transfers','CashBook for transfers',TO_DATE('2008-05-23 23:29:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- May 26, 2008 23:29:40 AM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=52029 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- May 26, 2008 23:30:32 AM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,52076,52029,0,18,52004,228,'TransferCashBook_ID',TO_DATE('2008-05-23 23:30:31','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','N','N','N','N','N','N','Y','CashBook for transfers',0,TO_DATE('2008-05-23 23:30:31','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- May 26, 2008 23:30:32 AM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52076 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;


-- 1.7.0/oracle/004_Terminals_newTableDDL.sql
-----------------------------
-- Jun 2, 2008 23:58:02 AM GMT+04:00
-- Default comment for updating dictionary
CREATE TABLE U_POSTerminal (AD_Client_ID NUMBER(10) DEFAULT  NULL  NOT NULL, AD_Org_ID NUMBER(10) DEFAULT  NULL  NOT NULL, AutoLock CHAR(1) DEFAULT 'N' CHECK (AutoLock IN ('Y','N')) NOT NULL, Card_BankAccount_ID NUMBER(10), CardTransferBankAccount_ID NUMBER(10), CardTransferCashBook_ID NUMBER(10), CardTransferType CHAR(1), CashBookTransferType CHAR(1) NOT NULL, CashTransferBankAccount_ID NUMBER(10), CashTransferCashBook_ID NUMBER(10), C_CashBook_ID NUMBER(10) NOT NULL, C_CashBPartner_ID NUMBER(10) NOT NULL, Check_BankAccount_ID NUMBER(10), CheckTransferBankAccount_ID NUMBER(10), CheckTransferCashBook_ID NUMBER(10), CheckTransferType CHAR(1), Created DATE NOT NULL, CreatedBy NUMBER(10) NOT NULL, C_TemplateBPartner_ID NUMBER(10), Description NVARCHAR2(255), Help NVARCHAR2(2000), IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')), LastLockTime DATE, Locked CHAR(1) DEFAULT 'N' CHECK (Locked IN ('Y','N')), LockTime NUMBER(10), M_Warehouse_ID NUMBER(10), Name NVARCHAR2(60), PO_PriceList_ID NUMBER(10), PrinterName NVARCHAR2(60), SalesRep_ID NUMBER(10), SO_PriceList_ID NUMBER(10), UnlockingTime DATE, Updated DATE NOT NULL, UpdatedBy NUMBER(10), U_POSTerminal_ID NUMBER(10) NOT NULL, CONSTRAINT U_POSTerminal_Key PRIMARY KEY (U_POSTerminal_ID))
;


-- 1.7.0/oracle/005_Messages.sql
-----------------------------
-- Jun 9, 2008 1:24:35 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,52452,0,TO_DATE('2008-06-09 13:24:35','YYYY-MM-DD HH24:MI:SS'),100,'A','Y','Organisation','I',TO_DATE('2008-06-09 13:24:35','YYYY-MM-DD HH24:MI:SS'),100,'smenu.organisation')
;

-- Jun 9, 2008 1:25:48 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,52453,0,TO_DATE('2008-06-09 13:25:48','YYYY-MM-DD HH24:MI:SS'),100,'A','Y','Terminals','I',TO_DATE('2008-06-09 13:25:48','YYYY-MM-DD HH24:MI:SS'),100,'smenu.terminal')
;

-- Jun 9, 2008 1:26:06 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,52454,0,TO_DATE('2008-06-09 13:26:06','YYYY-MM-DD HH24:MI:SS'),100,'A','Y','Cash Books','I',TO_DATE('2008-06-09 13:26:06','YYYY-MM-DD HH24:MI:SS'),100,'smenu.cashbook')
;

-- Jun 9, 2008 1:26:30 PM GMT+04:00
-- Default comment for updating dictionary
UPDATE AD_Message SET MsgTip='Maintain POS Terminals',Updated=TO_DATE('2008-06-09 13:26:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=52453
;

-- Jun 9, 2008 1:26:39 PM GMT+04:00
-- Default comment for updating dictionary
UPDATE AD_Message SET MsgTip='Maintain Cash Books',Updated=TO_DATE('2008-06-09 13:26:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=52454
;

-- Jun 9, 2008 1:27:15 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,52455,0,TO_DATE('2008-06-09 13:27:15','YYYY-MM-DD HH24:MI:SS'),100,'A','Y','Completed','I',TO_DATE('2008-06-09 13:27:15','YYYY-MM-DD HH24:MI:SS'),100,'document.status.completed')
;

-- Jun 9, 2008 1:28:05 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,52456,0,TO_DATE('2008-06-09 13:28:04','YYYY-MM-DD HH24:MI:SS'),100,'A','Y','Drafted','I',TO_DATE('2008-06-09 13:28:04','YYYY-MM-DD HH24:MI:SS'),100,'document.status.drafted')
;

-- Jun 9, 2008 1:28:27 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,52457,0,TO_DATE('2008-06-09 13:28:27','YYYY-MM-DD HH24:MI:SS'),100,'A','Y','In Progress','I',TO_DATE('2008-06-09 13:28:27','YYYY-MM-DD HH24:MI:SS'),100,'document.status.inprogress')
;

-- Jun 9, 2008 1:28:44 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,52458,0,TO_DATE('2008-06-09 13:28:44','YYYY-MM-DD HH24:MI:SS'),100,'A','Y','Invalid','I',TO_DATE('2008-06-09 13:28:44','YYYY-MM-DD HH24:MI:SS'),100,'document.status.invalid')
;

-- Jun 9, 2008 1:28:58 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,52459,0,TO_DATE('2008-06-09 13:28:58','YYYY-MM-DD HH24:MI:SS'),100,'A','Y','Closed','I',TO_DATE('2008-06-09 13:28:58','YYYY-MM-DD HH24:MI:SS'),100,'document.status.closed')
;

-- Jun 9, 2008 1:29:15 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,52460,0,TO_DATE('2008-06-09 13:29:15','YYYY-MM-DD HH24:MI:SS'),100,'A','Y','Cash','I',TO_DATE('2008-06-09 13:29:15','YYYY-MM-DD HH24:MI:SS'),100,'payment.rule.cash')
;

-- Jun 9, 2008 1:29:27 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,52461,0,TO_DATE('2008-06-09 13:29:27','YYYY-MM-DD HH24:MI:SS'),100,'A','Y','Card','I',TO_DATE('2008-06-09 13:29:27','YYYY-MM-DD HH24:MI:SS'),100,'payment.rule.card')
;

-- Jun 9, 2008 1:29:42 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,52462,0,TO_DATE('2008-06-09 13:29:41','YYYY-MM-DD HH24:MI:SS'),100,'A','Y','Cheque','I',TO_DATE('2008-06-09 13:29:41','YYYY-MM-DD HH24:MI:SS'),100,'payment.rule.cheque')
;

-- Jun 9, 2008 1:29:55 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,52463,0,TO_DATE('2008-06-09 13:29:55','YYYY-MM-DD HH24:MI:SS'),100,'A','Y','Mixed','I',TO_DATE('2008-06-09 13:29:55','YYYY-MM-DD HH24:MI:SS'),100,'payment.rule.mixed')
;

-- Jun 9, 2008 1:30:14 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,52464,0,TO_DATE('2008-06-09 13:30:13','YYYY-MM-DD HH24:MI:SS'),100,'A','Y','Credit','I',TO_DATE('2008-06-09 13:30:13','YYYY-MM-DD HH24:MI:SS'),100,'payment.rule.credit')
;

-- Jun 9, 2008 1:30:46 PM GMT+04:00
-- Default comment for updating dictionary
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,52465,0,TO_DATE('2008-06-09 13:30:46','YYYY-MM-DD HH24:MI:SS'),100,'A','Y','Amount Paid','I',TO_DATE('2008-06-09 13:30:46','YYYY-MM-DD HH24:MI:SS'),100,'AmountPaid')
;

---------------------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO AD_MESSAGE
(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, 
updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
VALUES(52470 , 0, 0, 'Y', TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, 
'html', 'HTML', ' ', 'I', 'D'); 

INSERT INTO AD_MESSAGE
(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, 
updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
VALUES(52471 , 0, 0, 'Y', TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, 
'pdf', 'PDF', ' ', 'I', 'D'); 

INSERT INTO AD_MESSAGE
(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, 
updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
VALUES(52472 , 0, 0, 'Y', TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, 
'csv', 'CSV', ' ', 'I', 'D'); 

INSERT INTO AD_MESSAGE
(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, 
updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
VALUES(52473 , 0, 0, 'Y', TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, 
'smenu.sales.report', 'Sales Report', ' ', 'I', 'D');

INSERT INTO AD_MESSAGE
(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, 
updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
VALUES(52474 , 0, 0, 'Y', TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, 
'smenu.best.selling.items', 'Best Selling Items', ' ', 'I', 'D');  

INSERT INTO AD_MESSAGE
(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, 
updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
VALUES(52475 , 0, 0, 'Y', TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, 
'smenu.stock.sales.report', 'Stock Sales Report', ' ', 'I', 'D'); 

INSERT INTO AD_MESSAGE
(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, 
updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
VALUES(52476 , 0, 0, 'Y', TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, 
'smenu.transfer.stock', 'Stock Transfer', ' ', 'I', 'D'); 

INSERT INTO AD_MESSAGE
(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, 
updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
VALUES(52477 , 0, 0, 'Y', TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, 
'smenu.inventory.move', 'Inventory Move', ' ', 'I', 'D'); 

INSERT INTO AD_MESSAGE
(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, 
updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
VALUES(52478 , 0, 0, 'Y', TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, 
'smenu.move.confirmation', 'Move Confirmation', ' ', 'I', 'D'); 

INSERT INTO AD_MESSAGE
(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, 
updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
VALUES(52479 , 0, 0, 'Y', TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, 
'smenu.barcode.printing', 'Barcode Printing', ' ', 'I', 'D'); 

INSERT INTO AD_MESSAGE
(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, 
updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
VALUES(52480 , 0, 0, 'Y', TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, 
'basePriceList', 'Base Price List', ' ', 'I', 'D'); 

INSERT INTO AD_MESSAGE
(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, 
updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
VALUES(52481, 0, 0, 'Y', TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, 
'isDeleteOldRecords', 'Delete Old Records', ' ', 'I', 'D'); 

INSERT INTO AD_MESSAGE
(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, 
updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
VALUES(52482, 0, 0, 'Y', TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, 
'sales.price.list', 'Sales Price List', ' ', 'I', 'D'); 

INSERT INTO AD_MESSAGE
(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, 
updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
VALUES(52483, 0, 0, 'Y', TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, 
'purchase.price.list', 'Purchase Price List', ' ', 'I', 'D'); 

INSERT INTO AD_MESSAGE
(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, 
updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
VALUES(52484, 0, 0, 'Y', TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, 
'pos.terminal', 'Terminal', ' ', 'I', 'D'); 

INSERT INTO AD_MESSAGE
(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, 
updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
VALUES(52485, 0, 0, 'Y', TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, 
'pos.version', 'Version', ' ', 'I', 'D'); 

INSERT INTO AD_MESSAGE
(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, 
updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
VALUES(52486, 0, 0, 'Y', TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, 
'pmenu.new.reports', 'Reports', ' ', 'I', 'D'); 

INSERT INTO AD_MESSAGE
(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, 
updated, updatedby, VALUE, msgtext, msgtip, msgtype, entitytype)
VALUES(52487, 0, 0, 'Y', TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, TO_TIMESTAMP('2008-06-20 10:40:00','YYYY-MM-DD HH24:MI:SS'), 100, 
'isCreatePriceList', 'Create Price List', ' ', 'I', 'D'); 


-- 1.7.1/oracle/001_M_Inventory.sql
-----------------------------
-- Add QtyCsv in M_inventoryLine
ALTER TABLE M_INVENTORYLINE ADD ( QTYCSV NUMBER(10, 0) DEFAULT 0 NOT NULL);


-- 1.7.2/oracle/001_ADRole.sql
-----------------------------
-- Additional Role Configuration for Discount
ALTER TABLE AD_ROLE ADD (IsDiscountUptoLimitPrice char(1) DEFAULT 'N' NOT NULL);
ALTER TABLE AD_ROLE ADD (IsDiscountAllowedOnTotal char(1) DEFAULT 'N' NOT NULL);


-- 1.7.3/oracle/002_ADMessage.sql
-----------------------------
INSERT INTO ad_message(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, value, msgtext, msgtip, msgtype, entitytype)
  VALUES(52488, 0, 0, 'Y', sysdate, 100, sysdate, 100, 'discount.upto.price.limit', 'Enforce Discount Upto Limit Price', NULL, 'I', 'U');
INSERT INTO ad_message(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, value, msgtext, msgtip, msgtype, entitytype)
  VALUES(52489, 0, 0, 'Y', sysdate, 100, sysdate, 100, 'over.ride.limit.price', 'Enforce Overide Limit Price', NULL, 'I', 'U');
INSERT INTO ad_message(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, value, msgtext, msgtip, msgtype, entitytype)
  VALUES(52490, 0, 0, 'Y', sysdate, 100, sysdate, 100, 'discount.allowed.total', 'Allowed Discount On Total Sales', NULL, 'I', 'U');
INSERT INTO ad_message(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, value, msgtext, msgtip, msgtype, entitytype)
  VALUES(52491, 0, 0, 'Y', sysdate, 100, sysdate, 100, 'smenu.pos.purchase.report', 'Purchases Report', NULL, 'I', 'U');
INSERT INTO ad_message(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, value, msgtext, msgtip, msgtype, entitytype)
  VALUES(52492, 0, 0, 'Y', sysdate, 100, sysdate, 100, 'smenu.settle.payment.credit.purchase', 'Settle Payment For Purchases On Credit', NULL, 'I', 'U');
INSERT INTO ad_message(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, value, msgtext, msgtip, msgtype, entitytype)
  VALUES(52493, 0, 0, 'Y', sysdate, 100, sysdate, 100, 'smenu.user.manual', 'User Manual', NULL, 'I', 'U');
INSERT INTO ad_message(ad_message_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, value, msgtext, msgtip, msgtype, entitytype)
  VALUES(52494, 0, 0, 'Y', sysdate, 100, sysdate, 100, 'smenu.contactus', 'Contact Us', NULL, 'I', 'U');


-- 1.7.3/oracle/003_AD_OrgInfo.sql
-----------------------------
ALTER TABLE ad_orginfo ADD (receiptFooterMsg varchar(1023));


-- 1.7.3/oracle/004_AD_Changes for AD_OrgInfo.sql
-----------------------------
INSERT INTO AD_Element
(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, 
columnname, entitytype, name, printname, description, help, po_name, po_printname, po_description, po_help)
VALUES(52050, 0, 0, 'Y', sysdate, 100, sysdate, 100, 
'ReceiptFooterMsg', 'U', 'Receipt Footer Msg', 'Receipt Footer Msg', 'This message will be displayed at the bottom of a receipt when doing a sales or purchase', NULL, NULL, NULL, NULL, NULL);


INSERT INTO ad_column
(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, name,
description, help, version, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id,
ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic,
isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, 
ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql, mandatorylogic)
VALUES(52112, 0, 0, 'Y', sysdate, sysdate, 100, 100, 'ReceiptFooterMsg' , 'This message will be displayed at the bottom of a receipt when doing a sales or purchase', NULL, 0, 'U', 'ReceiptFooterMsg', 228, 10, NULL, NULL, 1024, 1, 'N', 'N', 'Y', 'Y', NULL, 
'N', 0, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 52050, NULL, 'N', 'N', NULL, NULL);


-- 1.7.3/oracle/005_MissingIDs.sql
-----------------------------
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


-- 1.7.3/oracle/006_PackSize_MProduct.sql
-----------------------------
-- The Units Per Pack indicates the no of units of a product packed together.
ALTER TABLE M_Product ADD (UnitsPerPack number(10,0) default 1 not null );

-- AD Element --
INSERT INTO AD_ELEMENT(ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, columnname, entitytype, name, printname, description, help, po_name, po_printname, po_description, po_help)
  VALUES(52054, 0, 0, 'Y', sysdate, 100, sysdate, 100, 'UnitsPerPack', 'U', 'UnitsPerPack', 'UnitsPerPack', 'The Units Per Pack indicates the no of units of a product packed together.', NULL, NULL, NULL, NULL, NULL);

-- AD Column --
INSERT INTO AD_COLUMN(ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, name, description, help, version, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql, mandatorylogic, infofactoryclass)
  VALUES(52116, 0, 0, 'Y', sysdate, sysdate, 100, 100, 'UnitsPerPack', 'The Units Per Pack indicates the no of units of a product packed together.', NULL, 0, 'U', 'UnitsPerPack', 208, 11, NULL, NULL, 14, NULL, 'N', 'N', 'N', 'Y', NULL, 'N', 0, 'N', 'N', NULL, NULL, NULL, NULL, 'N', 52054, NULL, 'N', 'N', NULL, NULL, NULL);


-- 1.7.3/oracle/007_CashPayment.sql
-----------------------------
--- Alter table -- oracle
--- Table: C_Payment -----------------------------------------------------------
ALTER TABLE C_Payment 
MODIFY C_BankAccount_ID NUMBER(10,0) NULL;


ALTER TABLE C_Payment 
ADD C_CashBook_ID NUMBER(10,0);

ALTER TABLE C_Payment 
ADD CONSTRAINT C_Payment__C_CashBo_C_CashBook 
FOREIGN KEY(C_CashBook_ID)  REFERENCES C_CashBook(C_CashBook_ID);


--- Migration scripts ----------------------------------------------------------
-- Aug 26, 2008 11:49:31 AM MUT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) 
VALUES (0,52117,1463,0,19,335,'C_CashBook_ID',sysdate,100,'Cash Book for recording petty cash transactions','U',10,'The Cash Book identifies a unique cash book.  The cash book is used to record cash transactions.','Y','N','N','N','N','N','N','N','N','Y','Cash Book',sysdate,100,0);

-- Aug 26, 2008 11:49:31 AM MUT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52117 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID);


-- Aug 26, 2008 11:50:27 AM MUT
-- Default comment for updating dictionary
UPDATE AD_Column SET IsMandatory='N',Updated=sysdate,UpdatedBy=100 WHERE AD_Column_ID=3880;


-- Aug 26, 2008 11:50:27 AM MUT
-- Default comment for updating dictionary
UPDATE AD_Field SET Name='Bank Account', Description='Account at the Bank', Help='The Bank Account identifies an account at this Bank.' WHERE AD_Column_ID=3880 AND IsCentrallyMaintained='Y';


-- Aug 26, 2008 11:51:40 AM MUT
-- Default comment for updating dictionary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) 
VALUES (0,52117,52052,0,330,sysdate,100,'Cash Book for recording petty cash transactions',10,'U','The Cash Book identifies a unique cash book.  The cash book is used to record cash transactions.','Y','Y','Y','N','N','N','N','N','Cash Book',sysdate,100);


-- Aug 26, 2008 11:51:40 AM MUT
-- Default comment for updating dictionary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=52052 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID);


-- Aug 26, 2008 11:51:40 AM MUT
-- Default comment for updating dictionary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) 
VALUES (0,13705,52053,0,330,sysdate,100,10,'D','Y','Y','Y','N','N','N','N','N','Referenced Payment',sysdate,100);


-- Aug 26, 2008 11:51:40 AM MUT
-- Default comment for updating dictionary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=52053 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID);


-- Aug 26, 2008 11:52:11 AM MUT
-- Default comment for updating dictionary
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=52053;


-- Aug 26, 2008 11:52:11 AM MUT
-- Default comment for updating dictionary
UPDATE AD_Field SET SeqNo=660,IsDisplayed='Y' WHERE AD_Field_ID=52052;


-- Aug 26, 2008 11:52:56 AM MUT
-- Default comment for updating dictionary
UPDATE AD_Field SET DisplayLogic='@TenderType@ !''X''',Updated=sysdate,UpdatedBy=100 WHERE AD_Field_ID=4030;


-- Aug 26, 2008 11:54:17 AM MUT
-- Default comment for updating dictionary
UPDATE AD_Field SET DisplayLength=14, DisplayLogic='@TenderType@=''X''', IsSameLine='Y',Updated=sysdate,UpdatedBy=100 WHERE AD_Field_ID=52052;


-- Aug 26, 2008 11:54:42 AM MUT
-- Default comment for updating dictionary
UPDATE AD_Tab SET WhereClause=NULL,Updated=sysdate,UpdatedBy=100 WHERE AD_Tab_ID=330;


-- x.x.x/oracle/
-----------------------------