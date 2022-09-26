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

import org.adempiere.core.domains.models.X_WM_Area_Type;
import org.compiere.util.CLogger;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Class Model Warehouse Area Type
 * @author victor.perez@e-evoluton.com, e-Evolution
 *
 */
public class MWMAreaType extends X_WM_Area_Type
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 5736982679951123329L;
	/**	Logger	**/
	private static CLogger	s_log = CLogger.getCLogger (MWMAreaType.class);
	
	/**************************************************************************
	 * 	Warehouse Area Type
	 *	@param ctx context
	 *	@param WM_Rue_ID  
	 *	@param trxName transaction name 
	 */
	public MWMAreaType (Properties ctx, int WM_Area_Type_ID, String trxName)
	{
		super (ctx, WM_Area_Type_ID, trxName);
		if (WM_Area_Type_ID == 0)
		{
		}
	}

	/**
	 * 	Warehouse Area Type
	 *	@param ctx context
	 *	@param WM_Area_Type_ID Cahs Flow ID
	 */
	public MWMAreaType (Properties ctx, int WM_Area_Type_ID)
	{
		this (ctx, WM_Area_Type_ID, null);
	}

	
	
	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 *	@param trxName transaction
	 */
	public MWMAreaType (Properties ctx, ResultSet rs, String trxName)
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
		StringBuffer sb = new StringBuffer ("MWMAreaType[")
			.append (get_ID ())
			.append ("-")
			.append (getName())
			.append ("]");
		return sb.toString ();
	}	//	toString
}	
