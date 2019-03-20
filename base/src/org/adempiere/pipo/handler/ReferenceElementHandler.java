/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *
 * Copyright (C) 2005 Robert Klein. robeklein@hotmail.com
 * Contributor(s): Low Heng Sin hengsin@avantz.com
 *****************************************************************************/
package org.adempiere.pipo.handler;

import java.util.List;
import java.util.Properties;

import javax.xml.transform.sax.TransformerHandler;

import org.adempiere.pipo.PackOut;
import org.compiere.model.I_AD_Ref_List;
import org.compiere.model.Query;
import org.compiere.model.X_AD_Ref_List;
import org.compiere.model.X_AD_Ref_Table;
import org.compiere.model.X_AD_Reference;
import org.compiere.util.Env;
import org.xml.sax.SAXException;

public class ReferenceElementHandler extends GenericPOHandler {
	public void create(Properties ctx, TransformerHandler document) throws SAXException {
		int referenceId = Env.getContextAsInt(ctx, X_AD_Reference.COLUMNNAME_AD_Reference_ID);
		PackOut packOut = (PackOut) ctx.get("PackOutProcess");
		if(packOut == null ) {
			packOut = new PackOut();
			packOut.setLocalContext(ctx);
		}
		//	Reference
		X_AD_Reference reference = new X_AD_Reference(ctx, referenceId, null);
		packOut.createGenericPO(document, reference, true, null);
		if(reference.getValidationType().equals(X_AD_Reference.VALIDATIONTYPE_ListValidation)) {
			List<X_AD_Ref_List> referenceListAsList = new Query(ctx, I_AD_Ref_List.Table_Name, I_AD_Ref_List.COLUMNNAME_AD_Reference_ID + " = ?", null)
				.setParameters(referenceId)
				.setOnlyActiveRecords(true)
				.list();
			for(X_AD_Ref_List referenceList : referenceListAsList) {
				packOut.createGenericPO(document, referenceList, true, null);
			}
		} else if(reference.getValidationType().equals(X_AD_Reference.VALIDATIONTYPE_TableValidation)) {
			List<X_AD_Ref_Table> referenceTableAsList = new Query(ctx, I_AD_Ref_List.Table_Name, I_AD_Ref_List.COLUMNNAME_AD_Reference_ID + " = ?", null)
					.setParameters(referenceId)
					.setOnlyActiveRecords(true)
					.list();
				for(X_AD_Ref_Table referenceTable : referenceTableAsList) {
					packOut.createGenericPO(document, referenceTable, true, null);
				}
		}
	}
}
