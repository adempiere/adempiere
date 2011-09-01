-- Jun 14, 2010 2:48:57 PM CEST
-- FR 3015882 - Copy process for product
INSERT INTO AD_Process (AccessLevel,AD_Client_ID,AD_Org_ID,AD_Process_ID,Classname,CopyFromProcess,Created,CreatedBy,Description,EntityType,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,IsServerProcess,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value) VALUES ('1',0,0,53206,'org.compiere.process.CopyProduct','N',TO_TIMESTAMP('2010-06-14 14:48:55','YYYY-MM-DD HH24:MI:SS'),100,'Copy prices etc from other product','D','Y','N','N','N','N','Copy from product','Y',0,0,TO_TIMESTAMP('2010-06-14 14:48:55','YYYY-MM-DD HH24:MI:SS'),100,'M_Product CopyFrom')
;

-- Jun 14, 2010 2:48:57 PM CEST
-- FR 3015882 - Copy process for product
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53206 AND NOT EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_ID=t.AD_Process_ID)
;

-- Jun 14, 2010 2:49:42 PM CEST
-- FR 3015882 - Copy process for product
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,454,0,53206,53410,30,'M_Product_ID',TO_TIMESTAMP('2010-06-14 14:49:41','YYYY-MM-DD HH24:MI:SS'),100,'Product identifier','U',0,'The product to copy from','Y','Y','Y','N','Product',10,TO_TIMESTAMP('2010-06-14 14:49:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 14, 2010 2:49:42 PM CEST
-- FR 3015882 - Copy process for product
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53410 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

-- Jun 14, 2010 2:53:15 PM CEST
-- FR 3015882 - Copy process for product
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59231,2037,0,53206,28,208,'CopyFrom',TO_TIMESTAMP('2010-06-14 14:53:14','YYYY-MM-DD HH24:MI:SS'),100,'Copy From Record','D',1,'Copy From Record','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Copy From',0,TO_TIMESTAMP('2010-06-14 14:53:14','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Jun 14, 2010 2:53:15 PM CEST
-- FR 3015882 - Copy process for product
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59231 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 14, 2010 2:53:29 PM CEST
-- FR 3015882 - Copy process for product
ALTER TABLE M_Product ADD COLUMN CopyFrom CHAR(1) DEFAULT NULL 
;

-- Jun 14, 2010 2:58:52 PM CEST
-- FR 3015882 - Copy process for product
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59231,58973,0,180,TO_TIMESTAMP('2010-06-14 14:58:51','YYYY-MM-DD HH24:MI:SS'),100,'Copy From Record',23,'D','Copy From Record','Y','Y','Y','N','N','N','N','N','Copy From',540,0,TO_TIMESTAMP('2010-06-14 14:58:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jun 14, 2010 2:58:52 PM CEST
-- FR 3015882 - Copy process for product
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58973 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

