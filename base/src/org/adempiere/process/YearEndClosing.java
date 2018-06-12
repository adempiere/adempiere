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

import org.compiere.model.*;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
 

/**
 *	Balances expense/income account to a selected account
 *	
 *  @author Susanne Calderon
 *  @version $Id: yearendclosing.java,v 1.2 2011/01/20$
 */
public class YearEndClosing extends YearEndClosingAbstract
{

	/**
	 *  Perform process.
	 *  @return Message (clear text)
	 *  @throws Exception if not successful
	 */
	protected String doIt () throws Exception
	{
		MAcctSchema as = new MAcctSchema(getCtx(), getAcctSchemaId(), get_TrxName());
		MJournalBatch journalBatch = new MJournalBatch(getCtx(), 0, get_TrxName());
		BigDecimal balance = Env.ZERO;
		journalBatch.setAD_Org_ID(getOrgId());
		journalBatch.setDateAcct(getDateAcct());
		journalBatch.setDateDoc(getDateAcct());
		journalBatch.setDescription(Msg.getMsg(Env.getAD_Language(getCtx()), "Year-end Closing")
				+ getDateAcct());
		journalBatch.setC_DocType_ID(getDocTypeId());
		journalBatch.setDocumentNo(Msg.getMsg(Env.getAD_Language(getCtx()), "Year-end Closing")
				+ getDateAcct());
		journalBatch.setGL_Category_ID(getCategoryId());
		journalBatch.setControlAmt(Env.ZERO);
		journalBatch.setC_Currency_ID(as.getC_Currency_ID());
		journalBatch.setIsYearEndClosing(true);
		journalBatch.saveEx();
		
		String whereClause = "accounttype in ('E', 'R')  and ISSUMMARY = 'N' ";
		List<MElementValue> sourceAccounts = new Query(getCtx(), MElementValue.Table_Name,
				whereClause, get_TrxName())
		.setOrderBy("value")
		.list();
		for (MElementValue sourceAccount : sourceAccounts)
		{			
			balance = searchBalance(sourceAccount.getC_ElementValue_ID());
			if (balance.signum()==0 )
				continue;
			createJournal(as, journalBatch, sourceAccount, balance);
		}
		return  Msg.getMsg(Env.getAD_Language(getCtx()),"DocumentNo") + ": " + journalBatch.getDocumentNo();
	}	//	doIt

	
	private Boolean createJournal(MAcctSchema as, MJournalBatch journalBatch, MElementValue sourceAccount,
                                  BigDecimal balance)
	{
		MJournal journal = new MJournal(journalBatch);
		if (journal == null)
			return false;
		
		journal.setC_Currency_ID(as.getC_Currency_ID());
		journal.setC_AcctSchema_ID(as.get_ID());
		journal.setC_ConversionType_ID(114);
		journal.setDescription(Msg.getMsg(Env.getAD_Language(getCtx()), "Year-end Closing")
				+ sourceAccount.getValue() + " " + sourceAccount.getName());
		journal.setDocumentNo(DB.getDocumentNo(getAD_Client_ID(), MJournal.Table_Name, get_TrxName()));
		journal.setGL_Category_ID(getCategoryId());
		journal.saveEx();
		if (!createLines(journal,sourceAccount , balance))
			return false;				
		return true;
	}
	
	private Boolean createLines(MJournal journal, MElementValue sourceAccount, BigDecimal balance)
	{if (balance.signum() == 0)
        return true;
		MJournalLine debit = new MJournalLine(journal);
		MJournalLine credit = new MJournalLine(journal);

		if (sourceAccount.getAccountType().equals(MElementValue.ACCOUNTTYPE_Revenue))
		{
			debit.setAccount_ID(sourceAccount.getC_ElementValue_ID());
			debit.setAmtSourceDr(balance.negate());
			debit.setDescription(sourceAccount.getName());
			debit.saveEx();
			credit.setAccount_ID(getTargetAccountId());
			credit.setAmtSourceCr(balance.negate());
			credit.setDescription(Msg.getMsg(Env.getAD_Language(getCtx()), "Target"));
			credit.saveEx();
		}
		else
		{
            credit.setAccount_ID(sourceAccount.getC_ElementValue_ID());
            credit.setAmtSourceCr(balance);
            credit.setDescription(sourceAccount.getName());
            credit.saveEx();
            debit.setAccount_ID(getTargetAccountId());
            debit.setAmtSourceDr(balance);
            debit.setDescription(Msg.getMsg(Env.getAD_Language(getCtx()), "Target"));
            debit.saveEx();
		}
		return true;
	}
	
	private BigDecimal searchBalance(int cElementvalueID)
	{
        ArrayList<Object> parameters = new ArrayList<Object>();
        parameters.add(getDateAcct());
        parameters.add(getDateAcct());
        parameters.add(Env.getAD_Client_ID(getCtx()));
        parameters.add(cElementvalueID);
		String whereClause =
			" DATEACCT between Firstof(?,  'YY') AND ? " +
			" AND AD_CLIENT_ID = ?" +
			" AND ACCOUNT_ID = ?" +
            " AND PostingType = 'A'";
		BigDecimal balance = new Query(getCtx(), MFactAcct.Table_Name  , whereClause, get_TrxName())
                .setParameters(parameters)
                .aggregate("amtacctdr - amtacctcr" , Query.AGGREGATE_SUM);
		return balance;
	}
	

}	//	A_SHW_YearEndClosing
