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
import java.util.List;
import java.util.Properties;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *	AP Payment Selection
 *	
 *  @author Jorg Janke
 *  @version $Id: MPaySelection.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 */
public class MPaySelection extends X_C_PaySelection
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6521282913549455131L;

	/**
	 * 	Default Constructor
	 *	@param ctx context
	 *	@param C_PaySelection_ID id
	 *	@param trxName transaction
	 */
	public MPaySelection (Properties ctx, int C_PaySelection_ID, String trxName)
	{
		super(ctx, C_PaySelection_ID, trxName);
		if (C_PaySelection_ID == 0)
		{
		//	setC_BankAccount_ID (0);
		//	setName (null);	// @#Date@
		//	setPayDate (new Timestamp(System.currentTimeMillis()));	// @#Date@
			setTotalAmt (Env.ZERO);
			setIsApproved (false);
			setProcessed (false);
			setProcessing (false);
		}
	}	//	MPaySelection

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MPaySelection(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MPaySelection

	/**	Lines						*/
	private MPaySelectionLine[]	m_lines = null;
	/**	Currency of Bank Account	*/
	private int					m_C_Currency_ID = 0;
	
	/**
	 * 	Get Lines
	 *	@param requery requery
	 *	@return lines
	 */
	public MPaySelectionLine[] getLines(boolean requery)
	{
		if (m_lines != null && !requery) {
			set_TrxName(m_lines, get_TrxName());
			return m_lines;
		}
		//FR: [ 2214883 ] Remove SQL code and Replace for Query - red1
		String whereClause = "C_PaySelection_ID=?";
		List <MPaySelectionLine> list = new Query(getCtx(), MPaySelectionLine.Table_Name, whereClause, get_TrxName())
			.setParameters(getC_PaySelection_ID())
			.setOrderBy("Line")
			.list()
		;
		//
		m_lines = new MPaySelectionLine[list.size ()];
		list.toArray (m_lines);
		return m_lines;
	}	//	getLines
	
	/**
	 * 	Get Currency of Bank Account
	 *	@return C_Currency_ID
	 */
	public int getC_Currency_ID()
	{
		if (m_C_Currency_ID == 0)
		{
			String sql = "SELECT C_Currency_ID FROM C_BankAccount " 
				+ "WHERE C_BankAccount_ID=?";
			m_C_Currency_ID = DB.getSQLValue(null, sql, getC_BankAccount_ID());
		}
		return m_C_Currency_ID;
	}	//	getC_Currency_ID
	
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("MPaySelection[");
		sb.append(get_ID()).append(",").append(getName())
			.append("]");
		return sb.toString();
	}	//	toString

	
	
}	//	MPaySelection
