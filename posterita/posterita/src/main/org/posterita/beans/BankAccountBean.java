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
 * Created on Jul 26, 2005 by praveen
 *
 */
package org.posterita.beans;

public class BankAccountBean extends UDIBean
{
    public String getAccountNo() 
    {
        return accountNo;
    }
    
    public void setAccountNo(String accountNo) 
    {
        this.accountNo = accountNo;
    }
    
    public String getAccountType() 
    {
        return accountType;
    }
    
    public void setAccountType(String accountType) 
    {
        this.accountType = accountType;
    }
    
    public Integer getBankId()
    {
        return bankId;
    }
    
    public void setBankId(Integer bankId) 
    {
        this.bankId = bankId;
    }
    
    public Double getCurrentBalance() 
    {
        return currentBalance;
    }
    
    public void setCurrentBalance(Double currentBalance)
    {
        this.currentBalance = currentBalance;
    }
     
    public String getBankName()
	{
		return bankName;
	}
    
	public void setBankName(String bankName)
	{
		this.bankName = bankName;
	}
	
	public String getSummary()
	{
	    return bankName + " - " + accountNo;
	}

    /**
     * @return the bankAccountId
     */
    public Integer getBankAccountId()
    {
        return bankAccountId;
    }

    /**
     * @param bankAccountId the bankAccountId to set
     */
    public void setBankAccountId(Integer bankAccountId)
    {
        this.bankAccountId = bankAccountId;
    }
}
