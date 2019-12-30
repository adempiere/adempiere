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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.model;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MCurrency;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Util;

/**
 *	Payroll Concept for HRPayroll Module
 *	
 *  @author Oscar Gómez Islas
 *  @author Teo Sarca, www.arhipac.ro
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/771">
 * 		@see FR [ 771 ] method don't search completed process</a>
 */
public class MHRMovement extends X_HR_Movement
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9100478676337821603L;

	/***
	 *   get list HR movement by Concept , Partner and description
	 * @param process
	 * @param conceptValue
	 * @param partnerId
	 * @return
	 */
	public static List<MHRMovement> findByProcessAndConceptValueAndPartnerId(MHRProcess process, String conceptValue, int partnerId)
	{
		return findByProcessAndConceptValueAndPartnerId(process, conceptValue , partnerId, null , null);
	}

	/***
	 *   get list HR movement by Concept , Partner and description
	 * @param process
	 * @param conceptValue
	 * @param partnerId
	 * @param description
	 * @param referenceNo
	 * @return
	 */
	public static List<MHRMovement> findByProcessAndConceptValueAndPartnerId(
			MHRProcess process,
			String conceptValue,
			int partnerId,
			String referenceNo,
			String description)
	{
		List<MHRMovement> movements =  new ArrayList<>();
		MHRConcept concept = MHRConcept.getByValue(process.getCtx() , conceptValue, process.get_TrxName());
		if (concept == null)
			return  movements;

		return findByProcessAndConceptIdAndPartnerId(
				process,
				concept.getHR_Concept_ID() ,
				partnerId,
				referenceNo ,
				description);
	}


	 /***
	 * get list HR movement by concept and PArtner
	 * @param process
	 * @param conceptId
	 * @param partnerId
	 * @param referenceNo
	 * @param description
	 * @return
	*/
	public static List<MHRMovement> findByProcessAndConceptIdAndPartnerId(
			MHRProcess process,
			int conceptId,
			int partnerId ,
			String referenceNo ,
			String description)
	{

		List<MHRMovement> movements = new ArrayList<>();
		List<Object> parameters = new ArrayList<>();
		if (conceptId <0)
			return  movements;

		StringBuilder whereClause = new StringBuilder();
		whereClause.append(MHRMovement.COLUMNNAME_HR_Process_ID).append("=? AND ");
		parameters.add(process.getHR_Process_ID());
		whereClause.append(MHRMovement.COLUMNNAME_HR_Concept_ID).append("=? AND ");
		parameters.add(conceptId);
		whereClause.append(MHRMovement.COLUMNNAME_C_BPartner_ID).append("=?");
		parameters.add(partnerId);
		if (referenceNo != null && referenceNo.length() > 0 )
		{
			whereClause.append( " AND ").append(MHRMovement.COLUMNNAME_ReferenceNo).append("=?");
			parameters.add(referenceNo);
		}
		if (description != null && description.length() > 0 )
		{
			whereClause.append( " AND ").append(MHRMovement.COLUMNNAME_Description).append("=?");
			parameters.add(description);
		}
		movements =  new Query(process.getCtx(), MHRMovement.Table_Name , whereClause.toString(), process.get_TrxName())
				.setClient_ID()
				.setParameters(parameters)
				.list();

		return movements;
	}

	public static  List<MHRMovement> findByConceptValueAndPartnerId(
			Properties ctx ,
			String conceptValue ,
			Integer partnerId  ,
			String referenceNo ,
			String description ,
			String trxName)
	{
		return findByConceptValueAndPartnerId(
				ctx ,
				conceptValue,
				partnerId,
				referenceNo ,
				description ,
				null ,
				null ,
				trxName);
	}

	/**
	 * Find by concept value , partner id , reference , description , from and to
	 * @param ctx
	 * @param conceptValue
	 * @param partnerId
	 * @param referenceNo
	 * @param description
	 * @param from
	 * @param to
	 * @param trxName
	 * @return
	 */
	public static  List<MHRMovement> findByConceptValueAndPartnerId(
			Properties ctx,
			String conceptValue ,
			Integer partnerId,
			String referenceNo ,
			String description ,
			Timestamp from ,
			Timestamp to ,
			String trxName)
	{
		List<MHRMovement> movements =  new ArrayList<>();
		List<Object> parameters = new ArrayList<>();
		if (conceptValue == null)
			return  movements;

		MHRConcept concept = MHRConcept.getByValue(ctx, conceptValue, trxName);
		if (concept == null)
			return movements;

		StringBuilder whereClause = new StringBuilder();
		whereClause.append(MHRMovement.COLUMNNAME_HR_Concept_ID).append("=?");
		parameters.add(concept.getHR_Concept_ID());
		if (partnerId > 0 ) {
			whereClause.append(" AND ").append(MHRMovement.COLUMNNAME_C_BPartner_ID).append("=?");
			parameters.add(partnerId);
		}

		if (referenceNo != null && referenceNo.length() > 0)
		{
			whereClause.append(" AND ").append(MHRMovement.COLUMNNAME_ReferenceNo).append("=?");
			parameters.add(referenceNo);
		}

		if (description != null && description.length() > 0)
		{
			whereClause.append(" AND ").append(MHRMovement.COLUMNNAME_Description).append("=?");
			parameters.add(description);
		}

		if (from != null) {
			whereClause.append(" AND ").append(MHRMovement.COLUMNNAME_ValidFrom).append("=>?");
			parameters.add(from);
		}

		if (to != null) {
			whereClause.append(" AND ").append(MHRMovement.COLUMNNAME_ValidTo).append("<=?");
			parameters.add(to);
		}

		movements = new Query(ctx, MHRMovement.Table_Name , whereClause.toString(), trxName)
				.setClient_ID()
				.setParameters(parameters)
				.list();
		return movements;
	}

	/**
	 * Helper Method: gets Concept value of a payroll between 2 dates
	 * @param conceptValue
	 * @param payrollId
	 * @param partnerId business partner for search
	 * @param from
	 * @param to
	 * @param trxName
	 * */
	public static double getConceptSum(
			Properties ctx, String
			conceptValue,
			int payrollId,
			int partnerId,
			Timestamp from,
			Timestamp to,
			String trxName) {
		return getConceptSum(ctx,conceptValue,payrollId,partnerId,from,to,false,trxName);
	}

	/**
	 * Helper Method: gets Concept value of a payroll between 2 dates
	 * @param conceptValue
	 * @param payrollId
	 * @param partnerId business partner for search
	 * @param from
	 * @param to
	 * @param includeInProcess
	 * @param trxName      */
	public static double getConceptSum(
			Properties ctx,
			String conceptValue,
			int payrollId,
			int partnerId,
			Timestamp from,
			Timestamp to,
			boolean includeInProcess,
			String trxName) {
		
		MHRConcept concept = MHRConcept.getByValue(ctx, conceptValue, trxName);
		if (concept == null)
			return 0.0;
		//
		// Detect field name
		final String fieldName;
		if (MHRConcept.COLUMNTYPE_Quantity.equals(concept.getColumnType())) {
			fieldName = MHRMovement.COLUMNNAME_Qty;
		} else if (MHRConcept.COLUMNTYPE_Amount.equals(concept.getColumnType())) {
			fieldName = MHRMovement.COLUMNNAME_Amount;
		} else {
			return 0; // TODO: throw exception?
		}
		//
		ArrayList<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer();
		//check concept
		whereClause.append(MHRMovement.COLUMNNAME_HR_Concept_ID + "=?");
		params.add(concept.get_ID());
		//check partner
		whereClause.append(" AND " + MHRMovement.COLUMNNAME_C_BPartner_ID  + "=?");
		params.add(partnerId);
		//Adding dates 
		whereClause.append(" AND validTo BETWEEN ? AND ?");
		params.add(from);
		params.add(to);
		//
		//check process and payroll
		whereClause.append(" AND EXISTS (SELECT 1 FROM HR_Process p"
							+" WHERE HR_Movement.HR_Process_ID = p.HR_Process_ID"
							+" AND " + (includeInProcess ? "p.DocStatus IN('DR','IP', 'CO', 'CL')" : "p.DocStatus IN('CO', 'CL')")
							+" AND p.HR_Payroll_ID=?");

		params.add(payrollId);
		
		whereClause.append(")");
		//
		StringBuffer sql = new StringBuffer("SELECT COALESCE(SUM(")
								.append(fieldName).append("),0) FROM ").append(MHRMovement.Table_Name)
								.append(" WHERE ").append(whereClause);
		BigDecimal value = DB.getSQLValueBDEx(null, sql.toString(), params);
		return value.doubleValue();
		
	} // getConcept

	/**
	 * Helper Method: gets Concept AVG value of a payroll between 2 dates
	 * @param conceptValue
	 * @param payrollId
	 * @param partnerId business partner for search
	 * @param from
	 * @param to
	 * @param trxName
	 */
	public static double getConceptAvg(
			Properties ctx,
			String conceptValue,
			int payrollId,
			int partnerId,
			Timestamp from,
			Timestamp to,
			String trxName) {
		return getConceptAvg(ctx,conceptValue,payrollId,partnerId,from,to,true,trxName);
	}

		/**
         * Helper Method: gets Concept AVG value of a payroll between 2 dates
		 * @param conceptValue
		 * @param payrollId
		 * @param partnerId business partner for search
		 * @param from
		 * @param to
		 * @param includeInProcess
		 * @param trxName               */
	public static double getConceptAvg(
			Properties ctx,
			String conceptValue,
			int payrollId,
			int partnerId,
			Timestamp from,
			Timestamp to,
			boolean includeInProcess,
			String trxName) {
		
		MHRConcept concept = MHRConcept.getByValue(ctx, conceptValue, trxName);
		if (concept == null)
			return 0.0;
		//
		// Detect field name
		final String fieldName;
		if (MHRConcept.COLUMNTYPE_Quantity.equals(concept.getColumnType())) {
			fieldName = MHRMovement.COLUMNNAME_Qty;
		} else if (MHRConcept.COLUMNTYPE_Amount.equals(concept.getColumnType())) {
			fieldName = MHRMovement.COLUMNNAME_Amount;
		} else {
			return 0; // TODO: throw exception?
		}
		//
		ArrayList<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer();
		//check concept
		whereClause.append(MHRMovement.COLUMNNAME_HR_Concept_ID + "=?");
		params.add(concept.get_ID());
		//check partner
		whereClause.append(" AND " + MHRMovement.COLUMNNAME_C_BPartner_ID  + "=?");
		params.add(partnerId);
		//Adding dates 
		whereClause.append(" AND validTo BETWEEN ? AND ?");
		params.add(from);
		params.add(to);
		//
		//check process and payroll
		whereClause.append(" AND EXISTS (SELECT 1 FROM HR_Process p"
							+" WHERE HR_Movement.HR_Process_ID = p.HR_Process_ID"
							+" AND " + (includeInProcess? "p.DocStatus IN('DR', 'IP', 'CO', 'CL')" : "p.DocStatus IN('CO', 'CL')")
							+" AND p.HR_Payroll_ID=?");

		params.add(payrollId);
		
		whereClause.append(")");
		//
		StringBuffer sql = new StringBuffer("SELECT COALESCE(AVG(")
								.append(fieldName).append("),0) FROM ").append(MHRMovement.Table_Name)
								.append(" WHERE ").append(whereClause);
		BigDecimal value = DB.getSQLValueBDEx(null, sql.toString(), params);
		return value.doubleValue();
		
	} // getConcept

	/**
	 *  Helper Method : Concept by range from-to in periods from a different payroll
	 *  periods with values 0 -1 1, etc. actual previous one period, next period
	 *  0 corresponds to actual period
	 *  @param conceptValue
	 *  @param payrollId ID is the ID of the payroll.
	 *  @param partnerId business partner for search
	 *  @param periodId period ID
	 *  @param periodFrom
	 *  @param periodTo the search is done by the period value, it helps to search from previous years
	 *  @param trxName
	 */
	public static double getConceptSum(
			Properties ctx,
			String conceptValue,
			int payrollId,
			int partnerId,
			int periodId,
			int periodFrom,
			int periodTo,
			String trxName) {
		return getConceptSum(ctx,conceptValue,payrollId,partnerId,periodId,periodFrom,periodTo,true,trxName);
	}

	/**
	 *  Helper Method : Concept by range from-to in periods from a different payroll
	 *  periods with values 0 -1 1, etc. actual previous one period, next period
	 *  0 corresponds to actual period
	 * @param conceptValue
	 * @param payrollId ID is the ID of the payroll.
	 * @param partnerId business partner for search
	 * @param periodId period ID
	 * @param periodFrom
	 * @param periodTo the search is done by the period value, it helps to search from previous years
	 * @param includeInProcess
	 * @param trxName
	 */
	public static double getConceptSum(
			Properties ctx,
			String conceptValue,
			int payrollId,
			int partnerId,
			int periodId,
			int periodFrom,
			int periodTo,
			boolean includeInProcess,
			String trxName) {
		MHRConcept concept = MHRConcept.getByValue(ctx, conceptValue, trxName);
		if (concept == null)
			return 0.0;
		//
		// Detect field name
		final String fieldName;
		if (MHRConcept.COLUMNTYPE_Quantity.equals(concept.getColumnType())) {
			fieldName = MHRMovement.COLUMNNAME_Qty;
		} else if (MHRConcept.COLUMNTYPE_Amount.equals(concept.getColumnType())) {
			fieldName = MHRMovement.COLUMNNAME_Amount;
		} else {
			return 0; // TODO: throw exception?
		}
		//
		MHRPeriod period = MHRPeriod.getById(ctx, periodId, trxName);
		ArrayList<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer();
		//check concept
		whereClause.append(MHRMovement.COLUMNNAME_HR_Concept_ID + "=?");
		params.add(concept.get_ID());
		//check partner
		whereClause.append(" AND " + MHRMovement.COLUMNNAME_C_BPartner_ID  + "=?");
		params.add(partnerId);
		//
		//check process and payroll
		whereClause.append(" AND EXISTS (SELECT 1 FROM HR_Process p"
				+" INNER JOIN HR_Period pr ON (pr.HR_Period_id=p.HR_Period_ID)"
				+" WHERE HR_Movement.HR_Process_ID = p.HR_Process_ID"
				+" AND " + (includeInProcess? "p.DocStatus IN('DR', 'IP', 'CO', 'CL')" : "p.DocStatus IN('CO', 'CL')")
				+" AND p.HR_Payroll_ID=?");

		params.add(payrollId);
		if (periodFrom < 0) {
			whereClause.append(" AND pr.PeriodNo >= ?");
			params.add(period.getPeriodNo() +periodFrom);
		}
		if (periodTo > 0) {
			whereClause.append(" AND pr.PeriodNo <= ?");
			params.add(period.getPeriodNo() +periodTo);
		}
		whereClause.append(")");
		//
		StringBuffer sql = new StringBuffer("SELECT COALESCE(SUM(")
					.append(fieldName).append("),0) FROM ").append(MHRMovement.Table_Name)
					.append(" WHERE ").append(whereClause);
		BigDecimal value = DB.getSQLValueBDEx(null, sql.toString(), params);
		return value.doubleValue();

	} // getConcept
	
	/**
	 * Get Last Movement for a concept value and a break date
	 * @param ctx
	 * @param conceptValue
	 * @param payrollId
	 * @param partnerId
	 * @param breakDate
	 * @return
	 */
	public static MHRMovement getLastMovement(
			Properties ctx,
			String conceptValue,
			int payrollId,
			int partnerId,
			Timestamp breakDate,
			String trxName) {
		MHRConcept concept = MHRConcept.getByValue(ctx, conceptValue, trxName);
		if (concept == null)
			return null;
		//	
		//
		ArrayList<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer();
		//check concept
		whereClause.append(MHRMovement.COLUMNNAME_HR_Concept_ID + "=?");
		params.add(concept.get_ID());
		//check partner
		whereClause.append(" AND " + MHRMovement.COLUMNNAME_C_BPartner_ID  + "=?");
		params.add(partnerId);
		//Adding dates 
		whereClause.append(" AND validTo <= ?");
		params.add(breakDate);
		//
		//check process and payroll
		whereClause.append(" AND EXISTS (SELECT 1 FROM HR_Process p"
							+" WHERE HR_Movement.HR_Process_ID = p.HR_Process_ID"
							+" AND p.DocStatus IN('CO', 'CL')"
							+" AND p.HR_Payroll_ID=?");

		params.add(payrollId);
		
		whereClause.append(")");
		//	return
		return new Query(ctx, I_HR_Movement.Table_Name, whereClause.toString(), trxName)
			.setParameters(params)
			.setOrderBy(I_HR_Movement.COLUMNNAME_ValidFrom + " DESC")
			.<MHRMovement>first();
	}
	
	/**
	 * Get a amount from the last concept
	 * @param ctx
	 * @param conceptValue
	 * @param payrollId
	 * @param partnerId
	 * @param breakDate
	 * @return
	 */
	public static double getLastConcept(
			Properties ctx,
			String conceptValue,
			int payrollId,
			int partnerId,
			Timestamp breakDate,
			String trxName) {
		MHRConcept concept = MHRConcept.getByValue(ctx, conceptValue, trxName);
		if (concept == null)
			return 0.0;
		//
		// Detect field name
		if (!MHRConcept.COLUMNTYPE_Quantity.equals(concept.getColumnType())
				&& !MHRConcept.COLUMNTYPE_Amount.equals(concept.getColumnType())) {
			return 0.0;
		}
		//	
		MHRMovement lastMovement = getLastMovement(ctx, conceptValue, payrollId, partnerId, breakDate, trxName);
		if(lastMovement == null) {
			return 0.0;
		}
		//	
		if(MHRConcept.COLUMNTYPE_Quantity.equals(concept.getColumnType())) {
			if(lastMovement.getQty() != null) {
				return lastMovement.getQty().doubleValue();
			}
		} else if(MHRConcept.COLUMNTYPE_Amount.equals(concept.getColumnType())) {
			if(lastMovement.getAmount() != null) {
				return lastMovement.getAmount().doubleValue();
			}
		}
		//	Default
		return 0.0;
	}
	
	/**
	 * Get a date from the last concept
	 * @param ctx
	 * @param conceptValue
	 * @param payrollId
	 * @param partnerId
	 * @param breakDate
	 * @return
	 */
	public static Timestamp getLastConceptDate(
			Properties ctx,
			String conceptValue,
			int payrollId,
			int partnerId,
			Timestamp breakDate,
			String trxName) {
		MHRConcept concept = MHRConcept.getByValue(ctx, conceptValue , trxName);
		if (concept == null)
			return null;
		//
		// Detect field name
		if (!MHRConcept.COLUMNTYPE_Date.equals(concept.getColumnType())) {
			return null;
		}
		//	
		MHRMovement lastMovement = getLastMovement(ctx, conceptValue, payrollId, partnerId, breakDate, trxName);
		if(lastMovement == null) {
			return null;
		}
		//	Default
		return lastMovement.getServiceDate();
	}

	/**
	 *  Helper Method : Concept AVG by range from-to in periods from a different payroll
	 *  periods with values 0 -1 1, etc. actual previous one period, next period
	 *  0 corresponds to actual period
	 * @param conceptValue
	 * @param payrollId ID is the ID of the payroll.
	 * @param partnerId business partner for search
	 * @param periodId period ID
	 * @param periodFrom
	 * @param periodTo the search is done by the period value, it helps to search from previous years
	 * @param trxName
	 */
	public static double getConceptAvg(
			Properties ctx,
			String conceptValue,
			int payrollId,
			int partnerId,
			int periodId,
			int periodFrom,
			int periodTo,
			String trxName) {
		return getConceptAvg(ctx,conceptValue,payrollId,partnerId,periodId ,periodFrom,periodTo,true,trxName);
	}

	/**
	 *  Helper Method : Concept AVG by range from-to in periods from a different payroll
	 *  periods with values 0 -1 1, etc. actual previous one period, next period
	 *  0 corresponds to actual period
	 * @param conceptValue
	 * @param payrollId ID is the ID of the payroll.
	 * @param partnerId business partner for search
	 * @param periodId period ID
	 * @param periodFrom
	 * @param periodTo the search is done by the period value, it helps to search from previous years
	 * @param processed
	 * @param trxName
	 */
	public static double getConceptAvg(
			Properties ctx,
			String conceptValue,
			int payrollId,
			int partnerId,
			int periodId,
			int periodFrom,
			int periodTo,
			boolean includeInProcess,
			String trxName) {
		MHRConcept concept = MHRConcept.getByValue(ctx, conceptValue, trxName);
		if (concept == null)
			return 0.0;
		//
		// Detect field name
		final String fieldName;
		if (MHRConcept.COLUMNTYPE_Quantity.equals(concept.getColumnType())) {
			fieldName = MHRMovement.COLUMNNAME_Qty;
		} else if (MHRConcept.COLUMNTYPE_Amount.equals(concept.getColumnType())) {
			fieldName = MHRMovement.COLUMNNAME_Amount;
		} else {
			return 0; // TODO: throw exception?
		}
		//
		MHRPeriod period = MHRPeriod.getById(ctx, periodId, trxName);
		ArrayList<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer();
		//check concept
		whereClause.append(MHRMovement.COLUMNNAME_HR_Concept_ID + "=?");
		params.add(concept.get_ID());
		//check partner
		whereClause.append(" AND " + MHRMovement.COLUMNNAME_C_BPartner_ID  + "=?");
		params.add(partnerId);
		//
		//check process and payroll
		whereClause.append(" AND EXISTS (SELECT 1 FROM HR_Process p"
				+" INNER JOIN HR_Period pr ON (pr.HR_Period_id=p.HR_Period_ID)"
				+" WHERE HR_Movement.HR_Process_ID = p.HR_Process_ID"
				+" AND " + (includeInProcess? "p.DocStatus IN('DR', 'IP', 'CO', 'CL')" : "p.DocStatus IN('CO', 'CL')")
				+" AND p.HR_Payroll_ID=?");

		params.add(payrollId);
		if (periodFrom < 0) {
			whereClause.append(" AND pr.PeriodNo >= ?");
			params.add(period.getPeriodNo() +periodFrom);
		}
		if (periodTo > 0) {
			whereClause.append(" AND pr.PeriodNo <= ?");
			params.add(period.getPeriodNo() +periodTo);
		}
		whereClause.append(")");
		//
		StringBuffer sql = new StringBuffer("SELECT COALESCE(AVG(")
					.append(fieldName).append("),0) FROM ").append(MHRMovement.Table_Name)
					.append(" WHERE ").append(whereClause);
		BigDecimal value = DB.getSQLValueBDEx(null, sql.toString(), params);
		return value.doubleValue();

	} // getConcept
	
	/**
	 * Get Movement by payroll process
	 * @param process
	 * @return
	 */
	public static List<MHRMovement> findByProcess(MHRProcess process)
	{
		final String whereClause = MHRMovement.COLUMNNAME_HR_Process_ID+"=?";
		//
		return new Query (process.getCtx(), I_HR_Movement.Table_Name, whereClause , process.get_TrxName())
				.setClient_ID()
				.setParameters(process.getHR_Process_ID())
				.list();
	}

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param movementId
	 *	@param trxName
	 */
	public MHRMovement (Properties ctx, int movementId, String trxName)
	{
		super (ctx, movementId, trxName);
	}

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName
	 */
	public MHRMovement (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}
	
	/**
	 * 	Import Constructor
	 *	@param importPayrollMovement import
	 */
	public MHRMovement (X_I_HR_Movement importPayrollMovement)
	{
		this (importPayrollMovement.getCtx(), 0, importPayrollMovement.get_TrxName());
		MHRConcept concept = new MHRConcept(getCtx(), importPayrollMovement.getHR_Concept_ID(), get_TrxName());
		MHREmployee employee = Optional.ofNullable(MHREmployee.getActiveEmployee(
				getCtx(),
				importPayrollMovement.getC_BPartner_ID(),
				get_TrxName()))
				.orElseThrow(() -> new AdempiereException("@HR_Employee_ID@ @NotFound@"));
		setAD_Org_ID(employee.getAD_Org_ID());
		setUpdatedBy(importPayrollMovement.getUpdatedBy());
		setHR_Process_ID(importPayrollMovement.getHR_Process_ID());
		setC_BPartner_ID(importPayrollMovement.getC_BPartner_ID());
		setHR_Concept_ID(importPayrollMovement.getHR_Concept_ID());
		setHR_Concept_Category_ID(concept.getHR_Concept_Category_ID());
		setHR_Concept_Type_ID(concept.getHR_Concept_Type_ID());
		setDescription(importPayrollMovement.getDescription());
		setHR_Job_ID(employee.getHR_Job_ID());
		setHR_Department_ID(employee.getHR_Department_ID());
		setC_Activity_ID(employee.getC_Activity_ID());
		setValidFrom(importPayrollMovement.getValidFrom());
        setValidTo(importPayrollMovement.getValidTo());
		setIsManual(concept.isManual());
		setIsPrinted(concept.isPrinted());
		setAmount(null);
		setQty(null);
		setServiceDate(null);
		setTextMsg(null);
		if (concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Quantity))			// Concept Type
			setQty(importPayrollMovement.getQty());
		else if (concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Amount))
			setAmount(importPayrollMovement.getAmount());
		else if (concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Date))
			setServiceDate(importPayrollMovement.getServiceDate());
		else if (concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Text))
			setTextMsg(importPayrollMovement.getTextMsg());
	}

	public MHRMovement (MHRProcess process, I_HR_Concept concept)
	{
		this(process.getCtx(), 0, process.get_TrxName());
		// Process
		this.setSeqNo(concept.getSeqNo());
		this.setHR_Process_ID(process.getHR_Process_ID());
		this.setHR_Payroll_ID(process.getHR_Payroll_ID());
		// Concept
		this.setHR_Concept_Category_ID(concept.getHR_Concept_Category_ID());
		this.setHR_Concept_Type_ID(concept.getHR_Concept_Type_ID());
		this.setHR_Concept_ID(concept.getHR_Concept_ID());
	}
	
	public void addAmount(BigDecimal amount)
	{
		setAmount(getAmount().add(amount == null ? Env.ZERO : amount));
	}
	
	public void addQty(BigDecimal qty)
	{
		setQty(getAmount().add(qty == null ? Env.ZERO : qty));
	}
	
	/**
	 * @return true if all movement values (Amount, Qty, Text) are empty 
	 */
	public boolean isEmpty()
	{
		return getQty().signum() == 0
				&& getAmount().signum() == 0
				&& Util.isEmpty(getTextMsg())
				&& getServiceDate() == null;		
	}
	
	/**
	 * According to the concept type, it's saved in the column specified for the purpose
	 * @param value
	 */
	public void setColumnValue(Object value) {
		if(value == null) {
			return;
		}
		try {
			//	Get column Type from concept
			MHRConcept concept = MHRConcept.getById(getCtx(), getHR_Concept_ID(), get_TrxName());
			if(concept == null) {
				throw new AdempiereException("@HR_Concept_ID@ @NotFound@");
			}
			//	
			final String columnType = concept.getColumnType();
			int currencyPrecision = MCurrency.getStdPrecision(getCtx(), Env.getContextAsInt(p_ctx, "#C_Currency_ID"));
			Optional<Integer> conceptStandardPrecisionOptional = Optional.ofNullable((Integer)concept.get_Value("StdPrecision"));
			
			if(MHRConcept.COLUMNTYPE_Text.equals(columnType)) {
				setTextMsg(value.toString().trim());
			} else if(MHRConcept.COLUMNTYPE_Date.equals(columnType)) {
				if (value instanceof Timestamp) {
					setServiceDate((Timestamp)value);
				} else {
					setServiceDate(Timestamp.valueOf(value.toString().trim().substring(0, 10)+ " 00:00:00.0"));	
				}
			} else if (MHRConcept.COLUMNTYPE_Quantity.equals(columnType) || MHRConcept.COLUMNTYPE_Amount.equals(columnType)) {
				double doubleValue = 0;
				//	Validate Double
				try {
					doubleValue = Double.parseDouble(value.toString());
				} catch (Exception e) {
					String businessPartnerName = "";
					if(getC_BPartner_ID() != 0) {
						MBPartner businessPartner = (MBPartner) getC_BPartner();
						businessPartnerName = businessPartner.getValue() + " - " + businessPartner.getName();
					}
					throw new AdempiereException(businessPartnerName + " [" + concept.getValue() + " @Error@ " + value + "]");
				}
				//	Set from type
				if(MHRConcept.COLUMNTYPE_Amount.equals(columnType)) {
					BigDecimal amount = new BigDecimal(doubleValue)
							.setScale(conceptStandardPrecisionOptional.orElseGet(() -> currencyPrecision),BigDecimal.ROUND_HALF_UP);
					setAmount(amount);
					setQty(Env.ZERO);
				} else {
					BigDecimal qty = new BigDecimal(doubleValue);
					setQty(qty);
					setAmount(Env.ZERO);
				}
			} else {
				throw new AdempiereException("@NotSupported@ @ColumnType@ - "+columnType);
			}
		}
		catch (Exception e) 
		{
			throw new AdempiereException("@Script Error@ " + e.getLocalizedMessage());
		}
	}
	
	@Override
	protected boolean beforeSave(boolean newRecord)
	{
		MHREmployee employee  = MHREmployee.getActiveEmployee(Env.getCtx(), getC_BPartner_ID(), get_TrxName());
		if (employee != null) {
			setAD_Org_ID(employee.getAD_Org_ID());
		}
		return true;
	}

	/**
	 * Set Employee info
	 * @param employee
	 */
	public void setEmployee(MHREmployee employee)
	{
		setAD_Org_ID(employee.getAD_Org_ID());
		setHR_Department_ID(employee.getHR_Department_ID());
		setHR_Job_ID(employee.getHR_Job_ID());
		setC_Activity_ID(employee.getC_Activity_ID() > 0 ?  employee.getC_Activity_ID() : employee.getHR_Department().getC_Activity_ID());
		setHR_Employee_ID(employee.getHR_Employee_ID());
		setHR_EmployeeType_ID(employee.getHR_EmployeeType_ID());
		setHR_SkillType_ID(employee.getHR_SkillType_ID());
		setHR_Payroll_ID(employee.getHR_Payroll_ID());
		setC_Project_ID(employee.getC_Project_ID());
		if (employee.getHR_Payroll_ID() > 0)
			setHR_Contract_ID(employee.getHR_Payroll().getHR_Contract_ID());
	}
}	//	HRMovement