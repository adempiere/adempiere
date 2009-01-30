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

package org.eevolution.process;


import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.compiere.model.MBPartner;
import org.compiere.model.MDocType;
import org.compiere.model.MLocator;
import org.compiere.model.MMessage;
import org.compiere.model.MNote;
import org.compiere.model.MOrg;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPO;
import org.compiere.model.MRequisition;
import org.compiere.model.MRequisitionLine;
import org.compiere.model.MResource;
import org.compiere.model.MStorage;
import org.compiere.model.MWarehouse;
import org.compiere.model.PO;
import org.compiere.model.POResultSet;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.CCache;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;
import org.compiere.wf.MWorkflow;
import org.eevolution.model.I_PP_Product_Planning;
import org.eevolution.model.MDDNetworkDistribution;
import org.eevolution.model.MDDNetworkDistributionLine;
import org.eevolution.model.MDDOrder;
import org.eevolution.model.MDDOrderLine;
import org.eevolution.model.MPPMRP;
import org.eevolution.model.MPPOrder;
import org.eevolution.model.MPPProductBOM;
import org.eevolution.model.MPPProductPlanning;
import org.eevolution.model.X_PP_Product_Planning;
/**
 *	Calculate Material Plan MRP
 *	
 *  @author Victor Perez, e-Evolution, S.C.
 *  @author Teo Sarca, www.arhipac.ro
 */
public class MRP extends SvrProcess
{
	private int     m_AD_Client_ID  = 0;		
	private int     p_AD_Org_ID     = 0;
	private int     p_S_Resource_ID = 0 ;
	private int     p_M_Warehouse_ID= 0;
	private boolean p_IsRequiredDRP = false;
	@SuppressWarnings("unused")
	private String  p_Version = "1";
	private String  result = "";

	//Global Variables
	private I_PP_Product_Planning m_product_planning = null;
	private int Planner_ID = 0;
	private BigDecimal QtyProjectOnHand = Env.ZERO;
	private BigDecimal QtyGrossReqs = Env.ZERO;
	private BigDecimal QtyScheduledReceipts = Env.ZERO;
	private Timestamp DatePromisedFrom = null;
	private Timestamp DatePromisedTo = null;
	private Timestamp Today = new Timestamp (System.currentTimeMillis());  
	private Timestamp TimeFence = null;
	private Timestamp Planning_Horizon = null;
	private int count_MO = 0;
	private int count_MR = 0;
	private int count_DO = 0;
	private int count_Msg = 0;

	private int DocTypeReq = 0;
	private int DocTypeMO = 0; 
	private int DocTypeDO = 0;
	
	private static CCache<String ,Integer>   dd_order_id_cache 	= new CCache<String,Integer>(MDDOrder.COLUMNNAME_DD_Order_ID, 50);
	private static CCache<Integer,MBPartner>   partner_cache 	= new CCache<Integer,MBPartner>(MBPartner.Table_Name, 50);



	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		m_AD_Client_ID = Env.getAD_Client_ID(getCtx());
		Planner_ID = Env.getAD_User_ID(getCtx());
		ProcessInfoParameter[] para = getParameter();

		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();

