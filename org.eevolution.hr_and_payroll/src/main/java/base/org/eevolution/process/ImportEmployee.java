/**
 * Copyright (C) 2003-2017, e-Evolution Consultants S.A. , http://www.e-evolution.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * Created by victor.perez@e-evolution.com , www.e-evolution.com
 */
package org.eevolution.process;

import org.compiere.model.MActivity;
import org.compiere.model.MBPartner;
import org.compiere.model.MCampaign;
import org.compiere.model.MImage;
import org.compiere.model.MOrg;
import org.compiere.model.MProject;
import org.compiere.model.MSalesRegion;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.eevolution.model.I_I_HR_Employee;
import org.eevolution.model.MHRCareerLevel;
import org.eevolution.model.MHRDegree;
import org.eevolution.model.MHRDepartment;
import org.eevolution.model.MHRDesignation;
import org.eevolution.model.MHREmployee;
import org.eevolution.model.MHREmployeeType;
import org.eevolution.model.MHRGrade;
import org.eevolution.model.MHRJob;
import org.eevolution.model.MHRJobEducation;
import org.eevolution.model.MHRJobType;
import org.eevolution.model.MHRPayroll;
import org.eevolution.model.MHRRace;
import org.eevolution.model.MHRSalaryRange;
import org.eevolution.model.MHRSalaryStructure;
import org.eevolution.model.MHRShiftGroup;
import org.eevolution.model.MHRSkillType;
import org.eevolution.model.MHRWorkGroup;
import org.eevolution.model.X_I_HR_Employee;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Import Employee
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/854">
 * 		@see FR [ 854 ] Add new columns for Concept Attribute</a>
 */
public class ImportEmployee extends ImportEmployeeAbstract {

    /**
     * preapare
     */
    protected void prepare() {
        super.prepare();
    }

    /**
     * Do it
     * @return
     * @throws Exception
     */
    protected String doIt() throws Exception {
        if (isDeleteoldimportedrecords())
            Arrays.stream(getImportEmployeeIds(true, true)).forEach(importEmployeeId -> {
                X_I_HR_Employee importEmployee = new X_I_HR_Employee(getCtx() , importEmployeeId , null);
                importEmployee.deleteEx(true);
            });

        AtomicInteger importedRecord = new AtomicInteger(0);
        AtomicInteger withErrors = new AtomicInteger(0);
        Arrays.stream(getImportEmployeeIds(false, false)).forEach(importEmployeeId -> {
            Trx.run(trxName -> {
                        X_I_HR_Employee importEmployee = new X_I_HR_Employee(getCtx() , importEmployeeId ,trxName);
                        importEmployee.setI_ErrorMsg(null);
                        fillIdValues(importEmployee, trxName);
                        if (importRecord(importEmployee, trxName))
                            importedRecord.updateAndGet(record -> record + 1);
                        else
                            withErrors.updateAndGet(error -> error + 1);
                    }
            );
        });

        return "@HR_Employee_ID@ @Import@ @Records@ " + importedRecord.get() + " @Errors@ " + withErrors.get();
    }

