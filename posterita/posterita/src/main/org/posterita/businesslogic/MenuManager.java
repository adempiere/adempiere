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
 *  Created on Nov 6, 2005 by alok
 **/
package org.posterita.businesslogic;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.compiere.util.DB;
import org.compiere.util.Env;
import org.posterita.Constants;
import org.posterita.beans.MenuItemBean;
import org.posterita.core.MenuItem;
import org.posterita.core.bean.ElementBean;
import org.posterita.core.businesslogic.ElementManager;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.SystemException;
import org.posterita.factory.AbstractFactory;
import org.posterita.factory.POSMenuFactory;
import org.posterita.keyname.MenuKeyNamePair;
import org.compiere.model.MWebMenu;
import org.posterita.util.PoManager;


public class MenuManager
{
	
	public static ArrayList<MWebMenu> getMenus(Properties ctx, int roleId) throws SystemException, OperationException
	{
		POSMenuFactory.getFactoryInstance(ctx);
		ArrayList<MWebMenu> menuList = new ArrayList<MWebMenu>();
    	int adRoleId = roleId;
    	
    	String sqlStatement = "";
    	
    	sqlStatement = " select m.* " +
    			" from U_WebMENU m, U_RoleMenu rm " +
    			" where m.U_WebMenu_ID = rm.U_WebMenu_ID "+
                " and M.isActive='Y'"+
    			" and rm.AD_Role_ID = " + adRoleId + 
//    			" and m.AD_Client_ID = " + adClientId + // Menu not dependent on client
    			" order by m.Sequence, m.U_WebMenu_ID";
       	
    	
    	PreparedStatement prepStatement = null;
    	try
    	{
    		prepStatement = DB.prepareStatement(sqlStatement, null);
    		ResultSet rs = prepStatement.executeQuery();
    		
    		
    		while(rs.next())
    		{
    			MWebMenu menu = new MWebMenu(ctx, rs, null);
    			
    			if (!menu.isActive())
				{
    				menu.setMenuLink("#");
				}
    			
    			menuList.add(menu);
    		}
    		rs.close();
    		
    	}
    	catch(SQLException ex)
    	{
    		throw new SystemException(ex.getMessage());
    	}
    	finally
    	{
    		try
    		{
    			prepStatement.close();
    		}
    		catch(Exception e)
    		{
    		}
    		prepStatement = null;
    	}
    	return menuList;
	}
	
    public static ArrayList<MWebMenu> getMenus(Properties ctx) throws SystemException, OperationException
    {
    	int adRoleId = Env.getAD_Role_ID(ctx);
    	return MenuManager.getMenus(ctx, adRoleId);
    }
    
    public static ArrayList<MWebMenu> getMenus(Properties ctx, HttpServletRequest request) throws SystemException, OperationException
    {
    	ArrayList<MWebMenu> menuList = getMenus(ctx);
    	rewriteMenuURL(menuList, request);
    	
    	return menuList;
    }
    
    
    public static MenuItem buildMenuTree(Properties ctx, ArrayList menuList)
    {
    	MenuItem rootMenuItem = MenuItem.getDummyMenuItem(ctx);
    	
    	Iterator menuIter = menuList.iterator();
    	Iterator subIterator;
    	MWebMenu mMenu;
    	MWebMenu sMenu;
    	
    	
    	while(menuIter.hasNext())
    	{
    		mMenu = (MWebMenu)menuIter.next();
   		
    		if(mMenu.getParentMenu_ID() == 0) // Parent Menu
    		{
    			MenuItem mItem = new MenuItem(mMenu);
        		rootMenuItem.addSubMenu(mItem);
    			subIterator = menuList.iterator();
    			
    			while(subIterator.hasNext())
    			{
    				sMenu = (MWebMenu)subIterator.next();
    				if(sMenu.getParentMenu_ID() == mMenu.get_ID())
    				{
    					MenuItem sItem = new MenuItem(sMenu);
    					
    					String menuLink = sItem.getMenuLink();
    					if(! menuLink.startsWith("javascript:"))
    					{
    						sItem.setMenuLink(sItem.getMenuLink() + sItem.getMenuId());
    					}
    					    					
    					//sItem
    					mItem.addSubMenu(sItem);
    				}
    			}
    			//if(mItem.hasSubMenu())
    			mItem.setMenuLink(mItem.getMenuLink() + mItem.getMenuId());
    		}
    	}
    	
    	return rootMenuItem;
    }
    
