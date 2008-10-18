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
 * Created on Oct 27, 2006
 */


package org.posterita.struts.pos;

import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.posterita.Constants;
import org.posterita.beans.DunningBean;
import org.posterita.businesslogic.POSBpartnerInfoManager;
import org.posterita.businesslogic.administration.DunningManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;
import org.posterita.struts.core.DefaultForm;


public class DunningAction extends POSDispatchAction
{    
    
    public static final String INIT_PRINT_DUNNING = "initPrintDunning";
    public  ActionForward initPrintDunning(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        DunningManager.associateAllBpartnerWithDunning(ctx);
        ArrayList list = POSBpartnerInfoManager.getPartnerInfo(ctx,true);
        
        request.getSession().setAttribute(Constants.DEBTORS,list);
        request.getSession().removeAttribute(Constants.BUSINESSPARTNER);
        
        return mapping.findForward(INIT_PRINT_DUNNING);
    }
    
    public static final String PRINT_DUNNING_LETTERS="printDunningLetters";
    public ActionForward printDunningLetters(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        DunningBean bean=(DunningBean) df.getBean();
        
        DunningManager.printDunningLetters(ctx,bean);
        return mapping.findForward(PRINT_DUNNING_LETTERS);
    }
    
}