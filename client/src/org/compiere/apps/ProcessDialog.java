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

import java.awt.AWTEvent;
import java.awt.GraphicsConfiguration;
import java.util.logging.Level;

import org.compiere.process.ProcessInfo;
import org.compiere.swing.CFrame;
import org.compiere.util.ASyncProcess;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 *	Dialog to Start process.
 *	Displays information about the process
 *		and lets the user decide to start it
 *  	and displays results (optionally print them).
 *  Calls ProcessCtl to execute.
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: ProcessDialog.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 *  @author		Low Heng Sin
 *  - Merge process parameter dialog into process dialog.
 *  @author     arboleda - globalqss
 *  - Implement ShowHelp option on processes and reports
 *  @author		Teo Sarca, SC ARHIPAC SERVICE SRL
 *  				<li>BF [ 1893525 ] ProcessDialog: Cannot select the text from text field
 *  				<li>BF [ 1963128 ] Running a process w/o trl should display an error
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *					<li>FR [ 265 ] ProcessParameterPanel is not MVC
 *					@see https://github.com/adempiere/adempiere/issues/265
 *					<a href="https://github.com/adempiere/adempiere/issues/571">
 * 					@see FR [ 571 ] Process Dialog is not MVC</a>
 * 					<a href="https://github.com/adempiere/adempiere/issues/659">
 * 					@see FR [ 659 ] Default button is not set on Process Dialog</a>
 */
public class ProcessDialog extends CFrame
	implements IProcessDialog, ASyncProcess {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 790447068287846414L;
	
	/**
	 *	Dialog to start Process
	 *
	 * @param gc
	 * @param AD_Process_ID process
	 * @param isSOTrx is sales trx
	 */
	public ProcessDialog (GraphicsConfiguration gc, int AD_Process_ID, boolean isSOTrx) {
		super(gc);
		log.info("Process=" + AD_Process_ID + "; SOTrx=" + isSOTrx);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		processId = AD_Process_ID;
		windowNo = Env.createWindowNo (this);
		Env.setContext(Env.getCtx(), windowNo, "IsSOTrx", isSOTrx ? "Y" : "N");
		try {
			jbInit();
		} catch(Exception ex) {
			log.log(Level.SEVERE, "", ex);
		}
	}	//	ProcessDialog

	private int 		    processId;
	private int			    windowNo;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(ProcessDialog.class);
	//
	private ProcessPanel parameterPanel = null;
	
	/**
	 *	Static Layout
	 *  @throws Exception
	 */
	private void jbInit() throws Exception {
		setIconImage(Env.getImage("mProcess.gif"));
	}	//	jbInit

	/**
	 * 	Set Visible 
	 * 	(set focus to OK if visible)
	 * 	@param visible true if visible
	 */
	public void setVisible (boolean visible) {
		super.setVisible(visible);
		if (visible) {
			parameterPanel.getPanel().requestFocus();
		}
	}	//	setVisible

	/**
	 *	Dispose
	 */
	public void dispose()
	{
		Env.clearWinContext(windowNo);
		super.dispose();
	}	//	dispose


	/**
	 *	Dynamic Init
	 *  @return true, if there is something to process (start from menu)
	 */
	public boolean init()
	{
		log.config("");
		//	Similar to APanel.actionButton
		ProcessInfo pi = new ProcessInfo(null, processId);
		pi.setAD_User_ID (Env.getAD_User_ID(Env.getCtx()));
		pi.setAD_Client_ID(Env.getAD_Client_ID(Env.getCtx()));
		parameterPanel = new ProcessPanel(this, windowNo, pi, ProcessPanel.COLUMNS_1);
		//	Set Default
		parameterPanel.createFieldsAndEditors();
		getContentPane().add(parameterPanel.getPanel());
		setTitle(parameterPanel.getName());
		//	Revalidate
		validateScreen();
		return true;
	}	//	init
	
	@Override
	public void printScreen() {
		PrintScreenPainter.printScreen(this);
	}

	@Override
	public void validateScreen() {
		validate();
		getRootPane().setDefaultButton(parameterPanel.getDefaultButton());
	}

	@Override
	public void showCenterScreen() {
		AEnv.showCenterScreen(this);
	}

	@Override
	public void lockUI(ProcessInfo pi) {
		parameterPanel.lockUI(pi);
	}

	@Override
	public void unlockUI(ProcessInfo pi) {
		parameterPanel.unlockUI(pi);
	}

	@Override
	public boolean isUILocked() {
		return parameterPanel.isUILocked();
	}

	@Override
	public void executeASync(ProcessInfo pi) {
		parameterPanel.executeASync(pi);
	}

	@Override
	public Object getParentContainer() {
		return this;
	}

	@Override
	public boolean isEmbedded() {
		return false;
	}

	@Override
	public ASyncProcess getParentProcess() {
		return this;
	}

}	//	ProcessDialog
