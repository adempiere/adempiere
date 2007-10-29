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

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.compiere.model.X_U_WebMenu;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.posterita.beans.MenuItemBean;
import org.posterita.beans.OrgBean;
import org.posterita.core.MenuItem;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.SystemException;
import org.posterita.factory.AbstractFactory;
import org.posterita.factory.POSMenuFactory;
import org.posterita.keyname.MenuKeyNamePair;
import org.posterita.model.MMenu;
import org.posterita.model.U_WebMenu;


public class MenuManager
{
	
	private static boolean isTrue(String val)
	{
		if(val.compareTo("Y") == 0)
			return true;
		return false;
	}
	
	
	public static ArrayList<U_WebMenu> getMenus(Properties ctx, int roleId) throws SystemException, OperationException
	{
		POSMenuFactory.getFactoryInstance(ctx);
		ArrayList<U_WebMenu> menuList = new ArrayList<U_WebMenu>();
    	int adRoleId = roleId;
    	
    	String sqlStatement = "";
    	
    	sqlStatement = " select m.U_WebMenu_ID, " +
    			" m.Name, m.MenuLink, " +
    			" m.Module, m.ParentMenu_ID, " +
    			" m.isActive, " +
    			" m.ImageLink, m.Position, m.description " +
    			" from U_WebMENU m, U_RoleMenu rm " +
    			" where m.U_WebMenu_ID = rm.U_WebMenu_ID "+
                " and M.isActive='Y'"+
    			" and rm.AD_Role_ID = " + adRoleId + 
//    			" and m.AD_Client_ID = " + adClientId + // Menu not dependent on client
    			" order by m.Sequence, m.U_WebMenu_ID";
       	
    	System.out.println("Query for menu manager :" + sqlStatement);
    	
    	PreparedStatement prepStatement = null;
    	try
    	{
    		prepStatement = DB.prepareStatement(sqlStatement, null);
    		ResultSet rs = prepStatement.executeQuery();
    		int menuId;
    		String name;
    		String menuLink;
    		String module;
    		int parentMenuId;
    		boolean isActive;
    		String imageLink;
    		String position;
    		String boolVal;
    		String description;
    		
    		while(rs.next())
    		{
    			menuId = rs.getInt(1);
    			name = rs.getString(2);
    			menuLink = rs.getString(3);
    			module = rs.getString(4);
    			parentMenuId = rs.getInt(5);
    			
    			boolVal = rs.getString(6);
    			isActive = MenuManager.isTrue(boolVal);
    			
    			imageLink = rs.getString(7);
    			position = rs.getString(8);
    			
    			description = rs.getString(9);
    			
    			//setting the link name to be either car / bike
    			name = formatMenuName(ctx, name);
    			
    			MMenu menu = new MMenu(ctx, menuId, null);
    			menu.setName(name);
    			
    			if (!isActive)
    			    menu.setMenuLink("#");
    			else
    			    menu.setMenuLink(menuLink);
    			menu.setModule(module);
    			menu.setParentMenu_ID(parentMenuId);
    			menu.setIsActive(isActive);
    			menu.setImageLink(imageLink);
    			menu.setPosition(position);
    			menu.setDescription(description);
    			
    			U_WebMenu udiMenu = new U_WebMenu(menu);
    			
    			menuList.add(udiMenu);
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
	
    public static ArrayList getMenus(Properties ctx) throws SystemException, OperationException
    {
    	int adRoleId = Env.getAD_Role_ID(ctx);
    	return MenuManager.getMenus(ctx, adRoleId);
    }
    
    
    public static MenuItem buildMenuTree(Properties ctx, ArrayList menuList)
    {
    	MenuItem rootMenuItem = MenuItem.getDummyMenuItem(ctx);
    	
    	Iterator menuIter = menuList.iterator();
    	Iterator subIterator;
    	U_WebMenu mMenu;
    	U_WebMenu sMenu;
    	
    	
    	while(menuIter.hasNext())
    	{
    		mMenu = (U_WebMenu)menuIter.next();
   		
    		if(mMenu.getParentMenuId() == 0) // Parent Menu
    		{
    			MenuItem mItem = new MenuItem(mMenu);
        		rootMenuItem.addSubMenu(mItem);
    			subIterator = menuList.iterator();
    			
    			while(subIterator.hasNext())
    			{
    				sMenu = (U_WebMenu)subIterator.next();
    				if(sMenu.getParentMenuId() == mMenu.getMenuId())
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
    	X_U_WebMenu menu;
    	U_WebMenu selectmenu;
    	if (bean.getMenuId().equals(Integer.valueOf(0)))
    	{
    		menu = new X_U_WebMenu(ctx,0,null);
    	}
//    	when editing a menu
    	else
    	{
    		menu = new X_U_WebMenu(ctx,bean.getMenuId().intValue(),null);
    	}
    	
    	menu.setIsActive(bean.getIsActive().booleanValue());
		menu.setDescription(bean.getDescription());
		selectmenu = new U_WebMenu(menu);
    	selectmenu.save();
    }
    
    public static U_WebMenu createParentMenu(Properties ctx, String menuName, String moduleName, int sequence) throws OperationException
    {
    	X_U_WebMenu menu = new X_U_WebMenu(ctx, 0, null);
       
        menu.setMenuLink("GetMenuItemsAction.do?action=getMenuItems&menuId=");        
        menu.setModule(moduleName);
        menu.setPosition(AbstractFactory.MENU_POSITION_LEFT);
        menu.setSequence(new BigDecimal(sequence));
        menu.setName(menuName);
        
        U_WebMenu udiMenu = new U_WebMenu(menu);
        
        return udiMenu;
    }
    
    @Deprecated
    public static U_WebMenu createParentMenu(Properties ctx, String menuName, String moduleName, boolean forRetailer, boolean forWholesaler, boolean forShipper) throws OperationException
    {
    	X_U_WebMenu menu = new X_U_WebMenu(ctx, 0, null);
        menu.setName(menuName);
        menu.setMenuLink("GetMenuItemsAction.do?action=getMenuItems&menuId=");
        
        menu.setModule(moduleName);
        menu.setPosition(AbstractFactory.MENU_POSITION_LEFT);
        
        U_WebMenu udiMenu = new U_WebMenu(menu);
        
        return udiMenu;
    }
    
    public static U_WebMenu createSubMenu(Properties ctx,String menuName, String menuLink, String moduleName, int parentId, int sequence) throws OperationException
    {
       return createSubMenu(ctx, menuName, menuLink, moduleName, parentId, sequence, null);
    }
    
    public static U_WebMenu createSubMenu(Properties ctx,String menuName, String menuLink, String moduleName, int parentId, int sequence, String category) throws OperationException
    {
    	X_U_WebMenu menu = new X_U_WebMenu(ctx, 0, null);        
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
        
        U_WebMenu udiMenu = new U_WebMenu(menu);
        return udiMenu;
    }
    
    @Deprecated
    public static U_WebMenu createSubMenu(Properties ctx,String menuName, String menuLink, String moduleName, int parentId, boolean closable, boolean forRetailer, boolean forWholesaler, boolean forShipper) throws OperationException
    {
    	X_U_WebMenu menu = new X_U_WebMenu(ctx, 0, null);
        menu.setName(menuName);
        menu.setParentMenu_ID(parentId);
        
        if (menuLink.endsWith(".do"))
        	menuLink = menuLink + "?menuId=";
        else
        	menuLink = menuLink + "&menuId=";
        	
        menu.setMenuLink(menuLink);
        menu.setModule(moduleName);
        
        menu.setPosition(AbstractFactory.MENU_POSITION_LEFT);
        
        U_WebMenu udiMenu = new U_WebMenu(menu);
        return udiMenu;
    }
    
    public static ArrayList<U_WebMenu> getMenusForOrganisationType(Properties ctx) throws OperationException
    {
    	
    	// Creating the menus first.
//    	MenuFactory factory = MenuFactory.getFactoryInstance(ctx);
    	
    	
    	
    	ArrayList<U_WebMenu> menuList = new ArrayList<U_WebMenu>();
    
    	
    	String sqlStatement = "";
    	
    	sqlStatement = "select m.U_Menu_ID, " +
    			"m.Name, m.MenuLink, " +
    			"m.Module, m.ParentMenu_ID, " +
    			"m.isActive, " +
    			"m.ImageLink, m.Position " +
    			"from U_MENU m where " + //m.AD_Client_ID = " + adClientId +
                " m.AD_CLIENT_ID=0"+
                " and m.AD_ORG_ID=0" +
                " and m.isactive = 'Y'";
    			
//    	sqlStatement += " and m." + OrganisationManager.getOrganisationSqlStatement(ctx);
    	
    	sqlStatement += " order by m.U_Menu_ID";
    	
    	System.out.println(sqlStatement);
    	
    	PreparedStatement prepStatement = null;
    	
    	try
    	{
    		prepStatement = DB.prepareStatement(sqlStatement, null);
    		ResultSet rs = prepStatement.executeQuery();
    		int menuId;
    		String name;
    		String menuLink;
    		String module;
    		int parentMenuId;
    		boolean isActive;
    		String imageLink;
    		String position;
    		String boolVal;
    		
    		while(rs.next())
    		{
    			menuId = rs.getInt(1);
    			name = rs.getString(2);
    			menuLink = rs.getString(3);
    			module = rs.getString(4);
    			parentMenuId = rs.getInt(5);
    			
    			boolVal = rs.getString(6);
    			isActive = MenuManager.isTrue(boolVal);
    			
    			imageLink = rs.getString(7);
    			position = rs.getString(8);
    			
    			// setting the link name to be either car / bike
    			name = formatMenuName(ctx, name);
    			
    			X_U_WebMenu menu = new X_U_WebMenu(ctx, menuId, null);
    			menu.setName(name);
    			menu.setMenuLink(menuLink);
    			menu.setModule(module);
    			menu.setParentMenu_ID(parentMenuId);
    			menu.setIsActive(isActive);
    			menu.setImageLink(imageLink);
    			menu.setPosition(position);
    			
    			U_WebMenu udiMenu = new U_WebMenu(menu);
    			
    			menuList.add(udiMenu);
    		}
    		rs.close();
    		
    	}
    	catch(SQLException ex)
    	{
    		throw new OperationException("Could not retrieve menu list with sql: " + sqlStatement, ex);
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
    
    
    public static int[] getDefaultMenus(Properties ctx) throws OperationException
    {
    	String whereClause = "AD_Client_ID=0 and AD_Org_ID=0 ";
    	whereClause += " and Position='TOP' and ParentMenu_ID is null";
    	
    	int menuIds[] = MMenu.getAllIDs(MMenu.Table_Name, whereClause, null);
    	
    	if(menuIds == null)
    		throw new OperationException("Could not retrieve Default Menus, Where Clause: " + whereClause);
    	
    	return menuIds;
    }
    
    public static int[] getMenuIdForOrganisation(Properties ctx) throws OperationException
    {
    	int adClientId = Env.getAD_Client_ID(ctx);
    	
    	String whereClause = "AD_CLIENT_ID=" + adClientId;
    	
    	int menuIds[] = X_U_WebMenu.getAllIDs(X_U_WebMenu.Table_Name, whereClause, null);
    	
    	return menuIds;
    }
    
    public static int[] getMenuIdForSuperUser(Properties ctx) throws OperationException
    {
    	int adClientId = Env.getAD_Client_ID(ctx);
    	int adOrgId = Env.getAD_Org_ID(ctx);
    	    	    	
    	String whereClause = "AD_CLIENT_ID =" + adClientId + " and AD_ORG_ID = " + adOrgId + " and ISSUPERUSER = 'Y'";
    	
    	int menuIds[] = X_U_WebMenu.getAllIDs(X_U_WebMenu.Table_Name, whereClause, null);
    	
    	return menuIds;
    }    
    
    // TODO make use of regex to replace the name
    public static String formatMenuName(Properties ctx, String menuName) throws OperationException
    {
    	int index = menuName.indexOf("%s");
    	if(index == -1)
    		return menuName;
    	
    	int adOrgId = Env.getAD_Org_ID(ctx);
    	
    	OrgBean organisationDet = OrganisationManager.getOrganisation(ctx, adOrgId);
    	if(organisationDet == null)
    		return menuName;
    	
    	String menuFirstPart = menuName.substring(0, index);
    	String menuLastPart = menuName.substring(index + 2);
    	String menuMidPart = "Car";
    	
    	if(organisationDet.getIsAutomobile().booleanValue()) 
    		menuMidPart = "Car";
    	else if (organisationDet.getIsMotorcycle().booleanValue())
    		menuMidPart = "Bike";
    	// Can add other type here
    	
    	String retMenuName = menuFirstPart + menuMidPart + menuLastPart;    	
    	
    	return retMenuName;
    }
    
    public static ArrayList getClosableMenus(Properties ctx) throws OperationException 
    {
        String sql = "ad_client_id=" + Env.getAD_Client_ID(ctx)
        				+ " and isClosable='Y'";
        				//+ filter;
        
        ArrayList menuKeyNamePairs;
        
        try 
        {
            menuKeyNamePairs = MenuKeyNamePair.getData(ctx, X_U_WebMenu.Table_Name, sql);
        } 
        catch (SQLException e) 
        {
            throw new OperationException(e.getMessage());
        }
        
        return menuKeyNamePairs;
    }
    
    public static MenuItemBean getMenu(Properties ctx, int menuId)
    {
    	X_U_WebMenu menu = new X_U_WebMenu(ctx, menuId, null);
        
        MenuItemBean bean = new MenuItemBean();
        bean.setDescription(menu.getDescription());
        bean.setMenuId(Integer.valueOf(menu.get_ID()));
        bean.setIsActive(Boolean.valueOf(menu.isActive()));
        
        
        return bean;
    }
    
    public static MenuItemBean editMenu(Properties ctx, MenuItemBean bean)
    {
    	X_U_WebMenu menu = new X_U_WebMenu(ctx, bean.getMenuId().intValue(), null);
        menu.setDescription(bean.getDescription());
        menu.setIsActive(bean.getIsActive().booleanValue());
        menu.save();
        
        bean.setName(menu.getName());
        
        return bean;
    }
    
    public static int[] getMenus(Properties ctx,String module)
    {
    	int ad_client_id = Env.getAD_Client_ID(ctx);
    	int ad_org_id = Env.getAD_Org_ID(ctx);
    	
    	String whereClause = " and ad_client_id = "+ ad_client_id +
    	" and ad_org_id = "+ ad_org_id +" and isactive = 'Y'";
    	
    	return X_U_WebMenu.getAllIDs(X_U_WebMenu.Table_Name,whereClause,null);
    }

    
    public static boolean getRoleMenuAccess(Properties ctx,String role) throws OperationException
    {
       int id =  POSMenuFactory.getFactoryInstance(ctx).get(ctx,role).getID();
       
       String sql="select AD_ROLE_ID from U_ROLEMENU where U_MENU_ID="+id;
       boolean access=false;
       int currentRoleId=Env.getAD_Role_ID(ctx);
       
       PreparedStatement pstmt = DB.prepareStatement(sql,null);
       try
       {
           ResultSet rs = pstmt.executeQuery();
           
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
           try 
           {
               pstmt.close();
           } 
           catch (SQLException e) 
            {
                
            }
       }
       
       return access;
    }
    
   
	
}	
	

