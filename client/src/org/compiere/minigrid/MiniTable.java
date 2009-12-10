/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.minigrid;

import java.awt.Component;
import java.awt.Insets;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;

import javax.swing.DefaultCellEditor;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.compiere.apps.search.Info_Column;
import org.compiere.grid.ed.VCellRenderer;
import org.compiere.grid.ed.VHeaderRenderer;
import org.compiere.model.MRole;
import org.compiere.model.PO;
import org.compiere.swing.CCheckBox;
import org.compiere.swing.CTable;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Util;

/**
 *  Mini Table.
 *  Default Read Only Table for Boolean, String, Number, Timestamp values
 *  <p>
 *  After initializing the Table Model, you need to call setColumnClass,
 *  add columns via addColumn or in one go prepare the table.
 *  <code>
 *  MiniTable mt = new MiniTable();
 *  String sql = mt.prepareTable(..);   //  table defined
 *  //  add where to the sql statement
 *  ResultSet rs = ..
 *  mt.loadTable(rs);
 *  rs.close();
 *  </code>
 *  @author     Jorg Janke
 *  @version    $Id: MiniTable.java,v 1.3 2006/07/30 00:51:28 jjanke Exp $
 * 
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 				<li>BF [ 1891082 ] NPE on MiniTable when you hide some columns
 * 				<li>FR [ 1974299 ] Add MiniTable.getSelectedKeys method
 * 				<li>FR [ 2847295 ] MiniTable multiselection checkboxes not working
 * 					https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2847295&group_id=176962
 * @author Teo Sarca, teo.sarca@gmail.com
 * 				<li>BF [ 2876895 ] MiniTable.loadTable: NPE if column is null
 * 					https://sourceforge.net/tracker/?func=detail&aid=2876895&group_id=176962&atid=879332
 */
