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
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.compiere.util.CLogger;
import org.compiere.util.Msg;

/**
 *  Message/Translation Tag.
 * 	<cws:message txt="AD_Message"/>
 *
 *  @author Jorg Janke
 *  @version $Id: MessageTag.java,v 1.2 2006/07/30 00:53:21 jjanke Exp $
 */
public class MessageTag  extends TagSupport
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5706293833314600951L;
	/**	Logger				*/
	private CLogger		log = CLogger.getCLogger (getClass());
	/** Text				*/
	private String		m_txt;

	/**
	 * 	Set text
	 * 	@param txt text to be translated
	 */
	public void setTxt (String txt)
	{
		m_txt = txt;
	}	//	setVar


	/**
	 *  Start Tag
	 *  @return SKIP_BODY
	 * 	@throws JspException
	 */
	public int doStartTag() throws JspException
	{
		if (m_txt != null && m_txt.length() > 0)
		{
			Properties ctx = JSPEnv.getCtx((HttpServletRequest)pageContext.getRequest());
			String msg = Msg.translate(ctx, m_txt);
			log.fine(m_txt + "->" + msg);
			//
			try
			{
				JspWriter out = pageContext.getOut();
				out.print (msg);
			}
			catch (Exception e)
			{
				throw new JspException(e);
			}
		}
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

}	//	MessageTag
