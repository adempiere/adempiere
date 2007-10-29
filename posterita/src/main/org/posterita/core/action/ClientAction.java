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
* Created on Oct 30, 2006 by ashley
* 
*/

/**
	@author ashley
 */

package org.posterita.core.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.compiere.model.MCountry;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.posterita.Constants;
import org.posterita.businesslogic.CurrencyManager;
import org.posterita.businesslogic.POSClientManager;
import org.posterita.core.KeyNamePairUtil;
import org.posterita.core.bean.ClientBean;
import org.posterita.exceptions.ClientAlreadyExistException;
import org.posterita.exceptions.OperationException;
import org.posterita.struts.core.BaseDispatchAction;
import org.posterita.struts.core.DefaultForm;

public class ClientAction extends BaseDispatchAction
{
	
	public static final String INIT_CREATE_CLIENT = "initCreateClient";
	public ActionForward initCreateClient(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Properties ctx = Env.getCtx(); //--> Not using TmkJSPEnv because it may be that no store is defined
		ArrayList<KeyNamePair> countriesKNPair = KeyNamePairUtil.getData(ctx, MCountry.Table_Name, "IsActive='Y'");
		ArrayList<KeyNamePair> currenciesKNPair = CurrencyManager.getAllCurrencies();
		
		request.getSession().setAttribute(Constants.COUNTRIES, countriesKNPair);
		request.getSession().setAttribute(Constants.CURRENCIES, currenciesKNPair);
		
		return mapping.findForward(INIT_CREATE_CLIENT);
	}
	
	public static final String CREATE_CLIENT = "createClient";
	public ActionForward createClient(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DefaultForm df = (DefaultForm)form;
		
		ClientBean clientBean = (ClientBean)df.getBean();
		
		if (clientBean.getFile() == null)
		{
			clientBean.setFile(df.getFile());
		}		
		
		if(!clientBean.getPassword().equals(clientBean.getConfirmPassword()))
		{
			postGlobalError("error.wrong.password.matching", request);
			return mapping.getInputForward();
		}
		
		String storeContext;
		try
		{
			storeContext = POSClientManager.createClient(clientBean);
		}
		catch(ClientAlreadyExistException ex)
		{
			postGlobalError("error.client.alreadyexist", request);
			return mapping.getInputForward();
		}
		catch(OperationException ex)
		{
			postGlobalError("error.client.create", ex.getMessage(), request);
			return mapping.getInputForward();
		}
		
		String contextPath = "/SetApplicationParametersAction.do?action=setApplicationParameters&applicationName=" + storeContext;
		
		return new ActionForward(contextPath);
	}
}
