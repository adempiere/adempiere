/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2008 SC ARHIPAC SERVICE SRL. All Rights Reserved.            *
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
package org.adempiere.impexp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.compiere.Adempiere;
import org.compiere.model.MSysConfig;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Language;
import org.compiere.util.Msg;
import org.compiere.util.Util;

/**
 * Abstract MS Excel Format (xls) Exporter
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * @author Victor Perez, www.e-evolution.com
 */
public abstract class AbstractExcelExporter
{
	/**
	 * Is the current Row a Function Row
	 * @return true if function row
	 */
	public abstract boolean isFunctionRow();
	
	/**
	 * Get Columns Count
	 * @return number of columns
	 */
	public abstract int getColumnCount();
	
	/**
	 * Get Rows Count
	 * @return number of rows
	 */
	public abstract int getRowCount();
	
	/**
	 * Set current row
	 * @param row row index
	 */
	protected abstract void setCurrentRow(int row);
	
	/**
	 * Check if column is printed (displayed) 
	 * @param col column index
	 * @return true if is visible
	 */
	public abstract boolean isColumnPrinted(int col);

	/**
	 * Get column header name
	 * @param col column index
	 * @return header name
	 */
	public abstract String getHeaderName(int col);
	
	/**
	 * Get column pattern from Print Format Item
	 * @param col
	 * @return
	 */
	public abstract String getFormatPattern(int col);
	
	/**
	 * Get cell display type (see {@link DisplayType})
	 * @param row row index
	 * @param col column index
	 * @return display type
	 */
	public abstract int getDisplayType(int row, int col);
	
	/**
	 * Get cell value
	 * @param row row index
	 * @param col column index
	 * @return cell value
	 */
	public abstract Object getValueAt(int row, int col);
	
	/**
	 * Check if there is a page break on given cell
	 * @param row row index
	 * @param col column index
	 * @return true if there is a page break
	 */
	public abstract boolean isPageBreak(int row, int col);

	/** Logger */
	protected final CLogger log = CLogger.getCLogger(getClass());
	//
	private SXSSFWorkbook workbook;  // Use SXSSFWorkbook specifically for streaming
	private DataFormat dataFormat;
	private Font fontHeader = null;
	private Font fontDefault = null;
	private Font fontFunction = null;  // Cache function row font
	private Language language = null;
	private int sheetCount = 0;
	protected boolean dataIncludeHeader = false;

	public boolean isXLSX = false;
	
	// Performance optimization settings
	private static final int SXSSF_WINDOW_SIZE = 100;  // Keep 100 rows in memory
	private static final int ROW_FLUSH_INTERVAL = 100;  // Flush every 100 rows
	private boolean autoSizeColumns = MSysConfig.getBooleanValue("AUTO_FIT_COLUMNS_ON_EXCEL_EXPORT", false , Env.getAD_Client_ID(Env.getCtx()));  // Make autoSizeColumn optional (default: false for performance)
	private static final int DEFAULT_COLUMN_WIDTH = 4000;  // Default width when not auto-sizing (~15 chars)

	//
	private int columnSplit = 1;
	private int rowSplit = 1;
	/** Styles cache */
	private HashMap<String, CellStyle> styles = new HashMap<String, CellStyle>();

	public AbstractExcelExporter() {
		initWorkbook();
	}
	
	/**
	 * Initialize workbook with optimized settings
	 */
	private void initWorkbook() {
		// Dispose of any existing workbook to free resources
		if (workbook != null) {
			try {
				workbook.dispose();
			} catch (Exception e) {
				// Ignore disposal errors
			}
		}
		// Use SXSSFWorkbook with window size for better memory management
		workbook = new SXSSFWorkbook(SXSSF_WINDOW_SIZE);
		// Set compression for temp files
		workbook.setCompressTempFiles(true);
		dataFormat = workbook.createDataFormat();
		// Clear style cache when creating new workbook
		styles.clear();
		// Reset per-workbook state so a repeated export on the same instance starts clean:
		// cached fonts and the sheet counter belong to the workbook just disposed
		fontHeader = null;
		fontDefault = null;
		fontFunction = null;
		sheetCount = 0;
	}

