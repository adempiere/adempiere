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

import org.adempiere.core.domains.models.I_AD_BrowseFieldCustom;
import org.adempiere.core.domains.models.X_AD_BrowseCustom;
import org.adempiere.model.MBrowse;
import org.compiere.util.Util;

/**
 * Customization handler
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
public class MBrowseCustom extends X_AD_BrowseCustom {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7788247772367181508L;
	
	public MBrowseCustom(Properties ctx, int AD_BrowseCustom_ID, String trxName) {
		super(ctx, AD_BrowseCustom_ID, trxName);
		setIsCollapsibleByDefault(null);
		setIsDeleteable(null);
		setIsExecutedQueryByDefault(null);
		setIsSelectedByDefault(null);
		setIsShowTotal(null);
		setIsUpdateable(null);
	}

	public MBrowseCustom(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * Get parameters for it
	 * @return
	 */
	public List<MBrowseFieldCustom> getFields() {
		//	Get
		return new Query(getCtx(), I_AD_BrowseFieldCustom.Table_Name, COLUMNNAME_AD_BrowseCustom_ID + " = ?", get_TrxName())
				.setParameters(getAD_BrowseCustom_ID())
				.setOnlyActiveRecords(true)
				.list();
	}
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		if(newRecord
				&& !Util.isEmpty(getHierarchyType())
				&& getHierarchyType().equals(HIERARCHYTYPE_Overwrite)
				&& getAD_Browse_ID() > 0) {
			MBrowse browse = MBrowse.get(getCtx(), getAD_Browse_ID());
			browse.getFields().forEach(field -> {
				MBrowseFieldCustom customBrowseField = new MBrowseFieldCustom(this);
				customBrowseField.setField(field);
				customBrowseField.saveEx();
			});
		}
		return true;
	}

	@Override
	public String toString() {
		return "MBrowseCustom [getAD_BrowseCustom_ID()=" + getAD_BrowseCustom_ID() + ", getAD_Browse_ID()="
				+ getAD_Browse_ID() + ", getAD_Role_ID()=" + getAD_Role_ID() + ", getAD_User_ID()=" + getAD_User_ID()
				+ ", getASP_Level_ID()=" + getASP_Level_ID() + "]";
	}
}
