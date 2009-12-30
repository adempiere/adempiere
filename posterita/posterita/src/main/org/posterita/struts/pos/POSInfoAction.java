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

import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.posterita.Constants;
import org.posterita.beans.POSInfoBean;
import org.posterita.beans.ReportBean;
import org.posterita.businesslogic.POSManager;
import org.posterita.businesslogic.performanceanalysis.CSVReportManager;
import org.posterita.businesslogic.performanceanalysis.ReportDateManager;
import org.posterita.businesslogic.performanceanalysis.ReportManager;
import org.posterita.core.TabularReport;
import org.posterita.core.TimestampConvertor;
import org.posterita.core.TmkJSPEnv;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;
import org.posterita.struts.core.DefaultForm;

import com.itextpdf.text.DocumentException;

public class POSInfoAction extends POSDispatchAction
{
	public static final String GET_CURRENT_DAY_REPORT = "getCurrentDayReport";
	public ActionForward getCurrentDayReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
    {
        ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        
        Timestamp fromDate = TimestampConvertor.getCurrentDateTimestamp();
        Timestamp toDate = TimestampConvertor.getCurrentDateTimeTimestamp();
        
        ArrayList<POSInfoBean> list = POSManager.getPOSInfo(ctx, fromDate, toDate, null);
        request.getSession().setAttribute(Constants.POS_INFO,list);
        
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
        SimpleDateFormat sdf = new SimpleDateFormat(TimestampConvertor.DEFAULT_DATE_PATTERN1);
        
        Timestamp toDate = new Timestamp(cal.getTimeInMillis());
        
        cal.set(Calendar.DATE,1);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        
        Timestamp fromDate =new Timestamp(cal.getTimeInMillis());
        
        ArrayList<POSInfoBean> list = POSManager.getPOSInfo(ctx, fromDate, toDate, null);
        request.getSession().setAttribute(Constants.POS_INFO, list);
        
        return mapping.findForward(GET_CURRENT_MONTH_REPORT);
    }
	
	public static final String GET_CUSTOM_REPORT = "getCustomReport";
	public ActionForward getCustomReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException, FileNotFoundException, DocumentException
    {
		ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;    
        ReportBean bean = (ReportBean) df.getBean();
        
        String title = "POS Info";
        String subtitle = "";
        
        String dateRange = bean.getDateRange();
        String timePeriod = bean.getTimePeriod();
        
        Timestamp fromDate = null;
        Timestamp toDate = null;
        
        //setting from and to dates
        if(dateRange.endsWith(Constants.FIXED_DATE_RANGE))
        {
        	if(timePeriod==null)
        	{
        		throw new OperationException("Invalid Parameter for timePeriod. timePeriod is NULL");
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
    
        ArrayList<Object[]> reportData = POSManager.getPOSInfoReportData(ctx, fromDate, toDate, null);        
        
        //constructing the table    	
    	TabularReport tReport = new TabularReport(reportData);    	
    	tReport.setSortable(false);
    	tReport.setStyle("display");
    	tReport.setTitle(title);
    	tReport.setSubtitle(subtitle);
    	tReport.createReport();    	
    	
    	String csvReport = CSVReportManager.generateCSVReport(ctx,reportData);
    	String csvURI = ReportManager.getReportURI(csvReport,request);  
    	
    	String pdfReport = tReport.createPDFReport();
    	String pdfURI = ReportManager.getReportURI(pdfReport,request);    	
    	
    	request.getSession().setAttribute(Constants.TABULAR_REPORT_DATA,tReport.toString());
    	request.getSession().setAttribute(Constants.CSV_FILE,csvURI);
    	
    	request.getSession().setAttribute(Constants.PDF_FILE,pdfURI);
        
    	return mapping.findForward(GET_CUSTOM_REPORT);        
        
    
    }
}
