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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import org.compiere.apps.form.FormFrame;
import org.compiere.model.MTask;
import org.compiere.swing.CFrame;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Msg;
import org.eevolution.form.VBrowser;


/**
 *	Start application action ( process, workflow, window, form, task )
 *  of a menu item ( ad_menu ) or workflow node ( ad_wf_node ).
 *
 * 	@author 	Jorg Janke
 *  @author victor.perez@e-evoluton.com, www.e-evolution.com 
 * 		<li>FR [ 3426137 ] Smart Browser
 *  	https://sourceforge.net/tracker/?func=detail&aid=3426137&group_id=176962&atid=879335
 * 	@version 	$Id: AMenuStartItem.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 */
public class AMenuStartItem extends Thread implements ActionListener
{
	/**
	 *  Start Menu Item
	 *
	 * 	@param ID		ID
	 * 	@param isMenu   false if Workflow
	 * 	@param name		Name
	 * 	@param menu		Menu
	 */
	public AMenuStartItem (int ID, boolean isMenu, String name, AMenu menu)
	{
		m_ID = ID;
		m_isMenu = isMenu;
		m_name = name;
		m_menu = menu;
		if (menu != null)
			m_increment = (menu.progressBar.getMaximum()-menu.progressBar.getMinimum()) / 5;
	}	//	UpdateProgress

	/**	The ID				*/
	private int			m_ID = 0;
	private boolean		m_isMenu = false;
	private String		m_name;
	private AMenu		m_menu;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(AMenuStartItem.class);

	//	Reset Progress Bar
	private Runnable m_resetPB = new Runnable()
	{
		public void run()
		{
			m_value = 0;
			if (m_menu != null)
				m_menu.progressBar.setValue(0);
		}
	};
	//  Progress Bar tick
	private Runnable m_tickPB = new Runnable()
	{
		public void run()
		{
			if (m_menu == null)
				return;
			//  100/5 => 20 ticks - every .5 sec => 10 seconds loadtime
			final int tick = 5;
			if (m_menu.progressBar.getValue() < (m_menu.progressBar.getMaximum() - tick))
				m_menu.progressBar.setValue(m_menu.progressBar.getValue() + tick);
		}
	};
	//  Progress Bar max state
	private Runnable m_updatePB = new Runnable()
	{
		public void run()
		{
			if (m_menu == null)
				return;
			m_value += m_increment;
			if (m_menu.progressBar.getValue() > m_value)     //  max value
				m_menu.progressBar.setValue(m_value);
		}
	};
	/** Value */
	int m_value = 0;
	/** Increment */
	int m_increment = 20;
	/** Timer */
	private Timer m_timer = new Timer(500, this); // every 1/2 second

	/**
	 *	Start Menu Item
	 */
	public void run()
	{
		if (m_menu != null)
			m_menu.setBusy (true);
		SwingUtilities.invokeLater(m_resetPB);
		m_timer.start();
		SwingUtilities.invokeLater(m_updatePB);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String errmsg = null;
		try
		{
			String sql = "SELECT * FROM AD_Menu WHERE AD_Menu_ID=?";
			if (!m_isMenu)
				sql = "SELECT * FROM AD_WF_Node WHERE AD_WF_Node_ID=?";
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, m_ID);
			rs = pstmt.executeQuery();

			SwingUtilities.invokeLater(m_updatePB);
			if (rs.next())	//	should only be one
			{
				String Action = rs.getString("Action");
				String IsSOTrx = "Y";
				if (m_isMenu)
					IsSOTrx = rs.getString("IsSOTrx");
				int cmd;
				if (Action.equals("W"))				//	Window
				{
					cmd = rs.getInt("AD_Window_ID");
					startWindow(0, cmd);
				}
				else if (Action.equals("P") || Action.equals("R"))	//	Process & Report
				{
					cmd = rs.getInt("AD_Process_ID");
					startProcess(cmd, IsSOTrx);
				}
				else if (Action.equals("B"))		//	Workbench
				{
					cmd = rs.getInt("AD_Workbench_ID");
					startWindow (cmd, 0);
				}
				else if (Action.equals("F"))		//	WorkFlow
				{
					if (m_isMenu)
						cmd = rs.getInt("AD_Workflow_ID");
					else
						cmd = rs.getInt("Workflow_ID");
					if (m_menu != null)
						m_menu.startWorkFlow(cmd);
				}
				else if (Action.equals("T"))		//	Task
				{
					cmd = rs.getInt("AD_Task_ID");
					startTask(cmd);
				}
				else if (Action.equals("X"))		//	Form
				{
					cmd = rs.getInt("AD_Form_ID");
					startForm(cmd);
				}
				else if (Action.equals("S"))		//	Form
				{
					cmd = rs.getInt("AD_Browse_ID");
					startSmartBrowse(cmd);
				}
				else
					log.log(Level.SEVERE, "No valid Action in ID=" + m_ID);
			}	//	for all records

			SwingUtilities.invokeLater(m_updatePB);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "ID=" + m_ID, e);
			errmsg = Msg.parseTranslation(Env.getCtx(), e.getMessage());
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		// Show error if any
		if (errmsg != null)
			ADialog.error(0, null, "Error", errmsg);

		try	{Thread.sleep(1000);}	//	1 sec
		catch (InterruptedException ie) {}

