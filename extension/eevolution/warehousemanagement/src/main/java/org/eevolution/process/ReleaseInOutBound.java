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

package org.eevolution.process;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.compiere.model.I_S_Resource;
import org.compiere.model.MBPartner;
import org.compiere.model.MDocType;
import org.compiere.model.MLocator;
import org.compiere.model.MOrg;
import org.compiere.model.MPInstance;
import org.compiere.model.MPInstancePara;
import org.compiere.model.MProcess;
import org.compiere.model.MQuery;
import org.compiere.model.MStorage;
import org.compiere.model.MTable;
import org.compiere.model.MUser;
import org.compiere.model.MWarehouse;
import org.compiere.model.PrintInfo;
import org.compiere.model.Query;
import org.compiere.model.X_C_DocType;
import org.compiere.model.X_S_Resource;
import org.compiere.print.MPrintFormat;
import org.compiere.print.ReportCtl;
import org.compiere.print.ReportEngine;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.eevolution.engines.Warehouse.WMRuleEngine;
import org.eevolution.exceptions.NoPlantForWarehouseException;
import org.eevolution.model.I_WM_InOutBoundLine;
import org.eevolution.model.MDDOrder;
import org.eevolution.model.MDDOrderLine;
import org.eevolution.model.MWMInOutBound;
import org.eevolution.model.MWMInOutBoundLine;
import org.eevolution.model.X_DD_Order;
import org.eevolution.model.X_WM_InOutBound;

/**
 *	
 *  @author victor.perez@e-evolution.com, www.e-evolution.com
 *  @version $Id: $
 */
public class ReleaseInOutBound extends SvrProcess
{	
	/** Record ID */
	protected int p_Record_ID = 0;	
	protected int p_M_Locator_ID = 0;
	protected String p_DocAction = null;
	protected String p_DeliveryRule = null;
	protected int p_C_DocType_ID = 0;
	protected int p_WM_Area_Type_ID = 0;
	protected int p_WM_Section_Type_ID = 0;
	protected boolean p_IsPrintPickList = false;
	protected boolean p_IsCreateSupply = false;
	
	private MLocator m_locator = null;
	private int AD_User_ID = 0;
	private Timestamp Today = new Timestamp (System.currentTimeMillis()); 
	private MDDOrder order = null;
	private static Hashtable<Integer,MWarehouse>   m_warehouse_cache 	= new Hashtable();
	
