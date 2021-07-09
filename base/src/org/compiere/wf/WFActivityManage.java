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
 *	Manage Workflow Activity
 *	
 *  @author Jorg Janke
 * 	@author Victor Pérez, E Evolution Consulting,  wwww.e-evolution.com
 * 				<li>[Bug Report] The workflow engine is not correctly handling transactions when processing documents #3170
 * 				<a href="https://github.com/adempiere/adempiere/issues/3170">
 */
public class WFActivityManage extends WFActivityManageAbstract
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
		MWFActivity activity = new MWFActivity (getCtx(), getRecord_ID(), get_TrxName());
		log.info("doIt - " + activity);
		
		MUser user = MUser.get(getCtx(), getAD_User_ID());
		//	Abort
		if (isAbort())
		{
			String activityMessage = user.getName() + ": Abort";
			activity.setTextMsg(activityMessage);
			activity.setAD_User_ID(getAD_User_ID());
			// 2007-06-14, matthiasO.
			// Set the 'processed'-flag when an activity is aborted; not setting this flag
			// will leave the activity in an "unmanagable" state
			activity.setProcessed(true);
			activity.setWFState(StateEngine.STATE_Aborted);
			activity.saveEx();
			MWFProcess process =  new MWFProcess( getCtx() , activity.getAD_WF_Process_ID() , get_TrxName());
			if (process.isProcessed())
				process.unlockDocument();

			return activityMessage;
		}
		String userMessage = null;
		//	Change User
		if (getUserId() != 0 && activity.getAD_User_ID() != getUserId())
		{
			MUser userFrom = MUser.get(getCtx(), activity.getAD_User_ID());
			MUser userTo = MUser.get(getCtx(), getUserId());
			userMessage = user.getName() + ": " + userFrom.getName() + " -> " + userTo.getName();
			activity.setTextMsg(userMessage);
			activity.setAD_User_ID(getUserId());
		}
		//	Change Responsible
		if (getWFResponsibleId() != 0 && activity.getAD_WF_Responsible_ID() != getWFResponsibleId())
		{
			MWFResponsible responsibleFrom = MWFResponsible.get(getCtx(), activity.getAD_WF_Responsible_ID());
			MWFResponsible responsibleTo = MWFResponsible.get(getCtx(), getWFResponsibleId());
			String responsibleMessage = user.getName() + ": " + responsibleFrom.getName() + " -> " + responsibleTo.getName();
			activity.setTextMsg(responsibleMessage);
			activity.setAD_WF_Responsible_ID(getWFResponsibleId());
			if (userMessage == null)
				userMessage = responsibleMessage;
			else
				userMessage += " - " + responsibleMessage;
		}
		//
		activity.saveEx();
		
		return userMessage;
	}	//	doIt
	
}	//	WFActivityManage
