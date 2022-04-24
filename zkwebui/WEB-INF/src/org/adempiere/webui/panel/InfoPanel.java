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

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.apps.BusyDialog;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.Combobox;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListModelTable;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.WListItemRenderer;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.editor.WPAttributeEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.exceptions.ValueChangeEvent;
import org.adempiere.exceptions.ValueChangeListener;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.event.WTableModelListener;
import org.adempiere.webui.part.ITabOnSelectHandler;
import org.adempiere.webui.session.SessionManager;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MRole;
import org.compiere.model.MSysConfig;
import org.compiere.model.MTable;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.zkforge.keylistener.Keylistener;
import org.zkoss.zk.au.out.AuEcho;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.KeyEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zkex.zul.West;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelExt;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Separator;
import org.zkoss.zul.event.ZulEvents;

/**
 *	Search Information and return selection - Base Class.
 *  Based on Info written by Jorg Janke
 *  
 *  @author Sendy Yagambrum
 *  
 * Zk Port
 * @author Elaine
 * @version	Info.java Adempiere Swing UI 3.4.1
 *
 * @author Michael McKay, ADEMPIERE-72 VLookup and Info Window improvements
 * 	<li>https://adempiere.atlassian.net/browse/ADEMPIERE-72
 */
public abstract class InfoPanel extends Window implements EventListener, WTableModelListener, ListModelExt
{
	
	/**
	 * generated serial version ID
	 */
	private static final long serialVersionUID = 325050327514511004L;
	private final static int PAGE_SIZE = 100;
	
    public static InfoPanel create (int WindowNo,
            String tableName, String keyColumn, int record_id, String value,
            boolean multiSelection, String whereClause)
    {
    	return create(WindowNo, true, tableName, keyColumn, record_id, value,
            multiSelection, true, whereClause);
    }
    
    public static InfoPanel create (int WindowNo, boolean modal,
            String tableName, String keyColumn, int record_id, String value,
            boolean multiSelection, boolean saveResult, String whereClause)
        {
    	
            InfoPanel info = null;

            if (tableName.equals("C_BPartner"))
                info = new InfoBPartnerPanel (WindowNo, modal, record_id, value, 
                				!Env.getContext(Env.getCtx(),"IsSOTrx").equals("N"), false,
                				multiSelection, saveResult, whereClause);
            else if (tableName.equals("M_Product"))
                info = new InfoProductPanel ( WindowNo, modal, 0, 0, 
                        record_id, value, multiSelection, saveResult, whereClause);
            else if (tableName.equals("C_Invoice"))
                info = new InfoInvoicePanel ( WindowNo, modal, record_id, value,
                        multiSelection, saveResult, whereClause);
            else if (tableName.equals("A_Asset"))
                info = new InfoAssetPanel (WindowNo, modal, record_id, value,
                        multiSelection, saveResult, whereClause);
            else if (tableName.equals("C_Order"))
                info = new InfoOrderPanel ( WindowNo, modal, record_id, value,
                        multiSelection, saveResult, whereClause);
            else if (tableName.equals("M_InOut"))
                info = new InfoInOutPanel (WindowNo, modal, record_id, value,
                        multiSelection, saveResult, whereClause);
            else if (tableName.equals("C_Payment"))
                info = new InfoPaymentPanel (WindowNo, modal, record_id, value, multiSelection, saveResult, whereClause);
            else if (tableName.equals("C_CashLine"))
               info = new InfoCashLinePanel (WindowNo, modal, record_id, value,
                        multiSelection, saveResult, whereClause);
            else if (tableName.equals("S_ResourceAssigment"))
                info = new InfoAssignmentPanel (WindowNo, modal, record_id, value,
                        multiSelection, saveResult, whereClause);
            else
                info = new InfoGeneralPanel (WindowNo, modal, record_id, value,  
                    tableName, keyColumn, multiSelection, saveResult, whereClause);
            //
            return info;
    
        }
    
	/**
	 * Show BPartner Info (non modal)
	 * @param WindowNo window no
	 */
	public static void showBPartner (int WindowNo)
	{
		InfoBPartnerPanel info = new InfoBPartnerPanel (WindowNo, false, 0, "", 
			true, false, true, false, "");
		AEnv.showWindow(info);
	}   //  showBPartner

	/**
	 * Show Asset Info (non modal)
	 * @param frame Parent Frame
	 * @param WindowNo window no
	 */
	public static void showAsset (int WindowNo)
	{
		InfoPanel info = new InfoAssetPanel (WindowNo, false, 0, "", false, false, "");
		AEnv.showWindow(info);
	}   //  showBPartner

	/**
	 * Show Product Info (non modal)
	 * @param frame Parent Frame
	 * @param WindowNo window no
	 */
	public static void showProduct (int WindowNo)
	{
		InfoPanel info = new InfoProductPanel(WindowNo, false, 
				Env.getContextAsInt(Env.getCtx(), WindowNo, "M_Warehouse_ID"),
				Env.getContextAsInt(Env.getCtx(), WindowNo, "M_PriceList_ID"),
				0, "", false, false, "");
		AEnv.showWindow(info);
	}   //  showProduct
	
	/**
	 * Show Order Info (non modal)
	 * @param frame Parent Frame
	 * @param WindowNo window no
	 * @param value query value
	 */
	public static void showOrder (int WindowNo, String value)
	{
		InfoPanel info = new InfoOrderPanel(WindowNo, false, 0, value, false, false, "");
		AEnv.showWindow(info);
	}   //  showOrder

	/**
	 * Show Invoice Info (non modal)
	 * @param frame Parent Frame
	 * @param WindowNo window no
	 * @param value query value
	 */
	public static void showInvoice (int WindowNo, String value)
	{
		InfoPanel info = new InfoInvoicePanel(WindowNo, false, 0, value, false, false, "");
		AEnv.showWindow(info);
	}   //  showInvoice

	/**
	 * Show Shipment Info (non modal)
	 * @param frame Parent Frame
	 * @param WindowNo window no
	 * @param value query value
	 */
	public static void showInOut (int WindowNo, String value)
	{
		InfoPanel info = new InfoInOutPanel (WindowNo, false, 0, value,
			false, false, "");
		AEnv.showWindow(info);
	}   //  showInOut

	/**
	 * Show Payment Info (non modal)
	 * @param frame Parent Frame
	 * @param WindowNo window no
	 * @param value query value
	 */
	public static void showPayment (int WindowNo, String value)
	{
		InfoPanel info = new InfoPaymentPanel (WindowNo, false, 0, value,
			false, false, "");
		AEnv.showWindow(info);
	}   //  showPayment

	/**
	 * Show Cash Line Info (non modal)
	 * @param frame Parent Frame
	 * @param WindowNo window no
	 * @param value query value
	 */
	public static void showCashLine (int WindowNo, String value)
	{
		InfoPanel info = new InfoCashLinePanel (WindowNo, false, 0, value,
			false, false, "");
		AEnv.showWindow(info);
	}   //  showCashLine

	/**
	 * Show Assignment Info (non modal)
	 * @param frame Parent Frame
	 * @param WindowNo window no
	 * @param value query value
	 */
	public static void showAssignment (int WindowNo, String value)
	{
		InfoPanel info = new InfoAssignmentPanel (WindowNo, false, 0, value,
			false, false, "");
		AEnv.showWindow(info);
	}   //  showAssignment

	/** Window Width                */
	static final int        INFO_WIDTH = 800;
	private boolean m_modal;

