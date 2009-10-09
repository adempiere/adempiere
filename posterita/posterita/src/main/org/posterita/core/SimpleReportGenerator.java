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
 * Created on Mar 6, 2006 by praveen
 *
 */
package org.posterita.core;

import java.util.Iterator;

import org.posterita.exceptions.OperationException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class SimpleReportGenerator extends PDFReportGenerator
{
    private PdfPTable table = null;
    float[] columnWidth =  null;
    
    public SimpleReportGenerator()
    {
        this.PAGE_SIZE = PageSize.A4.rotate();
    }
    
    protected void writeDocument(Document document) throws OperationException 
    {
        int columnCount = 0;
        
        Object[] obj = null;
        
        Iterator iter = dataSource.iterator();
        
        try 
        {
            if(iter.hasNext())
            {
                obj = (Object[]) iter.next();
                
                columnCount = obj.length;
                
                table = new PdfPTable(columnCount);
                
                                
                for(int i = 0; i < columnCount; i++)
                {
                    Paragraph p = new Paragraph(new Chunk(obj[i].toString(),HEADER_FONT));                    
                    PdfPCell cell = new PdfPCell(p);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);                
                    
                    table.addCell(cell);
                }
                
                //table.endHeaders();
            }
            
            while(iter.hasNext())
            {
                obj = (Object[]) iter.next();
                
                
                for(int i = 0; i < columnCount; i++)
                {
                    Paragraph p = new Paragraph(new Chunk(obj[i].toString(),HEADER_FONT));                    
                    PdfPCell cell = new PdfPCell(p);
                    
                    table.addCell(cell);
                }             
            }
            
            table.setWidths(columnWidth);            
            document.add(table);
        } 
        catch (Exception e) 
        {
           throw new OperationException(e); 
        }
    }

    
    protected Rectangle getDocumentDimension() 
    {
        if(dataSource == null) return PAGE_SIZE;
        
        Iterator iter = dataSource.iterator();        
        
        int columnCount = 0;
        
        Object[] obj = null;
        
        if(iter.hasNext())
        {
            obj = (Object[]) iter.next();
            
            columnCount = obj.length;
            columnWidth = new float[columnCount];
            
            for(int i = 0; i < columnCount; i++)
            {
                columnWidth[i] = new Chunk(obj[i].toString(),HEADER_FONT).getWidthPoint();
            }            
        }
        
        while(iter.hasNext())
        {
            obj = (Object[]) iter.next();
            
            columnCount = obj.length;            
            
            for(int i = 0; i < columnCount; i++)
            {
                if(obj[i] == null)obj[i] = "";
                    
                float dataWidth = new Chunk(obj[i].toString(),DATA_FONT).getWidthPoint();
                
                if(dataWidth > columnWidth[i])columnWidth[i] = dataWidth;
            }             
        }
        
        
        float tableWidth = 0.0f;
        
        for(int j=0; j<columnWidth.length; j++)
        {
            tableWidth += (columnWidth[j] + 2*CELLPADDING);
        }
        
        float actualTableWidth = PAGE_SIZE.getWidth() - 2*MARGIN;
        //float actualTableHeight = PAGE_SIZE.height() - 2*MARGIN;
        
        //if the table size is greater than that of the page we should 
        //scale the page
                
        if(tableWidth > actualTableWidth)
        {
            float documentWidth = PAGE_SIZE.getWidth();
            float documentHeight = PAGE_SIZE.getHeight();
            
            float newDocumentWidth = tableWidth + 2*MARGIN;            
            float newDocumentHeight = (documentHeight * newDocumentWidth)/documentWidth;             
            
            return new Rectangle(newDocumentWidth,newDocumentHeight);
        }
        else
        {
            float scaleFactor = (actualTableWidth * tableWidth)/tableWidth;
            
            for(int k=0; k<columnWidth.length; k++)
            {
                tableWidth *= scaleFactor;
            }
            
            return PAGE_SIZE;
        }        
        
    }

}
