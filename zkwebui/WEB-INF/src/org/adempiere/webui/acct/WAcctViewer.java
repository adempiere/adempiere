/******************************************************************************
 * Product: Posterita Ajax UI 												  *
 * Copyright (C) 2007 Posterita Ltd. All Rights Reserved.                     *
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

package org.adempiere.webui.acct;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.core.domains.models.X_C_AcctSchema_Element;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.Datebox;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListModelTable;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.Tab;
import org.adempiere.webui.component.Tabbox;
import org.adempiere.webui.component.Tabpanel;
import org.adempiere.webui.component.Tabpanels;
import org.adempiere.webui.component.Tabs;
import org.adempiere.webui.component.VerticalBox;
import org.adempiere.webui.component.WListItemRenderer;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.panel.InfoPanel;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MAcctSchemaElement;
import org.compiere.model.Query;
import org.compiere.report.core.RColumn;
import org.compiere.report.core.RModel;
import org.compiere.report.core.RModelExcelExporter;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.compiere.util.ValueNamePair;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Separator;

/**
 *  Account Viewer : Based on class AcctViewer
 *
 *  @author Niraj Sohun
 *  		July 27, 2007
 *
 *  @author Elaine Tan
 *  @author Low Heng Sin
 *  @author Michael McKay, 
 * 				<li>ADEMPIERE-72 VLookup and Info Window improvements
 * 					https://adempiere.atlassian.net/browse/ADEMPIERE-72
 *  @author victor.perez@e-evolution.com, www.e-evolution.com
 * 			<li>FR[3435028] Add Export Icon in Account Viewer for ZK
 * 			<li>http://sourceforge.net/tracker/?func=detail&aid=3435028&group_id=176962&atid=879335
 */

public class WAcctViewer extends Window implements EventListener
{
	/**
	 *
	 */
	private static final long serialVersionUID = -223185724918504685L;

	private static final int PAGE_SIZE = 1000;

	/** State Info          */
	private WAcctViewerData accountViewerData = null;

	private Listbox selAcctSchema = new Listbox();
	private Listbox selTable = new Listbox();
	private Listbox selPostingType = new Listbox();
	private Listbox selOrg = new Listbox();
	private Listbox sortBy1 = new Listbox();
	private Listbox sortBy2 = new Listbox();
	private Listbox sortBy3 = new Listbox();
	private Listbox sortBy4 = new Listbox();

	private Button selRecord = new Button();
	private Button selAcct = new Button();
	private Button bQuery = new Button();
	private Button bRePost = new Button();
	private Button bPrint = new Button();
	private Button bExport = new Button();
	private Button bZoom = new Button();

	private Button sel1 = new Button();
	private Button sel2 = new Button();
	private Button sel3 = new Button();
	private Button sel4 = new Button();
	private Button sel5 = new Button();
	private Button sel6 = new Button();
	private Button sel7 = new Button();
	private Button sel8 = new Button();
	private Button sel9 = new Button();
	private Button sel10 = new Button();
	private Button sel11 = new Button();
	private Button sel12 = new Button();
	private Button sel13 = new Button();



	private Label statusLine = new Label();
	private Label lsel1 = new Label();
	private Label lsel2 = new Label();
	private Label lsel3 = new Label();
	private Label lsel4 = new Label();
	private Label lsel5 = new Label();
	private Label lsel6 = new Label();
	private Label lsel7 = new Label();
	private Label lsel8 = new Label();
	private Label lsel9 = new Label();
	private Label lsel10 = new Label();
	private Label lsel11 = new Label();
	private Label lsel12 = new Label();
	private Label lsel13 = new Label();


	private Label lacctSchema = new Label();
	private Label lpostingType = new Label();
	private Label lOrg = new Label();
	private Label lAcct = new Label();
	private Label lDate = new Label();
	private Label lSort = new Label();
	private Label lGroup = new Label();

	private Datebox selDateFrom = new Datebox();
	private Datebox selDateTo = new Datebox();

	private Checkbox selDocument = new Checkbox();
	private Checkbox displayQty = new Checkbox();
	private Checkbox displaySourceAmt = new Checkbox();
	private Checkbox displayDocumentInfo = new Checkbox();
	private Checkbox group1 = new Checkbox();
	private Checkbox group2 = new Checkbox();
	private Checkbox group3 = new Checkbox();
	private Checkbox group4 = new Checkbox();
	private Checkbox forcePost = new Checkbox();

	private Tabbox tabbedPane = new Tabbox();
	private Iframe iframe = new Iframe();

	private WListbox table = new WListbox();
	private Paging paging = new Paging();

	private VerticalBox displayPanel = new VerticalBox();
	private VerticalBox selectionPanel = new VerticalBox();

	private Tab tabQuery = new Tab();
	private Tab tabResult = new Tab();
	private Tabs tabs = new Tabs();
	private Tabpanel result = new Tabpanel();
	private Tabpanel query = new Tabpanel();
	private Tabpanels tabpanels = new Tabpanels();

	private Hbox southPanel = new Hbox();

	private int windowNo;
	private boolean isLookup;

	private ArrayList<ArrayList<Object>> queryData;

	private South pagingPanel;

	private Borderlayout resultPanel;

	/**	Logger				*/
	private static CLogger log = CLogger.getCLogger(WAcctViewer.class);

	/**
	 *  Default constructor
	 */

	public WAcctViewer()
	{
		this (0, 0, 0);
	} // AcctViewer

	/**
	 *  Detail Constructor
	 *
	 *  @param clientId Client
	 *  @param tableId Table
	 *  @param recordId Record
	 */

	public WAcctViewer(int clientId, int tableId, int recordId)
	{
		this (clientId, tableId, recordId, false);
	}
	
	/**
	 *  Detail Constructor
	 *
	 *  @param clientId Client
	 *  @param tableId Table
	 *  @param recordId Record
	 *  @param isLookup - a flag, modal if true, non-modal if false
	 */

