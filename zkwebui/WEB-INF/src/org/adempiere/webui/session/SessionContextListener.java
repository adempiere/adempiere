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

import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.zkoss.util.Locales;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Execution;
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
 * @author <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @version $Revision: 0.10 $
 * @date Feb 25, 2007
 */
public class SessionContextListener implements ExecutionInit,
        ExecutionCleanup, EventThreadInit, DesktopCleanup, DesktopInit, EventThreadResume, EventThreadCleanup {

    private static CLogger log = CLogger.getCLogger(SessionContextListener.class);
    public static final String SERVLET_SESSION_ID = "servlet.sessionId";

    /**
     * @param executionCleanup
     * @param parentCleanup
     * @see ExecutionInit#init(Execution, Execution)
     */
    public void init(Execution executionCleanup, Execution parentCleanup) {
        Optional.ofNullable(parentCleanup)
                .flatMap(parent -> Optional.ofNullable(executionCleanup))
                .ifPresent(execution -> {
                    ServerPush serverPush = ((DesktopCtrl) execution.getDesktop()).getServerPush();
                    if (serverPush == null || !serverPush.isActive()) {
                        setContextForSession(execution);
                    }
                });
    }

    /**
     * Refresh the context from session
     *
     * @param executionCleanup
     * @param parentCleanup
     * @param errors
     * @see ExecutionCleanup#cleanup(Execution, Execution, List)
     */
    public void cleanup(Execution executionCleanup, Execution parentCleanup, List errors) {
        ServerContext.dispose();
    }

    /**
     * @param component
     * @param event
     * @see EventThreadInit#prepare(Component, Event)
     */
    public void prepare(Component component, Event event) {
        Optional.ofNullable(component.getDesktop())
                .flatMap(desktop -> Optional.ofNullable(desktop.getExecution()))
                .ifPresent(execution -> {
                    ServerPush serverPush = ((DesktopCtrl) component.getDesktop()).getServerPush();
                    if (serverPush == null || !serverPush.isActive()) {
                        setContextForSession(execution);
                    }
                });
    }

    /**
     * @param component
     * @param event
     * @see EventThreadInit#init(Component, Event)
     */
    public boolean init(Component component, Event event) {
        Optional.ofNullable(component.getDesktop())
                .flatMap(desktop -> Optional.ofNullable(desktop.getExecution()))
                .ifPresent(execution -> {
                    ServerPush serverPush = ((DesktopCtrl) component.getDesktop()).getServerPush();
                    if (serverPush == null || !serverPush.isActive()) {
                        setContextForSession(execution);
                    }
                });
        return true;
    }

    /**
     * @param component
     * @param event
     * @see EventThreadResume#beforeResume(Component, Event)
     */
    public void beforeResume(Component component, Event event) {
        Optional.ofNullable(component.getDesktop()).ifPresent(desktop ->
                Optional.ofNullable(desktop.getExecution()).ifPresent(execution -> {
                    ServerPush serverPush = ((DesktopCtrl) desktop).getServerPush();
                    if (serverPush == null || !serverPush.isActive()) {
                        setContextForSession(execution);
                    }
                }));
    }

    /**
     * @param component
     * @param event
     * @see EventThreadResume#afterResume(Component, Event)
     */
    public void afterResume(Component component, Event event) {
        Optional.ofNullable(component.getDesktop()).ifPresent(desktop ->
                Optional.ofNullable(desktop.getExecution()).ifPresent(execution -> {
                    ServerPush serverPush = ((DesktopCtrl) desktop).getServerPush();
                    if (serverPush == null || !serverPush.isActive()) {
                        setContextForSession(execution);
                    }
                }));
    }

    /**
     * @param component
     * @param event
     * @see EventThreadResume#abortResume(Component, Event)
     */
    public void abortResume(Component component, Event event) {
        Optional.ofNullable(component.getDesktop()).ifPresent(desktop ->
                Optional.ofNullable(desktop.getExecution()).ifPresent(execution -> {
                    ServerPush serverPush = ((DesktopCtrl) desktop).getServerPush();
                    if (serverPush == null || !serverPush.isActive()) {
                        setContextForSession(execution);
                    }
                }));
    }

    /**
     * @param component
     * @param event
     * @param errors
     * @see EventThreadCleanup#cleanup(Component, Event, List)
     */
    public void cleanup(Component component, Event event, List errors) throws Exception {
        ServerContext.dispose();
    }

    /**
     * @param component
     * @param event
     * @see EventThreadCleanup#complete(Component, Event)
     */
    public void complete(Component component, Event event) throws Exception {
        Optional.ofNullable(component.getDesktop()).ifPresent(desktop ->
                Optional.ofNullable(desktop.getExecution()).ifPresent(execution -> {
            ServerPush serverPush = ((DesktopCtrl) component.getDesktop()).getServerPush();
            if (serverPush == null || !serverPush.isActive()) {
                if (!isValidContext(execution)) {
                    setContextForSession(execution);
                }
            }
        }));
    }

    /**
     *
     * @param desktopClean
     * @throws Exception
     */
    @Override
    public void cleanup(Desktop desktopClean) throws Exception {
        ServerContext.dispose();
    }

    /**
     *
     * @param desktopInit
     * @param request
     * @throws Exception
     */
    @Override
    public void init(Desktop desktopInit, Object request) throws Exception {
        Optional.ofNullable(desktopInit).ifPresent(desktop -> {
            ServerPush serverPush = ((DesktopCtrl) desktop).getServerPush();
            if (serverPush == null || !serverPush.isActive()) {
                setContextForSession(desktop.getExecution());
            } else
                ServerContext.dispose();
        });
    }

    /**
     * Validate Context
     *
     * @param execution
     * @return Isvalid
     */
    public static boolean isValidContext(Execution execution) {
        Properties ctx = ServerContext.getCurrentInstance();
        if (ctx == null)
            return false;

        if (execution == null || execution.getDesktop() == null)
            return false;

        Session session = execution.getDesktop().getSession();
        HttpSession httpSession = (HttpSession) session.getNativeSession();
        //verify ctx
        String existsSessionId = ctx.getProperty(SERVLET_SESSION_ID);
        if (existsSessionId == null || httpSession == null || !existsSessionId.equals(httpSession.getId())) {
            return false;
        }

        Optional<Properties> maybeSessionContext = Optional.of(SessionManager.getSessionContext(httpSession.getId()));
        return maybeSessionContext.map(sessionContext -> {
            if (Env.getAD_Client_ID(sessionContext) != Env.getAD_Client_ID(ctx)) {
                return false;
            }
            if (Env.getAD_User_ID(sessionContext) != Env.getAD_User_ID(ctx)) {
                return false;
            }
            if (Env.getAD_Role_ID(sessionContext) != Env.getAD_Role_ID(ctx)) {
                return false;
            }
            return true;
        }).orElse(true);
    }

    /**
     * get servlet thread local context from session
     *
     * @param execution Execution
     */
    public synchronized static void setContextForSession(Execution execution) {
        Session session = execution.getDesktop().getSession();
        HttpSession httpSession = (HttpSession) session.getNativeSession();
        ServerContext.setCurrentInstance(SessionManager.getSessionContext(httpSession.getId()));
        Locales.setThreadLocal(Env.getLanguage(ServerContext.getCurrentInstance()).getLocale());
    }
}
