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

import java.awt.Color;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import org.adempiere.webui.event.TableValueChangeEvent;
import org.adempiere.webui.event.TableValueChangeListener;
import org.compiere.minigrid.IDColumn;
import org.compiere.util.DisplayType;
import org.compiere.util.MSort;
import org.compiere.util.Util;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.ListitemRendererExt;

/**
 * Renderer for {@link org.adempiere.webui.component.ListItems}
 * for the {@link org.adempiere.webui.component.Listbox}.
 *
 * @author Andrew Kimball
 *
 */
public class WListItemRenderer implements ListitemRenderer, EventListener, ListitemRendererExt
{
	/** Array of listeners for changes in the table components. */
	protected ArrayList<TableValueChangeListener> m_listeners =
            new ArrayList<TableValueChangeListener>();

	/** A list containing the indices of the currently selected ListItems. */
	private Set m_selectedItems = new HashSet<ListItem>();
	/**	Array of table details. */
	private ArrayList<WTableColumn> m_tableColumns = new ArrayList<WTableColumn>();
	/** Array of {@link ListHeader}s for the list head. */
    private ArrayList<ListHeader> m_headers = new ArrayList<ListHeader>();

	/**
	 * Default constructor.
	 *
	 */
	public WListItemRenderer()
	{
		super();
	}

	/**
	 * Constructor specifying the column headers.
	 *
	 * @param columnNames	vector of column titles.
	 */
	public WListItemRenderer(Vector< ? extends String> columnNames)
	{
		super();
		WTableColumn tableColumn;

		for (String columnName : columnNames)
		{
			tableColumn = new WTableColumn();
			tableColumn.setHeaderValue(Util.cleanAmp(columnName));
			m_tableColumns.add(tableColumn);
		}
	}

	/**
	 * Get the column details of the specified <code>column</code>.
	 *
	 * @param columnIndex	The index of the column for which details are to be retrieved.
	 * @return	The details of the column at the specified index.
	 */
	private WTableColumn getColumn(int columnIndex)
	{
		try
		{
			return m_tableColumns.get(columnIndex);
		}
		catch (IndexOutOfBoundsException exception)
		{
			throw new IllegalArgumentException("There is no WTableColumn at column "
                    + columnIndex);
		}
	}


	/* (non-Javadoc)
	 * @see org.zkoss.zul.ListitemRenderer#render(org.zkoss.zul.Listitem, java.lang.Object)
	 */
	public void render(Listitem item, Object data) throws Exception
	{
		render((ListItem)item, data);
	}

	/**
	 * Renders the <code>data</code> to the specified <code>Listitem</code>.
	 *
	 * @param item 	the listitem to render the result.
	 * 				Note: when this method is called, the listitem has no child
	 * 				at all.
	 * @param data 	that is returned from {@link ListModel#getElementAt}
	 * @throws Exception
	 * @see {@link #render(Listitem, Object)}
	 */
	private void render(ListItem item, Object data)
	{
		Listcell listcell = null;
		int colIndex = 0;
		int rowIndex = item.getIndex();
		WListbox table = null;

		if (item.getListbox() instanceof WListbox)
		{
			table = (WListbox)item.getListbox();
		}

		if (!(data instanceof Vector))
		{
			throw new IllegalArgumentException("A model element was not a vector");
		}

		for (Object field : (Vector)data)
		{
			listcell = getCellComponent(table, field, rowIndex, colIndex);
			listcell.setParent(item);
			colIndex++;
		}

		return;
	}

