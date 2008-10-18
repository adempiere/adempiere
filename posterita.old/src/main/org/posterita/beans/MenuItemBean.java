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
package org.posterita.beans;


public class MenuItemBean extends UDIBean
{
    

    public Integer getMenuId()
	{
		return menuId;
	}
	public void setMenuId(Integer menuId)
	{
		this.menuId = menuId;
	}
	public String getMenuItemName()
	{
		return menuItemName;
	}
	public void setMenuItemName(String menuItemName)
	{
		this.menuItemName = menuItemName;
	}
	public String getMenuLink()
	{
		return menuLink;
	}
	public void setMenuLink(String menuLink)
	{
		this.menuLink = menuLink;
	}
	public Integer getParentMenuId()
	{
		return parentMenuId;
	}
	public void setParentMenuId(Integer parentMenuId)
	{
		this.parentMenuId = parentMenuId;
	}
	
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}

	public Boolean getIsActive()
	{
		return isActive;
	}
	public void setIsActive(Boolean isActive)
	{
		this.isActive = isActive;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
        this.name = name;
	}
	public Boolean getIsClosable()
	{
		return isClosable;
	}
	public void setIsClosable(Boolean isClosable)
	{
		this.isClosable = isClosable;
	}

	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
}
