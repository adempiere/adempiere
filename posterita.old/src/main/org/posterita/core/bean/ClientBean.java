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
* Created on Oct 30, 2006 by ashley
* 
*/

/**
	@author ashley
 */

package org.posterita.core.bean;

import org.apache.struts.upload.FormFile;
import org.posterita.beans.UDIBean;

public class ClientBean extends UDIBean
{
	public String getClientName()
	{
		return clientName;
	}
	
	public void setClientName(String clientName)
	{
		this.clientName = clientName;
	}

	public String getAddress1()
	{
		return address1;
	}

	public void setAddress1(String address1)
	{
		this.address1 = address1;
	}

	public Integer getCountryId()
	{
		return countryId;
	}

	public void setCountryId(Integer countryId)
	{
		this.countryId = countryId;
	}

	public Integer getCurrencyId()
	{
		return currencyId;
	}

	public void setCurrencyId(Integer currencyId)
	{
		this.currencyId = currencyId;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
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

	public void setPostalAddress(String postalAddress)
	{
		this.postalAddress = postalAddress;
	}

	public String getConfirmPassword()
	{
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword)
	{
		this.confirmPassword = confirmPassword;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getUserPIN()
	{
		return userPIN;
	}

	public void setUserPIN(String userPIN)
	{
		this.userPIN = userPIN;
	}

	public String getRoleName()
	{
		return roleName;
	}

	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}
	
	public FormFile getFile() 
	{
		return file;
	}
	public void setFile(FormFile file) 
	{
		this.file = file;
	}
}
