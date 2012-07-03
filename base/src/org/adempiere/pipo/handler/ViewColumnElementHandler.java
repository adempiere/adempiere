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

import java.util.Properties;

import javax.xml.transform.sax.TransformerHandler;

import org.adempiere.model.MViewColumn;
import org.adempiere.model.X_AD_View_Column;
import org.adempiere.pipo.AbstractElementHandler;
import org.adempiere.pipo.Element;
import org.adempiere.pipo.PackIn;
import org.adempiere.pipo.PackOut;
import org.adempiere.pipo.exception.POSaveFailedException;
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
public class ViewColumnElementHandler extends AbstractElementHandler {
	public void startElement(Properties ctx, Element element)
			throws SAXException {
		PackIn packIn = (PackIn) ctx.get("PackInProcess");
		String elementValue = element.getElementValue();
		Attributes atts = element.attributes;
		log.info(elementValue + " " + atts.getValue("Name"));
		String entitytype = atts.getValue("EntityType");
		if (isProcessElement(ctx, entitytype)) {
			if (element.parent != null
					&& element.parent.getElementValue()
							.equals("viewdefinition") && element.parent.defer) {
				element.defer = true;
				return;
			}
			String name = atts.getValue("Name");
			String viewdenitionname = atts.getValue("ADViewDefinitionNameID");
			String colname = atts.getValue("ADColumnNameID");
			String tableName = atts.getValue("ADTableNameID");
			int tableid = packIn.getTableId(tableName);
			if (tableid <= 0) {
				tableid = get_IDWithColumn(ctx, "AD_Table", "TableName",
						tableName);
				if (tableid > 0)
					packIn.addTable(tableName, tableid);
			}
			if (tableid <= 0) {
				element.defer = true;
				return;
			}
			int viewid = get_ID(ctx, "AD_View", atts.getValue("ADViewNameID"));
			if (viewid <= 0) {
				element.defer = true;
				return;
			}
			int columnid = packIn.getColumnId(tableName, colname);
			if (columnid <= 0) {
				columnid = get_IDWithMasterAndColumn(ctx, "AD_Column",
						"ColumnName", colname, "AD_Table", tableid);
				if (columnid > 0)
					packIn.addColumn(tableName, colname, columnid);
			}
			if (columnid <= 0) {
				element.defer = true;
				return;
			}
			int viewdefinitionid = 0;
			if (element.parent != null
					&& element.parent.getElementValue()
							.equals("viewdefinition")
					&& element.parent.recordId > 0) {
				viewdefinitionid = element.parent.recordId;
			} else {
				StringBuffer sqlB = new StringBuffer(
						"select AD_View_Definition_ID from AD_View_Definition where AD_View_ID = "
								+ viewid).append(
						" and TableAlias = '" + viewdenitionname + "'").append(
						" and AD_Table_ID = ?");
				viewdefinitionid = DB.getSQLValue(getTrxName(ctx),
						sqlB.toString(), tableid);
				if (element.parent != null
						&& element.parent.getElementValue().equals(
								"viewdefinition") && viewdefinitionid > 0) {
					element.parent.recordId = viewdefinitionid;
				}
			}
			if (viewdefinitionid > 0) {
				StringBuffer sqlB = new StringBuffer(
						"select AD_View_Column_ID from AD_View_Column where AD_Column_ID =? ")
						.append(" and AD_View_Definition_ID = ?");
				int id = DB.getSQLValue(getTrxName(ctx), sqlB.toString(),
						columnid, viewdefinitionid);
				final MViewColumn m_ColumnView = new MViewColumn(ctx, id,
						getTrxName(ctx));
				if (id <= 0
						&& atts.getValue("AD_View_Column_ID") != null
						&& Integer.parseInt(atts.getValue("AD_View_Column_ID")) <= PackOut.MAX_OFFICIAL_ID)
					m_ColumnView.setAD_View_Column_ID(Integer.parseInt(atts
							.getValue("AD_View_Column_ID")));
				int AD_Backup_ID = -1;
				String Object_Status = null;
				if (id > 0) {
					AD_Backup_ID = copyRecord(ctx, "AD_View_Column_ID",
							m_ColumnView);
					Object_Status = "Update";
				} else {
					Object_Status = "New";
					AD_Backup_ID = 0;
				}

				m_ColumnView.setName(atts.getValue("Name"));
				m_ColumnView.setAD_View_ID(viewid);
				m_ColumnView.setAD_Column_ID(columnid);
				m_ColumnView.setAD_View_Definition_ID(viewdefinitionid);
				m_ColumnView.setEntityType(atts.getValue("EntityType"));
				m_ColumnView.setColumnSQL(atts.getValue("ColumnSQL"));
				m_ColumnView.setColumnName(atts.getValue("ColumnName"));

				// m_ColumnView.setIsReadOnly(Boolean.valueOf(
				// atts.getValue("isReadOnly")).booleanValue());

				m_ColumnView
						.setDescription(getStringValue(atts, "Description"));
				m_ColumnView.setHelp(getStringValue(atts, "Help"));
				m_ColumnView
						.setIsActive(atts.getValue("isActive") != null ? Boolean
								.valueOf(atts.getValue("isActive"))
								.booleanValue() : true);

				if (m_ColumnView.save(getTrxName(ctx)) == true) {
					record_log(
							ctx,
							1,
							m_ColumnView.getName(),
							"ViewColumn",
							m_ColumnView.get_ID(),
							AD_Backup_ID,
							Object_Status,
							"ViewColumn",
							get_IDWithColumn(ctx, "AD_Table", "TableName",
									"AD_View_Column"));
					element.recordId = m_ColumnView.getAD_View_Column_ID();
				} else {
					record_log(
							ctx,
							0,
							m_ColumnView.getName(),
							"ViewColumn",
							m_ColumnView.get_ID(),
							AD_Backup_ID,
							Object_Status,
							"AD_View_Column",
							get_IDWithColumn(ctx, "AD_Table", "TableName",
									"AD_View_Column"));
					throw new POSaveFailedException(
							"Failed to save view column definition.");
				}
			}
		} else {
			element.skip = true;
		}
	}

