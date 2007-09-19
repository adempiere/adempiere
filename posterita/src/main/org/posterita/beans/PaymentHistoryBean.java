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
 * Created on 05-Aug-2005 by alok
 *
 */
package org.posterita.beans;

import java.sql.Timestamp;


public class PaymentHistoryBean extends FilterBean
{
    private MinOutHistoryBean minoutHistoryBean;
    
    public MinOutHistoryBean getMinoutHistoryBean()
    {
        return minoutHistoryBean;
    }
    
    public void setMinoutHistoryBean(MinOutHistoryBean bean)
    {
        this.minoutHistoryBean = bean;
    }
    
	public String getCheckNo()
	{
		return checkNo;
	}
	public void setCheckNo(String checkNo)
	{
		this.checkNo = checkNo;
	}
	public Timestamp getDateTrx()
	{
		return dateTrx;
	}
	public void setDateTrx(Timestamp dateTrx)
	{
		this.dateTrx = dateTrx;
	}	
	
	public Integer getInvoiceId()
	{
		return invoiceId;
	}
	public void setInvoiceId(Integer invoiceId)
	{
		this.invoiceId = invoiceId;
	}
	public String getIsReceipt()
	{
		return isReceipt;
	}
	public void setIsReceipt(String isReceipt)
	{
		this.isReceipt = isReceipt;
	}
	public Integer getOrderId()
	{
		return orderId;
	}
	public void setOrderId(Integer orderId)
	{
		this.orderId = orderId;
	}
	
	public Integer getPayAmt()
	{
		return payAmt;
	}
	public void setPayAmt(Integer payAmt)
	{
		this.payAmt = payAmt;
	}
	public Integer getPaymentId()
	{
		return paymentId;
	}
	public void setPaymentId(Integer paymentId)
	{
		this.paymentId = paymentId;
	}
	
	
}
