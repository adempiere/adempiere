-- 06-jun-2009 17:52:14 COT
-- Error in M_InOut.C_Order_ID filter
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52055,'(IsSOTrx=''@IsSOTrx@'' AND DocStatus=''CO'')',TO_DATE('2009-06-06 17:52:10','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','C_Order in M_InOut (Complete and IsSOTrx)','S',TO_DATE('2009-06-06 17:52:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 06-jun-2009 17:52:29 COT
-- Error in M_InOut.C_Order_ID filter
UPDATE AD_Column SET AD_Val_Rule_ID=52055,Updated=TO_DATE('2009-06-06 17:52:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=3809
;

