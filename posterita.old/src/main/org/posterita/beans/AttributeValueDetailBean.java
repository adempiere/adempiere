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
 * Created on Feb 3, 2006 by praveen
 *
 */
package org.posterita.beans;

public class AttributeValueDetailBean extends UDIBean
{  
    public String getDescription() 
    {
        return description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }
    
    public Integer getId() 
    {
        return id;
    }
    
    public void setId(Integer id) 
    {
        this.id = id;
    }
    
    public String getName() 
    {
        return name;
    }
    
    public void setName(String name) 
    {
        this.name = name;
    }
    
    public Boolean getIsActive() 
    {
        return isActive;
    }
    
    public void setActive(Boolean isActive) 
    {
        this.isActive = isActive;
    }
    
	public Integer getAttributeId()
	{
		return attributeId;
	}
	
	public void setAttributeId(Integer attributeId)
	{
		this.attributeId = attributeId;
	}
	
	public Integer getAttributeValueId()
	{
		return attributeValueId;
	}
	
	public void setAttributeValueId(Integer attributeValueId)
	{
		this.attributeValueId = attributeValueId;
	}
	
	public String getNewName()
	{
		return newName;
	}
	
	public void setNewName(String newName)
	{
		this.newName = newName;
	}
}
