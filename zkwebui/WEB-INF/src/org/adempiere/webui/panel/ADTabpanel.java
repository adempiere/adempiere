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

package org.adempiere.webui.panel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;

import org.adempiere.webui.LayoutUtils;
import org.adempiere.webui.component.Bandbox;
import org.adempiere.webui.component.Datebox;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridPanel;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.SimpleTreeModel;
import org.adempiere.webui.component.ToolBar;
import org.adempiere.webui.editor.IZoomableEditor;
import org.adempiere.webui.editor.WButtonEditor;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WEditorPopupMenu;
import org.adempiere.webui.editor.WebEditorFactory;
import org.adempiere.webui.event.ContextMenuListener;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.DataStatusEvent;
import org.compiere.model.DataStatusListener;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.GridTable;
import org.compiere.model.GridWindow;
import org.compiere.model.MLookup;
import org.compiere.model.MTree;
import org.compiere.model.MTreeNode;
import org.compiere.model.X_AD_FieldGroup;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Evaluatee;
import org.zkoss.zhtml.Span;
import org.zkoss.zhtml.Text;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.West;
import org.zkoss.zul.Div;
import org.zkoss.zul.Separator;
import org.zkoss.zul.SimpleTreeNode;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treeitem;

/**
 * 
 * This class is based on org.compiere.grid.GridController written by Jorg Janke.
 * Changes have been brought for UI compatibility.
 * 
 * @author Jorg Janke
 * 
 * @author <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date Feb 25, 2007
 * @version $Revision: 0.10 $
 *
 * @author Low Heng Sin 
 */
