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

import java.io.Serializable;
import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.TreeMap;
import java.util.logging.Level;

import org.compiere.model.MFactAcct;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MQuery;
import org.compiere.model.MRole;
import org.compiere.model.MSysConfig;
import org.compiere.model.MTable;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Language;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.compiere.util.ValueNamePair;

/**
 * Data Engine.
 * Creates SQL and laods data into PrintData (including totals/etc.)
 *
 * @author 	Jorg Janke
 * @version 	$Id: DataEngine.java,v 1.3 2006/07/30 00:53:02 jjanke Exp $
 * 
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 				<li>BF [ 1761891 ] Included print format with report view attached issue
 * 				<li>BF [ 1807368 ] DataEngine does not close DB connection
 * 				<li>BF [ 2549128 ] Report View Column not working at all
 * 				<li>BF [ 2865545 ] Error if not all parts of multikey are lookups
 * 					https://sourceforge.net/tracker/?func=detail&aid=2865545&group_id=176962&atid=879332
 * @author Teo Sarca, teo.sarca@gmail.com
 * 				<li>BF [ 2876268 ] DataEngine: error on text long fields
 * 					https://sourceforge.net/tracker/?func=detail&aid=2876268&group_id=176962&atid=879332
 * @author victor.perez@e-evolution.com 
 *				<li>FR [ 2011569 ] Implementing new Summary flag in Report View  http://sourceforge.net/tracker/index.php?func=detail&aid=2011569&group_id=176962&atid=879335
 * @author Paul Bowden (phib)
 * 				<li> BF 2908435 Virtual columns with lookup reference types can't be printed
 *                   https://sourceforge.net/tracker/?func=detail&aid=2908435&group_id=176962&atid=879332
 *  @contributor  Fernandinho (FAIRE)
 *  				- http://jira.idempiere.com/browse/IDEMPIERE-153
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<li>BR [ 236 ] Report View does not refresh when print format is changed
 * 			@see https://github.com/adempiere/adempiere/issues/236
 * 		<li>BR [ 237 ] Same Print format but distinct report view
 * 			@see https://github.com/adempiere/adempiere/issues/237
 *		<a href="https://github.com/adempiere/adempiere/issues/1539">
 * 		@see FR [ 1539 ] Add Process for Reporting</a>
 * 		@See: https://github.com/adempiere/adempiere/issues/2873
 * @author Michael McKay, mckayERP@gmail.com
 * 		<li>BR [ <a href="https://github.com/adempiere/adempiere/issues/431">#431</a> ] Report Groups do not handle single values well
 */
public class DataEngine
{
	/**
	 *	Constructor
	 *	@param language Language of the data (for translation)
	 */
	public DataEngine (Language language)
	{
		this(language, null);
	}	//	DataEngine

	/**
	 *	Constructor
	 *	@param language Language of the data (for translation)
	 *  @param trxName
	 */
	public DataEngine (Language language, String trxName){
		if (language != null)
			m_language = language;
		m_trxName = trxName;
	}	//	DataEngine

	/**	Logger							*/
	private static CLogger	log = CLogger.getCLogger (DataEngine.class);

	/** Print format items of the report with their column and report view column */
	private static final String DEFINITION_SQL = "SELECT c.AD_Column_ID,c.ColumnName,"				//	1..2
			+ "c.AD_Reference_ID,c.AD_Reference_Value_ID,"				//	3..4
			+ "c.FieldLength,c.IsMandatory,c.IsKey,c.IsParent,"			//	5..8
			+ "COALESCE(rvc.IsGroupFunction,'N') AS IsGroupFunction, rvc.FunctionColumn,"	//	9..10
			+ "pfi.IsGroupBy,pfi.IsSummarized,pfi.IsAveraged,pfi.IsCounted, "	//	11..14
			+ "pfi.IsPrinted,pfi.SortNo,pfi.IsPageBreak, "				//	15..17
			+ "pfi.IsMinCalc,pfi.IsMaxCalc, "							//	18..19
			+ "pfi.isRunningTotal,pfi.RunningTotalLines, "				//	20..21
			+ "pfi.IsVarianceCalc, pfi.IsDeviationCalc, "				//	22..23
			+ "c.ColumnSQL, COALESCE(pfi.FormatPattern, c.FormatPattern) AS FormatPattern "		//	24, 25
			+ " , pfi.isDesc " //26
			+ " , pfi.SeqNo " //27
			+ ", pfi.AD_PrintFormatItem_ID, pfi.IsHideGrandTotal " // 28
			+ "FROM AD_PrintFormat pf"
			+ " INNER JOIN AD_PrintFormatItem pfi ON (pf.AD_PrintFormat_ID=pfi.AD_PrintFormat_ID)"
			+ " INNER JOIN AD_Column c ON (pfi.AD_Column_ID=c.AD_Column_ID)"
			+ " LEFT OUTER JOIN AD_ReportView_Col rvc ON (pf.AD_ReportView_ID=rvc.AD_ReportView_ID AND c.AD_Column_ID=rvc.AD_Column_ID) "
			+ "WHERE pf.AD_PrintFormat_ID=?"					//	#1
			+ " AND pfi.IsActive='Y' AND (pfi.IsPrinted='Y' OR c.IsKey='Y' OR pfi.SortNo > 0) "
			+ " AND pfi.PrintFormatType IN ('"
				+ MPrintFormatItem.PRINTFORMATTYPE_Field
				+ "','"
				+ MPrintFormatItem.PRINTFORMATTYPE_Image
				+ "','"
				+ MPrintFormatItem.PRINTFORMATTYPE_PrintFormat
				+ "') "
			+ "ORDER BY pfi.IsPrinted DESC, pfi.SeqNo, pfi.AD_PrintFormatItem_ID";


	/**	Synonym							*/
	private	String			m_synonym = "A";

	/**	Default Language				*/
	private Language		m_language = Language.getLoginLanguage();
	/** Break & Column Functions		*/
	private PrintDataGroup 	group = new PrintDataGroup();
	/** The PrintData being printed */
	private PrintData printData = null;
	
	/**	Start Time						*/
	private long			m_startTime = System.currentTimeMillis();
	/** Running Total after .. lines	*/
	private int				runningTotalLines = -1;
	/** Print String					*/
	private String			m_runningTotalString = null;
	/** TrxName String					*/
	private String			m_trxName = null;
	/** Report Summary FR [ 2011569 ]**/ 
	private boolean 		m_summary = false;

	TreeMap<Integer, PrintDataColumn> columnsSortedByGroupOrder = null;
	
	/** Key Indicator in Report			*/
	public static final String KEY = "*";

	private static final String REPORT_DISPLAY_YES_NO = "REPORT_DISPLAY_YES_NO";

	/** SysConfig: Y (default) resolves TableDir lookups with LEFT OUTER JOINs, N keeps the correlated subselects */
	public static final String REPORT_LOOKUP_JOIN = "REPORT_LOOKUP_JOIN";

	/** Override of the REPORT_LOOKUP_JOIN SysConfig, null = use SysConfig */
	private Boolean m_lookupJoinOverride = null;

	/** The report reads a translation view (..._vt), lookups are not joined */
	private boolean m_translationView = false;

	/** SysConfig: Y (default) caches the definition of the reports (print format, items, lookups), N reads the dictionary on every run */
	public static final String REPORT_DEFINITION_CACHE = "REPORT_DEFINITION_CACHE";

	/** Override of the REPORT_DEFINITION_CACHE SysConfig, null = use SysConfig */
	private Boolean m_definitionCacheOverride = null;

	/**
	 * 	Force the report definition cache of this engine, independent of the REPORT_DEFINITION_CACHE SysConfig.
	 * 	@param definitionCache true = use the cache, false = read the dictionary on every run, null = SysConfig
	 * 	@return this engine
	 */
	public DataEngine setDefinitionCache (Boolean definitionCache)
	{
		m_definitionCacheOverride = definitionCache;
		return this;
	}	//	setDefinitionCache

