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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.Env;

/**
 * @author Yamel Senih, ySenih@erpya.com, ERPCyA http://www.erpya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/2109">
 * 		@see FR [ 2109 ] Add App Registration ADempiere</a>
 */
public class MADAppRegistration extends X_AD_AppRegistration {


	private static final long serialVersionUID = -1171525387615789574L;

	/** Static Cache */
	private static CCache<Integer, MADAppRegistration> definitionCacheIds = new CCache<Integer, MADAppRegistration>(Table_Name, 30);
	/** Static Cache */
	private static CCache<String, MADAppRegistration> definitionCacheValues = new CCache<String, MADAppRegistration>(Table_Name, 30);
	/**	Parameters	*/
	private Map<String, MADAppRegistrationPara> parameters = null;
	
	public MADAppRegistration(Properties ctx, int AD_AppRegistration_ID, String trxName) {
		super(ctx, AD_AppRegistration_ID, trxName);
	}

	public MADAppRegistration(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Get/Load Activity [CACHED]
	 * @param ctx context
	 * @param registrationId
	 * @param trxName
	 * @return activity or null
	 */
	public static MADAppRegistration getById(Properties ctx, int registrationId, String trxName) {
		if (registrationId <= 0)
			return null;

		MADAppRegistration definition = definitionCacheIds.get(registrationId);
		if (definition != null && definition.get_ID() > 0)
			return definition;

		definition = new Query(ctx , Table_Name , COLUMNNAME_AD_AppRegistration_ID + "=?" , trxName)
				.setParameters(registrationId)
				.first();
		if (definition != null && definition.get_ID() > 0) {
			String key = definition.getValue();
			definitionCacheValues.put(key, definition);
			definitionCacheIds.put(definition.get_ID(), definition);
		}
		return definition;
	}

	/**
	 * get Activity By Value [CACHED]
	 * @param ctx
	 * @param applicationType
	 * @param trxName
	 * @return
	 */
	public static MADAppRegistration getByApplicationType(Properties ctx, String applicationType, String trxName) {
		if (applicationType == null)
			return null;
		if (definitionCacheValues.size() == 0) {
			getAll(ctx, true, trxName);
		}
		String key = applicationType;
		MADAppRegistration definition = definitionCacheValues.get(key);
		if (definition != null && definition.get_ID() > 0 )
			return definition;

		definition =  new Query(ctx, Table_Name , COLUMNNAME_ApplicationType +  "=?", trxName)
				.setParameters(applicationType)
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
	public static List<MADAppRegistration> getAll(Properties ctx, boolean resetCache, String trxName) {
		List<MADAppRegistration> definitionList;
		if (resetCache || definitionCacheIds.size() > 0 ) {
			definitionList = new Query(Env.getCtx(), Table_Name, null , trxName)
					.setOrderBy(COLUMNNAME_Value)
					.list();
			definitionList.stream().forEach(definition -> {
				String key = definition.getValue();
				definitionCacheIds.put(definition.getAD_AppRegistration_ID(), definition);
				definitionCacheValues.put(key, definition);
			});
			return definitionList;
		}
		definitionList = definitionCacheIds.entrySet().stream()
				.map(activity -> activity.getValue())
				.collect(Collectors.toList());
		return  definitionList;
	}
	
	/**
	 * Get parameter value from name
	 * @param parameterName
	 * @return
	 */
	public String getParameterValue(String parameterName) {
		if(parameters == null) {
			loadParameters();
		}
		return parameters.get(parameterName).getParameterValue();
	}
	
	/**
	 * Get Parameter Type
	 * @param parameterName
	 * @return
	 */
	public String getParameterType(String parameterName) {
		if(parameters == null) {
			loadParameters();
		}
		return parameters.get(parameterName).getParameterType();
	}
	
	/**
	 * Load Parameters
	 */
	private void loadParameters() {
		parameters = new HashMap<String, MADAppRegistrationPara>();
		new Query(getCtx(), I_AD_AppRegistration_Para.Table_Name, COLUMNNAME_AD_AppRegistration_ID + " = ?", get_TrxName())
			.setParameters(getAD_AppRegistration_ID())
			.<MADAppRegistrationPara>list().forEach(parameter -> {
			parameters.put(parameter.getParameterName(), parameter);
		});
	}

	@Override
	public String toString() {
		return "MADAppRegistration [getAD_AppRegistration_ID()=" + getAD_AppRegistration_ID() + ", getName()=" + getName() 
		+ ", getApplicationType()=" + getApplicationType() + ", getValue()="
				+ getValue() + "]";
	}
}
