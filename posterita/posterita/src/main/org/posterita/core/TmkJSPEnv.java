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
 *  
 **/
package org.posterita.core;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.compiere.util.Env;
import org.compiere.wstore.JSPEnv;
import org.posterita.exceptions.TMKRuntimeException;
import org.posterita.lib.UdiConstants;
 
/**
 *  JSP Environment Utilities
 *
 *  @author Jorg Janke
 *  @version $Id: TmkJSPEnv.java,v 1.2 2006/12/15 06:32:14 vishee Exp $
 */
public class TmkJSPEnv
{
	public static Properties getCtx(HttpServletRequest request)
	{
		Properties ctx =  JSPEnv.getCtx(request);
		
        String user_org = Env.getContext(ctx,UdiConstants.ROLE_EDITABLE_ORGS_CTX_PARAM);
        
        if ((user_org == null) || (user_org.equals(""))) //FIXME
        {
        	Env.setContext(ctx, UdiConstants.ROLE_EDITABLE_ORGS_CTX_PARAM, Env.getAD_Org_ID(ctx));
        }
        
        try //Required since our friend Jorg hardcoded 30 mins in JSPEnv.java
        {
			request.getSession().setMaxInactiveInterval(5*60*60); // 5 Hours
        }
        catch(Exception ex)
        {
        	throw new TMKRuntimeException("Could not figure the application type for the current session");
        }
        
        return ctx;
        	
	}
}	
