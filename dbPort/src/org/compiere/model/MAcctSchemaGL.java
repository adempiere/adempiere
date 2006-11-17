/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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

import java.sql.*;
import java.util.*;

import java.util.logging.*;
import org.compiere.util.*;

/**
 *	Accounting Schema GL info
 *	
 *  @author Jorg Janke
 *  @version $Id: MAcctSchemaGL.java,v 1.3 2006/07/30 00:58:18 jjanke Exp $
 */
public class MAcctSchemaGL extends X_C_AcctSchema_GL
{
	/**
	 * 	Get Accounting Schema GL Info
	 *	@param ctx context
	 *	@param C_AcctSchema_ID id
	 *	@return defaults
	 */
	public static MAcctSchemaGL get (Properties ctx, int C_AcctSchema_ID)
	{
		MAcctSchemaGL retValue = null;
		String sql = "SELECT * FROM C_AcctSchema_GL WHERE C_AcctSchema_ID=?";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, C_AcctSchema_ID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				retValue = new MAcctSchemaGL (ctx, rs, null);
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		return retValue;
	}	//	get
	
	/**	Logger							*/
	protected static CLogger			s_log = CLogger.getCLogger(MAcctSchemaGL.class);
	
	
	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param C_AcctSchema_ID AcctSchema 
	 *	@param trxName transaction
	 */
	public MAcctSchemaGL (Properties ctx, int C_AcctSchema_ID, String trxName)
	{
		super(ctx, C_AcctSchema_ID, trxName);
		if (C_AcctSchema_ID == 0)
		{
			setUseCurrencyBalancing (false);
			setUseSuspenseBalancing (false);
			setUseSuspenseError (false);
		}
	}	//	MAcctSchemaGL

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MAcctSchemaGL (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MAcctSchemaGL

	/**
	 * 	Get Acct Info list 
	 *	@return list
	 */
	public ArrayList<KeyNamePair> getAcctInfo()
	{
		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
		for (int i = 0; i < get_ColumnCount(); i++)
		{
			String columnName = get_ColumnName(i);
			if (columnName.endsWith("Acct"))
			{
				int id = ((Integer)get_Value(i));
				list.add(new KeyNamePair (id, columnName));
			}
		}
		return list;
	}	//	getAcctInfo
	
	/**
	 * 	Set Value (don't use)
	 *	@param columnName column name
	 *	@param value value
	 *	@return true if set
	 */
	public boolean setValue (String columnName, Integer value)
	{
		return super.set_Value (columnName, value);
	}	//	setValue
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		if (getAD_Org_ID() != 0)
			setAD_Org_ID(0);
		return true;
	}	//	beforeSave

}	//	MAcctSchemaGL
