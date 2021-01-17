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

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.MBrowse;
import org.adempiere.model.MViewDefinition;
import org.compiere.Adempiere;
import org.compiere.apps.ADialog;
import org.compiere.apps.AEnv;
import org.compiere.apps.AppsAction;
import org.compiere.apps.ConfirmPanel;
import org.compiere.apps.ProcessCtl;
import org.compiere.apps.ProcessController;
import org.compiere.apps.ProcessPanel;
import org.compiere.apps.StatusBar;
import org.compiere.apps.Waiting;
import org.compiere.apps.form.FormFrame;
import org.compiere.grid.ed.VEditor;
import org.compiere.model.GridField;
import org.compiere.model.MQuery;
import org.compiere.process.ProcessInfo;
import org.compiere.swing.CButton;
import org.compiere.swing.CFrame;
import org.compiere.swing.CPanel;
import org.compiere.swing.CollapsiblePanel;
import org.compiere.util.ASyncProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Login;
import org.compiere.util.Msg;
import org.compiere.util.Splash;
import org.eevolution.grid.Browser;
import org.eevolution.grid.BrowserSearch;
import org.eevolution.grid.VBrowserTable;
import org.spin.util.ASPUtil;

/**
 * UI Browser
 * 
 * @author victor.perez@e-evolution.com, victor.perez@e-evolution.com
 * <li>FR [ 3426137 ] Smart Browser
 * https://sourceforge.net/tracker/?func=detail&aid=3426137&group_id=176962&atid=879335
 * @author carlosaparada@gmail.com Carlos Parada, ERP Consultores y asociados
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<li>FR [ 245 ] Change Smart Browse to MVC
 * 		@see https://github.com/adempiere/adempiere/issues/245
 * 		<li>FR [ 246 ] Smart Browse validate parameters when is auto-query
 * 		@see https://github.com/adempiere/adempiere/issues/246
 * 		<li>FR [ 247 ] Smart Browse don't have the standard buttons
 * 		@see https://github.com/adempiere/adempiere/issues/247
 * 		<li>FR [ 248 ] Smart Browse not open on modal inside a window
 * 		@see https://github.com/adempiere/adempiere/issues/248
 * 		<li>FR [ 249 ] Smart Browse not validate process parameter when its are mandatory
 * 		@see https://github.com/adempiere/adempiere/issues/249
 * 		<li>BR [ 251 ] Smart Browse get the hidden parameters
 * 		@see https://github.com/adempiere/adempiere/issues/251
 * 		<li>FR [ 252 ] Smart Browse is Collapsible when query don't have result
 * 		@see https://github.com/adempiere/adempiere/issues/252
 * 		<li>FR [ 352 ] T_Selection is better send to process like a HashMap instead read from disk
 *		@see https://github.com/adempiere/adempiere/issues/352
 *		<li>BR [ 394 ] Smart browse does not reset context when windows is closed
 *		@see https://github.com/adempiere/adempiere/issues/394
 *		<li>BR [ 460 ] Update context when you select a row in a SmartBrowser
 *		@see https://github.com/adempiere/adempiere/issues/460
 */
public class VBrowser extends Browser implements ActionListener, ListSelectionListener, ASyncProcess {
	/**
	 * get Browse
	 * @param windowNo
	 * @param browserId
	 * @param whereClause
	 * @param isSOTrx
	 */
	public static CFrame openBrowse(int windowNo , int browserId, String whereClause, Boolean isSOTrx) {
		MBrowse browse = new MBrowse(Env.getCtx(), browserId , null);
		boolean modal = false;
		if (windowNo > 0 )
			modal = true;
		String value = "";
		String keyColumn = "";
		boolean multiSelection = true;
		FormFrame ff = new FormFrame(windowNo);
		return new VBrowser(ff, modal , windowNo, value, browse, keyColumn,multiSelection, whereClause, isSOTrx)
		.getFrame();
	}

