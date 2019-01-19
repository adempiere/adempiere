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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.adempiere.exceptions.ValueChangeEvent;
import org.adempiere.exceptions.ValueChangeListener;
import org.adempiere.model.MBrowseField;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.Combobox;
import org.adempiere.webui.component.Datebox;
import org.adempiere.webui.component.ListCell;
import org.adempiere.webui.component.ListHead;
import org.adempiere.webui.component.ListHeader;
import org.adempiere.webui.component.ListItem;
import org.adempiere.webui.component.NumberBox;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.WTableColumn;
import org.adempiere.webui.component.ZkCssHelper;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WebEditorFactory;
import org.adempiere.webui.event.TableValueChangeEvent;
import org.adempiere.webui.event.TableValueChangeListener;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.GridField;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.MSort;
import org.compiere.util.NamePair;
import org.compiere.util.Util;
import org.zkoss.xml.XMLs;
import org.zkoss.zhtml.Label;
import org.zkoss.zhtml.Text;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.ListitemRendererExt;

/**
 * for the {@link org.adempiere.webui.component.Listbox}.
 *
 * @author Andrew Kimball
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<li>FR [ 257 ] Smart Browse does not get the hidden fields in Selection Browse
 * 		@see https://github.com/adempiere/adempiere/issues/257
 * 		<li>BR [ 269 ] Smart Browse don't allow edit Text fields
 *		@see https://github.com/adempiere/adempiere/issues/269
 *		<li>BR [ 270 ] Smart Browse cast error in ZK Table
 *		@see https://github.com/adempiere/adempiere/issues/270
 *		<li>BR [ 347 ] ZK Smart Browse Error cast from Integer to BigDecimal loading table
 * 		@see https://github.com/adempiere/adempiere/issues/347
 * 		<li><a href="https://github.com/adempiere/adempiere/issues/560">
 * 		FR [ 560 ] SB on ZK have always editable the columns</a>
 */
public class WBrowserListItemRenderer implements ListitemRenderer, EventListener, ListitemRendererExt , ValueChangeListener
{
	/** Array of listeners for changes in the table components. */
	protected ArrayList<TableValueChangeListener> m_listeners =
            new ArrayList<TableValueChangeListener>();

	/** A list containing the indices of the currently selected ListItems. */
	private Set<ListItem> m_selectedItems = new HashSet<ListItem>();
	/**	Array of table details. */
	private ArrayList<WTableColumn> m_tableColumns = new ArrayList<WTableColumn>();
	/** Array of {@link org.adempiere.webui.component.ListHeader}s for the list head. */
    private ArrayList<ListHeader> m_headers = new ArrayList<ListHeader>();

    private Listbox listBox;

	private EventListener cellListener;

	private List<WTableColumn> hiddenColumns = new ArrayList<WTableColumn>();

	private Map<WTableColumn, ColumnAttributes> columnAttributesMap
			= new HashMap<WTableColumn, ColumnAttributes>();

	private Map<String, Map<Object, String>> lookupCache = null;
	
	private static final int MAX_TEXT_LENGTH = 60;

	class ColumnAttributes {

		protected Object headerValue;

		protected int minWidth;

		protected int maxWidth;

		protected int preferredWidth;
	}

	/**
	 * Default constructor.
	 *
	 */
	public WBrowserListItemRenderer(WBrowserTable table) {
		super();
	}

	/**
	 *
	 * @param columnNames
	 * @param table
	 */
	public WBrowserListItemRenderer(List< ? extends String> columnNames,WBrowserTable table)
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


	@Override
	public void render(Listitem item, Object data) throws Exception {
		render((ListItem)item, data);
	}

	/**
	 * Create a Read Only CheckBox
	 * @param value
	 * @return
	 */
	private Component createReadonlyCheckbox(Object value) {
		Checkbox checkBox = new Checkbox();
		if (value != null && "true".equalsIgnoreCase(value.toString()))
			checkBox.setChecked(true);
		else
			checkBox.setChecked(false);
		checkBox.setDisabled(true);
		return checkBox;
	}
	
