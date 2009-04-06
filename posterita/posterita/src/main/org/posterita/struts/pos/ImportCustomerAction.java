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
 **/

package org.posterita.struts.pos;

import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.posterita.Constants;
import org.posterita.beans.CustomerBean;
import org.posterita.businesslogic.administration.ImportCustomerManager;
import org.posterita.businesslogic.performanceanalysis.ReportManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;
import org.posterita.struts.core.BaseDispatchAction;
import org.posterita.struts.core.DefaultForm;


public class ImportCustomerAction extends BaseDispatchAction
{
    public static final String IMPORT_POS_CUSTOMER ="importPOSCustomer";
    public ActionForward importPOSCustomer(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd = init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        DefaultForm df = (DefaultForm) form;
                
        ArrayList<CustomerBean> list = new ArrayList<CustomerBean>();
        
        try
        {
            list=ImportCustomerManager.importCustomer(ctx, df.getFile(), null);
        }
        
        catch(Exception e)
        {
            String message = e.getMessage();
            int index = message.indexOf("csv");
            String filename = message.substring(0,index+3);
            String error = message.substring(index+3);
            postGlobalError("error.process", error, request);
            String csvURI = ReportManager.getReportURI(filename,request); 
            request.getSession().setAttribute(Constants.IMPORT_FAIL_CSV_FILE, csvURI);
            return mapping.getInputForward();
        }
               
        request.getSession().setAttribute(Constants.CUSTOMER_CREATED,list); 
       // return mapping.findForward(IMPORT_POS_CUSTOMER);
        return mapping.getInputForward();
        
    }
}