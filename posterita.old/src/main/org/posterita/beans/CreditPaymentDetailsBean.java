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
 * Created on Oct 25, 2006
 */


package org.posterita.beans;

import java.math.BigDecimal;
import java.sql.Timestamp;


public class CreditPaymentDetailsBean extends UDIBean
{

  
    public Integer getCurrencyId() {
        return currencyId;
    }
    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }
    public String getCashBookName() {
        return cashBookName;
    }
    public void setCashBookName(String cashBookName) {
        this.cashBookName = cashBookName;
    }
    public Integer getCashId() {
        return cashId;
    }
    public void setCashId(Integer cashId) {
        this.cashId = cashId;
    }
    public Integer getCashLineId() {
        return cashLineId;
    }
    public void setCashLineId(Integer cashLineId) {
        this.cashLineId = cashLineId;
    }
    public String getCashType() {
        return cashType;
    }
    public void setCashType(String cashType) {
        this.cashType = cashType;
    }
    public String getCashTypeName() {
        return cashTypeName;
    }
    public void setCashTypeName(String cashTypeName) {
        this.cashTypeName = cashTypeName;
    }
    public String getChequeNo() {
        return chequeNo;
    }
    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }
    public String getCreditCardNumber() {
        return creditCardNumber;
    }
    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
    public Timestamp getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public BigDecimal getDiscountAmt() {
        return discountAmt;
    }
    public void setDiscountAmt(BigDecimal discountAmt) {
        this.discountAmt = discountAmt;
    }
    public String getDocumentNo() {
        return documentNo;
    }
    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }
    public BigDecimal getInvoiceGrandTotal() {
        return invoiceGrandTotal;
    }
    public void setInvoiceGrandTotal(BigDecimal invoiceGrandTotal) {
        this.invoiceGrandTotal = invoiceGrandTotal;
    }
    public Integer getInvoiceId() {
        return invoiceId;
    }
    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }
    public BigDecimal getPaidAmt() {
        return paidAmt;
    }
    public void setPaidAmt(BigDecimal paidAmt) {
        this.paidAmt = paidAmt;
    }
    public Integer getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }
    public String getTenderType() {
        return tenderType;
    }
    public void setTenderType(String tenderType) {
        this.tenderType = tenderType;
    }
    public Integer getUserID() {
        return userID;
    }
    public void setUserID(Integer userID) {
        this.userID = userID;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public BigDecimal getWriteOffAmt() {
        return writeOffAmt;
    }
    public void setWriteOffAmt(BigDecimal writeOffAmt) {
        this.writeOffAmt = writeOffAmt;
    }
    
    
    
    
    
    
    
}
