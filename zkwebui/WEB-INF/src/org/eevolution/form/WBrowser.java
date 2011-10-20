/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2003-2011 e-Evolution Consultants. All Rights Reserved.      *
 * Copyright (C) 2003-2011 Victor Pérez Juárez 								  * 
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
 * Contributor(s): Victor Pérez Juárez  (victor.perez@e-evolution.com)		  *
 * Sponsors: e-Evolution Consultants (http://www.e-evolution.com/)            *
 *****************************************************************************/
package org.eevolution.form;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.model.MBrowse;
import org.adempiere.model.MBrowseField;
import org.adempiere.webui.apps.ProcessParameterPanel;
import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Tab;
import org.adempiere.webui.component.Tabbox;
import org.adempiere.webui.component.Tabpanel;
import org.adempiere.webui.component.Tabpanels;
import org.adempiere.webui.component.Tabs;
import org.adempiere.webui.component.ToolBar;
import org.adempiere.webui.component.WAppsAction;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.editor.WDateEditor;
import org.adempiere.webui.editor.WNumberEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.editor.WStringEditor;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.event.WTableModelListener;
import org.adempiere.webui.panel.CustomForm;
import org.adempiere.webui.panel.IFormController;
import org.adempiere.webui.panel.StatusBarPanel;
import org.compiere.apps.AEnv;
import org.compiere.apps.ProcessCtl;
import org.compiere.apps.search.Info_Column;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MLookup;
import org.compiere.model.MPInstance;
import org.compiere.model.MProcess;
import org.compiere.model.MRole;
import org.compiere.model.M_Element;
import org.compiere.process.ProcessInfo;
import org.compiere.util.ASyncProcess;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Vbox;
/**
 * Implementation Smart Browser for ZK
 * @author victor.perez@e-evoluton.com, www.e-evolution.com 
 * 	<li>FR [ 3426137 ] Smart Browser
 *  https://sourceforge.net/tracker/?func=detail&aid=3426137&group_id=176962&atid=879335
 *
 */
public class WBrowser extends Browser implements IFormController,
		EventListener, WTableModelListener, ValueChangeListener, ASyncProcess {

	private CustomForm m_frame = new CustomForm();
	private ProcessParameterPanel parameterPanel;
	protected StatusBarPanel statusBar = new StatusBarPanel();

	private Button bCancel;
	private Button bDelete;
	private Button bExport;
	private Button bFind;
	private Button bOk;
	private Button bPrint;
	private Button bSearch;
	private Button bZoom;
	private Button bSelectAll;

	private WListbox detail;
	private Borderlayout graphPanel;
	private Borderlayout processPanel;
	private Grid searchPanel = GridFactory.newGridLayout();
	private Borderlayout searchTab;
	private Borderlayout footPanel;
	private Tabbox tabsPanel;
	private ToolBar toolsBar;
	private Hbox topPanel;

	public WBrowser(boolean modal, int WindowNo, String value, MBrowse browse,
			String keyColumn, boolean multiSelection, String whereClause) {
		
		super(modal, WindowNo, value, browse, keyColumn, multiSelection,
				whereClause);
		
		m_frame = new CustomForm();

		// m_frame.setTitle(m_Browse.getName());

		initComponents();
		statInit();
		p_loadedOK = initBrowser();
		detail.setMultiSelection(true);
		int no = detail.getRowCount();
		setStatusLine(
				Integer.toString(no) + " "
						+ Msg.getMsg(Env.getCtx(), "SearchRows_EnterQuery"),
				false);
		setStatusDB(Integer.toString(no));
	}

	private void statInit() {

		Rows rows = new Rows();
		rows.setParent(searchPanel);

		int cols = 0;
		Row row = rows.newRow();

		for (MBrowseField field : m_Browse.getCriteriaFields()) {
			M_Element element = new M_Element(m_Browse.getCtx(),
					field.getAD_Element_ID(), null);
			String title = Msg.translate(Env.getCtx(), element.getColumnName());
			String name = field.getAD_View_Column().getAD_Column()
					.getColumnName();
			addComponent(field, row, field.getName(), title);

			cols++;

			if (field.isRange()) {
				title = Msg.getMsg(Env.getCtx(), "To");
				addComponent(field, row, field.getName() + "_To", title);
				cols++;
			}

			if (cols >= 2) {
				cols = 0;
				row = rows.newRow();
			}
		}
	}

	public void addComponent(MBrowseField field, Row row, String name,
			String title) {
		Label label = new Label();
		label.setText(title);
		row.appendChild(label.rightAlign());

		if (DisplayType.YesNo == field.getAD_Reference_ID()) {
			Checkbox data = new Checkbox();
			data.setName(name);
			row.appendChild(data);
		} else if (DisplayType.String == field.getAD_Reference_ID()) {
			WStringEditor data = new WStringEditor(name, false, false, true,
					30, 30, "", null);
			data.getComponent().setName(name);
			row.appendChild(data.getComponent());
		} else if (DisplayType.Number == field.getAD_Reference_ID()
				|| DisplayType.Quantity == field.getAD_Reference_ID()
				|| DisplayType.CostPrice == field.getAD_Reference_ID()
				|| DisplayType.Integer == field.getAD_Reference_ID()
				|| DisplayType.Amount == field.getAD_Reference_ID()) {
			WNumberEditor data = new WNumberEditor(name, false, false, true,
					field.getAD_Reference_ID(), title);
			data.getComponent();
			row.appendChild(data.getComponent());
		} else if (DisplayType.Date == field.getAD_Reference_ID()
				|| DisplayType.DateTime == field.getAD_Reference_ID()) {
			WDateEditor data = new WDateEditor();
			row.appendChild(data.getComponent());
		} else if (DisplayType.TableDir == field.getAD_Reference_ID()
				|| DisplayType.Table == field.getAD_Reference_ID()
				|| DisplayType.ID == field.getAD_Reference_ID()
				|| DisplayType.List == field.getAD_Reference_ID()
				|| DisplayType.Search == field.getAD_Reference_ID()) {
			WSearchEditor data = getLookup(field);
			row.appendChild(data.getComponent());
		}

	}

	private WSearchEditor getLookup(MBrowseField field) {
		try {
			MLookup dataL = getMLookup(field);
			WSearchEditor data = new WSearchEditor(field.getAD_View_Column()
					.getAD_Column().getColumnName(), field.isMandatory(),
					false, true, dataL);
			data.addValueChangeListener(this);
			return data;
		} catch (Exception e) {
			log.log(Level.SEVERE, "Browser.init", e);
		}
		return null;
	}

	private boolean initBrowser() {
		if (!initBrowserTable())
			return false;

		// prepare table
		StringBuffer where = new StringBuffer(m_View.getParentEntityAliasName()
				+ ".IsActive='Y'");

		if (p_whereClause.length() > 0) {
			where.append(p_whereClause);
		}

		prepareTable(m_generalLayout, m_View.getFromClause(), where.toString(),
				"2");

		if (m_Browse.getAD_Process_ID() > 0) {
			m_process = MProcess.get(Env.getCtx(), m_Browse.getAD_Process_ID());
			m_pi = new ProcessInfo(m_process.getName(),
					m_Browse.getAD_Process_ID());
			m_pi.setAD_User_ID(Env.getAD_User_ID(Env.getCtx()));
			m_pi.setAD_Client_ID(Env.getAD_Client_ID(Env.getCtx()));
			parameterPanel = new ProcessParameterPanel(p_WindowNo, m_pi);
			parameterPanel.setMode(ProcessParameterPanel.BROWSER_MODE);
			parameterPanel.init();

			South south = new South();
			south.appendChild(parameterPanel);
			south.setCollapsible(true);
			south.setTitle(" ");
			
			Div div = new Div();
			div.setWidth("100%");
			div.appendChild(parameterPanel);
			south.appendChild(div);
			
			footPanel.appendChild(south);
			
			
		}
		return true;
	}

	private boolean initBrowserTable() {
		String title = Msg.translate(Env.getCtx(), m_Browse.getName());
		// m_frame.setTitle(m_frame.getTitle() + " " + title);
		ArrayList<Info_Column> list = initBrowserData();
		if (list.size() == 0) {

			log.log(Level.SEVERE, "No Brwose for view=" + m_View.getName());
			return false;
		}
		log.finest("Browse Fields #" + list.size());

		// Convert ArrayList to Array
		m_generalLayout = new Info_Column[list.size()];
		list.toArray(m_generalLayout);
		return true;
	}

	private void setParameters() {
		/** Parameters **/
		m_parameters = new ArrayList();
		m_values = new ArrayList();
		Rows rows = (Rows) searchPanel.getRows();

		List rowList = rows.getChildren();

		for (int index = 0; index <= rowList.size() - 1; index++) {
			Row row = (Row) rowList.get(index);
			List components = row.getChildren();
			for (int ind = 0; ind <= components.size() - 1; ind++) {
				if (components.get(ind) instanceof WSearchEditor) {
					WSearchEditor component = (WSearchEditor) components
							.get(ind);
					addParameter(component.getColumnName(),
							component.getValue());
					continue;
				}
				if (components.get(ind) instanceof WStringEditor) {
					WStringEditor component = (WStringEditor) components
							.get(ind);
					addParameter(component.getComponent().getName(),
							component.getValue());
					continue;
				}
				if (components.get(ind) instanceof Checkbox) {
					Checkbox component = (Checkbox) components.get(ind);
					addParameter(component.getName(),
							new Boolean(component.isChecked()));
					continue;
				}
				if (components.get(ind) instanceof WDateEditor) {
					WDateEditor component = (WDateEditor) components.get(ind);
					addParameter(component.getComponent().getName(),
							component.getValue());
					continue;
				}
			}
		}
	}

	public void setStatusLine(String text, boolean error) {
		statusBar.setStatusLine(text, error);
	}

	public void setStatusDB(String text) {
		statusBar.setStatusDB(text);
	}

	protected void executeQuery() {
		setParameters();

		if (!testCount())
			return;

		setStatusLine(Msg.getMsg(Env.getCtx(), "StartSearch"), false);
		work();
	}

	private void cmd_zoom() {

		Integer record_ID = getSelectedRowKey();

		if (record_ID == null)
			return;
		AEnv.zoom(m_View.getParentViewDefinition().getAD_Table_ID(), record_ID);
	}

	protected void prepareTable(Info_Column[] layout, String from,
			String staticWhere, String orderBy) {
		p_layout = layout;
		m_sqlMain = detail.prepareTable(layout, from, staticWhere, false, from);
		StringBuffer sql = new StringBuffer("SELECT ");
		for (int i = 0; i < layout.length; i++) {
			if (i > 0)
				sql.append(", ");
			sql.append(layout[i].getColSQL());

			if (layout[i].isIDcol())
				sql.append(",").append(layout[i].getIDcolSQL());
		}
		sql.append(" FROM ").append(from);
		//
		sql.append(" WHERE ").append(staticWhere);
		m_sqlMain = sql.toString();

		m_sqlCount = "SELECT COUNT(*) FROM " + from + " WHERE " + staticWhere;
		m_sqlOrder = "";
		if (orderBy != null && orderBy.length() > 0)
			m_sqlOrder = " ORDER BY " + orderBy;

	}

	private boolean testCount() {
		int no = -1;
		no = getCount();

		MRole role = MRole.getDefault();
		if (role.isQueryMax(no))
			return true; // use dialog //ADialog.ask(p_WindowNo, m_frame,
							// "InfoHighRecordCount", String.valueOf(no));
		return true;
	}

	protected void saveSelection() {
		// Already disposed
		if (detail == null)
			return;

		log.config("OK=" + m_ok);
		if (!m_ok) // did not press OK
		{
			m_results.clear();
			detail.clearTable();
			detail = null;
			return;
		}

		// Multi Selection
		if (p_multiSelection) {
			m_results.addAll(getSelectedRowKeys());
		} else // singleSelection
		{
			Integer data = getSelectedRowKey();
			if (data != null)
				m_results.add(data);
		}
		log.config(getSelectedSQL());

		// Save Settings of detail info screens
		// saveSelectionDetail();
		// Clean-up
		detail.clearTable();
		detail = null;
	}

	public ArrayList<Integer> getSelectedRowKeys() {
		ArrayList<Integer> selectedDataList = new ArrayList<Integer>();

		if (m_keyColumnIndex == -1) {
			return selectedDataList;
		}

		if (p_multiSelection) {
			int rows = detail.getRowCount();
			for (int row = 0; row < rows; row++) {
				Object data = detail.getModel().getValueAt(row,
						m_keyColumnIndex);
				if (data instanceof IDColumn) {
					IDColumn dataColumn = (IDColumn) data;
					if (dataColumn.isSelected()) {
						selectedDataList.add(dataColumn.getRecord_ID());
					}
				}
			}
		}

		if (selectedDataList.size() == 0) {
			int row = detail.getSelectedRow();
			if (row != -1 && m_keyColumnIndex != -1) {
				Object data = detail.getModel().getValueAt(row,
						m_keyColumnIndex);
				if (data instanceof IDColumn)
					selectedDataList.add(((IDColumn) data).getRecord_ID());
				if (data instanceof Integer)
					selectedDataList.add((Integer) data);
			}
		}

		return selectedDataList;
	}

	public void dispose(boolean ok) {
		saveSelection();
		m_frame.dispose();
		if (m_Browse.getAD_Process_ID() <= 0)
			return;

		MPInstance instance = new MPInstance(Env.getCtx(),
				m_Browse.getAD_Process_ID(), 0);
		instance.saveEx();

		DB.createT_Selection(instance.getAD_PInstance_ID(), getSelectedKeys(),
				null);
		// call process
		m_pi.setAD_PInstance_ID(instance.getAD_PInstance_ID());
		parameterPanel.saveParameters();
		// Execute Process
		ProcessCtl worker = new ProcessCtl(this, 0, m_pi, null);
		worker.start();
	}

	private void setupToolBar() {

		try{
			toolsBar = new ToolBar();
			WAppsAction selectAllAction = new WAppsAction (ConfirmPanel.A_OK, null, ConfirmPanel.A_OK);
			bOk = selectAllAction.getButton();
			selectAllAction = new WAppsAction (ConfirmPanel.A_CANCEL, null, ConfirmPanel.A_CANCEL);
			bCancel = selectAllAction.getButton();
			selectAllAction = new WAppsAction (ConfirmPanel.A_PRINT, null, ConfirmPanel.A_PRINT);
			bPrint = selectAllAction.getButton();
			selectAllAction = new WAppsAction (ConfirmPanel.A_ZOOM, null, ConfirmPanel.A_ZOOM);
			bZoom = selectAllAction.getButton();
			selectAllAction = new WAppsAction (ConfirmPanel.A_EXPORT, null, ConfirmPanel.A_EXPORT);
			bExport =  selectAllAction.getButton();
			selectAllAction = new WAppsAction (ConfirmPanel.A_DELETE, null, ConfirmPanel.A_DELETE);
			bDelete = selectAllAction.getButton();
			selectAllAction = new WAppsAction ("Find", null, "Find");
			bFind = selectAllAction.getButton();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private void initComponents() {

		toolsBar = new ToolBar();
		bPrint = new Button();
		bZoom = new Button();
		bExport = new Button();
		bDelete = new Button();
		bFind = new Button();
		tabsPanel = new Tabbox();
		searchTab = new Borderlayout();
		topPanel = new Hbox();
		searchPanel = GridFactory.newGridLayout();
		bSearch = new Button();
		detail = new WListbox();
		bCancel = new Button();
		bOk = new Button();
		processPanel = new Borderlayout();
		graphPanel = new Borderlayout();
		footPanel= new Borderlayout();
		

		Borderlayout mainLayout = new Borderlayout();

		setupToolBar();

		bPrint.setLabel("Print");

		bPrint.addActionListener(new EventListener() {
			@Override
			public void onEvent(Event event) throws Exception {
				bPrintActionPerformed(event);
			}
		});

		toolsBar.appendChild(bPrint);

		bZoom.setLabel("Zoom");
		bZoom.addActionListener(new EventListener() {
			public void onEvent(Event evt) {
				bZoomActionPerformed(evt);
			}
		});
		toolsBar.appendChild(bZoom);

		bExport.setLabel("Export");
		bExport.addActionListener(new EventListener() {
			public void onEvent(Event evt) {
				bExportActionPerformed(evt);
			}
		});
		toolsBar.appendChild(bExport);

		bDelete.setLabel("Delete");
		bDelete.addActionListener(new EventListener() {
			public void onEvent(Event evt) {
				bDeleteActionPerformed(evt);
			}
		});
		toolsBar.appendChild(bDelete);

		bFind.setLabel("Find");
		bFind.addActionListener(new EventListener() {
			public void onEvent(Event evt) {
				bFindActionPerformed(evt);
			}
		});
		toolsBar.appendChild(bFind);
		
		try{
			WAppsAction selectAllAction = new WAppsAction ("SelectAll", null, "Select All");
			bSelectAll = selectAllAction.getButton();
			bSelectAll.setLabel("Select All");

		}catch(Exception e)
		{
			e.printStackTrace();
		}

		toolsBar.appendChild(bSelectAll);

		bSelectAll.addActionListener(new EventListener(){
        	public void onEvent(Event evt){
        		if(detail.getRowCount()>0)
        		{
        			if(!isAllSelected)
        			{
        				int size = detail.getRowCount();
        			
	        			int selectedList[] = new int[size];
	        			
		        		
	        			for(int x = 0; x<= detail.getRowCount() -1; x++)
		        		{
		        			selectedList[x] = x;
		        		}
		        		detail.setSelectedIndices(selectedList);
        			}
        			else
        			{
        				detail.clearSelection();
        			}
        			
        			isAllSelected = !isAllSelected;
        		}
        	}
        });

		m_frame.setWidth("100%");
		m_frame.setHeight("100%");
		m_frame.setStyle("position: absolute; padding: 0; margin: 0");
		m_frame.appendChild(mainLayout);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setStyle("position: absolute");

		North north = new North();
		north.appendChild(toolsBar);
		mainLayout.appendChild(north);

		searchTab = new Borderlayout();
		searchTab.setWidth("100%");
		searchTab.setHeight("100%");
		searchTab.setStyle("background-color: transparent");

		topPanel = new Hbox();
		topPanel.setHeight("90%");
		topPanel.setWidth("100%");
		//topPanel.setStyle("position: absolute");
		topPanel.setStyle("background-color: transparent");

		//searchPanel = GridFactory.newGridLayout();
		searchPanel.setStyle("background-color: transparent");

		topPanel.appendChild(searchPanel);
		bSearch.setLabel("Search");

		bSearch.addActionListener(new EventListener() {
			public void onEvent(Event evt) {
				bSearchActionPerformed(evt);
			}
		});


		North sNorth = new North();

		
		Vbox vbox = new Vbox();
		vbox.appendChild(topPanel);
		vbox.appendChild(bSearch);
		vbox.setAlign("center");
		vbox.setWidth("100%");
		vbox.setStyle("background-color: transparent");
		
		Div div = new Div();
		div.appendChild(vbox);
		div.setWidth("100%");
		div.setHeight("100%");
		
		sNorth.setFlex(true);
		sNorth.setCollapsible(true);
		sNorth.setAutoscroll(true);
		sNorth.appendChild(div);
		sNorth.setTitle(" ");
		sNorth.setStyle("background-color: transparent");
		sNorth.setStyle("border: none");
		searchTab.appendChild(sNorth);
		
		
		
		

		detail.setWidth("100%");
		detail.setHeight("100%");
		Center dCenter = new Center();
		dCenter.appendChild(detail);
		dCenter.setBorder("none");
		detail.setVflex(true);
		detail.setFixedLayout(true);
		dCenter.setFlex(true);
		dCenter.setAutoscroll(true);
		
		footPanel.setHeight("100%");
		footPanel.setWidth("100%");
		footPanel.appendCenter(detail);
		
		Div dv = new Div();
		div.appendChild(footPanel);
		div.setHeight("100%");
		div.setWidth("100%");
		

		searchTab.appendCenter(footPanel);

		Hbox hbox = new Hbox();

		bCancel.setLabel("Cancel");

		bCancel.addActionListener(new EventListener() {
			public void onEvent(Event evt) {
				bCancelActionPerformed(evt);
			}
		});

		bOk.setLabel("Ok");
		bOk.addActionListener(new EventListener() {
			public void onEvent(Event evt) {
				bOkActionPerformed(evt);
			}
		});

		hbox.appendChild(bCancel);
		hbox.appendChild(bOk);
		hbox.setAlign("center");
		searchTab.appendSouth(hbox);
		searchTab.getSouth().setBorder("none");

		Tabpanel search = new Tabpanel();
		search.setWidth("100%");
		search.appendChild(searchTab);

		Tab tabSearch = new Tab();
		tabSearch.addEventListener(Events.ON_SELECT, this);
		tabSearch.setLabel(Msg.getMsg(Env.getCtx(), "Search").replaceAll("[&]",
				""));

		Tabs tabs = new Tabs();
		tabs.appendChild(tabSearch);

		Tabpanels tabPanels = new Tabpanels();
		tabPanels.setWidth("100%");
		tabPanels.appendChild(search);

		graphPanel = new Borderlayout();

		Tabpanel graph = new Tabpanel();
		graph.setWidth("100%");
		graph.appendChild(graphPanel);

		Tab tabGraph = new Tab();
		tabGraph.addEventListener(Events.ON_SELECT, this);
		tabGraph.setLabel(Msg.getMsg(Env.getCtx(), "Graph").replaceAll("[&]",
				""));

		tabs.appendChild(tabGraph);
		tabPanels.appendChild(graph);

		tabsPanel.setWidth("100%");
		tabsPanel.setHeight("100%");
		tabsPanel.appendChild(tabs);
		tabsPanel.appendChild(tabPanels);

		mainLayout.appendCenter(tabsPanel);
	}

	private void bZoomActionPerformed(Event evt) {// GEN-FIRST:event_bZoomActionPerformed
		// TODO add your handling code here:
		cmd_zoom();
	}// GEN-LAST:event_bZoomActionPerformed

	private void bOkActionPerformed(Event evt) {
		dispose(true);
	}

	private void bCancelActionPerformed(Event evt) {
		m_frame.dispose();
	}

	private void bSearchActionPerformed(Event evt) {
		executeQuery();
	}

	private void bFindActionPerformed(Event evt) {
		// TODO add your handling code here:
	}

	private void bExportActionPerformed(Event evt) {
		// TODO add your handling code here:
	}

	private void bDeleteActionPerformed(Event evt) {
		// TODO add your handling code here:
	}

	private void bPrintActionPerformed(Event evt) {
		// TODO add your handling code here:
	}

	public void work() {
		PreparedStatement m_pstmt = null;
		ResultSet m_rs = null;
		long start = System.currentTimeMillis();

		// Clear Table
		detail.setRowCount(0);
		//
		String dynWhere = getSQLWhere();
		StringBuffer sql = new StringBuffer(m_sqlMain);
		if (dynWhere.length() > 0)
			sql.append(dynWhere); // includes first AND
		sql.append(m_sqlOrder);
		String dataSql = Msg.parseTranslation(Env.getCtx(), sql.toString()); // Variables
		dataSql = MRole.getDefault().addAccessSQL(dataSql,
				m_View.getParentEntityAliasName(), MRole.SQL_FULLYQUALIFIED,
				MRole.SQL_RO);
		log.finer(dataSql);

		try {
			m_pstmt = DB.prepareStatement(dataSql, null);
			if (getParametersValues().size() > 0)
				DB.setParameters(m_pstmt, getParametersValues());

			setParameters(m_pstmt, false); // no count
			log.fine("Start query - " + (System.currentTimeMillis() - start)
					+ "ms");
			m_rs = m_pstmt.executeQuery();
			log.fine("End query - " + (System.currentTimeMillis() - start)
					+ "ms");

			detail.loadTable(m_rs);
		} catch (SQLException e) {
			log.log(Level.SEVERE, dataSql, e);
		}
		DB.close(m_rs, m_pstmt);
		//
		int no = detail.getRowCount();
		log.fine("#" + no + " - " + (System.currentTimeMillis() - start) + "ms");
		detail.autoSize();
		//

		setStatusLine(
				Integer.toString(no) + " "
						+ Msg.getMsg(Env.getCtx(), "SearchRows_EnterQuery"),
				false);
		setStatusDB(Integer.toString(no));
		if (no == 0)
			log.fine(dataSql);
		else {
			detail.setFocus(true);
		}
	} // run

	@Override
	public void onEvent(Event event) throws Exception {

	}

	@Override
	public CustomForm getForm() {
		return m_frame;
	}

	@Override
	public void valueChange(ValueChangeEvent evt) {

	}

	@Override
	public void tableChanged(WTableModelEvent event) {

	}

	@Override
	public void lockUI(ProcessInfo pi) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unlockUI(ProcessInfo pi) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isUILocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void executeASync(ProcessInfo pi) {
		// TODO Auto-generated method stub

	}

}
