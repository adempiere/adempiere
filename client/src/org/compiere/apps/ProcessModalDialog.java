/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2007 Low Heng Sin											  *
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
 *****************************************************************************/
package org.compiere.apps;

import java.awt.Frame;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.process.ProcessInfo;
import org.compiere.swing.CDialog;
import org.compiere.util.ASyncProcess;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 * [ 1639242 ] Inconsistent appearance of Process/Report Dialog
 * 
 *	Modal Dialog to Start process.
 *	Displays information about the process
 *		and lets the user decide to start it
 *  	and displays results (optionally print them).
 *  Calls ProcessCtl to execute.
 *  @author 	Low Heng Sin
 *  @author     arboleda - globalqss
 *  - Implement ShowHelp option on processes and reports
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li>FR [ 265 ] ProcessParameterPanel is not MVC
 *		@see https://github.com/adempiere/adempiere/issues/265
 *		<li>BR [ 300 ] ZK Process action buttons don't have standard size and position
 *		@see https://github.com/adempiere/adempiere/issues/300
 *		<li>BR[ 323 ] Process parameter panel is showed without parameter
 *		@see https://github.com/adempiere/adempiere/issues/323
 *		<a href="https://github.com/adempiere/adempiere/issues/571">
 * 		@see FR [ 571 ] Process Dialog is not MVC</a>
 *	@author Michael McKay, michael.mckay@mckayerp.com, 
 *	 <li>Bug [ <a href="https://github.com/adempiere/adempiere/issues/1926">#1926</a> ] ZK Exports migration XML files to 
 *       different location than what is selected in the dialogs.
 */
public class ProcessModalDialog extends CDialog implements IProcessDialog, ASyncProcess {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6613814452809135635L;

	/**
	 * Dialog to start a process/report
	 * @param ctx
	 * @param parent
	 * @param title
	 * @param aProcess
	 * @param WindowNo
	 * @param AD_Process_ID
	 * @param tableId
	 * @param recordId
	 * @param autoStart
	 */
	public ProcessModalDialog (Properties ctx, Frame parent, String title, 
			ASyncProcess aProcess, int WindowNo, int AD_Process_ID,
			int tableId, int recordId, boolean autoStart) {
		this(ctx, parent, title, aProcess, WindowNo, 
				AD_Process_ID, tableId, recordId, autoStart, null, false);
	}
	
	
	/**
	 * Private Constructor
	 * @param ctx
	 * @param parent
	 * @param title
	 * @param aProcess
	 * @param WindowNo
	 * @param processId
	 * @param tableId
	 * @param recordId
	 * @param autoStart
	 * @param pi
	 * @param isOnlyPanel
	 */
	private ProcessModalDialog (Properties ctx, Frame parent, String title, 
			ASyncProcess aProcess, int WindowNo, int processId,
			int tableId, int recordId, boolean autoStart, ProcessInfo pi, boolean isOnlyPanel) {
		super(parent, title, true);
		log.info("Process=" + processId);
		this.isOnlyPanel = isOnlyPanel;
		this.autoStart = autoStart;
		if(pi == null) {
			aSyncProcess = aProcess;
			processInfo = new ProcessInfo(title, processId, tableId, recordId);
		} else {
			processInfo = pi;
		}
		
		//	
		windowNo = WindowNo;
		//	
		try {
			jbInit();
			init();
		} catch(Exception ex) {
			log.log(Level.SEVERE, "", ex);
		}
	}	//	ProcessDialog
	
	/**
	 * Optional constructor, for launch from ProcessCtl
	 * @param frame
	 * @param WindowNo
	 * @param pi
	 */
	public ProcessModalDialog (Frame frame, int WindowNo, ProcessInfo pi) {
		this(Env.getCtx(), frame, pi.getTitle(), 
				null, WindowNo, pi.getAD_Process_ID(), 
				pi.getTable_ID(), pi.getRecord_ID(), false, pi, true);
	}

	private ASyncProcess 	aSyncProcess;
	private int 			windowNo;
	private boolean 		isValid = true;
	private boolean 		isOnlyPanel;
	private boolean 		autoStart;
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(ProcessDialog.class);
	//
	private ProcessPanel processPanel = null;
	private ProcessInfo processInfo;

	/**
	 *	Static Layout
	 *  @throws Exception
	 */
	private void jbInit() throws Exception {
		setIconImage(Env.getImage("mProcess.gif"));
	}	//	jbInit

	/**
	 *	Dispose
	 */
	public void dispose() {
		isValid = false;
		processPanel.restoreContext(); // teo_sarca [ 1699826 ]
		super.dispose();
	}	//	dispose

	/**
	 * Is Valid the dialog
	 * @return
	 */
	public boolean isValidDialog() {
		return isValid;
	}

	/**
	 *	Dynamic Init
	 *  @return true, if there is something to process (start from menu)
	 */
	private boolean init() {
		log.config("");
		processInfo.setAD_User_ID (Env.getAD_User_ID(Env.getCtx()));
		processInfo.setAD_Client_ID(Env.getAD_Client_ID(Env.getCtx()));
		
		// #1926 ZK Exports migration XML files to different location 
		// than what is selected in the dialogs. Fix is to let the process
		// know what interface is being used so it can manage the export 
		// process correctly.
		processInfo.setInterfaceType(ProcessInfo.INTERFACE_TYPE_SWING);
		
		processPanel = new ProcessPanel(this, windowNo, processInfo, ProcessPanel.COLUMNS_1);
		processPanel.setAutoStart(autoStart);
		processPanel.setIsOnlyPanel(isOnlyPanel);
		processPanel.createFieldsAndEditors();
		//	Set Default
		getContentPane().add(processPanel.getPanel());
		setTitle(processPanel.getName());
		//	Revalidate
		validateScreen();
		return true;
	}	//	init
	
	/**
	 *	Is everything OK?
	 *  @return true if parameters saved correctly
	 */
	public boolean isOK() {
		return processPanel.isOkPressed();
	}	//	isOK


	@Override
	public void printScreen() {
		PrintScreenPainter.printScreen(this);
	}

	@Override
	public void validateScreen() {
		validate();
		getRootPane().setDefaultButton(processPanel.getDefaultButton());
	}

	@Override
	public void showCenterScreen() {
		AEnv.showCenterScreen(this);
	}
	
	@Override
	public ASyncProcess getParentProcess() {
		return this;
	}


	@Override
	public boolean isEmbedded() {
		return true;
	}


	@Override
	public Object getParentContainer() {
		return this;
	}
	
	/**
	 * Return true when is auto start process
	 * @return
	 */
	public boolean isAutoStart() {
		return processPanel.isAutoStart();
	}
	
	@Override
	public void lockUI(ProcessInfo pi) {
		if(aSyncProcess != null) {
			aSyncProcess.lockUI(pi);
		}
	}


	@Override
	public void unlockUI(ProcessInfo pi) {
		if(aSyncProcess != null) {
			aSyncProcess.unlockUI(pi);
		}
		//	
		processPanel.openResult();
	}


	@Override
	public boolean isUILocked() {
		if(aSyncProcess != null) {
			return aSyncProcess.isUILocked();
		}
		return false;
	}


	@Override
	public void executeASync(ProcessInfo pi) {
		if(aSyncProcess != null) {
			aSyncProcess.executeASync(pi);
		}
	}
}	//	ProcessDialog
