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
package org.spin.investment.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.adempiere.core.domains.models.I_FM_Batch;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.spin.investment.model.MFMAccount;
import org.spin.investment.model.MFMAccountProduct;
import org.spin.investment.model.MFMAgreement;
import org.spin.investment.model.MFMBatch;
import org.spin.investment.model.MFMFunctionalApplicability;
import org.spin.investment.model.MFMProduct;

/**
 * Financial Management
 *
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 *      <li> FR [ 1583 ] New Definition for loan
 *		@see https://github.com/adempiere/adempiere/issues/1583
 */
public class FinancialSetting {

	public FinancialSetting() {
		
	}
	
	/** Engine Singleton				*/
	private static FinancialSetting settingEngine = null;
	private HashMap<String, Object> returnValues;
	
	public static final String PARAMETER_PO = "PO";
	public static final String PARAMETER_CTX = "CTX";
	public static final String PARAMETER_TRX_NAME = "TRX_NAME";
	public static final String AGREEMENT_PO = "MFMAgreement";
	public static final String ACCOUNT_PO = "MFMAccount";
	public static final String BATCH_PO = "MFMBatch";
	

	/**
	 * 	Get Singleton
	 *	@return modelValidatorEngine
	 */
	public synchronized static FinancialSetting get() {
		if (settingEngine == null)
			settingEngine = new FinancialSetting();
		return settingEngine;
	}	//	get
	
	/**
	 * Get return Values
	 * @return
	 */
	public HashMap<String, Object> getReturnValues() {
		return returnValues;
	}
	
	/**
	 * 	Fire Document Validation.
	 * 	Call docValidate method of added validators
	 *	@param po persistent objects
	 *	@param docTiming see ModelValidator.TIMING_ constants
     *	@return error message or null
	 */
	public String fireDocValidate(PO po, int docTiming, int financialProductId) {
		if (po == null
				|| financialProductId <= 0) {
			return null;
		}
		//	Get Product
		MFMProduct financialProduct = MFMProduct.getById(po.getCtx(), financialProductId, po.get_TrxName());
		//	Apply Listener
		List<MFMFunctionalApplicability> applicabilityList = financialProduct.getApplicability(po.get_TableName(), ModelValidator.documentEventValidators[docTiming]);
		//	flush return values
		returnValues = new HashMap<String, Object>();
		//	default
		return runApplicability(po.getCtx(), po.get_TrxName(), po, applicabilityList, null);
	}
	
	/**
	 * 	Fire Model Change.
	 * 	Call modelChange method of added validators
	 *	@param po persistent objects
	 *	@param changeType ModelValidator.TYPE_*
	 *	@return error message or NULL for no veto
	 */
	public String fireModelChange(PO po, int changeType, int financialProductId) {
		if (po == null
				|| financialProductId <= 0) {
			return null;
		}
		//	Get Product
		MFMProduct financialProduct = MFMProduct.getById(po.getCtx(), financialProductId, po.get_TrxName());
		if(financialProduct == null) {
			return null;
		}
		//	Apply Listener
		List<MFMFunctionalApplicability> applicabilityList = financialProduct.getApplicability(po.get_TableName(), ModelValidator.tableEventValidators[changeType]);
		//	flush return values
		returnValues = new HashMap<String, Object>();
		//	default
		return runApplicability(po.getCtx(), po.get_TrxName(), po, applicabilityList, null);
	}
	
	
	/**
	 * Fire it for a alert process scheduled
	 * @param ctx
	 * @param financialProductId
	 * @param trxName
	 * @return
	 */
	public String fire(Properties ctx, int financialProductId, String eventType, Map<String, Object> parameters, String trxName) {
		if (financialProductId <= 0) {
			return null;
		}
		//	
		MFMProduct financialProduct = MFMProduct.getById(ctx, financialProductId, trxName);
		if(financialProduct == null) {
			return null;
		}
		//	Apply Listener
		List<MFMFunctionalApplicability> applicabilityList = financialProduct.getApplicability(eventType);
		//	flush return values
		returnValues = new HashMap<String, Object>();
		//	default
		return runApplicability(ctx, trxName, null, applicabilityList, parameters);
	}
	
