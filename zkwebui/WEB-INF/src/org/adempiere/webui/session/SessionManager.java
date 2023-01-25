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

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.webui.IWebClient;
import org.adempiere.webui.desktop.IDesktop;
import org.adempiere.webui.util.UserPreference;
import org.compiere.model.MSession;
import org.compiere.model.MUser;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.SecureEngine;
import org.zkforge.keylistener.Keylistener;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.impl.ExecutionCarryOver;

import javax.servlet.http.HttpSession;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import static org.adempiere.webui.session.SessionContextListener.SERVLET_SESSION_ID;

/**
 * @author <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @version $Revision: 0.10 $
 * @date Feb 25, 2007
 */
public class SessionManager {

    private static final CLogger log = CLogger.getCLogger(SessionManager.class);

    private static final Map<String, WeakReference<IWebClient>> applicationCache = Collections.synchronizedMap(new Hashtable<>());
    private static final Map<String, WeakReference<IDesktop>> desktopCache = Collections.synchronizedMap(new Hashtable<>());
    private static final Map<String, HttpSession> sessionCache = Collections.synchronizedMap(new Hashtable<>());
    private static final Map<String, Properties> sessionContextCache = Collections.synchronizedMap(new Hashtable<>());
    private static final Map<String, ExecutionCarryOver> executionCarryOverCache = Collections.synchronizedMap(new Hashtable<>());
    private static final Map<String, UserPreference> sessionUserPreferenceCache = Collections.synchronizedMap(new Hashtable<>());
    private static final Map<String, String> userAuthenticationCache = Collections.synchronizedMap(new Hashtable<>());

    public static boolean isUserLoggedIn(Properties ctx) {
        String adUserId = Env.getContext(ctx, "#AD_User_ID");
        String adRoleId = Env.getContext(ctx, "#AD_Role_ID");
        String adClientId = Env.getContext(ctx, "#AD_Client_ID");
        String adOrgId = Env.getContext(ctx, "#AD_Org_ID");

        return (!"".equals(adUserId) && !"".equals(adRoleId)
                && !"".equals(adClientId) && !"".equals(adOrgId));
    }

    public static void changeRole(MUser user) {
        Optional.ofNullable(getApplication()).ifPresent(application -> application.changeRole(user));
    }

    public static boolean activateDesktop(Desktop desktop) {
        if (Events.inEventListener()) {
            return true;
        }
        if (desktop == null) {
            log.severe("Attempted to activate NULL desktop.");
            return false;
        }

        try {
            if (Executions.activate((org.zkoss.zk.ui.Desktop) desktop, 500)) {
                return true;
            } else {
                log.fine("Unable to grab control of desktop.");
                if (!desktop.isServerPushEnabled()) {
                    log.severe("Unexpected. Server Push not enabled");
                }
            }
        } catch (Exception e) {
            log.severe(e.getMessage());
        } finally {
        }
        return false;
    }

    public static void releaseDesktop(Desktop desktop) {
        if (desktop == null) {
            return;
        }
        Executions.deactivate((org.zkoss.zk.ui.Desktop) desktop);
    }

    public static void createSession(HttpSession httpSession) {
        if (!sessionCache.containsKey(httpSession.getId())) {
            sessionCache.put(httpSession.getId(), httpSession);
            createSessionContext(httpSession.getId());
        }
    }

    public static HttpSession getSession(String sessionId) {
        if (sessionCache.containsKey(sessionId)) {
            return sessionCache.get(sessionId);
        } else {
            throw new AdempiereException("Session not exist");
        }
    }

    public static boolean existsSession(String sessionId) {
        return sessionCache.containsKey(sessionId);
    }

    public static Map<String, HttpSession> getSessionCache() {
        return sessionCache;
    }

    public static void clearSession(String sessionId) {
        Optional.ofNullable(getApplication()).ifPresent(application -> {
            int adempiereSessionId = Env.getContextAsInt(Env.getCtx(), "#AD_Session_ID");
            if (adempiereSessionId > 0) {
                MSession adempiereSession = new MSession(Env.getCtx(), adempiereSessionId, null);
                adempiereSession.logout();
                log.info("ADempiere Session " + sessionId + " Logout ...");
            }
        });
    }

    public static void removeSession(String sessionId) {
        Optional.ofNullable(sessionCache.get(sessionId))
                .ifPresent(session -> {
                    sessionCache.remove(sessionId);
                    removeSessionCache(sessionId);
                });
    }

    public static void cleanSessionBackground(String sessionId) {
        Optional.ofNullable(getApplication()).ifPresent(application -> {
            Keylistener keyListenerApplication = application.getKeylistener();
            //stop key listener
            if (keyListenerApplication != null) {
                keyListenerApplication.detach();
                keyListenerApplication = null;
            }
            // stop background thread
            IDesktop dashboard = application.getApplicationDesktop();
            if (dashboard != null) {
                dashboard.logout();
            }

            // clear remove all children and root component
            application.getChildren().clear();
            application.getPage().removeComponents();
            application.detach();
            application.clearDesktop();
        });
        //Clean the current context
        SessionManager.removeExecutionCarryOver(sessionId);
        SessionManager.removeDestop(sessionId);
        SessionManager.removeApplication(sessionId);
    }

    public static void createSessionContext(String sessionId) {
        Properties context = new Properties();
        context.put(SERVLET_SESSION_ID, sessionId);
        sessionContextCache.put(sessionId, context);
    }

    public static Properties getSessionContext(String sessionId) {
        return sessionContextCache.get(sessionId);
    }

    public static boolean containsKeySessionContext(String sessionId) {
        return sessionContextCache.containsKey(sessionId);
    }

