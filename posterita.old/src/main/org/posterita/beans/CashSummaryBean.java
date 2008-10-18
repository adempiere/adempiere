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
* Created on Sep 11, 2006 by ashley
* 
*/

/**
	@author ashley
 */

package org.posterita.beans;

import java.math.BigDecimal;

import org.compiere.util.Env;

public class CashSummaryBean extends UDIBean
{
	public CashSummaryBean()
	{
		this.setBankAcctTransferAmount(Env.ZERO);
		this.setChargeAmount(Env.ZERO);
		this.setDifferenceAmount(Env.ZERO);
		this.setGeneralExpenseAmount(Env.ZERO);
		this.setGeneralReceiptsAmount(Env.ZERO);
		this.setInvoiceAmount(Env.ZERO);
	}
	
	public Integer getCashId()
	{
		return cashId;
	}
	
	public void setCashId(Integer cashId)
	{
		this.cashId = cashId;
	}

	public BigDecimal getBankAcctTransferAmount()
	{
		return bankAcctTransferAmount;
	}

	public void setBankAcctTransferAmount(BigDecimal bankAcctTransferAmount)
	{
		this.bankAcctTransferAmount = bankAcctTransferAmount;
	}

	public BigDecimal getChargeAmount()
	{
		return chargeAmount;
	}

	public void setChargeAmount(BigDecimal chargeAmount)
	{
		this.chargeAmount = chargeAmount;
	}

	public BigDecimal getDifferenceAmount()
	{
		return differenceAmount;
	}

	public void setDifferenceAmount(BigDecimal differenceAmount)
	{
		this.differenceAmount = differenceAmount;
	}

	public BigDecimal getGeneralExpenseAmount()
	{
		return generalExpenseAmount;
	}

	public void setGeneralExpenseAmount(BigDecimal generalExpenseAmount)
	{
		this.generalExpenseAmount = generalExpenseAmount;
	}

	public BigDecimal getGeneralReceiptsAmount()
	{
		return generalReceiptsAmount;
	}

	public void setGeneralReceiptsAmount(BigDecimal generalReceiptsAmount)
	{
		this.generalReceiptsAmount = generalReceiptsAmount;
	}

	public BigDecimal getInvoiceAmount()
	{
		return invoiceAmount;
	}

	public void setInvoiceAmount(BigDecimal invoiceAmount)
	{
		this.invoiceAmount = invoiceAmount;
	}

}
