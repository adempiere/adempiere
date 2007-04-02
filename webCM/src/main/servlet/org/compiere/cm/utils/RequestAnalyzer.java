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
package org.compiere.cm.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.cm.cache.*;
import org.compiere.cm.*;
import org.compiere.util.*;

/**
 * RequestAnalyzer
 * 
 * @author Yves Sandfort
 * @version $Id$
 */
public class RequestAnalyzer
{

	private String				m_requestURL;

	private String				m_relativeURL;

	private String				m_serverName;

	private String				m_baseURL;

	private String				m_redirectURL;
	
	private String				m_procClassName = null;

	private MWebProjectDomain	m_WebProjectDomain;

	private MWebProject			m_WebProject;

	private MContainer			m_Container;

	private boolean				m_isValid	= false;

	private boolean				m_isSecure   = false;

	private boolean				m_isRedirect = false;

	private int					m_portNumber = 80;
	
	private HttpServletRequest  m_request;
	
	private Properties			m_ctx;
	
	private HttpSession         m_session;

	/**
	 * 	RequestAnalyzer
	 *	@param servlet servlet
	 *	@param request request
	 *	@param showStage show stage
	 *	@param servletExtend servlet extend
	 */
	public RequestAnalyzer (HttpServletCM servlet, HttpServletRequest request,
		boolean showStage, String servletExtend)
	{
		Domain domainCache = servlet.getDomainCache ();
		WebProject webProjectCache = servlet.getWebProjectCache ();
		Container containerCache = servlet.getContainerCache ();
		if (servletExtend==null) servletExtend="";
		m_request = request;
		m_ctx = servlet.getCtx ();
		m_requestURL = m_request.getRequestURL ().toString ();
		m_serverName = m_request.getServerName ();
		m_baseURL = m_requestURL.substring (0, m_requestURL.indexOf (m_serverName)
			+ m_serverName.length () + servletExtend.length ())
			+ m_request.getContextPath ();
		m_relativeURL = m_requestURL.substring (m_baseURL.length ());
		// If RelativeURL is empty it should be /
		if (m_relativeURL== null || m_relativeURL.equals("")) m_relativeURL="/";
		// If URL ends with a / we should continue it with index.html
		if (m_relativeURL.substring (m_relativeURL.length () - 1).equals ("/"))
			m_relativeURL = m_relativeURL + "index.html";
		m_isSecure = m_request.isSecure ();
		m_portNumber = m_request.getServerPort ();
		m_WebProjectDomain = domainCache.getWebProjectDomain (m_serverName);
		if (m_WebProjectDomain != null)
		{
			// If we could identify the Domain we will have a project etc.
			m_WebProject = webProjectCache.getWebProject 
				(m_WebProjectDomain.getCM_WebProject_ID ());
		}
		else
		{
			// Since we have not found a sufficient WebProject Domain we will
            // fallback to the default
			int[] defaultID = MWebProject.getAllIDs ("CM_WebProject",
				"AD_Client_ID=0", null);
			if (defaultID.length > 0)
				m_WebProject = webProjectCache.getWebProject (defaultID[0]);
			else	 {
				m_isRedirect = true;
				m_redirectURL = m_requestURL + "admin/";
			}
				// JJ
				//throw new IllegalStateException("Unknown context - Set up Web Project"); // no known context
		}
		// Check for adempiere.jnlp
		if (m_relativeURL!=null) {
			if(m_relativeURL.equals("/adempiere.jnlp") || m_relativeURL.equals("/adempiereDirect.jnlp"))
			{
				m_isRedirect = true;
				m_redirectURL = m_requestURL.substring(0,m_requestURL.indexOf(m_serverName)+m_serverName.length()) + "/admin" + m_relativeURL;
			}
		}
		if (!m_isRedirect) {
			if (m_relativeURL != null)
			{
				// We have a URL, so let's see whether we can handle it...
				m_Container = containerCache.getCM_ContainerByURL (m_relativeURL,
					m_WebProject.get_ID (), true);
				if (m_Container == null)
					m_isValid = false;
				else
					m_isValid = true;
				if (m_isValid && !m_Container.getRelativeURL ().equals (m_relativeURL))
				{
					m_isRedirect = true;
					m_redirectURL = m_Container.getRelativeURL ();
				}
			}
			else
			{
				// We have no or an invalid relative URL found, so we need to
	            // fallback to Domain or Error handling
				if (m_WebProjectDomain.getCM_Container_ID () > 0)
				{
					m_Container = containerCache.getCM_Container (
						m_WebProjectDomain.getCM_Container_ID (), 
							m_WebProject.get_ID ());
				}
				if (m_Container == null)
				{
					m_Container = containerCache.getCM_ContainerByURL 
						("/index.html", m_WebProject.get_ID (), true);
					if (m_Container == null)
					{
						m_isValid = false;
					}
					else
					{
						m_isValid = true;
					}
					if (m_isValid
						&& !m_Container.getRelativeURL ().equals ("/index.html"))
					{
						m_isRedirect = true;
						m_redirectURL = m_Container.getRelativeURL ();
					}
				}
			}
			if (m_isValid == false) {
				// Try to solve invalid requests
				if (m_WebProject==null || m_WebProject.getAD_Client_ID()==0) 
				{
					// If we endup with an invalid request in NULL or System Project we redirect to /admin/
					m_isRedirect = true;
					m_redirectURL = m_requestURL.substring(0,m_requestURL.indexOf(m_serverName)+m_serverName.length()) + "/admin/";
				}
			}
			if (m_isValid) {
				if (m_Container.getContainerType ().equals ("L")) {
					m_isRedirect = true;
					MContainer linkedContainer = containerCache.getCM_Container (m_Container.getCM_ContainerLink_ID (), m_WebProject.get_ID());
					if (linkedContainer!=null) 
						m_redirectURL = linkedContainer.getRelativeURL ();
				}
				servlet.setAD_Client_ID(m_WebProject.getAD_Client_ID());
				
			}
		}
		if (m_request.getParameter ("cn")!=null) {
			String className = m_request.getParameter("cn");
			// First check adempiere.cm.
			if (classChecker("adempiere.cm." + className,servlet.getLogger())) 
				m_procClassName = "adempiere.cm." + className;
			if (classChecker("org.compiere.cm.extend." + className,servlet.getLogger())) 
				m_procClassName = "org.compiere.cm.extend." + className;
		}
	}	//	RequestAnalyzer
	
