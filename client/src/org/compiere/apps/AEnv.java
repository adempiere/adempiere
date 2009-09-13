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

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Set;
import java.util.logging.Level;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.RepaintManager;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.compiere.db.CConnection;
import org.compiere.grid.ed.Calculator;
import org.compiere.interfaces.Server;
import org.compiere.model.GridWindowVO;
import org.compiere.model.MMenu;
import org.compiere.model.MQuery;
import org.compiere.model.MRole;
import org.compiere.process.DocumentEngine;
import org.compiere.swing.CButton;
import org.compiere.swing.CFrame;
import org.compiere.swing.CMenuItem;
import org.compiere.util.CCache;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Msg;
import org.compiere.util.Splash;

/**
 *  Windows Application Environment and utilities
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: AEnv.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 *  
 *  @author Colin Rooney (croo) & kstan_79 RFE#1670185
 *  @author victor.perez@e-evolution.com 
 *  @see FR [ 1966328 ] New Window Info to MRP and CRP into View http://sourceforge.net/tracker/index.php?func=detail&aid=1966328&group_id=176962&atid=879335
 *  
 */
public final class AEnv
{
	/**
	 * Show window: de-iconify and bring it to front
	 * @author teo_sarca [ 1707221 ]
	 */
	public static void showWindow(Window window) {
		window.setVisible(true);
		if (window instanceof Frame) {
			Frame f = (Frame)window;
			int state = f.getExtendedState();
			if ((state & Frame.ICONIFIED) > 0)
				f.setExtendedState(state & ~Frame.ICONIFIED);
		}
		window.toFront();
	}

	/**
	 *  Show in the center of the screen.
	 *  (pack, set location and set visibility)
	 * 	@param window Window to position
	 */
	public static void showCenterScreen(Window window)
	{
		positionCenterScreen(window);
		showWindow(window);
	}   //  showCenterScreen
	
	/**
	 * Show frame as maximized.
	 * @param frame
	 */
	public static void showMaximized(Frame frame)
	{
		frame.pack();
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.toFront();
	}

	/**
	 *	Position window in center of the screen
	 * 	@param window Window to position
	 */
	public static void positionCenterScreen(Window window)
	{
		positionScreen (window, SwingConstants.CENTER);
	}	//	positionCenterScreen

	/**
	 *  Show in the center of the screen.
	 *  (pack, set location and set visibility)
	 * 	@param window Window to position
	 * 	@param position SwingConstants
	 */
	public static void showScreen(Window window, int position)
	{
		positionScreen(window, position);
		showWindow(window);
	}   //  showScreen


	/**
	 *	Position window in center of the screen
	 * 	@param window Window to position
	 * 	@param position SwingConstants
	 */
	public static void positionScreen (Window window, int position)
	{
		window.pack();
		Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
		// take into account task bar and other adornments
		GraphicsConfiguration config = window.getGraphicsConfiguration();
		Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(config);
		sSize.width -= (insets.left + insets.right);
		sSize.height -= (insets.top + insets.bottom);
		
		Dimension wSize = window.getSize();
		//	fit on window
		if (wSize.height > sSize.height)
			wSize.height = sSize.height;
		if (wSize.width > sSize.width)
			wSize.width = sSize.width;
		window.setSize(wSize);
		//	Center
		int x = (sSize.width - wSize.width) / 2;
		int y = (sSize.height - wSize.height) / 2;
		if (position == SwingConstants.CENTER)
			;
		else if (position == SwingConstants.NORTH_WEST)
		{
			x = 0;
			y = 0;
		}
		else if (position == SwingConstants.NORTH)
		{
			y = 0;
		}
		else if (position == SwingConstants.NORTH_EAST)
		{
			x = (sSize.width - wSize.width);
			y = 0;
		}
		else if (position == SwingConstants.WEST)
		{
			x = 0;
		}
		else if (position == SwingConstants.EAST)
		{
			x = (sSize.width - wSize.width);
		}
		else if (position == SwingConstants.SOUTH)
		{
			y = (sSize.height - wSize.height);
		}
		else if (position == SwingConstants.SOUTH_WEST)
		{
			x = 0;
			y = (sSize.height - wSize.height);
		}
		else if (position == SwingConstants.SOUTH_EAST)
		{
			x = (sSize.width - wSize.width);
			y = (sSize.height - wSize.height);
		}
		//
		window.setLocation(x + insets.left, y + insets.top);
	}	//	positionScreen

