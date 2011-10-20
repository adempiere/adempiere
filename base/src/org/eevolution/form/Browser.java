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

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.model.MBrowse;
import org.adempiere.model.MBrowseField;
import org.adempiere.model.MView;
import org.adempiere.model.MViewColumn;
import org.compiere.apps.search.Info_Column;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MProcess;
import org.compiere.model.MRole;
import org.compiere.model.M_Element;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
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
	public ArrayList<String> m_parameters;
	/** Parameters Values */
	public ArrayList<Object> m_values;
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

	public Browser(boolean modal, int WindowNo, String value, MBrowse browse,
			String keyColumn, boolean multiSelection, String whereClause) {
		m_Browse = browse;
		m_View = browse.getAD_View();
		p_WindowNo = WindowNo;
		p_keyColumn = keyColumn;
		p_multiSelection = multiSelection;
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

	public MLookup getMLookup(MBrowseField field) throws Exception {
		MViewColumn column = field.getAD_View_Column();
		Language language = Language.getLoginLanguage();
		return MLookupFactory.get(m_Browse.getCtx(), p_WindowNo,
				column.getAD_Column_ID(), field.getAD_Reference_ID(), language,
				column.getAD_Column().getColumnName(),
				field.getAD_Reference_Value_ID(), false, "");

	}

	public ArrayList<Info_Column> initBrowserData() {
		List<MBrowseField> fields = m_Browse.getFields();
		ArrayList<Info_Column> list = new ArrayList<Info_Column>();
		for (MBrowseField field : fields) {
			MViewColumn vcol = field.getAD_View_Column();
			M_Element element = new M_Element(m_Browse.getCtx(),
					field.getAD_Element_ID(), null);
			String columnName = element.getColumnName();
			if (field.isQueryCriteria()) {
				m_queryColumns.add(columnName);
			}
			m_queryColumnsSql.add(vcol.getColumnSQL());

			// String columnName =vcol.getColumnName();
			Language language = Language.getLanguage(Env
					.getAD_Language(m_Browse.getCtx()));
			int displayType = field.getAD_Reference_ID();
			boolean isKey = field.isKey();
			boolean isDisplayed = field.isDisplayed();
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
						+ MLookupFactory.getLookup_TableDirEmbed(language,
								columnName, alias) + ") AS "
						+ vcol.getColumnName());
				colClass = String.class;
			} else if (DisplayType.Table == displayType) {
				String alias = vcol.getAD_View_Definition().getTableAlias();
				colSql = new StringBuffer("("
						+ MLookupFactory.getLookup_TableEmbed(language,
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
						+ MLookupFactory.getLookup_ListEmbed(language,
								field.getAD_Reference_Value_ID(),
								vcol.getColumnSQL()) + ")");
				colClass = String.class;
			}
			if (colClass != null) {
				Info_Column infocol = new Info_Column(Msg.translate(
						Env.getCtx(), columnName), colSql.toString(), colClass);
				list.add(infocol);
				log.finest("Added Column=" + columnName);
			} else
				log.finest("Not Added Column=" + columnName);
		}

		return list;
	}

	public String getSQLWhere() {

		StringBuffer sql = new StringBuffer(
				m_Browse.getWhereClause() == null ? "" : " AND "
						+ m_Browse.getWhereClause());
		if (getParameters().size() > 0) {
			sql.append(" AND ");
		}

		Iterator<String> parameters = getParameters().iterator();
		while (parameters.hasNext()) {
			String parameter = parameters.next();
			MBrowseField field = m_Browse.getField(parameter);
			if (field != null) {
				if (field.isRange()) {
					MViewColumn column = field.getAD_View_Column();
					sql.append(column.getColumnSQL()).append(
							" BETWEEN ? AND ? ");
				} else {
					MViewColumn column = field.getAD_View_Column();
					sql.append(column.getColumnSQL()).append("=? ");
					if (parameters.hasNext()) {
						sql.append(" AND ");
					}
				}
			} else if (parameters.hasNext()) {
				sql.append(" AND ");
			}
		}
		return sql.toString();
	}

	public ArrayList<String> getParameters() {
		return m_parameters;
	}

	/**
	 * get Parameters values
	 * 
	 * @return ArralyList with parameters values
	 */
	public ArrayList<Object> getParametersValues() {
		return m_values;
	}

	public void addParameter(String name, Object value) {
		if (value != null && value.toString().length() > 0) {
			m_parameters.add(name);
			m_values.add(value);
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

	public List getSelectedKeys() {
		if (!m_ok || m_results.size() == 0)
			return null;
		return m_results;
	}

	public Object getSelectedKey() {
		if (!m_ok || m_results.size() == 0)
			return null;
		return m_results.get(0);
	}

	public int getAD_Browse_ID() {
		return m_Browse.getAD_Browse_ID();
	}
}
