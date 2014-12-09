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
import org.compiere.model.GridFieldVO;
import org.eevolution.grid.BrowseTable;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 *   @author victor.perez@www.e-evolution.com, e-Evolution
 *   @author carlosaparada@gmail.com Carlos Parada, ERP Consultores y asociados
 */
public class BrowserRows {

	private Integer column = null;
	private Integer row = null;
	private Integer viewColumns=0;
	private LinkedHashMap<Integer, LinkedHashMap<Integer, Object>> rows = new LinkedHashMap<Integer, LinkedHashMap<Integer, Object>>();
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
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 21/10/2013, 16:05:28
	 * @param table
	 */
	public BrowserRows(BrowseTable table)
	{
		this.table=table;
	}

	/**
	 * Get GridFieldVO From Column
	 * @param windowNo
	 * @param title
	 * @param col
	 * @return
	 * @return GridFieldVO
	 */
	public GridFieldVO getGridFieldVO(int windowNo,String title,int col)
	{
		MBrowseField field  = getBrowserField(col);
		GridFieldVO voBase = GridFieldVO.createStdField(field.getCtx(),
				windowNo, 0, 0, 0, false, false, false);

		String uniqueName =  field.getAD_View_Column().getColumnSQL();
		voBase.isProcess = true;
		voBase.IsDisplayed = field.isDisplayed();
		voBase.IsReadOnly = field.isReadOnly();
		voBase.IsUpdateable = true;
		voBase.WindowNo = windowNo;
		voBase.AD_Column_ID = field.getAD_View_Column().getAD_Column_ID();
		voBase.AD_Table_ID = field.getAD_View_Column().getAD_Column()
				.getAD_Table_ID();
		voBase.ColumnName = field.getAD_View_Column().getAD_Column()
				.getColumnName();
		voBase.displayType = field.getAD_Reference_ID();
		voBase.AD_Reference_Value_ID = field.getAD_Reference_Value_ID();
		voBase.IsMandatory = field.isMandatory();
		voBase.IsAlwaysUpdateable = false;
		voBase.IsKey = field.isKey();
		voBase.DefaultValue = field.getDefaultValue();
		voBase.DefaultValue2 = field.getDefaultValue2();
		voBase.InfoFactoryClass = field.getInfoFactoryClass();
		voBase.FieldLength = field.getFieldLength();
		voBase.ReadOnlyLogic = field.getReadOnlyLogic();
		voBase.DisplayLogic =  field.getDisplayLogic();
		voBase.VFormat = field.getVFormat();
		voBase.ValueMin = field.getValueMin();
		voBase.ValueMax = field.getValueMax();
		voBase.ValidationCode = field.getAD_Val_Rule().getCode();
		voBase.isRange = field.isRange();
		voBase.Description = field.getDescription();
		voBase.Help = uniqueName;
		voBase.Header = title;
		voBase.Callout = field.getCallout();
		voBase.initFinish();

		GridField gField = new GridField(GridFieldVO.createParameter(voBase));
		//  Set Default
		Object defaultObject = gField.getDefault();
		gField.setValue (defaultObject, true);
		gField.lookupLoadComplete();
		return voBase;
	}

