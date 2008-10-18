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


public class BankBean extends UDIBean
{
	public String getAddress1()
	{
		return address1;
	}
	public void setAddress1(String address1)
	{
		this.address1 = address1;
	}
	 
    public Integer getBankId() 
    {
        return bankId;
    }
    public void setBankId(Integer bankId) 
    {
        this.bankId = bankId;
    }
	public String getBankName()
	{
		return bankName;
	}
	public void setBankName(String bankName)
	{
		this.bankName = bankName;
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
	public String getRoutingNumber()
	{
		return routingNumber;
	}
	public void setRoutingNumber(String routingNumber)
	{
		this.routingNumber = routingNumber;
	}	
    public Integer getLocationId()
    {
        return locationId;
    }
    public void setLocationId(Integer locationId)
    {
        this.locationId = locationId;
    }
}
