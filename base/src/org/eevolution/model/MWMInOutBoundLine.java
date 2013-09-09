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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MBPartner;
import org.compiere.model.MLocator;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.Query;
import org.compiere.util.CLogger;

/**
 * Class Model for Inbound & Outbound Operation Line
 * @author victor.perez@e-evoluton.com, e-Evolution
 *
 */
public class MWMInOutBoundLine extends X_WM_InOutBoundLine 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8397162302198048638L;

	/**	Logger							*/
	private static CLogger	s_log = CLogger.getCLogger (MWMInOutBoundLine.class);
	
	/** Parent					*/
	private MWMInOutBound		m_parent = null;
	/** Product 				*/
	private MProduct 			m_product = null; 
	/** MOrderLine orderLine 	*/ 
	private MOrderLine 			m_orderLine = null;
	/** MBPartner BPartner	 	*/ 
	private MBPartner			m_bpartner = null;
	
	
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
	
	/**
	 * get MInOutBound Order
	 * @return  MInOutBound Order
	 */
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
	public MProduct getMProduct()
	{
		if (m_product == null && getM_Product_ID() != 0)
		{	
			m_product =  MProduct.get (getCtx(), getM_Product_ID());
		}
		
		return m_product;
	}	//	getProduct
	
	/**
	 * Get Sales Order Line
	 * @return Sales Order line or null
	 */
	public MOrderLine getMOrderLine()
	{
		if(m_orderLine == null && getC_OrderLine_ID() != 0)
		{	
			m_orderLine = new MOrderLine(getCtx(), getC_OrderLine_ID(), get_TrxName());
		}	
		return m_orderLine;
	}
	
	/**
	 * get Business Partner 
	 * @return Business Partner or null
	 */
	public MBPartner getMBPartner()
	{
		if(m_bpartner == null && getMOrderLine().getC_BPartner_ID() != 0)
		{	
			m_bpartner = (MBPartner) getMOrderLine().getC_BPartner();
		}	
		return m_bpartner;
	}

	/**
	 * get Quantity to Pick
	 * @return BigDecimal with Quantity to Pick
	 */
	public BigDecimal getQtyToPick()
	{
		return getMovementQty().subtract(getPickedQty());
	}
	
	/** 
	 * get Quantity to Ship
	 * @return BigDecimal with Quantity to Ship
	 */
	public BigDecimal getQtyToDeliver()
	{
		MOrderLine oline = getMOrderLine();
		return oline.getQtyOrdered().subtract(oline.getQtyDelivered());
	}
	
	/**
	 * get MLocator based in Distribution Order Line
	 * @return MLocator or null
	 */
	public MLocator getMLocator()
	{
		String whereClause =  MWMInOutBoundLine.COLUMNNAME_WM_InOutBoundLine_ID + "=?";
		MDDOrderLine line = new Query(getCtx(),I_DD_OrderLine.Table_Name,whereClause, get_TrxName())
		.setClient_ID().setParameters(new Object[]{getWM_InOutBoundLine_ID()})
		.firstOnly();
		return (MLocator) line.getM_LocatorTo();
	}
	
	/**
	 * get Warehouse
	 * @return MWarehouse or null
	 */
	public int getM_Warehouse_ID()
	{
		return getParent().getM_Warehouse_ID();
	}
}	
