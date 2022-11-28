/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, see http://www.gnu.org/licenses or write to the * 
 * Free Software Foundation, Inc.,                                            *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2016                                                    *
 * All Rights Reserved.                                                       *
 *****************************************************************************/
package org.eevolution.hr.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.adempiere.core.domains.models.I_C_BPartner;
import org.adempiere.core.domains.models.I_HR_EmployeeDependent;
import org.adempiere.core.domains.models.X_HR_EmployeeDependent;
import org.compiere.model.Query;

/**
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *
 */
public class MHREmployeeDependent extends X_HR_EmployeeDependent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 113089938678164180L;

	/**
	 * @param ctx
	 * @param HR_EmployeeDependent_ID
	 * @param trxName
	 */
	public MHREmployeeDependent(Properties ctx, int HR_EmployeeDependent_ID, String trxName) {
		super(ctx, HR_EmployeeDependent_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MHREmployeeDependent(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**	Dependents List	*/
	private List<MHREmployeeDependent> dependentList;

	/**
	 * Get Dependents of Business Partner
	 *
	 * @param ctx
	 * @param bpartnerId
	 * @param relationshipValue
	 * @param gradeValue
	 * @param onlyDepending
	 * @param onlyScholarship
	 * @param onlyActiveStudent
	 * @return
	 */
	@Deprecated
	public static MHREmployeeDependent[] getEmployeeDependents(
			Properties ctx,
			int bpartnerId,
			String relationshipValue,
			String gradeValue,
			boolean onlyDepending,
			boolean onlyScholarship,
			boolean onlyActiveStudent) {
		return getEmployeeDependents(ctx, bpartnerId, relationshipValue, gradeValue, onlyDepending, onlyDepending, onlyActiveStudent, null).toArray(new MHREmployeeDependent[0]);
	}

	/**
	 * Get Dependents of Business Partner
	 *
	 * @param ctx
	 * @param bpartnerId
	 * @param relationshipValue
	 * @param gradeValue
	 * @param onlyDepending
	 * @param onlyScholarship
	 * @param onlyActiveStudent
	 * @param trxName
	 * @return
	 */
	public static List<MHREmployeeDependent> getEmployeeDependents(
			Properties ctx,
			int bpartnerId,
			String relationshipValue,
			String gradeValue,
			boolean onlyDepending,
			boolean onlyScholarship,
			boolean onlyActiveStudent,
			String trxName) {
		
		StringBuffer whereClause = new StringBuffer();
		//	Parameters
		ArrayList<Object> params = new ArrayList<Object>();
		//	Add Business Partner
		whereClause.append(I_C_BPartner.COLUMNNAME_C_BPartner_ID).append("= ?");
		params.add(bpartnerId);
		//	Add Relationship
		if(relationshipValue != null
				&& relationshipValue.length() > 0) {
			whereClause.append(" AND EXISTS(SELECT 1 FROM HR_Relationship r "
					+ "								WHERE r.HR_Relationship_ID = HR_EmployeeDependent.HR_Relationship_ID "
					+ "								AND r.Value = ?)");
			params.add(relationshipValue);
		}
		//	Add Grade
		if(gradeValue != null
				&& gradeValue.length() > 0) {
			whereClause.append(" AND EXISTS(SELECT 1 FROM HR_Grade g "
					+ "								WHERE g.HR_Grade_ID = HR_EmployeeDependent.HR_Grade_ID "
					+ "								AND g.Value = ?)");
			params.add(gradeValue);
		}
		//	Add Only Depending
		if(onlyDepending) {
			whereClause.append(" AND IsDepending = ?");
			params.add(true);
		}
		//	Add Only Scholarship
		if(onlyScholarship) {
			whereClause.append(" AND IsScholarship = ?");
			params.add(true);
		}
		//	Add Only Active Student
		if(onlyActiveStudent) {
			whereClause.append(" AND IsActiveStudent = ?");
			params.add(true);
		}
		//	Get Family
		return new Query(ctx, I_HR_EmployeeDependent.Table_Name, whereClause.toString(), trxName)
				.setParameters(params)
				.setOnlyActiveRecords(true)
				.<MHREmployeeDependent>list();
	}
	
	/**
	 * Static method for depending load
	 * @param ctx
	 * @param bpartnerId
	 * @param trxName
	 * @return
	 */
	public static MHREmployeeDependent loadDependent(Properties ctx, int bpartnerId, String trxName) {
		return new MHREmployeeDependent(ctx, 0, trxName).loadDependent(bpartnerId);
	}
	
	/**
	 * Load Dependent from BP
	 * @param bpartnerId
	 * @return
	 */
	public MHREmployeeDependent loadDependent(int bpartnerId) {
		//	Get Family
		dependentList = new Query(getCtx(), I_HR_EmployeeDependent.Table_Name, I_C_BPartner.COLUMNNAME_C_BPartner_ID + " = ?", get_TrxName())
				.setParameters(bpartnerId)
				.setOnlyActiveRecords(true)
				.<MHREmployeeDependent>list();
		//	return this
		return this;
	}
	
	/**
	 * Make filter for depending
	 * @param isDepending
	 * @return
	 */
	public MHREmployeeDependent filterDepending(boolean isDepending) {
		if(dependentList != null
				&& dependentList.size() > 0) {
			dependentList = dependentList.stream()
					.filter(entry -> entry.isDepending() == isDepending)
					.collect(Collectors.toList());
		}
		//	Return this
		return this;
	}
	
	/**
	 * Make filter for active student
	 * @param isActiveStudent
	 * @return
	 */
	public MHREmployeeDependent filterActiveStudent(boolean isActiveStudent) {
		if(dependentList != null
				&& dependentList.size() > 0) {
			dependentList = dependentList.stream()
					.filter(entry -> entry.isActiveStudent() == isActiveStudent)
					.collect(Collectors.toList());
		}
		//	Return this
		return this;
	}
	
	/**
	 * Make filter for scholarship
	 * @param isScholarship
	 * @return
	 */
	public MHREmployeeDependent filterScholarship(boolean isScholarship) {
		if(dependentList != null
				&& dependentList.size() > 0) {
			dependentList = dependentList.stream()
					.filter(entry -> entry.isScholarship() == isScholarship)
					.collect(Collectors.toList());
		}
		//	Return this
		return this;
	}

	/**
	 * Make filter for degree value
	 * @param degreeValue
	 * @return
	 */
	public MHREmployeeDependent filterDegreeValue(String degreeValue) {
		if(degreeValue == null
				|| degreeValue.length() == 0) {
			return this;
		}
		if(dependentList != null
				&& dependentList.size() > 0) {
			//	Get Degree Id
			MHRDegree degree = MHRDegree.getByValue(getCtx(), degreeValue, get_TrxName());
			if(degree != null
					&& degree.getHR_Degree_ID() != 0) {
				dependentList = dependentList.stream()
						.filter(entry -> entry.getHR_Degree_ID() == degree.getHR_Degree_ID())
						.collect(Collectors.toList());
			}
		}
		//	Return this
		return this;
	}
	
	/**
	 * Make filter for grade value
	 * @param gradeValue
	 * @return
	 */
	public MHREmployeeDependent filterGradeValue(String gradeValue) {
		if(gradeValue == null
				|| gradeValue.length() == 0) {
			return this;
		}
		if(dependentList != null
				&& dependentList.size() > 0) {
			//	Get Degree Id
			MHRGrade grade = MHRGrade.getByValue(getCtx(), gradeValue, get_TrxName());
			if(grade != null
					&& grade.getHR_Grade_ID() != 0) {
				dependentList = dependentList.stream()
						.filter(entry -> entry.getHR_Grade_ID() == grade.getHR_Grade_ID())
						.collect(Collectors.toList());
			}
		}
		//	Return this
		return this;
	}
	
	/**
	 * Make filter for career level value
	 * @param careerLevelValue
	 * @return
	 */
	public MHREmployeeDependent filterCareerLevelValue(String careerLevelValue) {
		if(careerLevelValue == null
				|| careerLevelValue.length() == 0) {
			return this;
		}
		if(dependentList != null
				&& dependentList.size() > 0) {
			//	Get Degree Id
			MHRCareerLevel careerLevel = MHRCareerLevel.getByValue(getCtx(), careerLevelValue, get_TrxName());
			if(careerLevel != null
					&& careerLevel.getHR_CareerLevel_ID() != 0) {
				dependentList = dependentList.stream()
						.filter(entry -> entry.getHR_CareerLevel_ID() == careerLevel.getHR_CareerLevel_ID())
						.collect(Collectors.toList());
			}
		}
		//	Return this
		return this;
	}
	
	/**
	 * Make filter for race value
	 * @param raceValue
	 * @return
	 */
	public MHREmployeeDependent filterRaceValue(String raceValue) {
		if(raceValue == null
				|| raceValue.length() == 0) {
			return this;
		}
		if(dependentList != null
				&& dependentList.size() > 0) {
			//	Get Degree Id
			MHRRace race = MHRRace.getByValue(getCtx(), raceValue, get_TrxName());
			if(race != null
					&& race.getHR_Race_ID() != 0) {
				dependentList = dependentList.stream()
						.filter(entry -> entry.getHR_Race_ID() == race.getHR_Race_ID())
						.collect(Collectors.toList());
			}
		}
		//	Return this
		return this;
	}
	
	/**
	 * Make filter for relationship value
	 * @param relationshipValue
	 * @return
	 */
	public MHREmployeeDependent filterRelationshipValue(String relationshipValue) {
		if(relationshipValue == null
				|| relationshipValue.length() == 0) {
			return this;
		}
		if(dependentList != null
				&& dependentList.size() > 0) {
			//	Get Degree Id
			MHRRelationship race = MHRRelationship.getByValue(getCtx(), relationshipValue, get_TrxName());
			if(race != null
					&& race.getHR_Relationship_ID() != 0) {
				dependentList = dependentList.stream()
						.filter(entry -> entry.getHR_Relationship_ID() == race.getHR_Relationship_ID())
						.collect(Collectors.toList());
			}
		}
		//	Return this
		return this;
	}
	
	/**
	 * Make filter for Special Condition
	 * @param isSpecialCondition
	 * @return
	 */
	public MHREmployeeDependent filterIsSpecialCondition(boolean isSpecialCondition) {
		if(dependentList != null
				&& dependentList.size() > 0) {
			dependentList = dependentList.stream()
					.filter(entry -> entry.get_ValueAsBoolean("IsSpecialCondition") == isSpecialCondition)
					.collect(Collectors.toList());
		}
		//	Return this
		return this;
	}
	
	@Override
	public String toString() {
		return "MHREmployeeDependent[getName() " + getName() + "]";
	}
}
