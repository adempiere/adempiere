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

import org.adempiere.model.I_AD_Browse_Field;
import org.adempiere.model.MBrowse;
import org.adempiere.model.MBrowseField;
import org.adempiere.model.X_AD_Browse;
import org.adempiere.model.X_AD_Browse_Field;
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
public class BrowseElementHandler extends AbstractElementHandler {

	private List<Integer> browses = new ArrayList<Integer>();
	private BrowseFieldElementHandler browseFieldHandler = new BrowseFieldElementHandler();

	public void startElement(Properties ctx, Element element)
			throws SAXException {
		// Check namespace.
		String elementValue = element.getElementValue();
		Attributes atts = element.attributes;
		log.info(elementValue + " " + atts.getValue("Name"));
		String entitytype = atts.getValue("EntityType");
		if (isProcessElement(ctx, entitytype)) {
			String name = atts.getValue("Name");
			int id = get_ID(ctx, "AD_Browse", name);
			if (id > 0 && browses.contains(id)) {
				return;
			}

			MBrowse m_Browse = new MBrowse(ctx, id, getTrxName(ctx));
			if (id <= 0
					&& atts.getValue("AD_Browse_ID") != null
					&& Integer.parseInt(atts.getValue("AD_Browse_ID")) <= PackOut.MAX_OFFICIAL_ID)
				m_Browse.setAD_Browse_ID(Integer.parseInt(atts
						.getValue("AD_Browse_ID")));

			String Object_Status = null;
			int AD_Backup_ID = -1;
			if (id > 0) {
				AD_Backup_ID = copyRecord(ctx, "AD_Browse", m_Browse);
				Object_Status = "Update";
			} else {
				Object_Status = "New";
				AD_Backup_ID = 0;
			}

			if (getStringValue(atts, "ADViewNameID") != null) {
				name = atts.getValue("ADViewNameID");
				id = get_IDWithColumn(ctx, "AD_View", "Name", name);
				if (id <= 0) {
					element.defer = true;
					element.unresolved = "AD_View: " + name;
					return;
				}
				m_Browse.setAD_View_ID(id);
			}

			if (getStringValue(atts, "ADProcessNameID") != null) {

				name = atts.getValue("ADProcessNameID");
				if (name != null && name.trim().length() > 0) {
					id = get_IDWithColumn(ctx, "AD_Process", "Name", name);
					if (id <= 0) {
						element.defer = true;
						element.unresolved = "AD_Process: " + name;
						return;
					}
					m_Browse.setAD_Process_ID(id);
				}
			}

			m_Browse.setValue(atts.getValue("Value"));
			m_Browse.setName(atts.getValue("Name"));
			m_Browse.setDescription(getStringValue(atts, "Description"));
			m_Browse.setHelp(getStringValue(atts, "Help"));
			m_Browse.setIsActive(atts.getValue("isActive") != null ? Boolean
					.valueOf(atts.getValue("isActive")).booleanValue() : true);
			m_Browse.setEntityType(atts.getValue("EntityType"));
			m_Browse.setWhereClause(atts.getValue("WhereClause"));
			m_Browse.setAccessLevel(atts.getValue("AccessLevel"));

			if (m_Browse.save(getTrxName(ctx)) == true) {
				record_log(
						ctx,
						1,
						m_Browse.getName(),
						"Browse",
						m_Browse.get_ID(),
						AD_Backup_ID,
						Object_Status,
						"AD_Browse",
						get_IDWithColumn(ctx, "AD_Table", "TableName",
								"AD_Browse"));
				element.recordId = m_Browse.getAD_Browse_ID();
				browses.add(m_Browse.getAD_Browse_ID());
			} else {
				record_log(
						ctx,
						0,
						m_Browse.getName(),
						"Browse",
						m_Browse.get_ID(),
						AD_Backup_ID,
						Object_Status,
						"AD_Browse",
						get_IDWithColumn(ctx, "AD_Table", "TableName",
								"AD_Browse"));
				throw new POSaveFailedException("Browse");
			}
		} else {
			element.skip = true;
		}
	}

