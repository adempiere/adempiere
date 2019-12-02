package org.adempiere.webui.apps;

import java.util.logging.Level;

import org.adempiere.webui.component.Window;
import org.adempiere.webui.desktop.IDesktop;
import org.adempiere.webui.process.WProcessInfo;
import org.adempiere.webui.session.SessionManager;
import org.compiere.util.ASyncProcess;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

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



/**
 *	Dialog to Start process or report.
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
 *		<a href="https://github.com/adempiere/adempiere/issues/571">
 * 		@see FR [ 571 ] Process Dialog is not MVC</a>
 */
public class ProcessDialog extends Window implements IZKProcessDialog {
	/**
	 * generate serial version ID
	 */
	private static final long serialVersionUID = 5545731871518761455L;	

    /**
     * Dialog to start a process/report
     * @param processId
     * @param isSOTrx
     */
	public ProcessDialog (int processId, boolean isSOTrx) {
		super();
		log.info("Process=" + processId);
		windowNo = SessionManager.getAppDesktop().registerWindow(this);
		setAttribute(IDesktop.WINDOWNO_ATTRIBUTE, windowNo);
		processInfo = new WProcessInfo(null, processId);
		Env.setContext(Env.getCtx(), windowNo, "IsSOTrx", isSOTrx ? "Y" : "N");
		try {
			setStyle("position: absolute; width: 100%; height: 100%");
			init();
		} catch(Exception ex) {
			log.log(Level.SEVERE, "", ex);
		}
	}	//	ProcessDialog
	
	private ASyncProcess 	aSyncProcess;
	private int 			windowNo;
	private boolean 		valid = true;
	private WProcessInfo 	processInfo;
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(ProcessDialog.class);
	//
	private ProcessPanel processPanel = null;
	
	@Override
	public void dispose() {
		SessionManager.getAppDesktop().closeWindow(windowNo);
		valid = false;
	}//	dispose

	/**
	 *	Dynamic Init
	 *  @return true, if there is something to process (start from menu)
	 */
	public boolean init() {
		log.config("");
		processInfo.setAD_User_ID (Env.getAD_User_ID(Env.getCtx()));
		processInfo.setAD_Client_ID(Env.getAD_Client_ID(Env.getCtx()));
		processInfo.setInterfaceType(WProcessInfo.INTERFACE_TYPE_ZK);
		processPanel = new ProcessPanel(this, windowNo, processInfo, "70%");
		//	BR[ 265 ]
		processPanel.init();
		appendChild(processPanel.getPanel());
		setTitle(processPanel.getName());
		valid = true;
		return valid;
	}	//	init

	/**
	 * Is Valid Dialog
	 * @return
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * Is UI Locked
	 * @return
	 */
	public boolean isUILocked() {
		return processPanel.isUILocked();
	}

	@Override
	public void printScreen() {
		
	}

	@Override
	public void validateScreen() {
		super.invalidate();
	}

	@Override
	public void showCenterScreen() {
		
	}

	@Override
	public boolean isEmbedded() {
		return false;
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
	public void runProcess() {
		processPanel.runProcess();
	}
	
	/**
	 * Run it after init
	 */
	public void afterInit() {
		processPanel.afterInit();
	}
}	//	ProcessDialog
