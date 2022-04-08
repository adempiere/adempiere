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
import java.util.logging.Level;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import org.compiere.apps.form.FormFrame;
import org.compiere.model.MMenu;
import org.compiere.model.MRecentItem;
import org.compiere.model.MTask;
import org.compiere.swing.CFrame;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Msg;
import org.compiere.wf.MWFNode;
import org.eevolution.form.VBrowser;


/**
 *	Start application action ( process, workflow, window, form, task )
 *  of a menu item ( ad_menu ) or workflow node ( ad_wf_node ).
 *
 * 	@author 	Jorg Janke
 *  @author victor.perez@e-evoluton.com, www.e-evolution.com 
 * 		<li>FR [ 3426137 ] Smart Browser
 *  	https://sourceforge.net/tracker/?func=detail&aid=3426137&group_id=176962&atid=879335
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 114 ] Change "Create From" UI for Form like Dialog in window without "hardcode"
 *		@see https://github.com/adempiere/adempiere/issues/114
 *		<a href="https://github.com/adempiere/adempiere/issues/884">
 * 		@see FR [ 884 ] Recent Items in Dashboard</a>
 * 	@version 	$Id: AMenuStartItem.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 */
public class AMenuStartItem extends Thread implements ActionListener
{
	/**
	 *  Start Menu Item
	 *
	 * 	@param id		ID
	 * 	@param isMenu   false if Workflow
	 * 	@param name		Name
	 * 	@param menu		Menu
	 */
	public AMenuStartItem (int id, boolean isMenu, String name, AMenu menu) {
		this.id = id;
		this.isMenu = isMenu;
		this.name = name;
		this.menu = menu;
		if (menu != null) {
			m_increment = (menu.progressBar.getMaximum()-menu.progressBar.getMinimum()) / 5;
		}
	}	//	UpdateProgress

	/**	The ID				*/
	private int			id = 0;
	private boolean		isMenu = false;
	private String		name;
	private AMenu		menu;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(AMenuStartItem.class);

	//	Reset Progress Bar
	private Runnable resetProgressBar = new Runnable()
	{
		public void run()
		{
			m_value = 0;
			if (menu != null)
				menu.progressBar.setValue(0);
		}
	};
	//  Progress Bar tick
	private Runnable m_tickPB = new Runnable()
	{
		public void run()
		{
			if (menu == null)
				return;
			//  100/5 => 20 ticks - every .5 sec => 10 seconds loadtime
			final int tick = 5;
			if (menu.progressBar.getValue() < (menu.progressBar.getMaximum() - tick))
				menu.progressBar.setValue(menu.progressBar.getValue() + tick);
		}
	};
	//  Progress Bar max state
	private Runnable updateProgressBar = new Runnable()
	{
		public void run()
		{
			if (menu == null)
				return;
			m_value += m_increment;
			if (menu.progressBar.getValue() > m_value)     //  max value
				menu.progressBar.setValue(m_value);
		}
	};
	/** Value */
	int m_value = 0;
	/** Increment */
	int m_increment = 20;
	/** Timer */
	private Timer timer = new Timer(500, this); // every 1/2 second

	/**
	 *	Start Menu Item
	 */
	public void run() {
		if (menu != null)
			menu.setBusy (true);
		SwingUtilities.invokeLater(resetProgressBar);
		timer.start();
		String errmsg = null;
		try {
			SwingUtilities.invokeLater(updateProgressBar);
			//	For menu
			if(isMenu) {
				MMenu menu = MMenu.getFromId(Env.getCtx(), id);
				startOption(menu.getAction(), menu.getOptionId(), menu.isSOTrx());
				//	Add to recent items
				MRecentItem.addMenuOption(Env.getCtx(), menu.getAD_Menu_ID(), menu.getAD_Window_ID());
			} else {
				MWFNode workflowNode = MWFNode.get(Env.getCtx(), id);
				startOption(workflowNode.getAction(), workflowNode.getOptionId(), false);
			}
			//	Run
			SwingUtilities.invokeLater(updateProgressBar);
		} catch (Exception e) {
			log.log(Level.SEVERE, "ID=" + id, e);
			errmsg = Msg.parseTranslation(Env.getCtx(), e.getMessage());
		}
		// Show error if any
		if (errmsg != null) {
			ADialog.error(0, null, "Error", errmsg);
		}
		//	
		try	{	//	1 sec
			Thread.sleep(1000);
		} catch (InterruptedException ie) {
			
		}
		//	ready for next
		timer.stop();
		SwingUtilities.invokeLater(resetProgressBar);
		if (menu != null) {
			//m_menu.updateInfo();
			menu.setBusy(false);
		}
	}	//	run

	/**
	 * Start Option
	 * @param action
	 * @param optionId
	 * @param isSOTrx
	 */
	private void startOption(String action, int optionId, boolean isSOTrx) {
		if (action.equals(MMenu.ACTION_Window)) {	//	Window
			startWindow(0, optionId);
		} else if (action.equals(MMenu.ACTION_Process) 
				|| action.equals(MMenu.ACTION_Report)) {	//	Process & Report
			startProcess(optionId, isSOTrx);
		} else if (action.equals(MMenu.ACTION_Workbench)) {	//	Workbench
			startWindow (optionId, 0);
		} else if (action.equals(MMenu.ACTION_WorkFlow)) {	//	WorkFlow
			if (menu != null) {
				menu.startWorkFlow(optionId);
			}
		} else if (action.equals(MMenu.ACTION_Task)) {	//	Task
			startTask(optionId);
		} else if (action.equals(MMenu.ACTION_Form)) {	//	Form
			startForm(optionId);
		} else if (action.equals(MMenu.ACTION_SmartBrowse)) {	//	Smart Browse
			startSmartBrowse(optionId, isSOTrx);
		} else {
			log.log(Level.SEVERE, "No valid Action in ID=" + optionId);
		}
	}
	
	
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
			AWindow frame = (AWindow)AEnv.showWindow(AD_Window_ID); 
			if (frame != null) {
				menu.getWindowManager().add(frame);
				return;
			}
			
