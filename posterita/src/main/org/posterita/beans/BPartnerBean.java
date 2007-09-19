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
 * Created on 25-Jul-2005 by alok
 *
 */
package org.posterita.beans;


public class BPartnerBean extends UDIBean
{    
    
    public String getCountryName() 
    {
        return countryName;
    }

    public void setCountryName(String countryName) 
    {
        this.countryName = countryName;
    }

    public Integer getCountryId() 
    {
    	return countryId;
    }
    
    public void setCountryId(Integer countryId) 
    {
            this.countryId = countryId;
    }

    public String getName2()
    {
        return name2;
    }
    public void setName2(String name2)
    {
        this.name2 = name2;
    }
	public Integer getBpartnerId()
	{
		return bpartnerId;
	}
	public void setBpartnerId(Integer bpartnerId)
	{
		this.bpartnerId = bpartnerId;
	}
	
	public String getPartnerName()
	{
		return partnerName;
	}
	public void setPartnerName(String partnerName)
	{
		this.partnerName = partnerName;
	}
	public Boolean getIsCustomer()
	{
		return isCustomer;
	}
	public void setIsCustomer(Boolean isCustomer)
	{
		this.isCustomer = isCustomer;
	}
	public Boolean getIsEmployee()
	{
		return isEmployee;
	}
	public void setIsEmployee(Boolean isEmployee)
	{
		this.isEmployee = isEmployee;
	}
	public Boolean getIsSalesRep()
	{
		return isSalesRep;
	}
	public void setIsSalesRep(Boolean isSalesRep)
	{
		this.isSalesRep = isSalesRep;
	}
	public Boolean getIsVendor()
	{
		return isVendor;
	}
	public void setIsVendor(Boolean isVendor)
	{
		this.isVendor = isVendor;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
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
	
	public Boolean getIsActive()
	{
		return isActive;
	}
	public void setIsActive(Boolean isActive)
	{
		this.isActive = isActive;
	}
	public Integer getLocationId()
    {
        return locationId;
    }
    public void setLocationId(Integer locationId)
    {
        this.locationId = locationId;
    }
	public String getAddress2() 
	{
		return address2;
	}
	public void setAddress2(String address2) 
	{
		this.address2 = address2;
	}        
    public String getFax() 
    {
        return fax;
    }    
    public void setFax(String fax) 
    {
        this.fax = fax;
    }
}
