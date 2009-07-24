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
import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.WebEnv;
import org.compiere.util.WebSessionCtx;
import org.compiere.util.WebUtil;
 
/**
 *  Shopping Basket.
 * 	you could add a new line via parameter
 * 	?M_Product_ID=11&Name=aaaaa&Quantity=1&Price=11.11
 * 	if price list found, the price will be potentially overwritten
 *
 *  @author Jorg Janke
 *  @version  $Id: BasketServlet.java,v 1.2 2006/07/30 00:53:21 jjanke Exp $
 */
public class BasketServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3636119916937667318L;
	/**	Logging						*/
	private static CLogger			log = CLogger.getCLogger(BasketServlet.class);
	/** Name						*/
	static public final String		NAME = "basketServlet";
	/** SalesRep Parameter			*/
	static public final String		P_SalesRep_ID = "SalesRep_ID";
	/** Product Parameter			*/
	static public final String		P_Product_ID = "M_Product_ID";
	
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
			throw new ServletException("BasketServlet.init");
	}   //  init

	/**
	 * Get Servlet information
	 * @return Info
	 */
	public String getServletInfo()
	{
		return "Adempiere Web Basket";
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
	 *
	 *  @param request request
	 *  @param response response
	 *  @throws ServletException
	 *  @throws IOException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		log.info("From " + request.getRemoteHost() + " - " + request.getRemoteAddr()
			+ " - " + request.getRequestURL());
		Properties ctx = JSPEnv.getCtx(request);
		HttpSession session = request.getSession(true);
		session.removeAttribute(WebSessionCtx.HDR_MESSAGE);

		//	Create WebBasket
		WebBasket wb = (WebBasket)session.getAttribute(WebBasket.NAME);
		if (wb == null)
			wb = new WebBasket();
		session.setAttribute(WebBasket.NAME, wb);
		//	SalesRep
		int SalesRep_ID = WebUtil.getParameterAsInt (request, P_SalesRep_ID);
		if (SalesRep_ID != 0)
		{
			wb.setSalesRep_ID(SalesRep_ID);
			log.fine("SalesRep_ID=" + SalesRep_ID);
		}

		//	Get Price List
		PriceList pl = (PriceList)session.getAttribute(PriceList.NAME);
		if (pl == null)
		{
			log.fine("No Price List in session");
			pl = (PriceList)request.getAttribute(PriceList.NAME);
		}
		log.fine("PL=" + pl);

		//	Do we delete?	Delete_x
		deleteLine (request, wb);
		//	Do we add?	Add_x
		addLine (request, pl, wb);

		log.info(wb.toString());
		//	Go back to basket
		String url = "/basket.jsp";
		log.info ("Forward to " + url);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher (url);
		dispatcher.forward (request, response);
	}	//	doGet

	/**
	 * 	Add Line
	 * 	@param request request
	 * 	@param pl price list
	 * 	@param wb web basket
	 */
	private void addLine (HttpServletRequest request, PriceList pl, WebBasket wb)
	{
		Properties ctx = JSPEnv.getCtx(request);
		HttpSession session = request.getSession(true);
		
		//	Get Parameter
		int M_PriceList_ID = WebUtil.getParameterAsInt (request, "M_PriceList_ID");
		int M_PriceList_Version_ID = WebUtil.getParameterAsInt (request, "M_PriceList_Version_ID");
		wb.setM_PriceList_ID (M_PriceList_ID);
		wb.setM_PriceList_Version_ID (M_PriceList_Version_ID);
		//
		int M_Product_ID = WebUtil.getParameterAsInt (request, P_Product_ID);
		String Name = WebUtil.getParameter (request, "Name");
		String sQuantity = WebUtil.getParameter (request, "Quantity");
		String sPrice = WebUtil.getParameter (request, "Price");

		//	Search for Product ID	Add_134 = Add
		Enumeration en = request.getParameterNames ();
		while (M_Product_ID == 0 && en.hasMoreElements ())
		{
			String parameter = (String)en.nextElement ();
			if (parameter.startsWith ("Add_"))
			{
				if (WebUtil.exists (request, parameter)) //	to be sure
				{
					try
					{
						M_Product_ID = Integer.parseInt (parameter.substring (4));
						log.fine("Found Parameter=" + parameter + " -> " + M_Product_ID);
						if (!WebUtil.exists(sQuantity))
							sQuantity = WebUtil.getParameter (request, "Qty_" + M_Product_ID);
						if (!WebUtil.exists(sPrice))
							sPrice = WebUtil.getParameter (request, "Price_" + M_Product_ID);
						if (!WebUtil.exists(Name))
							Name = WebUtil.getParameter (request, "Name_" + M_Product_ID);
						log.fine("Found Parameters " + Name + ",Qty=" + sQuantity + ",Price=" + sPrice);
					}
					catch (Exception ex)
					{
						log.warning ("ParseError for " + parameter + " - " + ex.toString ());
					}
				}
			}
		}
		if (M_Product_ID == 0)
			return;

		//	****	Set Qty	
		BigDecimal Qty = null;
		try
		{
			if (sQuantity != null && sQuantity.length () > 0)
				Qty = new BigDecimal (sQuantity);
		}
		catch (Exception ex1)
		{
			log.warning ("(qty) - " + ex1.toString());			
		}
		if (Qty == null)
			Qty = Env.ONE;

		//	****	Set Price
		BigDecimal Price = null;
		//	Find info in current price list
		if (M_Product_ID != 0 && pl != null)
		{
			PriceListProduct plp = pl.getPriceListProduct(M_Product_ID);
			if (plp != null)
			{
				Price = plp.getPrice ();
				Name = plp.getName ();
				log.fine("Found in PL = " + Name + " - " + Price);
			}
		}
		/**	if not found inPL and exists as parameter
		try 	
		{
			if (Price == null && sPrice != null && sPrice.length () > 0)
				Price = new BigDecimal (sPrice);
		}
		catch (Exception ex1)
		{
			log.warn ("addLine (price) - " + ex1.toString());			
		}
		/**/
		//	Price not in session price list and not as parameter 
		if (Price == null && (pl == null || pl.isNotAllPrices()))
		{
			//	Create complete Price List
			int AD_Client_ID = Env.getContextAsInt(ctx, "AD_Client_ID");
			pl = PriceList.get (ctx, AD_Client_ID, M_PriceList_ID, null, null, true);
			session.setAttribute(PriceList.NAME, pl);	//	set on session level
			PriceListProduct plp = pl.getPriceListProduct(M_Product_ID);
			if (plp != null)
			{
				Price = plp.getPrice ();
				Name = plp.getName ();
				log.fine("Found in complete PL = " + Name + " - " + Price);
			}
		}

		
		if (Price != null)
		{
			WebBasketLine wbl = wb.add (M_Product_ID, Name, Qty, Price);
			log.fine(wbl.toString());			
		}
		else	//	Price not found
			log.warning ("Product Price not found - M_Product_ID=" + M_Product_ID
				+ ", Name=" + Name);

	}	//	addLine

	/**
	 *	Delete Line.
	 *		Delete_x
	 *	@param request request
	 *	@param wb web basket
	 */
	private void deleteLine (HttpServletRequest request, WebBasket wb)
	{
		try
		{
			String enc = request.getCharacterEncoding();
			if (enc == null)
				request.setCharacterEncoding(WebEnv.ENCODING);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "Set CharacterEncoding=" + WebEnv.ENCODING, e);
		}
		Enumeration en = request.getParameterNames();
		while (en.hasMoreElements())
		{
			String parameter = (String)en.nextElement();
			if (parameter.startsWith("Delete_"))
			{
				try
				{
					int line = Integer.parseInt (parameter.substring (7));
					log.fine("Delete parameter=" + parameter + " -> " + line);
					wb.delete(line);
				}
				catch (NumberFormatException ex)
				{
					log.warning("ParseError for " + parameter + " - " + ex.toString());
				}
			}
		}
	}	//	deleteLine


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
	//	log.info("Post from " + request.getRemoteHost() + " - " + request.getRemoteAddr());
		doGet(request, response);
	}
}   //  Basket
