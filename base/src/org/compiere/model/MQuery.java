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
package org.compiere.model;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.compiere.util.ValueNamePair;

/**
 *	Query Descriptor.
 * 	Maintains restrictions (WHERE clause)
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: MQuery.java,v 1.4 2006/07/30 00:58:04 jjanke Exp $
 * 
 * @author Teo Sarca
 * 		<li>BF [ 2860022 ] MQuery.get() is generating restrictions for non-existent column
 * 			https://sourceforge.net/tracker/?func=detail&aid=2860022&group_id=176962&atid=879332
 * @author  Tony Snook tspc@users.sourceforge.net
 * 		<li>BF [2945715] Improve Advanced Search
 * 			https://sourceforge.net/tracker/index.php?func=detail&aid=2945715&group_id=176962&atid=879335
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<li>BR [ 236 ] Report View does not refresh when print format is changed
 * 			@see https://github.com/adempiere/adempiere/issues/236
 */
public class MQuery implements Serializable
{
	/**
	 *	Get Query from Parameter
	 *	@param ctx context (to determine language)
	 *  @param AD_PInstance_ID instance
	 *  @param TableName table name
	 *  @return where clause
	 */
	static public MQuery get (Properties ctx, int AD_PInstance_ID, String TableName)
	{
		s_log.info("AD_PInstance_ID=" + AD_PInstance_ID + ", TableName=" + TableName);
		MQuery query = new MQuery(TableName);
		//	Temporary Tables - add qualifier (not displayed)
		boolean isTemporaryTable = false;
		MTable table = null;
		table = MTable.get(ctx, TableName);
		if (TableName.startsWith("T_"))
		{
			//	BR [ 236 ]
			query.addRestriction(TableName + ".AD_PInstance_ID=" + AD_PInstance_ID, true);
			isTemporaryTable = true;
		}
		boolean isFinancialReport = ("T_Report".equals(TableName) || "T_ReportStatement".equals(TableName));
		query.m_AD_PInstance_ID = AD_PInstance_ID;

		//	How many rows do we have?
		String SQL = "SELECT COUNT(*) FROM AD_PInstance_Para WHERE AD_PInstance_ID=?";
		int rows = DB.getSQLValue(null, SQL, AD_PInstance_ID);

		if (rows < 1)
			return query;

		//	Msg.getMsg(Env.getCtx(), "Parameter")
		boolean trl = !Env.isBaseLanguage(ctx, "AD_Process_Para");
		if (!trl)
			SQL = "SELECT ip.ParameterName,ip.P_String,ip.P_String_To,"			//	1..3
				+ "ip.P_Number,ip.P_Number_To,"									//	4..5
				+ "ip.P_Date,ip.P_Date_To, ip.Info,ip.Info_To, "				//	6..9
				+ "pp.Name, pp.IsRange "										//	10..11
				+ "FROM AD_PInstance_Para ip, AD_PInstance i, AD_Process_Para pp "
				+ "WHERE i.AD_PInstance_ID=ip.AD_PInstance_ID"
				+ " AND pp.AD_Process_ID=i.AD_Process_ID"
				+ " AND pp.ColumnName=ip.ParameterName"
				+ " AND pp.IsActive='Y'"
				+ " AND ip.AD_PInstance_ID=?";
		else
			SQL = "SELECT ip.ParameterName,ip.P_String,ip.P_String_To, ip.P_Number,ip.P_Number_To,"
				+ "ip.P_Date,ip.P_Date_To, ip.Info,ip.Info_To, "
				+ "ppt.Name, pp.IsRange "
				+ "FROM AD_PInstance_Para ip, AD_PInstance i, AD_Process_Para pp, AD_Process_Para_Trl ppt "
				+ "WHERE i.AD_PInstance_ID=ip.AD_PInstance_ID"
				+ " AND pp.AD_Process_ID=i.AD_Process_ID"
				+ " AND pp.ColumnName=ip.ParameterName"
				+ " AND pp.IsActive='Y'"
				+ " AND pp.AD_Process_Para_ID=ppt.AD_Process_Para_ID"
				+ " AND ip.AD_PInstance_ID=?"
				+ " AND ppt.AD_Language=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(SQL, null);
			pstmt.setInt(1, AD_PInstance_ID);
			if (trl)
				pstmt.setString(2, Env.getAD_Language(ctx));
			rs = pstmt.executeQuery();
			//	all records
			for (int row = 0; rs.next(); row++)
			{
				if (row == rows)
				{
					s_log.log(Level.SEVERE, "(Parameter) - more rows than expected");
					break;
				}
				String ParameterName = rs.getString(1);
				String P_String = rs.getString(2);
				String P_String_To = rs.getString(3);
				//
				Double P_Number = null;
				double d = rs.getDouble(4);
				if (!rs.wasNull())
					P_Number = new Double(d);
				Double P_Number_To = null;
				d = rs.getDouble(5);
				if (!rs.wasNull())
					P_Number_To = new Double(d);
				//
				Timestamp P_Date = rs.getTimestamp(6);
				Timestamp P_Date_To = rs.getTimestamp(7);
				//
				String Info = rs.getString(8);
				String Info_To = rs.getString(9);
				//
				String Name = rs.getString(10);
				boolean isRange = "Y".equals(rs.getString(11));
				//
				s_log.fine(ParameterName + " S=" + P_String + "-" + P_String_To
					+ ", N=" + P_Number + "-" + P_Number_To + ", D=" + P_Date + "-" + P_Date_To
					+ "; Name=" + Name + ", Info=" + Info + "-" + Info_To + ", Range=" + isRange);
				//
				// Check if the parameter exists as column in our table.
				// This condition applies only to temporary tables - teo_sarca [ 2860022 ]
				if (isTemporaryTable && !isFinancialReport && table != null && table.getColumn(ParameterName) == null)
				{
					s_log.info("Skip parameter "+ParameterName+" because there is no column in table "+TableName);
					continue;
				}
				
				if (table != null && table.getColumn(ParameterName) != null)
				{
					MColumn column = table.getColumn(ParameterName);
					if (column != null && !Util.isEmpty(column.getColumnSQL()))
						ParameterName = column.getColumnSQL();
				}

				//-------------------------------------------------------------
				if (P_String != null)
				{
					if (P_String_To == null)
					{
						if (P_String.indexOf('%') == -1)
							query.addRestriction(ParameterName, MQuery.EQUAL, 
								P_String, Name, Info);
						else
							query.addRestriction(ParameterName, MQuery.LIKE, 
								P_String, Name, Info);
					}
					else
						query.addRangeRestriction(ParameterName, 
							P_String, P_String_To, Name, Info, Info_To);
				}
				//	Number
				else if (P_Number != null || P_Number_To != null)
				{
					if (P_Number_To == null)
					{
						if (isRange)
							query.addRestriction(ParameterName, MQuery.GREATER_EQUAL, 
								P_Number, Name, Info);
						else
							query.addRestriction(ParameterName, MQuery.EQUAL, 
								P_Number, Name, Info);
					}
					else	//	P_Number_To != null
					{
						if (P_Number == null)
							query.addRestriction("TRUNC("+ParameterName+")", MQuery.LESS_EQUAL, 
								P_Number_To, Name, Info);
						else
							query.addRangeRestriction(ParameterName, 
								P_Number, P_Number_To, Name, Info, Info_To);
					}
				}
				//	Date
				else if (P_Date != null || P_Date_To != null)
				{
					if (P_Date_To == null)
					{
						if (isRange)
							query.addRestriction("TRUNC("+ParameterName+", 'DD')", MQuery.GREATER_EQUAL, 
								P_Date, Name, Info);
						else
							query.addRestriction("TRUNC("+ParameterName+", 'DD')", MQuery.EQUAL, 
								P_Date, Name, Info);
					}
					else	//	P_Date_To != null
					{
						if (P_Date == null)
							query.addRestriction("TRUNC("+ParameterName+", 'DD')", MQuery.LESS_EQUAL, 
								P_Date_To, Name, Info);
						else
							query.addRangeRestriction("TRUNC("+ParameterName+", 'DD')", 
								P_Date, P_Date_To, Name, Info, Info_To);
					}
				}
			}
		}
		catch (SQLException e2)
		{
			s_log.log(Level.SEVERE, SQL, e2);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		s_log.info(query.toString());
		return query;
	}	//	get
	
	
	/**
	 * Clear all where clause added after instance
	 * BR [ 236 ]
	 */
	public void clear() {
		if(getAD_PInstance_ID() != 0) {
			for (int i = 0; i < m_list.size(); i++) {
				Restriction restriction = m_list.get(i);
				if(!restriction.orignalClause) {
					m_list.remove(i);
				}
			}
		}
	}
	
