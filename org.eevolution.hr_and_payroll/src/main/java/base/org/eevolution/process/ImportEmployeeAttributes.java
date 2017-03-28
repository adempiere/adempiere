/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2014 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Oscar Gómez  www.e-evolution.com                           *
 * Contributor(s): Victor Pérez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.process;

import org.compiere.model.MActivity;
import org.compiere.model.MCampaign;
import org.compiere.model.MOrg;
import org.compiere.model.MProject;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.eevolution.model.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Import Employee Attribute, this process allows import employee attribute for an employee
 * @author oscar.gomez@www.e-evolution.com, e-Evolution
 * @author victor.perez@www.e-evolution.com, e-Evolution
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/854">
 * 		@see FR [ 854 ] Add new columns for Concept Attribute</a>
 */
public class ImportEmployeeAttributes extends ImportEmployeeAttributesAbstract {
    /**
     * Prepare - e.g., get Parameters.
     */
    protected void prepare() {
        super.prepare();
    }    //	prepare

    /**
     * Process
     *
     * @return message
     * @throws Exception
     */
    protected String doIt() throws Exception {
        if (isDeleteoldimportedrecords())
            Arrays.stream(getAttributeIds(true,true, null)).forEach(recordId -> {
                X_I_HR_Attribute importAttribute = new X_I_HR_Attribute(getCtx(), recordId , null);
                importAttribute.deleteEx(true);
            });

        AtomicInteger importedRecord = new AtomicInteger(0);
        AtomicInteger withErrors = new AtomicInteger(0);
        Arrays.stream(getAttributeIds(false, false, null )).forEach(recordId -> {
            Trx.run( trxName -> {
                X_I_HR_Attribute importEmployeeAttribute = new X_I_HR_Attribute(getCtx(), recordId, trxName);
                fillIdValues(importEmployeeAttribute);
                if (importRecord(importEmployeeAttribute))
                    importedRecord.updateAndGet(record -> record + 1);
                else
                    withErrors.updateAndGet(error -> error + 1);
            });
        });
        return "@HR_Attribute_ID@ @Import@ @Records@ " + importedRecord.get() + " @Errors@ " + withErrors.get();
    }    //	doIt

