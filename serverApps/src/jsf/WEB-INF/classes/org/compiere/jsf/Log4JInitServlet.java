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

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.xml.DOMConfigurator;

/**
 * manually initializes the log4j system independent of adempiere's main logging;
 * enables jsf components and other existing code to use log4j for their output.
 * this should not be considered anything near a permanent solution, unless there is
 * no better way to integrate the two logging systems
 * init param, "log4j-init-file"; make sure to put an xml descriptor file somewhere this can see it
 * @author rfreden
 */
public class Log4JInitServlet extends HttpServlet
{
	private static final long serialVersionUID = 2294612125299399465L;

	/**
     * loads the log4j.xml logging descriptor
     * @todo load the init file as a resource, also handle alternate configuration (properties file)
     */
    public void init() throws ServletException
    {
        super.init();
        String path=getServletContext().getRealPath("/");
        String fn=getInitParameter("log4j-init-file");
        if(fn!=null)
            DOMConfigurator.configure(path+fn);
        else
        	throw new UnavailableException("could not find log4j xml descriptor, make sure it exists and is specified in web.xml");
    }
}
