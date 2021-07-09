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
package org.compiere.util;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Window;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.RepaintManager;
import javax.swing.SwingUtilities;

import org.compiere.db.CConnection;
import org.compiere.model.MLookupCache;
import org.compiere.model.MSession;
import org.compiere.swing.CFrame;


/**
 *  Swing extension of System Environment and static variables.
 *  
 */
public final class SwingEnv
{
	/**	Logging								*/
	private static CLogger				s_log = CLogger.getCLogger(SwingEnv.class);
		
	public static void reset (boolean finalCall)
	{
		if (Ini.isClient())
		{
			closeWindows();
			
			//	Dismantle windows
			/**
			for (int i = 0; i < s_windows.size(); i++)
			{
				Container win = (Container)s_windows.get(i);
				if (win.getClass().getName().endsWith("AMenu")) // Null pointer
					;
				else if (win instanceof Window)
					((Window)win).dispose();
				else
					win.removeAll();
			}
			**/
			//bug [ 1574630 ]
			if (s_windows.size() > 0) {
				if (!finalCall) {
					Container c = s_windows.get(0);
					s_windows.clear();
					createWindowNo(c);
				} else {
					s_windows.clear();
				}
			}
		}

		if (Ini.isClient())
			DB.closeTarget();
		
		//	Reset Role Access
		if (!finalCall)
		{
			if (Ini.isClient())
				DB.setDBTarget(CConnection.get());
		}

		Env.reset(finalCall);

	}	//	resetAll

	/**
	 * Logout from the system
	 */
	public static void logout()
	{
		//	End Session
		MSession session = MSession.get(Env.getCtx(), false);	//	finish
		if (session != null)
			session.logout();
		//
		reset(true);	// final cache reset
		//
		
		CConnection.get().setAppServerCredential(null, null);
	}

	/**
	 *	Exit System
	 *  @param status System exit status (usually 0 for no error)
	 */
	public static void exitEnv (int status)
	{
		//hengsin, avoid unncessary query of session when exit without log in
		if (DB.isConnected(false)) {
			//	End Session
			MSession session = MSession.get(Env.getCtx(), false);	//	finish
			if (session != null)
				session.logout();
		}
		//
		reset(true);	// final cache reset
		s_log.info("");
		//
		CLogMgt.shutdown();
		//
		if (Ini.isClient())
			System.exit (status);
	}	//	close

	/*************************************************************************/

	//	Array of active Windows
	private static ArrayList<Container>	s_windows = new ArrayList<Container>(20);

	/**
	 *	Add Container and return WindowNo.
	 *  The container is a APanel, AWindow or JFrame/JDialog
	 *  @param win window
	 *  @return WindowNo used for context
	 */
	public static int createWindowNo(Container win)
	{
		int retValue = s_windows.size();
		s_windows.add(win);
		return retValue;
	}	//	createWindowNo

	/**
	 *	Clean up context for Window (i.e. delete it)
	 *  @param ctx context
	 *  @param WindowNo window
	 */
	public static void clearWinContext(Properties ctx, int WindowNo)
	{
		if (ctx == null)
			throw new IllegalArgumentException ("Require Context");
		//
		Object[] keys = ctx.keySet().toArray();
		for (int i = 0; i < keys.length; i++)
		{
			String tag = keys[i].toString();
			if (tag.startsWith(WindowNo+"|"))
				ctx.remove(keys[i]);
		}
		//  Clear Lookup Cache
		MLookupCache.cacheReset(WindowNo);
	//	MLocator.cacheReset(WindowNo);
		//
		if (Ini.isClient())
			removeWindow(WindowNo);
	}	//	clearWinContext

	/**
	 *	Clean up context for Window (i.e. delete it)
	 *  @param WindowNo window
	 */
	public static void clearWinContext(int WindowNo)
	{
		clearWinContext (Env.getCtx(), WindowNo);
	}	//	clearWinContext


	/**
	 *	Remove window from active list
	 *  @param WindowNo window
	 */
	private static void removeWindow (int WindowNo)
	{
		if (WindowNo < s_windows.size())
			s_windows.set(WindowNo, null);
	}	//	removeWindow


	/**************************************************************************
	 *	Get Frame of Window
	 *  @param container Container
	 *  @return JFrame of container or null
	 */
	public static JFrame getFrame (Container container)
	{
		Container element = container;
		while (element != null)
		{
			if (element instanceof JFrame)
				return (JFrame)element;
			element = element.getParent();
		}
		return null;
	}	//	getFrame

	/**
	 *	Get Graphics of container or its parent.
	 *  The element may not have a Graphic if not displayed yet,
	 * 	but the parent might have.
	 *  @param container Container
	 *  @return Graphics of container or null
	 */
	public static Graphics getGraphics (Container container)
	{
		Container element = container;
		while (element != null)
		{
			Graphics g = element.getGraphics();
			if (g != null)
				return g;
			element = element.getParent();
		}
		return null;
	}	//	getFrame