	/**
	 * 	Is the definition of the reports cached?
	 * 	@param ctx context
	 * 	@return true if the report definition cache is used
	 */
	private boolean isDefinitionCache (Properties ctx)
	{
		if (m_definitionCacheOverride != null)
			return m_definitionCacheOverride.booleanValue();
		return MSysConfig.getBooleanValue(REPORT_DEFINITION_CACHE, true, Env.getAD_Client_ID(ctx));
	}	//	isDefinitionCache

	/**
	 * 	Force the lookup strategy of this engine, independent of the REPORT_LOOKUP_JOIN SysConfig.
	 * 	@param lookupJoin true = LEFT OUTER JOINs, false = correlated subselects, null = SysConfig
	 * 	@return this engine
	 */
	public DataEngine setLookupJoin (Boolean lookupJoin)
	{
		m_lookupJoinOverride = lookupJoin;
		return this;
	}	//	setLookupJoin

	/**
	 * 	Are the TableDir lookups of the report resolved with LEFT OUTER JOINs?
	 * 	Not for the report tables (T_Report*, AD_PInstance*) and the translation views.
	 * 	@param ctx context
	 * 	@param tableName table of the report
	 * 	@return true if joins are used
	 */
	private boolean isLookupJoin (Properties ctx, String tableName)
	{
		if (tableName.startsWith("T_Report") || tableName.startsWith("AD_PInstance") || m_translationView)
			return false;
		if (m_lookupJoinOverride != null)
			return m_lookupJoinOverride.booleanValue();
		return MSysConfig.getBooleanValue(REPORT_LOOKUP_JOIN, true, Env.getAD_Client_ID(ctx));
	}	//	isLookupJoin


	/**************************************************************************
	 * 	Load Data
	 *
	 * 	@param format print format
	 * 	@param query query
	 * 	@param ctx context
	 * 	@return PrintData or null
	 */
	public PrintData getPrintData (Properties ctx, MPrintFormat format, MQuery query)
	{
		return getPrintData(ctx, format, query, false, 0);
	}
	
	/**************************************************************************
	 * 	Load Data
	 *
	 * 	@param format print format
	 * 	@param query query
	 * 	@param ctx context
	 *  @param summary
	 *  @param p_AD_ReportView_ID
	 * 	@return PrintData or null
	 */
	public PrintData getPrintData (Properties ctx, MPrintFormat format, MQuery query, boolean summary, int p_AD_ReportView_ID)
	{

		if (format == null)
			throw new IllegalStateException ("No print format");

		//	The definition of the report (dictionary) is read through the report definition cache
		PrintData pd = null;
		try (ReportDefinitionCache.Scope scope = ReportDefinitionCache.open(isDefinitionCache(ctx), m_trxName))
		{
			pd = createPrintData(ctx, format, query, summary, p_AD_ReportView_ID);
		}
		if (pd == null)
			return null;
		loadPrintData(pd, format);
		return pd;
	}	//	getPrintData

	/**
	 * 	Create the PrintData with the SQL of the report, without data
	 * 	@param ctx context
	 * 	@param format print format
	 * 	@param query query
	 * 	@param summary report summary
	 * 	@param p_AD_ReportView_ID report view or 0 for the one of the print format
	 * 	@return PrintData without rows or null
	 */
	private PrintData createPrintData (Properties ctx, MPrintFormat format, MQuery query, boolean summary, int p_AD_ReportView_ID)
	{
		/** Report Summary FR [ 2011569 ]**/ 
		m_summary = !format.isForm() && summary;
		m_translationView = false;
		String tableName = null;
		String reportName = format.getName();
		//	Yamel Senih BR [ 236 ] Clear Query before add new restrictions
		query.clear();
		//	End Yamel Senih
		//	FR [ 237 ]
		if(p_AD_ReportView_ID == 0) {
			p_AD_ReportView_ID = format.getAD_ReportView_ID();
		}
		//	
		if (p_AD_ReportView_ID > 0)
		{
			ReportDefinition.ReportView reportView = getReportView(p_AD_ReportView_ID);
			if (reportView == null && m_reportViewFailed)
				return null;
			if (reportView != null)
			{
				tableName = reportView.tableName;  	//	TableName
				reportName = reportView.name;
				// Add WhereClause restriction from AD_ReportView - teo_sarca BF [ 1761891 ]
				if (!Util.isEmpty(reportView.whereClause))
					query.addRestriction(reportView.whereClause);
			}
		}
		else
		{
			tableName = MTable.getTableName(ctx, format.getAD_Table_ID());
		}
		if (tableName == null)
		{
			log.log(Level.SEVERE, "Not found Format=" + format);
			return null;
		}
		if (format.isTranslationView() && tableName.toLowerCase().endsWith("_v"))
		{
			boolean hasVT = DB.isTableOrViewExists(tableName+"t");
			if (hasVT)
			{
				tableName += "t";
				m_translationView = true;
				format.setTranslationViewQuery (query);
			}
		}
		//
		return getPrintDataInfo (ctx, format, query, reportName, tableName, p_AD_ReportView_ID);
	}	//	createPrintData

	/** The AD_ReportView query failed (an error was logged) */
	private boolean m_reportViewFailed = false;

