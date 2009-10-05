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

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.compiere.util.WebSessionCtx;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.posterita.Constants;
import org.posterita.core.MenuItem;
import org.posterita.exceptions.ApplicationException;

public class BaseDispatchAction extends DispatchAction
{
    BaseAction act = new BaseAction();
    
    public static final String LOGIN_HOME = "loginHome";
    public static final String APPLICATION_ERROR = "applicationError";
    public static final String CHOOSE_APPLICATION = "chooseApplication";
    public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException
    {
        Object webSessionCtx = request.getSession().getAttribute(WebSessionCtx.NAME);
 
        DefaultForm df = (DefaultForm) form;
        
        if (df != null)
	        if ((request.getSession().getAttribute(Constants.MENUS) != null) && (df.getMenuId() != null))
	        {
	        	MenuItem menuItem = (MenuItem) request.getSession().getAttribute(Constants.MENUS);	
		        
		        Integer menuId = Integer.valueOf(df.getMenuId());
		        ArrayList<MenuItem> list = menuItem.getBreadCrumb(menuId.intValue());
		        
		        request.getSession().setAttribute(Constants.BREADCRUMB, list);
	        }
	        
        
        if (webSessionCtx == null) // Session Timeout
        {
       		return mapping.findForward(CHOOSE_APPLICATION);
        }
        else
            return null;
    }
    
    
  
    protected void postGlobalError(String errorKey, HttpServletRequest request)
    {
        act.postGlobalError(errorKey, null, request);
    }
    
    protected void postGlobalError(String errorKey, Object arg, HttpServletRequest request)
    {
        act.postGlobalError(errorKey, arg, request);
    }
    
    protected void postGlobalMessage(String msgKey, HttpServletRequest request)
    {
        act.postGlobalMessage(msgKey, null, request);
    }
    
    protected ActionForm getForm(String formName, String path, HttpServletRequest request)
    {
        return act.getForm(formName, path, request, getServlet());
    }
    
    
    public Object getAttribute(HttpServletRequest request, Class vo)
    {
        Object object = request.getSession().getAttribute(getClass().getName()+vo.getName());
        request.getSession().setAttribute(getClass().getName()+vo.getName(),null);
        return object;
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
    
    /**
     * Checks whether the request is an action on the display tag table (Paging, sorting)
     * @param request Server Request
     * @return True if the request is a display tag action
     */
    protected boolean isDisplayTagAction(HttpServletRequest request)
    {
        String tableId = request.getParameter(Constants.DISPLAYTAG_TABLE_ID);
        
        if (tableId == null || tableId.trim().length() == 0)
        {
            return false;
        }
        
        String strOrder = request.getParameter(new ParamEncoder(tableId).encodeParameterName(TableTagParameters.PARAMETER_ORDER));
        String strPage = request.getParameter(new ParamEncoder(tableId).encodeParameterName(TableTagParameters.PARAMETER_PAGE));
        String strSort = request.getParameter(new ParamEncoder(tableId).encodeParameterName(TableTagParameters.PARAMETER_SORT));
        
        return ((strOrder != null && strOrder.trim().length() > 0) 
                || (strPage != null && strPage.trim().length() > 0) 
                || (strSort != null && strSort.trim().length() > 0));
    }
    
}
