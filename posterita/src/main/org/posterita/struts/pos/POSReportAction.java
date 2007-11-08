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
 * Created on May 9, 2006
 */


package org.posterita.struts.pos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.compiere.util.DisplayType;

import org.posterita.Constants;
import org.posterita.beans.POSHistoryBean;
import org.posterita.beans.ReportBean;
import org.posterita.businesslogic.CSVReportManager;
import org.posterita.businesslogic.POSReportManager;
import org.posterita.businesslogic.ReportDateManager;
import org.posterita.businesslogic.ReportManager;
import org.posterita.core.TabularReport;
import org.posterita.core.TmkJSPEnv;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;
import org.posterita.form.POSHistoryForm;
import org.posterita.order.UDIOrderTypes;
import org.posterita.struts.core.BaseDispatchAction;
import org.posterita.struts.core.DefaultForm;


public class POSReportAction extends BaseDispatchAction
{
    
    public static final String GET_MIN_SOLD_ITEMS="getMinSoldItems";
    public ActionForward getMinSoldItems(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        
        ReportBean bean = (ReportBean) df.getBean();
        
        String fromdate = ReportDateManager.getFromDate(bean);
        String todate = ReportDateManager.getToDate(bean);
        
        ArrayList list=POSReportManager.getMaxMinSoldProducts(ctx,"asc",fromdate,todate);
        request.getSession().setAttribute(Constants.MIN_SOLD_ITEMS,list);
        
        return mapping.findForward(GET_MIN_SOLD_ITEMS);
    }
    
    
    public static final String GET_MAX_SOLD_ITEMS="getMaxSoldItems";
    public ActionForward getMaxSoldItems(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        
        ReportBean bean = (ReportBean) df.getBean();
        
        String fromdate = ReportDateManager.getFromDate(bean);
        String todate = ReportDateManager.getToDate(bean);
        
        ArrayList list=POSReportManager.getMaxMinSoldProducts(ctx,"desc",fromdate,todate);
        request.getSession().setAttribute(Constants.MAX_SOLD_ITEMS,list);
        
        return mapping.findForward(GET_MAX_SOLD_ITEMS);
    }
    
    public static final String GET_STOCK_MOVEMENT="getStockMovement";
    public ActionForward getStockMovement(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        
        ReportBean bean = (ReportBean) df.getBean();
        
        String fromdate = ReportDateManager.getFromDate(bean);
        String todate = ReportDateManager.getToDate(bean);
        
        ArrayList list=POSReportManager.getStockMovementReport(ctx,fromdate,todate);
        request.getSession().setAttribute(Constants.STOCK_MOVEMENT,list);
        
        return mapping.findForward(GET_STOCK_MOVEMENT);
    }
    
    
    public static final String INIT_POS_HISTORY="initPOSHistory";
    public ActionForward initPOSHistory(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        Properties ctx = TmkJSPEnv.getCtx(request);      
        ArrayList orderTypeList=POSReportManager.getAllOrderTypes(ctx);
        ArrayList paymentRuleList=POSReportManager.getAllPaymentRule(ctx);
        request.getSession().setAttribute(Constants.ORDER_TYPES,orderTypeList);
        request.getSession().setAttribute(Constants.PAYMENT_RULES,paymentRuleList);
        request.setAttribute(Constants.ALL_ORDER_HISTORY,"allOrderHistory");
        getPOSHistory(mapping,new POSHistoryForm(),request,response);        
        
        return mapping.findForward(INIT_POS_HISTORY);
    }
    
    
    public static final String GET_POS_HISTORY="getPOSHistory";
    
    public ActionForward getPOSHistory(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        Properties ctx = TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        POSHistoryBean bean = (POSHistoryBean) df.getBean();
       
        if(bean.getHistoryType()==null)
        {
        	String historyType = (String)request.getAttribute(Constants.ALL_ORDER_HISTORY);
        	bean.setHistoryType(historyType);
        }
        
        if(bean.getHistoryType()==null || !bean.getHistoryType().equalsIgnoreCase(Constants.ALL_ORDER_HISTORY))
        {
        	if(bean.getOrderType()==null)
	        {
	            String orderType=(String)request.getSession().getAttribute(Constants.POS_HISTORY_ORDER_TYPE);
	            bean.setOrderType(orderType);
	        }
        }
        ArrayList list=POSReportManager.getOrderHistory(ctx,bean.getOrderType(),bean.getDocStatus(),bean.getMonth(),bean.getYear(),bean.getPaymentRule(),null);
        request.getSession().setAttribute(Constants.POS_HISTORY,list);
        if(bean.getOrderType()!=null)
        {
            request.getSession().setAttribute(Constants.POS_HISTORY_ORDER_TYPE,bean.getOrderType());   
        }
        else
        {
            String orderType=(String)request.getSession().getAttribute(Constants.POS_HISTORY_ORDER_TYPE);  
            request.getSession().setAttribute(Constants.POS_HISTORY_ORDER_TYPE,orderType);   
        }
        
        
        return mapping.findForward(GET_POS_HISTORY);
    }
    
