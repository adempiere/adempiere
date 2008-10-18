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
 * 31-Jul-2006 12:07:35 by praveen
 *
 */

package org.posterita.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.apache.ecs.xhtml.table;
import org.apache.ecs.xhtml.td;
import org.apache.ecs.xhtml.th;
import org.apache.ecs.xhtml.tr;

import org.posterita.businesslogic.ReportManager;
import org.posterita.exceptions.OperationException;

public class TabularReport 
{
	private ArrayList<Object[]> reportData = null;
	private table tbl = new table();
	
	private boolean sortable = false;	
	private String id = null;
	private String[] headerStyle = null;
	private String style = null;
	private String title = null;
	private String subtitle = null;

	
	public TabularReport(ArrayList<Object[]> reportData)
	{
		this.reportData = reportData;
	}
	
	public TabularReport()
	{
		
	}
	
	public void createReport() throws OperationException
	{
		
		tbl = new table();
    	tbl.addAttribute("border","0");
    	tbl.addAttribute("width","100%");        	
    	tbl.addAttribute("class","main");
    	
    	td tdtitle = new td("<font class='reporttitle'>" + title + "</font>");
    	td tdsubtitle = new td("<font class='reportsubtitle'>" + subtitle + "</font>");
    	
    	tbl.addElement(
		        			new tr().addElement(tdtitle.addAttribute("align","center"))
    					);
    	
    	tbl.addElement(
    						new tr().addElement(tdsubtitle.addAttribute("align","center"))
						);
    	
    	tbl.addElement(new td("&nbsp;"));
    	
    	////////////////////////////////////////////////////////////////////////
    	table displaytbl = new table(); 
    	
    	if(reportData==null)
		{
			throw new OperationException("Cannot create tabular report: cause -> dataset empty!");
		}	
		
		id = RandomStringGenerator.randomstring();
		    	
    	displaytbl.addAttribute("border","1");
    	//displaytbl.addAttribute("width","100%");
    	
    	String styleClass = (style == null) ? "" : style;
    	if(isSortable())
    	{
    		styleClass = styleClass + " sortable";
    	}
    	displaytbl.addAttribute("class",styleClass.trim());
    	displaytbl.addAttribute("id",id);    	
    	
    	Iterator<Object[]> iter = reportData.iterator();        	
    	Object[] headers = iter.next();
    	
    	int columnCount = headers.length;
    	
    	tr r = new tr();        	
    	
    	for(int i=0;i<columnCount;i++)
    	{
    		th h = new th(headers[i]+"");
    		if((headerStyle != null) && (headerStyle.length > i))
    		{
    			String style = headerStyle[i]+"";
    			h.addAttribute("class",style);
    		}    		
    		
    		r.addElement(h);
    	}
    	
    	displaytbl.addElement(r);
    	
    	int count = 0;
    	String[] s = {"label","contentname"};
    	String tdStyle = "";
    	
    	while(iter.hasNext())
    	{
    		count++;
    		
    		tdStyle = s[count%2];
    		
    		Object[] data = iter.next();
    		tr rr = new tr();
    		        		
    		for(int i=0;i<columnCount;i++)
        	{
        		td d = new td(data[i]+"");
        		d.addAttribute("class",tdStyle);
        		
        		rr.addElement(d);
        	}
    		
    		displaytbl.addElement(rr);
    	}
    	////////////////////////////////////////////////////////////////////////
    	tbl.addElement(new tr().addElement(new td().addElement(displaytbl)));
		
	}
	
	public String toString()
	{
		return tbl.toString();
	}
	
	public void getDataFromSQL(Properties ctx,String sql) throws OperationException
	{
		reportData = ReportManager.getReportData(ctx,sql);
	}

	public ArrayList<Object[]> getReportData() {
		return reportData;
	}

	public void setReportData(ArrayList<Object[]> reportData) {
		this.reportData = reportData;
	}

	public table getTable() {
		return tbl;
	}

	public String[] getHeaderStyle() {
		return headerStyle;
	}

	public void setHeaderStyle(String[] headerStyle) {
		this.headerStyle = headerStyle;
	}

	public boolean isSortable() {
		return sortable;
	}

	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
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
	
	
}
