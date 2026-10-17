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
		private final boolean isTranslationView;
		private final boolean isLookupJoinEnabled;
		private final boolean isFine;
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
		Key (int printFormatId, String tableName, String adLanguage, int reportViewId, boolean isTranslationView,
			boolean isLookupJoinEnabled, boolean isFine, int[] orderColumnIds)
		{
			this.printFormatId = printFormatId;
			this.tableName = tableName;
			this.adLanguage = adLanguage;
			this.reportViewId = reportViewId;
			this.isTranslationView = isTranslationView;
			this.isLookupJoinEnabled = isLookupJoinEnabled;
			this.isFine = isFine;
			this.orderColumnIds = orderColumnIds.clone();
		}

		@Override
		public boolean equals (Object object)
		{
			if (this == object)
				return true;
			if (!(object instanceof Key))
				return false;
			Key otherKey = (Key)object;
			return printFormatId == otherKey.printFormatId && reportViewId == otherKey.reportViewId
				&& isTranslationView == otherKey.isTranslationView && isLookupJoinEnabled == otherKey.isLookupJoinEnabled && isFine == otherKey.isFine
				&& tableName.equals(otherKey.tableName) && adLanguage.equals(otherKey.adLanguage)
				&& Arrays.equals(orderColumnIds, otherKey.orderColumnIds);
		}

		@Override
		public int hashCode ()
		{
			int hash = printFormatId;
			hash = 31 * hash + tableName.hashCode();
			hash = 31 * hash + adLanguage.hashCode();
			hash = 31 * hash + reportViewId;
			hash = 31 * hash + (isTranslationView ? 1 : 0);
			hash = 31 * hash + (isLookupJoinEnabled ? 1 : 0);
			hash = 31 * hash + (isFine ? 1 : 0);
			return 31 * hash + Arrays.hashCode(orderColumnIds);
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
		final boolean isPageBreak;
		final int printFormatItemId;
		final boolean isGrandTotalHidden;
		final String formatPattern;
		final int sortOrderIndex;
		final int displayOrderIndex;

		Column (PrintDataColumn printDataColumn, int fieldLength)
		{
			columnId = printDataColumn.getAD_Column_ID();
			columnName = printDataColumn.getColumnName();
			displayType = printDataColumn.getDisplayType();
			this.fieldLength = fieldLength;
			alias = printDataColumn.getAlias();
			isPageBreak = printDataColumn.isPageBreak();
			printFormatItemId = printDataColumn.getPrinformatItemId();
			isGrandTotalHidden = printDataColumn.isHideGrandTotal();
			formatPattern = printDataColumn.getFormatPattern();
			sortOrderIndex = printDataColumn.getSortOrderIndex();
			displayOrderIndex = printDataColumn.getDisplayOrderIndex();
		}

		/** @return a new mutable column for one run */
		PrintDataColumn newColumn ()
		{
			PrintDataColumn printDataColumn = new PrintDataColumn(columnId, columnName, displayType, fieldLength, alias,
				isPageBreak, printFormatItemId, isGrandTotalHidden);
			printDataColumn.setFormatPattern(formatPattern);
			printDataColumn.setSortOrderIndex(sortOrderIndex);
			printDataColumn.setDisplayOrderIndex(displayOrderIndex);
			return printDataColumn;
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
		boolean isTableIdRequired = false;
		boolean isTableIdPresent = false;
		boolean hasLevelNo = false;
		/** the join strategy was requested (part of the cache key) */
		boolean isLookupJoinEnabled = false;
		/** at least one lookup was joined: the statement is wrapped by {@link #outerPrefix} and {@link #outerSuffix} */
		boolean isLookupJoined = false;
		String head;
		String groupBy = "";
		String outerPrefix = "";
		String outerSuffix = "";
		String orderBy = "";
		String tag = "";
		/** false when the dictionary could not be read completely (an error was logged): do not cache it */
		boolean isComplete = true;

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
	final boolean isTableIdRequired;
	final boolean isTableIdPresent;
	final boolean hasLevelNo;
	/** the join strategy was requested */
	final boolean isLookupJoinEnabled;
	/** at least one lookup was joined, so {@link #finish(String)} wraps the statement */
	final boolean isLookupJoined;
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
	final boolean isComplete;

	private ReportDefinition (Builder builder)
	{
		tableName = builder.tableName;
		columns = Collections.unmodifiableList(new ArrayList<Column>(builder.columns));
		groups = Collections.unmodifiableList(new ArrayList<GroupSpec>(builder.groups));
		hasRunningTotal = builder.hasRunningTotal;
		runningTotalLines = builder.runningTotalLines;
		isTableIdRequired = builder.isTableIdRequired;
		isTableIdPresent = builder.isTableIdPresent;
		hasLevelNo = builder.hasLevelNo;
		isLookupJoinEnabled = builder.isLookupJoinEnabled;
		isLookupJoined = builder.isLookupJoined;
		head = builder.head;
		groupBy = builder.groupBy;
		outerPrefix = builder.outerPrefix;
		outerSuffix = builder.outerSuffix;
		orderBy = builder.orderBy;
		tag = builder.tag;
		isComplete = builder.isComplete;
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
		PrintDataColumn[] printDataColumns = new PrintDataColumn[columns.size()];
		for (int index = 0; index < printDataColumns.length; index++)
			printDataColumns[index] = columns.get(index).newColumn();
		return printDataColumns;
	}	//	newColumns

	/**
	 * 	Complete the statement of a run
	 * 	@param filtered {@link #head} with the WHERE and the role access of the run
	 * 	@return final SQL
	 */
	String finish (String filtered)
	{
		StringBuffer sql = new StringBuffer(filtered).append(groupBy);
		if (isLookupJoined)
			sql = new StringBuffer(outerPrefix).append(sql).append(outerSuffix);
		//	the ORDER BY of the statement with joins starts a line, the legacy statement stays on one line
		if (isLookupJoined && orderBy.length() > 0)
			sql.append("\n").append(orderBy.substring(1));
		else
			sql.append(orderBy);
		//	the DBA tag goes right after the leading SELECT keyword, a statement that does not start with it stays untagged
		if (tag.length() > 0 && sql.length() >= SELECT_KEYWORD.length()
			&& SELECT_KEYWORD.equalsIgnoreCase(sql.substring(0, SELECT_KEYWORD.length())))
			sql.insert(SELECT_KEYWORD.length(), tag);
		return sql.toString();
	}	//	finish

}	//	ReportDefinition