	/**
	 *	Position in center of the parent window.
	 *  (pack, set location and set visibility)
	 * 	@param parent Parent Window
	 * 	@param window Window to position
	 */
	public static void showCenterWindow(Window parent, Window window)
	{
		positionCenterWindow(parent, window);
		showWindow(window);
	}   //  showCenterWindow

	/**
	 *	Position in center of the parent window
	 *
	 * @param parent Parent Window
	 * @param window Window to position
	 */
	public static void positionCenterWindow(Window parent, Window window)
	{
		if (parent == null)
		{
			positionCenterScreen(window);
			return;
		}
		window.pack();
		//
		Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
		// take into account task bar and other adornments
		GraphicsConfiguration config = window.getGraphicsConfiguration();
		Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(config);
		sSize.width -= (insets.left + insets.right);
		sSize.height -= (insets.top + insets.bottom);
		
		Dimension wSize = window.getSize();
		//	fit on window
		if (wSize.height > sSize.height)
			wSize.height = sSize.height;
		if (wSize.width > sSize.width)
			wSize.width = sSize.width;
		window.setSize(wSize);
		//	center in parent
		Rectangle pBounds = parent.getBounds();
		//	Parent is in upper left corner
		if (pBounds.x == pBounds.y && pBounds.x == 0)
		{
			positionCenterScreen(window);
			return;
		}
		//  Find middle
		int x = pBounds.x + ((pBounds.width-wSize.width)/2);
		if (x < 0)
			x = 0;
		int y = pBounds.y + ((pBounds.height-wSize.height)/2);
		if (y < 0)
			y = 0;

		//	Is it on Screen?
		if (x + wSize.width > sSize.width)
			x = sSize.width - wSize.width;
		if (y + wSize.height > sSize.height)
			y = sSize.height - wSize.height;
		//
	//	System.out.println("Position: x=" + x + " y=" + y + " w=" + wSize.getWidth() + " h=" + wSize.getHeight()
	//		+ " - Parent loc x=" + pLoc.x + " y=" + y + " w=" + pSize.getWidth() + " h=" + pSize.getHeight());
		window.setLocation(x + insets.left, y + insets.top);
	}	//	positionCenterScreen
	
	
	/*************************************************************************
	 * 	Get Button
	 *	@param iconName
	 *	@return button
	 */
	public static CButton getButton (String iconName)
	{
		CButton button = new CButton(Env.getImageIcon(iconName + "16.gif"));
		button.setMargin(new Insets (0, 0, 0, 0));
		button.setToolTipText(Msg.getMsg(Env.getCtx(), iconName));
		button.setDefaultCapable(false);
		return button;
	}	//	getButton


	/**
	 *	Create Menu Title (translate it and set Mnemonics).
	 *	Based on MS notation of &Help => H is Mnemonics
	 *
	 *  @param AD_Message message
	 *  @return JMenu
	 */
	public static JMenu getMenu (String AD_Message)
	{
		JMenu menu = new JMenu();
		String text = Msg.getMsg(Env.getCtx(), AD_Message);
		int pos = text.indexOf('&');
		if (pos != -1 && text.length() > pos)	//	We have a nemonic
		{
			char ch = text.toUpperCase().charAt(pos+1);
			if (ch != ' ')
			{
				text = text.substring(0, pos) + text.substring(pos+1);
				menu.setMnemonic(ch);
			}
		}
		menu.setText(text);
		return menu;
	}	//	getMenu

	/**
	 *  Create Menu Item.
	 *  @param actionName   action command
	 *  @param iconName optional name of the icon, defaults to action if null
	 *  @param ks       optional key stroke
	 *  @param menu     menu to add menu item to
	 *  @param al       action listener to register
	 *  @return MenuItem
	 */
	public static JMenuItem addMenuItem (String actionName, String iconName, KeyStroke ks,
		JMenu menu, ActionListener al)
	{
		if (iconName == null)
			iconName = actionName;
		String text = Msg.getMsg(Env.getCtx(), actionName);
		ImageIcon icon = Env.getImageIcon2(iconName + "16");
		CMenuItem mi = new CMenuItem(text, icon);
		mi.setActionCommand(actionName);
		if (ks != null)
			mi.setAccelerator(ks);
		if (menu != null)
			menu.add(mi);
		if (al != null)
			mi.addActionListener(al);
		return mi;
	}   //  addMenuItem

