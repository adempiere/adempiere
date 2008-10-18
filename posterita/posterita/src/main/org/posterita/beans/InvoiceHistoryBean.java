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

import java.sql.Timestamp;


public class InvoiceHistoryBean extends FilterBean
{	
	private PaymentHistoryBean paymentHistoryBean;
	
	public PaymentHistoryBean getPaymentHistoryBean()
	{
	    return paymentHistoryBean;
	}
	
	public void setPaymentHistoryBean(PaymentHistoryBean paymentHistoryBean)
	{
	    this.paymentHistoryBean = paymentHistoryBean;
	}

	public Integer getInvoiceId()
	{
		return invoiceId;
	}
	public void setInvoiceId(Integer invoiceId)
	{
		this.invoiceId = invoiceId;
	}
	public Timestamp getDateInvoiced()
	{
		return dateInvoiced;
	}
	public void setDateInvoiced(Timestamp dateInvoiced)
	{
		this.dateInvoiced = dateInvoiced;
	}
	
	public Integer getGrandTotal()
	{
		return grandTotal;
	}
	public void setGrandTotal(Integer grandTotal)
	{
		this.grandTotal = grandTotal;
	}
	public String getIsPaid()
	{
		return isPaid;
	}
	public void setIsPaid(String isPaid)
	{
		this.isPaid = isPaid;
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
	
	public Integer[] getCheckBox()
	{
	    return checkBox;
	}
	
	public void setCheckBox(Integer[] checkBox)
	{
	    this.checkBox = checkBox;
	}
	
}
