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
 * Created on Nov 6, 2006
 */


package org.posterita.beans;

import java.math.BigDecimal;



public class CommissionBean extends UDIBean
{
   
    public BigDecimal getSubtractAmt() {
        return subtractAmt;
    }
    public void setSubtractAmt(BigDecimal subtractAmt) {
        this.subtractAmt = subtractAmt;
    }
    public Integer getInvoiceId() {
        return invoiceId;
    }
    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }
    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public String getPeriodAndCurrencyDesc() {
        return periodAndCurrencyDesc;
    }
    public void setPeriodAndCurrencyDesc(String periodAndCurrencyDesc) {
        this.periodAndCurrencyDesc = periodAndCurrencyDesc;
    }
    public BigDecimal getAmtMultiplier() {
        return amtMultiplier;
    }
    public void setAmtMultiplier(BigDecimal amtMultiplier) {
        this.amtMultiplier = amtMultiplier;
    }
    public String getDocBasisType() {
        return docBasisType;
    }
    public void setDocBasisType(String docBasisType) {
        this.docBasisType = docBasisType;
    }
    public String getFrequencyType() {
        return frequencyType;
    }
    public void setFrequencyType(String frequencyType) {
        this.frequencyType = frequencyType;
    }
    public BigDecimal getActualAmt() {
        return actualAmt;
    }
    public void setActualAmt(BigDecimal actualAmt) {
        this.actualAmt = actualAmt;
    }
    public BigDecimal getActualQty() {
        return actualQty;
    }
    public void setActualQty(BigDecimal actualQty) {
        this.actualQty = actualQty;
    }
    public BigDecimal getCommissionAmt() {
        return commissionAmt;
    }
    public void setCommissionAmt(BigDecimal commissionAmt) {
        this.commissionAmt = commissionAmt;
    }
    public Integer getCommissionAmtId() {
        return commissionAmtId;
    }
    public void setCommissionAmtId(Integer commissionAmtId) {
        this.commissionAmtId = commissionAmtId;
    }
    public Integer getCommissionDetailId() {
        return commissionDetailId;
    }
    public void setCommissionDetailId(Integer commissionDetailId) {
        this.commissionDetailId = commissionDetailId;
    }
    public Integer getCommissionLineId() {
        return commissionLineId;
    }
    public void setCommissionLineId(Integer commissionLineId) {
        this.commissionLineId = commissionLineId;
    }
    public String getCommissionLineName() {
        return commissionLineName;
    }
    public void setCommissionLineName(String commissionLineName) {
        this.commissionLineName = commissionLineName;
    }
    public Integer getCommissionRunId() {
        return commissionRunId;
    }
    public void setCommissionRunId(Integer commissionRunId) {
        this.commissionRunId = commissionRunId;
    }
    public BigDecimal getConvertedAmt() {
        return convertedAmt;
    }
    public void setConvertedAmt(BigDecimal convertedAmt) {
        this.convertedAmt = convertedAmt;
    }
    public Integer getCurrencyId() {
        return currencyId;
    }
    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public Integer getInvoiceLineId() {
        return invoiceLineId;
    }
    public void setInvoiceLineId(Integer invoiceLineId) {
        this.invoiceLineId = invoiceLineId;
    }
    public Integer getOrderLineId() {
        return orderLineId;
    }
    public void setOrderLineId(Integer orderLineId) {
        this.orderLineId = orderLineId;
    }
    public String getReference() {
        return reference;
    }
    public void setReference(String reference) {
        this.reference = reference;
    }
     

}
