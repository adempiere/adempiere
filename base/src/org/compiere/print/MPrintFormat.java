/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.print;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;

import javax.sql.RowSet;

import org.compiere.model.MClient;
import org.compiere.model.MQuery;
import org.compiere.model.MRole;
import org.compiere.model.X_AD_PrintFormat;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.CPreparedStatement;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Language;
import org.compiere.util.Msg;
import org.compiere.util.Util;

/**
 *	AD_PrintFormat - Print Format Model.
 *	(Add missing Items with PrintFormatUtil)
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: MPrintFormat.java,v 1.3 2006/07/30 00:53:02 jjanke Exp $
 */
public class MPrintFormat extends X_AD_PrintFormat
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5475500787921763987L;

	/**
	 *	Public Constructor.
	 * 	Use static get methods
	 *  @param ctx context
	 *  @param AD_PrintFormat_ID AD_PrintFormat_ID
	 *	@param trxName transaction
	 */
	public MPrintFormat (Properties ctx, int AD_PrintFormat_ID, String trxName)
	{
		super (ctx, AD_PrintFormat_ID, trxName);
		//	Language=[Deutsch,Locale=de_DE,AD_Language=en_US,DatePattern=DD.MM.YYYY,DecimalPoint=false]
		m_language = Env.getLanguage(ctx);
		if (AD_PrintFormat_ID == 0)
		{
			setStandardHeaderFooter(true);
			setIsTableBased(true);
			setIsForm(false);
			setIsDefault(false);
		}
		m_items = getItems();
	}	//	MPrintFormat
	
	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MPrintFormat (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		m_language = Env.getLanguage(ctx);
		m_items = getItems();
	}	//	MPrintFormat

	/** Items							*/
	private MPrintFormatItem[]		m_items = null;
	/** Translation View Language		*/
	private String					m_translationViewLanguage = null;
	/**	Language of Report				*/
	private Language 				m_language;
	/** Table Format					*/
	private MPrintTableFormat 		m_tFormat;

	/**	Static Logger	*/
	private static CLogger			s_log = CLogger.getCLogger (MPrintFormat.class);

	/**
	 * 	Get Language
	 *  @return language
	 */
	public Language getLanguage()
	{
		return m_language;
	}	//	getLanguage

	/**
	 * 	Set Language
	 *  @param language language
	 */
	public void setLanguage(Language language)
	{
		if (language != null)
		{
			m_language = language;
		//	log.fine("setLanguage - " + language);
		}
		m_translationViewLanguage = null;
	}	//	getLanguage

	/**
	 * 	Get AD_Column_ID of Order Columns
	 * 	@return Array of AD_Column_IDs in Sort Order
	 */
	public int[] getOrderAD_Column_IDs()
	{
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();	//	SortNo - AD_Column_ID
		for (int i = 0; i < m_items.length; i++)
		{
			//	Sort Order and Column must be > 0
			if (m_items[i].getSortNo() != 0 && m_items[i].getAD_Column_ID() != 0)
				map.put(new Integer(m_items[i].getSortNo()), new Integer(m_items[i].getAD_Column_ID()));
		}
		//	Get SortNo and Sort them
		Integer[] keys = new Integer[map.keySet().size()];
		map.keySet().toArray(keys);
		Arrays.sort(keys);

		//	Create AD_Column_ID array
		int[] retValue = new int[keys.length];
		for (int i = 0; i < keys.length; i++)
		{
			Integer value = (Integer)map.get(keys[i]);
			retValue[i] = value.intValue();
		}
		return retValue;
	}	//	getOrderAD_Column_IDs

	/**
	 * 	Get AD_Column_IDs of columns in Report
	 * 	@return Array of AD_Column_ID
	 */
	public int[] getAD_Column_IDs()
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < m_items.length; i++)
		{
			if (m_items[i].getAD_Column_ID() != 0 && m_items[i].isPrinted())
				list.add(new Integer(m_items[i].getAD_Column_ID()));
		}
		//	Convert
		int[] retValue = new int[list.size()];
		for (int i = 0; i < list.size(); i++)
			retValue[i] = ((Integer)list.get(i)).intValue();
		return retValue;
	}	//	getAD_Column_IDs

	/**
	 * 	Set Items
	 * 	@param items items
	 */
	private void setItems (MPrintFormatItem[] items)
	{
		if (items != null)
			m_items = items;
	}	//	setItems

	/**
	 * 	Get active Items
	 * 	@return items
	 */
	private MPrintFormatItem[] getItems()
	{
		ArrayList<MPrintFormatItem> list = new ArrayList<MPrintFormatItem>();
		String sql = "SELECT * FROM AD_PrintFormatItem pfi "
			+ "WHERE pfi.AD_PrintFormat_ID=? AND pfi.IsActive='Y'"
			//	Display restrictions - Passwords, etc.
			+ " AND NOT EXISTS (SELECT * FROM AD_Field f "
				+ "WHERE pfi.AD_Column_ID=f.AD_Column_ID"
				+ " AND (f.IsEncrypted='Y' OR f.ObscureType IS NOT NULL))" 
			+ "ORDER BY SeqNo";
		MRole role = MRole.getDefault(getCtx(), false);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, get_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				MPrintFormatItem pfi = new MPrintFormatItem(p_ctx, rs, get_TrxName());
				if (role.isColumnAccess(getAD_Table_ID(), pfi.getAD_Column_ID(), true))
					list.add (pfi);
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//
		MPrintFormatItem[] retValue = new MPrintFormatItem[list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getItems

	/**
	 * 	Get Item Count
	 * 	@return number of items or -1 if items not defined
	 */
	public int getItemCount()
	{
		if (m_items == null)
			return -1;
		return m_items.length;
	}	//	getItemCount

	/**
	 * 	Get Print Format Item
	 * 	@param index index
	 * 	@return Print Format Item
	 */
	public MPrintFormatItem getItem (int index)
	{
		if (index < 0 || index >= m_items.length)
			throw new ArrayIndexOutOfBoundsException("Index=" + index + " - Length=" + m_items.length);
		return m_items[index];
	}	//	getItem

	/**
	 * 	Set the translation of the Format Items to the original
	 */
	public void setTranslation()
	{
		StringBuffer sb = new StringBuffer ("UPDATE AD_PrintFormatItem_Trl t"
			+ " SET (PrintName, PrintNameSuffix)="
			+ " (SELECT PrintName, PrintNameSuffix FROM AD_PrintFormatItem i WHERE i.AD_PrintFormatItem_ID=t.AD_PrintFormatItem_ID) "
			+ "WHERE AD_PrintFormatItem_ID IN"
			+ " (SELECT AD_PrintFormatItem_ID FROM AD_PrintFormatItem WHERE AD_PrintFormat_ID=").append(get_ID()).append(")");
		int no = DB.executeUpdate(sb.toString(), get_TrxName());
		log.fine("setTranslation #" + no);
	}	//	setTranslation

	
	/**************************************************************************
	 * 	Set Standard Header
	 *	@param standardHeaderFooter true if std header
	 */
	public void setStandardHeaderFooter (boolean standardHeaderFooter)
	{
		super.setIsStandardHeaderFooter(standardHeaderFooter);
		if (standardHeaderFooter)
		{
			setFooterMargin(0);
			setHeaderMargin(0);
		}
	}	//	setSatndardHeaderFooter

	/**
	 * 	Set Table based.
	 * 	Reset Form
	 * 	@param tableBased true if table based
	 */
	public void setIsTableBased (boolean tableBased)
	{
		super.setIsTableBased (tableBased);
		if (tableBased)
			super.setIsForm(false);
	}	//	setIsTableBased

	
	/**************************************************************************
	 * 	Set Translation View Language.
	 * 	@param language language (checked for base language)
	 */
	public void setTranslationLanguage (Language language)
	{
		if (language == null || language.isBaseLanguage())
		{
			log.info("Ignored - " + language);
			m_translationViewLanguage = null;
		}
		else
		{
			log.info("Language=" + language.getAD_Language());
			m_translationViewLanguage = language.getAD_Language();
			m_language = language;
		}
	}	//	setTranslationLanguage

	/**
	 *  Get Translation View use
	 *	@return true if a translation view is used
	 */
	public boolean isTranslationView()
	{
		return m_translationViewLanguage != null;
	}	//	isTranslationView

	/**
	 *	Update the Query to access the Translation View.
	 *  Can be called multiple times, adds only if not set already
	 *  @param query query to be updated
	 */
	public void setTranslationViewQuery (MQuery query)
	{
		//	Set Table Name and add add restriction, if a view and language set
		if (m_translationViewLanguage != null && query != null && query.getTableName().toUpperCase().endsWith("_V"))
		{
			query.setTableName(query.getTableName() + "t");
			query.addRestriction("AD_Language", MQuery.EQUAL, m_translationViewLanguage);
		}
	}	//	setTranslationViewQuery

	
	/**************************************************************************
	 * 	Get Optional TableFormat
	 * 	@param AD_PrintTableFormat_ID table format
	 */
	public void setAD_PrintTableFormat_ID (int AD_PrintTableFormat_ID)
	{
		super.setAD_PrintTableFormat_ID(AD_PrintTableFormat_ID);
		m_tFormat = MPrintTableFormat.get (getCtx(), AD_PrintTableFormat_ID, getAD_PrintFont_ID());
	}	//	getAD_PrintTableFormat_ID

	/**
	 * 	Get Table Format
	 * 	@return Table Format
	 */
	public MPrintTableFormat getTableFormat()
	{
		if (m_tFormat == null)
			m_tFormat = MPrintTableFormat.get(getCtx(), getAD_PrintTableFormat_ID(), getAD_PrintFont_ID());
		return m_tFormat;
	}	//	getTableFormat

	/**
	 * 	Sting Representation
	 * 	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer ("MPrintFormat[ID=").append(get_ID())
			.append(",Name=").append(getName())
			.append(",Language=").append(getLanguage())
			.append(",Items=").append(getItemCount())
			.append("]");
		return sb.toString();
	}	//	toString

	
	/**************************************************************************
	 *  Load Special data (images, ..).
	 *  To be extended by sub-classes
	 *  @param rs result set
	 *  @param index zero based index
	 *  @return value value
	 *  @throws SQLException
	 */
	protected Object loadSpecial (ResultSet rs, int index) throws SQLException
	{
		//	CreateCopy
	//	log.config(p_info.getColumnName(index));
		return null;
	}   //  loadSpecial

	/**
	 *  Save Special Data.
	 *  To be extended by sub-classes
	 *  @param value value
	 *  @param index index
	 *  @return SQL code for INSERT VALUES clause
	 */
	protected String saveNewSpecial (Object value, int index)
	{
		//	CreateCopy
	//	String colName = p_info.getColumnName(index);
	//	String colClass = p_info.getColumnClass(index).toString();
	//	String colValue = value == null ? "null" : value.getClass().toString();
	//	log.log(Level.SEVERE, "Unknown class for column " + colName + " (" + colClass + ") - Value=" + colValue);
		if (value == null)
			return "NULL";
		return value.toString();
	}   //  saveNewSpecial


	/**************************************************************************
	 * 	Create MPrintFormat for Table
	 *  @param ctx context
	 * 	@param AD_Table_ID table
	 * 	@return print format
	 */
	static public MPrintFormat createFromTable (Properties ctx, int AD_Table_ID)
	{
		return createFromTable(ctx, AD_Table_ID, 0);
	}	//	createFromTable

	/**
	 * 	Create MPrintFormat for Table
	 *  @param ctx context
	 * 	@param AD_Table_ID table
	 *  @param AD_PrintFormat_ID 0 or existing PrintFormat
	 * 	@return print format
	 */
	static public MPrintFormat createFromTable (Properties ctx, 
		int AD_Table_ID, int AD_PrintFormat_ID)
	{
		MClient company = MClient.get(ctx);
		s_log.info ("AD_Table_ID=" + AD_Table_ID + " - AD_Client_ID=" + company.get_ID());

		MPrintFormat pf = new MPrintFormat(ctx, AD_PrintFormat_ID, null);
		pf.setAD_Table_ID (AD_Table_ID);

		//	Get Info
		String sql = "SELECT TableName,"		//	1
			+ " (SELECT COUNT(*) FROM AD_PrintFormat x WHERE x.AD_Table_ID=t.AD_Table_ID AND x.AD_Client_ID=c.AD_Client_ID) AS Count,"
			+ " COALESCE (cpc.AD_PrintColor_ID, pc.AD_PrintColor_ID) AS AD_PrintColor_ID,"	//	3
			+ " COALESCE (cpf.AD_PrintFont_ID, pf.AD_PrintFont_ID) AS AD_PrintFont_ID,"
			+ " COALESCE (cpp.AD_PrintPaper_ID, pp.AD_PrintPaper_ID) AS AD_PrintPaper_ID "
			+ "FROM AD_Table t, AD_Client c"
			+ " LEFT OUTER JOIN AD_PrintColor cpc ON (cpc.AD_Client_ID=c.AD_Client_ID AND cpc.IsDefault='Y')"
			+ " LEFT OUTER JOIN AD_PrintFont cpf ON (cpf.AD_Client_ID=c.AD_Client_ID AND cpf.IsDefault='Y')"
			+ " LEFT OUTER JOIN AD_PrintPaper cpp ON (cpp.AD_Client_ID=c.AD_Client_ID AND cpp.IsDefault='Y'),"
			+ " AD_PrintColor pc, AD_PrintFont pf, AD_PrintPaper pp "
			+ "WHERE t.AD_Table_ID=? AND c.AD_Client_ID=?"		//	#1/2
			+ " AND pc.IsDefault='Y' AND pf.IsDefault='Y' AND pp.IsDefault='Y'";
		boolean error = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_Table_ID);
			pstmt.setInt(2, company.get_ID());
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				//	Name
				String TableName = rs.getString(1);
				String ColumnName = TableName + "_ID";
				String s = ColumnName;
				if (!ColumnName.equals("T_Report_ID"))
				{
					s = Msg.translate (ctx, ColumnName);
					if (ColumnName.equals (s)) //	not found
						s = Msg.translate (ctx, TableName);
				}
				int count = rs.getInt(2);
				if (count > 0)
					s += "_" + (count+1);
				pf.setName(company.getValue() + " -> " + s);
				//
				pf.setAD_PrintColor_ID(rs.getInt(3));
				pf.setAD_PrintFont_ID(rs.getInt(4));
				pf.setAD_PrintPaper_ID(rs.getInt(5));
				//
				error = false;
			}
			else
				s_log.log(Level.SEVERE, "No info found " + AD_Table_ID);
		}
		catch (SQLException e)
		{
			s_log.log(Level.SEVERE, sql, e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		if (error)
			return null;

		//	Save & complete
		if (!pf.save())
			return null;
	//	pf.dump();
		pf.setItems (createItems(ctx, pf));
		//
		return pf;
	}	//	createFromTable

	/**
	 * 	Create MPrintFormat for ReportView
	 *  @param ctx context
	 * 	@param AD_ReportView_ID ReportView
	 *  @param ReportName - optional Report Name
	 * 	@return print format
	 */
	static public MPrintFormat createFromReportView (Properties ctx, int AD_ReportView_ID, String ReportName)
	{
		MClient company = MClient.get(ctx);
		s_log.info ("AD_ReportView_ID=" + AD_ReportView_ID + " - AD_Client_ID=" + company.get_ID() + " - " + ReportName);

		MPrintFormat pf = new MPrintFormat(ctx, 0, null);
		pf.setAD_ReportView_ID (AD_ReportView_ID);

		//	Get Info
		String sql = "SELECT t.TableName,"
			+ " (SELECT COUNT(*) FROM AD_PrintFormat x WHERE x.AD_ReportView_ID=rv.AD_ReportView_ID AND x.AD_Client_ID=c.AD_Client_ID) AS Count,"
			+ " COALESCE (cpc.AD_PrintColor_ID, pc.AD_PrintColor_ID) AS AD_PrintColor_ID,"
			+ " COALESCE (cpf.AD_PrintFont_ID, pf.AD_PrintFont_ID) AS AD_PrintFont_ID,"
			+ " COALESCE (cpp.AD_PrintPaper_ID, pp.AD_PrintPaper_ID) AS AD_PrintPaper_ID,"
			+ " t.AD_Table_ID "
			+ "FROM AD_ReportView rv"
			+ " INNER JOIN AD_Table t ON (rv.AD_Table_ID=t.AD_Table_ID),"
			+ " AD_Client c"
			+ " LEFT OUTER JOIN AD_PrintColor cpc ON (cpc.AD_Client_ID=c.AD_Client_ID AND cpc.IsDefault='Y')"
			+ " LEFT OUTER JOIN AD_PrintFont cpf ON (cpf.AD_Client_ID=c.AD_Client_ID AND cpf.IsDefault='Y')"
			+ " LEFT OUTER JOIN AD_PrintPaper cpp ON (cpp.AD_Client_ID=c.AD_Client_ID AND cpp.IsDefault='Y'),"
			+ " AD_PrintColor pc, AD_PrintFont pf, AD_PrintPaper pp "
			+ "WHERE rv.AD_ReportView_ID=? AND c.AD_Client_ID=?"
			+ " AND pc.IsDefault='Y' AND pf.IsDefault='Y' AND pp.IsDefault='Y'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean error = true;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_ReportView_ID);
			pstmt.setInt(2,  company.get_ID());
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				//	Name
				String name = ReportName;
				if (name == null || name.length() == 0)
					name = rs.getString(1);		//	TableName
				int count = rs.getInt(2);
				if (count > 0)
					name += "_" + count;
				pf.setName(company.getValue() + " -> " + name);
				//
				pf.setAD_PrintColor_ID(rs.getInt(3));
				pf.setAD_PrintFont_ID(rs.getInt(4));
				pf.setAD_PrintPaper_ID(rs.getInt(5));
				//
				pf.setAD_Table_ID (rs.getInt(6));
				error = false;
			}
			else
				s_log.log(Level.SEVERE, "Not found: AD_ReportView_ID=" + AD_ReportView_ID);
		}
		catch (SQLException e)
		{
			s_log.log(Level.SEVERE, sql, e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		if (error)
			return null;

		//	Save & complete
		if (!pf.save())
			return null;
	//	pf.dump();
		pf.setItems (createItems(ctx, pf));
		//
		return pf;
	}	//	createFromReportView


	/**
	 * 	Create Items.
	 *  Using the display order of Fields in some Tab
	 *  @param ctx context
	 *  @param format print format
	 * 	@return items
	 */
	static private MPrintFormatItem[] createItems (Properties ctx, MPrintFormat format)
	{
		s_log.fine ("From window Tab ...");
		ArrayList<MPrintFormatItem> list = new ArrayList<MPrintFormatItem>();
		//	Get Column List from Tab
		String sql = "SELECT AD_Column_ID " //, Name, IsDisplayed, SeqNo
			+ "FROM AD_Field "
			+ "WHERE AD_Tab_ID=(SELECT MIN(AD_Tab_ID) FROM AD_Tab WHERE AD_Table_ID=?)"
			+ " AND IsEncrypted='N' AND ObscureType IS NULL "
			+ " AND AD_Column_ID NOT IN (SELECT pfi.AD_Column_ID FROM AD_PrintFormatItem pfi WHERE pfi.AD_PrintFormat_ID=? AND pfi.AD_Column_ID IS NOT NULL) "
			+ "ORDER BY COALESCE(IsDisplayed,'N') DESC, SortNo, SeqNo, Name";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, format.get_TrxName());
			pstmt.setInt(1, format.getAD_Table_ID());
			pstmt.setInt(2, format.getAD_PrintFormat_ID());
			rs = pstmt.executeQuery();
			int seqNo = 1;
			while (rs.next())
			{
				MPrintFormatItem pfi = MPrintFormatItem.createFromColumn (format, rs.getInt(1), seqNo++);
				if (pfi != null)
				{
					list.add (pfi);
					s_log.finest("Tab: " + pfi);
				}
			}
		}
		catch (SQLException e)
		{
			s_log.log(Level.SEVERE, "(tab) - " + sql, e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//	No Tab found for Table
		if (list.size() == 0)
		{
			s_log.fine("From Table ...");
			sql = "SELECT AD_Column_ID "
				+ "FROM AD_Column "
				+ "WHERE AD_Table_ID=? "
				+ " AND AD_Column_ID NOT IN (SELECT pfi.AD_Column_ID FROM AD_PrintFormatItem pfi WHERE pfi.AD_PrintFormat_ID=? AND pfi.AD_Column_ID IS NOT NULL) "
				+ "ORDER BY IsIdentifier DESC, SeqNo, Name";
			try
			{
				pstmt = DB.prepareStatement(sql, format.get_TrxName());
				pstmt.setInt(1, format.getAD_Table_ID());
				pstmt.setInt(2, format.getAD_PrintFormat_ID());
				rs = pstmt.executeQuery();
				int seqNo = 1;
				while (rs.next())
				{
					MPrintFormatItem pfi = MPrintFormatItem.createFromColumn (format, rs.getInt(1), seqNo++);
					if (pfi != null)
					{
						list.add (pfi);
						s_log.finest("Table: " + pfi);
					}
				}
			}
			catch (SQLException e)
			{
				s_log.log(Level.SEVERE, "(table) - " + sql, e);
			}			
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		
		//
		MPrintFormatItem[] retValue = new MPrintFormatItem[list.size()];
		list.toArray(retValue);
		s_log.info(format + " - #" + retValue.length);
		return retValue;
	}	//	createItems

	/**
	 * 	Copy Items
	 *  @param fromFormat from print format
	 *  @param toFormat to print format (client, id)
	 * 	@return items
	 */
	static private MPrintFormatItem[] copyItems (MPrintFormat fromFormat, MPrintFormat toFormat)
	{
		s_log.info("From=" + fromFormat);
		ArrayList<MPrintFormatItem> list = new ArrayList<MPrintFormatItem>();

		MPrintFormatItem[] items = fromFormat.getItems();
		for (int i = 0; i < items.length; i++)
		{
			MPrintFormatItem pfi = items[i].copyToClient (toFormat.getAD_Client_ID(), toFormat.get_ID());
			if (pfi != null)
				list.add (pfi);
		}
		//
		MPrintFormatItem[] retValue = new MPrintFormatItem[list.size()];
		list.toArray(retValue);
		copyTranslationItems (items, retValue);	//	JTP fix
		return retValue;
	}	//	copyItems

	/**
     *	Copy translation records (from - to)
     *	@param fromItems from items
     *	@param toItems to items
     */
    static private void copyTranslationItems (MPrintFormatItem[] fromItems, 
    	MPrintFormatItem[] toItems)
    {
    	if (fromItems == null || toItems == null)
            return;		//	should not happen
        
    	int counter = 0;
        for (int i = 0; i < fromItems.length; i++)
        {   
            int fromID = fromItems[i].getAD_PrintFormatItem_ID();
            int toID = toItems[i].getAD_PrintFormatItem_ID();
            
            StringBuffer sql = new StringBuffer("UPDATE AD_PrintFormatItem_Trl new ")
            	//	Set
            	.append("SET (PrintName, PrintNameSuffix, IsTranslated) = ")
            	.append("(")
            	.append("SELECT PrintName, PrintNameSuffix, IsTranslated ")
            	.append("FROM AD_PrintFormatItem_Trl old ")
            	.append("WHERE old.AD_Language=new.AD_Language")
            	.append(" AND AD_PrintFormatItem_ID =").append(fromID)
            	.append(") ")
            	//	WHERE
            	.append("WHERE  AD_PrintFormatItem_ID=").append(toID)
            	.append(" AND EXISTS (SELECT AD_PrintFormatItem_ID ")
            		.append(" FROM AD_PrintFormatItem_trl old")
            		.append(" WHERE old.AD_Language=new.AD_Language")
            		.append(" AND AD_PrintFormatItem_ID =").append(fromID)
            		.append(")");
            int no = DB.executeUpdate(sql.toString(), null);
            if (no == 0)	//	if first has no translation, the rest does neither
            	break;
            counter += no;
        }	//	for
        s_log.finest("#" + counter);
    }	//	copyTranslationItems

	
	/**************************************************************************
	 * 	Copy existing Definition To Client
	 * 	@param ctx context
	 * 	@param from_AD_PrintFormat_ID format
	 * 	@param to_AD_PrintFormat_ID format
	 * 	@return print format
	 */
	public static MPrintFormat copy (Properties ctx, 
		int from_AD_PrintFormat_ID, int to_AD_PrintFormat_ID)
	{
		return copy (ctx, from_AD_PrintFormat_ID, to_AD_PrintFormat_ID, -1);
	}	//	copy

	/**
	 * 	Copy existing Definition To Client
	 * 	@param ctx context
	 * 	@param AD_PrintFormat_ID format
	 * 	@param to_Client_ID to client
	 * 	@return print format
	 */
	public static MPrintFormat copyToClient (Properties ctx, 
		int AD_PrintFormat_ID, int to_Client_ID)
	{
		return copy (ctx, AD_PrintFormat_ID, 0, to_Client_ID);
	}	//	copy

	/**
	 * 	Copy existing Definition To Client
	 * 	@param ctx context
	 * 	@param from_AD_PrintFormat_ID format
	 *  @param to_AD_PrintFormat_ID to format or 0 for new
	 * 	@param to_Client_ID to client (ignored, if to_AD_PrintFormat_ID <> 0)
	 * 	@return print format
	 */
	private static MPrintFormat copy (Properties ctx, int from_AD_PrintFormat_ID,
		int to_AD_PrintFormat_ID, int to_Client_ID)
	{
		MClient company = MClient.get(ctx);
		s_log.info ("From AD_PrintFormat_ID=" + from_AD_PrintFormat_ID
			+ ", To AD_PrintFormat_ID=" + to_AD_PrintFormat_ID 
			+ ", To Client_ID=" + to_Client_ID);
		if (from_AD_PrintFormat_ID == 0)
			throw new IllegalArgumentException ("From_AD_PrintFormat_ID is 0");
		//
		MPrintFormat from = new MPrintFormat(ctx, from_AD_PrintFormat_ID, null);
		MPrintFormat to = new MPrintFormat (ctx, to_AD_PrintFormat_ID, null);		//	could be 0
		MPrintFormat.copyValues (from, to);
		//	New
		if (to_AD_PrintFormat_ID == 0)
		{
			if (to_Client_ID < 0)
				to_Client_ID = Env.getAD_Client_ID(ctx);
			to.setClientOrg (to_Client_ID, 0);
		}
		//Set Name - Remove TEMPLATE
   		to.setName(company.getValue() + " -> " + Util.replace(to.getName(), "** TEMPLATE **", "").trim());
    	String sql = "SELECT count(*) from AD_PrintFormat WHERE AD_Client_ID = ? AND AD_Table_ID = ? AND Name = ?";
  		
  		String suggestedName = to.getName();
   		int count = 0;
   		while (DB.getSQLValue(to.get_TrxName(), sql,to.getAD_Client_ID(), to.getAD_Table_ID(), suggestedName) > 0)
   		{
   			count++;
    			suggestedName = to.getName() + " ("+ count + ")";
   		}
    		
    	to.setName(suggestedName);



		//
		to.saveEx();

		//	Copy Items
		to.setItems(copyItems(from,to));
		return to;
	}	//	copyToClient

	/*************************************************************************/

	/** Cached Formats						*/
	static private CCache<Integer,MPrintFormat> s_formats = new CCache<Integer,MPrintFormat>("AD_PrintFormat", 30);

	/**
	 * 	Get Format
	 * 	@param ctx context
	 * 	@param AD_PrintFormat_ID id
	 *  @param readFromDisk refresh from disk
	 * 	@return Format
	 */
	static public MPrintFormat get (Properties ctx, int AD_PrintFormat_ID, boolean readFromDisk)
	{
		Integer key = new Integer(AD_PrintFormat_ID);
		MPrintFormat pf = null;
		if (!readFromDisk)
			pf = (MPrintFormat)s_formats.get(key);
		if (pf == null)
		{
			pf = new MPrintFormat (ctx, AD_PrintFormat_ID, null);
			if (pf.get_ID() <= 0)
				pf = null;
			else
				s_formats.put(key, pf);
		}

		if (pf != null)
		{
			try {
				pf = pf.clone();
			} catch (CloneNotSupportedException e) {
				throw new RuntimeException(e);
			}
		}
		return pf;
	}	//	get

	/**
	 * 	Get (default) Printformat for Report View or Table
	 *	@param ctx context
	 *	@param AD_ReportView_ID id or 0
	 *	@param AD_Table_ID id or 0
	 *	@return first print format found or null
	 */
	static public MPrintFormat get (Properties ctx, int AD_ReportView_ID, int AD_Table_ID)
	{
		MPrintFormat retValue = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM AD_PrintFormat WHERE ";
		if (AD_ReportView_ID > 0)
			sql += "AD_ReportView_ID=?";
		else
			sql += "AD_Table_ID=?";
		sql += " ORDER BY IsDefault DESC"; 
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, AD_ReportView_ID > 0 ? AD_ReportView_ID : AD_Table_ID);
			rs = pstmt.executeQuery ();
			if (rs.next ())
				retValue = new MPrintFormat (ctx, rs, null);
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql, e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return retValue;
	}	//	get

	/**
	 * 	Delete Format from Cache
	 * 	@param AD_PrintFormat_ID id
	 */
	static public void deleteFromCache (int AD_PrintFormat_ID)
	{
		Integer key = new Integer(AD_PrintFormat_ID);
		s_formats.put(key, null);
	}	//	deleteFromCache
	
    //begin vpj-cd e-evolution
	/**
	 * Get ID of Print Format use Name
	 * @param String formatName
	 * @param AD_Table_ID
	 * @param AD_Client_ID
	 * @return AD_PrintFormat_ID
	 */
	public static int getPrintFormat_ID(String formatName, int AD_Table_ID, int AD_Client_ID) {
		final String sql = "SELECT AD_PrintFormat_ID FROM AD_PrintFormat"
								+ " WHERE Name = ? AND AD_Table_ID = ? AND AD_Client_ID IN (0, ?)"
								+ " ORDER BY AD_Client_ID DESC";
		return DB.getSQLValue(null, sql, formatName, AD_Table_ID, AD_Client_ID);
	}
	//end vpj-cd e-evolution
	
	/**
	 * @param AD_Table_ID
	 * @param AD_Client_ID use -1 to retrieve from all client 
	 * @param trxName
	 */
	public static RowSet getAccessiblePrintFormats (int AD_Table_ID, int AD_Client_ID, String trxName)
	{
		RowSet rowSet = null;
		String sql = "SELECT AD_PrintFormat_ID, Name, AD_Client_ID "
			+ "FROM AD_PrintFormat "
			+ "WHERE AD_Table_ID=? AND IsTableBased='Y' ";
		if (AD_Client_ID >= 0)
		{
			sql = sql + " AND AD_Client_ID = ? ";
		}
		sql = sql + "ORDER BY AD_Client_ID DESC, IsDefault DESC, Name"; //	Own First 
		//
		sql = MRole.getDefault().addAccessSQL (
			sql, "AD_PrintFormat", MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);
		CPreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, AD_Table_ID);
			if (AD_Client_ID >= 0)
				pstmt.setInt(2, AD_Client_ID);
			rowSet = pstmt.getRowSet();
		}
		catch (SQLException e)
		{
			s_log.log(Level.SEVERE, sql, e);
		}
		finally {
			DB.close(pstmt);
			pstmt = null;
		}

		return rowSet;
	}

	@Override
	public MPrintFormat clone() throws CloneNotSupportedException {
		MPrintFormat clone = (MPrintFormat) super.clone();
		clone.m_items = m_items == null ? null : new MPrintFormatItem[m_items.length];
		for(int i = 0; i < m_items.length; i++) {
			clone.m_items[i] = m_items[i];
		}
		clone.m_tFormat = m_tFormat;
		clone.m_language = Env.getLanguage(Env.getCtx());
		clone.m_translationViewLanguage = null;
		return clone;
	}

	/**************************************************************************
	 * 	Test
	 * 	@param args arga
	 */
	static public void main (String[] args)
	{
		org.compiere.Adempiere.startup(true);
		/**
		MPrintFormat.createFromTable(Env.getCtx(), 496);	//	Order
		MPrintFormat.createFromTable(Env.getCtx(), 497);
		MPrintFormat.createFromTable(Env.getCtx(), 516);	//	Invoice
		MPrintFormat.createFromTable(Env.getCtx(), 495);
		MPrintFormat.createFromTable(Env.getCtx(), 500);	//	Shipment
		MPrintFormat.createFromTable(Env.getCtx(), 501);

		MPrintFormat.createFromTable(Env.getCtx(), 498);	//	Check
		MPrintFormat.createFromTable(Env.getCtx(), 499);
		MPrintFormat.createFromTable(Env.getCtx(), 498);	//	Remittance
		**/
	}	//	main


}	//	MPrintFormat
