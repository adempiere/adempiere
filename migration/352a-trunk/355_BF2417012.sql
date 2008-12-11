-- 11.12.2008 15:19:17 EET
-- [ 2417012 ] Create PO from Requsition display all requisitions
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52037,'M_Requisition.DocStatus IN (''CO'')
AND EXISTS (SELECT 1 FROM M_RequisitionLine rl
        WHERE rl.M_Requisition_ID=M_Requisition.M_Requisition_ID
              AND rl.C_OrderLine_ID IS NULL)',TO_DATE('2008-12-11 15:19:15','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','M_Requistion PO Create Candidate','S',TO_DATE('2008-12-11 15:19:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 11.12.2008 15:19:17 EET
-- [ 2417012 ] Create PO from Requsition display all requisitions
INSERT INTO AD_Val_Rule_Trl (AD_Language,AD_Val_Rule_ID, Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Val_Rule_ID, t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Val_Rule t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Val_Rule_ID=52037 AND EXISTS (SELECT * FROM AD_Val_Rule_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Val_Rule_ID!=t.AD_Val_Rule_ID)
;

-- 11.12.2008 15:19:55 EET
-- [ 2417012 ] Create PO from Requsition display all requisitions
UPDATE AD_Process_Para SET AD_Val_Rule_ID=52037,Updated=TO_DATE('2008-12-11 15:19:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=697
;