	/**
	 * 	Get the AD_ReportView of a report with its table: from the report definition cache or from the dictionary
	 * 	@param reportViewId AD_ReportView_ID
	 * 	@return report view or null if it does not exist (or the query failed: {@link #m_reportViewFailed})
	 */
	private ReportDefinition.ReportView getReportView (int reportViewId)
	{
		m_reportViewFailed = false;
		ReportDefinitionCache.Scope scope = ReportDefinitionCache.current();
		List<Object> key = Arrays.<Object>asList("ReportView", Integer.valueOf(reportViewId));
		if (scope != null)
		{
			ReportDefinition.ReportView cached = (ReportDefinition.ReportView)scope.get(key);
			if (cached != null)
				return cached;
		}
		String sql = "SELECT t.AD_Table_ID, t.TableName, rv.Name, rv.WhereClause "
			+ "FROM AD_Table t"
			+ " INNER JOIN AD_ReportView rv ON (t.AD_Table_ID=rv.AD_Table_ID) "
			+ "WHERE rv.AD_ReportView_ID=?";	//	1
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReportDefinition.ReportView reportView = null;
		try
		{				
			pstmt = DB.prepareStatement(sql, m_trxName);
			pstmt.setInt(1, reportViewId);
			rs = pstmt.executeQuery();
			if (rs.next())
				reportView = new ReportDefinition.ReportView(rs.getString(2), rs.getString(3), rs.getString(4));
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
			m_reportViewFailed = true;
			return null;
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		if (scope != null && reportView != null)
			scope.put(key, reportView);
		return reportView;
	}	//	getReportView

	
	/**************************************************************************
	 * 	Create Load SQL and update PrintData Info
	 *
	 * 	@param ctx context
	 * 	@param format print format
	 * 	@param query query
	 *  @param reportName report name
	 *  @param tableName table name
	 * 	@return PrintData or null
	 */
	private PrintData getPrintDataInfo (Properties ctx, MPrintFormat format, MQuery query,
		String reportName, String tableName, int reportViewId)
	{
		m_startTime = System.currentTimeMillis();
		log.info(reportName + " - " + m_language.getAD_Language());
		log.fine("TableName=" + tableName + ", Query=" + query);
		log.fine("Format=" + format);
		group = new PrintDataGroup();

		//	The definition: from the report definition cache, otherwise from the dictionary
		int[] orderAD_Column_IDs = format.getOrderAD_Column_IDs();
		boolean lookupJoin = isLookupJoin(ctx, tableName);
		ReportDefinitionCache.Scope scope = ReportDefinitionCache.current();
		ReportDefinition.Key key = new ReportDefinition.Key(format.get_ID(), tableName, m_language.getAD_Language(),
			reportViewId, m_translationView, lookupJoin, CLogMgt.isLevelFine(), orderAD_Column_IDs);
		ReportDefinition definition = scope == null ? null : (ReportDefinition)scope.get(key);
		if (definition == null)
		{
			definition = readDefinition(ctx, format, tableName, orderAD_Column_IDs, lookupJoin);
			if (scope != null && definition.complete)
				scope.put(key, definition);
		}

		if (definition.columns.size() == 0
				&& format.getJasperProcess_ID() == 0)
		{
			log.log(Level.SEVERE, "No Colums - Delete Report Format " + reportName + " and start again");
			log.finest("No Colums - SQL=" + DEFINITION_SQL + " - ID=" + format.get_ID());
			return null;
		}
		definition.fill(group);
		if (definition.hasRunningTotal)
			runningTotalLines = Math.max(runningTotalLines, definition.runningTotalLines);
		boolean hasLevelNo = definition.hasLevelNo;

		/**
		 *	Assemble final SQL
		 */
		StringBuffer finalSQL = new StringBuffer(definition.head);

		//	WHERE clause
		if (tableName.startsWith("T_Report"))
		{
			finalSQL.append(" WHERE ");
			for (int i = 0; i < query.getRestrictionCount(); i++)
			{
				String q = query.getWhereClause (i);
				if (q.indexOf("AD_PInstance_ID") != -1)	//	ignore all other Parameters
					finalSQL.append (q);
			}	//	for all restrictions
		}
		else if (tableName.startsWith("AD_PInstance"))
		{
			finalSQL.append(" WHERE ");
			for (int i = 0; i < query.getRestrictionCount(); i++)
			{
				String q = query.getWhereClause (i);
				if (q.indexOf("AD_PInstance_ID") != -1)	
				{//	ignore all other Parameters
					q=q.substring(9);
						finalSQL.append (q);
				}
			}	//	for all restrictions
		}
		else
		{
			//	User supplied Where Clause
			if (query != null && query.isActive ())
			{
				finalSQL.append (" WHERE ");
				if (!query.getTableName ().equals (tableName))
					query.setTableName (tableName);
				finalSQL.append (query.getWhereClause (true));
			}
			//	Access Restriction
			MRole role = MRole.getDefault(ctx, false);
			if (role.getAD_Role_ID() == 0 && !Ini.isClient())
				;	//	System Access
			else
				finalSQL = new StringBuffer (role.addAccessSQL (finalSQL.toString (), 
					tableName, MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO));
		}

		//	GROUP BY, the lookups joined to the statement of the report, ORDER BY and the tag of the report for the DBA
		String finalStatement = definition.finish(finalSQL.toString());

		//	Print Data
		PrintData pd = new PrintData (ctx, reportName);
		PrintDataColumn[] info = definition.newColumns();	//	column order is in sync with SELECT column position
		pd.setColumnInfo(info);
		pd.setTableName(tableName);
		pd.setSQL(finalStatement);
		pd.setHasLevelNo(hasLevelNo);
		if (definition.tableIDRequired && !definition.tableIDPresent) {
			pd.setHasDummyTableID(true);
		}
		
		log.finest (finalStatement);
		log.finest ("Group=" + group);
		return pd;
	}	//	getPrintDataInfo

	/**
	 * 	Read the definition of the report from the dictionary: columns, group and function specs, the lookups
	 * 	(joined or embedded) and the fragments of the statement that do not depend on the user, role or query.
	 * 	@param ctx context
	 * 	@param format print format
	 * 	@param tableName table name
	 * 	@param orderAD_Column_IDs AD_Column_IDs in sort order
	 * 	@param lookupJoin the TableDir lookups are joined
	 * 	@return immutable definition
	 */
	private ReportDefinition readDefinition (Properties ctx, MPrintFormat format, String tableName,
		int[] orderAD_Column_IDs, boolean lookupJoin)
	{
		ReportDefinition.Builder b = new ReportDefinition.Builder();
		b.tableName = tableName;
		b.lookupJoin = lookupJoin;

		//	Order Columns (identified by non zero/null SortNo)
		ArrayList<String> orderColumns = new ArrayList<String>(orderAD_Column_IDs.length);
		for (int i = 0; i < orderAD_Column_IDs.length; i++)
		{
			log.finest("Order AD_Column_ID=" + orderAD_Column_IDs[i]);
			orderColumns.add("");		//	initial value overwritten with fully qualified name
		}

		//	Direct SQL w/o Reference Info
		StringBuffer sqlSELECT = new StringBuffer("SELECT ");
		//	Items of the SELECT list, the lookups of the report are joined when the plan is set
		List<ReportLookupItem> items = new ArrayList<ReportLookupItem>();
		ReportLookupPlan lookupPlan = lookupJoin ? new ReportLookupPlan(m_language, tableName) : null;
		StringBuffer sqlFROM = new StringBuffer(" FROM ").append(tableName);
		ArrayList<String> groupByColumns = new ArrayList<String>();
		
		//Activate when Line_ID or Record_ID is present
		boolean isTableIDRequired = false;
		boolean isTableIDPresent = false;
		//
		boolean IsGroupedBy = false;
		//
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(DEFINITION_SQL, m_trxName);
			pstmt.setInt(1, format.get_ID());
			rs = pstmt.executeQuery();

			m_synonym = "A";		//	synonym
			while (rs.next())
			{
				//	get Values from record
				int columnId = rs.getInt("AD_Column_ID");
				String columnName = rs.getString("ColumnName");
				String columnSQL = rs.getString("ColumnSQL");
				if (columnSQL == null)
					columnSQL = "";
				int referenceId = rs.getInt("AD_Reference_ID");
				int referenceValueId = rs.getInt("AD_Reference_Value_ID");
				//  ColumnInfo
				int fieldLength = rs.getInt("FieldLength");
				boolean isMandatory = "Y".equals(rs.getString("IsMandatory"));
				boolean isKey = "Y".equals(rs.getString("IsKey"));
				boolean isParent = "Y".equals(rs.getString("IsParent"));
				//  SQL GroupBy
				boolean isGroupFunction = "Y".equals(rs.getString("IsGroupFunction"));
				if (isGroupFunction)
					IsGroupedBy = true;
				String functionColumn = rs.getString("FunctionColumn");
				if (functionColumn == null)
					functionColumn = "";
				//	Breaks/Column Functions
				if ("Y".equals(rs.getString("IsGroupBy")))
					b.groups.add(new ReportDefinition.GroupSpec(columnName, (char)0));
				if ("Y".equals(rs.getString("IsSummarized")))
					b.groups.add(new ReportDefinition.GroupSpec(columnName, PrintDataFunction.F_SUM));
				if ("Y".equals(rs.getString("IsAveraged")))
					b.groups.add(new ReportDefinition.GroupSpec(columnName, PrintDataFunction.F_MEAN));
				if ("Y".equals(rs.getString("IsCounted")))
					b.groups.add(new ReportDefinition.GroupSpec(columnName, PrintDataFunction.F_COUNT));
				if ("Y".equals(rs.getString("IsMinCalc")))	//	IsMinCalc
					b.groups.add(new ReportDefinition.GroupSpec(columnName, PrintDataFunction.F_MIN));
				if ("Y".equals(rs.getString("IsMaxCalc")))	//	IsMaxCalc
					b.groups.add(new ReportDefinition.GroupSpec(columnName, PrintDataFunction.F_MAX));
				if ("Y".equals(rs.getString("IsVarianceCalc")))	//	IsVarianceCalc
					b.groups.add(new ReportDefinition.GroupSpec(columnName, PrintDataFunction.F_VARIANCE));
				if ("Y".equals(rs.getString("IsDeviationCalc")))	//	IsDeviationCalc
					b.groups.add(new ReportDefinition.GroupSpec(columnName, PrintDataFunction.F_DEVIATION));
				if ("Y".equals(rs.getString("isRunningTotal")))	//	isRunningTotal
					//	RunningTotalLines only once - use max
					{
						b.hasRunningTotal = true;
						b.runningTotalLines = Math.max(b.runningTotalLines, rs.getInt("RunningTotalLines"));
					}	

				if (columnName.equalsIgnoreCase(MFactAcct.COLUMNNAME_Line_ID) 
						|| columnName.equalsIgnoreCase(MFactAcct.COLUMNNAME_Record_ID)
						) {
					isTableIDRequired = true;
				}
				
				if (columnName.equalsIgnoreCase(MFactAcct.COLUMNNAME_AD_Table_ID)) {
					isTableIDPresent = true;
				}				
				//	General Info
				boolean IsPrinted = "Y".equals(rs.getString("IsPrinted"));
				int SortNo = rs.getInt(16);
				boolean isPageBreak = "Y".equals(rs.getString("IsPageBreak"));
				
				String formatPattern = rs.getString("FormatPattern");
				boolean isDesc = "Y".equals(rs.getString("isDesc"));
				int printFormatItemId = rs.getInt("AD_PrintFormatItem_ID");
				boolean isHideGrandTotal = "Y".equals(rs.getString("IsHideGrandTotal"));
				//	Fully qualified Table.Column for ordering
				String orderName = tableName + "." + columnName;
				String lookupSQL = orderName;
				PrintDataColumn pdc = null;

				int seqNo = rs.getInt("SeqNo");

				//  -- Key --
				if (isKey)
				{
					//	=>	Table.Column,
					items.add(new ReportLookupItem(tableName + "." + columnName, null, columnName));
					groupByColumns.add(tableName+"."+columnName);
					pdc = new PrintDataColumn(columnId, columnName, referenceId, fieldLength, KEY, isPageBreak, printFormatItemId, isHideGrandTotal);	//	KeyColumn
				}
				// not printed Sort Columns
				else if (!IsPrinted)
				{
					;
				}
				//	-- Parent, TableDir (and unqualified Search) --
				else if (referenceId == DisplayType.TableDir
						|| (referenceId == DisplayType.Search && referenceValueId == 0)
					)
				{

					//  Creates Embedded SQL in the form
					//  SELECT ColumnTable.Name FROM ColumnTable WHERE TableName.ColumnName=ColumnTable.ColumnName
					if (columnSQL.length() > 0)
						lookupSQL = columnSQL;

					//	TableName
					String table = columnName;
					if (table.endsWith("_ID"))
						table = table.substring(0, table.length()-3);
					//  DisplayColumn
					String display = columnName;
					ReportLookupItem foreignKey = new ReportLookupItem(lookupSQL, columnName, columnName);
					ReportLookupItem displayItem = null;
					//	=> LEFT OUTER JOIN lookup, CASE .. END AS AName, Table.ID,
					if (lookupPlan != null)
					{
						ReportLookupItem joined = new ReportLookupItem(null, m_synonym + display, m_synonym + display);
						{
							MLookupFactory.TableDirEmbed embed = MLookupFactory.getTableDirEmbed(m_language, columnName);
							if (embed == null)
								b.complete = false;
							if (lookupPlan.lookup(joined, foreignKey, embed))
								displayItem = joined;
						}
					}
					//	=> (..) AS AName, Table.ID,
					if (displayItem == null)
					{
						String eSql;
						if (columnSQL.length() > 0)
							eSql = MLookupFactory.getLookup_TableDirEmbed(m_language, columnName, tableName, "(" + columnSQL + ")");
						else
							eSql = MLookupFactory.getLookup_TableDirEmbed(m_language, columnName, tableName);
						if (eSql == null || eSql.length() == 0)
							b.complete = false;
						displayItem = new ReportLookupItem("(" + eSql + ")", m_synonym + display, m_synonym + display);
					}
					items.add(displayItem);
					items.add(foreignKey);
					groupByColumns.add(lookupSQL);
					orderName = m_synonym + display;
					//
					pdc = new PrintDataColumn(columnId, columnName, referenceId, fieldLength, orderName, isPageBreak, printFormatItemId, isHideGrandTotal);
					synonymNext();
				}

				//	-- Table --
				else if (referenceId == DisplayType.Table
						|| (referenceId == DisplayType.Search && referenceValueId != 0)
					)
				{
					if (columnSQL.length() > 0)
					{
						lookupSQL = columnSQL;
					}
					if (referenceValueId <= 0)
					{
						log.warning(columnName + " - AD_Reference_Value_ID not set");
						continue;
					}
					TableReference tr = getTableReference(referenceValueId);
					if (tr.TableName == null)
						b.complete = false;
					String display = tr.DisplayColumn;
					//	=> A.Name AS AName, Table.ID,
					items.add(new ReportLookupItem((tr.IsValueDisplayed ? m_synonym + ".Value||'-'||" : "")
						+ m_synonym + "." + display, m_synonym + display, m_synonym + display));
					items.add(new ReportLookupItem(lookupSQL, columnName, columnName));
					groupByColumns.add(m_synonym+display);
					groupByColumns.add(lookupSQL);
					orderName = m_synonym + display;

					//	=> x JOIN table A ON (x.KeyColumn=A.Key)
					if (isMandatory)
						sqlFROM.append(" INNER JOIN ");
					else
						sqlFROM.append(" LEFT OUTER JOIN ");
					sqlFROM.append(tr.TableName).append(" ").append(m_synonym).append(" ON (")
						.append(lookupSQL).append("=")
						.append(m_synonym).append(".").append(tr.KeyColumn).append(")");
					//
					pdc = new PrintDataColumn(columnId, columnName, referenceId, fieldLength, orderName, isPageBreak, printFormatItemId, isHideGrandTotal);
					synonymNext();
				}

				//	-- List or Button with ReferenceValue --
				else if (referenceId == DisplayType.List 
					|| (referenceId == DisplayType.Button && referenceValueId != 0))
				{
					if (columnSQL.length() > 0)
					{
						lookupSQL = columnSQL;
					}
					if (Env.isBaseLanguage(m_language, "AD_Ref_List"))
					{
						//	=> A.Name AS AName,
						items.add(new ReportLookupItem(m_synonym + ".Name", m_synonym + "Name", m_synonym + "Name"));
						groupByColumns.add(m_synonym+".Name");
						orderName = m_synonym + "Name";
						//	=> x JOIN AD_Ref_List A ON (x.KeyColumn=A.Value AND A.AD_Reference_ID=123)
						if (isMandatory)
							sqlFROM.append(" INNER JOIN ");
						else
							sqlFROM.append(" LEFT OUTER JOIN ");
						sqlFROM.append("AD_Ref_List ").append(m_synonym).append(" ON (")
							.append(lookupSQL).append("=").append(m_synonym).append(".Value")
							.append(" AND ").append(m_synonym).append(".AD_Reference_ID=").append(referenceValueId).append(")");
					}
					else
					{
						//	=> A.Name AS AName,
						items.add(new ReportLookupItem(m_synonym + ".Name", m_synonym + "Name", m_synonym + "Name"));
						groupByColumns.add(m_synonym+".Name");
						orderName = m_synonym + "Name";

						//	LEFT OUTER JOIN AD_Ref_List XA ON (AD_Table.EntityType=XA.Value AND XA.AD_Reference_ID=245)
						//	LEFT OUTER JOIN AD_Ref_List_Trl A ON (XA.AD_Ref_List_ID=A.AD_Ref_List_ID AND A.AD_Language='de_DE')
						if (isMandatory)
							sqlFROM.append(" INNER JOIN ");
						else
							sqlFROM.append(" LEFT OUTER JOIN ");
						sqlFROM.append(" AD_Ref_List X").append(m_synonym).append(" ON (")
							.append(lookupSQL).append("=X")
							.append(m_synonym).append(".Value AND X").append(m_synonym).append(".AD_Reference_ID=").append(referenceValueId)
							.append(")");
						if (isMandatory)
							sqlFROM.append(" INNER JOIN ");
						else
							sqlFROM.append(" LEFT OUTER JOIN ");
						sqlFROM.append(" AD_Ref_List_Trl ").append(m_synonym).append(" ON (X")
							.append(m_synonym).append(".AD_Ref_List_ID=").append(m_synonym).append(".AD_Ref_List_ID")
							.append(" AND ").append(m_synonym).append(".AD_Language='").append(m_language.getAD_Language()).append("')");
					}
					// 	TableName.ColumnName,
					items.add(new ReportLookupItem(lookupSQL, columnName, columnName));
					pdc = new PrintDataColumn(columnId, columnName, referenceId, fieldLength, orderName, isPageBreak, printFormatItemId, isHideGrandTotal);
					synonymNext();
				}

				//  -- Special Lookups --
				else if (referenceId == DisplayType.Location
					|| referenceId == DisplayType.Account
					|| referenceId == DisplayType.Locator
					|| referenceId == DisplayType.PAttribute
				)
				{
					if (columnSQL.length() > 0)
					{
						lookupSQL = columnSQL;
					}
					//	TableName, DisplayColumn
					String table = ""; 
					String key = ""; 
					String display = ""; 
					String synonym = null;
					//
					if (referenceId == DisplayType.Location)
					{
						table = "C_Location";
						key = "C_Location_ID";
						display = "City||'.'";	//	in case City is empty
						synonym = "Address";
					}
					else if (referenceId == DisplayType.Account)
					{
						table = "C_ValidCombination";
						key = "C_ValidCombination_ID";
						display = "Combination";
					}
					else if (referenceId == DisplayType.Locator)
					{
						table = "M_Locator";
						key = "M_Locator_ID";
						display = "Value";
					}
					else if (referenceId == DisplayType.PAttribute)
					{
						table = "M_AttributeSetInstance";
						key = "M_AttributeSetInstance_ID";
						display = "Description";
						if (CLogMgt.isLevelFine())
							display += "||'{'||" + m_synonym + ".M_AttributeSetInstance_ID||'}'";
						synonym = "Description";
					}
					if (synonym == null)
						synonym = display;

					//	=> A.Name AS AName, table.ID,
					items.add(new ReportLookupItem(m_synonym + "." + display, m_synonym + synonym, m_synonym + synonym));
					items.add(new ReportLookupItem(lookupSQL, columnName, columnName));
					groupByColumns.add(m_synonym+"."+synonym);
					groupByColumns.add(lookupSQL);
					orderName = m_synonym + synonym;
					//	=> x JOIN table A ON (table.ID=A.Key)
					if (isMandatory)
						sqlFROM.append(" INNER JOIN ");
					else
						sqlFROM.append(" LEFT OUTER JOIN ");
					sqlFROM.append(table).append(" ").append(m_synonym).append(" ON (")
						.append(lookupSQL).append("=")
						.append(m_synonym).append(".").append(key).append(")");
					//
					pdc = new PrintDataColumn(columnId, columnName, referenceId, fieldLength, orderName, isPageBreak, printFormatItemId, isHideGrandTotal);
					synonymNext();
				}

				//	-- Standard Column --
				else
				{
					int index = functionColumn.indexOf('@');
					if (columnSQL != null && columnSQL.length() > 0)
					{
					//	=> ColumnSQL AS ColumnName
						items.add(new ReportLookupItem(columnSQL, columnName, columnName));
						if (!isGroupFunction)
							groupByColumns.add(columnSQL);
						orderName = columnName;		//	no prefix for synonym
					}
					else if (index == -1)
					{
					//	=> Table.Column,
						StringBuffer sb = new StringBuffer();
						sb.append(tableName).append(".").append(columnName);
						items.add(new ReportLookupItem(sb.toString(), null, columnName));
						if (!isGroupFunction)
							groupByColumns.add(sb.toString());
					}
					else
					{
					//  => Function(Table.Column) AS Column   -- function has @ where column name goes
						StringBuffer sb = new StringBuffer();
						sb.append(functionColumn.substring(0, index))
							.append(tableName).append(".").append(columnName)
							.append(functionColumn.substring(index+1));
						items.add(new ReportLookupItem(sb.toString(), columnName, columnName));
						if (!isGroupFunction)
							groupByColumns.add(sb.toString());
						orderName = columnName;		//	no prefix for synonym
					}
					pdc = new PrintDataColumn(columnId, columnName, referenceId, fieldLength, columnName, isPageBreak, printFormatItemId, isHideGrandTotal);
				}

				//	Order Sequence - Overwrite order column name
				for (int i = 0; i < orderAD_Column_IDs.length; i++)
				{
					if (columnId == orderAD_Column_IDs[i])
					{
						if (isDesc)
							orderName += " DESC";

						orderColumns.set(i, orderName);
						// We need to GROUP BY even is not printed, because is used in ORDER clause
						if (!IsPrinted && !isGroupFunction)
						{
							groupByColumns.add(tableName+"."+columnName);
						}
						//	the sort column is selected by the derived table to be sorted by the outer SELECT
						if (!IsPrinted)
							items.add(new ReportLookupItem(tableName + "." + columnName, null, columnName, true));
						break;
					}
				}

				//
				if (pdc == null || (!IsPrinted && !isKey))
					continue;

				pdc.setFormatPattern(formatPattern);
				pdc.setSortOrderIndex(SortNo);
				pdc.setDisplayOrderIndex(seqNo);
				b.columns.add(new ReportDefinition.Column(pdc, fieldLength));
			}	//	for all Fields in Tab
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, "SQL=" + DEFINITION_SQL + " - ID=" + format.get_ID(), e);
			b.complete = false;
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}