	/**
	 * 	Get Zoom Column Name.
	 * 	Converts Synonyms like SalesRep_ID to AD_User_ID
	 *	@param columnName column name
	 *	@return column name
	 */
	public static String getZoomColumnName (String columnName)
	{
		if (columnName == null)
			return null;
		if (columnName.equals("SalesRep_ID"))
			return "AD_User_ID";
		if (columnName.equals("C_DocTypeTarget_ID"))
			return "C_DocType_ID";
		if (columnName.equals("Bill_BPartner_ID"))
			return "C_BPartner_ID";
		if (columnName.equals("Bill_Location_ID"))
			return "C_BPartner_Location_ID";
		if (columnName.equals("Account_ID"))
			return "C_ElementValue_ID";
		if (columnName.equals("C_LocFrom_ID") || columnName.equals("C_LocTo_ID"))
			return "C_Location_ID";
		// Fix "*_To" columns
		if (columnName.toUpperCase().endsWith("TO_ID")) {
			return columnName.substring(0, columnName.length()-5)+"_ID";
		}
		if (columnName.toUpperCase().endsWith("_TO_ID")) {
			return columnName.substring(0, columnName.length()-6)+"_ID";
		}
		if (columnName.equals("AD_OrgBP_ID") || columnName.equals("AD_OrgTrx_ID"))
			return "AD_Org_ID";
		//	See also GridTab.validateQuery
		//
		return columnName;
	}	//	getZoomColumnName
	