			if (Ini.isPropertyBool(Ini.P_SINGLE_INSTANCE_PER_WINDOW)) {
				frame = menu.getWindowManager().find(AD_Window_ID);
				if ( frame != null ) {
					frame.toFront();
					return;
				}
			}
			
			SwingUtilities.invokeLater(updateProgressBar);			//	1
			frame = new AWindow(menu.getGraphicsConfiguration());
			boolean OK = false;
			if (AD_Workbench_ID != 0)
				OK = frame.initWorkbench(AD_Workbench_ID);
			else
				OK = frame.initWindow(AD_Window_ID, null);	//	No Query Value
			if (!OK)
				return;

			SwingUtilities.invokeLater(updateProgressBar);			//	2
			if (Ini.isPropertyBool(Ini.P_OPEN_WINDOW_MAXIMIZED) ) 
			{
				AEnv.showMaximized(frame);
			}
			
			//	Center the window
			SwingUtilities.invokeLater(updateProgressBar);			//	3
			if (!(Ini.isPropertyBool(Ini.P_OPEN_WINDOW_MAXIMIZED)) ) 
			{
				frame.validate();
				AEnv.showCenterScreen(frame);
			}
			
			menu.getWindowManager().add(frame);
			
//			if (wfPanel.isVisible())
//				m_WF_Window = frame;            //  maintain one reference
			frame = null;
		}	//	startWindow

		/**
		 *	Start Process.
		 *  Start/show Process Dialog which calls ProcessCtl
		 *  @param AD_Process_ID	process
		 *  @param isSOTrx	is SO trx
		 */
		private void startProcess (int AD_Process_ID, boolean isSOTrx) {
			SwingUtilities.invokeLater(updateProgressBar);			//	1
			timer.stop();
			ProcessDialog pd = new ProcessDialog (menu.getGraphicsConfiguration(), AD_Process_ID, isSOTrx);
			if (!pd.init())
				return;
			timer.start();
			menu.getWindowManager().add(pd);

			SwingUtilities.invokeLater(updateProgressBar);			//	2
			pd.getContentPane().invalidate();
			pd.getContentPane().validate();
			pd.pack();
			//	Center the window
			SwingUtilities.invokeLater(updateProgressBar);			//	3
			AEnv.showCenterScreen(pd);
		}	//	startProcess

	/**
	 *	Start OS Task
	 *  @param AD_Task_ID task
	 */
	private void startTask (int AD_Task_ID)
	{
		SwingUtilities.invokeLater(updateProgressBar);			//	1
		//	Get Command
		MTask task = null;
		if (AD_Task_ID > 0)
			task = new MTask(Env.getCtx(), AD_Task_ID, null);
		if (task.get_ID() != AD_Task_ID)
			task = null;
		if (task == null)
			return;
		SwingUtilities.invokeLater(updateProgressBar);			//	2
		menu.getWindowManager().add(new ATask(name, task));
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
			ff = menu.getWindowManager().findForm(AD_Form_ID);
			if ( ff != null ) {
				ff.toFront();
				return;
			}
		}
		//	Yamel Senih FR [ 114 ] Add Support to Dialog Frame
		ff = new FormFrame(0);
		SwingUtilities.invokeLater(updateProgressBar);			//	1
		boolean ok = ff.openForm(AD_Form_ID);
		if (!ok) {
			ff.dispose();
			return;
		}
		//	Add Menu
		menu.getWindowManager().add(ff.getCFrame());
		SwingUtilities.invokeLater(updateProgressBar);			//	2
		
		//	Center the window
		SwingUtilities.invokeLater(updateProgressBar);			//	3
		if (Ini.isPropertyBool(Ini.P_OPEN_WINDOW_MAXIMIZED) ) {
			AEnv.showMaximized(ff.getCFrame());
		} else
			AEnv.showCenterScreen(ff);
		//	End Yamel Senih
	}	//	startForm
	
	/**
	 *	Start SmartBrowse
	 *  @param AD_SmartBrowse_ID form
	 */
	private void startSmartBrowse (int AD_Browse_ID, Boolean isSOTrx)
	{
		CFrame ff = new CFrame();
		if (Ini.isPropertyBool(Ini.P_SINGLE_INSTANCE_PER_WINDOW)) {
			ff = menu.getWindowManager().findBrowse(AD_Browse_ID).getFrame();
			if ( ff != null ) {
				ff.toFront();
				return;
			}
		}
		//ff = new FormFrame();
		SwingUtilities.invokeLater(updateProgressBar);			//	1
		ff 	= VBrowser.openBrowse(0 , AD_Browse_ID , "", isSOTrx);
		ff.setVisible(true);
		ff.pack();
		menu.getWindowManager().add(ff);
		SwingUtilities.invokeLater(updateProgressBar);			//	2
		
		//	Center the window
		SwingUtilities.invokeLater(updateProgressBar);			//	3
		if (Ini.isPropertyBool(Ini.P_OPEN_WINDOW_MAXIMIZED) ) {
			AEnv.showMaximized(ff);
		} else
			AEnv.showCenterScreen(ff);
	}	//	startForm
	
}	//	StartItem
