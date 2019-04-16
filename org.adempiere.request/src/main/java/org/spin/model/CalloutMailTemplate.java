/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 or later of the GNU General Public               *
 * License as published                                                       *
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
package org.spin.model;

import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.I_R_MailText;
import org.compiere.model.MRefList;
import org.compiere.model.X_R_MailText;
import org.compiere.util.Util;

/**
 * 	Callout Mail Template
 * 	@author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 *			<li> FR Mail Template for distint events
 */
public class CalloutMailTemplate extends CalloutEngine {
	
	/**
	 * Set Name from List
	 * @param ctx
	 * @param windowNo
	 * @param tab
	 * @param field
	 * @param value
	 * @return
	 */
	public String eventType(Properties ctx, int windowNo, GridTab tab, GridField field, Object value) {
		//	Valid Value
		if(value == null)
			return "";
		//	Set Default
		String eventType = (String) tab.getValue(I_R_MailText.COLUMNNAME_EventType);
		String name = MRefList.getListName(ctx, X_R_MailText.EVENTTYPE_AD_Reference_ID, eventType);
		if(!Util.isEmpty(name)) {
			tab.setValue(I_R_MailText.COLUMNNAME_Name, name);
		}
		return "";
	}
}