		if (b.columns.size() == 0)
			b.complete = false;

		//	Table ID PrintColumn is needed but not present, manually add a dummy column
		boolean addTableID = isTableIDRequired && !isTableIDPresent;
		boolean lookupJoined = lookupPlan != null && !lookupPlan.isEmpty();
		if (lookupJoined)
		{
			//	derived table with the statement of the report, the lookups are joined to it
			if (addTableID)
				items.add(new ReportLookupItem(tableName + "." + MFactAcct.COLUMNNAME_AD_Table_ID, null, MFactAcct.COLUMNNAME_AD_Table_ID));
			sqlSELECT.append(lookupPlan.innerSelect(items)).append(",");
		}
		else
		{
			for (ReportLookupItem item : items)
			{
				if (!item.hidden)
					sqlSELECT.append(item.legacyText()).append(",");
			}
		}

		boolean hasLevelNo = false;
		if (tableName.startsWith("T_Report"))
		{
			hasLevelNo = true;
			if (sqlSELECT.indexOf("LevelNo") == -1)
				sqlSELECT.append("LevelNo,");
			
			if ( tableName.equals("T_Report") &&
					sqlSELECT.indexOf("PA_ReportLine_ID") == -1)
				sqlSELECT.append("PA_ReportLine_ID,");
		}

