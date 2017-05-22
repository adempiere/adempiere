/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.process;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Hashtable;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_EXP_Format;
import org.compiere.model.MColumn;
import org.compiere.model.MEXPFormat;
import org.compiere.model.MEXPFormatLine;
import org.compiere.model.MTab;
import org.compiere.model.MTable;
import org.compiere.model.MWindow;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;


/**
 * Create a Export Format from a Window
 *
 * @author Victor Perez www.e-evolution.com
 * @version $Id: ExportFormatGenerator.java,v 1.0
 */
public class ExportFormatGenerator extends ExportFormatGeneratorAbstract {
    private Hashtable<String, MEXPFormat> exportFormats = new Hashtable<String, MEXPFormat>();
    private String version = "3.8.2";
    private String parentTable = null;
    private String formatValue = null;
    private int level = -1;

    /**
     * Prepare - e.g., get Parameters.
     */
    protected void prepare() {
        super.prepare();
    }    //	prepare

    /**
     * Generate Export Format
     *
     * @return info
     * @throws Exception
     */
    protected String doIt() throws Exception {
        MWindow window = new MWindow(getCtx(), getWindowId(), get_TrxName());
        MTab[] tabs = window.getTabs(true, get_TrxName());
        for (MTab tab : tabs) {
            MTable table = null;
            String format = null;
            if (iscludesonlytheTabsthatInsertrecords() && tab.isInsertRecord()) {
                table = new MTable(getCtx(), tab.getAD_Table_ID(), get_TrxName());
                format = createFormat(table);
            } else if (!iscludesonlytheTabsthatInsertrecords()) {
                table = new MTable(getCtx(), tab.getAD_Table_ID(), get_TrxName());
                format = createFormat(table);
            }
            if (tab.getTabLevel() > level) {
                parentTable = table.getTableName();
                formatValue = format;
            }
        }
        return "ok";
    }    //	doIt

    private String createFormat(MTable table) throws SQLException {
        log.info("Table Name:" + table.getTableName());
        MColumn[] columns = table.getColumns(true);
        String unique = null;
        boolean isFieldName = false;
        for (MColumn column : columns) {
            if (column.isIdentifier() && column.getSeqNo() == 1) {
                unique = column.getColumnName();
                if (unique.equals("Name"))
                    isFieldName = true;
                log.info("Unique Key" + unique);
                break;
            }
        }

        if (unique == null)
            unique = "Name";

        MEXPFormat format = null;
        //String formatValue = table.getTableName()+"_"+unique;
        String formatValue = table.getTableName();
        log.info("Export Format Value:" + formatValue);
        format = (MEXPFormat) exportFormats.get(formatValue);
        if (format != null)
            return format.getValue();

        String where = " value = ? ";
        Query sql = new Query(getCtx(), I_EXP_Format.Table_Name, where, get_TrxName()).setParameters(formatValue);
        if (sql.match()) {
            format = (MEXPFormat) sql.first();
            exportFormats.put(format.getValue(), format);
            return format.getValue();
        }

        format = MEXPFormat.getFormatByValueAD_Client_IDAndVersion(getCtx(), formatValue, getAD_Client_ID(), version, get_TrxName());
        if (format == null)
            format = new MEXPFormat(getCtx(), 0, get_TrxName());
        format.setAD_Org_ID(0);
        format.setValue(formatValue);
        format.setName(table.getName());
        format.setAD_Table_ID(table.getAD_Table_ID());
        format.setDescription(table.getDescription());
        format.setHelp(table.getHelp());
        format.setVersion(version);
        format.saveEx();
        if (format != null)
            exportFormats.put(format.getValue(), format);

        int position = 10;
        for (MColumn column : columns) {
            if (iscludesonlythemandatorycolumns()) {
                if (column.isMandatory())
                    createFormatLine(format, table, column, position, false);
            } else
                createFormatLine(format, table, column, position, false);
            position++;
        }
        return format.getValue();
    }

