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
 * Created on Nov 7, 2006
 */


package org.posterita.struts.pos;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.compiere.model.MCurrency;
import org.posterita.Constants;
import org.posterita.beans.CommissionBean;
import org.posterita.beans.ReportBean;
import org.posterita.businesslogic.POSTerminalManager;
import org.posterita.businesslogic.administration.CommissionManager;
import org.posterita.businesslogic.performanceanalysis.ReportDateManager;
import org.posterita.businesslogic.performanceanalysis.ReportManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;
import org.posterita.struts.core.DefaultForm;


public class CommissionAction extends POSDispatchAction
{

    public static final String GENERATE_COMMISSION="generateCommission";
    public ActionForward generateCommission(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        
        ReportBean bean = (ReportBean) df.getBean();
        MCurrency currency = POSTerminalManager.getCurrencyOfTerminalCashBook(ctx);
        request.setAttribute(Constants.CURRENCY_SYMBOLE,currency.getCurSymbol());
        
        Timestamp fromdate = ReportDateManager.getFromDate(bean);
        CommissionManager.generateCommission(ctx,fromdate);
        ArrayList list=CommissionManager.getCommissionAmt(ctx);
        String reportName = CommissionManager.getCommissionAmtAsCSV(ctx, list);
        String reportURI = ReportManager.getReportURI(reportName, request);
        
        request.getSession().setAttribute(Constants.COMMISSION_AMT,list);
        request.getSession().setAttribute(Constants.REPORT_URL, reportURI);
        
        return mapping.findForward(GENERATE_COMMISSION);
    }
    
    public static final String VIEW_COMMISSION="viewCommission";
    public ActionForward viewCommission(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        ArrayList<CommissionBean> list=CommissionManager.getCommissionAmt(ctx);
        String reportName = CommissionManager.getCommissionAmtAsCSV(ctx, list);
        String reportURI = ReportManager.getReportURI(reportName, request);
        
        request.getSession().setAttribute(Constants.COMMISSION_AMT,list);
        request.getSession().setAttribute(Constants.REPORT_URL, reportURI);
        
        return mapping.findForward(VIEW_COMMISSION);
    }
    
    
    public static final String VIEW_COMMISSION_DETAILS="viewCommissionDetails";
    public ActionForward viewCommissionDetails(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        
        CommissionBean bean = (CommissionBean) df.getBean();
       
        ArrayList list=CommissionManager.getCommissionDetails(ctx,bean.getCommissionAmtId());
        request.getSession().setAttribute(Constants.COMMISSION_AMT_DETAILS,list);
        
        return mapping.findForward(VIEW_COMMISSION_DETAILS);
    }
    
}
