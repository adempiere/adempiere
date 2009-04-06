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
 * Created on Jul 25, 2005 by vishee
 */

package org.posterita.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.posterita.factory.AbstractFactory;
import org.compiere.model.MWebMenu;

public class MenuItem
{
	
	protected String menuLink;
	protected MWebMenu menu;
	protected ArrayList<MenuItem> children;
	protected int menuId;
	protected boolean available;

	//dangerous constructor since nothing else is initialised.
	private MenuItem(int menuId)
	{
		this.menuId = menuId;
	}
	
	public MenuItem(MWebMenu menu)
	{
		this.menu = menu; 
		children = new ArrayList<MenuItem>();
		menuLink = menu.getMenuLink();
		menuId = menu.get_ID();
	}
	
	public ArrayList getSubMenus()
	{
		return children;
	}
	
	public Iterator getSubMenusIterator()
	{
		return children.iterator();
	}
	
	public void addSubMenu(MenuItem subMenu)
	{
		children.add(subMenu);
	}
	
	public boolean hasSubMenu()
	{
		return (!children.isEmpty());
	}
	
	public boolean getHasSubMenu()
	{
		return (!children.isEmpty());
	}
	
	public boolean isActive()
	{
		return menu.isActive();
	}
	
	public String getImageLink()
	{
		return menu.getImageLink();
	}
	
	public String getMenuLink()
	{
		return menuLink;
	}
	
	public int getMenuId()
	{
		return menuId;
	}
	
	public String getModule()
	{
		return menu.getModule();
	}
	
	public int getParentMenuId()
	{
		return menu.getParentMenu_ID();
	}
	
	public String getPosition()
	{
		return menu.getPosition();
	}
	
	public void setMenuLink(String link)
	{
		menuLink = link;
	}
	
	public String getDescription()
	{
	    return menu.getDescription();
	}
	
	public boolean AmIaParent(int menuId)
	{
		ArrayList list = getAllChildren();
		MenuItem item = new MenuItem(menuId);
		return list.contains(item);
	}
	
	public ArrayList<MenuItem> getBreadCrumb(int menuId)
	{
		ArrayList<MenuItem> list = new ArrayList<MenuItem>();
		
		if (children.size() == 0)
			return list;
		
		Iterator iter = children.iterator();
		
		MenuItem me = new MenuItem(menuId);
		
		while (iter.hasNext())
		{
			MenuItem item = (MenuItem) iter.next();
			
			if (item.AmIaParent(menuId) || item.equals(me))
			{
				list.add(item);
				list.addAll(item.getBreadCrumb(menuId));
			}
			
		}

		return list;
	}
	
	public String convert(int menuId)
	{
		ArrayList breadCrumb = getBreadCrumb(menuId);
		Iterator iter = breadCrumb.iterator();
		
		String bread = "<img src=\"images/link_icon.gif\" alt=\"\" border=\"0\"> &nbsp;";
		while(iter.hasNext())
		{
			MenuItem item = (MenuItem) iter.next();
			bread = bread + item.getName();
		}
		
		return bread;
	}
	
	public ArrayList<MenuItem> getAllChildren()
	{
		ArrayList<MenuItem> list = new ArrayList<MenuItem>();
		
		if (children.size() == 0)
			return list;
		
		Iterator iter = children.iterator();
		
		while (iter.hasNext())
		{
			MenuItem item = (MenuItem) iter.next();
			
			ArrayList<MenuItem> temp = item.getAllChildren();
			list.add(item);
			list.addAll(temp);
		}

		return list;
	}
	
	


	public static MenuItem getDummyMenuItem(Properties ctx)
	{
		MWebMenu menu = new MWebMenu(ctx, 0, null);
		menu.setName("Dummy Root Menu");
		menu.setIsActive(false);
		
		MenuItem retMenu = new MenuItem(menu);

		return retMenu;
	}
	
	
	
	public ArrayList getTopMenus()
	{
		return getMenus(AbstractFactory.MENU_POSITION_TOP);
	}
		
	public ArrayList<MenuItem> getLeftMenus()
	{
		return getMenus(AbstractFactory.MENU_POSITION_LEFT);

	}							   					
	
