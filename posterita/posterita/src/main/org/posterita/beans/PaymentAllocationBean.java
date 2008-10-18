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
 * Created on Oct 30, 2006
 */

package org.posterita.beans;

import java.math.BigDecimal;

public class PaymentAllocationBean extends UDIBean {

	public Integer getCreditMemoId() {
		return creditMemoId;
	}

	public void setCreditMemoId(Integer creditMemoId) {
		this.creditMemoId = creditMemoId;
	}

	public String getCreditMemoNumber() {
		return creditMemoNumber;
	}

	public void setCreditMemoNumber(String creditMemoNumber) {
		this.creditMemoNumber = creditMemoNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getBpartnerId() {
		return bpartnerId;
	}

	public void setBpartnerId(Integer bpartnerId) {
		this.bpartnerId = bpartnerId;
	}

	public Integer getCashLineId() {
		return cashLineId;
	}

	public void setCashLineId(Integer cashLineId) {
		this.cashLineId = cashLineId;
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

	public Integer getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getOverUnderPayment() {
		return overUnderPayment;
	}

	public void setOverUnderPayment(BigDecimal overUnderPayment) {
		this.overUnderPayment = overUnderPayment;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public BigDecimal getWriteOffAmt() {
		return writeOffAmt;
	}

	public void setWriteOffAmt(BigDecimal writeOffAmt) {
		this.writeOffAmt = writeOffAmt;
	}
	
	public Boolean getIsCustomer() {
		return isCustomer;
	}

	public void setIsCustomer(Boolean isCustomer) {
		this.isCustomer = isCustomer;
	}

	public Boolean getIsVendor() {
		return isVendor;
	}

	public void setIsVendor(Boolean isVendor) {
		this.isVendor = isVendor;
	}

}
