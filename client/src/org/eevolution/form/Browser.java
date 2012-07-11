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
	LinkedHashMap<Object,Object> m_search= new LinkedHashMap<Object,Object>();
	/** Parameters */
	//public ArrayList<String> m_parameters;
	/** Parameters Values */
	//public ArrayList<Object> m_values;
	/** MProcess process */
	public MProcess m_process = null;
	/** ProcessInfo */
	public ProcessInfo m_pi = null;

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
	public String m_sqlOrder;
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
	/** Title **/
	private String m_title = null;

	public Browser(boolean modal, int WindowNo, String value, MBrowse browse,
			String keyColumn, boolean multiSelection, String whereClause) {
		m_Browse = browse;
		m_View = browse.getAD_View();
		p_WindowNo = WindowNo;
		p_keyColumn = keyColumn;
		p_multiSelection = multiSelection;
		m_language = Language.getLanguage(Env
				.getAD_Language(m_Browse.getCtx()));
		
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
		List<MBrowseField> fields = m_Browse.getFields();
		ArrayList<Info_Column> list = new ArrayList<Info_Column>();
		for (MBrowseField field : fields) {
			MViewColumn vcol = field.getAD_View_Column();

			//String title = m_Browse.getTitle();
			String columnName = vcol.getAD_Column().getColumnName();
			
			if (field.isQueryCriteria()) {
				m_queryColumns.add(field.getName());
			}
			m_queryColumnsSql.add(vcol.getColumnSQL());

			int displayType = field.getAD_Reference_ID();
			boolean isKey = field.isKey();
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
			
			// teo_sarca
			String columnSql = vcol.getColumnSQL() + " AS "
					+ vcol.getColumnName();
			if (columnSql == null || columnSql.length() == 0)
				columnSql = columnName;
			// Default
			StringBuffer colSql = new StringBuffer(columnSql);
			Class colClass = null;
			if (isKey) {
				colClass = IDColumn.class;
			} else if (!isDisplayed)
				;
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
				colSql = new StringBuffer("("
						+ MLookupFactory.getLookup_TableDirEmbed(m_language,
								columnName, alias) + ") AS "
						+ vcol.getColumnName());
				colClass = String.class;
			} else if (DisplayType.Table == displayType) {
				String alias = vcol.getAD_View_Definition().getTableAlias();
				colSql = new StringBuffer("("
						+ MLookupFactory.getLookup_TableEmbed(m_language,
								columnName, alias,
								field.getAD_Reference_Value_ID()) + ") AS "
						+ vcol.getColumnName());
				colClass = String.class;
			} else if (DisplayType.String == displayType
					|| DisplayType.Text == displayType
					|| DisplayType.Memo == displayType)
				colClass = String.class;
			else if (DisplayType.isDate(displayType))
				colClass = Timestamp.class;
			else if (DisplayType.List == displayType) {
				colSql = new StringBuffer("("
						+ MLookupFactory.getLookup_ListEmbed(m_language,
								field.getAD_Reference_Value_ID(),
								vcol.getColumnSQL()) + ")");
				colClass = String.class;
			}
			if (colClass != null) {
				Info_Column infocol = new Info_Column(field.getName(), colSql.toString(), colClass);
				infocol.setReadOnly(field.isReadOnly());
				list.add(infocol);
				log.finest("Added Field=" + columnName + " Name=" + field.getName());
			} else
				log.finest("Not Added Field=" +  columnName + "Name=" + field.getName());
		}

		return list;
	}

	public String getSQLWhere() {

		StringBuffer sql = new StringBuffer(m_Browse.getWhereClause() == null ? "" : m_Browse.getWhereClause());
		for (MBrowseField field : m_Browse.getCriteriaFields())
		{
				sql.append(" AND ");
				if (field.isRange()) {
					MViewColumn column = field.getAD_View_Column();
					sql.append(column.getColumnSQL()).append(
							" BETWEEN ? AND ? ");
				} 
				else 
				{
					MViewColumn column = field.getAD_View_Column();
					sql.append(column.getColumnSQL()).append("=? ");
				}
		}
		
		return sql.toString();
	}

	public ArrayList<Object> getParameters() {
		ArrayList<Object> parameters = new ArrayList<Object>();
		for (Entry<Object, Object> entry : m_search.entrySet()) {
			parameters.add(entry.getKey());
		}
		return parameters;
	}

	public void setParameter(Object name, Object value) {
		if (value != null) {
			m_search.put(name, value);
		}
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
		String dynWhere = getSQLWhere();
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

	public String getSelectedSQL() {
		// No results
		List<Integer> keys = getSelectedKeys();
		if (keys == null || keys.size() == 0) {
			log.config("No Results - OK=" + m_ok + ", Cancel=" + m_cancel);
			return "";
		}
		//
		StringBuffer sb = new StringBuffer(getKeyColumn());
		if (keys.size() > 1)
			sb.append(" IN (");
		else
			sb.append("=");

		// Add elements
		for (Integer key : keys) {
			if (getKeyColumn().endsWith("_ID"))
				sb.append(key.toString()).append(",");
			else
				sb.append("'").append(key.toString()).append("',");
		}

		sb.replace(sb.length() - 1, sb.length(), "");
		if (keys.size() > 1)
			sb.append(")");
		return sb.toString();
	} // getSelectedSQL;
	
	

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
			Class colClass = String.class;
			I_AD_View_Column xcol, pcol, ycol;
	
			xcol = field.getAD_View_Column();
			pcol = field.getAxis_Parent_Column();
			ycol = field.getAxis_Column();
			
			String columnName = xcol.getAD_Column().getColumnName();
			MTable xTable = (MTable) xcol.getAD_Column().getAD_Table();
			String xTableName = xTable.getTableName();
	
			MBrowseField fieldKey = field.getFieldKey();
			if(fieldKey == null)
				throw new AdempiereException("@NotFound@ @IsKey@");
	
			String keyColumn = MQuery.getZoomColumnName(columnName);
			String tableName = MQuery.getZoomTableName(columnName);
	
			MTable parentTable = MTable.get(field.getCtx(), tableName);
			MColumn parentColumn = getParentColumn(parentTable.getAD_Table_ID());
			if (parentColumn == null)
				throw new AdempiereException("@NotFound@ @IsParent@");
			if(pcol.getColumnName() == null)
				throw new AdempiereException("@NotFound@ @ColumnName@");
	
			String whereClause = parentColumn.getColumnName() + "="
					+ getParamenterValue(pcol.getColumnName());

			MLookup lookup = MLookupFactory.get(Env.getCtx(), 0,
					xcol.getAD_Column_ID(), field.getAD_Reference_ID(),
					m_language, keyColumn, 0, false, whereClause);

			for (int id : MTable.getAllIDs(tableName, whereClause, null)) {
				String colName = lookup.getDisplay(id)
						+ "/"
						+ Msg.translate(m_language, ycol.getAD_Column()
								.getColumnName());
				StringBuffer select = new StringBuffer("(SELECT ")
						.append(ycol.getAD_Column()
								.getColumnName())
						.append(" FROM  ")
						.append(xTableName)
						.append(" WHERE ")
						.append(xTableName)
						.append(".")
						.append(fieldKey.getAD_View_Column().getAD_Column()
								.getColumnName()).append("=")
						.append(fieldKey.getAD_View_Column().getColumnSQL())
						.append(" AND ").append(xTableName).append(".")
						.append(xcol.getAD_Column().getColumnName())
						.append("=").append(id).append(") AS ");
				select.append(makePrefix(lookup.getDisplay(id))).append("_").append(ycol.getColumnName());
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
	
	/**
	 * get Parameter Value
	 * @param key
	 * @return Object Value
	 */
	 public abstract Object getParamenterValue(Object key);
	 
	 /**
	  * get Parameters Values
	  * @return Object component
	  */
	 abstract public ArrayList<Object> getParametersValues();
	
	protected String getSQL() {
		String dynWhere = getSQLWhere();
		StringBuffer sql = new StringBuffer(m_sqlMain);
		if (dynWhere.length() > 0)
			sql.append(dynWhere); // includes first AND
		sql.append(m_sqlOrder);
		String dataSql = Msg.parseTranslation(Env.getCtx(), sql.toString()); // Variables
		dataSql = MRole.getDefault().addAccessSQL(dataSql,
				m_View.getParentEntityAliasName(), MRole.SQL_FULLYQUALIFIED,
				MRole.SQL_RO);
		log.finer(dataSql);
		return dataSql;
	}

	protected PreparedStatement getStatement() {
		PreparedStatement stmt = null;
		String sqlData = getSQL();
		try {
			stmt = DB.prepareStatement(sqlData, null);
			if (getParametersValues().size() > 0)
				DB.setParameters(stmt, getParametersValues());
			setParameters(stmt, false); // no count
			return stmt;
		} catch (SQLException e) {
			log.log(Level.SEVERE, sqlData, e);
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
		insert.append("INSERT INTO T_SELECTION_BROWSE (AD_PINSTANCE_ID, T_SELECTION_ID, COLUMNNAME , VALUE_STRING, VALUE_NUMBER , VALUE_DATE ) ");
		for (Entry<Integer,LinkedHashMap<String, Object>> records : m_values.entrySet()) {
			//set Record ID
			
				LinkedHashMap<String, Object> fields = records.getValue();
				for(Entry<String, Object> field : fields.entrySet())
				{
					StringBuilder insertValues = new StringBuilder();
					insertValues.append(" VALUES(").append(AD_PInstance_ID).append(",");
					insertValues.append(records.getKey()).append(",");
					//set Browse Field ID
					insertValues.append("'").append(field.getKey()).append("',");
					Object data = field.getValue();
					// set Values
					if (data instanceof String)
					{
						insertValues.append("'").	append(data).append("',");
						insertValues.append("null").append(",");
						insertValues.append("null").append(")");
					}
					else if (data instanceof BigDecimal || data instanceof Integer || data instanceof Double)
					{
						insertValues.append("null").append(",");
						insertValues.append(data).append(",");
						insertValues.append("null").append(")");
					}
					else if (data instanceof Timestamp || data instanceof Date)
					{
						insertValues.append("null").append(",");
						insertValues.append("null").append(",");
						insertValues.append(data).append(")");
					}
					else
					{
						insertValues.append("'").append(data).append("',");
						insertValues.append("null").append(",");
						insertValues.append("null").append(")");
					}
					
					DB.executeUpdateEx(insert.toString() + insertValues.toString(), null);		
						
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
			m_pstmt = getStatement();
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
						if (c == IDColumn.class)
							data = new IDColumn(m_rs.getInt(colIndex));
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
