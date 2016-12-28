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
	 * Get Attribute to Invoice
	 * @param ctx
	 * @param partnerId
	 * @param conceptId
	 * @param validFrom
	 * @param trxName
	 * @return
	 */
	public static MHRAttribute getAttributeToInvoice(Properties ctx,int partnerId, int conceptId , Timestamp validFrom,  String trxName) {
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
	public static MHRAttribute forValue(Properties ctx, String conceptValue, int partnerId, Timestamp startDate)
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
	public static MHRAttribute forValue(Properties ctx, String conceptValue, int partnerId, Timestamp startDate, Timestamp endDate)
	{
		if (Util.isEmpty(conceptValue, true))
		{
			return null;
		}

		if (endDate == null)
		{
			return forValue(ctx, conceptValue, partnerId, startDate);
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
		if (getHR_Concept().isEmployee() && getHR_Employee_ID() <= 0)
			throw new AdempiereException("@HR_Employee_ID@ @NotFound@");
		else if (!getHR_Concept().isEmployee())
			setHR_Employee_ID(-1);

		return true;
	}
}
