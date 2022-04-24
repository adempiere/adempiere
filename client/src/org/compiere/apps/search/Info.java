/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.apps.search;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.*;

import org.adempiere.plaf.AdempiereTaskPaneUI;
import org.compiere.apps.ADialog;
import org.compiere.apps.AEnv;
import org.compiere.apps.ALayout;
import org.compiere.apps.AWindow;
import org.compiere.apps.AppsAction;
import org.compiere.apps.ConfirmPanel;
import org.compiere.apps.PrintScreenPainter;
import org.compiere.apps.StatusBar;
import org.compiere.grid.ed.Calculator;
import org.compiere.grid.ed.VCheckBox;
import org.compiere.grid.ed.VComboBox;
import org.compiere.grid.ed.VLookup;
import org.compiere.grid.ed.VPAttribute;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.MiniTable;
import org.compiere.model.GridTab;
import org.compiere.model.MQuery;
import org.compiere.model.MRole;
import org.compiere.model.MSysConfig;
import org.compiere.swing.CButton;
import org.compiere.swing.CDialog;
import org.compiere.swing.CMenuItem;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTextField;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.jdesktop.swingx.JXTaskPane;

/**
 *	Search Information and return selection - Base Class.
 *  <pre>
 *  Structure:
 *      parameterPanel  (JPanel) - for subclasses to add parameter fields
 *      scrollPame      (JScrollPane)
 *          m_table     (MiniTable)
 *      southPanel      (JPanel)
 *          confirmPanel
 *          statusPanel
 *  </pre>
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: Info.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 *  
 *  @author Teo Sarca
 *  		<li>FR [ 2846869 ] Info class - add more helper methods
 *  			https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2846869&group_id=176962
 * 			<li>FR [ 2847305 ] Info class improvements
 * 				https://sourceforge.net/tracker/?func=detail&aid=2847305&group_id=176962&atid=879335
 * 			<li>BF [ 2860556 ] Info class throws false error
 * 				https://sourceforge.net/tracker/?func=detail&aid=2860556&group_id=176962&atid=879332
 *
 * @author Michael McKay, 
 * 				<li>ADEMPIERE-72 VLookup and Info Window improvements
 * 					https://adempiere.atlassian.net/browse/ADEMPIERE-72
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/508">
 * 		@see FR [ 508 ] Buttons on VLookup fields in Find window don't work.</a>
 */
