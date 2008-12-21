-- INSERTING into AD_ELEMENT
Insert into AD_ELEMENT (AD_ELEMENT_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,CREATEDBY,UPDATED,UPDATEDBY,COLUMNNAME,ENTITYTYPE,NAME,PRINTNAME,DESCRIPTION,HELP,PO_NAME,PO_PRINTNAME,PO_DESCRIPTION,PO_HELP) values (53460,0,0,'Y',TO_TIMESTAMP('01/MAY/08','DD/MON/RR'),100,TO_TIMESTAMP('01/MAY/08','DD/MON/RR'),100,'DropShip_User_ID','U','Drop Shipment Contact','Drop Shipment Contact','Business Partner Contact for drop shipment',null,null,null,null,null);
Insert into AD_ELEMENT (AD_ELEMENT_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,CREATEDBY,UPDATED,UPDATEDBY,COLUMNNAME,ENTITYTYPE,NAME,PRINTNAME,DESCRIPTION,HELP,PO_NAME,PO_PRINTNAME,PO_DESCRIPTION,PO_HELP) values (53459,0,0,'Y',TO_TIMESTAMP('01/MAY/08','DD/MON/RR'),100,TO_TIMESTAMP('07/MAY/08','DD/MON/RR'),100,'DropShip_Location_ID','D','Drop Shipment Location','Drop Shipment Location','Business Partner Location for shipping to',null,null,null,null,null);
Insert into AD_ELEMENT (AD_ELEMENT_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,CREATEDBY,UPDATED,UPDATEDBY,COLUMNNAME,ENTITYTYPE,NAME,PRINTNAME,DESCRIPTION,HELP,PO_NAME,PO_PRINTNAME,PO_DESCRIPTION,PO_HELP) values (53458,0,0,'Y',TO_TIMESTAMP('01/MAY/08','DD/MON/RR'),100,TO_TIMESTAMP('01/MAY/08','DD/MON/RR'),100,'DropShip_BPartner_ID','U','Drop Shipment Partner','Drop Shipment  Partner','Business Partner to ship to','If empty the business partner will be shipped to.',null,null,null,null);

-- INSERTING into AD_Column
Insert into AD_Column (AD_COLUMN_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,UPDATED,CREATEDBY,UPDATEDBY,NAME,DESCRIPTION,HELP,VERSION,ENTITYTYPE,COLUMNNAME,AD_TABLE_ID,AD_REFERENCE_ID,AD_REFERENCE_VALUE_ID,AD_VAL_RULE_ID,FIELDLENGTH,DEFAULTVALUE,ISKEY,ISPARENT,ISMANDATORY,ISUPDATEABLE,READONLYLOGIC,ISIDENTIFIER,SEQNO,ISTRANSLATED,ISENCRYPTED,CALLOUT,VFORMAT,VALUEMIN,VALUEMAX,ISSELECTIONCOLUMN,AD_ELEMENT_ID,AD_PROCESS_ID,ISSYNCDATABASE,ISALWAYSUPDATEABLE,COLUMNSQL,MANDATORYLOGIC,INFOFACTORYCLASS) values (55314,0,0,'Y',TO_TIMESTAMP('07/MAY/08','DD/MON/RR'),TO_TIMESTAMP('07/MAY/08','DD/MON/RR'),100,100,'Drop Shipment Partner','Business Partner to ship to','If empty the business partner will be shipped to.',1,'D','DropShip_BPartner_ID',259,30,138,230,22,null,'N','N','N','Y',null,'N',0,'N','N',null,null,null,null,'N',53458,null,'N','N',null,null,null);

-- 7/05/2008 11:35:44
-- Drop shipment
ALTER TABLE C_Order ADD DropShip_BPartner_ID NUMERIC(10)
;

