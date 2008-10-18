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
 * Created on Aug 29, 2005 by din
 */

package org.posterita.struts.login;



import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.posterita.core.TmkJSPEnv;

import org.posterita.Constants;
import org.posterita.beans.ChangePasswordBean;
import org.posterita.businesslogic.LoginManager;
import org.posterita.exceptions.WrongPasswordException;
import org.posterita.struts.core.BaseDispatchAction;
import org.posterita.struts.core.DefaultForm;
import org.posterita.user.WebUserInfo;

	

	public class ChangePasswordAction extends BaseDispatchAction
	{
		
		public ActionForward change(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			ActionForward fwd = init(mapping,form,request,response);
		    if (fwd!=null)
		        return fwd;
			
		    DefaultForm df = (DefaultForm) form;
		    ChangePasswordBean bean = (ChangePasswordBean) df.getBean();
			Properties ctx = TmkJSPEnv.getCtx(request);		
			
			WebUserInfo userInfo = (WebUserInfo) request.getSession().getAttribute(WebUserInfo.NAME);
			
			try
			{
				LoginManager.changePassword(ctx, bean, userInfo);
			}
			
			catch(WrongPasswordException e)		
			{
				 postGlobalError("admin.wrongPasswordError",request);
				 return (new ActionForward(mapping.getInput()));
				
			}
			
			
			return mapping.findForward(Constants.CHANGE);
			
		}

	}


