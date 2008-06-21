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

import java.util.logging.*;
import java.math.*;
import java.sql.*;


import org.compiere.model.*;
import org.compiere.util.*;
import org.compiere.process.*;
import org.eevolution.model.MPPProductPlanning;

/**
 *	CreateProductPlanning
 *	
 *  @author Victor Perez, e-Evolution, S.C.
 *  @version $Id: CreateProductPlanning.java,v 1.1 2004/06/22 05:24:03 vpj-cd Exp $
 */
public class CreateProductPlanning extends SvrProcess
{
		/**	Process Parameters				*/;
        private int             p_M_Product_Category_ID = 0;
        private int             p_M_Warehouse_ID = 0;
        private int             p_S_Resource_ID = 0 ;
        private int             p_Planner = 0 ; 
        private BigDecimal      p_DeliveryTime_Promised = Env.ZERO;
        private int             p_DD_NetworkDistribution_ID = 0;
        private int             p_AD_Workflow_ID = 0;
        private BigDecimal      p_TimeFence = Env.ZERO;
        private boolean         p_CreatePlan = false;
        private boolean         p_MPS = false;
        private String          p_OrderPolicy = "";
        private BigDecimal      p_OrderPeriod = Env.ZERO;
        private BigDecimal      p_TransferTime = Env.ZERO;
        private BigDecimal      p_SafetyStock = Env.ZERO;
        private BigDecimal      p_Order_Min = Env.ZERO;
        private BigDecimal      p_Order_Max = Env.ZERO;
        private BigDecimal      p_Order_Pack = Env.ZERO;
        private BigDecimal      p_Order_Qty = Env.ZERO;
        private BigDecimal      p_WorkingTime = Env.ZERO;
        private int             p_Yield = 0;
        private int 			m_AD_Org_ID = 0;	

        
        
	
        
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
                
                
               
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();

						if (para[i].getParameter() == null)
						;
                        else if (name.equals("M_Product_Category_ID"))
                        {    
				p_M_Product_Category_ID = ((BigDecimal)para[i].getParameter()).intValue();
                                
                        }
                        else if (name.equals("M_Warehouse_ID"))
                        {    
				p_M_Warehouse_ID = ((BigDecimal)para[i].getParameter()).intValue();
                                
                        }
                        else if (name.equals("S_Resource_ID"))
                        {    
				p_S_Resource_ID = ((BigDecimal)para[i].getParameter()).intValue();
                                
                        }
                        else if (name.equals("IsCreatePlan"))
                        {    
				p_CreatePlan = "Y".equals((String)para[i].getParameter());
                             
                        }
                        else if (name.equals("IsMPS"))
                        {    
                                p_MPS = "Y".equals((String)para[i].getParameter());
                              
                        }
                        else if (name.equals("DD_NetworkDistribution_ID"))
                        {    
                p_DD_NetworkDistribution_ID =  ((BigDecimal)para[i].getParameter()).intValue();                                
                        } 		
                        else if (name.equals("AD_Workflow_ID"))
                        {    
				p_AD_Workflow_ID =  ((BigDecimal)para[i].getParameter()).intValue();
                                
                        }		
                        else if (name.equals("TimeFence"))
                        {    
				p_TimeFence =  ((BigDecimal)para[i].getParameter());  
                        } 
                        
