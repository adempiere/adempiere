package org.eevolution.grid;

import java.util.List;
import org.adempiere.model.MBrowseField;
import org.compiere.model.GridField;

public interface IBrowseTable 
{
	public boolean isCellEditable(int row, int column);
	
	public Object getValueAt(int row, int column);
	
	public void setValueAt(Object value, int row, int column);
	
	public int convertColumnIndexToModel(int viewColumnIndex);
	
	public void setColumnReadOnly (int index, boolean readOnly);
	
	public String prepareTable(List<MBrowseField> fields,boolean multiSelection);
	
	public void addColumn (String header);
	
	public void setColumnClass (int index, GridField gField,int displayType, boolean readOnly, String header);
		
	public Integer getSelectedRowKey();
	
	public int getSelectedRow();
	
	public void setRowCount (int rowCount);

	public int getColumnCount();
	
	public int getRowCount();
	
	public void setMultiSelection(boolean multiSelection);
	
	public boolean isMultiSelection();
	
	public int getColorCode (int row);
	
	public void setColorCompare (Object dataCompare);
	
	public void repaint();
	
	public void autoSize();
}