	/**
	 *  Return JDialog or JFrame Parent
	 *  @param container Container
	 *  @return JDialog or JFrame of container
	 */
	public static Window getParent (Container container)
	{
		Container element = container;
		while (element != null)
		{
			if (element instanceof JDialog || element instanceof JFrame)
				return (Window)element;
			if (element instanceof Window)
				return (Window)element;
			element = element.getParent();
		}
		return null;
	}   //  getParent

	   	
	/** Array of hidden Windows				*/
	private static ArrayList<CFrame>	s_hiddenWindows = new ArrayList<CFrame>();
	
	/** Closing Window Indicator			*/
	private static boolean 				s_closingWindows = false;
	
	/**
	 * 	Hide Window
	 *	@param window window
	 *	@return true if window is hidden, otherwise close it
	 */
	static public boolean hideWindow(CFrame window)
	{
		if (!Ini.isCacheWindow() || s_closingWindows)
			return false;
		for (int i = 0; i < s_hiddenWindows.size(); i++)
		{
			CFrame hidden = s_hiddenWindows.get(i);
			s_log.info(i + ": " + hidden);
			if (hidden.getAD_Window_ID() == window.getAD_Window_ID())
				return false;	//	already there
		}
		if (window.getAD_Window_ID() != 0)	//	workbench
		{
			if (s_hiddenWindows.add(window))
			{
				window.setVisible(false);
				s_log.info(window.toString());
			//	window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_ICONIFIED));
				if (s_hiddenWindows.size() > 10) {
					CFrame toClose = s_hiddenWindows.remove(0);		//	sort of lru
					try {
						s_closingWindows = true;
						toClose.dispose();
					} finally {
						s_closingWindows = false;
					}
				}
				return true;
			}
		}
		return false;
	}	//	hideWindow
	
	/**
	 * 	Show Window
	 *	@param AD_Window_ID window
	 *	@return true if window re-displayed
	 */
	static public CFrame showWindow (int AD_Window_ID)
	{
		for (int i = 0; i < s_hiddenWindows.size(); i++)
		{
			CFrame hidden = s_hiddenWindows.get(i);
			if (hidden.getAD_Window_ID() == AD_Window_ID)
			{
				s_hiddenWindows.remove(i);
				s_log.info(hidden.toString());
				hidden.setVisible(true);
				// De-iconify window - teo_sarca [ 1707221 ]
				int state = hidden.getExtendedState();
				if ((state & CFrame.ICONIFIED) > 0)
					hidden.setExtendedState(state & ~CFrame.ICONIFIED);
				//
				hidden.toFront();
				return hidden;
			}
		}
		return null;
	}	//	showWindow

	/**
	 * 	Clode Windows.
	 */
	static void closeWindows ()
	{
		s_closingWindows = true;
		for (int i = 0; i < s_hiddenWindows.size(); i++)
		{
			CFrame hidden = s_hiddenWindows.get(i);
			hidden.dispose();
		}
		s_hiddenWindows.clear();
		s_closingWindows = false;
	}	//	closeWindows

	
	/**
	 * Update all windows after look and feel changes.
	 * @since 2006-11-27 
	 */
	public static Set<Window>updateUI() 
	{
		Set<Window> updated = new HashSet<Window>();
		for (Container c : s_windows)
		{
			Window w = getFrame(c);
			if (w == null) continue;
			if (updated.contains(w)) continue;
			SwingUtilities.updateComponentTreeUI(w);
			w.validate();
			RepaintManager mgr = RepaintManager.currentManager(w);
			Component childs[] = w.getComponents();
			for (Component child : childs) {
				if (child instanceof JComponent)
					mgr.markCompletelyDirty((JComponent)child);
			}
			w.repaint();
			updated.add(w);
		}
		for (Window w : s_hiddenWindows)
		{
			if (updated.contains(w)) continue;
			SwingUtilities.updateComponentTreeUI(w);
			w.validate();
			RepaintManager mgr = RepaintManager.currentManager(w);
			Component childs[] = w.getComponents();
			for (Component child : childs) {
				if (child instanceof JComponent)
					mgr.markCompletelyDirty((JComponent)child);
			}
			w.repaint();
			updated.add(w);
		}
		return updated;
	}

	/**
	 *	Return the JFrame pointer of WindowNo - or null
	 *  @param WindowNo window
	 *  @return JFrame of WindowNo
	 */
	public static JFrame getWindow (int WindowNo)
	{
		JFrame retValue = null;
		try
		{
			retValue = getFrame ((Container)s_windows.get(WindowNo));
		}
		catch (Exception e)
		{
			Env.s_log.log(Level.SEVERE, e.toString());
		}
		return retValue;
	}	//	getWindow

	/**
	 *	Search Window by comparing the Frames
	 *  @param container container
	 *  @return WindowNo of container or 0
	 */
	public static int getWindowNo (Container container)
	{
		if (container == null)
			return 0;
		JFrame winFrame = getFrame(container);
		if (winFrame == null)
			return 0;
	
		//  loop through windows
		for (int i = 0; i < s_windows.size(); i++)
		{
			Container cmp = (Container)s_windows.get(i);
			if (cmp != null)
			{
				JFrame cmpFrame = getFrame(cmp);
				if (winFrame.equals(cmpFrame))
					return i;
			}
		}
		return 0;
	}	//	getWindowNo

}   //  SwingEnv