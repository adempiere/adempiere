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

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.core.domains.models.I_C_Charge_Acct;
import org.adempiere.core.domains.models.X_C_Charge_Acct;
import org.compiere.util.CLogger;

/**
 *	Charge Account Model 
 */
public class MChargeAcct extends X_C_Charge_Acct
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2256532431388781618L;
	/** Static Logger					*/
	private static CLogger s_log = CLogger.getCLogger(MChargeAcct.class);
	
	/**
	 * 	Get Charge Account for Charge
	 *	@param as accounting schema
	 *	@param C_Charge_ID Charge
	 *	@return Charge Account or null
	 */
	public static MChargeAcct get (MAcctSchema as, int C_Charge_ID, String trx)
	{
		final String whereClause = I_C_Charge_Acct.COLUMNNAME_C_AcctSchema_ID+"=? AND "+I_C_Charge_Acct.COLUMNNAME_C_Charge_ID+"=?";	
		MChargeAcct retValue = new Query(as.getCtx(),I_C_Charge_Acct.Table_Name,whereClause,trx)
		.setParameters(as.getC_AcctSchema_ID(),C_Charge_ID)
		.first();
		return retValue;
	}	//	get
	
	
	/**************************************************************************
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MChargeAcct(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MChargeAcct


	public MChargeAcct(Properties ctx, int C_Charge_Acct_ID, String trxName) {
		super(ctx, C_Charge_Acct_ID, trxName);
	}
	
}	//	MChargeAcct
