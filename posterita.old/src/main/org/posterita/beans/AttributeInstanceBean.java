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


public class AttributeInstanceBean
{
    private int attributeSetInstanceId;
    private int attributeId;
    private String attributeValue;
    private String attribute;
    private int attributeValueId;
    
    
    public int getAttributeId() 
    {
        
        return attributeId;
    }
    
    public void setAttributeId(int attributeId) 
    {
        this.attributeId = attributeId;
    }
    
    public int getAttributeSetInstanceId() 
    {
        return attributeSetInstanceId;
    }
    
    public void setAttributeSetInstanceId(int attributeSetInstanceId) 
    {
        this.attributeSetInstanceId = attributeSetInstanceId;
    }
    
    public String getAttribute()
    {
        return attribute;
    }
    
    public void setAttribute(String attribute)
    {
        this.attribute = attribute;
    }
    
    public String getAttributeValue()
    {
        return attributeValue;
    }
    public void setAttributeValue(String attributeValue)
    {
        this.attributeValue = attributeValue;
    }
    public int getAttributeValueId()
    {
        return attributeValueId;
    }
    
    public void setAttributeValueId(int attributeValueId)
    {
        this.attributeValueId = attributeValueId;
    }

    public boolean equals(Object o)
    {
        if (o == null)
            return false;
        
        AttributeInstanceBean bean = (AttributeInstanceBean) o;
        
        if (bean.getAttributeSetInstanceId() == this.getAttributeSetInstanceId())
            return true;
        
        return false;
    }
}
