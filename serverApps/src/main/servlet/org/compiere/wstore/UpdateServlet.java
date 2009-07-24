/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
package org.compiere.wstore;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.compiere.util.CLogger;
import org.compiere.util.WebEnv;
import org.compiere.util.WebSessionCtx;
import org.compiere.util.WebUser;
import org.compiere.util.WebUtil;

/**
 *  Web User Update.
 *  @author     Malachi de AElfweald
 *  @version    $Id: UpdateServlet.java,v 1.6 2006/09/23 19:38:46 comdivision Exp $
 */
public class UpdateServlet
    extends HttpServlet
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4366607660993185848L;

	/**	Logging						*/
    private CLogger				log = CLogger.getCLogger(getClass());

    /** Name						*/
    static public final String	NAME = "updateServlet";

    /** Forward Parameter					*/
    public static final String		P_ForwardTo = "ForwardTo";

    /** SalesRep Parameter					*/
    public static final String		P_SalesRep_ID = "SalesRep_ID";

    /** Login Page							*/
    public static final String		LOGIN_JSP = "/login.jsp";

    /** Update Page                         */
    public static final String      UPDATE_JSP = "/update.jsp";

    /**
     *	Initialize global variables
     *
     *  @param config Configuration
     *  @throws javax.servlet.ServletException
     */
    public void init(ServletConfig config)
        throws ServletException
    {
        super.init(config);
        if (!WebEnv.initWeb(config))
            throw new ServletException("UpdateServlet.init");
    }   //  init

    /**
     * Get Servlet information
     * @return Info
     */
    public String getServletInfo()
    {
        return "Adempiere Web Update Servlet";
    }	//	getServletInfo

    /**
     * Clean up resources
     */
    public void destroy()
    {
        log.fine("destroy");
    }   //  destroy

    /**
     *  Process the HTTP Get request.
     * 	(logout, deleteCookie)
     *  Sends Web Request Page
     *
     *  @param request request
     *  @param response response
     *  @throws ServletException
     *  @throws java.io.IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        log.info("From " + request.getRemoteHost() + " - " + request.getRemoteAddr());
        HttpSession session = request.getSession(true);
        // ignore GET
    }	//	doGet

    /**
     *  Process the HTTP Post request
     *
     *  @param request request
     *  @param response response
     *  @throws ServletException
     *  @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        log.info("From " + request.getRemoteHost() + " - " + request.getRemoteAddr());
        Properties ctx = JSPEnv.getCtx(request);
        HttpSession session = request.getSession(true);
        session.removeAttribute(WebSessionCtx.HDR_MESSAGE);
    //	WEnv.dump(session);
    //	WEnv.dump(request);
        
		org.compiere.util.WebLogin thisLogin = new org.compiere.util.WebLogin(request, response, ctx);
		thisLogin.init ();
		thisLogin.setUpdate_page(UPDATE_JSP);

        //	Web User
        WebUser wu = WebUser.get(request);
        if(wu == null || (wu.getAD_User_ID() == 0) || (!wu.isLoggedIn()))
        {
            WebUtil.reload("User not logged in", LOGIN_JSP, session, request, response, getServletContext());
            return;
        }
		if (!thisLogin.action ())
		{
			WebUtil.reload(thisLogin.getMessage(), thisLogin.getUpdate_page (), session, request, response, getServletContext());
			return;
		}
		String url = thisLogin.getForward();
		if (url == null || url.length() == 0)
			url = "/";

		session.setAttribute (WebUser.NAME, wu);


		if (!url.startsWith("/"))
            url = "/" + url;

        log.info("Forward to " + url);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
    }	//	doPost
}
