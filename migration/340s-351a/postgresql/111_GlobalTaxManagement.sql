-- Mar 3, 2008 10:10:00 PM CST
-- Tax Global Management
INSERT INTO AD_EntityType (AD_Client_ID,AD_EntityType_ID,AD_Org_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,ModelPackage,Name,Processing,Updated,UpdatedBy,Version) VALUES (0,50001,0,TO_TIMESTAMP('2008-03-03 22:09:57','YYYY-MM-DD HH24:MI:SS'),0,'e-Evolution Libero Global Tax Management','EE04',NULL,'Y','org.eevolution.model','e-Evolution Libero Global Tax Management','N',TO_TIMESTAMP('2008-03-03 22:09:57','YYYY-MM-DD HH24:MI:SS'),0,'1.00')
;

-- Mar 3, 2008 10:11:09 PM CST
-- Tax Global Management
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType) VALUES (0,0,53020,TO_TIMESTAMP('2008-03-03 22:11:07','YYYY-MM-DD HH24:MI:SS'),0,'Tax Groups let you group the business partner with a reference tax.','EE04','Y','N','N','Y','Tax Group','N',TO_TIMESTAMP('2008-03-03 22:11:07','YYYY-MM-DD HH24:MI:SS'),0,'M')
;

-- Mar 3, 2008 10:11:09 PM CST
-- Tax Global Management
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53020 AND EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Window_ID!=t.AD_Window_ID)
;

