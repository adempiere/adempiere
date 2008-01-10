-- Jan 9, 2008 11:29:56 PM COT
-- 1846929 - SaaS (ASP) (On-Demand) configurator
INSERT INTO AD_ELEMENT (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,NAME,PrintName,Updated,UpdatedBy) VALUES (0,53325,0,'IsUseASP',TO_TIMESTAMP('2008-01-09 23:29:56','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','IsUseASP','IsUseASP',TO_TIMESTAMP('2008-01-09 23:29:56','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_ELEMENT_TRL (AD_LANGUAGE,AD_Element_ID, Description,Help,NAME,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Element_ID, t.Description,t.Help,t.NAME,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_ELEMENT t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53325 AND EXISTS (SELECT * FROM AD_ELEMENT_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Element_ID!=t.AD_Element_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54095,53325,0,20,112,'IsUseASP',TO_TIMESTAMP('2008-01-09 23:29:56','YYYY-MM-DD HH24:MI:SS'),100,'N','D',1,'Y','N','N','N','N','Y','N','N','Y','N','Y','IsUseASP',TO_TIMESTAMP('2008-01-09 23:29:56','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54095 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

ALTER TABLE AD_CLIENT ADD COLUMN IsUseASP CHAR(1) DEFAULT 'N' CHECK (IsUseASP IN ('Y','N')) NOT NULL
;


INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,DisplayLogic,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54095,54238,0,145,TO_TIMESTAMP('2008-01-09 23:30:04','YYYY-MM-DD HH24:MI:SS'),100,1,'@AD_Client_ID@>0','D','Y','Y','Y','N','N','N','N','IsUseASP',280,0,TO_TIMESTAMP('2008-01-09 23:30:04','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54238 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_WINDOW (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,NAME,Processing,Updated,UpdatedBy,WindowType) VALUES (0,0,53015,TO_TIMESTAMP('2008-01-09 23:30:05','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Y','N','Y','ASP Modules','N',TO_TIMESTAMP('2008-01-09 23:30:05','YYYY-MM-DD HH24:MI:SS'),100,'M')
;

INSERT INTO AD_WINDOW_TRL (AD_LANGUAGE,AD_Window_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Window_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_WINDOW t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53015 AND EXISTS (SELECT * FROM AD_WINDOW_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Window_ID!=t.AD_Window_ID)
;

INSERT INTO AD_WINDOW_ACCESS (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,0,53015,TO_TIMESTAMP('2008-01-09 23:30:06','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_TIMESTAMP('2008-01-09 23:30:06','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_WINDOW_ACCESS (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,102,53015,TO_TIMESTAMP('2008-01-09 23:30:06','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_TIMESTAMP('2008-01-09 23:30:06','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_WINDOW_ACCESS (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,103,53015,TO_TIMESTAMP('2008-01-09 23:30:06','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_TIMESTAMP('2008-01-09 23:30:06','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_WINDOW_ACCESS (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,50001,53015,TO_TIMESTAMP('2008-01-09 23:30:06','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_TIMESTAMP('2008-01-09 23:30:06','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_TABLE (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,NAME,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53046,'4',TO_TIMESTAMP('2008-01-09 23:30:06','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','N','Y','N','N','N','ASP Window','L','ASP_Window',TO_TIMESTAMP('2008-01-09 23:30:06','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_TABLE_TRL (AD_LANGUAGE,AD_Table_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Table_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_TABLE t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53046 AND EXISTS (SELECT * FROM AD_TABLE_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Table_ID!=t.AD_Table_ID)
;

INSERT INTO AD_SEQUENCE (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,NAME,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53063,TO_TIMESTAMP('2008-01-09 23:30:07','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table ASP_Window',1,'Y','N','Y','Y','ASP_Window','N',1000000,TO_TIMESTAMP('2008-01-09 23:30:07','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54096,102,0,19,53046,'AD_Client_ID',TO_TIMESTAMP('2008-01-09 23:30:17','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Client_ID@','Client/Tenant for this installation.','D',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','N','N','N','Client',TO_TIMESTAMP('2008-01-09 23:30:17','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54096 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54097,113,0,19,53046,104,'AD_Org_ID',TO_TIMESTAMP('2008-01-09 23:30:21','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Org_ID@','Organizational entity within client','D',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','N','N','N','Organization',TO_TIMESTAMP('2008-01-09 23:30:21','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54097 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54098,143,0,19,53046,'AD_Window_ID',TO_TIMESTAMP('2008-01-09 23:30:22','YYYY-MM-DD HH24:MI:SS'),100,'Data entry or display window','D',22,'The Window field identifies a unique Window in the system.','Y','N','N','N','N','Y','Y','N','N','N','N','Window',TO_TIMESTAMP('2008-01-09 23:30:22','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54098 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_ELEMENT (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,NAME,PrintName,Updated,UpdatedBy) VALUES (0,53326,0,'ASP_Level_ID',TO_TIMESTAMP('2008-01-09 23:30:22','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','ASP_Level_ID','ASP_Level_ID',TO_TIMESTAMP('2008-01-09 23:30:22','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_ELEMENT_TRL (AD_LANGUAGE,AD_Element_ID, Description,Help,NAME,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Element_ID, t.Description,t.Help,t.NAME,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_ELEMENT t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53326 AND EXISTS (SELECT * FROM AD_ELEMENT_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Element_ID!=t.AD_Element_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54099,53326,0,19,53046,'ASP_Level_ID',TO_TIMESTAMP('2008-01-09 23:30:22','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','N','Y','Y','N','N','N','N','ASP_Level_ID',TO_TIMESTAMP('2008-01-09 23:30:22','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54099 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54100,608,0,18,110,53046,'UpdatedBy',TO_TIMESTAMP('2008-01-09 23:30:25','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','D',22,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','N','N','N','Updated By',TO_TIMESTAMP('2008-01-09 23:30:25','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54100 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54101,245,0,16,53046,'Created',TO_TIMESTAMP('2008-01-09 23:30:28','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','D',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','N','N','N','Created',TO_TIMESTAMP('2008-01-09 23:30:28','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54101 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54102,246,0,18,110,53046,'CreatedBy',TO_TIMESTAMP('2008-01-09 23:30:29','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','D',22,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','N','N','N','Created By',TO_TIMESTAMP('2008-01-09 23:30:29','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54102 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54103,348,0,20,53046,'IsActive',TO_TIMESTAMP('2008-01-09 23:30:33','YYYY-MM-DD HH24:MI:SS'),100,'Y','The record is active in the system','D',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons FOR de-activating AND NOT DELETING records:
(1) THE SYSTEM requires THE RECORD FOR AUDIT purposes.
(2) THE RECORD IS referenced BY other records. E.g., you cannot DELETE a Business Partner, IF there are invoices FOR this partner RECORD existing. You de-ACTIVATE THE Business Partner AND prevent that this RECORD IS used FOR future entries.','Y','N','N','N','N','Y','N','N','N','N','Y','Active',TO_TIMESTAMP('2008-01-09 23:30:33','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54103 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54104,607,0,16,53046,'Updated',TO_TIMESTAMP('2008-01-09 23:30:35','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','D',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','N','N','N','Updated',TO_TIMESTAMP('2008-01-09 23:30:35','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54104 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_REFERENCE (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,NAME,Updated,UpdatedBy,ValidationType) VALUES (0,0,53234,TO_TIMESTAMP('2008-01-09 23:30:36','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','ASP_Status',TO_TIMESTAMP('2008-01-09 23:30:36','YYYY-MM-DD HH24:MI:SS'),100,'L')
;

INSERT INTO AD_REFERENCE_TRL (AD_LANGUAGE,AD_Reference_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Reference_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_REFERENCE t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53234 AND EXISTS (SELECT * FROM AD_REFERENCE_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

INSERT INTO AD_REF_LIST (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,NAME,Updated,UpdatedBy,VALUE) VALUES (0,0,53284,53234,TO_TIMESTAMP('2008-01-09 23:30:37','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Hide',TO_TIMESTAMP('2008-01-09 23:30:37','YYYY-MM-DD HH24:MI:SS'),100,'H')
;

INSERT INTO AD_REF_LIST_TRL (AD_LANGUAGE,AD_Ref_List_ID, Description,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Ref_List_ID, t.Description,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_REF_LIST t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53284 AND EXISTS (SELECT * FROM AD_REF_LIST_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

INSERT INTO AD_REF_LIST (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,NAME,Updated,UpdatedBy,VALUE) VALUES (0,0,53285,53234,TO_TIMESTAMP('2008-01-09 23:30:38','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Show',TO_TIMESTAMP('2008-01-09 23:30:38','YYYY-MM-DD HH24:MI:SS'),100,'S')
;

INSERT INTO AD_REF_LIST_TRL (AD_LANGUAGE,AD_Ref_List_ID, Description,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Ref_List_ID, t.Description,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_REF_LIST t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53285 AND EXISTS (SELECT * FROM AD_REF_LIST_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

INSERT INTO AD_REF_LIST (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,NAME,Updated,UpdatedBy,VALUE) VALUES (0,0,53286,53234,TO_TIMESTAMP('2008-01-09 23:30:39','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Undefined',TO_TIMESTAMP('2008-01-09 23:30:39','YYYY-MM-DD HH24:MI:SS'),100,'U')
;

INSERT INTO AD_REF_LIST_TRL (AD_LANGUAGE,AD_Ref_List_ID, Description,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Ref_List_ID, t.Description,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_REF_LIST t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53286 AND EXISTS (SELECT * FROM AD_REF_LIST_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

INSERT INTO AD_ELEMENT (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,NAME,PrintName,Updated,UpdatedBy) VALUES (0,53327,0,'ASP_Status',TO_TIMESTAMP('2008-01-09 23:30:42','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','ASP_Status','ASP_Status',TO_TIMESTAMP('2008-01-09 23:30:42','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_ELEMENT_TRL (AD_LANGUAGE,AD_Element_ID, Description,Help,NAME,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Element_ID, t.Description,t.Help,t.NAME,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_ELEMENT t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53327 AND EXISTS (SELECT * FROM AD_ELEMENT_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Element_ID!=t.AD_Element_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54105,53327,0,17,53234,53046,'ASP_Status',TO_TIMESTAMP('2008-01-09 23:30:42','YYYY-MM-DD HH24:MI:SS'),100,'U','D',1,'Y','N','N','N','N','Y','N','N','N','N','Y','ASP_Status',TO_TIMESTAMP('2008-01-09 23:30:42','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54105 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_TAB (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,NAME,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53056,53046,53015,NULL,TO_TIMESTAMP('2008-01-09 23:30:46','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','N','N','Y','N','N','N','N','Window','N',40,2,TO_TIMESTAMP('2008-01-09 23:30:46','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_TAB_TRL (AD_LANGUAGE,AD_Tab_ID, CommitWarning,Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_TAB t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53056 AND EXISTS (SELECT * FROM AD_TAB_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54096,54239,0,53056,TO_TIMESTAMP('2008-01-09 23:30:50','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.',22,'D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_TIMESTAMP('2008-01-09 23:30:50','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54239 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54097,54240,0,53056,TO_TIMESTAMP('2008-01-09 23:30:54','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client',22,'D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_TIMESTAMP('2008-01-09 23:30:54','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54240 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54099,54241,0,53056,TO_TIMESTAMP('2008-01-09 23:30:56','YYYY-MM-DD HH24:MI:SS'),100,22,'D','Y','Y','Y','N','N','N','N','ASP_Level_ID',30,0,TO_TIMESTAMP('2008-01-09 23:30:56','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54241 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54098,54242,0,53056,TO_TIMESTAMP('2008-01-09 23:30:56','YYYY-MM-DD HH24:MI:SS'),100,'Data entry or display window',22,'D','The Window field identifies a unique Window in the system.','Y','Y','Y','N','N','N','N','Window',40,0,TO_TIMESTAMP('2008-01-09 23:30:56','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54242 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54105,54243,0,53056,TO_TIMESTAMP('2008-01-09 23:31:00','YYYY-MM-DD HH24:MI:SS'),100,1,'D','Y','Y','Y','N','N','N','N','ASP_Status',50,0,TO_TIMESTAMP('2008-01-09 23:31:00','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54243 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54103,54244,0,53056,TO_TIMESTAMP('2008-01-09 23:31:01','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system',1,'D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons FOR de-activating AND NOT DELETING records:
(1) THE SYSTEM requires THE RECORD FOR AUDIT purposes.
(2) THE RECORD IS referenced BY other records. E.g., you cannot DELETE a Business Partner, IF there are invoices FOR this partner RECORD existing. You de-ACTIVATE THE Business Partner AND prevent that this RECORD IS used FOR future entries.','Y','Y','Y','N','N','N','N','Active',60,0,TO_TIMESTAMP('2008-01-09 23:31:01','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54244 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_TABLE (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,NAME,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53047,'4',TO_TIMESTAMP('2008-01-09 23:31:05','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','N','Y','N','N','N','ASP Tab','L','ASP_Tab',TO_TIMESTAMP('2008-01-09 23:31:05','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_TABLE_TRL (AD_LANGUAGE,AD_Table_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Table_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_TABLE t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53047 AND EXISTS (SELECT * FROM AD_TABLE_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Table_ID!=t.AD_Table_ID)
;

INSERT INTO AD_SEQUENCE (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,NAME,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53064,TO_TIMESTAMP('2008-01-09 23:31:06','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table ASP_Tab',1,'Y','N','Y','Y','ASP_Tab','N',1000000,TO_TIMESTAMP('2008-01-09 23:31:06','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54106,102,0,19,53047,'AD_Client_ID',TO_TIMESTAMP('2008-01-09 23:31:07','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Client_ID@','Client/Tenant for this installation.','D',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','N','N','N','Client',TO_TIMESTAMP('2008-01-09 23:31:07','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54106 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54107,113,0,19,53047,104,'AD_Org_ID',TO_TIMESTAMP('2008-01-09 23:31:11','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Org_ID@','Organizational entity within client','D',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','N','N','N','Organization',TO_TIMESTAMP('2008-01-09 23:31:11','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54107 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54108,125,0,19,53047,163,'AD_Tab_ID',TO_TIMESTAMP('2008-01-09 23:31:12','YYYY-MM-DD HH24:MI:SS'),100,'Tab within a Window','D',22,'The Tab indicates a tab that displays within a window.','Y','N','N','N','N','Y','Y','N','N','N','N','Tab',TO_TIMESTAMP('2008-01-09 23:31:12','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54108 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54109,143,0,19,53047,'AD_Window_ID',TO_TIMESTAMP('2008-01-09 23:31:13','YYYY-MM-DD HH24:MI:SS'),100,'Data entry or display window','D',22,'The Window field identifies a unique Window in the system.','Y','N','N','N','N','Y','Y','N','N','N','N','Window',TO_TIMESTAMP('2008-01-09 23:31:13','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54109 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54110,53326,0,19,53047,'ASP_Level_ID',TO_TIMESTAMP('2008-01-09 23:31:14','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','N','Y','Y','N','N','N','N','ASP_Level_ID',TO_TIMESTAMP('2008-01-09 23:31:14','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54110 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54111,53327,0,17,53234,53047,'ASP_Status',TO_TIMESTAMP('2008-01-09 23:31:16','YYYY-MM-DD HH24:MI:SS'),100,'U','D',1,'Y','N','N','N','N','Y','N','N','N','N','Y','ASP_Status',TO_TIMESTAMP('2008-01-09 23:31:16','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54111 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54112,608,0,18,110,53047,'UpdatedBy',TO_TIMESTAMP('2008-01-09 23:31:20','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','D',22,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','N','N','N','Updated By',TO_TIMESTAMP('2008-01-09 23:31:20','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54112 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54113,245,0,16,53047,'Created',TO_TIMESTAMP('2008-01-09 23:31:21','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','D',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','N','N','N','Created',TO_TIMESTAMP('2008-01-09 23:31:21','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54113 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54114,246,0,18,110,53047,'CreatedBy',TO_TIMESTAMP('2008-01-09 23:31:25','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','D',22,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','N','N','N','Created By',TO_TIMESTAMP('2008-01-09 23:31:25','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54114 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54115,348,0,20,53047,'IsActive',TO_TIMESTAMP('2008-01-09 23:31:26','YYYY-MM-DD HH24:MI:SS'),100,'Y','The record is active in the system','D',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons FOR de-activating AND NOT DELETING records:
(1) THE SYSTEM requires THE RECORD FOR AUDIT purposes.
(2) THE RECORD IS referenced BY other records. E.g., you cannot DELETE a Business Partner, IF there are invoices FOR this partner RECORD existing. You de-ACTIVATE THE Business Partner AND prevent that this RECORD IS used FOR future entries.','Y','N','N','N','N','Y','N','N','N','N','Y','Active',TO_TIMESTAMP('2008-01-09 23:31:26','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54115 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_PROCESS (AD_Client_ID,AD_Org_ID,AD_Process_ID,AccessLevel,Classname,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,NAME,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,VALUE,WorkflowValue) VALUES (0,0,53065,'4','org.adempiere.process.ASPGenerateFields',TO_TIMESTAMP('2008-01-09 23:31:27','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','N','N','ASP Generate Fields','Y',0,0,TO_TIMESTAMP('2008-01-09 23:31:27','YYYY-MM-DD HH24:MI:SS'),100,'ASP Generate Fields',NULL)
;

INSERT INTO AD_PROCESS_TRL (AD_LANGUAGE,AD_Process_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Process_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_PROCESS t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53065 AND EXISTS (SELECT * FROM AD_PROCESS_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Process_ID!=t.AD_Process_ID)
;

INSERT INTO AD_PROCESS_PARA (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,NAME,SeqNo,Updated,UpdatedBy) VALUES (0,53327,0,53065,53125,17,53234,'ASP_Status',TO_TIMESTAMP('2008-01-09 23:31:32','YYYY-MM-DD HH24:MI:SS'),100,'U','D',0,'Y','Y','Y','N','ASP Status',10,TO_TIMESTAMP('2008-01-09 23:31:32','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_PROCESS_PARA_TRL (AD_LANGUAGE,AD_Process_Para_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Process_Para_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_PROCESS_PARA t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53125 AND EXISTS (SELECT * FROM AD_PROCESS_PARA_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54116,524,0,53065,28,53047,'Processing',TO_TIMESTAMP('2008-01-09 23:31:33','YYYY-MM-DD HH24:MI:SS'),100,'D',1,'Y','N','N','N','N','N','N','N','N','N','Y','Process Now',TO_TIMESTAMP('2008-01-09 23:31:33','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54116 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54117,607,0,16,53047,'Updated',TO_TIMESTAMP('2008-01-09 23:31:33','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','D',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','N','N','N','Updated',TO_TIMESTAMP('2008-01-09 23:31:33','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54117 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_ELEMENT (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,NAME,PrintName,Updated,UpdatedBy) VALUES (0,53328,0,'AllFields',TO_TIMESTAMP('2008-01-09 23:31:34','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','AllFields','AllFields',TO_TIMESTAMP('2008-01-09 23:31:34','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_ELEMENT_TRL (AD_LANGUAGE,AD_Element_ID, Description,Help,NAME,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Element_ID, t.Description,t.Help,t.NAME,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_ELEMENT t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53328 AND EXISTS (SELECT * FROM AD_ELEMENT_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Element_ID!=t.AD_Element_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54118,53328,0,20,53047,'AllFields',TO_TIMESTAMP('2008-01-09 23:31:34','YYYY-MM-DD HH24:MI:SS'),100,'Y','D',1,'Y','N','N','N','N','N','N','N','N','N','Y','AllFields',TO_TIMESTAMP('2008-01-09 23:31:34','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54118 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_TAB (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,NAME,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53057,53047,53015,NULL,TO_TIMESTAMP('2008-01-09 23:31:39','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','N','N','Y','N','N','N','N','Tab','N',50,3,TO_TIMESTAMP('2008-01-09 23:31:39','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_TAB_TRL (AD_LANGUAGE,AD_Tab_ID, CommitWarning,Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_TAB t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53057 AND EXISTS (SELECT * FROM AD_TAB_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54106,54245,0,53057,TO_TIMESTAMP('2008-01-09 23:31:40','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.',22,'D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_TIMESTAMP('2008-01-09 23:31:40','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54245 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54107,54246,0,53057,TO_TIMESTAMP('2008-01-09 23:31:40','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client',22,'D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_TIMESTAMP('2008-01-09 23:31:40','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54246 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54110,54247,0,53057,TO_TIMESTAMP('2008-01-09 23:31:41','YYYY-MM-DD HH24:MI:SS'),100,22,'D','Y','Y','Y','N','N','Y','N','ASP_Level_ID',30,0,TO_TIMESTAMP('2008-01-09 23:31:41','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54247 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54109,54248,0,53057,TO_TIMESTAMP('2008-01-09 23:31:42','YYYY-MM-DD HH24:MI:SS'),100,'Data entry or display window',22,'D','The Window field identifies a unique Window in the system.','Y','Y','Y','N','N','Y','N','Window',40,0,TO_TIMESTAMP('2008-01-09 23:31:42','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54248 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54108,54249,0,53057,TO_TIMESTAMP('2008-01-09 23:31:43','YYYY-MM-DD HH24:MI:SS'),100,'Tab within a Window',22,'D','The Tab indicates a tab that displays within a window.','Y','Y','Y','N','N','N','N','Tab',50,0,TO_TIMESTAMP('2008-01-09 23:31:43','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54249 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54111,54250,0,53057,TO_TIMESTAMP('2008-01-09 23:31:44','YYYY-MM-DD HH24:MI:SS'),100,1,'D','Y','Y','Y','N','N','N','N','ASP_Status',60,0,TO_TIMESTAMP('2008-01-09 23:31:44','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54250 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54118,54251,0,53057,TO_TIMESTAMP('2008-01-09 23:31:45','YYYY-MM-DD HH24:MI:SS'),100,1,'D','Y','Y','Y','N','N','N','N','AllFields',70,0,TO_TIMESTAMP('2008-01-09 23:31:45','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54251 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,DisplayLogic,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54116,54252,0,53057,TO_TIMESTAMP('2008-01-09 23:31:46','YYYY-MM-DD HH24:MI:SS'),100,1,'@AllFields@=N','D','Y','Y','Y','N','N','N','N','Process Now',80,0,TO_TIMESTAMP('2008-01-09 23:31:46','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54252 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54115,54253,0,53057,TO_TIMESTAMP('2008-01-09 23:31:46','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system',1,'D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons FOR de-activating AND NOT DELETING records:
(1) THE SYSTEM requires THE RECORD FOR AUDIT purposes.
(2) THE RECORD IS referenced BY other records. E.g., you cannot DELETE a Business Partner, IF there are invoices FOR this partner RECORD existing. You de-ACTIVATE THE Business Partner AND prevent that this RECORD IS used FOR future entries.','Y','Y','Y','N','N','N','N','Active',90,0,TO_TIMESTAMP('2008-01-09 23:31:46','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54253 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_TABLE (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,NAME,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53048,'4',TO_TIMESTAMP('2008-01-09 23:31:48','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','N','Y','N','N','N','ASP Field','L','ASP_Field',TO_TIMESTAMP('2008-01-09 23:31:48','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_TABLE_TRL (AD_LANGUAGE,AD_Table_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Table_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_TABLE t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53048 AND EXISTS (SELECT * FROM AD_TABLE_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Table_ID!=t.AD_Table_ID)
;

INSERT INTO AD_SEQUENCE (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,NAME,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53065,TO_TIMESTAMP('2008-01-09 23:31:48','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table ASP_Field',1,'Y','N','Y','Y','ASP_Field','N',1000000,TO_TIMESTAMP('2008-01-09 23:31:48','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54119,102,0,19,53048,'AD_Client_ID',TO_TIMESTAMP('2008-01-09 23:31:50','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Client_ID@','Client/Tenant for this installation.','D',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','N','N','N','Client',TO_TIMESTAMP('2008-01-09 23:31:50','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54119 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_VAL_RULE (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,NAME,TYPE,Updated,UpdatedBy) VALUES (0,0,52005,'AD_Field.AD_Tab_ID=@AD_Tab_ID@',TO_TIMESTAMP('2008-01-09 23:31:50','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','AD_Field in Tab','S',TO_TIMESTAMP('2008-01-09 23:31:50','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54120,107,0,19,53048,52005,'AD_Field_ID',TO_TIMESTAMP('2008-01-09 23:31:51','YYYY-MM-DD HH24:MI:SS'),100,'Field on a database table','D',22,'The Field identifies a field on a database table.','Y','N','N','N','N','N','Y','N','N','N','N','Field',TO_TIMESTAMP('2008-01-09 23:31:51','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54120 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54121,113,0,19,53048,104,'AD_Org_ID',TO_TIMESTAMP('2008-01-09 23:31:52','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Org_ID@','Organizational entity within client','D',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','N','N','N','Organization',TO_TIMESTAMP('2008-01-09 23:31:52','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54121 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54122,125,0,19,53048,163,'AD_Tab_ID',TO_TIMESTAMP('2008-01-09 23:31:53','YYYY-MM-DD HH24:MI:SS'),100,'Tab within a Window','D',22,'The Tab indicates a tab that displays within a window.','Y','N','N','N','N','Y','Y','N','N','N','N','Tab',TO_TIMESTAMP('2008-01-09 23:31:53','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54122 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54123,53326,0,19,53048,'ASP_Level_ID',TO_TIMESTAMP('2008-01-09 23:31:54','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','N','Y','Y','N','N','N','N','ASP_Level_ID',TO_TIMESTAMP('2008-01-09 23:31:54','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54123 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54124,608,0,18,110,53048,'UpdatedBy',TO_TIMESTAMP('2008-01-09 23:31:55','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','D',22,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','N','N','N','Updated By',TO_TIMESTAMP('2008-01-09 23:31:55','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54124 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54125,245,0,16,53048,'Created',TO_TIMESTAMP('2008-01-09 23:32:00','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','D',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','N','N','N','Created',TO_TIMESTAMP('2008-01-09 23:32:00','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54125 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54126,246,0,18,110,53048,'CreatedBy',TO_TIMESTAMP('2008-01-09 23:32:00','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','D',22,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','N','N','N','Created By',TO_TIMESTAMP('2008-01-09 23:32:00','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54126 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54127,348,0,20,53048,'IsActive',TO_TIMESTAMP('2008-01-09 23:32:01','YYYY-MM-DD HH24:MI:SS'),100,'Y','The record is active in the system','D',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons FOR de-activating AND NOT DELETING records:
(1) THE SYSTEM requires THE RECORD FOR AUDIT purposes.
(2) THE RECORD IS referenced BY other records. E.g., you cannot DELETE a Business Partner, IF there are invoices FOR this partner RECORD existing. You de-ACTIVATE THE Business Partner AND prevent that this RECORD IS used FOR future entries.','Y','N','N','N','N','Y','N','N','N','N','Y','Active',TO_TIMESTAMP('2008-01-09 23:32:01','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54127 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54128,607,0,16,53048,'Updated',TO_TIMESTAMP('2008-01-09 23:32:02','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','D',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','N','N','N','Updated',TO_TIMESTAMP('2008-01-09 23:32:02','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54128 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54129,53327,0,17,53234,53048,'ASP_Status',TO_TIMESTAMP('2008-01-09 23:32:03','YYYY-MM-DD HH24:MI:SS'),100,'U','D',1,'Y','N','N','N','N','Y','N','N','N','N','Y','ASP_Status',TO_TIMESTAMP('2008-01-09 23:32:03','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54129 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_TAB (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,DisplayLogic,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,NAME,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53058,53048,53015,NULL,TO_TIMESTAMP('2008-01-09 23:32:03','YYYY-MM-DD HH24:MI:SS'),100,'@AllFields@=N','D','N','Y','N','N','Y','N','N','N','N','Field','N',60,4,TO_TIMESTAMP('2008-01-09 23:32:03','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_TAB_TRL (AD_LANGUAGE,AD_Tab_ID, CommitWarning,Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_TAB t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53058 AND EXISTS (SELECT * FROM AD_TAB_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54119,54254,0,53058,TO_TIMESTAMP('2008-01-09 23:32:10','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.',22,'D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_TIMESTAMP('2008-01-09 23:32:10','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54254 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54121,54255,0,53058,TO_TIMESTAMP('2008-01-09 23:32:11','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client',22,'D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_TIMESTAMP('2008-01-09 23:32:11','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54255 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54123,54256,0,53058,TO_TIMESTAMP('2008-01-09 23:32:12','YYYY-MM-DD HH24:MI:SS'),100,22,'D','Y','Y','Y','N','N','N','N','ASP_Level_ID',30,0,TO_TIMESTAMP('2008-01-09 23:32:12','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54256 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54122,54257,0,53058,TO_TIMESTAMP('2008-01-09 23:32:16','YYYY-MM-DD HH24:MI:SS'),100,'Tab within a Window',22,'D','The Tab indicates a tab that displays within a window.','Y','Y','Y','N','N','N','N','Tab',40,0,TO_TIMESTAMP('2008-01-09 23:32:16','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54257 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54120,54258,0,53058,TO_TIMESTAMP('2008-01-09 23:32:17','YYYY-MM-DD HH24:MI:SS'),100,'Field on a database table',22,'D','The Field identifies a field on a database table.','Y','Y','Y','N','N','N','N','Field',50,0,TO_TIMESTAMP('2008-01-09 23:32:17','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54258 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54129,54259,0,53058,TO_TIMESTAMP('2008-01-09 23:32:18','YYYY-MM-DD HH24:MI:SS'),100,1,'D','Y','Y','Y','N','N','N','N','ASP_Status',60,0,TO_TIMESTAMP('2008-01-09 23:32:18','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54259 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54127,54260,0,53058,TO_TIMESTAMP('2008-01-09 23:32:18','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system',1,'D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons FOR de-activating AND NOT DELETING records:
(1) THE SYSTEM requires THE RECORD FOR AUDIT purposes.
(2) THE RECORD IS referenced BY other records. E.g., you cannot DELETE a Business Partner, IF there are invoices FOR this partner RECORD existing. You de-ACTIVATE THE Business Partner AND prevent that this RECORD IS used FOR future entries.','Y','Y','Y','N','N','N','N','Active',70,0,TO_TIMESTAMP('2008-01-09 23:32:18','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54260 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_TABLE (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,NAME,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53049,'4',TO_TIMESTAMP('2008-01-09 23:32:22','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','N','Y','N','N','N','ASP Process','L','ASP_Process',TO_TIMESTAMP('2008-01-09 23:32:22','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_TABLE_TRL (AD_LANGUAGE,AD_Table_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Table_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_TABLE t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53049 AND EXISTS (SELECT * FROM AD_TABLE_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Table_ID!=t.AD_Table_ID)
;

INSERT INTO AD_SEQUENCE (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,NAME,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53066,TO_TIMESTAMP('2008-01-09 23:32:23','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table ASP_Process',1,'Y','N','Y','Y','ASP_Process','N',1000000,TO_TIMESTAMP('2008-01-09 23:32:23','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54130,102,0,19,53049,'AD_Client_ID',TO_TIMESTAMP('2008-01-09 23:32:24','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Client_ID@','Client/Tenant for this installation.','D',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','N','N','N','Client',TO_TIMESTAMP('2008-01-09 23:32:24','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54130 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54131,113,0,19,53049,104,'AD_Org_ID',TO_TIMESTAMP('2008-01-09 23:32:28','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Org_ID@','Organizational entity within client','D',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','N','N','N','Organization',TO_TIMESTAMP('2008-01-09 23:32:28','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54131 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54132,117,0,19,53049,'AD_Process_ID',TO_TIMESTAMP('2008-01-09 23:32:32','YYYY-MM-DD HH24:MI:SS'),100,'Process or Report','D',22,'The Process field identifies a unique Process or Report in the system.','Y','N','N','N','N','Y','Y','N','N','N','N','Process',TO_TIMESTAMP('2008-01-09 23:32:32','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54132 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54133,53326,0,19,53049,'ASP_Level_ID',TO_TIMESTAMP('2008-01-09 23:32:32','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','N','Y','Y','N','N','N','N','ASP_Level_ID',TO_TIMESTAMP('2008-01-09 23:32:32','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54133 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54134,608,0,18,110,53049,'UpdatedBy',TO_TIMESTAMP('2008-01-09 23:32:33','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','D',22,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','N','N','N','Updated By',TO_TIMESTAMP('2008-01-09 23:32:33','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54134 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54135,245,0,16,53049,'Created',TO_TIMESTAMP('2008-01-09 23:32:34','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','D',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','N','N','N','Created',TO_TIMESTAMP('2008-01-09 23:32:34','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54135 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54136,246,0,18,110,53049,'CreatedBy',TO_TIMESTAMP('2008-01-09 23:32:35','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','D',22,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','N','N','N','Created By',TO_TIMESTAMP('2008-01-09 23:32:35','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54136 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54137,348,0,20,53049,'IsActive',TO_TIMESTAMP('2008-01-09 23:32:36','YYYY-MM-DD HH24:MI:SS'),100,'Y','The record is active in the system','D',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons FOR de-activating AND NOT DELETING records:
(1) THE SYSTEM requires THE RECORD FOR AUDIT purposes.
(2) THE RECORD IS referenced BY other records. E.g., you cannot DELETE a Business Partner, IF there are invoices FOR this partner RECORD existing. You de-ACTIVATE THE Business Partner AND prevent that this RECORD IS used FOR future entries.','Y','N','N','N','N','Y','N','N','N','N','Y','Active',TO_TIMESTAMP('2008-01-09 23:32:36','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54137 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54138,607,0,16,53049,'Updated',TO_TIMESTAMP('2008-01-09 23:32:37','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','D',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','N','N','N','Updated',TO_TIMESTAMP('2008-01-09 23:32:37','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54138 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54139,53327,0,17,53234,53049,'ASP_Status',TO_TIMESTAMP('2008-01-09 23:32:38','YYYY-MM-DD HH24:MI:SS'),100,'U','D',1,'Y','N','N','N','N','Y','N','N','N','N','Y','ASP_Status',TO_TIMESTAMP('2008-01-09 23:32:38','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54139 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_TAB (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,NAME,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53059,53049,53015,NULL,TO_TIMESTAMP('2008-01-09 23:32:45','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','N','N','Y','N','N','N','N','Process','N',70,2,TO_TIMESTAMP('2008-01-09 23:32:45','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_TAB_TRL (AD_LANGUAGE,AD_Tab_ID, CommitWarning,Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_TAB t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53059 AND EXISTS (SELECT * FROM AD_TAB_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54130,54261,0,53059,TO_TIMESTAMP('2008-01-09 23:32:46','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.',22,'D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_TIMESTAMP('2008-01-09 23:32:46','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54261 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54131,54262,0,53059,TO_TIMESTAMP('2008-01-09 23:32:49','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client',22,'D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_TIMESTAMP('2008-01-09 23:32:49','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54262 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54133,54263,0,53059,TO_TIMESTAMP('2008-01-09 23:32:51','YYYY-MM-DD HH24:MI:SS'),100,22,'D','Y','Y','Y','N','N','N','N','ASP_Level_ID',30,0,TO_TIMESTAMP('2008-01-09 23:32:51','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54263 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54132,54264,0,53059,TO_TIMESTAMP('2008-01-09 23:32:51','YYYY-MM-DD HH24:MI:SS'),100,'Process or Report',22,'D','The Process field identifies a unique Process or Report in the system.','Y','Y','Y','N','N','N','N','Process',40,0,TO_TIMESTAMP('2008-01-09 23:32:51','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54264 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54139,54265,0,53059,TO_TIMESTAMP('2008-01-09 23:32:55','YYYY-MM-DD HH24:MI:SS'),100,1,'D','Y','Y','Y','N','N','N','N','ASP_Status',50,0,TO_TIMESTAMP('2008-01-09 23:32:55','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54265 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54137,54266,0,53059,TO_TIMESTAMP('2008-01-09 23:32:56','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system',1,'D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons FOR de-activating AND NOT DELETING records:
(1) THE SYSTEM requires THE RECORD FOR AUDIT purposes.
(2) THE RECORD IS referenced BY other records. E.g., you cannot DELETE a Business Partner, IF there are invoices FOR this partner RECORD existing. You de-ACTIVATE THE Business Partner AND prevent that this RECORD IS used FOR future entries.','Y','Y','Y','N','N','N','N','Active',60,0,TO_TIMESTAMP('2008-01-09 23:32:56','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54266 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_TABLE (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,NAME,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53050,'4',TO_TIMESTAMP('2008-01-09 23:32:57','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','N','Y','N','N','N','ASP Process Parameter','L','ASP_Process_Para',TO_TIMESTAMP('2008-01-09 23:32:57','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_TABLE_TRL (AD_LANGUAGE,AD_Table_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Table_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_TABLE t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53050 AND EXISTS (SELECT * FROM AD_TABLE_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Table_ID!=t.AD_Table_ID)
;

INSERT INTO AD_SEQUENCE (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,NAME,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53067,TO_TIMESTAMP('2008-01-09 23:32:59','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table ASP_Process_Para',1,'Y','N','Y','Y','ASP_Process_Para','N',1000000,TO_TIMESTAMP('2008-01-09 23:32:59','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54140,102,0,19,53050,'AD_Client_ID',TO_TIMESTAMP('2008-01-09 23:32:59','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Client_ID@','Client/Tenant for this installation.','D',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','N','N','N','Client',TO_TIMESTAMP('2008-01-09 23:32:59','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54140 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54141,113,0,19,53050,104,'AD_Org_ID',TO_TIMESTAMP('2008-01-09 23:33:00','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Org_ID@','Organizational entity within client','D',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','N','N','N','Organization',TO_TIMESTAMP('2008-01-09 23:33:00','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54141 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54142,117,0,19,53050,163,'AD_Process_ID',TO_TIMESTAMP('2008-01-09 23:33:01','YYYY-MM-DD HH24:MI:SS'),100,'Process or Report','D',22,'The Process field identifies a unique Process or Report in the system.','Y','N','N','N','N','Y','Y','N','N','N','N','Process',TO_TIMESTAMP('2008-01-09 23:33:01','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54142 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54143,118,0,19,53050,186,'AD_Process_Para_ID',TO_TIMESTAMP('2008-01-09 23:33:05','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','N','N','Y','N','N','N','N','Process Parameter',TO_TIMESTAMP('2008-01-09 23:33:05','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54143 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54144,53326,0,19,53050,'ASP_Level_ID',TO_TIMESTAMP('2008-01-09 23:33:06','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','N','Y','Y','N','N','N','N','ASP_Level_ID',TO_TIMESTAMP('2008-01-09 23:33:06','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54144 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54145,608,0,18,110,53050,'UpdatedBy',TO_TIMESTAMP('2008-01-09 23:33:06','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','D',22,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','N','N','N','Updated By',TO_TIMESTAMP('2008-01-09 23:33:06','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54145 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54146,245,0,16,53050,'Created',TO_TIMESTAMP('2008-01-09 23:33:07','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','D',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','N','N','N','Created',TO_TIMESTAMP('2008-01-09 23:33:07','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54146 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54147,246,0,18,110,53050,'CreatedBy',TO_TIMESTAMP('2008-01-09 23:33:09','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','D',22,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','N','N','N','Created By',TO_TIMESTAMP('2008-01-09 23:33:09','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54147 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54148,348,0,20,53050,'IsActive',TO_TIMESTAMP('2008-01-09 23:33:10','YYYY-MM-DD HH24:MI:SS'),100,'Y','The record is active in the system','D',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons FOR de-activating AND NOT DELETING records:
(1) THE SYSTEM requires THE RECORD FOR AUDIT purposes.
(2) THE RECORD IS referenced BY other records. E.g., you cannot DELETE a Business Partner, IF there are invoices FOR this partner RECORD existing. You de-ACTIVATE THE Business Partner AND prevent that this RECORD IS used FOR future entries.','Y','N','N','N','N','Y','N','N','N','N','Y','Active',TO_TIMESTAMP('2008-01-09 23:33:10','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54148 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54149,607,0,16,53050,'Updated',TO_TIMESTAMP('2008-01-09 23:33:10','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','D',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','N','N','N','Updated',TO_TIMESTAMP('2008-01-09 23:33:10','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54149 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54150,53327,0,17,53234,53050,'ASP_Status',TO_TIMESTAMP('2008-01-09 23:33:11','YYYY-MM-DD HH24:MI:SS'),100,'U','D',1,'Y','N','N','N','N','Y','N','N','N','N','Y','ASP_Status',TO_TIMESTAMP('2008-01-09 23:33:11','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54150 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_TAB (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,NAME,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53060,53050,53015,NULL,TO_TIMESTAMP('2008-01-09 23:33:15','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','N','N','Y','N','N','N','N','Process Parameter','N',80,3,TO_TIMESTAMP('2008-01-09 23:33:15','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_TAB_TRL (AD_LANGUAGE,AD_Tab_ID, CommitWarning,Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_TAB t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53060 AND EXISTS (SELECT * FROM AD_TAB_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54140,54267,0,53060,TO_TIMESTAMP('2008-01-09 23:33:16','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.',22,'D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_TIMESTAMP('2008-01-09 23:33:16','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54267 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54141,54268,0,53060,TO_TIMESTAMP('2008-01-09 23:33:17','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client',22,'D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_TIMESTAMP('2008-01-09 23:33:17','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54268 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54144,54269,0,53060,TO_TIMESTAMP('2008-01-09 23:33:17','YYYY-MM-DD HH24:MI:SS'),100,22,'D','Y','Y','Y','N','N','Y','N','ASP_Level_ID',30,0,TO_TIMESTAMP('2008-01-09 23:33:17','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54269 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54142,54270,0,53060,TO_TIMESTAMP('2008-01-09 23:33:18','YYYY-MM-DD HH24:MI:SS'),100,'Process or Report',22,'D','The Process field identifies a unique Process or Report in the system.','Y','Y','Y','N','N','N','N','Process',40,0,TO_TIMESTAMP('2008-01-09 23:33:18','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54270 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54143,54271,0,53060,TO_TIMESTAMP('2008-01-09 23:33:21','YYYY-MM-DD HH24:MI:SS'),100,22,'D','Y','Y','Y','N','N','N','N','Process Parameter',50,0,TO_TIMESTAMP('2008-01-09 23:33:21','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54271 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54150,54272,0,53060,TO_TIMESTAMP('2008-01-09 23:33:22','YYYY-MM-DD HH24:MI:SS'),100,1,'D','Y','Y','Y','N','N','N','N','ASP_Status',60,0,TO_TIMESTAMP('2008-01-09 23:33:22','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54272 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54148,54273,0,53060,TO_TIMESTAMP('2008-01-09 23:33:22','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system',1,'D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons FOR de-activating AND NOT DELETING records:
(1) THE SYSTEM requires THE RECORD FOR AUDIT purposes.
(2) THE RECORD IS referenced BY other records. E.g., you cannot DELETE a Business Partner, IF there are invoices FOR this partner RECORD existing. You de-ACTIVATE THE Business Partner AND prevent that this RECORD IS used FOR future entries.','Y','Y','Y','N','N','N','N','Active',70,0,TO_TIMESTAMP('2008-01-09 23:33:22','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54273 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_TABLE (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,NAME,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53051,'4',TO_TIMESTAMP('2008-01-09 23:33:23','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','N','Y','N','N','N','ASP Form','L','ASP_Form',TO_TIMESTAMP('2008-01-09 23:33:23','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_TABLE_TRL (AD_LANGUAGE,AD_Table_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Table_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_TABLE t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53051 AND EXISTS (SELECT * FROM AD_TABLE_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Table_ID!=t.AD_Table_ID)
;

INSERT INTO AD_SEQUENCE (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,NAME,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53068,TO_TIMESTAMP('2008-01-09 23:33:24','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table ASP_Form',1,'Y','N','Y','Y','ASP_Form','N',1000000,TO_TIMESTAMP('2008-01-09 23:33:24','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54151,102,0,19,53051,'AD_Client_ID',TO_TIMESTAMP('2008-01-09 23:33:25','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Client_ID@','Client/Tenant for this installation.','D',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','N','N','N','Client',TO_TIMESTAMP('2008-01-09 23:33:25','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54151 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54152,1298,0,19,53051,'AD_Form_ID',TO_TIMESTAMP('2008-01-09 23:33:26','YYYY-MM-DD HH24:MI:SS'),100,'Special Form','D',22,'The Special Form field identifies a unique Special Form in the system.','Y','N','N','N','N','Y','Y','N','N','N','N','Special Form',TO_TIMESTAMP('2008-01-09 23:33:26','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54152 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54153,113,0,19,53051,104,'AD_Org_ID',TO_TIMESTAMP('2008-01-09 23:33:26','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Org_ID@','Organizational entity within client','D',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','N','N','N','Organization',TO_TIMESTAMP('2008-01-09 23:33:26','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54153 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54154,53326,0,19,53051,'ASP_Level_ID',TO_TIMESTAMP('2008-01-09 23:33:28','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','N','Y','Y','N','N','N','N','ASP_Level_ID',TO_TIMESTAMP('2008-01-09 23:33:28','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54154 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54155,608,0,18,110,53051,'UpdatedBy',TO_TIMESTAMP('2008-01-09 23:33:28','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','D',22,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','N','N','N','Updated By',TO_TIMESTAMP('2008-01-09 23:33:28','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54155 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54156,245,0,16,53051,'Created',TO_TIMESTAMP('2008-01-09 23:33:29','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','D',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','N','N','N','Created',TO_TIMESTAMP('2008-01-09 23:33:29','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54156 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54157,246,0,18,110,53051,'CreatedBy',TO_TIMESTAMP('2008-01-09 23:33:30','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','D',22,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','N','N','N','Created By',TO_TIMESTAMP('2008-01-09 23:33:30','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54157 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54158,348,0,20,53051,'IsActive',TO_TIMESTAMP('2008-01-09 23:33:31','YYYY-MM-DD HH24:MI:SS'),100,'Y','The record is active in the system','D',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons FOR de-activating AND NOT DELETING records:
(1) THE SYSTEM requires THE RECORD FOR AUDIT purposes.
(2) THE RECORD IS referenced BY other records. E.g., you cannot DELETE a Business Partner, IF there are invoices FOR this partner RECORD existing. You de-ACTIVATE THE Business Partner AND prevent that this RECORD IS used FOR future entries.','Y','N','N','N','N','Y','N','N','N','N','Y','Active',TO_TIMESTAMP('2008-01-09 23:33:31','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54158 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54159,607,0,16,53051,'Updated',TO_TIMESTAMP('2008-01-09 23:33:32','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','D',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','N','N','N','Updated',TO_TIMESTAMP('2008-01-09 23:33:32','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54159 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54160,53327,0,17,53234,53051,'ASP_Status',TO_TIMESTAMP('2008-01-09 23:33:33','YYYY-MM-DD HH24:MI:SS'),100,'U','D',1,'Y','N','N','N','N','Y','N','N','N','N','Y','ASP_Status',TO_TIMESTAMP('2008-01-09 23:33:33','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54160 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_TAB (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,NAME,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53061,53051,53015,NULL,TO_TIMESTAMP('2008-01-09 23:33:34','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','N','N','Y','N','N','N','N','Form','N',90,2,TO_TIMESTAMP('2008-01-09 23:33:34','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_TAB_TRL (AD_LANGUAGE,AD_Tab_ID, CommitWarning,Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_TAB t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53061 AND EXISTS (SELECT * FROM AD_TAB_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54151,54274,0,53061,TO_TIMESTAMP('2008-01-09 23:33:35','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.',22,'D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_TIMESTAMP('2008-01-09 23:33:35','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54274 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54153,54275,0,53061,TO_TIMESTAMP('2008-01-09 23:33:36','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client',22,'D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_TIMESTAMP('2008-01-09 23:33:36','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54275 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54154,54276,0,53061,TO_TIMESTAMP('2008-01-09 23:33:36','YYYY-MM-DD HH24:MI:SS'),100,22,'D','Y','Y','Y','N','N','N','N','ASP_Level_ID',30,0,TO_TIMESTAMP('2008-01-09 23:33:36','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54276 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54152,54277,0,53061,TO_TIMESTAMP('2008-01-09 23:33:37','YYYY-MM-DD HH24:MI:SS'),100,'Special Form',22,'D','The Special Form field identifies a unique Special Form in the system.','Y','Y','Y','N','N','N','N','Special Form',40,0,TO_TIMESTAMP('2008-01-09 23:33:37','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54277 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54160,54278,0,53061,TO_TIMESTAMP('2008-01-09 23:33:38','YYYY-MM-DD HH24:MI:SS'),100,1,'D','Y','Y','Y','N','N','N','N','ASP_Status',50,0,TO_TIMESTAMP('2008-01-09 23:33:38','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54278 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54158,54279,0,53061,TO_TIMESTAMP('2008-01-09 23:33:40','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system',1,'D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons FOR de-activating AND NOT DELETING records:
(1) THE SYSTEM requires THE RECORD FOR AUDIT purposes.
(2) THE RECORD IS referenced BY other records. E.g., you cannot DELETE a Business Partner, IF there are invoices FOR this partner RECORD existing. You de-ACTIVATE THE Business Partner AND prevent that this RECORD IS used FOR future entries.','Y','Y','Y','N','N','N','N','Active',60,0,TO_TIMESTAMP('2008-01-09 23:33:40','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54279 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_TABLE (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,NAME,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53052,'4',TO_TIMESTAMP('2008-01-09 23:33:44','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','N','Y','N','N','N','ASP Task','L','ASP_Task',TO_TIMESTAMP('2008-01-09 23:33:44','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_TABLE_TRL (AD_LANGUAGE,AD_Table_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Table_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_TABLE t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53052 AND EXISTS (SELECT * FROM AD_TABLE_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Table_ID!=t.AD_Table_ID)
;

INSERT INTO AD_SEQUENCE (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,NAME,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53069,TO_TIMESTAMP('2008-01-09 23:33:45','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table ASP_Task',1,'Y','N','Y','Y','ASP_Task','N',1000000,TO_TIMESTAMP('2008-01-09 23:33:45','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54161,102,0,19,53052,'AD_Client_ID',TO_TIMESTAMP('2008-01-09 23:33:46','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Client_ID@','Client/Tenant for this installation.','D',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','N','N','N','Client',TO_TIMESTAMP('2008-01-09 23:33:46','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54161 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54162,113,0,19,53052,104,'AD_Org_ID',TO_TIMESTAMP('2008-01-09 23:33:47','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Org_ID@','Organizational entity within client','D',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','N','N','N','Organization',TO_TIMESTAMP('2008-01-09 23:33:47','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54162 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54163,128,0,19,53052,'AD_Task_ID',TO_TIMESTAMP('2008-01-09 23:33:49','YYYY-MM-DD HH24:MI:SS'),100,'Operation System Task','D',22,'The Task field identifies a Operation System Task in the system.','Y','N','N','N','N','Y','Y','N','N','N','N','OS Task',TO_TIMESTAMP('2008-01-09 23:33:49','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54163 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54164,53326,0,19,53052,'ASP_Level_ID',TO_TIMESTAMP('2008-01-09 23:33:50','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','N','Y','Y','N','N','N','N','ASP_Level_ID',TO_TIMESTAMP('2008-01-09 23:33:50','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54164 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54165,608,0,18,110,53052,'UpdatedBy',TO_TIMESTAMP('2008-01-09 23:33:51','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','D',22,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','N','N','N','Updated By',TO_TIMESTAMP('2008-01-09 23:33:51','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54165 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54166,245,0,16,53052,'Created',TO_TIMESTAMP('2008-01-09 23:33:52','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','D',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','N','N','N','Created',TO_TIMESTAMP('2008-01-09 23:33:52','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54166 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54167,246,0,18,110,53052,'CreatedBy',TO_TIMESTAMP('2008-01-09 23:33:56','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','D',22,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','N','N','N','Created By',TO_TIMESTAMP('2008-01-09 23:33:56','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54167 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54168,348,0,20,53052,'IsActive',TO_TIMESTAMP('2008-01-09 23:34:00','YYYY-MM-DD HH24:MI:SS'),100,'Y','The record is active in the system','D',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons FOR de-activating AND NOT DELETING records:
(1) THE SYSTEM requires THE RECORD FOR AUDIT purposes.
(2) THE RECORD IS referenced BY other records. E.g., you cannot DELETE a Business Partner, IF there are invoices FOR this partner RECORD existing. You de-ACTIVATE THE Business Partner AND prevent that this RECORD IS used FOR future entries.','Y','N','N','N','N','Y','N','N','N','N','Y','Active',TO_TIMESTAMP('2008-01-09 23:34:00','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54168 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54169,607,0,16,53052,'Updated',TO_TIMESTAMP('2008-01-09 23:34:01','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','D',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','N','N','N','Updated',TO_TIMESTAMP('2008-01-09 23:34:01','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54169 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54170,53327,0,17,53234,53052,'ASP_Status',TO_TIMESTAMP('2008-01-09 23:34:05','YYYY-MM-DD HH24:MI:SS'),100,'U','D',1,'Y','N','N','N','N','Y','N','N','N','N','Y','ASP_Status',TO_TIMESTAMP('2008-01-09 23:34:05','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54170 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_TAB (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,NAME,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53062,53052,53015,NULL,TO_TIMESTAMP('2008-01-09 23:34:06','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','N','N','Y','N','N','N','N','Task','N',100,2,TO_TIMESTAMP('2008-01-09 23:34:06','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_TAB_TRL (AD_LANGUAGE,AD_Tab_ID, CommitWarning,Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_TAB t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53062 AND EXISTS (SELECT * FROM AD_TAB_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54161,54280,0,53062,TO_TIMESTAMP('2008-01-09 23:34:08','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.',22,'D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_TIMESTAMP('2008-01-09 23:34:08','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54280 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54162,54281,0,53062,TO_TIMESTAMP('2008-01-09 23:34:11','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client',22,'D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_TIMESTAMP('2008-01-09 23:34:11','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54281 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54164,54282,0,53062,TO_TIMESTAMP('2008-01-09 23:34:15','YYYY-MM-DD HH24:MI:SS'),100,22,'D','Y','Y','Y','N','N','N','N','ASP_Level_ID',30,0,TO_TIMESTAMP('2008-01-09 23:34:15','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54282 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54163,54283,0,53062,TO_TIMESTAMP('2008-01-09 23:34:16','YYYY-MM-DD HH24:MI:SS'),100,'Operation System Task',22,'D','The Task field identifies a Operation System Task in the system.','Y','Y','Y','N','N','N','N','OS Task',40,0,TO_TIMESTAMP('2008-01-09 23:34:16','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54283 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54170,54284,0,53062,TO_TIMESTAMP('2008-01-09 23:34:17','YYYY-MM-DD HH24:MI:SS'),100,1,'D','Y','Y','Y','N','N','N','N','ASP_Status',50,0,TO_TIMESTAMP('2008-01-09 23:34:17','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54284 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54168,54285,0,53062,TO_TIMESTAMP('2008-01-09 23:34:18','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system',1,'D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons FOR de-activating AND NOT DELETING records:
(1) THE SYSTEM requires THE RECORD FOR AUDIT purposes.
(2) THE RECORD IS referenced BY other records. E.g., you cannot DELETE a Business Partner, IF there are invoices FOR this partner RECORD existing. You de-ACTIVATE THE Business Partner AND prevent that this RECORD IS used FOR future entries.','Y','Y','Y','N','N','N','N','Active',60,0,TO_TIMESTAMP('2008-01-09 23:34:18','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54285 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_TABLE (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,NAME,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53053,'4',TO_TIMESTAMP('2008-01-09 23:34:22','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','N','Y','N','N','N','ASP Workflow','L','ASP_Workflow',TO_TIMESTAMP('2008-01-09 23:34:22','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_TABLE_TRL (AD_LANGUAGE,AD_Table_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Table_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_TABLE t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53053 AND EXISTS (SELECT * FROM AD_TABLE_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Table_ID!=t.AD_Table_ID)
;

INSERT INTO AD_SEQUENCE (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,NAME,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53070,TO_TIMESTAMP('2008-01-09 23:34:26','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table ASP_Workflow',1,'Y','N','Y','Y','ASP_Workflow','N',1000000,TO_TIMESTAMP('2008-01-09 23:34:26','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54171,102,0,19,53053,'AD_Client_ID',TO_TIMESTAMP('2008-01-09 23:34:27','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Client_ID@','Client/Tenant for this installation.','D',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','N','N','N','Client',TO_TIMESTAMP('2008-01-09 23:34:27','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54171 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54172,113,0,19,53053,104,'AD_Org_ID',TO_TIMESTAMP('2008-01-09 23:34:28','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Org_ID@','Organizational entity within client','D',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','N','N','N','Organization',TO_TIMESTAMP('2008-01-09 23:34:28','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54172 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_VAL_RULE (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,NAME,TYPE,Updated,UpdatedBy) VALUES (0,0,52006,'AD_Workflow.WorkflowType = ''G''',TO_TIMESTAMP('2008-01-09 23:34:28','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','General Workflows','S',TO_TIMESTAMP('2008-01-09 23:34:28','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54173,144,0,19,53053,52006,'AD_Workflow_ID',TO_TIMESTAMP('2008-01-09 23:34:34','YYYY-MM-DD HH24:MI:SS'),100,'Workflow or combination of tasks','D',22,'The Workflow field identifies a unique Workflow in the system.','Y','N','N','N','N','Y','Y','N','N','N','N','Workflow',TO_TIMESTAMP('2008-01-09 23:34:34','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54173 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54174,53326,0,19,53053,'ASP_Level_ID',TO_TIMESTAMP('2008-01-09 23:34:34','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','N','Y','Y','N','N','N','N','ASP_Level_ID',TO_TIMESTAMP('2008-01-09 23:34:34','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54174 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54175,608,0,18,110,53053,'UpdatedBy',TO_TIMESTAMP('2008-01-09 23:34:36','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','D',22,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','N','N','N','Updated By',TO_TIMESTAMP('2008-01-09 23:34:36','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54175 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54176,245,0,16,53053,'Created',TO_TIMESTAMP('2008-01-09 23:34:37','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','D',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','N','N','N','Created',TO_TIMESTAMP('2008-01-09 23:34:37','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54176 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54177,246,0,18,110,53053,'CreatedBy',TO_TIMESTAMP('2008-01-09 23:34:38','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','D',22,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','N','N','N','Created By',TO_TIMESTAMP('2008-01-09 23:34:38','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54177 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54178,348,0,20,53053,'IsActive',TO_TIMESTAMP('2008-01-09 23:34:38','YYYY-MM-DD HH24:MI:SS'),100,'Y','The record is active in the system','D',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons FOR de-activating AND NOT DELETING records:
(1) THE SYSTEM requires THE RECORD FOR AUDIT purposes.
(2) THE RECORD IS referenced BY other records. E.g., you cannot DELETE a Business Partner, IF there are invoices FOR this partner RECORD existing. You de-ACTIVATE THE Business Partner AND prevent that this RECORD IS used FOR future entries.','Y','N','N','N','N','Y','N','N','N','N','Y','Active',TO_TIMESTAMP('2008-01-09 23:34:38','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54178 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54179,607,0,16,53053,'Updated',TO_TIMESTAMP('2008-01-09 23:34:39','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','D',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','N','N','N','Updated',TO_TIMESTAMP('2008-01-09 23:34:39','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54179 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54180,53327,0,17,53234,53053,'ASP_Status',TO_TIMESTAMP('2008-01-09 23:34:40','YYYY-MM-DD HH24:MI:SS'),100,'U','D',1,'Y','N','N','N','N','Y','N','N','N','N','Y','ASP_Status',TO_TIMESTAMP('2008-01-09 23:34:40','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54180 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_TAB (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,NAME,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53063,53053,53015,NULL,TO_TIMESTAMP('2008-01-09 23:34:41','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','N','N','Y','N','N','N','N','Workflow','N',110,2,TO_TIMESTAMP('2008-01-09 23:34:41','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_TAB_TRL (AD_LANGUAGE,AD_Tab_ID, CommitWarning,Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_TAB t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53063 AND EXISTS (SELECT * FROM AD_TAB_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54171,54286,0,53063,TO_TIMESTAMP('2008-01-09 23:34:41','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.',22,'D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_TIMESTAMP('2008-01-09 23:34:41','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54286 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54172,54287,0,53063,TO_TIMESTAMP('2008-01-09 23:34:43','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client',22,'D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_TIMESTAMP('2008-01-09 23:34:43','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54287 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54174,54288,0,53063,TO_TIMESTAMP('2008-01-09 23:34:46','YYYY-MM-DD HH24:MI:SS'),100,22,'D','Y','Y','Y','N','N','N','N','ASP_Level_ID',30,0,TO_TIMESTAMP('2008-01-09 23:34:46','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54288 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54173,54289,0,53063,TO_TIMESTAMP('2008-01-09 23:34:47','YYYY-MM-DD HH24:MI:SS'),100,'Workflow or combination of tasks',22,'D','The Workflow field identifies a unique Workflow in the system.','Y','Y','Y','N','N','N','N','Workflow',40,0,TO_TIMESTAMP('2008-01-09 23:34:47','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54289 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54180,54290,0,53063,TO_TIMESTAMP('2008-01-09 23:34:48','YYYY-MM-DD HH24:MI:SS'),100,1,'D','Y','Y','Y','N','N','N','N','ASP_Status',50,0,TO_TIMESTAMP('2008-01-09 23:34:48','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54290 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54178,54291,0,53063,TO_TIMESTAMP('2008-01-09 23:34:49','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system',1,'D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons FOR de-activating AND NOT DELETING records:
(1) THE SYSTEM requires THE RECORD FOR AUDIT purposes.
(2) THE RECORD IS referenced BY other records. E.g., you cannot DELETE a Business Partner, IF there are invoices FOR this partner RECORD existing. You de-ACTIVATE THE Business Partner AND prevent that this RECORD IS used FOR future entries.','Y','Y','Y','N','N','N','N','Active',60,0,TO_TIMESTAMP('2008-01-09 23:34:49','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54291 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_TABLE (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,NAME,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53054,'4',TO_TIMESTAMP('2008-01-09 23:34:49','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','N','Y','N','N','N','ASP Module','L','ASP_Module',TO_TIMESTAMP('2008-01-09 23:34:49','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_TABLE_TRL (AD_LANGUAGE,AD_Table_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Table_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_TABLE t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53054 AND EXISTS (SELECT * FROM AD_TABLE_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Table_ID!=t.AD_Table_ID)
;

INSERT INTO AD_SEQUENCE (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,NAME,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53071,TO_TIMESTAMP('2008-01-09 23:34:56','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table ASP_Module',1,'Y','N','Y','Y','ASP_Module','N',1000000,TO_TIMESTAMP('2008-01-09 23:34:56','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,SeqNo,Updated,UpdatedBy,VERSION) VALUES (0,54181,469,0,10,53054,'Name',TO_TIMESTAMP('2008-01-09 23:34:58','YYYY-MM-DD HH24:MI:SS'),100,'Alphanumeric identifier of the entity','D',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','N','N','Y','Name',1,TO_TIMESTAMP('2008-01-09 23:34:58','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54181 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54182,113,0,19,53054,104,'AD_Org_ID',TO_TIMESTAMP('2008-01-09 23:34:59','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Org_ID@','Organizational entity within client','D',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','N','N','N','Organization',TO_TIMESTAMP('2008-01-09 23:34:59','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54182 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_ELEMENT (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,NAME,PrintName,Updated,UpdatedBy) VALUES (0,53329,0,'ASP_Module_ID',TO_TIMESTAMP('2008-01-09 23:34:59','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','ASP_Module_ID','ASP_Module_ID',TO_TIMESTAMP('2008-01-09 23:34:59','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_ELEMENT_TRL (AD_LANGUAGE,AD_Element_ID, Description,Help,NAME,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Element_ID, t.Description,t.Help,t.NAME,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_ELEMENT t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53329 AND EXISTS (SELECT * FROM AD_ELEMENT_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Element_ID!=t.AD_Element_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54183,53329,0,13,53054,'ASP_Module_ID',TO_TIMESTAMP('2008-01-09 23:34:59','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','Y','Y','N','N','N','N','N','ASP_Module_ID',TO_TIMESTAMP('2008-01-09 23:34:59','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54183 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54184,245,0,16,53054,'Created',TO_TIMESTAMP('2008-01-09 23:35:01','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','D',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','N','N','N','Created',TO_TIMESTAMP('2008-01-09 23:35:01','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54184 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54185,246,0,18,110,53054,'CreatedBy',TO_TIMESTAMP('2008-01-09 23:35:02','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','D',22,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','N','N','N','Created By',TO_TIMESTAMP('2008-01-09 23:35:02','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54185 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54186,620,0,10,53054,'Value',TO_TIMESTAMP('2008-01-09 23:35:05','YYYY-MM-DD HH24:MI:SS'),100,'Search key for the record in the format required - must be unique','D',40,'A search key allows you a fast method of finding a particular record.
IF you leave THE search KEY empty, THE SYSTEM automatically creates a NUMERIC NUMBER.  THE document SEQUENCE used FOR this fallback NUMBER IS DEFINED IN THE "Maintain Sequence" window WITH THE NAME "DocumentNo_<TableName>", WHERE TableName IS THE actual NAME OF THE TABLE (e.g. C_ORDER).','Y','N','N','N','N','Y','N','N','N','N','Y','Search KEY',TO_TIMESTAMP('2008-01-09 23:35:05','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54186 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54187,326,0,14,53054,'Help',TO_TIMESTAMP('2008-01-09 23:35:06','YYYY-MM-DD HH24:MI:SS'),100,'Comment or Hint','D',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','N','N','Y','Comment/Help',TO_TIMESTAMP('2008-01-09 23:35:06','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54187 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54188,348,0,20,53054,'IsActive',TO_TIMESTAMP('2008-01-09 23:35:07','YYYY-MM-DD HH24:MI:SS'),100,'Y','The record is active in the system','D',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons FOR de-activating AND NOT DELETING records:
(1) THE SYSTEM requires THE RECORD FOR AUDIT purposes.
(2) THE RECORD IS referenced BY other records. E.g., you cannot DELETE a Business Partner, IF there are invoices FOR this partner RECORD existing. You de-ACTIVATE THE Business Partner AND prevent that this RECORD IS used FOR future entries.','Y','N','N','N','N','Y','N','N','N','N','Y','Active',TO_TIMESTAMP('2008-01-09 23:35:07','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54188 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54189,607,0,16,53054,'Updated',TO_TIMESTAMP('2008-01-09 23:35:08','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','D',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','N','N','N','Updated',TO_TIMESTAMP('2008-01-09 23:35:08','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54189 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54190,608,0,18,110,53054,'UpdatedBy',TO_TIMESTAMP('2008-01-09 23:35:08','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','D',22,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','N','N','N','Updated By',TO_TIMESTAMP('2008-01-09 23:35:08','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54190 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54191,102,0,19,53054,'AD_Client_ID',TO_TIMESTAMP('2008-01-09 23:35:09','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Client_ID@','Client/Tenant for this installation.','D',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','N','N','N','Client',TO_TIMESTAMP('2008-01-09 23:35:09','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54191 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54192,275,0,10,53054,'Description',TO_TIMESTAMP('2008-01-09 23:35:13','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record','D',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','N','N','Y','Description',TO_TIMESTAMP('2008-01-09 23:35:13','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54192 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_TAB (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,NAME,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53064,53054,53015,NULL,TO_TIMESTAMP('2008-01-09 23:35:16','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','N','N','Y','N','Y','N','N','Module','N',10,0,TO_TIMESTAMP('2008-01-09 23:35:16','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_TAB_TRL (AD_LANGUAGE,AD_Tab_ID, CommitWarning,Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_TAB t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53064 AND EXISTS (SELECT * FROM AD_TAB_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54183,54292,0,53064,TO_TIMESTAMP('2008-01-09 23:35:16','YYYY-MM-DD HH24:MI:SS'),100,22,'D','Y','Y','N','N','N','N','N','ASP_Module_ID',0,0,TO_TIMESTAMP('2008-01-09 23:35:16','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54292 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54191,54293,0,53064,TO_TIMESTAMP('2008-01-09 23:35:17','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.',20,'D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_TIMESTAMP('2008-01-09 23:35:17','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54293 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54182,54294,0,53064,TO_TIMESTAMP('2008-01-09 23:35:21','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client',20,'D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_TIMESTAMP('2008-01-09 23:35:21','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54294 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54186,54295,0,53064,TO_TIMESTAMP('2008-01-09 23:35:22','YYYY-MM-DD HH24:MI:SS'),100,'Search key for the record in the format required - must be unique',20,'D','A search key allows you a fast method of finding a particular record.
IF you leave THE search KEY empty, THE SYSTEM automatically creates a NUMERIC NUMBER.  THE document SEQUENCE used FOR this fallback NUMBER IS DEFINED IN THE "Maintain Sequence" window WITH THE NAME "DocumentNo_<TableName>", WHERE TableName IS THE actual NAME OF THE TABLE (e.g. C_ORDER).','Y','Y','Y','N','N','N','N','Search KEY',30,0,TO_TIMESTAMP('2008-01-09 23:35:22','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54295 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54181,54296,0,53064,TO_TIMESTAMP('2008-01-09 23:35:23','YYYY-MM-DD HH24:MI:SS'),100,'Alphanumeric identifier of the entity',40,'D','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',40,0,TO_TIMESTAMP('2008-01-09 23:35:23','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54296 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54192,54297,0,53064,TO_TIMESTAMP('2008-01-09 23:35:24','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record',40,'D','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',50,0,TO_TIMESTAMP('2008-01-09 23:35:24','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54297 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54187,54298,0,53064,TO_TIMESTAMP('2008-01-09 23:35:25','YYYY-MM-DD HH24:MI:SS'),100,'Comment or Hint',40,'D','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','Comment/Help',60,0,TO_TIMESTAMP('2008-01-09 23:35:25','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54298 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54188,54299,0,53064,TO_TIMESTAMP('2008-01-09 23:35:25','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system',1,'D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons FOR de-activating AND NOT DELETING records:
(1) THE SYSTEM requires THE RECORD FOR AUDIT purposes.
(2) THE RECORD IS referenced BY other records. E.g., you cannot DELETE a Business Partner, IF there are invoices FOR this partner RECORD existing. You de-ACTIVATE THE Business Partner AND prevent that this RECORD IS used FOR future entries.','Y','Y','Y','N','N','N','N','Active',70,0,TO_TIMESTAMP('2008-01-09 23:35:25','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54299 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_TABLE (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,NAME,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53055,'4',TO_TIMESTAMP('2008-01-09 23:35:27','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','N','Y','N','N','N','ASP Level','L','ASP_Level',TO_TIMESTAMP('2008-01-09 23:35:27','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_TABLE_TRL (AD_LANGUAGE,AD_Table_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Table_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_TABLE t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53055 AND EXISTS (SELECT * FROM AD_TABLE_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Table_ID!=t.AD_Table_ID)
;

INSERT INTO AD_SEQUENCE (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,NAME,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53072,TO_TIMESTAMP('2008-01-09 23:35:28','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table ASP_Level',1,'Y','N','Y','Y','ASP_Level','N',1000000,TO_TIMESTAMP('2008-01-09 23:35:28','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_PROCESS (AD_Client_ID,AD_Org_ID,AD_Process_ID,AccessLevel,Classname,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,NAME,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,VALUE,WorkflowValue) VALUES (0,0,53067,'4','org.adempiere.process.ASPGenerateLevel',TO_TIMESTAMP('2008-01-09 23:35:29','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','N','N','ASP Generate Level','Y',0,0,TO_TIMESTAMP('2008-01-09 23:35:29','YYYY-MM-DD HH24:MI:SS'),100,'ASP Generate Level',NULL)
;

INSERT INTO AD_PROCESS_TRL (AD_LANGUAGE,AD_Process_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Process_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_PROCESS t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53067 AND EXISTS (SELECT * FROM AD_PROCESS_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Process_ID!=t.AD_Process_ID)
;

INSERT INTO AD_PROCESS_PARA (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,NAME,SeqNo,Updated,UpdatedBy) VALUES (0,53327,0,53067,53126,17,53234,'ASP_Status',TO_TIMESTAMP('2008-01-09 23:35:33','YYYY-MM-DD HH24:MI:SS'),100,'U','D',0,'Y','Y','Y','N','ASP Status',10,TO_TIMESTAMP('2008-01-09 23:35:33','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_PROCESS_PARA_TRL (AD_LANGUAGE,AD_Process_Para_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Process_Para_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_PROCESS_PARA t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53126 AND EXISTS (SELECT * FROM AD_PROCESS_PARA_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

INSERT INTO AD_PROCESS_PARA (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,NAME,SeqNo,Updated,UpdatedBy) VALUES (0,0,53067,53127,20,'IsGenerateFields',TO_TIMESTAMP('2008-01-09 23:35:35','YYYY-MM-DD HH24:MI:SS'),100,'N','D',0,'Y','Y','Y','N','Generate Fields',30,TO_TIMESTAMP('2008-01-09 23:35:35','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_PROCESS_PARA_TRL (AD_LANGUAGE,AD_Process_Para_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Process_Para_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_PROCESS_PARA t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53127 AND EXISTS (SELECT * FROM AD_PROCESS_PARA_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54193,524,0,53067,28,53055,'Processing',TO_TIMESTAMP('2008-01-09 23:35:36','YYYY-MM-DD HH24:MI:SS'),100,'D',1,'Y','N','N','N','N','N','N','N','N','N','Y','Process Now',TO_TIMESTAMP('2008-01-09 23:35:36','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54193 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,SeqNo,Updated,UpdatedBy,VERSION) VALUES (0,54194,469,0,10,53055,'Name',TO_TIMESTAMP('2008-01-09 23:35:37','YYYY-MM-DD HH24:MI:SS'),100,'Alphanumeric identifier of the entity','D',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','N','N','Y','Name',1,TO_TIMESTAMP('2008-01-09 23:35:37','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54194 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54195,53326,0,13,53055,'ASP_Level_ID',TO_TIMESTAMP('2008-01-09 23:35:38','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','Y','Y','N','N','N','N','N','ASP_Level_ID',TO_TIMESTAMP('2008-01-09 23:35:38','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54195 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54196,53329,0,19,53055,'ASP_Module_ID',TO_TIMESTAMP('2008-01-09 23:35:39','YYYY-MM-DD HH24:MI:SS'),100,'D',1,'Y','N','N','N','N','Y','Y','N','N','N','N','ASP_Module_ID',TO_TIMESTAMP('2008-01-09 23:35:39','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54196 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54197,245,0,16,53055,'Created',TO_TIMESTAMP('2008-01-09 23:35:43','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','D',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','N','N','N','Created',TO_TIMESTAMP('2008-01-09 23:35:43','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54197 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54198,246,0,18,110,53055,'CreatedBy',TO_TIMESTAMP('2008-01-09 23:35:47','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','D',22,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','N','N','N','Created By',TO_TIMESTAMP('2008-01-09 23:35:47','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54198 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54199,620,0,10,53055,'Value',TO_TIMESTAMP('2008-01-09 23:35:48','YYYY-MM-DD HH24:MI:SS'),100,'Search key for the record in the format required - must be unique','D',40,'A search key allows you a fast method of finding a particular record.
IF you leave THE search KEY empty, THE SYSTEM automatically creates a NUMERIC NUMBER.  THE document SEQUENCE used FOR this fallback NUMBER IS DEFINED IN THE "Maintain Sequence" window WITH THE NAME "DocumentNo_<TableName>", WHERE TableName IS THE actual NAME OF THE TABLE (e.g. C_ORDER).','Y','N','N','N','N','Y','N','N','N','N','Y','Search KEY',TO_TIMESTAMP('2008-01-09 23:35:48','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54199 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54200,326,0,14,53055,'Help',TO_TIMESTAMP('2008-01-09 23:35:49','YYYY-MM-DD HH24:MI:SS'),100,'Comment or Hint','D',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','N','N','Y','Comment/Help',TO_TIMESTAMP('2008-01-09 23:35:49','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54200 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54201,348,0,20,53055,'IsActive',TO_TIMESTAMP('2008-01-09 23:35:53','YYYY-MM-DD HH24:MI:SS'),100,'Y','The record is active in the system','D',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons FOR de-activating AND NOT DELETING records:
(1) THE SYSTEM requires THE RECORD FOR AUDIT purposes.
(2) THE RECORD IS referenced BY other records. E.g., you cannot DELETE a Business Partner, IF there are invoices FOR this partner RECORD existing. You de-ACTIVATE THE Business Partner AND prevent that this RECORD IS used FOR future entries.','Y','N','N','N','N','Y','N','N','N','N','Y','Active',TO_TIMESTAMP('2008-01-09 23:35:53','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54201 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54202,607,0,16,53055,'Updated',TO_TIMESTAMP('2008-01-09 23:35:54','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','D',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','N','N','N','Updated',TO_TIMESTAMP('2008-01-09 23:35:54','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54202 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54203,608,0,18,110,53055,'UpdatedBy',TO_TIMESTAMP('2008-01-09 23:35:57','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','D',22,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','N','N','N','Updated By',TO_TIMESTAMP('2008-01-09 23:35:57','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54203 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54204,113,0,19,53055,104,'AD_Org_ID',TO_TIMESTAMP('2008-01-09 23:35:58','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Org_ID@','Organizational entity within client','D',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','N','N','N','Organization',TO_TIMESTAMP('2008-01-09 23:35:58','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54204 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54205,102,0,19,53055,'AD_Client_ID',TO_TIMESTAMP('2008-01-09 23:35:59','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Client_ID@','Client/Tenant for this installation.','D',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','N','N','N','Client',TO_TIMESTAMP('2008-01-09 23:35:59','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54205 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54206,275,0,10,53055,'Description',TO_TIMESTAMP('2008-01-09 23:36:00','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record','D',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','N','N','Y','Description',TO_TIMESTAMP('2008-01-09 23:36:00','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54206 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_TAB (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,NAME,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53065,53055,53015,NULL,TO_TIMESTAMP('2008-01-09 23:36:01','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','N','N','Y','N','Y','N','N','Level','N',20,1,TO_TIMESTAMP('2008-01-09 23:36:01','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_TAB_TRL (AD_LANGUAGE,AD_Tab_ID, CommitWarning,Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_TAB t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53065 AND EXISTS (SELECT * FROM AD_TAB_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54195,54300,0,53065,TO_TIMESTAMP('2008-01-09 23:36:02','YYYY-MM-DD HH24:MI:SS'),100,22,'D','Y','Y','N','N','N','N','N','ASP_Level_ID',0,0,TO_TIMESTAMP('2008-01-09 23:36:02','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54300 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54205,54301,0,53065,TO_TIMESTAMP('2008-01-09 23:36:03','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.',20,'D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_TIMESTAMP('2008-01-09 23:36:03','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54301 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54204,54302,0,53065,TO_TIMESTAMP('2008-01-09 23:36:04','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client',20,'D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_TIMESTAMP('2008-01-09 23:36:04','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54302 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54196,54303,0,53065,TO_TIMESTAMP('2008-01-09 23:36:05','YYYY-MM-DD HH24:MI:SS'),100,20,'D','Y','Y','Y','N','N','N','N','ASP_Module_ID',30,0,TO_TIMESTAMP('2008-01-09 23:36:05','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54303 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54199,54304,0,53065,TO_TIMESTAMP('2008-01-09 23:36:06','YYYY-MM-DD HH24:MI:SS'),100,'Search key for the record in the format required - must be unique',40,'D','A search key allows you a fast method of finding a particular record.
IF you leave THE search KEY empty, THE SYSTEM automatically creates a NUMERIC NUMBER.  THE document SEQUENCE used FOR this fallback NUMBER IS DEFINED IN THE "Maintain Sequence" window WITH THE NAME "DocumentNo_<TableName>", WHERE TableName IS THE actual NAME OF THE TABLE (e.g. C_ORDER).','Y','Y','Y','N','N','N','N','Search KEY',40,0,TO_TIMESTAMP('2008-01-09 23:36:06','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54304 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54194,54305,0,53065,TO_TIMESTAMP('2008-01-09 23:36:07','YYYY-MM-DD HH24:MI:SS'),100,'Alphanumeric identifier of the entity',40,'D','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',50,0,TO_TIMESTAMP('2008-01-09 23:36:07','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54305 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54206,54306,0,53065,TO_TIMESTAMP('2008-01-09 23:36:11','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record',40,'D','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',60,0,TO_TIMESTAMP('2008-01-09 23:36:11','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54306 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54200,54307,0,53065,TO_TIMESTAMP('2008-01-09 23:36:11','YYYY-MM-DD HH24:MI:SS'),100,'Comment or Hint',40,'D','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','Comment/Help',70,0,TO_TIMESTAMP('2008-01-09 23:36:11','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54307 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54193,54308,0,53065,TO_TIMESTAMP('2008-01-09 23:36:12','YYYY-MM-DD HH24:MI:SS'),100,1,'D','Y','Y','Y','N','N','N','N','Process Now',80,0,TO_TIMESTAMP('2008-01-09 23:36:12','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54308 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54201,54309,0,53065,TO_TIMESTAMP('2008-01-09 23:36:13','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system',1,'D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons FOR de-activating AND NOT DELETING records:
(1) THE SYSTEM requires THE RECORD FOR AUDIT purposes.
(2) THE RECORD IS referenced BY other records. E.g., you cannot DELETE a Business Partner, IF there are invoices FOR this partner RECORD existing. You de-ACTIVATE THE Business Partner AND prevent that this RECORD IS used FOR future entries.','Y','Y','Y','N','N','N','N','Active',90,0,TO_TIMESTAMP('2008-01-09 23:36:13','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54309 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_WINDOW (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,NAME,Processing,Updated,UpdatedBy,WindowType) VALUES (0,0,53016,TO_TIMESTAMP('2008-01-09 23:36:14','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Y','N','Y','ASP Subscribed Modules','N',TO_TIMESTAMP('2008-01-09 23:36:14','YYYY-MM-DD HH24:MI:SS'),100,'M')
;

INSERT INTO AD_WINDOW_TRL (AD_LANGUAGE,AD_Window_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Window_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_WINDOW t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53016 AND EXISTS (SELECT * FROM AD_WINDOW_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Window_ID!=t.AD_Window_ID)
;

INSERT INTO AD_WINDOW_ACCESS (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,0,53016,TO_TIMESTAMP('2008-01-09 23:36:15','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_TIMESTAMP('2008-01-09 23:36:15','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_WINDOW_ACCESS (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,102,53016,TO_TIMESTAMP('2008-01-09 23:36:15','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_TIMESTAMP('2008-01-09 23:36:15','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_WINDOW_ACCESS (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,103,53016,TO_TIMESTAMP('2008-01-09 23:36:15','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_TIMESTAMP('2008-01-09 23:36:15','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_WINDOW_ACCESS (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,50001,53016,TO_TIMESTAMP('2008-01-09 23:36:15','YYYY-MM-DD HH24:MI:SS'),100,'Y',TO_TIMESTAMP('2008-01-09 23:36:15','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_TABLE (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,NAME,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53056,'2',TO_TIMESTAMP('2008-01-09 23:36:15','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','N','Y','N','N','N','ASP Client Level','L','ASP_ClientLevel',TO_TIMESTAMP('2008-01-09 23:36:15','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_TABLE_TRL (AD_LANGUAGE,AD_Table_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Table_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_TABLE t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53056 AND EXISTS (SELECT * FROM AD_TABLE_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Table_ID!=t.AD_Table_ID)
;

INSERT INTO AD_SEQUENCE (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,NAME,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53073,TO_TIMESTAMP('2008-01-09 23:36:16','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table ASP_ClientLevel',1,'Y','N','Y','Y','ASP_ClientLevel','N',1000000,TO_TIMESTAMP('2008-01-09 23:36:16','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54207,102,0,19,53056,'AD_Client_ID',TO_TIMESTAMP('2008-01-09 23:36:20','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Client_ID@','Client/Tenant for this installation.','D',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','N','N','N','Client',TO_TIMESTAMP('2008-01-09 23:36:20','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54207 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54208,113,0,19,53056,104,'AD_Org_ID',TO_TIMESTAMP('2008-01-09 23:36:20','YYYY-MM-DD HH24:MI:SS'),100,'0','Organizational entity within client','D',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','N','N','N','Organization',TO_TIMESTAMP('2008-01-09 23:36:20','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54208 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_ELEMENT (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,NAME,PrintName,Updated,UpdatedBy) VALUES (0,53330,0,'ASP_ClientLevel_ID',TO_TIMESTAMP('2008-01-09 23:36:22','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','ASP_ClientLevel_ID','ASP_ClientLevel_ID',TO_TIMESTAMP('2008-01-09 23:36:22','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_ELEMENT_TRL (AD_LANGUAGE,AD_Element_ID, Description,Help,NAME,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Element_ID, t.Description,t.Help,t.NAME,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_ELEMENT t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53330 AND EXISTS (SELECT * FROM AD_ELEMENT_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Element_ID!=t.AD_Element_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54209,53330,0,13,53056,'ASP_ClientLevel_ID',TO_TIMESTAMP('2008-01-09 23:36:22','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','Y','Y','N','N','N','N','N','ASP_ClientLevel_ID',TO_TIMESTAMP('2008-01-09 23:36:22','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54209 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_VAL_RULE (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,NAME,TYPE,Updated,UpdatedBy) VALUES (0,0,52007,'ASP_Module_ID=@ASP_Module_ID@',TO_TIMESTAMP('2008-01-09 23:36:26','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','ASP_SameModule','S',TO_TIMESTAMP('2008-01-09 23:36:26','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54210,53326,0,19,53056,52007,'ASP_Level_ID',TO_TIMESTAMP('2008-01-09 23:36:27','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','N','N','N','N','Y','N','N','N','N','Y','ASP_Level_ID',TO_TIMESTAMP('2008-01-09 23:36:27','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54210 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54211,53329,0,19,53056,'ASP_Module_ID',TO_TIMESTAMP('2008-01-09 23:36:28','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','N','N','N','N','Y','N','N','N','N','Y','ASP_Module_ID',TO_TIMESTAMP('2008-01-09 23:36:28','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54211 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54212,608,0,18,110,53056,'UpdatedBy',TO_TIMESTAMP('2008-01-09 23:36:29','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','D',22,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','N','N','N','Updated By',TO_TIMESTAMP('2008-01-09 23:36:29','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54212 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54213,246,0,18,110,53056,'CreatedBy',TO_TIMESTAMP('2008-01-09 23:36:30','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','D',22,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','N','N','N','Created By',TO_TIMESTAMP('2008-01-09 23:36:30','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54213 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54214,326,0,14,53056,'Help',TO_TIMESTAMP('2008-01-09 23:36:31','YYYY-MM-DD HH24:MI:SS'),100,'Comment or Hint','D',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','N','N','Y','Comment/Help',TO_TIMESTAMP('2008-01-09 23:36:31','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54214 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54215,348,0,20,53056,'IsActive',TO_TIMESTAMP('2008-01-09 23:36:32','YYYY-MM-DD HH24:MI:SS'),100,'Y','The record is active in the system','D',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons FOR de-activating AND NOT DELETING records:
(1) THE SYSTEM requires THE RECORD FOR AUDIT purposes.
(2) THE RECORD IS referenced BY other records. E.g., you cannot DELETE a Business Partner, IF there are invoices FOR this partner RECORD existing. You de-ACTIVATE THE Business Partner AND prevent that this RECORD IS used FOR future entries.','Y','N','N','N','N','Y','N','N','N','N','Y','Active',TO_TIMESTAMP('2008-01-09 23:36:32','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54215 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54216,607,0,16,53056,'Updated',TO_TIMESTAMP('2008-01-09 23:36:33','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','D',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','N','N','N','Updated',TO_TIMESTAMP('2008-01-09 23:36:33','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54216 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54217,245,0,16,53056,'Created',TO_TIMESTAMP('2008-01-09 23:36:33','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','D',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','N','N','N','Created',TO_TIMESTAMP('2008-01-09 23:36:33','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54217 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_TAB (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,NAME,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53066,53056,53016,NULL,TO_TIMESTAMP('2008-01-09 23:36:37','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','N','N','Y','N','Y','N','N','Client Level','N',10,0,TO_TIMESTAMP('2008-01-09 23:36:37','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_TAB_TRL (AD_LANGUAGE,AD_Tab_ID, CommitWarning,Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_TAB t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53066 AND EXISTS (SELECT * FROM AD_TAB_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54209,54310,0,53066,TO_TIMESTAMP('2008-01-09 23:36:39','YYYY-MM-DD HH24:MI:SS'),100,22,'D','Y','Y','N','N','N','N','N','ASP_ClientLevel_ID',0,0,TO_TIMESTAMP('2008-01-09 23:36:39','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54310 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54207,54311,0,53066,TO_TIMESTAMP('2008-01-09 23:36:39','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.',20,'D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_TIMESTAMP('2008-01-09 23:36:39','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54311 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54208,54312,0,53066,TO_TIMESTAMP('2008-01-09 23:36:40','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client',20,'D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_TIMESTAMP('2008-01-09 23:36:40','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54312 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54211,54313,0,53066,TO_TIMESTAMP('2008-01-09 23:36:42','YYYY-MM-DD HH24:MI:SS'),100,20,'D','Y','Y','Y','N','N','N','N','ASP_Module_ID',30,0,TO_TIMESTAMP('2008-01-09 23:36:42','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54313 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54210,54314,0,53066,TO_TIMESTAMP('2008-01-09 23:36:43','YYYY-MM-DD HH24:MI:SS'),100,20,'D','Y','Y','Y','N','N','N','N','ASP_Level_ID',40,0,TO_TIMESTAMP('2008-01-09 23:36:43','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54314 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54214,54315,0,53066,TO_TIMESTAMP('2008-01-09 23:36:43','YYYY-MM-DD HH24:MI:SS'),100,'Comment or Hint',40,'D','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','Comment/Help',50,0,TO_TIMESTAMP('2008-01-09 23:36:43','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54315 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54215,54316,0,53066,TO_TIMESTAMP('2008-01-09 23:36:44','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system',1,'D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons FOR de-activating AND NOT DELETING records:
(1) THE SYSTEM requires THE RECORD FOR AUDIT purposes.
(2) THE RECORD IS referenced BY other records. E.g., you cannot DELETE a Business Partner, IF there are invoices FOR this partner RECORD existing. You de-ACTIVATE THE Business Partner AND prevent that this RECORD IS used FOR future entries.','Y','Y','Y','N','N','N','N','Active',60,0,TO_TIMESTAMP('2008-01-09 23:36:44','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54316 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_TABLE (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,NAME,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53057,'2',TO_TIMESTAMP('2008-01-09 23:36:45','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','N','Y','N','N','N','ASP Client Exception','L','ASP_ClientException',TO_TIMESTAMP('2008-01-09 23:36:45','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_TABLE_TRL (AD_LANGUAGE,AD_Table_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Table_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_TABLE t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53057 AND EXISTS (SELECT * FROM AD_TABLE_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Table_ID!=t.AD_Table_ID)
;

INSERT INTO AD_SEQUENCE (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,NAME,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53074,TO_TIMESTAMP('2008-01-09 23:36:46','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table ASP_ClientException',1,'Y','N','Y','Y','ASP_ClientException','N',1000000,TO_TIMESTAMP('2008-01-09 23:36:46','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,ReadOnlyLogic,Updated,UpdatedBy,VERSION) VALUES (0,54218,144,0,19,53057,52006,'AD_Workflow_ID',TO_TIMESTAMP('2008-01-09 23:36:51','YYYY-MM-DD HH24:MI:SS'),100,'Workflow or combination of tasks','D',22,'The Workflow field identifies a unique Workflow in the system.','Y','N','N','N','N','N','N','N','N','N','Y','Workflow','@AD_Window_ID@>0 | @AD_Process_ID@>0 | @AD_Form_ID@>0 | @AD_Task_ID@>0',TO_TIMESTAMP('2008-01-09 23:36:51','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54218 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,ReadOnlyLogic,Updated,UpdatedBy,VERSION) VALUES (0,54219,1298,0,19,53057,'AD_Form_ID',TO_TIMESTAMP('2008-01-09 23:36:55','YYYY-MM-DD HH24:MI:SS'),100,'Special Form','D',22,'The Special Form field identifies a unique Special Form in the system.','Y','N','N','N','N','N','N','N','N','N','Y','Special Form','@AD_Process_ID@>0 | @AD_Window_ID@>0 | @AD_Task_ID@>0 | @AD_Workflow_ID@>0',TO_TIMESTAMP('2008-01-09 23:36:55','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54219 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,ReadOnlyLogic,Updated,UpdatedBy,VERSION) VALUES (0,54220,142,0,19,53057,140,'AD_WF_Node_ID',TO_TIMESTAMP('2008-01-09 23:36:56','YYYY-MM-DD HH24:MI:SS'),100,'Workflow Node (activity), step or process','D',22,'The Workflow Node indicates a unique step or process in a Workflow.','Y','N','N','N','N','N','N','N','N','N','Y','Node','@AD_Window_ID@>0 | @AD_Process_ID@>0 | @AD_Form_ID@>0 | @AD_Task_ID@>0',TO_TIMESTAMP('2008-01-09 23:36:56','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54220 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,ReadOnlyLogic,Updated,UpdatedBy,VERSION) VALUES (0,54221,128,0,19,53057,'AD_Task_ID',TO_TIMESTAMP('2008-01-09 23:36:58','YYYY-MM-DD HH24:MI:SS'),100,'Operation System Task','D',22,'The Task field identifies a Operation System Task in the system.','Y','N','N','N','N','N','N','N','N','N','Y','OS Task','@AD_Process_ID@>0 | @AD_Form_ID@>0 | @AD_Window_ID@>0 | @AD_Workflow_ID@>0',TO_TIMESTAMP('2008-01-09 23:36:58','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54221 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54222,53327,0,17,53234,53057,'ASP_Status',TO_TIMESTAMP('2008-01-09 23:36:58','YYYY-MM-DD HH24:MI:SS'),100,'U','D',1,'Y','N','N','N','N','Y','N','N','N','N','Y','ASP_Status',TO_TIMESTAMP('2008-01-09 23:36:58','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54222 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,ReadOnlyLogic,Updated,UpdatedBy,VERSION) VALUES (0,54223,117,0,19,53057,'AD_Process_ID',TO_TIMESTAMP('2008-01-09 23:36:59','YYYY-MM-DD HH24:MI:SS'),100,'Process or Report','D',22,'The Process field identifies a unique Process or Report in the system.','Y','N','N','N','N','N','N','N','N','N','Y','Process','@AD_Window_ID@>0 | @AD_Form_ID@>0 | @AD_Task_ID@>0 | @AD_Workflow_ID@>0',TO_TIMESTAMP('2008-01-09 23:36:59','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54223 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_ELEMENT (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,NAME,PrintName,Updated,UpdatedBy) VALUES (0,53331,0,'ASP_ClientException_ID',TO_TIMESTAMP('2008-01-09 23:37:00','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','ASP_ClientException_ID','ASP_ClientException_ID',TO_TIMESTAMP('2008-01-09 23:37:00','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_ELEMENT_TRL (AD_LANGUAGE,AD_Element_ID, Description,Help,NAME,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Element_ID, t.Description,t.Help,t.NAME,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_ELEMENT t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53331 AND EXISTS (SELECT * FROM AD_ELEMENT_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Element_ID!=t.AD_Element_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54224,53331,0,13,53057,'ASP_ClientException_ID',TO_TIMESTAMP('2008-01-09 23:37:00','YYYY-MM-DD HH24:MI:SS'),100,'D',22,'Y','N','N','N','Y','Y','N','N','N','N','N','ASP_ClientException_ID',TO_TIMESTAMP('2008-01-09 23:37:00','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54224 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54225,245,0,16,53057,'Created',TO_TIMESTAMP('2008-01-09 23:37:02','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','D',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','N','N','N','Created',TO_TIMESTAMP('2008-01-09 23:37:02','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54225 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54226,246,0,18,110,53057,'CreatedBy',TO_TIMESTAMP('2008-01-09 23:37:02','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','D',22,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','N','N','N','Created By',TO_TIMESTAMP('2008-01-09 23:37:02','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54226 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54227,348,0,20,53057,'IsActive',TO_TIMESTAMP('2008-01-09 23:37:04','YYYY-MM-DD HH24:MI:SS'),100,'Y','The record is active in the system','D',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons FOR de-activating AND NOT DELETING records:
(1) THE SYSTEM requires THE RECORD FOR AUDIT purposes.
(2) THE RECORD IS referenced BY other records. E.g., you cannot DELETE a Business Partner, IF there are invoices FOR this partner RECORD existing. You de-ACTIVATE THE Business Partner AND prevent that this RECORD IS used FOR future entries.','Y','N','N','N','N','Y','N','N','N','N','Y','Active',TO_TIMESTAMP('2008-01-09 23:37:04','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54227 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54228,607,0,16,53057,'Updated',TO_TIMESTAMP('2008-01-09 23:37:08','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','D',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','N','N','N','Updated',TO_TIMESTAMP('2008-01-09 23:37:08','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54228 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,ReadOnlyLogic,Updated,UpdatedBy,VERSION) VALUES (0,54229,143,0,30,53057,'AD_Window_ID',TO_TIMESTAMP('2008-01-09 23:37:09','YYYY-MM-DD HH24:MI:SS'),100,'Data entry or display window','D',10,'The Window field identifies a unique Window in the system.','Y','N','N','N','N','N','N','N','N','N','Y','Window','@AD_Workflow_ID@>0 | @AD_Process_ID@>0 | @AD_Form_ID@>0 | @AD_Task_ID@>0',TO_TIMESTAMP('2008-01-09 23:37:09','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54229 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,ReadOnlyLogic,Updated,UpdatedBy,VERSION) VALUES (0,54230,125,0,30,53057,163,'AD_Tab_ID',TO_TIMESTAMP('2008-01-09 23:37:10','YYYY-MM-DD HH24:MI:SS'),100,'Tab within a Window','D',10,'The Tab indicates a tab that displays within a window.','Y','N','N','N','N','N','N','N','N','N','Y','Tab','@AD_Process_ID@>0 | @AD_Form_ID@>0 | @AD_Task_ID@>0 | @AD_Workflow_ID@>0',TO_TIMESTAMP('2008-01-09 23:37:10','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54230 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,ReadOnlyLogic,Updated,UpdatedBy,VERSION) VALUES (0,54231,118,0,30,53057,'AD_Process_Para_ID',TO_TIMESTAMP('2008-01-09 23:37:12','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','N','N','N','N','N','N','N','N','N','Y','Process Parameter','@AD_Window_ID@>0 | @AD_Form_ID@>0 | @AD_Task_ID@>0 | @AD_Workflow_ID@>0',TO_TIMESTAMP('2008-01-09 23:37:12','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54231 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54232,113,0,19,53057,104,'AD_Org_ID',TO_TIMESTAMP('2008-01-09 23:37:13','YYYY-MM-DD HH24:MI:SS'),100,'0','Organizational entity within client','D',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','N','N','N','Organization',TO_TIMESTAMP('2008-01-09 23:37:13','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54232 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54233,608,0,18,110,53057,'UpdatedBy',TO_TIMESTAMP('2008-01-09 23:37:14','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','D',22,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','N','N','N','Updated By',TO_TIMESTAMP('2008-01-09 23:37:14','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54233 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,Updated,UpdatedBy,VERSION) VALUES (0,54234,102,0,19,53057,'AD_Client_ID',TO_TIMESTAMP('2008-01-09 23:37:14','YYYY-MM-DD HH24:MI:SS'),100,'@#AD_Client_ID@','Client/Tenant for this installation.','D',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','N','N','N','Client',TO_TIMESTAMP('2008-01-09 23:37:14','YYYY-MM-DD HH24:MI:SS'),100,1)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54234 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_COLUMN (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,NAME,ReadOnlyLogic,Updated,UpdatedBy,VERSION) VALUES (0,54235,107,0,30,53057,52005,'AD_Field_ID',TO_TIMESTAMP('2008-01-09 23:37:15','YYYY-MM-DD HH24:MI:SS'),100,'Field on a database table','D',10,'The Field identifies a field on a database table.','Y','N','N','N','N','N','N','N','N','N','Y','Field','@AD_Process_ID@>0 | @AD_Form_ID@>0 | @AD_Task_ID@>0 | @AD_Workflow_ID@>0',TO_TIMESTAMP('2008-01-09 23:37:15','YYYY-MM-DD HH24:MI:SS'),100,0)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54235 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

INSERT INTO AD_TAB (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,NAME,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53067,53057,53016,NULL,TO_TIMESTAMP('2008-01-09 23:37:16','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','N','N','Y','N','Y','N','N','Exceptions','N',20,0,TO_TIMESTAMP('2008-01-09 23:37:16','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_TAB_TRL (AD_LANGUAGE,AD_Tab_ID, CommitWarning,Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_TAB t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53067 AND EXISTS (SELECT * FROM AD_TAB_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54224,54317,0,53067,TO_TIMESTAMP('2008-01-09 23:37:17','YYYY-MM-DD HH24:MI:SS'),100,22,'D','Y','Y','N','N','N','N','N','ASP_ClientException_ID',0,0,TO_TIMESTAMP('2008-01-09 23:37:17','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54317 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54234,54318,0,53067,TO_TIMESTAMP('2008-01-09 23:37:18','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.',22,'D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_TIMESTAMP('2008-01-09 23:37:18','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54318 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54232,54319,0,53067,TO_TIMESTAMP('2008-01-09 23:37:19','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client',22,'D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_TIMESTAMP('2008-01-09 23:37:19','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54319 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54229,54320,0,53067,TO_TIMESTAMP('2008-01-09 23:37:20','YYYY-MM-DD HH24:MI:SS'),100,'Data entry or display window',10,'D','The Window field identifies a unique Window in the system.','Y','Y','Y','N','N','N','N','Window',30,0,TO_TIMESTAMP('2008-01-09 23:37:20','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54320 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54230,54321,0,53067,TO_TIMESTAMP('2008-01-09 23:37:21','YYYY-MM-DD HH24:MI:SS'),100,'Tab within a Window',10,'D','The Tab indicates a tab that displays within a window.','Y','Y','Y','N','N','N','N','Tab',40,0,TO_TIMESTAMP('2008-01-09 23:37:21','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54321 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54235,54322,0,53067,TO_TIMESTAMP('2008-01-09 23:37:22','YYYY-MM-DD HH24:MI:SS'),100,'Field on a database table',10,'D','The Field identifies a field on a database table.','Y','Y','Y','N','N','N','N','Field',50,0,TO_TIMESTAMP('2008-01-09 23:37:22','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54322 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54223,54323,0,53067,TO_TIMESTAMP('2008-01-09 23:37:26','YYYY-MM-DD HH24:MI:SS'),100,'Process or Report',22,'D','The Process field identifies a unique Process or Report in the system.','Y','Y','Y','N','N','N','N','Process',60,0,TO_TIMESTAMP('2008-01-09 23:37:26','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54323 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54231,54324,0,53067,TO_TIMESTAMP('2008-01-09 23:37:27','YYYY-MM-DD HH24:MI:SS'),100,10,'D','Y','Y','Y','N','N','N','N','Process Parameter',70,0,TO_TIMESTAMP('2008-01-09 23:37:27','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54324 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54219,54325,0,53067,TO_TIMESTAMP('2008-01-09 23:37:27','YYYY-MM-DD HH24:MI:SS'),100,'Special Form',22,'D','The Special Form field identifies a unique Special Form in the system.','Y','Y','Y','N','N','N','N','Special Form',80,0,TO_TIMESTAMP('2008-01-09 23:37:27','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54325 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54221,54326,0,53067,TO_TIMESTAMP('2008-01-09 23:37:28','YYYY-MM-DD HH24:MI:SS'),100,'Operation System Task',22,'D','The Task field identifies a Operation System Task in the system.','Y','Y','Y','N','N','N','N','OS Task',90,0,TO_TIMESTAMP('2008-01-09 23:37:28','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54326 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54218,54327,0,53067,TO_TIMESTAMP('2008-01-09 23:37:29','YYYY-MM-DD HH24:MI:SS'),100,'Workflow or combination of tasks',22,'D','The Workflow field identifies a unique Workflow in the system.','Y','Y','Y','N','N','N','N','Workflow',100,0,TO_TIMESTAMP('2008-01-09 23:37:29','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54327 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54222,54328,0,53067,TO_TIMESTAMP('2008-01-09 23:37:30','YYYY-MM-DD HH24:MI:SS'),100,1,'D','Y','Y','Y','N','N','N','N','ASP_Status',110,0,TO_TIMESTAMP('2008-01-09 23:37:30','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54328 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

INSERT INTO AD_FIELD (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,NAME,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54227,54329,0,53067,TO_TIMESTAMP('2008-01-09 23:37:32','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system',1,'D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons FOR de-activating AND NOT DELETING records:
(1) THE SYSTEM requires THE RECORD FOR AUDIT purposes.
(2) THE RECORD IS referenced BY other records. E.g., you cannot DELETE a Business Partner, IF there are invoices FOR this partner RECORD existing. You de-ACTIVATE THE Business Partner AND prevent that this RECORD IS used FOR future entries.','Y','Y','Y','N','N','N','N','Active',120,0,TO_TIMESTAMP('2008-01-09 23:37:32','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_FIELD_TRL (AD_LANGUAGE,AD_Field_ID, Description,Help,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Field_ID, t.Description,t.Help,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_FIELD t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54329 AND EXISTS (SELECT * FROM AD_FIELD_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Field_ID!=t.AD_Field_ID)
;

CREATE TABLE ASP_MODULE (AD_Client_ID NUMERIC(10) DEFAULT NULL NOT NULL, AD_Org_ID NUMERIC(10) DEFAULT NULL NOT NULL, ASP_Module_ID NUMERIC(10) NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, Description VARCHAR(255), Help VARCHAR(2000), IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, NAME VARCHAR(60) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, VALUE VARCHAR(40) NOT NULL, CONSTRAINT ASP_Module_Key PRIMARY KEY (ASP_Module_ID))
;

CREATE TABLE ASP_LEVEL (AD_Client_ID NUMERIC(10) DEFAULT NULL NOT NULL, AD_Org_ID NUMERIC(10) DEFAULT NULL NOT NULL, ASP_Level_ID NUMERIC(10) NOT NULL, ASP_Module_ID NUMERIC(10) NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, Description VARCHAR(255), Help VARCHAR(2000), IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, NAME VARCHAR(60) NOT NULL, Processing CHAR(1), Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, VALUE VARCHAR(40) NOT NULL, CONSTRAINT ASP_Level_Key PRIMARY KEY (ASP_Level_ID))
;

CREATE TABLE ASP_WINDOW (AD_Client_ID NUMERIC(10) DEFAULT NULL NOT NULL, AD_Org_ID NUMERIC(10) DEFAULT NULL NOT NULL, AD_Window_ID NUMERIC(10) NOT NULL, ASP_Level_ID NUMERIC(10) NOT NULL, ASP_Status CHAR(1) DEFAULT 'U' NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, CONSTRAINT ASP_Window_Key PRIMARY KEY (AD_Window_ID, ASP_Level_ID))
;

CREATE TABLE ASP_TAB (AD_Client_ID NUMERIC(10) DEFAULT NULL NOT NULL, AD_Org_ID NUMERIC(10) DEFAULT NULL NOT NULL, AD_Tab_ID NUMERIC(10) NOT NULL, AD_Window_ID NUMERIC(10) NOT NULL, ASP_Level_ID NUMERIC(10) NOT NULL, ASP_Status CHAR(1) DEFAULT 'U' NOT NULL, AllFields CHAR(1) DEFAULT 'Y' CHECK (AllFields IN ('Y','N')), Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Processing CHAR(1), Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, CONSTRAINT ASP_Tab_Key PRIMARY KEY (AD_Tab_ID, AD_Window_ID, ASP_Level_ID))
;

CREATE TABLE ASP_FIELD (AD_Client_ID NUMERIC(10) DEFAULT NULL NOT NULL, AD_Field_ID NUMERIC(10), AD_Org_ID NUMERIC(10) DEFAULT NULL NOT NULL, AD_Tab_ID NUMERIC(10) NOT NULL, ASP_Level_ID NUMERIC(10) NOT NULL, ASP_Status CHAR(1) DEFAULT 'U' NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, CONSTRAINT ASP_Field_Key PRIMARY KEY (AD_Field_ID, AD_Tab_ID, ASP_Level_ID))
;

CREATE TABLE ASP_FORM (AD_Client_ID NUMERIC(10) DEFAULT NULL NOT NULL, AD_Form_ID NUMERIC(10) NOT NULL, AD_Org_ID NUMERIC(10) DEFAULT NULL NOT NULL, ASP_Level_ID NUMERIC(10) NOT NULL, ASP_Status CHAR(1) DEFAULT 'U' NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, CONSTRAINT ASP_Form_Key PRIMARY KEY (AD_Form_ID, ASP_Level_ID))
;

CREATE TABLE ASP_TASK (AD_Client_ID NUMERIC(10) DEFAULT NULL NOT NULL, AD_Org_ID NUMERIC(10) DEFAULT NULL NOT NULL, AD_Task_ID NUMERIC(10) NOT NULL, ASP_Level_ID NUMERIC(10) NOT NULL, ASP_Status CHAR(1) DEFAULT 'U' NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, CONSTRAINT ASP_Task_Key PRIMARY KEY (AD_Task_ID, ASP_Level_ID))
;

CREATE TABLE ASP_PROCESS (AD_Client_ID NUMERIC(10) DEFAULT NULL NOT NULL, AD_Org_ID NUMERIC(10) DEFAULT NULL NOT NULL, AD_Process_ID NUMERIC(10) NOT NULL, ASP_Level_ID NUMERIC(10) NOT NULL, ASP_Status CHAR(1) DEFAULT 'U' NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, CONSTRAINT ASP_Process_Key PRIMARY KEY (AD_Process_ID, ASP_Level_ID))
;

CREATE TABLE ASP_PROCESS_PARA (AD_Client_ID NUMERIC(10) DEFAULT NULL NOT NULL, AD_Org_ID NUMERIC(10) DEFAULT NULL NOT NULL, AD_Process_ID NUMERIC(10) NOT NULL, AD_Process_Para_ID NUMERIC(10), ASP_Level_ID NUMERIC(10) NOT NULL, ASP_Status CHAR(1) DEFAULT 'U' NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, CONSTRAINT ASP_Process_Para_Key PRIMARY KEY (AD_Process_ID, AD_Process_Para_ID, ASP_Level_ID))
;

CREATE TABLE ASP_WORKFLOW (AD_Client_ID NUMERIC(10) DEFAULT NULL NOT NULL, AD_Org_ID NUMERIC(10) DEFAULT NULL NOT NULL, AD_Workflow_ID NUMERIC(10) NOT NULL, ASP_Level_ID NUMERIC(10) NOT NULL, ASP_Status CHAR(1) DEFAULT 'U' NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, CONSTRAINT ASP_Workflow_Key PRIMARY KEY (AD_Workflow_ID, ASP_Level_ID))
;

CREATE TABLE ASP_CLIENTLEVEL (AD_Client_ID NUMERIC(10) DEFAULT NULL NOT NULL, AD_Org_ID NUMERIC(10) DEFAULT 0 NOT NULL, ASP_ClientLevel_ID NUMERIC(10) NOT NULL, ASP_Level_ID NUMERIC(10) NOT NULL, ASP_Module_ID NUMERIC(10) NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, Help VARCHAR(2000), IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, CONSTRAINT ASP_ClientLevel_Key PRIMARY KEY (ASP_ClientLevel_ID))
;

CREATE TABLE ASP_CLIENTEXCEPTION (AD_Client_ID NUMERIC(10) DEFAULT NULL NOT NULL, AD_Field_ID NUMERIC(10), AD_Form_ID NUMERIC(10), AD_Org_ID NUMERIC(10) DEFAULT 0 NOT NULL, AD_Process_ID NUMERIC(10), AD_Process_Para_ID NUMERIC(10), AD_Tab_ID NUMERIC(10), AD_Task_ID NUMERIC(10), AD_WF_Node_ID NUMERIC(10), AD_Window_ID NUMERIC(10), AD_Workflow_ID NUMERIC(10), ASP_ClientException_ID NUMERIC(10) NOT NULL, ASP_Status CHAR(1) DEFAULT 'U' NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, CONSTRAINT ASP_ClientException_Key PRIMARY KEY (ASP_ClientException_ID))
;

INSERT INTO AD_MENU (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,"action",Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,NAME,Updated,UpdatedBy) VALUES (0,53083,0,53015,'W',TO_TIMESTAMP('2008-01-09 23:57:19','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','N','N','ASP Modules',TO_TIMESTAMP('2008-01-09 23:57:19','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_MENU_TRL (AD_LANGUAGE,AD_Menu_ID, Description,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Menu_ID, t.Description,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_MENU t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53083 AND EXISTS (SELECT * FROM AD_MENU_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

INSERT INTO AD_TREENODEMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 53083, 0, 999 FROM AD_TREE t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TREENODEMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53083)
;

INSERT INTO AD_MENU (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,"action",Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,NAME,Updated,UpdatedBy) VALUES (0,53084,0,53016,'W',TO_TIMESTAMP('2008-01-09 23:57:35','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','N','N','ASP Subscribed Modules',TO_TIMESTAMP('2008-01-09 23:57:35','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_MENU_TRL (AD_LANGUAGE,AD_Menu_ID, Description,NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Menu_ID, t.Description,t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_MENU t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53084 AND EXISTS (SELECT * FROM AD_MENU_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

INSERT INTO AD_TREENODEMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 53084, 0, 999 FROM AD_TREE t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TREENODEMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53084)
;

-- manually added foreign keys

ALTER TABLE ASP_CLIENTEXCEPTION ADD CONSTRAINT ADField_ASPClientException FOREIGN KEY (AD_Field_ID) REFERENCES AD_FIELD;

ALTER TABLE ASP_CLIENTEXCEPTION ADD CONSTRAINT ADForm_ASPClientException FOREIGN KEY (AD_Form_ID) REFERENCES AD_FORM;

ALTER TABLE ASP_CLIENTEXCEPTION ADD CONSTRAINT ADProcess_ASPClientException FOREIGN KEY (AD_Process_ID) REFERENCES AD_PROCESS;

ALTER TABLE ASP_CLIENTEXCEPTION ADD CONSTRAINT ADProcessPara_ASPClientExcepti FOREIGN KEY (AD_Process_Para_ID) REFERENCES AD_PROCESS_PARA;

ALTER TABLE ASP_CLIENTEXCEPTION ADD CONSTRAINT ADTab_ASPClientException FOREIGN KEY (AD_Tab_ID) REFERENCES AD_TAB;

ALTER TABLE ASP_CLIENTEXCEPTION ADD CONSTRAINT ADTask_ASPClientException FOREIGN KEY (AD_Task_ID) REFERENCES AD_TASK;

ALTER TABLE ASP_CLIENTEXCEPTION ADD CONSTRAINT ADWFNode_ASPClientException FOREIGN KEY (AD_WF_Node_ID) REFERENCES AD_WF_NODE;

ALTER TABLE ASP_CLIENTEXCEPTION ADD CONSTRAINT ADWindow_ASPClientException FOREIGN KEY (AD_Window_ID) REFERENCES AD_WINDOW;

ALTER TABLE ASP_CLIENTEXCEPTION ADD CONSTRAINT ADWorkflow_ASPClientException FOREIGN KEY (AD_Workflow_ID) REFERENCES AD_WORKFLOW;

ALTER TABLE ASP_CLIENTLEVEL ADD CONSTRAINT ASPLevel_ASPClientLevel FOREIGN KEY (ASP_Level_ID) REFERENCES ASP_LEVEL;

ALTER TABLE ASP_CLIENTLEVEL ADD CONSTRAINT ASPModule_ASPClientLevel FOREIGN KEY (ASP_Module_ID) REFERENCES ASP_MODULE;

ALTER TABLE ASP_FIELD ADD CONSTRAINT ADField_ASPField FOREIGN KEY (AD_Field_ID) REFERENCES AD_FIELD;

ALTER TABLE ASP_FIELD ADD CONSTRAINT ADTab_ASPField FOREIGN KEY (AD_Tab_ID) REFERENCES AD_TAB;

ALTER TABLE ASP_FIELD ADD CONSTRAINT ASPLevel_ASPField FOREIGN KEY (ASP_Level_ID) REFERENCES ASP_LEVEL;

ALTER TABLE ASP_FORM ADD CONSTRAINT ADForm_ASPForm FOREIGN KEY (AD_Form_ID) REFERENCES AD_FORM;

ALTER TABLE ASP_FORM ADD CONSTRAINT ASPLevel_ASPForm FOREIGN KEY (ASP_Level_ID) REFERENCES ASP_LEVEL;

ALTER TABLE ASP_LEVEL ADD CONSTRAINT ASPModule_ASPLevel FOREIGN KEY (ASP_Module_ID) REFERENCES ASP_MODULE;

ALTER TABLE ASP_PROCESS ADD CONSTRAINT ADProcess_ASPProcess FOREIGN KEY (AD_Process_ID) REFERENCES AD_PROCESS;

ALTER TABLE ASP_PROCESS ADD CONSTRAINT ASPLevel_ASPProcess FOREIGN KEY (ASP_Level_ID) REFERENCES ASP_LEVEL;

ALTER TABLE ASP_PROCESS_PARA ADD CONSTRAINT ADProcess_ASPProcessPara FOREIGN KEY (AD_Process_ID) REFERENCES AD_PROCESS;

ALTER TABLE ASP_PROCESS_PARA ADD CONSTRAINT ADProcessPara_ASPProcessPara FOREIGN KEY (AD_Process_Para_ID) REFERENCES AD_PROCESS_PARA;

ALTER TABLE ASP_PROCESS_PARA ADD CONSTRAINT ASPLevel_ASPProcessPara FOREIGN KEY (ASP_Level_ID) REFERENCES ASP_LEVEL;

ALTER TABLE ASP_TAB ADD CONSTRAINT ADTab_ASPTab FOREIGN KEY (AD_Tab_ID) REFERENCES AD_TAB;

ALTER TABLE ASP_TAB ADD CONSTRAINT ADWindow_ASPTab FOREIGN KEY (AD_Window_ID) REFERENCES AD_WINDOW;

ALTER TABLE ASP_TAB ADD CONSTRAINT ASPLevel_ASPTab FOREIGN KEY (ASP_Level_ID) REFERENCES ASP_LEVEL;

ALTER TABLE ASP_TASK ADD CONSTRAINT ADTask_ASPTask FOREIGN KEY (AD_Task_ID) REFERENCES AD_TASK;

ALTER TABLE ASP_TASK ADD CONSTRAINT ASPLevel_ASPTask FOREIGN KEY (ASP_Level_ID) REFERENCES ASP_LEVEL;

ALTER TABLE ASP_WINDOW ADD CONSTRAINT ADWindow_ASPWindow FOREIGN KEY (AD_Window_ID) REFERENCES AD_WINDOW;

ALTER TABLE ASP_WINDOW ADD CONSTRAINT ASPLevel_ASPWindow FOREIGN KEY (ASP_Level_ID) REFERENCES ASP_LEVEL;

ALTER TABLE ASP_WORKFLOW ADD CONSTRAINT ADWorkflow_ASPWorkflow FOREIGN KEY (AD_Workflow_ID) REFERENCES AD_WORKFLOW;

ALTER TABLE ASP_WORKFLOW ADD CONSTRAINT ASPLevel_ASPWorkflow FOREIGN KEY (ASP_Level_ID) REFERENCES ASP_LEVEL;

-- Jan 10, 2008 1:06:02 AM COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,110,0,53067,53128,18,105,'AD_Menu_ID',TO_TIMESTAMP('2008-01-10 01:05:57','YYYY-MM-DD HH24:MI:SS'),100,'U',0,'Y','Y','N','N','AD_Menu_ID',20,TO_TIMESTAMP('2008-01-10 01:05:57','YYYY-MM-DD HH24:MI:SS'),100)
;

INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53128 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

UPDATE AD_Process_Para SET Description='Identifies a Menu', Help='The Menu identifies a unique Menu.  Menus are used to control the display of those screens a user has access to.', Name='Menu',Updated=TO_TIMESTAMP('2008-01-10 01:06:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53128
;

UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53128
;

UPDATE AD_Element SET Name='Client Exception', PrintName='Client Exception',Updated=TO_TIMESTAMP('2008-01-10 01:15:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53331
;

UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53331
;

UPDATE AD_Column SET ColumnName='ASP_ClientException_ID', Name='Client Exception', Description=NULL, Help=NULL WHERE AD_Element_ID=53331
;

UPDATE AD_Field SET Name='Client Exception', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53331) AND IsCentrallyMaintained='Y'
;

UPDATE AD_Process_Para SET ColumnName='ASP_ClientException_ID', Name='Client Exception', Description=NULL, Help=NULL, AD_Element_ID=53331 WHERE UPPER(ColumnName)='ASP_CLIENTEXCEPTION_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

UPDATE AD_Process_Para SET ColumnName='ASP_ClientException_ID', Name='Client Exception', Description=NULL, Help=NULL WHERE AD_Element_ID=53331 AND IsCentrallyMaintained='Y'
;

UPDATE AD_PrintFormatItem SET PrintName='Client Exception', Name='Client Exception' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53331)
;

UPDATE AD_PrintFormatItem SET PrintName='Client Exception', Name='Client Exception' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53331)
;

UPDATE AD_Element SET Name='Client Level', PrintName='Client Level',Updated=TO_TIMESTAMP('2008-01-10 01:15:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53330
;

UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53330
;

UPDATE AD_Column SET ColumnName='ASP_ClientLevel_ID', Name='Client Level', Description=NULL, Help=NULL WHERE AD_Element_ID=53330
;

UPDATE AD_Field SET Name='Client Level', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53330) AND IsCentrallyMaintained='Y'
;

UPDATE AD_Process_Para SET ColumnName='ASP_ClientLevel_ID', Name='Client Level', Description=NULL, Help=NULL, AD_Element_ID=53330 WHERE UPPER(ColumnName)='ASP_CLIENTLEVEL_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

UPDATE AD_Process_Para SET ColumnName='ASP_ClientLevel_ID', Name='Client Level', Description=NULL, Help=NULL WHERE AD_Element_ID=53330 AND IsCentrallyMaintained='Y'
;

UPDATE AD_PrintFormatItem SET PrintName='Client Level', Name='Client Level' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53330)
;

UPDATE AD_PrintFormatItem SET PrintName='Client Level', Name='Client Level' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53330)
;

UPDATE AD_Element SET Name='ASP Level', PrintName='ASP Level',Updated=TO_TIMESTAMP('2008-01-10 01:15:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53326
;

UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53326
;

UPDATE AD_Column SET ColumnName='ASP_Level_ID', Name='ASP Level', Description=NULL, Help=NULL WHERE AD_Element_ID=53326
;

UPDATE AD_Field SET Name='ASP Level', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53326) AND IsCentrallyMaintained='Y'
;

UPDATE AD_Process_Para SET ColumnName='ASP_Level_ID', Name='ASP Level', Description=NULL, Help=NULL, AD_Element_ID=53326 WHERE UPPER(ColumnName)='ASP_LEVEL_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

UPDATE AD_Process_Para SET ColumnName='ASP_Level_ID', Name='ASP Level', Description=NULL, Help=NULL WHERE AD_Element_ID=53326 AND IsCentrallyMaintained='Y'
;

UPDATE AD_PrintFormatItem SET PrintName='ASP Level', Name='ASP Level' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53326)
;

UPDATE AD_PrintFormatItem SET PrintName='ASP Level', Name='ASP Level' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53326)
;

UPDATE AD_Element SET Name='ASP Module', PrintName='ASP Module',Updated=TO_TIMESTAMP('2008-01-10 01:15:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53329
;

UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53329
;

UPDATE AD_Column SET ColumnName='ASP_Module_ID', Name='ASP Module', Description=NULL, Help=NULL WHERE AD_Element_ID=53329
;

UPDATE AD_Field SET Name='ASP Module', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53329) AND IsCentrallyMaintained='Y'
;

UPDATE AD_Process_Para SET ColumnName='ASP_Module_ID', Name='ASP Module', Description=NULL, Help=NULL, AD_Element_ID=53329 WHERE UPPER(ColumnName)='ASP_MODULE_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

UPDATE AD_Process_Para SET ColumnName='ASP_Module_ID', Name='ASP Module', Description=NULL, Help=NULL WHERE AD_Element_ID=53329 AND IsCentrallyMaintained='Y'
;

UPDATE AD_PrintFormatItem SET PrintName='ASP Module', Name='ASP Module' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53329)
;

UPDATE AD_PrintFormatItem SET PrintName='ASP Module', Name='ASP Module' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53329)
;

UPDATE AD_Element SET Name='ASP Status', PrintName='ASP Status',Updated=TO_TIMESTAMP('2008-01-10 01:16:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53327
;

UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53327
;

UPDATE AD_Column SET ColumnName='ASP_Status', Name='ASP Status', Description=NULL, Help=NULL WHERE AD_Element_ID=53327
;

UPDATE AD_Field SET Name='ASP Status', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53327) AND IsCentrallyMaintained='Y'
;

UPDATE AD_Process_Para SET ColumnName='ASP_Status', Name='ASP Status', Description=NULL, Help=NULL, AD_Element_ID=53327 WHERE UPPER(ColumnName)='ASP_STATUS' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

UPDATE AD_Process_Para SET ColumnName='ASP_Status', Name='ASP Status', Description=NULL, Help=NULL WHERE AD_Element_ID=53327 AND IsCentrallyMaintained='Y'
;

UPDATE AD_PrintFormatItem SET PrintName='ASP Status', Name='ASP Status' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53327)
;

UPDATE AD_PrintFormatItem SET PrintName='ASP Status', Name='ASP Status' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53327)
;