	private ArrayList<MenuItem> getMenus(String position)
	{
		ArrayList<MenuItem> menuList = new ArrayList<MenuItem>();
		
		ArrayList menus = children;
		
		Iterator parentIter = menus.iterator(); 
		
		while(parentIter.hasNext())
		{
			MenuItem menuItem = (MenuItem)parentIter.next();
			if(menuItem.getPosition().compareTo(position) == 0)
			{
				menuList.add(menuItem);
			}
		}	
		
		return menuList;		
	}
	
	// not good.
	public ArrayList getSubMenus(int parentId)
	{
		ArrayList subMenus = new ArrayList();
		
		Iterator pIter = children.iterator();
		MenuItem mItem;
		
		while(pIter.hasNext())
		{
			mItem = (MenuItem)pIter.next();
			
			if(mItem.getMenuId() == parentId)
			{
				subMenus = mItem.children;
				
				return subMenus;
			}
		}
		
		return subMenus;
	}
	
	public boolean equals(Object obj)
	{
		MenuItem item = (MenuItem) obj;
		
		if ( obj == null)
		   	return false;
		 
		if (this.getMenuId() == item.getMenuId())
			return true;
		else
			return false;
	}

	public boolean isAvailable()
	{
		return available;
	}

	public void setAvailable(boolean available)
	{
		this.available = available;
	}
	

