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
 * Class Model Warehouse Section Type
 * @author victor.perez@e-evoluton.com, e-Evolution
 *
 */
public class MWMSectionType extends X_WM_Section_Type
{

	public static Collection <MWMSectionType> getTypeByOutbound(Properties ctx, String trxName)
	{
		final String whereClause = MWMSectionType.COLUMNNAME_InOutBoundType + "="
								 + MWMSectionType.INOUTBOUNDTYPE_OutboundOperation;
		return new Query(ctx, MWMSectionType.Table_Name, whereClause, trxName)
		.setClient_ID()
		.setOnlyActiveRecords(true)
		.list();
	}
	
	public static Collection <MWMSectionType> getTypeByInbound(Properties ctx, String trxName)
	{
		final String whereClause = MWMSectionType.COLUMNNAME_InOutBoundType + "="
								 + MWMSectionType.INOUTBOUNDTYPE_InboundOperation;
		return new Query(ctx, MWMSectionType.Table_Name, whereClause, trxName)
		.setClient_ID()
		.setOnlyActiveRecords(true)
		.list();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6833844107617832202L;
	/**	Logger	**/
	private static CLogger	s_log = CLogger.getCLogger (MWMSectionType.class);
	
	/**************************************************************************
	 * 	Warehouse Section
	 *	@param ctx context
	 *	@param WM_Section_Type_ID
	 *	@param trxName transaction name 
	 */
	public MWMSectionType (Properties ctx, int WM_Section_Type_ID, String trxName)
	{
		super (ctx, WM_Section_Type_ID, trxName);
		if (WM_Section_Type_ID == 0)
		{
		}
	}

	/**
	 * 	Warehouse Section
	 *	@param ctx context
	 *	@param WM_Section_Type_ID Warehouse Section ID
	 */
	public MWMSectionType (Properties ctx, int WM_Section_Type_ID)
	{
		this (ctx, WM_Section_Type_ID, null);
	}
	
	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 *	@param trxName transaction
	 */
	public MWMSectionType (Properties ctx, ResultSet rs, String trxName)
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
		StringBuffer sb = new StringBuffer ("MWMSectionType[")
			.append (get_ID ())
			.append ("-")
			.append (getName())
			.append ("]");
		return sb.toString ();
	}	//	toString
	

}	
