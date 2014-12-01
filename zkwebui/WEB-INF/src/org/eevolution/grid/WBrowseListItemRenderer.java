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

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.*;
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
 * Renderer for {@link org.adempiere.webui.component.ListItems}
 * for the {@link org.adempiere.webui.component.Listbox}.
 *
 * @author Andrew Kimball
 *
 */
public class WBrowseListItemRenderer implements ListitemRenderer, EventListener, ListitemRendererExt
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
	
	private WBrowseListbox wbListBox;
	
	private Object[] currentValues;
	
	private Map<String, Map<Object, String>> lookupCache = null;
	
	private static final int MAX_TEXT_LENGTH = 60;

	/**
	 * Default constructor.
	 *
	 */
	public WBrowseListItemRenderer(WBrowseListbox p_wbListBox)
	{
		super();
		wbListBox = p_wbListBox;
	}

	/**
	 * Constructor specifying the column headers.
	 *
	 * @param columnNames	vector of column titles.
	 */
	public WBrowseListItemRenderer(List< ? extends String> columnNames,WBrowseListbox p_wbListBox)
	{
		super();
		wbListBox = p_wbListBox;
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

/*
	public void render(ListItem item, Object data) throws Exception {
		//don't render if not visible
		
		WBrowseListbox table = null;

		if (item.getListbox() instanceof WBrowseListbox)
		{
			table = (WBrowseListbox)item.getListbox();
		}
		
		
		//if (gridPanel != null && !gridPanel.isVisible()) {
		//	return;
		//}

		//if (grid == null)
		//	grid = (Grid) row.getParent().getParent();

		//if (rowListener == null)
		//	rowListener = new RowListener((Grid)row.getParent().getParent());

		
		ListCell listcell = new ListCell();
		
		
		if (data instanceof ArrayList){
			currentValues = new Object[((ArrayList)data).size()];
			((ArrayList)data).toArray(currentValues);
		}
		else
			currentValues = (Object[])data;
		int columnCount = wbListBox.getgFields().size();//gridTab.getTableModel().getColumnCount();
		
		GridField[] gridField  = new GridField[columnCount];//;= gridTab.getFields();
		wbListBox.getGridFields().toArray(gridField);
		//Center grid = (Center) item.getParent().getParent();
		//org.zkoss.zul.Columns columns = grid.getColumns();

		//int rowIndex = row.getParent().getChildren().indexOf(row);
		//if (paging != null && paging.getPageSize() > 0) {
		//	rowIndex = (paging.getActivePage() * paging.getPageSize()) + rowIndex;
		//}

		int colIndex = -1;
		int compCount = 0;
		for (int i = 0; i < columnCount; i++) {
			if (!gridField[i].isDisplayed()) {
				continue;
			}
			colIndex ++;

			Div div = new Div();
			String divStyle = "border: none; width: 100%; height: 100%;";
			//org.zkoss.zul.Column column = (org.zkoss.zul.Column) columns.getChildren().get(colIndex);
			if (gridField[i].isDisplayed()) {
				
				if (gridField[i].isKey())
				{
					listcell.setValue(((IDColumn) currentValues[i]).getRecord_ID());
					if (!table.isCheckmark()) {
						table.setCheckmark(true);
						table.removeEventListener(Events.ON_SELECT, this);
						table.addEventListener(Events.ON_SELECT, this);
						listcell.appendChild(div);
					}
					continue;
				}
				compCount++;
				Component component = getDisplayComponent(currentValues[i], gridField[i]);
				div.appendChild(component);
//				if (compCount == 1) {
					//add hidden input component to help focusing to row
					div.appendChild(createAnchorInput());
//				}

				if (DisplayType.YesNo == gridField[i].getDisplayType() || DisplayType.Image == gridField[i].getDisplayType()) {
					divStyle += "text-align:center; ";
				}
				else if (DisplayType.isNumeric(gridField[i].getDisplayType())) {
					divStyle += "text-align:right; ";
				}
			}
			div.setStyle(divStyle);
			div.setAttribute("columnName", gridField[i].getColumnName());
			//div.addEventListener(Events.ON_CLICK, rowListener);
			//div.addEventListener(Events.ON_DOUBLE_CLICK, rowListener);
			//row.appendChild(div);
			listcell.appendChild(div);
		}
		
		listcell.setParent(item);
		listcell.addEventListener(Events.ON_DOUBLE_CLICK, cellListener);
		//if (rowIndex == gridTab.getCurrentRow()) {
		//	setCurrentRow(row);
		//}
		//row.addEventListener(Events.ON_OK, rowListener);
	}
	
	private Input createAnchorInput() {
		Input input = new Input();
		input.setDynamicProperty("type", "text");
		input.setValue("");
		input.setDynamicProperty("readonly", "readonly");
		input.setStyle("border: none; display: none; width: 3px;");
		return input;
	}
	*/
	private Component getDisplayComponent(Object value, GridField gridField) {
		Component component;
		if (gridField.getDisplayType() == DisplayType.YesNo) {
			component = createReadonlyCheckbox(value);
		} else {
			String text = getDisplayText(value, gridField);

			Label label = new Label();
			setLabelText(text, label);

			component = label;
		}
		return component;
	}

	private Component createReadonlyCheckbox(Object value) {
		Checkbox checkBox = new Checkbox();
		if (value != null && "true".equalsIgnoreCase(value.toString()))
			checkBox.setChecked(true);
		else
			checkBox.setChecked(false);
		checkBox.setDisabled(true);
		return checkBox;
	}
	
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
    	//else if (gridTab.getTableModel().getColumnClass(getColumnIndex(gridField)).equals(Timestamp.class))
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
    		if (value == null || (Integer)value <= 0)
    			return "";
    		else
    			return "...";
    	}
    	else
    		return value.toString();
	}
	
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
	
	private void render(ListItem item, Object data)
	{
		Listcell listcell = null;
		int colIndex = 0;
		int rowIndex = item.getIndex();
		WBrowseListbox table = null;

		if (item.getListbox() instanceof WBrowseListbox)
		{
			table = (WBrowseListbox)item.getListbox();
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

		for (Object field : (List<?>)data)
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
	
	private Listcell getCellComponent(WBrowseListbox table, Object field,
									  int rowIndex, int columnIndex)
	{
		ListCell listcell = new ListCell();
		//boolean isCellEditable = table != null ? table.isCellEditable(rowIndex, columnIndex) : false;

        // TODO put this in factory method for generating cell renderers, which
        // are assigned to Table Columns
		
		GridField gfield = wbListBox.getGridFields().get(columnIndex);
		if (gfield != null)
		{
			boolean isCellEditable = gfield.isReadOnly()==false;
			if (gfield.getDisplayType() == DisplayType.YesNo)
			{
				listcell.setValue(Boolean.valueOf(field.toString()));

				if (table != null && columnIndex == 0)
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
			else if ((gfield.getDisplayType() == DisplayType.Number
						|| gfield.getDisplayType() == DisplayType.Amount
							|| gfield.getDisplayType() == DisplayType.Integer) && !gfield.isKey())
			{
				DecimalFormat format = field instanceof BigDecimal
					? DisplayType.getNumberFormat(DisplayType.Amount, AEnv.getLanguage(Env.getCtx()))
				    : DisplayType.getNumberFormat(DisplayType.Integer, AEnv.getLanguage(Env.getCtx()));

				// set cell value to allow sorting
				listcell.setValue((field==null ?"0" :field.toString()));

				if (isCellEditable)
				{
					NumberBox numberbox = new NumberBox(false);
					numberbox.setFormat(format);
					numberbox.setValue(field);
					numberbox.setWidth("100px");
					numberbox.setEnabled(true);
					numberbox.setStyle("text-align:right; "
									+ listcell.getStyle());
					numberbox.addEventListener(Events.ON_CHANGE, this);
					listcell.appendChild(numberbox);
				}
				else
				{
					listcell.setLabel(format.format(((Number)(field==null?Env.ZERO:field)).doubleValue()));
					ZkCssHelper.appendStyle(listcell, "text-align:right");
				}
			}
			else if (gfield.getDisplayType() == DisplayType.Date)
			{

				SimpleDateFormat dateFormat = DisplayType.getDateFormat(DisplayType.Date, AEnv.getLanguage(Env.getCtx()));
				//listcell.setValue(dateFormat.format((Timestamp)field));
				listcell.setValue(dateFormat.format(field));
				if (isCellEditable)
				{
					Datebox datebox = new Datebox();
					datebox.setFormat(dateFormat.toPattern());
					//datebox.setValue(new Date(((Timestamp)field).getTime()));
					datebox.setValue((Date) field);
					datebox.addEventListener(Events.ON_CHANGE, this);
					listcell.appendChild(datebox);
				}
				else
				{
					//listcell.setLabel(dateFormat.format((Timestamp)field));
					listcell.setLabel(dateFormat.format(field));
				}
			}
			else if (gfield.getDisplayType() == DisplayType.String)
			{
				listcell.setValue((field==null ?"" :field.toString()));
				if (isCellEditable)
				{
					Textbox textbox = new Textbox();
					textbox.setValue((field==null ?"" :field.toString()));
					textbox.addEventListener(Events.ON_CHANGE, this);
					listcell.appendChild(textbox);
				}
				else
				{
					listcell.setLabel((field==null ?"" :field.toString()));
				}
			}
			//----------------------------------------------------------------------------------------
			
			else 
				if (gfield.isLookup() && !gfield.isKey())
			{	
				if (isCellEditable)
				{
					WEditor editor = WebEditorFactory.getEditor(gfield, false);
					editor.setValue(field);
					editor.getComponent().addEventListener(Events.ON_CHANGE, this);
					listcell.appendChild(editor.getComponent());
				}
				else
				{
					Component component;
					if (gfield.getDisplayType() == DisplayType.YesNo) {
						component = createReadonlyCheckbox(field);
					} else {
						String text = getDisplayText(field, gfield);

						Label label = new Label();
						setLabelText(text, label);

						component = label;
					}
					
					listcell.appendChild(component);
				}
			}
			
			
			
			
			//-----------------------------------------------------------------------------------------
			
			
			
			
			// if ID column make it invisible
			else if (gfield.isKey())
			{
				listcell.setValue(((IDColumn) field).getRecord_ID());
				if (!table.isCheckmark()) {
					table.setCheckmark(true);
					table.removeEventListener(Events.ON_SELECT, this);
					table.addEventListener(Events.ON_SELECT, this);
				}
			}
			else
			{
				listcell.setLabel((field==null ?"" :field.toString()));
				listcell.setValue((field==null ?"" :field.toString()));
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
        	if (classType != null && classType.isAssignableFrom(IDColumn.class))
        	{
        		header = new ListHeader("");
        		header.setWidth("20px");
        	}
        	else
        	{
	            Comparator<Object> ascComparator =  getColumnComparator(true, headerIndex);
	            Comparator<Object> dscComparator =  getColumnComparator(false, headerIndex);
	
	            header = new ListHeader(headerText);
	
	            /*header.setSort("auto");
	            header.setSortAscending(ascComparator);
	            header.setSortDescending(dscComparator);
				*/
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

            if (!header.getLabel().equals(headerText))
            {
                header.setLabel(headerText);
            }
        }

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

	/* (non-Javadoc)
	 * @see org.zkoss.zk.ui.event.EventListener#onEvent(org.zkoss.zk.ui.event.Event)
	 */
	public void onEvent(Event event) throws Exception
	{
		int col = -1;
		int row = -1;
		Object value = null;
		TableValueChangeEvent vcEvent = null;
		WTableColumn tableColumn;

		Component source = event.getTarget();

		if (isWithinListCell(source))
		{
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
		else if (event.getTarget() instanceof WBrowseListbox && Events.ON_SELECT.equals(event.getName()))
		{
			WBrowseListbox table = (WBrowseListbox) event.getTarget();
			if (table.isCheckmark()) {
				int cnt = table.getRowCount();
				if (cnt == 0 || !(table.getValueAt(0, 0) instanceof IDColumn))
					return;

				//update IDColumn
				tableColumn = m_tableColumns.get(0);
				for (int i = 0; i < cnt; i++) {
					IDColumn idcolumn = (IDColumn) table.getValueAt(i, 0);
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


