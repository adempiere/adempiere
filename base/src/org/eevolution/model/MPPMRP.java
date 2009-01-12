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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MDocType;
import org.compiere.model.MForecastLine;
import org.compiere.model.MLocator;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.MRefList;
import org.compiere.model.MRequisition;
import org.compiere.model.MRequisitionLine;
import org.compiere.model.MResource;
import org.compiere.model.MResourceType;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.model.X_M_Forecast;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;
import org.compiere.wf.MWorkflow;


/**
 *	PP_MRP
 *	
 *  @author Victor Perez www.e-evolution.com     
 *  @version $Id: MPPMRP.java,v 1.4 2004/05/13 06:05:22 vpj-cd Exp $
 *  
 *  @author Teo Sarca, www.arhipac.ro
 */
public class MPPMRP extends X_PP_MRP
{
	private static final long serialVersionUID = 1L;
	
	private static CLogger s_log = CLogger.getCLogger(MPPMRP.class);
	
	private static HashMap<String, String[]> s_sourceColumnNames = new HashMap<String, String[]>();
	static
	{
		s_sourceColumnNames.put(MOrder.Table_Name, new String[]{
				MOrder.COLUMNNAME_DatePromised,
				MOrder.COLUMNNAME_DocStatus,
		});
		s_sourceColumnNames.put(MOrderLine.Table_Name, new String[]{
				"AD_Org_ID",
				MOrderLine.COLUMNNAME_DateOrdered,
				MOrderLine.COLUMNNAME_DatePromised,
				MOrderLine.COLUMNNAME_C_BPartner_ID,
				MOrderLine.COLUMNNAME_M_Warehouse_ID,
				MOrderLine.COLUMNNAME_M_Product_ID,
				MOrderLine.COLUMNNAME_QtyOrdered,
				MOrderLine.COLUMNNAME_QtyDelivered,
		});
		s_sourceColumnNames.put(MRequisition.Table_Name, new String[]{
				MRequisition.COLUMNNAME_DateRequired,
				MRequisition.COLUMNNAME_M_Warehouse_ID,
		});
		s_sourceColumnNames.put(MRequisitionLine.Table_Name, new String[]{
				"AD_Org_ID",
				MRequisitionLine.COLUMNNAME_M_Product_ID,
				MRequisitionLine.COLUMNNAME_Qty,
				MRequisitionLine.COLUMNNAME_C_OrderLine_ID, // QtyOrdered depends on that
		});
		s_sourceColumnNames.put(X_M_Forecast.Table_Name, new String[]{
		});
		s_sourceColumnNames.put(MForecastLine.Table_Name, new String[]{
				"AD_Org_ID",
				MForecastLine.COLUMNNAME_DatePromised,
				MForecastLine.COLUMNNAME_M_Warehouse_ID,
				MForecastLine.COLUMNNAME_M_Product_ID,
				MForecastLine.COLUMNNAME_Qty,
		});
		s_sourceColumnNames.put(MDDOrderLine.Table_Name, new String[]{
				"AD_Org_ID",
				MDDOrderLine.COLUMNNAME_M_Product_ID,
				MDDOrderLine.COLUMNNAME_DatePromised,
				MDDOrderLine.COLUMNNAME_QtyOrdered,
				MDDOrderLine.COLUMNNAME_QtyDelivered,
				MDDOrderLine.COLUMNNAME_ConfirmedQty,
				MDDOrderLine.COLUMNNAME_M_Locator_ID,
				MDDOrderLine.COLUMNNAME_M_LocatorTo_ID,
				MDDOrderLine.COLUMNNAME_ConfirmedQty,
		});
		s_sourceColumnNames.put(MPPOrder.Table_Name, new String[]{
				"AD_Org_ID",
				MPPOrder.COLUMNNAME_M_Product_ID,
				MPPOrder.COLUMNNAME_DatePromised,
				MPPOrder.COLUMNNAME_QtyOrdered,
				MPPOrder.COLUMNNAME_QtyDelivered,
				MPPOrder.COLUMNNAME_PP_Product_BOM_ID,
				MPPOrder.COLUMNNAME_AD_Workflow_ID,
		});
		s_sourceColumnNames.put(MPPOrderBOMLine.Table_Name, new String[]{
				MPPOrderBOMLine.COLUMNNAME_M_Product_ID,
				MPPOrderBOMLine.COLUMNNAME_M_Warehouse_ID,
				MPPOrderBOMLine.COLUMNNAME_QtyEntered,
				MPPOrderBOMLine.COLUMNNAME_QtyDelivered,
		});
	}

