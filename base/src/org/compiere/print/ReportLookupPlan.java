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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.compiere.model.MLookupFactory;
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
 * The CASE keeps the NULL of the subselect when there is no foreign key, no row or no translation.
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
		final ReportLookupItem foreignKey;
		final String alias;
		/** alias of the translation table, null if the identifier is not read from it */
		final String translationAlias;

		private Join(String table, String key, ReportLookupItem foreignKey, String alias, String translationAlias)
		{
			this.table = table;
			this.key = key;
			this.foreignKey = foreignKey;
			this.alias = alias;
			this.translationAlias = translationAlias;
		}
	}

	private final Language language;
	private final String baseTable;
	private final ArrayList<Join> joins = new ArrayList<Join>();

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
			if (existing.table.equals(table) && existing.foreignKey.expression.equals(foreignKey.expression))
			{
				join = existing;
				break;
			}
		}
		boolean isNew = join == null;
		if (isNew)
		{
			int n = joins.size() + 1;
			join = new Join(table, embed.getKeyColumn(), foreignKey,
				ReportLookupAlias.lookup(table, n, baseTable),
				embed.isTranslationJoin() ? ReportLookupAlias.translation(table, n, baseTable) : null);
		}
		String expression = embed.getDisplayExpression(join.alias, join.translationAlias);
		if (expression == null)
			return false;
		if (isNew)
			joins.add(join);
		display.join = join;
		display.display = expression;
		return true;
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
			for (int i = 1; !names.add(name.toUpperCase()); i++)
				name = item.outputName + "_" + i;
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
	 * 	Statement of the report: SELECT of the items FROM the inner statement and the lookup joins.
	 * 	Call {@link #innerSelect(List)} first.
	 * 	@param items select items of the report
	 * 	@param inner statement of the report without lookup joins
	 * 	@return statement without ORDER BY
	 */
	String outerSelect(List<ReportLookupItem> items, String inner)
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
				Join join = item.join;
				sql.append("CASE WHEN ").append(join.alias).append(".").append(join.key).append(" IS NOT NULL");
				if (join.translationAlias != null)
					sql.append(" AND ").append(join.translationAlias).append(".").append(join.key).append(" IS NOT NULL");
				sql.append(" THEN ").append(item.display).append(" END AS ").append(item.alias);
			}
			else
			{
				sql.append(baseTable).append(".").append(item.innerName);
				if (!item.innerName.equals(item.outputName))
					sql.append(" AS ").append(item.outputName);
			}
		}
		sql.append(" FROM (").append(inner).append(") ").append(baseTable);
		for (Join join : joins)
		{
			sql.append(" LEFT OUTER JOIN ").append(join.table).append(" ").append(join.alias)
				.append(" ON (").append(baseTable).append(".").append(join.foreignKey.innerName)
				.append("=").append(join.alias).append(".").append(join.key).append(")");
			if (join.translationAlias != null)
			{
				sql.append(" LEFT OUTER JOIN ").append(join.table).append("_Trl ").append(join.translationAlias)
					.append(" ON (").append(join.alias).append(".").append(join.key)
					.append("=").append(join.translationAlias).append(".").append(join.key)
					.append(" AND ").append(join.translationAlias).append(".AD_Language=")
					.append(DB.TO_STRING(language.getAD_Language())).append(")");
			}
		}
		return sql.toString();
	}
}	//	ReportLookupPlan
