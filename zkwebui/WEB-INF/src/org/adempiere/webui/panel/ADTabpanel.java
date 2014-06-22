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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.webui.component.Column;
import org.adempiere.webui.component.Columns;
import org.adempiere.webui.component.EditorBox;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridPanel;
import org.adempiere.webui.component.Group;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.SimpleTreeModel;
import org.adempiere.webui.component.Tab;
import org.adempiere.webui.component.Tabbox;
import org.adempiere.webui.component.Tabpanel;
import org.adempiere.webui.component.Tabpanels;
import org.adempiere.webui.component.Tabs;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.editor.IZoomableEditor;
import org.adempiere.webui.editor.WButtonEditor;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WEditorPopupMenu;
import org.adempiere.webui.editor.WebEditorFactory;
import org.adempiere.webui.event.ContextMenuListener;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.theme.ThemeUtils;
import org.adempiere.webui.util.GridTabDataBinder;
import org.adempiere.webui.util.TreeUtils;
import org.adempiere.webui.window.FDialog;
import org.adempiere.webui.window.WAlertDialog;
import org.compiere.model.DataStatusEvent;
import org.compiere.model.DataStatusListener;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.GridTable;
import org.compiere.model.GridWindow;
import org.compiere.model.Lookup;
import org.compiere.model.MLookup;
import org.compiere.model.MMemo;
import org.compiere.model.MTree;
import org.compiere.model.MTreeNode;
import org.compiere.model.X_AD_FieldGroup;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Evaluatee;
import org.compiere.util.Util;
import org.zkoss.zk.au.out.AuFocus;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Center;
import org.zkoss.zul.DefaultTreeNode;
import org.zkoss.zul.Div;
import org.zkoss.zul.Groupfoot;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.Space;
import org.zkoss.zul.TreeModel;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.West;

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
public class ADTabpanel extends Div implements Evaluatee, EventListener<Event>,
DataStatusListener, IADTabpanel, VetoableChangeListener
{
	/**
	 * generated serial version ID
	 */
	private static final long serialVersionUID = 6945934489328360251L;

	private static final CLogger logger;

    static
    {
        logger = CLogger.getCLogger(ADTabpanel.class);
    }

    private GridTab           gridTab;

    @SuppressWarnings("unused")
	private GridWindow        gridWindow;

    private AbstractADWindowPanel      windowPanel;

    private int               windowNo;

    private Grid              grid;

    private ArrayList<WEditor> editors = new ArrayList<WEditor>();

    //private ArrayList<String> editorIds = new ArrayList<String>();

    private boolean			  uiCreated = false;

    private GridPanel		  listPanel;

    private Map<String, List<Row>> fieldGroupContents = new HashMap<String, List<Row>>();

    private Map<String, List<Group>> fieldGroupHeaders = new HashMap<String, List<Group>>();

	private ArrayList<Row> rowList;

	private Component formComponent = null;

	private ADTreePanel treePanel = null;
	
	private Tabbox tabBox	= null;
	
	private Tabs tabs	= null;
	
	private Tabpanels tabPanels	= null;

	private GridTabDataBinder dataBinder;

	private Map<Integer, Group> includedTab = new HashMap<Integer, Group>();
	private Map<Integer, Groupfoot> includedTabFooter = new HashMap<Integer, Groupfoot>();
	private Map<Integer, Tabpanel> embeddTabPanel = new HashMap<Integer, Tabpanel>();
	private List<EmbeddedPanel> includedPanel = new ArrayList<EmbeddedPanel>();

	private boolean active = false;

	private Group currentGroup;
	
	private Rows rows;

	private boolean m_vetoActive = false;
	
	
	private static final String ON_DEFER_SET_SELECTED_NODE = "onDeferSetSelectedNode";
	
	private int numCols = 0; // TODO - make this variable
	
	private List<Group> allCollapsibleGroups = new ArrayList<Group>();

	/**
	 * Used to selecte the first of multiple events that can occur
	 */
	private long m_lastCallTime = 0;

	public ADTabpanel()
	{
        init();
    }

    private void init()
    {
        initComponents();
        
        addEventListener(ON_DEFER_SET_SELECTED_NODE, this);
    }

    private void initComponents()
    {
    	ThemeUtils.addSclass("ad-adtabpanel adtab-content", this);

        grid = new Grid();
        //have problem moving the following out as css class
//        grid.setWidth("100%");
//        grid.setHeight("100%");
//        grid.setVflex(true);
//        grid.setStyle("margin:0; padding:0; position: absolute");
        grid.makeNoStrip();

        listPanel = new GridPanel();
        listPanel.getListbox().addEventListener(Events.ON_DOUBLE_CLICK, this);
    }

    /**
     *
     * @param winPanel
     * @param windowNo
     * @param gridTab
     * @param gridWindow
     */
    public void init(AbstractADWindowPanel winPanel, int windowNo, GridTab gridTab,
            GridWindow gridWindow)
    {
        this.windowNo = windowNo;
        this.gridWindow = gridWindow;
        this.gridTab = gridTab;
        this.windowPanel = winPanel;
        gridTab.addDataStatusListener(this);
        this.dataBinder = new GridTabDataBinder(gridTab);

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

			treePanel = new ADTreePanel();
			West west = new West();
			west.appendChild(treePanel);
			west.setWidth("300px");
			west.setCollapsible(true);
			west.setSplittable(true);
			west.setAutoscroll(true);
			layout.appendChild(west);

			Center center = new Center();
			center.setHflex("true");
			center.setVflex("true");
			center.appendChild(grid);
			layout.appendChild(center);

			formComponent = layout;
			treePanel.getTree().addEventListener(Events.ON_SELECT, this);
		}
		else
		{
			this.appendChild(grid);
			formComponent = grid;
		}
        this.appendChild(listPanel);
        listPanel.setVisible(false);
        listPanel.setWindowNo(windowNo);
        listPanel.setADWindowPanel(winPanel);

        gridTab.getTableModel().addVetoableChangeListener(this);
    }

    /**
     * Create UI components if not already created
     */
    public void createUI()
    {
    	if (uiCreated) {
            dynamicDisplay(-1);
    		return;
    	}

    	uiCreated = true;

    	//setup columns
    	Columns columns = new Columns();
    	grid.appendChild(columns);
    	Column col = new Column();
    	col.setWidth("14%");
    	columns.appendChild(col);
    	col = new Column();
    	col.setWidth("35%");
    	columns.appendChild(col);
    	col = new Column();
    	col.setWidth("14%");
    	columns.appendChild(col);
    	col = new Column();
    	col.setWidth("35%");
    	columns.appendChild(col);
    	col = new Column();
    	col.setWidth("2%");
    	columns.appendChild(col);
    	
    	numCols = columns.getChildren().size();

    	rows = grid.newRows();
        GridField fields[] = gridTab.getFields();
        Row row = new Row();

        String currentFieldGroup = null;
        for (int i = 0; i < fields.length; i++)
        {
            GridField field = fields[i];
            if (field.isDisplayed())
            {
            	//included tab
            	if (field.getIncluded_Tab_ID() > 0)
            	{
            		// Complete the current row
            		if (row.getChildren().size() == 2)
        			{
        				row.appendCellChild(createSpacer());
                        row.appendCellChild(createSpacer());
                        row.appendCellChild(createSpacer());

        			} else if (row.getChildren().size() > 0)
        			{
                        row.appendCellChild(createSpacer());
        			}

            		addRow(row);
            		// End current field group
            		if (currentGroup != null) {
            			Groupfoot groupfoot = new Groupfoot();
            			rows.appendChild(groupfoot);
            			currentGroup = null;
            			currentFieldGroup = null;
            		}

            		//row = new Row();
					//row.appendCellChild(new Separator(), 5);
					//rows.appendChild( row );

            		// Create a new group for the included tab
            		Group group = new Group();
            		group.setSpan(numCols);
            		rows.appendChild(group);
            		includedTab.put(field.getIncluded_Tab_ID(), group);
            		Groupfoot groupfoot = new Groupfoot();
            		rows.appendChild(groupfoot);
            		includedTabFooter.put(field.getIncluded_Tab_ID(), groupfoot);

            		for (EmbeddedPanel ep : includedPanel) {
            			if (ep.adTabId == field.getIncluded_Tab_ID()) {
            				ep.group = (Group) includedTab.get(ep.adTabId);
            				createEmbeddedPanelUI(ep);
            				break;
            			}
            		}

            		// Start a new row for the next field
            		row = new Row();
            		continue;
            	}

            	// Normal field
            	String fieldGroup = field.getFieldGroup();
            	if (fieldGroup != null && fieldGroup.trim().length() > 0)
            	{
            		if (!fieldGroup.equals(currentFieldGroup))
            		{
            			// We have a new group
            			// Complete the current row
            			if (row.getChildren().size() == 2)
            			{
            				row.appendCellChild(createSpacer());
            				row.appendCellChild(createSpacer());
            				row.appendCellChild(createSpacer());
            			} else if (row.getChildren().size() > 0)
            			{
            				row.appendCellChild(createSpacer());
            			}
            			addRow(row);
            			
            			// Start a new row
            			row = new Row();

            			// TODO - Group footer for the previous group?
            			
            			// Start a new group
            			currentFieldGroup = fieldGroup;

            			// Create a list for the group components
            			List<Group> headerRows = new ArrayList<Group>();
            			fieldGroupHeaders.put(fieldGroup, headerRows);

            			// Create a list for the group contents
        				rowList = new ArrayList<Row>();
        				fieldGroupContents.put(fieldGroup, rowList);

            			if (X_AD_FieldGroup.FIELDGROUPTYPE_Label.equals(field.getFieldGroupType()))
            			{
            				// Label only
            				Group group = new Group(fieldGroup);
                    		group.setSpan(numCols);
            				rows.appendChild(group);
            				headerRows.add(group);
            				// TODO remove collabsible functionality with a style
            			}
            			else
            			{
            				Group group = new Group(fieldGroup);
                    		group.setSpan(numCols);
                    		allCollapsibleGroups.add(group);
            				if (X_AD_FieldGroup.FIELDGROUPTYPE_Tab.equals(field.getFieldGroupType()) || field.getIsCollapsedByDefault())
            				{
            					group.setOpen(false);
            				}
            				currentGroup = group;
            				rows.appendChild(group);
            				headerRows.add(group);
            			}
            		}
            	}

                if (!field.isSameLine() || field.isLongField())
                {
                	// complete the current row and start a new row
                	if(row.getChildren().size() > 0)
                	{
	                    if (row.getChildren().size() == 2)
	                    {
	                        row.appendCellChild(createSpacer(),3);
	                    }
	                    else // Likely 4
	                    {
	                    	row.appendCellChild(createSpacer());
	                    }
	                    addRow(row);
	                    row = new Row();
                	}
                }
                else if (row.getChildren().size() == 4)
                {
                	//next line if reach max column ( 4 )
                	row.appendCellChild(createSpacer());
                    addRow(row);
                    row = new Row();
                }

                WEditor editor = WebEditorFactory.getEditor(gridTab, field, false);

                if (editor != null) // Not heading
                {
                    editor.setGridTab(this.getGridTab());
                	field.addPropertyChangeListener(editor);
                    editors.add(editor);
                    editor.getComponent().setAttribute("ComponentType", "Editor");
                    
                    // Add the label
                    if (field.isFieldOnly())
                    {
                    	// blank label
                    	row.appendChild(createSpacer());
                    }
                    else
                    {
                    	//  Add the label
                    	Div div = new Div();
                    	ThemeUtils.addSclass("ad-label", div);
                        div.setAttribute("ComponentType", "Label");
                        Label label = editor.getLabel();
	                    div.appendChild(label);
	                    if (label.getDecorator() != null)
	                    	div.appendChild(label.getDecorator());
	                    row.appendCellChild(div);
                    }
                    
                    // Long fields take up the rest of the row
                    if (field.isLongField()) {
                        row.appendCellChild(editor.getComponent(),3);
                    	row.appendCellChild(createSpacer());
	                    addRow(row);
                    	row = new Row();
                    }
                    else {
                    	// Just a single column
                        row.appendCellChild(editor.getComponent());                    	
                    }

                    if (editor instanceof WButtonEditor)
                    {
                    	if (windowPanel != null)
                    		((WButtonEditor)editor).addActionListener(windowPanel);
                    }
                    else
                    {
                    	editor.addValueChangeListener(dataBinder);
                    }

                    //streach component to fill grid cell
                    editor.fillHorizontal();

                    //setup editor context menu
                    WEditorPopupMenu popupMenu = editor.getPopupMenu();
                    if (popupMenu != null)
                    {
                    	popupMenu.addMenuListener((ContextMenuListener)editor);
                        this.appendChild(popupMenu);
                        if (!field.isFieldOnly())
                        {
                        	Label label = editor.getLabel();
	                        if (popupMenu.isZoomEnabled() && editor instanceof IZoomableEditor)
	                        {
	                        	label.setZoomable(true);
	                        	label.addEventListener(Events.ON_CLICK, new ZoomListener((IZoomableEditor) editor));
	                        }

	                        label.setContext(popupMenu.getId());
                        }
                    }
                }
                else if (field.isHeading())
                {
    				//display just a label if we are "heading only"
    				Label label = new Label(field.getHeader());
    				Div div = new Div();
                	ThemeUtils.addSclass("ad-heading", div);
                    div.setAttribute("ComponentType", "Heading");
    				row.appendChild(createSpacer());
    				div.appendChild(label);
    				row.appendChild(div);
    			}
            }
        }

        //last row
        if (row.getChildren().size() > 0)
        {
            if (row.getChildren().size() == 2)
            {
                row.appendChild(createSpacer());
                row.appendChild(createSpacer());
            }
            row.appendChild(createSpacer());
            addRow(row);
        }

        //create tree
        if (gridTab.isTreeTab() && treePanel != null) {
			int AD_Tree_ID = MTree.getDefaultAD_Tree_ID (
				Env.getAD_Client_ID(Env.getCtx()), gridTab.getKeyColumnName());
			treePanel.initTree(AD_Tree_ID, windowNo);
        }

        if (!gridTab.isSingleRow() && !isGridView())
        	switchRowPresentation();
        
        dynamicDisplay(-1);
    }

    private Row createPanelForEmbedded(Div divComponent, Row footer , EmbeddedPanel ep ) {
    	
    	//
    	//Setting Properties to Div Component
    	//
    	divComponent.setHeight("100%");
    	divComponent.setWidth("100%");
    	
    	//
    	// Create a Panel Object
    	//
    	
    	Panel panel = new Panel();
    	
    	//
    	//Setting Properties to Panel 
    	//
    	panel.setFramable(true);
//    	panel.setStyle("overflow:auto");
    	panel.setWidth("100%");
    	panel.setHeight("100%");
    	
    	//
    	//creating Object to PanelChildren class 
    	//
    	ep.panelChildren = new Panelchildren( );

    	//
    	// Creating a Object to Grid And Apply Properties
    	//
        Grid newGrid = new Grid();
        newGrid.setVflex(true);
        newGrid.setStyle("margin:0; padding:0; position: absolute; border: none;");
        newGrid.makeNoStrip();
    	newGrid.setWidth("100%");
    	newGrid.setHeight("100%");
    	
    	
    	// Grid append to Panel Children
    	ep.panelChildren.appendChild( newGrid );
    	
    	// Panel Children Append to Panel
        panel.appendChild(ep.panelChildren);

        // Creating one Tabbox And panel append to TabPanels
        Tabbox panels =  setTabPanels(ep, panel) ;
        
        // TabBox Append to divComponent
        panels.setParent( divComponent );
    	
        //ep.embeddedGrid = newGrid;
        
        //
        //Creating Rows based on the Grid
        //
        
		Rows newRows = newGrid.newRows();
		newRows.setWidth("100%");
		newRows.setHeight("100%");
		
		
		org.zkoss.zul.Row newRow = new Group();
		
		//
		// Create a Row For ToolBar
		//
		
		Row toolbarRow = new Row();
		toolbarRow.setSpans("5");
		ep.toolbarRow = toolbarRow;
		
		//
		//Create a Row For All Widgets  
		//
		Row panelRow = new Row();
		panelRow.setSpans("5");
		panelRow.setWidth("100%");
		panelRow.setHeight("100%");
		
		// Added to Group
		newRows.appendChild( newRow );
		
		// Added to tool-bar Row
		newRows.appendChild(toolbarRow);
		
		// Now added to Panel Row
		newRows.appendChild( panelRow );
		
		//Added to footer
		newRows.appendChild( footer );
		
		
    	return panelRow ;
    }

    private Component createSpacer() {
		return new Space();
	}

	/**
	 * Validate display properties of fields of current row
	 * @param col
	 */
    public void dynamicDisplay (int col)
    {
        // Fails during tabSelectionChanged events.  Need to set visibility before the tab is open.
    	//if (!gridTab.isOpen())
        //{
        //   return;
        //}
    	
    	//  This funciton can be called by several events which can all occur at
    	//  roughly the same time.  Only need to proceed with the first of these
    	//  and execute the function once.
    	long callTime = System.currentTimeMillis();
    	if (col < 0 && callTime < m_lastCallTime + 500)
    		return;    	
    	m_lastCallTime = callTime;
    	
     	List<Group> collapsedGroups = new ArrayList<Group>();
    	for (Group group : allCollapsibleGroups) {
    		if (! group.isOpen())
    			collapsedGroups.add(group);
    	}

        for (WEditor comp : editors)
        {
        	comp.setMandatoryLabels();
        }
        
        //  Selective
        if (col > 0)
        {
            GridField changedField = gridTab.getField(col);
            String columnName = changedField.getColumnName();
            ArrayList<?> dependants = gridTab.getDependantFields(columnName);
            logger.config("(" + gridTab.toString() + ") "
                + columnName + " - Dependents=" + dependants.size());
            if (dependants.size() == 0 && changedField.getCallout().length() > 0)
            {
                return;
            }
        }

        boolean noData = gridTab.getRowCount() == 0;
        logger.config(gridTab.toString() + " - Rows=" + gridTab.getRowCount());
        for (WEditor comp : editors)
        {
            GridField mField = comp.getGridField();
            if (mField != null && mField.getIncluded_Tab_ID() <= 0)
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
                    	comp.dynamicDisplay();
                        boolean rw = mField.isEditable(true);   //  r/w - check Context
                        comp.setReadWrite(rw);
                        comp.setMandatory(mField.isMandatory(true));    //  check context
                    }
                }
                else if (comp.isVisible())
                {
                    comp.setVisible(false);
                }
            }
        }   //  all components

        //hide row if all editor within the row is invisible or the group is closed
        List<Component> rows = grid.getRows().getChildren();
        for(Component comp: rows)
        {
        	// Ignore the groups
        	if (comp instanceof Group) {
        		continue;
        	}
        	
        	Row row = (Row) comp;
        	
        	boolean visible = false;
        	boolean editorRow = false;
        	for (Component component: row.getChildren())
        	{
        		if (component instanceof Cell)
        			component = ((Cell) component).getFirstChild(); // Should only be a single child per cell.
        		
        		String type = (String) component.getAttribute("ComponentType");
        		if (type != null && type.equals("Editor")) 
        		{
        			editorRow = true;
           			// TODO open the group if there is a mandatory unfilled field
        			if (component.isVisible())
        			{
        				visible = true;
        				break;
        			}
        		}
        	}
        	
        	if (editorRow && (row.isVisible() != visible)) {
        		row.setAttribute(Group.GROUP_ROW_VISIBLE_KEY, visible ? "true" : "false");
        		row.setVisible(visible);
        	}
        }

        //hide fieldgroup if all editor row within the fieldgroup is invisible
        for(Iterator<Entry<String, List<Group>>> i = fieldGroupHeaders.entrySet().iterator(); i.hasNext();)
        {
        	Map.Entry<String, List<Group>> entry = i.next();
        	List<Row> contents = fieldGroupContents.get(entry.getKey());
        	boolean visible = false;
        	for (org.zkoss.zul.Row row : contents)
        	{
        		if (row.isVisible())
        		{
        			visible = true;
        			break;
        		}
        	}
        	List<Group> headers = entry.getValue();
        	for(Group group : headers)
        	{
        		if (group.isVisible() != visible)
        			group.setVisible(visible);
        	}
        }

        // collapse the groups closed
        for (Group group : collapsedGroups) {
        	group.setOpen(false);
        }
        
        logger.config(gridTab.toString() + " - fini - " + (col<=0 ? "complete" : "seletive"));
    }   //  dynamicDisplay

    /**
     * @return String
     */
    public String getDisplayLogic()
    {
        return gridTab.getDisplayLogic();
    }

    /**
     * @return String
     */
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

    /**
     * Is panel need refresh
     * @return boolean
     */
    public boolean isCurrent()
    {
        return gridTab != null ? gridTab.isCurrent() : false;
    }

    /**
     *
     * @return windowNo
     */
    public int getWindowNo()
    {
        return windowNo;
    }

    /**
     * Retrieve from db
     */
    public void query()
    {
    	boolean open = gridTab.isOpen();
        gridTab.query(false);
        if (listPanel.isVisible() && !open)
        	gridTab.getTableModel().fireTableDataChanged();
    }

    /**
     * Retrieve from db
     * @param onlyCurrentRows
     * @param onlyCurrentDays
     * @param maxRows
     */
    public void query (boolean onlyCurrentRows, int onlyCurrentDays, int maxRows)
    {
    	boolean open = gridTab.isOpen();
        gridTab.query(onlyCurrentRows, onlyCurrentDays, maxRows);
        if (listPanel.isVisible() && !open)
        	gridTab.getTableModel().fireTableDataChanged();
    }

    /**
     * @return GridTab
     */
    public GridTab getGridTab()
    {
        return gridTab;
    }

    /**
     * Refresh current row
     */
    public void refresh()
    {
        gridTab.dataRefresh();
    }

    /**
     * Activate/deactivate panel
     * @param activate
     */
    public void activate(boolean activate)
    {
    	active = activate;
        if (listPanel.isVisible()) {
        	if (activate)
        		listPanel.activate(gridTab);
        	else
        		listPanel.deactivate();
        } else {
        	if (activate) {
        		formComponent.setVisible(activate);
        		setFocusToField();
        	}
        }

        //activate embedded panel
        for(EmbeddedPanel ep : includedPanel)
        {
        	activateChild(activate, ep);
        }
    }

	private void activateChild(boolean activate, EmbeddedPanel panel) {
		if (activate)
		{
			panel.windowPanel.getADTab().evaluate(null);
			panel.windowPanel.getADTab().setSelectedIndex(0);
			panel.tabPanel.query(false, 0, 0);
		}
		panel.tabPanel.activate(activate);
		if (activate)
		{
			activateTabPanel(panel);
		}

	}
	
	private void activateTabPanel(EmbeddedPanel panel) {
		
		if( tabPanels != null )
			
			panel.group.setVisible(true);
//			panel.divComponent.setStyle("position: relative; overflow:auto; ");


			tabPanels.setVisible(true);
			tabPanels.setStyle("margin:0; padding:0; border: none; position: relative; ");

//			embeddTabPanel.get(panel.adTabId).setVisible(true);
//			embeddTabPanel.get(panel.adTabId).setStyle(" margin:0; padding:0; border: none; height: 400px; ");
			
//			panel.panelChildren.setVisible(true);
//			panel.panelChildren.setStyle(" margin:0; padding:0; border: none; height: 400px; ");
			
//			panel.embeddedGrid.setVisible(true);
//			panel.embeddedGrid.setStyle("border: none; height: 400px;  ");
			
	}

	/**
	 * set focus to first active editor
	 */
	private void setFocusToField() {
		WEditor toFocus = null;
		for (WEditor editor : editors) {
			if (editor.isHasFocus() && editor.isVisible() && editor.getComponent().getParent() != null) {
				toFocus = editor;
				break;
			}

			if (toFocus == null) {
				if (editor.isVisible() && editor.isReadWrite() && editor.getComponent().getParent() != null) {
					toFocus = editor;
				}
			}
		}
		if (toFocus != null) {
			Component c = toFocus.getComponent();
			if (c instanceof EditorBox) {
				c = ((EditorBox)c).getTextbox();
			}
			Clients.response(new AuFocus(c));
		}
	}

    /**
     * @param event
     * @see EventListener#onEvent(Event)
     */
    public void onEvent(Event event)
    {
    	if (event.getTarget() == listPanel.getListbox())
    	{
    		this.switchRowPresentation();
    	}
    	else if (treePanel != null && event.getTarget() == treePanel.getTree()) 
    	{
    		Treeitem item =  treePanel.getTree().getSelectedItem();
    		navigateTo((DefaultTreeNode<MTreeNode>)item.getValue());
    	} 
    	else if (ON_DEFER_SET_SELECTED_NODE.equals(event.getName())) 
    	{
    		if (gridTab.getRecord_ID() > 0 && gridTab.isTreeTab() && treePanel != null) {
            	setSelectedNode(gridTab.getRecord_ID());
            }
    	}
    }

    private void navigateTo(DefaultTreeNode<MTreeNode> value) {
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

    /**
     * @param e
     * @see DataStatusListener#dataStatusChanged(DataStatusEvent)
     */
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
    		ArrayList<GridField> list = gridTab.getDependantFields(mField.getColumnName());
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
        if (!uiCreated) {
        	createUI();  // Also calls dynamicDisplay()
        } 
        else {
        	// Only set the visible rows as required.
            dynamicDisplay(col);
        }
        
        if ( mField != null && mField.isLookup() )
		{
			Lookup lookup = (Lookup) mField.getLookup();
			if (lookup != null  && lookup instanceof MLookup )
			{
				MLookup mlookup = (MLookup) lookup;
				Object value = mField.getValue();
				if ( mlookup.isAlert() && value != null && value instanceof Integer )
				{
					String alert = MMemo.getAlerts(Env.getCtx(), mlookup.getTableName(), (Integer) value);
					if ( !Util.isEmpty(alert) )
					{
						WAlertDialog dialog = new WAlertDialog(alert);
						dialog.setAttribute(Window.MODE_KEY, Window.MODE_MODAL);
						SessionManager.getAppDesktop().showWindow(dialog);
						
					}
				}
			}
		}

        //sync tree
        if (treePanel != null) {
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

        if (listPanel.isVisible()) {
        	listPanel.updateListIndex();
        	listPanel.dynamicDisplay(col);
        }

        if (!includedPanel.isEmpty() && e.getChangedColumn() == -1) {
        	for (EmbeddedPanel panel : includedPanel)
        		panel.tabPanel.query(false, 0, 0);
        }

    }

    private void deleteNode(int recordId) {
		if (recordId <= 0) return;

		SimpleTreeModel model = (SimpleTreeModel)(TreeModel<?>) treePanel.getTree().getModel();

		if (treePanel.getTree().getSelectedItem() != null) {
			DefaultTreeNode treeNode = (DefaultTreeNode) treePanel.getTree().getSelectedItem().getValue();
			MTreeNode data = (MTreeNode) treeNode.getData();
			if (data.getNode_ID() == recordId) {
				model.removeNode(treeNode);
				return;
			}
		}

		DefaultTreeNode treeNode = model.find(null, recordId);
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
			SimpleTreeModel model = (SimpleTreeModel)(TreeModel<?>) treePanel.getTree().getModel();
			DefaultTreeNode treeNode = model.getRoot();
			MTreeNode root = (MTreeNode) treeNode.getData();
			MTreeNode node = new MTreeNode (gridTab.getRecord_ID(), 0, name, description,
					root.getNode_ID(), summary, imageIndicator, false, null);
			DefaultTreeNode newNode = new DefaultTreeNode(node, new ArrayList<Object>());
			model.addNode(newNode);
			int[] path = model.getPath(newNode);
			Treeitem ti = treePanel.getTree().renderItemByPath(path);
			treePanel.getTree().setSelectedItem(ti);
    	}
	}

	private void setSelectedNode(int recordId) {
		if (recordId <= 0) return;
		
		if (TreeUtils.isOnInitRenderPosted(treePanel.getTree()) || treePanel.getTree().getTreechildren() == null) {
			treePanel.getTree().onInitRender();
		}

		
		SimpleTreeModel model = (SimpleTreeModel)(TreeModel<?>) treePanel.getTree().getModel();
		
		
		if (treePanel.getTree().getSelectedItem() != null) {
			DefaultTreeNode<Object> treeNode = treePanel.getTree().getSelectedItem().getValue();
			MTreeNode data = (MTreeNode) treeNode.getData();
			if (data.getNode_ID() == recordId){
				int[] path = model.getPath(treeNode);
				Treeitem ti = treePanel.getTree().renderItemByPath(path);
				if (ti.getPage() == null) {
					Events.echoEvent(ON_DEFER_SET_SELECTED_NODE, this, null);
				}
				 return;
			}
		}

		DefaultTreeNode<Object> treeNode = model.find(null, recordId);
		if (treeNode != null) {
			int[] path = model.getPath(treeNode);
			Treeitem ti = treePanel.getTree().renderItemByPath(path);
			treePanel.getTree().setSelectedItem(ti);
		} else {
			addNewNode();
		}
	}

	/**
	 * Toggle between form and grid view
	 */
	public void switchRowPresentation() {
		if (formComponent.isVisible()) {
			formComponent.setVisible(false);
			//de-activate embedded panel
	        for(EmbeddedPanel ep : includedPanel)
	        {
	        	activateChild(false, ep);
	        }
		} else {
			formComponent.setVisible(true);
			//activate embedded panel
	        for(EmbeddedPanel ep : includedPanel)
	        {
	        	activateChild(true, ep);
	        }
		}
		listPanel.setVisible(!formComponent.isVisible());
		if (listPanel.isVisible()) {
			listPanel.refresh(gridTab);
			listPanel.scrollToCurrentRow();
		} else {
			listPanel.deactivate();
		}
	}

	class ZoomListener implements EventListener<Event> {

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

	/**
	 * Embed detail tab
	 * @param ctx
	 * @param windowNo
	 * @param gridWindow
	 * @param adTabId
	 * @param tabIndex
	 * @param tabPanel
	 */
	public void embed(Properties ctx, int windowNo, GridWindow gridWindow,
			int adTabId, int tabIndex, IADTabpanel tabPanel) {
		EmbeddedPanel ep = new EmbeddedPanel();
		ep.tabPanel = tabPanel;
		ep.adTabId = adTabId;
		ep.tabIndex = tabIndex;
		ep.gridWindow = gridWindow;
		includedPanel.add(ep);
		Group group = (Group) includedTab.get(adTabId);
		ep.group = group;
		if (tabPanel instanceof ADTabpanel) {
			ADTabpanel atp = (ADTabpanel) tabPanel;
			atp.listPanel.setPageSize(-1);
		}
		ADWindowPanel panel = new ADWindowPanel(ctx, windowNo, gridWindow, tabIndex, tabPanel);
		ep.windowPanel = panel;

		if (group != null) {
			createEmbeddedPanelUI(ep);
			if (active)
				activateChild(true, ep);
		}
	}
	
	private Tabbox setTabPanels(EmbeddedPanel ep, Panel panel  ) {
		//
		// For One Tab We created only one TabBox
		//
		if (null == tabBox && null == tabPanels) {
			
			tabBox = new Tabbox();
			tabBox.setHeight("100%");
			tabBox.setStyle("height: 100%; width: 100%; position: relative;");

			tabPanels = new Tabpanels();
			tabPanels.setHeight("400px");
			
			
			tabBox.appendChild(tabPanels);

			tabs = new Tabs();
			tabBox.appendChild(tabs);

		}
		// Setting Name For Tab
		
		Tab tab = new Tab(ep.gridWindow.getTab(ep.tabIndex).getName());
		
		// Appending Tab to Tabs
		tabs.appendChild(tab);
		
		//
		//Creating a TabPanel For every Tab
		//
		Tabpanel tabPanel = new Tabpanel();
		embeddTabPanel.put(ep.adTabId, tabPanel);


//		tabPanel.setWidth("100%");
		tabPanel.setStyle("margin:0; padding:0; border: none; height:400px;");
		
		//
		//Setting Embedded Panel to tabPanel
		//
		tabPanel.appendChild(panel);
		
		//
		//Appending Tab Panel To TabPanels
		//
		tabPanels.appendChild(tabPanel);
		
		//
		//By Default first Tab should populated
		//
		tabBox.setSelectedIndex(0);
		
		//
		// Returning the tabbox
		//
		return tabBox;

	}

	class EmbeddedPanel {
		Group group;
		GridWindow gridWindow;
		int tabIndex;
		ADWindowPanel windowPanel;
		IADTabpanel tabPanel;
		int adTabId;
		Panelchildren panelChildren;
		Row toolbarRow;
	}

	/**
	 * @see IADTabpanel#afterSave(boolean)
	 */
	public void afterSave(boolean onSaveEvent) {
		if (!includedPanel.isEmpty()) {
        	for (EmbeddedPanel panel : includedPanel)
        		panel.tabPanel.query(false, 0, 0);
        }
		
		//  Sync tree
		if (treePanel == null || gridTab.getRecord_ID() <= 0)
			return;
		
		rowChanged(true, gridTab.getRecord_ID());
		
	}

	/**
	 *  Row Changed - synchronize with Tree
	 *
	 *  @param  save    true the row was saved (changed/added), false if the row was deleted
	 *  @param  keyID   the ID of the row changed
	 */
	public void rowChanged (boolean save, int keyID)
	{
		String name = (String)gridTab.getValue("Name");
		String description = (String)gridTab.getValue("Description");
		Boolean IsSummary = (Boolean)gridTab.getValue("IsSummary");
		boolean summary = IsSummary != null && IsSummary.booleanValue();
		String imageIndicator = (String)gridTab.getValue("Action");  //  Menu - Action
		//
		treePanel.nodeChanged(save, keyID, name, description,
			summary, imageIndicator);
	}   //  rowChanged
	
	private void createEmbeddedPanelUI(EmbeddedPanel ep) {
		org.zkoss.zul.Row row = new Row();
		row.setSpans("5");
		grid.getRows().insertBefore(row, includedTabFooter.get(ep.adTabId));
		ep.windowPanel.createPart(row);
		ep.windowPanel.getComponent().setWidth("100%");
		ep.windowPanel.getComponent().setStyle("position: relative");
		ep.windowPanel.getComponent().setHeight("400px");

		Label title = new Label(ep.gridWindow.getTab(ep.tabIndex).getName());
		ep.group.appendChild(title);
		ep.group.appendChild(ep.windowPanel.getToolbar());
		ep.windowPanel.getStatusBar().setZclass("z-group-foot");
		ep.windowPanel.initPanel(-1, null);
	}
    
	@Override
	public void focus() {
		if (formComponent.isVisible())
			this.setFocusToField();
		else
			listPanel.focus();
	}

	public void setFocusToField(String columnName) {
		if (formComponent.isVisible()) {
			boolean found = false;
			for (WEditor editor : editors) {
				if (found)
					editor.setHasFocus(false);
				else if (columnName.equals(editor.getColumnName())) {
					editor.setHasFocus(true);
					Clients.response(new AuFocus(editor.getComponent()));
					found = true;
				}
			}
		} else {
			listPanel.setFocusToField(columnName);
		}
	}

	/**
	 * @see IADTabpanel#onEnterKey()
	 */
	public boolean onEnterKey() {
		if (listPanel.isVisible()) {
			return listPanel.onEnterKey();
		}
		return false;
	}

	/**
	 * @param e
	 * @see VetoableChangeListener#vetoableChange(PropertyChangeEvent)
	 */
	public void vetoableChange(PropertyChangeEvent e)
			throws PropertyVetoException {
		//  Save Confirmation dialog    MTable-RowSave
		if (e.getPropertyName().equals(GridTable.PROPERTY))
		{
			//  throw new PropertyVetoException will call this listener again to revert to old value
			if (m_vetoActive)
			{
				//ignore
				m_vetoActive = false;
				return;
			}
			if (!Env.isAutoCommit(Env.getCtx(), getWindowNo()) || gridTab.getCommitWarning().length() > 0)
			{
				if (!FDialog.ask(getWindowNo(), this, "SaveChanges?", gridTab.getCommitWarning()))
				{
					m_vetoActive = true;
					throw new PropertyVetoException ("UserDeniedSave", e);
				}
			}
			return;
		}   //  saveConfirmation
	}

	/**
	 * @return boolean
	 */
	public boolean isGridView() {
		return listPanel.isVisible();
	}

	/**
	 * @param gTab
	 * @return embedded panel or null if not found
	 */
	public IADTabpanel findEmbeddedPanel(GridTab gTab) {
		IADTabpanel panel = null;
		for(EmbeddedPanel ep : includedPanel) {
			if (ep.tabPanel.getGridTab().equals(gTab)) {
				return ep.tabPanel;
			}
		}
		return panel;
	}

	/**
	 * 
	 * @return GridPanel
	 */
	public GridPanel getGridView() {
		return listPanel;
	}
	
	/**
	 * Add a row to the rows and group.
	 * 
	 * @param row
	 */
	private void addRow(Row row) {
        rows.appendChild(row);
        if (rowList != null) {
			rowList.add(row);
        }
        if (currentGroup != null) {
        	currentGroup.add(row);
        }

	}
}

