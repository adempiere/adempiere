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
 * Contributor(s): Igor G. - progerpro@gmail.com
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
import org.adempiere.pipo.PoFiller;
import org.adempiere.pipo.exception.POSaveFailedException;
import org.compiere.model.X_AD_FieldGroup;
import org.compiere.util.Env;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import java.sql.ResultSet;

public class FieldGroupElementHandler extends AbstractElementHandler  implements IPackOutHandler{
	
	
	private List<Integer> processedFieldGroups = new ArrayList<Integer>();
	
	
	public void startElement(Properties ctx, Element element)
			throws SAXException {
		String elementValue = element.getElementValue();
		String Object_Status = null;

		Attributes atts = element.attributes;
		log.info(elementValue + " " + atts.getValue("Name"));

		String entitytype = atts.getValue("EntityType");
		String name = atts.getValue("Name");	

		if (isProcessElement(ctx, entitytype)) {
			
			int id = get_IDWithColumn(ctx, X_AD_FieldGroup.Table_Name, X_AD_FieldGroup.COLUMNNAME_Name, name);

			X_AD_FieldGroup fieldGroup = new X_AD_FieldGroup(ctx, id,
					getTrxName(ctx));
			if (id <= 0 && atts.getValue("AD_FieldGroup_ID") != null && Integer.parseInt(atts.getValue("AD_FieldGroup_ID")) <= PackOut.MAX_OFFICIAL_ID)
				fieldGroup.setAD_FieldGroup_ID(Integer.parseInt(atts.getValue("AD_FieldGroup_ID")));
			if (id > 0) {
				backupRecord(ctx, X_AD_FieldGroup.Table_Name, fieldGroup);
				Object_Status = "Update";
				if (processedFieldGroups.contains(id)) {
					element.skip = true;
					return;
				}
			} else {
				Object_Status = "New";
			}

			PoFiller pf = new PoFiller(fieldGroup, atts);
			
			pf.setBoolean("IsActive");
			
			pf.setString(X_AD_FieldGroup.COLUMNNAME_Name);
			pf.setString(X_AD_FieldGroup.COLUMNNAME_EntityType);

			
			if (fieldGroup.save(getTrxName(ctx)) == true) {
				record_log(ctx, 1, fieldGroup.getName(), "FieldGroup",
						fieldGroup.get_ID(), Object_Status,
						X_AD_FieldGroup.Table_Name, get_IDWithColumn(ctx, "AD_Table",
								"TableName", X_AD_FieldGroup.Table_Name));
				
				element.recordId = fieldGroup.getAD_FieldGroup_ID();
				
				processedFieldGroups.add(fieldGroup.getAD_FieldGroup_ID());
				
			} else {
				record_log(ctx, 0, fieldGroup.getName(), "FieldGroup",
						fieldGroup.get_ID(), Object_Status,
						X_AD_FieldGroup.Table_Name, get_IDWithColumn(ctx, "AD_Table",
								"TableName", X_AD_FieldGroup.Table_Name));
				throw new POSaveFailedException("Reference");
			}
		} else {
			element.skip = true;
		}
	}

	public void endElement(Properties ctx, Element element) throws SAXException {
	}

	protected void create(Properties ctx, TransformerHandler document)
			throws SAXException {
		
		
		int fieldGroup_id = Env.getContextAsInt(ctx,
				X_AD_FieldGroup.COLUMNNAME_AD_FieldGroup_ID);
		
		if (processedFieldGroups.contains(fieldGroup_id))
			return;
		
		processedFieldGroups.add(fieldGroup_id);
		
		X_AD_FieldGroup fieldGroup = new X_AD_FieldGroup(ctx, fieldGroup_id, null);
		
		AttributesImpl atts = new AttributesImpl();
		createAdElementBinding(atts, fieldGroup);
		
		document.startElement("", "", "fieldgroup", atts);
		
		PackOut packOut = (PackOut)ctx.get("PackOutProcess");
		
		try{
			new CommonTranslationHandler().packOut(packOut,null,null,document,null,fieldGroup.get_ID());
		}
		catch(Exception e)
		{
			log.info(e.toString());
		}
		document.endElement("", "", "fieldgroup");
	}

	
	private AttributesImpl createAdElementBinding(AttributesImpl atts,
			X_AD_FieldGroup fieldGroup) {
		
		AttributeFiller filler = new AttributeFiller(atts, fieldGroup);
		if (fieldGroup.getAD_FieldGroup_ID() <= PackOut.MAX_OFFICIAL_ID)
			filler.add(X_AD_FieldGroup.COLUMNNAME_AD_FieldGroup_ID);
		
		filler.add("IsActive");
		
		filler.add(X_AD_FieldGroup.COLUMNNAME_Name);
		filler.add(X_AD_FieldGroup.COLUMNNAME_EntityType);
		
		return atts;
	}
	
	public void packOut(PackOut packout, ResultSet header, ResultSet detail,TransformerHandler packOutDocument,TransformerHandler packageDocument,int recordId) throws Exception
	{
		if (recordId <= 0)
			recordId = detail.getInt(X_AD_FieldGroup.COLUMNNAME_AD_FieldGroup_ID);
		
		Env.setContext(packout.getCtx(), X_AD_FieldGroup.COLUMNNAME_AD_FieldGroup_ID, recordId);
			
		this.create(packout.getCtx(), packOutDocument);
		packout.getCtx().remove(X_AD_FieldGroup.COLUMNNAME_AD_FieldGroup_ID);
	}	
}