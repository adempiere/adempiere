/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.adempiere.process;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MConversionType;
import org.compiere.model.MElementValue;
import org.compiere.model.MJournal;
import org.compiere.model.MJournalBatch;
import org.compiere.model.MJournalLine;
import org.compiere.model.MPeriod;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
 

/**
 *	Balances expense/income account to a selected account
 *	
 *  @author Susanne Calderon
 *  @version $Id: yearendclosing.java,v 1.2 2011/01/20$
 */
public class YearEndClosing extends YearEndClosingAbstract
{
	private int					closingAccountId = 0;
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		super.prepare();
	}	//	prepare

	/**
	 *  Perform process.
	 *  @return Message (clear text)
	 *  @throws Exception if not successful
	 */
	protected String doIt () throws Exception
	{
		MAcctSchema acctSchema = new MAcctSchema(getCtx(), getAcctSchemaId(), get_TrxName());
		closingAccountId = createAccount(getElementValueId());
		if (closingAccountId == 0)
			return "";
		MJournalBatch journalBatch = new MJournalBatch(getCtx(), 0, get_TrxName());	
		
			
		BigDecimal balance = Env.ZERO;
		String documentNo = Msg.translate(Env.getCtx(), "Closing")+ "_"  + getDateAcct().toString();
		journalBatch.setAD_Org_ID(getOrgId());
		journalBatch.setDateAcct(getDateAcct());
		journalBatch.setDateDoc(getDateAcct());
		journalBatch.setDescription(documentNo);
		journalBatch.setC_DocType_ID(getDocTypeId());
		journalBatch.setDocumentNo(documentNo);
		journalBatch.setGL_Category_ID(getCategoryId());
		journalBatch.setControlAmt(Env.ZERO);
		journalBatch.setC_Currency_ID(acctSchema.getC_Currency_ID());
		journalBatch.save();
		
		String whereClause = "accounttype IN ('E', 'R')  AND ISSUMMARY = 'N' AND c_Element_ID=?";
		int elementId = DB.getSQLValueEx(get_TrxName(), 
				"SELECT C_Element_ID FROM C_AcctSchema_Element WHERE elementtype = 'AC' AND C_AcctSchema_ID=?", getAcctSchemaId());
		List<MElementValue> elementValues = new Query(getCtx(), MElementValue.Table_Name, 
				whereClause, get_TrxName())
		.setClient_ID()
		.setParameters(elementId)
		.setOrderBy("value")
		.list();		
		for (MElementValue elementValue : elementValues) 
		{			

			log.info (elementValue.getValue());		
			balance = searchBalance(elementValue.getC_ElementValue_ID());
			if (balance.equals(Env.ZERO))
				continue;
			createJournal(elementValue, journalBatch, acctSchema, balance);
		}
		return "@Created@: " + journalBatch.getDocumentNo();		
	}	//	doIt
	
	private int createAccount(int elementValueId)
	{   
		
		
		MAccount account = MAccount.get(getCtx(), getAD_Client_ID(),
		getOrgId(), getAcctSchemaId(), 
		elementValueId, 0,
		0, 0, 0, 
		0, 0, 0, 
		0, 0, 0,
		0, 0, 0,0,0,0, get_TrxName());
		if (account == null)
			return 0;
		return account.get_ID();
		
	}
	
	private Boolean createJournal(MElementValue elementValue, MJournalBatch journalBatch, 
			MAcctSchema acctSchema, BigDecimal balance)
	{
		MJournal journal = new MJournal(journalBatch);
		int accountId = createAccount(elementValue.getC_ElementValue_ID());
		log.info (elementValue.getValue());		
		journal.setC_Currency_ID(acctSchema.getC_Currency_ID());
		journal.setC_AcctSchema_ID(acctSchema.get_ID());
		journal.setC_ConversionType_ID(MConversionType.getDefault(getAD_Client_ID()));
		journal.setDescription(Msg.translate(Env.getCtx(), "Closing") + " " + elementValue.getValue() + " " + elementValue.getName());
		journal.setDocumentNo(DB.getDocumentNo(getAD_Client_ID(), MJournal.Table_Name, get_TrxName()));
		journal.setGL_Category_ID(getCategoryId());
		journal.save();
		if (!createLines(accountId, journal, elementValue, balance))
			return false;				
		return true;
	}
	
	private Boolean createLines(int accountID, MJournal journal, 
			MElementValue elementValue, BigDecimal balance)
	{
		String accountType = elementValue.getAccountType();
		MJournalLine journalLine_debit = new MJournalLine(journal);
		MJournalLine journalLine_credit = new MJournalLine(journal);
		if (balance.signum() == 0)
			return true;
		if (accountType.equals(MElementValue.ACCOUNTTYPE_Revenue))
		{
			journalLine_debit.setC_ValidCombination_ID(closingAccountId);
			journalLine_debit.setAmtSourceDr(balance.negate());
			journalLine_debit.save();
			journalLine_credit.setC_ValidCombination_ID(accountID);
			journalLine_credit.setAmtSourceCr(balance.negate());
			journalLine_credit.save();
		}
		else
		{
			journalLine_debit.setC_ValidCombination_ID(closingAccountId);
			journalLine_debit.setAmtSourceDr(balance);
			journalLine_debit.save();
			journalLine_credit.setC_ValidCombination_ID(accountID);
			journalLine_credit.setAmtSourceCr(balance);
			journalLine_credit.save();
		}
		return true;
	}
	
	private BigDecimal searchBalance(int elementValueId)
	{
	    ArrayList<Object> params = new ArrayList<Object>();	    
	    params.add(MPeriod.getFirstInYear(getCtx(), getDateAcct(), 0).getStartDate()); 
	    params.add(getDateAcct()); 
	    params.add(elementValueId);
		String sql = " SELECT COALESCE(sum(amtacctDr - amtacctCr), 0) FROM fact_acct f" +
			" WHERE (dateacct BETWEEN ? AND ? )" +
			" AND f.account_ID = ? ";
		BigDecimal balance = DB.getSQLValueBDEx(get_TrxName(), sql, params);
		return balance;
	}
	

	
}	//	YearEndClosing
