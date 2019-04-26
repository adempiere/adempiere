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
import org.compiere.model.I_AD_PrintFormat;
import org.compiere.model.I_AD_ReportView;
import org.compiere.model.I_AD_ReportView_Col;
import org.compiere.model.Query;
import org.compiere.model.X_AD_ReportView_Col;
import org.compiere.print.MPrintFormat;
import org.compiere.util.Env;
import org.xml.sax.SAXException;

public class ReportViewElementHandler extends GenericPOHandler {
	public void create(Properties ctx, TransformerHandler document) throws SAXException {
		int reportViewId = Env.getContextAsInt(ctx, "AD_ReportView_ID");
		PackOut packOut = (PackOut) ctx.get("PackOutProcess");
		if(packOut == null ) {
			packOut = new PackOut();
			packOut.setLocalContext(ctx);
		}
		//	Task
		packOut.createGenericPO(document, I_AD_ReportView.Table_ID, reportViewId, true, null);
		//	Get all columns
		List<MPrintFormat> printFormatList = new Query(ctx, I_AD_PrintFormat.Table_Name, I_AD_PrintFormat.COLUMNNAME_AD_ReportView_ID + " = ?", null)
			.setParameters(reportViewId)
			.setClient_ID()
			.<MPrintFormat>list();
		//	All
		for(MPrintFormat printFormat : printFormatList) {
			packOut.createPrintFormat(printFormat.getAD_PrintFormat_ID(), document);
		}
		//	Get all columns
		List<X_AD_ReportView_Col> reportViewColumnList = new Query(ctx, I_AD_ReportView_Col.Table_Name, I_AD_ReportView_Col.COLUMNNAME_AD_ReportView_ID + " = ?", null)
			.setParameters(reportViewId)
			.setClient_ID()
			.<X_AD_ReportView_Col>list();
		for(X_AD_ReportView_Col reportViewColumn : reportViewColumnList) {
			packOut.createGenericPO(document, I_AD_ReportView_Col.Table_ID, reportViewColumn.getAD_ReportView_Col_ID(), true, null);
		}
	}
}