    //-----------------------------------------------------------------------------------------------------
    
    public static final String GET_CUSTOM_REPORT = "getCustomReport";
    public static final String GET_CUSTOM_MOVING_ITEMS = "getCustomMovingItems";
    public ActionForward getCustomFastSlowMovingItems(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        
        ReportBean bean = (ReportBean) df.getBean();
        
        String title = " Moving Items Report";
        String subtitle = "subtitle";
        
        String reportType = bean.getReportType();
        if(reportType.equalsIgnoreCase("asc"))
        {
        	title = "Slow" + title;
        }
        else if(reportType.equalsIgnoreCase("desc"))
        {
        	title = "Fast" + title;
        }
        else
        {
        	throw new OperationException("invalid report type: " + reportType);
        }
        
        String dateRange = bean.getDateRange();
        String timePeriod = bean.getTimePeriod();
        
        String fromDate = null;
        String toDate = null;
        
        //setting from and to dates
        if(dateRange.endsWith(Constants.FIXED_DATE_RANGE))
        {
        	if(timePeriod==null)
        	{
        		throw new OperationException("Invalid Parameter for timePeriod. timePeriod is null");
        	}
        	
        	Date startDate = ReportDateManager.getStartDateForPeriod(timePeriod);
        	Date endDate = ReportDateManager.getEndDateForPeriod(timePeriod);
        	
        	//SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        	SimpleDateFormat sdf = DisplayType.getDateFormat(DisplayType.Date);
        	fromDate = sdf.format(startDate);
        	toDate = sdf.format(endDate);
        	
        	subtitle = "For " + timePeriod;
        }
        else if(dateRange.endsWith(Constants.CUSTOM_DATE_RANGE))
        {
        	boolean error = false;
        	ActionMessages messages = new ActionMessages();
        	ActionMessage message = null;
        	
        	
        	if((bean.getFromDate()==null)||(bean.getFromDate()==""))
            {            	
            	message = new ActionMessage("error.required.fromDate");
            	messages.add(ActionMessages.GLOBAL_MESSAGE, message);            	
            	error = true;
            }
            
            if((bean.getToDate()==null)||(bean.getToDate()==""))
            {
            	message = new ActionMessage("error.required.toDate");            	
            	messages.add(ActionMessages.GLOBAL_MESSAGE, message);
            	error = true;
            }
            
            if(error)
            {            	           	
        		saveErrors(request, messages);            	
            	return mapping.getInputForward();
            }
            else
            {
            	fromDate = ReportDateManager.getFromDate(bean);
            	toDate = ReportDateManager.getToDate(bean);
            	
            }
            
        	
        	subtitle = "For period:"+fromDate+" to "+toDate;
        }
        else
        {
        	throw new OperationException("Invalid Parameter for dateRange :" + dateRange);
        }
        
        
        ArrayList<Object[]> reportData = POSReportManager.getMaxMinSoldProductReportData(ctx,reportType,fromDate,toDate);        	
    	
    	//constructing the table
        TabularReport tReport = new TabularReport(reportData);    	
    	tReport.setSortable(false);
    	tReport.setStyle("display");
    	tReport.setTitle(title);
    	tReport.setSubtitle(subtitle);
    	tReport.createReport();    	
    	
    	String csvReport = CSVReportManager.generateCSVReport(ctx,reportData);
    	String csvURI = ReportManager.getReportURI(csvReport,request);    	
    	
    	request.getSession().setAttribute(Constants.TABULAR_REPORT_DATA,tReport.toString());
    	request.getSession().setAttribute(Constants.CSV_FILE,csvURI);
        
    	return mapping.findForward(GET_CUSTOM_REPORT);
    }
    
    //Partial POS History action
    public static final String INIT_PARTIAL_POS_HISTORY="initPartialPOSHistory";
    public ActionForward initPartialPOSHistory(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        Properties ctx = TmkJSPEnv.getCtx(request);      
        ArrayList list=POSReportManager.getAllOrderTypes(ctx);
        request.getSession().setAttribute(Constants.ORDER_TYPES,list);
        
        getPartialPOSHistory(mapping,new POSHistoryForm(),request,response);        
        
        return mapping.findForward(INIT_PARTIAL_POS_HISTORY);
    }
    
    
    public static final String GET_PARTIAL_POS_HISTORY="getPartialPOSHistory";
    public ActionForward getPartialPOSHistory(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        POSHistoryBean bean = (POSHistoryBean) df.getBean();
        
        String orderType = UDIOrderTypes.POS_ORDER.getOrderType();
        
        ArrayList list=POSReportManager.getPartialOrderHistory(ctx,orderType,bean.getMonth(),bean.getYear());
        request.getSession().setAttribute(Constants.POS_HISTORY,list);
        
        return mapping.findForward(GET_PARTIAL_POS_HISTORY);
    }
    
}