		//	ready for next
		m_timer.stop();
		SwingUtilities.invokeLater(m_resetPB);
		if (m_menu != null)
		{
			//m_menu.updateInfo();
			m_menu.setBusy(false);
		}
	}	//	run


	/**
	 *  Actlion Listener for Timer
	 *  @param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
		SwingUtilities.invokeLater(m_tickPB);
	}   //  actionPerformed

		/**
		 *	Start Window
		 *
		 * @param AD_Workbench_ID workbench
		 * @param AD_Window_ID	window
		 */
		private void startWindow(int AD_Workbench_ID, int AD_Window_ID)
		{
			AWindow frame = (AWindow)Env.showWindow(AD_Window_ID); 
			if (frame != null) {
				m_menu.getWindowManager().add(frame);
				return;
			}
			
			if (Ini.isPropertyBool(Ini.P_SINGLE_INSTANCE_PER_WINDOW)) {
				frame = m_menu.getWindowManager().find(AD_Window_ID);
				if ( frame != null ) {
					frame.toFront();
					return;
				}
			}
			
			SwingUtilities.invokeLater(m_updatePB);			//	1
			frame = new AWindow(m_menu.getGraphicsConfiguration());
			boolean OK = false;
			if (AD_Workbench_ID != 0)
				OK = frame.initWorkbench(AD_Workbench_ID);
			else
				OK = frame.initWindow(AD_Window_ID, null);	//	No Query Value
			if (!OK)
				return;

			SwingUtilities.invokeLater(m_updatePB);			//	2
			if (Ini.isPropertyBool(Ini.P_OPEN_WINDOW_MAXIMIZED) ) 
			{
				AEnv.showMaximized(frame);
			}
			
			//	Center the window
			SwingUtilities.invokeLater(m_updatePB);			//	3
			if (!(Ini.isPropertyBool(Ini.P_OPEN_WINDOW_MAXIMIZED)) ) 
			{
				frame.validate();
				AEnv.showCenterScreen(frame);
			}
			
			m_menu.getWindowManager().add(frame);
			
//			if (wfPanel.isVisible())
//				m_WF_Window = frame;            //  maintain one reference
			frame = null;
		}	//	startWindow

		/**
		 *	Start Process.
		 *  Start/show Process Dialog which calls ProcessCtl
		 *  @param AD_Process_ID	process
		 *  @param IsSOTrx	is SO trx
		 */
		private void startProcess (int AD_Process_ID, String IsSOTrx)
		{
			SwingUtilities.invokeLater(m_updatePB);			//	1
			boolean isSO = false;
			if (IsSOTrx != null && IsSOTrx.equals("Y"))
				isSO = true;
			m_timer.stop();
			ProcessDialog pd = new ProcessDialog (m_menu.getGraphicsConfiguration(), AD_Process_ID, isSO);
			if (!pd.init())
				return;
			m_timer.start();
			m_menu.getWindowManager().add(pd);

			SwingUtilities.invokeLater(m_updatePB);			//	2
			pd.getContentPane().invalidate();
			pd.getContentPane().validate();
			pd.pack();
			//	Center the window
			SwingUtilities.invokeLater(m_updatePB);			//	3
			AEnv.showCenterScreen(pd);
		}	//	startProcess

	/**
	 *	Start OS Task
	 *  @param AD_Task_ID task
	 */
	private void startTask (int AD_Task_ID)
	{
		SwingUtilities.invokeLater(m_updatePB);			//	1
		//	Get Command
		MTask task = null;
		if (AD_Task_ID > 0)
			task = new MTask(Env.getCtx(), AD_Task_ID, null);
		if (task.get_ID() != AD_Task_ID)
			task = null;
		if (task == null)
			return;
		SwingUtilities.invokeLater(m_updatePB);			//	2
		m_menu.getWindowManager().add(new ATask(m_name, task));
	//	ATask.start(m_name, task);
	}	//	startTask

	/**
	 *	Start Form
	 *  @param AD_Form_ID form
	 */
	private void startForm (int AD_Form_ID)
	{
		FormFrame ff = null;
		if (Ini.isPropertyBool(Ini.P_SINGLE_INSTANCE_PER_WINDOW)) {
			ff = m_menu.getWindowManager().findForm(AD_Form_ID);
			if ( ff != null ) {
				ff.toFront();
				return;
			}
		}
		ff = new FormFrame(m_menu.getGraphicsConfiguration());
		SwingUtilities.invokeLater(m_updatePB);			//	1
		boolean ok = ff.openForm(AD_Form_ID);
		if (!ok) {
			ff.dispose();
			return;
		}
		m_menu.getWindowManager().add(ff);
		SwingUtilities.invokeLater(m_updatePB);			//	2
		
		//	Center the window
		SwingUtilities.invokeLater(m_updatePB);			//	3
		if (Ini.isPropertyBool(Ini.P_OPEN_WINDOW_MAXIMIZED) ) {
			AEnv.showMaximized(ff);
		} else
			AEnv.showCenterScreen(ff);
	}	//	startForm
	
	/**
	 *	Start SmartBrowse
	 *  @param AD_SmartBrowse_ID form
	 */
	private void startSmartBrowse (int AD_Browse_ID)
	{
		CFrame ff = new CFrame();
		if (Ini.isPropertyBool(Ini.P_SINGLE_INSTANCE_PER_WINDOW)) {
			ff = m_menu.getWindowManager().findBrowse(AD_Browse_ID).getFrame();
			if ( ff != null ) {
				ff.toFront();
				return;
			}
		}
		//ff = new FormFrame();
		SwingUtilities.invokeLater(m_updatePB);			//	1
		ff 	= VBrowser.openBrowse(AD_Browse_ID);
		ff.setVisible(true);
		ff.pack();
		m_menu.getWindowManager().add(ff);
		SwingUtilities.invokeLater(m_updatePB);			//	2
		
		//	Center the window
		SwingUtilities.invokeLater(m_updatePB);			//	3
		if (Ini.isPropertyBool(Ini.P_OPEN_WINDOW_MAXIMIZED) ) {
			AEnv.showMaximized(ff);
		} else
			AEnv.showCenterScreen(ff);
	}	//	startForm
	
}	//	StartItem
