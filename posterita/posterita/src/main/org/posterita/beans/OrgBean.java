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

public class OrgBean extends UDIBean
{
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public Integer getOrgId()
	{
		return orgId;
	}
	
	public void setOrgId(Integer orgId)
	{
		this.orgId = orgId;
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
    
    public String getOrgName() 
    {
        return orgName;
    }
    
    public void setOrgName(String orgName) 
    {
        this.orgName = orgName;
    }
    
    public String getPostalAddress() 
    {
        return postalAddress;
    }
    
    public void setPostalAddress(String postalAddress1) 
    {
        this.postalAddress = postalAddress1;
    }
    
    public Integer getRegionId()
	{
		return regionId;
	}
	
	public void setRegionId(Integer regionId)
	{
		this.regionId = regionId;
	}
    
	public String getRegion() 
	{
		return region;
	}
	
	public void setRegion(String region) 
	{
		this.region = region;
	}
	
    
    public String getFax()
    {
        return fax;
    }
    
    public void setFax(String fax)
    {
        this.fax = fax;
    }
    
    public String getPhone()
    {
        return phone;
    }
    
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
   
    public Boolean getIsActive()
    {
        return isActive;
    }
    
    public void setIsActive(Boolean isActive)
    {
        this.isActive = isActive;
    }
  
    public String getActive()
    {
        return active;
    }
    
    public void setActive(String active)
    {
        this.active = active;
    }
    
    public void setCountryId(Integer countryId)
    {
    	this.countryId = countryId;
    }
    
    public Integer getCountryId()
    {
    	return this.countryId;
    }
    
    public String getReceiptFooterMsg()
    {
        return receiptFooterMsg;
    }
    
    public void setReceiptFooterMsg(String receiptFooterMsg)
    {
        this.receiptFooterMsg = receiptFooterMsg;
    }
}
