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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.apache.ecs.xhtml.table;
import org.apache.ecs.xhtml.td;
import org.apache.ecs.xhtml.th;
import org.apache.ecs.xhtml.tr;
import org.posterita.businesslogic.performanceanalysis.ReportManager;
import org.posterita.exceptions.OperationException;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class TabularReport2 
{
	private ArrayList<Object[]> reportData = null;
	private table tbl = new table();
	
	private boolean sortable = false;	
	private String id = null;
	private String[] headerStyle = null;
	private String style = null;
	private String title = null;
	private String subtitle = null;
	private Timestamp fromDate = null;
	private Timestamp toDate = null;
	private boolean isSalesReport = false;
	private boolean fullDetails = false;
	
	public TabularReport2(ArrayList<Object[]> reportData)
	{
		this.reportData = reportData;
	}
	
	public TabularReport2()
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
    	
    	String date1 = "";
    	String date2 = "";
    	String temp1 = "";
    	String temp2 = "";
    	
    	while(iter.hasNext())
    	{
    		count++;
    		
    		tdStyle = s[count%2];
    		
    		Object[] data = iter.next();
    		tr rr = new tr();
    		
    		if (count == 1)
			{
				temp1 = data[0].toString();
			}
    		if (count == reportData.size() - 2)
    		{
    			temp2 = data[0].toString();
    		}
    		date2 = date1 = data[0].toString();
    		
    		if(count == reportData.size()-1)
    		{
    			date1 = temp1;
    			date2 = temp2;
    		}
    		        		
    		for(int i=0;i<columnCount;i++)
        	{    			
    			String cellData = "";
    			Double qty = 0.0;
    			
    			cellData = data[i]+"";
				if (i>0 && i<5)
				{
					if (data[i]!=null)
	    			{
	    				qty = Double.valueOf(data[i].toString());
	    			}
					if (qty != 0.0)
    				{				
						cellData = "<a href=\"SalesReportAction.do?action=getDetailedSalesReport&salesType=" + i + 
						"&isSalesReport="+ isSalesReport + "&isFullDetails="+ isFullDetails() +"&date1=" + data[0].toString() + "&date2=" + data[0].toString() + "\">" + data[i] + "</a>";
    				}
				}
    			
    			
        		td d = new td(cellData);
        		
        		if(count == reportData.size()-1)
        		{
        			d.setAlign("left");
        		}			
        		
        		d.addAttribute("class",tdStyle);
        		
        		rr.addElement(d);
        	}
    		
    		displaytbl.addElement(rr);
    	}
    	////////////////////////////////////////////////////////////////////////
    	tbl.addElement(new tr().addElement(new td().addElement(displaytbl)));
		
	}
	
	public void createReport(String action) throws OperationException
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
				String cellData = "";
				
				if(i > 0 && i < 5)
				{    				
					cellData = "<a href = \"" + action + "&product";
				}
				else
				{
					cellData = data[i]+"";
				}
				
				
	    		td d = new td(cellData);
	    		
	    		if(count == reportData.size()-1)
	    		{
	    			d.setAlign("left");
	    		}			
	    		
	    		d.addAttribute("class",tdStyle);
	    		
	    		rr.addElement(d);
	    	}
		
	    	displaytbl.addElement(rr);
    	}
//////////////////////////////////////////////////////////////////////
    	tbl.addElement(new tr().addElement(new td().addElement(displaytbl)));
	}
	
	public String createPDFReport() throws DocumentException, FileNotFoundException
	{
		String filename = RandomStringGenerator.randomstring() + ".pdf";
        String filepath = ReportManager.getReportPath(filename);
        
        FileOutputStream fos = new FileOutputStream(filepath);  		
		
		Document document = new Document(PageSize.A4.rotate());
		PdfWriter pdfWriter = PdfWriter.getInstance(document, fos);
		
		document.open();
		
		Font style1 = new Font(Font.HELVETICA, 16.0f, Font.BOLD);
		Font style2 = new Font(Font.HELVETICA, 14.0f);
		Font style3 = new Font(Font.HELVETICA, 10.0f);
		
		//write content
		Paragraph p = new Paragraph(new Chunk(title, style1));
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
		
		p = new Paragraph(new Chunk(subtitle, style2));
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
		
		p = new Paragraph("\n\n");
		document.add(p);
		
		Object[] headers = reportData.get(0);
		
		PdfPTable table = new PdfPTable(headers.length);
		table.setWidthPercentage(100.0f);
		
		//int[] widths = new int[]{1,2,1,4};
		//table.setWidths(widths);
		
		for(Object[] row : reportData)
		{
			for(Object data : row)
			{
				PdfPCell cell = new PdfPCell(new Phrase(data.toString(), style3));
				table.addCell(cell);
			}
		}
		
		document.add(table);		
		document.close();		
        
		
		return filename;
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

	public Timestamp getFromDate() {
		return fromDate;
	}

	public void setFromDate(Timestamp fromDate) {
		this.fromDate = fromDate;
	}

	public Timestamp getToDate() {
		return toDate;
	}

	public void setToDate(Timestamp toDate) {
		this.toDate = toDate;
	}

    public boolean isSalesReport()
    {
        return isSalesReport;
    }

    public void setSalesReport(boolean isSalesReport)
    {
        this.isSalesReport = isSalesReport;
    }

    public boolean isFullDetails()
    {
        return fullDetails;
    }

    public void setFullDetails(boolean fullDetails)
    {
        this.fullDetails = fullDetails;
    }
	
	
}
