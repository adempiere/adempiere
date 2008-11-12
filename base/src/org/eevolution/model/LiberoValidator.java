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

import org.compiere.model.MClient;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MRequisitionLine;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.model.X_M_ForecastLine;
import org.compiere.util.CLogger;


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
		}
		else  {
			log.info("Initializing global validator: "+this.toString());
		}
		//	Tables to be monitored
		engine.addModelChange(MOrder.Table_Name, this);
		engine.addModelChange(MOrderLine.Table_Name, this);
		engine.addModelChange(MRequisitionLine.Table_Name, this);
		engine.addModelChange(X_M_ForecastLine.Table_Name, this);
		engine.addModelChange(MDDOrder.Table_Name, this);
		engine.addModelChange(MDDOrderLine.Table_Name, this);
		engine.addModelChange(MPPOrder.Table_Name, this);
		engine.addModelChange(MPPOrderBOMLine.Table_Name, this);
		//engine.addModelChange(MProject.Table_Name, this);
		//engine.addModelChange(MProjectPhase.Table_Name, this);
		//engine.addModelChange(MProjectTask.Table_Name, this);
	}	//	initialize

	public String modelChange (PO po, int type) throws Exception
	{
		log.info(po.get_TableName() + " Type: "+type);
		if (po.get_TableName().equals(MOrder.Table_Name))
		{
			MOrder order = (MOrder)po;
			//Create a planning supply when isPurchase Order
			if(type ==  TYPE_AFTER_NEW && !order.isSOTrx())
			{
				MPPMRP.C_Order((MOrder)po, false);
			}
			//Update MRP when you change DatePromised or DocStatus and is Purchase Order
			else if(type == TYPE_AFTER_CHANGE && !order.isSOTrx() && order.is_ValueChanged(MOrder.COLUMNNAME_DatePromised))
			{
				if( order.is_ValueChanged(MOrder.COLUMNNAME_DatePromised) || 
					order.is_ValueChanged(MOrder.COLUMNNAME_DocStatus))
						MPPMRP.C_Order((MOrder)po, false);
			}
			
			//Update MRP when you change the status order to complete or in process for a sales order or you change DatePromised
			if(type == TYPE_AFTER_CHANGE && order.isSOTrx())
			{
				if(order.is_ValueChanged(MOrder.COLUMNNAME_DatePromised) || 
				   order.is_ValueChanged(MOrder.COLUMNNAME_DocStatus) ||
				   order.getDocStatus().equals(MOrder.DOCSTATUS_InProgress) ||
				   order.getDocStatus().equals(MOrder.DOCSTATUS_Completed))
				{	
						MPPMRP.C_Order((MOrder)po, false);
				}
			}

		}
		// 
		if (po.get_TableName().equals(MOrderLine.Table_Name))
		{
			MOrderLine ol = (MOrderLine)po;
			MOrder order = ol.getParent();
			//Create a planning supply when isPurchase Order
			if ( type == TYPE_AFTER_NEW && !order.isSOTrx())
			{
				MPPMRP.C_OrderLine(ol, false);
			}
			//Update MRP when when isPurchase Order and you change DatePromised , Product , Qty Ordered, Qty Delivered
			else if(type == TYPE_AFTER_CHANGE && !order.isSOTrx())
			{
				if(ol.is_ValueChanged(MOrderLine.COLUMNNAME_DatePromised) || 
				   ol.is_ValueChanged(MOrderLine.COLUMNNAME_M_Product_ID) ||
				   ol.is_ValueChanged(MOrderLine.COLUMNNAME_QtyOrdered)   ||
				   ol.is_ValueChanged(MOrderLine.COLUMNNAME_QtyDelivered)
				  )
					MPPMRP.C_Order(order, false);
			}
			//Update MRP when Sales Order have document status in process or complete 
			//You change DatePromised , Product , Qty Ordered, Qty Delivered
			else if(type == TYPE_AFTER_CHANGE && order.isSOTrx())
			{
				  if( order.getDocStatus().equals(MOrder.DOCSTATUS_InProgress) ||
				      order.getDocStatus().equals(MOrder.DOCSTATUS_Completed))
				  {	  
						if(ol.is_ValueChanged(MOrderLine.COLUMNNAME_DatePromised) || 
						   ol.is_ValueChanged(MOrderLine.COLUMNNAME_M_Product_ID) ||
						   ol.is_ValueChanged(MOrderLine.COLUMNNAME_QtyOrdered)   ||
						   ol.is_ValueChanged(MOrderLine.COLUMNNAME_QtyDelivered)
						)
								MPPMRP.C_Order(order, false);
				  }
			}
			
		}
		if (po.get_TableName().equals(MOrderLine.Table_Name) && type == TYPE_BEFORE_DELETE)
		{
			MOrderLine ol = (MOrderLine)po;
			org.eevolution.model.MPPMRP.C_OrderLine(ol, true);
			log.info(po.toString());
		}
		
		
		if (po.get_TableName().equals(MRequisitionLine.Table_Name) )
		{
			MRequisitionLine rl = (MRequisitionLine)po;
			if(type == TYPE_AFTER_NEW)
			{
				MPPMRP.M_RequisitionLine(rl, false);
			}
			if(type == TYPE_AFTER_CHANGE)
			{
				if(  rl.is_ValueChanged(MRequisitionLine.COLUMNNAME_M_Product_ID) ||
				     rl.is_ValueChanged(MRequisitionLine.COLUMNNAME_Qty))
				MPPMRP.M_RequisitionLine(rl, false);
			}
		}
		if (po.get_TableName().equals(MRequisitionLine.Table_Name) && type == TYPE_BEFORE_DELETE )
		{
			MRequisitionLine ol = (MRequisitionLine)po;
			MPPMRP.M_RequisitionLine(ol, true);
			log.info(po.toString());
		}
		if (po.get_TableName().equals(X_M_ForecastLine.Table_Name))
		{
			X_M_ForecastLine fl = (X_M_ForecastLine)po;
			if(type == TYPE_AFTER_NEW)
				MPPMRP.M_ForecastLine(fl, false);
			if(type ==  TYPE_AFTER_CHANGE)
			{
				if(fl.is_ValueChanged(X_M_ForecastLine.COLUMNNAME_M_Product_ID) ||
				   fl.is_ValueChanged(X_M_ForecastLine.COLUMNNAME_Qty) ||
				   fl.is_ValueChanged(X_M_ForecastLine.COLUMNNAME_DatePromised)
				)
					MPPMRP.M_ForecastLine(fl, false);
			}
		}
		if (po.get_TableName().equals(X_M_ForecastLine.Table_Name) && type == TYPE_BEFORE_DELETE)
		{
			X_M_ForecastLine ol = (X_M_ForecastLine)po;
			MPPMRP.M_ForecastLine(ol, true);
			log.info(po.toString());
		}
		if (po.get_TableName().equals(MDDOrderLine.Table_Name))
		{
			MDDOrderLine ol = (MDDOrderLine)po;
			if (type == TYPE_AFTER_NEW)
			{	
				MPPMRP.DD_Order_Line(ol , false);
			}
			if (type ==  TYPE_AFTER_CHANGE)
			{
				if(ol.is_ValueChanged(MDDOrderLine.COLUMNNAME_M_Product_ID) ||
				   ol.is_ValueChanged(MDDOrderLine.COLUMNNAME_DatePromised) ||
				   ol.is_ValueChanged(MDDOrderLine.COLUMNNAME_QtyOrdered )  ||
				   ol.is_ValueChanged(MDDOrderLine.COLUMNNAME_QtyDelivered) ||
				   ol.is_ValueChanged(MDDOrderLine.COLUMNNAME_ConfirmedQty) ||		
				   ol.is_ValueChanged(MDDOrderLine.COLUMNNAME_M_Locator_ID) ||
				   ol.is_ValueChanged(MDDOrderLine.COLUMNNAME_M_LocatorTo_ID) ||
				   ol.is_ValueChanged(MDDOrderLine.COLUMNNAME_ConfirmedQty) 		
				)
					MPPMRP.DD_Order_Line(ol , false);
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
			if(type == TYPE_AFTER_NEW)
			{
				MPPMRP.PP_Order(order, false);	
			}
			if(type ==  TYPE_AFTER_CHANGE)
			{
				if(order.is_ValueChanged(MPPOrder.COLUMNNAME_M_Product_ID) ||
				   order.is_ValueChanged(MPPOrder.COLUMNNAME_DatePromised) ||
				   order.is_ValueChanged(MPPOrder.COLUMNNAME_QtyOrdered )  ||
				   order.is_ValueChanged(MPPOrder.COLUMNNAME_QtyDelivered) ||
				   order.is_ValueChanged(MPPOrder.COLUMNNAME_PP_Product_BOM_ID) ||
				   order.is_ValueChanged(MPPOrder.COLUMNNAME_AD_Workflow_ID) 
				)
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
			if(type == TYPE_AFTER_NEW)
			{
				MPPMRP.PP_Order_BOMLine(ol, false);	
			}
			if(type ==  TYPE_AFTER_CHANGE)
			{
				if(ol.is_ValueChanged(MPPOrderBOMLine.COLUMNNAME_M_Product_ID) ||
				   ol.is_ValueChanged(MPPOrderBOMLine.COLUMNNAME_M_Warehouse_ID) ||
				   ol.is_ValueChanged(MPPOrderBOMLine.COLUMNNAME_QtyEntered)  ||
				   ol.is_ValueChanged(MPPOrderBOMLine.COLUMNNAME_QtyDelivered) 
				)
				MPPMRP.PP_Order_BOMLine(ol, false);	
			}
		}
		if (po.get_TableName().equals(MPPOrderBOMLine.Table_Name) && type == TYPE_BEFORE_DELETE)
		{
			MPPOrderBOMLine ol = (MPPOrderBOMLine)po;
			MPPMRP.PP_Order_BOMLine(ol, true);	
		}
		
		/*
		log.info(po.get_TableName() + " Type: "+type);
		if (po.get_TableName().equals(MProjectPhase.Table_Name) && ( type == TYPE_AFTER_NEW ))
		{
			MProjectPhase pf = (MProjectPhase)po;
			
			X_C_Phase phase = new X_C_Phase(pf.getCtx(), pf.getC_Phase_ID(),pf.get_TrxName());
			if(phase.get_Value("PP_Product_BOM_ID") !=null && phase.get_Value("AD_Workflow_ID") != null)
			{
				pf.set_ValueOfColumn("PP_Product_BOM_ID", phase.get_Value("PP_Product_BOM_ID"));
				pf.set_ValueOfColumn("AD_Workflow_ID", phase.get_Value("AD_Workflow_ID"));
				
				int PP_Product_BOM_ID=(Integer)phase.get_Value("PP_Product_BOM_ID");
				int AD_Workflow_ID=(Integer)phase.get_Value("AD_Workflow_ID");
				
				MProject project = new MProject(po.getCtx(), pf.getC_Project_ID(), pf.get_TrxName());
			
				MPPOrder order = new MPPOrder(project,PP_Product_BOM_ID, AD_Workflow_ID );
				order.save();
				
				Query query = MTable.get(order.getCtx(),MPPOrderBOMLine.Table_ID).createQuery("PP_Order_ID=?", order.get_TrxName());
				query.setParameters(new Object[]{order.getPP_Order_ID()}); 
				List<MPPOrderBOMLine> bomline = query.list();
				
				for (MPPOrderBOMLine line : bomline)
				{
					line.set_CustomColumn("C_ProjectPhase_ID", pf.getC_ProjectPhase_ID());
					line.save();
				}
				
				query = MTable.get(order.getCtx(),MPPOrderNode.Table_ID).createQuery("PP_Order_ID=?", order.get_TrxName());
				query.setParameters(new Object[]{order.getPP_Order_ID()}); 
				List<MPPOrderNode> nodes = query.list();
				
				for (MPPOrderNode activity : nodes)
				{
					activity.set_CustomColumn("C_ProjectPhase_ID", pf.getC_ProjectPhase_ID());
					activity.save();
				}
			}	
			
			log.info(po.toString());
		}
			
		if (po.get_TableName().equals(MProjectTask.Table_Name) && ( type == TYPE_AFTER_NEW ))
		{
			MProjectTask pt = (MProjectTask)po;
			
			
			X_C_Task task = new X_C_Task(pt.getCtx(), pt.getC_Task_ID(),pt.get_TrxName());
			if(task.get_Value("PP_Product_BOM_ID") != null && task.get_Value("AD_Workflow_ID") !=null)
			{	
				int PP_Product_BOM_ID=(Integer)task.get_Value("PP_Product_BOM_ID");
				int AD_Workflow_ID=(Integer)task.get_Value("AD_Workflow_ID");
				
				pt.set_ValueOfColumn("PP_Product_BOM_ID", task.get_Value("PP_Product_BOM_ID"));
				pt.set_ValueOfColumn("AD_Workflow_ID", task.get_Value("AD_Workflow_ID"));
				
				
				MProjectPhase pf = new MProjectPhase(po.getCtx(), pt.getC_ProjectPhase_ID(), pt.get_TrxName());
				MProject project = new MProject(po.getCtx(), pf.getC_Project_ID(), pf.get_TrxName());
	
	
				if(PP_Product_BOM_ID > 0 & AD_Workflow_ID > 0)
				{
					MPPOrder order = new MPPOrder(project,PP_Product_BOM_ID, AD_Workflow_ID );
					order.save();
					
					Query query = MTable.get(order.getCtx(),MPPOrderBOMLine.Table_ID).createQuery("PP_Order_ID=?", order.get_TrxName());
					query.setParameters(new Object[]{order.getPP_Order_ID()}); 
					List<MPPOrderBOMLine> list = query.list();
					
					for (MPPOrderBOMLine line : list)
					{
						line.set_CustomColumn("C_ProjectPhase_ID", pf.getC_ProjectPhase_ID());
						line.set_CustomColumn("C_ProjectTask_ID", pt.getC_ProjectTask_ID());
						line.save();
					}
					
					query = MTable.get(order.getCtx(),MPPOrderNode.Table_ID).createQuery("PP_Order_ID=?", order.get_TrxName());
					query.setParameters(new Object[]{order.getPP_Order_ID()}); 
					List<MPPOrderNode> nodes = query.list();
					
					for (MPPOrderNode activity : nodes)
					{
						activity.set_CustomColumn("C_ProjectPhase_ID", pf.getC_ProjectPhase_ID());
						activity.set_CustomColumn("C_ProjectTask_ID", pt.getC_ProjectTask_ID());
						activity.save();
					}
				}		
			}	
			log.info(po.toString());
		}*/	
		
		return null;
	}	//	modelChange
	
	public String docValidate (PO po, int timing)
	{
		log.info(po.get_TableName() + " Timing: "+timing);
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
