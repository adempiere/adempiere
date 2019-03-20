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

import org.adempiere.model.I_AD_Browse;
import org.adempiere.model.I_AD_Browse_Field;
import org.adempiere.model.MBrowse;
import org.adempiere.model.MBrowseField;
import org.adempiere.pipo.PackOut;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.xml.sax.SAXException;

/**
 * 
 * @author victor.perez@e-evoluton.com, www.e-evolution.com
 * 
 */
public class BrowseElementHandler extends GenericPOHandler {
	public void create(Properties ctx, TransformerHandler document) throws SAXException {
		int browseId = Env.getContextAsInt(ctx, "AD_Browse_ID");
		PackOut packOut = (PackOut) ctx.get("PackOutProcess");
		if(packOut == null ) {
			packOut = new PackOut();
			packOut.setLocalContext(ctx);
		}
		MBrowse browse = MBrowse.get(ctx, browseId);
		//	Export View
		packOut.createView(browse.getAD_View_ID(), document);
		//	Export Process
		if(browse.getAD_Process_ID() > 0) {
			packOut.createProcess(browse.getAD_Process_ID(), document);
		}
		//	Export Table
		if(browse.getAD_Table_ID() > 0) {
			packOut.createTable(browse.getAD_Table_ID(), document);
		}
		//	Export Window
		if(browse.getAD_Window_ID() > 0) {
			packOut.createWindow(browse.getAD_Window_ID(), document);
		}
		//	Browse
		packOut.createGenericPO(document, browse, true, null);
		//	Browse Field
		List<MBrowseField> browseFieldList = new Query(ctx, I_AD_Browse_Field.Table_Name, I_AD_Browse.COLUMNNAME_AD_Browse_ID + " = ?", null)
				.setParameters(browseId)
				.<MBrowseField>list();
		for(MBrowseField field : browseFieldList) {
			packOut.createGenericPO(document, field, true, null);
		}
	}
}