	/**************************************************
     *  Detail Constructor 
     * @param WindowNo  WindowNo
     * @param tableName tableName
     * @param keyColumn keyColumn   
     * @param whereClause   whereClause
	 */
	protected InfoPanel (int WindowNo,
		String tableName, String keyColumn, boolean multipleSelection,
		 String whereClause)
	{
		this(WindowNo, true, tableName, keyColumn, multipleSelection, true, whereClause);
	}
		
	/**************************************************
     *  Detail Constructor
     * @param WindowNo  WindowNo
     * @param modal
     * @param tableName tableName
     * @param keyColumn keyColumn
	 * @param saveResults flag if the results will be saved in context
     * @param whereClause   whereClause
	 */
	protected InfoPanel (int WindowNo, boolean modal,
		String tableName, String keyColumn, boolean multipleSelection, boolean saveResults,
		 String whereClause)
	{

		log.info("WinNo=" + p_WindowNo + " " + whereClause);
		p_WindowNo = WindowNo;
		p_tableName = tableName;
		p_keyColumn = keyColumn;
        p_multipleSelection = multipleSelection;
        p_saveResults = saveResults;
        m_modal = modal;
		//
        p_TabNo = 0;
		p_height = SessionManager.getAppDesktop().getClientInfo().desktopHeight * 90 / 100;
		p_width = SessionManager.getAppDesktop().getClientInfo().desktopWidth * 80 / 100;

		init();
		//
		if (whereClause == null || whereClause.indexOf('@') == -1)
			p_whereClause = whereClause;
		else
		{
			p_whereClause = Env.parseContext(Env.getCtx(), p_WindowNo, whereClause, false, false);
			if (p_whereClause.length() == 0)
				log.log(Level.SEVERE, "Cannot parse context= " + whereClause);
		}
		
		this.setAttribute(ITabOnSelectHandler.ATTRIBUTE_KEY, new ITabOnSelectHandler() {
			public void onSelect() {
				scrollToSelectedRow();
			}
			
		});
		
		p_table.addActionListener(new EventListener() {
			public void onEvent(Event event) throws Exception {

				if (p_table.getRowCount() == 0)
				{
					enableButtons();		
					return;
				}
				//
				
				if (event.getName().equals("onSelect"))
				{
					SelectEvent se = ((SelectEvent) event);
					setNumRecordsSelected(se.getSelectedItems().size());
					recordSelected(p_table.getLeadRowKey());
					p_selectedRecordKey = p_table.getLeadRowKey();
				}

				enableButtons();		
			}
		});
		
		p_table.getModel().addTableModelListener(this);

	}	//	InfoPanel
	
	/**
	 *  Called to set the sizes of the layout after the children are loaded.
	 */
	protected void setSizes()
	{
		//  TODO this can be removed if Zk is upgraded to 5+.  Use vflex=min for all layout areas except 
		//  the p_centerCenter which should fill the remaining space.
		// Have to set the criteriaGrid height specifically.  58 is the height of the reset button and label.
		// p_criteriaGrid is assumed to hold a Rows component that is non null and has children.
		int rowHeight = (30*((Rows) p_criteriaGrid.getFirstChild()).getChildren().size());
		rowHeight = rowHeight > 58 ? rowHeight : 58;
		p_northLayout.setHeight(rowHeight + "px");
		p_southLayout.setHeight("70px");
		
		if (p_centerNorth.getChildren().size() == 0)
		{
			p_centerNorth.detach();
		}
		else
		{
			p_centerNorth.setHeight("25px");
		}
		
		if (p_centerSouth.getChildren().size() > 0)
		{
			int detailHeight = (p_height * 25 / 100);
			p_centerSouth.setHeight(detailHeight + "px");
		}
		else
		{
			p_centerSouth.detach();
		}

	}
	
	protected void init()
	{
		if (isModal())
		{
			setAttribute(Window.MODE_KEY, Window.MODE_MODAL);
			setBorder("normal");
			setClosable(true);
			setWidth(p_width + "px");
			setHeight(p_height + "px");
    		setContentStyle("overflow: auto");
            setSizable(true);      
            setMaximizable(true);        
		}
		else
		{
			setAttribute(Window.MODE_KEY, Window.MODE_EMBEDDED);
			setBorder("none");
			setWidth("100%");
			setHeight("100%");
			setStyle("position: absolute");
		}
		
        confirmPanel = new ConfirmPanel(true, true, false, true, true, true);  // Elaine 2008/12/16
        confirmPanel.addActionListener(Events.ON_CLICK, this);
        confirmPanel.setStyle("border-top: 2px; border-bottom: 2px; padding: 4px");
        
        // Elaine 2008/12/16
		confirmPanel.getButton(ConfirmPanel.A_CUSTOMIZE).setVisible(hasCustomize());
		confirmPanel.getButton(ConfirmPanel.A_HISTORY).setVisible(hasHistory());
		confirmPanel.getButton(ConfirmPanel.A_ZOOM).setVisible(hasZoom());		
		confirmPanel.getButton(ConfirmPanel.A_OK).setVisible(p_saveResults);

		checkAutoQuery.setText(Msg.getMsg(Env.getCtx(), "AutoRefresh"));
		checkAutoQuery.setTooltip(Msg.getMsg(Env.getCtx(), "AutoRefresh"));
		checkAutoQuery.setName("AutoQuery");
		checkAutoQuery.setSelected(MSysConfig.getValue(SYSCONFIG_INFO_AUTO_QUERY,"Y",Env.getAD_Client_ID(Env.getCtx())).equals("Y"));  
		checkAutoQuery.setAttribute("zk_component_ID", "Lookup_Confirm_checkAutoQuery");
		checkAutoQuery.addActionListener(this);
		confirmPanel.getButton(ConfirmPanel.A_REFRESH).getParent().insertBefore(checkAutoQuery, confirmPanel.getButton(ConfirmPanel.A_REFRESH));
		confirmPanel.getButton(ConfirmPanel.A_REFRESH).getParent().insertBefore(new Separator("vertical"), confirmPanel.getButton(ConfirmPanel.A_REFRESH));
		//
        
		statusBar.setEastVisibility(false);
		statusBar.setAttribute("zk_component_ID", "info_statusBar");
		//
		Center center = new Center();
		center.appendChild(confirmPanel);
		p_southLayout.appendChild(center);
		South south = new South();
		south.appendChild(statusBar);
		p_southLayout.appendChild(south);
		
		//
		// Reset button
		bReset = confirmPanel.createButton(ConfirmPanel.A_RESET);
		bReset.addActionListener(this);
		lblReset = new Label();
		lblReset.setValue(Util.cleanAmp(Msg.translate(Env.getCtx(), "Reset")));

        p_table.setOddRowSclass(null);
        p_table.setAttribute("zk_component_ID", "Lookup_Data_SearchResults");        
        p_table.setVflex(true);
        
        p_centerLayout.setWidth("100%");
        //p_centerLayout.setHeight("100%");
        if (isModal())
        	p_centerLayout.setStyle("border: none; position: relative");
        else
        	p_centerLayout.setStyle("border: none; position: absolute");

		p_centerLayout.appendChild(p_centerNorth);  // May be empty
		p_centerLayout.appendChild(p_centerCenter); // the table
		p_centerLayout.appendChild(p_centerSouth);  // detail tabs or other
        //
		Div div = new Div();  // Need to use a container for p_table so we can insert paging if required.
		div.appendChild(p_table);
		div.setStyle("width :100%; height: 100%");
		p_centerCenter.appendChild(div);
		p_centerCenter.setAutoscroll(false);
        p_centerCenter.setFlex(true);
		//
		p_centerSouth.setCollapsible(true);
		p_centerSouth.setSplittable(true);
		p_centerSouth.setFlex(true);

		//  Setup the north reset button and criteria grid
		West spWest = new West();
		spWest.setBorder("0");
		Center spCenter = new Center();
		spCenter.setBorder("0");

		p_northLayout.setWidth("");
		p_northLayout.appendChild(spWest);
		p_northLayout.appendChild(spCenter);
		// spWest - the reset button
		Grid bGrid = GridFactory.newGridLayout();
		Rows bRows = new Rows();
		Row bRow = new Row();
		bGrid.appendChild(bRows);
		bRows.appendChild(bRow);
		bRow.appendChild(bReset);
		bRow = new Row();
		bRows.appendChild(bRow);
		bRow.appendChild(lblReset);
		spWest.appendChild(bGrid);
		
		// The criteria table
		spCenter.appendChild(p_criteriaGrid);

        Borderlayout mainPanel = new Borderlayout();
        mainPanel.setWidth("100%");
        mainPanel.setHeight("100%");
        //
        North north = new North();
        mainPanel.appendChild(north);
        north.appendChild(p_northLayout);
        //
        center = new Center();
        mainPanel.appendChild(center);
        center.appendChild(p_centerLayout);
        //
        south = new South();
        mainPanel.appendChild(south);
        south.appendChild(p_southLayout);
        //
        if (!isModal())
        {
        	mainPanel.setStyle("position: absolute");
        }
		this.appendChild(mainPanel);
        this.addEventListener(Events.ON_OK, this);
        this.setVisible(true);
        
        // Add Key Events
        keyListener = new Keylistener();
		
		keyListener.setCtrlKeys("#enter");
		keyListener.addEventListener(Events.ON_CTRL_KEY, this);
		addEventListener(Events.ON_CANCEL, this);
		appendChild(keyListener);
        
	}  //  init
	
