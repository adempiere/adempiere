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
 * Created on 04-Aug-2005 by alok
 *
 */
package org.posterita.beans;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;


public class OrderHistoryBean extends FilterBean
{
	public ArrayList orderHistoryBean;
	private ArrayList invoiceHistoryList;
	private ArrayList orderHistoryList;
	
    protected Integer refOrderId;	
	private FilterBean filterBean;
	private String serno;
	
	
	
	

    public Integer getDocumentId() {
        return documentId;
    }
    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }
    public Integer[] getOrderIds() {
        return orderIds;
    }
    public void setOrderIds(Integer[] orderIds) {
        this.orderIds = orderIds;
    }
    public String getSerno() {
        return serno;
    }
    
    public void setSerno(String serno) {
        this.serno = serno;
    }
    
    public Integer getRefOrderId() 
    {
        return refOrderId;
    }
    
    public void setRefOrderId(Integer refOrderId) 
    {
        this.refOrderId = refOrderId;
    }
    
    public Integer getRefToQuote()
    {
        return refToQuote;
    }
    public void setRefToQuote(Integer refToQuote) 
    {
        this.refToQuote = refToQuote;
    }
    public String getOrderType()
    {
        return orderType;
    }
    public void setOrderType(String orderType)
    {
        this.orderType = orderType;
    }
	public ArrayList getOrderHistoryBean() 
	{
		return orderHistoryBean;
	}

	public void setOrderHistoryBean(ArrayList orderHistoryBean) 
	{
		this.orderHistoryBean = orderHistoryBean;
	}

	public Integer getInOutId() 
	{
		return inOutId;
	}

	public void setInOutId(Integer inOutId) 
	{
		this.inOutId = inOutId;
	}

	public Integer getInvoiceId() 
	{
		return invoiceId;
	}
	
	public void setInvoiceId(Integer invoiceId) 
	{
		this.invoiceId = invoiceId;
	}
	
	public Integer getPaymentId() 
	{
		return paymentId;
	}
	
	public void setPaymentId(Integer paymentId) 
	{
		this.paymentId = paymentId;
	}
	
	public String getPartnerName()
	{
		return partnerName;
	}
	
	public void setPartnerName(String partnerName)
	{
		this.partnerName = partnerName;
	}
	public Integer getBpartnerId()
	{
		return bpartnerId;
	}
	public void setBpartnerId(Integer bpartnerId)
	{
		this.bpartnerId = bpartnerId;
	}
	
	public Timestamp getDateOrdered()
	{
		return dateOrdered;
	}
	public void setDateOrdered(Timestamp dateOrdered)
	{
		this.dateOrdered = dateOrdered;
	}
	public String getDatePromised()
	{
		return datePromised;
	}
	public void setDatePromised(String datePromised)
	{
		this.datePromised = datePromised;
	}	
	public BigDecimal getOrderGrandTotal()
	{
		return orderGrandTotal;
	}
	public void setOrderGrandTotal(BigDecimal orderGrandTotal)
	{
		this.orderGrandTotal = orderGrandTotal;
	}
	public String getIsApproved()
	{
		return isApproved;
	}
	public void setIsApproved(String isApproved)
	{
		this.isApproved = isApproved;
	}
	public String getIsDelivered()
	{
		return isDelivered;
	}
	public void setIsDelivered(String isDelivered)
	{
		this.isDelivered = isDelivered;
	}
	public String getIsInvoiced()
	{
		return isInvoiced;
	}
	public void setIsInvoiced(String isInvoiced)
	{
		this.isInvoiced = isInvoiced;
	}
	public Boolean getIsSotrx()
	{
		return isSoTrx;
	}
	public void setIsSotrx(Boolean isSotrx)
	{
		this.isSoTrx = isSotrx;
	}
	public Integer getOrderId()
	{
		return orderId;
	}
	public void setOrderId(Integer orderId)
	{
		this.orderId = orderId;
	}
	public String getPoReference()
	{
		return poReference;
	}
	public void setPoReference(String poReference)
	{
		this.poReference = poReference;
	}	  
	
	public Boolean getIsSoTrx()
	{
	    return isSoTrx;
	}
	
	public void setIsSoTrx(Boolean isSoTrx)
	{
	    this.isSoTrx = isSoTrx;
	}
	
	public ArrayList getInvoiceHistoryList()
	{
	    return invoiceHistoryList;
	}
	
	public void setInvoiceHistoryList(ArrayList list)
	{
	    this.invoiceHistoryList = list;
	}
	
	public String getSotrxFlag()
	{
	    return sotrxFlag;
	}
	
	public void setSotrxFlag(String sotrxFlag)
	{
	    this.sotrxFlag = sotrxFlag;
	}
	
	public FilterBean getFilterBean()
	{
	    return filterBean;
	}
	
	public void setFilterBean(FilterBean filterBean)
	{
	    this.filterBean = filterBean;
	}
	
	public String getBpName()
	{
	    return bpName;
	}
	
	public void setBpName(String bpName)
	{
	    this.bpName = bpName;
	}
	
	public String getDocStatus()
	{
	    return docStatus;
	}
	
	public void setDocStatus(String docStatus)
	{
	    this.docStatus = docStatus;
	}
	
	public ArrayList getOrderHistoryList()
	{
	    return orderHistoryList;
	}
	
	public void setOrderHistoryList(ArrayList orderHistoryList)
	{
	    this.orderHistoryList = orderHistoryList;
	}
	
	public String getCompiereDocStatus()
	{
	    return compiereDocStatus;
	}
	
	public void setCompiereDocStatus(String compiereDocStatus)
	{
	    this.compiereDocStatus = compiereDocStatus;
	}
	
	public Integer getOrderlineCount() 
	{
		return orderlineCount;
	}
	
	public void setOrderlineCount(Integer orderlineCount) 
	{
		this.orderlineCount = orderlineCount;
	}
	
	public Integer getOrderlineInvoicedCount() 
	{
		return orderlineInvoicedCount;
	}
	
	public void setOrderlineInvoicedCount(Integer orderlineInvoicedCount) 
	{
		this.orderlineInvoicedCount = orderlineInvoicedCount;
	}
	
    public Integer getAttributeSetInstanceId()
    {
        return attributeSetInstanceId;
    }
    
    public void setAttributeSetInstanceId(Integer attributeSetInstanceId)
    {
        this.attributeSetInstanceId = attributeSetInstanceId;
    }
    
    public Timestamp getDateInvoiced()
    {
        return dateInvoiced;
    }
    
    public void setDateInvoiced(Timestamp dateInvoiced)
    {
        this.dateInvoiced = dateInvoiced;
    }
}
