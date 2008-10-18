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
 * Created on May 3, 2006
 */


package org.posterita.beans;

import java.math.BigDecimal;


public class POSInfoBean extends UDIBean
{
    public String getOrgName() 
    {
		return orgName;
	}
	public void setOrgName(String orgName) 
	{
		this.orgName = orgName;
	}
	public String getPosName()
    {
        return posName;
    }
    public void setPosName(String posName) 
    {
        this.posName = posName;
    }
    
    public String getPaymentRule() {
        return paymentRule;
    }
    public void setPaymentRule(String paymentRule) {
        this.paymentRule = paymentRule;
    }
    public BigDecimal getOrderGrandTotal() {
        return orderGrandTotal;
    }
    public void setOrderGrandTotal(BigDecimal orderGrandTotal) {
        this.orderGrandTotal = orderGrandTotal;
    }
    
    public BigDecimal getTillGrandTotal() 
    {
        return tillGrandTotal;
    }
    public void setTillGrandTotal(BigDecimal tillGrandTotal)
    {
        this.tillGrandTotal = tillGrandTotal;
    }
    public BigDecimal getCashTotal()
    {
        return cashTotal;
    }
    
    public void setCashTotal(BigDecimal cashTotal)
    {
        this.cashTotal = cashTotal;
    }
    
    public BigDecimal getCardTotal() 
    {
        return cardTotal;
    }
    public void setCardTotal(BigDecimal cardTotal) 
    {
        this.cardTotal = cardTotal;
    }
    public BigDecimal getChequeTotal() 
    {
        return chequeTotal;
    }
    public void setChequeTotal(BigDecimal chequeTotal)
    {
        this.chequeTotal = chequeTotal;
    }
    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("\n POS NAME= "+ getPosName());
        buffer.append("\n PAYMENT RULE = " + getPaymentRule());
        buffer.append("\n TOTAL = "+getOrderGrandTotal());  
        
        
        return buffer.toString(); 
		
    }

}
