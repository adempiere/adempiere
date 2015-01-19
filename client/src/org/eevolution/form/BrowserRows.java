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
 * Contributor(s): carlosaparada@gmail.com 							  		  *
 *****************************************************************************/

package org.eevolution.form;

import org.adempiere.model.MBrowseField;
import org.compiere.model.GridField;
import org.eevolution.grid.BrowseTable;
import org.eevolution.grid.IBrowserRows;
import java.util.LinkedHashMap;

/**
 *   @author carlosaparada@gmail.com Carlos Parada, ERP Consultores y asociados
 *   @author victor.perez@www.e-evolution.com, e-Evolution
 */
public class BrowserRows implements IBrowserRows {

	private Integer column = null;
	private Integer row = null;
	private Integer noViewColumns = 0;
	private LinkedHashMap<Integer, LinkedHashMap<Integer, GridField>> rows = new LinkedHashMap<Integer, LinkedHashMap<Integer, GridField>>();
	private LinkedHashMap<Integer, MBrowseField> browserFields = new LinkedHashMap<Integer, MBrowseField>();
	private LinkedHashMap<Integer, Integer> displayIndexes =new LinkedHashMap<Integer, Integer>();
	private LinkedHashMap<Integer, Integer> indexesDisplay =new LinkedHashMap<Integer, Integer>();
	private LinkedHashMap<String, Integer> columnNamesIndex =new LinkedHashMap<String, Integer>();
	private BrowseTable table;

	/**
	 *
	 * *** Build Of Class ***
	 */
	public BrowserRows() {
	}

	/**
	 * Build With table
	 * *** Build Of Class ***
	 * @param table
	 */
	public BrowserRows(BrowseTable table)
	{
		this.table=table;
	}

	/**
	 * Add Column
	 * @param field
	 * @return void
	 */
	public void addBrowserField(MBrowseField field, int column)
	{
		if (field.isDisplayed()){
			indexesDisplay.put(column, noViewColumns);
			displayIndexes.put(noViewColumns, column);
			noViewColumns++;
		}
		columnNamesIndex.put(field.getAD_View_Column().getAD_Column().getColumnName(), column);
		browserFields.put(column, field);
	}

	/**
	 * Get BrowseField Column
	 * @param col
	 * @return
	 * @return MBrowseField
	 */
	public MBrowseField getBrowserField(int col)
	{
		return browserFields.get(col);
	}

	/**
	 * Set Row
	 * @param id
	 * @param row
	 * @return void
	 */
	/*public void setRow (int id  , ArrayList<Object> row)
	{
		LinkedHashMap<Integer, GridField> values = rows.get(id);
		if (values == null)
			values = new LinkedHashMap<Integer, GridField>();

		for (Object o : row)
			values.put(id, o);

		rows.put(id, values);
	}*/

	/**
	 * Set Value to Browse Rows
	 * @param row
	 * @param col
	 * @return void
	 */
	public void setValue(int row , int col, GridField gridField)
	{
		this.column = col;
		this.row = row;

		LinkedHashMap<Integer, GridField> values = rows.get(row);
		if (values == null)
			values = new LinkedHashMap<Integer, GridField>();

		values.put(col , gridField);
		rows.put(row , values);
	}

	/**
	 * Get Value From BrowseRows
	 * @param row
	 * @param col
	 * @return
	 * @return Object
	 */
	public GridField getValue(int row , int col)
	{
		if (rows.size() > row)
		{
			LinkedHashMap<Integer, GridField> values = rows.get(row);
			return values.get(col);
		}
		return null;
	}

	/**
	 * Heads
	 * @return
	 * @return LinkedHashMap<Integer,MBrowseField>
	 */
	public LinkedHashMap<Integer, MBrowseField> getBrowserFields() {
		return browserFields;
	}

	/**
	 * Rows
	 * @return
	 * @return LinkedHashMap<Integer,LinkedHashMap<Integer,Object>>
	 */
	public LinkedHashMap<Integer, LinkedHashMap<Integer, GridField>> getRows() {
		return rows;
	}

	/**
	 * Qty Rows
	 * @return
	 * @return int
	 */
	public int size()
	{
		return rows.size();
	}

	/**
	 * Clear Rows
	 * @return void
	 */
	public void clear()
	{
		rows.clear();
	}

	/**
	 * Get Number of Columns
	 * @return
	 * @return int
	 */
	public int getColumnCount()
	{
		return browserFields.size();
	}


	/**
	 * Returns Qty Columns Displayed
	 * @return
	 * @return Integer
	 */
	public Integer getNoViewColumns() {
		return noViewColumns;
	}

	/**
	 * get Column Index From Table
	 * @param index
	 * @return
	 * @return int
	 */
	public int getDisplayIndex(int index) {

		return indexesDisplay.get(index);
	}

	/**
	 * Get Column Index From Browse Fields
	 * @param display
	 * @return
	 * @return int
	 */
	public int getTableIndex(int display) {
		return displayIndexes.get(display);
	}

	/**
	 * get Table
	 * @return
	 * @return BrowseTable
	 */
	public BrowseTable getTable() {
		return table;
	}

	/**
	 * set Table
	 * @param table
	 * @return void
	 */
	public void setTable(BrowseTable table) {
		this.table = table;
	}

	/**
	 * Get Selected Row
	 * @return
	 * @return int
	 */
	public int getSelectedRow()
	{
		if (table==null)
			return -1;
		else
			return table.getSelectedRow();
	}

	/**
	 * Get Selected Row
	 * @return
	 * @return int
	 */
	public int getSelectedColumn()
	{
		if (table==null)
			return -1;
		else
			return table.getSelectedColumn();
	}

	/**
	 * Get Value of Selected Cell
	 * @return
	 * @return Object
	 */
	public Object getValueOfSelectedCell()
	{
		if (table!=null)
			return getValue(getSelectedRow(), getTableIndex(getSelectedColumn()));
		else
			return null;
	}

	/**
	 *
	 * @param gridField
	 */
	public void setValueOfSelectedCell(GridField gridField)
	{

		if (table != null){
			//GridField gridField = getValue(getSelectedRow(), getTableIndex(getSelectedColumn()));
			if (gridField != null){
				table.setValueAt(getSelectedRow(), getSelectedColumn(), gridField);
			}
		}
	}


	/**
	 * Get Object GridField from Column Index
	 * @param col
	 * @return
	 * @return Object
	 */
	public Object getValueOfColumn(int col)
	{
		if(table!=null)
			return getValue(getSelectedRow(), col);
		else
			return null;
	}

	/**
	 *
	 * @param row
	 * @param ColumnName
	 * @param gridField
	 */
	public void setValueOfColumn(int row, String ColumnName, GridField gridField)
	{
		if (table != null){
			if (columnNamesIndex.get(ColumnName) != null) {
				//GridField gridField = getValue(row, columnNamesIndex.get(ColumnName));
				if (gridField != null){
					table.setValueAt(row, getDisplayIndex(columnNamesIndex.get(ColumnName)), gridField);
				}
			}

		}
	}

	/**
	 *
	 * @param row
	 * @param ColumnName
	 * @return
	 */
	public Object getValueOfColumn(int row, String ColumnName)
	{
		int index;
		if(table != null){
			index = (columnNamesIndex.get(ColumnName) == null ? -1: columnNamesIndex.get(ColumnName));
			if (index >= 0)
				return getValue(row, index);
		}
		return null;
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		for (int i=0; i < rows.size() ; i++)
			result.append(rows.get(i) + "\n");
		return result.toString();
	}
}
