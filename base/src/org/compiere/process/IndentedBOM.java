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
 *                 Teo Sarca, www.arhipac.ro                                  *
 *****************************************************************************/
package org.compiere.process;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.FillMandatoryException;
import org.adempiere.util.StringUtils;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCost;
import org.compiere.model.MCostElement;
import org.compiere.model.MProduct;
import org.compiere.model.MProductBOM;
import org.compiere.model.Query;
import org.compiere.model.X_T_BOM_Indented;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.eevolution.model.MPPProductBOMLine;

/**
 * Cost Multi-Level BOM & Formula Review
 * 
 * @author victor.perez@e-evolution.com
 * @author Teo Sarca, www.arhipac.ro
 * 
 * @author pbowden@adaxa.com modified for manufacturing light
 * 
 */
public class IndentedBOM extends SvrProcess
{
	//
	private int p_AD_Org_ID = 0;
	private int p_C_AcctSchema_ID = 0;
	private int p_M_Product_ID = 0;
	private int p_M_CostElement_ID = 0;
	//
	private int m_LevelNo = 0;
	private int m_SeqNo = 0;
	private MAcctSchema m_as = null;

	protected void prepare()
	{
		for (ProcessInfoParameter para : getParameter())
		{
			String name = para.getParameterName();
			if (para.getParameter() == null)
				;
			else if (name.equals(MCost.COLUMNNAME_C_AcctSchema_ID))
			{
				p_C_AcctSchema_ID= para.getParameterAsInt();
				m_as = MAcctSchema.get(getCtx(), p_C_AcctSchema_ID);
			}
			else if (name.equals(MCost.COLUMNNAME_M_CostElement_ID))
			{
				p_M_CostElement_ID = para.getParameterAsInt();
			}
			else if (name.equals(MCost.COLUMNNAME_M_Product_ID))
				p_M_Product_ID = para.getParameterAsInt();
			else
				log.log(Level.SEVERE, "prepare - Unknown Parameter: " + name);
		}
	} // prepare

	/**
	 * Perform process.
	 * 
	 * @return Message (clear text)
	 * @throws Exception
	 *             if not successful
	 */
	protected String doIt() throws Exception
	{
		if (p_M_Product_ID == 0)
		{
			throw new FillMandatoryException("M_Product_ID");
		}
		explodeProduct(p_M_Product_ID, Env.ONE, Env.ONE); 
		//
		return "";
	} // doIt

	/**
	 * Generate an Explosion for this product
	 * @param product
	 * @param isComponent component / header
	 */
	private llCost explodeProduct(int M_Product_ID, BigDecimal qty, BigDecimal accumQty)
	{
		MProduct product = MProduct.get(getCtx(), M_Product_ID);

		X_T_BOM_Indented tboml = new X_T_BOM_Indented(getCtx(), 0, get_TrxName());

		tboml.setAD_Org_ID(p_AD_Org_ID);
		tboml.setC_AcctSchema_ID(p_C_AcctSchema_ID);
		tboml.setAD_PInstance_ID(getAD_PInstance_ID());
		tboml.setM_CostElement_ID(p_M_CostElement_ID);
		tboml.setSel_Product_ID(product.get_ID());
		tboml.setM_Product_ID(p_M_Product_ID);
		tboml.setQtyBOM(qty);
		tboml.setQty(accumQty);
		//
		tboml.setSeqNo(m_SeqNo);
		tboml.setLevelNo(m_LevelNo);
		tboml.setLevels( (m_LevelNo > 0 ? ":" : "") +  StringUtils.repeat("    ", m_LevelNo) +" " + product.getValue());
		//
		// Set Costs:
		MCost cost = MCost.get(product, 0, m_as, p_AD_Org_ID, 0, p_M_CostElement_ID, get_TrxName());
		tboml.setCurrentCostPrice(cost.getCurrentCostPrice());
		tboml.setCost(cost.getCurrentCostPrice().multiply(accumQty));
		tboml.setFutureCostPrice(cost.getFutureCostPrice());
		tboml.setCostFuture(cost.getFutureCostPrice().multiply(accumQty));
		m_SeqNo++;

		BigDecimal llCost = Env.ZERO;
		BigDecimal llFutureCost = Env.ZERO;
		List<MPPProductBOMLine> list = getBOMs(product);
		for (MPPProductBOMLine bom : list)
		{
			m_LevelNo++;
			llCost ll = explodeProduct(bom.getM_Product_ID(), bom.getQtyBOM(), accumQty.multiply(bom.getQtyBOM()));
			llCost = llCost.add(ll.currentCost.multiply(accumQty.multiply(bom.getQtyBOM())));
			llFutureCost = llFutureCost.add(ll.futureCost.multiply(accumQty.multiply(bom.getQtyBOM())));
			m_LevelNo--;
		}

		llCost retVal = new llCost();
		if (list.size() == 0 )
		{
			tboml.setCurrentCostPriceLL(cost.getCurrentCostPrice());
			tboml.setFutureCostPriceLL(cost.getFutureCostPrice());

			//
			retVal.currentCost = cost.getCurrentCostPrice();
			retVal.futureCost = cost.getFutureCostPrice();
		}
		else 
		{
			tboml.setCurrentCostPriceLL(llCost);
			tboml.setFutureCostPriceLL(llFutureCost);

			//
			retVal.currentCost = llCost;
			retVal.futureCost = llFutureCost;
		}

		tboml.saveEx();
		return retVal;
		
	}

	/**
	 * Get BOMs for given product
	 * @param product
	 * @param isComponent
	 * @return list of MProductBOM
	 */
	private List<MPPProductBOMLine> getBOMs(MProduct product)
	{
		
		log.severe(" PRODUCT NAME = " + product.getName() ) ;
		
		StringBuffer whereClause = new StringBuffer();
		whereClause.append(MPPProductBOMLine.COLUMNNAME_PP_Product_BOM_ID);
		whereClause.append(" IN ( SELECT PP_Product_BOM_ID FROM PP_Product_BOM ");
		whereClause.append(" WHERE M_Product_ID = " + product.get_ID() + " ) ");
		
		List<MPPProductBOMLine> list = new Query(getCtx(), MPPProductBOMLine.Table_Name, whereClause.toString(), null)
									.setOnlyActiveRecords(true)
									.setOrderBy(MPPProductBOMLine.COLUMNNAME_Line)
									.list();
		return list;
	}
	
	private class llCost {
		BigDecimal currentCost = Env.ZERO;
		BigDecimal futureCost = Env.ZERO;
	}
	
}
