/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *                 Teo Sarca, www.arhipac.ro                                  *
 *****************************************************************************/
package org.eevolution.model;

import java.math.BigDecimal;
import java.util.Collection;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MClient;
import org.compiere.model.MForecast;
import org.compiere.model.MForecastLine;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MLocator;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.MRMALine;
import org.compiere.model.MRequisition;
import org.compiere.model.MRequisitionLine;
import org.compiere.model.MWarehouse;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.eevolution.distribution.model.MDDOrder;
import org.eevolution.distribution.model.MDDOrderLine;


/**
 * Libero Validator 
 *	
 * @author Victor Perez
 * @author Trifon Trifonov
 *		<li>[ 2270421 ] Can not complete Shipment (Customer)</li>
 * @author Teo Sarca, www.arhipac.ro
 */
public class LiberoValidator implements ModelValidator
{
	/** Context variable which says if libero manufacturing is enabled */
	public static final String CTX_IsLiberoEnabled = "#IsLiberoEnabled";
	
	/**	Logger			*/
	private CLogger log = CLogger.getCLogger(getClass());
	/** Client			*/
	private int		m_AD_Client_ID = -1;
	
	
	public void initialize (ModelValidationEngine engine, MClient client)
	{
		if (client != null)
		{	
			m_AD_Client_ID = client.getAD_Client_ID();
		}
		engine.addModelChange(MProduct.Table_Name, this);
		//	MRP Sources
		for (String mrpTableName : MPPMRP.getSourceTableNames())
		{
			engine.addModelChange(mrpTableName, this);
		}
		//
		engine.addDocValidate(MOrder.Table_Name, this);
		engine.addDocValidate(MInOut.Table_Name, this);
		engine.addDocValidate(MMovement.Table_Name, this);
		
		//
        // TODO : register this class in other way 
//		ADClassNameMap.add("org.eevolution.form.VOrderReceiptIssue", "org.eevolution.form.WOrderReceiptIssue");
		
	}	//	initialize

	public String modelChange (PO po, int type) throws Exception
	{
		log.info(po.get_TableName() + " Type: "+type);
		boolean isChange = (TYPE_AFTER_NEW == type || (TYPE_AFTER_CHANGE == type && MPPMRP.isChanged(po)));
		boolean isDelete = (TYPE_BEFORE_DELETE == type);
		boolean isReleased = false;
		boolean isVoided = false;
		
		//Update MRP Change Net 
		if(MPPMRP.isChanged(po) && (TYPE_AFTER_CHANGE == type || TYPE_AFTER_NEW == type))
		{
			MPPMRP.setIsRequired(po,MPPProductPlanning.COLUMNNAME_IsRequiredMRP, true , po.get_TrxName());
		}
		
		DocAction doc = null;
		if (po instanceof DocAction)
		{
			doc = (DocAction)po;
		}
		else if (po instanceof MOrderLine)
		{
			doc = ((MOrderLine)po).getParent();
		}
		if (doc != null)
		{
			String docStatus = doc.getDocStatus();
			isReleased = DocAction.STATUS_InProgress.equals(docStatus)
							|| DocAction.STATUS_Completed.equals(docStatus);
			isVoided = DocAction.STATUS_Voided.equals(docStatus);
		}
		//
		// Can we change M_Product.C_UOM_ID ?
		if (po instanceof MProduct && TYPE_BEFORE_CHANGE == type
				&& po.is_ValueChanged(MProduct.COLUMNNAME_C_UOM_ID)
				&& MPPMRP.hasProductRecords((MProduct)po))
		{
			throw new AdempiereException("@SaveUomError@");
		}
		//
		//
		if (isDelete || isVoided || !po.isActive())
		{
			if(MOrder.Table_Name.equals(po.get_TableName()) 
			|| MOrderLine.Table_Name.equals(po.get_TableName())
			|| MPPOrder.Table_Name.equals(po.get_TableName())
			|| MPPOrderBOMLine.Table_Name.equals(po.get_TableName())
			|| MDDOrder.Table_Name.equals(po.get_TableName())	
			|| MDDOrderLine.Table_Name.equals(po.get_TableName())	
			|| MRequisition.Table_Name.equals(po.get_TableName())
			|| MRequisitionLine.Table_Name.equals(po.get_TableName())	
			|| MForecast.Table_Name.equals(po.get_TableName())	
			|| MForecastLine.Table_Name.equals(po.get_TableName())	
			)
				MPPMRP.deleteMRP(po);
		}
		else if (po instanceof MOrder)
		{
			MOrder order = (MOrder)po;
			// Create/Update a planning supply when isPurchase Order
			// or when you change DatePromised or DocStatus and is Purchase Order
			if (isChange && !order.isSOTrx())
			{
				MPPMRP.C_Order(order);
			}
			// Update MRP when you change the status order to complete or in process for a sales order
			// or you change DatePromised
			else if (type == TYPE_AFTER_CHANGE && order.isSOTrx())
			{
				if (isReleased || MPPMRP.isChanged(order)) 
				{	
					MPPMRP.C_Order(order);
				}
			}
		}
		// 
		else if (po instanceof MOrderLine && isChange)
		{
			MOrderLine ol = (MOrderLine)po;
			MOrder order = ol.getParent();
			// Create/Update a planning supply when isPurchase Order or you change relevant fields
			if (!order.isSOTrx())
			{
				MPPMRP.C_OrderLine(ol);
			}
			// Update MRP when Sales Order have document status in process or complete and 
			// you change relevant fields
			else if(order.isSOTrx() && isReleased)
			{
				MPPMRP.C_OrderLine(ol);
			}
		}
		//
		else if (po instanceof MRequisition && isChange)
		{
			MRequisition r = (MRequisition)po;
			MPPMRP.M_Requisition(r);
		}
		//
		else if (po instanceof MRequisitionLine && isChange)
		{
			MRequisitionLine rl = (MRequisitionLine)po;
			MPPMRP.M_RequisitionLine(rl);
		}
		//
		else if (po instanceof MForecast && isChange)
		{
			MForecast fl = (MForecast)po;
			MPPMRP.M_Forecast(fl);
		}
		//
		else if (po instanceof MForecastLine && isChange)
		{
			MForecastLine fl = (MForecastLine)po;
			MPPMRP.M_ForecastLine(fl);
		}
		
		else if (po instanceof MDDOrder  && isChange)
		{
			MDDOrder order = (MDDOrder)po;
			MPPMRP.DD_Order(order);
		}
		
		//
		else if (po instanceof MDDOrderLine && isChange)
		{
			MDDOrderLine ol = (MDDOrderLine)po;
			MPPMRP.DD_OrderLine(ol);
		}
		//
		else if (po instanceof MPPOrder && isChange)
		{
			MPPOrder order = (MPPOrder)po;
			MPPMRP.PP_Order(order);
		}
		//
		else if (po instanceof MPPOrderBOMLine && isChange)
		{
			MPPOrderBOMLine obl = (MPPOrderBOMLine)po;
			MPPMRP.PP_Order_BOMLine(obl);
		}
		//
		return null;
	}	//	modelChange
	
