/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.adempiere.core.domains.models.I_C_CommissionDetail;
import org.adempiere.core.domains.models.X_C_CommissionAmt;
import org.compiere.util.Env;

/**
 *	Commission Run Amounts
 *	
 *  @author Jorg Janke
 *  @version $Id: MCommissionAmt.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 */
public class MCommissionAmt extends X_C_CommissionAmt
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1747802539808391638L;

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_CommissionAmt_ID id
	 *	@param trxName transaction
	 */
	public MCommissionAmt(Properties ctx, int C_CommissionAmt_ID, String trxName)
	{
		super(ctx, C_CommissionAmt_ID, trxName);
		if (C_CommissionAmt_ID == 0)
		{
		//	setC_CommissionRun_ID (0);
		//	setC_CommissionLine_ID (0);
			setActualQty (Env.ZERO);
			setCommissionAmt (Env.ZERO);
			setConvertedAmt (Env.ZERO);
		}
	}	//	MCommissionAmt

	/**
	 * 	Parent Constructor
	 *	@param run parent
	 *	@param C_CommissionLine_ID line
	 */
	public MCommissionAmt (MCommissionRun run, int C_CommissionLine_ID)
	{
		this (run.getCtx(), 0, run.get_TrxName());
		setClientOrg (run);
		setC_CommissionRun_ID (run.getC_CommissionRun_ID());
		setC_CommissionLine_ID (C_CommissionLine_ID);
	}	//	MCommissionAmt

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MCommissionAmt(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MCommissionAmt

	/**
	 * 	Get Details
	 *	@return array of details
	 */
	public MCommissionDetail[] getDetails()
	{
		final String whereClause = I_C_CommissionDetail.COLUMNNAME_C_CommissionAmt_ID+"=?";
		List<MCommissionDetail> list = new Query(getCtx(),I_C_CommissionDetail.Table_Name, whereClause, get_TrxName())
			.setParameters(getC_CommissionAmt_ID())
			.list();
		//	Convert
		MCommissionDetail[] retValue = new MCommissionDetail[list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getDetails

	/**
	 * 	Calculate Commission
	 */
	public void updateCommissionAmount() {
		BigDecimal totalConvertedAmt = Env.ZERO;
		BigDecimal totalActualQty = Env.ZERO;
		BigDecimal totalCommissionAmt = Env.ZERO;
		//	Iterate it
		for (MCommissionDetail detail : getDetails()) {
			//	Set totals
			totalConvertedAmt = totalConvertedAmt.add(detail.getConvertedAmt());
			totalActualQty = totalActualQty.add(detail.getActualQty());
			totalCommissionAmt = totalCommissionAmt.add(detail.getCommissionAmt());
		}
		//	Set Totals to model
		setConvertedAmt(totalConvertedAmt);
		setActualQty(totalActualQty);
		setCommissionAmt(totalCommissionAmt);
	}	//	calculateCommission
	
	
	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (!newRecord)
			updateRunHeader();
		return success;
	}	//	afterSave
	
	/**
	 * 	After Delete
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterDelete (boolean success)
	{
		if (success)
			updateRunHeader();
		return success;
	}	//	afterDelete
	
	/**
	 * 	Update Amt Header
	 */
	private void updateRunHeader()
	{
		MCommissionRun run = new MCommissionRun(getCtx(), getC_CommissionRun_ID(),get_TrxName());
		run.updateFromAmt();
		run.saveEx();
	}	//	updateRunHeader

}	//	MCommissionAmt
