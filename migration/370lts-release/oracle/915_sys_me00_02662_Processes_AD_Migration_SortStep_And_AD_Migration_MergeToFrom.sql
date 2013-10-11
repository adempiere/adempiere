-- Jun 19, 2013 3:17:27 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Process (Name,Statistic_Seconds,Classname,AD_Process_ID,IsReport,AccessLevel,IsDirectPrint,Statistic_Count,Value,IsBetaFunctionality,ShowHelp,IsServerProcess,CopyFromProcess,EntityType,AD_Client_ID,Updated,Created,AD_Org_ID,CreatedBy,IsActive,UpdatedBy) VALUES ('Merge migration to/from',0,'org.adempiere.ad.migration.process.MigrationMergeToFrom',53389,'N','7','N',0,'AD_Migration_MergeToFrom','N','Y','N','N','org.adempiere.ad.migration',0,TO_DATE('2013-06-19 15:17:26','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2013-06-19 15:17:26','YYYY-MM-DD HH24:MI:SS'),0,100,'Y',100)
;

-- Jun 19, 2013 3:17:27 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Name,Description,Help, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Name,t.Description,t.Help, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53389 AND NOT EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_ID=t.AD_Process_ID)
;

-- Jun 19, 2013 3:18:11 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Process_Para (FieldLength,AD_Process_Para_ID,ColumnName,Help,AD_Process_ID,IsCentrallyMaintained,IsRange,Name,AD_Reference_ID,SeqNo,IsMandatory,AD_Element_ID,EntityType,AD_Client_ID,Created,AD_Org_ID,CreatedBy,IsActive,Description,Updated,UpdatedBy) VALUES (0,53911,'AD_Migration_ID','Migration change management.',53389,'Y','N','Migration',19,10,'Y',53877,'org.adempiere.ad.migration',0,TO_DATE('2013-06-19 15:18:11','YYYY-MM-DD HH24:MI:SS'),0,100,'Y','Migration change management.',TO_DATE('2013-06-19 15:18:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 19, 2013 3:18:11 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53911 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Jun 19, 2013 3:33:07 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Element (AD_Element_ID,Name,ColumnName,PrintName,AD_Reference_ID,FieldLength,EntityType,AD_Client_ID,Created,AD_Org_ID,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (55917,'Merge To','IsMergeTo','Merge To',20,1,'org.adempiere.ad.migration',0,TO_DATE('2013-06-19 15:33:06','YYYY-MM-DD HH24:MI:SS'),0,100,'Y',TO_DATE('2013-06-19 15:33:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 19, 2013 3:33:07 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Name,PrintName,Help,PO_PrintName,PO_Description,PO_Help,PO_Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Name,t.PrintName,t.Help,t.PO_PrintName,t.PO_Description,t.PO_Help,t.PO_Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=55917 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Jun 19, 2013 3:34:01 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Process_Para (FieldLength,AD_Process_Para_ID,ColumnName,AD_Process_ID,IsCentrallyMaintained,IsRange,Name,AD_Reference_ID,DefaultValue,SeqNo,IsMandatory,AD_Element_ID,EntityType,AD_Client_ID,Created,AD_Org_ID,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (1,53912,'IsMergeTo',53389,'Y','N','Merge To',20,'N',20,'Y',55917,'org.adempiere.ad.migration',0,TO_DATE('2013-06-19 15:34:01','YYYY-MM-DD HH24:MI:SS'),0,100,'Y',TO_DATE('2013-06-19 15:34:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 19, 2013 3:34:01 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53912 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Jun 19, 2013 3:39:22 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Process_Para (FieldLength,AD_Process_Para_ID,ColumnName,AD_Process_ID,IsCentrallyMaintained,IsRange,Name,AD_Reference_ID,DefaultValue,SeqNo,IsMandatory,AD_Element_ID,EntityType,AD_Client_ID,Created,AD_Org_ID,CreatedBy,IsActive,Description,Updated,UpdatedBy) VALUES (1,53913,'DeleteOld',53389,'N','N','Delete source migration',20,'Y',30,'Y',1669,'org.adempiere.ad.migration',0,TO_DATE('2013-06-19 15:39:22','YYYY-MM-DD HH24:MI:SS'),0,100,'Y',NULL,TO_DATE('2013-06-19 15:39:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 19, 2013 3:39:22 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53913 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Jun 19, 2013 3:47:54 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Process (Name,Statistic_Seconds,Classname,Help,AD_Process_ID,IsReport,AccessLevel,IsDirectPrint,Statistic_Count,Value,IsBetaFunctionality,ShowHelp,IsServerProcess,CopyFromProcess,EntityType,AD_Client_ID,Updated,Created,AD_Org_ID,CreatedBy,IsActive,UpdatedBy) VALUES ('Sort migration steps',0,'org.adempiere.ad.migration.process.MigrationSortSteps','By default, this process is sorting steps by Created',53390,'N','7','N',0,'AD_Migration_SortSteps','N','Y','N','N','org.adempiere.ad.migration',0,TO_DATE('2013-06-19 15:47:53','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2013-06-19 15:47:53','YYYY-MM-DD HH24:MI:SS'),0,100,'Y',100)
;

-- Jun 19, 2013 3:47:54 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Name,Description,Help, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Name,t.Description,t.Help, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53390 AND NOT EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_ID=t.AD_Process_ID)
;

