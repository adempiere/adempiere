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

import org.adempiere.core.domains.models.I_C_OrderLine;
import org.adempiere.core.domains.models.I_M_InOutLine;
import org.adempiere.core.domains.models.I_M_MovementLine;
import org.adempiere.core.domains.models.I_PP_Cost_Collector;
import org.adempiere.core.domains.models.X_WM_InOutBoundLine;
import org.compiere.model.MBPartner;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.MUOMConversion;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.eevolution.distribution.model.MDDOrderLine;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Class Model for Inbound & Outbound Operation Line
 * @author victor.perez@e-evoluton.com, e-Evolution
 *
 */
public class MWMInOutBoundLine extends X_WM_InOutBoundLine
{

	public static MWMInOutBoundLine getByInvoiceLine(MInvoiceLine invoiceLine)
	{
		StringBuilder whereClause = new StringBuilder();
		whereClause.append(MWMInOutBoundLine.COLUMNNAME_C_InvoiceLine_ID).append("=?");
		return new Query(invoiceLine.getCtx() , MWMInOutBoundLine.Table_Name, whereClause.toString() , invoiceLine.get_TrxName())
				.setClient_ID()
				.setParameters(invoiceLine.getC_InvoiceLine_ID())
				.first();
	}

	public static MWMInOutBoundLine getByOrderLine(MOrderLine orderLine)
	{
		StringBuilder whereClause = new StringBuilder();
		whereClause.append(MWMInOutBoundLine.COLUMNNAME_C_OrderLine_ID).append("=?");
		return new Query(orderLine.getCtx() , MWMInOutBoundLine.Table_Name, whereClause.toString() , orderLine.get_TrxName())
				.setClient_ID()
				.setParameters(orderLine.getC_OrderLine_ID())
				.first();
	}


	public BigDecimal getShipmentQtyDelivered() {
		return new Query(getCtx(), I_M_InOutLine.Table_Name,
				I_M_InOutLine.COLUMNNAME_WM_InOutBoundLine_ID + "=?" , get_TrxName())
				.setClient_ID()
				.setParameters(getWM_InOutBoundLine_ID())
				.sum(MInOutLine.COLUMNNAME_MovementQty);
	}

	public BigDecimal getManufacturingOrderQtyDelivered() {
		return new Query(getCtx(), I_PP_Cost_Collector.Table_Name,
				I_PP_Cost_Collector.COLUMNNAME_WM_InOutBoundLine_ID + "=?" , get_TrxName())
				.setClient_ID()
				.setParameters(getWM_InOutBoundLine_ID())
				.sum(I_PP_Cost_Collector.COLUMNNAME_MovementQty);
	}

