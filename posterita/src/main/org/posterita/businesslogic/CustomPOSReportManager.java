/**
 *  Product: Posterita Web-Based POS (an Adempiere Plugin)
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
 */

/**
	@author Praveen Beekoo
 */
package org.posterita.businesslogic;

import java.awt.Color;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.TreeMap;

import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import org.posterita.Constants;
import org.posterita.beans.ReportBean;
import org.posterita.beans.TabularReportRecordBean;
import org.posterita.core.BarChart;
import org.posterita.core.PieChart;
import org.posterita.core.TabularReport;
import org.posterita.core.TimeSeriesChart;
import org.posterita.exceptions.OperationException;

public class CustomPOSReportManager 
{
	public static PieChart generatePieChart(Properties ctx,String title,String subtitle,int account_id,String fromDate,String toDate,String salesGroup,String priceQtyFilter) throws OperationException
	{
		PieChart pieChart = new PieChart();            
        pieChart.setTitle(title);
        pieChart.setSubtitle(subtitle);
        
        String pieChartSQL = SalesAnalysisReportManager.getPieChartDataSetSQL(ctx,account_id,fromDate,toDate,salesGroup);        
        ArrayList<Object[]> list = ReportManager.getReportData(ctx,pieChartSQL,false);        
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        StandardPieSectionLabelGenerator labelGenerator = null;
        
        
        if(priceQtyFilter.equalsIgnoreCase(Constants.PRICE))
        {
        	//against price
        	for(Object[] obj:list)
            {
            	String name = (String) obj[0];
            	BigDecimal price = (BigDecimal) obj[1];            	
            	
            	pieDataset.setValue(name,price);            	
            } 
        	
        	String currency = POSTerminalManager.getPOSDefaultSellCurrency(ctx).getCurSymbol();
        	labelGenerator = new StandardPieSectionLabelGenerator("{0} = "+ currency +"{1}");        	
        }
        else
        {
        	//against qty
        	for(Object[] obj:list)
            {
            	String name = (String) obj[0];            	
            	BigDecimal qty = (BigDecimal) obj[2];
            	
            	pieDataset.setValue(name,qty);            	
            }
        	
        	labelGenerator = new StandardPieSectionLabelGenerator();
        }        
        
        
        pieChart.setDataset(pieDataset);
        PiePlot p = (PiePlot) pieChart.getChart().getPlot();        
        p.setLegendLabelGenerator(labelGenerator);        
        pieChart.getChart().setBackgroundPaint(Color.white);        
		
        return pieChart;
	}
	
	public static BarChart generateBarChart(Properties ctx,String title,String subtitle,int account_id,String fromDate,String toDate,String salesGroup,String priceQtyFilter) throws OperationException
	{    	        
    	BarChart barChart = new BarChart();        
    	barChart.setTitle(title);
    	barChart.setSubtitle(subtitle);
    	//barChart.getDataSetFromSQL(barChartSQL);
    	
    	String barChartSQL = SalesAnalysisReportManager.getBarChartDataSetSQL(ctx,account_id,fromDate,toDate,salesGroup);
    	ArrayList<Object[]> list = ReportManager.getReportData(ctx,barChartSQL,true);    	
    	DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
    	
    	Object[] header = list.remove(0);
    	String grouping = header[0]+"";
    	String yLabel = null;
    	
    	
    	if(priceQtyFilter.equalsIgnoreCase(Constants.PRICE))
    	{
    		String currency = POSTerminalManager.getPOSDefaultSellCurrency(ctx).getCurSymbol();
    		
    		//against price
    		for(Object[] obj:list)
            {
            	String name = (String) obj[0];
            	BigDecimal price = (BigDecimal) obj[1];            	
            	
            	categoryDataset.setValue(price,grouping,name);
            	yLabel = "Value (" + currency + ")";
            } 
    	}
    	else
    	{
    		//against qty
    		for(Object[] obj:list)
            {
            	String name = (String) obj[0];            	
            	BigDecimal qty = (BigDecimal) obj[2];
            	
            	categoryDataset.setValue(qty,grouping,name); 
            	yLabel = "Quantity";
            }
    	}
    	
    	//xLabel = grouping;
    	   	
    	//---------------------------------------------------------------------------------
    	barChart.setDataset(categoryDataset);
    	barChart.setIntegerTickUnits(true);
    	
    	//CategoryItemRenderer itemRender = barChart.getChart().getPlot();
		//itemRender.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());		
    	
    	barChart.setYLabel(yLabel);
    	//barChart.setXLabel(xLabel);
    	barChart.setShowLabels(true);
    	barChart.getChart().setBackgroundPaint(Color.white);
    	
    	
		return barChart;
	}
	
