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
package org.compiere.apps.form;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;

import org.compiere.apps.AEnv;
import org.compiere.apps.AGlassPane;
import org.compiere.apps.AMenu;
import org.compiere.apps.Help;
import org.compiere.apps.WindowMenu;
import org.compiere.model.MRole;
import org.compiere.model.MUser;
import org.compiere.process.ProcessInfo;
import org.compiere.swing.CDialog;
import org.compiere.swing.CFrame;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trace;


/**
 *	Form Framework
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: FormFrame.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 * 
 *  Colin Rooney 2007/03/20 RFE#1670185 & BUG#1684142 
 *                           Extend security to Info Queries
 *	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 114 ] Change "Create From" UI for Form like Dialog in window without "hardcode"
 *		@see https://github.com/adempiere/adempiere/issues/114
 */
public class FormFrame
	implements ActionListener 
{
	/**
	 * @deprecated
	 *	Create Form.
	 *  Need to call openForm
	 */
	public FormFrame ()
	{
		this(0);
	}	//	FormFrame
	
	/**
	 *	Create Form.
	 *  Need to call openForm
	 *	@param gc
	 */
	public FormFrame (int p_ParentWindowNo)
	{
		Frame owner = Env.getWindow(p_ParentWindowNo);
		if(p_ParentWindowNo == 0) {
			CFrame frame = new CFrame(owner.getGraphicsConfiguration());
			frame.setGlassPane(m_glassPane);
			frame.addWindowListener(new java.awt.event.WindowAdapter() 
			{
				public void windowOpened(java.awt.event.WindowEvent evt) 
				{
					formWindowOpened(evt);
				}
			});
			m_MainContent = frame;
			m_WindowNo = Env.createWindowNo (m_MainContent);
		} else {
			CDialog dialog = new CDialog(owner, true);
			dialog.setModalityType(ModalityType.DOCUMENT_MODAL);
			m_MainContent = dialog;
			m_WindowNo = p_ParentWindowNo;
		}
		

		try
		{
			jbInit();
			createMenu();
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
	}	//	FormFrame
	
	/**
	 * Constuctor for create from previous frame
	 * @param frame
	 */
	public FormFrame(CFrame frame) {
		m_MainContent = frame;
		p_AD_Form_ID = frame.getAD_Form_ID();
	}
	
	/**
	 * 
	 * @param gc
	 */
	@Deprecated
	public FormFrame(GraphicsConfiguration gc) {
		this(0);
	}

	private ProcessInfo  m_pi;
	
	/**	WindowNo					*/
	private int			m_WindowNo;
	/** The GlassPane           	*/
	private AGlassPane  m_glassPane = new AGlassPane();
	/**	Description					*/
	private String		m_Description = null;
	/**	Help						*/
	private String		m_Help = null;
	/**	Menu Bar					*/
	private JMenuBar 	menuBar = new JMenuBar();
	/**	The Panel to be displayed	*/
	private FormPanel 	m_panel = null;
	/** Maximize Window				*/
	public boolean 		m_maximize = false;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(FormFrame.class);
	
	/** Form ID			*/
	private int			p_AD_Form_ID = 0;
	//	Yamel Senih FR [ 114 ]
	/**	Container		*/
	private Container 	m_MainContent	= null;
	
	/**
	 * 	Static Init
	 * 	@throws Exception
	 */
	private void jbInit() throws Exception
	{
		setIconImage(org.adempiere.Adempiere.getImage16());
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setJMenuBar(menuBar);
	}	//	jbInit
	
	/**
	 *  Create Menu
	 */
	private void createMenu()
	{
		//      File
		JMenu mFile = AEnv.getMenu("File");
		menuBar.add(mFile);
		AEnv.addMenuItem("PrintScreen", null, KeyStroke.getKeyStroke(KeyEvent.VK_PRINTSCREEN, 0), mFile, this);
		AEnv.addMenuItem("ScreenShot", null, KeyStroke.getKeyStroke(KeyEvent.VK_PRINTSCREEN, Event.SHIFT_MASK), mFile, this);
		AEnv.addMenuItem("Report", null, KeyStroke.getKeyStroke(KeyEvent.VK_P, Event.ALT_MASK), mFile, this);
		mFile.addSeparator();
		AEnv.addMenuItem("End", null, KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.ALT_MASK), mFile, this);
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
		if (MRole.getDefault().isShowPreference())
		{
			mTools.addSeparator();
			AEnv.addMenuItem("Preference", null, null, mTools, this);
		}
		
		//		Window
		AMenu aMenu = (AMenu)Env.getWindow(0);
		JMenu mWindow = new WindowMenu(aMenu.getWindowManager(), null);
		menuBar.add(mWindow);

		//      Help
		JMenu mHelp = AEnv.getMenu("Help");
		menuBar.add(mHelp);
		AEnv.addMenuItem("Help", "Help", KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0),	mHelp, this);
		AEnv.addMenuItem("Online", null, null, mHelp, this);
		AEnv.addMenuItem("EMailSupport", null, null, mHelp, this);
		AEnv.addMenuItem("About", null, null, mHelp, this);
	}   //  createMenu

	/**
	 *  Dispose
	 */
	public void dispose()
	{
		log.config("");
		//	recursive calls
		if (Trace.isCalledFrom("JFrame") && m_panel != null)	//	[x] close window pressed
			m_panel.dispose();
		m_panel = null;
		Env.clearWinContext(m_WindowNo);
		getWindow().dispose();
	}	//  dispose

	/**
	 * 	Open Form
	 * 	@param AD_Form_ID form
	 *  @return true if form opened
	 */
	public boolean openForm (int AD_Form_ID)
	{
		Properties ctx = Env.getCtx();
		//
		String name = null;
		String className = null;
		String sql = "SELECT Name, Description, ClassName, Help FROM AD_Form WHERE AD_Form_ID=?";
		boolean trl = !Env.isBaseLanguage(ctx, "AD_Form");
		if (trl)
			sql = "SELECT t.Name, t.Description, f.ClassName, t.Help "
				+ "FROM AD_Form f INNER JOIN AD_Form_Trl t"
				+ " ON (f.AD_Form_ID=t.AD_Form_ID AND AD_Language=?)"
				+ "WHERE f.AD_Form_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			if (trl)
			{
				pstmt.setString(1, Env.getAD_Language(ctx));
				pstmt.setInt(2, AD_Form_ID);
			}
			else
				pstmt.setInt(1, AD_Form_ID);
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				name = rs.getString(1);
				m_Description = rs.getString(2);
				className = rs.getString(3);
				m_Help = rs.getString(4);
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		if (className == null)
			return false;
		//
		return openForm(AD_Form_ID, className, name);
	}	//	openForm
	
	/**
	 * 	Open Form
	 *	@param AD_Form_ID Form
	 *	@param className class name
	 *	@param name title
	 *	@return true if started
	 */
	protected boolean openForm (int AD_Form_ID, String className, String name)
	{
		log.info("AD_Form_ID=" + AD_Form_ID + " - Class=" + className);
		Properties ctx = Env.getCtx();
		Env.setContext(ctx, m_WindowNo, "WindowName", name);
		setTitle(Env.getHeader(ctx, m_WindowNo));
		try
		{
			//	Create instance w/o parameters
			m_panel = (FormPanel)Class.forName(className).newInstance();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "Class=" + className + ", AD_Form_ID=" + AD_Form_ID, e);
			return false;
		}
		//
		m_panel.init(m_WindowNo, this);
		p_AD_Form_ID = AD_Form_ID;
		return true;
	}	//	openForm

	/**
	 * 	Get Form Panel
	 *	@return form panel
	 */
	public FormPanel getFormPanel()
	{
		return m_panel;
	}	//	getFormPanel

	/**
	 * 	Action Listener
	 * 	@param e event
	 */
	public void actionPerformed(ActionEvent e)
	{
		String cmd = e.getActionCommand();
		if (cmd.equals("End"))
			dispose();
		else if (cmd.equals("Help"))
			actionHelp();
		else if (!AEnv.actionPerformed(cmd, m_WindowNo, m_MainContent))
			log.log(Level.SEVERE, "Not handeled=" + cmd);
	}   //  actionPerformed

	/**
	 *	Show Help
	 */
	private void actionHelp()
	{
		StringBuffer sb = new StringBuffer();
		if (m_Description != null && m_Description.length() > 0)
			sb.append("<h2>").append(m_Description).append("</h2>");
		if (m_Help != null && m_Help.length() > 0)
			sb.append("<p>").append(m_Help);
		Help hlp = new Help (Env.getFrame(m_MainContent), getTitle(), sb.toString());
		hlp.setVisible(true);
	}	//	actionHelp


	/*************************************************************************
	 *  Set Window Busy
	 *  @param busy busy
	 */
	public void setBusy (boolean busy)
	{
		if (busy == m_glassPane.isVisible())
			return;
		log.info("Busy=" + busy);
		if (busy)
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		else
			setCursor(Cursor.getDefaultCursor());
		m_glassPane.setMessage(null);
		m_glassPane.setVisible(busy);
		m_glassPane.requestFocus();
	}   //  setBusy
	
	/**
	 *  Set Busy Message
	 *  @param AD_Message message
	 */
	public void setBusyMessage (String AD_Message)
	{
		m_glassPane.setMessage(AD_Message);
	}   //  setBusyMessage

	/**
	 *  Set and start Busy Counter
	 *  @param time in seconds
	 */
	public void setBusyTimer (int time)
	{
		m_glassPane.setBusyTimer (time);
	}   //  setBusyTimer
	
	 
	/**
	 * 	Set Maximize Window
	 *	@param max maximize
	 */
	public void setMaximize (boolean max)
	{
		m_maximize = max;
	}	//	setMaximize
	 
 
	/**
	 * 	Form Window Opened.
	 * 	Maximize window if required
	 *	@param evt event
	 */
	private void formWindowOpened(java.awt.event.WindowEvent evt) 
	{
		if (m_maximize == true)
		{
			((CFrame) m_MainContent).setVisible(true);
			((CFrame) m_MainContent).setExtendedState(JFrame.MAXIMIZED_BOTH);
		}
   }	//	formWindowOpened