	public void create(Properties ctx, TransformerHandler document)
			throws SAXException {
		int AD_Browse_ID = Env.getContextAsInt(ctx, "AD_Browse_ID");
		PackOut packOut = (PackOut) ctx.get("PackOutProcess");

		MBrowse m_Browse = new MBrowse(ctx, AD_Browse_ID, null);
		AttributesImpl atts = new AttributesImpl();

		packOut.createView(m_Browse.getAD_View_ID(), document);
		packOut.createProcess(m_Browse.getAD_Process_ID(), document);
		createBrowseBinding(atts, m_Browse);
		document.startElement("", "", "browse", atts);

		// Tab Tag
		StringBuilder whereClause = new StringBuilder(
				I_AD_Browse_Field.COLUMNNAME_AD_Browse_ID).append("=?");
		List<MBrowseField> browseFields = new Query(ctx,
				I_AD_Browse_Field.Table_Name, whereClause.toString(),
				getTrxName(ctx)).setParameters(m_Browse.getAD_Browse_ID())
				.list();

		for (MBrowseField bf : browseFields) {
			createBrowseField(ctx, document, bf.getAD_Browse_Field_ID());
		}
		// Loop tags.
		document.endElement("", "", "browse");

	}

	private void createBrowseField(Properties ctx, TransformerHandler document,
			int AD_Browse_Field_ID) throws SAXException {
		Env.setContext(ctx, X_AD_Browse_Field.COLUMNNAME_AD_Browse_Field_ID,
				AD_Browse_Field_ID);
		browseFieldHandler.create(ctx, document);
		ctx.remove(X_AD_Browse_Field.COLUMNNAME_AD_Browse_Field_ID);
	}

	private AttributesImpl createBrowseBinding(AttributesImpl atts,
			X_AD_Browse m_Browse) {
		atts.clear();
		if (m_Browse.getAD_Browse_ID() <= PackOut.MAX_OFFICIAL_ID)
			atts.addAttribute("", "", "AD_Browse_ID", "CDATA",
					Integer.toString(m_Browse.getAD_Browse_ID()));
		String sql = "SELECT Name FROM AD_Browse WHERE AD_Browse_ID=?";
		String name = DB.getSQLValueString(null, sql,
				m_Browse.getAD_Browse_ID());
		atts.addAttribute("", "", "ADBrowseNameID", "CDATA", name);
		atts.addAttribute("", "", "Value", "CDATA",
				(m_Browse.getValue() != null ? m_Browse.getValue() : ""));
		atts.addAttribute("", "", "Name", "CDATA",
				(m_Browse.getName() != null ? m_Browse.getName() : ""));
		atts.addAttribute("", "", "Description", "CDATA", (m_Browse
				.getDescription() != null ? m_Browse.getDescription() : ""));
		atts.addAttribute("", "", "Help", "CDATA",
				(m_Browse.getHelp() != null ? m_Browse.getHelp() : ""));
		atts.addAttribute("", "", "EntityType", "CDATA", (m_Browse
				.getEntityType() != null ? m_Browse.getEntityType() : ""));
		atts.addAttribute("", "", "isActive", "CDATA",
				(m_Browse.isActive() == true ? "true" : "false"));
		atts.addAttribute("", "", "WhereClause", "CDATA", (m_Browse
				.getWhereClause() != null ? m_Browse.getWhereClause() : ""));
		atts.addAttribute("", "", "isBetaFunctionality", "CDATA",
				(m_Browse.isBetaFunctionality() == true ? "true" : "false"));
		atts.addAttribute("", "", "AccessLevel", "CDATA", (m_Browse
				.getAccessLevel() != null ? m_Browse.getAccessLevel() : ""));

		if (m_Browse.getAD_Process_ID() > 0) {
			sql = "SELECT Name FROM AD_Process WHERE AD_Process_ID=?";
			name = DB.getSQLValueString(null, sql, m_Browse.getAD_Process_ID());
			atts.addAttribute("", "", "ADProcessNameID", "CDATA", name);
		} else
			atts.addAttribute("", "", "ADProcessNameID", "CDATA", "");

		if (m_Browse.getAD_View_ID() > 0) {
			sql = "SELECT Name FROM AD_View WHERE AD_View_ID=?";
			name = DB.getSQLValueString(null, sql, m_Browse.getAD_View_ID());
			atts.addAttribute("", "", "ADViewNameID", "CDATA", name);
		} else
			atts.addAttribute("", "", "ADViewNameID", "CDATA", "");

		return atts;
	}

	@Override
	public void endElement(Properties ctx, Element element) throws SAXException {
	}
}