	/**
	 * 	Derive Zoom Table Name from column name.
	 * 	(e.g. drop _ID)
	 *	@param columnName  column name
	 *	@return table name
	 */
	public static String getZoomTableName (String columnName)
	{
		String tableName = getZoomColumnName(columnName);
		int index = tableName.lastIndexOf("_ID");
		if (index != -1)
			return tableName.substring(0, index);
		return tableName;
	}	//	getZoomTableName

	
	/*************************************************************************
	 * 	Create simple Equal Query.
	 *  Creates columnName=value or columnName='value'
	 * 	@param columnName columnName
	 * 	@param value value
	 * 	@return quary
	 */
	public static MQuery getEqualQuery (String columnName, Object value)
	{
		MQuery query = new MQuery();
		query.addRestriction(columnName, EQUAL, value);
		query.setRecordCount(1);	//	guess
		return query;
	}	//	getEqualQuery

	/**
	 * 	Create simple Equal Query.
	 *  Creates columnName=value
	 * 	@param columnName columnName
	 * 	@param value value
	 * 	@return query
	 */
	public static MQuery getEqualQuery (String columnName, int value)
	{
		MQuery query = new MQuery();
		if (columnName.endsWith("_ID"))
			query.setTableName(columnName.substring(0, columnName.length()-3));
		query.addRestriction(columnName, EQUAL, new Integer(value));
		query.setRecordCount(1);	//	guess
		return query;
	}	//	getEqualQuery