	/**
	 * 	Get Parameters
	 */
	@Override
	protected void prepare ()
	{
		AD_User_ID = Env.getAD_User_ID(getCtx());
		p_Record_ID = getRecord_ID();
		for (ProcessInfoParameter para:getParameter())
		{
			String name = para.getParameterName();
			if (para.getParameter() == null)
				;
			else if (name.equals("WM_Area_Type_ID"))
			{
				p_WM_Area_Type_ID = para.getParameterAsInt();
			}
			else if (name.equals("WM_Section_Type_ID"))
			{
				p_WM_Section_Type_ID = para.getParameterAsInt();
			}
			else if (name.equals("DeliveryRule"))
			{
				p_DeliveryRule = (String)para.getParameter();
			}
			else if (name.equals("DocAction"))
			{
				p_DocAction = (String)para.getParameter();
			}
			else if (name.equals("C_DocType_ID"))
			{
				p_C_DocType_ID = para.getParameterAsInt();
			}
			else if (name.equals("M_Locator_ID"))
			{
				p_M_Locator_ID = para.getParameterAsInt();
				m_locator = new MLocator(getCtx(), p_M_Locator_ID, get_TrxName());
			}
			else if (name.equals("IsPrintPickList"))
			{
				p_IsPrintPickList = "Y".equals(para.getParameter());
			}
			else if (name.equals("IsCreateSupply"))
			{
				p_IsCreateSupply = "Y".equals(para.getParameter());
			}
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	/**
	 * 	Process - Generate Export Format
	 *	@return info
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected String doIt () throws Exception
	{
		String whereClause = "EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE  T_Selection.AD_PInstance_ID=? AND T_Selection.T_Selection_ID=WM_InOutBoundLine.WM_InOutboundLine_ID)";		
		Collection <MWMInOutBoundLine> lines = new Query(getCtx(), I_WM_InOutBoundLine.Table_Name, whereClause,null)
										.setClient_ID()
										.setParameters(new Object[]{getAD_PInstance_ID()})
										.list();
		
		int seq = 10;
		for (MWMInOutBoundLine boundline: lines)
		{
			MWMInOutBound bound = boundline.getParent();
			if(!m_warehouse_cache.containsKey(bound.getWM_InOutBound_ID()))
			{
				MWarehouse warehouse = (MWarehouse) bound.getM_Warehouse();
				m_warehouse_cache.put(bound.getWM_InOutBound_ID(), warehouse);
			}
				
			
			if(X_WM_InOutBound.DOCSTATUS_Completed.equals(bound.getDocStatus()) 
			|| X_WM_InOutBound.DOCSTATUS_Closed.equals(bound.getDocStatus())
			|| X_WM_InOutBound.DOCSTATUS_Voided.equals(bound.getDocStatus()))
			{
				continue;
			}
			else
			{
				bound.completeIt();
				bound.saveEx();
			}
			createDDOrder(boundline);
			seq ++;
		}
		
		if(order != null && p_DocAction != null)
		{
			order.setDocAction(p_DocAction);
			order.setDocStatus(DocAction.STATUS_InProgress);
			order.completeIt();
			order.save();
		}	
		
		if (p_IsCreateSupply)
		{
			Enumeration ws = m_warehouse_cache.elements();
			while(ws.hasMoreElements())
			{	
				runMRP((MWarehouse) ws.nextElement());
			}
			
		}
		
		if(p_IsPrintPickList && order != null)
		{
			// Get Format & Data
			ReportEngine re = this.getReportEngine("DistributionOrder_Header  ** TEMPLATE **","DD_Order_Header_v", order.getDD_Order_ID());
			if(re == null )
			{
				return"";
			}
			ReportCtl.preview(re);
			re.print(); // prints only original
		}
		
		return "" ;//@DocumentNo@ " + order.getDocumentNo();
	}

	
	/**
	 * create Distribution Order
	 * @param boundline
	 */
	protected void createDDOrder(MWMInOutBoundLine boundline)
	{				
			WMRuleEngine engineRule = WMRuleEngine.get();
			Collection<MStorage> storages = engineRule.getMStorage(boundline, p_WM_Area_Type_ID, p_WM_Section_Type_ID);
			
			int M_Shipper_ID = 0;
			BigDecimal qtySupply = Env.ZERO;
			if(storages != null && storages.size() > 0)
			{	
				//get the warehouse in transit
				MWarehouse[] wsts = MWarehouse.getInTransitForOrg(getCtx(), m_locator.getAD_Org_ID());
				if (wsts == null || wsts.length == 0)
				{	
					throw new AdempiereException("Do not exist Transit Warehouse");
				}
				
	
				//Org Must be linked to BPartner
				MOrg org = MOrg.get(getCtx(),  m_locator.getAD_Org_ID());
				int C_BPartner_ID = org.getLinkedC_BPartner_ID(get_TrxName()); 
				if (C_BPartner_ID == 0)
				{
					throw new AdempiereException("Do not exist Business Parter link for organization:" + org.getName());
				}
					
				MBPartner bp = MBPartner.get(getCtx(), C_BPartner_ID);
				
				if(order == null)
				{
					order = new MDDOrder(getCtx() , 0 , get_TrxName());
					order.setAD_Org_ID(m_locator.getAD_Org_ID());
					order.setC_BPartner_ID(C_BPartner_ID);
					if(p_C_DocType_ID > 0)
					{	
						order.setC_DocType_ID(p_C_DocType_ID);
					}	
					else
					{
						order.setC_DocType_ID(MDocType.getDocType(X_C_DocType.DOCBASETYPE_DistributionOrder));
					}
					
					order.setM_Warehouse_ID(wsts[0].get_ID());
					if(p_DocAction != null)
					{	
						order.setDocAction(p_DocAction);
					}
					else
					{
						order.setDocAction(X_DD_Order.DOCACTION_Prepare);
					}
					
					MUser[] users = MUser.getOfBPartner(getCtx(), bp.getC_BPartner_ID(), get_TrxName());
					if (users == null || users.length == 0)
					{	
						throw new AdempiereException("Do not exist Users for this Business Partner"+ bp.getName());						
					}
					
					order.setDateOrdered(getToday());                       
					order.setDatePromised(getToday());
			
					order.setAD_User_ID(users[0].getAD_User_ID());
					order.setM_Shipper_ID(M_Shipper_ID);	    	                
					order.setIsInDispute(false);
					order.setIsInTransit(false);
					order.setSalesRep_ID(bp.getPrimaryAD_User_ID());
					order.saveEx();
				}
		
	
					for (MStorage storage: storages)
					{			
						MDDOrderLine oline = new MDDOrderLine(order);
						oline.setM_Locator_ID(storage.getM_Locator_ID());
						oline.setM_LocatorTo_ID(p_M_Locator_ID);
						oline.setC_UOM_ID(boundline.getC_UOM_ID());
						oline.setM_Product_ID(boundline.getM_Product_ID());
						oline.setDateOrdered(getToday());                       
						oline.setDatePromised(boundline.getPickDate());
						oline.set_ValueOfColumn(I_WM_InOutBoundLine.COLUMNNAME_WM_InOutBoundLine_ID, boundline.getWM_InOutBoundLine_ID());
						oline.setIsInvoiced(false);
					
						
						if (boundline.getQtyToPick().subtract(qtySupply).compareTo(storage.getQtyOnHand()) < 0)
						{
							oline.setConfirmedQty(boundline.getQtyToPick());
							oline.setQtyEntered(boundline.getQtyToPick());
							oline.setQtyOrdered(boundline.getQtyToPick());
							oline.setTargetQty(boundline.getQtyToPick());
							qtySupply = qtySupply.add(boundline.getQtyToPick());					
						}
						else
						{
							oline.setConfirmedQty(storage.getQtyOnHand());
							oline.setQtyEntered(storage.getQtyOnHand());
							oline.setQtyOrdered(storage.getQtyOnHand());
							oline.setTargetQty(storage.getQtyOnHand());						
							qtySupply = qtySupply.add(storage.getQtyOnHand());				
						}				
						
						oline.saveEx();
					}
				}
	}
	
	/**
	 * Execute MRP background when the need generate supply
	 * @param warehouse MWarehouse
	 */
	public void runMRP(MWarehouse warehouse)
	{
		try 
		{
			String whereClause = I_S_Resource.COLUMNNAME_ManufacturingResourceType + "='"+ X_S_Resource.MANUFACTURINGRESOURCETYPE_Plant+"' AND "
							   + I_S_Resource.COLUMNNAME_M_Warehouse_ID + "= ?";
			
			int  plant_id = new  Query(getCtx(), I_S_Resource.Table_Name, whereClause, get_TrxName())
			.setParameters(new Object[]{warehouse.getM_Warehouse_ID()})
			.setClient_ID()
			.setOnlyActiveRecords(true)
			.firstId();
			
			if(plant_id <= 0)
			{
				throw new NoPlantForWarehouseException(warehouse.getM_Warehouse_ID());
			}
			
	    	//Prepare Process
			int AD_Process_ID = 0;	  
			AD_Process_ID = MProcess.getProcess_ID("PP_Calculate Material Plan",get_TrxName());
			
			MPInstance instance = new MPInstance(Env.getCtx(), AD_Process_ID, 0);
			if (!instance.save())
			{
				throw new Exception(Msg.getMsg(getCtx(), "ProcessNoInstance"),CLogger.retrieveException());
			}
			
	    	//call process
			ProcessInfo pi = new ProcessInfo ("PP_Calculate Material Plan", AD_Process_ID);
			pi.setAD_PInstance_ID (instance.getAD_PInstance_ID());
			
			MPInstancePara ip = new MPInstancePara(instance, 10);
			ip.setParameter("AD_Org_ID", warehouse.getAD_Org_ID());
			if (!ip.save())
			{
				String msg = "No Parameter added";  //  not translated
				throw new Exception(msg,CLogger.retrieveException()); 
			}	
			//	Add Parameter - DatePromised
			ip = new MPInstancePara(instance, 20);
			ip.setParameter("S_Resource_ID", plant_id);
			//ip.setP_Date_To(p_DatePromised_To);
			if (!ip.save())
			{
				String msg = "No Parameter added";  //  not translated
				throw new Exception(msg,CLogger.retrieveException()); 
			}	
			//	Add Parameter - M_Warehouse_ID
			ip = new MPInstancePara(instance, 30);
			ip.setParameter("M_Warehouse_ID", warehouse.getM_Warehouse_ID());
			if (!ip.save())
			{
				String msg = "No Parameter added";  //  not translated
				throw new Exception(msg,CLogger.retrieveException()); 
			}		
			//	Add Parameter - CreateDO
			ip = new MPInstancePara(instance, 40);
			ip.setParameter("IsRequiredDRP","N");
			if (!ip.save())
			{
				String msg = "No Parameter added";  //  not translated
				throw new Exception(msg,CLogger.retrieveException()); 
			}
			//TODO: Net Change MRP
			/*ip = new MPInstancePara(instance, 50);
			ip.setParameter("IsNetChangeMRP","N");
			if (!ip.save())
			{
				String msg = "No Parameter added";  //  not translated
				throw new Exception(msg,CLogger.retrieveException()); 
			}*/
				
			//	Add Parameter - IsTest=Y
			ip = new MPInstancePara(instance, 60);
			ip.setParameter("Version","");
			if (!ip.save())
			{
				String msg = "No Parameter added";  //  not translated
				throw new Exception(msg,CLogger.retrieveException()); 
			}		
		
			
			//	Execute Process
			MProcess worker = new MProcess(getCtx(),AD_Process_ID,get_TrxName());
			worker.processIt(pi, Trx.get(get_TrxName(), true));	
		}
		catch (SQLException ex)
		{
			throw new DBException(ex);
		} catch (Exception e) {
			throw new DBException(e);
		}
	}
	
	/*
	 * get the a Report Engine Instance using the view table 
	 * @param tableName
	 */
	private ReportEngine getReportEngine(String formatName, String tableName,int record_id)
	{
		// Get Format & Data
		int format_id= MPrintFormat.getPrintFormat_ID(formatName, MTable.getTable_ID(tableName), getAD_Client_ID());
		MPrintFormat format = MPrintFormat.get(getCtx(), format_id, true);
		if (format == null)
		{
			addLog("@NotFound@ @AD_PrintFormat_ID@");
			return null;
		}
		// query
		MQuery query = new MQuery(tableName);
		query.addRestriction("DD_Order_ID", MQuery.EQUAL, record_id);
		// Engine
		PrintInfo info = new PrintInfo(tableName,  MTable.getTable_ID(tableName), record_id);
		ReportEngine re = new ReportEngine(getCtx(), format, query, info);
		return re;
	}
	
	/**
	 * get Today
	 * @return Today
	 */
	protected Timestamp getToday()
	{
		return this.Today;
	}
}