	public WAcctViewer(int clientId, int tableId, int recordId, Boolean isLookup)
	{
		super ();

		log.info("AD_Table_ID=" + tableId + ", Record_ID=" + recordId);

		//setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		windowNo = SessionManager.getAppDesktop().registerWindow(this);
		accountViewerData = new WAcctViewerData (Env.getCtx(), windowNo, clientId, tableId);
		this.isLookup = isLookup;

		try
		{
			init();
			dynInit (tableId, recordId);
			AEnv.showWindow(this);
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "", e);
			//dispose();
		}
	} // AcctViewer

	/**
	 *  Static Init.
	 *  <pre>
	 *  - mainPanel
	 *      - tabbedPane
	 *          - query
	 *          - result
	 *          - graphPanel
	 *  </pre>
	 *  @throws Exception
	 */

	private void init() throws Exception
	{
		// Modal or non-modal
		if (isLookup())
		{
			setTitle(Msg.getMsg(Env.getCtx(), "Posting"));
			setAttribute(Window.MODE_KEY, Window.MODE_MODAL);
			setBorder("normal");
			setClosable(true);
			int height = SessionManager.getAppDesktop().getClientInfo().desktopHeight * 85 / 100;
    		int width = SessionManager.getAppDesktop().getClientInfo().desktopWidth * 80 / 100;
    		setWidth(width + "px");
    		setHeight(height + "px");
    		setContentStyle("overflow: auto");
			setSizable(true);
			setMaximizable(true);
		}
		else
		{
			setTitle(Msg.getMsg(Env.getCtx(), "InfoAccount"));
			setAttribute(Window.MODE_KEY, Window.MODE_EMBEDDED);
			setBorder("none");
			setWidth("100%");
			setHeight("100%");
			setStyle("position: absolute");
		}

		
		// Selection Panel

		// Accounting Schema

		Hbox boxAcctSchema = new Hbox();
		boxAcctSchema.setWidth("100%");
		boxAcctSchema.setWidths("30%, 70%");

		lacctSchema.setValue(Msg.translate(Env.getCtx(), "C_AcctSchema_ID"));
		lacctSchema.setAttribute("zk_component_ID", "Lookup_Criteria_Label_C_AcctSchema_ID");

		selAcctSchema.setMold("select");
		selAcctSchema.setRows(1);
		selAcctSchema.setAttribute("zk_component_ID", "Lookup_Criteria_C_AcctSchema_ID");

		boxAcctSchema.appendChild(lacctSchema);
		boxAcctSchema.appendChild(selAcctSchema);

		Hbox boxSelDoc = new Hbox();
		boxSelDoc.setWidth("100%");
		boxSelDoc.setWidths("30%, 50%, 20%");

		selDocument.setLabel(Msg.getMsg(Env.getCtx(), "SelectDocument"));
		selDocument.setAttribute("zk_component_ID", "Lookup_Criteria_selDocument");
		selDocument.addEventListener(Events.ON_CHECK, this);

		selTable.setMold("select");
		selTable.setRows(1);
		selTable.setAttribute("zk_component_ID", "Lookup_Criteria_selTable");
		selRecord.setAttribute("zk_component_ID", "Lookup_Criteria_selRecord");


		boxSelDoc.appendChild(selDocument);
		boxSelDoc.appendChild(selTable);
		boxSelDoc.appendChild(selRecord);

			// Posting Type

		Hbox boxPostingType = new Hbox();
		boxPostingType.setWidth("100%");
		boxPostingType.setWidths("30%, 70%");

		lpostingType.setValue(Msg.translate(Env.getCtx(), "PostingType"));
		selPostingType.setMold("select");
		selPostingType.setRows(1);
		selPostingType.addEventListener(Events.ON_CLICK, this);
		selPostingType.setAttribute("zk_component_ID", "Lookup_Criteria_selPostingType");

		boxPostingType.appendChild(lpostingType);
		boxPostingType.appendChild(selPostingType);

			// Date

		Hbox boxDate = new Hbox();
		boxDate.setWidth("100%");
		boxDate.setWidths("30%, 35%, 35%");

		lDate.setValue(Msg.translate(Env.getCtx(), "DateAcct"));
		lDate.setAttribute("zk_component_ID", "Lookup_Criteria_Label_Date");
		selDateFrom.setAttribute("zk_component_ID", "Lookup_Criteria_selDateFrom");
		selDateTo.setAttribute("zk_component_ID", "Lookup_Criteria_selDateTo");

		boxDate.appendChild(lDate);
		boxDate.appendChild(selDateFrom);
		boxDate.appendChild(selDateTo);

			// Organization

		Hbox boxOrg = new Hbox();
		boxOrg.setWidth("100%");
		boxOrg.setWidths("30%, 70%");

		lOrg.setValue(Msg.translate(Env.getCtx(), "AD_Org_ID"));
		selOrg.setMold("select");
		selOrg.setRows(1);
		selOrg.addEventListener(Events.ON_SELECT, this);
		lOrg.setAttribute("zk_component_ID", "Lookup_Criteria_Label_Org");
		selOrg.setAttribute("zk_component_ID", "Lookup_Criteria_selOrg");

		boxOrg.appendChild(lOrg);
		boxOrg.appendChild(selOrg);

			// Account

		Hbox boxAcct = new Hbox();
		boxAcct.setWidth("100%");
		boxAcct.setWidths("30%, 70%");

		lAcct.setValue(Msg.translate(Env.getCtx(), "Account_ID"));
		lAcct.setAttribute("zk_component_ID", "Lookup_Criteria_Label_Acct");
		selAcct.setAttribute("zk_component_ID", "Lookup_Criteria_selAcct");

		boxAcct.appendChild(lAcct);
		boxAcct.appendChild(selAcct);

		Hbox boxSel1 = new Hbox();
		boxSel1.setWidth("100%");
		boxSel1.setWidths("30%, 70%");

		boxSel1.appendChild(lsel1);
		boxSel1.appendChild(sel1);

		Hbox boxSel2 = new Hbox();
		boxSel2.setWidth("100%");
		boxSel2.setWidths("30%, 70%");

		boxSel2.appendChild(lsel2);
		boxSel2.appendChild(sel2);

		Hbox boxSel3 = new Hbox();
		boxSel3.setWidth("100%");
		boxSel3.setWidths("30%, 70%");

		boxSel3.appendChild(lsel3);
		boxSel3.appendChild(sel3);

		Hbox boxSel4 = new Hbox();
		boxSel4.setWidth("100%");
		boxSel4.setWidths("30%, 70%");

		boxSel4.appendChild(lsel4);
		boxSel4.appendChild(sel4);

		Hbox boxSel5 = new Hbox();
		boxSel5.setWidth("100%");
		boxSel5.setWidths("30%, 70%");

		boxSel5.appendChild(lsel5);
		boxSel5.appendChild(sel5);

		Hbox boxSel6 = new Hbox();
		boxSel6.setWidth("100%");
		boxSel6.setWidths("30%, 70%");

		boxSel6.appendChild(lsel6);
		boxSel6.appendChild(sel6);

		Hbox boxSel7 = new Hbox();
		boxSel7.setWidth("100%");
		boxSel7.setWidths("30%, 70%");

		boxSel7.appendChild(lsel7);
		boxSel7.appendChild(sel7);

		Hbox boxSel8 = new Hbox();
		boxSel8.setWidth("100%");
		boxSel8.setWidths("30%, 70%");

		boxSel8.appendChild(lsel8);
		boxSel8.appendChild(sel8);

		Hbox boxSel9 = new Hbox();
		boxSel9.setWidth("100%");
		boxSel9.setWidths("30%, 70%");

		boxSel9.appendChild(lsel9);
		boxSel9.appendChild(sel9);



		Hbox boxSel10 = new Hbox();
		boxSel10.setWidth("100%");
		boxSel10.setWidths("30%, 70%");

		boxSel10.appendChild(lsel10);
		boxSel10.appendChild(sel10);


		Hbox boxSel11 = new Hbox();
		boxSel11.setWidth("100%");
		boxSel11.setWidths("30%, 70%");

		boxSel11.appendChild(lsel11);
		boxSel11.appendChild(sel11);


		Hbox boxSel12 = new Hbox();
		boxSel12.setWidth("100%");
		boxSel12.setWidths("30%, 70%");

		boxSel12.appendChild(lsel12);
		boxSel12.appendChild(sel12);

		Hbox boxSel13 = new Hbox();
		boxSel13.setWidth("100%");
		boxSel13.setWidths("30%, 70%");

		boxSel13.appendChild(lsel13);
		boxSel13.appendChild(sel13);

		selectionPanel.setWidth("100%");
		selectionPanel.appendChild(boxAcctSchema);
		selectionPanel.appendChild(boxSelDoc);
		selectionPanel.appendChild(boxPostingType);
		selectionPanel.appendChild(boxDate);
		selectionPanel.appendChild(boxOrg);
		selectionPanel.appendChild(boxAcct);
		selectionPanel.appendChild(boxSel1);
		selectionPanel.appendChild(boxSel2);
		selectionPanel.appendChild(boxSel3);
		selectionPanel.appendChild(boxSel4);
		selectionPanel.appendChild(boxSel5);
		selectionPanel.appendChild(boxSel6);
		selectionPanel.appendChild(boxSel7);
		selectionPanel.appendChild(boxSel8);
		selectionPanel.appendChild(boxSel9);
		selectionPanel.appendChild(boxSel10);
		selectionPanel.appendChild(boxSel11);
		selectionPanel.appendChild(boxSel12);
		selectionPanel.appendChild(boxSel13);

		// Display Panel
		// Display Document Info
		displayDocumentInfo.setLabel(Util.cleanAmp(Msg.getMsg(Env.getCtx(), "DisplayDocumentInfo")));
		displayDocumentInfo.addEventListener(Events.ON_CHECK, this);
		displayDocumentInfo.setSelected(true);

			// Display Source Info

		displaySourceAmt.setLabel(Util.cleanAmp(Msg.getMsg(Env.getCtx(), "DisplaySourceInfo")));
		displaySourceAmt.addEventListener(Events.ON_CHECK, this);


			// Display Quantity

		displayQty.setLabel(Util.cleanAmp(Msg.getMsg(Env.getCtx(), "DisplayQty")));
		displayQty.addEventListener(Events.ON_CHECK, this);

		Hbox boxSortDisplay = new Hbox();
		boxSortDisplay.setWidth("100%");
		boxSortDisplay.setWidths("70%, 30%");

		lSort.setValue(Util.cleanAmp(Msg.getMsg(Env.getCtx(), "SortBy")));
		lGroup.setValue(Util.cleanAmp(Msg.getMsg(Env.getCtx(), "GroupBy")));

		boxSortDisplay.appendChild(lSort);
		boxSortDisplay.appendChild(lGroup);

		Hbox boxSort1 = new Hbox();
		boxSort1.setWidth("100%");
		boxSort1.setWidths("70%, 30%");

		sortBy1.setMold("select");
		sortBy1.setRows(1);

		boxSort1.appendChild(sortBy1);
		boxSort1.appendChild(group1);

		Hbox boxSort2 = new Hbox();
		boxSort2.setWidth("100%");
		boxSort2.setWidths("70%, 30%");

		sortBy2.setMold("select");
		sortBy2.setRows(1);

		boxSort2.appendChild(sortBy2);
		boxSort2.appendChild(group2);

		Hbox boxSort3 = new Hbox();
		boxSort3.setWidth("100%");
		boxSort3.setWidths("70%, 30%");

		sortBy3.setMold("select");
		sortBy3.setRows(1);

		boxSort3.appendChild(sortBy3);
		boxSort3.appendChild(group3);

		Hbox boxSort4 = new Hbox();
		boxSort4.setWidth("100%");
		boxSort4.setWidths("70%, 30%");

		sortBy4.setMold("select");
		sortBy4.setRows(1);

		boxSort4.appendChild(sortBy4);
		boxSort4.appendChild(group4);

		displayPanel.setWidth("100%");
		displayPanel.appendChild(displayDocumentInfo);
		displayPanel.appendChild(displaySourceAmt);
		displayPanel.appendChild(displayQty);
		displayPanel.appendChild(boxSortDisplay);
		displayPanel.appendChild(boxSort1);
		displayPanel.appendChild(boxSort2);
		displayPanel.appendChild(boxSort3);
		displayPanel.appendChild(boxSort4);

		//"images/InfoAccount16.png"

		Groupbox groupDisplay = new Groupbox();
		Caption capDisplay = new Caption("Display");
		groupDisplay.appendChild(capDisplay);
		groupDisplay.appendChild(displayPanel);

		Groupbox groupSelection = new Groupbox();
		Caption capSelection = new Caption("Selection");
		groupSelection.appendChild(capSelection);
		groupSelection.appendChild(selectionPanel);

		Hbox boxQueryPanel = new Hbox();

		boxQueryPanel.setWidth("98%");
		boxQueryPanel.setWidths("63%,1%,36%");

		boxQueryPanel.appendChild(groupSelection);
		Separator separator = new Separator();
		separator.setOrient("vertical");
		boxQueryPanel.appendChild(separator);
		boxQueryPanel.appendChild(groupDisplay);

		//  South Panel

		bRePost.setLabel(Util.cleanAmp(Msg.getMsg(Env.getCtx(), "RePost")));
		bRePost.setTooltiptext(Util.cleanAmp(Msg.getMsg(Env.getCtx(), "RePostInfo")));
		bRePost.addEventListener(Events.ON_CLICK, this);
		bRePost.setVisible(false);

		forcePost.setLabel(Util.cleanAmp(Msg.getMsg(Env.getCtx(), "Force")));
		forcePost.setTooltiptext(Util.cleanAmp(Msg.getMsg(Env.getCtx(), "ForceInfo")));
		forcePost.setVisible(false);

		bQuery.setImage("/images/Refresh16.png");
		bQuery.setTooltiptext(Util.cleanAmp(Msg.getMsg(Env.getCtx(), "Refresh")));
		bQuery.addEventListener(Events.ON_CLICK, this);

		bZoom.setImage("/images/Zoom16.png");
		bZoom.setTooltiptext(Util.cleanAmp(Msg.getMsg(Env.getCtx(), "Zoom")));
		bZoom.setVisible(displayDocumentInfo.isSelected());
		bZoom.addEventListener(Events.ON_CLICK, this);

		bExport.setImage("/images/Export16.png");
		bExport.setTooltiptext(Util.cleanAmp(Msg.getMsg(Env.getCtx(), "Export")));
		bExport.addEventListener(Events.ON_CLICK, this);

		bPrint.setImage("/images/Print16.png");
		bPrint.setTooltiptext(Util.cleanAmp(Msg.getMsg(Env.getCtx(), "Print")));
		bPrint.addEventListener(Events.ON_CLICK, this);
		
		southPanel.setWidth("100%");
		southPanel.setWidths("2%, 12%, 82%, 2% , 2%, 2%");
		southPanel.appendChild(bRePost);
		southPanel.appendChild(forcePost);
		southPanel.appendChild(statusLine);
		southPanel.appendChild(bZoom);
		southPanel.appendChild(bExport);
		southPanel.appendChild(bPrint);
		southPanel.appendChild(bQuery);

		// Result Tab

		resultPanel = new Borderlayout();
		resultPanel.setStyle("position: absolute");
		resultPanel.setWidth("99%");
		resultPanel.setHeight("99%");
		result.appendChild(resultPanel);

		Center resultCenter = new Center();
		resultCenter.setFlex(true);
		resultPanel.appendChild(resultCenter);
		table.setWidth("96%");
		table.setHeight("98%");
		table.setVflex(true);
		table.setStyle("overflow: auto; position: absolute;");
		resultCenter.appendChild(table);

		pagingPanel = new South();
		resultPanel.appendChild(pagingPanel);
		pagingPanel.appendChild(paging);

		result.setWidth("100%");
		result.setHeight("100%");
		result.setStyle("position: relative");

		paging.addEventListener("onPaging", this);
		paging.setAutohide(true);
		paging.setDetailed(true);

		// Query Tab

		query.setWidth("100%");
		query.appendChild(boxQueryPanel);

		// Tabbox

		tabQuery.addEventListener(Events.ON_SELECT, this);
		tabQuery.setLabel(Util.cleanAmp(Msg.getMsg(Env.getCtx(), "ViewerQuery")));

		tabResult.addEventListener(Events.ON_SELECT, this);
		tabResult.setLabel(Util.cleanAmp(Msg.getMsg(Env.getCtx(), "ViewerResult")));

		tabs.appendChild(tabQuery);
		tabs.appendChild(tabResult);

		tabpanels.setWidth("100%");
		tabpanels.appendChild(query);
		tabpanels.appendChild(result);

		tabbedPane.setWidth("100%");
		tabbedPane.setHeight("100%");
		tabbedPane.appendChild(tabs);
		tabbedPane.appendChild(tabpanels);

		Borderlayout layout = new Borderlayout();
		layout.setParent(this);
		layout.setHeight("100%");
		layout.setWidth("100%");
		layout.setStyle("background-color: transparent");

		Center center = new Center();
		center.setParent(layout);
		center.setFlex(true);
		center.setStyle("background-color: transparent");
		tabbedPane.setParent(center);

		South south = new South();
		south.setParent(layout);
		south.setFlex(true);
		south.setStyle("background-color: transparent");
		southPanel.setParent(south);

		//tabbedPane.addEventListener(Events.ON_SELECT, this);
	}

	/**
	 *  Dynamic Init
	 *
	 *  @param tableId table
	 *  @param recordId record
	 */

	private void dynInit (int tableId, int recordId)
	{
		accountViewerData.fillAcctSchema(selAcctSchema );
		selAcctSchema.addEventListener(Events.ON_SELECT, this);

		selAcctSchema.setSelectedIndex(0);
		actionAcctSchema();

		accountViewerData.fillTable(selTable);
		selTable.addEventListener(Events.ON_SELECT, this);

		selRecord.setImage("/images/Find16.png");
		selRecord.addEventListener(Events.ON_CLICK, this);
		selRecord.setLabel("");

		accountViewerData.fillPostingType(selPostingType);
		selPostingType.setSelectedIndex(0);

		//  Mandatory Elements

		accountViewerData.fillOrg(selOrg);
		selAcct.setName("Account_ID");
		selAcct.addEventListener(Events.ON_CLICK, this);
		selAcct.setLabel("");
		selAcct.setImage("/images/Find16.png");

		statusLine.setValue(" " + Msg.getMsg(Env.getCtx(), "ViewerOptions"));

		//  Initial Query
		selOrg.setSelectedIndex(0);
		sortBy1.setSelectedIndex(0);
		sortBy2.setSelectedIndex(0);
		sortBy3.setSelectedIndex(0);
		sortBy4.setSelectedIndex(0);

		//  Document Select
		boolean haveDocument = (tableId != 0 && recordId != 0);
		selDocument.setChecked(haveDocument);
		actionDocument();
		actionTable();
		statusLine.setText(" " + Msg.getMsg(Env.getCtx(), "ViewerOptions"));

		//  Initial Query
		if (haveDocument)
		{
			accountViewerData.AD_Table_ID = tableId;
			accountViewerData.Record_ID = recordId;
			actionQuery();
			String keyColumn = selRecord.getName();
			String tableName = keyColumn.substring(0, keyColumn.length()-3);
			String selectSQL = keyColumn + "=" + recordId;
			accountViewerData.buttonRecordID.put(keyColumn,recordId);
			selRecord.setLabel(accountViewerData.getButtonText(tableName, keyColumn, selectSQL));
		}

		if (tabResult.isSelected())
			stateChanged();
		
		// Display quantity as default
		displayQty.setSelected(true);
		displayDocumentInfo.setSelected(true);
	} // dynInit

	/**
	 *  Dispose
	 */

	public void dispose()
	{
		accountViewerData.dispose();
		accountViewerData = null;
		this.detach();
	} // dispose;

	/**************************************************************************
	 *  Tab Changed
	 */

	public void stateChanged()
	{
	//	log.info( "AcctViewer.stateChanged");
		iframe.setContent(null);
		boolean visible = accountViewerData.documentQuery && tabResult.isSelected();
		bRePost.setVisible(visible);

		if (Ini.isPropertyBool(Ini.P_SHOW_ADVANCED))
			forcePost.setVisible(visible);
	}   //  stateChanged

	/**
	 *  Event Performed (Event Listener)
	 *  @param event Event
	 */

	public void onEvent(Event event) throws Exception
	{
		// log.info(e.getActionCommand());
		iframe.setContent(null);
		
		Object source = event.getTarget();

		if (source == tabResult)
			stateChanged();
		if (source == displayDocumentInfo)
		{
			if (displayDocumentInfo.isChecked())
				bZoom.setVisible(true);
			else
				bZoom.setVisible(false);
		}
		else if (source == tabQuery)
			stateChanged();
		else if (source == selAcctSchema)
			actionAcctSchema();
		else if (source == bQuery)
			actionQuery();
		else if (source == selDocument)
			actionDocument();
		else if (source == selTable)
			actionTable();
		else if (source == bRePost)
			actionRePost();
		else if (source == bZoom)
			actionZoom();
		else if  (source == bExport)
			actionExportExcel();
		else if  (source == bPrint)
			;//PrintScreenPainter.printScreen(this);
		//  InfoButtons
		else if  (source == bExport)
			actionExportExcel(); // Export the table.
		else if (source instanceof Button)
			actionButton((Button)source);
		else if (source == paging)
		{
			int pgno = paging.getActivePage();
			int start = pgno * PAGE_SIZE;
			int end = start + PAGE_SIZE;
			if ( end > paging.getTotalSize())
				end = paging.getTotalSize();
			List<ArrayList<Object>> list = queryData.subList(start, end);
			ListModelTable model = new ListModelTable(list);
			table.setModel(model);
		}
	} // onEvent

	/**
	 * 	New Acct Schema
	 */

	private void actionAcctSchema()
	{
		Listitem listitem = selAcctSchema.getSelectedItem();

		KeyNamePair keyNamePair = null;

		if (listitem != null)
			keyNamePair = (KeyNamePair)listitem.getValue();

		if (keyNamePair == null)
			return;

		accountViewerData.C_AcctSchema_ID = keyNamePair.getKey();
		accountViewerData.ASchema = MAcctSchema.get(Env.getCtx(), accountViewerData.C_AcctSchema_ID);

		log.info(accountViewerData.ASchema.toString());

		//  Sort Options

		sortBy1.getChildren().clear();
		sortBy2.getChildren().clear();
		sortBy3.getChildren().clear();
		sortBy4.getChildren().clear();

		sortAddItem(new ValueNamePair("",""));
		sortAddItem(new ValueNamePair("DateAcct", Msg.translate(Env.getCtx(), "DateAcct")));
		sortAddItem(new ValueNamePair("DateTrx", Msg.translate(Env.getCtx(), "DateTrx")));
		sortAddItem(new ValueNamePair("C_Period_ID", Msg.translate(Env.getCtx(), "C_Period_ID")));

		Label[] labels = new Label[] {lsel1, lsel2, lsel3, lsel4, lsel5, lsel6, lsel7, lsel8 , lsel9, lsel10, lsel11, lsel12, lsel13};
		Button[] buttons = new Button[] {sel1 , sel2, sel3, sel4, sel5, sel6, sel7, sel8, sel9, sel10, sel11, sel12, sel13};

		int selectionIndex = 0;

		MAcctSchemaElement[] elements = accountViewerData.ASchema.getAcctSchemaElements();
		for (int i = 0; i < elements.length && selectionIndex < labels.length; i++)
		{
			MAcctSchemaElement acctSchemaElement = elements[i];
			String columnName = acctSchemaElement.getColumnName();
			String displayColumnName;
			if (columnName.equals("User1_ID") || columnName.equals("User2_ID") || columnName.equals("User3_ID") || columnName.equals("User4_ID")  )
				displayColumnName = acctSchemaElement.getName();
			else
				displayColumnName = acctSchemaElement.getDisplayColumnName();

			//  Add Sort Option

			sortAddItem(new ValueNamePair(columnName, Msg.translate(Env.getCtx(), displayColumnName)));

			//  Additional Elements

			if (!acctSchemaElement.isElementType(X_C_AcctSchema_Element.ELEMENTTYPE_Organization)
				&& !acctSchemaElement.isElementType(X_C_AcctSchema_Element.ELEMENTTYPE_Account))
			{
				labels[selectionIndex].setValue(Msg.translate(Env.getCtx(), displayColumnName));
				labels[selectionIndex].setVisible(true);
				buttons[selectionIndex].setName(columnName); // actionCommand
				buttons[selectionIndex].addEventListener(Events.ON_CLICK, this);
				buttons[selectionIndex].setImage("/images/Find16.png");
				buttons[selectionIndex].setLabel("");
				buttons[selectionIndex].setVisible(true);
				selectionIndex++;
			}
		}

		//	don't show remaining

		while (selectionIndex < labels.length)
		{
			labels[selectionIndex].setVisible(false);
			buttons[selectionIndex++].setVisible(false);
		}
	} // actionAcctSchema

	/**
	 * 	Add to Sort
	 *	@param valueNamePair name pair
	 */

	private void sortAddItem(ValueNamePair valueNamePair)
	{
		sortBy1.appendItem(valueNamePair.getName(), valueNamePair);
		sortBy2.appendItem(valueNamePair.getName(), valueNamePair);
		sortBy3.appendItem(valueNamePair.getName(), valueNamePair);
		sortBy4.appendItem(valueNamePair.getName(), valueNamePair);
	} // sortAddItem

	/**
	 *  Query
	 */

	private void actionQuery()
	{
		//  Parameter Info

		StringBuffer para = new StringBuffer();

		//  Reset Selection Data

		accountViewerData.C_AcctSchema_ID = 0;
		accountViewerData.AD_Org_ID = 0;

		//  Save Selection Choices

		Listitem listitem = selAcctSchema.getSelectedItem();

		KeyNamePair keyNamePair = null;

		if (listitem != null)
			keyNamePair = (KeyNamePair)listitem.getValue();

		if (keyNamePair != null)
			accountViewerData.C_AcctSchema_ID = keyNamePair.getKey();

		para.append("C_AcctSchema_ID=").append(accountViewerData.C_AcctSchema_ID);

		listitem = selPostingType.getSelectedItem();

		ValueNamePair valueNamePair = null;

		if (listitem != null)
			valueNamePair = (ValueNamePair)listitem.getValue();
		else
			return;

		accountViewerData.PostingType = valueNamePair.getValue();
		para.append(", PostingType=").append(accountViewerData.PostingType);

		//  Document

		accountViewerData.documentQuery = selDocument.isChecked();
		para.append(", DocumentQuery=").append(accountViewerData.documentQuery);

		if (selDocument.isChecked())
		{
			if (accountViewerData.AD_Table_ID == 0 || accountViewerData.Record_ID == 0)
				return;

			para.append(", AD_Table_ID=").append(accountViewerData.AD_Table_ID)
				.append(", Record_ID=").append(accountViewerData.Record_ID);
		}
		else
		{
			accountViewerData.DateFrom = selDateFrom.getValue() != null
				? new Timestamp(selDateFrom.getValue().getTime()) : null;
			para.append(", DateFrom=").append(accountViewerData.DateFrom);
			accountViewerData.DateTo = selDateTo.getValue() != null
				? new Timestamp(selDateTo.getValue().getTime()) : null;
			para.append(", DateTo=").append(accountViewerData.DateTo);

			listitem = selOrg.getSelectedItem();

			if (listitem != null)
				keyNamePair = (KeyNamePair)listitem.getValue();
			else
				keyNamePair = null;

			if (keyNamePair != null)
				accountViewerData.AD_Org_ID = keyNamePair.getKey();
			para.append(", AD_Org_ID=").append(accountViewerData.AD_Org_ID);
			//
			Iterator<String> it = accountViewerData.whereInfo.values().iterator();
			while (it.hasNext())
				para.append(", ").append(it.next());
		}

		//  Save Display Choices

		accountViewerData.displayQty = displayQty.isChecked();
		para.append(" - Display Qty=").append(accountViewerData.displayQty);
		accountViewerData.displaySourceAmt = displaySourceAmt.isChecked();
		para.append(", Source=").append(accountViewerData.displaySourceAmt);
		accountViewerData.displayDocumentInfo = displayDocumentInfo.isChecked();
		para.append(", Doc=").append(accountViewerData.displayDocumentInfo);

		listitem = sortBy1.getSelectedItem();
		valueNamePair = null;

		if (listitem != null)
		{
			valueNamePair = (ValueNamePair)listitem.getValue();
			if (valueNamePair.getName() != null && valueNamePair.getName().trim().length() > 0)
			{
				accountViewerData.sortBy1 = valueNamePair.getValue();//vp.getName();
				accountViewerData.group1 = group1.isChecked();
				para.append(" - Sorting: ").append(accountViewerData.sortBy1).append("/").append(accountViewerData.group1);
			}
		}

		listitem = sortBy2.getSelectedItem();
		valueNamePair = null;

		if (listitem != null)
		{
			valueNamePair = (ValueNamePair)listitem.getValue();
			if (valueNamePair.getName() != null && valueNamePair.getName().trim().length() > 0)
			{
				accountViewerData.sortBy2 = valueNamePair.getValue();//vp.getName();
				accountViewerData.group2 = group2.isChecked();
				para.append(", ").append(accountViewerData.sortBy2).append("/").append(accountViewerData.group2);
			}
		}

		listitem = sortBy3.getSelectedItem();
		valueNamePair = null;

		if (listitem != null)
		{
			valueNamePair = (ValueNamePair)listitem.getValue();
			if (valueNamePair.getName() != null && valueNamePair.getName().trim().length() > 0)
			{
				accountViewerData.sortBy3 = valueNamePair.getValue();//vp.getName();
				accountViewerData.group3 = group3.isChecked();
				para.append(", ").append(accountViewerData.sortBy3).append("/").append(accountViewerData.group3);
			}
		}

		listitem = sortBy4.getSelectedItem();
		valueNamePair = null;

		if (listitem != null)
		{
			valueNamePair = (ValueNamePair)listitem.getValue();
			if (valueNamePair.getName() != null && valueNamePair.getName().trim().length() > 0)
			{
				accountViewerData.sortBy4 = valueNamePair.getValue();//vp.getName();
				accountViewerData.group4 = group4.isChecked();
				para.append(", ").append(accountViewerData.sortBy4).append("/").append(accountViewerData.group4);
			}
		}

		bQuery.setEnabled(false);
		statusLine.setValue(" " + Msg.getMsg(Env.getCtx(), "Processing"));

		log.config(para.toString());

		//  Switch to Result pane

		tabbedPane.setSelectedIndex(1);

		//  Set TableModel with Query

		RModel model = accountViewerData.query();
		queryData = model.getRows();
		List<ArrayList<Object>> list = null;
		paging.setPageSize(PAGE_SIZE);
		if (queryData.size() > PAGE_SIZE)
		{
			list = queryData.subList(0, PAGE_SIZE);
			paging.setTotalSize(queryData.size());
			pagingPanel.setVisible(true);
		}
		else
		{
			list = queryData;
			paging.setTotalSize(queryData.size());
			pagingPanel.setVisible(false);
		}
		paging.setActivePage(0);

		ListModelTable listmodeltable = new ListModelTable(list);

		if (table.getListhead() == null)
		{
			Listhead listhead = new Listhead();
			listhead.setSizable(true);

			for (int i = 0; i < model.getColumnCount(); i++)
			{
				// Replace user columns with the user selected names
				String displayColumnName = model.getColumnName(i);;
				String columnName;
				RColumn col = model.getColumn(i);
				columnName = col.getColumnName();
				MAcctSchema as = MAcctSchema.get(Env.getCtx(), accountViewerData.C_AcctSchema_ID);
				if (columnName.equals("User1_ID")) {
					MAcctSchemaElement ase = as.getAcctSchemaElement(MAcctSchemaElement.ELEMENTTYPE_UserList1);
					if (ase != null)
						displayColumnName = Msg.translate(Env.getCtx(), ase.getName());					
				}
				else if (columnName.equals("User2_ID")) {
					MAcctSchemaElement ase = as.getAcctSchemaElement(MAcctSchemaElement.ELEMENTTYPE_UserList2);
					if (ase != null)
						displayColumnName = Msg.translate(Env.getCtx(), ase.getName());					
				}
				else if (columnName.equals("User3_ID")) {
					MAcctSchemaElement ase = as.getAcctSchemaElement(MAcctSchemaElement.ELEMENTTYPE_UserList3);
					if (ase != null)
						displayColumnName = Msg.translate(Env.getCtx(), ase.getName());
				}
				else if (columnName.equals("User4_ID")) {
					MAcctSchemaElement ase = as.getAcctSchemaElement(MAcctSchemaElement.ELEMENTTYPE_UserList4);
					if (ase != null)
						displayColumnName = Msg.translate(Env.getCtx(), ase.getName());
				}
				Listheader listheader = new Listheader(displayColumnName);
				listheader.setTooltiptext(model.getColumnName(i));
				listhead.appendChild(listheader);
			}
			table.appendChild(listhead);
		}
		// Elaine 2008/07/28
		else
		{
			Listhead listHead = table.getListhead();

			// remove existing column header
			listHead.getChildren().clear();

			// add in new column header
			for (int i = 0; i < model.getColumnCount(); i++)
			{
				Listheader listheader = new Listheader(model.getColumnName(i));
				listHead.appendChild(listheader);
			}
		}
		//

		table.getItems().clear();

		table.setItemRenderer(new WListItemRenderer());
		table.setModel(listmodeltable);

		resultPanel.invalidate();

		bQuery.setEnabled(true);
		statusLine.setValue(" " + Msg.getMsg(Env.getCtx(), "ViewerOptions"));
	}   //  actionQuery

	/**
	 *  Document selection
	 */

	private void actionDocument()
	{
		boolean isDocumentSelected = selDocument.isChecked();
		selTable.setEnabled(isDocumentSelected);
		selRecord.setEnabled(isDocumentSelected);
		//
		selDateFrom.setEnabled(!isDocumentSelected);
		selDateTo.setEnabled(!isDocumentSelected);
		selOrg.setEnabled(!isDocumentSelected);
		selAcct.setEnabled(!isDocumentSelected);
		sel1.setEnabled(!isDocumentSelected);
		sel2.setEnabled(!isDocumentSelected);
		sel3.setEnabled(!isDocumentSelected);
		sel4.setEnabled(!isDocumentSelected);
		sel5.setEnabled(!isDocumentSelected);
		sel6.setEnabled(!isDocumentSelected);
		sel7.setEnabled(!isDocumentSelected);
		sel8.setEnabled(!isDocumentSelected);
	} // actionDocument

	/**
	 *  Save Table selection (reset Record selection)
	 */

	private void actionTable()
	{
		Listitem listitem = selTable.getSelectedItem();
		ValueNamePair valueNamePair = null;

		if (listitem != null)
			valueNamePair = (ValueNamePair)listitem.getValue();
		else
			return;

		accountViewerData.AD_Table_ID = ((Integer) accountViewerData.tableInfo.get(valueNamePair.getValue())).intValue();
		log.config(valueNamePair.getValue() + " = " + accountViewerData.AD_Table_ID);

		//  Reset Record

		accountViewerData.Record_ID = 0;
		selRecord.setLabel("");
		selRecord.setName(valueNamePair.getValue() + "_ID");
	} // actionTable

	/**
	 *  Action Button
	 *
	 *  @param button pressed button
	 *  @return ID
	 * @throws Exception
	 */

	private int actionButton(Button button) throws Exception
	{
		String keyColumn = button.getName();
		log.info(keyColumn);
		// String whereClause = ""; // Elaine 2008/07/28
		String whereClause = "(IsSummary='N' OR IsSummary IS NULL)";
		String lookupColumn = keyColumn;
		int recordId = accountViewerData.getButtonRecordID(keyColumn);
		if ("Account_ID".equals(keyColumn))
		{
			lookupColumn = "C_ElementValue_ID";
			MAcctSchemaElement ase = accountViewerData.ASchema
				.getAcctSchemaElement(X_C_AcctSchema_Element.ELEMENTTYPE_Account);

			if (ase != null)
				whereClause += " AND C_Element_ID=" + ase.getC_Element_ID();
		}
		else if ("User1_ID".equals(keyColumn))
		{
			lookupColumn = "C_ElementValue_ID";
			MAcctSchemaElement ase = accountViewerData.ASchema
				.getAcctSchemaElement(X_C_AcctSchema_Element.ELEMENTTYPE_UserList1);

			if (ase != null)
				whereClause += " AND C_Element_ID=" + ase.getC_Element_ID();
		}
		else if ("User2_ID".equals(keyColumn))
		{
			lookupColumn = "C_ElementValue_ID";
			MAcctSchemaElement ase = accountViewerData.ASchema
				.getAcctSchemaElement(X_C_AcctSchema_Element.ELEMENTTYPE_UserList2);

			if (ase != null)
				whereClause += " AND C_Element_ID=" + ase.getC_Element_ID();
		}
		else if ("User3_ID".equals(keyColumn))
		{
			lookupColumn = "C_ElementValue_ID";
			MAcctSchemaElement ase = accountViewerData.ASchema
					.getAcctSchemaElement(X_C_AcctSchema_Element.ELEMENTTYPE_UserList3);

			if (ase != null)
				whereClause += " AND C_Element_ID=" + ase.getC_Element_ID();
		}
		else if ("User4_ID".equals(keyColumn))
		{
			lookupColumn = "C_ElementValue_ID";
			MAcctSchemaElement ase = accountViewerData.ASchema
					.getAcctSchemaElement(X_C_AcctSchema_Element.ELEMENTTYPE_UserList4);

			if (ase != null)
				whereClause += " AND C_Element_ID=" + ase.getC_Element_ID();
		}
		else if ("UserElement1_ID".equals(keyColumn))
		{
			MAcctSchemaElement et = new Query(Env.getCtx(), MAcctSchemaElement.Table_Name, 
					"elementtype = 'X1' ", 
					null)
					.setClient_ID()
					.firstOnly();
			String tableName = et.getAD_Column().getAD_Table().getTableName();
			String lookupcolumnname = tableName + "_ID";
			lookupColumn = lookupcolumnname;
			whereClause = "";
		}
		else if ("UserElement2_ID".equals(keyColumn))
		{
			MAcctSchemaElement et = new Query(Env.getCtx(), MAcctSchemaElement.Table_Name, 
					"elementtype = 'X2' ", 
					null)
					.setClient_ID()
					.firstOnly();
			String tableName = et.getAD_Column().getAD_Table().getTableName();
			String lookupcolumnname = tableName + "_ID";
			lookupColumn = lookupcolumnname;
			whereClause = "";
		}
		else if (keyColumn.equals("M_Product_ID"))
		{
			whereClause = "";
		}
		else if (selDocument.isChecked())
			whereClause = "";

		if (button == selRecord)                            //  Record_ID
			recordId = accountViewerData.Record_ID;
		else
			recordId = accountViewerData.getButtonRecordID(keyColumn);
		
		String tableName = lookupColumn.substring(0, lookupColumn.length()-3);
		//whereClause = tableName + ".IsSummary='N'" + whereClause; // Elaine 2008/07/28

		//  Open modal
		InfoPanel info = InfoPanel.create(accountViewerData.WindowNo, tableName, lookupColumn, recordId, "", false, whereClause);

		if (!info.loadedOK())
		{
			info.dispose();
			info = null;
			button.setLabel("");
			accountViewerData.whereInfo.put(keyColumn, "");
			accountViewerData.buttonRecordID.put(keyColumn, null);
			return 0;
		}

		info.setVisible(true);
		AEnv.showWindow(info);

   		boolean isCancelled = info.isCancelled();
		boolean isOK = info.isOk();
		Integer key = 0;
		
		if (isCancelled && !isOK) // Delete the saved info
		{
			key = 0;
			if (button == selRecord)                            //  Record_ID
				accountViewerData.Record_ID = key.intValue();
			else
			{
				accountViewerData.whereInfo.put(keyColumn, "");    //  no query
				accountViewerData.buttonRecordID.put(keyColumn, key.intValue());
			}
			button.setLabel("");
		}
		else if(!isCancelled && isOK)
		{
			//  Save for query
			String selectSQL = info.getSelectedSQL();       //  C_Project_ID=100 or ""
			key = (Integer)info.getSelectedKey();
			log.config(keyColumn + " - " + key);
			if (button == selRecord)                            //  Record_ID
				accountViewerData.Record_ID = key.intValue();
			else
			{
				accountViewerData.whereInfo.put(keyColumn, keyColumn + "=" + key.intValue());  //  Add to query
				accountViewerData.buttonRecordID.put(keyColumn, key.intValue());
			}
			//  Display Selection and resize
			button.setLabel(accountViewerData.getButtonText(tableName, lookupColumn, selectSQL));
		}
		else if(!(isCancelled ^ isOK)) // xor: window closed or error - no change
		{
			// accountViewerData not changed
			if (button == selRecord)                            //  Record_ID
				key = accountViewerData.Record_ID = key.intValue();
			else
				key = accountViewerData.getButtonRecordID(keyColumn);
		}
		info = null;
		return key.intValue();

	} // actionButton

	/**
	 *  Action Zoom
	 */
	private void actionZoom()
	{
		if (displayDocumentInfo.isChecked() && table.getSelectedRow() >= 0) {
			RModel model = accountViewerData.getRModel();
			int columnTableId = model.getColumnIndex("AD_Table_ID");
			int columnRecordId =model.getColumnIndex("Record_ID");
			if (columnTableId >=0 && columnRecordId >= 0) {
				KeyNamePair tableKeyPair = (KeyNamePair) table.getValueAt(table.getSelectedRow(), columnTableId);
				Integer recordId = (Integer) table.getValueAt(table.getSelectedRow(), columnRecordId);
				if (tableKeyPair != null && recordId != null)
					AEnv.zoom(tableKeyPair.getKey(), recordId);
			}
		}


	} // actionRePost

	/**
	 *  RePost Record
	 */
	private void actionRePost()
	{
		if (accountViewerData.documentQuery
			&& accountViewerData.AD_Table_ID != 0 && accountViewerData.Record_ID != 0
			&& FDialog.ask(accountViewerData.WindowNo, this, "PostImmediate?"))
		{
			//setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			boolean force = forcePost.isChecked();
			String error = AEnv.postImmediate (accountViewerData.WindowNo, accountViewerData.AD_Client_ID,
				accountViewerData.AD_Table_ID, accountViewerData.Record_ID, force);
			//setCursor(Cursor.getDefaultCursor());
			if (error != null)
				FDialog.error(0, this, "PostingError-N", error);

			actionQuery();
		}
	} // actionRePost

	/**
	 * Determine if the window is a lookup (modal) or not
	 * @return boolean. True if modal.
	 */
	public boolean isLookup()
	{
		return isLookup;
	}
	
	//FR[3435028]
	/**
	 * Export to Excel
	 */
	private void actionExportExcel() {
		RModel model = accountViewerData.query();
		if (model == null) {

			return;
		}
		try {
			String path = System.getProperty("java.io.tmpdir");
			String prefix = makePrefix(this.getTitle());  // getTitle will be null for embedded windows.
			if (prefix.equals(""))
				prefix = makePrefix(Msg.getMsg(Env.getCtx(), "InfoAccount"));
			
			if (log.isLoggable(Level.FINE))
			{
				log.log(Level.FINE, "Path="+path + " Prefix="+prefix);
			}
			File file = File.createTempFile(prefix, ".xls", new File(path));
			
			RModelExcelExporter exporter = new RModelExcelExporter((RModel)model);
			exporter.export(file, null, false);
			//AMedia media = new AMedia(getTitle(), "xls", "application/msexcel", file, true);
			//Filedownload.save(media, getTitle() + "." + "xls");
			Filedownload.save(file,"application/msexcel");
		}
		catch (Exception e) {
			FDialog.error(0, this, "LoadError", e.getLocalizedMessage());
			if (CLogMgt.isLevelFinest()) e.printStackTrace();
		}
	}
	//FR[3435028]
	private String makePrefix(String name) {
		StringBuffer prefix = new StringBuffer();
		char[] nameArray = name.toCharArray();
		for (char ch : nameArray) {
			if (Character.isLetterOrDigit(ch)) {
				prefix.append(ch);
			} else {
				prefix.append("_");
			}
		}
		return prefix.toString();
	}
}
