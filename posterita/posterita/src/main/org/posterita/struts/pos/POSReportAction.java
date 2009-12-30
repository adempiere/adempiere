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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.ecs.XhtmlDocument;
import org.apache.ecs.xhtml.tr;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.compiere.model.MProcess;
import org.compiere.model.MProcessPara;
import org.compiere.model.MProduct;
import org.compiere.print.MPrintFormat;
import org.compiere.print.PrintData;
import org.compiere.print.ReportEngine;
import org.compiere.process.ProcessInfo;
import org.compiere.report.ReportStarter;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.MimeType;
import org.posterita.Constants;
import org.posterita.beans.POSHistoryBean;
import org.posterita.beans.ReportBean;
import org.posterita.beans.StockMovementBean;
import org.posterita.businesslogic.POSProductManager;
import org.posterita.businesslogic.POSStockManager;
import org.posterita.businesslogic.ProcessManager;
import org.posterita.businesslogic.administration.ProductManager;
import org.posterita.businesslogic.performanceanalysis.CSVReportManager;
import org.posterita.businesslogic.performanceanalysis.POSReportManager;
import org.posterita.businesslogic.performanceanalysis.POSSalesReportManager;
import org.posterita.businesslogic.performanceanalysis.ReportDateManager;
import org.posterita.businesslogic.performanceanalysis.ReportManager;
import org.posterita.businesslogic.stock.InventoryManager;
import org.posterita.businesslogic.stock.MMovementManager;
import org.posterita.core.TabularReport;
import org.posterita.core.TabularReport2;
import org.posterita.core.TabularReportMerge;
import org.posterita.core.TimestampConvertor;
import org.posterita.core.TmkJSPEnv;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;
import org.posterita.factory.WebComponentFactory;
import org.posterita.form.POSHistoryForm;
import org.posterita.form.ReportForm;
import org.posterita.form.SellingItemForm;
import org.posterita.form.StockEnquiryForm;
import org.posterita.lib.UdiConstants;
import org.posterita.order.UDIOrderTypes;
import org.posterita.struts.core.BaseDispatchAction;
import org.posterita.struts.core.DefaultForm;
import org.posterita.util.PathInfo;

import com.itextpdf.text.DocumentException;


public class POSReportAction extends BaseDispatchAction
{
    
    public static final String GET_MIN_SOLD_ITEMS="getMinSoldItems";
    public static HashMap<Integer, String> monthMap = new HashMap<Integer, String>();
    
