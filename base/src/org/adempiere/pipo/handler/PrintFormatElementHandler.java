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
 *                 Teo Sarca, SC ARHIPAC SERVICE SRL
 *****************************************************************************/
package org.adempiere.pipo.handler;

import java.util.List;
import java.util.Properties;

import javax.xml.transform.sax.TransformerHandler;

import org.adempiere.pipo.PackOut;
import org.compiere.model.I_AD_PrintFormat;
import org.compiere.model.I_AD_PrintFormatItem;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.model.X_AD_Package_Exp_Detail;
import org.compiere.print.MPrintFormat;
import org.compiere.print.MPrintFormatItem;
import org.compiere.util.Env;
import org.xml.sax.SAXException;

/**
 *	@author Dixon Martinez, dmartinez@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li>BR [1019] New Icon to export report definition is show only swing but not ZK https://github.com/adempiere/adempiere/issues/1019
 */
public class PrintFormatElementHandler extends GenericPOHandler {
	public void create(Properties ctx, TransformerHandler document) throws SAXException {
		int printFormatId = Env.getContextAsInt(ctx, X_AD_Package_Exp_Detail.COLUMNNAME_AD_PrintFormat_ID);
		PackOut packOut = (PackOut) ctx.get("PackOutProcess");
		if(packOut == null ) {
			packOut = new PackOut();
			packOut.setLocalContext(ctx);
		}
		//	Task
		packOut.createGenericPO(document, I_AD_PrintFormat.Table_ID, printFormatId, true, null);
		List<MPrintFormat> printFormatList = new Query(ctx, I_AD_PrintFormat.Table_Name, "AD_PrintFormat_ID = ? "
				+ "OR EXISTS(SELECT 1 FROM AD_PrintFormatItem pfi WHERE pfi.AD_PrintFormatChild_ID = AD_PrintFormat.AD_PrintFormat_ID AND pfi.AD_PrintFormat_ID = ?)", null)
			.setParameters(printFormatId, printFormatId)
			.<MPrintFormat>list();
		//	
		for(MPrintFormat printFormat : printFormatList) {
			packOut.createGenericPO(document, I_AD_PrintFormat.Table_ID, printFormat.getAD_PrintFormat_ID(), true, null);
			List<MPrintFormatItem> printFormatItemList = new Query(ctx, I_AD_PrintFormatItem.Table_Name, I_AD_PrintFormatItem.COLUMNNAME_AD_PrintFormat_ID + " = ? ", null)
				.setParameters(printFormatId)
				.<MPrintFormatItem>list();
			//	For
			for(MPrintFormatItem printFormatItem : printFormatItemList) {
				packOut.createGenericPO(document, I_AD_PrintFormatItem.Table_ID, printFormatItem.getAD_PrintFormatItem_ID(), true, null);
			}
		}
	}
	
	@Override
	protected void afterSave(PO entity) {
		MPrintFormat printFormat = (MPrintFormat) entity;
		List<MPrintFormatItem> printFormatItemList = new Query(Env.getCtx(), I_AD_PrintFormatItem.Table_Name, I_AD_PrintFormatItem.COLUMNNAME_AD_PrintFormat_ID + " = ? ", null)
				.setParameters(printFormat.getAD_PrintFormat_ID())
				.<MPrintFormatItem>list();
		//	For
		for(MPrintFormatItem printFormatItem : printFormatItemList) {
			printFormatItem.deleteEx(true);
		}
	}
}
