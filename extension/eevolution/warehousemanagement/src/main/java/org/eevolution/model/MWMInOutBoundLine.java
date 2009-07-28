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

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.model.engines.IDocumentLine;
import org.compiere.model.MOrder;
import org.compiere.model.MProduct;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;

/**
 * Class Model for Inbound & Outbound Operation Line
 * @author victor.perez@e-evoluton.com, e-Evolution
 *
 */
public class MWMInOutBoundLine extends X_WM_InOutBoundLine implements IDocumentLine
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4021478780945193837L;

	/**	Logger							*/
	private static CLogger	s_log = CLogger.getCLogger (MWMInOutBoundLine.class);
	
	/** Parent					*/
	private MWMInOutBound			m_parent = null;
	/** Product 				*/
	private MProduct 			m_product = null; 
	
	/**************************************************************************
	 * 	Asset Constructor
	 *	@param ctx context
	 *	@param M_InOutBoundLine_ID In Out Bound Line ID
	 *	@param trxName transaction name 
	 */
	public MWMInOutBoundLine (Properties ctx, int M_InOutBoundLine_ID, String trxName)
	{
		super (ctx, M_InOutBoundLine_ID, trxName);
		if (M_InOutBoundLine_ID == 0)
		{
		}
	}

	/**
	 * 	Discontinued Asset Constructor - DO NOT USE (but don't delete either)
	 *	@param ctx context
	 *	@param M_InOutBoundLine_ID  In Out Bound Line ID
	 */
	public MWMInOutBoundLine (Properties ctx, int M_InOutBoundLine_ID)
	{
		this (ctx, M_InOutBoundLine_ID, null);
	}

	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 *	@param trxName transaction
	 */
	public MWMInOutBoundLine (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MAsset

	/**
	 * 	Discontinued Asset Constructor - DO NOT USE (but don't delete either)
	 *	@param ctx context
	 *	@param M_InOutBoundLine_ID  In Out Bound Line ID
	 */
	public MWMInOutBoundLine (MWMInOutBound bound)
	{
		this (bound.getCtx(), 0, bound.get_TrxName());
		this.setWM_InOutBound_ID(bound.getWM_InOutBound_ID());
	}
	
	/**
	 * 	String representation
	 *	@return info
	 */
	@Override
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MInOutBoundLine[")
			.append (get_ID ())
			.append ("-")
			.append ("")
			.append ("]");
		return sb.toString ();
	}	//	toString
	
	public MWMInOutBound getParent()
	{
		if (m_parent == null)
			m_parent = new MWMInOutBound(getCtx(), getWM_InOutBound_ID(), get_TrxName());
		return m_parent;
	}	//	getParent
	
	/**
	 * 	Get Product
	 *	@return product or null
	 */
	public MProduct getProduct()
	{
		if (m_product == null && getM_Product_ID() != 0)
			m_product =  MProduct.get (getCtx(), getM_Product_ID());
		return m_product;
	}	//	getProduct
}	