	/**
	 *  Perform action command for common menu items.
	 * 	Created in AMenu.createMenu(), APanel.createMenu(), FormFrame.createMenu()
	 *  @param actionCommand known action command
	 *  @param WindowNo window no
	 *  @param c Container parent
	 *  @return true if actionCommand was found and performed
	 */
	public static boolean actionPerformed (String actionCommand, int WindowNo, Container c)
	{
		MRole role = MRole.getDefault();
		//  File Menu   ------------------------
		if (actionCommand.equals("PrintScreen"))
		{
			PrintScreenPainter.printScreen (Env.getFrame(c));
		}
		else if (actionCommand.equals("ScreenShot"))
		{
			ScreenShot.createJPEG(Env.getFrame(c), null);
		}
	//	else if (actionCommand.equals("Report"))
	//	{
	//		AEnv.showCenterScreen (new ProcessStart());
	//	}
		else if (actionCommand.equals("Exit"))
		{
			if (ADialog.ask(WindowNo, c, "ExitApplication?"))
			{
				AMenu aMenu = (AMenu)Env.getWindow(0);
				aMenu.dispose() ;
			}
		}
		else if (actionCommand.equals("Logout"))
		{
			AMenu aMenu = (AMenu)Env.getWindow(0);
			aMenu.logout();
		}

		//  View Menu   ------------------------
		else if (actionCommand.equals("InfoProduct") && AEnv.canAccessInfo("PRODUCT"))
		{
			org.compiere.apps.search.Info.showProduct (Env.getFrame(c), WindowNo);
		}
		else if (actionCommand.equals("InfoBPartner") && AEnv.canAccessInfo("BPARTNER"))
		{
			org.compiere.apps.search.Info.showBPartner (Env.getFrame(c), WindowNo);
		}
		else if (actionCommand.equals("InfoAsset") && AEnv.canAccessInfo("ASSET"))
		{
			org.compiere.apps.search.Info.showAsset (Env.getFrame(c), WindowNo);
		}
		else if (actionCommand.equals("InfoAccount") && 
				  MRole.getDefault().isShowAcct() &&
				  AEnv.canAccessInfo("ACCOUNT"))
		{
			new org.compiere.acct.AcctViewer();
		}
		else if (actionCommand.equals("InfoSchedule") && AEnv.canAccessInfo("SCHEDULE"))
		{
			new org.compiere.apps.search.InfoSchedule (Env.getFrame(c), null, false);
		}
		//FR [ 1966328 ] 
		else if (actionCommand.equals("InfoMRP") && AEnv.canAccessInfo("MRP"))
		{
			CFrame frame = (CFrame) Env.getFrame(c);
			int	m_menu_id = MMenu.getMenu_ID("MRP Info");
			AMenu menu = AEnv.getAMenu(frame);
			AMenuStartItem form = new AMenuStartItem (m_menu_id, true, Msg.translate(Env.getCtx(), "MRP Info"), menu);		//	async load
			form.start();
		}
		else if (actionCommand.equals("InfoCRP") && AEnv.canAccessInfo("CRP"))
		{
			CFrame frame = (CFrame) Env.getFrame(c);
			int	m_menu_id = MMenu.getMenu_ID("CRP Info");
			AMenu menu = AEnv.getAMenu(frame);
			AMenuStartItem form = new AMenuStartItem (m_menu_id, true, Msg.translate(Env.getCtx(), "CRP Info"), menu);		//	async load
			form.start();			
		}
		else if (actionCommand.equals("InfoOrder") && AEnv.canAccessInfo("ORDER"))
		{
			org.compiere.apps.search.Info.showOrder (Env.getFrame(c), WindowNo, "");
		}
		else if (actionCommand.equals("InfoInvoice") && AEnv.canAccessInfo("INVOICE"))
		{
			org.compiere.apps.search.Info.showInvoice (Env.getFrame(c), WindowNo, "");
		}
		else if (actionCommand.equals("InfoInOut") && AEnv.canAccessInfo("INOUT"))
		{
			org.compiere.apps.search.Info.showInOut (Env.getFrame(c), WindowNo, "");
		}
		else if (actionCommand.equals("InfoPayment") && AEnv.canAccessInfo("PAYMENT"))
		{
			org.compiere.apps.search.Info.showPayment (Env.getFrame(c), WindowNo, "");
		}
		else if (actionCommand.equals("InfoCashLine") && AEnv.canAccessInfo("CASHJOURNAL"))
		{
			org.compiere.apps.search.Info.showCashLine (Env.getFrame(c), WindowNo, "");
		}
		else if (actionCommand.equals("InfoAssignment") && AEnv.canAccessInfo("RESOURCE"))
		{
			org.compiere.apps.search.Info.showAssignment (Env.getFrame(c), WindowNo, "");
		}
		

		//  Go Menu     ------------------------
		else if (actionCommand.equals("WorkFlow"))
		{
			startWorkflowProcess(0,0);
		}
		else if (actionCommand.equals("Home"))
		{
			showWindow(Env.getWindow(0));
		}

		//  Tools Menu  ------------------------
		else if (actionCommand.equals("Calculator"))
		{
			Calculator calc = new org.compiere.grid.ed.Calculator(Env.getFrame(c));
			calc.setDisposeOnEqual(false);
			AEnv.showCenterScreen (calc);
		}
		else if (actionCommand.equals("Calendar"))
		{
			AEnv.showCenterScreen (new org.compiere.grid.ed.Calendar(Env.getFrame(c)));
		}
		else if (actionCommand.equals("Editor"))
		{
			AEnv.showCenterScreen (new org.compiere.grid.ed.Editor(Env.getFrame(c)));
		}
		else if (actionCommand.equals("Script"))
		{
			new BeanShellEditor(Env.getFrame(c));
		}
		else if (actionCommand.equals("Preference"))
		{
			if (role.isShowPreference()) {
				AEnv.showCenterScreen(new Preference (Env.getFrame(c), WindowNo));
			}
		}

		//  Help Menu   ------------------------
		else if (actionCommand.equals("Online"))
		{
			Env.startBrowser(org.compiere.Adempiere.getOnlineHelpURL());
		}
		else if (actionCommand.equals("EMailSupport"))
		{
			ADialog.createSupportEMail(Env.getFrame(c), Env.getFrame(c).getTitle(), "\n\n");
		}
		else if (actionCommand.equals("About"))
		{
			AEnv.showCenterScreen(new AboutBox(Env.getFrame(c)));
		}
		else
			return false;
		//
		return true;
	}   //  actionPerformed

