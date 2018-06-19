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
 * Contributor(s): Yamel Senih www.erpconsultoresyasociados.com               *
 *****************************************************************************/
package org.spin.process;

import java.lang.reflect.Constructor;
import java.security.SecureRandom;
import java.util.logging.Level;

import org.compiere.model.MSysConfig;
import org.compiere.util.CLogger;
import org.compiere.util.Util;
import org.spin.model.MToken;

import com.sun.enterprise.admin.util.Logger;
/**
 * @author Raul Muñoz, rMunoz@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1446">
 * 		@see FR [ 1446 ] Smart Browse for Deposit from cash</a>
 *
 */
public class TokenGeneratorHandler {


    protected static SecureRandom random = new SecureRandom();
    private static TokenGeneratorHandler tokenHandler = null;
    

    private ITokenGenerator  tokenGenerator = null;

	private static final CLogger logger = CLogger.getCLogger(TokenGeneratorHandler.class);
	
	
    public static TokenGeneratorHandler getInstance() {
    	if(tokenHandler == null)
    		tokenHandler = new TokenGeneratorHandler();
    	
    	return tokenHandler;
    }
    

    /**
     * Get Database
     * @return
     * @throws Exception
     */
    private ITokenGenerator getTokenGenerator() throws Exception  {
        if(tokenGenerator == null) {
            loadClass();
        }
        //  Default return
        return tokenGenerator;
    }

    public String generateToken(int userId) throws Exception {
    	return getTokenGenerator().generateToken(userId);
    }

    public boolean validateToken(String token, int userId) throws Exception {
    	return getTokenGenerator().validateToken(token, userId);
    }
    
    public MToken getToken() throws Exception {
    	return getTokenGenerator().getToken();
    }
    /**
     * Get class name for instance
     * @return
     */
    private String getClassname() {
        return MSysConfig.getValue("TokenGeneratorClass", "org.spin.process.TokenGenerator");
    }
    
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
            if(ITokenGenerator.class.isAssignableFrom(clazz)) {
                return clazz;
            }
            //	Make sure that it is a PO class
            Class<?> superClazz = clazz.getSuperclass();
            //	Validate super class
            while (superClazz != null) {
                if (superClazz == ITokenGenerator.class) {
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
     * @throws Exception
     */
    private void loadClass() throws Exception {
        if(tokenGenerator != null) {
            return;
        }
        //	Load it
        //	Get class from parent
        Class<?> clazz = getHandlerClass();
        //	Not yet implemented
        if (clazz == null) {
            logger.log(Level.SEVERE, "Class not found, Using Standard Class");
            tokenGenerator = null;
            throw new Exception("Class for connection not found");
        }
        //
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        //	new instance
        tokenGenerator = (ITokenGenerator) constructor.newInstance();
    }

  	
	  

	
}
