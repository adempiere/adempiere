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

package org.eevolution.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.Env;


/**
 * HR Employee Model
 *
 * @author Victor Perez
 * @author Cristina Ghita, www.arhipac.ro
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/775">
 * 		@see FR [ 775 ] Add isIgnoreDefaultPayroll feature for payroll definition</a>
 *		<a href="https://github.com/adempiere/adempiere/issues/854">
 * 		@see FR [ 854 ] Add new columns for Concept Attribute</a>
 */
public class MHREmployee extends X_HR_Employee
{
	private static final long serialVersionUID = -7083160315471023587L;

	/** Cache */
	private static CCache<Integer, MHREmployee> employeeCache = new CCache<Integer, MHREmployee>(Table_Name, 1000);

	/**
	 * Get Employee by Partner Id and Date Start
	 * @param ctx
	 * @param partnerId
	 * @param dateStart
	 * @param trxName
	 * @return
	 */
	public static MHREmployee getByPartnerIdAndStartDate(Properties ctx , Integer partnerId , Timestamp dateStart, String trxName)
	{
		StringBuilder whereClause = new StringBuilder();
		whereClause.append(MHREmployee.COLUMNNAME_C_BPartner_ID).append("=? AND ");
		whereClause.append(MHREmployee.COLUMNNAME_StartDate).append("=? ");
		return new Query(ctx , MHREmployee.Table_Name , whereClause.toString(),trxName)
				.setClient_ID()
				.setParameters(partnerId , dateStart)
				.first();
	}

	/**
	 * Get Employee by Id
	 * @param ctx
	 * @param employeeId
	 * @return
	 */
	public static MHREmployee getById(Properties ctx, int employeeId)
	{
		if (employeeId <= 0)
			return null;

		MHREmployee employee = employeeCache.get(employeeId);
		if (employee != null)
			return employee;

		employee = new MHREmployee(ctx, employeeId, null);
		if (employee.get_ID() == employeeId)
			employeeCache.put(employeeId, employee);
		else
			employee = null;
		return employee;
	}

	/**
	 * Get Employee by id
	 * @param ctx
	 * @param employeeId
	 * @return
	 */
	@Deprecated
	public static MHREmployee get(Properties ctx, int employeeId)
	{
		return getById(ctx, employeeId);
	}


	/**
	 * 	Get Employees of Process
	 *  @param process HR Process
	 * 	@return Array of Business Partners
	 */
	public static MBPartner[] getEmployees (MHRProcess process)
	{
		List<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer();
		whereClause.append("EXISTS(SELECT 1 FROM HR_Employee e " + 
				"WHERE e.C_BPartner_ID = C_BPartner.C_BPartner_ID " +
				"AND (e.EmployeeStatus = ? OR EmployeeStatus IS NULL) ");
		//	For Active
		params.add(MHREmployee.EMPLOYEESTATUS_Active);
		//	look if it is a not regular payroll
		MHRPayroll payroll = MHRPayroll.getById(process.getCtx(), process.getHR_Payroll_ID(), process.get_TrxName());
		// This payroll not content periods, NOT IS a Regular Payroll > ogi-cd 28Nov2007
		if(process.getHR_Payroll_ID() != 0 && process.getHR_Period_ID() != 0 && !payroll.isIgnoreDefaultPayroll())
		{
			whereClause.append(" AND (e.HR_Payroll_ID IS NULL OR e.HR_Payroll_ID=?) " );
			params.add(process.getHR_Payroll_ID());
		}
		//	Active Record
		whereClause.append(" AND e.IsActive = 'Y' " );
		// HR Period
		if(process.getHR_Period_ID() == 0)
		{
			whereClause.append(" AND e.StartDate <=? ");
			params.add(process.getDateAcct());
		}
		else
		{
			MHRPeriod period = new MHRPeriod(process.getCtx(), process.getHR_Period_ID(), process.get_TrxName());
			whereClause.append(" AND e.StartDate <=? ");
			params.add(period.getEndDate());
			whereClause.append(" AND (e.EndDate IS NULL OR e.EndDate >=?) ");
			params.add(period.getStartDate());
		}
		
		// Selected Department
		if (process.getHR_Department_ID() != 0)
		{
			whereClause.append(" AND e.HR_Department_ID =? ");
			params.add(process.getHR_Department_ID());
		}		
		// Selected Job add
		if (process.getHR_Job_ID() != 0)
		{
			whereClause.append(" AND e.HR_Job_ID =? ");
			params.add(process.getHR_Job_ID());
		}
		
		whereClause.append(" ) "); // end select from HR_Employee
		
		// Selected Employee
		if (process.getC_BPartner_ID() != 0)
		{
			whereClause.append(" AND C_BPartner_ID =? ");
			params.add(process.getC_BPartner_ID());
		}
		
		//client
		whereClause.append(" AND AD_Client_ID =? ");
		params.add(process.getAD_Client_ID());
		
		List<MBPartner> list = new Query(process.getCtx(), MBPartner.Table_Name, whereClause.toString(), process.get_TrxName())
								.setParameters(params)
								.setOnlyActiveRecords(true)
								.setOrderBy(COLUMNNAME_Name)
								.list();

		return list.toArray(new MBPartner[list.size()]);
	}	//	getEmployees
	
