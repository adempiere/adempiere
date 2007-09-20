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
package org.posterita.webstore.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.compiere.model.MBPartner;
import org.compiere.process.DocAction;
import org.compiere.util.Env;
import org.posterita.Constants;
import org.posterita.beans.DocumentHistortFilterBean;
import org.posterita.beans.DocumentHistoryBean;
import org.posterita.beans.FilterBean;
import org.posterita.businesslogic.POSReportManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;
import org.posterita.form.DocumentHistoryForm;
import org.posterita.order.UDIOrderTypes;
import org.posterita.struts.core.BaseDispatchAction;
import org.posterita.user.WebUserInfo;
import org.posterita.webstore.businesslogic.DocumentHistoryManager;

public class DocumentHistoryAction extends BaseDispatchAction
{

	public static final String INIT_HISTORY = "initHistory";
	 public ActionForward initHistory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OperationException, ApplicationException
	    {
			ActionForward fwd = init(mapping, form, request, response);
			
	        if (fwd != null)
	            return fwd;	
	        
	        Properties ctx = TmkJSPEnv.getCtx(request);

	    	DocumentHistoryForm dhf = (DocumentHistoryForm) form;
	    	DocumentHistoryBean bean = (DocumentHistoryBean) dhf.getBean();
	    	
	    	Calendar calendar = Calendar.getInstance();
	    	int month = calendar.get(Calendar.MONTH) + 1 ;
	    	int year    = calendar.get(Calendar.YEAR);
	    	
	    	bean.setMonth( Integer.valueOf(month) );
	    	bean.setYear( Integer.valueOf(year) );
	    	bean.setOrderType( UDIOrderTypes.POS_ORDER .getOrderType());
	    	bean.setDocStatus( DocAction.STATUS_Completed );
	    	
	    	dhf.populate( bean );
	    	
	    	//TODO OrderType should be taken from the bean
	    	ArrayList <DocumentHistoryBean> list = DocumentHistoryManager.getHistory(ctx, bean.getBpartnerId(), bean.getDocStatus(), bean.getMonth(), bean.getYear(), bean.getOrderType());

	    	getWebstoreFilterBean(ctx, request, list);
	    	
	    	request.getSession().setAttribute(Constants.DOCUMENT_HISTORY, list);
	    	
	    	return mapping.findForward(GET_HISTORY);
	    }
	
	public static final String GET_HISTORY = "getHistory";
    public ActionForward getHistory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OperationException, ApplicationException
    {
		ActionForward fwd = init(mapping, form, request, response);
		
        if (fwd != null)
            return fwd;	
        
        Properties ctx = TmkJSPEnv.getCtx(request);

    	DocumentHistoryForm dhf = (DocumentHistoryForm) form;
    	DocumentHistoryBean bean = (DocumentHistoryBean) dhf.getBean();
    	
    	//TODO OrderType should be taken from the bean
    	ArrayList <DocumentHistoryBean> list = DocumentHistoryManager.getHistory(ctx, bean.getBpartnerId(), bean.getDocStatus(), bean.getMonth(), bean.getYear(), bean.getOrderType());

    	getWebstoreFilterBean(ctx, request, list);
    	
    	request.getSession().setAttribute(Constants.DOCUMENT_HISTORY, list);
    	
    	return mapping.findForward(GET_HISTORY);
    	
    }
    
    public static void getWebstoreFilterBean(Properties ctx, HttpServletRequest request, ArrayList orderHistory) throws OperationException
    {
    	
    	DocumentHistortFilterBean filterBean = new DocumentHistortFilterBean(orderHistory);        

    	TreeSet docStatus = filterBean.getDocStatusList();
        request.getSession().setAttribute(Constants.DOC_STATUS, docStatus);
        ArrayList orderTypeList=POSReportManager.getAllOrderTypes(ctx);
        
        request.getSession().setAttribute(Constants.ORDER_TYPES,orderTypeList);
        
        WebUserInfo wuInfo = (WebUserInfo) request.getSession().getAttribute(WebUserInfo.NAME);
        int partnerId = wuInfo.getInfo().getC_BPartner_ID();
        MBPartner partner = new MBPartner(ctx, partnerId, null);

        
        if (partner.getAD_Org_ID() != Env.getAD_Org_ID(ctx))
        {
            TreeSet bPartners = (TreeSet)filterBean.getBPartnerList();
            request.getSession().setAttribute(Constants.BPARTNERS, bPartners);
        	
        }
    }   
   
}
