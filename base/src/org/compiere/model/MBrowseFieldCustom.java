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

import org.adempiere.core.domains.models.X_AD_BrowseFieldCustom;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.MBrowseField;

/**
 * Customization handler
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
public class MBrowseFieldCustom extends X_AD_BrowseFieldCustom {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7788247772367181508L;
	
	public MBrowseFieldCustom(Properties ctx, int AD_BrowseFieldCustom_ID, String trxName) {
		super(ctx, AD_BrowseFieldCustom_ID, trxName);
	}

	public MBrowseFieldCustom(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Create from parent
	 * @param customBrowse
	 */
	public MBrowseFieldCustom(MBrowseCustom customBrowse) {
		this(customBrowse.getCtx(), 0, customBrowse.get_TrxName());
		setAD_BrowseCustom_ID(customBrowse.getAD_BrowseCustom_ID());
	}
	
	@Override
	public void setAD_Browse_Field_ID(int browseFieldId) {
		if(browseFieldId > 0) {
			MBrowseField field = new MBrowseField(getCtx(), browseFieldId, get_TrxName());
			setField(field);
		}
	}
	
	/**
	 * Copy all values from custom field
	 * Note that if current custom field already exist then the field referenced is keep
	 * <br> Call {@link org.compiere.model.PO#saveEx()} after use this method
	 * @param customField
	 */
	public void overwriteValuesFromCustomField(MBrowseFieldCustom customField) {
		if(getAD_BrowseFieldCustom_ID() == 0) {
			throw new AdempiereException("Use default constructor based on other custom field");
		}
		int browseFieldId = getAD_Browse_Field_ID();
		int customBrowseId = getAD_BrowseCustom_ID();
		PO.copyValues(customField, this);
		set_ValueNoCheck(COLUMNNAME_AD_BrowseCustom_ID, customBrowseId);
		set_ValueNoCheck(COLUMNNAME_AD_Browse_Field_ID, browseFieldId);
	}
	
	/**
	 * Set Field
	 * @param field
	 */
	public void setField(MBrowseField field) {
		super.setAD_Browse_Field_ID(field.getAD_Browse_Field_ID());
		setIsActive(field.isActive());
		setIsDisplayed(field.isDisplayed());
		setIsOrderBy(field.isOrderBy());
		setIsQueryCriteria(field.isQueryCriteria());
		setSeqNo(field.getSeqNo());
		setSeqNoGrid(field.getSeqNoGrid());
	}
}
