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
 *	System Element Model
 *	
 *  @author Jorg Janke
 *  @version $Id: M_Element.java,v 1.3 2006/07/30 00:58:37 jjanke Exp $
 */
public class M_Element extends X_AD_Element
{
	/**
	 * 	Get case sensitive Column Name
	 *	@param columnName case insentitive column name
	 *	@return case sensitive column name
	 */
	public static String getColumnName (String columnName)
	{
		if (columnName == null || columnName.length() == 0)
			return columnName;
		String retValue = columnName;
		String sql = "SELECT ColumnName FROM AD_Element WHERE UPPER(ColumnName)=?";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setString (1, columnName.toUpperCase());
			ResultSet rs = pstmt.executeQuery ();
			if (rs.next ())
			{
				retValue = rs.getString(1);
				if (rs.next())
					s_log.warning("Not unique: " + columnName 
						+ " -> " + retValue + " - " + rs.getString(1));
			}
			else
				s_log.warning("No found: " + columnName);
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			s_log.log (Level.SEVERE, columnName, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		return retValue;
	}	//	getColumnName

	/**
	 * 	Get Element
	 * 	@param ctx context
	 *	@param columnName case insentitive column name
	 *	@return case sensitive column name
	 */
	public static M_Element get (Properties ctx, String columnName)
	{
		if (columnName == null || columnName.length() == 0)
			return null;
		M_Element retValue = null;
		String sql = "SELECT * FROM AD_Element WHERE UPPER(ColumnName)=?";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setString (1, columnName.toUpperCase());
			ResultSet rs = pstmt.executeQuery ();
			if (rs.next ())
			{
				retValue = new M_Element (ctx, rs, null);
				if (rs.next())
					s_log.warning("Not unique: " + columnName 
						+ " -> " + retValue + " - " + rs.getString("ColumnName"));
			}
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			s_log.log (Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		return retValue;
	}	//	get

	
	/**	Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (M_Element.class);
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param AD_Element_ID element
	 *	@param trxName transaction
	 */
	public M_Element (Properties ctx, int AD_Element_ID, String trxName)
	{
		super (ctx, AD_Element_ID, trxName);
		if (AD_Element_ID == 0)
		{
		//	setColumnName (null);
		//	setEntityType (null);	// U
		//	setName (null);
		//	setPrintName (null);
		}	
	}	//	M_Element

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public M_Element (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	M_Element

	/**
	 * 	Minimum Constructor
	 *	@param ctx context
	 *	@param columnName column
	 *	@param EntityType entity type
	 *	@param trxName trx
	 */
	public M_Element (Properties ctx, String columnName, String EntityType,
		String trxName)
	{
		super(ctx, 0, trxName);
		setColumnName (columnName);
		setName (columnName);
		setPrintName (columnName);
		//
		setEntityType (EntityType);	// U
	}	//	M_Element

	
	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		//	Update Columns, Fields, Parameters, Print Info
		if (!newRecord)
		{
			//	Column
			StringBuffer sql = new StringBuffer("UPDATE AD_Column SET ColumnName=")
				.append(DB.TO_STRING(getColumnName()))
				.append(", Name=").append(DB.TO_STRING(getName()))
				.append(", Description=").append(DB.TO_STRING(getDescription()))
				.append(", Help=").append(DB.TO_STRING(getHelp()))
				.append(" WHERE AD_Element_ID=").append(get_ID());
			int no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("afterSave - Columns updated #" + no);
			
			//	Field
			sql = new StringBuffer("UPDATE AD_Field SET Name=")
				.append(DB.TO_STRING(getName()))
				.append(", Description=").append(DB.TO_STRING(getDescription()))
				.append(", Help=").append(DB.TO_STRING(getHelp()))
				.append(" WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=")
				.append(get_ID())
				.append(") AND IsCentrallyMaintained='Y'");
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("afterSave - Fields updated #" + no);
			
			//	Parameter 
			sql = new StringBuffer("UPDATE AD_Process_Para SET ColumnName=")
				.append(DB.TO_STRING(getColumnName()))
				.append(", Name=").append(DB.TO_STRING(getName()))
				.append(", Description=").append(DB.TO_STRING(getDescription()))
				.append(", Help=").append(DB.TO_STRING(getHelp()))
				.append(", AD_Element_ID=").append(get_ID())
				.append(" WHERE UPPER(ColumnName)=")
				.append(DB.TO_STRING(getColumnName().toUpperCase()))
				.append(" AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL");
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			sql = new StringBuffer("UPDATE AD_Process_Para SET ColumnName=")
				.append(DB.TO_STRING(getColumnName()))
				.append(", Name=").append(DB.TO_STRING(getName()))
				.append(", Description=").append(DB.TO_STRING(getDescription()))
				.append(", Help=").append(DB.TO_STRING(getHelp()))
				.append(" WHERE AD_Element_ID=").append(get_ID())
				.append(" AND IsCentrallyMaintained='Y'");
			no += DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("afterSave - Parameters updated #" + no);
			
			//	Print Info
			sql = new StringBuffer("UPDATE AD_PrintFormatItem pi SET PrintName=")
				.append(DB.TO_STRING(getPrintName()))
				.append(", Name=").append(DB.TO_STRING(getName()))
				.append(" WHERE AD_Client_ID=0")
				.append(" AND EXISTS (SELECT * FROM AD_Column c ")
					.append("WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=")
					.append(get_ID()).append(")");
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("afterSave - PrintFormatItem updated #" + no);
		}
		return success;
	}	//	afterSave
	
}	//	M_Element
