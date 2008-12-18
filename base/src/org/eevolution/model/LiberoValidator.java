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
 *****************************************************************************/
package org.eevolution.model;

import java.util.Collection;

import org.compiere.model.MClient;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MRequisitionLine;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.eevolution.model.MForecastLine;
import org.compiere.util.CLogger;
import org.compiere.util.Msg;


/**
 *	LiberoValidator 
 *	
 *	@author Victor Perez
 *	@author Trifon Trifonov
 *		<li>[ 2270421 ] Can not complete Shipment (Customer)</li>
 *
 *	@version $Id: LiberoValidator.java,v 1 vpj-cd Exp $
 */
public class LiberoValidator implements ModelValidator
{
	/**
	 *	Constructor.
	 *	The class is instantiated when logging in and client is selected/known
	 */
	public LiberoValidator ()
	{
		super ();
	}	//	LiberoValidator
	
	/**	Logger			*/
	private CLogger log = CLogger.getCLogger(getClass());
	/** Client			*/
	private int		m_AD_Client_ID = -1;
	
	
	public void initialize (ModelValidationEngine engine, MClient client)
	{
		//client = null for global validator
		if (client != null) {	
			m_AD_Client_ID = client.getAD_Client_ID();
			log.info(client.toString());
		} else  {
			log.info("Initializing global validator: "+this.toString());
		}
		//	Tables to be monitored
		engine.addModelChange(MOrder.Table_Name, this);
		engine.addModelChange(MOrderLine.Table_Name, this);
		engine.addModelChange(MRequisitionLine.Table_Name, this);
		engine.addModelChange(MForecastLine.Table_Name, this);
		engine.addModelChange(MDDOrderLine.Table_Name, this);
		engine.addModelChange(MPPOrder.Table_Name, this);
		engine.addModelChange(MPPOrderBOMLine.Table_Name, this);
		engine.addDocValidate(MInOut.Table_Name, this);
	}	//	initialize