	protected Properties getCtx() {
		return Env.getCtx();
	}

	protected void setFreezePane(int colSplit, int rowSplit) {
		columnSplit = colSplit;
		rowSplit = rowSplit;
	}

	private String fixString(String str)
	{
		// ms excel doesn't support UTF8 charset
		return Util.stripDiacritics(str);
	}

	protected Language getLanguage() {
		if (language == null)
			language = Env.getLanguage(getCtx());
		return language;
	}
	
	/**
	 * Set whether to auto size columns (expensive operation)
	 * @param autoSize true to enable auto sizing
	 */
	public void setAutoSizeColumns(boolean autoSize) {
		this.autoSizeColumns = autoSize;
	}

	private Font getFont(boolean isHeader) {
		Font font = null;
		if (isHeader) {
			if (fontHeader == null) {
				fontHeader = workbook.createFont();
				fontHeader.setBold(true);
			}
			font = fontHeader;
		}
		else if (isFunctionRow()) {
			// Cache function row font to avoid creating new instances
			if (fontFunction == null) {
				fontFunction = workbook.createFont();
				fontFunction.setBold(true);
				fontFunction.setItalic(true);
			}
			font = fontFunction;
		}
		else {
			if (fontDefault == null) {
				fontDefault = workbook.createFont();
			}
			font = fontDefault;
		}
		return font;
	}

	/**
	 * Get Excel number format string by given {@link NumberFormat}
	 * @param df number format
	 * @param isHighlightNegativeNumbers highlight negative numbers using RED color
	 * @return number excel format string
	 */
	private String getFormatString(NumberFormat df, boolean isHighlightNegativeNumbers) {
		StringBuffer format = new StringBuffer();
		int integerDigitsMin = df.getMinimumIntegerDigits();
		int integerDigitsMax = df.getMaximumIntegerDigits();
		for (int i = 0; i < integerDigitsMax; i++) {
			if (i < integerDigitsMin)
				format.insert(0, "0");
			else
				format.insert(0, "#");
			if (i == 2) {
				format.insert(0, ",");
			}
		}
		int fractionDigitsMin = df.getMinimumFractionDigits();
		int fractionDigitsMax = df.getMaximumFractionDigits();
		for (int i = 0; i < fractionDigitsMax; i++) {
			if (i == 0)
				format.append(".");
			if (i < fractionDigitsMin)
				format.append("0");
			else
				format.append("#");
		}
		if (isHighlightNegativeNumbers) {
			String f = format.toString();
			format = new StringBuffer(f).append(";[RED]-").append(f);
		}
		//
		if (CLogMgt.isLevelFinest()) log.finest("NumberFormat: "+format);
		return format.toString();

	}

	private CellStyle getStyle(int row, int col) {
		int displayType = getDisplayType(row, col);
		boolean isFunction = isFunctionRow();
		String formatPattern = getFormatPattern(col);
		
		// Build cache key with all style parameters
		// Use StringBuilder for better performance in string concatenation
		StringBuilder keyBuilder = new StringBuilder(32);
		keyBuilder.append("c").append(col)
			.append("-").append(displayType);
		if (isFunction) {
			keyBuilder.append("-fn");
		}
		if (formatPattern != null) {
			keyBuilder.append("-").append(formatPattern.hashCode());
		}
		String key = keyBuilder.toString();
		
		CellStyle cs = styles.get(key);
		if (cs == null) {
			boolean isHighlightNegativeNumbers = true;
			cs = workbook.createCellStyle();
			Font font = getFont(false);
			cs.setFont(font);
			// Border
			cs.setBorderLeft(BorderStyle.THIN);
			cs.setBorderTop(BorderStyle.THIN);
			cs.setBorderRight(BorderStyle.THIN);
			cs.setBorderBottom(BorderStyle.THIN);
			//
			if (DisplayType.isDate(displayType)) {
				//cs.setDataFormat(dataFormat.getFormat("DD.MM.YYYY"));
				if (formatPattern != null) {
					cs.setDataFormat(dataFormat.getFormat(formatPattern));
				} else {
					SimpleDateFormat sdf = DisplayType.getDateFormat(DisplayType.Date, getLanguage());
					cs.setDataFormat(dataFormat.getFormat(sdf.toPattern()));
				}
			}
			else if (DisplayType.isNumeric(displayType)) {
				if (formatPattern != null) {
					cs.setDataFormat(dataFormat.getFormat(formatPattern));
				} else {
					DecimalFormat df = DisplayType.getNumberFormat(displayType, getLanguage());
					String format = getFormatString(df, isHighlightNegativeNumbers);
					cs.setDataFormat(dataFormat.getFormat(format));
				}
			}
			styles.put(key, cs);
		}
		return cs;
	}