	public void setCategory(String category)
	{
		menu.setCategory(category);
	}
	

/*	public String getBreadCrumbMenu(Properties ctx, Integer menuId)
	{
		
		if (menuId.intValue() != 0)
		{
			X_U_WebMenu menu = new X_U_WebMenu(ctx, menuId.intValue(), null);
			
			breadcrumb = breadcrumb + "<a href=" + menu.getMenuLink() + ">" + menu.getName() + "</a>";
					
			if (menu.getParentMenu_ID() == 0)
				return breadcrumb;

			getBreadCrumbMenu(ctx, Integer.valueOf(menu.getParentMenu_ID()));
		}
		
		return breadcrumb;
	} 
*/	
/*	public static String printTopMenu(MenuItem menuItem)
	{
		Iterator parentIter = menuItem.getSubMenusIterator();
		String htmlCode = "";
		htmlCode += "<table><tr>";
		MenuItem pItem;
		
		while(parentIter.hasNext())
		{
			pItem = (MenuItem)parentIter.next();
			if(pItem.getPosition() == MenuFactory.MENU_POSITION_TOP)
			{
				
			}
		}
		
		htmlCode += "</tr></table>";
	}*/
	
/*    private Properties ctx;
    private Integer menuId;
    private Integer parentMenuId;
    private String name;
    private String link;
    private MenuItem[] children;
    private String imageLink;
    
    private static final String BREAD_CRUMB_DELIMITER = ">";
    
    public MenuItem(Properties ctx, Integer menuId)
    {
        this.ctx = ctx;
        this.menuId = menuId;
    }
    
    public String print() throws SystemException
    {
        MenuItem[] items = getChildren();
        
        table table = new table();
        
        for (int i = 0; i < items.length; i++)
        {
            MenuItem item = items[i];
            
            tr tr = new tr();
            td td = new td();
            
            img img = new img("images/link_icon.gif");
            img.addAttribute("width","15");
            img.addAttribute("height","15");
            img.addAttribute("border","0");
            img.addAttribute("align","absmiddle");
            img.addAttribute("vspace","5");
            
            a a;
            
            //this is a leaf node
            if (MenuManager.getChildren(ctx, item.getMenuId()).length == 0)
                a  = new a(item.getLink());
            else 
                a  = new a(item.getLink() + item.getMenuId());
            
            a.setClass("left");
            a.addElement(img);
            
            img.setTagText("" + item.getName());
            
            a.addElement(img);
            a.setPrettyPrint(true);
            
            td.addElement(a);
            tr.addElement(td);
            
            table.addElement(tr);
        }     
        
        return table.toString();
    }
    
    public String getBreadCrumb(Integer menuId) throws SystemException, OperationException
    {
        MenuItem item = MenuManager.getParent(ctx, menuId);
        MenuItem childMenuItm = getMenuItem(menuId);
        
        if (item == null)
            return createBreadCrumb(childMenuItm);
        
        String breadCrumb = new String("");
        do
        {	
            breadCrumb = createBreadCrumb(item) + BREAD_CRUMB_DELIMITER + breadCrumb;
            item = MenuManager.getParent(ctx, item.getMenuId());
        }
        while(item!=null);
        
        return breadCrumb;
        
    }
    
    private static String createBreadCrumb(MenuItem item)
    {
        a a = new a(item.getLink() + item.getMenuId());
        a.setTagText(item.getName());
        return a.toString();
    }
    
    public MenuItem[] getMenuJSP() throws SystemException
    {
        this.menuId = null; // make sure to retrieve first level menu
        return getChildren();
    }
    
    public MenuItem[] MenuItems(Integer menuId) throws SystemException
    {
        return MenuManager.getChildren(ctx, menuId);
    }
    
    private MenuItem getMenuItem(Integer menuItemId)
    {
        X_U_Menu menu = new X_U_Menu(ctx, menuItemId.intValue(), null);
        MenuItem menuItem = new MenuItem(ctx, menuItemId);
        menuItem.setLink(menu.getMenuLink());
        menuItem.setName(menu.getName());
        menuItem.setParentMenuId(Integer.valueOf(menu.getParentMenu_ID()));
        return menuItem;
    }
   
    
    protected MenuItem[] getChildren() throws SystemException
    {
        if (children != null)
            return children;
        
        MenuItem[] item = MenuManager.getChildren(ctx, menuId);
        
        children = item;
        
        return children;
    }
    
    
    public Integer getMenuId()
    {
        return menuId;
    }
    
    public void setMenuId(Integer menuId)
    {
        this.menuId = menuId;
    }
    
    public String getLink()
    {
        return link;
    }
    
    public void setLink(String link)
    {
        this.link = link;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    
    public Integer getParentMenuId() 
    {
        return parentMenuId;
    }
    
    public void setParentMenuId(Integer parentMenuId) 
    {
        this.parentMenuId = parentMenuId;
    }
    
    
    //Generating menurole 
     
    public static String getMenu(Properties ctx,Integer menuId) throws SystemException, OperationException
    {
    	
    	String tab = "&nbsp;&nbsp;&nbsp;&nbsp;";
     
        StringBuffer menuStr = new StringBuffer("");
        
        StringBuffer whereClause = new StringBuffer("ad_client_id=" + Env.getAD_Client_ID(ctx) + " and ad_org_id="+ Env.getAD_Org_ID(ctx));
        
        // Modification brought due to Organisation type
        String orgType = OrganisationManager.getOrganisationType(ctx);
        	
        if(orgType == UdiConstants.ORG_TYPE_WHOLESALER)
        		whereClause.append(" and IsWholesaler='Y'");
        else if(orgType == UdiConstants.ORG_TYPE_RETAILER)
        		whereClause.append(" and IsRetailer='Y'");

        // end modification
        
        MenuItem menuItem;
        
        if(menuId == null)
        {       
           whereClause.append(" and parentmenu_id is null");           
        }
        else
        {
           whereClause.append(" and parentmenu_id =" + menuId); 
           
           X_U_Menu menu = new X_U_Menu(ctx,menuId.intValue(), null);           
          
           
           if((MenuManager.getChildren(ctx,menuId).length == 0)   &&
                   (MenuManager.getParent(ctx,menuId) != null))
           {
               	menuStr.append("<tr><td class='label'>");
          		menuStr.append(tab  + menu.getName());
          		menuStr.append("</td><td class='label'>");
          		menuStr.append("<input type='checkbox' name='checkBox' value=" + menuId + ">");
          		menuStr.append("</td></tr>");
           } 	
           else
           {
               	menuStr.append("<tr><td class='contentname'><strong>");
      			menuStr.append(tab  + menu.getName());
      			menuStr.append("</strong></td><td class='contentname'>");
      			menuStr.append("&nbsp;");
      			menuStr.append("</td></tr>");
           }
        }
        
        //getting the child menus
        int[] childId = X_U_Menu.getAllIDs(X_U_Menu.Table_Name,whereClause.toString(),null);
        tab = tab + tab;
        
        for(int i=0; i<childId.length; i++)
        {
            menuStr.append(getMenu(ctx,Integer.valueOf(childId[i])));            
        }       
        
        return menuStr.toString();
    }
    
    public static int[] getAllMenuIDs(Properties ctx,int roleId) throws SystemException
    {       
        ArrayList list = new ArrayList();
        StringBuffer sql = new StringBuffer("SELECT ");
        sql.append("U_MENU_ID FROM U_ROLEMENU ");
        sql.append("WHERE AD_ROLE_ID =");
        sql.append(roleId);
        
        System.out.println("Query for RoleMenu :" + sql);
        
        try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
				list.add(Integer.valueOf(rs.getInt(1)));
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{			
			throw new SystemException(e.getMessage());		    
		}
		
		//Convert to array
		int[] retValue = new int[list.size()];
		for (int i = 0; i < retValue.length; i++)
			retValue[i] = ((Integer)list.get(i)).intValue();
		
		return retValue;       
        
    }
    
    public static ArrayList getMenu(Properties ctx,int roleId) throws SystemException, OperationException
    {
        ArrayList menus = new ArrayList();
        
        int id[] = MenuItem.getAllMenuIDs(ctx,roleId);
        
        for(int i=0;i<id.length;i++)
        {
            X_U_Menu menu = new X_U_Menu(ctx,id[i],null);
            KeyNamePair pair = new KeyNamePair(menu.getID(),menu.getName());
            menus.add(pair);
        }       
        
        return menus;
    }
    
    
    // Modified to accomodate the new menu Style
    
    public String printMenu(String position) throws SystemException
    {
    	String strRet = "";
    	
    	if(position == MenuFactory.MENU_POSITION_LEFT)
    	{
    		MenuItem items[] = MenuManager.getMenusFromPosition(ctx, parentMenuId, position);
    		strRet = printLeftMenu(items);
    	}
    	else if(position == MenuFactory.MENU_POSITION_TOP)
    	{
    		MenuItem items[] = MenuManager.getMenusFromPosition(ctx, parentMenuId, position);
    		strRet = printTopMenu(items);
    	}
    	
    	return strRet;
    }
    
    protected String printLeftMenu(MenuItem items[]) throws SystemException
    {
        
        table table = new table();
        
        for (int i = 0; i < items.length; i++)
        {
            MenuItem item = items[i];
            
            tr tr = new tr();
            td td = new td();
            
            img img = new img("images/link_icon.gif");
            img.addAttribute("width","15");
            img.addAttribute("height","15");
            img.addAttribute("border","0");
            img.addAttribute("align","absmiddle");
            img.addAttribute("vspace","5");
            
            a a;
            
            //this is a leaf node
            if (MenuManager.getChildren(ctx, item.getMenuId()).length == 0)
                a  = new a(item.getLink());
            else 
                a  = new a(item.getLink() + item.getMenuId());
            
            a.setClass("left");
            a.addElement(img);
            
            img.setTagText("" + item.getName());
            
            a.addElement(img);
            a.setPrettyPrint(true);
            
            td.addElement(a);
            tr.addElement(td);
            
            table.addElement(tr);
        }     
        
        return table.toString();
    }
    
    protected String printTopMenu(MenuItem items[]) throws SystemException
    {
        table table = new table();
        tr tr = new tr();
        
        for (int i = 0; i < items.length; i++)
        {
            MenuItem item = items[i];
            
            td td = new td();
                      
            a a;
            
            //this is a leaf node
            if (MenuManager.getChildren(ctx, item.getMenuId()).length == 0)
                a  = new a(item.getLink());
            else 
                a  = new a(item.getLink() + item.getMenuId());
            
            a.setClass("left");
           
            img img = null;
            
            if(imageLink != null && imageLink.length() > 0)
            {
            	img = new img(imageLink);
            	img.addAttribute("width","48");
            	img.addAttribute("height","48");
            	img.addAttribute("border","0");
            	img.addAttribute("align","absmiddle");
            	img.addAttribute("vspace","5");
                img.setTagText("" + item.getName());
                a.addElement(img);
            }
            else
            {
            	a.setTagText(item.getName());
            }

            a.setPrettyPrint(true);
            
            td.addElement(a);
            td.setHeight(48);
            tr.addElement(td);
        }
        table.setCellPadding(10);
        table.setBorder(2);
        table.setBorderColor("#CC0000");
        table.setClass("header");
        table.addElement(tr);        
        return table.toString();
    }

	public String getImageLink()
	{
		return imageLink;
	}

	public void setImageLink(String imageLink)
	{
		this.imageLink = imageLink;
	}
*/
	public String getCategory()
	{
		//return ElementManager.getMsg(menu.getCtx(),menu.getCategory()).getName();
		return menu.getCategory();
	}
	
	public String getName()
	{
		//return ElementManager.getMsg(menu.getCtx(),menu.getName()).getName();
		return menu.getName();
	}
	
}
