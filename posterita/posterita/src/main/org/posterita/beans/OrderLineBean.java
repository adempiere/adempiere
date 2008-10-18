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
 * Created on 09-Aug-2005 by alok
 *
 */
package org.posterita.beans;

import java.math.BigDecimal;
import java.util.ArrayList;

public class OrderLineBean extends DocumentBean implements Comparable
{	
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Double getTotalActualPrice() {
        return totalActualPrice;
    }
    public void setTotalActualPrice(Double totalActualPrice) {
        this.totalActualPrice = totalActualPrice;
    }
    public Double getPaymentByCard() {
        return paymentByCard;
    }
    public void setPaymentByCard(Double paymentByCard) {
        this.paymentByCard = paymentByCard;
    }
    public Double getPaymentByCash() {
        return paymentByCash;
    }
    public void setPaymentByCash(Double paymentByCash) {
        this.paymentByCash = paymentByCash;
    }
    public Double getPaymentByChq() {
        return paymentByChq;
    }
    public void setPaymentByChq(Double paymentByChq) {
        this.paymentByChq = paymentByChq;
    }
    public String[] getActualPrice() {
        return actualPrice;
    }
    public void setActualPrice(String[] actualPrice) {
        this.actualPrice = actualPrice;
    }
    
    public String[] getDiscountPercent() {
        return discountPercent;
    }
    public void setDiscountPercent(String[] discountPercent) {
        this.discountPercent = discountPercent;
    }
    public String getQtyAndItem() {
        return qtyAndItem;
    }
    public void setQtyAndItem(String qtyAndItem) {
        this.qtyAndItem = qtyAndItem;
    }
    public String getBarCode() {
        return barCode;
    }
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
    