    public ActionForward getMinSoldItems(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        
        ReportBean bean = (ReportBean) df.getBean();
        
        Timestamp fromdate = ReportDateManager.getFromDate(bean);
        Timestamp todate = ReportDateManager.getToDate(bean);
        
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
        
        Timestamp fromdate = ReportDateManager.getFromDate(bean);
        Timestamp todate = ReportDateManager.getToDate(bean);
        
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
        
        Timestamp fromdate = ReportDateManager.getFromDate(bean);
        Timestamp todate = ReportDateManager.getToDate(bean);
        
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
        request.setAttribute(Constants.POS_HISTORY,list);
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
        
        Timestamp fromDate = null;
        Timestamp toDate = null;
        
        //setting from and to dates
        if(dateRange.endsWith(Constants.FIXED_DATE_RANGE))
        {
        	if(timePeriod==null)
        	{
        		throw new OperationException("Invalid Parameter for timePeriod. timePeriod is null");
        	}
        	
        	Date startDate = ReportDateManager.getStartDateForPeriod(timePeriod);
        	Date endDate = ReportDateManager.getEndDateForPeriod(timePeriod);
        	
        	fromDate = new Timestamp(startDate.getTime());
        	toDate = new Timestamp(endDate.getTime());
        	
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
    
    public static final String GET_SALES_REPORT = "getSalesReport";
    public ActionForward getSalesOrPurchaseReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        
        df.validate(mapping, request);
        
        ReportBean bean = (ReportBean) df.getBean();
        
        String isSalesRPT = bean.getIsSalesReport();
               
        boolean isSalesReport = Boolean.parseBoolean(isSalesRPT);
             
        String title = "Sales Report";
        
        if(!(isSalesReport))
        {
            title = "Purchase Report";
        }
        
        String subtitle = "subtitle";     
        
        
        String dateRange = bean.getDateRange();
        String timePeriod = bean.getTimePeriod();
        
        Timestamp fromDate = null;
        Timestamp toDate = null;
        
        Date startDate = null;
        Date endDate = null;
        
        //setting from and to dates
        if(dateRange.endsWith(Constants.FIXED_DATE_RANGE))
        {
        	if(timePeriod==null)
        	{
        		throw new OperationException("Invalid Parameter for timePeriod. timePeriod is null");
        	}
        	
        	startDate = ReportDateManager.getStartDateForPeriod(timePeriod);
        	endDate = ReportDateManager.getEndDateForPeriod(timePeriod);
        	
        	fromDate = new Timestamp(startDate.getTime());
        	toDate = new Timestamp(endDate.getTime());
        	
        	subtitle = "For " + timePeriod + ": " + startDate;
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
        boolean isFullDetails = false;
        
        if (Constants.FULL_DETAILS.equals(bean.getFullDetails()))
        {
            isFullDetails = true;
        }
        
        ArrayList<Object[]> reportData = POSSalesReportManager.getSalesOrPurchaseReportData(ctx, bean, null);        	
    	
    	//constructing the table
        TabularReport2 tReport = new TabularReport2(reportData);    	
    	tReport.setSortable(false);
    	tReport.setStyle("display");
    	tReport.setTitle(title);
    	tReport.setSubtitle(subtitle);
    	tReport.setFromDate(fromDate);
    	tReport.setSalesReport(isSalesReport);
    	tReport.setFullDetails(isFullDetails);
    	tReport.setToDate(toDate);
    	tReport.createReport();    	
    	
    	String csvReport = CSVReportManager.generateCSVReport(ctx,reportData);
    	String csvURI = ReportManager.getReportURI(csvReport,request); 
    	
    	String pdfReport = tReport.createPDFReport();
    	String pdfURI = ReportManager.getReportURI(pdfReport,request);
    	
    	request.getSession().setAttribute(Constants.TABULAR_REPORT_DATA,tReport.toString());
    	request.getSession().setAttribute(Constants.CSV_FILE,csvURI);
    	request.getSession().setAttribute(Constants.PDF_FILE,pdfURI);
    	
    	return mapping.findForward(GET_SALES_REPORT);
    }
    
    public static final String GET_DETAILED_SALES_REPORT = "getDetailedSalesReport";
    public ActionForward getDetailedSalesReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, ParseException, FileNotFoundException, DocumentException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        
        String salesType = request.getParameter("salesType");
        String date1 = request.getParameter("date1");
        String date2 = request.getParameter("date2");
        boolean isSalesReport = Boolean.parseBoolean(request.getParameter("isSalesReport"));
        
        Timestamp fromDate = TimestampConvertor.getTimestamp(date1, TimestampConvertor.XML_DATE_PATTERN);
        Timestamp toDate = TimestampConvertor.getTimestamp(date2, TimestampConvertor.XML_DATE_PATTERN);
        // sales types
        // 1 -> Sales Excluding VAT
        // 2 -> Credit Note Excluding VAT
        // 3 -> Exempt Sales
        // 4 -> Exempt Credit Note
        
        ArrayList list = null;
        try
        {
        	list =POSSalesReportManager.getDetailedSalesReport(ctx, salesType, isSalesReport, fromDate, toDate, null);
        }
        catch (OperationException e)
        {
        	 postGlobalError("error.process",e.getMessage(),request);
	    	 return mapping.getInputForward();
        }
        request.getSession().setAttribute(Constants.DETAILED_SALES_REPORT,list);
        
        return mapping.findForward(GET_DETAILED_SALES_REPORT);
    }
    
    public static String GET_MONTHLY_SALES_REPORT = "getMonthlySalesReport";
    public ActionForward getMonthlySalesReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, ParseException, FileNotFoundException, DocumentException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
           
        String productName = request.getParameter("productName");
       
        String orgIds = getOrgIds(ctx, request);
                
        String date1 = request.getParameter("date1");
        String date2 = request.getParameter("date2");
        
        Timestamp d1 = TimestampConvertor.getTimestamp(date1, TimestampConvertor.XML_DATE_PATTERN);
        Timestamp d2 = TimestampConvertor.getTimestamp(date2, TimestampConvertor.XML_DATE_PATTERN);
        
        Calendar fd = Calendar.getInstance();
        fd.setTimeInMillis(d1.getTime());
        fd.set(Calendar.DATE, 1);
		
        Calendar td = Calendar.getInstance();
        td.setTimeInMillis(d2.getTime());
        td.set(Calendar.DATE, td.getMaximum(Calendar.DAY_OF_MONTH));
       
        Timestamp fromDate = new Timestamp(fd.getTimeInMillis());
        Timestamp toDate = new Timestamp(td.getTimeInMillis());
        
       // Timestamp fromDate = TimestampConvertor.getTimestamp(date1, TimestampConvertor.XML_DATE_PATTERN);
        //Timestamp toDate = TimestampConvertor.getTimestamp(date2, TimestampConvertor.XML_DATE_PATTERN);
               
        int[] productIds = ProductManager.getProducts(ctx, productName, Env.getContext(ctx,UdiConstants.ROLE_EDITABLE_ORGS_CTX_PARAM), null, null, null);
		
		if (productIds == null || productIds.length == 0)
		{
			throw new OperationException ("no products found with product name " + productName);
		}
		ArrayList<Object[]> reportData = null;
        try
        {
        	reportData = new ArrayList<Object[]>();
        	Object[] header = {"Month", "Qty Sold"};
        	reportData.add(header);
    		int precision = ProductManager.getUOMPrecision(ctx, productIds[0]);
    		
        	HashMap<String, BigDecimal> monthQtyMap = POSProductManager.getOrderQtyInfoPerProduct(ctx, true, productIds[0],
        			orgIds, fromDate, toDate, null);
        	
        	reportData = POSReportManager.formatStockSalesReportData(reportData, monthQtyMap, 
        			fromDate, toDate, precision);
        }
        catch (OperationException e)
        {
        	 postGlobalError("error.process",e.getMessage(),request);
	    	 return mapping.getInputForward();
        }
        
        TabularReport  tReport = new TabularReport(reportData);
        tReport.setSortable(false);
    	tReport.setStyle("display");
    	tReport.setTitle("Monthly Sales Report from " + date1 + " to "+date2 );
    	tReport.setSubtitle("Product: "+productName);
    	tReport.setFromDate(fromDate);
    	tReport.setToDate(toDate);
    	tReport.createReport("SalesReportAction.do?action=getSalesDetailsPerProduct", productIds[0]);   
    	
    	request.getSession().setAttribute(Constants.MONTHLY_SALES_REPORT_DATA,tReport.toString());
    	
    	
        return mapping.findForward(GET_MONTHLY_SALES_REPORT);
    }
    
    public static String GET_SALES_DETAILS_PER_PRODUCT = "getSODetailsPerProduct";
    public ActionForward getSalesDetailsPerProduct(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException 
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        
        String productId = request.getParameter("productId");
        
        String orgIds = getOrgIds(ctx, request);
        
        String date1 = request.getParameter("date1");
        String date2 = request.getParameter("date2");
        
        Timestamp fromDate = TimestampConvertor.getTimestamp(date1, TimestampConvertor.BIRTH_DATE);
        Timestamp toDate = TimestampConvertor.getTimestamp(date2, TimestampConvertor.BIRTH_DATE);
                
        ArrayList<POSHistoryBean> list = null;
        try
        {
        	list = POSSalesReportManager.getOrderDetailsPerProduct(ctx, true, productId, orgIds, fromDate, toDate);
        }
        catch (OperationException e)
        {
        	 postGlobalError("error.process",e.getMessage(),request);
	    	 return mapping.getInputForward();
        }
/*        request.getSession().setAttribute(Constants.DETAILED_SALES_REPORT_PER_PRODUCT,list);*/
        request.getSession().setAttribute(Constants.POS_HISTORY,list);
        request.getSession().setAttribute(Constants.POS_HISTORY_ORDER_TYPE,UDIOrderTypes.POS_ORDER.getOrderType());   
        
        return mapping.findForward(GET_SALES_DETAILS_PER_PRODUCT);
    }
     
