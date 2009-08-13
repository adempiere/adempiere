-- 14/05/2009 11:43:13
-- Fin Report Summary
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WinHeight,WinWidth,WindowType) VALUES (0,0,53078,TO_TIMESTAMP('2009-05-14 11:43:12','YYYY-MM-DD HH24:MI:SS'),100,'Define reporting cube for pre-calculation of summary accounting data.','D','Summary data will be generated for each period of the selected calendar, grouped by the selected dimensions..','Y','N','N','Y','Report Cube','N',TO_TIMESTAMP('2009-05-14 11:43:12','YYYY-MM-DD HH24:MI:SS'),100,0,0,'M')
;

-- 14/05/2009 11:43:13
-- Fin Report Summary
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53078 AND EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Window_ID!=t.AD_Window_ID)
;

-- 14/05/2009 11:43:33
-- Fin Report Summary
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,CopyColumnsFromTable,Created,CreatedBy,Description,EntityType,Help,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,LoadSeq,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53202,'3','N',TO_TIMESTAMP('2009-05-14 11:43:30','YYYY-MM-DD HH24:MI:SS'),100,'Define reporting cube for pre-calculation of summary accounting data.','D','Summary data will be generated for each period of the selected calendar, grouped by the selected dimensions..','N','Y','N','Y','N','N','N',0,'Report Cube','L','PA_ReportCube',TO_TIMESTAMP('2009-05-14 11:43:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 11:43:33
-- Fin Report Summary
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53202 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- 14/05/2009 11:43:43
-- Fin Report Summary
UPDATE AD_Table SET AD_Window_ID=53078,Updated=TO_TIMESTAMP('2009-05-14 11:43:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=53202
;

-- 14/05/2009 11:44:26
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57556,102,0,19,53202,'AD_Client_ID',TO_TIMESTAMP('2009-05-14 11:44:25','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.','D',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','N','Y','N','N','N','N','Y','Client',0,TO_TIMESTAMP('2009-05-14 11:44:25','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 11:44:26
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57556 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 11:44:53
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57557,113,0,19,53202,'AD_Org_ID',TO_TIMESTAMP('2009-05-14 11:44:52','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client','D',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','N','Y','N','N','N','N','Y','Organization',0,TO_TIMESTAMP('2009-05-14 11:44:52','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 11:44:53
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57557 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 11:45:33
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57558,190,0,19,53202,'C_Calendar_ID',TO_TIMESTAMP('2009-05-14 11:45:32','YYYY-MM-DD HH24:MI:SS'),100,'Accounting Calendar Name','D',22,'The Calendar uniquely identifies an accounting calendar.  Multiple calendars can be used.  For example you may need a standard calendar that runs from Jan 1 to Dec 31 and a fiscal calendar that runs from July 1 to June 30.','Y','Y','N','N','N','N','N','Y','N','N','N','N','Y','Calendar',0,TO_TIMESTAMP('2009-05-14 11:45:32','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 11:45:33
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57558 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 11:45:55
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57559,245,0,16,53202,'Created',TO_TIMESTAMP('2009-05-14 11:45:54','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','D',7,'The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','N','Y','N','N','N','N','Y','Created',0,TO_TIMESTAMP('2009-05-14 11:45:54','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 11:45:55
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57559 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 11:46:32
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57560,246,0,18,286,53202,'CreatedBy',TO_TIMESTAMP('2009-05-14 11:46:31','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','D',22,'The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','N','Y','N','N','N','N','Y','Created By',0,TO_TIMESTAMP('2009-05-14 11:46:31','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 11:46:32
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57560 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 11:47:12
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57561,275,0,14,53202,'Description',TO_TIMESTAMP('2009-05-14 11:47:11','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record','D',255,'A description is limited to 255 characters.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Description',0,TO_TIMESTAMP('2009-05-14 11:47:11','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 11:47:12
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57561 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 11:48:37
-- Fin Report Summary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53821,0,'PA_ReportCube_ID',TO_TIMESTAMP('2009-05-14 11:48:36','YYYY-MM-DD HH24:MI:SS'),100,'Define reporting cube for pre-calculation of summary accounting data.','D','Summary data will be generated for each period of the selected calendar, grouped by the selected dimensions..','Y','Report Cube','Report Cube',TO_TIMESTAMP('2009-05-14 11:48:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 11:48:37
-- Fin Report Summary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53821 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 14/05/2009 11:49:14
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57562,53821,0,13,53202,'PA_ReportCube_ID',TO_TIMESTAMP('2009-05-14 11:49:13','YYYY-MM-DD HH24:MI:SS'),100,'Define reporting cube for pre-calculation of summary accounting data.','D',22,'Summary data will be generated for each period of the selected calendar, grouped by the selected dimensions..','Y','Y','N','N','N','N','Y','Y','N','N','N','N','N','Report Cube',0,TO_TIMESTAMP('2009-05-14 11:49:13','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 11:49:14
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57562 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 11:49:47
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57563,469,0,10,53202,'Name',TO_TIMESTAMP('2009-05-14 11:49:46','YYYY-MM-DD HH24:MI:SS'),100,'Alphanumeric identifier of the entity','D',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','N','N','N','N','N','Y','N','N','N','N','Y','Name',0,TO_TIMESTAMP('2009-05-14 11:49:46','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 11:49:47
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57563 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 11:50:16
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57564,607,0,16,53202,'Updated',TO_TIMESTAMP('2009-05-14 11:50:16','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','D',7,'The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','N','Y','N','N','N','N','Y','Updated',0,TO_TIMESTAMP('2009-05-14 11:50:16','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 11:50:16
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57564 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 11:50:54
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57565,608,0,18,286,53202,'UpdatedBy',TO_TIMESTAMP('2009-05-14 11:50:53','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','D',22,'The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','N','Y','N','N','N','N','Y','Updated By',0,TO_TIMESTAMP('2009-05-14 11:50:53','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 11:50:54
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57565 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 11:52:59
-- Fin Report Summary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53822,0,'IsActivityDim',TO_TIMESTAMP('2009-05-14 11:52:58','YYYY-MM-DD HH24:MI:SS'),100,'Include Activity as a cube dimension','D','Y','Activity Dimension','Activity Dimension',TO_TIMESTAMP('2009-05-14 11:52:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 11:52:59
-- Fin Report Summary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53822 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 14/05/2009 11:53:35
-- Fin Report Summary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53823,0,'IsOrgTrxDim',TO_TIMESTAMP('2009-05-14 11:53:34','YYYY-MM-DD HH24:MI:SS'),100,'Include OrgTrx as a cube dimension','D','Y','OrgTrx Dimension','OrgTrx Dimension',TO_TIMESTAMP('2009-05-14 11:53:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 11:53:35
-- Fin Report Summary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53823 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 14/05/2009 11:54:25
-- Fin Report Summary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53824,0,'IsBPartnerDim',TO_TIMESTAMP('2009-05-14 11:54:24','YYYY-MM-DD HH24:MI:SS'),100,'Include Business Partner as a cube dimension','D','Y','Business Partner Dimension','Business Partner Dimension',TO_TIMESTAMP('2009-05-14 11:54:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 11:54:25
-- Fin Report Summary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53824 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 14/05/2009 11:55:01
-- Fin Report Summary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53825,0,'IsCampaignDim',TO_TIMESTAMP('2009-05-14 11:55:01','YYYY-MM-DD HH24:MI:SS'),100,'Include Campaign as a cube dimension','D','Y','Campaign Dimension','Campaign Dimension',TO_TIMESTAMP('2009-05-14 11:55:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 11:55:01
-- Fin Report Summary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53825 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 14/05/2009 11:55:33
-- Fin Report Summary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53826,0,'IsLocFromDim',TO_TIMESTAMP('2009-05-14 11:55:32','YYYY-MM-DD HH24:MI:SS'),100,'Include Location From as a cube dimension','D','Y','Location From Dimension','Location From Dimension',TO_TIMESTAMP('2009-05-14 11:55:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 11:55:33
-- Fin Report Summary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53826 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 14/05/2009 11:55:54
-- Fin Report Summary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53827,0,'IsLocToDim',TO_TIMESTAMP('2009-05-14 11:55:53','YYYY-MM-DD HH24:MI:SS'),100,'Include Location To as a cube dimension','D','Y','Location To  Dimension','Location To Dimension',TO_TIMESTAMP('2009-05-14 11:55:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 11:55:54
-- Fin Report Summary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53827 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 14/05/2009 11:56:31
-- Fin Report Summary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53828,0,'IsProjectPhaseDim',TO_TIMESTAMP('2009-05-14 11:56:31','YYYY-MM-DD HH24:MI:SS'),100,'Include Project Phase as a cube dimension','D','Y','Project Phase  Dimension','Project Phase Dimension',TO_TIMESTAMP('2009-05-14 11:56:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 11:56:31
-- Fin Report Summary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53828 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 14/05/2009 11:57:00
-- Fin Report Summary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53829,0,'IsProjectTaskDim',TO_TIMESTAMP('2009-05-14 11:56:59','YYYY-MM-DD HH24:MI:SS'),100,'Include Project Task as a cube dimension','D','Y','Project Task  Dimension','Project Task Dimension',TO_TIMESTAMP('2009-05-14 11:56:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 11:57:00
-- Fin Report Summary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53829 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 14/05/2009 11:57:24
-- Fin Report Summary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53830,0,'IsProjectDim',TO_TIMESTAMP('2009-05-14 11:57:24','YYYY-MM-DD HH24:MI:SS'),100,'Include Project as a cube dimension','D','Y','Project Dimension','Project Dimension',TO_TIMESTAMP('2009-05-14 11:57:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 11:57:24
-- Fin Report Summary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53830 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 14/05/2009 11:58:01
-- Fin Report Summary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53831,0,'IsSalesRegionDim',TO_TIMESTAMP('2009-05-14 11:58:00','YYYY-MM-DD HH24:MI:SS'),100,'Include Sales Region as a cube dimension','D','Y','Sales Region Dimension','Sales Region Dimension',TO_TIMESTAMP('2009-05-14 11:58:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 11:58:01
-- Fin Report Summary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53831 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 14/05/2009 11:58:43
-- Fin Report Summary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53832,0,'IsSubAcctDim',TO_TIMESTAMP('2009-05-14 11:58:42','YYYY-MM-DD HH24:MI:SS'),100,'Include Sub Acct as a cube dimension','D','Y','Sub Acct Dimension','Sub Acct Dimension',TO_TIMESTAMP('2009-05-14 11:58:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 11:58:43
-- Fin Report Summary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53832 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 14/05/2009 11:59:25
-- Fin Report Summary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53833,0,'IsGLBudgetDim',TO_TIMESTAMP('2009-05-14 11:59:24','YYYY-MM-DD HH24:MI:SS'),100,'Include GL Budget as a cube dimension','D','Y','GL Budget Dimension','GL Budget Dimension',TO_TIMESTAMP('2009-05-14 11:59:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 11:59:25
-- Fin Report Summary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53833 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 14/05/2009 11:59:56
-- Fin Report Summary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53834,0,'IsProductDim',TO_TIMESTAMP('2009-05-14 11:59:55','YYYY-MM-DD HH24:MI:SS'),100,'Include Product as a cube dimension','D','Y','Product Dimension','Product Dimension',TO_TIMESTAMP('2009-05-14 11:59:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 11:59:56
-- Fin Report Summary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53834 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 14/05/2009 12:00:50
-- Fin Report Summary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53835,0,'IsUser1Dim',TO_TIMESTAMP('2009-05-14 12:00:27','YYYY-MM-DD HH24:MI:SS'),100,'Include User 1 as a cube dimension','D','Y','User 1 Dimension','User 1 Dimension',TO_TIMESTAMP('2009-05-14 12:00:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 12:00:50
-- Fin Report Summary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53835 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 14/05/2009 12:02:09
-- Fin Report Summary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53836,0,'IsUser2Dim',TO_TIMESTAMP('2009-05-14 12:02:03','YYYY-MM-DD HH24:MI:SS'),100,'Include User 2 as a cube dimension','D',NULL,'Y','User 2 Dimension','User 2 Dimension',TO_TIMESTAMP('2009-05-14 12:02:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 12:02:09
-- Fin Report Summary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53836 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 14/05/2009 12:03:07
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57566,53823,0,20,53202,'IsOrgTrxDim',TO_TIMESTAMP('2009-05-14 12:03:06','YYYY-MM-DD HH24:MI:SS'),100,'Include OrgTrx as a cube dimension','D',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','OrgTrx Dimension',0,TO_TIMESTAMP('2009-05-14 12:03:06','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:03:07
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57566 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:03:29
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57567,53822,0,20,53202,'IsActivityDim',TO_TIMESTAMP('2009-05-14 12:03:28','YYYY-MM-DD HH24:MI:SS'),100,'Include Activity as a cube dimension','D',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Activity Dimension',0,TO_TIMESTAMP('2009-05-14 12:03:28','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:03:29
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57567 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:03:58
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57568,53824,0,20,53202,'IsBPartnerDim',TO_TIMESTAMP('2009-05-14 12:03:57','YYYY-MM-DD HH24:MI:SS'),100,'Include Business Partner as a cube dimension','D',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Business Partner Dimension',0,TO_TIMESTAMP('2009-05-14 12:03:57','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:03:58
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57568 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:04:18
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57569,53825,0,20,53202,'IsCampaignDim',TO_TIMESTAMP('2009-05-14 12:04:18','YYYY-MM-DD HH24:MI:SS'),100,'Include Campaign as a cube dimension','D',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Campaign Dimension',0,TO_TIMESTAMP('2009-05-14 12:04:18','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:04:18
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57569 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:04:41
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57570,53826,0,20,53202,'IsLocFromDim',TO_TIMESTAMP('2009-05-14 12:04:41','YYYY-MM-DD HH24:MI:SS'),100,'Include Location From as a cube dimension','D',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Location From Dimension',0,TO_TIMESTAMP('2009-05-14 12:04:41','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:04:41
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57570 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:04:57
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57571,53827,0,20,53202,'IsLocToDim',TO_TIMESTAMP('2009-05-14 12:04:56','YYYY-MM-DD HH24:MI:SS'),100,'Include Location To as a cube dimension','D',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Location To  Dimension',0,TO_TIMESTAMP('2009-05-14 12:04:56','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:04:57
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57571 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:05:17
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57572,53828,0,20,53202,'IsProjectPhaseDim',TO_TIMESTAMP('2009-05-14 12:05:16','YYYY-MM-DD HH24:MI:SS'),100,'Include Project Phase as a cube dimension','D',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Project Phase  Dimension',0,TO_TIMESTAMP('2009-05-14 12:05:16','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:05:17
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57572 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:05:33
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57573,53829,0,20,53202,'IsProjectTaskDim',TO_TIMESTAMP('2009-05-14 12:05:33','YYYY-MM-DD HH24:MI:SS'),100,'Include Project Task as a cube dimension','D',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Project Task  Dimension',0,TO_TIMESTAMP('2009-05-14 12:05:33','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:05:33
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57573 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:05:51
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57574,53830,0,20,53202,'IsProjectDim',TO_TIMESTAMP('2009-05-14 12:05:50','YYYY-MM-DD HH24:MI:SS'),100,'Include Project as a cube dimension','D',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Project Dimension',0,TO_TIMESTAMP('2009-05-14 12:05:50','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:05:51
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57574 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:06:15
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57575,53831,0,20,53202,'IsSalesRegionDim',TO_TIMESTAMP('2009-05-14 12:06:15','YYYY-MM-DD HH24:MI:SS'),100,'Include Sales Region as a cube dimension','D',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Sales Region Dimension',0,TO_TIMESTAMP('2009-05-14 12:06:15','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:06:15
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57575 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:06:37
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57576,53832,0,20,53202,'IsSubAcctDim',TO_TIMESTAMP('2009-05-14 12:06:36','YYYY-MM-DD HH24:MI:SS'),100,'Include Sub Acct as a cube dimension','D',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Sub Acct Dimension',0,TO_TIMESTAMP('2009-05-14 12:06:36','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:06:37
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57576 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:07:11
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57577,53833,0,20,53202,'IsGLBudgetDim',TO_TIMESTAMP('2009-05-14 12:07:10','YYYY-MM-DD HH24:MI:SS'),100,'Include GL Budget as a cube dimension','D',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','GL Budget Dimension',0,TO_TIMESTAMP('2009-05-14 12:07:10','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:07:11
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57577 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:07:32
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57578,53834,0,20,53202,'IsProductDim',TO_TIMESTAMP('2009-05-14 12:07:32','YYYY-MM-DD HH24:MI:SS'),100,'Include Product as a cube dimension','D',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Product Dimension',0,TO_TIMESTAMP('2009-05-14 12:07:32','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:07:32
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57578 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:07:50
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57579,53835,0,20,53202,'IsUser1Dim',TO_TIMESTAMP('2009-05-14 12:07:49','YYYY-MM-DD HH24:MI:SS'),100,'Include User 1 as a cube dimension','D',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','User 1 Dimension',0,TO_TIMESTAMP('2009-05-14 12:07:49','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:07:50
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57579 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:08:10
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57580,53836,0,20,53202,'IsUser2Dim',TO_TIMESTAMP('2009-05-14 12:08:09','YYYY-MM-DD HH24:MI:SS'),100,'Include User 2 as a cube dimension','D',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','User 2 Dimension',0,TO_TIMESTAMP('2009-05-14 12:08:09','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:08:10
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57580 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:09:13
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57581,348,0,20,53202,'IsActive',TO_TIMESTAMP('2009-05-14 12:09:11','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system','D',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Active',0,TO_TIMESTAMP('2009-05-14 12:09:11','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:09:13
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57581 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:10:04
-- Fin Report Summary
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,EntityType,HasTree,ImportFields,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53219,53202,53078,TO_TIMESTAMP('2009-05-14 12:10:03','YYYY-MM-DD HH24:MI:SS'),100,'D','N','N','Y','N','N','Y','N','Y','N','N','Report Cube','N',10,0,TO_TIMESTAMP('2009-05-14 12:10:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 12:10:04
-- Fin Report Summary
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53219 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- 14/05/2009 12:10:18
-- Fin Report Summary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57581,57012,0,53219,TO_TIMESTAMP('2009-05-14 12:10:17','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system',1,'D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','N','Active',TO_TIMESTAMP('2009-05-14 12:10:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 12:10:18
-- Fin Report Summary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57012 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 14/05/2009 12:10:19
-- Fin Report Summary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57567,57013,0,53219,TO_TIMESTAMP('2009-05-14 12:10:18','YYYY-MM-DD HH24:MI:SS'),100,'Include Activity as a cube dimension',1,'D','Y','Y','Y','N','N','N','N','N','Activity Dimension',TO_TIMESTAMP('2009-05-14 12:10:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 12:10:19
-- Fin Report Summary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57013 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 14/05/2009 12:10:20
-- Fin Report Summary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57568,57014,0,53219,TO_TIMESTAMP('2009-05-14 12:10:19','YYYY-MM-DD HH24:MI:SS'),100,'Include Business Partner as a cube dimension',1,'D','Y','Y','Y','N','N','N','N','N','Business Partner Dimension',TO_TIMESTAMP('2009-05-14 12:10:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 12:10:20
-- Fin Report Summary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57014 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 14/05/2009 12:10:21
-- Fin Report Summary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57558,57015,0,53219,TO_TIMESTAMP('2009-05-14 12:10:20','YYYY-MM-DD HH24:MI:SS'),100,'Accounting Calendar Name',22,'D','The Calendar uniquely identifies an accounting calendar.  Multiple calendars can be used.  For example you may need a standard calendar that runs from Jan 1 to Dec 31 and a fiscal calendar that runs from July 1 to June 30.','Y','Y','Y','N','N','N','N','N','Calendar',TO_TIMESTAMP('2009-05-14 12:10:20','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 12:10:21
-- Fin Report Summary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57015 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 14/05/2009 12:10:22
-- Fin Report Summary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57569,57016,0,53219,TO_TIMESTAMP('2009-05-14 12:10:21','YYYY-MM-DD HH24:MI:SS'),100,'Include Campaign as a cube dimension',1,'D','Y','Y','Y','N','N','N','N','N','Campaign Dimension',TO_TIMESTAMP('2009-05-14 12:10:21','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 12:10:22
-- Fin Report Summary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57016 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 14/05/2009 12:10:23
-- Fin Report Summary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57556,57017,0,53219,TO_TIMESTAMP('2009-05-14 12:10:22','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.',22,'D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','N','Client',TO_TIMESTAMP('2009-05-14 12:10:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 12:10:23
-- Fin Report Summary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57017 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 14/05/2009 12:10:23
-- Fin Report Summary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57561,57018,0,53219,TO_TIMESTAMP('2009-05-14 12:10:23','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record',255,'D','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','N','Description',TO_TIMESTAMP('2009-05-14 12:10:23','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 12:10:23
-- Fin Report Summary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57018 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 14/05/2009 12:10:24
-- Fin Report Summary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57577,57019,0,53219,TO_TIMESTAMP('2009-05-14 12:10:23','YYYY-MM-DD HH24:MI:SS'),100,'Include GL Budget as a cube dimension',1,'D','Y','Y','Y','N','N','N','N','N','GL Budget Dimension',TO_TIMESTAMP('2009-05-14 12:10:23','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 12:10:24
-- Fin Report Summary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57019 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 14/05/2009 12:10:25
-- Fin Report Summary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57570,57020,0,53219,TO_TIMESTAMP('2009-05-14 12:10:24','YYYY-MM-DD HH24:MI:SS'),100,'Include Location From as a cube dimension',1,'D','Y','Y','Y','N','N','N','N','N','Location From Dimension',TO_TIMESTAMP('2009-05-14 12:10:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 12:10:25
-- Fin Report Summary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57020 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 14/05/2009 12:10:26
-- Fin Report Summary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57571,57021,0,53219,TO_TIMESTAMP('2009-05-14 12:10:25','YYYY-MM-DD HH24:MI:SS'),100,'Include Location To as a cube dimension',1,'D','Y','Y','Y','N','N','N','N','N','Location To  Dimension',TO_TIMESTAMP('2009-05-14 12:10:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 12:10:26
-- Fin Report Summary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57021 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 14/05/2009 12:10:26
-- Fin Report Summary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57563,57022,0,53219,TO_TIMESTAMP('2009-05-14 12:10:26','YYYY-MM-DD HH24:MI:SS'),100,'Alphanumeric identifier of the entity',60,'D','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','N','Name',TO_TIMESTAMP('2009-05-14 12:10:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 12:10:26
-- Fin Report Summary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57022 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 14/05/2009 12:10:27
-- Fin Report Summary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57566,57023,0,53219,TO_TIMESTAMP('2009-05-14 12:10:26','YYYY-MM-DD HH24:MI:SS'),100,'Include OrgTrx as a cube dimension',1,'D','Y','Y','Y','N','N','N','N','N','OrgTrx Dimension',TO_TIMESTAMP('2009-05-14 12:10:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 12:10:28
-- Fin Report Summary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57023 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 14/05/2009 12:10:29
-- Fin Report Summary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57557,57024,0,53219,TO_TIMESTAMP('2009-05-14 12:10:28','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client',22,'D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','N','N','Organization',TO_TIMESTAMP('2009-05-14 12:10:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 12:10:29
-- Fin Report Summary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57024 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 14/05/2009 12:10:29
-- Fin Report Summary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57578,57025,0,53219,TO_TIMESTAMP('2009-05-14 12:10:29','YYYY-MM-DD HH24:MI:SS'),100,'Include Product as a cube dimension',1,'D','Y','Y','Y','N','N','N','N','N','Product Dimension',TO_TIMESTAMP('2009-05-14 12:10:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 12:10:29
-- Fin Report Summary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57025 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 14/05/2009 12:10:30
-- Fin Report Summary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57574,57026,0,53219,TO_TIMESTAMP('2009-05-14 12:10:29','YYYY-MM-DD HH24:MI:SS'),100,'Include Project as a cube dimension',1,'D','Y','Y','Y','N','N','N','N','N','Project Dimension',TO_TIMESTAMP('2009-05-14 12:10:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 12:10:30
-- Fin Report Summary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57026 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 14/05/2009 12:10:31
-- Fin Report Summary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57572,57027,0,53219,TO_TIMESTAMP('2009-05-14 12:10:30','YYYY-MM-DD HH24:MI:SS'),100,'Include Project Phase as a cube dimension',1,'D','Y','Y','Y','N','N','N','N','N','Project Phase  Dimension',TO_TIMESTAMP('2009-05-14 12:10:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 12:10:31
-- Fin Report Summary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57027 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 14/05/2009 12:10:32
-- Fin Report Summary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57573,57028,0,53219,TO_TIMESTAMP('2009-05-14 12:10:31','YYYY-MM-DD HH24:MI:SS'),100,'Include Project Task as a cube dimension',1,'D','Y','Y','Y','N','N','N','N','N','Project Task  Dimension',TO_TIMESTAMP('2009-05-14 12:10:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 12:10:32
-- Fin Report Summary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57028 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 14/05/2009 12:10:42
-- Fin Report Summary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57562,57029,0,53219,TO_TIMESTAMP('2009-05-14 12:10:32','YYYY-MM-DD HH24:MI:SS'),100,'Define reporting cube for pre-calculation of summary accounting data.',22,'D','Summary data will be generated for each period of the selected calendar, grouped by the selected dimensions..','Y','Y','N','N','N','N','N','N','Report Cube',TO_TIMESTAMP('2009-05-14 12:10:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 12:10:42
-- Fin Report Summary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57029 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 14/05/2009 12:10:44
-- Fin Report Summary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57575,57030,0,53219,TO_TIMESTAMP('2009-05-14 12:10:42','YYYY-MM-DD HH24:MI:SS'),100,'Include Sales Region as a cube dimension',1,'D','Y','Y','Y','N','N','N','N','N','Sales Region Dimension',TO_TIMESTAMP('2009-05-14 12:10:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 12:10:44
-- Fin Report Summary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57030 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 14/05/2009 12:10:44
-- Fin Report Summary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57576,57031,0,53219,TO_TIMESTAMP('2009-05-14 12:10:44','YYYY-MM-DD HH24:MI:SS'),100,'Include Sub Acct as a cube dimension',1,'D','Y','Y','Y','N','N','N','N','N','Sub Acct Dimension',TO_TIMESTAMP('2009-05-14 12:10:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 12:10:44
-- Fin Report Summary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57031 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 14/05/2009 12:10:45
-- Fin Report Summary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57579,57032,0,53219,TO_TIMESTAMP('2009-05-14 12:10:44','YYYY-MM-DD HH24:MI:SS'),100,'Include User 1 as a cube dimension',1,'D','Y','Y','Y','N','N','N','N','N','User 1 Dimension',TO_TIMESTAMP('2009-05-14 12:10:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 12:10:45
-- Fin Report Summary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57032 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 14/05/2009 12:10:46
-- Fin Report Summary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57580,57033,0,53219,TO_TIMESTAMP('2009-05-14 12:10:45','YYYY-MM-DD HH24:MI:SS'),100,'Include User 2 as a cube dimension',1,'D','Y','Y','Y','N','N','N','N','N','User 2 Dimension',TO_TIMESTAMP('2009-05-14 12:10:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 12:10:46
-- Fin Report Summary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57033 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 14/05/2009 12:13:44
-- Fin Report Summary
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=57017
;

-- 14/05/2009 12:13:44
-- Fin Report Summary
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=57024
;

-- 14/05/2009 12:13:44
-- Fin Report Summary
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=57022
;

-- 14/05/2009 12:13:44
-- Fin Report Summary
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=57018
;

-- 14/05/2009 12:13:44
-- Fin Report Summary
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=57015
;

-- 14/05/2009 12:13:44
-- Fin Report Summary
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=57012
;

-- 14/05/2009 12:13:44
-- Fin Report Summary
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=57025
;

-- 14/05/2009 12:13:44
-- Fin Report Summary
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=57014
;

-- 14/05/2009 12:13:44
-- Fin Report Summary
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=57030
;

-- 14/05/2009 12:13:44
-- Fin Report Summary
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=57023
;

-- 14/05/2009 12:13:44
-- Fin Report Summary
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=57013
;

-- 14/05/2009 12:13:44
-- Fin Report Summary
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=57016
;

-- 14/05/2009 12:13:44
-- Fin Report Summary
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=57026
;

-- 14/05/2009 12:13:44
-- Fin Report Summary
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=57027
;

-- 14/05/2009 12:13:44
-- Fin Report Summary
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=57028
;

-- 14/05/2009 12:13:44
-- Fin Report Summary
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=57019
;

-- 14/05/2009 12:13:44
-- Fin Report Summary
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=57020
;

-- 14/05/2009 12:13:44
-- Fin Report Summary
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=57021
;

-- 14/05/2009 12:13:44
-- Fin Report Summary
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=57031
;

-- 14/05/2009 12:13:44
-- Fin Report Summary
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=57032
;

-- 14/05/2009 12:13:44
-- Fin Report Summary
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=57033
;

-- 14/05/2009 12:13:56
-- Fin Report Summary
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2009-05-14 12:13:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57024
;

-- 14/05/2009 12:14:08
-- Fin Report Summary
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2009-05-14 12:14:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57012
;

-- 14/05/2009 12:14:12
-- Fin Report Summary
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2009-05-14 12:14:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57014
;

-- 14/05/2009 12:14:17
-- Fin Report Summary
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2009-05-14 12:14:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57023
;

-- 14/05/2009 12:14:23
-- Fin Report Summary
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2009-05-14 12:14:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57016
;

-- 14/05/2009 12:14:27
-- Fin Report Summary
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2009-05-14 12:14:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57027
;

-- 14/05/2009 12:14:36
-- Fin Report Summary
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2009-05-14 12:14:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57019
;

-- 14/05/2009 12:14:39
-- Fin Report Summary
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2009-05-14 12:14:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57021
;

-- 14/05/2009 12:14:45
-- Fin Report Summary
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2009-05-14 12:14:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57033
;

-- 14/05/2009 12:15:41
-- Fin Report Summary
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,"action",Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53214,0,53078,'W',TO_TIMESTAMP('2009-05-14 12:15:40','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','N','N','Report Cube',TO_TIMESTAMP('2009-05-14 12:15:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 12:15:41
-- Fin Report Summary
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53214 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- 14/05/2009 12:15:41
-- Fin Report Summary
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 53214, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53214)
;

-- 14/05/2009 12:15:57
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- 14/05/2009 12:15:57
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- 14/05/2009 12:15:57
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- 14/05/2009 12:15:57
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=166
;

-- 14/05/2009 12:15:57
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- 14/05/2009 12:15:57
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- 14/05/2009 12:15:57
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- 14/05/2009 12:15:57
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- 14/05/2009 12:15:57
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- 14/05/2009 12:15:57
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- 14/05/2009 12:15:57
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=10, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53014
;

-- 14/05/2009 12:15:57
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=11, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53108
;

-- 14/05/2009 12:15:57
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=280, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=283
;

-- 14/05/2009 12:15:57
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=280, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=282
;

-- 14/05/2009 12:15:57
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=280, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=548
;

-- 14/05/2009 12:15:57
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=280, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53214
;

-- 14/05/2009 12:15:57
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=280, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=281
;

-- 14/05/2009 12:15:57
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=280, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=350
;

-- 14/05/2009 12:15:57
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=280, SeqNo=6, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=502
;

-- 14/05/2009 12:19:28
-- Fin Report Summary
CREATE TABLE PA_ReportCube (AD_Client_ID NUMERIC(10) NOT NULL, AD_Org_ID NUMERIC(10) NOT NULL, C_Calendar_ID NUMERIC(10) NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, Description VARCHAR(255) DEFAULT NULL , IsActive CHAR(1) DEFAULT NULL CHECK (IsActive IN ('Y','N')), IsActivityDim CHAR(1) DEFAULT NULL CHECK (IsActivityDim IN ('Y','N')), IsBPartnerDim CHAR(1) DEFAULT NULL CHECK (IsBPartnerDim IN ('Y','N')), IsCampaignDim CHAR(1) DEFAULT NULL CHECK (IsCampaignDim IN ('Y','N')), IsGLBudgetDim CHAR(1) DEFAULT NULL CHECK (IsGLBudgetDim IN ('Y','N')), IsLocFromDim CHAR(1) DEFAULT NULL CHECK (IsLocFromDim IN ('Y','N')), IsLocToDim CHAR(1) DEFAULT NULL CHECK (IsLocToDim IN ('Y','N')), IsOrgTrxDim CHAR(1) DEFAULT NULL CHECK (IsOrgTrxDim IN ('Y','N')), IsProductDim CHAR(1) DEFAULT NULL CHECK (IsProductDim IN ('Y','N')), IsProjectDim CHAR(1) DEFAULT NULL CHECK (IsProjectDim IN ('Y','N')), IsProjectPhaseDim CHAR(1) DEFAULT NULL CHECK (IsProjectPhaseDim IN ('Y','N')), IsProjectTaskDim CHAR(1) DEFAULT NULL CHECK (IsProjectTaskDim IN ('Y','N')), IsSalesRegionDim CHAR(1) DEFAULT NULL CHECK (IsSalesRegionDim IN ('Y','N')), IsSubAcctDim CHAR(1) DEFAULT NULL CHECK (IsSubAcctDim IN ('Y','N')), IsUser1Dim CHAR(1) DEFAULT NULL CHECK (IsUser1Dim IN ('Y','N')), IsUser2Dim CHAR(1) DEFAULT NULL CHECK (IsUser2Dim IN ('Y','N')), Name VARCHAR(60) NOT NULL, PA_ReportCube_ID NUMERIC(10) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, CONSTRAINT PA_ReportCube_Key PRIMARY KEY (PA_ReportCube_ID))
;

-- 14/05/2009 12:28:23
-- Fin Report Summary
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53299,TO_TIMESTAMP('2009-05-14 12:28:22','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','PA_ReportCube',TO_TIMESTAMP('2009-05-14 12:28:22','YYYY-MM-DD HH24:MI:SS'),100,'T')
;

-- 14/05/2009 12:28:23
-- Fin Report Summary
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53299 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- 14/05/2009 12:28:53
-- Fin Report Summary
INSERT INTO AD_Ref_Table (AD_Client_ID,AD_Display,AD_Key,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,Updated,UpdatedBy) VALUES (0,57563,57562,0,53299,53202,TO_TIMESTAMP('2009-05-14 12:28:53','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N',TO_TIMESTAMP('2009-05-14 12:28:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 12:29:18
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57582,53821,0,18,53299,445,'PA_ReportCube_ID',TO_TIMESTAMP('2009-05-14 12:29:17','YYYY-MM-DD HH24:MI:SS'),100,'Define reporting cube for pre-calculation of summary accounting data.','D',22,'Summary data will be generated for each period of the selected calendar, grouped by the selected dimensions..','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Report Cube',0,TO_TIMESTAMP('2009-05-14 12:29:17','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:29:18
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57582 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:29:23
-- Fin Report Summary
ALTER TABLE PA_Report ADD COLUMN PA_ReportCube_ID NUMERIC(10) DEFAULT NULL 
;

-- 14/05/2009 12:30:45
-- Fin Report Summary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57582,57034,0,372,TO_TIMESTAMP('2009-05-14 12:30:44','YYYY-MM-DD HH24:MI:SS'),100,'Define reporting cube for pre-calculation of summary accounting data.',0,'D','Summary data will be generated for each period of the selected calendar, grouped by the selected dimensions..','Y','Y','Y','N','N','N','N','N','Report Cube',95,0,TO_TIMESTAMP('2009-05-14 12:30:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 12:30:45
-- Fin Report Summary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57034 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 14/05/2009 12:33:00
-- Fin Report Summary
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,CopyColumnsFromTable,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,LoadSeq,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53203,'3','N',TO_TIMESTAMP('2009-05-14 12:33:00','YYYY-MM-DD HH24:MI:SS'),100,'D','N','Y','N','Y','N','N','N',0,'Accounting Fact summary','L','Fact_Acct_Summary',TO_TIMESTAMP('2009-05-14 12:33:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 12:33:00
-- Fin Report Summary
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53203 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- 14/05/2009 12:33:01
-- Fin Report Summary
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53296,TO_TIMESTAMP('2009-05-14 12:33:00','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table Fact_Acct_Summary',1,'Y','N','Y','Y','Fact_Acct_Summary','N',1000000,TO_TIMESTAMP('2009-05-14 12:33:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 14/05/2009 12:33:10
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57583,102,0,19,53203,'AD_Client_ID',TO_TIMESTAMP('2009-05-14 12:33:09','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.','D',10,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','N','N','Client',TO_TIMESTAMP('2009-05-14 12:33:09','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:33:10
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57583 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:33:11
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57584,113,0,19,53203,'AD_Org_ID',TO_TIMESTAMP('2009-05-14 12:33:10','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client','D',10,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','N','N','Organization',TO_TIMESTAMP('2009-05-14 12:33:10','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:33:11
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57584 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:33:12
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57585,53821,0,19,53203,'PA_ReportCube_ID',TO_TIMESTAMP('2009-05-14 12:33:11','YYYY-MM-DD HH24:MI:SS'),100,'Define reporting cube for pre-calculation of summary accounting data.','D',10,'Summary data will be generated for each period of the selected calendar, grouped by the selected dimensions..','Y','N','N','N','N','Y','N','N','N','Y','Report Cube',TO_TIMESTAMP('2009-05-14 12:33:11','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:33:12
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57585 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:33:12
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57586,181,0,19,53203,'C_AcctSchema_ID',TO_TIMESTAMP('2009-05-14 12:33:12','YYYY-MM-DD HH24:MI:SS'),100,'Rules for accounting','D',10,'An Accounting Schema defines the rules used in accounting such as costing method, currency and calendar','Y','N','N','N','N','Y','N','N','N','Y','Accounting Schema',TO_TIMESTAMP('2009-05-14 12:33:12','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:33:12
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57586 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:33:13
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57587,206,0,19,53203,'C_Period_ID',TO_TIMESTAMP('2009-05-14 12:33:12','YYYY-MM-DD HH24:MI:SS'),100,'Period of the Calendar','D',10,'The Period indicates an exclusive range of dates for a calendar.','Y','N','N','N','N','Y','N','N','N','Y','Period',TO_TIMESTAMP('2009-05-14 12:33:12','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:33:13
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57587 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:33:14
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57588,148,0,19,53203,'Account_ID',TO_TIMESTAMP('2009-05-14 12:33:13','YYYY-MM-DD HH24:MI:SS'),100,'Account used','D',10,'The (natural) account used','Y','N','N','N','N','Y','N','N','N','Y','Account',TO_TIMESTAMP('2009-05-14 12:33:13','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:33:14
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57588 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:33:15
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57589,514,0,20,53203,'PostingType',TO_TIMESTAMP('2009-05-14 12:33:14','YYYY-MM-DD HH24:MI:SS'),100,'The type of posted amount for the transaction','D',1,'The Posting Type indicates the type of amount (Actual, Budget, Reservation, Commitment, Statistical) the transaction.','Y','N','N','N','N','Y','N','N','N','Y','PostingType',TO_TIMESTAMP('2009-05-14 12:33:14','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:33:15
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57589 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:33:16
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57590,454,0,19,53203,'M_Product_ID',TO_TIMESTAMP('2009-05-14 12:33:15','YYYY-MM-DD HH24:MI:SS'),100,'Product, Service, Item','D',10,'Identifies an item which is either purchased or sold in this organization.','Y','N','N','N','N','N','N','N','N','Y','Product',TO_TIMESTAMP('2009-05-14 12:33:15','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:33:16
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57590 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:33:17
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57591,187,0,19,53203,'C_BPartner_ID',TO_TIMESTAMP('2009-05-14 12:33:16','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Business Partner','D',10,'A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','N','N','N','N','N','N','N','N','Y','Business Partner ',TO_TIMESTAMP('2009-05-14 12:33:16','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:33:17
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57591 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:33:17
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57592,208,0,19,53203,'C_Project_ID',TO_TIMESTAMP('2009-05-14 12:33:17','YYYY-MM-DD HH24:MI:SS'),100,'Financial Project','D',10,'A Project allows you to track and control internal or external activities.','Y','N','N','N','N','N','N','N','N','Y','Project',TO_TIMESTAMP('2009-05-14 12:33:17','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:33:17
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57592 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:33:18
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57593,112,0,19,53203,'AD_OrgTrx_ID',TO_TIMESTAMP('2009-05-14 12:33:17','YYYY-MM-DD HH24:MI:SS'),100,'Performing or initiating organization','D',10,'The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.','Y','N','N','N','N','N','N','N','N','Y','Trx Organization',TO_TIMESTAMP('2009-05-14 12:33:17','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:33:18
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57593 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:33:19
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57594,210,0,19,53203,'C_SalesRegion_ID',TO_TIMESTAMP('2009-05-14 12:33:18','YYYY-MM-DD HH24:MI:SS'),100,'Sales coverage region','D',10,'The Sales Region indicates a specific area of sales coverage.','Y','N','N','N','N','N','N','N','N','Y','Sales Region',TO_TIMESTAMP('2009-05-14 12:33:18','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:33:19
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57594 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:33:20
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57595,1005,0,19,53203,'C_Activity_ID',TO_TIMESTAMP('2009-05-14 12:33:19','YYYY-MM-DD HH24:MI:SS'),100,'Business Activity','D',10,'Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','N','N','N','N','N','N','N','N','Y','Activity',TO_TIMESTAMP('2009-05-14 12:33:19','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:33:20
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57595 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:33:20
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57596,550,0,19,53203,'C_Campaign_ID',TO_TIMESTAMP('2009-05-14 12:33:20','YYYY-MM-DD HH24:MI:SS'),100,'Marketing Campaign','D',10,'The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','N','N','N','N','N','N','N','N','Y','Campaign',TO_TIMESTAMP('2009-05-14 12:33:20','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:33:21
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57596 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:33:21
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57597,201,0,19,53203,'C_LocTo_ID',TO_TIMESTAMP('2009-05-14 12:33:21','YYYY-MM-DD HH24:MI:SS'),100,'Location that inventory was moved to','D',10,'The Location To indicates the location that a product was moved to.','Y','N','N','N','N','N','N','N','N','Y','Location To',TO_TIMESTAMP('2009-05-14 12:33:21','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:33:21
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57597 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:33:22
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57598,200,0,19,53203,'C_LocFrom_ID',TO_TIMESTAMP('2009-05-14 12:33:21','YYYY-MM-DD HH24:MI:SS'),100,'Location that inventory was moved from','D',10,'The Location From indicates the location that a product was moved from.','Y','N','N','N','N','N','N','N','N','Y','Location From',TO_TIMESTAMP('2009-05-14 12:33:21','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:33:22
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57598 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:33:23
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57599,613,0,19,53203,'User1_ID',TO_TIMESTAMP('2009-05-14 12:33:22','YYYY-MM-DD HH24:MI:SS'),100,'User defined list element #1','D',10,'The user defined element displays the optional elements that have been defined for this account combination.','Y','N','N','N','N','N','N','N','N','Y','User List 1',TO_TIMESTAMP('2009-05-14 12:33:22','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:33:23
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57599 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:33:24
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57600,614,0,19,53203,'User2_ID',TO_TIMESTAMP('2009-05-14 12:33:23','YYYY-MM-DD HH24:MI:SS'),100,'User defined list element #2','D',10,'The user defined element displays the optional elements that have been defined for this account combination.','Y','N','N','N','N','N','N','N','N','Y','User List 2',TO_TIMESTAMP('2009-05-14 12:33:23','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:33:24
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57600 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:33:25
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57601,308,0,19,53203,'GL_Budget_ID',TO_TIMESTAMP('2009-05-14 12:33:24','YYYY-MM-DD HH24:MI:SS'),100,'General Ledger Budget','D',10,'The General Ledger Budget identifies a user defined budget.  These can be used in reporting as a comparison against your actual amounts.','Y','N','N','N','N','N','N','N','N','Y','Budget',TO_TIMESTAMP('2009-05-14 12:33:24','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:33:25
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57601 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:33:26
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57602,162,0,12,53203,'AmtAcctDr',TO_TIMESTAMP('2009-05-14 12:33:25','YYYY-MM-DD HH24:MI:SS'),100,'Accounted Debit Amount','D',22,'The Account Debit Amount indicates the transaction amount converted to this organization''s accounting currency','Y','N','N','N','N','Y','N','N','N','Y','Accounted Debit',TO_TIMESTAMP('2009-05-14 12:33:25','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:33:26
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57602 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:33:26
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57603,161,0,12,53203,'AmtAcctCr',TO_TIMESTAMP('2009-05-14 12:33:26','YYYY-MM-DD HH24:MI:SS'),100,'Accounted Credit Amount','D',22,'The Account Credit Amount indicates the transaction amount converted to this organization''s accounting currency','Y','N','N','N','N','Y','N','N','N','Y','Accounted Credit',TO_TIMESTAMP('2009-05-14 12:33:26','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:33:26
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57603 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:33:27
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57604,526,0,29,53203,'Qty',TO_TIMESTAMP('2009-05-14 12:33:26','YYYY-MM-DD HH24:MI:SS'),100,'Quantity','D',22,'The Quantity indicates the number of a specific product or item for this document.','Y','N','N','N','N','Y','N','N','N','Y','Quantity',TO_TIMESTAMP('2009-05-14 12:33:26','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:33:27
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57604 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:33:28
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57605,246,0,18,110,53203,'CreatedBy',TO_TIMESTAMP('2009-05-14 12:33:27','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','D',10,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','N','N','Created By',TO_TIMESTAMP('2009-05-14 12:33:27','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:33:28
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57605 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:33:29
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57606,245,0,16,53203,'Created',TO_TIMESTAMP('2009-05-14 12:33:28','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','D',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','N','N','Created',TO_TIMESTAMP('2009-05-14 12:33:28','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:33:29
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57606 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:33:30
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57607,608,0,18,110,53203,'UpdatedBy',TO_TIMESTAMP('2009-05-14 12:33:29','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','D',10,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','N','N','Updated By',TO_TIMESTAMP('2009-05-14 12:33:29','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:33:30
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57607 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:33:30
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57608,607,0,16,53203,'Updated',TO_TIMESTAMP('2009-05-14 12:33:30','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','D',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','N','N','Updated',TO_TIMESTAMP('2009-05-14 12:33:30','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:33:30
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57608 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:33:31
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57609,348,0,20,53203,'IsActive',TO_TIMESTAMP('2009-05-14 12:33:30','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system','D',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','N','Y','Active',TO_TIMESTAMP('2009-05-14 12:33:30','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:33:31
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57609 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:33:32
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57610,2876,0,19,53203,'C_SubAcct_ID',TO_TIMESTAMP('2009-05-14 12:33:31','YYYY-MM-DD HH24:MI:SS'),100,'Sub account for Element Value','D',10,'The Element Value (e.g. Account) may have optional sub accounts for further detail. The sub account is dependent on the value of the account, so a further specification. If the sub-accounts are more or less the same, consider using another accounting dimension.','Y','N','N','N','N','N','N','N','N','Y','Sub Account',TO_TIMESTAMP('2009-05-14 12:33:31','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:33:32
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57610 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:33:33
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57611,2877,0,19,53203,'UserElement1_ID',TO_TIMESTAMP('2009-05-14 12:33:32','YYYY-MM-DD HH24:MI:SS'),100,'User defined accounting Element','D',10,'A user defined accounting element referres to a Adempiere table. This allows to use any table content as an accounting dimension (e.g. Project Task).  Note that User Elements are optional and are populated from the context of the document (i.e. not requested)','Y','N','N','N','N','N','N','N','N','Y','User Element 1',TO_TIMESTAMP('2009-05-14 12:33:32','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:33:33
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57611 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:33:34
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57612,2878,0,19,53203,'UserElement2_ID',TO_TIMESTAMP('2009-05-14 12:33:33','YYYY-MM-DD HH24:MI:SS'),100,'User defined accounting Element','D',10,'A user defined accounting element referres to a Adempiere table. This allows to use any table content as an accounting dimension (e.g. Project Task).  Note that User Elements are optional and are populated from the context of the document (i.e. not requested) ','Y','N','N','N','N','N','N','N','N','Y','User Element 2',TO_TIMESTAMP('2009-05-14 12:33:33','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:33:34
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57612 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:33:34
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57613,2073,0,19,53203,'C_ProjectPhase_ID',TO_TIMESTAMP('2009-05-14 12:33:34','YYYY-MM-DD HH24:MI:SS'),100,'Phase of a Project','D',10,'Y','N','N','N','N','N','N','N','N','Y','Project Phase',TO_TIMESTAMP('2009-05-14 12:33:34','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:33:34
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57613 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:33:35
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57614,2074,0,19,53203,'C_ProjectTask_ID',TO_TIMESTAMP('2009-05-14 12:33:34','YYYY-MM-DD HH24:MI:SS'),100,'Actual Project Task in a Phase','D',10,'A Project Task in a Project Phase represents the actual work.','Y','N','N','N','N','N','N','N','N','Y','Project Task',TO_TIMESTAMP('2009-05-14 12:33:34','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 14/05/2009 12:33:35
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57614 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 14/05/2009 12:35:08
-- Fin Report Summary
CREATE TABLE Fact_Acct_Summary (AD_Client_ID NUMERIC(10) NOT NULL, AD_OrgTrx_ID NUMERIC(10) DEFAULT NULL , AD_Org_ID NUMERIC(10) NOT NULL, Account_ID NUMERIC(10) NOT NULL, AmtAcctCr NUMERIC NOT NULL, AmtAcctDr NUMERIC NOT NULL, C_AcctSchema_ID NUMERIC(10) NOT NULL, C_Activity_ID NUMERIC(10) DEFAULT NULL , C_BPartner_ID NUMERIC(10) DEFAULT NULL , C_Campaign_ID NUMERIC(10) DEFAULT NULL , C_LocFrom_ID NUMERIC(10) DEFAULT NULL , C_LocTo_ID NUMERIC(10) DEFAULT NULL , C_Period_ID NUMERIC(10) NOT NULL, C_ProjectPhase_ID NUMERIC(10) DEFAULT NULL , C_ProjectTask_ID NUMERIC(10) DEFAULT NULL , C_Project_ID NUMERIC(10) DEFAULT NULL , C_SalesRegion_ID NUMERIC(10) DEFAULT NULL , C_SubAcct_ID NUMERIC(10) DEFAULT NULL , Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, GL_Budget_ID NUMERIC(10) DEFAULT NULL , IsActive CHAR(1) CHECK (IsActive IN ('Y','N')) NOT NULL, M_Product_ID NUMERIC(10) DEFAULT NULL , PA_ReportCube_ID NUMERIC(10) NOT NULL, PostingType CHAR(1) NOT NULL, Qty NUMERIC NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, User1_ID NUMERIC(10) DEFAULT NULL , User2_ID NUMERIC(10) DEFAULT NULL , UserElement1_ID NUMERIC(10) DEFAULT NULL , UserElement2_ID NUMERIC(10) DEFAULT NULL )
;

-- 15/05/2009 0:43:09
-- Fin Report Summary
INSERT INTO AD_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AccessLevel,Classname,Created,CreatedBy,Description,EntityType,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,IsServerProcess,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value) VALUES (0,0,53166,'3','org.compiere.process.FactAcctSummary',TO_TIMESTAMP('2009-05-15 00:43:07','YYYY-MM-DD HH24:MI:SS'),100,'Recalculate summary facts based on report cube definitions.','D','Y','N','N','N','N','Recalculate Fact Summary','Y',0,0,TO_TIMESTAMP('2009-05-15 00:43:07','YYYY-MM-DD HH24:MI:SS'),100,'FactAcctSummary')
;

-- 15/05/2009 0:43:09
-- Fin Report Summary
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53166 AND EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_ID!=t.AD_Process_ID)
;

-- 15/05/2009 0:44:21
-- Fin Report Summary
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Process_ID,"action",Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53215,0,53166,'P',TO_TIMESTAMP('2009-05-15 00:44:20','YYYY-MM-DD HH24:MI:SS'),100,'Recalculate Fact Summary','D','Y','N','N','N','Recalculate Fact Summary',TO_TIMESTAMP('2009-05-15 00:44:20','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 15/05/2009 0:44:21
-- Fin Report Summary
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53215 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- 15/05/2009 0:44:21
-- Fin Report Summary
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 53215, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53215)
;

-- 15/05/2009 0:44:33
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- 15/05/2009 0:44:33
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- 15/05/2009 0:44:33
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- 15/05/2009 0:44:33
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=166
;

-- 15/05/2009 0:44:33
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- 15/05/2009 0:44:33
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- 15/05/2009 0:44:33
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- 15/05/2009 0:44:33
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- 15/05/2009 0:44:33
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- 15/05/2009 0:44:33
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- 15/05/2009 0:44:33
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=10, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53014
;

-- 15/05/2009 0:44:33
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=11, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53108
;

-- 15/05/2009 0:44:33
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=280, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=283
;

-- 15/05/2009 0:44:33
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=280, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=282
;

-- 15/05/2009 0:44:33
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=280, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=548
;

-- 15/05/2009 0:44:33
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=280, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53214
;

-- 15/05/2009 0:44:33
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=280, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53215
;

-- 15/05/2009 0:44:33
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=280, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=281
;

-- 15/05/2009 0:44:33
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=280, SeqNo=6, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=350
;

-- 15/05/2009 0:44:33
-- Fin Report Summary
UPDATE AD_TreeNodeMM SET Parent_ID=280, SeqNo=7, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=502
;

-- 19/05/2009 23:03:38
-- Fin Report Summary
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53308,TO_TIMESTAMP('2009-05-19 23:03:36','YYYY-MM-DD HH24:MI:SS'),100,1000000,50000,'Table PA_ReportCube',1,'Y','N','Y','Y','PA_ReportCube','N',1000000,TO_TIMESTAMP('2009-05-19 23:03:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 19/05/2009 23:10:04
-- Fin Report Summary
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52050,'PA_ReportCube.C_Calendar_ID=@C_Calendar_ID@',TO_TIMESTAMP('2009-05-19 23:10:03','YYYY-MM-DD HH24:MI:SS'),100,'U','Y','PA_ReportCube of Calendar','S',TO_TIMESTAMP('2009-05-19 23:10:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 19/05/2009 23:10:22
-- Fin Report Summary
UPDATE AD_Column SET AD_Val_Rule_ID=52050,Updated=TO_TIMESTAMP('2009-05-19 23:10:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=57582
;

-- 19/05/2009 23:16:03
-- Fin Report Summary
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,53821,0,202,53313,18,53299,52050,'PA_ReportCube_ID',TO_TIMESTAMP('2009-05-19 23:16:02','YYYY-MM-DD HH24:MI:SS'),100,'@PA_ReportCube_ID@','Optional report cube to retrieve pre-calculated summary date from.','D',10,'Y','Y','N','N','Report Cube',120,TO_TIMESTAMP('2009-05-19 23:16:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 19/05/2009 23:16:03
-- Fin Report Summary
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53313 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- 19/05/2009 23:16:14
-- Fin Report Summary
UPDATE AD_Process_Para SET Description='Optional report cube to retrieve pre-calculated summary data from.',Updated=TO_TIMESTAMP('2009-05-19 23:16:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53313
;

-- 19/05/2009 23:16:14
-- Fin Report Summary
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53313
;

-- 19/05/2009 23:19:33
-- Fin Report Summary
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,0,53166,53314,20,'Reset',TO_TIMESTAMP('2009-05-19 23:19:32','YYYY-MM-DD HH24:MI:SS'),100,'Delete all existing summary data and recalculate.','D',0,'If not selected, only those periods with recently posted accounting facts will be recalculated.','Y','Y','N','N','Full rebuild',10,TO_TIMESTAMP('2009-05-19 23:19:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 19/05/2009 23:19:33
-- Fin Report Summary
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53314 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- 19/05/2009 23:20:37
-- Fin Report Summary
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,0,53166,53315,18,53299,'PA_ReportCube_ID',TO_TIMESTAMP('2009-05-19 23:20:30','YYYY-MM-DD HH24:MI:SS'),100,'Recalculate only this cube.','D',0,'Y','Y','N','N','Report cube',20,TO_TIMESTAMP('2009-05-19 23:20:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 19/05/2009 23:20:37
-- Fin Report Summary
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53315 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- 19/05/2009 23:22:04
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57646,524,0,20,53202,'Processing',TO_TIMESTAMP('2009-05-19 23:22:02','YYYY-MM-DD HH24:MI:SS'),100,'N','D',1,'Y','Y','N','N','N','N','N','Y','N','N','N','N','Y','Process Now',0,TO_TIMESTAMP('2009-05-19 23:22:02','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 19/05/2009 23:22:04
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57646 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 19/05/2009 23:22:07
-- Fin Report Summary
ALTER TABLE PA_ReportCube ADD COLUMN Processing CHAR(1) DEFAULT 'N' CHECK (Processing IN ('Y','N')) NOT NULL
;

-- 19/05/2009 23:25:05
-- Fin Report Summary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53837,0,'LastRecalculated',TO_TIMESTAMP('2009-05-19 23:25:04','YYYY-MM-DD HH24:MI:SS'),100,'The time last recalculated.','D','Y','Last Recalculated','Last Recalculated',TO_TIMESTAMP('2009-05-19 23:25:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 19/05/2009 23:25:05
-- Fin Report Summary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53837 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 19/05/2009 23:25:41
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57647,53837,0,16,53202,'LastRecalculated',TO_TIMESTAMP('2009-05-19 23:25:35','YYYY-MM-DD HH24:MI:SS'),100,'The time last recalculated.','D',7,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Last Recalculated',0,TO_TIMESTAMP('2009-05-19 23:25:35','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 19/05/2009 23:25:41
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57647 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 19/05/2009 23:25:45
-- Fin Report Summary
ALTER TABLE PA_ReportCube ADD COLUMN LastRecalculated TIMESTAMP DEFAULT NULL 
;

-- 21/05/2009 23:43:51
-- Fin Report Summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57649,263,0,15,53203,'DateAcct',TO_TIMESTAMP('2009-05-21 23:43:48','YYYY-MM-DD HH24:MI:SS'),100,NULL,'Accounting Date','D',7,'The Accounting Date indicates the date to be used on the General Ledger account entries generated from this document. It is also used for any currency conversion.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Account Date',0,TO_TIMESTAMP('2009-05-21 23:43:48','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 21/05/2009 23:43:51
-- Fin Report Summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57649 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 21/05/2009 23:43:55
-- Fin Report Summary
ALTER TABLE Fact_Acct_Summary ADD COLUMN DateAcct TIMESTAMP DEFAULT NULL 
;

-- 3/06/2009 0:44:04
-- Fin Report Summary
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,0,53166,53316,20,'Force',TO_TIMESTAMP('2009-06-03 00:44:03','YYYY-MM-DD HH24:MI:SS'),100,'N','Force rebuild of cube even if locked.','D',1,'Y','Y','N','N','Force',30,TO_TIMESTAMP('2009-06-03 00:44:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 3/06/2009 0:44:04
-- Fin Report Summary
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53316 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- 3/06/2009 0:59:21
-- Fin Report Summary
UPDATE AD_Process_Para SET SeqNo=25,Updated=TO_TIMESTAMP('2009-06-03 00:59:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53314
;

-- 2/08/2009 22:41:35
-- Acct fact summary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53897,0,'IsUserElement2Dim',TO_TIMESTAMP('2009-08-02 22:41:33','YYYY-MM-DD HH24:MI:SS'),100,'Include User Element 2 as a cube dimension','D','Y','User Element 2 Dimension','User Element 2 Dimension',TO_TIMESTAMP('2009-08-02 22:41:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/08/2009 22:41:35
-- Acct fact summary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53897 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 2/08/2009 22:41:49
-- Acct fact summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57953,53897,0,20,53202,'IsUserElement2Dim',TO_TIMESTAMP('2009-08-02 22:41:47','YYYY-MM-DD HH24:MI:SS'),100,'Include User Element 2 as a cube dimension','U',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','User Element 2 Dimension',0,TO_TIMESTAMP('2009-08-02 22:41:47','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/08/2009 22:41:49
-- Acct fact summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57953 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 2/08/2009 22:41:54
-- Acct fact summary
ALTER TABLE PA_ReportCube ADD COLUMN IsUserElement2Dim CHAR(1) DEFAULT NULL CHECK (IsUserElement2Dim IN ('Y','N'))
;

-- 2/08/2009 22:42:24
-- Acct fact summary
UPDATE AD_Element SET ColumnName='IsUserElement1Dim', Description='Include User Element 1 as a cube dimension', Name='User Element 1 Dimension', PrintName='User Element 1 Dimension',Updated=TO_TIMESTAMP('2009-08-02 22:42:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53897
;

-- 2/08/2009 22:42:24
-- Acct fact summary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53897
;

-- 2/08/2009 22:42:24
-- Acct fact summary
UPDATE AD_Column SET ColumnName='IsUserElement1Dim', Name='User Element 1 Dimension', Description='Include User Element 1 as a cube dimension', Help=NULL WHERE AD_Element_ID=53897
;

-- 2/08/2009 22:42:24
-- Acct fact summary
UPDATE AD_Process_Para SET ColumnName='IsUserElement1Dim', Name='User Element 1 Dimension', Description='Include User Element 1 as a cube dimension', Help=NULL, AD_Element_ID=53897 WHERE UPPER(ColumnName)='ISUSERELEMENT1DIM' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- 2/08/2009 22:42:24
-- Acct fact summary
UPDATE AD_Process_Para SET ColumnName='IsUserElement1Dim', Name='User Element 1 Dimension', Description='Include User Element 1 as a cube dimension', Help=NULL WHERE AD_Element_ID=53897 AND IsCentrallyMaintained='Y'
;

-- 2/08/2009 22:42:24
-- Acct fact summary
UPDATE AD_Field SET Name='User Element 1 Dimension', Description='Include User Element 1 as a cube dimension', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53897) AND IsCentrallyMaintained='Y'
;

-- 2/08/2009 22:42:24
-- Acct fact summary
UPDATE AD_PrintFormatItem SET PrintName='User Element 1 Dimension', Name='User Element 1 Dimension' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53897)
;

-- 2/08/2009 22:43:54
-- Acct fact summary
UPDATE AD_Element SET ColumnName='IsUserElement2Dim', Name='User Element 2 Dimension', PrintName='User Element 2 Dimension',Updated=TO_TIMESTAMP('2009-08-02 22:43:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53897
;

-- 2/08/2009 22:43:54
-- Acct fact summary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53897
;

-- 2/08/2009 22:43:54
-- Acct fact summary
UPDATE AD_Column SET ColumnName='IsUserElement2Dim', Name='User Element 2 Dimension', Description='Include User Element 1 as a cube dimension', Help=NULL WHERE AD_Element_ID=53897
;

-- 2/08/2009 22:43:54
-- Acct fact summary
UPDATE AD_Process_Para SET ColumnName='IsUserElement2Dim', Name='User Element 2 Dimension', Description='Include User Element 1 as a cube dimension', Help=NULL, AD_Element_ID=53897 WHERE UPPER(ColumnName)='ISUSERELEMENT2DIM' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- 2/08/2009 22:43:54
-- Acct fact summary
UPDATE AD_Process_Para SET ColumnName='IsUserElement2Dim', Name='User Element 2 Dimension', Description='Include User Element 1 as a cube dimension', Help=NULL WHERE AD_Element_ID=53897 AND IsCentrallyMaintained='Y'
;

-- 2/08/2009 22:43:54
-- Acct fact summary
UPDATE AD_Field SET Name='User Element 2 Dimension', Description='Include User Element 1 as a cube dimension', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53897) AND IsCentrallyMaintained='Y'
;

-- 2/08/2009 22:43:54
-- Acct fact summary
UPDATE AD_PrintFormatItem SET PrintName='User Element 2 Dimension', Name='User Element 2 Dimension' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53897)
;

-- 2/08/2009 22:44:09
-- Acct fact summary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53898,0,'IsUserElement1Dim',TO_TIMESTAMP('2009-08-02 22:44:08','YYYY-MM-DD HH24:MI:SS'),100,'Include User Element 1 as a cube dimension','D','Y','User Element 1 Dimension','User Element 1 Dimension',TO_TIMESTAMP('2009-08-02 22:44:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/08/2009 22:44:09
-- Acct fact summary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53898 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 2/08/2009 22:44:15
-- Acct fact summary
UPDATE AD_Element SET Description='Include User Element 2 as a cube dimension',Updated=TO_TIMESTAMP('2009-08-02 22:44:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53897
;

-- 2/08/2009 22:44:15
-- Acct fact summary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53897
;

-- 2/08/2009 22:44:15
-- Acct fact summary
UPDATE AD_Column SET ColumnName='IsUserElement2Dim', Name='User Element 2 Dimension', Description='Include User Element 2 as a cube dimension', Help=NULL WHERE AD_Element_ID=53897
;

-- 2/08/2009 22:44:15
-- Acct fact summary
UPDATE AD_Process_Para SET ColumnName='IsUserElement2Dim', Name='User Element 2 Dimension', Description='Include User Element 2 as a cube dimension', Help=NULL, AD_Element_ID=53897 WHERE UPPER(ColumnName)='ISUSERELEMENT2DIM' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- 2/08/2009 22:44:15
-- Acct fact summary
UPDATE AD_Process_Para SET ColumnName='IsUserElement2Dim', Name='User Element 2 Dimension', Description='Include User Element 2 as a cube dimension', Help=NULL WHERE AD_Element_ID=53897 AND IsCentrallyMaintained='Y'
;

-- 2/08/2009 22:44:15
-- Acct fact summary
UPDATE AD_Field SET Name='User Element 2 Dimension', Description='Include User Element 2 as a cube dimension', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53897) AND IsCentrallyMaintained='Y'
;

-- 2/08/2009 22:44:29
-- Acct fact summary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57954,53898,0,20,53202,'IsUserElement1Dim',TO_TIMESTAMP('2009-08-02 22:44:28','YYYY-MM-DD HH24:MI:SS'),100,'Include User Element 1 as a cube dimension','D',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','User Element 1 Dimension',0,TO_TIMESTAMP('2009-08-02 22:44:28','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2/08/2009 22:44:29
-- Acct fact summary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57954 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 2/08/2009 22:45:04
-- Acct fact summary
ALTER TABLE PA_ReportCube ADD COLUMN IsUserElement1Dim CHAR(1) DEFAULT NULL CHECK (IsUserElement1Dim IN ('Y','N'))
;

-- 2/08/2009 22:46:22
-- Acct fact summary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57647,57384,0,53219,TO_TIMESTAMP('2009-08-02 22:46:21','YYYY-MM-DD HH24:MI:SS'),100,'The time last recalculated.',7,'D','Y','Y','Y','N','N','N','N','N','Last Recalculated',TO_TIMESTAMP('2009-08-02 22:46:21','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/08/2009 22:46:22
-- Acct fact summary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57384 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 2/08/2009 22:46:23
-- Acct fact summary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57646,57385,0,53219,TO_TIMESTAMP('2009-08-02 22:46:22','YYYY-MM-DD HH24:MI:SS'),100,1,'D','Y','Y','Y','N','N','N','N','N','Process Now',TO_TIMESTAMP('2009-08-02 22:46:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/08/2009 22:46:23
-- Acct fact summary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57385 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 2/08/2009 22:46:24
-- Acct fact summary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57954,57386,0,53219,TO_TIMESTAMP('2009-08-02 22:46:23','YYYY-MM-DD HH24:MI:SS'),100,'Include User Element 1 as a cube dimension',1,'D','Y','Y','Y','N','N','N','N','N','User Element 1 Dimension',TO_TIMESTAMP('2009-08-02 22:46:23','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/08/2009 22:46:24
-- Acct fact summary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57386 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 2/08/2009 22:46:25
-- Acct fact summary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,57953,57387,0,53219,TO_TIMESTAMP('2009-08-02 22:46:24','YYYY-MM-DD HH24:MI:SS'),100,'Include User Element 2 as a cube dimension',1,'D','Y','Y','Y','N','N','N','N','N','User Element 2 Dimension',TO_TIMESTAMP('2009-08-02 22:46:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2/08/2009 22:46:25
-- Acct fact summary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57387 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 2/08/2009 22:46:48
-- Acct fact summary
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=57386
;

-- 2/08/2009 22:46:48
-- Acct fact summary
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=57387
;

-- 2/08/2009 22:46:48
-- Acct fact summary
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=57384
;

-- 2/08/2009 22:46:48
-- Acct fact summary
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=57385
;

-- 2/08/2009 22:47:05
-- Acct fact summary
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_TIMESTAMP('2009-08-02 22:47:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57384
;

-- 2/08/2009 22:47:28
-- Acct fact summary
UPDATE AD_Field SET IsDisplayed='N',Updated=TO_TIMESTAMP('2009-08-02 22:47:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57385
;

-- 2/08/2009 22:50:37
-- Acct fact summary
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2009-08-02 22:50:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57387
;

CREATE INDEX FAS_ACCOUNT ON FACT_ACCT_SUMMARY (AD_CLIENT_ID, AD_ORG_ID, C_ACCTSCHEMA_ID, ACCOUNT_ID);

CREATE INDEX FAS_DATEACCT ON FACT_ACCT_SUMMARY (DATEACCT);
 
CREATE INDEX FAS_PERIOD ON FACT_ACCT_SUMMARY (C_PERIOD_ID);
 
CREATE INDEX FAS_REPORTCUBE ON FACT_ACCT_SUMMARY (PA_REPORTCUBE_ID);

-- CREATE INDEX FACT_ACCT_UPDATED ON FACT_ACCT (UPDATED);

