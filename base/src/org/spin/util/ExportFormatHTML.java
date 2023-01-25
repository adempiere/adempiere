/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2015 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpcya.com                                 *
 *****************************************************************************/
package org.spin.util;

import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.legacy.apache.ecs.XhtmlDocument;
import org.adempiere.legacy.apache.ecs.xhtml.a;
import org.adempiere.legacy.apache.ecs.xhtml.img;
import org.adempiere.legacy.apache.ecs.xhtml.link;
import org.adempiere.legacy.apache.ecs.xhtml.script;
import org.adempiere.legacy.apache.ecs.xhtml.table;
import org.adempiere.legacy.apache.ecs.xhtml.td;
import org.adempiere.legacy.apache.ecs.xhtml.th;
import org.adempiere.legacy.apache.ecs.xhtml.tr;
import org.compiere.model.MFactAcct;
import org.compiere.model.MTable;
import org.compiere.print.IHTMLExtension;
import org.compiere.print.MPrintFont;
import org.compiere.print.MPrintFormat;
import org.compiere.print.MPrintFormatItem;
import org.compiere.print.PrintData;
import org.compiere.print.PrintDataElement;
import org.compiere.print.ReportEngine;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;

/**
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/1400">
 * 		@see FR [ 1400 ] Dynamic report export</a>
 */
public class ExportFormatHTML extends AbstractExportFormat {
	
	public ExportFormatHTML(Properties ctx, ReportEngine reportEngine) {
		setCtx(ctx);
		setReportEngine(reportEngine);
	}
	
	/**	Static Logger	*/
	private static CLogger	log	= CLogger.getCLogger (ExportFormatHTML.class);
	
	@Override
	public String getExtension() {
		return "html";
	}

	@Override
	public String getName() {
		return Msg.getMsg(Env.getCtx(), "FileHTML");
	}
	
	@Override
	public boolean exportToFile(File file) {
		if(getReportEngine() == null
				|| getCtx() == null) {
			return false;
		}
		//	
		return createHTML(file, false, null);
	}
	
	/**************************************************************************
	 * 	Create HTML File
	 * 	@param file file
	 *  @param onlyTable if false create complete HTML document
	 *  @param extension optional extension for html output
	 * 	@return true if success
	 */
	public boolean createHTML (File file, boolean onlyTable, IHTMLExtension extension) {
		BufferedWriter buffer = convertFile(file);
		if(buffer == null) {
			return false;
		}
		//	
		return createHTML (buffer, onlyTable, extension);
	}	//	createHTML
	
