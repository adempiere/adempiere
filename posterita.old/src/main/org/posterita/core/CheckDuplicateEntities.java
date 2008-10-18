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
 * Created on 01-Aug-2005 by alok
 *
 */
package org.posterita.core;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.compiere.util.DB;
import org.compiere.util.Env;




public class CheckDuplicateEntities
{
	
	public static boolean checkDuplicateName(Properties ctx,String name,String tableName)
	{
		boolean existName = false;
		
		String sql = " select name from "+tableName +
		" where upper(name) = upper('"+name+"')" +
		" and AD_CLIENT_ID = "+ Env.getAD_Client_ID(ctx);
		
		PreparedStatement pstmt = DB.prepareStatement(sql, null);
		
		ResultSet rs = null;
		
		try
		{
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				existName = true;
			}

			rs.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				pstmt.close();
			}
			catch(Exception e)
			{}
			
			pstmt = null;
		}
		
		return existName;
		
	}
	
	public static boolean checkDuplicateBPName(Properties ctx,String name,String name2,String tableName)
	{
		boolean existName = false;
		String sql;
		
		if (name2 != null)
		    sql = " select name, name2 from "+tableName +
			" where upper(name) = upper('"+name+"')" +
			" and upper(name2) = upper('" + name2 + "')" + 
			" and ad_org_id = " + Env.getAD_Org_ID(ctx) +
			" and AD_CLIENT_ID = "+ Env.getAD_Client_ID(ctx);
		else
		    sql = " select name, name2 from "+tableName +
			" where upper(name) = upper('"+name+"')" +
			" and upper(name2) is null" + 
			" and ad_org_id = " + Env.getAD_Org_ID(ctx) +
			" and AD_CLIENT_ID = "+ Env.getAD_Client_ID(ctx);
		
		PreparedStatement pstmt = DB.prepareStatement(sql, null);
		
		ResultSet rs = null;
		
		try
		{
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				existName = true;
			}
			
			rs.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				pstmt.close();
			}
			catch (Exception e)
			{}
			
			pstmt = null;
		}
		
		return existName;
		
	}
	
	public static boolean checkDuplicateChequeNo(Properties ctx,String chequeNo,String tableName)
	{
		boolean existCheque = false;
		
		String sql = " select CHEQUENO from "+tableName +
		" where upper(CHEQUENO) = upper('"+chequeNo+"')" +
		" and AD_CLIENT_ID = "+ Env.getAD_Client_ID(ctx);
		
		PreparedStatement pstmt = DB.prepareStatement(sql, null);
		
		ResultSet rs = null;
		
		try
		{
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				existCheque = true;
			}

			rs.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				pstmt.close();
			}
			catch(Exception e)
			{}
			
			pstmt = null;
		}
		
		return existCheque;
		
	}
	
	
}
