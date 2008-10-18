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
 * Created on May 15, 2006
 */


package org.posterita.beans;

import java.math.BigDecimal;
import java.sql.Timestamp;


public class POSHistoryBean extends UDIBean
{
	public Boolean isCustomer;
	private Integer invoiceRefNo;
	private String invoiceDocumentNo;
	private BigDecimal amountPaid;
	
    public BigDecimal getGrossProfit() 
    {
		return grossProfit;
	}
	public void setGrossProfit(BigDecimal grossProfit) 
	{
		this.grossProfit = grossProfit;
	}
	public String getHistoryType()
	{
		return historyType;
	}
	public void setHistoryType(String historyType)
	{
		this.historyType = historyType;
	}
	public Integer getBpartnerId()
	{
		return bpartnerId;
	}
	public void setBpartnerId(Integer bpartnerId)
	{
		this.bpartnerId = bpartnerId;
	}
	public String getPaymentRule() {
        return paymentRule;
    }
    public void setPaymentRule(String paymentRule) {
        this.paymentRule = paymentRule;
    }
    public String getOrderType() {
        return orderType;
    }
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    public Integer getMonth() {
        return month;
    }
    public void setMonth(Integer month) {
        this.month = month;
    }
    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
    public Timestamp getDateAcct() {
        return dateAcct;
    }
    public void setDateAcct(Timestamp dateAcct) {
        this.dateAcct = dateAcct;
    }
    public String getDocumentNo() {
        return documentNo;
    }
    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }
    public BigDecimal getOrderGrandTotal() {
        return orderGrandTotal;
    }
    public void setOrderGrandTotal(BigDecimal orderGrandTotal) {
        this.orderGrandTotal = orderGrandTotal;
    }
    public Integer getInvoiceId() {
        return invoiceId;
    }
    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }
    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public String getPartnerName() {
        return partnerName;
    }
    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }
	public String getDocStatus()
	{
		return docStatus;
	}
	public void setDocStatus(String docStatus)
	{
		this.docStatus = docStatus;
	}
	public Boolean getIsCustomer()
	{
		return isCustomer;
	}
	public void setIsCustomer(Boolean isCustomer)
	{
		this.isCustomer = isCustomer;
	}
	
	public Integer getInvoiceRefNo() 
	{
		return invoiceRefNo;
	}
	
	public void setInvoiceRefNo(Integer invoiceRefNo) 
	{
		this.invoiceRefNo = invoiceRefNo;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
    /**
     * @return The cashBookId
     */
    public Integer getCashBookId()
    {
        return cashBookId;
    }
    
    /**
     * @param cashBookId The cashBookId to set
     */
    public void setCashBookId(Integer cashBookId)
    {
        this.cashBookId = cashBookId;
    }
    
    /**
     * @return The invoiceDocumentNo
     */
    public String getInvoiceDocumentNo()
    {
        return invoiceDocumentNo;
    }
    
    /**
     * @param invoiceDocumentNo The invoiceDocumentNo to set
     */
    public void setInvoiceDocumentNo(String invoiceDocumentNo)
    {
        this.invoiceDocumentNo = invoiceDocumentNo;
    }
    
    /**
     * @return The amountPaid
     */
    public BigDecimal getAmountPaid()
    {
        return amountPaid;
    }
    
    /**
     * @param amountPaid The amountPaid to set
     */
    public void setAmountPaid(BigDecimal amountPaid)
    {
        this.amountPaid = amountPaid;
    }
    /**
     * @return The dateOrdered
     */
    public Timestamp getDateOrdered()
    {
        return dateOrdered;
    }
    /**
     * @param dateOrdered The dateOrdered to set
     */
    public void setDateOrdered(Timestamp dateOrdered)
    {
        this.dateOrdered = dateOrdered;
    }
}
