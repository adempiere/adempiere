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

public class PaymentBean extends PaymentHistoryBean {
	private AttributeValuesPair attributeValuesPair;
	private String serno;

	public Integer[] getInvoiceIds() {
		return invoiceIds;
	}

	public void setInvoiceIds(Integer[] invoiceIds) {
		this.invoiceIds = invoiceIds;
	}

	public BigDecimal[] getAllocateAmount() {
		return allocateAmount;
	}

	public void setAllocateAmount(BigDecimal[] allocateAmount) {
		this.allocateAmount = allocateAmount;
	}

	
	public BigDecimal getAllocatedAmount() {
		return allocatedAmount;
	}

	public void setAllocatedAmount(BigDecimal allocatedAmount) {
		this.allocatedAmount = allocatedAmount;
	}

	public Integer getBpartnerId() {
		return bpartnerId;
	}

	public void setBpartnerId(Integer bpartnerId) {
		this.bpartnerId = bpartnerId;
	}

	public AttributeValuesPair getAttributeValuesPair() {
		return attributeValuesPair;
	}

	public void setAttributeValuesPair(AttributeValuesPair attributeValuesPair) {
		this.attributeValuesPair = attributeValuesPair;
	}

	public String getSerno() {
		return serno;
	}

	public void setSerno(String serno) {
		this.serno = serno;
	}

	public String getTrxType() {
		return trxType;
	}

	public void setTrxType(String trxType) {
		this.trxType = trxType;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public Boolean getIsSoTrx() {
		return isSoTrx;
	}

	public void setIsSoTrx(Boolean isSoTrx) {
		this.isSoTrx = isSoTrx;
	}    

}
