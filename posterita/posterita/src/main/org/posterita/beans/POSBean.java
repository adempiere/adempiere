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
 * Created on 17-Mar-2006
 */


package org.posterita.beans;

import java.math.BigDecimal;
import java.sql.Timestamp;


public class POSBean extends OrderLineBean 
{   
    public Timestamp getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }
    public String getCashJournalDisc() {
        return cashJournalDisc;
    }
    public void setCashJournalDisc(String cashJournalDisc) {
        this.cashJournalDisc = cashJournalDisc;
    }
    public Integer getCashJournalId() {
        return cashJournalId;
    }
    public void setCashJournalId(Integer cashJournalId) {
        this.cashJournalId = cashJournalId;
    }
    public String getCashJournalName() {
        return cashJournalName;
    }
    public void setCashJournalName(String cashJournalName) {
        this.cashJournalName = cashJournalName;
    }
    public Timestamp getDateAcct() {
        return dateAcct;
    }
    public void setDateAcct(Timestamp dateAcct) {
        this.dateAcct = dateAcct;
    }
    public String getDocStatus() {
        return docStatus;
    }
    public void setDocStatus(String docStatus) {
        this.docStatus = docStatus;
    }
    public Integer getGrandTotal() {
        return grandTotal;
    }
    public void setGrandTotal(Integer grandTotal) {
        this.grandTotal = grandTotal;
    }
    public String getPaymentRule() {
        return paymentRule;
    }
    public void setPaymentRule(String paymentRule) {
        this.paymentRule = paymentRule;
    }
    public Integer getPriceListId() {
        return priceListId;
    }
    public void setPriceListId(Integer priceListId) {
        this.priceListId = priceListId;
    }
    public String getPriceListName() {
        return priceListName;
    }
    public void setPriceListName(String priceListName) {
        this.priceListName = priceListName;
    }
    public String getWarehouseName() {
        return warehouseName;
    }
    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }
    public String getPosDesc() 
    {
        return posDesc;
    }
    public void setPosDesc(String posDesc)
    {
        this.posDesc = posDesc;
    }
    public Integer getPosId()
    {
        return posId;
    }
    public void setPosId(Integer posId)
    {
        this.posId = posId;
    }
    public String getPosName()
    {
        return posName;
    }
    public void setPosName(String posName) 
    {
        this.posName = posName;
    }
    public String getSaleRepName() 
    {
        return saleRepName;
    }
    public void setSaleRepName(String saleRepName)
    {
        this.saleRepName = saleRepName;
    }
    public Integer getSalesRepId()
    {
        return salesRepId;
    }
    public void setSalesRepId(Integer salesRepId) 
    {
        this.salesRepId = salesRepId;
    }
    public Integer getWarehouseId()
    {
        return warehouseId;
    }
    public void setWarehouseId(Integer warehouseId) 
    {
        this.warehouseId = warehouseId;
    }
    public Integer getCashBookId()
    {
        return cashBookId;
    }
    
    public void setCashBookId(Integer cashBookId) 
    {
        this.cashBookId = cashBookId;
    }
    
    public String getCashBookName() 
    {
        return cashBookName;
    }
    
    public void setCashBookName(String cashBookName)
    {
        this.cashBookName = cashBookName;
    }
    public String getTrxType() 
    {
        return trxType;
    }
    
    
    public void setTrxType(String trxType) 
    {
        this.trxType = trxType;
    }
    public BigDecimal getAmount()
    {
        return amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    public BigDecimal getBeginingBalance()
    {
        return beginingBalance;
    }
    
    public void setBeginingBalance(BigDecimal beginingBalance)
    {
        this.beginingBalance = beginingBalance;
    }
    
    public BigDecimal getEndingBalance()
    {
        return endingBalance;
    }
    
    public void setEndingBalance(BigDecimal endingBalance)
    {
        this.endingBalance = endingBalance;
    }
    
    public BigDecimal getStatementDifference()
    {
        return statementDifference;
    }
    
    public void setStatementDifference(BigDecimal statementDifference)
    {
        this.statementDifference = statementDifference;
    }
    
    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("\n POS NAME= "+ getPosName());
        buffer.append("\n PAYMENT RULE = " + getPaymentRule());
        buffer.append("\n TOTAL = "+getGrandTotal());  
        buffer.append("\n The Begining Balance = "+getBeginingBalance());
        buffer.append("\n The Ending Balance = "+getEndingBalance());
        
        return buffer.toString(); 
		
    }
}
