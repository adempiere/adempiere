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

import org.adempiere.webui.ZkContextProvider;
import org.adempiere.webui.window.ZkJRViewerProvider;
import org.adempiere.webui.window.ZkReportViewerProvider;
import org.compiere.Adempiere;
import org.compiere.jr.report.ReportStarter;
import org.compiere.print.ReportCtl;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.zkoss.zk.ui.http.DHtmlLayoutServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 
 * @author <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date Feb 25, 2007
 * @version $Revision: 0.10 $
 */
public class WebUIServlet extends DHtmlLayoutServlet
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 261899419681731L;
    private ZkContextProvider contextProvider =  new ZkContextProvider();

    private int serviceCounter = 0;
    private Object lock = new Object();
    private boolean shuttingDown;

	/** Logger for the class * */
    private static final CLogger logger;

    static
    {
        logger = CLogger.getCLogger(WebUIServlet.class); 
    }


    public void init(ServletConfig servletConfig) throws ServletException
    {
        /**
         * Start ADempiere
         */
        super.init(servletConfig);

        /** Initialise context for the current thread*/
        ServerContext.getCurrentInstance();
        Env.setContextProvider(contextProvider);
        boolean started = Adempiere.startup(false);
        if(!started)
            throw new ServletException("Could not start ADempiere");

        Ini.setProperty(Ini.P_ADEMPIERESYS, false);
        ReportCtl.setReportViewerProvider(new ZkReportViewerProvider());
        ReportStarter.setReportViewerProvider(new ZkJRViewerProvider());
        logger.info("ADempiere started successfully");
        /**
         * End ADempiere Start
         */
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        enteringServiceMethod();
        try {
            super.service(request, response);
        } finally {
            leavingServiceMethod();
        }
    }

    @Override
    public void destroy()
    {
        synchronized(lock) {
            /* Check to see whether there are still service methods running,
             * and if there are, tell them to stop. */
            if (numServices() > 0) {
                setShuttingDown(true);
            }

            /* Wait for the all the service methods to stop.  */
            while(numServices() > 0) {
                try {
                    wait();
                } catch (InterruptedException exception) {
                    throw new RuntimeException(exception.toString());
                }
            }
        }

        try {
            //Get Context for Current Thread and Remove
            SessionManager.getSessionCache().values().forEach(httpSession -> {
                logger.info( "Session " + httpSession.getId() + " Logout ...");
                SessionManager.clearSession(httpSession.getId());
                SessionManager.cleanSessionBackground(httpSession.getId());
                SessionManager.removeSession(httpSession.getId());
                logger.info("Session " + httpSession.getId() + " Destroyed");
            });
            contextProvider = null;
            ServerContext.dispose();
        } catch (Exception exception) {
            throw new RuntimeException(exception.toString());
        } finally {
            super.destroy();
        }
    }

    //Access methods for serviceCounter
    protected void enteringServiceMethod() {
        synchronized(lock) {
            serviceCounter++;
        }
    }
    protected void leavingServiceMethod() {
        synchronized(lock) {
            serviceCounter--;
            if (serviceCounter == 0 && isShuttingDown())
                notifyAll();
        }
    }
    protected int numServices() {
        synchronized(lock) {
            return serviceCounter;
        }
    }

    //Access methods for shuttingDown
    protected void setShuttingDown(boolean flag) {
        shuttingDown = flag;
    }
    protected boolean isShuttingDown() {
        return shuttingDown;
    }
}
