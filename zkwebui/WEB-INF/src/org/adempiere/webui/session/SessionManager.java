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
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.desktop.IDesktop;
import org.adempiere.webui.util.UserPreference;
import org.compiere.model.MSession;
import org.compiere.model.MUser;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.zkoss.web.Attributes;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.event.Events;

import javax.servlet.http.HttpSession;
import java.lang.ref.WeakReference;
import java.util.Hashtable;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;

import static org.adempiere.webui.session.SessionContextListener.SESSION_CTX;

/**
 * 
 * @author <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date Feb 25, 2007
 * @version $Revision: 0.10 $
 */
public class SessionManager
{

	public static final String SESSION_APPLICATION = "SessionApplication";
	public static final String SESSION_USER_PREFERENCE = "SessionUserPreference";

    private static CLogger log = CLogger.getCLogger(SessionManager.class);

	private static final Hashtable<String, WeakReference<Session>> sessionContainer = new Hashtable<>();

    public synchronized static boolean isUserLoggedIn(Properties ctx)
    {
        String adUserId = Env.getContext(ctx, "#AD_User_ID");
        String adRoleId = Env.getContext(ctx, "#AD_Role_ID");
        String adClientId = Env.getContext(ctx, "#AD_Client_ID");
        String adOrgId = Env.getContext(ctx, "#AD_Org_ID");

        return (!"".equals(adUserId) && !"".equals(adRoleId)
                && !"".equals(adClientId) && !"".equals(adOrgId));
    }

	public synchronized static Session getSession() {
		return Executions.getCurrent().getDesktop().getSession();
	}

	public synchronized static void setApplicationToSession(IWebClient app) {
		Desktop desktop = AEnv.getDesktop();
		if (desktop != null) {
			desktop.setAttribute(SESSION_APPLICATION, new WeakReference<>(app));
		} else {
			log.severe("Unable to save session application on desktop");
		}
	}

	public synchronized static void removeApplicationToSession()
	{
		Session session = getSession();
		session.removeAttribute(SESSION_APPLICATION);
	}

	public synchronized static IDesktop getAppDesktop()
	{
		IWebClient webClient = getApplication();
		return webClient != null ? webClient.getApplicationDesktop() : null;
	}

	public synchronized static IWebClient getApplication() {
		Desktop desktop = AEnv.getDesktop();
		if (desktop != null) {
			IWebClient appplication = null;
			@SuppressWarnings("unchecked")
			WeakReference<IWebClient> weakReference = (WeakReference<IWebClient>) desktop.getAttribute(SESSION_APPLICATION);
			appplication = weakReference != null ? weakReference.get() : null;
			if (appplication != null) {
				// Set Properties based on Session Context
				Properties context = (Properties) desktop.getSession().getAttribute(SESSION_CTX);
				ServerContext.setCurrentInstance(context);
			}
			return appplication;
		}
		return null;
	}

	public synchronized static void changeRole(MUser user)
	{
		IWebClient appplication  = getApplication();
		if (appplication != null)
			appplication.changeRole(user);
	}

    public synchronized static boolean activateDesktop(Desktop desktop)
     {
 		if (Events.inEventListener()) {
 			return true;
 		}
    	if (desktop ==  null) {
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

    public synchronized static void releaseDesktop(Desktop desktop)
	{
		if (desktop  == null) {
			return;
		}
		Executions.deactivate((org.zkoss.zk.ui.Desktop) desktop);
//		if (Env.getContext(Env.getCtx(), ZK_DESKTOP_ISACTIVE).equals("Y")) {
//			Env.setContext(Env.getCtx(), ZK_DESKTOP_ISACTIVE, "N");
//		}
		
	}

	public synchronized static Hashtable<String, WeakReference<Session>> getSessionContainer() {
		return sessionContainer;
	}

	public synchronized static void removeSession(String sessionId) {
    	if (sessionContainer.containsKey(sessionId))
			sessionContainer.remove(sessionId);
		else throw new AdempiereException("Application not exist with this Id :" + sessionId);
	}

	public synchronized static void addSession(Session session) {
		HttpSession httpSession = (HttpSession) session.getNativeSession();
		if (!sessionContainer.containsKey(httpSession.getId()))
			sessionContainer.put(httpSession.getId(), new WeakReference<>(session));
	}

	public synchronized static Session getSession(String sessionId) {
		if (sessionContainer.containsKey(sessionId)) {
			return sessionContainer.get(sessionId).get();
		}
		else {
			throw new AdempiereException("Session not exist");
		}
	}

	public synchronized static void loadUserPreference(Integer authenticatedUserId) {
		UserPreference userPreference = new UserPreference();
		userPreference.loadPreference(authenticatedUserId);
		getSession().setAttribute(SESSION_USER_PREFERENCE, userPreference);
	}

	public synchronized static void loadUserPreference(String httpSessionId, Integer authenticatedUserId) {
			UserPreference userPreference = new UserPreference();
			userPreference.loadPreference(authenticatedUserId);
			getSession(httpSessionId).setAttribute(SESSION_USER_PREFERENCE, userPreference);
	}

	public synchronized static void RemoveUserPreference(String sessionId) {
		getSession().removeAttribute(SESSION_USER_PREFERENCE);
	}

	public synchronized static UserPreference getUserPreference() {
		UserPreference userPreference =  (UserPreference) getSession().getAttribute(SESSION_USER_PREFERENCE);
		if (userPreference == null)
			throw new AdempiereException("User Preference not load for this session");
		return userPreference;
	}

	public synchronized static UserPreference getUserPreference(String sessionId) {
		return (UserPreference) getSession(sessionId).getAttribute(SESSION_USER_PREFERENCE);
	}

	public synchronized static void clearSessions() {
		sessionContainer.clear();
	}

	public synchronized static void clearSession(String sessionId) {
		Session session =  getSession(sessionId);
		HttpSession httpSession = (HttpSession) session.getNativeSession();
		Optional<IWebClient> maybeApplication = Optional.ofNullable(getApplication());
		maybeApplication.ifPresent(application -> {
			int adempiereSessionId = Env.getContextAsInt(Env.getCtx(), "#AD_Session_ID");
			if (adempiereSessionId > 0) {
				MSession adempiereSession = MSession.get(Env.getCtx(), session.getRemoteAddr(), session.getRemoteHost(), httpSession.getId());
				adempiereSession.logout();
				log.log(Level.INFO, "ADempiere Session " + httpSession.getId() + " Logout ...");
			}
			application.logoutDestroyed();
			session.removeAttribute(SESSION_CTX);
			session.removeAttribute(SESSION_USER_PREFERENCE);
			session.removeAttribute("Check_AD_User_ID");
			session.removeAttribute(Attributes.PREFERRED_LOCALE);
			session.removeAttribute(SESSION_APPLICATION);
		});
		session.invalidate();
		log.log(Level.INFO, "Session " + httpSession.getId() + " Invalidate ...");
	}
}
