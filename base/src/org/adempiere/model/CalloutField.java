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
 * Copyright (C) 2003-2015 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpcya.com                                 *
 *****************************************************************************/
package org.adempiere.model;

import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MColumn;

/**
 * 	CalloutField Callout
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 9223372036854775807 ] Add default values for Name, Description, Entity Type...
 *		@see https://adempiere.atlassian.net/browse/ADEMPIERE-449
 *		<a href="https://github.com/adempiere/adempiere/issues/922">
 * 		@see FR [ 922 ] Is Allow Copy in model</a>
 */
public class CalloutField extends CalloutEngine {
	
	/**
	 * Set field attributes from column
	 * @param ctx
	 * @param WindowNo
	 * @param mTab
	 * @param mField
	 * @param value
	 * @return
	 */
	public String column(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value) {
		Integer m_AD_Column_ID = (Integer) value;
		if (m_AD_Column_ID == null || m_AD_Column_ID <= 0)
			return "";
		
		MColumn column = MColumn.get(ctx, m_AD_Column_ID);
		//	Set values from column
		if(column != null) {
			mTab.setValue("Name", column.getName());
			mTab.setValue("Description", column.getDescription ());
			mTab.setValue("Help", column.getHelp());
			mTab.setValue("EntityType", column.getEntityType());
			//	for Allow copy
			mTab.setValue("IsAllowCopy", column.isAllowCopy());
		}
		//	
		return "";
	}
}