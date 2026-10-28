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
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.DesktopUnavailableException;

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
        if (tmp.dashboardPanels != null) {
            this.dashboardPanels = new ArrayList<>(tmp.dashboardPanels);
        }
    }

    public void start() {
        if (running.get()) {
            return;
        }
        worker = new Thread(this);
        worker.setDaemon(true);
        worker.start();
    }

    public void stop() {
        running.set(false);
        interruptWorker();
    }

    public void interrupt() {
        running.set(false);
        interruptWorker();
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

            Optional<Desktop> maybeDesktop = getDesktop();
            Optional<IDesktop> maybeApplicationDesktop = getApplicationDesktop();
            if (!maybeDesktop.isPresent() || !maybeApplicationDesktop.isPresent()
                    || !isRefreshable(maybeDesktop.get(), maybeApplicationDesktop.get())) {
                running.set(false);
                break;
            }

            Locales.setThreadLocal(locale);
            try {
                refreshDashboard(maybeDesktop.get(), maybeApplicationDesktop.get());
            } catch (DesktopUnavailableException desktopUnavailableException) {
                logger.log(Level.INFO, "Refresh Dashboard stop execution: desktop unavailable");
                running.set(false);
            } catch (Exception exception) {
                logger.log(Level.INFO, "Refresh Dashboard stop execution ..." + exception.getMessage());
                running.set(false);
            }
        }
        cleanup();
        stopped.set(true);
    }

    /**
     * Refresh dashboard content
     */
    public void refreshDashboard() {
        Optional<Desktop> maybeDesktop = getDesktop();
        Optional<IDesktop> maybeApplicationDesktop = getApplicationDesktop();
        if (!maybeDesktop.isPresent() || !maybeApplicationDesktop.isPresent()
                || !isRefreshable(maybeDesktop.get(), maybeApplicationDesktop.get())) {
            running.set(false);
            return;
        }
        refreshDashboard(maybeDesktop.get(), maybeApplicationDesktop.get());
    }

    private void refreshDashboard(Desktop desktop, IDesktop applicationDesktop) {
        if (!SessionContextListener.setContextForDesktop(desktop)) {
            running.set(false);
            return;
        }

        ServerPushTemplate template = new ServerPushTemplate(desktop);
        if (dashboardPanels != null) {
            dashboardPanels.removeIf(dashboardPanel -> !isPanelAttached(dashboardPanel, desktop));
            dashboardPanels.forEach(dashboardPanel -> dashboardPanel.refresh(template));
        }
        applicationDesktop.onServerPush(template);
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
        if (dashboardPanels != null) {
            dashboardPanels.forEach(dashboardPanel -> {
                if (dashboardPanel != null) {
                    dashboardPanel.getAttributes().clear();
                }
            });
            dashboardPanels.clear();
        }
        if (desktopReference != null) {
            desktopReference.clear();
        }

        if (applicationDesktopReference != null) {
            applicationDesktopReference.clear();
        }

        dashboardPanels = null;
        desktopReference = null;
        applicationDesktopReference = null;
        worker = null;
    }

    private void interruptWorker() {
        Thread currentWorker = worker;
        if (currentWorker != null) {
            currentWorker.interrupt();
        }
    }

    private boolean isRefreshable(Desktop desktop, IDesktop applicationDesktop) {
        if (desktop == null || !desktop.isAlive() || applicationDesktop == null) {
            return false;
        }

        Component component = applicationDesktop.getComponent();
        return component != null
                && component.getPage() != null
                && component.getDesktop() == desktop;
    }

    private boolean isPanelAttached(DashboardPanel dashboardPanel, Desktop desktop) {
        return dashboardPanel != null
                && dashboardPanel.getPage() != null
                && dashboardPanel.getDesktop() == desktop;
    }
}
