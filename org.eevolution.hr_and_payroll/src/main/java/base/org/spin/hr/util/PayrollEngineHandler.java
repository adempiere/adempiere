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
package org.spin.hr.util;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.compiere.model.MRule;
import org.compiere.util.CLogger;
import org.compiere.util.Util;
import org.spin.util.RuleEngineUtil;
/**
 * @author Yamel Senih, ySenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class PayrollEngineHandler {
	
    private static PayrollEngineHandler ruleEngineHandler = null;
    
    /**	Map for engines	*/
    private Map<Integer, RuleInterface> ruleEngineMap = null;
    
	private static final CLogger logger = CLogger.getCLogger(PayrollEngineHandler.class);
	
	/**
	 * Singleton
	 * @return
	 */
    public static PayrollEngineHandler getInstance() {
    	if(ruleEngineHandler == null) {
    		ruleEngineHandler = new PayrollEngineHandler();
    	}
    	return ruleEngineHandler;
    }
    
    /**
     * Instance hash map
     */
    public PayrollEngineHandler() {
    	ruleEngineMap = new HashMap<Integer, RuleInterface>();
    }

    /**
     * Get Database
     * @param rule
     * @return
     * @throws Exception
     */
    public RuleInterface getRuleEngine(MRule rule) throws Exception {
    	if(rule == null) {
    		return null;
    	}
        if(!ruleEngineMap.containsKey(rule.getAD_Rule_ID())) {
            loadClass(rule);
            return ruleEngineMap.get(rule.getAD_Rule_ID());
        }
        //  Default return
        return ruleEngineMap.get(rule.getAD_Rule_ID());
    }
    
    /**
     * Get Class from device type, used for handler
     * @param rule
     * @return
     * @return Class<?>
     */
    private Class<?> getHandlerClass(MRule rule) {
        String className = RuleEngineUtil.getCompleteClassName(rule);
        //	Validate null values
        if(Util.isEmpty(className)) {
            return null;
        }
        try {
            Class<?> clazz = Class.forName(className);
            if(RuleInterface.class.isAssignableFrom(clazz)) {
                return clazz;
            }
            //	Make sure that it is a PO class
            Class<?> superClazz = clazz.getSuperclass();
            //	Validate super class
            while (superClazz != null) {
                if (superClazz == RuleInterface.class) {
                	logger.log(Level.SEVERE, "Error loading class, Use: " + className);
                    return clazz;
                }
                //	Get Super Class
                superClazz = superClazz.getSuperclass();
            }
        } catch (Exception e) {
        	logger.log(Level.SEVERE, "Loading class Error"+ e.getMessage());
        }
        //
        logger.log(Level.SEVERE,"Not found Class: " + className);
        return null;
    }	//	getHandlerClass

    /**
     * Load class for export
     * @param rule
     * @throws Exception
     */
    private void loadClass(MRule rule) throws Exception {
        //	Load it
        //	Get class from parent
        Class<?> clazz = getHandlerClass(rule);
        RuleInterface generator = null;
        //	Not yet implemented
        if (clazz == null) {
            logger.log(Level.SEVERE, "Class not found, Using Standard Class");
            generator = null;
            throw new ClassNotFoundException("Class for rule engine not found");
        }
        //
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        //	new instance
        generator = (RuleInterface) constructor.newInstance();
        ruleEngineMap.put(rule.getAD_Rule_ID(), generator);
    }
}
