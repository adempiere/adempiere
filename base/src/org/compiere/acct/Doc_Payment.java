/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
package org.compiere.acct;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;

import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MBankAccount;
import org.compiere.model.MCharge;
import org.compiere.model.MPayment;
import org.compiere.model.MSysConfig;
import org.compiere.util.Env;

/**
 *  Post Invoice Documents.
 *  <pre>
 *  Table:              C_Payment (335)
 *  Document Types      ARP, APP
 *  </pre>
 *  @author Jorg Janke
 *  @version  $Id: Doc_Payment.java,v 1.3 2006/07/30 00:53:33 jjanke Exp $
 *  @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 *		<li> Add support to unidentified payments
 */
public class Doc_Payment extends Doc
{
	/**
	 *  Constructor
	 * 	@param ass accounting schemata
	 * 	@param rs record
	 * 	@param trxName trx
	 */
	public Doc_Payment (MAcctSchema[] ass, ResultSet rs, String trxName)
	{
		super (ass, MPayment.class, rs, null, trxName);
	}	//	Doc_Payment
	
	/**	Tender Type				*/
	private String		tenderType = null;
	/** Prepayment				*/
	private boolean		prepayment = false;
	/** Unidentified			*/
	private boolean		unidentified = false;
	/** Bank Account			*/
	private int			bankAccountId = 0;

	/**
	 *  Load Specific Document Details
	 *  @return error message or null
	 */
	protected String loadDocumentDetails ()
	{
		MPayment pay = (MPayment)getPO();
		setDateDoc(pay.getDateTrx());
		tenderType = pay.getTenderType();
		prepayment = pay.isPrepayment();
		unidentified = pay.isUnidentifiedPayment();
		bankAccountId = pay.getC_BankAccount_ID();
		//	Amount
		setAmount(Doc.AMTTYPE_Gross, pay.getPayAmt());
		return null;
	}   //  loadDocumentDetails

	
	/**************************************************************************
	 *  Get Source Currency Balance - always zero
	 *  @return Zero (always balanced)
	 */
	public BigDecimal getBalance()
	{
		BigDecimal retValue = Env.ZERO;
	//	log.config( toString() + " Balance=" + retValue);
		return retValue;
	}   //  getBalance

	/**
	 *  Create Facts (the accounting logic) for
	 *  ARP, APP.
	 *  <pre>
	 *  ARP
	 *      BankInTransit   DR
	 *      UnallocatedCash         CR
	 *      or Charge/C_Prepayment
	 *  APP
	 *      PaymentSelect   DR
	 *      or Charge/V_Prepayment
	 *      BankInTransit           CR
	 *  CashBankTransfer
	 *      -
	 *  </pre>
	 *  @param as accounting schema
	 *  @return Fact
	 */
	public ArrayList<Fact> createFacts (MAcctSchema as)
	{
		//  create Fact Header
		Fact fact = new Fact(this, as, Fact.POST_Actual);
		//	Cash Transfer
		if ("X".equals(tenderType) && !MSysConfig.getBooleanValue("CASH_AS_PAYMENT", true, getAD_Client_ID()))
		{
			ArrayList<Fact> facts = new ArrayList<Fact>();
			facts.add(fact);
			return facts;
		}

		int AD_Org_ID = getBank_Org_ID();		//	Bank Account Org	
		if (getDocumentType().equals(DOCTYPE_ARReceipt))
		{
			//	Asset
			FactLine fl = fact.createLine(null, getAccount(Doc.ACCTTYPE_BankInTransit, as),
				getC_Currency_ID(), getAmount(), null);
			if (fl != null && AD_Org_ID != 0)
				fl.setAD_Org_ID(AD_Org_ID);
			//	
			MAccount acct = null;
			if (getC_Charge_ID() != 0)
				acct = MCharge.getAccount(getC_Charge_ID(), as, getAmount());
			else if (prepayment)
				acct = getAccount(Doc.ACCTTYPE_C_Prepayment, as);
			else if(unidentified)
				acct = getAccount(Doc.ACCTTYPE_BankUnidentified, as);
			else
				acct = getAccount(Doc.ACCTTYPE_UnallocatedCash, as);
			fl = fact.createLine(null, acct,
				getC_Currency_ID(), null, getAmount());
			if (fl != null && AD_Org_ID != 0
				&& getC_Charge_ID() == 0)		//	don't overwrite charge
				fl.setAD_Org_ID(AD_Org_ID);
		}
		//  APP
		else if (getDocumentType().equals(DOCTYPE_APPayment))
		{
			MAccount acct = null;
			if (getC_Charge_ID() != 0)
				acct = MCharge.getAccount(getC_Charge_ID(), as, getAmount());
			else if (prepayment)
				acct = getAccount(Doc.ACCTTYPE_V_Prepayment, as);
			else if(unidentified)
				acct = getAccount(Doc.ACCTTYPE_BankUnidentified, as);
			else
				acct = getAccount(Doc.ACCTTYPE_PaymentSelect, as);
			FactLine fl = fact.createLine(null, acct,
				getC_Currency_ID(), getAmount(), null);
			if (fl != null && AD_Org_ID != 0
				&& getC_Charge_ID() == 0)		//	don't overwrite charge
				fl.setAD_Org_ID(AD_Org_ID);
			
			//	Asset
			fl = fact.createLine(null, getAccount(Doc.ACCTTYPE_BankInTransit, as),
				getC_Currency_ID(), null, getAmount());
			if (fl != null && AD_Org_ID != 0)
				fl.setAD_Org_ID(AD_Org_ID);
		}
		else
		{
			p_Error = "DocumentType unknown: " + getDocumentType();
			log.log(Level.SEVERE, p_Error);
			fact = null;
		}
		//
		ArrayList<Fact> facts = new ArrayList<Fact>();
		facts.add(fact);
		return facts;
	}   //  createFact

	/**
	 * 	Get AD_Org_ID from Bank Account
	 * 	@return AD_Org_ID or 0
	 */
	private int getBank_Org_ID ()
	{
		if (bankAccountId == 0)
			return 0;
		//
		MBankAccount ba = MBankAccount.get(getCtx(), bankAccountId);
		return ba.getAD_Org_ID();
	}	//	getBank_Org_ID
	
}   //  Doc_Payment
