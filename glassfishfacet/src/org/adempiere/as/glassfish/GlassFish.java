/******************************************************************************
 * Copyright (C) 2008 Low Heng Sin                                            *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.adempiere.as.glassfish;

import java.net.URL;
import java.util.Hashtable;

import javax.naming.Context;

import org.adempiere.as.IApplicationServer;

import com.sun.appserv.security.ProgrammaticLogin;

/**
 * 
 * @author Low Heng Sin
 *
 */
public class GlassFish implements IApplicationServer {

	private static final String LOGIN_CONFIG = "java.security.auth.login.config";

	//ensure client library is installed
	static {
		try {
			Class.forName("com.sun.enterprise.naming.SerialInitContextFactory");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(e);
		}
	}
	
	/**
	 * @see IApplicationServer#getInitialContextEnvironment(String, int, String, String)
	 */
	public Hashtable<String, String> getInitialContextEnvironment(
			String AppsHost, int AppsPort, String principal, String credential) {
		if (principal != null && credential != null) 
		{
			String property = System.getProperty(LOGIN_CONFIG);
			if (property == null || property.trim().length() == 0)
			{
				try 
				{
					URL configUrl = GlassFish.class.getResource("login.conf");
					if (configUrl == null)
						configUrl = GlassFish.class.getClassLoader().getResource("/org/adempiere/as/glassfish/login.conf");
					System.setProperty(LOGIN_CONFIG, configUrl.toString());
				} 
				catch (Exception e)
				{
					throw new RuntimeException("Failed to load login.conf");
				}
				ProgrammaticLogin login = new ProgrammaticLogin();
				try {
					login.login(principal, credential);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}	
			else
			{
				ProgrammaticLogin login = new ProgrammaticLogin();
				try {
					login.login(principal, credential, "adempiere", true);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
		
		Hashtable<String,String> env = new Hashtable<String,String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
		env.put(Context.URL_PKG_PREFIXES, "com.sun.enterprise.naming");
		env.put(Context.STATE_FACTORIES, "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
		
		env.put("org.omg.CORBA.ORBInitialHost", AppsHost);
		env.put("org.omg.CORBA.ORBInitialPort", Integer.toString(AppsPort));
		return env;
	}

	public int getDefaultNamingServicePort() {
		return 3700;
	}

}