    public static String GET_GRN_DETAILS_PER_PRODUCT = "getPODetailsPerProduct";
    public ActionForward getGRNDetailsPerProduct(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException 
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        
        String productId = request.getParameter("productId");
        
        String orgIds = getOrgIds(ctx, request);
        
        String date1 = request.getParameter("date1");
        String date2 = request.getParameter("date2");
        
        Timestamp fromDate = TimestampConvertor.getTimestamp(date1, TimestampConvertor.BIRTH_DATE);
        Timestamp toDate = TimestampConvertor.getTimestamp(date2, TimestampConvertor.BIRTH_DATE);
                
        ArrayList<POSHistoryBean> list = null;
        try
        {
        	list = POSSalesReportManager.getOrderDetailsPerProduct(ctx, false, productId, orgIds, fromDate, toDate);
        }
        catch (OperationException e)
        {
        	 postGlobalError("error.process",e.getMessage(),request);
	    	 return mapping.getInputForward();
        }
        request.getSession().setAttribute(Constants.POS_HISTORY,list);
        request.getSession().setAttribute(Constants.POS_HISTORY_ORDER_TYPE,UDIOrderTypes.POS_GOODS_RECEIVE_NOTE.getOrderType());   
        
        return mapping.findForward(GET_GRN_DETAILS_PER_PRODUCT);
    }
    
    /*********************************************************************************/
    
    public static final String INIT_BEST_SELLING_REPORT = "initBestSellingReport";
	
    /**
     * Initialise Best Selling Items Report
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws ApplicationException
     * @throws OperationException
     */
    public ActionForward initBestSellingReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
	{
		 ActionForward fwd= init(mapping,form,request,response);
	        if(fwd!=null)
	            return fwd;
	        
		SellingItemForm reportForm = (SellingItemForm) form;         
        
        //setting defaults
        String timePeriod = ReportDateManager.TODAY;
        String dateRange = Constants.FIXED_DATE_RANGE;        
        
        reportForm.setTimePeriod(timePeriod);
        reportForm.setDateRange(dateRange);    	
    	reportForm.setFromDate("");
    	reportForm.setToDate("");
        
        reportForm.validate(mapping,request);
        
		return mapping.findForward(INIT_BEST_SELLING_REPORT);
	}
    
    
    public static final String GET_BEST_SELLING_ITEMS = "getBestSellingItems";
    
