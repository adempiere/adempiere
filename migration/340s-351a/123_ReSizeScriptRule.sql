-- 07-mar-2008 20:37:34 ECT
-- New Event Model Validator
UPDATE AD_Column SET FieldLength=2000,Updated=TO_DATE('2008-03-07 20:37:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54257
;

-- 07-mar-2008 20:37:34 ECT
-- New Event Model Validator
UPDATE AD_Field SET Name='Script', Description='Dynamic Java Language Script to calculate result', Help='Use Java language constructs to define the result of the calculation' WHERE AD_Column_ID=54257 AND IsCentrallyMaintained='Y'
;

-- 07-mar-2008 20:37:38 ECT
-- New Event Model Validator
ALTER TABLE AD_Rule MODIFY Script NVARCHAR2(2000) DEFAULT  NULL 
;

