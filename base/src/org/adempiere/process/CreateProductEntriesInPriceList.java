/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

package org.adempiere.process;

import org.compiere.model.MProductPrice;
import org.compiere.util.Env;

import java.math.BigDecimal;
import java.util.Optional;

/** Generated Process for (CreateProductEntriesInPriceList)
 *  @author Systemhaus Westfalia
 *  @version Release 3.9.1
 */
public class CreateProductEntriesInPriceList extends CreateProductEntriesInPriceListAbstract
{
	@Override
	protected void prepare()
	{
		super.prepare();
	}

	@Override
	protected String doIt() throws Exception
	{
			getSelectionKeys().stream().forEach( key -> {
				Optional<MProductPrice> productPriceOptional =
						Optional.ofNullable(MProductPrice.get(getCtx(), getRecord_ID(), key, get_TrxName()) );
				MProductPrice productPrice = productPriceOptional
						.orElseGet(() -> new MProductPrice(getCtx(), getRecord_ID(), key, get_TrxName()));
				productPrice.setAD_Org_ID(productPrice.getM_PriceList_Version().getAD_Org_ID());
				BigDecimal priceList = getSelectionAsBigDecimal(key, "PP_PriceList");
				BigDecimal priceStd = getSelectionAsBigDecimal(key, "PP_PriceStd");
				BigDecimal priceLimit = getSelectionAsBigDecimal(key, "PP_PriceLimit");

				productPrice.setPrices(priceList ==null?Env.ZERO:priceList,
				priceStd ==null?Env.ZERO:priceStd,
				priceLimit==null?Env.ZERO:priceLimit);
				productPrice.saveEx();
			});   //  for all rows
		return "";
	}
}