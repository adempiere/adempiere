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
import org.compiere.model.PO;
import org.posterita.Constants;
import org.posterita.beans.MenuItemBean;
import org.posterita.businesslogic.MenuManager;
import org.posterita.core.MenuItem;
import org.posterita.core.TmkJSPEnv;
import org.posterita.core.bean.ElementBean;
import org.posterita.core.businesslogic.ElementManager;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;
import org.posterita.factory.POSMenuFactory;
import org.posterita.struts.core.BaseDispatchAction;
import org.posterita.struts.core.DefaultForm;



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
        	return mapping.findForward(CHOOSE_APPLICATION);
        }
        
        ArrayList subMenus = new ArrayList();
        if(bean.getMenuId()==null)
        {
        	PO po = POSMenuFactory.getFactoryInstance(ctx).get(ctx, POSMenuFactory.PMENU_SALES_ID);
            bean.setMenuId(po.get_ID());
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
    	String imgSrc = "images/newUI/";
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
        
      
        while(iter.hasNext())
        {
            //----------------------------------------------------------------------------------------------------------------------
            imgSrc = "images/newUI/";
            out.print("<div class=\"liquid-round\">");
            out.print("<div class=\"top\">");
            out.print("<span>");
        	out.print("</span>");
        	out.print("</div>"); //top
        	out.print("<div class=\"center-content\">");
        	out.print("<div id=\"posSubNavContainer\">");
        	out.print("<div id=\"menuCategory\" class=\"category\">");
        	out.print("<h1 id=\"categoryHeader\">");    
        	
        	category = iter.next();
            list = categoryMap.get(category);           
            
            if((category != null)&&(category.length() != 0))
            {
                if(category.contains("order"))
                {
                    imgSrc = imgSrc + "hicon-salesorder.gif";
                }
                else if(category.contains("cash"))
                {
                    imgSrc = imgSrc + "hicon-cashbook.gif";
                }
                else if(category.contains("till"))
                {
                    imgSrc = imgSrc + "hicon-tillmgt.gif";
                }
                else
                {
                    imgSrc = imgSrc + "spacer.gif";
                }
                out.print("<img id=\"categoryImg\" src=\"" + imgSrc + "\">");
             
                elementBean = ElementManager.getMsg(ctx,category);              
                out.print(elementBean.getName());
            }//if
            else
            {
                out.print("<img id=\"categoryImg\" src=\"images/newUI/spacer.gif\">");
            }
            
            out.print("</h1>");
        	out.print("</div>"); //salesOrder
        	
        	out.print("<ul>");
        	
    		for(MenuItem m : list)
    		{
    			out.print("<li>");
    			out.print("<a href='" + m.getMenuLink() + "'>");
    			
    			elementBean = ElementManager.getMsg(ctx,m.getName());        		
        		out.print(elementBean.getName());
    			    			
    			out.print("</a>");
    			out.print("</li>");
    		}//for
    		
    		out.print("</ul>");
    		out.print("</div>"); //posSubNavContainer
    		out.print("</div>"); //center-content
    		out.print("<div class=\"bottom\">");
    		out.print("<span>");
    		out.print("</span>");
    		out.print("</div>");
    		
    		if((category != null)&&(category.length() != 0))
        	{  
    		    out.print("</div>"); //liquid-round
        	}//if        	
    	            	
        }
                   
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