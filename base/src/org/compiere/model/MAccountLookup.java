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

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import org.compiere.util.*;

/**
 *	Account Model Lookup - Maintains ValidCombination Info for Display & Edit - not cached
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: MAccountLookup.java,v 1.3 2006/07/30 00:54:54 jjanke Exp $
 */
public final class MAccountLookup extends Lookup implements Serializable
{
	/**
	 *	Constructor
	 *  @param ctx context
	 *  @param WindowNo window no
	 */
	public MAccountLookup (Properties ctx, int WindowNo)
	{
		super (DisplayType.TableDir, WindowNo);
		m_ctx = ctx;
	}	//	MAccountLookup

	/**	Context				*/
	private Properties  m_ctx;
	/** Account_ID			*/
	public int 		    C_ValidCombination_ID;
	private String		Combination;
	private String		Description;

	/**
	 *	Get Display for Value
	 *  @param value value
	 *  @return String
	 */
	public String getDisplay (Object value)
	{
		if (!containsKey (value))
			return "<" + value.toString() + ">";
		return toString();
	}	//	getDisplay

	/**
	 *	Get Object of Key Value
	 *  @param value value
	 *  @return Object or null
	 */
	public NamePair get (Object value)
	{
		if (value == null)
			return null;
		if (!containsKey (value))
			return null;
		return new KeyNamePair (C_ValidCombination_ID, toString());
	}	//	get

	/**
	 *  The Lookup contains the key
	 *  @param key key
	 *  @return true if exists
	 */
	public boolean containsKey (Object key)
	{
		int intValue = 0;
		if (key instanceof Integer)
			intValue = ((Integer)key).intValue();
		else if (key != null)
			intValue = Integer.parseInt(key.toString());
		//
		return load (intValue);
	}   //  containsKey

	/**
	 *  Get Description
	 *  @return Description
	 */
	public String getDescription()
	{
		return Description;
	}   //  getDescription

	/**
	 *	Return String representation
	 *  @return Combination
	 */
	public String toString()
	{
		if (C_ValidCombination_ID == 0)
			return "";
		return Combination;
	}	//	toString

	/**
	 *	Load C_ValidCombination with ID
	 *  @param ID C_ValidCombination_ID
	 *  @return true if found
	 */
	public boolean load (int ID)
	{
		if (ID == 0)						//	new
		{
			C_ValidCombination_ID = 0;
			Combination = "";
			Description = "";
			return true;
		}
		if (ID == C_ValidCombination_ID)	//	already loaded
			return true;

		String	SQL = "SELECT C_ValidCombination_ID, Combination, Description "
			+ "FROM C_ValidCombination WHERE C_ValidCombination_ID=?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			//	Prepare Statement
			pstmt = DB.prepareStatement(SQL, null);
			pstmt.setInt(1, ID);
			rs = pstmt.executeQuery();
			if (!rs.next())
			{
				return false;
			}
			//
			C_ValidCombination_ID = rs.getInt(1);
			Combination = rs.getString(2);
			Description = rs.getString(3);
		}
		catch (SQLException e)
		{
			return false;
		}
		finally
		{
			DB.close(rs, pstmt);
		}
		return true;
	}	//	load

	/**
	 *	Get underlying fully qualified Table.Column Name
	 *  @return ""
	 */
	public String getColumnName()
	{
		return "";
	}   //  getColumnName

	/**
	 *	Return data as sorted Array.
	 *  Used in Web Interface
	 *  @param mandatory mandatory
	 *  @param onlyValidated only valid
	 *  @param onlyActive only active
	 * 	@param temporary force load for temporary display
	 *  @return ArrayList with KeyNamePair
	 */
	public ArrayList<Object> getData (boolean mandatory, boolean onlyValidated, 
		boolean onlyActive, boolean temporary)
	{
		ArrayList<Object> list = new ArrayList<Object>();
		if (!mandatory)
			list.add(new KeyNamePair (-1, ""));
		//
		StringBuffer sql = new StringBuffer ("SELECT C_ValidCombination_ID, Combination, Description "
			+ "FROM C_ValidCombination WHERE AD_Client_ID=?");
		if (onlyActive)
			sql.append(" AND IsActive='Y'");
		sql.append(" ORDER BY 2");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, Env.getAD_Client_ID(m_ctx));
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add (new KeyNamePair(rs.getInt(1), rs.getString(2) + " - " + rs.getString(3)));
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		}
		finally
		{
			DB.close(rs, pstmt);
		}

		//  Sort & return
		return list;
	}   //  getData

}	//	MAccountLookup
