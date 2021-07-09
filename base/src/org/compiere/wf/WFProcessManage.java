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

import org.compiere.model.MUser;
import org.compiere.process.StateEngine;

/**
 *	Manage Workflow Process
 *	
 *  @author Jorg Janke
 * 	@author Victor Pérez, E Evolution Consulting,  wwww.e-evolution.com
 * 				<li>[Bug Report] The workflow engine is not correctly handling transactions when processing documents #3170
 * 				<a href="https://github.com/adempiere/adempiere/issues/3170">
 */
public class WFProcessManage extends WFProcessManageAbstract
{
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		super.prepare();
	}	//	prepare

	/**
	 *  Perform process.
	 *  @return Message (variables are parsed)
	 *  @throws Exception if not successful
	 */
	protected String doIt() throws Exception
	{
		MWFProcess workflowProcess = new MWFProcess (getCtx(), getRecord_ID(), get_TrxName());
		log.info("doIt - " + workflowProcess);
		
		MUser user = MUser.get(getCtx(), getAD_User_ID());
		//	Abort
		if (isAbort())
		{
			String processMessage = user.getName() + ": Abort";
			workflowProcess.setTextMsg(processMessage);
			workflowProcess.setAD_User_ID(getAD_User_ID());
			workflowProcess.setWFState(StateEngine.STATE_Aborted);
			workflowProcess.saveEx();
			if (workflowProcess.isProcessed())
				workflowProcess.unlockDocument();

			return processMessage;
		}
		String userMessage = null;
		//	Change User
		if (getUserId() != 0 && workflowProcess.getAD_User_ID() != getUserId())
		{
			MUser userFrom = MUser.get(getCtx(), workflowProcess.getAD_User_ID());
			MUser userTo = MUser.get(getCtx(), getUserId());
			userMessage = user.getName() + ": " + userFrom.getName() + " -> " + userTo.getName();
			workflowProcess.setTextMsg(userMessage);
			workflowProcess.setAD_User_ID(getUserId());
		}
		//	Change Responsible
		if (getWFResponsibleId() != 0 && workflowProcess.getAD_WF_Responsible_ID() != getWFResponsibleId())
		{
			MWFResponsible responsibleFrom = MWFResponsible.get(getCtx(), workflowProcess.getAD_WF_Responsible_ID());
			MWFResponsible responsibleTo = MWFResponsible.get(getCtx(), getWFResponsibleId());
			String responsibleMessage = user.getName() + ": " + responsibleFrom.getName() + " -> " + responsibleTo.getName();
			workflowProcess.setTextMsg(responsibleMessage);
			workflowProcess.setAD_WF_Responsible_ID(getWFResponsibleId());
			if (userMessage == null)
				userMessage = responsibleMessage;
			else
				userMessage += " - " + responsibleMessage;
		}
		//
		workflowProcess.saveEx();
		
		return userMessage;
	}	//	doIt
	
}	//	WFProcessManage