		//Table ID PrintColumn is needed but not present, manually add a dummy column
		if (addTableID && !lookupJoined) {
			sqlSELECT.append(tableName).append(".").append(MFactAcct.COLUMNNAME_AD_Table_ID).append(",");
		}
		
		//	SELECT list and FROM - delete last SELECT ','
		b.head = new StringBuffer().append(sqlSELECT.substring(0, sqlSELECT.length()-1)).append(sqlFROM).toString();
		b.hasLevelNo = hasLevelNo;
		b.tableIDRequired = isTableIDRequired;
		b.tableIDPresent = isTableIDPresent;
		b.lookupJoined = lookupJoined;

		//	Add GROUP BY clause
		if (IsGroupedBy)
		{
			StringBuffer groupBy = new StringBuffer();
			for (int i = 0; i < groupByColumns.size(); i++)
			{
				if (i == 0)
					groupBy.append(" GROUP BY ");
				else
					groupBy.append(",");
				groupBy.append(groupByColumns.get(i));
			}
			b.groupBy = groupBy.toString();
		}

		//	Lookups joined to the statement of the report
		if (lookupJoined)
		{
			b.outerPrefix = lookupPlan.outerPrefix(items);
			b.outerSuffix = lookupPlan.outerSuffix();
		}

		//	Add ORDER BY clause
		StringBuffer orderBy = new StringBuffer();
		for (int i = 0; i < orderColumns.size(); i++)
		{
			if (i == 0)
				orderBy.append(" ORDER BY ");
			else
				orderBy.append(",");
			String by = (String)orderColumns.get(i);
			if (by == null || by.length() == 0)
				by = String.valueOf(i+1);
			orderBy.append(by);
		}	//	order by
		b.orderBy = orderBy.toString();

