-- Feb 11, 2010 3:41:40 PM EST
-- Reset Allocation
INSERT INTO AD_Process (AccessLevel,AD_Client_ID,AD_Org_ID,AD_Process_ID,Classname,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,IsServerProcess,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value) VALUES ('2',0,0,53199,'org.compiere.process.AllocationReset',TO_DATE('2010-02-11 15:41:38','YYYY-MM-DD HH24:MI:SS'),100,'Reset (delete) allocation of invoices to payments','D','Delete individual allocation. In contrast to "Reverse", the allocation is deleted (no trace), if the period is open.','Y','N','N','N','N','Reset Allocation Direct','Y',0,0,TO_DATE('2010-02-11 15:41:38','YYYY-MM-DD HH24:MI:SS'),100,'C_Allocation_Reset_Direct')
;

-- Feb 11, 2010 3:41:40 PM EST
-- Reset Allocation
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53199 AND NOT EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_ID=t.AD_Process_ID)
;

-- Feb 11, 2010 3:43:37 PM EST
-- Reset Allocation
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=10431
;

-- Feb 11, 2010 3:44:45 PM EST
-- Reset Allocation
UPDATE AD_Column SET AD_Process_ID=53199, AD_Reference_ID=28,Updated=TO_DATE('2010-02-11 15:44:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=12314
;

-- Feb 11, 2010 3:44:49 PM EST
-- Reset Allocation
UPDATE AD_Field SET Description='Reset (delete) allocation of invoices to payments', Name='Reset Allocation',Updated=TO_DATE('2010-02-11 15:44:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=10431
;

-- Feb 11, 2010 3:44:49 PM EST
-- Reset Allocation
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=10431
;

-- Feb 11, 2010 3:50:09 PM EST
-- Reset Allocation
UPDATE AD_Column SET IsAlwaysUpdateable='Y',Updated=TO_DATE('2010-02-11 15:50:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=12314
;

