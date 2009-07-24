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
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.ecs.xhtml.a;
import org.apache.ecs.xhtml.br;
import org.apache.ecs.xhtml.hr;
import org.compiere.model.MStore;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.HtmlCode;
import org.compiere.util.WebInfo;
import org.compiere.util.WebSessionCtx;
import org.compiere.util.WebUser;

/**
 *  Info Links (Menu).
 * 	Creates Invoice/Payment/Asset/AddressInfo/PaymentInfo Link
 *  <pre>
 *  <cws:infoLink/>
 *	</pre>
 *
 *  @author Jorg Janke
 *  @version $Id: InfoLinkTag.java,v 1.6 2006/09/21 20:45:30 jjanke Exp $
 */
public class InfoLinkTag extends TagSupport
{
	/** SV */
	private static final long serialVersionUID = 7608741032814139346L;
	/**	Logger	*/
	private static CLogger log = CLogger.getCLogger (InfoLinkTag.class);
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
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		Properties ctx = JSPEnv.getCtx(request);		//	creates wsc/wu
		WebSessionCtx wsc = WebSessionCtx.get(request);
		//
		HttpSession session = pageContext.getSession();
		WebUser wu = (WebUser)session.getAttribute(WebUser.NAME);
		if (wu != null && wu.isLoggedIn())
		{
			if (ctx != null)
			{
				WebInfo info = (WebInfo)session.getAttribute(WebInfo.NAME);
				if (info == null || wu.getAD_User_ID() != info.getAD_User_ID())
				{
					info = new WebInfo (ctx, wu);
					session.setAttribute (WebInfo.NAME, info);
				}
			}
			//
		//	log.fine("WebUser exists - " + wu);
			//
			JspWriter out = pageContext.getOut();
			HtmlCode html = new HtmlCode();
			//
			if (wu.isCustomer() || wu.isVendor())
				menuBPartner (html, wsc.wstore);
			if (wu.isSalesRep())
				menuSalesRep (html, wsc.wstore);
			if (wu.isEmployee() || wu.isSalesRep())
				menuUser (html, wu.isEmployee(), wsc.wstore);
			menuAll (html, wsc.wstore);
			//
			html.output(out);
		}
		else
		{
			if (CLogMgt.isLevelFiner())
				log.fine("No WebUser");
			if (session.getAttribute(WebInfo.NAME) == null)
				session.setAttribute (WebInfo.NAME, WebInfo.getGeneral());
		}
		return (SKIP_BODY);
	}   //  doStartTag

	/**
	 * 	Add Business Partner Links.
	 * 		My Assets
	 * 		My Invoices
	 * 		My Payments
	 * 		My Orders
	 * 		My Shipments
	 * 		My RfQ
	 *	@param html code
	 *	@param wstore web store
	 */
	private void menuBPartner (HtmlCode html, MStore wstore)
	{
		boolean first = true;
		if (wstore.isMenuAssets())
		{
			nl (html, first);		//		---	Assets
			first = false;
			a a = new a ("assets.jsp");
			a.setClass ("menuSub");
			a.addElement ("My Assets");
			html.addElement (a);
		}
		//
		if (wstore.isMenuInvoices())
		{
			nl (html, first);		//		---	Invoices
			first = false;
			a a = new a ("invoices.jsp");
			a.setClass ("menuSub");
			a.addElement ("My Invoices");
			html.addElement (a);
		}
		//
		if (wstore.isMenuPayments())
		{
			nl (html, first);		//		--- Payments
			first = false;
			a a = new a ("payments.jsp");
			a.setClass ("menuSub");
			a.addElement ("My Payments");
			html.addElement (a);
		}
		//
		if (wstore.isMenuOrders())
		{
			nl (html, first);		//		--- Orders
			first = false;
			a a = new a ("orders.jsp");
			a.setClass ("menuSub");
			a.addElement ("My Orders");
			html.addElement (a);
		}
		//
		if (wstore.isMenuShipments())
		{
			nl (html, first);		//		--- Shipments
			first = false;
			a a = new a ("shipments.jsp");
			a.setClass ("menuSub");
			a.addElement ("My Shipments");
			html.addElement (a);
		}
		//
		if (wstore.isMenuRfQs())
		{
			nl (html, first);		//		--- RfQs
			first = false;
			a a = new a ("rfqs.jsp");
			a.setClass ("menuSub");
			a.addElement ("My RfQ's");
			html.addElement (a);
		}
	}	//	menuCustomer

	/**
	 * 	Add Links for all.
	 * 		My Requests
	 * 		Interest Area
	 * 		Registration
	 *	@param html code
	 *	@param wstore web store
	 */
	private void menuAll (HtmlCode html, MStore wstore)
	{
        boolean first = false;
		if (wstore.isMenuRequests())
		{
			nl (html, first);		//	Requests
			a a = new a ("requests.jsp");
			a.setClass ("menuSub");
			a.addElement ("My Requests");
			html.addElement (a);
		}
		//
		if (wstore.isMenuInterests())
		{
			nl (html, first);		//		--- Interest Area
			a a = new a ("info.jsp");
			a.setClass ("menuSub");
			a.addElement ("Interest Area");
			html.addElement (a);
		}
		
		if (wstore.isMenuRegistrations())
		{
			nl (html, false);		//		--- Registration
			a a = new a ("registrations.jsp");
			a.setClass ("menuSub");
			a.addElement ("Registration");
			html.addElement (a);
		}
	}	//	menuAll

	/**
	 * 	Add Links for Sales Reps.
	 * 		Open Requests
	 * 		Advertisements
	 * 		Commissions
	 * 		C.Invoices
	 *	@param html code
	 *	@param wstore web store
	 */
	private void menuSalesRep (HtmlCode html, MStore wstore)
	{
		nl (html, true); 			//	------------
		//							--- Assigned Requests
		a a = new a ("requests_sr.jsp");
		a.setClass ("menuSub");
		a.addElement ("Open Requests");
		html.addElement (a);
		//
		nl (html, false);
		//							--- Advertisements
		a = new a ("advertisements.jsp");
		a.setClass ("menuSub");
		a.addElement ("Advertisements");
		html.addElement (a);
		//
		nl (html, false);
		//							--- Commissions
		a = new a ("commissionRuns.jsp");
		a.setClass ("menuSub");
		a.addElement ("Commissions");
		html.addElement (a);
		//							--- C.Invoices
		a = new a ("commissionedInvoices.jsp");
		a.setClass ("menuDetail");
		a.addElement ("C.Invoices");
		html.addElement (a);
		//
		nl (html, false);
	}	//	menuSalesRep

	/**
	 * 	Add Links for Users.
	 * 		Notes
	 * 		Expenses
	 *	@param html code
	 *	@param isEmployee employee
	 *	@param wstore web store
	 */
	private void menuUser (HtmlCode html, boolean isEmployee, MStore wstore)
	{
		nl (html, true); 			//	------------
		//							--- Notices
		if (isEmployee)
		{
			a a = new a ("notes.jsp");
			a.setClass ("menuMain");
			a.addElement ("Notices");
			html.addElement (a);
			//
			nl (html, false);
		}
		//							--- Expense
		a a = new a ("expenses.jsp");
		a.setClass ("menuSub");
		a.addElement ("Expenses");
		html.addElement (a);
	}	//	menuUser

	/**
	 * 	Add New Line / Break
	 * 	@param html code
	 * 	@param hr insert HR rather BR
	 */
	private void nl (HtmlCode html, boolean hr)
	{
		if (m_oneLine)
			html.addElement("&nbsp;- ");
		else if (hr)
			html.addElement(new hr("90%", "left"));
		else
			html.addElement(new br());
	}	//	nl

	/**
	 * 	End Tag
	 * 	@return EVAL_PAGE
	 * 	@throws JspException
	 */
	public int doEndTag() throws JspException
	{
		return EVAL_PAGE;
	}	//	doEndTag

}	//	InfoLinkTag
