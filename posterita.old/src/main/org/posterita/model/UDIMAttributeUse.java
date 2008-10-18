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
package org.posterita.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.compiere.model.MAttributeUse;
import org.compiere.util.DB;

import org.posterita.exceptions.OperationException;

public class UDIMAttributeUse extends UDIPO
{

    public UDIMAttributeUse(MAttributeUse attributeUse) 
    {
        super(attributeUse);
    }
    
    public MAttributeUse getMAttributeUse()
    {
    	return (MAttributeUse) getPO();
    }
    
    public static int[] getAllIDs (String TableName, String WhereClause, String trxName) throws OperationException
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		StringBuffer sql = new StringBuffer("SELECT ");
		sql.append("M_ATTRIBUTE").append("_ID FROM ").append(TableName);
		if (WhereClause != null && WhereClause.length() > 0)
			sql.append(" WHERE ").append(WhereClause);
		
		PreparedStatement pstmt = null;
		
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
				list.add(Integer.valueOf(rs.getInt(1)));
			rs.close();
		}
		catch (SQLException e)
		{
			throw new OperationException(e.getMessage());
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
		
		//	Convert to array
		int[] retValue = new int[list.size()];
		for (int i = 0; i < retValue.length; i++)
			retValue[i] = ((Integer)list.get(i)).intValue();
		return retValue;
	}	//	getAllIDs

}