	/**
	 *  Set Text and Mnemonic for Button.
	 *  Create Mnemonics of text containing '&'.
	 *	Based on MS notation of &Help => H is Mnemonics
	 *  @param b The button
	 *  @param text The text with optional Mnemonics
	 */
	public static void setTextMnemonic (JButton b, String text)
	{
		if (text == null || b == null)
			return;
		int pos = text.indexOf('&');
		if (pos != -1)					//	We have a nemonic
		{
			char ch = text.charAt(pos+1);
			b.setMnemonic(ch);
			b.setText(text.substring(0, pos) + text.substring(pos+1));
		}
		b.setText(text);
	}   //  setTextMnemonic

	/**
	 *  Get Mnemonic character from text.
	 *  @param text text with '&'
	 *  @return Mnemonic or 0
	 */
	public static char getMnemonic (String text)
	{
		int pos = text.indexOf('&');
		if (pos != -1)					//	We have a nemonic
			return text.charAt(pos+1);
		return 0;
	}   //  getMnemonic

	
	/*************************************************************************
	 * 	Zoom
	 *	@param AD_Table_ID
	 *	@param Record_ID
	 */
	public static void zoom (int AD_Table_ID, int Record_ID)
	{
		String TableName = null;
		int AD_Window_ID = 0;
		int PO_Window_ID = 0;
		String sql = "SELECT TableName, AD_Window_ID, PO_Window_ID FROM AD_Table WHERE AD_Table_ID=?";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_Table_ID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				TableName = rs.getString(1);
				AD_Window_ID = rs.getInt(2);
				PO_Window_ID = rs.getInt(3);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		//  Nothing to Zoom to
		if (TableName == null || AD_Window_ID == 0)
			return;

		//	PO Zoom ?
		boolean isSOTrx = true;
		if (PO_Window_ID != 0)
		{
			String whereClause = TableName + "_ID=" + Record_ID;
			isSOTrx = DB.isSOTrx(TableName, whereClause);
			if (!isSOTrx)
				AD_Window_ID = PO_Window_ID;
		}
		
		log.config(TableName + " - Record_ID=" + Record_ID + " (IsSOTrx=" + isSOTrx + ")");
		AWindow frame = new AWindow();
		if (!frame.initWindow(AD_Window_ID, MQuery.getEqualQuery(TableName + "_ID", Record_ID)))
			return;
		addToWindowManager(frame);
		if (Ini.isPropertyBool(Ini.P_OPEN_WINDOW_MAXIMIZED))
		{
			AEnv.showMaximized(frame);
		}
		else
		{
			AEnv.showCenterScreen(frame);
		}
		frame = null;
	}	//	zoom

