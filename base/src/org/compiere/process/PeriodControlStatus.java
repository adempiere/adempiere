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
package org.compiere.process;

import java.util.logging.Level;

import org.compiere.model.MPeriodControl;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.CacheMgt;


/**
 *	Open/Close Period Control
 *	
 *  @author Jorg Janke
 *  @version $Id: PeriodControlStatus.java,v 1.2 2006/07/30 00:51:02 jjanke Exp $
 */
public class PeriodControlStatus extends SvrProcess
{
	/** Period Control				*/
	private int 		p_C_PeriodControl_ID = 0;
	
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
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		p_C_PeriodControl_ID = getRecord_ID();
	}	//	prepare

	/**
	 * 	Process
	 *	@return message
	 *	@throws Exception
	 */
	protected String doIt() throws Exception
	{
		log.info ("C_PeriodControl_ID=" + p_C_PeriodControl_ID);
		MPeriodControl pc = new MPeriodControl (getCtx(), p_C_PeriodControl_ID, get_TrxName());
		if (pc.get_ID() == 0)
			throw new AdempiereUserError("@NotFound@  @C_PeriodControl_ID@=" + p_C_PeriodControl_ID);
		//	Permanently closed
		if (MPeriodControl.PERIODACTION_PermanentlyClosePeriod.equals(pc.getPeriodStatus()))
			throw new AdempiereUserError("@PeriodStatus@ = " + pc.getPeriodStatus());
		//	No Action
		if (MPeriodControl.PERIODACTION_NoAction.equals(pc.getPeriodAction()))
			return "@OK@";
	
		//	Open
		if (MPeriodControl.PERIODACTION_OpenPeriod.equals(pc.getPeriodAction()))
			pc.setPeriodStatus(MPeriodControl.PERIODSTATUS_Open);
		//	Close
		if (MPeriodControl.PERIODACTION_ClosePeriod.equals(pc.getPeriodAction()))
			pc.setPeriodStatus(MPeriodControl.PERIODSTATUS_Closed);
		//	Close Permanently
		if (MPeriodControl.PERIODACTION_PermanentlyClosePeriod.equals(pc.getPeriodAction()))
			pc.setPeriodStatus(MPeriodControl.PERIODSTATUS_PermanentlyClosed);
		pc.setPeriodAction(MPeriodControl.PERIODACTION_NoAction);
		//
		boolean ok = pc.save();
		
		//	Reset Cache
		CacheMgt.get().reset("C_PeriodControl", 0);
		CacheMgt.get().reset("C_Period", pc.getC_Period_ID());

		if (!ok)
			return "@Error@";
		return "@OK@";
	}	//	doIt

}	//	PeriodControlStatus
