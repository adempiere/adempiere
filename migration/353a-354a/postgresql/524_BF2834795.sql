-- 11/08/2009 12:43:24
-- Acct fact summary
UPDATE AD_Process SET Help='Copy role access records from one role to another.  The existing access records for the destination role will be deleted.', Name='Copy Role',Updated=TO_TIMESTAMP('2009-08-11 12:43:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_ID=50010
;

-- 11/08/2009 12:43:24
-- Acct fact summary
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=50010
;

-- 11/08/2009 12:43:24
-- Acct fact summary
UPDATE AD_Menu SET Description='Copy Role', IsActive='Y', Name='Copy Role',Updated=TO_TIMESTAMP('2009-08-11 12:43:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_ID=50007
;

-- 11/08/2009 12:43:24
-- Acct fact summary
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=50007
;

-- 11/08/2009 12:44:32
-- Acct fact summary
UPDATE AD_Process_Para SET IsCentrallyMaintained='N', Name='Role From',Updated=TO_TIMESTAMP('2009-08-11 12:44:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=50002
;

-- 11/08/2009 12:44:32
-- Acct fact summary
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=50002
;

-- 11/08/2009 12:44:46
-- Acct fact summary
UPDATE AD_Process_Para SET DefaultValue='-1', SeqNo = 15, IsCentrallyMaintained='N', Name='Role To',Updated=TO_TIMESTAMP('2009-08-11 12:44:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=50001
;

-- 11/08/2009 12:44:46
-- Acct fact summary
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=50001
;

-- 11/08/2009 12:44:49
-- Acct fact summary
UPDATE AD_Process_Para SET DefaultValue='-1',Updated=TO_TIMESTAMP('2009-08-11 12:44:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=50002
;

