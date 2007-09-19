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


public class ItemBean extends UDIBean
{
   
    private Integer productId;
    private String description;
    private BigDecimal price;
    private Integer qty;
    private BigDecimal grandTotal;
    private BigDecimal discountPercent;
    private BigDecimal actualPrice;
    private BigDecimal taxAmt;
    private BigDecimal standardPrice;
    private BigDecimal priceTotal;
    private BigDecimal taxTotal;
    private BigDecimal qtyTotal;
    private BigDecimal unitPrice;
    
    
    
    
    
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getQtyTotal() {
        return qtyTotal;
    }

    public void setQtyTotal(BigDecimal qtyTotal) {
        this.qtyTotal = qtyTotal;
    }

    public BigDecimal getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(BigDecimal priceTotal) {
        this.priceTotal = priceTotal;
    }

    public BigDecimal getTaxTotal() {
        return taxTotal;
    }

    public void setTaxTotal(BigDecimal taxTotal) {
        this.taxTotal = taxTotal;
    }

    public BigDecimal getStandardPrice() {
        return standardPrice;
    }

    public void setStandardPrice(BigDecimal standardPrice) {
        this.standardPrice = standardPrice;
    }

    public BigDecimal getTaxAmt() {
        return taxAmt;
    }

    public void setTaxAmt(BigDecimal taxAmt) {
        this.taxAmt = taxAmt;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getBackOrder()
	{
		return backOrder;
	}
    
	public void setBackOrder(String backOrder)
	{
		this.backOrder = backOrder;
	}
	
	public BigDecimal getActualPrice() {
        return actualPrice;
    }
	
    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }
    public BigDecimal getDiscountPercent() {
        return discountPercent;
    }
    public void setDiscountPercent(BigDecimal discountPercent) {
        this.discountPercent = discountPercent;
    }
    public void setgrandTotal(BigDecimal grandTotal)
    {
        this.grandTotal=grandTotal;
    }
    
    public BigDecimal getGrandTotal()
    {
        return grandTotal;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public BigDecimal getPrice()
    {
        return price;
    }
    
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }
    
    public Integer getProductId()
    {
        return productId;
    }
    
    public void setProductId(Integer productId)
    {
        this.productId = productId;
    }
    
    public Integer getQty()
    {
        return qty;
    }
    
    public void setQty(Integer qty)
    {
        this.qty = qty;
    }
     
  
    public  void updateQuantity(ItemBean original, ItemBean itemToAdd,boolean add)
    {
        int originalQuantity = original.getQty().intValue();
        
        int quantityToAdd = itemToAdd.getQty().intValue();
       
        int finalQuantity=0;
        if(!add)
            finalQuantity=  originalQuantity - quantityToAdd;
        else
            finalQuantity=  originalQuantity + quantityToAdd;
     
            
        original.setQty(Integer.valueOf(finalQuantity));
    }
    
    public boolean equals(Object o)
    {
        if (o == null)
            return false;
        
        ItemBean bean1 = (ItemBean) o;
        if (bean1.getProductId().intValue() == this.getProductId().intValue())
            return true;
        
        return false;
       
    }
    public String getBarCode() {
        return barCode;
    }
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
     
}
