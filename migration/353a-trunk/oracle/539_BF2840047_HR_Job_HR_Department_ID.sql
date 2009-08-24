-- Aug 24, 2009 3:44:24 PM COT
-- [ adempiere-Libero-2840047 ] Design issue: HR_Job.HR_Department_ID not normalized
UPDATE AD_Column SET IsMandatory='N',Updated=TO_DATE('2009-08-24 15:44:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54803
;

-- Aug 24, 2009 3:44:26 PM COT
-- [ adempiere-Libero-2840047 ] Design issue: HR_Job.HR_Department_ID not normalized
ALTER TABLE HR_Job MODIFY HR_Department_ID NUMBER(10) DEFAULT NULL 
;

-- Aug 24, 2009 3:44:26 PM COT
-- [ adempiere-Libero-2840047 ] Design issue: HR_Job.HR_Department_ID not normalized
ALTER TABLE HR_Job MODIFY HR_Department_ID NULL
;

