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
 * Created on Mar 14, 2006 by praveen
 *
 */
package org.posterita.beans;

import java.util.ArrayList;

import org.apache.struts.upload.FormFile;

import org.posterita.businesslogic.administration.ProductManager;


public class ProductImageBean extends UDIBean
{   
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public FormFile getFile() {
        return file;
    }
    public void setFile(FormFile file) {
        this.file = file;
    }    
    public Integer[] getProductIds() {
        return productIds;
    }
    public void setProductIds(Integer[] productIds) {
        this.productIds = productIds;
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
	public String[] getSizes()
	{
		return sizes;
	}
	public void setSizes(String[] sizes)
	{
		this.sizes = sizes;
	}
	
	public String toString()
	{
		StringBuffer productName = new StringBuffer();
		productName.append(brandName + ProductManager.PRODUCT_ATTRIBUTES_DELIMETER);
		productName.append(designName + ProductManager.PRODUCT_ATTRIBUTES_DELIMETER);
		productName.append(modelName + ProductManager.PRODUCT_ATTRIBUTES_DELIMETER);
		productName.append(colourName + ProductManager.PRODUCT_ATTRIBUTES_DELIMETER);
		
		return productName.toString();
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
	public String getImageName()
	{
		return imageName;
	}
	public void setImageName(String imageName)
	{
		this.imageName = imageName;
	}
	public ArrayList getProductsList()
	{
		return productsList;
	}
	public void setProductsList(ArrayList productsList)
	{
		this.productsList = productsList;
	}
	public String getBarCode()
	{
		return barCode;
	}
	public void setBarCode(String barCode)
	{
		this.barCode = barCode;
	}
}
