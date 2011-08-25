-- 11-oct-2010 14:42:01 VET
-- Make column payroll concept category updatable
UPDATE AD_Column SET IsUpdateable='Y',Updated=TO_DATE('2010-10-11 14:42:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54820
;

-- 11-oct-2010 14:51:57 VET
-- Make column HR_Year.StartDate type Date instead of DateTime
UPDATE AD_Column SET AD_Reference_ID=15,Updated=TO_DATE('2010-10-11 14:51:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54937
;

-- 13-oct-2010 15:21:20 VET
-- All periods of a year shown
UPDATE AD_Tab SET AD_Column_ID=54914,Updated=TO_DATE('2010-10-13 15:21:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=53116
;

-- 13-oct-2010 15:28:02 VET
UPDATE AD_Field SET DefaultValue='@C_Year_ID@', IsReadOnly='Y',Updated=TO_DATE('2010-10-13 15:28:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=55077
;

-- 13-oct-2010 15:44:17 VET
-- Add security validation to HR_Process.AD_Org_ID
UPDATE AD_Column SET AD_Val_Rule_ID=130, DefaultValue='@#AD_Org_ID@',Updated=TO_DATE('2010-10-13 15:44:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54881
;

-- 13-oct-2010 15:45:37 VET
-- Change HR_Movement date columns from datetime to just date
UPDATE AD_Column SET AD_Reference_ID=15,Updated=TO_DATE('2010-10-13 15:45:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=55048
;

-- 13-oct-2010 15:45:43 VET
UPDATE AD_Column SET AD_Reference_ID=15,Updated=TO_DATE('2010-10-13 15:45:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=55052
;

-- 13-oct-2010 15:45:46 VET
UPDATE AD_Column SET AD_Reference_ID=15,Updated=TO_DATE('2010-10-13 15:45:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=55054
;

-- 15-oct-2010 15:05:04 VET
-- Payroll Concept Sign cannot be Natural
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52096,'AD_Ref_List.Value IN (''C'',''D'')',TO_DATE('2010-10-15 15:04:58','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','C_ElementValue Account Sign - No Natural','S',TO_DATE('2010-10-15 15:04:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 15-oct-2010 15:05:25 VET
UPDATE AD_Column SET AD_Val_Rule_ID=52096,Updated=TO_DATE('2010-10-15 15:05:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54814
;

-- 15-oct-2010 15:12:23 VET
-- Filter payroll period by payroll in payroll process
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52097,'HR_Period.HR_Payroll_ID=@HR_Payroll_ID@',TO_DATE('2010-10-15 15:12:18','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','HR_Period of Payroll Process','S',TO_DATE('2010-10-15 15:12:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 15-oct-2010 15:12:35 VET
UPDATE AD_Column SET AD_Val_Rule_ID=52097,Updated=TO_DATE('2010-10-15 15:12:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54873
;

