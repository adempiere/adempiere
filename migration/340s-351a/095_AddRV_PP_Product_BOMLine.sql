SET DEFINE OFF
SET SQLBLANKLINES ON
-- Feb 12, 2008 1:31:46 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53063,'7',TO_DATE('2008-02-12 13:31:45','YYYY-MM-DD HH24:MI:SS'),0,'EE01','N','Y','N','N','N','N','Y','Product BOM Line VIEW','L','RV_PP_Product_BOMLine',TO_DATE('2008-02-12 13:31:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 12, 2008 1:31:46 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53063 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Feb 12, 2008 1:31:47 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53080,TO_DATE('2008-02-12 13:31:46','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table RV_PP_Product_BOMLine',1,'Y','N','Y','Y','RV_PP_Product_BOMLine','N',1000000,TO_DATE('2008-02-12 13:31:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 12, 2008 1:31:47 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference SET Description='Direct Table Access', EntityType='D', Help=NULL, IsActive='Y', Name='Table Direct', ValidationType='D',Updated=TO_DATE('2008-02-12 13:31:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=19
;

-- Feb 12, 2008 1:31:47 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=19
;

-- Feb 12, 2008 1:31:48 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54322,102,0,19,53063,'AD_Client_ID',TO_DATE('2008-02-12 13:31:47','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','EE01',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','Y','N','Y','Client',TO_DATE('2008-02-12 13:31:47','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:31:48 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54322 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:31:49 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54323,113,0,19,53063,104,'AD_Org_ID',TO_DATE('2008-02-12 13:31:48','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE01',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','Y','N','Y','Organization',TO_DATE('2008-02-12 13:31:48','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:31:49 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54323 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:31:50 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54324,114,0,19,53063,'AD_PInstance_ID',TO_DATE('2008-02-12 13:31:49','YYYY-MM-DD HH24:MI:SS'),0,'Instance of the process','EE01',22,'Y','N','N','N','N','N','N','N','Y','N','Y','Process Instance',TO_DATE('2008-02-12 13:31:49','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:31:50 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54324 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:31:51 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54325,215,0,19,53063,'C_UOM_ID',TO_DATE('2008-02-12 13:31:50','YYYY-MM-DD HH24:MI:SS'),0,'Unit of Measure','EE01',22,'The UOM defines a unique non monetary Unit of Measure','Y','N','N','N','N','N','N','N','Y','N','Y','UOM',TO_DATE('2008-02-12 13:31:50','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:31:51 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54325 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:31:51 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference SET Description='Reference List', EntityType='D', Help=NULL, IsActive='Y', Name='List', ValidationType='D',Updated=TO_DATE('2008-02-12 13:31:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=17
;

-- Feb 12, 2008 1:31:51 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=17
;

-- Feb 12, 2008 1:31:51 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference SET Description=NULL, EntityType='EE01', Help=NULL, IsActive='Y', Name='PP_ComponentType', ValidationType='L',Updated=TO_DATE('2008-02-12 13:31:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=53225
;

-- Feb 12, 2008 1:31:51 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53225
;

-- Feb 12, 2008 1:31:52 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_List SET AD_Reference_ID=53225, Description=NULL, EntityType='EE01', IsActive='Y', Name='By Product', Value='BY',Updated=TO_DATE('2008-02-12 13:31:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53257
;

-- Feb 12, 2008 1:31:52 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53257
;

-- Feb 12, 2008 1:31:52 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_List SET AD_Reference_ID=53225, Description=NULL, EntityType='EE01', IsActive='Y', Name='Component', Value='CO',Updated=TO_DATE('2008-02-12 13:31:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53258
;

-- Feb 12, 2008 1:31:52 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53258
;

-- Feb 12, 2008 1:31:52 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_List SET AD_Reference_ID=53225, Description=NULL, EntityType='EE01', IsActive='Y', Name='Phantom', Value='PH',Updated=TO_DATE('2008-02-12 13:31:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53259
;

-- Feb 12, 2008 1:31:52 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53259
;

-- Feb 12, 2008 1:31:52 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_List SET AD_Reference_ID=53225, Description=NULL, EntityType='EE01', IsActive='Y', Name='Packing', Value='PK',Updated=TO_DATE('2008-02-12 13:31:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53260
;

-- Feb 12, 2008 1:31:52 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53260
;

-- Feb 12, 2008 1:31:53 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_List SET AD_Reference_ID=53225, Description=NULL, EntityType='EE01', IsActive='Y', Name='Planning', Value='PL',Updated=TO_DATE('2008-02-12 13:31:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53261
;

-- Feb 12, 2008 1:31:53 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53261
;

-- Feb 12, 2008 1:31:53 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_List SET AD_Reference_ID=53225, Description=NULL, EntityType='EE01', IsActive='Y', Name='Tools', Value='TL',Updated=TO_DATE('2008-02-12 13:31:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53262
;

-- Feb 12, 2008 1:31:53 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53262
;

-- Feb 12, 2008 1:31:53 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_List SET AD_Reference_ID=53225, Description=NULL, EntityType='EE01', IsActive='Y', Name='Option', Value='OP',Updated=TO_DATE('2008-02-12 13:31:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53263
;

-- Feb 12, 2008 1:31:53 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53263
;

-- Feb 12, 2008 1:31:53 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_List SET AD_Reference_ID=53225, Description=NULL, EntityType='EE01', IsActive='Y', Name='Variant', Value='VA',Updated=TO_DATE('2008-02-12 13:31:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53264
;

-- Feb 12, 2008 1:31:53 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53264
;

-- Feb 12, 2008 1:31:58 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54326,53249,0,17,53225,53063,'ComponentType',TO_DATE('2008-02-12 13:31:53','YYYY-MM-DD HH24:MI:SS'),0,'CO','EE01',2,'Y','N','N','N','N','N','N','N','Y','N','Y','ComponentType',TO_DATE('2008-02-12 13:31:53','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:31:58 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54326 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:31:58 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference SET Description='Date with time', EntityType='D', Help=NULL, IsActive='Y', Name='Date+Time', ValidationType='D',Updated=TO_DATE('2008-02-12 13:31:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=16
;

-- Feb 12, 2008 1:31:58 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=16
;

-- Feb 12, 2008 1:31:59 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54327,245,0,16,53063,'Created',TO_DATE('2008-02-12 13:31:58','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE01',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','N','N','N','Y','N','Y','Created',TO_DATE('2008-02-12 13:31:58','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:31:59 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54327 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:32:00 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference SET Description='Table List', EntityType='D', Help=NULL, IsActive='Y', Name='Table', ValidationType='D',Updated=TO_DATE('2008-02-12 13:32:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=18
;

-- Feb 12, 2008 1:32:00 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=18
;

-- Feb 12, 2008 1:32:00 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference SET Description='User selection', EntityType='D', Help=NULL, IsActive='Y', Name='AD_User', ValidationType='T',Updated=TO_DATE('2008-02-12 13:32:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=110
;

-- Feb 12, 2008 1:32:00 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=110
;

-- Feb 12, 2008 1:32:00 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_Table SET AD_Table_ID = 114, AD_Display = 213, AD_Key = 212, isValueDisplayed = 'N', OrderByClause = 'AD_User.Name', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 110
;

-- Feb 12, 2008 1:32:01 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54328,246,0,18,110,53063,'CreatedBy',TO_DATE('2008-02-12 13:32:00','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE01',10,'The Created By field indicates the user who created this record.','Y','N','N','N','N','N','N','N','Y','N','Y','Created By',TO_DATE('2008-02-12 13:32:00','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:32:01 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54328 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:32:01 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference SET Description='Character String up to 2000 characters', EntityType='D', Help=NULL, IsActive='Y', Name='Text', ValidationType='D',Updated=TO_DATE('2008-02-12 13:32:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=14
;

-- Feb 12, 2008 1:32:01 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=14
;

-- Feb 12, 2008 1:32:02 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54329,275,0,14,53063,'Description',TO_DATE('2008-02-12 13:32:01','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE01',510,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','N','Y','Description',TO_DATE('2008-02-12 13:32:01','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:32:02 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54329 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:32:02 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference SET Description='CheckBox', EntityType='D', Help=NULL, IsActive='Y', Name='Yes-No', ValidationType='D',Updated=TO_DATE('2008-02-12 13:32:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=20
;

-- Feb 12, 2008 1:32:02 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=20
;

-- Feb 12, 2008 1:32:03 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54330,348,0,20,53063,'IsActive',TO_DATE('2008-02-12 13:32:02','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system','EE01',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','N','N','N','Y','N','Y','Active',TO_DATE('2008-02-12 13:32:02','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:32:03 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54330 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:32:04 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54331,53251,0,20,53063,'IsCritical',TO_DATE('2008-02-12 13:32:03','YYYY-MM-DD HH24:MI:SS'),0,'EE01',1,'Y','N','N','N','N','N','N','N','Y','N','Y','IsCritical',TO_DATE('2008-02-12 13:32:03','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:32:04 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54331 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:32:04 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54332,53252,0,20,53063,'IsQtyPercentage',TO_DATE('2008-02-12 13:32:04','YYYY-MM-DD HH24:MI:SS'),0,'EE01',1,'Y','N','N','N','N','N','N','N','Y','N','Y','IsQtyPercentage',TO_DATE('2008-02-12 13:32:04','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:32:04 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54332 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:32:04 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference SET Description='Issue Method', EntityType='EE01', Help=NULL, IsActive='Y', Name='PP_Product_BOM IssueMethod', ValidationType='L',Updated=TO_DATE('2008-02-12 13:32:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=53226
;

-- Feb 12, 2008 1:32:05 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53226
;

-- Feb 12, 2008 1:32:06 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54333,53253,0,17,53226,53063,'IssueMethod',TO_DATE('2008-02-12 13:32:05','YYYY-MM-DD HH24:MI:SS'),0,'EE01',1,'Y','N','N','N','N','N','N','N','Y','N','Y','IssueMethod',TO_DATE('2008-02-12 13:32:05','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:32:06 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54333 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:32:06 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference SET Description='10 Digit numeric', EntityType='D', Help=NULL, IsActive='Y', Name='Integer', ValidationType='D',Updated=TO_DATE('2008-02-12 13:32:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=11
;

-- Feb 12, 2008 1:32:06 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=11
;

-- Feb 12, 2008 1:32:07 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54334,1982,0,11,53063,'LevelNo',TO_DATE('2008-02-12 13:32:06','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','N','N','N','N','N','N','N','Y','N','Y','Level no',TO_DATE('2008-02-12 13:32:06','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:32:07 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54334 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:32:07 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference SET Description='Character String', EntityType='D', Help=NULL, IsActive='Y', Name='String', ValidationType='D',Updated=TO_DATE('2008-02-12 13:32:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=10
;

-- Feb 12, 2008 1:32:07 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=10
;

-- Feb 12, 2008 1:32:08 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54335,53318,0,10,53063,'Levels',TO_DATE('2008-02-12 13:32:07','YYYY-MM-DD HH24:MI:SS'),0,'EE01',250,'Y','N','N','N','N','N','N','N','Y','N','Y','Levels',TO_DATE('2008-02-12 13:32:07','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:32:08 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54335 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:32:09 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54336,439,0,11,53063,'Line',TO_DATE('2008-02-12 13:32:08','YYYY-MM-DD HH24:MI:SS'),0,'Unique line for this document','EE01',22,'Indicates the unique line for a document.  It will also control the display order of the lines within a document.','Y','N','N','N','N','Y','N','N','Y','N','Y','Line No',TO_DATE('2008-02-12 13:32:08','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:32:09 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54336 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:32:09 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference SET Description='Product Attribute', EntityType='D', Help=NULL, IsActive='Y', Name='Product Attribute', ValidationType='D',Updated=TO_DATE('2008-02-12 13:32:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=35
;

-- Feb 12, 2008 1:32:09 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=35
;

-- Feb 12, 2008 1:32:10 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54337,2019,0,35,53063,'M_AttributeSetInstance_ID',TO_DATE('2008-02-12 13:32:09','YYYY-MM-DD HH24:MI:SS'),0,'Product Attribute Set Instance','EE01',22,'The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','N','N','N','N','N','N','N','Y','N','Y','Attribute Set Instance',TO_DATE('2008-02-12 13:32:09','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:32:10 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54337 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:32:10 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference SET Description='Search Field', EntityType='D', Help=NULL, IsActive='Y', Name='Search', ValidationType='D',Updated=TO_DATE('2008-02-12 13:32:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=30
;

-- Feb 12, 2008 1:32:10 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=30
;

-- Feb 12, 2008 1:32:11 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54338,454,0,30,53063,'M_Product_ID',TO_DATE('2008-02-12 13:32:10','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item','EE01',22,'Identifies an item which is either purchased or sold in this organization.','Y','N','N','N','N','N','N','N','Y','N','Y','Product',TO_DATE('2008-02-12 13:32:10','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:32:11 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54338 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:32:11 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54339,53254,0,19,53063,'PP_Product_BOMLine_ID',TO_DATE('2008-02-12 13:32:11','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','Y','N','Y','PP_Product_BOMLine_ID',TO_DATE('2008-02-12 13:32:11','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:32:11 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54339 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:32:12 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54340,53245,0,19,53063,'PP_Product_BOM_ID',TO_DATE('2008-02-12 13:32:11','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','N','N','N','N','N','N','N','Y','N','Y','BOM & Formaula',TO_DATE('2008-02-12 13:32:11','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:32:12 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54340 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:32:12 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference SET Description='Quantity data type', EntityType='D', Help=NULL, IsActive='Y', Name='Quantity', ValidationType='D',Updated=TO_DATE('2008-02-12 13:32:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=29
;

-- Feb 12, 2008 1:32:12 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=29
;

-- Feb 12, 2008 1:32:14 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54341,53255,0,29,53063,'QtyBOM',TO_DATE('2008-02-12 13:32:12','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','N','N','N','N','N','N','N','Y','N','Y','QtyBOM',TO_DATE('2008-02-12 13:32:12','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:32:14 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54341 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:32:15 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54342,53256,0,29,53063,'QtyBatch',TO_DATE('2008-02-12 13:32:14','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','N','N','N','N','Y','N','N','Y','N','Y','QtyBatch',TO_DATE('2008-02-12 13:32:14','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:32:15 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54342 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:32:16 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54343,53257,0,29,53063,'Scrap',TO_DATE('2008-02-12 13:32:15','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','N','N','N','N','N','N','N','Y','N','Y','Scrap',TO_DATE('2008-02-12 13:32:15','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:32:16 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54343 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:32:17 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54344,566,0,11,53063,'SeqNo',TO_DATE('2008-02-12 13:32:16','YYYY-MM-DD HH24:MI:SS'),0,'Method of ordering records; lowest number comes first','EE01',22,'The Sequence indicates the order of records','Y','N','N','N','N','N','N','N','Y','N','Y','Sequence',TO_DATE('2008-02-12 13:32:16','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:32:17 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54344 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:32:17 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54345,607,0,16,53063,'Updated',TO_DATE('2008-02-12 13:32:17','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE01',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','N','N','N','Y','N','Y','Updated',TO_DATE('2008-02-12 13:32:17','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:32:17 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54345 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:32:18 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54346,608,0,18,110,53063,'UpdatedBy',TO_DATE('2008-02-12 13:32:17','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE01',10,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','N','N','N','Y','N','Y','Updated By',TO_DATE('2008-02-12 13:32:17','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:32:18 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54346 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:32:19 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54347,617,0,16,53063,'ValidFrom',TO_DATE('2008-02-12 13:32:18','YYYY-MM-DD HH24:MI:SS'),0,'Valid from including this date (first day)','EE01',7,'The Valid From date indicates the first day of a date range','Y','N','N','N','N','N','N','N','Y','N','Y','Valid from',TO_DATE('2008-02-12 13:32:18','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:32:19 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54347 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:32:20 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,54348,618,0,16,53063,'ValidTo',TO_DATE('2008-02-12 13:32:19','YYYY-MM-DD HH24:MI:SS'),0,'Valid to including this date (last day)','EE01',7,'The Valid To date indicates the last day of a date range','Y','N','N','N','N','N','N','N','Y','N','Y','Valid to',TO_DATE('2008-02-12 13:32:19','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2008 1:32:20 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54348 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 12, 2008 1:45:29 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_ReportView (AD_Client_ID,AD_Org_ID,AD_ReportView_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,0,53007,53063,TO_DATE('2008-02-12 13:45:25','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','RV_PP_Product_BOMLine',TO_DATE('2008-02-12 13:45:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 12, 2008 1:45:30 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Table SET AD_Window_ID=NULL, AccessLevel='7', Description=NULL, EntityType='EE01', Help=NULL, ImportTable='N', IsActive='Y', IsChangeLog='N', IsDeleteable='N', IsHighVolume='N', IsSecurityEnabled='N', IsView='Y', Name='Product BOM Line VIEW', ReplicationType='L', TableName='RV_PP_Product_BOMLine',Updated=TO_DATE('2008-02-12 13:45:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=53063
;

-- Feb 12, 2008 1:45:30 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference SET Description='Direct Table Access', EntityType='D', Help=NULL, IsActive='Y', Name='Table Direct', ValidationType='D',Updated=TO_DATE('2008-02-12 13:45:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=19
;

-- Feb 12, 2008 1:45:30 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=19
;

-- Feb 12, 2008 1:45:31 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Column SET AD_Element_ID=102, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53063, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_Client_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Client/Tenant for this installation.', EntityType='EE01', FieldLength=22, Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Client', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2008-02-12 13:45:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54322
;

-- Feb 12, 2008 1:45:31 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Field SET Name='Client', Description='Client/Tenant for this installation.', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.' WHERE AD_Column_ID=54322 AND IsCentrallyMaintained='Y'
;

-- Feb 12, 2008 1:45:31 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Column SET AD_Element_ID=113, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53063, AD_Val_Rule_ID=104, Callout=NULL, ColumnName='AD_Org_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Organizational entity within client', EntityType='EE01', FieldLength=22, Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Organization', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2008-02-12 13:45:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54323
;

-- Feb 12, 2008 1:45:31 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Field SET Name='Organization', Description='Organizational entity within client', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.' WHERE AD_Column_ID=54323 AND IsCentrallyMaintained='Y'
;

-- Feb 12, 2008 1:45:32 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Column SET AD_Element_ID=114, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53063, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_PInstance_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Instance of the process', EntityType='EE01', FieldLength=22, Help=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Process Instance', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2008-02-12 13:45:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54324
;

-- Feb 12, 2008 1:45:32 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Field SET Name='Process Instance', Description='Instance of the process', Help=NULL WHERE AD_Column_ID=54324 AND IsCentrallyMaintained='Y'
;

-- Feb 12, 2008 1:45:32 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Column SET AD_Element_ID=215, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53063, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='C_UOM_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Unit of Measure', EntityType='EE01', FieldLength=22, Help='The UOM defines a unique non monetary Unit of Measure', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='UOM', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2008-02-12 13:45:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54325
;

-- Feb 12, 2008 1:45:32 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Field SET Name='UOM', Description='Unit of Measure', Help='The UOM defines a unique non monetary Unit of Measure' WHERE AD_Column_ID=54325 AND IsCentrallyMaintained='Y'
;

-- Feb 12, 2008 1:45:33 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference SET Description='Reference List', EntityType='D', Help=NULL, IsActive='Y', Name='List', ValidationType='D',Updated=TO_DATE('2008-02-12 13:45:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=17
;

-- Feb 12, 2008 1:45:33 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=17
;

-- Feb 12, 2008 1:45:33 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference SET Description=NULL, EntityType='EE01', Help=NULL, IsActive='Y', Name='PP_ComponentType', ValidationType='L',Updated=TO_DATE('2008-02-12 13:45:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=53225
;

-- Feb 12, 2008 1:45:33 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53225
;

-- Feb 12, 2008 1:45:33 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_List SET AD_Reference_ID=53225, Description=NULL, EntityType='EE01', IsActive='Y', Name='By Product', Value='BY',Updated=TO_DATE('2008-02-12 13:45:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53257
;

-- Feb 12, 2008 1:45:33 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53257
;

-- Feb 12, 2008 1:45:33 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_List SET AD_Reference_ID=53225, Description=NULL, EntityType='EE01', IsActive='Y', Name='Component', Value='CO',Updated=TO_DATE('2008-02-12 13:45:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53258
;

-- Feb 12, 2008 1:45:33 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53258
;

-- Feb 12, 2008 1:45:33 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_List SET AD_Reference_ID=53225, Description=NULL, EntityType='EE01', IsActive='Y', Name='Phantom', Value='PH',Updated=TO_DATE('2008-02-12 13:45:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53259
;

-- Feb 12, 2008 1:45:33 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53259
;

-- Feb 12, 2008 1:45:33 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_List SET AD_Reference_ID=53225, Description=NULL, EntityType='EE01', IsActive='Y', Name='Packing', Value='PK',Updated=TO_DATE('2008-02-12 13:45:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53260
;

-- Feb 12, 2008 1:45:33 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53260
;

-- Feb 12, 2008 1:45:34 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_List SET AD_Reference_ID=53225, Description=NULL, EntityType='EE01', IsActive='Y', Name='Planning', Value='PL',Updated=TO_DATE('2008-02-12 13:45:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53261
;

-- Feb 12, 2008 1:45:34 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53261
;

-- Feb 12, 2008 1:45:34 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_List SET AD_Reference_ID=53225, Description=NULL, EntityType='EE01', IsActive='Y', Name='Tools', Value='TL',Updated=TO_DATE('2008-02-12 13:45:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53262
;

-- Feb 12, 2008 1:45:34 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53262
;

-- Feb 12, 2008 1:45:34 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_List SET AD_Reference_ID=53225, Description=NULL, EntityType='EE01', IsActive='Y', Name='Option', Value='OP',Updated=TO_DATE('2008-02-12 13:45:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53263
;

-- Feb 12, 2008 1:45:34 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53263
;

-- Feb 12, 2008 1:45:34 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_List SET AD_Reference_ID=53225, Description=NULL, EntityType='EE01', IsActive='Y', Name='Variant', Value='VA',Updated=TO_DATE('2008-02-12 13:45:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=53264
;

-- Feb 12, 2008 1:45:34 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53264
;

-- Feb 12, 2008 1:45:35 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Column SET AD_Element_ID=53249, AD_Process_ID=NULL, AD_Reference_ID=17, AD_Reference_Value_ID=53225, AD_Table_ID=53063, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='ComponentType', ColumnSQL=NULL, DefaultValue='CO', Description='Define of Componet Type (Componet, Tools, Documentation, By-Product, Variant, Option, etc)', EntityType='EE01', FieldLength=2, Help='Component: identify a raw material, ingredient, part, or subassembly that goes into a higher level assembly, compound or other item. Byproduct: This entity is a non scheduled product gotten as a consequence of another production process. It has a sales value but it is minimum.

Phantom: indicates the product is a fictitious assembly, that is to say, a set of components that are grouped only to make easier the analysis in a separated way from the rest of the BOM. When the MRP generates a requirement of the phantom and the projected on hand is not available, the process goes to the lower level and start a new MRP cycle but does not create Planned Orders for the phantom product.

Packing: This product will not be taken into account to calculate the total quantity of components when the IsQtyPercentage check box is ticked.

Planning: The parent product will be used for the planning process of the different options of similar products. (e.g. 30 % bread with fiber and 70 % bread without fiber)

Tools:The product is a tool which is going to be used in a production operation.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Component Type', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2008-02-12 13:45:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54326
;

-- Feb 12, 2008 1:45:35 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=54326
;

-- Feb 12, 2008 1:45:35 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Field SET Name='Component Type', Description='Define of Componet Type (Componet, Tools, Documentation, By-Product, Variant, Option, etc)', Help='Component: identify a raw material, ingredient, part, or subassembly that goes into a higher level assembly, compound or other item. Byproduct: This entity is a non scheduled product gotten as a consequence of another production process. It has a sales value but it is minimum.

Phantom: indicates the product is a fictitious assembly, that is to say, a set of components that are grouped only to make easier the analysis in a separated way from the rest of the BOM. When the MRP generates a requirement of the phantom and the projected on hand is not available, the process goes to the lower level and start a new MRP cycle but does not create Planned Orders for the phantom product.

Packing: This product will not be taken into account to calculate the total quantity of components when the IsQtyPercentage check box is ticked.

Planning: The parent product will be used for the planning process of the different options of similar products. (e.g. 30 % bread with fiber and 70 % bread without fiber)

Tools:The product is a tool which is going to be used in a production operation.' WHERE AD_Column_ID=54326 AND IsCentrallyMaintained='Y'
;

-- Feb 12, 2008 1:45:35 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference SET Description='Date with time', EntityType='D', Help=NULL, IsActive='Y', Name='Date+Time', ValidationType='D',Updated=TO_DATE('2008-02-12 13:45:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=16
;

-- Feb 12, 2008 1:45:35 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=16
;

-- Feb 12, 2008 1:45:35 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Column SET AD_Element_ID=245, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=53063, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Created', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was created', EntityType='EE01', FieldLength=7, Help='The Created field indicates the date that this record was created.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Created', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2008-02-12 13:45:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54327
;

-- Feb 12, 2008 1:45:35 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Field SET Name='Created', Description='Date this record was created', Help='The Created field indicates the date that this record was created.' WHERE AD_Column_ID=54327 AND IsCentrallyMaintained='Y'
;

-- Feb 12, 2008 1:45:35 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference SET Description='Table List', EntityType='D', Help=NULL, IsActive='Y', Name='Table', ValidationType='D',Updated=TO_DATE('2008-02-12 13:45:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=18
;

-- Feb 12, 2008 1:45:35 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=18
;

-- Feb 12, 2008 1:45:35 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference SET Description='User selection', EntityType='D', Help=NULL, IsActive='Y', Name='AD_User', ValidationType='T',Updated=TO_DATE('2008-02-12 13:45:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=110
;

-- Feb 12, 2008 1:45:35 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=110
;

-- Feb 12, 2008 1:45:36 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_Table SET AD_Table_ID = 114, AD_Display = 213, AD_Key = 212, isValueDisplayed = 'N', OrderByClause = 'AD_User.Name', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 110
;

-- Feb 12, 2008 1:45:36 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Column SET AD_Element_ID=246, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=53063, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='CreatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who created this records', EntityType='EE01', FieldLength=10, Help='The Created By field indicates the user who created this record.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Created By', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2008-02-12 13:45:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54328
;

-- Feb 12, 2008 1:45:36 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Field SET Name='Created By', Description='User who created this records', Help='The Created By field indicates the user who created this record.' WHERE AD_Column_ID=54328 AND IsCentrallyMaintained='Y'
;

-- Feb 12, 2008 1:45:36 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference SET Description='Character String up to 2000 characters', EntityType='D', Help=NULL, IsActive='Y', Name='Text', ValidationType='D',Updated=TO_DATE('2008-02-12 13:45:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=14
;

-- Feb 12, 2008 1:45:36 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=14
;

-- Feb 12, 2008 1:45:37 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Column SET AD_Element_ID=275, AD_Process_ID=NULL, AD_Reference_ID=14, AD_Reference_Value_ID=NULL, AD_Table_ID=53063, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Description', ColumnSQL=NULL, DefaultValue=NULL, Description='Optional short description of the record', EntityType='EE01', FieldLength=510, Help='A description is limited to 255 characters.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Description', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2008-02-12 13:45:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54329
;

-- Feb 12, 2008 1:45:37 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Field SET Name='Description', Description='Optional short description of the record', Help='A description is limited to 255 characters.' WHERE AD_Column_ID=54329 AND IsCentrallyMaintained='Y'
;

-- Feb 12, 2008 1:45:37 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference SET Description='CheckBox', EntityType='D', Help=NULL, IsActive='Y', Name='Yes-No', ValidationType='D',Updated=TO_DATE('2008-02-12 13:45:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=20
;

-- Feb 12, 2008 1:45:37 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=20
;

-- Feb 12, 2008 1:45:37 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Column SET AD_Element_ID=348, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=53063, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsActive', ColumnSQL=NULL, DefaultValue=NULL, Description='The record is active in the system', EntityType='EE01', FieldLength=1, Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Active', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2008-02-12 13:45:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54330
;

-- Feb 12, 2008 1:45:37 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Field SET Name='Active', Description='The record is active in the system', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.' WHERE AD_Column_ID=54330 AND IsCentrallyMaintained='Y'
;

-- Feb 12, 2008 1:45:38 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Column SET AD_Element_ID=53251, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=53063, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsCritical', ColumnSQL=NULL, DefaultValue=NULL, Description='Indicates that a Manufacturing Order will not be released to the shop floor if this component is not on hand.', EntityType='EE01', FieldLength=1, Help='Indicates that a Manufacturing Order will not be released to the shop floor if this component is not on hand.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Is Critical', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2008-02-12 13:45:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54331
;

-- Feb 12, 2008 1:45:38 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=54331
;

-- Feb 12, 2008 1:45:38 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Field SET Name='Is Critical', Description='Indicates that a Manufacturing Order will not be released to the shop floor if this component is not on hand.', Help='Indicates that a Manufacturing Order will not be released to the shop floor if this component is not on hand.' WHERE AD_Column_ID=54331 AND IsCentrallyMaintained='Y'
;

-- Feb 12, 2008 1:45:38 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Column SET AD_Element_ID=53252, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=53063, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsQtyPercentage', ColumnSQL=NULL, DefaultValue=NULL, Description='Yes to Formula and No to BOM', EntityType='EE01', FieldLength=1, Help='When is ''Yes'' means you need to introduce the quantity of the componenet as a percentage of all the components.

When is ''No'' means you need to introduce the quantity of the component to produce a unit of measure of the parent product.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Qty is Percentage', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2008-02-12 13:45:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54332
;

-- Feb 12, 2008 1:45:38 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=54332
;

-- Feb 12, 2008 1:45:38 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Field SET Name='Qty is Percentage', Description='Yes to Formula and No to BOM', Help='When is ''Yes'' means you need to introduce the quantity of the componenet as a percentage of all the components.

When is ''No'' means you need to introduce the quantity of the component to produce a unit of measure of the parent product.' WHERE AD_Column_ID=54332 AND IsCentrallyMaintained='Y'
;

-- Feb 12, 2008 1:45:38 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference SET Description='Issue Method', EntityType='EE01', Help=NULL, IsActive='Y', Name='PP_Product_BOM IssueMethod', ValidationType='L',Updated=TO_DATE('2008-02-12 13:45:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=53226
;

-- Feb 12, 2008 1:45:38 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53226
;

-- Feb 12, 2008 1:45:39 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Column SET AD_Element_ID=53253, AD_Process_ID=NULL, AD_Reference_ID=17, AD_Reference_Value_ID=53226, AD_Table_ID=53063, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IssueMethod', ColumnSQL=NULL, DefaultValue=NULL, Description='Indicate if the components is issue manuality or using back-flush', EntityType='EE01', FieldLength=1, Help='Issue Method field permit you to select between Issue or Backflush, if you choose issue you need two steps, first you issue the components from the warehouse and after production you need a second warehouse movement of receiving the finished product into the warehouse.

If you choose backflush in one single movement you receive the finished product in the warehouse and automatically you issue the components required from the warehouse.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Issue Method', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2008-02-12 13:45:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54333
;

-- Feb 12, 2008 1:45:39 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=54333
;

-- Feb 12, 2008 1:45:39 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Field SET Name='Issue Method', Description='Indicate if the components is issue manuality or using back-flush', Help='Issue Method field permit you to select between Issue or Backflush, if you choose issue you need two steps, first you issue the components from the warehouse and after production you need a second warehouse movement of receiving the finished product into the warehouse.

If you choose backflush in one single movement you receive the finished product in the warehouse and automatically you issue the components required from the warehouse.' WHERE AD_Column_ID=54333 AND IsCentrallyMaintained='Y'
;

-- Feb 12, 2008 1:45:39 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference SET Description='10 Digit numeric', EntityType='D', Help=NULL, IsActive='Y', Name='Integer', ValidationType='D',Updated=TO_DATE('2008-02-12 13:45:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=11
;

-- Feb 12, 2008 1:45:39 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=11
;

-- Feb 12, 2008 1:45:40 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Column SET AD_Element_ID=1982, AD_Process_ID=NULL, AD_Reference_ID=11, AD_Reference_Value_ID=NULL, AD_Table_ID=53063, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='LevelNo', ColumnSQL=NULL, DefaultValue=NULL, Description=NULL, EntityType='EE01', FieldLength=22, Help=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Level no', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2008-02-12 13:45:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54334
;

-- Feb 12, 2008 1:45:40 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Field SET Name='Level no', Description=NULL, Help=NULL WHERE AD_Column_ID=54334 AND IsCentrallyMaintained='Y'
;

-- Feb 12, 2008 1:45:40 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference SET Description='Character String', EntityType='D', Help=NULL, IsActive='Y', Name='String', ValidationType='D',Updated=TO_DATE('2008-02-12 13:45:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=10
;

-- Feb 12, 2008 1:45:40 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=10
;

-- Feb 12, 2008 1:45:40 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Column SET AD_Element_ID=53318, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=53063, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Levels', ColumnSQL=NULL, DefaultValue=NULL, Description=NULL, EntityType='EE01', FieldLength=250, Help=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Levels', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2008-02-12 13:45:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54335
;

-- Feb 12, 2008 1:45:40 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Field SET Name='Levels', Description=NULL, Help=NULL WHERE AD_Column_ID=54335 AND IsCentrallyMaintained='Y'
;

-- Feb 12, 2008 1:45:41 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Column SET AD_Element_ID=439, AD_Process_ID=NULL, AD_Reference_ID=11, AD_Reference_Value_ID=NULL, AD_Table_ID=53063, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Line', ColumnSQL=NULL, DefaultValue=NULL, Description='Unique line for this document', EntityType='EE01', FieldLength=22, Help='Indicates the unique line for a document.  It will also control the display order of the lines within a document.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Line No', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2008-02-12 13:45:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54336
;

-- Feb 12, 2008 1:45:41 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Field SET Name='Line No', Description='Unique line for this document', Help='Indicates the unique line for a document.  It will also control the display order of the lines within a document.' WHERE AD_Column_ID=54336 AND IsCentrallyMaintained='Y'
;

-- Feb 12, 2008 1:45:41 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference SET Description='Product Attribute', EntityType='D', Help=NULL, IsActive='Y', Name='Product Attribute', ValidationType='D',Updated=TO_DATE('2008-02-12 13:45:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=35
;

-- Feb 12, 2008 1:45:41 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=35
;

-- Feb 12, 2008 1:45:41 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Column SET AD_Element_ID=2019, AD_Process_ID=NULL, AD_Reference_ID=35, AD_Reference_Value_ID=NULL, AD_Table_ID=53063, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='M_AttributeSetInstance_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Product Attribute Set Instance', EntityType='EE01', FieldLength=22, Help='The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Attribute Set Instance', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2008-02-12 13:45:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54337
;

-- Feb 12, 2008 1:45:41 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Field SET Name='Attribute Set Instance', Description='Product Attribute Set Instance', Help='The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.' WHERE AD_Column_ID=54337 AND IsCentrallyMaintained='Y'
;

-- Feb 12, 2008 1:45:41 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference SET Description='Search Field', EntityType='D', Help=NULL, IsActive='Y', Name='Search', ValidationType='D',Updated=TO_DATE('2008-02-12 13:45:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=30
;

-- Feb 12, 2008 1:45:41 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=30
;

-- Feb 12, 2008 1:45:42 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Column SET AD_Element_ID=454, AD_Process_ID=NULL, AD_Reference_ID=30, AD_Reference_Value_ID=NULL, AD_Table_ID=53063, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='M_Product_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Product, Service, Item', EntityType='EE01', FieldLength=22, Help='Identifies an item which is either purchased or sold in this organization.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Product', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2008-02-12 13:45:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54338
;

-- Feb 12, 2008 1:45:42 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Field SET Name='Product', Description='Product, Service, Item', Help='Identifies an item which is either purchased or sold in this organization.' WHERE AD_Column_ID=54338 AND IsCentrallyMaintained='Y'
;

-- Feb 12, 2008 1:45:42 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Column SET AD_Element_ID=53254, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53063, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='PP_Product_BOMLine_ID', ColumnSQL=NULL, DefaultValue=NULL, Description=NULL, EntityType='EE01', FieldLength=10, Help=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='BOM Line', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2008-02-12 13:45:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54339
;

-- Feb 12, 2008 1:45:42 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=54339
;

-- Feb 12, 2008 1:45:42 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Field SET Name='BOM Line', Description=NULL, Help=NULL WHERE AD_Column_ID=54339 AND IsCentrallyMaintained='Y'
;

-- Feb 12, 2008 1:45:43 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Column SET AD_Element_ID=53245, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53063, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='PP_Product_BOM_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Define the Parent Product to this BOM & Formula', EntityType='EE01', FieldLength=10, Help='Define the Parent Product to this BOM & Formula', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='BOM & Formaula', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2008-02-12 13:45:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54340
;

-- Feb 12, 2008 1:45:43 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Field SET Name='BOM & Formaula', Description='Define the Parent Product to this BOM & Formula', Help='Define the Parent Product to this BOM & Formula' WHERE AD_Column_ID=54340 AND IsCentrallyMaintained='Y'
;

-- Feb 12, 2008 1:45:43 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference SET Description='Quantity data type', EntityType='D', Help=NULL, IsActive='Y', Name='Quantity', ValidationType='D',Updated=TO_DATE('2008-02-12 13:45:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=29
;

-- Feb 12, 2008 1:45:43 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=29
;

-- Feb 12, 2008 1:45:43 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Column SET AD_Element_ID=53255, AD_Process_ID=NULL, AD_Reference_ID=29, AD_Reference_Value_ID=NULL, AD_Table_ID=53063, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='QtyBOM', ColumnSQL=NULL, DefaultValue=NULL, Description=NULL, EntityType='EE01', FieldLength=22, Help=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Component Qty', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2008-02-12 13:45:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54341
;

-- Feb 12, 2008 1:45:44 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=54341
;

-- Feb 12, 2008 1:45:44 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Field SET Name='Component Qty', Description=NULL, Help=NULL WHERE AD_Column_ID=54341 AND IsCentrallyMaintained='Y'
;

-- Feb 12, 2008 1:45:44 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Column SET AD_Element_ID=53256, AD_Process_ID=NULL, AD_Reference_ID=29, AD_Reference_Value_ID=NULL, AD_Table_ID=53063, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='QtyBatch', ColumnSQL=NULL, DefaultValue=NULL, Description=NULL, EntityType='EE01', FieldLength=22, Help=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Qty Batch', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2008-02-12 13:45:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54342
;

-- Feb 12, 2008 1:45:44 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=54342
;

-- Feb 12, 2008 1:45:44 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Field SET Name='Qty Batch', Description=NULL, Help=NULL WHERE AD_Column_ID=54342 AND IsCentrallyMaintained='Y'
;

-- Feb 12, 2008 1:45:44 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Column SET AD_Element_ID=53257, AD_Process_ID=NULL, AD_Reference_ID=29, AD_Reference_Value_ID=NULL, AD_Table_ID=53063, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Scrap', ColumnSQL=NULL, DefaultValue=NULL, Description='The Scrap field is introduced as a component percentage factor that is expected not to be useful as a part of the parent production.', EntityType='EE01', FieldLength=22, Help='The Scrap field is introduced as a component percentage factor that is expected not to be useful as a part of the parent production.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Scrap', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2008-02-12 13:45:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54343
;

-- Feb 12, 2008 1:45:44 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Field SET Name='Scrap', Description='The Scrap field is introduced as a component percentage factor that is expected not to be useful as a part of the parent production.', Help='The Scrap field is introduced as a component percentage factor that is expected not to be useful as a part of the parent production.' WHERE AD_Column_ID=54343 AND IsCentrallyMaintained='Y'
;

-- Feb 12, 2008 1:45:45 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Column SET AD_Element_ID=566, AD_Process_ID=NULL, AD_Reference_ID=11, AD_Reference_Value_ID=NULL, AD_Table_ID=53063, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='SeqNo', ColumnSQL=NULL, DefaultValue=NULL, Description='Method of ordering records; lowest number comes first', EntityType='EE01', FieldLength=22, Help='The Sequence indicates the order of records', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Sequence', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2008-02-12 13:45:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54344
;

-- Feb 12, 2008 1:45:45 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Field SET Name='Sequence', Description='Method of ordering records; lowest number comes first', Help='The Sequence indicates the order of records' WHERE AD_Column_ID=54344 AND IsCentrallyMaintained='Y'
;

-- Feb 12, 2008 1:45:45 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Column SET AD_Element_ID=607, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=53063, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Updated', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was updated', EntityType='EE01', FieldLength=7, Help='The Updated field indicates the date that this record was updated.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Updated', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2008-02-12 13:45:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54345
;

-- Feb 12, 2008 1:45:45 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Field SET Name='Updated', Description='Date this record was updated', Help='The Updated field indicates the date that this record was updated.' WHERE AD_Column_ID=54345 AND IsCentrallyMaintained='Y'
;

-- Feb 12, 2008 1:45:46 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Column SET AD_Element_ID=608, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=53063, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='UpdatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who updated this records', EntityType='EE01', FieldLength=10, Help='The Updated By field indicates the user who updated this record.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Updated By', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2008-02-12 13:45:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54346
;

-- Feb 12, 2008 1:45:46 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Field SET Name='Updated By', Description='User who updated this records', Help='The Updated By field indicates the user who updated this record.' WHERE AD_Column_ID=54346 AND IsCentrallyMaintained='Y'
;

-- Feb 12, 2008 1:45:46 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Column SET AD_Element_ID=617, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=53063, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='ValidFrom', ColumnSQL=NULL, DefaultValue=NULL, Description='Valid from including this date (first day)', EntityType='EE01', FieldLength=7, Help='The Valid From date indicates the first day of a date range', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Valid from', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2008-02-12 13:45:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54347
;

-- Feb 12, 2008 1:45:46 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Field SET Name='Valid from', Description='Valid from including this date (first day)', Help='The Valid From date indicates the first day of a date range' WHERE AD_Column_ID=54347 AND IsCentrallyMaintained='Y'
;

-- Feb 12, 2008 1:45:46 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Column SET AD_Element_ID=618, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=53063, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='ValidTo', ColumnSQL=NULL, DefaultValue=NULL, Description='Valid to including this date (last day)', EntityType='EE01', FieldLength=7, Help='The Valid To date indicates the last day of a date range', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Valid to', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2008-02-12 13:45:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54348
;

-- Feb 12, 2008 1:45:46 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Field SET Name='Valid to', Description='Valid to including this date (last day)', Help='The Valid To date indicates the last day of a date range' WHERE AD_Column_ID=54348 AND IsCentrallyMaintained='Y'
;

-- Feb 12, 2008 1:45:48 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormat (AD_Client_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFont_ID,AD_PrintFormat_ID,AD_PrintPaper_ID,AD_PrintTableFormat_ID,AD_ReportView_ID,AD_Table_ID,CreateCopy,Created,CreatedBy,Description,FooterMargin,HeaderMargin,IsActive,IsDefault,IsForm,IsStandardHeaderFooter,IsTableBased,Name,Updated,UpdatedBy) VALUES (0,0,100,130,50013,100,100,53007,53063,'N',TO_DATE('2008-02-12 13:45:47','YYYY-MM-DD HH24:MI:SS'),0,'Multi Level BOM & Formula Detail',0,0,'Y','N','N','Y','Y','Multi Level BOM & Formula Detail',TO_DATE('2008-02-12 13:45:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 12, 2008 1:45:49 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,54340,0,50450,50013,0,0,TO_DATE('2008-02-12 13:45:48','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'BOM & Formaula','C','F','BOM & Formaula',0,0,'N',0,TO_DATE('2008-02-12 13:45:48','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Feb 12, 2008 1:45:49 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50450 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Feb 12, 2008 1:45:49 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,54328,0,50451,50013,0,0,TO_DATE('2008-02-12 13:45:49','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Created By','C','F','Created By',0,0,'N',0,TO_DATE('2008-02-12 13:45:49','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Feb 12, 2008 1:45:49 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50451 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Feb 12, 2008 1:45:50 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,54329,0,50452,50013,0,0,TO_DATE('2008-02-12 13:45:49','YYYY-MM-DD HH24:MI:SS'),0,'B','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Description','C','F','Description',0,0,'N',0,TO_DATE('2008-02-12 13:45:49','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Feb 12, 2008 1:45:50 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50452 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Feb 12, 2008 1:45:51 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,54323,0,50453,50013,0,0,TO_DATE('2008-02-12 13:45:50','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Organization','C','F','Organization',0,0,'N',0,TO_DATE('2008-02-12 13:45:50','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Feb 12, 2008 1:45:51 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50453 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Feb 12, 2008 1:45:52 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,54334,0,50454,50013,0,0,TO_DATE('2008-02-12 13:45:51','YYYY-MM-DD HH24:MI:SS'),0,'T','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Level no','C','F','Level no',0,0,'N',0,TO_DATE('2008-02-12 13:45:51','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Feb 12, 2008 1:45:52 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50454 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Feb 12, 2008 1:45:53 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,54337,0,50455,50013,0,0,TO_DATE('2008-02-12 13:45:52','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Attribute Set Instance','C','F','Attribute Set Instance',0,0,'N',0,TO_DATE('2008-02-12 13:45:52','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Feb 12, 2008 1:45:53 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50455 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Feb 12, 2008 1:45:54 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,54322,0,50456,50013,0,0,TO_DATE('2008-02-12 13:45:53','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Client','C','F','Client',0,0,'N',0,TO_DATE('2008-02-12 13:45:53','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Feb 12, 2008 1:45:54 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50456 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Feb 12, 2008 1:45:55 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,54348,0,50457,50013,0,0,TO_DATE('2008-02-12 13:45:54','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Valid to','C','F','Valid to',0,0,'N',0,TO_DATE('2008-02-12 13:45:54','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Feb 12, 2008 1:45:55 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50457 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Feb 12, 2008 1:45:56 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,54336,0,50458,50013,0,0,TO_DATE('2008-02-12 13:45:55','YYYY-MM-DD HH24:MI:SS'),0,'T','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Line No','C','F','Line No',0,0,'N',0,TO_DATE('2008-02-12 13:45:55','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Feb 12, 2008 1:45:56 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50458 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Feb 12, 2008 1:45:57 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,54339,0,50459,50013,0,0,TO_DATE('2008-02-12 13:45:56','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'BOM Line','C','F','BOM Line',0,0,'N',0,TO_DATE('2008-02-12 13:45:56','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Feb 12, 2008 1:45:57 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50459 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Feb 12, 2008 1:45:58 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,54326,0,50460,50013,0,0,TO_DATE('2008-02-12 13:45:57','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Component Type','C','F','Component Type',0,0,'N',0,TO_DATE('2008-02-12 13:45:57','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Feb 12, 2008 1:45:58 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50460 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Feb 12, 2008 1:45:59 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,54331,0,50461,50013,0,0,TO_DATE('2008-02-12 13:45:58','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Is Critical','C','F','Is Critical',0,0,'N',0,TO_DATE('2008-02-12 13:45:58','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Feb 12, 2008 1:45:59 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50461 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Feb 12, 2008 1:46:00 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,54327,0,50462,50013,0,0,TO_DATE('2008-02-12 13:45:59','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Created','C','F','Created',0,0,'N',0,TO_DATE('2008-02-12 13:45:59','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Feb 12, 2008 1:46:00 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50462 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Feb 12, 2008 1:46:01 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,54345,0,50463,50013,0,0,TO_DATE('2008-02-12 13:46:00','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Updated','C','F','Updated',0,0,'N',0,TO_DATE('2008-02-12 13:46:00','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Feb 12, 2008 1:46:01 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50463 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Feb 12, 2008 1:46:02 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,54346,0,50464,50013,0,0,TO_DATE('2008-02-12 13:46:01','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Updated By','C','F','Updated By',0,0,'N',0,TO_DATE('2008-02-12 13:46:01','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Feb 12, 2008 1:46:02 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50464 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Feb 12, 2008 1:46:05 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,54347,0,50465,50013,0,0,TO_DATE('2008-02-12 13:46:02','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Valid from','C','F','Valid from',0,0,'N',0,TO_DATE('2008-02-12 13:46:02','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Feb 12, 2008 1:46:05 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50465 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Feb 12, 2008 1:46:06 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,54324,0,50466,50013,0,0,TO_DATE('2008-02-12 13:46:05','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Process Instance','C','F','Process Instance',0,0,'N',0,TO_DATE('2008-02-12 13:46:05','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Feb 12, 2008 1:46:06 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50466 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Feb 12, 2008 1:46:07 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,54344,0,50467,50013,0,0,TO_DATE('2008-02-12 13:46:06','YYYY-MM-DD HH24:MI:SS'),0,'T','N','Y','N','N','N','N','N','N','N','N','N','N','Y','N','N','Y','N','N','N','N','N','X',1,0,0,'Sequence','C','F','Sequence',0,0,'N',10,TO_DATE('2008-02-12 13:46:06','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Feb 12, 2008 1:46:07 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50467 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Feb 12, 2008 1:46:08 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,54330,0,50468,50013,0,0,TO_DATE('2008-02-12 13:46:07','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Active','C','F','Active',0,0,'N',0,TO_DATE('2008-02-12 13:46:07','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Feb 12, 2008 1:46:08 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50468 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Feb 12, 2008 1:46:09 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,54335,0,50469,50013,0,0,TO_DATE('2008-02-12 13:46:08','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Levels','C','F','Levels',0,10,'N',0,TO_DATE('2008-02-12 13:46:08','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Feb 12, 2008 1:46:09 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50469 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Feb 12, 2008 1:46:10 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,54338,0,50470,50013,0,0,TO_DATE('2008-02-12 13:46:09','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Product','C','F','Product',0,20,'N',0,TO_DATE('2008-02-12 13:46:09','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Feb 12, 2008 1:46:10 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50470 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Feb 12, 2008 1:46:11 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,54332,0,50471,50013,0,0,TO_DATE('2008-02-12 13:46:10','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Qty is Percentage','C','F','Qty is Percentage',0,30,'N',0,TO_DATE('2008-02-12 13:46:10','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Feb 12, 2008 1:46:11 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50471 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Feb 12, 2008 1:46:12 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,54333,0,50472,50013,0,0,TO_DATE('2008-02-12 13:46:11','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Issue Method','C','F','Issue Method',0,40,'N',0,TO_DATE('2008-02-12 13:46:11','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Feb 12, 2008 1:46:12 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50472 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Feb 12, 2008 1:46:13 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,54341,0,50473,50013,0,0,TO_DATE('2008-02-12 13:46:12','YYYY-MM-DD HH24:MI:SS'),0,'T','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Component Qty','C','F','Component Qty',0,50,'N',0,TO_DATE('2008-02-12 13:46:12','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Feb 12, 2008 1:46:13 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50473 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Feb 12, 2008 1:46:13 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFont_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,54342,0,100,130,50474,50013,0,0,TO_DATE('2008-02-12 13:46:13','YYYY-MM-DD HH24:MI:SS'),0,'D','N','Y','N','N','N','N','N','Y','N','N','Y','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Qty Batch','C','F','Qty Batch',20,60,'N',0,TO_DATE('2008-02-12 13:46:13','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Feb 12, 2008 1:46:13 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50474 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Feb 12, 2008 1:46:14 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,54325,0,50475,50013,0,0,TO_DATE('2008-02-12 13:46:13','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'UOM','C','F','UOM',0,70,'N',0,TO_DATE('2008-02-12 13:46:13','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Feb 12, 2008 1:46:14 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50475 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Feb 12, 2008 1:46:15 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,54343,0,50476,50013,0,0,TO_DATE('2008-02-12 13:46:14','YYYY-MM-DD HH24:MI:SS'),0,'T','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Scrap','C','F','Scrap',0,80,'N',0,TO_DATE('2008-02-12 13:46:14','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Feb 12, 2008 1:46:15 PM CST
-- Fix Multi Leven Print BOM
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50476 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Feb 12, 2008 1:46:15 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormat SET AD_PrintColor_ID=100, AD_PrintFont_ID=130, AD_PrintPaper_ID=100, AD_PrintTableFormat_ID=100, AD_ReportView_ID=53007, AD_Table_ID=53063, CreateCopy='N', Description='Multi Level BOM & Formula Detail', FooterMargin=0, HeaderMargin=0, IsActive='Y', IsDefault='N', IsForm='N', IsStandardHeaderFooter='Y', IsTableBased='Y', Name='Multi Level BOM & Formula Detail', PrinterName=NULL,Updated=TO_DATE('2008-02-12 13:46:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormat_ID=50013
;

-- Feb 12, 2008 1:46:16 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem SET AD_Column_ID=54340, AD_PrintFormat_ID=50013, ArcDiameter=0, BarcodeType=NULL, BelowColumn=0, FieldAlignmentType='L', ImageIsAttached='N', ImageURL=NULL, IsActive='Y', IsAveraged='N', IsCounted='N', IsDeviationCalc='N', IsFixedWidth='N', IsGroupBy='N', IsHeightOneLine='N', IsMaxCalc='N', IsMinCalc='N', IsNextLine='N', IsNextPage='N', IsOrderBy='N', IsPageBreak='N', IsPrinted='N', IsRelativePosition='Y', IsRunningTotal='N', IsSetNLPosition='N', IsSummarized='N', IsSuppressNull='N', IsVarianceCalc='N', LineAlignmentType='X', LineWidth=1, MaxHeight=0, MaxWidth=0, Name='BOM & Formaula', PrintAreaType='C', PrintFormatType='F', PrintName='BOM & Formaula', PrintNameSuffix=NULL, RunningTotalLines=0, SeqNo=0, ShapeType='N', SortNo=0, XPosition=0, XSpace=0, YPosition=0, YSpace=0,Updated=TO_DATE('2008-02-12 13:46:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50450
;

-- Feb 12, 2008 1:46:16 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50450
;

-- Feb 12, 2008 1:46:17 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem SET AD_Column_ID=54328, AD_PrintFormat_ID=50013, ArcDiameter=0, BarcodeType=NULL, BelowColumn=0, FieldAlignmentType='L', ImageIsAttached='N', ImageURL=NULL, IsActive='Y', IsAveraged='N', IsCounted='N', IsDeviationCalc='N', IsFixedWidth='N', IsGroupBy='N', IsHeightOneLine='N', IsMaxCalc='N', IsMinCalc='N', IsNextLine='N', IsNextPage='N', IsOrderBy='N', IsPageBreak='N', IsPrinted='N', IsRelativePosition='Y', IsRunningTotal='N', IsSetNLPosition='N', IsSummarized='N', IsSuppressNull='N', IsVarianceCalc='N', LineAlignmentType='X', LineWidth=1, MaxHeight=0, MaxWidth=0, Name='Created By', PrintAreaType='C', PrintFormatType='F', PrintName='Created By', PrintNameSuffix=NULL, RunningTotalLines=0, SeqNo=0, ShapeType='N', SortNo=0, XPosition=0, XSpace=0, YPosition=0, YSpace=0,Updated=TO_DATE('2008-02-12 13:46:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50451
;

-- Feb 12, 2008 1:46:17 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50451
;

-- Feb 12, 2008 1:46:17 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem SET AD_Column_ID=54329, AD_PrintFormat_ID=50013, ArcDiameter=0, BarcodeType=NULL, BelowColumn=0, FieldAlignmentType='B', ImageIsAttached='N', ImageURL=NULL, IsActive='Y', IsAveraged='N', IsCounted='N', IsDeviationCalc='N', IsFixedWidth='N', IsGroupBy='N', IsHeightOneLine='N', IsMaxCalc='N', IsMinCalc='N', IsNextLine='N', IsNextPage='N', IsOrderBy='N', IsPageBreak='N', IsPrinted='N', IsRelativePosition='Y', IsRunningTotal='N', IsSetNLPosition='N', IsSummarized='N', IsSuppressNull='N', IsVarianceCalc='N', LineAlignmentType='X', LineWidth=1, MaxHeight=0, MaxWidth=0, Name='Description', PrintAreaType='C', PrintFormatType='F', PrintName='Description', PrintNameSuffix=NULL, RunningTotalLines=0, SeqNo=0, ShapeType='N', SortNo=0, XPosition=0, XSpace=0, YPosition=0, YSpace=0,Updated=TO_DATE('2008-02-12 13:46:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50452
;

-- Feb 12, 2008 1:46:17 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50452
;

-- Feb 12, 2008 1:46:18 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem SET AD_Column_ID=54323, AD_PrintFormat_ID=50013, ArcDiameter=0, BarcodeType=NULL, BelowColumn=0, FieldAlignmentType='L', ImageIsAttached='N', ImageURL=NULL, IsActive='Y', IsAveraged='N', IsCounted='N', IsDeviationCalc='N', IsFixedWidth='N', IsGroupBy='N', IsHeightOneLine='N', IsMaxCalc='N', IsMinCalc='N', IsNextLine='N', IsNextPage='N', IsOrderBy='N', IsPageBreak='N', IsPrinted='N', IsRelativePosition='Y', IsRunningTotal='N', IsSetNLPosition='N', IsSummarized='N', IsSuppressNull='N', IsVarianceCalc='N', LineAlignmentType='X', LineWidth=1, MaxHeight=0, MaxWidth=0, Name='Organization', PrintAreaType='C', PrintFormatType='F', PrintName='Organization', PrintNameSuffix=NULL, RunningTotalLines=0, SeqNo=0, ShapeType='N', SortNo=0, XPosition=0, XSpace=0, YPosition=0, YSpace=0,Updated=TO_DATE('2008-02-12 13:46:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50453
;

-- Feb 12, 2008 1:46:18 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50453
;

-- Feb 12, 2008 1:46:19 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem SET AD_Column_ID=54334, AD_PrintFormat_ID=50013, ArcDiameter=0, BarcodeType=NULL, BelowColumn=0, FieldAlignmentType='T', ImageIsAttached='N', ImageURL=NULL, IsActive='Y', IsAveraged='N', IsCounted='N', IsDeviationCalc='N', IsFixedWidth='N', IsGroupBy='N', IsHeightOneLine='N', IsMaxCalc='N', IsMinCalc='N', IsNextLine='N', IsNextPage='N', IsOrderBy='N', IsPageBreak='N', IsPrinted='N', IsRelativePosition='Y', IsRunningTotal='N', IsSetNLPosition='N', IsSummarized='N', IsSuppressNull='N', IsVarianceCalc='N', LineAlignmentType='X', LineWidth=1, MaxHeight=0, MaxWidth=0, Name='Level no', PrintAreaType='C', PrintFormatType='F', PrintName='Level no', PrintNameSuffix=NULL, RunningTotalLines=0, SeqNo=0, ShapeType='N', SortNo=0, XPosition=0, XSpace=0, YPosition=0, YSpace=0,Updated=TO_DATE('2008-02-12 13:46:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50454
;

-- Feb 12, 2008 1:46:19 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50454
;

-- Feb 12, 2008 1:46:19 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem SET AD_Column_ID=54337, AD_PrintFormat_ID=50013, ArcDiameter=0, BarcodeType=NULL, BelowColumn=0, FieldAlignmentType='L', ImageIsAttached='N', ImageURL=NULL, IsActive='Y', IsAveraged='N', IsCounted='N', IsDeviationCalc='N', IsFixedWidth='N', IsGroupBy='N', IsHeightOneLine='N', IsMaxCalc='N', IsMinCalc='N', IsNextLine='N', IsNextPage='N', IsOrderBy='N', IsPageBreak='N', IsPrinted='N', IsRelativePosition='Y', IsRunningTotal='N', IsSetNLPosition='N', IsSummarized='N', IsSuppressNull='N', IsVarianceCalc='N', LineAlignmentType='X', LineWidth=1, MaxHeight=0, MaxWidth=0, Name='Attribute Set Instance', PrintAreaType='C', PrintFormatType='F', PrintName='Attribute Set Instance', PrintNameSuffix=NULL, RunningTotalLines=0, SeqNo=0, ShapeType='N', SortNo=0, XPosition=0, XSpace=0, YPosition=0, YSpace=0,Updated=TO_DATE('2008-02-12 13:46:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50455
;

-- Feb 12, 2008 1:46:19 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50455
;

-- Feb 12, 2008 1:46:20 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem SET AD_Column_ID=54322, AD_PrintFormat_ID=50013, ArcDiameter=0, BarcodeType=NULL, BelowColumn=0, FieldAlignmentType='L', ImageIsAttached='N', ImageURL=NULL, IsActive='Y', IsAveraged='N', IsCounted='N', IsDeviationCalc='N', IsFixedWidth='N', IsGroupBy='N', IsHeightOneLine='N', IsMaxCalc='N', IsMinCalc='N', IsNextLine='N', IsNextPage='N', IsOrderBy='N', IsPageBreak='N', IsPrinted='N', IsRelativePosition='Y', IsRunningTotal='N', IsSetNLPosition='N', IsSummarized='N', IsSuppressNull='N', IsVarianceCalc='N', LineAlignmentType='X', LineWidth=1, MaxHeight=0, MaxWidth=0, Name='Client', PrintAreaType='C', PrintFormatType='F', PrintName='Client', PrintNameSuffix=NULL, RunningTotalLines=0, SeqNo=0, ShapeType='N', SortNo=0, XPosition=0, XSpace=0, YPosition=0, YSpace=0,Updated=TO_DATE('2008-02-12 13:46:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50456
;

-- Feb 12, 2008 1:46:20 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50456
;

-- Feb 12, 2008 1:46:20 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem SET AD_Column_ID=54348, AD_PrintFormat_ID=50013, ArcDiameter=0, BarcodeType=NULL, BelowColumn=0, FieldAlignmentType='L', ImageIsAttached='N', ImageURL=NULL, IsActive='Y', IsAveraged='N', IsCounted='N', IsDeviationCalc='N', IsFixedWidth='N', IsGroupBy='N', IsHeightOneLine='N', IsMaxCalc='N', IsMinCalc='N', IsNextLine='N', IsNextPage='N', IsOrderBy='N', IsPageBreak='N', IsPrinted='N', IsRelativePosition='Y', IsRunningTotal='N', IsSetNLPosition='N', IsSummarized='N', IsSuppressNull='N', IsVarianceCalc='N', LineAlignmentType='X', LineWidth=1, MaxHeight=0, MaxWidth=0, Name='Valid to', PrintAreaType='C', PrintFormatType='F', PrintName='Valid to', PrintNameSuffix=NULL, RunningTotalLines=0, SeqNo=0, ShapeType='N', SortNo=0, XPosition=0, XSpace=0, YPosition=0, YSpace=0,Updated=TO_DATE('2008-02-12 13:46:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50457
;

-- Feb 12, 2008 1:46:20 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50457
;

-- Feb 12, 2008 1:46:21 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem SET AD_Column_ID=54336, AD_PrintFormat_ID=50013, ArcDiameter=0, BarcodeType=NULL, BelowColumn=0, FieldAlignmentType='T', ImageIsAttached='N', ImageURL=NULL, IsActive='Y', IsAveraged='N', IsCounted='N', IsDeviationCalc='N', IsFixedWidth='N', IsGroupBy='N', IsHeightOneLine='N', IsMaxCalc='N', IsMinCalc='N', IsNextLine='N', IsNextPage='N', IsOrderBy='N', IsPageBreak='N', IsPrinted='N', IsRelativePosition='Y', IsRunningTotal='N', IsSetNLPosition='N', IsSummarized='N', IsSuppressNull='N', IsVarianceCalc='N', LineAlignmentType='X', LineWidth=1, MaxHeight=0, MaxWidth=0, Name='Line No', PrintAreaType='C', PrintFormatType='F', PrintName='Line No', PrintNameSuffix=NULL, RunningTotalLines=0, SeqNo=0, ShapeType='N', SortNo=0, XPosition=0, XSpace=0, YPosition=0, YSpace=0,Updated=TO_DATE('2008-02-12 13:46:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50458
;

-- Feb 12, 2008 1:46:21 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50458
;

-- Feb 12, 2008 1:46:21 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem SET AD_Column_ID=54339, AD_PrintFormat_ID=50013, ArcDiameter=0, BarcodeType=NULL, BelowColumn=0, FieldAlignmentType='L', ImageIsAttached='N', ImageURL=NULL, IsActive='Y', IsAveraged='N', IsCounted='N', IsDeviationCalc='N', IsFixedWidth='N', IsGroupBy='N', IsHeightOneLine='N', IsMaxCalc='N', IsMinCalc='N', IsNextLine='N', IsNextPage='N', IsOrderBy='N', IsPageBreak='N', IsPrinted='N', IsRelativePosition='Y', IsRunningTotal='N', IsSetNLPosition='N', IsSummarized='N', IsSuppressNull='N', IsVarianceCalc='N', LineAlignmentType='X', LineWidth=1, MaxHeight=0, MaxWidth=0, Name='BOM Line', PrintAreaType='C', PrintFormatType='F', PrintName='BOM Line', PrintNameSuffix=NULL, RunningTotalLines=0, SeqNo=0, ShapeType='N', SortNo=0, XPosition=0, XSpace=0, YPosition=0, YSpace=0,Updated=TO_DATE('2008-02-12 13:46:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50459
;

-- Feb 12, 2008 1:46:22 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50459
;

-- Feb 12, 2008 1:46:22 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem SET AD_Column_ID=54326, AD_PrintFormat_ID=50013, ArcDiameter=0, BarcodeType=NULL, BelowColumn=0, FieldAlignmentType='L', ImageIsAttached='N', ImageURL=NULL, IsActive='Y', IsAveraged='N', IsCounted='N', IsDeviationCalc='N', IsFixedWidth='N', IsGroupBy='N', IsHeightOneLine='N', IsMaxCalc='N', IsMinCalc='N', IsNextLine='N', IsNextPage='N', IsOrderBy='N', IsPageBreak='N', IsPrinted='N', IsRelativePosition='Y', IsRunningTotal='N', IsSetNLPosition='N', IsSummarized='N', IsSuppressNull='N', IsVarianceCalc='N', LineAlignmentType='X', LineWidth=1, MaxHeight=0, MaxWidth=0, Name='Component Type', PrintAreaType='C', PrintFormatType='F', PrintName='Component Type', PrintNameSuffix=NULL, RunningTotalLines=0, SeqNo=0, ShapeType='N', SortNo=0, XPosition=0, XSpace=0, YPosition=0, YSpace=0,Updated=TO_DATE('2008-02-12 13:46:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50460
;

-- Feb 12, 2008 1:46:22 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50460
;

-- Feb 12, 2008 1:46:23 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem SET AD_Column_ID=54331, AD_PrintFormat_ID=50013, ArcDiameter=0, BarcodeType=NULL, BelowColumn=0, FieldAlignmentType='L', ImageIsAttached='N', ImageURL=NULL, IsActive='Y', IsAveraged='N', IsCounted='N', IsDeviationCalc='N', IsFixedWidth='N', IsGroupBy='N', IsHeightOneLine='N', IsMaxCalc='N', IsMinCalc='N', IsNextLine='N', IsNextPage='N', IsOrderBy='N', IsPageBreak='N', IsPrinted='N', IsRelativePosition='Y', IsRunningTotal='N', IsSetNLPosition='N', IsSummarized='N', IsSuppressNull='N', IsVarianceCalc='N', LineAlignmentType='X', LineWidth=1, MaxHeight=0, MaxWidth=0, Name='Is Critical', PrintAreaType='C', PrintFormatType='F', PrintName='Is Critical', PrintNameSuffix=NULL, RunningTotalLines=0, SeqNo=0, ShapeType='N', SortNo=0, XPosition=0, XSpace=0, YPosition=0, YSpace=0,Updated=TO_DATE('2008-02-12 13:46:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50461
;

-- Feb 12, 2008 1:46:23 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50461
;

-- Feb 12, 2008 1:46:23 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem SET AD_Column_ID=54327, AD_PrintFormat_ID=50013, ArcDiameter=0, BarcodeType=NULL, BelowColumn=0, FieldAlignmentType='L', ImageIsAttached='N', ImageURL=NULL, IsActive='Y', IsAveraged='N', IsCounted='N', IsDeviationCalc='N', IsFixedWidth='N', IsGroupBy='N', IsHeightOneLine='N', IsMaxCalc='N', IsMinCalc='N', IsNextLine='N', IsNextPage='N', IsOrderBy='N', IsPageBreak='N', IsPrinted='N', IsRelativePosition='Y', IsRunningTotal='N', IsSetNLPosition='N', IsSummarized='N', IsSuppressNull='N', IsVarianceCalc='N', LineAlignmentType='X', LineWidth=1, MaxHeight=0, MaxWidth=0, Name='Created', PrintAreaType='C', PrintFormatType='F', PrintName='Created', PrintNameSuffix=NULL, RunningTotalLines=0, SeqNo=0, ShapeType='N', SortNo=0, XPosition=0, XSpace=0, YPosition=0, YSpace=0,Updated=TO_DATE('2008-02-12 13:46:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50462
;

-- Feb 12, 2008 1:46:23 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50462
;

-- Feb 12, 2008 1:46:24 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem SET AD_Column_ID=54345, AD_PrintFormat_ID=50013, ArcDiameter=0, BarcodeType=NULL, BelowColumn=0, FieldAlignmentType='L', ImageIsAttached='N', ImageURL=NULL, IsActive='Y', IsAveraged='N', IsCounted='N', IsDeviationCalc='N', IsFixedWidth='N', IsGroupBy='N', IsHeightOneLine='N', IsMaxCalc='N', IsMinCalc='N', IsNextLine='N', IsNextPage='N', IsOrderBy='N', IsPageBreak='N', IsPrinted='N', IsRelativePosition='Y', IsRunningTotal='N', IsSetNLPosition='N', IsSummarized='N', IsSuppressNull='N', IsVarianceCalc='N', LineAlignmentType='X', LineWidth=1, MaxHeight=0, MaxWidth=0, Name='Updated', PrintAreaType='C', PrintFormatType='F', PrintName='Updated', PrintNameSuffix=NULL, RunningTotalLines=0, SeqNo=0, ShapeType='N', SortNo=0, XPosition=0, XSpace=0, YPosition=0, YSpace=0,Updated=TO_DATE('2008-02-12 13:46:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50463
;

-- Feb 12, 2008 1:46:24 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50463
;

-- Feb 12, 2008 1:46:25 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem SET AD_Column_ID=54346, AD_PrintFormat_ID=50013, ArcDiameter=0, BarcodeType=NULL, BelowColumn=0, FieldAlignmentType='L', ImageIsAttached='N', ImageURL=NULL, IsActive='Y', IsAveraged='N', IsCounted='N', IsDeviationCalc='N', IsFixedWidth='N', IsGroupBy='N', IsHeightOneLine='N', IsMaxCalc='N', IsMinCalc='N', IsNextLine='N', IsNextPage='N', IsOrderBy='N', IsPageBreak='N', IsPrinted='N', IsRelativePosition='Y', IsRunningTotal='N', IsSetNLPosition='N', IsSummarized='N', IsSuppressNull='N', IsVarianceCalc='N', LineAlignmentType='X', LineWidth=1, MaxHeight=0, MaxWidth=0, Name='Updated By', PrintAreaType='C', PrintFormatType='F', PrintName='Updated By', PrintNameSuffix=NULL, RunningTotalLines=0, SeqNo=0, ShapeType='N', SortNo=0, XPosition=0, XSpace=0, YPosition=0, YSpace=0,Updated=TO_DATE('2008-02-12 13:46:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50464
;

-- Feb 12, 2008 1:46:25 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50464
;

-- Feb 12, 2008 1:46:25 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem SET AD_Column_ID=54347, AD_PrintFormat_ID=50013, ArcDiameter=0, BarcodeType=NULL, BelowColumn=0, FieldAlignmentType='L', ImageIsAttached='N', ImageURL=NULL, IsActive='Y', IsAveraged='N', IsCounted='N', IsDeviationCalc='N', IsFixedWidth='N', IsGroupBy='N', IsHeightOneLine='N', IsMaxCalc='N', IsMinCalc='N', IsNextLine='N', IsNextPage='N', IsOrderBy='N', IsPageBreak='N', IsPrinted='N', IsRelativePosition='Y', IsRunningTotal='N', IsSetNLPosition='N', IsSummarized='N', IsSuppressNull='N', IsVarianceCalc='N', LineAlignmentType='X', LineWidth=1, MaxHeight=0, MaxWidth=0, Name='Valid from', PrintAreaType='C', PrintFormatType='F', PrintName='Valid from', PrintNameSuffix=NULL, RunningTotalLines=0, SeqNo=0, ShapeType='N', SortNo=0, XPosition=0, XSpace=0, YPosition=0, YSpace=0,Updated=TO_DATE('2008-02-12 13:46:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50465
;

-- Feb 12, 2008 1:46:25 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50465
;

-- Feb 12, 2008 1:46:26 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem SET AD_Column_ID=54324, AD_PrintFormat_ID=50013, ArcDiameter=0, BarcodeType=NULL, BelowColumn=0, FieldAlignmentType='L', ImageIsAttached='N', ImageURL=NULL, IsActive='Y', IsAveraged='N', IsCounted='N', IsDeviationCalc='N', IsFixedWidth='N', IsGroupBy='N', IsHeightOneLine='N', IsMaxCalc='N', IsMinCalc='N', IsNextLine='N', IsNextPage='N', IsOrderBy='N', IsPageBreak='N', IsPrinted='N', IsRelativePosition='Y', IsRunningTotal='N', IsSetNLPosition='N', IsSummarized='N', IsSuppressNull='N', IsVarianceCalc='N', LineAlignmentType='X', LineWidth=1, MaxHeight=0, MaxWidth=0, Name='Process Instance', PrintAreaType='C', PrintFormatType='F', PrintName='Process Instance', PrintNameSuffix=NULL, RunningTotalLines=0, SeqNo=0, ShapeType='N', SortNo=0, XPosition=0, XSpace=0, YPosition=0, YSpace=0,Updated=TO_DATE('2008-02-12 13:46:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50466
;

-- Feb 12, 2008 1:46:26 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50466
;

-- Feb 12, 2008 1:46:26 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem SET AD_Column_ID=54344, AD_PrintFormat_ID=50013, ArcDiameter=0, BarcodeType=NULL, BelowColumn=0, FieldAlignmentType='T', ImageIsAttached='N', ImageURL=NULL, IsActive='Y', IsAveraged='N', IsCounted='N', IsDeviationCalc='N', IsFixedWidth='N', IsGroupBy='N', IsHeightOneLine='N', IsMaxCalc='N', IsMinCalc='N', IsNextLine='N', IsNextPage='N', IsOrderBy='Y', IsPageBreak='N', IsPrinted='N', IsRelativePosition='Y', IsRunningTotal='N', IsSetNLPosition='N', IsSummarized='N', IsSuppressNull='N', IsVarianceCalc='N', LineAlignmentType='X', LineWidth=1, MaxHeight=0, MaxWidth=0, Name='Sequence', PrintAreaType='C', PrintFormatType='F', PrintName='Sequence', PrintNameSuffix=NULL, RunningTotalLines=0, SeqNo=0, ShapeType='N', SortNo=10, XPosition=0, XSpace=0, YPosition=0, YSpace=0,Updated=TO_DATE('2008-02-12 13:46:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50467
;

-- Feb 12, 2008 1:46:26 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50467
;

-- Feb 12, 2008 1:46:27 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem SET AD_Column_ID=54330, AD_PrintFormat_ID=50013, ArcDiameter=0, BarcodeType=NULL, BelowColumn=0, FieldAlignmentType='L', ImageIsAttached='N', ImageURL=NULL, IsActive='Y', IsAveraged='N', IsCounted='N', IsDeviationCalc='N', IsFixedWidth='N', IsGroupBy='N', IsHeightOneLine='N', IsMaxCalc='N', IsMinCalc='N', IsNextLine='N', IsNextPage='N', IsOrderBy='N', IsPageBreak='N', IsPrinted='N', IsRelativePosition='Y', IsRunningTotal='N', IsSetNLPosition='N', IsSummarized='N', IsSuppressNull='N', IsVarianceCalc='N', LineAlignmentType='X', LineWidth=1, MaxHeight=0, MaxWidth=0, Name='Active', PrintAreaType='C', PrintFormatType='F', PrintName='Active', PrintNameSuffix=NULL, RunningTotalLines=0, SeqNo=0, ShapeType='N', SortNo=0, XPosition=0, XSpace=0, YPosition=0, YSpace=0,Updated=TO_DATE('2008-02-12 13:46:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50468
;

-- Feb 12, 2008 1:46:27 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50468
;

-- Feb 12, 2008 1:46:28 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem SET AD_Column_ID=54335, AD_PrintFormat_ID=50013, ArcDiameter=0, BarcodeType=NULL, BelowColumn=0, FieldAlignmentType='L', ImageIsAttached='N', ImageURL=NULL, IsActive='Y', IsAveraged='N', IsCounted='N', IsDeviationCalc='N', IsFixedWidth='N', IsGroupBy='N', IsHeightOneLine='N', IsMaxCalc='N', IsMinCalc='N', IsNextLine='N', IsNextPage='N', IsOrderBy='N', IsPageBreak='N', IsPrinted='Y', IsRelativePosition='Y', IsRunningTotal='N', IsSetNLPosition='N', IsSummarized='N', IsSuppressNull='N', IsVarianceCalc='N', LineAlignmentType='X', LineWidth=1, MaxHeight=0, MaxWidth=0, Name='Levels', PrintAreaType='C', PrintFormatType='F', PrintName='Levels', PrintNameSuffix=NULL, RunningTotalLines=0, SeqNo=10, ShapeType='N', SortNo=0, XPosition=0, XSpace=0, YPosition=0, YSpace=0,Updated=TO_DATE('2008-02-12 13:46:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50469
;

-- Feb 12, 2008 1:46:28 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50469
;

-- Feb 12, 2008 1:46:28 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem SET AD_Column_ID=54338, AD_PrintFormat_ID=50013, ArcDiameter=0, BarcodeType=NULL, BelowColumn=0, FieldAlignmentType='L', ImageIsAttached='N', ImageURL=NULL, IsActive='Y', IsAveraged='N', IsCounted='N', IsDeviationCalc='N', IsFixedWidth='N', IsGroupBy='N', IsHeightOneLine='N', IsMaxCalc='N', IsMinCalc='N', IsNextLine='N', IsNextPage='N', IsOrderBy='N', IsPageBreak='N', IsPrinted='Y', IsRelativePosition='Y', IsRunningTotal='N', IsSetNLPosition='N', IsSummarized='N', IsSuppressNull='N', IsVarianceCalc='N', LineAlignmentType='X', LineWidth=1, MaxHeight=0, MaxWidth=0, Name='Product', PrintAreaType='C', PrintFormatType='F', PrintName='Product', PrintNameSuffix=NULL, RunningTotalLines=0, SeqNo=20, ShapeType='N', SortNo=0, XPosition=0, XSpace=0, YPosition=0, YSpace=0,Updated=TO_DATE('2008-02-12 13:46:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50470
;

-- Feb 12, 2008 1:46:28 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50470
;

-- Feb 12, 2008 1:46:29 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem SET AD_Column_ID=54332, AD_PrintFormat_ID=50013, ArcDiameter=0, BarcodeType=NULL, BelowColumn=0, FieldAlignmentType='L', ImageIsAttached='N', ImageURL=NULL, IsActive='Y', IsAveraged='N', IsCounted='N', IsDeviationCalc='N', IsFixedWidth='N', IsGroupBy='N', IsHeightOneLine='N', IsMaxCalc='N', IsMinCalc='N', IsNextLine='N', IsNextPage='N', IsOrderBy='N', IsPageBreak='N', IsPrinted='Y', IsRelativePosition='Y', IsRunningTotal='N', IsSetNLPosition='N', IsSummarized='N', IsSuppressNull='N', IsVarianceCalc='N', LineAlignmentType='X', LineWidth=1, MaxHeight=0, MaxWidth=0, Name='Qty is Percentage', PrintAreaType='C', PrintFormatType='F', PrintName='Qty is Percentage', PrintNameSuffix=NULL, RunningTotalLines=0, SeqNo=30, ShapeType='N', SortNo=0, XPosition=0, XSpace=0, YPosition=0, YSpace=0,Updated=TO_DATE('2008-02-12 13:46:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50471
;

-- Feb 12, 2008 1:46:29 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50471
;

-- Feb 12, 2008 1:46:29 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem SET AD_Column_ID=54333, AD_PrintFormat_ID=50013, ArcDiameter=0, BarcodeType=NULL, BelowColumn=0, FieldAlignmentType='L', ImageIsAttached='N', ImageURL=NULL, IsActive='Y', IsAveraged='N', IsCounted='N', IsDeviationCalc='N', IsFixedWidth='N', IsGroupBy='N', IsHeightOneLine='N', IsMaxCalc='N', IsMinCalc='N', IsNextLine='N', IsNextPage='N', IsOrderBy='N', IsPageBreak='N', IsPrinted='Y', IsRelativePosition='Y', IsRunningTotal='N', IsSetNLPosition='N', IsSummarized='N', IsSuppressNull='N', IsVarianceCalc='N', LineAlignmentType='X', LineWidth=1, MaxHeight=0, MaxWidth=0, Name='Issue Method', PrintAreaType='C', PrintFormatType='F', PrintName='Issue Method', PrintNameSuffix=NULL, RunningTotalLines=0, SeqNo=40, ShapeType='N', SortNo=0, XPosition=0, XSpace=0, YPosition=0, YSpace=0,Updated=TO_DATE('2008-02-12 13:46:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50472
;

-- Feb 12, 2008 1:46:30 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50472
;

-- Feb 12, 2008 1:46:30 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem SET AD_Column_ID=54341, AD_PrintFormat_ID=50013, ArcDiameter=0, BarcodeType=NULL, BelowColumn=0, FieldAlignmentType='T', ImageIsAttached='N', ImageURL=NULL, IsActive='Y', IsAveraged='N', IsCounted='N', IsDeviationCalc='N', IsFixedWidth='N', IsGroupBy='N', IsHeightOneLine='N', IsMaxCalc='N', IsMinCalc='N', IsNextLine='N', IsNextPage='N', IsOrderBy='N', IsPageBreak='N', IsPrinted='Y', IsRelativePosition='Y', IsRunningTotal='N', IsSetNLPosition='N', IsSummarized='N', IsSuppressNull='N', IsVarianceCalc='N', LineAlignmentType='X', LineWidth=1, MaxHeight=0, MaxWidth=0, Name='Component Qty', PrintAreaType='C', PrintFormatType='F', PrintName='Component Qty', PrintNameSuffix=NULL, RunningTotalLines=0, SeqNo=50, ShapeType='N', SortNo=0, XPosition=0, XSpace=0, YPosition=0, YSpace=0,Updated=TO_DATE('2008-02-12 13:46:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50473
;

-- Feb 12, 2008 1:46:30 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50473
;

-- Feb 12, 2008 1:46:31 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem SET AD_Column_ID=54342, AD_PrintColor_ID=100, AD_PrintFont_ID=130, AD_PrintFormat_ID=50013, ArcDiameter=0, BarcodeType=NULL, BelowColumn=0, FieldAlignmentType='D', ImageIsAttached='N', ImageURL=NULL, IsActive='Y', IsAveraged='N', IsCounted='N', IsDeviationCalc='N', IsFixedWidth='N', IsGroupBy='N', IsHeightOneLine='Y', IsMaxCalc='N', IsMinCalc='N', IsNextLine='Y', IsNextPage='N', IsOrderBy='N', IsPageBreak='N', IsPrinted='Y', IsRelativePosition='Y', IsRunningTotal='N', IsSetNLPosition='N', IsSummarized='N', IsSuppressNull='N', IsVarianceCalc='N', LineAlignmentType='X', LineWidth=1, MaxHeight=0, MaxWidth=0, Name='Qty Batch', PrintAreaType='C', PrintFormatType='F', PrintName='Qty Batch', PrintNameSuffix=NULL, RunningTotalLines=20, SeqNo=60, ShapeType='N', SortNo=0, XPosition=0, XSpace=0, YPosition=0, YSpace=0,Updated=TO_DATE('2008-02-12 13:46:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50474
;

-- Feb 12, 2008 1:46:31 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50474
;

-- Feb 12, 2008 1:46:31 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem SET AD_Column_ID=54325, AD_PrintFormat_ID=50013, ArcDiameter=0, BarcodeType=NULL, BelowColumn=0, FieldAlignmentType='L', ImageIsAttached='N', ImageURL=NULL, IsActive='Y', IsAveraged='N', IsCounted='N', IsDeviationCalc='N', IsFixedWidth='N', IsGroupBy='N', IsHeightOneLine='N', IsMaxCalc='N', IsMinCalc='N', IsNextLine='N', IsNextPage='N', IsOrderBy='N', IsPageBreak='N', IsPrinted='Y', IsRelativePosition='Y', IsRunningTotal='N', IsSetNLPosition='N', IsSummarized='N', IsSuppressNull='N', IsVarianceCalc='N', LineAlignmentType='X', LineWidth=1, MaxHeight=0, MaxWidth=0, Name='UOM', PrintAreaType='C', PrintFormatType='F', PrintName='UOM', PrintNameSuffix=NULL, RunningTotalLines=0, SeqNo=70, ShapeType='N', SortNo=0, XPosition=0, XSpace=0, YPosition=0, YSpace=0,Updated=TO_DATE('2008-02-12 13:46:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50475
;

-- Feb 12, 2008 1:46:31 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50475
;

-- Feb 12, 2008 1:46:32 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem SET AD_Column_ID=54343, AD_PrintFormat_ID=50013, ArcDiameter=0, BarcodeType=NULL, BelowColumn=0, FieldAlignmentType='T', ImageIsAttached='N', ImageURL=NULL, IsActive='Y', IsAveraged='N', IsCounted='N', IsDeviationCalc='N', IsFixedWidth='N', IsGroupBy='N', IsHeightOneLine='N', IsMaxCalc='N', IsMinCalc='N', IsNextLine='N', IsNextPage='N', IsOrderBy='N', IsPageBreak='N', IsPrinted='Y', IsRelativePosition='Y', IsRunningTotal='N', IsSetNLPosition='N', IsSummarized='N', IsSuppressNull='N', IsVarianceCalc='N', LineAlignmentType='X', LineWidth=1, MaxHeight=0, MaxWidth=0, Name='Scrap', PrintAreaType='C', PrintFormatType='F', PrintName='Scrap', PrintNameSuffix=NULL, RunningTotalLines=0, SeqNo=80, ShapeType='N', SortNo=0, XPosition=0, XSpace=0, YPosition=0, YSpace=0,Updated=TO_DATE('2008-02-12 13:46:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormatItem_ID=50476
;

-- Feb 12, 2008 1:46:32 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_PrintFormatItem_Trl SET IsTranslated='N' WHERE AD_PrintFormatItem_ID=50476
;

-- Feb 12, 2008 1:46:32 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Process SET AD_PrintFormat_ID=50013, AD_ReportView_ID=53007, AccessLevel='3', Classname='org.eevolution.process.PrintBOM', Description='Shows in two different panels the parent-component relationship for the product entered in the Product field.', EntityType='EE01', Help='The BOM & Formula Review option menu shows in two different panels the parent-component relationship for the product entered in the Product field.

You need to introduce the parent product you want to see its components then click the OK button, next drag the left margin of the panel to the right and you will have the two panels.

When you need to consult an implosion, click the implosion check box and enter the component you wish to consult and tick the Implosion check box.

You need to introduce the parent product you want to see its components then click the OK button, next drag the left margin of the panel to the right and you will have the two panels.

The left panel shows the BOM hierarchies in a tree form. The right panel shows the information connected with the BOM for every product in it.', IsActive='Y', IsBetaFunctionality='N', IsDirectPrint='N', IsReport='Y', JasperReport=NULL, Name='Multi Level BOM & Formula Detail', ProcedureName=NULL, ShowHelp='Y', Statistic_Count=0, Statistic_Seconds=0, Value='PP_Multi Level BOM & Formula', WorkflowValue=NULL,Updated=TO_DATE('2008-02-12 13:46:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_ID=53006
;

-- Feb 12, 2008 1:46:33 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Process_Para SET AD_Process_ID=53006, AD_Reference_ID=20, ColumnName='Implotion', DefaultValue='N', DefaultValue2=NULL, Description=NULL, EntityType='EE01', FieldLength=1, Help=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsMandatory='N', IsRange='N', Name='Implotion', SeqNo=20, VFormat=NULL, ValueMax=NULL, ValueMin=NULL,Updated=TO_DATE('2008-02-12 13:46:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53028
;

-- Feb 12, 2008 1:46:33 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53028
;

-- Feb 12, 2008 1:46:33 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference SET Description='Product selection no summary', EntityType='D', Help=NULL, IsActive='Y', Name='M_Product (no summary)', ValidationType='T',Updated=TO_DATE('2008-02-12 13:46:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=162
;

-- Feb 12, 2008 1:46:33 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=162
;

-- Feb 12, 2008 1:46:33 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Ref_Table SET AD_Table_ID = 208, AD_Display = 1410, AD_Key = 1402, isValueDisplayed = 'N', OrderByClause = 'M_Product.Value', EntityType ='D', WhereClause = 'M_Product.IsSummary=''N''' WHERE AD_Reference_ID = 162
;

-- Feb 12, 2008 1:46:33 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_Process_Para SET AD_Element_ID=454, AD_Process_ID=53006, AD_Reference_ID=30, AD_Reference_Value_ID=162, ColumnName='M_Product_ID', DefaultValue=NULL, DefaultValue2=NULL, Description='Product, Service, Item', EntityType='EE01', FieldLength=22, Help='Identifies an item which is either purchased or sold in this organization.', IsActive='Y', IsCentrallyMaintained='Y', IsMandatory='Y', IsRange='N', Name='Product', SeqNo=10, VFormat=NULL, ValueMax=NULL, ValueMin=NULL,Updated=TO_DATE('2008-02-12 13:46:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53029
;

-- Feb 12, 2008 1:46:34 PM CST
-- Fix Multi Leven Print BOM
UPDATE AD_TREENODEMM SET Parent_ID = 53022 , SeqNo = 3 WHERE AD_Tree_ID = 10 AND Node_ID = 53026
;

