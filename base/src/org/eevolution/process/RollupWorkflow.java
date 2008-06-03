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
//import compiere.model.*;
import org.eevolution.model.MPPProductPlanning;
import org.eevolution.model.QueryDB;

/**
 *	Rollup of Rouning
 *	
 *  @author Victor Perez, e-Evolution, S.C.
 *  @version $Id: RollupWorkflow.java,v 1.1 2004/06/22 05:24:03 vpj-cd Exp $
 */
public class RollupWorkflow extends SvrProcess
{
	/**					*/
       
       private int		 		   p_AD_Org_ID = 0;
       private int               p_C_AcctSchema_ID = 0;
       //private int               p_M_Warehouse_ID = 0;
       //private int               p_S_Resource_ID = 0;
       private int               p_M_Product_ID = 0;
       private int               p_M_CostType_ID = 0;
       private int               p_M_Product_Category_ID = 0;
       
       //private String            p_ElementType = "";
       
       
        
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
                        /*else if (name.equals("M_Warehouse_ID"))
                        {    
				p_M_Warehouse_ID = ((BigDecimal)para[i].getParameter()).intValue();
                                
                        }*/
                        else if (name.equals("M_Product_ID"))
                        {    
				p_M_Product_ID = ((BigDecimal)para[i].getParameter()).intValue();
                                
                        }
                        /*else if (name.equals("S_Resource_ID"))
                        {    
				p_S_Resource_ID = ((BigDecimal)para[i].getParameter()).intValue();
                                
                        }
                        else if (name.equals("PP_Cost_Group_ID"))
                        {    
				
                                p_PP_Cost_Group_ID = ((BigDecimal)para[i].getParameter()).intValue();
                        }*/
                        else if (name.equals("M_CostType_ID"))
                        {    
				
                                p_M_CostType_ID = ((BigDecimal)para[i].getParameter()).intValue();
                        }
                        //else if (name.equals("ElementType"))
                        //{    
			//	p_ElementType = (String)para[i].getParameter();
                        //        
                        //}
                        else if (name.equals("C_AcctSchema_ID"))
                        {    
				p_C_AcctSchema_ID = ((BigDecimal)para[i].getParameter()).intValue();
                                
                        } 
                        /*else if (name.equals("M_Produc_Category_ID"))
                        {    
				p_M_Product_Category_ID = ((BigDecimal)para[i].getParameter()).intValue();
                                
                        }*/
                        else
				log.log(Level.SEVERE,"prepare - Unknown Parameter: " + name);
		}
	}	//	prepare

        
     protected String doIt() throws Exception                
     {
            
     	int AD_Client_ID =getAD_Client_ID(); //Integer.parseInt(Env.getContext(Env.getCtx(), "#AD_Client_ID"));
         
                StringBuffer sql = new StringBuffer ("SELECT p.M_Product_ID FROM M_Product p WHERE p.ProductType = '" + MProduct.PRODUCTTYPE_Item + "' AND");
                
                if (p_M_Product_ID != 0)
                {    
                sql.append(" p.M_Product_ID = " + p_M_Product_ID + " AND ");
                }               
                sql.append(" p.AD_Client_ID = " + AD_Client_ID);
                sql.append(" ORDER BY p.LowLevel");
                
                
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql.toString(), get_TrxName());                       						
                        ResultSet rs = pstmt.executeQuery ();
                        while (rs.next())
                        {
                           //System.out.println("Exist Product" ); 
                           int M_Product_ID = rs.getInt("M_Product_ID");                           
                           org.eevolution.model.MCost[]  pc = org.eevolution.model.MCost.getElements( M_Product_ID , p_C_AcctSchema_ID , p_M_CostType_ID);            
                           for (int e = 0 ; e < pc.length ; e ++ )
                           {
                                MCostElement element = new MCostElement(getCtx(), pc[e].getM_CostElement_ID(),null);
                                // check if element cost is of type Labor
                                if (element.getCostElementType().equals(element.COSTELEMENTTYPE_Resource))
                                {                                    
                                BigDecimal Labor = getCost(element.COSTELEMENTTYPE_Resource , p_AD_Org_ID , M_Product_ID , p_M_CostType_ID , p_C_AcctSchema_ID);
                                log.info("Labor : " + Labor);                                
                                pc[e].setCurrentCostPrice(Labor);
                                pc[e].save(get_TrxName());
                                continue;
                                }
                                if (element.getCostElementType().equals(element.COSTELEMENTTYPE_BurdenMOverhead))
                                {                                    
                                BigDecimal Burden = getCost(element.COSTELEMENTTYPE_BurdenMOverhead, p_AD_Org_ID , M_Product_ID , p_M_CostType_ID , p_C_AcctSchema_ID);
                                log.info("Burden : " + Burden);                                   
                                pc[e].setCurrentCostPrice(Burden);
                                pc[e].save(get_TrxName());
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
     

     
     private BigDecimal getCost(String CostElementType , int AD_Org_ID , int M_Product_ID , int M_CostType_ID , int  C_AcctSchema_ID)
     {         
     	 
         BigDecimal totalcost = Env.ZERO;         
         BigDecimal cost = Env.ZERO;        
         
         int AD_Workflow_ID =  getAD_Workflow_ID(AD_Org_ID , M_Product_ID);
         //System.out.println(".................................................................................AD_Workflow_ID=" + AD_Workflow_ID);         
         if (AD_Workflow_ID != 0)
         {    
            //System.out.println("................................................................................Exist AD_Workflow_ID=" + AD_Workflow_ID);
            MWorkflow Workflow = new MWorkflow(getCtx(),AD_Workflow_ID,null);                 
            MWFNode[] nodes = Workflow.getNodes(false,getAD_Client_ID());
            
            for (int i = 0 ; i < nodes.length ; i ++ )
            {
                MWFNode node = (MWFNode) nodes[i];                
                BigDecimal rate = getRate(CostElementType, node.getS_Resource_ID(), AD_Org_ID , C_AcctSchema_ID , M_CostType_ID);
                String sql = "SELECT CASE WHEN ow.DurationUnit = 's'  THEN 1 * ( (onode.SetupTime/ow.QtyBatchSize) + onode.Duration ) WHEN ow.DurationUnit = 'm' THEN 60 * ( (onode.SetupTime/ow.QtyBatchSize)  + onode.Duration) WHEN ow.DurationUnit = 'h'  THEN 3600 * ( (onode.SetupTime/ow.QtyBatchSize)  + onode.Duration) WHEN ow.DurationUnit = 'Y'  THEN 31536000 *  ( (onode.SetupTime/ow.QtyBatchSize)  + onode.Duration) WHEN ow.DurationUnit = 'M' THEN 2592000 * ( (onode.SetupTime/ow.QtyBatchSize)  + onode.Duration ) WHEN ow.DurationUnit = 'D' THEN 86400 * ((onode.SetupTime/ow.QtyBatchSize)  + onode.Duration) END  AS load FROM AD_WF_Node onode INNER JOIN AD_Workflow ow ON (ow.AD_Workflow_ID =  onode.AD_Workflow_ID)  WHERE onode.AD_WF_Node_ID = ?  AND onode.AD_Client_ID = ?" ;
                int seconds = DB.getSQLValue(null,sql,node.getAD_WF_Node_ID(),node.getAD_Client_ID());
                int C_UOM_ID = DB.getSQLValue(null,"SELECT C_UOM_ID FROM M_Product WHERE S_Resource_ID = ? " , node.getS_Resource_ID());
	 			MUOM oum = new MUOM(getCtx(),C_UOM_ID,null);
	 			if (oum.isHour())
	 			{	 			 
	 				
	 			 	BigDecimal time = new BigDecimal(seconds);
	 			 	cost = cost.add(time.multiply(rate).divide(new BigDecimal(3600),BigDecimal.ROUND_HALF_UP,6));
	 			 	System.out.println("Yes isHour" + seconds);
	 				//System.out.println("seconds/3600"+ seconds/3600);
	 				//System.out.println("time.multiply(rate)"+ time.multiply(rate));
	 				System.out.println("Cost" + cost);
	 			}	
                //totalcost.add(cost);
                //System.out.println("Node" + node.getName() + " PP_ElementType"+ PP_ElementType +" Duration=" + node.getDuration() +  " rate:" + rate + " Cost:" +  cost);
                log.info("Node" + node.getName() + " CostElementType"+ CostElementType +" Duration=" + node.getDuration() +  " rate:" + rate + " Cost:" +  cost);
            }
            return cost;
         }
         
         return cost;
         
     }
     
     private  BigDecimal getRate(String CostElementType , int S_Resource_ID , int AD_Org_ID , int C_AcctSchema_ID ,int M_CostType_ID)
     {
                int M_Product_ID = getM_Product_ID(S_Resource_ID);
                
                //System.out.println("...................................................................RATE:Org :" + AD_Org_ID + " S_ResourceProduct_ID:" + S_Resource_ID + " C_AcctSchema_ID :" + C_AcctSchema_ID+ " M_Warehouse_ID:" + M_Warehouse_ID + " PLAN:" + S_ResourcePlant_ID);
                
                // get the rate for this resource public static MPPProductCosting[] getElements (int M_Product_ID, int C_AcctSchema_ID, int PP_Cost_Group_ID , int M_Warehouse_ID, int S_Resource_ID , boolean requery)
                                                     
                org.eevolution.model.MCost[]  pc = org.eevolution.model.MCost.getElements(M_Product_ID , C_AcctSchema_ID , M_CostType_ID);       
                if (pc != null)
                {    
                    //System.out.println("............." + "MPPProductCosting[].size=" + pc.length);
                    BigDecimal rate = Env.ZERO;
                    
                    for (int e = 0 ; e < pc.length ; e ++ )
                    {                   
                        MCostElement element = new MCostElement(getCtx(), pc[e].getM_CostElement_ID(),null);
                        // check if element cost is of type Labor
                        if (element.getCostElementType().equals(CostElementType))
                        {                        
                        rate = rate.add(pc[e].getCurrentCostPrice());
                        log.info("Org" + AD_Org_ID + "S_Resource" + S_Resource_ID + "C_AcctSchema_ID " + C_AcctSchema_ID);
                        //System.out.println("Org" + AD_Org_ID + "S_Resource" + S_Resource_ID + "C_AcctSchema_ID " + C_AcctSchema_ID+ "M_Warehouse_ID" + M_Warehouse_ID + "PLAN" + S_ResourcePlant_ID);
                        log.info("Element rate=" + CostElementType +  "rate:" + rate);
                        //System.out.println("Element rate=" + PP_ElementType +  "rate:" + rate);                                                 
                        }
                    }
                    return rate;
                }     
                return Env.ZERO;
     }
     
     private int getM_Product_ID(int S_Resource_ID)
     {
                    QueryDB query = new QueryDB("org.compiere.model.X_M_Product");
                    String filter = "S_Resource_ID = " + S_Resource_ID;
                    java.util.List results = query.execute(filter);
                    Iterator select = results.iterator();
                    while (select.hasNext())
                    {
                     X_M_Product M_Product =  (X_M_Product) select.next();                                          
                     return M_Product.getM_Product_ID();
                    }
         
                    return 0;
     }
     
         
     private int getAD_Workflow_ID(int AD_Org_ID , int M_Product_ID)
     {
         
         MPPProductPlanning pp = MPPProductPlanning.get(getCtx(), AD_Org_ID , M_Product_ID, null);                 
         MProduct M_Product = new MProduct(getCtx(), M_Product_ID,null);
         
         int  AD_Workflow_ID = 0;       
        
         if ( pp == null )
         {
                    //System.out.println("pp.getAD_Workflow_ID() ............. " + pp.getAD_Workflow_ID());
                    QueryDB  query = new QueryDB("org.compiere.model.X_AD_Workflow");
                    String filter = "Name = '" + M_Product.getName() + "'";
                    java.util.List results = query.execute(filter);
                    Iterator select = results.iterator();
                    while (select.hasNext())
                    {
                     X_AD_Workflow AD_Workflow =  (X_AD_Workflow) select.next();                                          
                     return AD_Workflow.getAD_Workflow_ID();
                    }
         }
         else
         {
              AD_Workflow_ID = pp.getAD_Workflow_ID();  
         }
         
         //System.out.println("Product" + pp.getM_Product_ID() + "Workflow" + pp.getAD_Workflow_ID());         
         return AD_Workflow_ID;
        
         }
                                                                
}	//	OrderOpen
