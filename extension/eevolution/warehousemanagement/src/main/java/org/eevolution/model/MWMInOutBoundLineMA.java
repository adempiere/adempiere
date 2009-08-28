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

import org.compiere.util.CLogger;

/**
 * Class Model for Inbound & Outbound Operation Line MA
 * @author victor.perez@e-evoluton.com, e-Evolution
 *
 */
public class MWMInOutBoundLineMA extends X_WM_InOutBoundLineMA
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 6140705060893582307L;
	/**	Logger							*/
	private static CLogger	s_log = CLogger.getCLogger (MWMInOutBoundLineMA.class);
	
	/**************************************************************************
	 * 	Asset Constructor
	 *	@param ctx context
	 *	@param M_InOutBoundLineMA_ID In Out Bound Line MA ID
	 *	@param trxName transaction name 
	 */
	public MWMInOutBoundLineMA (Properties ctx, int M_InOutBoundLineMA_ID, String trxName)
	{
		super (ctx, M_InOutBoundLineMA_ID, trxName);
		if (M_InOutBoundLineMA_ID == 0)
		{
		}
	}

	/**
	 * 	Discontinued Asset Constructor - DO NOT USE (but don't delete either)
	 *	@param ctx context
	 *	@param M_InOutBoundLine_ID  In Out Bound Line ID
	 */
	public MWMInOutBoundLineMA (Properties ctx, int M_InOutBoundLineMA_ID)
	{
		this (ctx, M_InOutBoundLineMA_ID, null);
	}

	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 *	@param trxName transaction
	 */
	public MWMInOutBoundLineMA (Properties ctx, ResultSet rs, String trxName)
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
		StringBuffer sb = new StringBuffer ("MInOutBoundLineMA[")
			.append (get_ID ())
			.append ("-")
			.append ("")
			.append ("]");
		return sb.toString ();
	}	//	toString
}	
