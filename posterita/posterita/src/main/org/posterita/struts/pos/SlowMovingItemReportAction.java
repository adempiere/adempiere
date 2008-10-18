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
 * Created on 25-Jul-2005 by alok
 *
 */

package org.posterita.struts.pos;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.posterita.Constants;
import org.posterita.beans.ReportBean;
import org.posterita.businesslogic.performanceanalysis.POSReportManager;
import org.posterita.businesslogic.performanceanalysis.ReportDateManager;
import org.posterita.core.TmkJSPEnv;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;
import org.posterita.struts.core.DefaultForm;

public class SlowMovingItemReportAction extends POSDispatchAction
{
	public static final String GET_CURRENT_DAY_REPORT = "getCurrentDayReport";	
	public ActionForward getCurrentDayReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
	{
		ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
       
        Properties ctx = TmkJSPEnv.getCtx(request);
        
        Calendar cal = Calendar.getInstance();
        
        Timestamp toDate = new Timestamp(cal.getTimeInMillis());
        
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        
        Timestamp fromDate = new Timestamp(cal.getTimeInMillis());
        
        ArrayList list=POSReportManager.getMaxMinSoldProducts(ctx,"asc",fromDate,toDate);
        request.getSession().setAttribute(Constants.MIN_SOLD_ITEMS,list);       
        
		return mapping.findForward(GET_CURRENT_DAY_REPORT);
	}
	
	public static final String GET_CURRENT_MONTH_REPORT = "getCurrentMonthReport";
	public ActionForward getCurrentMonthReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
	{
		ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        
        Calendar cal = Calendar.getInstance();
        Timestamp toDate = new Timestamp(cal.getTimeInMillis());
        
        cal.set(Calendar.DATE,1);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        
        Timestamp fromDate = new Timestamp(cal.getTimeInMillis());
        
        ArrayList list=POSReportManager.getMaxMinSoldProducts(ctx,"asc",fromDate,toDate);
        request.getSession().setAttribute(Constants.MIN_SOLD_ITEMS,list);   
        
		return mapping.findForward(GET_CURRENT_MONTH_REPORT);
	}
	
	public static final String GET_CUSTOM_REPORT = "getCustomReport";
	public ActionForward getCustomReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws OperationException, ApplicationException
	{
		ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;        
       
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        
        ReportBean bean = (ReportBean) df.getBean();        
        Timestamp fromDate = ReportDateManager.getFromDate(bean);
        Timestamp toDate = ReportDateManager.getToDate(bean);
        
        ArrayList list=POSReportManager.getMaxMinSoldProducts(ctx,"asc",fromDate,toDate);
        request.getSession().setAttribute(Constants.MIN_SOLD_ITEMS,list);   
        
		return mapping.findForward(GET_CUSTOM_REPORT);
	}
	
	
}
