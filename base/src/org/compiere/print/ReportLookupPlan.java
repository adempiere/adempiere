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
 *****************************************************************************/
package org.compiere.print;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.compiere.model.MLookupFactory;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Language;

/**
 * Lookups of a report resolved with LEFT OUTER JOIN instead of correlated SELECT-list subselects.
 * <p>
 * The statement of the report (with its WHERE, role access and GROUP BY) becomes a derived table that has the
 * name of the base table, and the TableDir lookups are joined to it:
 * <pre>
 * SELECT CASE WHEN BPartner_1.C_BPartner_ID IS NOT NULL THEN NVL(BPartner_1.Name,'') END AS AC_BPartner_ID,
 *        C_Order.C_BPartner_ID ...
 * FROM (SELECT C_Order.C_BPartner_ID AS C_BPartner_ID ... FROM C_Order WHERE ...) C_Order
 *   LEFT OUTER JOIN C_BPartner BPartner_1 ON (C_Order.C_BPartner_ID=BPartner_1.C_BPartner_ID)
 * </pre>
 * The outer statement has a line break before FROM, each LEFT OUTER JOIN and ORDER BY, so that a DBA reads it in the
 * trace; the statement of the report (the derived table) stays on one line. On PostgreSQL and Oracle (12c+) the derived
 * table ends with the SQL:2008 clause OFFSET 0 ROWS, so that the planner does not merge it with the lookups.
 * <p>
 * The CASE keeps the NULL of the subselect when there is no foreign key or no row. A translated column whose
 * translation is missing shows the column of the base table: <code>NVL(Product_Trl_1.Name,Product_1.Name)</code>.
 * <p>
 * A display of one column that the database declares NOT NULL needs no CASE: the LEFT OUTER JOIN gives NULL
 * when the row is missing, so the column is selected as it is (<code>BPartner_1.Name</code>). The displays of several
 * columns (Oracle concatenates NULL as an empty string), the translated ones and the nullable columns keep the CASE.
 * The nullability is read from the database catalog while the statement is built, so it is cached with the
 * report definition.
 */
final class ReportLookupPlan
{
	/**
	 * 	LEFT OUTER JOIN to a lookup table (and to its translation table)
	 */
	static final class Join
	{
		final String table;
		final String key;
		/** item that selects the foreign key, null for a nested join */
		final ReportLookupItem foreignKey;
		/** join of the table that holds the foreign key, null for a top level join */
		final Join parent;
		/** foreign key column of the parent table, null for a top level join */
		final String column;
		/** 1 for a top level join, 2 for a join nested in it, and so on */
		final int depth;
		/** lookup numbers inside the alias: n for a top level join, n_m for a nested one */
		final String path;
		final String alias;
		/** alias of the translation table, null if the identifier is not read from it */
		final String translationAlias;
		/** nested joins already numbered under this join */
		int children;

		private Join(String table, String key, ReportLookupItem foreignKey, Join parent, String column, String path,
			String alias, String translationAlias)
		{
			this.table = table;
			this.key = key;
			this.foreignKey = foreignKey;
			this.parent = parent;
			this.column = column;
			this.depth = parent == null ? 1 : parent.depth + 1;
			this.path = path;
			this.alias = alias;
			this.translationAlias = translationAlias;
		}

		/**
		 * 	@return CASE that gives NULL, as the embedded SELECT does, when the row of the lookup is missing.
		 * 	A translated column falls back to the column of the base table when its translation is missing
		 */
		String guard(String expression)
		{
			StringBuilder sql = new StringBuilder("CASE WHEN ").append(alias).append(".").append(key).append(" IS NOT NULL THEN ");
			sql.append(translationAlias == null ? expression : withFallback(expression));
			return sql.append(" END").toString();
		}

