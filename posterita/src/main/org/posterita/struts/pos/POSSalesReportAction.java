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
 * 17-Jul-2006 14:12:03 by praveen
 *
 */

package org.posterita.struts.pos;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.compiere.model.MAttribute;
import org.compiere.model.MAttributeSet;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;

import org.posterita.Constants;
import org.posterita.beans.ReportBean;
import org.posterita.businesslogic.ReportDateManager;
import org.posterita.businesslogic.ReportManager;
import org.posterita.businesslogic.SalesAnalysisReportManager;
import org.posterita.core.PieChart;
import org.posterita.core.RandomStringGenerator;
import org.posterita.core.SessionStorage;
import org.posterita.core.TimeSeriesChart;
import org.posterita.core.TmkJSPEnv;
import org.posterita.exceptions.ApplicationException;
import org.posterita.exceptions.OperationException;
import org.posterita.form.SalesReportForm;
import org.posterita.struts.core.DefaultForm;

public class POSSalesReportAction extends POSDispatchAction
{
	public static final String GET_CUSTOM_REPORT = "getCustomReport";
	public ActionForward getCustomReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws OperationException, ApplicationException
	{
		ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;       
       
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        DefaultForm df= (DefaultForm) form;
        
        ReportBean bean = (ReportBean) df.getBean();        
        String fromDate = ReportDateManager.getFromDate(bean);
        String toDate = ReportDateManager.getToDate(bean);        
        String timeperiod = bean.getTimePeriod();
        String chartType = bean.getChartType();
        String salesGroup = bean.getSalesGroup();
        
        String reportDir = ReportManager.getReportDirectoryFromServletContext(this.servlet);
        String filename = RandomStringGenerator.randomstring();  
        
        String subtitle = null;
        String title = null;
        
        if(timeperiod!=null)
        {
        	subtitle = "For " + timeperiod;
        }
        else
        {
        	subtitle = "For period:"+fromDate+" to "+toDate;
        }
        
        
        if(salesGroup.equalsIgnoreCase(Constants.REVENUE_RECOGNITION)||salesGroup.equalsIgnoreCase(Constants.ATTRIBUTESET))
        {
        	title = "Sales By " + salesGroup;
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
			
			title = "Sales By " + attributeName + " For " + attributeSetName;
        }
        
        
        if(chartType.equals(PieChart.CHART_TYPE))
        {
        	//draw pie chart
        	String pieChartSQL = SalesAnalysisReportManager.getPieChartDataSetSQL(ctx,Integer.valueOf(1),fromDate,toDate,salesGroup);        
            PieChart pieChart = new PieChart();
            
            pieChart.setTitle(title);
            pieChart.setSubtitle(subtitle);
            pieChart.getDataSetFromSQL(pieChartSQL);
            pieChart.getChart().setBackgroundPaint(Color.white);            
            pieChart.saveChartAsJPEG(reportDir+filename+".jpg",700,600);
        }
        else if(chartType.equals(TimeSeriesChart.CHART_TYPE))
        {
        	//draw timeseries
        	String timeSeriesChartSQL = SalesAnalysisReportManager.getTimeSeriesDataSetSQL(ctx,Integer.valueOf(1),fromDate,toDate,salesGroup);        
            TimeSeriesChart timeSeriesChart = new TimeSeriesChart();
            
            timeSeriesChart.setTitle(title);
            timeSeriesChart.setSubtitle(subtitle);        
            timeSeriesChart.setShowShapes(true);
            timeSeriesChart.getDataSetFromSQL(timeSeriesChartSQL);
            
            
            XYPlot plot = (XYPlot) timeSeriesChart.getChart().getPlot();
            DateAxis axis = (DateAxis) plot.getDomainAxis();    
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
            Date d1 = null;
    		Date d2 = null;;
    		try 
    		{
    			d1 = sdf.parse(fromDate);
    			d2 = sdf.parse(toDate);
    		} 
    		catch (ParseException e) 
    		{
    			throw new OperationException("Unable to parse dates supplied.");
    		}
            
            
            axis.setRange(d1,d2);
            //axis.setTickUnit(new DateTickUnit(DateTickUnit.MONTH,1,new SimpleDateFormat("MMM-yyyy")));
            
            timeSeriesChart.getChart().setBackgroundPaint(Color.white);            
            timeSeriesChart.saveChartAsJPEG(reportDir+filename+".jpg",700,600);
        }
        else
        {
        	throw new OperationException("Invalid Chart type:" + chartType);
        }
                
        String imgURL = request.getContextPath() + "/config/reports/"+filename+".jpg";
        request.getSession().setAttribute(Constants.REPORT_URL,imgURL);        
        
		return mapping.findForward(GET_CUSTOM_REPORT);
        //return getGroupedSalesReport(mapping,form,request,response);
	}
	
