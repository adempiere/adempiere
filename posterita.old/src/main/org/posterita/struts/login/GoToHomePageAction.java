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
 * Created on Oct 26, 2005 by praveen
 *
 */
package org.posterita.struts.login;

import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.compiere.model.MStore;
import org.compiere.util.Env;
import org.posterita.Constants;
import org.posterita.beans.ApplicationParametersBean;
import org.posterita.businesslogic.ApplicationManager;
import org.posterita.businesslogic.StoreManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.exceptions.DefaultStoreException;
import org.posterita.lib.UdiConstants;
import org.posterita.struts.core.BaseDispatchAction;
import org.posterita.struts.core.DefaultForm;

public class GoToHomePageAction extends BaseDispatchAction
{
    public static String GO_TO_HOMEPAGE = "goToHomePage";
    public ActionForward goToHomePage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		ActionForward fwd = init(mapping,form,request,response);
	    if (fwd!=null)
	        return fwd;
	    
	    try
    	{
	    	Properties ctx = Env.getCtx();
	    	MStore store = StoreManager.getDefaultStore(ctx);
	    	ApplicationManager.setApplicationParametersInContext(ctx, store.get_ID());
    		ctx = TmkJSPEnv.getCtx(request);
    		String forward = Env.getContext(ctx, UdiConstants.DEFAULT_FORWARD);
    		
    		return mapping.findForward(forward);
    	}
    	catch(Exception ex)
    	{
    		postGlobalError("error.store.default", ex.getMessage(), request);
    		return mapping.findForward(CHOOSE_APPLICATION);
    	}
	}
    
    public static final String CHOOSE_APPLICATION = "chooseApplication";
    public ActionForward setApplicationParameters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		DefaultForm df = (DefaultForm) form;
		ApplicationParametersBean bean = (ApplicationParametersBean) df.getBean();
		
		Integer storeId = bean.getStoreId();
		
		// Store not defined, should go to default store or choose from different stores
		if (storeId == null)
		{
			return goToHomePage(mapping, form, request, response);
		}
		
		Properties ctx = Env.getCtx();
		
		MStore store = new MStore(ctx, storeId, null);
		
		if (store.get_ID() == 0)
		{
			//TODO: Add error message showing no store error to user
			mapping.findForward(CHOOSE_APPLICATION);
		}
		
		
		ApplicationManager.setApplicationParametersInContext(ctx, storeId);
		
        ctx = TmkJSPEnv.getCtx(request);
        
        request.getSession().getServletContext().setAttribute(Constants.APP_NAME, bean.getApplicationName());
			
		return mapping.findForward(UdiConstants.DEFAULT_FORWARD);
    }
    
    public static final String INIT_CHOOSE_APPLICATION = "initChooseApplication";
    public ActionForward initChooseApplication(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	ArrayList<ApplicationParametersBean> appList = ApplicationManager.getAvailableApplications();		
		servlet.getServletContext().setAttribute(Constants.WEB_APPLICATIONS, appList);
		
		return mapping.findForward(INIT_CHOOSE_APPLICATION);
    }
}
