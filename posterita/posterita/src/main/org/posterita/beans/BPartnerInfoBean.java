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
 * Created on Jul 26, 2006
 */


package org.posterita.beans;

import java.math.BigDecimal;


public class BPartnerInfoBean extends UDIBean
{
     
    protected String soCreditStatus;
    protected String forward;
     
    public String getForward() 
    {
        return forward;
    }
    
    public void setForward(String forward) 
    {
        this.forward = forward;
    }
    
    public String getBpfirstSale() 
    {
        return bpfirstSale;
    }
    
    public void setBpfirstSale(String bpfirstSale) 
    {
        this.bpfirstSale = bpfirstSale;
    }
       
    public String getSoCreditStatus() 
    {
        return soCreditStatus;
    }
    
    public void setSoCreditStatus(String soCreditStatus) 
    {
        this.soCreditStatus = soCreditStatus;
    }
    
    public Integer getDunningId() 
    {
        return dunningId;
    }
    
    public void setDunningId(Integer dunningId) 
    {
        this.dunningId = dunningId;
    }
    
    public Integer[] getBpartnerIds() 
    {
        return bpartnerIds;
    }
    
    public void setBpartnerIds(Integer[] bpartnerIds) 
    {
        this.bpartnerIds = bpartnerIds;
    }
    
    public BigDecimal getCreditLimit() 
    {
        return creditLimit;
    }
    
    public void setCreditLimit(BigDecimal creditLimit) 
    {
        this.creditLimit = creditLimit;
    }
    
    public BigDecimal getCreditUsed() 
    {
        return creditUsed;
    }
    
    public void setCreditUsed(BigDecimal creditUsed) 
    {
        this.creditUsed = creditUsed;
    }
    
    public BigDecimal getOpenAmt() 
    {
        return openAmt;
    }
    
    public void setOpenAmt(BigDecimal openAmt) 
    {
        this.openAmt = openAmt;
    }
    
    public BigDecimal getRevenue() 
    {
        return revenue;
    }
    
    public void setRevenue(BigDecimal revenue) 
    {
        this.revenue = revenue;
    }
    
    public BigDecimal getAmount() 
    {
        return amount;
    }
    
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }
    
    public Integer getProductId() 
    {
        return productId;
    }
    
    public void setProductId(Integer productId) 
    {
        this.productId = productId;
    }
    
    public String getProductName() 
    {
        return productName;
    }
    
    public void setProductName(String productName) 
    {
        this.productName = productName;
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
    
    public Boolean getIsCustomer() 
    {
        return isCustomer;
    }
    
    public void setIsCustomer(Boolean isCustomer) 
    {
        this.isCustomer = isCustomer;
    }
    
    public Integer getBpartnerId() 
    {
        return bpartnerId;
    }
    
    public void setBpartnerId(Integer bpartnerId) 
    {
        this.bpartnerId = bpartnerId;
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
    
    public String getPartnerName() 
    {
        return partnerName;
    }
    
    public void setPartnerName(String partnerName) 
    {
        this.partnerName = partnerName;
    }
    
    public String getPhone2() 
    {
        return phone2;
    }
    
    public void setPhone2(String phone2) 
    {
        this.phone2 = phone2;
    }
    
    public String getPhone() 
    {
        return phone;
    }
    
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }
    
    public BigDecimal getQuantity() 
    {
        return quantity;
    }
    
    public void setQuantity(BigDecimal quantity) 
    {
        this.quantity = quantity;
    }   

}
