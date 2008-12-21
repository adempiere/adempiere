-- 2/09/2008 14:38:46
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2008-09-02 14:38:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54945
;

-- 2/09/2008 14:38:49
insert into t_alter_column values('hr_payrollconcept','HR_Concept_ID','NUMERIC(10)',null,'NULL')
;

-- 2/09/2008 14:38:49
insert into t_alter_column values('hr_payrollconcept','HR_Concept_ID',null,'NOT NULL',null)
;

-- 2/09/2008 14:39:06
UPDATE AD_Column SET IsMandatory='Y', IsUpdateable='N',Updated=TO_TIMESTAMP('2008-09-02 14:39:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54947
;

-- 2/09/2008 14:39:08
insert into t_alter_column values('hr_payrollconcept','HR_Payroll_ID','NUMERIC(10)',null,'NULL')
;

-- 2/09/2008 14:39:08
insert into t_alter_column values('hr_payrollconcept','HR_Payroll_ID',null,'NOT NULL',null)
;

