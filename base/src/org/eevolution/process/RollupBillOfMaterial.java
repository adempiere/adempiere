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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.MAcctSchema;
import org.compiere.model.MCost;
import org.compiere.model.MCostElement;
import org.compiere.model.MCostType;
import org.compiere.model.MProduct;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.eevolution.model.MPPMRP;
import org.eevolution.model.MPPProductBOM;
import org.eevolution.model.MPPProductBOMLine;
import org.eevolution.model.MPPProductPlanning;

/**
 *	Roll-UP Bill of Material 
 *	
 *  @author victor.perez@e-evolution.com, e-Evolution, S.C.
 *  @version $Id: RollupBillOfMaterial.java,v 1.1 2004/06/22 05:24:03 vpj-cd Exp $
 */
public class RollupBillOfMaterial extends SvrProcess
{
	/* Organization 		*/ 
	private int		 		p_AD_Org_ID = 0;
	/* Account Schema 	*/
	private int				p_C_AcctSchema_ID = 0;
	/* Cost Type			*/
	private int				p_M_CostType_ID = 0;
	/* Product 			*/
	private int				p_M_Product_ID = 0;
	/* Product Category  */
	private int				p_M_Product_Category_ID = 0;

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
			else if (name.equals("AD_Org_ID"))
				p_AD_Org_ID = para.getParameterAsInt();
			else if (name.equals(MAcctSchema.COLUMNNAME_C_AcctSchema_ID))  
				p_C_AcctSchema_ID = para.getParameterAsInt();
			else if (name.equals(MCostType.COLUMNNAME_M_CostType_ID))
				p_M_CostType_ID = para.getParameterAsInt();
			else if (name.equals(MProduct.COLUMNNAME_M_Product_ID))   
				p_M_Product_ID = para.getParameterAsInt();
			else if (name.equals(MProduct.COLUMNNAME_M_Product_Category_ID))
				p_M_Product_Category_ID = para.getParameterAsInt();
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
		int maxLowLevel = MPPMRP.getMaxLowLevel(getCtx(), get_TrxName());
		// Cost Roll-up for all levels
		for (int lowLevel = maxLowLevel; lowLevel >= 0; lowLevel--)
		{
			for (MProduct product : getProducts(lowLevel))
			{	  
				for (MCost cost : getCosts(product.get_ID()))
				{        
					log.info("Calculate Lower Cost for :"+ product.getName());
					MCostElement element = cost.getCostElement();
					log.info("Element Cost:"+ element.getName());
					
					// check if element cost is of Material Type 
					if (element.getCostElementType().equals(MCostElement.COSTELEMENTTYPE_Material))
					{                 
						BigDecimal Material = getCurrentCostPriceLL(MCostElement.COSTELEMENTTYPE_Material, product);     
						log.info("Material Cost Low Level:" + Material);
						cost.setCurrentCostPriceLL(Material);
						cost.saveEx();
					}
					else if (element.getCostElementType().equals(MCostElement.COSTELEMENTTYPE_Resource))
					{
						BigDecimal Labor = getCurrentCostPriceLL(MCostElement.COSTELEMENTTYPE_Resource, product);     
						log.info("Labor Cost Low Level:" + Labor);
						cost.setCurrentCostPriceLL(Labor);
						cost.saveEx();
					}
					else if (element.getCostElementType().equals(MCostElement.COSTELEMENTTYPE_BurdenMOverhead))
					{
						BigDecimal Burder = getCurrentCostPriceLL(MCostElement.COSTELEMENTTYPE_BurdenMOverhead, product);     
						log.info("Burden Cost Low Level:" + Burder);
						cost.setCurrentCostPriceLL(Burder);
						cost.saveEx();
					}
					else if (element.getCostElementType().equals(MCostElement.COSTELEMENTTYPE_Overhead))
					{
						BigDecimal Overhead = getCurrentCostPriceLL(MCostElement.COSTELEMENTTYPE_Overhead, product);     
						log.info("Overhead Cost Low Level:" + Overhead);
						cost.setCurrentCostPriceLL(Overhead);
						cost.saveEx();
					}
					else if (element.getCostElementType().equals(MCostElement.COSTELEMENTTYPE_OutsideProcessing))
					{
						BigDecimal Subcontract = getCurrentCostPriceLL(MCostElement.COSTELEMENTTYPE_OutsideProcessing, product);     
						log.info("Subcontract Cost Low Level:" + Subcontract);
						cost.setCurrentCostPriceLL(Subcontract);
						cost.saveEx();
					}
					else if (element.getCostElementType().equals(MCostElement.COSTELEMENTTYPE_Overhead))
					{
						BigDecimal Subcontract = getCurrentCostPriceLL(MCostElement.COSTELEMENTTYPE_Overhead, product);     
						log.info("Overhead Cost Low Level:" + Subcontract);
						cost.setCurrentCostPriceLL(Subcontract);
						cost.saveEx();
					}
					/* TODO Comment for future implementation
					else if (element.getCostElementType().equals(MCostElement.COSTELEMENTTYPE_Distribution))
					{

						BigDecimal Distribution = getCurrentCostPriceLL(MCostElement.COSTELEMENTTYPE_Distribution, M_Product_ID);     
						cost.setCurrentCostPriceLL(Distribution);
						cost.saveEx();
					}
					*/   	  
				} // for each Costs 
			} // for each Products 
		} // for each LLC
		return "@OK@";
	}


	/**
	 * get the sum Current Cost Price Level Low for this Cost Element Type
	 * @param CostElementType Cost Element Type (Material,Labor,Overhead,Burden)
	 * @param AD_Org_ID Organization
	 * @param MProduct Product
	 * @param M_CostType_ID Cost Type
	 * @param C_AcctSchema_ID Account Schema
	 * @return CurrentCostPriceLL Sum Current Cost Price Level Low for this Cost Element Type
	 */
	private BigDecimal getCurrentCostPriceLL(String CostElementType, MProduct product)
	{
		log.info("ElementType: "+CostElementType);
		BigDecimal costPriceLL = Env.ZERO;
		MPPProductPlanning pp = MPPProductPlanning.find(getCtx(), p_AD_Org_ID,
															0, // M_Warehouse_ID
															0, // S_Resource_ID
															product.getM_Product_ID(),
															get_TrxName());                 

		int PP_Product_BOM_ID = 0;
		if (pp != null)
		{
			PP_Product_BOM_ID = pp.getPP_Product_BOM_ID();
		}
		if (PP_Product_BOM_ID <= 0)
		{
			PP_Product_BOM_ID = MPPProductBOM.getBOMSearchKey(product);
		}
		if (PP_Product_BOM_ID <= 0)
		{
			return Env.ZERO;
		}
		
		MPPProductBOM bom = MPPProductBOM.get(getCtx(), PP_Product_BOM_ID);
		for (MPPProductBOMLine bomline : bom.getLines())
		{
			// get the rate for this resource     
			for (MCost cost : getCosts(bomline.getM_Product_ID()))
			{                 
				MCostElement element = cost.getCostElement();
				// check if current cost element type is specified cost element type
				if (element.getCostElementType().equals(CostElementType))
				{
					BigDecimal qtyPercentage = bomline.getQtyBatch().divide(Env.ONEHUNDRED, 8, BigDecimal.ROUND_UP);
					BigDecimal qtyBOM = bomline.getQtyBOM(); 
					BigDecimal scrapDec = bomline.getScrap().divide(Env.ONEHUNDRED, 4, BigDecimal.ROUND_UP);
					BigDecimal qtyTotal = Env.ZERO;
					if (bomline.isQtyPercentage())
					{
						qtyTotal =  qtyPercentage.divide(Env.ONE.subtract(scrapDec), 4, BigDecimal.ROUND_HALF_UP);
					}
					else
					{
						qtyTotal =  qtyBOM.divide(Env.ONE.subtract(scrapDec), 4, BigDecimal.ROUND_HALF_UP);
					}

					BigDecimal costPrice = cost.getCurrentCostPrice().add(cost.getCurrentCostPriceLL());
					costPriceLL = costPriceLL.add(costPrice.multiply(qtyTotal));
					log.info("Cost Element:"+element.getName()
								+ ", Total Cost Element: " +   costPriceLL
								+ ", QtyPercentage: " + qtyPercentage
								+ ", QtyBOM: " + qtyBOM);
				}
			} // for each cost
		} // for each BOM line  
		//
		// Try find planning Data for this product and get % Yield to calculate cost
		if (pp != null)
		{            
			int yield = pp.getYield();
			if(yield != 0)
			{
				BigDecimal decimalYield = new BigDecimal(yield / 100);
				costPriceLL = costPriceLL.divide(decimalYield, 4 ,BigDecimal.ROUND_HALF_UP);
			}
		}                 
		return costPriceLL;     
	}

	private MCost[] getCosts(int product_id)
	{
		return MCost.getCosts(getCtx(), getAD_Client_ID(), p_AD_Org_ID, product_id,
				p_M_CostType_ID, p_C_AcctSchema_ID , get_TrxName());
	}
	
	private List<MProduct> getProducts(int lowLevel)
	{
		List<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer("AD_Client_ID=? AND LowLevel=? AND ProductType=?");
		params.add(getAD_Client_ID());
		params.add(lowLevel);
		params.add(MProduct.PRODUCTTYPE_Item);

		if (p_M_Product_ID > 0)
		{  
			whereClause.append(" AND M_Product_ID=?");
			params.add(p_M_Product_ID);
		}		
		else if (p_M_Product_Category_ID > 0)
		{
			whereClause.append(" AND M_Product_Category_ID=?");
			params.add(p_M_Product_Category_ID);
		}	

		return new Query(getCtx(),MProduct.Table_Name, whereClause.toString(), get_TrxName())
					.setParameters(params)
					.list();
		
	}
}
