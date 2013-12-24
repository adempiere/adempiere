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
package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

/**
 *  Productionplan Model
 *
 *	@author Susanne Calderón
 * */
public class MProductionPlan extends X_M_ProductionPlan
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -364132249042527640L;

	private MProductionLine[]	m_lines = null;
	
	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MProductionPlan (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MProductionPlan
	
	public MProductionLine[] getLines()
	{
		return getLines(false, "");
	}	//	getLines
	
	public MProductionLine[] getLines (boolean requery, String OrderBy)
	{
		if (m_lines != null && !requery) {
			set_TrxName(m_lines, get_TrxName());
			return m_lines;
		}
		List<MProductionLine> list = new Query(getCtx(), I_M_ProductionLine.Table_Name, " M_ProductionPlan_ID=?", get_TrxName())
		.setParameters(getM_ProductionPlan_ID())
		.setOrderBy(OrderBy)
		.list();
		//
		m_lines = new MProductionLine[list.size()];
		list.toArray(m_lines);
		return m_lines;
	}	//	getMInOutLines
}