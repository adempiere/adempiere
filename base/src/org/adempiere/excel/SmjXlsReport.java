package org.adempiere.excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.compiere.model.MImage;
import org.compiere.report.MReportColumn;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.adempiere.util.StringUtils;

import org.compiere.model.ReportTO;

/**
 *  @version <li>SmartJSP: SmjXlsReport.java, 2012/02/15
 *          <ul TYPE ="circle">
 *          <li> Crea el XLS de la Tabla T_Report teniendo en cuenta las
 *          prametrizaciones personalizadas
 *          <li>Create XLS Report from T_Report Table. It takes custom settings
 *          for report
 *          </ul>
 * @author Freddy Rodriguez - "SmartJSP" - http://www.smartjsp.com/
 *
 */
@SuppressWarnings("deprecation")
public class SmjXlsReport {

	/** Logger */
	public CLogger log = CLogger.getCLogger(SmjXlsReport.class);
	private int cols = 0;
	private short endRegion=2;

	public HSSFWorkbook generate(LinkedList<ReportTO> data,
			String generalTitle[], String clientName, String clientNIT,
			String periodName, String currencyName, MReportColumn[] m_columns, 
			String city, Integer logoId) {
		int fila = 0;
		HSSFRow row;
		cols = m_columns.length + 2;
		endRegion = (short) (cols -1);
		try {
			// create workbook
			HSSFWorkbook book = new HSSFWorkbook();
			// crea hoja - create sheet
			HSSFSheet sheet = book.createSheet(StringUtils.makePrefix(generalTitle[0]));	// Goodwill BF: Invalid sheet name
			// crea fuente - Create Font
			HSSFFont font = book.createFont();
			font.setFontHeightInPoints((short) 13);
			font.setFontName(HSSFFont.FONT_ARIAL);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

			// estio celda - cell style
			HSSFCellStyle cellStyle = book.createCellStyle();
			cellStyle.setWrapText(true);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
			cellStyle.setFont(font);
			// //////////////////////////////////////////////////////////////////////////////////////
			// agrega el logo
			// add logo
			if (logoId > 0) {
				MImage mimage = MImage.get(Env.getCtx(), logoId);
				byte[] imageData = mimage.getData();
				HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
				HSSFClientAnchor anchor;
				anchor = new HSSFClientAnchor(100,50,200,255,(short)0,0,(short)1,1);
				anchor.setAnchorType( 2 );
				int pictureIndex = book.addPicture(imageData, HSSFWorkbook.PICTURE_TYPE_PNG );
				patriarch.createPicture(anchor, pictureIndex);
				for (int i=0;i<5;i++)
					row = sheet.createRow(fila++);
			}//if Logo report
			
			// Titulo General - general Title
			row = sheet.createRow(fila++);
			HSSFRichTextString text = new HSSFRichTextString(generalTitle[0]);
			HSSFCell cell = row.createCell((short) 0);
			cell.setCellStyle(cellStyle);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(text);
			Region region = new Region(fila-1,(short)0,fila-1,endRegion);
			sheet.addMergedRegion(region);

			// empresa - Company
			row = sheet.createRow(fila++);
			text = new HSSFRichTextString(clientName);
			cell = row.createCell((short) 0);
			cell.setCellStyle(cellStyle);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(text);
			region = new Region(fila-1,(short)0,fila-1,endRegion);
			sheet.addMergedRegion(region);

			// Ciudad - City
			row = sheet.createRow(fila++);
			text = new HSSFRichTextString(city);
			cell = row.createCell((short) 0);
			cell.setCellStyle(cellStyle);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(text);
			region = new Region(fila-1,(short)0,fila-1,endRegion);
			sheet.addMergedRegion(region);
			
			// NIT
			row = sheet.createRow(fila++);
			text = new HSSFRichTextString(clientNIT);
			cell = row.createCell((short) 0);
			cell.setCellStyle(cellStyle);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(text);
			region = new Region(fila-1,(short)0,fila-1,endRegion);
			sheet.addMergedRegion(region);

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
			row = sheet.createRow(fila++);
			text = new HSSFRichTextString(pn);
			cell = row.createCell((short) 0);
			cell.setCellStyle(cellStyle);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(text);
			region = new Region(fila-1,(short)0,fila-1,endRegion);
			sheet.addMergedRegion(region);

			// tipo moneda - currency
			row = sheet.createRow(fila++);
			text = new HSSFRichTextString(currencyName);
			cell = row.createCell((short) 0);
			cell.setCellStyle(cellStyle);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(text);
			region = new Region(fila-1,(short)0,fila-1,endRegion);
			sheet.addMergedRegion(region);
			row = sheet.createRow(fila++);
			titleTable(book, sheet, fila++, m_columns);

			// llena datos del reporte - fill data report
			reportTable(book, data, sheet, fila);
			return book;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}//try/catch
	}// generate

