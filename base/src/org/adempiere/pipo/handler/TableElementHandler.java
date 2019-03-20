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
import org.compiere.model.I_AD_Column;
import org.compiere.model.I_AD_Sequence;
import org.compiere.model.I_AD_Table;
import org.compiere.model.I_AD_Table_Process;
import org.compiere.model.MColumn;
import org.compiere.model.MSequence;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.model.X_AD_Package_Exp_Detail;
import org.compiere.model.X_AD_Table_Process;
import org.compiere.util.Env;
import org.xml.sax.SAXException;

/**
 * Change to Generic PO Handler for import and export PO
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
public class TableElementHandler extends GenericPOHandler {
	
	public void create(Properties ctx, TransformerHandler document) throws SAXException {
		int tableId = Env.getContextAsInt(ctx, X_AD_Package_Exp_Detail.COLUMNNAME_AD_Table_ID);
		PackOut packOut = (PackOut) ctx.get("PackOutProcess");
		if(packOut == null ) {
			packOut = new PackOut();
			packOut.setLocalContext(ctx);
		}
		//	Table
		packOut.createGenericPO(document, I_AD_Table.Table_ID, tableId, true, null);
		MTable table = MTable.get(ctx, tableId);
		for(MColumn colunm : table.getColumns(true)) {
			if(colunm.getAD_Reference_Value_ID() > 0) {
				packOut.createReference(colunm.getAD_Reference_Value_ID(), document);
			}
			packOut.createGenericPO(document, I_AD_Column.Table_ID, colunm.getAD_Column_ID(), true, null);
		}
		//	Create Sequence
		MSequence sequence = MSequence.get(ctx, table.getTableName());
		if(sequence != null) {
			packOut.createGenericPO(document, I_AD_Sequence.Table_ID, sequence.getAD_Sequence_ID(), true, null);
		}
		//	Process
		List<X_AD_Table_Process> assignedProcessList = new Query(ctx, I_AD_Table_Process.Table_Name, I_AD_Table_Process.COLUMNNAME_AD_Table_ID + " = ?", null)
				.setParameters(table.getAD_Table_ID())
				.setOnlyActiveRecords(true)
				.<X_AD_Table_Process>list();
		//	Create
		for(X_AD_Table_Process assignedProcess : assignedProcessList) {
			packOut.createProcess(assignedProcess.getAD_Process_ID(), document);
			packOut.createGenericPO(document, assignedProcess, true, null);
		}
	}
}
