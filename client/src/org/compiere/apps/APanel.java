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
 * @contributor Victor Perez , e-Evolution.SC FR [ 1757088 ]                  *
 *****************************************************************************/
package org.compiere.apps;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.compiere.apps.form.FormFrame;
import org.compiere.apps.search.Find;
import org.compiere.grid.APanelTab;
import org.compiere.grid.GridController;
import org.compiere.grid.GridSynchronizer;
import org.compiere.grid.ICreateFrom;
import org.compiere.grid.RecordAccessDialog;
import org.compiere.grid.VCreateFromFactory;
import org.compiere.grid.VOnlyCurrentDays;
import org.compiere.grid.VPayment;
import org.compiere.grid.VSortTab;
import org.compiere.grid.VTabbedPane;
import org.compiere.grid.ed.VButton;
import org.compiere.grid.ed.VDocAction;
import org.compiere.model.DataStatusEvent;
import org.compiere.model.DataStatusListener;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.GridTable;
import org.compiere.model.GridWindow;
import org.compiere.model.GridWindowVO;
import org.compiere.model.GridWorkbench;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MProcess;
import org.compiere.model.MQuery;
import org.compiere.model.MRole;
import org.compiere.model.MUser;
import org.compiere.model.MWindow;
import org.compiere.plaf.CompiereColor;
import org.compiere.print.AReport;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoUtil;
import org.compiere.swing.CPanel;
import org.compiere.util.ASyncProcess;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Language;
import org.compiere.util.Msg;
import org.compiere.util.Util;

/**
 *	Main Panel of application window.
 *  <pre>
 *  Structure:
 *      (MenuBar) -> to be added to owning window
 *		northPanel  (ToolBar)
 *		tabPanel
 *		southPanel  (StatusBar)
 *  </pre>
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: APanel.java,v 1.4 2006/07/30 00:51:27 jjanke Exp $
 * 
 *  Colin Rooney 2007/03/20 RFE#1670185 & related BUG#1684142 - Extend Sec to Info Queries
 *  @contributor Victor Perez , e-Evolution.SC FR [ 1757088 ]
 *  @contributor fer_luck@centuryon.com , FR [ 1757088 ]
 *  @author Teo Sarca, SC ARHIPAC SERVICE SRL
 *				<li>BF [ 1824621 ] History button can't be canceled
 *				<li>BF [ 1941271 ] VTreePanel is modifying even if is save wasn't successfull
 *				<li>FR [ 1943731 ] Window data export functionality
 *				<li>FR [ 1974354 ] VCreateFrom.create should be more flexible
 * 				<li>BF [ 1996056 ] Report error message is not displayed
 * 				<li>BF [ 1998575 ] Document Print is discarding any error
 *  @author Teo Sarca, teo.sarca@gmail.com
 *  			<li>BF [ 2876892 ] Save included tab before calling button action
 *  				https://sourceforge.net/tracker/?func=detail&aid=2876892&group_id=176962&atid=879332
 *  @author victor.perez@e-evolution.com 
 *  @see FR [ 1966328 ] New Window Info to MRP and CRP into View http://sourceforge.net/tracker/index.php?func=detail&aid=1966328&group_id=176962&atid=879335
 *  @autor tobi42, metas GmBH
 *  			<li>BF [ 2799362 ] You can press New button a lot of times
 *  @author Cristina Ghita, www.arhipac.ro
 *  @see FR [ 2877111 ] See identifiers columns when delete records https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2877111&group_id=176962
 * 
 * 	@author hengsin, hengsin.low@idalica.com
 *  @see FR [2887701] https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2887701&group_id=176962
 *  @sponsor www.metas.de
 */
