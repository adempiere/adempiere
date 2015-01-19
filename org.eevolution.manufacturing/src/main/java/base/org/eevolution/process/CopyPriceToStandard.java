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
import java.util.Collection;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.engine.CostDimension;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MConversionRate;
import org.compiere.model.MCost;
import org.compiere.model.MCostElement;
import org.compiere.model.MPriceListVersion;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPrice;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;


/**
 *	CopyPriceToStandard 
 *	
 *  @author Victor Perez, e-Evolution, S.C.
 *  @version $Id: CopyPriceToStandard.java,v 1.1 2004/06/22 05:24:03 vpj-cd Exp $
 */
public class CopyPriceToStandard extends SvrProcess
{
	private int p_AD_Org_ID = 0;
	private int p_C_AcctSchema_ID = 0;
	private int p_M_CostType_ID = 0;
	private int p_M_CostElement_ID = 0;
	private int p_M_PriceList_Version_ID =0;

	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();

			if (para[i].getParameter() == null)
				;
			else if (name.equals("M_CostType_ID"))
			{    
				p_M_CostType_ID = ((BigDecimal)para[i].getParameter()).intValue();
			}
			else if (name.equals("AD_Org_ID"))
			{    
				p_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();
			}
			else if (name.equals("C_AcctSchema_ID"))
			{    
				p_C_AcctSchema_ID = ((BigDecimal)para[i].getParameter()).intValue();
			}           
			else if (name.equals("M_CostElement_ID"))
			{    
				p_M_CostElement_ID = ((BigDecimal)para[i].getParameter()).intValue();
			}
			else if (name.equals("M_PriceList_Version_ID"))
			{    
				p_M_PriceList_Version_ID = ((BigDecimal)para[i].getParameter()).intValue();
			}
			else
			{
				log.log(Level.SEVERE,"prepare - Unknown Parameter: " + name);
			}
		}
	}
	
	protected String doIt() throws Exception                
	{
		MAcctSchema as = MAcctSchema.get(getCtx(), p_C_AcctSchema_ID);
		MCostElement element = MCostElement.get(getCtx(), p_M_CostElement_ID);
		if (!MCostElement.COSTELEMENTTYPE_Material.equals(element.getCostElementType()))
		{
			throw new AdempiereException("Only Material Cost Elements are allowed");
		}
		
		int count_updated = 0;
		
		MPriceListVersion plv = new MPriceListVersion(getCtx(), p_M_PriceList_Version_ID, get_TrxName());
		for (final MProductPrice pprice : plv.getProductPrice(" AND "+MProductPrice.COLUMNNAME_PriceStd+"<>0"))
		{
			BigDecimal price = pprice.getPriceStd();
			int C_Currency_ID = plv.getPriceList().getC_Currency_ID();
			if (C_Currency_ID != as.getC_Currency_ID())
			{                     	
				price = MConversionRate.convert(getCtx(), pprice.getPriceStd(),
								C_Currency_ID, as.getC_Currency_ID(),
								getAD_Client_ID(), p_AD_Org_ID);                     	
			}
			MProduct product = MProduct.get(getCtx(), pprice.getM_Product_ID());
			CostDimension d = new CostDimension(product, as, p_M_CostType_ID, p_AD_Org_ID, 0, 0, p_M_CostElement_ID);
			Collection<MCost> costs = d.toQuery(MCost.class, get_TrxName()).list(); 
			for (MCost cost : costs)
			{
				if (cost.getM_CostElement_ID() == element.get_ID())
				{
					cost.setFutureCostPrice(price);
					cost.saveEx();
					count_updated++;
					break;
				}
			}                                                                      
		}
		return "@Updated@ #"+count_updated;
	}
}	
