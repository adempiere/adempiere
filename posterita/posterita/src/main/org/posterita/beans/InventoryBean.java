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
 * Created on Dec 4, 2006
 */


package org.posterita.beans;

import java.math.BigDecimal;


public class InventoryBean extends UDIBean
{

    public Integer[] getInventoryIds()
	{
		return inventoryIds;
	}
	public void setInventoryIds(Integer[] inventoryIds)
	{
		this.inventoryIds = inventoryIds;
	}
	public BigDecimal getBookQtyValue() {
        return bookQtyValue;
    }
    public void setBookQtyValue(BigDecimal bookQtyValue) {
        this.bookQtyValue = bookQtyValue;
    }
    public BigDecimal getCountQtyValue() {
        return countQtyValue;
    }
    public void setCountQtyValue(BigDecimal countQtyValue) {
        this.countQtyValue = countQtyValue;
    }
    public String getBarCode() {
        return barCode;
    }
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
    public String getDocStatus() {
        return docStatus;
    }
    public void setDocStatus(String docStatus) {
        this.docStatus = docStatus;
    }
    public String getInventoryNo() {
        return inventoryNo;
    }
    public void setInventoryNo(String inventoryNo) {
        this.inventoryNo = inventoryNo;
    }
    public String getMovementDate() {
        return movementDate;
    }
    public void setMovementDate(String movementDate) {
        this.movementDate = movementDate;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getInventoryId() {
        return inventoryId;
    }
    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }
    public Integer getInventoryLineId() {
        return inventoryLineId;
    }
    public void setInventoryLineId(Integer inventoryLineId) {
        this.inventoryLineId = inventoryLineId;
    }
    public BigDecimal getQtyBook() {
        return qtyBook;
    }
    public void setQtyBook(BigDecimal qtyBook) {
        this.qtyBook = qtyBook;
    }
    public BigDecimal getQtyCount() {
        return qtyCount;
    }
    public void setQtyCount(BigDecimal qtyCount) {
        this.qtyCount = qtyCount;
    }
    public void setUom(String uom)
    {
    	this.uom = uom;
    }
    public String getUom()
    {
    	return this.uom;
    }
	
    public String getIfAdd() {
		return ifAdd;
	}
	public void setIfAdd(String ifAdd) {
		this.ifAdd = ifAdd;
	}
	
	public Integer getPriceListId() {
		return priceListId;
	}
	public void setPriceListId(Integer priceListId) {
		this.priceListId = priceListId;
	}
   
    public BigDecimal getQtyCsv()
    {
        return qtyCsv;
    }
    public void setQtyCsv(BigDecimal qtyCsv)
    {
        this.qtyCsv = qtyCsv;
    }
    
    public Integer getMonth()
    {
        return month;
    }
    public void setMonth(Integer month)
    {
        this.month = month;
    }
    public Integer getYear()
    {
        return year;
    }
    public void setYear(Integer year)
    {
        this.year = year;
    }
    
}
