/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
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
 * Copyright (C) 2003-2008 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *                 Teo Sarca, SC ARHIPAC SERVICE SRL                          *
 *****************************************************************************/
package org.eevolution.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MWarehouse;
import org.compiere.model.Query;
import org.compiere.util.CCache;

/**
 * Network Distribution
 * @author Victor Perez, e-Evolution,SC
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 */
public class MDDNetworkDistribution extends X_DD_NetworkDistribution
{
	private static final long serialVersionUID = 1L;
	private static CCache<Integer,MDDNetworkDistribution> 	s_cache = new CCache<Integer,MDDNetworkDistribution>(MDDNetworkDistribution.Table_Name, 50);

	/** Standard Constructor */
	public MDDNetworkDistribution (Properties ctx, int DD_NetworkDistribution_ID, String trxName)
	{
		super (ctx, DD_NetworkDistribution_ID, trxName);
		if (DD_NetworkDistribution_ID == 0)
		{
		}
	}

	/** Load Constructor */
	public MDDNetworkDistribution (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}
	
	/** Network Lines */
	private MDDNetworkDistributionLine[] m_lines = null;
	
	/**
	 * 	Get from Cache
	 *	@param ctx context
	 *	@param M_Warehouse_ID id
	 *	@return warehouse
	 */
	public static MDDNetworkDistribution get (Properties ctx, int DD_NetworkDistribution_ID)
	{
		Integer key = new Integer(DD_NetworkDistribution_ID);
		MDDNetworkDistribution retValue = (MDDNetworkDistribution)s_cache.get(DD_NetworkDistribution_ID);
		if (retValue != null)
			return retValue;
		//
		retValue = new MDDNetworkDistribution (ctx,DD_NetworkDistribution_ID, null);
		retValue.getLines();
		s_cache.put (key, retValue);
		return retValue;
	}	//	get

	/**
	 * 	Get Lines
	 *	@return array of lines MDDNetworkDistributionLine
	 */
	public MDDNetworkDistributionLine[] getLines()
	{
		if (m_lines != null)
			return m_lines;

		List<MDDNetworkDistributionLine>
		list = new Query(getCtx(), MDDNetworkDistributionLine.Table_Name, "DD_NetworkDistribution_ID=?", get_TrxName())
					.setParameters(new Object[]{get_ID()})
					.setOrderBy("PriorityNo, M_Shipper_ID")
					.list();
		m_lines = list.toArray (new MDDNetworkDistributionLine[list.size()]);
		return m_lines;
	}	//	getLines


	/**
	 * 	Get Lines
	 *  @param M_Warehouse_ID ID Warehouse
	 *	@return array of lines MDDNetworkDistributionLine
	 */
	public MDDNetworkDistributionLine[] getLines(int M_Warehouse_ID)
	{
		List<MDDNetworkDistributionLine> list = new ArrayList<MDDNetworkDistributionLine>();
		for (MDDNetworkDistributionLine line : getLines())
		{
			if (line.getM_Warehouse_ID() == M_Warehouse_ID)
				list.add(line);
		}
		return list.toArray(new MDDNetworkDistributionLine[list.size()]);
	}	//	getLines
}