	public static TimeSeriesChart generateTimeSeriesChart(Properties ctx,String title,String subtitle,int account_id,String fromDate,String toDate,String salesGroup,String priceQtyFilter) throws OperationException
	{
		TimeSeriesChart timeSeriesChart = new TimeSeriesChart();        
        timeSeriesChart.setTitle(title);
        timeSeriesChart.setSubtitle(subtitle);        
        timeSeriesChart.setShowShapes(true);       
        //timeSeriesChart.getDataSetFromSQL(timeSeriesChartSQL);
        
        String timeSeriesChartSQL = SalesAnalysisReportManager.getTimeSeriesDataSetSQL(ctx,account_id,fromDate,toDate,salesGroup);        
        ArrayList<Object[]> list = ReportManager.getReportData(ctx,timeSeriesChartSQL,false);
        
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries series = null;
        String yLabel = null;
        
        String seriesName = null;
        String date = null;
        BigDecimal price = null;
        String[] s = null;
        int day,month,year;
        
        for (Object[] data : list) 
		{    			    			
			seriesName = (String)data[0];
			date = (String)data[1];
			price = (BigDecimal) data[2];
			s = date.split("-");
			
			if(s.length!=3)	
				throw new OperationException("Unable to generate timeseries. " +
						"Cause:Invalid date format, the date returned should have the following format 'DD-MM-YYYY'");
			
			SimpleDateFormat sdf = new SimpleDateFormat();
			Calendar cal = Calendar.getInstance();
			Date d = null;
			
			try 
			{
				sdf.applyPattern("dd-MM-yyyy");
				d = sdf.parse(date);
			} 
			catch (ParseException e1) 
			{
				try 
				{
					sdf.applyPattern("dd-MMM-yyyy");
					d = sdf.parse(date);
				} 
				catch (ParseException e) 
				{
					throw new OperationException("Unable to generate timeseries. " +
							"Cause:Invalid date format, the date returned should have one of the following formats 'DD-MM-YYYY' or 'DD-MMM-YYYY'",e);
				}
			}
			
			
			cal.setTime(d);
			
			day = cal.get(Calendar.DATE);
			month = cal.get(Calendar.MONTH) + 1;
			year = cal.get(Calendar.YEAR);
			
			series = dataset.getSeries(seriesName);
			
			if(series == null)
			{
				series = new TimeSeries(seriesName,Day.class);
				series.add(new Day(day,month,year),price);
				
				dataset.addSeries(series);				
			}
			else
			{
				series.add(new Day(day,month,year),price);
			}//if	
			
		}//for
        
        if(priceQtyFilter.equalsIgnoreCase(Constants.PRICE))
        {
        	//against price
        	String currency = POSTerminalManager.getPOSDefaultSellCurrency(ctx).getCurSymbol();
        	yLabel = "Value (" + currency + ")";
        }
        else
        {
        	yLabel = "Quantity";
        }  
        
        timeSeriesChart.setYLabel(yLabel);
        timeSeriesChart.setDataset(dataset);
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
        
        return timeSeriesChart;
	}
	
