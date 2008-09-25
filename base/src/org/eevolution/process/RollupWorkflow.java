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
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MClient;
import org.compiere.model.MCost;
import org.compiere.model.MCostElement;
import org.compiere.model.MProduct;
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
		//
		if (p_C_AcctSchema_ID <= 0)
		{
			p_C_AcctSchema_ID = MClient.get(getCtx(), getAD_Client_ID()).getInfo().getC_AcctSchema1_ID();
		}
		if (p_M_CostType_ID <= 0)
		{
			MAcctSchema as = MAcctSchema.get(getCtx(), p_C_AcctSchema_ID);
			p_M_CostType_ID = as.getM_CostType_ID();
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
		StringBuffer whereClause = new StringBuffer("AD_Client_ID=?");
		params.add(getAD_Client_ID());
		
		whereClause.append(" AND ").append(MProduct.COLUMNNAME_ProductType).append("=?");
		params.add(MProduct.PRODUCTTYPE_Item);
		
		whereClause.append(" AND ").append(MProduct.COLUMNNAME_IsBOM).append("=?");
		params.add(true);
		
		if (p_M_Product_ID > 0)
		{  
			whereClause.append(" AND ").append(MProduct.COLUMNNAME_M_Product_ID).append("=?");
			params.add(p_M_Product_ID);
		}	
		else if (p_M_Product_Category_ID > 0)
		{
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
					BigDecimal Labor = getCost(MCostElement.COSTELEMENTTYPE_Resource, p_AD_Org_ID, product);
					log.info("Labor : " + Labor);                                
					cost.setCurrentCostPrice(Labor);
					cost.saveEx();
				}
				else if (element.getCostElementType().equals(MCostElement.COSTELEMENTTYPE_BurdenMOverhead))
				{                                    
					BigDecimal Burden = getCost(MCostElement.COSTELEMENTTYPE_BurdenMOverhead, p_AD_Org_ID, product);
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
	 *  @param MProduct product (BOM)
	 *  @param M_CostType_ID Cost Type
	 *  @param C_AcctSchema_ID Account Schema
	 *  @return Cost for this Element
	 *  @throws Exception if not successful
	 */
	private BigDecimal getCost(String CostElementType, int AD_Org_ID, MProduct product)
	{
		MPPProductPlanning pp = MPPProductPlanning.find(getCtx(), AD_Org_ID, 0, 0, product.get_ID(), get_TrxName());                 
		int AD_Workflow_ID = 0;
		if (pp != null)
		{
			AD_Workflow_ID = pp.getAD_Workflow_ID();
		}
		if (AD_Workflow_ID <= 0)
		{
			AD_Workflow_ID = MWorkflow.getWorkflowSearchKey(getCtx(), product);
		}
		if(AD_Workflow_ID <= 0)
		{
			return Env.ZERO;
		}

		BigDecimal cost = Env.ZERO;
		MWorkflow workflow = MWorkflow.get(getCtx(), AD_Workflow_ID);
		MWFNode[] nodes = workflow.getNodes(false, getAD_Client_ID());
		for (MWFNode node : nodes)
		{               
			BigDecimal rate = getResouceRate(CostElementType, node.getS_Resource_ID(), AD_Org_ID);
			if (rate.signum() == 0)
			{
				continue;
			}
			
			int C_UOM_ID = DB.getSQLValue(get_TrxName(),"SELECT C_UOM_ID FROM M_Product WHERE S_Resource_ID = ? " , node.getS_Resource_ID());
			MUOM uom = MUOM.get(getCtx(), C_UOM_ID);
			if (uom.isHour())
			{
				double hours = (node.getSetupTime() / workflow.getQtyBatchSize().doubleValue() + node.getDuration())
									* workflow.getDurationBaseSec() / 3600; 
 				BigDecimal nodeCost = new BigDecimal(hours).multiply(rate).setScale(12, RoundingMode.HALF_UP);
				cost = cost.add(nodeCost);
				log.info("Node:" + node.getName() + " CostElementType:"+ CostElementType +" Duration(H):" + hours +  " rate:" + rate + " NodeCost:" +  nodeCost +" =>Cost:"+cost);
			}
			else
			{
				throw new AdempiereException("@NotSupported@ @C_UOM_ID@="+uom.getName());
			}
		}
		return cost;        
	}

	/**
	 *  Get Rate for this Resource
	 *  @param CostElementType Cost Element Type (Labor and Overhead.)
	 *  @param S_Resource_ID Resource
	 *  @param AD_Org_ID Organization
	 *  @return Rate for Resource
	 *  @throws Exception if not successful
	 */
	private BigDecimal getResouceRate(String CostElementType, int S_Resource_ID, int AD_Org_ID)
	{
		final String sql = "SELECT SUM(c."+MCost.COLUMNNAME_CurrentCostPrice+")"
						+" FROM M_Cost c, M_CostElement ce, M_Product p"
						+" WHERE c.AD_Client_ID=? AND c.AD_Org_ID=?"
							+" AND c."+MCost.COLUMNNAME_C_AcctSchema_ID+"=?"
							+" AND c."+MCost.COLUMNNAME_M_CostType_ID+"=?"
							// Cost Element Type
							+" AND ce."+MCostElement.COLUMNNAME_M_CostElement_ID+"=c."+MCost.COLUMNNAME_M_CostElement_ID
							+" AND ce."+MCostElement.COLUMNNAME_CostElementType+"=?"
							// Product / Resource
							+" AND p."+MProduct.COLUMNNAME_M_Product_ID+"=c."+MCost.COLUMNNAME_M_Product_ID
							+" AND p."+MProduct.COLUMNNAME_S_Resource_ID+"=?"
		;
		BigDecimal rate = DB.getSQLValueBD(get_TrxName(), sql, getAD_Client_ID(), AD_Org_ID,
											p_C_AcctSchema_ID, p_M_CostType_ID,
											CostElementType, S_Resource_ID);
		return (rate != null ? rate : Env.ZERO);
	}                                                       
}