    /**
     * Fill values and validate employee data
     * This method create the dependences for Race , Department , Job , Job Type
     * Job Education , Career Level , Employee Type and Skill Type
     * @param importEmployee
     * @param trxName
     */
    private void fillIdValues(X_I_HR_Employee importEmployee, String trxName) {
        StringBuffer stringError = new StringBuffer("");
        Integer orgId = 0;
        if (importEmployee.getAD_Org_ID() > 0)
            orgId = importEmployee.getAD_Org_ID();
        if (orgId <= 0)
            orgId = getId(MOrg.Table_Name, MOrg.COLUMNNAME_Value + "=?", trxName, importEmployee.getOrgValue());
        if (orgId > 0)
            importEmployee.setAD_Org_ID(orgId);

        Integer partnerId = getId(MBPartner.Table_Name, MBPartner.COLUMNNAME_Value + "=?", trxName, importEmployee.getBPartnerValue());
        if (partnerId > 0)
            importEmployee.setC_BPartner_ID(partnerId);
        else if (isCreatedBusinessPartner()){
            if (getBusinessPartnerGroupId() > 0) {
                MBPartner partner = createPartnerFromEmployeeData(importEmployee);
                partnerId = partner.get_ID();
                importEmployee.setC_BPartner_ID(partnerId);
            }
            else
                stringError.append(" @C_BP_Group_ID@ @NotFound@");
        }

        //Set Race
        MHRRace race = null;
        if (importEmployee.getHR_Race_ID() > 0)
            race = MHRRace.getById(getCtx(), importEmployee.getHR_Race_ID());
        if (race != null && race.getHR_Race_ID() < 0 && importEmployee.getRaceValue() != null)
            race = MHRRace.getByValue(getCtx(), importEmployee.getRaceValue());
        if (race == null || race.getHR_Race_ID() < 0) {
            if (importEmployee.getRaceValue() != null && importEmployee.getRaceName() != null) {
                race = new MHRRace(getCtx(), importEmployee.getRaceValue(), importEmployee.getRaceName(), trxName);
                race.saveEx();
            }
        }
        if (race != null && race.getHR_Race_ID() > 0)
            importEmployee.setHR_Race_ID(race.getHR_Race_ID());

        //Set Organization Trx
        MOrg orgTrx = null;
        if (importEmployee.getAD_OrgTrx_ID() > 0)
            orgTrx = MOrg.get(getCtx(), importEmployee.getAD_OrgTrx_ID());
        if (orgTrx == null && importEmployee.getOrgTrxValue() != null) {
            int orgTrxId = getId(MOrg.Table_Name, MOrg.COLUMNNAME_Value + "=?", trxName, importEmployee.getOrgTrxValue());
            orgTrx = MOrg.get(getCtx(), orgTrxId);
        }
        if (orgTrx != null && orgTrx.getAD_Org_ID() > 0)
            importEmployee.setAD_OrgTrx_ID(orgTrx.getAD_Org_ID());

        //Set Project
        MProject project = null;
        if (importEmployee.getC_Project_ID() > 0)
            project = MProject
                    .getById(getCtx(), importEmployee.getC_Project_ID());
        if (project == null && importEmployee.getProjectValue() != null)
            project = MProject.getByValue(getCtx(), importEmployee.getProjectValue());
        if (project != null && project.getC_Project_ID() > 0)
            importEmployee.setC_Project_ID(project.getC_Project_ID());

        //Set Department
        MHRDepartment department = null;
        if (importEmployee.getHR_Department_ID() > 0)
            department = MHRDepartment.getById(getCtx(), importEmployee.getHR_Department_ID());
        if (department == null  && importEmployee.getDepartmentValue() != null)
            department = MHRDepartment.getByValue(getCtx(), importEmployee.getDepartmentValue());
        if (department == null || department.getHR_Department_ID() < 0) {
            if (importEmployee.getDepartmentValue() != null && importEmployee.getDepartmentName() != null) {
                department = new MHRDepartment(getCtx(), importEmployee.getDepartmentValue(), importEmployee.getDepartmentName(), trxName);
                department.saveEx();
            }
        }
        if (department != null && department.getHR_Department_ID() > 0)
            importEmployee.setHR_Department_ID(department.getHR_Department_ID());
        //Set Job
        MHRJob job = null;
        if (importEmployee.getHR_Job_ID() > 0)
            job = MHRJob.getById(getCtx(), importEmployee.getHR_Job_ID());
        if (job == null && importEmployee.getJobValue() != null)
            job = MHRJob.getByValue(getCtx(), importEmployee.getJobValue());
        if (job == null || job.getHR_Job_ID() < 0) {
            if (importEmployee.getJobValue() != null && importEmployee.getJobName() != null) {
                job = new MHRJob(getCtx(), importEmployee.getJobValue(), importEmployee.getJobName(), trxName);
                job.saveEx();
            }
        }
        if (job != null && job.getHR_Job_ID() > 0)
            importEmployee.setHR_Job_ID(job.getHR_Job_ID());
        //Set Job Education
        MHRJobEducation jobEducation = null;
        if (importEmployee.getHR_JobEducation_ID() > 0)
            jobEducation = new MHRJobEducation(getCtx(), importEmployee.getHR_JobEducation_ID(), trxName);
        if (jobEducation == null && importEmployee.getJobEducationValue() != null)
            jobEducation = MHRJobEducation.getByValue(getCtx(), importEmployee.getJobEducationValue());
        if (jobEducation == null || jobEducation.getHR_JobEducation_ID() < 0) {
            if (importEmployee.getJobEducationValue() != null && importEmployee.getJobEducationName() != null) {
                jobEducation = new MHRJobEducation(getCtx(), importEmployee.getJobEducationValue(), importEmployee.getJobEducationName(), trxName);
                jobEducation.saveEx();
            }
        }
        if (jobEducation != null && jobEducation.getHR_JobEducation_ID() > 0)
        	importEmployee.setHR_JobEducation_ID(jobEducation.getHR_JobEducation_ID());
        // Set Carrer Level
        MHRCareerLevel careerLevel = null;
        if (importEmployee.getHR_CareerLevel_ID() > 0)
            careerLevel = new MHRCareerLevel(getCtx(), importEmployee.getHR_CareerLevel_ID(), trxName);
        if (careerLevel == null && importEmployee.getCareerLevelValue() != null)
            careerLevel = MHRCareerLevel.getByValue(getCtx(), importEmployee.getCareerLevelValue());
        if (careerLevel == null || careerLevel.getHR_CareerLevel_ID() < 0) {
            if (importEmployee.getCareerLevelValue() != null && importEmployee.getCareerLevelName() != null) {
                careerLevel = new MHRCareerLevel(getCtx(), importEmployee.getCareerLevelValue(), importEmployee.getCareerLevelName(), trxName);
                careerLevel.saveEx();
            }
        }
        if (careerLevel != null && careerLevel.getHR_CareerLevel_ID() > 0)
            importEmployee.setHR_CareerLevel_ID(careerLevel.getHR_CareerLevel_ID());
        // Set Job Type
        MHRJobType jobType = null;
        if (importEmployee.getHR_JobType_ID() > 0)
            jobType = MHRJobType.getById(getCtx(), importEmployee.getHR_JobType_ID());
        if (jobType == null && importEmployee.getJobTypeValue() != null)
            jobType = MHRJobType.getByValue(getCtx(), importEmployee.getJobTypeValue());
        if (jobType == null || jobType.getHR_JobType_ID() < 0) {
            if (importEmployee.getJobTypeValue() != null && importEmployee.getJobTypeName() != null) {
                jobType = new MHRJobType(getCtx(), importEmployee.getJobTypeValue(), importEmployee.getJobTypeName(), trxName);
                jobType.saveEx();
            }
        }
        if (jobType != null && jobType.getHR_JobType_ID() > 0)
            importEmployee.setHR_JobType_ID(jobType.getHR_JobType_ID());

        // Set Payroll
        MHRPayroll payroll = null;
        if (importEmployee.getHR_Payroll_ID() > 0)
            payroll = MHRPayroll.getById(getCtx(), importEmployee.getHR_Job_ID());
        if (payroll == null && importEmployee.getPayrollValue() != null)
            payroll = MHRPayroll.getByValue(getCtx(), importEmployee.getPayrollValue());
        if (payroll != null && payroll.getHR_Payroll_ID() > 0)
            importEmployee.setHR_Payroll_ID(payroll.getHR_Payroll_ID());
        // Set Activity
        MActivity activity = null;
        if (importEmployee.getC_Activity_ID() > 0)
            activity = MActivity.getById(getCtx(), importEmployee.getC_Activity_ID());
        if (activity == null && importEmployee.getActivityValue() != null)
            activity = MActivity.getByValue(getCtx(), importEmployee.getActivityValue());
        if (activity != null && activity.getC_Activity_ID() > 0)
            importEmployee.setC_Activity_ID(activity.getC_Activity_ID());
        // Set Campaign
        MCampaign campaign = null;
        if (importEmployee.getC_Campaign_ID() > 0)
        	campaign = MCampaign.getById(getCtx(), importEmployee.getC_Campaign_ID());
        if (campaign == null && importEmployee.getCampaignValue() != null)
        	campaign = MCampaign.getByValue(getCtx(), importEmployee.getCampaignValue());
        if (campaign != null && campaign.getC_Campaign_ID() > 0)
            importEmployee.setC_Campaign_ID(campaign.getC_Campaign_ID());
        // Set Sales Region
        MSalesRegion salesRegion = null;
        if (importEmployee.getC_SalesRegion_ID() > 0)
        	salesRegion = MSalesRegion.getById(getCtx(), importEmployee.getC_SalesRegion_ID());
        if (salesRegion == null && importEmployee.getSalesRegionValue() != null)
        	salesRegion = MSalesRegion.getByValue(getCtx(), importEmployee.getSalesRegionValue());
        if (salesRegion != null && salesRegion.getC_SalesRegion_ID() > 0)
            importEmployee.setC_SalesRegion_ID(salesRegion.getC_SalesRegion_ID());
        // Set Work Group
        MHRWorkGroup workGroup = null;
        if (importEmployee.getHR_WorkGroup_ID() > 0)
        	workGroup = MHRWorkGroup.getById(getCtx(), importEmployee.getHR_WorkGroup_ID());
        if (workGroup == null && importEmployee.getWorkGroupValue() != null)
        	workGroup = MHRWorkGroup.getByValue(getCtx(), importEmployee.getWorkGroupValue());
        if (workGroup != null && workGroup.getHR_WorkGroup_ID() > 0)
            importEmployee.setHR_WorkGroup_ID(workGroup.getHR_WorkGroup_ID());
        // Set Shift Group
        MHRShiftGroup shiftGroup = null;
        if (importEmployee.getHR_ShiftGroup_ID() > 0)
        	shiftGroup = MHRShiftGroup.getById(getCtx(), importEmployee.getHR_WorkGroup_ID());
        if (shiftGroup == null && importEmployee.getShiftGroupValue() != null)
        	shiftGroup = MHRShiftGroup.getByValue(getCtx(), importEmployee.getWorkGroupValue());
        if (shiftGroup != null && shiftGroup.getHR_ShiftGroup_ID() > 0)
            importEmployee.setHR_ShiftGroup_ID(shiftGroup.getHR_ShiftGroup_ID());
        
        // Set Degree
        MHRDegree degree = null;
        if (importEmployee.getHR_Degree_ID() > 0)
        	degree = MHRDegree.getById(getCtx(), importEmployee.getHR_Degree_ID());
        if (degree == null && importEmployee.getDegreeValue() != null)
        	degree = MHRDegree.getByValue(getCtx(), importEmployee.getDegreeValue());
        if (degree == null || degree.getHR_Degree_ID() < 0) {
            if (importEmployee.getDegreeValue() != null && importEmployee.getDegreeName() != null) {
            	degree = new MHRDegree(getCtx(), importEmployee.getDegreeValue(), importEmployee.getDegreeName(), trxName);
            	degree.saveEx();
            }
        }
        //	Set it
        if (degree != null && degree.getHR_Degree_ID() > 0)
            importEmployee.setHR_Degree_ID(degree.getHR_Degree_ID());
        // Set Grade
        MHRGrade grade = null;
        if (importEmployee.getHR_Grade_ID() > 0)
        	grade = MHRGrade.getById(getCtx(), importEmployee.getHR_Grade_ID());
        if (grade == null && importEmployee.getGradeValue() != null)
        	grade = MHRGrade.getByValue(getCtx(), importEmployee.getGradeValue());
        if (grade == null || grade.getHR_Grade_ID() < 0) {
            if (importEmployee.getGradeValue() != null && importEmployee.getGradeName() != null) {
            	grade = new MHRGrade(getCtx(), importEmployee.getGradeValue(), importEmployee.getGradeName(), trxName);
            	grade.saveEx();
            }
        }
        if (grade != null && grade.getHR_Grade_ID() > 0)
            importEmployee.setHR_Grade_ID(grade.getHR_Grade_ID());
        //Set Designation
        MHRDesignation designation = null;
        if (importEmployee.getHR_Designation_ID() > 0)
            designation = MHRDesignation.getById(getCtx(), importEmployee.getHR_Designation_ID());
        if (designation == null && importEmployee.getDesignationValue() != null)
            designation = MHRDesignation.getByValue(getCtx(), importEmployee.getDesignationValue());
        if (designation != null && designation.getHR_Designation_ID() > 0)
            importEmployee.setHR_Designation_ID(designation.getHR_Designation_ID());
        //Set Salary Structure
        MHRSalaryStructure salaryStructure = null;
        if (importEmployee.getHR_SalaryRange_ID() > 0)
            salaryStructure = MHRSalaryStructure.getById(getCtx(), importEmployee.getHR_SalaryStructure_ID());
        if (salaryStructure == null && importEmployee.getSalaryStructureValue() != null)
            salaryStructure = MHRSalaryStructure.getByValue(getCtx(), importEmployee.getSalaryStructureValue());
        if (salaryStructure != null && salaryStructure.getHR_SalaryStructure_ID() > 0)
            importEmployee.setHR_SalaryRange_ID(salaryStructure.getHR_SalaryStructure_ID());
        //Set Salary Range
        MHRSalaryRange salaryRange = null;
        if (importEmployee.getHR_SalaryRange_ID() > 0)
            salaryRange = MHRSalaryRange.getById(getCtx(), importEmployee.getHR_SalaryRange_ID());
        if (salaryRange == null && importEmployee.getSalaryRangeValue() != null)
            salaryRange = MHRSalaryRange.getByValue(getCtx(), importEmployee.getSalaryRangeValue());
        if (salaryRange != null && salaryRange.getHR_SalaryRange_ID() > 0)
            importEmployee.setHR_SalaryRange_ID(salaryRange.getHR_SalaryRange_ID());
        // Set Employee Type
        MHREmployeeType employeeType = null;
        if (importEmployee.getHR_EmployeeType_ID() > 0)
            employeeType = MHREmployeeType.getById(getCtx(), importEmployee.getHR_EmployeeType_ID());
        if (employeeType == null && importEmployee.getEmployeeTypeValue() != null)
            employeeType = MHREmployeeType.getByValue(getCtx(), importEmployee.getEmployeeTypeValue());
        if (employeeType == null || employeeType.getHR_EmployeeType_ID() < 0) {
            if (importEmployee.getEmployeeTypeValue() != null && importEmployee.getEmployeeTypeName() != null 
            		&& payroll != null && payroll.getHR_Payroll_ID() > 0 ) {
                employeeType = new MHREmployeeType(
                        getCtx(),
                        importEmployee.getEmployeeTypeValue(),
                        importEmployee.getEmployeeTypeName(),
                        MHREmployeeType.WAGELEVEL_Daily ,
                        payroll.getHR_Payroll_ID() ,
                        trxName);
                employeeType.saveEx();
            }
        }
        if (employeeType != null && employeeType.getHR_EmployeeType_ID() > 0)
            importEmployee.setHR_EmployeeType_ID(employeeType.getHR_EmployeeType_ID());
        // Set Skill Type
        MHRSkillType skillType = null;
        if (importEmployee.getHR_SkillType_ID() > 0)
            skillType = MHRSkillType.getById(getCtx(), importEmployee.getHR_SkillType_ID());
        if (skillType == null && importEmployee.getSkillTypeValue() != null)
            skillType = MHRSkillType.getByValue(getCtx(), importEmployee.getSkillTypeValue());
        if (skillType == null || skillType.getHR_SkillType_ID() < 0) {
            if(importEmployee.getSkillTypeValue() != null && importEmployee.getSkillTypeName() != null) {
                skillType = new MHRSkillType(getCtx(), importEmployee.getSkillTypeValue(), importEmployee.getSkillTypeName(), trxName);
                skillType.saveEx();
            }
        }
        if (skillType != null && skillType.getHR_SkillType_ID() > 0)
            importEmployee.setHR_SkillType_ID(skillType.getHR_SkillType_ID());

        if (importEmployee.getAD_Org_ID() <= 0)
            stringError.append(" @AD_Org_ID@ @IsMandatory@");
        if (importEmployee.getC_BPartner_ID() <= 0)
            stringError.append(" @C_BPartner_ID@ @IsMandatory@,");
        if (importEmployee.getHR_Department_ID() <= 0)
            stringError.append(" @HR_Department_ID@ @NotFound@,");

        if (importEmployee.getHR_Job_ID() <= 0)
            stringError.append(" @HR_Job_ID@ @NotFound@,");

        if (importEmployee.getStartDate() == null)
            stringError.append(" @StartDate@ @IsMandatory@ ");

        if (!stringError.toString().isEmpty() && stringError.toString().length() > 0) {
            importEmployee.setI_ErrorMsg(Msg.parseTranslation(getCtx(), stringError.toString()));
            importEmployee.saveEx();
        }
        importEmployee.saveEx();
    }

