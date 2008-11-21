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
import org.eevolution.model.MDDNetworkDistribution;
import org.eevolution.model.MDDNetworkDistributionLine;
import org.eevolution.model.MDDOrder;
import org.eevolution.model.MDDOrderLine;
import org.eevolution.model.MPPMRP;
import org.eevolution.model.MPPOrder;
import org.eevolution.model.MPPProductBOM;
import org.eevolution.model.MPPProductPlanning;
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
	private MPPProductPlanning m_product_planning = null;
	private int Planner_ID = 0;
	private BigDecimal QtyProjectOnHand = Env.ZERO;
	private BigDecimal QtyNetReqs = Env.ZERO;
	private BigDecimal QtyPlanned = Env.ZERO;
	private BigDecimal QtyGrossReqs = Env.ZERO;
	private BigDecimal QtyScheduledReceipts = Env.ZERO;
	private Timestamp DatePromisedFrom = null;
	private Timestamp DatePromisedTo = null;
	private Timestamp Today = new Timestamp (System.currentTimeMillis());  
	private Timestamp Planning_Horizon = null;
	private int count_MO = 0;
	private int count_MR = 0;
	private int count_DO = 0;
	private int count_Msg = 0;

	private int DocTypeReq = 0;
	private int DocTypeMO = 0; 
	private int DocTypeDO = 0;
	
	private static CCache<String ,Integer				>   dd_order_id_cache 	= new CCache<String,Integer>(MDDOrder.COLUMNNAME_DD_Order_ID, 50);
	private static CCache<Integer,MBPartner				>   partner_cache 	= new CCache<Integer,MBPartner>(MBPartner.Table_Name, 50);



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
		MDocType[] doc = MDocType.getOfDocBaseType(getCtx(), MDocType.DOCBASETYPE_PurchaseRequisition);

		if (doc==null || doc.length == 0) 
		{
			log.log(Level.SEVERE,"Not found default document type for docbasetype "+ MDocType.DOCBASETYPE_PurchaseRequisition);                                                                                                                       
			MNote note = new MNote(getCtx(), MMessage.getAD_Message_ID (getCtx(), "SequenceDocNotFound"), Planner_ID,MPPMRP.Table_ID, 0,Msg.getMsg(getCtx(), "SequenceDocNotFound"),Msg.getMsg(getCtx(), "SequenceDocNotFound"),get_TrxName());
			note.saveEx();			
			return Msg.getMsg(getCtx(), "SequenceDocNotFound");
		} 
		else
			DocTypeReq = doc[0].getC_DocType_ID();

		doc = MDocType.getOfDocBaseType(getCtx(), MDocType.DOCBASETYPE_ManufacturingOrder);

		if (doc==null || doc.length == 0) {
			log.severe ("Not found default document type for docbasetype " +  MDocType.DOCBASETYPE_ManufacturingOrder);                                                           
			MNote note = new MNote (getCtx(),  MMessage.getAD_Message_ID (getCtx(), "SequenceDocNotFound"), Planner_ID , MPPMRP.Table_ID , 0 ,  Msg.getMsg(getCtx(), "SequenceDocNotFound") , Msg.getMsg(getCtx(), "SequenceDocNotFound"),get_TrxName());    
			note.saveEx();
			return Msg.getMsg(getCtx(), Msg.getMsg(getCtx(), "SequenceDocNotFound"));
		}
		else
			DocTypeMO = doc[0].getC_DocType_ID();

		doc = MDocType.getOfDocBaseType(getCtx(), MDocType.DOCBASETYPE_DistributionOrder);

		if (doc==null || doc.length == 0) {
			log.severe ("Not found default document type for docbasetype " + MDocType.DOCBASETYPE_DistributionOrder);                                                           
			MNote note = new MNote (getCtx(),  MMessage.getAD_Message_ID (getCtx(), "SequenceDocNotFound"), Planner_ID , MPPMRP.Table_ID , 0 ,  Msg.getMsg(getCtx(), "SequenceDocNotFound") , Msg.getMsg(getCtx(), "SequenceDocNotFound"),get_TrxName());    
			note.saveEx();
			return Msg.getMsg(getCtx(), Msg.getMsg(getCtx(), "SequenceDocNotFound"));
		}
		else
			DocTypeDO = doc[0].getC_DocType_ID();


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
			Planning_Horizon = TimeUtil.addDays(Today, plant.getPlanningHorizon()); 
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
				result = result + "<br> " +Msg.translate(getCtx(), "AD_Note_ID")+":"+count_MR;
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
	private void deleteMRP(int AD_Client_ID, int AD_Org_ID,int S_Resource_ID, int M_Warehouse_ID)
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
		

	}

	/**************************************************************************
	 *  Calculate plan
	 *  @param AD_Client_ID Client ID
	 *  @param AD_Org_ID Organization ID
	 *  @param M_Warehuse_ID Warehouse ID
	 */
	public String runMRP(int AD_Client_ID , int AD_Org_ID, int S_Resource_ID , int M_Warehouse_ID)
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
			
			// Mark all supply MRP records as available
			
			DB.executeUpdateEx("UPDATE PP_MRP SET IsAvailable ='Y' WHERE TypeMRP = 'S'  AND AD_Client_ID = ?  AND AD_Org_ID=? AND M_Warehouse_ID=?", new Object[]{AD_Client_ID,AD_Org_ID,M_Warehouse_ID} ,get_TrxName());
			
			commit();
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
						+" WHERE mrp.TypeMRP=? AND mrp.AD_Client_ID = ? AND mrp.AD_Org_ID=? "
						+ " AND M_Warehouse_ID=? "
						+ " AND mrp.DatePromised <= ?"
						//+ " AND mrp.M_Product_ID=1004253 "
						+ " AND COALESCE(p.LowLevel,0) = ? "
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
					String TypeMRP = rs.getString(MPPMRP.COLUMNNAME_TypeMRP);
					String OrderType = rs.getString(MPPMRP.COLUMNNAME_OrderType);
					Timestamp DatePromised = rs.getTimestamp(MPPMRP.COLUMNNAME_DatePromised);
					Timestamp DateStartSchedule = rs.getTimestamp(MPPMRP.COLUMNNAME_DateStartSchedule);

					// if demand is a forecast and this is minor today then is ignore this QtyGrossReq
					if (MPPMRP.TYPEMRP_Demand.equals(TypeMRP)
							&& MPPMRP.ORDERTYPE_Forecast.equals(OrderType)
							&& DatePromised.compareTo(Today) <= 0)
					{
						continue;  
					}

					// New Product
					if (product == null || product.get_ID() != rs.getInt("M_Product_ID"))
					{
						product = MProduct.get(getCtx(), rs.getInt("M_Product_ID"));
						log.info("Calculte Plan to this Product:" + product.getName());
						
						//if exist QtyGrossReq of last Demand verify plan
						if (QtyGrossReqs.signum() != 0)
						{							
							if (MPPProductPlanning.ORDER_POLICY_PeriodOrderQuantity.equals(m_product_planning.getOrder_Policy()) &&  POQDateStartSchedule.compareTo(Planning_Horizon) < 0) 
							{
								BeforeDateStartSchedule =  POQDateStartSchedule; 
								calculatePlan(AD_Org_ID,BeforePP_MRP_ID , product, QtyGrossReqs ,BeforeDateStartSchedule);
							}
							else if (MPPProductPlanning.ORDER_POLICY_LoteForLote.equals(m_product_planning.getOrder_Policy()) && BeforeDateStartSchedule.compareTo(Planning_Horizon) <= 0 )
							{
								calculatePlan(AD_Org_ID,BeforePP_MRP_ID , product, QtyGrossReqs ,BeforeDateStartSchedule );
							}
							QtyGrossReqs = Env.ZERO;
						}


						// Define m_product_planning
						setProduct(AD_Client_ID,AD_Org_ID ,S_Resource_ID , M_Warehouse_ID,  product);
						
						// If No Product Planning found, go to next MRP record 
						if (m_product_planning == null)
							continue;	  
							
						//first DatePromised.compareTo for ORDER_POLICY_PeriodOrderQuantity
						if (MPPProductPlanning.ORDER_POLICY_PeriodOrderQuantity.equals(m_product_planning.getOrder_Policy()))
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
					                                          
					BeforePP_MRP_ID = rs.getInt(MPPMRP.COLUMNNAME_PP_MRP_ID);

					// Create Notice for Demand due
					if(DatePromised.compareTo(Today) < 0)
					{
						createMRPNote("MRP-040", rs.getInt(MPPMRP.COLUMNNAME_PP_MRP_ID), product);
					}
					
					if (MPPProductPlanning.ORDER_POLICY_PeriodOrderQuantity.equals(m_product_planning.getOrder_Policy()))
					{
						// Verify if is DatePromised < DatePromisedTo then Accumulation QtyGrossReqs 
						if (DatePromisedTo != null && DatePromised.compareTo(DatePromisedTo) < 0)
						{
							QtyGrossReqs = QtyGrossReqs.add(rs.getBigDecimal(MPPMRP.COLUMNNAME_Qty));
							log.info("Accumulation   QtyGrossReqs:" + QtyGrossReqs);
							log.info("DatePromised:" + DatePromised);
							log.info("DatePromisedTo:" + DatePromisedTo);
							continue;
						}
						else
						{ // if not then create new range for next period
							BeforeDateStartSchedule =  POQDateStartSchedule; 
							calculatePlan(AD_Org_ID,rs.getInt(MPPMRP.COLUMNNAME_PP_MRP_ID),product, QtyGrossReqs ,BeforeDateStartSchedule);										
							QtyGrossReqs = rs.getBigDecimal(MPPMRP.COLUMNNAME_Qty); 
							DatePromisedFrom = DatePromised;
							DatePromisedTo = TimeUtil.addDays(DatePromised, m_product_planning.getOrder_Period().intValue());         
							POQDateStartSchedule = (level == 0 ? DatePromised : DateStartSchedule);
							continue;
						}
					}
					// If  Order_Policy = LoteForLote then always create new range for next period and put QtyGrossReqs          
					else if (MPPProductPlanning.ORDER_POLICY_LoteForLote.equals(m_product_planning.getOrder_Policy()))
					{                                                                                                                                           
						QtyGrossReqs = rs.getBigDecimal(MPPMRP.COLUMNNAME_Qty);
						BeforeDateStartSchedule =   rs.getTimestamp(MPPMRP.COLUMNNAME_DatePromised); 		
						calculatePlan(AD_Org_ID,rs.getInt(MPPMRP.COLUMNNAME_PP_MRP_ID),product,QtyGrossReqs,BeforeDateStartSchedule); 		
						continue;
					}                                                                        
				} // end while

				//if exist QtyGrossReq of last Demand after finish while verify plan
				if (QtyGrossReqs.signum() != 0)
				{   
					if (MPPProductPlanning.ORDER_POLICY_PeriodOrderQuantity.equals(m_product_planning.getOrder_Policy()) &&  POQDateStartSchedule.compareTo(Planning_Horizon) < 0) 
					{
						BeforeDateStartSchedule =  POQDateStartSchedule; 
						calculatePlan(AD_Org_ID,BeforePP_MRP_ID , product, QtyGrossReqs ,BeforeDateStartSchedule);
					}
					else if (MPPProductPlanning.ORDER_POLICY_LoteForLote.equals(m_product_planning.getOrder_Policy()) && BeforeDateStartSchedule.compareTo(Planning_Horizon) <= 0 )
					{
						calculatePlan(AD_Org_ID,BeforePP_MRP_ID , product, QtyGrossReqs ,BeforeDateStartSchedule );
					}	
					
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
		//find data product planning demand 
		MPPProductPlanning pp = MPPProductPlanning.find(getCtx() ,AD_Org_ID , M_Warehouse_ID, S_Resource_ID , product.getM_Product_ID(), get_TrxName());  
		DatePromisedTo = null;
		DatePromisedFrom = null;
		if (pp != null)
		{
			m_product_planning = new MPPProductPlanning(getCtx(), 0 , null);                                                       
			MPPProductPlanning.copyValues(pp, m_product_planning);
			//Find the BOM to this Product
			if (m_product_planning.getPP_Product_BOM_ID() <= 0 && product.isBOM())
			{
				m_product_planning.setPP_Product_BOM_ID(MPPProductBOM.getBOMSearchKey(getCtx(), product));  
			}      
			if (m_product_planning.getAD_Workflow_ID() <= 0 && product.isBOM())
			{
				m_product_planning.setAD_Workflow_ID(MWorkflow.getWorkflowSearchKey(getCtx(), product));  
			} 
			if (m_product_planning.getPlanner_ID() <= 0)
			{
				m_product_planning.setPlanner_ID(Planner_ID);
			}
			if(m_product_planning.getM_Warehouse_ID() <= 0)
			{
				m_product_planning.setM_Warehouse_ID(M_Warehouse_ID);
			}
			if (m_product_planning.getS_Resource_ID() <= 0)
			{
				m_product_planning.setS_Resource_ID(S_Resource_ID);
			}
			if (m_product_planning.getOrder_Policy() == null)
			{
				m_product_planning.setOrder_Policy(MPPProductPlanning.ORDER_POLICY_LoteForLote);
			}
			m_product_planning.dump();
		}
		else
		{
			createMRPNote("MRP-120", 0, product);
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
				if(C_BPartner_ID == 0)
					m_product_planning.setIsCreatePlan(false);
			}
			if (product.isBOM())
			{	
				if (m_product_planning.getAD_Workflow_ID() == 0 )
					log.info("Error: Do not exist workflow ("+product.getValue()+")");
			}
		}	

		QtyProjectOnHand = MPPMRP.getQtyOnHand(getCtx(), m_product_planning.getM_Warehouse_ID() , m_product_planning.getM_Product_ID(), get_TrxName());
		if(m_product_planning.getSafetyStock().signum() > 0
				&& m_product_planning.getSafetyStock().compareTo(QtyProjectOnHand) > 0)
		{
			QtyProjectOnHand =  QtyProjectOnHand.subtract(m_product_planning.getSafetyStock());
		}
		log.info("QtyOnHand :" + QtyProjectOnHand);


		final String whereClause = "DocStatus = ? AND IsAvailable = ? AND TypeMRP = ?"
							+ " AND M_Product_ID = ?"
							+ " AND AD_Client_ID = ? AND AD_Org_ID = ?"
							+ " AND M_Warehouse_ID = ?";
		Object[] params = new Object[]{
										DocAction.STATUS_Completed,
										"Y", // Available
										MPPMRP.TYPEMRP_Supply,
										m_product_planning.getM_Product_ID(),
										AD_Client_ID, AD_Org_ID,
										m_product_planning.getM_Warehouse_ID()
		};
		QtyScheduledReceipts = DB.getSQLValueBD(null, "SELECT COALESCE(SUM(Qty),0) FROM PP_MRP WHERE "+whereClause, params);
		DB.executeUpdateEx("UPDATE PP_MRP SET IsAvailable = 'N' WHERE "+whereClause, params, get_TrxName());
		log.info("QtyScheduledReceipts :" + QtyScheduledReceipts);

		//QtyProjectOnHand =  QtyProjectOnHand.add(QtyScheduledReceipts);
		//QtyScheduledReceipts = Env.ZERO;
	}

	/**************************************************************************
	 * 	Calculate Plan this product
	 *	@param PP_MRP_ID MRP ID
	 *  @param M_Warehouse_ID Warehoue ID
	 *  @param product Product
	 *  @param Qty Qty
	 *  @param DemandDateStartSchedule Demand Date Start Schedule
	 */
	private void calculatePlan(int AD_Org_ID, int PP_MPR_ID , MProduct product , BigDecimal Qty, Timestamp DemandDateStartSchedule)        
	{
		if (m_product_planning.isCreatePlan() == false)
			return;

		//Set Yield o QtyGrossReqs
		// Note : the variables  DemandDateStartSchedule , DemandDateFinishSchedule are same DatePromised to Demands Sales Order Type

		log.info("Create Plan ...");

		BigDecimal  DecimalYield = new BigDecimal(m_product_planning.getYield()/100);
		if (DecimalYield.signum() != 0)
			QtyGrossReqs = QtyGrossReqs.divide(DecimalYield, 4, BigDecimal.ROUND_HALF_UP);

		QtyNetReqs = ((QtyScheduledReceipts).add(QtyProjectOnHand)).subtract(QtyGrossReqs);

		log.info("             Planning Data :" + product.getName() + " Create Plan:" + m_product_planning.isCreatePlan() + " OrderPlan:" + QtyPlanned);
		log.info(" Demand Date Start Schedule:" + DemandDateStartSchedule);
		log.info("     Delivery Time Promised:" + m_product_planning.getDeliveryTime_Promised());
		log.info("           DatePromisedFrom:" + DatePromisedFrom + " DatePromisedTo:" +   DatePromisedTo);    
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

		// Check Order Min 
		if(QtyPlanned.signum() > 0 && m_product_planning.getOrder_Min().signum() > 0)
		{    
			QtyPlanned = QtyPlanned.max(m_product_planning.getOrder_Min());
			if (m_product_planning.getOrder_Min().compareTo(QtyPlanned) > 0)
			{
				createMRPNote("MRP-080", PP_MPR_ID, product);
			}
		}

		// Check Order Max                                                
		if(QtyPlanned.compareTo(m_product_planning.getOrder_Max()) > 0 && m_product_planning.getOrder_Max().signum() > 0)
		{    
			createMRPNote("MRP-090", PP_MPR_ID, product); 
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

		if (QtyPlanned.signum() > 0)    
		{
			int loops = 1;
			if (m_product_planning.getOrder_Policy().equals(MPPProductPlanning.ORDER_POLICY_FixedOrderQuantity))
			{    
				if (m_product_planning.getOrder_Qty().compareTo(Env.ZERO) != 0)
					loops = (QtyPlanned.divide(m_product_planning.getOrder_Qty() , 0 , BigDecimal.ROUND_UP)).intValue();
				QtyPlanned = m_product_planning.getOrder_Qty();
			}

			for (int ofq = 1 ; ofq <= loops ; ofq ++ )
			{
				log.info("Is Purchased: "+ product.isPurchased()+ " Is BOM: " +  product.isBOM());

				// Distribution Order
				if(p_IsRequiredDRP && m_product_planning.getDD_NetworkDistribution_ID() > 0)
				{
					createDDOrder(AD_Org_ID, PP_MPR_ID, product, DemandDateStartSchedule);
				}
				// Requisition
				else if (product.isPurchased()) // then create M_Requisition
				{
					createRequisition(AD_Org_ID,PP_MPR_ID, product, DemandDateStartSchedule);
				}
				// Manufacturing Order
				else if (product.isBOM())
				{
					createPPOrder(AD_Org_ID,PP_MPR_ID, product, DemandDateStartSchedule);
				}
			} // end for oqf
		}       
		else
		{
			log.info("No Create Plan");
		}
	}
	
	private void createDDOrder(int AD_Org_ID,int PP_MRP_ID, MProduct product, Timestamp DemandDateStartSchedule)
	{
		
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
				createMRPNote("DRP-001", PP_MRP_ID, product);
				continue;
			}
			//get the warehouse in transit
			MWarehouse[] wsts = MWarehouse.getInTransitForOrg(getCtx(), source.getAD_Org_ID());

			if (wsts == null)
			{
				createMRPNote("DRP-010", PP_MRP_ID, product);
				continue;
			}

			if(network_line.getM_Shipper_ID()==0)
			{
				createMRPNote("DRP-030", PP_MRP_ID, product);
				continue;
			}
			
			if(M_Shipper_ID != network_line.getM_Shipper_ID())
			{	

				//Org Must be linked to BPartner
				MOrg org = MOrg.get(getCtx(), locator_to.getAD_Org_ID());
				int C_BPartner_ID = org.getLinkedC_BPartner_ID(get_TrxName()); 
				if (C_BPartner_ID == 0)
				{
					createMRPNote("DRP-020", PP_MRP_ID, product);
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
					order.setDateOrdered(Today);                       
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
			oline.setDateOrdered(Today);                       
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
			for (MPPMRP mrp : mrpList) {
				mrp.setDateOrdered(Today);               
				mrp.setS_Resource_ID(m_product_planning.getS_Resource_ID());
				mrp.setDatePromised(TimeUtil.addDays(DemandDateStartSchedule , (m_product_planning.getDeliveryTime_Promised().add(transfertTime)).negate().intValue()));                                                            
				mrp.setDateFinishSchedule(DemandDateStartSchedule);
				mrp.saveEx();
				

			}
		}
		count_DO += 1;
		commit();
	}
	
	private void createRequisition(int AD_Org_ID , int PP_MRP_ID, MProduct product, Timestamp DemandDateStartSchedule)
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
		for (MPPMRP mrp : mrpList) {
			mrp.setDateOrdered(Today);
			mrp.setS_Resource_ID(m_product_planning.getS_Resource_ID());
			mrp.setDatePromised(req.getDateRequired());                                                            
			mrp.setDateStartSchedule(req.getDateRequired());                                                            
			mrp.setDateFinishSchedule(DemandDateStartSchedule);
			mrp.saveEx();

		}
		commit();	
		count_MR += 1;
	}
	
	private void createPPOrder(int AD_Org_ID, int PP_MRP_ID, MProduct product, Timestamp DemandDateStartSchedule)
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
		order.setDateOrdered(Today);                       
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

	private void createMRPNote(String code, int PP_MRP_ID, MProduct product)
	{
		MMessage msg = MMessage.get(getCtx(), code);
		int user_id = 0;
		if (PP_MRP_ID == 0 && m_product_planning != null)
		{
			user_id = m_product_planning.getPlanner_ID();
		}
		
		MNote note = new MNote(getCtx(),
							msg.getAD_Message_ID(),
							user_id,
							MPPMRP.Table_ID, PP_MRP_ID,
							product.getValue() + " " + product.getName(),
							Msg.getMsg(getCtx(), msg.getValue()),
							get_TrxName());
		note.saveEx();
		commit();
		log.info(code+": "+note.getTextMsg());  
		count_Msg += 1;
	}
	
	private int getDDOrder_ID(int AD_Org_ID,int M_Warehouse_ID, int M_Shipper_ID,int C_BPartner_ID, Timestamp DatePromised)
	{
		String key = AD_Org_ID+"#"+M_Warehouse_ID+"#"+M_Shipper_ID+"#"+C_BPartner_ID+"#"+DatePromised+"DR";
		Integer order_id = dd_order_id_cache.get(key.toString());
		if ( order_id == null)
		{	
			 order_id = DB.getSQLValue(get_TrxName(), "SELECT DD_Order_ID FROM DD_Order WHERE AD_Org_ID=? AND M_Warehouse_ID=? AND M_Shipper_ID = ? AND C_BPartner_ID=? AND DatePromised=? AND DocStatus=?", 
					 new Object[]{	AD_Org_ID,
				 					M_Warehouse_ID,
				 					M_Shipper_ID,
				 					C_BPartner_ID,
				 					DatePromised,
				 					"DR"});
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
}