	/**
	 * 	Create No Record query.
	 * 	@param tableName table name
	 * 	@param newRecord new Record Indicator (2=3) 
	 * 	@return query
	 */
	public static MQuery getNoRecordQuery (String tableName, boolean newRecord)
	{
		MQuery query = new MQuery(tableName);
		if (newRecord)
			query.addRestriction(NEWRECORD);
		else
			query.addRestriction("1=2");
		query.setRecordCount(0);
		return query;
	}	//	getNoRecordQuery
	
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MQuery.class);
	
	
	/**************************************************************************
	 *	Constructor w/o table name
	 */
	public MQuery ()
	{
	}	//	MQuery

	/**
	 *	Constructor
	 *  @param TableName Table Name
	 */
	public MQuery (String TableName)
	{
		m_TableName = TableName;
	}	//	MQuery

	/**
	 * 	Constructor get TableNAme from Table
	 * 	@param AD_Table_ID Table_ID
	 */
	public MQuery (int AD_Table_ID)
	{	//	Use Client Context as r/o
		m_TableName = MTable.getTableName (Env.getCtx(), AD_Table_ID);
	}	//	MQuery

	/**	Serialization Info	**/
	private static final long serialVersionUID = 4883859385509199306L;

	/**	Table Name					*/
	private String		m_TableName = "";
	/** PInstance					*/
	private int m_AD_PInstance_ID = 0;
	/** WindowNo					*/
	private int m_windowNo = 0;
	/**	List of Restrictions		*/
	private ArrayList<Restriction>	m_list = new ArrayList<Restriction>();
	/**	Record Count				*/
	private int			m_recordCount = 999999;
	/** New Record Query			*/
	private boolean		m_newRecord = false;
	/** New Record String			*/
	private static final String	NEWRECORD = "2=3";
	
	private String m_zoomTable;
	
	private String m_zoomColumn;
	
	private Object m_zoomValue;

	/**
	 * 	Get Record Count
	 *	@return count - default 999999
	 */
	public int getRecordCount()
	{
		return m_recordCount;
	}	//	getRecordCount
	
	/**
	 * 	Set Record Count
	 *	@param count count
	 */
	public void setRecordCount(int count)
	{
		m_recordCount = count;
	}	//	setRecordCount
	
	
	/** Equal 			*/
	public static final String	EQUAL = "=";
	/** Equal - 0		*/
	public static final int		EQUAL_INDEX = 0;
	/** Not Equal		*/
	public static final String	NOT_EQUAL = "!=";
	/** Not Equal - 1		*/
	public static final int		NOT_EQUAL_INDEX = 1;
	/** Like			*/
	public static final String	LIKE = " LIKE ";
	/** Not Like		*/
	public static final String	NOT_LIKE = " NOT LIKE ";
	/** Greater			*/
	public static final String	GREATER = ">";
	/** Greater Equal	*/
	public static final String	GREATER_EQUAL = ">=";
	/** Less			*/
	public static final String	LESS = "<";
	/** Less Equal		*/
	public static final String	LESS_EQUAL = "<=";
	/** Between			*/
	public static final String	BETWEEN = " BETWEEN ";
	/** Between - 8		*/
	public static final int		BETWEEN_INDEX = 8;
	/** For 	*/
	public static final String 	NOT_NULL = " IS NOT NULL ";
	/** For 	*/
	public static final String 	NULL = " IS NULL ";

	/**	Operators for Strings				*/
	public static final ValueNamePair[]	OPERATORS = new ValueNamePair[] {
		new ValueNamePair (EQUAL,			" = "),		//	0
		new ValueNamePair (NOT_EQUAL,		" != "),
		new ValueNamePair (LIKE,			" ~ "),
		new ValueNamePair (NOT_LIKE,		" !~ "),
		new ValueNamePair (GREATER,			" > "),
		new ValueNamePair (GREATER_EQUAL,	" >= "),	//	5
		new ValueNamePair (LESS,			" < "),
		new ValueNamePair (LESS_EQUAL,		" <= "),
		new ValueNamePair (BETWEEN,			" >-< "),	//	8
		new ValueNamePair (NULL,			" NULL "),
		new ValueNamePair (NOT_NULL,		" !NULL ")
		
	};
	/**	Operators for IDs					*/
	public static final ValueNamePair[]	OPERATORS_ID = new ValueNamePair[] {
		new ValueNamePair (EQUAL,			" = "),		
		new ValueNamePair (NOT_EQUAL,		" != "),
		new ValueNamePair (GREATER,			" > "),
		new ValueNamePair (GREATER_EQUAL,	" >= "),		
		new ValueNamePair (LESS,			" < "),
		new ValueNamePair (LESS_EQUAL,		" <= "),
		new ValueNamePair (BETWEEN,			" >-< ")	
	};
	/**	Operators for Boolean					*/
	public static final ValueNamePair[]	OPERATORS_YN = new ValueNamePair[] {
		new ValueNamePair (EQUAL,			" = ")
	};

	/*************************************************************************
	 * 	Add Restriction
	 * 	@param ColumnName ColumnName
	 * 	@param Operator Operator, e.g. = != ..
	 * 	@param Code Code, e.g 0, All%
	 *  @param InfoName Display Name
	 * 	@param InfoDisplay Display of Code (Lookup)
	 *  @param andCondition true=and, false=or
	 *  @param depth ( = no open brackets )
	 */
	public void addRestriction (String ColumnName, String Operator,
		Object Code, String InfoName, String InfoDisplay, boolean andCondition, int depth)
	{
		Restriction r = new Restriction (ColumnName, Operator,
			Code, InfoName, InfoDisplay, andCondition, depth);
		m_list.add(r);
	}	//	addRestriction
	
	/*************************************************************************
	 * 	Add Restriction
	 * 	@param ColumnName ColumnName
	 * 	@param Operator Operator, e.g. = != ..
	 * 	@param Code Code, e.g 0, All%
	 *  @param InfoName Display Name
	 * 	@param InfoDisplay Display of Code (Lookup)
	 */
	public void addRestriction (String ColumnName, String Operator,
		Object Code, String InfoName, String InfoDisplay)
	{
		Restriction r = new Restriction (ColumnName, Operator,
			Code, InfoName, InfoDisplay, true, 0);
		m_list.add(r);
	}	//	addRestriction

	/**
	 * 	Add Restriction
	 * 	@param ColumnName ColumnName
	 * 	@param Operator Operator, e.g. = != ..
	 * 	@param Code Code, e.g 0, All%
	 */
	public void addRestriction (String ColumnName, String Operator,
		Object Code)
	{
		Restriction r = new Restriction (ColumnName, Operator,
			Code, null, null, true, 0);
		m_list.add(r);
	}	//	addRestriction

	/**
	 * 	Add Restriction
	 * 	@param ColumnName ColumnName
	 * 	@param Operator Operator, e.g. = != ..
	 * 	@param Code Code, e.g 0
	 */
	public void addRestriction (String ColumnName, String Operator,
		int Code)
	{
		Restriction r = new Restriction (ColumnName, Operator,
			new Integer(Code), null, null, true, 0);
		m_list.add(r);
	}	//	addRestriction

	/**
	 * 	Add Range Restriction (BETWEEN)
	 * 	@param ColumnName ColumnName
	 * 	@param Code Code, e.g 0, All%
	 * 	@param Code_to Code, e.g 0, All%
	 *  @param InfoName Display Name
	 * 	@param InfoDisplay Display of Code (Lookup)
	 * 	@param InfoDisplay_to Display of Code (Lookup)
	 *  @param andCondition true=and, false=or
	 *  @param depth ( = no open brackets )
	 */
	public void addRangeRestriction (String ColumnName,
		Object Code, Object Code_to,
		String InfoName, String InfoDisplay, String InfoDisplay_to, boolean andCondition, int depth)
	{
		Restriction r = new Restriction (ColumnName, Code, Code_to,
			InfoName, InfoDisplay, InfoDisplay_to, andCondition, depth);
		m_list.add(r);
	}	//	addRestriction
	
	/**
	 * 	Add Range Restriction (BETWEEN)
	 * 	@param ColumnName ColumnName
	 * 	@param Code Code, e.g 0, All%
	 * 	@param Code_to Code, e.g 0, All%
	 *  @param InfoName Display Name
	 * 	@param InfoDisplay Display of Code (Lookup)
	 * 	@param InfoDisplay_to Display of Code (Lookup)
	 */
	public void addRangeRestriction (String ColumnName,
		Object Code, Object Code_to,
		String InfoName, String InfoDisplay, String InfoDisplay_to)
	{
		Restriction r = new Restriction (ColumnName, Code, Code_to,
			InfoName, InfoDisplay, InfoDisplay_to, true, 0);
		m_list.add(r);
	}	//	addRestriction

	/**
	 * 	Add Range Restriction (BETWEEN)
	 * 	@param ColumnName ColumnName
	 * 	@param Code Code, e.g 0, All%
	 * 	@param Code_to Code, e.g 0, All%
	 */
	public void addRangeRestriction (String ColumnName,
		Object Code, Object Code_to)
	{
		Restriction r = new Restriction (ColumnName, Code, Code_to,
			null, null, null, true, 0);
		m_list.add(r);
	}	//	addRestriction

	/**
	 * 	Add Restriction
	 * 	@param r Restriction
	 */
	protected void addRestriction (Restriction r)
	{
		m_list.add(r);
	}	//	addRestriction

	/**
	 * 	Add Restriction
	 * 	@param whereClause SQL WHERE clause
	 */
	public void addRestriction (String whereClause, boolean andCondition, int joinDepth)
	{
		if (whereClause == null || whereClause.trim().length() == 0)
			return;
		Restriction r = new Restriction (whereClause, andCondition, joinDepth);
		m_list.add(r);
		m_newRecord = whereClause.equals(NEWRECORD);
	}	//	addRestriction
	
	/**
	 * 	Add Restriction
	 * 	@param whereClause SQL WHERE clause
	 */
	public void addRestriction (String whereClause)
	{
		addRestriction(whereClause, false);
	}	//	addRestriction
	
	/**
	 * BR [ 236 ]
	 * Set Original clause
	 * @param whereClause
	 * @param p_OrignalClause
	 */
	public void addRestriction (String whereClause, boolean p_OrignalClause) {
		if (whereClause == null || whereClause.trim().length() == 0)
			return;
		Restriction r = new Restriction (whereClause, true, 0, p_OrignalClause);
		m_list.add(r);
		m_newRecord = whereClause.equals(NEWRECORD);
	}	//	addRestriction

	/**
	 * 	New Record Query
	 *	@return true if new record query
	 */
	public boolean isNewRecordQuery()
	{
		return m_newRecord;
	}	//	isNewRecord
	
	/*************************************************************************
	 * 	Create the resulting Query WHERE Clause
	 * 	@return Where Clause
	 */
	public String getWhereClause ()
	{
		return getWhereClause(false);
	}	//	getWhereClause

	/**
	 * 	Create the resulting Query WHERE Clause
	 * 	@param fullyQualified fully qualified Table.ColumnName
	 * 	@return Where Clause
	 */
	public String getWhereClause (boolean fullyQualified)
	{
		int currentDepth = 0;
		boolean qualified = fullyQualified;
		if (qualified && (m_TableName == null || m_TableName.length() == 0))
			qualified = false;
		//
		StringBuffer sb = new StringBuffer();
		//BF[2945715]
		if (! isActive())
		    return sb.toString();
		
		sb.append('(');
		for (int i = 0; i < m_list.size(); i++)
		{
			Restriction r = (Restriction)m_list.get(i);
			if (i != 0)
				sb.append(r.andCondition ? " AND " : " OR ");
			for ( ; currentDepth < r.joinDepth; currentDepth++ )
			{
				sb.append('(');
			}
			if (qualified)
				sb.append(r.getSQL(m_TableName));
			else
				sb.append(r.getSQL(null));
			
			for ( ; currentDepth > r.joinDepth; currentDepth-- )
			{
				sb.append(')');
			}
		}
		
		// close brackets
		for ( ; currentDepth > 0; currentDepth-- )
		{
			sb.append(')');
		}
		sb.append(')');
		return sb.toString();
	}	//	getWhereClause

	/**
	 * 	Get printable Query Info
	 *	@return info
	 */
	public String getInfo ()
	{
		StringBuffer sb = new StringBuffer();
		int currentDepth = 0;
		if (m_TableName != null)
			sb.append(m_TableName).append(": ");
		//
		for (int i = 0; i < m_list.size(); i++)
		{
			Restriction r = (Restriction)m_list.get(i);
			for ( ; currentDepth < r.joinDepth; currentDepth++ )
			{
				sb.append('(');
			}
			for ( ; currentDepth > r.joinDepth; currentDepth-- )
			{
				sb.append(')');
			}
			if (i != 0)
				sb.append(r.andCondition ? " AND " : " OR ");
			//
			sb.append(r.getInfoName())
				.append(r.getInfoOperator())
				.append(r.getInfoDisplayAll());
		}
		// close brackets
		for ( ; currentDepth > 0; currentDepth-- )
		{
			sb.append(')');
		}
		return sb.toString();
	}	//	getInfo

	
	/**
	 * 	Create Query WHERE Clause.
	 *  Not fully qualified
	 * 	@param index restriction index
	 * 	@return Where Clause or "" if not valid
	 */
	public String getWhereClause (int index)
	{
		StringBuffer sb = new StringBuffer();
		if (index >= 0 && index < m_list.size())
		{
			Restriction r = (Restriction)m_list.get(index);
			sb.append(r.getSQL(null));
		}
		return sb.toString();
	}	//	getWhereClause

	/**
	 * 	Get Restriction Count
	 * 	@return number of restrictions
	 */
	public int getRestrictionCount()
	{
		return m_list.size();
	}	//	getRestrictionCount

	/**
	 * 	Is Query Active
	 * 	@return true if number of restrictions > 0
	 */
	public boolean isActive()
	{
		return m_list.size() != 0;
	}	//	isActive

	/**
	 * 	Get Table Name
	 * 	@return Table Name
	 */
	public String getTableName ()
	{
		return m_TableName;
	}	//	getTableName

	/**
	 * 	Set Table Name
	 * 	@param TableName Table Name
	 */
	public void setTableName (String TableName)
	{
		m_TableName = TableName;
	}	//	setTableName

	
	/*************************************************************************
	 * 	Get ColumnName of index
	 * 	@param index index
	 * 	@return ColumnName
	 */
	public String getColumnName (int index)
	{
		if (index < 0 || index >= m_list.size())
			return null;
		Restriction r = (Restriction)m_list.get(index);
		return r.ColumnName;
	}	//	getColumnName

	/*************************************************************************
	 * 	Get Value of index
	 * 	@param index index
	 * 	@return ColumnName
	 */
	public Object getColumnCode(int index)
	{
		if (index < 0 || index >= m_list.size())
			return null;
		Restriction r = (Restriction)m_list.get(index);
		return r.Code;
	}	//	getColumnName
	
	/**
	 * 	Set ColumnName of index
	 * 	@param index index
	 *  @param ColumnName new column name
	 */
	protected void setColumnName (int index, String ColumnName)
	{
		if (index < 0 || index >= m_list.size())
			return;
		Restriction r = (Restriction)m_list.get(index);
		r.ColumnName = ColumnName;
	}	//	setColumnName

	/**
	 * 	Get Operator of index
	 * 	@param index index
	 * 	@return Operator
	 */
	public String getOperator (int index)
	{
		if (index < 0 || index >= m_list.size())
			return null;
		Restriction r = (Restriction)m_list.get(index);
		return r.Operator;
	}	//	getOperator

	/**
	 * 	Get Operator of index
	 * 	@param index index
	 * 	@return Operator
	 */
	public Object getCode (int index)
	{
		if (index < 0 || index >= m_list.size())
			return null;
		Restriction r = (Restriction)m_list.get(index);
		return r.Code;
	}	//	getCode

	/**
	 * 	Get Restriction Display of index
	 * 	@param index index
	 * 	@return Restriction Display
	 */
	public String getInfoDisplay (int index)
	{
		if (index < 0 || index >= m_list.size())
			return null;
		Restriction r = (Restriction)m_list.get(index);
		return r.InfoDisplay;
	}	//	getOperator

	/**
	 * 	Get TO Restriction Display of index
	 * 	@param index index
	 * 	@return Restriction Display
	 */
	public String getInfoDisplay_to (int index)
	{
		if (index < 0 || index >= m_list.size())
			return null;
		Restriction r = (Restriction)m_list.get(index);
		return r.InfoDisplay_to;
	}	//	getOperator

	/**
	 * 	Get Info Name
	 * 	@param index index
	 * 	@return Info Name
	 */
	public String getInfoName(int index)
	{
		if (index < 0 || index >= m_list.size())
			return null;
		Restriction r = (Restriction)m_list.get(index);
		return r.InfoName;
	}	//	getInfoName

	/**
	 * 	Get Info Operator
	 * 	@param index index
	 * 	@return info Operator
	 */
	public String getInfoOperator(int index)
	{
		if (index < 0 || index >= m_list.size())
			return null;
		Restriction r = (Restriction)m_list.get(index);
		return r.getInfoOperator();
	}	//	getInfoOperator

	/**
	 * 	Get Display with optional To
	 * 	@param index index
	 * 	@return info display
	 */
	public String getInfoDisplayAll (int index)
	{
		if (index < 0 || index >= m_list.size())
			return null;
		Restriction r = (Restriction)m_list.get(index);
		return r.getInfoDisplayAll();
	}	//	getInfoDisplay

	/**
	 * 	String representation
	 * 	@return info
	 */
	public String toString()
	{
		if (isActive())
			return getWhereClause(true);
		return "MQuery[" + m_TableName + ",Restrictions=0]";
	}	//	toString
	
	/**
	 * 	Get Display Name
	 *	@param ctx context
	 *	@return display Name
	 */
	public String getDisplayName(Properties ctx)
	{
		String keyColumn = null;
		if (m_TableName != null)
			keyColumn = m_TableName + "_ID";
		else
			keyColumn = getColumnName(0);
		String retValue = Msg.translate(ctx, keyColumn);
		if (retValue != null && retValue.length() > 0)
			return retValue;
		return m_TableName;
	}	//	getDisplayName

	/**
	 * 	Clone Query
	 * 	@return Query
	 */
	public MQuery deepCopy()
	{
		MQuery newQuery = new MQuery(m_TableName);
		for (int i = 0; i < m_list.size(); i++)
			newQuery.addRestriction((Restriction)m_list.get(i));
		return newQuery;
	}	//	clone

	/**
	 * @return AD_PInstance_ID; this value is set if you created this query by using {@link #get(Properties, int, String)}  
	 */
	public int getAD_PInstance_ID() {
		return m_AD_PInstance_ID;
	}

	/**
	 * 
	 * @param tableName
	 */
	public void setZoomTableName(String tableName) {
		m_zoomTable = tableName;
	}
	
	/**
	 * 
	 * @return zoom table name
	 */
	public String getZoomTableName() {
		return m_zoomTable;
	}

	/**
	 * 
	 * @param column
	 */
	public void setZoomColumnName(String column) {
		m_zoomColumn = column;
	}
	
	/**
	 * 
	 * @return zoom column name
	 */
	public String getZoomColumnName() {
		return m_zoomColumn;
	}

	/**
	 * 
	 * @param value
	 */
	public void setZoomValue(Object value) {
		m_zoomValue = value;
	}
	
	/**
	 * 
	 * @return zoom value, usually an integer
	 */
	public Object getZoomValue() {
		return m_zoomValue;
	}


	public int getWindowNo() {
		return m_windowNo;
	}


	public void setWindowNo(int m_windowNo) {
		this.m_windowNo = m_windowNo;
	}
}	//	MQuery

