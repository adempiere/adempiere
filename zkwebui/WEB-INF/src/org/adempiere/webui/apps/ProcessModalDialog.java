/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2007 Adempiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *
 * Copyright (C) 2007 Low Heng Sin hengsin@avantz.com
 * _____________________________________________
 *****************************************************************************/
package org.adempiere.webui.apps;

import java.util.logging.Level;

import org.adempiere.webui.component.Window;
import org.compiere.process.ProcessInfo;
import org.compiere.util.ASyncProcess;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
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
 *		<a href="https://github.com/adempiere/adempiere/issues/571">
 * 		@see FR [ 571 ] Process Dialog is not MVC</a>
 */
public class ProcessModalDialog extends Window implements IZKProcessDialog {
	/**
	 * generated serial version ID
	 */
	private static final long serialVersionUID = -7109707014309321369L;

	/**
	 * @param aProcess
	 * @param WindowNo
	 * @param pi
	 * @param autoStart
	 */
	public ProcessModalDialog(ASyncProcess aProcess, int WindowNo, ProcessInfo pi, boolean autoStart, boolean onlyPanel) {
		super();
		aSyncProcess = aProcess;
		windowNo = WindowNo;
		processInfo = pi;
		this.autoStart = autoStart;
		this.onlyPanel = onlyPanel;
		log.info("Process=" + pi.getAD_Process_ID());		
		try {
			init();
		} catch(Exception ex) {
			log.log(Level.SEVERE, "", ex);
		}
	}
	
	/**
	 * Dialog to start a process/report
	 * @param ctx
	 * @param aProcess
	 * @param WindowNo
	 * @param processId
	 * @param tableId
	 * @param recordId
	 * @param autoStart
	 */
	public ProcessModalDialog (ASyncProcess aProcess, int WindowNo, int processId, int tableId, int recordId, boolean autoStart) {						
		this(aProcess, WindowNo, new ProcessInfo("", processId, tableId, recordId), autoStart, false);		
	}
	
	/**
	 * Optional constructor, for launch from ProcessCtl
	 * @param frame
	 * @param WindowNo
	 * @param pi
	 */
	public ProcessModalDialog (ASyncProcess aProcess, int WindowNo, ProcessInfo pi) {
		//	Set Process instance and flag
		this(aProcess, WindowNo, pi, false, true);
	}

	private ASyncProcess 	aSyncProcess;
	private int 			windowNo;
	private boolean 		onlyPanel;
	private boolean 		autoStart;
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(ProcessModalDialog.class);
	//
	private ProcessPanel 	processPanel = null;
	/**	Process Information	*/
	private ProcessInfo 	processInfo = null;
	
	/**
	 * 	Set Visible 
	 * 	(set focus to OK if visible)
	 * 	@param visible true if visible
	 */
	public boolean setVisible (boolean visible) {
		return super.setVisible(visible);
	}	//	setVisible

	/**
	 *	Dispose
	 */
	public void dispose() {		
		processPanel.restoreContext();
		this.detach();
	}	//	dispose

	/**
	 * is dialog still valid
	 * @return boolean
	 */
	public boolean isValid() {
		if(autoStart) {
			setStyle("");
			getFirstChild().setVisible(false);
			setBorder("none");
			setTitle(null);
			setVisible(false);
			appendChild(new BusyDialog());
			processPanel.process();
		}
		//	
		return true;
	}

	/**
	 *	Dynamic Init
	 *  @return true, if there is something to process (start from menu)
	 */
	private boolean init() {
		setAttribute("modal", true);
		setBorder("normal");
		setSizable(true);
		setClosable(true);
		setMaximizable(true);
		setPosition("center");
		setStyle("overflow: auto");
		setWidth("70%");
        setHeight("60%");
		log.config("");
		//	Move from APanel.actionButton
		processInfo.setAD_User_ID (Env.getAD_User_ID(Env.getCtx()));
		processInfo.setAD_Client_ID(Env.getAD_Client_ID(Env.getCtx()));
		processPanel = new ProcessPanel(this, windowNo, processInfo, "100%");
		processPanel.setIsOnlyPanel(onlyPanel);
		processPanel.setAutoStart(autoStart);
		processPanel.init();
		appendChild(processPanel.getPanel());
		setTitle(processPanel.getName());
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
		
	}

	@Override
	public void validateScreen() {
		invalidate();
	}

	@Override
	public void showCenterScreen() {
		
	}

	@Override
	public Object getParentContainer() {
		return this;
	}

	@Override
	public ASyncProcess getParentProcess() {
		return aSyncProcess;
	}

	@Override
	public boolean isEmbedded() {
		return true;
	}

	@Override
	public void runProcess() {
		processPanel.runProcess();
	}
}	//	ProcessDialog
