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
 * 31-Jul-2006 10:08:46 by praveen
 *
 */

package org.posterita.struts.pos;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.compiere.util.MimeType;

import org.posterita.businesslogic.ReportManager;
import org.posterita.exceptions.OperationException;
import org.posterita.struts.core.DefaultForm;

public class GetReportAction extends POSAction
{	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OperationException
	{
        
		DefaultForm df = (DefaultForm) form;
		String reportName = df.getReportName();
		
		if(reportName==null)
		{
			throw new OperationException("Invalid parameter! ReportName cannot be null.");
		}
		
		String mimeType = null;
			
		if(reportName.endsWith(".csv"))
		{
			mimeType = "text/comma-separated-values";
		}
		else
		{
			mimeType = MimeType.getMimeType(reportName);
		}		
				
		String reportPath = ReportManager.getReportPath(reportName);
		
		File f = new File(reportPath);
		
		if(!f.exists())
		{
			throw new OperationException("Unable to load " + reportName + ". Cause file not found.");
		}
		
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
		
		return null;
	}
}