	/**
	 * 	Zoom
	 *	@param query query
	 */
	public static void zoom (MQuery query)
	{
		if (query == null || query.getTableName() == null || query.getTableName().length() == 0)
			return;
		String TableName = query.getTableName();
		int AD_Window_ID = 0;
		int PO_Window_ID = 0;
		String sql = "SELECT AD_Window_ID, PO_Window_ID FROM AD_Table WHERE TableName=?";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setString(1, TableName);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				AD_Window_ID = rs.getInt(1);
				PO_Window_ID = rs.getInt(2);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		//  Nothing to Zoom to
		if (AD_Window_ID == 0)
			return;

		//	PO Zoom ?
		boolean isSOTrx = true;
		if (PO_Window_ID != 0)
		{
			isSOTrx = DB.isSOTrx(TableName, query.getWhereClause(false));
			if (!isSOTrx)
				AD_Window_ID = PO_Window_ID;
		}
		
		log.config(query + " (IsSOTrx=" + isSOTrx + ")");
		AWindow frame = new AWindow();
		if (!frame.initWindow(AD_Window_ID, query))
			return;
		addToWindowManager(frame);
		if (Ini.isPropertyBool(Ini.P_OPEN_WINDOW_MAXIMIZED))
		{
			AEnv.showMaximized(frame);
		}
		else
		{
			AEnv.showCenterScreen(frame);
		}
		frame = null;
	}	//	zoom
	
	/**
	 * Track open frame in window manager
	 * @param frame
	 */
	public static void addToWindowManager(CFrame frame)
	{
		JFrame top = Env.getWindow(0);
		if (top instanceof AMenu)
		{
			((AMenu)top).getWindowManager().add(frame);
		}
	}
	
	/**
	 * FR [ 1966328 ] 
	 * get AMenu
	 * @param frame
	 */
	public static AMenu getAMenu(CFrame frame)
	{
		JFrame top = Env.getWindow(0);
		if (top instanceof AMenu)
		{
			return (AMenu)top;
		}
		return null;
	}
	/**
	 *	Exit System
	 *  @param status System exit status (usually 0 for no error)
	 */
	public static void exit (int status)
	{
		Env.exitEnv(status);
	}	//	exit

	public static void logout() 
	{
		Env.logout();
		
		Splash.getSplash().setVisible(true);

		//reload
		new AMenu();
	}
	
