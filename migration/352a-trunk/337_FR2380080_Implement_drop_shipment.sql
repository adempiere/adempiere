-- INSERTING into AD_ELEMENT
Insert into AD_ELEMENT (AD_ELEMENT_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,CREATEDBY,UPDATED,UPDATEDBY,COLUMNNAME,ENTITYTYPE,NAME,PRINTNAME,DESCRIPTION,HELP,PO_NAME,PO_PRINTNAME,PO_DESCRIPTION,PO_HELP) values (53460,0,0,'Y',to_date('01/MAY/08','DD/MON/RR'),100,to_date('01/MAY/08','DD/MON/RR'),100,'DropShip_User_ID','U','Drop Shipment Contact','Drop Shipment Contact','Business Partner Contact for drop shipment',null,null,null,null,null);
Insert into AD_ELEMENT (AD_ELEMENT_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,CREATEDBY,UPDATED,UPDATEDBY,COLUMNNAME,ENTITYTYPE,NAME,PRINTNAME,DESCRIPTION,HELP,PO_NAME,PO_PRINTNAME,PO_DESCRIPTION,PO_HELP) values (53459,0,0,'Y',to_date('01/MAY/08','DD/MON/RR'),100,to_date('07/MAY/08','DD/MON/RR'),100,'DropShip_Location_ID','D','Drop Shipment Location','Drop Shipment Location','Business Partner Location for shipping to',null,null,null,null,null);
Insert into AD_ELEMENT (AD_ELEMENT_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,CREATEDBY,UPDATED,UPDATEDBY,COLUMNNAME,ENTITYTYPE,NAME,PRINTNAME,DESCRIPTION,HELP,PO_NAME,PO_PRINTNAME,PO_DESCRIPTION,PO_HELP) values (53458,0,0,'Y',to_date('01/MAY/08','DD/MON/RR'),100,to_date('01/MAY/08','DD/MON/RR'),100,'DropShip_BPartner_ID','U','Drop Shipment Partner','Drop Shipment  Partner','Business Partner to ship to','If empty the business partner will be shipped to.',null,null,null,null);

-- INSERTING into AD_Column
Insert into AD_Column (AD_COLUMN_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,UPDATED,CREATEDBY,UPDATEDBY,NAME,DESCRIPTION,HELP,VERSION,ENTITYTYPE,COLUMNNAME,AD_TABLE_ID,AD_REFERENCE_ID,AD_REFERENCE_VALUE_ID,AD_VAL_RULE_ID,FIELDLENGTH,DEFAULTVALUE,ISKEY,ISPARENT,ISMANDATORY,ISUPDATEABLE,READONLYLOGIC,ISIDENTIFIER,SEQNO,ISTRANSLATED,ISENCRYPTED,CALLOUT,VFORMAT,VALUEMIN,VALUEMAX,ISSELECTIONCOLUMN,AD_ELEMENT_ID,AD_PROCESS_ID,ISSYNCDATABASE,ISALWAYSUPDATEABLE,COLUMNSQL,MANDATORYLOGIC,INFOFACTORYCLASS) values (55314,0,0,'Y',to_date('07/MAY/08','DD/MON/RR'),to_date('07/MAY/08','DD/MON/RR'),100,100,'Drop Shipment Partner','Business Partner to ship to','If empty the business partner will be shipped to.',1,'D','DropShip_BPartner_ID',259,30,138,230,22,null,'N','N','N','Y',null,'N',0,'N','N',null,null,null,null,'N',53458,null,'N','N',null,null,null);

-- 7/05/2008 11:35:44
-- Drop shipment
ALTER TABLE C_Order ADD DropShip_BPartner_ID NUMBER(10)
;

