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
import java.util.List;
import java.util.Properties;

import org.compiere.model.MDocType;
import org.compiere.model.MLocator;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.MRequisition;
import org.compiere.model.MRequisitionLine;
import org.compiere.model.MResource;
import org.compiere.model.MResourceType;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.model.X_C_DocType;
import org.compiere.model.X_M_Forecast;
import org.compiere.model.X_M_ForecastLine;
import org.compiere.process.DocAction;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Util;
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

	/**************************************************************************
	 * 	Default Constructor
	 *	@param ctx context
	 *	@param PP_MRP_ID id
	 *	@param trxName Transaction Name
	 */
	public MPPMRP(Properties ctx, int PP_MRP_ID,String trxName)
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
	public MPPMRP(Properties ctx, ResultSet rs,String trxName)
	{
		super(ctx, rs , trxName);
	}                

	protected boolean afterSave(boolean newRecord, boolean success) 
	{

		if (!newRecord)
			return success;         
		return true;
	}

	/**
	 * Create MRP record based in Forecast Line 
	 * @param X_M_ForecastLine Forecast Line
	 * @param delete Indicate if this record is delete
	 */
	public static void M_ForecastLine(X_M_ForecastLine fl, boolean delete)
	{
		String trxName = fl.get_TrxName();
		Properties ctx =fl.getCtx();
		if (delete)
		{
			final String sql = "DELETE FROM PP_MRP WHERE M_ForecastLine_ID=? AND AD_Client_ID=?";
			DB.executeUpdateEx(sql, new Object[]{fl.get_ID(), fl.getAD_Client_ID()}, trxName);
			return;
		}

		X_M_Forecast f = new X_M_Forecast(ctx, fl.getM_Forecast_ID(), trxName);
		MPPMRP mrp = new Query(ctx, MPPMRP.Table_Name, "M_ForecastLine_ID=?", trxName)
							.setParameters(new Object[]{fl.get_ID()})
							.first();
		if (mrp == null)
		{
			mrp = new MPPMRP(ctx, 0, trxName);                                                          
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
		mrp.setDateStartSchedule(mrp.getDatePromised());
		mrp.setDateFinishSchedule(mrp.getDatePromised());
		mrp.setDateOrdered(mrp.getDatePromised());
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
		String trxName = o.get_TrxName();
		final String whereClause = COLUMNNAME_C_Order_ID+"=? AND AD_Client_ID=?";
		Object[] params = new Object[]{o.get_ID(), o.getAD_Client_ID()};
		if (delete)
		{
			final String sql = "DELETE FROM PP_MRP WHERE "+whereClause;
			DB.executeUpdateEx(sql, params, trxName);
			return;
		}
		if (o.is_ValueChanged(MOrder.COLUMNNAME_DocStatus))
		{
			List<MPPMRP> list = new Query(o.getCtx(), MPPMRP.Table_Name, whereClause, trxName)
							.setParameters(params)
							.list();
			for (MPPMRP mrp : list) {
				mrp.setDocStatus(o.getDocStatus());
				mrp.saveEx();
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
		Properties ctx = ol.getCtx();
		String trxName = ol.get_TrxName();
		final String whereClause = "AD_Client_ID = ? AND C_OrderLine_ID = ?";
		Object[] params = new Object[]{ol.getAD_Client_ID(),ol.getC_OrderLine_ID()};
		if (delete)
		{
			DB.executeUpdateEx("DELETE FROM PP_MRP WHERE "+whereClause, params, trxName);
			MPPOrder order = new Query(ctx, MPPOrder.Table_Name, whereClause, trxName)
									.setParameters(params)
									.first();
			if (order != null && !order.isProcessed())
			{
				order.deleteEx(true, trxName);
			}
			return;
		}
		
		MPPMRP mrp = new Query(ctx, MPPMRP.Table_Name, whereClause, trxName)
						.setParameters(params)
						.first();
		if(mrp == null)
		{	
			mrp = new MPPMRP(ctx, 0,trxName);                                                          
			mrp.setC_Order_ID(ol.getC_Order_ID());
			mrp.setC_OrderLine_ID(ol.getC_OrderLine_ID());
			if (ol.getParent().isSOTrx())
			{    
				mrp.setOrderType(MPPMRP.ORDERTYPE_SalesOrder);
				mrp.setTypeMRP(MPPMRP.TYPEMRP_Demand);
			}
			else
			{
				mrp.setOrderType(MPPMRP.ORDERTYPE_PurchaseOrder);
				mrp.setTypeMRP(MPPMRP.TYPEMRP_Supply);                                 
			}
		}
		mrp.setDescription(ol.getDescription());
		mrp.setName("MRP");
		mrp.setQty(ol.getQtyOrdered().subtract(ol.getQtyDelivered()));
		mrp.setDatePromised(ol.getDatePromised());
		mrp.setDateStartSchedule(ol.getDatePromised());
		mrp.setDateFinishSchedule(ol.getDatePromised());
		mrp.setDateOrdered(ol.getDateOrdered());
		mrp.setM_Product_ID(ol.getM_Product_ID());
		mrp.setM_Warehouse_ID(ol.getM_Warehouse_ID());
		mrp.setC_BPartner_ID(ol.getParent().getC_BPartner_ID());                                         
		mrp.setDocStatus(ol.getParent().getDocStatus());                                         
		mrp.saveEx();

		MPPOrder order = new Query(ctx, MPPOrder.Table_Name, whereClause, trxName)
								.setParameters(params)
								.first();		
		if (order == null)
		{
			MProduct product = MProduct.get(ctx,ol.getM_Product_ID());   
			MPPProductBOM bom =  MPPProductBOM.getDefault(product, trxName);
			if (bom != null) 
			{
				if (bom.getBOMType().equals(MPPProductBOM.BOMTYPE_Make_To_Order))
				{					
					String WhereClause = "ManufacturingResourceType = 'PT' AND IsManufacturingResource = 'Y' AND AD_Client_ID = ? AND M_Warehouse_ID = ?";
					MResource m_resource = (MResource)MTable.get(ctx,MResource.Table_ID).getPO(WhereClause, new Object[]{ ol.getAD_Client_ID(),ol.getM_Warehouse_ID()}, trxName);
					MWorkflow m_workflow = MWorkflow.get(ctx, MWorkflow.getWorkflowSearchKey(ctx, product));
					if (m_resource != null && m_workflow != null)
					{
						MDocType[] doc = MDocType.getOfDocBaseType(ctx, X_C_DocType.DOCBASETYPE_ManufacturingOrder);
						int C_DocType_ID = doc[0].getC_DocType_ID();
						
						order = new MPPOrder(ctx, 0 , trxName);                                     
						order.setC_OrderLine_ID(ol.getC_OrderLine_ID());
						order.setS_Resource_ID(m_resource.get_ID());
						order.setM_Warehouse_ID(ol.getM_Warehouse_ID());
						order.setM_Product_ID(ol.getM_Product_ID());
						order.setM_AttributeSetInstance_ID(ol.getM_AttributeSetInstance_ID());
						order.setPP_Product_BOM_ID(bom.get_ID());
						order.setAD_Workflow_ID(m_workflow.get_ID());
						//order.setPlanner_ID(SupplyPlanner_ID);
						order.setLine(10);
						order.setQtyDelivered(Env.ZERO);
						order.setQtyReject(Env.ZERO);
						order.setQtyScrap(Env.ZERO);                                                        
						order.setDateOrdered(ol.getDateOrdered());                       
						order.setDatePromised(ol.getDatePromised());
						order.setDateStartSchedule(TimeUtil.addDays(ol.getDatePromised(), (MPPMRP.getDays(ctx,m_resource.getS_Resource_ID(),m_workflow.getAD_Workflow_ID(), ol.getQtyOrdered(),ol.get_TrxName())).negate().intValue()));                                                       
						order.setDateFinishSchedule(ol.getDatePromised());
						order.setQtyEntered(ol.getQtyEntered());
						order.setQtyOrdered(ol.getQtyOrdered());
						order.setC_UOM_ID(ol.getC_UOM_ID());
						order.setPosted(false);
						order.setProcessed(false);
						order.setC_DocTypeTarget_ID(C_DocType_ID);
						order.setC_DocType_ID(C_DocType_ID);
						order.setPriorityRule(MPPOrder.PRIORITYRULE_High);                                
						order.saveEx();  
						order.prepareIt(); 
						order.setDocAction(MPPOrder.DOCSTATUS_Completed);
						order.saveEx();
					}
				}
			}    
		}
		else
		{    
			if (!order.isProcessed())
			{
				order.setQtyEntered(ol.getQtyEntered());
				order.setDatePromised(ol.getDatePromised());
				order.saveEx();
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

		String sql = null;
		String trxName = o.get_TrxName();
		Properties m_ctx = o.getCtx();
		if (delete)
		{
			sql = "DELETE FROM PP_MRP WHERE PP_Order_ID = "+ o.getPP_Order_ID()  +" AND AD_Client_ID = " + o.getAD_Client_ID();				
			DB.executeUpdateEx(sql ,trxName);
			return;
		}
		String whereClause = "TypeMRP=? AND OrderType=? AND AD_Client_ID=? AND PP_Order_ID=?";
		MPPMRP mrp = new Query(m_ctx, MPPMRP.Table_Name, whereClause, trxName)
						.setParameters(new Object[]{MPPMRP.TYPEMRP_Supply, MPPMRP.ORDERTYPE_ManufacturingOrder, o.getAD_Client_ID() , o.getPP_Order_ID()})
						.first();
		if(mrp == null)
		{		                    
			mrp = new MPPMRP(m_ctx, 0, trxName);                                                                                                                 
			mrp.setPP_Order_ID(o.getPP_Order_ID());
			mrp.setTypeMRP(MPPMRP.TYPEMRP_Supply);
			mrp.setOrderType(MPPMRP.ORDERTYPE_ManufacturingOrder);
		}
		mrp.setDescription(o.getDescription());
		mrp.setName(o.getDocumentNo());
		mrp.setQty(o.getQtyOrdered().subtract(o.getQtyDelivered()));
		mrp.setDatePromised(o.getDatePromised());
		mrp.setDateOrdered(o.getDateOrdered());
		mrp.setDateStartSchedule(o.getDateStartSchedule());
		mrp.setDateFinishSchedule(o.getDateStartSchedule());
		mrp.setM_Product_ID(o.getM_Product_ID());
		mrp.setM_Warehouse_ID(o.getM_Warehouse_ID());
		mrp.setS_Resource_ID(o.getS_Resource_ID());
		mrp.setDocStatus(o.getDocStatus());
		mrp.saveEx();           
	}

	/**
	 * Create MRP record based in Manufacturing Order BOM Line
	 * @param MPPOrderBOMLine Order BOM Line
	 * @param delete indicate if this record is delete
	 */
	public static void PP_Order_BOMLine(MPPOrderBOMLine obl,boolean delete)
	{        	   
		String trxName = obl.get_TrxName();
		Properties ctx = obl.getCtx();
		if (delete)
		{
			final String sql = "DELETE FROM PP_MRP WHERE PP_Order_BOMLine_ID=? AND AD_Client_ID=?";				
			DB.executeUpdateEx(sql, new Object[]{obl.get_ID(), obl.getAD_Client_ID()}, trxName);
			return;
		}

		final String whereClause = "TypeMRP=? AND OrderType=? AND PP_Order_BOMLine_ID=? AND AD_Client_ID=?";
		MPPMRP mrp = new Query(ctx, MPPMRP.Table_Name, whereClause, trxName)
							.setParameters(new Object[]{TYPEMRP_Demand, ORDERTYPE_ManufacturingOrder, obl.get_ID(), obl.getAD_Client_ID()})
							.first();
		MPPOrder o = obl.getParent();
		if(mrp == null)
		{
			mrp = new MPPMRP(ctx, 0, trxName);                                                                           
			mrp.setPP_Order_BOMLine_ID(obl.getPP_Order_BOMLine_ID());
			mrp.setPP_Order_ID(o.getPP_Order_ID());
			mrp.setOrderType(MPPMRP.ORDERTYPE_ManufacturingOrder);
			mrp.setTypeMRP(MPPMRP.TYPEMRP_Demand);
		}
		mrp.setName(o.getDocumentNo());
		mrp.setDescription(o.getDescription());
		mrp.setQty(obl.getQtyRequiered().subtract(obl.getQtyDelivered()));
		mrp.setDatePromised(o.getDatePromised());
		mrp.setDateOrdered(o.getDateOrdered());
		mrp.setDateStartSchedule(o.getDateStartSchedule());
		mrp.setDateFinishSchedule(o.getDateFinishSchedule());
		mrp.setM_Product_ID(obl.getM_Product_ID());
		mrp.setM_Warehouse_ID(obl.getM_Warehouse_ID());
		mrp.setS_Resource_ID(o.getS_Resource_ID());
		mrp.setDocStatus(o.getDocStatus());
		mrp.saveEx();
	}

	/**
	 * Create MRP record based in Distribution Order
	 * @param MDDOrder Distribution Order
	 * @param delete Indicate if this record is delete
	 */
	public static void DD_Order(MDDOrder o, boolean delete)
	{        	   
		String sql = null;
		String trxName = o.get_TrxName();
		if (delete)
		{
			sql = "DELETE FROM PP_MRP WHERE DD_Order_ID = "+ o.getDD_Order_ID()  +" AND AD_Client_ID = " + o.getAD_Client_ID();				
			DB.executeUpdateEx(sql, trxName);
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
		String sql = null;
		String trxName = ol.getParent().get_TrxName();
		Properties m_ctx = ol.getCtx();
		if (delete)
		{
			sql = "DELETE FROM PP_MRP WHERE DD_OrderLine_ID = "+ ol.getDD_OrderLine_ID()  +" AND AD_Client_ID = " + ol.getAD_Client_ID();				
			DB.executeUpdateEx(sql ,trxName);
			return;
		}
		String whereClause = "TypeMRP = 'D' AND OrderType='DOO' AND AD_Client_ID=? AND DD_OrderLine_ID = ?";
		MPPMRP mrp = (MPPMRP)MTable.get(m_ctx, MPPMRP.Table_ID).getPO(whereClause, new Object[]{ol.getAD_Client_ID(),ol.getDD_OrderLine_ID()}, trxName);
		MLocator source = MLocator.get( m_ctx , ol.getM_Locator_ID());
		MLocator target = MLocator.get( m_ctx , ol.getM_LocatorTo_ID());
		if(mrp!=null)
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
		whereClause ="TypeMRP='S' AND OrderType='DOO' AND AD_Client_ID=? AND DD_OrderLine_ID = ? ";
		mrp = (MPPMRP)MTable.get(m_ctx, MPPMRP.Table_ID).getPO(whereClause, new Object[]{ol.getAD_Client_ID(),ol.getDD_OrderLine_ID()}, trxName);
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
		String trxName = rl.get_TrxName();
		Properties ctx = rl.getCtx();
		final String whereClause = "M_RequisitionLine_ID=? AND AD_Client_ID=?";
		Object[] params = new Object[]{rl.getM_RequisitionLine_ID(), rl.getAD_Client_ID()};
		
		if (delete)
		{
			DB.executeUpdateEx("DELETE FROM PP_MRP WHERE "+whereClause, params, trxName);
			return;
		}

		MPPMRP mrp = new Query(ctx, MPPMRP.Table_Name, whereClause, trxName)
							.setParameters(params)
							.first();
		MRequisition r = rl.getParent();
		if (mrp == null)
		{
			mrp = new MPPMRP(ctx, 0, trxName);  
			mrp.setM_Requisition_ID(rl.getM_Requisition_ID());
			mrp.setM_RequisitionLine_ID(rl.getM_RequisitionLine_ID());
			mrp.setOrderType(MPPMRP.ORDERTYPE_MaterialRequisition);
			mrp.setTypeMRP(MPPMRP.TYPEMRP_Supply);
			mrp.setIsAvailable(true);
		}
		mrp.setName("MRP");
		mrp.setDescription(rl.getDescription());                                                        
		mrp.setDatePromised(r.getDateRequired());
		mrp.setDateStartSchedule(r.getDateRequired());
		mrp.setDateFinishSchedule(r.getDateRequired());
		mrp.setDateOrdered(r.getDateRequired());
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
		final String sql = "SELECT SUM(bomQtyOnHand (M_Product_ID,?,0)) AS OnHand FROM M_Product"
							+" WHERE AD_Client_ID=? AND M_Product_ID=?";
		BigDecimal QtyOnHand = DB.getSQLValueBD(trxName, sql, new Object[]{M_Warehouse_ID,Env.getAD_Client_ID(ctx),M_Product_ID});
		if (QtyOnHand == null)
			QtyOnHand = Env.ZERO;
		return QtyOnHand;
	}
	
    /**
     * Get Reserved Quantity for a Warehouse 
	 * @param ctx
	 * @param M_Warehouse_ID
	 * @param M_Product_ID
	 * @param to
	 * @param Ordertype separate for SOO,POO,
	 * @param trxName
	 * @return BibDecimal
	 */
    public static BigDecimal getQtyReserved(Properties ctx, int M_Warehouse_ID ,int M_Product_ID, Timestamp to,String trxName)
	{
    	StringBuffer sql = new StringBuffer("SELECT SUM(Qty) FROM PP_MRP WHERE  TypeMRP='D' AND DocStatus IN ('IN','CO')");
    	sql.append("AND OrderType IN ('SOO','MOP','DOO') AD_Client_ID= ? AND DatePromised <=? AND M_Warehouse_ID =? AND M_Product_ID=?");
		BigDecimal qty = DB.getSQLValueBD(trxName, sql.toString(), new Object[]{Env.getAD_Client_ID(ctx), to , M_Warehouse_ID, M_Product_ID}); 		
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
	 * @param to
	 * @param Ordertype separate for SOO,POO,
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
	 * @param to
	 * @param trxName
	 * @return
	 */
    public static BigDecimal getQtyOrdered(Properties ctx, int M_Warehouse_ID ,int M_Product_ID, Timestamp to,String trxName)
	{
    	StringBuffer sql = new StringBuffer("SELECT SUM(Qty) FROM PP_MRP WHERE  TypeMRP='S' AND DocStatus IN ('IN','CO')");	
		sql.append("AND OrderType IN ('POO','MOP','DOO') AD_Client_ID= ? AND DatePromised <=? AND M_Warehouse_ID =? AND M_Product_ID=?");
		BigDecimal qty = DB.getSQLValueBD(trxName, sql.toString(), new Object[]{Env.getAD_Client_ID(ctx), to , M_Warehouse_ID, M_Product_ID}); 		
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
	 * @param to
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
		int LowLevel = DB.getSQLValue(trxName, sql, AD_Client_ID);
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
								* ( wf.getDurationBaseSec() / 60 / 60 ) // convert to hours
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
