-- Nov 1, 2013 2:33:11 PM IST
-- Improvement in Dashboard
UPDATE AD_Field SET Description='Zoom Field has to be the primary key of the Zoom Tab selected. For example, for Zoom Tab Business Partner in Business Partner window, Zoom Field must be C_BPartner_ID.',Updated=TO_DATE('2013-11-01 14:33:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70190
;

-- Nov 1, 2013 2:33:11 PM IST
-- Improvement in Dashboard
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=70190
;

-- Nov 1, 2013 6:06:12 PM IST
-- Improvement in Dashboard
UPDATE AD_Field SET DisplayLogic='@IsEventRequired@=''Y''',Updated=TO_DATE('2013-11-01 18:06:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70003
;

-- Nov 1, 2013 6:06:17 PM IST
-- Improvement in Dashboard
UPDATE AD_Field SET DisplayLogic='@IsEventRequired@=''Y''',Updated=TO_DATE('2013-11-01 18:06:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=70190
;