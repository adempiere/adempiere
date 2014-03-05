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
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MDocType;
import org.compiere.model.MForecast;
import org.compiere.model.MForecastLine;
import org.compiere.model.MLocator;
import org.compiere.model.MMessage;
import org.compiere.model.MNote;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPO;
import org.compiere.model.MRefList;
import org.compiere.model.MRequisition;
import org.compiere.model.MRequisitionLine;
import org.compiere.model.MResource;
import org.compiere.model.MStorage;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.model.X_M_Forecast;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;
import org.compiere.util.Util;
import org.compiere.wf.MWorkflow;
import org.eevolution.exceptions.NoPlantForWarehouseException;


/**
 * PP MRP
 *	
 * @author Victor Perez www.e-evolution.com     
 * @author Teo Sarca, www.arhipac.ro
 */
public class MPPMRP extends X_PP_MRP
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6831223361306903297L;
	
	private static CLogger s_log = CLogger.getCLogger(MPPMRP.class);
	
	public static int getM_Warehouse_ID(int  PP_MRP_ID , String trxName)
	{
		return DB.getSQLValue(trxName, "SELECT M_Warehouse_ID FROM PP_MRP mrp WHERE mrp.PP_MRP_ID=? ", PP_MRP_ID);
	}
	
	public static MPPOrder createMOMakeTo(MOrderLine ol, BigDecimal qty)
	{
		MPPOrder order = MPPOrder.forC_OrderLine_ID(ol.getCtx(), ol.getC_OrderLine_ID(), ol.getM_Product_ID(), ol.get_TrxName());
		if (order == null)
		{
			final MProduct product = MProduct.get(ol.getCtx(), ol.getM_Product_ID());
			
			final String whereClause = MPPProductBOM.COLUMNNAME_BOMType+" IN (?,?)"
						   +" AND "+MPPProductBOM.COLUMNNAME_BOMUse+"=?"
						   +" AND "+MPPProductBOM.COLUMNNAME_Value+"=?";
			
			//Search standard BOM
			MPPProductBOM bom = new Query(ol.getCtx(), MPPProductBOM.Table_Name, whereClause,ol.get_TrxName())
						.setClient_ID()
						.setParameters(
								MPPProductBOM.BOMTYPE_Make_To_Order, 
								MPPProductBOM.BOMTYPE_Make_To_Kit, 
								MPPProductBOM.BOMUSE_Manufacturing,
								product.getValue())
						.firstOnly();

			
			//Search workflow standard
			MWorkflow workflow = null;
			int workflow_id =  MWorkflow.getWorkflowSearchKey(product);
			if(workflow_id > 0)
				workflow = MWorkflow.get(ol.getCtx(), workflow_id);
			
			//Search Plant for this Warehouse
			int plant_id = 0;
			
			MPPProductPlanning pp = null;
			//Search planning data if no exist BOM or Workflow Standard
			if(bom == null || workflow == null)
			{
				plant_id = MPPProductPlanning.getPlantForWarehouse(ol.getM_Warehouse_ID());

				if(plant_id <= 0)
				{
					throw new NoPlantForWarehouseException(ol.getM_Warehouse_ID());
				}
				
				pp = MPPProductPlanning.find(ol.getCtx(), ol.getAD_Org_ID(), ol.getM_Warehouse_ID() , plant_id , ol.getM_Product_ID(), ol.get_TrxName()); 	
				if(pp == null)
					throw new AdempiereException("@NotFound@ @PP_Product_Planning_ID@");
			}
			
			//Validate BOM
			if(bom == null && pp != null)
			{
					bom = new MPPProductBOM(ol.getCtx(), pp.getPP_Product_BOM_ID(), ol.get_TrxName());
					if( bom != null
						&& !MPPProductBOM.BOMTYPE_Make_To_Order.equals(bom.getBOMType())
						&& !MPPProductBOM.BOMTYPE_Make_To_Kit.equals(bom.getBOMType()) )
					{
						throw new AdempiereException("@NotFound@ @PP_ProductBOM_ID@");
					}
			}
			
			if (workflow == null && pp != null) 
			{		
				//Validate the workflow based in planning data 						
				workflow = new MWorkflow( ol.getCtx() , pp.getAD_Workflow_ID(), ol.get_TrxName());
				
				if(workflow == null)
				{
					throw new AdempiereException("@NotFound@ @AD_Workflow_ID@");
				}
				
				
			} 
			
			if (plant_id > 0 && workflow != null)
			{
				String description = Msg.translate(ol.getCtx(),MRefList.getListName(ol.getCtx(), MPPOrderBOM.BOMTYPE_AD_Reference_ID, bom.getBOMType())) 
				+ " "
				+ Msg.translate(ol.getCtx(), MOrder.COLUMNNAME_C_Order_ID) 
				+ " : "
				+ ol.getParent().getDocumentNo();					
				// Create temporary data planning to create Manufacturing Order
				pp = new MPPProductPlanning(ol.getCtx(), 0 , ol.get_TrxName());
				pp.setAD_Org_ID(ol.getAD_Org_ID());
				pp.setM_Product_ID(product.getM_Product_ID());
				pp.setPlanner_ID(ol.getParent().getSalesRep_ID());
				pp.setPP_Product_BOM_ID(bom.getPP_Product_BOM_ID());
				pp.setAD_Workflow_ID(workflow.getAD_Workflow_ID());
				pp.setM_Warehouse_ID(ol.getM_Warehouse_ID());
				pp.setS_Resource_ID(plant_id);
				
				order = MPPMRP.createMO(pp, ol.getC_OrderLine_ID(),ol.getM_AttributeSetInstance_ID(), 
										qty, ol.getDateOrdered(), ol.getDatePromised(), description);
				
				description = "";
				if(ol.getDescription() != null)
					description = ol.getDescription();
				
				description = description + " " + Msg.translate(ol.getCtx(),MRefList.getListName(ol.getCtx(), MPPOrderBOM.BOMTYPE_AD_Reference_ID, bom.getBOMType())) 
							+ " "
							+ Msg.translate(ol.getCtx(), MPPOrder.COLUMNNAME_PP_Order_ID) 
							+ " : "
							+ order.getDocumentNo();
				
				ol.setDescription(description);
				ol.saveEx();
			}
		}
		else
		{    
			if (!order.isProcessed())
			{
				//if you chance product in order line the Manufacturing order is void
				if(order.getM_Product_ID() != ol.getM_Product_ID())
				{
					order.setDescription("");
					order.setQtyEntered(Env.ZERO);
					order.setC_OrderLine_ID(0);
					order.voidIt();
					order.setDocStatus(MPPOrder.DOCSTATUS_Voided);
					order.setDocAction(MPPOrder.ACTION_None);
					order.save();
					ol.setDescription("");
					ol.saveEx();
					
				}
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
	return order;
	}
	
	/**
	 * Create Manufacturing Order base on Planning Data
	 * @param pp Product Planning 
	 * @param C_OrderLine_ID Sales Order Line
	 * @param M_AttributeSetInstance_ID ASI
	 * @param qty Quantity 
	 * @param dateOrdered Data Ordered
	 * @param datePromised Data Promised
	 * @param description Order Description
	 * @return Manufacturing Order or null
	 */
	public static MPPOrder createMO(MPPProductPlanning pp,int C_OrderLine_ID,int M_AttributeSetInstance_ID , BigDecimal qty, 
									Timestamp dateOrdered,Timestamp datePromised,String description)
	{
	
		MPPProductBOM bom = pp.getPP_Product_BOM();
		MWorkflow wf = pp.getAD_Workflow();
		
		if (pp.getS_Resource_ID() > 0 && bom != null && wf != null)
		{
			//RoutingService routingService = RoutingServiceFactory.get().getRoutingService(pp.getCtx());
			//int duration = routingService.calculateDuration(wf,MResource.get(pp.getCtx(), pp.getS_Resource_ID()),qty).intValueExact(); 
			int duration = MPPMRP.getDurationDays(qty, pp);

			MPPOrder order = new MPPOrder(pp.getCtx(), 0 , pp.get_TrxName());
			order.setAD_Org_ID(pp.getAD_Org_ID());
			order.setDescription(description);
			order.setC_OrderLine_ID(C_OrderLine_ID);
			order.setS_Resource_ID(pp.getS_Resource_ID());
			order.setM_Warehouse_ID(pp.getM_Warehouse_ID());
			order.setM_Product_ID(pp.getM_Product_ID());
			order.setM_AttributeSetInstance_ID(M_AttributeSetInstance_ID);
			order.setPP_Product_BOM_ID(pp.getPP_Product_BOM_ID());
			order.setAD_Workflow_ID(pp.getAD_Workflow_ID());
			order.setPlanner_ID(pp.getPlanner_ID());
			order.setLine(10);
			order.setDateOrdered(dateOrdered);                       
			order.setDatePromised(datePromised);
			order.setDateStartSchedule(TimeUtil.addDays(datePromised, 0 - duration));
			order.setDateFinishSchedule(datePromised);
			order.setC_UOM_ID(pp.getM_Product().getC_UOM_ID());
			order.setQty(qty);
			order.setPriorityRule(MPPOrder.PRIORITYRULE_High);                                
			order.saveEx();  
			order.setDocStatus(order.prepareIt());
			order.setDocAction(MPPOrder.ACTION_Complete);
			order.saveEx();
			return order;				
		}    
		return null;
	}
	
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
				MOrderLine.COLUMNNAME_C_UOM_ID,
				MOrderLine.COLUMNNAME_QtyOrdered,
				MOrderLine.COLUMNNAME_QtyDelivered,
		});
		s_sourceColumnNames.put(MRequisition.Table_Name, new String[]{
				MRequisition.COLUMNNAME_DateRequired,
				MRequisition.COLUMNNAME_M_Warehouse_ID,
				//MRequisition.COLUMNNAME_DocStatus, // not needed
		});
		s_sourceColumnNames.put(MRequisitionLine.Table_Name, new String[]{
				"AD_Org_ID",
				MRequisitionLine.COLUMNNAME_M_Product_ID,
				MRequisitionLine.COLUMNNAME_Qty,
				MRequisitionLine.COLUMNNAME_C_OrderLine_ID, // QtyOrdered depends on that
		});
		s_sourceColumnNames.put(MForecast.Table_Name, new String[]{
				MForecast.COLUMNNAME_Processed
		});
		s_sourceColumnNames.put(MForecastLine.Table_Name, new String[]{
				"AD_Org_ID",
				MForecastLine.COLUMNNAME_DatePromised,
				MForecastLine.COLUMNNAME_M_Warehouse_ID,
				MForecastLine.COLUMNNAME_M_Product_ID,
				MForecastLine.COLUMNNAME_Qty,
		});
		s_sourceColumnNames.put(MDDOrder.Table_Name, new String[]{
				MDDOrder.COLUMNNAME_DocStatus,
				MDDOrder.COLUMNNAME_C_BPartner_ID
		});
		s_sourceColumnNames.put(MDDOrderLine.Table_Name, new String[]{
				"AD_Org_ID",
				MDDOrderLine.COLUMNNAME_M_Product_ID,
				MDDOrderLine.COLUMNNAME_C_UOM_ID,
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
				MPPOrder.COLUMNNAME_C_UOM_ID,
				MPPOrder.COLUMNNAME_DatePromised,
				MPPOrder.COLUMNNAME_QtyOrdered,
				MPPOrder.COLUMNNAME_DateStartSchedule,
				MPPOrder.COLUMNNAME_DateFinishSchedule,
				MPPOrder.COLUMNNAME_QtyDelivered,
				MPPOrder.COLUMNNAME_PP_Product_BOM_ID,
				MPPOrder.COLUMNNAME_AD_Workflow_ID,
				MPPOrder.COLUMNNAME_DocStatus,
		});
		s_sourceColumnNames.put(MPPOrderBOMLine.Table_Name, new String[]{
				MPPOrderBOMLine.COLUMNNAME_M_Product_ID,
				MPPOrderBOMLine.COLUMNNAME_C_UOM_ID,
				MPPOrderBOMLine.COLUMNNAME_M_Warehouse_ID,
				MPPOrderBOMLine.COLUMNNAME_QtyEntered,
				MPPOrderBOMLine.COLUMNNAME_QtyDelivered,
				MPPOrderBOMLine.COLUMNNAME_QtyRequired,
		});
		//MRP net change
		s_sourceColumnNames.put(MPPProductPlanning.Table_Name, new String[]{
				MPPProductPlanning.COLUMNNAME_M_Product_ID,
				MPPProductPlanning.COLUMNNAME_S_Resource_ID,
				MPPProductPlanning.COLUMNNAME_IsActive,
				MPPProductPlanning.COLUMNNAME_Planner_ID,
				MPPProductPlanning.COLUMNNAME_PP_Product_BOM_ID,
				MPPProductPlanning.COLUMNNAME_IsPhantom,
				MPPProductPlanning.COLUMNNAME_AD_Workflow_ID,
				MPPProductPlanning.COLUMNNAME_DD_NetworkDistribution_ID,
				MPPProductPlanning.COLUMNNAME_IsMPS,
				MPPProductPlanning.COLUMNNAME_IsCreatePlan,
				MPPProductPlanning.COLUMNNAME_TimeFence,
				MPPProductPlanning.COLUMNNAME_DeliveryTime_Promised,
				MPPProductPlanning.COLUMNNAME_TransferTime,
				MPPProductPlanning.COLUMNNAME_Order_Policy,
				MPPProductPlanning.COLUMNNAME_Order_Period,
				MPPProductPlanning.COLUMNNAME_Order_Qty,
				MPPProductPlanning.COLUMNNAME_Order_Min,
				MPPProductPlanning.COLUMNNAME_Order_Max,
				MPPProductPlanning.COLUMNNAME_Order_Pack,
				MPPProductPlanning.COLUMNNAME_SafetyStock,
				MPPProductPlanning.COLUMNNAME_Yield,
		});
		//MRP net change
		s_sourceColumnNames.put(MProductPO.Table_Name, new String[]{
				MProductPO.COLUMNNAME_IsCurrentVendor,
				MProductPO.COLUMNNAME_DeliveryTime_Promised,
				MProductPO.COLUMNNAME_IsActive,
				MProductPO.COLUMNNAME_Order_Min,
				MProductPO.COLUMNNAME_Order_Pack,
		});
		//MRP net change
		s_sourceColumnNames.put(MProduct.Table_Name, new String[]{
				MProduct.COLUMNNAME_IsActive,
				MProduct.COLUMNNAME_C_UOM_ID,
				MProduct.COLUMNNAME_IsBOM,
				MProduct.COLUMNNAME_IsPurchased,
				MProduct.COLUMNNAME_IsVerified,
				MProduct.COLUMNNAME_IsStocked,
				MProduct.COLUMNNAME_ProductType
		});
		//MRP net change
		s_sourceColumnNames.put(MStorage.Table_Name, new String[]{
				MStorage.COLUMNNAME_QtyOnHand,
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
		if (po.is_new()
				|| po.is_ValueChanged("IsActive"))
		{
			return true;
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
	
	/**
	 * 
	 * @return MRP Source tables
	 */
	public static Collection<String> getSourceTableNames()
	{
		return s_sourceColumnNames.keySet();
	}
	
	public static void setIsRequired(PO po, String type, boolean isRequiredMRP, String trxName) {

		int M_Product_ID = po
				.get_ValueAsInt(MPPProductPlanning.COLUMNNAME_M_Product_ID);
		int M_Warehouse_ID = po
				.get_ValueAsInt(MPPProductPlanning.COLUMNNAME_M_Warehouse_ID);
		
		if(M_Warehouse_ID <= 0)
		{
			int M_Locator_ID = po
					.get_ValueAsInt(MStorage.COLUMNNAME_M_Locator_ID);
			if (M_Locator_ID > 0)
				M_Warehouse_ID = DB
						.getSQLValue(
								trxName,
								"SELECT M_Warehouse_ID FROM M_Locator WHERE M_Locator_ID=?",
								M_Locator_ID);
		}
			

		if (M_Product_ID <= 0)
			return;

		StringBuilder sql = new StringBuilder();
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(isRequiredMRP);
		parameters.add(po.getAD_Client_ID());

		sql.append("UPDATE PP_Product_Planning SET ").append(type).append("=?")
				.append(" WHERE ")
				.append(MPPProductPlanning.COLUMNNAME_AD_Client_ID)
				.append("=? AND ");

		if (po.getAD_Org_ID() > 0) {
			sql.append(MPPProductPlanning.COLUMNNAME_AD_Org_ID).append(
					"=? AND ");
			parameters.add(po.getAD_Org_ID());
		}
		if (M_Warehouse_ID > 0) {
			sql.append(MPPProductPlanning.COLUMNNAME_M_Warehouse_ID).append(
					"=? AND ");
			parameters.add(M_Warehouse_ID);
		}

		sql.append(MPPProductPlanning.COLUMNNAME_M_Product_ID).append("=? ");
		parameters.add(M_Product_ID);

		DB.executeUpdateEx(sql.toString(), parameters.toArray(),
				trxName);
	}
	
	public static void deleteMRP(PO po)
	{
		String tableName = po.get_TableName();
		int no = DB.executeUpdateEx("DELETE FROM "+Table_Name+" WHERE "+tableName+"_ID=? AND AD_Client_ID=?",
				new Object[]{po.get_ID(), po.getAD_Client_ID()},
				po.get_TrxName());
		s_log.finest("Deleted "+tableName+" #"+no);
		
		// Delete generated manufacturing order
		if (po instanceof MOrderLine)
		{
			MOrderLine ol = (MOrderLine)po;
			MPPOrder order = MPPOrder.forC_OrderLine_ID(ol.getCtx(), ol.getC_OrderLine_ID(), ol.getM_Product_ID() ,  ol.get_TrxName());
			if (order != null && !order.isProcessed())
			{
				order.deleteEx(true);
			}
		}
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
		// In case we query for PP_Order, we need to assure that no BOM Lines records are returned.
		// The TypeMRP=D/S filter is not enough since a BOM Line can produce a supply (e.g. co-product)
		if (po instanceof MPPOrder && TYPEMRP_Supply.equals(typeMRP))
		{
			whereClause.append(" AND ").append(COLUMNNAME_PP_Order_BOMLine_ID).append(" IS NULL");
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
			setValue("MRP");
			setName("MRP");
			setDateSimulation(new Timestamp (System.currentTimeMillis()));
			//
			// The available flag should be handled by MRP engine. Initial it should be disabled.
			// see : [ 2593359 ] Calculate Material Plan error related to MRP-060 notice
			//		 https://sourceforge.net/tracker/?func=detail&atid=934929&aid=2593359&group_id=176962
			setIsAvailable(false);
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
		setPriority(o.getPriorityRule());
		setPlanner_ID(o.getPlanner_ID());
		setC_Project_ID(o.getC_Project_ID());
		setC_ProjectPhase_ID(o.getC_ProjectPhase_ID());
		setC_ProjectTask_ID(o.getC_ProjectTask_ID());
		setS_Resource_ID(o.getS_Resource_ID());
		setDocStatus(o.getDocStatus());
	}
	
	public void setC_Order(MOrder o)
	{
		setC_Order_ID(o.get_ID());
		setC_BPartner_ID(o.getC_BPartner_ID());
		setDocStatus(o.getDocStatus());
		setPriority(o.getPriorityRule());
		setPlanner_ID(o.getSalesRep_ID());
		setC_Project_ID(o.getC_Project_ID());
		//setC_ProjectPhase_ID(o.getC_ProjectPhase_ID());
		//setC_ProjectTask_ID(o.getC_ProjectTask_ID());
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
	
	public void setDD_Order(MDDOrder o)
	{
		setDD_Order_ID(o.get_ID());
		setC_BPartner_ID(o.getC_BPartner_ID());
		setPlanner_ID(o.getSalesRep_ID());
		setPriority(o.getPriorityRule());
		setC_Project_ID(o.getC_Project_ID());
		/*setC_ProjectPhase_ID(o.getC_ProjectPhase_ID());
		setC_ProjectTask_ID(o.getC_ProjectTask_ID());*/
		setDocStatus(o.getDocStatus());

	}
	public void setM_Requisition(MRequisition r)
	{
		setM_Requisition_ID(r.get_ID());
		setOrderType(MPPMRP.ORDERTYPE_MaterialRequisition);
		setTypeMRP(MPPMRP.TYPEMRP_Supply);
		//
		//setAD_Org_ID(r.getAD_Org_ID());
		setDateOrdered(r.getDateDoc());
		setDatePromised(r.getDateRequired());
		setDateStartSchedule(r.getDateDoc());
		setDateFinishSchedule(r.getDateRequired());
		setM_Warehouse_ID(r.getM_Warehouse_ID());
		setPriority(r.getPriorityRule());
		setPlanner_ID(r.getAD_User_ID());
		/*setC_Project_ID(r.getC_Project_ID());
		setC_ProjectPhase_ID(r.getC_ProjectPhase_ID());
		setC_ProjectTask_ID(r.getC_ProjectTask_ID());*/
		setDocStatus(r.getDocStatus());
	}
	
	public void setM_Forecast(X_M_Forecast f)
	{
		setOrderType(MPPMRP.ORDERTYPE_Forecast);
		setTypeMRP(MPPMRP.TYPEMRP_Demand);                         
		setM_Forecast_ID(f.getM_Forecast_ID());
		setDescription(f.getDescription());
		setC_Project_ID(f.getC_Project_ID());
		setC_ProjectPhase_ID(f.getC_ProjectPhase_ID());
	}
	
	/**
	 * @return true if the document is released
	 */
	public boolean isReleased()
	{
		String docStatus = getDocStatus();
		if (docStatus == null)
			return false;
		return DOCSTATUS_InProgress.equals(docStatus)
				|| DOCSTATUS_Completed.equals(docStatus);
	}

	/**
	 * Create MRP record based in Forecast 
	 * @param MForecast Forecast
	 */
	public static void M_Forecast(X_M_Forecast f)
	{
		List<MPPMRP> list = getQuery(f, null, null).list();
		for (MPPMRP mrp : list)
		{
			mrp.setM_Forecast(f);
		}
	}

	/**
	 * Create MRP record based in Forecast Line 
	 * @param MForecastLine Forecast Line
	 */
	public static void M_ForecastLine(MForecastLine fl)
	{
		String trxName = fl.get_TrxName();
		Properties ctx = fl.getCtx();
		
		X_M_Forecast f = new X_M_Forecast(ctx, fl.getM_Forecast_ID(), trxName);
		MPPMRP mrp = getQuery(fl, null, null).firstOnly();
		if (mrp == null)
		{
			mrp = new MPPMRP(ctx, 0, trxName);     
			mrp.setM_ForecastLine_ID(fl.getM_ForecastLine_ID());
		}
		mrp.setM_Forecast(f);
		mrp.setName("MRP");
		mrp.setAD_Org_ID(fl.getAD_Org_ID());
		mrp.setDatePromised(fl.getDatePromised());
		mrp.setDateStartSchedule(fl.getDatePromised());
		mrp.setDateFinishSchedule(fl.getDatePromised());
		mrp.setDateOrdered(fl.getDatePromised());
		mrp.setM_Warehouse_ID(fl.getM_Warehouse_ID());
		mrp.setM_Product_ID(fl.getM_Product_ID());
		mrp.setQty(fl.getQty());  
		mrp.setDocStatus(DocAction.STATUS_InProgress);
		mrp.saveEx();
	}
	
	/**
	 * Create MRP record based in Order 
	 * @param MOrder
	 */
	public static void C_Order(MOrder o)
	{
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
					C_OrderLine(line);
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
	 */
	public static void C_OrderLine(MOrderLine ol)
	{
		if(ol.isConsumesForecast())
			return ;
		
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
		mrp.setDatePromised(ol.getDatePromised());
		mrp.setDateStartSchedule(ol.getDatePromised());
		mrp.setDateFinishSchedule(ol.getDatePromised());
		mrp.setDateOrdered(ol.getDateOrdered());
		mrp.setM_Warehouse_ID(ol.getM_Warehouse_ID());
		mrp.setM_Product_ID(ol.getM_Product_ID());
		mrp.setQty(ol.getQtyOrdered().subtract(ol.getQtyDelivered()));
		mrp.saveEx();

		MOrder o = ol.getParent();
		MDocType dt = MDocType.get(o.getCtx(), o.getC_DocTypeTarget_ID());
		String DocSubTypeSO = dt.getDocSubTypeSO();
		MProduct product = (MProduct) ol.getM_Product();
		if (MDocType.DOCSUBTYPESO_StandardOrder.equals(DocSubTypeSO)
				&& product.isBOM()
				&& !product.isPurchased()
				&& IsProductMakeToOrder(ol.getCtx(), ol.getM_Product_ID(),
						ol.get_TrxName())) {
			MPPMRP.createMOMakeTo(ol, ol.getQtyOrdered());
		}
		
		return;
	}
	
	//TODO: move MPPProductBOM
	public static boolean IsProductMakeToOrder(Properties ctx,int M_Product_ID , String trxName) {
		final String whereClause = MPPProductBOM.COLUMNNAME_BOMType+" IN (?,?)"
					   +" AND "+MPPProductBOM.COLUMNNAME_BOMUse+"=?"
					   +" AND "+MPPProductBOM.COLUMNNAME_M_Product_ID+"=?";
		return new Query(ctx, MPPProductBOM.Table_Name, whereClause,trxName)
		.setClient_ID()
		.setParameters(
				MPPProductBOM.BOMTYPE_Make_To_Order, 
				MPPProductBOM.BOMTYPE_Make_To_Kit, 
				MPPProductBOM.BOMUSE_Manufacturing,
				M_Product_ID)
		.match();
	}

	/**
	 * Create MRP record based in Manufacturing Order
	 * @param MPPOrder Manufacturing Order
	 */
	public static void PP_Order(MPPOrder o)
	{
		Properties ctx = o.getCtx();
		String trxName = o.get_TrxName();
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
		mrpSupply.setPriority(o.getPriorityRule());
		mrpSupply.setPlanner_ID(o.getPlanner_ID());
		mrpSupply.setM_Product_ID(o.getM_Product_ID());
		mrpSupply.setM_Warehouse_ID(o.getM_Warehouse_ID());
		mrpSupply.setQty(o.getQtyOrdered().subtract(o.getQtyDelivered()));
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
	 */
	public static void PP_Order_BOMLine(MPPOrderBOMLine obl)
	{        	   
		String trxName = obl.get_TrxName();
		Properties ctx = obl.getCtx();
		//
		String typeMRP = MPPMRP.TYPEMRP_Demand;
		BigDecimal qty = obl.getQtyRequired().subtract(obl.getQtyDelivered());
		if (obl.isCoProduct() || obl.isByProduct())
		{
			typeMRP = MPPMRP.TYPEMRP_Supply;
			qty = qty.negate();
		}
		//
		MPPMRP mrp = getQuery(obl, null, ORDERTYPE_ManufacturingOrder).firstOnly();
		if(mrp == null)
		{
			mrp = new MPPMRP(ctx, 0, trxName);                                                                           
			mrp.setPP_Order_BOMLine_ID(obl.getPP_Order_BOMLine_ID());
		}
		MPPOrder o = obl.getParent();
		mrp.setAD_Org_ID(obl.getAD_Org_ID());
		mrp.setTypeMRP(typeMRP);
		mrp.setPP_Order(o);
		mrp.setM_Warehouse_ID(obl.getM_Warehouse_ID());
		mrp.setM_Product_ID(obl.getM_Product_ID());
		mrp.setQty(qty);
		mrp.saveEx();
	}

	/**
	 * Create MRP record based in Distribution Order
	 * @param MDDOrder Distribution Order
	 */
	public static void DD_Order(MDDOrder o)
	{   
		if((MDDOrder.DOCSTATUS_InProgress.equals(o.getDocStatus())
		||  MDDOrder.DOCSTATUS_Completed.equals(o.getDocStatus())))
		{
			for(MDDOrderLine line : o.getLines())
			{
				DD_OrderLine(line);
			}
		}
		
		if (o.is_ValueChanged(MDDOrder.COLUMNNAME_DocStatus)
		||  o.is_ValueChanged(MDDOrder.COLUMNNAME_C_BPartner_ID))
		{
			List<MPPMRP> list = getQuery(o, null, null).list();
			for (MPPMRP mrp : list)
			{
				mrp.setDD_Order(o);
				mrp.saveEx();
			}
		}
	} 	

	/**
	 * Create MRP record based in Distribution Order Line
	 * @param MDDOrderLine Distribution Order Line
	 */
	public static void DD_OrderLine(MDDOrderLine ol)
	{        	   
		String trxName = ol.get_TrxName();
		Properties m_ctx = ol.getCtx();
		//
		MPPMRP mrp = getQuery(ol, TYPEMRP_Demand, ORDERTYPE_DistributionOrder).firstOnly();
		MLocator source = MLocator.get( m_ctx , ol.getM_Locator_ID());
		MLocator target = MLocator.get( m_ctx , ol.getM_LocatorTo_ID());
		if(mrp != null)
		{	
			mrp.setAD_Org_ID(source.getAD_Org_ID());
			mrp.setName("MRP");
			mrp.setDD_Order(ol.getParent());
			mrp.setDescription(ol.getDescription());                            
			mrp.setDatePromised(ol.getDatePromised());
			mrp.setDateOrdered(ol.getDateOrdered());
			mrp.setDateStartSchedule(ol.getDateOrdered());
			mrp.setM_Warehouse_ID(source.getM_Warehouse_ID()); 
			mrp.setM_Product_ID(ol.getM_Product_ID());                           
			mrp.setQty(ol.getQtyOrdered().subtract(ol.getQtyDelivered()));
			mrp.saveEx();
		}
		else
		{
			mrp = new MPPMRP(m_ctx , 0 ,trxName);                              
			mrp.setAD_Org_ID(source.getAD_Org_ID());
			mrp.setName("MRP");
			mrp.setDescription(ol.getDescription());
			mrp.setDD_Order(ol.getParent());
			mrp.setDD_OrderLine_ID(ol.getDD_OrderLine_ID());
			mrp.setDatePromised(ol.getDatePromised());
			mrp.setDateOrdered(ol.getDateOrdered());
			mrp.setDateStartSchedule(ol.getDateOrdered());
			mrp.setM_Warehouse_ID(source.getM_Warehouse_ID());
			mrp.setM_Product_ID(ol.getM_Product_ID());
			mrp.setQty(ol.getQtyOrdered().subtract(ol.getQtyDelivered()));
			mrp.setOrderType(MPPMRP.ORDERTYPE_DistributionOrder);
			mrp.setTypeMRP(MPPMRP.TYPEMRP_Demand);
			mrp.saveEx();

		}
		mrp = getQuery(ol, TYPEMRP_Supply, ORDERTYPE_DistributionOrder).firstOnly();
		if(mrp != null)
		{	
			mrp.setAD_Org_ID(target.getAD_Org_ID());
			mrp.setName("MRP");
			mrp.setDD_Order(ol.getParent());
			mrp.setDescription(ol.getDescription());                            
			mrp.setDatePromised(ol.getDatePromised());
			mrp.setDateOrdered(ol.getDateOrdered());
			mrp.setM_Product_ID(ol.getM_Product_ID());                           
			mrp.setM_Warehouse_ID(target.getM_Warehouse_ID()); 
			mrp.setQty(ol.getQtyOrdered().subtract(ol.getQtyDelivered()));
			mrp.saveEx();
		}	
		else
		{	
			mrp = new MPPMRP( m_ctx , 0,trxName);
			mrp.setAD_Org_ID(target.getAD_Org_ID());
			mrp.setName("MRP");
			mrp.setDescription(ol.getDescription());
			mrp.setDD_Order(ol.getParent());
			mrp.setDD_OrderLine_ID(ol.getDD_OrderLine_ID());
			mrp.setDatePromised(ol.getDatePromised());
			mrp.setDateOrdered(ol.getDateOrdered());
			mrp.setM_Product_ID(ol.getM_Product_ID());
			mrp.setM_Warehouse_ID(target.getM_Warehouse_ID());
			mrp.setQty(ol.getQtyOrdered().subtract(ol.getQtyDelivered()));
			mrp.setOrderType(MPPMRP.ORDERTYPE_DistributionOrder);
			mrp.setTypeMRP(MPPMRP.TYPEMRP_Supply);
			mrp.saveEx();
			}                       
		return;
	}

	/**
	 * Create MRP record based in Requisition Line
	 * @param MRequisitionLine Requisition Line
	 */
	public static void M_Requisition(MRequisition r)
	{
		List<MPPMRP> mrpList = getQuery(r, null, null).list();
		for (MPPMRP mrp : mrpList)
		{
			mrp.setM_Requisition(r);
			mrp.saveEx();
		}
	}

	/**
	 * Create MRP record based in Requisition Line
	 * @param MRequisitionLine Requisition Line
	 */
	public static void M_RequisitionLine(MRequisitionLine rl)
	{
		MPPMRP mrp = getQuery(rl, null, null).firstOnly();
		MRequisition r = rl.getParent();
		if (mrp == null)
		{
			mrp = new MPPMRP(rl.getCtx(), 0, rl.get_TrxName());  
			mrp.setM_Requisition_ID(rl.getM_Requisition_ID());
			mrp.setM_RequisitionLine_ID(rl.getM_RequisitionLine_ID());
		}
		mrp.setM_Requisition(r);
		mrp.setAD_Org_ID(rl.getAD_Org_ID());
		mrp.setName("MRP");
		mrp.setDescription(rl.getDescription());                                                        
		mrp.setM_Product_ID(rl.getM_Product_ID());
		mrp.setC_BPartner_ID(rl.getC_BPartner_ID());
		mrp.setPriority(rl.getParent().getPriorityRule());
		// We create a MRP record only for Not Ordered Qty. The Order will generate a MRP record for Ordered Qty.
		mrp.setQty(rl.getQty().subtract(rl.getQtyOrdered()));
		// MRP record for a requisition will be ALWAYS Drafted because
		// a requisition generates just Planned Orders (which is a wish list)
		// and not Scheduled Receipts
		// mrp.setDocStatus(DocAction.STATUS_Drafted); 
		mrp.saveEx();
	}

	/**
	 * @param product
	 * @return true if there are MRP records for given product 
	 */
	public static boolean hasProductRecords(MProduct product)
	{
		final String whereClause = COLUMNNAME_M_Product_ID+"=?"
									+" AND "+COLUMNNAME_Qty+"<>0";
		return new Query(product.getCtx(), Table_Name, whereClause, product.get_TrxName())
			.setParameters(new Object[]{product.getM_Product_ID()})
			.match();
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
    public static BigDecimal getQtyReserved(Properties ctx, int M_Warehouse_ID ,int M_Product_ID, Timestamp To, String trxName)
	{
    	final String sql = "SELECT SUM(Qty) FROM PP_MRP WHERE "
    		+" TypeMRP=?"
    		+" AND DocStatus IN ('IP','CO')"
    		//+" AND OrderType IN ('SOO','MOP','DOO')"
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
    public static BigDecimal getQtyOrdered(Properties ctx, int M_Warehouse_ID ,int M_Product_ID, Timestamp To, String trxName)
	{
    	final String sql = "SELECT SUM(Qty) FROM PP_MRP WHERE "
    				+" TypeMRP='S' AND DocStatus IN ('IP','CO')"	
    				//+" AND OrderType IN ('POO','MOP','DOO')"
    				+" AND AD_Client_ID=?"
    				+" AND DatePromised <=?"
    				+" AND M_Warehouse_ID =? AND M_Product_ID=?";
		BigDecimal qty = DB.getSQLValueBDEx(trxName, sql,
				new Object[]{Env.getAD_Client_ID(ctx), To , M_Warehouse_ID, M_Product_ID}); 		
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
	 * Duration to have this Qty available (i.e. Lead Time + Transfer Time)
	 * @param qty quantity
	 * @param pp product planning sheet 
	 * @return return duration [days]
	 */
	public static int getDurationDays(BigDecimal qty, I_PP_Product_Planning pp)
	{
		Properties ctx = null;
		if (pp instanceof PO)
		{
			ctx = ((PO)pp).getCtx();
		}
		else
		{
			ctx = Env.getCtx();
		}
		
		MProduct product = MProduct.get(ctx, pp.getM_Product_ID());
		BigDecimal leadtime = pp.getDeliveryTime_Promised();
		if (leadtime.signum() != 0 || product.isPurchased())
		{
			;
		}
		else if (pp.getS_Resource_ID() > 0 && pp.getAD_Workflow_ID() > 0)
		{
			RoutingService routingService = RoutingServiceFactory.get().getRoutingService(ctx);
			leadtime = routingService.calculateDuration(pp.getAD_Workflow(), pp.getS_Resource(), qty);
			// TODO: convert to days
		}
		else
		{
			throw new AdempiereException("Cannot calculate leadtime for "+pp); // TODO: translate or create notice?
		}
		return leadtime.add(pp.getTransferTime()).intValue();
	}

	public static String getDocumentNo(int PP_MRP_ID)
	{
		return DB.getSQLValueStringEx(null, "SELECT documentNo(PP_MRP_ID) AS DocumentNo FROM PP_MRP WHERE PP_MRP_ID = ?", PP_MRP_ID);
	}
	
	public static int getDocType(Properties ctx , String docBaseType, int AD_Org_ID,int Planner_ID, String trxName)
	{
		MDocType[] docs = MDocType.getOfDocBaseType(ctx, docBaseType);

		if (docs == null || docs.length == 0) 
		{
			String reference = Msg.getMsg(ctx, "SequenceDocNotFound");
			String textMsg = "Not found default document type for docbasetype "+ docBaseType;
			MNote note = new MNote(ctx, MMessage.getAD_Message_ID (ctx, "SequenceDocNotFound"),
									Planner_ID, MPPMRP.Table_ID, 0,
									reference,
									textMsg,
									trxName);
			note.setAD_Org_ID(AD_Org_ID);
			note.saveEx();
			throw new AdempiereException(textMsg);
		} 
		else
		{
			for(MDocType doc:docs)
			{
				if(doc.getAD_Org_ID()==AD_Org_ID)
				{
					return doc.getC_DocType_ID();
				}
			}
			//log.info("Doc Type for "+docBaseType+": "+ docs[0].getC_DocType_ID());
			return docs[0].getC_DocType_ID();
		}
	}

	public String toString()
	{
		String description = getDescription();
		return getClass().getSimpleName()+"["
			+" M_Product_ID=" + getM_Product_ID()	
			+", TypeMRP="+getTypeMRP()
			+", DocStatus="+getDocStatus()
			+", Qty="+getQty()
			+", DatePromised="+getDatePromised()
			+", Schedule="+getDateStartSchedule()+"/"+getDateFinishSchedule()
			+", IsAvailable="+isAvailable()
			+(!Util.isEmpty(description, true) ? ", Description="+description : "")
			+", ID="+get_ID()
			+"]";
	}

}	//	MPPMRP
