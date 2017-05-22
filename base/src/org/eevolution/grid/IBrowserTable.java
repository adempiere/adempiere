/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2003-2014 e-Evolution Consultants. All Rights Reserved.      *
 * Copyright (C) 2003-2014 Victor Pérez Juárez 								  *
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

package org.eevolution.grid;

import java.sql.ResultSet;
import java.util.List;

import org.adempiere.model.MBrowseField;
import org.compiere.model.GridField;

/**
 * Created by e-Evolution on 13/12/14.
 * @author victor.perez@www.e-evolution.com, e-Evolution
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<li>FR [ 245 ] Change Smart Browse to MVC
 * 		@see https://github.com/adempiere/adempiere/issues/245
 * 		<li>BR [ 268 ] Smart Browse Table don't have a MVC
 * 		@see https://github.com/adempiere/adempiere/issues/268
 */
public interface IBrowserTable {
	public boolean isCellEditable(int row, int column);
	
	public Object getValueAt(int row, int column);
	
	//	FR [ 245 ]
	public BrowserRow getData();
	
	public void setValueAt(Object value, int row, int column);
	
	public String prepareTable(List<MBrowseField> fields, boolean multiSelection);
	
	public int getSelectedColumn();
	
	//	BR [ 257 ]
	public int loadTable(ResultSet rs);
	
	//	BR [ 268 ]
	public String processCallOut(GridField field, Object value, Object oldValue, int currentRow, int currentColumn);
	
	public GridField getGridFieldAt(int row, int column);
	
	public int convertColumnIndexToModel(int viewColumnIndex);
	
	public void setColumnReadOnly (int index, boolean readOnly);

	public boolean isShowTotals();

	public void setShowTotals(boolean show);
	
	public void addColumn (String header);

	public void setValueAt(int row, int column, GridField value);

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