	/**
	 * Obtain the foreground colour to be used for the specified field.
	 *
	 * @param table		The table containing the affected field.
	 * @param row		The row of the field for which the colour is wanted.
	 * @return	The <code>Color</code> to be used.
	 */
/*	private Color getForegroundColour(WListbox table, int row)
	{
		Color fg = AdempierePLAF.getTextColor_Normal();

		int colourCode = table.getColorCode(row);
		//
		if (colourCode == 0)
		{
			//	Black
		}
		else if (colourCode < 0)
		{
			fg = AdempierePLAF.getTextColor_Issue();		//	Red
		}
		else
		{
			fg = AdempierePLAF.getTextColor_OK();		//	Blue
		}

		//	Highlighted row
		if (table.isSelected)
		{
			//fg = table.getSelectionForeground();
		}

		return fg;
	}
*/
	/**
	 * Obtain the background colour to be used for the specified field.
	 *
	 * @param table		The table containing the affected field.
	 * @param row		The row of the field for which the colour is wanted.
	 * @param column	The column of the field for which the colour is wanted.
	 * @return	The <code>Color</code> to be used.
	 */
/*	private Color getBackgroundColour(WListbox table, int row, int column)
	{

		Color bg = AdempierePLAF.getFieldBackground_Normal();

		boolean isCellReadOnly = !table.isCellEditable(row, column);
		if (isCellReadOnly)
		{
			bg = AdempierePLAF.getFieldBackground_Inactive();
			if (isSelected && !hasFocus)
			{
				bg = bg.darker();
			}
		}

		//	Highlighted row
		if (isSelected)
		{
		//	Windows is white on blue
			bg = table.getSelectionBackground();
			if (hasFocus)
			{
				bg = GraphUtil.brighter(bg, .9);
			}
		}

		return bg;
	}
*/
	/**
	 * Generate the cell for the given <code>field</code>.
	 *
	 * @param table 	The table into which the cell will be placed.
	 * @param field		The data field for which the cell is to be created.
	 * @param rowIndex	The row in which the cell is to be placed.
	 * @param columnIndex	The column in which the cell is to be placed.
	 * @return	The list cell component.
	 */
	private Listcell getCellComponent(WListbox table, Object field,
									  int rowIndex, int columnIndex)
	{
		ListCell listcell = new ListCell();
		boolean isCellEditable = table.isCellEditable(rowIndex, columnIndex);

/*		Color fgColor = getForegroundColour(table, rowIndex);
		Color bgColor = getBackgroundColour(table, rowIndex, columnIndex);

		ZkCssHelper.appendStyle(listcell, "color:#" + ZkCssHelper.createHexColorString(fgColor)
						+ "; bgcolor:#" + ZkCssHelper.createHexColorString(bgColor));
*/
        // TODO put this in factory method for generating cell renderers, which 
        // are assigned to Table Columns
		if (field != null)
		{
			if (field instanceof Boolean)
			{
				listcell.setValue(Boolean.valueOf(field.toString()));

				table.setCheckmark(false);
				Checkbox checkbox = new Checkbox();
				checkbox.setChecked(Boolean.valueOf(field.toString()));

				if (isCellEditable)
				{
					checkbox.setEnabled(true);
					checkbox.addEventListener(Events.ON_CHECK, this);
				}
				else
				{
					checkbox.setEnabled(false);
				}

				listcell.appendChild(checkbox);
				ZkCssHelper.appendStyle(listcell, "text-align:center");
			}
			else if (field instanceof BigDecimal)
			{
				DecimalFormat format = DisplayType.getNumberFormat(DisplayType.Amount);
				// set cell value to allow sorting
				listcell.setValue(field.toString());

				if (isCellEditable)
				{
					NumberBox numberbox = new NumberBox(false);
					numberbox.setFormat(format);
					numberbox.setValue((BigDecimal)field);
					numberbox.setWidth("100px");
					numberbox.setButtonVisible(true);
					numberbox.setReadonly(false);
					numberbox.setStyle("text-align:right; "
									+ listcell.getStyle());
					numberbox.addEventListener(Events.ON_CHANGE, this);
					listcell.appendChild(numberbox);
				}
				else
				{
					listcell.setLabel(format.format(((BigDecimal)field).doubleValue()));
					ZkCssHelper.appendStyle(listcell, "text-align:right");
				}
			}
			else if (field instanceof Timestamp)
			{

				SimpleDateFormat dateFormat = DisplayType.getDateFormat(DisplayType.Date);
				listcell.setValue(dateFormat.format((Timestamp)field));
				listcell.setLabel(dateFormat.format((Timestamp)field));
			}
			// if ID column make it invisible
			else if (field instanceof IDColumn)
			{
				//listcell.setLabel(field.toString());
				listcell.setValue(((IDColumn) field).getRecord_ID());
				//listcell.setVisible(false);
				table.setCheckmark(true);
				table.addEventListener(Events.ON_SELECT, this);
			}
			else
			{
				listcell.setLabel(field.toString());
				listcell.setValue(field.toString());
			}
		}
		else
		{
			listcell.setLabel("");
			listcell.setValue("");
		}

		return listcell;
	}


