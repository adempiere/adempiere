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
package org.adempiere.pipo.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.transform.sax.TransformerHandler;

import org.adempiere.core.domains.models.I_AD_FieldCustom;
import org.adempiere.core.domains.models.I_AD_Role;
import org.adempiere.core.domains.models.I_AD_TabCustom;
import org.adempiere.core.domains.models.I_AD_User;
import org.adempiere.core.domains.models.I_AD_WindowCustom;
import org.adempiere.pipo.PackOut;
import org.compiere.model.MFieldCustom;
import org.compiere.model.MTabCustom;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.xml.sax.SAXException;

/**
 * Window customization support
 * @author Yamel Senih www.erpya.com
 * 
 */
public class WindowCustomElementHandler extends GenericPOHandler {
	public void create(Properties ctx, TransformerHandler document) throws SAXException {
		int windowCustomId = Env.getContextAsInt(ctx, I_AD_WindowCustom.COLUMNNAME_AD_WindowCustom_ID);
		PackOut packOut = (PackOut) ctx.get("PackOutProcess");
		if(packOut == null ) {
			packOut = new PackOut();
			packOut.setLocalContext(ctx);
		}
		//	Excluded
		List<String> excludedTables = new ArrayList<>();
		excludedTables.add(I_AD_User.Table_Name);
		excludedTables.add(I_AD_Role.Table_Name);
		//	
		packOut.createGenericPO(document, I_AD_WindowCustom.Table_ID, windowCustomId, true, excludedTables);
		//	Tabs
		List<MTabCustom> tabList = new Query(ctx, I_AD_TabCustom.Table_Name, I_AD_TabCustom.COLUMNNAME_AD_WindowCustom_ID + " = ?", null)
				.setParameters(windowCustomId)
				.setOnlyActiveRecords(true)
				.list();
		//	
		for(MTabCustom tab : tabList) {
			packOut.createGenericPO(document, tab, true, excludedTables);
			//	Fields
			List<MFieldCustom> fieldList = new Query(ctx, I_AD_FieldCustom.Table_Name, I_AD_FieldCustom.COLUMNNAME_AD_TabCustom_ID + " = ?", null)
					.setParameters(tab.getAD_TabCustom_ID())
					.setOnlyActiveRecords(true)
					.list();
			//	
			for(MFieldCustom field : fieldList) {
				packOut.createGenericPO(document, field, true, excludedTables);
			}
		}
	}
}