		/** @return the expression with NVL(Translation.Column,Table.Column) in place of each Translation.Column */
		private String withFallback(String expression)
		{
			Pattern translated = Pattern.compile("(?<![A-Za-z0-9_$#.])" + Pattern.quote(translationAlias) + "\\.([A-Za-z0-9_]+)");
			return translated.matcher(expression).replaceAll(Matcher.quoteReplacement("NVL(" + translationAlias + ".") + "$1,"
				+ Matcher.quoteReplacement(alias + ".") + "$1)");
		}
	}

	/** Deepest level of nested lookups that is joined, deeper ones stay correlated SELECTs */
	static final int MAX_DEPTH = 3;

	/**
	 * 	Joins the nested TableDir columns of the identifier of one join.
	 * 	A nested join is reused only for the same parent alias and the same column.
	 */
	private final class NestedJoins implements MLookupFactory.NestedLookups
	{
		private final Join parent;

		private NestedJoins(Join parent)
		{
			this.parent = parent;
		}

		@Override
		public String join(MLookupFactory.TableDirEmbed nested, String parentAlias, String column)
		{
			if (parent.depth >= MAX_DEPTH || nested.hasVirtualColumn() || !nested.isJoinable())
				return null;
			Join join = null;
			for (Join existing : joins)
			{
				if (existing.parent == parent && existing.column.equals(column))
				{
					join = existing;
					break;
				}
			}
			int mark = joins.size();
			boolean isNew = join == null;
			if (isNew)
			{
				String path = parent.path + "_" + (parent.children + 1);
				join = newJoin(nested, null, parent, column, path);
				parent.children++;
				joins.add(join);
			}
			String expression = nested.getDisplayExpression(join.alias, join.translationAlias, new NestedJoins(join));
			if (expression == null)
			{
				if (isNew)
				{
					parent.children--;
					while (joins.size() > mark)
						joins.remove(joins.size() - 1);
				}
				return null;
			}
			return select(join, expression);
		}
	}

	private static final CLogger log = CLogger.getCLogger(ReportLookupPlan.class);

	private Join newJoin(MLookupFactory.TableDirEmbed embed, ReportLookupItem foreignKey, Join parent, String column, String path)
	{
		String table = embed.getTableName();
		boolean translated = embed.isTranslationJoin();
		String alias = ReportLookupAlias.lookup(table, path, baseTable);
		String translationAlias = translated ? ReportLookupAlias.translation(table, path, baseTable) : null;
		return new Join(table, embed.getKeyColumn(), foreignKey, parent, column, path, alias, translationAlias);
	}

	private final Language language;
	private final String baseTable;
	private final ArrayList<Join> joins = new ArrayList<Join>();
	/** Columns that the database declares NOT NULL by table, upper case, read from the catalog when first needed */
	private final Map<String, Set<String>> notNullColumns = new HashMap<String, Set<String>>();

	/**
	 * 	@param language language of the report
	 * 	@param baseTable table of the report, name of the derived table
	 */
	ReportLookupPlan(Language language, String baseTable)
	{
		this.language = language;
		this.baseTable = baseTable;
	}

	/**
	 * 	@return true if no lookup is resolved with a join
	 */
	boolean isEmpty()
	{
		return joins.isEmpty();
	}

	/**
	 * 	Resolve the display of a TableDir column with a join.
	 * 	@param display item of the display value (selected as correlated subselect when not joined)
	 * 	@param foreignKey item that selects the foreign key
	 * 	@param embed identifier definition of the lookup table, null if it could not be read
	 * 	@return false if the column must keep the correlated subselect
	 */
	boolean lookup(ReportLookupItem display, ReportLookupItem foreignKey, MLookupFactory.TableDirEmbed embed)
	{
		if (embed == null || embed.hasVirtualColumn() || !embed.isJoinable())
			return false;
		String table = embed.getTableName();
		Join join = null;
		for (Join existing : joins)
		{
			if (existing.parent == null && existing.table.equals(table)
				&& existing.foreignKey.expression.equals(foreignKey.expression))
			{
				join = existing;
				break;
			}
		}
		boolean isNew = join == null;
		int mark = joins.size();
		if (isNew)
		{
			int n = 1;
			for (Join existing : joins)
				if (existing.parent == null)
					n++;
			join = newJoin(embed, foreignKey, null, null, String.valueOf(n));
			joins.add(join);
		}
		String expression = embed.getDisplayExpression(join.alias, join.translationAlias, new NestedJoins(join));
		if (expression == null)
		{
			while (joins.size() > mark)
				joins.remove(joins.size() - 1);
			return false;
		}
		display.join = join;
		display.display = expression;
		return true;
	}