	/**
	 * Get Text for display
	 * @param value
	 * @param gridField
	 * @return
	 */
	private String getDisplayText(Object value, GridField gridField)
	{
		if (value == null)
			return "";

		if (gridField.isEncryptedField())
		{
			return "********";
		}
		else if (gridField.isLookup())
    	{
			if (lookupCache != null)
			{
				Map<Object, String> cache = lookupCache.get(gridField.getColumnName());
				if (cache != null && cache.size() >0)
				{
					String text = cache.get(value);
					if (text != null)
					{
						return text;
					}
				}
			}
			NamePair namepair = gridField.getLookup().get(value);
			if (namepair != null)
			{
				String text = namepair.getName();
				if (lookupCache != null)
				{
					Map<Object, String> cache = lookupCache.get(gridField.getColumnName());
					if (cache == null)
					{
						cache = new HashMap<Object, String>();
						lookupCache.put(gridField.getColumnName(), cache);
					}
					cache.put(value, text);
				}
				return text;
			}
			else
				return "";
    	}
		else if (DisplayType.isDate(gridField.getDisplayType()))
    	{
    		SimpleDateFormat dateFormat = DisplayType.getDateFormat(gridField.getDisplayType(), AEnv.getLanguage(Env.getCtx()));
    		return dateFormat.format((Timestamp)value);
    	}
    	else if (DisplayType.isNumeric(gridField.getDisplayType()))
    	{
    		return DisplayType.getNumberFormat(gridField.getDisplayType(), AEnv.getLanguage(Env.getCtx())).format(value);
    	}
    	else if (DisplayType.Button == gridField.getDisplayType())
    	{
    		return "";
    	}
    	else if (DisplayType.Image == gridField.getDisplayType())
    	{
    		if ((Integer)value <= 0)
    			return "";
    		else
    			return "...";
    	}
    	else
    		return value.toString();
	}
	
	/**
	 * Set the label text
	 * @param text
	 * @param label
	 */
	private void setLabelText(String text, Label label) {
		String display = text;
		if (text != null && text.length() > MAX_TEXT_LENGTH)
			display = text.substring(0, MAX_TEXT_LENGTH - 3) + "...";
		if (display != null)
			display = XMLs.encodeText(display);
		label.appendChild(new Text(display));
		if (text != null && text.length() > MAX_TEXT_LENGTH)
			label.setDynamicProperty("title", text);
		else
			label.setDynamicProperty("title", "");
	}
	
	/**
	 * Render Item
	 * @param item
	 * @param data
	 */
	private void render(ListItem item, Object data)
	{
		Listcell listcell = null;
		int colIndex = 0;
		int rowIndex = item.getIndex();
		WBrowserTable table = null;

		if (item.getListbox() instanceof WBrowserTable)
		{
			table = (WBrowserTable)item.getListbox();
		}

		if (!(data instanceof List))
		{
			throw new IllegalArgumentException("A model element was not a list");
		}

		if (listBox == null || listBox != item.getListbox())
		{
			listBox = item.getListbox();
		}
		if (cellListener == null)
		{
			cellListener = new CellListener();
		}

		for (Object field : (List<?>) data )
		{
			listcell = getCellComponent(table, field, rowIndex, colIndex);
			listcell.setParent(item);
			listcell.addEventListener(Events.ON_DOUBLE_CLICK, cellListener);
			colIndex++;
		}

		return;
	}
	
