/******************************************************************************
 * Copyright (C) 2008 Low Heng Sin                                            *
 * Copyright (C) 2008 Idalica Corporation                                     *
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
package org.adempiere.webui.dashboard;

import org.adempiere.webui.desktop.IDesktop;
import org.adempiere.webui.session.SessionContextListener;
import org.adempiere.webui.util.ServerPushTemplate;
import org.compiere.model.MSysConfig;
import org.compiere.util.CLogger;
import org.zkoss.util.Locales;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Execution;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;

/**
 * @author hengsin
 * @author Cristina Ghita, www.arhipac.ro BF [2871741] Error at start
 * @see https://sourceforge.net/tracker/?func=detail&atid=955896&aid=2871741&group_id=176962
 */
public class DashboardRunnable implements Runnable, Serializable {

    private static final long serialVersionUID = 5995227773511788894L;
    private Thread worker;
    private final int interval = MSysConfig.getIntValue(ZK_DASHBOARD_REFRESH_INTERVAL, 60000);
    private final AtomicBoolean running = new AtomicBoolean(false);
    private final AtomicBoolean stopped = new AtomicBoolean(false);
    private WeakReference<Desktop> desktopReference;
    private WeakReference<IDesktop> applicationDesktopReference;
    private List<DashboardPanel> dashboardPanels = new ArrayList<>();
    private Locale locale;

    private static final CLogger logger = CLogger.getCLogger(DashboardRunnable.class);
    private final static String ZK_DASHBOARD_REFRESH_INTERVAL = "ZK_DASHBOARD_REFRESH_INTERVAL";

    /**
     * @param desktop zk desktop interface
     * @param desktop adempiere desktop interface
     */
    public DashboardRunnable(Desktop desktop, IDesktop applicationDesktop) {
        this.desktopReference = new WeakReference<>(desktop);
        this.applicationDesktopReference = new WeakReference<>(applicationDesktop);

        dashboardPanels = new ArrayList<>();
        locale = Locales.getCurrent();
    }

    public DashboardRunnable(DashboardRunnable tmp, Desktop desktop, IDesktop applicationDesktop) {
        this(desktop, applicationDesktop);
        this.dashboardPanels = tmp.dashboardPanels;
    }

    public void start() {
        worker = new Thread(this);
        worker.setDaemon(true);
        worker.start();
    }

    public void stop() {
        running.set(false);
    }

    public void interrupt() {
        running.set(false);
        worker.interrupt();
    }

    public boolean isRunning() {
        return running.get();
    }

    public boolean isStopped() {
        return stopped.get();
    }


    public void run() {
        running.set(true);
        stopped.set(false);
        // default Update every one minutes
        while (running.get()) {
            try {
                Thread.sleep(interval);
            } catch (InterruptedException interruptedException) {
                logger.log(Level.INFO, "Thread was interrupted ..." + interruptedException.getMessage());
                break;
            }

            getApplicationDesktop().flatMap(applicationDesktop -> getDesktop()).ifPresent(desktop -> {
                Locales.setThreadLocal(locale);
                try {
                    refreshDashboard();
                } catch (Exception exception) {
                    logger.log(Level.INFO, "Refresh Dashboard stop execution ..." + exception.getMessage());
                    running.set(false);
                }
            });
        }
        cleanup();
        stopped.set(true);
    }

    /**
     * Refresh dashboard content
     */
    public void refreshDashboard() {
        getApplicationDesktop().ifPresent(applicationDesktop ->
                getDesktop().ifPresent(desktop -> {
                    ServerPushTemplate template = new ServerPushTemplate(desktop);
                    Optional<Execution> maybeExecution = Optional.ofNullable(desktop.getExecution());
                    maybeExecution.ifPresent(execution -> {
                        SessionContextListener.setContextForSession(execution);
                        dashboardPanels.forEach(dashboardPanel -> dashboardPanel.refresh(template));
                    });
                    applicationDesktop.onServerPush(template);
                }));
    }

    /**
     * Add DashboardPanel to the auto refresh list
     *
     * @param dashboardPanel
     */
    public void add(DashboardPanel dashboardPanel) {
        dashboardPanels.add(dashboardPanel);
    }

    private Optional<Desktop> getDesktop() {
        return Optional.ofNullable(desktopReference.get());
    }

    private Optional<IDesktop> getApplicationDesktop() {
        return Optional.ofNullable(applicationDesktopReference.get());
    }

    public void cleanup() {
        dashboardPanels.forEach(dashboardPanel ->  dashboardPanel.getAttributes().clear());
        dashboardPanels.clear();
        if (desktopReference != null) {
            Desktop desktop = desktopReference.get();
            desktopReference.clear();
        }

        if (applicationDesktopReference != null) {
            IDesktop desktopExecution = applicationDesktopReference.get();
            applicationDesktopReference.clear();
            desktopExecution = null;
        }

        dashboardPanels = null;
        desktopReference = null;
        applicationDesktopReference = null;
    }
}
