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
 * Created on Nov 16, 2005 by praveen
 *
 */
package org.posterita.struts.customer;

import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.posterita.core.TmkJSPEnv;

import org.posterita.Constants;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.SystemException;
import org.posterita.keyname.SalesRepKeyNamePair;
import org.posterita.struts.core.BaseDispatchAction;
import org.posterita.struts.core.DefaultForm;

public class SalesAction extends BaseDispatchAction
{
    public static final String INIT_SALES = "initSales";
    public ActionForward initSales(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, SystemException
    {
        ActionForward fwd = init(mapping,form,request,response);
        
        if(fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        
        //CustomerBean bean = new CustomerBean();    	
        //get sales reps
        ArrayList salesRep = SalesRepKeyNamePair.getKeyNamePairs(ctx);
        request.getSession().setAttribute(Constants.SALES_REP,salesRep);
        
        return mapping.findForward(INIT_SALES);
    }
    
    public static String INIT_SALES_FOR_EXISTING_CUSTOMER = "initSalesForExistingCustomer";
    public ActionForward initSalesForExistingCustomer(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, SystemException
    {
        ActionForward fwd = init(mapping,form,request,response);
        
        if(fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        
        DefaultForm df = (DefaultForm) form;
        
        Integer customerId = Integer.valueOf(df.getBpartnerId());
        
        request.getSession().setAttribute(Constants.CUSTOMER_ID, customerId);
        
        ArrayList salesRep = SalesRepKeyNamePair.getKeyNamePairs(ctx);
        
        Boolean canTradeIn = Boolean.valueOf(true);
        request.getSession().setAttribute(Constants.SALES_REP,salesRep);
        request.getSession().setAttribute(Constants.CAN_TRADE_IN, canTradeIn);
        
        return mapping.findForward(INIT_SALES);
    }
    
    public static final String CREATE_TRADE_IN_ORDER = "createTradeInOrder";
    public static final String CREATE_NATIS_ORDER = "createNatisOrder";
  
    
    public static final String PURCHASE_TRADE_IN_VEHICLE_AND_CREATE_CUSTOMER_ORDER = "purchaseTradeInVehicleAndCreateCustomerOrder";
  
}