		//	Tag of the report for the DBA
		if (lookupJoin)
			b.tag = "/* AD_PrintFormat_ID=" + format.get_ID() + " */ ";

		return b.build();
	}	//	readDefinition

	/**
	 *	Next Synonym.
	 * 	Creates next synonym A..Z AA..ZZ AAA..ZZZ
	 */
	private void synonymNext()
	{
		int length = m_synonym.length();
		char cc = m_synonym.charAt(0);
		if (cc == 'Z')
		{
			cc = 'A';
			length++;
		}
		else
			cc++;
		//
		m_synonym = String.valueOf(cc);
		if (length == 1)
			return;
		m_synonym += String.valueOf(cc);
		if (length == 2)
			return;
		m_synonym += String.valueOf(cc);
	}	//	synonymNext

	/**
	 *	Get TableName and ColumnName for Reference Tables.
	 *  @param AD_Reference_Value_ID reference value
	 *	@return 0=TableName, 1=KeyColumn, 2=DisplayColumn
	 */
	public static TableReference getTableReference (int AD_Reference_Value_ID)
	{
		if (AD_Reference_Value_ID <= 0)
			throw new IllegalArgumentException("AD_Reference_Value_ID <= 0");
		//
		//	The reference of a report run is read once: a copy is returned, TableReference is mutable
		ReportDefinitionCache.Scope scope = ReportDefinitionCache.current();
		List<Object> key = Arrays.<Object>asList("TableReference", Integer.valueOf(AD_Reference_Value_ID));
		if (scope != null)
		{
			TableReference cached = (TableReference)scope.get(key);
			if (cached != null)
				return cached.copy();
		}
		TableReference tr = new TableReference();
		//
		String SQL = "SELECT t.TableName, ck.ColumnName AS KeyColumn,"	//	1..2
			+ " cd.ColumnName AS DisplayColumn, rt.IsValueDisplayed, cd.IsTranslated "
			+ "FROM AD_Ref_Table rt"
			+ " INNER JOIN AD_Table t ON (rt.AD_Table_ID = t.AD_Table_ID)"
			+ " INNER JOIN AD_Column ck ON (rt.AD_Key = ck.AD_Column_ID)"
			+ " INNER JOIN AD_Column cd ON (rt.AD_Display = cd.AD_Column_ID) "
			+ "WHERE rt.AD_Reference_ID=?"			//	1
			+ " AND rt.IsActive = 'Y' AND t.IsActive = 'Y'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(SQL, null);
			pstmt.setInt (1, AD_Reference_Value_ID);
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				tr.TableName = rs.getString(1);
				tr.KeyColumn = rs.getString(2);
				tr.DisplayColumn = rs.getString(3);
				tr.IsValueDisplayed = "Y".equals(rs.getString(4));
				tr.IsTranslated = "Y".equals(rs.getString(5));
			}
		}
		catch (SQLException ex)
		{
			log.log(Level.SEVERE, SQL, ex);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		if (scope != null && tr.TableName != null)
			scope.put(key, tr.copy());
		return tr;
	}	//  getTableReference

	
	/**************************************************************************
	 * 	Load Data into PrintData
	 * 	@param pd print data with SQL and ColumnInfo set
	 *  @param format print format
	 */
	private void loadPrintData (PrintData pd, MPrintFormat format)
	{
		//	Translate Spool Output
		boolean translateSpool = pd.getTableName().equals("T_Spool");
		m_runningTotalString = Msg.getMsg(format.getLanguage(), "RunningTotal");
		int rowNo = 0;
		PrintDataColumn pdc = null;
		boolean hasLevelNo = pd.hasLevelNo();
		int levelNo = 0;
		int reportLineId = 0;
		log.log(Level.FINE, "SQL: " +pd.getSQL());
		printData = pd;
		//
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(pd.getSQL(), m_trxName);
			rs = pstmt.executeQuery();
			//	Row Loop
			while (rs.next())
			{
				if (hasLevelNo)
				{
					levelNo = rs.getInt("LevelNo");
					if(pd.getTableName().equals("T_Report"))
						reportLineId = rs.getInt("PA_ReportLine_ID");
				}
				else
					levelNo = 0;
				//	Check Group Change ----------------------------------------
				if (group.getGroupColumnCount() > 1)	//	one is GRANDTOTAL_
				{
					int firstColumn = getFirstColumnIndexInGroupOrder();
					rowNo = checkGroupChange(firstColumn, pd, rs, false, levelNo, rowNo); // Check for changes and add summary rows
				}	//	group change

				//	new row ---------------------------------------------------
				printRunningTotal(pd, levelNo, rowNo++);

				/** Report Summary FR [ 2011569 ]**/ 
				if(!m_summary)
					pd.addRow(false, levelNo, reportLineId);

				int counter = 1;
				//	get columns
				for (int i = 0; i < pd.getColumnInfo().length; i++)
				{
					pdc = pd.getColumnInfo()[i];
					PrintDataElement pde = null;

					//	Key Column - No DisplayColumn
					if (pdc.getAlias().equals(KEY))
					{
						if (pdc.getColumnName().endsWith("_ID"))
						{
						//	int id = rs.getInt(pdc.getColumnIDName());
							int id = rs.getInt(counter++);
							if (!rs.wasNull())
							{
								KeyNamePair pp = new KeyNamePair(id, KEY);	//	Key
								pde = new PrintDataElement(pdc.getColumnName(), pp, pdc.getDisplayType(),
										true, pdc.isPageBreak(), pdc.getFormatPattern());
							}
						}
						else
						{
						//	String id = rs.getString(pdc.getColumnIDName());
							String id = rs.getString(counter++);
							if (!rs.wasNull())
							{
								ValueNamePair pp = new ValueNamePair(id, KEY);	//	Key
								pde = new PrintDataElement(pdc.getColumnName(), pp, pdc.getDisplayType(),
										true, pdc.isPageBreak(), pdc.getFormatPattern());
							}
						}
					}
					//	Non-Key Column
					else
					{
						//	Display and Value Column
						if (pdc.hasAlias())
						{
							//	DisplayColumn first
							String display = rs.getString(counter++);
							if (pdc.getColumnName().endsWith("_ID"))
							{
								int id = rs.getInt(counter++);
								if (display != null && !rs.wasNull())
								{
									KeyNamePair pp = new KeyNamePair(id, display);
									pde = new PrintDataElement(pdc.getColumnName(), pp, pdc.getDisplayType(), pdc.getFormatPattern());
								}
							}
							else
							{
								String id = rs.getString(counter++);
								if (display != null && !rs.wasNull())
								{
									ValueNamePair pp = new ValueNamePair(id, display);
									pde = new PrintDataElement(pdc.getColumnName(), pp, pdc.getDisplayType(), pdc.getFormatPattern());
								}
							}
						}
						//	Display Value only
						else
						{
							//	Transformation for Booleans
							if (pdc.getDisplayType() == DisplayType.YesNo)
							{
								String displayAs = MSysConfig.getValue(REPORT_DISPLAY_YES_NO, "text", Env.getAD_Client_ID(pd.getCtx()));
								String s = rs.getString(counter++);
								if (!rs.wasNull())
								{
									Serializable value = null;
									boolean b = s.equals("Y");
									if ( "symbol".equalsIgnoreCase(displayAs) )
									{
										value = b ? "\u2714" : "\u2718";
									}
									else if ( "image".equalsIgnoreCase(displayAs) )
									{
										value = Boolean.valueOf(b);
								}
									else  // default text
									{
										value = b ? "Y" : "N";
							}
									pde = new PrintDataElement(pdc.getColumnName(), value, pdc.getDisplayType(), pdc.getFormatPattern());
								}
								
							}
							else if (pdc.getDisplayType() == DisplayType.TextLong)
							{
								String value = "";
								if ("java.lang.String".equals(rs.getMetaData().getColumnClassName(counter)))
								{
									value = rs.getString(counter++);
								}
								else
								{
									Clob clob = rs.getClob(counter++);
									if (clob != null)
									{
										long length = clob.length();
										value = clob.getSubString(1, (int)length);
									}
								}
								pde = new PrintDataElement(pdc.getColumnName(), value, pdc.getDisplayType(), pdc.getFormatPattern());
							}
                            // fix bug [ 1755592 ] Printing time in format
                            else if (pdc.getDisplayType() == DisplayType.DateTime)
                            {
                                Timestamp datetime = rs.getTimestamp(counter++);
                                pde = new PrintDataElement(pdc.getColumnName(), datetime, pdc.getDisplayType(), pdc.getFormatPattern());
                            }
							else
							//	The general case
							{
								Object obj = rs.getObject(counter++);
								if (obj != null && obj instanceof String)
								{
									obj = ((String)obj);
									if (((String)obj).length() == 0)
										obj = null;
								}
								if (obj != null)
								{
									//	Translate Spool Output
									if (translateSpool  && obj instanceof String)
									{
										String s = (String)obj;
										s = Msg.parseTranslation(pd.getCtx(), s);
										pde = new PrintDataElement(pdc.getColumnName(), s, pdc.getDisplayType(), pdc.getFormatPattern());
									}
									else
										pde = new PrintDataElement(pdc.getColumnName(), (Serializable) obj, pdc.getDisplayType(), pdc.getFormatPattern());
								}
							}
						}	//	Value only
					}	//	Non-Key Column
					if (pde != null)
					{
						/** Report Summary FR [ 2011569 ]**/ 
						if(!m_summary)
							pd.addNode(pde);
						group.addValue(pde.getColumnName(), pde.getFunctionValue());
					}
				}	//	for all columns
				if (pd.isHasDummyTableID()) {
					String tableID = rs.getString("AD_Table_ID");
					pd.addNode(tableID);
				}
			}	//	for all rows
			
			//	--	we have all rows - add the group functions, if any.
			//	Check last Group Change
			if (group.getGroupColumnCount() > 1)	//	one is TOTAL
			{
				// Check for changes and add summary rows - force the groups to change
				int firstColumn = getFirstColumnIndexInGroupOrder();
				rowNo = checkGroupChange(firstColumn, pd, rs, true, levelNo, rowNo);
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, pdc + " - " + e.getMessage() + "\nSQL=" + pd.getSQL());
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}

		//	Add Total Lines
		if (group.isGroupColumn(PrintDataGroup.TOTAL))
		{
			char[] functions = group.getFunctions(PrintDataGroup.TOTAL);
			for (int f = 0; f < functions.length; f++)
			{
				printRunningTotal(pd, levelNo, rowNo++);
				pd.addRow(true, levelNo);
				//	get columns
				for (int c = 0; c < pd.getColumnInfo().length; c++)
				{
					pdc = pd.getColumnInfo()[c];
					if (c == 0)		//	put Function in first Column
					{
						String name = "";
						if (!format.getTableFormat().isPrintFunctionSymbols())		//	Translate Sum, etc.
							name = Msg.getMsg(format.getLanguage(), PrintDataFunction.getFunctionName(functions[f]));
						else
							name = PrintDataFunction.getFunctionSymbol(functions[f]);	//	Symbol
						pd.addNode(new PrintDataElement(pdc.getColumnName(), name.trim(),
								DisplayType.String, pdc.getFormatPattern()));
					} else if (group.isFunctionColumn(pdc.getColumnName(), functions[f])
							&& !pdc.isHideGrandTotal()) {
						pd.addNode(new PrintDataElement(pdc.getColumnName(),
							group.getValue(PrintDataGroup.TOTAL, 
								pdc.getColumnName(), functions[f]),
							PrintDataFunction.getFunctionDisplayType(functions[f], pdc.getDisplayType()), pdc.getFormatPattern()));
					}
				}	//	for all columns
			}	//	for all functions
			//	No Need to Reset
		}	//	TotalLine

		if (pd.getRowCount() == 0)
		{
			if (CLogMgt.isLevelFiner())
				log.warning("NO Rows - ms=" + (System.currentTimeMillis()-m_startTime) 
					+ " - " + pd.getSQL());
			else
				log.warning("NO Rows - ms=" + (System.currentTimeMillis()-m_startTime)); 
		}
		else
			log.info("Rows=" + pd.getRowCount()
				+ " - ms=" + (System.currentTimeMillis()-m_startTime));
	}	//	loadPrintData

	/**
	 * 	Print Running Total
	 *	@param pd Print Data to add lines to
	 *	@param levelNo level no
	 *	@param rowNo row no
	 */
	private void printRunningTotal (PrintData pd, int levelNo, int rowNo)
	{
		if (runningTotalLines < 1)	//	-1 = none
			return;
		log.fine("(" + runningTotalLines + ") - Row=" + rowNo 
			+ ", mod=" + rowNo % runningTotalLines);
		if (rowNo % runningTotalLines != 0)
			return;
			
		log.fine("Row=" + rowNo);
		PrintDataColumn pdc = null;
		int start = 0;
		if (rowNo == 0)	//	no page break on page 1
			start = 1;
		for (int rt = start; rt < 2; rt++)
		{
			pd.addRow (true, levelNo);
			//	get sum columns
			for (int c = 0; c < pd.getColumnInfo().length; c++)
			{
				pdc = pd.getColumnInfo()[c];
				if (c == 0)
				{
					String title = "RunningTotal";
					pd.addNode(new PrintDataElement(pdc.getColumnName(),
						title, DisplayType.String, false, rt==0, pdc.getFormatPattern()));		//	page break
				}
				else if (group.isFunctionColumn(pdc.getColumnName(), PrintDataFunction.F_SUM))
				{
					pd.addNode(new PrintDataElement(pdc.getColumnName(),
						group.getValue(PrintDataGroup.TOTAL, pdc.getColumnName(), PrintDataFunction.F_SUM),
						PrintDataFunction.getFunctionDisplayType(PrintDataFunction.F_SUM,
								pdc.getDisplayType()), false, false, pdc.getFormatPattern()));
				}
			}	//	for all sum columns
		}	//	 two lines
	}	//	printRunningTotal

	
	/*************************************************************************
	 * 	Test
	 * 	@param args args
	 */
	public static void main(String[] args)
	{
		org.compiere.Adempiere.startup(true);

	//	DataEngine de = new DataEngine(null);
		DataEngine de = new DataEngine(Language.getLanguage("de_DE"));
		MQuery query = new MQuery();
		query.addRestriction("AD_Table_ID", MQuery.LESS, 105);
	//	PrintData pd = de.load_fromTable(100, query, null, null, false);
	//	pd.dump();
	//	pd.createXML(new javax.xml.transform.stream.StreamResult(System.out));
	}
		
	/**
	 * A recursive method that checks the columns for report functions (Sum, Average, etc...)
	 * and adds rows for the function results.  The functions are based on groups of data so 
	 * the results are triggered when there is a change in the grouped column value.  If there 
	 * are more than one group, the groups are nested where a higher level group change will
	 * force a change in all lower level groups.
	 * <p>See <a href="https://github.com/adempiere/adempiere/issues/431">BR #431</a>
	 * 
	 * @param columnIndex - the column index to examine, zero based
	 * @param pd - the PrintData structure
	 * @param rs - the report Result Set
	 * @param forceChange - set to true if the columnIndex and all subordinate groups are to
	 * 			act as if there is a change in value
	 * @param levelNo
	 * @param rowNo
	 * @return The row number completed.
	 * @throws SQLException
	 * 
	 */
	private int checkGroupChange(int columnIndex, PrintData pd, ResultSet rs, boolean forceChange, int levelNo, int rowNo) throws SQLException {
		//	Recursively Check Columns for Function Columns
				
		// Check if we have exceeded the number of columns which means we are done.
		if (columnIndex >= pd.getColumnInfo().length)
			return rowNo;
		
		// Check if this is not a group column and move on (recursive)
		PrintDataColumn group_pdc = pd.getColumnInfo()[columnIndex];
		PrintDataColumn pdc = null;
		if (!group.isGroupColumn(group_pdc.getColumnName())) {
			columnIndex = getNextColumnIndexInGroupOrder(columnIndex);
			return checkGroupChange(columnIndex, pd, rs, forceChange, levelNo, rowNo);
		}
		
		// Check if this column has a change, if so, force the change in the lower groups, recursively
		Object currentValue = new Object();
		if (!rs.isAfterLast())
		{
			currentValue = rs.getObject(group_pdc.getAlias());
		}
		Object value = group.groupChange(group_pdc.getColumnName(), currentValue);
		forceChange = forceChange || (value!=null);
		columnIndex = getNextColumnIndexInGroupOrder(columnIndex);
		rowNo = checkGroupChange(columnIndex, pd, rs, forceChange, levelNo, rowNo);
		
		// On the way out of the recursive loop
		if (forceChange)	//	Group change. ForceChange on value change or if a higher group changed.
		{
			// Add rows for all the functions affected
			char[] functions = group.getFunctions(group_pdc.getColumnName());
			for (int f = 0; f < functions.length; f++)
			{
				printRunningTotal(pd, levelNo, rowNo++);
				pd.addRow(true, levelNo);
				//	get columns
				for (int c = getFirstColumnIndexInGroupOrder(); c < pd.getColumnInfo().length; c = getNextColumnIndexInGroupOrder(c))
				{
					pdc = pd.getColumnInfo()[c];
				//	log.fine("loadPrintData - PageBreak = " + pdc.isPageBreak());
					if (group_pdc.getColumnName().equals(pdc.getColumnName()))
					{
						if (value == null) { // Indicates no change in value
							if (!rs.isAfterLast())
							{
								value = rs.getObject(group_pdc.getAlias());  // Use the current value
							}
							if (value == null)
								value = "";
						}
						String valueString = value.toString();
						if (value instanceof Timestamp)
							valueString = DisplayType.getDateFormat(pdc.getDisplayType(), m_language).format(value);
						valueString	+= PrintDataFunction.getFunctionSymbol(functions[f]);
						pd.addNode(new PrintDataElement(pdc.getColumnName(),
							valueString, DisplayType.String, false, pdc.isPageBreak(), pdc.getFormatPattern()));
					}
					else if (group.isFunctionColumn(pdc.getColumnName(), functions[f]))
					{
						pd.addNode(new PrintDataElement(pdc.getColumnName(),
							group.getValue(group_pdc.getColumnName(), 
								pdc.getColumnName(), functions[f]), 
							PrintDataFunction.getFunctionDisplayType(functions[f], pdc.getDisplayType()), 
								false, pdc.isPageBreak(), pdc.getFormatPattern()));
					}
				}	//	 for all columns
			}	//	for all functions
			//	Reset Group Values
			for (int c = 0; c < pd.getColumnInfo().length; c++)
			{
				pdc = pd.getColumnInfo()[c];
				group.reset(group_pdc.getColumnName(), pdc.getColumnName());
			}
		}
		return rowNo;
	}

	private int getNextColumnIndexInGroupOrder(int columnIndex) {

		if (printData == null)
			return -1;

		sortColumnsByGroupOrder();  // runs once
		
		Integer nextColIndex = columnsSortedByGroupOrder.higherKey(Integer.valueOf(columnIndex));
		
		if (nextColIndex == null)
			return printData.getColumnInfo().length;
		
		return nextColIndex.intValue();
	}

	private int getPreviousColumnIndexInGroupOrder(int columnIndex) {

		if (printData == null)
			return -1;

		sortColumnsByGroupOrder();  // runs once
		
		Integer prevColIndex = columnsSortedByGroupOrder.lowerKey(Integer.valueOf(columnIndex));
		
		if (prevColIndex == null)
			return -1;
		
		return prevColIndex.intValue();
	}

	private int getFirstColumnIndexInGroupOrder() {

		if (printData == null)
			return -1;

		sortColumnsByGroupOrder();  // runs once
		
		Integer firstColIndex = columnsSortedByGroupOrder.firstKey();
		
		if (firstColIndex == null)
			return 0;
		
		return firstColIndex.intValue();
	}

	private int getLastColumnIndexInGroupOrder() {

		if (printData == null)
			return -1;

		sortColumnsByGroupOrder();  // runs once
		
		return columnsSortedByGroupOrder.lastKey().intValue();
		
	}

	private void sortColumnsByGroupOrder() {
				
		if (printData == null || columnsSortedByGroupOrder != null)
			return;
		
		columnsSortedByGroupOrder 
		= new TreeMap<Integer, PrintDataColumn>(new GroupOrderComparator(printData));

		for (int i=0; i<printData.getColumnInfo().length; i++)
		{
			columnsSortedByGroupOrder.put(Integer.valueOf(i), printData.getColumnInfo()[i]);
		}
		
	}

}	//	DataEngine