    /**
     * Import Record for Employee
     * @param importEmployee
     * @param trxName
     * @return
     */
    private boolean importRecord(X_I_HR_Employee importEmployee, String trxName) {

        if (importEmployee.getI_ErrorMsg() != null)
            return false;

        if (!isOnlyValidateData()) {
            MHREmployee employee = MHREmployee.getByPartnerIdAndStartDate(importEmployee.getCtx(), importEmployee.getC_BPartner_ID(), importEmployee.getStartDate(), trxName);
            if (employee != null && employee.getHR_Employee_ID() <= 0) {
                importEmployeeImages(importEmployee);
                employee.updateEmployeeData(importEmployee).saveEx();
            } else {
                importEmployeeImages(importEmployee);
                employee = new MHREmployee(importEmployee);
                employee.saveEx();
                updatePartnerFromEmployeeData(importEmployee);
            }
            importEmployee.setHR_Employee_ID(employee.getHR_Employee_ID());
            importEmployee.setI_IsImported(true);
            importEmployee.setProcessed(true);
            importEmployee.saveEx();
            return true;
        } else return false;
    }

    private MBPartner createPartnerFromEmployeeData(X_I_HR_Employee importEmployee)
    {
        MBPartner partner = new MBPartner(importEmployee.getCtx() , 0 , importEmployee.get_TrxName());
        partner.setAD_Org_ID(0);
        partner.setValue(importEmployee.getBPartnerValue());
        partner.setName(importEmployee.getName());
        partner.setName2(importEmployee.getName2());
        partner.setIsEmployee(true);
        partner.setIsCustomer(true);
        partner.setIsSalesRep(false);
        partner.setC_BP_Group_ID(getBusinessPartnerGroupId());
        partner.setBirthday(importEmployee.getBirthday());
        partner.setBloodGroup(importEmployee.getBloodGroup());
        partner.setGender(importEmployee.getGender());
        partner.setPlaceOfBirth(importEmployee.getPlaceOfBirth());
        partner.setFathersName(importEmployee.getFathersName());
        partner.saveEx();

        importEmployee.setC_BPartner_ID(importEmployee.getC_BPartner_ID());
        importEmployee.saveEx();
        return  partner;
    }

