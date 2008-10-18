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
 * Created on 25-Jul-2005 by alok
 *
 */
 
package org.posterita.struts.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.RequestUtils;

import org.posterita.exceptions.ApplicationException;
import org.posterita.user.WebUserInfo;


public class BaseAction extends Action
{
    
    public static final String LOGIN_HOME = "loginHome";
    public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException
	{
		WebUserInfo userInfo = (WebUserInfo) request.getSession().getAttribute(WebUserInfo.NAME);
	
		if (userInfo == null)
		    return mapping.findForward(LOGIN_HOME);
		else
		    return null;
		
		
		    
	}
    
	protected void postGlobalError(String errorKey, HttpServletRequest request)
	{
		postGlobalError(errorKey, null, request);
	}
	
	protected void postComplexGlobalError(String errorKey, Object[] args,HttpServletRequest request)
	{
	  
		ActionMessage message = (args.length == 0 ? 
							new ActionMessage(errorKey):
							new ActionMessage(errorKey, args));
										  
		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveErrors(request, messages);
		
		
		
	}

    protected void postGlobalError(String errorKey, Object arg, HttpServletRequest request)
	{
	    //TODO Replace this code
        ActionMessage message = (arg == null ? 
							new ActionMessage(errorKey):
							new ActionMessage(errorKey, arg));
										  
        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveErrors(request, messages);
		
		 // workflow
		//TODO Integrate this code when workflow works
	   //WorkFlowManager.removeLastWorkFlowAction(request);
	}
    
    protected void postGlobalMessage(String msgKey, Object arg, HttpServletRequest request)
	{
	    //TODO Replace this code
        ActionMessage message = (arg == null ? 
							new ActionMessage(msgKey):
							new ActionMessage(msgKey, arg));
										  
        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveMessages(request, messages);
        		
		 // workflow
		//TODO Integrate this code when workflow works
	   //WorkFlowManager.removeLastWorkFlowAction(request);
	}
	
	protected ActionForm getForm(String formName, String path, HttpServletRequest request)
	{
		return getForm(formName, path, request, getServlet());
	}
	
	protected ActionForm getForm(String formName, String path, HttpServletRequest request, ActionServlet servlet)
	{
		// check if the form is in the request scope
		ActionForm form = (ActionForm) request.getAttribute(formName);
		
		// check if the form is in the session scope
		if (form == null)
		{
			form = (ActionForm) request.getSession().getAttribute(formName);
		}
		// if not found in any of these create one and set it in the appropriate scope.
		if(form == null)
		{
			ModuleConfig appConfig = (ModuleConfig) request.getAttribute(Globals.MODULE_KEY);
			
			ActionMapping mapping = (ActionMapping) appConfig.findActionConfig(path);
			
			form = RequestUtils.createActionForm(request,mapping,appConfig, servlet);
			
			String key = mapping.getAttribute();
			
			if (!key.equals(formName))
				throw new RuntimeException("error.baseAction.InvalidFormName");
			
			if(mapping.getScope().equals("request"))
				request.setAttribute(key, form);
			else
				request.getSession().setAttribute(key, form);
			
		}
		return form;
		
	}
	
}
