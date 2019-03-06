/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.               *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *                                                                            *
 * Copyright (C) 2005 Robert Klein. robeklein@hotmail.com                     *
 * Copyright (C) 2003-2012 e-Evolution Consultants. All Rights Reserved.      *
 * Copyright (C) 2003-2012 Victor Pérez Juárez 								  * 
 * Contributor(s): Low Heng Sin hengsin@avantz.com                            *
 *                 Victor Perez  victor.perez@e-evoluton.com				  *
 *****************************************************************************/
package org.adempiere.pipo.handler;

import java.util.List;
import java.util.Properties;

import javax.xml.transform.sax.TransformerHandler;

import org.adempiere.model.I_AD_View;
import org.adempiere.model.I_AD_View_Column;
import org.adempiere.model.I_AD_View_Definition;
import org.adempiere.model.MViewColumn;
import org.adempiere.model.MViewDefinition;
import org.adempiere.model.X_AD_View_Definition;
import org.adempiere.pipo.PackOut;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.xml.sax.SAXException;

/**
 * 
 * @author victor.perez@e-evoluton.com, www.e-evolution.com
 * 
 */
public class ViewElementHandler extends GenericPOHandler {
	public void create(Properties ctx, TransformerHandler document) throws SAXException {
		int viewId = Env.getContextAsInt(ctx, "AD_View_ID");
		PackOut packOut = (PackOut) ctx.get("PackOutProcess");
		if(packOut == null ) {
			packOut = new PackOut();
			packOut.setLocalContext(ctx);
		}
		//	Task
		packOut.createGenericPO(document, I_AD_View.Table_ID, viewId, true, null);
		StringBuilder whereClause = new StringBuilder(I_AD_View.COLUMNNAME_AD_View_ID).append("=?");
		List<MViewDefinition> viewDefinitions = new Query(ctx,
				I_AD_View_Definition.Table_Name, whereClause.toString(),
				getTrxName(ctx))
				.setParameters(viewId)
				.setOrderBy(
						X_AD_View_Definition.COLUMNNAME_SeqNo
								+ ","
								+ X_AD_View_Definition.COLUMNNAME_AD_View_Definition_ID)
				.list();

		for (MViewDefinition vd : viewDefinitions) {
			packOut.createGenericPO(document, I_AD_View_Definition.Table_ID, vd.getAD_View_Definition_ID(), true, null);
			// View Columns tags.
			whereClause = new StringBuilder(I_AD_View_Definition.COLUMNNAME_AD_View_Definition_ID).append("=?");
			List<MViewColumn> viewColumns = new Query(ctx,
					I_AD_View_Column.Table_Name, whereClause.toString(),
					getTrxName(ctx)).setParameters(vd.getAD_View_Definition_ID())
					.list();
			for (MViewColumn vc : viewColumns) {
				packOut.createGenericPO(document, I_AD_View_Column.Table_ID, vc.getAD_View_Column_ID(), true, null);
			}
		}
	}
}