	private boolean classChecker(String className, CLogger log) {
		try
		{
			Class clazz = Class.forName(className);
			//	Make sure that it is a cm.Extend class
			Class superClazz = clazz.getSuperclass();
			while (superClazz != null)
			{
				if (superClazz == org.compiere.cm.Extend.class)
				{
					log.fine("Use: " + className);
					return true;
				}
			}
		}
		catch (Exception e)
		{
		}
		log.finest("Not found: " + className);
		return false;
	}
	
	public org.compiere.cm.Extend getProcClass() 
	{
		if (m_procClassName==null)
			return null;
		try {
			Class thisProcClass = Class.forName (m_procClassName);
			boolean errorLogged = false;
			try
			{
				Constructor constructor = thisProcClass.getDeclaredConstructor(new Class[]{HttpServletRequest.class, Properties.class});
				Extend procClass = (Extend)constructor.newInstance(new Object[] {m_request, m_ctx});
				return procClass;
			}
			catch (Exception e)
			{
			}
			return null;
		}
		catch (Exception e)
		{
		}
		return null;
	}

	/**
	 * 	Get Request URL
	 *	@return request url
	 */
	public String getRequestURL ()
	{
		return m_requestURL;
	}	//	getRequestURL

	/**
	 * 	Get ServerName
	 *	@return server name
	 */
	public String getServerName ()
	{
		return m_serverName;
	}	//	getServerName

	/**
	 * 	get WebProject_Domain
	 *	@return web project domain 
	 */
	public MWebProjectDomain getWebProjectDomain()
	{
		return m_WebProjectDomain;
	}	//	getWebProjectDomain

	/**
	 * 	Get WebProject
	 *	@return web project
	 */
	public MWebProject getWebProject ()
	{
		return m_WebProject;
	}	//	getWebProject

	/**
	 * 	Get CM_Container
	 *	@return container
	 */
	public MContainer getCM_Container ()
	{
		return m_Container;
	}	//	getCM_Container

	/**
	 * 	Valid
	 *	@return true if valid
	 */
	public boolean getIsValid ()
	{
		return m_isValid;
	}	//	getIsValid

	/**
	 * 	Redirect
	 *	@return true redirect
	 */
	public boolean getIsRedirect ()
	{
		return m_isRedirect;
	}	//	getIsRedirect
	
	/**
	 * 	setRedirectURL
	 *	@param redirectURL
	 */
	public void setRedirectURL(String redirectURL)
	{
		m_redirectURL = redirectURL;
	}

	/**
	 * 	Get Redirect URL
	 *	@return URL
	 */
	public String getRedirectURL ()
	{
		try {
			/* We will use the URL Object to check the validity of the URL
			 * this would not imply that the URL is reachable, it only 
			 * checks the format.
			 */
			URL testURL = new URL(m_redirectURL);
			return m_redirectURL;
		} catch (MalformedURLException E) {
			if (m_redirectURL.equals ("/error404.html"))
			{
				return m_baseURL + m_redirectURL + "?errorURL=" + m_relativeURL;
			}
			else
			{
				return m_baseURL + m_redirectURL;
			}
		}
	}	//	getRedirectURL
	
	/**
	 * 	Get Processor Class Name
	 *	@return ClassName for Processor
	 */
	public String getProcClassName()
	{
		return m_procClassName;
	}
	
}	//	RequestAnalyzer
