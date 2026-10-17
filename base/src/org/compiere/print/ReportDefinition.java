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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Immutable definition of a report: what {@link DataEngine} reads from the dictionary (AD_PrintFormat,
 * AD_PrintFormatItem, AD_Column, AD_ReportView_Col, AD_Table, AD_Ref_Table and the identifier columns of the
 * lookup tables) and builds from it, without anything that depends on the user, the role or the query.
 * <p>
 * Every run creates its own mutable PrintDataColumn and PrintDataGroup from the specs, and appends to
 * {@link #head} the WHERE and role access of the run; {@link #finish(String)} adds the rest of the statement.
 * Nothing in here is modified after construction, so one instance is shared by all threads.
 */
final class ReportDefinition
{
	/** Leading keyword of every report statement, the DBA tag is inserted after it */
	private static final String SELECT_KEYWORD = "SELECT ";

	/**
	 * 	Every input that changes the result of the definition
	 */
	static final class Key
	{
		private final int printFormatId;
		private final String tableName;
		private final String adLanguage;
		private final int reportViewId;
		private final boolean translationView;
		private final boolean lookupJoin;
		private final boolean fine;
		private final int[] orderColumnIds;

		/**
		 * 	@param printFormatId AD_PrintFormat_ID
		 * 	@param tableName table or view the report reads (a translation view has the _vt name)
		 * 	@param adLanguage AD_Language of the report: translated lookups and the List join
		 * 	@param reportViewId AD_ReportView_ID of the run
		 * 	@param translationView the report reads a translation view
		 * 	@param lookupJoin the TableDir lookups are LEFT OUTER JOINs (REPORT_LOOKUP_JOIN, override, table kind)
		 * 	@param fine log level is FINE or lower: the attribute set instance display adds the id
		 * 	@param orderColumnIds AD_Column_IDs in sort order, as the print format object in memory has them
		 */
		Key (int printFormatId, String tableName, String adLanguage, int reportViewId, boolean translationView,
			boolean lookupJoin, boolean fine, int[] orderColumnIds)
		{
			this.printFormatId = printFormatId;
			this.tableName = tableName;
			this.adLanguage = adLanguage;
			this.reportViewId = reportViewId;
			this.translationView = translationView;
			this.lookupJoin = lookupJoin;
			this.fine = fine;
			this.orderColumnIds = orderColumnIds.clone();
		}

		@Override
		public boolean equals (Object o)
		{
			if (this == o)
				return true;
			if (!(o instanceof Key))
				return false;
			Key k = (Key)o;
			return printFormatId == k.printFormatId && reportViewId == k.reportViewId
				&& translationView == k.translationView && lookupJoin == k.lookupJoin && fine == k.fine
				&& tableName.equals(k.tableName) && adLanguage.equals(k.adLanguage)
				&& Arrays.equals(orderColumnIds, k.orderColumnIds);
		}

		@Override
		public int hashCode ()
		{
			int h = printFormatId;
			h = 31 * h + tableName.hashCode();
			h = 31 * h + adLanguage.hashCode();
			h = 31 * h + reportViewId;
			h = 31 * h + (translationView ? 1 : 0);
			h = 31 * h + (lookupJoin ? 1 : 0);
			h = 31 * h + (fine ? 1 : 0);
			return 31 * h + Arrays.hashCode(orderColumnIds);
		}
	}	//	Key

	/**
	 * 	AD_ReportView of a report: table, name and raw WHERE
	 */
	static final class ReportView
	{
		final String tableName;
		final String name;
		final String whereClause;

		ReportView (String tableName, String name, String whereClause)
		{
			this.tableName = tableName;
			this.name = name;
			this.whereClause = whereClause;
		}
	}	//	ReportView

	/**
	 * 	Column of the report, the arguments of PrintDataColumn
	 */
	static final class Column
	{
		final int columnId;
		final String columnName;
		final int displayType;
		final int fieldLength;
		final String alias;
		final boolean pageBreak;
		final int printFormatItemId;
		final boolean hideGrandTotal;
		final String formatPattern;
		final int sortOrderIndex;
		final int displayOrderIndex;

		Column (PrintDataColumn pdc, int fieldLength)
		{
			columnId = pdc.getAD_Column_ID();
			columnName = pdc.getColumnName();
			displayType = pdc.getDisplayType();
			this.fieldLength = fieldLength;
			alias = pdc.getAlias();
			pageBreak = pdc.isPageBreak();
			printFormatItemId = pdc.getPrinformatItemId();
			hideGrandTotal = pdc.isHideGrandTotal();
			formatPattern = pdc.getFormatPattern();
			sortOrderIndex = pdc.getSortOrderIndex();
			displayOrderIndex = pdc.getDisplayOrderIndex();
		}

		/** @return a new mutable column for one run */
		PrintDataColumn newColumn ()
		{
			PrintDataColumn pdc = new PrintDataColumn(columnId, columnName, displayType, fieldLength, alias,
				pageBreak, printFormatItemId, hideGrandTotal);
			pdc.setFormatPattern(formatPattern);
			pdc.setSortOrderIndex(sortOrderIndex);
			pdc.setDisplayOrderIndex(displayOrderIndex);
			return pdc;
		}
	}	//	Column

	/**
	 * 	Group column or function of PrintDataGroup, in the order the print format items add them
	 */
	static final class GroupSpec
	{
		final String columnName;
		/** the function, 0 for a group column */
		final char function;

		GroupSpec (String columnName, char function)
		{
			this.columnName = columnName;
			this.function = function;
		}
	}	//	GroupSpec

	/**
	 * 	Collects the definition while the dictionary is read
	 */
	static final class Builder
	{
		String tableName;
		final List<Column> columns = new ArrayList<Column>();
		final List<GroupSpec> groups = new ArrayList<GroupSpec>();
		boolean hasRunningTotal = false;
		int runningTotalLines = -1;
		boolean tableIDRequired = false;
		boolean tableIDPresent = false;
		boolean hasLevelNo = false;
		/** the join strategy was requested (part of the cache key) */
		boolean lookupJoin = false;
		/** at least one lookup was joined: the statement is wrapped by {@link #outerPrefix} and {@link #outerSuffix} */
		boolean lookupJoined = false;
		String head;
		String groupBy = "";
		String outerPrefix = "";
		String outerSuffix = "";
		String orderBy = "";
		String tag = "";
		/** false when the dictionary could not be read completely (an error was logged): do not cache it */
		boolean complete = true;

		ReportDefinition build ()
		{
			return new ReportDefinition(this);
		}
	}	//	Builder

	final String tableName;
	final List<Column> columns;
	final List<GroupSpec> groups;
	final boolean hasRunningTotal;
	/** greatest RunningTotalLines of the items, meaningful when {@link #hasRunningTotal} */
	final int runningTotalLines;
	final boolean tableIDRequired;
	final boolean tableIDPresent;
	final boolean hasLevelNo;
	/** the join strategy was requested */
	final boolean lookupJoin;
	/** at least one lookup was joined, so {@link #finish(String)} wraps the statement */
	final boolean lookupJoined;
	/** SELECT list and FROM clause (with the joins of the Table, List and special lookups) */
	final String head;
	/** GROUP BY clause or empty */
	final String groupBy;
	/** Start of the statement that wraps the inner statement when the lookups are joined */
	final String outerPrefix;
	/** End of the statement that wraps the inner statement (derived table alias and lookup joins) */
	final String outerSuffix;
	/** ORDER BY clause or empty */
	final String orderBy;
	/** DBA tag inserted after SELECT, empty when the lookups are not joined */
	final String tag;
	/** false when the dictionary could not be read completely */
	final boolean complete;

	private ReportDefinition (Builder b)
	{
		tableName = b.tableName;
		columns = Collections.unmodifiableList(new ArrayList<Column>(b.columns));
		groups = Collections.unmodifiableList(new ArrayList<GroupSpec>(b.groups));
		hasRunningTotal = b.hasRunningTotal;
		runningTotalLines = b.runningTotalLines;
		tableIDRequired = b.tableIDRequired;
		tableIDPresent = b.tableIDPresent;
		hasLevelNo = b.hasLevelNo;
		lookupJoin = b.lookupJoin;
		lookupJoined = b.lookupJoined;
		head = b.head;
		groupBy = b.groupBy;
		outerPrefix = b.outerPrefix;
		outerSuffix = b.outerSuffix;
		orderBy = b.orderBy;
		tag = b.tag;
		complete = b.complete;
	}

	/**
	 * 	Fill the group and function specs of a run
	 * 	@param group the (new, empty) group of the run
	 */
	void fill (PrintDataGroup group)
	{
		for (GroupSpec spec : groups)
		{
			if (spec.function == 0)
				group.addGroupColumn(spec.columnName);
			else
				group.addFunction(spec.columnName, spec.function);
		}
	}	//	fill

	/**
	 * 	@return new mutable columns for one run, in the order of the SELECT list
	 */
	PrintDataColumn[] newColumns ()
	{
		PrintDataColumn[] info = new PrintDataColumn[columns.size()];
		for (int i = 0; i < info.length; i++)
			info[i] = columns.get(i).newColumn();
		return info;
	}	//	newColumns

	/**
	 * 	Complete the statement of a run
	 * 	@param filtered {@link #head} with the WHERE and the role access of the run
	 * 	@return final SQL
	 */
	String finish (String filtered)
	{
		StringBuffer sql = new StringBuffer(filtered).append(groupBy);
		if (lookupJoined)
			sql = new StringBuffer(outerPrefix).append(sql).append(outerSuffix);
		sql.append(orderBy);
		//	the DBA tag goes right after the leading SELECT keyword, a statement that does not start with it stays untagged
		if (tag.length() > 0 && sql.length() >= SELECT_KEYWORD.length()
			&& SELECT_KEYWORD.equalsIgnoreCase(sql.substring(0, SELECT_KEYWORD.length())))
			sql.insert(SELECT_KEYWORD.length(), tag);
		return sql.toString();
	}	//	finish

}	//	ReportDefinition
