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
 * Created on Jul 28, 2005 by din
 */

package org.posterita.struts.menu;

import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.posterita.Constants;
import org.posterita.beans.MenuItemBean;
import org.posterita.businesslogic.MenuManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.SystemException;
import org.posterita.keyname.MenuKeyNamePair;
import org.posterita.struts.core.BaseDispatchAction;
import org.posterita.struts.core.DefaultForm;

public class CloseMenuAction extends BaseDispatchAction
{
	
	    public static final String CLOSE_MENU= "closeMenuItems";
	    public ActionForward closeMenuItems(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, SystemException, OperationException
	    {
	        ActionForward fwd = init(mapping, form, request, response);

			if (fwd != null)
				return fwd;		
			
	        Properties ctx = TmkJSPEnv.getCtx(request);		
			
	        DefaultForm df = (DefaultForm) form;
	        
	        MenuItemBean bean = (MenuItemBean) df.getBean();
	        
	        MenuManager.saveMenu(ctx,bean);
	        
	        request.getSession().setAttribute(Constants.MENUS,bean);
	                     
	        return mapping.findForward(CLOSE_MENU);
	    }    

	    public static final String INIT_MENU= "initMenuItems";
	    public ActionForward initMenuItems(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, SystemException, OperationException
	    {
	        ActionForward fwd = init(mapping, form, request, response);

			if (fwd != null)
				return fwd;		
		
	        Properties ctx = TmkJSPEnv.getCtx(request);		
		      
	        ArrayList menus = MenuKeyNamePair.getKeyNamePairs(ctx);
	        
	        
	        
	        request.getSession().setAttribute(Constants.MENUS, menus);
	        
	        return mapping.findForward(INIT_MENU);
	    }    
	}