	public String docValidate (PO po, int timing)
	{
		log.info(po.get_TableName() + " Timing: "+timing);

		if (po instanceof MOrder && timing == TIMING_BEFORE_COMPLETE)
		{
			MOrder order = (MOrder) po;
			MPPMRP.C_Order(order);
		}

		if (po instanceof MInOut && timing == TIMING_AFTER_COMPLETE)
		{
			MInOut inout = (MInOut)po;
			if(inout.isSOTrx())
			{
				for (MInOutLine outline : inout.getLines())
				{										
					updateMPPOrder(outline);
				
				}
			}
			//Purchase Receipt
			else
			{	
				for (MInOutLine line : inout.getLines())
				{
					final String whereClause = "C_OrderLine_ID=? AND PP_Cost_Collector_ID IS NOT NULL";
					Collection<MOrderLine> olines = new Query(po.getCtx(), MOrderLine.Table_Name, whereClause, po.get_TrxName())
													.setParameters(new Object[]{line.getC_OrderLine_ID()})
													.list();
					for (MOrderLine oline : olines)
					{
						if(oline.getQtyOrdered().compareTo(oline.getQtyDelivered()) >= 0)
						{	
							MPPCostCollector cc = new MPPCostCollector(po.getCtx(), oline.getPP_Cost_Collector_ID(), po.get_TrxName());
							String docStatus = cc.completeIt();
							cc.setDocStatus(docStatus);
							cc.setDocAction(MPPCostCollector.DOCACTION_Close);
							cc.saveEx();
							return null;
						}
					}
				}
			}	
		}
		//
		// Update Distribution Order Line
		else if (po instanceof MMovement && timing == TIMING_AFTER_COMPLETE)
		{
			MMovement move = (MMovement)po;
			for (MMovementLine line : move.getLines(false))
			{
				if(line.getDD_OrderLine_ID() > 0)
				{
				   MDDOrderLine oline= new MDDOrderLine(line.getCtx(),line.getDD_OrderLine_ID(), po.get_TrxName());
				   MLocator locator_to = MLocator.get(line.getCtx(), line.getM_LocatorTo_ID());
				   MWarehouse warehouse =  MWarehouse.get(line.getCtx(), locator_to.getM_Warehouse_ID()); 
				   if(warehouse.isInTransit())
				   {
					   oline.setQtyInTransit(oline.getQtyInTransit().add(line.getMovementQty()));
					   oline.setConfirmedQty(Env.ZERO);
				   }
				   else
				   {
					   oline.setQtyInTransit(oline.getQtyInTransit().subtract(line.getMovementQty()));
					   oline.setQtyDelivered(oline.getQtyDelivered().add(line.getMovementQty()));
				   }   
				   oline.saveEx();
				   
				}
			}
			
			if(move.getDD_Order_ID() > 0)
			{	
				MDDOrder order = new MDDOrder(move.getCtx(), move.getDD_Order_ID(), move.get_TrxName());
				order.setIsInTransit(isInTransting(order));
				order.reserveStock(order.getLines(true, null));
				order.saveEx();
			}	

		}
		return null;
	}	//	docValidate
	