	/**
	 * Detail Protected Constructor.
	 * 
	 * @param frame parent
	 * @param modal modal
	 * @param WindowNo window no
	 * @param value QueryValue
	 * @param browse table name
	 * @param keyColumn key column (ignored)
	 * @param multiSelection multiple selections
	 * @param whereClause where clause
	 * @param isSOTrx
	 */
	public VBrowser(FormFrame frame, boolean modal, int WindowNo, String value,
			MBrowse browse, String keyColumn, boolean multiSelection,
			String whereClause, Boolean isSOTrx) {
		super(modal, WindowNo, value, browse, keyColumn, multiSelection,
				whereClause);
		m_frame = frame;
		m_frame.setTitle(browse.getTitle());
		m_frame.getContentPane().add(statusBar, BorderLayout.SOUTH);
		windowNo = Env.createWindowNo(m_frame.getContainer());
		Env.setContext(Env.getCtx(), windowNo, "IsSOTrx", isSOTrx ? "Y" : "N");
		setProcessInfo(frame.getProcessInfo());
		copyWinContext();
		setContextWhere(whereClause);
		//	Init Smart Browse
		init();
	} // InfoGeneral

	/** Process Parameters Panel */
	private ProcessPanel processParameterPanel;
	/** StatusBar **/
	protected StatusBar statusBar = new StatusBar();
	/** Worker */
	private Worker m_worker = null;
	/** Waiting Dialog */
	private Waiting m_waiting = null;
	/**	Tool Bar 					*/
	private CButton bCancel;
	private CButton bDelete;
	private CButton bUpdate;
	private CButton bExport;
	private CButton bOk;
	private CButton bSearch;
	private CButton bSelectAll;
	private CButton bZoom;
	private CPanel buttonSearchPanel;
	private javax.swing.JScrollPane centerPanel;
	private CPanel footButtonPanel;
	private CPanel footPanel;
	private CPanel graphPanel;
	private CPanel processPanel;
	private CPanel searchTab;
	private javax.swing.JTabbedPane tabsPanel;
	private javax.swing.JToolBar toolsBar;
	private CPanel topPanel;
	/**	Table						*/
	private VBrowserTable detail;
	private CollapsiblePanel collapsibleSearch;
	private VBrowserSearch  searchPanel;
	/**	Form Frame				*/
	private FormFrame m_frame;

	
	@Override
	public void init() {
		initComponents();
		statInit();
		//m_frame.setPreferredSize(getPreferredSize());
		//
		int no = detail.getRowCount();
		setStatusLine(
				Integer.toString(no) + " "
						+ Msg.getMsg(Env.getCtx(), "SearchRows_EnterQuery"),
				false);
		setStatusDB(Integer.toString(no));
		//	
		if (isExecuteQueryByDefault()
				&& searchPanel.validateParameters() == null)
			executeQuery();
	}
	
	
	/**
	 * Static Setup - add fields to parameterPanel (GridLayout)
	 */
	private void statInit() {
		//	
		if (getAD_Process_ID() > 0) {
			//	FR [ 245 ]
			initProcessInfo();
			processParameterPanel = new ProcessPanel(getWindowNo(), getBrowseProcessInfo());
			processParameterPanel.setColumns(ProcessController.COLUMNS_2);
			processParameterPanel.setShowButtons(false);
			processParameterPanel.setShowDescription(false);
			processParameterPanel.createFieldsAndEditors();
			//	If don't have parameters then don'show collapsible panel
			if(processParameterPanel.hasParameters()) {
				//	Add collapsible panel for process pane;
				CollapsiblePanel collapsibleProcess = new CollapsiblePanel(Msg.getMsg(Env.getCtx(),("Parameter")));
				collapsibleProcess.add(processParameterPanel.getPanel());
				collapsibleProcess.validate();
				processPanel.add(collapsibleProcess);
			}
		}
	}

