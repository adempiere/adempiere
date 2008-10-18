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


public class WebInvoiceLineBean extends WebOrderLineBean
{
    private Integer invoiceId;
    private Integer qtyInvoiced;
    //amount including tax
    private BigDecimal lineTotalAmount;
    
    public BigDecimal getLineTotalAmt()
    {
        return lineTotalAmount;
    }
    
    public void setLineTotalAmt(BigDecimal lineTotalAmt)
    {
        this.lineTotalAmount = lineTotalAmt;
    }
       
    
    
    public Integer getInvoiceId()
    {
        return invoiceId;
    }
    
    public void setInvoiceId(Integer invoiceId)
    {
        this.invoiceId = invoiceId;
    }
    
    public Integer getQtyInvoiced()
    {
        return qtyInvoiced;
    }
    
    public void setQtyInvoiced(Integer qtyInvoiced)
    {
        this.qtyInvoiced = qtyInvoiced;
    }
}
