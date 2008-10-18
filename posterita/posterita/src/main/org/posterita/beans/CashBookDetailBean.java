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


public class CashBookDetailBean extends UDIBean
{    
    public String getTransferType() 
    {
        return transferType;
    }
    
    public void setTransferType(String transferType) 
    {
        this.transferType = transferType;
    }
    
    public BigDecimal getDifferenceAmt() 
    {
        return differenceAmt;
    }
    
    public void setDifferenceAmt(BigDecimal differenceAmt) 
    {
        this.differenceAmt = differenceAmt;
    }
    
    public BigDecimal getAdjustmentAmount() 
    {
        return adjustmentAmount;
    }
    
    public void setAdjustmentAmount(BigDecimal adjustmentAmount) 
    {
        this.adjustmentAmount = adjustmentAmount;
    }
    
    public Boolean getTransferAllAmount() 
    {
        return transferAllAmount;
    }
    public void setTransferAllAmount(Boolean transferAllAmount) 
    {
        this.transferAllAmount = transferAllAmount;
    }
    public String getBeginingBalanceAsString() 
    {
        return beginingBalanceAsString;
    }
    public void setBeginingBalanceAsString(String beginingBalanceAsString) 
    {
        this.beginingBalanceAsString = beginingBalanceAsString;
    }
    public String getEndingBalanceAsString() 
    {
        return endingBalanceAsString;
    }
    public void setEndingBalanceAsString(String endingBalanceAsString) 
    {
        this.endingBalanceAsString = endingBalanceAsString;
    }
    public BigDecimal getTransferAmount() 
    {
        return transferAmount;
    }
    public void setTransferAmount(BigDecimal transferAmount) 
    {
        this.transferAmount = transferAmount;
    }
    public Integer getCashJournalId() 
    {
        return cashJournalId;
    }
    public void setCashJournalId(Integer cashJournalId)
    {
        this.cashJournalId = cashJournalId;
    }
    public Integer getCashBookId()
    {
        return cashBookId;
    }
    
    public void setCashBookId(Integer cashBookId) 
    {
        this.cashBookId = cashBookId;
    }
    public String getCashJournalName() 
    {
        return cashJournalName;
    }
    public void setCashJournalName(String cashJournalName) 
    {
        this.cashJournalName = cashJournalName;
    }
    public String getCashJournalDisc() 
    {
        return cashJournalDisc;
    }
    public void setCashJournalDisc(String cashJournalDisc) 
    {
        this.cashJournalDisc = cashJournalDisc;
    }
    public void setDateAcct(Timestamp dateAcct) 
    {
        this.dateAcct = dateAcct;
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
    public BigDecimal getStatementDifference()
    {
        return statementDifference;
    }
    
    public void setStatementDifference(BigDecimal statementDifference)
    {
        this.statementDifference = statementDifference;
    }
    public String getDocStatus() {
        return docStatus;
    }
    public void setDocStatus(String docStatus) {
        this.docStatus = docStatus;
    }
    public String getCashBookName() 
    {
        return cashBookName;
    }
    
    public void setCashBookName(String cashBookName)
    {
        this.cashBookName = cashBookName;
    }
    
    public BigDecimal getCardTotal()
    {
        return cardTotal;
    }
    
    public void setCardTotal(BigDecimal cardTotal)
    {
        this.cardTotal=cardTotal;
    }
    
    public BigDecimal getChequeTotal()
    {
        return chequeTotal;
    }
    
    public void setChequeTotal(BigDecimal chequeTotal)
    {
        this.chequeTotal=chequeTotal;
    }
           
    public BigDecimal getCardDifference() 
    {
        return cardDifference;
    }
    public void setCardDifference(BigDecimal cardDifference) 
    {
        this.cardDifference = cardDifference;
    }
    public BigDecimal getChequeDifference() 
    {
        return chequeDifference;
    }
    public void setChequeDifference(BigDecimal chequeDifference) 
    {
        this.chequeDifference = chequeDifference;
    }
    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("\n The Begining Balance = "+getBeginingBalance());
        buffer.append("\n The Ending Balance = "+getEndingBalance());
        
        return buffer.toString(); 
        
    }

    /**
     * @return The description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description 
     *          The description to set
     */
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    private int cash_id;

	public int getCash_id() {
		return cash_id;
	}

	public void setCash_id(int cash_id) {
		this.cash_id = cash_id;
	}
	
}