	public void endElement(Properties ctx, Element element) throws SAXException {
	}

	public void create(Properties ctx, TransformerHandler document)
			throws SAXException {
		int AD_View_Column_ID = Env.getContextAsInt(ctx,
				X_AD_View_Column.COLUMNNAME_AD_View_Column_ID);
		MViewColumn m_ColumnView = new MViewColumn(ctx, AD_View_Column_ID, null);
		AttributesImpl atts = new AttributesImpl();
		createViewColumnBinding(atts, m_ColumnView);

		PackOut packOut = (PackOut) ctx.get("PackOutProcess");
		document.startElement("", "", "viewcolumn", atts);
		document.endElement("", "", "viewcolumn");
	}

	private AttributesImpl createViewColumnBinding(AttributesImpl atts,
			X_AD_View_Column m_ColumnView) {
		String sql = null;
		String name = null;
		atts.clear();
		if (m_ColumnView.getAD_View_Column_ID() <= PackOut.MAX_OFFICIAL_ID)
			atts.addAttribute("", "", "AD_View_Column_ID", "CDATA",
					Integer.toString(m_ColumnView.getAD_View_Column_ID()));
		if (m_ColumnView.getAD_Column_ID() > 0) {
			sql = "SELECT ColumnName FROM AD_Column WHERE AD_Column_ID=?";
			name = DB.getSQLValueString(null, sql,
					m_ColumnView.getAD_Column_ID());
			atts.addAttribute("", "", "ADColumnNameID", "CDATA", name);
		} else
			atts.addAttribute("", "", "ADColumnNameID", "CDATA", "");

		if (m_ColumnView.getAD_Column_ID() > 0) {
			sql = "SELECT AD_Table_ID FROM AD_Column WHERE AD_Column_ID=?";
			int idTable = DB.getSQLValue(null, sql,
					m_ColumnView.getAD_Column_ID());
			sql = "SELECT TableName FROM AD_Table WHERE AD_Table_ID=?";
			name = DB.getSQLValueString(null, sql, idTable);
			atts.addAttribute("", "", "ADTableNameID", "CDATA", name);
		} else
			atts.addAttribute("", "", "ADTableNameID", "CDATA", "");

		if (m_ColumnView.getAD_View_Column_ID() > 0) {
			sql = "SELECT ColumnName FROM AD_View_Column WHERE AD_View_Column_ID=?";
			name = DB.getSQLValueString(null, sql,
					m_ColumnView.getAD_View_Column_ID());
			atts.addAttribute("", "", "ADViewColumnNameID", "CDATA", name);
		} else
			atts.addAttribute("", "", "ADViewColumnNameID", "CDATA", "");

		if (m_ColumnView.getAD_View_Definition_ID() > 0) {
			sql = "SELECT TableAlias FROM AD_View_Definition WHERE AD_View_Definition_ID=?";
			name = DB.getSQLValueString(null, sql,
					m_ColumnView.getAD_View_Definition_ID());
			atts.addAttribute("", "", "ADViewDefinitionNameID", "CDATA", name);
			sql = "SELECT AD_View_ID FROM AD_View_Definition WHERE AD_View_Definition_ID=?";
			int viewid = DB.getSQLValue(null, sql,
					m_ColumnView.getAD_View_Definition_ID());
			sql = "SELECT Name FROM AD_View WHERE AD_View_ID=?";
			name = DB.getSQLValueString(null, sql, viewid);
			atts.addAttribute("", "", "ADViewNameID", "CDATA", name);
		} else
			atts.addAttribute("", "", "ADViewDefinitionNameID", "CDATA", "");

		atts.addAttribute("", "", "EntityType", "CDATA", (m_ColumnView
				.getEntityType() != null ? m_ColumnView.getEntityType() : ""));
		atts.addAttribute("", "", "Name", "CDATA",
				(m_ColumnView.getName() != null ? m_ColumnView.getName() : ""));
		atts.addAttribute("", "", "Description", "CDATA", (m_ColumnView
				.getDescription() != null ? m_ColumnView.getDescription() : ""));

		atts.addAttribute("", "", "ColumnName", "CDATA", (m_ColumnView
				.getColumnName() != null ? m_ColumnView.getColumnName() : ""));
		atts.addAttribute("", "", "ColumnSQL", "CDATA", (m_ColumnView
				.getColumnSQL() != null ? m_ColumnView.getColumnSQL() : ""));

		atts.addAttribute("", "", "isActive", "CDATA",
				(m_ColumnView.isActive() == true ? "true" : "false"));
		// atts.addAttribute("", "", "isReadOnly", "CDATA",
		// (m_ColumnView.isReadOnly() == true ? "true" : "false"));
		atts.addAttribute("", "", "Help", "CDATA",
				(m_ColumnView.getHelp() != null ? m_ColumnView.getHelp() : ""));

		return atts;
	}
}