	/**
	 * Set Status Line
	 * 
	 * @param text
	 *            text
	 * @param error
	 *            error
	 */
	public void setStatusLine(String text, boolean error) {
		statusBar.setStatusLine(text, error);
		Thread.yield();
	} // setStatusLine

	/**
	 * Set Status DB
	 * 
	 * @param text
	 *            text
	 */
	public void setStatusDB(String text) {
		statusBar.setStatusDB(text);
	} // setStatusDB

	/**************************************************************************
	 * Execute Query
	 */
	protected void executeQuery() {
		//	FR [ 245 ]
		String errorMsg = searchPanel.validateParameters();
		if (errorMsg == null) {
			if (getAD_Window_ID() > 1)
				bZoom.setEnabled(true);

			bSelectAll.setEnabled(true);
			bExport.setEnabled(true);

			if(isDeleteable()) {
				bDelete.setEnabled(true);
			}
			//	For Updateable
			if(isUpdateable()) {
				bUpdate.setEnabled(true);
			}

			loadedOK = initBrowser();

			Env.setContext(Env.getCtx(), 0, "currWindowNo", getWindowNo());
			if (processParameterPanel != null)
				processParameterPanel.refreshContext();

			if (m_worker != null && m_worker.isAlive())
				return;
			//	
			int no = testCount();
			if (no > 0) {
				if(!ADialog.ask(getWindowNo(), m_frame.getContainer(), "InfoHighRecordCount",
						String.valueOf(no))) {
					return;
				}
			}

			m_frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			setStatusLine(Msg.getMsg(Env.getCtx(), "StartSearch"), false);
			m_worker = new Worker();
			m_worker.start();
		} else {
			ADialog.error(windowNo, m_frame.getContentPane(), 
					"FillMandatory", Msg.parseTranslation(Env.getCtx(), errorMsg));
		}
	} // executeQuery
	
	/**
	 * General Init
	 * @return true, if success
	 */
	private boolean initBrowser() {
		//	
		initBrowserTable(detail);
		//	
		if (browserFields.size() == 0) {
			ADialog.error(getWindowNo(), m_frame.getContainer(), "Error", "No Browse Fields");
			log.log(Level.SEVERE, "No Browser for view=" + getViewName());
			return false;
		}
		return true;
	} // initInfo
	
	/**
	 * Zoom
	 */
	private void cmd_zoom() {
		
		m_frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		
		MQuery query = getMQuery(detail);
		if(query != null)
			AEnv.zoom(m_frame.getContainer(), getAD_Window_ID() , query);
		
		m_frame.setCursor(Cursor.getDefaultCursor());
		bZoom.setSelected(false);
	} // cmd_zoom
	
	/**
	 * Show a list to select one or more items to delete.
	 */
	private void cmd_deleteSelection(){
		m_frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		boolean deleted = false;
		if (ADialog.ask(getWindowNo(), m_frame.getContainer(), "DeleteSelection")) {	
			int records = deleteSelection(detail);
			setStatusLine(Msg.getMsg(Env.getCtx(), "Deleted") + " " + records, false);
			deleted = true;
		}	
		m_frame.setCursor(Cursor.getDefaultCursor());
		bDelete.setSelected(false);
		if(deleted) {
			executeQuery();
		}
		
	}//cmd_deleteSelection
	
	/**
	 * Show a list to select one or more items to delete.
	 */
	private void cmd_updateSelection(){
		m_frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		if (ADialog.ask(getWindowNo(), m_frame.getContainer(), "UpdateSelection")) {	
			int records = updateSelection(detail);
			setStatusLine(Msg.getMsg(Env.getCtx(), "Updated") + " " + records, false);
		}	
		m_frame.setCursor(Cursor.getDefaultCursor());
		bUpdate.setSelected(false);
	}//cmd_updateSelection

