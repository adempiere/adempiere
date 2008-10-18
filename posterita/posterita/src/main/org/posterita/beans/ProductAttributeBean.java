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
 * Created on Jul 28, 2005 by praveen
 *
 */
package org.posterita.beans;

import java.util.ArrayList;

public class ProductAttributeBean extends UDIBean
{
    private ArrayList attributeSets;
    private ArrayList attributes;
    private ArrayList attributeValues;    
    
    public String getAttribute() 
    {
        return attribute;
    }
    
    public void setAttribute(String attribute)
    {
        this.attribute = attribute;
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
    
    public String getAttributeValue()
    {
        return attributeValue;
    }
    
    public void setAttributeValue(String attributeValue) 
    {
        this.attributeValue = attributeValue;
    }
    
    public ArrayList getAttributes()
    {
        return attributes;
    }
    
    public void setAttributes(ArrayList attributes) 
    {
        this.attributes = attributes;
    }
    
    public ArrayList getAttributeSets() 
    {
        return attributeSets;
    }
    
    public void setAttributeSets(ArrayList attributeSets) 
    {
        this.attributeSets = attributeSets;
    }
    
    public ArrayList getAttributeValues() 
    {
        return attributeValues;        
    }
    
    public void setAttributeValues(ArrayList attributeValues)
    {
        this.attributeValues = attributeValues;
    }
    
    public String getDescription() 
    {
        return description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }
    
    public Integer getProductCategoryId()
    {
        return productCategoryId;
    }
    
    public void setProductCategoryId(Integer productCategoryId)
    {
        this.productCategoryId = productCategoryId;
    }
    
     
    public Integer getAttributeValueId() {
        return attributeValueId;
    }
    public void setAttributeValueId(Integer attributeValueId) {
        this.attributeValueId = attributeValueId;
    }
    public Boolean getShowActiveOnly() {
        return showActiveOnly;
    }
    public void setShowActiveOnly(Boolean showActiveOnly) {
        this.showActiveOnly = showActiveOnly;
    }
    public Integer getUpdateAttrValueId() {
        return updateAttrValueId;
    }
    public void setUpdateAttrValueId(Integer updateAttrValueId) {
        this.updateAttrValueId = updateAttrValueId;
    }
}
