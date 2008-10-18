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
package org.posterita.model;

import org.compiere.model.X_U_WebMenu;
import org.posterita.exceptions.OperationException;


public class UDIU_WebMenu extends UDIPO
{
    public UDIU_WebMenu(X_U_WebMenu menu)
    {
        super(menu);
    }

	public String getImageLink()
	{
		return getMenu().getImageLink();
	}

    public X_U_WebMenu getMenu()
    {
        return (X_U_WebMenu) getPO();
    }

	public int getMenuId()
	{
		return getMenu().get_ID();
	}

	public String getMenuLink()
	{
		return getMenu().getMenuLink();
	}

	public String getModule()
	{
		return getMenu().getModule();
	}

	public String getName()
	{
		return getMenu().getName();
	}
    
    public int getParentMenuId()
    {
        return getMenu().getParentMenu_ID();
    }

	public String getPosition()
	{
		return getMenu().getPosition();
	}

	public boolean isActive()
	{
		return getMenu().isActive();
	}

	public void setActive(boolean isActive)
	{
		getMenu().setIsActive(isActive);
	}

    public void setImageLink(String imageLink)
    {
    	getMenu().setImageLink(imageLink);
    }
	
    public void setParentMenuId(int parentMenuId)
    {
    	getMenu().setParentMenu_ID(parentMenuId);
    }
    
    public void setPosition(String position)
    {
    	getMenu().setPosition(position);
    }
    
    public String getDescription()
    {
        return getMenu().getDescription();
    }
    
    public String getCategory()
    {
        return getMenu().getCategory();
    }
    
    public void setCategory(String category)
    {
        getMenu().setCategory(category);
    }
    
    public void setName(String name)
	{
		getMenu().setName(name);
	}
    
    public void save() throws OperationException
    {
    	super.save();
    }
    
}