	/**
	 * Check if a persistent object is changed, from MRP point of view
	 * @param po MRP relevant PO (e.g. MOrder, MOrderLine, MPPOrder etc)
	 * @return true if object changed
	 */
	public static boolean isChanged(PO po)
	{
		String[] columnNames = s_sourceColumnNames.get(po.get_TableName());
		if (columnNames == null || columnNames.length == 0)
		{
			return false;
		}
		for (String columnName : columnNames)
		{
			if (po.is_ValueChanged(columnName))
			{
				return true;
			}
		}
		return false;
	}
	
	public static void deleteMRP(PO po)
	{
		String tableName = po.get_TableName();
		int no = DB.executeUpdateEx("DELETE FROM "+Table_Name+" WHERE "+tableName+"_ID=? AND AD_Client_ID=?",
				new Object[]{po.get_ID(), po.getAD_Client_ID()},
				po.get_TrxName());
		s_log.finest("Deleted "+tableName+" #"+no);
	}
	
	private static Query getQuery(PO po, String typeMRP, String orderType)
	{
		ArrayList<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer();
		//
		whereClause.append("AD_Client_ID=?");
		params.add(po.getAD_Client_ID());
		//
		whereClause.append(" AND ").append(po.get_TableName()).append("_ID=?");
		params.add(po.get_ID());
		//
		if (typeMRP != null)
		{
			whereClause.append(" AND ").append(COLUMNNAME_TypeMRP).append("=?");
			params.add(typeMRP);
		}
		//
		if (orderType != null)
		{
			whereClause.append(" AND ").append(COLUMNNAME_OrderType).append("=?");
			params.add(orderType);
		}
		//
		return new Query(po.getCtx(), Table_Name, whereClause.toString(), po.get_TrxName())
					.setParameters(params);
	}
	
