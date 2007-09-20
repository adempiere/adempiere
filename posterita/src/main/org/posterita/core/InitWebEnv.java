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
 * Created on 16-Feb-2005 by fred
 * 
 */


package org.posterita.core;

import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.compiere.util.CLogMgt;
import org.compiere.util.WebEnv;
import org.posterita.Constants;
import org.posterita.beans.ApplicationParametersBean;
import org.posterita.businesslogic.ApplicationManager;
import org.posterita.lib.PropertiesConstant;
import org.posterita.util.PathInfo;


public class InitWebEnv implements PlugIn 
{
  
    public static final String applicationType = UDIFilePropertiesManager.getProperty().get(new Properties(), PropertiesConstant.APPLICATION_TYPE);
    
	
	public void init(ActionServlet servlet, ModuleConfig config) throws ServletException
    {   	
        CLogMgt.initialize(true);
        
	    WebEnv.initWeb(servlet.getServletContext());
	    
	    PathInfo.setPathInfo(servlet);
	    
	    clearReportsDirectory(servlet);

	    setAvailableApplications(servlet);

    }
	
	private void setAvailableApplications(ActionServlet servlet)
	{
		ArrayList<ApplicationParametersBean> appList = ApplicationManager.getAvailableApplications();		
		servlet.getServletContext().setAttribute(Constants.WEB_APPLICATIONS, appList);
	}

  
    /**
     * Clears all the dynamically generated reports
     * @param servlet
     */
    private void clearReportsDirectory(HttpServlet servlet)
    {    	
    	/*
    	ServletContext context = servlet.getServletContext();
		String reportDirectoryPath = context.getRealPath(context.getInitParameter("REPORT_DIRECTORY"));	
		File reportDirectory = new File(reportDirectoryPath);
		
		if(reportDirectory.exists())
		{			
			System.out.println("Cleaning old reports --> "+ reportDirectoryPath);
			
			File[] files = reportDirectory.listFiles();
			for (File file : files) 
			{
				if(file.isFile())
					file.delete();
			}
			
			System.out.println("Cleaning process completed successfully.");
		}
		else
		{	
			System.out.println("Creating report directory.");
			if(reportDirectory.mkdirs())
			{
				System.out.println("Report directory created successfully.");
			}
			else
			{
				System.err.println("Unable to create report directory!");
			}
		}	
		*/	
    	
    }  	
	
    public void destroy() 
    {
    }	
    
  
    

}
