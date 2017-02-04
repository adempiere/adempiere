package org.compiere.minigrid;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import org.compiere.model.PO;

public interface IMiniTable 
{
	public boolean isCellEditable(int row, int column);
	
	public Object getValueAt(int row, int column);
	
	public void setValueAt(Object value, int row, int column);
	
	public int convertColumnIndexToModel(int viewColumnIndex);
	
	public int convertColumnIndexToView(int modelColumnIndex);
	
	public void setColumnReadOnly (int index, boolean readOnly);
	
	public String prepareTable(ColumnInfo[] layout, String from, String where, boolean multiSelection, String tableName);
	
	public void addColumn (String header);
	
	public void setColumnClass (int index, Class classType, boolean readOnly, String header);
	
	public void setColumnClass (int index, Class classType, boolean readOnly);
	
	public void loadTable(ResultSet rs);
	
	public void loadTable(PO[] pos);
	
	public void setKeyColumnIndex (int keyColumnIndex);

	public int getKeyColumnIndex();
	
	public ArrayList<Integer> getSelectedKeys();
	
	public Integer getSelectedRowKey();
	
	public int getSelectedRow();
	
	public int getRowKey(int row);
	
	public boolean isRowChecked(int row);
	
	public void setRowCount (int rowCount);
	
	public ColumnInfo[] getLayoutInfo();

	public int getColumnCount();
	
	public int getRowCount();
	
	public boolean getShowTotals();
	
	public void setMultiSelection(boolean multiSelection);
	
	public boolean isMultiSelection();
	
	public int getColorCode (int row);
	
	public void setColorCompare (Object dataCompare);
	
	public void repaint();
	
	public void autoSize();

	public int convertRowIndexToModel(int row);
	
	public void setRowChecked(int row, boolean value);
	
}