	/**
	 * Define if a Distribution Order is in transit 
	 * @param order
	 * @return true or false
	 */
	private boolean isInTransting(MDDOrder order)
	{
		for (MDDOrderLine line : order.getLines(true, null))
		{
			if(line.getQtyInTransit().signum() != 0)
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 *	User Login.
	 *	Called when preferences are set
	 *	@param AD_Org_ID org
	 *	@param AD_Role_ID role
	 *	@param AD_User_ID user
	 *	@return error message or null
	 */
	public String login (int AD_Org_ID, int AD_Role_ID, int AD_User_ID)
	{
		Env.setContext(Env.getCtx(), CTX_IsLiberoEnabled, true);
		return null;
	}	//	login

	/**
	 *	Get Client to be monitored
	 *	@return AD_Client_ID client
	 */
	public int getAD_Client_ID()
	{
		return m_AD_Client_ID;
	}	//	getAD_Client_ID
	
	private void updateMPPOrder(MInOutLine outline)
	{
		MPPOrder order = null;
		BigDecimal qtyShipment = Env.ZERO;
		MInOut inout =  outline.getParent();
		String movementType = inout.getMovementType();
		int orderLineId = 0;
		if(MInOut.MOVEMENTTYPE_CustomerShipment.equals(movementType))
		{
		   orderLineId = outline.getC_OrderLine_ID();
		   qtyShipment = outline.getMovementQty();
		}
		else if (MInOut.MOVEMENTTYPE_CustomerReturns.equals(movementType)) 
		{
				MRMALine rmaline = new MRMALine(outline.getCtx(),outline.getM_RMALine_ID(), null); 
				MInOutLine line = (MInOutLine) rmaline.getM_InOutLine();
				orderLineId = line.getC_OrderLine_ID();
				qtyShipment = outline.getMovementQty().negate();
		}
		
		final String whereClause = " C_OrderLine_ID = ? "
				+ " AND DocStatus IN  (?,?)"
				+ " AND EXISTS (SELECT 1 FROM  PP_Order_BOM "
				+ " WHERE PP_Order_BOM.PP_Order_ID=PP_Order.PP_Order_ID AND PP_Order_BOM.BOMType =? )"; 
	
		order = new Query(outline.getCtx(), MPPOrder.Table_Name, whereClause, outline.get_TrxName())
				.setParameters(orderLineId,
				 			MPPOrder.DOCSTATUS_InProgress,
				 			MPPOrder.DOCSTATUS_Completed,
					 		MPPOrderBOM.BOMTYPE_Make_To_Kit)
			 	.firstOnly();

		if (order == null || order.get_ID() <= 0)
				return;

		
		if(MPPOrder.DOCSTATUS_InProgress.equals(order.getDocStatus()))
		{
			order.completeIt();
			order.setDocStatus(MPPOrder.ACTION_Complete);
			order.setDocAction(MPPOrder.DOCACTION_Close);
			order.saveEx();			
		}
		if (MPPOrder.DOCSTATUS_Completed.equals(order.getDocStatus()))
		{	
			String description = order.getDescription() !=  null ?  order.getDescription() : ""
				+ Msg.translate(inout.getCtx(), MInOut.COLUMNNAME_M_InOut_ID) 
				+ " : " 
				+ Msg.translate(inout.getCtx(), MInOut.COLUMNNAME_DocumentNo);
			order.setDescription(description);
			order.updateMakeToKit(qtyShipment);
			order.saveEx();
		}
		
		if(order.getQtyToDeliver().signum() == 0)
		{
			order.closeIt();
			order.setDocStatus(MPPOrder.DOCACTION_Close);
			order.setDocAction(MPPOrder.DOCACTION_None);
			order.saveEx();
		}
		return;
	}
}	//	LiberoValidator