    private int createFormatLine(MEXPFormat format, MTable table, MColumn col, int position, boolean force) throws SQLException {

        MEXPFormatLine formatLine = null;
        String formatlinevalue = col.getColumnName();
        formatLine = MEXPFormatLine.getFormatLineByValue(getCtx(), formatlinevalue, format.getEXP_Format_ID(), get_TrxName());
        if (formatLine == null)
            formatLine = new MEXPFormatLine(getCtx(), 0, get_TrxName());


        formatLine.setAD_Org_ID(0);
        formatLine.setEXP_Format_ID(format.getEXP_Format_ID());
        formatLine.setValue(formatlinevalue);
        formatLine.setName(col.getName());
        formatLine.setDescription(col.getDescription());
        formatLine.setHelp(col.getHelp());
        formatLine.setPosition(position);
        formatLine.setIsMandatory(col.isMandatory());
        if (force || (col.isIdentifier() && !col.isKey())) {
            formatLine.setIsPartUniqueIndex(true);
            formatLine.setIsActive(true);
        } else {
            formatLine.setIsActive(false);
        }

        MTable tabledir = null;

        if (col.getColumnName().equals(parentTable + "_ID")
                && DisplayType.isID(col.getAD_Reference_ID())) {
            MEXPFormat referenceFormat = null;
            referenceFormat = MEXPFormat.getFormatByValueAD_Client_IDAndVersion(getCtx(), parentTable + "_Key", getAD_Client_ID(), version, get_TrxName());

            if (referenceFormat == null) {
                referenceFormat = new MEXPFormat(getCtx(), 0, get_TrxName());
            }

            referenceFormat.setAD_Org_ID(0);
            referenceFormat.setValue(parentTable + "_Key");
            referenceFormat.setName(parentTable + "_Key");
            referenceFormat.setAD_Table_ID(MTable.getTable_ID(parentTable));
            referenceFormat.setDescription(table.getDescription());
            referenceFormat.setHelp(table.getHelp());
            referenceFormat.saveEx();

            int AD_Column_ID = DB.getSQLValue(get_TrxName(), "SELECT AD_Column_ID FROM AD_Column WHERE AD_Table_ID=(SELECT AD_Table_ID FROM AD_Table WHERE TableName=?) AND UPPER(ColumnName)='DOCUMENTNO'", parentTable);
            if (AD_Column_ID > 0) {
                //used if the export format is a document like invoice, etc.
                createFormatLine(referenceFormat, table, new MColumn(getCtx(), AD_Column_ID, get_TrxName()), 10, true);
                AD_Column_ID = 0;
                AD_Column_ID = DB.getSQLValue(get_TrxName(), "SELECT AD_Column_ID FROM AD_Column WHERE AD_Table_ID=(SELECT AD_Table_ID FROM AD_Table WHERE TableName=?) AND UPPER(ColumnName)='C_DOCTYPE_ID'", parentTable);
                if (AD_Column_ID > 0)
                    createFormatLine(referenceFormat, table, new MColumn(getCtx(), AD_Column_ID, get_TrxName()), 20, true);
                formatLine.setValue(parentTable + "_Key");
                formatLine.setName("Key DocumentNo_C_DocType");
                formatLine.setAD_Column_ID(col.getAD_Column_ID());
                formatLine.setType(MEXPFormatLine.TYPE_ReferencedEXPFormat);
                formatLine.setEXP_EmbeddedFormat_ID(referenceFormat.getEXP_Format_ID());
                formatLine.saveEx();

                if (parentTable != null) {
                    if (col.isParent() && col.getColumnName().contains(parentTable)) {

                        int reference = ((MEXPFormat) exportFormats.get(formatValue)).getEXP_Format_ID();
                        MEXPFormatLine embededformatLine = new MEXPFormatLine(getCtx(), 0, get_TrxName());
                        embededformatLine.setAD_Org_ID(0);
                        embededformatLine.setValue(format.getValue() + "_Embedded");
                        embededformatLine.setName("Embedded " + format.getName());
                        embededformatLine.setEXP_EmbeddedFormat_ID(formatLine.getEXP_Format_ID());
                        embededformatLine.setEXP_Format_ID(reference);
                        embededformatLine.setType(MEXPFormatLine.TYPE_EmbeddedEXPFormat);
                        embededformatLine.setAD_Column_ID(col.getAD_Column_ID());
                        embededformatLine.saveEx();
                    }
                }
                log.info("Export Format Line:" + formatLine.getName());
                return formatLine.getEXP_FormatLine_ID();
            } else {
                AD_Column_ID = DB.getSQLValue(get_TrxName(), "SELECT AD_Column_ID FROM AD_Column WHERE AD_Table_ID=(SELECT AD_Table_ID FROM AD_Table WHERE TableName=?) AND UPPER(ColumnName)='NAME'", parentTable);
                if (AD_Column_ID > 0) {
                    createFormatLine(referenceFormat, table, new MColumn(getCtx(), AD_Column_ID, get_TrxName()), 10, true);
                } else {
                    AD_Column_ID = DB.getSQLValue(get_TrxName(), "SELECT AD_Column_ID FROM AD_Column WHERE AD_Table_ID=(SELECT AD_Table_ID FROM AD_Table WHERE TableName=?) AND UPPER(ColumnName)='VALUE'", parentTable);
                    if (AD_Column_ID > 0) {
                        createFormatLine(referenceFormat, table, new MColumn(getCtx(), AD_Column_ID, get_TrxName()), 10, true);
                    } else {
                        throw new AdempiereException("Table without name or value column");
                    }
                }
                formatLine.setValue(parentTable + "_Key");
                formatLine.setName("Key " + col.getColumnName());
                formatLine.setAD_Column_ID(col.getAD_Column_ID());
                formatLine.setType(MEXPFormatLine.TYPE_ReferencedEXPFormat);
                formatLine.setEXP_EmbeddedFormat_ID(referenceFormat.getEXP_Format_ID());
                formatLine.saveEx();
                return formatLine.getEXP_FormatLine_ID();
            }
        }

        if (DisplayType.isID(col.getAD_Reference_ID()) && col.getAD_Reference_Value_ID() > 0) {
            int AD_Table_ID = DB.getSQLValue(get_TrxName(), "SELECT rt.AD_Table_ID FROM AD_Reference r INNER JOIN AD_Ref_Table rt ON (r.AD_Reference_ID=rt.AD_Reference_ID)  WHERE r.AD_Reference_ID=?", col.getAD_Reference_Value_ID());
            if (AD_Table_ID > 0) {
                tabledir = MTable.get(getCtx(), AD_Table_ID);
                formatLine.setValue(col.getColumnName() + "_Reference");
                formatLine.setName("Referenced " + tabledir.getTableName());
                formatLine.setAD_Column_ID(col.getAD_Column_ID());
                String format_value = createFormat(tabledir);
                int embedded = ((MEXPFormat) exportFormats.get(format_value)).getEXP_Format_ID();
                formatLine.setType(MEXPFormatLine.TYPE_ReferencedEXPFormat);
                formatLine.setEXP_EmbeddedFormat_ID(embedded);
                formatLine.saveEx();
                return formatLine.getEXP_FormatLine_ID();
            }

        }
        if (DisplayType.isID(col.getAD_Reference_ID())
         && col.isKey() == false
         && DisplayType.ID  != col.getAD_Reference_ID()
         && DisplayType.Image != col.getAD_Reference_ID()) {

            String tableName = col.getColumnName().substring(0, col.getColumnName().lastIndexOf("_ID"));
            log.info("Table Name:" + tableName);

            if (tableName == null) {
                log.info("Table Name: null");
                return 0;
            }

            tabledir = MTable.get(getCtx(), tableName);
            if (tabledir == null)
                return 0;
            //	throw new Exception ("Ilegal Table Name");

            formatLine.setValue(tabledir.getTableName() + "_Reference");
            formatLine.setName("Referenced " + tabledir.getTableName());

            //formatLine.setType(MEXPFormatLine.TYPE_XMLElement);
            if (tabledir != null) {
                String format_value = createFormat(tabledir);
                int embedded = ((MEXPFormat) exportFormats.get(format_value)).getEXP_Format_ID();
                formatLine.setType(MEXPFormatLine.TYPE_ReferencedEXPFormat);
                formatLine.setEXP_EmbeddedFormat_ID(embedded);
            } else
                formatLine.setType(MEXPFormatLine.TYPE_XMLElement);
        }
        formatLine.setAD_Column_ID(col.getAD_Column_ID());
        formatLine.saveEx();
        log.info("Export Format Line:" + formatLine.getName());
        return formatLine.getEXP_FormatLine_ID();
    }
}    //	Generate Export Format