public class ADTabpanel extends Div implements Evaluatee, EventListener, 
DataStatusListener, ValueChangeListener, IADTabpanel
{
    
    private static final CLogger logger;
    
    static
    {
        logger = CLogger.getCLogger(ADTabpanel.class);
    }

    private GridTab           gridTab;

    private GridWindow        gridWindow;

    private AbstractADWindowPanel      windowPanel;

    private int               windowNo;

    private Grid              grid;
    
    private ArrayList<WEditor> editors = new ArrayList<WEditor>();
    
    private ArrayList<String> editorIds = new ArrayList<String>();
    
    private ArrayList<String> fieldGroupIds = new ArrayList<String>();
    
    private boolean           editing;
    
    private boolean			  uiCreated = false;
    
    private GridPanel		  listPanel;
    
    private Map<String, List<Row>> fieldGroupContents = new HashMap<String, List<Row>>();
    
    private Map<String, List<Row>> fieldGroupHeaders = new HashMap<String, List<Row>>();

	private ArrayList<Row> rowList;
	
	private Component formComponent = null;
	
	private Tree tree = null;

	public ADTabpanel() 
	{
        init();
    }

    private void init()
    {
        initComponents();
    }

    private void initComponents()
    {
    	LayoutUtils.addSclass("adtab-content", this);    	    	    		
    	
        grid = new Grid();
        //have problem moving the following out as css class
        grid.setWidth("99%");
        grid.setHeight("100%");                
        grid.setSclass("grid-no-striped");
        grid.setStyle("margin:0; padding:0; position: absolute");
        grid.setOddRowSclass("even");
        
        
        listPanel = new GridPanel();
        listPanel.getListbox().addEventListener(Events.ON_DOUBLE_CLICK, this);
                
    }

    public void init(AbstractADWindowPanel winPanel, int windowNo, GridTab gridTab,
            GridWindow gridWindow)
    {
        this.windowNo = windowNo;
        this.gridWindow = gridWindow;
        this.gridTab = gridTab;
        this.windowPanel = winPanel;
        gridTab.addDataStatusListener(this);
        
        this.getChildren().clear();
                
        int AD_Tree_ID = 0;
		if (gridTab.isTreeTab())
			AD_Tree_ID = MTree.getDefaultAD_Tree_ID (
				Env.getAD_Client_ID(Env.getCtx()), gridTab.getKeyColumnName());
		if (gridTab.isTreeTab() && AD_Tree_ID != 0)
		{
			Borderlayout layout = new Borderlayout();
			layout.setParent(this);
			layout.setStyle("width: 100%; height: 100%; position: absolute;");
		
			tree = new Tree();
			tree.setStyle("border: none");
			West west = new West();
			west.appendChild(tree);
			west.setWidth("300px");
			west.setCollapsible(true);
			west.setSplittable(true);
			west.setAutoscroll(true);
			layout.appendChild(west);
			
			Center center = new Center();
			center.setFlex(true);
			center.appendChild(grid);
			layout.appendChild(center);
			
			formComponent = layout;
			tree.addEventListener(Events.ON_SELECT, this);
		}
		else
		{
			this.appendChild(grid);
			formComponent = grid;
		}
        this.appendChild(listPanel);
        listPanel.setVisible(false);
    }

    public void createUI()
    {
    	if (uiCreated) return;
    	
    	uiCreated = true;
    	Rows rows = new Rows();
        GridField fields[] = gridTab.getFields();
        Row row = new Row();
        
        String currentFieldGroup = null;
        for (int i = 0; i < fields.length; i++)
        {
            GridField field = fields[i];
            if (field.isDisplayed())
            {
            	String fieldGroup = field.getFieldGroup();
            	if (fieldGroup != null && fieldGroup.trim().length() > 0)
            	{
            		if (!fieldGroup.equals(currentFieldGroup)) 
            		{
            			currentFieldGroup = fieldGroup;
            			if (row.getChildren().size() == 2)
            			{
            				row.appendChild(createSpacer());
                            row.appendChild(createSpacer());
                            row.appendChild(createSpacer());
                            rows.appendChild(row);
                            if (rowList != null)
                				rowList.add(row);
                            row = new Row();
            			} else if (row.getChildren().size() > 0) 
            			{
            				rows.appendChild(row);
            				if (rowList != null)
                				rowList.add(row);
            				row = new Row();
            			}
            			
            			List<Row> headerRows = new ArrayList<Row>();
            			fieldGroupHeaders.put(fieldGroup, headerRows);
            			
            			row.setSpans("5");
            			row.appendChild(new Separator());
            			rows.appendChild(row);
            			headerRows.add(row);
            			            			
        				rowList = new ArrayList<Row>();
        				fieldGroupContents.put(fieldGroup, rowList);
            			
            			row = new Row();            			
            			row.setSpans("4");
            			if (X_AD_FieldGroup.FIELDGROUPTYPE_Label.equals(field.getFieldGroupType())) 
            			{
            				Label groupLabel = new Label(fieldGroup); 
            				row.appendChild(groupLabel);
            				row.appendChild(createSpacer());
            				rows.appendChild(row);
            				headerRows.add(row);
            				
            				row = new Row();
	                        row.setSpans("4");
	                        Separator separator = new Separator();
	                        separator.setBar(true);
	            			row.appendChild(separator);
	            			row.appendChild(createSpacer());
	            			rows.appendChild(row);
	            			headerRows.add(row);
            			}
            			else
            			{
            				ToolBar toolbar = new ToolBar();
            				Toolbarbutton button = new Toolbarbutton(fieldGroup, "images/ns-collapse.gif");
            				button.addEventListener(Events.ON_CLICK, this);
            				button.setParent(toolbar);
            				row.appendChild(toolbar);
            				row.appendChild(createSpacer());
            				rows.appendChild(row);
            				headerRows.add(row);
            			}
            			
            			row = new Row();
            		}
            	}
            	
                if (!field.isSameLine())
                {
                	if(row.getChildren().size() > 0)
                	{
	                    if (row.getChildren().size() == 2)
	                    {
	                        row.appendChild(createSpacer());
	                        row.appendChild(createSpacer());
	                        row.appendChild(createSpacer());
	                    }
	                    {
	                    	row.appendChild(createSpacer());
	                    }
	                    rows.appendChild(row);
	                    if (rowList != null)
	        				rowList.add(row);
	                    row = new Row();
                	}
                } 
                else if (row.getChildren().size() == 4) 
                {
                	row.appendChild(createSpacer());
                	rows.appendChild(row);
                    if (rowList != null)
        				rowList.add(row);
                    row = new Row();
                }

                WEditor editor = WebEditorFactory.getEditor(gridTab, field, false);
                
                if (editor != null) // Not heading
                {
                    editor.setGridTab(this.getGridTab());
                	field.addPropertyChangeListener(editor);
                    editors.add(editor);
                    editorIds.add(editor.getComponent().getUuid());
                    Div div = new Div();
                    div.setAlign("right");
                    Label label = editor.getLabel();
                    div.appendChild(label);
                    row.appendChild(div);
                    row.appendChild(editor.getComponent());
                    if (field.isLongField()) {
                    	row.setSpans("1,3,1");
                    	row.appendChild(createSpacer());
                    	rows.appendChild(row);
                    	if (rowList != null)
            				rowList.add(row);
                    	row = new Row();
                    }
                    
                    if (editor instanceof WButtonEditor)
                    {
                    	((WButtonEditor)editor).addActionListener(windowPanel);
                    }
                    else
                    {
                    	editor.addValueChangeListner(this);
                    }
                    
                    if (editor.getComponent() instanceof HtmlBasedComponent) {
                    	//can't stretch bandbox & datebox
                    	if (!(editor.getComponent() instanceof Bandbox) && !(editor.getComponent() instanceof Datebox))
                    		((HtmlBasedComponent)editor.getComponent()).setWidth("100%");
                    }
                    
                    WEditorPopupMenu popupMenu = editor.getPopupMenu();
                    
                    if (popupMenu != null)
                    {
                    	popupMenu.addMenuListener((ContextMenuListener)editor);
                        this.appendChild(popupMenu);
                        if (popupMenu.isZoomEnabled() && editor instanceof IZoomableEditor) 
                        {
                        	label.setStyle("cursor: pointer; text-decoration: underline;");
                        	label.addEventListener(Events.ON_CLICK, new ZoomListener((IZoomableEditor) editor));
                        }
                        
                        label.setContext(popupMenu.getId());
                    }
                }
            }
        }
        if (row.getChildren().size() > 0)
        {
            if (row.getChildren().size() == 2)
            {
                row.appendChild(createSpacer());
                row.appendChild(createSpacer());
                row.appendChild(createSpacer());
            }
            rows.appendChild(row);
            if (rowList != null)
				rowList.add(row);
        }
        grid.appendChild(rows);
        
        if (gridTab.isTreeTab() && tree != null) {
			int AD_Tree_ID = MTree.getDefaultAD_Tree_ID (
				Env.getAD_Client_ID(Env.getCtx()), gridTab.getKeyColumnName());
			SimpleTreeModel.initADTree(tree, AD_Tree_ID, windowNo);
        }
    }

	private Component createSpacer() {
		Span span = new Span();
		Text text = new Text("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		text.setParent(span);
		span.setStyle("width: 100%");
		return span;
	}
    
    public void dynamicDisplay (int col)
    {
        if (!gridTab.isOpen())
        {
            return;
        }
        
        //  Selective
        if (col > 0)
        {
            GridField changedField = gridTab.getField(col);
            String columnName = changedField.getColumnName();
            ArrayList dependants = gridTab.getDependantFields(columnName);
            logger.config("(" + gridTab.toString() + ") "
                + columnName + " - Dependents=" + dependants.size());
            if (dependants.size() == 0 && changedField.getCallout().length() > 0)
            {
                for (WEditor comp : editors)
                {
                    if (columnName.equals(comp.getColumnName()))
                    {
                        boolean manMissing = false;
                        boolean noValue = changedField.getValue() == null || changedField.getValue().toString().length() == 0;
                        if (noValue && changedField.isEditable(true) && changedField.isMandatory(true))
                        {
                            manMissing = true;
                        }
                        comp.setBackground(manMissing || changedField.isError());
                        break;
                    }
                }
                return;
            }
        }

        boolean noData = gridTab.getRowCount() == 0;
        logger.config(gridTab.toString() + " - Rows=" + gridTab.getRowCount());
        for (WEditor comp : editors)
        {
            GridField mField = comp.getGridField();
            if (mField != null)
            {
                if (mField.isDisplayed(true))       //  check context
                {
                    if (!comp.isVisible())
                    {
                        comp.setVisible(true);      //  visibility
                    }
                    if (noData)
                    {
                        comp.setReadWrite(false);
                    }
                    else
                    {
                        boolean rw = mField.isEditable(true);   //  r/w - check Context
                        comp.setReadWrite(rw);
                        boolean manMissing = false;
                        if (rw && mField.getValue() == null && mField.isMandatory(true))    //  check context
                        {
                            manMissing = true;
                        }
                        comp.setBackground(manMissing || mField.isError());
                    }
                }
                else if (comp.isVisible())
                {
                    comp.setVisible(false);
                }
            }
        }   //  all components
        
        //hide row if all editor within the row is invisible
        List rows = grid.getRows().getChildren();
        for(int i = 0; i < rows.size(); i++) 
        {
        	Row row = (Row) rows.get(i);
        	List components = row.getChildren();
        	boolean visible = false;
        	boolean editorRow = false;
        	for (int j = 0; j < components.size(); j++)
        	{
        		Component component = (Component) components.get(j);
        		if (editorIds.contains(component.getUuid())) 
        		{
        			editorRow = true;
        			if (component.isVisible())
        			{
        				visible = true;
        				break;
        			}
        		}
        	}
        	if (editorRow && (row.isVisible() != visible))
        		row.setVisible(visible);
        }
        
        //hide fieldgroup is all editor row within the fieldgroup is invisible
        for(Iterator<Entry<String, List<Row>>> i = fieldGroupHeaders.entrySet().iterator(); i.hasNext();)
        {
        	Map.Entry<String, List<Row>> entry = i.next();
        	List<Row> contents = fieldGroupContents.get(entry.getKey());
        	boolean visible = false;
        	for (Row row : contents)
        	{
        		if (row.isVisible())
        		{
        			visible = true;
        			break;
        		}
        	}
        	List<Row> headers = entry.getValue();
        	for(Row row : headers)
        	{
        		if (row.isVisible() != visible)
        			row.setVisible(visible);
        	}
        }
        
        logger.config(gridTab.toString() + " - fini - " + (col<=0 ? "complete" : "seletive"));
    }   //  dynamicDisplay

    public String getDisplayLogic()
    {
        return gridTab.getDisplayLogic();
    }

    public String getTitle()
    {
        return gridTab.getName();
    } // getTitle

    /**
     * @param variableName
     */
    public String get_ValueAsString(String variableName)
    {
        return Env.getContext(Env.getCtx(), windowNo, variableName);
    } // get_ValueAsString

    /**
     * @return The tab level of this Tabpanel
     */
    public int getTabLevel()
    {
        return gridTab.getTabLevel();
    }

    public boolean isCurrent()
    {
        return gridTab != null ? gridTab.isCurrent() : false;
    }

    public int getWindowNo()
    {
        return windowNo;
    }

    public void query()
    {
        gridTab.query(false);
    }
    
    public void query (boolean onlyCurrentRows, int onlyCurrentDays, int maxRows)
    {
//        gridTab.query(onlyCurrentRows, onlyCurrentDays, maxRows);
        gridTab.query(false);
    }
    
    public GridTab getGridTab()
    {
        return gridTab;
    }
    
    public void refresh()
    {
        gridTab.dataRefresh();
    }
    
    public void activate(boolean activate)
    {
        if (listPanel.isVisible()) {
        	if (activate)
        		listPanel.activate(gridTab);
        	else
        		listPanel.deactivate();
        } else {
        	if (activate)
        		formComponent.setVisible(activate);
        }
    }
    
    public boolean isEditing()
    {
        return this.editing;
    }
    
    public void editRecord(boolean edit)
    {
       /* this.editing = edit;
        for (Editor editor : editors)
        {
            editor.setEditing(edit);
        }*/
    }

    public boolean isAsap()
    {
        return false;
    }

    public void onEvent(Event event)
    {
    	if (event.getTarget() instanceof Toolbarbutton) 
    	{
    		Toolbarbutton button = (Toolbarbutton) event.getTarget();
    		List<Row> list = fieldGroupContents.get(button.getLabel());
    		if (list.get(0).isVisible()) {
    			for (Row row : list) {
    				row.setVisible(false);
    			}
    			button.setImage("images/ns-expand.gif");
    		} else {
    			for (Row row : list) {
    				row.setVisible(true);
    			}
    			button.setImage("images/ns-collapse.gif");
    		}
    	} else if (event.getTarget() instanceof Listbox) 
    	{
    		this.switchRowPresentation();
    	} 
    	else if (event.getTarget() == tree) {
    		Treeitem item =  tree.getSelectedItem();
    		navigateTo((SimpleTreeNode)item.getValue());
    	}
    }

    private void navigateTo(SimpleTreeNode value) {
    	MTreeNode treeNode = (MTreeNode) value.getData();
    	//  We Have a TreeNode
		int nodeID = treeNode.getNode_ID();
		//  root of tree selected - ignore
		//if (nodeID == 0)
			//return;

		//  Search all rows for mode id
		int size = gridTab.getRowCount();
		int row = -1;
		for (int i = 0; i < size; i++)
		{
			if (gridTab.getKeyID(i) == nodeID)
			{
				row = i;
				break;
			}
		}
		if (row == -1)
		{
			if (nodeID > 0)
				logger.log(Level.WARNING, "Tab does not have ID with Node_ID=" + nodeID);
			return;
		}

		//  Navigate to node row
		gridTab.navigate(row);		
	}

	public void dataStatusChanged(DataStatusEvent e)
    {
    	//ignore background event
    	if (Executions.getCurrent() == null) return;
    	
        int col = e.getChangedColumn();
        logger.config("(" + gridTab + ") Col=" + col + ": " + e.toString());

        //  Process Callout
        GridField mField = gridTab.getField(col);
        if (mField != null 
            && (mField.getCallout().length() > 0 || gridTab.hasDependants(mField.getColumnName())))
        {
            String msg = gridTab.processFieldChange(mField);     //  Dependencies & Callout
            if (msg.length() > 0)
            {
                FDialog.error(windowNo, this, msg);
            }
            
            // Refresh the list on dependant fields
    		ArrayList list = gridTab.getDependantFields(mField.getColumnName());
    		for (int i = 0; i < list.size(); i++)
    		{
    			GridField dependentField = (GridField)list.get(i);
    		//	log.trace(log.l5_DData, "Dependent Field", dependentField==null ? "null" : dependentField.getColumnName());
    			//  if the field has a lookup
    			if (dependentField != null && dependentField.getLookup() instanceof MLookup)
    			{
    				MLookup mLookup = (MLookup)dependentField.getLookup();
    				//  if the lookup is dynamic (i.e. contains this columnName as variable)
    				if (mLookup.getValidation().indexOf("@"+mField.getColumnName()+"@") != -1)
    				{
    					mLookup.refresh();
    				}
    			}
    		}   //  for all dependent fields
            
        }
        //if (col >= 0)
        dynamicDisplay(col);
        
        //sync tree 
        if (tree != null) {
        	if ("Deleted".equalsIgnoreCase(e.getAD_Message()))
        		if (e.Record_ID != null 
        				&& e.Record_ID instanceof Integer 
        				&& ((Integer)e.Record_ID != gridTab.getRecord_ID()))
        			deleteNode((Integer)e.Record_ID);
        		else
        			setSelectedNode(gridTab.getRecord_ID());
        	else
        		setSelectedNode(gridTab.getRecord_ID());
        }
    }
    
    private void deleteNode(int recordId) {
		if (recordId <= 0) return;
		
		SimpleTreeModel model = (SimpleTreeModel) tree.getModel();
		
		if (tree.getSelectedItem() != null) {
			SimpleTreeNode treeNode = (SimpleTreeNode) tree.getSelectedItem().getValue();
			MTreeNode data = (MTreeNode) treeNode.getData();
			if (data.getNode_ID() == recordId) {
				model.removeNode(treeNode);
				return;
			}
		}
		
		SimpleTreeNode treeNode = model.find(null, recordId);
		if (treeNode != null) {
			model.removeNode(treeNode);
		} 
	}

	private void addNewNode() {
    	if (gridTab.getRecord_ID() > 0) {
	    	String name = (String)gridTab.getValue("Name");
			String description = (String)gridTab.getValue("Description");
			boolean summary = gridTab.getValueAsBoolean("IsSummary"); 
			String imageIndicator = (String)gridTab.getValue("Action");  //  Menu - Action
			//
			SimpleTreeModel model = (SimpleTreeModel) tree.getModel();
			SimpleTreeNode treeNode = model.getRoot();
			MTreeNode root = (MTreeNode) treeNode.getData();
			MTreeNode node = new MTreeNode (gridTab.getRecord_ID(), 0, name, description,
					root.getNode_ID(), summary, imageIndicator, false, null);
			SimpleTreeNode newNode = new SimpleTreeNode(node, new ArrayList());
			model.addNode(newNode);
			int[] path = model.getPath(model.getRoot(), newNode);
			Treeitem ti = tree.renderItemByPath(path);
			tree.setSelectedItem(ti);
    	}
	}

	private void setSelectedNode(int recordId) {
		if (recordId <= 0) return;
		
		if (tree.getSelectedItem() != null) {
			SimpleTreeNode treeNode = (SimpleTreeNode) tree.getSelectedItem().getValue();
			MTreeNode data = (MTreeNode) treeNode.getData();
			if (data.getNode_ID() == recordId) return;
		}
		
		SimpleTreeModel model = (SimpleTreeModel) tree.getModel();
		SimpleTreeNode treeNode = model.find(null, recordId);
		if (treeNode != null) {
			int[] path = model.getPath(model.getRoot(), treeNode);
			Treeitem ti = tree.renderItemByPath(path);
			tree.setSelectedItem(ti);
		} else {
			addNewNode();
		}
	}

	public void valueChange(ValueChangeEvent e)
    {
        if (gridTab.isProcessed())       //  only active records
        {
            Object source = e.getSource();
            if (source instanceof WEditor)
            {
                if (!((WEditor)source).isReadWrite())
                {
                    logger.config("(" + gridTab.toString() + ") " + e.getPropertyName());
                    return;
                }
            }
            else
            {
                logger.config("(" + gridTab.toString() + ") " + e.getPropertyName());
                return;
            }
        }   //  processed
        logger.config("(" + gridTab.toString() + ") "
            + e.getPropertyName() + "=" + e.getNewValue() + " (" + e.getOldValue() + ") "
            + (e.getOldValue() == null ? "" : e.getOldValue().getClass().getName()));
        

        //  Get Row/Col Info
        GridTable mTable = gridTab.getTableModel();
        int row = gridTab.getCurrentRow();
        int col = mTable.findColumn(e.getPropertyName());
        //
        if (e.getNewValue() == null && e.getOldValue() != null 
            && e.getOldValue().toString().length() > 0)     //  some editors return "" instead of null
            mTable.setChanged (true);
        else
        {
        //  mTable.setValueAt (e.getNewValue(), row, col, true);
           	mTable.setValueAt (e.getNewValue(), row, col);  //  -> dataStatusChanged -> dynamicDisplay
            //  Force Callout
            if ( e.getPropertyName().equals("S_ResourceAssignment_ID") )
            {
                GridField mField = gridTab.getField(col);
                if (mField != null && mField.getCallout().length() > 0)
                {
                    gridTab.processFieldChange(mField);     //  Dependencies & Callout
                }
            }
        }

    } // ValueChange 

	public void switchRowPresentation() {
		if (formComponent.isVisible()) {
			formComponent.setVisible(false);
		} else {
			formComponent.setVisible(true);
		}
		listPanel.setVisible(!formComponent.isVisible());
		if (listPanel.isVisible()) {
			listPanel.activate(gridTab);
		}
	}
	
	class ZoomListener implements EventListener {

		private IZoomableEditor searchEditor;

		ZoomListener(IZoomableEditor editor) {
			searchEditor = editor;
		}
		
		public void onEvent(Event event) throws Exception {
			if (Events.ON_CLICK.equals(event.getName())) {
				searchEditor.actionZoom();
			}
			
		}
		
	}
}