	public BigDecimal getDistributionOrderQtyDelivered() {
		return new Query(getCtx(), I_M_MovementLine.Table_Name,
				I_M_MovementLine.COLUMNNAME_WM_InOutBoundLine_ID + "=? AND " + I_M_MovementLine.COLUMNNAME_M_Locator_ID +  "=? ", get_TrxName())
				.setClient_ID()
				.setParameters(getWM_InOutBoundLine_ID(), getM_LocatorTo_ID())
				.sum(MInOutLine.COLUMNNAME_MovementQty);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8397162302198048638L;

	/**	Logger							*/
	private static CLogger	s_log = CLogger.getCLogger (MWMInOutBoundLine.class);
	
	/** Parent					*/
	private MWMInOutBound parent = null;
	/** Product 				*/
	private MProduct product = null;
	/** MOrderLine orderLine 	*/ 
	private MOrderLine orderLine = null;

	/** MOrderLine orderLine 	*/
	private MInvoiceLine invoiceLine = null;
	/** MBPartner BPartner	 	*/ 
	private MBPartner partner = null;
	
	
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
			setProcessed(false);
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
	 * Constructor
	 * @param inOutBound
	 */
	public MWMInOutBoundLine (MWMInOutBound inOutBound)
	{
		this (inOutBound.getCtx(), 0, inOutBound.get_TrxName());
		this.setWM_InOutBound_ID(inOutBound.getWM_InOutBound_ID());
	}

	public MWMInOutBoundLine (MWMInOutBound inOutBound , MInvoiceLine invoiceLine)
	{
		this (inOutBound.getCtx(), 0, inOutBound.get_TrxName());
		setWM_InOutBound_ID(inOutBound.get_ID());
		setC_Invoice_ID(invoiceLine.getC_Invoice_ID());
		setC_InvoiceLine_ID(invoiceLine.getC_InvoiceLine_ID());
		setMovementQty(invoiceLine.getQtyInvoiced());
		setM_Product_ID(invoiceLine.getM_Product_ID());
		setC_Charge_ID(invoiceLine.getC_Charge_ID());
		setC_UOM_ID(invoiceLine.getC_UOM_ID());
		setDescription(invoiceLine.getDescription());
	}

	public MWMInOutBoundLine (MWMInOutBound inOutBound , MOrderLine orderLine)
	{
		this (inOutBound.getCtx(), 0, inOutBound.get_TrxName());
		setWM_InOutBound_ID(inOutBound.get_ID());
		setC_Order_ID(orderLine.getC_Order_ID());
		setC_OrderLine_ID(orderLine.getC_OrderLine_ID());
		
		setMovementQty(getQtyToDeliver());
		setM_Product_ID(orderLine.getM_Product_ID());
		setC_Charge_ID(orderLine.getC_Charge_ID());
		setC_UOM_ID(orderLine.getC_UOM_ID());
		setDescription(orderLine.getDescription());
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
	public MWMInOutBound getParent() {
		return Optional.ofNullable(parent).orElseGet(() -> new MWMInOutBound(getCtx(), getWM_InOutBound_ID(), get_TrxName()));
	}	//	getParent
	
	/**
	 * 	Get Product
	 *	@return product or null
	 */
	public MProduct getProduct()
	{
		return Optional.ofNullable(product).orElseGet(() -> MProduct.get (getCtx(), getM_Product_ID()));
	}	//	getProduct
	
	/**
	 * Get Sales Order Line
	 * @return Sales Order line or null
	 */
	public MOrderLine getOrderLine() {
		return Optional.ofNullable(orderLine).orElseGet(() -> new MOrderLine(getCtx(), getC_OrderLine_ID(), get_TrxName()));
	}

	/**
	 * Get Sales Order Line
	 * @return Sales Order line or null
	 */
	public MInvoiceLine getInvoiceLine() {
		return Optional.ofNullable(invoiceLine).orElseGet(() -> new MInvoiceLine(getCtx(), getC_InvoiceLine_ID(), get_TrxName()));
	}
	
	/**
	 * get Business Partner 
	 * @return Business Partner or null
	 */
	public MBPartner getBPartner() {
		return Optional.ofNullable(partner).orElseGet(() -> (MBPartner) getOrderLine().getC_BPartner());
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
	public BigDecimal getQtyToDeliver() {
		if(getC_OrderLine_ID() > 0) {
			Optional<I_C_OrderLine> maybeOrderLine = Optional.ofNullable(getOrderLine());
			AtomicReference<BigDecimal> quantityToDeliver = new AtomicReference<>(BigDecimal.ZERO);
			maybeOrderLine.ifPresent( orderLine -> {
				BigDecimal convertedQuantity = MUOMConversion.convertProductFrom(getCtx(), orderLine.getM_Product_ID(), orderLine.getC_UOM_ID(), orderLine.getQtyOrdered().subtract(orderLine.getQtyDelivered()));
				quantityToDeliver.set(convertedQuantity);
			});
			return quantityToDeliver.get();
		} else if(getDD_OrderLine_ID() != 0) {
			AtomicReference<BigDecimal> quantityToDeliver = new AtomicReference<>(BigDecimal.ZERO);
			if(getDD_OrderLine_ID() > 0) {
				MDDOrderLine orderLine = new MDDOrderLine(getCtx(), getDD_OrderLine_ID(), get_TrxName());
				BigDecimal convertedQuantity = MUOMConversion.convertProductFrom(getCtx(), orderLine.getM_Product_ID(), orderLine.getC_UOM_ID(), orderLine.getQtyOrdered().subtract(orderLine.getQtyDelivered()));
				quantityToDeliver.set(convertedQuantity);
			}
			return quantityToDeliver.get();
		}
		//	Return
		return Env.ZERO;
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
