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

import java.sql.Timestamp;
import java.util.ArrayList;

import org.posterita.beans.UDIBean;

public class DocumentHistoryBean extends UDIBean
{
/*
	 protected Integer orderId;
	 protected Integer paymentId;
	 protected Integer invoiceId;
	 protected Integer minOutId;
	 protected String docStatus; //Shipment DocStatus
	 protected Integer month;
	 protected Integer year;
	 protected Integer bpartnerId; 
	 protected String partnerName;
	 protected Timestamp dateOrdered;
	 protected String docStatusCode; 
*/	 
	protected String paymentStatus;
	protected String shipmentStatus;
	
	protected String invoiceNo;
	protected String paymentNo;
	protected String shipmentNo;
    protected ArrayList documentHistoryList;
 
    
 
	   
    
   

   
    public ArrayList getDocumentHistoryList() {
        return documentHistoryList;
    }

    public void setDocumentHistoryList(ArrayList documentHistoryList) {
        this.documentHistoryList = documentHistoryList;
    }

    public Integer getCashLineId() {
        return cashLineId;
    }

    public void setCashLineId(Integer cashLineId) {
        this.cashLineId = cashLineId;
    }

    public String getDocumentNo()
    {
        return documentNo;
    }
    
    public void setDocumentNo(String documentNo)
    {
        this.documentNo=documentNo;
    }
    public String getOrderType()
    {
        return orderType;
    }
    
    public void setOrderType(String orderType)
    {
        this.orderType=orderType;
    }
	
    
	public String getDocStatus()
	{
		return docStatus;
	}
	public void setDocStatus(String docStatus)
	{
		this.docStatus = docStatus;
	}
	public Integer getInvoiceId()
	{
		return invoiceId;
	}
	public void setInvoiceId(Integer invoiceId)
	{
		this.invoiceId = invoiceId;
	}
	public Integer getMinOutId()
	{
		return minOutId;
	}
	public void setMinOutId(Integer minOutId)
	{
		this.minOutId = minOutId;
	}
	public Integer getMonth()
	{
		return month;
	}
	public void setMonth(Integer month)
	{
		this.month = month;
	}
	public Integer getOrderId()
	{
		return orderId;
	}
	public void setOrderId(Integer orderId)
	{
		this.orderId = orderId;
	}
	public Integer getPaymentId()
	{
		return paymentId;
	}
	public void setPaymentId(Integer paymentId)
	{
		this.paymentId = paymentId;
	}
	public Integer getYear()
	{
		return year;
	}
	public void setYear(Integer year)
	{
		this.year = year;
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
	public Timestamp getDateOrdered()
	{
		return dateOrdered;
	}
	public void setDateOrdered(Timestamp dateOrdered)
	{
		this.dateOrdered = dateOrdered;
	}
	public String getDocStatusCode()
	{
		return docStatusCode;
	}
	public void setDocStatusCode(String docStatusCode)
	{
		this.docStatusCode = docStatusCode;
	}
	public String getPaymentStatus()
	{
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus)
	{
		this.paymentStatus = paymentStatus;
	}
	public String getShipmentStatus()
	{
		return shipmentStatus;
	}
	public void setShipmentStatus(String shipmentStatus)
	{
		this.shipmentStatus = shipmentStatus;
	}

	public String getInvoiceNo()
	{
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo)
	{
		this.invoiceNo = invoiceNo;
	}

	public String getPaymentNo()
	{
		return paymentNo;
	}

	public void setPaymentNo(String paymentNo)
	{
		this.paymentNo = paymentNo;
	}

	public String getShipmentNo()
	{
		return shipmentNo;
	}

	public void setShipmentNo(String shipmentNo)
	{
		this.shipmentNo = shipmentNo;
	}

	public Boolean getIsCustomer() {
		return isCustomer;
	}

	public void setIsCustomer(Boolean isCustomer) {
		this.isCustomer = isCustomer;
	}

	public Boolean getIsVendor() {
		return isVendor;
	}

	public void setIsVendor(Boolean isVendor) {
		this.isVendor = isVendor;
	}



	
}
