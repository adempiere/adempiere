-- 23.06.2008 10:33:45 EEST
-- 
UPDATE AD_Column SET DefaultValue='N', IsMandatory='Y',Updated=TO_DATE('2008-06-23 10:33:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54951
;

-- 23.06.2008 10:33:47 EEST
-- 
ALTER TABLE HR_PayrollConcept MODIFY IsPrinted CHAR(1) DEFAULT 'N'
;

-- 23.06.2008 10:33:47 EEST
-- 
UPDATE HR_PayrollConcept SET IsPrinted='N' WHERE IsPrinted IS NULL
;

-- 23.06.2008 10:33:47 EEST
-- 
ALTER TABLE HR_PayrollConcept MODIFY IsPrinted NOT NULL
;

