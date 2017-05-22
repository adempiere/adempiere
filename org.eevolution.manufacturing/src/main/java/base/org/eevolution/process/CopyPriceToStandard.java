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
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
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
 */
public class CopyPriceToStandard extends CopyPriceToStandardAbstract
{


	protected void prepare()
	{
		super.prepare();
	}
	
	protected String doIt() throws Exception                
	{
		MAcctSchema accountSchema = MAcctSchema.get(getCtx(), getAccountingSchemaId());
		MCostElement costElement = MCostElement.get(getCtx(), getCostElementId());
		if (!MCostElement.COSTELEMENTTYPE_Material.equals(costElement.getCostElementType()))
			throw new AdempiereException("Only Material Cost Elements are allowed");
		
		AtomicInteger countUpdated = new AtomicInteger(0);
		MPriceListVersion priceListVersion = new MPriceListVersion(getCtx(), getPriceListVersionId(), get_TrxName());
		Arrays.stream(priceListVersion.getProductPrice(" AND " + MProductPrice.COLUMNNAME_PriceStd + " <> 0")).forEach(productPrice -> {
			final BigDecimal price;
			int currencyId = priceListVersion.getPriceList().getC_Currency_ID();
			if (currencyId != accountSchema.getC_Currency_ID())
			{                     	
				price = MConversionRate.convert(getCtx(), productPrice.getPriceStd(),
								currencyId, accountSchema.getC_Currency_ID(),
								getAD_Client_ID(), getOrganizationId());
			} else
				price = productPrice.getPriceStd();

			MProduct product = MProduct.get(getCtx(), productPrice.getM_Product_ID());
			CostDimension costDimension = new CostDimension(product, accountSchema, getCostTypeId(), getOrganizationId(), 0, 0, getCostElementId());
			List<MCost> costs = costDimension.toQuery(MCost.class, get_TrxName()).list();
			costs.stream()
					.filter( cost -> cost != null && cost.getM_CostElement_ID() == costElement.get_ID())
					.findFirst().ifPresent( cost -> {
							cost.setFutureCostPrice(price);
							cost.saveEx();
							countUpdated.getAndUpdate(count -> count + 1);
					});
			});
		return "@Updated@ # "+countUpdated;
	}
}	
