/******************************************************************************
 * Product: Posterita Ajax UI 												  *
 * Copyright (C) 2007 Posterita Ltd.  All Rights Reserved.                    *
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
 * Posterita Ltd., 3, Draper Avenue, Quatre Bornes, Mauritius                 *
 * or via info@posterita.org or http://www.posterita.org/                     *
 *****************************************************************************/

package org.adempiere.webui.session;

import java.util.Properties;

import org.compiere.util.Env;
import org.compiere.util.Language;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date    Feb 25, 2007
 * @version $Revision: 0.10 $
 */
public final class ServerContext extends Properties
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4686544952076576992L;

	private ServerContext()
    {
        super();
        /**
         * Set english as default language
         */
        this.put(Env.LANGUAGE, Language.getBaseAD_Language());        
    }
    
    private static InheritableThreadLocal<ServerContext> context = new InheritableThreadLocal<ServerContext>() {
        protected ServerContext initialValue()
        {
            return new ServerContext();
        }
    };
    
    /**
     * Get server context for current thread
     * @return ServerContext
     */
    public static ServerContext getCurrentInstance()
    {
        return (ServerContext)context.get();
    }
    
    /**
     * dispose server context for current thread
     */
    public static void dispose()
    {
    	context.remove();
    }
    
    /**
     * Allocate new server context for current thread
     * @return ServerContext
     */
    public static ServerContext newInstance() 
    {
    	dispose();
    	return getCurrentInstance();
    }
    
    /**
     * Set server context for current thread
     * @param ctx
     */
    public static void setCurrentInstance(ServerContext ctx)
    {
        context.set(ctx);
    }
}