	/**
	 * Instance tool bar
	 */
	private void setupToolBar() {
		bOk = ConfirmPanel.createOKButton(false);
		bOk.addActionListener(this);
		bSearch = ConfirmPanel.createRefreshButton(true);
		bSearch.addActionListener(this);
		bCancel = ConfirmPanel.createCancelButton(false);
		bCancel.addActionListener(this);
		bZoom = ConfirmPanel.createZoomButton(true);
		bZoom.addActionListener(this);
		bExport = ConfirmPanel.createExportButton(true);
		bExport.addActionListener(this);
		bDelete = ConfirmPanel.createDeleteButton(true);
		bDelete.addActionListener(this);
		AppsAction updateAction = new AppsAction("Save", null, Msg.getMsg(Env.getCtx(),"Update"));
		updateAction.setDelegate(this);
		bUpdate = (CButton) updateAction.getButton();
		AppsAction selectAllAction = new AppsAction("SelectAll", null, Msg.getMsg(Env.getCtx(),"SelectAll"));
		selectAllAction.setDelegate(this);
		bSelectAll = (CButton) selectAllAction.getButton();
		toolsBar = new javax.swing.JToolBar();
	}

	/**
	 * Init View componets
	 */
	private void initComponents() {

		toolsBar = new javax.swing.JToolBar();
		tabsPanel = new javax.swing.JTabbedPane();
		searchTab = new CPanel();
		topPanel = new CPanel();
		//	FR [ 344 ]
		searchPanel = new VBrowserSearch(getWindowNo(), getAD_Browse_ID(), BrowserSearch.COLUMNS_2);
		searchPanel.init();
		//	
		buttonSearchPanel = new CPanel();
		centerPanel = new javax.swing.JScrollPane();
		detail = new VBrowserTable(this);
		detail.setRowSelectionAllowed(true);
		detail.getSelectionModel().addListSelectionListener(this);
		footPanel = new CPanel();
		footButtonPanel = new CPanel(new FlowLayout(FlowLayout.CENTER));
		processPanel = new CPanel();
		graphPanel = new CPanel();

		setupToolBar();

		toolsBar.setRollover(true);
		
		bSelectAll.setText(Msg.getMsg(Env.getCtx(),"SelectAll").replaceAll("[&]",""));
		bSelectAll.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		bSelectAll.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		bSelectAll.setEnabled(false);

		toolsBar.add(bSelectAll);

		bZoom.setText(Msg.getMsg(Env.getCtx(),"Zoom").replaceAll("[&]",""));
		bZoom.setFocusable(false);
		bZoom.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		bZoom.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		bZoom.setEnabled(false);
		
		if (getAD_Window_ID() > 0) {
			toolsBar.add(bZoom);
		}

		bExport.setText(Msg.getMsg(Env.getCtx(),("Export")));
		bExport.setFocusable(false);
		bExport.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		bExport.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		bExport.setEnabled(false);
		
		toolsBar.add(bExport);

		bDelete.setText(Msg.getMsg(Env.getCtx(),"Delete").replaceAll("[&]",""));
		bDelete.setFocusable(false);
		bDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		bDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		bDelete.setEnabled(false);
		//	Add to Toolbar
		if(isDeleteable()) {
			toolsBar.add(bDelete);
		}
		
		bUpdate.setText(Msg.getMsg(Env.getCtx(),"Update").replaceAll("[&]",""));
		bUpdate.setFocusable(false);
		bUpdate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		bUpdate.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		bUpdate.setEnabled(false);
		//	Add to Toolbar
		if(isUpdateable()) {
			toolsBar.add(bUpdate);
		}
		
		m_frame.getContentPane()
				.add(toolsBar, java.awt.BorderLayout.PAGE_START);

		searchTab.setLayout(new java.awt.BorderLayout());

		topPanel.setLayout(new java.awt.BorderLayout());
		
		collapsibleSearch = new CollapsiblePanel(Msg.getMsg(Env.getCtx(),("SearchCriteria")));
		collapsibleSearch.add(searchPanel.getPanel());		
		topPanel.add(collapsibleSearch, java.awt.BorderLayout.NORTH);

		bSearch.setText(Msg.getMsg(Env.getCtx(), "StartSearch"));

		buttonSearchPanel.add(bSearch);
		collapsibleSearch.add(buttonSearchPanel);

		searchTab.add(topPanel, java.awt.BorderLayout.NORTH);
		
		centerPanel.setViewportView(detail);

		searchTab.add(centerPanel, java.awt.BorderLayout.CENTER);

		footPanel.setLayout(new java.awt.BorderLayout());
		
		footButtonPanel.add(bCancel);
		
		footButtonPanel.add(bOk);

		footPanel.add(footButtonPanel, java.awt.BorderLayout.SOUTH);

		processPanel.setLayout(new java.awt.BorderLayout());
		footPanel.add(processPanel, java.awt.BorderLayout.CENTER);

		searchTab.add(footPanel, java.awt.BorderLayout.SOUTH);

		tabsPanel.addTab(Msg.getMsg(Env.getCtx(), "Search"), searchTab);

		graphPanel.setLayout(new java.awt.BorderLayout());
		
		m_frame.getContentPane().add(tabsPanel, java.awt.BorderLayout.CENTER);
	}
	
