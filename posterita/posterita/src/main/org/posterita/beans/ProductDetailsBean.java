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

public class ProductDetailsBean extends UDIBean
{
   
	public Integer getProductId()
	{
		return productId;
	}
	
	public void setProductId(Integer productId)
	{
		this.productId = productId;
	}

	public String getBarCode()
	{
		return barCode;
	}

	public void setBarCode(String barCode)
	{
		this.barCode = barCode;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Boolean getHasImage()
	{
		return hasImage;
	}

	public void setHasImage(Boolean hasImage)
	{
		this.hasImage = hasImage;
	}

	public String getProductName()
	{
		return productName;
	}

	public void setProductName(String productName)
	{
		this.productName = productName;
	}

	public String getRevenueRecognition()
	{
		return revenueRecognition;
	}

	public void setRevenueRecognition(String revenueRecognition)
	{
		this.revenueRecognition = revenueRecognition;
	}

	public Double getStdPurchasePrice()
	{
		return stdPurchasePrice;
	}

	public void setStdPurchasePrice(Double stdPurchasePrice)
	{
		this.stdPurchasePrice = stdPurchasePrice;
	}

	public Double getStdSalesPrice()
	{
		return stdSalesPrice;
	}

	public void setStdSalesPrice(Double stdSalesPrice)
	{
		this.stdSalesPrice = stdSalesPrice;
	}

	public String getTaxCategoryName()
	{
		return taxCategoryName;
	}

	public void setTaxCategoryName(String taxCategoryName)
	{
		this.taxCategoryName = taxCategoryName;
	}
	
	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
}