	/**
	 * Crea la fila de titulos - create title row
	 * @param wb
	 * @param hs
	 * @param fila
	 * @param colsName
	 */
	private void titleTable(HSSFWorkbook book, HSSFSheet sheet, int fila,
			MReportColumn[] m_columns) {
		short col = 0;
		// crea fuente - create font
		HSSFFont font = book.createFont();
		font.setFontHeightInPoints((short) 13);
		font.setFontName(HSSFFont.FONT_ARIAL);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//		font.setColor(HSSFColor.BLUE.index);
		// estio celda - cell style
		HSSFCellStyle cellStyle = book.createCellStyle();
		cellStyle.setWrapText(true);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_JUSTIFY);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
//		cellStyle.setFillPattern(HSSFCellStyle.SPARSE_DOTS);
//		cellStyle.setFillBackgroundColor(HSSFColor.GREY_40_PERCENT.index);
		cellStyle.setFont(font);

		// //Titulos de la tabla - Table titles
		HSSFRow row = sheet.createRow(fila);
		// Nombre - name
		HSSFRichTextString text = new HSSFRichTextString(Msg.translate(
				Env.getCtx(), "name").toUpperCase());
		HSSFCell cell = row.createCell(col++);
		cell.setCellStyle(cellStyle);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(text);

		// Desripcion - description
		text = new HSSFRichTextString(Msg
				.translate(Env.getCtx(), "description").toUpperCase());
		cell = row.createCell(col++);
		cell.setCellStyle(cellStyle);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(text);

