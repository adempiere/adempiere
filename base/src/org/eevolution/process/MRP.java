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

package org.eevolution.process;


import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MDocType;
import org.compiere.model.MLocator;
import org.compiere.model.MMessage;
import org.compiere.model.MNote;
import org.compiere.model.MOrg;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPO;
import org.compiere.model.MRequisition;
import org.compiere.model.MRequisitionLine;
import org.compiere.model.MSequence;
import org.compiere.model.MWarehouse;
import org.compiere.model.PO;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;
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
 *  @version $Id: CreateCost.java,v 1.1 2004/06/22 05:24:03 vpj-cd Exp $
 */
public class MRP extends SvrProcess
{
	private int     m_AD_Client_ID  = 0;		
	private int     p_AD_Org_ID     = 0;
	private int     p_S_Resource_ID = 0 ;
	private int     p_M_Warehouse_ID= 0;
	private boolean p_IsRequiredDRP = true;
	private String  p_Version = "1";       
	private String  result = "";

	//Global Variables
	private MPPProductPlanning m_product_planning = null;
	private int Planner_ID = 0;
	private BigDecimal QtyProjectOnHand = Env.ZERO;
	private BigDecimal QtyNetReqs = Env.ZERO;
	private BigDecimal QtyPlanned = Env.ZERO;
	private BigDecimal QtyGrossReqs = Env.ZERO;
	private BigDecimal Order_Period = Env.ZERO;
	private BigDecimal QtyScheduledReceipts = Env.ZERO;
	private Timestamp DatePromisedFrom = null;
	private Timestamp DatePromisedTo = null;

