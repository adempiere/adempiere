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

import java.sql.Timestamp;


public class DisplayMessageLogBean 
{
    private String input;
    private String output;
    private String status;
    private String type;
    private String documentType;
    private Timestamp date;
    private Integer documentId;
    private String reasonDescription;
    
    public Timestamp getDate() 
    {
        return date;
    }
    
    public void setDate(Timestamp date) 
    {
        this.date = date;
    }
    
    public String getDocumentType() 
    {
        return documentType;
    }
    
    public void setDocumentType(String documentType) 
    {
        this.documentType = documentType;
    }
    
    public String getInput() 
    {
        return input;
    }
    
    public void setInput(String input) 
    {
        this.input = input;
    }
    
    public String getOutput() 
    {
        return output;
    }
    
    public void setOutput(String output) 
    {
        this.output = output;
    }
    
    public String getStatus() 
    {
        return status;
    }
    
    public void setStatus(String status) 
    {
        this.status = status;
    }
    
    public String getType() 
    {
        return type;
    }
    
    public void setType(String type) 
    {
        this.type = type;
    }
    
    public Integer getDocumentId()
    {
        return documentId;
    }
    
    public void setDocumentId(Integer documentId)
    {
        this.documentId = documentId;
    }

	public String getReasonDescription()
	{
		return reasonDescription;
	}

	public void setReasonDescription(String reasonDescription)
	{
		this.reasonDescription = reasonDescription;
	}
}
