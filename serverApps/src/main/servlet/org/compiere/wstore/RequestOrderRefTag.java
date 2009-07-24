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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.ecs.xhtml.option;
import org.apache.ecs.xhtml.select;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Util;

/**
 * 	Request Order Reference Tag
 *  <pre>
 *	<cws:requestOrder bpartnerID="${webUser.bpartnerID}" />
 *	</pre>
 *	
 *  @author Jorg Janke
 *  @version $Id: RequestOrderRefTag.java,v 1.2 2006/07/30 00:53:21 jjanke Exp $
 */
public class RequestOrderRefTag extends TagSupport
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3277546457746182498L;

	/**	Logger							*/
	private static CLogger			log = CLogger.getCLogger (RequestOrderRefTag.class);
	
	/** Business Partner Parameter		*/
	private String m_bpartnerID_el	= null;
	
	/**
	 * 	Set B.Partner parameter
	 *	@param bpartnerID_el region info
	 */
	public void setBpartnerID (String bpartnerID_el)
	{
		m_bpartnerID_el = bpartnerID_el;
	}	//	setBPartner

	/**
	 *  Start Tag
	 *  @return SKIP_BODY
	 */
	public int doStartTag()
	{
		//	Parameter
		int C_BPartner_ID = 0;
		try
		{
			String info = (String)ExpressionUtil.evalNotNull ("requestOrder", "bpartnerID",
				m_bpartnerID_el, String.class, this, pageContext);
			if (info != null && info.length () != 0)
				C_BPartner_ID = Integer.parseInt (info);
		}
		catch (Exception e)
		{
			log.severe ("BPartner - " + e);
		}
		
		JspWriter out = pageContext.getOut();
		select select = getRefOrders(C_BPartner_ID);
		select.output(out);
		//
		return (SKIP_BODY);
	}   //  doStartTag

	/**
	 * 	Create Select List
	 * 	@param C_BPartner_ID b partner
	 *	@return select list
	 */
	private select getRefOrders(int C_BPartner_ID)
	{
		select select = new select(RequestServlet.P_REF_ORDER_ID, getOrders(C_BPartner_ID));
		select.setID("ID_" + RequestServlet.P_REF_ORDER_ID);
		return select;
	}	//	getRequestType

	/**
	 * 	Get the Request Type options
	 * 	@param C_BPartner_ID b partner
	 * 	@return array of options
	 */
	private option[] getOrders(int C_BPartner_ID)
	{
		Properties ctx = JSPEnv.getCtx((HttpServletRequest)pageContext.getRequest());
		ArrayList<option> list = new ArrayList<option>();
		//	Optional Element
		option o = new option ("0").addElement(" ");
		o.setSelected(true);
		list.add(o);
		//
		String sql = "SELECT C_Order_ID, DocumentNo, DateOrdered, GrandTotal "
			+ "FROM C_Order "
			+ "WHERE C_BPartner_ID=? "
			+ "ORDER BY CreatedBy DESC";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, C_BPartner_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				o = new option (rs.getString(1));
				String display = rs.getString(2)
					+ "_" + rs.getTimestamp(3)
					+ "_" + rs.getBigDecimal(4);
				o.addElement(Util.maskHTML(display));
				list.add(o);
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			try
			{
				if (pstmt != null)
					pstmt.close ();
			}
			catch (Exception e)
			{}
			pstmt = null;
		}

		//	Return to Array and return
		option options[] = new option [list.size()];
		list.toArray(options);
		log.fine("#" + options.length);
		return options;
	}	//	getOptions

}	//	RequestOrderReference
