/*************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                              *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, C.A.                      *
 * Contributor(s): Yamel Senih ysenih@erpya.com                                      *
 * This program is free software: you can redistribute it and/or modify              *
 * it under the terms of the GNU General Public License as published by              *
 * the Free Software Foundation, either version 3 of the License, or                 *
 * (at your option) any later version.                                               *
 * This program is distributed in the hope that it will be useful,                   *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of                    *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                     *
 * GNU General Public License for more details.                                      *
 * You should have received a copy of the GNU General Public License                 *
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.            *
 ************************************************************************************/
package org.spin.store.model;

import org.adempiere.core.domains.models.I_M_Product;
import org.adempiere.core.domains.models.X_C_PaymentMethod;
import org.compiere.model.MStore;
import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.Env;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * 	Implementation for Web Store Product Group
 * 	@author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class MCPaymentMethod extends X_C_PaymentMethod {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5448641333159848635L;

	public MCPaymentMethod(Properties ctx, int terminalId, String trxName) {
		super(ctx, terminalId, trxName);
	}
	
	public MCPaymentMethod(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/** Static Cache */
	private static CCache<Integer, MCPaymentMethod> paymentMethodCacheIds = new CCache<Integer, MCPaymentMethod>(Table_Name, 30);
	/** Static Cache */
	private static CCache<String, MCPaymentMethod> paymentMethodGroupCodes = new CCache<String, MCPaymentMethod>(Table_Name, 30);
	
	/**
	 * Get/Load Payment Method for Web Store [CACHED]
	 * @param ctx context
	 * @param terminalId
	 * @param trxName
	 * @return activity or null
	 */
	public static MCPaymentMethod getById(Properties ctx, int terminalId, String trxName) {
		if (terminalId <= 0)
			return null;

		MCPaymentMethod paymentMethod = paymentMethodCacheIds.get(terminalId);
		if (paymentMethod != null && paymentMethod.get_ID() > 0)
			return paymentMethod;

		paymentMethod = new Query(ctx , Table_Name , COLUMNNAME_C_PaymentMethod_ID + "=?" , trxName)
				.setParameters(terminalId)
				.first();
		if (paymentMethod != null && paymentMethod.get_ID() > 0) {
			int clientId = Env.getAD_Client_ID(ctx);
			String key = clientId + "#" + paymentMethod.getValue();
			paymentMethodGroupCodes.put(key, paymentMethod);
			paymentMethodCacheIds.put(paymentMethod.get_ID(), paymentMethod);
		}
		return paymentMethod;
	}

	/**
	 * get Payment Method for Web Store By Value Code [CACHED]
	 * @param ctx
	 * @param value
	 * @param trxName
	 * @return
	 */
	public static MCPaymentMethod getByValue(Properties ctx, String value, String trxName) {
		if (value == null)
			return null;
		if (paymentMethodGroupCodes.size() == 0 )
			getAll(ctx, true, trxName);

		String key = value;
		MCPaymentMethod paymentMethod = paymentMethodGroupCodes.get(key);
		if (paymentMethod != null && paymentMethod.get_ID() > 0 )
			return paymentMethod;

		paymentMethod =  new Query(ctx, Table_Name , COLUMNNAME_Value +  "=?", trxName)
				.setParameters(value)
				.first();

		if (paymentMethod != null && paymentMethod.get_ID() > 0) {
			paymentMethodGroupCodes.put(key, paymentMethod);
			paymentMethodCacheIds.put(paymentMethod.get_ID() , paymentMethod);
		}
		return paymentMethod;
	}

	/**
	 * Get All Payment Methods
	 * @param ctx
	 * @param resetCache
	 * @param trxName
	 * @return
	 */
	public static List<MCPaymentMethod> getAll(Properties ctx, boolean resetCache, String trxName) {
		List<MCPaymentMethod> productGroupList;
		if (resetCache || paymentMethodCacheIds.size() > 0 ) {
			productGroupList = new Query(Env.getCtx(), Table_Name, null , trxName)
					.setClient_ID()
					.setOrderBy(COLUMNNAME_Name)
					.list();
			productGroupList.stream().forEach(paymentMethod -> {
				String key = paymentMethod.getValue();
				paymentMethodCacheIds.put(paymentMethod.getC_PaymentMethod_ID(), paymentMethod);
				paymentMethodGroupCodes.put(key, paymentMethod);
			});
			return productGroupList;
		}
		productGroupList = paymentMethodCacheIds.entrySet().stream()
				.map(applicationType -> applicationType.getValue())
				.collect(Collectors.toList());
		return  productGroupList;
	}
	
	/**
	 * Get Payment Methods allowed for Store
	 * @param ctx
	 * @param storeId
	 * @param trxName
	 * @return
	 */
	public static List<MCPaymentMethod> getOfStore(Properties ctx, int storeId, String trxName) {
		return new Query(Env.getCtx(), Table_Name, "EXISTS(SELECT 1 FROM C_PaymentMethodAllocation a "
				+ "WHERE a.C_PaymentMethod_ID = C_PaymentMethod.C_PaymentMethod_ID "
				+ "AND a.W_Store_ID = ?)" , trxName)
				.setParameters(storeId)
				.setClient_ID()
				.list();
	}
	
	/**
	 * Get Stores from Payment Method
	 * @return
	 */
	public List<MStore> getStoresList() {
		return new Query(Env.getCtx(), I_M_Product.Table_Name, "EXISTS(SELECT 1 FROM C_PaymentMethodAllocation a "
				+ "WHERE a.W_Store_ID = W_Store.W_Store_ID "
				+ "AND a.W_ProductCategory_ID = ?)" , get_TrxName())
				.setParameters(getC_PaymentMethod_ID())
				.setClient_ID()
				.list();
	}

	@Override
	public String toString() {
		return "MWPaymentMethod [getName()=" + getName() + ", getValue()=" + getValue() + ", getC_PaymentMethod_ID()="
				+ getC_PaymentMethod_ID() + "]";
	}
}
