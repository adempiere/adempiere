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

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.MBrowse;
import org.adempiere.model.MBrowseField;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.apps.BusyDialog;
import org.adempiere.webui.apps.ProcessParameterPanel;
import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Tab;
import org.adempiere.webui.component.Tabbox;
import org.adempiere.webui.component.Tabpanel;
import org.adempiere.webui.component.Tabpanels;
import org.adempiere.webui.component.Tabs;
import org.adempiere.webui.component.ToolBar;
import org.adempiere.webui.component.VerticalBox;
import org.adempiere.webui.component.WAppsAction;
import org.eevolution.grid.WBrowseListbox;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.event.WTableModelListener;
import org.adempiere.webui.panel.CustomForm;
import org.adempiere.webui.panel.IFormController;
import org.adempiere.webui.panel.StatusBarPanel;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.window.FDialog;
import org.compiere.apps.ProcessCtl;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.GridField;
import org.compiere.model.GridFieldVO;
import org.compiere.model.MPInstance;
import org.compiere.model.MProcess;
import org.compiere.model.MQuery;
import org.compiere.model.MRole;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoUtil;
import org.compiere.util.ASyncProcess;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Center;
import org.zkoss.zul.North;
import org.zkoss.zul.South;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Row;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;

import org.zkoss.zul.Separator;
import org.zkoss.zul.Vbox;

/**
 * Implementation Smart Browser for ZK
 * @author victor.perez@e-evoluton.com, www.e-evolution.com 
 * 	<li>FR [ 3426137 ] Smart Browser
 *  https://sourceforge.net/tracker/?func=detail&aid=3426137&group_id=176962&atid=879335
 *
 */