	/**
	 * 	Select text of the display of a join: the column itself when the display is one NOT NULL column of the join table,
	 * 	otherwise the display with the CASE guard of the join.
	 * 	@param join join of the display
	 * 	@param expression display expression of the join
	 * 	@return select text
	 */
	private String select(Join join, String expression)
	{
		String column = singleColumn(join, expression);
		if (column != null && isNotNull(join.table, column))
			return join.alias + "." + column;
		return join.guard(expression);
	}

	/**
	 * 	@return the column when the display is NVL(alias.Column,'') of the join table, null for any other display
	 * 		(several columns, translation, date or number, nested lookup)
	 */
	private static String singleColumn(Join join, String expression)
	{
		if (join.translationAlias != null)
			return null;
		String start = "NVL(" + join.alias + ".";
		String end = ",'')";
		if (!expression.startsWith(start) || !expression.endsWith(end))
			return null;
		String column = expression.substring(start.length(), expression.length() - end.length());
		if (column.length() == 0)
			return null;
		for (int i = 0; i < column.length(); i++)
		{
			char c = column.charAt(i);
			if (!Character.isLetterOrDigit(c) && c != '_')
				return null;
		}
		return column;
	}

	/**
	 * 	@return true if the database declares the column NOT NULL
	 */
	private boolean isNotNull(String table, String column)
	{
		String key = table.toUpperCase();
		Set<String> columns = notNullColumns.get(key);
		if (columns == null)
		{
			columns = readNotNullColumns(table);
			notNullColumns.put(key, columns);
		}
		return columns.contains(column.toUpperCase());
	}

	/**
	 * 	Read the NOT NULL columns of a table from the database metadata (Oracle and PostgreSQL).
	 * 	@return columns in upper case; empty if the catalog cannot be read, so the guard stays
	 */
	private static Set<String> readNotNullColumns(String table)
	{
		Set<String> columns = new HashSet<String>();
		Connection conn = DB.getConnectionRO();
		if (conn == null)
			return columns;
		ResultSet rs = null;
		try
		{
			DatabaseMetaData md = conn.getMetaData();
			String tableName = table;
			if (md.storesUpperCaseIdentifiers())
				tableName = table.toUpperCase();
			else if (md.storesLowerCaseIdentifiers())
				tableName = table.toLowerCase();
			rs = md.getColumns(DB.getDatabase().getCatalog(), DB.getDatabase().getSchema(), tableName, null);
			while (rs.next())
			{
				//	the table name is a pattern: _ matches any character
				if (table.equalsIgnoreCase(rs.getString("TABLE_NAME")) && "NO".equals(rs.getString("IS_NULLABLE")))
					columns.add(rs.getString("COLUMN_NAME").toUpperCase());
			}
		}
		catch (SQLException e)
		{
			log.log(Level.WARNING, "Cannot read the NOT NULL columns of " + table, e);
			columns.clear();
		}
		finally
		{
			DB.close(rs);
			try
			{
				conn.close();
			}
			catch (SQLException e)
			{
				log.log(Level.FINE, "Close", e);
			}
		}
		return columns;
	}