	/**************************************************************************
	 * 	Default Constructor
	 *	@param ctx context
	 *	@param PP_MRP_ID id
	 *	@param trxName Transaction Name
	 */
	public MPPMRP(Properties ctx, int PP_MRP_ID, String trxName)
	{
		super(ctx, PP_MRP_ID,trxName);
		if (PP_MRP_ID == 0)
		{
			setDateSimulation(new Timestamp (System.currentTimeMillis()));    
		}
	}	//	MPPMRP

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 */
	public MPPMRP(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs , trxName);
	}                
	
	public void setPP_Order(MPPOrder o)
	{
		setPP_Order_ID(o.getPP_Order_ID());
		setOrderType(ORDERTYPE_ManufacturingOrder);
		//
		setName(o.getDocumentNo());
		setDescription(o.getDescription());
		setDatePromised(o.getDatePromised());
		setDateOrdered(o.getDateOrdered());
		setDateStartSchedule(o.getDateStartSchedule());
		setDateFinishSchedule(o.getDateFinishSchedule());
		setS_Resource_ID(o.getS_Resource_ID());
		setDocStatus(o.getDocStatus());
	}
	
	public void setC_Order(MOrder o)
	{
		setC_Order_ID(o.get_ID());
		setC_BPartner_ID(o.getC_BPartner_ID());
		setDocStatus(o.getDocStatus());
		if (o.isSOTrx())
		{    
			setOrderType(MPPMRP.ORDERTYPE_SalesOrder);
			setTypeMRP(MPPMRP.TYPEMRP_Demand);
		}
		else
		{
			setOrderType(MPPMRP.ORDERTYPE_PurchaseOrder);
			setTypeMRP(MPPMRP.TYPEMRP_Supply);                                 
		}
	}

	/**
	 * Create MRP record based in Forecast Line 
	 * @param MForecastLine Forecast Line
	 * @param delete Indicate if this record is delete
	 */
	public static void M_ForecastLine(MForecastLine fl, boolean delete)
	{
		String trxName = fl.get_TrxName();
		Properties ctx = fl.getCtx();
		
		if (delete)
		{
			deleteMRP(fl);
			return;
		}

		X_M_Forecast f = new X_M_Forecast(ctx, fl.getM_Forecast_ID(), trxName);
		MPPMRP mrp = getQuery(fl, null, null).firstOnly();
		if (mrp == null)
		{
			mrp = new MPPMRP(ctx, 0, trxName);     
			mrp.setAD_Org_ID(fl.getAD_Org_ID());
			mrp.setM_Forecast_ID(fl.getM_Forecast_ID());
			mrp.setM_ForecastLine_ID(fl.getM_ForecastLine_ID());
			mrp.setOrderType(MPPMRP.ORDERTYPE_Forecast);
			mrp.setTypeMRP(MPPMRP.TYPEMRP_Demand);                         
		}
		mrp.setAD_Org_ID(fl.getAD_Org_ID());
		mrp.setDescription(f.getDescription());
		mrp.setName("MRP");
		mrp.setQty(fl.getQty());  
		mrp.setDatePromised(fl.getDatePromised());
		mrp.setDateStartSchedule(fl.getDatePromised());
		mrp.setDateFinishSchedule(fl.getDatePromised());
		mrp.setDateOrdered(fl.getDatePromised());
		mrp.setM_Product_ID(fl.getM_Product_ID());
		mrp.setM_Warehouse_ID(fl.getM_Warehouse_ID());
		mrp.setDocStatus(DocAction.STATUS_InProgress);
		mrp.saveEx();
	}
	
	/**
	 * Create MRP record based in Order 
	 * @param MOrder
	 * @param delete Indicate if this record is delete
	 * @return
	 */
	public static void C_Order(MOrder o, boolean delete)
	{
		if (delete)
		{
			deleteMRP(o);
			return;
		}
		
		MDocType dt = MDocType.get(o.getCtx(), o.getC_DocTypeTarget_ID());
		String DocSubTypeSO = dt.getDocSubTypeSO();
		if(MDocType.DOCSUBTYPESO_StandardOrder.equals(DocSubTypeSO) || !o.isSOTrx())
		{		
			if((o.getDocStatus().equals(MOrder.DOCSTATUS_InProgress)
					|| o.getDocStatus().equals(MOrder.DOCSTATUS_Completed))
					|| !o.isSOTrx())
			{
				for(MOrderLine line : o.getLines())
				{
					C_OrderLine(line , false);
				}
			}
			
			if (o.is_ValueChanged(MOrder.COLUMNNAME_DocStatus)
					|| o.is_ValueChanged(MOrder.COLUMNNAME_C_BPartner_ID)
				)
			{
				List<MPPMRP> list = getQuery(o, null, null).list();
				for (MPPMRP mrp : list)
				{
					mrp.setC_Order(o);
					mrp.saveEx();
				}
			}
		}	
	}
	
	/**
	 * Create MRP record based in Order Line 
	 * @param MOrderLine
	 * @param delete Indicate if this record is delete
	 * @return
	 */
	public static void C_OrderLine(MOrderLine ol, boolean delete)
	{
		if (delete)
		{
			deleteMRP(ol);
			// Delete generated manufacturing order
			MPPOrder order = MPPOrder.forC_OrderLine_ID(ol.getCtx(), ol.get_ID(), ol.get_TrxName());
			if (order != null && !order.isProcessed())
			{
				order.deleteEx(true);
			}
			return;
		}
		
		MPPMRP mrp = getQuery(ol, null, null).firstOnly();
		if(mrp == null)
		{	
			mrp = new MPPMRP(ol.getCtx(), 0, ol.get_TrxName());                                                          
			mrp.setC_OrderLine_ID(ol.getC_OrderLine_ID());
		}
		mrp.setAD_Org_ID(ol.getAD_Org_ID());
		mrp.setC_Order(ol.getParent());
		mrp.setDescription(ol.getDescription());
		mrp.setName("MRP");
		mrp.setQty(ol.getQtyOrdered().subtract(ol.getQtyDelivered()));
		mrp.setDatePromised(ol.getDatePromised());
		mrp.setDateStartSchedule(ol.getDatePromised());
		mrp.setDateFinishSchedule(ol.getDatePromised());
		mrp.setDateOrdered(ol.getDateOrdered());
		mrp.setM_Product_ID(ol.getM_Product_ID());
		mrp.setM_Warehouse_ID(ol.getM_Warehouse_ID());
		mrp.saveEx();

		MOrder o = ol.getParent();
		MDocType dt = MDocType.get(o.getCtx(), o.getC_DocTypeTarget_ID());
		String DocSubTypeSO = dt.getDocSubTypeSO();
		if(MDocType.DOCSUBTYPESO_StandardOrder.equals(DocSubTypeSO))
		{
			MPPOrder order = MPPOrder.forC_OrderLine_ID(ol.getCtx(), ol.get_ID(), ol.get_TrxName());
			if (order == null)
			{
				final String whereClause = MPPProductBOM.COLUMNNAME_BOMType+"=? "
							   +" OR  "+MPPProductBOM.COLUMNNAME_BOMType+"=? "
							   +" AND "+MPPProductBOM.COLUMNNAME_BOMUse+"=?"
							   +" AND "+MPPProductBOM.COLUMNNAME_M_Product_ID+"=?";
				MPPProductBOM bom = new Query(ol.getCtx(), MPPProductBOM.Table_Name, whereClause, null)
							.setParameters(new Object[]{
									MPPProductBOM.BOMTYPE_Make_To_Order, 
									MPPProductBOM.BOMTYPE_Make_To_Kit, 
									MPPProductBOM.BOMUSE_Manufacturing,
									ol.getM_Product_ID()})
							.firstOnly();	
				
				MPPProductPlanning pp = null;
				//Validate the BOM based in planning data 
				if(bom == null)
				{
					pp = MPPProductPlanning.find(ol.getCtx(), ol.getAD_Org_ID(), 0, 0, ol.getM_Product_ID(), null); 
					if(pp != null)
					{	
						bom = (MPPProductBOM) pp.getPP_Product_BOM();
						if(bom != null && !MPPProductBOM.BOMTYPE_Make_To_Order.equals(bom.getBOMType()))
						{
							bom = null;
						}
						if(bom != null && !MPPProductBOM.BOMTYPE_Make_To_Kit.equals(bom.getBOMType()))
						{
							bom = null;
						}
					}
				}
				if (bom != null) 
				{		
					final MProduct product = MProduct.get(ol.getCtx(), ol.getM_Product_ID());   
					final int plant_id = MPPProductPlanning.getPlantForWarehouse(ol.getM_Warehouse_ID());
					MWorkflow workflow = MWorkflow.get(ol.getCtx(), MWorkflow.getWorkflowSearchKey(ol.getCtx(), product));
					//Validate the workflow based in planning data 						
					if(workflow == null && pp != null)
					{
						workflow = pp.getAD_Workflow();
					}
					//
					if (plant_id > 0 && workflow != null)
					{
						int duration = MPPMRP.getDays(ol.getCtx(), plant_id, workflow.get_ID(), ol.getQtyOrdered(), ol.get_TrxName()).intValue();
						//
						order = new MPPOrder(ol.getCtx(), 0 , ol.get_TrxName());
						order.setDescription( Msg.translate(ol.getCtx(),MRefList.getListName(ol.getCtx(), MPPOrderBOM.BOMTYPE_AD_Reference_ID, bom.getBOMType())) 
								+ " "
								+ Msg.translate(ol.getCtx(), MOrder.COLUMNNAME_C_Order_ID) 
								+ " : "
								+ o.getDocumentNo());
						order.setC_OrderLine_ID(ol.getC_OrderLine_ID());
						order.setS_Resource_ID(plant_id);
						order.setM_Warehouse_ID(ol.getM_Warehouse_ID());
						order.setM_Product_ID(ol.getM_Product_ID());
						order.setM_AttributeSetInstance_ID(ol.getM_AttributeSetInstance_ID());
						order.setPP_Product_BOM_ID(bom.get_ID());
						order.setAD_Workflow_ID(workflow.get_ID());
						order.setPlanner_ID(ol.getParent().getSalesRep_ID());
						order.setLine(10);
						order.setDateOrdered(ol.getDateOrdered());                       
						order.setDatePromised(ol.getDatePromised());
						order.setDateStartSchedule(TimeUtil.addDays(ol.getDatePromised(), 0 - duration));
						order.setDateFinishSchedule(ol.getDatePromised());
						order.setC_UOM_ID(ol.getC_UOM_ID());
						order.setQty(ol.getQtyOrdered());
						order.setPriorityRule(MPPOrder.PRIORITYRULE_High);                                
						order.saveEx();  
						order.setDocStatus(order.prepareIt());
						order.setDocAction(MPPOrder.ACTION_Complete);
						order.saveEx();
					}
				}    
			}
			else
			{    
				if (!order.isProcessed())
				{
					if(order.getQtyEntered().compareTo(ol.getQtyEntered()) != 0)
					{	
						order.setQty(ol.getQtyEntered());
						order.saveEx();
					}	
					if(order.getDatePromised().compareTo(ol.getDatePromised()) != 0)
					{
						order.setDatePromised(ol.getDatePromised());
						order.saveEx();
					}
				}    
			}    
		}
		
		return;
	}

	/**
	 * Create MRP record based in Manufacturing Order
	 * @param MPPOrder Manufacturing Order
	 * @param delete Indicate if this record is delete
	 */
	public static void PP_Order(MPPOrder o, boolean delete)
	{
		Properties ctx = o.getCtx();
		String trxName = o.get_TrxName();
		
		if (delete)
		{
			deleteMRP(o);
			return;
		}
		//
		// Supply
		MPPMRP mrpSupply = getQuery(o, TYPEMRP_Supply, ORDERTYPE_ManufacturingOrder).firstOnly();
		if(mrpSupply == null)
		{		                    
			mrpSupply = new MPPMRP(ctx, 0, trxName);                                                                                                                 
			mrpSupply.setAD_Org_ID(o.getAD_Org_ID());
			mrpSupply.setTypeMRP(MPPMRP.TYPEMRP_Supply);
		}
		mrpSupply.setPP_Order(o);
		mrpSupply.setQty(o.getQtyOrdered().subtract(o.getQtyDelivered()));
		mrpSupply.setM_Product_ID(o.getM_Product_ID());
		mrpSupply.setM_Warehouse_ID(o.getM_Warehouse_ID());
		mrpSupply.saveEx();
		//
		// Demand
		List<MPPMRP> mrpDemandList = getQuery(o, TYPEMRP_Demand, ORDERTYPE_ManufacturingOrder).list();
		for (MPPMRP mrpDemand : mrpDemandList)
		{
			mrpDemand.setPP_Order(o);
			mrpDemand.saveEx();
		}
	}

	/**
	 * Create MRP record based in Manufacturing Order BOM Line
	 * @param MPPOrderBOMLine Order BOM Line
	 * @param delete indicate if this record is delete
	 */
	public static void PP_Order_BOMLine(MPPOrderBOMLine obl, boolean delete)
	{        	   
		String trxName = obl.get_TrxName();
		Properties ctx = obl.getCtx();
		if (delete)
		{
			deleteMRP(obl);
			return;
		}
		//
		String typeMRP = MPPMRP.TYPEMRP_Demand;
		BigDecimal qty = obl.getQtyRequiered().subtract(obl.getQtyDelivered());
		//
		// By-Product support:
		if (obl.isComponentType(MPPOrderBOMLine.COMPONENTTYPE_ByProduct))
		{
			// TODO: need to evaluate
			deleteMRP(obl);
			return;
//			typeMRP = MPPMRP.TYPEMRP_Supply;
//			qty = qty.negate();
		}
		//
		MPPMRP mrp = getQuery(obl, typeMRP, ORDERTYPE_ManufacturingOrder).firstOnly();
		if(mrp == null)
		{
			mrp = new MPPMRP(ctx, 0, trxName);                                                                           
			mrp.setPP_Order_BOMLine_ID(obl.getPP_Order_BOMLine_ID());
		}
		mrp.setAD_Org_ID(obl.getAD_Org_ID());
		mrp.setTypeMRP(typeMRP);
		mrp.setPP_Order(obl.getParent());
		mrp.setQty(qty);
		mrp.setM_Product_ID(obl.getM_Product_ID());
		mrp.setM_Warehouse_ID(obl.getM_Warehouse_ID());
		mrp.saveEx();
	}

	/**
	 * Create MRP record based in Distribution Order
	 * @param MDDOrder Distribution Order
	 * @param delete Indicate if this record is delete
	 */
	public static void DD_Order(MDDOrder o, boolean delete)
	{        	   
		if (delete)
		{
			deleteMRP(o);
			return;
		}
	} 	

	/**
	 * Create MRP record based in Distribution Order Line
	 * @param MDDOrderLine Distribution Order Line
	 * @param delete Indicate if this record is delete
	 */
	public static void DD_Order_Line(MDDOrderLine ol, boolean delete)
	{        	   
		String trxName = ol.getParent().get_TrxName();
		Properties m_ctx = ol.getCtx();
		if (delete)
		{
			deleteMRP(ol);
			return;
		}
		//
		MPPMRP mrp = getQuery(ol, TYPEMRP_Demand, ORDERTYPE_DistributionOrder).firstOnly();
		MLocator source = MLocator.get( m_ctx , ol.getM_Locator_ID());
		MLocator target = MLocator.get( m_ctx , ol.getM_LocatorTo_ID());
		if(mrp != null)
		{	
			mrp.setAD_Org_ID(source.getAD_Org_ID());
			mrp.setName("MRP"); 
			mrp.setDescription(ol.getDescription());                            
			mrp.setQty(ol.getQtyOrdered().subtract(ol.getQtyDelivered()));
			mrp.setDatePromised(ol.getDatePromised());
			mrp.setDateOrdered(ol.getDateOrdered());
			mrp.setM_Product_ID(ol.getM_Product_ID());                           
			mrp.setM_Warehouse_ID(source.getM_Warehouse_ID()); 
			mrp.setDocStatus(ol.getParent().getDocStatus());
			mrp.saveEx();
		}
		else
		{
			mrp = new MPPMRP(m_ctx , 0 ,trxName);                              
			mrp.setAD_Org_ID(source.getAD_Org_ID());
			mrp.setName("MRP");
			mrp.setDescription(ol.getDescription());
			mrp.setDD_Order_ID(ol.getDD_Order_ID());
			mrp.setDD_OrderLine_ID(ol.getDD_OrderLine_ID());
			mrp.setQty(ol.getQtyOrdered().subtract(ol.getQtyDelivered()));
			mrp.setDatePromised(ol.getDatePromised());
			mrp.setDateOrdered(ol.getDateOrdered());
			mrp.setM_Product_ID(ol.getM_Product_ID());
			mrp.setM_Warehouse_ID(source.getM_Warehouse_ID());
			mrp.setDocStatus(ol.getParent().getDocStatus());
			mrp.setOrderType(MPPMRP.ORDERTYPE_DistributionOrder);
			mrp.setTypeMRP(MPPMRP.TYPEMRP_Demand);
			mrp.saveEx();

		}
		mrp = getQuery(ol, TYPEMRP_Supply, ORDERTYPE_DistributionOrder).firstOnly();
		if(mrp!=null)
		{	
			mrp.setAD_Org_ID(target.getAD_Org_ID());
			mrp.setName("MRP"); 
			mrp.setDescription(ol.getDescription());                            
			mrp.setQty(ol.getQtyOrdered().subtract(ol.getQtyDelivered()));
			mrp.setDatePromised(ol.getDatePromised());
			mrp.setDateOrdered(ol.getDateOrdered());
			mrp.setM_Product_ID(ol.getM_Product_ID());                           
			mrp.setM_Warehouse_ID(target.getM_Warehouse_ID()); 
			mrp.setDocStatus(ol.getParent().getDocStatus());
			mrp.save();
		}	
		else
		{	
			mrp = new MPPMRP( m_ctx , 0,trxName);
			mrp.setAD_Org_ID(target.getAD_Org_ID());
			mrp.setName("MRP");
			mrp.setDescription(ol.getDescription());
			mrp.setDD_Order_ID(ol.getDD_Order_ID());
			mrp.setDD_OrderLine_ID(ol.getDD_OrderLine_ID());
			mrp.setQty(ol.getQtyOrdered().subtract(ol.getQtyDelivered()));
			mrp.setDatePromised(ol.getDatePromised());
			mrp.setDateOrdered(ol.getDateOrdered());
			mrp.setM_Product_ID(ol.getM_Product_ID());
			mrp.setM_Warehouse_ID(target.getM_Warehouse_ID());
			mrp.setDocStatus(ol.getParent().getDocStatus());
			mrp.setOrderType(MPPMRP.ORDERTYPE_DistributionOrder);
			mrp.setTypeMRP(MPPMRP.TYPEMRP_Supply);
			mrp.saveEx();
			}                       
		return;
	}


	/**
	 * Create MRP record based in Requisition Line
	 * @param MRequisitionLine Requisition Line
	 * @param delete Indicate if this record is delete
	 */
	public static void M_RequisitionLine( MRequisitionLine rl , boolean delete)
	{
		if (delete)
		{
			deleteMRP(rl);
			return;
		}

		MPPMRP mrp = getQuery(rl, null, null).firstOnly();
		MRequisition r = rl.getParent();
		if (mrp == null)
		{
			mrp = new MPPMRP(rl.getCtx(), 0, rl.get_TrxName());  
			mrp.setM_Requisition_ID(rl.getM_Requisition_ID());
			mrp.setM_RequisitionLine_ID(rl.getM_RequisitionLine_ID());
			mrp.setOrderType(MPPMRP.ORDERTYPE_MaterialRequisition);
			mrp.setTypeMRP(MPPMRP.TYPEMRP_Supply);
			mrp.setIsAvailable(true);
		}
		mrp.setAD_Org_ID(r.getAD_Org_ID());
		mrp.setName("MRP");
		mrp.setDescription(rl.getDescription());                                                        
		mrp.setDateOrdered(r.getDateRequired());
		mrp.setDatePromised(r.getDateRequired());
		mrp.setDateStartSchedule(r.getDateRequired());
		mrp.setDateFinishSchedule(r.getDateRequired());
		mrp.setM_Product_ID(rl.getM_Product_ID());
		mrp.setM_Warehouse_ID(r.getM_Warehouse_ID());
		// We create a MRP record only for Not Ordered Qty. The Order will generate a MRP record for Ordered Qty.
		mrp.setQty(rl.getQty().subtract(rl.getQtyOrdered()));
		// MRP record for a requisition will be ALWAYS Drafted because
		// a requisition generates just Planned Orders (which is a wish list)
		// and not Scheduled Receipts
		mrp.setDocStatus(DocAction.STATUS_Drafted); 
		mrp.saveEx();
	}

	/**
	 * Get Qty Onhand
	 * @param AD_Client_ID
	 * @param M_Warehouse_ID
	 * @param M_Product_ID
	 * @return
	 */
	public static BigDecimal getQtyOnHand(Properties ctx, int M_Warehouse_ID ,int M_Product_ID,String trxName)
	{	
		final String sql = "SELECT COALESCE(bomQtyOnHand (M_Product_ID,?,0),0) FROM M_Product"
							+" WHERE AD_Client_ID=? AND M_Product_ID=?";
		return DB.getSQLValueBDEx(trxName, sql, new Object[]{M_Warehouse_ID,Env.getAD_Client_ID(ctx),M_Product_ID});
	}
	
    /**
     * Get Reserved Quantity for a Warehouse 
	 * @param ctx
	 * @param M_Warehouse_ID
	 * @param M_Product_ID
	 * @param To
	 * @param trxName
	 * @return BibDecimal
	 */
    public static BigDecimal getQtyReserved(Properties ctx, int M_Warehouse_ID ,int M_Product_ID, Timestamp To,String trxName)
	{
    	final String sql = "SELECT SUM(Qty) FROM PP_MRP WHERE "
    		+" TypeMRP=?"
    		+" AND DocStatus IN ('IN','CO')"
    		+" AND OrderType IN ('SOO','MOP','DOO')"
    		+" AND AD_Client_ID=? AND M_Warehouse_ID =? AND M_Product_ID=?"
    		+" AND DatePromised <=?";
    	BigDecimal qty = DB.getSQLValueBDEx(trxName, sql, new Object[]{
    			MPPMRP.TYPEMRP_Demand,
    			Env.getAD_Client_ID(ctx),M_Warehouse_ID, M_Product_ID,
    			To,
    	}); 		
		//	SQL may return no rows or null
		if (qty == null)
			return Env.ZERO;
		
		return qty;
     }
    
    /**
     * Get Reserved Quantity for a Warehouse 
	 * @param ctx
	 * @param M_Warehouse_ID
	 * @param M_Product_ID
	 * @param trxName
	 * @return BibDecimal
	 */
    public static BigDecimal getQtyReserved(Properties ctx, int M_Warehouse_ID ,int M_Product_ID,String trxName)
	{
    	return getQtyReserved(ctx, M_Warehouse_ID, M_Product_ID, new Timestamp (System.currentTimeMillis()), trxName);
    }
    
    /**
     * Get Reserved Quantity for a Warehouse 
	 * @param ctx
	 * @param M_Warehouse_ID
	 * @param M_Product_ID
	 * @param To
	 * @param trxName
	 * @return
	 */
    public static BigDecimal getQtyOrdered(Properties ctx, int M_Warehouse_ID ,int M_Product_ID, Timestamp To,String trxName)
	{
    	StringBuffer sql = new StringBuffer("SELECT SUM(Qty) FROM PP_MRP WHERE  TypeMRP='S' AND DocStatus IN ('IN','CO')");	
		sql.append(" AND OrderType IN ('POO','MOP','DOO') AND AD_Client_ID= ? AND DatePromised <=? AND M_Warehouse_ID =? AND M_Product_ID=?");
		BigDecimal qty = DB.getSQLValueBDEx(trxName, sql.toString(), new Object[]{Env.getAD_Client_ID(ctx), To , M_Warehouse_ID, M_Product_ID}); 		
		//	SQL may return no rows or null
		if (qty == null)
			return Env.ZERO;
		
		return qty;
     }
    
	 /**
	 * Set Order Reserved Quantity for a Warehouse 
	 * @param AD_Client_ID
	 * @param M_Warehouse_ID
	 * @param M_Product_ID
	 * @param trxName
	 * @return
	 */
   public static BigDecimal getQtyOrdered(Properties ctx, int M_Warehouse_ID ,int M_Product_ID,String trxName)
   {
		return getQtyOrdered(ctx, M_Warehouse_ID, M_Product_ID, new Timestamp (System.currentTimeMillis()), trxName);
   }
   
    
	/**
	 * Maximum Low Level Code
	 * @param ctx
	 * @param trxName
	 * @return maximum low level
	 */
	public static int getMaxLowLevel(Properties ctx, String trxName)
	{
		int AD_Client_ID = Env.getAD_Client_ID(ctx);
		//
		final String sql = "SELECT MAX("+MProduct.COLUMNNAME_LowLevel+") FROM M_Product"
							+" WHERE AD_Client_ID=? AND "+MProduct.COLUMNNAME_LowLevel+" IS NOT NULL";                      
		int LowLevel = DB.getSQLValueEx(trxName, sql, AD_Client_ID);
		return LowLevel + 1;
	}

	/**
	 * Calculated duration of given workflow, considering resource's available days and timeslot.
	 * @param ctx
	 * @param S_Resource_ID
	 * @param AD_Workflow_ID
	 * @param QtyOrdered
	 * @param trxName
	 * @return duration [days]
	 */
	public static BigDecimal getDays(Properties ctx ,int S_Resource_ID, int AD_Workflow_ID, BigDecimal QtyOrdered, String trxName)
	{
		if (S_Resource_ID <= 0)
			return Env.ZERO;

		MResource S_Resource = MResource.get(ctx, S_Resource_ID);
		MResourceType S_ResourceType = MResourceType.get(ctx, S_Resource.getS_ResourceType_ID());  	

		BigDecimal AvailableDayTime  = Env.ZERO;
		if (S_ResourceType.isTimeSlot())
			AvailableDayTime = BigDecimal.valueOf(getHoursAvailable(S_ResourceType.getTimeSlotStart(),S_ResourceType.getTimeSlotEnd()));
		else
			AvailableDayTime  = BigDecimal.valueOf(24); 

		int AvailableDays = 0;
		if (S_ResourceType.isDateSlot()) {
			if (S_ResourceType.isOnMonday())
				AvailableDays += 1; 
			if (S_ResourceType.isOnTuesday())
				AvailableDays += 1;
			if (S_ResourceType.isOnThursday())
				AvailableDays += 1;
			if (S_ResourceType.isOnWednesday())	
				AvailableDays += 1;
			if (S_ResourceType.isOnFriday())	 
				AvailableDays += 1;
			if (S_ResourceType.isOnSaturday())	
				AvailableDays += 1;
			if (S_ResourceType.isOnSunday())
				AvailableDays += 1;
		}
		else {
			AvailableDays = 7;
		}

		MWorkflow wf = MWorkflow.get(ctx, AD_Workflow_ID);
		
		BigDecimal RequiredTime = BigDecimal.valueOf (	
								(	  wf.getQueuingTime()
									+ wf.getSetupTime()
									+ (wf.getDuration() * QtyOrdered.doubleValue())
									+ wf.getWaitingTime()
									+ wf.getMovingTime()
								)
								* ( (double)wf.getDurationBaseSec() / 60 / 60 ) // convert to hours
		);
		// TODO: implement here, Victor's suggestion - https://sourceforge.net/forum/message.php?msg_id=5179460

		// Weekly Factor  	
		BigDecimal WeeklyFactor = BigDecimal.valueOf(7).divide(BigDecimal.valueOf(AvailableDays), 8, BigDecimal.ROUND_UP);

		return (RequiredTime.multiply(WeeklyFactor)).divide(AvailableDayTime, 0, BigDecimal.ROUND_UP);
	}  

	/**
	 * Return hours between time1 and time2.
	 * Minutes, secords and millis are discarded. 
	 * @param time1 Time Start
	 * @param time2 Time End
	 * @return hours between time1 and time2
	 */
	public static long getHoursAvailable (Timestamp time1, Timestamp time2)
	{
		return (time2.getTime() - time1.getTime()) / (60 * 60 * 1000);
	}


}	//	MPPMRP