    /**
     * Generate Best Selling Items
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws ApplicationException
     * @throws OperationException
     * @throws DocumentException
     * @throws IOException
     * @throws SQLException
     * @throws ParseException
     */
    public ActionForward getBestSellingItems(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, DocumentException, IOException, SQLException, ParseException
	{
		 ActionForward fwd= init(mapping,form,request,response);
	     if(fwd!=null)
	    	 return fwd;
	        
	     Properties ctx = TmkJSPEnv.getCtx(request);
	     
	     SellingItemForm sf = (SellingItemForm)form;	        
	     ReportBean bean = (ReportBean) sf.getBean();
	     
	     String dateRange = bean.getDateRange();
	     String timePeriod = bean.getTimePeriod();
	     Timestamp fromDate = null;
	     Timestamp toDate = null;
	     
	     //setting from and to dates
         if(dateRange.endsWith(Constants.FIXED_DATE_RANGE))
         {
        	 if(timePeriod == null)
        	 {
        		 throw new OperationException("Invalid Parameter for timePeriod. TimePeriod is null");
        	 }
        	
        	 fromDate = ReportDateManager.getStartDateForPeriod(timePeriod);
        	 toDate = ReportDateManager.getEndDateForPeriod(timePeriod);
        	
         }
         else if(dateRange.endsWith(Constants.CUSTOM_DATE_RANGE))
         {
        	 fromDate = ReportDateManager.getFromDate(bean);
        	 toDate = ReportDateManager.getToDate(bean);
	   	     
         }
         else
         {
        	 throw new OperationException("Invalid Parameter for dateRange :" + dateRange);
         }
	         
   	    
	     ArrayList<Object[]> reportData = POSReportManager.getBestSellingItemsData(ctx, fromDate, toDate, null);
	     
	     String title = "BEST 100 SELLING ITEMS";
	     String subtitle  = null;
	     
	     String sql = "Select Upper(Name) from AD_Org where AD_org_ID=?";
	     PreparedStatement pstmt = null;
	     ResultSet rs = null;
		
	     pstmt = DB.prepareStatement(sql.toString(), null); 
	     pstmt.setInt(1, Env.getAD_Org_ID(ctx));
		
	     try
	     {
	    	 rs = pstmt.executeQuery();
    		
	    	 if (rs.next())
	    	 {
	    		 subtitle = rs.getString(1);
	    	 }
    		    		
	     }
	     catch(SQLException e)
	     {
	    	 throw new OperationException(e);
	     }
	     finally 
	     {
	    	 DB.close(rs);
	    	 DB.close(pstmt);
    	 }
	     
	     SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd-MMM-yyyy");
	     
	     //constructing the table
	     TabularReport tReport = new TabularReport(reportData);    	
	     tReport.setSortable(false);
	     tReport.setStyle("display");
	     tReport.setTitle(title);
	     tReport.setFromDate(fromDate);
	     tReport.setToDate(toDate);
	     tReport.setSubtitle(subtitle);
	     tReport.createReport();
	     
	     response.setContentType("x-application/pdf");
	     response.setHeader("Content-Disposition","inline;filename=BestSellingItemsReport("+System.currentTimeMillis()+").pdf");
	     response.getOutputStream().write(tReport.getSellingItemsData());
	          
	     
	     return mapping.findForward(GET_BEST_SELLING_ITEMS);
	}
    
    public static final String INIT_STOCK_ENQUIRY_REPORT = "initStockEnquiryReport";
    
    public ActionForward initStockEnquiryReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
	{
		 ActionForward fwd= init(mapping,form,request,response);
	        if(fwd!=null)
	            return fwd;
	        
		Properties ctx = TmkJSPEnv.getCtx(request);
		StockEnquiryForm reportForm = (StockEnquiryForm) form;         
        
        //setting defaults
        String timePeriod = ReportDateManager.TODAY;
        String dateRange = Constants.FIXED_DATE_RANGE;        
        
        reportForm.setTimePeriod(timePeriod);
        reportForm.setDateRange(dateRange);    	
    	reportForm.setFromDate("");
    	reportForm.setToDate("");
        
        reportForm.validate(mapping,request);
        
		return mapping.findForward(INIT_STOCK_ENQUIRY_REPORT);
	}
    
    public static final String GET_STOCK_ENQUIRY_REPORT = "getStockEnquiryReport";
    
    
    /**
     * Get Stock Enquiry Report
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws ApplicationException
     * @throws OperationException
     * @throws DocumentException
     * @throws IOException
     * @throws SQLException
     * @throws ParseException
     */
    public ActionForward getStockEnquiryReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, DocumentException, IOException, SQLException, ParseException
	{
		 ActionForward fwd= init(mapping,form,request,response);
	     if(fwd!=null)
	    	 return fwd;
	        
	     Properties ctx = TmkJSPEnv.getCtx(request);
	     
	     StockEnquiryForm sf = (StockEnquiryForm)form;	        
	     ReportBean bean = (ReportBean) sf.getBean();
	     
	     String dateRange = bean.getDateRange();
	     String timePeriod = bean.getTimePeriod();
	     Timestamp fromDate = null;
	     Timestamp toDate = null;
	    
	     //setting from and to dates
         if(dateRange.endsWith(Constants.FIXED_DATE_RANGE))
         {
        	 if(timePeriod == null)
        	 {
        		 throw new OperationException("Invalid Parameter for timePeriod. TimePeriod is null");
        	 }
        	
        	 fromDate = ReportDateManager.getStartDateForPeriod(timePeriod);
        	 toDate = ReportDateManager.getEndDateForPeriod(timePeriod);
        	
         }
         else if(dateRange.endsWith(Constants.CUSTOM_DATE_RANGE))
         {
        	 fromDate = ReportDateManager.getFromDate(bean);
        	 toDate = ReportDateManager.getToDate(bean);
	   	     
         }
         else
         {
        	 throw new OperationException("Invalid Parameter for dateRange :" + dateRange);
         }
	     
	     ArrayList<Object[]> reportData = POSReportManager.getStockEnquiryData(ctx, fromDate, toDate, null);
	     
	     String title = "STOCK ENQUIRY REPORT";
	     String subtitle  = null;
	     
	     String sql = "Select Upper(Name) from AD_Org where AD_org_ID=?";
	     PreparedStatement pstmt = null;
	     ResultSet rs = null;
		
	     pstmt = DB.prepareStatement(sql.toString(), null); 
	     pstmt.setInt(1, Env.getAD_Org_ID(ctx));
		
	     try
	     {
	    	 rs = pstmt.executeQuery();
    		
	    	 if (rs.next())
	    	 {
	    		 subtitle = rs.getString(1);
	    	 }
    		    		
	     }
	     catch(SQLException e)
	     {
	    	 throw new OperationException(e);
	     }
	     finally 
	     {
	    	 DB.close(rs);
	    	 DB.close(pstmt);
    	 }
	     
	     SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd-MMM-yyyy");
	     
	     //constructing the table
	     TabularReport tReport = new TabularReport(reportData);    	
	     tReport.setSortable(false);
	     tReport.setStyle("display");
	     tReport.setTitle(title);
	     tReport.setFromDate(fromDate);
	     tReport.setToDate(toDate);
	     tReport.setSubtitle(subtitle);
	     tReport.createReport();
	     
	     response.setContentType("x-application/pdf");
	     response.setHeader("Content-Disposition","inline;filename=StockEnquiryReport("+System.currentTimeMillis()+").pdf");
	     response.getOutputStream().write(tReport.getStockEnquiryData());
	          
	     
	     return mapping.findForward(GET_STOCK_ENQUIRY_REPORT);
	}
    
    public static final String GET_STOCK_SALES_REPORT = "getStockSalesReport";
    public ActionForward getStockSalesReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, SQLException, OperationException 
	{
		 ActionForward fwd= init(mapping,form,request,response);
	    
		 if (fwd!=null)
	     {
	    	 return fwd;
	     }
	        
	     Properties ctx = TmkJSPEnv.getCtx(request);
	     
	     ReportForm sf = (ReportForm)form;	        
	     ReportBean bean = (ReportBean) sf.getBean();
	     
	     Integer productId = bean.getProductId();
	     String description = bean.getDescription();
	     String barCode = bean.getBarCode();
	     Integer orgId = bean.getOrgId();
	     
	     String fromDate = bean.getFromDate();
	     String toDate = bean.getToDate();
	    
	     Timestamp thisDate = TimestampConvertor.getCurrentDateTimeTimestamp();
	     Timestamp startDate = thisDate;
	     Timestamp endDate = thisDate;
	     
	     String productName = bean.getProductName();
	     
	     HashSet<Integer> orgIds = (HashSet<Integer>)request.getSession().getAttribute(Constants.STOCK_SALES_REPORT_ORGS);
	     if (orgIds == null)
	     {
	    	 orgIds = new HashSet<Integer>();
	     }
	     orgIds.add(orgId);
	     
	     if (fromDate != null)
	     {
	    	 startDate = TimestampConvertor.getTimestamp(fromDate, TimestampConvertor.BIRTH_DATE);
	     }
	     if (toDate != null)
    	 {
	    	 endDate = TimestampConvertor.getTimestamp(toDate, TimestampConvertor.BIRTH_DATE);
	     }
	     Timestamp oldFromDate = (Timestamp)request.getSession().getAttribute(Constants.FROM_DATE);
	     Timestamp oldToDate  = (Timestamp)request.getSession().getAttribute(Constants.TO_DATE);
	     
	     if (!startDate.equals(oldFromDate) || !endDate.equals(oldToDate))
	     {
	    	 clear(request, true);
	    	 clear(request, false);
	     }
	     
	     description = description == null? "":description;
	     barCode = barCode == null?"": barCode;
	     
	     ArrayList<Object[]> reportSalesData = getReportData(request, true, orgId);
	     ArrayList<Object[]> reportStockData = getReportData(request, false, orgId);
	     
	     HashSet<Integer> productSalesCompleteSet = (HashSet<Integer>) request.getSession().getAttribute(Constants.PRODUCT_SALES_COMPLETE_SET);
	     HashSet<Integer> productStockCompleteSet = (HashSet<Integer>) request.getSession().getAttribute(Constants.PRODUCT_STOCK_COMPLETE_SET);
	     
	     
	     HashMap<Integer, ArrayList<String>> prodOrgStockMap = (HashMap<Integer, ArrayList<String>>)request.getSession().getAttribute(Constants.PRODUCT_ORG_STOCK_MAP);
	 	 HashMap<Integer, ArrayList<String>> prodOrgSalesMap = (HashMap<Integer, ArrayList<String>>)request.getSession().getAttribute(Constants.PRODUCT_ORG_SALES_MAP);
	     
	 	 
	 	 productSalesCompleteSet = productSalesCompleteSet == null? new HashSet<Integer>() : productSalesCompleteSet;
	 	 productStockCompleteSet = productStockCompleteSet == null? new HashSet<Integer>() : productStockCompleteSet;
	 	 prodOrgStockMap = prodOrgStockMap == null? new HashMap<Integer, ArrayList<String>>() : prodOrgStockMap;
	 	 prodOrgSalesMap = prodOrgSalesMap == null? new HashMap<Integer, ArrayList<String>>() : prodOrgSalesMap;
	 	 
	 	 
	     ArrayList<Object[]> salesBackup  = POSReportManager.doBackup(reportSalesData);    	 
	     ArrayList<Object[]> stockBackup = POSReportManager.doBackup(reportStockData);
	     
	     	     
	     if (orgId != null)
	     {
	    	 boolean isComplete = false;
	    	 if (orgId == 0)
	    	 {
	    		 isComplete = true;
	    	 }
	    	 try
	         {	
	    		 
	    		 int[] productIds = null ;
	    		 
	    		if (productName == null)
		    	 {
	    			 productIds = ProductManager.getProducts(ctx, "", description, barCode, null);
	    			 if (productIds.length == 0)
	    			 {
	    				 throw new OperationException("no products found matching that description and/or barcode");
	    			 }
		    	 }
	    		 else
	    		 {
	    			 productIds = new int[]{productId};
    			 }

	    		clear(request, !isComplete);
	    		 
	    		 if (orgId == 0)
			     {
	    			 	    			 
    			 	 for (int prodId: productIds)
    				 {
    			 		reportSalesData = POSReportManager.getStockSalesReportComplete(ctx, productSalesCompleteSet, true, reportSalesData, prodId, orgId, startDate, endDate);
    			 		reportStockData = POSReportManager.getStockSalesReportComplete(ctx, productStockCompleteSet,false, reportStockData, prodId, orgId, startDate, endDate);
    				 }
			     }
			     else
		    	 {
			    	 
			    	 for (int prodId: productIds)
	    			 {
	    				 reportSalesData = POSReportManager.getStockSalesReport(ctx, prodOrgSalesMap, reportSalesData, true, prodId, orgId, startDate, endDate);
					   	 reportStockData = POSReportManager.getStockSalesReport(ctx, prodOrgStockMap,reportStockData, false, prodId, orgId, startDate, endDate);
	    			 }
		    	 }
		     }
		     catch (OperationException e)
		     {
		    	 reportStockData = stockBackup;
	    		 reportSalesData = salesBackup;
	    		 
		    	 if (orgId != 0)
		    	 {
			    	 request.getSession().setAttribute(Constants.SALES_REPORT_LIST, reportSalesData);
				     request.getSession().setAttribute(Constants.STOCK_REPORT_LIST, reportStockData);
		    	 }
		    	 else
		    	 {
		    		 request.getSession().setAttribute(Constants.SALES_REPORT_LIST_COMPLETE, reportSalesData);
				     request.getSession().setAttribute(Constants.STOCK_REPORT_LIST_COMPLETE, reportStockData);
			    	     		 
		    	 }
		    	 postGlobalError("error.process",e.getMessage(),request);
		    	 return mapping.getInputForward();
		     }
	     	    
		     //constructing the table
		     TabularReportMerge tReport = new TabularReportMerge("Stock Sales Report", 2);
		     tReport.setFromDate(startDate);
		     tReport.setToDate(endDate);
		     tReport.setSortable(false);
		     tReport.setStyle("display");
		     try
		     {
		    	 if (orgId == 0)
		    	 {
		    		 tReport.createReport(reportSalesData, Constants.SALES_TYPE, "Sales (Qty Sold)");
		    		 tReport.createReport(reportStockData, Constants.STOCK_TYPE, "Stock (Qty On Hand)");
		    	 }
		    	 else
	    		 {
		    		 tReport.createReport(reportSalesData, Constants.SALES_TYPE, "Sales (Qty Sold)");
		    		 tReport.createReport(reportStockData, Constants.STOCK_TYPE, "Stock (Qty On Hand)");
	    		 }  	 
		     }
		     catch (OperationException e)
		     {
		    	 postGlobalError("error.process",e.getMessage(),request);
		    	 return mapping.getInputForward();
		     }
		     request.getSession().setAttribute(Constants.STOCK_SALES_REPORT_DATA,tReport.toString());		   		     
		     request.getSession().setAttribute(Constants.STOCK_SALES_REPORT_ORGS, orgIds);
		     
		     if (orgId != 0)
		     {
		    	 request.getSession().setAttribute(Constants.SALES_REPORT_LIST, reportSalesData);
		    	 request.getSession().setAttribute(Constants.STOCK_REPORT_LIST, reportStockData);
		    	 request.getSession().setAttribute(Constants.PRODUCT_ORG_STOCK_MAP, prodOrgStockMap);
			 	 request.getSession().setAttribute(Constants.PRODUCT_ORG_SALES_MAP, prodOrgSalesMap);
		    	
		     }
		     else
		     {
		    	 request.getSession().setAttribute(Constants.SALES_REPORT_LIST_COMPLETE, reportSalesData);
		    	 request.getSession().setAttribute(Constants.STOCK_REPORT_LIST_COMPLETE, reportStockData);
		    	 request.getSession().setAttribute(Constants.PRODUCT_SALES_COMPLETE_SET, productSalesCompleteSet);
		    	 request.getSession().setAttribute(Constants.PRODUCT_STOCK_COMPLETE_SET, productStockCompleteSet);
		     }
	     }
	     
	     request.getSession().setAttribute(Constants.FROM_DATE, startDate);
	     request.getSession().setAttribute(Constants.TO_DATE, endDate);	     
	     
	     return mapping.findForward(GET_STOCK_SALES_REPORT);
	     
	}
    
    public static final String CLEAR_STOCK_SALES_REPORT = "clearStockSalesReport";
    public ActionForward clearStockSalesReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, DocumentException, IOException, SQLException, ParseException
	{
		 ActionForward fwd= init(mapping,form,request,response);
	     
		 if (fwd!=null)
	     {
	    	 return fwd;
	     }
	        
		 clear(request, true);
		 clear(request, false);
		 request.getSession().removeAttribute(Constants.STOCK_SALES_REPORT_ORGS);
	     request.getSession().removeAttribute(Constants.STOCK_SALES_REPORT_DATA);
	     
	     return mapping.findForward(GET_STOCK_SALES_REPORT);
	}         
    
    private void clear(HttpServletRequest request, boolean isComplete)
    {
    	if (!isComplete)
    	{
			request.getSession().removeAttribute(Constants.PRODUCT_ORG_STOCK_MAP);
			request.getSession().removeAttribute(Constants.PRODUCT_ORG_SALES_MAP);
			request.getSession().removeAttribute(Constants.SALES_REPORT_LIST);
			request.getSession().removeAttribute(Constants.STOCK_REPORT_LIST);
    	}
    	else
    	{
			request.getSession().removeAttribute(Constants.PRODUCT_SALES_COMPLETE_SET);
			request.getSession().removeAttribute(Constants.PRODUCT_STOCK_COMPLETE_SET);
			request.getSession().removeAttribute(Constants.SALES_REPORT_LIST_COMPLETE);
			request.getSession().removeAttribute(Constants.STOCK_REPORT_LIST_COMPLETE);
    	}   
	}
    
    private ArrayList<Object[]> getReportData(HttpServletRequest request, boolean isSales, int orgId)
    {
    	
    	if (isSales)
    	{
    		if (orgId == 0)
    		{
    			return (ArrayList<Object[]>) request.getSession().getAttribute(Constants.SALES_REPORT_LIST_COMPLETE);
    		}
    		else
    		{
    			return (ArrayList<Object[]>)request.getSession().getAttribute(Constants.SALES_REPORT_LIST);
    		}
    	}
    	else
    	{
    		if (orgId == 0)
    		{
    			return (ArrayList<Object[]>) request.getSession().getAttribute(Constants.STOCK_REPORT_LIST_COMPLETE);
    		}
    		else
    		{
    			return (ArrayList<Object[]>)request.getSession().getAttribute(Constants.STOCK_REPORT_LIST);
    		}
    	}
    }
    
    public ActionForward getMonthlyStockReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, ParseException, FileNotFoundException, DocumentException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
           
        String productName = request.getParameter("productName");
        String orgIds = getOrgIds(ctx, request);
        String date1 = request.getParameter("date1");
        String date2 = request.getParameter("date2");
        
        Timestamp d1 = TimestampConvertor.getTimestamp(date1, TimestampConvertor.XML_DATE_PATTERN);
        Timestamp d2 = TimestampConvertor.getTimestamp(date2, TimestampConvertor.XML_DATE_PATTERN);
             
        Calendar fd = Calendar.getInstance();
        fd.setTimeInMillis(d1.getTime());
        fd.set(Calendar.DATE, 1);
		
        Calendar td = Calendar.getInstance();
        td.setTimeInMillis(d2.getTime());
        td.set(Calendar.DATE, td.getMaximum(Calendar.DAY_OF_MONTH));
       
        Timestamp fromDate = new Timestamp(fd.getTimeInMillis());
        Timestamp toDate = new Timestamp(td.getTimeInMillis());
        
        int[] productIds = ProductManager.getProducts(ctx, productName, Env.getContext(ctx,UdiConstants.ROLE_EDITABLE_ORGS_CTX_PARAM), null, null, null);
		
		if (productIds == null || productIds.length == 0)
		{
			throw new OperationException ("no products found with product name " + productName);
		}
		ArrayList<Object[]> reportData = null;
        try
        {
			reportData = new ArrayList<Object[]>();
			Object[] header = new Object[]{"Month", "Qty Sold", "GRN", "Stock Adjustments"};
			reportData.add(header);
			
        	HashMap<String, BigDecimal> monthQtySales = POSProductManager.getOrderQtyInfoPerProduct(ctx, true, productIds[0],
        			orgIds, fromDate, toDate, null);
        	
        	reportData = POSReportManager.formatStockSalesReportData(reportData, monthQtySales, fromDate, toDate, 2);
        	
        	HashMap<String, BigDecimal> monthQtyGRN = POSProductManager.getOrderQtyInfoPerProduct(ctx, false, productIds[0],
        			orgIds, fromDate, toDate, null);
        	
        	reportData = POSReportManager.formatStockSalesReportData(reportData, monthQtyGRN,fromDate, toDate, 2);
        	
        	HashMap<String, BigDecimal> monthStockAdjust = POSStockManager.getMonthlyStockAdjustments(ctx,
        			productIds[0], orgIds, fromDate, toDate, null);
        	reportData = POSReportManager.formatStockSalesReportData(reportData, monthStockAdjust,fromDate, toDate, 2);
        	
        }
        catch (OperationException e)
        {
        	 postGlobalError("error.process",e.getMessage(),request);
	    	 return mapping.getInputForward();
        }
        
        TabularReport  tReport = new TabularReport(reportData);
        tReport.setSortable(false);
    	tReport.setStyle("display");
    	tReport.setTitle("Monthly Stock and Sales Report from " + date1 + " to "+date2 );
    	tReport.setSubtitle("Product: "+productName);
    	tReport.setFromDate(fromDate);
    	tReport.setToDate(toDate);
    	tReport.createReport("SalesReportAction.do?action=getGRNDetailsPerProduct", productIds[0]); // need to find another way to generate reports with links on data   
    	
    	request.getSession().setAttribute(Constants.MONTHLY_SALES_REPORT_DATA,tReport.toString()); 
    	
    	
        return mapping.findForward(GET_MONTHLY_SALES_REPORT);
    }
    
