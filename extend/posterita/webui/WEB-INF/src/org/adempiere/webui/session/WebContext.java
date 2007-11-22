/******************************************************************************
 * Product: Posterita Ajax UI 												  *
 * Copyright (C) 2007 Posterita Ltd.  All Rights Reserved.                    *
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
 * Posterita Ltd., 3, Draper Avenue, Quatre Bornes, Mauritius                 *
 * or via info@posterita.org or http://www.posterita.org/                     *
 *****************************************************************************/

package org.adempiere.webui.session;

import java.util.Properties;

import org.compiere.util.Env;
import org.compiere.util.Language;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date    Feb 25, 2007
 * @version $Revision: 0.10 $
 */
@SuppressWarnings("serial")
public final class WebContext extends Properties
{
    public WebContext()
    {
        super();
        /**
         * Set english as default language
         */
        this.put(Env.LANGUAGE, Language.getBaseAD_Language());
    }
    
    private static InheritableThreadLocal context = new InheritableThreadLocal() {
        protected WebContext initialValue()
        {
            return null;
        }
    };
    
    public static WebContext getCurrentInstance()
    {
        return (WebContext)context.get();
    }
    
    @SuppressWarnings("unchecked")
    public static void setCurrentInstance(WebContext webCtx)
    {
        context.set(webCtx);
    }
}
