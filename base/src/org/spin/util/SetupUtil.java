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
package org.spin.util;

import java.lang.reflect.Constructor;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.Util;
import org.spin.model.MADSetupDefinition;


/**
 * Util class for handle setup
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class SetupUtil {
	
	/**
	 * Get Setup handler for definition
	 * @param context
	 * @param setupDefinitionId
	 * @param transactionName
	 * @return
	 */
	public static ISetupDefinition getSetupHandler(Properties context, int setupDefinitionId, String transactionName) {
		if(setupDefinitionId <= 0) {
			throw new AdempiereException("@AD_SetupDefinition_ID@ @NotFound@");
		}
		//	Load
		MADSetupDefinition definition = new MADSetupDefinition(context, setupDefinitionId, transactionName);
		if(Util.isEmpty(definition.getClassname())) {
			throw new AdempiereException("@Classname@ @NotFound@");
		}
		//	Get handler
		return getInstance(definition.getClassname());
	}
	
	/**
	 * Get instance of class from classname
	 * @param className
	 * @return
	 */
    private static ISetupDefinition getInstance(String className) {
        //	Validate null values
        if(Util.isEmpty(className)) {
            return null;
        }
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className);
            if(!ISetupDefinition.class.isAssignableFrom(clazz)) {
                //	Make sure that it is a PO class
                Class<?> superClazz = clazz.getSuperclass();
                //	Validate super class
                while (superClazz != null) {
                    if (superClazz == ISetupDefinition.class) {
                    	break;
                    }
                    //	Get Super Class
                    superClazz = superClazz.getSuperclass();
                }
            }
            //	
            if(clazz != null) {
            	Constructor<?> constructor = clazz.getDeclaredConstructor();
                //	new instance
                return (ISetupDefinition) constructor.newInstance();
            }
        } catch (Exception e) {
        	throw new AdempiereException(e);
        }
        //	Default
        return null;
    }	//	getHandlerClass
}
