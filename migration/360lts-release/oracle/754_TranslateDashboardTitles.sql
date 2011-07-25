-- Dec 5, 2010 7:59:36 PM COT
-- Translate zkwebui dashboard titles
UPDATE AD_Column SET IsTranslated='Y',Updated=TO_DATE('2010-12-05 19:59:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=51005
;

-- Dec 5, 2010 7:59:44 PM COT
UPDATE AD_Column SET IsTranslated='Y',Updated=TO_DATE('2010-12-05 19:59:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=51010
;

-- Dec 5, 2010 8:03:35 PM COT
UPDATE AD_Table SET Name='Dashboard Content',Updated=TO_DATE('2010-12-05 20:03:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=50010
;

-- Dec 5, 2010 8:03:35 PM COT
UPDATE AD_Table_Trl SET IsTranslated='N' WHERE AD_Table_ID=50010
;

-- Dec 5, 2010 8:03:54 PM COT
INSERT INTO AD_Table (AccessLevel,AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,EntityType,ImportTable,IsActive,IsCentrallyMaintained,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES ('6',0,0,53295,50007,TO_DATE('2010-12-05 20:03:49','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','Y','N','Y','N','N','N','Dashboard Content Trl','L','PA_DashboardContent_Trl',TO_DATE('2010-12-05 20:03:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 5, 2010 8:03:54 PM COT
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53295 AND NOT EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Table_ID=t.AD_Table_ID)
;

-- Dec 5, 2010 8:03:59 PM COT
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53408,TO_DATE('2010-12-05 20:03:54','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table PA_DashboardContent_Trl',1,'Y','N','Y','Y','PA_DashboardContent_Trl','N',1000000,TO_DATE('2010-12-05 20:03:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 5, 2010 8:04:16 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,60117,102,0,19,53295,129,'AD_Client_ID',TO_DATE('2010-12-05 20:04:13','YYYY-MM-DD HH24:MI:SS'),100,'@AD_Client_ID@','Client/Tenant for this installation.','D',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','Y','N','N','Client',0,TO_DATE('2010-12-05 20:04:13','YYYY-MM-DD HH24:MI:SS'),100,0.0)
;

-- Dec 5, 2010 8:04:16 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60117 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Dec 5, 2010 8:04:18 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,60118,109,0,18,106,53295,'AD_Language',TO_DATE('2010-12-05 20:04:16','YYYY-MM-DD HH24:MI:SS'),100,'Language for this entity','D',6,'The Language identifies the language to use for display and formatting','Y','N','N','N','N','Y','Y','N','Y','N','N','Language',0,TO_DATE('2010-12-05 20:04:16','YYYY-MM-DD HH24:MI:SS'),100,0.0)
;

-- Dec 5, 2010 8:04:18 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60118 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Dec 5, 2010 8:04:21 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,60119,113,0,19,53295,104,'AD_Org_ID',TO_DATE('2010-12-05 20:04:18','YYYY-MM-DD HH24:MI:SS'),100,'@AD_Org_ID@','Organizational entity within client','D',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','Y','N','N','Organization',0,TO_DATE('2010-12-05 20:04:18','YYYY-MM-DD HH24:MI:SS'),100,0.0)
;

-- Dec 5, 2010 8:04:21 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60119 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Dec 5, 2010 8:04:25 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,60120,245,0,16,53295,'Created',TO_DATE('2010-12-05 20:04:21','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','D',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','Y','N','N','Created',0,TO_DATE('2010-12-05 20:04:21','YYYY-MM-DD HH24:MI:SS'),100,0.0)
;

-- Dec 5, 2010 8:04:25 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60120 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Dec 5, 2010 8:04:27 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,60121,246,0,18,110,53295,'CreatedBy',TO_DATE('2010-12-05 20:04:25','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','D',22,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Created By',0,TO_DATE('2010-12-05 20:04:25','YYYY-MM-DD HH24:MI:SS'),100,0.0)
;

-- Dec 5, 2010 8:04:27 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60121 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Dec 5, 2010 8:04:32 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,60122,211,0,19,53295,'C_TaxCategory_ID',TO_DATE('2010-12-05 20:04:27','YYYY-MM-DD HH24:MI:SS'),100,'Tax Category','D',22,'The Tax Category provides a method of grouping similar taxes.  For example, Sales Tax or Value Added Tax.','Y','N','N','N','N','Y','Y','N','Y','N','N','Tax Category',0,TO_DATE('2010-12-05 20:04:27','YYYY-MM-DD HH24:MI:SS'),100,0.0)
;

-- Dec 5, 2010 8:04:32 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60122 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Dec 5, 2010 8:04:38 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,60123,275,0,10,53295,'Description',TO_DATE('2010-12-05 20:04:32','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record','D',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','N','Y','Description',0,TO_DATE('2010-12-05 20:04:32','YYYY-MM-DD HH24:MI:SS'),100,0.0)
;

-- Dec 5, 2010 8:04:38 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60123 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Dec 5, 2010 8:04:41 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,60124,348,0,20,53295,'IsActive',TO_DATE('2010-12-05 20:04:38','YYYY-MM-DD HH24:MI:SS'),100,'Y','The record is active in the system','D',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','Y','N','Y','Active',0,TO_DATE('2010-12-05 20:04:38','YYYY-MM-DD HH24:MI:SS'),100,0.0)
;

-- Dec 5, 2010 8:04:41 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60124 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Dec 5, 2010 8:04:46 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,60125,420,0,20,53295,'IsTranslated',TO_DATE('2010-12-05 20:04:41','YYYY-MM-DD HH24:MI:SS'),100,'This column is translated','D',1,'The Translated checkbox indicates if this column is translated.','Y','N','N','N','N','Y','N','N','Y','N','Y','Translated',0,TO_DATE('2010-12-05 20:04:41','YYYY-MM-DD HH24:MI:SS'),100,0.0)
;

-- Dec 5, 2010 8:04:46 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60125 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Dec 5, 2010 8:04:50 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,60126,469,0,10,53295,'Name',TO_DATE('2010-12-05 20:04:46','YYYY-MM-DD HH24:MI:SS'),100,'Alphanumeric identifier of the entity','D',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','Y','N','Y','Name',1,TO_DATE('2010-12-05 20:04:46','YYYY-MM-DD HH24:MI:SS'),100,0.0)
;

-- Dec 5, 2010 8:04:50 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60126 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Dec 5, 2010 8:04:54 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,60127,607,0,16,53295,'Updated',TO_DATE('2010-12-05 20:04:50','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','D',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated',0,TO_DATE('2010-12-05 20:04:50','YYYY-MM-DD HH24:MI:SS'),100,0.0)
;

-- Dec 5, 2010 8:04:54 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60127 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Dec 5, 2010 8:04:59 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,60128,608,0,18,110,53295,'UpdatedBy',TO_DATE('2010-12-05 20:04:54','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','D',22,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated By',0,TO_DATE('2010-12-05 20:04:54','YYYY-MM-DD HH24:MI:SS'),100,0.0)
;

-- Dec 5, 2010 8:04:59 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60128 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Dec 5, 2010 8:05:16 PM COT
UPDATE AD_Column SET AD_Element_ID=51006, ColumnName='PA_DashboardContent_ID', Description=NULL, Help=NULL, IsUpdateable='N', Name='PA_DashboardContent_ID',Updated=TO_DATE('2010-12-05 20:05:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60122
;

-- Dec 5, 2010 8:05:16 PM COT
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=60122
;

-- Dec 5, 2010 8:05:16 PM COT
UPDATE AD_Field SET Name='PA_DashboardContent_ID', Description=NULL, Help=NULL WHERE AD_Column_ID=60122 AND IsCentrallyMaintained='Y'
;

-- Dec 5, 2010 8:05:43 PM COT
UPDATE AD_Element SET Name='Dashboard Content', PrintName='Dashboard Content',Updated=TO_DATE('2010-12-05 20:05:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=51006
;

-- Dec 5, 2010 8:05:43 PM COT
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=51006
;

-- Dec 5, 2010 8:05:44 PM COT
UPDATE AD_Column SET ColumnName='PA_DashboardContent_ID', Name='Dashboard Content', Description=NULL, Help=NULL WHERE AD_Element_ID=51006
;

-- Dec 5, 2010 8:05:44 PM COT
UPDATE AD_Process_Para SET ColumnName='PA_DashboardContent_ID', Name='Dashboard Content', Description=NULL, Help=NULL, AD_Element_ID=51006 WHERE UPPER(ColumnName)='PA_DASHBOARDCONTENT_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Dec 5, 2010 8:05:44 PM COT
UPDATE AD_Process_Para SET ColumnName='PA_DashboardContent_ID', Name='Dashboard Content', Description=NULL, Help=NULL WHERE AD_Element_ID=51006 AND IsCentrallyMaintained='Y'
;

-- Dec 5, 2010 8:05:44 PM COT
UPDATE AD_Field SET Name='Dashboard Content', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=51006) AND IsCentrallyMaintained='Y'
;

-- Dec 5, 2010 8:05:44 PM COT
UPDATE AD_PrintFormatItem pi SET PrintName='Dashboard Content', Name='Dashboard Content' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=51006)
;

-- Dec 5, 2010 8:06:14 PM COT
CREATE TABLE PA_DashboardContent_Trl (AD_Client_ID NUMBER(10) NOT NULL, AD_Language VARCHAR2(6) NOT NULL, AD_Org_ID NUMBER(10) NOT NULL, Created DATE NOT NULL, CreatedBy NUMBER(10) NOT NULL, Description NVARCHAR2(255) DEFAULT NULL , IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, IsTranslated CHAR(1) CHECK (IsTranslated IN ('Y','N')) NOT NULL, Name NVARCHAR2(60) NOT NULL, PA_DashboardContent_ID NUMBER(10) NOT NULL, Updated DATE NOT NULL, UpdatedBy NUMBER(10) NOT NULL, CONSTRAINT PA_DashboardContent_Trl_Key PRIMARY KEY (AD_Language, PA_DashboardContent_ID))
;

-- Dec 5, 2010 8:10:56 PM COT
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,EntityType,HasTree,ImportFields,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53372,53295,50007,TO_DATE('2010-12-05 20:10:55','YYYY-MM-DD HH24:MI:SS'),100,'D','N','N','Y','N','N','N','N','Y','N','Y','Translation','N',20,1,TO_DATE('2010-12-05 20:10:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 5, 2010 8:10:56 PM COT
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53372 AND NOT EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Tab_ID=t.AD_Tab_ID)
;

-- Dec 5, 2010 8:11:20 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,60124,60873,0,53372,TO_DATE('2010-12-05 20:11:19','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system',1,'D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','N','Active',TO_DATE('2010-12-05 20:11:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 5, 2010 8:11:20 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=60873 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Dec 5, 2010 8:11:21 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,60117,60874,0,53372,TO_DATE('2010-12-05 20:11:20','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.',22,'D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','N','Client',TO_DATE('2010-12-05 20:11:20','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 5, 2010 8:11:21 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=60874 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Dec 5, 2010 8:11:21 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,60122,60875,0,53372,TO_DATE('2010-12-05 20:11:21','YYYY-MM-DD HH24:MI:SS'),100,22,'D','Y','Y','Y','N','N','N','N','N','Dashboard Content',TO_DATE('2010-12-05 20:11:21','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 5, 2010 8:11:21 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=60875 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Dec 5, 2010 8:11:22 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,60123,60876,0,53372,TO_DATE('2010-12-05 20:11:21','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record',255,'D','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','N','Description',TO_DATE('2010-12-05 20:11:21','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 5, 2010 8:11:22 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=60876 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Dec 5, 2010 8:11:23 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,60118,60877,0,53372,TO_DATE('2010-12-05 20:11:22','YYYY-MM-DD HH24:MI:SS'),100,'Language for this entity',6,'D','The Language identifies the language to use for display and formatting','Y','Y','Y','N','N','N','N','N','Language',TO_DATE('2010-12-05 20:11:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 5, 2010 8:11:23 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=60877 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Dec 5, 2010 8:11:23 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,60126,60878,0,53372,TO_DATE('2010-12-05 20:11:23','YYYY-MM-DD HH24:MI:SS'),100,'Alphanumeric identifier of the entity',60,'D','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','N','Name',TO_DATE('2010-12-05 20:11:23','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 5, 2010 8:11:23 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=60878 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Dec 5, 2010 8:11:24 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,60119,60879,0,53372,TO_DATE('2010-12-05 20:11:23','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client',22,'D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','N','N','Organization',TO_DATE('2010-12-05 20:11:23','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 5, 2010 8:11:24 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=60879 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Dec 5, 2010 8:11:24 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,60125,60880,0,53372,TO_DATE('2010-12-05 20:11:24','YYYY-MM-DD HH24:MI:SS'),100,'This column is translated',1,'D','The Translated checkbox indicates if this column is translated.','Y','Y','Y','N','N','N','N','N','Translated',TO_DATE('2010-12-05 20:11:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 5, 2010 8:11:24 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=60880 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Dec 5, 2010 8:11:58 PM COT
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=60874
;

-- Dec 5, 2010 8:11:58 PM COT
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=60879
;

-- Dec 5, 2010 8:11:58 PM COT
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=60875
;

-- Dec 5, 2010 8:11:58 PM COT
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=60877
;

-- Dec 5, 2010 8:11:58 PM COT
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=60873
;

-- Dec 5, 2010 8:11:58 PM COT
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=60880
;

-- Dec 5, 2010 8:11:58 PM COT
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=60878
;

-- Dec 5, 2010 8:11:58 PM COT
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=60876
;

-- Dec 5, 2010 8:12:31 PM COT
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2010-12-05 20:12:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=60874
;

-- Dec 5, 2010 8:12:45 PM COT
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2010-12-05 20:12:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=60875
;

-- Dec 5, 2010 8:12:56 PM COT
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2010-12-05 20:12:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=60877
;

ALTER TABLE PA_DashboardContent_Trl ADD (CONSTRAINT ADLangu_PADashboardContentTrl FOREIGN KEY (AD_Language) REFERENCES AD_Language);
                     
ALTER TABLE PA_DashboardContent_Trl ADD (CONSTRAINT PADashboardContent_PADashboard FOREIGN KEY (PA_DashboardContent_ID) REFERENCES PA_DashboardContent);

ALTER TABLE pa_dashboardcontent_trl MODIFY ( CREATED DATE DEFAULT SYSDATE );

ALTER TABLE pa_dashboardcontent_trl MODIFY ( UPDATED DATE DEFAULT SYSDATE );

INSERT 
INTO PA_DashboardContent_Trl
(
  AD_Language,
  IsTranslated,
  AD_Client_ID,
  AD_Org_ID,
  Createdby,
  UpdatedBy,
  PA_DashboardContent_ID,
  Description,
  NAME
)
SELECT l.ad_language,
  'N',
  dc.AD_Client_ID,
  dc.AD_Org_ID,
  100,
  100,
  dc.PA_DashboardContent_ID,
  dc.Description,
  dc.NAME 
FROM PA_DashboardContent dc, ad_language l
WHERE l.issystemlanguage='Y' AND dc.PA_DashboardContent_ID NOT IN (
  SELECT dat.PA_DashboardContent_ID 
    FROM PA_DashboardContent_Trl dat
    WHERE dat.AD_Language=l.ad_language
);

-- Dec 5, 2010 8:35:08 PM COT
-- Translate zkwebui dashboard titles
UPDATE PA_DashboardContent_Trl SET IsTranslated='Y',Name='Actividades',Description='Actividades de flujo de trabajo, avisos y solicitudes',Updated=TO_DATE('2010-12-05 20:35:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PA_DashboardContent_ID=50000 AND AD_Language LIKE 'es_%'
;

-- Dec 5, 2010 8:35:18 PM COT
UPDATE PA_DashboardContent_Trl SET IsTranslated='Y',Name='Favoritos',Description='Favoritos de usuario',Updated=TO_DATE('2010-12-05 20:35:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PA_DashboardContent_ID=50001 AND AD_Language LIKE 'es_%'
;

-- Dec 5, 2010 8:35:26 PM COT
UPDATE PA_DashboardContent_Trl SET IsTranslated='Y',Name='Vistas',Description='Vistas de Información',Updated=TO_DATE('2010-12-05 20:35:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PA_DashboardContent_ID=50002 AND AD_Language LIKE 'es_%'
;

-- Dec 5, 2010 8:35:39 PM COT
UPDATE PA_DashboardContent_Trl SET IsTranslated='Y',Name='Desempeño',Description='Indicadores de Desempeño',Updated=TO_DATE('2010-12-05 20:35:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PA_DashboardContent_ID=50003 AND AD_Language LIKE 'es_%'
;

-- Dec 5, 2010 8:37:00 PM COT
UPDATE PA_DashboardContent_Trl SET IsTranslated='Y',Name='Calendario',Description='Calendario de Google',Updated=TO_DATE('2010-12-05 20:37:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PA_DashboardContent_ID=50004 AND AD_Language LIKE 'es_%'
;