	/**
	 *  Update Table Column.
	 *
	 *  @param index	The index of the column to update
	 *  @param header 	The header text for the column
	 */
	public void updateColumn(int index, String header)
	{
		WTableColumn tableColumn;

		tableColumn = getColumn(index);
		tableColumn.setHeaderValue(Util.cleanAmp(header));

		return;
	}   //  updateColumn

	/**
	 *  Add Table Column.
	 *  after adding a column, you need to set the column classes again
	 *  (DefaultTableModel fires TableStructureChanged, which calls
	 *  JTable.tableChanged .. createDefaultColumnsFromModel
	 *  @param header The header text for the column
	 */
	public void addColumn(String header)
	{
		WTableColumn tableColumn;

		tableColumn = new WTableColumn();
		tableColumn.setHeaderValue(Util.cleanAmp(header));
		m_tableColumns.add(tableColumn);

		return;
	}   //  addColumn

	/**
	 * Get the number of columns.
	 * @return the number of columns
	 */
	public int getNoColumns()
	{
		return m_tableColumns.size();
	}

	/**
	 * This is unused.
	 * The readonly proprty of a column should be set in
	 * the parent table.
	 *
	 * @param colIndex
	 * @param readOnly
	 * @deprecated
	 */
	public void setRO(int colIndex, Boolean readOnly)
	{
		return;
	}

	/**
	 * Create a ListHeader using the given <code>headerValue</code> to
	 * generate the header text.
	 * The <code>toString</code> method of the <code>headerValue</code>
	 * is used to set the header text.
	 *
	 * @param headerValue	The object to use for generating the header text.
     * @param headerIndex   The column index of the header
	 * @return The generated ListHeader
	 * @see #renderListHead(ListHead)
	 */
	private Component getListHeaderComponent(Object headerValue, int headerIndex)
	{
        ListHeader header = null;

        if (m_headers.size() <= headerIndex)
        {
            Comparator ascComparator =  getColumnComparator(true, headerIndex);
            Comparator dscComparator =  getColumnComparator(false, headerIndex);

            header = new ListHeader(headerValue.toString());

            header.setSort("auto");
            header.setSortAscending(ascComparator);
            header.setSortDescending(dscComparator);

            header.setWidth("auto");
            m_headers.add(header);
        }
        else
        {
            header = m_headers.get(headerIndex);

            if (!header.getLabel().equals(headerValue.toString()))
            {
                header.setLabel(headerValue.toString());
            }
        }

		return header;
	}

    /**
     * Obtain the comparator for a given column.
     *
     * @param ascending     whether the comparator will sort ascending
     * @param columnIndex   the index of the column for which the comparator is required
     * @return  comparator for the given column for the given direction
     */
    protected Comparator getColumnComparator(boolean ascending, final int columnIndex)
    {
        Comparator comparator;
        final MSort sort = new MSort(0, null);

        sort.setSortAsc(ascending);

        comparator = new Comparator<Object>()
        {
            public int compare(Object o1, Object o2)
            {
                Object item1 = ((Vector)o1).get(columnIndex);
                Object item2 = ((Vector)o2).get(columnIndex);
                return sort.compare(item1, item2);
            }
        };

        return comparator;
    }

	/**
	 * Render the ListHead for the table with headers for the table columns.
	 *
	 * @param head	The ListHead component to render.
	 * @see #addColumn(String)
	 * @see #WListItemRenderer(Vector)
	 */
	public void renderListHead(ListHead head)
	{
		Component header;
        WTableColumn column;

		for (int columnIndex = 0; columnIndex < m_tableColumns.size(); columnIndex++)
        {
            column = m_tableColumns.get(columnIndex);
			header = getListHeaderComponent(column.getHeaderValue(), columnIndex);
            head.appendChild(header);
		}
		head.setSizable(true);
		head.setWidth("auto");

		return;
	}

