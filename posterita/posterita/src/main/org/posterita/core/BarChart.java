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
 * 12-Jul-2006 16:05:22 by praveen
 *
 */

package org.posterita.core;

import java.sql.SQLException;

import org.compiere.util.DB;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;

import org.posterita.exceptions.OperationException;

public class BarChart extends AbstractChart
{
	public static final String CHART_TYPE = "BarChart";
	public static final PlotOrientation VERTICAL = PlotOrientation.VERTICAL;
	public static final PlotOrientation HORIZONTAL = PlotOrientation.HORIZONTAL;
	public static final int BARCHART_FLAT = 1;
	public static final int BARCHART_3D = 2;	
	
	private DefaultCategoryDataset dataset = null;
	private PlotOrientation orientation = VERTICAL;
	private int type = BARCHART_FLAT;
	private double maximumBarWidth = 0.1d;
	private boolean integerTickUnits = false;
		
	public BarChart(){};
	
	public BarChart(DefaultCategoryDataset dataset,String title)
	{
		this.dataset = dataset;
		this.title = title;		
		
	}
	
	public BarChart(DefaultCategoryDataset dataset,String title,String xLabel,String yLabel)
	{
		this.dataset = dataset;
		this.title = title;	
		this.xLabel = xLabel;
		this.yLabel = yLabel;
	}
	
	public JFreeChart createChart() throws OperationException 
	{	
		if(dataset==null)
		{
			throw new OperationException("Cannot create Bar chart: cause -> dataset empty!");
		}
				
		switch (type) {
		case BARCHART_FLAT:
			chart = ChartFactory.createBarChart(title,xLabel,yLabel,dataset,orientation,showLegend,showTooltip,true);
			break;
			
		case BARCHART_3D:
			chart = ChartFactory.createBarChart3D(title,xLabel,yLabel,dataset,orientation,showLegend,showTooltip,true);
			break;
			
		default:
			throw new OperationException("Invalid barchart type! Can only be BarChart.BARCHART_FLAT or BarChart.BARCHART_3D");
		}
		
		//setting subtitle
		if(subtitle!=null)
		{
			TextTitle title = new TextTitle(subtitle);
			chart.addSubtitle(title);
		}
		
		CategoryPlot plot = chart.getCategoryPlot();
		NumberAxis axis = (NumberAxis) plot.getRangeAxis();
		
		//setting tickUnits
		if(integerTickUnits)
			axis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		
		BarRenderer barRender = (BarRenderer) plot.getRenderer();
		barRender.setMaximumBarWidth(maximumBarWidth);
		
		//displaying labels
		if(showLabels)
		{
			CategoryItemRenderer itemRender = plot.getRenderer();
			itemRender.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());		
			itemRender.setItemLabelsVisible(true);
		}
		
		return chart;
	}

	public DefaultCategoryDataset getDataset() {
		return dataset;
	}

	public void setDataset(DefaultCategoryDataset dataset) {
		this.dataset = dataset;
	}

	public PlotOrientation getOrientation() {
		return orientation;
	}

	public void setOrientation(PlotOrientation orientation) {
		this.orientation = orientation;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getMaximumBarWidth() {
		return maximumBarWidth;
	}

	public void setMaximumBarWidth(double maximumBarWidth) {
		this.maximumBarWidth = maximumBarWidth;
	}

	public boolean isIntegerTickUnits() {
		return integerTickUnits;
	}

	public void setIntegerTickUnits(boolean integerTickUnits) {
		this.integerTickUnits = integerTickUnits;
	}

	public void getDataSetFromSQL(String sql) throws OperationException 
	{
		JDBCCategoryDataset jdbcDataset = new JDBCCategoryDataset(DB.getConnectionRO());
		
		try 
		{
			jdbcDataset.executeQuery(sql);
			this.dataset = jdbcDataset;
		} catch (SQLException e) {
			throw new OperationException(e);
		}
	}
	
}
