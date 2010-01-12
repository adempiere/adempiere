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

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

/**
 * This class extends the page event handler that displays the 
 * header and footer.
 */
public class PDFReportPageEventHelper extends PdfPageEventHelper
{
    protected PdfPTable table;      
    protected PdfTemplate tpl;
    protected String dateAndTime;
    
    protected static  final Font PAGE_FOOTER_FONT = FontFactory.getFont(FontFactory.HELVETICA,10,Font.BOLD);
    protected static  final float MARGIN = 30f;
    
    public void onOpenDocument(PdfWriter writer, Document document) 
    {
        SimpleDateFormat sdf = new SimpleDateFormat(TimestampConvertor.DEFAULT_DATE_PATTERN1);
        dateAndTime = sdf.format(Calendar.getInstance().getTime());
        
        table = new PdfPTable(2);            
        tpl = writer.getDirectContent().createTemplate(100, 100);
        tpl.setBoundingBox(new Rectangle(-20, -20, 100, 100));     
        
    }    
    
    public void onEndPage(PdfWriter writer, Document document) 
    {
        PdfContentByte cb = writer.getDirectContent();
        cb.saveState();
        // write the headertable
        table.setTotalWidth(document.right() - document.left());
        table.writeSelectedRows(0, -1, document.left(), document.getPageSize().getHeight() - 50, cb);
        // compose the footer
        String text = "Page " + writer.getPageNumber() + " of ";
        float textSize = PAGE_FOOTER_FONT.getBaseFont().getWidthPoint(text, 10);
        float textBase = document.bottom() - 20;
        cb.beginText();
        cb.setFontAndSize(PAGE_FOOTER_FONT.getBaseFont(), 10);
        
        float adjust = PAGE_FOOTER_FONT.getBaseFont().getWidthPoint("0", 10);
        cb.setTextMatrix(document.right() - textSize - adjust, textBase);
        cb.showText(text);
        cb.endText();
        cb.addTemplate(tpl, document.right() - adjust, textBase);
        
        cb.saveState();
        
        text = "Report Generated on : " + dateAndTime;
        
        textSize = PAGE_FOOTER_FONT.getBaseFont().getWidthPoint(text, 10);
        textBase = document.bottom() - 20;
        cb.beginText();
        cb.setFontAndSize(PAGE_FOOTER_FONT.getBaseFont(), 10);
        
        adjust = PAGE_FOOTER_FONT.getBaseFont().getWidthPoint("0", 10);
        cb.setTextMatrix(MARGIN, textBase);
        cb.showText(text);
        cb.endText();        
        
        cb.saveState();
        
    }
    
    public void onCloseDocument(PdfWriter writer, Document document) {
       tpl.beginText();
       tpl.setFontAndSize(PAGE_FOOTER_FONT.getBaseFont(), 10);
       tpl.setTextMatrix(0, 0);
       tpl.showText("" + (writer.getPageNumber() - 1));
       tpl.endText();
    }   

}