    public static void saveMenu(Properties ctx, MenuItemBean bean) throws OperationException
    {
    	//when creating a new menu 
    	MWebMenu menu;
    	if (bean.getMenuId().equals(Integer.valueOf(0)))
    	{
    		menu = new MWebMenu(ctx,0,null);
    	}
//    	when editing a menu
    	else
    	{
    		menu = new MWebMenu(ctx,bean.getMenuId().intValue(),null);
    	}
    	
    	menu.setIsActive(bean.getIsActive().booleanValue());
		menu.setDescription(bean.getDescription());
		PoManager.save(menu);
    }
    
    public static MWebMenu createParentMenu(Properties ctx, String menuName, String moduleName, int sequence) throws OperationException
    {
    	//MWebMenu menu = new MWebMenu(ctx, 0, null);
        MWebMenu menu;
        try {
            menu = new MWebMenu(ctx, getMenuId(ctx, menuName), null);
        } catch (SystemException e)
        {
            menu = new MWebMenu(ctx, 0, null);
        }
       
        menu.setMenuLink("GetMenuItemsAction.do?action=getMenuItems&menuId=");        
        menu.setModule(moduleName);
        menu.setPosition(AbstractFactory.MENU_POSITION_LEFT);
        menu.setSequence(new BigDecimal(sequence));
        menu.setName(menuName);
        
        return menu;
    }
    
    public static MWebMenu createSubMenu(Properties ctx,String menuName, String menuLink, String moduleName, int parentId, int sequence) throws OperationException
    {
       return createSubMenu(ctx, menuName, menuLink, moduleName, parentId, sequence, null);
    }
    
    public static MWebMenu createSubMenu(Properties ctx,String menuName, String menuLink, String moduleName, int parentId, int sequence, String category) throws OperationException
    {
        MWebMenu menu;
        try {
            menu = new MWebMenu(ctx, getMenuId(ctx, menuName), null);
        } catch (SystemException e)
        {
            menu = new MWebMenu(ctx, 0, null);
        }
        menu.setParentMenu_ID(parentId);
        
        if(!menuLink.contains("javascript"))
        {
        	if (menuLink.endsWith(".do"))
        		menuLink = menuLink + "?menuId=";
        	else
        		menuLink = menuLink + "&menuId=";
        }
        	
        menu.setMenuLink(menuLink);
        menu.setModule(moduleName);
        menu.setSequence(new BigDecimal(sequence));
        menu.setPosition(AbstractFactory.MENU_POSITION_LEFT);      
        
        menu.setName(menuName);
        menu.setCategory(category);
        return menu;
    }
   
   
    public static ArrayList<MWebMenu> getMenusForOrganisationType(Properties ctx) throws OperationException
    {
    	
    	// Creating the menus first.
//    	MenuFactory factory = MenuFactory.getFactoryInstance(ctx);
    	
    	ArrayList<MWebMenu> menuList = new ArrayList<MWebMenu>();
    
    	String sqlStatement = "";
    	
    	sqlStatement = "select * " +
    			"from U_WEBMENU m where " + //m.AD_Client_ID = " + adClientId +
                " m.AD_CLIENT_ID=0"+
                " and m.AD_ORG_ID=0" +
                " and m.isactive = 'Y'";
    			
//    	sqlStatement += " and m." + OrganisationManager.getOrganisationSqlStatement(ctx);
    	
    	sqlStatement += " order by m.U_WebMenu_ID";
    	
    	System.out.println(sqlStatement);
    	
    	PreparedStatement prepStatement = null;
    	ResultSet rs = null;
    	try
    	{
    		prepStatement = DB.prepareStatement(sqlStatement, null);
    		rs = prepStatement.executeQuery();
    		
    		while(rs.next())
    		{
    			// setting the link name to be either car / bike
    			//name = formatMenuName(ctx, name);
    			
    			MWebMenu menu = new MWebMenu(ctx, rs, null);
    			menuList.add(menu);
    		}
    		rs.close();
    		
    	}
    	catch(SQLException ex)
    	{
    		throw new OperationException("Could not retrieve menu list with sql: " + sqlStatement, ex);
    	}
    	finally
    	{
    		
    		
    		DB.close(rs, prepStatement);
    		
    		rs = null;
    		prepStatement = null;
    	}
    	return menuList;    	
    }
    
    
    public static int[] getDefaultMenus(Properties ctx) throws OperationException
    {
    	String whereClause = "AD_Client_ID=0 and AD_Org_ID=0 ";
    	whereClause += " and Position='TOP' and ParentMenu_ID is null";
    	
    	int menuIds[] = MWebMenu.getAllIDs(MWebMenu.Table_Name, whereClause, null);
    	
    	if(menuIds == null)
    		throw new OperationException("Could not retrieve Default Menus, Where Clause: " + whereClause);
    	
    	return menuIds;
    }
    
