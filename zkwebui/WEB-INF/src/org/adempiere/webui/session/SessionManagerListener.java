/**
 * Copyright (C) 2003-2022, e-Evolution, http://www.e-evolution.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * Created by victor.perez@e-evolution.com , www.e-evolution.com
 */

package org.adempiere.webui.session;

import org.compiere.util.CLogger;
import org.compiere.util.Ini;
import org.compiere.util.SecureInterface;
import org.zkoss.zk.ui.http.HttpSessionListener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import java.util.Enumeration;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;


public class SessionManagerListener extends HttpSessionListener {

    private static CLogger log = CLogger.getCLogger(SessionManagerListener.class);

    /** AdempiereEnv.properties key: inactivity timeout (seconds) of a new, still anonymous session */
    public static final String EPHEMERAL_SESSION_MAX_INACTIVE_INTERVAL = "EPHEMERAL_SESSION_MAX_INACTIVE_INTERVAL";
    /**
     * Session attribute (internal, not configurable): backup of the container timeout (web.xml session-timeout)
     * taken before the ephemeral interval is applied; AdempiereWebUI.loginCompleted restores it after login
     */
    public static final String MAX_INACTIVE_INTERVAL = "MAX_INACTIVE_INTERVAL";

    /**
     * Get the ephemeral max inactive interval of a new session.
     * The raw property value is read on purpose: Ini.getProperty tries to decrypt every value and turns a
     * plain number such as 300 into "". Accepts "300" and the clear-value form "xyz300"; an empty,
     * non-numeric or non-positive value is ignored, so the container timeout (web.xml) applies.
     * @param properties Ini properties
     * @return interval in seconds, if configured
     */
    static Optional<Integer> getEphemeralMaxInactiveInterval(Properties properties) {
        if (properties == null)
            return Optional.empty();
        return Optional.ofNullable(properties.getProperty(EPHEMERAL_SESSION_MAX_INACTIVE_INTERVAL))
                .map(String::trim)
                .filter(value -> !value.isEmpty())
                .flatMap(SessionManagerListener::parseSeconds);
    }

    private static Optional<Integer> parseSeconds(String value) {
        String clearValue = value.startsWith(SecureInterface.CLEARVALUE_START)
                ? value.substring(SecureInterface.CLEARVALUE_START.length()).trim()
                : value;
        try {
            int seconds = Integer.parseInt(clearValue);
            if (seconds > 0)
                return Optional.of(seconds);
        } catch (NumberFormatException e) {
            // reported below
        }
        log.warning(EPHEMERAL_SESSION_MAX_INACTIVE_INTERVAL + " ignored, a positive number of seconds is expected: " + value);
        return Optional.empty();
    }

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        super.sessionCreated(httpSessionEvent);
        final HttpSession httpSession = httpSessionEvent.getSession();
        log.info("        Create Session Id : " + httpSession.getId());
        log.info("------------------------------------------------");
        log.info("             Event Source : " + httpSessionEvent.getSource());
        log.info("            Session Cache : " +  SessionManager.getSessionCache().size());
        log.info("    Session Context Cache : " +  SessionManager.getSessionContextCache().size());
        log.info("        Application Cache : " +  SessionManager.getAppicationCache().size());
        log.info("            Desktop Cache : " +  SessionManager.getDesktopCache().size());
        log.info("Execution CarryOver Cache : " +  SessionManager.getExecutionCarryOverCache().size());
        log.info("    User Preference Cache : " +  SessionManager.getSessionUserPreferenceCache().size());
        log.info("User Authentication Cache : " +  SessionManager.getUserAuthenticationCache().size());
        log.info("------------------------------------------------");
        log.info(" ");
        //Setting Ephemeral session
        Optional<Integer> maybeMaxInactiveInterval = Optional.ofNullable((Integer) httpSession.getAttribute(MAX_INACTIVE_INTERVAL));
        if (maybeMaxInactiveInterval.isEmpty()) {
            httpSession.setAttribute(MAX_INACTIVE_INTERVAL, httpSession.getMaxInactiveInterval());
            getEphemeralMaxInactiveInterval(Ini.getProperties())
                    .ifPresent(ephemeralMaxInactiveInterval -> {
                        // Keep the container timeout stored in MaxInactiveInterval: loginCompleted restores it
                        httpSession.setMaxInactiveInterval(ephemeralMaxInactiveInterval);
                        log.log(Level.INFO, "Ephemeral Session Max Inactive Interval = " + ephemeralMaxInactiveInterval);
                    });
        }
        log.info(" Max Inactive Interval : " +  httpSession.getMaxInactiveInterval());
        Enumeration<String> attributeNames = httpSession.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attrubuteName = attributeNames.nextElement();
            log.info(" Attribute Name : " + attrubuteName +  " - Value : " + httpSession.getAttribute(attrubuteName));
        }
        log.info(" ");
        SessionManager.createSession(httpSession);
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        super.sessionDestroyed(httpSessionEvent);
        final HttpSession httpSession = httpSessionEvent.getSession();
        log.info(" Destroyed Session Id : " + httpSession.getId());
        log.info("------------------------------------------------");
        if (SessionManager.existsSession(httpSession.getId())){
            SessionManager.clearSession(httpSession.getId());
            SessionManager.removeUserAuthentication(httpSession.getId());
            SessionManager.removeSessionUserPreference(httpSession.getId());
            SessionManager.cleanSessionBackground(httpSession.getId());
            SessionManager.removeSession(httpSession.getId());
            log.info("             Event Source : " + httpSessionEvent.getSource());
            log.info("            Session Cache : " + SessionManager.getSessionCache().size());
            log.info("    Session Context Cache : " + SessionManager.getSessionContextCache().size());
            log.info("        Application Cache : " + SessionManager.getAppicationCache().size());
            log.info("            Desktop Cache : " + SessionManager.getDesktopCache().size());
            log.info("Execution CarryOver Cache : " + SessionManager.getExecutionCarryOverCache().size());
            log.info("    User Preference Cache : " + SessionManager.getSessionUserPreferenceCache().size());
            log.info("User Authentication Cache : " + SessionManager.getUserAuthenticationCache().size());
        }
        log.info("       Invalidate Session : " + httpSession.getId());
        log.info("------------------------------------------------");
        httpSession.invalidate();
    }
}
