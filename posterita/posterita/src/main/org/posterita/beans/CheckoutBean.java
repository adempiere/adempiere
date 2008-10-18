/**
 * 
 * Copyright (c) 2008 Posterita. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Posterita. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Posterita.
 *
 * POSTERITA MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR
 * A PARTICULAR PURPOSE, OR NON-INFRINGEMENT. TAMAK ICT SHALL NOT
 * BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING,
 * MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 *
 * Mar 21, 2008 10:31:54 AM by praveen
 *
 */

package org.posterita.beans;

import java.math.BigDecimal;

import org.compiere.model.MOrder;
import org.posterita.Constants;
import org.posterita.exceptions.OperationException;
import org.posterita.lib.UdiConstants;

public class CheckoutBean 
{
	private BigDecimal amountTendered;
	private BigDecimal amountRefunded;
	private String cardType;
    private String cardNo;
    private String chequeNo;
    private BigDecimal cashAmt;
    private BigDecimal chequeAmt;
    private BigDecimal cardAmt;
    private Integer bpartnerId;
    private String tenderType;
    private String paymentRule;
    private BigDecimal discount[];
    private BigDecimal discountedPrice[];
    private Boolean[] isDiscOnInclUnitPrice;
    private BigDecimal[] discInclUnitPrice;
    private String orderType;
    private BigDecimal discountLimit;
    private Integer priceListId;
    private Integer m_productId;
    private Boolean toBeShipped;
    private BigDecimal discountedTotal;
    private Boolean discountOnTotal;
    private Boolean discountOnTotalPercent;
    private Boolean overridePriceLimit;
    private Boolean isDiscountUptoLimitPrice;
    private BigDecimal totalDiscount;
    private BigDecimal writeOffAmt;
    private BigDecimal discountAmt;
    private BigDecimal totalAmount;
    private Boolean isOverridePinOk;
    private Boolean[] isDiscOnPerc;
    private Boolean[] isDiscOnTotal;
    private BigDecimal[] qtyPerLine;
    private BigDecimal grandTotal;
    protected Integer roleId;
          
