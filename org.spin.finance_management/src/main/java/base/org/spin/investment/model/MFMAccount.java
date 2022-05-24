/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2016 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/

package org.spin.investment.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MClient;
import org.compiere.model.Query;
import org.compiere.util.CCache;
/**
 * Financial Management
 *
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 *      <li> FR [ 1583 ] New Definition for loan
 *		@see https://github.com/adempiere/adempiere/issues/1583
 */
public class MFMAccount extends X_FM_Account {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6954882533416850938L;

	public MFMAccount(Properties ctx, int FM_Account_ID, String trxName) {
		super(ctx, FM_Account_ID, trxName);
	}

	public MFMAccount(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public MFMAccount(MFMAgreement agreement) {
		super(agreement.getCtx(), 0, agreement.get_TrxName());
		//	Set default values
		setFM_Agreement_ID(agreement.getFM_Agreement_ID());
		setAccountNo("#" + agreement.getDocumentNo());
		int currencyId = MClient.get(agreement.getCtx()).getC_Currency_ID();
		if(agreement.getFM_Product_ID() != 0) {
			MFMProduct financialProduct = MFMProduct.getById(getCtx(), agreement.getFM_Product_ID(), agreement.get_TrxName());
			if(financialProduct.getC_Currency_ID() != 0) {
				currencyId = financialProduct.getC_Currency_ID();
			}
			//	For Frequency
			if(financialProduct.getPaymentFrequency() != null) {
				setPaymentFrequency(financialProduct.getPaymentFrequency());
			}
			//	Fees Quantity
			if(financialProduct.get_ValueAsInt(COLUMNNAME_FeesQty) > 0) {
				setFeesQty((BigDecimal) financialProduct.get_Value(COLUMNNAME_FeesQty));
			}
			//	Payment Date
			if(financialProduct.get_Value(COLUMNNAME_PayDate) != null) {
				setPayDate((Timestamp) financialProduct.get_Value(COLUMNNAME_PayDate));
			}
		}
		//	Set currency
		setC_Currency_ID(currencyId);
	}
	
	/** Static Cache */
	private static CCache<Integer, List<MFMAccount>> accountsCacheValues = new CCache<Integer, List<MFMAccount>>(Table_Name, 30);
	/** Static Cache */
	private static CCache<Integer, MFMAccount> financialAccountCacheIds = new CCache<Integer, MFMAccount>(Table_Name, 30);
	
	/**
	 * Get/Load Functional Product [CACHED]
	 * @param ctx context
	 * @param financialAccountId
	 * @return activity or null
	 */
	public static MFMAccount getById(Properties ctx, int financialAccountId, String trxName) {
		if (financialAccountId <= 0)
			return null;

		MFMAccount financialAccount = financialAccountCacheIds.get(financialAccountId);
		if (financialAccount != null && financialAccount.get_ID() > 0) {
			return financialAccount;
		}
		financialAccount = new Query(ctx , Table_Name , COLUMNNAME_FM_Account_ID + "=?" , trxName)
				.setClient_ID()
				.setParameters(financialAccountId)
				.first();
		if (financialAccount != null && financialAccount.get_ID() > 0) {
			financialAccountCacheIds.put(financialAccount.get_ID(), financialAccount);
		}
		return financialAccount;
	}
	
	/**
	 * Get By ID
	 * @param ctx
	 * @param financialAccountId
	 * @return
	 */
	public static MFMAccount getById(Properties ctx, int financialAccountId) {
		return getById(ctx, financialAccountId, null);
	}
	
	/**
	 * Get Account from Agreement
	 * @param agreement
	 * @return
	 */
	public static List<MFMAccount> getAccountFromAgreement(MFMAgreement agreement) {
		List<MFMAccount> accountList = accountsCacheValues.get(agreement.getFM_Agreement_ID());
		if(accountList != null && !accountList.isEmpty()) {
			return accountList;
		}
		//	Load
		accountList = new Query(agreement.getCtx(), Table_Name, "FM_Agreement_ID = ?", agreement.get_TrxName())
				.setClient_ID()
				.setParameters(agreement.getFM_Agreement_ID())
				.setOnlyActiveRecords(true)
				.<MFMAccount>list();
		//	Set Value
		accountsCacheValues.put(agreement.getFM_Agreement_ID(), accountList);
		//	Return
		return accountList;
	}

}
