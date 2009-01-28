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
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.MAcctSchema;
import org.compiere.model.MClient;
import org.compiere.model.MCost;
import org.compiere.model.MCostElement;
import org.compiere.model.MProduct;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
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

	/* Organization     */
	private int		 		p_AD_Org_ID = 0;
	/* Account Schema   */
	private int             p_C_AcctSchema_ID = 0;
	/* Cost Type 		*/
	private int             p_M_CostType_ID = 0;    
	/* Product 			*/
	private int             p_M_Product_ID = 0;   
	/* Product Category */
	private int 			p_M_Product_Category_ID = 0;
	/* Costing Method 	*/
	private String 			p_ConstingMethod = MCostElement.COSTINGMETHOD_StandardCosting;
	
	private MAcctSchema m_as = null;

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		for (ProcessInfoParameter para : getParameter())
		{
			String name = para.getParameterName();

			if (para.getParameter() == null)
				;
			else if (name.equals(MCost.COLUMNNAME_AD_Org_ID))  
				p_AD_Org_ID = para.getParameterAsInt();       
			else if (name.equals(MCost.COLUMNNAME_C_AcctSchema_ID))
			{	
				p_C_AcctSchema_ID = para.getParameterAsInt();  
				m_as = MAcctSchema.get(getCtx(), p_C_AcctSchema_ID);
			}	
			else if (name.equals(MCost.COLUMNNAME_M_CostType_ID))
				p_M_CostType_ID = para.getParameterAsInt();  
			else if (name.equals(MCostElement.COLUMNNAME_CostingMethod))
				p_ConstingMethod=(String)para.getParameter();
			else if (name.equals(MProduct.COLUMNNAME_M_Product_ID)) 
				p_M_Product_ID = para.getParameterAsInt();  
			else if (name.equals(MProduct.COLUMNNAME_M_Product_Category_ID)) 
				p_M_Product_Category_ID = para.getParameterAsInt();  
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

		Collection<MProduct> products = new Query(getCtx(),MProduct.Table_Name, whereClause.toString(), get_TrxName())
											.setOrderBy(MProduct.COLUMNNAME_LowLevel)
											.setParameters(params)
											.list();    

		for (MProduct product : products)
		{  
			MPPProductPlanning pp = MPPProductPlanning.find( getCtx(), p_AD_Org_ID , 0, 0, product.get_ID(), get_TrxName());                 
			int AD_Workflow_ID = 0;
			if (pp != null)
			{
				AD_Workflow_ID = pp.getAD_Workflow_ID();
			}
			if (AD_Workflow_ID <= 0)
			{
				AD_Workflow_ID = MWorkflow.getWorkflowSearchKey(product);
			}
			if(AD_Workflow_ID <= 0)
			{
				continue;
			}
			
			MWorkflow workflow = MWorkflow.get(getCtx(), AD_Workflow_ID);
			workflow.setCost(Env.ZERO);
			
			MWFNode[] nodes = workflow.getNodes(false, getAD_Client_ID());
			for (MWFNode node : nodes)
			{
				node.setCost(Env.ZERO);
			}
			
			BigDecimal labor = Env.ZERO;
			BigDecimal burden = Env.ZERO;
			Collection<MCostElement> elements = MCostElement.getByCostingMethod(getCtx(),  p_ConstingMethod);
			for (MCostElement element : elements)
			{	
				Collection<MCost> costs = MCost.getByCostType(
						product,
						m_as,
						p_M_CostType_ID,
						p_AD_Org_ID,
						0,element.getCostElementType()); // ASI
				for (MCost cost : costs)
				{
					if(MCostElement.COSTELEMENTTYPE_Resource.equals(element.getCostElementType()) 
					|| MCostElement.COSTELEMENTTYPE_BurdenMOverhead.equals(element.getCostElementType()))
					{					

						for (MWFNode node : nodes)
						{   	
							BigDecimal nodeCost = Env.ZERO;
							// check if element cost is of type Labor
							if (MCostElement.COSTELEMENTTYPE_Resource.equals(element.getCostElementType()))
							{ 
								nodeCost = node.getCostForCostElementType(MCostElement.COSTELEMENTTYPE_Resource ,p_C_AcctSchema_ID,  p_M_CostType_ID, p_AD_Org_ID, node.getSetupTime(), node.getDuration());
								labor = labor.add(nodeCost);
							}
							else if (MCostElement.COSTELEMENTTYPE_BurdenMOverhead.equals(element.getCostElementType()))
							{                                    
								nodeCost = node.getCostForCostElementType(MCostElement.COSTELEMENTTYPE_BurdenMOverhead ,p_C_AcctSchema_ID,  p_M_CostType_ID, p_AD_Org_ID, node.getSetupTime(), node.getDuration());                                 
								burden = burden.add(nodeCost);
							}
							if(nodeCost.signum() != 0)
							{	
								node.setCost(node.getCost().add(nodeCost));
								node.saveEx();
							}
						} // Node
						// check if element cost is of type Labor
						if (MCostElement.COSTELEMENTTYPE_Resource.equals(element.getCostElementType()))
						{  
							log.info("Product:"+product.getName()+" Labor: " + labor);                                
							cost.setCurrentCostPrice(labor);
							cost.saveEx();
						}
						else if (MCostElement.COSTELEMENTTYPE_BurdenMOverhead.equals(element.getCostElementType()))
						{                                    
							log.info("Product:"+product.getName()+" Burden: " + burden);                                   
							cost.setCurrentCostPrice(burden);
							cost.saveEx();
						}
				}
			} // MCost
			} // Cost Elements
			workflow.setCost(labor.add(burden));
			workflow.saveEx(get_TrxName());
				
		}                               
		return "@OK@";
	}                 
	
	
}
