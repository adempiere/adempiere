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
package org.adempiere.webui.apps;

import org.adempiere.webui.component.Window;
import org.compiere.apps.ProcessController;
import org.compiere.apps.ProcessCtl;
import org.compiere.model.MPInstance;
import org.compiere.process.ProcessInfo;
import org.compiere.util.ASyncProcess;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;

/**
 * Ported from org.compiere.apps.ProcessCtl
 * @author hengsin
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/571">
 * 		@see FR [ 571 ] Process Dialog is not MVC</a>
 *
 */
public class WProcessCtl {
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(WProcessCtl.class);
	
	/**
	 *	Process Control
	 *  <code>
	 *	- Get Instance ID
	 *	- Get Parameters
	 *	- execute (lock - start process - unlock)
	 *  </code>
	 *  Creates a ProcessCtl instance, which calls
	 *  lockUI and unlockUI if parent is a ASyncProcess
	 *  <br>
	 *
	 *  @param asyncProcess ASyncProcess & Container
	 *  @param windowNo window no
	 *  @param pi ProcessInfo process info
	 *  @param trx Transaction
	 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 *		<li>FR [ 265 ] ProcessParameterPanel is not MVC
	 *		@see https://github.com/adempiere/adempiere/issues/265
	 */
	public static void process (ASyncProcess asyncProcess, int windowNo, ProcessInfo pi, Trx trx)
	{
		log.fine("WindowNo=" + windowNo + " - " + pi);

		MPInstance instance = null; 
		try 
		{ 
			instance = new MPInstance(Env.getCtx(), pi.getAD_Process_ID(), pi.getRecord_ID()); 
		} 
		catch (Exception e) 
		{ 
			pi.setSummary (e.getLocalizedMessage()); 
			pi.setError (true); 
			log.warning(pi.toString()); 
		} 
		catch (Error e) 
		{ 
			pi.setSummary (e.getLocalizedMessage()); 
			pi.setError (true); 
			log.warning(pi.toString()); 
		}
		//	Valid null
		if(instance == null)
			return;
		//	
		if (!instance.save())
		{
			pi.setSummary (Msg.getMsg(Env.getCtx(), "ProcessNoInstance"));
			pi.setError (true);
		}
		pi.setAD_PInstance_ID (instance.getAD_PInstance_ID());

		//	Get Parameters (Dialog)
		ProcessModalDialog processModalDialog = new ProcessModalDialog(asyncProcess, windowNo, pi);
		if (processModalDialog.isValidDialog())
		{
			processModalDialog.setWidth("500px");
			processModalDialog.setVisible(true);
			processModalDialog.setPosition("center");
			processModalDialog.setAttribute(Window.MODE_KEY, Window.MODE_MODAL);
			AEnv.showWindow(processModalDialog);
			if (!processModalDialog.isOK()) {
				return;
			}
		}
		processModalDialog.runProcess();
		return;

	}	//	execute
	
	/**
	 *	Async Process - Do it all.
	 *  <code>
	 *	- Get Instance ID
	 *	- Get Parameters
	 *	- execute (lock - start process - unlock)
	 *  </code>
	 *  Creates a ProcessCtl instance, which calls
	 *  lockUI and unlockUI if parent is a ASyncProcess
	 *  <br>
	 *	Called from ProcessDialog.actionPerformed
	 *
	 *  @param parent ASyncProcess & Container
	 *  @param WindowNo window no
	 *  @param paraPanel Process Parameter Panel
	 *  @param pi ProcessInfo process info
	 *  @param trx Transaction
	 */
	public static void process(ASyncProcess parent, int WindowNo, ProcessController parameter, ProcessInfo pi, Trx trx) {
		ProcessCtl.process(parent, WindowNo, parameter, pi, trx);
	}
}
