package org.compiere.model;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.DBException;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Util;

/**
 * Buffered {@link Iterator} over a {@link Query} result.
 * 
 * @author tsa
 * 
 * @param <T> model interface
 */
/* package */class POBufferedIterator<T> implements Iterator<T>
{
	private static final transient CLogger logger = CLogger.getCLogger(POBufferedIterator.class);

	private final Query query;
	private final Class<T> clazz;

	private int bufferSize = 50;
	private int offset = 0;
	private List<T> buffer;
	private Iterator<T> bufferIterator;

	/**
	 * Buffer was fully loaded? True when buffer contains as much data as it was required. If this flag is false then it's a good indicator that we are on last page.
	 * 
	 */
	private boolean bufferFullyLoaded = false;

	/* package */POBufferedIterator(final Query query, final Class<T> clazz)
	{
		super();

		// Make sure database paging is supported
		if (!DB.getDatabase().isPagingSupported())
		{
			throw new DBException("Database paging support is required in order to have " + POBufferedIterator.class + " working");
		}

		Util.assume(query != null, "query != null");
		this.query = query.copy();
		if (Util.isEmpty(this.query.getOrderBy(), true))
		{
			final String orderBy = buildOrderBy(this.query.getCtx(), this.query.getTableName());
			if (Util.isEmpty(orderBy))
			{
				throw new DBException("Query does not have ORDER BY and we could not build one for given table because there are no key columns: " + query);
			}

			if (logger.isLoggable(Level.FINEST))
			{
				logger.info("Using default build-in ORDER BY: " + orderBy);
			}
			this.query.setOrderBy(orderBy);
		}

		// Util.assume(clazz != null, "clazz != null"); // class can be null
		this.clazz = clazz;
	}

	private String buildOrderBy(Properties ctx, String tableName)
	{
		final MTable table = MTable.get(ctx, tableName);

		StringBuffer orderBy = new StringBuffer();
		for (String keyColumnName : table.getKeyColumns())
		{
			if (orderBy.length() > 0)
			{
				orderBy.append(", ");
			}
			orderBy.append(keyColumnName);
		}

		return orderBy.toString();
	}

	@Override
	public boolean hasNext()
	{
		final Iterator<T> it = getBufferIterator();
		return it.hasNext();
	}

	@Override
	public T next()
	{
		final Iterator<T> it = getBufferIterator();
		final T value = it.next();
		return value;
	}

	@Override
	public void remove()
	{
		throw new UnsupportedOperationException("Remove operation not supported.");
	}

	private Iterator<T> getBufferIterator()
	{
		// Buffer iterator was not initialized yet, loading first page
		if (bufferIterator == null)
		{
			loadNextPage();
			return bufferIterator;
		}

		// Buffer iterator has reached the end. We load the next page only if current page was fully load.
		// Else, makes no sense to load next page because last page was a short one so we are sure that there are no more pages
		if (!bufferIterator.hasNext() && bufferFullyLoaded)
		{
			loadNextPage();
		}

		return bufferIterator;
	}

	private void loadNextPage()
	{
		query.setLimit(bufferSize, offset);
		buffer = query.list(clazz);
		bufferIterator = buffer.iterator();
		bufferFullyLoaded = buffer.size() == bufferSize;

		if (logger.isLoggable(Level.INFO))
		{
			logger.info("Loaded next page: bufferSize=" + bufferSize + ", offset=" + offset + " => " + buffer.size() + " records (fullyLoaded=" + bufferFullyLoaded + ")");
		}

		offset += bufferSize;
	}

	public void setBufferSize(int bufferSize)
	{
		Util.assume(bufferSize > 0, "bufferSize > 0");
		this.bufferSize = bufferSize;
	}

	public int getBufferSize()
	{
		return bufferSize;
	}

	@Override
	public String toString()
	{
		return "POBufferedIterator [clazz=" + clazz
				+ ", bufferSize=" + bufferSize
				+ ", offset=" + offset
				+ ", query=" + query
				+ "]";
	}
}
