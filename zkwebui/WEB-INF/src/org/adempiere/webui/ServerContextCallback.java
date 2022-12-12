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
package org.adempiere.webui;

import org.adempiere.webui.session.ServerContext;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Intercaptor for Server context properties that delegate to the threadlocal instance
 *
 * @author Low Heng Sin
 * @author Victor Perez Juarez . victor.perez@e-evolution.com e-Evolution
 * #4021 [Bug Report] Replace obsolete library cglib.jar not compatible with JDK 11 or > https://github.com/adempiere/adempiere/issues/4021
 */
public class ServerContextCallback implements InvocationHandler, Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 6708635918931322152L;

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        Properties context = ServerContext.getCurrentInstance();
        //optimize for the 2 most common access
        if (method.getName().equals("getProperty")) {
            Class<?>[] types = method.getParameterTypes();
            if (types != null && types.length == 1 && types[0] == String.class &&
                    args != null && args.length == 1 && args[0] instanceof String) {
                return context.getProperty((String) args[0]);
            } else if (types != null && types.length == 2 && types[0] == String.class &&
                    types[1] == String.class && args != null && args[0] instanceof String &&
                    args[1] instanceof String)
                return context.getProperty((String) args[0], (String) args[1]);
        }
        Method m = context.getClass().getMethod(method.getName(), method.getParameterTypes());
        return m.invoke(context, args);
    }

}