	/**
	 * Add Column
	 * @param col
	 * @param field
	 * @return void
	 */
	public void addBrowserField(int col, MBrowseField field)
	{
		/**
		 * Carlos Parada Add Indexes to LinkedHasMap
		 */
		if (field.isDisplayed()){
			indexesDisplay.put(col, viewColumns);
			displayIndexes.put(viewColumns, col);
			viewColumns++;
		}
		columnNamesIndex.put(field.getAD_View_Column().getAD_Column().getColumnName(), col);
		/**
		 * End Carlos Parada
		 */

		browserFields.put(col, field);
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
	public void setRow (int id  , ArrayList<Object> row)
	{
		LinkedHashMap<Integer, Object> values = rows.get(id);
		if (values == null)
			values = new LinkedHashMap<Integer, Object>();

		for (Object o : row)
			values.put(id, o);

		rows.put(id, values);
	}

	/**
	 * Set Value to Browse Rows
	 * @param row
	 * @param col
	 * @param value
	 * @return void
	 */
	public void setValue(int row , int col, Object value)
	{
		this.column = col;
		this.row = row;

		LinkedHashMap<Integer, Object> values = rows.get(row);
		if (values == null)
			values = new LinkedHashMap<Integer, Object>();

		values.put(col , value);
		rows.put(row , values);
	}

	/**
	 * Get Value From BrowseRows
	 * @param id
	 * @param col
	 * @return
	 * @return Object
	 */
	public Object getValue(int id , int col)
	{
		if (rows.size() > id)
		{
			LinkedHashMap<Integer, Object> values = rows.get(id);
			return values.get(col);
		}
		return null;
	}

	/**
	 * Heads
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 02/09/2013, 07:02:36
	 * @return
	 * @return LinkedHashMap<Integer,MBrowseField>
	 */
	public LinkedHashMap<Integer, MBrowseField> getBrowserFields() {
		return browserFields;
	}

	/**
	 * Rows
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 02/09/2013, 07:02:18
	 * @return
	 * @return LinkedHashMap<Integer,LinkedHashMap<Integer,Object>>
	 */
	public LinkedHashMap<Integer, LinkedHashMap<Integer, Object>> getRows() {
		return rows;
	}

	/**
	 * Qty Rows
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 02/09/2013, 07:02:22
	 * @return
	 * @return int
	 */
	public int size()
	{
		return rows.size();
	}

	/**
	 * Clear Rows
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 02/09/2013, 07:02:27
	 * @return void
	 */
	public void clear()
	{
		rows.clear();
	}

	/**
	 * Get Number of Columns
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 15/10/2013, 09:44:11
	 * @return
	 * @return int
	 */
	public int getColumnCount()
	{
		return browserFields.size();
	}


	/**
	 * Returns Qty Columns Displayed
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 15/10/2013, 11:09:56
	 * @return
	 * @return Integer
	 */
	public Integer getViewColumns() {
		return viewColumns;
	}

	/**
	 * get Column Index From Table
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 21/10/2013, 16:07:43
	 * @param index
	 * @return
	 * @return int
	 */
	public int getDisplayIndex(int index) {

		return indexesDisplay.get(index);
	}

	/**
	 * Get Column Index From Browse Fields
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 21/10/2013, 11:53:52
	 * @param display
	 * @return
	 * @return int
	 */
	public int getTableIndex(int display) {
		return displayIndexes.get(display);
	}

	/**
	 * get Table
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 21/10/2013, 16:12:57
	 * @return
	 * @return BrowseTable
	 */
	public BrowseTable getTable() {
		return table;
	}

	/**
	 * set Table
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 21/10/2013, 16:06:07
	 * @param table
	 * @return void
	 */
	public void setTable(BrowseTable table) {
		this.table = table;
	}

	/**
	 * Get Selected Row
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 21/10/2013, 16:13:11
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
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 21/10/2013, 16:13:11
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
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 21/10/2013, 16:17:26
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
	 * Set Value on Selected Cell
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 21/10/2013, 16:18:14
	 * @param Value
	 * @return void
	 */
	public void setValueOfSelectedCell(Object Value)
	{

		if (table!=null){
			GridField gField=(GridField) getValue(getSelectedRow(), getTableIndex(getSelectedColumn()));
			if (gField!=null){
				table.setValueAt(gField, Value, getSelectedRow(), getSelectedColumn());
			}
		}
	}


	/**
	 * Get Object GridField from Column Index
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> Oct 22, 2013, 2:49:42 PM
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
	 * Set Value of Column From ColumnName
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> Oct 22, 2013, 2:53:50 PM
	 * @param ColumnName
	 * @param Value
	 * @return void
	 */
	public void setValueOfColumn(String ColumnName, Object Value, int row)
	{
		if (table!=null){
			if (columnNamesIndex.get(ColumnName)!=null){
				GridField gField=(GridField) getValue(row, columnNamesIndex.get(ColumnName));
				if (gField!=null){
					table.setValueAt(gField, Value, row, getDisplayIndex(columnNamesIndex.get(ColumnName)));
				}
			}

		}
	}

	/**
	 * Get Object GridField from ColumnName
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> Oct 22, 2013, 2:48:21 PM
	 * @param ColumnName
	 * @return
	 * @return Object
	 */
	public Object getValueOfColumn(String ColumnName, int row)
	{
		int index;
		if(table!=null){
			index =(columnNamesIndex.get(ColumnName)==null?-1: columnNamesIndex.get(ColumnName));
			if (index>=0)
				return getValue(row, index);
		}
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer result = new StringBuffer();
		for (int i=0;i<rows.size();i++)
			result.append(rows.get(i) + "\n");
		return result.toString();
	}
}