    public static String GET_STOCK_ADJUSTMENTS_PER_PRODUCT = "getStockAdjustmentsPerProduct";
    public ActionForward getStockAdjustmentsPerProduct(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException 
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);        
        String prodId = request.getParameter("productId");
        String orgIds = getOrgIds(ctx, request);
        int productId = Integer.parseInt(prodId);
        String date1 = request.getParameter("date1");
        String date2 = request.getParameter("date2");
        Timestamp fromDate = TimestampConvertor.getTimestamp(date1, TimestampConvertor.BIRTH_DATE);
        Timestamp toDate = TimestampConvertor.getTimestamp(date2, TimestampConvertor.BIRTH_DATE);
        MProduct product = MProduct.get(ctx, productId);
        
        ArrayList<Object[]> reportData = new ArrayList<Object[]>();
        
      
        reportData = POSStockManager.getStockAdjustments(ctx, reportData, productId, orgIds, fromDate, toDate, null);
        
        TabularReport tReport = new TabularReport(reportData);
        tReport.setSortable(false);
    	tReport.setStyle("display");
    	tReport.setTitle("Stock Adjustments Report from " + date1 + " to "+date2 );
    	tReport.setSubtitle("Product: "+product.getName());
    	tReport.setFromDate(fromDate);
    	tReport.setToDate(toDate);
    	tReport.createReport(ctx, productId);   
    	
