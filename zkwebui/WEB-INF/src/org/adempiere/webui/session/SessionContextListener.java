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
import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.compiere.util.Env;
import org.zkoss.util.Locales;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventThreadCleanup;
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
        ExecutionCleanup, EventThreadInit, EventThreadResume, EventThreadCleanup
{
	public static final String SERVLET_SESSION_ID = "servlet.sessionId";
    public static final String SESSION_CTX = "WebUISessionContext";

    /**
     * @param exec
     * @param parent
     *
     * @see ExecutionInit#init(Execution, Execution)
     */
    public void init(Execution exec, Execution parent)
    {
        if (parent == null)
        {
        	Properties ctx = (Properties)exec.getDesktop().getSession().getAttribute(SESSION_CTX);
            if (ctx == null)
            {
            	ctx = new Properties();
            	HttpSession session = (HttpSession)exec.getDesktop().getSession().getNativeSession();
            	ctx.setProperty(SERVLET_SESSION_ID, session.getId());
                exec.getDesktop().getSession().setAttribute(SESSION_CTX, ctx);
            }
            
            ServerContext serverContext = ServerContext.getCurrentInstance();
            if (serverContext == null)
            {
            	serverContext = ServerContext.newInstance();
            }
            serverContext.clear();
            serverContext.putAll(ctx);

            //set locale
            Locales.setThreadLocal(Env.getLanguage(ctx).getLocale());
        }
    }

    /**
     * @param exec
     * @param parent
     * @param errs
     * @see ExecutionCleanup#cleanup(Execution, Execution, List)
     */
    public void cleanup(Execution exec, Execution parent, List errs)
    {
        if (parent == null)
        {
            ServerContext.dispose();
        }
    }

    /**
     * @param comp
     * @param evt
     * @see EventThreadInit#prepare(Component, Event)
     */
    public void prepare(Component comp, Event evt)
    {
    }

    /**
     * @param comp
     * @param evt
     * @see EventThreadInit#init(Component, Event)
     */
    public boolean init(Component comp, Event evt)
    {
    	//setup context
    	Properties ctx = (Properties)Executions.getCurrent().getDesktop().getSession().getAttribute(SESSION_CTX);
        if (ctx == null)
        {
        	ctx = new Properties();
        	HttpSession session = (HttpSession)Executions.getCurrent().getDesktop().getSession().getNativeSession();
        	ctx.setProperty(SERVLET_SESSION_ID, session.getId());
        	Executions.getCurrent().getDesktop().getSession().setAttribute(SESSION_CTX, ctx);
        }
        
        ServerContext serverContext = ServerContext.getCurrentInstance();
        if (serverContext == null)
        {
        	serverContext = ServerContext.newInstance();
        }
        serverContext.clear();
        serverContext.putAll(ctx);

        //set locale
        Locales.setThreadLocal(Env.getLanguage(ctx).getLocale());
        
		return true;
    }

    /**
     * @param comp
     * @param evt
     * @see EventThreadResume#beforeResume(Component, Event)
     */
    public void beforeResume(Component comp, Event evt)
    {
    }

    /**
     * @param comp
     * @param evt
     * @see EventThreadResume#afterResume(Component, Event)
     */
    public void afterResume(Component comp, Event evt)
    {
    	//make sure context is correct
    	Properties ctx = (Properties)Executions.getCurrent().getDesktop().getSession().getAttribute(SESSION_CTX);
    	if (ctx != null)
    	{
    		ServerContext serverContext = ServerContext.getCurrentInstance();
    		if (serverContext == null) {
    			serverContext = ServerContext.newInstance();
    			serverContext.putAll(ctx);
    		} else {
    			serverContext.clear();
    			serverContext.putAll(ctx);
    		}
    	}
    }

    /**
     * @param comp
     * @param evt
     * @see EventThreadResume#abortResume(Component, Event)
     */
    public void abortResume(Component comp, Event evt)
    {
    }

    /**
     * @param comp
     * @param evt
     * @param errs
     * @see EventThreadCleanup#cleanup(Component, Event, List)
     */
	public void cleanup(Component comp, Event evt, List errs) throws Exception 
	{
		//update session context
    	Properties ctx = (Properties) Executions.getCurrent().getDesktop().getSession().getAttribute(SESSION_CTX);
    	ServerContext serverContext = ServerContext.getCurrentInstance();
    	
    	if (ctx != null && serverContext != null 
    		&& ctx.getProperty(SERVLET_SESSION_ID).equals(serverContext.getProperty(SERVLET_SESSION_ID)))
    	{
    		ctx.clear();
    		ctx.putAll(serverContext);
    	}
    	
    	ServerContext.dispose();
	}

	/**
	 * @param comp
	 * @param evt
	 * @see EventThreadCleanup#complete(Component, Event) 
	 */
	public void complete(Component comp, Event evt) throws Exception 
	{
	}
}
