-- 11.06.2009 12:02:13 EEST
-- -
UPDATE AD_Process SET Name='Payroll Processing', Classname='org.eevolution.process.PayrollProcessing', Value='PayrollProcessing',Updated=TO_TIMESTAMP('2009-06-11 12:02:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_ID=53082
;

-- 11.06.2009 12:02:13 EEST
-- -
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=53082
;

-- 11.06.2009 12:02:15 EEST
-- -
UPDATE AD_Menu SET Name='Payroll Processing', IsActive='Y', Description='The Payroll Processing is used to processing a Payroll, you can calculate for a Employee or All Employees',Updated=TO_TIMESTAMP('2009-06-11 12:02:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=53116
;

-- 11.06.2009 12:02:15 EEST
-- -
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53116
;

-- 11.06.2009 12:02:20 EEST
-- -
UPDATE AD_Process SET Value='HR_PayrollProcessing',Updated=TO_TIMESTAMP('2009-06-11 12:02:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_ID=53082
;

