/******************************************************************************
 * Product: Posterita Ajax UI 												  *
 * Copyright (C) 2007 Posterita Ltd.  All Rights Reserved.                    *
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
 * Posterita Ltd., 3, Draper Avenue, Quatre Bornes, Mauritius                 *
 * or via info@posterita.org or http://www.posterita.org/                     *
 *****************************************************************************/

package org.eevolution.grid;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Level;

import javax.script.ScriptEngine;

import org.adempiere.model.MBrowseField;
import org.adempiere.model.MViewColumn;
import org.adempiere.webui.component.ListHead;
import org.adempiere.webui.component.ListModelTable;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.event.TableValueChangeEvent;
import org.adempiere.webui.event.TableValueChangeListener;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.event.WTableModelListener;
import org.adempiere.webui.exception.ApplicationException;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.GridField;
import org.compiere.model.MRule;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.eevolution.form.WBrowserCallout;
import org.eevolution.form.WBrowser;
import org.eevolution.form.WBrowserRows;
import org.zkoss.zul.ListModel;

/**
 * Replacement for the Swing client minigrid component
 *
 * ZK Listbox extension for Adempiere Web UI.
 * The listbox contains a model and a renderer.
 * The model holds the underlying data objects, while the
 * renderer deals with displaying the data objects.
 * The renderer will render data objects using a variety of components.
 * These components can then be edited if they are not readonly.
 *
 * @author Andrew Kimball
 * @author Sendy Yagambrum
 */