	private static String SYSCONFIG_INFO_AUTO_WILDCARD = "INFO_AUTO_WILDCARD";
	private static String SYSCONFIG_INFO_AUTO_QUERY = "INFO_AUTO_QUERY";
	
	protected ConfirmPanel confirmPanel;
	protected Borderlayout p_northLayout = new Borderlayout();
	protected Borderlayout p_centerLayout = new Borderlayout();
	protected Borderlayout p_southLayout = new Borderlayout();
    protected North p_centerNorth = new North();
	protected Center p_centerCenter = new Center();
	protected South p_centerSouth = new South();
	protected Grid p_criteriaGrid = GridFactory.newGridLayout();

	protected int p_height;
	protected int p_width;

	private Button bReset;
	private Label lblReset = new Label();

	/** Master (owning) Window  */
	protected int				p_WindowNo;
	/** Tab No to limit context */
	protected int				p_TabNo;
	/** Table Name              */
	protected String            p_tableName;
	/** Key Column Name         */
	protected String            p_keyColumn;
	/** Enable more than one selection  */
	protected boolean			p_multipleSelection;
	/** Initial WHERE Clause    */
	protected String			p_whereClause = "";
	/** Concrete WHERE Clause - used by concrete classes  */
	protected String			p_concreteWhereClause = "";
	protected StatusBarPanel statusBar = new StatusBarPanel();
	/**                    */
    private List<Object> line;
	/** Tracking for previously selected record				*/
	protected int 				p_selectedRecordKey = 0;
    /** A refresh of the data is required. Default true (1st time always) */
	protected boolean 			p_triggerRefresh = true;

    /** Perform a refresh now */
    protected boolean 			p_refreshNow = false;
	/** Will the results of the search be saved?	*/
	protected boolean 			p_saveResults = true;
	/** Does the layout need to be rebuilt. True by default (1st time always) */
	protected boolean 			p_resetColumns = true;

	private boolean			    m_ok = false;
	/** Cancel pressed - need to differentiate between OK - Cancel - Exit	*/
	private boolean			    m_cancel = false;
	/** Reset the record ID - false on load, reset by any action that reruns the query   */
	protected boolean				m_resetRecordID = false;
	/** Result IDs              */
	private ArrayList<Integer>	m_results = new ArrayList<Integer>(3);
    
    private ListModelTable model;
	/** Layout of Grid          */
	protected ColumnInfo[]     p_layout;
	/** SQL FROM Clause          */
	protected String		    p_sqlFrom;
	/** SQL Where Clause          */
	protected String		    p_sqlWhere;
	/** SQL Where Clause          */
	protected String		    p_sqlOrder;
	/** Main SQL Statement      */
	private String              m_sqlMain;
	/** Count SQL Statement		*/
	private String              m_sqlCount;
	/** Order By Clause         */
	private String              m_sqlOrder;
	private String              m_sqlUserOrder;
	/**ValueChange listeners       */
    private ArrayList<ValueChangeListener> listeners = new ArrayList<ValueChangeListener>();
	/** Loading success indicator       */
	protected boolean	        p_loadedOK = false;
	/**	SO Zoom Window						*/
	private int					m_SO_Window_ID = -1;
	/**	PO Zoom Window						*/
	private int					m_PO_Window_ID = -1;
	private Keylistener keyListener = new Keylistener();
	/**	Logger			*/
	protected CLogger log = CLogger.getCLogger(getClass());
	
	protected WListbox p_table = new WListbox();
	protected Checkbox checkAutoQuery = new Checkbox();
	protected Paging paging;
	protected int pageNo;
	private int m_count;
	private int cacheStart;
	private int cacheEnd;
	private boolean m_useDatabasePaging = false;
	private BusyDialog progressWindow;
	private boolean m_showTotals;
	
	/**  record the numbe of selected records in a multi-selection event */
	protected int p_numRecordsSelected = 0;
	private boolean m_busy = false;

	private static final String[] lISTENER_EVENTS = {};
	
	private static final int KEYBOARD_KEY_RETURN = 13;
	/**
	 *  Loaded correctly
	 *  @return true if loaded OK
	 */
	public boolean loadedOK()
	{
		return p_loadedOK;
	}   //  loadedOK

	/**
	 *	Set Status Line
	 *  @param text text
	 *  @param error error
	 */
	public void setStatusLine (String text, boolean error)
	{
		statusBar.setStatusLine(text, error);
	}	//	setStatusLine

	/**
	 *	Set Status DB
	 *  @param text text
	 */
	public void setStatusDB (String text)
	{
		statusBar.setStatusDB(text);
	}	//	setStatusDB
	
	protected void prepareTable (ColumnInfo[] layout, 
            String from, 
            String where, 
            String orderBy)
	{
	//  For dynamic columns, we need to wipe the table.
		if (p_resetColumns)
		{
			p_table.clear();
			//  Prevent repeats
			p_resetColumns = false;
		}
        String sql =p_table.prepareTable(layout, from,
                where,p_multipleSelection && m_modal,
                getTableName(),false);
        p_layout = p_table.getLayout();
		m_sqlMain = sql;
		m_sqlCount = "SELECT COUNT(*) FROM " + from + " WHERE " + where;
		//
		m_sqlOrder = "";
		m_sqlUserOrder = "";
		if (orderBy != null && orderBy.length() > 0)
			m_sqlOrder = " ORDER BY " + orderBy;			
	}   //  prepareTable

