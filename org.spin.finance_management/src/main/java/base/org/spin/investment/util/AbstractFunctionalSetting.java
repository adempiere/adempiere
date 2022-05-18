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
 * Copyright (C) 2003-2014 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/
package org.spin.investment.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.compiere.model.PO;
import org.compiere.util.Env;
import org.spin.investment.model.MFMAccount;
import org.spin.investment.model.MFMBatch;
import org.spin.investment.model.MFMFunctionalApplicability;
import org.spin.investment.model.MFMFunctionalSetting;

/**
 * Financial Management
 *
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 *      <li> FR [ 1583 ] New Definition for loan
 *		@see https://github.com/adempiere/adempiere/issues/1583
 */
public abstract class AbstractFunctionalSetting {
	
	
	public AbstractFunctionalSetting(MFMFunctionalSetting setting) {
		this.setting = setting;
		this.ctx = setting.getCtx();
	}
	
	/**	Setting	*/
	private MFMFunctionalSetting setting;
	/**	Applicability	*/
	private MFMFunctionalApplicability applicability;
	/**	Value Parameters	*/
	private HashMap<String, Object> parameters = new HashMap<String, Object>();
	/**	Return Value */
	private HashMap<String, Object> returnValues = new HashMap<String, Object>();
	/**	Context	*/
	private Properties ctx;
	
	/**
	 * Get Context
	 * @return
	 */
	public Properties getCtx() {
		return ctx;
	}
	
	/**
	 * Set Functional Setting Applicability
	 * @param applicability
	 */
	public void setFunctionalApplicability(MFMFunctionalApplicability applicability) {
		this.applicability = applicability;
	}
	
	/**
	 * Get Functional Setting Applicability
	 * @return
	 */
	public MFMFunctionalApplicability getFunctionalApplicability() {
		return applicability;
	}
	
	/**
	 * Get Functional Setting
	 * @return
	 */
	public MFMFunctionalSetting getFunctionalSetting() {
		return setting;
	}
	
	/**
	 * Set Parameter Value
	 * @param key
	 * @param value
	 */
	public void setParameter(String key, Object value) {
		parameters.put(key, value);
	}
	
	/**
	 * Set from Parameters hash
	 * @param parameters
	 */
	public void setParameters(Map<String, Object> parameters) {
		for(Entry<String, Object> entry : parameters.entrySet()) {
			this.parameters.put(entry.getKey(), entry.getValue());
		}
	}
	
	/**
	 * Get a Parameter value from key
	 * @param key
	 * @return
	 */
	public Object getParameter(String key) {
		return parameters.get(key);
	}

	/**
	 * Get Parameter as Integer
	 * @param key
	 * @return
	 */
	public int getParameterAsInt(String key) {
		Object parameter = getParameter(key);
		if(parameter != null 
				&& parameter instanceof Integer) {
			return ((Integer) parameter).intValue();
		}
		//	Default
		return 0;
	}
	
	/**
	 * Get Parameter as BigDecimal
	 * @param key
	 * @return
	 */
	public BigDecimal getParameterAsBigDecimal(String key) {
		Object parameter = getParameter(key);
		if(parameter != null 
				&& parameter instanceof BigDecimal) {
			return ((BigDecimal) parameter);
		}
		//	Default
		return Env.ZERO;
	}
	
	/**
	 * Set Parameter Value
	 * @param key
	 * @param value
	 */
	public void setReturnValue(String key, Object value) {
		returnValues.put(key, value);
	}
	
	/**
	 * Get a Parameter value from key
	 * @param key
	 * @return
	 */
	public Object getReturnValue(String key) {
		return returnValues.get(key);
	}
	
	/**
	 * Get All return values
	 * @return
	 */
	public HashMap<String, Object> getReturnValues() {
		return returnValues;
	}
	
	/**
	 * Create Batch helper method
	 * @param dateDoc (optional)
	 * @return
	 */
	protected MFMBatch createBatch(Timestamp dateDoc) {
		String trxName = (String) getParameter(FinancialSetting.PARAMETER_TRX_NAME);
		PO entity = (PO) getParameter(FinancialSetting.PARAMETER_PO);
		MFMAccount account = (MFMAccount) parameters.get(FinancialSetting.ACCOUNT_PO);
		if(account == null) {
			return null;
		}
		//	
		MFMBatch batch = new MFMBatch(getCtx(), 0, trxName);
		if(dateDoc == null) {
			dateDoc = new Timestamp(System.currentTimeMillis());
		}
		batch.setDateDoc(dateDoc);
		batch.setFM_Account_ID(account.getFM_Account_ID());
		batch.setFM_FunctionalSetting_ID(applicability.getFM_FunctionalSetting_ID());
		if(entity != null) {
			batch.setAD_Org_ID(entity.getAD_Org_ID());
		}
		batch.setC_DocType_ID();
		batch.saveEx();
		//	Default Return
		return batch;
	}
	
	/**
	 * Run Process
	 * @return
	 */
	public abstract String run();
	
}	//	PaymentExport
