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
 * Created on Jul 29, 2005 by din
 */

package org.posterita.beans;

import java.math.BigDecimal;

public class UserBean extends UDIBean
{ 
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Boolean getIsFullAccess() {
    return isFullAccess;
}

public void setIsFullAccess(Boolean isFullAccess) {
    this.isFullAccess = isFullAccess;
}

    public BigDecimal getSubtractAmt() {
    return subtractAmt;
}
    
public void setSubtractAmt(BigDecimal subtractAmt) {
    this.subtractAmt = subtractAmt;
}
    public String getFrequencyType() {
        return frequencyType;
    }
    public void setFrequencyType(String frequencyType) {
        this.frequencyType = frequencyType;
    }
    public BigDecimal getAmtMultiplier() {
    return amtMultiplier;
}
public void setAmtMultiplier(BigDecimal amtMultiplier) {
    this.amtMultiplier = amtMultiplier;
}
    public String getDocBasisType() {
        return docBasisType;
    }
    public void setDocBasisType(String docBasisType) {
        this.docBasisType = docBasisType;
    }
    public String getUserPIN() 
    {
        return userPIN;
    }
    public void setUserPIN(String userPIN) 
    {
        this.userPIN = userPIN;
    }
    public Integer getPartnerId()
	{
		return partnerId;
	}

	public void setPartnerId(Integer partnerId)
	{
		this.partnerId = partnerId;
	}

	public String getCountryName() {
    return countryName;
}

public void setCountryName(String countryName) {
    this.countryName = countryName;
}

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public Integer getLocationId() 
    {
        return locationId;
    }
    
    public void setLocationId(Integer locationId) 
    {
        this.locationId = locationId;
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
    
	public String getCity()
	{
		return city;
	}
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	public Boolean getIsActive()
	{
		return isActive;
	}
	
	public void setIsActive(Boolean isActive)
	{
		this.isActive = isActive;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getConfirmPassword()
	{
		return confirmPassword;
	}
	
	public void setConfirmPassword(String confirmPassword)
	{
		this.confirmPassword = confirmPassword;
	}
	
	public String getPhone()
	{
		return phone;
	}
	
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	
	public String getPostalAddress()
	{
		return postalAddress;
	}
	
	public void setPostalAddress(String postalAddress)
	{
		this.postalAddress = postalAddress;
	}
	
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getFax()
	{
		return fax;
	}
	
	public void setFax(String fax)
	{
		this.fax = fax;
	}
	
	public Integer getRoleId()
	{
		return roleId;
	}
	
	public void setRoleId(Integer roleId)
	{
		this.roleId = roleId;
	}
	public String getRoleName()
    {
        return roleName;
    }
    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }
	public Boolean getIsSalesRep()
	{
		return isSalesRep;
	}
	
	public void setIsSalesRep(Boolean isSalesRep)
	{
		this.isSalesRep = isSalesRep;
	}
	
	public Integer getRegionId()
	{
		return regionId;
	}
	
	public void setRegionId(Integer regionId)
	{
		this.regionId = regionId;
	}
	
	public Integer getUserId()
	{
		return userId;
	}
	
	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}
	
	public String getRegion()
	{
		return region;
	}
	public void setRegion(String region)
	{
		this.region = region;
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getCountryCode()
	{
		return countryCode;
	}
	public void setCountryCode(String countryCode)
	{
		this.countryCode = countryCode;
	}	
	
	public BigDecimal getUserDiscount() {
		return userDiscount;
	}
	public void setUserDiscount(BigDecimal userDiscount) {
		this.userDiscount = userDiscount;
	}

	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	
    public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	/*public String getUserSurname() {
		return userSurname;
	}

	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}*/
}
