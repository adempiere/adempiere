--DROP VIEW RV_HR_EmployeeDependent
CREATE OR REPLACE VIEW RV_HR_EmployeeDependent AS
SELECT e.AD_Client_ID, e.AD_Org_ID, e.IsActive, e.Created, e.CreatedBy, e.Updated, e.UpdatedBy, e.Value, e.TaxID, 
e.Name2, e.BPName, e.C_BPartner_ID, 
e.C_BP_Group_ID, e.EmployeeStatus, e.Gender, e.Birthday, e.MaritalStatus, e.BloodGroup, e.PlaceOfBirth_ID, e.FathersName, 
e.MarriageAnniversaryDate, e.PaymentRule,
e.HR_Job_ID, e.NationalCode, e.PartnersName, e.IdentificationMark, e.IsManager, e.HR_EmployeeType_ID, 
e.EndDate, e.HR_Department_ID, e.SSCode, e.HR_Payroll_ID, e.Code, e.C_Activity_ID, e.StartDate, 
e.HR_JobType_ID, e.HR_JobOpening_ID, e.HR_JobEducation_ID, e.HR_CareerLevel_ID, e.HR_SalaryRange_ID, e.HR_SalaryStructure_ID, 
e.HR_Designation_ID, e.HR_Grade_ID, e.C_Project_ID, e.AD_OrgTrx_ID, e.C_SalesRegion_ID, e.HR_ShiftGroup_ID, 
e.HR_Degree_ID, e.C_Campaign_ID, e.HR_WorkGroup_ID, e.MonthlySalary, e.DailySalary,
-- Dependents
ed.HR_EmployeeDependent_ID, ed.Birthday AS DependentBirthday, ed.HR_Employee_ID, ed.HR_Relationship_ID, ed.IsCollectCash
FROM RV_HR_Employee e
INNER JOIN HR_EmployeeDependent ed ON(ed.C_BPartner_ID = e.C_BPartner_ID AND ed.IsActive = 'Y')