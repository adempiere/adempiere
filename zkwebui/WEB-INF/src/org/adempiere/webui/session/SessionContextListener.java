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

import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.zkoss.util.Locales;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventThreadCleanup;
import org.zkoss.zk.ui.event.EventThreadInit;
import org.zkoss.zk.ui.event.EventThreadResume;
import org.zkoss.zk.ui.sys.DesktopCtrl;
import org.zkoss.zk.ui.sys.ServerPush;
import org.zkoss.zk.ui.util.DesktopCleanup;
import org.zkoss.zk.ui.util.DesktopInit;
import org.zkoss.zk.ui.util.ExecutionCleanup;
import org.zkoss.zk.ui.util.ExecutionInit;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

/**
 *
 * @author <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date Feb 25, 2007
 * @version $Revision: 0.10 $
 */
public class SessionContextListener implements ExecutionInit,
        ExecutionCleanup, EventThreadInit, DesktopCleanup, DesktopInit , EventThreadResume, EventThreadCleanup
{
	public static final String SERVLET_SESSION_ID = "servlet.sessionId";
    public static final String SESSION_CTX = "WebUISessionContext";

    /**
     * @param exec
     * @param parent
     *
     * @see ExecutionInit#init(Execution, Execution)
     */
    public synchronized void init(Execution exec, Execution parent)
    {
        //in servlet thread
        if (parent == null)
        {
            ServerPush serverPush = ((DesktopCtrl)exec.getDesktop()).getServerPush();
            if (serverPush == null || !serverPush.isActive())
            {
                setContextForSession(Executions.getCurrent());
                //set locale
                Locales.setThreadLocal(Env.getLanguage(ServerContext.getCurrentInstance()).getLocale());
            }
        }
    }

    /**
     * Refresh the context from session
     * @param execution
     * @param parent
     * @param errs
     * @see ExecutionCleanup#cleanup(Execution, Execution, List)
     */
    public synchronized void cleanup(Execution execution, Execution parent, List errs)
    {
        if (parent == null)
        {
            ServerPush serverPush = ((DesktopCtrl)execution.getDesktop()).getServerPush();
            if (serverPush == null || !serverPush.isActive())
                setContextForSession(Executions.getCurrent());
            else
                ServerContext.dispose();
        }
    }

    /**
     * @param comp
     * @param evt
     * @see EventThreadInit#prepare(Component, Event)
     */
    public synchronized void prepare(Component comp, Event evt)
    {
        //in servlet thread
        //check is thread local context have been setup
        if (ServerContext.getCurrentInstance().isEmpty() || !isValidContext())
        {
            setContextForSession(Executions.getCurrent());
        }

        //set locale
        Locales.setThreadLocal(Env.getLanguage(ServerContext.getCurrentInstance()).getLocale());
    }

    /**
     * @param comp
     * @param evt
     * @see EventThreadInit#init(Component, Event)
     */
    public synchronized boolean init(Component comp, Event evt)
    {
		return true;
    }

    public synchronized static boolean isValidContext() {
        Execution execution = Executions.getCurrent();
        Properties ctx = ServerContext.getCurrentInstance();
        if (ctx == null)
            return false;

        if (execution == null || execution.getDesktop() == null)
            return false;

        Session session = execution.getDesktop().getSession();
        HttpSession httpSession = (HttpSession)session.getNativeSession();
        //verify ctx
        String existsSessionId = ctx.getProperty(SERVLET_SESSION_ID);
        if (existsSessionId == null || httpSession == null || !existsSessionId.equals(httpSession.getId()) )
        {
            return false;
        }

        Properties sessionCtx = null;
        //catch invalidated session exception
        try {
            sessionCtx = (Properties) session.getAttribute(SESSION_CTX);
        } catch (IllegalStateException e) {
            return false;
        }
        if (sessionCtx != null)
        {
            if (Env.getAD_Client_ID(sessionCtx) != Env.getAD_Client_ID(ctx))
            {
                return false;
            }
            if (Env.getAD_User_ID(sessionCtx) != Env.getAD_User_ID(ctx))
            {
                return false;
            }
            if (Env.getAD_Role_ID(sessionCtx) != Env.getAD_Role_ID(ctx))
            {
                return false;
            }
        }

        return true;
    }

    /**
     * @param comp
     * @param evt
     * @see EventThreadResume#beforeResume(Component, Event)
     */
    public synchronized void beforeResume(Component comp, Event evt)
    {
    }

    /**
     * @param comp
     * @param evt
     * @see EventThreadResume#afterResume(Component, Event)
     */
    public synchronized void afterResume(Component comp, Event evt)
    {
    	Properties ctx = ServerContext.getCurrentInstance();
        if (ctx == null)
    		ServerContext.dispose();
        else
            setContextForSession(Executions.getCurrent());
        //set locale
        Locales.setThreadLocal(Env.getLanguage(ctx).getLocale());

    }

    /**
     * @param comp
     * @param evt
     * @see EventThreadResume#abortResume(Component, Event)
     */
    public synchronized void abortResume(Component comp, Event evt)
    {
    }

    /**
     * @param comp
     * @param evt
     * @param errs
     * @see EventThreadCleanup#cleanup(Component, Event, List)
     */
	public synchronized void cleanup(Component comp, Event evt, List errs) throws Exception
	{
        ServerPush serverPush = ((DesktopCtrl)Executions.getCurrent().getDesktop()).getServerPush();
        if (serverPush == null || !serverPush.isActive())
            setContextForSession(Executions.getCurrent());
        else
            ServerContext.dispose();
	}

	/**
	 * @param comp
	 * @param evt
	 * @see EventThreadCleanup#complete(Component, Event)
	 */
	public synchronized void complete(Component comp, Event evt) throws Exception
	{
	}



    @Override
    public synchronized void cleanup(Desktop desktop) throws Exception {
        if(Executions.getCurrent() == null) {
            if (!ServerContext.getCurrentInstance().isEmpty()) {
                ServerContext.dispose();
            }
            return;
        }

        if (ServerContext.getCurrentInstance().isEmpty() || !isValidContext())
        {
            setContextForSession(Executions.getCurrent());
        }
    }

    @Override
    public synchronized void init(Desktop desktop, Object request) throws Exception {
        if(Executions.getCurrent() == null)
            return;

        if (ServerContext.getCurrentInstance().isEmpty() || !isValidContext())
        {
            Session session = desktop.getSession();
            //Setting Ephemeral session
            Optional<Integer> maybeMaxInactiveInterval = Optional.ofNullable((Integer) session.getAttribute("MaxInactiveInterval"));
            if (maybeMaxInactiveInterval.isEmpty()) {
                session.setAttribute("MaxInactiveInterval", session.getMaxInactiveInterval());
                Optional<String> maybeEphemeralMaxInactiveInterval = Optional.of(Ini.getProperty("EphemeralSessionMaxInactiveInterval"));
                maybeEphemeralMaxInactiveInterval
                        .filter(ephemeralMaxInactiveInterval -> !ephemeralMaxInactiveInterval.isEmpty())
                        .ifPresent(ephemeralMaxInactiveInterval ->  session.setMaxInactiveInterval(Integer.parseInt(ephemeralMaxInactiveInterval)));
            }
            setContextForSession(Executions.getCurrent());
        }
    }

    /**
     * get servlet thread local context from session
     * @param execution
     */
    public synchronized static void setContextForSession(Execution execution) {
        Session session = execution.getDesktop().getSession();
        Properties context = null;
        //catch potential Session already invalidated exception
        boolean sessionInvalidated = false;
        try {
            context = (Properties)session.getAttribute(SESSION_CTX);
        } catch (IllegalStateException e) {
            sessionInvalidated = true;
        }
        HttpSession httpSession = sessionInvalidated ? null : (HttpSession)session.getNativeSession();
        //create empty context if there's no valid native session
        if (httpSession == null)
        {
            context = new Properties();
            ServerContext.setCurrentInstance(context);
            return;
        }

        if (context != null)
        {
            //verify ctx
            String cacheId = context.getProperty(SERVLET_SESSION_ID);
            if (cacheId == null || !cacheId.equals(httpSession.getId()) )
            {
                context = null;
                session.removeAttribute(SESSION_CTX);
            }
        }
        if (context == null)
        {
            context = new Properties();
            context.setProperty(SERVLET_SESSION_ID, httpSession.getId());
            session.setAttribute(SESSION_CTX, context);
        }
        ServerContext.setCurrentInstance(context);
    }
}