	/**
     *  Set the selected row to a particular key if found
     *  @returns true if successful
     */
    protected boolean setSelectedRow(int record_id)
    {
        if (p_table == null)
        {
        	return false;
        }

        // Is there a key column?
        if (p_table.getKeyColumnIndex() == -1)
        {
            return false;
        }
        
    	// If the query is empty, return
        if (p_table.getRowCount() == 0)
        {
            return false;
        }

		if (p_table.isMultiSelection() && p_table.isDefaultSelected()) // Select all by default
		{
			return false; // All rows will be selected by default
		}

        if (record_id <= 0)
        {
        	//  Select the first record
        	p_table.addItemToSelection(p_table.getItemAtIndex(0));
        	log.fine("Selected the first record shown");
        	return true;
        }
        
        //  Is the record already selected?
        Integer selectedKey = (Integer) getSelectedKey();
        if(selectedKey != null && selectedKey.intValue() == record_id)  //  We're already there
        	return true;
        
        //  Nothing or the wrong row selected - try to find the record in the table
    	int rows = p_table.getRowCount();
    	
    	//  Ignore the total row
        if (p_table.getShowTotals())
        	rows = rows - 1;

    	for (int row = 0; row < rows; row++)
        {
            Object data = p_table.getModel().getValueAt(row, p_table.getKeyColumnIndex());
            if (data instanceof IDColumn)
            {
                IDColumn dataColumn = (IDColumn)data;
                if (dataColumn.getRecord_ID() == record_id)
                {
                	p_table.addItemToSelection(p_table.getItemAtIndex(row));
                	log.fine("Record_ID = " + record_id + " found at row " + row);
                	return true;
                }
            }
        }
    	
    	//  record_id not found in the current list.  Select the first shown.
    	p_table.addItemToSelection(p_table.getItemAtIndex(0));
    	log.fine("Record_ID = " + record_id + " not found in the current table. Selecting the first record.");
        return true;
        
    }   //  setSelectedRow

	/**
	 *	autoQuery?
	 *	- yes if true
	 *  @return true for automatic queries, else must refresh
	 */
	public boolean autoQuery()
	{
		return checkAutoQuery.isSelected();
	}	//	autoQuery

	/**
	 *  Prepare and Execute the query
	 */
	protected void prepareAndExecuteQuery()
	{
		m_busy = true;
    	showBusyDialog();
    	Clients.response(new AuEcho(this, "onQueryCallback", null));
	}
	/**************************************************************************
	 *  Execute Query
	 */
	private void executeQuery()
	{
		line = new ArrayList<Object>();
		cacheStart = -1;
		cacheEnd = -1;

		testCount();
		m_useDatabasePaging = (m_count > 1000);
		if (m_useDatabasePaging)
		{			
			return ;
		}
		else
		{
			readLine(0, -1);
		}
	}
            
	private void readData(ResultSet rs) throws SQLException {
		int colOffset = 1;  //  columns start with 1
		List<Object> data = new ArrayList<Object>();
		for (int col = 0; col < p_layout.length; col++)
		{
			Object value = null;
			Class<?> c = p_layout[col].getColClass();
			int colIndex = col + colOffset;
			if (c == IDColumn.class)
			{
		        value = new IDColumn(rs.getInt(colIndex));
						
			}
			else if (c == Boolean.class)
		        value = Boolean.valueOf("Y".equals(rs.getString(colIndex)));
			else if (c == Timestamp.class)
		        value = rs.getTimestamp(colIndex);
			else if (c == BigDecimal.class)
		        value = rs.getBigDecimal(colIndex);
			else if (c == Double.class)
		        value = new Double(rs.getDouble(colIndex));
			else if (c == Integer.class)
		        value = Integer.valueOf(rs.getInt(colIndex));
			else if (c == KeyNamePair.class)
			{
				String display = rs.getString(colIndex);
				int key = rs.getInt(colIndex+1);
                value = new KeyNamePair(key, display);

				colOffset++;
			}
			else
			{
		        value = rs.getString(colIndex);
			}
			data.add(value);
		}
        line.add(data);
	}
            
    protected void renderItems()
    {
        if (m_count > 0)
        {
        	if (m_count > PAGE_SIZE)
        	{
        		if (paging == null) 
        		{
	        		paging = new Paging();
	    			paging.setPageSize(PAGE_SIZE);
	    			paging.setTotalSize(m_count);
	    			paging.setDetailed(true);
	    			paging.addEventListener(ZulEvents.ON_PAGING, this);
	    			insertPagingComponent();
        		}
        		else
        		{
        			paging.setTotalSize(m_count);
        			paging.setActivePage(0);
        		}
    			List<Object> subList = readLine(0, PAGE_SIZE);
    			model = new ListModelTable(subList);
    			model.setSorter(this);
	            model.addTableModelListener(this);
	            p_table.setData(model, null);
	            
	            pageNo = 0;
        	}
        	else
        	{
        		if (paging != null) 
        		{
        			paging.setTotalSize(m_count);
        			paging.setActivePage(0);
        			pageNo = 0;
        		}
	            model = new ListModelTable(readLine(0, -1));
	            model.setSorter(this);
	            model.addTableModelListener(this);
	            p_table.setData(model, null);
        	}
        }
       // metas c.ghita@metas.ro : start  
        else
        {
        	model = new ListModelTable();
            p_table.setData(model, null);
            
        }
        // metas c.ghita@metas.ro : start
        int no = m_count;
        setStatusLine(Integer.toString(no) + " " + Msg.getMsg(Env.getCtx(), "SearchRows_EnterQuery"), false);
        setStatusDB(Integer.toString(no));
                
        addDoubleClickListener();
    }
    
    private List<Object> readLine(int start, int end) {
    	//cacheStart & cacheEnd - 1 based index, start & end - 0 based index
    	if (cacheStart >= 1 && cacheEnd > cacheStart)
    	{
    		if (start+1 >= cacheStart && end+1 <= cacheEnd)
    		{
    			return end == -1 ? line : line.subList(start-cacheStart+1, end-cacheStart+2);
    		}
    	}

    	cacheStart = start + 1 - (PAGE_SIZE * 4);
    	if (cacheStart <= 0)
    		cacheStart = 1;

    	if (end == -1)
    	{
    		cacheEnd = m_count;
    	}
    	else
    	{
	    	cacheEnd = end + 1 + (PAGE_SIZE * 4);
	    	if (cacheEnd > m_count)
	    		cacheEnd = m_count;
    	}

    	line = new ArrayList<Object>();

    	PreparedStatement m_pstmt = null;
		ResultSet m_rs = null;

		long startTime = System.currentTimeMillis();
			//

        String dynWhere = getSQLWhere();
        StringBuffer sql = new StringBuffer (m_sqlMain);
        if (dynWhere.length() > 0)
            sql.append(dynWhere);   //  includes first AND
        if (m_sqlUserOrder != null && m_sqlUserOrder.trim().length() > 0)
        	sql.append(m_sqlUserOrder);
        else
        	sql.append(m_sqlOrder);
        String dataSql = Msg.parseTranslation(Env.getCtx(), sql.toString());    //  Variables
        dataSql = MRole.getDefault().addAccessSQL(dataSql, getTableName(),
            MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO);
        if (end > start && m_useDatabasePaging && DB.getDatabase().isPagingSupported())
        {
        	dataSql = DB.getDatabase().addPagingSQL(dataSql, cacheStart, cacheEnd);
        }
        log.finer(dataSql);
		try
		{
			m_pstmt = DB.prepareStatement(dataSql, null);
			setParameters (m_pstmt, false);	//	no count
			log.fine("Start query - " + (System.currentTimeMillis()-startTime) + "ms");
			m_pstmt.setFetchSize(100);
			m_rs = m_pstmt.executeQuery();
			log.fine("End query - " + (System.currentTimeMillis()-startTime) + "ms");
			//skips the row that we dont need if we can't use native db paging
			if (end > start && m_useDatabasePaging && !DB.getDatabase().isPagingSupported())
			{
				for (int i = 0; i < cacheStart - 1; i++)
				{
					if (!m_rs.next())
						break;
				}
			}

			int rowPointer = cacheStart-1;
			while (m_rs.next())
			{
				rowPointer++;
				readData(m_rs);
				//check now of rows loaded, break if we hit the suppose end
				if (m_useDatabasePaging && rowPointer >= cacheEnd)
				{
					break;
				}
			}
		}

		catch (SQLException e)
		{
			log.log(Level.SEVERE, dataSql, e);
		}

		finally
		{
			DB.close(m_rs, m_pstmt);
		}

		return line;
	}

