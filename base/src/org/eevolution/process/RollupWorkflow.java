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
 *                 Bogdan Ioan, www.arhipac.ro                                *
 *****************************************************************************/

package org.eevolution.process;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.MCost;
import org.compiere.model.MCostElement;
import org.compiere.model.MProduct;
import org.compiere.model.MResource;
import org.compiere.model.MUOM;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.wf.MWFNode;
import org.compiere.wf.MWorkflow;
import org.eevolution.model.MPPProductPlanning;

/**
 *	RollUp of Cost Manufacturing Workflow 
 *	This process calculate the Labor, Overhead, Burden Cost
 *  @author Victor Perez, e-Evolution, S.C.
 *  @version $Id: RollupWorkflow.java,v 1.1 2004/06/22 05:24:03 vpj-cd Exp $
 *  
 *  @author Bogdan Ioan, www.arhipac.ro
 *  		<li>BF [ 2093001 ] Error in Cost Workflow & Process Details
 */
public class RollupWorkflow extends SvrProcess
{

	/* Organization      */
	private int		 		 p_AD_Org_ID = 0;
	/* Account Schema   	*/
	private int               p_C_AcctSchema_ID = 0;
	/* Cost Type 		*/
	private int               p_M_CostType_ID = 0;    
	/* Product 			*/
	private int               p_M_Product_ID = 0;   
	/* Product Category 	*/
	private int 				 p_M_Product_Category_ID = 0;

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
			else if (name.equals(MCost.COLUMNNAME_C_AcctSchema_ID))
				p_C_AcctSchema_ID = para[i].getParameterAsInt();  
			else if (name.equals(MCost.COLUMNNAME_M_CostType_ID))
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
	 *  Perform process.
	 *  @return Message (text with variables)
	 *  @throws Exception if not successful
	 */   
	protected String doIt() throws Exception                
	{
		List<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer("AD_Client_ID=? AND ProductType=?");
		params.add(getAD_Client_ID());
		params.add(MProduct.PRODUCTTYPE_Item);
		
		if (p_M_Product_ID > 0) {  
			whereClause.append(" AND ").append(MProduct.COLUMNNAME_M_Product_ID).append("=?");
			params.add(p_M_Product_ID);
		}		
		if (p_M_Product_Category_ID > 0){
			whereClause.append(" AND ").append(MProduct.COLUMNNAME_M_Product_Category_ID).append("=?");
			params.add(p_M_Product_Category_ID);
		}	

		List<MProduct> products = new Query(getCtx(),MProduct.Table_Name, whereClause.toString(), get_TrxName())
											.setOrderBy(MProduct.COLUMNNAME_LowLevel)
											.setParameters(params)
											.list();    

		for (MProduct product : products)
		{  
			MCost[]  costs = MCost.getCosts(getCtx(), getAD_Client_ID(), p_AD_Org_ID, product.getM_Product_ID(), p_M_CostType_ID, p_C_AcctSchema_ID , get_TrxName());            
			for (MCost cost : costs)
			{
				MCostElement element = cost.getCostElement();
				// check if element cost is of type Labor
				if (element.getCostElementType().equals(MCostElement.COSTELEMENTTYPE_Resource))
				{                                    
					BigDecimal Labor = getCost(MCostElement.COSTELEMENTTYPE_Resource , getAD_Client_ID(), p_AD_Org_ID , product , p_M_CostType_ID , p_C_AcctSchema_ID);
					log.info("Labor : " + Labor);                                
					cost.setCurrentCostPrice(Labor);
					cost.saveEx();
				}
				else if (element.getCostElementType().equals(MCostElement.COSTELEMENTTYPE_BurdenMOverhead))
				{                                    
					BigDecimal Burden = getCost(MCostElement.COSTELEMENTTYPE_BurdenMOverhead, getAD_Client_ID() , p_AD_Org_ID , product , p_M_CostType_ID , p_C_AcctSchema_ID);
					log.info("Burden : " + Burden);                                   
					cost.setCurrentCostPrice(Burden);
					cost.saveEx();
				}
			}
		}                               
		return "@OK@";
	}


