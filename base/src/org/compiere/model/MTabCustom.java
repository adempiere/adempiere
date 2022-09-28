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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.adempiere.core.domains.models.I_AD_FieldCustom;
import org.adempiere.core.domains.models.X_AD_TabCustom;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.Util;

/**
 * Customization handler
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
public class MTabCustom extends X_AD_TabCustom {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7788247772367181508L;
	
	public MTabCustom(Properties ctx, int AD_TabCustom_ID, String trxName) {
		super(ctx, AD_TabCustom_ID, trxName);
	}

	public MTabCustom(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Create from Custom Window
	 * @param customWindow
	 */
	public MTabCustom(MWindowCustom customWindow) {
		this(customWindow.getCtx(), 0, customWindow.get_TrxName());
		setAD_WindowCustom_ID(customWindow.getAD_WindowCustom_ID());
	}
	
	@Override
	public void setAD_Tab_ID(int tabId) {
		super.setAD_Tab_ID(tabId);
		if(tabId > 0) {
			MTab tab = new MTab(getCtx(), tabId, get_TrxName());
			setIsActive(tab.isActive());
			setSeqNo(tab.getSeqNo());
			setTabLevel(tab.getTabLevel());
		}
	}
	
	/**
	 * Copy all values from custom tab
	 * Note that if current custom tab already exist then the field referenced is keep
	 * <br> Call {@link org.compiere.model.PO#saveEx()} after use this method
	 * @param customTab
	 */
	public void overwriteValuesFromCustomTab(MTabCustom customTab) {
		if(getAD_TabCustom_ID() == 0) {
			throw new AdempiereException("Use default constructor based on other custom field");
		}
		int tabId = getAD_Tab_ID();
		int customWindowId = getAD_WindowCustom_ID();
		PO.copyValues(customTab, this);
		setIsActive(customTab.isActive());
		set_ValueNoCheck(COLUMNNAME_AD_WindowCustom_ID, customWindowId);
		set_ValueNoCheck(COLUMNNAME_AD_Tab_ID, tabId);
	}
	
	/**
	 * Get tabs for it
	 * @return
	 */
	public List<MFieldCustom> getFields() {
		//	Get
		return new Query(getCtx(), I_AD_FieldCustom.Table_Name, COLUMNNAME_AD_TabCustom_ID + " = ?", get_TrxName())
				.setParameters(getAD_TabCustom_ID())
				.setOnlyActiveRecords(true)
				.list();
	}
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if(newRecord
				&& getAD_WindowCustom_ID() > 0
				&& getAD_Tab_ID() > 0) {
			Optional.ofNullable(getAD_WindowCustom()).ifPresent(customWindow -> {
				if(!Util.isEmpty(customWindow.getHierarchyType())
						&& customWindow.getHierarchyType().equals(MWindowCustom.HIERARCHYTYPE_Overwrite)) {
					//	For fields
					Arrays.asList(MTab.get(getCtx(), getAD_Tab_ID()).getFields(true, get_TrxName())).forEach(field -> {
						MFieldCustom customField = new MFieldCustom(this);
						customField.setField(field);
						customField.saveEx();
					});
				}
			});
		}
		return super.afterSave(newRecord, success);
	}
	
	@Override
	public String toString() {
		return "MTabCustom [getAD_TabCustom_ID()=" + getAD_TabCustom_ID() + ", getAD_Tab_ID()="
				+ getAD_Tab_ID() + "]";
	}
}