    public static int[] getMenuIdForOrganisation(Properties ctx) throws OperationException
    {
    	int adClientId = Env.getAD_Client_ID(ctx);
    	
    	String whereClause = "AD_CLIENT_ID=" + adClientId;
    	
    	int menuIds[] = MWebMenu.getAllIDs(MWebMenu.Table_Name, whereClause, null);
    	
    	return menuIds;
    }
    
    public static int[] getMenuIdForSuperUser(Properties ctx) throws OperationException
    {
    	int adClientId = Env.getAD_Client_ID(ctx);
    	int adOrgId = Env.getAD_Org_ID(ctx);
    	    	    	
    	String whereClause = "AD_CLIENT_ID =" + adClientId + " and AD_ORG_ID = " + adOrgId + " and ISSUPERUSER = 'Y'";
    	
    	int menuIds[] = MWebMenu.getAllIDs(MWebMenu.Table_Name, whereClause, null);
    	
    	return menuIds;
    }    
    
    public static ArrayList getClosableMenus(Properties ctx) throws OperationException 
    {
        String sql = "ad_client_id=" + Env.getAD_Client_ID(ctx)
        				+ " and isClosable='Y'";
        				//+ filter;
        
        ArrayList menuKeyNamePairs;
        
        try 
        {
            menuKeyNamePairs = MenuKeyNamePair.getData(ctx, MWebMenu.Table_Name, sql);
        } 
        catch (SQLException e) 
        {
            throw new OperationException(e.getMessage());
        }
        
        return menuKeyNamePairs;
    }
    
    public static MenuItemBean getMenu(Properties ctx, int menuId)
    {
    	MWebMenu menu = new MWebMenu(ctx, menuId, null);
        
        MenuItemBean bean = new MenuItemBean();
        bean.setDescription(menu.getDescription());
        bean.setMenuId(menu.get_ID());
        bean.setIsActive(menu.isActive());
        
        return bean;
    }
    
    public static MenuItemBean editMenu(Properties ctx, MenuItemBean bean) throws OperationException
    {
    	MWebMenu menu = new MWebMenu(ctx, bean.getMenuId(), null);
        menu.setDescription(bean.getDescription());
        menu.setIsActive(bean.getIsActive());
        PoManager.save(menu);
        
        bean.setName(menu.getName());
        
        return bean;
    }
    
    public static int[] getMenus(Properties ctx,String module)
    {
    	int ad_client_id = Env.getAD_Client_ID(ctx);
    	int ad_org_id = Env.getAD_Org_ID(ctx);
    	
    	String whereClause = " and ad_client_id = "+ ad_client_id +
    	" and ad_org_id = "+ ad_org_id +" and isactive = 'Y'";
    	
    	return MWebMenu.getAllIDs(MWebMenu.Table_Name,whereClause,null);
    }

    
    public static boolean getRoleMenuAccess(Properties ctx,String role) throws OperationException
    {
       int id =  POSMenuFactory.getFactoryInstance(ctx).get(ctx,role).get_ID();
       
       String sql="select AD_ROLE_ID from U_ROLEMENU where U_WEBMENU_ID="+id;
       boolean access=false;
       int currentRoleId=Env.getAD_Role_ID(ctx);
       ResultSet rs = null;
       PreparedStatement pstmt = DB.prepareStatement(sql,null);
       try
       {
           rs = pstmt.executeQuery();
           
           while(rs.next())
           {
              if(rs.getInt(1)==currentRoleId)
              {
                  access=true;
                  break;
              }
              
           }
           
           rs.close();
       } 
       catch (SQLException e) 
       {
        throw new OperationException(e);
       }
       finally
       {
    	   DB.close(rs, pstmt);
    	   rs = null;
    	   pstmt = null;
       }
       
       return access;
    }
    