    public BigDecimal[] getQtyPerLine()
    {
        return qtyPerLine;
    }
    public void setQtyPerLine(BigDecimal[] qtyPerLine)
    {
        this.qtyPerLine = qtyPerLine;
    }
    public Boolean[] getIsDiscOnPerc()
    {
        return isDiscOnPerc;
    }
    public void setIsDiscOnPerc(Boolean[] isDiscOnPerc)
    {
        this.isDiscOnPerc = isDiscOnPerc;
    }
    public Boolean[] getIsDiscOnTotal()
    {
        return isDiscOnTotal;
    }
    public void setIsDiscOnTotal(Boolean[] isDiscOnTotal)
    {
        this.isDiscOnTotal = isDiscOnTotal;
    }
    public Boolean getIsOverridePinOk() {
        return isOverridePinOk;
    }
    public void setIsOverridePinOk(Boolean isOverridePinOk) {
        this.isOverridePinOk = isOverridePinOk;
    }
    public BigDecimal getAmountTendered() {
        return amountTendered;
    }
    public void setAmountTendered(BigDecimal amountTendered) {
        this.amountTendered = amountTendered;
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
    public Integer getBpartnerId() {
        return bpartnerId;
    }
    public void setBpartnerId(Integer bpartnerId) {
        this.bpartnerId = bpartnerId;
    }
    public String getTenderType() {
        return tenderType;
    }
    public void setTenderType(String tenderType) throws OperationException {
        this.tenderType = tenderType;
        
        //set payment rule
        if(tenderType == null) return;
        
        if (Constants.PAYMENT_RULE_CASH.equalsIgnoreCase(tenderType))
        {
            paymentRule=MOrder.PAYMENTRULE_Cash;
        }
        else if(Constants.PAYMENT_RULE_CARD.equalsIgnoreCase(tenderType))
        {
            paymentRule=MOrder.PAYMENTRULE_CreditCard; 
        }
        else if(Constants.PAYMENT_RULE_CHEQUE.equalsIgnoreCase(tenderType))
        {
            paymentRule=MOrder.PAYMENTRULE_Check;
        }
        else if(Constants.PAYMENT_RULE_MIXED.equalsIgnoreCase(tenderType))
        {
            paymentRule=UdiConstants.PAYMENTRULE_MIXED;
        }
        else if(Constants.PAYMENT_RULE_CREDIT.equalsIgnoreCase(tenderType))
        {
            paymentRule=MOrder.PAYMENTRULE_OnCredit;
        }        
        else
        {
            throw new OperationException("Invalid Payment Rule :" + tenderType);
        }
    }
    public BigDecimal[] getDiscount() {
        return discount;
    }
    public void setDiscount(BigDecimal[] discount) {
        this.discount = discount;
    }
    public String getOrderType() {
        return orderType;
    }
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    public BigDecimal getDiscountLimit() {
        return discountLimit;
    }
    public void setDiscountLimit(BigDecimal discountLimit) {
        this.discountLimit = discountLimit;
    }
    public BigDecimal getAmountRefunded() {
        return amountRefunded;
    }
    public void setAmountRefunded(BigDecimal amountRefunded) {
        this.amountRefunded = amountRefunded;
    }
    public BigDecimal getCardAmt() {
        return cardAmt;
    }
    public void setCardAmt(BigDecimal cardAmt) {
        this.cardAmt = cardAmt;
    }
    public BigDecimal[] getDiscountedPrice() {
        return discountedPrice;
    }
    public void setDiscountedPrice(BigDecimal[] discountedPrice) {
        this.discountedPrice = discountedPrice;
    }
    public Integer getPriceListId() {
        return priceListId;
    }
    public void setPriceListId(Integer priceListId) {
        this.priceListId = priceListId;
    }
    public Boolean getToBeShipped() {
        return toBeShipped;
    }
    public void setToBeShipped(Boolean toBeShipped) {
        this.toBeShipped = toBeShipped;
    }
    public BigDecimal getDiscountedTotal() {
        return discountedTotal;
    }
    public void setDiscountedTotal(BigDecimal discountedTotal) {
        this.discountedTotal = discountedTotal;
    }
    public Boolean getDiscountOnTotal() {
        return discountOnTotal;
    }
    public void setDiscountOnTotal(Boolean discountOnTotal) {
        this.discountOnTotal = discountOnTotal;
    }
    public Boolean getOverridePriceLimit() {
        return overridePriceLimit;
    }
    public void setOverridePriceLimit(Boolean overridePriceLimit) {
        this.overridePriceLimit = overridePriceLimit;
    }
    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }
    public void setTotalDiscount(BigDecimal totalDiscount) {
        this.totalDiscount = totalDiscount;
    }
    /**
     * @return the cardType
     */
    public String getCardType()
    {
        return cardType;
    }
    /**
     * @param cardType the cardType to set
     */
    public void setCardType(String cardType)
    {
        this.cardType = cardType;
    }
    /**
     * @return the writeOffAmt
     */
    public BigDecimal getWriteOffAmt()
    {
        return writeOffAmt;
    }
    /**
     * @param writeOffAmt the writeOffAmt to set
     */
    public void setWriteOffAmt(BigDecimal writeOffAmt)
    {
        this.writeOffAmt = writeOffAmt;
    }
    /**
     * @return the discountAmt
     */
    public BigDecimal getDiscountAmt()
    {
        return discountAmt;
    }
    /**
     * @param discountAmt the discountAmt to set
     */
    public void setDiscountAmt(BigDecimal discountAmt)
    {
        this.discountAmt = discountAmt;
    }
    public Boolean[] getIsDiscOnInclUnitPrice() {
        return isDiscOnInclUnitPrice;
    }
    public void setIsDiscOnInclUnitPrice(Boolean[] isDiscOnInclUnitPrice) {
        this.isDiscOnInclUnitPrice = isDiscOnInclUnitPrice;
    }
    public BigDecimal[] getDiscInclUnitPrice() {
        return discInclUnitPrice;
    }
    public void setDiscInclUnitPrice(BigDecimal[] discInclUnitPrice) {
        this.discInclUnitPrice = discInclUnitPrice;
    }
	public Boolean getDiscountOnTotalPercent() {
		return discountOnTotalPercent;
	}
	public void setDiscountOnTotalPercent(Boolean discountOnTotalPercent) {
		this.discountOnTotalPercent = discountOnTotalPercent;
	}
    public String getPaymentRule() {
		return paymentRule;
	}
	public void setPaymentRule(String paymentRule) {
		this.paymentRule = paymentRule;
	}
	
	public String getPaymentRuleFromTenderType()
	{
		if (Constants.PAYMENT_RULE_CASH.equalsIgnoreCase(tenderType))
        {
            paymentRule=MOrder.PAYMENTRULE_Cash;
        }
        else if(Constants.PAYMENT_RULE_CARD.equalsIgnoreCase(tenderType))
        {
            paymentRule=MOrder.PAYMENTRULE_CreditCard; 
        }
        else if(Constants.PAYMENT_RULE_CHEQUE.equalsIgnoreCase(tenderType))
        {
            paymentRule=MOrder.PAYMENTRULE_Check;
        }
        else if(Constants.PAYMENT_RULE_MIXED.equalsIgnoreCase(tenderType))
        {
            paymentRule=UdiConstants.PAYMENTRULE_MIXED;
        }
        else if(Constants.PAYMENT_RULE_CREDIT.equalsIgnoreCase(tenderType))
        {
            paymentRule=MOrder.PAYMENTRULE_OnCredit;
        } 
		
		return paymentRule;        
		
	}
    public BigDecimal getTotalAmount()
    {
        return totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount)
    {
        this.totalAmount = totalAmount;
    }
    public Integer getM_productId()
    {
        return m_productId;
    }
    public void setM_productId(Integer id)
    {
        m_productId = id;
    }
    public Boolean getIsDiscountUptoLimitPrice()
    {
        return isDiscountUptoLimitPrice;
    }
    public void setIsDiscountUptoLimitPrice(Boolean isDiscountUptoLimitPrice)
    {
        this.isDiscountUptoLimitPrice = isDiscountUptoLimitPrice;
    }
    public BigDecimal getGrandTotal()
    {
        return grandTotal;
    }
    public void setGrandTotal(BigDecimal grandTotal)
    {
        this.grandTotal = grandTotal;
    }
    public Integer getRoleId()
    {
        return roleId;
    }
    public void setRoleId(Integer roleId)
    {
        this.roleId = roleId;
    }
    
}