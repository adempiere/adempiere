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
 * Created on May 3, 2006
 */


package org.posterita.beans;

import java.math.BigDecimal;
import java.sql.Timestamp;


public class CashBean extends UDIBean
{

    public Integer getCashId()
	{
		return cashId;
	}
	public void setCashId(Integer cashId)
	{
		this.cashId = cashId;
	}
	public Timestamp getDateAcct()
	{
		return dateAcct;
	}
	public void setDateAcct(Timestamp dateAcct)
	{
		this.dateAcct = dateAcct;
	}
	public Timestamp getDateCreated()
	{
		return dateCreated;
	}
	public void setDateCreated(Timestamp dateCreated)
	{
		this.dateCreated = dateCreated;
	}
	public Timestamp getDateUpdated()
	{
		return dateUpdated;
	}
	public void setDateUpdated(Timestamp dateUpdated)
	{
		this.dateUpdated = dateUpdated;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getStatmentDate()
	{
		return statmentDate;
	}
	public void setStatmentDate(String statmentDate)
	{
		this.statmentDate = statmentDate;
	}
	public String getCashJournalDisc() 
	{
        return cashJournalDisc;
    }
    public void setCashJournalDisc(String cashJournalDisc) 
    {
        this.cashJournalDisc = cashJournalDisc;
    }
    public Integer getCashJournalId() 
    {
        return cashJournalId;
    }
    public void setCashJournalId(Integer cashJournalId) 
    {
        this.cashJournalId = cashJournalId;
    }
    public String getCashJournalName() 
    {
        return cashJournalName;
    }
    public void setCashJournalName(String cashJournalName) 
    {
        this.cashJournalName = cashJournalName;
    }
    public BigDecimal getBeginingBalance()
    {
        return beginingBalance;
    }
    
    public void setBeginingBalance(BigDecimal beginingBalance)
    {
        this.beginingBalance = beginingBalance;
    }
    
    public BigDecimal getEndingBalance()
    {
        return endingBalance;
    }
    
    public void setEndingBalance(BigDecimal endingBalance)
    {
        this.endingBalance = endingBalance;
    }
    public String getDocStatus() 
    {
        return docStatus;
    }
    public void setDocStatus(String docStatus) 
    {
        this.docStatus = docStatus;
    }
    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("\n The Begining Balance = "+getBeginingBalance());
        buffer.append("\n The Ending Balance = "+getEndingBalance());
        
        return buffer.toString(); 		
    }
}