	private int DocTypeReq = 0;
	private int DocTypeMO = 0;
	private int DocTypeDO = 0;


	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		m_AD_Client_ID = Integer.parseInt(Env.getContext(getCtx(), "#AD_Client_ID"));
		Planner_ID = Integer.parseInt(Env.getContext(getCtx(), "#AD_User_ID"));
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
				p_IsRequiredDRP = ((String)para[i].getParameter()).equals('Y');        
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
			note.save();
			return Msg.getMsg(getCtx(), "SequenceDocNotFound");
		} 
		else
			DocTypeReq = doc[0].getC_DocType_ID();

		doc = MDocType.getOfDocBaseType(getCtx(), MDocType.DOCBASETYPE_MaintenanceOrder);

		if (doc==null || doc.length == 0) {
			log.severe ("Not found default document type for docbasetype " +  MDocType.DOCBASETYPE_MaintenanceOrder);                                                           
			MNote note = new MNote (getCtx(),  MMessage.getAD_Message_ID (getCtx(), "SequenceDocNotFound"), Planner_ID , MPPMRP.Table_ID , 0 ,  Msg.getMsg(getCtx(), "SequenceDocNotFound") , Msg.getMsg(getCtx(), "SequenceDocNotFound"),get_TrxName());    
			note.save();
			return Msg.getMsg(getCtx(), Msg.getMsg(getCtx(), "SequenceDocNotFound"));
		}
		else
			DocTypeMO = doc[0].getC_DocType_ID();

		doc = MDocType.getOfDocBaseType(getCtx(), MDocType.DOCBASETYPE_DistributionOrder);

		if (doc==null || doc.length == 0) {
			log.severe ("Not found default document type for docbasetype " + MDocType.DOCBASETYPE_DistributionOrder);                                                           
			MNote note = new MNote (getCtx(),  MMessage.getAD_Message_ID (getCtx(), "SequenceDocNotFound"), Planner_ID , MPPMRP.Table_ID , 0 ,  Msg.getMsg(getCtx(), "SequenceDocNotFound") , Msg.getMsg(getCtx(), "SequenceDocNotFound"),get_TrxName());    
			note.save();
			return Msg.getMsg(getCtx(), Msg.getMsg(getCtx(), "SequenceDocNotFound"));
		}
		else
			DocTypeDO = doc[0].getC_DocType_ID();

		log.info("Type Document to Requisition:"+ DocTypeReq);
		log.info("Type Document to MO:" + DocTypeMO);
		log.info("Type Document to MO:" + DocTypeDO);
		if(p_M_Warehouse_ID==0)
		{
			MWarehouse[] ws = MWarehouse.getForOrg(getCtx(), p_AD_Org_ID);
			for(MWarehouse w : ws)
			{	 

				runMRP(m_AD_Client_ID,p_AD_Org_ID,p_S_Resource_ID,w.getM_Warehouse_ID());
				result = result + "finish MRP to Warehouse " +w.getName();
			}
		}
		else
		{
			runMRP(m_AD_Client_ID,p_AD_Org_ID,p_S_Resource_ID,p_M_Warehouse_ID);
		}

		return result;
	} 


	/**************************************************************************
	 * 	Delete old record in MRP table to calculate again MRP and Document with Draft status 
	 *	@param AD_Client_ID Client_ID
	 *	@param AD_Org_ID Orgganization ID
	 *  @param M_Warehouse_ID Warehouse ID
	 *  @return false if exist an error
	 */
	public boolean deleteMRP(int AD_Client_ID, int AD_Org_ID, int M_Warehouse_ID)
	{
		// Delete Manufacturing Order with Status Draft or Close from MRP Table
		String sql = "DELETE FROM PP_MRP  WHERE TypeMRP = 'MOP' AND DocStatus IN ('DR','CL') AND AD_Client_ID=" + AD_Client_ID  + " AND AD_Org_ID=" + AD_Org_ID + " AND M_Warehouse_ID="+M_Warehouse_ID;				
		DB.executeUpdate(sql, get_TrxName());

		// Delete Requisition with Status Draft from MRP Table
		sql = "DELETE FROM PP_MRP WHERE TypeMRP = 'POR' AND DocStatus='DR' AND AD_Client_ID = " + AD_Client_ID +  " AND AD_Org_ID=" + AD_Org_ID+ " AND M_Warehouse_ID="+M_Warehouse_ID;				
		DB.executeUpdate(sql, get_TrxName());

		// Delete Action Notice
		sql = "DELETE FROM AD_Note WHERE AD_Table_ID =  " + MPPMRP.Table_ID + " AND AD_Client_ID = " + AD_Client_ID  + " AND AD_Org_ID=" + AD_Org_ID;

		DB.executeUpdate(sql, get_TrxName());

		if (p_IsRequiredDRP)
		{

			//Delete Distribution Order with Status Draft  or Close from MRP Table
			sql = "DELETE FROM PP_MRP WHERE TypeMRP = 'DOO' AND DocStatus='DR' AND AD_Client_ID = " + AD_Client_ID +  " AND AD_Org_ID=" + AD_Org_ID+ " AND M_Warehouse_ID="+M_Warehouse_ID;				
			DB.executeUpdate(sql, get_TrxName());

			//Delete Distribution Order with Draft Status
			sql = "SELECT o.DD_Order_ID FROM DD_Order o WHERE o.DocStatus = 'DR' AND o.AD_Client_ID = ? AND AD_Org_ID=?";		
			try
			{
				PreparedStatement pstmt = null;
				pstmt = DB.prepareStatement (sql , get_TrxName());
				pstmt.setInt(1, AD_Client_ID);
				pstmt.setInt(2, AD_Org_ID);   
				ResultSet rs = pstmt.executeQuery();
				while (rs.next())
				{
					MDDOrder order = new  MDDOrder(getCtx(), rs.getInt(1) , get_TrxName());
					order.delete(true);
				}
				rs.close();
				pstmt.close();

			}
			catch (Exception e)
			{
				log.log(Level.SEVERE,"doIt - " + sql, e);
				return false;
			}    
			//Delete Manufacturing Order with Draft Status 
			sql = "SELECT o.PP_Order_ID FROM PP_Order o WHERE o.DocStatus = 'DR' AND o.AD_Client_ID = ? AND AD_Org_ID=? AND M_Warehouse_ID=?";		
			try
			{
				PreparedStatement pstmt = null;
				pstmt = DB.prepareStatement (sql , get_TrxName());
				pstmt.setInt(1, AD_Client_ID);
				pstmt.setInt(2, AD_Org_ID);  
				pstmt.setInt(3, M_Warehouse_ID);  
				ResultSet rs = pstmt.executeQuery();
				while (rs.next())
				{
					MPPOrder order = new  MPPOrder(getCtx(), rs.getInt(1) , get_TrxName());
					order.delete(true);
				}
				rs.close();
				pstmt.close();

			}
			catch (Exception e)
			{
				log.log(Level.SEVERE,"doIt - " + sql, e);
				return false;
			}    
		}
		//Delete Requisition with Draft Status
		sql = "SELECT  * FROM M_Requisition r WHERE  r.DocStatus = 'DR' AND r.AD_Client_ID = ? AND AD_Org_ID=? AND M_Warehouse_ID = ?";
		try
		{
			PreparedStatement pstmt = null;
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setInt(1, AD_Client_ID);
			pstmt.setInt(2, AD_Org_ID);  
			pstmt.setInt(3, M_Warehouse_ID);  
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				MRequisition r = new  MRequisition(getCtx(), rs,get_TrxName());

				MRequisitionLine[] rlines = r. getLines();
				for ( int i= 0 ; i < rlines.length; i++ )
				{
					MRequisitionLine line =  rlines[i];
					line.delete(true);
				}

				r.delete(true);
			}
			rs.close();
			pstmt.close();
			return true;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE,"doIt - " + sql, e);
			return false;
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
		// Deleted Old record      
		deleteMRP(AD_Client_ID,AD_Org_ID,M_Warehouse_ID);                       
		try
		{
			//String sql = "SELECT LowLevel FROM PP_MRP mrp INNER JOIN M_Product p ON (p.M_Product_ID =  mrp.M_Product_ID) WHERE mrp.M_Warehouse_ID = ? ORDER BY  p.LowLevel DESC ";                            
			MProduct product =  null;                                                                       
			Timestamp DatePromised = null;
			Timestamp Today = new Timestamp (System.currentTimeMillis());                                               
			String sql = null;
			ResultSet rs = null;
			PreparedStatement pstmt = null;

			int BeforePP_MRP_ID = 0;						
			Timestamp  DateStartSchedule = null;
			Timestamp  BeforeDateStartSchedule = null;
			Timestamp  POQDateStartSchedule = null;                        
			DB.executeUpdate("UPDATE PP_MRP SET IsAvailable ='Y' WHERE Type = 'S'  AND AD_Client_ID = " + AD_Client_ID+" AND AD_Org_ID=" + AD_Org_ID + " AND M_Warehouse_ID=" + M_Warehouse_ID ,get_TrxName());


			//String Order_Policy = MPPProductPlanning.ORDER_POLICY_LoteForLote;                       

			int lowlevel = MPPMRP.getMaxLowLevel();                                                        
			//int lowlevel = 0;
			int Level = MPPMRP.getMaxLowLevel(); //lowlevel;                            ;
			log.info("Low Level Is :"+lowlevel);
			// Calculate MRP for all levels
			for (int index = 0 ; index <= lowlevel ; index++)
			{

				log.info("Current Level Is :" + Level);
				sql = "SELECT p.M_Product_ID ,p.Name , p.LowLevel , mrp.Qty , mrp.DatePromised, mrp.Type , mrp.TypeMRP , mrp.DateOrdered , mrp.M_Warehouse_ID , mrp.PP_MRP_ID ,  mrp.DateStartSchedule , mrp.DateFinishSchedule FROM PP_MRP mrp INNER JOIN M_Product p ON (p.M_Product_ID =  mrp.M_Product_ID) WHERE mrp.Type='D' AND p.LowLevel = "+ index + " AND mrp.AD_Client_ID = " + AD_Client_ID + " AND mrp.AD_Org_ID=" + AD_Org_ID +" AND M_Warehouse_ID=" +M_Warehouse_ID +" ORDER BY  p.LowLevel DESC ,  p.M_Product_ID , mrp.DatePromised  ";
				//sql = "SELECT p.M_Product_ID ,p.Name , p.LowLevel , mrp.Qty , mrp.DatePromised, mrp.Type , mrp.TypeMRP , mrp.DateOrdered , mrp.M_Warehouse_ID , mrp.PP_MRP_ID ,  mrp.DateStartSchedule , mrp.DateFinishSchedule FROM PP_MRP mrp INNER JOIN M_Product p ON (p.M_Product_ID =  mrp.M_Product_ID) WHERE  mrp.Type='D' AND p.M_Product_ID = 1004033  ORDER BY  p.LowLevel DESC ,  p.M_Product_ID , mrp.DatePromised ";
				pstmt = DB.prepareStatement (sql, get_TrxName());
				rs = pstmt.executeQuery();                                

				int lastrow = 0;
				while (rs.next())
				{
					lastrow ++ ;                                     
				}                                 

				rs = pstmt.executeQuery();                               
				while (rs.next())
				{

					String Type = rs.getString("Type");                                    
					String TypeMRP = rs.getString("TypeMRP");                                    
					//Set Global Variable
					DatePromised = rs.getTimestamp("DatePromised");
					DateStartSchedule =   rs.getTimestamp("DateStartSchedule"); 

					// if demand is a forecast and this is minor today then is ignore this QtyGrossReq
					if (Type.equals("D") && TypeMRP.equals("FCT") && DatePromised.compareTo(Today) <= 0)
					{
						continue;  
					}

					if ( product == null || product.getM_Product_ID() != rs.getInt("M_Product_ID"))
					{
						//if exist QtyGrossReq of last Demand verify plan
						if (!QtyGrossReqs.equals(Env.ZERO))
						{   
							calculatePlan(BeforePP_MRP_ID,product, QtyGrossReqs ,BeforeDateStartSchedule);
							QtyGrossReqs = Env.ZERO;
						}

						product = new MProduct(getCtx(), rs.getInt("M_Product_ID"),get_TrxName());
						log.info("Calculte Plan to this Product:" + product.getName());

						//Define m_product_planning
						setProduct(AD_Client_ID,AD_Org_ID ,S_Resource_ID , M_Warehouse_ID,  product);

						//first DatePromised.compareTo for ORDER_POLICY_PeriodOrderQuantity
						if (m_product_planning.getOrder_Policy().equals(MPPProductPlanning.ORDER_POLICY_PeriodOrderQuantity))
						{
							DatePromisedFrom = DatePromised;
							DatePromisedTo = TimeUtil.addDays(DatePromised , Order_Period.intValue());                                       
							//set the POQDateStartSchedule && POQDateStartSchedule to first period
							if (index == 0 )
								POQDateStartSchedule = DatePromised;
							else
								POQDateStartSchedule = DateStartSchedule;
						}

						//if (Order_Policy.equals(MPPProductPlanning.ORDER_POLICY_LoteForLote))
						//{
						// DateStartSchedule =  rs.getTimestamp("DateStartSchedule");
						//}


					}

					BeforeDateStartSchedule =  DateStartSchedule; 						                                          
					BeforePP_MRP_ID = rs.getInt("PP_MRP_ID");

					// Create Notice for Demand due
					if(DatePromised.compareTo(Today) < 0)
					{                                        
						MMessage MRP=MMessage.get(Env.getCtx(), "MRP-040");	
						MNote note = new MNote (getCtx(), MRP.getAD_Message_ID() , m_product_planning.getPlanner_ID(), MPPMRP.Table_ID , rs.getInt("PP_MRP_ID") ,  product.getValue() + " " + product.getName()  , Msg.getMsg(getCtx(), MRP.getValue()),get_TrxName());    
						note.save();
						log.info( Msg.getMsg(getCtx(), MRP.getValue()));  
					}

					// Verify if is ORDER_POLICY_PeriodOrderQuantity and DatePromised < DatePromisedTo then Accumaltion QtyGrossReqs 
					if (m_product_planning.getOrder_Policy().equals(MPPProductPlanning.ORDER_POLICY_PeriodOrderQuantity) && DatePromised.compareTo(DatePromisedTo) < 0 )
					{            
						QtyGrossReqs = QtyGrossReqs.add(rs.getBigDecimal("Qty"));
						//BeforeQty = BeforeQty + Env.ZERO;
						log.info("Acumulation   QtyGrossReqs:" + QtyGrossReqs);
						continue;	                                              
					}// if not then create new range for next period 
					else if (m_product_planning.getOrder_Policy().equals(MPPProductPlanning.ORDER_POLICY_PeriodOrderQuantity))
					{           

						calculatePlan(rs.getInt("PP_MRP_ID"),product, QtyGrossReqs ,POQDateStartSchedule);

						QtyGrossReqs = rs.getBigDecimal("Qty"); 
						DatePromisedFrom = DatePromised;
						DatePromisedTo = TimeUtil.addDays(DatePromised, Order_Period.intValue());         

						if (index == 0 )
							POQDateStartSchedule = DatePromised;
						else
							POQDateStartSchedule = DateStartSchedule;                                                                                                  
						continue;
					}
					// If  Order_Policy = LoteForLote then always create new range for next period and put QtyGrossReqs          
					if (m_product_planning.getOrder_Policy().equals(MPPProductPlanning.ORDER_POLICY_LoteForLote))
					{                                                                                                                                           
						QtyGrossReqs = rs.getBigDecimal("Qty");
						calculatePlan(rs.getInt("PP_MRP_ID"),product,  QtyGrossReqs , rs.getTimestamp("DateStartSchedule"));
						continue;
					}                                                                        


				} // end while

				//if exist QtyGrossReq of last Demand after finish while verify plan
				if (!QtyGrossReqs.equals(Env.ZERO))
				{   
					calculatePlan(BeforePP_MRP_ID , product, QtyGrossReqs ,BeforeDateStartSchedule);
				}

				rs.close();
				pstmt.close();
				Level = Level - 1;
			} // end for
		} // try
		catch (SQLException ex)
		{
			log.log(Level.SEVERE,"getLines", ex);
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
		MPPProductPlanning pp = MPPProductPlanning.getMPPProductPlanning(getCtx(), AD_Client_ID ,AD_Org_ID , M_Warehouse_ID, S_Resource_ID , product.getM_Product_ID(), get_TrxName());  
		DatePromisedTo = null;
		DatePromisedFrom = null;
		if (pp != null)
		{
			m_product_planning = new MPPProductPlanning(getCtx(), 0 , "MRP");                                                       
			m_product_planning.setM_Warehouse_ID(M_Warehouse_ID);	
			PO.copyValues(pp, m_product_planning);     
			log.info("------------ Planning Data --------------");
			log.info("              Resource: " + m_product_planning.getS_Resource_ID());
			log.info("                   BOM: " + m_product_planning.getPP_Product_BOM_ID());
			log.info("  Network Distribution: "  + m_product_planning.getDD_NetworkDistribution_ID());
			log.info("              Workflow: " + m_product_planning.getAD_Workflow_ID());
			log.info("Delivery Time Promised: " + m_product_planning.getDeliveryTime_Promised());
			log.info("           Create Plan: " + m_product_planning.isCreatePlan());
			log.info("             Max Order: " + m_product_planning.getOrder_Max());
			log.info("             Min Order: " + m_product_planning.getOrder_Min());
			log.info("            Pack Order: " + m_product_planning.getOrder_Pack());
			log.info("          Safety Stock: " + m_product_planning.getSafetyStock());
			log.info("          Order Period: " + m_product_planning.getOrder_Period());
			log.info("          Order Policy: " + m_product_planning.getOrder_Policy());
			log.info("             Warehouse: " + m_product_planning.getM_Warehouse_ID());
			log.info("               Planner: " + m_product_planning.getPlanner_ID());
			log.info("Product:" + product.getName());
			log.info("PP_Product_BOM_ID:" + m_product_planning.getPP_Product_BOM_ID() + " DD_NetworkDistribution_ID:"+m_product_planning.getDD_NetworkDistribution_ID() +"  S_Resource_ID:"+  m_product_planning.getS_Resource_ID() + " TransfertTime:"+m_product_planning.getTransfertTime ()+ " Planner ID:"+ m_product_planning.getPlanner_ID() );
			//Find the BOM to this Pruct
			if (m_product_planning.getPP_Product_BOM_ID() == 0 && product.isBOM())
			{
				m_product_planning.setPP_Product_BOM_ID(MPPProductBOM.getBOMSearchKey(m_product_planning.getM_Product_ID()));  
			}                                                                                                                                                            
		}
		else
		{           
			MMessage MRP=MMessage.get(Env.getCtx(), "MRP-120");
			MNote note = new MNote (getCtx(), MRP.getAD_Message_ID() , 0 , MPPMRP.Table_ID , 0 ,  product.getValue() + " " + product.getName()  , Msg.getMsg(getCtx(), MRP.getValue()),get_TrxName());
			note.save();
			log.severe(Msg.getMsg(getCtx(), MRP.getValue()));
			m_product_planning.setIsCreatePlan(false);

		}


		//Find Vendor
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
				}
			}
			if(C_BPartner_ID == 0)
				m_product_planning.setIsCreatePlan(false);
		}


		if (m_product_planning.getAD_Workflow_ID() == 0 )
			log.info("Error: Do not exist workflow");

		if (m_product_planning.getOrder_Policy() == null)
			m_product_planning.setOrder_Policy(MPPProductPlanning.ORDER_POLICY_LoteForLote);




		//QtyOnHand = getOnHand(M_Product_ID);
		QtyProjectOnHand = MPPMRP.getOnHand(AD_Client_ID , m_product_planning.getM_Warehouse_ID() , m_product_planning.getM_Product_ID());
		if(QtyProjectOnHand == null)
			QtyProjectOnHand = Env.ZERO;    

		if(m_product_planning.getSafetyStock().compareTo(QtyProjectOnHand) > 0)
		{
			QtyProjectOnHand =  QtyProjectOnHand.subtract(m_product_planning.getSafetyStock());
		}

		log.info("QtyOnHand :" + QtyProjectOnHand);


		//result.append("---------------------------------------------------------------\n");
		//result.append("Product " + rs.getString("Name") + " On Hand: " + QtyOnHand + "\n");
		//result.append("--------------------   -------------------------------------------\n");                                        
		//result.append("        Due Date        Gross Reqs     Sched Rcpt     Project On Hand     Order Plan \n");
		QtyScheduledReceipts = Env.ZERO;                                        //get Supply this Product 
		String sql= "SELECT mrp.PP_MRP_ID , mrp.Qty FROM PP_MRP mrp WHERE mrp.DocStatus = 'CO' AND mrp.IsAvailable = 'Y' AND mrp.Type = 'S' AND mrp.M_Product_ID = " + m_product_planning.getM_Product_ID()  + " AND mrp.AD_Client_ID = " + AD_Client_ID + " AND mrp.AD_Org_ID="+ AD_Org_ID + " AND M_Warehouse_ID="+m_product_planning.getM_Warehouse_ID();                                               
		//System.out.println(sqlsupply);
		try
		{
			PreparedStatement pstmt = DB.prepareStatement (sql,get_TrxName());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				QtyScheduledReceipts = QtyScheduledReceipts.add(rs.getBigDecimal("Qty"));
				DB.executeUpdate("UPDATE PP_MRP SET IsAvailable = 'N' WHERE PP_MRP_ID = " + rs.getInt("PP_MRP_ID") +  " AND AD_Client_ID = " + AD_Client_ID + " AND AD_Org_ID="+ AD_Org_ID,get_TrxName());
			}                    
			pstmt.close();
			rs.close();                                                
			//System.out.println("Inicial --------------------------------------------------------> QtyScheduledReceipts " + QtyScheduledReceipts);
		}
		catch (SQLException ex)
		{
			log.log(Level.SEVERE,"getLines" + sql, ex);
		}


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
	private void calculatePlan(int PP_MPR_ID , MProduct product , BigDecimal Qty, Timestamp DemandDateStartSchedule)        
	{        	

		if (m_product_planning.isCreatePlan() == false)
			return;

		//Set Yield o QtyGrossReqs
		// Note : the variables  DemandDateStartSchedule , DemandDateFinishSchedule are same DatePromised to Demands Sales Order Type
		Timestamp Today = new Timestamp (System.currentTimeMillis());

		log.info("Create Plan ....");

		BigDecimal  DecimalYield = new BigDecimal(m_product_planning.getYield()/100);
		if (!DecimalYield.equals(Env.ZERO))
			QtyGrossReqs = QtyGrossReqs.divide(DecimalYield, 4 ,BigDecimal.ROUND_HALF_UP);

		QtyNetReqs = ((QtyScheduledReceipts).add(QtyProjectOnHand)).subtract(QtyGrossReqs);

		log.info("             Planning Data :" + product.getName() + "Create Plan:" + m_product_planning.isCreatePlan() + " OrderPlan:" + QtyPlanned);
		log.info(" Demand Date Start Schedule:" + DemandDateStartSchedule);
		log.info("     Delivery Time Promised:" + m_product_planning.getDeliveryTime_Promised());
		log.info("           DatePromisedFrom:" + DatePromisedFrom + " DatePromisedTo:" +   DatePromisedTo);    
		log.info("     Qty Scheduled Receipts:" + QtyScheduledReceipts);
		log.info("           QtyProjectOnHand:" + QtyProjectOnHand);
		log.info("               QtyGrossReqs:" + QtyGrossReqs);
		log.info("                     Supply:" + (QtyScheduledReceipts).add(QtyProjectOnHand));
		log.info("                 QtyNetReqs:" + QtyNetReqs);    

		if (QtyNetReqs.compareTo(Env.ZERO) > 0)
		{    
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

		if(QtyPlanned.compareTo(Env.ZERO) > 0 && m_product_planning.getOrder_Min().compareTo(Env.ZERO) > 0)
		{    
			QtyPlanned = QtyPlanned.max(m_product_planning.getOrder_Min());
			MMessage MRP=MMessage.get(Env.getCtx(), "MRP-080");
			MNote note = new MNote (getCtx(),  MRP.getAD_Message_ID(), m_product_planning.getPlanner_ID() , MPPMRP.Table_ID , PP_MPR_ID ,  product.getValue() + " " + product.getName()  , Msg.getMsg(getCtx(), MRP.getValue()),get_TrxName());    
			note.save();
		}

		// Check Order Max                                                
		if(QtyPlanned.compareTo(m_product_planning.getOrder_Max()) > 0 && m_product_planning.getOrder_Max().compareTo(Env.ZERO) > 0)
		{    
			System.out.println("Error: Orden Planeada exede el maximo a ordenar");
			MMessage MRP=MMessage.get(Env.getCtx(), "MRP-090");
			MNote note = new MNote (getCtx(),  MRP.getAD_Message_ID (), m_product_planning.getPlanner_ID() , MPPMRP.Table_ID , PP_MPR_ID ,  product.getValue() + " " + product.getName()  , Msg.getMsg(getCtx(), MRP.getValue()),get_TrxName());    
			note.save();
		}                        
		// Check Order Pack
		if (m_product_planning.getOrder_Pack().compareTo(Env.ZERO) > 0 && QtyPlanned.compareTo(Env.ZERO) > 0)
			QtyPlanned = m_product_planning.getOrder_Pack().multiply(QtyPlanned.divide(m_product_planning.getOrder_Pack(), 0 , BigDecimal.ROUND_UP));

		QtyProjectOnHand = QtyPlanned.add(QtyNetReqs);

		log.info("QtyPlanned:" +  QtyPlanned);
		log.info("QtyProjectOnHand:" +  QtyProjectOnHand);                        

		if (m_product_planning.isCreatePlan() && QtyPlanned.compareTo(Env.ZERO) > 0)    
		{

			int loops = 1;

			if (m_product_planning.getOrder_Policy().equals(MPPProductPlanning.ORDER_POLICY_OrderFixedQuantity))
			{    
				if (m_product_planning.getOrder_Qty().compareTo(Env.ZERO) != 0)
					loops = (QtyPlanned.divide(m_product_planning.getOrder_Qty() , 0 , BigDecimal.ROUND_UP)).intValue();
				QtyPlanned = m_product_planning.getOrder_Qty();
			}

			for (int ofq = 1 ; ofq <= loops ; ofq ++ )
			{      
				log.info("Is Purchased:"+ product.isPurchased()+ " Is BOM" +  product.isBOM());

				//Distribution Order
				if(m_product_planning.getDD_NetworkDistribution_ID() > 0 && p_IsRequiredDRP)
				{

					MDDNetworkDistribution network = new MDDNetworkDistribution(getCtx(),m_product_planning.getDD_NetworkDistribution_ID(), get_TrxName());

					MDDNetworkDistributionLine[] network_lines = network.getLines(p_M_Warehouse_ID);
					int M_Shipper_ID = 0;
					MDDOrder order = null;

					for (MDDNetworkDistributionLine network_line : network_lines)
					{	
						MWarehouse source = MWarehouse.get(getCtx(), network_line.getM_WarehouseSource_ID());
						MWarehouse target = MWarehouse.get(getCtx(), network_line.getM_Warehouse_ID());
						MLocator locator =  MLocator.getDefault(source);
						MLocator locator_to = MLocator.getDefault(target); 
						BigDecimal transfertTime = network_line.getTransfertTime(); 
						if(transfertTime.compareTo(Env.ZERO) <= 0)
						{
							transfertTime = m_product_planning.getTransfertTime();
						}

						if (locator == null || locator_to == null)
						{
							log.info("Do not exist default Locator for Warehouse" );
							MMessage MRP=MMessage.get(Env.getCtx(), "DRP-001");
							MNote note = new MNote(getCtx(), MRP.getAD_Message_ID (), m_product_planning.getPlanner_ID(),MPPMRP.Table_ID, PP_MPR_ID,product.getValue() + " " + product.getName(),Msg.getMsg(getCtx(), MRP.getValue()),get_TrxName());
							note.save();
							continue;
						}
						MWarehouse[] wsts = MWarehouse.getInTransitForOrg(getCtx(), target.getAD_Org_ID());

						if (wsts.length <= 0)
						{
							log.info("Do not exist Warehouse to this Organization");
							MMessage MRP=MMessage.get(Env.getCtx(), "DRP-010");
							MNote note = new MNote(getCtx(), MRP.getAD_Message_ID (), m_product_planning.getPlanner_ID(),MPPMRP.Table_ID, PP_MPR_ID,product.getValue() + " " + product.getName(),Msg.getMsg(getCtx(), MRP.getValue()),get_TrxName());
							note.save();
							continue;
						}

						if(M_Shipper_ID != network_line.getM_Shipper_ID())
						{	

//							//Org Must be linked to BPartner
							MOrg org = MOrg.get(getCtx(), locator_to.getAD_Org_ID());
							int C_BPartner_ID = org.getLinkedC_BPartner_ID(get_TrxName()); 
							if (C_BPartner_ID == 0)
							{
								log.info("Org targer do not linked to BPartner");
								MMessage MRP=MMessage.get(Env.getCtx(), "DRP-020");
								MNote note = new MNote(getCtx(), MRP.getAD_Message_ID (), m_product_planning.getPlanner_ID(),MPPMRP.Table_ID, PP_MPR_ID,product.getValue() + " " + product.getName(),Msg.getMsg(getCtx(), MRP.getValue()),get_TrxName());
								note.save();
								continue;
							}


							order = new MDDOrder(getCtx() , 0 , get_TrxName());
							order.setAD_Org_ID(target.getAD_Org_ID());
							order.setDocumentNo(MSequence.getDocumentNo(DocTypeDO,get_TrxName(),false));
							order.setC_BPartner_ID(C_BPartner_ID);
							order.setC_DocType_ID(DocTypeDO);  
							order.setM_Warehouse_ID(wsts[0].getM_Warehouse_ID());
							order.setDocAction(MDDOrder.DOCACTION_Complete);



							order.setDateOrdered(Today);                       
							//order.setDatePromised(TimeUtil.addDays(DemandDateStartSchedule , (m_product_planning.getDeliveryTime_Promised().add(transfertTime)).negate().intValue()));
							order.setDatePromised(DemandDateStartSchedule);
							order.setM_Shipper_ID(network_line.getM_Shipper_ID());	    	                
							order.setIsInDispute(false);
							order.setIsInTransit(false);
							order.save();	
							M_Shipper_ID = network_line.getM_Shipper_ID();
						}   

						BigDecimal QtyOrdered = QtyPlanned.multiply(network_line.getPercent()).divide(Env.ONEHUNDRED);

						MDDOrderLine oline = new MDDOrderLine(getCtx(), 0 , get_TrxName());
						oline.setDD_Order_ID(order.getDD_Order_ID());
						oline.setM_Locator_ID(locator.getM_Locator_ID());
						oline.setM_LocatorTo_ID(locator_to.getM_Locator_ID());
						oline.setM_Product_ID(m_product_planning.getM_Product_ID()); 
						oline.setDateOrdered(Today);                       
						oline.setDatePromised(DemandDateStartSchedule);
						oline.setQtyEntered(QtyOrdered);
						oline.setQtyOrdered(QtyOrdered);
						oline.setIsInvoiced(false);
						oline.save();

						// Set Correct Dates for Plan
						String sql = "SELECT * FROM PP_MRP mrp WHERE DD_OrderLine_ID = " + oline.getDD_OrderLine_ID();                                                                                                       
						try
						{
							PreparedStatement rpstmt = DB.prepareStatement (sql, get_TrxName());
							ResultSet rs = rpstmt.executeQuery();
							while (rs.next())
							{
								log.info("Set Correct Dates for Plan");
								MPPMRP mrp = new MPPMRP(getCtx(),rs,get_TrxName());                                                        	
								mrp.setDateOrdered(Today);                                                                                                                       
								mrp.setDatePromised(TimeUtil.addDays(DemandDateStartSchedule , (m_product_planning.getDeliveryTime_Promised().add(transfertTime)).negate().intValue()));;                                                            
								mrp.setDateFinishSchedule(DemandDateStartSchedule);
								mrp.save();
							}
							rpstmt.close();                                                                                                       
							rs.close();                                                                                                        
						}
						catch (SQLException ex)
						{
							log.log(Level.SEVERE,"getLines" + sql, ex);
						}
					}
					return;

				}
				else if (product.isPurchased()) // then create M_Requisition
				{           
					log.info("Create Requisistion");
					int M_PriceList_ID = Env.getContextAsInt(getCtx(), "#M_PriceList_ID");                                                        
					if (M_PriceList_ID==0) 
					{
						log.info("No default pricelist has been retrieved");
						MMessage MRP=MMessage.get(Env.getCtx(), "MRP-140");
						MNote note = new MNote(getCtx(), MRP.getAD_Message_ID (), m_product_planning.getPlanner_ID(),MPPMRP.Table_ID, PP_MPR_ID,product.getValue() + " " + product.getName(),Msg.getMsg(getCtx(), MRP.getValue()),get_TrxName());
						note.save();
						continue;
					}
					// 4Layers - end

					MRequisition req = new  MRequisition(getCtx(),0, get_TrxName()); 

					req.setAD_User_ID(m_product_planning.getPlanner_ID());                                                        
					req.setDateRequired(TimeUtil.addDays(DemandDateStartSchedule , (m_product_planning.getDeliveryTime_Promised().add(m_product_planning.getTransfertTime())).negate().intValue()));
					//req.setDateRequired(BeforeDateStartSchedule); 

					req.setDescription("Generate from MRP");
					req.setM_Warehouse_ID(m_product_planning.getM_Warehouse_ID());
					req.setDocumentNo(MSequence.getDocumentNo(DocTypeReq , get_TrxName() ,false));
					req.setC_DocType_ID(DocTypeReq);
					req.setM_PriceList_ID(M_PriceList_ID);
					req.setTotalLines(Env.ZERO);
					req.save();

					MRequisitionLine reqline = new  MRequisitionLine(getCtx(), 0 ,get_TrxName());
					reqline.setLine(10);
					reqline.setM_Requisition_ID(req.getM_Requisition_ID());
					reqline.setM_Product_ID(m_product_planning.getM_Product_ID());
					reqline.setPrice(M_PriceList_ID);
					reqline.setPriceActual(new BigDecimal(0));
					reqline.setQty(QtyPlanned);
					reqline.save();

					// Set Correct Dates for Plan
					String sql = "SELECT * FROM PP_MRP mrp WHERE M_Requisition_ID = " + req.getM_Requisition_ID();                                                                                                       
					try
					{
						PreparedStatement rpstmt = DB.prepareStatement (sql, get_TrxName());
						ResultSet rs = rpstmt.executeQuery();
						while (rs.next())
						{
							log.info("Set Correct Dates for Plan");
							MPPMRP mrp = new MPPMRP(getCtx(),rs,get_TrxName());                                                        	
							mrp.setDateOrdered(Today);                                                            
							mrp.setDatePromised(DemandDateStartSchedule);                                                            
							mrp.setDateStartSchedule(TimeUtil.addDays(DemandDateStartSchedule, (m_product_planning.getDeliveryTime_Promised().add(m_product_planning.getTransfertTime())).negate().intValue()));                                                            
							mrp.setDateFinishSchedule(DemandDateStartSchedule);
							mrp.save();
						}
						rpstmt.close();                                                                                                       
						rs.close();                                                                                                        
					}
					catch (SQLException ex)
					{
						log.log(Level.SEVERE,"getLines" + sql, ex);
					}

					return;
				}
				else if (product.isBOM())// else create PP_Order
				{       

					log.info("PP_Product_BOM_ID" + m_product_planning.getPP_Product_BOM_ID() + "AD_Workflow_ID" + m_product_planning.getAD_Workflow_ID());
					if (m_product_planning.getPP_Product_BOM_ID() != 0 && m_product_planning.getAD_Workflow_ID() != 0)
					{    
						log.info("Manufacturing Order Create");                       
						//System.out.println("-----------> DateStartSchedule" + DateStartSshedule +" DatePromisedFrom:" +  DatePromisedFrom + " DatePromisedTo:" +   DatePromisedTo);    
						MPPOrder order = new MPPOrder(getCtx(),0, get_TrxName());
						order.setLine(10);        
						order.setDocumentNo(MSequence.getDocumentNo(DocTypeMO,get_TrxName(),false));
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

						if (m_product_planning.getDeliveryTime_Promised().compareTo(Env.ZERO) == 0)
							order.setDateStartSchedule(TimeUtil.addDays(DemandDateStartSchedule, (MPPMRP.getDays(order.getS_Resource_ID(),order.getAD_Workflow_ID(), QtyPlanned).add(m_product_planning.getTransfertTime())).negate().intValue()));
						else	                                                        	
							order.setDateStartSchedule(TimeUtil.addDays(DemandDateStartSchedule, (m_product_planning.getDeliveryTime_Promised().add(m_product_planning.getTransfertTime())).negate().intValue()));
						order.setDateFinishSchedule(DemandDateStartSchedule);
						order.setQtyEntered(QtyPlanned);
						order.setQtyOrdered(QtyPlanned);
						order.setQtyBatchs(Env.ONE);
						order.setQtyBatchSize(QtyPlanned);
						order.setC_UOM_ID(product.getC_UOM_ID());
						order.setPosted(false);
						order.setProcessed(false);  
						order.setYield(Env.ZERO);
						order.setScheduleType("D");
						order.setPriorityRule(MPPOrder.PRIORITYRULE_Medium);
						order.setDocStatus(MPPOrder.DOCSTATUS_Drafted);
						order.setDocAction(MPPOrder.DOCSTATUS_Completed);
						order.save();
						return;
					}
				}
			} // end for oqf
		}       
		else
		{
			log.info("No Create Plan");
		}

		QtyGrossReqs = Qty;                                	        	
	}        	      
}

