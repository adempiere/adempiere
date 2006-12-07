/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.
 * This program is free software; you can redistribute it and/or modify it
 * under the terms version 2 of the GNU General Public License as published
 * by the Free Software Foundation. This program is distributed in the hope
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along 
 * with this program; if not, write to the Free Software Foundation, Inc., 
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
 * You may reach us at: ComPiere, Inc. - http://www.adempiere.org/license.html
 * 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA or info@adempiere.org 
 *****************************************************************************/
package org.compiere.cm;

import java.io.Serializable;
import org.compiere.util.*;
import java.util.*;
import javax.servlet.http.*;

/**
 *	
 *	
 *  @author Yves Sandfort
 *  @version $Id$
 */
public abstract class Extend implements Serializable
{
	protected HttpServletRequest e_request;
	protected HttpSession e_session;
	protected Properties ctx;
	protected WebInfo wi = null;
	private StringBuffer e_xmlCode;
	private String e_redirectURL;
	
	/**
	 * Extend
	 * @param request
	 * @param t_ctx 
	 */
	public Extend (HttpServletRequest request, Properties t_ctx) {
		ctx = t_ctx;
		e_request = request;
		e_session = request.getSession();
		if (e_session.getAttribute (WebInfo.NAME)!=null) 
		{
			wi = (WebInfo) e_session.getAttribute (WebInfo.NAME);
		}
		e_xmlCode = new StringBuffer();
	}
	
	/**
	 * 	set XML Code
	 *	@param xmlCode
	 */
	protected void setXML(StringBuffer xmlCode)
	{
		e_xmlCode = xmlCode;
	}
	
	/**
	 * append XML Code
	 * @param xmlCode String containing XMLCode
	 */
	public void appendXML(String xmlCode)
	{
		e_xmlCode.append(xmlCode);
	}
	
	/**
	 * append XML Code
	 * @param xmlCode Stringbuffer containing XMLCode
	 */
	public void appendXML(StringBuffer xmlCode)
	{
		e_xmlCode.append(xmlCode);
	}
	
	/**
	 * 	get XML Code
	 *	@returns xmlCode
	 */
	protected StringBuffer getXML()
	{
		if (e_xmlCode==null)
			return new StringBuffer("");
		return e_xmlCode;
	}
	
	/**
	 * 	get Redirect URL
	 *	@param redirectURL
	 */
	protected String getRedirectURL()
	{
		return e_redirectURL;
	}
	
	/**
	 * 	set Redirect URL
	 *	@param redirectURL
	 */
	protected void setRedirectURL(String redirectURL) 
	{
		e_redirectURL = redirectURL;
	}
	
	protected Properties getCtx() 
	{
		return ctx;
	}
	
	protected boolean doIt()
	{
		return true;
	}
}
