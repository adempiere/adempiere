/******************************************************************************
 *  Product: Posterita Web-Based POS and Adempiere Plugin                     *
 *  Copyright (C) 2008  Posterita Ltd                                         *
 *  This file is part of POSterita                                            *
 *                                                                            *
 *  POSterita is free software; you can redistribute it and/or modify         *
 *  it under the terms of the GNU General Public License as published by      *
 *  the Free Software Foundation; either version 2 of the License, or         *
 *  (at your option) any later version.                                       *
 *                                                                            *
 *  This program is distributed in the hope that it will be useful,           *
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of            *
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the             *
 *  GNU General Public License for more details.                              *
 *                                                                            *
 *  You should have received a copy of the GNU General Public License along   *
 *  with this program; if not, write to the Free Software Foundation, Inc.,   *
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.               *
 *****************************************************************************/
package org.posterita.struts.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.posterita.pos.taglib.AjaxTag;

/**
 * @author Ashley G Ramdass
 * Apr 22, 2008
 */
public class AjaxAction extends BaseDispatchAction
{
    public static final String PROCESS_REQUEST_PARAM = "RequestProcessor";
    public static final String AJAX_TAG_PACKAGE = "org.posterita.pos.taglib";
    
    public ActionForward processRequest(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            String tagClass = request.getParameter(PROCESS_REQUEST_PARAM);
            AjaxTag tag = (AjaxTag)Class.forName(AJAX_TAG_PACKAGE + "." + tagClass).newInstance();
            tag.processRequest(request, response);
        }
        catch (Exception ex)
        {
            
        }
        return null;
    }
}
