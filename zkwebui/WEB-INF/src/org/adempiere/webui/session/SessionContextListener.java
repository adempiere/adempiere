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

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventThreadInit;
import org.zkoss.zk.ui.event.EventThreadResume;
import org.zkoss.zk.ui.util.ExecutionCleanup;
import org.zkoss.zk.ui.util.ExecutionInit;

/**
 * 
 * @author <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date Feb 25, 2007
 * @version $Revision: 0.10 $
 */
public class SessionContextListener implements ExecutionInit,
        ExecutionCleanup, EventThreadInit, EventThreadResume
{
    public static final String SESSION_CTX = "WebUISessionContext";

    public void init(Execution exec, Execution parent)
    {
        if (parent == null)
        { 
            ServerContext ctx = (ServerContext)exec.getDesktop().getSession().getAttribute(SESSION_CTX);
            if (ctx == null)
            {
            	ctx = ServerContext.newInstance();                
                exec.getDesktop().getSession().setAttribute(SESSION_CTX, ctx);
            }
            else
            {
            	ServerContext.setCurrentInstance(ctx);
            }
            exec.setAttribute(SESSION_CTX, ctx);
        }
    }

    public void cleanup(Execution exec, Execution parent, List errs)
    {
        if (parent == null)
        {
            exec.removeAttribute(SESSION_CTX);
            ServerContext.dispose();
        }        
    }

    public void prepare(Component comp, Event evt)
    {
    }

    public boolean init(Component comp, Event evt)
    {
        ServerContext ctx = (ServerContext) Executions.getCurrent().getAttribute(
                SESSION_CTX);
        ServerContext.setCurrentInstance(ctx);
        
		return true; 
    }

    public void beforeResume(Component comp, Event evt)
    {
    }

    public void afterResume(Component comp, Event evt)
    {
        ServerContext ctx = (ServerContext) Executions.getCurrent().getAttribute(
                SESSION_CTX);
        ServerContext.setCurrentInstance(ctx);
    }

    public void abortResume(Component comp, Event evt)
    {
        // do nothing
    }    
}
