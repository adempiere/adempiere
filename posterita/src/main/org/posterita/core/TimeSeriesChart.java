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
 * 13-Jul-2006 09:59:29 by praveen
 *
 */

package org.posterita.core;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.compiere.util.DB;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import org.posterita.businesslogic.ReportManager;
import org.posterita.exceptions.OperationException;

/**
 * @author praveen
 *
 */
public class TimeSeriesChart extends AbstractChart
{
	public static final String CHART_TYPE = "TimeSeries";
	
	TimeSeriesCollection dataset = null;
	boolean showShapes = false;

	public TimeSeriesChart(){};
	
	public TimeSeriesChart(TimeSeriesCollection dataset,String title)
	{
		this.dataset = dataset;
		this.title = title;
	}
	
	public TimeSeriesChart(TimeSeriesCollection dataset,String title,String xLabel,String yLabel)
	{
		this.dataset = dataset;
		this.title = title;
		this.xLabel = xLabel;
		this.yLabel = yLabel;
	}
	
	public JFreeChart createChart() throws OperationException 
	{
		if(dataset == null)
		{
			throw new OperationException("Cannot create Time series chart: cause -> dataset null!");
		}
		
		chart =  ChartFactory.createTimeSeriesChart(title,xLabel,yLabel,dataset,showLegend,showTooltip,false);
		
		if(showShapes)
		{
			XYPlot plot = (XYPlot) chart.getPlot();	
			XYLineAndShapeRenderer render = (XYLineAndShapeRenderer) plot.getRenderer();
			render.setBaseShapesVisible(true);
		}
		
		//setting subtitle
		if(subtitle!=null)
		{
			TextTitle title = new TextTitle(subtitle);
			chart.addSubtitle(title);
		}
		
		//displaying labels
		if(showLabels)
		{
			XYPlot plot = (XYPlot) chart.getPlot();	
			XYLineAndShapeRenderer render = (XYLineAndShapeRenderer) plot.getRenderer();
			render.setItemLabelGenerator(new StandardXYItemLabelGenerator());
			render.setItemLabelsVisible(true);			
		}
		
		return chart;
		
	}

	/**
	 * Takes a sql as input and generates a Timeseries that is added to
	 * the dataset.	 
	 * Note: The sql must return 3 columns only Series, date & value
	 * Column 1: Type -> String
	 * Column 2: Type -> String or Date. For String the format must be as dd-MM-yyyy
	 * Column 3: Type -> BigDecimal
	 * 
	 * @param sql
	 */
	public void getDataSetFromSQL(String sql) throws OperationException 
	{
		PreparedStatement pstmt = DB.prepareStatement(sql,null);
		
		ArrayList<Object[]> dataSource = ReportManager.getReportData(pstmt);
		int count = 0;
		
		String seriesName = null;
		TimeSeries series = null;		
		BigDecimal value = null;
		int day = 0;
		int month = 0;
		int year = 0;
		
		TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection();		
				
		for (Object[] data : dataSource) 
		{
			if(data.length!=3)
				throw new OperationException("Unable to generate timeseries. Cause:Invalid sql, the return resultset must have 3 columns only");
			
			count++;
			if(count==1)
			{
				continue;			
			}
			
			seriesName = (String)data[0];
			String date = (String)data[1];
			value = (BigDecimal) data[2];
			String s[] = date.split("-");
			
			if(s.length!=3)	
				throw new OperationException("Unable to generate timeseries. " +
						"Cause:Invalid date format, the date returned should have the following format 'DD-MM-YYYY'");
			
			SimpleDateFormat sdf = new SimpleDateFormat();
			Calendar cal = Calendar.getInstance();
			Date d = null;
			
			try 
			{
				sdf.applyPattern("DD-MM-YYYY");
				d = sdf.parse(date);
			} 
			catch (ParseException e1) 
			{
				try 
				{
					sdf.applyPattern("DD-MMM-YYYY");
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
			
			
			series = timeSeriesCollection.getSeries(seriesName);
			
			if(series == null)
			{
				series = new TimeSeries(seriesName,Day.class);
				series.add(new Day(day,month,year),value);
				
				timeSeriesCollection.addSeries(series);				
			}
			else
			{
				series.add(new Day(day,month,year),value);
			}//if	
			
		}//for
		
		dataset = timeSeriesCollection;
	}
	
	public void getTimeSeriesFromSQL(String sql,String key) throws OperationException 
	{
		PreparedStatement pstmt = DB.prepareStatement(sql,null);
		
		ArrayList<Object[]> dataSource = ReportManager.getReportData(pstmt);		
		TimeSeries series = new TimeSeries(key,Day.class);
		int count = 0;		
		
		for (Object[] data : dataSource) 
		{
			if(data.length != 2)
				throw new OperationException("Unable to generate timeseries. Cause:Invalid sql, the return resultset must have 2 columns only");
			
			count++;
			if(count==1)continue;		
			
			series.add(new Day((Date)data[0]),(BigDecimal)data[1]);			
		}
		
		pstmt = null;
		addSeries(series);
	}
	
	
	public void addSeries(TimeSeries series)
	{
		if(dataset==null)
			dataset = new TimeSeriesCollection();
		
		dataset.addSeries(series);
	}

	public TimeSeriesCollection getDataset() {
		return dataset;
	}

	public void setDataset(TimeSeriesCollection dataset) {
		this.dataset = dataset;
	}

	public boolean isShowShapes() {
		return showShapes;
	}

	public void setShowShapes(boolean showShapes) {
		this.showShapes = showShapes;
	}
	
	

}
