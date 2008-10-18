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
 * Created on Aug 11, 2005 by Praveen
 */

package org.posterita.taglib;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.bean.WriteTag;
import org.apache.struts.util.RequestUtils;

import org.posterita.core.TmkJSPEnv;
import org.posterita.model.MBank;

public class BankName extends WriteTag
{ 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public String formatValue(Object valueToFormat) throws JspException
	{
		if (valueToFormat instanceof Integer == false)
			throw new JspException("Expected java.lang.Integer, obtained" + valueToFormat.getClass().getName());

		Integer id = (Integer) valueToFormat;
		
		return format(id);
	}
	
	private String format(Integer integerValueToFormat) 
    {	
        
		if (integerValueToFormat == null)
            return "";
		
		 HttpServletRequest  request = (HttpServletRequest) pageContext.getRequest();
	        
	        Properties ctx = TmkJSPEnv.getCtx(request);
		
		MBank bankName = new MBank(ctx, integerValueToFormat.intValue(),null);
		
     return bankName.getName();
    }

	
	
	@SuppressWarnings("deprecation")
	public int doStartTag() throws JspException
	{
   
		if (ignore)
		{
			if (RequestUtils.lookup(pageContext, name, scope) == null)
				return (SKIP_BODY);
		}

		Object value = RequestUtils.lookup(pageContext, name, property, scope);
		if (value == null)
			return (SKIP_BODY);

		String output = formatValue(value);

		org.apache.struts.util.ResponseUtils.write(pageContext, output);

		return (SKIP_BODY);
	}

}
