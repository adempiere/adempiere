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
 * Created on Feb 10, 2006 by praveen
 *
 */
package org.posterita.businesslogic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.compiere.util.DB;
import org.compiere.util.MimeType;
import org.posterita.exceptions.OperationException;

public class ReportManager 
{
    
    public static ArrayList<Object[]> getReportData(Properties ctx, PreparedStatement pstmt) throws OperationException
    {
    	ResultSet rs = null;
    	
        try 
        {
            ArrayList<Object[]> reportData =  new ArrayList<Object[]>();
            
            rs = pstmt.executeQuery();
            
            int columnCount = rs.getMetaData().getColumnCount();
            
            Object[] data = null;
                       
            
            Object[] header = new Object[columnCount];
            Object[] columnType = new Object[columnCount];
            
            for(int i = 0; i < columnCount; i++)
            {
                header[i] = rs.getMetaData().getColumnName(i+1);
                columnType[i] = rs.getMetaData().getColumnTypeName(i+1);
            }
            
            reportData.add(header);
            
            while(rs.next())
            {
                data = new Object[columnCount];
                
                for(int i = 0; i < columnCount; i++)
                {
                    data[i] = rs.getObject(i+1);
                }
                
                reportData.add(data);
            }
             
            rs.close();
            
            return reportData;
        } 
        catch (Exception e) 
        {
            throw new OperationException(e);
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
        
    }
    
    public static ArrayList<Object[]> getReportData(Properties ctx, PreparedStatement pstmt, boolean columnHeader) throws OperationException
    {
    	ResultSet rs = null;
    	
        try 
        {
            ArrayList<Object[]> reportData =  new ArrayList<Object[]>();
            
            rs = pstmt.executeQuery();
            
            int columnCount = rs.getMetaData().getColumnCount();
            
            Object[] data = null;
                       
            
            if(columnHeader)
            {
            	Object[] header = new Object[columnCount];
                Object[] columnType = new Object[columnCount];
                
                for(int i = 0; i < columnCount; i++)
                {
                    header[i] = rs.getMetaData().getColumnName(i+1);
                    columnType[i] = rs.getMetaData().getColumnTypeName(i+1);
                }
                
                reportData.add(header);
            }
            
            
            while(rs.next())
            {
                data = new Object[columnCount];
                
                for(int i = 0; i < columnCount; i++)
                {
                    data[i] = rs.getObject(i+1);
                }
                
                reportData.add(data);
            }
             
            rs.close();
            
            
            return reportData;
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
        	catch(Exception e)
        	{}
        	
        	pstmt = null;
        }
        
    }
    
    public static ArrayList<Object[]> getReportData(PreparedStatement pstmt) throws OperationException
    {
    	return getReportData(null,pstmt);
    }
    
    public static ArrayList<Object[]> getReportData(PreparedStatement pstmt,boolean columnHeader) throws OperationException
    {
    	return getReportData(null,pstmt,columnHeader);
    }
    
    public static ArrayList<Object[]> getReportData(Properties ctx,String sql,boolean columnHeader) throws OperationException
    {
    	PreparedStatement pstmt = DB.prepareStatement(sql,null);
    	
		return getReportData(ctx,pstmt,columnHeader);    	
    }
    
    public static ArrayList<Object[]> getReportData(Properties ctx,String sql) throws OperationException
    {
    	PreparedStatement pstmt = DB.prepareStatement(sql,null);
    	
		return getReportData(ctx,pstmt);    	
    }
    
    public static String getReportDirectoryFromServletContext(HttpServlet servlet)
    {
    	ServletContext context = servlet.getServletContext();
		String reportDirectory = context.getRealPath(context.getInitParameter("REPORT_DIRECTORY")) + System.getProperty("file.separator");
		
		return reportDirectory;
    }

	public static String getReportDirectory() 
	{
		//String		
		return System.getProperty("java.io.tmpdir");
	}
	
	public static String getReportURI(String reportName,HttpServletRequest request)
	{
		//return request.getContextPath() + "/GetReportAction.do?reportName=" + reportName;
		return request.getContextPath() + "/servlet/DisplayReportServlet/" + reportName;
	}
	
	public static String getReportPath(String reportName)
	{
		String path = getReportDirectory() + System.getProperty("file.separator") + reportName;
		
		return path;
	}
	
	public static void writeReport(String reportName, HttpServletResponse response) throws OperationException
	{
		String reportPath= getReportPath(reportName);
				
		File f = new File(reportPath);
		
		if(!f.exists())
		{
			throw new OperationException("Unable to load " + reportName + ". Cause file not found.");
		}
		
		String mimeType = null;
		mimeType = MimeType.getMimeType(reportName);
						
		try 
		{
			response.setContentType(mimeType);
			
			FileInputStream fis = new FileInputStream(f);
			BufferedInputStream bis = new BufferedInputStream(fis);
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			
			//Transfer bytes from bis to bos
	        byte[] buf = new byte[1024];
	        int len;
	        while ((len = bis.read(buf)) > 0) 
	        {	        	
	        	bos.write(buf, 0, len);
	        }
	        bis.close();
	        
	        bos.flush();
	        bos.close();
	        
		}
		catch (Exception e) 
		{
			throw new OperationException(e);
		}
	}

}
