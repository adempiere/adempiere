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

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *	Payroll for HRayroll Module
 *	
 *  @author victor.perez@e-evolution.com, www.e-evolution.com
 *  
 */
public class MHRPaySelectionLine extends X_HR_PaySelectionLine
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3486055138810301789L;

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_PaySelectionLine_ID id
	 *	@param trxName transaction
	 */
	public MHRPaySelectionLine (Properties ctx, int HR_PaySelectionLine_ID, String trxName)
	{
		super(ctx, HR_PaySelectionLine_ID, trxName);
		if (HR_PaySelectionLine_ID == 0)
		{
			setIsSOTrx (false);
			setOpenAmt(Env.ZERO);
			setPayAmt (Env.ZERO);
			setDiscountAmt(Env.ZERO);
			setDifferenceAmt (Env.ZERO);
			setIsManual (false);
		}
	}	//	MHRPaySelectionLine

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MHRPaySelectionLine(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MHRPaySelectionLine

	/**
	 * 	Parent Constructor
	 *	@param ps parent
	 *	@param Line line
	 *	@param PaymentRule payment rule
	 */
	public MHRPaySelectionLine (MHRPaySelection ps, int Line, String PaymentRule)
	{
		this (ps.getCtx(), 0, ps.get_TrxName());
		setClientOrg(ps);
		setHR_PaySelection_ID(ps.getHR_PaySelection_ID());
		setLine(Line);
		setPaymentRule(PaymentRule);
	}	//	MHRPaySelectionLine
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		setDifferenceAmt(getOpenAmt().subtract(getPayAmt()).subtract(getDiscountAmt()));
		return true;
	}	//	beforeSave
	
	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		setHeader();
		return success;
	}	//	afterSave
	
	/**
	 * 	After Delete
	 *	@param success success
	 *	@return sucess
	 */
	protected boolean afterDelete (boolean success)
	{
		setHeader();
		return success;
	}	//	afterDelete
	
	/**
	 * 	Recalculate Header Sum
	 */
	private void setHeader()
	{
		//	Update Header
		String sql = "UPDATE HR_PaySelection ps "
			+ "SET TotalAmt = (SELECT COALESCE(SUM(psl.PayAmt),0) "
				+ "FROM HR_PaySelectionLine psl "
				+ "WHERE ps.HR_PaySelection_ID=psl.HR_PaySelection_ID AND psl.IsActive='Y') "
			+ "WHERE HR_PaySelection_ID=" + getHR_PaySelection_ID();
		DB.executeUpdate(sql, get_TrxName());
	}	//	setHeader
	
}	//	MPaySelectionLine
