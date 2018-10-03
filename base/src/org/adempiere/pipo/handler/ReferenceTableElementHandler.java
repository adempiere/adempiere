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

import static org.compiere.model.I_AD_Ref_Table.COLUMNNAME_AD_Reference_ID;

import java.util.Properties;

import javax.xml.transform.sax.TransformerHandler;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.pipo.AbstractElementHandler;
import org.adempiere.pipo.Element;
import org.adempiere.pipo.PackOut;
import org.adempiere.pipo.exception.POSaveFailedException;
import org.compiere.model.I_AD_Ref_Table;
import org.compiere.model.I_AD_Reference;
import org.compiere.model.MColumn;
import org.compiere.model.MRefTable;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.model.X_AD_Ref_Table;
import org.compiere.model.X_AD_Reference;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class ReferenceTableElementHandler extends AbstractElementHandler {

	public void startElement(Properties ctx, Element element)
			throws SAXException {
		String elementValue = element.getElementValue();

		log.info(elementValue);
		Attributes atts = element.attributes;
		String entitytype = atts.getValue("EntityType");
		String name = atts.getValue("ADRefenceNameID");
		if (isProcessElement(ctx, entitytype)) {
			if (element.parent != null && element.parent.skip) {
				element.skip = true;
				return;
			}
			
			int AD_Reference_ID = 0;
			if (element.parent != null && element.parent.getElementValue().equals("reference") &&
				element.parent.recordId > 0) {
				AD_Reference_ID = element.parent.recordId;
			} else {
				StringBuffer sqlB = new StringBuffer(
					"SELECT AD_Reference_ID FROM AD_Reference WHERE Name= ?");
				AD_Reference_ID = DB.getSQLValue(getTrxName(ctx), sqlB.toString(), name);
			}
			if (AD_Reference_ID <= 0 && atts.getValue("AD_Reference_ID") != null && Integer.parseInt(atts.getValue("AD_Reference_ID")) <= PackOut.MAX_OFFICIAL_ID)
				AD_Reference_ID = Integer.parseInt(atts.getValue("AD_Reference_ID"));
			
			int tableId = get_IDWithColumn(ctx, "AD_Table", "TableName", atts
					.getValue("ADTableNameID"));
			if (tableId == 0) {
				MTable m_Table = new MTable(ctx, 0, getTrxName(ctx));
				m_Table.setAccessLevel("3");
				m_Table.setName(atts.getValue("ADTableNameID"));
				m_Table.setTableName(atts.getValue("ADTableNameID"));
				if (m_Table.save(getTrxName(ctx)) == true) {
					record_log(ctx, 1, m_Table.getName(), "Table", m_Table
							.get_ID(), 0, "New", "AD_Table", get_IDWithColumn(
							ctx, "AD_Table", "TableName", "AD_Table"));
				} else {
					record_log(ctx, 0, m_Table.getName(), "Table", m_Table
							.get_ID(), 0, "New", "AD_Table", get_IDWithColumn(
							ctx, "AD_Table", "TableName", "AD_Table"));
				}
				tableId = get_IDWithColumn(ctx, "AD_Table", "TableName", atts
						.getValue("ADTableNameID"));
			}

			name = atts.getValue("Key");
			int keyId = get_IDWithMasterAndColumn(ctx, "AD_Column",
					"ColumnName", name, "AD_Table", tableId);
			if (keyId == 0)
				throw new AdempiereException("@AD_Column_ID@:" + name + "@NotFound@");

            int displayId = 0;
            name = atts.getValue("ADDisplay");
            displayId = get_IDWithMasterAndColumn(ctx, "AD_Column",
                    "ColumnName", name, "AD_Table", tableId);

            String isDisplayIdentifier = atts.getValue("IsDisplayIdentifier");
            String isAlert =   atts.getValue("IsAlert");

            String entityType = atts.getValue("EntityType");
			String isValueDisplayed = atts.getValue("IsValueDisplayed");


            String OrderByClause = "";
            String WhereClause = "";
            String displaySQL = "";
            if(DB.isOracle())
            {
			    OrderByClause = atts.getValue("OrderByClause");
			    WhereClause = atts.getValue("WhereClause");
                displaySQL = atts.getValue("DisplaySQL");
            }
            else if (DB.isPostgreSQL())
            {
                OrderByClause = atts.getValue("OrderByClause").replaceAll("''", "'");
                WhereClause = atts.getValue("WhereClause").replaceAll("''", "'");
                displaySQL = atts.getValue("DisplaySQL").replaceAll("''", "'");;
            }

            MRefTable refTable = new Query(ctx, I_AD_Ref_Table.Table_Name,
                    COLUMNNAME_AD_Reference_ID + "=?", getTrxName(ctx)).
                    setParameters(AD_Reference_ID)
                    .firstOnly();

            if (refTable != null)
            {
            		if(AD_Reference_ID > 0) {
            			refTable.setIsDirectLoad(true);
            		}
                    refTable.setAD_Table_ID(tableId);
                    refTable.setAD_Display(displayId);
                    refTable.setAD_Key(keyId);
                    refTable.setIsValueDisplayed(isValueDisplayed.equals("Y"));
                    refTable.setIsDisplayIdentifier(isDisplayIdentifier.equals("Y"));
                    refTable.setIsAlert(isAlert.equals("Y"));
                    refTable.setDisplaySQL(displaySQL);
                    refTable.setOrderByClause(OrderByClause);
                    refTable.setEntityType(entityType);
                    refTable.setWhereClause(WhereClause);
                    refTable.saveEx();
                    if (refTable != null) {
                        record_log(ctx, 1, atts.getValue("ADRefenceNameID"),
                                "Reference Table", AD_Reference_ID, 0, "Update", "AD_Ref_Table",
                                get_IDWithColumn(ctx, "AD_Table", "TableName",
                                        "AD_Ref_Table"));
                    } else {
                        record_log(ctx, 0, atts.getValue("ADRefenceNameID"),
                                "Reference Table", AD_Reference_ID, 0, "Update", "AD_Ref_Table",
                                get_IDWithColumn(ctx, "AD_Table", "TableName",
                                        "AD_Ref_Table"));
                        throw new POSaveFailedException("ReferenceTable");
                    }
			}
            else
            {
                refTable = new MRefTable(ctx, 0 , getTrxName(ctx));
                refTable.setAD_Reference_ID(AD_Reference_ID);
                refTable.setAD_Table_ID(tableId);
                refTable.setAD_Display(displayId);
                refTable.setAD_Key(keyId);
                refTable.setIsValueDisplayed(isValueDisplayed.equals("Y"));
                refTable.setIsDisplayIdentifier(isDisplayIdentifier.equals("Y"));
                refTable.setIsAlert(isAlert.equals("Y"));
                refTable.setDisplaySQL(displaySQL);
                refTable.setOrderByClause(OrderByClause);
                refTable.setEntityType(entityType);
                refTable.setWhereClause(WhereClause);

                if (refTable.save())
					record_log(ctx, 1, atts.getValue("ADRefenceNameID"),
							"Reference Table", AD_Reference_ID, 0, "New", "AD_Ref_Table",
							get_IDWithColumn(ctx, "AD_Table", "TableName",
									"AD_Ref_Table"));
                else
                {
					record_log(ctx, 0, atts.getValue("ADRefenceNameID"),
							"Reference Table", AD_Reference_ID, 0, "New", "AD_Ref_Table",
							get_IDWithColumn(ctx, "AD_Table", "TableName",
									"AD_Ref_Table"));
					throw new POSaveFailedException("ReferenceTable");
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
		int Reference_id = Env.getContextAsInt(ctx,
				X_AD_Ref_Table.COLUMNNAME_AD_Reference_ID);
		AttributesImpl atts = new AttributesImpl();
		createReferenceTableBinding(ctx, atts, Reference_id);
		document.startElement("", "", "referencetable", atts);
		document.endElement("", "", "referencetable");
	}

	private AttributesImpl createReferenceTableBinding(Properties ctx,
			AttributesImpl atts, int reference_ID) {
		atts.clear();
		if (reference_ID <= PackOut.MAX_OFFICIAL_ID)
			atts.addAttribute("", "", "AD_Reference_ID", "CDATA", Integer.toString(reference_ID));

        I_AD_Reference reference = new X_AD_Reference(ctx, reference_ID,getTrxName(ctx));
        MRefTable refTable;
        if (X_AD_Reference.VALIDATIONTYPE_TableValidation.equals(reference.getValidationType()))
        {
            refTable = new MRefTable(ctx, reference_ID, getTrxName(ctx));
            if(refTable != null)
            {
                atts.addAttribute("", "", "ADRefenceNameID", "CDATA", reference.getName());
                if(refTable.getAD_Table_ID() > 0)
					atts.addAttribute("", "", "ADTableNameID", "CDATA", refTable.getAD_Table().getTableName());
				else
					atts.addAttribute("", "", "ADTableNameID", "CDATA", "");
                if (refTable.getAD_Display() > 0)
					atts.addAttribute("", "", "ADDisplay", "CDATA", MColumn.get(ctx,refTable.getAD_Display()).getColumnName());
                else
					atts.addAttribute("", "", "ADDisplay", "CDATA", "");

                if(refTable.getAD_Key() >  0)
               	atts.addAttribute("", "", "Key", "CDATA", MColumn.get(ctx, refTable.getAD_Key()).getColumnName());
				else
					atts.addAttribute("", "", "Key", "CDATA", "");

                if(refTable.getAD_Window_ID() > 0)
                    atts.addAttribute("", "", "ADWindowNameID", "CDATA", refTable.getAD_Window().getName());
                else
                    atts.addAttribute("", "", "ADWindowNameID", "CDATA", "");

				atts.addAttribute("", "", "EntityType", "CDATA", (refTable.getEntityType() != null ? refTable.getEntityType() : ""));
				atts.addAttribute("", "", "IsValueDisplayed", "CDATA", refTable.isValueDisplayed() ? "Y" : "N");
                atts.addAttribute("", "", "IsDisplayIdentifier", "CDATA", refTable.isDisplayIdentifier() ? "Y" : "N");
                atts.addAttribute("", "", "IsAlert", "CDATA", refTable.isAlert() ? "Y" : "N");
                atts.addAttribute("", "", "DisplaySQL", "CDATA", (refTable.getDisplaySQL() != null ? refTable.getDisplaySQL() : ""));
                atts.addAttribute("", "", "OrderByClause", "CDATA", (refTable.getOrderByClause()  != null ? refTable.getOrderByClause() : ""));
				atts.addAttribute("", "", "isActive", "CDATA", (refTable.isActive() ? "Y" : "N"));
				atts.addAttribute("", "", "WhereClause", "CDATA", ( refTable.getWhereClause() != null ? refTable.getWhereClause() : ""));
			}
		}
		return atts;
	}
}
