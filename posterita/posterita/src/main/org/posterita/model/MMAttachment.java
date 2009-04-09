/**
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
* Created on May 10, 2006 by ashley
* 
*/

package org.posterita.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MAttachment;
import org.compiere.util.DB;

import org.posterita.exceptions.OperationException;

public class MMAttachment extends MAttachment
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MMAttachment(Properties ctx, int AD_Table_ID, int Record_ID, String trxName)
	{
		super(ctx, AD_Table_ID, Record_ID, trxName);
	}
	
	public static MAttachment get (Properties ctx, int AD_Table_ID, int Record_ID, String trxName) throws OperationException
	{
		MAttachment retValue = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM AD_Attachment WHERE AD_Table_ID=? AND Record_ID=?";
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			pstmt.setInt (1, AD_Table_ID);
			pstmt.setInt (2, Record_ID);
			ResultSet rs = pstmt.executeQuery ();
			if (rs.next ())
				retValue = new MAttachment (ctx, rs, trxName);
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			throw new OperationException("Cannot get attachment!!!", e);
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
}
