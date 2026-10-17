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

/**
 * Item of the SELECT list of a report statement.
 * <p>
 * Text of the legacy statement is <code>expression [AS alias]</code>. When the lookups are resolved
 * with joins the statement has an inner SELECT (this items, without the joined display columns) wrapped
 * by an outer SELECT that adds the lookup joins.
 */
final class ReportLookupItem
{
	/** SQL expression of the item */
	final String expression;
	/** Alias in the legacy statement or null */
	final String alias;
	/** Column name the item has in the result of the legacy statement */
	final String outputName;
	/** Item is only exposed to ORDER BY (sort column that is not printed), never projected */
	final boolean hidden;

	/** Name of the item in the derived table, unique inside it */
	String innerName;
	/** Join that resolves the display value of the item, null = item is selected as it is */
	ReportLookupPlan.Join join;
	/** Display expression of the joined identifier */
	String display;

	ReportLookupItem(String expression, String alias, String outputName)
	{
		this(expression, alias, outputName, false);
	}

	ReportLookupItem(String expression, String alias, String outputName, boolean hidden)
	{
		this.expression = expression;
		this.alias = alias;
		this.outputName = outputName;
		this.hidden = hidden;
		this.innerName = outputName;
	}

	/** @return item as it is selected by the legacy statement */
	String legacyText()
	{
		if (alias == null)
			return expression;
		return expression + " AS " + alias;
	}
}	//	ReportLookupItem
