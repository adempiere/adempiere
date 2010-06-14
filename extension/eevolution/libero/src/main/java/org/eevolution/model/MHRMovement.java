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
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_C_BP_BankAccount;
import org.compiere.model.MCurrency;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.Util;

/**
 *	Payroll Concept for HRayroll Module
 *	
 *  @author Oscar Gómez Islas
 *  @author Teo Sarca, www.arhipac.ro
 */
public class MHRMovement extends X_HR_Movement
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1587073469278066854L;

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param HR_Concept_ID
	 *	@param trxName
	 */
	public MHRMovement (Properties ctx, int HR_Movement_ID, String trxName)
	{
		super (ctx, HR_Movement_ID, trxName);
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
	
	public MHRMovement (MHRProcess proc, I_HR_Concept concept)
	{
		this(proc.getCtx(), 0, proc.get_TrxName());
		// Process
		this.setHR_Process_ID(proc.getHR_Process_ID());
		// Concept
		this.setHR_Concept_Category_ID(concept.getHR_Concept_Category_ID());
		this.setHR_Concept_ID(concept.getHR_Concept_ID());
		this.setColumnType(concept.getColumnType());
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
				&& Util.isEmpty(getTextMsg());		
	}
	
	/**
	 * According to the concept type, it's saved in the column specified for the purpose
	 * @param columnType column type (see MHRConcept.COLUMNTYPE_*)
	 * @param value
	 */
	public void setColumnValue(Object value)
	{
		try
		{
			final String columnType = getColumnType();
			if (MHRConcept.COLUMNTYPE_Quantity.equals(columnType))
			{
				BigDecimal qty = new BigDecimal(value.toString()); 
				setQty(qty);
				setAmount(Env.ZERO);
			} 
			else if(MHRConcept.COLUMNTYPE_Amount.equals(columnType))
			{
					int precision = MCurrency.getStdPrecision(getCtx(), Env.getContextAsInt(p_ctx, "#C_Currency_ID"));				
					BigDecimal amount = new BigDecimal(value.toString()).setScale(precision, BigDecimal.ROUND_HALF_UP);
				setAmount(amount);
				setQty(Env.ZERO);
			} 
			else if(MHRConcept.COLUMNTYPE_Text.equals(columnType))
			{
				setTextMsg(value.toString().trim());
			}
			else if(MHRConcept.COLUMNTYPE_Date.equals(columnType))
			{
				if (value instanceof Timestamp)
				{
					setServiceDate((Timestamp)value);
				}
				else
				{
					setServiceDate(Timestamp.valueOf(value.toString().trim().substring(0, 10)+ " 00:00:00.0"));	
				}
			}
			else
			{
				throw new AdempiereException("@NotSupported@ @ColumnType@ - "+columnType);
			}
		}
		catch (Exception e) 
		{
			throw new AdempiereException("@Script Error@");
		}
	}
	
	@Override
	protected boolean beforeSave(boolean newRecord)
	{
		MHREmployee employee  = MHREmployee.getActiveEmployee(Env.getCtx(), getC_BPartner_ID(), get_TrxName());
		if (employee != null) {
			setAD_Org_ID(employee.getAD_Org_ID());
		}
		setC_BP_Group_ID(getC_BPartner().getC_BP_Group_ID());
		// BankAccount
		int C_BP_BankAccount_ID = new Query(getCtx(), I_C_BP_BankAccount.Table_Name, COLUMNNAME_C_BPartner_ID+"=?", get_TrxName())
			.setOnlyActiveRecords(true)
			.setParameters(getC_BPartner_ID())
			.setOrderBy(I_C_BP_BankAccount.COLUMNNAME_C_BP_BankAccount_ID+" DESC") // just in case...
			.firstId();
		
		if(C_BP_BankAccount_ID > 0)
			setC_BP_BankAccount_ID(C_BP_BankAccount_ID);
			
		setAccountSign(getHR_Concept().getAccountSign());
		return true;
	}       
}	//	HRMovement