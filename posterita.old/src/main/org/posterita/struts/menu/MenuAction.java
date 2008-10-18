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
package org.posterita.struts.menu;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.compiere.util.Env;

import org.posterita.Constants;
import org.posterita.beans.MenuItemBean;
import org.posterita.businesslogic.ApplicationManager;
import org.posterita.businesslogic.MenuManager;
import org.posterita.core.MenuItem;
import org.posterita.core.TmkJSPEnv;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.DefaultStoreException;
import org.posterita.exceptions.OperationException;
import org.posterita.factory.POSMenuFactory;
import org.posterita.lib.UdiConstants;
import org.posterita.model.UDIPO;
import org.posterita.struts.core.BaseDispatchAction;
import org.posterita.struts.core.DefaultForm;
import org.posterita.core.bean.ElementBean;
import org.posterita.core.businesslogic.ElementManager;



public class MenuAction extends BaseDispatchAction
{
    public static final String GET_MENU_ITEMS = "getMenuItems";
    public static final String GET_POS_MENU_ITEMS = "getPOSMenuItems";
    
    public ActionForward getMenuItems(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping, form, request, response);

		if (fwd != null)
			return fwd;		
		
        Properties ctx = TmkJSPEnv.getCtx(request);		
		
        DefaultForm df = (DefaultForm) form;
        
        MenuItemBean bean = (MenuItemBean) df.getBean();
        
        MenuItem rootMenuItem = (MenuItem)request.getSession().getAttribute(Constants.MENUS);
        
        if(rootMenuItem == null)
        {
        	//TODO: Display error message or should kick to right store
        	mapping.findForward(CHOOSE_APPLICATION);
        }
        
        ArrayList subMenus = new ArrayList();
        if(bean.getMenuId()==null)
        {
        	UDIPO udiPO = POSMenuFactory.getFactoryInstance(ctx).get(ctx, POSMenuFactory.PMENU_SALES_ID);
            bean.setMenuId(udiPO.getID());
        }
        
        if(bean.getMenuId()!= null)
        {
            subMenus = rootMenuItem.getSubMenus(bean.getMenuId().intValue());
        }

    	//subgrouping menus
    	ArrayList<MenuItem> menuItems = subMenus;        
        HashMap<String,ArrayList<MenuItem>> categoryMap = new HashMap<String,ArrayList<MenuItem>>();
        
    	StringWriter sw = new StringWriter();
    	PrintWriter out = new PrintWriter(sw);
    	ElementBean elementBean = null;
    	
    	String category = "";
        ArrayList<MenuItem>  list = null;
        ArrayList<String> categories = new ArrayList<String>();
        
        //group menus
        for(MenuItem menuItem : menuItems)
        {
        	category = menuItem.getCategory();
        	category = (category==null)? "" : category;
        	
        	if(!categories.contains(category))
        	{
        		categories.add(category);
        	}
        	
        	list = categoryMap.get(category);
        	
        	if(list==null)
        	{
        		list = new ArrayList<MenuItem>();        		    		
        	}
        	
        	list.add(menuItem);
        	categoryMap.put(category,list);
        	
        }
        
        //display groups
       
        Iterator<String> iter = categories.iterator();                	
        
