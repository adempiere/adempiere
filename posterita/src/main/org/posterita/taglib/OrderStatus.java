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
* Created on Mar 3, 2005
*/

package org.posterita.taglib;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.bean.WriteTag;
import org.apache.struts.util.RequestUtils;
import org.compiere.process.DocumentEngine;

import org.posterita.core.UDIMap;
import org.posterita.user.WebUserInfo;



public class OrderStatus extends WriteTag
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static LinkedHashMap<String, String> webDocStatusMap;
    static LinkedHashMap<String, String> docStatusMap;
    
    static
    {
        webDocStatusMap = new LinkedHashMap<String, String>();
        webDocStatusMap.putAll(UDIMap.docStatusMap);
    }
    
    static
    {
        docStatusMap = new LinkedHashMap<String, String>();
        docStatusMap.putAll(webDocStatusMap);
        docStatusMap.put(DocumentEngine.STATUS_Approved,"Approved");
        docStatusMap.put(DocumentEngine.STATUS_NotApproved,"Not Approved");
        docStatusMap.put(DocumentEngine.STATUS_Reversed,"Reversed");
        docStatusMap.put(DocumentEngine.STATUS_Unknown,"??");
        docStatusMap.put(DocumentEngine.STATUS_WaitingPayment,"Waiting Payment");
        docStatusMap.put(DocumentEngine.STATUS_WaitingConfirmation,"Waiting Confirmation");
        docStatusMap.put(DocumentEngine.STATUS_Invalid,"Invalid");
    }
    

    
    public static LinkedHashMap getWebDocStatusMap()
    {
        return webDocStatusMap;
    }
    
    public static void setWebDocStatusMap(LinkedHashMap<String, String> webDocStatusMap)
    {
        OrderStatus.webDocStatusMap = webDocStatusMap;
    }
    
	public String formatValue(Object valueToFormat) throws JspException
	{
		if (valueToFormat instanceof String == false)
			throw new JspException("Expected java.lang.String, obtained" + valueToFormat.getClass().getName());

		String strValueToFormat = (String) valueToFormat;

		return format(strValueToFormat);
	}




	
    private String format(String strValueToFormat) 
    {	
        if (docStatusMap.containsKey(strValueToFormat))
            return (String) docStatusMap.get(strValueToFormat);
        
        
        return (String) docStatusMap.get(DocumentEngine.STATUS_Unknown);
    }


    @SuppressWarnings("deprecation")
	public int doStartTag() throws JspException
	{
	    
        HttpServletRequest  request = (HttpServletRequest) pageContext.getRequest();
        
        request.getSession().getAttribute(WebUserInfo.NAME);
        
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