	private CellStyle getHeaderStyle(int col)
	{
		String key = "header-"+col;
		CellStyle cs_header = styles.get(key);
		if (cs_header == null) {
			Font font_header = getFont(true);
			cs_header = workbook.createCellStyle();
			cs_header.setFont(font_header);
			cs_header.setBorderLeft(BorderStyle.THIN);
			cs_header.setBorderTop(BorderStyle.THIN);
			cs_header.setBorderRight(BorderStyle.THIN);
			cs_header.setBorderBottom(BorderStyle.THIN);
			//cs_header.setDataFormat(HSSFDataFormat.getBuiltinFormat("text"));
			cs_header.setDataFormat(workbook.createDataFormat().getFormat("text"));
			cs_header.setWrapText(true);
			styles.put(key, cs_header);
		}
		return cs_header;
	}

	private void fixColumnWidth(SXSSFSheet sheet, int lastColumnIndex)
	{
		// Only auto-size if explicitly enabled (expensive operation)
		if (autoSizeColumns) {
			// Auto-size only tracked columns
			// Note: This is still expensive for large sheets
			for (short colnum = 0; colnum < lastColumnIndex; colnum++)
			{
				try {
					// Only auto-size if column was tracked
					sheet.autoSizeColumn(colnum, false); // false = don't merge cells
				} catch (Exception e) {
					// If auto-size fails, set default width
					try {
						sheet.setColumnWidth(colnum, DEFAULT_COLUMN_WIDTH);
					} catch (Exception widthException) {
						// Ignore column width errors
					}
				}
			}
		} else {
			// Set reasonable default widths - much more efficient than auto-sizing
			// This provides consistent column widths without the computational cost
			for (short colnum = 0; colnum < lastColumnIndex; colnum++)
			{
				try {
					sheet.setColumnWidth(colnum, DEFAULT_COLUMN_WIDTH);
				} catch (Exception e) {
					// Ignore column width errors
				}
			}
		}
	}

	private void closeTableSheet(SXSSFSheet prevSheet, String prevSheetName, int colCount)
	{
		if (prevSheet == null)
			return;
		//
		fixColumnWidth(prevSheet, colCount);
		if (columnSplit >= 0 || rowSplit >= 0)
			prevSheet.createFreezePane(columnSplit >= 0 ? columnSplit : 0, rowSplit >= 0 ? rowSplit : 0);
		if (!Util.isEmpty(prevSheetName, true) && sheetCount > 0) {
			int prevSheetIndex = sheetCount - 1;
			try {
				workbook.setSheetName(prevSheetIndex, prevSheetName);
			}
			catch (Exception e) {
				log.log(Level.WARNING, "Error setting sheet "+prevSheetIndex+" name to "+prevSheetName, e);
			}
		}
	}
	private SXSSFSheet createTableSheet()
	{
		SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet();
		formatPage(sheet);
		// Only track columns if auto-sizing is explicitly enabled
		// This is memory intensive and should be avoided for large exports
		if (autoSizeColumns) {
			try {
				// Track only visible columns to reduce memory overhead
				for (int col = 0; col < getColumnCount(); col++) {
					if (isColumnPrinted(col)) {
						sheet.trackColumnForAutoSizing(col);
					}
				}
			} catch (Exception e) {
				log.log(Level.FINE, "Could not track columns for auto-sizing", e);
				// Disable auto-sizing if tracking fails
				autoSizeColumns = false;
			}
		}
		createHeaderFooter(sheet);
		if (!dataIncludeHeader)
			createTableHeader(sheet);

		sheetCount++;
		return sheet;
	}

