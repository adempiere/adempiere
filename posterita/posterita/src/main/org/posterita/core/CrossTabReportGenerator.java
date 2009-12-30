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
 * Created on Feb 24, 2006 by praveen
 *
 */
package org.posterita.core;

import java.awt.Color;
import java.util.Iterator;

import org.posterita.exceptions.OperationException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;


public class CrossTabReportGenerator extends PDFReportGenerator
{
        
    protected void writeDocument(Document document) throws OperationException 
    {
        try 
        {                       
            int noOfRows = dataSource.size();
            String longestText = "";
            int columnCount = 0;
            
            Object[] obj = null;
            
            Object[] header = (Object[]) dataSource.get(0);
            columnCount = header.length;
                            
            PdfPTable table = new PdfPTable(columnCount);
            table.setWidthPercentage(100);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.getDefaultCell().setPaddingBottom(5);
            table.getDefaultCell().setPaddingTop(5);   
                   
                    
            //adding the headers
            for(int i = 0; i < columnCount; i++)
            {
                if(i==0)
                {
                    longestText = header[i].toString();                
                    table.addCell(new Paragraph(header[i].toString()));
                }
                else
                {
                    Image img = getTextAsImage(header[i].toString());
                    img.setRotationDegrees(90);
                    img.setAlignment(Image.ALIGN_BOTTOM);
                    
                    PdfPCell cell = new PdfPCell(img);
                    cell.setPadding(4);                
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
                    //cell.setBackgroundColor(new Color(0, 0, 255));
                    
                    table.addCell(cell);
                }
            }
            
            
            
            //adding the data 
            for(int j = 1; j < noOfRows; j++)
            {
                obj = (Object[]) dataSource.get(j);
                
                            
                for(int k = 0; k < columnCount; k++)
                {
                    if(k==0)
                    {
                        String text = obj[0].toString();
                        
                        if(new Chunk(text,HEADER_FONT).getWidthPoint() > new Chunk(longestText,HEADER_FONT).getWidthPoint())
                        {
                            longestText = text;
                        }   
                        
                        
                        Chunk txtck = new Chunk(obj[0].toString(),HEADER_FONT);
                        PdfPCell cell = new PdfPCell(new Paragraph(txtck));
                        
                        if(j == noOfRows - 1)
                        {
                            cell.setBackgroundColor(BaseColor.GRAY);
                        }
                        
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setPaddingBottom(5);
                        cell.setPaddingTop(5);
                        cell.setPaddingLeft(5);
                        
                        table.addCell(cell);
                    }
                    else
                    {
                        Chunk txtck = new Chunk(obj[k].toString(),DATA_FONT);
                        
                        if(k == columnCount-1)
                        {
                            PdfPCell cell = new PdfPCell(new Paragraph(txtck));
                            cell.setBackgroundColor(BaseColor.GRAY);
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            cell.setPaddingBottom(5);
                            cell.setPaddingTop(5);
                            
                            table.addCell(cell);
                        }
                        else
                        {
                            PdfPCell cell = new PdfPCell(new Paragraph(txtck));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            cell.setPaddingBottom(5);
                            cell.setPaddingTop(5); 
                            
                            if(j == noOfRows - 1)
                            {
                                cell.setBackgroundColor(BaseColor.GRAY);                            
                            }
                            
                            table.addCell(cell); 
                        }                    
                    }    
                    
                }
                
            }
            
            //setting table width
            Chunk dataChk = new Chunk("9999",DATA_FONT);         
            Chunk headerChk = new Chunk(longestText,HEADER_FONT);
            
            float dataChkLength = dataChk.getWidthPoint() + 2*CELLPADDING ;
            float headerChkLength = headerChk.getWidthPoint() + 2*CELLPADDING;
            
            float tableWidth = headerChkLength + dataChkLength*columnCount;        
            float actualTableWidth = document.getPageSize().getWidth() - 2*MARGIN;
            
            float columnWidth = dataChkLength;
            
            if(tableWidth < actualTableWidth)
            {
                columnWidth = (actualTableWidth - headerChkLength)/(columnCount - 1);               
            }
            
            
            float[] widths = new float[columnCount];
            widths[0] = headerChkLength + 2*CELLPADDING;
            
            for(int i=1; i<columnCount; i++)
            {
                widths[i] = columnWidth;
            }
            
            table.setWidths(widths);
            
            //writing the table		
            document.add(table);
        } 
        catch (DocumentException e) 
        {
            throw new OperationException(e);
        } 	
		       
    }
    
    protected Rectangle getDocumentDimension()
    {
        Document document = new Document(PAGE_SIZE,MARGIN,MARGIN,MARGIN,MARGIN);
        
        String longestText ="";
        int columnCount = 0;
        Object[] obj = null;
        
        //Getting column count
        obj = (Object[]) dataSource.get(0);
        columnCount = obj.length;
        
        //Getting longest text
        Iterator iter = dataSource.iterator();
        
        while(iter.hasNext())
        {
            obj = (Object[]) iter.next();
            
            String header = obj[0].toString();
            
            if(header.length() > longestText.length())
            {
                longestText = header;
            }
        }
        
        
        //setting the table width
        Chunk dataChk = new Chunk("9999",DATA_FONT);         
        Chunk headerChk = new Chunk(longestText,HEADER_FONT);
        
        float dataChkLength = dataChk.getWidthPoint() + 2*CELLPADDING ;
        float headerChkLength = headerChk.getWidthPoint() + 2*CELLPADDING;
        
        float tableWidth = headerChkLength + dataChkLength*columnCount;
        
        float actualTableWidth = document.getPageSize().getWidth() - 2*MARGIN;
        //float actualTableHeight = document.getPageSize().height() - 2*MARGIN;
        
        //if the table size is greater than that of the page we should 
        //scale the page
                
        if(tableWidth > actualTableWidth)
        {
            float documentWidth = document.getPageSize().getWidth();
            float documentHeight = document.getPageSize().getHeight();
            
            float newDocumentWidth = tableWidth + 2*MARGIN;            
            float newDocumentHeight = (documentHeight * newDocumentWidth)/documentWidth;             
            
            return new Rectangle(newDocumentWidth,newDocumentHeight);
        }
        
        return document.getPageSize();
    }    
    
}
