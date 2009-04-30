-- Apr 30, 2009 10:13:34 AM EEST
-- 
UPDATE AD_Column SET IsMandatory='N',Updated=TO_DATE('2009-04-30 10:13:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=3537
;

-- Apr 30, 2009 10:13:48 AM EEST
-- 
ALTER TABLE M_InOutLine MODIFY M_Locator_ID NUMBER(10) DEFAULT NULL 
;

-- Apr 30, 2009 10:13:48 AM EEST
-- 
ALTER TABLE M_InOutLine MODIFY M_Locator_ID NULL
;

-- Apr 30, 2009 10:45:23 AM EEST
-- 
UPDATE AD_Field SET IsMandatory='Y',Updated=TO_DATE('2009-04-30 10:45:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=3514
;

-- Apr 30, 2009 10:46:16 AM EEST
-- 
UPDATE AD_Field SET IsMandatory='Y',Updated=TO_DATE('2009-04-30 10:46:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=2717
;

