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
import java.util.Properties;

import org.compiere.util.CCache;
import org.compiere.util.Env;


/**
 *  Bank Account Model
 *
 *  @author Jorg Janke
 *  @version $Id: MBankAccount.java,v 1.3 2006/07/30 00:51:05 jjanke Exp $
 */
public class MBankAccount extends X_C_BankAccount
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8742307130542608791L;

	/**
	 * 	Get BankAccount from Cache
	 *	@param ctx context
	 *	@param C_BankAccount_ID id
	 *	@return MBankAccount
	 */
	public static MBankAccount get (Properties ctx, int C_BankAccount_ID)
	{
		Integer key = new Integer (C_BankAccount_ID);
		MBankAccount retValue = (MBankAccount) s_cache.get (key);
		if (retValue != null)
			return retValue;
		retValue = new MBankAccount (ctx, C_BankAccount_ID, null);
		if (retValue.get_ID () != 0)
			s_cache.put (key, retValue);
		return retValue;
	} //	get

	/**	Cache						*/
	private static CCache<Integer,MBankAccount>	s_cache
		= new CCache<Integer,MBankAccount>("C_BankAccount", 5);
	
	/**
	 * 	Bank Account Model
	 *	@param ctx context
	 *	@param C_BankAccount_ID bank account
	 *	@param trxName transaction
	 */
	public MBankAccount (Properties ctx, int C_BankAccount_ID, String trxName)
	{
		super (ctx, C_BankAccount_ID, trxName);
		if (C_BankAccount_ID == 0)
		{
			setIsDefault (false);
			setBankAccountType (BANKACCOUNTTYPE_Checking);
			setCurrentBalance (Env.ZERO);
		//	setC_Currency_ID (0);
			setCreditLimit (Env.ZERO);
		//	setC_BankAccount_ID (0);
		}
	}	//	MBankAccount

	/**
	 * 	Bank Account Model
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MBankAccount (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MBankAccount

	/**
	 * 	String representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MBankAccount[")
			.append (get_ID())
			.append("-").append(getAccountNo())
			.append ("]");
		return sb.toString ();
	}	//	toString

	/**
	 * 	Get Bank
	 *	@return bank parent
	 */
	public MBank getBank()
	{
		return MBank.get(getCtx(), getC_Bank_ID());
	}	//	getBank
	
	/**
	 * 	Get Bank Name and Account No
	 *	@return Bank/Account
	 */
	public String getName()
	{
		return getBank().getName() + " " + getAccountNo();
	}	//	getName
	
	/**
	 * 	After Save
	 *	@param newRecord new record
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (newRecord && success)
			return insert_Accounting("C_BankAccount_Acct", "C_AcctSchema_Default", null);
		return success;
	}	//	afterSave
	
	/**
	 * 	Before Delete
	 *	@return true
	 */
	protected boolean beforeDelete ()
	{
		return delete_Accounting("C_BankAccount_Acct");
	}	//	beforeDelete

}	//	MBankAccount