public class MiniTable extends CTable implements IMiniTable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2853772547464132496L;

	/**
	 *  Default Constructor
	 */
	public MiniTable()
	{
		super();
	//	log.config( "MiniTable");
		setCellSelectionEnabled(false);
		setRowSelectionAllowed(false);
		//  Default Editor
		this.setCellEditor(new ROCellEditor());
	}   //  MiniTable

	/** List of R/W columns     */
	private ArrayList<Integer>   m_readWriteColumn = new ArrayList<Integer>();
	/** List of Column Width    */
	private ArrayList<Integer>   m_minWidth = new ArrayList<Integer>();

	/** Color Column Index of Model     */
	private int         m_colorColumnIndex = -1;
	/** Color Column compare data       */
	private Object      m_colorDataCompare = Env.ZERO;

	/** Multi Selection mode (default false) */
	private boolean     m_multiSelection = false;

	/** Lauout set in prepareTable and used in loadTable    */
	private ColumnInfo[]        m_layout = null;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(MiniTable.class);
	/** Is Total Show */
	private boolean showTotals = false;

	/**
	 * Gets the swing column of given index. No index checking 
	 * is done.
	 * 
	 * @param col
	 * @return
	 */
	public TableColumn getColumn(int col) {
		return(getColumnModel().getColumn(col));
	}

	/**
	 * Return number of columns in the mini table
	 */
	public int getColumnCount() {
		return(getColumnModel().getColumnCount());
	}
	
	/**
	 *	Size Columns.
	 *  Uses Mimimum Column Size
	 */
	public void autoSize()
	{
		long start = System.currentTimeMillis();
		//
		final int SLACK = 8;		//	making sure it fits in a column
		final int MAXSIZE = 300;    //	max size of a column
		//
		TableModel model = this.getModel();
		int size = model.getColumnCount();
		//	for all columns
		for (int col = 0; col < size; col++)
		{
			//  Column & minimum width
			TableColumn tc = this.getColumnModel().getColumn(col);
			int width = 0;
			if (m_minWidth.size() > col)
				width = ((Integer)m_minWidth.get(col)).intValue();
		//  log.config( "Column=" + col + " " + column.getHeaderValue());

			//	Header
			TableCellRenderer renderer = tc.getHeaderRenderer();
			if (renderer == null)
				renderer = new DefaultTableCellRenderer();
			Component comp = renderer.getTableCellRendererComponent
				(this, tc.getHeaderValue(), false, false, 0, 0);
		//	log.fine( "Hdr - preferred=" + comp.getPreferredSize().width + ", width=" + comp.getWidth());
			width = Math.max(width, comp.getPreferredSize().width + SLACK);

			//	Cells
			int maxRow = Math.min(30, getRowCount());       //  first 30 rows
			for (int row = 0; row < maxRow; row++)
			{
				renderer = getCellRenderer(row, col);
				comp = renderer.getTableCellRendererComponent
					(this, getValueAt(row, col), false, false, row, col);
				if (comp != null) {
					int rowWidth = comp.getPreferredSize().width + SLACK;
					width = Math.max(width, rowWidth);
				}
			}
			//	Width not greater ..
			width = Math.min(MAXSIZE, width);
			tc.setPreferredWidth(width);
		//	log.fine( "width=" + width);
		}	//	for all columns
		log.finer("Cols=" + size + " - " + (System.currentTimeMillis()-start) + "ms");
	}	//	autoSize


	/**
	 *  Is Cell Editable
	 *  @param row row
	 *  @param column column
	 *  @return true if editable
	 */
	public boolean isCellEditable(int row, int column)
	{
		//  if the first column is a boolean and it is false, it is not editable
		if (column != 0
				&& getValueAt(row, 0) instanceof Boolean
				&& !((Boolean)getValueAt(row, 0)).booleanValue())
			return false;

		//  is the column RW?
		if (m_readWriteColumn.contains(new Integer(column)))
			return true;
		return false;
	}   //  isCellEditable

	/**
	 *  Set Column to ReadOnly
	 *  @param column column
	 *  @param readOnly read only
	 */
	public void setColumnReadOnly (int column, boolean readOnly)
	{
		//  Column is ReadWrite
		if (m_readWriteColumn.contains(new Integer(column)))
		{
			//  Remove from list
			if (readOnly)
			{
				int size = m_readWriteColumn.size();
				for (int i = 0; i < size; i++)
				{
					if (((Integer)m_readWriteColumn.get(i)).intValue() == column)
					{
						m_readWriteColumn.remove(i);
						break;
					}
				}
			}   //  ReadOnly
		}
		//  current column is R/O - ReadWrite - add to list
		else if (!readOnly)
			m_readWriteColumn.add(new Integer(column));
	}   //  setColumnReadOnly

	
	/**************************************************************************
	 *  Prepare Table and return SQL
	 *
	 *  @param layout    array of column info
	 *  @param from      SQL FROM content
	 *  @param where     SQL WHERE content
	 *  @param multiSelection multiple selections
	 *  @param tableName table name
	 *  @return SQL
	 */
	public String prepareTable(ColumnInfo[] layout, 
		String from, String where, boolean multiSelection, String tableName)
	{
		m_layout = layout;
		m_multiSelection = multiSelection;
		//
		StringBuffer sql = new StringBuffer ("SELECT ");
		//  add columns & sql
		for (int i = 0; i < layout.length; i++)
		{
			//  create sql
			if (i > 0)
				sql.append(", ");
			sql.append(layout[i].getColSQL());
			//  adding ID column
			if (layout[i].isKeyPairCol())
				sql.append(",").append(layout[i].getKeyPairColSQL());

			//  add to model
			addColumn(layout[i].getColHeader());
			if (layout[i].isColorColumn())
				setColorColumn(i);
			if (layout[i].getColClass() == IDColumn.class)
				p_keyColumnIndex = i;
		}
		//  set editors (two steps)
		for (int i = 0; i < layout.length; i++)
			setColumnClass(i, layout[i].getColClass(), layout[i].isReadOnly(), layout[i].getColHeader());

		sql.append( " FROM ").append(from);
		sql.append(" WHERE ").append(where);

		//  Table Selection
		setRowSelectionAllowed(true);
		
		//	org.compiere.apps.form.VMatch.dynInit calls routine for initial init only
		if (from.length() == 0)
			return sql.toString();
		//
		String finalSQL = MRole.getDefault().addAccessSQL(sql.toString(), 
			tableName, MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO);
		log.finest(finalSQL);
		return finalSQL;
	}   //  prepareTable

	/**
	 *  Add Table Column.
	 *  after adding a column, you need to set the column classes again
	 *  (DefaultTableModel fires TableStructureChanged, which calls
	 *  JTable.tableChanged .. createDefaultColumnsFromModel
	 *  @param header header
	 */
	public void addColumn (String header)
	{
		if (getModel() instanceof DefaultTableModel)
		{
			DefaultTableModel model = (DefaultTableModel)getModel();
			model.addColumn(Util.cleanAmp(header));
		}
		else
			throw new IllegalArgumentException("Model must be instance of DefaultTableModel");
	}   //  addColumn

	/**
	 *  Set Column Editor & Renderer to Class.
	 *  (after all columns were added)
	 *  @param index column index
	 *  @param c   class of column - determines renderere
	 *  @param readOnly read only flag
	 */
	public void setColumnClass (int index, Class c, boolean readOnly)
	{
		setColumnClass(index, c, readOnly, null);
	}   //  setColumnClass

	/**
	 *  Set Column Editor & Renderer to Class
	 *  (after all columns were added)
	 *  Lauout of IDColumn depemds on multiSelection
	 *  @param index column index
	 *  @param c   class of column - determines renderere/editors supported:
	 *  IDColumn, Boolean, Double (Quantity), BigDecimal (Amount), Integer, Timestamp, String (default)
	 *  @param readOnly read only flag
	 *  @param header optional header value
	 */
	public void setColumnClass (int index, Class c, boolean readOnly, String header)
	{
	//	log.config( "MiniTable.setColumnClass - " + index, c.getName() + ", r/o=" + readOnly);
		TableColumn tc = getColumnModel().getColumn(index);
		if (tc == null)
			return;
		//  Set R/O
		setColumnReadOnly(index, readOnly);

		//  Header
		if (header != null && header.length() > 0)
			tc.setHeaderValue(Util.cleanAmp(header));

		//  ID Column & Selection
		if (c == IDColumn.class)
		{
			tc.setCellRenderer(new IDColumnRenderer(m_multiSelection));
			if (m_multiSelection)
			{
				tc.setCellEditor(new IDColumnEditor());
				setColumnReadOnly(index, false);
			}
			else
			{
				tc.setCellEditor(new ROCellEditor());
			}
			m_minWidth.add(new Integer(10));
			tc.setMaxWidth(20);
			tc.setPreferredWidth(20);
			tc.setResizable(false);
			
			tc.setHeaderRenderer(new VHeaderRenderer(DisplayType.Number));
		}
		//  Boolean
		else if (c == Boolean.class)
		{
			tc.setCellRenderer(new CheckRenderer());
			if (readOnly)
				tc.setCellEditor(new ROCellEditor());
			else
			{
				CCheckBox check = new CCheckBox();
				check.setMargin(new Insets(0,0,0,0));
				check.setHorizontalAlignment(SwingConstants.CENTER);
				tc.setCellEditor(new DefaultCellEditor(check));
			}
			m_minWidth.add(new Integer(30));
			
			tc.setHeaderRenderer(new VHeaderRenderer(DisplayType.YesNo));
		}
		//  Date
		else if (c == Timestamp.class)
		{
			tc.setCellRenderer(new VCellRenderer(DisplayType.Date));
			if (readOnly)
				tc.setCellEditor(new ROCellEditor());
			else
				tc.setCellEditor(new MiniCellEditor(c));
			m_minWidth.add(new Integer(30));
			
			tc.setHeaderRenderer(new VHeaderRenderer(DisplayType.DateTime));
		}
		//  Amount
		else if (c == BigDecimal.class)
		{
			tc.setCellRenderer(new VCellRenderer(DisplayType.Amount));
			if (readOnly)
			{
				tc.setCellEditor(new ROCellEditor());
				m_minWidth.add(new Integer(70));
			}
			else
			{
				tc.setCellEditor(new MiniCellEditor(c));
				m_minWidth.add(new Integer(80));
			}
			
			tc.setHeaderRenderer(new VHeaderRenderer(DisplayType.Number));
		}
		//  Number
		else if (c == Double.class)
		{
			tc.setCellRenderer(new VCellRenderer(DisplayType.Number));
			if (readOnly)
			{
				tc.setCellEditor(new ROCellEditor());
				m_minWidth.add(new Integer(70));
			}
			else
			{
				tc.setCellEditor(new MiniCellEditor(c));
				m_minWidth.add(new Integer(80));
			}
			
			tc.setHeaderRenderer(new VHeaderRenderer(DisplayType.Number));
		}
		//  Integer
		else if (c == Integer.class)
		{
			tc.setCellRenderer(new VCellRenderer(DisplayType.Integer));
			if (readOnly)
				tc.setCellEditor(new ROCellEditor());
			else
				tc.setCellEditor(new MiniCellEditor(c));
			m_minWidth.add(new Integer(30));
			
			tc.setHeaderRenderer(new VHeaderRenderer(DisplayType.Number));
		}
		//  String
		else
		{
			tc.setCellRenderer(new VCellRenderer(DisplayType.String));
			if (readOnly)
				tc.setCellEditor(new ROCellEditor());
			else
				tc.setCellEditor(new MiniCellEditor(String.class));
			m_minWidth.add(new Integer(30));
			
			tc.setHeaderRenderer(new VHeaderRenderer(DisplayType.String));
		}
	//	log.fine( "Renderer=" + tc.getCellRenderer().toString() + ", Editor=" + tc.getCellEditor().toString());
	}   //  setColumnClass

	/**
	 *  Clear Table Content
	 *  @param no number of rows
	 */
	public void setRowCount (int no)
	{
		if (getModel() instanceof DefaultTableModel)
		{
			DefaultTableModel model = (DefaultTableModel)getModel();
			model.setRowCount(no);
		//	log.config( "MiniTable.setRowCount", "rows=" + getRowCount() + ", cols=" + getColumnCount());
		}
		else
			throw new IllegalArgumentException("Model must be instance of DefaultTableModel");
	}   //  setRowCount

	
	/**************************************************************************
	 *	Load Table from ResultSet - The ResultSet is not closed
	 *
	 *  @param rs ResultSet with the column layout defined in prepareTable
	 */
	public void loadTable(ResultSet rs)
	{
		if (m_layout == null)
			throw new UnsupportedOperationException("Layout not defined");

		//  Clear Table
		setRowCount(0);
		//
		try
		{
			while (rs.next())
			{
				int row = getRowCount();
				setRowCount(row+1);
				int colOffset = 1;  //  columns start with 1
				for (int col = 0; col < m_layout.length; col++)
				{
					Object data = null;
					Class<?> c = m_layout[col].getColClass();
					int colIndex = col + colOffset;
					if (c == IDColumn.class)
						data = new IDColumn(rs.getInt(colIndex));
					else if (c == Boolean.class)
						data = new Boolean("Y".equals(rs.getString(colIndex)));
					else if (c == Timestamp.class)
						data = rs.getTimestamp(colIndex);
					else if (c == BigDecimal.class)
						data = rs.getBigDecimal(colIndex);
					else if (c == Double.class)
						data = rs.getDouble(colIndex);
					else if (c == Integer.class)
						data = rs.getInt(colIndex);
					else if (c == KeyNamePair.class)
					{
						String display = rs.getString(colIndex);
						int key = rs.getInt(colIndex+1);
						data = new KeyNamePair(key, display);
						colOffset++;
					}
					else
					{
						String s = rs.getString(colIndex);
						if (s != null)
							data = s.trim();	//	problems with NCHAR
					}
					//  store
					setValueAt(data, row, col);
			//		log.fine( "r=" + row + ", c=" + col + " " + m_layout[col].getColHeader(),
			//			"data=" + data.toString() + " " + data.getClass().getName() + " * " + m_table.getCellRenderer(row, col));
				}
				

			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, "", e);
		}
		if(getShowTotals())
			addTotals(m_layout);
		autoSize();
		log.config("Row(rs)=" + getRowCount());
		
		
	}	//	loadTable

	/**
	 *	Load Table from Object Array
	 *  @param pos array of POs 
	 */
	public void loadTable(PO[] pos)
	{
		if (m_layout == null)
			throw new UnsupportedOperationException("Layout not defined");

		//  Clear Table
		setRowCount(0);
		//
		for (int i = 0; i < pos.length; i++)
		{
			PO myPO = pos[i];
			int row = getRowCount();
			setRowCount(row+1);
			
			for (int col = 0; col < m_layout.length; col++)
			{
				String columnName = m_layout[col].getColSQL();
				Object data = myPO.get_Value(columnName);
				if (data != null)
				{
					Class<?> c = m_layout[col].getColClass();
					if (c == IDColumn.class)
						data = new IDColumn(((Integer)data).intValue());
					else if (c == Double.class)
						data = new Double(((BigDecimal)data).doubleValue());
				}
				//  store
				setValueAt(data, row, col);
			}
		}
		if(getShowTotals())
			addTotals(m_layout);
		autoSize();
		log.config("Row(array)=" + getRowCount());
	}	//	loadTable
	
	
	/**
	 *  Get the key of currently selected row based on layout defined in prepareTable
	 *  @return ID if key
	 */
	public Integer getSelectedRowKey()
	{
		if (m_layout == null)
			throw new UnsupportedOperationException("Layout not defined");

		int row = getSelectedRow();
		if (row != -1 && p_keyColumnIndex != -1)
		{
			Object data = getModel().getValueAt(row, p_keyColumnIndex);
			if (data instanceof IDColumn)
				data = ((IDColumn)data).getRecord_ID();
			if (data instanceof Integer)
				return (Integer)data;
		}
		return null;
	}   //  getSelectedRowKey

	/**
	 * @return collection of selected IDs
	 */
	public Collection<Integer> getSelectedKeys()
	{
		if (m_layout == null)
		{
			throw new UnsupportedOperationException("Layout not defined");
		}
		if (p_keyColumnIndex < 0)
		{
			throw new UnsupportedOperationException("Key Column is not defined");
		}
		//
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int row = 0; row < getRowCount(); row++)
		{
			Object data = getModel().getValueAt(row, p_keyColumnIndex);
			if (data instanceof IDColumn)
			{
				IDColumn record = (IDColumn)data;
				if (record.isSelected())
				{
					list.add(record.getRecord_ID());
				}
			}
		}
		return list;
	}

	/**************************************************************************
	 *  Get Layout
	 *  @return Array of ColumnInfo
	 */
	public ColumnInfo[] getLayoutInfo()
	{
		return m_layout;
	}   //  getLayout

	/**
	 *  Set Single Selection
	 *  @param multiSelection multiple selections
	 */
	public void setMultiSelection (boolean multiSelection)
	{
		m_multiSelection = multiSelection;
	}   //  setMultiSelection

	/**
	 *  Single Selection Table
	 *  @return true if multiple rows can be selected
	 */
	public boolean isMultiSelection()
	{
		return m_multiSelection;
	}   //  isMultiSelection

	/**
	 *	Set the Column to determine the color of the row (based on model index)
	 *  @param modelIndex model index
	 */
	public void setColorColumn (int modelIndex)
	{
		m_colorColumnIndex = modelIndex;
	}   //  setColorColumn

	/**
	 *  Set ColorColumn comparison criteria
	 *  @param dataCompare data
	 */
	public void setColorCompare (Object dataCompare)
	{
		m_colorDataCompare = dataCompare;
	}   //

	/**
	 *	Get ColorCode for Row.
	 *  <pre>
	 *	If numerical value in compare column is
	 *		negative = -1,
	 *      positive = 1,
	 *      otherwise = 0
	 *  If Timestamp
	 *  </pre>
	 * @param row row
	 * @return color code
	 */
	public int getColorCode (int row)
	{
		if (m_colorColumnIndex  == -1)
			return 0;

		Object data = getModel().getValueAt(row, m_colorColumnIndex);
		int cmp = 0;

		//	We need to have a Number
		if (data == null)
			return 0;
		try
		{
			if (data instanceof Timestamp)
			{
				if (m_colorDataCompare == null || !(m_colorDataCompare instanceof Timestamp))
					m_colorDataCompare = new Timestamp(System.currentTimeMillis());
				cmp = ((Timestamp)m_colorDataCompare).compareTo((Timestamp)data);
			}
			else
			{
				if (m_colorDataCompare == null || !(m_colorDataCompare instanceof BigDecimal))
					m_colorDataCompare = Env.ZERO;
				if (!(data instanceof BigDecimal))
					data = new BigDecimal(data.toString());
				cmp = ((BigDecimal)m_colorDataCompare).compareTo((BigDecimal)data);
			}
		}
		catch (Exception e)
		{
			return 0;
		}
		if (cmp > 0)
			return -1;
		if (cmp < 0)
			return 1;
		return 0;
	}   //  getColorCode
	

	/**
	 *  Set if Totals is Show
	 *  @param boolean Show
	 */
	public void setShowTotals(boolean show)
	{
		showTotals= show;
	}
	/**
	 *  get if Totals is Show
	 *  @param boolean Show
	 */
	public boolean getShowTotals()
	{
		return showTotals;
	}
	
	/**
	 *  Adding a new row with the totals
	 */
	public void addTotals(ColumnInfo[] layout)
	{
		if (getRowCount() == 0 || layout.length == 0)
			return;
		
		Object[] total = new Object[layout.length];
		
		for (int row = 0 ; row < getRowCount(); row ++)
		{

				for (int col = 0; col < layout.length; col++)
				{
					Object data = getModel().getValueAt(row, col);
					Class<?> c = layout[col].getColClass();
					if (c == BigDecimal.class)
					{	
						BigDecimal subtotal = Env.ZERO;
						if(total[col]!= null)
							subtotal = (BigDecimal)(total[col]);
							
						BigDecimal amt =  (BigDecimal) data;
						if(subtotal == null)
							subtotal = Env.ZERO;
						if(amt == null )
							amt = Env.ZERO;
						total[col] = subtotal.add(amt);
					}
					else if (c == Double.class)
					{
						Double subtotal = new Double(0);
						if(total[col] != null)
							subtotal = (Double)(total[col]);
						
						Double amt =  (Double) data;
						if(subtotal == null)
							subtotal = new Double(0);
						if(amt == null )
							subtotal = new Double(0);
						total[col] = subtotal + amt;
						
					}		
				}	
		}
		
		//adding total row

		int row = getRowCount() + 1;
		setRowCount(row);
		boolean symbol = false;  
		for (int col = 0; col < layout.length; col++)
		{
			Class<?> c = layout[col].getColClass();
			if (c == BigDecimal.class)
			{	
				setValueAt(total[col] , row - 1, col);
			}
			else if (c == Double.class)
			{
				setValueAt(total[col] , row -1 , col);
			}
			else
			{	
				if(col == 0 &&  symbol == false)
				{	
					setValueAt(" Σ  " , row -1 , col);
					symbol = true;
				}	
				else
					setValueAt(null , row - 1, col );	
			}	
			
		}
	}

	/**
	 *  Adding a new row with the totals
	 */
	public void addTotals(Info_Column[] layout)
	{
		if (getRowCount() == 0 || layout.length == 0)
			return;
		
		Object[] total = new Object[layout.length];
		
		for (int row = 0 ; row < getRowCount(); row ++)
		{

				for (int col = 0; col < layout.length; col++)
				{
					Object data = getModel().getValueAt(row, col);
					Class<?> c = layout[col].getColClass();
					if (c == BigDecimal.class)
					{	
						BigDecimal subtotal = Env.ZERO;
						if(total[col]!= null)
							subtotal = (BigDecimal)(total[col]);
							
						BigDecimal amt =  (BigDecimal) data;
						if(subtotal == null)
							subtotal = Env.ZERO;
						if(amt == null )
							amt = Env.ZERO;
						total[col] = subtotal.add(amt);
					}
					else if (c == Double.class)
					{
						Double subtotal = new Double(0);
						if(total[col] != null)
							subtotal = (Double)(total[col]);
						
						Double amt =  (Double) data;
						if(subtotal == null)
							subtotal = new Double(0);
						if(amt == null )
							subtotal = new Double(0);
						total[col] = subtotal + amt;
						
					}		
				}	
		}
		
		//adding total row

		int row = getRowCount() + 1;
		setRowCount(row);
		boolean symbol = true;
		for (int col = 0; col < layout.length; col++)
		{
			Class<?> c = layout[col].getColClass();
			if (c == BigDecimal.class)
			{	
				setValueAt(total[col] , row - 1, col);
			}
			else if (c == Double.class)
			{
				setValueAt(total[col] , row -1 , col);
			}
			else
			{	
				if(c == String.class && symbol)
				{	
					setValueAt(" Σ  " , row -1 , col );
					symbol = false;
				}	
				else
					setValueAt(null , row - 1, col );	
			}	
			
		}
	}
}   //  MiniTable
