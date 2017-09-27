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
package org.compiere.sla;

import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MSLACriteria;
import org.compiere.model.MSLAGoal;
import org.compiere.model.MSLAMeasure;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;


/**
 *	Service Level Agreement Measure.
 *	Calculate/update the actual measure.
 *	
 *  @author Jorg Janke
 *  @version $Id: SLAMeasureProcess.java,v 1.2 2006/07/30 00:51:06 jjanke Exp $
 */
public class SLAMeasureProcess extends SvrProcess
{
	/** Goal					*/
	private int			p_PA_SLA_Measure_ID;

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
				log.log(Level.SEVERE, "prepare - Unknown Parameter: " + name);
		}
		p_PA_SLA_Measure_ID = getRecord_ID();
	}	//	prepare

	/**
	 * 	Process
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt () throws Exception
	{
		log.info ("PA_SLA_Measure_ID=" + p_PA_SLA_Measure_ID);
		MSLAMeasure measure = new MSLAMeasure (getCtx(), p_PA_SLA_Measure_ID, get_TrxName());
		if (measure.get_ID() == 0)
			throw new AdempiereUserError("@PA_SLA_Measure_ID@ " + p_PA_SLA_Measure_ID);
		
		MSLAGoal goal = new MSLAGoal(getCtx(), measure.getPA_SLA_Goal_ID(), get_TrxName());
		if (goal.get_ID() == 0)
			throw new AdempiereUserError("@PA_SLA_Goal_ID@ " + measure.getPA_SLA_Goal_ID());

		MSLACriteria criteria = MSLACriteria.get(getCtx(), goal.getPA_SLA_Criteria_ID(), get_TrxName());
		if (criteria.get_ID() == 0)
			throw new AdempiereUserError("@PA_SLA_Criteria_ID@ " + goal.getPA_SLA_Criteria_ID());
		
		SLACriteria pgm = criteria.newInstance();
		//
		goal.setMeasureActual(pgm.calculateMeasure(goal));
		goal.setDateLastRun(new Timestamp(System.currentTimeMillis()));
		goal.saveEx();
		//
		return "@MeasureActual@=" + goal.getMeasureActual();
	}	//	doIt

}	//	SLAMeasureProcess
