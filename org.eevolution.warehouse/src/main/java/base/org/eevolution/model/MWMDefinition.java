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
package org.eevolution.model;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.Properties;

import org.compiere.model.Query;
import org.compiere.util.CLogger;

/**
 * Class Model Warehouse Management Definition
 * @author victor.perez@e-evoluton.com, e-Evolution
 *
 */
public class MWMDefinition extends X_WM_Definition
{

	public static Collection<MWMDefinition> getAll(Properties ctx,String trxName)
	{
		if (s_Collection != null)
		{
			return s_Collection;
		}
		
		s_Collection = new Query(ctx,Table_Name, null ,trxName)
			.setOnlyActiveRecords(true)
			.setClient_ID()
			.setOrderBy(MWMDefinition.COLUMNNAME_SeqNo)
			.list();	
		return s_Collection;
	}
	
	public static Collection<MWMDefinition> getByOutboundType(Properties ctx,String trxName)
	{
		if(s_CollectionOudboudType != null)
		{
			return s_CollectionOudboudType;
		}
		//EXISTS (SELECT 1 FROM  WM_Strategy WHERE  WM_Strategy.WM_Strategy_ID = WM_Definition.WM_Strategy_ID AND  InOutBoundType = ?)
		String whereClause = "EXISTS (SELECT 1 FROM  WM_Strategy WHERE  WM_Strategy.WM_Strategy_ID = WM_Definition.WM_Strategy_ID AND InOutBoundType = ?)";
		
		s_CollectionOudboudType = new Query(ctx,Table_Name, whereClause ,trxName)
		.setOnlyActiveRecords(true)
		.setParameters(new Object[]{MWMRule.INOUTBOUNDTYPE_OutboundOperation})
		.setClient_ID()
		.setOrderBy(MWMDefinition.COLUMNNAME_SeqNo)
		.list();		

		return s_CollectionOudboudType;
	}
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3647030789608864533L;
	/**	Logger	**/
	private static CLogger	s_log = CLogger.getCLogger (MWMDefinition.class);
	
	
	/**	Cache						*/
	private static Collection<MWMDefinition> s_Collection = null;
	
	/**	Cache OudboudType			*/
	private static Collection<MWMDefinition> s_CollectionOudboudType = null;
	
	/**	Cache OudboudType			*/
	private static Collection<MWMDefinition> s_CollectionInboudType = null;
	
	
	/**************************************************************************
	 * 	Warehouse Definition
	 *	@param ctx context
	 *	@param WM_Definition_ID  
	 *	@param trxName transaction name 
	 */
	public MWMDefinition (Properties ctx, int WM_Definition_ID, String trxName)
	{
		super (ctx, WM_Definition_ID, trxName);
		if (WM_Definition_ID == 0)
		{
		}
	}

	/**
	 * 	Warehouse Area
	 *	@param ctx context
	 *	@param WM_Definition_ID Cahs Flow ID
	 */
	public MWMDefinition (Properties ctx, int WM_Definition_ID)
	{
		this (ctx, WM_Definition_ID, null);
	}

	
	
	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 *	@param trxName transaction
	 */
	public MWMDefinition (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MAsset

	/**
	 * 	String representation
	 *	@return info
	 */
	@Override
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MWMDefinition[")
			.append (get_ID ())
			.append ("-")
			.append (getName())
			.append ("]");
		return sb.toString ();
	}	//	toString
	
	public MWMStrategy getWMStrategy()
	{
		return new MWMStrategy(getCtx(), getWM_Strategy_ID(), get_TrxName());
	}

}	
