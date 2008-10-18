/** 
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Created on Nov 15, 2006 by ashley
 */

/**
 *  @author ashley
 */

package org.posterita.pos.taglib;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.compiere.wstore.JSPEnv;

import org.posterita.core.bean.ElementBean;
import org.posterita.core.businesslogic.ElementManager;

public class MessageTag extends TagSupport
{
	private static final long serialVersionUID = 1L;

	private String key;
	private boolean printing = false;
    private boolean textOnly = false;
	
	public int doStartTag() throws JspException
	{
		Properties ctx = JSPEnv.getCtx((HttpServletRequest)pageContext.getRequest());
		
		ElementBean elementBean = ElementManager.getMsg(ctx, key);
		
		String disp = "";
		
		if (isPrinting())
		{
			disp = elementBean.getPrintName();
		}
		else
		{
			disp = elementBean.getName();
		}
		
		try
		{
            JspWriter out = pageContext.getOut();
            if (this.isTextOnly())
            {
                out.write(elementBean.getName());
            }
            else
            {
    			String help = ((elementBean.getHelp() != null) ? elementBean.getHelp() : ""); 
    			out.print("<span title=\"");
    			out.print(elementBean.getDescription());
    			out.print("\" ");
    			out.print("name=\"help\" ");
    			out.print("tooltip=\"" + help + "\"");
    			out.print(">");
    			out.print(disp);
    			out.print("</span>");
            }
		}
		catch (Exception e)
		{
			throw new JspException(e);
		}
		
		return (SKIP_BODY);
	}
	
	public int doEndTag() throws JspException
	{
		return EVAL_PAGE;
	}

	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

	public boolean isPrinting()
	{
		return printing;
	}

	public void setPrinting(boolean printing)
	{
		this.printing = printing;
	}

    public boolean isTextOnly() 
    {
        return textOnly;
    }

    public void setTextOnly(boolean textOnly) 
    {
        this.textOnly = textOnly;
    }
}