			if (para[i].getParameter() == null)
				;
			else if (name.equals("AD_Org_ID"))
			{    
				p_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();

			}                       
			else if (name.equals("S_Resource_ID"))
			{    
				p_S_Resource_ID = ((BigDecimal)para[i].getParameter()).intValue();    
			}
			else if (name.equals("M_Warehouse_ID"))
			{    
				p_M_Warehouse_ID = ((BigDecimal)para[i].getParameter()).intValue();                
			}
			else if (name.equals("IsRequiredDRP"))
			{    
				p_IsRequiredDRP = "Y".equals((String)para[i].getParameter());        
			}
			else if (name.equals("Version"))
			{    
				p_Version = (String)para[i].getParameter();        
			}
			else
				log.log(Level.SEVERE,"prepare - Unknown Parameter: " + name);
		}


	}	//	prepare


	protected String doIt() throws Exception                
	{
		// Set Default Document Type To Requisition
		DocTypeReq = getDocType(MDocType.DOCBASETYPE_PurchaseRequisition);
		DocTypeMO = getDocType(MDocType.DOCBASETYPE_ManufacturingOrder);
		DocTypeDO = getDocType(MDocType.DOCBASETYPE_DistributionOrder);

		log.info("Type Document to Requisition:"+ DocTypeReq);
		log.info("Type Document to Manufacturing Order:" + DocTypeMO);
		log.info("Type Document to Distribution Order:" + DocTypeDO);
		
		ArrayList <Object> parameters = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer(MResource.COLUMNNAME_ManufacturingResourceType+"=? AND AD_Client_ID=?");
		parameters.add(MResource.MANUFACTURINGRESOURCETYPE_Plant);
		parameters.add(m_AD_Client_ID);
		
		if (p_S_Resource_ID > 0)
		{	
			whereClause.append(" AND S_Resource_ID=?");
			parameters.add(p_S_Resource_ID);
		}	
		
		List <MResource> plants = new Query(getCtx(), MResource.Table_Name, whereClause.toString(), get_TrxName())
										.setParameters(parameters)
										.list(); 
		
		for(MResource plant : plants)
		{	
			log.info("Run MRP to Plant: " + plant.getName());
			Planning_Horizon = TimeUtil.addDays(getToday(), plant.getPlanningHorizon()); 
			parameters = new ArrayList<Object>();
			whereClause = new StringBuffer("AD_Client_ID=?");
			parameters.add(m_AD_Client_ID);
			
			if (p_AD_Org_ID > 0)
			{	
				whereClause.append(" AND AD_Org_ID=?");
				parameters.add(p_AD_Org_ID);
			}	
			
		
			List <MOrg> organizations = new Query(getCtx(),MOrg.Table_Name, whereClause.toString(), get_TrxName())
			.setParameters(parameters)
			.list();
			
				for (MOrg organization :  organizations)
				{
					log.info("Run MRP to Organization: " + organization.getName());
					if(p_M_Warehouse_ID==0)
					{
						MWarehouse[] ws = MWarehouse.getForOrg(getCtx(), organization.getAD_Org_ID());
						for(MWarehouse w : ws)
						{	
							if(plant.getM_Warehouse_ID() == w.getM_Warehouse_ID() && p_IsRequiredDRP)
								continue;
							
							log.info("Run MRP to Wharehouse: " + w.getName());
							runMRP(m_AD_Client_ID,organization.getAD_Org_ID(),plant.getS_Resource_ID(),w.getM_Warehouse_ID());
							result = result + "<br>finish MRP to Warehouse " +w.getName();
						}
					}
					else
					{
						if(plant.getM_Warehouse_ID() == p_M_Warehouse_ID && p_IsRequiredDRP)
							continue;
						
						runMRP(m_AD_Client_ID,organization.getAD_Org_ID(),plant.getS_Resource_ID(),p_M_Warehouse_ID);
					}
					result = result + "<br>finish MRP to Organization " +organization.getName();
				}
				result = result + "<br> " +Msg.translate(getCtx(), "Created");
				result = result + "<br> " ;
				result = result + "<br> " +Msg.translate(getCtx(), "PP_Order_ID")+":"+count_MO;
				result = result + "<br> " +Msg.translate(getCtx(), "DD_Order_ID")+":"+count_DO;
				result = result + "<br> " +Msg.translate(getCtx(), "M_Requisition_ID")+":"+count_MR;
				result = result + "<br> " +Msg.translate(getCtx(), "AD_Note_ID")+":"+count_Msg;
				result = result + "<br>finish MRP to Plant " +plant.getName();
		}		
			
		return result;
	} 


	/**************************************************************************
	 * Delete old record in MRP table to calculate again MRP and Document with Draft status 
	 * @param AD_Client_ID Client_ID
	 * @param AD_Org_ID Orgganization ID
	 * @param M_Warehouse_ID Warehouse ID
	 */
	protected void deleteMRP(int AD_Client_ID, int AD_Org_ID,int S_Resource_ID, int M_Warehouse_ID)
	{
		// Delete Manufacturing Order with Close Status from MRP Table
		String sql = "DELETE FROM PP_MRP WHERE OrderType = 'MOP' AND DocStatus ='CL' AND AD_Client_ID=" + AD_Client_ID  + " AND AD_Org_ID=" + AD_Org_ID + " AND M_Warehouse_ID="+M_Warehouse_ID +  " AND S_Resource_ID="+S_Resource_ID ;					
		DB.executeUpdateEx(sql, get_TrxName());
		commit();
		//Delete Manufacturing Order with Draft Status 
		String whereClause = "DocStatus='DR' AND AD_Client_ID=? AND AD_Org_ID=? AND M_Warehouse_ID=? AND S_Resource_ID=?";
		deletePO(MPPOrder.Table_Name, whereClause, new Object[]{AD_Client_ID, AD_Org_ID, M_Warehouse_ID, S_Resource_ID});

		// Delete Requisition with Status Close from MRP Table
		sql = "DELETE FROM PP_MRP WHERE OrderType = 'POR' AND DocStatus IN ('CL','DR') AND AD_Client_ID = " + AD_Client_ID +  " AND AD_Org_ID=" + AD_Org_ID+ " AND M_Warehouse_ID="+M_Warehouse_ID;				
		DB.executeUpdateEx(sql, get_TrxName());
		commit();		
		//Delete Requisition with Draft Status
		whereClause = "DocStatus IN ('CL','DR') AND AD_Client_ID=? AND AD_Org_ID=? AND M_Warehouse_ID=?";
		deletePO(MRequisition.Table_Name, whereClause, new Object[]{AD_Client_ID, AD_Org_ID, M_Warehouse_ID});

		// Delete Action Notice
		sql = "DELETE FROM AD_Note WHERE AD_Table_ID=? AND AD_Client_ID=? AND AD_Org_ID=?";
		DB.executeUpdateEx(sql, new Object[]{MPPMRP.Table_ID, AD_Client_ID, AD_Org_ID}, get_TrxName());
		commit();

		if (p_IsRequiredDRP)
		{
			//Delete Distribution Order with Draft Status
			whereClause = "DocStatus='DR' AND AD_Client_ID=? AND AD_Org_ID=?"
								+" AND EXISTS (SELECT 1 FROM DD_OrderLine ol INNER JOIN  M_Locator l ON (l.M_Locator_ID=ol.M_LocatorTo_ID) "
										+" WHERE ol.DD_Order_ID=DD_Order.DD_Order_ID AND l.M_Warehouse_ID=?)";
			deletePO(MDDOrder.Table_Name, whereClause, new Object[]{AD_Client_ID, AD_Org_ID, M_Warehouse_ID});
		}
		
		// Mark all supply MRP records as available
		DB.executeUpdateEx("UPDATE PP_MRP SET IsAvailable ='Y' WHERE TypeMRP = 'S' AND AD_Client_ID = ? AND AD_Org_ID=? AND M_Warehouse_ID=?", new Object[]{AD_Client_ID,AD_Org_ID,M_Warehouse_ID} ,get_TrxName());
		commit();
	}

	/**************************************************************************
	 *  Calculate plan
	 *  @param AD_Client_ID Client ID
	 *  @param AD_Org_ID Organization ID
	 *  @param M_Warehuse_ID Warehouse ID
	 */
	protected String runMRP(int AD_Client_ID , int AD_Org_ID, int S_Resource_ID , int M_Warehouse_ID)
	{
		deleteMRP(AD_Client_ID,AD_Org_ID,S_Resource_ID,M_Warehouse_ID);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			MProduct product =  null;                                                                       

			int BeforePP_MRP_ID = 0;						
			Timestamp  BeforeDateStartSchedule = null;
			Timestamp  POQDateStartSchedule = null;
			
			int lowlevel = MPPMRP.getMaxLowLevel(getCtx(), get_TrxName());
			log.info("Low Level Is :"+lowlevel);
			// Calculate MRP for all levels
			for (int level = 0 ; level <= lowlevel ; level++)
			{
				log.info("Current Level Is :" + level);
				final String sql = "SELECT p.M_Product_ID ,p.Name , p.LowLevel , mrp.Qty , mrp.DatePromised"
							+ ", mrp.TypeMRP , mrp.OrderType , mrp.DateOrdered , mrp.M_Warehouse_ID"
							+ ", mrp.PP_MRP_ID ,  mrp.DateStartSchedule , mrp.DateFinishSchedule"
						+" FROM PP_MRP mrp"
						+" INNER JOIN M_Product p ON (p.M_Product_ID =  mrp.M_Product_ID)"
						+" WHERE mrp.TypeMRP=?"
						+" AND mrp.AD_Client_ID=?"
						+" AND mrp.AD_Org_ID=? "
						+" AND M_Warehouse_ID=? "
						+" AND mrp.DatePromised<=?"
						+" AND COALESCE(p.LowLevel,0)=? "
						+" ORDER BY  mrp.M_Product_ID , mrp.DatePromised  ";
				pstmt = DB.prepareStatement (sql, get_TrxName());
				pstmt.setString(1, MPPMRP.TYPEMRP_Demand);
				pstmt.setInt(2, AD_Client_ID);
				pstmt.setInt(3, AD_Org_ID);
				pstmt.setInt(4, M_Warehouse_ID);
				pstmt.setTimestamp(5, Planning_Horizon);
				pstmt.setInt(6, level);
				rs = pstmt.executeQuery();                               
				while (rs.next())
				{
					final int PP_MRP_ID = rs.getInt(MPPMRP.COLUMNNAME_PP_MRP_ID);
					final String TypeMRP = rs.getString(MPPMRP.COLUMNNAME_TypeMRP);
					final String OrderType = rs.getString(MPPMRP.COLUMNNAME_OrderType);
					final Timestamp DatePromised = rs.getTimestamp(MPPMRP.COLUMNNAME_DatePromised);
					final Timestamp DateStartSchedule = rs.getTimestamp(MPPMRP.COLUMNNAME_DateStartSchedule);
					final BigDecimal Qty = rs.getBigDecimal(MPPMRP.COLUMNNAME_Qty);
					final int M_Product_ID = rs.getInt(MPPMRP.COLUMNNAME_M_Product_ID); 
					
					//MRP-150
					//Past Due Demand
					//Indicates that a demand order is past due.
					if(DatePromised.compareTo(getToday()) < 0)
					{
						String comment = Msg.translate(getCtx(), MPPOrder.COLUMNNAME_DatePromised)
										 + ": " + DatePromised;			
						createMRPNote("MRP-150", AD_Org_ID, PP_MRP_ID, product, MPPMRP.getDocumentNo(PP_MRP_ID), 
								Qty, comment);
					}

					// if demand is a forecast and this is minor today then is ignore this QtyGrossReq
					if (MPPMRP.TYPEMRP_Demand.equals(TypeMRP)
							&& MPPMRP.ORDERTYPE_Forecast.equals(OrderType)
							&& DatePromised.compareTo(getToday()) <= 0)
					{
						continue;  
					}

					// New Product
					if (product == null || product.get_ID() != M_Product_ID)
					{
						product = MProduct.get(getCtx(), M_Product_ID);
						log.info("Calculte Plan to this Product:" + product.getName());
						
						//if exist QtyGrossReq of last Demand verify plan
						if (QtyGrossReqs.signum() != 0)
						{							
							if (X_PP_Product_Planning.ORDER_POLICY_PeriodOrderQuantity.equals(m_product_planning.getOrder_Policy()) &&  POQDateStartSchedule.compareTo(Planning_Horizon) < 0) 
							{
								BeforeDateStartSchedule =  POQDateStartSchedule; 
								calculatePlan(AD_Client_ID, AD_Org_ID,M_Warehouse_ID ,BeforePP_MRP_ID , product ,BeforeDateStartSchedule);
							}
							else if (X_PP_Product_Planning.ORDER_POLICY_LoteForLote.equals(m_product_planning.getOrder_Policy()) && BeforeDateStartSchedule.compareTo(Planning_Horizon) <= 0 )
							{
								calculatePlan(AD_Client_ID, AD_Org_ID,M_Warehouse_ID ,BeforePP_MRP_ID , product ,BeforeDateStartSchedule );
							}
							QtyGrossReqs = Env.ZERO;
						}


						// Define m_product_planning
						setProduct(AD_Client_ID,AD_Org_ID ,S_Resource_ID , M_Warehouse_ID,  product);
						
						// If No Product Planning found, go to next MRP record 
						if (m_product_planning == null)
							continue;	  
							
						//first DatePromised.compareTo for ORDER_POLICY_PeriodOrderQuantity
						if (X_PP_Product_Planning.ORDER_POLICY_PeriodOrderQuantity.equals(m_product_planning.getOrder_Policy()))
						{
							DatePromisedFrom = DatePromised;
							DatePromisedTo = TimeUtil.addDays(DatePromised , m_product_planning.getOrder_Period().intValue());                                       
							//set the POQDateStartSchedule && POQDateStartSchedule to first period
							//POQDateStartSchedule = (level == 0 ? DatePromised : DateStartSchedule);
							POQDateStartSchedule = DatePromised;
						}
					} // new product
					
					// If No Product Planning found, go to next MRP record 
					if (m_product_planning == null)
						continue;
					                                          
					BeforePP_MRP_ID = PP_MRP_ID;

					if (X_PP_Product_Planning.ORDER_POLICY_PeriodOrderQuantity.equals(m_product_planning.getOrder_Policy()))
					{
						// Verify if is DatePromised < DatePromisedTo then Accumulation QtyGrossReqs 
						if (DatePromisedTo != null && DatePromised.compareTo(DatePromisedTo) < 0)
						{
							QtyGrossReqs = QtyGrossReqs.add(Qty);
							log.info("Accumulation   QtyGrossReqs:" + QtyGrossReqs);
							log.info("DatePromised:" + DatePromised);
							log.info("DatePromisedTo:" + DatePromisedTo);
							continue;
						}
						else
						{ // if not then create new range for next period
							BeforeDateStartSchedule =  POQDateStartSchedule; 
							calculatePlan(AD_Client_ID,AD_Org_ID,M_Warehouse_ID,PP_MRP_ID,product ,BeforeDateStartSchedule);										
							QtyGrossReqs = Qty; 
							DatePromisedFrom = DatePromised;
							DatePromisedTo = TimeUtil.addDays(DatePromised, m_product_planning.getOrder_Period().intValue());         
							POQDateStartSchedule = (level == 0 ? DatePromised : DateStartSchedule);
							continue;
						}
					}
					// If  Order_Policy = LoteForLote then always create new range for next period and put QtyGrossReqs          
					else if (X_PP_Product_Planning.ORDER_POLICY_LoteForLote.equals(m_product_planning.getOrder_Policy()))
					{                                                                                                                                           
						QtyGrossReqs = Qty;
						BeforeDateStartSchedule = DatePromised; 		
						calculatePlan(AD_Client_ID, AD_Org_ID,M_Warehouse_ID,PP_MRP_ID,product,BeforeDateStartSchedule); 		
						continue;
					}                                                                        
				} // end while

				//if exist QtyGrossReq of last Demand after finish while verify plan
				if (QtyGrossReqs.signum() != 0 && product != null)
				{   
					if (X_PP_Product_Planning.ORDER_POLICY_PeriodOrderQuantity.equals(m_product_planning.getOrder_Policy())
							&&  POQDateStartSchedule.compareTo(Planning_Horizon) < 0) 
					{
						BeforeDateStartSchedule =  POQDateStartSchedule; 
						calculatePlan(AD_Client_ID,AD_Org_ID,M_Warehouse_ID,BeforePP_MRP_ID , product ,BeforeDateStartSchedule);
					}
					else if (X_PP_Product_Planning.ORDER_POLICY_LoteForLote.equals(m_product_planning.getOrder_Policy())
							&& BeforeDateStartSchedule.compareTo(Planning_Horizon) <= 0 )
					{
						calculatePlan(AD_Client_ID,AD_Org_ID,M_Warehouse_ID,BeforePP_MRP_ID , product ,BeforeDateStartSchedule );
					}	
					
				}
				else if (product != null)
				{
					//Create Action Notice if exist supply
					getNetRequierements(
							AD_Client_ID, 
							AD_Org_ID, 
							M_Warehouse_ID, 
							product, 
							null);					
				}

				DB.close(rs, pstmt);
			} // end for
		} // try
		catch (SQLException ex)
		{
			throw new DBException(ex);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}

		return "ok";
	}

	/**************************************************************************
	 * 	Define the product to calculate plan
	 *  @param AD_Client_ID Client ID
	 *  @param AD_Org_ID Organization ID
	 *  @param M_Warehuse_ID Warehouse ID
	 *	@param MProduct
	 */
	private void setProduct(int AD_Client_ID , int AD_Org_ID, int S_Resource_ID , int M_Warehouse_ID, MProduct product)
	{
		DatePromisedTo = null;
		DatePromisedFrom = null;
		//
		// Find data product planning demand 
		m_product_planning = getProductPlanning(AD_Client_ID, AD_Org_ID, S_Resource_ID, M_Warehouse_ID, product);
		if (m_product_planning == null)
		{
			createMRPNote("MRP-120", AD_Org_ID, 0, product, null,  null , null);
			return;
		}

		//Find Vendor
		if (!p_IsRequiredDRP)
		{	
			if(product.isPurchased())
			{    
				int C_BPartner_ID = 0;
				MProductPO[] ppos = MProductPO.getOfProduct(getCtx(), product.getM_Product_ID(), get_TrxName());
				for (int i = 0; i < ppos.length; i++)
				{
					if (ppos[i].isCurrentVendor() && ppos[i].getC_BPartner_ID() != 0)
					{
						C_BPartner_ID = ppos[i].getC_BPartner_ID();
						m_product_planning.setDeliveryTime_Promised(new BigDecimal(ppos[i].getDeliveryTime_Promised()));    	                	            
						m_product_planning.setOrder_Min(ppos[i].getOrder_Min());
						m_product_planning.setOrder_Pack(ppos[i].getOrder_Pack());
						break;
					}
				}
				if(C_BPartner_ID <= 0)
				{
					createMRPNote("MRP-130", AD_Org_ID, 0, product, null, null , null);
					m_product_planning.setIsCreatePlan(false);
				}
			}
			if (product.isBOM())
			{	
				if (m_product_planning.getAD_Workflow_ID() == 0 )
					log.info("Error: Do not exist workflow ("+product.getValue()+")");
			}
		}	
		
		if(m_product_planning.getTimeFence().signum() > 0)
			TimeFence = TimeUtil.addDays(getToday(), m_product_planning.getTimeFence().intValue()); 

		QtyProjectOnHand = getQtyOnHand(m_product_planning);
		if(QtyProjectOnHand.signum() < 0)
		{
			String comment = Msg.translate(getCtx(), MStorage.COLUMNNAME_QtyOnHand) 
							+ ": " + QtyProjectOnHand;
			//MRP-140
			//Beginning Quantity Less Than Zero
			//Indicates that the quantity on hand is negative.
			createMRPNote("MRP-140", AD_Org_ID, 0, product , null , QtyProjectOnHand , comment);
		}
		
		// Quantity Project On hand 100 
		// Safety Stock 150
		// 150 > 100 The Quantity Project On hand is now 50
		if(m_product_planning.getSafetyStock().signum() > 0
				&& m_product_planning.getSafetyStock().compareTo(QtyProjectOnHand) > 0)
		{
			String comment = Msg.translate(getCtx(), MStorage.COLUMNNAME_QtyOnHand) 
							+ ": " + QtyProjectOnHand
							+ " "  +  Msg.translate(getCtx(), I_PP_Product_Planning.COLUMNNAME_SafetyStock)
							+ ": " + m_product_planning.getSafetyStock();
			createMRPNote("MRP-001", AD_Org_ID, 0, product , null , QtyProjectOnHand , comment);
			QtyProjectOnHand =  QtyProjectOnHand.subtract(m_product_planning.getSafetyStock());
		}
		log.info("QtyOnHand :" + QtyProjectOnHand);
		
	}
	
	protected I_PP_Product_Planning getProductPlanning(int AD_Client_ID , int AD_Org_ID, int S_Resource_ID , int M_Warehouse_ID, MProduct product)
	{
		//find data product planning demand 
		MPPProductPlanning pp = MPPProductPlanning.find(getCtx() ,AD_Org_ID , M_Warehouse_ID, S_Resource_ID , product.getM_Product_ID(), get_TrxName());
		if (pp == null)
		{
			return null;
		}
		MPPProductPlanning pp2 = new MPPProductPlanning(getCtx(), 0 , null);                                                       
		MPPProductPlanning.copyValues(pp, pp2);
		//Find the BOM to this Product
		if (pp2.getPP_Product_BOM_ID() <= 0 && product.isBOM())
		{
			pp2.setPP_Product_BOM_ID(MPPProductBOM.getBOMSearchKey(product));  
		}      
		if (pp2.getAD_Workflow_ID() <= 0 && product.isBOM())
		{
			pp2.setAD_Workflow_ID(MWorkflow.getWorkflowSearchKey(product));  
		} 
		if (pp2.getPlanner_ID() <= 0)
		{
			pp2.setPlanner_ID(Planner_ID);
		}
		if(pp2.getM_Warehouse_ID() <= 0)
		{
			pp2.setM_Warehouse_ID(M_Warehouse_ID);
		}
		if (pp2.getS_Resource_ID() <= 0)
		{
			pp2.setS_Resource_ID(S_Resource_ID);
		}
		if (pp2.getOrder_Policy() == null)
		{
			pp2.setOrder_Policy(X_PP_Product_Planning.ORDER_POLICY_LoteForLote);
		}

		//Find Vendor
		if (!p_IsRequiredDRP)
		{	
			if(product.isPurchased())
			{    
				int C_BPartner_ID = 0;
				MProductPO[] ppos = MProductPO.getOfProduct(getCtx(), product.getM_Product_ID(), get_TrxName());
				for (int i = 0; i < ppos.length; i++)
				{
					if (ppos[i].isCurrentVendor() && ppos[i].getC_BPartner_ID() != 0)
					{
						C_BPartner_ID = ppos[i].getC_BPartner_ID();
						pp2.setDeliveryTime_Promised(new BigDecimal(ppos[i].getDeliveryTime_Promised()));    	                	            
						pp2.setOrder_Min(ppos[i].getOrder_Min());
						pp2.setOrder_Pack(ppos[i].getOrder_Pack());
						break;
					}
				}
				if(C_BPartner_ID <= 0)
				{
					createMRPNote("MRP-130", AD_Org_ID, 0, product, null, null , null);
					pp2.setIsCreatePlan(false);
				}
			}
			if (product.isBOM())
			{	
				if (pp2.getAD_Workflow_ID() == 0 )
					log.info("Error: Do not exist workflow ("+product.getValue()+")");
			}
		}
		//
		return pp2;
	}
	
	protected BigDecimal getQtyOnHand(I_PP_Product_Planning pp)
	{
		return MPPMRP.getQtyOnHand(getCtx(), pp.getM_Warehouse_ID() , pp.getM_Product_ID(), get_TrxName());
	}
	
	protected Timestamp getToday()
	{
		return this.Today;
	}

	/**************************************************************************
	 * 	Calculate Plan this product
	 *	@param PP_MRP_ID MRP ID
	 *  @param M_Warehouse_ID Warehoue ID
	 *  @param product Product
	 *  @param DemandDateStartSchedule Demand Date Start Schedule
	 */
	private void calculatePlan(int AD_Client_ID, int AD_Org_ID, int M_Warehouse_ID, int PP_MRP_ID,
								MProduct M_Product, Timestamp DemandDateStartSchedule)
	{
		//Set Yield o QtyGrossReqs
		// Note : the variables  DemandDateStartSchedule , DemandDateFinishSchedule are same DatePromised to Demands Sales Order Type

		log.info("Create Plan ...");

		BigDecimal  DecimalYield = new BigDecimal(m_product_planning.getYield()/100);
		if (DecimalYield.signum() != 0)
			QtyGrossReqs = QtyGrossReqs.divide(DecimalYield, 4, BigDecimal.ROUND_HALF_UP);
		
		BigDecimal QtyNetReqs = getNetRequierements(
				AD_Client_ID, 
				AD_Org_ID, 
				M_Warehouse_ID, 
				M_Product, 
				DemandDateStartSchedule);
		
		BigDecimal QtyPlanned = Env.ZERO;

		((PO)m_product_planning).dump();
		log.info("                    Product:" + M_Product.getName());
		log.info(" Demand Date Start Schedule:" + DemandDateStartSchedule);
		log.info("           DatePromisedFrom:" + DatePromisedFrom + " DatePromisedTo:" +   DatePromisedTo);    
		log.info("                Qty Planned:" + QtyPlanned);
		log.info("     Qty Scheduled Receipts:" + QtyScheduledReceipts);
		log.info("           QtyProjectOnHand:" + QtyProjectOnHand);
		log.info("               QtyGrossReqs:" + QtyGrossReqs);
		log.info("                     Supply:" + (QtyScheduledReceipts).add(QtyProjectOnHand));
		log.info("                 QtyNetReqs:" + QtyNetReqs);    

		if (QtyNetReqs.signum() > 0)
		{ // entire qty is available or scheduled to receipt
			QtyProjectOnHand = QtyNetReqs;                                                
			QtyNetReqs = Env.ZERO;
			QtyScheduledReceipts = Env.ZERO;
			QtyPlanned = Env.ZERO;
			QtyGrossReqs = Env.ZERO;
			return;
		}
		else
		{                                                                           
			QtyPlanned = QtyNetReqs.negate();                          
			QtyGrossReqs = Env.ZERO;
			QtyScheduledReceipts = Env.ZERO;
		}

		// ***** Order Modifier ********
		// Check Order Min 
		if(QtyPlanned.signum() > 0 && m_product_planning.getOrder_Min().signum() > 0)
		{    
			if (m_product_planning.getOrder_Min().compareTo(QtyPlanned) > 0)
			{
				String comment = Msg.translate(getCtx(), I_PP_Product_Planning.COLUMNNAME_Order_Min) 
								+ ":" + m_product_planning.getOrder_Min();
				createMRPNote("MRP-080", AD_Org_ID, PP_MRP_ID, M_Product , null, QtyPlanned, comment );
			}
			QtyPlanned = QtyPlanned.max(m_product_planning.getOrder_Min());
		}

		// Check Order Max                                                
		if(QtyPlanned.compareTo(m_product_planning.getOrder_Max()) > 0 && m_product_planning.getOrder_Max().signum() > 0)
		{   
			String comment = Msg.translate(getCtx(), I_PP_Product_Planning.COLUMNNAME_Order_Max) 
			+ ":" + m_product_planning.getOrder_Max();
			createMRPNote("MRP-090", AD_Org_ID, PP_MRP_ID, M_Product  , null , QtyPlanned , comment); 
		}                        
		// Check Order Pack
		if (m_product_planning.getOrder_Pack().signum() > 0 && QtyPlanned.signum() > 0)
		{
			QtyPlanned = m_product_planning.getOrder_Pack().multiply(QtyPlanned.divide(m_product_planning.getOrder_Pack(), 0 , BigDecimal.ROUND_UP));
		}

		QtyProjectOnHand = QtyPlanned.add(QtyNetReqs);

		log.info("QtyNetReqs:" +  QtyNetReqs);
		log.info("QtyPlanned:" +  QtyPlanned);
		log.info("QtyProjectOnHand:" +  QtyProjectOnHand);     
	
		//MRP-100 Time Fence Conflict  Action Notice
		//Indicates that there is an unsatisfied material requirement inside the planning time fence for this product.
		//You should either manually schedule and expedite orders to fill this demand or delay fulfillment of the requirement that created the demand.
		if(TimeFence != null && DemandDateStartSchedule.compareTo(TimeFence) < 0)
		{
			String comment =  Msg.translate(getCtx(), I_PP_Product_Planning.COLUMNNAME_TimeFence) 
							+ ":" + m_product_planning.getTimeFence()
							+ "-"
							+ Msg.getMsg(getCtx(), "Date")
							+ ":" + TimeFence + " "
							+ Msg.translate(getCtx(), MPPOrder.COLUMNNAME_DatePromised)
							+ ":" + DemandDateStartSchedule;
			createMRPNote("MRP-100", AD_Org_ID, PP_MRP_ID, M_Product , null , QtyPlanned , comment);
		}
		
		if (m_product_planning.isCreatePlan() == false && QtyPlanned.signum() > 0)
		{	
			createMRPNote("MRP-020", AD_Org_ID, PP_MRP_ID, M_Product , null , QtyPlanned , null); 
			return;
		}
		
		if (QtyPlanned.signum() > 0)    
		{
			int loops = 1;
			if (m_product_planning.getOrder_Policy().equals(X_PP_Product_Planning.ORDER_POLICY_FixedOrderQuantity))
			{    
				if (m_product_planning.getOrder_Qty().compareTo(Env.ZERO) != 0)
					loops = (QtyPlanned.divide(m_product_planning.getOrder_Qty() , 0 , BigDecimal.ROUND_UP)).intValue();
				QtyPlanned = m_product_planning.getOrder_Qty();
			}

			for (int ofq = 1 ; ofq <= loops ; ofq ++ )
			{
				log.info("Is Purchased: "+ M_Product.isPurchased()+ " Is BOM: " +  M_Product.isBOM());

				// Distribution Order
				if(p_IsRequiredDRP && m_product_planning.getDD_NetworkDistribution_ID() > 0)
				{
					createDDOrder(AD_Org_ID, PP_MRP_ID, M_Product, QtyPlanned, DemandDateStartSchedule);
				}
				// Requisition
				else if (M_Product.isPurchased()) // then create M_Requisition
				{
					createRequisition(AD_Org_ID,PP_MRP_ID, M_Product, QtyPlanned ,DemandDateStartSchedule);
				}
				// Manufacturing Order
				else if (M_Product.isBOM())
				{
					createPPOrder(AD_Org_ID,PP_MRP_ID, M_Product,QtyPlanned, DemandDateStartSchedule);
				}
				else
				{
					// TODO: throw error ?
				}
			} // end for oqf
		}       
		else
		{
			log.info("No Create Plan");
		}
	}
	
	protected void createDDOrder(int AD_Org_ID, int PP_MRP_ID, MProduct product,BigDecimal QtyPlanned ,Timestamp DemandDateStartSchedule)
	{		
		//TODO vpj-cd I need to create logic for DRP-040 Shipment Due  Action Notice
		//Indicates that a shipment for a Order Distribution is due. 
		// Action should be taken at the source warehouse to ensure that the order is received on time.
		
		//TODO vpj-cd I need to create logic for DRP-050 Shipment Pas Due  Action Notice
		//Indicates that a shipment for a Order Distribution is past due. You should either delay the orders created the requirement for the product 
		//or expedite them when the product does arrive.
		
		if(m_product_planning.getDD_NetworkDistribution_ID() == 0)
		{
			//Indicates that the Product Planning Data for this product does not specify a valid network distribution.
			createMRPNote("DRP-060", AD_Org_ID, PP_MRP_ID, product , null , null , null);
		}
		MDDNetworkDistribution network = MDDNetworkDistribution.get(getCtx(),m_product_planning.getDD_NetworkDistribution_ID());
		MDDNetworkDistributionLine[] network_lines = network.getLines(m_product_planning.getM_Warehouse_ID());
		int M_Shipper_ID = 0;
		MDDOrder order = null;
		Integer DD_Order_ID = 0;

		for (MDDNetworkDistributionLine network_line : network_lines)
		{	
			//get supply source warehouse and locator
			MWarehouse source = MWarehouse.get(getCtx(), network_line.getM_WarehouseSource_ID());	
			MLocator locator = source.getDefaultLocator();
			
			//get supply target warehouse and locator
			MWarehouse target = MWarehouse.get(getCtx(), network_line.getM_Warehouse_ID());
			MLocator locator_to =target.getDefaultLocator(); 
			//get the transfer time
			BigDecimal transfertTime = network_line.getTransfertTime(); 
			if(transfertTime.compareTo(Env.ZERO) <= 0)
			{
				transfertTime = m_product_planning.getTransfertTime();
			}

			if (locator == null || locator_to == null)
			{
				String comment = Msg.translate(getCtx(), MDDNetworkDistributionLine.COLUMNNAME_M_WarehouseSource_ID)
								 + ":" + source.getName();
				createMRPNote("DRP-001", AD_Org_ID, PP_MRP_ID, product , null , null , comment);
				continue;
			}
			//get the warehouse in transit
			MWarehouse[] wsts = MWarehouse.getInTransitForOrg(getCtx(), source.getAD_Org_ID());

			if (wsts == null)
			{	
				
				String comment = Msg.translate(getCtx(), MOrg.COLUMNNAME_Name)
				 + ":" + MOrg.get(getCtx(), AD_Org_ID).getName();
				createMRPNote("DRP-010", AD_Org_ID, PP_MRP_ID, product , null , null , comment);
				continue;
			}

			if(network_line.getM_Shipper_ID()==0)
			{
				String comment = Msg.translate(getCtx(), MDDNetworkDistribution.COLUMNNAME_Name) 
				+ ":" + network.getName();
				createMRPNote("DRP-030", AD_Org_ID, PP_MRP_ID, product , null , null , comment);
				continue;
			}
			
			if(M_Shipper_ID != network_line.getM_Shipper_ID())
			{	

				//Org Must be linked to BPartner
				MOrg org = MOrg.get(getCtx(), locator_to.getAD_Org_ID());
				int C_BPartner_ID = org.getLinkedC_BPartner_ID(get_TrxName()); 
				if (C_BPartner_ID == 0)
				{
					String comment = Msg.translate(getCtx(), MOrg.COLUMNNAME_Name)
					 + ":" + MOrg.get(getCtx(), AD_Org_ID).getName();
					createMRPNote("DRP-020", AD_Org_ID, PP_MRP_ID, product, null , null , comment);
					continue;
				}
				
				MBPartner bp = getBPartner(C_BPartner_ID);
				// Try found some order with Shipper , Business Partner and Doc Status = Draft 
				// Consolidate the demand in a single order for each Shipper , Business Partner , DemandDateStartSchedule
				DD_Order_ID = getDDOrder_ID(AD_Org_ID,wsts[0].get_ID(),network_line.getM_Shipper_ID(), bp.getC_BPartner_ID(),DemandDateStartSchedule);
				if (DD_Order_ID < 0)
				{	
					order = new MDDOrder(getCtx() , 0 , get_TrxName());
					order.setAD_Org_ID(target.getAD_Org_ID());
					order.setC_BPartner_ID(C_BPartner_ID);
					//order.setAD_User_ID(bp.getPrimaryAD_User_ID());
					order.setAD_User_ID(m_product_planning.getPlanner_ID());
					order.setC_DocType_ID(DocTypeDO);  
					order.setM_Warehouse_ID(wsts[0].get_ID());
					order.setDocAction(MDDOrder.DOCACTION_Complete);
					order.setDateOrdered(getToday());                       
					order.setDatePromised(DemandDateStartSchedule);
					order.setM_Shipper_ID(network_line.getM_Shipper_ID());	    	                
					order.setIsInDispute(false);
					order.setIsInTransit(false);
					//order.setSalesRep_ID(m_product_planning.getPlanner_ID());
					order.setSalesRep_ID(bp.getPrimaryAD_User_ID());
					order.saveEx();
					DD_Order_ID = order.get_ID();				
					String key = network_line.getM_Shipper_ID()+"#"+C_BPartner_ID+"#"+DemandDateStartSchedule+"DR";
					dd_order_id_cache.put(key,DD_Order_ID);
				}	
				M_Shipper_ID = network_line.getM_Shipper_ID();
			}   

			BigDecimal QtyOrdered = QtyPlanned.multiply(network_line.getPercent()).divide(Env.ONEHUNDRED);

			MDDOrderLine oline = new MDDOrderLine(getCtx(), 0 , get_TrxName());
			oline.setDD_Order_ID(DD_Order_ID);
			oline.setM_Locator_ID(locator.getM_Locator_ID());
			oline.setM_LocatorTo_ID(locator_to.getM_Locator_ID());
			oline.setM_Product_ID(m_product_planning.getM_Product_ID()); 
			oline.setDateOrdered(getToday());                       
			oline.setDatePromised(DemandDateStartSchedule);
			oline.setQtyEntered(QtyOrdered);
			oline.setQtyOrdered(QtyOrdered);
			oline.setTargetQty(MPPMRP.getQtyReserved(getCtx(), target.getM_Warehouse_ID(), m_product_planning.getM_Product_ID(), DemandDateStartSchedule, get_TrxName()));
			oline.setIsInvoiced(false);
			oline.saveEx();



			// Set Correct Dates for Plan
			final String whereClause = MPPMRP.COLUMNNAME_DD_OrderLine_ID+"=?";
			List<MPPMRP> mrpList = new Query(getCtx(), MPPMRP.Table_Name, whereClause, get_TrxName())
										.setParameters(new Object[]{oline.getDD_OrderLine_ID()})
										.list();
			for (MPPMRP mrp : mrpList)
			{
				mrp.setDateOrdered(getToday());               
				mrp.setS_Resource_ID(m_product_planning.getS_Resource_ID());
				mrp.setDatePromised(TimeUtil.addDays(DemandDateStartSchedule , (m_product_planning.getDeliveryTime_Promised().add(transfertTime)).negate().intValue()));                                                            
				mrp.setDateFinishSchedule(DemandDateStartSchedule);
				mrp.saveEx();
			}
		}
		count_DO += 1;
		commit();
	}
	
	protected void createRequisition(int AD_Org_ID , int PP_MRP_ID, MProduct product,BigDecimal QtyPlanned, Timestamp DemandDateStartSchedule)
	{
		log.info("Create Requisition");
		
		// Duration = Lead Time + Transfer Time
		int duration = m_product_planning.getDeliveryTime_Promised().intValue()
							+ m_product_planning.getTransfertTime().intValue();

		MRequisition req = new  MRequisition(getCtx(),0, get_TrxName()); 
		req.setAD_Org_ID(AD_Org_ID);
		req.setAD_User_ID(m_product_planning.getPlanner_ID());                                                        
		req.setDateRequired(TimeUtil.addDays(DemandDateStartSchedule, 0 - duration));
		req.setDescription("Generate from MRP"); // TODO: add translation
		req.setM_Warehouse_ID(m_product_planning.getM_Warehouse_ID());
		req.setC_DocType_ID(DocTypeReq);
		req.setM_PriceList_ID();
		req.saveEx();



		MRequisitionLine reqline = new  MRequisitionLine(req);
		reqline.setLine(10);
		reqline.setAD_Org_ID(AD_Org_ID);
		reqline.setM_Product_ID(m_product_planning.getM_Product_ID());
		reqline.setPrice();
		reqline.setPriceActual(Env.ZERO);
		reqline.setQty(QtyPlanned);
		reqline.saveEx();



		// Set Correct Dates for Plan
		final String whereClause = MPPMRP.COLUMNNAME_M_Requisition_ID+"=?";
		List<MPPMRP> mrpList = new Query(getCtx(), MPPMRP.Table_Name, whereClause, get_TrxName())
									.setParameters(new Object[]{req.getM_Requisition_ID()})
									.list();
		for (MPPMRP mrp : mrpList)
		{
			mrp.setDateOrdered(getToday());
			mrp.setS_Resource_ID(m_product_planning.getS_Resource_ID());
			mrp.setDatePromised(req.getDateRequired());                                                            
			mrp.setDateStartSchedule(req.getDateRequired());                                                            
			mrp.setDateFinishSchedule(DemandDateStartSchedule);
			mrp.saveEx();

		}
		commit();	
		count_MR += 1;
	}
	
	protected void createPPOrder(int AD_Org_ID, int PP_MRP_ID, MProduct product,BigDecimal QtyPlanned,Timestamp DemandDateStartSchedule)
	{
		log.info("PP_Product_BOM_ID" + m_product_planning.getPP_Product_BOM_ID() + "AD_Workflow_ID" + m_product_planning.getAD_Workflow_ID());
		if (m_product_planning.getPP_Product_BOM_ID() == 0 || m_product_planning.getAD_Workflow_ID() == 0)
		{    
			throw new AdempiereException("@FillMandatory: @PP_Product_BOM_ID@, @AD_Workflow_ID@ ( @M_Product_ID@="+product.getValue()+")");
		}
		
		log.info("Manufacturing Order Create");                       
		MPPOrder order = new MPPOrder(getCtx(), 0, get_TrxName());
		order.setAD_Org_ID(AD_Org_ID);
		order.setLine(10);
		order.setC_DocTypeTarget_ID(DocTypeMO);
		order.setC_DocType_ID(DocTypeMO);  
		order.setS_Resource_ID(m_product_planning.getS_Resource_ID());
		order.setM_Warehouse_ID(m_product_planning.getM_Warehouse_ID());
		order.setM_Product_ID(m_product_planning.getM_Product_ID());
		order.setM_AttributeSetInstance_ID(0);
		order.setPP_Product_BOM_ID(m_product_planning.getPP_Product_BOM_ID());
		order.setAD_Workflow_ID(m_product_planning.getAD_Workflow_ID());
		order.setPlanner_ID(m_product_planning.getPlanner_ID());
		order.setQtyDelivered(Env.ZERO);
		order.setQtyReject(Env.ZERO);
		order.setQtyScrap(Env.ZERO);                                                        
		order.setDateOrdered(getToday());                       
		order.setDatePromised(DemandDateStartSchedule);

		if (m_product_planning.getDeliveryTime_Promised().signum() == 0)
			order.setDateStartSchedule(TimeUtil.addDays(DemandDateStartSchedule, (MPPMRP.getDays(order.getCtx(), order.getS_Resource_ID(), order.getAD_Workflow_ID(), QtyPlanned, get_TrxName()).add(m_product_planning.getTransfertTime())).negate().intValue()));
		else	                                                        	
			order.setDateStartSchedule(TimeUtil.addDays(DemandDateStartSchedule, (m_product_planning.getDeliveryTime_Promised().add(m_product_planning.getTransfertTime())).negate().intValue()));
		order.setDateFinishSchedule(DemandDateStartSchedule);
		order.setQty(QtyPlanned);
		order.setQtyBatchs(Env.ONE);
		order.setQtyBatchSize(QtyPlanned);
		order.setC_UOM_ID(product.getC_UOM_ID());
		order.setPosted(false);
		order.setProcessed(false);  
		order.setYield(Env.ZERO);
		order.setScheduleType(MPPMRP.TYPEMRP_Demand);
		order.setPriorityRule(MPPOrder.PRIORITYRULE_Medium);
		order.setDocStatus(MPPOrder.DOCSTATUS_Drafted);
		order.setDocAction(MPPOrder.DOCSTATUS_Completed);
		order.saveEx();
		commit();
		
		count_MO += 1;

	}
	
	private void deletePO(String tableName, String whereClause, Object[] params)
	{
		// TODO: refactor this method and move it to org.compiere.model.Query class
		POResultSet<PO> rs = new Query(getCtx(), tableName, whereClause, get_TrxName())
									.setParameters(params)
									.scroll();
		try {
			while(rs.hasNext()) {
				rs.next().deleteEx(true);
			}
		}
		finally {
			rs.close();
		}
		commit();
	}

	protected void createMRPNote(String code, int AD_Org_ID, int PP_MRP_ID, MProduct product,String documentNo,BigDecimal qty ,String comment)
	{
		documentNo = documentNo != null ? documentNo : "";
		comment = comment != null ? comment : "";
		qty = qty != null ? qty : Env.ZERO;
		
		MMessage msg = MMessage.get(getCtx(), code);
		int user_id = 0;
		if (PP_MRP_ID == 0 && m_product_planning != null)
		{
			user_id = m_product_planning.getPlanner_ID();
			String message = Msg.getMsg(getCtx(), msg.getValue());
			if (documentNo.length() > 0)
				message += " " + Msg.translate(getCtx(), MPPOrder.COLUMNNAME_DocumentNo) +":" + documentNo;
			if (qty !=  null)
				message += " " + Msg.translate(getCtx(), "QtyPlan") + ":" + qty;
			if (comment.length() > 0)
		        message +=  " " + comment;
		
			MNote note = new MNote(getCtx(),
								msg.getAD_Message_ID(),
								user_id,
								MPPMRP.Table_ID, PP_MRP_ID,
								product.getValue() + " " + product.getName(),
								message,
								get_TrxName());
			note.setAD_Org_ID(AD_Org_ID);
			note.saveEx();
			commit(); 
			log.info(code+": "+note.getTextMsg());  
			count_Msg += 1;
		}
	}
	
	private int getDDOrder_ID(int AD_Org_ID,int M_Warehouse_ID, int M_Shipper_ID,int C_BPartner_ID, Timestamp DatePromised)
	{
		String key = AD_Org_ID+"#"+M_Warehouse_ID+"#"+M_Shipper_ID+"#"+C_BPartner_ID+"#"+DatePromised+"DR";
		Integer order_id = dd_order_id_cache.get(key.toString());
		if ( order_id == null)
		{
			String sql = "SELECT DD_Order_ID FROM DD_Order WHERE AD_Org_ID=? AND M_Warehouse_ID=? AND M_Shipper_ID = ? AND C_BPartner_ID=? AND DatePromised=? AND DocStatus=?";
			order_id = DB.getSQLValueEx(get_TrxName(), sql, 
				new Object[]{	AD_Org_ID,
								M_Warehouse_ID,
								M_Shipper_ID,
								C_BPartner_ID,
								DatePromised,
								MDDOrder.DOCSTATUS_Drafted });
			if(order_id > 0)
				dd_order_id_cache.put(key,order_id);
		}
		return order_id;
	}
	
	private MBPartner getBPartner(int C_BPartner_ID)
	{
		MBPartner partner = partner_cache.get(C_BPartner_ID);
		if ( partner == null)
		{	
			 partner = MBPartner.get(getCtx(), C_BPartner_ID);
			 partner_cache.put(C_BPartner_ID, partner);
		}
		return partner;
	}
	
	/**
	 * return ScheduledReceipts to cover the ProjectQtyOnhand
	 * @param AD_Client_ID
	 * @param AD_Org_ID
	 * @param M_Warehouse_ID
	 * @param M_Product
	 * @param ProjectQtyOnhand
	 * @param DemandDateStartSchedule
	 * @return
	 */
	private BigDecimal getNetRequierements(int AD_Client_ID, int AD_Org_ID, 
											int M_Warehouse_ID, MProduct M_Product,
											Timestamp DemandDateStartSchedule)
	{
		BigDecimal QtyNetReqs = QtyProjectOnHand.subtract(QtyGrossReqs);
		final String whereClause =  " AD_Client_ID=? AND AD_Org_ID=?"
			+ " AND M_Product_ID = ?"
			+ " AND M_Warehouse_ID = ?"
		  	+ " AND DocStatus IN (?,?) AND IsAvailable = ? AND TypeMRP = ?";
		ArrayList<Object> parameters= new ArrayList<Object>();
		parameters.add(AD_Client_ID);
		parameters.add(AD_Org_ID);
		parameters.add(M_Product.get_ID());
		parameters.add(M_Warehouse_ID);
		parameters.add(DocAction.STATUS_Completed);
		parameters.add(DocAction.STATUS_InProgress);
		parameters.add(true);
		parameters.add(MPPMRP.TYPEMRP_Supply);
		  
		Collection<MPPMRP> mrps = new Query(getCtx(), MPPMRP.Table_Name, whereClause, get_TrxName())
										.setParameters(parameters)
										.setOrderBy(MPPMRP.COLUMNNAME_DateStartSchedule)
										.list();
		for (MPPMRP mrp : mrps)
		{
			QtyScheduledReceipts = QtyScheduledReceipts.add(mrp.getQty());
			
			if(DemandDateStartSchedule != null)
			{
				//MRP-030 De-Expedite Action Notice
				//Indicates that a schedule supply order is due before it is needed and should be delayed,
				//or demand rescheduled to an earlier date.
				if(QtyNetReqs.negate().signum() > 0
						&& mrp.getDateStartSchedule().compareTo(DemandDateStartSchedule) < 0)
				{
					String comment = Msg.translate(getCtx(), MPPMRP.COLUMNNAME_DateStartSchedule)
									 + ":" + mrp.getDateStartSchedule()
									 + " " + Msg.translate(getCtx(), MPPMRP.COLUMNNAME_DatePromised)
									 + ":" + DemandDateStartSchedule;
					createMRPNote("MRP-030",  AD_Org_ID, mrp.get_ID(), M_Product,
								MPPMRP.getDocumentNo(mrp.get_ID()), mrp.getQty(), comment);
				}
				
				//MRP-040 Expedite Action Notice
				//Indicates that a scheduled supply order is due after is needed and should be rescheduled to
				//an earlier date or demand rescheduled to a later date.		
				if(QtyNetReqs.negate().signum() < 0
						&& mrp.getDateStartSchedule().compareTo(DemandDateStartSchedule) > 0)
				{
					String comment = Msg.translate(getCtx(), MPPMRP.COLUMNNAME_DateStartSchedule)
									 + ":" + mrp.getDateStartSchedule()
									 + " " + Msg.translate(getCtx(), MPPMRP.COLUMNNAME_DatePromised)
									 + ":" + DemandDateStartSchedule;
					createMRPNote("MRP-040", AD_Org_ID, mrp.get_ID(), M_Product,
							MPPMRP.getDocumentNo(mrp.get_ID()), mrp.getQty(), comment);
				}
				
				//TODO vpj-cd I need to create logic for MRP-060 Release Due For  Action Notice in time
				//Indicate that a supply order should be released. if it is a draft order , it must also be approved.
				// if(date release > today && date release + after floating)
				
				//TODO vpj-cd I need to create logic for MRP-070 Release Past Due For  Action Notice overdue
				//Indicates that a supply order was not released when it was due, and should be either released 
				//or expedited now, or the demand rescheduled for a later date.
				//if (date release < today && date erelese + before floating)
				
				
				//MRP-110 Past Due  Action Notice
				//Indicates that a schedule supply order receipt is past due.		
				if(mrp.getDatePromised().compareTo(getToday()) < 0)
				{
					String comment =  Msg.translate(getCtx(), MPPMRP.COLUMNNAME_DatePromised)
									 + ":" + mrp.getDatePromised();
					createMRPNote("MRP-110", AD_Org_ID, mrp.get_ID(), M_Product, 
								MPPMRP.getDocumentNo(mrp.get_ID()), mrp.getQty(), comment);
				}
				
				mrp.setIsAvailable(false);
				mrp.saveEx();
				
				QtyNetReqs.add(mrp.getQty());
				
				if (QtyNetReqs.signum() == 0)
				{
					return QtyNetReqs;
				}
			}	
			else
			{
				//MRP-050 Cancel Action Notice
				//Indicate that a scheduled supply order is no longer needed and should be deleted.
				if(QtyScheduledReceipts.signum() > 0)
				{
					String comment =  Msg.translate(getCtx(), MPPMRP.COLUMNNAME_DatePromised)
					 				+ ":" + mrp.getDatePromised();
					createMRPNote("MRP-050", AD_Org_ID, mrp.get_ID(), M_Product,
								MPPMRP.getDocumentNo(mrp.get_ID()), mrp.getQty(), comment);
				}
				QtyNetReqs.add(mrp.getQty());
				mrp.setIsAvailable(false);
				mrp.saveEx();	
			}
		}

		return QtyNetReqs;
	}
	
	protected int getDocType(String docBaseType)
	{
		MDocType[] doc = MDocType.getOfDocBaseType(getCtx(), docBaseType);

		if (doc==null || doc.length == 0) 
		{
			String reference = Msg.getMsg(getCtx(), "SequenceDocNotFound");
			String textMsg = "Not found default document type for docbasetype "+ docBaseType;
			MNote note = new MNote(getCtx(), MMessage.getAD_Message_ID (getCtx(), "SequenceDocNotFound"),
									Planner_ID, MPPMRP.Table_ID, 0,
									reference,
									textMsg,
									get_TrxName());
			note.saveEx();			
			throw new AdempiereException(textMsg);
		} 
		else
			return doc[0].getC_DocType_ID();
	}
}

