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
* Created on Mar 15, 2006 by ashley
* 
*/

package org.posterita.util;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;

public class PathInfo
{

	public static String PROJECT_HOME = "";
	public static final String FILE_SEPARATOR =  System.getProperty("file.separator");
	static
	{
		PROJECT_HOME = System.getProperty("user.dir");
	}
	
	public static void setPathInfo(Servlet servlet)
	{
		ServletContext serCtx = servlet.getServletConfig().getServletContext();
		
		try
		{
			PROJECT_HOME = serCtx.getRealPath("") + FILE_SEPARATOR;
		}
		catch(Exception ex)
		{}		
		
	}
}
