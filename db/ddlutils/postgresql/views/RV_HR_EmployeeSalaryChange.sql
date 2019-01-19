--DROP VIEW RV_HR_EmployeeSalaryChange
CREATE OR REPLACE VIEW RV_HR_EmployeeSalaryChange AS
SELECT e.AD_Client_ID, e.AD_Org_ID, e.IsActive, e.Created, e.CreatedBy, e.Updated, e.UpdatedBy, e.Value, e.TaxID, 
e.Name2, e.BPName, e.C_BPartner_ID, 
e.C_BP_Group_ID, e.EmployeeStatus, e.Gender, e.Birthday, e.MaritalStatus, e.BloodGroup, e.PlaceOfBirth_ID, e.FathersName, 
e.MarriageAnniversaryDate, e.PaymentRule,
e.HR_Job_ID, e.NationalCode, e.PartnersName, e.IdentificationMark, e.IsManager, e.HR_EmployeeType_ID, 
e.EndDate, e.HR_Department_ID, e.SSCode, e.HR_Payroll_ID, e.Code, e.C_Activity_ID, e.StartDate, 
e.HR_JobType_ID, e.HR_JobOpening_ID, e.HR_JobEducation_ID, e.HR_CareerLevel_ID, e.HR_SalaryRange_ID, e.HR_SalaryStructure_ID, 
e.HR_Designation_ID, e.HR_Grade_ID, e.C_Project_ID, e.AD_OrgTrx_ID, e.C_SalesRegion_ID, e.HR_ShiftGroup_ID, 
e.HR_Degree_ID, e.C_Campaign_ID, e.HR_WorkGroup_ID, 
COALESCE(ds.ValidFrom, ms.ValidFrom) AS ValidFrom, ms.Amount AS MonthlySalary, ds.Amount AS DailySalary, 
COALESCE(ds.Description, ms.Description) AS Description
FROM RV_HR_Employee e
LEFT JOIN (SELECT a.C_BPartner_ID, a.ValidFrom, 
		(CASE WHEN c.ColumnType = 'A' THEN a.Amount ELSE a.Qty END) AS Amount, a.Description
		FROM HR_Attribute a
		INNER JOIN HR_Concept c ON(c.HR_Concept_ID = a.HR_Concept_ID)
		WHERE c.ColumnType IN('A', 'Q')
		AND EXISTS(SELECT 1 FROM RV_HR_Employee r
				INNER JOIN HR_Contract c ON(c.HR_Contract_ID = r.HR_Contract_ID)
				WHERE c.DailySalary_ID = a.HR_Concept_ID)) ds ON(ds.C_BPartner_ID = e.C_BPartner_ID)
LEFT JOIN (SELECT a.C_BPartner_ID, a.ValidFrom, 
		(CASE WHEN c.ColumnType = 'A' THEN a.Amount ELSE a.Qty END) AS Amount, a.Description
		FROM HR_Attribute a
		INNER JOIN HR_Concept c ON(c.HR_Concept_ID = a.HR_Concept_ID)
		WHERE c.ColumnType IN('A', 'Q')
		AND EXISTS(SELECT 1 FROM RV_HR_Employee r
				INNER JOIN HR_Contract c ON(c.HR_Contract_ID = r.HR_Contract_ID)
				WHERE c.MonthlySalary_ID = a.HR_Concept_ID)) ms ON(ms.C_BPartner_ID = e.C_BPartner_ID)