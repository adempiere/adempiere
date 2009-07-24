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

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.jstl.core.Config;
import javax.servlet.jsp.tagext.TagSupport;

import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.WebUser;

/**
 * 	PriceList Tag.
 * 	Loads Price List
 *  <pre>
 *  <cws:priceList priceList_ID="0"/>
 *  Variable used = "priceList"
 *	</pre>
 *
 *  @author Jorg Janke
 *  @version $Id: PriceListTag.java,v 1.2 2006/07/30 00:53:21 jjanke Exp $
 */
public class PriceListTag extends TagSupport
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2109422988040998327L;

	/**	Price List ID			*/
	private int					m_priceList_ID = 0;

	/**	Web User				*/
	private PriceList			m_priceList;
	/**	Logger							*/
	private CLogger				log = CLogger.getCLogger (getClass());

	/**
	 * 	Set Price List
	 * 	@param var price list
	 */
	public void setPriceList_ID (String var)
	{
		try
		{
			m_priceList_ID = Integer.parseInt (var);
		}
		catch (NumberFormatException ex)
		{
			log.warning("setPriceList_ID - " + ex.toString());
		}
	}	//	setM_PriceList_ID


	/**
	 *  Start Tag
	 *  @return SKIP_BODY
	 * 	@throws JspException
	 */
	public int doStartTag() throws JspException
	{
		//	Create Price List
		Properties ctx = JSPEnv.getCtx((HttpServletRequest)pageContext.getRequest());
		int AD_Client_ID = Env.getContextAsInt(ctx, "AD_Client_ID");
		int M_PriceList_ID = m_priceList_ID;
		if (M_PriceList_ID == 0)
			M_PriceList_ID = Env.getContextAsInt(ctx, "M_PriceList_ID");

		//	Check Business Partner
		WebUser wu = (WebUser)pageContext.getSession().getAttribute(WebUser.NAME);
		if (wu != null)
		{
			int PriceList_ID = wu.getM_PriceList_ID();
			if (PriceList_ID != 0)
			{
				log.fine("- using BP PriceList_ID=" + PriceList_ID);
				M_PriceList_ID = PriceList_ID;
			}
		}
		
		//	Get Parameters
		String searchString = ctx.getProperty(ProductServlet.P_SEARCHSTRING);
		String productCategory = ctx.getProperty(ProductServlet.P_M_PRODUCT_CATEGORY_ID);

		
		//	get price list
		m_priceList = PriceList.get (ctx, AD_Client_ID, M_PriceList_ID, 
			searchString, productCategory, false);
		if (M_PriceList_ID == 0)
			Env.setContext(ctx, "#M_PriceList_ID", m_priceList.getPriceList_ID());

		//	Set Price List
		HttpSession session = pageContext.getSession();
		session.setAttribute (PriceList.NAME, m_priceList);
		log.fine("PL=" + m_priceList);

		//	Set Locale from Price List
		String AD_Language = m_priceList.getAD_Language();
		if (AD_Language == null || AD_Language.length() == 0)
			AD_Language = "en_US";
		Config.set(session, Config.FMT_LOCALE, AD_Language);
		Config.set(session, Config.FMT_FALLBACK_LOCALE, "en_US");
		//
		return (SKIP_BODY);
	}   //  doStartTag

	/**
	 * 	End Tag
	 * 	@return EVAL_PAGE
	 * 	@throws JspException
	 */
	public int doEndTag() throws JspException
	{
		return EVAL_PAGE;
	}	//	doEndTag

}	//	PriceListTag
