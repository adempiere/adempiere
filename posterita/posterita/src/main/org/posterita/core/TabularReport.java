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

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;

import org.apache.ecs.xhtml.table;
import org.apache.ecs.xhtml.td;
import org.apache.ecs.xhtml.th;
import org.apache.ecs.xhtml.tr;
import org.compiere.model.MOrg;
import org.posterita.businesslogic.OrganisationManager;
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
	private Timestamp fromDate = null;
	private Timestamp toDate = null;

	
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

	
	public void createReport(Properties ctx, int productId) throws OperationException
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
    	int size = reportData.size();
    	
    	
    	while(iter.hasNext())
    	{
    		count++;
    		
    		tdStyle = s[count%2];
    		
    		Object[] data = iter.next();
    		
    		tr rr = new tr();
    		for(int i=0;i<columnCount;i++)
        	{
    			String cellData = "";
    			double qty = 0f;
				if (i!=0)
				{
					qty =  Double.valueOf(data[i].toString());
				}
    			cellData = data[i]+"";
    			MOrg org = null;
    			if (i !=0)    			
    			{
    				String orgName = headers[i].toString();
    				org = OrganisationManager.getOrgByName(ctx, orgName, null);
    			}
    			if (org!=null && qty !=0f)
    			{
    				
	    			if (count>2)
	    			{
		    			cellData = "<a href=\"" + "SalesReportAction.do?action=viewInventoryHistory&orgId="+org.get_ID()+"&productId="+ productId + "&date1="+getFromDate() + "&date2= "+getToDate()+ "\">" + data[i] + "</a>";
	    			}
	    			else
	    			{
	    				if (count == 1)
	    				{
	    					cellData = "<a href=\"" + "SalesReportAction.do?action=viewInventoryMoveHistory&type=1&orgId="+org.get_ID()+"&productId="+ productId + "&date1="+getFromDate() + "&date2= "+getToDate()+ "\">" + data[i] + "</a>";
	    				}
	    				else
	    				{
	    					cellData = "<a href=\"" + "SalesReportAction.do?action=viewInventoryMoveHistory&type=2&orgId="+org.get_ID()+"&productId="+ productId + "&date1="+getFromDate() + "&date2= "+getToDate()+ "\">" + data[i] + "</a>";
	    				}
	    			}
    			}
        		td d = new td(cellData);
        		d.addAttribute("class",tdStyle);
        		
        		rr.addElement(d);
        	}
    		
    		displaytbl.addElement(rr);
    	}
    	////////////////////////////////////////////////////////////////////////
    	tbl.addElement(new tr().addElement(new td().addElement(displaytbl)));
	}
	
	public void createReport(String action, Integer productId) throws OperationException
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
    	    	        
	    int size = reportData.size();
    	while(iter.hasNext())
    	{
    		
    			tdStyle = s[count%2];
    		
	    		Object[] data = iter.next();
	    		tr rr = new tr();
	    		
	    		String date = data[0].toString();
	    		String month = "";
	    		String[] dateSplit = date.split(" ");
	    		
	    		
	    		if (dateSplit != null)
	    		{	
	    			month= dateSplit[0];
	    		}
	    		DateFormatSymbols dfs = new DateFormatSymbols(Locale.ENGLISH);
	    		int mon = 0;
	    		int yr = 0;
	    		
	    		if (count < size-1)
	    		{
	    			yr = Integer.parseInt(dateSplit[1]);
	    		    		
		    		String[] monthName = dfs.getMonths();
		    		for (int i = 0; i<monthName.length; i++)
		    		{
		    			if (monthName[i].equals(month))
		    			{
		    				mon = i;
		    			}
		    		}
	    		}
	    		Calendar cal = Calendar.getInstance();
				cal.set(Calendar.MONTH, mon);
				int monLength = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	    		
	    		for(int i=0;i<columnCount;i++)
	        	{
	    			String cellData = "";
	    				    			
	    			cellData = data[i]+"";
	    			
	    			if (count < size-1)    		
	        		{
	    				double qty = 0f;
	    				if (i!=0)
	    				 {
	    					qty =  Double.valueOf(data[i].toString());
	    				 }
	    				if (i == 1 && !(qty == 0f))
	    				{
	    					cellData = "<a href=\"" + "SalesReportAction.do?action=getSalesDetailsPerProduct&productId= "+ productId +  "&date1= 01/"+(mon+1)+"/" + yr + "&date2=" +monLength+ "/"+(mon+1)+"/" + yr + "\">" + data[i] + "</a>";
	    				}
		    			if (i == 2 && ! (qty == 0f))
		    			{    				
		    				cellData = "<a href=\"" + action + "&productId= "+ productId +  "&date1= 01/"+(mon+1)+"/" + yr + "&date2=" +monLength+ "/"+(mon+1)+"/" + yr + "\">" + data[i] + "</a>";
		    			}
		    			if (i == 3)
		    			{
		    				cellData = "<a href=\"" + "SalesReportAction.do?action=getStockAdjustmentsPerProduct&productId="+ productId +  "&date1= 01/"+(mon+1)+"/" + yr + "&date2=" +monLength+ "/"+(mon+1)+"/" + yr + "\">" + data[i] + "</a>";
		    			}
	        		}
		    					
	    			td d = new td(cellData);
	        		d.addAttribute("class",tdStyle);
	        		
	        		rr.addElement(d);
	        		displaytbl.addElement(rr);
	        	}
	    		count++;
	    		
    		}
    		    	
    	////////////////////////////////////////////////////////////////////////
    	tbl.addElement(new tr().addElement(new td().addElement(displaytbl)));
		
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
        PdfPCell header = new PdfPCell(new Paragraph(getTitle() + "\n" + getSubtitle(), style1));
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
        PdfPCell header = new PdfPCell(new Paragraph(new Chunk(getTitle(), style1) + "\n" + new Chunk(getSubtitle(), style2) + "\n", style1));
        header.setColspan(4);
        header.setHorizontalAlignment(Element.ALIGN_CENTER);
        header.setBackgroundColor(BaseColor.BLACK);
        mytable.addCell(header);
        
        //Date Header
        PdfPCell dateHeader = new PdfPCell(new Paragraph("From : " + new Chunk(TimestampConvertor.convertTimeStampToFyracleDate(fromDate), style1), style1));
        dateHeader.setColspan(1);
        dateHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
        dateHeader.setBackgroundColor(BaseColor.GRAY);
        dateHeader.setBorderColor(BaseColor.WHITE);
        mytable.addCell(dateHeader);
        
        dateHeader = new PdfPCell(new Paragraph("To : " +new Chunk(TimestampConvertor.convertTimeStampToFyracleDate(toDate), style1), style1));
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
        PdfPCell dateHeader = new PdfPCell(new Paragraph("From : " + new Chunk(TimestampConvertor.convertTimeStampToFyracleDate(fromDate), style1), style1));
        dateHeader.setColspan(2);
        dateHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
        dateHeader.setBackgroundColor(BaseColor.GRAY);
        dateHeader.setBorderColor(BaseColor.WHITE);
        mytable.addCell(dateHeader);
        
        dateHeader = new PdfPCell(new Paragraph("To : " + new Chunk(TimestampConvertor.convertTimeStampToFyracleDate(toDate), style1), style1));
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
	
	public byte[] getInventoryData() throws DocumentException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();        
        
        Document document = new Document(PageSize.A4);
        PdfWriter pdfWriter = PdfWriter.getInstance(document, bos);
        
        document.open();
        
        // Font Syle
        Font style1 = new Font(Font.TIMES_ROMAN, 12.0f, Font.BOLD);
        Font style2 = new Font(Font.TIMES_ROMAN, 8.0f, Font.BOLD);      
        Font style3 = new Font(Font.TIMES_ROMAN, 8.0f);
        Font style4 = new Font(Font.TIMES_ROMAN, 8.0f, Font.BOLD);
        float[] widths = { 1f, 1f, 0.55f, 0.55f, 0.55f, 1f, 1f, 1f, 1f};
        
        // Table header
        PdfPTable mytable = new PdfPTable(9);
        mytable.setTotalWidth(widths);
        mytable.setWidthPercentage(100f);
        PdfPCell header = new PdfPCell(new Paragraph(getTitle() + "\n" + getSubtitle(), style1));
        header.setColspan(9);
        header.setHorizontalAlignment(Element.ALIGN_CENTER);
        header.setBackgroundColor(BaseColor.BLACK);
        mytable.addCell(header);
        
        // Table sub header        
        PdfPCell subheader = new PdfPCell();
        subheader = new PdfPCell(new Paragraph(""));
        subheader.setColspan(2);
        mytable.addCell(subheader);
        
        subheader = new PdfPCell(new Paragraph("QUANTITY", style2));
        subheader.setColspan(3);
        subheader.setHorizontalAlignment(Element.ALIGN_CENTER);
        mytable.addCell(subheader);
        
        subheader = new PdfPCell(new Paragraph("VALUE (Excl. VAT)", style2));
        subheader.setColspan(2);
        subheader.setHorizontalAlignment(Element.ALIGN_CENTER);
        mytable.addCell(subheader); 
        
        subheader = new PdfPCell(new Paragraph("DIFFERENCE", style2));
        subheader.setColspan(2);
        subheader.setHorizontalAlignment(Element.ALIGN_CENTER);
        mytable.addCell(subheader);
        
        // Rows Header
        PdfPCell rowHeader = new PdfPCell(new Paragraph("BARCODE",style4));
        rowHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
        mytable.addCell(rowHeader);
        
        rowHeader = new PdfPCell(new Paragraph("PRODUCT", style4));
        rowHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
        mytable.addCell(rowHeader);
        
        
        rowHeader = new PdfPCell(new Paragraph("CSV", style4));
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
                
        rowHeader = new PdfPCell(new Paragraph("QUANTITY", style4));
        rowHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
        mytable.addCell(rowHeader);
        
        rowHeader = new PdfPCell(new Paragraph("VALUE (Excl. VAT)", style4));
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
	
	
}
