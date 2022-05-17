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
package org.spin.queue.notification.util;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.CLogger;
import org.compiere.util.Util;
import org.spin.queue.notification.model.MADNotificationQueue;
import org.spin.queue.notification.model.MADNotificationUpdates;
import org.spin.queue.notification.support.IUpdateHandler;
/**
 * @author Yamel Senih, ySenih@erpya.com, ERPCyA http://www.erpya.com
 *		A util class for update handler
 */
public class UpdateHandlerUtil {
	
    private static UpdateHandlerUtil appSupportHandler = null;
    
    /**	Token Generator	*/
    private Map<String, IUpdateHandler> updateHandlerGeneratorMap = null;
    
	private static final CLogger logger = CLogger.getCLogger(UpdateHandlerUtil.class);
	
	/**
	 * Singleton
	 * @return
	 */
    public static UpdateHandlerUtil getInstance() {
    	if(appSupportHandler == null) {
    		appSupportHandler = new UpdateHandlerUtil();
    	}
    	return appSupportHandler;
    }
    
    /**
     * Instance hash map
     */
    private UpdateHandlerUtil() {
    	updateHandlerGeneratorMap = new HashMap<String, IUpdateHandler>();
    }
    
    /**
     * Run from update
     * @param update
     */
    public void runHandler(MADNotificationUpdates update) {
    	if(update == null) {
    		return;
    	}
    	MADNotificationQueue queue = (MADNotificationQueue) update.getAD_NotificationQueue();
    	IUpdateHandler handler = getHandler(queue.getResponseHandler());
    	if(handler != null) {
    		handler.run(update);
    	}
    }

    /**
     * Get Database
     * @param handler
     * @return
     * @throws Exception
     */
    private IUpdateHandler getHandler(String handler) {
    	if(handler == null) {
    		return null;
    	}
    	if(!updateHandlerGeneratorMap.containsKey(handler)) {
            loadClass(handler);
            return updateHandlerGeneratorMap.get(handler);
        }
        //  Default return
        return updateHandlerGeneratorMap.get(handler);
    }
    
    /**
     * Get Class from device type, used for handler
     * @param handler Handler
     * @return
     * @return Class<?>
     */
    private Class<?> getHandlerClass(String handler) {
        //	Validate null values
        if(Util.isEmpty(handler)) {
            return null;
        }
        try {
            Class<?> clazz = Class.forName(handler);
            if(IUpdateHandler.class.isAssignableFrom(clazz)) {
                return clazz;
            }
            //	Make sure that it is a PO class
            Class<?> superClazz = clazz.getSuperclass();
            //	Validate super class
            while (superClazz != null) {
                if (superClazz == IUpdateHandler.class) {
                	logger.log(Level.SEVERE, "Error loading class, Use: " + handler);
                    return clazz;
                }
                //	Get Super Class
                superClazz = superClazz.getSuperclass();
            }
        } catch (Exception e) {
        	logger.severe("Loading class Error"+ e.getMessage());
        }
        //
        logger.log(Level.SEVERE,"Not found Class: " + handler);
        return null;
    }	//	getHandlerClass

    /**
     * Load class for export
     * @param handler Handler
     * @throws Exception
     */
    private void loadClass(String handler) {
        //	Load it
        //	Get class from parent
    	try {
    		Class<?> clazz = getHandlerClass(handler);
            IUpdateHandler generator = null;
            //	Not yet implemented
            if (clazz == null) {
                logger.log(Level.SEVERE, "Class not found, Using Standard Class");
                generator = null;
                return;
            }
            //
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            //	new instance
            generator = (IUpdateHandler) constructor.newInstance();
            updateHandlerGeneratorMap.put(handler, generator);
    	} catch (Exception e) {
    		throw new AdempiereException(e.getLocalizedMessage());
		}
    }
}