                         else if (name.equals("TransfertTime"))
                        {    
				p_TransferTime =  ((BigDecimal)para[i].getParameter());  
                        } 
                         else if (name.equals("SafetyStock"))
                         {    
 				p_SafetyStock =  ((BigDecimal)para[i].getParameter());  
                         }
                         else if (name.equals("Order_Min"))
                        {    
				p_Order_Min =  ((BigDecimal)para[i].getParameter());  
                        }
                         else if (name.equals("Order_Max"))
                        {    
				p_Order_Max =  ((BigDecimal)para[i].getParameter());  
                        } 
                         else if (name.equals("Order_Pack"))
                        {    
				p_Order_Pack =  ((BigDecimal)para[i].getParameter());  
                        } 
                         else if (name.equals("Order_Qty"))
                        {    
				p_Order_Qty =  ((BigDecimal)para[i].getParameter());  
                        } 
                         else if (name.equals("WorkingTime"))
                        {    
				p_WorkingTime =  ((BigDecimal)para[i].getParameter());  
                        } 
                         else if (name.equals("Yield"))
                        {    
				p_Yield =  ((BigDecimal)para[i].getParameter()).intValue();  
                        } 
                        else if (name.equals("DeliveryTime_Promised"))
                        {    
				p_DeliveryTime_Promised =  ((BigDecimal)para[i].getParameter());  
                        }           
                        else if (name.equals("Order_Period"))
                        {    
				p_OrderPeriod =  ((BigDecimal)para[i].getParameter());  
                        } 
                         else if (name.equals("Order_Policy"))
                        {    
				p_OrderPolicy =  ((String)para[i].getParameter()); 
                                System.out.println("MPS   " +p_OrderPolicy);
                        } 
                        else if (name.equals("Planner_ID"))
                        {    
				p_Planner =   ((BigDecimal)para[i].getParameter()).intValue(); 
                        }                        
                        else
				log.log(Level.SEVERE,"prepare - Unknown Parameter: " + name);
		}
		
		if(p_M_Warehouse_ID > 0 )
		{
			MWarehouse w = MWarehouse.get(getCtx(), p_M_Warehouse_ID);
			m_AD_Org_ID = w.getAD_Org_ID();
		}
			
	}	//	prepare


    /**********************
     * Create Data Planning record     
     */
    protected String doIt() throws Exception                
	{
        String sql = "SELECT p.M_Product_ID , p.M_Product_Category_ID FROM M_Product p WHERE p.M_Product_Category_ID = ?  ";
                         
        PreparedStatement pstmt = null;
		try
		{
						pstmt = DB.prepareStatement (sql,get_TrxName());
                        pstmt.setInt(1, p_M_Product_Category_ID);
                        ResultSet rs = pstmt.executeQuery ();
                        while (rs.next())
                        {                                                   
                   
                        	int M_Product_ID = rs.getInt(1);
                        	MPPProductPlanning pp = MPPProductPlanning.get(getCtx(), m_AD_Org_ID , p_M_Warehouse_ID, p_S_Resource_ID,M_Product_ID, get_TrxName());
	                        if (pp==null && ( p_S_Resource_ID == 0 || p_M_Warehouse_ID == 0 ))
	                        {
	                                    pp = new MPPProductPlanning(getCtx(),0,get_TrxName());                                                                                                       
	                                    pp.setM_Product_ID(rs.getInt(1));
	                                    pp.setDD_NetworkDistribution_ID (p_DD_NetworkDistribution_ID);		
	                                    pp.setAD_Workflow_ID(p_AD_Workflow_ID);
	                                    pp.setIsActive(true);
	                                    pp.setIsCreatePlan(p_CreatePlan);                                                                         
	                                    pp.setIsMPS(p_MPS);                                    
	                                    pp.setIsPhantom(false);
	                                    pp.setIsRequiredMRP(true);                                    
	                                    pp.setM_Warehouse_ID(p_M_Warehouse_ID);
	                                    pp.setS_Resource_ID(p_S_Resource_ID);
	                                    pp.setDeliveryTime_Promised(p_DeliveryTime_Promised);
	                                    pp.setOrder_Period(p_OrderPeriod);
	                                    pp.setPlanner_ID(p_Planner);
	                                    pp.setOrder_Policy(p_OrderPolicy);
	                                    pp.setSafetyStock(p_SafetyStock);
	                                    pp.setOrder_Qty(p_Order_Qty);
	                                    pp.setOrder_Min(p_Order_Min);
	                                    pp.setOrder_Max(p_Order_Max);
	                                    pp.setOrder_Pack(p_Order_Pack);
	                                    pp.setTimeFence(p_TimeFence);
	                                    pp.setWorkingTime(p_WorkingTime);
	                                    pp.setYield(p_Yield);                                                                                        
	                                    pp.save(get_TrxName()); 
	                        }
	                        else
	                        {	
	                                   
	                                    pp.setDD_NetworkDistribution_ID (p_DD_NetworkDistribution_ID);	
	                                    pp.setAD_Workflow_ID(p_AD_Workflow_ID);                                 	
	                                    pp.setIsCreatePlan(p_CreatePlan);                                 
	                                    pp.setIsMPS(p_MPS);                                  
	                                    pp.setDeliveryTime_Promised(p_DeliveryTime_Promised);
	                                    pp.setOrder_Period(p_OrderPeriod);
	                                    pp.setPlanner_ID(p_Planner);
	                                    pp.setOrder_Policy(p_OrderPolicy);
	                                    pp.setSafetyStock(p_SafetyStock);
	                                    pp.setOrder_Qty(p_Order_Qty);
	                                    pp.setOrder_Min(p_Order_Min);
	                                    pp.setOrder_Max(p_Order_Max);
	                                    pp.setOrder_Pack(p_Order_Pack);
	                                    pp.setTimeFence(p_TimeFence);
	                                    pp.setTransfertTime(p_TransferTime);
	                                    pp.setWorkingTime(p_WorkingTime);
	                                    pp.setYield(p_Yield);                                                                                        
	                                    pp.save();       
	                        }            
                        }
         DB.close(rs);
         DB.close(pstmt);

		}
		catch (Exception e)
		{
			log.log(Level.SEVERE,"doIt - " + sql, e);
		}

            return "ok";
     }
                                                                
}	