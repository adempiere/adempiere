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

import org.apache.ecs.xhtml.b;
import org.apache.ecs.xhtml.br;
import org.apache.ecs.xhtml.option;
import org.apache.ecs.xhtml.select;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.compiere.util.CLogger;
import org.compiere.util.HtmlCode;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.compiere.wf.MWFActivity;

/**
 *	Workfloa Tag.
 *	<pre>
 *	<cws:workflow activityID="${act.AD_WF_Activity_ID}" />
 *	</pre>
 *	Depending on activity creates respose items
 *	
 *  @author Jorg Janke
 *  @version $Id: WorkflowTag.java,v 1.2 2006/07/30 00:53:21 jjanke Exp $
 */
public class WorkflowTag extends TagSupport
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -862613015608850843L;

	/**	Logging						*/
	private CLogger			log = CLogger.getCLogger(getClass());

	/**	Activity ID 				*/
	private String			m_activityID_el = null;

	/**	CSS Class Tag				*/
	private static final String C_MANDATORY = "Cmandatory";
	
	
	/**
	 * 	Set AD_WF_Activity_ID
	 *	@param info_el activity info
	 */
	public void setActivityID (String info_el)
	{
		m_activityID_el = info_el;
	}	//	setActivityID

	/**
	 *  Start Tag
	 *  @return SKIP_BODY
	 * 	@throws JspException
	 */
	public int doStartTag() throws JspException
	{
		Properties ctx = JSPEnv.getCtx((HttpServletRequest)pageContext.getRequest());

		//	Activity
		int AD_WF_Activity_ID = 0;
		String info = null;
		try
		{
			info = (String)ExpressionUtil.evalNotNull ("workflow", "activityID",
				m_activityID_el, String.class, this, pageContext);
			if (info != null && info.length () != 0)
				AD_WF_Activity_ID = Integer.parseInt (info);
		}
		catch (Exception e)
		{
			log.severe ("doStartTag - Activity" + e);
		}
		MWFActivity act = new MWFActivity (ctx, AD_WF_Activity_ID, null);
		if (AD_WF_Activity_ID == 0 || act == null || act.get_ID() != AD_WF_Activity_ID)
		{
			log.severe ("doStartTag - Activity Not found - " + m_activityID_el + " (" + info + ")");
			return (SKIP_BODY);
		}

		String name = null;
		if (act.isUserApproval())
			name = "IsApproved";
		else if (act.isUserManual())
			name = "IsConfirmed";
		else
			return (SKIP_BODY);

		//	YesNo
		option[] yesNoOptions = new option[3];
		yesNoOptions[0] = new option (" ");
		yesNoOptions[0].addElement(" ");
		yesNoOptions[0].setSelected (true);
		yesNoOptions[1] = new option ("Y");
		yesNoOptions[1].addElement(Util.maskHTML(Msg.translate(ctx, "Yes")));
		yesNoOptions[2] = new option ("N");
		yesNoOptions[2].addElement(Util.maskHTML(Msg.translate(ctx, "No")));
		select yesNoSelect = new select (name, yesNoOptions);
		yesNoSelect.setID("ID_" + name);
		yesNoSelect.setClass(C_MANDATORY);
		//
		String nameTrl = Msg.translate(ctx, name);

		//	Assemble
		HtmlCode html = new HtmlCode();
		html.addElement(new b(nameTrl));
		html.addElement(yesNoSelect);
		html.addElement(new br());

		JspWriter out = pageContext.getOut();
		html.output(out);
		//
		return (SKIP_BODY);
	}   //  doStartTag

	/**
	 * 	End Tag - NOP
	 * 	@return EVAL_PAGE
	 * 	@throws JspException
	 */
	public int doEndTag() throws JspException
	{
		return EVAL_PAGE;
	}	//	doEndTag
	
}	//	WorkflowTag
