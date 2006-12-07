/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.jsf;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.UnavailableException;

import org.compiere.Adempiere;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Login;

/**
 * for this project, this servlet needs to be replaced by a backing bean that can also log out
 * initializes the adempiere "engine" -- basically logs into the server
 * web.xml snippet:
 * <servlet>
        <servlet-name>adempiereInitServlet</servlet-name>
        <servlet-class>org.compiere.jsf.AdempiereInitServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
   </servlet>
 * @author rfreden
 * @todo wouldn't it be cool to have a Filter to implement lazy loading?
 * @fixme the adempiere code here does a System.exit (terminating tomcat...) if it fails to connect to the db
 */
public class AdempiereServlet implements Servlet
{
    private static final CLogger log=CLogger.getCLogger(AdempiereServlet.class);

    private Properties adempiereProperties;

    private ServletConfig servletConfig=null;

    /**
     * nop method, because this servlet doesn't actually serve anything
     */
    public void service(ServletRequest servletRequest,ServletResponse servletResponse)
    throws ServletException, IOException
    {}

    public void init(ServletConfig s) throws ServletException
    {
        servletConfig=s;
        // Adempiere start up and properties setup
        //org.compiere.Adempiere.startupEnvironment(true);
        if(!Adempiere.startupEnvironment(true))
            throw new UnavailableException("adempiere init failed");
        adempiereProperties = Env.getCtx();
        //CLogMgt.setLoggerLevel(Level.FINE, null);
        CLogMgt.setLevel(Level.ALL);

        Ini.setProperty(Ini.P_UID, "SuperUser");
        Ini.setProperty(Ini.P_PWD, "System");
        Ini.setProperty(Ini.P_ROLE, "GardenWorld Admin");
        Ini.setProperty(Ini.P_CLIENT, "GardenWorld");
        Ini.setProperty(Ini.P_ORG, "*");
        Ini.setProperty(Ini.P_LANGUAGE, "English");
        //Ini.setProperty(Ini.P_LANGUAGE, "Danish_dk_DK");
        Ini.setProperty(Ini.P_WAREHOUSE, "");
        Login login = new Login(adempiereProperties);

        if (!login.batchLogin(null))
            throw new UnavailableException("Adempiere login failed");

        log.info("adempiere login complete");
    }

    public String getServletInfo()
    {
        return "Adempiere Initialization Servlet";
    }

    public ServletConfig getServletConfig()
    {
        return servletConfig;
    }

    /**
     * @todo implement this method
     */
    public void destroy()
    {}
}
