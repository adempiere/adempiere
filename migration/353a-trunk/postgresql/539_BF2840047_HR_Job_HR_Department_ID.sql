-- Aug 24, 2009 3:44:24 PM COT
-- [ adempiere-Libero-2840047 ] Design issue: HR_Job.HR_Department_ID not normalized
UPDATE AD_Column SET IsMandatory='N',Updated=TO_TIMESTAMP('2009-08-24 15:44:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54803
;

-- Aug 24, 2009 3:44:26 PM COT
-- [ adempiere-Libero-2840047 ] Design issue: HR_Job.HR_Department_ID not normalized
insert into t_alter_column values('hr_job','HR_Department_ID','NUMERIC(10)',null,'NULL')
;

-- Aug 24, 2009 3:44:26 PM COT
-- [ adempiere-Libero-2840047 ] Design issue: HR_Job.HR_Department_ID not normalized
insert into t_alter_column values('hr_job','HR_Department_ID',null,'NULL',null)
;

