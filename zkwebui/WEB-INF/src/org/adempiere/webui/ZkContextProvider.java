/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2007 Adempiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *
 * Copyright (C) 2007 Low Heng Sin hengsin@avantz.com
 * _____________________________________________
 *****************************************************************************/
package org.adempiere.webui;

import net.bytebuddy.implementation.InvocationHandlerAdapter;
import org.adempiere.webui.session.SessionManager;
import org.compiere.util.ContextProvider;

import java.util.Properties;

import static net.bytebuddy.matcher.ElementMatchers.any;



/**
 * 
 * @author Low Heng Sin
 * @author Victor Perez Juarez . victor.perez@e-evolution.com e-Evolution
 * #4021 [Bug Report] Replace obsolete library cglib.jar not compatible with JDK 11 or > https://github.com/adempiere/adempiere/issues/4021
 *
 */
public class ZkContextProvider implements ContextProvider {

	private final static  ServerContextCallback callback = new ServerContextCallback();

	private final static Properties context;

	static {
		try {
			context = (new net.bytebuddy.ByteBuddy()
					.subclass(java.util.Properties.class)
					.method(any())
					.intercept(InvocationHandlerAdapter.of(callback))
					.make()
					.load(ZkContextProvider.class.getClassLoader())
					.getLoaded()).newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Get server context proxy
	 */
	public Properties getContext() {
		return context;
	}

	/**
	 * Show url at zk desktop
	 */
	public void showURL(String url) {
		showURL(url,"");
	}	

	/**
	 * Show url at zk desktop
	 */
	public void showURL(String url, String title) {
		SessionManager.getAppDesktop().showURL(url, title, true);
	} 
}
