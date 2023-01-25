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

import org.adempiere.core.domains.models.X_AD_FieldCustom;
import org.adempiere.exceptions.AdempiereException;

/**
 * Customization handler
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
public class MFieldCustom extends X_AD_FieldCustom {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7788247772367181508L;
	
	public MFieldCustom(Properties ctx, int AD_FieldCustom_ID, String trxName) {
		super(ctx, AD_FieldCustom_ID, trxName);
	}

	public MFieldCustom(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Create from parent
	 * @param customTab
	 */
	public MFieldCustom(MTabCustom customTab) {
		this(customTab.getCtx(), 0, customTab.get_TrxName());
		setAD_TabCustom_ID(customTab.getAD_TabCustom_ID());
	}
	
	@Override
	public void setAD_Field_ID(int fieldId) {
		if(fieldId > 0) {
			MField field = new MField(getCtx(), fieldId, get_TrxName());
			setField(field);
		}
	}
	
	/**
	 * Copy all values from custom field
	 * Note that if current custom field already exist then the field referenced is keep
	 * <br> Call {@link org.compiere.model.PO#saveEx()} after use this method
	 * @param customField
	 */
	public void overwriteValuesFromCustomField(MFieldCustom customField) {
		if(getAD_FieldCustom_ID() == 0) {
			throw new AdempiereException("Use default constructor based on other custom field");
		}
		int fieldId = getAD_Field_ID();
		int customTabId = getAD_TabCustom_ID();
		PO.copyValues(customField, this);
		set_ValueNoCheck(COLUMNNAME_AD_TabCustom_ID, customTabId);
		set_ValueNoCheck(COLUMNNAME_AD_Field_ID, fieldId);
	}
	
	/**
	 * Set Field
	 * @param field
	 */
	public void setField(MField field) {
		super.setAD_Field_ID(field.getAD_Field_ID());
		setIsActive(field.isActive());
		setIsDisplayed(field.isDisplayed());
		setSeqNo(field.getSeqNo());
		setIsDisplayedGrid(field.isDisplayedGrid());
		if(field.getSeqNoGrid() > 0) {
			setSeqNoGrid(field.getSeqNoGrid());
		} else {
			setSeqNoGrid(field.getSeqNo());
		}
	}

	@Override
	public String toString() {
		return "MFieldCustom [getAD_Field_ID()=" + getAD_Field().getName() + " - " + getAD_Field_ID() + ", isDisplayed()=" + isDisplayed()
				+ ", isActive()=" + isActive() + "]";
	}
}