public class WBrowseListbox extends Listbox implements IBrowseTable, TableValueChangeListener, WTableModelListener
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8717707799347994189L;

	/**	Logger. */
	private static CLogger logger = CLogger.getCLogger(WBrowseListbox.class);

	/** Model Index of Key Column.   */
	protected int m_keyColumnIndex = -1;

	/** List of R/W columns.     */
	private ArrayList<Integer> m_readWriteColumn = new ArrayList<Integer>();
	// TODO this duplicates other info held on columns. Needs rationalising.
	/** Layout set in prepareTable and used in loadTable.    */
	//private ColumnInfo[] m_layout = null;
	/** column class types (e.g. Boolean) */
	private ArrayList<Class> m_modelHeaderClass = new ArrayList<Class>();
	/** Color Column Index of Model.     */
	private int m_colorColumnIndex = -1;
	/** Color Column compare data.       */
	private Object m_colorDataCompare = Env.ZERO;
	
	
	protected WBrowserRows data= new WBrowserRows(this);

	protected WBrowser vbrowse; 
	
	/** Active BrowseCallOuts **/
	private List<String> activeCallouts = new ArrayList<String>();
	
	/** Active BrowseCallOutsInstances **/
	private List<WBrowserCallout> activeCalloutInstance = new ArrayList<WBrowserCallout>();
	
	/** Context **/
	private Properties ctx =Env.getCtx();   
	
	/** Multi Selection mode (default false) */
	private boolean     m_multiSelection = false;

	/** List of Column Width    */
	private ArrayList<Integer>   m_minWidth = new ArrayList<Integer>();
	
	private boolean showTotals = false;
	
	private List<GridField> gridFields = new ArrayList<GridField>();
	/**
	 * Default constructor.
	 *
	 * Sets a row renderer and an empty model
	 */
	public WBrowseListbox(WBrowser wbrowse)
	{
		super();
		WBrowseListItemRenderer rowRenderer = new WBrowseListItemRenderer(this);
	    rowRenderer.addTableValueChangeListener(this);

		setItemRenderer(rowRenderer);
		setModel(new ListModelTable());
		this.vbrowse = wbrowse;
	}

	/**
	 * Set the data model and column header names for the Listbox.
	 *
	 * @param model        The data model to assign to the table
	 * @param columnNames  The names of the table columns
	 */
	public void setData(ListModelTable model, List< ? extends String> columnNames)
	{
		WBrowseListItemRenderer rowRenderer = null;
		if (columnNames != null && columnNames.size() > 0)
		{
	    	//	 instantiate our custom row renderer
		    rowRenderer = new WBrowseListItemRenderer(this);

	    	// add listener for listening to component changes
	    	rowRenderer.addTableValueChangeListener(this);
		}
	    // assign the model and renderer
	    this.setModel(model);
	    if (rowRenderer != null)
	    {
	    	getModel().setNoColumns(columnNames.size());
	    	this.setItemRenderer(rowRenderer);

	    	//recreate listhead if needed
		    ListHead head = super.getListHead();
		    if (head != null)
		    {
		    	head.getChildren().clear();
		    	rowRenderer.renderListHead(head);
	    	}
	    }

	    // re-render
	    this.repaint();

	    return;
	}

    public void setModel(ListModel model)
    {
        super.setModel(model);
        if (model instanceof ListModelTable)
        {
            // TODO need to remove listener before adding, but how to do this without
            // causing ConcurrentModificationException
            //((ListModelTable)model).removeTableModelListener(this);
            ((ListModelTable)model).addTableModelListener(this);
        }
    }

	/**
	 * Create the listbox header by fetching it from the renderer and adding
	 * it to the Listbox.
	 *
	 */
	private void initialiseHeader()
	{
	    ListHead head = null;

	    head = super.getListHead();

	    //init only once
	    if (head != null)
	    {
	    	return;
	    }

	    head = new ListHead();

	    // render list head
	    if (this.getItemRenderer() instanceof WBrowseListItemRenderer)
	    {
	    	((WBrowseListItemRenderer)this.getItemRenderer()).renderListHead(head);
	    }
	    else
	    {
	    	throw new ApplicationException("Rendering of the ListHead is unsupported for "
	    			+ this.getItemRenderer().getClass().getSimpleName());
	    }

	    //attach the listhead
	    head.setParent(this);

	    return;
	}

	/**
	 *  Is the cell at the specified row and column editable?
	 *
	 *  @param row 		row index of cell
	 *  @param column 	column index of cell
	 *  @return true if cell is editable, false otherwise
	 */
	public boolean isCellEditable(int row, int column)
	{
		//  if the first column holds a boolean and it is false, it is not editable
		if (column != 0
			&& (getValueAt(row, 0) instanceof Boolean)
			&& !((Boolean)getValueAt(row, 0)).booleanValue())
		{
			return false;
		}

		//  is the column read/write?
		if (m_readWriteColumn.contains(new Integer(column)))
		{
			return true;
		}
		
		return false;
	}   //  isCellEditable

    /**
     * Returns the cell value at <code>row</code> and <code>column</code>.
     * <p>
     * <b>Note</b>: The column is specified in the table view's display
     *  order, and not in the <code>TableModel</code>'s column
     *	order.  This is an important distinction because as the
     *	user rearranges the columns in the table,
     *	the column at a given index in the view will change.
     *  Meanwhile the user's actions never affect the model's
     *  column ordering.
     *
     * @param   row    	the index of the row whose value is to be queried
     * @param   column  the index of the column whose value is to be queried
     * @return  the Object at the specified cell
     */
    public Object getValueAt(int row, int column)
    {
        return getModel().getDataAt(row, convertColumnIndexToModel(column));
    }

    /**
     * Return the <code>ListModelTable</code> associated with this table.
     *
     * @return The <code>ListModelTable</code> associated with this table.
     */
    public ListModelTable getModel()
    {
		if (super.getModel() instanceof ListModelTable)
		{
	    	return (ListModelTable)super.getModel();
		}
		else
		{
			throw new IllegalArgumentException("Model must be instance of " + ListModelTable.class.getName());
		}
    }

    /**
	 * Set the cell value at <code>row</code> and <code>column</code>.
	 *
	 * @param value		The value to set
     * @param row    	the index of the row whose value is to be set
     * @param column	the index of the column whose value is to be set
	 */
	public void setValueAt(Object value, int row, int column)
	{
		getModel().setDataAt(value, row, column);
	}
	 
    
    /**
	 * Set Value with BrowseField
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 15/10/2013, 10:02:04
	 * @param bField
	 * @param aValue
	 * @param row
	 * @param column
	 * @param index
	 * @return void
	 */
	public void setValueAt(MBrowseField bField,Object aValue, int row, int column,int index) {
		GridField gField=(GridField)data.getValue(row, index);
		GridField gf = null;
		
		if (gField==null)
		{
			gField=data.getBrowseField(index).getGridField();
			gf = new GridField(gField.getVO());
			gf.setValue(aValue, false);
			data.setValue(row, index, gf);
		}
		else
		{
			gField.setValue(aValue, false);
			data.setValue(row, index, gField);
		}

		if (gField.isDisplayed())
			setValueAt((gField.getDisplayType()==DisplayType.Date ||
						gField.getDisplayType()==DisplayType.DateTime ? 
								new Date(((Timestamp)aValue).getTime()) : aValue)  	
			, row, column);
			
			
		
	}
	
	/**
	 * Set Value On Table And BrowseRows
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 21/10/2013, 12:00:51
	 * @param gField
	 * @param aValue
	 * @param row
	 * @param column
	 * @return void
	 */
	public void setValueAt(GridField gField,Object aValue, int row, int column) {
		// TODO Auto-generated method stub
		
		if (gField==null)
			throw new UnsupportedOperationException("No GridField");
		
		GridField gf = new GridField(gField.getVO());
		gf.setValue(aValue, false);
		data.setValue(row, data.getTableIndex(column), gf);
		
		if (gField.isDisplayed())
			setValueAt(
					(gField.getDisplayType()==DisplayType.Date ||
					gField.getDisplayType()==DisplayType.DateTime ? 
							(Date)aValue : aValue)
					, row, column);
	}//setValueAt

    /**
     * Convert the index for a column from the display index to the
     * corresponding index in the underlying model.
     * <p>
     * This is unused for this implementation because the column ordering
     * cannot be dynamically changed.
     *
     * @param   viewColumnIndex     the index of the column in the view
     * @return  the index of the corresponding column in the model
     */
    public int convertColumnIndexToModel(int viewColumnIndex)
    {
    	return viewColumnIndex;
    }

    /**
	 *  Set Column at the specified <code>index</code> to read-only or read/write.
	 *
	 *  @param index 	index of column to set as read-only (or not)
	 *  @param readOnly Read only value. If <code>true</code> column is read only,
	 *  				if <code>false</code> column is read-write
	 */
	public void setColumnReadOnly (int index, boolean readOnly)
	{
		Integer indexObject = new Integer(index);

		//  Column is ReadWrite
		if (m_readWriteColumn.contains(indexObject))
		{
			//  Remove from list
			if (readOnly)
			{
				m_readWriteColumn.remove(indexObject);
			}   //  ReadOnly
		}
		//  current column is R/O - ReadWrite - add to list
		else if (!readOnly)
		{
			m_readWriteColumn.add(indexObject);
		}

		return;
	}   //  setColumnReadOnly

	/*
	public String prepareTable(ColumnInfo[] layout,
							String from,
							String where,
							boolean multiSelection,
							String tableName)
	{
		return prepareTable(layout, from, where, multiSelection, tableName, true);
	}   //  prepareTable
	*/
    /*public String prepareTable(ColumnInfo[] layout,
            String from,
            String where,
            boolean multiSelection,
            String tableName,boolean addAccessSQL)
    {
        int columnIndex = 0;
        StringBuffer sql = new StringBuffer ("SELECT ");
        setLayout(layout);

        clearColumns();

        setMultiSelection(multiSelection);

        //  add columns & sql
        for (columnIndex = 0; columnIndex < layout.length; columnIndex++)
        {
            //  create sql
            if (columnIndex > 0)
            {
                sql.append(", ");
            }
            sql.append(layout[columnIndex].getColSQL());

            //  adding ID column
            if (layout[columnIndex].isKeyPairCol())
            {
                sql.append(",").append(layout[columnIndex].getKeyPairColSQL());
            }

            //  add to model
            addColumn(layout[columnIndex].getColHeader());

            // set the colour column
            if (layout[columnIndex].isColorColumn())
            {
                setColorColumn(columnIndex);
            }
            if (layout[columnIndex].getColClass() == IDColumn.class)
            {
                m_keyColumnIndex = columnIndex;
            }
        }

        //  set editors (two steps)
        for (columnIndex = 0; columnIndex < layout.length; columnIndex++)
        {
            setColumnClass(columnIndex,
                        layout[columnIndex].getColClass(),
                        layout[columnIndex].isReadOnly(),
                        layout[columnIndex].getColHeader());
        }

        sql.append( " FROM ").append(from);
        sql.append(" WHERE ").append(where);

        if (from.length() == 0)
        {
            return sql.toString();
        }
        //
        if (addAccessSQL)
        {
            String finalSQL = MRole.getDefault().addAccessSQL(sql.toString(),
                                                        tableName,
                                                        MRole.SQL_FULLYQUALIFIED,
                                                        MRole.SQL_RO);

            logger.finest(finalSQL);

            return finalSQL;
        }
        else
        {
            return sql.toString();
        }
    }   // prepareTable
    */

	public String prepareTable(List<MBrowseField> fields, boolean multiSelection)
	{
		StringBuffer sql = new StringBuffer("");
		m_multiSelection = multiSelection;
		clearColumns();
		int col = 0;
		//  Add columns & sql
		for (MBrowseField field : fields)
		{
			MViewColumn columnView = field.getAD_View_Column();
			//  create sql
			if (col > 0 && columnView.getColumnSQL().length() > 0)
				sql.append(", ");

			if (field.isKey())
				setKey(col);

			sql.append(columnView.getColumnSQL())
					.append(" ")
					.append("AS")
					.append(" ")
					.append(columnView.getColumnName());

			data.addBrowseField(col, field);
			field.setGridField(new GridField(data.getGridFieldVO(vbrowse.p_WindowNo, field.getName(), col)));
			if (field.isDisplayed()){
				addColumn(field.getName());
				col++;
			}
		}

		col = 0;
		for (MBrowseField field : fields)
		{
			if (field.isDisplayed()){
				setColumnClass(col,
						field.getGridField(),
						field.getAD_Reference_ID(),
						field.isReadOnly(),
						field.getName());
				col++;
			}
		}

		return sql.toString();
	}
	/**
	 * Clear the table columns from both the model and renderer
	 */
	private void clearColumns()
	{
		((WBrowseListItemRenderer)getItemRenderer()).clearColumns();
		getModel().setNoColumns(0);

		return;
	}


	/**
	 *  Add Table Column and specify the column header.
	 *
	 *  @param header	name of column header
	 */
	public void addColumn (String header)
	{
		WBrowseListItemRenderer renderer = (WBrowseListItemRenderer)getItemRenderer();
		renderer.addColumn(Util.cleanAmp(header));
		getModel().addColumn();

		return;
	}   //  addColumn

	/**
	 * Set the attributes of the column.
	 * @param index		The index of the column to be modified
	 * @param classType	The class of data that the column will contain
	 * @param readOnly	Whether the data in the column is read only
	 * @param header	The header text for the column
	 */
	public void setColumnClass (int index, Class classType, boolean readOnly, String header)
	{
		WBrowseListItemRenderer renderer = (WBrowseListItemRenderer)getItemRenderer();

		setColumnReadOnly(index, readOnly);

		renderer.setColumnHeader(index, header);

		renderer.setColumnClass(index, classType);

		if (index < m_modelHeaderClass.size())
			m_modelHeaderClass.set(index, classType);
		else
			m_modelHeaderClass.add(classType);

 		return;
	}


    /**
     * Set the attributes of the column.
     *
     * @param index     The index of the column to be modified
     * @param classType The class of data that the column will contain
     * @param readOnly  Whether the data in the column is read only
     *
     * @see #setColumnClass(int, Class, boolean, String)
     */
    /*public void setColumnClass (int index, Class classType, boolean readOnly)
    {
        setColumnReadOnly(index, readOnly);

        WBrowseListItemRenderer renderer = (WBrowseListItemRenderer)getItemRenderer();

        renderer.setColumnClass(index, classType);

        m_modelHeaderClass.add(classType);

        return;
    }*/

	/**
	 * Set the attributes of the column.
	 *
	 * @param classType	The class of data that the column will contain
	 * @param readOnly	Whether the data in the column is read only
	 * @param header	The header text for the column
	 *
	 * @see #setColumnClass(int, Class, boolean)
	 * @see #addColumn(String)
	 */
	/*public void addColumn(Class classType, boolean readOnly, String header)
	{
		m_modelHeaderClass.add(classType);

		setColumnReadOnly(m_modelHeaderClass.size() - 1, readOnly);

		addColumn(header);

		WBrowseListItemRenderer renderer = (WBrowseListItemRenderer)getItemRenderer();
		renderer.setColumnClass((renderer.getNoColumns() - 1), classType);

 		return;
	}*/

	/**
	 *	Set the Column to determine the color of the row (based on model index).
	 *  @param modelIndex 	the index of the column used to decide the colour
	 */
	public void setColorColumn (int modelIndex)
	{
		m_colorColumnIndex = modelIndex;
	}   //  setColorColumn

	/**
	 *	Load Table from ResultSet - The ResultSet is not closed.
	 *
	 *  @param rs 	ResultSet containing data t enter int the table.
	 *  			The contents must conform to the column layout defined in
	 *  			{@link #prepareTable(ColumnInfo[], String, String, boolean, String)}
	 */
	/*public void loadTable(ResultSet rs)
	{
		int no = 0;
		int row = 0; // model row
		int col = 0; // model column
		Object data = null;
		int rsColIndex = 0; // index into result set
		int rsColOffset = 1;  //  result set columns start with 1
		Class columnClass; // class of the column

		if (getLayout() == null)
		{
			throw new UnsupportedOperationException("Layout not defined");
		}

		clearTable();

		try
		{
			while (rs.next())
			{
				row = getItemCount();
				setRowCount(row + 1);
				rsColOffset = 1;
				no++;
				for (col = 0; col < m_layout.length; col++)
				{
					//reset the data value
					data=null;
					columnClass = m_layout[col].getColClass();
					rsColIndex = col + rsColOffset;

					if (isColumnClassMismatch(col, columnClass))
					{
						throw new ApplicationException("Cannot enter a " + columnClass.getName()
								+ " in column " + col + ". " +
								"An object of type " + m_modelHeaderClass.get(col).getSimpleName()
								+ " was expected.");
					}
					
					if (columnClass == IDColumn.class && !m_layout[col].getColSQL().equals("'Row' AS \"Row\""))
					{
						data = new IDColumn(rs.getInt(rsColIndex));
					}
					else if (columnClass == IDColumn.class && m_layout[col].getColSQL().equals("'Row' AS \"Row\""))
					{	
						data = new IDColumn(no);
					}	
					else if (columnClass == Boolean.class)
					{
						data = new Boolean(rs.getString(rsColIndex).equals("Y"));
					}
					else if (columnClass == Timestamp.class)
					{
						data = rs.getTimestamp(rsColIndex);
					}
					else if (columnClass == BigDecimal.class)
					{
						data = rs.getBigDecimal(rsColIndex);
					}
					else if (columnClass == Double.class)
					{
						data = new Double(rs.getDouble(rsColIndex));
					}
					else if (columnClass == Integer.class)
					{
						data = new Integer(rs.getInt(rsColIndex));
					}
					else if (columnClass == KeyNamePair.class)
					{
						// TODO factor out this generation
						String display = rs.getString(rsColIndex);
						int key = rs.getInt(rsColIndex + 1);
						data = new KeyNamePair(key, display);
						rsColOffset++;
					}
					else
					{
						// TODO factor out this cleanup
						String s = rs.getString(rsColIndex);
						if (s != null)
						{
							data = s.trim();	//	problems with NCHAR
						}
						else
						{
							data=null;
						}
					}
					//  store in underlying model
					getModel().setDataAt(data, row, col);
				}
			}
		}
		catch (SQLException exception)
		{
			logger.log(Level.SEVERE, "", exception);
		}
		// TODO implement this
		//autoSize();

		// repaint the table
		this.repaint();

		logger.config("Row(rs)=" + getRowCount());

		return;
	}	//	loadTable
	 */
	/**
	 * @param col
	 * @param columnClass
	 * @return
	 */
	private boolean isColumnClassMismatch(int col, Class columnClass)
	{
		return !columnClass.equals(m_modelHeaderClass.get(col));
	}

	/**
	 *	Load Table from Object Array.
	 *  @param pos array of Persistent Objects
	 */
	/*
	public void loadTable(PO[] pos)
	{
		int row = 0;
		int col = 0;
		int poIndex = 0; // index into the PO array
		String columnName;
		Object data;
		Class columnClass;

		if (m_layout == null)
		{
			throw new UnsupportedOperationException("Layout not defined");
		}

		//  Clear Table
		clearTable();

		for (poIndex = 0; poIndex < pos.length; poIndex++)
		{
			PO myPO = pos[poIndex];
			row = getRowCount();
			setRowCount(row + 1);

			for (col = 0; col < m_layout.length; col++)
			{
				columnName = m_layout[col].getColSQL();
				data = myPO.get_Value(columnName);
				if (data != null)
				{
					columnClass = m_layout[col].getColClass();

					if (isColumnClassMismatch(col, columnClass))
					{
						throw new ApplicationException("Cannot enter a " + columnClass.getName()
								+ " in column " + col + ". " +
								"An object of type " + m_modelHeaderClass.get(col).getSimpleName()
								+ " was expected.");
					}

					if (columnClass == IDColumn.class)
					{
						data = new IDColumn(((Integer)data).intValue());
					}
					else if (columnClass == Double.class)
					{
						data = new Double(((BigDecimal)data).doubleValue());
					}
				}
				//  store
				getModel().setDataAt(data, row, col);
			}
		}
		// TODO implement this
		//autoSize();

		// repaint the table
		this.repaint();

		logger.config("Row(array)=" + getRowCount());

		return;
	}	//	loadTable
	*/
	/**
	 * Clear the table components.
	 */
	public void clear()
	{
		this.getChildren().clear();
	}
	/**
	 *  Get the key of currently selected row based on layout defined in
	 *  @return ID if key
	 */
	public Integer getSelectedRowKey()
	{
		int row = 0;
		final int noSelection = -1;
		final int noIndex = -1;
		Object data;

		if (this.data.getColumnCount() == 0)
		{
			throw new UnsupportedOperationException("Layout not defined");
		}

		row = getSelectedRow();

		// TODO factor out the two parts of this guard statement
		if (row != noSelection && m_keyColumnIndex != noIndex)
		{
			data = getModel().getDataAt(row, m_keyColumnIndex);

			if (data instanceof IDColumn)
			{
				data = ((IDColumn)data).getRecord_ID();
			}
			if (data instanceof Integer)
			{
				return (Integer)data;
			}
		}

		return null;
	}   //  getSelectedRowKey


	/**
     * Returns the index of the first selected row, -1 if no row is selected.
     *
     * @return the index of the first selected row
     */
    public int getSelectedRow()
    {
    	return this.getSelectedIndex();
    }

	/**
	 *  Set the size of the underlying data model.
	 *
	 *  @param rowCount	number of rows
	 */
	public void setRowCount (int rowCount)
	{
		getModel().setNoRows(rowCount);

		return;
	}   //  setRowCount

	/**
	 *  Get Layout.
	 *
	 *  @return Array of ColumnInfo
	 */
	/*
	public ColumnInfo[] getLayoutInfo()
	{
		return getLayout();
	}   //  getLayout
	*/
	/**
	 * Removes all data stored in the underlying model.
	 *
	 */
	public void clearTable()
	{
		WBrowseListItemRenderer renderer = null;

		// First clear the model
		getModel().clear();

		// Then the renderer
		if (getItemRenderer() instanceof WBrowseListItemRenderer)
		{
			renderer = (WBrowseListItemRenderer)getItemRenderer();
			renderer.clearSelection();
		}
		else
		{
			throw new IllegalArgumentException("Renderer must be instance of WBrowseListItemRenderer");
		}

		return;
	}


    /**
     * Get  the number of rows in this table's model.
     *
     * @return the number of rows in this table's model
     *
     */
    public int getRowCount()
    {
        return getModel().getSize();
    }


	/**
	 *  Set whether or not multiple rows can be selected.
	 *
	 *  @param multiSelection are multiple selections allowed
	 */
	public void setMultiSelection(boolean multiSelection)
	{
		this.setMultiple(multiSelection);

		return;
	}   //  setMultiSelection


	/**
	 *  Query whether multiple rows can be selected in the table.
	 *
	 *  @return true if multiple rows can be selected
	 */
	public boolean isMultiSelection()
	{
		return this.isMultiple();
	}   //  isMultiSelection

	/**
	 *  Set ColorColumn comparison criteria.
	 *
	 *  @param dataCompare object encapsualating comparison criteria
	 */
	public void setColorCompare (Object dataCompare)
	{
		m_colorDataCompare = dataCompare;

		return;
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
		// TODO expose these through interface
		// (i.e. make public class member variables)
		final int valPositive = 1;
		final int valNegative = -1;
		final int valOtherwise = 0;
		Object data;
		int cmp = valOtherwise;

		if (m_colorColumnIndex  == -1)
		{
			return valOtherwise;
		}

		data = getModel().getDataAt(row, m_colorColumnIndex);

		//	We need to have a Number
		if (data == null)
		{
			return valOtherwise;
		}

		try
		{
			if (data instanceof Timestamp)
			{
				if ((m_colorDataCompare == null)
					|| !(m_colorDataCompare instanceof Timestamp))
				{
					m_colorDataCompare = new Timestamp(System.currentTimeMillis());
				}
				cmp = ((Timestamp)m_colorDataCompare).compareTo((Timestamp)data);
			}
			else
			{
				if ((m_colorDataCompare == null)
					|| !(m_colorDataCompare instanceof BigDecimal))
				{
					m_colorDataCompare = Env.ZERO;
				}
				if (!(data instanceof BigDecimal))
				{
					data = new BigDecimal(data.toString());
				}
				cmp = ((BigDecimal)m_colorDataCompare).compareTo((BigDecimal)data);
			}
		}
		catch (Exception exception)
		{
			return valOtherwise;
		}

		if (cmp > 0)
		{
			return valNegative;
		}
		else if (cmp < 0)
		{
			return valPositive;
		}

		return valOtherwise;
	}   //  getColorCode

	/* (non-Javadoc)
	 * @see org.adempiere.webui.event.TableValueChangeListener#tableValueChange
	 * 		(org.adempiere.webui.event.TableValueChangeEvent)
	 */
	public void tableValueChange(TableValueChangeEvent event)
	{
		int col = event.getColumn(); // column of table field which caused the event
		int row = event.getRow(); // row of table field which caused the event
		boolean newBoolean;
		IDColumn idColumn;

		// if the event was caused by an ID Column and the value is a boolean
		// then set the IDColumn's select field
		if (col >= 0 && row >=0)
		{
			if (this.getValueAt(row, col) instanceof IDColumn
				&& event.getNewValue() instanceof Boolean)
			{
				newBoolean = ((Boolean)event.getNewValue()).booleanValue();
				idColumn = (IDColumn)this.getValueAt(row, col);
				idColumn.setSelected(newBoolean);
				this.setValueAt(idColumn, row, col);
			}
			// othewise just set the value in the model to the new value
			else
			{
				
				GridField gfield = (GridField)data.getValue(row, data.getTableIndex(col));
				setValueAt(gfield, event.getNewValue(), row, col);
				if (gfield.getCallout()!=null){
					
					processCallout(gfield,event.getNewValue(),event.getOldValue(),row,col);
				}
			}
		}

		return;
	}


	/**
	 * Repaint the Table.
	 */
	public void repaint()
	{
	    // create the head
	    initialiseHeader();

	    // this causes re-rendering of the Listbox
		this.setModel(this.getModel());

		return;
	}

    /**
     * Get the table layout.
     *
     * @return the layout of the table
     * @see #setLayout(ColumnInfo[])
     */
	/*public ColumnInfo[] getLayout()
	{
		return m_layout;
	}*/

	/**
	 * Set the column information for the table.
	 *
	 * @param layout	The new layout to set for the table
	 * @see #getLayout()
	 */
	/*private void setLayout(ColumnInfo[] layout)
	{
		this.m_layout = layout;
		getModel().setNoColumns(m_layout.length);

		return;
	}*/

    /**
     * Respond to a change in the table's model.
     *
     * If the event indicates that the entire table has changed, the table is repainted.
     *
     * @param event The event fired to indicate a change in the table's model
     */
    public void tableChanged(WTableModelEvent event)
    {
        if ((event.getType() == WTableModelEvent.CONTENTS_CHANGED)
                && (event.getColumn() == WTableModelEvent.ALL_COLUMNS)
                && (event.getFirstRow() == WTableModelEvent.ALL_ROWS))
        {
            this.repaint();
        }
        else if ((event.getType() == WTableModelEvent.CONTENTS_CHANGED)
        		&& event.getFirstRow() != WTableModelEvent.ALL_ROWS
        		&& !m_readWriteColumn.isEmpty())
        {
        	int[] indices = this.getSelectedIndices();
        	ListModelTable model = this.getModel();
        	if (event.getLastRow() > event.getFirstRow())
        		model.updateComponent(event.getFirstRow(), event.getLastRow());
        	else
        		model.updateComponent(event.getFirstRow());
        	if (indices != null && indices.length > 0)
        	{
        		this.setSelectedIndices(indices);
        	}
        }

        return;
    }

    /**
     * no op, to ease porting of swing form
     */
	public void autoSize() {
		//no op
	}

	public int getColumnCount() {
		return getModel() != null ? getModel().getNoColumns() : 0;
	}
	
	public int getKeyColumnIndex() {
		return m_keyColumnIndex;
	}


	/**
	 * Set Key index From Table
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 21/10/2013, 11:59:42
	 * @param col
	 * @return void
	 */
	private void setKey(int col)
	{
		m_keyColumnIndex = col;
		vbrowse.m_keyColumnIndex=col;
	}//setKey
	
	public void setColumnClass (int index, GridField gField, int displayType ,boolean readOnly, String header)
	{
	//	log.config( "MiniTable.setColumnClass - " + index, c.getName() + ", r/o=" + readOnly);
		
		WBrowseListItemRenderer renderer = (WBrowseListItemRenderer)getItemRenderer();
		
		renderer.setColumnHeader(index, header);
		
		//  Set R/O
		setColumnReadOnly(index, readOnly);

		// repaint the table
		this.repaint();
		
	}   //  setColumnClass

	

	/**
	 *  Set if Totals is Show
	 * @param show
	 */
	public void setShowTotals(boolean show)
	{
		showTotals= show;
	}

	/**
	 * Shoe Totals
	 * @return
	 */
	public boolean getShowTotals()
	{
		return showTotals;
	}

	/**
	 *  Adding a new row with the totals
	 */
	public void addTotals()
	{
		if (getRowCount() == 0 || this.data.getViewColumns() == 0)
			return;

		Object[] total = new Object[this.data.getViewColumns()];

		for (int row = 0 ; row < getRowCount(); row ++){
			for (int col = 0; col < this.data.getViewColumns(); col++){
				Object data = getModel().getValueAt(row, col);
				int ReferenceType = this.data.getBrowseField(this.data.getTableIndex(col)).getAD_Reference_ID();
				//if (c == BigDecimal.class)
				if(DisplayType.isNumeric(ReferenceType)){
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
			}
		}

		//adding total row

		int row = getRowCount() + 1;
		setRowCount(row);
		for (int col = 0; col < this.data.getViewColumns(); col++)
		{
			MBrowseField bField = this.data.getBrowseField(this.data.getTableIndex(col));
			if(DisplayType.isNumeric(bField.getAD_Reference_ID()))
				setValueAt(bField.getGridField() ,total[col] , row - 1, col);
			else{
				if(bField.isKey())
					setValueAt(" Σ  " , row -1 , col);
				else
					setValueAt(null , row - 1, col );
			}

		}
	}


	public List<GridField> getGridFields() {
		return gridFields;
	}
	
	/**
	 * Get Browse Rows Data 
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 15/10/2013, 10:01:47
	 * @return
	 * @return BrowserRows
	 */
	public WBrowserRows getData() {
		return data;
	}
	
	/**************************************************************************
	 *  Carlos Parada
	 *  Adapted for Browse Callouts
	 *  Process Callout(s) Adapted.
	 *  <p>
	 *  The Callout is in the string of
	 *  "class.method;class.method;"
	 * If there is no class name, i.e. only a method name, the class is regarded
	 * as CalloutSystem.
	 * The class needs to comply with the Interface Callout.
	 *
	 * For a limited time, the old notation of Sx_matheod / Ux_menthod is maintained.
	 *
	 * @param field field
	 * @return error message or ""
	 * @see org.compiere.model.Callout
	 */
	public String processCallout (GridField field,Object value,Object oldValue, int currentRow,int currentColumn )
	{
		String callout = field.getCallout();
		if (callout.length() == 0)
			return "";


		//Object value = field.getValue();
		//Object oldValue = field.getOldValue();
		logger.fine(field.getColumnName() + "=" + value
			+ " (" + callout + ") - old=" + oldValue);

		StringTokenizer st = new StringTokenizer(callout, ";,", false);
		while (st.hasMoreTokens())      //  for each callout
		{
			String cmd = st.nextToken().trim();
			
			//detect infinite loop
			if (activeCallouts.contains(cmd)) continue;
			
			String retValue = "";
			// FR [1877902]
			// CarlosRuiz - globalqss - implement beanshell callout
			// Victor Perez  - vpj-cd implement JSR 223 Scripting
			if (cmd.toLowerCase().startsWith(MRule.SCRIPT_PREFIX)) {
				
				MRule rule = MRule.get(ctx, cmd.substring(MRule.SCRIPT_PREFIX.length()));
				if (rule == null) {
					retValue = "Callout " + cmd + " not found"; 
					logger.log(Level.SEVERE, retValue);
					return retValue;
				}
				if ( !  (rule.getEventType().equals(MRule.EVENTTYPE_Callout) 
					  && rule.getRuleType().equals(MRule.RULETYPE_JSR223ScriptingAPIs))) {
					retValue = "Callout " + cmd
						+ " must be of type JSR 223 and event Callout"; 
					logger.log(Level.SEVERE, retValue);
					return retValue;
				}

				ScriptEngine engine = rule.getScriptEngine();

				// Window context are    W_
				// Login context  are    G_
				MRule.setContext(engine, ctx, vbrowse.p_WindowNo);
				// now add the callout parameters windowNo, tab, field, value, oldValue to the engine 
				// Method arguments context are A_
				engine.put(MRule.ARGUMENTS_PREFIX + "WindowNo", vbrowse.p_WindowNo);
				engine.put(MRule.ARGUMENTS_PREFIX + "Tab", this);
				engine.put(MRule.ARGUMENTS_PREFIX + "Field", field);
				engine.put(MRule.ARGUMENTS_PREFIX + "Value", value);
				engine.put(MRule.ARGUMENTS_PREFIX + "OldValue", oldValue);
				engine.put(MRule.ARGUMENTS_PREFIX + "currentRow", currentRow);
				engine.put(MRule.ARGUMENTS_PREFIX + "currentColumn", currentColumn);
				engine.put(MRule.ARGUMENTS_PREFIX + "Ctx", ctx);

				try 
				{
					activeCallouts.add(cmd);
					retValue = engine.eval(rule.getScript()).toString();
				}
				catch (Exception e)
				{
					logger.log(Level.SEVERE, "", e);
					retValue = 	"Callout Invalid: " + e.toString();
					return retValue;
				}
				finally
				{
					activeCallouts.remove(cmd);
				}
				
			} else {

				WBrowserCallout call = null;
				String method = null;
				int methodStart = cmd.lastIndexOf('.');
				try
				{
					if (methodStart != -1)      //  no class
					{
						Class<?> cClass = Class.forName(cmd.substring(0,methodStart));
						call = (WBrowserCallout)cClass.newInstance();
						method = cmd.substring(methodStart+1);
					}
				}
				catch (Exception e)
				{
					logger.log(Level.SEVERE, "class", e);
					return "Callout Invalid: " + cmd + " (" + e.toString() + ")";
				}

				if (call == null || method == null || method.length() == 0)
					return "Callout Invalid: " + method;

				try
				{
					activeCallouts.add(cmd);
					activeCalloutInstance.add(call);
					retValue = call.start(ctx, method, vbrowse.p_WindowNo, data, field, value, oldValue,currentRow,currentColumn);
				}
				catch (Exception e)
				{
					logger.log(Level.SEVERE, "start", e);
					retValue = 	"Callout Invalid: " + e.toString();
					return retValue;
				}
				finally
				{
					activeCallouts.remove(cmd);
					activeCalloutInstance.remove(call);
				}
				
			}			
			
			if (!Util.isEmpty(retValue))		//	interrupt on first error
			{
				logger.severe (retValue);
				return retValue;
			}
		}   //  for each callout
		return "";
	}	//	processCallout
}
