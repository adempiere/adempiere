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
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.ecs.xhtml.a;
import org.apache.ecs.xhtml.hr;
import org.apache.ecs.xhtml.img;
import org.apache.ecs.xhtml.p;
import org.compiere.util.CLogger;
import org.compiere.util.HtmlCode;

/**
 *  CheckOut Links.
 * 	Creates Basket / Checkout Link
 *  <pre>
 *  <cws:checkOutLink/>
 *	</pre>
 *
 *  @author Jorg Janke
 *  @version $Id: CheckOutLinkTag.java,v 1.3 2006/07/30 00:53:21 jjanke Exp $
 */
public class CheckOutLinkTag extends TagSupport
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6103865361649483332L;
	/**	Logger							*/
	private CLogger			log = CLogger.getCLogger (getClass());
	/** One Line						*/
	private boolean			m_oneLine = false;

	/**
	 *	Set to one line
	 *	@param var Y or something else
	 */
	public void setOneLine (String var)
	{
		m_oneLine = "Y".equals(var);
	}	//	setOneLine

	/**
	 *  Start Tag
	 *  @return SKIP_BODY
	 * 	@throws JspException
	 */
	public int doStartTag() throws JspException
	{
		HttpSession session = pageContext.getSession();
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		WebBasket wb = (WebBasket)session.getAttribute(WebBasket.NAME);

	//	log.fine("WebBasket=" + wb);
		if (wb != null && wb.getLineCount() > 0)
		{
			log.fine("WebBasket exists");
			//
			JspWriter out = pageContext.getOut();
			HtmlCode html = new HtmlCode();
			//
			if (!m_oneLine)
				html.addElement(new hr("90%", "left"));
			//
			img img = new img ("basket.gif");
			img.setBorder(0);
			a a = new a("basket.jsp");
			a.setClass("menuMain");
			if (m_oneLine)
			{
				a.addElement (img);
				a.addElement ("Basket");
				html.addElement(a);
				html.addElement("&nbsp;- ");
			}
			else
			{
				a.addElement ("Basket");
				a.addElement (img);
				html.addElement(a);
				//	List Content
				p p = new p();
				p.setClass("Cbasket");
				ArrayList<WebBasketLine> lines = wb.getLines();
				for (int i = 0; i < lines.size(); i++)
				{
					p.addElement("<br>");
					Object line = lines.get(i);
					p.addElement(line.toString());
				}
				p.addElement("<br><br>");
				html.addElement(p);
			//	html.addElement(new br());
			}
			//
			img = new img ("checkout.gif");
			img.setBorder(0);
			String url = CheckOutServlet.NAME;
			if (!request.isSecure())
				url = "./" + CheckOutServlet.NAME;
			a = new a(url);
			a.setClass("menuMain");
			a.addElement("Create Order");
			a.addElement(img);
			html.addElement(a);
			//
			html.output(out);
		}	//	web basket
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

}	//	CheckOutLinkTag
