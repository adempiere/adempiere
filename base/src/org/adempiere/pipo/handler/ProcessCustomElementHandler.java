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

import org.adempiere.core.domains.models.I_AD_ProcessCustom;
import org.adempiere.core.domains.models.I_AD_ProcessParaCustom;
import org.adempiere.core.domains.models.I_AD_Role;
import org.adempiere.core.domains.models.I_AD_User;
import org.adempiere.pipo.PackOut;
import org.compiere.model.MProcessParaCustom;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.xml.sax.SAXException;

/**
 * Window customization support
 * @author Yamel Senih www.erpya.com
 * 
 */
public class ProcessCustomElementHandler extends GenericPOHandler {
	public void create(Properties ctx, TransformerHandler document) throws SAXException {
		int processCustomId = Env.getContextAsInt(ctx, I_AD_ProcessCustom.COLUMNNAME_AD_ProcessCustom_ID);
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
		packOut.createGenericPO(document, I_AD_ProcessCustom.Table_ID, processCustomId, true, excludedTables);
		//	Parameters
		List<MProcessParaCustom> parametersList = new Query(ctx, I_AD_ProcessParaCustom.Table_Name, I_AD_ProcessParaCustom.COLUMNNAME_AD_ProcessCustom_ID + " = ?", null)
				.setParameters(processCustomId)
				.setOnlyActiveRecords(true)
				.list();
		//	
		for(MProcessParaCustom parameter : parametersList) {
			packOut.createGenericPO(document, parameter, true, excludedTables);
		}
	}
}
