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

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.MBrowse;
import org.adempiere.model.MViewDefinition;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.apps.BusyDialog;
import org.adempiere.webui.apps.ProcessPanel;
import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Tab;
import org.adempiere.webui.component.Tabbox;
import org.adempiere.webui.component.Tabpanel;
import org.adempiere.webui.component.Tabpanels;
import org.adempiere.webui.component.Tabs;
import org.adempiere.webui.component.ToolBar;
import org.adempiere.webui.component.WAppsAction;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.panel.CustomForm;
import org.adempiere.webui.panel.IFormController;
import org.adempiere.webui.panel.StatusBarPanel;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.window.FDialog;
import org.compiere.apps.ProcessCtl;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.GridField;
import org.compiere.model.MQuery;
import org.compiere.process.ProcessInfo;
import org.compiere.util.ASyncProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.eevolution.grid.Browser;
import org.eevolution.grid.BrowserSearch;
import org.eevolution.grid.WBrowserTable;
import org.spin.util.ASPUtil;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Vbox;

/**
 * Implementation Smart Browser for ZK
 * @author victor.perez@e-evoluton.com, www.e-evolution.com 
 * 	<li>FR [ 3426137 ] Smart Browser
 *  https://sourceforge.net/tracker/?func=detail&aid=3426137&group_id=176962&atid=879335
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<li>FR [ 245 ] Change Smart Browse to MVC
 * 		@see https://github.com/adempiere/adempiere/issues/245
 * 		<li>FR [ 246 ] Smart Browse validate parameters when is auto-query
 * 		@see https://github.com/adempiere/adempiere/issues/246
 * 		<li>FR [ 247 ] Smart Browse don't have the standard buttons
 * 		@see https://github.com/adempiere/adempiere/issues/247
 * 		<li>FR [ 249 ] Smart Browse not validate process parameter when its are mandatory
 * 		@see https://github.com/adempiere/adempiere/issues/249
 * 		<li>BR [ 251 ] Smart Browse get the hidden parameters
 * 		@see https://github.com/adempiere/adempiere/issues/251
 * 		<li>FR [ 252 ] Smart Browse is Collapsible when query don't have result
 * 		@see https://github.com/adempiere/adempiere/issues/252
 *		<li>FR [ 265 ] ProcessParameterPanel is not MVC
 *		@see https://github.com/adempiere/adempiere/issues/265
 *		<li>BR [ 340 ] Smart Browse context is changed from table
 * 		@see https://github.com/adempiere/adempiere/issues/340
 * 		<li>BR [ 394 ] Smart browse does not reset context when windows is closed
 *		@see https://github.com/adempiere/adempiere/issues/394
 *	@author Michael Mckay michael.mckay@mckayerp.com
 *		<li> BR [ <a href="https://github.com/adempiere/adempiere/issues/495">495</a> ] 
 *			Parameter Panel & SmartBrowser criteria do not set gridField value
 */
