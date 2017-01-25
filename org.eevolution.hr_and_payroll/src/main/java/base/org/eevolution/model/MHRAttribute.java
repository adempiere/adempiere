/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2008 SC ARHIPAC SERVICE SRL. All Rights Reserved.            *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.eevolution.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.Util;

/**
 * HR Attribute Model
 * @author Cristina Ghita, www.arhipac.ro
 *
 */
public class MHRAttribute extends X_HR_Attribute
{
	private static final long serialVersionUID = 3783311896401143394L;


	/**
	 * Get Attribute By Concept value , partner Id , reference and description
	 * @param ctx
	 * @param conceptValue
	 * @param partnerId
	 * @param referenceNo
	 * @param description
	 * @param trxName
	 * @return
	 */
	public static Optional<MHRAttribute> getByConceptValueAndPartnerId(Properties ctx , String conceptValue, Integer partnerId , String referenceNo, String description, String trxName)
	{
		if (partnerId == null)
			return Optional.empty();

		StringBuilder whereClause = new StringBuilder();
		List<Object> parameters = new ArrayList<Object>();
		whereClause.append("EXISTS (SELECT 1 FROM HR_Concept c WHERE c.IsEmployee=? AND c.HR_Concept_ID=HR_Attribute.HR_Concept_ID AND c.Value=?)");
		parameters.add("Y");
		parameters.add(conceptValue);
		if (partnerId != null && partnerId > 0)
		{
			whereClause.append(" AND ").append(MHRAttribute.COLUMNNAME_C_BPartner_ID).append("=?");
			parameters.add(partnerId);
		}
		if (description != null && description.length() > 0)
		{
			whereClause.append(" AND ").append(MHRAttribute.COLUMNNAME_Description).append("=?");
			parameters.add(description);
		}
		if (referenceNo != null && referenceNo.length() > 0)
		{
			whereClause.append(" AND ").append(MHRAttribute.COLUMNNAME_ReferenceNo).append("=?");
			parameters.add(referenceNo);
		}
		MHRAttribute attribute = new Query(ctx , MHRAttribute.Table_Name , whereClause.toString(), trxName)
				.setClient_ID()
				.setParameters(parameters)
				.first();
		return Optional.ofNullable(attribute);
	}
	/**
	 * Find by concept value , partner Id , reference no and description
	 * @param ctx
	 * @param conceptValue
	 * @param partnerId
	 * @param referenceNo
	 * @param description
	 * @param trxName
	 * @return
	 */
	public static List<MHRAttribute> findByConceptValueAndPartnerId(Properties ctx , String conceptValue, Integer partnerId , String referenceNo , String description , String trxName)
	{
		List<MHRAttribute> attributes = new ArrayList<MHRAttribute>();
		if (partnerId == null)
			return attributes;

		StringBuilder whereClause = new StringBuilder();
		List<Object> parameters = new ArrayList<Object>();
		whereClause.append("EXISTS (SELECT 1 FROM HR_Concept c WHERE c.IsEmployee=? AND c.HR_Concept_ID=HR_Attribute.HR_Concept_ID AND c.Value=?)");
		parameters.add("Y");
		parameters.add(conceptValue);
		if (partnerId != null && partnerId > 0)
		{
			whereClause.append(" AND ").append(MHRAttribute.COLUMNNAME_C_BPartner_ID).append("=?");
			parameters.add(partnerId);
		}
		if (description != null && description.length() > 0)
		{
			whereClause.append(" AND ").append(MHRAttribute.COLUMNNAME_Description).append("=?");
			parameters.add(description);
		}
		if (referenceNo != null && referenceNo.length() > 0)
		{
			whereClause.append(" AND ").append(MHRAttribute.COLUMNNAME_ReferenceNo).append("=?");
			parameters.add(referenceNo);
		}
		attributes = new Query(ctx , MHRAttribute.Table_Name , whereClause.toString(), trxName)
				.setClient_ID()
				.setParameters(parameters)
				.list();
		return attributes;
	}

	/**
	 * Get first attribute by concept , partner id , payroll id , reference no , description and valid fro
	 * @param concept
	 * @param partnerId
	 * @param payrollId
	 * @param referenceNo
	 * @param description
	 *@param validFrom  @return
	 */
	public static MHRAttribute getByConceptAndPartnerId(MHRConcept concept, Integer partnerId, int payrollId, String referenceNo, String description, Timestamp validFrom) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer();
		whereClause.append(MHRAttribute.COLUMNNAME_HR_Concept_ID).append("=?");
		params.add(concept.getHR_Concept_ID());
		if (partnerId > 0) {
			whereClause.append(" AND ").append(MHRAttribute.COLUMNNAME_C_BPartner_ID).append("=? ");
			params.add(partnerId);
		}

