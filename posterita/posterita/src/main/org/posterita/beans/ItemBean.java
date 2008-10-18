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

import org.compiere.util.Env;


public class ItemBean 
{
   
    private Integer productId;
    private Integer priceListId;
    private Integer inventoryId;
    private Integer inventoryLineId;
    private String description;
    private String barCode;
    private BigDecimal price;
    private BigDecimal qty = Env.ONE;
    private BigDecimal grandTotal = Env.ZERO;
    private BigDecimal discountPercent = Env.ZERO;
    private BigDecimal actualPrice;
    private BigDecimal taxAmt;
    private BigDecimal standardPrice;
    private BigDecimal priceLimit;
    private BigDecimal priceLimitTotal;
    private BigDecimal priceTotal;
    private BigDecimal taxTotal;
    private BigDecimal qtyTotal = Env.ZERO;
    private BigDecimal unitPrice;
    private String productName;
    private BigDecimal inclPrice;
    private BigDecimal taxRate;
	private String uom;
	private String backOrder;
	private BigDecimal discountedLinePrice;
	private BigDecimal discountedInclUnitPrice;
    private Boolean isDiscountOnInclUnitPrice;
    private BigDecimal qtyCsv;
    private BigDecimal qtyBook;
    private BigDecimal qtyCount;
    private BigDecimal bookQtyValue;
    private BigDecimal countQtyValue;
	private BigDecimal listPrice;
	
    private Boolean isDiscountOnPercentage;
    private Boolean isDiscountOnTotal;
    private Boolean isTaxIncluded;
    
    private BigDecimal stockValue;
    private BigDecimal qtyToMove;
    private BigDecimal qtyOnHand;
    private Integer unitsPerPack;
    private Integer noOfPack;
    
    public Boolean getIsTaxIncluded() {
		return isTaxIncluded;
	}

	public void setIsTaxIncluded(Boolean isTaxIncluded) {
		this.isTaxIncluded = isTaxIncluded;
	}

	public Boolean getIsDiscountOnInclUnitPrice() {
        return isDiscountOnInclUnitPrice;
    }

    public void setIsDiscountOnInclUnitPrice(Boolean isDiscountOnInclUnitPrice) {
        this.isDiscountOnInclUnitPrice = isDiscountOnInclUnitPrice;
    }

    public BigDecimal getDiscountedLinePrice() {
        return discountedLinePrice;
    }