public class WBrowser extends Browser implements IFormController,
		EventListener, ASyncProcess {

	private CustomForm m_frame = new CustomForm();
	private ProcessPanel parameterPanel;
	protected StatusBarPanel statusBar = new StatusBarPanel();

	private Button bCancel;
	private Button bDelete;
	private Button bUpdate;
	private Button bExport;
	private Button bOk;
	private Button bSearch;
	private Button bZoom;
	private Button bSelectAll;

	private WBrowserTable detail;
	private WBrowserSearch searchGrid;
	private Borderlayout searchTab;
	private North collapsibleSeach;
	private Borderlayout detailPanel;
	private Tabbox tabsPanel;
	private ToolBar toolsBar;
	private Hbox topPanel;
	private BusyDialog m_waiting;

	/**
	 * Open Browser
	 * @param windowNo
	 * @param browserId
	 * @param whereClause
	 * @param isSOTrx
	 * @return
	 */
	public static CustomForm openBrowse(int windowNo , int browserId , String whereClause, Boolean isSOTrx) {
		boolean modal = false;
		if (windowNo > 0)
			modal = true;
		String value = "";
		String keyColumn = "";
		boolean multiSelection = true;
		return new WBrowser(modal, windowNo, value, ASPUtil.getInstance().getBrowse(browserId), keyColumn, multiSelection, whereClause,isSOTrx).getForm();
	}
	
	/**
	 * Standard constructor
	 * @param modal
	 * @param WindowNo
	 * @param value
	 * @param browse
	 * @param keyColumn
	 * @param multiSelection
	 * @param whereClause
	 * @param isSOTrx
	 */
	public WBrowser(boolean modal, int WindowNo, String value, MBrowse browse,
			String keyColumn, boolean multiSelection, String whereClause, Boolean isSOTrx) {
		
		super(modal, WindowNo, value, browse, keyColumn, multiSelection,
				whereClause);
		//	Clear Context
		//	BR [ 394 ]
		m_frame = new CustomForm() {
			private static final long serialVersionUID = 2887836301614655646L;
			//	
			@Override
			public void onClose() {
				Env.clearWinContext(getWindowNo());
				super.onClose();
			}
		};
		windowNo = SessionManager.getAppDesktop().registerWindow(this);
		Env.setContext(Env.getCtx(), windowNo, "IsSOTrx", isSOTrx ? "Y" : "N");
		copyWinContext();
		setContextWhere(whereClause);
		//	Init Smart Browse
		init();
	}

	@Override
	public void init() {
		initComponents();
		statInit();
		detail.setMultiSelection(true);
		int no = detail.getRowCount();
		setStatusLine(
				Integer.toString(no) + " "
						+ Msg.getMsg(Env.getCtx(), "SearchRows_EnterQuery"),
				false);
		setStatusDB(Integer.toString(no));
		//	
		if(isExecuteQueryByDefault()
				&& searchGrid.validateParameters() == null)
			executeQuery();
	}
	
	/**
	 * Static Setup - add fields to parameterPanel (GridLayout)
	 */
	private void statInit() {
		searchGrid.init();
		Panel search = searchGrid.getPanel();
		search.setStyle("background-color: transparent");
		topPanel.appendChild(search);
		topPanel.setStyle("overflow-y:auto");
		//	
		if (getAD_Process_ID() > 0) {
			//	FR [ 245 ]
			initProcessInfo();
			//	FR [ 265 ]
			parameterPanel = new ProcessPanel(getWindowNo(), getBrowseProcessInfo() , "100%", ProcessPanel.COLUMNS_2);
			parameterPanel.setShowDescription(false);
			parameterPanel.setShowButtons(false);
			//	
			parameterPanel.createFieldsAndEditors();
			//	If don't have parameters then don'show collapsible panel
			if(parameterPanel.hasParameters()) {
				Panel panel = parameterPanel.getPanel();
				panel.setWidth("100%");
				panel.setHeight("100%");
				panel.setStyle("overflow-y:auto");
				
				South south = new South();
				south.setBorder("none");
				
				south.setAutoscroll(true);
				south.setFlex(true);
				south.setCollapsible(true);
				south.setTitle(Msg.getMsg(Env.getCtx(),("Parameter")));
				south.setCollapsible(true);
				south.setAutoscroll(true);
				south.appendChild(panel);
				south.setStyle("background-color: transparent");
				south.setStyle("border: none");
				south.setHeight("40%");
				//	
				detailPanel.appendChild(south);
			}
			//	
			detailPanel.setStyle("overflow-y:auto");
		}		
	}


	/**
	 * General Init
	 *
	 * @return true, if success
	 */
	private boolean initBrowser() {
		//	
		initBrowserTable(detail);
		//	
		if (browserFields.size() == 0) {
			FDialog.error(getWindowNo(), m_frame, "Error", "No Browse Fields");
			log.log(Level.SEVERE, "No Browser for view=" + getViewName());
			return false;
		}
		return true;
	} // initInfo

	public void setStatusLine(String text, boolean error) {
		statusBar.setStatusLine(text, error);
	}

	public void setStatusDB(String text) {
		statusBar.setStatusDB(text);
	}

	/**
	 * Execute Query
	 */
	protected void executeQuery() {
		//	FR [ 245 ]
		reloadDependents();
		String errorMsg = searchGrid.validateParameters();
		if (errorMsg == null) {
			if (getAD_Window_ID() > 1)
				bZoom.setEnabled(true);

			bSelectAll.setEnabled(true);
			bExport.setEnabled(true);

			if(isDeleteable()) {
				bDelete.setEnabled(true);
			}
			//	
			if(isUpdateable()) {
				bUpdate.setEnabled(true);
			}
			loadedOK = initBrowser();

			int no = testCount();
			if (no > 0) {
				if(!FDialog.ask(getWindowNo(), m_frame, "InfoHighRecordCount",
						String.valueOf(no))) {
					return;
				}
			}

			setStatusLine(Msg.getMsg(Env.getCtx(), "StartSearch"), false);

			work();
			
		} else {
			FDialog.error(getWindowNo(), m_frame, 
					"FillMandatory", Msg.parseTranslation(Env.getCtx(), errorMsg));
		}
	}
	
	/**
	 * Reload dependents
	 */
	private void reloadDependents() {
		Env.setContext(Env.getCtx(), 0, "currWindowNo", getWindowNo());
		if (parameterPanel != null)
			parameterPanel.refreshContext();
	}

	/**
	 * Zoom
	 */
	private void cmd_zoom() {
		showBusyDialog();
		
		MQuery query = getMQuery(detail);
		if(query != null)
			AEnv.zoom(getAD_Window_ID() , query);
		hideBusyDialog();
	}
	
	/**
	 * For Delete Selection
	 */
	private void cmd_deleteSelection() {
		if (FDialog.ask(getWindowNo(), m_frame, "DeleteSelection")) {	
			int records = deleteSelection(detail);
			setStatusLine(Msg.getMsg(Env.getCtx(), "Deleted") + " " + records, false);
			executeQuery();
		}
	}
	
	/**
	 * For Update Selection
	 */
	private void cmd_updateSelection() {
		if (FDialog.ask(getWindowNo(), m_frame, "UpdateSelection")) {	
			int records = updateSelection(detail);
			setStatusLine(Msg.getMsg(Env.getCtx(), "Updated") + " " + records, false);
		}
	}

	/**
	 * Get the keys of selected row/s based on layout defined in prepareTable
	 *
	 * @return IDs if selection present
	 */
	public ArrayList<Integer> getSelectedRowKeys() {
		ArrayList<Integer> selectedDataList = new ArrayList<Integer>();

		if (keyColumnIndex == -1) {
			return selectedDataList;
		}

		if (isMultiSelection) {
			int rows = detail.getRowCount();
			for (int row = 0; row < rows; row++) {
				Object data = detail.getModel().getValueAt(row,
						keyColumnIndex);
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
			if (row != -1 && keyColumnIndex != -1) {
				Object data = detail.getModel().getValueAt(row,
						keyColumnIndex);
				if (data instanceof IDColumn)
					selectedDataList.add(((IDColumn) data).getRecord_ID());
				if (data instanceof Integer)
					selectedDataList.add((Integer) data);
			}
		}

		return selectedDataList;
	}

	/**
	 * Add Components to tool bar
	 */
	private void setupToolBar() {

		try{
			toolsBar = new ToolBar();
			WAppsAction action = new WAppsAction (ConfirmPanel.A_REFRESH, null, ConfirmPanel.A_REFRESH);
			bSearch = action.getButton();
			action = new WAppsAction (ConfirmPanel.A_OK, null, ConfirmPanel.A_OK);
			bOk = action.getButton();
			action = new WAppsAction (ConfirmPanel.A_CANCEL, null, ConfirmPanel.A_CANCEL);
			bCancel = action.getButton();
			action = new WAppsAction (ConfirmPanel.A_ZOOM, null, ConfirmPanel.A_ZOOM);
			bZoom = action.getButton();
			action = new WAppsAction (ConfirmPanel.A_EXPORT, null, ConfirmPanel.A_EXPORT);
			bExport =  action.getButton();
			action = new WAppsAction (ConfirmPanel.A_DELETE, null, ConfirmPanel.A_DELETE);
			bDelete = action.getButton();
			action = new WAppsAction ("Save", null, ConfirmPanel.A_CUSTOMIZE);
			bUpdate = action.getButton();
			action = new WAppsAction ("SelectAll", null, Msg.getMsg(Env.getCtx(),"SelectAll"));
			bSelectAll = action.getButton();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Initialize View Components
	 */
	private void initComponents() {

		toolsBar = new ToolBar();
		bZoom = new Button();
		bExport = new Button();
		bDelete = new Button();
		bUpdate = new Button();
		tabsPanel = new Tabbox();
		searchTab = new Borderlayout();
		collapsibleSeach = new North();
		topPanel = new Hbox();
		searchGrid = new WBrowserSearch(getWindowNo(), getAD_Browse_ID(), BrowserSearch.COLUMNS_2);
		detail = new WBrowserTable(this);
		detail.addEventListener(Events.ON_SELECT, this);
		bCancel = new Button();
		bOk = new Button();
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
		
		bZoom.setLabel(Msg.getMsg(Env.getCtx(),"Zoom").replaceAll("[&]",""));
		bZoom.setEnabled(false);
		bZoom.addActionListener(new EventListener() {
			public void onEvent(Event evt) {
				cmd_Zoom();
			}
		});
		
		//Only enable if exist a reference
		if(getAD_Window_ID() > 0) {
			toolsBar.appendChild(bZoom);
		}

		bExport.setLabel(Msg.getMsg(Env.getCtx(),"Export"));
		bExport.setEnabled(false);
		bExport.addActionListener(new EventListener() {
			public void onEvent(Event evt) {
				cmd_Export();
			}
		});
		toolsBar.appendChild(bExport);

		bDelete.setLabel(Msg.getMsg(Env.getCtx(),"Delete").replaceAll("[&]",""));
		bDelete.setEnabled(false);
		bDelete.addActionListener(new EventListener() {
			public void onEvent(Event evt) {
				cmd_deleteSelection();
			}
		});
		//	
		if(isDeleteable()) {
			toolsBar.appendChild(bDelete);
		}
		bUpdate.setLabel(Msg.getMsg(Env.getCtx(),"Update").replaceAll("[&]",""));
		bUpdate.setEnabled(false);
		bUpdate.addActionListener(new EventListener() {
			public void onEvent(Event evt) {
				cmd_updateSelection();
			}
		});
		//	For Updateable
		if(isUpdateable()) {
			toolsBar.appendChild(bUpdate);
		}

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
		topPanel.setStyle("background-color: transparent");

		bSearch.setLabel(Msg.getMsg(Env.getCtx(), "StartSearch"));

		bSearch.addActionListener(new EventListener() {
			public void onEvent(Event evt) {
				cmd_Search();
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
		collapsibleSeach.setStyle("overflow-y:auto");
		collapsibleSeach.setStyle("background-color: transparent");
		collapsibleSeach.setStyle("border: none");
		searchTab.appendChild(collapsibleSeach);

		detail.setWidth("100%");
		detail.setHeight("100%");
		Center dCenter = new Center();
		dCenter.appendChild(detail);
		dCenter.setBorder("none");
		detail.setVflex(true);
		detail.setFixedLayout(true);
		dCenter.setFlex(true);
		dCenter.setAutoscroll(true);
		
		detailPanel.setHeight("100%");
		detailPanel.setWidth("100%");
		detailPanel.appendCenter(detail);
		
//		Div dv = new Div();
		div.appendChild(detailPanel);
		div.setHeight("100%");
		div.setWidth("100%");

		searchTab.appendCenter(detailPanel);

		Hbox hbox = new Hbox();

//		bCancel.setLabel(Msg.getMsg(Env.getCtx(), "Cancel").replaceAll("[&]",""));

		bCancel.addActionListener(new EventListener() {
			public void onEvent(Event evt) {
				cmd_Cancel();
			}
		});

//		bOk.setLabel(Msg.getMsg(Env.getCtx(), "Ok").replaceAll("[&]",""));
		bOk.addActionListener(new EventListener() {
			public void onEvent(Event evt) {
				cmd_Ok();
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

	/**
	 * Zoom a Record
	 */
	private void cmd_Zoom() {
		cmd_zoom();
	}

	/**
	 * Ok Action
	 */
	private void cmd_Ok() {
		log.config("OK=" + true);
		m_ok = true;
		
		saveResultSelection(detail);
		saveSelection(detail);
		//	Is Process ok
		boolean isOk = false;
		//	Valid Process, Selected Keys and process parameters
		if (getAD_Process_ID() > 0 && getSelectedKeys() != null) {
			parameterPanel.getProcessInfo().setAD_PInstance_ID(-1);
			// FR [ 265 ]
			if(parameterPanel.validateParameters() == null) {
				//	Save Parameters
				if(parameterPanel.saveParameters() == null) {
					//	Get Process Info
					ProcessInfo pi = parameterPanel.getProcessInfo();
					if (getFieldKey() != null && getFieldKey().get_ID() > 0) {
						MViewDefinition viewDefinition = (MViewDefinition) getFieldKey().getAD_View_Column().getAD_View_Definition();
						pi.setAliasForTableSelection(viewDefinition.getTableAlias());
						pi.setTableSelectionId(viewDefinition.getAD_Table_ID());
					}
					//	Set Selected Values
					pi.setSelectionValues(getSelectedValues());
					//	
					setBrowseProcessInfo(pi);	
					// Execute Process
					ProcessCtl worker = new ProcessCtl(this, pi.getWindowNo(), pi , null);
					showBusyDialog();
					worker.run();
					hideBusyDialog();
					setStatusLine(pi.getSummary(), pi.isError());
					//	For Valid Ok
					isOk = !pi.isError();
				}
			}
		}
		//	For when is ok the process
		if(isOk) {
			//	Close
			if(getParentWindowNo() > 0) {
				//	BR [ 394 ]
				Env.clearWinContext(getWindowNo());
				SessionManager.getAppDesktop().closeActiveWindow();
				return;
			}
			//	Else Reset
			loadedOK = initBrowser();
			collapsibleSeach.setOpen(true);
		}
	}
	
	/**
	 * Show dialog for busy window
	 */
	private void showBusyDialog() {
		m_waiting = new BusyDialog();
		m_waiting.setPage(m_frame.getPage());
		m_waiting.doHighlighted();
	}

	private void hideBusyDialog() {
		m_waiting.dispose();
		m_waiting = null;
	}

	/**
	 * Cancel and Dispose
	 */
	private void cmd_Cancel() {
		//	BR [ 394 ]
		Env.clearWinContext(getWindowNo());
		SessionManager.getAppDesktop().closeActiveWindow();
	}

	/**
	 * Search
	 */
	private void cmd_Search() {
		bZoom.setEnabled(true);
		bSelectAll.setEnabled(true);
		bExport.setEnabled(true);
		bDelete.setEnabled(true);
		bUpdate.setEnabled(true);
		//	
		executeQuery();
	}

	/**
	 * Export Data
	 */
	private void cmd_Export() {
		bExport.setEnabled(false);
		try 
		{
			File file = exportXLS(detail);
			AMedia media = new AMedia(file.getName() , "xls", "application/xls", file, true);
			Filedownload.save(media);
		} catch (Exception e) {
			throw new AdempiereException("Failed to render report", e);
		}
		bExport.setEnabled(true);
	}

	/**
	 * Work Task
	 */
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
		if (no == 0) {
			//	FR [ 252 ]
			if(!collapsibleSeach.isOpen()) {
				collapsibleSeach.setOpen(true);
			}
			log.fine(dataSql);
		} else {
			if(collapsibleSeach.isOpen()) {
				collapsibleSeach.setOpen(false);
			}
			detail.setFocus(true);
		}
		
		isAllSelected = isSelectedByDefault();
		if(isAllSelected) {
			selectedRows();
		}
	} // run

	@Override
	public void onEvent(Event event) throws Exception {
		if (event == null)
			return;
		//	For Click
		if (event.getTarget() == detail && Events.ON_SELECT.equals(event.getName())) {
			//	For row
			//click on selected row to enter edit mode
			int index = detail.getSelectedRow();
			if (index >= 0 ) {
				detail.getData().setCurrentRow(index);
			}
			//	
        }
	}

	@Override
	public CustomForm getForm() {
		return m_frame;
	}

	@Override
	public void lockUI(ProcessInfo pi) {
		
	}

	@Override
	public void unlockUI(ProcessInfo pi) {
		parameterPanel.setProcessInfo(pi);
		parameterPanel.openResult();
	}

	@Override
	public boolean isUILocked() {
		return false;
	}

	@Override
	public void executeASync(ProcessInfo pi) {
		
	}
	
	@Override
	public LinkedHashMap<String, GridField> getPanelParameters() {
		LinkedHashMap<String, GridField> m_List = new LinkedHashMap<String, GridField>();
		for (Entry<String, Object> entry : searchGrid.getParameters().entrySet()) {
			WEditor editor = (WEditor) entry.getValue();
			//	BR [ 251 ]
			if(!editor.isVisible()
					|| editor.getGridField().isInfoOnly())
				continue;
			//
			GridField field = editor.getGridField();
			m_List.put(entry.getKey(), field);
		}
		//	Default Return
		return m_List;
	}
	
	/**
	 * Selected Rows
	 */
	private void selectedRows() {
		int topIndex = detail.isShowTotals() ? 2 : 1;
		int rows = detail.getRowCount();
		int selectedList[] = new int[rows];
		if(isAllSelected)
		{
			for (int row = 0; row <= rows - topIndex; row++) {
				Object data = detail.getModel().getValueAt(row,
						keyColumnIndex);
				if (data instanceof IDColumn) {
					IDColumn dataColumn = (IDColumn) data;
					dataColumn.setSelected(true);
					detail.getModel().setValueAt(dataColumn, row,keyColumnIndex);
				}
				selectedList[row] = row;
			}
			detail.setSelectedIndices(selectedList);
		} else {
			for (int row = 0; row <= rows - topIndex; row++) {
				Object data = detail.getModel().getValueAt(row,
						keyColumnIndex);
				if (data instanceof IDColumn) {
					IDColumn dataColumn = (IDColumn) data;
					dataColumn.setSelected(false);
					detail.getModel().setValueAt(dataColumn, row,keyColumnIndex);
				}
			}
			detail.clearSelection();
		}
		isAllSelected = !isAllSelected;
	}
}
