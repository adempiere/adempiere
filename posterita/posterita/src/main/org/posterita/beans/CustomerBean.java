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
 * Created on 02-Sep-2005
 */


package org.posterita.beans;

import java.math.BigDecimal;

public class CustomerBean extends DocumentBean
{  	
	public Boolean isEditable;
	
	
	
    public Boolean getIsEditable() {
		return isEditable;
	}

	public void setIsEditable(Boolean isEditable) {
		this.isEditable = isEditable;
	}

	public Integer getOrgId() 
    {
		return orgId;
	}
    
	public void setOrgId(Integer orgId) 
	{
		this.orgId = orgId;
	}
	
	public String getPaymentTermName() {
        return paymentTermName;
    }
    public void setPaymentTermName(String paymentTermName) {
        this.paymentTermName = paymentTermName;
    }
    public Integer getPaymentTermId() {
        return paymentTermId;
    }
    public void setPaymentTermId(Integer paymentTermId) {
        this.paymentTermId = paymentTermId;
    }
    public String getCreditStatus() {
        return creditStatus;
    }
    public void setCreditStatus(String creditStatus) {
        this.creditStatus = creditStatus;
    }
    public BigDecimal getCreditLimit() {
        return creditLimit;
    }
    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }
    public Integer getDunningId() {
        return dunningId;
    }
    public void setDunningId(Integer dunningId) {
        this.dunningId = dunningId;
    }
    public Boolean getCreatingFromOrder() {
        return creatingFromOrder;
    }
    public void setCreatingFromOrder(Boolean creatingFromOrder) {
        this.creatingFromOrder = creatingFromOrder;
    }
    public Integer getAttributeSetInstanceId()
    {
        return attributeSetInstanceId;
    }
    public void setAttributeSetInstanceId(Integer attributeSetInstanceId)
    {
        this.attributeSetInstanceId = attributeSetInstanceId;
    }
    public Integer getProductId()
    {
        return productId;
    }
    public void setProductId(Integer productId)
    {
        this.productId = productId;
    }
    public Integer getLicensingDistrictId() {
        return licensingDistrictId;
    }
    public void setLicensingDistrictId(Integer licensingDistrictId) {
        this.licensingDistrictId = licensingDistrictId;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getAaCardnumber() {
        return aaCardnumber;
    }
    public String getAddress1() {
        return address1;
    }
    public String getAddress2() {
        return address2;
    }
    public String getCity() {
        return city;
    }
    
    public String getCustIdNumber() {
        return custIdNumber;
    }
    
    public String getMaintenanceContractNumber() {
        return maintenanceContractNumber;
    }
    public String getMobile() {
        return mobile;
    }
    public String getPartnerName() {
        return partnerName;
    }
    
    public String getPhone2() {
        return phone2;
    }
    public String getPhone() {
        return phone;
    }
    public String getPostalAddress1() {
        return postalAddress1;
    }
    public String getPostalCity() {
        return postalCity;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public String getPostalPostalCode() {
        return postalPostalCode;
    }
    
    
    public Integer getRegionId() {
        return regionId;
    }
    
    public void setAaCardnumber(String aaCardnumber) {
        this.aaCardnumber = aaCardnumber;
    }
    public void setAddress1(String address1) {
        this.address1 = address1;
    }
    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setCustIdNumber(String custIdNumber) {
        this.custIdNumber = custIdNumber;
    }
    public void setMaintenanceContractNumber(String maintenanceContractNumber) {
        this.maintenanceContractNumber = maintenanceContractNumber;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }
    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setPostalAddress1(String postalAddress1) {
        this.postalAddress1 = postalAddress1;
    }
    public void setPostalCity(String postalCity) {
        this.postalCity = postalCity;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public void setPostalPostalCode(String postalPostalCode) {
        this.postalPostalCode = postalPostalCode;
    }
    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }
   
    public Integer getBpartnerId()
    {
        return bpartnerId;
    }
    
    public void setBpartnerId(Integer bpartnerId)
    {
        this.bpartnerId = bpartnerId;
    }
    
    public String getLicensingDistrict()
    {
        return licensingDistrict;
    }
    
    public void setLicensingDistrict(String licensingDistrict)
    {
        this.licensingDistrict = licensingDistrict;
    }
    
    public String getRegion()
    {
        return region;
    }
    
    public void setRegion(String region)
    {
        this.region = region;
    }
    
    public String getCompanyRegNo()
    {
        return companyRegNo;
    }
    
    public void setCompanyRegNo(String companyRegNo)
    {
        this.companyRegNo = companyRegNo;
    }
    
    public String getPassportNo()
    {
        return passportNo;
    }
    
    public void setPassportNo(String passportNo)
    {
        this.passportNo = passportNo;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public Integer getSalesRepId() 
    {
        return salesRepId;
    }
    
    public void setSalesRepId(Integer salesRepId) 
    {
        this.salesRepId = salesRepId;
    }
         
    public String getMakeName()
    {
        return makeName;
    }
    
    public void setMakeName(String makeName)
    {
        this.makeName = makeName;
    }
    
    public String getModelName()
    {
        return modelName;
    }
    
    public void setModelName(String modelName)
    {
        this.modelName = modelName;
    }
    
    public String getYearName()
    {
        return yearName;
    }
    
    public void setYearName(String yearName)
    {
        this.yearName = yearName;
    }

	public Integer getCreditCardExpMonth()
	{
		return creditCardExpMonth;
	}
	
	public void setCreditCardExpMonth(Integer creditCardExpMonth)
	{
		this.creditCardExpMonth = creditCardExpMonth;
	}
	
	public Integer getCreditCardExpYear()
	{
		return creditCardExpYear;
	}
	
	public void setCreditCardExpYear(Integer creditCardExpYear)
	{
		this.creditCardExpYear = creditCardExpYear;
	}
	
	public String getCreditCardNumber()
	{
		return creditCardNumber;
	}
	
	public void setCreditCardNumber(String creditCardNumber)
	{
		this.creditCardNumber = creditCardNumber;
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

    public String getCountryName() 
    {
    return countryName;
    }

    public void setCountryName(String countryName) 
    {
    	this.countryName = countryName;
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
	
    public String getUserSurname() 
    {
        return userSurname;
    }

    public void setUserSurname(String userSurname) 
    {
        this.userSurname = userSurname;
    }
    
	public String getFax()
	{
		return fax;
	}
	
	public void setFax(String fax)
	{
		this.fax = fax;
	} 
	
    public Integer getCountryId() 
    {
        return countryId;
    }

    public void setCountryId(Integer countryId) 
    {
        this.countryId = countryId;
    }	

/*	public Integer getCvv()
	{
		return cvv;
	}
	
	public void setCvv(Integer cvv)
	{
		this.cvv = cvv;
	}
*/
	public String getCreditCardPayment()
	{
		return creditCardPayment;
	}
	
	public void setCreditCardPayment(String creditCardPayment)
	{
		this.creditCardPayment = creditCardPayment;
	}

	public String getShipmentMethod()
	{
		return shipmentMethod;
	}
	
	public void setShipmentMethod(String shipmentMethod)
	{
		this.shipmentMethod = shipmentMethod;
	}
	
	public String getBirthdate() 
	{
		return birthdate;
	}
	public void setBirthdate(String birthdate) 
	{
		this.birthdate = birthdate;
	}   
	public String getStartDay() {
		return startDay;
	}
	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}
	public String getStartMonth() {
		return startMonth;
	}
	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}
	public String getStartYear() {
		return startYear;
	}
	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	} 
	public Integer getPriceListId() 
	{
		return priceListId;
	}

	public void setPriceListId(Integer priceListId) 
	{
		this.priceListId = priceListId;
	}
	
	//----------------------------------------------
	private BigDecimal totalOpenBalance;
	private BigDecimal creditLimit;
	private BigDecimal creditUsed;
	private BigDecimal creditAvailable;

	public BigDecimal getTotalOpenBalance() {
		return totalOpenBalance;
	}

	public void setTotalOpenBalance(BigDecimal totalOpenBalance) {
		this.totalOpenBalance = totalOpenBalance;
	}

	public BigDecimal getCreditUsed() {
		return creditUsed;
	}

	public void setCreditUsed(BigDecimal creditUsed) {
		this.creditUsed = creditUsed;
	}

	public BigDecimal getCreditAvailable() {
		return creditAvailable;
	}

	public void setCreditAvailable(BigDecimal creditAvailable) {
		this.creditAvailable = creditAvailable;
	}
	
	public String getBankName()
    {
        return bankName;
    }
	
    public void setBankName(String bankName)
    {
        this.bankName = bankName;
    }
    
    public String getAccountNo()
    {
        return accountNo;
    }
    
    public void setAccountNo(String accountNo)
    {
        this.accountNo = accountNo;
    }
    
    public String getBranch()
    {
        return branch;
    }
    
    public void setBranch(String branch)
    {
        this.branch = branch;
    }
    
    public String getPriceListName()
    {
        return priceListName;
    }
    
    public void setPriceListName(String priceListName)
    {
        this.priceListName = priceListName;
    }
    
    public String getSaleRepName()
    {
        return saleRepName;
    }
    
    public void setSaleRepName(String saleRepName)
    {
        this.saleRepName = saleRepName;
    }
    
    public String getTaxNo()
    {
        return taxNo;
    }
    
    public void setTaxNo(String taxNo)
    {
        this.taxNo = taxNo;
    }
  
}
