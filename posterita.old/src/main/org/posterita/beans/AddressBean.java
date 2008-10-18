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
 *  
 **/
package org.posterita.beans;

import org.posterita.beans.UDIBean;

public class AddressBean extends UDIBean
{
	
	public Integer getBpartnerId()
	{
		return bpartnerId;
	}
	
	public void setBpartnerId(Integer bpartnerId)
	{
		this.bpartnerId = bpartnerId;
	}

	public String getAddress1()
	{
		return address1;
	}
	
	public void setAddress1(String address1)
	{
		this.address1 = address1;
	}
	
	public String getAddress2()
	{
		return address2;
	}
	public void setAddress2(String address2)
	{
		this.address2 = address2;
	}
	
	public Boolean getBillTo()
	{
		return billTo;
	}
	
	public void setBillTo(Boolean billTo)
	{
		this.billTo = billTo;
	}
	public String getCity()
	{
		return city;
	}
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	public Integer getCountryId()
	{
		return countryId;
	}
	public void setCountryId(Integer countryId)
	{
		this.countryId = countryId;
	}
	
	public String getPostalAddress()
	{
		return postalAddress;
	}
	
	public void setPostalAddress(String postalAddress)
	{
		this.postalAddress = postalAddress;
	}
	
	public Boolean getShipTo()
	{
		return shipTo;
	}
	
	public void setShipTo(Boolean shipTo)
	{
		this.shipTo = shipTo;
	}
	
	public String getCountryName()
	{
		return countryName;
	}
	
	public void setCountryName(String countryName)
	{
		this.countryName = countryName;
	}
	
    public String getUserSurname() 
    {
        return userSurname;
    }

    public void setUserSurname(String userSurname) 
    {
        this.userSurname = userSurname;
    }
    
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}    


	public String getCountryCode()
	{
		return countryCode;
	}
	
	public void setCountryCode(String countryCode)
	{
		this.countryCode = countryCode;
	}

	public Integer getRegionId()
	{
		return regionId;
	}
	
	public void setRegionId(Integer regionId)
	{
		this.regionId = regionId;
	}	
	

}