    /**
     * Update Business Partner from import employee data
     * @param importEmployee
     * @return
     */
    private MBPartner updatePartnerFromEmployeeData(X_I_HR_Employee importEmployee) {
        MBPartner partner = (MBPartner) importEmployee.getC_BPartner();
        partner.setName(importEmployee.getName());
        partner.setName2(importEmployee.getName2());
        partner.setBirthday(importEmployee.getBirthday());
        partner.setBloodGroup(importEmployee.getBloodGroup());
        partner.setGender(importEmployee.getGender());
        partner.setPlaceOfBirth(importEmployee.getPlaceOfBirth());
        partner.setFathersName(importEmployee.getFathersName());
        partner.saveEx();
        return partner;

    }

    /**
     * get ids based table name
     * @param tableName
     * @param whereClause
     * @param trxName
     * @param parameters
     * @return
     */
    private int getId(String tableName, String whereClause, String trxName, Object... parameters) {
        return new Query(getCtx(), tableName, whereClause, trxName)
                .setParameters(parameters).firstId();
    }

    /**
     * get employee ids
     * @param isImported
     * @param isProcessed
     * @return
     */
    private int[] getImportEmployeeIds(boolean isImported, boolean isProcessed) {
        StringBuilder whereClause = new StringBuilder();
        whereClause.append(I_I_HR_Employee.COLUMNNAME_I_IsImported).append("=? AND ")
                .append(I_I_HR_Employee.COLUMNNAME_Processed).append("=?");

        return new Query(getCtx(), X_I_HR_Employee.Table_Name, whereClause.toString(), null)
                .setOnlyActiveRecords(true)
                .setParameters(isImported, isProcessed)
                .getIDs();

    }

