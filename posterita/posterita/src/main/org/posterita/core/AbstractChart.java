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
 * 12-Jul-2006 15:56:26 by praveen
 *
 */

package org.posterita.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;

import org.posterita.exceptions.OperationException;

public abstract class AbstractChart 
{
	protected String title = "";
	protected String subtitle = null;
	protected String xLabel = "";
	protected String yLabel = "";	
	protected boolean showLegend = true;
	protected boolean showTooltip = true;
	protected boolean showLabels = false;
	protected ChartRenderingInfo renderingInfo = new ChartRenderingInfo(new StandardEntityCollection());
	protected JFreeChart chart = null;
	
	public abstract JFreeChart createChart() throws OperationException ;
	
	public abstract void getDataSetFromSQL(String sql) throws OperationException ;
	
	public String saveChartAsPNG(String filePath,int width,int height) throws OperationException
	{
		try {
			ChartUtilities.saveChartAsPNG(new File(filePath),getChart(),width,height,renderingInfo);
			return filePath;
		} catch (IOException e) {
			throw new OperationException("Problem occured while saving chart.",e);
		}
	}
	
	public String saveChartAsJPEG(String filePath,int width,int height) throws OperationException
	{
		try {
			ChartUtilities.saveChartAsJPEG(new File(filePath),getChart(),width,height,renderingInfo);
			return filePath;
		} catch (IOException e) {
			throw new OperationException("Problem occured while saving chart.",e);
		}
	}
	
	public void writeChartAsPNG(OutputStream outputStream,int width,int height) throws OperationException
	{
		try {
			ChartUtilities.writeChartAsPNG(outputStream,getChart(),width,height,renderingInfo);
		} catch (IOException e) {
			throw new OperationException("Problem occured while write chart.",e);
		}
	}
	
	public void writeChartAsJPEG(OutputStream outputStream,int width,int height) throws OperationException
	{
		try {
			ChartUtilities.writeChartAsJPEG(outputStream,getChart(),width,height,renderingInfo);
		} catch (IOException e) {
			throw new OperationException("Problem occured while write chart.",e);
		}
	}
	
	public String getImageMap(String imageMapName) throws OperationException
	{
		try {
			StringWriter stringWriter = new StringWriter();			
			PrintWriter printWriter = new PrintWriter(stringWriter);
			ChartUtilities.writeImageMap(printWriter, imageMapName, renderingInfo,false);
			printWriter.flush();
			
			String imageMap = stringWriter.getBuffer().toString();
			return imageMap;
		} catch (IOException e) {
			throw new OperationException("Problem occured while writing imagemap",e);
		}
	}
	
	public void writeImageMap(OutputStream outputStream,String imageMapName) throws OperationException
	{
		PrintWriter printWriter = new PrintWriter(outputStream);
		try {
			ChartUtilities.writeImageMap(printWriter, imageMapName, renderingInfo,false);
			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			throw new OperationException("Problem occured while writing imagemap",e);
		}		
	}
	
	public String saveImageMap(String imageMapPath) throws OperationException
	{
		String fileSeparator = System.getProperty("file.separator");
		int index = imageMapPath.lastIndexOf(fileSeparator)+1;
		String imageMapName = imageMapPath.substring(index);	
			
		try {	
			FileOutputStream fos = new FileOutputStream(new File(imageMapPath));
			PrintWriter printWriter = new PrintWriter(fos);
			ChartUtilities.writeImageMap(printWriter, imageMapName, renderingInfo,false);
			printWriter.flush();
			fos.close();
			
			return imageMapName;
		} catch (IOException e) {
			throw new OperationException("Problem occured while writing imagemap",e);
		}
	}

	public boolean isShowLegend() {
		return showLegend;
	}

	public void setShowLegend(boolean showLegend) {
		this.showLegend = showLegend;
	}

	public boolean isShowTooltip() {
		return showTooltip;
	}

	public void setShowTooltip(boolean showTooltip) {
		this.showTooltip = showTooltip;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getXLabel() {
		return xLabel;
	}

	public void setXLabel(String label) {
		xLabel = label;
	}

	public String getYLabel() {
		return yLabel;
	}

	public void setYLabel(String label) {
		yLabel = label;
	}

	public boolean isShowLabels() {
		return showLabels;
	}

	public void setShowLabels(boolean showLabels) {
		this.showLabels = showLabels;
	}

	public ChartRenderingInfo getRenderingInfo() {
		return renderingInfo;
	}

	public void setRenderingInfo(ChartRenderingInfo renderingInfo) {
		this.renderingInfo = renderingInfo;
	}

	public JFreeChart getChart() throws OperationException {
		
		if(chart==null)
			createChart();
		
		return chart;
	}		
	
}
