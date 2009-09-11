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
package org.compiere.apps;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JProgressBar;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.adempiere.apps.graph.PAPanel;
import org.adempiere.plaf.AdempierePLAF;
import org.compiere.Adempiere;
import org.compiere.apps.wf.WFActivity;
import org.compiere.apps.wf.WFPanel;
import org.compiere.grid.tree.VTreePanel;
import org.compiere.model.MRole;
import org.compiere.model.MSession;
import org.compiere.model.MSysConfig;
import org.compiere.model.MSystem;
import org.compiere.model.MTreeNode;
import org.compiere.model.MUser;
import org.compiere.swing.CButton;
import org.compiere.swing.CFrame;
import org.compiere.swing.CPanel;
import org.compiere.swing.CScrollPane;
import org.compiere.swing.CTabbedPane;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Language;
import org.compiere.util.Msg;
import org.compiere.util.Splash;
/**
 *	Application Menu Controller
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: AMenu.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 *
 *  @author Colin Rooney (croo) RFE#1670185  restrict access to info queries
 *  @author victor.perez@e-evolution.com 
 *  @see FR [ 1966328 ] New Window Info to MRP and CRP into View http://sourceforge.net/tracker/index.php?func=detail&aid=1966328&group_id=176962&atid=879335
 * 
 */
public final class AMenu extends CFrame
	implements ActionListener, PropertyChangeListener, ChangeListener
{
	/**
	 * generated serialVersionUID
	 */
	private static final long serialVersionUID = 5255914306969824011L;

	static {
		AdempierePLAF.setPLAF ();
	}
	
	/**
	 *	Application Start and Menu
	 */
	public AMenu ()
	{
		super();
		log.info("CodeBase=" + Adempiere.getCodeBase());		
		Splash splash = Splash.getSplash();
		//
		m_WindowNo = Env.createWindowNo(this);
		//	Login
		initSystem (splash);        //	login
		splash.setText(Msg.getMsg(m_ctx, "Loading"));
		splash.toFront();
		splash.paint(splash.getGraphics());
		
		//
		if (!Adempiere.startupEnvironment(true)) // Load Environment
			System.exit(1);		
		MSession.get (Env.getCtx(), true);		//	Start Session

		// Setting close operation/listener - teo_sarca [ 1684168 ]
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowListener() {
			public void windowClosing(WindowEvent e) {
				if (!ADialog.ask(0, null, "ExitApplication?"))
					return;
				dispose();
			}
			public void windowActivated(WindowEvent e) {}
			public void windowClosed(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowOpened(WindowEvent e) {}
		});
		
		//	Preparation
		wfActivity = new WFActivity(this); 
		wfPanel = new WFPanel(this);
		treePanel = new VTreePanel (m_WindowNo, true, false);	//	!editable & hasBar

		try
		{
			jbInit();
			createMenu();
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, "AMenu", ex);
		}

		//	initialize & load tree
		int AD_Role_ID = Env.getAD_Role_ID(Env.getCtx());
		int AD_Tree_ID = DB.getSQLValue(null,
			"SELECT COALESCE(r.AD_Tree_Menu_ID, ci.AD_Tree_Menu_ID)" 
			+ "FROM AD_ClientInfo ci" 
			+ " INNER JOIN AD_Role r ON (ci.AD_Client_ID=r.AD_Client_ID) "
			+ "WHERE AD_Role_ID=?", AD_Role_ID);
		if (AD_Tree_ID <= 0)
			AD_Tree_ID = 10;	//	Menu
		treePanel.initTree(AD_Tree_ID);

		//	Translate
		Env.setContext(m_ctx, m_WindowNo, "WindowName", Msg.getMsg(m_ctx, "Menu"));
		setTitle(Env.getHeader(m_ctx, m_WindowNo));

		progressBar.setString(Msg.getMsg(m_ctx, "SelectProgram"));

		//  Finish UI
		Point loc = Ini.getWindowLocation(0);
		if (loc == null)
			loc = new Point(0,0);
		this.setLocation(loc);
		this.pack();
		this.setVisible(true);
		if (Ini.isPropertyBool(Ini.P_OPEN_WINDOW_MAXIMIZED))
			this.setExtendedState(Frame.MAXIMIZED_BOTH);
		else
			this.setState(Frame.NORMAL);
		this.validate();
		m_AD_User_ID = Env.getContextAsInt(m_ctx, "#AD_User_ID");
		m_AD_Role_ID = Env.getContextAsInt(m_ctx, "#AD_Role_ID");
		updateInfo();
		//
		infoUpdater = new InfoUpdater();
		infoUpdaterThread = new Thread(infoUpdater, "InfoUpdater");
		infoUpdaterThread.start();
		//
		splash.dispose();
		splash = null;		
	}	//	AMenu

	private int 		m_WindowNo;
	private Properties  m_ctx = Env.getCtx();
	private boolean		m_startingItem = false;
	/** The User			*/
	private int 		m_AD_User_ID;
	/** The Role			*/
	private int 		m_AD_Role_ID;
	
	/** Center Tabbed Pane index: Menu			*/
	private int 		m_tabMenu = 0;
	/** Center Tabbed Pane index: Activities	*/
	private int 		m_tabActivities = 1;
	/** Center Tabbed Pane index: Workflow		*/
	private int 		m_tabWorkflow = 2;
	
	//	Links
	private int			m_request_Menu_ID = 0;
	private int			m_note_Menu_ID = 0;
	private String		m_requestSQL = null;