		if (referenceNo != null && referenceNo.length() > 0) {
			whereClause.append(" AND ").append(MHRAttribute.COLUMNNAME_ReferenceNo).append("=? ");
			params.add(referenceNo);
		}

		if (description != null && description.length() > 0) {
			whereClause.append(" AND ").append(MHRAttribute.COLUMNNAME_Description).append("=? ");
			params.add(description);
		}

		if (payrollId > 0) {
			whereClause.append(" AND ").append(MHRAttribute.COLUMNNAME_HR_Payroll_ID).append("? ");
			params.add(payrollId);
		}

		if (validFrom != null) {
			whereClause.append(" AND ").append(MHRAttribute.COLUMNNAME_ValidFrom).append("=? ");
			params.add(validFrom);
		}

		MHRAttribute attribute = new Query(concept.getCtx(), MHRAttribute.Table_Name, whereClause.toString(), concept.get_TrxName())
				.setParameters(params)
				.setOnlyActiveRecords(true)
				.setOrderBy(MHRAttribute.COLUMNNAME_ValidFrom + " DESC")
				.first();
		return attribute;
	}

	/**
	 * Get Employee Attribute
	 * @param concept
	 * @param employee
	 * @param payrollId
	 * @param dateFrom
	 * @param dateTo
	 * @return
	 */
	public static MHRAttribute getByConceptAndEmployee(MHRConcept concept , MHREmployee employee , int payrollId , Timestamp dateFrom, Timestamp dateTo)
	{
		List<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer();

		whereClause.append("? >= ValidFrom AND ( ? <= ValidTo OR ValidTo IS NULL)");
		params.add(dateFrom);
		params.add(dateTo);
		whereClause.append(" AND HR_Concept_ID = ? ");
		params.add(concept.getHR_Concept_ID());
		whereClause.append(" AND EXISTS (SELECT 1 FROM HR_Concept conc WHERE conc.HR_Concept_ID = HR_Attribute.HR_Concept_ID )");

		// Check the concept is within a valid range for the attribute
		if (concept.isEmployee() && employee != null) {
			whereClause.append(" AND C_BPartner_ID = ? AND (HR_Employee_ID = ? OR HR_Employee_ID IS NULL)");
			params.add(employee.getC_BPartner_ID());
			params.add(employee.get_ID());
		}
		else
			whereClause.append(" AND C_BPartner_ID IS NULL ");


		whereClause.append(" AND (HR_Payroll_ID = ? OR HR_Payroll_ID IS NULL)");
		params.add(payrollId);

		MHRAttribute attribute = new Query(concept.getCtx(), MHRAttribute.Table_Name, whereClause.toString(), concept.get_TrxName())
				.setParameters(params)
				.setOnlyActiveRecords(true)
				.setOrderBy(MHRAttribute.COLUMNNAME_ValidFrom + " DESC")
				.first();
		return attribute;
	}

	/**
	 * Get Attribute to Invoice
	 * @param ctx
	 * @param conceptId
	 * @param partnerId
	 * @param validFrom
	 * @param trxName
	 * @return
	 */
	public static MHRAttribute getByConceptIdAndPartnerId(Properties ctx, int conceptId, int partnerId, Timestamp validFrom, String trxName) {
		StringBuilder whereClause = new StringBuilder("(");
		whereClause.append(I_HR_Attribute.COLUMNNAME_C_BPartner_ID).append("=? OR ")
				.append(I_HR_Attribute.COLUMNNAME_C_BPartner_ID).append(" IS NULL) AND ");
		whereClause.append(I_HR_Attribute.COLUMNNAME_ValidFrom).append("<=? AND ")
				.append("EXISTS (SELECT 1 FROM HR_Concept c WHERE HR_Attribute.HR_Concept_ID = c.HR_Concept_ID AND c.HR_Concept_ID=?)");

		return new Query(ctx, I_HR_Attribute.Table_Name, whereClause.toString(), trxName)
				.setParameters(partnerId, validFrom, conceptId)
				.setOnlyActiveRecords(true)
				.setOrderBy(I_HR_Attribute.COLUMNNAME_ValidFrom + " DESC")
				.first();
	}

	/**
	 * @deprecated since 3.5.3a
	 * Get Concept by Value
	 * @param ctx
	 * @param conceptValue
	 * @param partnerId
	 * @param startDate
	 * @return attribute
	 */
	public static MHRAttribute getByConceptValueAndPartnerId(Properties ctx, String conceptValue, int partnerId, Timestamp startDate)
	{
		if (Util.isEmpty(conceptValue, true))
		{
			return null;
		}
		
		int clientId = Env.getAD_Client_ID(ctx);
		
		final String whereClause = COLUMNNAME_C_BPartner_ID+"=? AND AD_Client_ID IN (?,?) "
								+ " AND " + COLUMNNAME_ValidFrom +"<=?"
								+ " AND EXISTS (SELECT 1 FROM HR_Concept c WHERE HR_Attribute.HR_Concept_ID = c.HR_Concept_ID" 
								+ " AND c.Value=?)"; 
		MHRAttribute attribute = new Query(ctx, Table_Name, whereClause, null)
							.setParameters(new Object[]{partnerId, 0, clientId, startDate, conceptValue})
							.setOnlyActiveRecords(true)
							.setOrderBy(COLUMNNAME_ValidFrom + " DESC")
							.first();
		return attribute;
	}	
	
	/**
	 * Get Concept by Value
	 * @param ctx
	 * @param conceptValue
	 * @param partnerId
	 * @param startDate
	 * @param endDate
	 * @return attribute
	 */	
	public static MHRAttribute getByConceptValueAndPartnerId(Properties ctx, String conceptValue, int partnerId, Timestamp startDate, Timestamp endDate)
	{
		if (Util.isEmpty(conceptValue, true))
		{
			return null;
		}

		if (endDate == null)
		{
			return getByConceptValueAndPartnerId(ctx, conceptValue, partnerId, startDate);
		}
		else
		{			
			int clientId = Env.getAD_Client_ID(ctx);
			
			final String whereClause = COLUMNNAME_C_BPartner_ID+"=? AND AD_Client_ID IN (?,?) "
									+ " AND " + COLUMNNAME_ValidFrom +"<=? AND " + COLUMNNAME_ValidTo +">=?"
									+ " AND EXISTS (SELECT 1 FROM HR_Concept c WHERE HR_Attribute.HR_Concept_ID = c.HR_Concept_ID" 
									+ " AND c.Value=?)"; 
			MHRAttribute attribute = new Query(ctx, Table_Name, whereClause, null)
								.setParameters(new Object[]{partnerId, 0, clientId, startDate, endDate, conceptValue})
								.setOnlyActiveRecords(true)
								.setOrderBy(COLUMNNAME_ValidFrom + " DESC")
								.first();
			return attribute;
		}
	}	
	
	/**
	 * @param ctx
	 * @param HR_Attribute_ID
	 * @param trxName
	 */
	public MHRAttribute(Properties ctx, int HR_Attribute_ID, String trxName)
	{
		super(ctx, HR_Attribute_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MHRAttribute(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}

	/**
	 *
	 * @param importAttribute
	 */
	public MHRAttribute(X_I_HR_Attribute importAttribute)
	{
		super(importAttribute.getCtx() , 0 , importAttribute.get_TrxName());
		setAD_Org_ID(importAttribute.getAD_Org_ID());
		setHR_Employee_ID(importAttribute.getHR_Employee_ID());
		setC_BPartner_ID(importAttribute.getC_BPartner_ID());
		setC_Charge_ID(importAttribute.getC_Charge_ID());
		setHR_Concept_ID(importAttribute.getHR_Concept_ID());
		setDescription(importAttribute.getDescription());
		setIsPrinted(importAttribute.isPrinted());
	}

	@Override
	public I_HR_Concept getHR_Concept()
	{
		return MHRConcept.get(getCtx(), getHR_Concept_ID());
	}

	/**************************************************************************
	 * 	Before Save
	 *	@param newRecord new
	 *	@return save
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		if (getHR_Concept().isEmployee() && getC_BPartner_ID() <= 0)
			throw new AdempiereException("@HR_Employee_ID@ @NotFound@");
		else if (!getHR_Concept().isEmployee())
			setHR_Employee_ID(-1);

		return true;
	}
}
