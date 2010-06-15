-- Nov 26, 2009 11:18:36 AM MYT
-- 2904257 - wrong dynamic validation sql for rma line
UPDATE AD_Val_Rule SET Code='M_InOutLine.M_InOut_ID=@InOut_ID@ AND NOT EXISTS (SELECT * FROM M_RMALine rl WHERE rl.M_InOutLine_ID=M_InOutLine.M_InOutLine_ID AND rl.M_RMA_ID=@M_RMA_ID@ AND rl.M_RMALine_ID != @1|M_RMALine_ID@)',Updated=TO_TIMESTAMP('2009-12-07 17:27:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=52001
;

COMMIT;
