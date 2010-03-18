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
import org.compiere.util.CLogger;

/**
 *	Currency Account Model 
 *	
 *  @author Jorg Janke
 *  @version $Id: MCurrencyAcct.java,v 1.3 2006/07/30 00:58:38 jjanke Exp $
 *  @author red1 - FR: [ 2214883 ] Remove SQL code and Replace for Query
 */
public class MCurrencyAcct extends X_C_Currency_Acct
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2256532431388781618L;
	/** Static Logger					*/
	private static CLogger s_log = CLogger.getCLogger(MCurrencyAcct.class);
	
	/**
	 * 	Get Currency Account for Currency
	 *	@param as accounting schema default
	 *	@param C_Currency_ID currency
	 *	@return Currency Account or null
	 */
	public static MCurrencyAcct get (MAcctSchemaDefault as, int C_Currency_ID)
	{
		final String whereClause = I_C_Currency_Acct.COLUMNNAME_C_AcctSchema_ID+"=? AND "+I_C_Currency_Acct.COLUMNNAME_C_Currency_ID+"=?";	
		MCurrencyAcct retValue = new Query(as.getCtx(),I_C_Currency_Acct.Table_Name,whereClause,null)
		.setParameters(as.getC_AcctSchema_ID(),C_Currency_ID)
		.first();
		return retValue;
	}	//	get
	
	
	/**************************************************************************
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MCurrencyAcct(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MCurrencyAcct

}	//	MCurrencyAcct
