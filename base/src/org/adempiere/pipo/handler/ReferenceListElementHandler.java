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
import org.compiere.model.I_AD_Ref_List;
import org.compiere.model.I_AD_Reference;
import org.compiere.model.X_AD_Ref_List;
import org.compiere.util.Env;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class ReferenceListElementHandler extends AbstractElementHandler {

	public void startElement(Properties ctx, Element element) throws SAXException {
		String elementValue = element.getElementValue();
		int backupId = -1;
		String objectStatus = null;
		Attributes atts = element.attributes;
		String uuid = getUUIDValue(atts, I_AD_Ref_List.Table_Name);
		log.info(elementValue + " " + uuid);
		String entitytype = getStringValue(atts, I_AD_Ref_List.COLUMNNAME_EntityType);
		if (isProcessElement(ctx, entitytype)) {
			if (element.parent != null && element.parent.skip) {
				element.skip = true;
				return;
			}
			String referenceUuid = getUUIDValue(atts, I_AD_Ref_List.COLUMNNAME_AD_Reference_ID);
			int referenceId = getIdFromUUID(ctx, I_AD_Reference.Table_Name, referenceUuid);
			int refListId = getIdFromUUID(ctx, I_AD_Ref_List.Table_Name, uuid);
			X_AD_Ref_List refList = new X_AD_Ref_List(ctx, refListId, getTrxName(ctx));
			if (refListId <= 0 && getIntValue(atts, I_AD_Ref_List.COLUMNNAME_AD_Ref_List_ID) > 0 && getIntValue(atts, I_AD_Ref_List.COLUMNNAME_AD_Ref_List_ID) <= PackOut.MAX_OFFICIAL_ID) {
				refList.setAD_Ref_List_ID(getIntValue(atts, I_AD_Ref_List.COLUMNNAME_AD_Ref_List_ID));
				refList.setIsDirectLoad(true);
			}
			if (refListId > 0) {
				backupId = copyRecord(ctx, "AD_Ref_List", refList);
				objectStatus = "Update";
			} else {
				objectStatus = "New";
				backupId = 0;
			}
			refList.setUUID(uuid);
			refList.setAD_Reference_ID(referenceId);
			refList.setValue(getStringValue(atts, I_AD_Ref_List.COLUMNNAME_Value));
			refList.setName(getStringValue(atts, I_AD_Ref_List.COLUMNNAME_Name));
			refList.setDescription(getStringValue(atts, I_AD_Ref_List.COLUMNNAME_Description));
			refList.setEntityType(getStringValue(atts, I_AD_Ref_List.COLUMNNAME_EntityType));
			refList.setIsActive(getBooleanValue(atts, I_AD_Ref_List.COLUMNNAME_IsActive));
			refList.setValidFrom(getTimestampValue(atts, I_AD_Ref_List.COLUMNNAME_ValidFrom));
			refList.setValidTo(getTimestampValue(atts, I_AD_Ref_List.COLUMNNAME_ValidTo));
			//	Save
			try {
				refList.saveEx(getTrxName(ctx));
				recordLog(ctx, 1, refList.getUUID(), "Reference List",
						refList.get_ID(), backupId, objectStatus,
						"AD_Ref_List", get_IDWithColumn(ctx, "AD_Table",
								"TableName", "AD_Ref_List"));
			} catch (Exception e) {
				recordLog(ctx, 0, refList.getUUID(), "Reference List",
						refList.get_ID(), backupId, objectStatus,
						"AD_Ref_List", get_IDWithColumn(ctx, "AD_Table",
								"TableName", "AD_Ref_List"));
				throw new POSaveFailedException(e);
			}
		} else {
			element.skip = true;
		}
	}

	public void endElement(Properties ctx, Element element) throws SAXException {
		
	}

	public void create(Properties ctx, TransformerHandler document)
			throws SAXException {
		int refListId = Env.getContextAsInt(ctx, X_AD_Ref_List.COLUMNNAME_AD_Ref_List_ID);
		X_AD_Ref_List refList = new X_AD_Ref_List(ctx, refListId, getTrxName(ctx));
		AttributesImpl atts = new AttributesImpl();
		createRefListBinding(atts, refList);
		document.startElement("", "", "referencelist", atts);
		document.endElement("", "", "referencelist");
	}

	private AttributesImpl createRefListBinding(AttributesImpl atts, X_AD_Ref_List refList) {
		atts.clear();
		AttributeFiller filler = new AttributeFiller(atts, refList);
		if (refList.getAD_Ref_List_ID() <= PackOut.MAX_OFFICIAL_ID) {
			filler.add(I_AD_Ref_List.COLUMNNAME_AD_Ref_List_ID);
		}
		filler.addUUID();
		//	Reference
		if(refList.getAD_Reference_ID() > 0) {
			filler.add(I_AD_Ref_List.COLUMNNAME_AD_Reference_ID, true);
			filler.addUUID(I_AD_Ref_List.COLUMNNAME_AD_Reference_ID, getUUIDFromId(refList.getCtx(), I_AD_Reference.Table_Name, refList.getAD_Reference_ID()));
		}
		//	Attributes
		filler.add(I_AD_Ref_List.COLUMNNAME_Value);
		filler.add(I_AD_Ref_List.COLUMNNAME_Name);
		filler.add(I_AD_Ref_List.COLUMNNAME_Description);
		filler.add(I_AD_Ref_List.COLUMNNAME_EntityType);
		filler.add(I_AD_Ref_List.COLUMNNAME_IsActive);
		filler.add(I_AD_Ref_List.COLUMNNAME_ValidFrom);
		filler.add(I_AD_Ref_List.COLUMNNAME_ValidTo);
		return atts;
	}
}