	/**
	 * Process Action
	 */
	private void cmd_Process() {
		m_frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		m_ok = true;
		// End Worker
		if (m_worker != null) {
			// worker continues, but it does not block UI
			if (m_worker.isAlive())
				m_worker.interrupt();
			log.config("Worker alive=" + m_worker.isAlive());
		}
		m_worker = null;
		saveResultSelection(detail);
		saveSelection(detail);
		//	Is Process ok
		boolean isOk = false;
		//	Valid Process, Selected Keys and process parameters
		if (getAD_Process_ID() > 0 && getSelectedKeys() != null)
		{
			processParameterPanel.getProcessInfo().setAD_PInstance_ID(-1);
			// BR [ 249 ]
			if(processParameterPanel.validateParameters() == null) {
				//	Save Parameters
				if(processParameterPanel.saveParameters() == null) {
					//	Get Process Info
					ProcessInfo pi = processParameterPanel.getProcessInfo();
					//	Set Selected Values
					if (getFieldKey() != null && getFieldKey().get_ID() > 0) {
						MViewDefinition viewDefinition = (MViewDefinition) getFieldKey().getAD_View_Column().getAD_View_Definition();
						pi.setAliasForTableSelection(viewDefinition.getTableAlias());
						pi.setTableSelectionId(viewDefinition.getAD_Table_ID());
					}
					pi.setSelectionValues(getSelectedValues());
					//	
					setBrowseProcessInfo(pi);
					// Execute Process
					ProcessCtl worker = new ProcessCtl(this, getWindowNo() , pi , null);
					//	
					String msg = Msg.getMsg(Env.getCtx(), "Processing");
					//	For Dialog
					if(m_frame.isDialog()) {
						m_waiting = new Waiting (m_frame.getCDialog(), msg, false, getBrowseProcessInfo().getEstSeconds());
					} else {
						m_waiting = new Waiting (m_frame.getCFrame(), msg, false, getBrowseProcessInfo().getEstSeconds());
					}
					worker.run(); // complete tasks in unlockUI /
					m_waiting.doNotWait();
					setStatusLine(pi.getSummary(), pi.isError());
					//	For Valid Ok
					isOk = !pi.isError();
				}
			}
		}
		m_frame.setCursor(Cursor.getDefaultCursor());
		//	For when is ok the process
		if(isOk) {
			//	Close
			if(getParentWindowNo() > 0) {
				dispose();
				return;
			}
			//	Else Reset
			loadedOK = initBrowser();
			collapsibleSearch.setCollapsed(false);
		}
	}

