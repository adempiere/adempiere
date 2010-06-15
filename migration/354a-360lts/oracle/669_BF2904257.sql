-- 01-mar-2010 12:10:16 COT
-- Bug_2904257_wrong validation sql for M_InOutShipment/Receipt (RMA)
UPDATE AD_Val_Rule SET Code='M_InOutLine.M_InOut_ID=@InOut_ID@',Updated=TO_DATE('2010-03-01 12:10:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=52001
;