public final class APanel extends CPanel
	implements DataStatusListener, ChangeListener, ActionListener, ASyncProcess
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6066778919781303581L;

	private boolean isNested = false;
	
	/**
	 * Constructs a new instance.
	 * Need to call initPanel for dynamic initialization
	 */
	//FR [ 1757088 ]
	public APanel(GridController gc, int windowNo){
		super();
		isNested = true;
		m_ctx = Env.getCtx();
		try{
			m_curGC = gc;
			gc.addDataStatusListener(this);
			m_curTab = gc.getMTab();
			
			Component tabElement = null;
			tabElement = gc;
			VTabbedPane tabPane = new VTabbedPane(false);
			tabPane.addTab(m_curTab.getName().toString(), m_curTab, tabElement);
			m_curWinTab = tabPane;
			m_curWindowNo = windowNo;
			jbInit();
			initSwitchLineAction();
		}
		catch(Exception e){
			log.log(Level.SEVERE, "", e);
		}
		
		createMenu();
		
		MRole role = MRole.getDefault(); 
		m_curGC.query (m_onlyCurrentRows, m_onlyCurrentDays, role.getMaxQueryRecords());
		m_curTab.navigateCurrent();     //  updates counter
		m_curGC.dynamicDisplay(0);
	}
	
	public APanel(AWindow window)
	{
		super();
		m_window = window;
				
		m_ctx = Env.getCtx();
		//
		try
		{
			jbInit();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
		createMenu();
	}	//	APanel

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(APanel.class);
	
	private AWindow m_window;
	private boolean isCancel = false; //Goodwill

	/**
	 *	Dispose
	 */
	public void dispose()
	{
	//	log.config("");
		//  ignore changes
		m_disposing = true;
		//
		if (m_curAPanelTab != null)
		{
			m_curAPanelTab.unregisterPanel();
			m_curAPanelTab = null;
		}
		//  close panels
		tabPanel.dispose(this);
		tabPanel = null;
		//  All Workbenches
		for (int i = 0; i < m_mWorkbench.getWindowCount(); i++)
		{
			m_curWindowNo = m_mWorkbench.getWindowNo(i);
			log.info("#" + m_curWindowNo);
			Env.setAutoCommit(m_ctx, m_curWindowNo, false);
			m_mWorkbench.dispose(i);
			Env.clearWinContext(m_ctx, m_curWindowNo);
		}   //  all Workbenchens

		//  Get rid of remaining model
		if (m_mWorkbench != null)
			m_mWorkbench.dispose();
		m_mWorkbench = null;
		//  MenuBar
		if (menuBar != null)
			menuBar.removeAll();
		menuBar = null;
		//  ToolBar
		if (toolBar != null)
			toolBar.removeAll();
		toolBar = null;
		//  Prepare GC
		this.removeAll();
	}	//	dispose

	/**
	 * The Layout.
	 */
	private BorderLayout mainLayout = new BorderLayout();
	private VTabbedPane tabPanel = new VTabbedPane(true);
	private StatusBar statusBar = new StatusBar();
	private CPanel northPanel = new CPanel();
	private JToolBar toolBar = new JToolBar();
	private JMenuBar menuBar = new JMenuBar();
	private FlowLayout northLayout = new FlowLayout();

	/**
	 * Initializes the state of this instance.
	 * @throws Exception
	 */
	private void jbInit() throws Exception
	{
		this.setLocale(Language.getLoginLanguage().getLocale());
		this.setLayout(mainLayout);

		//	tabPanel
		mainLayout.setHgap(2);
		mainLayout.setVgap(2);
		if (isNested)
			this.add(m_curGC, BorderLayout.CENTER);
		else
		{
			CPanel dummy = new CPanel();
			dummy.setLayout(new BorderLayout());
			dummy.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 2));
			dummy.add(tabPanel, BorderLayout.CENTER);
			this.add(dummy, BorderLayout.CENTER);
		}
		//	southPanel
		this.add(statusBar, BorderLayout.SOUTH);
		//	northPanel
		this.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(northLayout);
		northLayout.setAlignment(FlowLayout.LEFT);
		toolBar.putClientProperty("JToolBar.isRollover", Boolean.TRUE);
		toolBar.setBorderPainted(false);
		toolBar.setFloatable(false); // teo_sarca, [ 1666591 ] Toolbar should not be floatable 
		northPanel.add(toolBar, null);
	}	//	jbInit

	private AppsAction 		aPrevious, aNext, aParent, aDetail, aFirst, aLast,
							aNew, aCopy, aDelete, aPrint, aPrintPreview,
							aExport = null,
							aRefresh, aHistory, aAttachment, aChat, aMulti, aFind,
							aWorkflow, aZoomAcross, aRequest, aWinSize, aArchive;
	/** Ignore Button		*/
	public AppsAction		aIgnore;
	/** Save Button			*/
	public AppsAction		aSave;
	/** Private Lock Button	*/
	public AppsAction		aLock;
	//	Local (added to toolbar)
	private AppsAction	    aReport, aEnd, aHome, aHelp, aProduct, aLogout,
							aAccount, aCalculator, aCalendar, aEditor, aPreference, aScript,
							aOnline, aMailSupport, aAbout, aPrintScr, aScrShot, aExit, aBPartner, 
							aDeleteSelection, aShowAllWindow;
	
	private SwitchAction aSwitchLinesDownAction, aSwitchLinesUpAction;

	private WindowMenu m_WindowMenu;
	/**************************************************************************
	 *	Create Menu and Toolbar and registers keyboard actions.
	 *  - started from constructor
	 */
	private void createMenu()
	{
		/**
		 *	Menu
		 */
	//	menuBar.setHelpMenu();
		//								File
		JMenu mFile = AEnv.getMenu("File");
		menuBar.add(mFile);
		aPrintScr =	addAction("PrintScreen",	mFile,	KeyStroke.getKeyStroke(KeyEvent.VK_PRINTSCREEN, 0), 	false);
		aScrShot =	addAction("ScreenShot",		mFile,	KeyStroke.getKeyStroke(KeyEvent.VK_PRINTSCREEN, Event.SHIFT_MASK), 	false);
		aReport = 	addAction("Report",			mFile, 	KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0),	false);
		aPrint = 	addAction("Print",			mFile, 	KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0),	false);
		aPrintPreview = addAction("PrintPreview",	mFile, KeyStroke.getKeyStroke(KeyEvent.VK_P, Event.SHIFT_MASK+Event.ALT_MASK), false);
		if (MRole.getDefault().isCanExport())
		{
			aExport = addAction("Export", mFile, null, false);
		}
		mFile.addSeparator();
		aEnd =	 	addAction("End",			mFile, 	KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.ALT_MASK),	false);
		aLogout = 	addAction("Logout", 		mFile, 	KeyStroke.getKeyStroke(KeyEvent.VK_L, Event.SHIFT_MASK+Event.ALT_MASK), false);
		aExit =		addAction("Exit",			mFile, 	KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.SHIFT_MASK+Event.ALT_MASK),	false);
		//								Edit
		JMenu mEdit = AEnv.getMenu("Edit");
		menuBar.add(mEdit);
		aNew = 		addAction("New", 			mEdit, 	KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), false);
		aSave = 	addAction("Save",			mEdit, 	KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0),	false);
		mEdit.addSeparator();
		aCopy =		addAction("Copy", 			mEdit, 	KeyStroke.getKeyStroke(KeyEvent.VK_F2, Event.SHIFT_MASK),	false);
		aDelete = 	addAction("Delete",			mEdit, 	KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0),	false);
		aDeleteSelection = addAction("DeleteSelection", mEdit, KeyStroke.getKeyStroke(KeyEvent.VK_D, Event.CTRL_MASK), false);
		aIgnore = 	addAction("Ignore",			mEdit, 	KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),	false);
		aRefresh = 	addAction("Refresh",		mEdit, 	KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0),	false);
		mEdit.addSeparator();
		aFind = 	addAction("Find",			mEdit, 	KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0), true);	//	toggle
		if (m_isPersonalLock)			
			aLock = addAction("Lock",			mEdit, 	null,	true);		//	toggle
		//								View
		JMenu mView = AEnv.getMenu("View");
		menuBar.add(mView);
		if (MRole.getDefault().isAllow_Info_Product())
		{
			aProduct =	addAction("InfoProduct",	mView, 	KeyStroke.getKeyStroke(KeyEvent.VK_I, Event.ALT_MASK),	false);
		}
		if (MRole.getDefault().isAllow_Info_BPartner())		
		{
			aBPartner =	addAction("InfoBPartner",	mView, 	KeyStroke.getKeyStroke(KeyEvent.VK_I, Event.SHIFT_MASK+Event.ALT_MASK),	false);
		}
		if (MRole.getDefault().isShowAcct() && MRole.getDefault().isAllow_Info_Account())
		{
			aAccount =  addAction("InfoAccount",mView, 	KeyStroke.getKeyStroke(KeyEvent.VK_I, Event.ALT_MASK+Event.CTRL_MASK),	false);
		}
		if (MRole.getDefault().isAllow_Info_Schedule())
		{
			AEnv.addMenuItem("InfoSchedule", null, null, mView, this);			
		}
		//FR [ 1966328 ] 
		if (MRole.getDefault().isAllow_Info_MRP())
		{
			AEnv.addMenuItem("InfoMRP", "Info", null, mView, this);	
		}
		if (MRole.getDefault().isAllow_Info_CRP())
		{
			AEnv.addMenuItem("InfoCRP", "Info", null, mView, this);	
		}
		mView.addSeparator();
		if (MRole.getDefault().isAllow_Info_Order())
		{
			AEnv.addMenuItem("InfoOrder", "Info", null, mView, this);	
		}
		if (MRole.getDefault().isAllow_Info_Invoice())
		{
			AEnv.addMenuItem("InfoInvoice", "Info", null, mView, this);			
		}
		if (MRole.getDefault().isAllow_Info_InOut())
		{
			AEnv.addMenuItem("InfoInOut", "Info", null, mView, this);	
		}
		if (MRole.getDefault().isAllow_Info_Payment())
		{
			AEnv.addMenuItem("InfoPayment", "Info", null, mView, this);	
		}
		if (MRole.getDefault().isAllow_Info_CashJournal())
		{
			AEnv.addMenuItem("InfoCashLine", "Info", null, mView, this);	
		}
		if (MRole.getDefault().isAllow_Info_Resource())
		{
			AEnv.addMenuItem("InfoAssignment", "Info", null, mView, this);	
		}
		if (MRole.getDefault().isAllow_Info_Asset())
		{
			AEnv.addMenuItem("InfoAsset", "Info", null, mView, this);	
		}

		

		mView.addSeparator();
		aAttachment = addAction("Attachment",	mView, 	KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0),	true);		//	toggle
		aChat = addAction("Chat",				mView, 	null,	true);		//	toggle
		aHistory = 	addAction("History",		mView, 	KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0),	true);		//	toggle
		mView.addSeparator();
		aMulti =	addAction("Multi",			mView, 	KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0),	true);		//	toggle
		//								Go
		JMenu mGo = AEnv.getMenu("Go");
		menuBar.add(mGo);
		aFirst =	addAction("First", 			mGo, 	KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_UP, Event.ALT_MASK),	false);
		aPrevious = addAction("Previous", 		mGo, 	KeyStroke.getKeyStroke(KeyEvent.VK_UP, Event.ALT_MASK),	false);
		aNext = 	addAction("Next", 			mGo, 	KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, Event.ALT_MASK),	false);
		aLast =		addAction("Last",	 		mGo, 	KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN, Event.ALT_MASK),	false);
		mGo.addSeparator();
		aParent =	addAction("Parent", 		mGo, 	KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, Event.ALT_MASK),	false);
		aDetail =	addAction("Detail", 		mGo,	KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, Event.ALT_MASK),	false);
		mGo.addSeparator();
		aZoomAcross = addAction("ZoomAcross",	mGo, 	null,	false);
		aRequest =  addAction("Request",		mGo, 	null,	false);
		aArchive =  addAction("Archive",		mGo, 	null,	false);
		aHome =		addAction("Home", 			mGo,	null,	false);
		//								Tools
		JMenu mTools = AEnv.getMenu("Tools");
		menuBar.add(mTools);
		aCalculator = addAction("Calculator",	mTools, 	null,	false);
		aCalendar = addAction("Calendar",		mTools, 	null,	false);
		aEditor =	addAction("Editor",			mTools, 	null,	false);
		MUser user = MUser.get(Env.getCtx());
		if (user.isAdministrator())
			aScript = addAction("Script",	        mTools, 	null,	false);
		if ("Y".equals(Env.getContext(m_ctx, "#SysAdmin")))	//	set in DB.loginDB
			aWinSize = addAction("WinSize",     mTools, 	null,	false);
		if (AEnv.isWorkflowProcess())
			aWorkflow = addAction("WorkFlow",	mTools,		null,	false);
		if (MRole.getDefault().isShowPreference())
		{
			mTools.addSeparator();
			aPreference = addAction("Preference",	mTools, 	null,	false);
		}
		
		//Window
		AMenu aMenu = (AMenu)Env.getWindow(0);
		m_WindowMenu = new WindowMenu(aMenu.getWindowManager(), m_window);
		menuBar.add(m_WindowMenu);
		aShowAllWindow = addAction("ShowAllWindow", null, KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_MASK),	false);
		
		//								Help
		JMenu mHelp = AEnv.getMenu("Help");
		menuBar.add(mHelp);
		aHelp = 	addAction("Help",			mHelp, 	KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0),	false);
		aOnline =	addAction("Online",			mHelp, 	null,	false);
		aMailSupport = addAction("EMailSupport",	mHelp,	null,	false);
		aAbout = 	addAction("About",			mHelp, 	null,	false);

		/**
		 *	ToolBar
		 */
		toolBar.add(aIgnore.getButton());		//	ESC
		toolBar.addSeparator();
		toolBar.add(aHelp.getButton());			//	F1
		toolBar.add(aNew.getButton());
		toolBar.add(aCopy.getButton());
		toolBar.add(aDelete.getButton());
		toolBar.add(aDeleteSelection.getButton());
		toolBar.add(aSave.getButton());
		toolBar.addSeparator();
		toolBar.add(aRefresh.getButton());      //  F5
		toolBar.add(aFind.getButton());
		toolBar.add(aAttachment.getButton());
		toolBar.add(aChat.getButton());
		toolBar.add(aMulti.getButton());
		toolBar.addSeparator();
		//FR [ 1757088 ]
		if((m_curGC == null) || (m_curGC != null && !m_curGC.isDetailGrid())){
			toolBar.add(aHistory.getButton());		//	F9
			toolBar.add(aHome.getButton()); //	F10 is Windows Menu Key
			toolBar.add(aParent.getButton());
			toolBar.add(aDetail.getButton());
			toolBar.addSeparator();
		}

		toolBar.add(aFirst.getButton());
		toolBar.add(aPrevious.getButton());
		toolBar.add(aNext.getButton());
		toolBar.add(aLast.getButton());
		toolBar.addSeparator();
		toolBar.add(aReport.getButton());
		toolBar.add(aArchive.getButton());
		toolBar.add(aPrintPreview.getButton());
		toolBar.add(aPrint.getButton());
		// FR [ 1757088 ]
		if((m_curGC == null) || (m_curGC != null && !m_curGC.isDetailGrid())){
			toolBar.addSeparator();
			if (m_isPersonalLock)
				toolBar.add(aLock.getButton());
			toolBar.add(aZoomAcross.getButton());
			if (aWorkflow != null)
				toolBar.add(aWorkflow.getButton());
			toolBar.add(aRequest.getButton());
			if (MRole.getDefault().isAllow_Info_Product())
			{
				toolBar.add(aProduct.getButton());
			}
			toolBar.addSeparator();
			toolBar.add(aEnd.getButton());
		}

		//
		if (CLogMgt.isLevelAll())
			Util.printActionInputMap(this);
	}	//	createMenu


	/**
	 *	Add (Toggle) Action to Toolbar and Menu
	 *  @param actionName action name
	 *  @param menu manu
	 *  @param accelerator accelerator
	 *  @param toggle toggle button
	 *  @return AppsAction
	 */
	private AppsAction addAction (String actionName, JMenu menu, KeyStroke accelerator, boolean toggle)
	{
		AppsAction action = new AppsAction(actionName, accelerator, toggle);
		if (menu != null)
			menu.add(action.getMenuItem());
		action.setDelegate(this);
	//	AbstractButton b = action.getButton();
	//	String s = null;
	//	if (b != null)
	//		s = b.getToolTipText();
		
		//	Key Strokes
		if (accelerator != null)
		{
			getInputMap(WHEN_IN_FOCUSED_WINDOW).put(accelerator, actionName);
			getActionMap().put(actionName, action);
		}
		//
		return action;
	}	//	addAction

	/**
	 *	Return MenuBar
	 *  @return JMenuBar
	 */
	public JMenuBar getMenuBar()
	{
		return menuBar;
	}	//	getMenuBar

	/**
	 *	Get Title of Window
	 *  @return String with Title
	 */
	public String getTitle()
	{
		if (m_mWorkbench != null && m_mWorkbench.getWindowCount() > 1)
		{
			StringBuffer sb = new StringBuffer();
			sb.append(m_mWorkbench.getName()).append("  ")
				.append(Env.getContext(m_ctx, "#AD_User_Name")).append("@")
				.append(Env.getContext(m_ctx, "#AD_Client_Name")).append(".")
				.append(Env.getContext(m_ctx, "#AD_Org_Name")).append(" [")
				.append(Env.getContext(m_ctx, "#DB_UID")).append("]");
			return sb.toString();
		}
		return Env.getHeader(m_ctx, m_curWindowNo);
	}	//	getTitle


	private Properties      m_ctx;

	/** Workbench Model                                 */
	private GridWorkbench	m_mWorkbench;
	/** Current MTab                                    */
	private GridTab			m_curTab;
	/** Current GridController                          */
	private GridController  m_curGC;
	/** Current Window Panel                            */
	private JTabbedPane     m_curWinTab = null;
	/** Current Window No                               */
	private int				m_curWindowNo;
	/** Current Window Panel Index                      */
	private int				m_curTabIndex = -1;
	/**	Current Tab Order								*/
	private APanelTab		m_curAPanelTab = null;

	/** Dispose active                                  */
	private boolean         m_disposing = false;
	/** Save Error Message indicator                    */
	private boolean         m_errorDisplayed = false;
	/** Only current row flag                           */
	private boolean			m_onlyCurrentRows = true;
	/** Number of days to show	0=all					*/
	private int				m_onlyCurrentDays = 0;
	/** Process Info                                    */
	private boolean         m_isLocked = false;
	/** Show Personal Lock								*/
	private boolean 		m_isPersonalLock = MRole.getDefault().isPersonalLock();
	/**	Last Modifier of Action Event					*/
	private int 			m_lastModifiers;

	private HashMap<Integer, GridController> includedMap;

	
	/**************************************************************************
	 *	Dynamic Panel Initialization - either single window or workbench.
	 *  <pre>
	 *  either
	 *  - Workbench tabPanel    (VTabbedPane)
	 *      - Tab               (GridController)
	 *  or
	 *  - Workbench tabPanel    (VTabbedPane)
	 *      - Window            (VTabbedPane)
	 *          - Tab           (GridController)
	 *  </pre>
	 *  tabPanel
	 *  @param AD_Workbench_ID  if > 0 this is a workbench, AD_Window_ID ignored
	 *  @param AD_Window_ID     if not a workbench, Window ID
	 *  @param query			if not a Workbench, Zoom Query - additional SQL where clause
	 *  @return true if Panel is initialized successfully
	 */
	public boolean initPanel (int AD_Workbench_ID, int AD_Window_ID, MQuery query)
	{
		log.info("WB=" + AD_Workbench_ID + ", Win=" + AD_Window_ID + ", Query=" + query);
		this.setName("APanel" + AD_Window_ID);

		//  Single Window
		if (AD_Workbench_ID == 0)
			m_mWorkbench = new GridWorkbench(m_ctx, AD_Window_ID);
		else
		//  Workbench
		{
		//	m_mWorkbench = new MWorkbench(m_ctx);
		//	if (!m_mWorkbench.initWorkbench (AD_Workbench_ID))
		//	{
		//		log.log(Level.SEVERE, "APanel.initWindow - No Workbench Model");
		//		return false;
		//	}
		//	tabPanel.setWorkbench(true);
		//	tabPanel.addChangeListener(this);
			ADialog.warn(0, this, "","Not implemented yet");
			return false;
		}

		Dimension windowSize = m_mWorkbench.getWindowSize();
		
		/**
		 *  WorkBench Loop
		 */
		for (int wb = 0; wb < m_mWorkbench.getWindowCount(); wb++)
		{
			//  Get/set WindowNo
			m_curWindowNo = Env.createWindowNo (this);			                //  Timing: ca. 1.5 sec
			m_mWorkbench.setWindowNo(wb, m_curWindowNo);
			//  Set AutoCommit for this Window
			Env.setAutoCommit(m_ctx, m_curWindowNo, Env.isAutoCommit(m_ctx));
			boolean autoNew = Env.isAutoNew(m_ctx);
			Env.setAutoNew(m_ctx, m_curWindowNo, autoNew);

			//  Workbench Window
			VTabbedPane window = null;
			//  just one window
			if (m_mWorkbench.getWindowCount() == 1)
			{
				window = tabPanel;
				window.setWorkbench(false);
			}
			else
			{
				VTabbedPane tp = new VTabbedPane(false);
				window = tp;
			}
			//  Window Init
			window.addChangeListener(this);

			/**
			 *  Init Model
			 */
			int wbType = m_mWorkbench.getWindowType(wb);

			/**
			 *  Window
			 */
			if (wbType == GridWorkbench.TYPE_WINDOW)
			{
				includedMap = new HashMap<Integer,GridController>(4);
				//
				GridWindowVO wVO = AEnv.getMWindowVO(m_curWindowNo, m_mWorkbench.getWindowID(wb), 0);
				if (wVO == null)
				{
					ADialog.error(0, null, "AccessTableNoView", "(No Window Model Info)");
					return false;
				}
				GridWindow mWindow = new GridWindow (wVO);			                //  Timing: ca. 0.3-1 sec
				//	Set SO/AutoNew for Window
				Env.setContext(m_ctx, m_curWindowNo, "IsSOTrx", mWindow.isSOTrx());
				if (!autoNew && mWindow.isTransaction())
					Env.setAutoNew(m_ctx, m_curWindowNo, true);
				m_mWorkbench.setMWindow(wb, mWindow);
				if (wb == 0)
					m_onlyCurrentRows = mWindow.isTransaction();	//	default = only current
				if (windowSize == null)
					windowSize = mWindow.getWindowSize();

				/**
				 *  Window Tabs
				 */
				int tabSize = mWindow.getTabCount();
				boolean goSingleRow = query != null;	//	Zoom Query
				for (int tab = 0; tab < tabSize; tab++)
				{
					boolean included = false;
					//  MTab
					if (tab == 0) mWindow.initTab(0);
					GridTab gTab = mWindow.getTab(tab);
					Env.setContext(m_ctx, m_curWindowNo, tab, GridTab.CTX_TabLevel, Integer.toString(gTab.getTabLevel()));
					//  Query first tab
					if (tab == 0)
					{
						//  initial user query for single workbench tab
						if (m_mWorkbench.getWindowCount() == 1)
						{
							isCancel = false; //Goodwill
							query = initialQuery (query, gTab);
							if (isCancel) return false; //Cancel opening window
							if (query != null && query.getRecordCount() <= 1)
								goSingleRow = true;
						}
						else if (wb != 0)
						//  workbench dynamic query for dependent windows
						{
							query = m_mWorkbench.getQuery();
						}
						//	Set initial Query on first tab
						if (query != null)
						{
							m_onlyCurrentRows = false;  //  Query might involve history
							gTab.setQuery(query);
						}
						if (wb == 0)
							m_curTab = gTab;
					}	//	query on first tab

					Component tabElement = null;
					//  GridController
					if (gTab.isSortTab())
					{
						VSortTab st = new VSortTab(m_curWindowNo, gTab.getAD_Table_ID(),
							gTab.getAD_ColumnSortOrder_ID(), gTab.getAD_ColumnSortYesNo_ID());
						st.setTabLevel(gTab.getTabLevel());
						tabElement = st;
					}
					else	//	normal tab
					{
						GridController gc = new GridController();			        //  Timing: ca. .1 sec
						CompiereColor cc = mWindow.getColor();
						if (cc != null)
							gc.setBackgroundColor(cc);                  //  set color on Window level
						gc.initGrid(gTab, false, m_curWindowNo, this, mWindow, (tab != 0));  //  will set color on Tab level
																		//  Timing: ca. 6-7 sec for first .2 for next
						gc.addDataStatusListener(this);
						gc.registerESCAction(aIgnore);      //  register Escape Key
						//	Set First Tab
						if (wb == 0 && tab == 0)
						{
							m_curGC = gc;
							Dimension size = gc.getPreferredSize();     //  Screen Sizing
							size.width += 4;
							size.height += 4;
							gc.setPreferredSize(size);
						}
						tabElement = gc;
						//	If we have a zoom query, switch to single row
						if (tab == 0 && goSingleRow)
							gc.switchSingleRow();
						
                        // FR [ 1757088 ]
						GridField[] fields = gc.getMTab().getFields();
						int m_tab_id = 0;
						for(int f =0 ; f < fields.length ; f ++)
						{
							m_tab_id = fields[f].getIncluded_Tab_ID();						  
							if ( m_tab_id != 0)
							{
								includedMap.put(m_tab_id, gc);
							}
						}  

						//	Is this tab included?
						if (includedMap.size() > 0)
						{
							GridController parent = (GridController)includedMap.get(new Integer(gTab.getAD_Tab_ID()));
							if (parent != null)
							{
								// FR [ 1757088 ]
								gc.removeDataStatusListener(this);
								GridSynchronizer synchronizer = new GridSynchronizer(mWindow, parent, gc);
								if (parent == m_curGC)
									synchronizer.activateChild();
								included = parent.includeTab(gc,this,synchronizer);								
							}
						}
						initSwitchLineAction();
					}	//	normal tab

					if (!included)	//  Add to TabbedPane
					{
						StringBuffer tabName = new StringBuffer ();
						tabName.append ("<html>");
						if (gTab.isReadOnly())
							tabName.append("<i>");
						int pos = gTab.getName ().indexOf (" ");
						if (pos == -1)
							tabName.append (gTab.getName ()).append ("<br>&nbsp;");
						else
						{
							tabName.append (gTab.getName().substring (0, pos))
							  .append ("<br>")
							  .append (gTab.getName().substring(pos + 1));
						}
						if (gTab.isReadOnly())
							tabName.append("</i>");
						tabName.append ("</html>");
						//	Add Tab - sets ALT-<number> and Shift-ALT-<x>
						window.addTab (tabName.toString(), gTab, tabElement);
					}
				}   //  Tab Loop
			//  Tab background
			//	window.setBackgroundColor(new AdempiereColor(Color.magenta, Color.green));
			}   //  Type-MWindow

			//  Single Workbench Window Tab
			if (m_mWorkbench.getWindowCount() == 1)
			{
				window.setToolTipText(m_mWorkbench.getDescription(wb));
			}
			else
			//  Add Workbench Window Tab
			{
				tabPanel.addTab(m_mWorkbench.getName(wb), m_mWorkbench.getIcon(wb), window, m_mWorkbench.getDescription(wb));
			}
			//  Used for Env.getHeader
			Env.setContext(m_ctx, m_curWindowNo, "WindowName", m_mWorkbench.getName(wb));

		}   //  Workbench Loop

		//  stateChanged (<->) triggered
		toolBar.setName(getTitle());
		m_curTab.getTableModel().setChanged(false);
		//	Set Detail Button
		aDetail.setEnabled(0 != m_curWinTab.getTabCount()-1);

		//	Enable/Disable Tabs dynamically
		if (m_curWinTab instanceof VTabbedPane)
			((VTabbedPane)m_curWinTab).evaluate(null);
		//	Size
		if (windowSize != null)
			setPreferredSize(windowSize);
		else
			revalidate();
		
		if (zoomToDetailTab(query)) {
			return true;			
		}
		
		Dimension size = getPreferredSize();
		log.info( "fini - " + size);
		m_curWinTab.requestFocusInWindow();
		return true;
	}	//	initPanel

	private boolean zoomToDetailTab(MQuery query) {
		if (query != null && query.getZoomTableName() != null && query.getZoomColumnName() != null)
    	{
    		GridTab gTab = m_mWorkbench.getMWindow(0).getTab(0);
    		if (!query.getZoomTableName().equalsIgnoreCase(gTab.getTableName()))
    		{
    			int tabSize = m_mWorkbench.getMWindow(0).getTabCount();

    	        for (int tab = 0; tab < tabSize; tab++)
    	        {
    	        	gTab = m_mWorkbench.getMWindow(0).getTab(tab);
    	        	if (gTab.isSortTab())
    	        		continue;
    	        	
    	        	if (gTab.getTabLevel() == 1 && gTab.getTableName().equalsIgnoreCase(query.getZoomTableName()))
    	        	{
    	        		GridField[] fields = gTab.getFields();
    	        		for (GridField field : fields)
    	        		{
    	        			if (field.getColumnName().equalsIgnoreCase(query.getZoomColumnName()))
    	        			{
    	        				if (query.getZoomValue() != null && query.getZoomValue() instanceof Integer)
    	        				{    	        					
    	        					if (!includedMap.containsKey(gTab.getAD_Tab_ID()))
    	        					{
    	        						m_mWorkbench.getMWindow(0).initTab(tab);
    	        						int index = tabPanel.findTabindex(gTab);
    	        						if (index >= 0)
    	        						{
    	        							GridController gc = (GridController) tabPanel.getComponentAt(index);
    	        							gc.activate();
    	        							gc.query(false, 0, 0);
    	        						}
    	        					}	
        	        				GridTable table = gTab.getTableModel();
        	        				int count = table.getRowCount();
        	        				for(int i = 0; i < count; i++)
        	        				{
        	        					int id = table.getKeyID(i);
        	        					if (id == ((Integer)query.getZoomValue()).intValue())
        	        					{
        	        						if (!includedMap.containsKey(gTab.getAD_Tab_ID()))
        	        						{
        	        							int index = tabPanel.findTabindex(gTab);
            	        						if (index >= 0)
            	        							tabPanel.setSelectedIndex(index);
        	        						}
        	        						gTab.setCurrentRow(i);
        	        						return true;
        	        					}
        	        				}
    	        				}
    	        				else
    	        				{
    	        					if (!includedMap.containsKey(gTab.getAD_Tab_ID()))
    	        					{
    	        						int index = tabPanel.findTabindex(gTab);
    	        						if (index >= 0)
    	        							tabPanel.setSelectedIndex(index);
    	        					}
	        						return true;
    	        				}
    	        				break;
    	        			}
    	        		}
    	        	}
    	        }
    		}
    	}
        return false;
	}

	/**
	 * 	Get Current Window No
	 *	@return win no
	 */
	public int getWindowNo()
	{
		return m_curWindowNo;
	}	//	getWindowNo
	
	/**
	 * 	Initial Query
	 *	@param query initial query
	 *	@param mTab tab
	 *	@return query or null
	 */
	private MQuery initialQuery (MQuery query, GridTab mTab)
	{
		MRole role = MRole.getDefault(m_ctx, false);
		//	We have a (Zoom) query
		if (query != null && query.isActive() && !role.isQueryMax(query.getRecordCount()))
			return query;
		//
		StringBuffer where = new StringBuffer(Env.parseContext(m_ctx, m_curWindowNo, mTab.getWhereExtended(), false));
		//	Query automatically if high volume and no query
		boolean require = mTab.isHighVolume();
		if (!require && !m_onlyCurrentRows)				//	No Trx Window
		{
			/*  Where Extended already appended above, check for variables */
			if (query != null)
			{
				String wh2 = query.getWhereClause();
				if (wh2.length() > 0)
				{
					if (where.length() > 0)
						where.append (" AND ");
					where.append(wh2);
				}
			}
			//
			StringBuffer sql = new StringBuffer("SELECT COUNT(*) FROM ")
				.append(mTab.getTableName());
			if (where.length() > 0)
				sql.append(" WHERE ").append(where);
			//	Does not consider security
			int no = DB.getSQLValue(null, sql.toString());
			//
			require = MRole.getDefault().isQueryRequire(no);
		}
		//	Show Query
		if (require)
		{
			GridField[] findFields = mTab.getFields();
			Find find = new Find (Env.getFrame(this), m_curWindowNo, mTab.getName(),
				mTab.getAD_Tab_ID(), mTab.getAD_Table_ID(), mTab.getTableName(), 
				where.toString(), findFields, 10);	//	no query below 10
			query = find.getQuery();
			isCancel = (query == null);//Goodwill
			find.dispose();
			find = null;
		}
		return query;
	}	//	initialQuery
	
	
	/**
	 *  Get Window Index
	 *  @return Window Index
	 */
	private int getWindowIndex()
	{
		//  only one window
		if (m_mWorkbench.getWindowCount() == 1)
			return 0;
		//  workbench
		return tabPanel.getSelectedIndex();
	}   //  getWindowIndex

	/**
	 *  Is first Tab (on Window)
	 *  @return true if the panel displays the first tab
	 */
	private boolean isFirstTab()
	{
		return m_curWinTab.getSelectedIndex() == 0;
	}   //  isFirstTab

	/**
	 * 	Get Window Image
	 *	@return image or null
	 */
	public Image getImage()
	{
		return m_mWorkbench.getImage(getWindowIndex());
	}	//	getImage
	
	
	/**************************************************************************
	 *	Data Status Listener (row change)			^ | v
	 *  @param e event
	 */
	public void dataStatusChanged (DataStatusEvent e)
	{
		if (m_disposing)
			return;
		log.info(e.getMessage());
		String dbInfo = e.getMessage();
		if (m_curTab != null && m_curTab.isQueryActive())
			dbInfo = "[ " + dbInfo + " ]";
		statusBar.setStatusDB(dbInfo, e);
		if (!isNested)
			m_window.setTitle(getTitle());

		//	Set Message / Info
		if (e.getAD_Message() != null || e.getInfo() != null)
		{
			StringBuffer sb = new StringBuffer();
			String msg = e.getMessage();
			if (msg != null && msg.length() > 0)
				sb.append(Msg.getMsg(m_ctx, e.getAD_Message()));
			String info = e.getInfo();
			if (info != null && info.length() > 0)
			{
				if (sb.length() > 0 && !sb.toString().trim().endsWith(":"))
					sb.append(": ");
				sb.append(info);
			}
			if (sb.length() > 0)
			{
				int pos = sb.indexOf("\n");
				if (pos != -1)  // replace CR/NL
					sb.replace(pos, pos+1, " - ");
				setStatusLine (sb.toString (), e.isError ());
			}
		}

		//  Confirm Error
		if (e.isError() && !e.isConfirmed())
		{
			ADialog.error(m_curWindowNo, this, e.getAD_Message(), e.getInfo());
			e.setConfirmed(true);   //  show just once - if MTable.setCurrentRow is involved the status event is re-issued
			m_errorDisplayed = true;
		}
		//  Confirm Warning
		else if (e.isWarning() && !e.isConfirmed())
		{
			ADialog.warn(m_curWindowNo, this, e.getAD_Message(), e.getInfo());
			e.setConfirmed(true);   //  show just once - if MTable.setCurrentRow is involved the status event is re-issued
		}

		//	update Navigation
		boolean firstRow = e.isFirstRow();
		aFirst.setEnabled(!firstRow);
		aPrevious.setEnabled(!firstRow);
		boolean lastRow = e.isLastRow();
		aNext.setEnabled(!lastRow);
		aLast.setEnabled(!lastRow);

		//	update Change
		
		boolean changed = e.isChanged() || e.isInserting();
		int changedColumn = e.getChangedColumn();
		boolean inserting = e.isInserting();
		
		if(e.getAD_Message() != null && e.getAD_Message().equals("Saved"))
			changed = false;
		boolean readOnly = m_curTab.isReadOnly();
		boolean insertRecord = !readOnly;
		if (insertRecord)
			insertRecord = m_curTab.isInsertRecord();
		aNew.setEnabled(((inserting && changedColumn>0) || !inserting) && insertRecord);
		aCopy.setEnabled(!changed && insertRecord);
		aRefresh.setEnabled(!changed);
		aDelete.setEnabled(!changed && !readOnly);
		//
		if (readOnly && m_curTab.isAlwaysUpdateField())
			readOnly = false;
		aIgnore.setEnabled(changed && !readOnly);
		aSave.setEnabled(changed && !readOnly);
		//
		//	No Rows
		if (e.getTotalRows() == 0 && insertRecord) {
			aNew.setEnabled(true);
			aDelete.setEnabled(false);
			aDeleteSelection.setEnabled(false);
		} else {
			aDeleteSelection.setEnabled(true);
		}

		//	Single-Multi
		aMulti.setPressed(!m_curGC.isSingleRow());

		//	History	(on first Tab only)
		if (isFirstTab())
			aHistory.setPressed(!m_curTab.isOnlyCurrentRows());

		//	Transaction info
		String trxInfo = m_curTab.getTrxInfo();
		if (trxInfo != null)
			statusBar.setInfo(trxInfo);

		//	Check Attachment
		boolean canHaveAttachment = m_curTab.canHaveAttachment();		//	not single _ID column
		//
		if (canHaveAttachment && e.isLoading() && m_curTab.getCurrentRow() > e.getLoadedRows())
			canHaveAttachment = false;
		if (canHaveAttachment && m_curTab.getRecord_ID() == -1)    //	No Key
			canHaveAttachment = false;
		if (canHaveAttachment)
		{
			aAttachment.setEnabled(true);
			aAttachment.setPressed(m_curTab.hasAttachment());
			aChat.setEnabled(true);
			aChat.setPressed(m_curTab.hasChat());
		}
		else
		{
			aAttachment.setEnabled(false);
			aChat.setEnabled(false);
		}
		//	Lock Indicator
		if (m_isPersonalLock)
			aLock.setPressed(m_curTab.isLocked());

		if (m_curWinTab instanceof VTabbedPane)
			((VTabbedPane)m_curWinTab).evaluate(e);
	//	log.info("- fini", e.getMessage());
	}	//	dataStatusChanged

	/**
	 *	Set Status Line to text
	 *  @param text clear text
	 *  @param error error flag
	 */
	public void setStatusLine (String text, boolean error)
	{
		log.fine(text);
		statusBar.setStatusLine(text, error);
	}	//	setStatusLine

	/**
	 *	Indicate Busy
	 *  @param busy busy
	 *  @param focus request focus
	 */
	private void setBusy (boolean busy, boolean focus)
	{
		m_isLocked = busy;
		//
		JFrame frame = Env.getFrame(this);
		if (frame == null)  //  during init
			return;
		if (frame instanceof AWindow)
			((AWindow)frame).setBusy(busy);
	//	String processing = Msg.getMsg(m_ctx, "Processing");
		if (busy)
		{
	//		setStatusLine(processing);
			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		}
		else
		{
			this.setCursor(Cursor.getDefaultCursor());
			frame.setCursor(Cursor.getDefaultCursor());
			if (focus)
				m_curGC.requestFocus();
	//		if (statusBar.getStatusLine().equals(processing))
	//			statusBar.setStatusLine("");
		}
	}	//	set Busy

	
	/**************************************************************************
	 *	Change Listener - (tab change)			<->
	 *  @param e event
	 */
	public void stateChanged (ChangeEvent e)
	{
		if (m_disposing)
			return;
		log.info(e.toString());
		setBusy(true, true);

		VTabbedPane tp = (VTabbedPane)e.getSource();
		boolean back = false;
		boolean isAPanelTab = false;

		//int previousIndex = 0;

		//  Workbench Tab Change
		if (tp.isWorkbench())
		{
			int WBIndex = tabPanel.getSelectedIndex();
			m_curWindowNo = m_mWorkbench.getWindowNo(WBIndex);
			//  Window Change
			log.info("curWin=" + m_curWindowNo + " - Win=" + tp);
			if (tp.getSelectedComponent() instanceof JTabbedPane)
				m_curWinTab = (JTabbedPane)tp.getSelectedComponent();
			else
				throw new java.lang.IllegalArgumentException("Window does not contain Tabs");
			if (m_curWinTab.getSelectedComponent() instanceof GridController) {
				m_curGC = (GridController)m_curWinTab.getSelectedComponent();
				initSwitchLineAction();
			}
		//	else if (m_curWinTab.getSelectedComponent() instanceof APanelTab)
		//		isAPanelTab = true;
			else
				throw new java.lang.IllegalArgumentException("Window-Tab does not contain GridControler");
			//  change pointers
			m_curTabIndex = m_curWinTab.getSelectedIndex();
		}
		else
		{
			//  Just a Tab Change
			log.info("Tab=" + tp);
			m_curWinTab = tp;
			int tpIndex = m_curWinTab.getSelectedIndex();
			//	detect no tab change
			if (tpIndex == m_curTabIndex) return;
			back = tpIndex < m_curTabIndex;
			GridController gc = null;
			if (m_curWinTab.getSelectedComponent() instanceof GridController)
				gc = (GridController)m_curWinTab.getSelectedComponent();
			else if (m_curWinTab.getSelectedComponent() instanceof APanelTab)
				isAPanelTab = true;
			else
				throw new java.lang.IllegalArgumentException("Tab does not contain GridControler");
			//  Save old Tab
			if (m_curGC != null)
			{
				m_curGC.stopEditor(true);
				//  has anything changed?
				if (m_curTab.needSave(true, false))
				{   //  do we have real change
					if (m_curTab.needSave(true, true))
					{
						//	Automatic Save
						if (Env.isAutoCommit(m_ctx, m_curWindowNo))
						{
							if (!m_curTab.dataSave(true))
							{	//  there is a problem, so we go back
								showLastError();
								m_curWinTab.setSelectedIndex(m_curTabIndex);
								setBusy(false, true);
								return;
							}
						}
						//  explicitly ask when changing tabs
						else if (ADialog.ask(m_curWindowNo, this, "SaveChanges?", m_curTab.getCommitWarning()))
						{   //  yes we want to save
							if (!m_curTab.dataSave(true))
							{   //  there is a problem, so we go back
								showLastError();
								m_curWinTab.setSelectedIndex(m_curTabIndex);
								setBusy(false, true);
								return;
							}
						}
						else    //  Don't save
							m_curTab.dataIgnore();
					}
					else    //  new record, but nothing changed
						m_curTab.dataIgnore();
				}   //  there is a change
			}
			if (m_curAPanelTab != null)
			{
				m_curAPanelTab.saveData();
				m_curAPanelTab.unregisterPanel();
				m_curAPanelTab = null;
			}

			//	new tab
		//	if (m_curTabIndex >= 0)
		//		m_curWinTab.setForegroundAt(m_curTabIndex, AdempierePLAF.getTextColor_Normal());
		//	m_curWinTab.setForegroundAt(tpIndex, AdempierePLAF.getTextColor_OK());
		//	previousIndex = m_curTabIndex;
			m_curTabIndex = tpIndex;
			if (!isAPanelTab) {
				m_curGC = gc;
				initSwitchLineAction();
			}
		}

		//	Sort Tab Handling
		if (isAPanelTab)
		{
			m_curAPanelTab = (APanelTab)m_curWinTab.getSelectedComponent();
			m_curAPanelTab.registerAPanel(this);
			m_curAPanelTab.loadData();
			// Consider that APanelTab (e.g. VSortTab) is not navigable - teo_sarca [ 1705444 ]
			aFirst.setEnabled(false);
			aPrevious.setEnabled(false);
			aNext.setEnabled(false);
			aLast.setEnabled(false);
		}
		else	//	Cur Tab Setting
		{
			int gwTabIndex = m_mWorkbench.getMWindow(0).getTabIndex(m_curGC.getMTab());
			//boolean needValidate = false;
			if (m_mWorkbench.getMWindow(0).isTabInitialized(gwTabIndex) == false)
			{
				m_mWorkbench.getMWindow(0).initTab(gwTabIndex);
				//needValidate = true;
			}
			m_curGC.activate();
			m_curTab = m_curGC.getMTab();

			//	Refresh only current row when tab is current
			if (back && m_curTab.isCurrent())
				m_curTab.dataRefresh();
			else	//	Requery & autoSize
			{
				MRole role = MRole.getDefault(); 
				m_curGC.query (m_onlyCurrentRows, m_onlyCurrentDays, role.getMaxQueryRecords());
				/*
				if (m_curGC.isNeedToSaveParent())
				{
					// there is a problem, so we go back
					ADialog.error(m_curWindowNo, this, "SaveParentFirst");
					m_curWinTab.setSelectedIndex(previousIndex);
					setBusy(false, true);
					return;
				}*/
			}
			//  Set initial record
			if (m_curTab.getRowCount() == 0)
			{
				//	Automatically create New Record, if none & tab not RO
				if (!m_curTab.isReadOnly() 
					&& (Env.isAutoNew(m_ctx, m_curWindowNo) || m_curTab.isQueryNewRecord()))
				{
					log.config("No record - New - AutoNew=" + Env.isAutoNew(m_ctx, m_curWindowNo)
						+ " - QueryNew=" + m_curTab.isQueryNewRecord());
					m_curTab.dataNew(false);
				}
				else	//	No Records found
				{
					aSave.setEnabled(false);
					aDelete.setEnabled(false);
					aDeleteSelection.setEnabled(false);
				}
				m_curTab.navigateCurrent();     //  updates counter
				m_curGC.dynamicDisplay(0);
			}
			/*
			if (needValidate)
			{
				JFrame frame = Env.getFrame(APanel.this);
				if (frame != null)
				{
					//not sure why, the following lines is needed to make dynamic resize work
					//tested on jdk1.5, 1.6 using jgoodies look and feel
					frame.getPreferredSize();
					
					if (frame.getExtendedState() != JFrame.MAXIMIZED_BOTH)
					{
						frame.setMinimumSize(frame.getSize());
						revalidate();
						SwingUtilities.invokeLater(new Runnable() {
		
							public void run() {
								JFrame frame = Env.getFrame(APanel.this);
								frame.validate();
								AEnv.showCenterScreen(frame);
								frame.setMinimumSize(null);
							}
							
						});
					}
				}
			}*/
		//	else		##CHANGE
		//		m_curTab.navigateCurrent();
		}

		//	Update <-> Navigation
		aDetail.setEnabled(m_curTabIndex != m_curWinTab.getTabCount()-1);
		aParent.setEnabled(m_curTabIndex != 0 && m_curWinTab.getTabCount() > 1);

		//	History (on first tab only)
		if (m_mWorkbench.getMWindow(getWindowIndex()).isTransaction())
			aHistory.setEnabled(isFirstTab());
		else
		{
			aHistory.setPressed(false);
			aHistory.setEnabled(false);
		}
		//	Document Print
		aPrint.setEnabled(m_curTab.isPrinted());
		aPrintPreview.setEnabled(m_curTab.isPrinted());
		//	Query
		aFind.setPressed(m_curTab.isQueryActive());

		//	Order Tab
		if (isAPanelTab)
		{
			aMulti.setPressed(false);
			aMulti.setEnabled(false);
			aNew.setEnabled(false);
			aDelete.setEnabled(false);
			aDeleteSelection.setEnabled(false);
			aFind.setEnabled(false);
			aRefresh.setEnabled(false);
			aAttachment.setEnabled(false);
			aChat.setEnabled(false);
		}
		else	//	Grid Tab
		{
			aMulti.setEnabled(true);
			aMulti.setPressed(!m_curGC.isSingleRow());
			aFind.setEnabled(true);
			aRefresh.setEnabled(true);
			aAttachment.setEnabled(true);
			aChat.setEnabled(true);
		}
		//
		m_curWinTab.requestFocusInWindow();
		setBusy(false, true);
		log.config( "fini");
	}	//	stateChanged

	/**
	 *	Navigate to Detail Tab			->
	 */
	private void cmd_detail()
	{
		int index = m_curWinTab.getSelectedIndex();
		if (index == m_curWinTab.getTabCount()-1)
			return;
		//hengsin, bug [ 1637763 ]
		if (m_curWinTab instanceof VTabbedPane)
		{
			VTabbedPane tabPane = (VTabbedPane)m_curWinTab;
			index++;
			while ( index < tabPane.getTabCount() )
			{
				if (tabPane.isEnabledAt(index))
				{
					m_curGC.getTable().removeEditor();
					m_curGC.acceptEditorChanges();
					tabPane.setSelectedIndex(index);
					break;
				}
				else
					index++;
			}
		}
		else
		{
			m_curGC.getTable().removeEditor();
			m_curGC.acceptEditorChanges();
			m_curWinTab.setSelectedIndex(index+1);
		}
			
	}	//	navigateDetail

	/**
	 *	Navigate to Parent Tab			<-
	 */
	private void cmd_parent()
	{
		int index = m_curWinTab.getSelectedIndex();
		if (index == 0)
			return;
		//hengsin, bug [ 1637763 ]
		if (m_curWinTab instanceof VTabbedPane)
		{
			VTabbedPane tabPane = (VTabbedPane)m_curWinTab;
			index--;
			while ( index >= 0 )
			{
				if (tabPane.isEnabledAt(index))
				{
					m_curGC.getTable().removeEditor();
					m_curGC.acceptEditorChanges();
					tabPane.setSelectedIndex(index);
					break;
				}
				else
					index--;
			}
		}
		else
		{
			m_curGC.getTable().removeEditor();
			m_curGC.acceptEditorChanges();
			m_curWinTab.setSelectedIndex(index-1);
		}
	}	//	navigateParent

	
	/**************************************************************************
	 *	Action Listener
	 *  @param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
		log.info(e.getActionCommand() + " - " + e.getModifiers());
		//	+ " - " + new Timestamp(e.getWhen()) + " " + isUILocked());
		if (m_disposing || isUILocked())
			return;
			
		m_lastModifiers = e.getModifiers();
		String cmd = e.getActionCommand();
		//	Do ScreenShot w/o busy
		if (cmd.equals("ScreenShot"))
		{
			AEnv.actionPerformed (e.getActionCommand(), m_curWindowNo, this);
			return;
		}

		//  Problem: doubleClick detection - can't disable button as clicking button may change button status
		if (!cmd.equals(aShowAllWindow.getName()))
			setBusy (true, true);
		//  Command Buttons
		if (e.getSource() instanceof VButton)
		{
			setStatusLine(processButtonCallout((VButton)e.getSource()), true);
			actionButton((VButton)e.getSource());
			setBusy(false, true);
			return;
		}

		try
		{
			//	File
			if (cmd.equals(aReport.getName()))
				cmd_report();
			else if (cmd.equals(aPrint.getName()))
				cmd_print();
			else if (cmd.equals(aPrintPreview.getName()))
				cmd_print(true);
			else if (aExport != null && cmd.equals(aExport.getName()))
				cmd_export();
			else if (cmd.equals(aEnd.getName()))
				cmd_end(false);
			else if (cmd.equals(aExit.getName()))
				cmd_end(true);
			//	Edit
			else if (cmd.equals(aNew.getName()))
				cmd_new(false);
			else if (cmd.equals(aSave.getName()))
				cmd_save(true);
			else if (cmd.equals(aCopy.getName()))
				cmd_new(true);
			else if (cmd.equals(aDelete.getName()))
				cmd_delete();
			else if (cmd.equals(aDeleteSelection.getName()))
				cmd_deleteSelection();
			else if (cmd.equals(aIgnore.getName()))
				cmd_ignore();
			else if (cmd.equals(aRefresh.getName()))
				cmd_refresh();
			else if (cmd.equals(aFind.getName()))
				cmd_find();
			else if (m_isPersonalLock && cmd.equals(aLock.getName()))
				cmd_lock();
			//	View
			else if (cmd.equals(aAttachment.getName()))
				cmd_attachment();
			else if (cmd.equals(aChat.getName()))
				cmd_chat();
			else if (cmd.equals(aHistory.getName()))
				cmd_history();
			else if (cmd.equals(aMulti.getName()))
				m_curGC.switchRowPresentation();
			//	Go
			else if (cmd.equals(aHome.getName())) {
				// show main menu - teo_sarca [ 1706409, 1707221 ] 
				setBusy(false, false);
				AEnv.showWindow(Env.getWindow(0));
				return;
			}
			else if (cmd.equals(aFirst.getName()))
			{	/*cmd_save(false);*/
				m_curGC.getTable().removeEditor();
				m_curGC.acceptEditorChanges();
				m_curTab.navigate(0);
			}
			else if (cmd.equals(aSwitchLinesUpAction.getName())) 
			{
				//up-key + shift
				m_curGC.getTable().removeEditor();
				m_curTab.switchRows(m_curTab.getCurrentRow(), m_curTab.getCurrentRow() - 1, m_curGC.getTable().getSortColumn(), m_curGC.getTable().isSortAscending());
				m_curGC.getTable().requestFocus();
			} 
			else if (cmd.equals(aPrevious.getName())) 
			{ /* cmd_save(false); */
				//up-image + shift
				m_curGC.getTable().removeEditor();
				m_curGC.acceptEditorChanges();
				if ((e.getModifiers() & ActionEvent.SHIFT_MASK) != 0) {
					m_curTab.switchRows(m_curTab.getCurrentRow(), m_curTab.getCurrentRow() - 1, m_curGC.getTable().getSortColumn(), m_curGC.getTable().isSortAscending());
				} else {
					m_curTab.navigateRelative(-1);
				}
			} 
			else if (cmd.equals(aSwitchLinesDownAction.getName())) 
			{
				//down-key + shift
				m_curGC.getTable().removeEditor();
				m_curTab.switchRows(m_curTab.getCurrentRow(), m_curTab.getCurrentRow() + 1, m_curGC.getTable().getSortColumn(), m_curGC.getTable().isSortAscending());
				m_curGC.getTable().requestFocus();
			} 
			else if (cmd.equals(aNext.getName())) 
			{ /* cmd_save(false); */
				//down-image + shift
				m_curGC.getTable().removeEditor();
				m_curGC.acceptEditorChanges();
				if ((e.getModifiers() & ActionEvent.SHIFT_MASK) != 0) {
					m_curTab.switchRows(m_curTab.getCurrentRow(), m_curTab.getCurrentRow() + 1, m_curGC.getTable().getSortColumn(), m_curGC.getTable().isSortAscending());
				} else {
					m_curTab.navigateRelative(+1);
				}
			}
			else if (cmd.equals(aLast.getName()))
			{	/*cmd_save(false);*/
				m_curGC.getTable().removeEditor();
				m_curGC.acceptEditorChanges();
				m_curTab.navigate(m_curTab.getRowCount()-1);
			}
			else if (cmd.equals(aParent.getName()))
				cmd_parent();
			else if (cmd.equals(aDetail.getName()))
				cmd_detail();
			else if (cmd.equals(aZoomAcross.getName()))
				cmd_zoomAcross();
			else if (cmd.equals(aRequest.getName()))
				cmd_request();
			else if (cmd.equals(aArchive.getName()))
				cmd_archive();
			//	Tools
			else if (aWorkflow != null && cmd.equals(aWorkflow.getName()))
			{
				if (m_curTab.getRecord_ID() <= 0)
					;
				else if (m_curTab.getTabNo() == 0 && m_mWorkbench.getMWindow(getWindowIndex()).isTransaction())
					AEnv.startWorkflowProcess(m_curTab.getAD_Table_ID(), m_curTab.getRecord_ID());
				else
					AEnv.startWorkflowProcess(m_curTab.getAD_Table_ID(), m_curTab.getRecord_ID());
			}
			else if (aWinSize != null && cmd.equals(aWinSize.getName()))
				cmd_winSize();
			//	Help
			else if (cmd.equals(aHelp.getName()))
				cmd_help();
			//  General Commands (Environment)
			else if (cmd.equals(aLogout.getName()))
				cmd_logout();
			else if (cmd.equals(aShowAllWindow.getName()))
				m_WindowMenu.expose();
			else if (!AEnv.actionPerformed (e.getActionCommand(), m_curWindowNo, this))
				log.log(Level.SEVERE, "No action for: " + cmd);
		}
		catch (Exception ex)
		{
			log.log(Level.SEVERE, cmd, ex);
			String msg = ex.getMessage();
			if (msg == null || msg.length() == 0)
				msg = ex.toString();
			msg = Msg.parseTranslation(m_ctx, msg);
			ADialog.error(m_curWindowNo, this, "Error", msg);
		}
		//
		m_curWinTab.requestFocusInWindow();
		setBusy(false, true);
	}	//	actionPerformed

	private void cmd_logout() {
		JFrame top = Env.getWindow(0);
		if (top instanceof AMenu) {
			((AMenu)top).logout();
		}
		
	}

	/**************************************************************************
	 *  Process Callout(s).
	 *  <p>
	 *  The Callout is in the string of
	 *  "class.method;class.method;"
	 * If there is no class name, i.e. only a method name, the class is regarded
	 * as CalloutSystem.
	 * The class needs to comply with the Interface Callout.
	 *
	 * @param field field
	 * @return error message or ""
	 * @see org.compiere.model.Callout
	 */
	private String processButtonCallout (VButton button)
	{
		GridField field = m_curTab.getField(button.getColumnName());
		return m_curTab.processCallout(field);
	}	//	processButtonCallout
	
	/**
	 *  Create New Record
	 *  @param copy true if current record is to be copied
	 */
	private void cmd_new (boolean copy)
	{
		log.config("copy=" + copy);
		if (!m_curTab.isInsertRecord())
		{
			log.warning("Insert Record disabled for Tab");
			return;
		}
		
		m_curGC.stopEditor(true);
		m_curGC.acceptEditorChanges();
		
		// BF [ 2799362 ] attempt to save if save action is enabled. Using
		// m_curTab.needSave instead might miss unfilled mandatory fields.
		if(aSave.isEnabled()){
			//	Automatic Save
			if (Env.isAutoCommit(m_ctx, m_curWindowNo))
			{
				if (!cmd_save(true))
				{	
					return;
				}
			}
			//  explicitly ask when changing tabs
			else if (ADialog.ask(m_curWindowNo, this, "SaveChanges?", m_curTab.getCommitWarning()))
			{   //  yes we want to save
				if (!cmd_save(true))
				{   
					return;
				}
			}
			else    //  Don't save
				m_curTab.dataIgnore();
		}
		m_curTab.dataNew (copy);
		m_curGC.dynamicDisplay(0);
	//	m_curTab.getTableModel().setChanged(false);
	}   //  cmd_new

	/**
	 *  Confirm & delete record
	 */
	private void cmd_delete()
	{
		if (m_curTab.isReadOnly())
			return;
		int keyID = m_curTab.getRecord_ID();
		if (ADialog.ask(m_curWindowNo, this, "DeleteRecord?"))
			if (m_curTab.dataDelete())
				m_curGC.rowChanged(false, keyID);
		m_curGC.dynamicDisplay(0);
	}   //  cmd_delete
	
	/**
	 * Show a list to select one or more items to delete.
	 */
	private void cmd_deleteSelection(){
		if (m_curTab.isReadOnly())
			return;
		//show table with deletion rows -> by identifiers columns
		JPanel messagePanel = new JPanel();
		JList list = new JList();
		JScrollPane scrollPane = new JScrollPane(list);
		Vector<String> data = new Vector<String>();
		// FR [ 2877111 ]
		final String keyColumnName = m_curTab.getKeyColumnName();
		final String sql = MLookupFactory.getLookup_TableDirEmbed(Env.getLanguage(m_ctx), keyColumnName, "[?","?]")
								   .replace("[?.?]", "?");
		int noOfRows = m_curTab.getRowCount();
		for(int i = 0; i < noOfRows; i++)
		{
			final int id = m_curTab.getKeyID(i);
			StringBuffer displayValue = new StringBuffer();
			String value = DB.getSQLValueStringEx(null, sql, id);
			value = value.replace(" - ", " | ");
			displayValue.append(value);
			// Append ID
			if (displayValue.length() == 0 || CLogMgt.isLevelFine())
			{
				if (displayValue.length() > 0)
					displayValue.append(" | ");
				displayValue.append("<").append(id).append(">");
			}
			//
			data.add(displayValue.toString());
		}
		// FR [ 2877111 ]
		list.setListData(data);
	
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		messagePanel.add(scrollPane);
		
		final JOptionPane pane = new JOptionPane(
				messagePanel, // message
				JOptionPane.QUESTION_MESSAGE, // messageType
				JOptionPane.OK_CANCEL_OPTION); // optionType
		final JDialog deleteDialog = pane.createDialog(this.getParent(), Msg.getMsg(m_ctx, "DeleteSelection"));
		deleteDialog.setVisible(true);
		Integer okCancel = (Integer) pane.getValue();
		if(okCancel != null && okCancel == JOptionPane.OK_OPTION)
		{
			log.fine("ok");
			Object[] selectedValues = list.getSelectedValues();
			for (int i = 0; i < selectedValues.length; i++)
			{
				log.fine(selectedValues[i].toString());
			}
			int[] indices = list.getSelectedIndices();
			Arrays.sort(indices);
			int offset = 0;
			for (int i = 0; i < indices.length; i++)
			{
				//m_curTab.setCurrentRow(indices[i]-offset);
				m_curTab.navigate(indices[i]-offset);
				int keyID = m_curTab.getRecord_ID();
				if (m_curTab.dataDelete())
				{
					m_curGC.rowChanged(false, keyID);
					offset++;
				}
			}
			m_curGC.dynamicDisplay(0);
		}
		else
		{
			log.fine("cancel");
		}
	}//cmd_deleteSelection

	/**
	 *  If required ask if you want to save and save it
	 *  @param manualCmd true if invoked manually (i.e. force)
	 *  @return true if saved
	 */
	public boolean cmd_save (boolean manualCmd)
	{
		if (m_curAPanelTab != null)
			manualCmd = false;
		log.config("Manual=" + manualCmd);
		m_errorDisplayed = false;
		m_curGC.stopEditor(true);
		m_curGC.acceptEditorChanges();

		if (m_curAPanelTab != null)
		{
			m_curAPanelTab.saveData();
			aSave.setEnabled(false);	//	set explicitly
		}

		if (m_curTab.getCommitWarning().length() > 0 && m_curTab.needSave(true, false))
			if (!ADialog.ask(m_curWindowNo, this, "SaveChanges?", m_curTab.getCommitWarning()))
				return false;

		//  manually initiated
		boolean retValue = m_curTab.dataSave(manualCmd);
		//   if there is no previous error
		if (manualCmd && !retValue && !m_errorDisplayed)
		{
			showLastError();
		}
		if (retValue)
			m_curGC.rowChanged(true, m_curTab.getRecord_ID());
		if (manualCmd) {
			m_curGC.dynamicDisplay(0);
			if (!isNested)
				m_window.setTitle(getTitle());
		}
		
		//BEGIN - [FR 1953734]
		if(m_curGC.isDetailGrid() && retValue){
			m_curGC.getGCParent().refreshMTab(m_curGC);
		}
		//END - [FR 1953734]
		
		return retValue;
	}   //  cmd_save

	private void showLastError() {
		String msg = CLogger.retrieveErrorString(null);
		if (msg != null)
			ADialog.error(m_curWindowNo, this, null, msg);
		else
			ADialog.error(m_curWindowNo, this, "SaveIgnored");
		setStatusLine(Msg.getMsg(m_ctx, "SaveIgnored"), true);
	}

	/**
	 *  Ignore
	 */
	private void cmd_ignore()
	{
		m_curGC.stopEditor(false);
		// Ignore changes in APanelTab (e.g. VSortTab) - teo_sarca [ 1705429 ] 
		if (m_curAPanelTab != null)
		{
			m_curAPanelTab.loadData();
		}
		m_curTab.dataIgnore();
		m_curGC.dynamicDisplay(0);
		
	}   //  cmd_ignore

	/**
	 *  Refresh
	 */
	private void cmd_refresh()
	{
		cmd_save(false);
		m_curTab.dataRefreshAll();
		m_curGC.dynamicDisplay(0);
	}   //  cmd_refresh

	/**
	 *	Print standard Report
	 */
	private void cmd_report ()
	{
		log.info("");
		if (!MRole.getDefault().isCanReport(m_curTab.getAD_Table_ID()))
		{
			ADialog.error(m_curWindowNo, this, "AccessCannotReport");
			return;
		}
		
		cmd_save(false);

		//	Query
		MQuery query = new MQuery(m_curTab.getTableName());
		//	Link for detail records
		String queryColumn = m_curTab.getLinkColumnName();
		//	Current row otherwise
		if (queryColumn.length() == 0)
			queryColumn = m_curTab.getKeyColumnName();
		//	Find display
		String infoName = null;
		String infoDisplay = null;
		for (int i = 0; i < m_curTab.getFieldCount(); i++)
		{
			GridField field = m_curTab.getField(i);
			if (field.isKey())
				infoName = field.getHeader();
			if ((field.getColumnName().equals("Name") || field.getColumnName().equals("DocumentNo") )
				&& field.getValue() != null)
				infoDisplay = field.getValue().toString();
			if (infoName != null && infoDisplay != null)
				break;
		}
		if (queryColumn.length() != 0)
		{
			if (queryColumn.endsWith("_ID"))
				query.addRestriction(queryColumn, MQuery.EQUAL,
					new Integer(Env.getContextAsInt(m_ctx, m_curWindowNo, queryColumn)),
					infoName, infoDisplay);
			else
				query.addRestriction(queryColumn, MQuery.EQUAL,
					Env.getContext(m_ctx, m_curWindowNo, queryColumn),
					infoName, infoDisplay);
		}

		new AReport (m_curTab.getAD_Table_ID(), aReport.getButton(), query, this, m_curWindowNo, m_curTab.getWhereExtended());
	}	//	cmd_report

	
	/**
	 * 	Zoom Across Menu
	 */
	private void cmd_zoomAcross()
	{
		int record_ID = m_curTab.getRecord_ID();
		log.info("ID=" + record_ID);
		if (record_ID <= 0)
			return;

		//	Query
		MQuery query = new MQuery();
		//	Current row
		String link = m_curTab.getKeyColumnName();
		//	Link for detail records
		if (link.length() == 0)
			link = m_curTab.getLinkColumnName();
		if (link.length() != 0)
		{
			if (link.endsWith("_ID"))
				query.addRestriction(link, MQuery.EQUAL,
					new Integer(Env.getContextAsInt(m_ctx, m_curWindowNo, link)));
			else
				query.addRestriction(link, MQuery.EQUAL,
					Env.getContext(m_ctx, m_curWindowNo, link));
		}
		new AZoomAcross (aZoomAcross.getButton(), 
			m_curTab.getTableName(), query);
	}	//	cmd_zoom
	
	/**
	 * 	Open/View Request
	 */
	private void cmd_request()
	{
		int record_ID = m_curTab.getRecord_ID();
		log.info("ID=" + record_ID);
		if (record_ID <= 0)
			return;

		int AD_Table_ID = m_curTab.getAD_Table_ID();
		int C_BPartner_ID = 0;
		Object BPartner_ID = m_curTab.getValue("C_BPartner_ID");
		if (BPartner_ID != null)
			C_BPartner_ID = ((Integer)BPartner_ID).intValue();
		new ARequest (aRequest.getButton(), AD_Table_ID, record_ID, C_BPartner_ID);
	}	//	cmd_request

	/**
	 * 	Open/View Archive
	 */
	private void cmd_archive()
	{
		int record_ID = m_curTab.getRecord_ID();
		log.info("ID=" + record_ID);
		if (record_ID <= 0)
			return;

		int AD_Table_ID = m_curTab.getAD_Table_ID();
		new AArchive (aArchive.getButton(), AD_Table_ID, record_ID);
	}	//	cmd_archive
	
	/**
	 *	Print specific Report - or start default Report
	 */
	private void cmd_print()
	{
		cmd_print(false);
	}
	
	/**
	 *	Print specific Report - or start default Report
	 */
	private void cmd_print(boolean printPreview)
	{
		//	Get process defined for this tab
		int AD_Process_ID = m_curTab.getAD_Process_ID();
		log.info("ID=" + AD_Process_ID);

		//	No report defined
		if (AD_Process_ID == 0)
		{
			cmd_report();
			return;
		}

		cmd_save(false);
		//
		int table_ID = m_curTab.getAD_Table_ID();
		int record_ID = m_curTab.getRecord_ID();
		ProcessInfo pi = new ProcessInfo (getTitle(), AD_Process_ID, table_ID, record_ID);
		pi.setAD_User_ID (Env.getAD_User_ID(m_ctx));
		pi.setAD_Client_ID (Env.getAD_Client_ID(m_ctx));
		pi.setPrintPreview(printPreview);

		ProcessCtl.process(this, m_curWindowNo, pi, null); //  calls lockUI, unlockUI
		statusBar.setStatusLine(pi.getSummary(), pi.isError());
	}   //  cmd_print

	/**
	 *	Find - Set Query
	 */
	private void cmd_find()
	{
		if (m_curTab == null)
			return;
		cmd_save(false);
		//	Gets Fields from AD_Field_v
		GridField[] findFields = GridField.createFields(m_ctx, m_curWindowNo, 0, m_curTab.getAD_Tab_ID());
		Find find = new Find (Env.getFrame(this), m_curWindowNo, m_curTab.getName(),
			m_curTab.getAD_Tab_ID(), m_curTab.getAD_Table_ID(), m_curTab.getTableName(), 
			m_curTab.getWhereExtended(), findFields, 1);
		MQuery query = find.getQuery();
		find.dispose();
		find = null;

		//	Confirmed query
		if (query != null)
		{
			m_onlyCurrentRows = false;      	//  search history too
			m_curTab.setQuery(query);
			m_curGC.query(m_onlyCurrentRows, m_onlyCurrentDays, 0);   //  autoSize
		}
		aFind.setPressed(m_curTab.isQueryActive());
	}	//	cmd_find

	/**
	 *	Attachment
	 */
	private void cmd_attachment()
	{
		int record_ID = m_curTab.getRecord_ID();
		log.info("Record_ID=" + record_ID);
		if (record_ID == -1)	//	No Key
		{
			aAttachment.setEnabled(false);
			return;
		}

	//	Attachment va = 
		new Attachment (Env.getFrame(this), m_curWindowNo,
			m_curTab.getAD_AttachmentID(), m_curTab.getAD_Table_ID(), record_ID, null);
		//
		m_curTab.loadAttachments();				//	reload
		aAttachment.setPressed(m_curTab.hasAttachment());
	}	//	attachment

	/**
	 *	Chat
	 */
	private void cmd_chat()
	{
		int record_ID = m_curTab.getRecord_ID();
		log.info("Record_ID=" + record_ID);
		if (record_ID == -1)	//	No Key
		{
			aChat.setEnabled(false);
			return;
		}
		//	Find display
		String infoName = null;
		String infoDisplay = null;
		for (int i = 0; i < m_curTab.getFieldCount(); i++)
		{
			GridField field = m_curTab.getField(i);
			if (field.isKey())
				infoName = field.getHeader();
			if ((field.getColumnName().equals("Name") || field.getColumnName().equals("DocumentNo") )
				&& field.getValue() != null)
				infoDisplay = field.getValue().toString();
			if (infoName != null && infoDisplay != null)
				break;
		}
		String description = infoName + ": " + infoDisplay;
		//
	//	AChat va = 
		new AChat (Env.getFrame(this), m_curWindowNo,
			m_curTab.getCM_ChatID(), m_curTab.getAD_Table_ID(), record_ID, 
			description, null);
		//
		m_curTab.loadChats();				//	reload
		aChat.setPressed(m_curTab.hasChat());
	}	//	chat

	/**
	 *	Lock
	 */
	private void cmd_lock()
	{
		log.info("Modifiers=" + m_lastModifiers);
		if (!m_isPersonalLock)
			return;
		int record_ID = m_curTab.getRecord_ID();
		if (record_ID == -1)	//	No Key
			return;
		//	Control Pressed
		if ((m_lastModifiers & InputEvent.CTRL_MASK) != 0)
		{
			new RecordAccessDialog(Env.getFrame(this), m_curTab.getAD_Table_ID(), record_ID);
		}
		else
		{
			m_curTab.lock (Env.getCtx(), record_ID, aLock.getButton().isSelected());
			m_curTab.loadAttachments();			//	reload
		}
		aLock.setPressed(m_curTab.isLocked());
	}	//	lock

	/**
	 *	Toggle History
	 */
	private void cmd_history()
	{
		log.info("");
		if (m_mWorkbench.getMWindow(getWindowIndex()).isTransaction())
		{
			if (m_curTab.needSave(true, true) && !cmd_save(false))
				return;

			Point pt = new Point (0, aHistory.getButton().getBounds().height);
			SwingUtilities.convertPointToScreen(pt, aHistory.getButton());
			VOnlyCurrentDays ocd = new VOnlyCurrentDays(Env.getFrame(this), pt);
			if (!ocd.isCancel()) {
				m_onlyCurrentDays = ocd.getCurrentDays();
				if (m_onlyCurrentDays == 1)	//	Day
				{
					m_onlyCurrentRows = true;
					m_onlyCurrentDays = 0; 	//	no Created restriction
				}
				else
					m_onlyCurrentRows = false;
				//
				m_curTab.setQuery(null);	//	reset previous queries
				MRole role = MRole.getDefault(); 
				int maxRows = role.getMaxQueryRecords();
				//
				log.config("OnlyCurrent=" + m_onlyCurrentRows 
					+ ", Days=" + m_onlyCurrentDays
					+ ", MaxRows=" + maxRows);
				m_curGC.query(m_onlyCurrentRows, m_onlyCurrentDays, maxRows );   //  autoSize
			}
			// Restore history button's pressed status
			else {
				if (isFirstTab())
					aHistory.setPressed(!m_curTab.isOnlyCurrentRows());
			}
		}
	}	//	cmd_history

	/**
	 *	Help
	 */
	private void cmd_help()
	{
		log.info("");
		Help hlp = new Help (Env.getFrame(this), this.getTitle(), m_mWorkbench.getMWindow(getWindowIndex()));
		hlp.setVisible(true);
	}	//	cmd_help

	/**
	 *  Close this screen - after save
	 *  @param exit ask if user wants to exit application
	 */
	private void cmd_end (boolean exit)
	{
		boolean exitSystem = false;
		if (!cmd_save(false))
			return;
		if (exit && ADialog.ask(m_curWindowNo, this, "ExitApplication?"))
			exitSystem = true;

		Env.getFrame(this).dispose();		//	calls this dispose

		if (exitSystem)
			AEnv.exit(0);
	}   //  cmd_end

	/**
	 * 	Set Window Size
	 */
	private void cmd_winSize()
	{
		Dimension size = getSize();
		if (!ADialog.ask(m_curWindowNo, this, "WinSizeSet", 
			"x=" + size.width + " - y=" + size.height))
		{
			setPreferredSize(null);
			SwingUtilities.getWindowAncestor(this).pack();
			size = new Dimension (0,0);
		}
		//
		MWindow win = new MWindow(m_ctx, m_curTab.getAD_Window_ID(), null);
		win.setWindowSize(size);
		win.save();
	}	//	cmdWinSize
	
	private void cmd_export()
	{
		new AExport(this);
	}
	
	
	/**************************************************************************
	 *	Start Button Process
	 *  @param vButton button
	 */
	private void actionButton (VButton vButton)
	{
		log.info(vButton.toString());

		if (m_curTab.hasChangedCurrentTabAndParents()) {
			String msg = CLogger.retrieveErrorString("Please ReQuery Window");
			ADialog.error(m_curWindowNo, this, null, msg);
			return;
		}

		boolean startWOasking = false;
//		boolean batch = false;
		String col = vButton.getColumnName();

		//  Zoom
		if (col.equals("Record_ID"))
		{
			int AD_Table_ID = Env.getContextAsInt (m_ctx, m_curWindowNo, "AD_Table_ID");
			int Record_ID = Env.getContextAsInt (m_ctx, m_curWindowNo, "Record_ID");
			AEnv.zoom(AD_Table_ID, Record_ID);
			return;
		}   //  Zoom

		//  save first	---------------
		if (m_curTab.needSave(true, false))
			if (!cmd_save(true))
				return;
		// Save included tabs if necessary - teo_sarca BF [ 2876892 ]
		for (GridTab includedTab : m_curTab.getIncludedTabs())
		{
			if (includedTab.needSave(true, false))
				if(!includedTab.dataSave(true))
					return;
		}
		//
		int table_ID = m_curTab.getAD_Table_ID();
		//	Record_ID
		int record_ID = m_curTab.getRecord_ID();
		//	Record_ID - Language Handling
		if (record_ID == -1 && m_curTab.getKeyColumnName().equals("AD_Language"))
			record_ID = Env.getContextAsInt (m_ctx, m_curWindowNo, "AD_Language_ID");
		//	Record_ID - Change Log ID
		if (record_ID == -1 
			&& (vButton.getProcess_ID() == 306 || vButton.getProcess_ID() == 307))
		{
			Integer id = (Integer)m_curTab.getValue("AD_ChangeLog_ID");
			record_ID = id.intValue();
		}
		//	Ensure it's saved
		if (record_ID == -1 && m_curTab.getKeyColumnName().endsWith("_ID"))
		{
			ADialog.error(m_curWindowNo, this, "SaveErrorRowNotFound");
			return;
		}
		
		boolean isProcessMandatory = false;

		//	Pop up Payment Rules
		if (col.equals("PaymentRule"))
		{
			VPayment vp = new VPayment(m_curWindowNo, m_curTab, vButton);
			if (vp.isInitOK())		//	may not be allowed
				vp.setVisible(true);
			vp.dispose();
			if (vp.needSave())
			{
				cmd_save(false);
				cmd_refresh();
			}
		}	//	PaymentRule

		//	Pop up Document Action (Workflow)
		else if (col.equals("DocAction"))
		{
			isProcessMandatory = true;
			VDocAction vda = new VDocAction(m_curWindowNo, m_curTab, vButton, record_ID);
			//	Something to select from?
			if (vda.getNumberOfOptions() == 0)
			{
				vda.dispose ();
				log.info("DocAction - No Options");
				return;
			}
			else
			{
				vda.setVisible(true);
				if (!vda.isStartProcess())
					return;
//				batch = vda.isBatch();
				startWOasking = true;
				vda.dispose();
			}
		}	//	DocAction

		//  Pop up Create From
		else if (col.equals("CreateFrom"))
		{
			// Run form only if the button has no process defined - teo_sarca [ 1974354 ] 
			if (vButton.getProcess_ID() <= 0)
			{
				ICreateFrom cf = VCreateFromFactory.create(m_curTab);
				if(cf != null)
				{
					if(cf.isInitOK())
					{
						cf.showWindow();
						cf.closeWindow();
						m_curTab.dataRefresh();
					}
					else
						cf.closeWindow();
					return;
				}				
				//	else may start process
			}
		}	//	CreateFrom

		//  Posting -----
		else if (col.equals("Posted") && MRole.getDefault().isShowAcct())
		{
			//  Check Doc Status
			String processed = Env.getContext(m_ctx, m_curWindowNo, "Processed");
			if (!processed.equals("Y"))
			{
				String docStatus = Env.getContext(m_ctx, m_curWindowNo, "DocStatus");
				if (DocAction.STATUS_Completed.equals(docStatus)
					|| DocAction.STATUS_Closed.equals(docStatus)
					|| DocAction.STATUS_Reversed.equals(docStatus)
					|| DocAction.STATUS_Voided.equals(docStatus))
					;
				else
				{
					ADialog.error(m_curWindowNo, this, "PostDocNotComplete");
					return;
				}
			}

			// try to get table and record id from context data (eg for unposted view)
			// otherwise use current table/record
			int tableId = Env.getContextAsInt(m_ctx, m_curWindowNo, "AD_Table_ID", true);
			int recordId = Env.getContextAsInt(m_ctx, m_curWindowNo, "Record_ID", true);
			if ( tableId == 0 || recordId == 0 )
			{
				tableId = m_curTab.getAD_Table_ID();
				recordId = m_curTab.getRecord_ID();
			}

			//  Check Post Status
			Object ps = m_curTab.getValue("Posted");
			if (ps != null && ps.equals("Y"))
			{
				new org.compiere.acct.AcctViewer (Env.getContextAsInt (m_ctx, m_curWindowNo, "AD_Client_ID"),
					tableId, recordId);
			}
			else
			{
				if (ADialog.ask(m_curWindowNo, this, "PostImmediate?"))
				{
					boolean force = ps != null && !ps.equals ("N");		//	force when problems
					String error = AEnv.postImmediate (m_curWindowNo, Env.getAD_Client_ID(m_ctx),
						tableId, recordId, force);
					if (error != null)
						ADialog.error(m_curWindowNo, this, "PostingError-N", error);
					cmd_refresh();
				}
			}
			return;
		}   //  Posted

		/**
		 *  Start Process ----
		 *  or invoke user form
		 */

		log.config("Process_ID=" + vButton.getProcess_ID() + ", Record_ID=" + record_ID);
		if (vButton.getProcess_ID() == 0)
		{
			if (isProcessMandatory)
			{
				ADialog.error(m_curWindowNo, this, null, Msg.parseTranslation(m_ctx, "@NotFound@ @AD_Process_ID@"));
			}
			return;
		}
		//	Save item changed
		if (m_curTab.needSave(true, false))
			if (!cmd_save(true))
				return;
		
		// call form
		MProcess pr = new MProcess(m_ctx, vButton.getProcess_ID(), null);
		int form_ID = pr.getAD_Form_ID();
		if (form_ID != 0 ) 
		{
			
			if (m_curTab.needSave(true, false))
				if (!cmd_save(true))
					return;
			
			FormFrame ff = new FormFrame(); 
			String title = vButton.getDescription();
			if (title == null || title.length() == 0)
				title = vButton.getName();
			ProcessInfo pi = new ProcessInfo (title, vButton.getProcess_ID(), table_ID, record_ID);
			pi.setAD_User_ID (Env.getAD_User_ID(m_ctx));
			pi.setAD_Client_ID (Env.getAD_Client_ID(m_ctx));
			ff.setProcessInfo(pi);
			ff.openForm(form_ID); 
			ff.pack(); 
			AEnv.showCenterScreen(ff); 
			return; 
		}		
		else {
			ProcessModalDialog dialog = new ProcessModalDialog(m_ctx, Env.getWindow(m_curWindowNo), Env.getHeader(m_ctx, m_curWindowNo),
					this, m_curWindowNo, vButton.getProcess_ID(), table_ID, 
					record_ID, startWOasking);
			if (dialog.isValidDialog())
			{
				dialog.validate();
				dialog.pack();
				AEnv.showCenterWindow(Env.getWindow(m_curWindowNo), dialog);
			}
		}
	}	//	actionButton

	
	/**************************************************************************
	 *  Lock User Interface.
	 *  Called from the Worker before processing
	 *  @param pi process info
	 */
	public void lockUI (ProcessInfo pi)
	{
	//	log.fine("" + pi);
		setBusy(true, false);
	}   //  lockUI

	/**
	 *  Unlock User Interface.
	 *  Called from the Worker when processing is done
	 *  @param pi of execute ASync call
	 */
	public void unlockUI (ProcessInfo pi)
	{
	//	log.fine("" + pi);
		boolean notPrint = pi != null 
			&& pi.getAD_Process_ID() != m_curTab.getAD_Process_ID()
			&& pi.isReportingProcess() == false;
		//
		setBusy(false, notPrint);
		//  Process Result
		if (notPrint)		//	refresh if not print 
		{
			//	Refresh data
			m_curTab.dataRefresh();
			//	Timeout
			if (pi.isTimeout())		//	set temporarily to R/O
				Env.setContext(m_ctx, m_curWindowNo, "Processed", "Y");
			m_curGC.dynamicDisplay(0);
			//	Update Status Line
			setStatusLine(pi.getSummary(), pi.isError());
			if ( pi.isError() )
				ADialog.error(m_curWindowNo, this, null, pi.getSummary());
			//	Get Log Info
			ProcessInfoUtil.setLogFromDB(pi);
			String logInfo = pi.getLogInfo();
			if (logInfo.length() > 0)
				ADialog.info(m_curWindowNo, this, Env.getHeader(m_ctx, m_curWindowNo),
					pi.getTitle(), logInfo);	//	 clear text
		}
		else
		{
			//	Update Status Line
			setStatusLine(pi.getSummary(), pi.isError());
			if ( pi.isError() )
				ADialog.error(m_curWindowNo, this, null, pi.getSummary());
		}
	}   //  unlockUI

	/**
	 *  Is the UI locked (Internal method)
	 *  @return true, if UI is locked
	 */
	public boolean isUILocked()
	{
		return m_isLocked;
	}   //  isLoacked

	/**
	 *  Method to be executed async.
	 *  Called from the ASyncProcess worker
	 *  @param pi process info
	 */
	public void executeASync (ProcessInfo pi)
	{
		log.config("-");
	}   //  executeASync

	/**
	 * 	Get Current Tab
	 *	@return current tab
	 */
	public GridTab getCurrentTab()
	{
		return m_curTab;
	}	//	getCurrentTab
	
	/**
	 * Get the number of tabs in the panels JTabbedPane.
	 * @return no of tabs in the JTabbedPane of the panel
	 */
	public int noOfTabs() {
 		return m_curWinTab.getTabCount();
	}
	
	/**
	 * Get the selected tab index of the panels JTabbedPane.
	 * @return selected index of JTabbedPane
	 */
	public int getSelectedTabIndex() {
 		return m_curWinTab.getSelectedIndex();
	}
	
	/**
	 * Set the tab index of the panels JTabbedPane.
	 */
	public void setSelectedTabIndex(int index) {
 		m_curWinTab.setSelectedIndex(index);
	}
	
	/**
	 * Get the name of the selected tab in the panels JTabbedPane.
	 * @return name of selected tab
	 */
	public String getSelectedTabName() {
 		String title = m_curWinTab.getTitleAt(m_curWinTab.getSelectedIndex());
 		title = title.substring(title.indexOf("<html>")+6);
 		title = title.substring(0,title.indexOf('<'));
 		return title;
	}
	
	/**
	 *  String representation
	 *  @return String representation
	 */
	public String toString()
	{
		String s = "APanel[curWindowNo=" + m_curWindowNo;
		if (m_mWorkbench != null)
			s += ",WB=" + m_mWorkbench.toString();
		s += "]";
		return s;
	}   //  toString

	/**
	 * Simple action class for the resort of tablelines (switch line no). Delegates actionPerformed
	 * to APanel.
	 * 
	 * @author Karsten Thiemann, kthiemann@adempiere.org
	 * 
	 */
	class SwitchAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 3837712049468116744L;

		/** the action listener - APanel */
		private ActionListener al;

		/** action name */
		private String name;

		/**
		 * Constructor.
		 * @param name
		 * @param accelerator
		 * @param al
		 */
		SwitchAction(String name, KeyStroke accelerator, ActionListener al) {
			super(name);
			putValue(Action.NAME, name); // Display
			putValue(Action.SHORT_DESCRIPTION, name); // Tooltip
			putValue(Action.ACCELERATOR_KEY, accelerator); // KeyStroke
			putValue(Action.ACTION_COMMAND_KEY, name); // ActionCammand
			this.al = al;
			this.name = name;
		}

		public void actionPerformed(ActionEvent e) {
			al.actionPerformed(e);
		} // actionPerformed

		public String getName() {
			return name;
		}
	}

	/**
	 * Removes the default KeyStroke action for the up/down keys and adds switch
	 * line actions.
	 */
	private void initSwitchLineAction() {
		aSwitchLinesDownAction = new SwitchAction("switchLinesDown", KeyStroke.getKeyStroke(
				KeyEvent.VK_DOWN, Event.SHIFT_MASK), this);
		aSwitchLinesUpAction = new SwitchAction("switchLinesUp", KeyStroke.getKeyStroke(
				KeyEvent.VK_UP, Event.SHIFT_MASK), this);

		JTable table = m_curGC.getTable();
		table.getInputMap(CPanel.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, Event.SHIFT_MASK), "none");
		table.getInputMap(CPanel.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_UP, Event.SHIFT_MASK), "none");
		table.getInputMap(CPanel.WHEN_FOCUSED).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, Event.SHIFT_MASK), "none");
		table.getInputMap(CPanel.WHEN_FOCUSED).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_UP, Event.SHIFT_MASK), "none");

		getInputMap(CPanel.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, Event.SHIFT_MASK),
				aSwitchLinesDownAction.getName());
		getActionMap().put(aSwitchLinesDownAction.getName(), aSwitchLinesDownAction);
		getInputMap(CPanel.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_UP, Event.SHIFT_MASK),
				aSwitchLinesUpAction.getName());
		getActionMap().put(aSwitchLinesUpAction.getName(), aSwitchLinesUpAction);
	}
	
	public boolean isNested() {
		return isNested;
	}

}	//	APanel
