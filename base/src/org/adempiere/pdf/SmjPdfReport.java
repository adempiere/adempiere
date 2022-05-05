package org.adempiere.pdf;


import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;

import org.adempiere.util.StringUtils;
import org.compiere.model.MImage;
import org.compiere.model.ReportTO;
import org.compiere.report.MReportColumn;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;


/**
 * @version <li>SmartJSP: SmjPdfReport.java, 2011/03/01
 *          <ul TYPE ="circle">
 *          <li>Crea el PDF de la Tabla T_Report teniendo en cuenta las
 *          prametrizaciones personalizadas
 *          <li>Create PDF Report from T_Report Table. It takes custom settings
 *          for report
 *          </ul>
 * @author Freddy Rodriguez - "SmartJSP" - http://www.smartjsp.com/
 * //SMJReport --> Clase nueva para el PDF
 * 
 * Goodwill Change Log
 * - BF: file not saved in temporary folder
 * - Change some hard text to English
 * - Fix Logger point to wrong class
 * - Change font size onEndPage and onCloseDocument
 * - Change PDF author to clientName
 * - Fix Rounding is Necessary
 */
public class SmjPdfReport extends PdfPageEventHelper {

	/** Logger */
	public CLogger log = CLogger.getCLogger(SmjPdfReport.class);
	private ByteArrayOutputStream baosPDF;
	private Font titleFont = new Font(FontFamily.HELVETICA, 15, Font.BOLDITALIC);
	private Font titleTableFont = new Font(FontFamily.HELVETICA, 12, Font.BOLDITALIC);
	private Font catFont = new Font(FontFamily.HELVETICA, 12, Font.BOLD);
	private Font subFont = new Font(FontFamily.HELVETICA, 10, Font.NORMAL);
	private int cols = 0;
	private LinkedList<ReportTO> data = null;
	private com.itextpdf.text.Document document = null;
	private PdfWriter writer = null;
	private PdfPTable table = null;
	private BaseFont helv;
	private PdfTemplate total;