    public String getPartnerName() {
        return partnerName;
    }
    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }
    public Integer[] getPosOrderLineIds()
    {
        return posOrderLineIds;
    }
    
    public void setPosOrderLineIds(Integer[] posOrderLineIds)
    {
        this.posOrderLineIds = posOrderLineIds;
    }
	public String getChequeNo()
	{
	    return chequeNo;
	}
	
	public void setChequeNo(String chequeNo)
	{
	    this.chequeNo = chequeNo;
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
	
	public String getCreditCardType()
	{
	    return creditCardType;
	}
	
	public void setCreditCardType(String creditCardType)
	{
	    this.creditCardType = creditCardType;
	}
    public Integer getOrderId() 
    {
        return orderId;
    }
    
    public void setOrderId(Integer orderId) 
    {
        this.orderId = orderId;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

	public Integer getBpartnerId()
	{
	    return bpartnerId;
	}
	
	public void setBpartnerId(Integer bpartnerId) 
	{
	   this.bpartnerId = bpartnerId;
	}
    public Boolean getIsQtyReserved()
    {
        return isQtyReserved;
    }
    
    public void setIsQtyReserved(Boolean isQtyReserved)
    {
        this.isQtyReserved = isQtyReserved;
    }
    public String getEngineNo() {
        return engineNo;
    }
    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }
	public BigDecimal getQuantity()
	{
		return quantity;
	}
	
	public void setQuantity(BigDecimal quantity)
	{
		this.quantity = quantity;
	}
	
	public Integer getProductId()
	{
		return productId;
	}
	
	public void setProductId(Integer productId)
	{
		this.productId = productId;
	}
   
	
	public Integer[] getCheckBox()
	{
		return checkBox;
	}
	public void setCheckBox(Integer [] checkBox)
	{
		this.checkBox = checkBox;
	}
	
	public Integer getAttributeSetInstanceId()
	{
	    return attributeSetInstanceId;
	}
	
	public void setAttributeSetInstanceId(Integer attributeSetInstanceId)
	{
	    this.attributeSetInstanceId = attributeSetInstanceId;
	}
	
	public ArrayList getOrderLineList()
	{
		return orderLineList;
	}
	public void setOrderLineList(ArrayList orderLineList)
	{
		this.orderLineList = orderLineList;
	}
	
	public Integer getOrderLineId()
	{
	    return orderLineId;
	}
	
	public void setOrderLineId(Integer orderLineId)
	{
	    this.orderLineId = orderLineId;
	}
	
	public Integer getOrgId()
	{
	    return orgId;
	}
	
	public void setOrgId(Integer orgId)
	{
	    this.orgId = orgId;
	}
	
	public Integer getAllocationId()
	{
	    return allocationId;
	}
	
	public void setAllocationId(Integer allocationId)
	{
	    this.allocationId = allocationId;
	}
	
	public boolean equals(Object obj)
	{
	    OrderLineBean target = (OrderLineBean) obj;
	    
	    if ( obj == null)
	    	return false;
	    
	    if (this.getAttributeSetInstanceId().equals(target.getAttributeSetInstanceId()))
	        return true;
	    
	    return false;
	}
	
	public String getSubmit()
	{
	    return submit;
	}
	
	public void setSubmit(String submit)
	{
	    this.submit = submit;
	}


    public int compareTo(Object o) 
    {
        OrderLineBean src = (OrderLineBean) o;
        
        if (src.getAttributeSetInstanceId().intValue() == this.getAttributeSetInstanceId().intValue())
            return 0;
        
        
        return -1;
    }
    
    
	
  
  
    
    public BigDecimal getAmountGiven() 
    {
        return amountGiven;
    }
    
    public void setAmountGiven(BigDecimal amountGiven)
    {
        this.amountGiven = amountGiven;
    }
    
    public BigDecimal getAmountRefunded() 
    {
        return amountRefunded;
    }
   
    public void setAmountRefunded(BigDecimal amountRefunded)
    {
        this.amountRefunded = amountRefunded;
    }
    public String getTrxType()
    {
        return trxType;
    }
    
    public void setTrxType(String trxType)
    {
        this.trxType = trxType;
    }
    
    public BigDecimal getTransferAmount() {
        return transferAmount;
    }
    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }
    
    public Integer getCashBookIds() {
        return cashBookIds;
    }
    public void setCashBookIds(Integer cashBookIds) {
        this.cashBookIds = cashBookIds;
    }
    public String getIsSales() {
        return isSales;
    }
    public void setIsSales(String isSales) {
        this.isSales = isSales;
    }
    public String getIfAdd() {
        return ifAdd;
    }
    public void setIfAdd(String ifAdd) {
        this.ifAdd = ifAdd;
    }
    
    public String getVendorName() {
        return vendorName;
    }
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
   
	public String getDocumentNo() {
		return documentNo;
	}
	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}
	
	public BigDecimal getUserDiscount() {
		return userDiscount;
	}
	public void setUserDiscount(BigDecimal userDiscount) {
		this.userDiscount = userDiscount;
	}
	
     public Integer getPaymentTermId() {
            return paymentTermId;
        }
        public void setPaymentTermId(Integer paymentTermId) {
            this.paymentTermId = paymentTermId;
        }
       
        public String getPaymentTermName() {
            return paymentTermName;
        }
        public void setPaymentTermName(String paymentTermName) {
            this.paymentTermName = paymentTermName;
        }
        public String getToBeShipped() {
            return toBeShipped;
        }
        public void setToBeShipped(String toBeShipped) {
            this.toBeShipped = toBeShipped;
        }
        
		public Boolean getOpenDrawer() {
			return openDrawer;
		}
		public void setOpenDrawer(Boolean openDrawer) {
			this.openDrawer = openDrawer;
		}
		
	public void setUom(String uom)
	{
		this.uom = uom;
	}
	
	public String getUom()
	{
		return this.uom;
	}
	public Integer getPriceListId() {
		return priceListId;
	}
	public void setPriceListId(Integer priceListId) {
		this.priceListId = priceListId;
	}
	
	public BigDecimal getUnitPurchasePrice()
    {
        return unitPurchasePrice;
    }

    public void setUnitPurchasePrice(BigDecimal unitPurchasePrice)
    {
        this.unitPurchasePrice = unitPurchasePrice;
    }
    
    public BigDecimal getGrossProfit() 
    {
        return grossProfit;
    }

    public void setGrossProfit(BigDecimal grossProfit) 
    {
        this.grossProfit = grossProfit;
    }
    
    public BigDecimal getTotalGrossProfit()
    {
        return totalGrossProfit;
    }

    public void setTotalGrossProfit(BigDecimal totalGrossProfit)
    {
        this.totalGrossProfit = totalGrossProfit;
    }
	
	protected String paymentRule;

	public String getPaymentRule() {
		return paymentRule;
	}
	
	public void setPaymentRule(String paymentRule) {
		this.paymentRule = paymentRule;
	}
    public Integer getRoleId()
    {
        return roleId;
    }
    public void setRoleId(Integer roleId)
    {
        this.roleId = roleId;
    }
		
        
}
