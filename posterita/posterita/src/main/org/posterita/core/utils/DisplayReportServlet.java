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
 * 03-Aug-2006 14:29:15 by praveen
 *
 */

package org.posterita.core.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.compiere.util.MimeType;

import org.posterita.businesslogic.performanceanalysis.ReportManager;

public class DisplayReportServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException
	{
		String reportURI = request.getRequestURI();
		int index = reportURI.lastIndexOf("/");
		String reportName = reportURI.substring(index+1);
		
		String reportPath = ReportManager.getReportPath(reportName);
		
		File f = new File(reportPath);
		
		if(!f.exists())
		{
			throw new ServletException("Unable to load " + reportName + ". Cause file not found.");
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
			throw new ServletException(e);
		}
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException
	{
		doGet(request,response);
	}
}