    /**
     * import employee images based on import employee table
     * @param importEmployee
     */
    private void importEmployeeImages(X_I_HR_Employee importEmployee) {
        String fileName = importEmployee.getFile_Directory();
        String imageEmployeeName = importEmployee.getBPartnerValue();
        String logoEmployeeName = "Logo" + importEmployee.getBPartnerValue();
        String thumbEmployeeName = "Thumb" + importEmployee.getBPartnerValue();
        String path = fileName + "/" + imageEmployeeName;
        byte[] imageEmployee = getImage(path + ".jpg");
        if (imageEmployee == null)
            imageEmployee = getImage(path + ".JPG");
        if (imageEmployee == null)
            imageEmployee = getImage(path + ".png");
        if (imageEmployee == null)
            imageEmployee = getImage(path + ".PNG");

        if (imageEmployee != null && imageEmployee.length > 0) {
            MImage image = MImage.get(Env.getCtx(), 0);
            image.setName(imageEmployeeName);
            image.setImageURL(imageEmployeeName);
            image.setBinaryData(imageEmployee);
            image.saveEx();
            importEmployee.setEmployeeImage_ID(image.getAD_Image_ID());
            importEmployee.saveEx();
        }

        path = fileName + "/" + logoEmployeeName;
        byte[] logoEmployee = getImage(path + ".jpg");
        if (logoEmployee == null)
            logoEmployee = getImage(path + ".JPG");
        if (logoEmployee == null)
            logoEmployee = getImage(path + ".png");
        if (logoEmployee == null)
            logoEmployee = getImage(path + ".PNG");
        if (logoEmployee != null && logoEmployee.length > 0) {
            MImage image = MImage.get(Env.getCtx(), 0);
            image.setName(logoEmployeeName);
            image.setImageURL(logoEmployeeName);
            image.setBinaryData(logoEmployee);
            image.saveEx();
            importEmployee.setLogo_ID(image.getAD_Image_ID());
            importEmployee.saveEx();
        }

        path = fileName + "/" + thumbEmployeeName;
        byte[] thumbEmployee = getImage(path + ".jpg");
        if (thumbEmployee == null)
            thumbEmployee = getImage(path + ".JPG");
        if (thumbEmployee == null)
            thumbEmployee = getImage(path + ".png");
        if (thumbEmployee == null)
            thumbEmployee = getImage(path + ".PNG");
        if (thumbEmployee != null && thumbEmployee.length > 0) {
            MImage image = MImage.get(Env.getCtx(), 0);
            image.setName(thumbEmployeeName);
            image.setImageURL(thumbEmployeeName);
            image.setBinaryData(thumbEmployee);
            image.saveEx();
            importEmployee.setThumbImage_ID(image.getAD_Image_ID());
            importEmployee.saveEx();
        }
    }

    /**
     * get image from path file
     * @param pathFile
     * @return
     */
    private byte[] getImage(String pathFile) {
        byte[] imageBytes = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(pathFile);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024 * 8]; //  8kB
            int length = -1;
            while ((length = fileInputStream.read(buffer)) != -1)
                byteArrayOutputStream.write(buffer, 0, length);
            fileInputStream.close();
            imageBytes = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return imageBytes;
        } catch (Exception e) {
            return null;
        }
    }
}