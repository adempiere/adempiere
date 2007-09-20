/**
 *  Product: Posterita Web-Based POS (an Adempiere Plugin)
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

/**
	@author Praveen Beekoo
 */
package org.posterita.businesslogic;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.compiere.util.DB;

import org.posterita.core.RandomStringGenerator;
import org.posterita.exceptions.OperationException;

public class CSVReportManager 
{
	public static String generateCSVReport(Properties ctx, String sql) throws OperationException
	{
		PreparedStatement pstmt = DB.prepareStatement(sql, null);
		
		return generateCSVReport(ctx, pstmt);
	}
    public static String generateCSVReport(Properties ctx, PreparedStatement pstmt) throws OperationException
    {
        ResultSet rs;  
        StringBuffer sb = new StringBuffer();
        
//        int count=0;
        try 
        {
            rs = pstmt.executeQuery();
            
            int columnCount = rs.getMetaData().getColumnCount();
            for(int i = 1; i <= columnCount; i++)
            {
                if(i == 1)
                    sb.append("\"");
                else
                    sb.append(",\"");
                
                sb.append(rs.getMetaData().getColumnName(i));
                sb.append("\"");
            }
            
            sb.append("\n");
            
            while(rs.next())
            {
                for(int i = 1; i <= columnCount; i++)
                {
                    if(i == 1)
                        sb.append("\"");
                    else
                        sb.append(",\"");
                    
                    sb.append(rs.getObject(i));
                    sb.append("\"");
                }  
                
                sb.append("\n");
            }            
                       
            rs.close();
        }
        catch (SQLException e)
        {            
            throw new OperationException(e);
        } 
        finally
   	 	{
	   		 try
	   		 {
   				 pstmt.close();
	   		 }
	   		 catch(Exception ex){}

	   		pstmt = null;
   	 	}
        
        String filename = RandomStringGenerator.randomstring() + ".csv";            
        String filepath = ReportManager.getReportPath(filename);
        
        try 
        {
            FileWriter writer = new FileWriter(filepath);
            writer.write(sb.toString());
            writer.flush();
            writer.close();
        }
        catch (IOException e1) 
        {
            throw new OperationException(e1);
        }
        
        return filename;
    }
    
    public static String generateCSVReport(Properties ctx, ArrayList reportDataSource) throws OperationException
    {
//        ResultSet rs;  
        StringBuffer sb = new StringBuffer();        
        
        Iterator iter = reportDataSource.iterator();
        
        while(iter.hasNext())
        {
            Object[] obj = (Object[]) iter.next(); 
            
            for(int i = 0; i < obj.length; i++)
            {
                if(i == 0)
                    sb.append("\"");
                else
                    sb.append(",\"");
                
                sb.append(obj[i]);
                sb.append("\"");
            }  
            
            sb.append("\n");
        }        
        
        String filename = RandomStringGenerator.randomstring() + ".csv";
        String filepath = ReportManager.getReportPath(filename);
        
        try 
        {
            FileWriter writer = new FileWriter(filepath);
            writer.write(sb.toString());
            writer.flush();
            writer.close();
        }
        catch (IOException e1) 
        {
            throw new OperationException(e1);
        }
        
        return filename;
    }
}