	public static final String INIT_CUSTOM_REPORT = "initCustomReport";
	public ActionForward initCustomReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws OperationException, ApplicationException
	{
		ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        SalesReportForm salesReportForm = (SalesReportForm) form;
        ReportBean reportBean = (ReportBean) salesReportForm.getBean();
        
        salesReportForm.setChartType(PieChart.CHART_TYPE);
        reportBean.setChartType(PieChart.CHART_TYPE);
        
        boolean error = false;
        
        if((salesReportForm.getFromDate()==null)||(salesReportForm.getFromDate()==""))
        {
        	postGlobalError("error.required.startDate",request);
        	error = true;
        }
        
        if((salesReportForm.getToDate()==null)||(salesReportForm.getToDate()==""))
        {
        	postGlobalError("error.required.endDate",request);
        	error = true;
        }
        
        if(error)
        {
        	return mapping.getInputForward();
        }
        
        SessionStorage.putSalesGroupFilter(ctx,request,reportBean);
        
        return mapping.findForward(INIT_CUSTOM_REPORT);
	}
	
	public static final String INIT_CUSTOM_SALES_REPORT = "initCustomSalesReport";
	public ActionForward initCustomSalesReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws OperationException, ApplicationException
	{
		ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;       
        
        Properties ctx = TmkJSPEnv.getCtx(request);
        SalesReportForm reportForm = (SalesReportForm) form;
        ReportBean reportBean = (ReportBean) reportForm.getBean();
        
        if(reportBean.getFromDate()==null)
        {
        	String timeperiod = reportBean.getTimePeriod();
        	if(timeperiod==null)
        	{
        		throw new OperationException("No period parameter found!");
        	}
        	
        	Date startDate = ReportDateManager.getStartDateForPeriod(timeperiod);
        	Date endDate = ReportDateManager.getEndDateForPeriod(timeperiod);
        	
        	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        	String fromDate = sdf.format(startDate);
        	String toDate = sdf.format(endDate);
        	
        	reportForm.setFromDate(fromDate);
        	reportForm.setToDate(toDate);
        	reportForm.setStartHour("0");
        	reportForm.setStartMinute("0");
        	reportForm.setChartType(PieChart.CHART_TYPE);
        	
        	reportBean.setFromDate(fromDate);
        	reportBean.setToDate(toDate);
        	reportBean.setStartHour("0");
        	reportBean.setStartMinute("0");
        	reportBean.setChartType(PieChart.CHART_TYPE);
        	
        	Calendar cal = Calendar.getInstance();
        	
        	reportForm.setToDate(toDate);
        	reportForm.setEndHour(cal.get(Calendar.HOUR_OF_DAY)+"");
        	reportForm.setEndMinute(cal.get(Calendar.MINUTE)+"");
        	
        	reportBean.setToDate(toDate);
        	reportBean.setEndHour(cal.get(Calendar.HOUR_OF_DAY)+"");
        	reportBean.setEndMinute(cal.get(Calendar.MINUTE)+"");
        	
        	SessionStorage.putSalesGroupFilter(ctx,request,reportBean);  	
        	
        }
        
        return mapping.findForward(INIT_CUSTOM_SALES_REPORT);
	}
	
	public static final String SALES_BY_ATTRIBUTESET = "salesByAttributeSet";
	public static final String SALES_BY_REVENUE_RECOGNITION = "salesByRevenueRecognition";
	
	public static final String GET_GROUPED_SALES_REPORT = "getGroupedSalesReport";
	public ActionForward getGroupedSalesReport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws OperationException, ApplicationException
	{
		ActionForward fwd= init(mapping,form,request,response);
        if(fwd!=null)
            return fwd;
        
        SalesReportForm reportForm = (SalesReportForm) form;
        ReportBean reportBean = (ReportBean) reportForm.getBean();
        
        String salesGroup = reportBean.getSalesGroup();        
        
        if(salesGroup.equalsIgnoreCase(Constants.REVENUE_RECOGNITION))
        {
        	return mapping.findForward(SALES_BY_REVENUE_RECOGNITION);
        }
        else if(salesGroup.equalsIgnoreCase(Constants.ATTRIBUTESET))
        {
        	return mapping.findForward(SALES_BY_ATTRIBUTESET);
        }
        else
        {
        	throw new OperationException("Invalid Sales Grouping: "+ salesGroup);
        }        
        
	}
	

}
