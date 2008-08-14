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

import java.util.List;

import org.compiere.model.MClient;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProject;
import org.compiere.model.MProjectPhase;
import org.compiere.model.MProjectTask;
import org.compiere.model.MRequisitionLine;
import org.compiere.model.MTable;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.model.X_C_Phase;
import org.compiere.model.X_C_Task;
import org.compiere.model.X_M_ForecastLine;
import org.compiere.util.CLogger;


/**
 *	LiberoValidator 
 *	
 *	@author Victor Perez
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
	private static CLogger log = CLogger.getCLogger(LiberoValidator.class);
	/** Client			*/
	private int		m_AD_Client_ID = -1;
	
	
	/**
	 *	Initialize Validation
	 *	@param engine validation engine 
	 *	@param client client
	 */
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
		engine.addModelChange(MClient.Table_Name, this);
		engine.addModelChange(X_M_ForecastLine.Table_Name, this);
		engine.addModelChange(MDDOrder.Table_Name, this);
		engine.addModelChange(MDDOrderLine.Table_Name, this);
		engine.addModelChange(MPPOrder.Table_Name, this);
		engine.addModelChange(MPPOrderBOMLine.Table_Name, this);
		//engine.addModelChange(MProject.Table_Name, this);
		//engine.addModelChange(MProjectPhase.Table_Name, this);
		//engine.addModelChange(MProjectTask.Table_Name, this);
	}	//	initialize

    /**
     *	Model Change of a monitored Table.
     *	Called after PO.beforeSave/PO.beforeDelete
     *	when you called addModelChange for the table
     *	@param po persistent object
     *	@param type TYPE_
     *	@return error message or null
     *	@exception Exception if the recipient wishes the change to be not accept.
     */
	public String modelChange (PO po, int type) throws Exception
	{
		log.info(po.get_TableName() + " Type: "+type);
		if (po.get_TableName().equals(MOrderLine.Table_Name) && ( type == TYPE_AFTER_NEW || type == TYPE_AFTER_CHANGE ))
		{
			MOrderLine ol = (MOrderLine)po;
			MPPMRP.C_OrderLine(ol, false);
			log.info(po.toString());
		}
		if (po.get_TableName().equals(MOrderLine.Table_Name) && type == TYPE_BEFORE_DELETE)
		{
			MOrderLine ol = (MOrderLine)po;
			org.eevolution.model.MPPMRP.C_OrderLine(ol, true);
			log.info(po.toString());
		}
		if (po.get_TableName().equals(MRequisitionLine.Table_Name) && (type == TYPE_AFTER_NEW ||  type == TYPE_AFTER_CHANGE ))
		{
			MRequisitionLine rl = (MRequisitionLine)po;
			MPPMRP.M_RequisitionLine(rl, false);
			log.info(po.toString());
		}
		if (po.get_TableName().equals(MRequisitionLine.Table_Name) && type == TYPE_BEFORE_DELETE )
		{
			MRequisitionLine ol = (MRequisitionLine)po;
			MPPMRP.M_RequisitionLine(ol, true);
			log.info(po.toString());
		}
		if (po.get_TableName().equals(X_M_ForecastLine.Table_Name) && (type == TYPE_AFTER_NEW || type ==  TYPE_AFTER_CHANGE ))
		{
			X_M_ForecastLine ol = (X_M_ForecastLine)po;
			MPPMRP.M_ForecastLine(ol, false);
			log.info(po.toString());
		}
		if (po.get_TableName().equals(X_M_ForecastLine.Table_Name) && type == TYPE_BEFORE_DELETE)
		{
			X_M_ForecastLine ol = (X_M_ForecastLine)po;
			MPPMRP.M_ForecastLine(ol, true);
			log.info(po.toString());
		}
		if (po.get_TableName().equals(MDDOrderLine.Table_Name) && (type == TYPE_AFTER_NEW|| type ==  TYPE_AFTER_CHANGE ))
		{
			MDDOrderLine ol = (MDDOrderLine)po;
			MPPMRP.DD_Order_Line(ol , false);
			log.info(po.toString());
		}
		if (po.get_TableName().equals(MDDOrderLine.Table_Name) && type == TYPE_BEFORE_DELETE)
		{
			MDDOrderLine ol = (MDDOrderLine)po;
			MPPMRP.DD_Order_Line(ol, true);	
			log.info(po.toString());
		}
		if (po.get_TableName().equals(MPPOrder.Table_Name) && (type == TYPE_AFTER_NEW || type ==  TYPE_AFTER_CHANGE ))
		{
			MPPOrder o = (MPPOrder)po;
			MPPMRP.PP_Order(o, false);	
			log.info(po.toString());
		}
		if (po.get_TableName().equals(MPPOrder.Table_Name) && type == TYPE_BEFORE_DELETE)
		{
			MPPOrder o = (MPPOrder)po;
			MPPMRP.PP_Order(o, true);	
			log.info(po.toString());
		}
		if (po.get_TableName().equals(MPPOrderBOMLine.Table_Name) && (type == TYPE_AFTER_NEW|| type ==  TYPE_AFTER_CHANGE ))
		{
			MPPOrderBOMLine ol = (MPPOrderBOMLine)po;
			MPPMRP.PP_Order_BOMLine(ol, false);	
			log.info(po.toString());
		}
		if (po.get_TableName().equals(MPPOrderBOMLine.Table_Name) && type == TYPE_BEFORE_DELETE)
		{
			MPPOrderBOMLine ol = (MPPOrderBOMLine)po;
			MPPMRP.PP_Order_BOMLine(ol, true);	
			log.info(po.toString());
		}
		
		log.info(po.get_TableName() + " Type: "+type);
		if (po.get_TableName().equals(MProjectPhase.Table_Name) && ( type == TYPE_AFTER_NEW ))
		{
			MProjectPhase pf = (MProjectPhase)po;
			
			X_C_Phase phase = new X_C_Phase(pf.getCtx(), pf.getC_Phase_ID(),pf.get_TrxName());
			int PP_Product_BOM_ID=(Integer)phase.get_Value("PP_Product_BOM_ID");
			int AD_Workflow_ID=(Integer)phase.get_Value("AD_Workflow_ID");
			
			MProject project = new MProject(po.getCtx(), pf.getC_Project_ID(), pf.get_TrxName());
			
			/*int PP_Product_BOM_ID=(Integer)pf.get_Value("PP_Product_BOM_ID");
			int AD_Workflow_ID=(Integer)pf.get_Value("AD_Workflow_ID");*/
			
			if(PP_Product_BOM_ID > 0 & AD_Workflow_ID > 0)
			{
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
			int PP_Product_BOM_ID=(Integer)task.get_Value("PP_Product_BOM_ID");
			int AD_Workflow_ID=(Integer)task.get_Value("AD_Workflow_ID");
			
			
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
			log.info(po.toString());
		}	
		
		return null;
	}	//	modelChange
	
	/**
	 *	Validate Document.
	 *	Called as first step of DocAction.prepareIt 
     *	when you called addDocValidate for the table.
     *	Note that totals, etc. may not be correct.
	 *	@param po persistent object
	 *	@param timing see TIMING_ constants
     *	@return error message or null
	 */
	public String docValidate (PO po, int timing)
	{
		log.info(po.get_TableName() + " Timing: "+timing);
		//	Ignore all after Complete events
		//if (timing == TIMING_AFTER_COMPLETE)
		//	return null;
		
		if (timing == TIMING_AFTER_COMPLETE) {
			if (po.get_TableName().equals(MOrder.Table_Name))
			{
				/**	Order Discount Example	*
				MOrder order = (MOrder)po;
				String error = orderDiscount(order);
				if (error != null)
					return error;
				/** Order Discount Example */
				log.info(po.toString());
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
		log.info("AD_User_ID=" + AD_User_ID);
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

	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("LiberoValidator");
		return sb.toString ();
	}	//	toString
	
}	//	LiberoValidator
