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
 * Created on Jun 15, 2006
 */


package org.posterita.beans;

import java.math.BigDecimal;
import java.util.ArrayList;


public class AttributeBean extends UDIBean
{
    public BigDecimal getUnitPrice()
    {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice)
	{
		this.unitPrice = unitPrice;
	}

	public BigDecimal getPrice()
	{
		return price;
	}
    
	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}
	
	public String getBarCode() 
	{
        return barCode;
    }
	
    public void setBarCode(String barCode) 
    {
        this.barCode = barCode;
    }
    
    public Integer[] getProductIds() 
    {
        return productIds;
    }
    
    public void setProductIds(Integer[] productIds) 
    {
        this.productIds = productIds;
    }
    
    public String getQtyNumberFilter() 
    {
        return qtyNumberFilter;
    }

    public void setQtyNumberFilter(String qtyNumberFilter) 
    {
        this.qtyNumberFilter = qtyNumberFilter;
    }

    public String getQtyFilter() 
    {
        return qtyFilter;
    }

    public void setQtyFilter(String qtyFilter) 
    {
        this.qtyFilter = qtyFilter;
    }
    
    public Integer getAttributeSetInstanceId() 
    {
        return attributeSetInstanceId;
    }
    
    public void setAttributeSetInstanceId(Integer attributeSetInstanceId) 
    {
        this.attributeSetInstanceId = attributeSetInstanceId;
    }
    
    public Integer getProductId() 
    {
        return productId;
    }
    public void setProductId(Integer productId) {
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
    
    public BigDecimal getQuantity() 
    {
        return quantity;
    }
    
    public void setQuantity(BigDecimal quantity) 
    {
        this.quantity = quantity;
    }
    
    public Integer getAttributeValueId() 
    {
        return attributeValueId;
    }
    
    public void setAttributeValueId(Integer attributeValueId) 
    {
        this.attributeValueId = attributeValueId;
    }
    
    public String getAttributeValue() 
    {
        return attributeValue;
    }
    
    public void setAttributeValue(String attributeValue) 
    {
        this.attributeValue = attributeValue;
    }
    
    public String getAttributeName() 
    {
        return attributeName;
    }
    
    public void setAttributeName(String attributeName) 
    {
        this.attributeName = attributeName;
    }
    
    public Integer getAttributeId() 
    {
        return attributeId;
    }
    
    public void setAttributeId(Integer attributeId) 
    {
        this.attributeId = attributeId;
    }
    
    public Integer getAttributeSetId() 
    {
        return attributeSetId;
    }
    
    public void setAttributeSetId(Integer attributeSetId) 
    {
        this.attributeSetId = attributeSetId;
    }
    
    public String getAttributeSetName() 
    {
        return attributeSetName;
    }
    
    public void setAttributeSetName(String attributeSetName) 
    {
        this.attributeSetName = attributeSetName;
    }
    
    public ArrayList getAttributeValueList() 
    {
        return attributeValueList;
    }
    
    public void setAttributeValueList(ArrayList attributeValueList) 
    {
        this.attributeValueList = attributeValueList;
    }
    
    public String[] getAttributeValueIds() 
    {
        return attributeValueIds;
    }
    
    public void setAttributeValueIds(String[] attributeValueIds) 
    {
        this.attributeValueIds = attributeValueIds;
    }
    public void setUom(String uom)
    {
    	this.uom = uom;
    }
    public String getUom()
    {
    	return this.uom;
    }

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
   
}
