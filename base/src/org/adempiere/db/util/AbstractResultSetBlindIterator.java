package org.adempiere.db.util;

import java.io.Closeable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.adempiere.exceptions.DBException;
import org.adempiere.util.collections.BlindIterator;
import org.adempiere.util.collections.BlindIteratorWrapper;
import org.compiere.util.DB;

/**
 * Abstract class useful for implementing iterators over {@link ResultSet}s.
 * 
 * This class behaves like an {@link BlindIterator}. If you want full {@link Iterator} functionality then you can wrap this by using {@link BlindIteratorWrapper}.
 * 
 * @author tsa
 * 
 * @param <E>
 */
public abstract class AbstractResultSetBlindIterator<E> implements BlindIterator<E>, Closeable
{
	private ResultSet rs = null;
	private boolean closed = false;

	public AbstractResultSetBlindIterator()
	{
		super();
	}

	/**
	 * Create and returns the {@link ResultSet}.
	 * 
	 * This method will be called internally, one time, right before trying to iterate first element.
	 * 
	 * @return
	 * @throws SQLException
	 */
	protected abstract ResultSet createResultSet() throws SQLException;

	/**
	 * Method responsible for fetching current row from {@link ResultSet} and convert it to target object.
	 * 
	 * NOTE: implementors of this method shall NEVER call rs.next().
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	protected abstract E fetch(ResultSet rs) throws SQLException;

	@Override
	public final E next()
	{
		if (closed)
		{
			throw new NoSuchElementException("ResultSet already closed");
		}

		boolean keepAliveResultSet = false; // if false, underlying ResultSet will be closed in finally block
		try
		{
			if (rs == null)
			{
				rs = createResultSet();
				if (rs == null)
				{
					throw new IllegalStateException("No ResultSet was created");
				}
			}

			if (!rs.next())
			{
				// we reached the end of result set
				keepAliveResultSet = false; // flag that we need to close the ResultSet
				return null;
			}

			final E item = fetch(rs);
			keepAliveResultSet = true;
			return item;
		}
		catch (SQLException e)
		{
			keepAliveResultSet = false; // make sure we will close the ResultSet
			throw new DBException(e);
		}
		finally
		{
			if (!keepAliveResultSet)
			{
				close();
			}
		}
	}

	/**
	 * Closes the underlying {@link ResultSet}.
	 */
	@Override
	public void close()
	{
		DB.close(rs);
		rs = null;
		closed = true;
	}
}