// Add window and tab no called from
	
	public void setProcessInfo(ProcessInfo pi)
	{
		m_pi = pi;
		
	}
	
	public ProcessInfo getProcessInfo()
	{
		return m_pi;
	}

	// End
	
	/**
	 * 	Start Batch
	 *	@param process
	 *	@return running thread
	 */
	public Thread startBatch (final Runnable process)
	{
		Thread worker = new Thread()
		{
			public void run()
			{
				setBusy(true);
				process.run();
				setBusy(false);
			}
		};
		worker.start();
		return worker;
	}	//	startBatch

	/**
	 * @return Returns the AD_Form_ID.
	 */
	public int getAD_Form_ID ()
	{
		return p_AD_Form_ID;
	}	//	getAD_Window_ID
	/**
	 * @return Returns the  manuBar
	 */
	public JMenuBar getMenu()
	{
		return menuBar;
	}
	
	/**
	 * Set Title to Frame
	 * @param p_Title
	 */
	public void setTitle(String p_Title) {
		if(m_MainContent instanceof CFrame) {
			((CFrame) m_MainContent).setTitle(p_Title);
		} else {
			((CDialog) m_MainContent).setTitle(p_Title);
		}
	}
	
	/**
	 * Get Title
	 * @return
	 */
	public String getTitle() {
		if(m_MainContent instanceof CFrame) {
			return ((CFrame) m_MainContent).getTitle();
		} else {
			return ((CDialog) m_MainContent).getTitle();
		}
	}
	
	/**
	 * Verify if is frame
	 * @return
	 */
	public boolean isDialog() {
		return !(m_MainContent instanceof CFrame);
	}
	
	/**
	 * Send to Front
	 */
	public void toFront() {
		getWindow().toFront();
	}
	
	/**
	 * Get Container
	 * @return
	 */
	public Container getContainer() {
		return m_MainContent;
	}
	
	/**
	 * Get parse to Window
	 * @return
	 */
	public Window getWindow() {
		return ((Window) m_MainContent);
	}
	
	/**
	 * Get Frame
	 * @return
	 */
	public CFrame getCFrame() {
		if(!isDialog()) {
			CFrame frame = ((CFrame) m_MainContent);
			//	Set Form ID
			frame.setAD_Form_ID(p_AD_Form_ID);
			return frame;
		}
		//	Default
		return null;
	}
	
	/**
	 * Get Dialog
	 * @return
	 */
	public CDialog getCDialog() {
		if(isDialog()) {
			return ((CDialog) m_MainContent);
		}
		//	Default
		return null;
	}
	
	/**
	 * Support to pack
	 */
	public void pack() {
		getWindow().pack();
	}
	
	/**
	 * Return getContentPanel
	 * @return
	 */
	public Container getContentPane() {
		return getContainer();
	}
	
	/**
	 * Support to setIconImage
	 * @param p_Image
	 */
	public void setIconImage(Image p_Image) {
		if(!isDialog()) {
			((CFrame) m_MainContent).setIconImage(p_Image);
		} else {
			((CDialog) m_MainContent).setIconImage(p_Image);
		}
	}
	
	/**
	 * Support to setDefaultCloseOperation
	 * @param p_CloseOperetion
	 */
	public void setDefaultCloseOperation(int p_CloseOperetion) {
		if(!isDialog()) {
			((CFrame) m_MainContent).setDefaultCloseOperation(p_CloseOperetion);
		} else {
			((CDialog) m_MainContent).setDefaultCloseOperation(p_CloseOperetion);
		}
	}
	
	/**
	 * Support to setJMenuBar
	 * @param p_MenuBar
	 */
	public void setJMenuBar(JMenuBar p_MenuBar) {
		if(!isDialog()) {
			((CFrame) m_MainContent).setJMenuBar(p_MenuBar);
		} else {
			((CDialog) m_MainContent).setJMenuBar(p_MenuBar);
		}
	}
	
	/**
	 * Add support to setCursor
	 * @param cursor
	 */
	public void setCursor(Cursor cursor) {
		getWindow().setCursor(cursor);
	}
	
	/**
	 * Add support to setSize
	 * @param size
	 */
	public void setSize(Dimension size) {
		getWindow().setSize(size);
	}
	
	/**
	 * Add support to getPreferredSize
	 * @return
	 */
	public Dimension getPreferredSize() {
		return getWindow().getPreferredSize();
	}
	
	/**
	 * Add Support to getGraphicsConfiguration
	 * @return
	 */
	public GraphicsConfiguration getGraphicsConfiguration() {
		return getWindow().getGraphicsConfiguration();
	}
	
	/**
	 * Add support to setSize
	 * @param width
	 * @param height
	 */
	public void setSize(int width, int height) {
		getWindow().setSize(width, height);
	}
	
	/**
	 * Add support to addWindowListener
	 * @param adapter
	 */
	public void addWindowListener(WindowAdapter adapter) {
		getWindow().addWindowListener(adapter);
	}
	
	/**
	 * Add support to setVisible Nethod
	 * @param visible
	 */
	public void setVisible(boolean visible) {
		getWindow().setVisible(visible);
	}
}	//	FormFrame