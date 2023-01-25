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
import java.util.Optional;
import java.util.Properties;

import org.adempiere.core.domains.models.X_C_BankAccountDoc;
import org.compiere.util.CCache;
import org.compiere.util.Env;

/**
 * Bank Account Document Model
 * @author Carlos Parada, cparada@erpcya.com, ERPCyA http://www.erpcya.com
 */
public class MBankAccountDoc extends X_C_BankAccountDoc{

	private static final long serialVersionUID = 1L;
	
	/**	Cache Bank Account Document*/
	private static CCache<String,MBankAccountDoc>	s_cache
		= new CCache<String,MBankAccountDoc>("C_BankAccountDoc", 5);
	
	/**
	 * Constructor
	 * @param ctx
	 * @param C_BankAccountDoc_ID
	 * @param trxName
	 */
	public MBankAccountDoc(Properties ctx, int C_BankAccountDoc_ID, String trxName) {
		super(ctx, C_BankAccountDoc_ID, trxName);
	}
	
	/**
	 * Constructor
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MBankAccountDoc(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Get Bank Account Document for Account and Payment Rule
	 * @param bankAccountId
	 * @param paymentRule
	 * @return
	 */
	public static MBankAccountDoc get(int bankAccountId, String paymentRule) {
		
		String key = bankAccountId + "-" + paymentRule;
		Optional<MBankAccountDoc> maybeBankAccountDoc = Optional.ofNullable(s_cache.get(key));
		MBankAccountDoc bankAccountDoc = maybeBankAccountDoc.orElseGet(()-> new Query(Env.getCtx(), MBankAccountDoc.Table_Name, "C_BankAccount_ID = ? AND PaymentRule = ? ", null)
																				.setParameters(bankAccountId, paymentRule)
																				.<MBankAccountDoc>first()
		
		);
		return bankAccountDoc;
	}
	
}
