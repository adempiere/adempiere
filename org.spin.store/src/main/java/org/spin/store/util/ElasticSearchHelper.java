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
package org.spin.store.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MClient;
import org.compiere.model.MStore;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.spin.model.MADAppRegistration;
import org.spin.store.support.IExternalCache;
import org.spin.store.util.support.elasticsearch.IPersistenceWrapper;
import org.spin.util.support.AppSupportHandler;
import org.spin.util.support.IAppSupport;

/**
 * 	Util class for some helper methods
 * 	@author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class ElasticSearchHelper {
	
	/**	Instance	*/
	private static Map<Integer, ElasticSearchHelper> instances;
	/** Web Store	*/
	private MStore webStore;
	/**	Is valid	*/
	private boolean isValid = false;
	/**	Cache	*/
	private IExternalCache externalCache;
	
	/**
	 * Get instance singleton
	 * @return
	 */
	public static ElasticSearchHelper getInstance(MStore webStore) {
		int storeId = 0;
		if(webStore != null) {
			storeId = webStore.getW_Store_ID();
		}
		if(instances == null) {
			instances = new HashMap<Integer, ElasticSearchHelper>();
			instances.put(storeId, new ElasticSearchHelper(webStore));
		}
		return instances.get(storeId);
	}
	
	/**
	 * Default instance
	 * @return
	 */
	public static ElasticSearchHelper getInstance() {
		List<MStore> stores = Arrays.asList(MStore.getOfClient(MClient.get(Env.getCtx())));
		AtomicReference<MStore> store = new AtomicReference<MStore>();
		if(stores != null
				&& stores.size() > 0) {
			stores.forEach(storeToInstance -> {
				if(store.get() == null) {
					store.set(storeToInstance);
				}
			});
		}
		//	
		return ElasticSearchHelper.getInstance(store.get());
	}
	
	/**
	 * Private Constructor
	 * @param webStore
	 */
	private ElasticSearchHelper(MStore webStore) {
		this.webStore = webStore;
	}

	/**
	 * Load values for web store
	 * @throws Exception 
	 */
	private boolean isValid() {
		isValid = webStore != null;
		if(!isValid) {
			return false;
		}
		try {
			isValid = externalCache != null;
			int elasticSearchConnectionId = webStore.get_ValueAsInt(VueStoreFrontUtil.COLUMNNAME_CacheServerProvider_ID);
			if(isValid
					&& externalCache.getCacheId() == elasticSearchConnectionId) {
				return true;
			}
			//	
			if(elasticSearchConnectionId == 0) {
				throw new AdempiereException("@" + VueStoreFrontUtil.COLUMNNAME_CacheServerProvider_ID + "@ @NotFound@");
			}
			IAppSupport supportedApi = AppSupportHandler.getInstance().getAppSupport(MADAppRegistration.getById(Env.getCtx(), elasticSearchConnectionId, null));
			if(supportedApi == null) {
				throw new AdempiereException("@" + VueStoreFrontUtil.COLUMNNAME_CacheServerProvider_ID + "@ @NotFound@");
			}
			//	No valid
			if(!IExternalCache.class.isAssignableFrom(supportedApi.getClass())) {
				throw new AdempiereException("@" + VueStoreFrontUtil.COLUMNNAME_CacheServerProvider_ID + "@ @Invalid@");
			}
			externalCache = (IExternalCache) supportedApi;
			externalCache.withWebStore(webStore);
			isValid = true;
		} catch (Exception e) {
			throw new AdempiereException(e.getLocalizedMessage());
		}
		//	
		return isValid;
	}
	
	/**
	 * @return the webStore
	 */
	public final MStore getWebStore() {
		return webStore;
	}

	/**
	 * @param webStore the webStore to set
	 */
	public final ElasticSearchHelper withWebStore(MStore webStore) {
		this.webStore = webStore;
		return this;
	}
	
	/**
	 * @param webStoreId the webStoreId to set
	 */
	public final ElasticSearchHelper withWebStoreId(int webStoreId) {
		if(webStoreId <= 0) {
			return this;
		}
		this.webStore = MStore.get(Env.getCtx(), webStoreId);
		return this;
	}
	
	/**
	 * @param webContext the organizationId to set
	 */
	public final ElasticSearchHelper withWebContext(String webContext) {
		if(Util.isEmpty(webContext)) {
			return this;
		}
		this.webStore = MStore.get(Env.getCtx(), webContext);
		return this;
	}
	
	/**
	 * Index a entity
	 * @param entity
	 * @return
	 */
	public ElasticSearchHelper index(IPersistenceWrapper entity) {
		if(!isValid()) {
			return this;
		}
		//	
		try {
			externalCache.index(entity.withWebStoreId(webStore.getW_Store_ID()));
		} catch (Exception e) {
			throw new AdempiereException(e.getLocalizedMessage());
		}
		return this;
	}
	
	/**
	 * update a entity
	 * @param entity
	 * @return
	 */
	public ElasticSearchHelper update(IPersistenceWrapper entity) {
		if(!isValid()) {
			return this;
		}
		//	
		try {
			externalCache.update(entity.withWebStoreId(webStore.getW_Store_ID()));
		} catch (Exception e) {
			throw new AdempiereException(e.getLocalizedMessage());
		}
		return this;
	}
	
	/**
	 * delete a entity
	 * @param entity
	 * @return
	 */
	public ElasticSearchHelper delete(IPersistenceWrapper entity) {
		if(!isValid()) {
			return this;
		}
		//	
		try {
			externalCache.delete(entity.withWebStoreId(webStore.getW_Store_ID()));
		} catch (Exception e) {
			throw new AdempiereException(e.getLocalizedMessage());
		}
		return this;
	}
	
	/**
	 * Exists a entity
	 * @param entity
	 * @return
	 */
	public boolean exist(IPersistenceWrapper entity) {
		if(!isValid()) {
			return false;
		}
		//	
		try {
			return externalCache.exist(entity.withWebStoreId(webStore.getW_Store_ID()));
		} catch (Exception e) {
			throw new AdempiereException(e.getLocalizedMessage());
		}
	}
	
	/**
	 * Connect with cache
	 * @param entity
	 * @return
	 */
	public ElasticSearchHelper connect() {
		if(!isValid()) {
			return this;
		}
		//	
		try {
			externalCache.connect();
		} catch (Exception e) {
			throw new AdempiereException(e.getLocalizedMessage());
		}
		return this;
	}
	
	/**
	 * Connect of cache
	 * @param entity
	 * @return
	 */
	public ElasticSearchHelper close() {
		if(!isValid()) {
			return this;
		}
		//	
		try {
			externalCache.close();
		} catch (Exception e) {
			throw new AdempiereException(e.getLocalizedMessage());
		}
		return this;
	}
}
