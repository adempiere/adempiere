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
 * 24-Jul-2006 15:53:04 by praveen
 *
 */

package org.posterita.struts.pos;

import java.sql.Timestamp;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.compiere.model.MAttribute;
import org.compiere.model.MAttributeSet;
import org.compiere.model.MElementValue;
import org.compiere.util.Env;
import org.posterita.Constants;
import org.posterita.beans.ReportBean;
import org.posterita.businesslogic.performanceanalysis.CSVReportManager;
import org.posterita.businesslogic.performanceanalysis.CustomPOSReportManager;
import org.posterita.businesslogic.performanceanalysis.ReportDateManager;
import org.posterita.businesslogic.performanceanalysis.ReportManager;
import org.posterita.core.BarChart;
import org.posterita.core.PieChart;
import org.posterita.core.RandomStringGenerator;
import org.posterita.core.SessionStorage;
import org.posterita.core.TabularReport;
import org.posterita.core.TimeSeriesChart;
import org.posterita.core.TmkJSPEnv;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;
import org.posterita.form.SalesReportForm;

public class CustomPOSReportAction extends POSDispatchAction
{
	public static final String INIT_CUSTOM_REPORT = "initCustomReport";
	public ActionForward initCustomReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
	{
		 ActionForward fwd= init(mapping,form,request,response);
	        if(fwd!=null)
	            return fwd;
	        
		Properties ctx = TmkJSPEnv.getCtx(request);
		SalesReportForm reportForm = (SalesReportForm) form;         
        
        //setting defaults
        String chartType = PieChart.CHART_TYPE;
        String accountId = "41000";
        String timePeriod = ReportDateManager.TODAY;
        String salesGroup = Constants.REVENUE_RECOGNITION;
        String dateRange = Constants.FIXED_DATE_RANGE;        
                
        reportForm.setChartType(chartType);
        reportForm.setAccountId(accountId);
        reportForm.setTimePeriod(timePeriod);
        reportForm.setSalesGroup(salesGroup);
        reportForm.setDateRange(dateRange);    	
    	reportForm.setFromDate("");
    	reportForm.setToDate("");
        
        reportForm.validate(mapping,request);
        
        ReportBean reportBean = (ReportBean) reportForm.getBean();
                
        SessionStorage.putSalesGroupFilter(ctx,request,reportBean);  
        
		return mapping.findForward(INIT_CUSTOM_REPORT);
	}
	
	
	public static final String GET_CUSTOM_REPORT = "getCustomReport";
	public ActionForward getCustomReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ApplicationException, OperationException
	{
		 ActionForward fwd= init(mapping,form,request,response);
	        if(fwd!=null)
	            return fwd;
	        
		Properties ctx = TmkJSPEnv.getCtx(request);
		SalesReportForm reportForm = (SalesReportForm) form;        
        ReportBean bean = (ReportBean) reportForm.getBean();
        
        //validate form
        ActionMessages messages = validate(form);
        if(!messages.isEmpty())
        {
        	saveErrors(request, messages);            	
        	return mapping.getInputForward();
        }
        
        final Integer TAX_DUE 		= Constants.TAX_DUE;
        final Integer TAX_CREDIT 	= Constants.TAX_CREDIT;
        final Integer PROFIT_MARGIN	= Constants.PROFIT_MARGIN;
        
        Timestamp fromDate = null;
        Timestamp toDate = null;
        String dateRange = bean.getDateRange();
        String timePeriod = bean.getTimePeriod();
        String chartType = bean.getChartType();
        String salesGroup = bean.getSalesGroup();
        String priceQtyFilter = bean.getPriceQtyFilter();
        Integer account_id = bean.getAccountId();
        
        String filename = RandomStringGenerator.randomstring();
        String reportPath = ReportManager.getReportPath(filename);        
        
        String title = "";
        String subtitle = "";
        int height = 600;
        int width = 700;
        
        //setting from and to dates
        if(dateRange.endsWith(Constants.FIXED_DATE_RANGE))
        {
        	if(timePeriod == null)
        	{
        		throw new OperationException("Invalid Parameter for timePeriod. TimePeriod is null");
        	}
        	
        	fromDate = ReportDateManager.getStartDateForPeriod(timePeriod);
        	toDate = ReportDateManager.getEndDateForPeriod(timePeriod);
        	
        	subtitle = "For " + timePeriod;
        }
        else if(dateRange.endsWith(Constants.CUSTOM_DATE_RANGE))
        {
            fromDate = ReportDateManager.getFromDate(bean);
        	toDate = ReportDateManager.getToDate(bean);        	     
        	
        	subtitle = "For period:"+fromDate+" to "+toDate;
        }
        else
        {
        	throw new OperationException("Invalid Parameter for dateRange :" + dateRange);
        }
        
        //setting title
        String str = null;
        boolean isProfitMargin = (account_id.equals( PROFIT_MARGIN ));
        
        if(isProfitMargin)
        {
        	str = "Profit Margin";
        }
        else
        {
        	int[] ids = MElementValue.getAllIDs(MElementValue.Table_Name,"AD_CLIENT_ID ="+ Env.getAD_Client_ID(ctx) + " AND VALUE ='"+ account_id + "'",null);
            
            if(ids==null || ids.length==0)
            {
            	throw new OperationException("Unable to resolve account type!");
            }
            else
            {
            	MElementValue elementValue = new MElementValue(ctx,ids[0],null);
                str = elementValue.getName();
            }//else
        }//else
        
        
            
        boolean isTaxDue 	= (account_id.equals( TAX_DUE ));
        boolean isTaxCredit = (account_id.equals( TAX_CREDIT ));
        
        if(isTaxCredit || isTaxDue)
        {
        	title = str;
        }
        else
        {
        	title = str + " by ";
        	
        	if(
        			salesGroup.equalsIgnoreCase(Constants.REVENUE_RECOGNITION)||
        			salesGroup.equalsIgnoreCase(Constants.ATTRIBUTESET)	||
        			salesGroup.equalsIgnoreCase(Constants.PRODUCT)	||
        			salesGroup.equalsIgnoreCase(Constants.GROUP1)	||
        			salesGroup.equalsIgnoreCase(Constants.GROUP2)	||
        			salesGroup.equalsIgnoreCase(Constants.CUSTOMER)
        	)
            {
            	title = title + salesGroup;
            }
            else
            {
            	String[] s = salesGroup.split("_");
    			
    			if(s.length!=2)
    			{
    				throw new OperationException("Cannot generate sql. Unknown salesGroup parameter:"+salesGroup);
    			}
    			
    			int attributeSetId = Integer.parseInt(s[0]);
    			int attributeId = Integer.parseInt(s[1]);
    			
    			String attributeSetName = null;
    			String attributeName = null;
    			
    			attributeSetName = MAttributeSet.get(ctx,attributeSetId).getName();
    			attributeName = new MAttribute(ctx,attributeId,null).getName();
    			
    			title = title + attributeName + " For " + attributeSetName;	
    			
            }//else 
        	
        }//else                  
              
        
        //drawing chart
        if(chartType.equals(PieChart.CHART_TYPE))
        {
        	PieChart pieChart = CustomPOSReportManager.generatePieChart(ctx,title,subtitle,account_id.intValue(), fromDate, toDate, salesGroup,priceQtyFilter);           
            pieChart.saveChartAsJPEG(reportPath+".jpg",width,height);
        }
        else if(chartType.equals(BarChart.CHART_TYPE))
        {
        	BarChart barChart = CustomPOSReportManager.generateBarChart(ctx,title,subtitle,account_id.intValue(), fromDate, toDate,salesGroup,priceQtyFilter);
        	barChart.saveChartAsJPEG(reportPath+".jpg",width,height);
        }
        else if(chartType.equals(TimeSeriesChart.CHART_TYPE))
        {
        	//draw timeseries
        	TimeSeriesChart timeSeriesChart = CustomPOSReportManager.generateTimeSeriesChart(ctx,title,subtitle,account_id.intValue(), fromDate, toDate,salesGroup,priceQtyFilter);        
            timeSeriesChart.saveChartAsJPEG(reportPath+".jpg",width,height);
        }
        else if(chartType.equals("Tabular"))
        {
        	//constructing the table
        	TabularReport tReport = CustomPOSReportManager.generateTabularReport( ctx, title, subtitle, account_id.intValue(), fromDate, toDate, salesGroup, priceQtyFilter ); 	
        	
        	String csvReport = CSVReportManager.generateCSVReport(ctx,tReport.getReportData());
        	String csvURI = ReportManager.getReportURI(csvReport,request);    	
        	
        	request.getSession().removeAttribute(Constants.REPORT_URL);
        	request.getSession().setAttribute(Constants.TABULAR_REPORT_DATA,tReport.toString());
        	request.getSession().setAttribute(Constants.CSV_FILE,csvURI); 
        	request.getSession().setAttribute(Constants.DISPLAY_REPORT,""); 
        	
        	//-------------------------------------------------------------------------------------------------------------------
        	tReport = CustomPOSReportManager.generateTabularReportGroupByDate(ctx,title,subtitle,account_id.intValue(), fromDate, toDate,salesGroup,priceQtyFilter);
        	csvReport = CSVReportManager.generateCSVReport(ctx,tReport.getReportData());
        	csvURI = ReportManager.getReportURI(csvReport,request);    	
        	
        	request.getSession().removeAttribute(Constants.REPORT_URL);
        	request.getSession().setAttribute(Constants.TABULAR_REPORT_DATA2,tReport.toString());
        	request.getSession().setAttribute(Constants.CSV_FILE2,csvURI); 
        	request.getSession().setAttribute(Constants.DISPLAY_REPORT,""); 
        	
        	return mapping.findForward(GET_CUSTOM_REPORT);
        	
        	
        }
        else
        {
        	throw new OperationException("Invalid Chart type:" + chartType);
        }
                
        String imgURL = ReportManager.getReportURI(filename+".jpg",request);
        
        request.getSession().setAttribute(Constants.REPORT_URL,imgURL); 
        request.getSession().removeAttribute(Constants.TABULAR_REPORT_DATA); 
        request.getSession().setAttribute(Constants.DISPLAY_REPORT,""); 
        
        
        
        return mapping.findForward(GET_CUSTOM_REPORT);
	}
	
	
	//----------------------------------------------------------------------------------------------------
	public static ActionMessages validate(ActionForm form) throws OperationException, ApplicationException
    {
    	ActionMessages messages 	= new ActionMessages();
    	ActionMessage message 		= null;
    	boolean error				= false;    	
    	
		SalesReportForm reportForm 	= (SalesReportForm) form;        
        ReportBean bean 			= (ReportBean) reportForm.getBean();
        
    	String fromDate 		= bean.getFromDate();
        String toDate 			= bean.getToDate();
        String dateRange 	= bean.getDateRange();
        String timePeriod 		= bean.getTimePeriod();     
        
        
        if(dateRange.endsWith(Constants.FIXED_DATE_RANGE))
        {
        	if(timePeriod == null)
        	{
        		throw new OperationException("Invalid Parameter for timePeriod. Time Period is null.");
        	}        	
        }
        else if(dateRange.endsWith(Constants.CUSTOM_DATE_RANGE))
        {	
        	
        	if((fromDate == null)||(fromDate == ""))
            {            	
            	message = new ActionMessage("error.required.fromDate");
            	messages.add(ActionMessages.GLOBAL_MESSAGE, message);
            	error = true;
            }
            
            if((toDate == null)||(toDate == ""))
            {
            	message = new ActionMessage("error.required.toDate");            	
            	messages.add(ActionMessages.GLOBAL_MESSAGE, message);
            	error = true;
            }
            
            if(!error)
            { 	
            	
            	
            	Timestamp date1	= ReportDateManager.getFromDate(bean);
            	Timestamp date2	= ReportDateManager.getToDate(bean);
            	
            	if(date2.getTime()<= date1.getTime())
				{
					message = new ActionMessage("error.invalid.period");            	
		           	messages.add(ActionMessages.GLOBAL_MESSAGE, message);
		           	error = true;		            	
				}
            }        	
        }
        else
        {
        	throw new OperationException("Invalid Parameter for dateRange :" + dateRange);
        }
        
    	return messages;
    	
    }
	
	
}
