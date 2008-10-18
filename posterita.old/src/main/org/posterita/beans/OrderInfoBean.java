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
 * Created on Nov 8, 2005 by praveen
 *
 */
package org.posterita.beans;

import java.sql.Timestamp;

public class OrderInfoBean 
{
    private Integer orderId;
    private Integer poReference;
    private Timestamp dateOrdered;
    private String docStatus;
    private String orderedFrom;
    private String vinNumber;
    private String reserved;
    private String assetStatus;
    private String orderBy;
    private String docType;
    private Integer attributeSetInstanceId;

    public Timestamp getDateOrdered() 
    {
        return dateOrdered;
    }
    
    public void setDateOrdered(Timestamp dateOrdered)
    {
        this.dateOrdered = dateOrdered;
    }
    
    public String getDocStatus() 
    {
        return docStatus;
    }
    
    public void setDocStatus(String docStatus)
    {
        this.docStatus = docStatus;
    }
    
    public String getOrderedFrom() 
    {
        return orderedFrom;
    }
    
    public void setOrderedFrom(String orderedFrom)
    {
        this.orderedFrom = orderedFrom;
    }
    
    public Integer getPoReference() 
    {
        return poReference;
    }
    
    public void setPoReference(Integer poReference)
    {
        this.poReference = poReference;
    }
    
    public Integer getOrderId()
    {
        return orderId;
    }
    
    public void setOrderId(Integer orderId)
    {
        this.orderId = orderId;
    }    
    
    public String getAssetStatus()
    {
        return assetStatus;
    }
    
    public void setAssetStatus(String assetStatus) 
    {
        this.assetStatus = assetStatus;
    }
    
    public String getDocType()
    {
        return docType;
    }
    
    public void setDocType(String docType) 
    {
        this.docType = docType;
    }
    
    public String getOrderBy()
    {
        return orderBy;
    }
    
    public void setOrderBy(String orderBy) 
    {
        this.orderBy = orderBy;
    }
    
    public String getReserved() 
    {
        return reserved;
    }
    
    public void setReserved(String reserved) 
    {
        this.reserved = reserved;
    }
    
    public String getVinNumber() 
    {
        return vinNumber;
    }
    
    public void setVinNumber(String vinNumber) 
    {
        this.vinNumber = vinNumber;
    }    
    
    public Integer getAttributeSetInstanceId() 
    {
        return attributeSetInstanceId;
    }
    
    public void setAttributeSetInstanceId(Integer attributeSetInstanceId) 
    {
        this.attributeSetInstanceId = attributeSetInstanceId;
    }
}
