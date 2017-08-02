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
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *  Journal Line Model
 *
 *	@author Jorg Janke
 *	@author Cristina Ghita
 *  	<li>BF [ 2855807 ] AD_Org_ID from account 
 *  		https://sourceforge.net/tracker/?func=detail&aid=2855807&group_id=176962&atid=879332
 * 	@author victor.perez@e-evolution.com , wwww.e-evolution.com
 *	@version $Id: MJournalLine.java,v 1.3 2006/07/30 00:51:05 jjanke Exp $
 */
public class MJournalLine extends X_GL_JournalLine
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7008806797777773843L;

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param journalLineId id
	 *	@param trxName transaction
	 */
	public MJournalLine (Properties ctx, int journalLineId, String trxName)
	{
		super (ctx, journalLineId, trxName);
		if (journalLineId == 0)
		{
		//	setGL_JournalLine_ID (0);		//	PK
		//	setGL_Journal_ID (0);			//	Parent
		//	setC_Currency_ID (0);
		//	setC_ValidCombination_ID (0);
			setLine (0);
			setAmtAcctCr (Env.ZERO);
			setAmtAcctDr (Env.ZERO);
			setAmtSourceCr (Env.ZERO);
			setAmtSourceDr (Env.ZERO);
			setCurrencyRate (Env.ONE);
		//	setC_ConversionType_ID (0);
			setDateAcct (new Timestamp(System.currentTimeMillis()));
			setIsGenerated (true);
		}
	}	//	MJournalLine

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MJournalLine (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MJournalLine

	/**
	 * 	Parent Constructor
	 *	@param parent journal
	 */
	public MJournalLine (MJournal parent)
	{
		this (parent.getCtx(), 0, parent.get_TrxName());
		setClientOrg(parent);
		setGL_Journal_ID(parent.getGL_Journal_ID());
		setC_Currency_ID(parent.getC_Currency_ID());
		setC_ConversionType_ID(parent.getC_ConversionType_ID());
		setDateAcct(parent.getDateAcct());
		
	}	//	MJournalLine

	/** Parent					*/
	private MJournal parentJournal = null;
	
	/**
	 * 	Get Parent
	 *	@return parent
	 */
	public MJournal getParent()
	{
		if (parentJournal == null)
			parentJournal = new MJournal (getCtx(), getGL_Journal_ID(), get_TrxName());
		return parentJournal;
	}	//	getParent
	

	/**	Currency Precision		*/
	private int currencyPrecision = 2;
	/**	Account Combination		*/
	private MAccount account = null;
	/** Account Element			*/
	private MElementValue accountElement = null;
	
	/**
	 * 	Set Currency Info
	 *	@param currencyId currenct
	 *	@param conversionTypeId type
	 *	@param currencyRate rate
	 */
	public void setCurrency (int currencyId, int conversionTypeId, BigDecimal currencyRate)
	{
		setC_Currency_ID(currencyId);
		if (conversionTypeId != 0)
			setC_ConversionType_ID(conversionTypeId);
		if (currencyRate != null && currencyRate.signum() == 0)
			setCurrencyRate(currencyRate);
	}	//	setCurrency

	/**
	 * 	Set C_Currency_ID and precision
	 *	@param currencyId currency
	 */
	public void setC_Currency_ID (int currencyId)
	{
		if (currencyId == 0)
			return;
		super.setC_Currency_ID (currencyId);
		currencyPrecision = MCurrency.getStdPrecision(getCtx(), currencyId);
	}	//	setC_Currency_ID
	
	/**
	 * 	Get Currency Precision
	 *	@return precision
	 */
	public int getPrecision()
	{
		return currencyPrecision;
	}	//	getPrecision
	
	/**
	 * 	Set Currency Rate
	 *	@param currencyRate check for null (->one)
	 */
	public void setCurrencyRate (BigDecimal currencyRate)
	{
		if (currencyRate == null)
		{
			log.warning("was NULL - set to 1");
			super.setCurrencyRate (Env.ONE);
		}
		else if (currencyRate.signum() < 0)
		{
			log.warning("negative - " + currencyRate + " - set to 1");
			super.setCurrencyRate (Env.ONE);
		}
		else
			super.setCurrencyRate (currencyRate);
	}	//	setCurrencyRate
	
	/**
	 * 	Set Accounted Amounts only if not 0.
	 * 	Amounts overwritten in beforeSave - set conversion rate
	 *	@param amountAccountDebit Dr
	 *	@param amountAccountCredit Cr
	 */
	public void setAmtAcct (BigDecimal amountAccountDebit, BigDecimal amountAccountCredit)
	{
		//	setConversion
		double rateDebit = 0;
		if (amountAccountDebit != null && amountAccountDebit.signum() != 0)
		{
			rateDebit = amountAccountDebit.doubleValue() / getAmtSourceDr().doubleValue();
			super.setAmtAcctDr(amountAccountDebit);
		}
		double rateCredit = 0;
		if (amountAccountCredit != null && amountAccountCredit.signum() != 0)
		{
			rateCredit = amountAccountCredit.doubleValue() / getAmtSourceCr().doubleValue();
			super.setAmtAcctCr(amountAccountCredit);
		}
		if (rateDebit != 0 && rateCredit != 0 && rateDebit != rateCredit)
		{
			log.warning("Rates Different DR=" + rateDebit + "(used) <> CR=" + rateCredit + "(ignored)");
			rateCredit = 0;
		}
		if (rateDebit < 0 || Double.isInfinite(rateDebit) || Double.isNaN(rateDebit))
		{
			log.warning("DR Rate ignored - " + rateDebit);
			return;
		}
		if (rateCredit < 0 || Double.isInfinite(rateCredit) || Double.isNaN(rateCredit))
		{
			log.warning("CR Rate ignored - " + rateCredit);
			return;
		}
		
		if (rateDebit != 0)
			setCurrencyRate(new BigDecimal(rateDebit));
		if (rateCredit != 0)
			setCurrencyRate(new BigDecimal(rateCredit));
	}	//	setAmtAcct

	
	/**
	 * 	Set C_ValidCombination_ID
	 *	@param validCombinationId id
	 */
	public void setC_ValidCombination_ID (int validCombinationId)
	{
		super.setC_ValidCombination_ID (validCombinationId);
		account = null;
		accountElement = null;
	}	//	setC_ValidCombination_ID
	
	/**
	 * 	Set C_ValidCombination_ID
	 *	@param account account
	 */
	public void setC_ValidCombination_ID (MAccount account)
	{
		if (account == null)
			throw new IllegalArgumentException("Account is null");
		super.setC_ValidCombination_ID (account.getC_ValidCombination_ID());
		this.account = account;
		accountElement = null;
	}	//	setC_ValidCombination_ID

	/**
	 * 	Get Account (Valid Combination)
	 *	@return combination or null
	 */
	public MAccount getAccount_Combi()
	{
		if (account == null && getC_ValidCombination_ID() != 0)
			account = new MAccount (getCtx(), getC_ValidCombination_ID(), get_TrxName());
		return account;
	}	//	getValidCombination
	
	/**
	 * 	Get Natural Account Element Value
	 *	@return account
	 */
	public MElementValue getAccountElementValue()
	{
		if (accountElement == null)
		{
			MAccount account = getAccount_Combi();
			if (account != null && account.getAccount_ID() != 0)
				accountElement = new MElementValue (getCtx(), account.getAccount_ID(), get_TrxName());
		}
		return accountElement;
	}	//	getAccountElement
	
	/**
	 * 	Is it posting to a Control Acct
	 *	@return true if control acct
	 */
	public boolean isDocControlled()
	{
		MElementValue accountElementValue = getAccountElementValue();
		if (accountElementValue == null)
		{
			log.warning ("Account not found for C_ValidCombination_ID=" + getC_ValidCombination_ID());
			return false;
		}
		return accountElementValue.isDocControlled();
	}	//	isDocControlled
	
	
	/**************************************************************************
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true 
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		if (newRecord && getParent().isComplete()) {
			log.saveError("ParentComplete", Msg.translate(getCtx(), "GL_JournalLine"));
			return false;
		}

		// idempiere 344 - nmicoud
		if (!getOrCreateCombination())
			return false;
		if (getC_ValidCombination_ID() <= 0) 
		{
			log.saveError("SaveError", Msg.parseTranslation(getCtx(),
					"@FillMandatory@" + "@C_ValidCombination_ID@"));
			return false;
		}
		fillDimensionsFromCombination();
		//	Acct Amts
		BigDecimal currencyRate = getCurrencyRate();
		BigDecimal amountDebit = currencyRate.multiply(getAmtSourceDr());
		if (amountDebit.scale() > getPrecision())
			amountDebit = amountDebit.setScale(getPrecision(), BigDecimal.ROUND_HALF_UP);
		setAmtAcctDr(amountDebit);
		amountDebit = currencyRate.multiply(getAmtSourceCr());
		if (amountDebit.scale() > getPrecision())
			amountDebit = amountDebit.setScale(getPrecision(), BigDecimal.ROUND_HALF_UP);
		setAmtAcctCr(amountDebit);
		//	Set Line Org to Acct Org
	/*	if (newRecord 
				|| is_ValueChanged("C_ValidCombination_ID")
				|| is_ValueChanged("AD_Org_ID"))
		{
			int AD_Org_ID = getAccount().getAD_Org_ID(); 
			if(AD_Org_ID > 0) 
			{	 
				setAD_Org_ID(AD_Org_ID); 
			} 
			else  
			{ 
				setAD_Org_ID(getParent().getAD_Org_ID()); 
			} 
		} */
		
		// Set Line Org to Doc Org if still not set
		if (getAD_Org_ID() <= 0) 
		{
			setAD_Org_ID(getParent().getAD_Org_ID());
		}
		return true;
	}	//	beforeSave
	
	/**
	 * 	After Save.
	 * 	Update Journal/Batch Total
	 *	@param newRecord true if new record
	 *	@param success true if success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (!success)
			return success;
		return updateJournalTotal();
	}	//	afterSave
	
	
	/**
	 * 	After Delete
	 *	@param success true if deleted
	 *	@return true if success
	 */
	protected boolean afterDelete (boolean success)
	{
		if (!success)
			return success;
		return updateJournalTotal();
	}	//	afterDelete

	
	/**
	 * 	Update Journal and Batch Total
	 *	@return true if success
	 */
	private boolean updateJournalTotal()
	{
		//	Update Journal Total
		String sql = "UPDATE GL_Journal j"
			+ " SET (TotalDr, TotalCr) = (SELECT COALESCE(SUM(AmtAcctDr),0), COALESCE(SUM(AmtAcctCr),0)" // croo Bug# 1789935
				+ " FROM GL_JournalLine jl WHERE jl.IsActive='Y' AND j.GL_Journal_ID=jl.GL_Journal_ID) "
			+ "WHERE GL_Journal_ID=" + getGL_Journal_ID();
		int no = DB.executeUpdate(sql, get_TrxName());
		if (no != 1)
			log.warning("afterSave - Update Journal #" + no);
		
		//	Update Batch Total
		int GL_JournalBatch_ID = DB.getSQLValue(get_TrxName(),"SELECT GL_JournalBatch_ID FROM GL_Journal WHERE GL_Journal_ID=?",
				getGL_Journal_ID());
		if (GL_JournalBatch_ID != 0) { // idempiere 344 - nmicoud
			StringBuilder sql2 = new StringBuilder("UPDATE GL_JournalBatch jb")
			.append(" SET (TotalDr, TotalCr) = (SELECT COALESCE(SUM(TotalDr),0), COALESCE(SUM(TotalCr),0)")
			.append(" FROM GL_Journal j WHERE jb.GL_JournalBatch_ID=j.GL_JournalBatch_ID) ")
			.append("WHERE GL_JournalBatch_ID=")
			.append("(SELECT DISTINCT GL_JournalBatch_ID FROM GL_Journal WHERE GL_Journal_ID=")
			.append(getGL_Journal_ID()).append(")");
			
			no = DB.executeUpdate(sql2.toString(), get_TrxName());
			if (no != 1)
				log.warning("Update Batch #" + no);
		}
		return no == 1;
	}	//	updateJournalTotal
	
	/** Update combination and optionally **/
	private boolean getOrCreateCombination()
	{
		if (getC_ValidCombination_ID() == 0
				|| (!is_new() && (is_ValueChanged(COLUMNNAME_Account_ID)
						|| is_ValueChanged(COLUMNNAME_C_SubAcct_ID)
						|| is_ValueChanged(COLUMNNAME_M_Product_ID)
						|| is_ValueChanged(COLUMNNAME_C_BPartner_ID)
						|| is_ValueChanged(COLUMNNAME_AD_OrgTrx_ID)
						|| is_ValueChanged(COLUMNNAME_AD_Org_ID)
						|| is_ValueChanged(COLUMNNAME_C_LocFrom_ID)
						|| is_ValueChanged(COLUMNNAME_C_LocTo_ID)
						|| is_ValueChanged(COLUMNNAME_C_SalesRegion_ID)
						|| is_ValueChanged(COLUMNNAME_C_Project_ID)
						|| is_ValueChanged(COLUMNNAME_C_Campaign_ID)
						|| is_ValueChanged(COLUMNNAME_C_Activity_ID)
						|| is_ValueChanged(COLUMNNAME_User1_ID)
						|| is_ValueChanged(COLUMNNAME_User2_ID)
						|| is_ValueChanged(COLUMNNAME_User3_ID)
						|| is_ValueChanged(COLUMNNAME_User4_ID)))
						|| is_ValueChanged(COLUMNNAME_UserElement1_ID)
						|| is_ValueChanged(COLUMNNAME_UserElement2_ID))
		{
			MJournal gl = new MJournal(getCtx(), getGL_Journal_ID(), get_TrxName());

			// Validate all mandatory combinations are set
			MAcctSchema as = (MAcctSchema) getParent().getC_AcctSchema();
			String errorFields = "";
			for (MAcctSchemaElement elem : MAcctSchemaElement.getAcctSchemaElements(as)) {
				if (! elem.isMandatory())
					continue;
				String et = elem.getElementType();
				if (MAcctSchemaElement.ELEMENTTYPE_Account.equals(et) && getAccount_ID() == 0)
					errorFields += "@" +  COLUMNNAME_Account_ID + "@, ";
				if (MAcctSchemaElement.ELEMENTTYPE_Activity.equals(et) && getC_Activity_ID() == 0)
					errorFields += "@" + COLUMNNAME_C_Activity_ID + "@, ";
				if (MAcctSchemaElement.ELEMENTTYPE_BPartner.equals(et) && getC_BPartner_ID()  == 0)
					errorFields += "@" + COLUMNNAME_C_BPartner_ID + "@, ";
				if (MAcctSchemaElement.ELEMENTTYPE_Campaign.equals(et) && getC_Campaign_ID() == 0)
					errorFields += "@" + COLUMNNAME_C_Campaign_ID + "@, ";
				if (MAcctSchemaElement.ELEMENTTYPE_Organization.equals(et) && getAD_Org_ID() == 0)
					errorFields += "@" + COLUMNNAME_AD_Org_ID + "@, ";
				if (MAcctSchemaElement.ELEMENTTYPE_OrgTrx.equals(et) && getAD_OrgTrx_ID()  == 0)
					errorFields += "@" + COLUMNNAME_AD_OrgTrx_ID + "@, ";
				if (MAcctSchemaElement.ELEMENTTYPE_Product.equals(et) && getM_Product_ID()  == 0)
					errorFields += "@" + COLUMNNAME_M_Product_ID + "@, ";
				if (MAcctSchemaElement.ELEMENTTYPE_Project.equals(et) && getC_Project_ID()  == 0)
					errorFields += "@" + COLUMNNAME_C_Project_ID + "@, ";
				if (MAcctSchemaElement.ELEMENTTYPE_SalesRegion.equals(et) && getC_SalesRegion_ID() == 0)
					errorFields += "@" + COLUMNNAME_C_SalesRegion_ID + "@, ";
				if (MAcctSchemaElement.ELEMENTTYPE_UserList1.equals(et) && getUser1_ID()  == 0)
					errorFields += "@" + COLUMNNAME_User1_ID + "@, ";
				if (MAcctSchemaElement.ELEMENTTYPE_UserList2.equals(et) && getUser2_ID()  == 0)
					errorFields += "@" + COLUMNNAME_User2_ID + "@, ";
				if (MAcctSchemaElement.ELEMENTTYPE_UserList3.equals(et) && getUser3_ID()  == 0)
					errorFields += "@" + COLUMNNAME_User3_ID + "@, ";
				if (MAcctSchemaElement.ELEMENTTYPE_UserList4.equals(et) && getUser4_ID()  == 0)
					errorFields += "@" + COLUMNNAME_User4_ID + "@, ";
				if (MAcctSchemaElement.ELEMENTTYPE_UserElement1.equals(et) && getUserElement1_ID()  == 0)
					errorFields += "@" + COLUMNNAME_UserElement1_ID + "@, ";
				if (MAcctSchemaElement.ELEMENTTYPE_UserElement2.equals(et) && getUserElement2_ID()  == 0)
					errorFields += "@" + COLUMNNAME_UserElement2_ID + "@, ";
			}
			if (errorFields.length() > 0)
			{
				log.saveError("Error", Msg.parseTranslation(getCtx(), "@IsMandatory@: " + errorFields.substring(0, errorFields.length() - 2)));
				return false;
			}
			
			MAccount account = MAccount.get(getCtx(),
					getAD_Client_ID(),
					getAD_Org_ID(),
					gl.getC_AcctSchema_ID(),
					getAccount_ID(),
					getC_SubAcct_ID(),
					getM_Product_ID(),
					getC_BPartner_ID(),
					getAD_OrgTrx_ID(),
					getC_LocFrom_ID(),
					getC_LocTo_ID(),
					getC_SalesRegion_ID(),
					getC_Project_ID(),
					getC_Campaign_ID(),
					getC_Activity_ID(),
					getUser1_ID(),
					getUser2_ID(),
					getUser3_ID(),
					getUser4_ID(),
					getUserElement1_ID(),
					getUserElement2_ID() ,
					null);

			if (account != null)
			{
				account.saveEx(get_TrxName());	// get ID from transaction
				setC_ValidCombination_ID(account.get_ID());
				if (account.getAlias() != null && account.getAlias().length() > 0)
					setAlias_ValidCombination_ID(account.get_ID());
				else
					setAlias_ValidCombination_ID(-1);
			}
		}
		return true;
	}	//	getOrCreateCombination

	/** Fill Accounting Dimensions from line combination **/
	private void fillDimensionsFromCombination()
	{
		if (getC_ValidCombination_ID() > 0)
		{
			MAccount account = new MAccount(getCtx(), getC_ValidCombination_ID(), get_TrxName());
			setAD_Org_ID(account.getAD_Org_ID());
			setAccount_ID(account.getAccount_ID());
			setC_SubAcct_ID( account.getC_SubAcct_ID());
			setM_Product_ID( account.getM_Product_ID());
			setC_BPartner_ID( account.getC_BPartner_ID());
			setAD_OrgTrx_ID(account.getAD_OrgTrx_ID());
			setC_LocFrom_ID(account.getC_LocFrom_ID());
			setC_LocTo_ID(account.getC_LocTo_ID());
			setC_SalesRegion_ID(account.getC_SalesRegion_ID());
			setC_Project_ID(account.getC_Project_ID());
			setC_Campaign_ID( account.getC_Campaign_ID());
			setC_Activity_ID( account.getC_Activity_ID());
			setUser1_ID(account.getUser1_ID());
			setUser2_ID(account.getUser2_ID());
			setUser3_ID(account.getUser3_ID());
			setUser4_ID(account.getUser4_ID());
			setUserElement1_ID(account.getUserElement1_ID());
			setUserElement2_ID( account.getUserElement2_ID());
		}		
	}	// fillDimensionsFromCombination
	
}	//	MJournalLine