	public String modelChange (PO po, int type) throws Exception
	{
		log.info(po.get_TableName() + " Type: "+type);
		
		if (po.get_TableName().equals(MOrder.Table_Name))
		{
			MOrder order = (MOrder)po;
			//Create a planning supply when isPurchase Order
			if (type ==  TYPE_AFTER_NEW && !order.isSOTrx())
			{
				MPPMRP.C_Order(order, false);
			}
			//Update MRP when you change DatePromised or DocStatus and is Purchase Order
			else if (type == TYPE_AFTER_CHANGE && !order.isSOTrx())
			{
				if (   order.is_ValueChanged(MOrder.COLUMNNAME_DatePromised)
					|| order.is_ValueChanged(MOrder.COLUMNNAME_DocStatus))
					MPPMRP.C_Order(order, false);
			}
			
			//Update MRP when you change the status order to complete or in process for a sales order or you change DatePromised
			if (type == TYPE_AFTER_CHANGE && order.isSOTrx())
			{
				if (   order.is_ValueChanged(MOrder.COLUMNNAME_DatePromised) 
					|| order.is_ValueChanged(MOrder.COLUMNNAME_DocStatus) 
					|| order.getDocStatus().equals(MOrder.DOCSTATUS_InProgress) 
					|| order.getDocStatus().equals(MOrder.DOCSTATUS_Completed))
				{	
					MPPMRP.C_Order(order, false);
				}
			}

		}
		
		// 
		if (po.get_TableName().equals(MOrderLine.Table_Name))
		{
			MOrderLine ol = (MOrderLine)po;
			MOrder order = ol.getParent();
			//Create a planning supply when isPurchase Order
			if (type == TYPE_AFTER_NEW && !order.isSOTrx())
			{
				MPPMRP.C_OrderLine(ol, false);
			//Update MRP when when isPurchase Order and you change DatePromised , Product , Qty Ordered, Qty Delivered
			} else if (type == TYPE_AFTER_CHANGE && !order.isSOTrx()) {
				if (   ol.is_ValueChanged(MOrderLine.COLUMNNAME_DatePromised)
					|| ol.is_ValueChanged(MOrderLine.COLUMNNAME_M_Product_ID)
					|| ol.is_ValueChanged(MOrderLine.COLUMNNAME_QtyOrdered)
					|| ol.is_ValueChanged(MOrderLine.COLUMNNAME_QtyDelivered)
				)
					MPPMRP.C_OrderLine(ol, false);
			//Update MRP when Sales Order have document status in process or complete 
			//You change DatePromised , Product , Qty Ordered, Qty Delivered
			} else if(type == TYPE_AFTER_CHANGE && order.isSOTrx()) {
				if (   order.getDocStatus().equals(MOrder.DOCSTATUS_InProgress) 
					|| order.getDocStatus().equals(MOrder.DOCSTATUS_Completed))
				{
					if (   ol.is_ValueChanged(MOrderLine.COLUMNNAME_DatePromised)
						|| ol.is_ValueChanged(MOrderLine.COLUMNNAME_M_Product_ID)
						|| ol.is_ValueChanged(MOrderLine.COLUMNNAME_QtyOrdered)
						|| ol.is_ValueChanged(MOrderLine.COLUMNNAME_QtyDelivered)
					)
						MPPMRP.C_OrderLine(ol, false);
				}
			}
		}

		if (po.get_TableName().equals(MOrderLine.Table_Name) && type == TYPE_BEFORE_DELETE)
		{
			MOrderLine ol = (MOrderLine)po;
			MPPMRP.C_OrderLine(ol, true);
		}
		
		
		if (po.get_TableName().equals(MRequisitionLine.Table_Name) )
		{
			MRequisitionLine rl = (MRequisitionLine)po;
			if (type == TYPE_AFTER_NEW)
			{
				MPPMRP.M_RequisitionLine(rl, false);
			}
			if (type == TYPE_AFTER_CHANGE)
			{
				if (   rl.is_ValueChanged(MRequisitionLine.COLUMNNAME_M_Product_ID)
					|| rl.is_ValueChanged(MRequisitionLine.COLUMNNAME_Qty))
					MPPMRP.M_RequisitionLine(rl, false);
			}
		}
		
		if (po.get_TableName().equals(MRequisitionLine.Table_Name) && type == TYPE_BEFORE_DELETE )
		{
			MRequisitionLine ol = (MRequisitionLine)po;
			MPPMRP.M_RequisitionLine(ol, true);
		}

		if (po.get_TableName().equals(MForecastLine.Table_Name))
		{
			MForecastLine fl = (MForecastLine)po;
			if(type == TYPE_AFTER_NEW)
				MPPMRP.M_ForecastLine(fl, false);
			if (type == TYPE_AFTER_CHANGE)
			{
				if (   fl.is_ValueChanged(MForecastLine.COLUMNNAME_M_Product_ID)
					|| fl.is_ValueChanged(MForecastLine.COLUMNNAME_Qty)
					|| fl.is_ValueChanged(MForecastLine.COLUMNNAME_DatePromised)
				)
					MPPMRP.M_ForecastLine(fl, false);
			}
		}
		
		if (po.get_TableName().equals(MForecastLine.Table_Name) && type == TYPE_BEFORE_DELETE)
		{
			MForecastLine ol = (MForecastLine)po;
			MPPMRP.M_ForecastLine(ol, true);
		}

		if (po.get_TableName().equals(MDDOrderLine.Table_Name))
		{
			MDDOrderLine ol = (MDDOrderLine)po;
			if (type == TYPE_AFTER_NEW)
			{	
				MPPMRP.DD_Order_Line(ol , false);
			}
			if (type == TYPE_AFTER_CHANGE) {
				if (   ol.is_ValueChanged(MDDOrderLine.COLUMNNAME_M_Product_ID)
					|| ol.is_ValueChanged(MDDOrderLine.COLUMNNAME_DatePromised)
					|| ol.is_ValueChanged(MDDOrderLine.COLUMNNAME_QtyOrdered)
					|| ol.is_ValueChanged(MDDOrderLine.COLUMNNAME_QtyDelivered)
					|| ol.is_ValueChanged(MDDOrderLine.COLUMNNAME_ConfirmedQty)
					|| ol.is_ValueChanged(MDDOrderLine.COLUMNNAME_M_Locator_ID)
					|| ol.is_ValueChanged(MDDOrderLine.COLUMNNAME_M_LocatorTo_ID)
					|| ol.is_ValueChanged(MDDOrderLine.COLUMNNAME_ConfirmedQty))
					MPPMRP.DD_Order_Line(ol, false);
			}
		}

		if (po.get_TableName().equals(MDDOrderLine.Table_Name) && type == TYPE_BEFORE_DELETE)
		{
			
			MDDOrderLine ol = (MDDOrderLine)po;
			MPPMRP.DD_Order_Line(ol, true);	
		}

		if (po.get_TableName().equals(MPPOrder.Table_Name))
		{
			MPPOrder order = (MPPOrder)po;
			if (type == TYPE_AFTER_NEW) {
				MPPMRP.PP_Order(order, false);
			}
			if (type == TYPE_AFTER_CHANGE) {
				if (   order.is_ValueChanged(MPPOrder.COLUMNNAME_M_Product_ID)
					|| order.is_ValueChanged(MPPOrder.COLUMNNAME_DatePromised)
					|| order.is_ValueChanged(MPPOrder.COLUMNNAME_QtyOrdered)
					|| order.is_ValueChanged(MPPOrder.COLUMNNAME_QtyDelivered)
					|| order.is_ValueChanged(MPPOrder.COLUMNNAME_PP_Product_BOM_ID)
					|| order.is_ValueChanged(MPPOrder.COLUMNNAME_AD_Workflow_ID))
					MPPMRP.PP_Order(order, false);
			}
		}

		if (po.get_TableName().equals(MPPOrder.Table_Name) && type == TYPE_BEFORE_DELETE)
		{
			MPPOrder order = (MPPOrder)po;
			MPPMRP.PP_Order(order, true);	
		}
		
		if (po.get_TableName().equals(MPPOrderBOMLine.Table_Name))
		{
			MPPOrderBOMLine ol = (MPPOrderBOMLine)po;
			if (type == TYPE_AFTER_NEW) {
				MPPMRP.PP_Order_BOMLine(ol, false);
			}
			if (type == TYPE_AFTER_CHANGE) {
				if (   ol.is_ValueChanged(MPPOrderBOMLine.COLUMNNAME_M_Product_ID)
					|| ol.is_ValueChanged(MPPOrderBOMLine.COLUMNNAME_M_Warehouse_ID)
					|| ol.is_ValueChanged(MPPOrderBOMLine.COLUMNNAME_QtyEntered)
					|| ol.is_ValueChanged(MPPOrderBOMLine.COLUMNNAME_QtyDelivered))
					MPPMRP.PP_Order_BOMLine(ol, false);
			}
		}
		if (po.get_TableName().equals(MPPOrderBOMLine.Table_Name) && type == TYPE_BEFORE_DELETE)
		{
			MPPOrderBOMLine ol = (MPPOrderBOMLine)po;
			MPPMRP.PP_Order_BOMLine(ol, true);	
		}
		
		return null;
	}	//	modelChange
	