    public static int getMenuId(Properties ctx, String key) throws SystemException, OperationException
    {
        
        String sqlStatement = "select u_webmenu_id from u_webmenu where lower(name)='" + key.toLowerCase() + "'";
        int menu_id = 0;
        
        PreparedStatement prepStatement = null;
        try
        {
            prepStatement = DB.prepareStatement(sqlStatement, null);
            ResultSet rs = prepStatement.executeQuery();
            
            
            if(rs.next())
            {
                menu_id = rs.getInt(1);
            }
            rs.close();
            
        }
        catch(SQLException ex)
        {
            throw new SystemException(ex.getMessage());
        }
        finally
        {
            try
            {
                prepStatement.close();
            }
            catch(Exception e)
            {
            }
            prepStatement = null;
        }
        return menu_id;
    }
    
    public static String getMainMenus(Properties ctx, HttpServletRequest request)
   {
       ArrayList mainMenuList =  (ArrayList) request.getSession().getAttribute(Constants.TOP_MENUS);
       String mainMenu = "";
       Integer menuId = 0;
       String styleClass = "";
       int count = 0; 
       
       //Check which main menu link has been clicked so as to set style
       if(request.getParameter("menuId") != null)
       {
           menuId = Integer.valueOf(request.getParameter("menuId"));
       }
                    
       if(mainMenuList != null)
       {
           Iterator itr = mainMenuList.iterator(); 
           StringWriter sw = new StringWriter();
           PrintWriter out = new PrintWriter(sw);
           ElementBean elementBean = null;
                                 
           while(itr.hasNext())
           {                
               MenuItem menuItem = (MenuItem) itr.next();
               elementBean = ElementManager.getMsg(ctx, menuItem.getName());      
                            
               if(menuId.compareTo(menuItem.getMenuId()) == 0 || (menuId == 0 && count == 0))
               {
                   styleClass = "currentpage";
               }
               else
               {
                   styleClass = "";
               }
                    
               out.print("<ul>");
               out.print("<li id=\"mainMenu" + count + "\" class=\"" + styleClass + "\">");
               out.print("<a href=\"" + menuItem.getMenuLink() + "\">");
               out.print("<span>");
               out.print(elementBean.getName());
               out.print("</span>");
               out.print("</a>");
               out.print("</li>");
               out.print("</ul>");
               
               count ++;
           }
           
           out.flush();
           
           mainMenu = sw.toString();
           request.getSession().setAttribute(Constants.MAIN_MENUS, mainMenu);
           
       }
       
       return mainMenu;
   }
    
   /**
    * Rewrite all the URLs. Adds session id if not present
    * @param menuList
    * @param request
    */ 
   public static void rewriteMenuURL(ArrayList<MWebMenu> menuList, HttpServletRequest request)
   {
	   HttpSession session = request.getSession();       
       String sessionID = session.getId();
       
       for(MWebMenu menu : menuList)
       {
    	   String link = menu.getMenuLink();
			
			int index = link.indexOf("?");		
			if(index != -1)
			{
				String part1 = link.substring(0, index);
				String part2 = link.substring(index);
				
				link = part1 + ";jsessionid=" + sessionID + part2;
				
				menu.setMenuLink(link);
			}
       }
   }
	

   public static Boolean isCashSalesAllowed(Properties ctx, int roleId) throws Exception
   {
       boolean isCashSalesAllowed = false;
       ArrayList<MWebMenu> roleMenus = getMenus(ctx, roleId);
       
       if(roleMenus != null)
       {
           Iterator itr = roleMenus.iterator();
           
           while(itr.hasNext())
           {
               MWebMenu webMenu = (MWebMenu) itr.next();
               
               if(webMenu.getName().equalsIgnoreCase("smenu.cash.sales"))
               {
                   isCashSalesAllowed = true;
               }
           }
       }
       return isCashSalesAllowed;
   }
}	
	

