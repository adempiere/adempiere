/******************************************************************************
 * Copyright (C) 2008 Low Heng Sin                                            *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.adempiere.webui.component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.editor.WButtonEditor;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WEditorPopupMenu;
import org.adempiere.webui.editor.WebEditorFactory;
import org.adempiere.webui.event.ContextMenuListener;
import org.adempiere.webui.panel.AbstractADWindowPanel;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.util.GridTabDataBinder;
import org.adempiere.webui.window.ADWindow;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.NamePair;
import org.spin.util.FieldCondition;
import org.spin.util.FieldDefinition;
import org.zkoss.xml.XMLs;
import org.zkoss.zhtml.Label;
import org.zkoss.zhtml.Text;
import org.zkoss.zk.au.out.AuFocus;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Paging;
import org.zkoss.zul.RendererCtrl;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.RowRendererExt;

/**
 * Row renderer for GridTab grid.
 * @author hengsin
 * 
 * @author Teo Sarca, teo.sarca@gmail.com
 * 		<li>BF [ 2996608 ] GridPanel is not displaying time
 * 			https://sourceforge.net/tracker/?func=detail&aid=2996608&group_id=176962&atid=955896
 * @author Raul Muñoz, rMunoz@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1697">
 * 		@see FR [ 1697 ] Add definition for change style</a>
 *
 */
public class GridTabRowRenderer implements RowRenderer, RowRendererExt, RendererCtrl, EventListener {

	private static final String CURRENT_ROW_STYLE = "border-top: 2px solid #1f9bde; border-bottom: 2px solid #1f9bde";
	private static final int MAX_TEXT_LENGTH = 60;
	private GridTab gridTab;
	private int windowNo;
	private GridTabDataBinder dataBinder;
	private Map<GridField, WEditor> editors = new LinkedHashMap<GridField, WEditor>();
	private Paging paging;

	private Map<String, Map<Object, String>> lookupCache = null;
	private RowListener rowListener;
	private Grid grid = null;
	private GridPanel gridPanel = null;
	private Row currentRow = new Row();
	private Map<Row, Map<Integer, Col>> ColCache = new HashMap<Row, Map<Integer, Col>>();
	private int currentColumn = 0;
	private Col currentDiv;
	private Object[] currentValues;
	private boolean editing = false;
	private int currentRowIndex = 0;
	private AbstractADWindowPanel m_windowPanel;
	//	FR [ 1697 ]
	FieldDefinition definition = null;
	private static final String DIVSTYLE = "border: none; width: 100%; height: 100%;";

	private GridField[] gridField;
	private int totalColumns = -1;	
	/**
	 *
	 * @param gridTab
	 * @param windowNo
	 */
	public GridTabRowRenderer(GridTab gridTab, int windowNo) {
		this.gridTab = gridTab;
		this.windowNo = windowNo;
		this.dataBinder = new GridTabDataBinder(gridTab);
		
	}

	private WEditor getEditorCell(GridField gridField, Object object, boolean isNewEditor) {
		WEditor editor;
		if(isNewEditor)
			editor= editors.get(gridField);
		else {
			editor = WebEditorFactory.getEditor(gridField, true);
			editor.setADTabPanel(gridPanel.getADTabPanel());
		}

		if (!gridField.isUpdateable() && gridTab.getRecord_ID() <= 0) {	
			editor.setReadWrite(true);
			editor.dynamicDisplay();
		}	

		if (editor instanceof WButtonEditor) {
			if (m_windowPanel != null)
			{
				((WButtonEditor)editor).addActionListener(m_windowPanel);	
			}
			else
			{
				Object window = SessionManager.getAppDesktop().findWindow(windowNo);
            	if (window != null && window instanceof ADWindow)
            	{
            		AbstractADWindowPanel windowPanel = ((ADWindow)window).getADWindowPanel();
            		((WButtonEditor)editor).addActionListener(windowPanel);
            	}
			}
        }
		else {
			editor.addValueChangeListener(dataBinder);
		}
			gridField.removePropertyChangeListener(editor);
			gridField.addPropertyChangeListener(editor);
			editor.setValue(gridField.getValue());

            //streach component to fill grid cell
            if (editor.getComponent() instanceof Textbox)
            	((HtmlBasedComponent)editor.getComponent()).setWidth("80%");
            else
            	editor.fillHorizontal();
            
            
		return editor;
	}

