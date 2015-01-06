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
import java.util.Properties;

import org.compiere.model.MColumn;
import org.compiere.util.CLogger;

/**
 * Class Model Warehouse Management Rule
 * @author victor.perez@e-evoluton.com, e-Evolution
 *
 */
public class MWMRule extends X_WM_Rule
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 930993957329465177L;
	/**	Logger	**/
	private static CLogger	s_log = CLogger.getCLogger (MWMRule.class);
	/** MColumn **/
	private MColumn m_column = null;
	
	/**************************************************************************
	 * 	Warehouse Management Rule Constructor
	 *	@param ctx context
	 *	@param WM_Rue_ID  
	 *	@param trxName transaction name 
	 */
	public MWMRule (Properties ctx, int WM_Rue_ID, String trxName)
	{
		super (ctx, WM_Rue_ID, trxName);
		if (WM_Rue_ID == 0)
		{
		}
	}

	/**
	 * 	Warehouse Management Rule Constructor
	 *	@param ctx context
	 *	@param WM_Rue_ID Cahs Flow ID
	 */
	public MWMRule (Properties ctx, int WM_Rue_ID)
	{
		this (ctx, WM_Rue_ID, null);
	}

	
	
	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 *	@param trxName transaction
	 */
	public MWMRule (Properties ctx, ResultSet rs, String trxName)
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
		StringBuffer sb = new StringBuffer ("MWMRule[")
			.append (get_ID ())
			.append ("-")
			.append (getName())
			.append ("]");
		return sb.toString ();
	}	//	toString
}	