    public void setDiscountedLinePrice(BigDecimal discountedLinePrice) {
        this.discountedLinePrice = discountedLinePrice;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getInclPrice() {
        return inclPrice;
    }

    public void setInclPrice(BigDecimal inclPrice) {
        this.inclPrice = inclPrice;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

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
    
    public BigDecimal getQty()
    {
        return qty;
    }
    
    public void setQty(BigDecimal qty)
    {
        this.qty = qty;
    }
     
    /**
     * Update Quantity in order
     * @param original
     * @param itemToAdd
     * @param add
     */
    public  void updateQuantity(ItemBean original, ItemBean itemToAdd,boolean add)
    {
        BigDecimal originalQuantity = original.getQty();
        
        BigDecimal quantityToAdd = itemToAdd.getQty();
       
        BigDecimal finalQuantity = null;
        
        if(!add)
            finalQuantity=  originalQuantity.subtract(quantityToAdd);
        else
            finalQuantity=  originalQuantity.add(quantityToAdd);
     
            
        original.setQty(finalQuantity);
    }
    
    /**
     * Update Quantity count in inventory
     * @param original
     * @param itemToAdd
     * @param add
     */
    public  void updateQuantityCount(ItemBean original, ItemBean itemToAdd,boolean add)
    {
        BigDecimal originalQuantity = original.getQtyCount();
        
        BigDecimal quantityToAdd = itemToAdd.getQtyCount();
       
        BigDecimal finalQuantity = null;
        
        if(!add)
            finalQuantity=  originalQuantity.subtract(quantityToAdd);
        else
            finalQuantity=  originalQuantity.add(quantityToAdd);
     
            
        original.setQtyCount(finalQuantity);
    }
    
    /**
     * Update Quantity CSV in inventory
     * @param original
     * @param itemToAdd
     * @param add
     */
    public  void updateQuantityCsv(ItemBean original, ItemBean itemToAdd,boolean add)
    {
        BigDecimal originalQuantity = original.getQtyCsv();
        
        BigDecimal quantityToAdd = itemToAdd.getQtyCsv();
       
        BigDecimal finalQuantity = null;
        
        if(!add)
            finalQuantity=  originalQuantity.subtract(quantityToAdd);
        else
            finalQuantity=  originalQuantity.add(quantityToAdd);
     
            
        original.setQtyCsv(finalQuantity);
    }
    
    /**
     * Update Quantity to move in Stock Transfer
     * @param original
     * @param itemToAdd
     * @param add
     */
    public  void updateQuantityToMove(ItemBean original, ItemBean itemToAdd,boolean add)
    {
        BigDecimal originalQuantity = original.getQtyToMove();
        
        BigDecimal quantityToAdd = itemToAdd.getQtyToMove();
       
        BigDecimal finalQuantity = null;
        
        if(!add)
            finalQuantity=  originalQuantity.subtract(quantityToAdd);
        else
            finalQuantity=  originalQuantity.add(quantityToAdd);
                 
        original.setQtyToMove(finalQuantity);
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

    public Integer getPriceListId() {
        return priceListId;
    }

    public void setPriceListId(Integer priceListId) {
        this.priceListId = priceListId;
    }

    public BigDecimal getPriceLimit() {
        return priceLimit;
    }

    public void setPriceLimit(BigDecimal priceLimit) {
        this.priceLimit = priceLimit;
    }

    public BigDecimal getPriceLimitTotal() {
        return priceLimitTotal;
    }

    public void setPriceLimitTotal(BigDecimal priceLimitTotal) {
        this.priceLimitTotal = priceLimitTotal;
    }

    public BigDecimal getDiscountedInclUnitPrice() {
        return discountedInclUnitPrice;
    }

    public void setDiscountedInclUnitPrice(BigDecimal discountedInclUnitPrice) {
        this.discountedInclUnitPrice = discountedInclUnitPrice;
    }

    public Boolean getIsDiscountOnPercentage()
    {
        return isDiscountOnPercentage;
    }

    public void setIsDiscountOnPercentage(Boolean isDiscountOnPercentage)
    {
        this.isDiscountOnPercentage = isDiscountOnPercentage;
    }
	
	public BigDecimal getQtyBook()
    {
        return qtyBook;
    }
    public void setQtyBook(BigDecimal qtyBook)
    {
        this.qtyBook = qtyBook;
    }
    public BigDecimal getQtyCount()
    {
        return qtyCount;
    }
    public void setQtyCount(BigDecimal qtyCount)
    {
        this.qtyCount = qtyCount;
    }
    public BigDecimal getQtyCsv()
    {
        return qtyCsv;
    }
    public void setQtyCsv(BigDecimal qtyCsv)
    {
        this.qtyCsv = qtyCsv;
    }
    public BigDecimal getBookQtyValue() {
        return bookQtyValue;
    }
    public void setBookQtyValue(BigDecimal bookQtyValue) {
        this.bookQtyValue = bookQtyValue;
    }
    public Integer getInventoryLineId()
    {
        return inventoryLineId;
    }
    public void setInventoryLineId(Integer inventoryLineId)
    {
        this.inventoryLineId = inventoryLineId;
    }
    public Boolean getIsDiscountOnTotal()
    {
        return isDiscountOnTotal;
    }

    public BigDecimal getCountQtyValue()
    {
        return countQtyValue;
    }
    public void setCountQtyValue(BigDecimal countQtyValue)
    {
        this.countQtyValue = countQtyValue;
    }
    public Integer getInventoryId()
    {
        return inventoryId;
    }
    public void setInventoryId(Integer inventoryId)
    {
        this.inventoryId = inventoryId;
    }
     
    public void setIsDiscountOnTotal(Boolean isDiscountOnTotal)
    {
        this.isDiscountOnTotal = isDiscountOnTotal;
    }

	public BigDecimal getListPrice() {
		return listPrice;
	}

	public void setListPrice(BigDecimal listPrice) {
		this.listPrice = listPrice;
	}

    public BigDecimal getStockValue()
    {
        return stockValue;
    }

    public void setStockValue(BigDecimal stockValue)
    {
        this.stockValue = stockValue;
    }

    public BigDecimal getQtyToMove()
    {
        return qtyToMove;
    }

    public void setQtyToMove(BigDecimal qtyToMove)
    {
        this.qtyToMove = qtyToMove;
    }

    public BigDecimal getQtyOnHand()
    {
        return qtyOnHand;
    }

    public void setQtyOnHand(BigDecimal qtyOnHand)
    {
        this.qtyOnHand = qtyOnHand;
    }

    public Integer getUnitsPerPack()
    {
        return unitsPerPack;
    }

    public void setUnitsPerPack(Integer unitsPerPack)
    {
        this.unitsPerPack = unitsPerPack;
    }

    public Integer getNoOfPack()
    {
        return noOfPack;
    }

    public void setNoOfPack(Integer noOfPack)
    {
        this.noOfPack = noOfPack;
    }
}