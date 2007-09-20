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
 * Created on Mar 1, 2006 by praveen
 *
 */
package org.posterita.core;

import java.awt.Color;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.posterita.exceptions.OperationException;
import org.posterita.lib.PropertiesConstant;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

public abstract class PDFReportGenerator 
{
    protected float CELLPADDING = 4;
    protected float MARGIN = 30f;
    
    //setting default fonts
    protected Font TITLE_FONT = FontFactory.getFont(FontFactory.HELVETICA,18,Font.BOLD,new Color(255,0,0));
    protected Font SUBTITLE_FONT = FontFactory.getFont(FontFactory.HELVETICA,15,Font.BOLD,new Color(255,0,0));
    protected Font HEADER_FONT = FontFactory.getFont(FontFactory.HELVETICA,10,Font.BOLD);
    protected Font DATA_FONT = FontFactory.getFont(FontFactory.HELVETICA,10);    
    
    //setting default pagesize
    protected Rectangle PAGE_SIZE = PageSize.A4.rotate();    
       
    protected Image getTextAsImage(String text) throws OperationException
    {
        try 
        {
            PdfTemplate template = writer.getDirectContent().createTemplate(20, 20);
            //BaseFont bf = BaseFont.createFont("Helvetica", "winansi", false); 
            BaseFont bf = HEADER_FONT.getBaseFont();
            float size = 10;
            float width = bf.getWidthPoint(text, size);
            template.beginText();
            //template.setRGBColorFillF(1, 1, 1);
            template.setFontAndSize(bf, size);
            template.setTextMatrix(0, 2);
            template.showText(text);
            template.endText();
            template.setWidth(width);
            template.setHeight(size + 2);
            // make an Image object from the template
            Image img = Image.getInstance(template);
            img.setAlignment(Image.RIGHT | Image.TEXTWRAP);        
            
            return img;
            
        } 
        catch (Exception e) 
        {
            throw new OperationException(e);
        }
    }
    
        
    public String getPDFReport(Properties ctx,ArrayList dataSource) throws OperationException
    {
        String filename = RandomStringGenerator.randomstring() + ".pdf";        
        String dir = UDIFilePropertiesManager.getProperty().get(ctx, PropertiesConstant.UDI_HOME) + "/config/reports/pdf/";
        String filepath = dir + filename;
        
        //Generatting report
        
        //Initialising the datasource
        this.dataSource = dataSource;
        
        if(dataSource == null)
            throw new OperationException("Cannot generate report! Cause: empty datasource");
        
        Rectangle dimension = getDocumentDimension();        
        Document document = new Document(dimension,MARGIN,MARGIN,MARGIN,MARGIN);
                
        try 
        {
            writer = PdfWriter.getInstance(document, new FileOutputStream(filepath));
            writer.setPageEvent(new PDFReportPageEventHelper());
            
            document.open();
            
            //Add the title part
            Paragraph title = new Paragraph();
            Paragraph subTitle = new Paragraph();
            
                        
            if(reportTitle != null)
            {
                title.add(new Chunk(reportTitle,TITLE_FONT));
                title.setAlignment(Element.ALIGN_CENTER);
                document.add(title); 
            }
            
            if(reportSubTitle != null)
            {
                subTitle.add(new Chunk(reportSubTitle,SUBTITLE_FONT));
                subTitle.setAlignment(Element.ALIGN_CENTER);
                document.add(subTitle);
            }
            
            if((reportSubTitle != null)||(reportTitle != null))
            {
                document.add(new Paragraph(" "));
            }
            
            writeDocument(document);
            
            document.close();    		
    		writer.close();
            
            
            return "config/report/pdf/" + filename;
        } 
        catch (Exception e) 
        {
           throw new OperationException(e);
        }
        
    }
    
    public String getReportSubTitle() 
    {
        return reportSubTitle;
    }
    
    public void setReportSubTitle(String reportSubTitle) 
    {
        this.reportSubTitle = reportSubTitle;
    }
    
    public String getReportTitle() 
    {
        return reportTitle;
    }
    
    public void setReportTitle(String reportTitle) 
    {
        this.reportTitle = reportTitle;
    }

    
    protected abstract void writeDocument(Document document) throws OperationException;     
    protected abstract Rectangle getDocumentDimension();    
    
    protected ArrayList dataSource = null;
    protected PdfWriter writer = null;
    private String reportTitle = null;
    private String reportSubTitle = null; 
    
}
