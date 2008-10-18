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
 * Created on Oct 17, 2006
 */


package org.posterita.beans;

import java.math.BigDecimal;
import java.sql.Timestamp;


public class AgingBean extends UDIBean
{   
    
    public Integer getCurrencyId() 
    {
        return currencyId;
    }
    
    public void setCurrencyId(Integer currencyId) 
    {
        this.currencyId = currencyId;
    }
    
    public BigDecimal getDue0() 
    {
        return due0;
    }
    
    public void setDue0(BigDecimal due0) 
    {
        this.due0 = due0;
    }
    
    public BigDecimal getDue0_30() 
    {
        return due0_30;
    }
    
    public void setDue0_30(BigDecimal due0_30) 
    {
        this.due0_30 = due0_30;
    }
    
    public BigDecimal getDue1_7() 
    {
        return due1_7;
    }
    
    public void setDue1_7(BigDecimal due1_7) 
    {
        this.due1_7 = due1_7;
    }
    
    public BigDecimal getDue31_60() 
    {
        return due31_60;
    }
    
    public void setDue31_60(BigDecimal due31_60) 
    {
        this.due31_60 = due31_60;
    }
    
    public BigDecimal getDue61_90() 
    {
        return due61_90;
    }
    
    public void setDue61_90(BigDecimal due61_90) 
    {
        this.due61_90 = due61_90;
    }
    
    public BigDecimal getDue8_30() 
    {
        return due8_30;
    }
    
    public void setDue8_30(BigDecimal due8_30) 
    {
        this.due8_30 = due8_30;
    }
    
    public BigDecimal getDue91_PLUS() 
    {
        return due91_PLUS;
    }
    
    public void setDue91_PLUS(BigDecimal due91_PLUS) 
    {
        this.due91_PLUS = due91_PLUS;
    }
    
    public BigDecimal getDueAmt() 
    {
        return dueAmt;
    }
    
    public void setDueAmt(BigDecimal dueAmt) 
    {
        this.dueAmt = dueAmt;
    }
    
    public Timestamp getDueDate() 
    {
        return dueDate;
    }
    
    public void setDueDate(Timestamp dueDate) 
    {
        this.dueDate = dueDate;
    }
    
    public BigDecimal getInvoicedAmt() 
    {
        return invoicedAmt;
    }
    
    public void setInvoicedAmt(BigDecimal invoicedAmt) 
    {
        this.invoicedAmt = invoicedAmt;
    }
    
    public Integer getInvoiceId() 
    {
        return invoiceId;
    }
    
    public void setInvoiceId(Integer invoiceId) 
    {
        this.invoiceId = invoiceId;
    }
    
    public BigDecimal getOpenAmt() 
    {
        return openAmt;
    }
    
    public void setOpenAmt(BigDecimal openAmt) 
    {
        this.openAmt = openAmt;
    }
    
    public BigDecimal getPastDue1_30() 
    {
        return pastDue1_30;
    }
    
    public void setPastDue1_30(BigDecimal pastDue1_30) 
    {
        this.pastDue1_30 = pastDue1_30;
    }
    
    public BigDecimal getPastDue1_7() 
    {
        return pastDue1_7;
    }
    
    public void setPastDue1_7(BigDecimal pastDue1_7) 
    {
        this.pastDue1_7 = pastDue1_7;
    }
    
    public BigDecimal getPastDue31_60() 
    {
        return pastDue31_60;
    }
    
    public void setPastDue31_60(BigDecimal pastDue31_60) 
    {
        this.pastDue31_60 = pastDue31_60;
    }
    
    public BigDecimal getPastDue61_90() 
    {
        return pastDue61_90;
    }
    
    public void setPastDue61_90(BigDecimal pastDue61_90) 
    {
        this.pastDue61_90 = pastDue61_90;
    }
    
    public BigDecimal getPastDue8_30() 
    {
        return pastDue8_30;
    }
    
    public void setPastDue8_30(BigDecimal pastDue8_30) 
    {
        this.pastDue8_30 = pastDue8_30;
    }
    
    public BigDecimal getPastDue91_plus() 
    {
        return pastDue91_plus;
    }
    
    public void setPastDue91_plus(BigDecimal pastDue91_plus) 
    {
        this.pastDue91_plus = pastDue91_plus;
    }
    
    public BigDecimal getPastDueAmt() 
    {
        return pastDueAmt;
    }
    
    public void setPastDueAmt(BigDecimal pastDueAmt) 
    {
        this.pastDueAmt = pastDueAmt;
    }

}
