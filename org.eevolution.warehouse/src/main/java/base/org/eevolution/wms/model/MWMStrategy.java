/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          * 
 * http://www.adempiere.org                                           * 
 *                                                                    * 
 * Copyright (C) Victor Perez	                                      * 
 * Copyright (C) Contributors                                         * 
 *                                                                    * 
 * This program is free software; you can redistribute it and/or      * 
 * modify it under the terms of the GNU General Public License        * 
 * as published by the Free Software Foundation; either version 2     * 
 * of the License, or (at your option) any later version.             * 
 *                                                                    * 
 * This program is distributed in the hope that it will be useful,    * 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of     * 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       * 
 * GNU General Public License for more details.                       * 
 *                                                                    * 
 * You should have received a copy of the GNU General Public License  * 
 * along with this program; if not, write to the Free Software        * 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         * 
 * MA 02110-1301, USA.                                                * 
 *                                                                    * 
 * Contributors:                                                      * 
 *  - Victor Perez (victor.perez@e-evolution.com	 )                *
 *                                                                    *
 * Sponsors:                                                          *
 *  - e-Evolution (http://www.e-evolution.com/)                       *
 **********************************************************************/
package org.eevolution.wms.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;
import org.compiere.util.CLogger;

/**
 * Class Model Warehouse Strategy
 * @author victor.perez@e-evoluton.com, e-Evolution
 *
 */
public class MWMStrategy extends X_WM_Strategy
{

	/**
	 * get Strategy based on bound type (In/Out)
	 * @param ctx
	 * @param boundType
	 * @param trxName
     * @return
     */
	public static List<MWMStrategy> getByBoundType(Properties ctx , String boundType , String trxName)
	{
		final String whereClause = MWMStrategy.COLUMNNAME_InOutBoundType + "=" + boundType;
		return new Query(ctx, MWMStrategy.Table_Name, whereClause, trxName)
		.setClient_ID().setOnlyActiveRecords(true)
		.list();
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9114227725703466412L;
	/**	Logger	**/
	private static CLogger	s_log = CLogger.getCLogger (MWMStrategy.class);
	
	/**************************************************************************
	 * 	Warehouse Strategy
	 *	@param ctx context
	 *	@param WM_Strategy_ID
	 *	@param trxName transaction name 
	 */
	public MWMStrategy (Properties ctx, int WM_Strategy_ID, String trxName)
	{
		super (ctx, WM_Strategy_ID, trxName);
		if (WM_Strategy_ID == 0)
		{
		}
	}

	/**
	 * 	Warehouse Strategy
	 *	@param ctx context
	 *	@param WM_Strategy_ID Warehouse Strategy ID
	 */
	public MWMStrategy (Properties ctx, int WM_Strategy_ID)
	{
		this (ctx, WM_Strategy_ID, null);
	}

	
	
	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 *	@param trxName transaction
	 */
	public MWMStrategy (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MAsset


	/**
	 * Get Strategy Detail
	 * @return
     */
	public List <MWMStrategyDetail> getStrategyDetail()
	{
		final String whereClause = MWMStrategyDetail.COLUMNNAME_WM_Strategy_ID + "=?";
		return new Query(getCtx() , MWMStrategyDetail.Table_Name , whereClause , get_TrxName())
		.setClient_ID()
		.setOnlyActiveRecords(true)
		.setParameters(new Object[]{getWM_Strategy_ID()})
		.setOrderBy(MWMStrategyDetail.COLUMNNAME_SeqNo)
		.list();
	}

	
	/**
	 * 	String representation
	 *	@return info
	 */
	@Override
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MWMStrategy[")
			.append (get_ID ())
			.append ("-")
			.append (getName())
			.append ("]");
		return sb.toString ();
	}	//	toString
}	
