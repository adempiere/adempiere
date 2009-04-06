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
 * 27 May 2008 12:27:14 by shameem
 *
 */

package org.posterita.beans;

import java.math.BigDecimal;
import java.util.ArrayList;

public class InventoryCartBean extends UDIBean
{
    private ArrayList<ItemBean> items;
    private String currency;
    private BigDecimal totalPrice; 
    private int pricelistId;    
    
    public String getCurrency()
    {
        return currency;
    }

    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

    public ArrayList<ItemBean> getItems()
    {
        return items;
    }
    
    public void setItems(ArrayList<ItemBean> items)
    {
        this.items = items;
    }
    
    public BigDecimal getTotalPrice()
    {
        return totalPrice;
    }
    
    public void setTotalPrice(BigDecimal totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public int getPricelistId() {
        return pricelistId;
    }

    public void setPricelistId(int pricelistId) {
        this.pricelistId = pricelistId;
    }
	
	public BigDecimal getQtyCount() {
		return qtyCount;
	}
	public void setQtyCount(BigDecimal qtyCount) {
		this.qtyCount = qtyCount;
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
	
	public BigDecimal getQtyBook() {
	        return qtyBook;
    }
    public void setQtyBook(BigDecimal qtyBook) {
        this.qtyBook = qtyBook;
    }

    public BigDecimal getQtyCsv()
    {
        return qtyCsv;
    }

    public void setQtyCsv(BigDecimal qtyCsv)
    {
        this.qtyCsv = qtyCsv;
    }
    
    public void setInventoryId(Integer inventoryId)
    {
        this.inventoryId = inventoryId;
    }
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getInventoryId()
    {
        return inventoryId;
    }
    public String getUom()
    {
        return uom;
    }
    public void setUom(String uom)
    {
        this.uom = uom;
    }
    public Integer getInventoryLineId()
    {
        return inventoryLineId;
    }
    public void setInventoryLineId(Integer inventoryLineId)
    {
        this.inventoryLineId = inventoryLineId;
    }

    public BigDecimal getCountQtyValue()
    {
        return countQtyValue;
    }
    public void setCountQtyValue(BigDecimal countQtyValue)
    {
        this.countQtyValue = countQtyValue;
    }

    public Integer getProductId()
    {
        return productId;
    }
    public void setProductId(Integer productId)
    {
        this.productId = productId;
    }
    public String getProductName()
    {
        return productName;
    }
    public void setProductName(String productName)
    {
        this.productName = productName;
    }
    public BigDecimal getBookQtyValue() {
        return bookQtyValue;
    }
    public void setBookQtyValue(BigDecimal bookQtyValue) {
        this.bookQtyValue = bookQtyValue;
    }
}
