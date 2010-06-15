-- Dec 14, 2009 5:36:58 PM COT
-- FR2873784_bad defaults
UPDATE AD_Process_Para SET DefaultValue='0.01',Updated=TO_DATE('2009-12-14 17:36:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=219
;

-- Dec 14, 2009 5:39:26 PM COT
UPDATE AD_Process_Para SET DefaultValue='-99999', DefaultValue2='99999',Updated=TO_DATE('2009-12-14 17:39:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=173
;

-- Dec 14, 2009 5:43:07 PM COT
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54099,0,'AllAllocations',TO_DATE('2009-12-14 17:43:06','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','All Allocations','All Allocations',TO_DATE('2009-12-14 17:43:06','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 14, 2009 5:43:07 PM COT
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54099 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Dec 14, 2009 5:44:52 PM COT
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,DisplayLogic,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,54099,0,303,53382,20,'AllAllocations',TO_DATE('2009-12-14 17:44:52','YYYY-MM-DD HH24:MI:SS'),100,'N',NULL,'D',1,'Y','Y','Y','N','All Allocations',50,TO_DATE('2009-12-14 17:44:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 14, 2009 5:44:52 PM COT
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53382 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

