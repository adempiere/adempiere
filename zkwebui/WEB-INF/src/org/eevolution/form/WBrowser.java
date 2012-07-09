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
import java.util.Map.Entry;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.MBrowse;
import org.adempiere.model.MBrowseField;
import org.adempiere.webui.apps.ProcessParameterPanel;
import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Tab;
import org.adempiere.webui.component.Tabbox;
import org.adempiere.webui.component.Tabpanel;
import org.adempiere.webui.component.Tabpanels;
import org.adempiere.webui.component.Tabs;
import org.adempiere.webui.component.ToolBar;
import org.adempiere.webui.component.WAppsAction;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WebEditorFactory;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.event.WTableModelListener;
import org.adempiere.webui.panel.CustomForm;
import org.adempiere.webui.panel.IFormController;
import org.adempiere.webui.panel.StatusBarPanel;
import org.adempiere.webui.window.FDialog;
import org.compiere.apps.AEnv;
import org.compiere.apps.ProcessCtl;
import org.compiere.apps.search.Info_Column;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.GridField;
import org.compiere.model.GridFieldVO;
import org.compiere.model.MPInstance;
import org.compiere.model.MProcess;
import org.compiere.model.MRole;
import org.compiere.process.ProcessInfo;
import org.compiere.util.ASyncProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
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
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Iframe;
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
	private Iframe iframe;

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
		
		//m_frame.setTitle(getTitle());

		initComponents();
		statInit();
		//p_loadedOK = initBrowser();
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
			String title = m_Browse.getTitle();
			String name = field.getAD_View_Column().getAD_Column()
					.getColumnName();
			addComponent(field, row, name, title);

			cols++;

			if (field.isRange()) {
				title = Msg.getMsg(Env.getCtx(), "To");
				addComponent(field, row, name + "_To", title);
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
		GridFieldVO voBase = GridFieldVO.createStdField(field.getCtx(), p_WindowNo, 0, 0, 0, false, false, false);
	
		voBase.AD_Column_ID = field.getAD_View_Column().getAD_Column_ID();
		voBase.AD_Table_ID = field.getAD_View_Column().getAD_Column().getAD_Table_ID();
		voBase.ColumnName = field.getAD_View_Column().getAD_Column().getColumnName();
		voBase.displayType = field.getAD_Reference_ID();
		voBase.AD_Reference_Value_ID = field.getAD_Reference_Value_ID();
		voBase.IsMandatory = field.isMandatory();
		voBase.IsAlwaysUpdateable = false;
		voBase.IsKey = field.isKey();
		voBase.isRange = field.isRange();
		voBase.IsReadOnly = false;
		voBase.IsUpdateable = true;
		voBase.IsDisplayed = true;
		voBase.Description = field.getDescription();
		voBase.ColumnSQL = field.getAD_View_Column().getColumnSQL();
		voBase.Header = title;
				
		GridField gField = new GridField (GridFieldVO.createParameter(voBase));
		gField.lookupLoadComplete();
		WEditor editor = WebEditorFactory.getEditor(gField, false);
		editor.addValueChangeListener(this);
		editor.dynamicDisplay();
    	Div div = new Div();
        div.setAlign("right");
        org.adempiere.webui.component.Label label = editor.getLabel();
        div.appendChild(label);
        if (label.getDecorator() != null)
        	div.appendChild(label.getDecorator());
        row.appendChild(div);
		row.appendChild(editor.getComponent());
		setParameter(name, editor);

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
		ArrayList<Info_Column> list = initBrowserData();
		if (list.size() == 0) {

			log.log(Level.SEVERE, "No Brwose for view=" + m_View.getName());
			return false;
		}
		log.finest("Browse Fields #" + list.size());
		
		detail.clearTable();		
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

		Integer record_ID = getSelectedRowKey();

		if (record_ID == null)
			return;
		AEnv.zoom(m_View.getParentViewDefinition().getAD_Table_ID(), record_ID);
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
		m_sqlMain = detail.prepareTable(layout, from, staticWhere, false, from);
		StringBuffer sql = new StringBuffer("SELECT DISTINCT ");
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
		
		bSelectAll.setLabel(Msg.getMsg(Env.getCtx(),"SelectAll"));
		bSelectAll.setEnabled(false);
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

		bZoom.setLabel(Msg.getMsg(Env.getCtx(),"Zoom"));
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
		bSearch.setLabel(Msg.getMsg(Env.getCtx(), "StartSearch"));

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

		sNorth.setTitle(" ");
		//sNorth.setFlex(true);
		sNorth.setCollapsible(true);
		sNorth.setAutoscroll(true);
		sNorth.appendChild(div);

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
		dispose(true);
	}

	private void bCancelActionPerformed(Event evt) {
		m_frame.dispose();
	}

	private void bSearchActionPerformed(Event evt) {
		bZoom.setEnabled(true);
		bSelectAll.setEnabled(true);
		bExport.setEnabled(true);
		bDelete.setEnabled(true);
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
			m_pstmt = getStatement();		
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
			WEditor editor = (WEditor) m_search.get(key);
			if(editor != null)
				return editor.getValue();
			else
				return null;
	}
	
	public ArrayList<Object> getParametersValues() {
		ArrayList<Object> values = new ArrayList<Object>();
		for (Entry<Object, Object> entry : m_search.entrySet()) {
			WEditor editor = (WEditor) entry.getValue();
			values.add(editor.getValue());
		}
		return values;
	}
}
