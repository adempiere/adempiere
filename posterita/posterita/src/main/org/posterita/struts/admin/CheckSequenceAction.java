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
 * Created on 09-Feb-2006
 */


package org.posterita.struts.admin;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.posterita.core.TmkJSPEnv;

import org.posterita.businesslogic.administration.CheckSequenceManager;
import org.posterita.exceptions.SequenceUpdateException;
import org.posterita.struts.core.BaseDispatchAction;


public class CheckSequenceAction extends BaseDispatchAction
{
    public static final String CHECK_SEQUENCE="checkSequence";
    public ActionForward checkSequence(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        ActionForward fwd = init(mapping,form,request,response);
        
        if (fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
     
        try
        {
            CheckSequenceManager.runProcess(ctx); 
        }
        catch(SequenceUpdateException e)
        {
            postGlobalError("error.update.sequence",request);
            return mapping.getInputForward();
        }
        
        
        return mapping.findForward(CHECK_SEQUENCE);
        
    }
}