public abstract class Info extends CDialog
	implements ListSelectionListener, PropertyChangeListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5606614040914295869L;
	
	/**
	 *  Factory Constructor (Depreciated)
	 *  @param  frame   parent frame
	 *  @param  modal   new window is modal
	 *  @param  WindowNo	window no
	 *  @param  tableName   table name of the search
	 *  @param  keyColumn   key column of the search
	 *  @param value query value to find, exclusive of record_id
	 *  @param  multiSelection  allow to select more than one row
	 *  @param  whereClause fully qualified where clause for the search
	 *  @return special or general Info Window
	 */
	@Deprecated
	public static Info create (Frame frame, boolean modal, int WindowNo,
		String tableName, String keyColumn, String value,
		boolean multiSelection, String whereClause)	
	{
		return create (frame, modal, WindowNo,
				tableName, keyColumn, 0, value,
				multiSelection, true, whereClause);
	}
	
	/**
	 *  Factory Constructor
	 *  @param  frame   parent frame
	 *  @param  modal   new window is modal
	 *  @param  WindowNo	window no
	 *  @param  tableName   table name of the search
	 *  @param  keyColumn   key column of the search
	 *  @param record_id The record ID to find
	 *  @param value query value to find, exclusive of record_id
	 *  @param  multiSelection  allow to select more than one row
	 *  @param  saveResults  True if results will be saved, false for info only
	 *  @param  whereClause fully qualified where clause for the search
	 *  @return special or general Info Window
	 */
	public static Info create (Frame frame, boolean modal, int windowNo,
		String tableName, String keyColumn, int record_id, String value,
		boolean multiSelection, boolean saveResult, String whereClause)
	{
		Info info = null;

		if (tableName.equals("C_BPartner"))
			info = new InfoBPartner (frame, modal, windowNo, record_id, value, !Env.getContext(Env.getCtx(),"IsSOTrx").equals("N"),
					false, multiSelection, saveResult, whereClause);
		else if (tableName.equals("M_Product"))
			info = new InfoProduct (frame, modal, windowNo, 0, 0, record_id, value,
					multiSelection, saveResult, whereClause);
		else if (tableName.equals("C_Invoice"))
			info = new InfoInvoice (frame, modal, windowNo, record_id, value,
					multiSelection, saveResult, whereClause);
		else if (tableName.equals("A_Asset"))
			info = new InfoAsset (frame, modal, windowNo, record_id, value,
					multiSelection, saveResult, whereClause);
		else if (tableName.equals("C_Order"))
			info = new InfoOrder (frame, modal, windowNo, record_id, value,
					multiSelection, saveResult, whereClause);
		else if (tableName.equals("M_InOut"))
			info = new InfoInOut (frame, modal, windowNo, record_id, value,
					multiSelection, saveResult, whereClause);
		else if (tableName.equals("C_Payment"))
			info = new InfoPayment (frame, modal, windowNo, record_id, value,
					multiSelection, saveResult, whereClause);
		else if (tableName.equals("C_CashLine"))
			info = new InfoCashLine (frame, modal, windowNo, record_id, value,
					multiSelection, saveResult, whereClause);
		else if (tableName.equals("S_ResourceAssigment"))
			info = new InfoAssignment (frame, modal, windowNo, record_id, value,
					multiSelection, saveResult, whereClause);
		else
			info = new InfoGeneral (frame, modal, windowNo, record_id, value, 
				tableName, keyColumn, 
				multiSelection, saveResult, whereClause);
		//
		AEnv.positionCenterWindow(frame, info);
		return info;
	}   //  create

	/**
	 * Show BPartner Info (non modal)
	 * @param frame Parent Frame
	 * @param WindowNo window no
	 */
	public static void showBPartner (Frame frame, int WindowNo)
	{
		Info info = new InfoBPartner (frame, false, WindowNo,  0, "",
			!Env.getContext(Env.getCtx(),"IsSOTrx").equals("N"), false, false, false, "");
		AEnv.showCenterWindow(frame, info);
	}   //  showBPartner

	/**
	 * Show Asset Info (non modal)
	 * @param frame Parent Frame
	 * @param WindowNo window no
	 */
	public static void showAsset (Frame frame, int WindowNo)
	{
		Info info = new InfoAsset (frame, false, WindowNo, 0, "", false, false, "");
		AEnv.showCenterWindow(frame, info);
	}   //  showBPartner

	/**
	 * Show Product Info (non modal)
	 * @param frame Parent Frame
	 * @param WindowNo window no
	 */
	public static void showProduct (Frame frame, int WindowNo)
	{
		Info info = new InfoProduct (frame, false, WindowNo, 
			Env.getContextAsInt(Env.getCtx(), WindowNo, "M_Warehouse_ID"),
			Env.getContextAsInt(Env.getCtx(), WindowNo, "M_PriceList_ID"), 
			0,		// Record ID
			"",		//	value 
			false, false, "");
		AEnv.showCenterWindow(frame, info);
	}   //  showProduct

	/**
	 * Show Order Info (non modal)
	 * @param frame Parent Frame
	 * @param WindowNo window no
	 * @param value query value
	 */
	public static void showOrder (Frame frame, int WindowNo, String value)
	{
		Info info = new InfoOrder (frame, false, WindowNo, 0, value,
			false, false, "");
		AEnv.showCenterWindow(frame, info);
	}   //  showOrder

	/**
	 * Show Invoice Info (non modal)
	 * @param frame Parent Frame
	 * @param WindowNo window no
	 * @param value query value
	 */
	public static void showInvoice (Frame frame, int WindowNo, String value)
	{
		Info info = new InfoInvoice (frame, false, WindowNo, 0, value,
			false, false, "");
		AEnv.showCenterWindow(frame, info);
	}   //  showInvoice

	/**
	 * Show Shipment Info (non modal)
	 * @param frame Parent Frame
	 * @param WindowNo window no
	 * @param value query value
	 */
	public static void showInOut (Frame frame, int WindowNo, String value)
	{
		Info info = new InfoInOut (frame, false, WindowNo, 0, value,
			false, false, "");
		AEnv.showCenterWindow(frame, info);
	}   //  showInOut

	/**
	 * Show Payment Info (non modal)
	 * @param frame Parent Frame
	 * @param WindowNo window no
	 * @param value query value
	 */
	public static void showPayment (Frame frame, int WindowNo, String value)
	{
		Info info = new InfoPayment (frame, false, WindowNo, 0, value,
			false, false, "");
		AEnv.showCenterWindow(frame, info);
	}   //  showPayment

	/**
	 * Show Cash Line Info (non modal)
	 * @param frame Parent Frame
	 * @param WindowNo window no
	 * @param value query value
	 */
	public static void showCashLine (Frame frame, int WindowNo, String value)
	{
		Info info = new InfoCashLine (frame, false, WindowNo, 0, value,
			false, false, "");
		AEnv.showCenterWindow(frame, info);
	}   //  showCashLine

	/**
	 * Show Assignment Info (non modal)
	 * @param frame Parent Frame
	 * @param WindowNo window no
	 * @param value query value
	 */
	public static void showAssignment (Frame frame, int WindowNo, String value)
	{
		Info info = new InfoAssignment (frame, false, WindowNo, 0, value,
			false, false, "");
		AEnv.showCenterWindow(frame, info);
	}   //  showAssignment

	/** Window Width                */
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension screensize = toolkit.getScreenSize();

	protected final int        INFO_WIDTH = screensize.width > 1500 ? 1500 : screensize.width - 100;
	protected final int        SCREEN_HEIGHT = screensize.height;

	
	/**************************************************************************
	 *	Detail Constructor
	 *  @param frame parent frame
	 *  @param modal modal
	 *  @param WindowNo window no
	 *  @param tableName table name
	 *  @param keyColumn key column name
	 *  @param saveResults flag if the results will be saved in context
	 *  @param multiSelection multiple selection
	 *  @param whereClause where clause
	 */
	protected Info (Frame frame, boolean modal, int WindowNo,
		String tableName, String keyColumn,
		boolean multiSelection, boolean saveResults, String whereClause)
	{

		super (frame, modal);
		log.info("WinNo=" + p_WindowNo + " " + whereClause);
		p_saveResults = saveResults;
		p_tableName = tableName;
		p_keyColumn = keyColumn;
		p_table.setMultiSelection(multiSelection);
		p_WindowNo = WindowNo;
		p_TabNo = 0;
		//
		Class<?> frameClass = frame.getClass();
		if (frameClass == AWindow.class)
		{
			//  Activated from a window - find the active tab to limit the context
			GridTab tab = ((AWindow) frame).getAPanel().getCurrentTab();
			//	Validate possible NPE
			if(tab != null) {
				p_TabNo = tab.getTabNo();
			}
		}
		//
		if (whereClause == null || whereClause.indexOf('@') == -1)
			setWhereClause(whereClause);
		else
		{
			String tempClause = "";
			tempClause = Env.parseContext(Env.getCtx(), p_WindowNo, whereClause, false, false);
			if (tempClause.length() == 0)
				log.log(Level.SEVERE, "Cannot parse context= " + whereClause);
			setWhereClause(tempClause);
		}

		try
		{
			jbInit();
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, "Info", ex);
		}
	}	//	Info

	private static String SYSCONFIG_INFO_AUTO_WILDCARD = "INFO_AUTO_WILDCARD";
	private static String SYSCONFIG_INFO_AUTO_QUERY = "INFO_AUTO_QUERY";

	/** Master (owning) Window  */
	protected int				p_WindowNo;
	/** Tab No to limit context */
	protected int				p_TabNo;
	/** Table Name              */
	protected String            p_tableName;
	/** Key Column Name         */
	protected String            p_keyColumn;
	/** Initial WHERE Clause    */
	protected String			p_whereClause = "";
	/** Will the results of the search be saved?	*/
	protected boolean 			p_saveResults = true;
	/** Does the layout need to be rebuilt. True by default (1st time always) */
	protected boolean 			p_resetColumns = true;
	boolean p_triggerRefresh = false;
	boolean p_refreshNow = false;
	
	/** Table                   */
	protected MiniTable         p_table = new MiniTable(); //  p_table
	/** Tracking for previously selected record				*/
	protected int 				p_selectedRecordKey = 0;

	/** OK pressed                  */
	private boolean			    m_ok = false;
	/** Cancel pressed - need to differentiate between OK - Cancel - Exit	*/
	private boolean			    m_cancel = false;
	/** Reset the record ID - false on load, reset by any action that reruns the query   */
	protected boolean				m_resetRecordID = false;
	/** Result IDs              */
	private ArrayList<Integer>	m_results = new ArrayList<Integer>(3);

	/** Layout of Grid          */
	protected Info_Column[]     p_layout;
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
	/** 
	 *  Tracking the last list selection event
	 */
	ListSelectionEvent m_lse = new ListSelectionEvent(this, 0, 0, true);

	/** Loading success indicator       */
	protected boolean	        p_loadedOK = false;
	/**	SO Zoom Window						*/
	private int					m_SO_Window_ID = -1;
	/**	PO Zoom Window						*/
	private int					m_PO_Window_ID = -1;

	/** Worker                  */
	private Worker              m_worker = null;
	/** Ignore events                  */
	protected Boolean   		m_ignoreEvents = false;
	
	/**	Logger			*/
	protected CLogger log = CLogger.getCLogger(getClass());

	/** Static Layout           */
	private CButton bReset = new CButton();
	private VCheckBox checkAutoQuery = new VCheckBox();
	private CPanel southPanel = new CPanel();
	private BorderLayout southLayout = new BorderLayout();
	ConfirmPanel confirmPanel = new ConfirmPanel(true, true, true, true, true, true, true);

	private CPanel buttonPanel = new CPanel();
	protected CPanel p_criteriaGrid = new CPanel();

	protected JXTaskPane p_detailTaskPane = new JXTaskPane();
	protected CPanel addonPanel = new CPanel();
	protected StatusBar statusBar = new StatusBar();
	protected CPanel parameterPanel = new CPanel();
	private JScrollPane scrollPane = new JScrollPane();
	//
	private JPopupMenu popup = new JPopupMenu();
	private CMenuItem calcMenu = new CMenuItem();
	private CMenuItem zoomMenu = new CMenuItem();

	protected Object m_heldLastFocus = null;
	protected int m_leadSelection;
	private int m_popupRow = -1;
	private int m_popupColumn = -1;
	protected Container m_parentPanel;

	/**
	 *	Static Init
	 *  @throws Exception
	 */
	protected void jbInit() throws Exception
	{
		this.getContentPane().add(parameterPanel, BorderLayout.NORTH);
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);
		this.getContentPane().add(southPanel, BorderLayout.SOUTH);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//
		AppsAction aa = new AppsAction (ConfirmPanel.A_RESET, null, "reset");
		bReset = (CButton)aa.getButton();
		bReset.setMargin(ConfirmPanel.s_insets);
		bReset.setSize(bReset.getHeight(), bReset.getHeight());
		Dimension bSize = bReset.getSize();
		bSize.height = bReset.getHeight();
		bSize.width = bReset.getHeight();
		bReset.setMaximumSize(bSize);  // Make it square
		bReset.addActionListener(this);
		//
		buttonPanel = new CPanel(new FlowLayout(FlowLayout.LEFT));
		buttonPanel.add(bReset,FlowLayout.LEFT);
		//
		// p_criteriaGrid is filled by the subordinate class
		p_criteriaGrid.setLayout(new ALayout());
		//
		parameterPanel.setLayout(new BorderLayout());
		parameterPanel.add(buttonPanel, BorderLayout.WEST);
		parameterPanel.add(p_criteriaGrid,BorderLayout.CENTER);
		//
		// Setup the detail panel if used
		p_detailTaskPane.setVisible(false);
        p_detailTaskPane.setCollapsed(true);
        p_detailTaskPane.setLayout(new BorderLayout());
        p_detailTaskPane.setUI(new AdempiereTaskPaneUI());
        p_detailTaskPane.getContentPane().setBackground(new ColorUIResource(251,248,241));
        p_detailTaskPane.getContentPane().setForeground(new ColorUIResource(251,0,0));
        addonPanel.setLayout(new BorderLayout());
        addonPanel.add(p_detailTaskPane, BorderLayout.CENTER); // Allow auto resizing of the panel

		southPanel.setLayout(southLayout);
		southPanel.add(addonPanel, BorderLayout.NORTH);
		southPanel.add(confirmPanel, BorderLayout.CENTER);
		southPanel.add(statusBar, BorderLayout.SOUTH);
		scrollPane.getViewport().add(p_table, null);
		//
		confirmPanel.addActionListener(this);
		confirmPanel.getResetButton().setVisible(hasReset());
		confirmPanel.getCustomizeButton().setVisible(hasCustomize());
		confirmPanel.getHistoryButton().setVisible(hasHistory());
		confirmPanel.getZoomButton().setVisible(hasZoom());
		confirmPanel.setOKVisible(p_saveResults);  //  Only show the OK button if we intend to save
		//
		JButton print = ConfirmPanel.createPrintButton(true);
		print.addActionListener(this);
		confirmPanel.addButton(print);
		//
		checkAutoQuery.setText(Msg.getMsg(Env.getCtx(), "AutoRefresh"));
		checkAutoQuery.setToolTipText(Msg.getMsg(Env.getCtx(), "AutoRefresh"));
		checkAutoQuery.setName("AutoQuery");
		checkAutoQuery.setSelected(MSysConfig.getValue(SYSCONFIG_INFO_AUTO_QUERY,"Y",Env.getAD_Client_ID(Env.getCtx())).equals("Y"));  
		checkAutoQuery.addActionListener(this);
		CPanel leftButtons = (CPanel) confirmPanel.getComponent(1);  // Index 0 is OK-Cancel on the right
		leftButtons.add(checkAutoQuery, 0); // Add the check box on the very left before the refresh icon
		//
		popup.add(zoomMenu);
		zoomMenu.setText(Msg.getMsg(Env.getCtx(), "Zoom"));
		zoomMenu.setIcon(new ImageIcon(org.compiere.Adempiere.class.getResource("images/Zoom16.gif")));
		zoomMenu.addActionListener(this);
		//
		popup.add(calcMenu);
		calcMenu.setText(Msg.getMsg(Env.getCtx(), "Calculator"));
		calcMenu.setIcon(new ImageIcon(org.compiere.Adempiere.class.getResource("images/Calculator16.gif")));
		calcMenu.addActionListener(this);
		//
		//  Table Selection (Invoked before setting column class so that row selection is enabled)
		p_table.setKeyColumnIndex(-1);
		p_table.setCellSelectionEnabled(false);
		p_table.setColumnSelectionAllowed(false);
		p_table.setRowSelectionAllowed(true);
		//  Override the Enter key input and action map
		if(p_saveResults)
		{
			p_table.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "doDispose");
		}
		else
		{
			p_table.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "doNothing");
		}
		p_table.getActionMap().put("doDispose", doDispose);
		((ListSelectionModel) p_table.getSelectionModel()).addListSelectionListener(this); // To enable buttons
		p_table.addMouseListener(this);
		// Listen to changes in the table
		p_table.addPropertyChangeListener("p_table_update", this);
		((ListSelectionModel) p_table.getSelectionModel()).addListSelectionListener(this);
		//
		m_parentPanel = p_criteriaGrid;  //  Set default location where focus will go. See property change listener.
		//
		enableButtons();
	}	//	jbInit

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
		Thread.yield();
	}	//	setStatusLine

	/**
	 *	Set Status DB
	 *  @param text text
	 */
	public void setStatusDB (String text)
	{
		statusBar.setStatusDB(text);
	}	//	setStatusDB


	
	/**************************************************************************
	 *  Prepare Table, Construct SQL (m_m_sqlMain, m_sqlAdd)
	 *  and size Window
	 *  @param layout layout array
	 *  @param from from clause
	 *  @param staticWhere where clause
	 *  @param orderBy order by clause
	 */
	protected void prepareTable (Info_Column[] layout, String from, String staticWhere, String orderBy)
	{
		if (p_table == null)
			return;
		
		p_layout = layout;
		
		//  For dynamic columns, we need to wipe the table.
		if (p_resetColumns)
		{
			//  First, stop auto-updates
			boolean flag = p_table.getAutoCreateColumnsFromModel();
			p_table.setAutoCreateColumnsFromModel(false);
			p_table.setRowCount(0);
			//  Wipe the columns
			DefaultTableColumnModel tc = new DefaultTableColumnModel();
			p_table.setColumnModel(tc);
			//  Wipe the table data
			DefaultTableModel tm = new DefaultTableModel();
			p_table.setModel(tm);
			//  Zero out the indexes
			p_table.setColorColumn(-1);
			//  Re-establish the auto-updates
			p_table.setAutoCreateColumnsFromModel(flag);
			
			//  Prevent repeats
			p_resetColumns = false;
		}
		
		StringBuffer sql = new StringBuffer ("SELECT ");
		//  add columns & sql
		for (int i = 0; i < layout.length; i++)
		{
			if (i > 0)
				sql.append(", ");
			sql.append(layout[i].getColSQL());
			//  adding ID column
			if (layout[i].isIDcol())
				sql.append(",").append(layout[i].getIDcolSQL());
			//  add to model
			p_table.addColumn(layout[i].getColHeader());
			if (layout[i].isColorColumn())
				p_table.setColorColumn(i);
			if (layout[i].getColClass() == IDColumn.class)
				p_table.setKeyColumnIndex(i);
		}
				
		//  set editors (two steps)
		for (int i = 0; i < layout.length; i++)
			p_table.setColumnClass(i, layout[i].getColClass(), layout[i].isReadOnly(), layout[i].getColHeader());

		sql.append( " FROM ").append(from);
		//
		sql.append(" WHERE ").append(staticWhere);
		m_sqlMain = sql.toString();
		m_sqlCount = "SELECT COUNT(*) FROM " + from + " WHERE " + staticWhere;
		//
		m_sqlOrder = "";
		if (orderBy != null && orderBy.length() > 0)
			m_sqlOrder = " ORDER BY " + orderBy;

		if (p_table.getKeyColumnIndex() == -1)
			log.log(Level.SEVERE, "No KeyColumn - " + sql);
		
		//  Window Sizing
		parameterPanel.setPreferredSize(new Dimension (INFO_WIDTH, parameterPanel.getPreferredSize().height));
		//Begin - [FR 1823612 ] Product Info Screen Improvements
		scrollPane.setPreferredSize(new Dimension(INFO_WIDTH, 300));
		//End - [FR 1823612 ] Product Info Screen Improvements
	}   //  prepareTable

	
	/**************************************************************************
	 *  Execute Query
	 */
	protected void executeQuery()
	{
		//  ignore when running
		if (m_worker != null && m_worker.isAlive())
			return;

		//  ignore if we`re about to be closed
		if (m_ok)
			return;
		
		//  Get ready
		prepForRequery();
		//
		if (!testCount())
			return;

		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		setStatusLine(Msg.getMsg(Env.getCtx(), "StartSearch"), false);
		m_worker = new Worker();
		m_worker.start();
	}   //  executeQuery

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
		countSql = MRole.getDefault().addAccessSQL(countSql, getTableName(), 
			MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO);
		log.finer(countSql);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int no = -1;
		try
		{
			pstmt = DB.prepareStatement(countSql, null);
			setParameters (pstmt, true);
			rs = pstmt.executeQuery();
			if (rs.next())
				no = rs.getInt(1);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, countSql, e);
			no = -2;
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		log.fine("#" + no + " - " + (System.currentTimeMillis()-start) + "ms");
		//Armen: add role checking (Patch #1694788 )
		MRole role = MRole.getDefault(); 		
		if (role.isQueryMax(no))
			return ADialog.ask(p_WindowNo, this, "InfoHighRecordCount", String.valueOf(no));
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
			p_table.removeAll();
			p_table = null;
			return;
		}

		//	Multi Selection
		if (p_table.isMultiSelection())
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
		//	Clean-up
		p_table.removeAll();
		p_table = null;
	}	//	saveSelection


	/**
	 *  Get the key of currently selected row
	 *  @return selected key
	 */
	protected Integer getSelectedRowKey()
	{
		ArrayList<Integer> selectedDataList = getSelectedRowKeys();
		if (selectedDataList.size() == 0)
		{
			return null;
		}
		else
		{
			return selectedDataList.get(0);
		}
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
        
        if (p_table.isMultiSelection())
        {
        	int rows = p_table.getRowCount();
            for (int row = 0; row < rows; row++)
            {
            	// If this is the Totals row (last row), we need to skip it - teo_sarca [ 2860556 ]
                if (p_table.getShowTotals() && row == rows - 1)
                {
                	continue;
                }
                Object data = p_table.getModel().getValueAt(row, p_table.getKeyColumnIndex());
                if (data instanceof IDColumn)
                {
                    IDColumn dataColumn = (IDColumn)data;
                    if (dataColumn.isSelected())
                    {
                        selectedDataList.add(dataColumn.getRecord_ID());
                    }
                }
                else
                {
                    log.severe("For multiple selection, IDColumn should be key column for selection");
                }
            }
        }
        
        if (selectedDataList.size() == 0)
        {
        	int row;
        	if (p_table == null || p_table.getRowCount() <= 0) //  Trap NPE
        		row = -1;
    		else
    			row = p_table.getSelectedRow();
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
		Integer values[] = new Integer[m_results.size()];
		m_results.toArray(values);
		return values;
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
     *  Set the selected row to a particular key if found
     *  @returns true if successful
     */
    protected boolean setSelectedRow(int record_id)
    {
    	// Is there a key column?
        if (p_table.getKeyColumnIndex() == -1)
        {
            return false;
        }
        
        if (p_table == null)
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
        	p_table.changeSelection(0,p_table.getKeyColumnIndex(), false, false);
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
                	p_table.changeSelection(row,p_table.getKeyColumnIndex(), false, false);
                	log.fine("Record_ID = " + record_id + " found at row " + row);
                	return true;
                }
            }
        }
    	
    	//  record_id not found in the current list.  Select the first shown.
    	p_table.changeSelection(0,p_table.getKeyColumnIndex(), false, false);
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
	public static boolean isValidSQLText (CTextField f)
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
	public static String getSQLText (CTextField f)
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
	 * Property Change Listener for lookup fields
	 * @param e event
	 */
	public void propertyChange(PropertyChangeEvent e)
	{
		// Respond to updates to the table
		if (e.getPropertyName() == "p_table_update")
		{
			//  One query has been performed.  From now on, ignore the record_id and use the search criteria.
			m_resetRecordID = true; //  A new record ID will be selected

			//  Try to reselect the record
			if(!setSelectedRow(p_selectedRecordKey))		
			{
				//  Nothing was selected, or the query is empty
				noRecordSelected();
			}
			else  //  Found and selected the same record or selected the first record
			{
				recordSelected(p_table.getLeadRowKey());
			}
			
			p_selectedRecordKey = 0;

			//  Return focus to where it is expected to be
			m_parentPanel.requestFocus();
			if (m_heldLastFocus instanceof CTextField)
				((CTextField) m_heldLastFocus).requestFocus();
			if (m_heldLastFocus instanceof VLookup)
				((VLookup) m_heldLastFocus).requestFocus();
			if (m_heldLastFocus instanceof VCheckBox)
				((VCheckBox) m_heldLastFocus).requestFocus();
			
			enableButtons();
		}
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

	/**************************************************************************
	 *	(Button) Action Listener & Popup Menu
	 *  @param e event
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(!p_loadedOK)
			return;

		String cmd = e.getActionCommand();
		if (cmd.equals("CausedFocusEvent"))
		{
			return;
		}
		
		Object source = null;
		
		if(e.getSource() != null)
		{
			source = e.getSource();
			
			//  Keep the focus on the source field if the table updates
			m_heldLastFocus = this.getFocusOwner();

			//  Popup => Calculator
			if (source.equals(calcMenu))
			{
				BigDecimal number = null;
				if (m_popupRow >= 0 && m_popupColumn >= 0)
				{
					Object data = p_table.getValueAt(m_popupRow, m_popupColumn);
					try
					{
						if (data != null)
						{
							if (data instanceof BigDecimal)
								number = (BigDecimal)data;
							else
								number = new BigDecimal(data.toString());
						}
					}
					catch (Exception ex) {}
					Calculator c = new Calculator(null, number);
					c.setVisible(true);
				}
				return;
			}
			//  Popup => zoom
			else if (e.getSource().equals(zoomMenu))
			{
				if (m_popupRow >= 0 && m_popupColumn >= 0)
				{
					zoom(p_table.getRowKey(m_popupRow));
				}
				return;
			}   //  zoom

			else if (cmd.equals(ConfirmPanel.A_OK))
			{
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
			} 
			else if (source instanceof VComboBox)
			{
				if (((VComboBox) source).getParent() instanceof VLookup)
				{
					source = ((VComboBox) source).getParent();
					VLookup vl = ((VLookup)source);
					m_heldLastFocus = vl;
					
					//  Discard changes from mouse movements and keyboard entries
					//  Respond only to the Enter key which causes "comboBoxEdited"
					//  VLookups trigger multiple events in search mode. Reject 
					//  events that don't have changes from the last action.
					if(cmd.equals("comboBoxChanged"))		
					{
						if (!vl.hasChanged())
							return;
						else
							p_triggerRefresh = true;
					}
					else if(cmd.equals("comboBoxEdited"))
					{
						if (!vl.hasChanged() && !hasOutstandingChanges())
						{
							vl.requestFocus();
							return;
						}
						p_triggerRefresh = true;						
					}
				}
			}
			else if (source instanceof CTextField)
			{
				CTextField tf = ((CTextField) source);

				if (tf.getParent() instanceof VLookup)
				{
					// Ignore it.  User typed into the VLookup text field.
					// Look for events form the VLookup VComboBox component
					// instead.
					return;
				}
				else if (tf.hasChanged() || hasOutstandingChanges())  //  The change may have come from another field
				{
					p_triggerRefresh = true;
				}
				else
				{
					// Special case where text fields don't change but cause an event
					// Interpret this as a click of the OK button and close EXCEPT
					// if the dialog was opened from a menu.
					if (p_TabNo == 0)
						return;
					else
						dispose(true);  //  Save the selection and close;
				}
			}
			else if (e.getSource() instanceof VCheckBox)
			{
				//  Check box changes generally always cause a refresh
				//  Capture changes that don't 
				p_triggerRefresh = true;
				
				VCheckBox cb = (VCheckBox) e.getSource();
				if (cb.getName().equals("AutoQuery"))
				{
					//  Only trigger a refresh if the check box is selected
					if(!cb.isSelected())
					{
						return;
					}
				}
			}

			// Check if we need to reset the table.  The flag is reset when
			// the table is reset.  The first change triggers the reset.
			p_resetColumns = p_resetColumns || columnIsDynamic(source);

		} //  e.getSource() is null
								
		if (cmd.equals(ConfirmPanel.A_OK))
		{
			dispose(p_saveResults);
		}
		else if (cmd.equals(ConfirmPanel.A_CANCEL))
		{
			m_cancel = true;
			dispose(false);
		}
		else if (cmd.equals(ConfirmPanel.A_REFRESH))
		{
			//  Refresh always causes a requery in case there are
			//  changes to the underlying tables - even if the 
			//  criteria haven't changed.
			p_resetColumns = true;
			p_triggerRefresh = true;
			p_refreshNow = true;
		}
		else if (cmd.equals(ConfirmPanel.A_HISTORY))
			showHistory(p_table.getLeadRowKey());
		else if (cmd.equals(ConfirmPanel.A_CUSTOMIZE))
			customize();
		else if (cmd.equals(ConfirmPanel.A_ZOOM))
			zoom(p_table.getLeadRowKey());
		else if (cmd.equals(ConfirmPanel.A_RESET))
		{
			//  Go back to the defaults
			p_loadedOK = false;  // Prevent other actions
			initInfo();
			p_loadedOK = true;
			//
			p_resetColumns = true;
			p_triggerRefresh = true;
		}
		else if (cmd.equals(ConfirmPanel.A_PRINT))
			PrintScreenPainter.printScreen(this);
		
        // Refresh if the autoquery feature is selected or the refresh button is clicked.
		if (p_triggerRefresh && (p_refreshNow || autoQuery()))
		{
			//  Something changed so save the state and prepare for the query
			executeQuery();
		}
		
		//  Reset the flags
		p_triggerRefresh = false;
    	p_refreshNow = false;

	}	//	actionPerformed

	/**
	 * Prepare for Requery of the table.  If dynamic changes are pending, prepare the table.
	 */
	protected void prepForRequery()
	{
		// Capture the state
		setFieldOldValues();
		//  Find what is currently selected
		//  Re-selection of the column happens in the propertyChange listener
		Integer selectedKey = (Integer) getSelectedRowKey();
        if(selectedKey != null && selectedKey.intValue() != 0)
        	this.p_selectedRecordKey = selectedKey.intValue();  
		//
		if (this.p_resetColumns)  //  Reset the table
		{
			prepareTable(getTableLayout(),
					getFromClause(),
					getWhereClause(),
					getOrderClause());
			this.p_table.setShowTotals(getShowTotals());
			p_resetColumns = false;
		}
	}
	
	/**************************************************************************
	 *  Table Selection Changed
	 *  @param e event
	 */
	public void valueChanged(ListSelectionEvent lse)
	{
		// ValueChanged triggered by count can be ignored.  Event is fired in the info worker run()
		// when the table is cleared.
		if (m_ignoreEvents || p_table.getRowCount() == 0)
			return;
		
		//  Mouse events cause duplicate firings of the valueChanged event.  Trap the duplicate.
		if (m_lse.equals(lse))
			return;
		m_lse = lse;
		//
		recordSelected(p_table.getLeadRowKey());
		enableButtons();		
	}   //  ValueChanged

	/**
	 *	Zoom to target
	 *  @param AD_Window_ID window id
	 *  @param zoomQuery zoom query
	 */
	@SuppressWarnings("deprecation")
	protected void zoom (int AD_Window_ID, MQuery zoomQuery)
	{
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		final AWindow frame = new AWindow();
		if (!frame.initWindow(AD_Window_ID, zoomQuery))
			return;
		AEnv.addToWindowManager(frame);
		//	Modal Window causes UI lock
		if (isModal())
		{
			setModal(false);	//	remove modal option has no effect
			dispose();			//	VLookup.actionButton - Result = null (not cancelled)
		}
		else
			setCursor(Cursor.getDefaultCursor());

		//	VLookup gets info after method finishes
		new Thread()
		{
			public void run()
			{
				try
				{
					sleep(50);
				}
				catch (Exception e)
				{
				}
				AEnv.showCenterScreen(frame);
			}
		}.start();
	}	//	zoom

	/**
	 *	Dispose (not OK)
	 */
	public void dispose()
	{
		dispose(false);
	}	//	dispose

	/**
	 *	Dispose and save Selection
	 *	@param ok OK pressed
	 */
	public void dispose(boolean ok)
	{
		log.config("OK=" + ok);
		m_ok = ok;

		//  End Worker
		if (m_worker != null)
		{
			//  worker continues, but it does not block UI
			if (m_worker.isAlive())
				m_worker.interrupt();
			log.config("Worker alive=" + m_worker.isAlive());
		}
		m_worker = null;
		//
		p_loadedOK = false; // In case there are other events in the queue.
		saveSelection();
		removeAll();
		super.dispose();
	}	//	dispose

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

	
	/**
	 *  Enable OK, History, Zoom if row/s selected
     *  ---
     *  Changes: Changed the logic for accommodating multiple selection
     *  @author ashley
	 */
	protected void enableButtons ()
	{
		boolean enable = false;
		try
		{
			enable = (p_table.getLeadRowKey()>0);
		}
		catch (Exception e) {}
		
		confirmPanel.getOKButton().setEnabled(p_saveResults && p_table.getSelectedRowCount() > 0);
		
		if (hasHistory())
			confirmPanel.getHistoryButton().setEnabled(enable);
		if (hasZoom())
		{
			confirmPanel.getZoomButton().setEnabled(enable);
			zoomMenu.setEnabled(enable);
		}	
	}   //  enableButtons

	
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
	 *  Reset Parameters
	 *	To be overwritten by concrete classes
	 */
	protected void doReset()					{}
	/**
	 *  Has Reset (false)
	 *	To be overwritten by concrete classes
	 *  @return true if it has reset (default false)
	 */
	protected boolean hasReset()				{return false;}
	/**
	 *  History dialog
	 *	To be overwritten by concrete classes
	 */
	protected void showHistory(int record_id)					{}
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
	 *  Zoom action
	 *	To be overwritten by concrete classes
	 *  @param record ID to zoom to
	 */
	protected void zoom(int record_id)							{}
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
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setString(1, tableName);
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				m_SO_Window_ID = rs.getInt(1);
				m_PO_Window_ID = rs.getInt(2);
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//
		if (!isSOTrx && m_PO_Window_ID > 0)
			return m_PO_Window_ID;
		return m_SO_Window_ID;
	}	//	getAD_Window_ID
	
	
	/**
	 * 
	 * @return true if OK button was pressed
	 */
	public boolean isOk()
	{
		return m_ok;
	}
		
	
	/**
	 * Reset the record id
	 */
	public boolean isResetRecordID()
	{
		return m_resetRecordID;
	}
	
	/**
	 * Test the object for existence and valid data 
	 * @param o - one of a VLookup, VAttributeInstance
	 * @return
	 */
	public boolean isValidVObject(Object o)
	{
		if (o != null)
		{
			try 
			{
				if (o instanceof VLookup)
				{
					return 	(((VLookup) o).getValue() != null && ((Integer)((VLookup) o).getValue()).intValue() != 0);
				}
				else if (o instanceof VPAttribute)
				{
					return 	(((VPAttribute) o).getValue() != null && ((Integer)((VPAttribute) o).getValue()).intValue() != 0);
				}
			}
			catch(ClassCastException e)
			{
				return false;
			}
		}
		return false;
	}


	/**************************************************************************
	 *  Mouse Clicked
	 *  @param e event
	 */
	public void mouseClicked(MouseEvent e)
	{		
		//  If we want to save results, double click in a row will cause save and exit
		//  The miniTable class will trap double clicks when multiselection is enabled
		//  and the configuration is set to toggle the selection on double clicks
		if (p_saveResults && e.getID() == MouseEvent.MOUSE_CLICKED && e.getClickCount() > 1 && p_table.getSelectedRow() != -1)
		{
			dispose(p_saveResults);
		}

	}   //  mouseClicked

	/**
	 *  Mouse Pressed
	 *  @param e event
	 */
	public void mousePressed(MouseEvent e)
	{		
		//  Has a popup trigger occurred? Need to test both pressed and released for cross-platform
		//  compatibility
		if (e.isPopupTrigger())
		{
			if (e.getSource().equals(p_table))
			{
				m_popupRow = p_table.rowAtPoint(e.getPoint());
				m_popupColumn = p_table.columnAtPoint(e.getPoint());
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
			return;
		}
	}
	
	/**
	 *  Mouse Released
	 *  @param e event
	 */
	public void mouseReleased(MouseEvent e)
	{		
		//  Has a popup trigger occurred? Need to test both pressed and released for cross-platform
		//  compatibility
		if (e.isPopupTrigger())
		{
			if (e.getSource().equals(p_table))
			{
				m_popupRow = p_table.rowAtPoint(e.getPoint());
				m_popupColumn = p_table.columnAtPoint(e.getPoint());
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
			return;
		}
	}

    private Action doDispose = new AbstractAction() {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			dispose(p_saveResults);
        }
    };
	private boolean m_showTotals;


    /**
	 *  Worker
	 */
	class Worker extends Thread
	{
		private PreparedStatement m_pstmt = null;
		private ResultSet m_rs = null;
		
		public Worker()
		{
			// Basic constructor
		}
		
		/**
		 *  Do Work (load data)
		 */
		public void run()
		{
			if(p_table == null)
				return;

		//	setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		//	setStatusLine(Msg.getMsg(Env.getCtx(), "StartSearch"), false);
			long start = System.currentTimeMillis();			
			
			// Try to prevent unnecessary activity.
			m_ignoreEvents = true;
			//  Clear Table
			p_table.setRowCount(0);
			//
			String dynWhere = getSQLWhere();
			StringBuffer sql = new StringBuffer (m_sqlMain);
			if (dynWhere.length() > 0)
				sql.append(dynWhere);   //  includes first AND
			sql.append(m_sqlOrder);
			String dataSql = Msg.parseTranslation(Env.getCtx(), sql.toString());	//	Variables
			dataSql = MRole.getDefault().addAccessSQL(dataSql, getTableName(), 
				MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO);
			log.finer(dataSql);

			try
			{
				m_pstmt = DB.prepareStatement(dataSql, null);
				setParameters (m_pstmt, false);	//	no count
				log.fine("Start query - " + (System.currentTimeMillis()-start) + "ms");
				m_rs = m_pstmt.executeQuery();
				log.fine("End query - " + (System.currentTimeMillis()-start) + "ms");
				while (m_rs.next())
				{
					if (this.isInterrupted()) {
						log.finer("Interrupted");
						close();
						return;
					}
					int row = p_table.getRowCount();
					p_table.setRowCount(row+1);
					int colOffset = 1;  //  columns start with 1
					for (int col = 0; col < p_layout.length; col++)
					{
						Object data = null;
						Class<?> c = p_layout[col].getColClass();
						int colIndex = col + colOffset;
						if (c == IDColumn.class)
						{
							data = new IDColumn(m_rs.getInt(colIndex));
							
							//  Selection
							((IDColumn)data).setSelected(false); // Default
							if (p_table.isMultiSelection())  // Multiple rows can be selected
							{
								if (p_table.isDefaultSelected()) // Select all by default
								{
									p_table.changeSelection(row, 0, false, (row != 0));  // Anchor at the first row, extend to all others
								}
								else
								{
									//  Default to no selection
									p_table.clearSelection();
								}
							}
						}
						else if (c == Boolean.class)
							data = Boolean.valueOf("Y".equals(m_rs.getString(colIndex)));
						else if (c == Timestamp.class)
							data = m_rs.getTimestamp(colIndex);
						else if (c == BigDecimal.class)
							data = m_rs.getBigDecimal(colIndex);
						else if (c == Double.class)
							data = new Double(m_rs.getDouble(colIndex));
						else if (c == Integer.class)
							data = Integer.valueOf(m_rs.getInt(colIndex));
						else if (c == KeyNamePair.class)
						{
							String display = m_rs.getString(colIndex);
							int key = m_rs.getInt(colIndex+1);
							data = new KeyNamePair(key, display);
							colOffset++;
						}
						else
							data = m_rs.getString(colIndex);
						//  store
						/*
					 	if (data != null)
						 
							log.fine( "r=" + row + ", c=" + col + " " + p_layout[col].getColHeader() + 
									" data=" + data.toString() + " " + data.getClass().getName() + " * " + p_table.getCellRenderer(row, col));
						else
							log.fine( "r=" + row + ", c=" + col + " " + p_layout[col].getColHeader() + 
									" data=" + null + " String * " + p_table.getCellRenderer(row, col));
						*/
							
						p_table.setValueAt(data, p_table.convertRowIndexToView(row), p_table.convertColumnIndexToView(col));
					}
				}
			}
			catch (SQLException e)
			{
				log.log(Level.SEVERE, dataSql, e);
			}
			close();
			//
			int no = p_table.getRowCount();
			log.fine("#" + no + " - " + (System.currentTimeMillis()-start) + "ms");
			if(p_table.getShowTotals())
				p_table.addTotals(p_layout);
			p_table.autoSize();
			//
			setCursor(Cursor.getDefaultCursor());
			setStatusLine(Integer.toString(no) + " " + Msg.getMsg(Env.getCtx(), "SearchRows_EnterQuery"), false);
			setStatusDB(Integer.toString(no));
			if (no == 0)
				log.fine(dataSql);
			else
			{
				if(!p_table.isMultiSelection())
					p_table.getSelectionModel().setSelectionInterval(0, 0); // Select the first row only.
				p_table.requestFocus();
			}
			p_table.matchCheckWithSelectedRows();
			p_table.clearSelection();
			p_table.getSelectionModel().setSelectionInterval(0, 0);
			p_table.getSelectionModel().setLeadSelectionIndex(0);
			m_ignoreEvents = false;
			p_table.firePropertyChange("p_table_update", 0, 1);  // Inform the subclass of the change
		}   //  run
		
		/**
		 *	Close ResultSet and Statement
		 */
		private void close() {
			DB.close(m_rs, m_pstmt);
			m_rs = null;
			m_pstmt = null;
		}
		
		/**
		 *	Interrupt this thread - cancel the query if still in execution
		 *  Carlos Ruiz - globalqss - [2826660] - Info product performance BIG problem 
		 */
		public void interrupt() {
			if (m_pstmt != null) {
				try {
					m_pstmt.cancel();
					close();
				} catch (SQLException e) {
					log.log(Level.SEVERE, "Cannot cancel SQL statement", e);
				}
			}
			super.interrupt();
		}
	}   //  Worker

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
	 *  Clear all fields and set default values in check boxes
	 */
	protected void clearParameters()
	{
		return;
	}

	/**
	 * Generic init call invoked by the event handler to reset the criteria panel.  
	 * Used to call class specific initInfo function with reset parameters.
	 */
	protected void initInfo ()
	{
		clearParameters();
		initInfo(0,"");
	}

	/**
	 *	Dynamic Init
	 *  @param record_id The ID of the record to display or zero
	 *  @param value value 
	 */
	protected void initInfo(int record_id, String value)
	{
		return;
	}

	/**
	 * Determine if the column causes dynamic changes in the table layout
	 * @param o
	 * @return true if changes result
	 */
	protected boolean columnIsDynamic(Object o)
	{
		return false;
	}

	protected void setShowTotals(boolean showTotals)
	{
		m_showTotals = showTotals;
	}

	protected boolean getShowTotals()
	{
		return m_showTotals;
	}

	/**
	 * @return the p_layout
	 */
	protected Info_Column[] getTableLayout() {
		return p_layout;
	}

	/**
	 * @param p_layout the p_layout to set
	 */
	protected void setTableLayout(Info_Column[] p_layout) {
		this.p_layout = p_layout;
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
	
	protected boolean isMultipleResults() {
		return m_results.size() > 1;
	}
}	//	Info
