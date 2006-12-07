/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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
package org.compiere.jsf;

import javax.faces.context.FacesContext;
import javax.faces.el.VariableResolver;

import org.compiere.util.CLogger;

/**
 * this class exists only to signal that this is a custom lookup
 */
public class DynamicFieldVariableResolver extends VariableResolver
{
    private static final CLogger log=CLogger.getCLogger(DynamicFieldVariableResolver.class);
    
    private static final String LOOKUP_VAR="tab_lookup";

    private VariableResolver originalResolver;

    public DynamicFieldVariableResolver(VariableResolver variableResolver)
    {
        originalResolver=variableResolver;
    }

    public Object resolveVariable(FacesContext facescontext, String s)
    {
        //log.info("resolving var "+s);
        if(s.equals(LOOKUP_VAR))
        {
            //log.info("matching tab_lookup");
            return new DynamicFieldLookup(facescontext);
        }
        //log.info("failed to match");
        return originalResolver.resolveVariable(facescontext,s);
    }
}
