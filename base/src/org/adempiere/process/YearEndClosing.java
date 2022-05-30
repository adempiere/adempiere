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
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;

import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MConversionType;
import org.compiere.model.MDocType;
import org.compiere.model.MElementValue;
import org.compiere.model.MGLCategory;
import org.compiere.model.MJournal;
import org.compiere.model.MJournalBatch;
import org.compiere.model.MJournalLine;
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
		String documentno = Msg.translate(Env.getCtx(), "Closing")+ "_"  + getDateAcct().toString();
		String description =  Msg.translate(Env.getCtx(), "Closing") + " "  + getDateAcct().toString();
		journalBatch.setAD_Org_ID(getOrgId());
		journalBatch.setDateAcct(getDateAcct());
		journalBatch.setDateDoc(getDateAcct());
		journalBatch.setDescription(documentno);
		journalBatch.setC_DocType_ID(getDocTypeId());
		journalBatch.setDocumentNo(description);
		journalBatch.setGL_Category_ID(getCategoryId());
		journalBatch.setControlAmt(Env.ZERO);
		journalBatch.setC_Currency_ID(acctSchema.getC_Currency_ID());
		journalBatch.save();
		
		String whereclause = "accounttype in ('E', 'R')  and ISSUMMARY = 'N' AND c_Element_ID=?";
		int elementId = DB.getSQLValueEx(get_TrxName(), 
				"SELECT C_Element_ID FROM C_AcctSchema_Element WHERE elementtype = 'AC' AND C_AcctSchema_ID=?", getAcctSchemaId());
		List<MElementValue> accounts = new Query(getCtx(), MElementValue.Table_Name, 
				whereclause, get_TrxName())
		.setClient_ID()
		.setParameters(elementId)
		.setOrderBy("value")
		.list();		
		for (MElementValue el : accounts) 
		{			
			balance = SearchBalance(el.getC_ElementValue_ID());
			if (balance.equals(Env.ZERO))
				continue;
			createJournal(el.getC_ElementValue_ID(), journalBatch, acctSchema, balance);
		}
		return "@Created@: " + journalBatch.getDocumentNo();		
	}	//	doIt
	
	private int createAccount(int elementValueId)
	{   
		
		
		MAccount acc = MAccount.get(getCtx(), getAD_Client_ID(),
		getOrgId(), getAcctSchemaId(), 
		elementValueId, 0,
		0, 0, 0, 
		0, 0, 0, 
		0, 0, 0,
		0, 0, 0,0,0,0, get_TrxName());
		if (acc == null)
			return 0;
		return acc.get_ID();
		
	}
	
	private Boolean createJournal(int elementValueID, MJournalBatch journalBatch, 
			MAcctSchema acctSchema, BigDecimal balance)
	{
		MJournal journal = new MJournal(journalBatch);
		int accountId = createAccount(elementValueID);
		MElementValue elementValue = new MElementValue(getCtx(), elementValueID, get_TrxName() );
		
		journal.setC_Currency_ID(acctSchema.getC_Currency_ID());
		journal.setC_AcctSchema_ID(acctSchema.get_ID());
		journal.setC_ConversionType_ID(MConversionType.getDefault(getAD_Client_ID()));
		journal.setDescription(Msg.translate(Env.getCtx(), "Closing") + " " + elementValue.getValue() + " " + elementValue.getName());
		journal.setDocumentNo(DB.getDocumentNo(getAD_Client_ID(), MJournal.Table_Name, get_TrxName()));
		journal.setGL_Category_ID(getCategoryId());
		journal.save();
		if (!createLines(accountId, journal, elementValueID, balance))
			return false;				
		return true;
	}
	
	private Boolean createLines(int accountID, MJournal journal, 
			int elementValueId, BigDecimal balance)
	{
		MElementValue el = new MElementValue(getCtx(), elementValueId, get_TrxName());
		String accounttype = el.getAccountType();
		MJournalLine dr = new MJournalLine(journal);
		MJournalLine cr = new MJournalLine(journal);
		if (balance.signum() == 0)
			return true;
		if (accounttype.equals('R'))
		{
			dr.setC_ValidCombination_ID(closingAccountId);
			dr.setAmtSourceDr(balance.negate());
			dr.save();
			cr.setC_ValidCombination_ID(accountID);
			cr.setAmtSourceCr(balance.negate());
			cr.save();
		}
		else
		{
			dr.setC_ValidCombination_ID(closingAccountId);
			dr.setAmtSourceDr(balance);
			dr.save();
			cr.setC_ValidCombination_ID(accountID);
			cr.setAmtSourceCr(balance);
			cr.save();
		}
		return true;
	}
	
	private BigDecimal SearchBalance(int elementValueId)
	{
		String sql = " SELECT COALESCE(sum(amtacctDr - amtacctCr), 0) FROM fact_acct f" +
			" WHERE dateacct BETWEEN Firstof( " + DB.TO_DATE(getDateAcct()) +
			", 'YY' ) AND " + DB.TO_DATE(getDateAcct()) + 
			" AND f.ad_client_ID = " + Env.getAD_Client_ID(getCtx()) + 
			" AND f.account_ID = " + elementValueId;
		BigDecimal saldo = DB.getSQLValueBD(get_TrxName(), sql);
		return saldo;
	}
	

	
}	//	YearEndClosing
