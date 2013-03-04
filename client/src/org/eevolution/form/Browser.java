/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2003-2011 e-Evolution Consultants. All Rights Reserved.      *
 * Copyright (C) 2003-2011 Victor Pérez Juárez 								  * 
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
 * Contributor(s): Victor Pérez Juárez  (victor.perez@e-evolution.com)		  *
 * Sponsors: e-Evolution Consultants (http://www.e-evolution.com/)            *
 *****************************************************************************/
package org.eevolution.form;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.adempiere.impexp.ArrayExcelExporter;
import org.adempiere.model.I_AD_View_Column;
import org.adempiere.model.MBrowse;
import org.adempiere.model.MBrowseField;
import org.adempiere.model.MView;
import org.adempiere.model.MViewColumn;
import org.compiere.apps.search.Info_Column;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MColumn;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MProcess;
import org.compiere.model.MQuery;
import org.compiere.model.MRole;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Language;
import org.compiere.util.Msg;

/**
 * Abstract Smart Browser <li>FR [ 3426137 ] Smart Browser
 * https://sourceforge.net
 * /tracker/?func=detail&aid=3426137&group_id=176962&atid=879335
 * 
 */
public abstract class Browser {

	static public LinkedHashMap<String, Object> getBrowseValues(
			int AD_PInstance_ID, String alias, int recordId, String trxName) {
		LinkedHashMap<String, Object> values = new LinkedHashMap<String, Object>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Object> parameters = new ArrayList<Object>();
		try {
			StringBuilder sql = new StringBuilder(
					"SELECT ColumnName , Value_String, Value_Date , Value_Number FROM T_Selection_Browse "
							+ "WHERE  AD_PInstance_ID=? AND T_Selection_ID=? ");
			parameters.add(AD_PInstance_ID);
			parameters.add(recordId);
			
			if(alias != null)
			{	
				sql.append("AND ColumnName LIKE ?");
				parameters.add(alias.toUpperCase() + "_%");
			}	
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			DB.setParameters(pstmt, parameters);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String columnName = rs.getString(1);
				/*if (columnName.indexOf("_") > 0)
					columnName = columnName
							.substring(columnName.indexOf("_") + 1);*/
				String valueString = rs.getString(2);
				Timestamp valueDate = rs.getTimestamp(3);
				BigDecimal valueBigDecimal = rs.getBigDecimal(4);
				if (valueString != null) {
					values.put(columnName, valueString);
					continue;
				} else if (valueDate != null) {
					values.put(columnName, valueDate);
					continue;
				} else if (valueBigDecimal != null) {
					values.put(columnName, valueBigDecimal);
					continue;
				}
			}

		} catch (SQLException ex) {
			throw new DBException(ex);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}

		return values;
	}
	
	/** Smart Browse */
	public MBrowse m_Browse = null;
	/** Smart View */
	public MView m_View = null;

	public static final int WINDOW_WIDTH = 1024; // width of the window

	/** String Array of Column Info */
	public Info_Column[] m_generalLayout;
	/** list of query columns */
	public ArrayList<String> m_queryColumns = new ArrayList<String>();
	/** list of query columns (SQL) */
	public ArrayList<String> m_queryColumnsSql = new ArrayList<String>();

	/** Parameters */
	protected ArrayList<Object> m_parameters;
	/** Parameters */
	protected ArrayList<Object> m_parameters_values;
	/** Cache m_whereClause **/
	protected String m_whereClause = ""; 
	
	/** MProcess process */
	public MProcess m_process = null;
	/** ProcessInfo */
	public ProcessInfo m_pi = null;
	/** Browse Process Info */
	public ProcessInfo m_browse_pi = null;

	/** Loading success indicator */
	public boolean p_loadedOK = false;
	/** Model Index of Key Column */
	public int m_keyColumnIndex = -1;
	/** OK pressed */
	public boolean m_ok = false;
	/** Cancel pressed - need to differentiate between OK - Cancel - Exit */
	public boolean m_cancel = false;
	/** Result IDs */
	public ArrayList<Integer> m_results = new ArrayList<Integer>(3);
	/** Result Values */
	public LinkedHashMap<Integer,LinkedHashMap<String, Object>> m_values = new LinkedHashMap<Integer,LinkedHashMap<String,Object>>();
	/** Logger */
	public CLogger log = CLogger.getCLogger(getClass());

	/** Layout of Grid */
	public Info_Column[] p_layout;
	public String m_sqlMain;
	/** Count SQL Statement */
	public String m_sqlCount;
	/** Order By Clause */
	public String m_sqlOrderBy;
	/** Master (owning) Window */
	public int p_WindowNo;
	/** Table Name */
	public String p_FromClause;
	/** Key Column Name */
	public String p_keyColumn;
	/** Enable more than one selection */
	public boolean p_multiSelection;
	/** Initial WHERE Clause */
	public String p_whereClause = "";
	/** Window Width */
	public static final int INFO_WIDTH = 800;
	public boolean isAllSelected = false;
	/** Exporter */
	private Exporter m_exporter = null;
	/** Language **/
	private Language m_language = null;
	/** Export rows **/
	private ArrayList<ArrayList<Object>> m_rows = new ArrayList<ArrayList<Object>>();


	public Browser(boolean modal, int WindowNo, String value, MBrowse browse,
			String keyColumn, boolean multiSelection, String where) {
		m_Browse = browse;
		m_View = browse.getAD_View();
		p_WindowNo = WindowNo;
		p_keyColumn = keyColumn;
		p_multiSelection = multiSelection;
		m_language = Language.getLanguage(Env
				.getAD_Language(m_Browse.getCtx()));

		String whereClause = where != null ? where : "";

		if(m_Browse.getWhereClause() != null )
			   whereClause = whereClause + m_Browse.getWhereClause();
		else
				whereClause = " 1=1 ";
		if (whereClause == null || whereClause.indexOf('@') == -1)
			p_whereClause = whereClause;
		else {
			p_whereClause = Env.parseContext(Env.getCtx(), p_WindowNo,
					whereClause, false, false);
			if (p_whereClause.length() == 0)
				log.log(Level.SEVERE, "Cannot parse context= " + whereClause);
		}

		log.info(m_Browse.getName() + " - " + keyColumn + " - " + whereClause);
	}

	public ArrayList<Info_Column> initBrowserData() {
		List<MBrowseField> fields = m_Browse.getDisplayFields();
		ArrayList<Info_Column> list = new ArrayList<Info_Column>();
		
		MBrowseField fieldKey =  m_Browse.getFieldKey();
		if(fieldKey != null)
		{
			String columnSql = fieldKey.getAD_View_Column().getColumnSQL() + " AS "+ fieldKey.getAD_View_Column().getColumnName();
			Info_Column infoCol = new Info_Column(fieldKey.getName(), columnSql , IDColumn.class , "" );
			infoCol.setReadOnly(false);
			list.add(infoCol);
		}
		else
		{
			Info_Column infoCol = new Info_Column("Row", "'Row' AS \"Row\"", IDColumn.class  , "" );
			infoCol.setReadOnly(false);
			list.add(infoCol);
		}
			

		for (MBrowseField field : fields) {
			MViewColumn vcol = field.getAD_View_Column();

			//String title = m_Browse.getTitle();
			MColumn column =  vcol.getAD_Column();
			String columnName = column.getColumnName();
			
			if (field.isQueryCriteria()) {
				m_queryColumns.add(field.getName());
			}
			m_queryColumnsSql.add(vcol.getColumnSQL());

			int displayType = field.getAD_Reference_ID();
			if(field.isKey())
				continue;
			boolean isDisplayed = field.isDisplayed();
			// Defines Field as Y-Axis
			if(field.getAxis_Column_ID() > 0)
			{
					ArrayList<Info_Column> vlist = getInfoColumnForAxisField(field);
					for (Info_Column infoCol : vlist){
						list.add(infoCol);
					}
					continue;	
			}
			
			String IDcolSQL = "";
			String columnSql = vcol.getColumnSQL() + " AS "
					+ vcol.getColumnName();
			if (columnSql == null || columnSql.length() == 0)
				columnSql = columnName;
			// Default
			StringBuilder colSql = new StringBuilder(columnSql);
			Class colClass = null;
			if (!isDisplayed)
				;
			else if (column.isVirtualColumn())
			{
				colSql = new StringBuilder("(" + column.getColumnSQL() + ")");
				colClass = String.class;
			}
			else if (DisplayType.YesNo == displayType)
				colClass = Boolean.class;
			else if (DisplayType.Amount == displayType)
				colClass = BigDecimal.class;
			else if (DisplayType.Number == displayType
					|| DisplayType.Quantity == displayType)
				colClass = Double.class;
			else if (DisplayType.Integer == displayType)
				colClass = Integer.class;
			else if (DisplayType.TableDir == displayType
					|| DisplayType.Search == displayType) {
				String alias = vcol.getAD_View_Definition().getTableAlias();
				colSql = new StringBuilder("("
						+ MLookupFactory.getLookup_TableDirEmbed(m_language,
								columnName, alias) + ") AS "
						+ vcol.getColumnName());
				IDcolSQL = vcol.getColumnSQL();
				colClass = KeyNamePair.class;
			} else if (DisplayType.Table == displayType) {
				String alias = vcol.getAD_View_Definition().getTableAlias();
				colSql = new StringBuilder("("
						+ MLookupFactory.getLookup_TableEmbed(m_language,
								columnName, alias,
								field.getAD_Reference_Value_ID()) + ") AS "
						+ vcol.getColumnName());
				IDcolSQL = vcol.getColumnSQL();
				colClass = KeyNamePair.class;
			} else if (DisplayType.String == displayType
					|| DisplayType.Text == displayType
					|| DisplayType.Memo == displayType)
				colClass = String.class;
			else if (DisplayType.isDate(displayType))
				colClass = Timestamp.class;
			else if (DisplayType.List == displayType) {
				colSql = new StringBuilder("("
						+ MLookupFactory.getLookup_ListEmbed(m_language,
								field.getAD_Reference_Value_ID(),
								vcol.getColumnSQL()) + ") AS "
						+ vcol.getColumnName());
				colClass = String.class;;
			}
			if (colClass != null) {
				Info_Column infocol = new Info_Column(field.getName(), colSql.toString(), colClass , displayType , IDcolSQL );
				infocol.setReadOnly(field.isReadOnly());
				list.add(infocol);
				log.finest("Added Field=" + columnName + " Name=" + field.getName());
			} else
				log.finest("Not Added Field=" +  columnName + "Name=" + field.getName());
		}		
		return list;
	}

	public ArrayList<Object> getParameters() {
		return m_parameters;
	}
	
	public ArrayList<Object> getParametersValues() {
		return m_parameters_values;
	}
	
	public void addSQLWhere(StringBuffer sql, int index, String value) {
		if (!(value.equals("") || value.equals("%"))
				&& index < m_queryColumns.size()) {
			// sql.append(" AND UPPER(").append(m_queryColumnsSql.get(index).toString()).append(") LIKE '");
			sql.append(" UPPER(")
					.append(m_queryColumnsSql.get(index).toString())
					.append(") LIKE '");
			sql.append(value);
			if (value.endsWith("%"))
				sql.append("'");
			else
				sql.append("%'");
		}
	}

	public void setParameters(PreparedStatement pstmt, boolean forCount)
			throws SQLException {
		int index = 1;
	}

	public int getCount() {
		long start = System.currentTimeMillis();
		String dynWhere = getSQLWhere(true);
		StringBuffer sql = new StringBuffer(m_sqlCount);
		if (dynWhere.length() > 0)
			sql.append(dynWhere); // includes first AND
		String countSql = Msg.parseTranslation(Env.getCtx(), sql.toString()); // Variables
		countSql = MRole.getDefault().addAccessSQL(countSql,
				m_View.getParentEntityAliasName(), MRole.SQL_FULLYQUALIFIED,
				MRole.SQL_RO);
		log.finer(countSql);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int no = -1;
		try {
			pstmt = DB.prepareStatement(countSql, null);
			if (getParametersValues().size() > 0)
				DB.setParameters(pstmt, getParametersValues());
			setParameters(pstmt, true);
			rs = pstmt.executeQuery();
			if (rs.next())
				no = rs.getInt(1);
		} catch (Exception e) {
			log.log(Level.SEVERE, countSql, e);
			no = -2;
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		log.fine("#" + no + " - " + (System.currentTimeMillis() - start) + "ms");

		return no;
	}

	public abstract ArrayList<Integer> getSelectedRowKeys();
	
	public void setProcessInfo(ProcessInfo pi) {
		m_pi = pi;
		if(m_pi != null)
			if(	m_browse_pi !=null)
				m_browse_pi.setRecord_ID(m_pi.getRecord_ID());
	}

	public ProcessInfo getProcessInfo() {
		return m_pi;
	}

	public void setBrowseProcessInfo(ProcessInfo pi) {
		m_browse_pi = pi;
	}

	public ProcessInfo getBrowseProcessInfo() {
		return m_browse_pi;
	}
	
	public String getKeyColumn() {
		if(p_keyColumn == null || p_keyColumn.isEmpty())
			p_keyColumn = m_Browse.getFieldKey().getAD_View_Column().getAD_Column().getColumnName();
		
		return p_keyColumn;
	}

	public Integer getSelectedRowKey() {
		ArrayList<Integer> selectedDataList = getSelectedRowKeys();
		if (selectedDataList.size() == 0) {
			return null;
		} else {
			return selectedDataList.get(0);
		}
	}

	public List<Integer> getSelectedKeys() {
		if (!m_ok || m_results.size() == 0)
			return null;
		return m_results;
	}

	public Object getSelectedKey() {
		if (!m_ok || m_results.size() == 0)
			return null;
		return m_results.get(0);
	}
	
	protected int deleteSelection() {
		
		final MTable table = m_View.getParentViewDefinition().getAD_Table();
		int records = 0 ;
		for (int id : getSelectedRowKeys())
		{
			table.getPO(id, null).delete(true);
			records++;
		}
		return records;
	}

	public int getAD_Browse_ID() {
		return m_Browse.getAD_Browse_ID();
	}
	/**
	 * Get Info_Column for Axis Field
	 * @param field defined as Axis
	 * @return Info_Column with Axis Field
	 */
	public ArrayList<Info_Column> getInfoColumnForAxisField(MBrowseField field) 
	{	
		ArrayList<Info_Column> list = new ArrayList<Info_Column>();
		try {
			I_AD_View_Column xcol, pcol, ycol;
			xcol = field.getAD_View_Column();
			pcol = field.getAxis_Parent_Column();
			ycol = field.getAxis_Column();
			
			String columnName = xcol.getAD_Column().getColumnName();
			MTable xTable = (MTable) xcol.getAD_Column().getAD_Table();
			String xTableName = xTable.getTableName();
	
			//MBrowseField fieldKey = field.getFieldKey();
			//if(fieldKey == null)
			//	throw new AdempiereException("@NotFound@ @IsKey@");
	
			String keyColumn = MQuery.getZoomColumnName(columnName);
			String tableName = MQuery.getZoomTableName(columnName);
	
			MTable parentTable = MTable.get(field.getCtx(), tableName);
			MColumn parentColumn = getParentColumn(parentTable.getAD_Table_ID());
			if (parentColumn == null)
				throw new AdempiereException("@NotFound@ @IsParent@");
			if(pcol.getColumnName() == null)
				throw new AdempiereException("@NotFound@ @ColumnName@");
	
			String whereClause =  "";
			
			if(field.getAD_Val_Rule_ID() > 0)
				whereClause = Env.parseContext(Env.getCtx(), p_WindowNo, field.getAD_Val_Rule().getCode(), false);
			
			if(whereClause.length() > 0 )
				whereClause += " AND ";
			
			whereClause += parentColumn.getColumnName() + "="
					+ getParamenterValue(pcol.getColumnName());

			MLookup lookup = MLookupFactory.get(Env.getCtx(), 0,
					xcol.getAD_Column_ID(), field.getAD_Reference_ID(),
					m_language, keyColumn, field.getAD_Reference_Value_ID(), false, whereClause);

			for (int id : MTable.getAllIDs(tableName, whereClause , null)) {
				String colName = lookup.getDisplay(id).trim()
						+ "/"
						+ Msg.translate(m_language, ycol.getAD_Column()
								.getColumnName());
				StringBuffer select = new StringBuffer("(SELECT ")
						.append(ycol.getAD_Column()
								.getColumnName())
						.append(" FROM  ")
						.append(xTableName)
						.append(" WHERE ")
						//.append(xTableName)
						//.append(".")
						//.append(fieldKey.getAD_View_Column().getAD_Column()
						//		.getColumnName()).append("=")
						//.append(fieldKey.getAD_View_Column().getColumnSQL())
						//.append(" AND ").append(xTableName).append(".")
						.append(xTableName).append(".")
						.append(xcol.getAD_Column().getColumnName())
						.append("=").append(id).append(") AS ");
				select.append("\"").append(colName).append("\"");
				Info_Column infocol = new Info_Column(colName,
						select.toString(), DisplayType.getClass(ycol.getAD_Column().getAD_Reference_ID(), true));
				infocol.setReadOnly(field.isReadOnly());
				list.add(infocol);
				log.finest("Added Column=" + colName);
			}
			
		} catch (Exception e) {
			throw new AdempiereException(e);
		}	
		return list;
	}
	
	/**
	 * Get Parent Column for Table
	 * @param AD_Table_ID Table ID
	 * @return MColumn
	 */
	private MColumn getParentColumn(int AD_Table_ID)
	{
		String whereClause = MColumn.COLUMNNAME_AD_Table_ID + "=? AND "
				+ MColumn.COLUMNNAME_IsParent + "=? ";
		return new Query(Env.getCtx(), MColumn.Table_Name, whereClause, null)
				.setParameters(AD_Table_ID, true).first();
	}
	
	public MBrowseField getFieldKey()
	{
	MBrowseField fieldKey = m_Browse.getFieldKey();
	return fieldKey;
	}
	
	public boolean IsIdentifierSelection(String columnName)
	{	
		for (MBrowseField field : m_Browse.getIdentifierFields()) {
			if (field.getAD_View_Column().getColumnName().equals(columnName))
				return true;
		}
		return false;
	}
	
	public MQuery getMQuery()
	{
		Integer record_ID = getSelectedRowKey();

		if (record_ID == null)
			return null;
		
		MBrowseField fieldKey = getFieldKey();
		if(fieldKey == null)
			return null;
		
		MColumn column = fieldKey.getAD_View_Column().getAD_Column();
		String keyColumn = MQuery.getZoomColumnName(column.getColumnName());
		String tableName = column.getAD_Table().getTableName();
		MQuery query = new MQuery(tableName);
		query.addRestriction(keyColumn, MQuery.EQUAL, record_ID);
		return query;
	}
	
	
	/**
	 * get Parameter Value
	 * @param key
	 * @return Object Value
	 */
	 public abstract Object getParamenterValue(Object key);
	 
	 abstract public String  getSQLWhere(boolean refresh);
	
	protected String getSQL() {
		String dynWhere = getSQLWhere(false);
		StringBuilder sql = new StringBuilder(m_sqlMain);
		if (dynWhere.length() > 0)
			sql.append(dynWhere); // includes first AND
		sql.append(m_sqlOrderBy);
		String dataSql = Msg.parseTranslation(Env.getCtx(), sql.toString()); // Variables
		dataSql = MRole.getDefault().addAccessSQL(dataSql,
				m_View.getParentEntityAliasName(), MRole.SQL_FULLYQUALIFIED,
				MRole.SQL_RO);
		log.finer(dataSql);
		//dataSql += " ORDER BY " + m_Browse.getOrderByClause(); 
		return dataSql;
	}
	
	public String getSQLOrderBy() {
		StringBuilder sqlOrderBy = new StringBuilder();
		for (MBrowseField field : m_Browse.getOrderByFields()) {
			if (sqlOrderBy.length() > 0 && field.isOrderBy())
				sqlOrderBy.append(",");

			if (field.isOrderBy()) {
				int orderByPosition = getOrderByPosition(field
						.getAD_View_Column().getColumnName());
				if (orderByPosition > 0)
					sqlOrderBy.append(orderByPosition);
			}
		}
		return sqlOrderBy.length() > 0 ? "ORDER BY " + sqlOrderBy.toString()
				: "";
	}
	
	private int getOrderByPosition(String name)
	{
		int colOffset = 1; // columns start with 1
		for (int col = 0; col < p_layout.length; col++) {
			Info_Column column = p_layout[col];
			String columnName = column.getColSQL().substring(
					column.getColSQL().indexOf("AS ") + 3);

			Class<?> c = column.getColClass();
			int sortBySqlNo = col + colOffset;
			if (c == KeyNamePair.class)
				colOffset++;
			if(name.equals(columnName))
				return sortBySqlNo;
		}
		return 0;
	}
	
	protected PreparedStatement getStatement(String sql) {
		PreparedStatement stmt = null;
		try {
			stmt = DB.prepareStatement(sql, null);
			if (getParametersValues().size() > 0)
				DB.setParameters(stmt, getParametersValues());
			setParameters(stmt, false); // no count
			return stmt;
		} catch (SQLException e) {
			log.log(Level.SEVERE, sql, e);
		}
		return stmt;
	}
	
	protected File exportXLS() {
		File file = null;
		try {
			if (m_exporter != null && m_exporter.isAlive())
				return file;

			m_exporter = new Exporter();
			m_exporter.start();
			while (m_exporter.isAlive())
				;
			if (m_rows.size() > 1) {

				String path = System.getProperty("java.io.tmpdir");
				String prefix = makePrefix(m_Browse.getName());
				if (log.isLoggable(Level.FINE)) {
					log.log(Level.FINE, "Path=" + path + " Prefix=" + prefix);
				}
				file = File.createTempFile(prefix, ".xls", new File(path));
				ArrayExcelExporter exporter = new ArrayExcelExporter(
						Env.getCtx(), m_rows);
				exporter.export(file, m_language, false);
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "", e);
		} catch (Exception e) {
			log.log(Level.SEVERE, "", e);
		}
		return file;
	}

	private String makePrefix(String name) {
		StringBuffer prefix = new StringBuffer();
		char[] nameArray = name.toCharArray();
		for (char ch : nameArray) {
			if (Character.isLetterOrDigit(ch)) {
				prefix.append(ch);
			} else {
				prefix.append("_");
			}
		}
		return prefix.toString();
	}
	
	/**
	 * Insert result values
	 * @param AD_PInstance_ID
	 */
	public void createT_Selection_Browse(int AD_PInstance_ID)
	{
		StringBuilder insert = new StringBuilder();
		insert.append("INSERT INTO T_SELECTION_BROWSE (AD_PINSTANCE_ID, T_SELECTION_ID, COLUMNNAME , VALUE_STRING, VALUE_NUMBER , VALUE_DATE ) VALUES(?,?,?,?,?,?) ");
		for (Entry<Integer,LinkedHashMap<String, Object>> records : m_values.entrySet()) {
			//set Record ID
			
				LinkedHashMap<String, Object> fields = records.getValue();
				for(Entry<String, Object> field : fields.entrySet())
				{
					List<Object> parameters = new ArrayList<Object>();
					parameters.add(AD_PInstance_ID);
					parameters.add(records.getKey());
					parameters.add(field.getKey());
					
					Object data = field.getValue();
					// set Values					
					if (data instanceof IDColumn)
					{
						IDColumn id = (IDColumn) data;
						parameters.add(null);
						parameters.add(id.getRecord_ID());
						parameters.add(null);
					}
					else if (data instanceof String)
					{
						parameters.add(data);
						parameters.add(null);
						parameters.add(null);
					}
					else if (data instanceof BigDecimal || data instanceof Integer || data instanceof Double)
					{
						parameters.add(null);
						if(data instanceof Double)
						{	
							BigDecimal value = BigDecimal.valueOf((Double)data);
							parameters.add(value);
						}	
						else	
							parameters.add(data);
						parameters.add(null);
					}
					else if (data instanceof Integer)
					{
						parameters.add(null);
						parameters.add((Integer)data);
						parameters.add(null);
					}
					else if (data instanceof Timestamp || data instanceof Date)
					{
						parameters.add(null);
						parameters.add(null);
						if(data instanceof Date)
						{
							Timestamp value = new Timestamp(((Date)data).getTime());
							parameters.add(value);
						}
						else 
						parameters.add(data);
					}
					else
					{
						parameters.add(data);
						parameters.add(null);
						parameters.add(null);
					}
					DB.executeUpdateEx(insert.toString(),parameters.toArray() , null);		
						
				}
		}
	}

	/**
	 * Exporter
	 */
	class Exporter extends Thread {
		private PreparedStatement m_pstmt = null;
		private ResultSet m_rs = null;
		private String dataSql = null;

		/**
		 * Do Work (load data)
		 */
		public void run() {
			long start = System.currentTimeMillis();
			int no = 0;
			dataSql = getSQL();
			m_pstmt = getStatement(dataSql);
			m_rows = new ArrayList<ArrayList<Object>>();
			try {
				log.fine("Start query - "
						+ (System.currentTimeMillis() - start) + "ms");
				m_rs = m_pstmt.executeQuery();
				log.fine("End query - " + (System.currentTimeMillis() - start)
						+ "ms");
				boolean isFirstRow = true;
				while (m_rs.next()) {
					if (this.isInterrupted()) {
						log.finer("Interrupted");
						close();
						return;
					}
					no++;
					ArrayList<Object> header = (isFirstRow ? new ArrayList<Object>()
							: null);
					ArrayList<Object> row = new ArrayList<Object>();
					int colOffset = 1; // columns start with 1
					for (int col = 0; col < p_layout.length; col++) {

						if (isFirstRow) {
							String columnName = p_layout[col].getColHeader();
							header.add(columnName);
						}
						Object data = null;
						Class<?> c = p_layout[col].getColClass();
						int colIndex = col + colOffset;
						if (c == IDColumn.class && !p_layout[col].getColSQL().equals("'Row' AS \"Row\""))
							data = new IDColumn(m_rs.getInt(colIndex));
						else if (c == IDColumn.class && p_layout[col].getColSQL().equals("'Row' AS \"Row\""))
							data = new IDColumn(no);
						else if (c == Boolean.class)
							data = new Boolean("Y".equals(m_rs
									.getString(colIndex)));
						else if (c == Timestamp.class)
							data = m_rs.getTimestamp(colIndex);
						else if (c == BigDecimal.class)
							data = m_rs.getBigDecimal(colIndex);
						else if (c == Double.class)
							data = new Double(m_rs.getDouble(colIndex));
						else if (c == Integer.class)
							data = new Integer(m_rs.getInt(colIndex));
						else if (c == KeyNamePair.class) {
							String display = m_rs.getString(colIndex);
							int key = m_rs.getInt(colIndex + 1);
							data = new KeyNamePair(key, display);
							colOffset++;
						} else
							data = m_rs.getString(colIndex);
						
						row.add(data);
					}
					
					if (isFirstRow)
						m_rows.add(header);
					m_rows.add(row);
					isFirstRow = false;
				}

			} catch (Throwable e) {
				log.log(Level.SEVERE, dataSql, e);
			} finally {
				close();
			}
			log.fine("#" + no + " - " + (System.currentTimeMillis() - start)
					+ "ms");
			if (no == 0)
				log.fine(dataSql);

		} // run

		private void close() {
			DB.close(m_rs, m_pstmt);
			m_rs = null;
			m_pstmt = null;
		}
	} // Exporter
}