	private int getColumnIndex(GridField field) {
		if(gridField == null)
			gridField = gridTab.getFields();
		for(int i = 0; i < gridField.length; i++) {
			if (gridField[i] == field)
				return i;
		}
		return 0;
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
    	else if (gridTab.getTableModel().getColumnClass(getColumnIndex(gridField)).equals(Timestamp.class))
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

	/**
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
	 *
	 * @return active editor list
	 */
	public List<WEditor> getEditors() {
		List<WEditor> editorList = new ArrayList<WEditor>();
		if (!editors.isEmpty())
			editorList.addAll(editors.values());

		return editorList;
	}

	/**
	 * @param paging
	 */
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	
	public void stopColEditing(boolean updateCellLabel) {
		if (!editing) {
			return;
		} else {
			editing = false;
		}
		if(currentDiv.getEditor() != null) {
			WEditor entry = currentDiv.getEditor();
		
			Component component = currentDiv.getFirstChild();
			if (updateCellLabel) {
				if (component instanceof Label) {
					Label label = (Label)component;
					label.getChildren().clear();
					String text = getDisplayText(entry.getValue(), entry.getGridField());
					setLabelText(text, label);
				} else if (component instanceof Checkbox) {
					Checkbox checkBox = (Checkbox)component;
					Object value = entry.getValue();
					if (value != null && "true".equalsIgnoreCase(value.toString()))
						checkBox.setChecked(true);
					else
						checkBox.setChecked(false);
				}
			}
			component.setVisible(true);
			entry.setVisible(false);
			
//			currentDiv.setFocus(true);
		}
		

		GridTableListModel model = (GridTableListModel) grid.getModel();
		model.setEditing(false);
	}


	/**
	 * @param row
	 * @param data
	 * @see RowRenderer#render(Row, Object)
	 */
	public void render(Row row, Object data) throws Exception {
		
		//don't render if not visible
		if (gridPanel != null && !gridPanel.isVisible()) {
			return;
		}
		
		if (grid == null)
			grid = (Grid) row.getParent().getParent();

		if (rowListener == null)
			rowListener = new RowListener((Grid)row.getParent().getParent());

		if(gridField == null)
			gridField = gridTab.getFields();
		
		currentValues = (Object[])data;
		
		int columnCount = gridTab.getTableModel().getColumnCount();
		int rowIndex = row.getParent().getChildren().indexOf(row);
		if (paging != null && paging.getPageSize() > 0) {
			rowIndex = (paging.getActivePage() * paging.getPageSize()) + rowIndex;
		}
		 //	FR [ 1697 ] 
		 HashMap<String, Object> columnValues = new  HashMap<String, Object>();
		
		for (int i = 0; i < columnCount; i++) {
			columnValues.put(gridField[i].getColumnName(), currentValues[i]);
		}
		int colIndex = -1;
		for (int i = 0; i < columnCount; i++) {
			if (gridTab.isQuickEntry() 
					&& !gridField[i].isQuickEntry())
				{
				continue;
				}
			if (!(gridField[i].isDisplayed() ) ) {
				continue;
			}
			
			colIndex ++;

			String divStyle = DIVSTYLE;
			Component component=null;
			Col div = new Col();
			org.zkoss.zul.Column column = (org.zkoss.zul.Column) grid.getColumns().getChildren().get(colIndex);
			if (column.isVisible()) {
				component = getDisplayComponent(currentValues[i], gridField[i]);
				div.appendChild(component);

				if (DisplayType.YesNo == gridField[i].getDisplayType() || DisplayType.Image == gridField[i].getDisplayType()) {
					div.setTextAlign("center"); 
				}
				else if (DisplayType.isNumeric(gridField[i].getDisplayType())) {
					div.setTextAlign("right");
				}
				else {
					div.setTextAlign("left"); 
				}
			}

			//	FR [ 1697 ]
			//	Validate field condition
			if(gridField[i].getAD_FieldDefinition_ID() > 0) {
				definition = FieldDefinition.getInstance(gridField[i].getVO());
				FieldCondition condition = definition.getConditionValid(columnValues);
				div.setStyle(divStyle);
				if(condition != null && condition.isValid()) {
					div.setStyle(divStyle + condition.getStyleSheet());
				}
			}
			
			div.setReadOnly(!gridField[i].isEditable(true));
			div.setAttribute("columnName", gridField[i].getColumnName());
			div.setAttribute("columnNo", colIndex);
			div.setText(getDisplayText(currentValues[i], gridField[i]));
			div.addEventListener(Events.ON_CLICK, rowListener);
			div.addEventListener(Events.ON_DOUBLE_CLICK, rowListener);

			Map<Integer, Col> cache = new HashMap<Integer, Col>();
			cache.put(colIndex,div);
			ColCache.put(row, cache);
			row.appendChild(div);
			GridTableListModel model = (GridTableListModel) grid.getModel();
			model.setEditing(false);
			
			totalColumns=colIndex;
			if (rowIndex == gridTab.getCurrentRow()) {
				setCurrentRow(row);
			}
		}
		row.addEventListener(Events.ON_OK, rowListener);
		row.invalidate();
	}

	/**
	 * @param row
	 */
	public void setCurrentRow(Row row) {
//		if(isEditing() && currentRowIndex > 1 )
//			stopColEditing(true);
		currentRow.setStyle("");
		currentRow = row;
		Clients.scrollIntoView(currentRow);
		if (currentRowIndex != gridTab.getCurrentRow()) {
			currentRowIndex = gridTab.getCurrentRow();
		}
		setCurrentColumn(currentColumn);
	}

	/**
	 * @return Row
	 */
	public Row getCurrentRow() {
		return currentRow;
	}

	/**
	 * @return current row index ( absolute )
	 */
	public int getCurrentRowIndex() {
		return currentRowIndex;
	}

	/**
	 * Enter edit mode
	 */
	public boolean editCurrentCol(boolean showEditor) {
		if (grid != null && grid.isVisible() && grid.getParent() != null 
				&& grid.getParent().isVisible() && totalColumns > 0) {

			int colIndex = (Integer)currentDiv.getAttribute("columnNo");
			String colName = (String)currentDiv.getAttribute("columnName");
			GridField gridField = gridTab.getField(colName);
			org.zkoss.zul.Columns columns = grid.getColumns();
			WEditor editor = currentDiv.getEditor();
			if (editor == null) {	
				editor = getEditorCell(gridField, currentValues[colIndex], false);
			}
			editor.setADTabPanel(gridPanel.getADTabPanel());
			org.zkoss.zul.Column column = (org.zkoss.zul.Column) columns.getChildren().get(colIndex);
			if (column.isVisible() && !(gridField.isReadOnly()) ) {
				editor.setVisible(false);
				WEditorPopupMenu popupMenu = editor.getPopupMenu();
				currentDiv.setEditor(editor);

				if (popupMenu != null) {
	            	popupMenu.addMenuListener((ContextMenuListener)editor);
	            	currentDiv.appendChild(popupMenu);
	            }
	            //check context
				if (!gridField.isDisplayed(true)) {
					editor.setVisible(false);
				}
						
				editor.setReadWrite(gridField.isEditable(true));
				if(currentDiv.getAnchorInput() == null)
					currentDiv.createAnchorInput();
				

				String componentUuId = currentDiv.getComponent().getUuid();
				Clients.evalJavaScript("$('#"+currentDiv.getAnchorInput().getUuid()+"')."
						+ "keyup(function(event) {"
						+ "$('#"+componentUuId+" :input').each(function(){"
						+" if (event.keyCode >= 48 && event.keyCode <= 90 || event.keyCode >= 96 && event.keyCode <= 105) {" 
						+ "$(this).val(event.key);"
						+ "}"
						+"$(this).focus();"
						+ "}});");
				
				if (currentDiv.getComponent() instanceof NumberBox) {
					componentUuId = ((Component)((Component)((Component)((Component)currentDiv.getComponent().getChildren().get(0)).getChildren().get(0)).getChildren().get(0)).getChildren().get(0)).getUuid();
				}
				
				Clients.evalJavaScript("$('#"+currentDiv.getAnchorInput().getUuid()+"')."
						+ "keyup(function(event) {"
						+"if (event.keyCode == 13) {"
						+ "$('#"+componentUuId+"').show();"
						+ "}"
						+ "$('#"+componentUuId+"').focus();"
						+ "$('#"+componentUuId+"').find('input').focus();"
						+" if (event.keyCode >= 48 && event.keyCode <= 90 || event.keyCode >= 96 && event.keyCode <= 105) {" 
						+ "$('#"+componentUuId+"').val(event.key);"
						+ "$('#"+componentUuId+"').find('input').val(event.key);						}});");
				
				editing = gridField.isEditable(true);
			}  
			GridTableListModel model = (GridTableListModel) grid.getModel();
			if(showEditor) {
				if(!(gridField.isReadOnly())) {
					showEditor();
					model.setEditing(true);
					
				}
			}	
			else {
				if(gridField.getDisplayType() == DisplayType.Button)  {
					showEditor();
				}
				currentDiv.setFocus(true);
				editing = false;
				model.setEditing(false);
			
			}
		}
		return editing;
	}

	/**
	 * @see RowRendererExt#getControls()
	 */
	public int getControls() {
		return DETACH_ON_RENDER;
	}

	/**
	 * @see RowRendererExt#newCell(Row)
	 */
	public Component newCell(Row row) {
		return null;
	}

	/**
	 * @see RowRendererExt#newRow(Grid)
	 */
	public Row newRow(Grid grid) {
		return null;
	}

	/**
	 * @see RendererCtrl#doCatch(Throwable)
	 */
	public void doCatch(Throwable ex) throws Throwable {
		lookupCache = null;
	}

	/**
	 * @see RendererCtrl#doFinally()
	 */
	public void doFinally() {
		lookupCache = null;
	}

	/**
	 * @see RendererCtrl#doTry()
	 */
	public void doTry() {
		lookupCache = new HashMap<String, Map<Object,String>>();
	}

	/**
	 * set focus to first active editor
	 */
	public void setFocusToEditor() {
//		if (currentRow != null && currentRow.getParent() != null ) {
			
			String componentUuId = currentDiv.getComponent().getUuid();

			Clients.evalJavaScript("$('#"+componentUuId+"').focus();");
			
			if (currentDiv.getComponent() instanceof NumberBox) {
				componentUuId = ((Component)((Component)((Component)((Component)currentDiv.getComponent().getChildren().get(0)).getChildren().get(0)).getChildren().get(0)).getChildren().get(0)).getUuid();
				Clients.evalJavaScript("$('#"+componentUuId+"').focus();");
			}
			else if(currentDiv.getComponent() instanceof Combobox) {
				Clients.evalJavaScript("$('#"+componentUuId+"').focus();");
				Clients.evalJavaScript("$( '#"+componentUuId+"' ).find('input').focus();");

			}
			WEditor toFocus = currentDiv.getEditor();
			
			if (toFocus != null) {
				Component c = toFocus.getComponent();
				if (c instanceof Textbox) {
					((Textbox)c).focus();
				}
				else if (c instanceof NumberBox) {
					((NumberBox)c).focus();
				}
				else if (c instanceof Decimalbox) {
					((Decimalbox)c).focus();
				}
				else if (c instanceof EditorBox) {
					c = ((EditorBox)c).getTextbox();
					((Textbox) c).focus();
				}
				Clients.response(new AuFocus(c));
			} 
//		}
	}

	/**
	 *
	 * @param gridPanel
	 */
	public void setGridPanel(GridPanel gridPanel) {
		this.gridPanel = gridPanel;
	}

	/**
	 *
	 * @param grid
	 */
	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	class RowListener implements EventListener {

		private Grid _grid;

		public RowListener(Grid grid) {
			_grid = grid;
		}

		public void onEvent(Event event) throws Exception {
			if (Events.ON_CLICK.equals(event.getName())) {
				
				Event evt = new Event(Events.ON_CLICK, _grid, event.getTarget());
				Events.sendEvent(_grid, evt);
			}
			else if (Events.ON_DOUBLE_CLICK.equals(event.getName())) {
				Event evt = new Event(Events.ON_DOUBLE_CLICK, _grid, _grid);
				Events.sendEvent(_grid, evt);
			}
			else if (Events.ON_OK.equals(event.getName())) {
				Event evt = new Event(Events.ON_OK, _grid, _grid);
				Events.sendEvent(_grid, evt);
			}
			else if (Events.ON_FOCUS.equals(event.getName())) {
				currentDiv.setFocus(true);
			}
			else if (Events.ON_BLUR.equals(event.getName())) {
				currentDiv.setFocus(false);
			}
		}
	}

	/**
	 * @return boolean
	 */
	public boolean isEditing() {
		return editing;
	}

	/**
	 * @param windowPanel
	 */
	public void setADWindowPanel(AbstractADWindowPanel windowPanel) {
		this.m_windowPanel = windowPanel;
	}
	
	/**
	 * Valid Condition for Change Div Style
	 */
	public void validCondition() {
		if(definition == null)
			return;
		FieldCondition condition = definition.getConditionValid(null);
		if(condition == null)
			return;
			
		Row currentRow = getCurrentRow();
		
		String divStyle = DIVSTYLE;
		GridField[] gridField = gridTab.getFields();
		//	Iterate it
		for(int i=0; i < gridField.length; i++) {
			if(gridField[i].getAD_FieldDefinition_ID() != 0) {
				if(condition.isValid(null)) {
					if (DisplayType.YesNo == gridField[i].getDisplayType() || DisplayType.Image == gridField[i].getDisplayType()) {
						divStyle += "text-align:center; ";
					} else if (DisplayType.isNumeric(gridField[i].getDisplayType())) {
						divStyle += "text-align:right; ";
					}
					List<?> divList = currentRow.getChildren();
					for(int j=0; j< divList.size(); j++ ) {
						if(divList.get(j) instanceof Div) {
							Div div = (Div)divList.get(j);
							if(div.getAttribute("columnName").equals(gridField[i].getColumnName())) {
								div.setStyle(divStyle + condition.getStyleSheet());
								div.invalidate();
							}
						}
					}
				}
			}
		}
	}
	
	 /**
	 * Set Current Column
	 * @param col
	 * @return
	 */
	public boolean setCurrentColumn(int col) {
		int pgIndex;
		if(paging != null)
			pgIndex = currentRowIndex >= 0 ? currentRowIndex % paging.getPageSize() : 0;
		else
			pgIndex = currentRowIndex;
		if(grid != null) {
			org.zkoss.zul.Row row = (org.zkoss.zul.Row) grid.getRows().getChildren().get(pgIndex);
			currentRow.setStyle("");
			currentRow = row;
			if(!gridTab.isQuickEntry())
				currentRow.setStyle(CURRENT_ROW_STYLE);
		}
		if(isEditing() && currentRowIndex > 1 )
			stopColEditing(true);
		if(col < 0 || col >= currentRow.getChildren().size())
			return false;
		if(col <= totalColumns) {
			currentColumn = col;
			if(currentDiv != null) {
				currentDiv.addEventListener(Events.ON_CLICK, rowListener);
				if(currentDiv.getComponent() != null 
					&& currentDiv.getEditor().getGridField().getDisplayType() != DisplayType.YesNo) {
						currentDiv.getComponent().setVisible(false);
				}
				currentDiv.setFocus(false);
			}
		
			gridTab.setCurrentCol(col);
			Component cmp = grid.getCell(pgIndex, currentColumn);
			if(grid.getRows().getChildren().size() > 0 && cmp instanceof Col) {
				setCurrentDiv((Col)cmp);
				
			}
			editCurrentCol(false);
		}
		return true;
	}
	
	public int getCurrentColumn() {
		return currentColumn;
	}
	public void setCurrentDiv(Col col) {
		
		if(col.getAnchorInput() == null)
			col.createAnchorInput();
		col.getAnchorInput().addEventListener(Events.ON_CHANGING, this);
		col.setFocus(true);
		col.invalidate();

		currentDiv = col;
	}
	
	public Col getCurrentDiv() {
		return currentDiv;
	}
	
	public void setCurrentCell(int row) {
		int pgIndex = row >= 0 ? row % paging.getPageSize() : 0;
		if (row != currentRowIndex || pgIndex != currentRowIndex)
		{
			if (grid.getRows().getChildren().size() <= 0)
			{
				currentColumn = -1;
				return;
			}
			
			gridTab.setCurrentRow(row);
			
			currentRowIndex = gridTab.getCurrentRow();
		}

	}

	public void showEditor() {
		if(currentDiv.getEditor() != null && !currentDiv.getEditor().isVisible() ) {
			currentDiv.getEditor().setVisible(true); 
			currentDiv.getFirstChild().setVisible(false);
			 currentDiv.setFocus(false); 
			 setFocusToEditor();
			 ((GridTableListModel)grid.getModel()).setEditing(true);
			 editing=true;
		 } 
	}
	
	/**
	 * Check is Last Column
	 * @return
	 */
	public boolean isLastColumn() {
		if(currentColumn == totalColumns)
			return true;
		else
			return false;
	}
	
	public int getTotalColumns() {
		return totalColumns;
	}
	@Override
	public void onEvent(Event e) throws Exception {

		if(Events.ON_CHANGE.equals(e.getName()) || e.getName().equals(Events.ON_CHANGING)) {
			if(((InputEvent)e).getValue().length() > 0 ) {
				showEditor();
			}
		}
	}
	
}
