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
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.logging.Level;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.MBrowse;
import org.adempiere.model.MBrowseField;
import org.compiere.Adempiere;
import org.compiere.apps.ADialog;
import org.compiere.apps.AEnv;
import org.compiere.apps.AGlassPane;
import org.compiere.apps.ALayout;
import org.compiere.apps.AppsAction;
import org.compiere.apps.ConfirmPanel;
import org.compiere.apps.ProcessCtl;
import org.compiere.apps.ProcessParameterPanel;
import org.compiere.apps.StatusBar;
import org.compiere.apps.Waiting;
import org.compiere.grid.ed.VEditor;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.GridFieldVO;
import org.eevolution.grid.BrowseTable;
import org.compiere.model.GridField;
import org.compiere.model.MPInstance;
import org.compiere.model.MProcess;
import org.compiere.model.MQuery;
import org.compiere.model.MRole;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoUtil;
import org.compiere.swing.CFrame;
import org.compiere.swing.CollapsiblePanel;
import org.compiere.util.ASyncProcess;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Login;
import org.compiere.util.Msg;
import org.compiere.util.Splash;

/**
 * UI Browser
 * 
 * @author victor.perez@e-evolution.com, victor.perez@e-evolution.com
 * <li>FR [ 3426137 ] Smart Browser
 * https://sourceforge.net/tracker/?func=detail&aid=3426137&group_id=176962&atid=879335
 * @author carlosaparada@gmail.com Carlos Parada, ERP Consultores y asociados
 */