-- 7/05/2008 11:40:02
-- Drop shipment
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52022,'C_BPartner_Location.C_BPartner_ID=@DropShip_BPartner_ID@ AND C_BPartner_Location.IsShipTo=''Y'' AND C_BPartner_Location.IsActive=''Y''',TO_DATE('2008-05-07 11:39:57','YYYY-MM-DD HH24:MI:SS'),100,'U','Y','C_BPartner_Loc Ship - Drop Ship BPartner','S',TO_DATE('2008-05-07 11:39:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- INSERTING into AD_Column
Insert into AD_Column (AD_COLUMN_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,UPDATED,CREATEDBY,UPDATEDBY,NAME,DESCRIPTION,HELP,VERSION,ENTITYTYPE,
COLUMNNAME,AD_TABLE_ID,AD_REFERENCE_ID,AD_REFERENCE_VALUE_ID,AD_VAL_RULE_ID,FIELDLENGTH,DEFAULTVALUE,ISKEY,ISPARENT,ISMANDATORY,
ISUPDATEABLE,READONLYLOGIC,ISIDENTIFIER,SEQNO,ISTRANSLATED,ISENCRYPTED,CALLOUT,VFORMAT,VALUEMIN,VALUEMAX,ISSELECTIONCOLUMN,
AD_ELEMENT_ID,AD_PROCESS_ID,ISSYNCDATABASE,ISALWAYSUPDATEABLE,COLUMNSQL,MANDATORYLOGIC,INFOFACTORYCLASS) 
values (55315,0,0,'Y',to_date('07/MAY/08','DD/MON/RR'),to_date('07/MAY/08','DD/MON/RR'),100,100,'Drop Shipment Location',
'Business Partner Location for shipping to',null,1,'D','DropShip_Location_ID',259,18,159,52022,22,null,'N','N','N','Y',
null,'N',0,'N','N',null,null,null,null,'N',53459,null,'N','N',null,null,null);

-- 7/05/2008 11:41:42
-- Drop shipment
ALTER TABLE C_Order ADD DropShip_Location_ID NUMBER(10)
;

-- INSERTING 
Insert into AD_Column (AD_COLUMN_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,UPDATED,CREATEDBY,UPDATEDBY,NAME,DESCRIPTION,
HELP,VERSION,ENTITYTYPE,COLUMNNAME,AD_TABLE_ID,AD_REFERENCE_ID,AD_REFERENCE_VALUE_ID,AD_VAL_RULE_ID,FIELDLENGTH,
DEFAULTVALUE,ISKEY,ISPARENT,ISMANDATORY,ISUPDATEABLE,READONLYLOGIC,ISIDENTIFIER,SEQNO,ISTRANSLATED,ISENCRYPTED,
CALLOUT,VFORMAT,VALUEMIN,VALUEMAX,ISSELECTIONCOLUMN,AD_ELEMENT_ID,AD_PROCESS_ID,ISSYNCDATABASE,ISALWAYSUPDATEABLE,
COLUMNSQL,MANDATORYLOGIC,INFOFACTORYCLASS)
 values (55316,0,0,'Y',to_date('07/MAY/08','DD/MON/RR'),to_date('07/MAY/08','DD/MON/RR'),100,100,
'Drop Shipment Contact','Business Partner Contact for drop shipment',null,1,'D','DropShip_User_ID',
259,18,110,168,22,null,'N','N','N','Y',null,'N',0,'N','N',null,null,null,null,'N',53460,null,'N','N',null,null,null);


-- 7/05/2008 11:42:43
-- Drop shipment
ALTER TABLE C_Order ADD DropShip_User_ID NUMBER(10)
;

-- 7/05/2008 11:46:47
-- Drop shipment
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,55317,2466,0,20,319,'IsDropShip',TO_DATE('2008-05-07 11:46:46','YYYY-MM-DD HH24:MI:SS'),100,'N','Drop Shipments are sent from the Vendor directly to the Customer','D',1,'Drop Shipments do not cause any Inventory reservations or movements as the Shipment is from the Vendor''s inventory. The Shipment of the Vendor to the Customer must be confirmed.','Y','N','N','N','N','N','N','N','N','N','Y','Drop Shipment',0,TO_DATE('2008-05-07 11:46:46','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 7/05/2008 11:47:15
-- Drop shipment
ALTER TABLE M_InOut ADD IsDropShip CHAR(1) DEFAULT 'N' CHECK (IsDropShip IN ('Y','N'))
;

-- 7/05/2008 11:48:17
-- Drop shipment
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,55318,53458,0,18,138,319,'DropShip_BPartner_ID',TO_DATE('2008-05-07 11:48:16','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner to ship to','D',10,'If empty the business partner will be shipped to.','Y','N','N','N','N','N','N','N','N','N','Y','Drop Shipment Partner',0,TO_DATE('2008-05-07 11:48:16','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 7/05/2008 11:48:21
-- Drop shipment
ALTER TABLE M_InOut ADD DropShip_BPartner_ID NUMBER(10)
;


-- 7/05/2008 11:49:16
-- Drop shipment
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,55319,53459,0,18,159,319,120,'DropShip_Location_ID',TO_DATE('2008-05-07 11:49:15','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner Location for shipping to','D',10,'Y','N','N','N','N','N','N','N','N','N','Y','Drop Shipment Location',0,TO_DATE('2008-05-07 11:49:15','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 7/05/2008 11:49:18
-- Drop shipment
ALTER TABLE M_InOut ADD DropShip_Location_ID NUMBER(10)
;


-- 7/05/2008 11:49:58
-- Drop shipment
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,
Created,CreatedBy,Description,EntityType,FieldLength,
IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,
IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,55320,53460,0,18,110,319,168,'DropShip_User_ID',
TO_DATE('2008-05-07 11:49:57','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner Contact for drop shipment','D',10,
'Y','N','N','N','N','N','N','N','N','N',
'Y','Drop Shipment Contact',0,TO_DATE('2008-05-07 11:49:57','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 7/05/2008 11:50:00
-- Drop shipment
ALTER TABLE M_InOut ADD DropShip_User_ID NUMBER(10)
;

-- 7/05/2008 11:58:45
-- Drop shipment
UPDATE AD_Column SET IsUpdateable='Y',Updated=TO_DATE('2008-05-07 11:58:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=11580
;

-- 7/05/2008 12:03:04
-- Drop shipment
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,55314,55410,0,186,TO_DATE('2008-05-07 12:03:03','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner to ship to',14,'@IsDropShip@=''Y''','D','If empty the business partner will be shipped to.','Y','Y','Y','N','N','N','N','N','Drop Shipment Partner',192,0,TO_DATE('2008-05-07 12:03:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 7/05/2008 12:03:04
-- Drop shipment
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55410 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 7/05/2008 12:04:09
-- Drop shipment
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,55315,130,55411,0,186,TO_DATE('2008-05-07 12:04:08','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner Location for shipping to',14,'@IsDropShip@=''Y''','D','Y','Y','Y','N','N','N','N','N','Drop Shipment Location',194,0,TO_DATE('2008-05-07 12:04:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 7/05/2008 12:04:09
-- Drop shipment
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55411 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 7/05/2008 12:04:17
-- Drop shipment
UPDATE AD_Field SET AD_FieldGroup_ID=130,Updated=TO_DATE('2008-05-07 12:04:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=55410
;

-- 7/05/2008 12:05:27
-- Drop shipment
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,55316,130,55412,0,186,TO_DATE('2008-05-07 12:05:26','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner Contact for drop shipment',14,'@IsDropShip@=''Y''','D','Y','Y','Y','N','N','N','N','N','Drop Shipment Contact',196,0,TO_DATE('2008-05-07 12:05:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 7/05/2008 12:05:27
-- Drop shipment
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55412 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 7/05/2008 12:08:49
-- Drop shipment
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-05-07 12:08:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=55411
;

-- 7/05/2008 12:15:15
-- Drop shipment
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,55314,124,55413,0,294,TO_DATE('2008-05-07 12:15:13','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner to ship to',14,'@IsDropShip@=''Y''','D','If empty the business partner will be shipped to.','Y','Y','Y','N','N','N','N','N','Drop Shipment Partner',402,0,TO_DATE('2008-05-07 12:15:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 7/05/2008 12:15:15
-- Drop shipment
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55413 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 7/05/2008 12:16:35
-- Drop shipment
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,55315,124,55414,0,294,TO_DATE('2008-05-07 12:16:34','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner Location for shipping to',14,'@IsDropShip@=''Y''','D','Y','Y','Y','N','N','N','N','Y','Drop Shipment Location',404,0,TO_DATE('2008-05-07 12:16:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 7/05/2008 12:16:35
-- Drop shipment
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55414 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 7/05/2008 12:17:34
-- Drop shipment
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,55316,124,55415,0,294,TO_DATE('2008-05-07 12:17:33','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner Contact for drop shipment',14,'@IsDropShip@=''Y''','D','Y','Y','Y','N','N','N','N','N','Drop Shipment Contact',406,0,TO_DATE('2008-05-07 12:17:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 7/05/2008 12:17:34
-- Drop shipment
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55415 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 7/05/2008 12:17:50
-- Drop shipment
UPDATE AD_Field SET IsReadOnly='N',Updated=TO_DATE('2008-05-07 12:17:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=10123
;


-- 8/05/2008 10:32:31
-- Drop shipment
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53461,0,'DropShip_Warehouse_ID',TO_DATE('2008-05-08 10:32:26','YYYY-MM-DD HH24:MI:SS'),100,'The (logical) warehouse to use for recording drop ship receipts and shipments.','D','The drop ship warehouse will be used for recording material transactions relating to drop shipments to and from this organization.','Y','Drop Ship Warehouse','Drop Ship Warehouse',TO_DATE('2008-05-08 10:32:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 8/05/2008 10:32:31
-- Drop shipment
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53461 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 8/05/2008 10:33:53
-- Drop shipment
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,55321,53461,0,18,197,228,'DropShip_Warehouse_ID',TO_DATE('2008-05-08 10:33:51','YYYY-MM-DD HH24:MI:SS'),100,'The (logical) warehouse to use for recording drop ship receipts and shipments.','D',22,'The drop ship warehouse will be used for recording material transactions relating to drop shipments to and from this organization.','Y','N','N','N','N','N','N','N','N','N','Y','Drop Ship Warehouse',0,TO_DATE('2008-05-08 10:33:51','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 8/05/2008 10:33:53
-- Drop shipment
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=55321 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 8/05/2008 10:33:56
-- Drop shipment
ALTER TABLE AD_OrgInfo ADD DropShip_Warehouse_ID NUMBER(10)
;

-- 8/05/2008 10:35:33
-- Drop shipment
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2008-05-08 10:35:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=9245
;

-- 8/05/2008 10:36:37
-- Drop shipment
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,55321,55416,0,170,TO_DATE('2008-05-08 10:36:36','YYYY-MM-DD HH24:MI:SS'),100,'The (logical) warehouse to use for recording drop ship receipts and shipments.',22,'D','The drop ship warehouse will be used for recording material transactions relating to drop shipments to and from this organization.','Y','Y','Y','N','N','N','N','N','Drop Ship Warehouse',65,0,TO_DATE('2008-05-08 10:36:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 8/05/2008 10:36:37
-- Drop shipment
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55416 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 8/05/2008 10:39:12
-- Drop shipment
UPDATE AD_Column SET AD_Val_Rule_ID=189,Updated=TO_DATE('2008-05-08 10:39:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=55321
;

-- 8/05/2008 10:39:12
-- Drop shipment
UPDATE AD_Field SET Name='Drop Ship Warehouse', Description='The (logical) warehouse to use for recording drop ship receipts and shipments.', Help='The drop ship warehouse will be used for recording material transactions relating to drop shipments to and from this organization.' WHERE AD_Column_ID=55321 AND IsCentrallyMaintained='Y'
;

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,55317,124,55419,0,257,TO_DATE('2008-05-16 09:33:44','YYYY-MM-DD HH24:MI:SS'),100,'Drop Shipments are sent from the Vendor directly to the Customer',0,'D','Drop Shipments do not cause any Inventory reservations or movements as the Shipment is from the Vendor''s inventory. The Shipment of the Vendor to the Customer must be confirmed.','Y','Y','Y','N','N','N','N','N','Drop Shipment',282,0,TO_DATE('2008-05-16 09:33:44','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55419 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;


INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,55319,55425,0,296,TO_DATE('2008-05-16 09:43:45','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner Location for shipping to',14,'@IsDropShip@=''Y''','D','Y','Y','Y','N','N','N','N','Y','Drop Shipment Location',246,0,TO_DATE('2008-05-16 09:43:45','YYYY-MM-DD HH24:MI:SS'),100)
;


INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55425 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;


INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,55317,55426,0,296,TO_DATE('2008-05-16 09:44:49','YYYY-MM-DD HH24:MI:SS'),100,'Drop Shipments are sent from the Vendor directly to the Customer',1,'D','Drop Shipments do not cause any Inventory reservations or movements as the Shipment is from the Vendor''s inventory. The Shipment of the Vendor to the Customer must be confirmed.','Y','Y','Y','N','N','N','N','N','Drop Shipment',242,0,TO_DATE('2008-05-16 09:44:49','YYYY-MM-DD HH24:MI:SS'),100)
;


INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55426 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;


INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,55318,55427,0,296,TO_DATE('2008-05-16 09:45:31','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner to ship to',14,'@IsDropShip@=Y''','D','If empty the business partner will be shipped to.','Y','Y','Y','N','N','N','N','N','Drop Shipment Partner',244,0,TO_DATE('2008-05-16 09:45:31','YYYY-MM-DD HH24:MI:SS'),100)
;


INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55427 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;


INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,55320,55428,0,296,TO_DATE('2008-05-16 09:46:13','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner Contact for drop shipment',14,'@IsDropShip@=''Y''','D','Y','Y','Y','N','N','N','N','N','Drop Shipment Contact',248,0,TO_DATE('2008-05-16 09:46:13','YYYY-MM-DD HH24:MI:SS'),100)
;


INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55428 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;



INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,55318,124,55429,0,257,TO_DATE('2008-05-16 09:48:43','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner to ship to',0,'@IsDropShip@=''Y''','D','If empty the business partner will be shipped to.','Y','Y','Y','N','N','N','N','N','Drop Shipment Partner',284,0,TO_DATE('2008-05-16 09:48:43','YYYY-MM-DD HH24:MI:SS'),100)
;


INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55429 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;


INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,55319,124,55430,0,257,TO_DATE('2008-05-16 09:49:38','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner Location for shipping to',14,'@IsDropShip@=''Y''','D','Y','Y','Y','N','N','N','N','Y','Drop Shipment Location',286,0,TO_DATE('2008-05-16 09:49:38','YYYY-MM-DD HH24:MI:SS'),100)
;


INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55430 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;


INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,55320,124,55431,0,257,TO_DATE('2008-05-16 09:50:20','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner Contact for drop shipment',14,'@IsDropShip@=''Y''','D','Y','Y','Y','N','N','N','N','N','Drop Shipment Contact',288,0,TO_DATE('2008-05-16 09:50:20','YYYY-MM-DD HH24:MI:SS'),100)
;


INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55431 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

CREATE OR REPLACE VIEW "M_INOUT_CANDIDATE_V" 
AS 
SELECT	
	o.AD_Client_ID, o.AD_Org_ID, o.C_BPartner_ID, o.C_Order_ID,
	o.DocumentNo, o.DateOrdered, o.C_DocType_ID, 
    o.POReference, o.Description, o.SalesRep_ID,
    l.M_Warehouse_ID,
	SUM((l.QtyOrdered-l.QtyDelivered)*l.PriceActual) AS TotalLines
FROM C_Order o
  INNER JOIN C_OrderLine l ON (o.C_Order_ID=l.C_Order_ID)
WHERE	(o.DocStatus = 'CO' AND o.IsDelivered='N')  --  Status must be CO - not CL/RE
--	not Offers and open Walkin-Receipts
AND o.C_DocType_ID IN (SELECT C_DocType_ID FROM C_DocType
		WHERE DocBaseType='SOO' AND DocSubTypeSO NOT IN ('ON','OB','WR'))
--  Delivery Rule - not manual
AND o.DeliveryRule<>'M'
AND (l.M_Product_ID IS NULL OR EXISTS 
        (SELECT * FROM M_Product p 
        WHERE l.M_Product_ID=p.M_Product_ID AND p.IsExcludeAutoDelivery='N'))
--	we need to ship
AND	l.QtyOrdered <> l.QtyDelivered
-- AND o.IsDropShip='N'
-- no vendor drop shipment order has been raised
AND NOT EXISTS (SELECT 1 FROM C_Order lo
                INNER JOIN C_OrderLine ll ON (ll.C_Order_ID=lo.C_Order_ID)
                WHERE ll.C_Orderline_ID=l.Link_OrderLine_ID
                AND lo.IsDropShip = 'Y')
AND (l.M_Product_ID IS NOT NULL OR l.C_Charge_ID IS NOT NULL)
    --  Not confirmed shipment
    AND NOT EXISTS (SELECT * FROM M_InOutLine iol 
        INNER JOIN M_InOut io ON (iol.M_InOut_ID=io.M_InOut_ID)
        WHERE iol.C_OrderLine_ID=l.C_OrderLine_ID AND io.DocStatus IN ('IP','WC'))
	--
GROUP BY o.AD_Client_ID, o.AD_Org_ID, o.C_BPartner_ID, o.C_Order_ID,
	o.DocumentNo, o.DateOrdered, o.C_DocType_ID,
    o.POReference, o.Description, o.SalesRep_ID, l.M_Warehouse_ID;
    

 
  CREATE OR REPLACE VIEW "C_ORDER_HEADER_V" 
  AS
    SELECT o.AD_Client_ID, o.AD_Org_ID, o.IsActive, o.Created, o.CreatedBy, o.Updated, o.UpdatedBy,
	'en_US' AS AD_Language,
	o.C_Order_ID, o.IsSOTrx, o.DocumentNo, o.DocStatus,	 o.C_DocType_ID,
	o.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID, 
    o.M_Warehouse_ID, wh.C_Location_ID AS Warehouse_Location_ID,
	dt.PrintName AS DocumentType, dt.DocumentNote AS DocumentTypeNote,
	o.SalesRep_ID, COALESCE(ubp.Name, u.Name) AS SalesRep_Name,
	o.DateOrdered, o.DatePromised,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title, bpc.Phone,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID, l.Postal || l.Postal_Add AS Postal,
	bp.ReferenceNo,
    --  Bill to
    o.Bill_BPartner_ID, o.Bill_Location_ID, o.Bill_User_ID,
    bbp.Value AS Bill_BPValue, bbp.TaxID AS Bill_BPTaxID,
    bbp.Name AS Bill_Name, bbp.Name2 AS Bill_Name2,
    bbpc.Title AS Bill_Title, bbpc.Phone AS Bill_Phone,
    NULLIF (bbpc.Name, bbp.Name) AS Bill_ContactName,
    bbpl.C_Location_ID AS Bill_C_Location_ID,
    -- Ship to
    o.IsDropShip,
    COALESCE(o.DropShip_BPartner_ID, o.C_BPartner_ID) AS Ship_BPartner_ID,
    COALESCE(o.DropShip_Location_ID, o.C_BPartner_Location_ID) AS Ship_Location_ID,
    COALESCE(o.DropShip_User_ID, o.AD_User_ID) AS Ship_User_ID,
    COALESCE(dsbp.Value, bp.Value) AS Ship_BPValue, COALESCE(dsbp.TaxID, bp.TaxID) AS Ship_BPTaxID,
    COALESCE(dsbp.Name, bp.Name) AS Ship_Name, COALESCE(dsbp.Name2, bp.Name2) AS Ship_Name2,
    COALESCE(dsbpc.Title, bpc.Title) AS Ship_Title, COALESCE(dsbpc.Phone, bpc.Phone) AS Ship_Phone,
    NULLIF (COALESCE(dsbpc.Name, bpc.Name),COALESCE(dsbp.Name, bp.Name)) AS Ship_ContactName,
    COALESCE(dsbpl.C_Location_ID, bpl.C_Location_ID) AS Ship_C_Location_ID,
	o.Description,
	o.POReference,
	o.C_Currency_ID,
	pt.Name AS PaymentTerm, pt.DocumentNote AS PaymentTermNote,
	o.C_Charge_ID, o.ChargeAmt,
	o.TotalLines,
	o.GrandTotal, o.GrandTotal AS AmtInWords,
	o.M_PriceList_ID,
	o.IsTaxIncluded, o.Volume, o.Weight,
	o.C_Campaign_ID, o.C_Project_ID, o.C_Activity_ID,
	o.M_Shipper_ID, o.DeliveryRule, o.DeliveryViaRule, o.PriorityRule, o.InvoiceRule
FROM C_Order o
	INNER JOIN C_DocType dt ON (o.C_DocType_ID=dt.C_DocType_ID)
    INNER JOIN M_Warehouse wh ON (o.M_Warehouse_ID=wh.M_Warehouse_ID)
	INNER JOIN C_PaymentTerm pt ON (o.C_PaymentTerm_ID=pt.C_PaymentTerm_ID)
	INNER JOIN C_BPartner bp ON (o.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting bpg ON (bp.C_Greeting_ID=bpg.C_Greeting_ID)
	INNER JOIN C_BPartner_Location bpl ON (o.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
    INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (o.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting bpcg ON (bpc.C_Greeting_ID=bpcg.C_Greeting_ID)
	INNER JOIN AD_OrgInfo oi ON (o.AD_Org_ID=oi.AD_Org_ID)
	LEFT OUTER JOIN AD_User u ON (o.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID)
    INNER JOIN C_BPartner bbp ON (o.Bill_BPartner_ID=bbp.C_BPartner_ID)
	INNER JOIN C_BPartner_Location bbpl ON (o.Bill_Location_ID=bbpl.C_BPartner_Location_ID)
	LEFT OUTER JOIN AD_User bbpc ON (o.Bill_User_ID=bbpc.AD_User_ID)
    LEFT OUTER JOIN C_BPartner dsbp ON (o.DropShip_BPartner_ID=dsbp.C_BPartner_ID)
    	LEFT OUTER JOIN C_BPartner_Location dsbpl ON (o.DropShip_Location_ID=dsbpl.C_BPartner_Location_ID)
	LEFT OUTER JOIN AD_User dsbpc ON (o.DropShip_User_ID=dsbpc.AD_User_ID);
 
  CREATE OR REPLACE VIEW "C_ORDER_HEADER_VT"
  AS
  SELECT o.AD_Client_ID, o.AD_Org_ID, o.IsActive, o.Created, o.CreatedBy, o.Updated, o.UpdatedBy,
	dt.AD_Language,
	o.C_Order_ID, o.IsSOTrx, o.DocumentNo, o.DocStatus,	 o.C_DocType_ID,
	o.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID, 
    o.M_Warehouse_ID, wh.C_Location_ID AS Warehouse_Location_ID,
	dt.PrintName AS DocumentType, dt.DocumentNote AS DocumentTypeNote,
	o.SalesRep_ID, COALESCE(ubp.Name, u.Name) AS SalesRep_Name,
	o.DateOrdered, o.DatePromised,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title, bpc.Phone,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID, l.Postal || l.Postal_Add AS Postal,
	bp.ReferenceNo,
    --  Bill to
    o.Bill_BPartner_ID, o.Bill_Location_ID, o.Bill_User_ID,
    bbp.Value AS Bill_BPValue, bbp.TaxID AS Bill_BPTaxID,
    bbp.Name AS Bill_Name, bbp.Name2 AS Bill_Name2,
    bbpc.Title AS Bill_Title, bbpc.Phone AS Bill_Phone,
    NULLIF (bbpc.Name, bbp.Name) AS Bill_ContactName,
    bbpl.C_Location_ID AS Bill_C_Location_ID,
-- Ship to
    o.IsDropShip,
    COALESCE(o.DropShip_BPartner_ID, o.C_BPartner_ID) AS Ship_BPartner_ID,
    COALESCE(o.DropShip_Location_ID, o.C_BPartner_Location_ID) AS Ship_Location_ID,
    COALESCE(o.DropShip_User_ID, o.AD_User_ID) AS Ship_User_ID,
    COALESCE(dsbp.Value, bp.Value) AS Ship_BPValue, COALESCE(dsbp.TaxID, bp.TaxID) AS Ship_BPTaxID,
    COALESCE(dsbp.Name, bp.Name) AS Ship_Name, COALESCE(dsbp.Name2, bp.Name2) AS Ship_Name2,
    COALESCE(dsbpc.Title, bpc.Title) AS Ship_Title, COALESCE(dsbpc.Phone, bpc.Phone) AS Ship_Phone,
       NULLIF (COALESCE(dsbpc.Name, bpc.Name),COALESCE(dsbp.Name, bp.Name)) AS Ship_ContactName,
    COALESCE(dsbpl.C_Location_ID, bpl.C_Location_ID) AS Ship_C_Location_ID,
	o.Description,
	o.POReference,
	o.C_Currency_ID,
	pt.Name AS PaymentTerm, pt.DocumentNote AS PaymentTermNote,
	o.C_Charge_ID, o.ChargeAmt,
	o.TotalLines,
	o.GrandTotal, o.GrandTotal AS AmtInWords,
	o.M_PriceList_ID,
	o.IsTaxIncluded, o.Volume, o.Weight,
	o.C_Campaign_ID, o.C_Project_ID, o.C_Activity_ID,
	o.M_Shipper_ID, o.DeliveryRule, o.DeliveryViaRule, o.PriorityRule, o.InvoiceRule
FROM C_Order o
	INNER JOIN C_DocType_Trl dt ON (o.C_DocType_ID=dt.C_DocType_ID)
    INNER JOIN M_Warehouse wh ON (o.M_Warehouse_ID=wh.M_Warehouse_ID)
	INNER JOIN C_PaymentTerm_Trl pt ON (o.C_PaymentTerm_ID=pt.C_PaymentTerm_ID AND dt.AD_Language=pt.AD_Language)
	INNER JOIN C_BPartner bp ON (o.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpg ON (bp.C_Greeting_ID=bpg.C_Greeting_ID AND dt.AD_Language=bpg.AD_Language)
	INNER JOIN C_BPartner_Location bpl ON (o.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
    INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (o.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpcg ON (bpc.C_Greeting_ID=bpcg.C_Greeting_ID AND dt.AD_Language=bpcg.AD_Language)
	INNER JOIN AD_OrgInfo oi ON (o.AD_Org_ID=oi.AD_Org_ID)
	LEFT OUTER JOIN AD_User u ON (o.SalesRep_ID=u.AD_User_ID)
	LEFT OUTER JOIN C_BPartner ubp ON (u.C_BPartner_ID=ubp.C_BPartner_ID)
    INNER JOIN C_BPartner bbp ON (o.Bill_BPartner_ID=bbp.C_BPartner_ID)
	INNER JOIN C_BPartner_Location bbpl ON (o.Bill_Location_ID=bbpl.C_BPartner_Location_ID)
	LEFT OUTER JOIN AD_User bbpc ON (o.Bill_User_ID=bbpc.AD_User_ID)
     LEFT OUTER JOIN C_BPartner dsbp ON (o.DropShip_BPartner_ID=dsbp.C_BPartner_ID)
    	LEFT OUTER JOIN C_BPartner_Location dsbpl ON (o.DropShip_Location_ID=dsbpl.C_BPartner_Location_ID)
	LEFT OUTER JOIN AD_User dsbpc ON (o.DropShip_User_ID=dsbpc.AD_User_ID);
 
  CREATE OR REPLACE VIEW "M_INOUT_HEADER_V" 
  AS
  SELECT io.AD_Client_ID, io.AD_Org_ID, io.IsActive, io.Created, io.CreatedBy, io.Updated, io.UpdatedBy,
	'en_US' AS AD_Language,
	io.M_InOut_ID, io.IsSOTrx, io.DocumentNo, io.DocStatus,	 io.C_DocType_ID,
	io.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID,
	io.M_Warehouse_ID, wh.C_Location_ID AS Warehouse_Location_ID,
	dt.PrintName AS DocumentType, dt.DocumentNote AS DocumentTypeNote,
	io.C_Order_ID, 
	io.MovementDate, io.MovementType,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title, bpc.Phone,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID, l.Postal || l.Postal_Add AS Postal,
	bp.ReferenceNo,
	io.Description,
	io.POReference,
	io.DateOrdered, io.Volume, io.Weight,
	io.M_Shipper_ID, io.DeliveryRule, io.DeliveryViaRule, io.PriorityRule,
    	-- Ship to
   	 io.IsDropShip,
    	COALESCE(io.DropShip_BPartner_ID, io.C_BPartner_ID) AS Ship_BPartner_ID,
    	COALESCE(io.DropShip_Location_ID, io.C_BPartner_Location_ID) AS Ship_Location_ID,
    	COALESCE(io.DropShip_User_ID, io.AD_User_ID) AS Ship_User_ID,
    	COALESCE(dsbp.Value, bp.Value) AS Ship_BPValue, COALESCE(dsbp.TaxID, bp.TaxID) AS Ship_BPTaxID,
   	 COALESCE(dsbp.Name, bp.Name) AS Ship_Name, COALESCE(dsbp.Name2, bp.Name2) AS Ship_Name2,
    	COALESCE(dsbpc.Title, bpc.Title) AS Ship_Title, COALESCE(dsbpc.Phone, bpc.Phone) AS Ship_Phone,
    	 NULLIF (COALESCE(dsbpc.Name, bpc.Name),COALESCE(dsbp.Name, bp.Name)) AS Ship_ContactName,
    	COALESCE(dsbpl.C_Location_ID, bpl.C_Location_ID) AS Ship_C_Location_ID
FROM M_InOut io
	INNER JOIN C_DocType dt ON (io.C_DocType_ID=dt.C_DocType_ID)
	INNER JOIN C_BPartner bp ON (io.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting bpg ON (bp.C_Greeting_ID=bpg.C_Greeting_ID)
	INNER JOIN C_BPartner_Location bpl ON (io.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
    INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (io.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting bpcg ON (bpc.C_Greeting_ID=bpcg.C_Greeting_ID)
	INNER JOIN AD_OrgInfo oi ON (io.AD_Org_ID=oi.AD_Org_ID)
	INNER JOIN M_Warehouse wh ON (io.M_Warehouse_ID=wh.M_Warehouse_ID)
    LEFT OUTER JOIN C_BPartner dsbp ON (io.DropShip_BPartner_ID=dsbp.C_BPartner_ID)
    	LEFT OUTER JOIN C_BPartner_Location dsbpl ON (io.DropShip_Location_ID=dsbpl.C_BPartner_Location_ID)
	LEFT OUTER JOIN AD_User dsbpc ON (io.DropShip_User_ID=dsbpc.AD_User_ID);
 
  CREATE OR REPLACE VIEW "M_INOUT_HEADER_VT"
  AS
  SELECT io.AD_Client_ID, io.AD_Org_ID, io.IsActive, io.Created, io.CreatedBy, io.Updated, io.UpdatedBy,
	dt.AD_Language,
	io.M_InOut_ID, io.IsSOTrx, io.DocumentNo, io.DocStatus,	 io.C_DocType_ID,
	io.C_BPartner_ID, bp.Value AS BPValue, bp.TaxID AS BPTaxID, bp.NAICS, bp.DUNS,
	oi.C_Location_ID AS Org_Location_ID, oi.TaxID,
	io.M_Warehouse_ID, wh.C_Location_ID AS Warehouse_Location_ID,
	dt.PrintName AS DocumentType, dt.DocumentNote AS DocumentTypeNote,
	io.C_Order_ID, bpc.Phone,
	io.MovementDate, io.MovementType,
	bpg.Greeting AS BPGreeting,
	bp.Name, bp.Name2,
	bpcg.Greeting AS BPContactGreeting,
	bpc.Title,
	NULLIF (bpc.Name, bp.Name) AS ContactName,
	bpl.C_Location_ID, l.Postal || l.Postal_Add AS Postal,
	bp.ReferenceNo,
	io.Description,
	io.POReference,
	io.DateOrdered, io.Volume, io.Weight,
	io.M_Shipper_ID, io.DeliveryRule, io.DeliveryViaRule, io.PriorityRule,
    	-- Ship to
   	 io.IsDropShip,
    	COALESCE(io.DropShip_BPartner_ID, io.C_BPartner_ID) AS Ship_BPartner_ID,
    	COALESCE(io.DropShip_Location_ID, io.C_BPartner_Location_ID) AS Ship_Location_ID,
    	COALESCE(io.DropShip_User_ID, io.AD_User_ID) AS Ship_User_ID,
    	COALESCE(dsbp.Value, bp.Value) AS Ship_BPValue, COALESCE(dsbp.TaxID, bp.TaxID) AS Ship_BPTaxID,
   	 COALESCE(dsbp.Name, bp.Name) AS Ship_Name, COALESCE(dsbp.Name2, bp.Name2) AS Ship_Name2,
    	COALESCE(dsbpc.Title, bpc.Title) AS Ship_Title, COALESCE(dsbpc.Phone, bpc.Phone) AS Ship_Phone,
    	 NULLIF (COALESCE(dsbpc.Name, bpc.Name),COALESCE(dsbp.Name, bp.Name)) AS Ship_ContactName,
    	COALESCE(dsbpl.C_Location_ID, bpl.C_Location_ID) AS Ship_C_Location_ID
FROM M_InOut io
	INNER JOIN C_DocType_Trl dt ON (io.C_DocType_ID=dt.C_DocType_ID)
	INNER JOIN C_BPartner bp ON (io.C_BPartner_ID=bp.C_BPartner_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpg ON (bp.C_Greeting_ID=bpg.C_Greeting_ID AND dt.AD_Language=bpg.AD_Language)
	INNER JOIN C_BPartner_Location bpl ON (io.C_BPartner_Location_ID=bpl.C_BPartner_Location_ID)
    INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)
	LEFT OUTER JOIN AD_User bpc ON (io.AD_User_ID=bpc.AD_User_ID)
	LEFT OUTER JOIN C_Greeting_Trl bpcg ON (bpc.C_Greeting_ID=bpcg.C_Greeting_ID AND dt.AD_Language=bpcg.AD_Language)
	INNER JOIN AD_OrgInfo oi ON (io.AD_Org_ID=oi.AD_Org_ID)
	INNER JOIN M_Warehouse wh ON (io.M_Warehouse_ID=wh.M_Warehouse_ID)
    LEFT OUTER JOIN C_BPartner dsbp ON (io.DropShip_BPartner_ID=dsbp.C_BPartner_ID)
    	LEFT OUTER JOIN C_BPartner_Location dsbpl ON (io.DropShip_Location_ID=dsbpl.C_BPartner_Location_ID)
	LEFT OUTER JOIN AD_User dsbpc ON (io.DropShip_User_ID=dsbpc.AD_User_ID);
 