	public static TabularReport generateTabularReport(Properties ctx,String title,String subtitle,int account_id,String fromDate,String toDate,String salesGroup,String priceQtyFilter) throws OperationException
	{
		String sql = SalesAnalysisReportManager.getTabularDataSetSQL(ctx,account_id,fromDate,toDate,salesGroup);
    	ArrayList<Object[]> tmpData = ReportManager.getReportData(ctx,sql,true);
    	String currency = POSTerminalManager.getPOSDefaultSellCurrency(ctx).getCurSymbol();
    	
    	ArrayList<Object[]> reportData = new ArrayList<Object[]>();
    	//copying data from tmpData to reportData
    	
    	NumberFormat formatter = new DecimalFormat("###,###,##0.00");            
        
    	Iterator<Object[]> iter = tmpData.iterator();
    	
    	Object[] data = null;
    	Object[] headers = null;
    	BigDecimal b,c = null;
    	
    	boolean isTaxDue 		= (account_id == Constants.TAX_DUE.intValue() );
        boolean isTaxCredit 	= (account_id == Constants.TAX_CREDIT.intValue() );        
        
        if(isTaxCredit || isTaxDue)
        {
        	//copying header
        	headers = iter.next();
        	
        	reportData.add( new Object[]{
        			headers[0] +"("+ currency +")",
        	});
        	
        	while(iter.hasNext())
        	{
        		data = iter.next();
        		
        		if(data.length == 1)
        		{
        			b = (BigDecimal)data[0];        		
            		data[0] = formatter.format(b.doubleValue());
        		}   		
        		
        		reportData.add(data);
        	}  	
        }
        else
        {
       	    //copying header
        	headers = iter.next();
        	
        	reportData.add( new Object[]{
        			headers[0],
        			headers[1],
        			headers[2] +"("+ currency +")",
        			headers[3]
        	});
        	    
        	double totalAmt = 0.0d;
        	int totalQty = 0;
        	
    		while(iter.hasNext())
        	{
        		data = iter.next();
        		
        		
        		if(data.length > 2)
        		{
        			b = (BigDecimal)data[2];   
        			c = (BigDecimal)data[3];
        			
            		data[2] = formatter.format(b.doubleValue());
            		
            		totalAmt += b.doubleValue();
            		totalQty += c.intValue();
            		
        		}   		
        		
        		reportData.add(data);
        	}
    		
    		reportData.add(new Object[]{"Total","", formatter.format(totalAmt)+"", totalQty+""});
        	
        } 	
    	
        
        //style for table
        String tableStyle = "display";
        //style for columns        
        String[] styles = new String[]{"string","date","currency","numeric"};
        
        if(isTaxCredit || isTaxDue)
        {
        	styles = new String[]{"numeric"};        	
        }
            	
    	//constructing the table
    	TabularReport tReport = new TabularReport(reportData);    	
    	//tReport.setSortable(true);
    	tReport.setHeaderStyle(styles);
    	tReport.setStyle(tableStyle);
    	tReport.setTitle(title);
    	tReport.setSubtitle(subtitle);
    	tReport.createReport();
    	
    	return tReport;
	}
	
