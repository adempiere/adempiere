/******************************************************************************
 * Copyright (C) 2009 Low Heng Sin                                            *
 * Copyright (C) 2009 Idalica Corporation                                     *
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
package org.adempiere.webui.apps.form;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.webui.LayoutUtils;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.apps.BusyDialog;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.DesktopTabpanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Tab;
import org.adempiere.webui.component.Tabbox;
import org.adempiere.webui.component.Tabpanels;
import org.adempiere.webui.component.Tabs;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.event.WTableModelListener;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.StatusBarPanel;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.window.FDialog;
import org.adempiere.webui.window.SimplePDFViewer;
import org.compiere.apps.ProcessCtl;
import org.compiere.apps.form.GenForm;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MQuery;
import org.compiere.model.MTable;
import org.compiere.model.PrintInfo;
import org.compiere.print.MPrintFormat;
import org.compiere.print.ReportEngine;
import org.compiere.process.ProcessInfoUtil;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.au.out.AuEcho;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Div;
import org.zkoss.zul.Html;

/**
 * Generate custom form window
 * 
 */
public class WGenForm extends ADForm implements EventListener, WTableModelListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8669256486969882958L;

	private GenForm genForm;
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(WGenForm.class);
	//
	private Tabbox tabbedPane = new Tabbox();
	private Borderlayout selPanel = new Borderlayout();
	private Grid selNorthPanel = GridFactory.newGridLayout();
	private ConfirmPanel confirmPanelSel = new ConfirmPanel(true);
	private ConfirmPanel confirmPanelGen = new ConfirmPanel(false, true, false, false, false, false, false);
	private StatusBarPanel statusBar = new StatusBarPanel();
	private Borderlayout genPanel = new Borderlayout();
	private Html info = new Html();
	private WListbox miniTable = ListboxFactory.newDataTable();
	private BusyDialog progressWindow;
	
	private int[] m_ids;
	
	public WGenForm(GenForm genForm)
	{
		log.info("");
		this.genForm = genForm;
	}
	
	@Override
	protected void initForm() 
	{
		try
		{
			zkInit();
			dynInit();
			Borderlayout contentPane = new Borderlayout();
			this.appendChild(contentPane);
			contentPane.setWidth("99%");
			contentPane.setHeight("100%");
			Center center = new Center();
			center.setStyle("border: none");
			contentPane.appendChild(center);
			center.appendChild(tabbedPane);
			center.setFlex(true);
			South south = new South();
			south.setStyle("border: none");
			contentPane.appendChild(south);
			south.appendChild(statusBar);
			LayoutUtils.addSclass("status-border", statusBar);
			south.setHeight("22px");			
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, "init", ex);
		}
	}	//	init
	
	/**
	 *	Static Init.
	 *  <pre>
	 *  selPanel (tabbed)
	 *      fOrg, fBPartner
	 *      scrollPane & miniTable
	 *  genPanel
	 *      info
	 *  </pre>
	 *  @throws Exception
	 */
	void zkInit() throws Exception
	{
		//
		selPanel.setWidth("99%");
		selPanel.setHeight("90%");
		selPanel.setStyle("border: none; position: absolute");
		DesktopTabpanel tabpanel = new DesktopTabpanel();
		tabpanel.appendChild(selPanel);
		Tabpanels tabPanels = new Tabpanels();
		tabPanels.appendChild(tabpanel);
		tabbedPane.appendChild(tabPanels);
		Tabs tabs = new Tabs();
		tabbedPane.appendChild(tabs);
		Tab tab = new Tab(Msg.getMsg(Env.getCtx(), "Select"));
		tabs.appendChild(tab);
		
		North north = new North();
		selPanel.appendChild(north);
		north.appendChild(selNorthPanel);
		
		South south = new South();
		selPanel.appendChild(south);
		south.appendChild(confirmPanelSel);
		
		Center center = new Center();
		selPanel.appendChild(center);
		center.appendChild(miniTable);
		center.setFlex(true);
		miniTable.setHeight("99%");
		confirmPanelSel.addActionListener(this);
		//
		tabpanel = new DesktopTabpanel();
		tabPanels.appendChild(tabpanel);
		tabpanel.appendChild(genPanel);
		tab = new Tab(Msg.getMsg(Env.getCtx(), "Generate"));
		tabs.appendChild(tab);
		genPanel.setWidth("99%");
		genPanel.setHeight("90%");
		genPanel.setStyle("border: none; position: absolute");
		center = new Center();
		genPanel.appendChild(center);
		Div div = new Div();
		div.appendChild(info);
		center.appendChild(div);
		south = new South();
		genPanel.appendChild(south);
		south.appendChild(confirmPanelGen);
		confirmPanelGen.addActionListener(this);		
	}	//	jbInit

	/**
	 *	Dynamic Init.
	 *	- Create GridController & Panel
	 *	- AD_Column_ID from C_Order
	 */
	public void dynInit()
	{
		genForm.configureMiniTable(miniTable);
		miniTable.getModel().addTableModelListener(this);
		//	Info
		statusBar.setStatusDB(" ");
		//	Tabbed Pane Listener
		tabbedPane.addEventListener(Events.ON_SELECT, this);
	}	//	dynInit

	public void postQueryEvent() 
    {
		Clients.showBusy(Msg.getMsg(Env.getCtx(), "Processing"), true);
    	Events.echoEvent("onExecuteQuery", this, null);
    }
    
    /**
     * Dont call this directly, use internally to handle execute query event 
     */
    public void onExecuteQuery()
    {
    	try
    	{
    		genForm.executeQuery();
    	}
    	finally
    	{
    		Clients.showBusy(null, false);
    	}
    }
    
	/**
	 *	Action Listener
	 *  @param e event
	 */
	public void onEvent(Event e)
	{
		log.info("Cmd=" + e.getTarget().getId());
		//
		if (e.getTarget().getId().equals(ConfirmPanel.A_CANCEL))
		{
			dispose();
			return;
		}
		else if (e.getTarget() instanceof Tab)
		{
			int index = tabbedPane.getSelectedIndex();
			genForm.setSelectionActive(index == 0);
			return;
		}
		
		genForm.validate();
	}	//	actionPerformed

	/**
	 *  Table Model Listener
	 *  @param e event
	 */
	public void tableChanged(WTableModelEvent e)
	{
		int rowsSelected = 0;
		int rows = miniTable.getRowCount();
		for (int i = 0; i < rows; i++)
		{
			IDColumn id = (IDColumn)miniTable.getValueAt(i, 0);     //  ID in column 0
			if (id != null && id.isSelected())
				rowsSelected++;
		}
		statusBar.setStatusDB(" " + rowsSelected + " ");
	}   //  tableChanged

	/**
	 *	Save Selection & return selecion Query or ""
	 *  @return where clause like C_Order_ID IN (...)
	 */
	public void saveSelection()
	{
		genForm.saveSelection(miniTable);
	}	//	saveSelection

	
	/**************************************************************************
	 *	Generate Shipments
	 */
	public void generate()
	{
		info.setContent(genForm.generate());		

		this.lockUI();
		Clients.response(new AuEcho(this, "runProcess", null));		
	}	//	generate

	/**
	 * Internal use, don't call this directly
	 */
	public void runProcess() 
	{
		final ProcessCtl worker = new ProcessCtl(null, getWindowNo(), genForm.getProcessInfo(), genForm.getTrx());
		try {                    						
			worker.run();     //  complete tasks in unlockUI / generateShipments_complete						
		} finally{						
			unlockUI();
		}
	}
	
	/**
	 *  Complete generating shipments.
	 *  Called from Unlock UI
	 *  @param pi process info
	 */
	private void generateComplete ()
	{
		if (progressWindow != null) {
			progressWindow.dispose();
			progressWindow = null;
		}
		
		//  Switch Tabs
		tabbedPane.setSelectedIndex(1);
		// commented by reason of "prints result twice":
		//ProcessInfoUtil.setLogFromDB(genForm.getProcessInfo());
		StringBuffer iText = new StringBuffer();
		iText.append("<b>").append(genForm.getProcessInfo().getSummary())
			.append("</b><br>(")
			.append(Msg.getMsg(Env.getCtx(), genForm.getTitle()))
			//  Shipments are generated depending on the Delivery Rule selection in the Order
			.append(")<br>")
			.append(genForm.getProcessInfo().getLogInfo(true));
		info.setContent(iText.toString());

		//	Get results
		int[] ids = genForm.getProcessInfo().getIDs();
		if (ids == null || ids.length == 0)
			return;
		log.config("PrintItems=" + ids.length);
		
		m_ids = ids;
		Clients.response(new AuEcho(this, "onAfterProcess", null));
		
	}   //  generateShipments_complete
	
	
	public void onAfterProcess()
	{
		//	OK to print
		if (FDialog.ask(getWindowNo(), this, genForm.getAskPrintMsg()))
		{
			Clients.showBusy("Processing...", true);
			Clients.response(new AuEcho(this, "onPrint", null));			
		}	//	OK to print
	}
	
	public void onPrint() 
	{
//		Loop through all items
		List<File> pdfList = new ArrayList<File>();
		for (int i = 0; i < m_ids.length; i++)
		{
			int RecordID = m_ids[i];
			ReportEngine re = null;
			
			if(genForm.getPrintFormat() != null)
			{
				MPrintFormat format = genForm.getPrintFormat();
				MTable table = MTable.get(Env.getCtx(),format.getAD_Table_ID());
				MQuery query = new MQuery(table.getTableName());
				query.addRestriction(table.getTableName() + "_ID", MQuery.EQUAL, RecordID);
				//	Engine
				PrintInfo info = new PrintInfo(table.getTableName(),table.get_Table_ID(), RecordID);               
				re = new ReportEngine(Env.getCtx(), format, query, info);
			}
			else
			{	
				re = ReportEngine.get (Env.getCtx(), genForm.getReportEngineType(), RecordID);
			}	
			if (re != null)
				pdfList.add(re.getPDF());
		}
		
		if (pdfList.size() > 1) {
			try {
				File outFile = File.createTempFile(genForm.getClass().getName(), ".pdf");					
				AEnv.mergePdf(pdfList, outFile);

				Clients.showBusy(null, false);
				Window win = new SimplePDFViewer(getFormName(), new FileInputStream(outFile));
				SessionManager.getAppDesktop().showWindow(win, "center");
			} catch (Exception e) {
				log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			}
		} else if (pdfList.size() > 0) {
			Clients.showBusy(null, false);
			try {
				Window win = new SimplePDFViewer(getFormName(), new FileInputStream(pdfList.get(0)));
				SessionManager.getAppDesktop().showWindow(win, "center");
			} catch (Exception e)
			{
				log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			}
		}
	}

	/**************************************************************************
	 *  Lock User Interface.
	 *  Called from the Worker before processing
	 *  @param pi process info
	 */
	public void lockUI ()
	{
		progressWindow = new BusyDialog();
		progressWindow.setPage(this.getPage());
		progressWindow.doHighlighted();
	}   //  lockUI

	/**
	 *  Unlock User Interface.
	 *  Called from the Worker when processing is done
	 *  @param pi result of execute ASync call
	 */
	public void unlockUI ()
	{		
		generateComplete();
	}   //  unlockUI
	
	public void dispose() {
		SessionManager.getAppDesktop().closeActiveWindow();
	}
	
	public Grid getParameterPanel()
	{
		return selNorthPanel;
	}
	
	public WListbox getMiniTable()
	{
		return miniTable;
	}
	
	public StatusBarPanel getStatusBar()
	{
		return statusBar;
	}
}
