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


public class OpenItemBean extends UDIBean
{
    
   
	 public BigDecimal getAvailableAmt()
		{
			return AvailableAmt;
		}
		public void setAvailableAmt(BigDecimal availableAmt)
		{
			AvailableAmt = availableAmt;
		}
		public BigDecimal getPaymentAllocatedAmt()
		{
			return paymentAllocatedAmt;
		}
		public void setPaymentAllocatedAmt(BigDecimal paymentAllocatedAmt)
		{
			this.paymentAllocatedAmt = paymentAllocatedAmt;
		}
		public String getAllocationStatus()
		{
			return allocationStatus;
		}
		public void setAllocationStatus(String allocationStatus)
		{
			this.allocationStatus = allocationStatus;
		}
		public BigDecimal getAllocatedAmount() {
	        return allocatedAmount;
	    }
	    public void setAllocatedAmount(BigDecimal allocatedAmount) {
	        this.allocatedAmount = allocatedAmount;
	    }
	    
	    public Integer[] getPaymentIds() {
	        return paymentIds;
	    }
	    public void setPaymentIds(Integer[] paymentIds) {
	        this.paymentIds = paymentIds;
	    }
	    public String getTrxDate() {
	        return trxDate;
	    }
	    public void setTrxDate(String trxDate) {
	        this.trxDate = trxDate;
	    }
	    public BigDecimal getTaxedAmt() {
	        return taxedAmt;
	    }
	    public void setTaxedAmt(BigDecimal taxedAmt) {
	        this.taxedAmt = taxedAmt;
	    }
	    public Integer getPaymentId() {
	        return paymentId;
	    }
	    public void setPaymentId(Integer paymentId) {
	        this.paymentId = paymentId;
	    }
	    public String getPaymentNo() {
	        return paymentNo;
	    }
	    public void setPaymentNo(String paymentNo) {
	        this.paymentNo = paymentNo;
	    }
	   
	    
	    public String getTenderType() {
	        return tenderType;
	    }
	    public void setTenderType(String tenderType) {
	        this.tenderType = tenderType;
	    }
	    public String getForward() {
	    return forward;
	        }
	    public void setForward(String forward) {
	    this.forward = forward;
	    }
	    public String getPartnerName() {
	        return partnerName;
	    }
	    public void setPartnerName(String partnerName) {
	        this.partnerName = partnerName;
	    }
	    public String getPaymentTermName() {
	        return paymentTermName;
	    }
	    public void setPaymentTermName(String paymentTermName) {
	        this.paymentTermName = paymentTermName;
	    }
	    public String getInvoiceNo() {
	        return invoiceNo;
	    }
	    public void setInvoiceNo(String invoiceNo) {
	        this.invoiceNo = invoiceNo;
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
	    public String getInvoiceDate() {
	        return invoiceDate;
	    }
	    public void setInvoiceDate(String invoiceDate) {
	        this.invoiceDate = invoiceDate;
	    }
	    public Integer getBpartnerId() {
	        return bpartnerId;
	    }
	    public void setBpartnerId(Integer bpartnerId) {
	        this.bpartnerId = bpartnerId;
	    }
	    public Integer getPaymentTermId() {
	        return paymentTermId;
	    }
	    public Integer getCurrencyId() {
	        return currencyId;
	    }
	    public void setCurrencyId(Integer currencyId) {
	        this.currencyId = currencyId;
	    }
	   
	    public Integer getDaysDue() {
	        return daysDue;
	    }
	    public void setDaysDue(Integer daysDue) {
	        this.daysDue = daysDue;
	    }
	    public BigDecimal getDiscountAmt() {
	        return discountAmt;
	    }
	    public void setDiscountAmt(BigDecimal discountAmt) {
	        this.discountAmt = discountAmt;
	    }
	    public String getDiscountDate() {
	        return discountDate;
	    }
	    public void setDiscountDate(String discountDate) {
	        this.discountDate = discountDate;
	    }
	    public String getDocumentNo() {
	        return documentNo;
	    }
	    public void setDocumentNo(String documentNo) {
	        this.documentNo = documentNo;
	    }
	    public Timestamp getDueDate() {
	        return dueDate;
	    }
	    public void setDueDate(Timestamp dueDate) {
	        this.dueDate = dueDate;
	    }
	 
	    public Integer getInvoiceId() {
	        return invoiceId;
	    }
	    public void setInvoiceId(Integer invoiceId) {
	        this.invoiceId = invoiceId;
	    }
	    public Integer getInvoiceScheduleId() {
	        return invoiceScheduleId;
	    }
	    public void setInvoiceScheduleId(Integer invoiceScheduleId) {
	        this.invoiceScheduleId = invoiceScheduleId;
	    }
	    public Boolean getIsSoTrx() {
	        return isSoTrx;
	    }
	    public void setIsSoTrx(Boolean isSoTrx) {
	        this.isSoTrx = isSoTrx;
	    }
	    public Integer getNetDays() {
	        return netDays;
	    }
	    public void setNetDays(Integer netDays) {
	        this.netDays = netDays;
	    }
	    public BigDecimal getOpenAmt() {
	        return openAmt;
	    }
	    public void setOpenAmt(BigDecimal openAmt) {
	        this.openAmt = openAmt;
	    }
	    public Integer getOrderId() {
	        return orderId;
	    }
	    public void setOrderId(Integer orderId) {
	        this.orderId = orderId;
	    }
	    public BigDecimal getPaidAmt() {
	        return paidAmt;
	    }
	    public void setPaidAmt(BigDecimal paidAmt) {
	        this.paidAmt = paidAmt;
	    }
	    public Integer getPaumentTermId() {
	        return paymentTermId;
	    }
	    public void setPaymentTermId(Integer paymentTermId) {
	        this.paymentTermId = paymentTermId;
	    }
	    public BigDecimal getInvoiceGrandTotal() {
	        return invoiceGrandTotal;
	    }
	    public void setInvoiceGrandTotal(BigDecimal invoiceGrandTotal) {
	        this.invoiceGrandTotal = invoiceGrandTotal;
	    }
	 
	    public BigDecimal getOverUnderPayment() {
	        return overUnderPayment;
	    }
	    public void setOverUnderPayment(BigDecimal overUnderPayment) {
	        this.overUnderPayment = overUnderPayment;
	    }

	    public BigDecimal getPaymentAmt() {
	        return paymentAmt;
	    }
	    public void setPaymentAmt(BigDecimal paymentAmt) {
	        this.paymentAmt = paymentAmt;
	    }
	    public String getTrxType() {
	        return trxType;
	    }
	    public void setTrxType(String trxType) {
	        this.trxType = trxType;
	    }
	    public BigDecimal getWriteOffAmt() {
	        return writeOffAmt;
	    }
	    public void setWriteOffAmt(BigDecimal writeOffAmt) {
	        this.writeOffAmt = writeOffAmt;
	    }
	    public String getCurrencySymbole() {
	        return currencySymbole;
	    }
	    public void setCurrencySymbole(String currencySymbole) {
	        this.currencySymbole = currencySymbole;
	    }
	    public Integer[] getInvoiceIds() {
	        return invoiceIds;
	    }
	    public void setInvoiceIds(Integer[] invoiceIds) {
	        this.invoiceIds = invoiceIds;
	    }
    
}