//	private DecimalFormat	m_memoryFormat = DisplayType.getNumberFormat(DisplayType.Integer);
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(AMenu.class);
	
	/** The Info Update instance **/
	private InfoUpdater infoUpdater = null;
	/** The Info Update thread **/
	private Thread infoUpdaterThread = null;
	
	private WindowManager windowManager = new WindowManager();
	
	
	/**************************************************************************
	 *	Init System.
	 *  -- do not get Msg as environment not initialized yet --
	 *  <pre>
	 *	- Login - in not successful, exit
	 *  </pre>
	 *  @param splash splash window
	 */
	private void initSystem (Splash splash)
	{
		//  Default Image
		this.setIconImage(Adempiere.getImage16());

		//  Focus Traversal
		KeyboardFocusManager.setCurrentKeyboardFocusManager(AKeyboardFocusManager.get());
	//	FocusManager.getCurrentManager().setDefaultFocusTraversalPolicy(AFocusTraversalPolicy.get());
	//	this.setFocusTraversalPolicy(AFocusTraversalPolicy.get());

		/**
		 *	Show Login Screen - if not successful - exit
		 */
		log.finer("Login");

		ALogin login = new ALogin(splash);
		if (!login.initLogin())		//	no automatic login
		{
			//	Center the window
			try
			{
				AEnv.showCenterScreen(login);	//	HTML load errors
			}
			catch (Exception ex)
			{
				log.severe(ex.toString());
			}
			if (!login.isConnected() || !login.isOKpressed())
				AEnv.exit(1);
		}

		//  Check Build
		if (!DB.isBuildOK(m_ctx))
			AEnv.exit(1);

		//  Check DB	(AppsServer Version checked in Login)
		DB.isDatabaseOK(m_ctx);
	}	//	initSystem

	//	UI
	private CPanel mainPanel = new CPanel();
	private BorderLayout mainLayout = new BorderLayout();
	private CTabbedPane centerPane = new CTabbedPane();
	private CPanel southPanel = new CPanel();
	private BorderLayout southLayout = new BorderLayout();
	private JMenuBar menuBar = new JMenuBar();
	protected JProgressBar progressBar = new JProgressBar(0,100);
	private CPanel infoPanel = new CPanel();
	private CButton bNotes = new CButton();
	private CButton bRequests = new CButton();
	private GridLayout infoLayout = new GridLayout();
	private JProgressBar memoryBar = new JProgressBar();
	//	Tabs
	private PAPanel		paPanel = null;		
	private VTreePanel	treePanel = null;
	private WFActivity	wfActivity = null;
	private WFPanel		wfPanel = null;
	private WindowMenu m_WindowMenu;

	/**
	 *	Static Init.
	 *  <pre>
	 *  - mainPanel
	 * 		- centerPane
	 *      	- treePanel
	 * 			- wfActivity
	 * 			- wfPanel
	 *      - southPanel
	 *          - infoPanel
	 *              - bNotes
	 *              - bTask
	 *              - memoryBar
	 *          - wfPanel
	 *          - progressBar
	 *  </pre>
	 *  @throws Exception
	 */
	void jbInit() throws Exception
	{
		this.setName("Menu");
		this.setLocale(Language.getLoginLanguage().getLocale());
		this.setJMenuBar(menuBar);
		//
		mainPanel.setLayout(mainLayout);
		mainLayout.setHgap(0);
		mainLayout.setVgap(2);
		//
		treePanel.addPropertyChangeListener(VTreePanel.NODE_SELECTION, this);
		treePanel.addPropertyChangeListener(VTreePanel.NODE_SELECTION_RIGHT, this);
		//
		infoPanel.setLayout(infoLayout);
		infoLayout.setColumns(2);
		infoLayout.setHgap(4);
		infoLayout.setVgap(0);
	//	bNotes.setRequestFocusEnabled(false);
		bNotes.setToolTipText("");
		bNotes.setActionCommand("Notes");
		bNotes.addActionListener(this);
		bNotes.setIcon(Env.getImageIcon("GetMail24.gif"));
		bNotes.setMargin(new Insets(0, 0, 0, 0));
	//	bRequests.setRequestFocusEnabled(false);
		bRequests.setActionCommand("Requests");
		bRequests.addActionListener(this);
		bRequests.setIcon(Env.getImageIcon("Request24.gif"));
		bRequests.setMargin(new Insets(0, 0, 0, 0));
		//
		southLayout.setHgap(0);
		southLayout.setVgap(1);
		//
		memoryBar.setStringPainted(true);
		memoryBar.setOpaque(false);
		memoryBar.setBorderPainted(false);
		memoryBar.addMouseListener(new AMenu_MouseAdapter());
		//
		progressBar.setStringPainted(true);
		progressBar.setOpaque(false);
		//
		getContentPane().add(mainPanel);
		mainPanel.add(centerPane, BorderLayout.CENTER);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		mainPanel.add(Box.createHorizontalStrut(3), BorderLayout.EAST);
		mainPanel.add(Box.createHorizontalStrut(3), BorderLayout.WEST);

		//	Tabs
		centerPane.setFont(centerPane.getFont().deriveFont(centerPane.getFont().getSize2D()+1));
		paPanel = PAPanel.get();
		if (paPanel != null)
		{
			//centerPane.add(paPanel, Msg.getMsg(m_ctx, "PAPanel"));
			centerPane.addTab(Msg.getMsg(m_ctx, "PAPanel"), Env.getImageIcon2("InfoAccount16"), paPanel);
			m_tabMenu++;
			m_tabActivities++;
			m_tabWorkflow++;
		}
		treePanel.setBorder(BorderFactory.createEmptyBorder(2,3,2,3));
		//centerPane.add(treePanel, Msg.getMsg(m_ctx, "Menu"));
		centerPane.addTab(Msg.getMsg(m_ctx, "Menu"), Env.getImageIcon2("Home16"), treePanel);
		//centerPane.add(new CScrollPane(wfActivity), Msg.getMsg (m_ctx, "WorkflowActivities") + ": 0");
		centerPane.addTab(Msg.getMsg (m_ctx, "WorkflowActivities") + ": 0", Env.getImageIcon2("Assignment16"), new CScrollPane(wfActivity));
		//centerPane.add(new CScrollPane(wfPanel), Msg.getMsg (m_ctx, "WorkflowPanel"));
		centerPane.addTab(Msg.getMsg (m_ctx, "WorkflowPanel"), Env.getImageIcon2("WorkFlow16"), new CScrollPane(wfPanel));
		centerPane.addChangeListener (this);
		//
		southPanel.setLayout(southLayout);
		southPanel.add(infoPanel, BorderLayout.NORTH);
		southPanel.add(progressBar, BorderLayout.SOUTH);
		//
		infoPanel.add(bNotes, null);
		infoPanel.add(bRequests, null);
		infoPanel.add(memoryBar, null);
		//
		int loc = Ini.getDividerLocation();
		if (loc > 0)
			treePanel.setDividerLocation(loc);
	}	//	jbInit

	/**
	 * 	Get Preferred Size
	 * 	@return preferred Size
	 */
	public Dimension getPreferredSize()
	{
		Dimension dim = Ini.getWindowDimension(0);
		if (dim == null)
			dim = new Dimension (350, 500); 
		return dim;
	}	//	getPreferredSize


	/**
	 *  Create Menu
	 */
	private void createMenu()
	{
		//      File
		JMenu mFile = AEnv.getMenu("File");
		menuBar.add(mFile);
		AEnv.addMenuItem("PrintScreen", null, KeyStroke.getKeyStroke(KeyEvent.VK_PRINTSCREEN, 0), mFile, this);
		AEnv.addMenuItem("ScreenShot", null, KeyStroke.getKeyStroke(KeyEvent.VK_PRINTSCREEN, KeyEvent.SHIFT_MASK), mFile, this);
		mFile.addSeparator();
		AEnv.addMenuItem("Logout", null, KeyStroke.getKeyStroke(KeyEvent.VK_L, Event.SHIFT_MASK+Event.ALT_MASK), mFile, this);
		AEnv.addMenuItem("Exit", null, KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.SHIFT_MASK+Event.ALT_MASK), mFile, this);

		//      View
		JMenu mView = AEnv.getMenu("View");
		menuBar.add(mView);
		
		if (MRole.getDefault().isAllow_Info_Product())
		{
			AEnv.addMenuItem("InfoProduct", null, KeyStroke.getKeyStroke(KeyEvent.VK_I, Event.ALT_MASK), mView, this);			
		}
		if (MRole.getDefault().isAllow_Info_BPartner())
		{
			AEnv.addMenuItem("InfoBPartner", null, KeyStroke.getKeyStroke(KeyEvent.VK_I, Event.ALT_MASK+Event.CTRL_MASK), mView, this);
		}
		if (MRole.getDefault().isShowAcct() && MRole.getDefault().isAllow_Info_Account())
		{
			AEnv.addMenuItem("InfoAccount", null, KeyStroke.getKeyStroke(KeyEvent.VK_I, Event.ALT_MASK+Event.CTRL_MASK), mView, this);
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
		
		//      Tools
		JMenu mTools = AEnv.getMenu("Tools");
		menuBar.add(mTools);
		AEnv.addMenuItem("Calculator", null, null, mTools, this);
		AEnv.addMenuItem("Calendar", null, null, mTools, this);
		AEnv.addMenuItem("Editor", null, null, mTools, this);
		MUser user = MUser.get(Env.getCtx());
		if (user.isAdministrator())
			AEnv.addMenuItem("Script", null, null, mTools, this);
		if (AEnv.isWorkflowProcess())
			AEnv.addMenuItem("WorkFlow", null, null, mTools, this);
		if (MRole.getDefault().isShowPreference())
		{
			mTools.addSeparator();
			AEnv.addMenuItem("Preference", null, null, mTools, this);
		}
		
		//Window Menu
		m_WindowMenu = new WindowMenu(windowManager, this);
		menuBar.add(m_WindowMenu);
		KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_MASK);
		this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks, "ShowAllWindow");
		AppsAction action = new AppsAction("ShowAllWindow", ks, false);
		this.getRootPane().getActionMap().put("ShowAllWindow", action);
		action.setDelegate(this);

		//      Help
		JMenu mHelp = AEnv.getMenu("Help");
		menuBar.add(mHelp);
		AEnv.addMenuItem("Online", null, null, mHelp, this);
		AEnv.addMenuItem("EMailSupport", null, null, mHelp, this);
		AEnv.addMenuItem("About", null, null, mHelp, this);
	}   //  createMenu

	/**
	 *	Dispose - end system
	 */
	public void dispose()
	{
		preDispose();
		//
		super.dispose();
		AEnv.exit(0);
	}	//	dispose

	private void preDispose() {
		//	clean up - save window state
		Ini.setWindowDimension(0, getSize());
		Ini.setDividerLocation(treePanel.getDividerLocation());
		Ini.setWindowLocation(0, getLocation());
		Ini.saveProperties(true);
		//
		infoUpdater.stop = true;
		try {
			infoUpdaterThread.join(50);
		} catch(InterruptedException ire) {	
		} finally {
			infoUpdaterThread = null;
			infoUpdater = null;
		}
	}
	
	public void logout()
	{
		windowManager.close();
		preDispose();
		super.dispose();
		AEnv.logout();
	}

	/**
	 *  Window Events - requestFocus
	 *  @param e event
	 */
	protected void processWindowEvent(WindowEvent e)
	{
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_OPENED)
		{
			treePanel.getSearchField().requestFocusInWindow();
		//	this.toFront();
		}
	}   //  processWindowEvent

	/**
	 *	Set Busy
	 *  @param value true if buzy
	 */
	protected void setBusy (boolean value)
	{
		m_startingItem = value;
		if (value)
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		else
			setCursor(Cursor.getDefaultCursor());
	//	setEnabled (!value);        //  causes flicker
	}	//	setBusy

	/**
	 * 	Selection in tree - launch Application
	 *  @param e PropertyChangeEvent
	 */
	public void propertyChange(PropertyChangeEvent e)
	{
		if(e.getPropertyName().equals(VTreePanel.NODE_SELECTION_RIGHT)){
			MTreeNode nd = (MTreeNode)e.getNewValue();
			progressBar.setString(nd.toString());
			return;
		}
		
		MTreeNode nd = (MTreeNode)e.getNewValue();
		log.info(nd.getNode_ID() + " - " + nd.toString());

		//	ignore summary items & when loading
		if (m_startingItem || nd.isSummary())
			return;

		String sta = nd.toString();
		progressBar.setString(sta);
		int cmd = nd.getNode_ID();

		(new AMenuStartItem(cmd, true, sta, this)).start();		//	async load
		//hengsin, updateInfo is call again in AMenuStartItem
		//updateInfo();
	}	//	propertyChange


	/**************************************************************************
	 *	ActionListener
	 *  @param e ActionEvent
	 */
	public void actionPerformed(ActionEvent e)
	{
		//	Buttons
		if (e.getSource() == bNotes)
			gotoNotes();
		else if (e.getSource() == bRequests)
			gotoRequests();
		else if (e.getActionCommand().equals("ShowAllWindow"))
			m_WindowMenu.expose();
		else if (!AEnv.actionPerformed(e.getActionCommand(), m_WindowNo, this))
			log.log(Level.SEVERE, "unknown action=" + e.getActionCommand());
		//updateInfo();
	}	//	actionPerformed

	/**
	 *  Get number of open Notes
	 *  @return number of notes
	 */
	private int getNotes()
	{
		String sql = "SELECT COUNT(1) FROM AD_Note "
			+ "WHERE AD_Client_ID=? AND AD_User_ID IN (0,?)"
			+ " AND Processed='N'";
		int retValue = DB.getSQLValue(null, sql, Env.getAD_Client_ID(Env.getCtx()), m_AD_User_ID);
		return retValue;
	}	//	getNotes

	/**
	 *  Open Note Window
	 */
	private void gotoNotes()
	{
		//	AD_Table_ID for AD_Note = 389		HARDCODED
		if (m_note_Menu_ID == 0)
			m_note_Menu_ID = DB.getSQLValue(null, "SELECT AD_Menu_ID "
				+ "FROM AD_Menu m"
				+ " INNER JOIN AD_TABLE t ON (t.AD_Window_ID=m.AD_Window_ID) "
				+ "WHERE t.AD_Table_ID=?", 389);
		if (m_note_Menu_ID == 0)
			m_note_Menu_ID = 233;	//	fallback HARDCODED
		(new AMenuStartItem (m_note_Menu_ID, true, Msg.translate(m_ctx, "AD_Note_ID"), this)).start();		//	async load
	}   //  gotoMessage

	/**
	 *  Ger Number of open Requests
	 *  @return number of requests
	 */
	private int getRequests()
	{
		if (m_requestSQL == null)
			m_requestSQL = MRole.getDefault().addAccessSQL ("SELECT COUNT(1) FROM R_Request "
				+ "WHERE (SalesRep_ID=? OR AD_Role_ID=?) AND Processed='N'"
				+ " AND (DateNextAction IS NULL OR TRUNC(DateNextAction) <= TRUNC(SysDate))"
				+ " AND (R_Status_ID IS NULL OR R_Status_ID IN (SELECT R_Status_ID FROM R_Status WHERE IsClosed='N'))",
					"R_Request", false, true);	//	not qualified - RW
		int retValue = DB.getSQLValue(null, m_requestSQL, m_AD_User_ID, m_AD_Role_ID); 
		return retValue;
	}	//	getRequests

	/**
	 *  Open Request Window
	 */
	private void gotoRequests()
	{
		//	AD_Table_ID for R_Request = 417		HARDCODED
	//	if (m_request_Menu_ID == 0)		//	Goes to Request (all)
	//		m_request_Menu_ID = DB.getSQLValue (null, "SELECT AD_Menu_ID "
	//			+ "FROM AD_Menu m"
	//			+ " INNER JOIN AD_TABLE t ON (t.AD_Window_ID=m.AD_Window_ID) "
	//			+ "WHERE t.AD_Table_ID=?", 417);
		if (m_request_Menu_ID == 0)
			m_request_Menu_ID = 237;	//	My Requests
		(new AMenuStartItem (m_request_Menu_ID, true, Msg.translate(m_ctx, "R_Request_ID"), this)).start();		//	async load
	}   //  gotoRequests

	/**
	 *	Show Memory Info - run GC if required - Update Requests/Memos/Activities
	 */
	public void updateInfo()
	{
		double total = Runtime.getRuntime().totalMemory() / 1024;
		double free = Runtime.getRuntime().freeMemory() / 1024;
		double used = total - free;
		double percent = used * 100 / total;
		//
		memoryBar.setMaximum((int)total);
		memoryBar.setValue((int)used);
		String msg = MessageFormat.format("{0,number,integer} MB - {1,number,integer}%", 
			new Object[] {new BigDecimal(total / 1024), new BigDecimal(percent)});
		memoryBar.setString(msg);
		//
	//	msg = MessageFormat.format("Total Memory {0,number,integer} kB - Free {1,number,integer} kB", 
		msg = Msg.getMsg(m_ctx, "MemoryInfo",
			new Object[] {new BigDecimal(total), new BigDecimal(free)});
		memoryBar.setToolTipText(msg);
	//	progressBar.repaint();
		
		// CarlosRuiz - globalqss - [ 1881285 ] Remove unnecessary calls to System.gc
		// if (percent > 50)
			// System.gc();

		if (DB.isConnected())
		{
			//	Requests
			int requests =  getRequests();
			bRequests.setText(Msg.translate(m_ctx, "R_Request_ID") + ": " + requests);
			//	Memo
			int notes = getNotes();
			bNotes.setText(Msg.translate(m_ctx, "AD_Note_ID") + ": " + notes);
			//	Activities
			int activities = wfActivity.getActivitiesCount();
			centerPane.setTitleAt(m_tabActivities, Msg.getMsg (m_ctx, "WorkflowActivities") + ": " + activities);
			/*
			log.config(msg
					+ ", Processors=" + Runtime.getRuntime().availableProcessors()
					+ ", Requests=" + requests + ", Notes=" + notes + ", Activities=" + activities 
					+ "," + CConnection.get().getStatus()
				);
			*/
			MSystem.get(m_ctx).info();
		}
	}	//	updateInfo

	
	/**
	 *	Update Activities Label
	 */
	public void updateActivities(int act_length)
	{
		centerPane.setTitleAt(m_tabActivities, Msg.getMsg (m_ctx, "WorkflowActivities") + ": " + act_length);
	}	//	updateInfo

	/*************************************************************************
	 * 	Start Workflow Activity
	 * 	@param AD_Workflow_ID id
	 */
	protected void startWorkFlow (int AD_Workflow_ID)
	{
		centerPane.setSelectedIndex(m_tabWorkflow);		//	switch
		wfPanel.load(AD_Workflow_ID, false);
	}	//	startWorkFlow

	
	/**
	 * 	Change Listener (tab)
	 *	@see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 *	@param e event
	 */
	public void stateChanged (ChangeEvent e)
	{
		//updateInfo();
		//	show activities
		if (centerPane.getSelectedIndex() == m_tabActivities)
		{
			wfActivity.loadActivities();
			wfActivity.display(-1);
		}
	}	//	stateChanged

	public WindowManager getWindowManager() {
		return windowManager;
	}
	
	/**************************************************************************
	 * 	Mouse Listener
	 */
	class AMenu_MouseAdapter extends MouseAdapter
	{
		/**
		 * 	Invoked when the mouse has been clicked on a component.
		 * 	@param e event
		 */
		public void mouseClicked(MouseEvent e) 
		{
			if (e.getClickCount() > 1)
			{
				System.gc();
				//updateInfo();
			}
		}
	}	//	AMenu_MouseAdapter


	/**************************************************************************
	 *	OS Start
	 *  @param args Array of String arguments (ignored)
	 */
	public static void main(String[] args)
	{
		Splash.getSplash();
		Adempiere.startup(true);	//	needs to be here for UI
		new AMenu();
	}	//	main
	
	class InfoUpdater implements Runnable
	{
		boolean stop = false;
		
		public void run()
		{
			int sleep = MSysConfig.getIntValue("MENU_INFOUPDATER_SLEEP_MS", 60000, Env.getAD_Client_ID(Env.getCtx()));
			while(stop == false)
			{
				updateInfo();
	
				try {
					Thread.sleep(sleep);
				} catch(InterruptedException ire) { }
			}
		}
	}

}	//	AMenu
