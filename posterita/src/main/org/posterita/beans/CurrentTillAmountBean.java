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
 * Created on May 5, 2006
 */


package org.posterita.beans;

import java.math.BigDecimal;


public class CurrentTillAmountBean extends UDIBean
{
    
    
    
    public BigDecimal getBeginingBalance() {
        return beginingBalance;
    }
    public void setBeginingBalance(BigDecimal beginingBalance) {
        this.beginingBalance = beginingBalance;
    }
    public BigDecimal getStatementDifference() {
        return statementDifference;
    }
    public void setStatementDifference(BigDecimal statementDifference) {
        this.statementDifference = statementDifference;
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
    
    
    public String getPosName() {
        return posName;
    }
    public void setPosName(String posName) {
        this.posName = posName;
    }
}