	/**
	 * Cancel Action
	 */
	private void cmd_Cancel() {
		this.dispose();
	}

	/**
	 *  Dispose
	 */
	public void dispose() {
		//	BR [ 394 ]
		Env.clearWinContext(getWindowNo());
		searchPanel.dispose();
		m_frame.dispose();
	}   //  dis

	/**
	 * Action Search
	 */
	private void cmd_Search() {
		bZoom.setEnabled(true);
		bSelectAll.setEnabled(true);
		bExport.setEnabled(true);
		bDelete.setEnabled(true);
		bUpdate.setEnabled(true);
		Env.setContext(Env.getCtx(), 0, "currWindowNo", getWindowNo());

		if (getAD_Process_ID() > 0)
			processParameterPanel.refreshContext();

		executeQuery();
	}

	/**
	 * Action for export
	 */
	private void cmd_Export() {
		m_frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		try 
		{	File file = exportXLS(detail);
			Env.startBrowser(file.toURI().toString());
		} catch (Exception e) {
			throw new AdempiereException("Failed to render report", e);
		}
		m_frame.setCursor(Cursor.getDefaultCursor());
		bExport.setSelected(false);
	}

	/**
	 * Get form
	 * @return
	 */
	public CFrame getForm() {
		return m_frame.getCFrame();
	}
	
	/**
	 * Worker
	 */
	class Worker extends Thread {
		private PreparedStatement m_pstmt = null;
		private ResultSet m_rs = null;
		private String dataSql = null;

		/**
		 * Do Work (load data)
		 */
		public void run() {
			//	
			long start = System.currentTimeMillis();
			int no = 0;
			dataSql = getSQL();
			//	Row
			int row = 0;
			//	Delete Row
			detail.setRowCount(row);
			try {
				m_pstmt = getStatement(dataSql);
				log.fine("Start query - "
						+ (System.currentTimeMillis() - start) + "ms");
				m_rs = m_pstmt.executeQuery();
				log.fine("End query - " + (System.currentTimeMillis() - start)
						+ "ms");
				//	Load Table
				row = detail.loadTable(m_rs);
			} catch (SQLException e) {
				log.log(Level.SEVERE, dataSql, e);
			}
			close();
			//
			//no = detail.getRowCount();
			log.fine("#" + no + " - " + (System.currentTimeMillis() - start)
					+ "ms");
			detail.autoSize();
			//
			m_frame.setCursor(Cursor.getDefaultCursor());
			setStatusLine(
					Integer.toString(row) + " "
							+ Msg.getMsg(Env.getCtx(), "SearchRows_EnterQuery"),
					false);
			setStatusDB(Integer.toString(no));
			if (no == 0) {
				log.fine(dataSql);
			} else {
				detail.getSelectionModel().setSelectionInterval(0, 0);
				detail.requestFocus();
			}
			
			isAllSelected = isSelectedByDefault();
			if(isAllSelected) {
				selectedRows(detail);
			}
			//	Set Collapsed
			if(row > 0)
				collapsibleSearch.setCollapsed(isCollapsibleByDefault());
		} // run

		/**
		 * Close ResultSet and Statement
		 */
		private void close() {
			DB.close(m_rs, m_pstmt);
			m_rs = null;
			m_pstmt = null;
		}
	} // Worker

