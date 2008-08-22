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
import org.compiere.wf.MWorkflow;
import org.compiere.process.*;
import org.eevolution.model.MPPMRP;
import org.eevolution.model.MPPProductBOM;
import org.eevolution.model.MPPProductBOMLine;
import org.eevolution.model.MPPProductPlanning;
import org.eevolution.model.QueryDB;

import com.sun.org.apache.bcel.internal.generic.RETURN;

/**
 *	Roll-UP Bill of Material 
 *	
 *  @author victor.perez@e-evolution.com, e-Evolution, S.C.
 *  @version $Id: RollupBillOfMaterial.java,v 1.1 2004/06/22 05:24:03 vpj-cd Exp $
 */
public class RollupBillOfMaterial extends SvrProcess
{
   /* Organization 		*/ 
   private int		 		 p_AD_Org_ID = 0;
   /* Account Schema 	*/
   private int               p_C_AcctSchema_ID = 0;
   /* Cost Type			*/
   private int               p_M_CostType_ID = 0;
   /* Product 			*/
   private int               p_M_Product_ID = 0;
   /* Product Category  */
   private int               p_M_Product_Category_ID=0;

   private int Elementtypeint=0;
       
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
				p_AD_Org_ID = para[i].getParameterAsInt();
            else if (name.equals(MAcctSchema.COLUMNNAME_C_AcctSchema_ID))  
    			p_C_AcctSchema_ID = para[i].getParameterAsInt();
            else if (name.equals(MCostType.COLUMNNAME_M_CostType_ID))
                p_M_CostType_ID = para[i].getParameterAsInt();
            else if (name.equals(MProduct.COLUMNNAME_M_Product_ID))   
				p_M_Product_ID = para[i].getParameterAsInt();
            else if (name.equals(MProduct.COLUMNNAME_M_Product_Category_ID))
                p_M_Product_Category_ID = para[i].getParameterAsInt();
            else
				log.log(Level.SEVERE,"prepare - Unknown Parameter: " + name);
		}
	}	//	prepare

	/**
	 * 	Generate Calculate Cost
	 *	@return info
	 *	@throws Exception
	 */   
     protected String doIt() throws Exception                
     {
            
       int m_LowLevel = MPPMRP.getMaxLowLevel(getCtx(), get_TrxName());
       // Cost Roll-up for all levels
       for (int index = m_LowLevel ; index >= 0 ; index--)
       {   
		  StringBuffer whereClause = new StringBuffer("AD_Client_ID=? AND LowLevel=?  AND ProductType='"+MProduct.PRODUCTTYPE_Item+"'");
	
		  List<Object> params = new ArrayList<Object>();
		  params.add(getAD_Client_ID());
		  params.add(m_LowLevel);
		  if (p_M_Product_ID > 0) {  
	       	whereClause.append(" AND p.M_Product_ID=?");
	   		params.add(p_M_Product_ID);
	      }		
	      if (p_M_Product_Category_ID > 0){
	        whereClause.append(" AND p.M_Product_Category_ID=?");
	   		params.add(p_M_Product_ID);
	      }	
	       
	      List<MProduct> products = new Query(getCtx(),MProduct.Table_Name, whereClause.toString(), get_TrxName())
	       								.setParameters(params).list();
	      
	      for (MProduct product : products)
	      {	  
	                                                  
            MCost[]  costs = MCost.getCosts(getCtx(),getAD_Client_ID(),   p_AD_Org_ID  ,  product.getM_Product_ID() ,  p_M_CostType_ID , p_C_AcctSchema_ID , get_TrxName());
            for (MCost cost : costs )
            {        
            	 log.info("Calculate Lower Cost for :"+ product.getName());
                MCostElement element = new MCostElement(getCtx(), cost.getM_CostElement_ID(),get_TrxName());
                // check if element cost is of Material Type 
                log.info("Element Cost:"+ element.getName());
                if (element.getCostElementType().equals(MCostElement.COSTELEMENTTYPE_Material))
                {                 
                    BigDecimal Material = getCurrentCostPriceLL(MCostElement.COSTELEMENTTYPE_Material , p_AD_Org_ID , product , p_M_CostType_ID , p_C_AcctSchema_ID);     
                    log.info("Material Cost Low Level:" + Material);
                	cost.setCurrentCostPriceLL(Material);
                	cost.saveEx();
                }
                else if (element.getCostElementType().equals(MCostElement.COSTELEMENTTYPE_Resource))
                {
                	BigDecimal Labor = getCurrentCostPriceLL(MCostElement.COSTELEMENTTYPE_Resource, p_AD_Org_ID , product  , p_M_CostType_ID , p_C_AcctSchema_ID);     
                	log.info("Labor Cost Low Level:" + Labor);
                	cost.setCurrentCostPriceLL(Labor);
                	cost.saveEx();
                }
                else if (element.getCostElementType().equals(MCostElement.COSTELEMENTTYPE_BurdenMOverhead))
                {
                	BigDecimal Burder = getCurrentCostPriceLL(MCostElement.COSTELEMENTTYPE_BurdenMOverhead , p_AD_Org_ID , product  , p_M_CostType_ID , p_C_AcctSchema_ID);     
                	log.info("Burden Cost Low Level:" + Burder);
                	cost.setCurrentCostPriceLL(Burder);
                	cost.saveEx();
                }
                else if (element.getCostElementType().equals(MCostElement.COSTELEMENTTYPE_Overhead))
                {
                	BigDecimal Overhead = getCurrentCostPriceLL(MCostElement.COSTELEMENTTYPE_Overhead , p_AD_Org_ID , product   , p_M_CostType_ID , p_C_AcctSchema_ID);     
                	log.info("Overhead Cost Low Level:" + Overhead);
                	cost.setCurrentCostPriceLL(Overhead);
                	cost.saveEx();
                }
                else if (element.getCostElementType().equals(MCostElement.COSTELEMENTTYPE_OutsideProcessing))
                {
                	BigDecimal Subcontract = getCurrentCostPriceLL(MCostElement.COSTELEMENTTYPE_OutsideProcessing , p_AD_Org_ID , product  , p_M_CostType_ID , p_C_AcctSchema_ID);     
                	log.info("Subcontract Cost Low Level:" + Subcontract);
                	cost.setCurrentCostPriceLL(Subcontract);
                	cost.saveEx();
                }
                /* TODO Comment for future implementation
                else if (element.getCostElementType().equals(MCostElement.COSTELEMENTTYPE_Distribution))
                {
                            
                        BigDecimal Distribution = getCurrentCostPriceLL(MCostElement.COSTELEMENTTYPE_Distribution , p_AD_Org_ID , M_Product_ID  ,  p_M_CostType_ID , p_C_AcctSchema_ID);     
                        cost.setCurrentCostPriceLL(Distribution);
                    	cost.saveEx();
                 }*/   	  
		        } //Costs 
	        } //Products 
       } 		  
       return "@OK@";
     }
     

 	/** get the sum Current Cost Price Level Low for this Cost Element Type
 	 *	@param CostElementType Cost Element Type (Material,Labor,Overhead,Burden)
 	 *  @param AD_Org_ID Organization
 	 *  @param MProduct Product
 	 *  @param M_CostType_ID Cost Type
 	 *	@param C_AcctSchema_ID Account Schema
 	 *  @return CurrentCostPriceLL Sum Current Cost Price Level Low for this Cost Element Type
 	 */
     private BigDecimal getCurrentCostPriceLL(String CostElementType , int AD_Org_ID , MProduct product , int M_CostType_ID , int  C_AcctSchema_ID)
     {
         log.info("getCurrentCostPriceLL.ElementType"+CostElementType);
         BigDecimal m_cost = Env.ZERO; 
         MPPProductPlanning pp = MPPProductPlanning.find(getCtx(), getAD_Client_ID(), AD_Org_ID , 0 , 0 , product.getM_Product_ID(), get_TrxName());                 
         
         int PP_Product_BOM_ID = 0; 
         if(pp != null )
        	 PP_Product_BOM_ID = pp.getPP_Product_BOM_ID();
         else
        	 PP_Product_BOM_ID = MPPProductBOM.getBOMSearchKey(getCtx(), product);
         
         if(PP_Product_BOM_ID <= 0)
        	 return Env.ZERO;
         
         MPPProductBOM bom =  new MPPProductBOM(getCtx(), PP_Product_BOM_ID , get_TrxName()); 
         if (bom == null)
        	 return null;
         
         MPPProductBOMLine[] bomlines = bom.getLines();
         for (MPPProductBOMLine bomline : bomlines)
         {
             // get the rate for this resource     
             MCost[]  costs = MCost.getCosts(getCtx() , getAD_Client_ID(),   p_AD_Org_ID  ,  product.getM_Product_ID() ,  p_M_CostType_ID , p_C_AcctSchema_ID , get_TrxName());            
             for (MCost cost : costs)
             {                 
                 MCostElement element = new MCostElement(getCtx(), cost.getM_CostElement_ID(), get_TrxName());
                 // check if element cost is of type Labor
                 if (element.getCostElementType().equals(CostElementType))
                 {
                     BigDecimal QtyPercentage = bomline.getQtyBatch().divide(new BigDecimal(100),8,BigDecimal.ROUND_UP);
                     BigDecimal QtyBOM = bomline.getQtyBOM(); 
                     BigDecimal Scrap = bomline.getScrap();
                     Scrap = Scrap.divide(new BigDecimal(100),4,BigDecimal.ROUND_UP); //convert to decimal
                     BigDecimal QtyTotal = Env.ZERO;
                     if (bomline.isQtyPercentage())   
                         QtyTotal =  QtyPercentage.divide( Env.ONE.subtract(Scrap) , 4 ,BigDecimal.ROUND_HALF_UP );
                     else 
                    	 QtyTotal =  QtyBOM.divide( Env.ONE.subtract(Scrap) , 4 ,BigDecimal.ROUND_HALF_UP );
                     
                     m_cost = m_cost.add(cost.getCurrentCostPriceLL().multiply(QtyTotal));
                     log.info("Cost Element:"+element.getName() + "Total Cost Element:" +   m_cost + "QtyPercentage:" + QtyPercentage + "QtyBOM" + QtyBOM) ;
                 }
             }             
         }    
         // Try find planning Data for this product and get % Yield to calculate cost
         MPPProductPlanning pps = MPPProductPlanning.find(getCtx(), getAD_Client_ID() , AD_Org_ID , 0 , 0 , product.getM_Product_ID(), get_TrxName());
         if (pps != null)
         {            
        	int Yield = pps.getYield();
         	if(Yield != 0)
         	{
				BigDecimal  DecimalYield = new BigDecimal(Yield/100);
				if (!DecimalYield.equals(Env.ZERO))
					m_cost = m_cost.divide(DecimalYield, 4 ,BigDecimal.ROUND_HALF_UP);
         	}
         }                 
         return m_cost;     
     }  
}
