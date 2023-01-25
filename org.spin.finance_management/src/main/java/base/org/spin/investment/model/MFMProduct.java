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
 * Copyright (C) 2003-2016 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/
package org.spin.investment.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.adempiere.core.domains.models.X_FM_Product;
import org.compiere.model.Query;
import org.compiere.util.CCache;
/**
 * Financial Management
 *
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 *      <li> FR [ 1583 ] New Definition for loan
 *		@see https://github.com/adempiere/adempiere/issues/1583
 */
public class MFMProduct extends X_FM_Product {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4496357377702190439L;

	public MFMProduct(Properties ctx, int FM_Product_ID, String trxName) {
		super(ctx, FM_Product_ID, trxName);
	}

	public MFMProduct(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Get Applicability without table
	 * @param eventType
	 * @return
	 */
	public List<MFMFunctionalApplicability> getApplicability(String eventType) {
		MFMProductCategory category = MFMProductCategory.getById(getCtx(), getFM_ProductCategory_ID(), get_TrxName());
		return category.getApplicability(eventType);
	}

	/**
	 * Get from Table and event model validator
	 * @param tableName
	 * @param eventModelValidator
	 * @return
	 */
	public List<MFMFunctionalApplicability> getApplicability(String tableName, String eventModelValidator) {
		MFMProductCategory category = MFMProductCategory.getById(getCtx(), getFM_ProductCategory_ID(), get_TrxName());
		return category.getApplicability(tableName, eventModelValidator);
	}
	
	/** Static Cache */
	private static CCache<Integer, MFMProduct> financialProductCacheIds = new CCache<Integer, MFMProduct>(Table_Name, 30);

	/**
	 * Get/Load Functional Product [CACHED]
	 * @param ctx context
	 * @param financialProductId
	 * @return financial product or null
	 */
	@Deprecated
	public static MFMProduct getById(Properties ctx, int financialProductId) {
		return getById(ctx, financialProductId, null);
	}
	
	/**
	 * Get/Load Functional Product [CACHED]
	 * @param ctx context
	 * @param financialProductId
	 * @param transactionName
	 * @return financial product or null
	 */
	public static MFMProduct getById(Properties ctx, int financialProductId, String transactionName) {
		if (financialProductId <= 0)
			return null;

		MFMProduct financialProduct = financialProductCacheIds.get(financialProductId);
		if (financialProduct != null && financialProduct.get_ID() > 0)
			return financialProduct;

		financialProduct = new Query(ctx , Table_Name , COLUMNNAME_FM_Product_ID + "=?" , transactionName)
				.setClient_ID()
				.setParameters(financialProductId)
				.first();
		if (financialProduct != null && financialProduct.get_ID() > 0) {
			financialProductCacheIds.put(financialProduct.get_ID(), financialProduct);
		}
		return financialProduct;
	}
	
	@Override
	protected boolean afterSave(boolean newRecord, boolean success) {
		financialProductCacheIds.clear();
		return super.afterSave(newRecord, success);
	}
	
	@Override
	protected boolean afterDelete(boolean success) {
		financialProductCacheIds.clear();
		return super.afterDelete(success);
	}

	@Override
	public String toString() {
		return "MFMProduct [getM_Product_ID()=" + getM_Product_ID() + ", getName()=" + getName() + ", getValue()="
				+ getValue() + "]";
	}
}
