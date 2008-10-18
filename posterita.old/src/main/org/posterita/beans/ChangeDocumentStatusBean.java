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


public class ChangeDocumentStatusBean extends DocumentBean
{
      
    public String getTrackingNo() 
    {
        return trackingNo;
    }
    public void setTrackingNo(String trackingNo) 
    {
        this.trackingNo = trackingNo;
    }
    public Integer getAllocationId()
    {
        return allocationId;
    }
    public void setAllocationId(Integer allocationId) 
    {
        this.allocationId = allocationId;
    }
    public String getPoReference()
    {
        return poReference;
    }
    
    public void setPoReference(String poReference)
    {
        this.poReference = poReference;
    }
    
    public void setAgreement(Boolean agreement)
    {
        this.agreement = agreement;
    }
    
    public Boolean getAgreement()
    {
        return agreement;
    }
    
    public String getSubmit()
    {
        return submit;
    }
    
    public void setSubmit(String submit)
    {
        this.submit = submit;
    }
    
	public Integer getTradeInOrderlineId() 
	{
		return tradeInOrderlineId;
	}
	
	public void setTradeInOrderlineId(Integer tradeInOrderlineId) 
	{
		this.tradeInOrderlineId = tradeInOrderlineId;
	}        
    
    
}
