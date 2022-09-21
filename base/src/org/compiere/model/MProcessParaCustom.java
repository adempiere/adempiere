/*************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                              *
 * This program is free software; you can redistribute it and/or modify it    		 *
 * under the terms version 2 or later of the GNU General Public License as published *
 * by the Free Software Foundation. This program is distributed in the hope   		 *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 		 *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           		 *
 * See the GNU General Public License for more details.                       		 *
 * You should have received a copy of the GNU General Public License along    		 *
 * with this program; if not, write to the Free Software Foundation, Inc.,    		 *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     		 *
 * For the text or an alternative of this public license, you may reach us    		 *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved. *
 * Contributor(s): Yamel Senih www.erpya.com				  		                 *
 *************************************************************************************/
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.core.domains.models.X_AD_ProcessParaCustom;
import org.adempiere.exceptions.AdempiereException;

/**
 * Customization handler
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
public class MProcessParaCustom extends X_AD_ProcessParaCustom {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7788247772367181508L;
	
	public MProcessParaCustom(Properties ctx, int AD_ProcessParaCustom_ID, String trxName) {
		super(ctx, AD_ProcessParaCustom_ID, trxName);
	}

	public MProcessParaCustom(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Create from parent
	 * @param customProcess
	 */
	public MProcessParaCustom(MProcessCustom customProcess) {
		this(customProcess.getCtx(), 0, customProcess.get_TrxName());
		setAD_ProcessCustom_ID(customProcess.getAD_ProcessCustom_ID());
	}
	
	@Override
	public void setAD_Process_Para_ID(int processParaId) {
		if(processParaId > 0) {
			MProcessPara processParameter = MProcessPara.get(getCtx(), processParaId);
			setProcessParameter(processParameter);
		}
	}
	
	/**
	 * Copy all values from custom parameter
	 * Note that if current custom parameter already exist then the parameter referenced is keep
	 * <br> Call {@link org.compiere.model.PO#saveEx()} after use this method
	 * @param customParameter
	 */
	public void overwriteValuesFromCustomParameter(MProcessParaCustom customParameter) {
		if(getAD_ProcessParaCustom_ID() == 0) {
			throw new AdempiereException("Use default constructor based on other custom parameter");
		}
		int parameterId = getAD_Process_Para_ID();
		int customProcessId = getAD_ProcessCustom_ID();
		PO.copyValues(customParameter, this);
		setIsActive(customParameter.isActive());
		set_ValueNoCheck(COLUMNNAME_AD_ProcessCustom_ID, customProcessId);
		set_ValueNoCheck(COLUMNNAME_AD_Process_Para_ID, parameterId);
	}
	
	/**
	 * Set Process Parameter
	 * @param processParameter
	 */
	public void setProcessParameter(MProcessPara processParameter) {
		super.setAD_Process_Para_ID(processParameter.getAD_Process_Para_ID());
		setIsActive(processParameter.isActive());
		setSeqNo(processParameter.getSeqNo());
	}

	@Override
	public String toString() {
		return "MProcessParaCustom [getAD_ProcessCustom_ID()=" + getAD_ProcessCustom_ID() + ", getAD_Process_Para_ID()="
				+ getAD_Process_Para_ID() + ", getName()=" + getName() + "]";
	}
}
