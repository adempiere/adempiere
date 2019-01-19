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
package org.spin.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.Env;

/**
 * @author Yamel Senih, ySenih@erpya.com, ERPCyA http://www.erpya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1769">
 * 		@see FR [ 1769 ] Add option to restore the password from the login</a>
 *
 */
public class MADTokenDefinition extends X_AD_TokenDefinition {


	private static final long serialVersionUID = -1171525387615789574L;

	/** Static Cache */
	private static CCache<Integer, MADTokenDefinition> definitionCacheIds = new CCache<Integer, MADTokenDefinition>(Table_Name, 30);
	/** Static Cache */
	private static CCache<String, MADTokenDefinition> definitionCacheValues = new CCache<String, MADTokenDefinition>(Table_Name, 30);

	
	public MADTokenDefinition(Properties ctx, int AD_TokenDefinition_ID, String trxName) {
		
		super(ctx, AD_TokenDefinition_ID, trxName);
	}

	public MADTokenDefinition(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Get/Load Activity [CACHED]
	 * @param ctx context
	 * @param definitionId
	 * @param trxName
	 * @return activity or null
	 */
	public static MADTokenDefinition getById(Properties ctx, int definitionId, String trxName) {
		if (definitionId <= 0)
			return null;

		MADTokenDefinition definition = definitionCacheIds.get(definitionId);
		if (definition != null && definition.get_ID() > 0)
			return definition;

		definition = new Query(ctx , Table_Name , COLUMNNAME_AD_TokenDefinition_ID + "=?" , trxName)
				.setClient_ID()
				.setParameters(definitionId)
				.first();
		if (definition != null && definition.get_ID() > 0) {
			int clientId = Env.getAD_Client_ID(ctx);
			String key = clientId + "#" + definition.getValue();
			definitionCacheValues.put(key, definition);
			definitionCacheIds.put(definition.get_ID(), definition);
		}
		return definition;
	}

	/**
	 * get Activity By Value [CACHED]
	 * @param ctx
	 * @param tokenType
	 * @param trxName
	 * @return
	 */
	public static MADTokenDefinition getByTokenType(Properties ctx, String tokenType, String trxName) {
		if (tokenType == null)
			return null;
		if (definitionCacheValues.size() == 0) {
			getAll(ctx, true, trxName);
		}

		int clientId = Env.getAD_Client_ID(ctx);
		String key = clientId + "#" + tokenType;
		MADTokenDefinition definition = definitionCacheValues.get(key);
		if (definition != null && definition.get_ID() > 0 )
			return definition;

		definition =  new Query(ctx, Table_Name , COLUMNNAME_TokenType +  "=?", trxName)
				.setClient_ID()
				.setParameters(tokenType)
				.setOrderBy(COLUMNNAME_Value)
				.first();

		if (definition != null && definition.get_ID() > 0) {
			definitionCacheValues.put(key, definition);
			definitionCacheIds.put(definition.get_ID() , definition);
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
	public static List<MADTokenDefinition> getAll(Properties ctx, boolean resetCache, String trxName) {
		List<MADTokenDefinition> definitionList;
		if (resetCache || definitionCacheIds.size() > 0 ) {
			definitionList = new Query(Env.getCtx(), Table_Name, null , trxName)
					.setClient_ID()
					.setOrderBy(COLUMNNAME_Name)
					.list();
			definitionList.stream().forEach(definition -> {
				int clientId = Env.getAD_Client_ID(ctx);
				String key = clientId + "#" + definition.getValue();
				definitionCacheIds.put(definition.getAD_TokenDefinition_ID(), definition);
				definitionCacheValues.put(key, definition);
			});
			return definitionList;
		}
		definitionList = definitionCacheIds.entrySet().stream()
				.map(activity -> activity.getValue())
				.collect(Collectors.toList());
		return  definitionList;
	}

	@Override
	public String toString() {
		return "MADTokenDefinition [getAD_TokenDefinition_ID()=" + getAD_TokenDefinition_ID() + ", getClassname()="
				+ getClassname() + ", getName()=" + getName() + ", getTokenType()=" + getTokenType() + ", getValue()="
				+ getValue() + "]";
	}
}
