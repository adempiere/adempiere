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

public class ProductInstanceBean 
{
    private int attributeSetInstanceId;
    private int attributeId;
    private String serNo;
    private String description;
    private int qtyOnHand;
    
    public int getAttributeSetInstanceId()
    {
        return attributeSetInstanceId;
    }
    
    public void setAttributeSetInstanceId(int attributeSetInstanceId)
    {
        this.attributeSetInstanceId = attributeSetInstanceId;
    }
    
    public int getAttributeId()
    {
        return attributeId;
    }
    
    public void setAttributeId(int attributeId)
    {
        this.attributeId = attributeId;
    }
    
    public String getSerno()
    {
        return serNo;
    }
    
    public void setSerno(String serno)
    {
        this.serNo = serno;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    //this should return only on because a serial number(vin number)
    // must be unique
    public int getQtyOnHand()
    {
        return qtyOnHand;
    }
    
    public void setQtyOnHand(int qtyOnHand)
    {
        this.qtyOnHand = qtyOnHand;
    }
}