/*****************************************************************************
 *	Query Restriction
 */
class Restriction  implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4521978087587321243L;

	/**
	 * 	Restriction
	 * 	@param columnName ColumnName
	 * 	@param operator Operator, e.g. = != ..
	 * 	@param code Code, e.g 0, All%
	 *  @param infoName Display Name
	 * 	@param infoDisplay Display of Code (Lookup)
	 */
	Restriction (String columnName, String operator,
		Object code, String infoName, String infoDisplay, boolean andCondition, int depth)
	{
		this.ColumnName = columnName.trim();
		if (infoName != null)
			InfoName = infoName;
		else
			InfoName = ColumnName;

		
		this.andCondition = andCondition;
		this.joinDepth = depth < 0 ? 0 : depth;
		
		//
		this.Operator = operator;
		//	Boolean
		if (code instanceof Boolean)
			Code = ((Boolean)code).booleanValue() ? "Y" : "N";
		else if (code instanceof KeyNamePair)
			Code = new Integer(((KeyNamePair)code).getKey());
		else if (code instanceof ValueNamePair)
			Code = ((ValueNamePair)code).getValue();
		else
			Code = code;
		//	clean code
		if (Code instanceof String)
		{
			if (Code.toString().startsWith("'"))
				Code = Code.toString().substring(1);
			if (Code.toString().endsWith("'"))
				Code = Code.toString().substring(0, Code.toString().length()-2);
		}
		if (infoDisplay != null)
			InfoDisplay = infoDisplay.trim();
		else if (code != null)
			InfoDisplay = code.toString();
		//	Set Default original 
		orignalClause = true;
	}	//	Restriction

	/**
	 * 	Range Restriction (BETWEEN)
	 * 	@param columnName ColumnName
	 * 	@param code Code, e.g 0, All%
	 * 	@param code_to Code, e.g 0, All%
	 *  @param infoName Display Name
	 * 	@param infoDisplay Display of Code (Lookup)
	 * 	@param infoDisplay_to Display of Code (Lookup)
	 */
	Restriction (String columnName,
		Object code, Object code_to,
		String infoName, String infoDisplay, String infoDisplay_to, boolean andCondition, int depth)
	{
		this (columnName, MQuery.BETWEEN, code, infoName, infoDisplay, andCondition, depth);

		//	Code_to
		Code_to = code_to;
		if (Code_to instanceof String)
		{
			if (Code_to.toString().startsWith("'"))
				Code_to = Code_to.toString().substring(1);
			if (Code_to.toString().endsWith("'"))
				Code_to = Code_to.toString().substring(0, Code_to.toString().length()-2);
		}
		//	InfoDisplay_to
		if (infoDisplay_to != null)
			InfoDisplay_to = infoDisplay_to.trim();
		else if (Code_to != null)
			InfoDisplay_to = Code_to.toString();
		//	Set Default original 
		orignalClause = true;
	}	//	Restriction

	/**
	 * 	Create Restriction with direct WHERE clause
	 * 	@param whereClause SQL WHERE Clause
	 * 	@param andCondition AND condition
	 * 	@param depth Depth
	 * 	@param p_OrignalClause Original Clause
	 */
	Restriction (String whereClause, boolean andCondition, int depth, boolean p_OrignalClause)
	{
		DirectWhereClause = whereClause;
		this.andCondition = andCondition;
		this.joinDepth = depth;
		//	Set Default original 
		orignalClause = p_OrignalClause;
	}	//	Restriction
	
	/**
	 * BR [ 236 ]
	 * Standard constructor
	 * @param whereClause
	 * @param andCondition
	 * @param depth
	 */
	Restriction (String whereClause, boolean andCondition, int depth) {
		this(whereClause, andCondition, depth, false);
	}	//	Restriction

	/**	Direct Where Clause	*/
	protected String	DirectWhereClause = null;
	/**	Column Name			*/
	protected String 	ColumnName;
	/** Name				*/
	protected String	InfoName;
	/** Operator			*/
	protected String 	Operator;
	/** SQL Where Code		*/
	protected Object 	Code;
	/** Info				*/
	protected String 	InfoDisplay;
	/** SQL Where Code To	*/
	protected Object 	Code_to;
	/** Info To				*/
	protected String 	InfoDisplay_to;
	/** And/Or Condition	*/
	protected boolean	andCondition = true;
	/** And/Or condition nesting depth ( = number of open brackets at and/or) */
	protected int		joinDepth = 0;
	//	BR [ 237 ]
	/**	Original Parameter	*/
	protected boolean	orignalClause = false;

	/**
	 * 	Return SQL construct for this restriction
	 *  @param tableName optional table name
	 * 	@return SQL WHERE construct
	 */
	public String getSQL (String tableName)
	{
		if (DirectWhereClause != null)
			return DirectWhereClause;
		//
		StringBuffer sb = new StringBuffer();
		if (tableName != null && tableName.length() > 0)
		{
			//	Assumes - REPLACE(INITCAP(variable),'s','X') or UPPER(variable)
			int pos = ColumnName.lastIndexOf('(')+1;	//	including (
			int end = ColumnName.indexOf(')');
			// Column name is already qualified
			if (ColumnName.contains(tableName + "."))
				sb.append(ColumnName);
			//	We have a Function in the ColumnName
			else if (pos != -1 && end != -1)
				sb.append(ColumnName.substring(0, pos))
					.append(tableName).append(".").append(ColumnName.substring(pos, end))
					.append(ColumnName.substring(end));
			else
				sb.append(tableName).append(".").append(ColumnName);
		}
		else
			sb.append(ColumnName);
		
		//	NULL Operator
		if ((Operator.equals("=") || Operator.equals("!="))
			&& (Code == null
				|| "NULL".equals (Code.toString().toUpperCase())))
		{
			if (Operator.equals("="))
				sb.append(" IS NULL ");
			else
				sb.append(" IS NOT NULL ");
		}				
		else
		{
			sb.append(Operator);
			if ( ! (Operator.equals(MQuery.NULL) || Operator.equals(MQuery.NOT_NULL)))
			{
				if (Code instanceof String)
					sb.append(DB.TO_STRING(Code.toString()));
				else if (Code instanceof Timestamp)
					sb.append(DB.TO_DATE((Timestamp)Code));
				else
					sb.append(Code);

				//	Between
				//	if (Code_to != null && InfoDisplay_to != null)
				if (MQuery.BETWEEN.equals(Operator))
				{
					sb.append(" AND ");
					if (Code_to instanceof String)
						sb.append(DB.TO_STRING(Code_to.toString()));
					else if (Code_to instanceof Timestamp)
						sb.append(DB.TO_DATE((Timestamp)Code_to));
					else
						sb.append(Code_to);
				}
			}
		}
		return sb.toString();
	}	//	getSQL

	/**
	 * 	Get String Representation
	 * 	@return info
	 */
	public String toString()
	{
		return getSQL(null);
	}	//	toString

	/**
	 * 	Get Info Name
	 * 	@return Info Name
	 */
	public String getInfoName()
	{
		return InfoName;
	}	//	getInfoName

	/**
	 * 	Get Info Operator
	 * 	@return info Operator
	 */
	public String getInfoOperator()
	{
		for (int i = 0; i < MQuery.OPERATORS.length; i++)
		{
			if (MQuery.OPERATORS[i].getValue().equals(Operator))
				return MQuery.OPERATORS[i].getName();
		}
		return Operator;
	}	//	getInfoOperator

	/**
	 * 	Get Display with optional To
	 * 	@return info display
	 */
	public String getInfoDisplayAll()
	{
		if (InfoDisplay_to == null)
			return InfoDisplay;
		StringBuffer sb = new StringBuffer(InfoDisplay);
		sb.append(" - ").append(InfoDisplay_to);
		return sb.toString();
	}	//	getInfoDisplay

}	//	Restriction
