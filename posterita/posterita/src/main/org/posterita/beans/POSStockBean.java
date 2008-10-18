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
 * Created on May 8, 2006
 */


package org.posterita.beans;

import java.math.BigDecimal;
import java.util.HashMap;


public class POSStockBean extends UDIBean
{
    
    public Integer getOrgId() 
    {
		return orgId;
	}

	public void setOrgId(Integer orgId) 
	{
		this.orgId = orgId;
	}

	public String getUom() 
	{
		return uom;
	}

	public void setUom(String uom) 
	{
		this.uom = uom;
	}
	

	public String getQtyFilter() {
        return qtyFilter;
    }

    public void setQtyFilter(String qtyFilter) {
        this.qtyFilter = qtyFilter;
    }

    public Boolean getIsTextileProduct() {
        return isTextileProduct;
    }

    public void setIsTextileProduct(Boolean isTextileProduct) {
        this.isTextileProduct = isTextileProduct;
    }

    public Integer getAttributeSetInstanceId()
    {
        return attributeSetInstanceId;
    }

    public void setAttributeSetInstanceId(Integer attributeSetInstanceId)
    {
        this.attributeSetInstanceId = attributeSetInstanceId;
    }
    
    public HashMap getAttributeValuesMap() {
        return attributeValuesMap;
    }
    public void setAttributeValuesMap(HashMap attributeValuesMap) {
        this.attributeValuesMap = attributeValuesMap;
    }
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public BigDecimal getQuantity() {
        return quantity;
    }
    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
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
    public String getModelName() {
        return modelName;
    }
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
    public String getSizeName() {
        return sizeName;
    }
    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }
    
    public AttributeValuesPair getAttributeValuesPair() 
    {
        return attributeValuesPair;
    }
    
    public void setAttributeValuesPair(AttributeValuesPair attributeValuesPair) 
    {
        this.attributeValuesPair = attributeValuesPair;
    }

	public void setQtyOnHand(BigDecimal qtyAvailable) 
	{
		// TODO Auto-generated method stub
		
	}
    
    
    

}
