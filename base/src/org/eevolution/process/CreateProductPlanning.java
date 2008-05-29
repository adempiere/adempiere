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
import java.util.*;


import org.compiere.model.*;
import org.compiere.util.*;
import org.compiere.process.*;
//import compiere.model.*;
import org.eevolution.model.MPPProductPlanning;

/**
 *	CreateProductPlanning
 *	
 *  @author Victor Perez, e-Evolution, S.C.
 *  @version $Id: CreateProductPlanning.java,v 1.1 2004/06/22 05:24:03 vpj-cd Exp $
 */
public class CreateProductPlanning extends SvrProcess
{
	/**					*/
		private int 			p_AD_Org_ID = 0 ;
        private int             p_M_Product_Category_ID = 0;
        private int             p_M_Warehouse_ID = 0;
        private int             p_S_Resource_ID = 0 ;
        private int             p_Planner = 0 ; 
        private BigDecimal      p_DeliveryTime_Promised = Env.ZERO;
        private int             p_AD_Workflow_ID = 0;
        private BigDecimal      p_TimeFence = Env.ZERO;
        private boolean         p_CreatePlan = false;
        private boolean         p_MPS = false;
        private String          p_OrderPolicy = "";
        private BigDecimal      p_OrderPeriod = Env.ZERO;
        private BigDecimal      p_TransferTime = Env.ZERO;
        private BigDecimal      p_Order_Min = Env.ZERO;
        private BigDecimal      p_Order_Max = Env.ZERO;
        private BigDecimal      p_Order_Pack = Env.ZERO;
        private BigDecimal      p_Order_Qty = Env.ZERO;
        private BigDecimal      p_WorkingTime = Env.ZERO;
        private int             p_Yield = 0;

        
        
	
        
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
						else if (name.equals("AD_Org_ID"))
                        {    
				p_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();
                                
                        }
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
	}	//	prepare


    /**********************
     * Create Data Planning record     
     */
    protected String doIt() throws Exception                
	{
            
                int PP_Product_Planning_ID =0; 
            	String sql = "SELECT p.M_Product_ID , p.M_Product_Category_ID FROM M_Product p WHERE p.M_Product_Category_ID = ?  ";
                         
                PreparedStatement pstmt = null;
		try
		{
						pstmt = DB.prepareStatement (sql,get_TrxName());
                        pstmt.setInt(1, p_M_Product_Category_ID);
                        ResultSet rs = pstmt.executeQuery ();
                        while (rs.next())
                        {                                                   
                        String sql1 = "SELECT p.M_Product_ID , p.PP_Product_Planning_ID FROM PP_Product_Planning p WHERE p.M_Product_ID = ? and p.M_Warehouse_ID = ? and p.S_Resource_ID = ? ";
                         
                        PreparedStatement pstmt1 = null;
		
                        pstmt1 = DB.prepareStatement (sql1, get_TrxName());
                        pstmt1.setInt(1, rs.getInt(1));
                        pstmt1.setInt(2, p_M_Warehouse_ID);
                        pstmt1.setInt(3, p_S_Resource_ID);
                        ResultSet rs1 = pstmt1.executeQuery ();
                        if (rs1.next())
                        {
                        	PP_Product_Planning_ID = rs1.getInt(2);
                        }
                        rs1.close();
                        pstmt1.close();
                        if (PP_Product_Planning_ID!=0)
                        { 
                                    MPPProductPlanning pp = new MPPProductPlanning(getCtx(),PP_Product_Planning_ID,null);                                                                                                       
                                    pp.setAD_Workflow_ID(p_AD_Workflow_ID);
                                    pp.setIsCreatePlan(p_CreatePlan);                                 
                                    pp.setIsMPS(p_MPS);                                  
                                    pp.setDeliveryTime_Promised(p_DeliveryTime_Promised);
                                    pp.setOrder_Period(p_OrderPeriod);
                                    pp.setPlanner_ID(p_Planner);
                                    pp.setOrder_Policy(p_OrderPolicy);
                                    pp.setOrder_Qty(p_Order_Qty);
                                    pp.setOrder_Min(p_Order_Min);
                                    pp.setOrder_Max(p_Order_Max);
                                    pp.setOrder_Pack(p_Order_Pack);
                                    pp.setTimeFence(p_TimeFence);
                                    pp.setTransfertTime(p_TransferTime);
                                    pp.setWorkingTime(p_WorkingTime);
                                    pp.setYield(p_Yield);                                                                                        
                                    pp.save(get_TrxName());   
                        }
                        else
                        {
                                    MPPProductPlanning pp1 = new MPPProductPlanning(getCtx(),0,null);                                                                                                       
                                    pp1.setM_Product_ID(rs.getInt(1));
                                    pp1.setAD_Workflow_ID(p_AD_Workflow_ID);
                                    pp1.setIsActive(true);
                                    pp1.setIsCreatePlan(p_CreatePlan);                                                                         
                                    pp1.setIsMPS(p_MPS);                                    
                                    pp1.setIsPhantom(false);
                                    pp1.setIsRequiredMRP(true);                                    
                                    pp1.setM_Warehouse_ID(p_M_Warehouse_ID);
                                    pp1.setS_Resource_ID(p_S_Resource_ID);
                                    pp1.setDeliveryTime_Promised(p_DeliveryTime_Promised);
                                    pp1.setOrder_Period(p_OrderPeriod);
                                    pp1.setPlanner_ID(p_Planner);
                                    pp1.setOrder_Policy(p_OrderPolicy);
                                    pp1.setOrder_Qty(p_Order_Qty);
                                    pp1.setOrder_Min(p_Order_Min);
                                    pp1.setOrder_Max(p_Order_Max);
                                    pp1.setOrder_Pack(p_Order_Pack);
                                    pp1.setTimeFence(p_TimeFence);
                                    pp1.setWorkingTime(p_WorkingTime);
                                    pp1.setYield(p_Yield);                                                                                        
                                    pp1.save(get_TrxName()); 
			}
                        }
                        rs.close();
                        pstmt.close();

		}
		catch (Exception e)
		{
			log.log(Level.SEVERE,"doIt - " + sql, e);
		}

            return "ok";
     }
                                                                
}	