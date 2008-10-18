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
 * Created on Aug 19, 2005 by praveen
 *
 */
package org.posterita.beans;

import java.util.ArrayList;

public class FilterBean extends UDIBean
{
    private ArrayList bpList;
    private ArrayList docStatusList;
    public Integer getRefToQuote()
    {
        return refToQuote;
    }
    public void setRefToQuote(Integer refToQuote) 
    {
        this.refToQuote = refToQuote;
    }
    
    public String getOrderType() 
    {
        return orderType;
    }
    public void setOrderType(String orderType) 
    {
        this.orderType = orderType;
    }
    
	public String getDocStatus()
	{
		return docStatus;
	}
       
    public String getDocStatusCode()
    {
        return docStatusCode;
    }
	
	public String getDocumentNo()
	{
	    return documentNo;
	}
	
	public Integer getMonth() 
    {
        return month;
    }
    
	
    public Integer getPartnerId() 
    {
        return this.partnerId;
    }
    
    public String getPartnerName()
	{
		return partnerName;
	}
    
    public Integer getYear() 
    {
        return year;
    }
	public void setDocStatus(String docStatus)
	{
		this.docStatus = docStatus;
	}
    
    public void setDocStatusCode(String docStatusCode)
    {
        this.docStatusCode = docStatusCode;
    }
	
	public void setDocumentNo(String documentNo)
	{
	    this.documentNo = documentNo;
	}
    
    public void setMonth(Integer month) 
    {
        this.month = month;
    }
    
    public void setPartnerId(Integer partnerId) 
    {
        this.partnerId = partnerId;
    }  
	public void setPartnerName(String partnerName)
	{
		this.partnerName = partnerName;
	}
    
    public void setYear(Integer year) 
    {
        this.year = year;
    }
    
    public ArrayList getBpList()
    {
        return bpList;
    }
    
    public void setBpList(ArrayList bpList)
    {
        this.bpList = bpList;
    }
    
    public ArrayList getDocStatusList()
    {
        return docStatusList;
    }
    
    public void setDocStatusList(ArrayList docStatusList)
    {
        this.docStatusList = docStatusList;
    }

}