	/**
	 * 	Is Workflow Process view enabled.
	 *	@return true if enabled
	 */
	public static boolean isWorkflowProcess ()
	{
		if (s_workflow == null)
		{
			s_workflow = Boolean.FALSE;					
			int AD_Table_ID = 645;	//	AD_WF_Process	
			if (MRole.getDefault().isTableAccess (AD_Table_ID, true))	//	RO
				s_workflow = Boolean.TRUE;
			else
			{
				AD_Table_ID = 644;	//	AD_WF_Activity	
				if (MRole.getDefault().isTableAccess (AD_Table_ID, true))	//	RO
					s_workflow = Boolean.TRUE;
				else
					log.config(s_workflow.toString());
			}
			//	Get Window
			if (s_workflow.booleanValue())
			{
				s_workflow_Window_ID = DB.getSQLValue (null,
					"SELECT AD_Window_ID FROM AD_Table WHERE AD_Table_ID=?", AD_Table_ID);
				if (s_workflow_Window_ID == 0)
					s_workflow_Window_ID = 297;	//	fallback HARDCODED
				//	s_workflow = Boolean.FALSE;
				log.config(s_workflow + ", Window=" + s_workflow_Window_ID);
			}
		}
		return s_workflow.booleanValue();
	}	//	isWorkflowProcess

	
	/**
	 * 	Start Workflow Process Window
	 *	@param AD_Table_ID optional table
	 *	@param Record_ID optional record
	 */
	public static void startWorkflowProcess (int AD_Table_ID, int Record_ID)
	{
		if (s_workflow_Window_ID == 0)
			return;
		//
		MQuery query = null;
		if (AD_Table_ID != 0 && Record_ID != 0)
		{
			query = new MQuery("AD_WF_Process");
			query.addRestriction("AD_Table_ID", MQuery.EQUAL, AD_Table_ID);
			query.addRestriction("Record_ID", MQuery.EQUAL, Record_ID);
		}
		//
		AWindow frame = new AWindow();
		if (!frame.initWindow(s_workflow_Window_ID, query))
			return;
		addToWindowManager(frame);
		if (Ini.isPropertyBool(Ini.P_OPEN_WINDOW_MAXIMIZED) ) {
			frame.pack();
			frame.setExtendedState(Frame.MAXIMIZED_BOTH);
			frame.setVisible(true);
			frame.toFront();
		} else
			AEnv.showCenterScreen(frame);
		frame = null;
	}	//	startWorkflowProcess
	
	
	/*************************************************************************/

	/** Workflow Menu		*/
	private static Boolean	s_workflow = null;
	/** Workflow Menu		*/
	private static int		s_workflow_Window_ID = 0;
	
	/**	Server Re-tries		*/
	private static int 		s_serverTries = 0;
	/**	Server Session		*/
	private static Server	s_server = null;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(AEnv.class);

	/**
	 *  Is AppsServer Active ?
	 *  @return true if active
	 */
	public static boolean isServerActive()
	{
		boolean contactAgain = s_server == null && s_serverTries == 0;
		boolean ok = CConnection.get().isAppsServerOK(contactAgain);
		if (ok)
		{
			s_serverTries = 0;
			return true;
		}
		if (s_serverTries > 1)	//	try twice
			return false;

		//	Try to connect
		CLogMgt.enable(false);
		try
		{
			s_serverTries++;
			log.config("try #" + s_serverTries);
			ok = CConnection.get().isAppsServerOK(true);
			if (ok)
				s_serverTries = 0;
		}
		catch (Exception ex)
		{
			ok = false;
			s_server = null;
		}
		CLogMgt.enable(true);
		//
		return ok;
	}   //  isServerActive

	/**
	 *  Get Server Version
	 *  @return Apps Server Version
	 *  @see ALogin#checkVersion
	 */
	public static String getServerVersion ()
	{
		return CConnection.get().getServerVersion();
	}   //  getServerVersion

	/**	Window Cache		*/
	private static CCache<Integer,GridWindowVO>	s_windows 
		= new CCache<Integer,GridWindowVO>("AD_Window", 10); 
	
	/**
	 *  Get Window Model
	 *
	 *  @param WindowNo  Window No
	 *  @param AD_Window_ID window
	 *  @param AD_Menu_ID menu
	 *  @return Model Window Value Obkect
	 */
	public static GridWindowVO getMWindowVO (int WindowNo, int AD_Window_ID, int AD_Menu_ID)
	{
		log.config("Window=" + WindowNo + ", AD_Window_ID=" + AD_Window_ID);
		GridWindowVO mWindowVO = null;
		if (AD_Window_ID != 0 && Ini.isCacheWindow())	//	try cache
		{
			mWindowVO = s_windows.get(AD_Window_ID);
			if (mWindowVO != null)
			{
				mWindowVO = mWindowVO.clone(WindowNo);
				log.info("Cached=" + mWindowVO);
			}
		}
		
		//  Create Window Model on Client
		if (mWindowVO == null)
		{
			log.config("create local");
			mWindowVO = GridWindowVO.create (Env.getCtx(), WindowNo, AD_Window_ID, AD_Menu_ID);
			if (mWindowVO != null)
				s_windows.put(AD_Window_ID, mWindowVO);
		}	//	from Client
		if (mWindowVO == null)
			return null;
		
		//  Check (remote) context
		if (!mWindowVO.ctx.equals(Env.getCtx()))
		{
			//  Remote Context is called by value, not reference
			//  Add Window properties to context
			Enumeration<?> keyEnum = mWindowVO.ctx.keys();
			while (keyEnum.hasMoreElements())
			{
				String key = (String)keyEnum.nextElement();
				if (key.startsWith(WindowNo+"|"))
				{
					String value = mWindowVO.ctx.getProperty (key);
					Env.setContext(Env.getCtx(), key, value);
				}
			}
			//  Sync Context
			mWindowVO.setCtx(Env.getCtx());
		}
		return mWindowVO;
	}   //  getWindow

