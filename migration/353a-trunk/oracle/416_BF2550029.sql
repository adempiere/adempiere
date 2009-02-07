-- Feb 6, 2009 6:56:15 PM COT
-- [2550029] R_Request.SalesRep_ID must be mandatory on UI, not in DB
UPDATE AD_Column SET IsMandatory='N',Updated=TO_DATE('2009-02-06 18:56:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=5432
;

UPDATE AD_Field SET IsMandatory='Y',Updated=TO_DATE('2009-02-06 18:56:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=4284
;

UPDATE AD_Field SET IsMandatory='Y',Updated=TO_DATE('2009-02-06 18:56:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=5169
;

