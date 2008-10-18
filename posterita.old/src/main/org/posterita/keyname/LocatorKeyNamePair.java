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
* Created on Sep 22, 2005 by ashley
* 
*/

package org.posterita.keyname;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.model.MLocator;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

import org.posterita.exceptions.OperationException;

public class LocatorKeyNamePair
{
	public static ArrayList getKeyNamePair(Properties ctx) throws OperationException 
	{
		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
		
		PreparedStatement prepStmt = null;
		
		try
		{
			int adOrgId = Env.getAD_Org_ID(ctx);
			int adClientId = Env.getAD_Client_ID(ctx);
			
			String sqlStatement = "select M_LOCATOR_ID, VALUE from " + MLocator.Table_Name + " where AD_ORG_ID=" + adOrgId + " and AD_CLIENT_ID=" + adClientId + " and isactive = 'Y'";
			
			prepStmt = DB.prepareStatement(sqlStatement, null);
			ResultSet rs = prepStmt.executeQuery();
			
			while(rs.next())
			{
				int id = rs.getInt(1);
				String name = rs.getString(2);
				
				KeyNamePair keyPair = new KeyNamePair(id, name);
				list.add(keyPair);
			}
		} 
		catch (SQLException e)
		{
			throw new OperationException(e.getMessage());
		}
		
		return list;
	}
	
	public static KeyNamePair getKeyNamePair(Properties ctx, int locatorId) throws OperationException
	{
		PreparedStatement prepStmt = null;
		KeyNamePair keyPair = null;
		try
		{
			int adOrgId = Env.getAD_Org_ID(ctx);
			int adClientId = Env.getAD_Client_ID(ctx);
			
			String sqlStatement = "select M_LOCATOR_ID, VALUE from " + MLocator.Table_Name + " where AD_ORG_ID=" + adOrgId + " and AD_CLIENT_ID=" + adClientId + " and M_LOCATOR_ID=" + locatorId;
			
			prepStmt = DB.prepareStatement(sqlStatement, null);
			ResultSet rs = prepStmt.executeQuery();
			
			while(rs.next())
			{
				int id = rs.getInt(1);
				String name = rs.getString(2);
				
				keyPair = new KeyNamePair(id, name);
			}
		} 
		catch (SQLException e)
		{
			throw new OperationException(e.getMessage());
		}
		
		return keyPair;
	}
}
