/******************************************************************************
 * Copyright (C) 2009 Low Heng Sin                                            *
 * Copyright (C) 2009 Idalica Corporation                                     *
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
package org.adempiere.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.compiere.interfaces.Server;

/**
 *
 * @author hengsin
 *
 */
public class EmbeddedServerProxy implements InvocationHandler {

	private Server server = null;

	public EmbeddedServerProxy() {
		ClassLoader loader = null;
		loader = Thread.currentThread().getContextClassLoader();
		if (loader == null)
			loader = this.getClass().getClassLoader();
		try {
			Class<?> clazz = loader.loadClass("org.compiere.session.ServerBean");
			server = (Server) clazz.newInstance();
		} catch (Exception e) {
			throw new IllegalStateException("Failed to load org.compiere.session.ServerBean.", e);
		}
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Method m = Server.class.getMethod(method.getName(), method.getParameterTypes());
		return m.invoke(server, args);
	}

}
