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
import java.util.Properties;

import javax.xml.transform.sax.TransformerHandler;

import org.adempiere.pipo.AbstractElementHandler;
import org.adempiere.pipo.AttributeFiller;
import org.adempiere.pipo.Element;
import org.adempiere.pipo.PackOut;
import org.adempiere.pipo.exception.POSaveFailedException;
import org.compiere.model.I_AD_Column;
import org.compiere.model.I_AD_Ref_Table;
import org.compiere.model.I_AD_Reference;
import org.compiere.model.I_AD_Table;
import org.compiere.model.I_AD_Window;
import org.compiere.model.MRefTable;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.model.X_AD_Ref_Table;
import org.compiere.model.X_AD_Table;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class ReferenceTableElementHandler extends AbstractElementHandler {

	public void startElement(Properties ctx, Element element)
			throws SAXException {
		String elementValue = element.getElementValue();

		log.info(elementValue);
		Attributes atts = element.attributes;
		String uuid = getUUIDValue(atts, I_AD_Ref_Table.Table_Name);
		String referenceUuid = getUUIDValue(atts, I_AD_Ref_Table.COLUMNNAME_AD_Reference_ID);
		log.info(elementValue + " " + uuid);
		String entitytype = getStringValue(atts, I_AD_Table.COLUMNNAME_EntityType);
		if (isProcessElement(ctx, entitytype)) {
			if (element.parent != null && element.parent.skip) {
				element.skip = true;
				return;
			}
			
			int referenceId = 0;
			if (element.parent != null && element.parent.getElementValue().equals("reference") &&
				element.parent.recordId > 0) {
				referenceId = element.parent.recordId;
			} else {
				referenceId = getIdFromUUID(ctx, I_AD_Reference.Table_Name, referenceUuid);
			}
			if (referenceId <= 0 && getIntValue(atts, I_AD_Ref_Table.COLUMNNAME_AD_Reference_ID) > 0 && getIntValue(atts, I_AD_Ref_Table.COLUMNNAME_AD_Reference_ID) <= PackOut.MAX_OFFICIAL_ID) {
				referenceId = getIntValue(atts, I_AD_Ref_Table.COLUMNNAME_AD_Reference_ID);
			}
			//	 table
			uuid = getUUIDValue(atts, I_AD_Ref_Table.COLUMNNAME_AD_Table_ID);
			int tableId = getIdFromUUID(ctx, I_AD_Table.Table_Name, uuid);
			if (tableId == 0) {
				X_AD_Table table = new X_AD_Table(ctx, 0, getTrxName(ctx));
				table.setAccessLevel(X_AD_Table.ACCESSLEVEL_ClientPlusOrganization);
				table.setName(getStringValue(atts, I_AD_Table.COLUMNNAME_Name));
				table.setTableName(getStringValue(atts, I_AD_Table.COLUMNNAME_TableName));
				table.setUUID(uuid);
				//	Save Table
				try {
					table.saveEx(getTrxName(ctx));
					recordLog(ctx, 1, table.getName(), "Table", table
							.get_ID(), 0, "New", "AD_Table", get_IDWithColumn(
							ctx, "AD_Table", "TableName", "AD_Table"));
				} catch (Exception e) {
					recordLog(ctx, 0, table.getName(), "Table", table
							.get_ID(), 0, "New", "AD_Table", get_IDWithColumn(
							ctx, "AD_Table", "TableName", "AD_Table"));
				}
				tableId = getIdFromUUID(ctx, I_AD_Table.Table_Name, uuid);
			}
			//	Instance from DB
	        MRefTable refTable = new Query(ctx, I_AD_Ref_Table.Table_Name,
	                    I_AD_Ref_Table.COLUMNNAME_AD_Reference_ID + "=?", getTrxName(ctx)).
	                    setParameters(referenceId)
	                    .firstOnly();
			if (refTable == null) {
				refTable = new MRefTable(ctx, 0 , getTrxName(ctx));
			}
			uuid = getUUIDValue(atts, I_AD_Ref_Table.Table_Name);
			refTable.setUUID(uuid);
			//	For Key Column
			uuid = getUUIDValue(atts, I_AD_Ref_Table.COLUMNNAME_AD_Key);
			if (!Util.isEmpty(uuid)) {
				int id = getIdFromUUID(ctx, I_AD_Column.Table_Name, uuid);
				if (id <= 0) {
					element.defer = true;
					return;
				}
				refTable.setAD_Key(id);
			}
			//	For Display
			uuid = getUUIDValue(atts, I_AD_Ref_Table.COLUMNNAME_AD_Display);
			if (!Util.isEmpty(uuid)) {
				int id = getIdFromUUID(ctx, I_AD_Column.Table_Name, uuid);
				if (id <= 0) {
					element.defer = true;
					return;
				}
				refTable.setAD_Display(id);
			}
			//	For Table
			uuid = getUUIDValue(atts, I_AD_Ref_Table.COLUMNNAME_AD_Table_ID);
			if (!Util.isEmpty(uuid)) {
				int id = getIdFromUUID(ctx, I_AD_Table.Table_Name, uuid);
				if (id <= 0) {
					element.defer = true;
					return;
				}
				refTable.setAD_Table_ID(id);
			}
			//	For Window
			uuid = getUUIDValue(atts, I_AD_Ref_Table.COLUMNNAME_AD_Window_ID);
			if (!Util.isEmpty(uuid)) {
				int id = getIdFromUUID(ctx, I_AD_Window.Table_Name, uuid);
				if (id <= 0) {
					element.defer = true;
					return;
				}
				refTable.setAD_Window_ID(id);
			}
			//	Attributes
			refTable.setIsValueDisplayed(getBooleanValue(atts, I_AD_Ref_Table.COLUMNNAME_IsValueDisplayed));
			refTable.setIsActive(getBooleanValue(atts, I_AD_Ref_Table.COLUMNNAME_IsActive));
			refTable.setIsAlert(getBooleanValue(atts, I_AD_Ref_Table.COLUMNNAME_IsAlert));
			refTable.setIsDisplayIdentifier(getBooleanValue(atts, I_AD_Ref_Table.COLUMNNAME_IsDisplayIdentifier));
			refTable.setDisplaySQL(getStringValue(atts, I_AD_Ref_Table.COLUMNNAME_DisplaySQL));
			refTable.setEntityType(getStringValue(atts, I_AD_Ref_Table.COLUMNNAME_EntityType));
			refTable.setWhereClause(getStringValue(atts, I_AD_Ref_Table.COLUMNNAME_WhereClause));
			refTable.setOrderByClause(getStringValue(atts, I_AD_Ref_Table.COLUMNNAME_OrderByClause));
			
			//	Save
			try {
				refTable.saveEx(getTrxName(ctx));
				recordLog(ctx, 1, atts.getValue("ADRefenceNameID"),
						"Reference Table", referenceId, 0, "New", "AD_Ref_Table",
						get_IDWithColumn(ctx, "AD_Table", "TableName",
								"AD_Ref_Table"));
			} catch (Exception e) {
				recordLog(ctx, 0, atts.getValue("ADRefenceNameID"),
						"Reference Table", referenceId, 0, "New", "AD_Ref_Table",
						get_IDWithColumn(ctx, "AD_Table", "TableName",
								"AD_Ref_Table"));
				throw new POSaveFailedException("ReferenceTable");
			}
		} else {
			element.skip = true;
		}
	}

	public void endElement(Properties ctx, Element element) throws SAXException {
		
	}

	public void create(Properties ctx, TransformerHandler document) throws SAXException {
		int Reference_id = Env.getContextAsInt(ctx,
				X_AD_Ref_Table.COLUMNNAME_AD_Reference_ID);
		AttributesImpl atts = new AttributesImpl();
		createReferenceTableBinding(ctx, atts, Reference_id);
		document.startElement("", "", "referencetable", atts);
		document.endElement("", "", "referencetable");
	}

	private AttributesImpl createReferenceTableBinding(Properties ctx, AttributesImpl atts, int referenceId) {
		atts.clear();
		MRefTable refTable = MRefTable.getById(ctx, referenceId);
		AttributeFiller filler = new AttributeFiller(atts, refTable);
		if (referenceId <= PackOut.MAX_OFFICIAL_ID) {
			filler.add(I_AD_Ref_Table.COLUMNNAME_AD_Reference_ID);
		}
		filler.addUUID();
		//	Reference
		if(refTable.getAD_Reference_ID() > 0) {
			filler.add(I_AD_Ref_Table.COLUMNNAME_AD_Reference_ID, true);
			filler.addUUID(I_AD_Ref_Table.COLUMNNAME_AD_Reference_ID, getUUIDFromId(refTable.getCtx(), I_AD_Reference.Table_Name, refTable.getAD_Reference_ID()));
		}
		//	Table
		if(refTable.get_Table_ID() > 0) {
			filler.add(I_AD_Ref_Table.COLUMNNAME_AD_Table_ID, true);
			filler.addUUID(I_AD_Ref_Table.COLUMNNAME_AD_Table_ID, getUUIDFromId(refTable.getCtx(), I_AD_Table.Table_Name, refTable.get_Table_ID()));
			MTable table = MTable.get(ctx, refTable.getAD_Table_ID());
			filler.addString(I_AD_Table.COLUMNNAME_TableName, table.getTableName());
			filler.addString(I_AD_Table.COLUMNNAME_Name, table.getName());
		}
		//	Key Column
		if(refTable.getAD_Key() > 0) {
			filler.add(I_AD_Ref_Table.COLUMNNAME_AD_Key, true);
			filler.addUUID(I_AD_Ref_Table.COLUMNNAME_AD_Key, getUUIDFromId(refTable.getCtx(), I_AD_Column.Table_Name, refTable.getAD_Key()));
		}
		//	Display Column
		if(refTable.getAD_Display() > 0) {
			filler.add(I_AD_Ref_Table.COLUMNNAME_AD_Display, true);
			filler.addUUID(I_AD_Ref_Table.COLUMNNAME_AD_Display, getUUIDFromId(refTable.getCtx(), I_AD_Column.Table_Name, refTable.getAD_Display()));
		}
		//	Window
		if(refTable.getAD_Window_ID() > 0) {
			filler.add(I_AD_Ref_Table.COLUMNNAME_AD_Window_ID, true);
			filler.addUUID(I_AD_Ref_Table.COLUMNNAME_AD_Window_ID, getUUIDFromId(refTable.getCtx(), I_AD_Window.Table_Name, refTable.getAD_Window_ID()));
		}
		//	Attributes
		filler.add(I_AD_Ref_Table.COLUMNNAME_IsValueDisplayed);
		filler.add(I_AD_Ref_Table.COLUMNNAME_IsActive);
		filler.add(I_AD_Ref_Table.COLUMNNAME_IsAlert);
		filler.add(I_AD_Ref_Table.COLUMNNAME_IsDisplayIdentifier);
		filler.add(I_AD_Ref_Table.COLUMNNAME_DisplaySQL);
		filler.add(I_AD_Ref_Table.COLUMNNAME_EntityType);
		filler.add(I_AD_Ref_Table.COLUMNNAME_WhereClause);
		filler.add(I_AD_Ref_Table.COLUMNNAME_OrderByClause);
		return atts;
	}
}
