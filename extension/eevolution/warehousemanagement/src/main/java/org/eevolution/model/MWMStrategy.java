/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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
package org.eevolution.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.CLogger;

/**
 * Class Model Warehouse Strategy
 * @author victor.perez@e-evoluton.com, e-Evolution
 *
 */
public class MWMStrategy extends X_WM_Strategy
{


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
