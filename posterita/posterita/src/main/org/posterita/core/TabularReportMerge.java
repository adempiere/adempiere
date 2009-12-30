package org.posterita.core;

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

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.apache.ecs.xhtml.div;
import org.apache.ecs.xhtml.table;
import org.apache.ecs.xhtml.td;
import org.apache.ecs.xhtml.th;
import org.apache.ecs.xhtml.tr;
import org.posterita.Constants;
import org.posterita.businesslogic.performanceanalysis.ReportManager;
import org.posterita.exceptions.OperationException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class TabularReportMerge 
{
	private ArrayList<Object[]> reportData = null;
	private table bigTable;
	private div contain;
	private tr bigRow;
	private boolean sortable = false;	
	private String id = null;
	private String[] headerStyle = null;
	private String style = null;
	private String title = null;
	private String subtitle = null;
	private Timestamp fromDate = null;
	private Timestamp toDate = null;
	private int screenWidth = 1024; 
	public TabularReportMerge(ArrayList<Object[]> reportData)
	{
		this.reportData = reportData;
	}

	public TabularReportMerge(String title, int colspan)
	{
		bigTable = new table();
		bigRow = new tr();
		setTitle(title, colspan);
		bigTable.addElement(bigRow);
		contain = new div();
		contain.setStyle("width:" + screenWidth + ";overflow:auto;");
		contain.addElement(bigTable);
	}
	
	public void createReport(ArrayList<Object[]> reportData, int type, String subtitle) throws OperationException
	{
		table tbl = new table();
    	tbl.addAttribute("border","0");
    	tbl.addAttribute("width","100%");        	
    	tbl.addAttribute("class","main");
    	
    	
    	td tdsubtitle = new td("<font class='reportsubtitle'>" + subtitle + "</font>");
    	
    	
    	
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
    		String productName="";
    		for(int i=0;i<columnCount;i++)
        	{
        		td d = new td(data[i]+"");
        		
        		if (i==0)
        		{
        			productName = data[i].toString();        			
        		}
        		if(i==columnCount-1)
        		{
        			double qty = Double.valueOf(data[i].toString());
    				
        			String total = data[i].toString();
        			if (type == Constants.SALES_TYPE)
        			{
        				total = "<a href=\"SalesReportAction.do?action=getMonthlySalesReport&productName="+ productName+
        						"&date1=" + getFromDate() + "&date2=" + getToDate() + "\">" + data[i] + "</a>";
        			}
        			else if (type == Constants.STOCK_TYPE)
        			{
        				total = "<a href=\"SalesReportAction.do?action=getMonthlyStockReport&productName="+ productName+
						"&date1=" + getFromDate() + "&date2=" + getToDate() + "\">" + data[i] + "</a>";
        			}
        			d = new td(total);
        		}
        		d.addAttribute("class",tdStyle);
        		
        		rr.addElement(d);
        	}
    		
    		displaytbl.addElement(rr);
    	}
    	////////////////////////////////////////////////////////////////////////
    	tbl.addElement(new tr().addElement(new td().addElement(displaytbl)));
    	
    	bigRow.addElement(new td().addElement(tbl));
		
	}
	
	public String createPDFReport() throws DocumentException, FileNotFoundException
	{
		String filename = RandomStringGenerator.randomstring() + ".pdf";
        String filepath = ReportManager.getReportPath(filename);
        
        FileOutputStream fos = new FileOutputStream(filepath);  		
		
		Document document = new Document(PageSize.A4);
		PdfWriter pdfWriter = PdfWriter.getInstance(document, fos);
		
		document.open();
		
		Font style1 = new Font(Font.HELVETICA, 16.0f, Font.BOLD);
		Font style2 = new Font(Font.HELVETICA, 12.0f);
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
		for(Object[] row : reportData)
		{
			for(Object data : row)
			{
				PdfPCell cell = new PdfPCell(new Phrase(data.toString()));
				table.addCell(cell);
			}
		}
		
		document.add(table);		
		document.close();		
        
		
		return filename;
	}
	
	public byte[] getPDFData() throws DocumentException
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();		
		
		Document document = new Document(PageSize.A4);
		PdfWriter pdfWriter = PdfWriter.getInstance(document, bos);
		
		document.open();
		
		// Font Syle
		Font style1 = new Font(Font.TIMES_ROMAN, 12.0f, Font.BOLD);
		Font style2 = new Font(Font.TIMES_ROMAN, 8.0f, Font.BOLD);		
		Font style3 = new Font(Font.TIMES_ROMAN, 8.0f);
		Font style4 = new Font(Font.TIMES_ROMAN, 9.0f, Font.BOLD);
		float[] widths = { 2f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f};
		
		// Table header
		PdfPTable mytable = new PdfPTable(7);
		mytable.setTotalWidth(widths);
		mytable.setWidthPercentage(100f);
        PdfPCell header = new PdfPCell(new Paragraph(new Chunk(title, style1) + "\n" + new Chunk(subtitle, style2) + "\n", style1));
        header.setColspan(7);
        header.setHorizontalAlignment(Element.ALIGN_CENTER);
        header.setBackgroundColor(BaseColor.BLACK);
        mytable.addCell(header);
        
        // Table sub header        
        PdfPCell subheader = new PdfPCell();
        subheader = new PdfPCell(new Paragraph(""));
        mytable.addCell(subheader);
        
        subheader = new PdfPCell(new Paragraph("QUANTITY", style2));
        subheader.setColspan(2);
        subheader.setHorizontalAlignment(Element.ALIGN_CENTER);
        mytable.addCell(subheader);
        
        subheader = new PdfPCell(new Paragraph("VALUE (Excl. VAT)", style2));
        subheader.setColspan(2);
        subheader.setHorizontalAlignment(Element.ALIGN_CENTER);
        mytable.addCell(subheader); 
        
        subheader = new PdfPCell(new Paragraph("DISCREPANCY", style2));
        subheader.setColspan(2);
        subheader.setHorizontalAlignment(Element.ALIGN_CENTER);
        mytable.addCell(subheader);
        
        // Rows Header
        PdfPCell rowHeader = new PdfPCell(new Paragraph("PRODUCT NAME",style4));
        rowHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
        mytable.addCell(rowHeader);
        
        rowHeader = new PdfPCell(new Paragraph("BOOK", style4));
        rowHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
        mytable.addCell(rowHeader);
        
        rowHeader = new PdfPCell(new Paragraph("COUNT", style4));
        rowHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
        mytable.addCell(rowHeader);
        
        rowHeader = new PdfPCell(new Paragraph("BOOK", style4));
        rowHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
        mytable.addCell(rowHeader);
        
        rowHeader = new PdfPCell(new Paragraph("COUNT", style4));
        rowHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
        mytable.addCell(rowHeader);
        
        rowHeader = new PdfPCell(new Paragraph("QTY", style4));
        rowHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
        mytable.addCell(rowHeader);
        
        rowHeader = new PdfPCell(new Paragraph("VALUE", style4));
        rowHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
        mytable.addCell(rowHeader);
                
        document.add(mytable);

		
		Object[] headers = reportData.get(0);
		
		PdfPTable table = new PdfPTable(headers.length);
		
		table.setTotalWidth(widths);
		table.setWidthPercentage(100f);
		for(Object[] row : reportData)
		{
			
			for(Object data : row)
			{
				PdfPCell dataCell = new PdfPCell(new Phrase(data.toString(), style3));
				table.addCell(dataCell);
			}
		}
		
		document.add(table);		
		document.close();
		
		return bos.toByteArray();
	}
	
	/**
	 * Get Selling Items Data
	 * @return
	 * @throws DocumentException
	 * @throws OperationException 
	 */
	public byte[] getSellingItemsData() throws DocumentException, OperationException
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();		
		
		Document document = new Document(PageSize.A4);
		PdfWriter pdfWriter = PdfWriter.getInstance(document, bos);
		
		document.open();
		
		// Font Syle
		Font style1 = new Font(Font.TIMES_ROMAN, 12.0f, Font.BOLD);
		Font style2 = new Font(Font.TIMES_ROMAN, 8.0f, Font.BOLD);		
		Font style3 = new Font(Font.TIMES_ROMAN, 8.0f);
		Font style4 = new Font(Font.TIMES_ROMAN, 9.0f, Font.BOLD);
		Font style5 = new Font(Font.TIMES_ROMAN, 6.0f);
		float[] widths = { 2f, 0.5f, 0.5f, 1.0f};
		
		// Table header
		PdfPTable mytable = new PdfPTable(4);
		mytable.setTotalWidth(widths);
		mytable.setWidthPercentage(100f);
        PdfPCell header = new PdfPCell(new Paragraph(new Chunk(title, style1) + "\n" + new Chunk(subtitle, style2) + "\n", style1));
        header.setColspan(4);
        header.setHorizontalAlignment(Element.ALIGN_CENTER);
        header.setBackgroundColor(BaseColor.BLACK);
        mytable.addCell(header);
        
        //Date Header
        PdfPCell dateHeader = new PdfPCell(new Paragraph("From : " + new Chunk(TimestampConvertor.convertTimeStamp(fromDate, TimestampConvertor.DEFAULT_DATE_PATTERN1), style1), style1));
        dateHeader.setColspan(1);
        dateHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
        dateHeader.setBackgroundColor(BaseColor.GRAY);
        dateHeader.setBorderColor(BaseColor.WHITE);
        mytable.addCell(dateHeader);
        
        dateHeader = new PdfPCell(new Paragraph("To : " + new Chunk(TimestampConvertor.convertTimeStamp(toDate, TimestampConvertor.DEFAULT_DATE_PATTERN1), style1), style1));
        dateHeader.setColspan(3);
        dateHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
        dateHeader.setBackgroundColor(BaseColor.GRAY);
        dateHeader.setBorderColor(BaseColor.WHITE);
        mytable.addCell(dateHeader);
         
        // Info Header
        PdfPCell infoHeader = new PdfPCell(new Paragraph("ALL VALUES ARE EXCLUSIVE OF VAT - SALES FIGURES ARE TAKEN AFTER DISCOUNT", style5));
        infoHeader.setColspan(4);
        infoHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        infoHeader.setBackgroundColor(BaseColor.BLACK);
        mytable.addCell(infoHeader);
        
        // Rows Header
        PdfPCell rowHeader = new PdfPCell(new Paragraph("PRODUCT NAME",style4));
        rowHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
        mytable.addCell(rowHeader);
        
        
        rowHeader = new PdfPCell(new Paragraph("QTY", style4));
        rowHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
        mytable.addCell(rowHeader);
        
        rowHeader = new PdfPCell(new Paragraph("VALUE", style4));
        rowHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
        mytable.addCell(rowHeader);
                
        rowHeader = new PdfPCell(new Paragraph("SUPPLIER", style4));
        rowHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
        mytable.addCell(rowHeader);
        
        document.add(mytable);

		
		Object[] headers = reportData.get(0);
		
		PdfPTable table = new PdfPTable(headers.length);
		
		table.setTotalWidth(widths);
		table.setWidthPercentage(100f);
		for(Object[] row : reportData)
		{
			
			for(Object data : row)
			{
				PdfPCell dataCell = new PdfPCell(new Phrase(data.toString(), style3));
				table.addCell(dataCell);
			}
		}
		
		document.add(table);		
		document.close();
		
		return bos.toByteArray();
	}
	
	/**
	 * Get Stock Enquiry Report Data
	 * @return
	 * @throws DocumentException
	 * @throws OperationException 
	 */
	public byte[] getStockEnquiryData() throws DocumentException, OperationException
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();		
		
		Document document = new Document(PageSize.A4);
		PdfWriter pdfWriter = PdfWriter.getInstance(document, bos);
		
		document.open();
		
		// Font Syle
		Font style1 = new Font(Font.TIMES_ROMAN, 12.0f, Font.BOLD);
		Font style2 = new Font(Font.TIMES_ROMAN, 8.0f, Font.BOLD);		
		Font style3 = new Font(Font.TIMES_ROMAN, 8.0f);
		Font style4 = new Font(Font.TIMES_ROMAN, 7.0f, Font.BOLD);
		Font style5 = new Font(Font.TIMES_ROMAN, 6.0f);
		float[] widths = { 1f, 2f, 0.5f, 0.5f, 0.5f, 0.5f };
		
		// Table header
		PdfPTable mytable = new PdfPTable(6);
		mytable.setTotalWidth(widths);
		mytable.setWidthPercentage(100f);
        PdfPCell header = new PdfPCell(new Paragraph(new Chunk(title, style1) + "\n" + new Chunk(subtitle, style2) + "\n", style1));
        header.setColspan(6);
        header.setHorizontalAlignment(Element.ALIGN_CENTER);
        header.setBackgroundColor(BaseColor.BLACK);
        mytable.addCell(header);
        
        //Date Header
        PdfPCell dateHeader = new PdfPCell(new Paragraph("From : " + new Chunk(TimestampConvertor.convertTimeStamp(fromDate, TimestampConvertor.DEFAULT_DATE_PATTERN1), style1), style1));
        dateHeader.setColspan(2);
        dateHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
        dateHeader.setBackgroundColor(BaseColor.GRAY);
        dateHeader.setBorderColor(BaseColor.WHITE);
        mytable.addCell(dateHeader);
        
        dateHeader = new PdfPCell(new Paragraph("To : " + new Chunk(TimestampConvertor.convertTimeStamp(toDate, TimestampConvertor.DEFAULT_DATE_PATTERN1), style1), style1));
        dateHeader.setColspan(4);
        dateHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
        dateHeader.setBackgroundColor(BaseColor.GRAY);
        dateHeader.setBorderColor(BaseColor.WHITE);
        mytable.addCell(dateHeader);
         
        // Info Header
        PdfPCell infoHeader = new PdfPCell(new Paragraph("ALL VALUES ARE EXCLUSIVE OF VAT & BASED ON PURCHASE PRICES", style5));
        infoHeader.setColspan(6);
        infoHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
        infoHeader.setBackgroundColor(BaseColor.BLACK);
        mytable.addCell(infoHeader);
        
        // Rows Header
        PdfPCell rowHeader = new PdfPCell(new Paragraph("BARCODE",style4));
        rowHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
        mytable.addCell(rowHeader);
        
        rowHeader = new PdfPCell(new Paragraph("PRODUCT NAME", style4));
        rowHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
        mytable.addCell(rowHeader);
        
        
        rowHeader = new PdfPCell(new Paragraph("Opening Stock Qty", style4));
        rowHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
        mytable.addCell(rowHeader);
        
        rowHeader = new PdfPCell(new Paragraph("Opening Stock Value", style4));
        rowHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
        mytable.addCell(rowHeader);
                
        rowHeader = new PdfPCell(new Paragraph("Closing Stock Qty", style4));
        rowHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
        mytable.addCell(rowHeader);
        
        rowHeader = new PdfPCell(new Paragraph("Closing Stock Value", style4));
        rowHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
        mytable.addCell(rowHeader);
        
        document.add(mytable);

		
		Object[] headers = reportData.get(0);
		
		PdfPTable table = new PdfPTable(headers.length);
		
		table.setTotalWidth(widths);
		table.setWidthPercentage(100f);
		for(Object[] row : reportData)
		{
			
			for(Object data : row)
			{
				PdfPCell dataCell = new PdfPCell(new Phrase(data.toString(), style3));
				table.addCell(dataCell);
			}
		}
		
		document.add(table);		
		document.close();
		
		return bos.toByteArray();
	}
	
	public String toString()
	{
		return contain.toString();
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
		return bigTable;
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

	public void setTitle(String title, int colspan) 
	{
		 td tdtitle = new td("<font class='reporttitle'>" + title + "</font>");
		 tdtitle.addAttribute("colspan", colspan+"");
		 tr trow = new tr();
		 bigTable.addElement(
     			new tr().addElement(tdtitle.addAttribute("align","center"))
				);
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
	
	
}