        out.print("<table class=\"main\" cellspacing=\"10\">");
        while(iter.hasNext())
        {
        	out.print("<tr><td valign=\"top\" width=\"50%\">");            	
        	//----------------------------------------------------------------------------------------------------------------------
        	
        	category = iter.next();
        	list = categoryMap.get(category);        	
        	
        	if((category != null)&&(category.length() != 0))
        	{
        		out.print("<fieldset class=\"submenu\">");
        		out.print("<legend>");
        		
        		elementBean = ElementManager.getMsg(ctx,category);        		
        		out.print(elementBean.getName());
        		
        		out.print("</legend>");
        	}//if
        	
        	out.print("<ul>");
        	
    		for(MenuItem m : list)
    		{
    			out.print("<li class=\"submenu\">");
    			out.print("<a href='" + m.getMenuLink() + "' class=\"submenu\">");
    			
    			elementBean = ElementManager.getMsg(ctx,m.getName());        		
        		out.print(elementBean.getName());
    			    			
    			out.print("</a>");
    			out.print("</li>");
    		}//for
    		
    		out.print("</ul>");
    		
    		if((category != null)&&(category.length() != 0))
        	{        		
        		out.print("</fieldset>");
        	}//if        	
    		
        	//----------------------------------------------------------------------------------------------------------------------
        	out.print("</td><td valign=\"top\" width=\"50%\">");        	
        	
        	if(!iter.hasNext())
        	{
        		out.print("&nbsp;");
        	}
        	else
        	{
        		//----------------------------------------------------------------------------------------------------------------------
        		
        		category = iter.next();
            	list = categoryMap.get(category);        	
            	
            	if((category != null)&&(category.length() != 0))
            	{
            		out.print("<fieldset class=\"submenu\">");
            		out.print("<legend>");
	        		
	        		elementBean = ElementManager.getMsg(ctx,category);        		
	        		out.print(elementBean.getName());
	        		
	        		out.print("</legend>");
            	}//if
            	
            	out.print("<ul>");
            	
        		for(MenuItem m : list)
        		{
        			out.print("<li class=\"submenu\">");
        			out.print("<a href=" + m.getMenuLink() + " class=\"submenu\">");
        			
        			elementBean = ElementManager.getMsg(ctx,m.getName());        		
        			out.print(elementBean.getName());  
        			 			
        			out.print("</a>");
        			out.print("</li>");
        		}//for
        		
        		out.print("</ul>");
        		
        		if((category != null)&&(category.length() != 0))
            	{        		
            		out.print("</fieldset>");
            	}//if        	
        		
            	//----------------------------------------------------------------------------------------------------------------------
            	
        	}
        	out.print("</td></tr>");
        }
        
        out.print("</table>");
        out.flush();
        
        String menu = sw.toString();
    	
        request.getSession().setAttribute(Constants.MENU_ITEMS, menu);
    	return mapping.findForward(GET_POS_MENU_ITEMS);
    }    
    
    public static final String INIT_EDIT_LINKSTATUS = "initEditLinkStatus";
    public ActionForward initEditLinkStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping, form, request, response);

		if (fwd != null)
			return fwd;		
		
        Properties ctx = TmkJSPEnv.getCtx(request);		
		
        ArrayList menuKeyNamePairs = MenuManager.getClosableMenus(ctx);
        
        request.getSession().setAttribute(Constants.MENU_LIST, menuKeyNamePairs);
        
        return mapping.findForward(INIT_EDIT_LINKSTATUS);
    }
    
    public static final String SELECT_LINK_TO_EDIT = "selectLinkToEdit";
    public ActionForward selectLinkToEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping, form, request, response);

		if (fwd != null)
			return fwd;		
		
        Properties ctx = TmkJSPEnv.getCtx(request);		
		
        DefaultForm df = (DefaultForm) form;
        
        MenuItemBean bean = (MenuItemBean) df.getBean();
        
        MenuItemBean bean2 = MenuManager.getMenu(ctx, bean.getMenuId().intValue());
        
        df.populate(bean2);
        
        return mapping.findForward(SELECT_LINK_TO_EDIT);
    }
    
    public static final String EDIT_MENU = "editMenu";
    public ActionForward editMenu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OperationException, ApplicationException
    {
        ActionForward fwd = init(mapping, form, request, response);

		if (fwd != null)
			return fwd;		
		
        Properties ctx = TmkJSPEnv.getCtx(request);		
		
        DefaultForm df = (DefaultForm) form;
        
        MenuItemBean bean = (MenuItemBean) df.getBean();
        
        MenuItemBean editedMenu = MenuManager.editMenu(ctx, bean);
        
        request.getSession().setAttribute(Constants.MENU, editedMenu);
        
        return mapping.findForward(EDIT_MENU);
    }
}