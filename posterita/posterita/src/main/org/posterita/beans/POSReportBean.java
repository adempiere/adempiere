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
 * Created on May 11, 2006
 */


package org.posterita.beans;

import java.math.BigDecimal;


public class POSReportBean extends UDIBean
{

   
    
   
   
    
    
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public BigDecimal getQtyInventoryIn() {
        return qtyInventoryIn;
    }
    public void setQtyInventoryIn(BigDecimal qtyInventoryIn) {
        this.qtyInventoryIn = qtyInventoryIn;
    }
    public BigDecimal getQtyInventoryOut() {
        return qtyInventoryOut;
    }
    public void setQtyInventoryOut(BigDecimal qtyInventoryOut) {
        this.qtyInventoryOut = qtyInventoryOut;
    }
    public String getProductCategory() {
        return productCategory;
    }
    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
    public String getBrandName() {
        return brandName;
    }
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    public String getColourName() {
        return colourName;
    }
    public void setColourName(String colourName) {
        this.colourName = colourName;
    }
    public String getDesignName() {
        return designName;
    }
    public void setDesignName(String designName) {
        this.designName = designName;
    }
    public BigDecimal getEndingBalance()
    {
        return endingBalance;
    }
    
    public void setEndingBalance(BigDecimal endingBalance)
    {
        this.endingBalance = endingBalance;
    }
    public String getModelName() {
        return modelName;
    }
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
    public String getPartnerName() {
        return partnerName;
    }
    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }
    public BigDecimal getQuantity() {
        return quantity;
    }
    public void setQuantity(BigDecimal quantity) {
        this.quantity= quantity;
    }
    public String getSizeName() {
        return sizeName;
    }
    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }
    public BigDecimal getCloseingBalanceQty() {
        return closeingBalanceQty;
    }
    public void setCloseingBalanceQty(BigDecimal closeingBalanceQty) {
        this.closeingBalanceQty = closeingBalanceQty;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public BigDecimal getOpeningBalanceQty() {
        return openingBalanceQty;
    }
    public void setOpeningBalanceQty(BigDecimal openingBalanceQty) {
        this.openingBalanceQty = openingBalanceQty;
    }
    public BigDecimal getQtyOfGoodsReceived() {
        return qtyOfGoodsReceived;
    }
    public void setQtyOfGoodsReceived(BigDecimal qtyOfGoodsReceived) {
        this.qtyOfGoodsReceived = qtyOfGoodsReceived;
    }
    public BigDecimal getQtyOfGoodsReturned() {
        return qtyOfGoodsReturned;
    }
    public void setQtyOfGoodsReturned(BigDecimal qtyOfGoodsReturned) {
        this.qtyOfGoodsReturned = qtyOfGoodsReturned;
    }
    public BigDecimal getQtyOfGoodsSold() {
        return qtyOfGoodsSold;
    }
    public void setQtyOfGoodsSold(BigDecimal qtyOfGoodsSold) {
        this.qtyOfGoodsSold = qtyOfGoodsSold;
    }
    public BigDecimal getQtyReturnedByCustomer() {
        return qtyReturnedByCustomer;
    }
    public void setQtyReturnedByCustomer(BigDecimal qtyReturnedByCustomer) {
        this.qtyReturnedByCustomer = qtyReturnedByCustomer;
    }
    public void setUom(String uom)
    {
    	this.uom = uom;
    }
    public String getUom()
    {
    	return this.uom;
    }
}