    public static void removeSessionContext(String sessionId) {
        Optional.ofNullable(sessionContextCache.get(sessionId))
                .ifPresent(context -> {
                    sessionContextCache.remove(sessionId);
                    context = null;
                });
    }

    public static Map<String, Properties> getSessionContextCache() {
        return sessionContextCache;
    }

    public static void setApplication(String sessionId, IWebClient application) {
        applicationCache.put(sessionId, new WeakReference<>(application));
    }

    public static IWebClient getApplication(String sessionId) {
        return applicationCache.get(sessionId).get();
    }

    public static IWebClient getApplication() {
        String sessionId = Env.getContext(Env.getCtx(), SERVLET_SESSION_ID);
        WeakReference<IWebClient> applicationReference = applicationCache.get(sessionId);
        if (applicationReference == null)
            return null;
        return applicationReference.get();
    }

    public static void removeApplication(String sessionId) {
        Optional.ofNullable(applicationCache.get(sessionId))
                .ifPresent(applicationReference ->
                        Optional.ofNullable(applicationReference.get())
                                .ifPresentOrElse(
                                        // Then
                                        application -> {
                                            applicationCache.remove(sessionId);
                                            application.getChildren().clear();
                                            application.clearDesktop();
                                            application = null;
                                        }
                                        , // Else
                                        () -> applicationCache.remove(sessionId)
                                )
                );
    }

    public static void setApplicationDesktop(String sessionId, IDesktop desktop) {
        desktopCache.put(sessionId, new WeakReference<>(desktop));
    }

    public static IDesktop getApplicationDesktop(String sessionId) {
        WeakReference<IDesktop> desktopReference = desktopCache.get(sessionId);
        if (desktopReference == null)
            return null;
        else
            return desktopReference.get();
    }

    public static IDesktop getAppDesktop() {
        return getApplication().getApplicationDesktop();
    }

    public static void removeDestop(String sessionId) {
        Optional.ofNullable(desktopCache.get(sessionId))
                .ifPresent(desktopReference ->
                        Optional.ofNullable(desktopReference.get())
                                .ifPresentOrElse(
                                        // Then
                                        desktop -> {
                                            desktopCache.remove(sessionId);
                                            desktop = null;
                                        }
                                        ,   // Else
                                        () -> desktopCache.remove(sessionId)
                                )
                );
    }

    public static Map<String, WeakReference<IDesktop>> getDesktopCache() {
        return desktopCache;
    }

    public static void setExecutionCarryOverCache(String sessionId, ExecutionCarryOver executionCarryOver) {
        executionCarryOverCache.put(sessionId,executionCarryOver);
    }

    public static ExecutionCarryOver getExecutionCarryOver(String sessionId) {
        return executionCarryOverCache.get(sessionId);
    }

    public static boolean existsExecutionCarryOver(String sessionId) {
        return executionCarryOverCache.containsKey(sessionId);
    }

    public static Map<String, ExecutionCarryOver> getExecutionCarryOverCache() {
        return executionCarryOverCache;
    }

    public static void removeExecutionCarryOver(String sessionId) {
        Optional.ofNullable(executionCarryOverCache.get(sessionId))
                .ifPresentOrElse(
                        // Then
                        executionCarryOver -> {
                            executionCarryOverCache.remove(sessionId);
                            executionCarryOver = null;
                        }
                        , // Else
                        () -> executionCarryOverCache.remove(sessionId)
                );

    }

    public static void loadUserPreference(Integer authenticatedUserId) {
        String sessionId = Env.getContext(Env.getCtx(), SERVLET_SESSION_ID);
        UserPreference userPreference = new UserPreference();
        userPreference.loadPreference(authenticatedUserId);
        sessionUserPreferenceCache.put(sessionId, userPreference);
    }

    public static UserPreference getUserPreference() {
        String sessionId = Env.getContext(Env.getCtx(), SERVLET_SESSION_ID);
        return getUserPreference(sessionId);
    }

    public static UserPreference getUserPreference(String sessionId) {
        return sessionUserPreferenceCache.get(sessionId);
    }

    public static void removeSessionUserPreference(String sessionId) {
        Optional.ofNullable(sessionUserPreferenceCache.get(sessionId))
                .ifPresentOrElse(
                        userPreference -> {
                            // Then
                            sessionUserPreferenceCache.remove(sessionId);
                            userPreference = null;
                        }
                        ,   // Else
                        () -> sessionUserPreferenceCache.remove(sessionId)
                );
    }

    public static Map<String, UserPreference> getSessionUserPreferenceCache() {
        return sessionUserPreferenceCache;
    }

    public static void setUserAuthentication(String sessionId, String authentication) {
        userAuthenticationCache.put(sessionId, SecureEngine.encrypt(authentication));
    }

    public static String getUserAuthentication(String sessionId) {
        return SecureEngine.decrypt(userAuthenticationCache.get(sessionId));
    }

    public static void removeUserAuthentication(String sessionId) {
        Optional.ofNullable(userAuthenticationCache.get(sessionId))
                .ifPresent(userAuthentication -> {
                    userAuthenticationCache.remove(sessionId);
                    userAuthentication = null;
                });
    }

    public static Map<String, String> getUserAuthenticationCache() {
        return userAuthenticationCache;
    }

    public static Map<String, WeakReference<IWebClient>> getAppicationCache() {
        return applicationCache;
    }

    public static void removeSessionCache(String sessionId) {
        removeSessionUserPreference(sessionId);
        removeDestop(sessionId);
        removeExecutionCarryOver(sessionId);
        removeApplication(sessionId);
        removeSessionContext(sessionId);
        removeUserAuthentication(sessionId);
    }
}
