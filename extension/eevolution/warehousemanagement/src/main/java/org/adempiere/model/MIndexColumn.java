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
package org.adempiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.CLogger;

/**
 * Class Model for Index Column
 * @author victor.perez@e-evoluton.com, e-Evolution
 *
 */
public class MIndexColumn extends X_AD_TableIndex
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4495840818766809500L;
	/**	Logger							*/
	private static CLogger	s_log = CLogger.getCLogger (MIndexColumn.class);
	
	/**************************************************************************
	 * 	Index Column
	 *	@param ctx context
	 *	@param AD_IndexColumn_ID
	 *	@param trxName transaction name 
	 */
	public MIndexColumn (Properties ctx, int AD_IndexColumn_ID, String trxName)
	{
		super (ctx, AD_IndexColumn_ID, trxName);
		if (AD_IndexColumn_ID == 0)
		{
		}
	}

	/**
	 * 	Index Column
	 *	@param ctx context
	 *	@param AD_IndexColumn_ID Index Column ID
	 */
	public MIndexColumn (Properties ctx, int AD_IndexColumn_ID)
	{
		this (ctx, AD_IndexColumn_ID, null);
	}

	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 *	@param trxName transaction
	 */
	public MIndexColumn (Properties ctx, ResultSet rs, String trxName)
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
		StringBuffer sb = new StringBuffer ("MIndexColumn[")
			.append (get_ID ())
			.append ("-")
			.append (getName())
			.append ("]");
		return sb.toString ();
	}	//	toString
}	
