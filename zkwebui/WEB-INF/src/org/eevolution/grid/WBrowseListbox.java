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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Level;

import javax.script.ScriptEngine;

import org.adempiere.model.I_AD_Browse_Field;
import org.adempiere.model.MBrowseField;
import org.adempiere.model.MViewColumn;
import org.adempiere.webui.component.ListHead;
import org.adempiere.webui.component.ListItem;
import org.adempiere.webui.component.ListModelTable;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.event.TableValueChangeEvent;
import org.adempiere.webui.event.TableValueChangeListener;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.event.WTableModelListener;
import org.adempiere.webui.exception.ApplicationException;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.GridField;
import org.compiere.model.MRule;
import org.compiere.model.MSysConfig;
import org.compiere.util.*;
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
 * @author carlosaparada@gmail.com Carlos Parada, ERP Consultores y asociados
 * @author victor.perez@www.e-evolution.com, e-Evolution
 */
public class WBrowseListbox extends Listbox implements IBrowseTable, TableValueChangeListener, WTableModelListener
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8717707799347994189L;

	public static final String SYSCONFIG_INFO_DEFAULTSELECTED = "INFO_DEFAULTSELECTED";
	public static final String SYSCONFIG_INFO_DOUBLECLICKTOGGLESSELECTION = "INFO_DOUBLECLICKTOGGLESSELECTION";

	/**	Logger. */
	private static CLogger logger = CLogger.getCLogger(WBrowseListbox.class);

	/** Model Index of Key Column.   */
	protected int m_keyColumnIndex = -1;

	/** List of R/W columns.     */
	private ArrayList<Integer> m_readWriteColumn = new ArrayList<Integer>();

	/** Layout set in prepareTable and used in loadTable.    */
	private List<MBrowseField> browserFields = null;
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

	protected WBrowserRows browserRows = null;

	public boolean isAutoResize() {
		return autoResize;
	}

	public void setAutoResize(boolean autoResize) {
		this.autoResize = autoResize;
	}

	protected WBrowser browser;
	
	/** Active BrowseCallOuts **/
	private List<String> activeCallOuts = new ArrayList<String>();
	
	/** Active BrowseCallOutsInstances **/
	private List<WBrowserCallout> activeCallOutInstance = new ArrayList<WBrowserCallout>();
	
	/** Context **/
	private Properties ctx =Env.getCtx();   
	
	/** Multi Selection mode (default false) */
	private boolean     m_multiSelection = false;

	/** List of Column Width    */
	private ArrayList<Integer>   m_minWidth = new ArrayList<Integer>();
	
	//private List<GridField> gridFields = new ArrayList<GridField>();
	/**
	 * Default constructor.
	 *
	 * Sets a row renderer and an empty model
	 */
	public WBrowseListbox(WBrowser browser)
	{
		super();
		WBrowseListItemRenderer rowRenderer = new WBrowseListItemRenderer(this);
	    rowRenderer.addTableValueChangeListener(this);

		setItemRenderer(rowRenderer);
		setModel(new ListModelTable());
		this.browser = browser;
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

	    autoSize();
		if(isShowTotals())
			addTotals();

	    // re-render
	    this.repaint();

	    return;
	}

    public void setModel(ListModel model)
    {
        super.setModel(model);
        if (model instanceof ListModelTable)
        {
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
	protected void setValueAt(Object value, int row, int column)
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
	 * Get Value At
	 * @param row
	 * @param column
	 * @param gridField
	 */
	public void setValueAt(int row, int column, GridField gridField) {

		if (gridField == null)
			throw new UnsupportedOperationException("No GridField");

		browserRows.setValue(row, browserRows.getTableIndex(column), gridField);

		if (gridField.isDisplayed())
			setValueAt(gridField.getValue(), row, column);

	}

	/**
	 * preparate Table
	 * @param fields
	 * @param multiSelection
	 * @return
	 */
	public String prepareTable(List<MBrowseField> fields, boolean multiSelection)
	{
		browserRows = new WBrowserRows(this);
		StringBuffer sql = new StringBuffer("");
		m_multiSelection = multiSelection;
		clearColumns();
		setLayout(fields);
		int col = 0;
		//  Add columns & sql
		for (MBrowseField field : fields)
		{
			MViewColumn columnView = field.getAD_View_Column();
			//  create sql
			if (col > 0 && columnView.getColumnSQL().length() > 0)
				sql.append(", ");

			if (field.isKey()) {
				setKey(col);
				field.setName("#");
			}

			sql.append(columnView.getColumnSQL())
					.append(" ")
					.append("AS")
					.append(" ")
					.append(columnView.getColumnName());

			browserRows.addBrowserField(field , col);
			if (field.isDisplayed()){
				// Use get value get from memory entity because field can be calculated
				addColumn(field.get_ValueAsString(I_AD_Browse_Field.COLUMNNAME_Name));
				col++;
			}
		}

		col = 0;
		for (MBrowseField field : fields)
		{
			if (field.isDisplayed()){
				setColumnClass(col,
						MBrowseField.createGridFieldVO(field, browser.getWindowNo()),
						field.getAD_Reference_ID(),
						field.isReadOnly(),
						field.get_ValueAsString(I_AD_Browse_Field.COLUMNNAME_Name));
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
	 *  Add Table Column and specify the column header.
	 *
	 *  @param info	ColumInfo class for the column
	 */
	public void addColumn (MBrowseField info)
	{
		WBrowseListItemRenderer renderer = (WBrowseListItemRenderer)getItemRenderer();
		renderer.addColumn(info.getName());
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
	 *	Set the Column to determine the color of the row (based on model index).
	 *  @param modelIndex 	the index of the column used to decide the colour
	 */
	public void setColorColumn (int modelIndex)
	{
		m_colorColumnIndex = modelIndex;
	}   //  setColorColumn

	public void loadTable(ResultSet rs)
	{
		int no = 0;
		if (getLayout() == null)
			throw new UnsupportedOperationException("Layout not defined");

		clearTable();
		try
		{
			while (rs.next())
			{
				no++;
				int row = getRowCount();
				setRowCount(row + 1);
				int colOffset = 1;
				int colIndex =0;
				int col = 0;
				for (MBrowseField field : getLayout()) {
					Object value = null;
					if (field.isKey() && !field.getAD_View_Column().getColumnSQL().equals("'Row' AS \"Row\""))
						value = new IDColumn(rs.getInt(col + colOffset));
					else if (field.isKey() && !field.getAD_View_Column().getColumnSQL().equals("'Row' AS \"Row\""))
						value  = new IDColumn(no);
					else if (DisplayType.TableDir == field.getAD_Reference_ID()
							|| DisplayType.Table == field.getAD_Reference_ID()
							|| DisplayType.Integer == field.getAD_Reference_ID()
							|| DisplayType.PAttribute == field.getAD_Reference_ID()
							|| DisplayType.Account == field.getAD_Reference_ID()) {
						Integer id = rs.getInt(col + colOffset);
						value = id != 0  ? id : null ;
					}
					else if (DisplayType.isNumeric(field.getAD_Reference_ID()))
						value = rs.getBigDecimal(col + colOffset);
					else if (DisplayType.isDate(field.getAD_Reference_ID()))
						value = rs.getTimestamp(col + colOffset);
					else if (DisplayType.YesNo == field.getAD_Reference_ID()) {
						value = rs.getString(col + colOffset);
						if (value != null)
							value = value.equals("Y");
					}
					else
						value = rs.getObject(col + colOffset);

					GridField gridField = MBrowseField.createGridFieldVO(field , browser.getWindowNo());
					gridField.setValue(value, true);
					setValueAt(row, colIndex , gridField);
					if (field.isDisplayed())
						colIndex++;

					col ++;
				}

			}
		}
		catch (SQLException exception) {
			logger.log(Level.SEVERE, "", exception);
		}

		autoSize();
		if(isShowTotals())
			addTotals();

		// repaint the table
		this.repaint();

		logger.config("Row(rs)=" + getRowCount());
	}

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

		if (this.browserRows.getColumnCount() == 0)
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
     * @return the index of the first selected row
     */
    public int getSelectedRow()
    {
    	return this.getSelectedIndex();
    }

	/**
	 *  Set the size of the underlying data model.
	 *  @param rowCount	number of rows
	 */
	public void setRowCount (int rowCount)
	{
		getModel().setNoRows(rowCount);

		return;
	}   //  setRowCount

	/**
	 * Removes all data stored in the underlying model.
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
     * @return the number of rows in this table's model
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
	 *  @param show Show
	 */
	public void setShowTotals(boolean show)
	{
		showTotals = show;
	}
	/**
	 *  get if Totals is Show
	 */
	public boolean isShowTotals()
	{
		return showTotals;
	}

	/**
	 *  Adding a new row with the totals
	 */
	public void addTotals()
	{
		if (getRowCount() == 0 || this.browserRows.getNoViewColumns() == 0)
			return;

		Object[] total = new Object[this.browserRows.getNoViewColumns()];

		for (int row = 0; row < getRowCount(); row++) {
			for (int col = 0; col < this.browserRows.getNoViewColumns(); col++) {
				Object data = getModel().getValueAt(row, col);
				//Class<?> c = layout[col].getColClass();
				int ReferenceType = this.browserRows.getBrowserField(this.browserRows.getTableIndex(col)).getAD_Reference_ID();
				//if (c == BigDecimal.class)
				if (DisplayType.isNumeric(ReferenceType)) {
					BigDecimal subtotal = Env.ZERO;
					if (total[col] != null)
						subtotal = (BigDecimal) (total[col]);

					BigDecimal amt = (BigDecimal) data;
					if (subtotal == null)
						subtotal = Env.ZERO;
					if (amt == null)
						amt = Env.ZERO;
					total[col] = subtotal.add(amt);
				}
			}
		}

		//adding total row
		int row = getRowCount() + 1;
		boolean markerSet = false;
		setRowCount(row);
		for (int col = 0; col < this.browserRows.getNoViewColumns(); col++) {
			MBrowseField field = this.browserRows.getBrowserField(this.browserRows.getTableIndex(col));
			GridField gridField = MBrowseField.createGridFieldVO(field, browser.getWindowNo());
			if (DisplayType.isNumeric(field.getAD_Reference_ID())) {
				gridField.setValue(total[col], true);
				setValueAt(row - 1, col, gridField);
			} else {
				if (DisplayType.isText(field.getAD_Reference_ID()) && !markerSet) {
					gridField.setValue(" Σ ", true);
					setValueAt(row - 1, col, gridField);
					markerSet = true;
				} else {
					gridField.setValue(null, true);
					setValueAt(row - 1, col , gridField);
				}
			}
		}
	}

	/**
	 *
	 * @param event    The event that has occurred
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
			// otherwise just set the value in the model to the new value
			else
			{
				
				GridField gridField = browserRows.getValue(row, browserRows.getTableIndex(col));
				gridField.setValue(event.getNewValue(), true);
				//setValueAt(gridField , row, col);
				if (gridField.getCallout() != null){
					
					processCallOut(gridField, event.getNewValue(), event.getOldValue(), row, col);
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
     * @see #setLayout(List<MBrowseField>)
     */
	public List<MBrowseField> getLayout()
	{
		return browserFields;
	}

	/**
	 * Set the column information for the table.
	 *
	 * @param layout	The new layout to set for the table
	 * @see #getLayout()
	 */
	private void setLayout(List<MBrowseField> layout)
	{
		this.browserFields = layout;
		getModel().setNoColumns(browserFields.size());

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


	/**
	 * Set Key index From Table
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 21/10/2013, 11:59:42
	 * @param col
	 * @return void
	 */
	private void setKey(int col)
	{
		m_keyColumnIndex = col;
		browser.m_keyColumnIndex=col;
	}//setKey
	
	public void setColumnClass (int index, GridField gridField, int displayType ,boolean readOnly, String header)
	{
	//	log.config( "MiniTable.setColumnClass - " + index, c.getName() + ", r/o=" + readOnly);
		
		WBrowseListItemRenderer renderer = (WBrowseListItemRenderer)getItemRenderer();
		
		renderer.setColumnHeader(index, header);
		//  Set R/O
		setColumnReadOnly(index, readOnly);

		// repaint the table
		this.repaint();
		
	}   //  setColumnClass





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


	public void setKeyColumnIndex(int keyColumnIndex) {

		m_keyColumnIndex = keyColumnIndex;

	}


	public int convertColumnIndexToView(int modelColumnIndex) {
		return modelColumnIndex;
	}


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
	 * Get Browse Rows Data 
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 15/10/2013, 10:01:47
	 * @return
	 * @return BrowserRows
	 */
	public WBrowserRows getData() {
		return browserRows;
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
	public String processCallOut (GridField field,Object value,Object oldValue, int currentRow,int currentColumn )
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
			if (activeCallOuts.contains(cmd)) continue;
			
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
				MRule.setContext(engine, ctx, browser.getWindowNo());
				// now add the callout parameters windowNo, tab, field, value, oldValue to the engine 
				// Method arguments context are A_
				engine.put(MRule.ARGUMENTS_PREFIX + "WindowNo", browser.getWindowNo());
				engine.put(MRule.ARGUMENTS_PREFIX + "Tab", this);
				engine.put(MRule.ARGUMENTS_PREFIX + "Field", field);
				engine.put(MRule.ARGUMENTS_PREFIX + "Value", value);
				engine.put(MRule.ARGUMENTS_PREFIX + "OldValue", oldValue);
				engine.put(MRule.ARGUMENTS_PREFIX + "currentRow", currentRow);
				engine.put(MRule.ARGUMENTS_PREFIX + "currentColumn", currentColumn);
				engine.put(MRule.ARGUMENTS_PREFIX + "Ctx", ctx);

				try 
				{
					activeCallOuts.add(cmd);
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
					activeCallOuts.remove(cmd);
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
					activeCallOuts.add(cmd);
					activeCallOutInstance.add(call);
					retValue = call.start(ctx, method, browser.getWindowNo(), browserRows, field, value, oldValue,currentRow,currentColumn);
				}
				catch (Exception e)
				{
					logger.log(Level.SEVERE, "start", e);
					retValue = 	"Callout Invalid: " + e.toString();
					return retValue;
				}
				finally
				{
					activeCallOuts.remove(cmd);
					activeCallOutInstance.remove(call);
				}
				
			}			
			
			if (!Util.isEmpty(retValue))		//	interrupt on first error
			{
				logger.severe (retValue);
				return retValue;
			}
		}   //  for each callout
		return "";
	}	//	process Callout
}
