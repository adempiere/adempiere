/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 or later of the                                  *
 * GNU General Public License as published                                    *
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

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.stream.Collectors;

import org.adempiere.core.domains.models.X_AD_Package_Exp_Custom;
import org.adempiere.pipo.handler.GenericPOHandler;
import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.Env;
import org.compiere.util.Util;

/**
 * Custom Exporter Class for handle exporter
 * @author Yamel Senih, ySenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class MADPackageExpCustom extends X_AD_Package_Exp_Custom {


	private static final long serialVersionUID = -1171525387615789574L;

	/** Static Cache */
	private static CCache<Integer, MADPackageExpCustom> exporterCacheIds = new CCache<Integer, MADPackageExpCustom>(Table_Name, 30);
	/** Static Cache */
	private static CCache<String, MADPackageExpCustom> exporterCacheValues = new CCache<String, MADPackageExpCustom>(Table_Name, 30);

	
	public MADPackageExpCustom(Properties ctx, int AD_AppSupport_ID, String trxName) {
		
		super(ctx, AD_AppSupport_ID, trxName);
	}

	public MADPackageExpCustom(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Get/Load Activity [CACHED]
	 * @param ctx context
	 * @param definitionId
	 * @param trxName
	 * @return activity or null
	 */
	public static MADPackageExpCustom getById(Properties ctx, int definitionId, String trxName) {
		if (definitionId <= 0)
			return null;

		MADPackageExpCustom definition = exporterCacheIds.get(definitionId);
		if (definition != null && definition.get_ID() > 0)
			return definition;

		definition = new Query(ctx , Table_Name , COLUMNNAME_AD_Package_Exp_Custom_ID + "=?" , trxName)
				.setParameters(definitionId)
				.first();
		if (definition != null && definition.get_ID() > 0) {
			String key = definition.getValue();
			exporterCacheValues.put(key, definition);
			exporterCacheIds.put(definition.get_ID(), definition);
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
	public static List<MADPackageExpCustom> getAll(Properties ctx, boolean resetCache, String trxName) {
		List<MADPackageExpCustom> definitionList;
		if (resetCache || exporterCacheIds.size() > 0 ) {
			definitionList = new Query(Env.getCtx(), Table_Name, null , trxName)
					.setOrderBy(COLUMNNAME_Value)
					.list();
			definitionList.stream().forEach(definition -> {
				String key = definition.getValue();
				exporterCacheIds.put(definition.getAD_Package_Exp_Custom_ID(), definition);
				exporterCacheValues.put(key, definition);
			});
			return definitionList;
		}
		definitionList = exporterCacheIds.entrySet().stream()
				.map(activity -> activity.getValue())
				.collect(Collectors.toList());
		return  definitionList;
	}
	
	/**	Export class	*/
	private GenericPOHandler exporterHandler = null;
	
	/**
	 * Get Class from Custom Package Exporter
	 * @return
	 * @return Class<?>
	 */
	private Class<?> getHandlerClass() {
		String className = getClassname();
		//	Validate null values
		if(Util.isEmpty(className)) {
			return null;
		}
		try {
			Class<?> clazz = Class.forName(className);
			//	Make sure that it is a PO class
			Class<?> superClazz = clazz.getSuperclass();
			//	Validate super class
			while (superClazz != null) {
				if (superClazz == GenericPOHandler.class) {
					log.fine("Use: " + className);
					return clazz;
				}
				//	Get Super Class
				superClazz = superClazz.getSuperclass();
			}
		} catch (Exception e) {
			log.severe(e.getMessage());
		}
		//	
		log.finest("Not found: " + className);
		return null;
	}	//	getHandlerClass

	/**
	 * Load class for export
	 * @throws Exception
	 */
	private void loadClass() throws Exception {
		if(exporterHandler != null) {
			return;
		}
		//	Load it
		//	Get class from parent
		Class<?> clazz = getHandlerClass();
		//	Not yet implemented
		if (clazz == null) {
			log.log(Level.INFO, "Using Standard Functional Setting");
			exporterHandler = null;
			return;
		}
		//	
		Constructor<?> constructor = clazz.getDeclaredConstructor(new Class[]{});
		//	new instance
		exporterHandler = (GenericPOHandler) constructor.newInstance(new Object[] {});
	}
	
	/**
	 * Get Report export instance
	 * @return
	 */
	public GenericPOHandler getExporterInstance() throws Exception {
		loadClass();
		return exporterHandler;
	}

	@Override
	public String toString() {
		return "MADPackageExpCustom [getAD_Package_Exp_Custom_ID()=" + getAD_Package_Exp_Custom_ID()
				+ ", getClassname()=" + getClassname() + ", getName()=" + getName() + ", getValue()=" + getValue()
				+ "]";
	}
}
