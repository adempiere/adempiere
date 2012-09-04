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
 * Copyright (C) 2003-2011 e-Evolution Consultants. All Rights Reserved.      *
 * Copyright (C) 2003-2011 Victor Pérez Juárez 								  * 
 * Contributor(s): Low Heng Sin hengsin@avantz.com                            *
 *                 Teo Sarca teo.sarca@gmail.com                              *
 *                 Victor Perez  victor.perez@e-evoluton.com				  *
 *****************************************************************************/
package org.adempiere.pipo.handler;

import java.util.Properties;

import javax.xml.transform.sax.TransformerHandler;

import org.adempiere.model.MBrowseField;
import org.adempiere.model.X_AD_Browse_Field;
import org.adempiere.pipo.AbstractElementHandler;
import org.adempiere.pipo.Element;
import org.adempiere.pipo.PackIn;
import org.adempiere.pipo.PackOut;
import org.adempiere.pipo.exception.POSaveFailedException;
import org.compiere.model.X_AD_Element;
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
public class BrowseFieldElementHandler extends AbstractElementHandler {
	public void startElement(Properties ctx, Element element)
			throws SAXException {

		PackIn packIn = (PackIn) ctx.get("PackInProcess");
		String elementValue = element.getElementValue();
		Attributes atts = element.attributes;
		log.info(elementValue + " " + atts.getValue("Name"));
		String entitytype = atts.getValue("EntityType");
		if (isProcessElement(ctx, entitytype)) {
			if (element.parent != null
					&& element.parent.getElementValue().equals("browse")
					&& element.parent.defer) {
				element.defer = true;
				return;
			}
			String name = atts.getValue("Name");
			String browsename = atts.getValue("ADBrowseNameID");
			String colviewname = atts.getValue("ADViewColumnNameID");
			String viewName = atts.getValue("ADViewNameID");

			int viewid = get_IDWithColumn(ctx, "AD_View", "Name", viewName);
			if (viewid <= 0) {
				element.defer = true;
				return;
			}

			int viewcolumnid = get_IDWithMasterAndColumn(ctx, "AD_View_Column",
					"ColumnName", colviewname, "AD_View", viewid);
			if (viewcolumnid <= 0) {
				element.defer = true;
				return;
			}

			int browseid = 0;
			if (element.parent != null
					&& element.parent.getElementValue().equals("browse")
					&& element.parent.recordId > 0) {
				browseid = element.parent.recordId;
			} else {
				StringBuffer sqlB = new StringBuffer(
						"SELECT AD_Browse_ID from AD_Browse WHERE AD_Browse_ID = "
								+ browseid).append(
						" and Name = '" + browsename + "'").append(
						" and AD_View_ID = ?");
				browseid = DB.getSQLValue(getTrxName(ctx), sqlB.toString(),
						viewid);
				if (element.parent != null
						&& element.parent.getElementValue().equals("browse")
						&& browseid > 0) {
					element.parent.recordId = browseid;
				}
			}
			if (browseid > 0) {
				StringBuffer sqlB = new StringBuffer(
						"SELECT AD_Browse_Field_ID from AD_Browse_Field where AD_View_Column_ID = ")
						.append(viewcolumnid).append(" and AD_Browse_ID = ?");
				int id = DB.getSQLValue(getTrxName(ctx), sqlB.toString(),
						browseid);
				final MBrowseField m_BrowseField = new MBrowseField(ctx, id,
						getTrxName(ctx));
				if (id <= 0
						&& atts.getValue("AD_Browse_Field_ID") != null
						&& Integer
								.parseInt(atts.getValue("AD_Browse_Field_ID")) <= PackOut.MAX_OFFICIAL_ID)
					m_BrowseField.setAD_Browse_Field_ID(Integer.parseInt(atts
							.getValue("AD_Browse_Field_ID")));
				int AD_Backup_ID = -1;
				String Object_Status = null;
				if (id > 0) {
					AD_Backup_ID = copyRecord(ctx, "AD_Browse_Field",
							m_BrowseField);
					Object_Status = "Update";
				} else {
					Object_Status = "New";
					AD_Backup_ID = 0;
				}

				m_BrowseField.setName(atts.getValue("Name"));
				m_BrowseField.setAD_View_Column_ID(viewcolumnid);
				m_BrowseField.setAD_Browse_ID(browseid);
				m_BrowseField.setEntityType(atts.getValue("EntityType"));
				m_BrowseField.setIsCentrallyMaintained(Boolean.valueOf(
						atts.getValue("isCentrallyMaintained")).booleanValue());
				m_BrowseField.setIsMandatory(Boolean.valueOf(
						atts.getValue("isMandatory")).booleanValue());
				m_BrowseField.setIsDisplayed(Boolean.valueOf(
						atts.getValue("Displayed")).booleanValue());
				 m_BrowseField.setIsReadOnly(Boolean.valueOf(
				 atts.getValue("isReadOnly")).booleanValue());
				
				m_BrowseField.setDefaultValue(atts.getValue("DefaultValue"));
				m_BrowseField.setDefaultValue2(atts.getValue("DefaultValue2"));
				m_BrowseField.setReadOnlyLogic(atts.getValue("ReadOnlyLogic"));
				m_BrowseField.setDisplayLogic(atts.getValue("DisplayLogic"));
				m_BrowseField.setVFormat(atts.getValue("VFormat"));
				m_BrowseField.setFieldLength(Integer.parseInt(atts
						.getValue("FieldLength")));

				m_BrowseField.setValueMin(atts.getValue("ValueMin"));
				m_BrowseField.setValueMin(atts.getValue("ValueMax"));

				String Name = atts.getValue("ADValRuleNameID");
				id = get_IDWithColumn(ctx, "AD_Val_Rule", "Name", Name);
				m_BrowseField.setAD_Val_Rule_ID(id);

				 m_BrowseField
				 .setSeqNo(Integer.parseInt(atts.getValue("SeqNo")));
				 m_BrowseField
				 .setSortNo(Integer.parseInt(atts.getValue("SortNo")));				
				 m_BrowseField
				 .setIsOrderBy(Boolean.valueOf(
						 atts.getValue("IsOrderBy")).booleanValue());
				
				m_BrowseField
						.setDescription(getStringValue(atts, "Description"));
				m_BrowseField.setHelp(getStringValue(atts, "Help"));
				m_BrowseField
						.setIsActive(atts.getValue("isActive") != null ? Boolean
								.valueOf(atts.getValue("isActive"))
								.booleanValue() : true);

				m_BrowseField
						.setIsRange(atts.getValue("isRange") != null ? Boolean
								.valueOf(atts.getValue("isRange"))
								.booleanValue() : true);

				m_BrowseField.setIsKey(atts.getValue("isKey") != null ? Boolean
						.valueOf(atts.getValue("isKey")).booleanValue() : true);

				m_BrowseField
						.setIsQueryCriteria(atts.getValue("isQueryCriteria") != null ? Boolean
								.valueOf(atts.getValue("isQueryCriteria"))
								.booleanValue() : true);

				m_BrowseField
						.setIsIdentifier(atts.getValue("isIdentifier") != null ? Boolean
								.valueOf(atts.getValue("isIdentifier"))
								.booleanValue() : true);

				Name = atts.getValue("ADReferenceNameID");
				id = get_IDWithColumn(ctx, "AD_Reference", "Name", Name);
				m_BrowseField.setAD_Reference_ID(id);

				// Name = atts.getValue("ADValRuleNameID");
				// id = get_IDWithColumn(ctx, "AD_Val_Rule", "Name", Name);
				// m_BrowseField.setAD_Val_Rule_ID(id);
				Name = atts.getValue("ADReferenceNameValueID");
				id = get_IDWithColumn(ctx, "AD_Reference", "Name", Name);
				m_BrowseField.setAD_Reference_Value_ID(id);

				Name = atts.getValue("ADAxisViewColumnNameID");
				id = get_IDWithMasterAndColumn(ctx, "AD_View_Column",
						"ColumnName", Name, "AD_View", viewid);
				m_BrowseField.setAxis_Column_ID(id);

				Name = atts.getValue("ADAxisParentViewColumnNameID");
				id = get_IDWithMasterAndColumn(ctx, "AD_View_Column",
						"ColumnName", Name, "AD_View", viewid);
				m_BrowseField.setAxis_Parent_Column_ID(id);

				// Setup Element.
				Name = atts.getValue("ADElementNameID");
				id = get_IDWithColumn(ctx, "AD_Element", "ColumnName", Name);
				X_AD_Element adElement = new X_AD_Element(ctx, id,
						getTrxName(ctx));

				String Object_Status_col = Object_Status;
				if (adElement.getAD_Element_ID() == 0) {
					// Object_Status = "New";
					adElement.setColumnName(m_BrowseField.getAD_View_Column()
							.getAD_Column().getColumnName());
					adElement.setEntityType(m_BrowseField.getEntityType());
					adElement.setPrintName(m_BrowseField.getName());

					adElement.setName(m_BrowseField.getName());
					if (adElement.save(getTrxName(ctx)) == true) {
						record_log(
								ctx,
								1,
								m_BrowseField.getName(),
								"Element",
								adElement.getAD_Element_ID(),
								AD_Backup_ID,
								"New",
								"AD_Element",
								get_IDWithColumn(ctx, "AD_Table", "TableName",
										"AD_Element"));
					} else {
						record_log(
								ctx,
								0,
								m_BrowseField.getName(),
								"Element",
								adElement.getAD_Element_ID(),
								AD_Backup_ID,
								"New",
								"AD_Element",
								get_IDWithColumn(ctx, "AD_Table", "TableName",
										"AD_Element"));
					}
				}

				Object_Status = Object_Status_col;
				m_BrowseField.setAD_Element_ID(adElement.getAD_Element_ID());

				if (m_BrowseField.save(getTrxName(ctx)) == true) {
					record_log(
							ctx,
							1,
							m_BrowseField.getName(),
							"BrowseField",
							m_BrowseField.get_ID(),
							AD_Backup_ID,
							Object_Status,
							"AD_Browse_Field",
							get_IDWithColumn(ctx, "AD_Table", "TableName",
									"AD_Browse_Field"));
					element.recordId = m_BrowseField.getAD_Browse_Field_ID();
				} else {
					record_log(
							ctx,
							0,
							m_BrowseField.getName(),
							"BrowseField",
							m_BrowseField.get_ID(),
							AD_Backup_ID,
							Object_Status,
							"AD_Browse_Field",
							get_IDWithColumn(ctx, "AD_Table", "TableName",
									"AD_Browse_Field"));
					throw new POSaveFailedException(
							"Failed to save field definition.");
				}
			} else {
				element.defer = true;
				return;
			}
		} else {
			element.skip = true;
		}
	}