/**
 *	Table Reference Info
 */
class TableReference
{
	/** Table Name		*/
	public String 	TableName;
	/** Key Column		*/
	public String 	KeyColumn;
	/** Display Column	*/
	public String 	DisplayColumn;
	/** Displayed		*/
	public boolean 	IsValueDisplayed = false;
	/** Translated		*/
	public boolean	IsTranslated = false;

	/**
	 * 	@return a copy of this reference
	 */
	TableReference copy ()
	{
		TableReference tr = new TableReference();
		tr.TableName = TableName;
		tr.KeyColumn = KeyColumn;
		tr.DisplayColumn = DisplayColumn;
		tr.IsValueDisplayed = IsValueDisplayed;
		tr.IsTranslated = IsTranslated;
		return tr;
	}
}	//	TableReference

class GroupOrderComparator implements Comparator<Integer> 
{
	
	PrintData printData = null;
	
	GroupOrderComparator(PrintData data) {
		printData = data;
	}
	
	/**
	 * Used to compare columns by the sort order to set the level for the grouping.
	 * @return a negative integer if a comes before b (a<b), positive if b comes before a (b<a)
	 * or 0 if it doesn't matter - both are equivalent.
	 * 
	 */
	public int compare(Integer aIndex, Integer bIndex) {
		
		if (printData == null)
			throw new IllegalArgumentException("PrintData not initialized");

		if (aIndex.compareTo(bIndex) == 0)
			return 0;

		if (aIndex.compareTo(0) < 0 || aIndex.compareTo(printData.getColumnInfo().length) > 0)
			throw new IllegalArgumentException("aIndex out of bounds");

		if (bIndex.compareTo(0) < 0 || bIndex.compareTo(printData.getColumnInfo().length) > 0)
			throw new IllegalArgumentException("bIndex out of bounds");

		PrintDataColumn a = printData.getColumnInfo()[aIndex.intValue()];
		PrintDataColumn b = printData.getColumnInfo()[bIndex.intValue()];
	
		if (a == null || b == null)
			throw new IllegalArgumentException("Cannot compare null values.");

		int result = compare(a.getSortOrderIndex(), b.getSortOrderIndex());

		if (result == 0)
		{
			result = compare(a.getDisplayOrderIndex(), b.getDisplayOrderIndex());
		}

		return result;
		
	}
	
	private int compare(int a, int b) {

		if (a > 0 && b > 0)
		{
			return a - b;  
		}
		
		if (a > 0 && b <= 0)
		{
			return -1 * a;  
		}

		if (a <= 0 && b > 0)
		{
			return b;  
		}
		
		// a == 0 && b == 0
		return 0;
		
	}

}