	/**
	 * Fire it for a alert process scheduled
	 * @param ctx
	 * @param agreement
	 * @param trxName
	 * @return
	 */
	public String fireProcessAgreement(Properties ctx, MFMAgreement agreement, String trxName) {
		if (agreement == null) {
			return null;
		}
		StringBuffer errorMsg = new StringBuffer();
		//	
		List<MFMAccountProduct> accountProductList = agreement.getAccountProducts();
		for(MFMAccountProduct accountProduct : accountProductList) {
			
			MFMAccount account = MFMAccount.getById(accountProduct.getCtx(), accountProduct.getFM_Account_ID(), trxName);
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			parameters.put(AGREEMENT_PO, agreement);
			parameters.put(ACCOUNT_PO, account);
			String error = fire(ctx, accountProduct.getFM_Product_ID(), MFMFunctionalApplicability.EVENTTYPE_Process, parameters, trxName);
			if(!Util.isEmpty(error)) {
				if(errorMsg.length() > 0) {
					errorMsg.append(Env.NL);
				}
				errorMsg.append(error);
			}
		}
		//	
		if(errorMsg.length() > 0) {
			return errorMsg.toString();
		}
		//	Default
		return null;
	}
	
	/**
	 * Run Applicability from PO
	 * @param po
	 * @param applicabilityList
	 * @return
	 */
	private String runApplicability(Properties ctx, String trxName, PO po, List<MFMFunctionalApplicability> applicabilityList, Map<String, Object> parameters) {
		//	
		StringBuffer message = new StringBuffer();
		try {
			//	Iterate applicability for run
			for(MFMFunctionalApplicability applicability : applicabilityList) {
				AbstractFunctionalSetting settingForRun = applicability.getSetting();
				//	Validate Null Value
				if(settingForRun == null) {
					continue;
				}
				MFMBatch batch = null;
				//	
				settingForRun.setFunctionalApplicability(applicability);
				settingForRun.setParameter(PARAMETER_PO, po);
				settingForRun.setParameter(PARAMETER_CTX, ctx);
				settingForRun.setParameter(PARAMETER_TRX_NAME, trxName);
				//	Create Batch
				if(applicability.getEventType().equals(MFMFunctionalApplicability.EVENTTYPE_Process)) {
					MFMAccount account = (MFMAccount) parameters.get(ACCOUNT_PO);
					//	Reverse Previous Batch
					if(applicability.isCreateReversal()) {
						reversePreviousBatch(ctx, trxName, applicability.getFM_FunctionalSetting_ID(), account.getFM_Account_ID());
					}
					batch = new MFMBatch(ctx, 0, trxName);
					batch.setDateDoc(new Timestamp(System.currentTimeMillis()));
					batch.setFM_Account_ID(account.getFM_Account_ID());
					batch.setFM_FunctionalSetting_ID(applicability.getFM_FunctionalSetting_ID());
					batch.setAD_Org_ID(account.getAD_Org_ID());
					batch.setC_DocType_ID();
					batch.saveEx();
					//	Add to parameters
					settingForRun.setParameter(BATCH_PO, batch);
				}
				
				if(parameters != null) {
					settingForRun.setParameters(parameters);
				}
				//	Run It
				String runMsg = settingForRun.run();
				if(!Util.isEmpty(runMsg)) {
					//	Add new line
					if(message.length() > 0) {
						message.append(Env.NL);
					}
					message.append(runMsg);
				}
				//	Copy Return Value
				for(Entry<String, Object> entry : settingForRun.getReturnValues().entrySet()) {
					returnValues.put(entry.getKey(), entry.getValue());
				}
				//	Complete Batch
				if(batch != null
						&& !batch.isProcessed()
						&& !batch.getDocStatus().equals(MFMBatch.DOCACTION_Complete)) {
					batch.processIt(MFMBatch.ACTION_Complete);
					batch.saveEx();
				}
			}
		} catch(Exception e) {
			message.append(e);
		}
		//	
		if(message.length() > 0) {
			return message.toString();
		}
		//	Default
		return null;
	}
	
	private void reversePreviousBatch(Properties ctx, String trxName, int functionalSettingId, int accountId) {
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(functionalSettingId);
		parameters.add(accountId);
		//	Create Reverse
		MFMBatch previousBatch = new Query(ctx, I_FM_Batch.Table_Name, 
				I_FM_Batch.COLUMNNAME_FM_FunctionalSetting_ID + "=? " + 
				"AND " + I_FM_Batch.COLUMNNAME_FM_Account_ID + "=? " + 
				"AND " + I_FM_Batch.COLUMNNAME_DocStatus + "='CO'", trxName)
					.setParameters(parameters)
					.setOrderBy(I_FM_Batch.COLUMNNAME_DateDoc + " DESC")
					.first();
		//	Verify
		if(previousBatch != null) {
			previousBatch.reverseAccrualIt();
		}
	}
}
