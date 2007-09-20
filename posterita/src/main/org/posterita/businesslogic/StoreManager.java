/**
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *  
 * 
 *
 */

/**
	@author ashley
 */

package org.posterita.businesslogic;

import java.util.Properties;

import org.compiere.model.MStore;
import org.compiere.util.Env;

import org.posterita.exceptions.DefaultStoreException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.StoreException;
import org.posterita.model.UDIMStore;

public class StoreManager
{

	public static int getStoreIdByName(Properties ctx, String storeName, String trxName) throws StoreException
	{
		int adClientId = Env.getAD_Client_ID(ctx);
		int adOrgId = Env.getAD_Org_ID(ctx);
		
		String whereClause = "AD_Client_ID=" + adClientId + " and AD_Org_ID=" + adOrgId + " and Name='" + storeName + "'";
		
		int storeIds[] = MStore.getAllIDs(MStore.Table_Name, whereClause, trxName);
		
		if(storeIds.length == 0)
			throw new StoreException("Could not find Store with name: " + storeName);
		
		else if(storeIds.length > 1)
			throw new StoreException(storeIds.length + " Stores found with name: " + storeName);
		
		else
			return storeIds[0];
	}
	
	public static MStore createStore(Properties ctx, String storeName, String webContext, int orgId, 
			int priceListId, int salesRepId, int warehouseId, String webParam6, String hostUrl, String trxName) throws OperationException
	{
		if(storeName == null || storeName.length() == 0)
			throw new OperationException("Store name cannot be null");
		if(webContext == null || webContext.length() == 0)
			throw new OperationException("Web context cannot be null");
		
		MStore store = new MStore(ctx, 0, trxName);
		store.setAD_Org_ID(orgId);
		store.setName(storeName);
		store.setWebContext(webContext);
		store.setM_PriceList_ID(priceListId);
		store.setSalesRep_ID(salesRepId);
		store.setM_Warehouse_ID(warehouseId);
		store.setWebParam6(webParam6);
		store.setURL(hostUrl);
		
		UDIMStore udiStore = new UDIMStore(store);
		udiStore.save();
		
		return store;
	}
	
	public static boolean isStorePresent(String webContext)
	{
		String whereClause = "WebContext='" + webContext + "'";
		int storeIds[] = MStore.getAllIDs(MStore.Table_Name, whereClause, null);
		
		return (storeIds.length > 0);
	}
	
	public static MStore getDefaultStore(Properties ctx) throws DefaultStoreException
	{
		String whereClause = "IsDefault='Y'";
		int storeIds[] = MStore.getAllIDs(MStore.Table_Name, whereClause, null);
		
		if(storeIds.length == 0)
			throw new DefaultStoreException("No Default store defined!!!");
		else if(storeIds.length > 1)
			throw new DefaultStoreException("More than 1 default store defined!!!");
		else
			return new MStore(ctx, storeIds[0], null);
	}
}