	/**
	 * genera el PDF en un ByteArrayOutputStream ** Generate PDF Report into
	 * ByteArrayOutputStream
	 * 
	 * @return ByteArrayOutputStream
	 */
	public ByteArrayOutputStream generate(LinkedList<ReportTO> dataReport,
			String nameTrx, String[] generalTitle, String clientName,
			String clientNIT, String periodName, String currencyName,
			MReportColumn[] m_columns, String codeFont, String city, Integer logoId) {
		baosPDF = new ByteArrayOutputStream();
		data = dataReport;
		String fontPar[] = codeFont.split("-");
		Integer lFont = Integer.parseInt(fontPar[2]);
		titleFont = FontFactory.getFont(fontPar[0], lFont + 5, Font.BOLDITALIC);
		titleTableFont = FontFactory.getFont(fontPar[0], lFont + 2,
				Font.BOLDITALIC);
		catFont = FontFactory.getFont(fontPar[0], lFont + 2, Font.BOLD);
		subFont = FontFactory.getFont(fontPar[0], lFont, Font.NORMAL);
		try {
			document = new com.itextpdf.text.Document(PageSize.LETTER, 20, 20, 20, 40);// izq-der-arrib
			writer = PdfWriter.getInstance(document, baosPDF);
			document.open();
			// metadata del documento
			document.addTitle(generalTitle[0]);
			document.addAuthor("SmartJSP S.A.S.");
			document.addCreator("SmartJSP S.A.S.");
			onOpenDocument(writer, document);
			onEndPage(writer, document);

			// //////////////////////////////////////////////////////////////////////////////////////
			// agrega el logo
			// add logo
			java.awt.Image img;
			if (logoId > 0) {
				MImage mimage = MImage.get(Env.getCtx(), logoId);
				byte[] imageData = mimage.getData();
				img = Toolkit.getDefaultToolkit().createImage(imageData);
				Image logo = Image.getInstance(img, null);
				logo.scaleToFit(100, 30);
				document.add(logo);
			}
			// Titulo General - general Title
			Paragraph genTitle = new Paragraph(dataNull(generalTitle[0]).toUpperCase(), titleFont);
			genTitle.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(genTitle);
			// empresa - Company
			Paragraph clitName = new Paragraph(dataNull(clientName).toUpperCase(), titleFont);
			clitName.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(clitName);
			// Ciudad - City
			Paragraph cliCity = new Paragraph(dataNull(city).toUpperCase(),	titleFont);
			cliCity.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(cliCity);
			// NIT
			Paragraph cliNIT = new Paragraph(dataNull(clientNIT).toUpperCase(),	titleFont);
			cliNIT.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(cliNIT);
			// periodo - Period
			String pn = "";
			if (generalTitle[1]!=null && generalTitle[1].length()>0){
				pn = generalTitle[1]+" "+periodName;
			}else{
				pn = periodName;
			}
			if (generalTitle[2]!=null && generalTitle[2].length()>0){
				pn = pn+" "+generalTitle[2];
			}
			Paragraph perName = new Paragraph(dataNull(pn).toUpperCase(), titleTableFont);
			perName.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(perName);
			// tipo moneda - currency
			Paragraph currency = new Paragraph(dataNull(currencyName),titleTableFont);
			currency.setAlignment(Paragraph.ALIGN_CENTER);
			addEmptyLine(currency, 2);
			document.add(currency);
			cols = m_columns.length + 2;
			float[] columnWidths = new float[cols];
			columnWidths[0] = 1f;
			columnWidths[1] = 3f;
			for (int i = 2; i < cols; i++) {
				columnWidths[i] = 1f;
			}
			table = new PdfPTable(columnWidths);

			// //Titulos de la tabla - Table titles
			// Nombre - name
			PdfPCell cellTitle = new PdfPCell(new Paragraph(Msg.translate(Env.getCtx(), "name").toUpperCase(), catFont));
			cellTitle.setHorizontalAlignment(Paragraph.ALIGN_RIGHT);
			cellTitle.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(cellTitle);
			// Desripcion - description
			cellTitle = new PdfPCell(new Paragraph(Msg.translate(Env.getCtx(), "description").toUpperCase(), catFont));
			cellTitle.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
			cellTitle.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(cellTitle);
			// columnas de valores - Value Columns
			for (MReportColumn mcol:m_columns){
				String colName = mcol.getName();
				cellTitle = new PdfPCell(new Paragraph(colName.toUpperCase(), catFont));
				cellTitle.setHorizontalAlignment(Paragraph.ALIGN_RIGHT);
				cellTitle.setBackgroundColor(BaseColor.LIGHT_GRAY);
				table.addCell(cellTitle);
			}//for columnas

			// TABLA DEL REPORTE - REPORT TABLE
			reportTable();
			document.add(table);

			// funciones que ponen el pie del porte - put footer
			onEndPage(writer, document);
			onCloseDocument(writer, document);
			document.close();
		} catch (Exception e) {
			System.out
					.println("SMpdfReport(generar)ERROR:: al crear el documento PDF");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return baosPDF;
	}// generar


	/**
	 * coloca la tabla en el reporte ** Put table in report
	 */
	public void reportTable() {
		PdfPCell tableCell;
		Iterator<ReportTO> itRep = data.iterator();
		while (itRep.hasNext()) {
			ReportTO rpt = itRep.next();
			if (rpt.getReportlinestyle() != null	&& rpt.getReportlinestyle().equals("T")) {
				// Coloca titulo - put title
				PdfPCell title = new PdfPCell(new Paragraph(
						dataNull(rpt.getDescription()), titleTableFont));
				title.setColspan(cols);
				title.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
				title.setBorder(0);
				table.addCell(title);
			} else if (rpt.getReportlinestyle() != null && rpt.getReportlinestyle().equals("L")) {
				// coloca linea en el reporte - Put under line in the report
				PdfPCell line = new PdfPCell(new Paragraph("", subFont));
				line.setColspan(cols);
				line.setBorderWidthLeft(0);
				line.setBorderWidthRight(0);
				line.setBorderWidthTop(0);
				line.setBorderColorBottom(BaseColor.BLACK);
				table.addCell(line);
			} else if (rpt.getReportlinestyle() != null	&& rpt.getReportlinestyle().equals("X")) {
				// coloca linea de total - Put total line
				 simpleLine();
			} else if (rpt.getReportlinestyle() != null	&& rpt.getReportlinestyle().equals("Z")) {
				// coloca linea doble de total - Put total line doble
				for(int j=0; j< 2;j++){
				 simpleLine();
				}
			} else if (rpt.getReportlinestyle() != null && rpt.getReportlinestyle().equals("D")) {
				// coloca liena de descripcion - put description line
				tableCell = new PdfPCell(new Phrase(""));
				tableCell.setBorder(0);
				table.addCell(tableCell);
				tableCell = new PdfPCell(new Phrase(""));
				tableCell.setBorderWidthLeft(0);
				tableCell.setBorderWidthRight(0);
				tableCell.setBorderWidthTop(0);
				tableCell.setBorderColorBottom(BaseColor.BLACK);
				table.addCell(tableCell);
				for (int i = 0; i < (cols - 2); i++) {
					tableCell = new PdfPCell(new Phrase(""));
					tableCell.setBorder(0);
					table.addCell(tableCell);
				}// for
			} else if (rpt.getReportlinestyle() != null && rpt.getReportlinestyle().equals("S")) {
				// coloca linea en blanco - put empty line
				PdfPCell line = new PdfPCell(new Paragraph("         "));
				line.setColspan(cols);
				line.setBorder(0);
				table.addCell(line);
			} else if (rpt.getTablevel() != null && rpt.getTablevel() > 0) {
				// coloca espacios a la izquierda para simular jeraquia - put
				// left spaces to simulate hierarchy
				String jerarchy = "";
				for (int i = 1; i <= rpt.getTablevel(); i++) {
					jerarchy = jerarchy + "   ";
				}
				PdfPCell line = new PdfPCell(new Paragraph(jerarchy
						+ dataNull(rpt.getDescription()), catFont));
				line.setColspan(cols);
				line.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
				line.setBorder(0);
				table.addCell(line);
			} else {
				if (rpt.getDescription() != null) {
					// nombre - name
					tableCell = new PdfPCell(new Phrase(
							dataNull(rpt.getName()), subFont));
					tableCell.setBorder(0);
					tableCell.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
					table.addCell(tableCell);
					// descripcion - description
					tableCell = new PdfPCell(new Phrase(
							dataNull(rpt.getDescription()), subFont));
					tableCell.setBorder(0);
					table.addCell(tableCell);
					// columnas 0 a 20 - cols 0 to 20
					if (cols >= 3) {
						tableCell = new PdfPCell(new Phrase(formatValue(rpt.getCol_0()), subFont));
						tableCell.setBorder(0);
						tableCell.setHorizontalAlignment(Paragraph.ALIGN_RIGHT);
						table.addCell(tableCell);
					}
					if (cols >= 4) {
						tableCell = new PdfPCell(new Phrase(formatValue(rpt.getCol_1()), subFont));
						tableCell.setBorder(0);
						tableCell.setHorizontalAlignment(Paragraph.ALIGN_RIGHT);
						table.addCell(tableCell);
					}
					if (cols >= 5) {
						tableCell = new PdfPCell(new Phrase(formatValue(rpt.getCol_2()), subFont));
						tableCell.setBorder(0);
						tableCell.setHorizontalAlignment(Paragraph.ALIGN_RIGHT);
						table.addCell(tableCell);
					}
					if (cols >= 6) {
						tableCell = new PdfPCell(new Phrase(formatValue(rpt.getCol_3()), subFont));
						tableCell.setBorder(0);
						tableCell.setHorizontalAlignment(Paragraph.ALIGN_RIGHT);
						table.addCell(tableCell);
					}
					if (cols >= 7) {
						tableCell = new PdfPCell(new Phrase(formatValue(rpt.getCol_4()), subFont));
						tableCell.setBorder(0);
						tableCell.setHorizontalAlignment(Paragraph.ALIGN_RIGHT);
						table.addCell(tableCell);
					}
					if (cols >= 8) {
						tableCell = new PdfPCell(new Phrase(formatValue(rpt.getCol_5()), subFont));
						tableCell.setBorder(0);
						tableCell.setHorizontalAlignment(Paragraph.ALIGN_RIGHT);
						table.addCell(tableCell);
					}
					if (cols >= 9) {
						tableCell = new PdfPCell(new Phrase(formatValue(rpt.getCol_6()), subFont));
						tableCell.setBorder(0);
						tableCell.setHorizontalAlignment(Paragraph.ALIGN_RIGHT);
						table.addCell(tableCell);
					}
					if (cols >= 10) {
						tableCell = new PdfPCell(new Phrase(formatValue(rpt.getCol_7()), subFont));
						tableCell.setBorder(0);
						tableCell.setHorizontalAlignment(Paragraph.ALIGN_RIGHT);
						table.addCell(tableCell);
					}
					if (cols >= 11) {
						tableCell = new PdfPCell(new Phrase(formatValue(rpt.getCol_8()), subFont));
						tableCell.setBorder(0);
						tableCell.setHorizontalAlignment(Paragraph.ALIGN_RIGHT);
						table.addCell(tableCell);
					}
					if (cols >= 12) {
						tableCell = new PdfPCell(new Phrase(formatValue(rpt.getCol_9()), subFont));
						tableCell.setBorder(0);
						tableCell.setHorizontalAlignment(Paragraph.ALIGN_RIGHT);
						table.addCell(tableCell);
					}
					if (cols >= 13) {
						tableCell = new PdfPCell(new Phrase(formatValue(rpt.getCol_10()), subFont));
						tableCell.setBorder(0);
						tableCell.setHorizontalAlignment(Paragraph.ALIGN_RIGHT);
						table.addCell(tableCell);
					}
					if (cols >= 14) {
						tableCell = new PdfPCell(new Phrase(formatValue(rpt.getCol_11()), subFont));
						tableCell.setBorder(0);
						tableCell.setHorizontalAlignment(Paragraph.ALIGN_RIGHT);
						table.addCell(tableCell);
					}
					if (cols >= 15) {
						tableCell = new PdfPCell(new Phrase(formatValue(rpt.getCol_12()), subFont));
						tableCell.setBorder(0);
						tableCell.setHorizontalAlignment(Paragraph.ALIGN_RIGHT);
						table.addCell(tableCell);
					}
					if (cols >= 16) {
						tableCell = new PdfPCell(new Phrase(formatValue(rpt.getCol_13()), subFont));
						tableCell.setBorder(0);
						tableCell.setHorizontalAlignment(Paragraph.ALIGN_RIGHT);
						table.addCell(tableCell);
					}
					if (cols >= 17) {
						tableCell = new PdfPCell(new Phrase(formatValue(rpt.getCol_14()), subFont));
						tableCell.setBorder(0);
						tableCell.setHorizontalAlignment(Paragraph.ALIGN_RIGHT);
						table.addCell(tableCell);
					}
					if (cols >= 18) {
						tableCell = new PdfPCell(new Phrase(formatValue(rpt.getCol_15()), subFont));
						tableCell.setBorder(0);
						tableCell.setHorizontalAlignment(Paragraph.ALIGN_RIGHT);
						table.addCell(tableCell);
					}
					if (cols >= 19) {
						tableCell = new PdfPCell(new Phrase(formatValue(rpt.getCol_16()), subFont));
						tableCell.setBorder(0);
						tableCell.setHorizontalAlignment(Paragraph.ALIGN_RIGHT);
						table.addCell(tableCell);
					}
					if (cols >= 20) {
						tableCell = new PdfPCell(new Phrase(formatValue(rpt.getCol_17()), subFont));
						tableCell.setBorder(0);
						tableCell.setHorizontalAlignment(Paragraph.ALIGN_RIGHT);
						table.addCell(tableCell);
					}
					if (cols >= 21) {
						tableCell = new PdfPCell(new Phrase(formatValue(rpt.getCol_18()), subFont));
						tableCell.setBorder(0);
						tableCell.setHorizontalAlignment(Paragraph.ALIGN_RIGHT);
						table.addCell(tableCell);
					}
					if (cols >= 22) {
						tableCell = new PdfPCell(new Phrase(formatValue(rpt.getCol_19()), subFont));
						tableCell.setBorder(0);
						tableCell.setHorizontalAlignment(Paragraph.ALIGN_RIGHT);
						table.addCell(tableCell);
					}
					if (cols >= 23) {
						tableCell = new PdfPCell(new Phrase(formatValue(rpt.getCol_20()), subFont));
						tableCell.setBorder(0);
						tableCell.setHorizontalAlignment(Paragraph.ALIGN_RIGHT);
						table.addCell(tableCell);
					}
				}// if description NO null
			}// final else
		}// while
	}// bodyReport

	/**
	 * coloca linea de total - Put total line
	 */
	private void simpleLine(){
		PdfPCell tableCell = new PdfPCell(new Phrase(""));
		tableCell.setBorder(0);
		table.addCell(tableCell);
		tableCell = new PdfPCell(new Phrase(""));
		tableCell.setBorder(0);
		table.addCell(tableCell);
		for (int i = 0; i < (cols - 2); i++) {
			tableCell = new PdfPCell(new Phrase(""));
			tableCell.setBorderWidthLeft(0);
			tableCell.setBorderWidthRight(0);
			tableCell.setBorderWidthTop(0);
			tableCell.setBorderColorBottom(BaseColor.BLACK);
			table.addCell(tableCell);
			
		}// for
	}//simpleLine
	
	/**
	 * predefine los valores del pie de pagina ** predefined values ​​footer
	 * 
	 * @param writer
	 * @param document
	 */
	public void onOpenDocument(PdfWriter writer, com.itextpdf.text.Document document) {
		total = writer.getDirectContent().createTemplate(100, 100);
		total.setBoundingBox(new Rectangle(-20, -20, 100, 100));
		try {
			helv = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
		} catch (Exception e) {
			throw new ExceptionConverter(e);
		}
	}// onOpenDocument

	/**
	 * define el pie de pagina a colocar en las paginas ** define footer to put
	 * in all pages
	 * 
	 * @param writer
	 * @param document
	 */
	public void onEndPage(PdfWriter writer, com.itextpdf.text.Document document) {
		PdfContentByte cb = writer.getDirectContent();
		cb.saveState();
		Date date = new Date();
		String textLeft = "Page " + writer.getPageNumber() + " of ";
		String textRight = date + "            " + "Page "+ writer.getPageNumber() + " of ";
		float textBase = document.bottom() - 20;
		float textSizeLeft = helv.getWidthPoint(textLeft, 8);
		float textSizeRigth = helv.getWidthPoint(textRight, 8);
		cb.beginText();
		cb.setFontAndSize(helv, 8);
		if ((writer.getPageNumber() % 2) == 1) {
			cb.setTextMatrix(document.left(), textBase);
			cb.showText(textLeft + "            " + date);
			cb.endText();
			cb.addTemplate(total, document.left() + textSizeLeft, textBase);
		} else {
			float adjust = helv.getWidthPoint("", 8);
			cb.setTextMatrix(document.right() - textSizeRigth - adjust,
					textBase);
			cb.showText(textRight);
			cb.endText();
			cb.addTemplate(total, document.right() - adjust, textBase);
		}
		cb.restoreState();
	}

	/**
	 * coloca la pagina total en el pie de pagina ** put total number page in footer
	 * 
	 * @param writer
	 * @param document
	 */
	public void onCloseDocument(PdfWriter writer, Document document) {
		total.beginText();
		total.setFontAndSize(helv, 8);
		total.setTextMatrix(0, 0);
		total.showText(String.valueOf(writer.getPageNumber()));
		total.endText();
	}// onCloseDocument

	/**
	 * Crea el archivo PDF a partir de un Byte[] ** Create PDF File from a Byte[]
	 * @param buf
	 * @param generalTitle
	 * @return File
	 */
	public File tofile(byte[] buf, String[] generalTitle) {
		byte[] bytes = buf;
		// Goodwill
		String path = System.getProperty("java.io.tmpdir");
		if ( !(path.endsWith("/") || path.endsWith("\\")) )
			path = path + System.getProperty("file.separator");
		String prefix = StringUtils.makePrefix(generalTitle[0]);		
		if (log.isLoggable(Level.FINE))
		{
			log.log(Level.FINE, "Path="+path + " Prefix="+prefix);
		}
		File file = new File(path+prefix+".pdf");
		try {
			if (file.exists())
				file.delete();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "file", e);
			return null;
		}
		//
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			fos.write(bytes);
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}// tofile

	/**
	 * Crea un salto de linea ** Create a line Break
	 */
	private void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}// addEmptyLine

	/**
	 * Regresa un string libre de datos nulos **
	 * returns String without null
	 * @param data
	 * @return
	 */
	private String dataNull(String data) {
		if (data == null)
			return "";
		else
			return data;
	}// addEmptyLine
	
	/**
	 * regresa el valor formateado a dos caracteres
	 * returns value format 2 characters
	 * @param data
	 * @return String
	 */
	private String formatValue(BigDecimal data) {
		if (data == null)
			return "";
		else{
			DecimalFormat frm = new DecimalFormat("###,###,###,##0.00");
			return frm.format(data.setScale(2, RoundingMode.HALF_UP));	// Goodwill BF Rounding is necessary
		}
	}// formatValue

}// SMpdfReport