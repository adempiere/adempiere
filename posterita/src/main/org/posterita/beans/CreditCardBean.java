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
 **/
package org.posterita.beans;

public class CreditCardBean extends UDIBean
{

    
	public String getPaymentMethod()
	{
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod)
	{
		this.paymentMethod = paymentMethod;
	}
	public String getCreditCardNumberEncrypted()
	{
		return creditCardNumberEncrypted;
	}
	public void setCreditCardNumberEncrypted(String creditCardNumberEncrypted)
	{
		this.creditCardNumberEncrypted = creditCardNumberEncrypted;
	}
	public String getCreditCardExpMonthName()
	{
		return creditCardExpMonthName;
	}
	public void setCreditCardExpMonthName(String creditCardExpMonthName)
	{
		this.creditCardExpMonthName = creditCardExpMonthName;
	}
	public String getAccountName()
	{
		return accountName;
	}
	public void setAccountName(String accountName)
	{
		this.accountName = accountName;
	}
	public String getCreditCardNumber()
	{
		return creditCardNumber;
	}
	
	public void setCreditCardNumber(String creditCardNumber)
	{
		this.creditCardNumber = creditCardNumber;
	}
	
	public String getCvv()
	{
		return cvv;
	}
	
	public void setCvv(String cvv)
	{
		this.cvv = cvv;
	}
	
	public Integer getCreditCardExpMonth()
	{
		return creditCardExpMonth;
	}
	
	public void setCreditCardExpMonth(Integer creditCardExpMonth)
	{
		this.creditCardExpMonth = creditCardExpMonth;
	}
	
	public Integer getCreditCardExpYear()
	{
		return creditCardExpYear;
	}
	
	public void setCreditCardExpYear(Integer creditCardExpYear)
	{
		this.creditCardExpYear = creditCardExpYear;
	}
	public String getCreditCardType()
	{
		return creditCardType;
	}
	public void setCreditCardType(String creditCardType)
	{
		this.creditCardType = creditCardType;
	}	

	
}
