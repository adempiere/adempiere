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

import org.adempiere.engine.CostEngineFactory;
import org.compiere.model.MLandedCost;
import org.compiere.model.MLandedCostAllocation;
import org.compiere.util.AdempiereUserError;

/**
 * 	Distribute Landed Costs
 *	
 *  @author Jorg Janke
 *  @version $Id: LandedCostDistribute.java,v 1.2 2006/07/30 00:51:02 jjanke Exp $
 */
public class LandedCostDistribute extends SvrProcess
{
	/** Parameter			*/
	private int			p_C_LandedCost_ID = 0;
	/** LC					*/
	private MLandedCost	m_lc = null;
	
	/**
	 * 	Prepare
	 */
	protected void prepare ()
	{
		p_C_LandedCost_ID = getRecord_ID();
	}	//	prepare

	/**
	 * 	Process
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt () throws Exception
	{
		m_lc = new MLandedCost (getCtx(), p_C_LandedCost_ID, get_TrxName());
		log.info(m_lc.toString());
		if (m_lc.get_ID() == 0)
			throw new AdempiereUserError("@NotFound@: @C_LandedCost_ID@ - " + p_C_LandedCost_ID);

		String error = m_lc.allocateCosts();
		
		//generateCostDetail();
		
		if (error == null || error.length() == 0)
			return "@OK@";
		return error;
	}	//	doIt	
	
	private void generateCostDetail()
	{
		for (MLandedCostAllocation allocation : MLandedCostAllocation.getOfInvoiceLine(getCtx(), m_lc.getC_InvoiceLine_ID(), get_TrxName()))
		{
			CostEngineFactory.getCostEngine(getAD_Client_ID()).createCostDetailForLandedCostAllocation(allocation);
		}
	}
}	//	LandedCostDistribute
