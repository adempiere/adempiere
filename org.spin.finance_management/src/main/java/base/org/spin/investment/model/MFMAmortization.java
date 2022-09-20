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
 * Contributor(s): Carlos Parada www.erpya.com                                *
 *****************************************************************************/
package org.spin.investment.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.adempiere.core.domains.models.I_FM_Amortization;
import org.adempiere.core.domains.models.X_FM_Amortization;
import org.adempiere.core.domains.models.X_I_FM_Agreement;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.spin.investment.model.MFMAccount;

/**
 * Loan Amortization
 *
 * @author Carlos Parada, cparada@erpya.com , http://www.erpya.com
 *      <li> FR [ 1586 ]  Generate Amortization
 *		@see https://github.com/adempiere/adempiere/issues/1586
 */

public class MFMAmortization extends X_FM_Amortization {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MFMAmortization(Properties ctx, int FM_Amortization_ID, String trxName) {
		super(ctx, FM_Amortization_ID, trxName);
	}

	public MFMAmortization(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Constructor from import
	 * @param agreementImport
	 */
	public MFMAmortization(X_I_FM_Agreement agreementImport) {
		super(agreementImport.getCtx(), agreementImport.get_ValueAsInt("FM_Amortization_ID"), agreementImport.get_TrxName());
		setPeriodNo(agreementImport.get_ValueAsInt("PeriodNo"));
		if(agreementImport.get_Value("DueDate") != null) {
			setDueDate((Timestamp) agreementImport.get_Value("DueDate"));
		}
		//	
		if(agreementImport.get_Value("ValidFrom") != null) {
			setStartDate((Timestamp) agreementImport.get_Value("ValidFrom"));
		} else {
			setStartDate((Timestamp) agreementImport.get_Value("DateDoc"));
		}
		//	
		if(agreementImport.get_Value("EndDate") != null) {
			setEndDate((Timestamp) agreementImport.get_Value("EndDate"));
		} else {
			setEndDate((Timestamp) agreementImport.get_Value("DateDoc"));
		}
		//	
		if(agreementImport.get_Value("CapitalAmt") != null) {
			setCapitalAmt((BigDecimal) agreementImport.get_Value("CapitalAmt"));
		}
		//	
		if(agreementImport.get_Value("InterestAmt") != null) {
			setInterestAmt((BigDecimal) agreementImport.get_Value("InterestAmt"));
		}
		//	
		if(agreementImport.get_Value("TaxAmt") != null) {
			setTaxAmt((BigDecimal) agreementImport.get_Value("TaxAmt"));
		}
		setFM_Account_ID(agreementImport.getFM_Account_ID());
		if(agreementImport.get_Value("IsPaid") != null) {
			setIsPaid(agreementImport.get_ValueAsBoolean("IsPaid"));
		}
	}
	
	/**
	 * Delete Amortization for Account
	 * @param account
	 * @return
	 */
	public static int deleteForAccount(MFMAccount account){
		return DB.executeUpdate("DELETE "
									+ "FROM FM_Amortization "
									+ "WHERE FM_Account_ID = ? "
									+ "AND IsPaid = 'N'", account.getFM_Account_ID(), account.get_TrxName());	
	}
	
	/**
	 * Create Loan Amortization 
	 * @param ctx
	 * @param CapitalAmt
	 * @param Description
	 * @param DueDate
	 * @param EndDate
	 * @param FM_Account_ID
	 * @param InterestAmt
	 * @param PeriodNo
	 * @param StartDate
	 * @param TaxAmt
	 * @param trxName
	 * @return
	 */
	public static boolean createAmortization(Properties ctx, BigDecimal CapitalAmt,
												String Description, Timestamp DueDate,
													Timestamp EndDate, int FM_Account_ID,
														BigDecimal InterestAmt, int PeriodNo,
															Timestamp StartDate, BigDecimal TaxAmt,
																String trxName){

		MFMAmortization amortization = new MFMAmortization(ctx, 0, trxName);
		amortization.setCapitalAmt(CapitalAmt);
		amortization.setDescription(Description);
		amortization.setDueDate(DueDate);
		amortization.setEndDate(EndDate);
		amortization.setFM_Account_ID(FM_Account_ID);
		amortization.setInterestAmt(InterestAmt);
		amortization.setPeriodNo(PeriodNo);
		amortization.setStartDate(StartDate);
		amortization.setTaxAmt(TaxAmt);
		amortization.saveEx(trxName);
		return true;
	}
	
	/**
	 * Return true when an amortization line for account is paid
	 * @param account
	 * @return
	 */
	public static boolean checkAccount(MFMAccount account){
		return new Query(account.getCtx(), 
							MFMAmortization.Table_Name, 
							"FM_Account_ID = ? AND IsPaid = 'Y'", 
							account.get_TrxName())
				.setParameters(account.getFM_Account_ID())
				.match();
	}
	
	/**
	 * Get from Account
	 * @param financialAccountId
	 * @param trxName
	 * @return
	 */
	public static List<MFMAmortization> getFromAccount(int financialAccountId, String trxName) {
		return new Query(Env.getCtx(), I_FM_Amortization.Table_Name, "FM_Account_ID = ?", trxName)
				.setParameters(financialAccountId)
				.<MFMAmortization>list();
	}
	
	/**
	 * Get Only Open
	 * @param financialAccountId
	 * @param trxName
	 * @return
	 */
	public static List<MFMAmortization> getOpenFromAccount(int financialAccountId, String trxName) {
		return new Query(Env.getCtx(), I_FM_Amortization.Table_Name, "FM_Account_ID = ? AND IsInvoiced = 'N' AND IsPaid = 'N'", trxName)
				.setParameters(financialAccountId)
				.<MFMAmortization>list();
	}
	
	/**
	 * Get Last Amortization from Account
	 * @param financialAccountId
	 * @param trxName
	 * @return
	 */
	public static MFMAmortization getLastAmortizationFromAccount(int financialAccountId, String trxName) {
		return new Query(Env.getCtx(), I_FM_Amortization.Table_Name, "FM_Account_ID = ?", trxName)
				.setParameters(financialAccountId)
				.setOrderBy(I_FM_Amortization.COLUMNNAME_PeriodNo + " DESC")
				.first();
	}
	
	/**
	 * Get Amortization List from account
	 * @param financialAccountId
	 * @param whereClause
	 * @param trxName
	 * @return
	 */
	public static List<MFMAmortization> getAmortizationList(int financialAccountId, String whereClause, String trxName) {
		StringBuffer internalWhereClause = new StringBuffer("FM_Account_ID = ?");
		if(!Util.isEmpty(whereClause)) {
			internalWhereClause.append(" AND ").append(whereClause);
		}
		return new Query(Env.getCtx(), I_FM_Amortization.Table_Name, internalWhereClause.toString(), trxName)
				.setParameters(financialAccountId)
				.setOrderBy(I_FM_Amortization.COLUMNNAME_PeriodNo)
				.list();
	}
	
	/**
	 * Get Amortization List to Invoice
	 * @param financialAccountId
	 * @param trxName
	 * @return
	 */
	public static List<MFMAmortization> getToInvoiceList(int financialAccountId, String trxName) {
		return getAmortizationList(financialAccountId, "IsInvoiced = 'N'", trxName);
	}
	
	/**
	 * Get Amortization List to Pay
	 * @param financialAccountId
	 * @param trxName
	 * @return
	 */
	public static List<MFMAmortization> getToPayList(int financialAccountId, String trxName) {
		return getAmortizationList(financialAccountId, "IsPaid = 'N'", trxName);
	}

}
