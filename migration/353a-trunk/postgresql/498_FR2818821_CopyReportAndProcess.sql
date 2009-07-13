-- 9/07/2009 11:24:09
-- Copy report and process
INSERT INTO AD_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AccessLevel,Classname,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,IsServerProcess,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value) VALUES (0,0,53179,'4','org.compiere.process.CopyReportProcess',TO_TIMESTAMP('2009-07-09 11:24:07','YYYY-MM-DD HH24:MI:SS'),100,'Copy settings from one report and process to another.','D','Copy the settings from the selected report and process to the current one.  This overwrites existing settings and translations.','Y','N','N','N','N','Copy From Report and Process','Y',0,0,TO_TIMESTAMP('2009-07-09 11:24:07','YYYY-MM-DD HH24:MI:SS'),100,'CopyFromProcess')
;

-- 9/07/2009 11:24:09
-- Copy report and process
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53179 AND EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_ID!=t.AD_Process_ID)
;

-- 9/07/2009 11:25:08
-- Copy report and process
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,117,0,53179,53321,19,'AD_Process_ID',TO_TIMESTAMP('2009-07-09 11:25:04','YYYY-MM-DD HH24:MI:SS'),100,'-1','Report and Process to copy from','D',22,NULL,'Y','Y','Y','N','Copy from',10,TO_TIMESTAMP('2009-07-09 11:25:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 9/07/2009 11:25:08
-- Copy report and process
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53321 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- 9/07/2009 11:25:58
-- Copy report and process
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53893,0,'CopyFromProcess',TO_TIMESTAMP('2009-07-09 11:25:57','YYYY-MM-DD HH24:MI:SS'),100,'Copy settings from one report and process to another.','D','Copy the settings from the selected report and process to the current one.  This overwrites existing settings and translations.','Y','Copy From Report and Process','Copy From Report and Process',TO_TIMESTAMP('2009-07-09 11:25:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 9/07/2009 11:25:58
-- Copy report and process
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53893 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 9/07/2009 11:26:46
-- Copy report and process
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57920,53893,0,53179,28,284,'CopyFromProcess',TO_TIMESTAMP('2009-07-09 11:26:44','YYYY-MM-DD HH24:MI:SS'),100,'Copy settings from one report and process to another.','D',1,'Copy the settings from the selected report and process to the current one.  This overwrites existing settings and translations.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Copy From Report and Process',0,TO_TIMESTAMP('2009-07-09 11:26:44','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 9/07/2009 11:26:46
-- Copy report and process
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57920 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 9/07/2009 11:28:01
-- Copy report and process
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57920,57342,0,245,TO_TIMESTAMP('2009-07-09 11:28:00','YYYY-MM-DD HH24:MI:SS'),100,'Copy settings from one report and process to another.',22,'D','Copy the settings from the selected report and process to the current one.  This overwrites existing settings and translations.','Y','Y','Y','N','N','N','N','N','Copy From Report and Process',220,0,TO_TIMESTAMP('2009-07-09 11:28:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 9/07/2009 11:28:01
-- Copy report and process
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57342 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 9/07/2009 11:29:37
-- Copy report and process
ALTER TABLE AD_Process ADD COLUMN CopyFromProcess CHAR(1) DEFAULT NULL 
;

