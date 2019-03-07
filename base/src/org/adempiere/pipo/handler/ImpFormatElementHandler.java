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
 *                 Teo Sarca, teo.sarca@gmail.com
 *****************************************************************************/
package org.adempiere.pipo.handler;

import java.util.List;
import java.util.Properties;

import javax.xml.transform.sax.TransformerHandler;

import org.adempiere.pipo.PackOut;
import org.compiere.impexp.MImpFormatRow;
import org.compiere.model.I_AD_ImpFormat;
import org.compiere.model.I_AD_ImpFormat_Row;
import org.compiere.model.Query;
import org.compiere.model.X_AD_Package_Exp_Detail;
import org.compiere.util.Env;
import org.xml.sax.SAXException;

/**
 * Add support to generic PO Handler
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
public class ImpFormatElementHandler extends GenericPOHandler {

	public void create(Properties ctx, TransformerHandler document) throws SAXException {
		int importId = Env.getContextAsInt(ctx, X_AD_Package_Exp_Detail.COLUMNNAME_AD_ImpFormat_ID);
		PackOut packOut = (PackOut) ctx.get("PackOutProcess");
		if(packOut == null ) {
			packOut = new PackOut();
			packOut.setLocalContext(ctx);
		}
		//	Parent
		packOut.createGenericPO(document, I_AD_ImpFormat.Table_ID, importId, true, null);
		//	Import format Row
		List<MImpFormatRow> impFormatRowList = new Query(ctx, I_AD_ImpFormat_Row.Table_Name, "AD_ImpFormat_ID = ?", null)
			.setParameters(importId)
			.<MImpFormatRow>list();
		for(MImpFormatRow row : impFormatRowList) {
			packOut.createGenericPO(document, row);
		}
	}
}
