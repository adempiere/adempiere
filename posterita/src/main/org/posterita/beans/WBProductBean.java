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
* Created on May 3, 2006 by ashley
* 
*/

package org.posterita.beans;

import java.math.BigDecimal;

import org.posterita.businesslogic.ProductManager;

public class WBProductBean extends UDIBean
{    

    
	public String getProductClassification()
    {
        return productClassification;
    }

    public void setProductClassification(String productClassification) 
    {
        this.productClassification = productClassification;
    }

    public String getCurrency()
	{
		return currency;
	}

	public void setCurrency(String currency)
	{
		this.currency = currency;
	}

	public BigDecimal getPriceStandard()
	{
		return priceStandard;
	}
	
	public void setPriceStandard(BigDecimal priceStandard)
	{
		this.priceStandard = priceStandard;
	}
	
	public WBProductBean()
	{
		this.quantity = Integer.valueOf(0);
	}
	public String getBrandName()
	{
		return brandName;
	}
	public void setBrandName(String brandName)
	{
		this.brandName = brandName;
	}
	public String getColourName()
	{
		return colourName;
	}
	public void setColourName(String colourName)
	{
		this.colourName = colourName;
	}
	public String getDesignName()
	{
		return designName;
	}
	public void setDesignName(String designName)
	{
		this.designName = designName;
	}
	public String getModelName()
	{
		return modelName;
	}
	public void setModelName(String modelName)
	{
		this.modelName = modelName;
	}
	public String getSizeName()
	{
		return sizeName;
	}
	public void setSizeName(String sizeName)
	{
		this.sizeName = sizeName;
	}
	public String getKeyword1()
	{
		return keyword1;
	}
	public void setKeyword1(String keyword1)
	{
		this.keyword1 = keyword1;
	}
	public String getKeyword2()
	{
		return keyword2;
	}
	public void setKeyword2(String keyword2)
	{
		this.keyword2 = keyword2;
	}
	public Boolean getIsWebstoreFeatured()
	{
		return isWebstoreFeatured;
	}
	public void setIsWebstoreFeatured(Boolean isWebstoreFeatured)
	{
		this.isWebstoreFeatured = isWebstoreFeatured;
	}
	public Integer getProductId()
	{
		return productId;
	}
	public void setProductId(Integer productId)
	{
		this.productId = productId;
	}
	
	public String toString()
	{
		StringBuffer prodNameBuffer = new StringBuffer();
		prodNameBuffer.append(brandName + ProductManager.PRODUCT_ATTRIBUTES_DELIMETER);
		prodNameBuffer.append(designName + ProductManager.PRODUCT_ATTRIBUTES_DELIMETER);
		prodNameBuffer.append(modelName + ProductManager.PRODUCT_ATTRIBUTES_DELIMETER);
		prodNameBuffer.append(colourName + ProductManager.PRODUCT_ATTRIBUTES_DELIMETER);
		prodNameBuffer.append(sizeName + ProductManager.PRODUCT_ATTRIBUTES_DELIMETER);
		
		return prodNameBuffer.toString();
	}
	
	public String getMainAttributes()
	{
		StringBuffer prodNameBuffer = new StringBuffer();
		prodNameBuffer.append(brandName + ProductManager.PRODUCT_ATTRIBUTES_DELIMETER);
		prodNameBuffer.append(designName + ProductManager.PRODUCT_ATTRIBUTES_DELIMETER);
		prodNameBuffer.append(modelName + ProductManager.PRODUCT_ATTRIBUTES_DELIMETER);
		prodNameBuffer.append(colourName + ProductManager.PRODUCT_ATTRIBUTES_DELIMETER);
		return prodNameBuffer.toString();
	}
	
	public Integer getQuantity()
	{
		return quantity;
	}
	public void setQuantity(Integer quantity)
	{
		this.quantity = quantity;
	}
	public String getKeyword3()
	{
		return keyword3;
	}
	public void setKeyword3(String keyword3)
	{
		this.keyword3 = keyword3;
	}
	public String getKeyword4()
	{
		return keyword4;
	}
	public void setKeyword4(String keyword4)
	{
		this.keyword4 = keyword4;
	}

	public Boolean getIsSelfService()
	{
		return isSelfService;
	}

	public void setIsSelfService(Boolean isSelfService)
	{
		this.isSelfService = isSelfService;
	}

	public String getDocumentNote()
	{
		return documentNote;
	}

	public void setDocumentNote(String documentNote)
	{
		this.documentNote = documentNote;
	}
}
