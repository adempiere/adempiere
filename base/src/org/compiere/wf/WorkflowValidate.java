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

import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.Msg;


/**
 *	Validate Workflow Process
 *	
 *  @author Jorg Janke
 *  @version $Id: WorkflowValidate.java,v 1.2 2006/07/30 00:51:05 jjanke Exp $
 */
public class WorkflowValidate extends SvrProcess
{
	private int		p_AD_Worlflow_ID = 0;

	/**
	 * 	Prepare
	 */
	protected void prepare ()
	{
		p_AD_Worlflow_ID = getRecord_ID();
	}	//	prepare

	/**
	 * 	Process
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt () throws Exception
	{
		MWorkflow wf = MWorkflow.get (getCtx(), p_AD_Worlflow_ID);
		log.info("WF=" + wf);
		
		String msg = wf.validate();
		wf.save();
		if (msg.length() > 0)
			throw new AdempiereUserError(Msg.getMsg(getCtx(), "WorflowNotValid") 
				+ " - " + msg);
		return wf.isValid() ? "@OK@" : "@Error@";
	}	//	doIt
	
	
}	//	WorkflowValidate