	/**
	 * Generate the cell for the given <code>field</code>.
	 *
	 * @param table 	The table into which the cell will be placed.
	 * @param field		The data field for which the cell is to be created.
	 * @param rowIndex	The row in which the cell is to be placed.
	 * @param columnIndex	The column in which the cell is to be placed.
	 * @return	The list cell component.
	 */
	private Listcell getCellComponent(WBrowserTable table, Object field,
									  int rowIndex, int columnIndex)
	{
		ListCell listcell = new ListCell();
		if(table == null)
			return listcell;
		BrowserRow browserRows = table.getData();
		//	BR [ 257 ]
		MBrowseField browseField = browserRows.getBrowserField(browserRows.getTableIndex(columnIndex));
		if (browseField == null)
			return listcell;
		//	
		GridField gridField  = table.getGridFieldAt(rowIndex, columnIndex);
		boolean isColumnVisible = true;
		
		if ( !m_tableColumns.isEmpty() )
			isColumnVisible = isColumnVisible(getColumn(columnIndex));

        // are assigned to Table Columns
		if (isColumnVisible && gridField != null) {
	        //	Set Read Only
	        boolean isCellEditable = table.isCellEditable(rowIndex, columnIndex);
			//	
			if ( DisplayType.YesNo == browseField.getAD_Reference_ID()) {
				//	BR [ 347 ]
				boolean selected = false;
				if(field != null) {
					selected = Boolean.valueOf(field.toString());
				}
				listcell.setValue(selected);

				if (columnIndex == 0)
					table.setCheckmark(false);
				Checkbox checkbox = new Checkbox();
				checkbox.setChecked(selected);

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
			else if ((DisplayType.isNumeric(browseField.getAD_Reference_ID())) && !browseField.isKey())
			{
				DecimalFormat format = field instanceof BigDecimal
					? DisplayType.getNumberFormat(DisplayType.Amount, AEnv.getLanguage(Env.getCtx()))
				    : DisplayType.getNumberFormat(DisplayType.Integer, AEnv.getLanguage(Env.getCtx()));

				// set cell value to allow sorting
				listcell.setValue((field == null ? "0" : field.toString()));

				if (isCellEditable) {
					NumberBox numberbox = new NumberBox(false);
					numberbox.setFormat(format);
					numberbox.setValue(field);
					numberbox.setWidth("100px");
					numberbox.setStyle("text-align:right; " + listcell.getStyle());
					numberbox.addEventListener(Events.ON_CHANGE, this);
					listcell.appendChild(numberbox);
					numberbox.setEnabled(true);
				} else{
					listcell.setLabel(format.format(((Number)(field==null?Env.ZERO:field)).doubleValue()));
					ZkCssHelper.appendStyle(listcell, "text-align:right");
				}
			}
			else if (DisplayType.isDate(browseField.getAD_Reference_ID())) {
				WEditor editor = WebEditorFactory.getEditor(gridField, false);
				editor.addValueChangeListener(this);
				editor.dynamicDisplay();
				editor.setReadWrite(true);
				editor.fillHorizontal();
				gridField.addPropertyChangeListener(editor);
				listcell.appendChild(editor.getComponent());
			}
			//	BR [ 269 ]
			//	Add support to other String
			else if (browseField.getAD_Reference_ID() == DisplayType.String
		            || browseField.getAD_Reference_ID() == DisplayType.PrinterName 
		            || browseField.getAD_Reference_ID() == DisplayType.Text 
		            || browseField.getAD_Reference_ID() == DisplayType.TextLong)
			{
				listcell.setValue((field == null ? "" : field.toString()));
				if (isCellEditable) {
					Textbox textbox = new Textbox();
					textbox.setValue((field == null ? "" : field.toString()));
					textbox.addEventListener(Events.ON_CHANGE, this);
					listcell.appendChild(textbox);
				} else {
					listcell.setLabel((field == null ? "" : field.toString()));
				}

			}
            else if (field instanceof org.adempiere.webui.component.Combobox)
            {
                listcell.setValue(field);
                if (isCellEditable)
                {
                    Combobox combobox =  (Combobox)field;
                    combobox.addEventListener(Events.ON_CHANGE, this);
                    listcell.appendChild(combobox);
                }
                else
                {
                    Combobox combobox =  (Combobox)field;
                    if(combobox!=null && combobox.getItemCount()>0) {
                        if (combobox.getSelectedIndex() >= 0)
                            listcell.setLabel((String)combobox.getItemAtIndex(combobox.getSelectedIndex()).getLabel());
                        else
                            listcell.setLabel("");
                    }
                }
            }
            else if (field instanceof org.adempiere.webui.component.Button)
            {
                listcell.setValue(field);
                if (isCellEditable)
                {
                    Button button =  (Button)field;
                    button.addEventListener(Events.ON_CLICK, this);
                    listcell.appendChild(button);
                }
                else
                {
                    Button button =  (Button)field;
                    if(button!=null ) {
                        listcell.setLabel("");
                    }
                }
            }
			// if ID column make it invisible
			else if (field instanceof IDColumn && browseField.isKey())
			{
				IDColumn id = (IDColumn) field;
				if (id != null && id.getRecord_ID() != null)
				{
					listcell.setValue(id.getRecord_ID());
					if (!table.isCheckmark()) {
						table.setCheckmark(true);
						table.removeEventListener(Events.ON_SELECT, this);
						table.addEventListener(Events.ON_SELECT, this);
					}
				}
			}
			else if ((DisplayType.isLookup(browseField.getAD_Reference_ID()) 
						|| DisplayType.ID == browseField.getAD_Reference_ID()) 
					&& !browseField.isKey())
			{
				if (isCellEditable)
				{
					WEditor editor = WebEditorFactory.getEditor(gridField, true);
					editor.addValueChangeListener(this);
					editor.dynamicDisplay();
					editor.fillHorizontal();
					gridField.removePropertyChangeListener(editor);
					gridField.addPropertyChangeListener(editor);
					editor.setValue(gridField.getValue());
					listcell.appendChild(editor.getComponent());
				}
				else
				{
					Component component;
					if (gridField.getDisplayType() == DisplayType.YesNo) {
						component = createReadonlyCheckbox(field);
					} else {
						String text = getDisplayText(field, gridField);
						Label label = new Label();
						setLabelText(text, label);

						component = label;
					}

					listcell.appendChild(component);
				}
			}
			else
			{
				listcell.setLabel((field == null ? null : field.toString()));
				listcell.setValue((field == null ? null : field.toString()));
			}
		}
		else
		{
			listcell.setLabel("");
			listcell.setValue("");
		}

		listcell.setAttribute("zk_component_ID", "ListItem_Cell_" + rowIndex + "_" + columnIndex);

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
	 *  Add Table Column.  Assumes it is visible.
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
		setColumnVisibility(tableColumn, true);
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
	 * The read only property of a column should be set in
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
	 * @param classType
	 * @return The generated ListHeader
	 * @see #renderListHead(ListHead)
	 */
	private Component getListHeaderComponent(Object headerValue, int headerIndex, Class<?> classType)
	{
        ListHeader header = null;

        String headerText = headerValue.toString();
        if (m_headers.size() <= headerIndex || m_headers.get(headerIndex) == null)
        {
        	if (!isColumnVisible(getColumn(headerIndex)))
        	{
        		header = new ListHeader("");
        		header.setWidth("0px");
        		header.setStyle("width: 0px");
        	}
        	else if (classType != null && classType.isAssignableFrom(IDColumn.class))
        	{
        		header = new ListHeader("");
        		header.setWidth("35px");
        	}
        	else
        	{
	            Comparator<Object> ascComparator =  getColumnComparator(true, headerIndex);
	            Comparator<Object> dscComparator =  getColumnComparator(false, headerIndex);
	
	            header = new ListHeader(headerText);
	
	            header.setSort("auto");
	            header.setSortAscending(ascComparator);
	            header.setSortDescending(dscComparator);

	            int width = headerText.trim().length() * 9;
	            if (width > 300)
	            	width = 300;
	            else if (classType != null)
	            {
	            	if (classType.equals(String.class))
	            	{
	            		if (width > 0 && width < 180)
	            			width = 180;
	            	}
	            	else if (classType.equals(IDColumn.class))
	            	{
	            		header.setSort("none");
	            		if (width == 0)
	            			width = 30;
	            	}
		            else if (width > 0 && width < 100 && (classType == null || !classType.isAssignableFrom(Boolean.class)))
	            		width = 100;
	            }
	            else if (width > 0 && width < 100)
	            	width = 100;
	
	            header.setWidth(width + "px");
        	}
            m_headers.add(header);
        }
        else
        {
            header = m_headers.get(headerIndex);

            if (!isColumnVisible(getColumn(headerIndex)))
        	{
        		header.setLabel("");
        		header.setWidth("0px");
        		header.setStyle("width: 0px");
        	}
        	else if (!header.getLabel().equals(headerText))
            {
                header.setLabel(headerText);
            }
        }

        header.setAttribute("zk_component_ID", "ListItem_Header_C" + headerIndex);

		return header;
	}

	/**
	 * set custom list header
	 * @param index
	 * @param header
	 */
	public void setListHeader(int index, ListHeader header) {
		int size = m_headers.size();
		if (size <= index) {
			while (size <= index) {
				if (size == index)
					m_headers.add(header);
				else
					m_headers.add(null);
				size++;
			}

		} else
			m_headers.set(index, header);
	}

    /**
     * Obtain the comparator for a given column.
     *
     * @param ascending     whether the comparator will sort ascending
     * @param columnIndex   the index of the column for which the comparator is required
     * @return  comparator for the given column for the given direction
     */
    protected Comparator<Object> getColumnComparator(boolean ascending, final int columnIndex)
    {
    	return new ColumnComparator(ascending, columnIndex);
    }

    public static class ColumnComparator implements Comparator<Object>
    {

    	private int columnIndex;
		private MSort sort;

		public ColumnComparator(boolean ascending, int columnIndex)
    	{
    		this.columnIndex = columnIndex;
    		sort = new MSort(0, null);
        	sort.setSortAsc(ascending);
    	}

        public int compare(Object o1, Object o2)
        {
                Object item1 = ((List<?>)o1).get(columnIndex);
                Object item2 = ((List<?>)o2).get(columnIndex);
                return sort.compare(item1, item2);
        }

		public int getColumnIndex()
		{
			return columnIndex;
		}
    }

	/**
	 * Render the ListHead for the table with headers for the table columns.
	 *
	 * @param head	The ListHead component to render.
	 * @see #addColumn(String)
	 */
	public void renderListHead(ListHead head)
	{
		Component header;
        WTableColumn column;

		for (int columnIndex = 0; columnIndex < m_tableColumns.size(); columnIndex++)
        {
            column = m_tableColumns.get(columnIndex);
			header = getListHeaderComponent(column.getHeaderValue(), columnIndex, column.getColumnClass());
            head.appendChild(header);
		}
		head.setSizable(true);

		return;
	}

	@Override
	public void onEvent(Event event) throws Exception {
		int col = -1;
		int row = -1;
		Object value = null;
		TableValueChangeEvent vcEvent = null;
		WTableColumn tableColumn;

		Component source = event.getTarget();

		if (isWithinListCell(source)) {
			row = getRowPosition(source);
			col = getColumnPosition(source);

			tableColumn = m_tableColumns.get(col);

			if (source instanceof Checkbox)
			{
				value = Boolean.valueOf(((Checkbox)source).isChecked());
			}
			else if (source instanceof Decimalbox)
			{
				value = ((Decimalbox)source).getValue();
			}
			else if (source instanceof Datebox)
			{
				value = ((Datebox)source).getValue();
			}
			else if (source instanceof Textbox)
			{
				value = ((Textbox)source).getValue();
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
		else if (event.getTarget() instanceof WBrowserTable && Events.ON_SELECT.equals(event.getName()))
		{
			WBrowserTable table = (WBrowserTable) event.getTarget();
			if (table.isCheckmark()) {
				int cnt = table.getRowCount();
				if (cnt == 0 || !(table.getValueAt(0, 0) instanceof IDColumn))
					return;

				//update IDColumn
				tableColumn = m_tableColumns.get(0);
				for (int i = 0; i < cnt; i++) {
					IDColumn idcolumn = (IDColumn) table.getValueAt(i, 0);
					if (idcolumn != null)
					{
						Listitem item = table.getItemAtIndex(i);

						value = item.isSelected();
						Boolean old = idcolumn.isSelected();

						if (!old.equals(value)) {
							vcEvent = new TableValueChangeEvent(source,
									tableColumn.getHeaderValue().toString(),
									i, 0,
									old, value);

							fireTableValueChange(vcEvent);
						}
					}
				}
			}
		}

		return;
	}

	private boolean isWithinListCell(Component source) {
		if (source instanceof Listcell)
			return true;
		Component c = source.getParent();
		while(c != null) {
			if (c instanceof Listcell)
				return true;
			c = c.getParent();
		}
		return false;
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

		cell = findListcell(source);
		item = (ListItem)cell.getParent();

		row = item.getIndex();

		return row;
	}

	private Listcell findListcell(Component source) {
		if (source instanceof Listcell)
			return (Listcell) source;
		Component c = source.getParent();
		while(c != null) {
			if (c instanceof Listcell)
				return (Listcell) c;
			c = c.getParent();
		}
		return null;
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

		cell = findListcell(source);
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
		columnAttributesMap.clear();
		hiddenColumns.clear();
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
	}

	public void removeTableValueChangeListener(TableValueChangeListener listener)
	{
		if (listener == null)
	    {
	    	return;
		}

	    m_listeners.remove(listener);
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
		ListCell cell = new ListCell();
		cell.applyProperties();
		return cell;
	}

	/* (non-Javadoc)
	 * @see org.zkoss.zul.ListitemRendererExt#newListitem(org.zkoss.zul.Listbox)
	 */
	public Listitem newListitem(Listbox listbox)
	{
		ListItem item = new ListItem();
		item.applyProperties();

		return item;
	}

	/**
	 * @param index
	 * @param header
	 */
	public void setColumnHeader(int index, String header)
	{
		if (index >= 0 && index < m_tableColumns.size())
		{
			m_tableColumns.get(index).setHeaderValue(Util.cleanAmp(header));
		}

	}

	public void setColumnClass(int index, Class<?> classType) {
		if (index >= 0 && index < m_tableColumns.size())
		{
			m_tableColumns.get(index).setColumnClass(classType);
		}
	}

	/**
     *
     * @param column
     * @return boolean
     */
	public boolean isColumnVisible(WTableColumn column)
	{
		return !hiddenColumns.contains(column);
	}

	/**
	 * Hide or show column
	 * @param index of the column
	 * @param visible
	 */
	public void setColumnVisibility(int index, boolean visible)
	{
		WTableColumn column;

		if (index >= 0 && index < m_tableColumns.size())
		{
			column = m_tableColumns.get(index);
			setColumnVisibility(column, visible);
		}
		else
			return;
	}
	/**
	 * Hide or show column
	 * @param column
	 * @param visible
	 */
	public void setColumnVisibility(WTableColumn column, boolean visible)
	{

		if (visible)
		{
			if (isColumnVisible(column)) return;
			ColumnAttributes attributes = columnAttributesMap.get(column);
			if (attributes == null) return;

			column.setMinWidth(attributes.minWidth);
			column.setMaxWidth(attributes.maxWidth);
			column.setPreferredWidth(attributes.preferredWidth);
			columnAttributesMap.remove(column);
			hiddenColumns.remove(column);
		}
		else
		{
			if (!isColumnVisible(column)) return;

			ColumnAttributes attributes = new ColumnAttributes();
			attributes.minWidth = column.getMinWidth();
			attributes.maxWidth = column.getMaxWidth();
			attributes.preferredWidth = column.getPreferredWidth();
			columnAttributesMap.put(column, attributes);
			column.setMinWidth(0);
			column.setMaxWidth(0);
			column.setPreferredWidth(0);
        	hiddenColumns.add(column);
		}
	}

	/**
	 *	Editor Listener
	 *	@param evt Event
	 */
	public void valueChange(ValueChangeEvent evt) {
		if (evt.getSource() instanceof WEditor) {
			GridField changedField = ((WEditor) evt.getSource()).getGridField();
			if (changedField != null) {
				changedField.setValue(evt.getNewValue(), false);
				//processDependencies(changedField);
				// future processCallout (changedField);
			}
		}
		String columnName = "";
		if(evt.getSource() instanceof WEditor)
		{
			WEditor wEditor = (WEditor)evt.getSource();
			columnName = wEditor.getGridField().getVO().ColumnNameAlias;
		}
		//processNewValue(evt.getNewValue(), columnName);
	} // valueChange


	class CellListener implements EventListener {

		public CellListener() {
		}

		public void onEvent(Event event) throws Exception {
			if (listBox != null && Events.ON_DOUBLE_CLICK.equals(event.getName())) {
				Event evt = new Event(Events.ON_DOUBLE_CLICK, listBox);
				Events.sendEvent(listBox, evt);
			}
		}

	}
}


