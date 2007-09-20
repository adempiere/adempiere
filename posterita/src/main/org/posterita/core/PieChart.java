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
 * 12-Jul-2006 17:18:07 by praveen
 *
 */

package org.posterita.core;

import java.sql.SQLException;

import org.compiere.util.DB;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.jdbc.JDBCPieDataset;

import org.posterita.exceptions.OperationException;

public class PieChart extends AbstractChart
{
	public static final String CHART_TYPE = "PieChart";
	public static final int PIE_FLAT = 1;
	public static final int PIE_3D = 2;
	
	private DefaultPieDataset dataset = null;	
	private int type = PIE_FLAT;

	public PieChart(){};
	
	public PieChart(DefaultPieDataset dataset,String title)
	{
		this.dataset = dataset;
		this.title = title;		
		
	}
	
	public JFreeChart createChart() throws OperationException 
	{
		if(dataset==null)
		{
			throw new OperationException("Cannot create Pie chart: cause -> dataset empty!");
		}		
		
		
		switch (type) {
		case PIE_FLAT:
			chart = ChartFactory.createPieChart(title,dataset,showLegend,showTooltip,true);
			break;
			
		case PIE_3D:
			chart = ChartFactory.createPieChart3D(title,dataset,showLegend,showTooltip,true);
			break;
			
		default:
			throw new OperationException("Invalid Piechart type! Can only be Piechart.PIE_FLAT or Piechart.PIE_3D");
		}
		
		//setting subtitle
		if(subtitle!=null)
		{
			TextTitle title = new TextTitle(subtitle);
			chart.addSubtitle(title);
		}	
		
		return chart;
		
	}
	
	public void getDataSetFromSQL(String sql) throws OperationException 
	{

		JDBCPieDataset jdbcDataset = new JDBCPieDataset(DB.getConnectionRO());
		
		try 
		{
			jdbcDataset.executeQuery(sql);
			this.dataset = jdbcDataset;
		} catch (SQLException e) {
			throw new OperationException(e);
		}
	}

	public DefaultPieDataset getDataset() {
		return dataset;
	}

	public void setDataset(DefaultPieDataset dataset) {
		this.dataset = dataset;
	}	

}
