package org.compiere.model;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.logging.Level;

import org.adempiere.exceptions.DBException;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Util;

/**
 * Buffered {@link Iterator} over a {@link Query} result.
 * 
 * This iterator works like {@link POBufferedIterator} but in this case the result is guaranteed.
 * 
 * @author tsa
 * 
 * @param <T> model interface
 */
/* package */class GuaranteedPOBufferedIterator<T> implements Iterator<T>
{
	private static final transient CLogger logger = CLogger.getCLogger(GuaranteedPOBufferedIterator.class);

	private final Query query;
	private final Class<T> clazz;

	private String querySelectionUUID = null;
	private Query querySelection;
	private int rowCount = -1;
	private int rowIndex = 0;
	private POBufferedIterator<T> bufferedIterator;

	/* package */GuaranteedPOBufferedIterator(final Query query, final Class<T> clazz)
	{
		super();

		Util.assume(query != null, "query not null");
		this.query = query;

		// Util.assume(clazz != null, "clazz != null"); // class can be null
		this.clazz = clazz;

		init();
	}

	private final void init()
	{
		querySelectionUUID = UUID.randomUUID().toString();

		final String tableName = query.getTableName();
		final String[] keyColumnNames = query.getAD_Table().getKeyColumns();
		if (keyColumnNames == null || keyColumnNames.length != 1)
		{
			throw new DBException("Table " + tableName + " has 0 or more than 1 key columns");
		}
		final String keyColumnName = keyColumnNames[0];
		final String keyColumnNameFQ = tableName + "." + keyColumnName;

		final String orderBy = query.getOrderBy();

		final StringBuffer sqlRowNumber = new StringBuffer("row_number() OVER (");
		if (!Util.isEmpty(orderBy, true))
		{
			sqlRowNumber.append("ORDER BY ").append(orderBy);
		}
		sqlRowNumber.append(")");

		final StringBuilder sqlInsertIntoSelect = new StringBuilder();
		sqlInsertIntoSelect.append("INSERT INTO T_Query_Selection (uuid, line, record_id)")
				.append(" SELECT ")
				.append(DB.TO_STRING(querySelectionUUID))
				.append(", ").append(sqlRowNumber)
				.append(", ").append(keyColumnNameFQ)
				.append(" FROM ").append(tableName);

		final String sql = query.buildSQL(sqlInsertIntoSelect, true); // useOrderByClause = true
		final List<Object> params = query.getParametersEffective();
		final String trxName = query.getTrxName();

		rowCount = DB.executeUpdateEx(sql,
				params == null ? null : params.toArray(),
				trxName);

		if (logger.isLoggable(Level.FINEST))
		{
			logger.info("sql=" + sql + ", params=" + params + ", trxName=" + trxName + ", rowCount=" + rowCount);
		}

		final String selectionWhereClause = keyColumnNameFQ + " IN (SELECT Record_ID FROM T_Query_Selection s WHERE s.uuid=?)";
		final String selectionOrderBy = "(SELECT Line FROM T_Query_Selection s WHERE s.uuid=? and s.Record_ID=" + keyColumnNameFQ + ")";

		querySelection = new Query(query.getCtx(), query.getTableName(), selectionWhereClause, trxName)
				.setParameters(querySelectionUUID, querySelectionUUID)
				.setOrderBy(selectionOrderBy);

		bufferedIterator = new POBufferedIterator<T>(querySelection, clazz);
	}

	@Override
	public boolean hasNext()
	{
		if (rowIndex >= rowCount)
		{
			return false;
		}

		return bufferedIterator.hasNext();
	}

	@Override
	public T next()
	{
		if (rowIndex >= rowCount)
		{
			throw new NoSuchElementException();
		}

		final T value = bufferedIterator.next();
		rowIndex++;
		return value;
	}

	@Override
	public void remove()
	{
		bufferedIterator.remove();
	}

	public void setBufferSize(int bufferSize)
	{
		bufferedIterator.setBufferSize(bufferSize);
	}

	public int getBufferSize()
	{
		return bufferedIterator.getBufferSize();
	}

	@Override
	public String toString()
	{
		return "GuaranteedPOBufferedIterator [clazz=" + clazz
				+ ", querySelectionUUID=" + querySelectionUUID
				+ ", rowIndex=" + rowIndex
				+ ", rowCount=" + rowCount
				+ ", query=" + query
				+ ", bufferedIterator=" + bufferedIterator
				+ "]";
	}

}