-- 7/05/2008 11:40:02
-- Drop shipment
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52022,'C_BPartner_Location.C_BPartner_ID=@DropShip_BPartner_ID@ AND C_BPartner_Location.IsShipTo=''Y'' AND C_BPartner_Location.IsActive=''Y''',TO_TIMESTAMP('2008-05-07 11:39:57','YYYY-MM-DD HH24:MI:SS'),100,'U','Y','C_BPartner_Loc Ship - Drop Ship BPartner','S',TO_TIMESTAMP('2008-05-07 11:39:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- INSERTING into AD_Column
Insert into AD_Column (AD_COLUMN_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,UPDATED,CREATEDBY,UPDATEDBY,NAME,DESCRIPTION,HELP,VERSION,ENTITYTYPE,
COLUMNNAME,AD_TABLE_ID,AD_REFERENCE_ID,AD_REFERENCE_VALUE_ID,AD_VAL_RULE_ID,FIELDLENGTH,DEFAULTVALUE,ISKEY,ISPARENT,ISMANDATORY,
ISUPDATEABLE,READONLYLOGIC,ISIDENTIFIER,SEQNO,ISTRANSLATED,ISENCRYPTED,CALLOUT,VFORMAT,VALUEMIN,VALUEMAX,ISSELECTIONCOLUMN,
AD_ELEMENT_ID,AD_PROCESS_ID,ISSYNCDATABASE,ISALWAYSUPDATEABLE,COLUMNSQL,MANDATORYLOGIC,INFOFACTORYCLASS) 
values (55315,0,0,'Y',TO_TIMESTAMP('07/MAY/08','DD/MON/RR'),TO_TIMESTAMP('07/MAY/08','DD/MON/RR'),100,100,'Drop Shipment Location',
'Business Partner Location for shipping to',null,1,'D','DropShip_Location_ID',259,18,159,52022,22,null,'N','N','N','Y',
null,'N',0,'N','N',null,null,null,null,'N',53459,null,'N','N',null,null,null);

-- 7/05/2008 11:41:42
-- Drop shipment
ALTER TABLE C_Order ADD DropShip_Location_ID NUMERIC(10)
;

-- INSERTING 
Insert into AD_Column (AD_COLUMN_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,UPDATED,CREATEDBY,UPDATEDBY,NAME,DESCRIPTION,
HELP,VERSION,ENTITYTYPE,COLUMNNAME,AD_TABLE_ID,AD_REFERENCE_ID,AD_REFERENCE_VALUE_ID,AD_VAL_RULE_ID,FIELDLENGTH,
DEFAULTVALUE,ISKEY,ISPARENT,ISMANDATORY,ISUPDATEABLE,READONLYLOGIC,ISIDENTIFIER,SEQNO,ISTRANSLATED,ISENCRYPTED,
CALLOUT,VFORMAT,VALUEMIN,VALUEMAX,ISSELECTIONCOLUMN,AD_ELEMENT_ID,AD_PROCESS_ID,ISSYNCDATABASE,ISALWAYSUPDATEABLE,
COLUMNSQL,MANDATORYLOGIC,INFOFACTORYCLASS)
 values (55316,0,0,'Y',TO_TIMESTAMP('07/MAY/08','DD/MON/RR'),TO_TIMESTAMP('07/MAY/08','DD/MON/RR'),100,100,
'Drop Shipment Contact','Business Partner Contact for drop shipment',null,1,'D','DropShip_User_ID',
259,18,110,168,22,null,'N','N','N','Y',null,'N',0,'N','N',null,null,null,null,'N',53460,null,'N','N',null,null,null);


-- 7/05/2008 11:42:43
-- Drop shipment
ALTER TABLE C_Order ADD DropShip_User_ID NUMERIC(10)
;

-- 7/05/2008 11:46:47
-- Drop shipment
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,55317,2466,0,20,319,'IsDropShip',TO_TIMESTAMP('2008-05-07 11:46:46','YYYY-MM-DD HH24:MI:SS'),100,'N','Drop Shipments are sent from the Vendor directly to the Customer','D',1,'Drop Shipments do not cause any Inventory reservations or movements as the Shipment is from the Vendor''s inventory. The Shipment of the Vendor to the Customer must be confirmed.','Y','N','N','N','N','N','N','N','N','N','Y','Drop Shipment',0,TO_TIMESTAMP('2008-05-07 11:46:46','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 7/05/2008 11:47:15
-- Drop shipment
ALTER TABLE M_InOut ADD IsDropShip CHAR(1) DEFAULT 'N' CHECK (IsDropShip IN ('Y','N'))
;

-- 7/05/2008 11:48:17
-- Drop shipment
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,55318,53458,0,18,138,319,'DropShip_BPartner_ID',TO_TIMESTAMP('2008-05-07 11:48:16','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner to ship to','D',10,'If empty the business partner will be shipped to.','Y','N','N','N','N','N','N','N','N','N','Y','Drop Shipment Partner',0,TO_TIMESTAMP('2008-05-07 11:48:16','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 7/05/2008 11:48:21
-- Drop shipment
ALTER TABLE M_InOut ADD DropShip_BPartner_ID NUMERIC(10)
;


-- 7/05/2008 11:49:16
-- Drop shipment
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,55319,53459,0,18,159,319,120,'DropShip_Location_ID',TO_TIMESTAMP('2008-05-07 11:49:15','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner Location for shipping to','D',10,'Y','N','N','N','N','N','N','N','N','N','Y','Drop Shipment Location',0,TO_TIMESTAMP('2008-05-07 11:49:15','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 7/05/2008 11:49:18
-- Drop shipment
ALTER TABLE M_InOut ADD DropShip_Location_ID NUMERIC(10)
;


-- 7/05/2008 11:49:58
-- Drop shipment
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,
Created,CreatedBy,Description,EntityType,FieldLength,
IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,
IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,55320,53460,0,18,110,319,168,'DropShip_User_ID',
TO_TIMESTAMP('2008-05-07 11:49:57','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner Contact for drop shipment','D',10,
'Y','N','N','N','N','N','N','N','N','N',
'Y','Drop Shipment Contact',0,TO_TIMESTAMP('2008-05-07 11:49:57','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 7/05/2008 11:50:00
-- Drop shipment
ALTER TABLE M_InOut ADD DropShip_User_ID NUMERIC(10)
;

-- 7/05/2008 11:58:45
-- Drop shipment
UPDATE AD_Column SET IsUpdateable='Y',Updated=TO_TIMESTAMP('2008-05-07 11:58:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=11580
;

-- 7/05/2008 12:03:04
-- Drop shipment
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,55314,55410,0,186,TO_TIMESTAMP('2008-05-07 12:03:03','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner to ship to',14,'@IsDropShip@=''Y''','D','If empty the business partner will be shipped to.','Y','Y','Y','N','N','N','N','N','Drop Shipment Partner',192,0,TO_TIMESTAMP('2008-05-07 12:03:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 7/05/2008 12:03:04
-- Drop shipment
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55410 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 7/05/2008 12:04:09
-- Drop shipment
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,55315,130,55411,0,186,TO_TIMESTAMP('2008-05-07 12:04:08','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner Location for shipping to',14,'@IsDropShip@=''Y''','D','Y','Y','Y','N','N','N','N','N','Drop Shipment Location',194,0,TO_TIMESTAMP('2008-05-07 12:04:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 7/05/2008 12:04:09
-- Drop shipment
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55411 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 7/05/2008 12:04:17
-- Drop shipment
UPDATE AD_Field SET AD_FieldGroup_ID=130,Updated=TO_TIMESTAMP('2008-05-07 12:04:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=55410
;

-- 7/05/2008 12:05:27
-- Drop shipment
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,55316,130,55412,0,186,TO_TIMESTAMP('2008-05-07 12:05:26','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner Contact for drop shipment',14,'@IsDropShip@=''Y''','D','Y','Y','Y','N','N','N','N','N','Drop Shipment Contact',196,0,TO_TIMESTAMP('2008-05-07 12:05:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 7/05/2008 12:05:27
-- Drop shipment
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55412 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 7/05/2008 12:08:49
-- Drop shipment
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2008-05-07 12:08:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=55411
;

-- 7/05/2008 12:15:15
-- Drop shipment
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,55314,124,55413,0,294,TO_TIMESTAMP('2008-05-07 12:15:13','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner to ship to',14,'@IsDropShip@=''Y''','D','If empty the business partner will be shipped to.','Y','Y','Y','N','N','N','N','N','Drop Shipment Partner',402,0,TO_TIMESTAMP('2008-05-07 12:15:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 7/05/2008 12:15:15
-- Drop shipment
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55413 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 7/05/2008 12:16:35
-- Drop shipment
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,55315,124,55414,0,294,TO_TIMESTAMP('2008-05-07 12:16:34','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner Location for shipping to',14,'@IsDropShip@=''Y''','D','Y','Y','Y','N','N','N','N','Y','Drop Shipment Location',404,0,TO_TIMESTAMP('2008-05-07 12:16:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 7/05/2008 12:16:35
-- Drop shipment
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55414 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 7/05/2008 12:17:34
-- Drop shipment
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,55316,124,55415,0,294,TO_TIMESTAMP('2008-05-07 12:17:33','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner Contact for drop shipment',14,'@IsDropShip@=''Y''','D','Y','Y','Y','N','N','N','N','N','Drop Shipment Contact',406,0,TO_TIMESTAMP('2008-05-07 12:17:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 7/05/2008 12:17:34
-- Drop shipment
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55415 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 7/05/2008 12:17:50
-- Drop shipment
UPDATE AD_Field SET IsReadOnly='N',Updated=TO_TIMESTAMP('2008-05-07 12:17:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=10123
;


-- 8/05/2008 10:32:31
-- Drop shipment
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53461,0,'DropShip_Warehouse_ID',TO_TIMESTAMP('2008-05-08 10:32:26','YYYY-MM-DD HH24:MI:SS'),100,'The (logical) warehouse to use for recording drop ship receipts and shipments.','D','The drop ship warehouse will be used for recording material transactions relating to drop shipments to and from this organization.','Y','Drop Ship Warehouse','Drop Ship Warehouse',TO_TIMESTAMP('2008-05-08 10:32:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 8/05/2008 10:32:31
-- Drop shipment
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53461 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 8/05/2008 10:33:53
-- Drop shipment
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,55321,53461,0,18,197,228,'DropShip_Warehouse_ID',TO_TIMESTAMP('2008-05-08 10:33:51','YYYY-MM-DD HH24:MI:SS'),100,'The (logical) warehouse to use for recording drop ship receipts and shipments.','D',22,'The drop ship warehouse will be used for recording material transactions relating to drop shipments to and from this organization.','Y','N','N','N','N','N','N','N','N','N','Y','Drop Ship Warehouse',0,TO_TIMESTAMP('2008-05-08 10:33:51','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 8/05/2008 10:33:53
-- Drop shipment
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=55321 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 8/05/2008 10:33:56
-- Drop shipment
ALTER TABLE AD_OrgInfo ADD DropShip_Warehouse_ID NUMERIC(10)
;

-- 8/05/2008 10:35:33
-- Drop shipment
UPDATE AD_Field SET IsSameLine='N',Updated=TO_TIMESTAMP('2008-05-08 10:35:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=9245
;

-- 8/05/2008 10:36:37
-- Drop shipment
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,55321,55416,0,170,TO_TIMESTAMP('2008-05-08 10:36:36','YYYY-MM-DD HH24:MI:SS'),100,'The (logical) warehouse to use for recording drop ship receipts and shipments.',22,'D','The drop ship warehouse will be used for recording material transactions relating to drop shipments to and from this organization.','Y','Y','Y','N','N','N','N','N','Drop Ship Warehouse',65,0,TO_TIMESTAMP('2008-05-08 10:36:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 8/05/2008 10:36:37
-- Drop shipment
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55416 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 8/05/2008 10:39:12
-- Drop shipment
UPDATE AD_Column SET AD_Val_Rule_ID=189,Updated=TO_TIMESTAMP('2008-05-08 10:39:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=55321
;

-- 8/05/2008 10:39:12
-- Drop shipment
UPDATE AD_Field SET Name='Drop Ship Warehouse', Description='The (logical) warehouse to use for recording drop ship receipts and shipments.', Help='The drop ship warehouse will be used for recording material transactions relating to drop shipments to and from this organization.' WHERE AD_Column_ID=55321 AND IsCentrallyMaintained='Y'
;

INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,55317,124,55419,0,257,TO_TIMESTAMP('2008-05-16 09:33:44','YYYY-MM-DD HH24:MI:SS'),100,'Drop Shipments are sent from the Vendor directly to the Customer',0,'D','Drop Shipments do not cause any Inventory reservations or movements as the Shipment is from the Vendor''s inventory. The Shipment of the Vendor to the Customer must be confirmed.','Y','Y','Y','N','N','N','N','N','Drop Shipment',282,0,TO_TIMESTAMP('2008-05-16 09:33:44','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55419 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;


INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,55319,55425,0,296,TO_TIMESTAMP('2008-05-16 09:43:45','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner Location for shipping to',14,'@IsDropShip@=''Y''','D','Y','Y','Y','N','N','N','N','Y','Drop Shipment Location',246,0,TO_TIMESTAMP('2008-05-16 09:43:45','YYYY-MM-DD HH24:MI:SS'),100)
;


INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55425 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;


INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,55317,55426,0,296,TO_TIMESTAMP('2008-05-16 09:44:49','YYYY-MM-DD HH24:MI:SS'),100,'Drop Shipments are sent from the Vendor directly to the Customer',1,'D','Drop Shipments do not cause any Inventory reservations or movements as the Shipment is from the Vendor''s inventory. The Shipment of the Vendor to the Customer must be confirmed.','Y','Y','Y','N','N','N','N','N','Drop Shipment',242,0,TO_TIMESTAMP('2008-05-16 09:44:49','YYYY-MM-DD HH24:MI:SS'),100)
;


INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55426 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;


INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,55318,55427,0,296,TO_TIMESTAMP('2008-05-16 09:45:31','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner to ship to',14,'@IsDropShip@=Y''','D','If empty the business partner will be shipped to.','Y','Y','Y','N','N','N','N','N','Drop Shipment Partner',244,0,TO_TIMESTAMP('2008-05-16 09:45:31','YYYY-MM-DD HH24:MI:SS'),100)
;


INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55427 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;


INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,55320,55428,0,296,TO_TIMESTAMP('2008-05-16 09:46:13','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner Contact for drop shipment',14,'@IsDropShip@=''Y''','D','Y','Y','Y','N','N','N','N','N','Drop Shipment Contact',248,0,TO_TIMESTAMP('2008-05-16 09:46:13','YYYY-MM-DD HH24:MI:SS'),100)
;


INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55428 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;



INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,55318,124,55429,0,257,TO_TIMESTAMP('2008-05-16 09:48:43','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner to ship to',0,'@IsDropShip@=''Y''','D','If empty the business partner will be shipped to.','Y','Y','Y','N','N','N','N','N','Drop Shipment Partner',284,0,TO_TIMESTAMP('2008-05-16 09:48:43','YYYY-MM-DD HH24:MI:SS'),100)
;


INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55429 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;


INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,55319,124,55430,0,257,TO_TIMESTAMP('2008-05-16 09:49:38','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner Location for shipping to',14,'@IsDropShip@=''Y''','D','Y','Y','Y','N','N','N','N','Y','Drop Shipment Location',286,0,TO_TIMESTAMP('2008-05-16 09:49:38','YYYY-MM-DD HH24:MI:SS'),100)
;


INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55430 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;


INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_FieldGroup_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,55320,124,55431,0,257,TO_TIMESTAMP('2008-05-16 09:50:20','YYYY-MM-DD HH24:MI:SS'),100,'Business Partner Contact for drop shipment',14,'@IsDropShip@=''Y''','D','Y','Y','Y','N','N','N','N','N','Drop Shipment Contact',288,0,TO_TIMESTAMP('2008-05-16 09:50:20','YYYY-MM-DD HH24:MI:SS'),100)
;


INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55431 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;
    
   DROP VIEW m_inout_candidate_v;

CREATE OR REPLACE VIEW m_inout_candidate_v AS 
 SELECT o.ad_client_id, o.ad_org_id, o.c_bpartner_id, o.c_order_id, o.documentno, o.dateordered, o.c_doctype_id, o.poreference, o.description, o.salesrep_id, l.m_warehouse_id, sum((l.qtyordered - l.qtydelivered) * l.priceactual) AS totallines
   FROM c_order o
   JOIN c_orderline l ON o.c_order_id = l.c_order_id
  WHERE o.docstatus = 'CO'::bpchar AND o.isdelivered = 'N'::bpchar AND (o.c_doctype_id IN ( SELECT c_doctype.c_doctype_id
      FROM c_doctype
     WHERE c_doctype.docbasetype = 'SOO'::bpchar AND (c_doctype.docsubtypeso <> ALL (ARRAY['ON'::bpchar, 'OB'::bpchar, 'WR'::bpchar])))) AND o.deliveryrule <> 'M'::bpchar AND (l.m_product_id IS NULL OR (EXISTS ( SELECT 1
      FROM m_product p
     WHERE l.m_product_id = p.m_product_id AND p.isexcludeautodelivery = 'N'::bpchar))) AND l.qtyordered <> l.qtydelivered AND (l.m_product_id IS NOT NULL OR l.c_charge_id IS NOT NULL) AND NOT (EXISTS ( SELECT 1
      FROM m_inoutline iol
   JOIN m_inout io ON iol.m_inout_id = io.m_inout_id
  WHERE iol.c_orderline_id = l.c_orderline_id AND (io.docstatus = ANY (ARRAY['IP'::bpchar, 'WC'::bpchar]))))
  GROUP BY o.ad_client_id, o.ad_org_id, o.c_bpartner_id, o.c_order_id, o.documentno, o.dateordered, o.c_doctype_id, o.poreference, o.description, o.salesrep_id, l.m_warehouse_id;

   DROP VIEW c_order_header_v;
   
CREATE OR REPLACE VIEW c_order_header_v AS 
 SELECT o.ad_client_id, o.ad_org_id, o.isactive, o.created, o.createdby, o.updated, o.updatedby, 'en_US'::character varying AS ad_language, o.c_order_id, o.issotrx, o.documentno, o.docstatus, o.c_doctype_id, o.c_bpartner_id, bp.value AS bpvalue, bp.taxid AS bptaxid, bp.naics, bp.duns, oi.c_location_id AS org_location_id, oi.taxid, o.m_warehouse_id, wh.c_location_id AS warehouse_location_id, dt.printname AS documenttype, dt.documentnote AS documenttypenote, o.salesrep_id, COALESCE(ubp.name, u.name) AS salesrep_name, o.dateordered, o.datepromised, bpg.greeting AS bpgreeting, bp.name, bp.name2, bpcg.greeting AS bpcontactgreeting, bpc.title, bpc.phone, NULLIF(bpc.name::text, bp.name::text) AS contactname, bpl.c_location_id, l.postal::text || l.postal_add::text AS postal, bp.referenceno, o.bill_bpartner_id, o.bill_location_id, o.bill_user_id, bbp.value AS bill_bpvalue, bbp.taxid AS bill_bptaxid, bbp.name AS bill_name, bbp.name2 AS bill_name2, bbpc.title AS bill_title, bbpc.phone AS bill_phone, NULLIF(bbpc.name::text, bbp.name::text) AS bill_contactname, bbpl.c_location_id AS bill_c_location_id, o.isdropship, COALESCE(o.dropship_bpartner_id, o.c_bpartner_id) AS ship_bpartner_id, COALESCE(o.dropship_location_id, o.c_bpartner_location_id) AS ship_location_id, COALESCE(o.dropship_user_id, o.ad_user_id) AS ship_user_id, COALESCE(dsbp.value, bp.value) AS ship_bpvalue, COALESCE(dsbp.taxid, bp.taxid) AS ship_bptaxid, COALESCE(dsbp.name, bp.name) AS ship_name, COALESCE(dsbp.name2, bp.name2) AS ship_name2, COALESCE(dsbpc.title, bpc.title) AS ship_title, COALESCE(dsbpc.phone, bpc.phone) AS ship_phone, NULLIF (COALESCE(dsbpc.name, bpc.name),COALESCE(dsbp.name, bp.name)) AS ship_contactname, COALESCE(dsbpl.c_location_id, bpl.c_location_id) AS ship_c_location_id, o.description, o.poreference, o.c_currency_id, pt.name AS paymentterm, pt.documentnote AS paymenttermnote, o.c_charge_id, o.chargeamt, o.totallines, o.grandtotal, o.grandtotal AS amtinwords, o.m_pricelist_id, o.istaxincluded, o.volume, o.weight, o.c_campaign_id, o.c_project_id, o.c_activity_id, o.m_shipper_id, o.deliveryrule, o.deliveryviarule, o.priorityrule, o.invoicerule
   FROM c_order o
   JOIN c_doctype dt ON o.c_doctype_id = dt.c_doctype_id
   JOIN m_warehouse wh ON o.m_warehouse_id = wh.m_warehouse_id
   JOIN c_paymentterm pt ON o.c_paymentterm_id = pt.c_paymentterm_id
   JOIN c_bpartner bp ON o.c_bpartner_id = bp.c_bpartner_id
   LEFT JOIN c_greeting bpg ON bp.c_greeting_id = bpg.c_greeting_id
   JOIN c_bpartner_location bpl ON o.c_bpartner_location_id = bpl.c_bpartner_location_id
   JOIN c_location l ON bpl.c_location_id = l.c_location_id
   LEFT JOIN ad_user bpc ON o.ad_user_id = bpc.ad_user_id
   LEFT JOIN c_greeting bpcg ON bpc.c_greeting_id = bpcg.c_greeting_id
   JOIN ad_orginfo oi ON o.ad_org_id = oi.ad_org_id
   LEFT JOIN ad_user u ON o.salesrep_id = u.ad_user_id
   LEFT JOIN c_bpartner ubp ON u.c_bpartner_id = ubp.c_bpartner_id
   JOIN c_bpartner bbp ON o.bill_bpartner_id = bbp.c_bpartner_id
   JOIN c_bpartner_location bbpl ON o.bill_location_id = bbpl.c_bpartner_location_id
   LEFT JOIN ad_user bbpc ON o.bill_user_id = bbpc.ad_user_id
   LEFT JOIN c_bpartner dsbp ON (o.dropship_bpartner_id=dsbp.c_bpartner_id)
   LEFT JOIN c_bpartner_location dsbpl ON (o.dropship_location_id=dsbpl.c_bpartner_location_id)
   LEFT JOIN ad_user dsbpc ON (o.dropship_user_id=dsbpc.ad_user_id);

   DROP VIEW c_order_header_vt;
 
CREATE OR REPLACE VIEW c_order_header_vt AS 
 SELECT o.ad_client_id, o.ad_org_id, o.isactive, o.created, o.createdby, o.updated, o.updatedby, dt.ad_language, o.c_order_id, o.issotrx, o.documentno, o.docstatus, o.c_doctype_id, o.c_bpartner_id, bp.value AS bpvalue, bp.taxid AS bptaxid, bp.naics, bp.duns, oi.c_location_id AS org_location_id, oi.taxid, o.m_warehouse_id, wh.c_location_id AS warehouse_location_id, dt.printname AS documenttype, dt.documentnote AS documenttypenote, o.salesrep_id, COALESCE(ubp.name, u.name) AS salesrep_name, o.dateordered, o.datepromised, bpg.greeting AS bpgreeting, bp.name, bp.name2, bpcg.greeting AS bpcontactgreeting, bpc.title, bpc.phone, NULLIF(bpc.name::text, bp.name::text) AS contactname, bpl.c_location_id, l.postal::text || l.postal_add::text AS postal, bp.referenceno, o.bill_bpartner_id, o.bill_location_id, o.bill_user_id, bbp.value AS bill_bpvalue, bbp.taxid AS bill_bptaxid, bbp.name AS bill_name, bbp.name2 AS bill_name2, bbpc.title AS bill_title, bbpc.phone AS bill_phone, NULLIF(bbpc.name::text, bbp.name::text) AS bill_contactname, bbpl.c_location_id AS bill_c_location_id, o.isdropship, COALESCE(o.dropship_bpartner_id, o.c_bpartner_id) AS ship_bpartner_id, COALESCE(o.dropship_location_id, o.c_bpartner_location_id) AS ship_location_id, COALESCE(o.dropship_user_id, o.ad_user_id) AS ship_user_id, COALESCE(dsbp.value, bp.value) AS ship_bpvalue, COALESCE(dsbp.taxid, bp.taxid) AS ship_bptaxid, COALESCE(dsbp.name, bp.name) AS ship_name, COALESCE(dsbp.name2, bp.name2) AS ship_name2, COALESCE(dsbpc.title, bpc.title) AS ship_title, COALESCE(dsbpc.phone, bpc.phone) AS ship_phone, NULLIF (COALESCE(dsbpc.name, bpc.name),COALESCE(dsbp.name, bp.name)) AS ship_contactname, COALESCE(dsbpl.c_location_id, bpl.c_location_id) AS ship_c_location_id, o.description, o.poreference, o.c_currency_id, pt.name AS paymentterm, pt.documentnote AS paymenttermnote, o.c_charge_id, o.chargeamt, o.totallines, o.grandtotal, o.grandtotal AS amtinwords, o.m_pricelist_id, o.istaxincluded, o.volume, o.weight, o.c_campaign_id, o.c_project_id, o.c_activity_id, o.m_shipper_id, o.deliveryrule, o.deliveryviarule, o.priorityrule, o.invoicerule
   FROM c_order o
   JOIN c_doctype_trl dt ON o.c_doctype_id = dt.c_doctype_id
   JOIN m_warehouse wh ON o.m_warehouse_id = wh.m_warehouse_id
   JOIN c_paymentterm_trl pt ON o.c_paymentterm_id = pt.c_paymentterm_id AND dt.ad_language::text = pt.ad_language::text
   JOIN c_bpartner bp ON o.c_bpartner_id = bp.c_bpartner_id
   LEFT JOIN c_greeting_trl bpg ON bp.c_greeting_id = bpg.c_greeting_id AND dt.ad_language::text = bpg.ad_language::text
   JOIN c_bpartner_location bpl ON o.c_bpartner_location_id = bpl.c_bpartner_location_id
   JOIN c_location l ON bpl.c_location_id = l.c_location_id
   LEFT JOIN ad_user bpc ON o.ad_user_id = bpc.ad_user_id
   LEFT JOIN c_greeting_trl bpcg ON bpc.c_greeting_id = bpcg.c_greeting_id AND dt.ad_language::text = bpcg.ad_language::text
   JOIN ad_orginfo oi ON o.ad_org_id = oi.ad_org_id
   LEFT JOIN ad_user u ON o.salesrep_id = u.ad_user_id
   LEFT JOIN c_bpartner ubp ON u.c_bpartner_id = ubp.c_bpartner_id
   JOIN c_bpartner bbp ON o.bill_bpartner_id = bbp.c_bpartner_id
   JOIN c_bpartner_location bbpl ON o.bill_location_id = bbpl.c_bpartner_location_id
   LEFT JOIN ad_user bbpc ON o.bill_user_id = bbpc.ad_user_id
   LEFT JOIN c_bpartner dsbp ON (o.dropship_bpartner_id=dsbp.c_bpartner_id)
   LEFT JOIN c_bpartner_location dsbpl ON (o.dropship_location_id=dsbpl.c_bpartner_location_id)
   LEFT JOIN ad_user dsbpc ON (o.dropship_user_id=dsbpc.ad_user_id);

   DROP VIEW m_inout_header_v;

CREATE OR REPLACE VIEW m_inout_header_v AS 
 SELECT io.ad_client_id, io.ad_org_id, io.isactive, io.created, io.createdby, io.updated, io.updatedby, 'en_US'::character varying AS ad_language, io.m_inout_id, io.issotrx, io.documentno, io.docstatus, io.c_doctype_id, io.c_bpartner_id, bp.value AS bpvalue, bp.taxid AS bptaxid, bp.naics, bp.duns, oi.c_location_id AS org_location_id, oi.taxid, io.m_warehouse_id, wh.c_location_id AS warehouse_location_id, dt.printname AS documenttype, dt.documentnote AS documenttypenote, io.c_order_id, io.movementdate, io.movementtype, bpg.greeting AS bpgreeting, bp.name, bp.name2, bpcg.greeting AS bpcontactgreeting, bpc.title, bpc.phone, NULLIF(bpc.name::text, bp.name::text) AS contactname, bpl.c_location_id, l.postal::text || l.postal_add::text AS postal, bp.referenceno, io.description, io.poreference, io.dateordered, io.volume, io.weight, io.m_shipper_id, io.deliveryrule, io.deliveryviarule, io.priorityrule, io.isdropship, COALESCE(io.dropship_bpartner_id, io.c_bpartner_id) AS ship_bpartner_id, COALESCE(io.dropship_location_id, io.c_bpartner_location_id) AS ship_location_id, COALESCE(io.dropship_user_id, io.ad_user_id) AS ship_user_id, COALESCE(dsbp.value, bp.value) AS ship_bpvalue, COALESCE(dsbp.taxid, bp.taxid) AS ship_bptaxid, COALESCE(dsbp.name, bp.name) AS ship_name, COALESCE(dsbp.name2, bp.name2) AS ship_name2, COALESCE(dsbpc.title, bpc.title) AS ship_title, COALESCE(dsbpc.phone, bpc.phone) AS ship_phone, NULLIF (COALESCE(dsbpc.name, bpc.name),COALESCE(dsbp.name, bp.name)) AS ship_contactname, COALESCE(dsbpl.c_location_id, bpl.c_location_id) AS ship_c_location_id
   FROM m_inout io
   JOIN c_doctype dt ON io.c_doctype_id = dt.c_doctype_id
   JOIN c_bpartner bp ON io.c_bpartner_id = bp.c_bpartner_id
   LEFT JOIN c_greeting bpg ON bp.c_greeting_id = bpg.c_greeting_id
   JOIN c_bpartner_location bpl ON io.c_bpartner_location_id = bpl.c_bpartner_location_id
   JOIN c_location l ON bpl.c_location_id = l.c_location_id
   LEFT JOIN ad_user bpc ON io.ad_user_id = bpc.ad_user_id
   LEFT JOIN c_greeting bpcg ON bpc.c_greeting_id = bpcg.c_greeting_id
   JOIN ad_orginfo oi ON io.ad_org_id = oi.ad_org_id
   JOIN m_warehouse wh ON io.m_warehouse_id = wh.m_warehouse_id 
   LEFT JOIN c_bpartner dsbp ON (io.dropship_bpartner_id=dsbp.c_bpartner_id)
   LEFT JOIN c_bpartner_location dsbpl ON (io.dropship_location_id=dsbpl.c_bpartner_location_id)
   LEFT JOIN ad_user dsbpc ON (io.dropship_user_id=dsbpc.ad_user_id);

   DROP VIEW m_inout_header_vt;

CREATE OR REPLACE VIEW m_inout_header_vt AS 
 SELECT io.ad_client_id, io.ad_org_id, io.isactive, io.created, io.createdby, io.updated, io.updatedby, dt.ad_language, io.m_inout_id, io.issotrx, io.documentno, io.docstatus, io.c_doctype_id, io.c_bpartner_id, bp.value AS bpvalue, bp.taxid AS bptaxid, bp.naics, bp.duns, oi.c_location_id AS org_location_id, oi.taxid, io.m_warehouse_id, wh.c_location_id AS warehouse_location_id, dt.printname AS documenttype, dt.documentnote AS documenttypenote, io.c_order_id, bpc.phone, io.movementdate, io.movementtype, bpg.greeting AS bpgreeting, bp.name, bp.name2, bpcg.greeting AS bpcontactgreeting, bpc.title, NULLIF(bpc.name::text, bp.name::text) AS contactname, bpl.c_location_id, l.postal::text || l.postal_add::text AS postal, bp.referenceno, io.description, io.poreference, io.dateordered, io.volume, io.weight, io.m_shipper_id, io.deliveryrule, io.deliveryviarule, io.priorityrule, io.isdropship, COALESCE(io.dropship_bpartner_id, io.c_bpartner_id) AS ship_bpartner_id, COALESCE(io.dropship_location_id, io.c_bpartner_location_id) AS ship_location_id, COALESCE(io.dropship_user_id, io.ad_user_id) AS ship_user_id, COALESCE(dsbp.value, bp.value) AS ship_bpvalue, COALESCE(dsbp.taxid, bp.taxid) AS ship_bptaxid, COALESCE(dsbp.name, bp.name) AS ship_name, COALESCE(dsbp.name2, bp.name2) AS ship_name2, COALESCE(dsbpc.title, bpc.title) AS ship_title, COALESCE(dsbpc.phone, bpc.phone) AS ship_phone, NULLIF (COALESCE(dsbpc.name, bpc.name),COALESCE(dsbp.name, bp.name)) AS ship_contactname, COALESCE(dsbpl.c_location_id, bpl.c_location_id) AS ship_c_location_id
   FROM m_inout io
   JOIN c_doctype_trl dt ON io.c_doctype_id = dt.c_doctype_id
   JOIN c_bpartner bp ON io.c_bpartner_id = bp.c_bpartner_id
   LEFT JOIN c_greeting_trl bpg ON bp.c_greeting_id = bpg.c_greeting_id AND dt.ad_language::text = bpg.ad_language::text
   JOIN c_bpartner_location bpl ON io.c_bpartner_location_id = bpl.c_bpartner_location_id
   JOIN c_location l ON bpl.c_location_id = l.c_location_id
   LEFT JOIN ad_user bpc ON io.ad_user_id = bpc.ad_user_id
   LEFT JOIN c_greeting_trl bpcg ON bpc.c_greeting_id = bpcg.c_greeting_id AND dt.ad_language::text = bpcg.ad_language::text
   JOIN ad_orginfo oi ON io.ad_org_id = oi.ad_org_id
   JOIN m_warehouse wh ON io.m_warehouse_id = wh.m_warehouse_id
   LEFT JOIN c_bpartner dsbp ON (io.dropship_bpartner_id=dsbp.c_bpartner_id)
   LEFT JOIN c_bpartner_location dsbpl ON (io.dropship_location_id=dsbpl.c_bpartner_location_id)
   LEFT JOIN ad_user dsbpc ON (io.dropship_user_id=dsbpc.ad_user_id);


 
