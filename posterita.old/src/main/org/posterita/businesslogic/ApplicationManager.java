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
 */



package org.posterita.businesslogic;

import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.compiere.model.MStore;
import org.compiere.util.Env;
import org.posterita.beans.ApplicationParametersBean;
import org.posterita.exceptions.DefaultStoreException;
import org.posterita.lib.UdiConstants;

	/**
	 *  @author Ashley
	*/

public class ApplicationManager
{
    public static void setApplicationParametersInContext(Properties tmkCtx, int storeId) 
    {
    	Env.setContext(tmkCtx, "#AD_Client_ID", 0);
	    Env.setContext(tmkCtx, "#AD_Org_ID", 0);
    	MStore store = new MStore(tmkCtx, storeId, null);
    	
    	if(store.get_ID() == 0)
    		throw new RuntimeException("No store found for application with ID: " + storeId);
    	Env.setContext(tmkCtx, UdiConstants.CLIENT_ID_CTX_PARAM, store.getAD_Client_ID());
	    Env.setContext(tmkCtx, UdiConstants.ORG_ID_CTX_PARAM, store.getAD_Org_ID());
	    Env.setContext(tmkCtx, UdiConstants.USER_ORG_CTX_PARAM, store.getAD_Org_ID());
	    Env.setContext(tmkCtx, UdiConstants.PRICELIST_CTX_PARAM, store.getM_PriceList_ID());
	    Env.setContext(tmkCtx, UdiConstants.WAREHOUSE_CTX_PARAM, store.getM_Warehouse_ID());
	    Env.setContext(tmkCtx, UdiConstants.CSS, store.getWebParam5());
	    Env.setContext(tmkCtx, UdiConstants.FORWARD, store.getWebParam6());
	    Env.setContext(tmkCtx, UdiConstants.WSTORE_CTX_PARAM, store.get_ID());
        
        String language = store.getWebParam4();
        if (language != null && language != "")
        {
            Env.setContext(tmkCtx, UdiConstants.LANGUAGE_CTX_PARAM, language);
        }
    }
    
    //TODO Refactor, work with store id instead of context
    public static ArrayList<ApplicationParametersBean> getAvailableApplications()
    {
    	String whereClause = "IsActive='Y' order by Name";
		int storeIds[] = MStore.getAllIDs(MStore.Table_Name, whereClause, null);
		
		ArrayList<ApplicationParametersBean> appList = new ArrayList<ApplicationParametersBean>();
		Properties ctx = Env.getCtx();
		
		for(int i = 0; i < storeIds.length; i++)
		{
			MStore store = new MStore(ctx, storeIds[i], null);
			String appContextPath = store.getWebContext();
			appContextPath = appContextPath.replaceAll("/", "");
			
			ApplicationParametersBean appParamBean = new ApplicationParametersBean();
			appParamBean.setApplicationName(store.getName());
			appParamBean.setApplicationWebContext(appContextPath);
			appParamBean.setStoreId(store.get_ID());
			appList.add(appParamBean);
		}
		
		return appList;
    }
}