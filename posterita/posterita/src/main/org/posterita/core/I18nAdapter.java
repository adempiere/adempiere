/**
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2008  Posterita Ltd
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
 */

package org.posterita.core;

import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import org.apache.struts.Globals;
import org.displaytag.localization.I18nResourceProvider;
import org.displaytag.localization.LocaleResolver;
import org.posterita.core.bean.ElementBean;
import org.posterita.core.businesslogic.ElementManager;

public class I18nAdapter implements I18nResourceProvider, LocaleResolver
{

	public String getResource(String resourceKey, String defaultValue, 
			Tag tag, PageContext pageContext)
	{
		Properties ctx = TmkJSPEnv.getCtx((HttpServletRequest)pageContext.getRequest());
		
		String key = resourceKey;
		
		if (key == null)
		{
			key = defaultValue;
		}
		
		ElementBean msgBean = ElementManager.getMsg(ctx, key);
		return msgBean.getName();
	}

	/**
     * @see LocaleResolver#resolveLocale(HttpServletRequest)
     */
    public Locale resolveLocale(HttpServletRequest request)
    {
        Locale userLocale = null;
        HttpSession session = request.getSession(false);

        if (session != null)
        {
            userLocale = (Locale) session.getAttribute(Globals.LOCALE_KEY);
        }

        if (userLocale == null)
        {
            userLocale = request.getLocale();
        }

        return userLocale;
    }

}