	/**
	 *  Post Immediate
	 *  @param  WindowNo 		window
	 *  @param  AD_Table_ID     Table ID of Document
	 *  @param  AD_Client_ID    Client ID of Document
	 *  @param  Record_ID       Record ID of this document
	 *  @param  force           force posting
	 *  @return null if success, otherwise error
	 */
	public static String postImmediate (int WindowNo, int AD_Client_ID, 
		int AD_Table_ID, int Record_ID, boolean force)
	{
		log.config("Window=" + WindowNo 
			+ ", AD_Table_ID=" + AD_Table_ID + "/" + Record_ID
			+ ", Force=" + force);

		String error = DocumentEngine.postImmediate(Env.getCtx(), AD_Client_ID, AD_Table_ID, Record_ID, force, null);

		return error;
	}   //  postImmediate

	/**
	 *  Cache Reset
	 *  @param  tableName	table name
	 *  @param  Record_ID	record id
	 */
	public static void cacheReset (String tableName, int Record_ID)
	{
		log.config("TableName=" + tableName + ", Record_ID=" + Record_ID);

		//  try to get from Server when enabled
		if (isServerActive())
		{
			log.config("trying server");
			try
			{
				Server server = CConnection.get().getServer();
				if (server != null)
				{
					server.cacheReset(tableName, Record_ID); 
				}
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, "ex", e);
				s_server = null;
			}
		}
	}   //  cacheReset
	
	/**
	 * Update all windows after look and feel changes.
	 * @since 2006-11-27 
	 */
	public static void updateUI()
	{
		Set<Window> updated = Env.updateUI();
		JFrame top = Env.getWindow(0);
		if (top instanceof AMenu)
		{
			CFrame[] frames = ((AMenu)top).getWindowManager().getWindows();
			for (CFrame f : frames)
			{
				if (updated.contains(f)) continue;
				SwingUtilities.updateComponentTreeUI(f);
				f.validate();
				RepaintManager mgr = RepaintManager.currentManager(f);
				Component childs[] = f.getComponents();
				for (Component c : childs) {
					if (c instanceof JComponent)
						mgr.markCompletelyDirty((JComponent)c);
				}
				f.repaint();
				updated.add(f);
			}
		}
	}
	
	/**
	 *  Validate permissions to access Info queries on the view menu 
	*   @author kstan_79
	*   @return true if access is allowed
	*/ 
	
	public static boolean canAccessInfo(String infoWindowName) 
	{
		boolean result=false;
		int roleid= Env.getAD_Role_ID(Env.getCtx());
		String sqlRolePermission="Select COUNT(AD_ROLE_ID) AS ROWCOUNT FROM AD_ROLE WHERE AD_ROLE_ID=" + roleid  
	                              + " AND ALLOW_INFO_" + infoWindowName + "='Y'"; 

		log.config(sqlRolePermission); 
		PreparedStatement prolestmt = null; 
		ResultSet rs = null;
		try 
		{ 
			prolestmt = DB.prepareStatement (sqlRolePermission, null); 
	 
			rs = prolestmt.executeQuery ();  
	 
			rs.next(); 
	 
			if (rs.getInt("ROWCOUNT")>0)
			{
				result=true;
			}
			else 
			{
				return false;
			}
		} 
		catch (Exception e) 
		{
				System.out.println(e); 
				log.log(Level.SEVERE, "(1)", e); 
		} 
		finally
		{
			DB.close(rs, prolestmt);
			rs = null; prolestmt = null;
		}
	
		return result; 
	} // 	canAccessInfo
 
}	//	AEnv