public class VBrowser extends Browser implements IBrowser, ActionListener,
		VetoableChangeListener, ChangeListener, ListSelectionListener,
		TableModelListener, ASyncProcess {
	CFrame m_frame = new CFrame();
	/**
	 * get Browse
	 * @param browse_ID
	 */
	public static CFrame openBrowse(int browse_ID) {
		MBrowse browse = new MBrowse(Env.getCtx(), browse_ID , null);
		boolean modal = true;
		int WindowNo = 0;
		String value = "";
		String keyColumn = "";
		boolean multiSelection = true;
		String whereClause = null;
		CFrame ff = new CFrame();
		return new VBrowser(ff, modal , WindowNo, value, browse, keyColumn,multiSelection, whereClause)
		.getFrame();
		
	}

	/**
	 * Detail Protected Constructor.
	 * 
	 * @param frame
	 *            parent
	 * @param modal
	 *            modal
	 * @param WindowNo
	 *            window no
	 * @param value
	 *            QueryValue
	 * @param browse
	 *            table name
	 * @param keyColumn
	 *            key column (ignored)
	 * @param multiSelection
	 *            multiple selections
	 * @param whereClause
	 *            where clause
	 */
	public VBrowser(CFrame frame, boolean modal, int WindowNo, String value,
			MBrowse browse, String keyColumn, boolean multiSelection,
			String whereClause) {
		super(modal, WindowNo, value, browse, keyColumn, multiSelection,
				whereClause);
		m_frame = frame;
		m_frame.setTitle(browse.getTitle());
		m_frame.getContentPane().add(statusBar, BorderLayout.SOUTH);
		windowNo = Env.createWindowNo(m_frame);
		setContextWhere(browse, whereClause);		
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
		
		if (isExecuteQueryByDefault())
			executeQuery();
		
	} // InfoGeneral

	/** Process Parameters Panel */
	private ProcessParameterPanel parameterPanel;
	/** StatusBar **/
	protected StatusBar statusBar = new StatusBar();
	/** Worker */
	private Worker m_worker = null;
	/** Waiting Dialog */
	private Waiting m_waiting = null;

	/**
	 * Static Setup - add fields to parameterPanel (GridLayout)
	 */

	private void statInit() {
		searchPanel.setLayout(new ALayout());
		int cols = 0;
		int col = 2;
		int row = 0;
		for (MBrowseField field : m_Browse.getCriteriaFields()) {
			String title = field.getName();
			String name = field.getAD_View_Column().getColumnName();
			searchPanel.addField(field, row, cols, name, title);
			cols = cols + col;

			if (field.isRange())
				cols = cols + col;

			if (cols >= 4) {
				cols = 0;
				row++;
			}
		}
		
		searchPanel.dynamicDisplay();
		
		if (m_Browse.getAD_Process_ID() > 0) {
			m_process = MProcess.get(Env.getCtx(), m_Browse.getAD_Process_ID());
			ProcessInfo pi = new ProcessInfo(m_process.getName(),
					m_Browse.getAD_Process_ID());
			pi.setAD_User_ID(Env.getAD_User_ID(Env.getCtx()));
			pi.setAD_Client_ID(Env.getAD_Client_ID(Env.getCtx()));
			pi.setWindowNo(getWindowNo());
			setBrowseProcessInfo(pi);
			parameterPanel = new ProcessParameterPanel(pi.getWindowNo() , pi);
			parameterPanel.setMode(ProcessParameterPanel.MODE_HORIZONTAL);
			parameterPanel.init();
			processPanel.add(parameterPanel, BorderLayout.CENTER);
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
			ADialog.error(getWindowNo(), m_frame, "Error", "No Browse Fields");
			log.log(Level.SEVERE, "No Browser for view=" + m_View.getName());
			return null;
		}
		log.finest("Browse Fields #" + list.size());

		detail= new BrowseTable(this);
		centerPanel.setViewportView(detail);

		return list;
	} // initInfoTable

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

		if (evaluateMandatoryFilter()) {
			if (getAD_Window_ID() > 1)
				bZoom.setEnabled(true);

			bSelectAll.setEnabled(true);
			bExport.setEnabled(true);

			if (isDeleteable())
				bDelete.setEnabled(true);

			collapsibleSearch.setCollapsed(isCollapsibleByDefault());

			p_loadedOK = initBrowser();

			Env.setContext(Env.getCtx(), 0, "currWindowNo", getWindowNo());
			if (parameterPanel != null)
				parameterPanel.refreshContext();

			if (m_worker != null && m_worker.isAlive())
				return;
			//
			if (!testCount())
				return;

			m_frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			setStatusLine(Msg.getMsg(Env.getCtx(), "StartSearch"), false);
			m_worker = new Worker();
			m_worker.start();
		}
	} // executeQuery

	/**
	 * Zoom
	 */
	private void cmd_zoom() {
		
		m_frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		
		MQuery query = getMQuery();
		if(query != null)
			AEnv.zoom(m_frame , getAD_Window_ID() , query);
		
		m_frame.setCursor(Cursor.getDefaultCursor());
		bZoom.setSelected(false);
	} // cmd_zoom
	
	/**
	 * Show a list to select one or more items to delete.
	 */
	private void cmd_deleteSelection(){
		m_frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		if (ADialog.ask(getWindowNo(), m_frame, "DeleteSelection"))
		{	
			int records = deleteSelection();
			setStatusLine(Msg.getMsg(Env.getCtx(), "Deleted") + records, false);
		}	
		m_frame.setCursor(Cursor.getDefaultCursor());
		bDelete.setSelected(false);
		executeQuery();;
		
	}//cmd_deleteSelection

	/**************************************************************************
	 * Prepare Table, Construct SQL (m_m_sqlMain, m_sqlAdd) and size Window
	 * @param fields list
	 * @param from from clause
	 * @param staticWhere where clause
	 * @param orderBy order by clause
	 */
	protected void prepareTable(List<MBrowseField> fields,String from,
								 String staticWhere, String orderBy) {
		browserFields = fields;
		StringBuffer sql = new StringBuffer("SELECT DISTINCT ");
		sql.append(detail.prepareTable(browserFields, p_multiSelection));
		// Table Selection (Invoked before setting column class so that row
		// selection is enabled)
		detail.setRowSelectionAllowed(true);
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

	/**
	 * Test Row Count
	 * 
	 * @return true if display
	 */
	private boolean testCount() {
		int no = -1;

		no = getCount();
		// log.fine("#" + no + " - " + (System.currentTimeMillis()-start) +
		// "ms");
		MRole role = MRole.getDefault();
		if (role.isQueryMax(no))
			return ADialog.ask(getWindowNo(), m_frame, "InfoHighRecordCount",
					String.valueOf(no));
		return true;
	} // testCount

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
			detail.removeAll();
			detail = null;
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
		detail.removeAll();
		detail = null;
	} // saveSelection

	/**
	 * save result values
	 */
	protected void saveResultSelection() {
		if (m_keyColumnIndex == -1) {
			return;
		}

		if (p_multiSelection) {
			int rows = detail.getRowCount();
			BrowserRows browserRows = detail.getData();
			m_values = new LinkedHashMap<Integer,LinkedHashMap<String,Object>>();
			for (int row = 0; row < rows; row++) {
				//Find the IDColumn Key
				Object data = detail.getModel().getValueAt(row,
						m_keyColumnIndex);
				if (data instanceof IDColumn) {
					IDColumn dataColumn = (IDColumn) data;
					if (dataColumn.isSelected()) {
						LinkedHashMap<String, Object> values = new LinkedHashMap<String, Object>();
						for(int col = 0 ; col < browserRows.getColumnCount(); col++)
						{
							MBrowseField field = browserRows.getBrowserField(col);
							if (!field.isReadOnly() || field.isIdentifier() )
							{
								GridField gridField = detail.getData().getValue(row, col);
								Object value = gridField.getValue();
								values.put(field.getAD_View_Column().getColumnName(), value);
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
		m_ok = ok;

		// End Worker
		if (m_worker != null) {
			// worker continues, but it does not block UI
			if (m_worker.isAlive())
				m_worker.interrupt();
			log.config("Worker alive=" + m_worker.isAlive());
		}
		m_worker = null;
		
		saveResultSelection();
		saveSelection();

		m_frame.removeAll();
		m_frame.dispose();
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
		worker.start(); // complete tasks in unlockUI /
        Env.clearWinContext(getWindowNo());
	} // dispose

	private void setupToolBar() {
		AppsAction a = new AppsAction(ConfirmPanel.A_OK, null, ConfirmPanel.A_OK);
		bOk = new javax.swing.JButton(a);
		a = new AppsAction(ConfirmPanel.A_CANCEL, null, ConfirmPanel.A_CANCEL);
		bCancel = new javax.swing.JButton(a);
		toolsBar = new javax.swing.JToolBar();
		a = new AppsAction(ConfirmPanel.A_PRINT, null, ConfirmPanel.A_PRINT);
		bPrint = new javax.swing.JButton(a);
		a = new AppsAction(ConfirmPanel.A_ZOOM, null, ConfirmPanel.A_ZOOM);
		bZoom = new javax.swing.JToggleButton(a);
		a = new AppsAction(ConfirmPanel.A_EXPORT, null, ConfirmPanel.A_EXPORT);
		bExport = new javax.swing.JButton(a);
		a = new AppsAction(ConfirmPanel.A_DELETE, null, ConfirmPanel.A_DELETE);
		bDelete = new javax.swing.JButton(a);
		a = new AppsAction("Find", null, "Find");
		bFind = new javax.swing.JButton(a);
		a = new AppsAction("SelectAll", null, Msg.getMsg(Env.getCtx(),"SelectAll"));
		bSelectAll = new javax.swing.JButton(a);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		toolsBar = new javax.swing.JToolBar();
		//bPrint = new javax.swing.JButton();
		bZoom = new javax.swing.JToggleButton();
		bExport = new javax.swing.JButton();
		bDelete = new javax.swing.JButton();
		//bFind = new javax.swing.JButton();
		tabsPanel = new javax.swing.JTabbedPane();
		searchTab = new javax.swing.JPanel();
		topPanel = new javax.swing.JPanel();
		searchPanel = new VBrowserSearch(getWindowNo());
		buttonSearchPanel = new javax.swing.JPanel();
		bSearch = new javax.swing.JButton();
		centerPanel = new javax.swing.JScrollPane();
		//detail = new MiniTable();
		detail = new BrowseTable(this);
		footPanel = new javax.swing.JPanel();
		footButtonPanel = new javax.swing.JPanel();
		bCancel = new javax.swing.JButton();
		bOk = new javax.swing.JButton();
		processPanel = new javax.swing.JPanel();
		graphPanel = new javax.swing.JPanel();

		setupToolBar();

		toolsBar.setRollover(true);
		
		bSelectAll.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		bSelectAll.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		bSelectAll.setEnabled(false);

		bSelectAll.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				selectedRows();
			}
		});

		toolsBar.add(bSelectAll);
		
		//TODO: victor.perez@e-evolution.com, Implement Print functionality
		/*bPrint.setText("Print");
		bPrint.setFocusable(false);
		bPrint.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		bPrint.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		bPrint.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bPrintActionPerformed(evt);
			}
		});
		toolsBar.add(bPrint);*/

		bZoom.setText(Msg.getMsg(Env.getCtx(),"Zoom").replaceAll("[&]",""));
		bZoom.setFocusable(false);
		bZoom.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		bZoom.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		bZoom.setEnabled(false);
		bZoom.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bZoomActionPerformed(evt);
			}
		});
		if (AD_Window_ID > 0)
			toolsBar.add(bZoom);

		bExport.setText(Msg.getMsg(Env.getCtx(),("Export")));
		bExport.setFocusable(false);
		bExport.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		bExport.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		bExport.setEnabled(false);
		bExport.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bExportActionPerformed(evt);
			}
		});
		toolsBar.add(bExport);

		bDelete.setText(Msg.getMsg(Env.getCtx(),"Delete").replaceAll("[&]",""));
		bDelete.setFocusable(false);
		bDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		bDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		bDelete.setEnabled(false);
		bDelete.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bDeleteActionPerformed(evt);
			}
		});
		
		if(isDeleteable())
			toolsBar.add(bDelete);

		//TODO: victor.perez@e-evolution.com, Implement Print functionality
		/*
		bFind.setText("Find");
		bFind.setFocusable(false);
		bFind.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		bFind.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		bFind.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bFindActionPerformed(evt);
			}
		});
		toolsBar.add(bFind);*/



		m_frame.getContentPane()
				.add(toolsBar, java.awt.BorderLayout.PAGE_START);

		searchTab.setLayout(new java.awt.BorderLayout());

		topPanel.setLayout(new java.awt.BorderLayout());

		searchPanel.setLayout(new java.awt.GridBagLayout());
		
		collapsibleSearch = new CollapsiblePanel(Msg.getMsg(Env.getCtx(),("SearchCriteria")));
		collapsibleSearch.add(searchPanel);
		topPanel.add(collapsibleSearch, java.awt.BorderLayout.NORTH);

		bSearch.setText(Msg.getMsg(Env.getCtx(), "StartSearch"));
		bSearch.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bSearchActionPerformed(evt);
			}
		});

		buttonSearchPanel.add(bSearch);
		collapsibleSearch.add(buttonSearchPanel);

		searchTab.add(topPanel, java.awt.BorderLayout.NORTH);

		/*
		 * detail.setModel(new javax.swing.table.DefaultTableModel( new Object
		 * [][] { {}, {}, {}, {} }, new String [] {
		 * 
		 * } ));
		 */
		centerPanel.setViewportView(detail);

		searchTab.add(centerPanel, java.awt.BorderLayout.CENTER);

		footPanel.setLayout(new java.awt.BorderLayout());

		bCancel.setText(Msg.getMsg(Env.getCtx(), "Cancel").replaceAll("[&]",""));
		bCancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bCancelActionPerformed(evt);
			}
		});
		footButtonPanel.add(bCancel);

		bOk.setText(Msg.getMsg(Env.getCtx(), "Ok").replaceAll("[&]",""));
		bOk.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bOkActionPerformed(evt);
			}
		});
		footButtonPanel.add(bOk);

		footPanel.add(footButtonPanel, java.awt.BorderLayout.SOUTH);

		processPanel.setLayout(new java.awt.BorderLayout());
		footPanel.add(processPanel, java.awt.BorderLayout.CENTER);

		searchTab.add(footPanel, java.awt.BorderLayout.SOUTH);

		tabsPanel.addTab(Msg.getMsg(Env.getCtx(), "Search"), searchTab);

		graphPanel.setLayout(new java.awt.BorderLayout());
		//TODO: Create Graph implementation
		//tabsPanel.addTab("Graph", graphPanel);

		m_frame.getContentPane().add(tabsPanel, java.awt.BorderLayout.CENTER);
	}// </editor-fold>//GEN-END:initComponents

	private void selectedRows()
	{
		int topIndex = detail.isShowTotals() ? 2 : 1;
		int rows = detail.getRowCount();
		TableModel model =  detail.getModel();

		if (isAllSelected) {
			for (int row = 0; row <= rows - topIndex; row++) {
				Object data = model.getValueAt(row, m_keyColumnIndex);
				if (data instanceof IDColumn) {
					IDColumn dataColumn = (IDColumn) data;
					dataColumn.setSelected(true);
					model.setValueAt(dataColumn, row,m_keyColumnIndex);
				}
			}

		} else {
			for (int row = 0; row <= rows - topIndex; row++) {
				Object data = model.getValueAt(row, m_keyColumnIndex);
				if (data instanceof IDColumn) {
					IDColumn dataColumn = (IDColumn) data;
					dataColumn.setSelected(false);
					model.setValueAt(dataColumn, row, m_keyColumnIndex);
				}
			}
			detail.clearSelection();
		}
		isAllSelected = !isAllSelected;
	}


	private void bZoomActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_bZoomActionPerformed
		// TODO add your handling code here:
		cmd_zoom();
	}// GEN-LAST:event_bZoomActionPerformed

	private void bOkActionPerformed(java.awt.event.ActionEvent evt) {
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
			// call process 
			parameterPanel.saveParameters();
			ProcessInfoUtil.setParameterFromDB(pi);
			setBrowseProcessInfo(pi);
			//Save Values Browse Field Update
			createT_Selection_Browse(instance.getAD_PInstance_ID());
			// Execute Process
			ProcessCtl worker = new ProcessCtl(this, pi.getWindowNo() , pi , null);
			m_waiting = new Waiting (m_frame, Msg.getMsg(Env.getCtx(), "Processing"), false, getBrowseProcessInfo().getEstSeconds());
			worker.run(); // complete tasks in unlockUI /
			m_waiting.doNotWait();
			setStatusLine(pi.getSummary(), pi.isError());
			
		}
		m_frame.setCursor(Cursor.getDefaultCursor());
		p_loadedOK = initBrowser();
		collapsibleSearch.setCollapsed(false);
	}

	private void bCancelActionPerformed(java.awt.event.ActionEvent evt) {
		this.dispose();
	}

	/**
	 *  Dispose
	 */
	public void dispose()
	{
		searchPanel.dispose();
		m_frame.removeAll();
		m_frame.dispose();
	}   //  dis

	private void bSearchActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_bSearchActionPerformed
		bZoom.setEnabled(true);
		bSelectAll.setEnabled(true);
		bExport.setEnabled(true);
		bDelete.setEnabled(true);
		collapsibleSearch.setCollapsed(!isCollapsibleByDefault());
		p_loadedOK = initBrowser();
		Env.setContext(Env.getCtx(), 0, "currWindowNo", getWindowNo());

		if (m_Browse.getAD_Process_ID() > 0)
			parameterPanel.refreshContext();

		executeQuery();
	}// GEN-LAST:event_bSearchActionPerformed

	private void bFindActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void bExportActionPerformed(java.awt.event.ActionEvent evt) {
		m_frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		try 
		{	File file = exportXLS();
			Env.startBrowser(file.toURI().toString());
		} catch (Exception e) {
			throw new AdempiereException("Failed to render report", e);
		}
		m_frame.setCursor(Cursor.getDefaultCursor());
		bExport.setSelected(false);
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

	private void bDeleteActionPerformed(java.awt.event.ActionEvent evt) {
		cmd_deleteSelection();
	}

	private void bPrintActionPerformed(java.awt.event.ActionEvent evt) {
	}

	public CFrame getForm() {
		return m_frame;
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton bCancel;
	private javax.swing.JButton bDelete;
	private javax.swing.JButton bExport;
	private javax.swing.JButton bFind;
	private javax.swing.JButton bOk;
	private javax.swing.JButton bPrint;
	private javax.swing.JButton bSearch;
	private javax.swing.JButton bSelectAll;
	private javax.swing.JToggleButton bZoom;
	private javax.swing.JPanel buttonSearchPanel;
	private javax.swing.JScrollPane centerPanel;
	private BrowseTable detail;
	private javax.swing.JPanel footButtonPanel;
	private javax.swing.JPanel footPanel;
	private javax.swing.JPanel graphPanel;
	private javax.swing.JPanel processPanel;
	private javax.swing.JPanel searchTab;
	private javax.swing.JTabbedPane tabsPanel;
	private javax.swing.JToolBar toolsBar;
	private javax.swing.JPanel topPanel;
	/** The GlassPane           	*/
	private AGlassPane  m_glassPane = new AGlassPane();
	private CollapsiblePanel collapsibleSearch;
	private VBrowserSearch  searchPanel;

	// End of variables declaration//GEN-END:variables

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
			long start = System.currentTimeMillis();
			int no = 0;
			dataSql = getSQL();
			// Clear Table
			detail.setRowCount(0);
			try {
				m_pstmt = getStatement(dataSql);
				log.fine("Start query - "
						+ (System.currentTimeMillis() - start) + "ms");
				m_rs = m_pstmt.executeQuery();
				log.fine("End query - " + (System.currentTimeMillis() - start)
						+ "ms");
				while (m_rs.next()) {
					if (this.isInterrupted()) {
						log.finer("Interrupted");
						close();
						return;
					}
					no++;
					int row = detail.getRowCount();
					detail.setRowCount(row + 1);
					int colOffset = 1; // columns start with 1
					int columnDisplayIndex =0;
					int column = 0;
					for (MBrowseField field : browserFields) {
						Object value = null;
						if (field.isKey() && !field.getAD_View_Column().getColumnSQL().equals("'Row' AS \"Row\""))
							value = new IDColumn(m_rs.getInt(column + colOffset));
						else if (field.isKey() && !field.getAD_View_Column().getColumnSQL().equals("'Row' AS \"Row\""))
							value  = new IDColumn(no);
						else if (DisplayType.TableDir == field.getAD_Reference_ID()
							  || DisplayType.Table == field.getAD_Reference_ID()
							  || DisplayType.Integer == field.getAD_Reference_ID()
							  || DisplayType.PAttribute == field.getAD_Reference_ID()
							  || DisplayType.Account == field.getAD_Reference_ID()) {
							Integer id = m_rs.getInt(column + colOffset);
							value = id != 0 ? id : null;
						}
						else if (DisplayType.isNumeric(field.getAD_Reference_ID()))
							value = m_rs.getBigDecimal(column + colOffset);
						else if (DisplayType.isDate(field.getAD_Reference_ID()))
							value = m_rs.getTimestamp(column + colOffset);
						else if (DisplayType.YesNo == field.getAD_Reference_ID()){
							value = m_rs.getString(column + colOffset);
							if (value != null)
								value= value.equals("Y");
						}
						else
							value = m_rs.getObject(column + colOffset);

						GridField gridField = MBrowseField.createGridFieldVO(field , getWindowNo());
						gridField.setValue(value, true);
						detail.setValueAt(row, columnDisplayIndex, gridField);
						if (field.isDisplayed())
							columnDisplayIndex++;

						column ++;
					}
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, dataSql, e);
			}
			close();
			//
			//no = detail.getRowCount();
			log.fine("#" + no + " - " + (System.currentTimeMillis() - start)
					+ "ms");
			if (detail.isShowTotals())
				detail.addTotals();
			detail.autoSize();
			//
			m_frame.setCursor(Cursor.getDefaultCursor());
			setStatusLine(
					Integer.toString(no) + " "
							+ Msg.getMsg(Env.getCtx(), "SearchRows_EnterQuery"),
					false);
			setStatusDB(Integer.toString(no));
			if (no == 0)
				log.fine(dataSql);
			else {
				detail.getSelectionModel().setSelectionInterval(0, 0);
				detail.requestFocus();
			}
			isAllSelected = isSelectedByDefault();
			selectedRows();
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
		MBrowse browse = new MBrowse(m_ctx, 50025, null);
		CFrame frame = new CFrame();
		boolean modal = true;
		int WindowNo = 0;
		String value = "";
		String keyColumn = "";
		boolean multiSelection = true;
		String whereClause = "";
		VBrowser browser = new VBrowser(frame, modal, WindowNo, value, browse,
				keyColumn, multiSelection, whereClause);
		// browser.setPreferredSize(getPreferredSize ());
		browser.getFrame().setVisible(true);
		browser.getFrame().pack();
	}

	public CFrame getFrame() {
		return m_frame;
	}

	/*
	 * public int getAD_Browse_ID() { return m_Browse.getAD_Browse_ID(); }
	 */

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

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public void vetoableChange(PropertyChangeEvent evt)
			throws PropertyVetoException {
		// TODO Auto-generated method stub

	}

	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub

	}

	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub

	}

	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub

	}

	public void executeASync(ProcessInfo pi) {
		// TODO Auto-generated method stub

	}

	public boolean isUILocked() {
		// TODO Auto-generated method stub
		return false;
	}

	public void lockUI(ProcessInfo pi) {
		// TODO Auto-generated method stub

	}

	public void unlockUI(ProcessInfo pi) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getParameterValue(Object key) {
			VEditor editor = (VEditor) searchPanel.getParameters().get(key);
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

		for (Entry<Object, Object> entry : searchPanel.getParameters().entrySet()) {
			VEditor editor = (VEditor) entry.getValue();
			GridFieldVO field = editor.getField().getVO();
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
				} 
				else if (editor.getValue() != null
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
		
		for (Entry<Object, Object> entry : searchPanel.getParameters().entrySet()) {
			VEditor editor = (VEditor) entry.getValue();
			GridFieldVO field = editor.getField().getVO();
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
	 * @author carlosaparada@gmail.com Carlos Parada, ERP Consultores y asociados
	 * @return
	 */
	public boolean evaluateMandatoryFilter()
	{
		Object value_from=null;
		boolean onRange = false;
		boolean result =true;

		for (Entry<Object, Object> entry : searchPanel.getParameters().entrySet()) {
			VEditor editor = (VEditor) entry.getValue();
			GridFieldVO field = editor.getField().getVO();
			if (!onRange) {

				if ((editor.getValue() == null
						|| (editor.getValue() != null && editor.getValue().toString().isEmpty()))
						&& !field.isRange
						&& editor.isMandatory()) {
					ADialog.error(windowNo, getForm().getContentPane(), "FillMandatory", Msg.translate(Env.getCtx(),field.ColumnName));
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
					ADialog.error(windowNo, getForm().getContentPane(), "FillMandatory", Msg.translate(Env.getCtx(),field.ColumnName));
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
