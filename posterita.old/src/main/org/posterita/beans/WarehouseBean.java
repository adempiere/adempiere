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
 * Created on Jul 26, 2005 by din
 */

package org.posterita.beans;

public class WarehouseBean extends UDIBean 
{
	

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}

	public String getWarehouseType() 
	{
		return warehouseType;
	}

	public void setWarehouseType(String warehouseType) 
	{
		this.warehouseType = warehouseType;
	}

	public Boolean getIsAllocationWarehouse() 
	{
		return isAllocationWarehouse;
	}

	public void setIsAllocationWarehouse(Boolean isAllocationWarehouse) 
	{
		this.isAllocationWarehouse = isAllocationWarehouse;
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

	public String getAddress1() 
	{
		return address1;
	}

	public void setAddress1(String address1) 
	{
		this.address1 = address1;
	}

	public String getCity() 
	{
		return city;
	}

	public void setCity(String city) 
	{
		this.city = city;
	}

	public String getPostalAddress() 
	{
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) 
	{
		this.postalAddress = postalAddress;
	}

	public Integer getRegionId() 
	{
		return regionId;
	}

	public void setRegionId(Integer regionId)
	{
		this.regionId = regionId;
	}

	public Boolean getIsPublic() 
	{
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) 
	{
		this.isPublic = isPublic;
	}

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

	public String getRegionName() 
	{
		return regionName;
	}

	public void setRegionName(String regionName) 
	{
		this.regionName = regionName;
	}
	
	public Boolean getIsActive()
    {
        return isActive;
    }
    
    public void setIsActive(Boolean isActive)
    {
        this.isActive = isActive;
    }
}