	/**
	 * 	Write HTML to writer
	 * 	@param writer writer
	 *  @param onlyTable if false create complete HTML document
	 *  @param extension optional extension for html output
	 * 	@return true if success
	 */
	private boolean createHTML (Writer writer, boolean onlyTable, IHTMLExtension extension) {
		try
		{
			MPrintFormat printFormat = getPrintFormat();
			PrintData printData = getPrintData();
			ProcessInfo processInfo = getProcessInfo();
			//	
			String cssPrefix = extension != null ? extension.getClassPrefix() : null;
			ArrayList<Integer> hasAction = new ArrayList<Integer>();
			if (cssPrefix != null && cssPrefix.trim().length() == 0)
				cssPrefix = null;
			
			table table = new table();
			//	Print Writer
			PrintWriter printWriter = new PrintWriter(writer);
			//	
			if (printFormat.getAD_PrintFont_ID() != 0) {
				MPrintFont font = (MPrintFont) printFormat.getAD_PrintFont();
				Font ff = font.getFont();
				String generic = ff.getName();
				if ( generic.equalsIgnoreCase("sansserif"))
					generic = "sans-serif";
				if ( generic.equalsIgnoreCase("monospaced"))
					generic = "monospace";
				if ( generic.equalsIgnoreCase("dialog"))
					generic = "cursive";
				if ( generic.equalsIgnoreCase("dialoginput"))
					generic = "fantasy";
				
				table.setStyle("font-family:" + generic);
			}
			if (cssPrefix != null)
				table.setClass(cssPrefix + "-table");
			if (!onlyTable) {
				XhtmlDocument doc = new XhtmlDocument();
				doc.appendBody(table);
				if (extension!=null && extension.getStyleURL() != null)
				{
					link l = new link(extension.getStyleURL(), "stylesheet", "text/css");
					doc.appendHead(l);					
				}
				if (extension!=null && extension.getScriptURL() != null)
				{
					script jslink = new script();
					jslink.setLanguage("javascript");
					jslink.setSrc(extension.getScriptURL());
					doc.appendHead(jslink);
				}
				printWriter.write(doc.toString().replaceAll("</table>", "").replaceAll("</body>", "").replaceAll("</html>", ""));
			} else {
				printWriter.write(table.toString().replaceAll("</table>", ""));
			}
			//
			//	for all rows (-1 = header row)
			for (int row = -1; row < printData.getRowCount(); row++)
			{
				tr tr = new tr();
				String cssclass = "";
				if (cssPrefix != null && row % 2 == 0)
					cssclass = cssPrefix + "-odd";
				
				if (row != -1)
				{
					printData.setRowIndex(row);					
					if (extension != null)
					{
						extension.extendRowElement(tr, printData);
					}
					if (printData.isFunctionRow())
					{
						cssclass = cssclass + " " + cssPrefix + "-functionrow";
					}
					else if (row < printData.getRowCount() && printData.isFunctionRow(row + 1))
					{
						cssclass = cssclass + " " + cssPrefix + "-lastgrouprow";
					}
				}
				tr.setClass(cssclass);
				//	for all columns
				for (int col = 0; col < printFormat.getItemCount(); col++)
				{
					MPrintFormatItem item = printFormat.getItem(col);
					if (item.isPrinted())
					{
						//	header row
						if (row == -1)
						{
							th th = new th();
							tr.addElement(th);
							th.addElement(Util.maskHTML(item.getPrintName(Env.getLanguage(getCtx()))));
							
							if (item.getExecuteProcess() != null) {
								hasAction.add(item.getAD_PrintFormatItem_ID());
							}
						}
						else
						{
							td td = new td();
							tr.addElement(td);
							String style = "";
							if (item.isFixedWidth() && item.getMaxWidth() > 0)
							{
								// convert to pixels assuming 96dpi
								int pxs = (item.getMaxWidth()*96);
								pxs = pxs / 72;
								style = "min-width:" + pxs + ";max-width:" + pxs + "; overflow: hidden";
							}
							
							if ( item.isHeightOneLine() )
							{
								if (style.length() > 0 )
									style += ";";
								style += "white-space: nowrap;";
							}

							td.setStyle( style );
							
							Object obj = printData.getNode(Integer.valueOf(item.getAD_Column_ID()));
							if (!item.isDisplayed(printData))
								obj = null;

							if (obj == null)
								td.addElement("&nbsp;");
							else if (obj instanceof PrintDataElement)
							{
								PrintDataElement pde = (PrintDataElement) obj;
								String value = pde.getValueDisplay(Env.getLanguage(getCtx()));	//	formatted
								if (pde.getColumnName().endsWith("_ID") && extension != null)
								{
									//link for column
									a href = new a("javascript:void(0)");									
									href.setID(pde.getColumnName() + "_" + row + "_a");									
									td.addElement(href);
									if (item.getColumnName().equals("Record_ID"))
									{
										img image = new img("/webui/images/dark/Find16.png");
										image.setAlign("middle");
										href.addElement(image);
										td.setStyle("text-align:center");
									}
									else
										href.addElement(Util.maskHTML(value));
									if (cssPrefix != null)
										href.setClass(cssPrefix + "-href");
									
//									if (item.getColumnName().equals("Record_ID")
//									&& (m_printData.getTableName().endsWith("Fact_Acct") || m_printData.getTableName().endsWith("TrialBalance")))
									if (item.getColumnName().equals(MFactAcct.COLUMNNAME_Record_ID) || item.getColumnName().equals(MFactAcct.COLUMNNAME_Line_ID) )
									{
										String columnName = item.getColumnName();
										String columnValue = pde.getValueAsString();
										boolean isZoom = false;

										Object objPDE = printData.getNode("AD_Table_ID");
										if (objPDE != null)
										{
											MTable mTable = null;
											int AD_Table_ID = 0;
											if (objPDE instanceof PrintDataElement) {												
												PrintDataElement pdElement = (PrintDataElement) objPDE;
												//MTable mTable = new MTable(getCtx(), Integer.parseInt(pdElement.getValueAsString()), null);
												AD_Table_ID =Integer.parseInt(pdElement.getValueAsString());
											} else {
												AD_Table_ID =Integer.parseInt(objPDE.toString());
											}
											mTable = MTable.get(getCtx(), AD_Table_ID);
											if (item.getColumnName().equals(MFactAcct.COLUMNNAME_Record_ID)) {
												columnName = mTable.getTableName().trim() + "_ID";
											} else {
												//line_id
												String sql = "SELECT DISTINCT(columnname) FROM ad_column where columnname ilike '" + mTable.getTableName().trim() + "%Line_ID'";   
												columnName =  DB.getSQLValueString(null, sql);
											}
											isZoom = true;
										}
										else
										{
											objPDE = printData.getNode("Fact_Acct_ID");
											if (objPDE != null && objPDE instanceof PrintDataElement)
											{
												PrintDataElement pdElement = (PrintDataElement) objPDE;
												columnValue = pdElement.getValueAsString();
												isZoom = true;
											}
										}
										
										if (isZoom)
											href.addAttribute("onclick", "parent.zoom('" + extension.getComponentId()
													+ "', '" + columnName + "', '" + columnValue + "')");
									}
									else
									{
										if (hasAction.contains(item.getAD_PrintFormatItem_ID())) {
											extension.extendIDColumn(row, td, href, pde, item, processInfo.getAD_PInstance_ID());
										} else {
											extension.extendIDColumn(row, td, href, pde, null, 0);
										}
									}
																											
								}
								else
								{
									td.addElement(Util.maskHTML(value));
								}
								if (cssPrefix != null)
								{
									if (DisplayType.isNumeric(pde.getDisplayType())) {
										td.setClass(cssPrefix + "-number");
									}
									
									else if (DisplayType.isDate(pde.getDisplayType()))
										td.setClass(cssPrefix + "-date");
									else
										td.setClass(cssPrefix + "-text");
								}								
							}
							else if (obj instanceof PrintData)
							{
								//	ignore contained Data
							}
							else if (obj instanceof String)
							{
								// ignore contained Data
							}							
							else
								log.log(Level.SEVERE, "Element not PrintData(Element) " + obj.getClass());
						}
					}	//	printed
				}	//	for all columns
				tr.output(printWriter);
			}	//	for all rows
			printWriter.write('\n');
			printWriter.write("		</table>");
			printWriter.write('\n');
			if(!onlyTable) {
				printWriter.write("	</body>");
				printWriter.write('\n');
				printWriter.write("</html>");
				printWriter.write('\n');
			}
			printWriter.flush();
			printWriter.close();
		} catch (Exception e) {
			log.log(Level.SEVERE, "(w)", e);
		}
		return false;
	}	//	createHTML
	
}	//	AbstractBatchImport
