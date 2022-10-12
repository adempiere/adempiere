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

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.core.domains.models.X_FM_FunctionalSetting;
import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.Util;
import org.spin.investment.util.AbstractFunctionalSetting;
import org.spin.investment.util.GenericFunctionalSetting;

/**
 * Financial Management
 *
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 *      <li> FR [ 1583 ] New Definition for loan
 *		@see https://github.com/adempiere/adempiere/issues/1583
 */
public class MFMFunctionalSetting extends X_FM_FunctionalSetting {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1922091584368243121L;

	public MFMFunctionalSetting(Properties ctx, int FM_FunctionalSetting_ID, String trxName) {
		super(ctx, FM_FunctionalSetting_ID, trxName);
	}

	public MFMFunctionalSetting(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**	Export class	*/
	private AbstractFunctionalSetting functionalSetting = null;
	
	/**
	 * Get Class from device type, used for handler
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
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
				if (superClazz == AbstractFunctionalSetting.class) {
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
		if(functionalSetting != null) {
			return;
		}
		//	Load it
		//	Get class from parent
		Class<?> clazz = getHandlerClass();
		//	Not yet implemented
		if (clazz == null) {
			log.log(Level.INFO, "Using Standard Functional Setting");
			functionalSetting = new GenericFunctionalSetting(this);
			return;
		}
		//	
		Constructor<?> constructor = clazz.getDeclaredConstructor(new Class[]{MFMFunctionalSetting.class});
		//	new instance
		functionalSetting = (AbstractFunctionalSetting) constructor.newInstance(new Object[] {this});
	}
	
	/**
	 * Get Report export instance
	 * @return
	 */
	public AbstractFunctionalSetting getSettingInstance() throws Exception {
		loadClass();
		return functionalSetting;
	}
	
	/** Static Cache */
	private static CCache<Integer, MFMFunctionalSetting> functionalSettingCacheIds = new CCache<Integer, MFMFunctionalSetting>(Table_Name, 30);

	/**
	 * Get/Load Functional Setting [CACHED]
	 * @param ctx context
	 * @param functionalSettingId
	 * @return activity or null
	 */
	public static MFMFunctionalSetting getById(Properties ctx, int functionalSettingId) {
		if (functionalSettingId <= 0)
			return null;

		MFMFunctionalSetting functionalSetting = functionalSettingCacheIds.get(functionalSettingId);
		if (functionalSetting != null && functionalSetting.get_ID() > 0)
			return functionalSetting;

		functionalSetting = new Query(ctx , Table_Name , COLUMNNAME_FM_FunctionalSetting_ID + "=?" , null)
				.setClient_ID()
				.setParameters(functionalSettingId)
				.first();
		if (functionalSetting != null && functionalSetting.get_ID() > 0) {
			functionalSettingCacheIds.put(functionalSetting.get_ID(), functionalSetting);
		}
		return functionalSetting;
	}
}
