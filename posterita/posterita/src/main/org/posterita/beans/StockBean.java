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



public class StockBean extends UDIBean
{
    public String getKeyword2()
	{
		return keyword2;
	}

	public void setKeyword2(String keyword2)
	{
		this.keyword2 = keyword2;
	}

	public Integer getProductCategoryId()
    {
        return productCategoryId;
    }
    
    public void setProductCategoryId(Integer productCategoryId)
    {
        this.productCategoryId = productCategoryId;
    }
    
    public Integer getMake()
    {
        return make;
    }
    public void setMake(Integer make)
    {
        this.make = make;
    }
    
    public String getAvailableForBackOrderFilter() {
        
        return availableForBackOrderFilter;
    }
    
    public void setAvailableForBackOrderFilter(String availableForBackOrderFilter) 
    {
        this.availableForBackOrderFilter = availableForBackOrderFilter;
    }
    
    public Integer getModel()
    {
        return model;
    }
    
    public void setModel(Integer model)
    {
        this.model = model;
    }
    
    public Integer getColour()
    {
        return colour;
    }
    
    public void setColour(Integer colour)
    {
        this.colour = colour;
    }
    
    public Integer getTransmission()
    {
        return transmission;
    }
    
    public void setTransmission(Integer transmission)
    {
        this.transmission = transmission;
    }
    
    public Integer getYear()
    {
        return year;
    }
    
    public void setYear(Integer year)
    {
        this.year = year;
    }
    
    public Boolean getIsWholesaler()
    {
        return isWholesaler;
    }
    
    public void setIsWholesaler(Boolean isWholesaler)
    {
        this.isWholesaler = isWholesaler;
    }
    
    public Integer getRegionId()
    {
        return regionId;
    }
    
    public void setRegionId(Integer regionId)
    {
        this.regionId = regionId;
    }
    
        
    
    public Integer[] getCheckBox()
    {
        return checkBox;
    }
    public void setCheckBox(Integer [] checkBox)
    {
        this.checkBox = checkBox;
    }
    
    public Integer getReserveStatus()
    {
        return reserveStatus;
    }
    
    public void setReserveStatus(Integer reserveStatus)
    {
        this.reserveStatus = reserveStatus;
    }
    
    
    public Integer getOrgId()
    {
        return orgId;
    }
    
    public void setOrgId(Integer orgId)
    {
        this.orgId = orgId;
    }
    
    public Integer getAllocationId()
    {
        return allocationId;
    }
    
    public void setAllocationId(Integer allocationId)
    {
        this.allocationId = allocationId;
    }
    
    public String getWarehouseType()
    {
        return warehouseType;
    }
    
    public void setWarehouseType(String warehouseType)
    {
        this.warehouseType = warehouseType;
    }
    
    public Integer getFirst()
    {
        return first;
    }
    
    public void setFirst(Integer first)
    {
        this.first = first;
    }

	public Integer getWarehouseId() 
	{
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) 
	{
		this.warehouseId = warehouseId;
	}
	public Integer getDocumentId() {
        return documentId;
    }
    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }
    
    public String getETAFilter()
    {
        return ETAFilter;
    }
    
    public void setETAFilter(String filter)
    {
        ETAFilter = filter;
    }
    
    public Integer getDesign()
    {
        return design;
    }
    public void setDesign(Integer design)
    {
        this.design = design;
    }
    public Integer getSize()
    {
        return size;
    }
    public void setSize(Integer size)
    {
        this.size = size;
    }
    public Integer getStyle()
    {
        return style;
    }
    public void setStyle(Integer style)
    {
        this.style = style;
    }
    
    public Integer getColourAttributeValueId()
    {
        return colourAttributeValueId;
    }
    
    public void setColourAttributeValueId(Integer colourAttributeValueId)
    {
        this.colourAttributeValueId = colourAttributeValueId;
    }
    
    public Integer getDesignAttributeValueId()
    {
        return designAttributeValueId;
    }
    
    public void setDesignAttributeValueId(Integer designAttributeValueId)
    {
        this.designAttributeValueId = designAttributeValueId;
    }
    
    public Integer getModelAttributeValueId()
    {
        return modelAttributeValueId;
    }
    
    public void setModelAttributeValueId(Integer modelAttributeValueId)
    {
        this.modelAttributeValueId = modelAttributeValueId;
    }
    
    public Integer getSizeAttributeValueId()
    {
        return sizeAttributeValueId;
    }
    
    public void setSizeAttributeValueId(Integer sizeAttributeValueId)
    {
        this.sizeAttributeValueId = sizeAttributeValueId;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public String getOrderType()
    {
        return orderType;
    }
    
    public void setOrderType(String orderType)
    {
        this.orderType = orderType;
    }
    
    public Integer getAttributeId()
    {
        return attributeId;
    }
    
    public void setAttributeId(Integer attributeId)
    {
        this.attributeId = attributeId;
    }
    
    public String getKeyword()
    {
        return keyword;
    }
    
    public void setKeyword(String keyword)
    {
        this.keyword = keyword;
    }
    
    public String getBackOrder()
    {
        return backOrder;
    }
    
    public void setBackOrder(String backOrder)
    {
        this.backOrder = backOrder;
    }
    
    public String getFieldName()
    {
        return fieldName;
    }
    
    public void setFieldName(String fieldName)
    {
        this.fieldName = fieldName;
    }

	public Integer getProductId()
	{
		return productId;
	}

	public void setProductId(Integer productId)
	{
		this.productId = productId;
	}
}
