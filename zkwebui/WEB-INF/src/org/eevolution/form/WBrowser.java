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
import org.adempiere.webui.component.WListbox;
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
import org.compiere.apps.search.Info_Column;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.GridFieldVO;
import org.compiere.model.MPInstance;
import org.compiere.model.MProcess;
import org.compiere.model.MQuery;
import org.compiere.model.MRole;
import org.compiere.process.ProcessInfo;
import org.compiere.util.ASyncProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
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
import org.zkoss.zul.Row;
import org.zkoss.zul.Separator;
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
		initComponents();
		statInit();
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
			setBrowseProcessInfo(pi);
			parameterPanel = new ProcessParameterPanel(p_WindowNo, getBrowseProcessInfo() , "100%");
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

	private boolean initBrowser() {
		if (!initBrowserTable())
			return false;

		// prepare table	
		StringBuilder where = new StringBuilder("");
		if (p_whereClause.length() > 0) {
			where.append(p_whereClause);
		}

		prepareTable(m_generalLayout, m_View.getFromClause(), where.toString(),
				"2");
		return true;
	}

	private boolean initBrowserTable() {
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

	public void setStatusLine(String text, boolean error) {
		statusBar.setStatusLine(text, error);
	}

	public void setStatusDB(String text) {
		statusBar.setStatusDB(text);
	}

	protected void executeQuery() {
		if (!testCount())
			return;

		setStatusLine(Msg.getMsg(Env.getCtx(), "StartSearch"), false);
		work();
	}

	private void cmd_zoom() {
		showBusyDialog();
		
		MQuery query = getMQuery();
		if(query != null)
			AEnv.zoom(query);
		
		hideBusyDialog();
	}
	
	private void cmd_deleteSelection() {
		if (FDialog.ask(p_WindowNo, m_frame, "DeleteSelection"))
		{	
			int records = deleteSelection();
			setStatusLine(Msg.getMsg(Env.getCtx(), "Deleted") + records, false);
		}	
	}

	protected void prepareTable(Info_Column[] layout, String from,
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
			m_values = new LinkedHashMap<Integer,LinkedHashMap<String,Object>>();
			for (int row = 0; row < rows; row++) {
				//Find the IDColumn Key
				Object data = detail.getModel().getValueAt(row,
						m_keyColumnIndex);
				if (data instanceof IDColumn) {
					IDColumn dataColumn = (IDColumn) data;
					if (dataColumn.isSelected()) {
						LinkedHashMap<String, Object> values = new LinkedHashMap<String, Object>();
						int col = 0;
						for (Info_Column column : m_generalLayout)
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
						}
						if(values.size() > 0)
						{
							m_values.put(dataColumn.getRecord_ID(), values);
						}
					}
				}
			}
		}
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
		setBrowseProcessInfo(pi);
		//Save Values Browse Field Update
		createT_Selection_Browse(instance.getAD_PInstance_ID());
		parameterPanel.saveParameters();
		// Execute Process
		ProcessCtl worker = new ProcessCtl(this, 0, getBrowseProcessInfo(), null);
		worker.start();
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
		searchGrid = new WBrowserSearch(p_WindowNo);
		bSearch = new Button();
		detail = new WListbox();
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
    		int topIndex = 1 ; //detail.getShowTotals() ? 2 : 1;
    		int rows = detail.getRowCount();
			int selectedList[] = new int[rows];
    		if(!isAllSelected)
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
			pi.setAD_PInstance_ID(instance.getAD_PInstance_ID());
			setBrowseProcessInfo(pi);
			//Save Values Browse Field Update
			createT_Selection_Browse(instance.getAD_PInstance_ID());
			parameterPanel.saveParameters();
			// Execute Process
			ProcessCtl worker = new ProcessCtl(this, 0, getBrowseProcessInfo(), null);
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
		collapsibleSeach.setOpen(false);
		p_loadedOK = initBrowser();
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
	 * @param id
	 * @return Object Value
	 */
	@Override
	public Object getParamenterValue(Object key)
	{
			WEditor editor = (WEditor)  searchGrid.getParamenters().get(key);
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

		for (Entry<Object, Object> entry : searchGrid.getParamenters().entrySet()) {
			WEditor editor = (WEditor) entry.getValue();
			GridFieldVO field = editor.getGridField().getVO();
			if (!onRange) {

				if (editor.getValue() != null
						&& !editor.getValue().toString().isEmpty()
						&& !field.isRange) {
					sql.append(" AND ");
					sql.append(field.Help).append("=? ");
					m_parameters.add(field.Help);
					m_parameters_values.add(editor.getValue());
				} else if (editor.getValue() != null
						&& !editor.getValue().toString().isEmpty()
						&& field.isRange) {
					sql.append(" AND ");
					sql.append(field.Help).append(" BETWEEN ?");
					m_parameters.add(field.Help);
					m_parameters_values.add(editor.getValue());
					onRange = true;
				} else
					continue;
			} else if (editor.getValue() != null
					&& !editor.getValue().toString().isEmpty()) {
				sql.append(" AND ? ");
				m_parameters.add(field.Help);
				m_parameters_values.add(editor.getValue());
				onRange = false;
			}
		}
		m_whereClause = sql.toString();
		return sql.toString();
	}
}
