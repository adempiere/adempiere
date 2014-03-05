-- Oct 19, 2013 11:17:49 AM IST
-- Rectified the errors with Create Production Order Column and regenerted the migrations.
UPDATE AD_SysConfig SET Name='SMART_REPORT',Updated=TO_DATE('2013-10-19 11:17:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_SysConfig_ID=50074
;

ALTER TABLE T_Report MODIFY (AccountType NULl)
;