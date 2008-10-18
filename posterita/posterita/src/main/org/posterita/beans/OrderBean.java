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
 **/

/**
	@author ashley
 */

package org.posterita.beans;

import java.math.BigDecimal;

public class OrderBean
{
	private String orderType;
	private String docStatus;
	private String paymentRule;
	private Integer orderId;	
	private Integer paymentTermId;
	private Integer bpartnerId;
	private Integer salesRepId;
	private Integer priceListId;
	private Integer warehouseId;
	
	//payment details
	private BigDecimal amountTendered;
	private BigDecimal amountRefunded;
	private String cardType;
    private String cardNo;
    private String chequeNo;
    private BigDecimal cashAmt;
    private BigDecimal chequeAmt;
    private BigDecimal cardAmt;
    
    private BigDecimal writeOffAmt;
    private BigDecimal discountAmt;    
  
    //getters and setters    
    
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getDocStatus() {
		return docStatus;
	}
	public void setDocStatus(String docStatus) {
		this.docStatus = docStatus;
	}
	public String getPaymentRule() {
		return paymentRule;
	}
	public void setPaymentRule(String paymentRule) {
		this.paymentRule = paymentRule;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getPaymentTermId() {
		return paymentTermId;
	}
	public void setPaymentTermId(Integer paymentTermId) {
		this.paymentTermId = paymentTermId;
	}
	public Integer getBpartnerId() {
		return bpartnerId;
	}
	public void setBpartnerId(Integer bpartnerId) {
		this.bpartnerId = bpartnerId;
	}
	public Integer getSalesRepId() {
		return salesRepId;
	}
	public void setSalesRepId(Integer salesRepId) {
		this.salesRepId = salesRepId;
	}
	public Integer getPriceListId() {
		return priceListId;
	}
	public void setPriceListId(Integer priceListId) {
		this.priceListId = priceListId;
	}
	public Integer getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}
	public BigDecimal getAmountTendered() {
		return amountTendered;
	}
	public void setAmountTendered(BigDecimal amountTendered) {
		this.amountTendered = amountTendered;
	}
	public BigDecimal getAmountRefunded() {
		return amountRefunded;
	}
	public void setAmountRefunded(BigDecimal amountRefunded) {
		this.amountRefunded = amountRefunded;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}
	public BigDecimal getCashAmt() {
		return cashAmt;
	}
	public void setCashAmt(BigDecimal cashAmt) {
		this.cashAmt = cashAmt;
	}
	public BigDecimal getChequeAmt() {
		return chequeAmt;
	}
	public void setChequeAmt(BigDecimal chequeAmt) {
		this.chequeAmt = chequeAmt;
	}
	public BigDecimal getCardAmt() {
		return cardAmt;
	}
	public void setCardAmt(BigDecimal cardAmt) {
		this.cardAmt = cardAmt;
	}
	public BigDecimal getWriteOffAmt() {
		return writeOffAmt;
	}
	public void setWriteOffAmt(BigDecimal writeOffAmt) {
		this.writeOffAmt = writeOffAmt;
	}
	public BigDecimal getDiscountAmt() {
		return discountAmt;
	}
	public void setDiscountAmt(BigDecimal discountAmt) {
		this.discountAmt = discountAmt;
	}    
    
}
