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

import java.util.Collection;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MClient;
import org.compiere.model.MForecastLine;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MLocator;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.MRequisition;
import org.compiere.model.MRequisitionLine;
import org.compiere.model.MWarehouse;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.model.X_M_Forecast;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;


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
	private int		m_AD_Client_ID = 11; // TODO - change it to <> 11 at Berlin conference!
	
	
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
		engine.addDocValidate(MInOut.Table_Name, this);
		engine.addDocValidate(MMovement.Table_Name, this);
	}	//	initialize

	public String modelChange (PO po, int type) throws Exception
	{
		log.info(po.get_TableName() + " Type: "+type);
		boolean isChange = (TYPE_AFTER_NEW == type || (TYPE_AFTER_CHANGE == type && MPPMRP.isChanged(po)));
		boolean isDelete = (TYPE_BEFORE_DELETE == type);
		boolean isReleased = false;
		boolean isVoided = false;
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
		else if (po instanceof X_M_Forecast && isChange)
		{
			X_M_Forecast fl = (X_M_Forecast)po;
			MPPMRP.M_Forecast(fl);
		}
		//
		else if (po instanceof MForecastLine && isChange)
		{
			MForecastLine fl = (MForecastLine)po;
			MPPMRP.M_ForecastLine(fl);
		}
		//
		else if (po instanceof MDDOrderLine && isChange)
		{
			MDDOrderLine ol = (MDDOrderLine)po;
			MPPMRP.DD_Order_Line(ol);
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
		if(po instanceof MInOut && timing == TIMING_BEFORE_COMPLETE)
		{
			MInOut inout = (MInOut)po;
			if(inout.isSOTrx())
			{
				final String whereClause = "C_OrderLine_ID IS NOT NULL"
											+" AND EXISTS (SELECT 1 FROM M_InOutLine iol"
											+" WHERE iol.M_InOut_ID=? AND PP_Order.C_OrderLine_ID = iol.C_OrderLine_ID) AND " 
											+ MPPOrder.COLUMNNAME_DocStatus + " = ?";	   
				Collection<MPPOrder> orders = new Query(po.getCtx(), MPPOrder.Table_Name, whereClause, po.get_TrxName())
												.setParameters(new Object[]{inout.getM_InOut_ID(),  MPPOrder.DOCSTATUS_InProgress})
												.list();
				for(MPPOrder order : orders)
				{	   
					String description = order.getDescription() !=  null ?  order.getDescription() : ""
						+ Msg.translate(inout.getCtx(), MInOut.COLUMNNAME_M_InOut_ID) 
						+ " : " 
						+ Msg.translate(inout.getCtx(), MInOut.COLUMNNAME_DocumentNo);

					order.setDescription(description);
					order.closeIt();
					order.setDocStatus(MPPOrder.DOCACTION_Close);
					order.setDocAction(MPPOrder.DOCACTION_None);
					order.saveEx();					   
					 
				}
			} 						
		}
		else if (po instanceof MInOut && timing == TIMING_AFTER_COMPLETE)
		{
			MInOut inout = (MInOut)po;

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
		//
		// Update Distribution Order Line
		else if (po instanceof MMovement && timing == TIMING_AFTER_COMPLETE)
		{
			MMovement move = (MMovement)po;
			for (MMovementLine line : move.getLines(false))
			{
				if(line.getDD_OrderLine_ID() > 0)
				{
				   MDDOrderLine oline= new MDDOrderLine(line.getCtx(),line.getDD_OrderLine_ID(), line.get_TrxName());
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
				order.setIsInTransit(true);
				order.saveEx();
			}	

		}
		return null;
	}	//	docValidate
	
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
}	//	LiberoValidator
