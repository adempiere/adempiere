-- Jun 11, 2013 7:14:21 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Process (AccessLevel,AD_Client_ID,AD_Org_ID,AD_Process_ID,Classname,CopyFromProcess,Created,CreatedBy,EntityType
	,IsActive,IsBetaFunctionality,IsDirectPrint
	-- ,IsOneInstanceOnly
	,IsReport,IsServerProcess
	-- ,LockWaitTimeout
	,Name,ShowHelp,Statistic_Count,Statistic_Seconds
	-- ,Type
	,Updated,UpdatedBy,Value)
VALUES ('4',0,0,53388,'org.adempiere.ad.migration.process.AD_Migration_CreateFromEntityType','N',TO_TIMESTAMP('2013-06-11 19:14:21','YYYY-MM-DD HH24:MI:SS'),100,'D'
	,'Y','N','N'
	-- ,'N'
	,'N','N'
	-- ,0
	,'Create migration steps from entity type','Y',0,0
	-- ,'Java'
	,TO_TIMESTAMP('2013-06-11 19:14:21','YYYY-MM-DD HH24:MI:SS'),100,'AD_Migration_CreateFromEntityType')
;

-- Jun 11, 2013 7:14:21 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53388 AND NOT EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_ID=t.AD_Process_ID)
;

-- Jun 11, 2013 7:16:04 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,Description,EntityType
	,FieldLength
	,Help
	,IsActive,IsCentrallyMaintained
	-- ,IsEncrypted
	,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy)
VALUES (0,1682,0,53388,53909,18,389,'EntityType',TO_TIMESTAMP('2013-06-11 19:16:03','YYYY-MM-DD HH24:MI:SS'),100,'Dictionary Entity Type; Determines ownership and synchronization','U'
	,255
	,'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!'
	,'Y','Y'
	-- ,'N'
	,'Y','N','Entitäts-Art',10,TO_TIMESTAMP('2013-06-11 19:16:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 11, 2013 7:16:04 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53909 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Jun 11, 2013 7:16:22 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
--INSERT INTO AD_Table_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,Updated,UpdatedBy) VALUES (0,0,53388,53217,TO_TIMESTAMP('2013-06-11 19:16:22','YYYY-MM-DD HH24:MI:SS'),100,'D','Y',TO_TIMESTAMP('2013-06-11 19:16:22','YYYY-MM-DD HH24:MI:SS'),100)
--;

