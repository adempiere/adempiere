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
 * Created on Jul 26, 2006
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
import org.posterita.beans.BPartnerInfoBean;
import org.posterita.businesslogic.MenuManager;
import org.posterita.businesslogic.POSBpartnerInfoManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;
import org.posterita.factory.POSMenuFactory;
import org.posterita.struts.core.DefaultForm;


public class BpartnerInfoAction extends POSDispatchAction
{
    public static final String GET_BPARTNER_INFO = "getBpartnerInfo";
    public  ActionForward getBpartnerInfo(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        
        BPartnerInfoBean bean=(BPartnerInfoBean) df.getBean();
        
        ArrayList list = POSBpartnerInfoManager.getPartnerInfo(ctx,bean.getPartnerName(),bean.getIsCustomer());
        
       request.getSession().setAttribute(Constants.BUSINESSPARTNER,list);
       request.getSession().removeAttribute(Constants.DEBTORS);
       
        return mapping.findForward(GET_BPARTNER_INFO);
    }
    
    
    public static final String GET_BPARTNER_TRX_DETAILS = "getBpartnerTrxDetails";
    public  ActionForward getBpartnerTrxDetails(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx=TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        
        BPartnerInfoBean bean=(BPartnerInfoBean) df.getBean();
        
        boolean access=MenuManager.getRoleMenuAccess(ctx,POSMenuFactory.SMENU_VIEW_BPINFO);
        if(access==false)
        {           
            postGlobalError("error.access.not.allowed",request);             
            return new ActionForward(bean.getForward());
        }
        
        ArrayList list = POSBpartnerInfoManager.getbPartnerTrxDetails(ctx,bean.getBpartnerId(),bean.getIsCustomer());
        ArrayList orderList=POSBpartnerInfoManager.getBpartnerOrderHistory(ctx,bean.getBpartnerId());
        ArrayList infoList = POSBpartnerInfoManager.getPartnerInfo(ctx,bean.getBpartnerId(),bean.getIsCustomer());
        
        request.getSession().setAttribute(Constants.BUSINESSPARTNERINFO,infoList);
       request.getSession().setAttribute(Constants.BUSINESSPARTNER_TRX_DETAILS,list);
       request.getSession().setAttribute(Constants.BUSINESSPARTNER_ORDER_DETAILS,orderList);
       
        return mapping.findForward(GET_BPARTNER_TRX_DETAILS);
    }
}
