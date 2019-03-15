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
 * Contributor(s): Teo Sarca                                                  *
 *****************************************************************************/
package org.compiere.model;
 
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
 
/**
 *	Bank Statement Line Model
 *
 *	@author Eldir Tomassen/Jorg Janke
 *	@version $Id: MBankStatementLine.java,v 1.3 2006/07/30 00:51:02 jjanke Exp $
 *  
 *  Carlos Ruiz - globalqss - integrate bug fixing from Teo Sarca
 *    [ 1619076 ] Bank statement's StatementDifference becames NULL
 *
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 			<li>BF [ 1896880 ] Unlink Payment if TrxAmt is zero
 * 			<li>BF [ 1896885 ] BS Line: don't update header if after save/delete fails
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 *  	<li> FR [ 1699 ] Add support view for Bank Statement
 *  	<li> FR [ 1699 ] Add conversion for bank statement helper method #2403
 *  	@see https://github.com/adempiere/adempiere/issues/1699
 * @author Víctor Pérez Juárez Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution+
 * 		<li> FR [ 1699 ] Add functional programming #2403
 *      <a href="https://github.com/adempiere/adempiere/pull/2403">
 */
 public class MBankStatementLine extends X_C_BankStatementLine
 {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1914411222159254809L;

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_BankStatementLine_ID id
	 *	@param trxName transaction
	 */
	public MBankStatementLine (Properties ctx, int C_BankStatementLine_ID, String trxName)
	{
		super (ctx, C_BankStatementLine_ID, trxName);
		if (C_BankStatementLine_ID == 0)
		{
		//	setC_BankStatement_ID (0);		//	Parent
		//	setC_Charge_ID (0);
		//	setC_Currency_ID (0);	//	Bank Acct Currency
		//	setLine (0);	// @SQL=SELECT NVL(MAX(Line),0)+10 AS DefaultValue FROM C_BankStatementLine WHERE C_BankStatement_ID=@C_BankStatement_ID@
			setStmtAmt(Env.ZERO);
			setTrxAmt(Env.ZERO);
			setInterestAmt(Env.ZERO);
			setChargeAmt(Env.ZERO);
			setIsReversal (false);
		//	setValutaDate (new Timestamp(System.currentTimeMillis()));	// @StatementDate@
		//	setDateAcct (new Timestamp(System.currentTimeMillis()));	// @StatementDate@
		}
	}	//	MBankStatementLine
	
	/**
	 *	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MBankStatementLine (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MBankStatementLine
	
	/**
	 * 	Parent Constructor
	 * 	@param statement Bank Statement that the line is part of
	 */
	public MBankStatementLine(MBankStatement statement)
	{
		this (statement.getCtx(), 0, statement.get_TrxName());
		setClientOrg(statement);
		setC_BankStatement_ID(statement.getC_BankStatement_ID());
		setStatementLineDate(statement.getStatementDate());
	}	//	MBankStatementLine
	
	/**
	 * Create from import
	 * @param statement
	 * @param imp
	 */
	public MBankStatementLine(MBankStatement statement, X_I_BankStatement imp, int lineNo) {
		this(statement);
		//	Parent values
		MBankAccount account = MBankAccount.get(getCtx(), statement.getC_BankAccount_ID());
		if(account == null) {
			throw new AdempiereException("@C_BankAccount_ID@ @NotFound@");
		}
		//	set import values
		setReferenceNo(imp.getReferenceNo());
		setDescription(imp.getLineDescription());
		setStatementLineDate(imp.getStatementLineDate());
		setDateAcct(imp.getStatementLineDate());
		if(imp.getValutaDate() != null) {
			setValutaDate(imp.getValutaDate());
		}
		setIsReversal(imp.isReversal());
		if(imp.getC_Currency_ID() != 0) {
			setC_Currency_ID(imp.getC_Currency_ID());
		} else {
			setC_Currency_ID(account.getC_Currency_ID());
		}
		setTrxAmt(imp.getTrxAmt());
		setStmtAmt(imp.getStmtAmt());
		if (imp.getC_Charge_ID() != 0) {
			setC_Charge_ID(imp.getC_Charge_ID());
		}
		setInterestAmt(imp.getInterestAmt());
		setChargeAmt(imp.getChargeAmt());
		setMemo(imp.getMemo());
		if (imp.getC_Payment_ID() != 0) {
			setC_Payment_ID(imp.getC_Payment_ID());
		}
		//	Copy statement line reference data
		setEftTrxID(imp.getEftTrxID());
		setEftTrxType(imp.getEftTrxType());
		setEftCheckNo(imp.getEftCheckNo());
		setEftReference(imp.getEftReference());
		setEftMemo(imp.getEftMemo());
		setEftPayee(imp.getEftPayee());
		setEftPayeeAccount(imp.getEftPayeeAccount());
		setEftStatementLineDate(imp.getEftStatementLineDate());
		setEftValutaDate(imp.getEftValutaDate());
		setEftCurrency(imp.getEftCurrency());
		setEftAmt(imp.getEftAmt());
		setLine(lineNo);
	}

	/**
	 * 	Parent Constructor
	 * 	@param statement Bank Statement that the line is part of
	 * 	@param lineNo position of the line within the statement
	 */
	public MBankStatementLine(MBankStatement statement, int lineNo)
	{
		this (statement);
		setLine(lineNo);
	}	//	MBankStatementLine


	/**
	 * 	Set Statement Line Date and all other dates (Valuta, Acct)
	 *	@param StatementLineDate date
	 */
	public void setStatementLineDate(Timestamp StatementLineDate)
	{
		super.setStatementLineDate(StatementLineDate);
		setValutaDate (StatementLineDate);
		setDateAcct (StatementLineDate);
	}	//	setStatementLineDate

	/**
	 * 	Set Payment
	 *	@param payment payment
	 */
	public void setPayment (MPayment payment) {
        BigDecimal paymentAmount = payment.getPayAmt(true);
        int currencyId = payment.getC_Currency_ID();
        
        MBankAccount bankAccount = MBankAccount.get(getCtx(), getParent().getC_BankAccount_ID());
        if(bankAccount.getC_Currency_ID() != payment.getC_Currency_ID()) {
            currencyId = bankAccount.getC_Currency_ID();
            // Get Currency Info
            MCurrency currency = MCurrency.get (getCtx(),bankAccount.getC_Currency_ID());
            Timestamp conversionDate = getParent().getStatementDate();
    
            // Get Currency Rate
            Optional<BigDecimal> maybeCurrencyRate = Optional.ofNullable(MConversionRate.getRate (payment.getC_Currency_ID(),
                    bankAccount.getC_Currency_ID(), conversionDate, payment.getC_ConversionType_ID(), payment.getAD_Client_ID(),
                    payment.getAD_Org_ID()));
			maybeCurrencyRate.ifPresent(currencyRate -> paymentAmount.multiply(currencyRate)
					.setScale(currency.getStdPrecision(), BigDecimal.ROUND_HALF_UP));
        }
        setC_Payment_ID (payment.getC_Payment_ID());
        setC_Currency_ID (currencyId);

        BigDecimal chargeAmt = getChargeAmt();
        if (chargeAmt == null)
            chargeAmt = Env.ZERO;
        BigDecimal interestAmt = getInterestAmt();
        if (interestAmt == null)
            interestAmt = Env.ZERO;
        setTrxAmt(paymentAmount);
        setStmtAmt(paymentAmount.add(chargeAmt).add(interestAmt));
        //
        setDescription(payment.getDescription());
	}	//	setPayment

	/**
	 * 	Add to Description
	 *	@param description text
	 */
	public void addDescription (String description)
	{
		String desc = getDescription();
		if (desc == null)
			setDescription(description);
		else
			setDescription(desc + " | " + description);
	}	//	addDescription

	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		if (newRecord && getParent().isComplete()) {
			log.saveError("ParentComplete", Msg.translate(getCtx(), "C_BankStatementLine"));
			return false;
		}
		if (getChargeAmt().signum() != 0 && getC_Charge_ID() == 0)
		{
			log.saveError("FillMandatory", Msg.getElement(getCtx(), "C_Charge_ID"));
			return false;
		}
		// Un-link Payment if TrxAmt is zero - teo_sarca BF [ 1896880 ] 
		if (getTrxAmt().signum() == 0 && getC_Payment_ID() > 0)
		{
			setC_Payment_ID(I_ZERO);
			setC_Invoice_ID(I_ZERO);
		}
		//	Set Line No
		if (getLine() == 0)
		{
			String sql = "SELECT COALESCE(MAX(Line),0)+10 AS DefaultValue FROM C_BankStatementLine WHERE C_BankStatement_ID=?";
			int ii = DB.getSQLValue (get_TrxName(), sql, getC_BankStatement_ID());
			setLine (ii);
		}
		
		//	Set References
		if (getC_Payment_ID() != 0 && getC_BPartner_ID() == 0)
		{
			MPayment payment = new MPayment (getCtx(), getC_Payment_ID(), get_TrxName());
			setC_BPartner_ID(payment.getC_BPartner_ID());
			if (payment.getC_Invoice_ID() != 0)
				setC_Invoice_ID(payment.getC_Invoice_ID());

			MBPartner partner = MBPartner.get(payment.getCtx() , payment.getC_BPartner_ID());
			String description = getDescription() != null
			? getDescription() + " - " + payment.getDocumentInfo()  + " - " + partner.getValue() + " - " + partner.getName()
			:  payment.getDocumentInfo() + " - " + partner.getValue() + " - " + partner.getName() ;
			setDescription(description);
	}
		if (getC_Invoice_ID() != 0 && getC_BPartner_ID() == 0)
		{
			MInvoice invoice = new MInvoice (getCtx(), getC_Invoice_ID(), get_TrxName());
			setC_BPartner_ID(invoice.getC_BPartner_ID());
			String description = getDescription() != null
					? getDescription() + " - " + invoice.getDocumentInfo()  + " - " + invoice.getOpenAmt()
					:  invoice.getDocumentInfo() + " - " +invoice.getOpenAmt();
			setDescription(description);
		}
		
		//	Calculate Charge = Statement - trx - Interest  
		BigDecimal amt = getStmtAmt();
		amt = amt.subtract(getTrxAmt());
		amt = amt.subtract(getInterestAmt());
		if (amt.compareTo(getChargeAmt()) != 0)
			setChargeAmt (amt);
		//
		
		return true;
	}	//	beforeSave
	
	/** Parent					*/
	private MBankStatement			m_parent = null;
	
	/**
	 * 	Get Parent
	 *	@return parent
	 */
	public MBankStatement getParent()
	{
		if (m_parent == null)
			m_parent = new MBankStatement (getCtx(), getC_BankStatement_ID(), get_TrxName());
		return m_parent;
	}	//	getParent
	
	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (!success)
			return success;
		return updateHeader();
	}	//	afterSave
	
	/**
	 * 	After Delete
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterDelete (boolean success)
	{
		if (!success)
			return success;
		return updateHeader();
	}	//	afterSave

	/**
	 * 	Update Header
	 */
	private boolean updateHeader()
	{
		String sql = "UPDATE C_BankStatement bs"
			+ " SET StatementDifference=(SELECT COALESCE(SUM(StmtAmt),0) FROM C_BankStatementLine bsl "
				+ "WHERE bsl.C_BankStatement_ID=bs.C_BankStatement_ID AND bsl.IsActive='Y') "
			+ "WHERE C_BankStatement_ID=" + getC_BankStatement_ID();
		int no = DB.executeUpdate(sql, get_TrxName());
		if (no != 1) {
			log.warning("StatementDifference #" + no);
			return false;
		}
		sql = "UPDATE C_BankStatement bs"
			+ " SET EndingBalance=BeginningBalance+StatementDifference "
			+ "WHERE C_BankStatement_ID=" + getC_BankStatement_ID();
		no = DB.executeUpdate(sql, get_TrxName());
		if (no != 1) {
			log.warning("Balance #" + no);
			return false;
		}
		return true;
	}	//	updateHeader
	
 }	//	MBankStatementLine
