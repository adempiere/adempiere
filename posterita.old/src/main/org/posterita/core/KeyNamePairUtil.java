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
 */
package org.posterita.core;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.util.DB;
import org.compiere.util.KeyNamePair;

public class KeyNamePairUtil 
{
	public static ArrayList<KeyNamePair> getData(Properties ctx, String tableName, String whereClause) throws SQLException
	{
		return getData(ctx, tableName, whereClause, null);
	}
	
    public static ArrayList<KeyNamePair> getData(Properties ctx, String tableName, String whereClause, String trxName) throws SQLException 
    {
        
       // StringBuffer where = new StringBuffer();
        //StringBuffer from = new StringBuffer();
        
        String keyColumn = tableName + "_ID";
        String sql;

        sql = "select name, " 
	            + keyColumn
	            + " from "
	            + tableName;
        
        if (whereClause!=null)
            sql = sql + " where " + whereClause+(" order by 1");
        
        PreparedStatement pstmt = null;
        
        pstmt = DB.prepareStatement(sql, trxName);
        ResultSet rs;
        ArrayList<KeyNamePair> data = new ArrayList<KeyNamePair>();
        KeyNamePair pair;
        int key;
        String name;
        
        
        try
		{
			rs = pstmt.executeQuery();

			while (rs.next())
	        {
	            name = rs.getString(1);
	            key = rs.getInt(2);
	            pair = new KeyNamePair(key, name);
	            data.add(pair);
	        }
			
	        rs.close();
		}
		catch (SQLException e)
		{
			throw e;
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
        
        return data;  		
    }
}
