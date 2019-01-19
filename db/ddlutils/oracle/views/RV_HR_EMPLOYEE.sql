--DROP VIEW RV_HR_Employee
CREATE OR REPLACE VIEW RV_HR_Employee AS
SELECT bp.AD_Client_ID, bp.AD_Org_ID, bp.IsActive, bp.Created, bp.CreatedBy, bp.Updated, bp.UpdatedBy, bp.Value, bp.TaxID, 
COALESCE(e.Name, bp.Name) AS Name, COALESCE(e.Name2, bp.Name2) AS Name2, 
(COALESCE(e.Name, bp.Name) || COALESCE(' ' || COALESCE(e.Name2, bp.Name2), '')) BPName, bp.C_BPartner_ID, 
bp.C_BP_Group_ID, COALESCE(e.EmployeeStatus, e.EmployeeStatus) AS EmployeeStatus, 
bp.Gender, bp.Birthday, COALESCE(e.MaritalStatus, bp.MaritalStatus) AS MaritalStatus, bp.BloodGroup, bp.PlaceOfBirth_ID, bp.FathersName, 
e.MarriageAnniversaryDate, COALESCE(e.PaymentRule, bp.PaymentRule) AS PaymentRule,
e.HR_Job_ID, e.NationalCode, e.PartnersName, e.IdentificationMark, e.IsManager, e.HR_EmployeeType_ID, 
e.EndDate, e.HR_Department_ID, e.SSCode, e.HR_Payroll_ID, e.Code, e.C_Activity_ID, e.StartDate, 
e.HR_JobType_ID, e.HR_JobOpening_ID, e.HR_JobEducation_ID, e.HR_CareerLevel_ID, e.HR_SalaryRange_ID, e.HR_SalaryStructure_ID, 
e.HR_Designation_ID, e.HR_Grade_ID, e.C_Project_ID, e.AD_OrgTrx_ID, e.C_SalesRegion_ID, e.HR_ShiftGroup_ID, 
e.HR_Degree_ID, e.C_Campaign_ID, e.HR_WorkGroup_ID, monthlySalary(bp.C_BPartner_ID) AS MonthlySalary, dailySalary(bp.C_BPartner_ID) AS DailySalary, e.HR_Contract_ID
FROM C_BPartner bp
LEFT JOIN (SELECT e.MarriageAnniversaryDate, e.AD_User_ID, e.HR_Job_ID, e.NationalCode, e.PartnersName, 
		e.Nationality_ID, e.MaritalStatus, e.PartnersBirthDate, e.HR_Race_ID, e.HR_SkillType_ID, 
		e.IdentificationMark, e.MonthlySalary, e.DailySalary, e.EmployeeStatus, 
		e.PaymentRule, e.IsManager, e.HR_EmployeeType_ID, e.Name, e.EndDate, e.HR_Department_ID, 
		e.IsActive, e.SSCode, e.HR_Payroll_ID, e.Code, e.C_Activity_ID, e.C_BPartner_ID, e.AD_Org_ID, 
		e.StartDate, e.Updated, e.Name2, e.HR_JobType_ID, e.HR_JobOpening_ID, 
		e.HR_JobEducation_ID, e.HR_CareerLevel_ID, e.HR_SalaryRange_ID, e.HR_SalaryStructure_ID, 
		e.HR_Designation_ID, e.HR_Grade_ID, e.C_Project_ID, e.AD_OrgTrx_ID, 
		e.C_SalesRegion_ID, e.HR_ShiftGroup_ID, e.HR_Degree_ID, 
		e.C_Campaign_ID, e.HR_WorkGroup_ID, p.HR_Contract_ID
		FROM HR_Employee e
		LEFT JOIN HR_Payroll p ON(p.HR_Payroll_ID = e.HR_Payroll_ID)
		WHERE e.IsActive = 'Y'
        AND ROWNUM <= 1
        ORDER BY e.StartDate DESC) e ON(e.C_BPartner_ID = bp.C_BPartner_ID)
WHERE bp.IsEmployee = 'Y'