	public String docValidate (PO po, int timing)
	{
		log.info(po.get_TableName() + " Timing: "+timing);
		if (po.get_TableName().equals(MInOut.Table_Name) && timing == TIMING_AFTER_COMPLETE)
		{
			MInOut inout = (MInOut)po;
			for (MInOutLine line : inout.getLines())
			{
				String whereClause = "C_OrderLine_ID=? AND PP_Cost_Collector_ID IS NOT NULL";
				Collection<MOrderLine> olines = new Query(po.getCtx(), MOrderLine.Table_Name, whereClause, po.get_TrxName())
				.setParameters(new Object[]{line.getC_OrderLine_ID()}).list();
				
				for (MOrderLine oline:  olines)
				{
					if(oline.getQtyOrdered().compareTo(oline.getQtyDelivered()) >= 0)
					{	
						MPPCostCollector cc = new MPPCostCollector(po.getCtx(), oline.getPP_Cost_Collector_ID(), po.get_TrxName());
						cc.completeIt();
						cc.setDocStatus(MPPCostCollector.DOCSTATUS_Completed);
						cc.setDocAction(MPPCostCollector.DOCACTION_Close);
						cc.saveEx();
						return Msg.translate(po.getCtx(), "PP_Order_ID")+":"+cc.getPPOrder().getDocumentNo()+Msg.translate(po.getCtx(),"PP_Order_Node_ID")+":"+cc.getPPOrderNode().getValue();
						}
				}
				
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
