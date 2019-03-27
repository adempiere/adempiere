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
 * Contributor(s): Low Heng Sin hengsin@avantz.com                            *
 *****************************************************************************/
package org.adempiere.pipo.handler;

import java.util.Properties;

import javax.xml.transform.sax.TransformerHandler;

import org.adempiere.pipo.PackOut;
import org.compiere.model.MProcess;
import org.compiere.model.MProcessPara;
import org.compiere.util.Env;
import org.xml.sax.SAXException;

public class ProcessElementHandler extends GenericPOHandler {
	public void create(Properties ctx, TransformerHandler document) throws SAXException {
		int processId = Env.getContextAsInt(ctx, "AD_Process_ID");
		PackOut packOut = (PackOut) ctx.get("PackOutProcess");
		if(packOut == null ) {
			packOut = new PackOut();
			packOut.setLocalContext(ctx);
		}
		MProcess process = MProcess.get(ctx, processId);
		//	Form
		if(process.getAD_Form_ID() > 0) {
			packOut.createForm(process.getAD_Form_ID(), document);
		}
		//	Workflow
		if(process.getAD_Workflow_ID() > 0) {
			packOut.createWorkflow(process.getAD_Workflow_ID(), document);
		}
		//	Smart Browse
		if(process.getAD_Browse_ID() > 0) {
			packOut.createBrowse(process.getAD_Browse_ID(), document);
		}
		//	Report View
		if(process.getAD_ReportView_ID() > 0) {
			packOut.createReportview(process.getAD_ReportView_ID(), document);
		}
		//	Print format
		if(process.getAD_PrintFormat_ID() > 0) {
			packOut.createPrintFormat(process.getAD_PrintFormat_ID(), document);
		}
		//	Process
		packOut.createGenericPO(document, process, true, null);
		for(MProcessPara parameter : MProcess.get(ctx, processId).getParameters()) {
			if(parameter.getAD_Element_ID() > 0) {
				packOut.createAdElement(parameter.getAD_Element_ID(), document);
			}
			packOut.createGenericPO(document, parameter, true, null);
		}
	}
}