	public static MHREmployee getActiveEmployee(Properties ctx, int partnerId, String trxName) {
		
		List<Object> params = new ArrayList<Object>();
		params.add(partnerId);
		params.add(MHREmployee.EMPLOYEESTATUS_Active);
		return new Query(ctx, Table_Name, COLUMNNAME_C_BPartner_ID+"=? AND (EmployeeStatus = ? OR EmployeeStatus IS NULL)", trxName)
							.setOnlyActiveRecords(true)
							.setParameters(params)
							.setOrderBy(COLUMNNAME_StartDate+" DESC") // just in case...
							.first();
	}


	/**
	 * Employee Constructor
	 * @param ctx
	 * @param employeeId
	 * @param trxName
	 */
	public MHREmployee (Properties ctx, int employeeId, String trxName) //--
	{
		super (ctx, employeeId, trxName);
		if (employeeId == 0)
		{
			setClientOrg(Env.getAD_Client_ID(Env.getCtx()), Env.getAD_Org_ID(Env.getCtx()));
		}
	}	//	MHREmployee
	
	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 */
	public MHREmployee (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MHREmployee

	/**
	 * Construct based on import employee table
	 * @param importEmployee
	 */
	public MHREmployee(X_I_HR_Employee importEmployee)
	{
		super(importEmployee.getCtx() , 0 , importEmployee.get_TrxName());
		updateEmployeeData(importEmployee);
	}

	/**
	 * Update Employee data from import employee table
	 * @param importEmployee
	 * @return
	 */
	public MHREmployee updateEmployeeData(X_I_HR_Employee importEmployee)
	{
		setC_BPartner_ID(importEmployee.getC_BPartner_ID());
		setAD_Org_ID(importEmployee.getAD_Org_ID());
		setAD_User_ID(importEmployee.getAD_User_ID());
		setName(importEmployee.getName());
		setName2(importEmployee.getName2());
		setNationalCode(importEmployee.getNationalCode());
		setSSCode(importEmployee.getSSCode());
		setNationality_ID(importEmployee.getNationality_ID());
		setHR_Race_ID(importEmployee.getHR_Race_ID());
		setIdentificationMark(importEmployee.getIdentificationMark());
		setMaritalStatus(importEmployee.getMaritalStatus());
		setMarriageAnniversaryDate(importEmployee.getMarriageAnniversaryDate());
		setPartnersBirthDate(importEmployee.getPartnersBirthDate());
		setCode(importEmployee.getCode());

		setIsManager(importEmployee.isManager());
		setHR_Payroll_ID(importEmployee.getHR_Payroll_ID());
		setPaymentRule(importEmployee.getPaymentRule());
		setHR_Department_ID(importEmployee.getHR_Department_ID());
		setC_Activity_ID(importEmployee.getC_Activity_ID());
		setC_Project_ID(importEmployee.getC_Project_ID());
		setC_Campaign_ID(importEmployee.getC_Campaign_ID());
		setC_SalesRegion_ID(importEmployee.getC_SalesRegion_ID());
		setHR_Job_ID(importEmployee.getHR_Job_ID());
		setHR_WorkGroup_ID(importEmployee.getHR_WorkGroup_ID());
		setHR_ShiftGroup_ID(importEmployee.getHR_ShiftGroup_ID());
		setHR_Degree_ID(importEmployee.getHR_Degree_ID());
		setHR_Grade_ID(importEmployee.getHR_Grade_ID());
		setHR_JobType_ID(importEmployee.getHR_JobType_ID());
		setHR_JobEducation_ID(importEmployee.getHR_JobEducation_ID());
		setHR_CareerLevel_ID(importEmployee.getHR_CareerLevel_ID());
		setHR_Designation_ID(importEmployee.getHR_Designation_ID());
		setHR_SalaryStructure_ID(importEmployee.getHR_SalaryStructure_ID());
		setHR_SalaryRange_ID(importEmployee.getHR_SalaryRange_ID());
		setMonthlySalary(importEmployee.getMonthlySalary());
		setDailySalary(importEmployee.getDailySalary());
		setStartDate(importEmployee.getStartDate());
		setEndDate(importEmployee.getEndDate());
		setHR_EmployeeType_ID(importEmployee.getHR_EmployeeType_ID());
		setHR_SkillType_ID(importEmployee.getHR_SkillType_ID());
		setEmployeeStatus(importEmployee.getEmployeeStatus());

		setEmployeeImage_ID(importEmployee.getEmployeeImage_ID());
		setLogo_ID(importEmployee.getLogo_ID());
		setThumbImage_ID(importEmployee.getThumbImage_ID());
		setImageURL(importEmployee.getImageURL());
		return this;
	}
}	//	MHREmployee
