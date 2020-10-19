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
package org.spin.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.compiere.model.I_M_Product;
import org.compiere.model.MProduct;
import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.Env;

/**
 * 	Implementation for Web Store Product Group
 * 	@author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class MWProductGroup extends X_W_ProductGroup {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5448641333159848635L;

	public MWProductGroup(Properties ctx, int terminalId, String trxName) {
		super(ctx, terminalId, trxName);
	}
	
	public MWProductGroup(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/** Static Cache */
	private static CCache<Integer, MWProductGroup> productGroupCacheIds = new CCache<Integer, MWProductGroup>(Table_Name, 30);
	/** Static Cache */
	private static CCache<String, MWProductGroup> productGroupCodes = new CCache<String, MWProductGroup>(Table_Name, 30);
	
	/**
	 * Get/Load Product Group for Web Store [CACHED]
	 * @param ctx context
	 * @param terminalId
	 * @param trxName
	 * @return activity or null
	 */
	public static MWProductGroup getById(Properties ctx, int terminalId, String trxName) {
		if (terminalId <= 0)
			return null;

		MWProductGroup productGroup = productGroupCacheIds.get(terminalId);
		if (productGroup != null && productGroup.get_ID() > 0)
			return productGroup;

		productGroup = new Query(ctx , Table_Name , COLUMNNAME_W_ProductGroup_ID + "=?" , trxName)
				.setParameters(terminalId)
				.first();
		if (productGroup != null && productGroup.get_ID() > 0) {
			int clientId = Env.getAD_Client_ID(ctx);
			String key = clientId + "#" + productGroup.getValue();
			productGroupCodes.put(key, productGroup);
			productGroupCacheIds.put(productGroup.get_ID(), productGroup);
		}
		return productGroup;
	}

	/**
	 * get Product Group for Web Store By Value Code [CACHED]
	 * @param ctx
	 * @param value
	 * @param trxName
	 * @return
	 */
	public static MWProductGroup getByValue(Properties ctx, String value, String trxName) {
		if (value == null)
			return null;
		if (productGroupCodes.size() == 0 )
			getAll(ctx, true, trxName);

		String key = value;
		MWProductGroup productGroup = productGroupCodes.get(key);
		if (productGroup != null && productGroup.get_ID() > 0 )
			return productGroup;

		productGroup =  new Query(ctx, Table_Name , COLUMNNAME_Value +  "=?", trxName)
				.setParameters(value)
				.first();

		if (productGroup != null && productGroup.get_ID() > 0) {
			productGroupCodes.put(key, productGroup);
			productGroupCacheIds.put(productGroup.get_ID() , productGroup);
		}
		return productGroup;
	}

	/**
	 * Get All Product Group for Web Store
	 * @param ctx
	 * @param resetCache
	 * @param trxName
	 * @return
	 */
	public static List<MWProductGroup> getAll(Properties ctx, boolean resetCache, String trxName) {
		List<MWProductGroup> productGroupList;
		if (resetCache || productGroupCacheIds.size() > 0 ) {
			productGroupList = new Query(Env.getCtx(), Table_Name, null , trxName)
					.setClient_ID()
					.setOrderBy(COLUMNNAME_Name)
					.list();
			productGroupList.stream().forEach(airport -> {
				String key = airport.getValue();
				productGroupCacheIds.put(airport.getW_ProductGroup_ID(), airport);
				productGroupCodes.put(key, airport);
			});
			return productGroupList;
		}
		productGroupList = productGroupCacheIds.entrySet().stream()
				.map(applicationType -> applicationType.getValue())
				.collect(Collectors.toList());
		return  productGroupList;
	}
	
	/**
	 * Get Group of products
	 * @param ctx
	 * @param productId
	 * @param trxName
	 * @return
	 */
	public static List<MWProductGroup> getOfProduct(Properties ctx, int productId, String trxName) {
		return new Query(Env.getCtx(), Table_Name, "EXISTS(SELECT 1 FROM W_ProductGroupAllocation a "
				+ "WHERE a.W_ProductGroup_ID = W_ProductGroup.W_ProductGroup_ID "
				+ "AND a.M_Product_ID = ?)" , trxName)
				.setParameters(productId)
				.setClient_ID()
				.list();
	}
	
	/**
	 * Get Child list
	 * @return
	 */
	public List<MWProductGroup> getChildList() {
		return new Query(Env.getCtx(), Table_Name, COLUMNNAME_W_ProductGroup_Parent_ID + " = ?" , get_TrxName())
				.setParameters(getW_ProductGroup_ID())
				.setClient_ID()
				.list();
	}
	
	/**
	 * Get products of group
	 * @return
	 */
	public List<MProduct> getProductsList() {
		return new Query(Env.getCtx(), I_M_Product.Table_Name, "EXISTS(SELECT 1 FROM W_ProductGroupAllocation a "
				+ "WHERE a.M_Product_ID = M_Product.M_Product_ID "
				+ "AND a.W_ProductGroup_ID = ?)" , get_TrxName())
				.setParameters(getW_ProductGroup_ID())
				.setClient_ID()
				.list();
	}
	
	/**
	 * Get products counts
	 * @return
	 */
	public int getProductCount() {
		return new Query(Env.getCtx(), I_M_Product.Table_Name, "EXISTS(SELECT 1 FROM W_ProductGroupAllocation a "
				+ "WHERE a.M_Product_ID = M_Product.M_Product_ID "
				+ "AND a.W_ProductGroup_ID = ?)" , get_TrxName())
				.setParameters(getW_ProductGroup_ID())
				.setClient_ID()
				.count();
	}

	@Override
	public String toString() {
		return "MWProductGroup [getName()=" + getName() + ", getUUID()=" + getUUID() + ", getValue()=" + getValue()
				+ ", getW_ProductGroup_ID()=" + getW_ProductGroup_ID() + "]";
	}
}