	private void createTableHeader(Sheet sheet)
	{
		short colnumMax = 0;
		Row row = sheet.createRow(0);
		//	for all columns
		short colnum = 0;
		for (int col = 0; col < getColumnCount(); col++)
		{
			if (colnum > colnumMax)
				colnumMax = colnum;
			//
			if (isColumnPrinted(col))
			{
				Cell cell = row.createCell(colnum);
				//	header row
				CellStyle style = getHeaderStyle(col);
				cell.setCellStyle(style);
				String str = fixString(getHeaderName(col));
				cell.setCellValue(sheet.getWorkbook().getCreationHelper().createRichTextString(str));
				colnum++;
			}	//	printed
		}	//	for all columns
//		workbook.setRepeatingRowsAndColumns(sheetCount, 0, 0, 0, 0);
	}

	protected void createHeaderFooter(Sheet sheet)
	{
		// Sheet Header
		Header header = sheet.getHeader();
		//TODO header.setRight(Header.page()+ " / "+Header.numPages());
		header.setRight("Page &P of &N");
		// Sheet Footer
		Footer footer = sheet.getFooter();
		footer.setLeft(Adempiere.ADEMPIERE_R);
		footer.setCenter(Env.getHeader(getCtx(), 0));
		Timestamp now = new Timestamp(System.currentTimeMillis());
		footer.setRight(DisplayType.getDateFormat(DisplayType.DateTime, getLanguage()).format(now));
	}

	protected void formatPage(Sheet sheet)
	{
		sheet.setFitToPage(true);
		// Print Setup
		PrintSetup ps = sheet.getPrintSetup();
		ps.setFitWidth((short)1);
		ps.setNoColor(true);
		ps.setPaperSize(PrintSetup.A4_PAPERSIZE);
		ps.setLandscape(false);
	}

