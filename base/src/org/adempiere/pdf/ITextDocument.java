/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.               *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/

package org.adempiere.pdf;

import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.PrinterException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.inject.Named;
import javax.inject.Singleton;

import org.adempiere.pdf.viewer.PDFViewerBean;
import org.compiere.util.CLogger;

import com.itextpdf.awt.PdfGraphics2D;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import io.konik.carriage.itext.ITextInvoiceAppender;
import io.konik.carriage.itext.ITextInvoiceExtractor;
import io.konik.harness.AppendParameter;
import io.konik.harness.FileAppender;
import io.konik.harness.FileExtractor;

/* @see https://github.com/adempiere/adempiere/pull/1576
 * 
 * TODO:
 * wo werden com.lowagie.* Packages verwendet?
 *  org.adempiere.pdf.SmjPdfReport
 *  org.compiere.grid.VPanel
 *  org.adempiere.webui.apps.AEnv
 * wo wird iText-2.1.7.jar verwendet?
 *  adempiere.iml
 *  org.adempiere.pos/adempierePOS.iml
 *  ADempiere.launch
 *  serverRoot/Remote Debug Adempiere - serverRoot.launch
 *  JasperReports/build.xml
 *  org.adempierepos/buildPOSClient.xml
 *  tools/build.xml
 *  
 * TODO:
 *   add pom dependences
 *   add http://central.maven.org/maven2/io/konik/itext-carriage/0.8.0/itext-carriage-0.8.0.pom
 *   ...
 */

/**
 * this class will replace the deprecated org.adempiere.pdf.Document which is a container for static methods
 * <p> 
 * Adempiere reports are rendered to awt graphics to be displayed on screen. The main pursose of this class is to 
 * create a pdf from this awt report representation, method #writePDF(...). 
 * This class is a singleton and provides access to invoiceAppender and invoiceExtractor (itext-5)
 * and itext7 - for details @see https://github.com/adempiere/adempiere/issues/1567
 * 
 * @author eugen.hanussek@klst.com https://github.com/homebeaver
 */
@Named
@Singleton
public class ITextDocument implements FileExtractor, FileAppender  {
	
	private static CLogger log	= CLogger.getCLogger(ITextDocument.class);
	
	private static final FileExtractor invoiceExtractor // interface
		= new ITextInvoiceExtractor();
	
	private static final FileAppender invoiceAppender // interface
		= new ITextInvoiceAppender();

	private static final IText7Document itext7 = new IText7Document();
	
	private void writePDF(Pageable pageable, OutputStream output) throws DocumentException, IndexOutOfBoundsException, PrinterException {
		final PageFormat pf = pageable.getPageFormat(0); // of the first page
		log.config("pageable PageFormat.Height (in DTP points) ="+pf.getHeight());
		Rectangle pageSize = new Rectangle((int) pf.getWidth(), (int) pf.getHeight());
		final Document document = new Document(pageSize); 
		// dies ist nicht pdfA:
        final PdfWriter writer = PdfWriter.getInstance(document, output); // throws DocumentException
//		final PdfWriter writer = PdfAWriter.getInstance(document, output, PdfAConformanceLevel.PDF_A_3B);
        writer.setPdfVersion(PdfWriter.PDF_VERSION_1_7);
        
        document.open();
		log.config("PDFXConformance:"+writer.getPDFXConformance());
        PdfContentByte contentByte = writer.getDirectContent();
        
        final float width = (float) pf.getWidth();
        final float height = (float) pf.getHeight();
        for (int page = 0; page < pageable.getNumberOfPages(); page++) {
        	if (page != 0) {
        		document.newPage();
        	}
        	
            PdfTemplate temp = contentByte.createTemplate(width ,height);
            //Graphics2D g2d = temp.createGraphics(width, height); // is @deprecated 
            Graphics2D g2d = new PdfGraphics2D(contentByte, width, height); // without 4th para FontMapper, DefaultFontMapper is used
            temp.setWidth(width);
            temp.setHeight(height);
            pageable.getPrintable(page).print(g2d, pf, page); // throws IndexOutOfBoundsException, PrinterException
            g2d.dispose();
            contentByte.addTemplate(temp, 0, 0); // x,y(0,0) location of this template
        }        
        document.close();
	}
	
	/**
	 * maps to org.adempiere.pdf.IText7Document#merge(OutputStream, InputStream, String)
	 * @param result
	 * @param origin
	 * @param title
	 */
	public void merge(OutputStream result, InputStream origin, String title) {
		itext7.merge(result, origin, title);
	}	
	
	// --------------------------------------------------------------------------
	// same to org.adempiere.pdf.Document :
	
	public File getPDFAsFile(String filename, Pageable pageable) {
        final File result = new File(filename);
        
        try {
        	writePDF(pageable, new FileOutputStream(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
	}

	public byte[] getPDFAsArray(Pageable pageable) {
        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream(10240);
            writePDF(pageable, output);
            return output.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } 

        return null;		
	}
	
    public PDFViewerBean getViewer() {
    	return new PDFViewerBean();
    }
    
    public boolean isValid(Pageable layout) {
    	return true;
    }
    
    public boolean isLicensed() {
    	return true;
    }    

	// --------------------------------------------------------------------------
	// interface implementations:
	
	@Override
	public byte[] extract(InputStream inpStream) {
		return invoiceExtractor.extract(inpStream);
	}

	@Override
	public InputStream extractToStream(InputStream inpStream) {
		return invoiceExtractor.extractToStream(inpStream);
	}

	@Override
	public void append(AppendParameter param) {
		log.info("zugferdVersion:"+param.zugferdVersion()+" zugferdConformanceLevel:"+param.zugferdConformanceLevel());
		invoiceAppender.append(param);
	}

	@Override
	public int getPriority() {
		return invoiceAppender.getPriority();
	}
}