public class WBrowser extends Browser implements IBrowser ,IFormController,
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

	private WBrowseListbox detail;
	private Borderlayout graphPanel;
	private WBrowserSearch searchGrid;
	private Borderlayout searchTab;
	private North collapsibleSeach;
	private Borderlayout detailPanel;
	private Tabbox tabsPanel;
	private ToolBar toolsBar;
	private Hbox topPanel;
	private BusyDialog m_waiting;
	private VerticalBox dialogBody;

	public static CustomForm openBrowse(int AD_Browse_ID) {
		MBrowse browse = new MBrowse(Env.getCtx(), AD_Browse_ID , null);
		boolean modal = true;
		int WindowNo = 0;
		String value = "";
		String keyColumn = "";
		boolean multiSelection = true;
		String whereClause = "";
		return new WBrowser(modal, WindowNo, value, browse, keyColumn, multiSelection, whereClause).getForm();
	}
	
	public WBrowser(boolean modal, int WindowNo, String value, MBrowse browse,
			String keyColumn, boolean multiSelection, String whereClause) {
		
		super(modal, WindowNo, value, browse, keyColumn, multiSelection,
				whereClause);
		
		m_frame = new CustomForm();
		windowNo = SessionManager.getAppDesktop().registerWindow(this);
		setContextWhere(browse, whereClause);
		initComponents();
		statInit();
		detail.setMultiSelection(true);
		int no = detail.getRowCount();
		setStatusLine(
				Integer.toString(no) + " "
						+ Msg.getMsg(Env.getCtx(), "SearchRows_EnterQuery"),
				false);
		setStatusDB(Integer.toString(no));
		
		if(isExecuteQueryByDefault())
			executeQuery();
	}

	private void statInit() {

		Rows rows = new Rows();
		rows.setParent(searchGrid);

		int cols = 0;
		Row row = rows.newRow();

		for (MBrowseField field : m_Browse.getCriteriaFields()) {
			String title = field.getName();
			String name = field.getAD_View_Column().getColumnName();
			searchGrid.addField(field, row, name, title);

			cols++;

			if (field.isRange())
				cols++;

			if (cols >= 2) {
				cols = 0;
				row = rows.newRow();
			}
		}
		
		searchGrid.dynamicDisplay();
		
		if (m_Browse.getAD_Process_ID() > 0) {
			
			m_process = MProcess.get(Env.getCtx(), m_Browse.getAD_Process_ID());
			ProcessInfo pi = new ProcessInfo(m_process.getName(),
					m_Browse.getAD_Process_ID());
			pi.setAD_User_ID(Env.getAD_User_ID(Env.getCtx()));
			pi.setAD_Client_ID(Env.getAD_Client_ID(Env.getCtx()));
			pi.setWindowNo(getWindowNo());
			setBrowseProcessInfo(pi);
			parameterPanel = new ProcessParameterPanel(pi.getWindowNo(), pi , "100%");
			parameterPanel.setMode(ProcessParameterPanel.BROWSER_MODE);
			parameterPanel.init();
			
			South south = new South();
			south.setAutoscroll(true);
			south.setSplittable(true);
			south.setCollapsible(false);
			
			Div div = new Div();
			div.setWidth("100%");
			div.appendChild(parameterPanel);
			south.appendChild(div);	
			detailPanel.appendChild(south);
		}		
	}


	/**
	 * General Init
	 *
	 * @return true, if success
	 */
	private boolean initBrowser() {
		List<MBrowseField> fields = initBrowserTable();
		if (fields == null)
			return false;

		StringBuilder where = new StringBuilder("");
		setContextWhere(m_Browse, null);
		if (p_whereClause.length() > 0) {
			where.append(p_whereClause);
		}

		prepareTable(fields, m_View.getFromClause(), where.toString(),"2");
		return true;
	} // initInfo
	/**
	 * Init info with Table. - find QueryColumns (Value, Name, ..) - build
	 * gridController & column
	 *
	 * @return BrowseFields
	 */
	private List<MBrowseField> initBrowserTable() {

		List<MBrowseField> list = initBrowserData();
		if (list.size() == 0) {
			FDialog.error(getWindowNo(), m_frame, "Error", "No Browse Fields");
			log.log(Level.SEVERE, "No Browser for view=" + m_View.getName());
			return null;
		}
		log.finest("Browse Fields #" + list.size());
		//centerPanel.setViewportView(detail);

		return list;
	} // initInfoTable

	public void setStatusLine(String text, boolean error) {
		statusBar.setStatusLine(text, error);
	}

	public void setStatusDB(String text) {
		statusBar.setStatusDB(text);
	}

	protected void executeQuery() {
		if (evaluateMandatoryFilter()) {
			if (getAD_Window_ID() > 1)
				bZoom.setEnabled(true);

			bSelectAll.setEnabled(true);
			bExport.setEnabled(true);

			if (isDeleteable())
				bDelete.setEnabled(true);

			collapsibleSeach.setOpen(!isCollapsibleByDefault());

			p_loadedOK = initBrowser();

			Env.setContext(Env.getCtx(), 0, "currWindowNo", getWindowNo());
			if (parameterPanel != null)
				parameterPanel.refreshContext();

			if (!testCount())
				return;

			setStatusLine(Msg.getMsg(Env.getCtx(), "StartSearch"), false);

			work();

			isAllSelected = isSelectedByDefault();
			selectedRows();
		}
	}

	private void cmd_zoom() {
		showBusyDialog();
		
		MQuery query = getMQuery();
		if(query != null)
			AEnv.zoom(getAD_Window_ID() , query);
		
		hideBusyDialog();
	}
	
	private void cmd_deleteSelection() {
		if (FDialog.ask(getWindowNo(), m_frame, "DeleteSelection"))
		{	
			int records = deleteSelection();
			setStatusLine(Msg.getMsg(Env.getCtx(), "Deleted") + records, false);
		}	
		 executeQuery();
	}

	/**************************************************************************
	 * Prepare Table, Construct SQL (m_m_sqlMain, m_sqlAdd) and size Window
	 * @param fields layout array
	 * @param from from clause
	 * @param staticWhere where clause
	 * @param orderBy order by clause
	 */
	protected void prepareTable(List<MBrowseField> fields,String from,
								String staticWhere, String orderBy) {
		browserFields = fields;
		StringBuffer sql = new StringBuffer("SELECT DISTINCT ");
		sql.append(detail.prepareTable(fields, p_multiSelection));
		detail.setMultiSelection(p_multiSelection);
		detail.setShowTotals(m_Browse.isShowTotal());

		sql.append(" FROM ").append(from);
		sql.append(" WHERE ");
		m_sqlMain = sql.toString();
		m_sqlCount = "SELECT COUNT(*) FROM " + from + " WHERE ";
		m_sqlOrderBy = getSQLOrderBy();

		if (m_keyColumnIndex == -1)
			log.log(Level.WARNING, "No KeyColumn - " + sql);
	} // prepareTable

	/*protected void prepareTable(Info_Column[] layout, String from,
			String staticWhere, String orderBy) {
		p_layout = layout;
		detail.prepareTable(layout, "" , "" , true, "");
		StringBuffer sql = new StringBuffer("SELECT DISTINCT ");
		for (int i = 0; i < layout.length; i++) {
			if (i > 0 && layout[i].getColSQL().length() > 0)
				sql.append(", ");
			sql.append(layout[i].getColSQL());
			// adding ID column
			if (layout[i].isIDcol())
				sql.append(",").append(layout[i].getIDcolSQL());			
			if (layout[i].isColorColumn())
				detail.setColorColumn(i);
			if (layout[i].getColClass() == IDColumn.class)
				m_keyColumnIndex = i;
		}

		sql.append(" FROM ").append(from);
		sql.append(" WHERE ");
		m_sqlMain = sql.toString();
		m_sqlCount = "SELECT COUNT(*) FROM " + from + " WHERE ";
		m_sqlOrderBy = getSQLOrderBy();
		
		if (m_keyColumnIndex == -1)
			log.log(Level.WARNING, "No KeyColumn - " + sql);
	}*/

	private boolean testCount() {
		int no = -1;
		no = getCount();

		MRole role = MRole.getDefault();
		if (role.isQueryMax(no))
			return true;

		return true;
	}

	/**
	 * Save Selection - Called by dispose
	 */
	protected void saveSelection() {
		// Already disposed
		if (detail == null)
			return;

		log.config("OK=" + m_ok);
		if (!m_ok) // did not press OK
		{
			m_results.clear();
			return;
		}

		// Multi Selection
		if (p_multiSelection) {
			m_results.clear();
			m_results.addAll(getSelectedRowKeys());
		} else // singleSelection
		{
			Integer data = getSelectedRowKey();
			if (data != null)
				m_results.add(data);
		}

		// Save Settings of detail info screens
		// saveSelectionDetail();
		// Clean-up
		detail.clearTable();
	}
	
	/**
	 * save result values
	 */
	protected void saveResultSelection() {
		if (m_keyColumnIndex == -1) {
			return;
		}

		if (p_multiSelection) {
			int rows = detail.getRowCount();
			WBrowserRows browserRows =detail.getData();
			m_values = new LinkedHashMap<Integer,LinkedHashMap<String,Object>>();
			for (int row = 0; row < rows; row++) {
				//Find the IDColumn Key
				Object data = detail.getModel().getValueAt(row,
						m_keyColumnIndex);
				if (data instanceof IDColumn) {
					IDColumn dataColumn = (IDColumn) data;
					if (dataColumn.isSelected()) {
						LinkedHashMap<String, Object> values = new LinkedHashMap<String, Object>();
						for(int col  = 0 ; col < browserRows.getColumnCount(); col ++)
						{
							MBrowseField bField =browserRows.getBrowserField(col);
							if (!bField.isReadOnly() || bField.isIdentifier() )
							{
								GridField gField = (GridField)detail.getData().getValue( row, col );
								Object value = gField.getValue();
								values.put(bField.getAD_View_Column().getColumnName(), value);
							}

						}
						/*for (Info_Column column : m_generalLayout)
						{	
							String columnName = column.getColSQL().substring(
									column.getColSQL().indexOf("AS ") + 3);
							if (!column.isReadOnly()
									|| IsIdentifierSelection(columnName)) {
								if (!column.isKeyPairCol()) {
									Object value = detail.getModel()
											.getValueAt(row, col);
									values.put(columnName, value);
								} else {
									KeyNamePair value = (KeyNamePair) detail
											.getModel().getValueAt(row, col);
									values.put(columnName, value.getKey());
								}
							}
							col++;
						}*/
						if(values.size() > 0)
							m_values.put(dataColumn.getRecord_ID(), values);
					}
				}
			}
		}
	}

	/**
	 * Get the keys of selected row/s based on layout defined in prepareTable
	 *
	 * @return IDs if selection present
	 */
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
		log.config("OK=" + ok);
		searchGrid.dispose();
		m_ok = ok;
		
		saveResultSelection();
		saveSelection();
		
		if (m_Browse.getAD_Process_ID() <= 0)
			return;

		MPInstance instance = new MPInstance(Env.getCtx(),
				m_Browse.getAD_Process_ID(), getBrowseProcessInfo().getRecord_ID());
		instance.saveEx();

		DB.createT_Selection(instance.getAD_PInstance_ID(), getSelectedKeys(),
				null);
		ProcessInfo pi = getBrowseProcessInfo();
		pi.setAD_PInstance_ID(instance.getAD_PInstance_ID());
		pi.setWindowNo(getWindowNo());
		parameterPanel.saveParameters();
		ProcessInfoUtil.setParameterFromDB(pi);
		setBrowseProcessInfo(pi);
		//Save Values Browse Field Update
		createT_Selection_Browse(instance.getAD_PInstance_ID());
		// Execute Process
		ProcessCtl worker = new ProcessCtl(this, pi.getWindowNo() , pi , null);
		worker.start();
        Env.clearWinContext(getWindowNo());
		SessionManager.getAppDesktop().closeActiveWindow();
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
			selectAllAction = new WAppsAction ("SelectAll", null, Msg.getMsg(Env.getCtx(),"SelectAll"));
			bSelectAll = selectAllAction.getButton();
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
		collapsibleSeach = new North();
		topPanel = new Hbox();
		searchGrid = new WBrowserSearch(getWindowNo());
		bSearch = new Button();
		detail = new WBrowseListbox(this);
		bCancel = new Button();
		bOk = new Button();
		graphPanel = new Borderlayout();
		detailPanel= new Borderlayout();

		Borderlayout mainLayout = new Borderlayout();

		setupToolBar();
		
		bSelectAll.setLabel(Msg.getMsg(Env.getCtx(),"SelectAll").replaceAll("[&]",""));
		bSelectAll.setEnabled(false);
		bSelectAll.addActionListener(new EventListener(){
    	public void onEvent(Event evt)
    	{
    		selectedRows();
    	}
        });
		

		toolsBar.appendChild(bSelectAll);
		
		//TODO: victor.perez@e-evolution.com pending print functionality
		/*bPrint.setLabel("Print");

		bPrint.addActionListener(new EventListener() {
			@Override
			public void onEvent(Event event) throws Exception {
				bPrintActionPerformed(event);
			}
		});

		toolsBar.appendChild(bPrint);*/

		bZoom.setLabel(Msg.getMsg(Env.getCtx(),"Zoom").replaceAll("[&]",""));
		bZoom.setEnabled(false);
		bZoom.addActionListener(new EventListener() {
			public void onEvent(Event evt) {
				bZoomActionPerformed(evt);
			}
		});
		
		//Only enable if exist a reference
		if(AD_Window_ID > 0)
			toolsBar.appendChild(bZoom);

		bExport.setLabel(Msg.getMsg(Env.getCtx(),"Export"));
		bExport.setEnabled(false);
		bExport.addActionListener(new EventListener() {
			public void onEvent(Event evt) {
				bExportActionPerformed(evt);
			}
		});
		toolsBar.appendChild(bExport);

		bDelete.setLabel(Msg.getMsg(Env.getCtx(),"Delete").replaceAll("[&]",""));
		bDelete.setEnabled(false);
		bDelete.addActionListener(new EventListener() {
			public void onEvent(Event evt) {
				bDeleteActionPerformed(evt);
			}
		});
		
		if(isDeleteable())
			toolsBar.appendChild(bDelete);

		//TODO: victor.perez@e-evolution.com pending find functionality
		/*bFind.setLabel("Find");
		bFind.addActionListener(new EventListener() {
			public void onEvent(Event evt) {
				bFindActionPerformed(evt);
			}
		});
		toolsBar.appendChild(bFind);*/

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
		searchTab.setWidth("99.4%");
		searchTab.setHeight("99.4%");
		searchTab.setStyle("background-color: transparent");

		topPanel = new Hbox();
		topPanel.setHeight("90%");
		topPanel.setWidth("100%");
		//topPanel.setStyle("position: absolute");
		topPanel.setStyle("background-color: transparent");

		searchGrid.setStyle("background-color: transparent");
		topPanel.appendChild(searchGrid);
		
		bSearch.setLabel(Msg.getMsg(Env.getCtx(), "StartSearch"));

		bSearch.addActionListener(new EventListener() {
			public void onEvent(Event evt) {
				bSearchActionPerformed(evt);
			}
		});
		
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

		collapsibleSeach.setTitle(Msg.getMsg(Env.getCtx(),("SearchCriteria")));
		collapsibleSeach.setCollapsible(true);
		collapsibleSeach.setAutoscroll(true);
		collapsibleSeach.appendChild(div);
		collapsibleSeach.setStyle("background-color: transparent");
		collapsibleSeach.setStyle("border: none");
		searchTab.appendChild(collapsibleSeach);

		detail.setWidth("100%");
		//detail.setHeight("100%");
		Center dCenter = new Center();
		dCenter.appendChild(detail);
		dCenter.setBorder("none");
		detail.setVflex(true);
		detail.setFixedLayout(true);
		dCenter.setHflex("true");
		dCenter.setVflex("true");
		dCenter.setAutoscroll(true);
		
		detailPanel.setHeight("100%");
		detailPanel.setWidth("100%");
		detailPanel.appendCenter(detail);
		
		Div dv = new Div();
		div.appendChild(detailPanel);
		div.setHeight("100%");
		div.setWidth("100%");

		searchTab.appendCenter(detailPanel);

		Hbox hbox = new Hbox();

		bCancel.setLabel(Msg.getMsg(Env.getCtx(), "Cancel").replaceAll("[&]",""));

		bCancel.addActionListener(new EventListener() {
			public void onEvent(Event evt) {
				bCancelActionPerformed(evt);
			}
		});

		bOk.setLabel(Msg.getMsg(Env.getCtx(), "Ok").replaceAll("[&]",""));
		bOk.addActionListener(new EventListener() {
			public void onEvent(Event evt) {
				bOkActionPerformed(evt);
			}
		});
		
		Div confirmDiv = new Div();
		confirmDiv.setAlign("center");
		hbox.appendChild(bCancel);
		hbox.appendChild(bOk);
		hbox.setAlign("center");
		confirmDiv.appendChild(hbox);
		Separator separator = new Separator();
		separator.setBar(true);
		confirmDiv.appendChild(separator);
		confirmDiv.appendChild(statusBar);

		
		searchTab.appendSouth(confirmDiv);
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

		//graphPanel = new Borderlayout();

		//TODO victor.perez@e-evolution.com implement Graph Functionality
		//Tabpanel graph = new Tabpanel();
		//graph.setWidth("100%");
		//graph.appendChild(graphPanel);
		//Tab tabGraph = new Tab();
		//tabGraph.addEventListener(Events.ON_SELECT, this);
		//tabGraph.setLabel(Msg.getMsg(Env.getCtx(), "Graph").replaceAll("[&]",
		//		""));
		//tabs.appendChild(tabGraph);
		//tabPanels.appendChild(graph);

		tabsPanel.setWidth("100%");
		tabsPanel.setHeight("100%");
		tabsPanel.appendChild(tabs);
		tabsPanel.appendChild(tabPanels);

		mainLayout.appendCenter(tabsPanel);
	}
	
	private void selectedRows()
	{
		int topIndex = detail.isShowTotals() ? 2 : 1;
		int rows = detail.getRowCount();
		int selectedList[] = new int[rows];
		if(isAllSelected)
		{
			for (int row = 0; row <= rows - topIndex; row++) {
				Object data = detail.getModel().getValueAt(row,
						m_keyColumnIndex);
				if (data instanceof IDColumn) {
					IDColumn dataColumn = (IDColumn) data;
					dataColumn.setSelected(true);
					detail.getModel().setValueAt(dataColumn, row,m_keyColumnIndex);
				}
				selectedList[row] = row;
			}
			detail.setSelectedIndices(selectedList);
		} else {
			for (int row = 0; row <= rows - topIndex; row++) {
				Object data = detail.getModel().getValueAt(row,
						m_keyColumnIndex);
				if (data instanceof IDColumn) {
					IDColumn dataColumn = (IDColumn) data;
					dataColumn.setSelected(false);
					detail.getModel().setValueAt(dataColumn, row,m_keyColumnIndex);
				}
			}
			detail.clearSelection();
		}
			isAllSelected = !isAllSelected;
	}

	private void bZoomActionPerformed(Event evt) {// GEN-FIRST:event_bZoomActionPerformed
		// TODO add your handling code here:
		cmd_zoom();
	}// GEN-LAST:event_bZoomActionPerformed

	private void bOkActionPerformed(Event evt) {
		log.config("OK=" + true);
		m_ok = true;
		
		saveResultSelection();
		saveSelection();
		
		if (m_Browse.getAD_Process_ID() > 0 && getSelectedKeys() != null)
		{

			MPInstance instance = new MPInstance(Env.getCtx(),
					m_Browse.getAD_Process_ID(), getBrowseProcessInfo().getRecord_ID());
			instance.saveEx();
	
			DB.createT_Selection(instance.getAD_PInstance_ID(), getSelectedKeys(),
					null);

			ProcessInfo pi = getBrowseProcessInfo();
			pi.setWindowNo(getWindowNo());
			pi.setAD_PInstance_ID(instance.getAD_PInstance_ID());
			//Save Values Browse Field Update
			createT_Selection_Browse(instance.getAD_PInstance_ID());
			parameterPanel.saveParameters();
			ProcessInfoUtil.setParameterFromDB(pi);
			setBrowseProcessInfo(pi);
						
			// Execute Process
			ProcessCtl worker = new ProcessCtl(this, pi.getWindowNo(), pi , null);
			showBusyDialog();
			worker.run();
			hideBusyDialog();
			setStatusLine(pi.getSummary(), pi.isError());
		}	
		p_loadedOK = initBrowser();
		collapsibleSeach.setOpen(true);
	}
	
	private void showBusyDialog() {
		m_waiting = new BusyDialog();
		m_waiting.setPage(m_frame.getPage());
		m_waiting.doHighlighted();
	}

	private void hideBusyDialog() {
		m_waiting.dispose();
		m_waiting = null;
	}

	private void bCancelActionPerformed(Event evt) {
		  SessionManager.getAppDesktop().closeActiveWindow();
	}

	private void bSearchActionPerformed(Event evt) {
		bZoom.setEnabled(true);
		bSelectAll.setEnabled(true);
		bExport.setEnabled(true);
		bDelete.setEnabled(true);
		collapsibleSeach.setOpen(!isCollapsibleByDefault());
		p_loadedOK = initBrowser();
		Env.setContext(Env.getCtx(), 0, "currWindowNo", getWindowNo());
		
		if (m_Browse.getAD_Process_ID() > 0)
			parameterPanel.refreshContext();
		
		executeQuery();
	}

	private void bFindActionPerformed(Event evt) {
		// TODO add your handling code here:
	}

	private void bExportActionPerformed(Event evt) {
		bExport.setEnabled(false);
		try 
		{	AMedia media = null;
			File file = exportXLS();
			media = new AMedia(m_Browse.getName(), "xls",
					"application/vnd.ms-excel", file, true);
			Filedownload.save(media);
		} catch (Exception e) {
			throw new AdempiereException("Failed to render report", e);
		}
		bExport.setEnabled(true);
	}

	
	public  ArrayList<ArrayList<Object>> getDataRows()
	{
		ArrayList<ArrayList<Object>> rows = m_rows;
		if (isShowTotal())
		{	
			ArrayList<Object> row = new ArrayList<Object>();
			int lastRow = detail.getRowCount() - 1;
			for (int column = 0; column <= detail.getColumnCount() - 1 ; column++) {
				Object data = detail.getValueAt(lastRow , column); 
				if (data == null)
					row.add(null);
				else
					row.add(data);
			}
			rows.add(row);
		}				
		return rows;
	}

	
	private void bDeleteActionPerformed(Event evt) {
		cmd_deleteSelection();
	}


	private void bPrintActionPerformed(Event evt) {
		// TODO add your handling code here:
	}

	public void work() {
		PreparedStatement m_pstmt = null;
		ResultSet m_rs = null;
		String dataSql = getSQL();
		long start = System.currentTimeMillis();

		// Clear Table
		detail.setRowCount(0);
		try {
			m_pstmt = getStatement(dataSql);
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
		
		if(isSelectedByDefault())
		{	
			isAllSelected = false;
			selectedRows();			
		}	
		

		int topIndex = detail.isShowTotals() ? 2 : 1;		//int topIndex = 1 ;
		int rows = detail.getRowCount();
		int selectedList[] = new int[rows];

			for (int row = 0; row <= rows - topIndex; row++) {
				Object data = detail.getModel().getValueAt(row,
						m_keyColumnIndex);
				if (data instanceof IDColumn) {
					IDColumn dataColumn = (IDColumn) data;
					dataColumn.setSelected(isSelectedByDefault());
					detail.getModel().setValueAt(dataColumn, row,m_keyColumnIndex);
				}
				selectedList[row] = row;
			}
			detail.setSelectedIndices(selectedList);
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
	/**
	 * get Parameter Value
	 * @param key
	 * @return Object Value
	 */
	@Override
	public Object getParameterValue(Object key)
	{       WEditor editor = (WEditor)  searchGrid.getParameters().get(key);
			if(editor != null)
				return editor.getValue();
			else
				return null;
	}
	
	public String getSQLWhere(boolean refresh) {
		
		if(!refresh)
			return m_whereClause;
		
		m_parameters_values = new ArrayList<Object>();
		m_parameters = new ArrayList<Object>();

		boolean onRange = false;
		StringBuilder sql = new StringBuilder(p_whereClause);

		for (Entry<Object, Object> entry : searchGrid.getParameters().entrySet()) {
			WEditor editor = (WEditor) entry.getValue();
			GridFieldVO field = editor.getGridField().getVO();
			if (!onRange) {

				if (editor.getValue() != null
						&& !editor.getValue().toString().isEmpty()
						&& !field.isRange) {
					sql.append(" AND ");
                    if(DisplayType.String == field.displayType)
                    {
						if (field.ColumnName.equals("Value")
								|| field.ColumnName.equals("DocumentNo"))
						{
							String value = (String)editor.getValue();
							if (value.contains(","))
							{
								value = value.replace(" ", "");
								String token;
								String inStr = new String(value);
								StringBuffer outStr = new StringBuffer("(");
								int i = inStr.indexOf(',');
								while (i != -1)
								{
									outStr.append("'" + inStr.substring(0, i) + "',");	
									inStr = inStr.substring(i+1, inStr.length());
									i = inStr.indexOf(',');

								}
								outStr.append("'" + inStr + "')");
								sql.append(field.Help).append(" IN ")
								.append(outStr);
							}						
						}
						else
                    	{
                    		sql.append(field.Help).append(" LIKE ? ");
                    		m_parameters.add(field.Help);
                    		m_parameters_values.add("%" + editor.getValue() + "%");
                    	}      	
                    }
                    else
                    {
                        sql.append(field.Help).append("=? ");
                        m_parameters.add(field.Help);
                        m_parameters_values.add(editor.getValue());
                    }
				} else if (editor.getValue() != null
						&& !editor.getValue().toString().isEmpty()
						&& field.isRange) {
					sql.append(" AND ");
					//sql.append(field.Help).append(" BETWEEN ?");
					sql.append(field.Help).append(" >= ? ");
					m_parameters.add(field.Help);
					m_parameters_values.add(editor.getValue());
					onRange = true;
				}
				else if (editor.getValue() == null
						&& field.isRange) {
					onRange = true;
				} else
					continue;
			} else if (editor.getValue() != null
					&& !editor.getValue().toString().isEmpty()) {
				//sql.append(" AND ? ");
				sql.append(" AND ").append(field.Help).append(" <= ? ");
				m_parameters.add(field.Help);
				m_parameters_values.add(editor.getValue());
				onRange = false;
			}
			else
				onRange = false;
		}
		m_whereClause = sql.toString();
		return sql.toString();
	}

	public void setParameters() {
		
		m_parameters_values = new ArrayList<Object>();
		m_parameters = new ArrayList<Object>();
		m_parameters_field = new ArrayList<GridFieldVO>();
		boolean onRange = false;
		
		for (Entry<Object, Object> entry : searchGrid.getParameters().entrySet()) {
			WEditor editor = (WEditor) entry.getValue();
			GridFieldVO field = editor.getGridField().getVO();
			if (!onRange) {

				if (editor.getValue() != null
						&& !editor.getValue().toString().isEmpty()
						&& !field.isRange) {
					m_parameters.add(field.Help);
					m_parameters_values.add(editor.getValue());
					m_parameters_field.add(field);
				} else if (editor.getValue() != null
						&& !editor.getValue().toString().isEmpty()
						&& field.isRange) {
					m_parameters.add(field.Help);
					m_parameters_values.add(editor.getValue());
					m_parameters_field.add(field);
					onRange = true;
				} else
					continue;
			} else if (editor.getValue() != null
					&& !editor.getValue().toString().isEmpty()) {
				m_parameters.add(field.Help);
				m_parameters_values.add(editor.getValue());
				m_parameters_field.add(field);
				onRange = false;
			}
		}
	}

	/**
	 * Evaluate Mandatory Filter
	 * @return
	 */
	public boolean evaluateMandatoryFilter()
	{
		Object value_from=null;
		boolean onRange = false;
		boolean result =true;

		for (Entry<Object, Object> entry : searchGrid.getParameters().entrySet()) {
			WEditor editor = (WEditor) entry.getValue();
			GridFieldVO field = editor.getGridField().getVO();
			if (!onRange) {

				if ((editor.getValue() == null
						|| (editor.getValue() != null && editor.getValue().toString().isEmpty()))
						&& !field.isRange
						&& editor.isMandatory()) {
					FDialog.error(getWindowNo(), getForm(), "FillMandatory", Msg.translate(Env.getCtx(), field.ColumnName));
					return false;
				} else if (editor.getValue() != null
						&& !editor.getValue().toString().isEmpty()
						&& field.isRange
						&& editor.isMandatory()) {
					onRange = true;
					value_from =editor.getValue();
				}else if (editor.getValue() == null
						&& field.isRange
						&& editor.isMandatory()) {
					onRange = true;
					value_from = null;
				}
				else
					continue;
			} else if ((editor.getValue() == null
					|| (editor.getValue() != null && editor.getValue().toString().isEmpty()))
					&& editor.isMandatory()) {
				if (value_from!=null){
					value_from=null;
					onRange = false;
				}
				else
				{
					FDialog.error(getWindowNo(), getForm(), "FillMandatory", Msg.translate(Env.getCtx(),field.ColumnName));
					return false;
				}
			}
			else{
				onRange = false;
				value_from=null;
			}

		}

		return result;
	}
}
