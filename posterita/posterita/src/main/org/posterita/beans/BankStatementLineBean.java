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
*/
package org.posterita.beans;

import java.math.BigDecimal;
import java.sql.Timestamp;


public class BankStatementLineBean 
{
    private Timestamp dateTrx;
    private String documentNo;
    private int paymentId;
    private int currencyId;
    private String isoCode;
    private BigDecimal payAmt;
    private BigDecimal currencyConvert;
    private String bpName;
    
    
    public String getBpName() 
    {
        return bpName;
    }
    
    public void setBpName(String bpName) 
    {
        this.bpName = bpName;
    }
    
    public BigDecimal getCurrencyConvert() 
    {
        return currencyConvert;
    }
    
    public void setCurrencyConvert(BigDecimal currencyConvert) 
    {
        this.currencyConvert = currencyConvert;
    }
    
    public int getCurrencyId() 
    {
        return currencyId;
    }
    
    public void setCurrencyId(int currencyId) 
    {
        this.currencyId = currencyId;
    }
    public Timestamp getDateTrx() 
    {
        return dateTrx;
    }
    
    public void setDateTrx(Timestamp dateTrx) 
    {
        this.dateTrx = dateTrx;
    }
    
    public String getDocumentNo() 
    {
        return documentNo;
    }
    
    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }
    
    public String getIsoCode() 
    {
        return isoCode;
    }
    
    
    public void setIsoCode(String isoCode) 
    {
        this.isoCode = isoCode;
    }
    public BigDecimal getPayAmt() {
        return payAmt;
    }
    
    public void setPayAmt(BigDecimal payAmt) 
    {
        this.payAmt = payAmt;
    }
    
    public int getPaymentId() 
    {
        return paymentId;
    }
    
    public void setPaymentId(int paymentId) 
    {
        this.paymentId = paymentId;
    }
}
