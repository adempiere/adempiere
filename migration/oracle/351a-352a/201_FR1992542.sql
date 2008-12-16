-- Jun 12, 2008 7:57:23 PM COT
-- FR 1992542 - Import Payment doesn't have DocAction parameter
UPDATE AD_Process_Para SET SeqNo=40,Updated=TO_DATE('2008-06-12 19:57:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=712
;

-- Jun 12, 2008 7:58:40 PM COT
-- FR 1992542 - Import Payment doesn't have DocAction parameter
INSERT INTO AD_Process_Para (FieldLength,UpdatedBy,ColumnName,AD_Org_ID,AD_Process_Para_ID,SeqNo,Updated,Help,DefaultValue,Created,IsCentrallyMaintained,AD_Process_ID,AD_Client_ID,IsRange,AD_Reference_ID,EntityType,AD_Val_Rule_ID,Name,CreatedBy,IsMandatory,AD_Reference_Value_ID,IsActive,AD_Element_ID,Description) VALUES (1,100,'DocAction',0,53177,30,TO_DATE('2008-06-12 19:58:39','YYYY-MM-DD HH24:MI:SS'),'You find the current status in the Document Status field. The options are listed in a popup','PR',TO_DATE('2008-06-12 19:58:39','YYYY-MM-DD HH24:MI:SS'),'Y',343,0,'N',17,'D',219,'Document Action',100,'N',135,'Y',287,'The targeted status of the document')
;

-- Jun 12, 2008 7:58:40 PM COT
-- FR 1992542 - Import Payment doesn't have DocAction parameter
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53177 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jun 12, 2008 8:32:52 PM COT
-- FR 1992542 - Import Payment doesn't have DocAction parameter
INSERT INTO AD_Val_Rule (UpdatedBy,Code,AD_Org_ID,Updated,Type,Created,AD_Client_ID,EntityType,AD_Val_Rule_ID,Name,CreatedBy,IsActive) VALUES (100,'AD_Ref_List.Value IN (''CO'')',0,TO_DATE('2008-06-12 20:32:50','YYYY-MM-DD HH24:MI:SS'),'S',TO_DATE('2008-06-12 20:32:50','YYYY-MM-DD HH24:MI:SS'),0,'D',52027,'List - DocAction - Complete',100,'Y')
;

-- Jun 12, 2008 8:33:09 PM COT
-- FR 1992542 - Import Payment doesn't have DocAction parameter
UPDATE AD_Process_Para SET DefaultValue=NULL, AD_Val_Rule_ID=52027,Updated=TO_DATE('2008-06-12 20:33:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53177
;
