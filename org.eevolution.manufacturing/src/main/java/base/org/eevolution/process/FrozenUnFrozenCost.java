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
 * Copyright (C) 2003-2016 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.process;


import org.adempiere.engine.CostDimension;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCost;
import org.compiere.model.MCostElement;
import org.compiere.model.MCostType;
import org.compiere.model.MProduct;
import org.compiere.model.Query;
import org.compiere.util.Msg;
import org.compiere.util.Trx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/** Generated Process for (Frozen/UnFrozen Cost)
 *  @author victor.perez@e-evolution.com , www.e-evolution.com
 *  @version Release 3.9.0
 */
public class FrozenUnFrozenCost extends FrozenUnFrozenCostAbstract
{
	@Override
	protected void prepare()
	{
		super.prepare();
	}

	@Override
	protected String doIt() throws Exception
	{
		//Get account schema
		MAcctSchema accountSchema = MAcctSchema.get(getCtx(), getAccountingSchemaId());
		//Get cost type
		MCostType costType = MCostType.get(getCtx() , getCostTypeId());
		//Get cost element to process
		final List<MCostElement>  costElements = getCostElementId() > 0 ?
				Arrays.asList(MCostElement.get(getCtx(), getCostElementId())) :
				MCostElement.getCostElement(getCtx(), get_TrxName());
		//Iterate cost element
		costElements.stream()
				.filter(costElement -> costElement != null)
				.forEach(costElement -> {
					AtomicInteger records = new AtomicInteger(0);
					//Iterate product based on parameters
					Arrays.stream(getProductIds())
							.filter(productId -> productId > 0)
							.forEach(productId -> {
								MProduct product = MProduct.get(getCtx(), productId);
								//Get cost dimension for frozen / unfrozen
								final CostDimension costDimension = new CostDimension(
										product,
										accountSchema,
										costType.getM_CostType_ID(),
										getOrganizationId(),
										getWarehouseId(),
										0,
										costElement.getM_CostElement_ID());
								//Execute transaction to update cost dimension
								Trx.run(trxName -> {
									final List<MCost> costs = costDimension.toQuery(MCost.class, trxName).list();
									//Iterate cost dimension
									costs.stream()
											.filter(cost -> cost != null)
											.forEach(cost -> {
												cost.setIsCostFrozen(isCostFrozen());
												cost.saveEx();
												records.updateAndGet(record -> record + 1);
											});
								});
							});
					String message =  "@M_CostElement_ID@ " + costElement.getName() +  " @Records@ " + records.get() + " @IsCostFrozen@ = " + isCostFrozen();
					addLog(Msg.parseTranslation(getCtx() , message));
				});

		return "@OK@";
	}

	/**
	 * get Products ids
	 * @return
	 */
	private int[] getProductIds() {
		StringBuilder whereClause = new StringBuilder("1=1 ");
		List<Object> parameters = new ArrayList();
		if (getProductId() > 0) {
			whereClause.append(MCost.COLUMNNAME_M_Product_ID).append("=? ");
			parameters.add(getProcessId());
		}
		if (getProductCategoryId() > 0) {
			whereClause.append(" AND ").append(MProduct.COLUMNNAME_M_Product_Category_ID).append("=? ");
			parameters.add(getProductCategoryId());
		}
		if (getProductClassId() > 0) {
			whereClause.append(" AND ").append(MProduct.COLUMNNAME_M_Product_Class_ID).append("=?");
			parameters.add(getProductClassId());
		}
		if (getProductGroupId() > 0) {
			whereClause.append(" AND ").append(MProduct.COLUMNNAME_M_Product_Group_ID).append("=?");
			parameters.add(getProductGroupId());
		}
		if (getProductClassificationId() > 0) {
			whereClause.append(" AND ").append(MProduct.COLUMNNAME_M_Product_Classification_ID).append("=?");
			parameters.add(getProductClassificationId());
		}
		return new Query(getCtx(), MProduct.Table_Name, whereClause.toString(), get_TrxName())
				.setClient_ID()
				.setParameters(parameters)
				.getIDs();
	}
}