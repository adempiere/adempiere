package org.eevolution.form;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.adempiere.model.MBrowseField;
import org.eevolution.grid.WBrowseListbox;
import org.compiere.model.GridField;
import org.compiere.model.GridFieldVO;

public class WBrowserRows {

	MBrowseField m_browse_field = null;
	GridField m_grid_field = null;
	private Integer column = null;
	private Integer row = null;
	private Integer viewColumns=0;
	private LinkedHashMap<Integer, LinkedHashMap<Integer, Object>> rows = new LinkedHashMap<Integer, LinkedHashMap<Integer, Object>>();
	private LinkedHashMap<Integer, MBrowseField> browser_head = new LinkedHashMap<Integer, MBrowseField>();
	private LinkedHashMap<Integer, Integer> display_indexes =new LinkedHashMap<Integer, Integer>(); 
	private LinkedHashMap<Integer, Integer> indexes_display =new LinkedHashMap<Integer, Integer>();
	private LinkedHashMap<String, Integer> columnnames_index =new LinkedHashMap<String, Integer>();
	private WBrowseListbox table; 
	
	/**
	 * 
	 * *** Build Of Class ***
	 */
	public WBrowserRows() {
	}
	
	/**
	 * Build With table
	 * *** Build Of Class ***
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 21/10/2013, 16:05:28
	 * @param table
	 */
	public WBrowserRows(WBrowseListbox table)
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
		MBrowseField field  = getBrowseField(col);
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
		//voBase.InfoFactoryClass = field.getInfoFactoryClass();
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
		
		/**
		 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 02/09/2013, 07:02:36
		 * Set Callout
		 */
		voBase.Callout = field.getCallout();//"org.eevolution.form.BrowserCallOutExample.methodExample";
		voBase.initFinish();
		/**
		 * End Carlos Parada
		 */
		
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
	public void addBrowseField(int col , MBrowseField field)
	{
		/**
		 * Carlos Parada Add Indexes to LinkedHasMap
		 */
		
		if (field.isDisplayed()){
			indexes_display.put(col,viewColumns);
			display_indexes.put(viewColumns,col);
			viewColumns++;
		}
		columnnames_index.put(field.getAD_View_Column().getAD_Column().getColumnName(), col);
		/**
		 * End Carlos Parada
		 */
		
		browser_head.put(col, field);
	}
	
	/**
	 * Get BrowseField Column
	 * @param col
	 * @return
	 * @return MBrowseField
	 */
	public MBrowseField getBrowseField (int col)
	{
		return browser_head.get(col);
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
	public LinkedHashMap<Integer, MBrowseField> getBrowser_head() {
		return browser_head;
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
		return browser_head.size();
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
		
		return indexes_display.get(index);
	}
	
	/**
	 * Get Column Index From Browse Fields 
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 21/10/2013, 11:53:52
	 * @param display
	 * @return
	 * @return int
	 */
	public int getTableIndex(int display) {
		return display_indexes.get(display);
	}
	
	/**
	 * set Table
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 21/10/2013, 16:06:07
	 * @param table
	 * @return void
	 */
	public void setTable(WBrowseListbox table) {
		this.table = table;
	}
	
	/**
	 * get Table 
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 21/10/2013, 16:12:57
	 * @return
	 * @return BrowseTable
	 */
	public WBrowseListbox getTable() {
		return table;
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
			return table.getSelectedIndex();
	}
	
	/**
	 * Get Value of Selected Cell
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 21/10/2013, 16:17:26
	 * @return
	 * @return Object
	 */
	public Object getValueofSelectedCell()
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
	public void setValueofSelectedCell(Object Value)
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
	public Object getValueofColumn(int col)
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
	public void setValueofColumn(String ColumnName,Object Value,int row)
	{
		if (table!=null){
			if (columnnames_index.get(ColumnName)!=null){
				GridField gField=(GridField) getValue(row, columnnames_index.get(ColumnName));
				if (gField!=null){
					table.setValueAt(gField, Value, row, getDisplayIndex(columnnames_index.get(ColumnName)));
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
	public Object getValueofColumn(String ColumnName,int row)
	{
		int index; 
		if(table!=null){
			index =(columnnames_index.get(ColumnName)==null?-1:columnnames_index.get(ColumnName));
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
