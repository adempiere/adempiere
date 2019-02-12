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

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.transform.sax.TransformerHandler;

import org.adempiere.pipo.AbstractElementHandler;
import org.adempiere.pipo.AttributeFiller;
import org.adempiere.pipo.Element;
import org.adempiere.pipo.PackOut;
import org.adempiere.pipo.exception.POSaveFailedException;
import org.compiere.model.I_AD_Reference;
import org.compiere.model.X_AD_Ref_List;
import org.compiere.model.X_AD_Ref_Table;
import org.compiere.model.X_AD_Reference;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class ReferenceElementHandler extends AbstractElementHandler {

	private ReferenceListElementHandler listHandler = new ReferenceListElementHandler();
	private ReferenceTableElementHandler tableHandler = new ReferenceTableElementHandler();
	
	private List<Integer> references = new ArrayList<Integer>();

	public void startElement(Properties ctx, Element element)
			throws SAXException {
		String elementValue = element.getElementValue();
		int backupId = -1;
		String objectStatus = null;
		Attributes atts = element.attributes;
		String uuid = getUUIDValue(atts, I_AD_Reference.Table_Name);
		log.info(elementValue + " " + uuid);
		String entitytype = getStringValue(atts, I_AD_Reference.COLUMNNAME_EntityType);
		if (isProcessElement(ctx, entitytype)) {
			int id = getIdFromUUID(ctx, I_AD_Reference.Table_Name, uuid);
			X_AD_Reference reference = new X_AD_Reference(ctx, id, getTrxName(ctx));
			if (id <= 0 && getIntValue(atts, I_AD_Reference.COLUMNNAME_AD_Reference_ID) > 0 && getIntValue(atts, I_AD_Reference.COLUMNNAME_AD_Reference_ID) <= PackOut.MAX_OFFICIAL_ID) {
				reference.setAD_Reference_ID(getIntValue(atts, I_AD_Reference.COLUMNNAME_AD_Reference_ID));
				reference.setIsDirectLoad(true);
			}
			if (id > 0) {
				backupId = copyRecord(ctx, "AD_Reference", reference);
				objectStatus = "Update";
				if (references.contains(id)) {
					element.skip = true;
					return;
				}
			} else {
				objectStatus = "New";
				backupId = 0;
			}
			reference.setUUID(uuid);
			//	Standard Attributes
			reference.setName(getStringValue(atts, I_AD_Reference.COLUMNNAME_Name));
			reference.setDescription(getStringValue(atts, I_AD_Reference.COLUMNNAME_Description));
			reference.setHelp(getStringValue(atts, I_AD_Reference.COLUMNNAME_Help));
			reference.setEntityType(getStringValue(atts, I_AD_Reference.COLUMNNAME_EntityType));
			reference.setIsActive(getBooleanValue(atts, I_AD_Reference.COLUMNNAME_IsActive));
			reference.setVFormat(getStringValue(atts, I_AD_Reference.COLUMNNAME_VFormat));
			reference.setValidationType(getStringValue(atts, I_AD_Reference.COLUMNNAME_ValidationType));
			reference.setIsOrderByValue(getBooleanValue(atts, I_AD_Reference.COLUMNNAME_IsOrderByValue));
			//	Save
			try {
				reference.saveEx(getTrxName(ctx));
				recordLog(ctx, 1, reference.getUUID(), "Reference",
						reference.get_ID(), backupId, objectStatus,
						"AD_Reference", get_IDWithColumn(ctx, "AD_Table",
								"TableName", "AD_Reference"));
				references.add(reference.getAD_Reference_ID());
				element.recordId = reference.getAD_Reference_ID();
			} catch (Exception e) {
				recordLog(ctx, 0, reference.getUUID(), "Reference",
						reference.get_ID(), backupId, objectStatus,
						"AD_Reference", get_IDWithColumn(ctx, "AD_Table",
								"TableName", "AD_Reference"));
				throw new POSaveFailedException(e);
			}
		} else {
			element.skip = true;
		}
	}

	public void endElement(Properties ctx, Element element) throws SAXException {
		
	}

	public void create(Properties ctx, TransformerHandler document) throws SAXException {
		int referenceId = Env.getContextAsInt(ctx, X_AD_Reference.COLUMNNAME_AD_Reference_ID);
		if (references.contains(referenceId)) {
			return;
		}
		//	
		references.add(referenceId);
		AttributesImpl atts = new AttributesImpl();
		X_AD_Reference reference = new X_AD_Reference(ctx, referenceId, null);
		createReferenceBinding(atts, reference);
		document.startElement("", "", "reference", atts);
		if(reference.getValidationType().equals(X_AD_Reference.VALIDATIONTYPE_ListValidation)) {
			String sqlReferenceList = "SELECT AD_Ref_List_ID "
					+ "FROM AD_Ref_List "
					+ "WHERE AD_Reference_ID= ? "
					+ "ORDER BY Value, AD_Ref_List_ID";
			int[] refListIds = DB.getIDsEx(null, sqlReferenceList, referenceId);
			for(int id : refListIds) {
				createReferenceList(ctx, document, id);
			}
		} else if(reference.getValidationType().equals(X_AD_Reference.VALIDATIONTYPE_TableValidation)) {
			createReferenceTable(ctx, document, referenceId);
		}
		document.endElement("", "", "reference");
	}

	private void createReferenceTable(Properties ctx, TransformerHandler document,
			int referenceId) throws SAXException {
		Env.setContext(ctx, X_AD_Ref_Table.COLUMNNAME_AD_Reference_ID, referenceId);
		tableHandler.create(ctx, document);
		ctx.remove(X_AD_Ref_Table.COLUMNNAME_AD_Reference_ID);
	}

	private void createReferenceList(Properties ctx, TransformerHandler document, int refListId) throws SAXException {
		Env.setContext(ctx, X_AD_Ref_List.COLUMNNAME_AD_Ref_List_ID, refListId);
		listHandler.create(ctx, document);
		ctx.remove(X_AD_Ref_List.COLUMNNAME_AD_Ref_List_ID);
	}

	private AttributesImpl createReferenceBinding(AttributesImpl atts, X_AD_Reference reference) {
		atts.clear();
		AttributeFiller filler = new AttributeFiller(atts, reference);
		if (reference.getAD_Reference_ID() <= PackOut.MAX_OFFICIAL_ID) {
			filler.add(X_AD_Reference.COLUMNNAME_AD_Reference_ID);
		}
		filler.addUUID();
		//	
		filler.add(I_AD_Reference.COLUMNNAME_Name);
		filler.add(I_AD_Reference.COLUMNNAME_Description);
		filler.add(I_AD_Reference.COLUMNNAME_Help);
		filler.add(I_AD_Reference.COLUMNNAME_EntityType);
		filler.add(I_AD_Reference.COLUMNNAME_IsActive);
		filler.add(I_AD_Reference.COLUMNNAME_VFormat);
		filler.add(I_AD_Reference.COLUMNNAME_ValidationType);
		filler.add(I_AD_Reference.COLUMNNAME_IsOrderByValue);
		return atts;
	}
}
