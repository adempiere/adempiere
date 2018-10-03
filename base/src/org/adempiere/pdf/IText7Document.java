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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

import javax.inject.Named;
import javax.inject.Singleton;

import org.compiere.util.Language;

import com.itextpdf.kernel.pdf.PdfAConformanceLevel;
import com.itextpdf.kernel.pdf.PdfDate;
import com.itextpdf.kernel.pdf.PdfDictionary;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfDocumentInfo;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfOutputIntent;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.PdfViewerPreferences;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import com.itextpdf.kernel.pdf.filespec.PdfFileSpec;
import com.itextpdf.kernel.utils.PdfMerger;
import com.itextpdf.pdfa.PdfADocument;

import io.konik.harness.AppendParameter;
import io.konik.harness.FileAppender;
import io.konik.harness.exception.InvoiceAppendError;

/**
 * implements a FileAppender for itext7.
 * 
 * @see https://github.com/adempiere/adempiere/issues/1567
 * @author eugen.hanussek@klst.com https://github.com/homebeaver
 */
@Named
@Singleton
public class IText7Document implements FileAppender  {

    private final static String ICC_URL = "images/sRGBColorSpaceProfile.icm"; // from pdfa/src/test/resources
    private final static String DEFAULT_TITLE = "PDF/A-3 by klst.com";
    private boolean debugMode = false;
    
    /**
     * sets WriterProperties debugMode
     * @param debugMode
     * 
     * @see com.itextpdf.kernel.pdf.WriterProperties#useDebugMode()
     */
    public void setDebugMode(boolean debugMode) {
    	this.debugMode = debugMode;
    }
    
	private PdfADocument merge7(OutputStream result, final InputStream origin, final String title) throws IOException {
        WriterProperties props = new WriterProperties(); 
        if(debugMode) props.useDebugMode();
        PdfWriter writer = new PdfWriter(result, props);
        
        //Initialize PDFA document with output intent
        PdfOutputIntent intent = new PdfOutputIntent("Custom", "", "http://www.color.org", "sRGB IEC61966-2.1"
        		, org.compiere.Adempiere.class.getResource(ICC_URL).openStream() ); // throws IOException 
        PdfADocument pdf = new PdfADocument(writer, PdfAConformanceLevel.PDF_A_3B, intent);
        // Creates output intent dictionary. Null values are allowed to suppress any key. 
        //   By default output intent subtype is GTS_PDFA1, use setter to change it.

        //Setting some required parameters
        pdf.setTagged();
        Locale locale = Language.getLoginLanguage().getLocale();
        pdf.getCatalog().setLang(new PdfString(locale.toString())); // #AD_Language 
        pdf.getCatalog().setViewerPreferences(new PdfViewerPreferences().setDisplayDocTitle(true));
        PdfDocumentInfo info = pdf.getDocumentInfo();
        info.setTitle(title);

        //Create PdfMerger instance
		PdfMerger merger = new PdfMerger(pdf);
		PdfDocument pdforigin = new PdfDocument(new PdfReader(origin)); // PdfReader throws IOException
		int fromPage = 1;
		merger.merge(pdforigin, fromPage, pdforigin.getNumberOfPages());
		pdforigin.close();
		
        //pdf.close(); do not close, return:
		return pdf;
	}

	/**
	 * This method is used to merge an existing document into a new one with conformance level PDF/A-3B. 
	 * By default, if source document contains tags and outlines, they will be also copied to the destination document.
	 * @param result
	 * @param origin
	 * @param title   @see {@link PdfDocumentInfo#setTitle(String)}
	 */
	public void merge(OutputStream result, final InputStream origin, final String title) { 
        try {
        	PdfADocument pdf = merge7(result, origin, title==null ? DEFAULT_TITLE : title);  
        	pdf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	private void appendInvoiceIntern(AppendParameter appendable, final String description, final String fileName, PdfName mimeType, PdfName aFRelationship) throws IOException {
		byte[] attachmentFile = convertToByteArray(appendable.attachmentFile());
		PdfADocument pdf = merge7(appendable.resultingPdf(), appendable.inputPdf(), DEFAULT_TITLE); 
		//Add attachment
		PdfDictionary parameters = new PdfDictionary();
		parameters.put(PdfName.ModDate, new PdfDate().getPdfObject());
		PdfFileSpec fileSpec = PdfFileSpec.createEmbeddedFileSpec(pdf, attachmentFile
				, description    // @param description         file description 
				, fileName       // @param fileDisplay         actual file name stored in the pdf
				, mimeType       // @param mimeType            mime-type of the file (optional - null)
				, parameters     // @param fileParameter       Pdfdictionary containing fil parameters
				, aFRelationship // @param AFRelationship key value (optional - null)
				);
		pdf.addAssociatedFile(description, fileSpec);
		pdf.close();
	}

	private void appendInvoiceIntern(AppendParameter appendable, final String description, final String fileName) throws IOException {
		appendInvoiceIntern(appendable, description, fileName, null, null);
	}

	private static byte[] convertToByteArray(InputStream is) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		byte[] buffer = new byte[65536];
		try {
			for (int length; (length = is.read(buffer)) != -1;) {
				baos.write(buffer, 0, length);
			}
			is.close();
			baos.close();
		} catch (IOException e) {
			throw new InvoiceAppendError("Was not possible to read Invoice Content stream", e);
		}
		return baos.toByteArray();
	}

	/**
	 * Attach a xml file to PDF (non ZUGFeRD), the AFRelationship is "Data"
	 * 
	 * @param appendable .resultingPdf() The Resulting PDF output Stream.
	 * @param appendable .inputPdf() Input PDF to read from.
	 * @param appendable .attachmentFile() Attachment file.
	 * @throws IOException
	 * 
	 * Use ITextDocument.append method to append a ZUGFeRD-xml file
	 */
	public void appendXml(AppendParameter appendable, final String description, final String fileName, PdfName mimeType, PdfName aFRelationship) {
	      try {
	          appendInvoiceIntern(appendable, description, fileName, PdfName.ApplicationXml, new PdfName("Data"));
	       } catch (IOException e) {
	          throw new InvoiceAppendError("PDF IO Error", e);
	       }
	}

	/**
	 * Attach a file to PDF, no mimeType is defined (any file) and AFRelationshipValue.Unspecified (example data.csv)
	 * 
	 * @param appendable .resultingPdf() The Resulting PDF output Stream.
	 * @param appendable .inputPdf() Input PDF to read from.
	 * @param appendable .attachmentFile() Attachment file.
	 * @param appendable .zugferdVersion() use this String for the file description expl "the csvdata for invoice with number 123"
	 * @param appendable .zugferdConformanceLevel() use this String for the actual file name stored in the pdf expl "INVOICE-123.csv" 
	 * @throws IOException
	 * 
	 * Use ITextDocument.append method to append a ZUGFeRD-xml file
	 */
	@Override
	public void append(AppendParameter appendable) {
	      try {
	          appendInvoiceIntern(appendable, appendable.zugferdVersion(), appendable.zugferdConformanceLevel());
	       } catch (IOException e) {
	          throw new InvoiceAppendError("PDF IO Error", e);
	       }
	}

	@Override
	public int getPriority() {
		return 0;
	}

}