	public static TabularReport generateTabularReportGroupByDate(Properties ctx,String title,String subtitle,int account_id,String fromDate,String toDate,String salesGroup,String priceQtyFilter) throws OperationException
	{
		boolean isTaxDue 		= (account_id == Constants.TAX_DUE.intValue() );
        boolean isTaxCredit 	= (account_id == Constants.TAX_CREDIT.intValue() ); 
        
        NumberFormat formatter = new DecimalFormat("###,###,##0.00");
        
		String sql = SalesAnalysisReportManager.getTabularDataSetSQL(ctx,account_id,fromDate,toDate,salesGroup);
    	ArrayList<Object[]> tmpData = ReportManager.getReportData(ctx,sql,true);
    	String currency = POSTerminalManager.getPOSDefaultSellCurrency(ctx).getCurSymbol();
    	        
      
    	ArrayList<Object[]> reportData = new ArrayList<Object[]>();
        Object[] data = null;
        BigDecimal b = null;
        
        if(isTaxCredit || isTaxDue)
        {
        	reportData.add( tmpData.remove(0) );
        	Iterator<Object[]> iter = tmpData.iterator(); 
        	
        	while(iter.hasNext())
        	{
        		data = iter.next();
        		
        		if(data.length == 1)
        		{
        			b = (BigDecimal)data[0];        		
            		data[0] = formatter.format(b.doubleValue());
        		}   		
        		
        		reportData.add(data);
        	}  	
        }
        else
        {
            //----------------------------------------------------------------------------------------------------------------------------------------------------------
            TreeMap<String, TabularReportRecordBean> map = new TreeMap<String, TabularReportRecordBean>();
            
            String productName 	= null;
        	BigDecimal price 		= null;
        	BigDecimal qty			= null;
        	
        	TabularReportRecordBean bean = null;        
        	
        	 ArrayList<Object[]> reportData2 = new ArrayList<Object[]>();
        	 Object[] headers = tmpData.remove( 0 );
        	 
        	 //adding headers
        	 reportData2.add( new Object[]{
        			 headers[0],
        			 //headers[1],
        			 headers[2] +"("+ currency +")",
        			 headers[3]
        	 } );
        	 
        	double totalAmt = 0.0d;
         	int totalQty = 0;
        	 
            for( Object[] record : tmpData)
            {
            	productName 	= (String) record[0];
            	price 				= (BigDecimal) record[2];
            	qty					= (BigDecimal) record[3];
            	
            	totalAmt += price.doubleValue();
            	totalQty += qty.intValue();
            	
            	bean = map.get( productName );
            	
            	if( bean == null )
            	{
            		bean = new TabularReportRecordBean();
            		
            		bean.setProductName( productName );
            		bean.setDate("");
            		bean.setPrice( price );
            		bean.setQty( qty );        		
            	}
            	else
            	{
            		bean.setPrice( bean.getPrice().add( price ) );
            		bean.setQty( bean.getQty().add( qty ) );
            	}
            	
            	map.put( productName, bean );
            	
            }//for 
            
            
            Collection<TabularReportRecordBean> c = map.values();
            
            for( TabularReportRecordBean tbean : c )
            {
            	Object[] obj = new Object[]{
            			tbean.getProductName(),
            			tbean.getPrice(),
            			tbean.getQty()
            	};
            	
            	reportData2.add( obj );
            }            
            
            reportData.add( reportData2.remove(0) );
            
            Iterator<Object[]> iter = reportData2.iterator(); 
        
    		while(iter.hasNext())
        	{
        		data = iter.next();
        		
        		if(data.length > 2)
        		{
        			b = (BigDecimal)data[1];        		
            		data[1] = formatter.format(b.doubleValue());
        		}   		
        		
        		reportData.add(data);
        	}
    		
    		reportData.add(new Object[]{"Total","" + formatter.format(totalAmt), totalQty + ""});
        	
        } 	
    	
        
        //style for table
        String tableStyle = "display";
        //style for columns        
        String[] styles = new String[]{"string","currency","numeric"};
        
        if(isTaxCredit || isTaxDue)
        {
        	styles = new String[]{"numeric"};        	
        }
            	
    	//constructing the table
    	TabularReport tReport = new TabularReport(reportData);    	
    	//tReport.setSortable(true);
    	tReport.setHeaderStyle(styles);
    	tReport.setStyle(tableStyle);
    	tReport.setTitle(title);
    	tReport.setSubtitle(subtitle);
    	tReport.createReport();
    	
    	return tReport;
	}
	
	/**
	 * Generates the title of the custom report
	 * @param ctx
	 * @param title
	 * @param bean
	 * @return true is returned if set
	 */
	public static boolean setTitle(Properties ctx, String title, ReportBean bean)
	{
		return true;
	}
	
	/**
	 * Generates the subtitle of the custom report
	 * @param ctx
	 * @param subtitle
	 * @param bean
	 * @return true is returned if set
	 */
	public static String setSubTitle(Properties ctx, String subtitle, ReportBean bean)
	{
		return null;
	}
		
    
}
