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

package org.adempiere.webui.component;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.webui.event.TableValueChangeEvent;
import org.adempiere.webui.event.TableValueChangeListener;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.event.WTableModelListener;
import org.adempiere.webui.exception.ApplicationException;
import org.compiere.apps.search.Info_Column;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.IMiniTable;
import org.compiere.minigrid.MiniTable;
import org.compiere.model.MRole;
import org.compiere.model.MSysConfig;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Util;
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
 * 
 * ADEMPIERE-72 - Info Panel improvements
 * ADEMPIERE-41 - GL Reconciliation integration - added hidden columns
 * @author Michael McKay

 */
public class WListbox extends Listbox implements IMiniTable, TableValueChangeListener, WTableModelListener
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8717707799347994189L;

	public static final String SYSCONFIG_INFO_DEFAULTSELECTED = "INFO_DEFAULTSELECTED";
	public static final String SYSCONFIG_INFO_DOUBLECLICKTOGGLESSELECTION = "INFO_DOUBLECLICKTOGGLESSELECTION";

	/**	Logger. */
	private static CLogger logger = CLogger.getCLogger(MiniTable.class);

	/** Model Index of Key Column.   */
	protected int m_keyColumnIndex = -1;

	/** List of R/W columns.     */
	private ArrayList<Integer> m_readWriteColumn = new ArrayList<Integer>();
	// TODO this duplicates other info held on columns. Needs rationalising.
	/** Layout set in prepareTable and used in loadTable.    */
	private ColumnInfo[] m_layout = null;
	/** column class types (e.g. Boolean) */
	private ArrayList<Class> m_modelHeaderClass = new ArrayList<Class>();
	/** Color Column Index of Model.     */
	private int m_colorColumnIndex = -1;
	/** Color Column compare data.       */
	private Object m_colorDataCompare = Env.ZERO;

	/** Specify if the records should be checked(selected) by default (multi selection mode only) */
	private boolean				p_isDefaultSelected = MSysConfig.getBooleanValue(SYSCONFIG_INFO_DEFAULTSELECTED, false, Env.getAD_Client_ID(Env.getCtx()));
	/** Is Total Show */
	private boolean showTotals = false;
	private boolean autoResize = true;

	public boolean isAutoResize() {
		return autoResize;
	}

	public void setAutoResize(boolean autoResize) {
		this.autoResize = autoResize;
	}

	/**
	 * Default constructor.
	 *
	 * Sets a row renderer and an empty model
	 */
	public WListbox()
	{
		super();
		WListItemRenderer rowRenderer = new WListItemRenderer();
	    rowRenderer.addTableValueChangeListener(this);

		setItemRenderer(rowRenderer);
		setModel(new ListModelTable());
	}

	/**
	 * Set the data model and column header names for the Listbox.
	 *
	 * @param model        The data model to assign to the table
	 * @param columnNames  The names of the table columns
	 */
	public void setData(ListModelTable model, List< ? extends String> columnNames)
	{
		WListItemRenderer rowRenderer = null;
		if (columnNames != null && columnNames.size() > 0)
		{
	    	//	 instantiate our custom row renderer
		    rowRenderer = new WListItemRenderer(columnNames);

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

	    autoSize();
		if(getShowTotals())
			addTotals(m_layout);
		
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
	    if (this.getItemRenderer() instanceof WListItemRenderer)
	    {
	    	((WListItemRenderer)this.getItemRenderer()).renderListHead(head);
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
		getModel().setDataAt(value, row, convertColumnIndexToModel(column));
		if(value instanceof IDColumn)
		{
			IDColumn id = (IDColumn) value;
			boolean selected = id.isSelected();
			ListItem listItem = this.getItemAtIndex(row);
			
			if (listItem != null && !listItem.isSelected() && selected) {
				listItem.setSelected(true);
			}
		}
	}

    /**
     * Convert the index for a column from the display index to the
     * corresponding index in the underlying model.
     * <p>
     * This is unused for this implementation because the column ordering
     * cannot be dynamically changed.
     *
     * @param   viewColumnIndex     the index of the column in the view
     * @return  the index of the corresponding column in the model
     *
     * @see #convertColumnIndexToVi
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


	/**
	 *  Prepare Table and return SQL required to get resultset to
	 *  populate table.
	 *
	 *  @param layout    		array of column info
	 *  @param from      		SQL FROM content
	 *  @param where     		SQL WHERE content
	 *  @param multiSelection 	multiple selections
	 *  @param tableName 		table name
	 *  @return SQL statement to use to get resultset to populate table
	 */
	public String prepareTable(ColumnInfo[] layout,
							String from,
							String where,
							boolean multiSelection,
							String tableName)
	{
		return prepareTable(layout, from, where, multiSelection, tableName, true);
	}   //  prepareTable

    /**
     *  Prepare Table and return SQL required to get resultset to
     *  populate table
     *
     * @param layout            array of column info
     * @param from              SQL FROM content
     * @param where             SQL WHERE content
     * @param multiSelection    multiple selections
     * @param tableName         multiple selections
     * @param addAccessSQL      specifies whether to addAcessSQL
     * @return  SQL statement to use to get resultset to populate table
     */
    public String prepareTable(ColumnInfo[] layout,
            String from,
            String where,
            boolean multiSelection,
            String tableName,boolean addAccessSQL)
    {
        int columnIndex = 0;
        StringBuffer sql = new StringBuffer ("SELECT ");
        setLayout(layout);

        clearColumns();
        setColorColumn(-1); // No color column assigned.
        
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
            addColumn(layout[columnIndex]);

            // set the colour column
            if (layout[columnIndex].isColorColumn())
            {
                setColorColumn(columnIndex);
            }
            if (layout[columnIndex].getColClass() == IDColumn.class)
            {
                setKeyColumnIndex(columnIndex);
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

	/**
	 * Clear the table columns from both the model and renderer
	 */
	private void clearColumns()
	{
		((WListItemRenderer)getItemRenderer()).clearColumns();
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
		WListItemRenderer renderer = (WListItemRenderer)getItemRenderer();
		renderer.addColumn(Util.cleanAmp(header));
		getModel().addColumn();

		return;
	}   //  addColumn

	/**
	 *  Add Table Column and specify the column header.
	 *
	 *  @param info	ColumInfo class for the column
	 */
	public void addColumn (ColumnInfo info)
	{
		WListItemRenderer renderer = (WListItemRenderer)getItemRenderer();
		renderer.addColumn(info);
		getModel().addColumn();

		return;
	}   //  addColumn

	/**
	 * Set the attributes of the column.
	 *
	 * @param index		The index of the column to be modified
	 * @param classType	The class of data that the column will contain
	 * @param readOnly	Whether the data in the column is read only
	 * @param header	The header text for the column
	 *
	 * @see #setColumnClass(int, Class, boolean)
	 */
	public void setColumnClass (int index, Class classType, boolean readOnly, String header)
	{
		WListItemRenderer renderer = (WListItemRenderer)getItemRenderer();

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
    public void setColumnClass (int index, Class classType, boolean readOnly)
    {
        setColumnReadOnly(index, readOnly);

        WListItemRenderer renderer = (WListItemRenderer)getItemRenderer();

        renderer.setColumnClass(index, classType);

        m_modelHeaderClass.add(classType);

        return;
    }

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
	public void addColumn(Class classType, boolean readOnly, String header)
	{
		m_modelHeaderClass.add(classType);

		setColumnReadOnly(m_modelHeaderClass.size() - 1, readOnly);

		addColumn(header);

		WListItemRenderer renderer = (WListItemRenderer)getItemRenderer();
		renderer.setColumnClass((renderer.getNoColumns() - 1), classType);

 		return;
	}

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
	public void loadTable(ResultSet rs)
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
						data = rs.getString(rsColIndex) == null ?  new Boolean(false) : new Boolean(rs.getString(rsColIndex).equals("Y"));
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
		
		autoSize();
		if(getShowTotals())
			addTotals(m_layout);
		
		// repaint the table
		this.repaint();

		logger.config("Row(rs)=" + getRowCount());

		return;
	}	//	loadTable

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

		autoSize();
		if(getShowTotals())
			addTotals(m_layout);

		// repaint the table
		this.repaint();

		logger.config("Row(array)=" + getRowCount());

		return;
	}	//	loadTable

	/**
	 * Clear the table components.
	 */
	public void clear()
	{
		this.getChildren().clear();
	}
	/**
	 *  Get the key of currently selected row based on layout defined in
	 *  {@link #prepareTable(ColumnInfo[], String, String, boolean, String)}.
	 *
	 *  @return ID if key
	 */
	public Integer getSelectedRowKey()
	{
		int row = 0;
		final int noSelection = -1;
		final int noIndex = -1;
		Object data;

		if (m_layout == null)
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
	public ColumnInfo[] getLayoutInfo()
	{
		return getLayout();
	}   //  getLayout

	/**
	 * Removes all data stored in the underlying model.
	 *
	 */
	public void clearTable()
	{
		WListItemRenderer renderer = null;

		// First clear the model
		getModel().clear();

		// Then the renderer
		if (getItemRenderer() instanceof WListItemRenderer)
		{
			renderer = (WListItemRenderer)getItemRenderer();
			renderer.clearSelection();
		}
		else
		{
			throw new IllegalArgumentException("Renderer must be instance of WListItemRenderer");
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
	 * (for multi-selection only)
	 * @return true if records are selected by default
	 */
	public boolean isDefaultSelected()
	{
		return p_isDefaultSelected;
	}

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

	/**
	 *  Set if Totals is Show
	 *  @param boolean Show
	 */
	public void setShowTotals(boolean show)
	{
		showTotals = show;
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
					//int viewRow = row;
					//int viewCol = convertColumnIndexToView(col);
					//int modelRow = convertRowIndexToModel(row);
					//int modelCol = convertColumnIndexToModel(col);
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
		boolean markerSet = false;
		setRowCount(row);
		for (int col = 0; col < layout.length; col++)
		{
			int modelCol = convertColumnIndexToModel(col);
			Class<?> c = layout[modelCol].getColClass();
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
				if(c == String.class && !markerSet)
				{	
					setValueAt(" Σ  " , row -1 , col);
					markerSet = true;
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
		addTotals((ColumnInfo[]) layout);
	}
	
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
				this.setValueAt(event.getNewValue(), row, col);
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
	 *  Get the record id of the lead (highlighted) row
	 *  @return selected key
	 */
	public int getLeadRowKey()
	{
		Integer rowKey = getSelectedRowKey();
		if (rowKey != null)
			return rowKey.intValue();
		else
			return 0;
	}   //  getLeadRowKey

    /**
     * Get the table layout.
     *
     * @return the layout of the table
     * @see #setLayout(ColumnInfo[])
     */
	public ColumnInfo[] getLayout()
	{
		return m_layout;
	}

	/**
	 * Set the column information for the table.
	 *
	 * @param layout	The new layout to set for the table
	 * @see #getLayout()
	 */
	private void setLayout(ColumnInfo[] layout)
	{
		this.m_layout = layout;
		getModel().setNoColumns(m_layout.length);

		return;
	}

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
        	ListModelTable model = this.getModel();
        	if (event.getLastRow() > event.getFirstRow())
        	{
        		int[] indices = this.getSelectedIndices();
        		model.updateComponent(event.getFirstRow(), event.getLastRow());
        		if (indices != null && indices.length > 0)
            	{
            		this.setSelectedIndices(indices);
            	}
        	}
        	else
        	{
        		boolean selected = false;
        		ListItem listItem = this.getItemAtIndex(event.getFirstRow());
        		if (listItem != null && listItem.isSelected()) {
        			selected = true;
        		}
        		model.updateComponent(event.getFirstRow());
        		listItem = this.getItemAtIndex(event.getFirstRow());
        		if (listItem != null && !listItem.isSelected() && selected) {
        			listItem.setSelected(true);
        		}
        	}
        	
        }

        return;
    }


	public int getColumnCount() {
		return getModel() != null ? getModel().getNoColumns() : 0;
	}
	
	public int getKeyColumnIndex() {
		return m_keyColumnIndex;
	}

	@Override
	public int getRowKey(int row) {
		if (getKeyColumnIndex() < 0)
			throw new UnsupportedOperationException("Key Column is not defined");
		
		int rows = this.getRowCount();
		
		if (this.getShowTotals())
			rows = rows - 1;

		if (row >= 0 && row < rows)
		{
	        Object data = getValueAt(row, convertColumnIndexToView(getKeyColumnIndex())); //  Test
			if (data instanceof IDColumn)
			{
				IDColumn id = (IDColumn)data;
				return id.getRecord_ID().intValue();
			}
		}
		return 0;
	}

	@Override
	public ArrayList<Integer> getSelectedKeys() {
		if (getModel() == null)
		{
			throw new UnsupportedOperationException("Layout not defined");
		}
		if (getKeyColumnIndex() < 0)
		{
			throw new UnsupportedOperationException("Key Column is not defined");
		}
		//
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int row = 0; row < getRowCount(); row++)
		{
			Object data = getModel().getValueAt(row, getKeyColumnIndex());
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

	/**
	 *	Size Columns.
	 *  Uses Mimimum Column Size
	 */
	public void autoSize()
	{
//  TODO finish port from SWING
		if ( !autoResize  )
			return;
/*
		long start = System.currentTimeMillis();
		//
		final int SLACK = 8;		//	making sure it fits in a column
		final int MAXSIZE = 300;    //	max size of a column
		//
		ListModelTable model = this.getModel();
		int size = model.getNoColumns();
		//	for all columns
		for (int col = 0; col < size; col++)
		{
			//  Column & minimum width
			ListColumn tc = model.get.getColumn(col);
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
*/
	}	//	autoSize

	/**
	 * Determines if the row is marked selected in the key column. The table 
	 * selection status (highlight) is not considered.
	 * @param row
	 * @return true if the row is marked selected in the key column
	 */
	public boolean isRowChecked(int row)
	{
		int keyColumn = this.getKeyColumnIndex();
		
		if (keyColumn < 0)
			return false;
		
		//  The selection can be indicated by an IDColumn or Boolean in the keyColumn position
		Object data = getValueAt(row, convertColumnIndexToView(keyColumn)); 
		if (data instanceof IDColumn)
			return ((IDColumn) data).isSelected();
		else if (data instanceof Boolean)
			return (Boolean) data;

		return	false;
	}

	@Override
	public void setKeyColumnIndex(int keyColumnIndex) {

		m_keyColumnIndex = keyColumnIndex;

	}

	@Override
	public int convertColumnIndexToView(int modelColumnIndex) {
		return modelColumnIndex;
	}

	@Override
	public int convertRowIndexToModel(int row) {
		return row;
	}

    /**
     * If the table row has a IDColumn or a boolean checkbox in the KeyColumnIndex
     * this function will set the checkbox according to the setValue parameter
     * @param row - the view row
     * @param setValue - the checkbox value to set 
     */
    public void setRowChecked(int row, boolean setValue)
    {   	
        //  The key column will be defined or zero by default.
    	//  Check the class of the data in the cell to verify if 
    	//  it is a selection column.  Selection columns can be
    	//  of type IDColumn or Boolean.
    	Object data = this.getValueAt(row, this.convertColumnIndexToView(getKeyColumnIndex()));
		if (data instanceof IDColumn)
		{
			IDColumn id = (IDColumn)data;
			id.setSelected(setValue);
		}
		else if (data instanceof Boolean)
		{
			data = setValue;
		}
		else return;
		
		this.setValueAt(data, row, this.convertColumnIndexToView(getKeyColumnIndex()));

    }

    /**
     * Recreate the list header, especially after column classes have been changed. 
     */
    public void recreateListHead() {
    	
    	WListItemRenderer rowRenderer = null;
    	
    	rowRenderer = (WListItemRenderer) this.getItemRenderer();
    	
	    ListHead head = super.getListHead();
	    if (head != null && rowRenderer != null)
	    {
	    	head.getChildren().clear();
	    	rowRenderer.renderListHead(head);
    	}
    }
}
