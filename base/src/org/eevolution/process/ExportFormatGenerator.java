/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.process;

import java.math.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

import org.compiere.model.*;
import org.compiere.util.*;
import org.compiere.process.*;

import org.eevolution.model.*;



/**
 *	Create a Export Format from a Window
 *	
 *  @author Victor Perez www.e-evolution.com
 *  @version $Id: ExportFormatGenerator.java,v 1.0 
 */
public class ExportFormatGenerator extends SvrProcess
{

	private int			p_AD_Window_ID = 0;
	private boolean     p_IsMandatory = false;
	private boolean     p_IsInsertRecord= false;
	private Hashtable m_formats = new Hashtable();
	private String m_parent_table = null;
	private String m_format_value = null;
	private int m_level = -1;

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("AD_Window_ID"))
				p_AD_Window_ID = para[i].getParameterAsInt();
			else if (name.equals("IsMandatory"))
			{	
				p_IsMandatory = "Y".equals(para[i].getParameter());
			}	
			else if (name.equals("IsInsertRecord"))
			{	
				p_IsInsertRecord = "Y".equals(para[i].getParameter());
			}	
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}	//	prepare

	/**
	 * 	Generate Export Format
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt () throws Exception
	{
		MWindow window = new MWindow(getCtx(),p_AD_Window_ID, get_TrxName());
		MTab[] tabs = window.getTabs(true, get_TrxName());
		
		
		for(MTab tab:tabs)
		{
			MTable table = null;
			String format = null;
			
			if(p_IsInsertRecord)
			{	
				if(tab.isInsertRecord())
				{	
				table = new MTable(getCtx(), tab.getAD_Table_ID(), get_TrxName());				
			    format = createFormat(table);

				}
			}
			else
			{
				table = new MTable(getCtx(), tab.getAD_Table_ID(), get_TrxName());
				format = createFormat(table);

			}
			
			if (tab.getTabLevel() > m_level)
			{	
				m_parent_table = table.getTableName();
				m_format_value = format; 
			}
		}
		return "ok";
	}	//	doIt
	
	private String createFormat(MTable table) throws Exception 
	{		
		log.info("Table Name:"+table.getTableName());
		MColumn[] cols = table.getColumns(true);
		String unique = null;
		boolean fieldname = false;
		for(MColumn col : cols)
		{
			if(col.isIdentifier() && col.getSeqNo() == 1)
			{	
				unique = col.getColumnName();
				if(unique.equals("Name"))
					fieldname = true;
				log.info("Unique Key"+unique);
				break;
			}
		}
		
		if (unique==null)
			unique="Name";
		
		MEXPFormat format = null;
		//String formatValue = table.getTableName()+"_"+unique;
		String formatValue = table.getTableName();
		log.info("Export Format Value:"+formatValue);
		format = (MEXPFormat) m_formats.get(formatValue);
		if (format != null)
			return format.getValue();
		
		format =	MEXPFormat.getFormatByValueAD_Client_IDAndVersion(getCtx(), formatValue, getAD_Client_ID(), "1", get_TrxName());
		if(format == null)
		   format = new MEXPFormat(getCtx(), 0 , get_TrxName());
		
		format.setValue(formatValue);
		format.setName(table.getName());
		format.setAD_Table_ID(table.getAD_Table_ID());
		format.setDescription(table.getDescription());
		format.setHelp(table.getHelp());
		format.setVersion("1");
		format.save();
		if (format != null)
		m_formats.put(format.getValue(), format);
		
		int position = 10;
		for(MColumn col : cols)
		{
			if(p_IsMandatory)
			{	
				if(col.isMandatory())
				createFormatLine(format, table, col, position);
			}	
			else
				createFormatLine(format, table, col, position);
			
			position++;
		}
		return format.getValue(); 
	}
	
	private int createFormatLine(MEXPFormat format, MTable table, MColumn col, int position) throws Exception 
	{	
			    //validate redount tables
				//String column = table.getTableName()+"_ID";
				//if(column.equals(col.getColumnName()) & !col.isIdentifier())
				//	return 0;
				
				MEXPFormatLine format_line =null;
				//String formatlinevalue= table.getTableName()+"_"+col.getColumnName();
				String formatlinevalue= col.getColumnName();
				format_line  = MEXPFormatLine.getFormatLineByValue(getCtx(),formatlinevalue ,format.getEXP_Format_ID(),get_TrxName());
				if(format_line==null)
					format_line = new MEXPFormatLine(getCtx(),0,get_TrxName());
				
				format_line.setEXP_Format_ID(format.getEXP_Format_ID());
				format_line.setValue(formatlinevalue);
				format_line.setName(col.getName());
				format_line.setDescription(col.getDescription());
				format_line.setHelp(col.getHelp());
				format_line.setPosition(position);
				format_line.setIsMandatory(col.isMandatory());
				if(col.isIdentifier())
				{
				format_line.setIsPartUniqueIndex(true);	
				format_line.setIsActive(true);
				}
				else
				format_line.setIsActive(false);
				MTable tabledir = null;
				
				if(col.getAD_Reference_ID()==DisplayType.Table)
				{
					int AD_Table_ID = DB.getSQLValue(get_TrxName(), "SELECT rt.AD_Table_ID FROM AD_Reference r INNER JOIN AD_Ref_Table rt ON (r.AD_Reference_ID=rt.AD_Reference_ID)  WHERE r.AD_Reference_ID=?", col.getAD_Reference_Value_ID());
					if (AD_Table_ID > 0)
					{	
						tabledir = MTable.get(getCtx(), AD_Table_ID);
						format_line.setValue(col.getColumnName()+"_Reference");
						format_line.setName("Referenced "+ tabledir.getTableName());
						format_line.setAD_Column_ID(col.getAD_Column_ID());
						String format_value = createFormat(tabledir);
						int embedded = ((MEXPFormat)m_formats.get(format_value)).getEXP_Format_ID();
						format_line.setType(MEXPFormatLine.TYPE_ReferencedEXPFormat);
						format_line.setEXP_EmbeddedFormat_ID(embedded);
						format_line.save();
						return format_line.getEXP_FormatLine_ID();
					}
					
				}
				
				if((col.getColumnName().endsWith("_ID") & col.isKey()== false) || (col.getColumnName().endsWith("_ID") & col.isParent() == true))
				{

					String tableName = col.getColumnName().substring(0, col.getColumnName().lastIndexOf("_ID"));
					log.info("Table Name:"+tableName);
				
			
					tabledir = MTable.get(getCtx(), tableName);	
					
					format_line.setValue(tabledir.getTableName()+"_Reference");
					format_line.setName("Referenced "+ tabledir.getTableName());
					
					if (tabledir!=null)
					{	
						if(m_parent_table != null)
						{	
							if (col.isParent() && col.getColumnName().contains(m_parent_table))
							{		
								
									int embedded = ((MEXPFormat)m_formats.get(m_format_value)).getEXP_Format_ID();									
									format_line.setValue(format.getValue()+"_Embedded");
									format_line.setName("Embedded "+ format.getName());
									format_line.setEXP_EmbeddedFormat_ID(format_line.getEXP_Format_ID());
									format_line.setEXP_Format_ID(embedded);
									format_line.setType(MEXPFormatLine.TYPE_EmbeddedEXPFormat);										
									format_line.setAD_Column_ID(col.getAD_Column_ID());
									format_line.save();
									log.info("Export Format Line:"+format_line.getName());
									return format_line.getEXP_FormatLine_ID();
									
							}
						}
						String format_value = createFormat(tabledir);
						int embedded = ((MEXPFormat)m_formats.get(format_value)).getEXP_Format_ID();
						format_line.setType(MEXPFormatLine.TYPE_ReferencedEXPFormat);
						format_line.setEXP_EmbeddedFormat_ID(embedded);
					}
					else 
					format_line.setType(MEXPFormatLine.TYPE_XMLElement);	
				}
				
				format_line.setAD_Column_ID(col.getAD_Column_ID());
				format_line.save();
				log.info("Export Format Line:"+format_line.getName());
				return format_line.getEXP_FormatLine_ID();
				
		  }
}	//	Generate Export Format