    /**
     * Fill mandatory information
     *
     * @param importEmployeeAttribute
     */
    private void fillIdValues(X_I_HR_Attribute importEmployeeAttribute) {

    	StringBuilder messageError = new StringBuilder();
    	importEmployeeAttribute.setI_ErrorMsg("");
    	final String partnerQuery = "SELECT C_BPartner_ID FROM C_BPartner WHERE TRIM(Value) = TRIM(?)";
    	int partnerId = DB.getSQLValue(null, partnerQuery, importEmployeeAttribute.getValue());
    	if (partnerId < 0)
    		messageError.append("@HR_Employee_ID@ @NotFound@");

    	final String conceptQuery = "SELECT HR_Concept_ID FROM HR_Concept WHERE TRIM(Value) = TRIM(?)";
    	int conceptId = DB.getSQLValue(importEmployeeAttribute.get_TrxName(), conceptQuery, importEmployeeAttribute.getConceptValue());
    	if (conceptId < 0)
    		messageError.append(", ").append("@HR_Concept_ID@ @NotFound@");
    	if (importEmployeeAttribute.getValidFrom() == null)
    		messageError.append(", ").append("@Invalid@ @ValidFrom@");
        if (messageError.length() > 0)
            setImportError(importEmployeeAttribute, messageError.toString()).saveEx(importEmployeeAttribute.get_TrxName());

        importEmployeeAttribute.setC_BPartner_ID(partnerId);
        importEmployeeAttribute.setHR_Concept_ID(conceptId);
        //	For Reference
        //Set Race
        MHRRace race = null;
        if (importEmployeeAttribute.getHR_Race_ID() > 0)
            race = MHRRace.getById(getCtx(), importEmployeeAttribute.getHR_Race_ID());
        if (race != null && race.getHR_Race_ID() < 0 && importEmployeeAttribute.getRaceValue() != null)
            race = MHRRace.getByValue(getCtx(), importEmployeeAttribute.getRaceValue());
        if (race != null && race.getHR_Race_ID() > 0)
            importEmployeeAttribute.setHR_Race_ID(race.getHR_Race_ID());

        //Set Organization Trx
        MOrg orgTrx = null;
        if (importEmployeeAttribute.getAD_OrgTrx_ID() > 0)
            orgTrx = MOrg.get(getCtx(), importEmployeeAttribute.getAD_OrgTrx_ID());
        if (orgTrx == null && importEmployeeAttribute.getOrgTrxValue() != null) {
            int orgTrxId = getId(MOrg.Table_Name, MOrg.COLUMNNAME_Value + "=?", get_TrxName(), importEmployeeAttribute.getOrgTrxValue());
            orgTrx = MOrg.get(getCtx(), orgTrxId);
        }
        if (orgTrx != null && orgTrx.getAD_Org_ID() > 0)
            importEmployeeAttribute.setAD_OrgTrx_ID(orgTrx.getAD_Org_ID());

        //Set Project
        MProject project = null;
        if (importEmployeeAttribute.getC_Project_ID() > 0)
            project = MProject
                    .getById(getCtx(), importEmployeeAttribute.getC_Project_ID());
        if (project == null && importEmployeeAttribute.getProjectValue() != null)
            project = MProject.getByValue(getCtx(), importEmployeeAttribute.getProjectValue());
        if (project != null && project.getC_Project_ID() > 0)
            importEmployeeAttribute.setC_Project_ID(project.getC_Project_ID());

        //Set Department
        MHRDepartment department = null;
        if (importEmployeeAttribute.getHR_Department_ID() > 0)
            department = MHRDepartment.getById(getCtx(), importEmployeeAttribute.getHR_Department_ID());
        if (department == null  && importEmployeeAttribute.getDepartmentValue() != null)
            department = MHRDepartment.getByValue(getCtx(), importEmployeeAttribute.getDepartmentValue());
        if (department != null && department.getHR_Department_ID() > 0)
            importEmployeeAttribute.setHR_Department_ID(department.getHR_Department_ID());
        //Set Job
        MHRJob job = null;
        if (importEmployeeAttribute.getHR_Job_ID() > 0)
            job = MHRJob.getById(getCtx(), importEmployeeAttribute.getHR_Job_ID());
        if (job == null && importEmployeeAttribute.getJobValue() != null)
            job = MHRJob.getByValue(getCtx(), importEmployeeAttribute.getJobValue());
        if (job != null && job.getHR_Job_ID() > 0)
            importEmployeeAttribute.setHR_Job_ID(job.getHR_Job_ID());
        //Set Job Education
        MHRJobEducation jobEducation = null;
        if (importEmployeeAttribute.getHR_JobEducation_ID() > 0)
            jobEducation = new MHRJobEducation(getCtx(), importEmployeeAttribute.getHR_JobEducation_ID(), importEmployeeAttribute.get_TrxName());
        if (jobEducation == null && importEmployeeAttribute.getJobEducationValue() != null)
            jobEducation = MHRJobEducation.getByValue(getCtx(), importEmployeeAttribute.getJobEducationValue());
        if (jobEducation != null && jobEducation.getHR_JobEducation_ID() > 0)
        	importEmployeeAttribute.setHR_JobEducation_ID(jobEducation.getHR_JobEducation_ID());
        // Set Carrer Level
        MHRCareerLevel careerLevel = null;
        if (importEmployeeAttribute.getHR_CareerLevel_ID() > 0)
            careerLevel = new MHRCareerLevel(getCtx(), importEmployeeAttribute.getHR_CareerLevel_ID(), importEmployeeAttribute.get_TrxName());
        if (careerLevel == null && importEmployeeAttribute.getCareerLevelValue() != null)
            careerLevel = MHRCareerLevel.getByValue(getCtx(), importEmployeeAttribute.getCareerLevelValue());
        if (careerLevel != null && careerLevel.getHR_CareerLevel_ID() > 0)
            importEmployeeAttribute.setHR_CareerLevel_ID(careerLevel.getHR_CareerLevel_ID());
        // Set Job Type
        MHRJobType jobType = null;
        if (importEmployeeAttribute.getHR_JobType_ID() > 0)
            jobType = MHRJobType.getById(getCtx(), importEmployeeAttribute.getHR_JobType_ID());
        if (jobType == null && importEmployeeAttribute.getJobTypeValue() != null)
            jobType = MHRJobType.getByValue(getCtx(), importEmployeeAttribute.getJobTypeValue());
        if (jobType != null && jobType.getHR_JobType_ID() > 0)
            importEmployeeAttribute.setHR_JobType_ID(jobType.getHR_JobType_ID());
        // Set Payroll
        MHRPayroll payroll = null;
        if (importEmployeeAttribute.getHR_Payroll_ID() > 0)
            payroll = MHRPayroll.getById(getCtx(), importEmployeeAttribute.getHR_Job_ID());
        if (payroll == null && importEmployeeAttribute.getPayrollValue() != null)
            payroll = MHRPayroll.getByValue(getCtx(), importEmployeeAttribute.getPayrollValue());
        if (payroll != null && payroll.getHR_Payroll_ID() > 0)
            importEmployeeAttribute.setHR_Payroll_ID(payroll.getHR_Payroll_ID());
        // Set Activity
        MActivity activity = null;
        if (importEmployeeAttribute.getC_Activity_ID() > 0)
            activity = MActivity.getById(getCtx(), importEmployeeAttribute.getC_Activity_ID());
        if (activity == null && importEmployeeAttribute.getActivityValue() != null)
            activity = MActivity.getByValue(getCtx(), importEmployeeAttribute.getActivityValue());
        if (activity != null && activity.getC_Activity_ID() > 0)
            importEmployeeAttribute.setC_Activity_ID(activity.getC_Activity_ID());
        // Set Campaign
        MCampaign campaign = null;
        if (importEmployeeAttribute.getC_Campaign_ID() > 0)
        	campaign = MCampaign.getById(getCtx(), importEmployeeAttribute.getC_Campaign_ID());
        if (campaign == null && importEmployeeAttribute.getCampaignValue() != null)
        	campaign = MCampaign.getByValue(getCtx(), importEmployeeAttribute.getCampaignValue());
        if (campaign != null && campaign.getC_Campaign_ID() > 0)
            importEmployeeAttribute.setC_Campaign_ID(campaign.getC_Campaign_ID());
        // Set Work Group
        MHRWorkGroup workGroup = null;
        if (importEmployeeAttribute.getHR_WorkGroup_ID() > 0)
        	workGroup = MHRWorkGroup.getById(getCtx(), importEmployeeAttribute.getHR_WorkGroup_ID());
        if (workGroup == null && importEmployeeAttribute.getWorkGroupValue() != null)
        	workGroup = MHRWorkGroup.getByValue(getCtx(), importEmployeeAttribute.getWorkGroupValue());
        if (workGroup != null && workGroup.getHR_WorkGroup_ID() > 0)
            importEmployeeAttribute.setHR_WorkGroup_ID(workGroup.getHR_WorkGroup_ID());
        // Set Shift Group
        MHRShiftGroup shiftGroup = null;
        if (importEmployeeAttribute.getHR_ShiftGroup_ID() > 0)
        	shiftGroup = MHRShiftGroup.getById(getCtx(), importEmployeeAttribute.getHR_WorkGroup_ID());
        if (shiftGroup == null && importEmployeeAttribute.getShiftGroupValue() != null)
        	shiftGroup = MHRShiftGroup.getByValue(getCtx(), importEmployeeAttribute.getWorkGroupValue());
        if (shiftGroup != null && shiftGroup.getHR_ShiftGroup_ID() > 0)
            importEmployeeAttribute.setHR_ShiftGroup_ID(shiftGroup.getHR_ShiftGroup_ID());
        
        // Set Degree
        MHRDegree degree = null;
        if (importEmployeeAttribute.getHR_Degree_ID() > 0)
        	degree = MHRDegree.getById(getCtx(), importEmployeeAttribute.getHR_Degree_ID());
        if (degree == null && importEmployeeAttribute.getDegreeValue() != null)
        	degree = MHRDegree.getByValue(getCtx(), importEmployeeAttribute.getDegreeValue());
        //	Set it
        if (degree != null && degree.getHR_Degree_ID() > 0)
            importEmployeeAttribute.setHR_Degree_ID(degree.getHR_Degree_ID());
        // Set Grade
        MHRGrade grade = null;
        if (importEmployeeAttribute.getHR_Grade_ID() > 0)
        	grade = MHRGrade.getById(getCtx(), importEmployeeAttribute.getHR_Grade_ID());
        if (grade == null && importEmployeeAttribute.getGradeValue() != null)
        	grade = MHRGrade.getByValue(getCtx(), importEmployeeAttribute.getGradeValue());
        if (grade != null && grade.getHR_Grade_ID() > 0)
            importEmployeeAttribute.setHR_Grade_ID(grade.getHR_Grade_ID());
        //Set Designation
        MHRDesignation designation = null;
        if (importEmployeeAttribute.getHR_Designation_ID() > 0)
            designation = MHRDesignation.getById(getCtx(), importEmployeeAttribute.getHR_Designation_ID());
        if (designation == null && importEmployeeAttribute.getDesignationValue() != null)
            designation = MHRDesignation.getByValue(getCtx(), importEmployeeAttribute.getDesignationValue());
        if (designation != null && designation.getHR_Designation_ID() > 0)
            importEmployeeAttribute.setHR_Designation_ID(designation.getHR_Designation_ID());
        //Set Salary Structure
        MHRSalaryStructure salaryStructure = null;
        if (importEmployeeAttribute.getHR_SalaryRange_ID() > 0)
            salaryStructure = MHRSalaryStructure.getById(getCtx(), importEmployeeAttribute.getHR_SalaryStructure_ID());
        if (salaryStructure == null && importEmployeeAttribute.getSalaryStructureValue() != null)
            salaryStructure = MHRSalaryStructure.getByValue(getCtx(), importEmployeeAttribute.getSalaryStructureValue());
        if (salaryStructure != null && salaryStructure.getHR_SalaryStructure_ID() > 0)
            importEmployeeAttribute.setHR_SalaryRange_ID(salaryStructure.getHR_SalaryStructure_ID());
        //Set Salary Range
        MHRSalaryRange salaryRange = null;
        if (importEmployeeAttribute.getHR_SalaryRange_ID() > 0)
            salaryRange = MHRSalaryRange.getById(getCtx(), importEmployeeAttribute.getHR_SalaryRange_ID());
        if (salaryRange == null && importEmployeeAttribute.getSalaryRangeValue() != null)
            salaryRange = MHRSalaryRange.getByValue(getCtx(), importEmployeeAttribute.getSalaryRangeValue());
        if (salaryRange != null && salaryRange.getHR_SalaryRange_ID() > 0)
            importEmployeeAttribute.setHR_SalaryRange_ID(salaryRange.getHR_SalaryRange_ID());
        // Set Employee Type
        MHREmployeeType employeeType = null;
        if (importEmployeeAttribute.getHR_EmployeeType_ID() > 0)
            employeeType = MHREmployeeType.getById(getCtx(), importEmployeeAttribute.getHR_EmployeeType_ID());
        if (employeeType == null && importEmployeeAttribute.getEmployeeTypeValue() != null)
            employeeType = MHREmployeeType.getByValue(getCtx(), importEmployeeAttribute.getEmployeeTypeValue());
        if (employeeType != null && employeeType.getHR_EmployeeType_ID() > 0)
            importEmployeeAttribute.setHR_EmployeeType_ID(employeeType.getHR_EmployeeType_ID());
        // Set Skill Type
        MHRSkillType skillType = null;
        if (importEmployeeAttribute.getHR_SkillType_ID() > 0)
            skillType = MHRSkillType.getById(getCtx(), importEmployeeAttribute.getHR_SkillType_ID());
        if (skillType == null && importEmployeeAttribute.getSkillTypeValue() != null)
            skillType = MHRSkillType.getByValue(getCtx(), importEmployeeAttribute.getSkillTypeValue());
        if (skillType != null && skillType.getHR_SkillType_ID() > 0)
            importEmployeeAttribute.setHR_SkillType_ID(skillType.getHR_SkillType_ID());
        //	
        importEmployeeAttribute.saveEx();
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
     * set Import Error
     *
     * @param importEmployeeAttribute
     * @param error
     * @return
     */
    private X_I_HR_Attribute setImportError(X_I_HR_Attribute importEmployeeAttribute, String error) {
        importEmployeeAttribute.setI_ErrorMsg(Msg.parseTranslation(getCtx(), error));
        addLog(importEmployeeAttribute.getHR_Attribute_ID(), importEmployeeAttribute.getValidFrom(), importEmployeeAttribute.getAmount(), importEmployeeAttribute.getI_ErrorMsg());
        return importEmployeeAttribute;
    }

    private boolean importRecord(X_I_HR_Attribute importEmployeeAttribute) {

         if (importEmployeeAttribute.getI_ErrorMsg() != null && importEmployeeAttribute.getI_ErrorMsg().length() > 0) {
            importEmployeeAttribute.setProcessed(false);
            importEmployeeAttribute.setI_IsImported(false);
            importEmployeeAttribute.saveEx();
            return false;
         }

        Optional<BigDecimal> optionalAmount = Optional.ofNullable(importEmployeeAttribute.getAmount());
        Optional<BigDecimal> optionalQuantity = Optional.ofNullable(importEmployeeAttribute.getQty());
        Optional<Timestamp> optionalServiceDate = Optional.ofNullable(importEmployeeAttribute.getServiceDate());
        Optional<String> optionalTextMsg = Optional.ofNullable(importEmployeeAttribute.getTextMsg());
        Optional<Integer> optionalMinValue = Optional.ofNullable(importEmployeeAttribute.getMinValue());
        Optional<Integer> optionalMaxValue = Optional.ofNullable(importEmployeeAttribute.getMaxValue());
        Optional<Integer> optionalDepartmentId = Optional.ofNullable(importEmployeeAttribute.getHR_Department_ID());
        Optional<Integer> optionalJobId = Optional.ofNullable(importEmployeeAttribute.getHR_Job_ID());
        Optional<Timestamp> optionalValidFrom = Optional.ofNullable(importEmployeeAttribute.getValidFrom());
        Optional<Timestamp> optionalValidTo = Optional.ofNullable(importEmployeeAttribute.getValidTo());
        Optional<Integer> optionalRuleId = Optional.ofNullable(importEmployeeAttribute.getAD_Rule_ID());
        Optional<MHRPayroll> optionalPayroll = Optional.ofNullable(MHRPayroll.getByValue(getCtx(), importEmployeeAttribute.getPayrollValue()));
        Optional<String> optinalReferenceNo = Optional.ofNullable(importEmployeeAttribute.getReferenceNo());
        int payrollId = optionalPayroll.isPresent() ? optionalPayroll.get().getHR_Payroll_ID() : 0;
        MHREmployee employee = MHREmployee.getActiveEmployee(getCtx(), importEmployeeAttribute.getC_BPartner_ID(), get_TrxName());
        MHRConcept concept = MHRConcept.get(getCtx(), importEmployeeAttribute.getHR_Concept_ID());
        Optional<MHRAttribute> optionalAttribute = Optional.ofNullable(
                MHRAttribute.getByConceptAndPartnerId(
                        concept,
                        importEmployeeAttribute.getC_BPartner_ID(),
                        payrollId,
                        importEmployeeAttribute.getReferenceNo(),
                        importEmployeeAttribute.getDescription(),
                        importEmployeeAttribute.getValidFrom())
        );

        MHRAttribute employeeAttribute = optionalAttribute.orElse(new MHRAttribute(importEmployeeAttribute));
        if (employeeAttribute.getHR_Attribute_ID() <= 0) {
            employeeAttribute.setColumnType(concept.getColumnType());
            employeeAttribute.setHR_Employee_ID(employee.getHR_Employee_ID());
            optionalValidFrom.ifPresent(validFrom -> employeeAttribute.setValidFrom(validFrom));
            optionalPayroll.ifPresent(payroll -> employeeAttribute.setHR_Payroll_ID(payroll.getHR_Payroll_ID()));
        }

        optinalReferenceNo.ifPresent(referenceNo -> employeeAttribute.setReferenceNo(referenceNo));
        optionalAmount.filter(amount -> amount != null && amount.signum() > 0).ifPresent(amount -> employeeAttribute.setAmount(amount));
        optionalQuantity.filter(quantity -> quantity != null && quantity.signum() > 0).ifPresent(quantity -> employeeAttribute.setQty(quantity));
        optionalServiceDate.ifPresent(serviceDate -> employeeAttribute.setServiceDate(serviceDate));
        optionalTextMsg.ifPresent(msgText -> employeeAttribute.setTextMsg(msgText));
        optionalMinValue.filter(minValue -> minValue != null && minValue > 0).ifPresent(minValue -> employeeAttribute.setMinValue(minValue));
        optionalMaxValue.filter(maxValue -> maxValue != null && maxValue > 0).ifPresent(maxValue -> employeeAttribute.setMaxValue(maxValue));
        optionalDepartmentId.ifPresent(departamentId -> employeeAttribute.setHR_Department_ID(departamentId));
        optionalJobId.ifPresent(jobId -> employeeAttribute.setHR_Job_ID(jobId));
        optionalValidTo.ifPresent(validTo -> employeeAttribute.setValidTo(validTo));
        optionalRuleId.filter(ruleId -> ruleId != null && ruleId > 0).ifPresent(ruleId -> employeeAttribute.setAD_Rule_ID(ruleId));
        employeeAttribute.setIsActive(true);
        employeeAttribute.saveEx();

        importEmployeeAttribute.setHR_Attribute_ID(employeeAttribute.get_ID());
        importEmployeeAttribute.setI_IsImported(true);
        importEmployeeAttribute.setI_ErrorMsg("");
        importEmployeeAttribute.setProcessed(true);
        importEmployeeAttribute.saveEx();
        return true;
    }

        /**
         * Get Import Attribute List
         * @param isImported
         * @param isProcessed
         * @return
         */
        private int[] getAttributeIds(boolean isImported, boolean isProcessed, String trxName) {
            StringBuilder whereClause = new StringBuilder();
            whereClause.append(I_I_HR_Attribute.COLUMNNAME_I_IsImported).append("=? AND ")
                    .append(I_I_HR_Attribute.COLUMNNAME_Processed).append("=?");

            return new Query(getCtx(), X_I_HR_Attribute.Table_Name, whereClause.toString(), trxName)
                    .setOnlyActiveRecords(true)
                    .setParameters(isImported, isProcessed)
                    .getIDs();

        }
}