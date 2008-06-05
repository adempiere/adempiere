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
import org.compiere.wf.*;
import org.compiere.util.*;
import org.compiere.process.*;
import org.eevolution.model.MPPProductPlanning;

/**
 *	RollUp of Cost Manufacturing Workflow 
 *	This process calculate the Labor, Overhead, Burden Cost
 *  @author Victor Perez, e-Evolution, S.C.
 *  @version $Id: RollupWorkflow.java,v 1.1 2004/06/22 05:24:03 vpj-cd Exp $
 */
public class RollupWorkflow extends SvrProcess
{

       
       private int		 		 p_AD_Org_ID = 0;
       private int               p_C_AcctSchema_ID = 0;
       private int               p_M_Product_ID = 0;
       private int               p_M_CostType_ID = 0;       
        
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
            else if (name.equals("M_Product_ID"))
            {    
				p_M_Product_ID = ((BigDecimal)para[i].getParameter()).intValue();
            }
            else if (name.equals("M_CostType_ID"))
            {    
            	p_M_CostType_ID = ((BigDecimal)para[i].getParameter()).intValue();
            }
            else if (name.equals("C_AcctSchema_ID"))
            {    
				p_C_AcctSchema_ID = ((BigDecimal)para[i].getParameter()).intValue();
            } 
            else
				log.log(Level.SEVERE,"prepare - Unknown Parameter: " + name);
		}
	}	//	prepare

	/**
	 *  Perform process.
	 *  @return Message (text with variables)
	 *  @throws Exception if not successful
	 */   
     protected String doIt() throws Exception                
     {
            
         
                StringBuffer sql = new StringBuffer ("SELECT p.M_Product_ID FROM M_Product p WHERE p.ProductType = '" + MProduct.PRODUCTTYPE_Item + "' AND");
                
                if (p_M_Product_ID != 0)
                {    
                sql.append(" p.M_Product_ID = " + p_M_Product_ID + " AND ");
                }               
                sql.append(" p.AD_Client_ID = " + getAD_Client_ID());
                sql.append(" ORDER BY p.LowLevel");
                
                
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql.toString(), get_TrxName());                       						
            ResultSet rs = pstmt.executeQuery ();
            while (rs.next())
            { 
               int M_Product_ID = rs.getInt("M_Product_ID");                           
               MCost[]  costs = MCost.getCosts(getCtx() , getAD_Client_ID(),   p_AD_Org_ID  ,  M_Product_ID ,  p_M_CostType_ID , p_C_AcctSchema_ID , get_TrxName());            
               for (MCost cost : costs )
               {
                    MCostElement element = new MCostElement(getCtx(), cost.getM_CostElement_ID(), get_TrxName());
                    // check if element cost is of type Labor
                    if (element.getCostElementType().equals(element.COSTELEMENTTYPE_Resource))
                    {                                    
	                    BigDecimal Labor = getCost(element.COSTELEMENTTYPE_Resource , p_AD_Org_ID , M_Product_ID , p_M_CostType_ID , p_C_AcctSchema_ID);
	                    log.info("Labor : " + Labor);                                
	                    cost.setCurrentCostPrice(Labor);
	                    cost.save();
	                    continue;
                    }
                    if (element.getCostElementType().equals(element.COSTELEMENTTYPE_BurdenMOverhead))
                    {                                    
	                    BigDecimal Burden = getCost(element.COSTELEMENTTYPE_BurdenMOverhead, p_AD_Org_ID , M_Product_ID , p_M_CostType_ID , p_C_AcctSchema_ID);
	                    log.info("Burden : " + Burden);                                   
	                    cost.setCurrentCostPrice(Burden);
	                    cost.save(get_TrxName());
	                    continue;
                    }
               }
            }
            
            rs.close();
            pstmt.close();

		}
		catch (Exception e)
		{
			log.log(Level.SEVERE,"doIt - " + sql, e);
                        return null;
		}
                
                
            return "ok";
     }
     

 	/**
 	 *  Calculate Cost 
 	 *  @param CostElementType Cost Element Type (Labor and Overhead.)
 	 *  @param AD_Org_ID Organization
 	 *  @param M_Product_ID Product
 	 *  @param M_CostType_ID Cost Type
 	 *  @param C_AcctSchema_ID Account Schema
 	 *  @return Cost for this Element
 	 *  @throws Exception if not successful
 	 */
     private BigDecimal getCost(String CostElementType , int AD_Org_ID , int M_Product_ID , int M_CostType_ID , int  C_AcctSchema_ID)
     {                
         BigDecimal cost = Env.ZERO;        
         
         int AD_Workflow_ID =  getAD_Workflow_ID(AD_Org_ID , M_Product_ID);
         if (AD_Workflow_ID != 0)
         {    
            MWorkflow Workflow = new MWorkflow(getCtx(),AD_Workflow_ID,get_TrxName());                 
            MWFNode[] nodes = Workflow.getNodes(false,getAD_Client_ID());
            
            for (MWFNode node : nodes )
            {               
                BigDecimal rate = getRate(CostElementType, node.getS_Resource_ID(), AD_Org_ID , C_AcctSchema_ID , M_CostType_ID);
                String sql = "SELECT CASE WHEN ow.DurationUnit = 's'  THEN 1 * ( (onode.SetupTime/ow.QtyBatchSize) + onode.Duration ) WHEN ow.DurationUnit = 'm' THEN 60 * ( (onode.SetupTime/ow.QtyBatchSize)  + onode.Duration) WHEN ow.DurationUnit = 'h'  THEN 3600 * ( (onode.SetupTime/ow.QtyBatchSize)  + onode.Duration) WHEN ow.DurationUnit = 'Y'  THEN 31536000 *  ( (onode.SetupTime/ow.QtyBatchSize)  + onode.Duration) WHEN ow.DurationUnit = 'M' THEN 2592000 * ( (onode.SetupTime/ow.QtyBatchSize)  + onode.Duration ) WHEN ow.DurationUnit = 'D' THEN 86400 * ((onode.SetupTime/ow.QtyBatchSize)  + onode.Duration) END  AS load FROM AD_WF_Node onode INNER JOIN AD_Workflow ow ON (ow.AD_Workflow_ID =  onode.AD_Workflow_ID)  WHERE onode.AD_WF_Node_ID = ?  AND onode.AD_Client_ID = ?" ;
                int seconds = DB.getSQLValue(get_TrxName(),sql,node.getAD_WF_Node_ID(),node.getAD_Client_ID());
                int C_UOM_ID = DB.getSQLValue(get_TrxName(),"SELECT C_UOM_ID FROM M_Product WHERE S_Resource_ID = ? " , node.getS_Resource_ID());
	 			MUOM oum = new MUOM(getCtx(),C_UOM_ID,get_TrxName());
	 			if (oum.isHour())
	 			{	 			 
	 				
	 			 	BigDecimal time = new BigDecimal(seconds);
	 			 	cost = cost.add(time.multiply(rate).divide(new BigDecimal(3600),BigDecimal.ROUND_HALF_UP,6));
	 			 	log.info("Yes isHour" + seconds);
	 				log.info("seconds/3600"+ seconds/3600);
	 				log.info("time.multiply(rate)"+ time.multiply(rate));
	 				log.info("Cost" + cost);
	 			}	
                log.info("Node" + node.getName() + " CostElementType"+ CostElementType +" Duration=" + node.getDuration() +  " rate:" + rate + " Cost:" +  cost);
            }
            return cost;
         }
         
         return cost;
         
     }
     
  	/**
  	 *  get Rate for this Resource
  	 *  @param CostElementType Cost Element Type (Labor and Overhead.)
  	 *  @param S_Resource_ID Respurce
  	 *  @param AD_Org_ID Organization
  	 *  @param C_AcctSchema_ID Account Schema
  	 *  @param M_CostType_ID Cost Type
  	 *  @return Rate for Resource
  	 *  @throws Exception if not successful
  	 */
     private  BigDecimal getRate(String CostElementType , int S_Resource_ID , int AD_Org_ID , int C_AcctSchema_ID ,int M_CostType_ID)
     {
        int M_Product_ID = getM_Product_ID(S_Resource_ID);       
        MCost[]  costs = MCost.getCosts(getCtx() , getAD_Client_ID(),   p_AD_Org_ID  ,  M_Product_ID ,  p_M_CostType_ID , p_C_AcctSchema_ID , get_TrxName());            
        if (costs != null)
        {    
            BigDecimal rate = Env.ZERO;
            
            for (MCost cost: costs)
            {                   
                MCostElement element = new MCostElement(getCtx(), cost .getM_CostElement_ID(), get_TrxName());
                // check if element cost is of type Labor
                if (element.getCostElementType().equals(CostElementType))
                {                        
                    rate = rate.add(cost.getCurrentCostPrice());
                    log.info("Org" + AD_Org_ID + "S_Resource" + S_Resource_ID + "C_AcctSchema_ID " + C_AcctSchema_ID);
                    log.info("Element rate=" + CostElementType +  "rate:" + rate);                                                 
                }
            }
            return rate;
        }     
        return Env.ZERO;
     }
     
 	/**
   	 *  get Product of Resource 
   	 *  @param S_Resource_ID Resource
   	 *  @return Product ID
   	 **/
     private int getM_Product_ID(int S_Resource_ID)
     {
    	String whereClause = "S_Resource_ID =  ?";
 		Query query = MTable.get(getCtx(), MProduct.Table_ID)
 							.createQuery(whereClause, get_TrxName());
 		query.setParameters(new Object[]{S_Resource_ID});

 		List<MProduct> products = query.list();
 		for(MProduct product : products)
 		{                                      
           return product.getM_Product_ID();
        }
        return 0;
     }
     
  	 /**
     *  get Manufacturing Workflow 
     *  @param AD_Org Organization ID
     *  @param M_Product_ID Product ID
     *  @return Workflow ID
     **/     
     private int getAD_Workflow_ID(int AD_Org_ID , int M_Product_ID)
     {
         
         MPPProductPlanning pp = MPPProductPlanning.get(getCtx(), AD_Org_ID , M_Product_ID, get_TrxName());                 
         MProduct M_Product = new MProduct(getCtx(), M_Product_ID,null);
         
         int  AD_Workflow_ID = 0;       
        
         if ( pp == null )
         {
         	String whereClause = "Name=?";
     		Query query = MTable.get(getCtx(), MWorkflow.Table_ID)
     							.createQuery(whereClause, get_TrxName());
     		query.setParameters(new Object[]{M_Product.getName()});

     		List<MWorkflow> workflows = query.list();
     		for (MWorkflow workflow : workflows)
     		{                                          
              return workflow.getAD_Workflow_ID();
            }
         }
         else
         {
              AD_Workflow_ID = pp.getAD_Workflow_ID();  
         }
               
         return AD_Workflow_ID;
         }
                                                                
}