	public void endElement(Properties ctx, Element element) throws SAXException {
	}

	public void create(Properties ctx, TransformerHandler document)
			throws SAXException {
		int AD_Browse_Field_ID = Env.getContextAsInt(ctx,
				X_AD_Browse_Field.COLUMNNAME_AD_Browse_Field_ID);
		MBrowseField m_BrowseField = new MBrowseField(ctx, AD_Browse_Field_ID,
				null);
		AttributesImpl atts = new AttributesImpl();
		createBrowseFieldBinding(atts, m_BrowseField);

		PackOut packOut = (PackOut) ctx.get("PackOutProcess");

		if (m_BrowseField.getAD_Reference_ID() > 0) {
			packOut.createReference(m_BrowseField.getAD_Reference_ID(),
					document);
		}

		if (m_BrowseField.getAD_Reference_Value_ID() > 0) {
			packOut.createReference(m_BrowseField.getAD_Reference_Value_ID(),
					document);
		}

		if (m_BrowseField.getAD_Val_Rule_ID() > 0) {
			packOut.createDynamicRuleValidation(m_BrowseField.getAD_Val_Rule_ID(), document);
		}
		

		document.startElement("", "", "browsefield", atts);
		document.endElement("", "", "browsefield");
	}

	private AttributesImpl createBrowseFieldBinding(AttributesImpl atts,
			X_AD_Browse_Field m_BrowseField) {
		String sql = null;
		String name = null;
		atts.clear();
		if (m_BrowseField.getAD_Browse_Field_ID() <= PackOut.MAX_OFFICIAL_ID)
			atts.addAttribute("", "", "AD_Browse_Field_ID", "CDATA",
					Integer.toString(m_BrowseField.getAD_Browse_Field_ID()));
		if (m_BrowseField.getAD_View_Column_ID() > 0) {
			sql = "SELECT ColumnName FROM AD_View_Column WHERE AD_View_Column_ID=?";
			name = DB.getSQLValueString(null, sql,
					m_BrowseField.getAD_View_Column_ID());
			atts.addAttribute("", "", "ADViewColumnNameID", "CDATA", name);
		} else
			atts.addAttribute("", "", "ADViewColumnNameID", "CDATA", "");

		if (m_BrowseField.getAD_View_Column_ID() > 0) {
			sql = "SELECT AD_View_ID FROM AD_View_Column WHERE AD_View_Column_ID=?";
			int idView = DB.getSQLValue(null, sql,
					m_BrowseField.getAD_View_Column_ID());
			sql = "SELECT Name FROM AD_View WHERE AD_View_ID=?";
			name = DB.getSQLValueString(null, sql, idView);
			atts.addAttribute("", "", "ADViewNameID", "CDATA", name);
		} else
			atts.addAttribute("", "", "ADViewNameID", "CDATA", "");

		if (m_BrowseField.getAD_Browse_Field_ID() > 0) {
			sql = "SELECT Name FROM AD_Browse_Field WHERE AD_Browse_Field_ID=?";
			name = DB.getSQLValueString(null, sql,
					m_BrowseField.getAD_Browse_Field_ID());
			atts.addAttribute("", "", "ADBrowseFieldNameID", "CDATA", name);
		} else
			atts.addAttribute("", "", "ADBrowseFieldNameID", "CDATA", "");

		if (m_BrowseField.getAD_Browse_ID() > 0) {
			sql = "SELECT Name FROM AD_Browse WHERE AD_Browse_ID=?";
			name = DB.getSQLValueString(null, sql,
					m_BrowseField.getAD_Browse_ID());
			atts.addAttribute("", "", "ADBrowseNameID", "CDATA", name);
		} else
			atts.addAttribute("", "", "ADBrowseNameID", "CDATA", "");

		atts.addAttribute("", "", "EntityType", "CDATA", (m_BrowseField
				.getEntityType() != null ? m_BrowseField.getEntityType() : ""));
		atts.addAttribute(
				"",
				"",
				"Name",
				"CDATA",
				(m_BrowseField.getName() != null ? m_BrowseField.getName() : ""));
		atts.addAttribute("", "", "isCentrallyMaintained", "CDATA",
				(m_BrowseField.isCentrallyMaintained() == true ? "true"
						: "false"));
		atts.addAttribute("", "", "isMandatory", "CDATA",
				(m_BrowseField.isMandatory() == true ? "true" : "false"));
		atts.addAttribute("", "", "Displayed", "CDATA",
				(m_BrowseField.isDisplayed() == true ? "true" : "false"));
		atts.addAttribute("", "", "isActive", "CDATA",
				(m_BrowseField.isActive() == true ? "true" : "false"));
		atts.addAttribute("", "", "isRange", "CDATA",
				(m_BrowseField.isRange() == true ? "true" : "false"));
		atts.addAttribute("", "", "isKey", "CDATA",
				(m_BrowseField.isKey() == true ? "true" : "false"));
		atts.addAttribute("", "", "isQueryCriteria", "CDATA",
				(m_BrowseField.isQueryCriteria() == true ? "true" : "false"));
		atts.addAttribute("", "", "isIdentifier", "CDATA",
				(m_BrowseField.isIdentifier() == true ? "true" : "false"));
		atts.addAttribute("", "", "isReadOnly", "CDATA", 
				(m_BrowseField.isReadOnly() == true ? "true" : "false"));
		atts.addAttribute("", "", "SeqNo", "CDATA",
				"" + (m_BrowseField.getSeqNo()));
		atts.addAttribute("", "", "SortNo", "CDATA",
				"" + (m_BrowseField.getSortNo()));
		atts.addAttribute("", "", "isOrderBy", "CDATA",
				(m_BrowseField.isOrderBy() == true ? "true" : "false"));
		
		atts.addAttribute("", "", "DisplayLogic", "CDATA", (m_BrowseField.
				getDisplayLogic() != null ? m_BrowseField.getDisplayLogic() : ""));
		atts.addAttribute("", "", "ReadOnlyLogic", "CDATA", (m_BrowseField.
				getReadOnlyLogic() != null ? m_BrowseField.getReadOnlyLogic() : ""));
		atts.addAttribute("", "", "DefaultValue", "CDATA", (m_BrowseField
				.getDefaultValue() != null ? m_BrowseField.getDefaultValue() : ""));
		atts.addAttribute("", "", "DefaultValue2", "CDATA", (m_BrowseField
				.getDefaultValue2() != null ? m_BrowseField.getDefaultValue2() : ""));
		atts.addAttribute("", "", "ValueMin", "CDATA", (m_BrowseField
				.getValueMin() != null ? m_BrowseField.getValueMin() : ""));
		atts.addAttribute("", "", "ValueMax", "CDATA", (m_BrowseField
				.getValueMax() != null ? m_BrowseField.getValueMax() : ""));	
		atts.addAttribute("", "", "FieldLength", "CDATA",
				"" + (m_BrowseField.getFieldLength()));
		atts.addAttribute("", "", "VFormat", "CDATA", (m_BrowseField
				.getVFormat() != null ? m_BrowseField.getVFormat() : ""));
		
		atts.addAttribute(
				"",
				"",
				"Description",
				"CDATA",
				(m_BrowseField.getDescription() != null ? m_BrowseField
						.getDescription() : ""));
		atts.addAttribute(
				"",
				"",
				"Help",
				"CDATA",
				(m_BrowseField.getHelp() != null ? m_BrowseField.getHelp() : ""));

		// Element - this info is not needed since we search for element based
		// on ColumnName
		if (m_BrowseField.getAD_Element_ID() > 0) {
			sql = "SELECT ColumnName FROM AD_Element WHERE AD_Element_ID=?";
			name = DB.getSQLValueStringEx(null, sql,
					m_BrowseField.getAD_Element_ID());
			atts.addAttribute("", "", "ADElementNameID", "CDATA", name);
		} else
			atts.addAttribute("", "", "ADElementNameID", "CDATA", "");

		if (m_BrowseField.getAD_Reference_ID() > 0) {
			sql = "SELECT Name FROM AD_Reference WHERE AD_Reference_ID=?";
			name = DB.getSQLValueString(null, sql,
					m_BrowseField.getAD_Reference_ID());
			atts.addAttribute("", "", "ADReferenceNameID", "CDATA", name);
		} else
			atts.addAttribute("", "", "ADReferenceNameID", "CDATA", "");
		if (m_BrowseField.getAD_Reference_Value_ID() > 0) {
			sql = "SELECT Name FROM AD_Reference WHERE AD_Reference_ID=?";
			name = DB.getSQLValueString(null, sql,
					m_BrowseField.getAD_Reference_Value_ID());
			atts.addAttribute("", "", "ADReferenceNameValueID", "CDATA", name);
		} else
			atts.addAttribute("", "", "ADReferenceNameValueID", "CDATA", "");
		
		if (m_BrowseField.getAD_Val_Rule_ID() > 0) {
			sql = "SELECT Name FROM AD_Val_Rule WHERE AD_Val_Rule_ID=?";
			name = DB
					.getSQLValueString(null, sql, m_BrowseField.getAD_Val_Rule_ID());
			atts.addAttribute("", "", "ADValRuleNameID", "CDATA", name);
		} else			
			atts.addAttribute("", "", "ADValRuleNameID", "CDATA", "");

		if (m_BrowseField.getAxis_Column_ID() > 0) {
			sql = "SELECT ColumnName FROM AD_View_Column WHERE AD_View_Column_ID=?";
			name = DB.getSQLValueString(null, sql,
					m_BrowseField.getAxis_Column_ID());
			atts.addAttribute("", "", "ADAxisViewColumnNameID", "CDATA", name);
		} else
			atts.addAttribute("", "", "ADAxisViewColumnNameID", "CDATA", "");

		if (m_BrowseField.getAxis_Parent_Column_ID() > 0) {
			sql = "SELECT ColumnName FROM AD_View_Column WHERE AD_View_Column_ID=?";
			name = DB.getSQLValueString(null, sql,
					m_BrowseField.getAxis_Parent_Column_ID());
			atts.addAttribute("", "", "ADAxisParentViewColumnNameID", "CDATA",
					name);
		} else
			atts.addAttribute("", "", "ADAxisParentViewColumnNameID", "CDATA",
					"");

		return atts;
	}
}
