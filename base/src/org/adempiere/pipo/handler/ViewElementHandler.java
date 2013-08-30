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

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.transform.sax.TransformerHandler;

import org.adempiere.model.I_AD_View;
import org.adempiere.model.I_AD_View_Definition;
import org.adempiere.model.MView;
import org.adempiere.model.MViewDefinition;
import org.adempiere.model.X_AD_View;
import org.adempiere.model.X_AD_View_Definition;
import org.adempiere.pipo.AbstractElementHandler;
import org.adempiere.pipo.Element;
import org.adempiere.pipo.PackOut;
import org.adempiere.pipo.exception.POSaveFailedException;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * 
 * @author victor.perez@e-evoluton.com, www.e-evolution.com
 * 
 */
public class ViewElementHandler extends AbstractElementHandler {

	private ViewDefinitionElementHandler viewDefinitionHandler = new ViewDefinitionElementHandler();

	private List<Integer> views = new ArrayList<Integer>();

	public void startElement(Properties ctx, Element element)
			throws SAXException {
		// Check namespace.
		String elementValue = element.getElementValue();
		Attributes atts = element.attributes;
		log.info(elementValue + " " + atts.getValue("Name"));
		String entitytype = atts.getValue("EntityType");
		if (isProcessElement(ctx, entitytype)) {
			String name = atts.getValue("Name");
			int id = get_ID(ctx, "AD_View", name);
			if (id > 0 && views.contains(id)) {
				return;
			}
			MView m_View = new MView(ctx, id, getTrxName(ctx));
			if (id <= 0
					&& atts.getValue("AD_View_ID") != null
					&& Integer.parseInt(atts.getValue("AD_View_ID")) <= PackOut.MAX_OFFICIAL_ID)
				m_View.setAD_View_ID(Integer.parseInt(atts
						.getValue("AD_View_ID")));
			String Object_Status = null;
			int AD_Backup_ID = -1;
			if (id > 0) {
				AD_Backup_ID = copyRecord(ctx, "AD_View", m_View);
				Object_Status = "Update";
			} else {
				Object_Status = "New";
				AD_Backup_ID = 0;
			}

			m_View.setValue(atts.getValue("Value"));
			m_View.setName(atts.getValue("Name"));
			m_View.setDescription(getStringValue(atts, "Description"));
			m_View.setHelp(getStringValue(atts, "Help"));
			m_View.setIsActive(atts.getValue("isActive") != null ? Boolean
					.valueOf(atts.getValue("isActive")).booleanValue() : true);
			m_View.setEntityType(atts.getValue("EntityType"));

			if (m_View.save(getTrxName(ctx)) == true) {
				record_log(
						ctx,
						1,
						m_View.getName(),
						"View",
						m_View.get_ID(),
						AD_Backup_ID,
						Object_Status,
						"AD_View",
						get_IDWithColumn(ctx, "AD_Table", "TableName",
								"AD_View"));
				element.recordId = m_View.getAD_View_ID();
				views.add(m_View.getAD_View_ID());
			} else {
				record_log(
						ctx,
						0,
						m_View.getName(),
						"View",
						m_View.get_ID(),
						AD_Backup_ID,
						Object_Status,
						"AD_View",
						get_IDWithColumn(ctx, "AD_Table", "TableName",
								"AD_View"));
				throw new POSaveFailedException("View");
			}
		} else {
			element.skip = true;
		}
	}

	public void endElement(Properties ctx, Element element) throws SAXException {
	}

	public void create(Properties ctx, TransformerHandler document)
			throws SAXException {
		int AD_View_ID = Env.getContextAsInt(ctx, "AD_View_ID");
		PackOut packOut = (PackOut) ctx.get("PackOutProcess");

		X_AD_View m_View = new X_AD_View(ctx, AD_View_ID, null);
		AttributesImpl atts = new AttributesImpl();
		createViewBinding(atts, m_View);
		document.startElement("", "", "view", atts);
		// Tab Tag
		StringBuilder whereClause = new StringBuilder(
				I_AD_View.COLUMNNAME_AD_View_ID).append("=?");
		List<MViewDefinition> viewDefinitions = new Query(ctx,
				I_AD_View_Definition.Table_Name, whereClause.toString(),
				getTrxName(ctx))
				.setParameters(m_View.getAD_View_ID())
				.setOrderBy(
						X_AD_View_Definition.COLUMNNAME_SeqNo
								+ ","
								+ X_AD_View_Definition.COLUMNNAME_AD_View_Definition_ID)
				.list();

		for (MViewDefinition vd : viewDefinitions) {
			//Is not export table definition because maybe cause changes in tables
			//So that of tables should are created before to import Browser
			//packOut.createTable(vd.getAD_Table_ID(), document);
			createViewDefinition(ctx, document, vd.getAD_View_Definition_ID());
		}
		// Loop tags.
		document.endElement("", "", "view");

	}

	private void createViewDefinition(Properties ctx,
			TransformerHandler document, int AD_View_Definition_ID)
			throws SAXException {
		Env.setContext(ctx,
				X_AD_View_Definition.COLUMNNAME_AD_View_Definition_ID,
				AD_View_Definition_ID);
		viewDefinitionHandler.create(ctx, document);
		ctx.remove(X_AD_View_Definition.COLUMNNAME_AD_View_Definition_ID);
	}

	private AttributesImpl createViewBinding(AttributesImpl atts,
			X_AD_View m_View) {
		atts.clear();
		if (m_View.getAD_View_ID() <= PackOut.MAX_OFFICIAL_ID)
			atts.addAttribute("", "", "AD_View_ID", "CDATA",
					Integer.toString(m_View.getAD_View_ID()));
		String sql = "SELECT Name FROM AD_View WHERE AD_View_ID=?";
		String name = DB.getSQLValueString(null, sql, m_View.getAD_View_ID());
		atts.addAttribute("", "", "ADViewNameID", "CDATA", name);
		atts.addAttribute("", "", "Value", "CDATA",
				(m_View.getValue() != null ? m_View.getValue() : ""));
		atts.addAttribute("", "", "Name", "CDATA",
				(m_View.getName() != null ? m_View.getName() : ""));
		atts.addAttribute(
				"",
				"",
				"Description",
				"CDATA",
				(m_View.getDescription() != null ? m_View.getDescription() : ""));
		atts.addAttribute("", "", "Help", "CDATA",
				(m_View.getHelp() != null ? m_View.getHelp() : ""));
		atts.addAttribute("", "", "EntityType", "CDATA",
				(m_View.getEntityType() != null ? m_View.getEntityType() : ""));
		atts.addAttribute("", "", "isActive", "CDATA",
				(m_View.isActive() == true ? "true" : "false"));

		return atts;
	}
}
