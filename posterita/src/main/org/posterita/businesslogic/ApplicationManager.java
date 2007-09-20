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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.compiere.model.MBPartner;
import org.compiere.model.MStore;
import org.compiere.model.MUser;
import org.compiere.util.Env;
import org.compiere.util.WebSessionCtx;
import org.compiere.util.WebUser;
import org.compiere.wstore.JSPEnv;
import org.posterita.Constants;
import org.posterita.beans.ApplicationParametersBean;
import org.posterita.beans.CustomerBean;
import org.posterita.exceptions.DefaultStoreException;
import org.posterita.exceptions.OperationException;
import org.posterita.lib.UdiConstants;
import org.posterita.user.WebUserInfo;

	/**
	 *  @author Ashley
	*/

public class ApplicationManager
{
    public static void setApplicationParametersInContext(Properties tmkCtx, String applicationName) 
    {
    	String sql = "WebContext='/" + applicationName + "'";
    	int storeIds[] = MStore.getAllIDs(MStore.Table_Name, sql, null);
    	if(storeIds == null || storeIds.length == 0)
    		throw new RuntimeException("No store found for application with name/context: " + applicationName);
    	else if(storeIds.length > 1)
    		throw new RuntimeException(storeIds.length + " stores found with same context, " + applicationName);
    	
    	Env.setContext(tmkCtx, "#AD_Client_ID", 0);
	    Env.setContext(tmkCtx, "#AD_Org_ID", 0);
    	MStore store = new MStore(tmkCtx, storeIds[0], null);
    	
    	if(store.get_ID() == 0)
    		throw new RuntimeException("No store found for application with name/context: " + applicationName);
    	Env.setContext(tmkCtx, UdiConstants.CLIENT_ID_CTX_PARAM, store.getAD_Client_ID());
	    Env.setContext(tmkCtx, UdiConstants.ORG_ID_CTX_PARAM, store.getAD_Org_ID());
	    Env.setContext(tmkCtx, UdiConstants.USER_ORG_CTX_PARAM, store.getAD_Org_ID());
	    Env.setContext(tmkCtx, UdiConstants.PRICELIST_CTX_PARAM, store.getM_PriceList_ID());
	    Env.setContext(tmkCtx, UdiConstants.WAREHOUSE_CTX_PARAM, store.getM_Warehouse_ID());
	    Env.setContext(tmkCtx, UdiConstants.CSS, store.getWebParam5());
	    Env.setContext(tmkCtx, UdiConstants.FORWARD, store.getWebParam6());
	    Env.setContext(tmkCtx, "#W_Store_ID", store.get_ID());
        
        String language = store.getWebParam4();
        if (language != null && language != "")
        {
            Env.setContext(tmkCtx, UdiConstants.LANGUAGE_CTX_PARAM, language);
        }
    }
    
    public static void changeApplication(HttpServletRequest request, HttpServletResponse response) throws OperationException, DefaultStoreException
    {
    	String appName = ApplicationManager.getApplicationNameFromCookie(request);
    	changeApplication(request, response, appName);
    }
    
    public static void changeApplication(HttpServletRequest request, HttpServletResponse response, String applicationType) throws DefaultStoreException
    {
		if(applicationType == null)
			applicationType = getDefaultApplicationType();
		
		if(!isApplicationPresent(applicationType))
			applicationType = getDefaultApplicationType();
		
			
		HttpSession session = request.getSession();
        
        WebUserInfo wuInfo = (WebUserInfo)session.getAttribute(WebUserInfo.NAME);
        
        if (wuInfo != null)
        {
            WebUser wu = wuInfo.getUser();
            if (wu != null)
                wu.logout();
        }
        session.removeAttribute(WebSessionCtx.NAME);
        session.removeAttribute(WebUserInfo.NAME);
        session.setMaxInactiveInterval(0);
        Properties ctx = JSPEnv.getCtx(request);
		setApplicationParametersInContext(ctx, applicationType);
		setApplicationNameInCookie(response, applicationType);
    }

    
    public static String getApplicationType(Properties ctx) throws OperationException
    {
    	String appType = Env.getContext(ctx, UdiConstants.WEBPARAM6);
    	if(appType != null && appType.length() != 0)
    		return appType;
    	else
    		throw new OperationException("Session has timed out! Please reload the home page.");
    }
    
    public static void setApplicationNameInCookie(HttpServletResponse response,String appName)
    {
        Cookie cookie =  new Cookie(Constants.APP_NAME, appName);
        
        cookie.setMaxAge(365*24*60*60);		
        response.addCookie(cookie);
    }

    public static String getApplicationNameFromCookie(HttpServletRequest request) 
    {
        Cookie[] cookies = request.getCookies();
        String appName = null;
        
        if(cookies != null)
        {
            for(int i = 0; i < cookies.length; i++)
            {
                if(cookies[i].getName().equalsIgnoreCase(Constants.APP_NAME))
                {
                    appName = cookies[i].getValue();				
                }
                
            }
        }
        return appName;
    }
    
    public static String getDefaultApplicationType() throws DefaultStoreException
    {
    	Properties ctx = Env.getCtx();
    	MStore store = StoreManager.getDefaultStore(ctx);
    	
    	String retVal = store.getWebContext();
    	retVal = retVal.replaceAll("/", "");
    	
    	return retVal;
    }
    
    public static boolean isApplicationPresent(String applicationType)
    {
    	return StoreManager.isStorePresent("/" + applicationType);
    }

    //This method is used to find out the email sender depending on the application 
    public static CustomerBean getSalesRepMStore(Properties ctx, String trxName) throws OperationException
    {
    	CustomerBean bean = new CustomerBean();
    	
    	String storeIdStr = Env.getContext(ctx, "#W_Store_ID");
    	
    	if (storeIdStr == null || storeIdStr.equals(""))
    		throw new OperationException("WStore not found in session!!");
    	
    	MStore store = new MStore(ctx, Integer.parseInt(storeIdStr), trxName);
    	
    	if(store == null)
    		throw new OperationException("No store found!!");
    	
    	if (store.getSalesRep_ID() == 0)
    	{
    		//Default sender
    		bean.setEmail("crm@posterita.com");
    		bean.setUsername("Tamak Webmaster");
    	}
    	
    	MUser user = UserManager.loadUser(ctx, store.getSalesRep_ID(), null);
    	
    	MBPartner partner = BPartnerManager.loadBPartner(ctx, user.getC_BPartner_ID(), null);
    	
    	bean.setSalesRepId(store.getSalesRep_ID());
		bean.setEmail(user.getEMail());
		bean.setUsername(partner.getName() + " " + partner.getName2());
    	
    	return bean;
    }
    
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
			appParamBean.setApplicationType(store.getWebParam6());
			appList.add(appParamBean);
		}
		
		return appList;
    }

    public static String getApplicationName(String appContextPath)
    {
    	String whereClause = "IsActive='Y' and WEBCONTEXT='/"+ appContextPath + "'";
		int storeIds[] = MStore.getAllIDs(MStore.Table_Name, whereClause, null);
		
		Properties ctx = Env.getCtx();
		
		if (storeIds.length == 0)
			return null;
		
		
		MStore store = new MStore(ctx, storeIds[0], null);
		
		return store.getName();
    }
    
    
}
