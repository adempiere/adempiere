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
 * Created on Oct 31, 2006
 */


package org.posterita.beans;

import java.math.BigDecimal;


public class PaymentTermBean extends UDIBean
{
  

    
    
    
    
    
    
    public BigDecimal getDiscountAmt1() {
        return discountAmt1;
    }
    public void setDiscountAmt1(BigDecimal discountAmt1) {
        this.discountAmt1 = discountAmt1;
    }
    public BigDecimal getDiscountAmt2() {
        return discountAmt2;
    }
    public void setDiscountAmt2(BigDecimal discountAmt2) {
        this.discountAmt2 = discountAmt2;
    }
    public Integer getDiscountDay1() {
        return discountDay1;
    }
    public void setDiscountDay1(Integer discountDay1) {
        this.discountDay1 = discountDay1;
    }
    public Integer getDiscountDay2() {
        return discountDay2;
    }
    public void setDiscountDay2(Integer discountDay2) {
        this.discountDay2 = discountDay2;
    }
    public Boolean getAfterDelivery() {
        return afterDelivery;
    }
    public void setAfterDelivery(Boolean afterDelivery) {
        this.afterDelivery = afterDelivery;
    }
    public Integer getFiedMonthCutoff() {
        return fiedMonthCutoff;
    }
    public void setFiedMonthCutoff(Integer fiedMonthCutoff) {
        this.fiedMonthCutoff = fiedMonthCutoff;
    }
    public Boolean getFixedDueDate() {
        return fixedDueDate;
    }
    public void setFixedDueDate(Boolean fixedDueDate) {
        this.fixedDueDate = fixedDueDate;
    }
    public Integer getFixedMonthDay() {
        return fixedMonthDay;
    }
    public void setFixedMonthDay(Integer fixedMonthDay) {
        this.fixedMonthDay = fixedMonthDay;
    }
    public Integer getFixedMonthOffset() {
        return fixedMonthOffset;
    }
    public void setFixedMonthOffset(Integer fixedMonthOffset) {
        this.fixedMonthOffset = fixedMonthOffset;
    }
    public Boolean getNextBusinessday() {
        return nextBusinessday;
    }
    public void setNextBusinessday(Boolean nextBusinessday) {
        this.nextBusinessday = nextBusinessday;
    }
    public Integer getPaymentTermId() {
        return paymentTermId;
    }
    public void setPaymentTermId(Integer paymentTermId) {
        this.paymentTermId = paymentTermId;
    }
   
    public String getPaymentTermName() {
        return paymentTermName;
    }
    public void setPaymentTermName(String paymentTermName) {
        this.paymentTermName = paymentTermName;
    }
    public Integer getNetDays() {
        return netDays;
    }
    public void setNetDays(Integer netDays) {
        this.netDays = netDays;
    }
    
    

}
