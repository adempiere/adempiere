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
    public Integer getQtyInventoryIn() {
        return qtyInventoryIn;
    }
    public void setQtyInventoryIn(Integer qtyInventoryIn) {
        this.qtyInventoryIn = qtyInventoryIn;
    }
    public Integer getQtyInventoryOut() {
        return qtyInventoryOut;
    }
    public void setQtyInventoryOut(Integer qtyInventoryOut) {
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
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity= quantity;
    }
    public String getSizeName() {
        return sizeName;
    }
    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }
    public Integer getCloseingBalanceQty() {
        return closeingBalanceQty;
    }
    public void setCloseingBalanceQty(Integer closeingBalanceQty) {
        this.closeingBalanceQty = closeingBalanceQty;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Integer getOpeningBalanceQty() {
        return openingBalanceQty;
    }
    public void setOpeningBalanceQty(Integer openingBalanceQty) {
        this.openingBalanceQty = openingBalanceQty;
    }
    public Integer getQtyOfGoodsReceived() {
        return qtyOfGoodsReceived;
    }
    public void setQtyOfGoodsReceived(Integer qtyOfGoodsReceived) {
        this.qtyOfGoodsReceived = qtyOfGoodsReceived;
    }
    public Integer getQtyOfGoodsReturned() {
        return qtyOfGoodsReturned;
    }
    public void setQtyOfGoodsReturned(Integer qtyOfGoodsReturned) {
        this.qtyOfGoodsReturned = qtyOfGoodsReturned;
    }
    public Integer getQtyOfGoodsSold() {
        return qtyOfGoodsSold;
    }
    public void setQtyOfGoodsSold(Integer qtyOfGoodsSold) {
        this.qtyOfGoodsSold = qtyOfGoodsSold;
    }
    public Integer getQtyReturnedByCustomer() {
        return qtyReturnedByCustomer;
    }
    public void setQtyReturnedByCustomer(Integer qtyReturnedByCustomer) {
        this.qtyReturnedByCustomer = qtyReturnedByCustomer;
    }
}
