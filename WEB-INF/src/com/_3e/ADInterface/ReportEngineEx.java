package com._3e.ADInterface;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.ecs.XhtmlDocument;
import org.apache.ecs.xhtml.h1;
import org.apache.ecs.xhtml.i;
import org.apache.ecs.xhtml.style;
import org.apache.ecs.xhtml.table;
import org.apache.ecs.xhtml.td;
import org.apache.ecs.xhtml.th;
import org.apache.ecs.xhtml.tr;
import org.compiere.model.MOrg;
import org.compiere.model.MQuery;
import org.compiere.print.MPrintFormat;
import org.compiere.print.MPrintFormatItem;
import org.compiere.print.PrintData;
import org.compiere.print.PrintDataElement;
import org.compiere.print.ReportEngine;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Language;
import org.compiere.util.Util;


public class ReportEngineEx { //extends  ReportEngine  {

	
	private static CLogger	log = CLogger.getCLogger(ReportEngineEx.class);
	

	

	
	/**************************************************************************
	 * 	Create EXCEL HTML File
	 * 	@param file file
	 *  @param onlyTable if false create complete HTML document
	 *  @param language optional language - if null the default language is used to format nubers/dates
	 * 	@return true if success
	 */
	public static boolean createEXCEL_HTML (ReportEngine re, Properties m_ctx, File file, boolean onlyTable, Language language)
	{
		try
		{
			Language lang = language;
			if (lang == null)
				lang = Language.getLoginLanguage();
			FileWriter fw = new FileWriter (file, false);
			return createEXCEL_HTML (re, m_ctx, new BufferedWriter(fw), onlyTable, lang);
		}
		catch (FileNotFoundException fnfe)
		{
			log.log(Level.SEVERE, "(f) - " + fnfe.toString());
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "(f)", e);
		}
		return false;
	}	//	createHTML
	
	public static boolean createEXCEL_HTML_wr (ReportEngine re, Properties m_ctx, Writer wr, boolean onlyTable, Language language)
	{
		try
		{
			Language lang = language;
			if (lang == null)
				lang = Language.getLoginLanguage();
			//FileWriter fw = new FileWriter (file, false);
			return createEXCEL_HTML (re, m_ctx, new BufferedWriter(wr), onlyTable, lang);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "(f)", e);
		}
		return false;
	}	//	createHTML
	
	

	/**
	 * 	Write EXCEL HTML to writer
	 * 	@param writer writer
	 *  @param onlyTable if false create complete HTML document
	 *  @param language optional language - if null nubers/dates are not formatted
	 * 	@return true if success
	 */
	public static boolean createEXCEL_HTML (ReportEngine re, Properties m_ctx, Writer writer, boolean onlyTable, Language language)
	{
		
		PrintData m_printData = re.getPrintData();
		MPrintFormat m_printFormat = re.getPrintFormat();
		///Properties m_ctx = new Properties();
		MQuery m_query = re.getQuery();
		
		try
		{
			table table = new table();
			//
			//	for all rows (-1 = header row)
			for (int row = -1; row < m_printData.getRowCount(); row++)
			{
				tr tr = new tr();
				table.addElement(tr);
				if (row != -1)
					m_printData.setRowIndex(row);
				//	for all columns
				for (int col = 0; col < m_printFormat.getItemCount(); col++)
				{
					MPrintFormatItem item = m_printFormat.getItem(col);
					if (item.isPrinted())
					{
						//	header row
						if (row == -1)
						{
							th th = new th();
							tr.addElement(th);
							th.addElement(Util.maskHTML(item.getPrintName(language)));
							th.setClass("xl_head");						
						}
						else
						{
							td td = new td();
							tr.addElement(td);
							Object obj = m_printData.getNode(new Integer(item.getAD_Column_ID()));
							if (obj == null)
								td.addElement("&nbsp;");
							else if (obj instanceof PrintDataElement)
							{
								String value = ((PrintDataElement)obj).getValueDisplay(null);	//	formatted
								int displayType = ((PrintDataElement)obj).getDisplayType();
								if (((PrintDataElement)obj).isNumeric())
								{
									if (displayType == DisplayType.Integer)
									{
										td.addAttribute("x:num", value);
										td.addElement(Util.maskHTML(value));
									}
									else if (displayType == DisplayType.Quantity)
									{
										td.addAttribute("x:num", value);
										td.addElement(Util.maskHTML(value));
									}
									else if (displayType == DisplayType.Amount)
									{
										td.addAttribute("x:num", value);
										td.addElement(Util.maskHTML(value));
										
									}
									else if (displayType == DisplayType.CostPrice)
									{
										td.addAttribute("x:num", "");
										td.addAttribute("u1:num", value);
										td.setClass("xl25");
										td.addElement(Util.maskHTML(value));
									}
									//else if (displayType == DisplayType.YesNo)
									//{
										
									//}
									else //	if (displayType == Number)										
									{
										td.addElement(Util.maskHTML(value));
									}									
								}
								else
								if (((PrintDataElement)obj).isDate())
								{
									if (displayType == DisplayType.Date)
									{
										td.setClass("xl24");
										td.addElement(Util.maskHTML(value));
									}
									else
									if (displayType == DisplayType.DateTime)
									{
										td.setClass("xl26");
										td.addElement(Util.maskHTML(value));
									} else
									{
										td.addElement(Util.maskHTML(value));
									}
								}
								else
								
								td.addElement(Util.maskHTML(value));
							}
							else if (obj instanceof PrintData)
							{
								//	ignore contained Data
							}
							else
								log.log(Level.SEVERE, "createHTML - Element not PrintData(Element) " + obj.getClass());
						}
					}	//	printed
				}	//	for all columns
			}	//	for all rows

			table table1 = new table();
			table1.setBorder(0).setCols(10).setCellPadding(0).setCellSpacing(2);
			table1.addElement( new tr().addElement(new td().setColSpan(10).addElement(new h1(re.getName())).setStyle("border:none;")));
			tr tr1;
			String tmp = "Jednostka: ";
			MOrg o = MOrg.get(m_ctx, Env.getAD_Org_ID(m_ctx));
			tmp += o.getName();			
			tr1 = new tr();
			tr1.addElement( new td().addElement( tmp ).setColSpan(10).setStyle("border:none;") );
			table1.addElement( tr1 );			
			
			java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance(java.text.DateFormat.FULL, new Locale("pl_PL"));
			String dateData = "Czas wygenerowania: "+dateFormat.format(new java.util.Date());
			tr1 = new tr();
			tr1.addElement( new td().addElement( dateData ).setColSpan(10).setStyle("border:none;") );
			table1.addElement( tr1 );			
			
			for (int r = 0; r < m_query.getRestrictionCount(); r++)
			{
				tr1 = new tr();
				td td1 = new td().addElement(
				 new i(m_query.getInfoName(r)));
				td1.addElement(  "   " +
				 m_query.getInfoOperator(r) + "   " +
				 m_query.getInfoDisplayAll(r) ).setColSpan( 10 );
				
				td1.setStyle("border:none;");
				
				tr1.addElement(td1);
				table1.addElement(tr1);
			}
			tr1 = new tr();
			tr1.addElement( new td().addElement( "&nbsp;" ).setColSpan(10).setStyle("border:none;height:20px;") );
			table1.addElement(tr1);
			
			//
			PrintWriter w = new PrintWriter(writer);
			if (onlyTable)
				table.output(w);
			else
			{
				XhtmlDocument doc = new XhtmlDocument();
				
				String st = 
"<!--table "+
"	{mso-displayed-decimal-separator:\".\"; "+
"	mso-displayed-thousand-separator:\" \";} "+
"@page "+
"	{margin:.98in .79in .98in .79in; "+
"	mso-header-margin:.5in; "+
"	mso-footer-margin:.5in;} "+
"tr "+
"	{mso-height-source:auto;} "+
"col "+
"	{mso-width-source:auto;} "+
"br "+
"	{mso-data-placement:same-cell;} "+
".style0 "+
"	{mso-number-format:General; "+
"	text-align:general; "+
"	vertical-align:bottom; "+
"	white-space:nowrap; "+
"	mso-rotate:0; "+
"	mso-background-source:auto; "+
"	mso-pattern:auto; "+
"	color:windowtext; "+
"	font-size:10.0pt; "+
"	font-weight:400; "+
"	font-style:normal; "+
"	text-decoration:none; "+
"	font-family:Arial; "+
"	mso-generic-font-family:auto; "+
"	mso-font-charset:238; "+
"	border:none; "+
"	mso-protection:locked visible; "+
"	mso-style-name:Normalny; "+
"	mso-style-id:0;} "+
"td "+
"	{mso-style-parent:style0; "+
"	padding-top:1px; "+
"	padding-right:1px; "+
"	padding-left:1px; "+
"	mso-ignore:padding; "+
"	color:windowtext; "+
"	font-size:10.0pt; "+
"	font-weight:400; "+
"	font-style:normal; "+
"	text-decoration:none; "+
"	font-family:Arial; "+
"	mso-generic-font-family:auto; "+
"	mso-font-charset:238; "+
"	mso-number-format:General; "+
"	text-align:general; "+
"	vertical-align:bottom; "+
//"	border:none; "+
"border:.5pt solid #a0a0a0; "+ //windowtext
"	mso-background-source:auto; "+
"	mso-pattern:auto; "+
"	mso-protection:locked visible; "+
"	white-space:nowrap; "+
"	mso-rotate:0;} "+
".xl24 "+
"{mso-style-parent:style0; "+
"mso-number-format:\"Short Date\";} "+
".xl25 "+
"{mso-style-parent:style0; "+
"mso-number-format:Fixed;} "+
".xl26 "+
"{mso-style-parent:style0; "+
"mso-number-format:\"yy\\/mm\\/dd\\ h\\:mm\\;\\@\";} "+
".xl_head "+
"{text-align:center; mso-style-parent:style0; "+
"font-weight:700; "+
"font-family:Arial, sans-serif; "+
"mso-font-charset:238; "+
"border:.5pt solid windowtext; "+
"background:silver; "+
"mso-pattern:auto none;} "+
"-->  ";

				doc.appendHead( new style().addElement(st));
				doc.appendBody( table1 );
				doc.appendBody(table);
				doc.output(w);
			}
			w.flush();
			w.close();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "createHTML(w)", e);
		}
		return false;
	}	//	createHTML

}