		// columnas de valores - Value Columns
		for (MReportColumn mcol:m_columns){
			String colName = mcol.getName();
			text = new HSSFRichTextString(colName.toUpperCase());
			cell = row.createCell(col++);
			cell.setCellStyle(cellStyle);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(text);
		}//for columnas
	}// titleTable

	/**
	 * llena los datos del reporte - fill report data
	 * @param book
	 * @param data
	 * @param sheet
	 * @param fila
	 */
	public void reportTable(HSSFWorkbook book, LinkedList<ReportTO> data,
			HSSFSheet sheet, int fila) {
		HSSFRow row;
		// crea fuente - create font
		HSSFFont font = book.createFont();
		font.setFontHeightInPoints((short) 10);
		font.setFontName(HSSFFont.FONT_ARIAL);
		
		HSSFRichTextString text;
		Iterator<ReportTO> itRep = data.iterator();
		Boolean newRow = false;
		
		sheet.setColumnWidth((short)0, (short)(13*256));
		sheet.setColumnWidth((short)1, (short)(60*256));
		for (int i = 2;i<(cols);i++){
			sheet.setColumnWidth((short)i, (short)(15*256));
		}//for
		// estio celda - cell style
		HSSFCellStyle cellStyle = book.createCellStyle();
		HSSFCellStyle cellStyleD = book.createCellStyle();
		HSSFCellStyle cellStyleN = book.createCellStyle();
		while (itRep.hasNext()) {
			short col = 0;
			ReportTO rpt = itRep.next();
			if (!newRow){
				cellStyle = book.createCellStyle();
				cellStyleD = book.createCellStyle();
				cellStyleN = book.createCellStyle();
			}//if
			newRow = false;
			
			if (rpt.getReportlinestyle() != null && rpt.getReportlinestyle().equals("T")) {
				// Coloca titulo - put title
				row = sheet.createRow(fila++);
				HSSFFont fontT = book.createFont();
				fontT.setFontHeightInPoints((short) 12);
				fontT.setFontName(HSSFFont.FONT_ARIAL);
				fontT.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				HSSFCellStyle cellStyleT = book.createCellStyle();
				cellStyleT.setWrapText(true);
				cellStyleT.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellStyleT.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
				cellStyleT.setFont(fontT);
				Region region = new Region(fila-1,(short)0,fila-1,endRegion);
				sheet.addMergedRegion(region);
				text = new HSSFRichTextString(rpt.getDescription());
				HSSFCell cellT = row.createCell(col);
				cellT.setCellStyle(cellStyleT);
				cellT.setCellValue(text);
				newRow = true;
			} else if (rpt.getReportlinestyle() != null && rpt.getReportlinestyle().equals("L")) {
				// coloca linea en el reporte - Put under line in the report
				cellStyle.setWrapText(true);
				cellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
				cellStyle.setBottomBorderColor((short)8);
				cellStyleD.setWrapText(true);
				cellStyleD.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
				cellStyleD.setBottomBorderColor((short)8);
				cellStyleN.setWrapText(true);
				cellStyleN.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
				cellStyleN.setBottomBorderColor((short)8);
				newRow = true;
			} else if (rpt.getReportlinestyle() != null	&& rpt.getReportlinestyle().equals("X")) {
				// coloca linea de total - Put total line
				cellStyle.setWrapText(true);
				cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
				cellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
				cellStyle.setBottomBorderColor((short)8);
				newRow = true;
			}else if (rpt.getReportlinestyle() != null	&& rpt.getReportlinestyle().equals("Z")) {
				// coloca linea doble de total - Put total line doble
				cellStyle.setWrapText(true);
				cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
				cellStyle.setBorderTop(HSSFCellStyle.BORDER_DOUBLE);
				cellStyle.setBottomBorderColor((short)8);
				//--------------
				row = sheet.createRow(fila++);
				ReportTO rptD = new ReportTO();
				putRow(cellStyle, cellStyleD, cellStyleN, sheet, row, fila, rptD);
				cellStyle = book.createCellStyle();
				newRow = true;
			}else if (rpt.getReportlinestyle() != null && rpt.getReportlinestyle().equals("D")) {
				// coloca liena de descripcion - put description line
				cellStyleD.setWrapText(true);
				cellStyleD.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
				cellStyleD.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
				cellStyleD.setBottomBorderColor((short)8);
				newRow = true;
			}else if (rpt.getReportlinestyle() != null && rpt.getReportlinestyle().equals("S")) {
				// coloca linea en blanco - put empty line
				row = sheet.createRow(fila++);
				newRow = true;
			}else if (rpt.getTablevel() != null && rpt.getTablevel() > 0) {
				// coloca espacios a la izquierda para simular jeraquia - put
				// left spaces to simulate hierarchy
				row = sheet.createRow(fila++);
				String jerarchy = "";
				for (int i = 1; i <= rpt.getTablevel(); i++) {
					jerarchy = jerarchy + "   ";
				}//for
				Region region = new Region(fila-1,(short)0,fila-1,endRegion);
				sheet.addMergedRegion(region);
				text = new HSSFRichTextString(jerarchy+rpt.getDescription());
				HSSFCell cellJ = row.createCell(col);
				cellJ.setCellValue(text);
				newRow = true;
			}else {
				row = sheet.createRow(fila++);
				putRow(cellStyle, cellStyleD, cellStyleN, sheet, row, fila, rpt);
				
			}//else
		}// while itData
	}// reportTable

	/**
	 * pone la linea de informacion en el XLS
	 * put information line into XLS
	 * @param cellStyle
	 * @param cellStyleD
	 * @param cellStyleN
	 * @param sheet
	 * @param row
	 * @param fila
	 * @param rpt
	 */
	private void putRow(HSSFCellStyle cellStyle, HSSFCellStyle cellStyleD, HSSFCellStyle cellStyleN, 
			HSSFSheet sheet, HSSFRow row, int fila, ReportTO rpt){
		HSSFRichTextString text;
		short col = 0;
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		//Nombre - Name
		text = new HSSFRichTextString(rpt.getName());
		HSSFCell cell = row.createCell(col++);
		cell.setCellStyle(cellStyleN);
		cell.setCellValue(text);
		//Descripcion - Description
		text = new HSSFRichTextString(rpt.getDescription());
		cell.setCellStyle(cellStyleD);
		cell = row.createCell(col++);
		cell.setCellValue(text);
		if (cols >= 3) {
			//Col0
			text = new HSSFRichTextString(formatValue(rpt.getCol_0()));
			cell = row.createCell(col++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(text);
		}
		if (cols >= 4) {
			//Col1
			text = new HSSFRichTextString(formatValue(rpt.getCol_1()));
			cell = row.createCell(col++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(text);
		}
		if (cols >= 5) {
			//Col2
			text = new HSSFRichTextString(formatValue(rpt.getCol_2()));
			cell = row.createCell(col++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(text);
		}
		if (cols >= 6) {
			//Col3
			text = new HSSFRichTextString(formatValue(rpt.getCol_3()));
			cell = row.createCell(col++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(text);
		}
		if (cols >= 7) {
			//Col4
			text = new HSSFRichTextString(formatValue(rpt.getCol_4()));
			cell = row.createCell(col++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(text);
		}
		if (cols >= 8) {
			//Col5
			text = new HSSFRichTextString(formatValue(rpt.getCol_5()));
			cell = row.createCell(col++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(text);
		}
		if (cols >= 9) {
			//Col6
			text = new HSSFRichTextString(formatValue(rpt.getCol_6()));
			cell = row.createCell(col++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(text);
		}
		if (cols >= 10) {
			//Col7
			text = new HSSFRichTextString(formatValue(rpt.getCol_7()));
			cell = row.createCell(col++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(text);
		}
		if (cols >= 11) {
			//Col8
			text = new HSSFRichTextString(formatValue(rpt.getCol_8()));
			cell = row.createCell(col++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(text);
		}
		if (cols >= 12) {
			//Col9
			text = new HSSFRichTextString(formatValue(rpt.getCol_9()));
			cell = row.createCell(col++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(text);
		}
		if (cols >= 13) {
			//Col10
			text = new HSSFRichTextString(formatValue(rpt.getCol_10()));
			cell = row.createCell(col++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(text);
		}
		if (cols >= 14) {
			//Col11
			text = new HSSFRichTextString(formatValue(rpt.getCol_11()));
			cell = row.createCell(col++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(text);
		}
		if (cols >= 15) {
			//Col12
			text = new HSSFRichTextString(formatValue(rpt.getCol_12()));
			cell = row.createCell(col++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(text);
		}
		if (cols >= 16) {
			//Col13
			text = new HSSFRichTextString(formatValue(rpt.getCol_13()));
			cell = row.createCell(col++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(text);
		}
		if (cols >= 17) {
			//Col14
			text = new HSSFRichTextString(formatValue(rpt.getCol_14()));
			cell = row.createCell(col++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(text);
		}
		if (cols >= 18) {
			//Col15
			text = new HSSFRichTextString(formatValue(rpt.getCol_15()));
			cell = row.createCell(col++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(text);
		}
		if (cols >= 19) {
			//Col16
			text = new HSSFRichTextString(formatValue(rpt.getCol_16()));
			cell = row.createCell(col++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(text);
		}
		if (cols >= 20) {
			//Col17
			text = new HSSFRichTextString(formatValue(rpt.getCol_17()));
			cell = row.createCell(col++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(text);
		}
		if (cols >= 21) {
			//Col18
			text = new HSSFRichTextString(formatValue(rpt.getCol_18()));
			cell = row.createCell(col++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(text);
		}
		if (cols >= 22) {
			//Col19
			text = new HSSFRichTextString(formatValue(rpt.getCol_19()));
			cell = row.createCell(col++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(text);
		}
		if (cols >= 23) {
			//Col20
			text = new HSSFRichTextString(formatValue(rpt.getCol_20()));
			cell = row.createCell(col++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(text);
		}
	}//putRow
	
	/**
	 * Crea el archivo PDF a partir de un Byte[] ** Create PDF File from a Byte[]
	 * @param wb
	 * @param generalTitle
	 * @return File
	 */
	public File tofile(HSSFWorkbook wb, String[] generalTitle) {

		// Goodwill
		String path = System.getProperty("java.io.tmpdir");
		if ( !(path.endsWith("/") || path.endsWith("\\")) )
			path = path + System.getProperty("file.separator");
		String prefix = StringUtils.makePrefix(generalTitle[0]);
		if (log.isLoggable(Level.FINE))
		{
			log.log(Level.FINE, "Path="+path + " Prefix="+prefix);
		}
		File file = new File(path+prefix+".xls");
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
			// fos.write(bytes);
			wb.write(fos);
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
			return frm.format(data.setScale(2, BigDecimal.ROUND_HALF_UP));	// Goodwill BF Rounding is necessary
		}
	}// formatValue
	
}// SmjXlsReport