    	request.getSession().setAttribute(Constants.STOCK_ADJUSTMENTS_REPORT_DATA,tReport.toString());
    	
    	
        return mapping.findForward(GET_STOCK_ADJUSTMENTS_PER_PRODUCT);
    }
    
    public static final String VIEW_INVENTORY_HISTORY="viewInventoryHistory";
    public ActionForward viewInventoryHistory(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        
        Properties ctx = TmkJSPEnv.getCtx(request);        
        String prodId = request.getParameter("productId");
        String org_Id = request.getParameter("orgId");
        int orgId = Integer.parseInt(org_Id);
        int productId = Integer.parseInt(prodId);
        
        String date1 = request.getParameter("date1");
        String date2 = request.getParameter("date2");
        
        Timestamp fromDate = TimestampConvertor.getTimestamp(date1, TimestampConvertor.XML_DATE_PATTERN);
        Timestamp toDate = TimestampConvertor.getTimestamp(date2, TimestampConvertor.XML_DATE_PATTERN);
        
        ArrayList list = InventoryManager.viewInventoryHistory(ctx,orgId, productId, fromDate, toDate, null);
        request.setAttribute(Constants.INVENTORY_HISTORY_LIST,list);
        return mapping.findForward(VIEW_INVENTORY_HISTORY);
    }
    
    public static final String VIEW_INVENTORY_MOVE_HISTORY = "viewInventoryMoveHistory";
    public ActionForward viewInventoryMoveHistory(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws ApplicationException, OperationException
	{
		ActionForward fwd = init(mapping, form, request, response);
		
		if (fwd != null)
		{
			return fwd;
		}
		Properties ctx = TmkJSPEnv.getCtx(request);
		String prodId = request.getParameter("productId");
        String orgId =  request.getParameter("orgId");
        String type = request.getParameter("type");
        int productId = Integer.parseInt(prodId);
        String date1 = request.getParameter("date1");
        String date2 = request.getParameter("date2");
        
        Timestamp fromDate = TimestampConvertor.getTimestamp(date1, TimestampConvertor.XML_DATE_PATTERN);
        Timestamp toDate = TimestampConvertor.getTimestamp(date2, TimestampConvertor.XML_DATE_PATTERN);
        
		ArrayList<StockMovementBean> list = MMovementManager.getMMovement(ctx, orgId, type, productId, fromDate, toDate);
		
		request.setAttribute(Constants.INVENTORY_MOVE_HISTORY, list);
		return mapping.findForward(VIEW_INVENTORY_MOVE_HISTORY);
	}
    
    private String getOrgIds(Properties ctx, HttpServletRequest request)
    {
    	HashSet<Integer> orgIdSet = (HashSet<Integer>)request.getSession().getAttribute(Constants.STOCK_SALES_REPORT_ORGS);
        StringBuffer orgIds = new StringBuffer();
        Iterator<Integer> iter = orgIdSet.iterator();
        while (iter.hasNext())
        {	
        	Integer orgId = iter.next();
        	
        	if (orgId ==0)
        	{
        		return Env.getContext(ctx,UdiConstants.ROLE_EDITABLE_ORGS_CTX_PARAM);
        	}
        	else
        	{
        		orgIds = orgIds.append(orgId);
        		if (iter.hasNext())
        		{
        			orgIds = orgIds.append(",");
        		}
        	}
        }
        
        return orgIds.toString();                
    }  
    
    
    public static final String GENERATE_REPORT_INPUT = "generateReportInput";
    public ActionForward generateReportInput(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        
        if (fwd!=null)
        {
        	return fwd;
        }
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        StringBuffer componentList = new StringBuffer();
        String processDescription = null;
        String processComment = null;
        
        int processId = 0;
        try
        {
        	processId = Integer.valueOf(request.getParameter("processId"));
        	MProcess process = MProcess.get(ctx, processId);
        	MProcessPara[] processPara = process.getParameters();
        	processDescription = process.getDescription();
        	processComment = process.getHelp();
        	
        	for (MProcessPara parameter:processPara)
        	{
    			tr component = new tr();
				try 
				{
					component = (tr)WebComponentFactory.getWebComponent(ctx, parameter);
				} 
				catch (Exception e) 
				{
					postGlobalError("error.process", e.getMessage(), request);
					return mapping.getInputForward();
					
				}
    			componentList.append(component.toString());
        	}
        }
        catch (NumberFormatException e)
        {
        	return mapping.getInputForward();
        }
        request.getSession().setAttribute(Constants.PROCESS_ID, processId);
        request.getSession().setAttribute(Constants.REPORT_DESCRIPTION, processDescription);
        request.getSession().setAttribute(Constants.REPORT_COMMENT, processComment);
        request.getSession().setAttribute(Constants.WEB_COMPONENTS, componentList.toString());
        return mapping.findForward(GENERATE_REPORT_INPUT);
    }
    
    
    public static final String GENERATE_REPORT_OUTPUT = "generateReportOutput";
    public ActionForward generateReportOutput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ActionForward fwd = init(mapping, form, request, response);
		
		if (fwd != null)
		{
			return fwd;
		}
		Properties ctx = TmkJSPEnv.getCtx(request);		
		DefaultForm df = (DefaultForm)form;
		
		String reportType = df.getReportType();
		// Load process	
		int processId = Integer.valueOf(request.getSession().getAttribute(Constants.PROCESS_ID).toString());
		MProcess process = MProcess.get(ctx, processId);
		
		Map map = request.getParameterMap();
		ProcessInfo pi = null;
		// Create a new process instance and populate its parameters with the web user's input		
		try
		{
			pi = POSReportManager.createProcessInstance(ctx, processId, map, null);			
		}
		catch (OperationException e)
		{
			postGlobalError("error.process",e.getMessage(), request);
			return mapping.getInputForward();
		}
		try 
		{
			ProcessManager.startProcess(ctx, process.getClassname(), pi, null);
		} 
		catch (InstantiationException e) 
		{
			return mapping.getInputForward();
		} 
		catch (IllegalAccessException e) 
		{
			return mapping.getInputForward();
		} 
		catch (ClassNotFoundException e) 
		{
			return mapping.getInputForward();
		}
		
		if (POSReportManager.isJasperReport(process.getClassname()))
		{	
			JasperPrint jasperPrint = ReportStarter.getJasperPrint();
			if (Constants.HTML.equals(reportType))
			{
				XhtmlDocument doc = new XhtmlDocument();
				File file = File.createTempFile ("Jasper", ".html", new File(PathInfo.PROJECT_HOME));
				JasperExportManager.exportReportToHtmlFile(jasperPrint, file.getAbsolutePath());
				
				StringBuffer contents = new StringBuffer();
			    try 
			    {
			      BufferedReader input =  new BufferedReader(new FileReader(file));
			      try 
			      {
			        String line = null; 
			        while (( line = input.readLine()) != null){
			          contents.append(line);
			          contents.append(System.getProperty("line.separator"));
			        }
			      }
			      finally 
			      {
			        input.close();
			      }
			    }
			    catch (IOException ex)
			    {
			      ex.printStackTrace();
			    }
			    PrintWriter writer = response.getWriter();
			    writer.write(contents.toString());
			    doc.output(writer);
			    writer.flush();
			    writer.close();
			}			
			if (Constants.PDF.equals(reportType))
			{
				response.setContentType(MimeType.getMimeType(".pdf"));
				response.setHeader("Content-Disposition:", "attachment;filename=\""+process.getName()+".pdf\"");
				byte[] pdfData = JasperExportManager.exportReportToPdf(jasperPrint);
				response.getOutputStream().write(pdfData);
			}
		}
		else
		{
			MPrintFormat printFormat = POSReportManager.loadPrintFormat(ctx, process, null);
			PrintData printData = POSReportManager.getPrintData(ctx, printFormat, pi);	
				
			ReportEngine reportEngine = POSReportManager.getReportEngine(ctx, printFormat, pi);
			
			if (Constants.PDF.equals(reportType))
			{
				response.setContentType(MimeType.getMimeType(".pdf"));
				response.setHeader("Content-Disposition:", "attachment;filename=\""+process.getName()+".pdf\"");
				
				byte[] pdfData = reportEngine.createPDFData();
				response.getOutputStream().write(pdfData);
			}
			if (Constants.CSV.equals(reportType))
	    	{
				response.setContentType(MimeType.getMimeType(".csv"));
				response.setHeader("Content-Disposition:", "attachment;filename=\""+process.getName()+".csv\"");
				PrintWriter writer = new PrintWriter(response.getOutputStream());
				reportEngine.createCSV(writer, ',', printFormat.getLanguage());
	    	}
			if (Constants.HTML.equals(reportType))
			{
				PrintWriter printWriter = new PrintWriter(response.getOutputStream());
				HashMap<String, String> paramMap = POSReportManager.getReportParameters(ctx, pi.getAD_PInstance_ID(), null);
				POSReportManager.createHTML(printWriter, process.getName(), paramMap, printData, printFormat);
			}
		}
		response.getOutputStream().flush();
		response.getOutputStream().close();		
		return null;
	}
     
}