	/**
	 * Export to given stream
	 * @param out
	 * @throws Exception
	 */
	private void export(OutputStream out) throws Exception
	{
		SXSSFSheet sheet = null;
		try {
			sheet = createTableSheet();
			String sheetName = null;
			//
			int totalRows = getRowCount();
			int totalColumns = getColumnCount();
			
			// Pre-calculate column visibility for performance (this is consistent across all rows)
			boolean[] columnPrinted = new boolean[totalColumns];
			int colnumMax = 0;
			
			// Pre-calculate which columns are printed/visible
			for (int col = 0; col < totalColumns; col++) {
				columnPrinted[col] = isColumnPrinted(col);
				if (columnPrinted[col]) {
					colnumMax++;
				}
			}
		
			// Process rows with optimized batching
			int lastFlushedRow = 0;
			for (int rownum = 0, xls_rownum = 1; rownum < totalRows; rownum++, xls_rownum++)
			{
				setCurrentRow(rownum);

				boolean isPageBreak = false;
				Row row = sheet.createRow(xls_rownum);
				//	for all columns
				int colnum = 0;
				for (int col = 0; col < totalColumns; col++)
				{
					// Use cached column visibility
					if (columnPrinted[col])
					{
						Cell cell = row.createCell(colnum);
						
						// line row
						Object cellValue = getValueAt(rownum, col);
						
						// Evaluate display type for each individual cell to handle varying types per row
						int displayType = getDisplayType(rownum, col);
						
						if (cellValue == null) {
							// Leave cell empty
						}
						else if (DisplayType.isDate(displayType)) {
							if (cellValue instanceof Timestamp) {
								cell.setCellValue((Timestamp)cellValue);
							} else {
								// Fallback for unexpected data type
								String value = fixString(cellValue.toString());
								cell.setCellValue(sheet.getWorkbook().getCreationHelper().createRichTextString(value));
							}
						}
						else if (DisplayType.isNumeric(displayType)) {
							if (cellValue instanceof Number) {
								cell.setCellValue(((Number)cellValue).doubleValue());
							} else {
								// Fallback for non-numeric in numeric column
								try {
									cell.setCellValue(Double.parseDouble(cellValue.toString()));
								} catch (NumberFormatException e) {
									cell.setCellValue(sheet.getWorkbook().getCreationHelper().createRichTextString(cellValue.toString()));
								}
							}
						}
						else if (DisplayType.YesNo == displayType) {
							boolean value = false;
							if (cellValue instanceof Boolean)
								value = (Boolean)cellValue;
							else
								value = "Y".equals(cellValue);
							cell.setCellValue(sheet.getWorkbook().getCreationHelper().createRichTextString(
									Msg.getMsg(getLanguage(), value ? "Y" : "N")));
						}
						else {
							// String or other types
							String value = fixString(cellValue.toString());	//	formatted
							cell.setCellValue(sheet.getWorkbook().getCreationHelper().createRichTextString(value));
						}
						//
						CellStyle style = getStyle(rownum, col);
						cell.setCellStyle(style);
						// Page break check
						if (isPageBreak(rownum, col)) {
							isPageBreak = true;
							// Only get string value if we have text content
							if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
								sheetName = fixString(cell.getRichStringCellValue().getString());
							}
						}
						//
						colnum++;
					}	//	printed
				}	//	for all columns
			
				// Periodic row flushing to free memory - more aggressive flushing for large datasets
				if (rownum > 0 && rownum % ROW_FLUSH_INTERVAL == 0 && rownum > lastFlushedRow) {
					try {
						// Calculate rows to flush (keep only window_size rows in memory)
						// This is more memory efficient
						int rowsToKeep = Math.min(SXSSF_WINDOW_SIZE, totalRows - rownum);
						int rowsToFlush = Math.max(0, rownum - rowsToKeep);
						if (rowsToFlush > lastFlushedRow) {
							sheet.flushRows(rowsToFlush - lastFlushedRow);
							lastFlushedRow = rowsToFlush;
						}
					} catch (Exception e) {
						// If flush fails, continue without it
						log.log(Level.FINE, "Could not flush rows at row " + rownum, e);
					}
				}
				//
				// Page Break
				if (isPageBreak) {
					// Flush remaining rows before creating new sheet
					try {
						sheet.flushRows();
					} catch (Exception e) {
						log.log(Level.FINE, "Could not flush before page break", e);
					}
					closeTableSheet(sheet, sheetName, colnumMax);
					sheet = createTableSheet();
					xls_rownum = 0;
					isPageBreak = false;
					lastFlushedRow = 0; // Reset for new sheet
				}
			}	//	for all rows
			
			// Final flush before closing
			try {
				if (sheet != null) {
					sheet.flushRows();
				}
			} catch (Exception e) {
				log.log(Level.FINE, "Could not perform final flush", e);
			}
			
			closeTableSheet(sheet, sheetName, colnumMax);
			//
			workbook.write(out);
			out.close();
		} finally {
			// Clean up resources in finally block to ensure disposal even on errors
			if (workbook != null) {
				try {
					workbook.dispose();
				} catch (Exception e) {
					log.log(Level.FINE, "Error disposing workbook", e);
				}
				workbook = null;
			}
		}
		
		// Workbook Info
		if (CLogMgt.isLevelFine()) {
			log.fine("Export completed - Sheets: " + sheetCount + ", Styles cached: " + styles.size());
		}
	}

	/**
	 * Export to file
	 * @param file
	 * @param exportLanguage reporting language
	 * @throws Exception
	 */
	public void export(File file, Language exportLanguage) throws Exception
	{
		export(file, exportLanguage, true);
	}

	/**
	 * Export to file
	 * @param file
	 * @param exportLanguage reporting language
	 * @param autoOpen auto open file after generated
	 * @throws Exception
	 */
	public void export(File file, Language exportLanguage, boolean autoOpen) throws Exception
	{
		language = exportLanguage;
		// Reinitialize workbook with optimized settings
		initWorkbook();
		if (file == null)
			file = File.createTempFile("Report_", ".xlsx");
		FileOutputStream out = new FileOutputStream(file);
		export(out);
		if (autoOpen && Ini.isClient())
			Env.startBrowser(file.toURI().toString());
	}

	/**
	 * Export to file
	 * @throws Exception
	 */
	public File export() throws Exception
	{
		language = Env.getLanguage(getCtx());
		// Reinitialize workbook with optimized settings
		initWorkbook();
		File file = File.createTempFile("Report_", ".xlsx");
		FileOutputStream out = new FileOutputStream(file);
		export(out);
		return file;
	}
}
