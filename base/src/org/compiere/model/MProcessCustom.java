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
import java.util.List;
import java.util.Properties;

import org.adempiere.core.domains.models.I_AD_ProcessParaCustom;
import org.adempiere.core.domains.models.X_AD_ProcessCustom;
import org.compiere.util.Util;

/**
 * Customization handler
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
public class MProcessCustom extends X_AD_ProcessCustom {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7788247772367181508L;
	
	public MProcessCustom(Properties ctx, int AD_ProcessCustom_ID, String trxName) {
		super(ctx, AD_ProcessCustom_ID, trxName);
		setShowHelp(null);
	}

	public MProcessCustom(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	@Override
	public void setAD_Process_ID(int processId) {
		super.setAD_Process_ID(processId);
		if(processId > 0) {
			MProcess process = MProcess.get(getCtx(), processId);
			setIsActive(process.isActive());
		}
	}
	
	/**
	 * Get parameters for it
	 * @return
	 */
	public List<MProcessParaCustom> getParameters() {
		//	Get
		return new Query(getCtx(), I_AD_ProcessParaCustom.Table_Name, COLUMNNAME_AD_ProcessCustom_ID + " = ?", get_TrxName())
				.setParameters(getAD_ProcessCustom_ID())
				.setOnlyActiveRecords(true)
				.list();
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if(newRecord
				&& !Util.isEmpty(getHierarchyType())
				&& getHierarchyType().equals(HIERARCHYTYPE_Overwrite)
				&& getAD_Process_ID() > 0) {
			MProcess process = MProcess.get(getCtx(), getAD_Process_ID());
			process.getParametersAsList().forEach(parameter -> {
				MProcessParaCustom customProcessParameter = new MProcessParaCustom(this);
				customProcessParameter.setProcessParameter(parameter);
				customProcessParameter.saveEx();
			});
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "MProcessCustom [getAD_ProcessCustom_ID()=" + getAD_ProcessCustom_ID() + ", getAD_Process_ID()="
				+ getAD_Process_ID() + ", getAD_Role_ID()=" + getAD_Role_ID() + ", getAD_User_ID()=" + getAD_User_ID()
				+ ", getASP_Level_ID()=" + getASP_Level_ID() + "]";
	}
}
