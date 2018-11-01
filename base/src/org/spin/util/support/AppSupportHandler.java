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
package org.spin.util.support;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.Util;
import org.spin.model.MADAppRegistration;
import org.spin.model.MADAppSupport;
import org.compiere.util.Env;

import com.sun.enterprise.admin.util.Logger;
/**
 * @author Yamel Senih, ySenih@erpya.com, ERPCyA http://www.erpya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/2109">
 * 		@see FR [ 2109 ] Add App Registration ADempiere</a>
 */
public class AppSupportHandler {
	
    private static AppSupportHandler appSupportHandler = null;
    
    /**	Token Generator	*/
    private Map<Integer, IAppSupport> appSupportGeneratorMap = null;
    
	private static final CLogger logger = CLogger.getCLogger(AppSupportHandler.class);
	
	/**
	 * Singleton
	 * @return
	 */
    public static AppSupportHandler getInstance() {
    	if(appSupportHandler == null) {
    		appSupportHandler = new AppSupportHandler();
    	}
    	return appSupportHandler;
    }
    
    /**
     * Instance hash map
     */
    private AppSupportHandler() {
    	appSupportGeneratorMap = new HashMap<Integer, IAppSupport>();
    }

    /**
     * Get Database
     * @return
     * @throws Exception
     */
    public IAppSupport getAppSupport(MADAppRegistration registration) throws Exception {
    	if(registration == null) {
    		return null;
    	}
        if(!appSupportGeneratorMap.containsKey(registration.getAD_AppSupport_ID())) {
            loadClass(registration.getAD_AppRegistration_ID(), registration.getAD_AppSupport_ID());
        }
        //  Default return
        return appSupportGeneratorMap.get(registration.getAD_AppSupport_ID());
    }
    
    /**
     * Get class name for instance
     * @param appSupportId
     * @return
     */
    private String getClassname(int appSupportId) {
    	MADAppSupport definition = MADAppSupport.getById(Env.getCtx(), appSupportId, null);
    	if(definition == null) {
    		return null;
    	}
    	//	Default
    	return definition.getClassname();
    }
    
    /**
     * Get Class from device type, used for handler
     * @param appSupportId
     * @return
     * @return Class<?>
     */
    private Class<?> getHandlerClass(int appSupportId) {
        String className = getClassname(appSupportId);
        //	Validate null values
        if(Util.isEmpty(className)) {
            return null;
        }
        try {
            Class<?> clazz = Class.forName(className);
            if(IAppSupport.class.isAssignableFrom(clazz)) {
                return clazz;
            }
            //	Make sure that it is a PO class
            Class<?> superClazz = clazz.getSuperclass();
            //	Validate super class
            while (superClazz != null) {
                if (superClazz == IAppSupport.class) {
                	logger.log(Level.SEVERE, "Error loading class, Use: " + className);
                    return clazz;
                }
                //	Get Super Class
                superClazz = superClazz.getSuperclass();
            }
        } catch (Exception e) {
        	Logger.logError("Loading class Error"+ e.getMessage());
        }
        //
        logger.log(Level.SEVERE,"Not found Class: " + className);
        return null;
    }	//	getHandlerClass

    /**
     * Load class for export
     * @param appRegistrationId
     * @param appSupportId
     * @throws Exception
     */
    private void loadClass(int appRegistrationId, int appSupportId) throws Exception {
        //	Load it
        //	Get class from parent
        Class<?> clazz = getHandlerClass(appSupportId);
        IAppSupport generator = null;
        //	Not yet implemented
        if (clazz == null) {
            logger.log(Level.SEVERE, "Class not found, Using Standard Class");
            generator = null;
            throw new Exception("Class for connection not found");
        }
        //
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        //	new instance
        generator = (IAppSupport) constructor.newInstance();
        generator.setAppRegistrationId(appRegistrationId);
        appSupportGeneratorMap.put(appSupportId, generator);
    }
}