	/**
	 *  Calculate Cost 
	 *  @param CostElementType Cost Element Type (Labor and Overhead.)
	 *  @param AD_Org_ID Organization
	 *  @param MProduct Product
	 *  @param M_CostType_ID Cost Type
	 *  @param C_AcctSchema_ID Account Schema
	 *  @return Cost for this Element
	 *  @throws Exception if not successful
	 */
	private BigDecimal getCost(String CostElementType ,int AD_Client_ID , int AD_Org_ID , MProduct product , int M_CostType_ID , int  C_AcctSchema_ID)
	{                
		BigDecimal cost = Env.ZERO;        
		MPPProductPlanning pp = MPPProductPlanning.find(getCtx(), AD_Org_ID , 0 , 0 , product.getM_Product_ID(), get_TrxName());                 
		int AD_Workflow_ID = 0; 
		if(pp != null )
			AD_Workflow_ID = pp.getAD_Workflow_ID();
		else
			AD_Workflow_ID = MWorkflow.getWorkflowSearchKey(getCtx(), product);
		if(AD_Workflow_ID <= 0)
			return Env.ZERO;

		MWorkflow workflow = MWorkflow.get(getCtx(), AD_Workflow_ID);                 
		MWFNode[] nodes = workflow.getNodes(false,getAD_Client_ID());
		for (MWFNode node : nodes)
		{               
			BigDecimal rate = getRate(CostElementType, node.getS_Resource_ID(), AD_Org_ID , C_AcctSchema_ID , M_CostType_ID);
			String sql = "SELECT CASE WHEN ow.DurationUnit = 's'  THEN 1 * ( (onode.SetupTime/ow.QtyBatchSize) + onode.Duration ) WHEN ow.DurationUnit = 'm' THEN 60 * ( (onode.SetupTime/ow.QtyBatchSize)  + onode.Duration) WHEN ow.DurationUnit = 'h'  THEN 3600 * ( (onode.SetupTime/ow.QtyBatchSize)  + onode.Duration) WHEN ow.DurationUnit = 'Y'  THEN 31536000 *  ( (onode.SetupTime/ow.QtyBatchSize)  + onode.Duration) WHEN ow.DurationUnit = 'M' THEN 2592000 * ( (onode.SetupTime/ow.QtyBatchSize)  + onode.Duration ) WHEN ow.DurationUnit = 'D' THEN 86400 * ((onode.SetupTime/ow.QtyBatchSize)  + onode.Duration) END  AS load FROM AD_WF_Node onode INNER JOIN AD_Workflow ow ON (ow.AD_Workflow_ID =  onode.AD_Workflow_ID)  WHERE onode.AD_WF_Node_ID = ?  AND onode.AD_Client_ID = ?" ;
			int seconds = DB.getSQLValue(get_TrxName(),sql,node.getAD_WF_Node_ID(),node.getAD_Client_ID());
			int C_UOM_ID = DB.getSQLValue(get_TrxName(),"SELECT C_UOM_ID FROM M_Product WHERE S_Resource_ID = ? " , node.getS_Resource_ID());
			MUOM uom = MUOM.get(getCtx(), C_UOM_ID);
			if (uom.isHour())
			{	 			 
				BigDecimal time = new BigDecimal(seconds);
				cost = cost.add(time.multiply(rate).divide(new BigDecimal(3600),BigDecimal.ROUND_HALF_UP,12));
				log.info("Yes isHour" + seconds);
				log.info("seconds/3600"+ seconds/3600);
				log.info("time.multiply(rate)"+ time.multiply(rate));
				log.info("Cost" + cost);
			}	
			log.info("Node" + node.getName() + " CostElementType"+ CostElementType +" Duration=" + node.getDuration() +  " rate:" + rate + " Cost:" +  cost);
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
		MResource resource = MResource.get(getCtx(), S_Resource_ID);
		MProduct product = resource.getProduct();
		MCost[]  costs = MCost.getCosts(getCtx() , getAD_Client_ID(),   p_AD_Org_ID  ,  product.getM_Product_ID() ,  p_M_CostType_ID , p_C_AcctSchema_ID , get_TrxName());            
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
}