	/**
	 * 	Select list of the derived table: the items that are not joined, with unique names.
	 * 	@param items select items of the report
	 * 	@return select list
	 */
	String innerSelect(List<ReportLookupItem> items)
	{
		Set<String> names = new HashSet<String>();
		StringBuilder select = new StringBuilder();
		for (ReportLookupItem item : items)
		{
			if (item.join != null)
				continue;
			String name = item.outputName;
			for (int i = 1; name.equalsIgnoreCase(baseTable) || !names.add(name.toUpperCase()); i++)
				name = uniqueName(item.outputName, "_" + i);
			item.innerName = name;
			if (select.length() > 0)
				select.append(",");
			if (name.equals(item.outputName))
				select.append(item.legacyText());
			else
				select.append(item.expression).append(" AS ").append(name);
		}
		return select.toString();
	}

	/**
	 * 	Name with a numeric suffix. The name is cut, not the suffix, so the result has at most
	 * 	{@link ReportLookupAlias#MAX_LENGTH} characters (Oracle before 12.2) and stays unique.
	 */
	private static String uniqueName(String name, String suffix)
	{
		int max = ReportLookupAlias.MAX_LENGTH - suffix.length();
		return (name.length() > max ? name.substring(0, max) : name) + suffix;
	}

	/**
	 * 	Statement of the report: SELECT of the items FROM the inner statement and the lookup joins.
	 * 	Call {@link #innerSelect(List)} first.
	 * 	@param items select items of the report
	 * 	@param inner statement of the report without lookup joins
	 * 	@return statement without ORDER BY
	 */
	String outerSelect(List<ReportLookupItem> items, String inner)
	{
		return outerPrefix(items) + inner + outerSuffix();
	}

	/**
	 * 	Start of the statement of the report, up to the opening parenthesis of the inner statement.
	 * 	Call {@link #innerSelect(List)} first.
	 * 	@param items select items of the report
	 * 	@return SELECT list and FROM (
	 */
	String outerPrefix(List<ReportLookupItem> items)
	{
		StringBuilder sql = new StringBuilder("SELECT ");
		boolean first = true;
		for (ReportLookupItem item : items)
		{
			if (item.hidden)
				continue;
			if (!first)
				sql.append(",");
			first = false;
			if (item.join != null)
			{
				sql.append(select(item.join, item.display)).append(" AS ").append(item.alias);
			}
			else
			{
				sql.append(baseTable).append(".").append(item.innerName);
				if (!item.innerName.equals(item.outputName))
					sql.append(" AS ").append(item.outputName);
			}
		}
		return sql.append("\nFROM (").toString();
	}

	/**
	 * 	End of the statement of the report: the closing parenthesis of the inner statement, its alias and the lookup joins.
	 * 	On PostgreSQL and Oracle (12c+) the inner statement ends with the SQL:2008 clause OFFSET 0 ROWS, a planning fence:
	 * 	the planner plans it apart from the lookups of the outer statement, and the rows and their order do not change.
	 * 	MySQL and MariaDB get no clause.
	 * 	@return text after the inner statement
	 */
	String outerSuffix()
	{
		StringBuilder sql = new StringBuilder(DB.isPostgreSQL() || DB.isOracle() ? " OFFSET 0 ROWS) " : ") ").append(baseTable);
		for (Join join : joins)
		{
			String parentColumn = join.parent == null
				? baseTable + "." + join.foreignKey.innerName
				: join.parent.alias + "." + join.column;
			sql.append("\nLEFT OUTER JOIN ").append(join.table).append(" ").append(join.alias)
				.append(" ON (").append(parentColumn)
				.append("=").append(join.alias).append(".").append(join.key).append(")");
			if (join.translationAlias != null)
			{
				sql.append("\nLEFT OUTER JOIN ").append(join.table).append("_Trl ").append(join.translationAlias)
					.append(" ON (").append(join.alias).append(".").append(join.key)
					.append("=").append(join.translationAlias).append(".").append(join.key)
					.append(" AND ").append(join.translationAlias).append(".AD_Language=")
					.append(DB.TO_STRING(language.getAD_Language())).append(")");
			}
		}
		return sql.toString();
	}
}	//	ReportLookupPlan
