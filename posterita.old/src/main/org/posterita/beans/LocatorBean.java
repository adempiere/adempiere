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
 * Created on 26-Jul-2005 by alok
 *
 */
package org.posterita.beans;


public class LocatorBean extends UDIBean
{

	public String getAisle()
	{
		return aisle;
	}
	public void setAisle(String aisle)
	{
		this.aisle = aisle;
	}
	public String getBin()
	{
		return bin;
	}
	public void setBin(String bin)
	{
		this.bin = bin;
	}
	public Boolean getIsDefault()
	{
		return isDefault;
	}
	public void setIsDefault(Boolean isDefault)
	{
		this.isDefault = isDefault;
	}
	public String getLevel()
	{
		return level;
	}
	public void setLevel(String level)
	{
		this.level = level;
	}
	public Integer getWarehouseId()
	{
		return warehouseId;
	}
	public void setWarehouseId(Integer warehouseId)
	{
		this.warehouseId = warehouseId;
	}
	
	public String getWarehouseName()
	{
		return warehouseName;
	}
	
	public void setWarehouseName(String warehouseName)
	{
		this.warehouseName = warehouseName;
	}
	
	
	
    public Integer getAttributeSetInstance()
    {
        return attributeSetInstance;
    }
    public void setAttributeSetInstance(Integer attributeSetInstance)
    {
        this.attributeSetInstance = attributeSetInstance;
    }
    public String getSernoAttributeSetInstance()
    {
        return sernoAttributeSetInstance;
    }
    public void setSernoAttributeSetInstance(String sernoAttributeSetInstance)
    {
        this.sernoAttributeSetInstance = sernoAttributeSetInstance;
    }
    
	
    public Integer getAttributeSetInstanceId()
    {
        return attributeSetInstanceId;
    }
    public void setAttributeSetInstanceId(Integer attributeSetInstanceId)
    {
        this.attributeSetInstanceId = attributeSetInstanceId;
    }
    
    public Integer getLocatorId()
    {
        return locatorId;
    }
    public void setLocatorId(Integer locatorId)
    {
        this.locatorId = locatorId;
    }
}