    private void addDoubleClickListener() {
		Iterator<?> i = p_table.getListenerIterator(Events.ON_DOUBLE_CLICK);
		while (i.hasNext()) {
			if (i.next() == this)
				return;
		}
		p_table.addEventListener(Events.ON_DOUBLE_CLICK, this);
	}
    
    protected void insertPagingComponent() {
    	p_centerNorth.appendChild(paging);
    	p_centerLayout.appendChild(p_centerNorth);
    	setSizes();
    	//this.invalidate();
    	
	}
    
    public Vector<String> getColumnHeader(ColumnInfo[] p_layout)
    {
        Vector<String> columnHeader = new Vector<String>();
        
        for (ColumnInfo info: p_layout)
        {
             columnHeader.add(info.getColHeader());
        }
        return columnHeader;
    }
	/**
	 * 	Test Row Count
	 *	@return true if display
	 */
	private boolean testCount()
	{
		long start = System.currentTimeMillis();
		String dynWhere = getSQLWhere();
		StringBuffer sql = new StringBuffer (m_sqlCount);
		
		if (dynWhere.length() > 0)
			sql.append(dynWhere);   //  includes first AND
		
		String countSql = Msg.parseTranslation(Env.getCtx(), sql.toString());	//	Variables
		countSql = MRole.getDefault().addAccessSQL	(countSql, getTableName(), 
													MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO);
		log.finer(countSql);
		m_count = -1;
		
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(countSql, null);
			setParameters (pstmt, true);
			ResultSet rs = pstmt.executeQuery();
		
			if (rs.next())
				m_count = rs.getInt(1);
			
			rs.close();
			pstmt.close();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, countSql, e);
			m_count = -2;
		}
		
		log.fine("#" + m_count + " - " + (System.currentTimeMillis()-start) + "ms");
		
		//Armen: add role checking (Patch #1694788 )
		//MRole role = MRole.getDefault(); 		
		//if (role.isQueryMax(no))
		//	return ADialog.ask(p_WindowNo, this, "InfoHighRecordCount", String.valueOf(no));
		
		return true;
	}	//	testCount
			

	/**
	 *	Save Selection	- Called by dispose
	 */
	protected void saveSelection ()
	{
		//	Already disposed
		if (p_table == null)
			return;

		log.config( "OK=" + m_ok);
		
		if (!m_ok)      //  did not press OK
		{
			m_results.clear();
			p_table = null;
			this.detach();
            return;
		}

		//	Multi Selection
		if (p_multipleSelection)
		{
			m_results.addAll(getSelectedRowKeys());
		}
		else    //  singleSelection
		{
			Integer data = getSelectedRowKey();
			if (data != null)
				m_results.add(data);
		}
		
		log.config(getSelectedSQL());

		//	Save Settings of detail info screens
		saveSelectionDetail();
		
	}	//	saveSelection

	/**
	 *  Get the key of currently selected row
	 *  @return selected key
	 */
	protected Integer getSelectedRowKey()
	{
		Integer key = p_table.getSelectedRowKey();
		
		return key;        
	}   //  getSelectedRowKey
	
	/**
     *  Get the keys of selected row/s based on layout defined in prepareTable
     *  @return IDs if selection present
     *  @author ashley
     */
    protected ArrayList<Integer> getSelectedRowKeys()
    {
        ArrayList<Integer> selectedDataList = new ArrayList<Integer>();
        
        if (p_table.getKeyColumnIndex() == -1)
        {
            return selectedDataList;
        }
        
        if (p_multipleSelection)
        {
        	int[] rows = p_table.getSelectedIndices();
            for (int row = 0; row < rows.length; row++)
            {
                Object data = p_table.getModel().getValueAt(rows[row], p_table.getKeyColumnIndex());
                if (data instanceof IDColumn)
                {
                    IDColumn dataColumn = (IDColumn)data;
                    selectedDataList.add(dataColumn.getRecord_ID());
                }
                else
                {
                    log.severe("For multiple selection, IDColumn should be key column for selection");
                }
            }
        }
        
        if (selectedDataList.size() == 0)
        {
        	int row = p_table.getSelectedRow();
    		if (row != -1 && p_table.getKeyColumnIndex() != -1)
    		{
    			Object data = p_table.getModel().getValueAt(row, p_table.getKeyColumnIndex());
    			if (data instanceof IDColumn)
    				selectedDataList.add(((IDColumn)data).getRecord_ID());
    			if (data instanceof Integer)
    				selectedDataList.add((Integer)data);
    		}
        }
      
        return selectedDataList;
    }   //  getSelectedRowKeys

	/**
	 *	Get selected Keys
	 *  @return selected keys (Integers)
	 */
	public Object[] getSelectedKeys()
	{
		if (!m_ok || m_results.size() == 0)
			return null;
		return m_results.toArray(new Integer[0]);
	}	//	getSelectedKeys;

	/**
	 *	Get (first) selected Key
	 *  @return selected key
	 */
	public Object getSelectedKey()
	{
		if (!m_ok || m_results.size() == 0)
			return null;
		return m_results.get(0);
	}	//	getSelectedKey

	/**
	 *	Is cancelled?
	 *	- if pressed Cancel = true
	 *	- if pressed OK or window closed = false
	 *  @return true if cancelled
	 */
	public boolean isCancelled()
	{
		return m_cancel;
	}	//	isCancelled

	/**
	 * 
	 * @return true if OK button was pressed
	 */
	public boolean isOk()
	{
		return m_ok;
	}
	
	/**
	 *	Get where clause for (first) selected key
	 *  @return WHERE Clause
	 */
	public String getSelectedSQL()
	{
		//	No results
		Object[] keys = getSelectedKeys();
		if (keys == null || keys.length == 0)
		{
			log.config("No Results - OK=" 
				+ m_ok + ", Cancel=" + m_cancel);
			return "";
		}
		//
		StringBuffer sb = new StringBuffer(getKeyColumn());
		if (keys.length > 1)
			sb.append(" IN (");
		else
			sb.append("=");

		//	Add elements
		for (int i = 0; i < keys.length; i++)
		{
			if (getKeyColumn().endsWith("_ID"))
				sb.append(keys[i].toString()).append(",");
			else
				sb.append("'").append(keys[i].toString()).append("',");
		}

		sb.replace(sb.length()-1, sb.length(), "");
		if (keys.length > 1)
			sb.append(")");
		return sb.toString();
	}	//	getSelectedSQL;

	/**
	 *  Test SQL WHERE parameter for validity
	 *  @param f CText field
	 *  @return Upper case text with wild cards as configured
	 */
	public static boolean isValidSQLText (Textbox f)
	{
		if (f != null && f.getText() != null)
			return isValidSQLText(f.getText());
		return false;
	}   //  isValidSQLText

	/**
	 *  Test SQL WHERE parameter for validity
	 *  @param s string
	 *  @return Upper case text with wild cards as configured
	 */
	public static boolean isValidSQLText (String s)
	{
		// Don't trap single "%".  These can be used to find
		// all non-null values.
		if (s.length() > 0)
			return true;
		return false;
	}   //  isValidSQLText

	/**
	 *  Get SQL WHERE parameter
	 *  @param f CText field
	 *  @return Upper case text with wild cards as configured
	 */
	public static String getSQLText (Textbox f)
	{
		String s = f.getText();
		return getSQLText(s);
	}   //  getSQLText

	/**
	 *  Get SQL WHERE parameter
	 *  @param s string
	 *  @return Upper case text with wild cards as configured
	 */
	public static String getSQLText (String s)
	{
		s = s.toUpperCase();
		
		//  Check the configuration for the wild card pattern to apply
		//  It can be "%*" for first-only, "*%" for last-only, "%*%" for both or "*" for none.
		//  The pattern string must start and/or end with a "%" symbol.  The "*" symbol can be any string.
		//  The default is last-only.  "%" or "%%" are valid and will be interpreted as both.
		String wildCardPattern = MSysConfig.getValue(SYSCONFIG_INFO_AUTO_WILDCARD,"*%",Env.getAD_Client_ID(Env.getCtx()));
		
		if (wildCardPattern.startsWith("%"))
		{
			if (!s.startsWith("%"))
				s = "%" + s;
		}

		if (wildCardPattern.endsWith("%"))
		{
			if (!s.endsWith("%"))
				s += "%";
		}
		
		// Need static logger
		CLogger mlog = CLogger.get();
		mlog.fine("String with wild cards: " + s);

		return s;
	}   //  getSQLText
		
		
	/**
	 *  Get Table name Synonym
	 *  @return table name
	 */
	protected String getTableName()
	{
		return p_tableName;
	}   //  getTableName

	/**
	 *  Get Key Column Name
	 *  @return column name
	 */
	protected String getKeyColumn()
	{
		return p_keyColumn;
	}   //  getKeyColumn

	
	public String[] getEvents()
    {
        return InfoPanel.lISTENER_EVENTS;
    }
	
	// Elaine 2008/11/28
	/**
	 *  Enable OK, History, Zoom if row/s selected
     *  ---
     *  Changes: Changed the logic for accommodating multiple selection
     *  @author ashley
	 */
	protected void enableButtons ()
	{
		boolean enable = (p_table.getSelectedCount() == 1);
		confirmPanel.getOKButton().setEnabled(p_table.getSelectedCount() > 0);
		
		if (hasHistory())
			confirmPanel.getButton(ConfirmPanel.A_HISTORY).setEnabled(enable);
		if (hasZoom())
			confirmPanel.getButton(ConfirmPanel.A_ZOOM).setEnabled(enable);
	}   //  enableButtons
	//
		
	/**************************************************************************
	 *  Get dynamic WHERE part of SQL
	 *	To be overwritten by concrete classes
	 *  @return WHERE clause
	 */
	protected abstract String getSQLWhere();
      	
	/**
	 *  Set Parameters for Query
	 *	To be overwritten by concrete classes
	 *  @param pstmt statement
	 *  @param forCount for counting records
	 *  @throws SQLException
	 */
	protected abstract void setParameters (PreparedStatement pstmt, boolean forCount) 
		throws SQLException;
    /**
     * notify to search editor of a value change in the selection info
     * @param event event
    *
     */

	protected void showHistory()					{}
	/**
	 *  Has History (false)
	 *	To be overwritten by concrete classes
	 *  @return true if it has history (default false)
	 */
	protected boolean hasHistory()				{return false;}
	/**
	 *  Customize dialog
	 *	To be overwritten by concrete classes
	 */
	protected void customize()					{}
	/**
	 *  Has Customize (false)
	 *	To be overwritten by concrete classes
	 *  @return true if it has customize (default false)
	 */
	protected boolean hasCustomize()				{return false;}
	/**
	 *  Has Zoom (false)
	 *	To be overwritten by concrete classes
	 *  @return true if it has zoom (default false)
	 */
	protected boolean hasZoom()					{return false;}
	/**
	 *  Save Selection Details
	 *	To be overwritten by concrete classes
	 */
	protected void saveSelectionDetail()          {}

	/**
	 * 	Get Zoom Window
	 *	@param tableName table name
	 *	@param isSOTrx sales trx
	 *	@return AD_Window_ID
	 */
	protected int getAD_Window_ID (String tableName, boolean isSOTrx)
	{
		if (!isSOTrx && m_PO_Window_ID > 0)
			return m_PO_Window_ID;
		if (m_SO_Window_ID > 0)
			return m_SO_Window_ID;
		//
		String sql = "SELECT AD_Window_ID, PO_Window_ID FROM AD_Table WHERE TableName=?";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setString(1, tableName);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				m_SO_Window_ID = rs.getInt(1);
				m_PO_Window_ID = rs.getInt(2);
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		//
		if (!isSOTrx && m_PO_Window_ID > 0)
			return m_PO_Window_ID;
		return m_SO_Window_ID;
	}	//	getAD_Window_ID
    
    public void onEvent(Event event)
    {
    	
    	// Handle actions 

		if(!p_loadedOK)
			return;		//  We aren't ready
		
		if(m_busy )
			return;		//  We're busy.  Ignore events.

        if  (event!=null)
        {
    		if (event.getName().equals("onOK") && event.getTarget() != keyListener)
    		{
    			//  The enter key was pressed in a criteria field.  Ignore it.  The key click will trigger
    			//  other events that will be trapped.
    			event.stopPropagation();
    			return;
    		}

        	Component component = event.getTarget();
    		
    		if(component != null)
    		{
    			//  Generic components in the criteria fields
    			if (component instanceof Textbox)
    			{
    				Textbox tb = ((Textbox) component);

    				if (tb.hasChanged())
    				{
    					p_triggerRefresh = true;
    				}
    				else
    				{
    					// Special case where text fields don't change but cause an event
    					// Interpret this as a click of the OK button and close EXCEPT
    					// if the dialog was opened from a menu.
    					if (isModal())
    						dispose(true);  //  Save the selection and close;
    					else
    						return;
    				}
    			}
    			else if (component instanceof Checkbox)
    			{
    				//  Check box changes generally always cause a refresh
    				//  Capture changes that don't in a specific event handler
    				p_triggerRefresh = true;
    				
    				Checkbox cb = (Checkbox) component;
    				if (cb.getName() != null && cb.getName().equals("AutoQuery"))
    				{
    					//  Only trigger a refresh if the check box is selected
    					if(!cb.isSelected())
    					{
    						return;
    					}
    				}
    			}
    			else 
    			{
    				//  Assume another type of component
    				if(event.getName().equals("onChange"))
					{
    					if (component instanceof Combobox)
    					{
    						 if (hasOutstandingChanges())  //  Test for meaningful changes. Null == " ".
								 p_triggerRefresh = true;
    					}
    					else
    						p_triggerRefresh = true;	
					}
				}
    			
    			//  Buttons
	        	if (component.equals(confirmPanel.getButton(ConfirmPanel.A_OK)) || event.getName().equals(Events.ON_CTRL_KEY) )
	            {
	        		if(event.getTarget().equals(keyListener)) {
	        		KeyEvent keyEvent = (KeyEvent) event;
    				int code = keyEvent.getKeyCode();
    					if (code != KEYBOARD_KEY_RETURN)
    						return;
	        		}	
						//  The enter key is mapped to the Ok button which will close the dialog.
						//  Don't let this happen if there are outstanding changes to any of the 
						//  VLookup fields in the criteria
						if (hasOutstandingChanges())
						{
							return;
						}
						else
						{
							// We might close
							p_triggerRefresh = false;
						}
		                onOk();
    			
	            }
	            else if (component == p_table && event.getName().equals(Events.ON_DOUBLE_CLICK))
	            {
	            	onDoubleClick();
	            }
				else if (component.equals(confirmPanel.getButton(ConfirmPanel.A_RESET)))
				{
					//  Created by the reset button, if used, to reset the criteria panel.
					//  Go back to the defaults
					m_busy = true;  // Prevent other actions
					initInfo();  // Should be overridden in the subordinate class
					m_busy = false;
					
					p_triggerRefresh = true;
					p_refreshNow = true;  // Ignore the autoQuery value and refresh now.
					
				}
				else if (component.equals(confirmPanel.getButton(ConfirmPanel.A_REFRESH)))
	            {            	
					//  Refresh always causes a requery in case there are
					//  changes to the underlying tables - even if the 
					//  criteria haven't changed.
					p_resetColumns = true;
					p_triggerRefresh = true;
					p_refreshNow = true;	
	            }
	            else if (component.equals(confirmPanel.getButton(ConfirmPanel.A_CANCEL)) || event.getName().equals(Events.ON_CANCEL))
	            {
	            	m_cancel = true;
	                dispose(false);  // close
	            }
	            // Elaine 2008/12/16
	            else if (component.equals(confirmPanel.getButton(ConfirmPanel.A_HISTORY)))
	            {
	            	if (!p_table.getChildren().isEmpty() && p_table.getSelectedRowKey()!=null)
	                {
	            		showHistory();
	                }
	            	return;
	            }
	    		else if (component.equals(confirmPanel.getButton(ConfirmPanel.A_CUSTOMIZE)))
	    		{
	            	if (!p_table.getChildren().isEmpty() && p_table.getSelectedRowKey()!=null)
	                {
	            		customize();
	                }
	            	return;
	    		}
	            //
	            else if (component.equals(confirmPanel.getButton(ConfirmPanel.A_ZOOM)))
	            {
	                if (!p_table.getChildren().isEmpty() && p_table.getSelectedRowKey()!=null)
	                {
	                    zoom();
	                    if (isModal())
	                    	this.detach();
	                }
	                return;
	            }
	            else if (component == paging)
	            {
	            	int pgNo = paging.getActivePage();
	            	if (pageNo != pgNo) 
	            	{
	            	
	            		p_table.clearSelection();
	    			
	            		pageNo = pgNo;
	            		int start = pageNo * PAGE_SIZE;
	            		int end = start + PAGE_SIZE;
	            		List<Object> subList = readLine(start, end);
	        			model = new ListModelTable(subList);
	        			model.setSorter(this);
	    	            model.addTableModelListener(this);
	    	            p_table.setData(model, null);
	    	            
	    				p_table.setSelectedIndex(0);
	    			}
	            }
    		}
    		
    		//  All events, unless trapped above, will get here.
			//  Check if we need to reset the table.  The flag is reset when
			//  the table is reset.  The first change triggers the reset.
			p_resetColumns = p_resetColumns || columnIsDynamic(component);
			//
            // Refresh if the autoquery feature is selected or the refresh button is clicked.
            if( (p_triggerRefresh && autoQuery()) || p_refreshNow)
            {
            	prepareAndExecuteQuery();
            	p_refreshNow = false;
            }
        }
    }  //  onEvent

	/**
	 * Capture value changes in WSearchEditor components specifically.
	 * Copy and override as required.
	 * @param evt
	 */
	public void valueChange(ValueChangeEvent evt) 
	{
		Object c = null;
		
		if (evt.getSource() instanceof WSearchEditor)
			c = ((WSearchEditor) evt.getSource()).getComponent();
		else if (evt.getSource() instanceof WPAttributeEditor)
			c = ((WPAttributeEditor) evt.getSource()).getComponent();
				
		if (c == null)
			return;

		// Pass it off to the event handler to process.
		Event e = new Event("onChange", (Component) c);
		onEvent(e);

	}  //  valueChange

	protected void showBusyDialog() {
		progressWindow = new BusyDialog();
		progressWindow.setPage(this.getPage());
		progressWindow.doHighlighted();
	}

	private void hideBusyDialog() {
		progressWindow.dispose();
		progressWindow = null;
	}

    public void onQueryCallback()
    {
    	try
    	{
    		if (p_triggerRefresh)  //  Could be false if nothing has changed
    		{
    			if (this.p_resetColumns)  //  Reset the table
    			{
    				prepareTable(getTableLayout(),
    						getFromClause(),
    						getWhereClause(),
    						getOrderClause());
    				this.p_table.setShowTotals(getShowTotals());
    				p_resetColumns = false;
    			}
    			//
    			p_triggerRefresh = false;
    		}
    		//  Find what is currently selected
    		//  Re-selection of the column happens after the query is run
    		Integer selectedKey = (Integer) getSelectedRowKey();
            if(selectedKey != null && selectedKey.intValue() != 0)
            	this.p_selectedRecordKey = selectedKey.intValue();  

    		setFieldOldValues();  // Remember the query criteria values
            executeQuery();		  // Run the query
            renderItems();        // Display the table
    		//  One query has been performed.  From now on, ignore the record_id and use the search criteria.
    		m_resetRecordID = true; //  A new record ID will be selected
        }
    	finally
    	{
    		reselectRecord();	// Try to reselect the same record as was previously selected
    		refresh();			// Refresh any subordinate tables or other parts of the info window
    		enableButtons();	// Enable buttons based on the type of record selected
    		hideBusyDialog();
    		m_busy = false;
    	}
    }
    
    private void onOk() 
    {
		if (!p_table.getChildren().isEmpty() && p_table.getSelectedRowKey()!=null)
		{
		    dispose(p_saveResults);
		}
	}
    
    private void onDoubleClick()
	{
		if (isModal())
		{
			dispose(p_saveResults);
		}
		else
		{
			zoom();
		}

	}

    public void tableChanged(WTableModelEvent event)
    {
		//  Assume a selection event took place
		// p_selectedRecordKey = p_table.getLeadRowKey();
    	// enableButtons();    	
    }
    
    /**
     * Reselect the record
     */
    public void reselectRecord()
    {
		//  Try to reselect the record
		if(!setSelectedRow(p_selectedRecordKey))		
		{
			//  Nothing was selected, or the query is empty
			noRecordSelected();
			setNumRecordsSelected(0);
		}
		else  //  Found and selected the same record or selected the first record
		{
			recordSelected(p_table.getLeadRowKey());
			setNumRecordsSelected(1);
		}
		p_selectedRecordKey = p_table.getLeadRowKey();
    }
    
	/**
	 * Reset the record id
	 */
	public boolean isResetRecordID()
	{
		return m_resetRecordID;
	}

	/**
	 * Refresh the panel following a query
	 */
	protected void refresh()
	{
		//  Refresh the panel - override.
	}
	
	/**
	 * Test the object for existence and valid data 
	 * @param o - one of a WSearchEditor, VAttributeInstance
	 * @return
	 */
	public boolean isValidVObject(Object o)
	{
		if (o != null)
		{
			try 
			{
				if (o instanceof WSearchEditor)
				{
					return 	(((WSearchEditor) o).getValue() != null && ((Integer)((WSearchEditor) o).getValue()).intValue() != 0);
				}
				else if (o instanceof WTableDirEditor)
				{
					return 	(((WTableDirEditor) o).getValue() != null && ((Integer)((WTableDirEditor) o).getValue()).intValue() != 0);
				}
				else if (o instanceof WPAttributeEditor)
				{
					return 	(((WPAttributeEditor) o).getValue() != null && ((Integer)((WPAttributeEditor) o).getValue()).intValue() != 0);
				}
			}
			catch(ClassCastException e)
			{
				return false;
			}
		}
		return false;
	}

    public void zoom()
    {
    	if (listeners != null && listeners.size() > 0)
    	{
	        ValueChangeEvent event = new ValueChangeEvent(this,"zoom",
	                   p_table.getSelectedRowKey(),p_table.getSelectedRowKey());
	        fireValueChange(event);
    	}
    	else
    	{
    		Integer recordId = p_table.getSelectedRowKey();
    		int AD_Table_ID = MTable.getTable_ID(p_tableName);
    		if (AD_Table_ID <= 0)
    		{
    			if (p_keyColumn.endsWith("_ID")) 
    			{
    				AD_Table_ID = MTable.getTable_ID(p_keyColumn.substring(0, p_keyColumn.length() - 3));
    			}
    		}
    		if (AD_Table_ID > 0)
    			AEnv.zoom(AD_Table_ID, recordId);
    	}
    }
    
	
    public void addValueChangeListener(ValueChangeListener listener)
    {
        if (listener == null)
        {
            return;
        }
        
        listeners.add(listener);
    }
        
    public void fireValueChange(ValueChangeEvent event)
    {
        for (ValueChangeListener listener : listeners)
        {
           listener.valueChange(event);
        }
    }
    
	/**
	 *	Dispose (not OK)
	 */
	public void dispose()
	{
		dispose(false);
	}	//	dispose

    /**
     *  Dispose and save Selection
     *  @param ok OK pressed
     */
    public void dispose(boolean ok)
    {
        log.config("OK=" + ok);
        m_ok = ok;

        //  End Worker
        if (isModal())
        {
        	saveSelection();
        }
        if (Window.MODE_EMBEDDED.equals(getAttribute(Window.MODE_KEY)))
        	SessionManager.getAppDesktop().closeActiveWindow();
        else
	        this.detach();
    }   //  dispose
        
	public void sort(Comparator cmpr, boolean ascending) {
		WListItemRenderer.ColumnComparator lsc = (WListItemRenderer.ColumnComparator) cmpr;
		if (m_useDatabasePaging)
		{
			int col = lsc.getColumnIndex();
			String colsql = p_layout[col].getColSQL().trim();
			int lastSpaceIdx = colsql.lastIndexOf(" ");
			if (lastSpaceIdx > 0)
			{
				String tmp = colsql.substring(0, lastSpaceIdx).trim();
				char last = tmp.charAt(tmp.length() - 1);
				if (tmp.toLowerCase().endsWith("as"))
				{
					colsql = colsql.substring(lastSpaceIdx).trim();
				}
				else if (!(last == '*' || last == '-' || last == '+' || last == '/' || last == '>' || last == '<' || last == '='))
				{
					tmp = colsql.substring(lastSpaceIdx).trim();
					if (tmp.startsWith("\"") && tmp.endsWith("\""))
					{
						colsql = colsql.substring(lastSpaceIdx).trim();
					}
					else
					{
						boolean hasAlias = true;
						for(int i = 0; i < tmp.length(); i++)
						{
							char c = tmp.charAt(i);
							if (Character.isLetterOrDigit(c))
							{
								continue;
							}
							else
							{
								hasAlias = false;
								break;
							}
						}
						if (hasAlias)
						{
							colsql = colsql.substring(lastSpaceIdx).trim();
						}
					}
				}
			}
			m_sqlUserOrder = " ORDER BY " + colsql;
			if (!ascending)
				m_sqlUserOrder += " DESC ";
			executeQuery();
			renderItems();
		}
		else
		{
			Collections.sort(line, lsc);
			renderItems();
		}
	}

    public boolean isModal()
    {
    	return m_modal;
    }

    public void scrollToSelectedRow()
    {
    	if (p_table != null && p_table.getSelectedIndex() >= 0) {
    		Listitem selected = p_table.getItemAtIndex(p_table.getSelectedIndex());
    		if (selected != null) {
    			selected.focus();
    		}
    	}
    }
    
	/**
	 * Determine if the column causes dynamic changes in the table layout
	 * @param o
	 * @return true if changes result
	 */
	protected boolean columnIsDynamic(Object o)
	{
		// List of search fields that cause changes to the table layout
		// See getProductLayout() and component attribute
		if (o != null)
		{
			Component c = ((Component) o);
			try {
				if (c.getAttribute("IsDynamic") != null)
				{
					if (c.getAttribute("IsDynamic").equals("True"))
					{
						return true;
					}
				}			
			}
			catch(NullPointerException npe)
			{
				return false;
			}
		}
		return false;
	}
	
	/**
	 * Record outstanding changes by copying the current
	 * value to the oldValue on all fields
	 */
	protected void setFieldOldValues()
	{
		//  fieldValue.set_oldValue();
		return;
	}

	/**
	 * Does the parameter panel have outstanding changes that have not been
	 * used in a query?  Override with specific tests.
	 * @return true if there are outstanding changes.
	 */
	protected boolean hasOutstandingChanges()
	{
		return false;
	}

	/**
	 * Generic init call invoked by the event handler to reset the criteria panel.  
	 * Used to call class specific initInfo function with reset parameters.
	 */
	protected void initInfo ()
	{
	}

	protected void setShowTotals(boolean showTotals)
	{
		m_showTotals = showTotals;
		p_table.setShowTotals(m_showTotals);
	}

	protected boolean getShowTotals()
	{
		return m_showTotals;
	}

	/**
	 * @return the p_layout
	 */
	protected ColumnInfo[] getTableLayout() {
		return p_layout;
	}

	/**
	 * @param p_layout the p_layout to set
	 */
	protected void setTableLayout(ColumnInfo[] layout) {
		this.p_layout = layout;
	}

	/**
	 * @return the p_sqlFrom
	 */
	protected String getFromClause() {
		return p_sqlFrom;
	}

	/**
	 * @param from the p_sqlFrom to set
	 */
	protected void setFromClause(String from) {
		p_sqlFrom = from;
	}

	/**
	 * @return the p_sqlWhere
	 */
	protected String getWhereClause() {
		return p_whereClause;
	}

	/**
	 * @param where the p_sqlWhere to set
	 */
	protected void setWhereClause(String where) {
		p_whereClause = where;
	}

	/**
	 * @return the p_sqlOrder
	 */
	protected String getOrderClause() {
		return p_sqlOrder;
	}

	/**
	 * @param order the p_sqlOrder to set
	 */
	protected void setOrderClause(String order) {
		p_sqlOrder = order;
	}

	/**
	 * A record was selected - take action to sync subordinate tables if any
	 * @param key of the selected record
	 */
	protected void recordSelected(int key)
	{
		return;
	}
	/**
	 * No record was selected - take action to sync subordinate tables if any
	 */
	protected void noRecordSelected()
	{
		return;
	}

	/**
	 * @return the number of Records Selected
	 */
	protected int getNumRecordsSelected() {
		return p_numRecordsSelected;
	}

	/**
	 * @param the number of records selected
	 */
	protected void setNumRecordsSelected(int numRecordsSeleted) {
		p_numRecordsSelected = numRecordsSeleted;
	}

}	//	Info
