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
 * Mar 21, 2008 10:32:47 AM by praveen
 *
 */

package org.posterita.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ActionConfig;
import org.posterita.beans.CheckoutBean;
import org.posterita.struts.core.DefaultForm;

public class CheckoutForm extends DefaultForm
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1512494983524778853L;
	private String amountTendered;
    private String amountRefunded;
    private String cardNo;
    private String chequeNo;
    private String cashAmt;
    private String chequeAmt;
    private String cardAmt;
    private String bpartnerId;
    private String tenderType;
    private String[] discount;
    private String[] discountedPrice;
    private String[] isDiscOnInclUnitPrice;
    private String[] discInclUnitPrice;
    private String orderType;
    private String discountLimit;
    private String priceListId;
    private String toBeShipped = "true";
    private String discountedTotal;
    private String discountOnTotal;
    private String discountOnTotalPercent;
    private String overridePriceLimit;
    private String totalDiscount;
    private String[] isDiscOnPerc;
    private String[] isDiscOnTotal;
    private String[] qtyPerLine;
    private String m_productId;
    private String grandTotal;
   
    public CheckoutForm()
    {
        setBean(new CheckoutBean());
    }
    
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public String getRoleId() {
        return roleId;
    }
    
    public String getAmountTendered() {
        return amountTendered;
    }
    public void setAmountTendered(String amountTendered) {
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
    public String getCashAmt() {
        return cashAmt;
    }
    public void setCashAmt(String cashAmt) {
        this.cashAmt = cashAmt;
    }
    public String getChequeAmt() {
        return chequeAmt;
    }
    public void setChequeAmt(String chequeAmt) {
        this.chequeAmt = chequeAmt;
    }   
    public String getBpartnerId() {
        return bpartnerId;
    }
    public void setBpartnerId(String bpartnerId) {
        this.bpartnerId = bpartnerId;
    }
    public String getTenderType() {
        return tenderType;
    }
    public void setTenderType(String tenderType) {
        this.tenderType = tenderType;
    }
    
    public String getOrderType() {
        return orderType;
    }
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getDiscountLimit() {
        return discountLimit;
    }

    public void setDiscountLimit(String discountLimit) {
        this.discountLimit = discountLimit;
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
    {
        ActionConfig config = (ActionConfig) request.getAttribute("org.apache.struts.action.mapping.instance");
        if(config != null && input != null)
        {
            config.setInput(input);
        }
        
        ActionErrors errors = super.validate(mapping, request);
        
        if(errors!=null && !errors.isEmpty())
        {
            reset(mapping, request);
        }
        
        return errors;
    }

    public String[] getDiscount() {
        return discount;
    }

    public void setDiscount(String[] discount) {
        this.discount = discount;
    }

    public String getAmountRefunded() {
        return amountRefunded;
    }

    public void setAmountRefunded(String amountRefunded) {
        this.amountRefunded = amountRefunded;
    }

    public String getCardAmt() {
        return cardAmt;
    }

    public void setCardAmt(String cardAmt) {
        this.cardAmt = cardAmt;
    }

    public String[] getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(String[] discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public String getPriceListId() {
        return priceListId;
    }

    public void setPriceListId(String priceListId) {
        this.priceListId = priceListId;
    }

    public String getToBeShipped() {
        return toBeShipped;
    }

    public void setToBeShipped(String toBeShipped) {
        this.toBeShipped = toBeShipped;
    }

    public String getDiscountedTotal() {
        return discountedTotal;
    }

    public void setDiscountedTotal(String discountedTotal) {
        this.discountedTotal = discountedTotal;
    }

    public String getDiscountOnTotal() {
        return discountOnTotal;
    }

    public void setDiscountOnTotal(String discountOnTotal) {
        this.discountOnTotal = discountOnTotal;
    }

    public String getOverridePriceLimit() {
        return overridePriceLimit;
    }

    public void setOverridePriceLimit(String overridePriceLimit) {
        this.overridePriceLimit = overridePriceLimit;
    }

    public String getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(String totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public String[] getIsDiscOnInclUnitPrice() {
        return isDiscOnInclUnitPrice;
    }

    public void setIsDiscOnInclUnitPrice(String[] isDiscOnInclUnitPrice) {
        this.isDiscOnInclUnitPrice = isDiscOnInclUnitPrice;
    }

    public String[] getDiscInclUnitPrice() {
        return discInclUnitPrice;
    }

    public void setDiscInclUnitPrice(String[] discInclUnitPrice) {
        this.discInclUnitPrice = discInclUnitPrice;
    }
    
    public String[] getIsDiscOnPerc()
    {
        return isDiscOnPerc;
    }

    public void setIsDiscOnPerc(String[] isDiscOnPerc)
    {
        this.isDiscOnPerc = isDiscOnPerc;
    }

    public String[] getIsDiscOnTotal()
    {
        return isDiscOnTotal;
    }

    public void setIsDiscOnTotal(String[] isDiscOnTotal)
    {
        this.isDiscOnTotal = isDiscOnTotal;
    }

    public String[] getQtyPerLine()
    {
        return qtyPerLine;
    }

    public void setQtyPerLine(String[] qtyPerLine)
    {
        this.qtyPerLine = qtyPerLine;
    }

	public String getDiscountOnTotalPercent() {
		return discountOnTotalPercent;
	}

	public void setDiscountOnTotalPercent(String discountOnTotalPercent) {
		this.discountOnTotalPercent = discountOnTotalPercent;
	}

    public String getM_productId()
    {
        return m_productId;
    }

    public void setM_productId(String id)
    {
        m_productId = id;
    }

    public String getGrandTotal()
    {
        return grandTotal;
    }

    public void setGrandTotal(String grandTotal)
    {
        this.grandTotal = grandTotal;
    }
    
  

}