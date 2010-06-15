-- Nov 26, 2009 11:18:36 AM MYT
-- [ 2074600 ] Housekeeping - Process to delete historic information
UPDATE AD_Val_Rule SET Code='M_InOutLine.M_InOut_ID=@InOut_ID@ AND NOT EXISTS (SELECT * FROM M_RMALine rl WHERE rl.M_InOutLine_ID=M_InOutLine.M_InOutLine_ID AND rl.M_RMA_ID=@M_RMA_ID@ AND rl.M_RMALine_ID != @M_RMALine_ID@)',Updated=TO_TIMESTAMP('2009-11-26 11:18:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=52001
;

COMMIT;
