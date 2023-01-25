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

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.adempiere.core.domains.models.I_C_BP_BankAccount;
import org.adempiere.core.domains.models.X_C_BP_BankAccount;
import org.adempiere.exceptions.AdempiereException;
import org.apache.commons.validator.routines.CreditCardValidator;
import org.apache.commons.validator.routines.IBANValidator;
import org.compiere.util.CLogger;
import org.compiere.util.Util;

/**
 *  BP Bank Account Model
 *
 *  @author Jorg Janke
 *	@author  victor.perez , victor.perez@e-evolution.com http://www.e-evolution.com
 * 		<li> FR [ 297 ] Apply ADempiere best Pratice
 *		@see https://github.com/adempiere/adempiere/issues/297
 *  @version $Id: MBPBankAccount.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 */
public class MBPBankAccount extends X_C_BP_BankAccount
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2580706419593695062L;

	/**
	 * Get Accounts by BPartner
	 * @param ctx
	 * @param partnerId
	 * @param whereClause
	 * @param trxName
	 * @return
	 */
	public static List<MBPBankAccount> getByPartner(Properties ctx, int partnerId, String whereClause,String trxName)
	{
		String finalWhereClause = MBPBankAccount.COLUMNNAME_C_BPartner_ID+"=?";
		if (whereClause != null
				&& whereClause.length() > 0)
			finalWhereClause+=" AND " + whereClause;
		
		return new Query(ctx,I_C_BP_BankAccount.Table_Name,finalWhereClause,trxName)
		.setParameters(partnerId)
		.setOnlyActiveRecords(true)
		.list();
	}	//	getByPartner
	
	/**
	 * 	Get Accounts by BPartner
	 *	@param ctx context
	 *	@param partnerId bpartner
	 *	@return
	 */
	public static List<MBPBankAccount> getByPartner(Properties ctx, int partnerId)
	{
		return getByPartner(ctx, partnerId, null,null);
	}	//	getByPartner

    @Deprecated
    /*Remplace by getByPartner  not should use this method more */
    public static MBPBankAccount[] getOfBPartner(Properties ctx, int partnerId)
    {
        return getByPartner(ctx, partnerId).stream().toArray(MBPBankAccount[]::new);
    }

	/**	Logger	*/
	private static CLogger s_log = CLogger.getCLogger(MBPBankAccount.class);
	
	/**************************************************************************
	 * 	Constructor
	 *	@param ctx context
	 *	@param bankAccountId BP bank account
	 *	@param trxName transaction
	 */
	public MBPBankAccount (Properties ctx, int bankAccountId, String trxName)
	{
		super (ctx, bankAccountId, trxName);
		if (bankAccountId == 0)
		{
		//	setC_BPartner_ID (0);
			setIsACH (false);
			setBPBankAcctUse(BPBANKACCTUSE_Both);
		}
	}	//	MBP_BankAccount

	/**
	 * 	Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MBPBankAccount (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MBP_BankAccount

	/**
	 * 	Constructor
	 *	@param ctx context
	 * 	@param bp BP
	 *	@param bpc BP Contact
	 * 	@param location Location
	 */
	public MBPBankAccount (Properties ctx, MBPartner bp, MUser bpc, MLocation location)
	{
		this(ctx, 0, bp.get_TrxName());
		setIsACH (false);
		//
		setC_BPartner_ID(bp.getC_BPartner_ID());
		//
		setA_Name(bpc.getName());
		setA_EMail(bpc.getEMail());
		//
		setA_Street(location.getAddress1());
		setA_City(location.getCity());
		setA_Zip(location.getPostal());
		setA_State(location.getRegionName(true));
		setA_Country(location.getCountryName());
	}	//	MBP_BankAccount

	/** Bank Link			*/
	private MBank		m_bank = null;

	/**
	 * 	Is Direct Deposit
	 *	@return true if dd
	 */
	public boolean isDirectDeposit()
	{
		if (!isACH())
			return false;
		String s = getBPBankAcctUse();
		if (s == null)
			return true;
		return (s.equals(BPBANKACCTUSE_Both) || s.equals(BPBANKACCTUSE_DirectDeposit));
	}	//	isDirectDeposit
	
	/**
	 * 	Is Direct Debit
	 *	@return true if dd
	 */
	public boolean isDirectDebit()
	{
		if (!isACH())
			return false;
		String s = getBPBankAcctUse();
		if (s == null)
			return true;
		return (s.equals(BPBANKACCTUSE_Both) || s.equals(BPBANKACCTUSE_DirectDebit));
	}	//	isDirectDebit
	
	
	/**
	 * 	Get Bank
	 *	@return bank
	 */
	public MBank getBank()
	{
		int C_Bank_ID = getC_Bank_ID();
		if (C_Bank_ID == 0)
			return null;
		if (m_bank == null)
			m_bank = new MBank (getCtx(), C_Bank_ID, get_TrxName());
		return m_bank;
	}	//	getBank
	
	/**
	 * 	Get Routing No
	 *	@return routing No
	 */
	public String getRoutingNo() 
	{
		MBank bank = getBank();
		String rt = super.getRoutingNo();
		if (bank != null)
			rt = bank.getRoutingNo();
		return rt;
	}	//	getRoutingNo

	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave(boolean newRecord) 
	{
		//	maintain routing on bank level
		if (isACH() && getBank() != null)
			setRoutingNo(null);
		
		// validate IBAN
		if(!Util.isEmpty(getIBAN())) {
			IBANValidator validator = IBANValidator.getInstance();
			if(!validator.isValid(getIBAN().trim())) {
				throw new AdempiereException("@ValidationError@ (@Invalid@ @IBAN@)");
			}
			setIBAN(getIBAN().trim());
		}
		
		// validate CreditCardNumber
		if(!Util.isEmpty(getCreditCardNumber())) {
			if(CREDITCARDTYPE_Amex.equals(getCreditCardType())
			|| CREDITCARDTYPE_Visa.equals(getCreditCardType())
			|| CREDITCARDTYPE_MasterCard.equals(getCreditCardType())
			|| CREDITCARDTYPE_Discover.equals(getCreditCardType()) ) {
				CreditCardValidator validator = new CreditCardValidator(); // for AMEX, VISA, MASTERCARD and DISCOVER
				if(!validator.isValid(getCreditCardNumber().trim())) {
					throw new AdempiereException("@ValidationError@ (@Invalid@ @CreditCardNumber@)");				
				}
				setCreditCardNumber(getCreditCardNumber().trim());
			}
			if(CREDITCARDTYPE_Diners.equals(getCreditCardType()) ) {
				CreditCardValidator validator = new CreditCardValidator(CreditCardValidator.DINERS);
				if(!validator.isValid(getCreditCardNumber().trim())) {
					throw new AdempiereException("@ValidationError@ (@Invalid@ @CreditCardNumber@)");				
				}
				setCreditCardNumber(getCreditCardNumber().trim());
			}
		}

		return true;
	}
	
	/**
	 *	String Representation
	 * 	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MBP_BankAccount[")
			.append (get_ID ())
			.append(", Name=").append(getA_Name())
			.append ("]");
		return sb.toString ();
	}	//	toString

}	//	MBPBankAccount
