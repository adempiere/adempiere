-- Jan 7, 2008 9:37:36 PM COT
-- 1866483 - Jasper on Financial Reports

-- Is convenient to allow executing the configured financial report with adempiere reporter, and jasper
-- Exporting to CSV have different results from Jasper than from Adempiere reporter

UPDATE AD_FIELD SET DisplayLogic=NULL,Updated=TO_TIMESTAMP('2008-02-27 12:57:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=4737
;

UPDATE AD_FIELD SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=54235
;

UPDATE AD_FIELD SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=6265
;

