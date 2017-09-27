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
package org.compiere.wf;

import java.util.logging.Level;

import org.compiere.model.MUser;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.StateEngine;
import org.compiere.process.SvrProcess;

/**
 *	Manage Workflow Process
 *	
 *  @author Jorg Janke
 *  @version $Id: WFProcessManage.java,v 1.2 2006/07/30 00:51:05 jjanke Exp $
 */
public class WFProcessManage extends SvrProcess
{
	/**	Abort It				*/	
	private boolean		p_IsAbort = false;
	/** New User				*/
	private int			p_AD_User_ID = 0;
	/** New Responsible			*/
	private int			p_AD_WF_Responsible_ID = 0;
	/** Record					*/
	private int			p_AD_WF_Process_ID = 0;
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("IsAbort"))
				p_IsAbort = "Y".equals(para[i].getParameter());
			else if (name.equals("AD_User_ID"))
				p_AD_User_ID = para[i].getParameterAsInt();
			else if (name.equals("AD_WF_Responsible_ID"))
				p_AD_WF_Responsible_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		p_AD_WF_Process_ID = getRecord_ID();
	}	//	prepare

	/**
	 *  Perform process.
	 *  @return Message (variables are parsed)
	 *  @throws Exception if not successful
	 */
	protected String doIt() throws Exception
	{
		MWFProcess process = new MWFProcess (getCtx(), p_AD_WF_Process_ID, get_TrxName());
		log.info("doIt - " + process);
		
		MUser user = MUser.get(getCtx(), getAD_User_ID());
		//	Abort
		if (p_IsAbort)
		{
			String msg = user.getName() + ": Abort";
			process.setTextMsg(msg);
			process.setAD_User_ID(getAD_User_ID());
			process.setWFState(StateEngine.STATE_Aborted);
			return msg;
		}
		String msg = null;
		//	Change User
		if (p_AD_User_ID != 0 && process.getAD_User_ID() != p_AD_User_ID)
		{
			MUser from = MUser.get(getCtx(), process.getAD_User_ID());
			MUser to = MUser.get(getCtx(), p_AD_User_ID);
			msg = user.getName() + ": " + from.getName() + " -> " + to.getName();
			process.setTextMsg(msg);
			process.setAD_User_ID(p_AD_User_ID);
		}
		//	Change Responsible
		if (p_AD_WF_Responsible_ID != 0 && process.getAD_WF_Responsible_ID() != p_AD_WF_Responsible_ID)
		{
			MWFResponsible from = MWFResponsible.get(getCtx(), process.getAD_WF_Responsible_ID());
			MWFResponsible to = MWFResponsible.get(getCtx(), p_AD_WF_Responsible_ID);
			String msg1 = user.getName() + ": " + from.getName() + " -> " + to.getName();
			process.setTextMsg(msg1);
			process.setAD_WF_Responsible_ID(p_AD_WF_Responsible_ID);
			if (msg == null)
				msg = msg1;
			else
				msg += " - " + msg1;
		}
		//
		process.saveEx();
		
		return "OK";
	}	//	doIt
	
}	//	WFProcessManage
