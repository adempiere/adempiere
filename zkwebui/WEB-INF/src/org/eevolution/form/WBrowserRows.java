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
 * Copyright (C) 2003-2014 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): carlosaparada@gmail.com 							  		  *
 *****************************************************************************/

package org.eevolution.form;

import org.adempiere.model.MBrowseField;
import org.compiere.model.GridField;
import org.eevolution.grid.IBrowserRows;
import org.eevolution.grid.WBrowseListbox;

import java.util.LinkedHashMap;

/**
 * @author victor.perez@www.e-evolution.com, e-Evolution
 * @author carlosaparada@gmail.com Carlos Parada, ERP Consultores y asociados
 */
public class WBrowserRows implements IBrowserRows {

    private Integer column = null;
    private Integer row = null;
    private Integer noViewColumns = 0;
    private LinkedHashMap<Integer, LinkedHashMap<Integer, GridField>> rows = new LinkedHashMap<Integer, LinkedHashMap<Integer, GridField>>();
    private LinkedHashMap<Integer, MBrowseField> browserFields = new LinkedHashMap<Integer, MBrowseField>();
    private LinkedHashMap<Integer, Integer> displayIndexes = new LinkedHashMap<Integer, Integer>();
    private LinkedHashMap<Integer, Integer> indexesDisplay = new LinkedHashMap<Integer, Integer>();
    private LinkedHashMap<String, Integer> columnNamesIndex = new LinkedHashMap<String, Integer>();
    private WBrowseListbox table;

    /**
     * *** Build Of Class ***
     */
    public WBrowserRows() {
    }

    public WBrowserRows(WBrowseListbox table) {
        this.table = table;
    }


    /**
     *
     * @param field
     * @param column
     */
    public void addBrowserField(MBrowseField field, int column) {

        if (field.isDisplayed()) {
            indexesDisplay.put(column, noViewColumns);
            displayIndexes.put(noViewColumns, column);
            noViewColumns++;
        }
        columnNamesIndex.put(field.getAD_View_Column().getAD_Column().getColumnName(), column);

        browserFields.put(column, field);
    }

    /**
     *
     * @param col
     * @return
     */
    public MBrowseField getBrowserField(int col) {
        return browserFields.get(col);
    }

    /*public void setRow(int id, ArrayList<Object> row) {
        LinkedHashMap<Integer, Object> values = rows.get(id);
        if (values == null)
            values = new LinkedHashMap<Integer, Object>();

        for (Object o : row)
            values.put(id, o);

        rows.put(id, values);
    }*/

    /**
     *
     * @param row
     * @param col
     * @param gridField
     */
    public void setValue(int row, int col, GridField gridField) {
        this.column = col;
        this.row = row;

        LinkedHashMap<Integer, GridField> values = rows.get(row);
        if (values == null)
            values = new LinkedHashMap<Integer, GridField>();

        values.put(col, gridField);
        rows.put(row, values);
    }

    /**
     *
     * @param id
     * @param col
     * @return
     */
    public GridField getValue(int row, int col) {

        if (rows.size() > row) {
            LinkedHashMap<Integer, GridField> values = rows.get(row);
            return values.get(col);
        }
        return null;
    }

    /**
     *
     * @return
     */
    public LinkedHashMap<Integer, MBrowseField> getBrowserFields() {
        return browserFields;
    }

    /**
     *
     * @return
     */
    public LinkedHashMap<Integer, LinkedHashMap<Integer, GridField>> getRows() {
        return rows;
    }

    public int size() {
        return rows.size();
    }

    public void clear() {
        rows.clear();
    }

    public int getColumnCount() {
        return browserFields.size();
    }

    public Integer getNoViewColumns() {
        return noViewColumns;
    }


    public int getDisplayIndex(int index) {

        return indexesDisplay.get(index);
    }

    public int getTableIndex(int display) {
        return displayIndexes.get(display);
    }

    public WBrowseListbox getTable() {
        return table;
    }

    public void setTable(WBrowseListbox table) {
        this.table = table;
    }

    public int getSelectedRow() {
        if (table == null)
            return -1;
        else
            return table.getSelectedRow();
    }


    public int getSelectedColumn() {
        if (table == null)
            return -1;
        else
            return table.getSelectedIndex();
    }


    public Object getValueOfSelectedCell() {
        if (table != null)
            return getValue(getSelectedRow(), getTableIndex(getSelectedColumn()));
        else
            return null;
    }


    public void setValueOfSelectedCell(GridField gridField) {

        if (table != null) {
            //GridField gridField = (GridField) getValue(getSelectedRow(), getTableIndex(getSelectedColumn()));
            if (gridField != null) {
                table.setValueAt(getSelectedRow(), getSelectedColumn(), gridField);
            }
        }
    }


    public Object getValueOfColumn(int col) {
        if (table != null)
            return getValue(getSelectedRow(), col);
        else
            return null;
    }


    public void setValueOfColumn(int row, String ColumnName, GridField gridField) {
        if (table != null) {
            if (columnNamesIndex.get(ColumnName) != null) {
                //GridField gridField = (GridField) getValue(row, columnNamesIndex.get(ColumnName));
                if (gridField != null) {
                    table.setValueAt(row, getDisplayIndex(columnNamesIndex.get(ColumnName)), gridField);
                }
            }

        }
    }


    public Object getValueOfColumn(int row, String ColumnName) {
        int index;
        if (table != null) {
            index = (columnNamesIndex.get(ColumnName) == null ? -1 : columnNamesIndex.get(ColumnName));
            if (index >= 0)
                return getValue(row, index);
        }
        return null;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < rows.size(); i++)
            result.append(rows.get(i) + "\n");
        return result.toString();
    }
}