-- Mar 3, 2008 10:11:09 PM CST
-- Tax Global Management
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,0,53020,TO_TIMESTAMP('2008-03-03 22:11:09','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_TIMESTAMP('2008-03-03 22:11:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:11:09 PM CST
-- Tax Global Management
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,102,53020,TO_TIMESTAMP('2008-03-03 22:11:09','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_TIMESTAMP('2008-03-03 22:11:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:11:09 PM CST
-- Tax Global Management
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,103,53020,TO_TIMESTAMP('2008-03-03 22:11:09','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_TIMESTAMP('2008-03-03 22:11:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:11:09 PM CST
-- Tax Global Management
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,50001,53020,TO_TIMESTAMP('2008-03-03 22:11:09','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_TIMESTAMP('2008-03-03 22:11:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:11:11 PM CST
-- Tax Global Management
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_Window_ID,AccessLevel,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53066,53020,'3',TO_TIMESTAMP('2008-03-03 22:11:10','YYYY-MM-DD HH24:MI:SS'),0,'Tax Group','EE04','N','Y','N','Y','N','N','N','Tax Group','L','C_TaxGroup',TO_TIMESTAMP('2008-03-03 22:11:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:11:11 PM CST
-- Tax Global Management
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53066 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Mar 3, 2008 10:11:15 PM CST
-- Tax Global Management
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53083,TO_TIMESTAMP('2008-03-03 22:11:12','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table C_TaxGroup',1,'Y','N','Y','Y','C_TaxGroup','N',1000000,TO_TIMESTAMP('2008-03-03 22:11:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:11:15 PM CST
-- Tax Global Management
UPDATE AD_Reference SET Description='Character String', EntityType='D', Help=NULL, IsActive='Y', Name='String', ValidationType='D',Updated=TO_TIMESTAMP('2008-03-03 22:11:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=10
;

-- Mar 3, 2008 10:11:15 PM CST
-- Tax Global Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=10
;

-- Mar 3, 2008 10:11:16 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54396,469,0,10,53066,'Name',TO_TIMESTAMP('2008-03-03 22:11:15','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE04',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','Y','N','Y','Name',1,TO_TIMESTAMP('2008-03-03 22:11:15','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:11:16 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54396 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:11:17 PM CST
-- Tax Global Management
CREATE TABLE C_TaxGroup (Name VARCHAR(60) NOT NULL)
;

-- Mar 3, 2008 10:11:18 PM CST
-- Tax Global Management
UPDATE AD_Reference SET Description='Direct Table Access', EntityType='D', Help=NULL, IsActive='Y', Name='Table Direct', ValidationType='D',Updated=TO_TIMESTAMP('2008-03-03 22:11:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=19
;

-- Mar 3, 2008 10:11:18 PM CST
-- Tax Global Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=19
;

-- Mar 3, 2008 10:11:19 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54397,113,0,19,53066,104,'AD_Org_ID',TO_TIMESTAMP('2008-03-03 22:11:18','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','EE04',10,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','Y','N','N','Organization',TO_TIMESTAMP('2008-03-03 22:11:18','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:11:19 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54397 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:11:19 PM CST
-- Tax Global Management
ALTER TABLE C_TaxGroup ADD COLUMN AD_Org_ID NUMERIC(10) DEFAULT NULL NOT NULL
;

-- Mar 3, 2008 10:11:19 PM CST
-- Tax Global Management
UPDATE AD_Reference SET Description='10 Digit Identifier', EntityType='D', Help=NULL, IsActive='Y', Name='ID', ValidationType='D',Updated=TO_TIMESTAMP('2008-03-03 22:11:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=13
;

-- Mar 3, 2008 10:11:19 PM CST
-- Tax Global Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=13
;

-- Mar 3, 2008 10:11:20 PM CST
-- Tax Global Management
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53356,0,'C_TaxGroup_ID',TO_TIMESTAMP('2008-03-03 22:11:19','YYYY-MM-DD HH24:MI:SS'),0,'EE04','Y','C_TaxGroup_ID','C_TaxGroup_ID',TO_TIMESTAMP('2008-03-03 22:11:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:11:20 PM CST
-- Tax Global Management
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53356 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Mar 3, 2008 10:11:21 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54398,53356,0,13,53066,'C_TaxGroup_ID',TO_TIMESTAMP('2008-03-03 22:11:19','YYYY-MM-DD HH24:MI:SS'),0,'EE04',10,'Y','N','N','N','Y','Y','N','N','Y','N','N','C_TaxGroup_ID',TO_TIMESTAMP('2008-03-03 22:11:19','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:11:21 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54398 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:11:21 PM CST
-- Tax Global Management
ALTER TABLE C_TaxGroup ADD COLUMN C_TaxGroup_ID NUMERIC(10) NOT NULL
;

-- Mar 3, 2008 10:11:21 PM CST
-- Tax Global Management
ALTER TABLE C_TaxGroup ADD CONSTRAINT C_TaxGroup_Key PRIMARY KEY (C_TaxGroup_ID)
;

-- Mar 3, 2008 10:11:22 PM CST
-- Tax Global Management
UPDATE AD_Reference SET Description='Date with time', EntityType='D', Help=NULL, IsActive='Y', Name='Date+Time', ValidationType='D',Updated=TO_TIMESTAMP('2008-03-03 22:11:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=16
;

-- Mar 3, 2008 10:11:22 PM CST
-- Tax Global Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=16
;

-- Mar 3, 2008 10:11:22 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54399,245,0,16,53066,'Created',TO_TIMESTAMP('2008-03-03 22:11:22','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE04',29,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','Y','N','N','Created',TO_TIMESTAMP('2008-03-03 22:11:22','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:11:22 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54399 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:11:22 PM CST
-- Tax Global Management
ALTER TABLE C_TaxGroup ADD COLUMN Created TIMESTAMP NOT NULL
;

-- Mar 3, 2008 10:11:23 PM CST
-- Tax Global Management
UPDATE AD_Reference SET Description='Table List', EntityType='D', Help=NULL, IsActive='Y', Name='Table', ValidationType='D',Updated=TO_TIMESTAMP('2008-03-03 22:11:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=18
;

-- Mar 3, 2008 10:11:23 PM CST
-- Tax Global Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=18
;

-- Mar 3, 2008 10:11:23 PM CST
-- Tax Global Management
UPDATE AD_Reference SET Description='User selection', EntityType='D', Help=NULL, IsActive='Y', Name='AD_User', ValidationType='T',Updated=TO_TIMESTAMP('2008-03-03 22:11:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=110
;

-- Mar 3, 2008 10:11:23 PM CST
-- Tax Global Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=110
;

-- Mar 3, 2008 10:11:23 PM CST
-- Tax Global Management
UPDATE AD_Ref_Table SET AD_Table_ID = 114, AD_Display = 213, AD_Key = 212, isValueDisplayed = 'N', OrderByClause = 'AD_User.Name', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 110
;

-- Mar 3, 2008 10:11:24 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54400,246,0,18,110,53066,'CreatedBy',TO_TIMESTAMP('2008-03-03 22:11:23','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE04',10,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Created By',TO_TIMESTAMP('2008-03-03 22:11:23','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:11:24 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54400 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:11:24 PM CST
-- Tax Global Management
ALTER TABLE C_TaxGroup ADD COLUMN CreatedBy NUMERIC(10) NOT NULL
;

-- Mar 3, 2008 10:11:25 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54401,275,0,10,53066,'Description',TO_TIMESTAMP('2008-03-03 22:11:24','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE04',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','N','Y','Description',TO_TIMESTAMP('2008-03-03 22:11:24','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:11:25 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54401 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:11:25 PM CST
-- Tax Global Management
ALTER TABLE C_TaxGroup ADD COLUMN Description VARCHAR(255)
;

-- Mar 3, 2008 10:11:25 PM CST
-- Tax Global Management
UPDATE AD_Reference SET Description='Character String up to 2000 characters', EntityType='D', Help=NULL, IsActive='Y', Name='Text', ValidationType='D',Updated=TO_TIMESTAMP('2008-03-03 22:11:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=14
;

-- Mar 3, 2008 10:11:25 PM CST
-- Tax Global Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=14
;

-- Mar 3, 2008 10:11:26 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54402,326,0,14,53066,'Help',TO_TIMESTAMP('2008-03-03 22:11:25','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','EE04',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','Y','N','Y','Comment/Help',TO_TIMESTAMP('2008-03-03 22:11:25','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:11:26 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54402 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:11:26 PM CST
-- Tax Global Management
ALTER TABLE C_TaxGroup ADD COLUMN Help VARCHAR(2000)
;

-- Mar 3, 2008 10:11:26 PM CST
-- Tax Global Management
UPDATE AD_Reference SET Description='CheckBox', EntityType='D', Help=NULL, IsActive='Y', Name='Yes-No', ValidationType='D',Updated=TO_TIMESTAMP('2008-03-03 22:11:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=20
;

-- Mar 3, 2008 10:11:26 PM CST
-- Tax Global Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=20
;

-- Mar 3, 2008 10:11:27 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54403,348,0,20,53066,'IsActive',TO_TIMESTAMP('2008-03-03 22:11:26','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','EE04',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','Y','N','Y','Active',TO_TIMESTAMP('2008-03-03 22:11:26','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:11:27 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54403 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:11:27 PM CST
-- Tax Global Management
ALTER TABLE C_TaxGroup ADD COLUMN IsActive CHAR(1) CHECK (IsActive IN ('Y','N')) NOT NULL
;

-- Mar 3, 2008 10:11:28 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54404,607,0,16,53066,'Updated',TO_TIMESTAMP('2008-03-03 22:11:27','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE04',29,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated',TO_TIMESTAMP('2008-03-03 22:11:27','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:11:28 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54404 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:11:28 PM CST
-- Tax Global Management
ALTER TABLE C_TaxGroup ADD COLUMN Updated TIMESTAMP NOT NULL
;

-- Mar 3, 2008 10:11:29 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54405,608,0,18,110,53066,'UpdatedBy',TO_TIMESTAMP('2008-03-03 22:11:28','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE04',10,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated By',TO_TIMESTAMP('2008-03-03 22:11:28','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:11:29 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54405 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:11:29 PM CST
-- Tax Global Management
ALTER TABLE C_TaxGroup ADD COLUMN UpdatedBy NUMERIC(10) NOT NULL
;

-- Mar 3, 2008 10:11:29 PM CST
-- Tax Global Management
UPDATE AD_Val_Rule SET Code='AD_Client.AD_Client_ID <> 0', Description=NULL, EntityType='D', IsActive='Y', Name='AD_Client Trx Security validation', Type='S',Updated=TO_TIMESTAMP('2008-03-03 22:11:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Val_Rule_ID=129
;

-- Mar 3, 2008 10:11:30 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54406,102,0,19,53066,129,'AD_Client_ID',TO_TIMESTAMP('2008-03-03 22:11:29','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','EE04',10,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','Y','N','N','Client',TO_TIMESTAMP('2008-03-03 22:11:29','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:11:30 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54406 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:11:30 PM CST
-- Tax Global Management
ALTER TABLE C_TaxGroup ADD COLUMN AD_Client_ID NUMERIC(10) DEFAULT NULL NOT NULL
;

-- Mar 3, 2008 10:11:31 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54407,620,0,10,53066,'Value',TO_TIMESTAMP('2008-03-03 22:11:30','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique','EE04',40,'A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','Y','N','N','Y','N','Y','Search Key',TO_TIMESTAMP('2008-03-03 22:11:30','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:11:31 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54407 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:11:31 PM CST
-- Tax Global Management
ALTER TABLE C_TaxGroup ADD COLUMN Value VARCHAR(40) NOT NULL
;

-- Mar 3, 2008 10:11:32 PM CST
-- Tax Global Management
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,Description,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53075,53066,53020,NULL,TO_TIMESTAMP('2008-03-03 22:11:31','YYYY-MM-DD HH24:MI:SS'),0,'Tax Groups let you group the business partner with a reference tax.','EE04','N','Y','N','N','Y','N','Y','N','N','Tax Group','N',10,0,TO_TIMESTAMP('2008-03-03 22:11:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:11:32 PM CST
-- Tax Global Management
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53075 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Mar 3, 2008 10:11:33 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54398,54432,0,53075,TO_TIMESTAMP('2008-03-03 22:11:32','YYYY-MM-DD HH24:MI:SS'),0,10,'EE04','Y','Y','N','N','N','N','N','C_TaxGroup_ID',0,0,TO_TIMESTAMP('2008-03-03 22:11:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:11:33 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54432 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:11:34 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54406,54433,0,53075,TO_TIMESTAMP('2008-03-03 22:11:33','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',10,'EE04','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_TIMESTAMP('2008-03-03 22:11:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:11:34 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54433 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:11:35 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54397,54434,0,53075,TO_TIMESTAMP('2008-03-03 22:11:34','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',10,'EE04','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_TIMESTAMP('2008-03-03 22:11:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:11:35 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54434 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:11:36 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54407,54435,0,53075,TO_TIMESTAMP('2008-03-03 22:11:35','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique',40,'EE04','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','N','Search Key',30,0,TO_TIMESTAMP('2008-03-03 22:11:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:11:36 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54435 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:11:36 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54396,54436,0,53075,TO_TIMESTAMP('2008-03-03 22:11:36','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',60,'EE04','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',40,0,TO_TIMESTAMP('2008-03-03 22:11:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:11:36 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54436 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:11:37 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54401,54437,0,53075,TO_TIMESTAMP('2008-03-03 22:11:36','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',255,'EE04','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',50,0,TO_TIMESTAMP('2008-03-03 22:11:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:11:37 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54437 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:11:38 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54402,54438,0,53075,TO_TIMESTAMP('2008-03-03 22:11:37','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint',2000,'EE04','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','Comment/Help',60,0,TO_TIMESTAMP('2008-03-03 22:11:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:11:38 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54438 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:11:39 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54403,54439,0,53075,TO_TIMESTAMP('2008-03-03 22:11:38','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE04','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',70,0,TO_TIMESTAMP('2008-03-03 22:11:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:11:39 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54439 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:11:39 PM CST
-- Tax Global Management
UPDATE AD_Table SET AD_Window_ID=138, AccessLevel='2', Description='Tax Category', EntityType='D', Help=NULL, ImportTable=NULL, IsActive='Y', IsChangeLog='N', IsDeleteable='Y', IsHighVolume='N', IsSecurityEnabled='N', IsView='N', Name='Tax Category Trl', ReplicationType='L', TableName='C_TaxCategory_Trl',Updated=TO_TIMESTAMP('2008-03-03 22:11:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=348
;

-- Mar 3, 2008 10:11:41 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=469, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=348, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Name', ColumnSQL=NULL, DefaultValue=NULL, Description='Alphanumeric identifier of the entity', EntityType='D', FieldLength=60, Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='Y', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Name', ReadOnlyLogic=NULL, SeqNo=1, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:11:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=4103
;

-- Mar 3, 2008 10:11:41 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Name', Description='Alphanumeric identifier of the entity', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.' WHERE AD_Column_ID=4103 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:11:41 PM CST
-- Tax Global Management
UPDATE AD_Reference SET Description='Language selection', EntityType='D', Help=NULL, IsActive='Y', Name='AD_Language', ValidationType='T',Updated=TO_TIMESTAMP('2008-03-03 22:11:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=106
;

-- Mar 3, 2008 10:11:41 PM CST
-- Tax Global Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=106
;

-- Mar 3, 2008 10:11:41 PM CST
-- Tax Global Management
UPDATE AD_Ref_Table SET AD_Table_ID = 111, AD_Display = 204, AD_Key = 203, isValueDisplayed = 'N', OrderByClause = '', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 106
;

-- Mar 3, 2008 10:11:42 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=109, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=106, AD_Table_ID=348, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_Language', ColumnSQL=NULL, DefaultValue=NULL, Description='Language for this entity', EntityType='D', FieldLength=6, Help='The Language identifies the language to use for display and formatting', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='Y', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Language', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:11:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=4095
;

-- Mar 3, 2008 10:11:42 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Language', Description='Language for this entity', Help='The Language identifies the language to use for display and formatting' WHERE AD_Column_ID=4095 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:11:42 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=113, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=348, AD_Val_Rule_ID=104, Callout=NULL, ColumnName='AD_Org_ID', ColumnSQL=NULL, DefaultValue='@AD_Org_ID@', Description='Organizational entity within client', EntityType='D', FieldLength=22, Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Organization', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:11:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=4097
;

-- Mar 3, 2008 10:11:42 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Organization', Description='Organizational entity within client', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.' WHERE AD_Column_ID=4097 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:11:43 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=211, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=348, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='C_TaxCategory_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Tax Category', EntityType='D', FieldLength=22, Help='The Tax Category provides a method of grouping similar taxes.  For example, Sales Tax or Value Added Tax.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='Y', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Tax Category', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:11:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=4094
;

-- Mar 3, 2008 10:11:43 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Tax Category', Description='Tax Category', Help='The Tax Category provides a method of grouping similar taxes.  For example, Sales Tax or Value Added Tax.' WHERE AD_Column_ID=4094 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:11:44 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=245, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=348, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Created', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was created', EntityType='D', FieldLength=7, Help='The Created field indicates the date that this record was created.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:11:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=4099
;

-- Mar 3, 2008 10:11:44 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Created', Description='Date this record was created', Help='The Created field indicates the date that this record was created.' WHERE AD_Column_ID=4099 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:11:44 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=246, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=348, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='CreatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who created this records', EntityType='D', FieldLength=22, Help='The Created By field indicates the user who created this record.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created By', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:11:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=4100
;

-- Mar 3, 2008 10:11:44 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Created By', Description='User who created this records', Help='The Created By field indicates the user who created this record.' WHERE AD_Column_ID=4100 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:11:45 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=275, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=348, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Description', ColumnSQL=NULL, DefaultValue=NULL, Description='Optional short description of the record', EntityType='D', FieldLength=255, Help='A description is limited to 255 characters.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Description', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:11:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=4104
;

-- Mar 3, 2008 10:11:45 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Description', Description='Optional short description of the record', Help='A description is limited to 255 characters.' WHERE AD_Column_ID=4104 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:11:45 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=348, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=348, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsActive', ColumnSQL=NULL, DefaultValue='Y', Description='The record is active in the system', EntityType='D', FieldLength=1, Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Active', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:11:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=4098
;

-- Mar 3, 2008 10:11:45 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Active', Description='The record is active in the system', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.' WHERE AD_Column_ID=4098 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:11:46 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=420, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=348, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsTranslated', ColumnSQL=NULL, DefaultValue=NULL, Description='This column is translated', EntityType='D', FieldLength=1, Help='The Translated checkbox indicates if this column is translated.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Translated', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:11:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=4105
;

-- Mar 3, 2008 10:11:46 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Translated', Description='This column is translated', Help='The Translated checkbox indicates if this column is translated.' WHERE AD_Column_ID=4105 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:11:46 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=607, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=348, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Updated', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was updated', EntityType='D', FieldLength=7, Help='The Updated field indicates the date that this record was updated.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:11:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=4101
;

-- Mar 3, 2008 10:11:46 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Updated', Description='Date this record was updated', Help='The Updated field indicates the date that this record was updated.' WHERE AD_Column_ID=4101 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:11:47 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=102, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=348, AD_Val_Rule_ID=129, Callout=NULL, ColumnName='AD_Client_ID', ColumnSQL=NULL, DefaultValue='@AD_Client_ID@', Description='Client/Tenant for this installation.', EntityType='D', FieldLength=22, Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Client', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:11:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=4096
;

-- Mar 3, 2008 10:11:47 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Client', Description='Client/Tenant for this installation.', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.' WHERE AD_Column_ID=4096 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:11:47 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=608, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=348, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='UpdatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who updated this records', EntityType='D', FieldLength=22, Help='The Updated By field indicates the user who updated this record.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated By', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:11:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=4102
;

-- Mar 3, 2008 10:11:47 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Updated By', Description='User who updated this records', Help='The Updated By field indicates the user who updated this record.' WHERE AD_Column_ID=4102 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:11:48 PM CST
-- Tax Global Management
UPDATE AD_Tab SET AD_Table_ID=348, AD_Window_ID=138, CommitWarning=NULL, Description=NULL, EntityType='D', HasTree='N', Help=NULL, ImportFields=NULL, IsActive='Y', IsAdvancedTab='N', IsInfoTab='N', IsInsertRecord='N', IsReadOnly='N', IsSingleRow='Y', IsSortTab='N', IsTranslationTab='Y', Name='Translation', OrderByClause=NULL, Processing='N', SeqNo=20, TabLevel=1, WhereClause=NULL,Updated=TO_TIMESTAMP('2008-03-03 22:11:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=284
;

-- Mar 3, 2008 10:11:48 PM CST
-- Tax Global Management
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=284
;

-- Mar 3, 2008 10:11:48 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=4096, AD_FieldGroup_ID=NULL, AD_Tab_ID=284, Description='Client/Tenant for this installation.', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Client', SeqNo=10, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:11:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=3171
;

-- Mar 3, 2008 10:11:49 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=4097, AD_FieldGroup_ID=NULL, AD_Tab_ID=284, Description='Organizational entity within client', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Organization', SeqNo=20, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:11:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=3173
;

-- Mar 3, 2008 10:11:49 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=4094, AD_FieldGroup_ID=NULL, AD_Tab_ID=284, Description='Tax Category', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='The Tax Category provides a method of grouping similar taxes.  For example, Sales Tax or Value Added Tax.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Tax Category', SeqNo=30, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:11:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=3174
;

-- Mar 3, 2008 10:11:49 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=4095, AD_FieldGroup_ID=NULL, AD_Tab_ID=284, Description='Language for this entity', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='The Language identifies the language to use for display and formatting', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Language', SeqNo=40, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:11:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=3172
;

-- Mar 3, 2008 10:11:50 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=4098, AD_FieldGroup_ID=NULL, AD_Tab_ID=284, Description='The record is active in the system', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Active', SeqNo=50, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:11:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=3176
;

-- Mar 3, 2008 10:11:50 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=4105, AD_FieldGroup_ID=NULL, AD_Tab_ID=284, Description='This column is translated', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help='The Translated checkbox indicates if this column is translated.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Translated', SeqNo=60, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:11:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=3177
;

-- Mar 3, 2008 10:11:50 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=4103, AD_FieldGroup_ID=NULL, AD_Tab_ID=284, Description='Alphanumeric identifier of the entity', DisplayLength=60, DisplayLogic=NULL, EntityType='D', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Name', SeqNo=70, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:11:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=3178
;

-- Mar 3, 2008 10:11:51 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=4104, AD_FieldGroup_ID=NULL, AD_Tab_ID=284, Description='Optional short description of the record', DisplayLength=60, DisplayLogic=NULL, EntityType='D', Help='A description is limited to 255 characters.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Description', SeqNo=80, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:11:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=3175
;

-- Mar 3, 2008 10:11:51 PM CST
-- Tax Global Management
UPDATE AD_Table SET AD_Window_ID=138, AccessLevel='2', Description='Tax Category', EntityType='D', Help=NULL, ImportTable=NULL, IsActive='Y', IsChangeLog='N', IsDeleteable='Y', IsHighVolume='N', IsSecurityEnabled='N', IsView='N', Name='Tax Category', ReplicationType='L', TableName='C_TaxCategory',Updated=TO_TIMESTAMP('2008-03-03 22:11:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=252
;

-- Mar 3, 2008 10:11:52 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=469, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=252, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Name', ColumnSQL=NULL, DefaultValue=NULL, Description='Alphanumeric identifier of the entity', EntityType='D', FieldLength=60, Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='Y', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='Y', IsUpdateable='Y', Name='Name', ReadOnlyLogic=NULL, SeqNo=1, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:11:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=2075
;

-- Mar 3, 2008 10:11:52 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Name', Description='Alphanumeric identifier of the entity', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.' WHERE AD_Column_ID=2075 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:11:52 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=113, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=252, AD_Val_Rule_ID=104, Callout=NULL, ColumnName='AD_Org_ID', ColumnSQL=NULL, DefaultValue='@#AD_Org_ID@', Description='Organizational entity within client', EntityType='D', FieldLength=22, Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Organization', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:11:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=2068
;

-- Mar 3, 2008 10:11:52 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Organization', Description='Organizational entity within client', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.' WHERE AD_Column_ID=2068 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:11:53 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=211, AD_Process_ID=NULL, AD_Reference_ID=13, AD_Reference_Value_ID=NULL, AD_Table_ID=252, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='C_TaxCategory_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Tax Category', EntityType='D', FieldLength=22, Help='The Tax Category provides a method of grouping similar taxes.  For example, Sales Tax or Value Added Tax.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='Y', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Tax Category', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:11:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=2066
;

-- Mar 3, 2008 10:11:53 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Tax Category', Description='Tax Category', Help='The Tax Category provides a method of grouping similar taxes.  For example, Sales Tax or Value Added Tax.' WHERE AD_Column_ID=2066 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:11:53 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=1011, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=252, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='CommodityCode', ColumnSQL=NULL, DefaultValue=NULL, Description='Commodity code used for tax calculation', EntityType='D', FieldLength=20, Help='The Commodity Code indicates a code that is used in tax calculations', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Commodity Code', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:11:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=3396
;

-- Mar 3, 2008 10:11:53 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Commodity Code', Description='Commodity code used for tax calculation', Help='The Commodity Code indicates a code that is used in tax calculations' WHERE AD_Column_ID=3396 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:11:54 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=245, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=252, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Created', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was created', EntityType='D', FieldLength=7, Help='The Created field indicates the date that this record was created.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:11:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=2070
;

-- Mar 3, 2008 10:11:54 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Created', Description='Date this record was created', Help='The Created field indicates the date that this record was created.' WHERE AD_Column_ID=2070 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:11:54 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=246, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=252, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='CreatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who created this records', EntityType='D', FieldLength=22, Help='The Created By field indicates the user who created this record.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created By', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:11:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=2071
;

-- Mar 3, 2008 10:11:54 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Created By', Description='User who created this records', Help='The Created By field indicates the user who created this record.' WHERE AD_Column_ID=2071 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:11:55 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=275, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=252, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Description', ColumnSQL=NULL, DefaultValue=NULL, Description='Optional short description of the record', EntityType='D', FieldLength=255, Help='A description is limited to 255 characters.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='Y', IsUpdateable='Y', Name='Description', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:11:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=2076
;

-- Mar 3, 2008 10:11:55 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Description', Description='Optional short description of the record', Help='A description is limited to 255 characters.' WHERE AD_Column_ID=2076 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:11:56 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=348, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=252, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsActive', ColumnSQL=NULL, DefaultValue='Y', Description='The record is active in the system', EntityType='D', FieldLength=1, Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Active', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:11:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=2069
;

-- Mar 3, 2008 10:11:56 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Active', Description='The record is active in the system', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.' WHERE AD_Column_ID=2069 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:11:56 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=1103, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=252, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsDefault', ColumnSQL=NULL, DefaultValue=NULL, Description='Default value', EntityType='D', FieldLength=1, Help='The Default Checkbox indicates if this record will be used as a default value.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Default', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:11:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=3885
;

-- Mar 3, 2008 10:11:56 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Default', Description='Default value', Help='The Default Checkbox indicates if this record will be used as a default value.' WHERE AD_Column_ID=3885 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:11:57 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=607, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=252, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Updated', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was updated', EntityType='D', FieldLength=7, Help='The Updated field indicates the date that this record was updated.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:11:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=2072
;

-- Mar 3, 2008 10:11:57 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Updated', Description='Date this record was updated', Help='The Updated field indicates the date that this record was updated.' WHERE AD_Column_ID=2072 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:11:57 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=102, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=252, AD_Val_Rule_ID=129, Callout=NULL, ColumnName='AD_Client_ID', ColumnSQL=NULL, DefaultValue='@#AD_Client_ID@', Description='Client/Tenant for this installation.', EntityType='D', FieldLength=22, Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Client', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:11:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=2067
;

-- Mar 3, 2008 10:11:57 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Client', Description='Client/Tenant for this installation.', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.' WHERE AD_Column_ID=2067 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:11:58 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=608, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=252, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='UpdatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who updated this records', EntityType='D', FieldLength=22, Help='The Updated By field indicates the user who updated this record.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated By', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:11:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=2073
;

-- Mar 3, 2008 10:11:58 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Updated By', Description='User who updated this records', Help='The Updated By field indicates the user who updated this record.' WHERE AD_Column_ID=2073 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:11:59 PM CST
-- Tax Global Management
UPDATE AD_Tab SET AD_Table_ID=252, AD_Window_ID=138, CommitWarning=NULL, Description='Tax Category', EntityType='D', HasTree='N', Help='The Tax Category Tab is used to define and maintain Tax Categories.  Each Product is associated with a Tax Category.  This facilitates adapting to changes in taxation.', ImportFields=NULL, IsActive='Y', IsAdvancedTab='N', IsInfoTab='N', IsInsertRecord='Y', IsReadOnly='N', IsSingleRow='Y', IsSortTab='N', IsTranslationTab='N', Name='Tax Category', OrderByClause=NULL, Processing='N', SeqNo=10, TabLevel=0, WhereClause=NULL,Updated=TO_TIMESTAMP('2008-03-03 22:11:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=176
;

-- Mar 3, 2008 10:11:59 PM CST
-- Tax Global Management
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=176
;

-- Mar 3, 2008 10:11:59 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=2066, AD_FieldGroup_ID=NULL, AD_Tab_ID=176, Description='Tax Category', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='The Tax Category provides a method of grouping similar taxes.  For example, Sales Tax or Value Added Tax.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='N', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Tax Category', SeqNo=10, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:11:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=987
;

-- Mar 3, 2008 10:12:00 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=2067, AD_FieldGroup_ID=NULL, AD_Tab_ID=176, Description='Client/Tenant for this installation.', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Client', SeqNo=20, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=988
;

-- Mar 3, 2008 10:12:00 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=2068, AD_FieldGroup_ID=NULL, AD_Tab_ID=176, Description='Organizational entity within client', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Organization', SeqNo=30, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=2042
;

-- Mar 3, 2008 10:12:00 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=2075, AD_FieldGroup_ID=NULL, AD_Tab_ID=176, Description='Alphanumeric identifier of the entity', DisplayLength=60, DisplayLogic=NULL, EntityType='D', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Name', SeqNo=40, SortNo=1,Updated=TO_TIMESTAMP('2008-03-03 22:12:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=990
;

-- Mar 3, 2008 10:12:01 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=2076, AD_FieldGroup_ID=NULL, AD_Tab_ID=176, Description='Optional short description of the record', DisplayLength=60, DisplayLogic=NULL, EntityType='D', Help='A description is limited to 255 characters.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Description', SeqNo=50, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=991
;

-- Mar 3, 2008 10:12:01 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=2069, AD_FieldGroup_ID=NULL, AD_Tab_ID=176, Description='The record is active in the system', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Active', SeqNo=60, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=989
;

-- Mar 3, 2008 10:12:01 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=3885, AD_FieldGroup_ID=NULL, AD_Tab_ID=176, Description='Default value', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help='The Default Checkbox indicates if this record will be used as a default value.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Default', SeqNo=70, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=3077
;

-- Mar 3, 2008 10:12:02 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=3396, AD_FieldGroup_ID=NULL, AD_Tab_ID=176, Description='Commodity code used for tax calculation', DisplayLength=20, DisplayLogic=NULL, EntityType='D', Help='The Commodity Code indicates a code that is used in tax calculations', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Commodity Code', SeqNo=80, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=2586
;

-- Mar 3, 2008 10:12:03 PM CST
-- Tax Global Management
UPDATE AD_Reference SET Description='Number with 4 decimals', EntityType='D', Help=NULL, IsActive='Y', Name='Amount', ValidationType='D',Updated=TO_TIMESTAMP('2008-03-03 22:12:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=12
;

-- Mar 3, 2008 10:12:03 PM CST
-- Tax Global Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=12
;

-- Mar 3, 2008 10:12:03 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=164, AD_Process_ID=NULL, AD_Reference_ID=12, AD_Reference_Value_ID=NULL, AD_Table_ID=820, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AmtSourceCr', ColumnSQL='(SELECT AmtSourceCr FROM Fact_Acct f WHERE f.Fact_Acct_ID=C_TaxDeclarationAcct.Fact_Acct_ID)', DefaultValue=NULL, Description='Source Credit Amount', EntityType='D', FieldLength=11, Help='The Source Credit Amount indicates the credit amount for this line in the source currency.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Source Credit', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14500
;

-- Mar 3, 2008 10:12:03 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Source Credit', Description='Source Credit Amount', Help='The Source Credit Amount indicates the credit amount for this line in the source currency.' WHERE AD_Column_ID=14500 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:04 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=165, AD_Process_ID=NULL, AD_Reference_ID=12, AD_Reference_Value_ID=NULL, AD_Table_ID=820, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AmtSourceDr', ColumnSQL='(SELECT AmtSourceDr FROM Fact_Acct f WHERE f.Fact_Acct_ID=C_TaxDeclarationAcct.Fact_Acct_ID)', DefaultValue=NULL, Description='Source Debit Amount', EntityType='D', FieldLength=11, Help='The Source Debit Amount indicates the credit amount for this line in the source currency.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Source Debit', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14499
;

-- Mar 3, 2008 10:12:04 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Source Debit', Description='Source Debit Amount', Help='The Source Debit Amount indicates the credit amount for this line in the source currency.' WHERE AD_Column_ID=14499 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:04 PM CST
-- Tax Global Management
UPDATE AD_Reference SET Description='Date mm/dd/yyyy', EntityType='D', Help=NULL, IsActive='Y', Name='Date', ValidationType='D',Updated=TO_TIMESTAMP('2008-03-03 22:12:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=15
;

-- Mar 3, 2008 10:12:04 PM CST
-- Tax Global Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=15
;

-- Mar 3, 2008 10:12:04 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=263, AD_Process_ID=NULL, AD_Reference_ID=15, AD_Reference_Value_ID=NULL, AD_Table_ID=820, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='DateAcct', ColumnSQL='(SELECT DateAcct FROM Fact_Acct f WHERE f.Fact_Acct_ID=C_TaxDeclarationAcct.Fact_Acct_ID)', DefaultValue=NULL, Description='Accounting Date', EntityType='D', FieldLength=11, Help='The Accounting Date indicates the date to be used on the General Ledger account entries generated from this document. It is also used for any currency conversion.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Account Date', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14504
;

-- Mar 3, 2008 10:12:04 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Account Date', Description='Accounting Date', Help='The Accounting Date indicates the date to be used on the General Ledger account entries generated from this document. It is also used for any currency conversion.' WHERE AD_Column_ID=14504 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:05 PM CST
-- Tax Global Management
UPDATE AD_Reference SET Description='Account selection based on Client', EntityType='D', Help=NULL, IsActive='Y', Name='Account_ID', ValidationType='T',Updated=TO_TIMESTAMP('2008-03-03 22:12:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=331
;

-- Mar 3, 2008 10:12:05 PM CST
-- Tax Global Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=331
;

-- Mar 3, 2008 10:12:05 PM CST
-- Tax Global Management
UPDATE AD_Ref_Table SET AD_Table_ID = 188, AD_Display = 1135, AD_Key = 1125, isValueDisplayed = 'Y', OrderByClause = 'C_ElementValue.Value', EntityType ='D', WhereClause = 'C_ElementValue.IsActive=''Y'' 
AND C_ElementValue.C_Element_ID IN (SELECT C_Element_ID FROM C_AcctSchema_Element ase WHERE ase.ElementType=''AC'' AND ase.AD_Client_ID=@AD_Client_ID@)' WHERE AD_Reference_ID = 331
;

-- Mar 3, 2008 10:12:05 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=148, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=331, AD_Table_ID=820, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Account_ID', ColumnSQL='(SELECT Account_ID FROM Fact_Acct f WHERE f.Fact_Acct_ID=C_TaxDeclarationAcct.Fact_Acct_ID)', DefaultValue=NULL, Description='Account used', EntityType='D', FieldLength=11, Help='The (natural) account used', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Account', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14503
;

-- Mar 3, 2008 10:12:05 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Account', Description='Account used', Help='The (natural) account used' WHERE AD_Column_ID=14503 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:05 PM CST
-- Tax Global Management
UPDATE AD_Reference SET Description='Search Field', EntityType='D', Help=NULL, IsActive='Y', Name='Search', ValidationType='D',Updated=TO_TIMESTAMP('2008-03-03 22:12:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=30
;

-- Mar 3, 2008 10:12:05 PM CST
-- Tax Global Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=30
;

-- Mar 3, 2008 10:12:06 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=187, AD_Process_ID=NULL, AD_Reference_ID=30, AD_Reference_Value_ID=NULL, AD_Table_ID=820, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='C_BPartner_ID', ColumnSQL='(SELECT C_BPartner_ID FROM Fact_Acct f WHERE f.Fact_Acct_ID=C_TaxDeclarationAcct.Fact_Acct_ID)', DefaultValue=NULL, Description='Identifies a Business Partner', EntityType='D', FieldLength=11, Help='A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Business Partner ', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14502
;

-- Mar 3, 2008 10:12:06 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Business Partner ', Description='Identifies a Business Partner', Help='A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson' WHERE AD_Column_ID=14502 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:07 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=193, AD_Process_ID=NULL, AD_Reference_ID=30, AD_Reference_Value_ID=NULL, AD_Table_ID=820, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='C_Currency_ID', ColumnSQL='(SELECT C_Currency_ID FROM Fact_Acct f WHERE f.Fact_Acct_ID=C_TaxDeclarationAcct.Fact_Acct_ID)', DefaultValue=NULL, Description='The Currency for this record', EntityType='D', FieldLength=11, Help='Indicates the Currency to be used when processing or reporting on this record', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Currency', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14501
;

-- Mar 3, 2008 10:12:07 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Currency', Description='The Currency for this record', Help='Indicates the Currency to be used when processing or reporting on this record' WHERE AD_Column_ID=14501 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:07 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=161, AD_Process_ID=NULL, AD_Reference_ID=12, AD_Reference_Value_ID=NULL, AD_Table_ID=820, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AmtAcctCr', ColumnSQL='(SELECT AmtAcctCr FROM Fact_Acct f WHERE f.Fact_Acct_ID=C_TaxDeclarationAcct.Fact_Acct_ID)', DefaultValue=NULL, Description='Accounted Credit Amount', EntityType='D', FieldLength=11, Help='The Account Credit Amount indicates the transaction amount converted to this organization''s accounting currency', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Accounted Credit', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14498
;

-- Mar 3, 2008 10:12:07 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Accounted Credit', Description='Accounted Credit Amount', Help='The Account Credit Amount indicates the transaction amount converted to this organization''s accounting currency' WHERE AD_Column_ID=14498 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:08 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=162, AD_Process_ID=NULL, AD_Reference_ID=12, AD_Reference_Value_ID=NULL, AD_Table_ID=820, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AmtAcctDr', ColumnSQL='(SELECT AmtAcctDr FROM Fact_Acct f WHERE f.Fact_Acct_ID=C_TaxDeclarationAcct.Fact_Acct_ID)', DefaultValue=NULL, Description='Accounted Debit Amount', EntityType='D', FieldLength=11, Help='The Account Debit Amount indicates the transaction amount converted to this organization''s accounting currency', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Accounted Debit', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14497
;

-- Mar 3, 2008 10:12:08 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Accounted Debit', Description='Accounted Debit Amount', Help='The Account Debit Amount indicates the transaction amount converted to this organization''s accounting currency' WHERE AD_Column_ID=14497 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:08 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=213, AD_Process_ID=NULL, AD_Reference_ID=30, AD_Reference_Value_ID=NULL, AD_Table_ID=820, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='C_Tax_ID', ColumnSQL='(SELECT C_Tax_ID FROM Fact_Acct f WHERE f.Fact_Acct_ID=C_TaxDeclarationAcct.Fact_Acct_ID)', DefaultValue=NULL, Description='Tax identifier', EntityType='D', FieldLength=11, Help='The Tax indicates the type of tax used in document line.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Tax', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14634
;

-- Mar 3, 2008 10:12:08 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Tax', Description='Tax identifier', Help='The Tax indicates the type of tax used in document line.' WHERE AD_Column_ID=14634 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:09 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=348, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=820, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsActive', ColumnSQL=NULL, DefaultValue=NULL, Description='The record is active in the system', EntityType='D', FieldLength=1, Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Active', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14488
;

-- Mar 3, 2008 10:12:09 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Active', Description='The record is active in the system', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.' WHERE AD_Column_ID=14488 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:09 PM CST
-- Tax Global Management
UPDATE AD_Reference SET Description='10 Digit numeric', EntityType='D', Help=NULL, IsActive='Y', Name='Integer', ValidationType='D',Updated=TO_TIMESTAMP('2008-03-03 22:12:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=11
;

-- Mar 3, 2008 10:12:09 PM CST
-- Tax Global Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=11
;

-- Mar 3, 2008 10:12:09 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=439, AD_Process_ID=NULL, AD_Reference_ID=11, AD_Reference_Value_ID=NULL, AD_Table_ID=820, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Line', ColumnSQL=NULL, DefaultValue=NULL, Description='Unique line for this document', EntityType='D', FieldLength=10, Help='Indicates the unique line for a document.  It will also control the display order of the lines within a document.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Line No', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14633
;

-- Mar 3, 2008 10:12:09 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Line No', Description='Unique line for this document', Help='Indicates the unique line for a document.  It will also control the display order of the lines within a document.' WHERE AD_Column_ID=14633 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:10 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=607, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=820, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Updated', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was updated', EntityType='D', FieldLength=7, Help='The Updated field indicates the date that this record was updated.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14491
;

-- Mar 3, 2008 10:12:10 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Updated', Description='Date this record was updated', Help='The Updated field indicates the date that this record was updated.' WHERE AD_Column_ID=14491 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:10 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=102, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=820, AD_Val_Rule_ID=129, Callout=NULL, ColumnName='AD_Client_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Client/Tenant for this installation.', EntityType='D', FieldLength=10, Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Client', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14486
;

-- Mar 3, 2008 10:12:10 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Client', Description='Client/Tenant for this installation.', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.' WHERE AD_Column_ID=14486 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:11 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=608, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=820, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='UpdatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who updated this records', EntityType='D', FieldLength=10, Help='The Updated By field indicates the user who updated this record.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated By', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14492
;

-- Mar 3, 2008 10:12:11 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Updated By', Description='User who updated this records', Help='The Updated By field indicates the user who updated this record.' WHERE AD_Column_ID=14492 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:12 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=113, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=820, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_Org_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Organizational entity within client', EntityType='D', FieldLength=10, Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Organization', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14487
;

-- Mar 3, 2008 10:12:12 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Organization', Description='Organizational entity within client', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.' WHERE AD_Column_ID=14487 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:12 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=181, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=820, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='C_AcctSchema_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Rules for accounting', EntityType='D', FieldLength=10, Help='An Accounting Schema defines the rules used in accounting such as costing method, currency and calendar', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Accounting Schema', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14496
;

-- Mar 3, 2008 10:12:12 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Accounting Schema', Description='Rules for accounting', Help='An Accounting Schema defines the rules used in accounting such as costing method, currency and calendar' WHERE AD_Column_ID=14496 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:13 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=2864, AD_Process_ID=NULL, AD_Reference_ID=13, AD_Reference_Value_ID=NULL, AD_Table_ID=820, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='C_TaxDeclarationAcct_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Tax Accounting Reconciliation ', EntityType='D', FieldLength=10, Help='Accounting related information for reconcilation with documents. It includes all revenue/expense and tax entries as a base for detail reporting', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='Y', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Tax Declaration Accounting', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14485
;

-- Mar 3, 2008 10:12:13 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Tax Declaration Accounting', Description='Tax Accounting Reconciliation ', Help='Accounting related information for reconcilation with documents. It includes all revenue/expense and tax entries as a base for detail reporting' WHERE AD_Column_ID=14485 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:13 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=2862, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=820, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='C_TaxDeclaration_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Define the declaration to the tax authorities', EntityType='D', FieldLength=10, Help='The tax declaration allows you to create supporting information and reconcile the documents with the accounting', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='Y', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Tax Declaration', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14494
;

-- Mar 3, 2008 10:12:13 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Tax Declaration', Description='Define the declaration to the tax authorities', Help='The tax declaration allows you to create supporting information and reconcile the documents with the accounting' WHERE AD_Column_ID=14494 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:14 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=245, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=820, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Created', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was created', EntityType='D', FieldLength=7, Help='The Created field indicates the date that this record was created.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14489
;

-- Mar 3, 2008 10:12:14 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Created', Description='Date this record was created', Help='The Created field indicates the date that this record was created.' WHERE AD_Column_ID=14489 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:14 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=246, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=820, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='CreatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who created this records', EntityType='D', FieldLength=10, Help='The Created By field indicates the user who created this record.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created By', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14490
;

-- Mar 3, 2008 10:12:14 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Created By', Description='User who created this records', Help='The Created By field indicates the user who created this record.' WHERE AD_Column_ID=14490 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:15 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=275, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=820, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Description', ColumnSQL=NULL, DefaultValue=NULL, Description='Optional short description of the record', EntityType='D', FieldLength=255, Help='A description is limited to 255 characters.', IsActive='Y', IsAlwaysUpdateable='Y', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Description', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14493
;

-- Mar 3, 2008 10:12:15 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Description', Description='Optional short description of the record', Help='A description is limited to 255 characters.' WHERE AD_Column_ID=14493 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:15 PM CST
-- Tax Global Management
UPDATE AD_Reference SET Description='Account Element', EntityType='D', Help=NULL, IsActive='Y', Name='Account', ValidationType='D',Updated=TO_TIMESTAMP('2008-03-03 22:12:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=25
;

-- Mar 3, 2008 10:12:15 PM CST
-- Tax Global Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=25
;

-- Mar 3, 2008 10:12:15 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=885, AD_Process_ID=NULL, AD_Reference_ID=25, AD_Reference_Value_ID=NULL, AD_Table_ID=820, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Fact_Acct_ID', ColumnSQL=NULL, DefaultValue=NULL, Description=NULL, EntityType='D', FieldLength=10, Help=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Accounting Fact', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14495
;

-- Mar 3, 2008 10:12:15 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Accounting Fact', Description=NULL, Help=NULL WHERE AD_Column_ID=14495 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:16 PM CST
-- Tax Global Management
UPDATE AD_Tab SET AD_Table_ID=820, AD_Window_ID=359, CommitWarning=NULL, Description='Tax Accounting Reconciliation ', EntityType='D', HasTree='N', Help='Displays all accounting related information for reconcilation with documents. It includes all revenue/expense and tax entries as a base for detail reporting', ImportFields=NULL, IsActive='Y', IsAdvancedTab='N', IsInfoTab='N', IsInsertRecord='N', IsReadOnly='N', IsSingleRow='N', IsSortTab='N', IsTranslationTab='N', Name='Accounting', OrderByClause=NULL, Processing='N', SeqNo=30, TabLevel=1, WhereClause=NULL,Updated=TO_TIMESTAMP('2008-03-03 22:12:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=765
;

-- Mar 3, 2008 10:12:16 PM CST
-- Tax Global Management
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=765
;

-- Mar 3, 2008 10:12:16 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14485, AD_FieldGroup_ID=NULL, AD_Tab_ID=765, Description='Tax Accounting Reconciliation ', DisplayLength=10, DisplayLogic=NULL, EntityType='D', Help='Accounting related information for reconcilation with documents. It includes all revenue/expense and tax entries as a base for detail reporting', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='N', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Tax Declaration Accounting', SeqNo=0, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12414
;

-- Mar 3, 2008 10:12:17 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14488, AD_FieldGroup_ID=NULL, AD_Tab_ID=765, Description='The record is active in the system', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='N', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Active', SeqNo=0, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12404
;

-- Mar 3, 2008 10:12:17 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14495, AD_FieldGroup_ID=NULL, AD_Tab_ID=765, Description=NULL, DisplayLength=10, DisplayLogic=NULL, EntityType='D', Help=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='N', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Accounting Fact', SeqNo=0, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12402
;

-- Mar 3, 2008 10:12:17 PM CST
-- Tax Global Management
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=12402
;

-- Mar 3, 2008 10:12:17 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14634, AD_FieldGroup_ID=NULL, AD_Tab_ID=765, Description='Tax identifier', DisplayLength=11, DisplayLogic=NULL, EntityType='D', Help='The Tax indicates the type of tax used in document line.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Tax', SeqNo=0, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12638
;

-- Mar 3, 2008 10:12:18 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14486, AD_FieldGroup_ID=NULL, AD_Tab_ID=765, Description='Client/Tenant for this installation.', DisplayLength=10, DisplayLogic=NULL, EntityType='D', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Client', SeqNo=10, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12409
;

-- Mar 3, 2008 10:12:18 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14487, AD_FieldGroup_ID=NULL, AD_Tab_ID=765, Description='Organizational entity within client', DisplayLength=10, DisplayLogic=NULL, EntityType='D', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='Y', Name='Organization', SeqNo=20, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12412
;

-- Mar 3, 2008 10:12:19 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14494, AD_FieldGroup_ID=NULL, AD_Tab_ID=765, Description='Define the declaration to the tax authorities', DisplayLength=10, DisplayLogic=NULL, EntityType='D', Help='The tax declaration allows you to create supporting information and reconcile the documents with the accounting', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Tax Declaration', SeqNo=30, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12413
;

-- Mar 3, 2008 10:12:19 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14496, AD_FieldGroup_ID=NULL, AD_Tab_ID=765, Description='Rules for accounting', DisplayLength=10, DisplayLogic=NULL, EntityType='D', Help='An Accounting Schema defines the rules used in accounting such as costing method, currency and calendar', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Accounting Schema', SeqNo=40, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12403
;

-- Mar 3, 2008 10:12:19 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14633, AD_FieldGroup_ID=NULL, AD_Tab_ID=765, Description='Unique line for this document', DisplayLength=10, DisplayLogic=NULL, EntityType='D', Help='Indicates the unique line for a document.  It will also control the display order of the lines within a document.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Line No', SeqNo=50, SortNo=1,Updated=TO_TIMESTAMP('2008-03-03 22:12:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12637
;

-- Mar 3, 2008 10:12:20 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14493, AD_FieldGroup_ID=NULL, AD_Tab_ID=765, Description='Optional short description of the record', DisplayLength=255, DisplayLogic=NULL, EntityType='D', Help='A description is limited to 255 characters.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Description', SeqNo=60, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12411
;

-- Mar 3, 2008 10:12:20 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14503, AD_FieldGroup_ID=NULL, AD_Tab_ID=765, Description='Account used', DisplayLength=11, DisplayLogic=NULL, EntityType='D', Help='The (natural) account used', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Account', SeqNo=70, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12415
;

-- Mar 3, 2008 10:12:20 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14504, AD_FieldGroup_ID=NULL, AD_Tab_ID=765, Description='Accounting Date', DisplayLength=11, DisplayLogic=NULL, EntityType='D', Help='The Accounting Date indicates the date to be used on the General Ledger account entries generated from this document. It is also used for any currency conversion.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='Y', Name='Account Date', SeqNo=80, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12417
;

-- Mar 3, 2008 10:12:21 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14497, AD_FieldGroup_ID=NULL, AD_Tab_ID=765, Description='Accounted Debit Amount', DisplayLength=11, DisplayLogic=NULL, EntityType='D', Help='The Account Debit Amount indicates the transaction amount converted to this organization''s accounting currency', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Accounted Debit', SeqNo=90, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12406
;

-- Mar 3, 2008 10:12:21 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14498, AD_FieldGroup_ID=NULL, AD_Tab_ID=765, Description='Accounted Credit Amount', DisplayLength=11, DisplayLogic=NULL, EntityType='D', Help='The Account Credit Amount indicates the transaction amount converted to this organization''s accounting currency', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='Y', Name='Accounted Credit', SeqNo=100, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12405
;

-- Mar 3, 2008 10:12:21 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14502, AD_FieldGroup_ID=125, AD_Tab_ID=765, Description='Identifies a Business Partner', DisplayLength=11, DisplayLogic=NULL, EntityType='D', Help='A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Business Partner ', SeqNo=110, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12416
;

-- Mar 3, 2008 10:12:22 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14501, AD_FieldGroup_ID=125, AD_Tab_ID=765, Description='The Currency for this record', DisplayLength=11, DisplayLogic=NULL, EntityType='D', Help='Indicates the Currency to be used when processing or reporting on this record', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='Y', Name='Currency', SeqNo=120, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12410
;

-- Mar 3, 2008 10:12:22 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14499, AD_FieldGroup_ID=NULL, AD_Tab_ID=765, Description='Source Debit Amount', DisplayLength=11, DisplayLogic=NULL, EntityType='D', Help='The Source Debit Amount indicates the credit amount for this line in the source currency.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Source Debit', SeqNo=130, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12408
;

-- Mar 3, 2008 10:12:23 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14500, AD_FieldGroup_ID=NULL, AD_Tab_ID=765, Description='Source Credit Amount', DisplayLength=11, DisplayLogic=NULL, EntityType='D', Help='The Source Credit Amount indicates the credit amount for this line in the source currency.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='Y', Name='Source Credit', SeqNo=140, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12407
;

-- Mar 3, 2008 10:12:23 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=102, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=819, AD_Val_Rule_ID=129, Callout=NULL, ColumnName='AD_Client_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Client/Tenant for this installation.', EntityType='D', FieldLength=10, Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Client', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14467
;

-- Mar 3, 2008 10:12:23 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Client', Description='Client/Tenant for this installation.', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.' WHERE AD_Column_ID=14467 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:24 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=113, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=819, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_Org_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Organizational entity within client', EntityType='D', FieldLength=10, Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Organization', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14468
;

-- Mar 3, 2008 10:12:24 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Organization', Description='Organizational entity within client', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.' WHERE AD_Column_ID=14468 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:25 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=2534, AD_Process_ID=NULL, AD_Reference_ID=30, AD_Reference_Value_ID=NULL, AD_Table_ID=819, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='C_AllocationLine_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Allocation Line', EntityType='D', FieldLength=10, Help='Allocation of Cash/Payment to Invoice', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Allocation Line', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14481
;

-- Mar 3, 2008 10:12:25 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Allocation Line', Description='Allocation Line', Help='Allocation of Cash/Payment to Invoice' WHERE AD_Column_ID=14481 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:25 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=187, AD_Process_ID=NULL, AD_Reference_ID=30, AD_Reference_Value_ID=NULL, AD_Table_ID=819, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='C_BPartner_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Identifies a Business Partner', EntityType='D', FieldLength=10, Help='A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Business Partner ', ReadOnlyLogic='@IsManual@=N', VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14477
;

-- Mar 3, 2008 10:12:25 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Business Partner ', Description='Identifies a Business Partner', Help='A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson' WHERE AD_Column_ID=14477 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:26 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=193, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=819, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='C_Currency_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='The Currency for this record', EntityType='D', FieldLength=10, Help='Indicates the Currency to be used when processing or reporting on this record', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Currency', ReadOnlyLogic='@IsManual@=N', VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14635
;

-- Mar 3, 2008 10:12:26 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Currency', Description='The Currency for this record', Help='Indicates the Currency to be used when processing or reporting on this record' WHERE AD_Column_ID=14635 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:26 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=1076, AD_Process_ID=NULL, AD_Reference_ID=30, AD_Reference_Value_ID=NULL, AD_Table_ID=819, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='C_InvoiceLine_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Invoice Detail Line', EntityType='D', FieldLength=10, Help='The Invoice Line uniquely identifies a single line of an Invoice.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Invoice Line', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14480
;

-- Mar 3, 2008 10:12:26 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Invoice Line', Description='Invoice Detail Line', Help='The Invoice Line uniquely identifies a single line of an Invoice.' WHERE AD_Column_ID=14480 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:27 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=1008, AD_Process_ID=NULL, AD_Reference_ID=30, AD_Reference_Value_ID=NULL, AD_Table_ID=819, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='C_Invoice_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Invoice Identifier', EntityType='D', FieldLength=10, Help='The Invoice Document.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Invoice', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14479
;

-- Mar 3, 2008 10:12:27 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Invoice', Description='Invoice Identifier', Help='The Invoice Document.' WHERE AD_Column_ID=14479 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:27 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=2863, AD_Process_ID=NULL, AD_Reference_ID=13, AD_Reference_Value_ID=NULL, AD_Table_ID=819, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='C_TaxDeclarationLine_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Tax Declaration Document Information', EntityType='D', FieldLength=10, Help='The lines are created by the create process. You can delete them if you do not want to include them in a particular declaration. ', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='Y', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Tax Declaration Line', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14466
;

-- Mar 3, 2008 10:12:27 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Tax Declaration Line', Description='Tax Declaration Document Information', Help='The lines are created by the create process. You can delete them if you do not want to include them in a particular declaration. ' WHERE AD_Column_ID=14466 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:28 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=2862, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=819, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='C_TaxDeclaration_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Define the declaration to the tax authorities', EntityType='D', FieldLength=10, Help='The tax declaration allows you to create supporting information and reconcile the documents with the accounting', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='Y', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Tax Declaration', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14476
;

-- Mar 3, 2008 10:12:28 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Tax Declaration', Description='Define the declaration to the tax authorities', Help='The tax declaration allows you to create supporting information and reconcile the documents with the accounting' WHERE AD_Column_ID=14476 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:28 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=213, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=819, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='C_Tax_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Tax identifier', EntityType='D', FieldLength=10, Help='The Tax indicates the type of tax used in document line.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Tax', ReadOnlyLogic='@IsManual@=N', VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14478
;

-- Mar 3, 2008 10:12:28 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Tax', Description='Tax identifier', Help='The Tax indicates the type of tax used in document line.' WHERE AD_Column_ID=14478 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:30 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=245, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=819, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Created', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was created', EntityType='D', FieldLength=7, Help='The Created field indicates the date that this record was created.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14470
;

-- Mar 3, 2008 10:12:30 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Created', Description='Date this record was created', Help='The Created field indicates the date that this record was created.' WHERE AD_Column_ID=14470 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:30 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=246, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=819, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='CreatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who created this records', EntityType='D', FieldLength=10, Help='The Created By field indicates the user who created this record.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created By', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14471
;

-- Mar 3, 2008 10:12:30 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Created By', Description='User who created this records', Help='The Created By field indicates the user who created this record.' WHERE AD_Column_ID=14471 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:31 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=263, AD_Process_ID=NULL, AD_Reference_ID=15, AD_Reference_Value_ID=NULL, AD_Table_ID=819, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='DateAcct', ColumnSQL=NULL, DefaultValue=NULL, Description='Accounting Date', EntityType='D', FieldLength=7, Help='The Accounting Date indicates the date to be used on the General Ledger account entries generated from this document. It is also used for any currency conversion.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Account Date', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14484
;

-- Mar 3, 2008 10:12:31 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Account Date', Description='Accounting Date', Help='The Accounting Date indicates the date to be used on the General Ledger account entries generated from this document. It is also used for any currency conversion.' WHERE AD_Column_ID=14484 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:31 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=275, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=819, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Description', ColumnSQL=NULL, DefaultValue=NULL, Description='Optional short description of the record', EntityType='D', FieldLength=255, Help='A description is limited to 255 characters.', IsActive='Y', IsAlwaysUpdateable='Y', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Description', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14474
;

-- Mar 3, 2008 10:12:31 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Description', Description='Optional short description of the record', Help='A description is limited to 255 characters.' WHERE AD_Column_ID=14474 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:32 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=348, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=819, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsActive', ColumnSQL=NULL, DefaultValue=NULL, Description='The record is active in the system', EntityType='D', FieldLength=1, Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Active', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14469
;

-- Mar 3, 2008 10:12:32 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Active', Description='The record is active in the system', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.' WHERE AD_Column_ID=14469 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:32 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=1474, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=819, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsManual', ColumnSQL=NULL, DefaultValue='Y', Description='This is a manual process', EntityType='D', FieldLength=1, Help='The Manual check box indicates if the process will done manually.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Manual', ReadOnlyLogic='@IsManual@=N', VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14632
;

-- Mar 3, 2008 10:12:32 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Manual', Description='This is a manual process', Help='The Manual check box indicates if the process will done manually.' WHERE AD_Column_ID=14632 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:33 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=439, AD_Process_ID=NULL, AD_Reference_ID=11, AD_Reference_Value_ID=NULL, AD_Table_ID=819, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Line', ColumnSQL=NULL, DefaultValue=NULL, Description='Unique line for this document', EntityType='D', FieldLength=10, Help='Indicates the unique line for a document.  It will also control the display order of the lines within a document.', IsActive='Y', IsAlwaysUpdateable='Y', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Line No', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14475
;

-- Mar 3, 2008 10:12:33 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Line No', Description='Unique line for this document', Help='Indicates the unique line for a document.  It will also control the display order of the lines within a document.' WHERE AD_Column_ID=14475 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:33 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=1133, AD_Process_ID=NULL, AD_Reference_ID=12, AD_Reference_Value_ID=NULL, AD_Table_ID=819, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='TaxAmt', ColumnSQL=NULL, DefaultValue=NULL, Description='Tax Amount for a document', EntityType='D', FieldLength=22, Help='The Tax Amount displays the total tax amount for a document.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Tax Amount', ReadOnlyLogic='@IsManual@=N', VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14483
;

-- Mar 3, 2008 10:12:33 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Tax Amount', Description='Tax Amount for a document', Help='The Tax Amount displays the total tax amount for a document.' WHERE AD_Column_ID=14483 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:34 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=1134, AD_Process_ID=NULL, AD_Reference_ID=12, AD_Reference_Value_ID=NULL, AD_Table_ID=819, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='TaxBaseAmt', ColumnSQL=NULL, DefaultValue=NULL, Description='Base for calculating the tax amount', EntityType='D', FieldLength=22, Help='The Tax Base Amount indicates the base amount used for calculating the tax amount.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Tax base Amount', ReadOnlyLogic='@IsManual@=N', VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14482
;

-- Mar 3, 2008 10:12:34 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Tax base Amount', Description='Base for calculating the tax amount', Help='The Tax Base Amount indicates the base amount used for calculating the tax amount.' WHERE AD_Column_ID=14482 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:34 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=607, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=819, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Updated', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was updated', EntityType='D', FieldLength=7, Help='The Updated field indicates the date that this record was updated.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14472
;

-- Mar 3, 2008 10:12:34 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Updated', Description='Date this record was updated', Help='The Updated field indicates the date that this record was updated.' WHERE AD_Column_ID=14472 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:35 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=608, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=819, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='UpdatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who updated this records', EntityType='D', FieldLength=10, Help='The Updated By field indicates the user who updated this record.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated By', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14473
;

-- Mar 3, 2008 10:12:35 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Updated By', Description='User who updated this records', Help='The Updated By field indicates the user who updated this record.' WHERE AD_Column_ID=14473 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:35 PM CST
-- Tax Global Management
UPDATE AD_Tab SET AD_Table_ID=819, AD_Window_ID=359, CommitWarning=NULL, Description='Tax Declaration Lines', EntityType='D', HasTree='N', Help='The lines are created by the create process. You can delete them if you do not want to include them in a particular declaration.  You can create manual adjustment lines.', ImportFields=NULL, IsActive='Y', IsAdvancedTab='N', IsInfoTab='N', IsInsertRecord='Y', IsReadOnly='N', IsSingleRow='N', IsSortTab='N', IsTranslationTab='N', Name='Line', OrderByClause=NULL, Processing='N', SeqNo=20, TabLevel=1, WhereClause=NULL,Updated=TO_TIMESTAMP('2008-03-03 22:12:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=764
;

-- Mar 3, 2008 10:12:35 PM CST
-- Tax Global Management
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=764
;

-- Mar 3, 2008 10:12:36 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14481, AD_FieldGroup_ID=NULL, AD_Tab_ID=764, Description='Allocation Line', DisplayLength=10, DisplayLogic=NULL, EntityType='D', Help='Allocation of Cash/Payment to Invoice', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='N', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Allocation Line', SeqNo=0, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12389
;

-- Mar 3, 2008 10:12:36 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14466, AD_FieldGroup_ID=NULL, AD_Tab_ID=764, Description='Tax Declaration Document Information', DisplayLength=10, DisplayLogic=NULL, EntityType='D', Help='The lines are created by the create process. You can delete them if you do not want to include them in a particular declaration. ', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='N', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Tax Declaration Line', SeqNo=0, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12400
;

-- Mar 3, 2008 10:12:36 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14467, AD_FieldGroup_ID=NULL, AD_Tab_ID=764, Description='Client/Tenant for this installation.', DisplayLength=10, DisplayLogic=NULL, EntityType='D', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Client', SeqNo=10, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12391
;

-- Mar 3, 2008 10:12:37 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14468, AD_FieldGroup_ID=NULL, AD_Tab_ID=764, Description='Organizational entity within client', DisplayLength=10, DisplayLogic=NULL, EntityType='D', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='Y', Name='Organization', SeqNo=20, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12396
;

-- Mar 3, 2008 10:12:37 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14476, AD_FieldGroup_ID=NULL, AD_Tab_ID=764, Description='Define the declaration to the tax authorities', DisplayLength=10, DisplayLogic=NULL, EntityType='D', Help='The tax declaration allows you to create supporting information and reconcile the documents with the accounting', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Tax Declaration', SeqNo=30, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12399
;

-- Mar 3, 2008 10:12:38 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14469, AD_FieldGroup_ID=NULL, AD_Tab_ID=764, Description='The record is active in the system', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Active', SeqNo=40, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12388
;

-- Mar 3, 2008 10:12:38 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14475, AD_FieldGroup_ID=NULL, AD_Tab_ID=764, Description='Unique line for this document', DisplayLength=10, DisplayLogic=NULL, EntityType='D', Help='Indicates the unique line for a document.  It will also control the display order of the lines within a document.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Line No', SeqNo=50, SortNo=1,Updated=TO_TIMESTAMP('2008-03-03 22:12:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12395
;

-- Mar 3, 2008 10:12:38 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14632, AD_FieldGroup_ID=NULL, AD_Tab_ID=764, Description='This is a manual process', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help='The Manual check box indicates if the process will done manually.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='Y', Name='Manual', SeqNo=60, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12636
;

-- Mar 3, 2008 10:12:39 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14474, AD_FieldGroup_ID=NULL, AD_Tab_ID=764, Description='Optional short description of the record', DisplayLength=255, DisplayLogic=NULL, EntityType='D', Help='A description is limited to 255 characters.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Description', SeqNo=70, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12392
;

-- Mar 3, 2008 10:12:39 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14482, AD_FieldGroup_ID=125, AD_Tab_ID=764, Description='Base for calculating the tax amount', DisplayLength=22, DisplayLogic=NULL, EntityType='D', Help='The Tax Base Amount indicates the base amount used for calculating the tax amount.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Tax base Amount', SeqNo=80, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12401
;

-- Mar 3, 2008 10:12:39 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14483, AD_FieldGroup_ID=125, AD_Tab_ID=764, Description='Tax Amount for a document', DisplayLength=22, DisplayLogic=NULL, EntityType='D', Help='The Tax Amount displays the total tax amount for a document.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Tax Amount', SeqNo=90, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12398
;

-- Mar 3, 2008 10:12:40 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14484, AD_FieldGroup_ID=125, AD_Tab_ID=764, Description='Accounting Date', DisplayLength=7, DisplayLogic=NULL, EntityType='D', Help='The Accounting Date indicates the date to be used on the General Ledger account entries generated from this document. It is also used for any currency conversion.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Account Date', SeqNo=100, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12387
;

-- Mar 3, 2008 10:12:40 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14477, AD_FieldGroup_ID=104, AD_Tab_ID=764, Description='Identifies a Business Partner', DisplayLength=10, DisplayLogic=NULL, EntityType='D', Help='A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Business Partner ', SeqNo=110, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12390
;

-- Mar 3, 2008 10:12:41 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14635, AD_FieldGroup_ID=NULL, AD_Tab_ID=764, Description='The Currency for this record', DisplayLength=10, DisplayLogic=NULL, EntityType='D', Help='Indicates the Currency to be used when processing or reporting on this record', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Currency', SeqNo=120, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12639
;

-- Mar 3, 2008 10:12:41 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14479, AD_FieldGroup_ID=NULL, AD_Tab_ID=764, Description='Invoice Identifier', DisplayLength=10, DisplayLogic=NULL, EntityType='D', Help='The Invoice Document.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Invoice', SeqNo=130, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12393
;

-- Mar 3, 2008 10:12:41 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14480, AD_FieldGroup_ID=NULL, AD_Tab_ID=764, Description='Invoice Detail Line', DisplayLength=10, DisplayLogic=NULL, EntityType='D', Help='The Invoice Line uniquely identifies a single line of an Invoice.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='Y', Name='Invoice Line', SeqNo=140, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12394
;

-- Mar 3, 2008 10:12:42 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14478, AD_FieldGroup_ID=NULL, AD_Tab_ID=764, Description='Tax identifier', DisplayLength=10, DisplayLogic=NULL, EntityType='D', Help='The Tax indicates the type of tax used in document line.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Tax', SeqNo=150, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12397
;

-- Mar 3, 2008 10:12:42 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=469, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=818, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Name', ColumnSQL=NULL, DefaultValue=NULL, Description='Alphanumeric identifier of the entity', EntityType='D', FieldLength=120, Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='Y', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Name', ReadOnlyLogic=NULL, SeqNo=1, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14459
;

-- Mar 3, 2008 10:12:42 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Name', Description='Alphanumeric identifier of the entity', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.' WHERE AD_Column_ID=14459 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:43 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=113, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=818, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_Org_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Organizational entity within client', EntityType='D', FieldLength=10, Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Organization', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14453
;

-- Mar 3, 2008 10:12:43 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Organization', Description='Organizational entity within client', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.' WHERE AD_Column_ID=14453 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:43 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=2862, AD_Process_ID=NULL, AD_Reference_ID=13, AD_Reference_Value_ID=NULL, AD_Table_ID=818, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='C_TaxDeclaration_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Define the declaration to the tax authorities', EntityType='D', FieldLength=10, Help='The tax declaration allows you to create supporting information and reconcile the documents with the accounting', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='Y', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Tax Declaration', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14451
;

-- Mar 3, 2008 10:12:43 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Tax Declaration', Description='Define the declaration to the tax authorities', Help='The tax declaration allows you to create supporting information and reconcile the documents with the accounting' WHERE AD_Column_ID=14451 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:44 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=245, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=818, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Created', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was created', EntityType='D', FieldLength=7, Help='The Created field indicates the date that this record was created.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14455
;

-- Mar 3, 2008 10:12:44 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Created', Description='Date this record was created', Help='The Created field indicates the date that this record was created.' WHERE AD_Column_ID=14455 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:44 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=246, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=818, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='CreatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who created this records', EntityType='D', FieldLength=10, Help='The Created By field indicates the user who created this record.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created By', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14456
;

-- Mar 3, 2008 10:12:44 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Created By', Description='User who created this records', Help='The Created By field indicates the user who created this record.' WHERE AD_Column_ID=14456 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:45 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=1581, AD_Process_ID=NULL, AD_Reference_ID=15, AD_Reference_Value_ID=NULL, AD_Table_ID=818, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='DateFrom', ColumnSQL=NULL, DefaultValue=NULL, Description='Starting date for a range', EntityType='D', FieldLength=7, Help='The Date From indicates the starting date of a range.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Date From', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14462
;

-- Mar 3, 2008 10:12:45 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Date From', Description='Starting date for a range', Help='The Date From indicates the starting date of a range.' WHERE AD_Column_ID=14462 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:45 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=1582, AD_Process_ID=NULL, AD_Reference_ID=15, AD_Reference_Value_ID=NULL, AD_Table_ID=818, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='DateTo', ColumnSQL=NULL, DefaultValue=NULL, Description='End date of a date range', EntityType='D', FieldLength=7, Help='The Date To indicates the end date of a range (inclusive)', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Date To', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14463
;

-- Mar 3, 2008 10:12:45 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Date To', Description='End date of a date range', Help='The Date To indicates the end date of a range (inclusive)' WHERE AD_Column_ID=14463 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:46 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=1297, AD_Process_ID=NULL, AD_Reference_ID=15, AD_Reference_Value_ID=NULL, AD_Table_ID=818, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='DateTrx', ColumnSQL=NULL, DefaultValue=NULL, Description='Transaction Date', EntityType='D', FieldLength=7, Help='The Transaction Date indicates the date of the transaction.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Transaction Date', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14461
;

-- Mar 3, 2008 10:12:46 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Transaction Date', Description='Transaction Date', Help='The Transaction Date indicates the date of the transaction.' WHERE AD_Column_ID=14461 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:46 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=275, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=818, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Description', ColumnSQL=NULL, DefaultValue=NULL, Description='Optional short description of the record', EntityType='D', FieldLength=255, Help='A description is limited to 255 characters.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Description', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14460
;

-- Mar 3, 2008 10:12:46 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Description', Description='Optional short description of the record', Help='A description is limited to 255 characters.' WHERE AD_Column_ID=14460 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:47 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=348, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=818, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsActive', ColumnSQL=NULL, DefaultValue=NULL, Description='The record is active in the system', EntityType='D', FieldLength=1, Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Active', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14454
;

-- Mar 3, 2008 10:12:47 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Active', Description='The record is active in the system', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.' WHERE AD_Column_ID=14454 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:47 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=1047, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=818, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Processed', ColumnSQL=NULL, DefaultValue=NULL, Description='The document has been processed', EntityType='D', FieldLength=1, Help='The Processed checkbox indicates that a document has been processed.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Processed', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14465
;

-- Mar 3, 2008 10:12:47 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Processed', Description='The document has been processed', Help='The Processed checkbox indicates that a document has been processed.' WHERE AD_Column_ID=14465 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:48 PM CST
-- Tax Global Management
UPDATE AD_Reference SET Description='Command Button - starts a process', EntityType='D', Help=NULL, IsActive='Y', Name='Button', ValidationType='D',Updated=TO_TIMESTAMP('2008-03-03 22:12:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=28
;

-- Mar 3, 2008 10:12:48 PM CST
-- Tax Global Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=28
;

-- Mar 3, 2008 10:12:48 PM CST
-- Tax Global Management
UPDATE AD_Process SET AccessLevel='3', Classname='org.compiere.process.TaxDeclarationCreate', Description='Create Tax Declaration from Documents', EntityType='D', Help=NULL, IsActive='Y', IsBetaFunctionality='N', IsDirectPrint='N', IsReport='N', JasperReport=NULL, Name='Create Tax Declaration', ProcedureName=NULL, ShowHelp='Y', Statistic_Count=0, Statistic_Seconds=0, Value='C_TaxDeclaration Create', WorkflowValue=NULL,Updated=TO_TIMESTAMP('2008-03-03 22:12:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_ID=336
;

-- Mar 3, 2008 10:12:48 PM CST
-- Tax Global Management
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=336
;

-- Mar 3, 2008 10:12:49 PM CST
-- Tax Global Management
UPDATE AD_Process_Para SET AD_Element_ID=1669, AD_Process_ID=336, AD_Reference_ID=20, ColumnName='DeleteOld', DefaultValue='N', DefaultValue2=NULL, Description='Otherwise records will be added', EntityType='D', FieldLength=0, Help=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsMandatory='N', IsRange='N', Name='Delete old/existing records', SeqNo=10, VFormat=NULL, ValueMax=NULL, ValueMin=NULL,Updated=TO_TIMESTAMP('2008-03-03 22:12:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=681
;

-- Mar 3, 2008 10:12:49 PM CST
-- Tax Global Management
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=681
;

-- Mar 3, 2008 10:12:49 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=524, AD_Process_ID=336, AD_Reference_ID=28, AD_Reference_Value_ID=NULL, AD_Table_ID=818, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Processing', ColumnSQL=NULL, DefaultValue=NULL, Description=NULL, EntityType='D', FieldLength=1, Help=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Process Now', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14464
;

-- Mar 3, 2008 10:12:49 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Process Now', Description=NULL, Help=NULL WHERE AD_Column_ID=14464 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:50 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=607, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=818, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Updated', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was updated', EntityType='D', FieldLength=7, Help='The Updated field indicates the date that this record was updated.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14457
;

-- Mar 3, 2008 10:12:50 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Updated', Description='Date this record was updated', Help='The Updated field indicates the date that this record was updated.' WHERE AD_Column_ID=14457 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:50 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=102, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=818, AD_Val_Rule_ID=129, Callout=NULL, ColumnName='AD_Client_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Client/Tenant for this installation.', EntityType='D', FieldLength=10, Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Client', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14452
;

-- Mar 3, 2008 10:12:50 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Client', Description='Client/Tenant for this installation.', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.' WHERE AD_Column_ID=14452 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:51 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=608, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=818, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='UpdatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who updated this records', EntityType='D', FieldLength=10, Help='The Updated By field indicates the user who updated this record.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated By', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14458
;

-- Mar 3, 2008 10:12:51 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Updated By', Description='User who updated this records', Help='The Updated By field indicates the user who updated this record.' WHERE AD_Column_ID=14458 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:51 PM CST
-- Tax Global Management
UPDATE AD_Tab SET AD_Table_ID=818, AD_Window_ID=359, CommitWarning=NULL, Description='Define the declaration to the tax authorities', EntityType='D', HasTree='N', Help='The tax declaration allows you to create supporting information and reconcile the documents with the accounting', ImportFields=NULL, IsActive='Y', IsAdvancedTab='N', IsInfoTab='N', IsInsertRecord='Y', IsReadOnly='N', IsSingleRow='N', IsSortTab='N', IsTranslationTab='N', Name='Declaration', OrderByClause=NULL, Processing='N', SeqNo=10, TabLevel=0, WhereClause=NULL,Updated=TO_TIMESTAMP('2008-03-03 22:12:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=763
;

-- Mar 3, 2008 10:12:51 PM CST
-- Tax Global Management
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=763
;

-- Mar 3, 2008 10:12:51 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14451, AD_FieldGroup_ID=NULL, AD_Tab_ID=763, Description='Define the declaration to the tax authorities', DisplayLength=10, DisplayLogic=NULL, EntityType='D', Help='The tax declaration allows you to create supporting information and reconcile the documents with the accounting', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='N', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Tax Declaration', SeqNo=0, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12385
;

-- Mar 3, 2008 10:12:52 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14452, AD_FieldGroup_ID=NULL, AD_Tab_ID=763, Description='Client/Tenant for this installation.', DisplayLength=10, DisplayLogic=NULL, EntityType='D', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Client', SeqNo=10, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12377
;

-- Mar 3, 2008 10:12:52 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14453, AD_FieldGroup_ID=NULL, AD_Tab_ID=763, Description='Organizational entity within client', DisplayLength=10, DisplayLogic=NULL, EntityType='D', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Organization', SeqNo=20, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12382
;

-- Mar 3, 2008 10:12:52 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14459, AD_FieldGroup_ID=NULL, AD_Tab_ID=763, Description='Alphanumeric identifier of the entity', DisplayLength=120, DisplayLogic=NULL, EntityType='D', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Name', SeqNo=30, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12381
;

-- Mar 3, 2008 10:12:53 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14460, AD_FieldGroup_ID=NULL, AD_Tab_ID=763, Description='Optional short description of the record', DisplayLength=255, DisplayLogic=NULL, EntityType='D', Help='A description is limited to 255 characters.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Description', SeqNo=40, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12380
;

-- Mar 3, 2008 10:12:53 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14454, AD_FieldGroup_ID=NULL, AD_Tab_ID=763, Description='The record is active in the system', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Active', SeqNo=50, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12376
;

-- Mar 3, 2008 10:12:54 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14461, AD_FieldGroup_ID=NULL, AD_Tab_ID=763, Description='Transaction Date', DisplayLength=7, DisplayLogic=NULL, EntityType='D', Help='The Transaction Date indicates the date of the transaction.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Transaction Date', SeqNo=60, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12386
;

-- Mar 3, 2008 10:12:54 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14462, AD_FieldGroup_ID=104, AD_Tab_ID=763, Description='Starting date for a range', DisplayLength=7, DisplayLogic=NULL, EntityType='D', Help='The Date From indicates the starting date of a range.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Date From', SeqNo=70, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12378
;

-- Mar 3, 2008 10:12:54 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14463, AD_FieldGroup_ID=104, AD_Tab_ID=763, Description='End date of a date range', DisplayLength=7, DisplayLogic=NULL, EntityType='D', Help='The Date To indicates the end date of a range (inclusive)', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Date To', SeqNo=80, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12379
;

-- Mar 3, 2008 10:12:55 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14464, AD_FieldGroup_ID=101, AD_Tab_ID=763, Description='Create Tax Declaration from Documents', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Create Tax Declaration', SeqNo=90, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12383
;

-- Mar 3, 2008 10:12:55 PM CST
-- Tax Global Management
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=12383
;

-- Mar 3, 2008 10:12:55 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14465, AD_FieldGroup_ID=NULL, AD_Tab_ID=763, Description='The document has been processed', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help='The Processed checkbox indicates that a document has been processed.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Processed', SeqNo=100, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:12:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12384
;

-- Mar 3, 2008 10:12:55 PM CST
-- Tax Global Management
UPDATE AD_Window SET Description='Maintain Taxes and their Rates', EntityType='D', Help='The Tax Rate Window defines the different taxes used for each tax category.  For example Sales Tax must be defined for each State in which it applies.', IsActive='Y', IsBetaFunctionality='N', IsDefault='N', IsSOTrx='Y', Name='Tax Rate', Processing='N', WindowType='M',Updated=TO_TIMESTAMP('2008-03-03 22:12:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Window_ID=137
;

-- Mar 3, 2008 10:12:55 PM CST
-- Tax Global Management
UPDATE AD_Window_Trl SET IsTranslated='N' WHERE AD_Window_ID=137
;

-- Mar 3, 2008 10:12:55 PM CST
-- Tax Global Management
UPDATE AD_WF_Node SET Description='Maintain Taxes and their Rates', Help='The Tax Rate Window defines the different taxes used for each tax category.  For example Sales Tax must be defined for each State in which it applies.', Name='Tax Rate',Updated=TO_TIMESTAMP('2008-03-03 22:12:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=169
;

-- Mar 3, 2008 10:12:55 PM CST
-- Tax Global Management
UPDATE AD_WF_Node_Trl SET IsTranslated='N' WHERE AD_WF_Node_ID=169
;

-- Mar 3, 2008 10:12:56 PM CST
-- Tax Global Management
UPDATE AD_Table SET AD_Window_ID=137, AccessLevel='2', Description='Tax identifier', EntityType='D', Help=NULL, ImportTable=NULL, IsActive='Y', IsChangeLog='N', IsDeleteable='Y', IsHighVolume='N', IsSecurityEnabled='N', IsView='N', Name='Tax', ReplicationType='L', TableName='C_Tax',Updated=TO_TIMESTAMP('2008-03-03 22:12:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=261
;

-- Mar 3, 2008 10:12:56 PM CST
-- Tax Global Management
UPDATE AD_Reference SET Description='Reference List', EntityType='D', Help=NULL, IsActive='Y', Name='List', ValidationType='D',Updated=TO_TIMESTAMP('2008-03-03 22:12:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=17
;

-- Mar 3, 2008 10:12:56 PM CST
-- Tax Global Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=17
;

-- Mar 3, 2008 10:12:56 PM CST
-- Tax Global Management
UPDATE AD_Reference SET Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='C_Tax SPPOType', ValidationType='L',Updated=TO_TIMESTAMP('2008-03-03 22:12:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=287
;

-- Mar 3, 2008 10:12:56 PM CST
-- Tax Global Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=287
;

-- Mar 3, 2008 10:12:56 PM CST
-- Tax Global Management
UPDATE AD_Ref_List SET AD_Reference_ID=287, Description=NULL, EntityType='D', IsActive='Y', Name='Both', Value='B',Updated=TO_TIMESTAMP('2008-03-03 22:12:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=568
;

-- Mar 3, 2008 10:12:56 PM CST
-- Tax Global Management
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=568
;

-- Mar 3, 2008 10:12:57 PM CST
-- Tax Global Management
UPDATE AD_Ref_List SET AD_Reference_ID=287, Description=NULL, EntityType='D', IsActive='Y', Name='Purchase Tax', Value='P',Updated=TO_TIMESTAMP('2008-03-03 22:12:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=570
;

-- Mar 3, 2008 10:12:57 PM CST
-- Tax Global Management
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=570
;

-- Mar 3, 2008 10:12:57 PM CST
-- Tax Global Management
UPDATE AD_Ref_List SET AD_Reference_ID=287, Description=NULL, EntityType='D', IsActive='Y', Name='Sales Tax', Value='S',Updated=TO_TIMESTAMP('2008-03-03 22:12:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=569
;

-- Mar 3, 2008 10:12:57 PM CST
-- Tax Global Management
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=569
;

-- Mar 3, 2008 10:12:58 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=2167, AD_Process_ID=NULL, AD_Reference_ID=17, AD_Reference_Value_ID=287, AD_Table_ID=261, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='SOPOType', ColumnSQL=NULL, DefaultValue='B', Description='Sales Tax applies to sales situations, Purchase Tax to purchase situations', EntityType='D', FieldLength=1, Help='Sales Tax: charged when selling - examples: Sales Tax, Output VAT (payable)
Purchase Tax: tax charged when purchasing - examples: Use Tax, Input VAT (receivable)', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='SO/PO Type', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9767
;

-- Mar 3, 2008 10:12:58 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='SO/PO Type', Description='Sales Tax applies to sales situations, Purchase Tax to purchase situations', Help='Sales Tax: charged when selling - examples: Sales Tax, Output VAT (payable)
Purchase Tax: tax charged when purchasing - examples: Use Tax, Input VAT (receivable)' WHERE AD_Column_ID=9767 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:12:59 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54408,53332,0,19,261,'AD_Rule_ID',TO_TIMESTAMP('2008-03-03 22:12:58','YYYY-MM-DD HH24:MI:SS'),0,'EE04',22,'Y','N','N','N','N','N','N','N','Y','N','Y','Rule',TO_TIMESTAMP('2008-03-03 22:12:58','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 3, 2008 10:12:59 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54408 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:12:59 PM CST
-- Tax Global Management
ALTER TABLE C_Tax ADD COLUMN AD_Rule_ID NUMERIC(10)
;

-- Mar 3, 2008 10:12:59 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=930, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=261, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsTaxExempt', ColumnSQL=NULL, DefaultValue=NULL, Description='Business partner is exempt from tax', EntityType='D', FieldLength=1, Help='If a business partner is exempt from tax, the exempt tax rate is used. For this, you need to set up a tax rate with a 0% rate and indicate that this is your tax exempt rate.  This is required for tax reporting, so that you can track tax exempt transactions.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Tax exempt', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:12:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=7971
;

-- Mar 3, 2008 10:12:59 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Tax exempt', Description='Business partner is exempt from tax', Help='If a business partner is exempt from tax, the exempt tax rate is used. For this, you need to set up a tax rate with a 0% rate and indicate that this is your tax exempt rate.  This is required for tax reporting, so that you can track tax exempt transactions.' WHERE AD_Column_ID=7971 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:00 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=469, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=261, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Name', ColumnSQL=NULL, DefaultValue=NULL, Description='Alphanumeric identifier of the entity', EntityType='D', FieldLength=60, Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='Y', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='Y', IsUpdateable='Y', Name='Name', ReadOnlyLogic=NULL, SeqNo=1, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=2246
;

-- Mar 3, 2008 10:13:00 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Name', Description='Alphanumeric identifier of the entity', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.' WHERE AD_Column_ID=2246 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:01 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=211, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=261, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='C_TaxCategory_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Tax Category', EntityType='D', FieldLength=22, Help='The Tax Category provides a method of grouping similar taxes.  For example, Sales Tax or Value Added Tax.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Tax Category', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=2254
;

-- Mar 3, 2008 10:13:01 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Tax Category', Description='Tax Category', Help='The Tax Category provides a method of grouping similar taxes.  For example, Sales Tax or Value Added Tax.' WHERE AD_Column_ID=2254 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:01 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=213, AD_Process_ID=NULL, AD_Reference_ID=13, AD_Reference_Value_ID=NULL, AD_Table_ID=261, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='C_Tax_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Tax identifier', EntityType='D', FieldLength=22, Help='The Tax indicates the type of tax used in document line.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='Y', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Tax', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=2240
;

-- Mar 3, 2008 10:13:01 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Tax', Description='Tax identifier', Help='The Tax indicates the type of tax used in document line.' WHERE AD_Column_ID=2240 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:02 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=245, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=261, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Created', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was created', EntityType='D', FieldLength=7, Help='The Created field indicates the date that this record was created.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=2244
;

-- Mar 3, 2008 10:13:02 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Created', Description='Date this record was created', Help='The Created field indicates the date that this record was created.' WHERE AD_Column_ID=2244 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:02 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=246, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=261, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='CreatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who created this records', EntityType='D', FieldLength=22, Help='The Created By field indicates the user who created this record.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created By', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=2245
;

-- Mar 3, 2008 10:13:02 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Created By', Description='User who created this records', Help='The Created By field indicates the user who created this record.' WHERE AD_Column_ID=2245 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:03 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=275, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=261, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Description', ColumnSQL=NULL, DefaultValue=NULL, Description='Optional short description of the record', EntityType='D', FieldLength=255, Help='A description is limited to 255 characters.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='Y', IsUpdateable='Y', Name='Description', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=2247
;

-- Mar 3, 2008 10:13:03 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Description', Description='Optional short description of the record', Help='A description is limited to 255 characters.' WHERE AD_Column_ID=2247 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:03 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=348, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=261, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsActive', ColumnSQL=NULL, DefaultValue='Y', Description='The record is active in the system', EntityType='D', FieldLength=1, Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Active', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=2243
;

-- Mar 3, 2008 10:13:03 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Active', Description='The record is active in the system', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.' WHERE AD_Column_ID=2243 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:04 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=1103, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=261, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsDefault', ColumnSQL=NULL, DefaultValue=NULL, Description='Default value', EntityType='D', FieldLength=1, Help='The Default Checkbox indicates if this record will be used as a default value.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Default', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=4211
;

-- Mar 3, 2008 10:13:04 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Default', Description='Default value', Help='The Default Checkbox indicates if this record will be used as a default value.' WHERE AD_Column_ID=4211 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:05 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=917, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=261, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsDocumentLevel', ColumnSQL=NULL, DefaultValue=NULL, Description='Tax is calculated on document level (rather than line by line)', EntityType='D', FieldLength=1, Help='If the tax is calculated on document level, all lines with that tax rate are added before calculating the total tax for the document.
Otherwise the tax is calculated per line and then added.
Due to rounding, the tax amount can differ.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Document Level', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=3053
;

-- Mar 3, 2008 10:13:05 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Document Level', Description='Tax is calculated on document level (rather than line by line)', Help='If the tax is calculated on document level, all lines with that tax rate are added before calculating the total tax for the document.
Otherwise the tax is calculated per line and then added.
Due to rounding, the tax amount can differ.' WHERE AD_Column_ID=3053 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:05 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=2870, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=261, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsSalesTax', ColumnSQL=NULL, DefaultValue='N', Description='This is a sales tax (i.e. not a value added tax)', EntityType='D', FieldLength=1, Help='If selected AP tax is handled as expense, otherwise it is handeled as a VAT credit.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Sales Tax', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=14528
;

-- Mar 3, 2008 10:13:05 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Sales Tax', Description='This is a sales tax (i.e. not a value added tax)', Help='If selected AP tax is handled as expense, otherwise it is handeled as a VAT credit.' WHERE AD_Column_ID=14528 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:06 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=416, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=261, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsSummary', ColumnSQL=NULL, DefaultValue=NULL, Description='This is a summary entity', EntityType='D', FieldLength=1, Help='A summary entity represents a branch in a tree rather than an end-node. Summary entities are used for reporting and do not have own values.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Summary Level', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=3055
;

-- Mar 3, 2008 10:13:06 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Summary Level', Description='This is a summary entity', Help='A summary entity represents a branch in a tree rather than an end-node. Summary entities are used for reporting and do not have own values.' WHERE AD_Column_ID=3055 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:06 PM CST
-- Tax Global Management
UPDATE AD_Reference SET Description='Tax selection', EntityType='D', Help=NULL, IsActive='Y', Name='C_Tax', ValidationType='T',Updated=TO_TIMESTAMP('2008-03-03 22:13:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=158
;

-- Mar 3, 2008 10:13:06 PM CST
-- Tax Global Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=158
;

-- Mar 3, 2008 10:13:06 PM CST
-- Tax Global Management
UPDATE AD_Ref_Table SET AD_Table_ID = 261, AD_Display = 2246, AD_Key = 2240, isValueDisplayed = 'N', OrderByClause = '', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 158
;

-- Mar 3, 2008 10:13:07 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=497, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=158, AD_Table_ID=261, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Parent_Tax_ID', ColumnSQL=NULL, DefaultValue='@C_Tax_ID@', Description='Parent Tax indicates a tax that is made up of multiple taxes', EntityType='D', FieldLength=22, Help='The Parent Tax indicates a tax that is a reference for multiple taxes.  This allows you to charge multiple taxes on a document by entering the Parent Tax', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Parent Tax', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=2249
;

-- Mar 3, 2008 10:13:07 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Parent Tax', Description='Parent Tax indicates a tax that is made up of multiple taxes', Help='The Parent Tax indicates a tax that is a reference for multiple taxes.  This allows you to charge multiple taxes on a document by entering the Parent Tax' WHERE AD_Column_ID=2249 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:07 PM CST
-- Tax Global Management
insert into t_alter_column values('c_tax','Parent_Tax_ID','NUMERIC(10)',null,'NULL')
;

-- Mar 3, 2008 10:13:07 PM CST
-- Tax Global Management
UPDATE AD_Reference SET Description='Float Number', EntityType='D', Help=NULL, IsActive='Y', Name='Number', ValidationType='D',Updated=TO_TIMESTAMP('2008-03-03 22:13:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=22
;

-- Mar 3, 2008 10:13:07 PM CST
-- Tax Global Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=22
;

-- Mar 3, 2008 10:13:08 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=534, AD_Process_ID=NULL, AD_Reference_ID=22, AD_Reference_Value_ID=NULL, AD_Table_ID=261, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Rate', ColumnSQL=NULL, DefaultValue=NULL, Description='Rate or Tax or Exchange', EntityType='D', FieldLength=22, Help='The Rate indicates the percentage to be multiplied by the source to arrive at the tax or exchange amount.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Rate', ReadOnlyLogic=NULL, VFormat=NULL, ValueMin='0', Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=3693
;

-- Mar 3, 2008 10:13:08 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Rate', Description='Rate or Tax or Exchange', Help='The Rate indicates the percentage to be multiplied by the source to arrive at the tax or exchange amount.' WHERE AD_Column_ID=3693 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:08 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=1066, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=261, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='RequiresTaxCertificate', ColumnSQL=NULL, DefaultValue=NULL, Description='This tax rate requires the Business Partner to be tax exempt', EntityType='D', FieldLength=1, Help='The Requires Tax Certificate indicates that a tax certificate is required for a Business Partner to be tax exempt.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Requires Tax Certificate', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=3695
;

-- Mar 3, 2008 10:13:08 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Requires Tax Certificate', Description='This tax rate requires the Business Partner to be tax exempt', Help='The Requires Tax Certificate indicates that a tax certificate is required for a Business Partner to be tax exempt.' WHERE AD_Column_ID=3695 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:09 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=1135, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=261, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='TaxIndicator', ColumnSQL=NULL, DefaultValue=NULL, Description='Short form for Tax to be printed on documents', EntityType='D', FieldLength=10, Help='The Tax Indicator identifies the short name that will print on documents referencing this tax.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='Y', IsUpdateable='Y', Name='Tax Indicator', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=3724
;

-- Mar 3, 2008 10:13:09 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Tax Indicator', Description='Short form for Tax to be printed on documents', Help='The Tax Indicator identifies the short name that will print on documents referencing this tax.' WHERE AD_Column_ID=3724 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:09 PM CST
-- Tax Global Management
UPDATE AD_Reference SET Description='Country selection', EntityType='D', Help=NULL, IsActive='Y', Name='C_Country', ValidationType='T',Updated=TO_TIMESTAMP('2008-03-03 22:13:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=156
;

-- Mar 3, 2008 10:13:09 PM CST
-- Tax Global Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=156
;

-- Mar 3, 2008 10:13:09 PM CST
-- Tax Global Management
UPDATE AD_Ref_Table SET AD_Table_ID = 170, AD_Display = 949, AD_Key = 941, isValueDisplayed = 'N', OrderByClause = 'C_Country.Name', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 156
;

-- Mar 3, 2008 10:13:10 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=594, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=156, AD_Table_ID=261, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='To_Country_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Receiving Country', EntityType='D', FieldLength=22, Help='The To Country indicates the receiving country on a document', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='To', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=2252
;

-- Mar 3, 2008 10:13:10 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='To', Description='Receiving Country', Help='The To Country indicates the receiving country on a document' WHERE AD_Column_ID=2252 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:10 PM CST
-- Tax Global Management
UPDATE AD_Reference SET Description='Region selection', EntityType='D', Help=NULL, IsActive='Y', Name='C_Region', ValidationType='T',Updated=TO_TIMESTAMP('2008-03-03 22:13:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=157
;

-- Mar 3, 2008 10:13:10 PM CST
-- Tax Global Management
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=157
;

-- Mar 3, 2008 10:13:10 PM CST
-- Tax Global Management
UPDATE AD_Ref_Table SET AD_Table_ID = 164, AD_Display = 864, AD_Key = 856, isValueDisplayed = 'N', OrderByClause = '', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 157
;

-- Mar 3, 2008 10:13:10 PM CST
-- Tax Global Management
UPDATE AD_Val_Rule SET Code='C_Region.C_Country_ID=@To_Country_ID@', Description=NULL, EntityType='D', IsActive='Y', Name='C_Region of To_Country', Type='S',Updated=TO_TIMESTAMP('2008-03-03 22:13:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Val_Rule_ID=155
;

-- Mar 3, 2008 10:13:10 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=595, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=157, AD_Table_ID=261, AD_Val_Rule_ID=155, Callout=NULL, ColumnName='To_Region_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Receiving Region', EntityType='D', FieldLength=22, Help='The To Region indicates the receiving region on a document', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='To', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=2253
;

-- Mar 3, 2008 10:13:10 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='To', Description='Receiving Region', Help='The To Region indicates the receiving region on a document' WHERE AD_Column_ID=2253 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:11 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=607, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=261, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Updated', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was updated', EntityType='D', FieldLength=7, Help='The Updated field indicates the date that this record was updated.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=2276
;

-- Mar 3, 2008 10:13:11 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Updated', Description='Date this record was updated', Help='The Updated field indicates the date that this record was updated.' WHERE AD_Column_ID=2276 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:11 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=608, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=261, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='UpdatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who updated this records', EntityType='D', FieldLength=22, Help='The Updated By field indicates the user who updated this record.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated By', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=2277
;

-- Mar 3, 2008 10:13:11 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Updated By', Description='User who updated this records', Help='The Updated By field indicates the user who updated this record.' WHERE AD_Column_ID=2277 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:12 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=102, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=261, AD_Val_Rule_ID=129, Callout=NULL, ColumnName='AD_Client_ID', ColumnSQL=NULL, DefaultValue='@#AD_Client_ID@', Description='Client/Tenant for this installation.', EntityType='D', FieldLength=22, Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Client', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=2241
;

-- Mar 3, 2008 10:13:12 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Client', Description='Client/Tenant for this installation.', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.' WHERE AD_Column_ID=2241 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:12 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=617, AD_Process_ID=NULL, AD_Reference_ID=15, AD_Reference_Value_ID=NULL, AD_Table_ID=261, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='ValidFrom', ColumnSQL=NULL, DefaultValue=NULL, Description='Valid from including this date (first day)', EntityType='D', FieldLength=7, Help='The Valid From date indicates the first day of a date range', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Valid from', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=3054
;

-- Mar 3, 2008 10:13:12 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Valid from', Description='Valid from including this date (first day)', Help='The Valid From date indicates the first day of a date range' WHERE AD_Column_ID=3054 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:13 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=113, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=261, AD_Val_Rule_ID=104, Callout=NULL, ColumnName='AD_Org_ID', ColumnSQL=NULL, DefaultValue='@#AD_Org_ID@', Description='Organizational entity within client', EntityType='D', FieldLength=22, Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Organization', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=2242
;

-- Mar 3, 2008 10:13:13 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Organization', Description='Organizational entity within client', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.' WHERE AD_Column_ID=2242 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:14 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=192, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=156, AD_Table_ID=261, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='C_Country_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Country ', EntityType='D', FieldLength=22, Help='The Country defines a Country.  Each Country must be defined before it can be used in any document.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Country', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=2250
;

-- Mar 3, 2008 10:13:14 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Country', Description='Country ', Help='The Country defines a Country.  Each Country must be defined before it can be used in any document.' WHERE AD_Column_ID=2250 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:14 PM CST
-- Tax Global Management
UPDATE AD_Val_Rule SET Code='C_Region.C_Country_ID=@C_Country_ID@', Description=NULL, EntityType='D', IsActive='Y', Name='C_Region of Country', Type='S',Updated=TO_TIMESTAMP('2008-03-03 22:13:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Val_Rule_ID=153
;

-- Mar 3, 2008 10:13:14 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=209, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=157, AD_Table_ID=261, AD_Val_Rule_ID=153, Callout=NULL, ColumnName='C_Region_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Identifies a geographical Region', EntityType='D', FieldLength=22, Help='The Region identifies a unique Region for this Country.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Region', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=2251
;

-- Mar 3, 2008 10:13:14 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Region', Description='Identifies a geographical Region', Help='The Region identifies a unique Region for this Country.' WHERE AD_Column_ID=2251 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:15 PM CST
-- Tax Global Management
UPDATE AD_Tab SET AD_Table_ID=261, AD_Window_ID=137, CommitWarning=NULL, Description='Tax definition', EntityType='D', HasTree='N', Help='The Tax Rate Window defines the different taxes used for each tax category.  For example Sales Tax must be defined for each State in which it applies.<br>
If you have multiple taxes create a summary level tax with the approximate total tax rate and the actual tax rates pointing to the summary level tax as their parent. When entering the order or invoice lines the tax is estimated the correct tax is calculated when the document is processed.  The tax is always calculated from the line net amount. If one tax has a the tax basis the line net amount and another tax you need to adjust the percentage to result in the correct amount.<br>
Valid From/To is determined by the parent tax.', ImportFields=NULL, IsActive='Y', IsAdvancedTab='N', IsInfoTab='N', IsInsertRecord='Y', IsReadOnly='N', IsSingleRow='Y', IsSortTab='N', IsTranslationTab='N', Name='Tax', OrderByClause=NULL, Processing='N', SeqNo=10, TabLevel=0, WhereClause=NULL,Updated=TO_TIMESTAMP('2008-03-03 22:13:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=174
;

-- Mar 3, 2008 10:13:15 PM CST
-- Tax Global Management
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=174
;

-- Mar 3, 2008 10:13:15 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=2240, AD_FieldGroup_ID=NULL, AD_Tab_ID=174, Description='Tax identifier', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='The Tax indicates the type of tax used in document line.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='N', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Tax', SeqNo=0, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=967
;

-- Mar 3, 2008 10:13:15 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=2241, AD_FieldGroup_ID=NULL, AD_Tab_ID=174, Description='Client/Tenant for this installation.', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Client', SeqNo=10, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=968
;

-- Mar 3, 2008 10:13:16 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=2242, AD_FieldGroup_ID=NULL, AD_Tab_ID=174, Description='Organizational entity within client', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Organization', SeqNo=20, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=2040
;

-- Mar 3, 2008 10:13:16 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=2246, AD_FieldGroup_ID=NULL, AD_Tab_ID=174, Description='Alphanumeric identifier of the entity', DisplayLength=60, DisplayLogic=NULL, EntityType='D', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Name', SeqNo=30, SortNo=1,Updated=TO_TIMESTAMP('2008-03-03 22:13:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=970
;

-- Mar 3, 2008 10:13:16 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=2247, AD_FieldGroup_ID=NULL, AD_Tab_ID=174, Description='Optional short description of the record', DisplayLength=60, DisplayLogic=NULL, EntityType='D', Help='A description is limited to 255 characters.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Description', SeqNo=40, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=971
;

-- Mar 3, 2008 10:13:17 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=2243, AD_FieldGroup_ID=NULL, AD_Tab_ID=174, Description='The record is active in the system', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Active', SeqNo=50, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=969
;

-- Mar 3, 2008 10:13:17 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=4211, AD_FieldGroup_ID=NULL, AD_Tab_ID=174, Description='Default value', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help='The Default Checkbox indicates if this record will be used as a default value.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Default', SeqNo=60, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=3145
;

-- Mar 3, 2008 10:13:18 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=2254, AD_FieldGroup_ID=NULL, AD_Tab_ID=174, Description='Tax Category', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='The Tax Category provides a method of grouping similar taxes.  For example, Sales Tax or Value Added Tax.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Tax Category', SeqNo=70, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=978
;

-- Mar 3, 2008 10:13:18 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=3054, AD_FieldGroup_ID=NULL, AD_Tab_ID=174, Description='Valid from including this date (first day)', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='The Valid From date indicates the first day of a date range', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Valid from', SeqNo=80, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=2093
;

-- Mar 3, 2008 10:13:18 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=7971, AD_FieldGroup_ID=NULL, AD_Tab_ID=174, Description='Business partner is exempt from tax', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help='If a business partner is exempt from tax, the exempt tax rate is used. For this, you need to set up a tax rate with a 0% rate and indicate that this is your tax exempt rate.  This is required for tax reporting, so that you can track tax exempt transactions.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Tax exempt', SeqNo=90, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=6121
;

-- Mar 3, 2008 10:13:19 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=3695, AD_FieldGroup_ID=NULL, AD_Tab_ID=174, Description='This tax rate requires the Business Partner to be tax exempt', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help='The Requires Tax Certificate indicates that a tax certificate is required for a Business Partner to be tax exempt.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Requires Tax Certificate', SeqNo=100, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=2872
;

-- Mar 3, 2008 10:13:19 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=3053, AD_FieldGroup_ID=NULL, AD_Tab_ID=174, Description='Tax is calculated on document level (rather than line by line)', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help='If the tax is calculated on document level, all lines with that tax rate are added before calculating the total tax for the document.
Otherwise the tax is calculated per line and then added.
Due to rounding, the tax amount can differ.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Document Level', SeqNo=110, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=2091
;

-- Mar 3, 2008 10:13:20 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=14528, AD_FieldGroup_ID=NULL, AD_Tab_ID=174, Description='This is a sales tax (i.e. not a value added tax)', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help='If selected AP tax is handled as expense, otherwise it is handeled as a VAT credit.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Sales Tax', SeqNo=120, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=12489
;

-- Mar 3, 2008 10:13:20 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=3055, AD_FieldGroup_ID=NULL, AD_Tab_ID=174, Description='This is a summary entity', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help='A summary entity represents a branch in a tree rather than an end-node. Summary entities are used for reporting and do not have own values.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Summary Level', SeqNo=130, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=2092
;

-- Mar 3, 2008 10:13:20 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=2249, AD_FieldGroup_ID=NULL, AD_Tab_ID=174, Description='Parent Tax indicates a tax that is made up of multiple taxes', DisplayLength=14, DisplayLogic='@IsSummary@=''N''', EntityType='D', Help='The Parent Tax indicates a tax that is a reference for multiple taxes.  This allows you to charge multiple taxes on a document by entering the Parent Tax', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Parent Tax', SeqNo=140, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=973
;

-- Mar 3, 2008 10:13:21 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=9767, AD_FieldGroup_ID=NULL, AD_Tab_ID=174, Description='Sales Tax applies to sales situations, Purchase Tax to purchase situations', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='Sales Tax: charged when selling - examples: Sales Tax, Output VAT (payable)
Purchase Tax: tax charged when purchasing - examples: Use Tax, Input VAT (receivable)', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='SO/PO Type', SeqNo=150, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=8195
;

-- Mar 3, 2008 10:13:21 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=3724, AD_FieldGroup_ID=NULL, AD_Tab_ID=174, Description='Short form for Tax to be printed on documents', DisplayLength=5, DisplayLogic=NULL, EntityType='D', Help='The Tax Indicator identifies the short name that will print on documents referencing this tax.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Tax Indicator', SeqNo=160, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=3076
;

-- Mar 3, 2008 10:13:21 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=3693, AD_FieldGroup_ID=NULL, AD_Tab_ID=174, Description='Rate or Tax or Exchange', DisplayLength=26, DisplayLogic=NULL, EntityType='D', Help='The Rate indicates the percentage to be multiplied by the source to arrive at the tax or exchange amount.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Rate', SeqNo=170, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=2871
;

-- Mar 3, 2008 10:13:22 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54408,54440,0,174,TO_TIMESTAMP('2008-03-03 22:13:21','YYYY-MM-DD HH24:MI:SS'),0,22,'EE04','Y','Y','Y','N','N','N','Y','Rule',180,0,TO_TIMESTAMP('2008-03-03 22:13:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:13:22 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54440 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:13:23 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=2250, AD_FieldGroup_ID=NULL, AD_Tab_ID=174, Description='Country ', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='The Country defines a Country.  Each Country must be defined before it can be used in any document.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Country', SeqNo=190, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=974
;

-- Mar 3, 2008 10:13:23 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=2252, AD_FieldGroup_ID=NULL, AD_Tab_ID=174, Description='Receiving Country', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='The To Country indicates the receiving country on a document', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='To', SeqNo=200, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=976
;

-- Mar 3, 2008 10:13:24 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=2251, AD_FieldGroup_ID=NULL, AD_Tab_ID=174, Description='Identifies a geographical Region', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='The Region identifies a unique Region for this Country.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Region', SeqNo=210, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=975
;

-- Mar 3, 2008 10:13:24 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=2253, AD_FieldGroup_ID=NULL, AD_Tab_ID=174, Description='Receiving Region', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='The To Region indicates the receiving region on a document', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='To', SeqNo=220, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=977
;

-- Mar 3, 2008 10:13:24 PM CST
-- Tax Global Management
UPDATE AD_Table SET AD_Window_ID=137, AccessLevel='2', Description='Tax Postal/ZIP', EntityType='D', Help=NULL, ImportTable=NULL, IsActive='Y', IsChangeLog='N', IsDeleteable='Y', IsHighVolume='N', IsSecurityEnabled='N', IsView='N', Name='Tax ZIP', ReplicationType='L', TableName='C_TaxPostal',Updated=TO_TIMESTAMP('2008-03-03 22:13:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=701
;

-- Mar 3, 2008 10:13:25 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=608, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=701, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='UpdatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who updated this records', EntityType='D', FieldLength=22, Help='The Updated By field indicates the user who updated this record.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated By', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11460
;

-- Mar 3, 2008 10:13:25 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Updated By', Description='User who updated this records', Help='The Updated By field indicates the user who updated this record.' WHERE AD_Column_ID=11460 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:25 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=113, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=701, AD_Val_Rule_ID=104, Callout=NULL, ColumnName='AD_Org_ID', ColumnSQL=NULL, DefaultValue='@AD_Org_ID@', Description='Organizational entity within client', EntityType='D', FieldLength=22, Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Organization', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11461
;

-- Mar 3, 2008 10:13:25 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Organization', Description='Organizational entity within client', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.' WHERE AD_Column_ID=11461 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:26 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=2450, AD_Process_ID=NULL, AD_Reference_ID=13, AD_Reference_Value_ID=NULL, AD_Table_ID=701, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='C_TaxPostal_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Tax Postal/ZIP', EntityType='D', FieldLength=22, Help='For local tax, you may have to define a list of (ranges of) postal codes or ZIPs', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='Y', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Tax ZIP', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11456
;

-- Mar 3, 2008 10:13:26 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Tax ZIP', Description='Tax Postal/ZIP', Help='For local tax, you may have to define a list of (ranges of) postal codes or ZIPs' WHERE AD_Column_ID=11456 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:27 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=213, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=701, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='C_Tax_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Tax identifier', EntityType='D', FieldLength=22, Help='The Tax indicates the type of tax used in document line.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='Y', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Tax', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11463
;

-- Mar 3, 2008 10:13:27 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Tax', Description='Tax identifier', Help='The Tax indicates the type of tax used in document line.' WHERE AD_Column_ID=11463 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:27 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=245, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=701, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Created', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was created', EntityType='D', FieldLength=7, Help='The Created field indicates the date that this record was created.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11458
;

-- Mar 3, 2008 10:13:27 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Created', Description='Date this record was created', Help='The Created field indicates the date that this record was created.' WHERE AD_Column_ID=11458 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:28 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=246, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=701, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='CreatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who created this records', EntityType='D', FieldLength=22, Help='The Created By field indicates the user who created this record.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created By', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11459
;

-- Mar 3, 2008 10:13:28 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Created By', Description='User who created this records', Help='The Created By field indicates the user who created this record.' WHERE AD_Column_ID=11459 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:28 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=348, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=701, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsActive', ColumnSQL=NULL, DefaultValue='Y', Description='The record is active in the system', EntityType='D', FieldLength=1, Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Active', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11457
;

-- Mar 3, 2008 10:13:28 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Active', Description='The record is active in the system', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.' WHERE AD_Column_ID=11457 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:29 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=607, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=701, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Updated', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was updated', EntityType='D', FieldLength=7, Help='The Updated field indicates the date that this record was updated.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11464
;

-- Mar 3, 2008 10:13:29 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Updated', Description='Date this record was updated', Help='The Updated field indicates the date that this record was updated.' WHERE AD_Column_ID=11464 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:30 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=102, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=701, AD_Val_Rule_ID=129, Callout=NULL, ColumnName='AD_Client_ID', ColumnSQL=NULL, DefaultValue='@AD_Client_ID@', Description='Client/Tenant for this installation.', EntityType='D', FieldLength=22, Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Client', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11455
;

-- Mar 3, 2008 10:13:30 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Client', Description='Client/Tenant for this installation.', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.' WHERE AD_Column_ID=11455 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:30 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=512, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=701, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Postal', ColumnSQL=NULL, DefaultValue=NULL, Description='Postal code', EntityType='D', FieldLength=10, Help='The Postal Code or ZIP identifies the postal code for this entity''s address.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='Y', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='ZIP', ReadOnlyLogic=NULL, SeqNo=1, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11465
;

-- Mar 3, 2008 10:13:30 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='ZIP', Description='Postal code', Help='The Postal Code or ZIP identifies the postal code for this entity''s address.' WHERE AD_Column_ID=11465 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:31 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=2455, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=701, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Postal_To', ColumnSQL=NULL, DefaultValue=NULL, Description='Postal code to', EntityType='D', FieldLength=10, Help='Conecutive range to', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='Y', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='ZIP To', ReadOnlyLogic=NULL, SeqNo=2, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11462
;

-- Mar 3, 2008 10:13:31 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='ZIP To', Description='Postal code to', Help='Conecutive range to' WHERE AD_Column_ID=11462 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:31 PM CST
-- Tax Global Management
UPDATE AD_Tab SET AD_Table_ID=701, AD_Window_ID=137, CommitWarning=NULL, Description='Tax Postal/ZIP', EntityType='D', HasTree='N', Help='For local tax you may have to define a list of (ranges of) postal codes or ZIPs', ImportFields=NULL, IsActive='Y', IsAdvancedTab='N', IsInfoTab='N', IsInsertRecord='Y', IsReadOnly='N', IsSingleRow='N', IsSortTab='N', IsTranslationTab='N', Name='Tax ZIP', OrderByClause=NULL, Processing='N', SeqNo=20, TabLevel=1, WhereClause=NULL,Updated=TO_TIMESTAMP('2008-03-03 22:13:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=640
;

-- Mar 3, 2008 10:13:31 PM CST
-- Tax Global Management
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=640
;

-- Mar 3, 2008 10:13:32 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=11456, AD_FieldGroup_ID=NULL, AD_Tab_ID=640, Description='Tax Postal/ZIP', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='For local tax, you may have to define a list of (ranges of) postal codes or ZIPs', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='N', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Tax ZIP', SeqNo=10, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=10062
;

-- Mar 3, 2008 10:13:32 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=11455, AD_FieldGroup_ID=NULL, AD_Tab_ID=640, Description='Client/Tenant for this installation.', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Client', SeqNo=20, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=10061
;

-- Mar 3, 2008 10:13:33 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=11461, AD_FieldGroup_ID=NULL, AD_Tab_ID=640, Description='Organizational entity within client', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='Y', Name='Organization', SeqNo=30, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=10064
;

-- Mar 3, 2008 10:13:33 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=11463, AD_FieldGroup_ID=NULL, AD_Tab_ID=640, Description='Tax identifier', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='The Tax indicates the type of tax used in document line.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Tax', SeqNo=40, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=10066
;

-- Mar 3, 2008 10:13:33 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=11457, AD_FieldGroup_ID=NULL, AD_Tab_ID=640, Description='The record is active in the system', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Active', SeqNo=50, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=10063
;

-- Mar 3, 2008 10:13:34 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=11465, AD_FieldGroup_ID=NULL, AD_Tab_ID=640, Description='Postal code', DisplayLength=11, DisplayLogic=NULL, EntityType='D', Help='The Postal Code or ZIP identifies the postal code for this entity''s address.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='ZIP', SeqNo=60, SortNo=1,Updated=TO_TIMESTAMP('2008-03-03 22:13:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=10067
;

-- Mar 3, 2008 10:13:34 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=11462, AD_FieldGroup_ID=NULL, AD_Tab_ID=640, Description='Postal code to', DisplayLength=11, DisplayLogic=NULL, EntityType='D', Help='Conecutive range to', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='ZIP To', SeqNo=70, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=10065
;

-- Mar 3, 2008 10:13:34 PM CST
-- Tax Global Management
UPDATE AD_Table SET AD_Window_ID=137, AccessLevel='2', Description='Tax identifier', EntityType='D', Help=NULL, ImportTable=NULL, IsActive='Y', IsChangeLog='N', IsDeleteable='Y', IsHighVolume='N', IsSecurityEnabled='N', IsView='N', Name='Tax Trl', ReplicationType='L', TableName='C_Tax_Trl',Updated=TO_TIMESTAMP('2008-03-03 22:13:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=546
;

-- Mar 3, 2008 10:13:35 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=102, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=546, AD_Val_Rule_ID=129, Callout=NULL, ColumnName='AD_Client_ID', ColumnSQL=NULL, DefaultValue='@AD_Client_ID@', Description='Client/Tenant for this installation.', EntityType='D', FieldLength=22, Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Client', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=8206
;

-- Mar 3, 2008 10:13:35 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Client', Description='Client/Tenant for this installation.', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.' WHERE AD_Column_ID=8206 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:36 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=109, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=106, AD_Table_ID=546, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_Language', ColumnSQL=NULL, DefaultValue=NULL, Description='Language for this entity', EntityType='D', FieldLength=6, Help='The Language identifies the language to use for display and formatting', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='Y', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Language', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=8202
;

-- Mar 3, 2008 10:13:36 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Language', Description='Language for this entity', Help='The Language identifies the language to use for display and formatting' WHERE AD_Column_ID=8202 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:37 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=113, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=546, AD_Val_Rule_ID=104, Callout=NULL, ColumnName='AD_Org_ID', ColumnSQL=NULL, DefaultValue='@AD_Org_ID@', Description='Organizational entity within client', EntityType='D', FieldLength=22, Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Organization', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=8211
;

-- Mar 3, 2008 10:13:37 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Organization', Description='Organizational entity within client', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.' WHERE AD_Column_ID=8211 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:37 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=213, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=546, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='C_Tax_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Tax identifier', EntityType='D', FieldLength=22, Help='The Tax indicates the type of tax used in document line.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='Y', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Tax', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=8201
;

-- Mar 3, 2008 10:13:37 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Tax', Description='Tax identifier', Help='The Tax indicates the type of tax used in document line.' WHERE AD_Column_ID=8201 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:38 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=245, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=546, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Created', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was created', EntityType='D', FieldLength=7, Help='The Created field indicates the date that this record was created.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=8205
;

-- Mar 3, 2008 10:13:38 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Created', Description='Date this record was created', Help='The Created field indicates the date that this record was created.' WHERE AD_Column_ID=8205 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:38 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=246, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=546, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='CreatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who created this records', EntityType='D', FieldLength=22, Help='The Created By field indicates the user who created this record.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created By', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=8207
;

-- Mar 3, 2008 10:13:38 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Created By', Description='User who created this records', Help='The Created By field indicates the user who created this record.' WHERE AD_Column_ID=8207 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:39 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=275, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=546, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Description', ColumnSQL=NULL, DefaultValue=NULL, Description='Optional short description of the record', EntityType='D', FieldLength=255, Help='A description is limited to 255 characters.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Description', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=8203
;

-- Mar 3, 2008 10:13:39 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Description', Description='Optional short description of the record', Help='A description is limited to 255 characters.' WHERE AD_Column_ID=8203 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:39 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=348, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=546, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsActive', ColumnSQL=NULL, DefaultValue='Y', Description='The record is active in the system', EntityType='D', FieldLength=1, Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Active', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=8209
;

-- Mar 3, 2008 10:13:39 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Active', Description='The record is active in the system', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.' WHERE AD_Column_ID=8209 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:40 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=420, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=546, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsTranslated', ColumnSQL=NULL, DefaultValue=NULL, Description='This column is translated', EntityType='D', FieldLength=1, Help='The Translated checkbox indicates if this column is translated.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Translated', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=8204
;

-- Mar 3, 2008 10:13:40 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Translated', Description='This column is translated', Help='The Translated checkbox indicates if this column is translated.' WHERE AD_Column_ID=8204 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:40 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=469, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=546, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Name', ColumnSQL=NULL, DefaultValue=NULL, Description='Alphanumeric identifier of the entity', EntityType='D', FieldLength=60, Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Name', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=8210
;

-- Mar 3, 2008 10:13:40 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Name', Description='Alphanumeric identifier of the entity', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.' WHERE AD_Column_ID=8210 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:41 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=1135, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=546, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='TaxIndicator', ColumnSQL=NULL, DefaultValue=NULL, Description='Short form for Tax to be printed on documents', EntityType='D', FieldLength=10, Help='The Tax Indicator identifies the short name that will print on documents referencing this tax.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Tax Indicator', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=8199
;

-- Mar 3, 2008 10:13:41 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Tax Indicator', Description='Short form for Tax to be printed on documents', Help='The Tax Indicator identifies the short name that will print on documents referencing this tax.' WHERE AD_Column_ID=8199 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:42 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=607, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=546, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Updated', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was updated', EntityType='D', FieldLength=7, Help='The Updated field indicates the date that this record was updated.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=8208
;

-- Mar 3, 2008 10:13:42 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Updated', Description='Date this record was updated', Help='The Updated field indicates the date that this record was updated.' WHERE AD_Column_ID=8208 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:42 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=608, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=546, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='UpdatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who updated this records', EntityType='D', FieldLength=22, Help='The Updated By field indicates the user who updated this record.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated By', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=8200
;

-- Mar 3, 2008 10:13:42 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Updated By', Description='User who updated this records', Help='The Updated By field indicates the user who updated this record.' WHERE AD_Column_ID=8200 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:43 PM CST
-- Tax Global Management
UPDATE AD_Tab SET AD_Column_ID=8201, AD_Table_ID=546, AD_Window_ID=137, CommitWarning=NULL, Description=NULL, EntityType='D', HasTree='N', Help=NULL, ImportFields=NULL, IsActive='Y', IsAdvancedTab='N', IsInfoTab='N', IsInsertRecord='N', IsReadOnly='N', IsSingleRow='Y', IsSortTab='N', IsTranslationTab='Y', Name='Translation', OrderByClause=NULL, Processing='N', SeqNo=30, TabLevel=1, WhereClause=NULL,Updated=TO_TIMESTAMP('2008-03-03 22:13:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=458
;

-- Mar 3, 2008 10:13:43 PM CST
-- Tax Global Management
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=458
;

-- Mar 3, 2008 10:13:43 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=8206, AD_FieldGroup_ID=NULL, AD_Tab_ID=458, Description='Client/Tenant for this installation.', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Client', SeqNo=10, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=6280
;

-- Mar 3, 2008 10:13:43 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=8211, AD_FieldGroup_ID=NULL, AD_Tab_ID=458, Description='Organizational entity within client', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='Y', Name='Organization', SeqNo=20, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=6283
;

-- Mar 3, 2008 10:13:44 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=8201, AD_FieldGroup_ID=NULL, AD_Tab_ID=458, Description='Tax identifier', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='The Tax indicates the type of tax used in document line.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Tax', SeqNo=30, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=6276
;

-- Mar 3, 2008 10:13:44 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=8202, AD_FieldGroup_ID=NULL, AD_Tab_ID=458, Description='Language for this entity', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='The Language identifies the language to use for display and formatting', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Language', SeqNo=40, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=6277
;

-- Mar 3, 2008 10:13:45 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=8209, AD_FieldGroup_ID=NULL, AD_Tab_ID=458, Description='The record is active in the system', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Active', SeqNo=50, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=6281
;

-- Mar 3, 2008 10:13:45 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=8204, AD_FieldGroup_ID=NULL, AD_Tab_ID=458, Description='This column is translated', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help='The Translated checkbox indicates if this column is translated.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Translated', SeqNo=60, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=6279
;

-- Mar 3, 2008 10:13:45 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=8210, AD_FieldGroup_ID=NULL, AD_Tab_ID=458, Description='Alphanumeric identifier of the entity', DisplayLength=60, DisplayLogic=NULL, EntityType='D', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Name', SeqNo=70, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=6282
;

-- Mar 3, 2008 10:13:46 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=8203, AD_FieldGroup_ID=NULL, AD_Tab_ID=458, Description='Optional short description of the record', DisplayLength=60, DisplayLogic=NULL, EntityType='D', Help='A description is limited to 255 characters.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Description', SeqNo=80, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=6278
;

-- Mar 3, 2008 10:13:46 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=8199, AD_FieldGroup_ID=NULL, AD_Tab_ID=458, Description='Short form for Tax to be printed on documents', DisplayLength=5, DisplayLogic=NULL, EntityType='D', Help='The Tax Indicator identifies the short name that will print on documents referencing this tax.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Tax Indicator', SeqNo=90, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=6275
;

-- Mar 3, 2008 10:13:46 PM CST
-- Tax Global Management
UPDATE AD_Table SET AD_Window_ID=137, AccessLevel='3', Description=NULL, EntityType='D', Help=NULL, ImportTable=NULL, IsActive='Y', IsChangeLog='N', IsDeleteable='N', IsHighVolume='N', IsSecurityEnabled='N', IsView='N', Name='C_Tax_Acct', ReplicationType='L', TableName='C_Tax_Acct',Updated=TO_TIMESTAMP('2008-03-03 22:13:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=399
;

-- Mar 3, 2008 10:13:47 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=102, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=399, AD_Val_Rule_ID=129, Callout=NULL, ColumnName='AD_Client_ID', ColumnSQL=NULL, DefaultValue='@AD_Client_ID@', Description='Client/Tenant for this installation.', EntityType='D', FieldLength=22, Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Client', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=5077
;

-- Mar 3, 2008 10:13:47 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Client', Description='Client/Tenant for this installation.', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.' WHERE AD_Column_ID=5077 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:48 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=113, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=399, AD_Val_Rule_ID=104, Callout=NULL, ColumnName='AD_Org_ID', ColumnSQL=NULL, DefaultValue='@AD_Org_ID@', Description='Organizational entity within client', EntityType='D', FieldLength=22, Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Organization', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=5078
;

-- Mar 3, 2008 10:13:48 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Organization', Description='Organizational entity within client', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.' WHERE AD_Column_ID=5078 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:48 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=181, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=399, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='C_AcctSchema_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Rules for accounting', EntityType='D', FieldLength=22, Help='An Accounting Schema defines the rules used in accounting such as costing method, currency and calendar', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='Y', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Accounting Schema', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=5076
;

-- Mar 3, 2008 10:13:48 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Accounting Schema', Description='Rules for accounting', Help='An Accounting Schema defines the rules used in accounting such as costing method, currency and calendar' WHERE AD_Column_ID=5076 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:49 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=213, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=399, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='C_Tax_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Tax identifier', EntityType='D', FieldLength=22, Help='The Tax indicates the type of tax used in document line.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='Y', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Tax', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=5075
;

-- Mar 3, 2008 10:13:49 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Tax', Description='Tax identifier', Help='The Tax indicates the type of tax used in document line.' WHERE AD_Column_ID=5075 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:50 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=245, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=399, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Created', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was created', EntityType='D', FieldLength=7, Help='The Created field indicates the date that this record was created.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=5080
;

-- Mar 3, 2008 10:13:50 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Created', Description='Date this record was created', Help='The Created field indicates the date that this record was created.' WHERE AD_Column_ID=5080 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:50 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=246, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=399, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='CreatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who created this records', EntityType='D', FieldLength=22, Help='The Created By field indicates the user who created this record.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created By', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=5081
;

-- Mar 3, 2008 10:13:50 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Created By', Description='User who created this records', Help='The Created By field indicates the user who created this record.' WHERE AD_Column_ID=5081 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:51 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=348, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=399, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsActive', ColumnSQL=NULL, DefaultValue='Y', Description='The record is active in the system', EntityType='D', FieldLength=1, Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Active', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=5079
;

-- Mar 3, 2008 10:13:51 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Active', Description='The record is active in the system', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.' WHERE AD_Column_ID=5079 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:51 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=1436, AD_Process_ID=NULL, AD_Reference_ID=25, AD_Reference_Value_ID=NULL, AD_Table_ID=399, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='T_Credit_Acct', ColumnSQL=NULL, DefaultValue=NULL, Description='Account for Tax you can reclaim', EntityType='D', FieldLength=22, Help='The Tax Credit Account indicates the account used to record taxes that can be reclaimed', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Tax Credit', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=5086
;

-- Mar 3, 2008 10:13:51 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Tax Credit', Description='Account for Tax you can reclaim', Help='The Tax Credit Account indicates the account used to record taxes that can be reclaimed' WHERE AD_Column_ID=5086 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:52 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=1437, AD_Process_ID=NULL, AD_Reference_ID=25, AD_Reference_Value_ID=NULL, AD_Table_ID=399, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='T_Due_Acct', ColumnSQL=NULL, DefaultValue=NULL, Description='Account for Tax you have to pay', EntityType='D', FieldLength=22, Help='The Tax Due Account indicates the account used to record taxes that you are liable to pay.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Tax Due', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=5084
;

-- Mar 3, 2008 10:13:52 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Tax Due', Description='Account for Tax you have to pay', Help='The Tax Due Account indicates the account used to record taxes that you are liable to pay.' WHERE AD_Column_ID=5084 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:52 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=1438, AD_Process_ID=NULL, AD_Reference_ID=25, AD_Reference_Value_ID=NULL, AD_Table_ID=399, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='T_Expense_Acct', ColumnSQL=NULL, DefaultValue=NULL, Description='Account for paid tax you cannot reclaim', EntityType='D', FieldLength=22, Help='The Tax Expense Account indicates the account used to record the taxes that have been paid that cannot be reclaimed.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Tax Expense', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=5088
;

-- Mar 3, 2008 10:13:52 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Tax Expense', Description='Account for paid tax you cannot reclaim', Help='The Tax Expense Account indicates the account used to record the taxes that have been paid that cannot be reclaimed.' WHERE AD_Column_ID=5088 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:53 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=1439, AD_Process_ID=NULL, AD_Reference_ID=25, AD_Reference_Value_ID=NULL, AD_Table_ID=399, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='T_Liability_Acct', ColumnSQL=NULL, DefaultValue=NULL, Description='Account for Tax declaration liability', EntityType='D', FieldLength=22, Help='The Tax Liability Account indicates the account used to record your tax liability declaration.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Tax Liability', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=5085
;

-- Mar 3, 2008 10:13:53 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Tax Liability', Description='Account for Tax declaration liability', Help='The Tax Liability Account indicates the account used to record your tax liability declaration.' WHERE AD_Column_ID=5085 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:54 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=1440, AD_Process_ID=NULL, AD_Reference_ID=25, AD_Reference_Value_ID=NULL, AD_Table_ID=399, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='T_Receivables_Acct', ColumnSQL=NULL, DefaultValue=NULL, Description='Account for Tax credit after tax declaration', EntityType='D', FieldLength=22, Help='The Tax Receivables Account indicates the account used to record the tax credit amount after your tax declaration.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Tax Receivables', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=5087
;

-- Mar 3, 2008 10:13:54 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Tax Receivables', Description='Account for Tax credit after tax declaration', Help='The Tax Receivables Account indicates the account used to record the tax credit amount after your tax declaration.' WHERE AD_Column_ID=5087 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:54 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=607, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=399, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Updated', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was updated', EntityType='D', FieldLength=7, Help='The Updated field indicates the date that this record was updated.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=5082
;

-- Mar 3, 2008 10:13:54 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Updated', Description='Date this record was updated', Help='The Updated field indicates the date that this record was updated.' WHERE AD_Column_ID=5082 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:55 PM CST
-- Tax Global Management
UPDATE AD_Column SET AD_Element_ID=608, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=399, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='UpdatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who updated this records', EntityType='D', FieldLength=22, Help='The Updated By field indicates the user who updated this record.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated By', ReadOnlyLogic=NULL, VFormat=NULL, Version=0.0,Updated=TO_TIMESTAMP('2008-03-03 22:13:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=5083
;

-- Mar 3, 2008 10:13:55 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Updated By', Description='User who updated this records', Help='The Updated By field indicates the user who updated this record.' WHERE AD_Column_ID=5083 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:13:55 PM CST
-- Tax Global Management
UPDATE AD_Tab SET AD_Table_ID=399, AD_Window_ID=137, CommitWarning=NULL, Description='Accounting', EntityType='D', HasTree='N', Help='The Accounting Tab defines the accounting parameters to be used for transactions referencing this Tax Rate.', ImportFields=NULL, IsActive='Y', IsAdvancedTab='N', IsInfoTab='Y', IsInsertRecord='Y', IsReadOnly='N', IsSingleRow='Y', IsSortTab='N', IsTranslationTab='N', Name='Accounting', OrderByClause=NULL, Processing='N', SeqNo=40, TabLevel=1, WhereClause=NULL,Updated=TO_TIMESTAMP('2008-03-03 22:13:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=333
;

-- Mar 3, 2008 10:13:55 PM CST
-- Tax Global Management
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=333
;

-- Mar 3, 2008 10:13:56 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=5077, AD_FieldGroup_ID=NULL, AD_Tab_ID=333, Description='Client/Tenant for this installation.', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Client', SeqNo=10, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=4073
;

-- Mar 3, 2008 10:13:56 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=5078, AD_FieldGroup_ID=NULL, AD_Tab_ID=333, Description='Organizational entity within client', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='Y', Name='Organization', SeqNo=20, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=4074
;

-- Mar 3, 2008 10:13:56 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=5075, AD_FieldGroup_ID=NULL, AD_Tab_ID=333, Description='Tax identifier', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='The Tax indicates the type of tax used in document line.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Tax', SeqNo=30, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=4076
;

-- Mar 3, 2008 10:13:57 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=5076, AD_FieldGroup_ID=NULL, AD_Tab_ID=333, Description='Rules for accounting', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='An Accounting Schema defines the rules used in accounting such as costing method, currency and calendar', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Accounting Schema', SeqNo=40, SortNo=1,Updated=TO_TIMESTAMP('2008-03-03 22:13:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=4075
;

-- Mar 3, 2008 10:13:57 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=5079, AD_FieldGroup_ID=NULL, AD_Tab_ID=333, Description='The record is active in the system', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Active', SeqNo=50, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=4077
;

-- Mar 3, 2008 10:13:57 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=5084, AD_FieldGroup_ID=NULL, AD_Tab_ID=333, Description='Account for Tax you have to pay', DisplayLength=26, DisplayLogic=NULL, EntityType='D', Help='The Tax Due Account indicates the account used to record taxes that you are liable to pay.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Tax Due', SeqNo=60, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=4079
;

-- Mar 3, 2008 10:13:58 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=5085, AD_FieldGroup_ID=NULL, AD_Tab_ID=333, Description='Account for Tax declaration liability', DisplayLength=26, DisplayLogic=NULL, EntityType='D', Help='The Tax Liability Account indicates the account used to record your tax liability declaration.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Tax Liability', SeqNo=70, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=4081
;

-- Mar 3, 2008 10:13:58 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=5086, AD_FieldGroup_ID=NULL, AD_Tab_ID=333, Description='Account for Tax you can reclaim', DisplayLength=26, DisplayLogic=NULL, EntityType='D', Help='The Tax Credit Account indicates the account used to record taxes that can be reclaimed', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Tax Credit', SeqNo=80, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=4078
;

-- Mar 3, 2008 10:13:59 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=5087, AD_FieldGroup_ID=NULL, AD_Tab_ID=333, Description='Account for Tax credit after tax declaration', DisplayLength=26, DisplayLogic=NULL, EntityType='D', Help='The Tax Receivables Account indicates the account used to record the tax credit amount after your tax declaration.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Tax Receivables', SeqNo=90, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=4082
;

-- Mar 3, 2008 10:13:59 PM CST
-- Tax Global Management
UPDATE AD_Field SET AD_Column_ID=5088, AD_FieldGroup_ID=NULL, AD_Tab_ID=333, Description='Account for paid tax you cannot reclaim', DisplayLength=26, DisplayLogic=NULL, EntityType='D', Help='The Tax Expense Account indicates the account used to record the taxes that have been paid that cannot be reclaimed.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Tax Expense', SeqNo=100, SortNo=0,Updated=TO_TIMESTAMP('2008-03-03 22:13:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=4080
;

-- Mar 3, 2008 10:14:01 PM CST
-- Tax Global Management
INSERT INTO AD_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AccessLevel,Classname,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value,WorkflowValue) VALUES (0,0,53072,'3','org.eevolution.process.InvoiceCalculateTax',TO_TIMESTAMP('2008-03-03 22:13:59','YYYY-MM-DD HH24:MI:SS'),0,'EE04','Y','N','N','N','C_Invoce Calculate Tax','Y',0,0,TO_TIMESTAMP('2008-03-03 22:13:59','YYYY-MM-DD HH24:MI:SS'),0,'C_Invoce Calculate Tax',NULL)
;

-- Mar 3, 2008 10:14:01 PM CST
-- Tax Global Management
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53072 AND EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_ID!=t.AD_Process_ID)
;

-- Mar 3, 2008 10:14:02 PM CST
-- Tax Global Management
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,1008,0,53072,53134,30,'C_Invoice_ID',TO_TIMESTAMP('2008-03-03 22:14:01','YYYY-MM-DD HH24:MI:SS'),0,'Invoice Identifier','EE04',20,'The Invoice Document.','Y','Y','N','N','Invoice',10,TO_TIMESTAMP('2008-03-03 22:14:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:02 PM CST
-- Tax Global Management
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53134 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Mar 3, 2008 10:14:02 PM CST
-- Tax Global Management
UPDATE AD_Workflow SET AD_WF_Node_ID=NULL, AccessLevel='3', Author='Adempiere', Cost=0, Description='Setup tax calculation', DocValueLogic=NULL, Duration=0, DurationUnit=NULL, EntityType='D', Help='Setup the tax calculation for products, services and charges', IsValid='N', "limit"=0, Name='Tax Setup', Priority=0, PublishStatus='U', Value='Setup Tax', Version=1, WaitingTime=0, WorkflowType='G', WorkingTime=0,Updated=TO_TIMESTAMP('2008-03-03 22:14:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Workflow_ID=110
;

-- Mar 3, 2008 10:14:03 PM CST
-- Tax Global Management
UPDATE AD_WF_Node SET AD_Window_ID=140, AD_Workflow_ID=110, "action"='W', Cost=0, Description='Maintain Products', DocAction=NULL, Duration=0, DynPriorityChange=0, DynPriorityUnit=NULL, EntityType='D', IsActive='Y', IsCentrallyMaintained='N', JoinElement='X', Name='Product', Priority=0, SplitElement='X', StartMode=NULL, SubflowExecution=NULL, Value='Product', WaitTime=0, WaitingTime=0, WorkingTime=0, XPosition=170, YPosition=5,Updated=TO_TIMESTAMP('2008-03-03 22:14:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=167
;

-- Mar 3, 2008 10:14:04 PM CST
-- Tax Global Management
UPDATE AD_WF_Node SET AD_Window_ID=161, AD_Workflow_ID=110, "action"='W', Cost=0, Description='Maintain Charges', DocAction=NULL, Duration=0, DynPriorityChange=0, DynPriorityUnit=NULL, EntityType='D', IsActive='Y', IsCentrallyMaintained='N', JoinElement='X', Name='Charge', Priority=0, SplitElement='X', StartMode=NULL, SubflowExecution=NULL, Value='Charge', WaitTime=0, WaitingTime=0, WorkingTime=0, XPosition=5, YPosition=85,Updated=TO_TIMESTAMP('2008-03-03 22:14:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=168
;

-- Mar 3, 2008 10:14:05 PM CST
-- Tax Global Management
UPDATE AD_WF_Node SET AD_Window_ID=138, AD_Workflow_ID=110, "action"='W', Cost=0, Description='Maintain Tax Categories', DocAction=NULL, Duration=0, DynPriorityChange=0, DynPriorityUnit=NULL, EntityType='D', IsActive='Y', IsCentrallyMaintained='N', JoinElement='X', Name='Tax Category', Priority=0, SplitElement='X', StartMode=NULL, SubflowExecution=NULL, Value='Tax Category', WaitTime=0, WaitingTime=0, WorkingTime=0, XPosition=5, YPosition=5,Updated=TO_TIMESTAMP('2008-03-03 22:14:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=166
;

-- Mar 3, 2008 10:14:06 PM CST
-- Tax Global Management
UPDATE AD_WF_Node SET AD_Window_ID=125, AD_Workflow_ID=110, "action"='W', Cost=0, Description='Maintain Accounting Schema - For changes to become effective you must re-login', DocAction=NULL, Duration=0, DynPriorityChange=0, DynPriorityUnit=NULL, EntityType='D', IsActive='Y', IsCentrallyMaintained='N', JoinElement='X', Name='Accounting Schema', Priority=0, SplitElement='X', StartMode=NULL, SubflowExecution=NULL, Value='Accounting Schema', WaitTime=0, WaitingTime=0, WorkingTime=0, XPosition=5, YPosition=165,Updated=TO_TIMESTAMP('2008-03-03 22:14:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=170
;

-- Mar 3, 2008 10:14:07 PM CST
-- Tax Global Management
UPDATE AD_WF_Node SET AD_Window_ID=137, AD_Workflow_ID=110, "action"='W', Cost=0, Description='Maintain Taxes and their Rates', DocAction=NULL, Duration=0, DynPriorityChange=0, DynPriorityUnit=NULL, EntityType='D', IsActive='Y', IsCentrallyMaintained='N', JoinElement='X', Name='Tax Rate', Priority=0, SplitElement='X', StartMode=NULL, SubflowExecution=NULL, Value='Tax Rate', WaitTime=0, WaitingTime=0, WorkingTime=0, XPosition=170, YPosition=85,Updated=TO_TIMESTAMP('2008-03-03 22:14:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=169
;

-- Mar 3, 2008 10:14:07 PM CST
-- Tax Global Management
UPDATE AD_Workflow SET AD_WF_Node_ID=166, IsValid='Y',Updated=TO_TIMESTAMP('2008-03-03 22:14:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Workflow_ID=110
;

-- Mar 3, 2008 10:14:08 PM CST
-- Tax Global Management
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType) VALUES (0,0,53021,TO_TIMESTAMP('2008-03-03 22:14:07','YYYY-MM-DD HH24:MI:SS'),0,'Lets you define different tax combinations.','EE04','You can use the tax dfinition information to create the logic necessary to get the tax rate to your document','Y','N','N','Y','Tax Definition','N',TO_TIMESTAMP('2008-03-03 22:14:07','YYYY-MM-DD HH24:MI:SS'),0,'M')
;

-- Mar 3, 2008 10:14:08 PM CST
-- Tax Global Management
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53021 AND EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Window_ID!=t.AD_Window_ID)
;

-- Mar 3, 2008 10:14:08 PM CST
-- Tax Global Management
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,0,53021,TO_TIMESTAMP('2008-03-03 22:14:08','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_TIMESTAMP('2008-03-03 22:14:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:08 PM CST
-- Tax Global Management
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,102,53021,TO_TIMESTAMP('2008-03-03 22:14:08','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_TIMESTAMP('2008-03-03 22:14:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:08 PM CST
-- Tax Global Management
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,103,53021,TO_TIMESTAMP('2008-03-03 22:14:08','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_TIMESTAMP('2008-03-03 22:14:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:08 PM CST
-- Tax Global Management
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,50001,53021,TO_TIMESTAMP('2008-03-03 22:14:08','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_TIMESTAMP('2008-03-03 22:14:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:09 PM CST
-- Tax Global Management
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_Window_ID,AccessLevel,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53067,53021,'3',TO_TIMESTAMP('2008-03-03 22:14:08','YYYY-MM-DD HH24:MI:SS'),0,'Tax Definition','EE04','N','Y','N','Y','N','N','N','Tax Definition','L','C_TaxDefinition',TO_TIMESTAMP('2008-03-03 22:14:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:09 PM CST
-- Tax Global Management
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53067 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Mar 3, 2008 10:14:10 PM CST
-- Tax Global Management
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53084,TO_TIMESTAMP('2008-03-03 22:14:09','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table C_TaxDefinition',1,'Y','N','Y','Y','C_TaxDefinition','N',1000000,TO_TIMESTAMP('2008-03-03 22:14:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:11 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54409,617,0,15,53067,'ValidFrom',TO_TIMESTAMP('2008-03-03 22:14:10','YYYY-MM-DD HH24:MI:SS'),0,'Valid from including this date (first day)','EE04',7,'The Valid From date indicates the first day of a date range','Y','N','N','N','N','N','N','N','Y','N','Y','Valid from',TO_TIMESTAMP('2008-03-03 22:14:10','YYYY-MM-DD HH24:MI:SS'),0,1.000000000000)
;

-- Mar 3, 2008 10:14:11 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54409 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:14:11 PM CST
-- Tax Global Management
CREATE TABLE C_TaxDefinition (ValidFrom TIMESTAMP)
;

-- Mar 3, 2008 10:14:12 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54410,618,0,15,53067,'ValidTo',TO_TIMESTAMP('2008-03-03 22:14:11','YYYY-MM-DD HH24:MI:SS'),0,'Valid to including this date (last day)','EE04',7,'The Valid To date indicates the last day of a date range','Y','N','N','N','N','N','N','N','Y','N','Y','Valid to',TO_TIMESTAMP('2008-03-03 22:14:11','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 3, 2008 10:14:12 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54410 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:14:12 PM CST
-- Tax Global Management
ALTER TABLE C_TaxDefinition ADD COLUMN ValidTo TIMESTAMP
;

-- Mar 3, 2008 10:14:12 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54411,469,0,10,53067,'Name',TO_TIMESTAMP('2008-03-03 22:14:12','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE04',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','Y','N','Y','Name',1,TO_TIMESTAMP('2008-03-03 22:14:12','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:14:13 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54411 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:14:13 PM CST
-- Tax Global Management
ALTER TABLE C_TaxDefinition ADD COLUMN Name VARCHAR(60) NOT NULL
;

-- Mar 3, 2008 10:14:13 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54412,1383,0,19,53067,'C_BP_Group_ID',TO_TIMESTAMP('2008-03-03 22:14:13','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Group','EE04',10,'The Business Partner Group provides a method of defining defaults to be used for individual Business Partners.','Y','N','N','N','N','N','N','N','Y','N','Y','Business Partner Group',TO_TIMESTAMP('2008-03-03 22:14:13','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:14:13 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54412 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:14:13 PM CST
-- Tax Global Management
ALTER TABLE C_TaxDefinition ADD COLUMN C_BP_Group_ID NUMERIC(10)
;

-- Mar 3, 2008 10:14:14 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54413,187,0,30,53067,'C_BPartner_ID',TO_TIMESTAMP('2008-03-03 22:14:13','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a Business Partner','EE04',10,'A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','N','N','N','N','N','N','N','Y','N','Y','Business Partner ',TO_TIMESTAMP('2008-03-03 22:14:13','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:14:14 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54413 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:14:14 PM CST
-- Tax Global Management
ALTER TABLE C_TaxDefinition ADD COLUMN C_BPartner_ID NUMERIC(10)
;

-- Mar 3, 2008 10:14:15 PM CST
-- Tax Global Management
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53357,0,'C_TaxBase_ID',TO_TIMESTAMP('2008-03-03 22:14:14','YYYY-MM-DD HH24:MI:SS'),0,'EE04','Y','C_TaxBase_ID','C_TaxBase_ID',TO_TIMESTAMP('2008-03-03 22:14:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:15 PM CST
-- Tax Global Management
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53357 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Mar 3, 2008 10:14:16 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54414,53357,0,19,53067,'C_TaxBase_ID',TO_TIMESTAMP('2008-03-03 22:14:14','YYYY-MM-DD HH24:MI:SS'),0,'EE04',22,'Y','N','N','N','N','N','N','N','Y','N','Y','C_TaxBase_ID',TO_TIMESTAMP('2008-03-03 22:14:14','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:14:16 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54414 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:14:16 PM CST
-- Tax Global Management
ALTER TABLE C_TaxDefinition ADD COLUMN C_TaxBase_ID NUMERIC(10)
;

-- Mar 3, 2008 10:14:17 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54415,211,0,19,53067,'C_TaxCategory_ID',TO_TIMESTAMP('2008-03-03 22:14:16','YYYY-MM-DD HH24:MI:SS'),0,'Tax Category','EE04',10,'The Tax Category provides a method of grouping similar taxes.  For example, Sales Tax or Value Added Tax.','Y','N','N','N','N','N','N','N','Y','N','Y','Tax Category',TO_TIMESTAMP('2008-03-03 22:14:16','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:14:17 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54415 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:14:17 PM CST
-- Tax Global Management
ALTER TABLE C_TaxDefinition ADD COLUMN C_TaxCategory_ID NUMERIC(10)
;

-- Mar 3, 2008 10:14:17 PM CST
-- Tax Global Management
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53358,0,'C_TaxDefinition_ID',TO_TIMESTAMP('2008-03-03 22:14:17','YYYY-MM-DD HH24:MI:SS'),0,'EE04','Y','C_TaxDefinition_ID','C_TaxDefinition_ID',TO_TIMESTAMP('2008-03-03 22:14:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:17 PM CST
-- Tax Global Management
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53358 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Mar 3, 2008 10:14:18 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54416,53358,0,13,53067,'C_TaxDefinition_ID',TO_TIMESTAMP('2008-03-03 22:14:17','YYYY-MM-DD HH24:MI:SS'),0,'EE04',10,'Y','N','N','N','Y','Y','N','N','Y','N','N','C_TaxDefinition_ID',TO_TIMESTAMP('2008-03-03 22:14:17','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:14:18 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54416 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:14:18 PM CST
-- Tax Global Management
ALTER TABLE C_TaxDefinition ADD COLUMN C_TaxDefinition_ID NUMERIC(10) NOT NULL
;

-- Mar 3, 2008 10:14:18 PM CST
-- Tax Global Management
ALTER TABLE C_TaxDefinition ADD CONSTRAINT C_TaxDefinition_Key PRIMARY KEY (C_TaxDefinition_ID)
;

-- Mar 3, 2008 10:14:19 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54417,53356,0,19,53067,'C_TaxGroup_ID',TO_TIMESTAMP('2008-03-03 22:14:18','YYYY-MM-DD HH24:MI:SS'),0,'EE04',10,'Y','N','N','N','N','N','N','N','Y','N','Y','C_TaxGroup_ID',TO_TIMESTAMP('2008-03-03 22:14:18','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:14:19 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54417 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:14:19 PM CST
-- Tax Global Management
ALTER TABLE C_TaxDefinition ADD COLUMN C_TaxGroup_ID NUMERIC(10)
;

-- Mar 3, 2008 10:14:20 PM CST
-- Tax Global Management
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53359,0,'C_TaxType_ID',TO_TIMESTAMP('2008-03-03 22:14:19','YYYY-MM-DD HH24:MI:SS'),0,'EE04','Y','C_TaxType_ID','C_TaxType_ID',TO_TIMESTAMP('2008-03-03 22:14:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:20 PM CST
-- Tax Global Management
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53359 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Mar 3, 2008 10:14:20 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54418,53359,0,19,53067,'C_TaxType_ID',TO_TIMESTAMP('2008-03-03 22:14:19','YYYY-MM-DD HH24:MI:SS'),0,'EE04',22,'Y','N','N','N','N','N','N','N','Y','N','Y','C_TaxType_ID',TO_TIMESTAMP('2008-03-03 22:14:19','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:14:20 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54418 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:14:21 PM CST
-- Tax Global Management
ALTER TABLE C_TaxDefinition ADD COLUMN C_TaxType_ID NUMERIC(10)
;

-- Mar 3, 2008 10:14:22 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54419,213,0,19,53067,'C_Tax_ID',TO_TIMESTAMP('2008-03-03 22:14:21','YYYY-MM-DD HH24:MI:SS'),0,'Tax identifier','EE04',10,'The Tax indicates the type of tax used in document line.','Y','N','N','N','N','N','N','N','Y','N','Y','Tax',TO_TIMESTAMP('2008-03-03 22:14:21','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:14:22 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54419 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:14:22 PM CST
-- Tax Global Management
ALTER TABLE C_TaxDefinition ADD COLUMN C_Tax_ID NUMERIC(10)
;

-- Mar 3, 2008 10:14:24 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54420,245,0,16,53067,'Created',TO_TIMESTAMP('2008-03-03 22:14:22','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE04',29,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','Y','N','N','Created',TO_TIMESTAMP('2008-03-03 22:14:22','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:14:24 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54420 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:14:24 PM CST
-- Tax Global Management
ALTER TABLE C_TaxDefinition ADD COLUMN Created TIMESTAMP NOT NULL
;

-- Mar 3, 2008 10:14:25 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54421,246,0,18,110,53067,'CreatedBy',TO_TIMESTAMP('2008-03-03 22:14:24','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE04',10,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Created By',TO_TIMESTAMP('2008-03-03 22:14:24','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:14:25 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54421 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:14:25 PM CST
-- Tax Global Management
ALTER TABLE C_TaxDefinition ADD COLUMN CreatedBy NUMERIC(10) NOT NULL
;

-- Mar 3, 2008 10:14:25 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54422,275,0,10,53067,'Description',TO_TIMESTAMP('2008-03-03 22:14:25','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE04',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','N','Y','Description',TO_TIMESTAMP('2008-03-03 22:14:25','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:14:25 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54422 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:14:25 PM CST
-- Tax Global Management
ALTER TABLE C_TaxDefinition ADD COLUMN Description VARCHAR(255)
;

-- Mar 3, 2008 10:14:26 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54423,326,0,14,53067,'Help',TO_TIMESTAMP('2008-03-03 22:14:25','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','EE04',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','Y','N','Y','Comment/Help',TO_TIMESTAMP('2008-03-03 22:14:25','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:14:26 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54423 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:14:26 PM CST
-- Tax Global Management
ALTER TABLE C_TaxDefinition ADD COLUMN Help VARCHAR(2000)
;

-- Mar 3, 2008 10:14:27 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54424,348,0,20,53067,'IsActive',TO_TIMESTAMP('2008-03-03 22:14:26','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','EE04',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','Y','N','Y','Active',TO_TIMESTAMP('2008-03-03 22:14:26','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:14:27 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54424 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:14:27 PM CST
-- Tax Global Management
ALTER TABLE C_TaxDefinition ADD COLUMN IsActive CHAR(1) CHECK (IsActive IN ('Y','N')) NOT NULL
;

-- Mar 3, 2008 10:14:28 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54425,387,0,20,53067,'IsInvoiced',TO_TIMESTAMP('2008-03-03 22:14:27','YYYY-MM-DD HH24:MI:SS'),0,'Is this invoiced?','EE04',1,'If selected, invoices are created','Y','N','N','N','N','N','N','N','Y','N','Y','Invoiced',TO_TIMESTAMP('2008-03-03 22:14:27','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 3, 2008 10:14:28 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54425 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:14:28 PM CST
-- Tax Global Management
ALTER TABLE C_TaxDefinition ADD COLUMN IsInvoiced CHAR(1) CHECK (IsInvoiced IN ('Y','N'))
;

-- Mar 3, 2008 10:14:29 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54426,453,0,19,53067,'M_Product_Category_ID',TO_TIMESTAMP('2008-03-03 22:14:28','YYYY-MM-DD HH24:MI:SS'),0,'Category of a Product','EE04',10,'Identifies the category which this product belongs to.  Product categories are used for pricing and selection.','Y','N','N','N','N','N','N','N','Y','N','Y','Product Category',TO_TIMESTAMP('2008-03-03 22:14:28','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:14:29 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54426 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:14:29 PM CST
-- Tax Global Management
ALTER TABLE C_TaxDefinition ADD COLUMN M_Product_Category_ID NUMERIC(10)
;

-- Mar 3, 2008 10:14:30 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54427,454,0,30,53067,'M_Product_ID',TO_TIMESTAMP('2008-03-03 22:14:29','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item','EE04',10,'Identifies an item which is either purchased or sold in this organization.','Y','N','N','N','N','N','N','N','Y','N','Y','Product',TO_TIMESTAMP('2008-03-03 22:14:29','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:14:30 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54427 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:14:30 PM CST
-- Tax Global Management
ALTER TABLE C_TaxDefinition ADD COLUMN M_Product_ID NUMERIC(10)
;

-- Mar 3, 2008 10:14:30 PM CST
-- Tax Global Management
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53360,0,'MaxTaxable',TO_TIMESTAMP('2008-03-03 22:14:30','YYYY-MM-DD HH24:MI:SS'),0,'EE04','Y','MaxTaxable','MaxTaxable',TO_TIMESTAMP('2008-03-03 22:14:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:30 PM CST
-- Tax Global Management
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53360 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Mar 3, 2008 10:14:31 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54428,53360,0,11,53067,'MaxTaxable',TO_TIMESTAMP('2008-03-03 22:14:30','YYYY-MM-DD HH24:MI:SS'),0,'EE04',10,'Y','N','N','N','N','N','N','N','Y','N','Y','MaxTaxable',TO_TIMESTAMP('2008-03-03 22:14:30','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:14:31 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54428 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:14:31 PM CST
-- Tax Global Management
ALTER TABLE C_TaxDefinition ADD COLUMN MaxTaxable NUMERIC(10)
;

-- Mar 3, 2008 10:14:32 PM CST
-- Tax Global Management
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53361,0,'MinTaxable',TO_TIMESTAMP('2008-03-03 22:14:31','YYYY-MM-DD HH24:MI:SS'),0,'EE04','Y','MinTaxable','MinTaxable',TO_TIMESTAMP('2008-03-03 22:14:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:32 PM CST
-- Tax Global Management
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53361 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Mar 3, 2008 10:14:33 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54429,53361,0,11,53067,'MinTaxable',TO_TIMESTAMP('2008-03-03 22:14:31','YYYY-MM-DD HH24:MI:SS'),0,'EE04',10,'Y','N','N','N','N','N','N','N','Y','N','Y','MinTaxable',TO_TIMESTAMP('2008-03-03 22:14:31','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:14:33 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54429 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:14:33 PM CST
-- Tax Global Management
ALTER TABLE C_TaxDefinition ADD COLUMN MinTaxable NUMERIC(10)
;

-- Mar 3, 2008 10:14:33 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54430,566,0,11,53067,'SeqNo',TO_TIMESTAMP('2008-03-03 22:14:33','YYYY-MM-DD HH24:MI:SS'),0,'Method of ordering records; lowest number comes first','EE04',10,'The Sequence indicates the order of records','Y','N','N','N','N','N','N','N','Y','N','Y','Sequence',TO_TIMESTAMP('2008-03-03 22:14:33','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:14:33 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54430 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:14:34 PM CST
-- Tax Global Management
ALTER TABLE C_TaxDefinition ADD COLUMN SeqNo NUMERIC(10)
;

-- Mar 3, 2008 10:14:34 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54431,607,0,16,53067,'Updated',TO_TIMESTAMP('2008-03-03 22:14:34','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE04',29,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated',TO_TIMESTAMP('2008-03-03 22:14:34','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:14:34 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54431 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:14:34 PM CST
-- Tax Global Management
ALTER TABLE C_TaxDefinition ADD COLUMN Updated TIMESTAMP NOT NULL
;

-- Mar 3, 2008 10:14:35 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54432,608,0,18,110,53067,'UpdatedBy',TO_TIMESTAMP('2008-03-03 22:14:34','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE04',10,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated By',TO_TIMESTAMP('2008-03-03 22:14:34','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:14:35 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54432 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:14:35 PM CST
-- Tax Global Management
ALTER TABLE C_TaxDefinition ADD COLUMN UpdatedBy NUMERIC(10) NOT NULL
;

-- Mar 3, 2008 10:14:36 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54433,102,0,19,53067,129,'AD_Client_ID',TO_TIMESTAMP('2008-03-03 22:14:35','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','EE04',10,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','Y','N','N','Client',TO_TIMESTAMP('2008-03-03 22:14:35','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:14:36 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54433 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:14:36 PM CST
-- Tax Global Management
ALTER TABLE C_TaxDefinition ADD COLUMN AD_Client_ID NUMERIC(10) DEFAULT NULL NOT NULL
;

-- Mar 3, 2008 10:14:37 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54434,620,0,10,53067,'Value',TO_TIMESTAMP('2008-03-03 22:14:36','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique','EE04',40,'A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','Y','N','N','Y','N','Y','Search Key',TO_TIMESTAMP('2008-03-03 22:14:36','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:14:37 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54434 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:14:37 PM CST
-- Tax Global Management
ALTER TABLE C_TaxDefinition ADD COLUMN Value VARCHAR(40) NOT NULL
;

-- Mar 3, 2008 10:14:38 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54435,2355,0,19,53067,'AD_OrgType_ID',TO_TIMESTAMP('2008-03-03 22:14:37','YYYY-MM-DD HH24:MI:SS'),0,'Organization Type allows you to categorize your organizations','EE04',10,'Organization Type allows you to categorize your organizations for reporting purposes','Y','N','N','N','N','N','N','N','Y','N','Y','Organization Type',TO_TIMESTAMP('2008-03-03 22:14:37','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:14:38 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54435 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:14:38 PM CST
-- Tax Global Management
ALTER TABLE C_TaxDefinition ADD COLUMN AD_OrgType_ID NUMERIC(10)
;

-- Mar 3, 2008 10:14:39 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54436,113,0,19,53067,104,'AD_Org_ID',TO_TIMESTAMP('2008-03-03 22:14:38','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','EE04',10,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','Y','N','N','Organization',TO_TIMESTAMP('2008-03-03 22:14:38','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:14:39 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54436 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:14:39 PM CST
-- Tax Global Management
ALTER TABLE C_TaxDefinition ADD COLUMN AD_Org_ID NUMERIC(10) DEFAULT NULL NOT NULL
;

-- Mar 3, 2008 10:14:40 PM CST
-- Tax Global Management
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,Description,EntityType,HasTree,Help,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53076,53067,53021,NULL,TO_TIMESTAMP('2008-03-03 22:14:39','YYYY-MM-DD HH24:MI:SS'),0,'Lets you define different tax combinations.','EE04','N','You can use the tax definition information to create the logic necessary to get the tax rate to your document.','Y','N','N','Y','N','Y','N','N','Tax Definition','N',10,0,TO_TIMESTAMP('2008-03-03 22:14:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:40 PM CST
-- Tax Global Management
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53076 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Mar 3, 2008 10:14:41 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54416,54441,0,53076,TO_TIMESTAMP('2008-03-03 22:14:40','YYYY-MM-DD HH24:MI:SS'),0,10,'EE04','Y','Y','N','N','N','N','N','C_TaxDefinition_ID',0,0,TO_TIMESTAMP('2008-03-03 22:14:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:41 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54441 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:14:41 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54433,54442,0,53076,TO_TIMESTAMP('2008-03-03 22:14:41','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',10,'EE04','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_TIMESTAMP('2008-03-03 22:14:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:41 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54442 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:14:42 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54436,54443,0,53076,TO_TIMESTAMP('2008-03-03 22:14:41','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',10,'EE04','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_TIMESTAMP('2008-03-03 22:14:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:42 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54443 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:14:43 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54430,54444,0,53076,TO_TIMESTAMP('2008-03-03 22:14:42','YYYY-MM-DD HH24:MI:SS'),0,'Method of ordering records; lowest number comes first',10,'EE04','The Sequence indicates the order of records','Y','Y','Y','N','N','N','N','Sequence',30,0,TO_TIMESTAMP('2008-03-03 22:14:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:43 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54444 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:14:44 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54435,54445,0,53076,TO_TIMESTAMP('2008-03-03 22:14:43','YYYY-MM-DD HH24:MI:SS'),0,'Organization Type allows you to categorize your organizations',10,'EE04','Organization Type allows you to categorize your organizations for reporting purposes','Y','Y','Y','N','N','N','Y','Organization Type',40,0,TO_TIMESTAMP('2008-03-03 22:14:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:44 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54445 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:14:45 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54434,54446,0,53076,TO_TIMESTAMP('2008-03-03 22:14:44','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique',40,'EE04','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','N','Search Key',50,0,TO_TIMESTAMP('2008-03-03 22:14:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:45 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54446 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:14:45 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54411,54447,0,53076,TO_TIMESTAMP('2008-03-03 22:14:45','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',60,'EE04','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',60,0,TO_TIMESTAMP('2008-03-03 22:14:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:45 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54447 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:14:46 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54422,54448,0,53076,TO_TIMESTAMP('2008-03-03 22:14:45','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',255,'EE04','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',70,0,TO_TIMESTAMP('2008-03-03 22:14:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:46 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54448 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:14:47 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54423,54449,0,53076,TO_TIMESTAMP('2008-03-03 22:14:46','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint',2000,'EE04','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','Comment/Help',80,0,TO_TIMESTAMP('2008-03-03 22:14:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:47 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54449 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:14:48 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54424,54450,0,53076,TO_TIMESTAMP('2008-03-03 22:14:47','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE04','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',90,0,TO_TIMESTAMP('2008-03-03 22:14:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:48 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54450 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:14:48 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54409,54451,0,53076,TO_TIMESTAMP('2008-03-03 22:14:48','YYYY-MM-DD HH24:MI:SS'),0,'Valid from including this date (first day)',7,'EE04','The Valid From date indicates the first day of a date range','Y','Y','Y','N','N','N','N','Valid from',100,0,TO_TIMESTAMP('2008-03-03 22:14:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:48 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54451 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:14:49 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54410,54452,0,53076,TO_TIMESTAMP('2008-03-03 22:14:48','YYYY-MM-DD HH24:MI:SS'),0,'Valid to including this date (last day)',7,'EE04','The Valid To date indicates the last day of a date range','Y','Y','Y','N','N','N','Y','Valid to',110,0,TO_TIMESTAMP('2008-03-03 22:14:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:49 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54452 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:14:50 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54413,54453,0,53076,TO_TIMESTAMP('2008-03-03 22:14:49','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a Business Partner',10,'EE04','A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','Y','Y','N','N','N','N','Business Partner ',120,0,TO_TIMESTAMP('2008-03-03 22:14:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:50 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54453 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:14:51 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54412,54454,0,53076,TO_TIMESTAMP('2008-03-03 22:14:50','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Group',10,'EE04','The Business Partner Group provides a method of defining defaults to be used for individual Business Partners.','Y','Y','Y','N','N','N','Y','Business Partner Group',130,0,TO_TIMESTAMP('2008-03-03 22:14:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:51 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54454 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:14:52 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54417,54455,0,53076,TO_TIMESTAMP('2008-03-03 22:14:51','YYYY-MM-DD HH24:MI:SS'),0,10,'EE04','Y','Y','Y','N','N','N','N','C_TaxGroup_ID',140,0,TO_TIMESTAMP('2008-03-03 22:14:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:52 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54455 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:14:52 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54427,54456,0,53076,TO_TIMESTAMP('2008-03-03 22:14:52','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item',10,'EE04','Identifies an item which is either purchased or sold in this organization.','Y','Y','Y','N','N','N','N','Product',150,0,TO_TIMESTAMP('2008-03-03 22:14:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:52 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54456 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:14:53 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54426,54457,0,53076,TO_TIMESTAMP('2008-03-03 22:14:52','YYYY-MM-DD HH24:MI:SS'),0,'Category of a Product',10,'EE04','Identifies the category which this product belongs to.  Product categories are used for pricing and selection.','Y','Y','Y','N','N','N','Y','Product Category',160,0,TO_TIMESTAMP('2008-03-03 22:14:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:53 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54457 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:14:54 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54415,54458,0,53076,TO_TIMESTAMP('2008-03-03 22:14:53','YYYY-MM-DD HH24:MI:SS'),0,'Tax Category',10,'EE04','The Tax Category provides a method of grouping similar taxes.  For example, Sales Tax or Value Added Tax.','Y','Y','Y','N','N','N','N','Tax Category',170,0,TO_TIMESTAMP('2008-03-03 22:14:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:54 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54458 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:14:54 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54418,54459,0,53076,TO_TIMESTAMP('2008-03-03 22:14:54','YYYY-MM-DD HH24:MI:SS'),0,22,'EE04','Y','Y','Y','N','N','N','N','C_TaxType_ID',180,0,TO_TIMESTAMP('2008-03-03 22:14:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:54 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54459 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:14:55 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54414,54460,0,53076,TO_TIMESTAMP('2008-03-03 22:14:54','YYYY-MM-DD HH24:MI:SS'),0,22,'EE04','Y','Y','Y','N','N','N','Y','C_TaxBase_ID',190,0,TO_TIMESTAMP('2008-03-03 22:14:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:55 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54460 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:14:56 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54429,54461,0,53076,TO_TIMESTAMP('2008-03-03 22:14:55','YYYY-MM-DD HH24:MI:SS'),0,10,'EE04','Y','Y','Y','N','N','N','N','MinTaxable',200,0,TO_TIMESTAMP('2008-03-03 22:14:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:56 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54461 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:14:57 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54428,54462,0,53076,TO_TIMESTAMP('2008-03-03 22:14:56','YYYY-MM-DD HH24:MI:SS'),0,10,'EE04','Y','Y','Y','N','N','N','Y','MaxTaxable',210,0,TO_TIMESTAMP('2008-03-03 22:14:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:57 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54462 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:14:58 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54419,54463,0,53076,TO_TIMESTAMP('2008-03-03 22:14:57','YYYY-MM-DD HH24:MI:SS'),0,'Tax identifier',10,'EE04','The Tax indicates the type of tax used in document line.','Y','Y','Y','N','N','N','N','Tax',220,0,TO_TIMESTAMP('2008-03-03 22:14:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:58 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54463 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:14:58 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54425,54464,0,53076,TO_TIMESTAMP('2008-03-03 22:14:58','YYYY-MM-DD HH24:MI:SS'),0,'Is this invoiced?',1,'EE04','If selected, invoices are created','Y','Y','Y','N','N','N','N','Invoiced',230,0,TO_TIMESTAMP('2008-03-03 22:14:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:58 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54464 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:14:59 PM CST
-- Tax Global Management
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType) VALUES (0,0,53022,TO_TIMESTAMP('2008-03-03 22:14:58','YYYY-MM-DD HH24:MI:SS'),0,'Maintain Taxes and their Rates','EE04','The Tax Rate Window defines the different taxes used for each tax category.  For example Sales Tax must be defined for each State in which it applies.','Y','N','N','Y','Tax Rate Parent','N',TO_TIMESTAMP('2008-03-03 22:14:58','YYYY-MM-DD HH24:MI:SS'),0,'M')
;

-- Mar 3, 2008 10:14:59 PM CST
-- Tax Global Management
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53022 AND EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Window_ID!=t.AD_Window_ID)
;

-- Mar 3, 2008 10:14:59 PM CST
-- Tax Global Management
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,0,53022,TO_TIMESTAMP('2008-03-03 22:14:59','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_TIMESTAMP('2008-03-03 22:14:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:59 PM CST
-- Tax Global Management
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,102,53022,TO_TIMESTAMP('2008-03-03 22:14:59','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_TIMESTAMP('2008-03-03 22:14:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:59 PM CST
-- Tax Global Management
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,103,53022,TO_TIMESTAMP('2008-03-03 22:14:59','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_TIMESTAMP('2008-03-03 22:14:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:14:59 PM CST
-- Tax Global Management
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,50001,53022,TO_TIMESTAMP('2008-03-03 22:14:59','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_TIMESTAMP('2008-03-03 22:14:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:00 PM CST
-- Tax Global Management
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,Description,EntityType,HasTree,Help,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53077,399,53022,NULL,TO_TIMESTAMP('2008-03-03 22:14:59','YYYY-MM-DD HH24:MI:SS'),0,'Accounting','EE04','N','The Accounting Tab defines the accounting parameters to be used for transactions referencing this Tax Rate.','Y','N','Y','Y','N','Y','N','N','Accounting','N',40,1,TO_TIMESTAMP('2008-03-03 22:14:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:00 PM CST
-- Tax Global Management
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53077 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Mar 3, 2008 10:15:02 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,5077,54465,0,53077,TO_TIMESTAMP('2008-03-03 22:15:00','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',14,'EE04','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','Y','N','Client',10,0,TO_TIMESTAMP('2008-03-03 22:15:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:02 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54465 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:03 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,5078,54466,0,53077,TO_TIMESTAMP('2008-03-03 22:15:02','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',14,'EE04','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','Y','Y','Organization',20,0,TO_TIMESTAMP('2008-03-03 22:15:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:03 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54466 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:04 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,5075,54467,0,53077,TO_TIMESTAMP('2008-03-03 22:15:03','YYYY-MM-DD HH24:MI:SS'),0,'Tax identifier',14,'EE04','The Tax indicates the type of tax used in document line.','Y','Y','Y','N','N','Y','N','Tax',30,0,TO_TIMESTAMP('2008-03-03 22:15:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:04 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54467 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:05 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,5076,54468,0,53077,TO_TIMESTAMP('2008-03-03 22:15:04','YYYY-MM-DD HH24:MI:SS'),0,'Rules for accounting',14,'EE04','An Accounting Schema defines the rules used in accounting such as costing method, currency and calendar','Y','Y','Y','N','N','Y','N','Accounting Schema',40,1,TO_TIMESTAMP('2008-03-03 22:15:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:06 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54468 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:06 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,5079,54469,0,53077,TO_TIMESTAMP('2008-03-03 22:15:06','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE04','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',50,0,TO_TIMESTAMP('2008-03-03 22:15:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:06 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54469 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:07 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,5084,54470,0,53077,TO_TIMESTAMP('2008-03-03 22:15:06','YYYY-MM-DD HH24:MI:SS'),0,'Account for Tax you have to pay',26,'EE04','The Tax Due Account indicates the account used to record taxes that you are liable to pay.','Y','Y','Y','N','N','N','N','Tax Due',60,0,TO_TIMESTAMP('2008-03-03 22:15:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:07 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54470 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:08 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,5085,54471,0,53077,TO_TIMESTAMP('2008-03-03 22:15:07','YYYY-MM-DD HH24:MI:SS'),0,'Account for Tax declaration liability',26,'EE04','The Tax Liability Account indicates the account used to record your tax liability declaration.','Y','Y','Y','N','N','N','Y','Tax Liability',70,0,TO_TIMESTAMP('2008-03-03 22:15:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:08 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54471 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:09 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,5086,54472,0,53077,TO_TIMESTAMP('2008-03-03 22:15:08','YYYY-MM-DD HH24:MI:SS'),0,'Account for Tax you can reclaim',26,'EE04','The Tax Credit Account indicates the account used to record taxes that can be reclaimed','Y','Y','Y','N','N','N','N','Tax Credit',80,0,TO_TIMESTAMP('2008-03-03 22:15:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:09 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54472 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:09 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,5087,54473,0,53077,TO_TIMESTAMP('2008-03-03 22:15:09','YYYY-MM-DD HH24:MI:SS'),0,'Account for Tax credit after tax declaration',26,'EE04','The Tax Receivables Account indicates the account used to record the tax credit amount after your tax declaration.','Y','Y','Y','N','N','N','Y','Tax Receivables',90,0,TO_TIMESTAMP('2008-03-03 22:15:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:09 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54473 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:10 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,5088,54474,0,53077,TO_TIMESTAMP('2008-03-03 22:15:09','YYYY-MM-DD HH24:MI:SS'),0,'Account for paid tax you cannot reclaim',26,'EE04','The Tax Expense Account indicates the account used to record the taxes that have been paid that cannot be reclaimed.','Y','Y','Y','N','N','N','N','Tax Expense',100,0,TO_TIMESTAMP('2008-03-03 22:15:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:10 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54474 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:11 PM CST
-- Tax Global Management
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,Description,EntityType,HasTree,Help,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy,WhereClause) VALUES (0,0,53078,261,53022,NULL,TO_TIMESTAMP('2008-03-03 22:15:10','YYYY-MM-DD HH24:MI:SS'),0,'Tax definition','EE04','N','The Tax Rate Window defines the different taxes used for each tax category.  For example Sales Tax must be defined for each State in which it applies.<br>
If you have multiple taxes create a summary level tax with the approximate total tax rate and the actual tax rates pointing to the summary level tax as their parent. When entering the order or invoice lines the tax is estimated the correct tax is calculated when the document is processed.  The tax is always calculated from the line net amount. If one tax has a the tax basis the line net amount and another tax you need to adjust the percentage to result in the correct amount.<br>
Valid From/To is determined by the parent tax.','Y','N','N','Y','N','N','N','N','Tax Children','N',15,0,TO_TIMESTAMP('2008-03-03 22:15:10','YYYY-MM-DD HH24:MI:SS'),0,'IsSummary=''N'' AND Parent_Tax_ID=@C_Tax_ID@')
;

-- Mar 3, 2008 10:15:11 PM CST
-- Tax Global Management
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53078 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Mar 3, 2008 10:15:12 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,2240,54475,0,53078,TO_TIMESTAMP('2008-03-03 22:15:11','YYYY-MM-DD HH24:MI:SS'),0,'Tax identifier',14,'EE04','The Tax indicates the type of tax used in document line.','Y','Y','N','N','N','N','N','Tax',0,0,TO_TIMESTAMP('2008-03-03 22:15:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:12 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54475 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:13 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,2241,54476,0,53078,TO_TIMESTAMP('2008-03-03 22:15:12','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',14,'EE04','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_TIMESTAMP('2008-03-03 22:15:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:13 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54476 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:13 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,2242,54477,0,53078,TO_TIMESTAMP('2008-03-03 22:15:13','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',14,'EE04','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_TIMESTAMP('2008-03-03 22:15:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:13 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54477 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:14 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,2246,54478,0,53078,TO_TIMESTAMP('2008-03-03 22:15:13','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',60,'EE04','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',30,1,TO_TIMESTAMP('2008-03-03 22:15:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:14 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54478 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:15 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,2247,54479,0,53078,TO_TIMESTAMP('2008-03-03 22:15:15','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',60,'EE04','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',40,0,TO_TIMESTAMP('2008-03-03 22:15:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:15 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54479 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:16 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,2243,54480,0,53078,TO_TIMESTAMP('2008-03-03 22:15:15','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE04','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',50,0,TO_TIMESTAMP('2008-03-03 22:15:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:16 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54480 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:17 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,4211,54481,0,53078,TO_TIMESTAMP('2008-03-03 22:15:16','YYYY-MM-DD HH24:MI:SS'),0,'Default value',1,'EE04','The Default Checkbox indicates if this record will be used as a default value.','Y','Y','Y','N','N','N','Y','Default',60,0,TO_TIMESTAMP('2008-03-03 22:15:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:17 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54481 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:18 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,2254,54482,0,53078,TO_TIMESTAMP('2008-03-03 22:15:17','YYYY-MM-DD HH24:MI:SS'),0,'Tax Category',14,'EE04','The Tax Category provides a method of grouping similar taxes.  For example, Sales Tax or Value Added Tax.','Y','Y','Y','N','N','N','N','Tax Category',70,0,TO_TIMESTAMP('2008-03-03 22:15:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:18 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54482 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:19 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,3054,54483,0,53078,TO_TIMESTAMP('2008-03-03 22:15:18','YYYY-MM-DD HH24:MI:SS'),0,'Valid from including this date (first day)',14,'EE04','The Valid From date indicates the first day of a date range','Y','Y','Y','N','N','N','Y','Valid from',80,0,TO_TIMESTAMP('2008-03-03 22:15:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:19 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54483 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:19 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,7971,54484,0,53078,TO_TIMESTAMP('2008-03-03 22:15:19','YYYY-MM-DD HH24:MI:SS'),0,'Business partner is exempt from tax',1,'EE04','If a business partner is exempt from tax, the exempt tax rate is used. For this, you need to set up a tax rate with a 0% rate and indicate that this is your tax exempt rate.  This is required for tax reporting, so that you can track tax exempt transactions.','Y','Y','Y','N','N','N','N','Tax exempt',90,0,TO_TIMESTAMP('2008-03-03 22:15:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:19 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54484 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:20 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,3695,54485,0,53078,TO_TIMESTAMP('2008-03-03 22:15:19','YYYY-MM-DD HH24:MI:SS'),0,'This tax rate requires the Business Partner to be tax exempt',1,'EE04','The Requires Tax Certificate indicates that a tax certificate is required for a Business Partner to be tax exempt.','Y','Y','Y','N','N','N','Y','Requires Tax Certificate',100,0,TO_TIMESTAMP('2008-03-03 22:15:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:20 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54485 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:21 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,3053,54486,0,53078,TO_TIMESTAMP('2008-03-03 22:15:20','YYYY-MM-DD HH24:MI:SS'),0,'Tax is calculated on document level (rather than line by line)',1,'EE04','If the tax is calculated on document level, all lines with that tax rate are added before calculating the total tax for the document.
Otherwise the tax is calculated per line and then added.
Due to rounding, the tax amount can differ.','Y','Y','Y','N','N','N','N','Document Level',110,0,TO_TIMESTAMP('2008-03-03 22:15:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:21 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54486 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:22 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,14528,54487,0,53078,TO_TIMESTAMP('2008-03-03 22:15:21','YYYY-MM-DD HH24:MI:SS'),0,'This is a sales tax (i.e. not a value added tax)',1,'EE04','If selected AP tax is handled as expense, otherwise it is handeled as a VAT credit.','Y','Y','Y','N','N','N','Y','Sales Tax',120,0,TO_TIMESTAMP('2008-03-03 22:15:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:22 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54487 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:23 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,3055,54488,0,53078,TO_TIMESTAMP('2008-03-03 22:15:22','YYYY-MM-DD HH24:MI:SS'),0,'This is a summary entity',1,'EE04','A summary entity represents a branch in a tree rather than an end-node. Summary entities are used for reporting and do not have own values.','Y','Y','Y','N','N','N','N','Summary Level',130,0,TO_TIMESTAMP('2008-03-03 22:15:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:23 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54488 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:23 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,2249,54489,0,53078,TO_TIMESTAMP('2008-03-03 22:15:23','YYYY-MM-DD HH24:MI:SS'),0,'Parent Tax indicates a tax that is made up of multiple taxes',14,'@IsSummary@=''N''','EE04','The Parent Tax indicates a tax that is a reference for multiple taxes.  This allows you to charge multiple taxes on a document by entering the Parent Tax','Y','Y','Y','N','N','Y','Y','Parent Tax',140,0,TO_TIMESTAMP('2008-03-03 22:15:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:23 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54489 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:24 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,9767,54490,0,53078,TO_TIMESTAMP('2008-03-03 22:15:23','YYYY-MM-DD HH24:MI:SS'),0,'Sales Tax applies to sales situations, Purchase Tax to purchase situations',14,'EE04','Sales Tax: charged when selling - examples: Sales Tax, Output VAT (payable)
Purchase Tax: tax charged when purchasing - examples: Use Tax, Input VAT (receivable)','Y','Y','Y','N','N','N','N','SO/PO Type',150,0,TO_TIMESTAMP('2008-03-03 22:15:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:24 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54490 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:25 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,3724,54491,0,53078,TO_TIMESTAMP('2008-03-03 22:15:24','YYYY-MM-DD HH24:MI:SS'),0,'Short form for Tax to be printed on documents',5,'EE04','The Tax Indicator identifies the short name that will print on documents referencing this tax.','Y','Y','Y','N','N','N','Y','Tax Indicator',160,0,TO_TIMESTAMP('2008-03-03 22:15:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:25 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54491 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:26 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,3693,54492,0,53078,TO_TIMESTAMP('2008-03-03 22:15:25','YYYY-MM-DD HH24:MI:SS'),0,'Rate or Tax or Exchange',26,'EE04','The Rate indicates the percentage to be multiplied by the source to arrive at the tax or exchange amount.','Y','Y','Y','N','N','N','N','Rate',170,0,TO_TIMESTAMP('2008-03-03 22:15:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:26 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54492 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:27 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54408,54493,0,53078,TO_TIMESTAMP('2008-03-03 22:15:26','YYYY-MM-DD HH24:MI:SS'),0,22,'EE04','Y','Y','Y','N','N','N','Y','Rule',180,0,TO_TIMESTAMP('2008-03-03 22:15:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:27 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54493 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:28 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,2250,54494,0,53078,TO_TIMESTAMP('2008-03-03 22:15:27','YYYY-MM-DD HH24:MI:SS'),0,'Country ',14,'EE04','The Country defines a Country.  Each Country must be defined before it can be used in any document.','Y','Y','Y','N','N','N','N','Country',190,0,TO_TIMESTAMP('2008-03-03 22:15:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:28 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54494 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:28 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,2252,54495,0,53078,TO_TIMESTAMP('2008-03-03 22:15:28','YYYY-MM-DD HH24:MI:SS'),0,'Receiving Country',14,'EE04','The To Country indicates the receiving country on a document','Y','Y','Y','N','N','N','Y','To',200,0,TO_TIMESTAMP('2008-03-03 22:15:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:28 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54495 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:29 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,2251,54496,0,53078,TO_TIMESTAMP('2008-03-03 22:15:28','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a geographical Region',14,'EE04','The Region identifies a unique Region for this Country.','Y','Y','Y','N','N','N','N','Region',210,0,TO_TIMESTAMP('2008-03-03 22:15:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:29 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54496 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:31 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,2253,54497,0,53078,TO_TIMESTAMP('2008-03-03 22:15:29','YYYY-MM-DD HH24:MI:SS'),0,'Receiving Region',14,'EE04','The To Region indicates the receiving region on a document','Y','Y','Y','N','N','N','Y','To',220,0,TO_TIMESTAMP('2008-03-03 22:15:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:31 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54497 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:35 PM CST
-- Tax Global Management
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,Description,EntityType,HasTree,Help,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy,WhereClause) VALUES (0,0,53079,261,53022,NULL,TO_TIMESTAMP('2008-03-03 22:15:31','YYYY-MM-DD HH24:MI:SS'),0,'Tax definition','EE04','N','The Tax Rate Window defines the different taxes used for each tax category.  For example Sales Tax must be defined for each State in which it applies.<br>
If you have multiple taxes create a summary level tax with the approximate total tax rate and the actual tax rates pointing to the summary level tax as their parent. When entering the order or invoice lines the tax is estimated the correct tax is calculated when the document is processed.  The tax is always calculated from the line net amount. If one tax has a the tax basis the line net amount and another tax you need to adjust the percentage to result in the correct amount.<br>
Valid From/To is determined by the parent tax.','Y','N','N','Y','N','Y','N','N','Tax Parent','N',10,0,TO_TIMESTAMP('2008-03-03 22:15:31','YYYY-MM-DD HH24:MI:SS'),0,'IsSummary=''Y''')
;

-- Mar 3, 2008 10:15:35 PM CST
-- Tax Global Management
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53079 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Mar 3, 2008 10:15:40 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,3724,54498,0,53079,TO_TIMESTAMP('2008-03-03 22:15:35','YYYY-MM-DD HH24:MI:SS'),0,'Short form for Tax to be printed on documents',5,'EE04','The Tax Indicator identifies the short name that will print on documents referencing this tax.','Y','Y','N','N','N','N','Y','Tax Indicator',0,0,TO_TIMESTAMP('2008-03-03 22:15:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:40 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54498 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:42 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,2250,54499,0,53079,TO_TIMESTAMP('2008-03-03 22:15:40','YYYY-MM-DD HH24:MI:SS'),0,'Country ',14,'EE04','The Country defines a Country.  Each Country must be defined before it can be used in any document.','Y','Y','N','N','N','N','N','Country',0,0,TO_TIMESTAMP('2008-03-03 22:15:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:42 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54499 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:44 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,2251,54500,0,53079,TO_TIMESTAMP('2008-03-03 22:15:42','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a geographical Region',14,'EE04','The Region identifies a unique Region for this Country.','Y','Y','N','N','N','N','N','Region',0,0,TO_TIMESTAMP('2008-03-03 22:15:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:44 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54500 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:44 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,2252,54501,0,53079,TO_TIMESTAMP('2008-03-03 22:15:44','YYYY-MM-DD HH24:MI:SS'),0,'Receiving Country',14,'EE04','The To Country indicates the receiving country on a document','Y','Y','N','N','N','N','Y','To',0,0,TO_TIMESTAMP('2008-03-03 22:15:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:44 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54501 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:45 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,2253,54502,0,53079,TO_TIMESTAMP('2008-03-03 22:15:44','YYYY-MM-DD HH24:MI:SS'),0,'Receiving Region',14,'EE04','The To Region indicates the receiving region on a document','Y','Y','N','N','N','N','Y','To',0,0,TO_TIMESTAMP('2008-03-03 22:15:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:45 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54502 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:46 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,2241,54503,0,53079,TO_TIMESTAMP('2008-03-03 22:15:45','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',14,'EE04','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_TIMESTAMP('2008-03-03 22:15:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:46 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54503 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:47 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,2242,54504,0,53079,TO_TIMESTAMP('2008-03-03 22:15:46','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',14,'EE04','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_TIMESTAMP('2008-03-03 22:15:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:47 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54504 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:48 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,2246,54505,0,53079,TO_TIMESTAMP('2008-03-03 22:15:47','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',60,'EE04','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',30,1,TO_TIMESTAMP('2008-03-03 22:15:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:48 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54505 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:50 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,2247,54506,0,53079,TO_TIMESTAMP('2008-03-03 22:15:48','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',60,'EE04','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',40,0,TO_TIMESTAMP('2008-03-03 22:15:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:50 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54506 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:50 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,2243,54507,0,53079,TO_TIMESTAMP('2008-03-03 22:15:50','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE04','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',50,0,TO_TIMESTAMP('2008-03-03 22:15:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:50 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54507 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:51 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,4211,54508,0,53079,TO_TIMESTAMP('2008-03-03 22:15:50','YYYY-MM-DD HH24:MI:SS'),0,'Default value',1,'EE04','The Default Checkbox indicates if this record will be used as a default value.','Y','Y','Y','N','N','N','Y','Default',60,0,TO_TIMESTAMP('2008-03-03 22:15:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:51 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54508 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:52 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,2254,54509,0,53079,TO_TIMESTAMP('2008-03-03 22:15:51','YYYY-MM-DD HH24:MI:SS'),0,'Tax Category',14,'EE04','The Tax Category provides a method of grouping similar taxes.  For example, Sales Tax or Value Added Tax.','Y','Y','Y','N','N','N','N','Tax Category',70,0,TO_TIMESTAMP('2008-03-03 22:15:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:52 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54509 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:53 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,3054,54510,0,53079,TO_TIMESTAMP('2008-03-03 22:15:52','YYYY-MM-DD HH24:MI:SS'),0,'Valid from including this date (first day)',14,'EE04','The Valid From date indicates the first day of a date range','Y','Y','Y','N','N','N','Y','Valid from',80,0,TO_TIMESTAMP('2008-03-03 22:15:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:53 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54510 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:54 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,7971,54511,0,53079,TO_TIMESTAMP('2008-03-03 22:15:53','YYYY-MM-DD HH24:MI:SS'),0,'Business partner is exempt from tax',1,'EE04','If a business partner is exempt from tax, the exempt tax rate is used. For this, you need to set up a tax rate with a 0% rate and indicate that this is your tax exempt rate.  This is required for tax reporting, so that you can track tax exempt transactions.','Y','Y','Y','N','N','N','N','Tax exempt',90,0,TO_TIMESTAMP('2008-03-03 22:15:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:54 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54511 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:54 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,3695,54512,0,53079,TO_TIMESTAMP('2008-03-03 22:15:54','YYYY-MM-DD HH24:MI:SS'),0,'This tax rate requires the Business Partner to be tax exempt',1,'EE04','The Requires Tax Certificate indicates that a tax certificate is required for a Business Partner to be tax exempt.','Y','Y','Y','N','N','N','Y','Requires Tax Certificate',100,0,TO_TIMESTAMP('2008-03-03 22:15:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:54 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54512 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:55 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,3053,54513,0,53079,TO_TIMESTAMP('2008-03-03 22:15:54','YYYY-MM-DD HH24:MI:SS'),0,'Tax is calculated on document level (rather than line by line)',1,'EE04','If the tax is calculated on document level, all lines with that tax rate are added before calculating the total tax for the document.
Otherwise the tax is calculated per line and then added.
Due to rounding, the tax amount can differ.','Y','Y','Y','N','N','N','N','Document Level',110,0,TO_TIMESTAMP('2008-03-03 22:15:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:55 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54513 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:56 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,14528,54514,0,53079,TO_TIMESTAMP('2008-03-03 22:15:55','YYYY-MM-DD HH24:MI:SS'),0,'This is a sales tax (i.e. not a value added tax)',1,'EE04','If selected AP tax is handled as expense, otherwise it is handeled as a VAT credit.','Y','Y','Y','N','N','N','Y','Sales Tax',120,0,TO_TIMESTAMP('2008-03-03 22:15:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:56 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54514 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:57 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,3055,54515,0,53079,TO_TIMESTAMP('2008-03-03 22:15:56','YYYY-MM-DD HH24:MI:SS'),0,'This is a summary entity',1,'EE04','A summary entity represents a branch in a tree rather than an end-node. Summary entities are used for reporting and do not have own values.','Y','Y','Y','N','N','N','N','Summary Level',130,0,TO_TIMESTAMP('2008-03-03 22:15:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:57 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54515 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:57 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,2249,54516,0,53079,TO_TIMESTAMP('2008-03-03 22:15:57','YYYY-MM-DD HH24:MI:SS'),0,'Parent Tax indicates a tax that is made up of multiple taxes',14,'@IsSummary@=''N''','EE04','The Parent Tax indicates a tax that is a reference for multiple taxes.  This allows you to charge multiple taxes on a document by entering the Parent Tax','Y','Y','Y','N','N','N','Y','Parent Tax',140,0,TO_TIMESTAMP('2008-03-03 22:15:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:57 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54516 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:58 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,9767,54517,0,53079,TO_TIMESTAMP('2008-03-03 22:15:57','YYYY-MM-DD HH24:MI:SS'),0,'Sales Tax applies to sales situations, Purchase Tax to purchase situations',14,'EE04','Sales Tax: charged when selling - examples: Sales Tax, Output VAT (payable)
Purchase Tax: tax charged when purchasing - examples: Use Tax, Input VAT (receivable)','Y','Y','Y','N','N','N','N','SO/PO Type',150,0,TO_TIMESTAMP('2008-03-03 22:15:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:58 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54517 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:15:59 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,3693,54518,0,53079,TO_TIMESTAMP('2008-03-03 22:15:58','YYYY-MM-DD HH24:MI:SS'),0,'Rate or Tax or Exchange',26,'EE04','The Rate indicates the percentage to be multiplied by the source to arrive at the tax or exchange amount.','Y','Y','Y','N','N','N','N','Rate',160,0,TO_TIMESTAMP('2008-03-03 22:15:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:15:59 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54518 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:16:00 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54408,54519,0,53079,TO_TIMESTAMP('2008-03-03 22:15:59','YYYY-MM-DD HH24:MI:SS'),0,22,'EE04','Y','Y','Y','N','N','N','Y','Rule',170,0,TO_TIMESTAMP('2008-03-03 22:15:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:00 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54519 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:16:01 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,2240,54520,0,53079,TO_TIMESTAMP('2008-03-03 22:16:00','YYYY-MM-DD HH24:MI:SS'),0,'Tax identifier',14,'EE04','The Tax indicates the type of tax used in document line.','Y','Y','Y','N','N','N','N','Tax',180,0,TO_TIMESTAMP('2008-03-03 22:16:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:01 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54520 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:16:01 PM CST
-- Tax Global Management
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,Description,EntityType,HasTree,Help,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53080,701,53022,NULL,TO_TIMESTAMP('2008-03-03 22:16:01','YYYY-MM-DD HH24:MI:SS'),0,'Tax Postal/ZIP','EE04','N','For local tax you may have to define a list of (ranges of) postal codes or ZIPs','Y','N','N','Y','N','N','N','N','Tax ZIP','N',20,1,TO_TIMESTAMP('2008-03-03 22:16:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:01 PM CST
-- Tax Global Management
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53080 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Mar 3, 2008 10:16:02 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,11456,54521,0,53080,TO_TIMESTAMP('2008-03-03 22:16:01','YYYY-MM-DD HH24:MI:SS'),0,'Tax Postal/ZIP',14,'EE04','For local tax, you may have to define a list of (ranges of) postal codes or ZIPs','Y','Y','N','N','N','N','N','Tax ZIP',10,0,TO_TIMESTAMP('2008-03-03 22:16:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:02 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54521 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:16:03 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,11455,54522,0,53080,TO_TIMESTAMP('2008-03-03 22:16:02','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',14,'EE04','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','Y','N','Client',20,0,TO_TIMESTAMP('2008-03-03 22:16:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:03 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54522 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:16:04 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,11461,54523,0,53080,TO_TIMESTAMP('2008-03-03 22:16:03','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',14,'EE04','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','Y','Y','Organization',30,0,TO_TIMESTAMP('2008-03-03 22:16:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:04 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54523 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:16:05 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,11463,54524,0,53080,TO_TIMESTAMP('2008-03-03 22:16:04','YYYY-MM-DD HH24:MI:SS'),0,'Tax identifier',14,'EE04','The Tax indicates the type of tax used in document line.','Y','Y','Y','N','N','Y','N','Tax',40,0,TO_TIMESTAMP('2008-03-03 22:16:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:05 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54524 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:16:06 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,11457,54525,0,53080,TO_TIMESTAMP('2008-03-03 22:16:05','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE04','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',50,0,TO_TIMESTAMP('2008-03-03 22:16:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:06 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54525 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:16:06 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,11465,54526,0,53080,TO_TIMESTAMP('2008-03-03 22:16:06','YYYY-MM-DD HH24:MI:SS'),0,'Postal code',11,'EE04','The Postal Code or ZIP identifies the postal code for this entity''s address.','Y','Y','Y','N','N','N','N','ZIP',60,1,TO_TIMESTAMP('2008-03-03 22:16:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:06 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54526 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:16:07 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,11462,54527,0,53080,TO_TIMESTAMP('2008-03-03 22:16:06','YYYY-MM-DD HH24:MI:SS'),0,'Postal code to',11,'EE04','Conecutive range to','Y','Y','Y','N','N','N','Y','ZIP To',70,0,TO_TIMESTAMP('2008-03-03 22:16:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:07 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54527 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:16:09 PM CST
-- Tax Global Management
INSERT INTO AD_Tab (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,8201,0,53081,546,53022,NULL,TO_TIMESTAMP('2008-03-03 22:16:07','YYYY-MM-DD HH24:MI:SS'),0,'EE04','N','Y','N','N','N','N','Y','N','Y','Translation','N',30,1,TO_TIMESTAMP('2008-03-03 22:16:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:09 PM CST
-- Tax Global Management
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53081 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Mar 3, 2008 10:16:14 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,8206,54528,0,53081,TO_TIMESTAMP('2008-03-03 22:16:09','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',14,'EE04','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','Y','N','Client',10,0,TO_TIMESTAMP('2008-03-03 22:16:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:14 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54528 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:16:15 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,8211,54529,0,53081,TO_TIMESTAMP('2008-03-03 22:16:14','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',14,'EE04','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','Y','Y','Organization',20,0,TO_TIMESTAMP('2008-03-03 22:16:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:15 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54529 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:16:15 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,8201,54530,0,53081,TO_TIMESTAMP('2008-03-03 22:16:15','YYYY-MM-DD HH24:MI:SS'),0,'Tax identifier',14,'EE04','The Tax indicates the type of tax used in document line.','Y','Y','Y','N','N','Y','N','Tax',30,0,TO_TIMESTAMP('2008-03-03 22:16:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:15 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54530 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:16:16 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,8202,54531,0,53081,TO_TIMESTAMP('2008-03-03 22:16:15','YYYY-MM-DD HH24:MI:SS'),0,'Language for this entity',14,'EE04','The Language identifies the language to use for display and formatting','Y','Y','Y','N','N','N','N','Language',40,0,TO_TIMESTAMP('2008-03-03 22:16:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:16 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54531 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:16:17 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,8209,54532,0,53081,TO_TIMESTAMP('2008-03-03 22:16:16','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE04','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',50,0,TO_TIMESTAMP('2008-03-03 22:16:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:17 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54532 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:16:18 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,8204,54533,0,53081,TO_TIMESTAMP('2008-03-03 22:16:17','YYYY-MM-DD HH24:MI:SS'),0,'This column is translated',1,'EE04','The Translated checkbox indicates if this column is translated.','Y','Y','Y','N','N','N','N','Translated',60,0,TO_TIMESTAMP('2008-03-03 22:16:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:18 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54533 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:16:19 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,8210,54534,0,53081,TO_TIMESTAMP('2008-03-03 22:16:18','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',60,'EE04','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',70,0,TO_TIMESTAMP('2008-03-03 22:16:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:19 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54534 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:16:20 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,8203,54535,0,53081,TO_TIMESTAMP('2008-03-03 22:16:19','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',60,'EE04','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',80,0,TO_TIMESTAMP('2008-03-03 22:16:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:20 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54535 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:16:21 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,8199,54536,0,53081,TO_TIMESTAMP('2008-03-03 22:16:20','YYYY-MM-DD HH24:MI:SS'),0,'Short form for Tax to be printed on documents',5,'EE04','The Tax Indicator identifies the short name that will print on documents referencing this tax.','Y','Y','Y','N','N','N','N','Tax Indicator',90,0,TO_TIMESTAMP('2008-03-03 22:16:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:21 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54536 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:16:21 PM CST
-- Tax Global Management
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType) VALUES (0,0,53023,TO_TIMESTAMP('2008-03-03 22:16:21','YYYY-MM-DD HH24:MI:SS'),0,'Tax Types let you group taxes together.','EE04','Y','N','N','Y','Tax Type','N',TO_TIMESTAMP('2008-03-03 22:16:21','YYYY-MM-DD HH24:MI:SS'),0,'M')
;

-- Mar 3, 2008 10:16:21 PM CST
-- Tax Global Management
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53023 AND EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Window_ID!=t.AD_Window_ID)
;

-- Mar 3, 2008 10:16:21 PM CST
-- Tax Global Management
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,0,53023,TO_TIMESTAMP('2008-03-03 22:16:21','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_TIMESTAMP('2008-03-03 22:16:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:21 PM CST
-- Tax Global Management
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,102,53023,TO_TIMESTAMP('2008-03-03 22:16:21','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_TIMESTAMP('2008-03-03 22:16:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:21 PM CST
-- Tax Global Management
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,103,53023,TO_TIMESTAMP('2008-03-03 22:16:21','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_TIMESTAMP('2008-03-03 22:16:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:21 PM CST
-- Tax Global Management
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,50001,53023,TO_TIMESTAMP('2008-03-03 22:16:21','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_TIMESTAMP('2008-03-03 22:16:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:22 PM CST
-- Tax Global Management
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_Window_ID,AccessLevel,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53068,53023,'3',TO_TIMESTAMP('2008-03-03 22:16:21','YYYY-MM-DD HH24:MI:SS'),0,'Tax Type','EE04','N','Y','N','Y','N','N','N','Tax Type','L','C_TaxType',TO_TIMESTAMP('2008-03-03 22:16:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:22 PM CST
-- Tax Global Management
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53068 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Mar 3, 2008 10:16:23 PM CST
-- Tax Global Management
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53085,TO_TIMESTAMP('2008-03-03 22:16:22','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table C_TaxType',1,'Y','N','Y','Y','C_TaxType','N',1000000,TO_TIMESTAMP('2008-03-03 22:16:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:23 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54437,469,0,10,53068,'Name',TO_TIMESTAMP('2008-03-03 22:16:23','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE04',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','Y','N','Y','Name',1,TO_TIMESTAMP('2008-03-03 22:16:23','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:16:23 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54437 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:16:24 PM CST
-- Tax Global Management
CREATE TABLE C_TaxType (Name VARCHAR(60) NOT NULL)
;

-- Mar 3, 2008 10:16:25 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54438,113,0,19,53068,104,'AD_Org_ID',TO_TIMESTAMP('2008-03-03 22:16:24','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','EE04',10,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','Y','N','N','Organization',TO_TIMESTAMP('2008-03-03 22:16:24','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:16:25 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54438 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:16:25 PM CST
-- Tax Global Management
ALTER TABLE C_TaxType ADD COLUMN AD_Org_ID NUMERIC(10) DEFAULT NULL NOT NULL
;

-- Mar 3, 2008 10:16:27 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54439,53359,0,13,53068,'C_TaxType_ID',TO_TIMESTAMP('2008-03-03 22:16:25','YYYY-MM-DD HH24:MI:SS'),0,'EE04',10,'Y','N','N','N','Y','Y','N','N','Y','N','N','C_TaxType_ID',TO_TIMESTAMP('2008-03-03 22:16:25','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:16:27 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54439 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:16:27 PM CST
-- Tax Global Management
ALTER TABLE C_TaxType ADD COLUMN C_TaxType_ID NUMERIC(10) NOT NULL
;

-- Mar 3, 2008 10:16:27 PM CST
-- Tax Global Management
ALTER TABLE C_TaxType ADD CONSTRAINT C_TaxType_Key PRIMARY KEY (C_TaxType_ID)
;

-- Mar 3, 2008 10:16:28 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54440,245,0,16,53068,'Created',TO_TIMESTAMP('2008-03-03 22:16:27','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE04',29,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','Y','N','N','Created',TO_TIMESTAMP('2008-03-03 22:16:27','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:16:28 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54440 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:16:28 PM CST
-- Tax Global Management
ALTER TABLE C_TaxType ADD COLUMN Created TIMESTAMP NOT NULL
;

-- Mar 3, 2008 10:16:28 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54441,246,0,18,110,53068,'CreatedBy',TO_TIMESTAMP('2008-03-03 22:16:28','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE04',10,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Created By',TO_TIMESTAMP('2008-03-03 22:16:28','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:16:28 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54441 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:16:28 PM CST
-- Tax Global Management
ALTER TABLE C_TaxType ADD COLUMN CreatedBy NUMERIC(10) NOT NULL
;

-- Mar 3, 2008 10:16:29 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54442,275,0,10,53068,'Description',TO_TIMESTAMP('2008-03-03 22:16:28','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE04',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','N','Y','Description',TO_TIMESTAMP('2008-03-03 22:16:28','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:16:29 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54442 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:16:29 PM CST
-- Tax Global Management
ALTER TABLE C_TaxType ADD COLUMN Description VARCHAR(255)
;

-- Mar 3, 2008 10:16:30 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54443,326,0,14,53068,'Help',TO_TIMESTAMP('2008-03-03 22:16:29','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','EE04',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','Y','N','Y','Comment/Help',TO_TIMESTAMP('2008-03-03 22:16:29','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:16:30 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54443 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:16:30 PM CST
-- Tax Global Management
ALTER TABLE C_TaxType ADD COLUMN Help VARCHAR(2000)
;

-- Mar 3, 2008 10:16:32 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54444,348,0,20,53068,'IsActive',TO_TIMESTAMP('2008-03-03 22:16:30','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','EE04',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','Y','N','Y','Active',TO_TIMESTAMP('2008-03-03 22:16:30','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:16:32 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54444 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:16:32 PM CST
-- Tax Global Management
ALTER TABLE C_TaxType ADD COLUMN IsActive CHAR(1) CHECK (IsActive IN ('Y','N')) NOT NULL
;

-- Mar 3, 2008 10:16:33 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54445,607,0,16,53068,'Updated',TO_TIMESTAMP('2008-03-03 22:16:33','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE04',29,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated',TO_TIMESTAMP('2008-03-03 22:16:33','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:16:33 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54445 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:16:33 PM CST
-- Tax Global Management
ALTER TABLE C_TaxType ADD COLUMN Updated TIMESTAMP NOT NULL
;

-- Mar 3, 2008 10:16:34 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54446,608,0,18,110,53068,'UpdatedBy',TO_TIMESTAMP('2008-03-03 22:16:33','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE04',10,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated By',TO_TIMESTAMP('2008-03-03 22:16:33','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:16:34 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54446 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:16:34 PM CST
-- Tax Global Management
ALTER TABLE C_TaxType ADD COLUMN UpdatedBy NUMERIC(10) NOT NULL
;

-- Mar 3, 2008 10:16:35 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54447,102,0,19,53068,129,'AD_Client_ID',TO_TIMESTAMP('2008-03-03 22:16:34','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','EE04',10,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','Y','N','N','Client',TO_TIMESTAMP('2008-03-03 22:16:34','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:16:35 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54447 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:16:35 PM CST
-- Tax Global Management
ALTER TABLE C_TaxType ADD COLUMN AD_Client_ID NUMERIC(10) DEFAULT NULL NOT NULL
;

-- Mar 3, 2008 10:16:36 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54448,620,0,10,53068,'Value',TO_TIMESTAMP('2008-03-03 22:16:35','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique','EE04',40,'A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','Y','N','N','Y','N','Y','Search Key',TO_TIMESTAMP('2008-03-03 22:16:35','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:16:36 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54448 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:16:36 PM CST
-- Tax Global Management
ALTER TABLE C_TaxType ADD COLUMN Value VARCHAR(40) NOT NULL
;

-- Mar 3, 2008 10:16:37 PM CST
-- Tax Global Management
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,Description,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53082,53068,53023,NULL,TO_TIMESTAMP('2008-03-03 22:16:36','YYYY-MM-DD HH24:MI:SS'),0,'Tax Types let you group taxes together.','EE04','N','Y','N','N','Y','N','Y','N','N','Tax Type','N',10,0,TO_TIMESTAMP('2008-03-03 22:16:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:37 PM CST
-- Tax Global Management
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53082 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Mar 3, 2008 10:16:37 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54439,54537,0,53082,TO_TIMESTAMP('2008-03-03 22:16:37','YYYY-MM-DD HH24:MI:SS'),0,10,'EE04','Y','Y','N','N','N','N','N','C_TaxType_ID',0,0,TO_TIMESTAMP('2008-03-03 22:16:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:37 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54537 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:16:38 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54447,54538,0,53082,TO_TIMESTAMP('2008-03-03 22:16:38','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',10,'EE04','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_TIMESTAMP('2008-03-03 22:16:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:38 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54538 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:16:39 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54438,54539,0,53082,TO_TIMESTAMP('2008-03-03 22:16:38','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',10,'EE04','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_TIMESTAMP('2008-03-03 22:16:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:39 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54539 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:16:40 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54448,54540,0,53082,TO_TIMESTAMP('2008-03-03 22:16:39','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique',40,'EE04','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','N','Search Key',30,0,TO_TIMESTAMP('2008-03-03 22:16:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:40 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54540 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:16:40 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54437,54541,0,53082,TO_TIMESTAMP('2008-03-03 22:16:40','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',60,'EE04','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',40,0,TO_TIMESTAMP('2008-03-03 22:16:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:40 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54541 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:16:41 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54442,54542,0,53082,TO_TIMESTAMP('2008-03-03 22:16:40','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',255,'EE04','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',50,0,TO_TIMESTAMP('2008-03-03 22:16:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:41 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54542 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:16:42 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54443,54543,0,53082,TO_TIMESTAMP('2008-03-03 22:16:41','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint',2000,'EE04','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','Comment/Help',60,0,TO_TIMESTAMP('2008-03-03 22:16:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:42 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54543 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:16:43 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54444,54544,0,53082,TO_TIMESTAMP('2008-03-03 22:16:42','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE04','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',70,0,TO_TIMESTAMP('2008-03-03 22:16:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:43 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54544 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:16:43 PM CST
-- Tax Global Management
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType) VALUES (0,0,53024,TO_TIMESTAMP('2008-03-03 22:16:43','YYYY-MM-DD HH24:MI:SS'),0,'Defines tax base for a tax','EE04','The Tax Base can be based on price , cost and quantity','Y','N','N','Y','Tax Base','N',TO_TIMESTAMP('2008-03-03 22:16:43','YYYY-MM-DD HH24:MI:SS'),0,'M')
;

-- Mar 3, 2008 10:16:43 PM CST
-- Tax Global Management
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53024 AND EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Window_ID!=t.AD_Window_ID)
;

-- Mar 3, 2008 10:16:43 PM CST
-- Tax Global Management
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,0,53024,TO_TIMESTAMP('2008-03-03 22:16:43','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_TIMESTAMP('2008-03-03 22:16:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:43 PM CST
-- Tax Global Management
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,102,53024,TO_TIMESTAMP('2008-03-03 22:16:43','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_TIMESTAMP('2008-03-03 22:16:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:43 PM CST
-- Tax Global Management
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,103,53024,TO_TIMESTAMP('2008-03-03 22:16:43','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_TIMESTAMP('2008-03-03 22:16:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:43 PM CST
-- Tax Global Management
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,50001,53024,TO_TIMESTAMP('2008-03-03 22:16:43','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_TIMESTAMP('2008-03-03 22:16:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:44 PM CST
-- Tax Global Management
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_Window_ID,AccessLevel,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53069,53024,'3',TO_TIMESTAMP('2008-03-03 22:16:43','YYYY-MM-DD HH24:MI:SS'),0,'Tax Base','EE04','N','Y','N','Y','N','N','N','Tax Base','L','C_TaxBase',TO_TIMESTAMP('2008-03-03 22:16:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:44 PM CST
-- Tax Global Management
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53069 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Mar 3, 2008 10:16:44 PM CST
-- Tax Global Management
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53086,TO_TIMESTAMP('2008-03-03 22:16:44','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table C_TaxBase',1,'Y','N','Y','Y','C_TaxBase','N',1000000,TO_TIMESTAMP('2008-03-03 22:16:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:16:45 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54449,469,0,10,53069,'Name',TO_TIMESTAMP('2008-03-03 22:16:45','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE04',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','Y','N','Y','Name',1,TO_TIMESTAMP('2008-03-03 22:16:45','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:16:45 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54449 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:16:45 PM CST
-- Tax Global Management
CREATE TABLE C_TaxBase (Name VARCHAR(60) NOT NULL)
;

-- Mar 3, 2008 10:16:46 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54450,113,0,19,53069,104,'AD_Org_ID',TO_TIMESTAMP('2008-03-03 22:16:45','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','EE04',10,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','Y','N','N','Organization',TO_TIMESTAMP('2008-03-03 22:16:45','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:16:46 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54450 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:16:46 PM CST
-- Tax Global Management
ALTER TABLE C_TaxBase ADD COLUMN AD_Org_ID NUMERIC(10) DEFAULT NULL NOT NULL
;

-- Mar 3, 2008 10:16:47 PM CST
-- Tax Global Management
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53240,TO_TIMESTAMP('2008-03-03 22:16:46','YYYY-MM-DD HH24:MI:SS'),0,'EE04','Y','C_TaxBase',TO_TIMESTAMP('2008-03-03 22:16:46','YYYY-MM-DD HH24:MI:SS'),0,'L')
;

-- Mar 3, 2008 10:16:47 PM CST
-- Tax Global Management
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53240 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- Mar 3, 2008 10:16:48 PM CST
-- Tax Global Management
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53330,53240,TO_TIMESTAMP('2008-03-03 22:16:47','YYYY-MM-DD HH24:MI:SS'),0,'EE04','Y','Cost',TO_TIMESTAMP('2008-03-03 22:16:47','YYYY-MM-DD HH24:MI:SS'),0,'C')
;

-- Mar 3, 2008 10:16:48 PM CST
-- Tax Global Management
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53330 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Mar 3, 2008 10:16:48 PM CST
-- Tax Global Management
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53331,53240,TO_TIMESTAMP('2008-03-03 22:16:48','YYYY-MM-DD HH24:MI:SS'),0,'EE04','Y','Price',TO_TIMESTAMP('2008-03-03 22:16:48','YYYY-MM-DD HH24:MI:SS'),0,'P')
;

-- Mar 3, 2008 10:16:48 PM CST
-- Tax Global Management
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53331 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Mar 3, 2008 10:16:49 PM CST
-- Tax Global Management
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53332,53240,TO_TIMESTAMP('2008-03-03 22:16:48','YYYY-MM-DD HH24:MI:SS'),0,'EE04','Y','Quantity',TO_TIMESTAMP('2008-03-03 22:16:48','YYYY-MM-DD HH24:MI:SS'),0,'Q')
;

-- Mar 3, 2008 10:16:49 PM CST
-- Tax Global Management
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53332 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Mar 3, 2008 10:16:50 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54451,2818,0,17,53240,53069,'Base',TO_TIMESTAMP('2008-03-03 22:16:49','YYYY-MM-DD HH24:MI:SS'),0,'Calculation Base','EE04',1,'Y','N','N','N','N','N','N','N','Y','N','Y','Base',TO_TIMESTAMP('2008-03-03 22:16:49','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:16:50 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54451 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:16:50 PM CST
-- Tax Global Management
ALTER TABLE C_TaxBase ADD COLUMN Base CHAR(1)
;

-- Mar 3, 2008 10:16:50 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54452,53357,0,13,53069,'C_TaxBase_ID',TO_TIMESTAMP('2008-03-03 22:16:50','YYYY-MM-DD HH24:MI:SS'),0,'EE04',10,'Y','N','N','N','Y','Y','N','N','Y','N','N','C_TaxBase_ID',TO_TIMESTAMP('2008-03-03 22:16:50','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:16:50 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54452 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:16:51 PM CST
-- Tax Global Management
ALTER TABLE C_TaxBase ADD COLUMN C_TaxBase_ID NUMERIC(10) NOT NULL
;

-- Mar 3, 2008 10:16:51 PM CST
-- Tax Global Management
ALTER TABLE C_TaxBase ADD CONSTRAINT C_TaxBase_Key PRIMARY KEY (C_TaxBase_ID)
;

-- Mar 3, 2008 10:16:51 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54453,245,0,16,53069,'Created',TO_TIMESTAMP('2008-03-03 22:16:51','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE04',29,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','Y','N','N','Created',TO_TIMESTAMP('2008-03-03 22:16:51','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:16:51 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54453 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:16:51 PM CST
-- Tax Global Management
ALTER TABLE C_TaxBase ADD COLUMN Created TIMESTAMP NOT NULL
;

-- Mar 3, 2008 10:16:53 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54454,246,0,18,110,53069,'CreatedBy',TO_TIMESTAMP('2008-03-03 22:16:51','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE04',10,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Created By',TO_TIMESTAMP('2008-03-03 22:16:51','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:16:53 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54454 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:16:53 PM CST
-- Tax Global Management
ALTER TABLE C_TaxBase ADD COLUMN CreatedBy NUMERIC(10) NOT NULL
;

-- Mar 3, 2008 10:16:54 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54455,275,0,10,53069,'Description',TO_TIMESTAMP('2008-03-03 22:16:53','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE04',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','N','Y','Description',TO_TIMESTAMP('2008-03-03 22:16:53','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:16:54 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54455 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:16:54 PM CST
-- Tax Global Management
ALTER TABLE C_TaxBase ADD COLUMN Description VARCHAR(255)
;

-- Mar 3, 2008 10:16:54 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54456,326,0,14,53069,'Help',TO_TIMESTAMP('2008-03-03 22:16:54','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','EE04',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','Y','N','Y','Comment/Help',TO_TIMESTAMP('2008-03-03 22:16:54','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:16:54 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54456 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:16:54 PM CST
-- Tax Global Management
ALTER TABLE C_TaxBase ADD COLUMN Help VARCHAR(2000)
;

-- Mar 3, 2008 10:16:55 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54457,348,0,20,53069,'IsActive',TO_TIMESTAMP('2008-03-03 22:16:55','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','EE04',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','Y','N','Y','Active',TO_TIMESTAMP('2008-03-03 22:16:55','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:16:55 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54457 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:16:55 PM CST
-- Tax Global Management
ALTER TABLE C_TaxBase ADD COLUMN IsActive CHAR(1) CHECK (IsActive IN ('Y','N')) NOT NULL
;

-- Mar 3, 2008 10:16:56 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54458,2004,0,11,53069,'Percentage',TO_TIMESTAMP('2008-03-03 22:16:55','YYYY-MM-DD HH24:MI:SS'),0,'Percent of the entire amount','EE04',10,'Percentage of an amount (up to 100)','Y','N','N','N','N','N','N','N','Y','N','Y','Percentage',TO_TIMESTAMP('2008-03-03 22:16:55','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:16:56 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54458 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:16:56 PM CST
-- Tax Global Management
ALTER TABLE C_TaxBase ADD COLUMN Percentage NUMERIC(10)
;

-- Mar 3, 2008 10:16:57 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54459,607,0,16,53069,'Updated',TO_TIMESTAMP('2008-03-03 22:16:56','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE04',29,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated',TO_TIMESTAMP('2008-03-03 22:16:56','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:16:57 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54459 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:16:57 PM CST
-- Tax Global Management
ALTER TABLE C_TaxBase ADD COLUMN Updated TIMESTAMP NOT NULL
;

-- Mar 3, 2008 10:16:58 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54460,608,0,18,110,53069,'UpdatedBy',TO_TIMESTAMP('2008-03-03 22:16:57','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE04',10,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated By',TO_TIMESTAMP('2008-03-03 22:16:57','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:16:58 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54460 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:16:58 PM CST
-- Tax Global Management
ALTER TABLE C_TaxBase ADD COLUMN UpdatedBy NUMERIC(10) NOT NULL
;

-- Mar 3, 2008 10:16:59 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54461,102,0,19,53069,129,'AD_Client_ID',TO_TIMESTAMP('2008-03-03 22:16:58','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','EE04',10,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','Y','N','N','Client',TO_TIMESTAMP('2008-03-03 22:16:58','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:16:59 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54461 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:16:59 PM CST
-- Tax Global Management
ALTER TABLE C_TaxBase ADD COLUMN AD_Client_ID NUMERIC(10) DEFAULT NULL NOT NULL
;

-- Mar 3, 2008 10:16:59 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54462,620,0,10,53069,'Value',TO_TIMESTAMP('2008-03-03 22:16:59','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique','EE04',40,'A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','Y','N','N','Y','N','Y','Search Key',TO_TIMESTAMP('2008-03-03 22:16:59','YYYY-MM-DD HH24:MI:SS'),0,0.0)
;

-- Mar 3, 2008 10:17:00 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54462 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:17:00 PM CST
-- Tax Global Management
ALTER TABLE C_TaxBase ADD COLUMN Value VARCHAR(40) NOT NULL
;

-- Mar 3, 2008 10:17:00 PM CST
-- Tax Global Management
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53083,53069,53024,NULL,TO_TIMESTAMP('2008-03-03 22:17:00','YYYY-MM-DD HH24:MI:SS'),0,'EE04','N','Y','N','N','Y','N','Y','N','N','Tax Base','N',10,0,TO_TIMESTAMP('2008-03-03 22:17:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:17:00 PM CST
-- Tax Global Management
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53083 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Mar 3, 2008 10:17:01 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54452,54545,0,53083,TO_TIMESTAMP('2008-03-03 22:17:00','YYYY-MM-DD HH24:MI:SS'),0,10,'EE04','Y','Y','N','N','N','N','N','C_TaxBase_ID',0,0,TO_TIMESTAMP('2008-03-03 22:17:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:17:01 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54545 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:17:02 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54461,54546,0,53083,TO_TIMESTAMP('2008-03-03 22:17:01','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',10,'EE04','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_TIMESTAMP('2008-03-03 22:17:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:17:02 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54546 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:17:03 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54450,54547,0,53083,TO_TIMESTAMP('2008-03-03 22:17:02','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',10,'EE04','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_TIMESTAMP('2008-03-03 22:17:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:17:03 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54547 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:17:04 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54462,54548,0,53083,TO_TIMESTAMP('2008-03-03 22:17:03','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique',40,'EE04','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','N','Search Key',30,0,TO_TIMESTAMP('2008-03-03 22:17:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:17:04 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54548 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:17:05 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54449,54549,0,53083,TO_TIMESTAMP('2008-03-03 22:17:04','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',60,'EE04','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',40,0,TO_TIMESTAMP('2008-03-03 22:17:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:17:05 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54549 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:17:06 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54455,54550,0,53083,TO_TIMESTAMP('2008-03-03 22:17:05','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',255,'EE04','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',50,0,TO_TIMESTAMP('2008-03-03 22:17:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:17:06 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54550 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:17:07 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54456,54551,0,53083,TO_TIMESTAMP('2008-03-03 22:17:06','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint',2000,'EE04','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','Comment/Help',60,0,TO_TIMESTAMP('2008-03-03 22:17:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:17:07 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54551 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:17:07 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54457,54552,0,53083,TO_TIMESTAMP('2008-03-03 22:17:07','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE04','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',70,0,TO_TIMESTAMP('2008-03-03 22:17:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:17:07 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54552 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:17:08 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54451,54553,0,53083,TO_TIMESTAMP('2008-03-03 22:17:07','YYYY-MM-DD HH24:MI:SS'),0,'Calculation Base',1,'EE04','Y','Y','Y','N','N','N','N','Base',80,0,TO_TIMESTAMP('2008-03-03 22:17:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:17:08 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54553 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:17:09 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54458,54554,0,53083,TO_TIMESTAMP('2008-03-03 22:17:08','YYYY-MM-DD HH24:MI:SS'),0,'Percent of the entire amount',10,'EE04','Percentage of an amount (up to 100)','Y','Y','Y','N','N','N','Y','Percentage',90,0,TO_TIMESTAMP('2008-03-03 22:17:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:17:09 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54554 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:17:10 PM CST
-- Tax Global Management
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53091,0,TO_TIMESTAMP('2008-03-03 22:17:09','YYYY-MM-DD HH24:MI:SS'),0,'EE04','Y','Y','N','Y','Global Tax Management',TO_TIMESTAMP('2008-03-03 22:17:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:17:10 PM CST
-- Tax Global Management
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53091 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Mar 3, 2008 10:17:10 PM CST
-- Tax Global Management
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 164,26, 10, 53091)
;

-- Mar 3, 2008 10:17:10 PM CST
-- Tax Global Management
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,"action",Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53092,0,53020,'W',TO_TIMESTAMP('2008-03-03 22:17:10','YYYY-MM-DD HH24:MI:SS'),0,'Tax Groups let you group the business partner with a reference tax.','EE04','Y','N','N','N','Tax Group',TO_TIMESTAMP('2008-03-03 22:17:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:17:10 PM CST
-- Tax Global Management
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53092 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Mar 3, 2008 10:17:10 PM CST
-- Tax Global Management
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53091,2, 10, 53092)
;

-- Mar 3, 2008 10:17:11 PM CST
-- Tax Global Management
UPDATE AD_TREENODEMM SET Parent_ID = 53091 , SeqNo = 1 WHERE AD_Tree_ID = 10 AND Node_ID = 124
;

-- Mar 3, 2008 10:17:11 PM CST
-- Tax Global Management
UPDATE AD_TREENODEMM SET Parent_ID = 53091 , SeqNo = 8 WHERE AD_Tree_ID = 10 AND Node_ID = 547
;

-- Mar 3, 2008 10:17:11 PM CST
-- Tax Global Management
UPDATE AD_TREENODEMM SET Parent_ID = 53091 , SeqNo = 5 WHERE AD_Tree_ID = 10 AND Node_ID = 123
;

-- Mar 3, 2008 10:17:12 PM CST
-- Tax Global Management
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Process_ID,"action",Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53093,0,53072,'P',TO_TIMESTAMP('2008-03-03 22:17:11','YYYY-MM-DD HH24:MI:SS'),0,'EE04','Y','N','N','N','Invoice Calculate Tax',TO_TIMESTAMP('2008-03-03 22:17:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:17:12 PM CST
-- Tax Global Management
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53093 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Mar 3, 2008 10:17:12 PM CST
-- Tax Global Management
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53091,9, 10, 53093)
;

-- Mar 3, 2008 10:17:13 PM CST
-- Tax Global Management
UPDATE AD_TREENODEMM SET Parent_ID = 53091 , SeqNo = 0 WHERE AD_Tree_ID = 10 AND Node_ID = 270
;

-- Mar 3, 2008 10:17:14 PM CST
-- Tax Global Management
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,"action",Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53094,0,53021,'W',TO_TIMESTAMP('2008-03-03 22:17:13','YYYY-MM-DD HH24:MI:SS'),0,'Lets you define different tax combinations.','EE04','Y','N','N','N','Tax Definition',TO_TIMESTAMP('2008-03-03 22:17:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:17:14 PM CST
-- Tax Global Management
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53094 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Mar 3, 2008 10:17:14 PM CST
-- Tax Global Management
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53091,7, 10, 53094)
;

-- Mar 3, 2008 10:17:14 PM CST
-- Tax Global Management
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,"action",Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53095,0,53022,'W',TO_TIMESTAMP('2008-03-03 22:17:14','YYYY-MM-DD HH24:MI:SS'),0,'Maintain Taxes and their Rates','EE04','Y','N','N','N','Tax Rate Parent',TO_TIMESTAMP('2008-03-03 22:17:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:17:14 PM CST
-- Tax Global Management
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53095 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Mar 3, 2008 10:17:14 PM CST
-- Tax Global Management
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53091,6, 10, 53095)
;

-- Mar 3, 2008 10:17:15 PM CST
-- Tax Global Management
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,"action",Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53096,0,53023,'W',TO_TIMESTAMP('2008-03-03 22:17:14','YYYY-MM-DD HH24:MI:SS'),0,'Tax Types let you group taxes together.','EE04','Y','N','N','N','Tax Type',TO_TIMESTAMP('2008-03-03 22:17:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:17:15 PM CST
-- Tax Global Management
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53096 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Mar 3, 2008 10:17:15 PM CST
-- Tax Global Management
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53091,3, 10, 53096)
;

-- Mar 3, 2008 10:17:16 PM CST
-- Tax Global Management
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,"action",Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53097,0,53024,'W',TO_TIMESTAMP('2008-03-03 22:17:15','YYYY-MM-DD HH24:MI:SS'),0,'Defines tax base for a tax','EE04','Y','N','N','N','Tax Base',TO_TIMESTAMP('2008-03-03 22:17:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:17:16 PM CST
-- Tax Global Management
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53097 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Mar 3, 2008 10:17:16 PM CST
-- Tax Global Management
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53091,4, 10, 53097)
;

-- Mar 3, 2008 10:47:54 PM CST
-- Tax Global Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54463,53356,0,19,291,'C_TaxGroup_ID',TO_TIMESTAMP('2008-03-03 22:47:53','YYYY-MM-DD HH24:MI:SS'),0,'EE04',10,'Y','N','N','N','N','N','N','N','N','N','Y','C_TaxGroup_ID',0,TO_TIMESTAMP('2008-03-03 22:47:53','YYYY-MM-DD HH24:MI:SS'),0,1.000000000000)
;

-- Mar 3, 2008 10:47:54 PM CST
-- Tax Global Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54463 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 3, 2008 10:47:57 PM CST
-- Tax Global Management
ALTER TABLE C_BPartner ADD COLUMN C_TaxGroup_ID NUMERIC(10)
;

-- Mar 3, 2008 10:48:34 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54463,54555,0,220,TO_TIMESTAMP('2008-03-03 22:48:33','YYYY-MM-DD HH24:MI:SS'),0,10,'EE04','Y','Y','Y','N','N','N','N','N','C_TaxGroup_ID',TO_TIMESTAMP('2008-03-03 22:48:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:48:34 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54555 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:48:34 PM CST
-- Tax Global Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53246,54556,0,220,TO_TIMESTAMP('2008-03-03 22:48:34','YYYY-MM-DD HH24:MI:SS'),0,7,'D','Y','Y','Y','N','N','N','N','N','Dunning Grace',TO_TIMESTAMP('2008-03-03 22:48:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 3, 2008 10:48:34 PM CST
-- Tax Global Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54556 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 3, 2008 10:48:56 PM CST
-- Tax Global Management
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=54556
;

-- Mar 3, 2008 10:48:56 PM CST
-- Tax Global Management
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=54555
;

-- Mar 3, 2008 10:49:11 PM CST
-- Tax Global Management
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=54555
;

-- Mar 3, 2008 10:49:11 PM CST
-- Tax Global Management
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=2132
;

-- Mar 3, 2008 10:49:11 PM CST
-- Tax Global Management
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=2149
;

-- Mar 3, 2008 10:49:11 PM CST
-- Tax Global Management
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=2144
;

-- Mar 3, 2008 10:49:11 PM CST
-- Tax Global Management
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=2162
;

-- Mar 3, 2008 10:49:11 PM CST
-- Tax Global Management
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=3955
;

-- Mar 3, 2008 10:49:11 PM CST
-- Tax Global Management
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=2124
;

-- Mar 3, 2008 10:49:11 PM CST
-- Tax Global Management
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=2164
;

-- Mar 3, 2008 10:49:11 PM CST
-- Tax Global Management
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=2139
;

-- Mar 3, 2008 10:49:11 PM CST
-- Tax Global Management
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=9620
;

-- Mar 3, 2008 10:49:11 PM CST
-- Tax Global Management
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=2148
;

-- Mar 3, 2008 10:49:11 PM CST
-- Tax Global Management
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=2128
;

-- Mar 3, 2008 10:49:11 PM CST
-- Tax Global Management
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=2127
;

-- Mar 3, 2008 10:49:11 PM CST
-- Tax Global Management
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=2146
;

-- Mar 3, 2008 10:49:11 PM CST
-- Tax Global Management
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=2154
;

-- Mar 3, 2008 10:49:11 PM CST
-- Tax Global Management
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=2153
;

-- Mar 3, 2008 10:49:11 PM CST
-- Tax Global Management
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=2135
;

-- Mar 3, 2008 10:50:03 PM CST
-- Tax Global Management
UPDATE AD_Element SET Name='Tax Group', PrintName='Tax Group',Updated=TO_TIMESTAMP('2008-03-03 22:50:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53356
;

-- Mar 3, 2008 10:50:03 PM CST
-- Tax Global Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53356
;

-- Mar 3, 2008 10:50:03 PM CST
-- Tax Global Management
UPDATE AD_Column SET ColumnName='C_TaxGroup_ID', Name='Tax Group', Description=NULL, Help=NULL WHERE AD_Element_ID=53356
;

-- Mar 3, 2008 10:50:03 PM CST
-- Tax Global Management
UPDATE AD_Field SET Name='Tax Group', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53356) AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:50:04 PM CST
-- Tax Global Management
UPDATE AD_Process_Para SET ColumnName='C_TaxGroup_ID', Name='Tax Group', Description=NULL, Help=NULL, AD_Element_ID=53356 WHERE UPPER(ColumnName)='C_TAXGROUP_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Mar 3, 2008 10:50:04 PM CST
-- Tax Global Management
UPDATE AD_Process_Para SET ColumnName='C_TaxGroup_ID', Name='Tax Group', Description=NULL, Help=NULL WHERE AD_Element_ID=53356 AND IsCentrallyMaintained='Y'
;

-- Mar 3, 2008 10:50:04 PM CST
-- Tax Global Management
UPDATE AD_PrintFormatItem SET PrintName='Tax Group', Name='Tax Group' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53356)
;

-- Mar 3, 2008 10:50:04 PM CST
-- Tax Global Management
UPDATE AD_PrintFormatItem SET PrintName='Tax Group', Name='Tax Group' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53356)
;