	public static void main(String args[]) {
		Splash.getSplash();
		// Adempiere.startup(true); // needs to be here for UI
		// Adempiere.startupEnvironment(false);
		Adempiere.startup(true);
		Ini.setProperty(Ini.P_UID, "SuperUser");
		Ini.setProperty(Ini.P_PWD, "System");
		Ini.setProperty(Ini.P_ROLE, "GardenWorld Admin");
		Ini.setProperty(Ini.P_CLIENT, "GardenWorld");
		Ini.setProperty(Ini.P_ORG, "HQ");
		Ini.setProperty(Ini.P_WAREHOUSE, "HQ Warehouse");
		Ini.setProperty(Ini.P_LANGUAGE, "English");
		// Ini.setProperty(Ini.P_PRINTER,"MyPrinter");
		Login login = new Login(Env.getCtx());
		login.batchLogin();

		Properties m_ctx = Env.getCtx();
		FormFrame frame = new FormFrame(0);
		boolean modal = true;
		int WindowNo = 0;
		String value = "";
		String keyColumn = "";
		boolean multiSelection = true;
		String whereClause = "";
		VBrowser browser = new VBrowser(frame, modal, WindowNo, value, ASPUtil.getInstance().getBrowse(50025),
				keyColumn, multiSelection, whereClause, true);
		// browser.setPreferredSize(getPreferredSize ());
		browser.getFrame().setVisible(true);
		browser.getFrame().pack();
	}

	public CFrame getFrame() {
		return m_frame.getCFrame();
	}

	/**
	 * Get Preferred Size
	 * 
	 * @return size
	 */
	public Dimension getPreferredSize() {
		Dimension size = m_frame.getPreferredSize();
		if (size.width > WINDOW_WIDTH)
			size.width = WINDOW_WIDTH - 30;
		return size;
	} // getPreferredSize

	/**
	 * Action Performed
	 */
	public void actionPerformed(ActionEvent actionEvent) {
		String action = actionEvent.getActionCommand();
		if (action == null || action.length() == 0)
			return;
		log.info( "VBrowser - actionPerformed: " + action);
		if (actionEvent.getSource().equals(bSelectAll)) {
			selectedRows(detail);
		} else if(actionEvent.getSource().equals(bSearch)) {
			cmd_Search();
		} else if(actionEvent.getSource().equals(bCancel)) {
			cmd_Cancel();
		} else if(actionEvent.getSource().equals(bOk)) {
			cmd_Process();
		} else if(actionEvent.getSource().equals(bExport)) {
			cmd_Export();
		} else if(actionEvent.getSource().equals(bDelete)) {
			cmd_deleteSelection();
		} else if(actionEvent.getSource().equals(bUpdate)) {
			cmd_updateSelection();
		} else if(actionEvent.getSource().equals(bZoom)) {
			cmd_zoom();
		}		
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		//  no rows
		if (detail.getRowCount() == 0)
			return;
		
		int rowTable = detail.getSelectedRow();
		int rowCurrent = detail.getData().getCurrentRow();
		log.config("(" + detail.toString() + ") Row in Table=" + rowTable + ", in Model=" + rowCurrent);
		//  nothing selected
		if (rowTable == -1) {
			if (rowCurrent >= 0) {
				detail.setRowSelectionInterval(rowCurrent, rowCurrent); //  causes this method to be called again
				return;
			}
		} else {
			if (rowTable != rowCurrent) {
				//make sure table selection is consistent with model
				detail.getData().setCurrentRow(rowTable);
			}
		}
	}
	
	@Override
	public void executeASync(ProcessInfo pi) {
		
	}
	
	@Override
	public boolean isUILocked() {
		return false;
	}

	@Override
	public void lockUI(ProcessInfo pi) {
		
	}

	@Override
	public void unlockUI(ProcessInfo pi) {
		processParameterPanel.setProcessInfo(pi);
		processParameterPanel.openResult();
	}
	
	@Override
	public LinkedHashMap<String, GridField> getPanelParameters() {
		LinkedHashMap<String, GridField> m_List = new LinkedHashMap<String, GridField>();
		for (Entry<String, Object> entry : searchPanel.getParameters().entrySet()) {
			VEditor editor = (VEditor) entry.getValue();
			//	BR [ 251 ]
			if(!((Component)editor).isVisible()
					|| editor.getField().isInfoOnly())
				continue;
			//	
			GridField field = editor.getField();
			m_List.put(entry.getKey(), field);
		}
		//	Default Return
		return m_List;
	}
}
