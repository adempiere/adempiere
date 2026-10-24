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

import org.compiere.model.MSysConfig;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.sys.SessionCtrl;

import java.util.function.IntSupplier;
import java.util.function.Supplier;

/**
 * Suspends the ZK session inactivity timeout while a long synchronous task (process, report) runs.
 * <p>
 * ZK records the client activity at the START of an AU request, so a task that runs longer than the
 * session timeout gets the session invalidated by the next request that is not a keep-alive
 * (server push poll, another tab). While at least one task is running the max inactive interval is
 * set to -1 (ZK skips the inactivity check for negative values); when the last one ends the
 * interval is restored and the inactivity clock restarts.
 * <p>
 * A task that runs longer than the AD_SysConfig value {@value #ZK_LONG_TASK_WARNING_SECONDS}
 * (default 300 seconds, a value <= 0 disables the warning) is reported with a WARNING that names the
 * task, the user, the duration and the threshold. A WARNING is also logged when the session was
 * invalidated while the task ran. Logging never masks the outcome of the task nor the restore.
 */
public final class SessionTimeoutSuspender {

    /** AD_SysConfig key: seconds after which a suspended task is reported, <= 0 disables the warning */
    public static final String ZK_LONG_TASK_WARNING_SECONDS = "ZK_LONG_TASK_WARNING_SECONDS";
    private static final int DEFAULT_WARNING_SECONDS = 300;

    private static final CLogger logger = CLogger.getCLogger(SessionTimeoutSuspender.class);
    private static final String RUNNING_COUNT = SessionTimeoutSuspender.class.getName() + ".RUNNING_COUNT";
    private static final String SAVED_INTERVAL = SessionTimeoutSuspender.class.getName() + ".SAVED_INTERVAL";

    private SessionTimeoutSuspender() {
    }

    /**
     * Run a task in the current ZK session
     * @param taskName task label for the log, evaluated after the work ends (it can include data created by the work)
     * @param work task to run
     */
    public static void run(Supplier<String> taskName, Runnable work) {
        run(Sessions.getCurrent(), taskName, work, SessionTimeoutSuspender::readWarningThreshold);
    }

    /**
     * Run a task without session expiration; exceptions of the task propagate unchanged
     * @param session ZK session, if null the task runs unguarded
     * @param taskName task label for the log, evaluated after the work ends
     * @param work task to run
     * @param warningThresholdSeconds seconds after which the task is reported, <= 0 disables the warning
     */
    static void run(Session session, Supplier<String> taskName, Runnable work, int warningThresholdSeconds) {
        run(session, taskName, work, () -> warningThresholdSeconds);
    }

    private static void run(Session session, Supplier<String> taskName, Runnable work, IntSupplier warningThreshold) {
        if (session == null) {
            work.run();
            return;
        }
        synchronized (session) {
            Integer running = (Integer) session.getAttribute(RUNNING_COUNT);
            if (running == null || running == 0) {
                session.setAttribute(SAVED_INTERVAL, session.getMaxInactiveInterval());
                session.setMaxInactiveInterval(-1);
                running = 0;
            }
            session.setAttribute(RUNNING_COUNT, running + 1);
        }
        long start = System.nanoTime();
        try {
            work.run();
        } finally {
            boolean invalidated = false;
            try {
                synchronized (session) {
                    Integer counter = (Integer) session.getAttribute(RUNNING_COUNT);
                    int running = (counter == null ? 1 : counter) - 1;
                    if (running > 0) {
                        session.setAttribute(RUNNING_COUNT, running);
                    } else {
                        Integer saved = (Integer) session.getAttribute(SAVED_INTERVAL);
                        if (saved != null)
                            session.setMaxInactiveInterval(saved);
                        session.removeAttribute(RUNNING_COUNT);
                        session.removeAttribute(SAVED_INTERVAL);
                    }
                    if (session instanceof SessionCtrl)
                        ((SessionCtrl) session).notifyClientRequest(true);
                }
            } catch (IllegalStateException e) {
                // The session was invalidated while the task ran (e.g. logout in another tab):
                // there is nothing to restore, and the task outcome must not be masked.
                invalidated = true;
            }
            report(taskName, (System.nanoTime() - start) / 1_000_000L, invalidated, warningThreshold);
        }
    }

    /**
     * Log the warnings of a finished task; any RuntimeException raised here is swallowed
     */
    private static void report(Supplier<String> taskName, long elapsedMillis, boolean invalidated,
                               IntSupplier warningThreshold) {
        try {
            int threshold = readThreshold(warningThreshold);
            boolean tooLong = threshold > 0 && elapsedMillis > threshold * 1000L;
            long elapsedSeconds = elapsedMillis / 1000L;
            if (!invalidated && !tooLong)
                return;
            String name = taskName.get();
            if (invalidated)
                logger.warning("The session was invalidated while the task ran. Task=" + name
                        + " elapsedSeconds=" + elapsedSeconds);
            if (tooLong) {
                logger.warning("Long task. Task=" + name + " elapsedSeconds=" + elapsedSeconds
                        + " thresholdSeconds=" + threshold + " AD_User_ID=" + Env.getAD_User_ID(Env.getCtx())
                        + " user=" + Env.getContext(Env.getCtx(), "#AD_User_Name"));
            }
        } catch (RuntimeException e) {
            try {
                logger.warning("Cannot report the task: " + e);
            } catch (RuntimeException ignored) {
                // logging must never mask the outcome of the task
            }
        }
    }

    private static int readThreshold(IntSupplier warningThreshold) {
        try {
            return warningThreshold.getAsInt();
        } catch (RuntimeException e) {
            return DEFAULT_WARNING_SECONDS;
        }
    }

    private static int readWarningThreshold() {
        return MSysConfig.getIntValue(ZK_LONG_TASK_WARNING_SECONDS, DEFAULT_WARNING_SECONDS);
    }
}
