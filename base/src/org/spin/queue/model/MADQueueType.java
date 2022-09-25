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
 * Copyright (C) 2003-2015 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/
package org.spin.queue.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.adempiere.core.domains.models.X_AD_QueueType;
import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.Env;

/**
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 * Queue Type Definition
 */
public class MADQueueType extends X_AD_QueueType {


	private static final long serialVersionUID = -1171525387615789574L;

	/** Static Cache */
	private static CCache<Integer, MADQueueType> queueTypeCacheIds = new CCache<Integer, MADQueueType>(Table_Name, 30);
	/** Static Cache */
	private static CCache<String, MADQueueType> queueTypeCacheValues = new CCache<String, MADQueueType>(Table_Name, 30);

	
	public MADQueueType(Properties ctx, int queueTypeId, String trxName) {
		
		super(ctx, queueTypeId, trxName);
	}

	public MADQueueType(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Get/Load Activity [CACHED]
	 * @param ctx context
	 * @param definitionId
	 * @param trxName
	 * @return activity or null
	 */
	public static MADQueueType getById(Properties ctx, int definitionId, String trxName) {
		if (definitionId <= 0)
			return null;

		MADQueueType definition = queueTypeCacheIds.get(definitionId);
		if (definition != null && definition.get_ID() > 0)
			return definition;

		definition = new Query(ctx , Table_Name , COLUMNNAME_AD_QueueType_ID + "=?" , trxName)
				.setParameters(definitionId)
				.first();
		if (definition != null && definition.get_ID() > 0) {
			int clientId = Env.getAD_Client_ID(ctx);
			String key = clientId + "#" + definition.getValue();
			queueTypeCacheValues.put(key, definition);
			queueTypeCacheIds.put(definition.get_ID(), definition);
		}
		return definition;
	}

	/**
	 * get Queue By Value [CACHED]
	 * @param ctx
	 * @param queueType
	 * @param trxName
	 * @return
	 */
	public static MADQueueType getByQueueType(Properties ctx, String queueType, String trxName) {
		if (queueType == null)
			return null;
		if (queueTypeCacheValues.size() == 0) {
			getAll(ctx, true, trxName);
		}

		int clientId = Env.getAD_Client_ID(ctx);
		String key = clientId + "#" + queueType;
		MADQueueType definition = queueTypeCacheValues.get(key);
		if (definition != null && definition.get_ID() > 0 )
			return definition;

		definition =  new Query(ctx, Table_Name , COLUMNNAME_QueueType +  "=? AND AD_Client_ID IN(0, ?)", trxName)
				.setParameters(queueType, clientId)
				.setOrderBy(COLUMNNAME_AD_Client_ID + " DESC")
				.first();

		if (definition != null && definition.get_ID() > 0) {
			queueTypeCacheValues.put(key, definition);
			queueTypeCacheIds.put(definition.get_ID() , definition);
		}
		return definition;
	}

	/**
	 * Get All Activity
	 * @param ctx
	 * @param resetCache
	 * @param trxName
	 * @return
	 */
	public static List<MADQueueType> getAll(Properties ctx, boolean resetCache, String trxName) {
		List<MADQueueType> definitionList;
		if (resetCache || queueTypeCacheIds.size() > 0 ) {
			definitionList = new Query(Env.getCtx(), Table_Name, null , trxName)
					.setClient_ID()
					.setOrderBy(COLUMNNAME_Name)
					.list();
			definitionList.stream().forEach(definition -> {
				int clientId = Env.getAD_Client_ID(ctx);
				String key = clientId + "#" + definition.getValue();
				queueTypeCacheIds.put(definition.getAD_QueueType_ID(), definition);
				queueTypeCacheValues.put(key, definition);
			});
			return definitionList;
		}
		definitionList = queueTypeCacheIds.entrySet().stream()
				.map(activity -> activity.getValue())
				.collect(Collectors.toList());
		return  definitionList;
	}

	@Override
	public String toString() {
		return "MADQueueType [getAD_QueueType_ID()=" + getAD_QueueType_ID() + ", getClassname()="
				+ getClassname() + ", getName()=" + getName() + ", getQueueType()=" + getQueueType() + ", getValue()="
				+ getValue() + "]";
	}
}