	/* (non-Javadoc)
	 * @see org.zkoss.zk.ui.event.EventListener#onEvent(org.zkoss.zk.ui.event.Event)
	 */
	public void onEvent(Event event) throws Exception
	{
		int col = -1;
		int row = -1;
		Object value = null;
		TableValueChangeEvent vcEvent = null;
		Set newlyUnselectedItems = new HashSet<Listitem>();
		Set newlySelectedItems = new HashSet<Listitem>();
		WTableColumn tableColumn;

		Component source = event.getTarget();

		if (source instanceof WListbox)
		{
			if (event instanceof SelectEvent)
			{
				col = 0;
				tableColumn = m_tableColumns.get(col);

				newlyUnselectedItems.addAll(m_selectedItems);
				newlyUnselectedItems.removeAll(((SelectEvent)event).getSelectedItems());

				newlySelectedItems.addAll(((SelectEvent)event).getSelectedItems());
				newlySelectedItems.removeAll(m_selectedItems);

				m_selectedItems.clear();
				m_selectedItems.addAll(((SelectEvent)event).getSelectedItems());

				for (Object item : newlySelectedItems)
				{
					row =((ListItem)item).getIndex();
					value = Boolean.TRUE;
					vcEvent = new TableValueChangeEvent(source,
														tableColumn.getHeaderValue().toString(),
														row, col,
														value, value);
					fireTableValueChange(vcEvent);
				}


				for (Object item : newlyUnselectedItems)
				{
					row =((ListItem)item).getIndex();
					value = Boolean.FALSE;
					vcEvent = new TableValueChangeEvent(source,
														tableColumn.getHeaderValue().toString(),
														row, col,
														value, value);

					fireTableValueChange(vcEvent);
				}
			}
		}

		else if (source.getParent() instanceof Listcell)
		{
			row = getRowPosition(source);
			col = getColumnPosition(source);

			tableColumn = m_tableColumns.get(col);

			if (source instanceof Checkbox)
			{
				value = Boolean.valueOf(((Checkbox)source).isChecked());
			}
			else if (source instanceof NumberBox)
			{
				value = new BigDecimal(((NumberBox)source).getValue());
			}

			if(value != null)
			{
				vcEvent = new TableValueChangeEvent(source,
						tableColumn.getHeaderValue().toString(),
						row, col,
						value, value);

				fireTableValueChange(vcEvent);
			}
		}

		return;
	}

	/**
	 * Get the row index of the given <code>source</code> component.
	 *
	 * @param source	The component for which the row index is to be found.
	 * @return The row index of the given component.
	 */
	protected int getRowPosition(Component source)
	{
		Listcell cell;
		ListItem item;
		int row = -1;

		cell = (Listcell)source.getParent();
		item = (ListItem)cell.getParent();

		row = item.getIndex();

		return row;
	}

	/**
	 * Get the column index of the given <code>source</code> component.
	 *
	 * @param source	The component for which the column index is to be found.
	 * @return The column index of the given component.
	 */
	protected int getColumnPosition(Component source)
	{
		Listcell cell;
		int col = -1;

		cell = (Listcell)source.getParent();
		col = cell.getColumnIndex();

		return col;
	}


	/**
	 * Reset the renderer.
	 * This should be called if the table using this renderer is cleared.
	 */
	public void clearColumns()
	{
		m_tableColumns.clear();
	}

	/**
	 * Clear the renderer.
	 * This should be called if the table using this renderer is cleared.
	 */
	public void clearSelection()
	{
		m_selectedItems.clear();
	}

	/**
	 * Add a listener for changes in the table's component values.
	 *
	 * @param listener	The listener to add.
	 */
	public void addTableValueChangeListener(TableValueChangeListener listener)
	{
	    if (listener == null)
	    {
	    	return;
	    }

	    m_listeners.add(listener);

	    return;
	}

	/**
	 * Fire the given table value change <code>event</code>.
	 *
	 * @param event	The event to pass to the listeners
	 */
	private void fireTableValueChange(TableValueChangeEvent event)
	{
	    for (TableValueChangeListener listener : m_listeners)
	    {
	       listener.tableValueChange(event);
	    }
	}

	/* (non-Javadoc)
	 * @see org.zkoss.zul.ListitemRendererExt#getControls()
	 */
	public int getControls()
	{
		return DETACH_ON_RENDER;
	}

	/* (non-Javadoc)
	 * @see org.zkoss.zul.ListitemRendererExt#newListcell(org.zkoss.zul.Listitem)
	 */
	public Listcell newListcell(Listitem item)
	{
		return null;
	}

	/* (non-Javadoc)
	 * @see org.zkoss.zul.ListitemRendererExt#newListitem(org.zkoss.zul.Listbox)
	 */
	public Listitem newListitem(Listbox listbox)
	{
		return new ListItem();
	}

}


