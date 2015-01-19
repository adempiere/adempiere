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

import org.compiere.model.MDocType;
import org.compiere.model.MMailMsg;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPayment;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.WebEnv;
import org.compiere.util.WebSessionCtx;
import org.compiere.util.WebUser;
import org.compiere.util.WebUtil;


/**
 *  Web Order.
 *
 *  @author Jorg Janke
 *  @version $Id: OrderServlet.java,v 1.2 2006/07/30 00:53:21 jjanke Exp $
 */
public class OrderServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -94012227184686536L;

	/**	Logging						*/
	private static CLogger			log = CLogger.getCLogger(OrderServlet.class);
//	private static Logger	s_log = Logger.getCLogger(OrderServlet.class);

	/** Name						*/
	static public final String			NAME = "orderServlet";

	/**
	 *	Initialize global variables
	 *
	 *  @param config Configuration
	 *  @throws ServletException
	 */
	public void init(ServletConfig config)
		throws ServletException
	{
		super.init(config);
		if (!WebEnv.initWeb(config))
			throw new ServletException("OrderServlet.init");
	}   //  init

	/**
	 * Get Servlet information
	 * @return Info
	 */
	public String getServletInfo()
	{
		return "Adempiere Web Order Servlet";
	}	//	getServletInfo

	/**
	 * Clean up resources
	 */
	public void destroy()
	{
		log.fine("");
	}   //  destroy

	
	/**************************************************************************
	 *  Process the HTTP Get request.
	 * 	(logout, deleteCookie)
	 *  Sends Web Request Page
	 *
	 *  @param request request
	 *  @param response response
	 *  @throws ServletException
	 *  @throws IOException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		log.info("Get from " + request.getRemoteHost() + " - " + request.getRemoteAddr());
		doPost (request, response);
	}	//	doGet

	/**
	 *  Process the HTTP Post request
	 *
	 *  @param request request
	 *  @param response response
	 *  @throws ServletException
	 *  @throws IOException
	 */
	public void doPost (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		log.info("Post from " + request.getRemoteHost() + " - " + request.getRemoteAddr());
		Properties ctx = JSPEnv.getCtx(request);
		HttpSession session = request.getSession(true);
		session.removeAttribute(WebSessionCtx.HDR_MESSAGE);

		//	Web User/Basket
		WebUser wu = (WebUser)session.getAttribute(WebUser.NAME);
		WebBasket wb = (WebBasket)session.getAttribute(WebBasket.NAME);
		MOrder order = null;
		
		boolean done = false;
		String url = "/paymentInfo.jsp";
		//	Not logged in
		if (wu == null || !wu.isLoggedIn())
		{
			session.setAttribute("CheckOut", "Y");	//	indicate checkout
			url = "/login.jsp";
			done = true;
		}
		else	//	Order parameter
			order = getOrder(request, ctx);
		
		//	We have an Order
		if (!done && order != null)
		{
			if (processOrder(request, order))
				url = "/orders.jsp";
			else
			{
				WebOrder wo = new WebOrder (order);
				MPayment p = createPayment (session, ctx, wu, wo);
				if (p != null)
				{
					session.setAttribute (PaymentServlet.ATTR_PAYMENT, p);
					session.setAttribute(WebOrder.NAME, wo);
				}
				else
					url = "/orders.jsp";
			}
			done = true;
		}
		
		//	Nothing in basket
		if (!done && (wb == null || wb.getLineCount() == 0))
		{
			url = "/basket.jsp";
			done = true;
		}
		//	Create Order & Payment Info
		if (!done)
		{
			WebOrder wo = new WebOrder(wu, wb, ctx);
			//	We have an order - do delete basket & checkout indicator
			if (wo.isInProgress() || wo.isCompleted())
			{
				session.removeAttribute(CheckOutServlet.ATTR_CHECKOUT);
				session.removeAttribute(WebBasket.NAME);
				sendEMail(request, ctx, wo, wu);
			}
			//	If the Order is negative, don't create a payment
			if (wo.getGrandTotal().compareTo(Env.ZERO) > 0)
			{
				session.setAttribute(WebOrder.NAME, wo);
				MPayment p = createPayment (session, ctx, wu, wo);
				if (p == null)
				{
					WebUtil.createForwardPage(response, "Payment could not be created", "orders.jsp", 5);
					return;
				}
				else
					session.setAttribute (PaymentServlet.ATTR_PAYMENT, p);
			}
			else
			{
				url = "/orders.jsp";
			}
		}

		log.info ("Forward to " + url);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher (url);
		dispatcher.forward (request, response);
	}	//	doPost

	
	/**************************************************************************
	 * 	Create Payment, but don't save it
	 * 	@param session session
	 * 	@param ctx context
	 * 	@param wu web user
	 * 	@param wo Order
	 * 	@return Payment
	 */
	private MPayment createPayment(HttpSession session, Properties ctx,
		WebUser wu,	WebOrder wo)
	{
		//	See PaymentServlet.doGet
		MPayment p = new MPayment(ctx, 0, null);
		p.setAD_Org_ID(wo.getAD_Org_ID());
		p.setIsSelfService(true);
		p.setAmount (wo.getC_Currency_ID(), wo.getGrandTotal ()); //	for CC selection
		p.setIsOnline (true);
		//	Sales CC Trx
		p.setC_DocType_ID(true);
		p.setTrxType(MPayment.TRXTYPE_Sales);
		p.setTenderType(MPayment.TENDERTYPE_CreditCard);
		//	Order Info
		p.setC_Order_ID(wo.getC_Order_ID());
		//	BP Info
		p.setBP_BankAccount(wu.getBankAccount());
		//
		return p;
	}	//	createPayment

	/**
	 *	Get Order
	 *	@param request request
	 * 	@param ctx context
	 *	@return true if processed
	 */
	private MOrder getOrder (HttpServletRequest request, Properties ctx)
	{
		//	Order
		String para = WebUtil.getParameter (request, "C_Order_ID");
		if (para == null || para.length() == 0)
			return null;
		int C_Order_ID = 0;
		try
		{
			C_Order_ID = Integer.parseInt (para);
		}
		catch (NumberFormatException ex)
		{
		}
		if (C_Order_ID == 0)
			return null;

		log.fine("C_Order_ID=" + C_Order_ID);
		return new MOrder (ctx, C_Order_ID, null);
	}	//	getOrder
	
	
	/**
	 *	Process Order
	 *	@param request request
	 *	@param order order
	 *	@return true if processed/ok
	 */
	private boolean processOrder (HttpServletRequest request, MOrder order)
	{
		//	Doc Action
		String DocAction = WebUtil.getParameter (request, "DocAction");
		if (DocAction == null || DocAction.length() == 0)
			return false;

		MDocType dt = MDocType.get(order.getCtx(), order.getC_DocType_ID());
		if (!order.isSOTrx() 
			|| order.getGrandTotal().compareTo(Env.ZERO) <= 0
			|| !MDocType.DOCBASETYPE_SalesOrder.equals(dt.getDocBaseType()))
		{
			log.warning("Not a valid Sales Order " + order);
			return true;
		}

		//	We have a Order No & DocAction
		log.fine("DocAction=" + DocAction);
		if (!MOrder.DOCACTION_Void.equals(DocAction))
		{
			//	Do not complete Prepayment
			if (MOrder.STATUS_WaitingPayment.equals(order.getDocStatus()))
				return false;
			if (MDocType.DOCSUBTYPESO_PrepayOrder.equals(dt.getDocSubTypeSO()))
				return false;
			if (!MOrder.DOCACTION_Complete.equals(DocAction))
			{
				log.warning("Invalid DocAction=" + DocAction);
				return true;
			}
		}
		order.setDocAction (DocAction, true);	//	force creation
		boolean ok = order.processIt (DocAction);
		order.saveEx();
		return ok;
	}	//	processOrder


	/**
	 * 	Send Order EMail.
	 * 	@param request request
	 * 	@param ctx context
	 * 	@param wo web order
	 * 	@param wu web user
	 */
	private void sendEMail (HttpServletRequest request, Properties ctx, WebOrder wo, WebUser wu)
	{
		StringBuffer message = new StringBuffer("\n"); 
		//
		MOrder mo = wo.getOrder();
		if (mo != null)
		{
			MOrderLine[] ol = mo.getLines(true, null);
			for (int i = 0; i < ol.length; i++)
			{
				message.append("\n").append(ol[i].getQtyOrdered()).append(" * ")
					.append(ol[i].getName());
				if (ol[i].getDescription() != null)
					message.append(" - ").append(ol[i].getDescription());
				message.append(" (").append(ol[i].getPriceActual())
					.append(") = ").append(ol[i].getLineNetAmt());
			}	//	line
		}	//	order
		message.append("\n\n")
			.append(Msg.getElement(ctx, "C_Order_ID"))
			.append(": ")
			.append(wo.getDocumentNo())
			.append(" - ").append(Msg.getElement(ctx, "GrandTotal"))
			.append(": ").append(wo.getGrandTotal());

		
		JSPEnv.sendEMail(request, wu, MMailMsg.MAILMSGTYPE_OrderAcknowledgement,
			new Object[]{
				wo.getDocumentNo(),
				wu.getName(),
				message.toString()});
	}	//	sendEMail


}	//	OrderServlet
