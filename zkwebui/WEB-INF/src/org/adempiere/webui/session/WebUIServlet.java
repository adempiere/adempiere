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

import java.io.IOException;
import java.util.logging.Level;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.adempiere.webui.ZkContextProvider;
import org.adempiere.webui.window.ZkJRViewerProvider;
import org.adempiere.webui.window.ZkReportViewerProvider;
import org.compiere.Adempiere;
import org.compiere.print.ReportCtl;
import org.compiere.report.ReportStarter;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.zkoss.zk.ui.http.DHtmlLayoutServlet;

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
	
	/** Logger for the class * */
    private static final CLogger logger;

    static
    {
        logger = CLogger.getCLogger(WebUIServlet.class); 
    }

    public void init(ServletConfig servletConfig) throws ServletException
    {
        super.init(servletConfig);

        /** Initialise context for the current thread*/
        ServerContext.newInstance();
        Env.setContextProvider(new ZkContextProvider());
        
        /**
         * Start ADempiere
         */
        boolean started = Adempiere.startup(false);
        if(!started)
        {
            throw new ServletException("Could not start ADempiere");
        }
        
        // hengsin: temporary solution for problem with zk client
        Ini.setProperty(Ini.P_ADEMPIERESYS, false);
        ReportCtl.setReportViewerProvider(new ZkReportViewerProvider());
        ReportStarter.setReportViewerProvider(new ZkJRViewerProvider());
        logger.info("ADempiere started successfully");
        /**
         * End ADempiere Start
         */
    }

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException
    {
        super.doGet(request, response);
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException
    {
        
        super.doPost(request, response);
    }

    public void service(ServletRequest request, ServletResponse response)
            throws ServletException, IOException
    {
        super.service(request, response);
    }

    public ServletConfig getServletConfig()
    {
        return super.getServletConfig();
    }

    public String getServletInfo()
    {
        return super.getServletInfo();
    }

    public void destroy()
    {
        super.destroy();
    }
}
