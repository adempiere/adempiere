/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2003-2012 e-Evolution Consultants. All Rights Reserved.      *
 * Copyright (C) 2003-2012 Victor Pérez Juárez 								  * 
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Contributor(s): Victor Pérez Juárez  (victor.perez@e-evolution.com)		  *
 * Sponsors: e-Evolution Consultants (http://www.e-evolution.com/)            *
 *****************************************************************************/
package org.adempiere.pipo.handler;

import java.util.List;
import java.util.Properties;

import javax.xml.transform.sax.TransformerHandler;

import org.adempiere.model.I_AD_View_Column;
import org.adempiere.model.I_AD_View_Definition;
import org.adempiere.model.MViewColumn;
import org.adempiere.model.MViewDefinition;
import org.adempiere.model.X_AD_View_Column;
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
public class ViewDefinitionElementHandler extends AbstractElementHandler {
	private ViewColumnElementHandler viewColumnHandler = new ViewColumnElementHandler();

	public void startElement(Properties ctx, Element element)
			throws SAXException {
		String elementValue = element.getElementValue();
		Attributes atts = element.attributes;
		log.info(elementValue + " " + atts.getValue("ADViewDefinitionNameID"));
		String entitytype = atts.getValue("EntityType");
		if (isProcessElement(ctx, entitytype)) {
			if (element.parent != null
					&& element.parent.getElementValue().equals("view")
					&& element.parent.defer) {
				element.defer = true;
				return;
			}

			String tableAlias = atts.getValue("ADViewDefinitionNameID");
			int tableid = get_IDWithColumn(ctx, "AD_Table", "TableName",
					atts.getValue("ADTableNameID"));
			if (tableid <= 0) {
				element.defer = true;
				return;
			}

			int viewid = 0;
			if (element.parent != null
					&& element.parent.getElementValue().equals("view")
					&& element.parent.recordId > 0) {
				viewid = element.parent.recordId;
			} else {
				viewid = get_ID(ctx, "AD_View", atts.getValue("ADViewNameID"));
				if (element.parent != null
						&& element.parent.getElementValue().equals("view")
						&& viewid > 0) {
					element.parent.recordId = viewid;
				}
			}
			if (viewid <= 0) {
				element.defer = true;
				return;
			}

			StringBuffer sqlB = new StringBuffer(
					"SELECT AD_View_Definition_ID FROM AD_View_Definition WHERE AD_View_ID = "
							+ viewid + " and TableAlias = '" + tableAlias + "'"
							+ " and AD_Table_ID = ?");

			int id = DB.getSQLValue(getTrxName(ctx), sqlB.toString(), tableid);
			MViewDefinition m_View_Definition = new MViewDefinition(ctx, id,
					getTrxName(ctx));
			if (id <= 0
					&& atts.getValue("AD_View_Definition_ID") != null
					&& Integer.parseInt(atts.getValue("AD_View_Definition_ID")) <= PackOut.MAX_OFFICIAL_ID)
				m_View_Definition.setAD_View_Definition_ID(Integer
						.parseInt(atts.getValue("AD_View_Definition_ID")));
			int AD_Backup_ID = -1;
			String Object_Status = null;
			if (id > 0) {
				AD_Backup_ID = copyRecord(ctx, "AD_View_Definition",
						m_View_Definition);
				Object_Status = "Update";
			} else {
				Object_Status = "New";
				AD_Backup_ID = 0;
			}
			sqlB = null;
			m_View_Definition.setTableAlias(tableAlias);
			String name;
			if (getStringValue(atts, "ADTableNameID") != null) {
				name = atts.getValue("ADTableNameID");
				id = get_IDWithColumn(ctx, "AD_Table", "TableName", name);
				m_View_Definition.setAD_Table_ID(id);
			}

			m_View_Definition.setAD_View_ID(viewid);
			m_View_Definition
					.setIsActive(atts.getValue("isActive") != null ? Boolean
							.valueOf(atts.getValue("isActive")).booleanValue()
							: true);
			m_View_Definition.setProcessing(false);
			m_View_Definition
					.setSeqNo(Integer.parseInt(atts.getValue("SeqNo")));
			if (getStringValue(atts, "JoinClause") != null) {
				m_View_Definition.setJoinClause(atts.getValue("JoinClause"));
			}

			if (m_View_Definition.save(getTrxName(ctx)) == true) {
				record_log(
						ctx,
						1,
						m_View_Definition.getTableAlias(),
						"ViewDefinition",
						m_View_Definition.get_ID(),
						AD_Backup_ID,
						Object_Status,
						"AD_View_Definition",
						get_IDWithColumn(ctx, "AD_Table", "TableName",
								"AD_View_Definition"));
				element.recordId = m_View_Definition.getAD_View_Definition_ID();
			} else {
				record_log(
						ctx,
						0,
						m_View_Definition.getTableAlias(),
						"ViewDefinition",
						m_View_Definition.get_ID(),
						AD_Backup_ID,
						Object_Status,
						"AD_View_Definition",
						get_IDWithColumn(ctx, "AD_Table", "TableName",
								"AD_View_Definition"));
				throw new POSaveFailedException("ViewDefinition");
			}
		} else {
			element.skip = true;
		}

	}

	public void endElement(Properties ctx, Element element) throws SAXException {
	}

	public void create(Properties ctx, TransformerHandler document)
			throws SAXException {
		PackOut packOut = (PackOut) ctx.get("PackOutProcess");
		int AD_View_Definition_ID = Env.getContextAsInt(ctx,
				X_AD_View_Definition.COLUMNNAME_AD_View_Definition_ID);
		MViewDefinition m_View_Definition = new MViewDefinition(ctx,
				AD_View_Definition_ID, getTrxName(ctx));
		AttributesImpl atts = new AttributesImpl();
		createViewDefinitionBinding(atts, m_View_Definition);
		document.startElement("", "", "viewdefinition", atts);

		// View Columns tags.
		StringBuilder whereClause = new StringBuilder(
				I_AD_View_Definition.COLUMNNAME_AD_View_Definition_ID)
				.append("=?");
		List<MViewColumn> viewColumns = new Query(ctx,
				I_AD_View_Column.Table_Name, whereClause.toString(),
				getTrxName(ctx)).setParameters(m_View_Definition.get_ID())
				.list();

		for (MViewColumn vc : viewColumns) {
			createViewColumn(ctx, document, vc.getAD_View_Column_ID());
		}
		document.endElement("", "", "viewdefinition");

	}

	private void createViewColumn(Properties ctx, TransformerHandler document,
			int AD_View_Column_ID) throws SAXException {
		Env.setContext(ctx, X_AD_View_Column.COLUMNNAME_AD_View_Column_ID,
				AD_View_Column_ID);
		viewColumnHandler.create(ctx, document);
		ctx.remove(X_AD_View_Column.COLUMNNAME_AD_View_Column_ID);
	}

	private AttributesImpl createViewDefinitionBinding(AttributesImpl atts,
			X_AD_View_Definition m_View_Definition) {
		String sql = null;
		String name = null;
		atts.clear();
		if (m_View_Definition.getAD_View_Definition_ID() <= PackOut.MAX_OFFICIAL_ID)
			atts.addAttribute("", "", "AD_View_Definition_ID", "CDATA", Integer
					.toString(m_View_Definition.getAD_View_Definition_ID()));
		atts.addAttribute("", "", "Name", "CDATA", (m_View_Definition
				.getTableAlias() != null ? m_View_Definition.getTableAlias()
				: ""));

		if (m_View_Definition.getAD_View_Definition_ID() > 0) {
			sql = "SELECT TableAlias FROM AD_View_Definition WHERE AD_View_Definition_ID=?";
			name = DB.getSQLValueString(null, sql,
					m_View_Definition.getAD_View_Definition_ID());
			atts.addAttribute("", "", "ADViewDefinitionNameID", "CDATA", name);
		} else
			atts.addAttribute("", "", "ADViewDefinitionNameID", "CDATA", "");

		sql = "SELECT TableName FROM AD_Table WHERE AD_Table_ID=?";
		name = DB.getSQLValueString(null, sql,
				m_View_Definition.getAD_Table_ID());
		atts.addAttribute("", "", "ADTableNameID", "CDATA", name);
		sql = "SELECT Name FROM AD_View WHERE AD_View_ID=?";
		name = DB.getSQLValueString(null, sql,
				m_View_Definition.getAD_View_ID());
		atts.addAttribute("", "", "ADViewNameID", "CDATA", name);

		atts.addAttribute("", "", "TableAlias", "CDATA", (m_View_Definition
				.getTableAlias() != null ? m_View_Definition.getTableAlias()
				: ""));
		atts.addAttribute("", "", "isActive", "CDATA",
				(m_View_Definition.isActive() == true ? "true" : "false"));
		atts.addAttribute("", "", "JoinClause", "CDATA", (m_View_Definition
				.getJoinClause() != null ? m_View_Definition.getJoinClause()
				: ""));
		atts.addAttribute("", "", "isProcessing", "CDATA",
				(m_View_Definition.isProcessing() == true ? "true" : "false"));
		atts.addAttribute(
				"",
				"",
				"SeqNo",
				"CDATA",
				(m_View_Definition.getSeqNo() >= 0 ? ""
						+ m_View_Definition.getSeqNo() : "0"));
		return atts;
	}

}
