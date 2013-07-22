package org.adempiere.db.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import org.adempiere.util.collections.BlindIterator;
import org.adempiere.util.collections.BlindIteratorWrapper;
import org.compiere.util.DB;

/**
 * Abstract class useful for implementing iterators over {@link PreparedStatement}s.
 * 
 * This class behaves like an {@link BlindIterator}. If you want full {@link Iterator} functionality then you can wrap this by using {@link BlindIteratorWrapper}.
 * 
 * @author tsa
 * 
 * @param <E>
 */
public abstract class AbstractPreparedStatementBlindIterator<E> extends AbstractResultSetBlindIterator<E>
{
	private PreparedStatement pstmt = null;

	public AbstractPreparedStatementBlindIterator()
	{
		super();
	}

	/**
	 * Create and returns the {@link PreparedStatement}.
	 * 
	 * This method will be called internally, one time, right before trying to iterate first element.
	 * 
	 * @return
	 * @throws SQLException
	 */
	protected abstract PreparedStatement createPreparedStatement() throws SQLException;

	@Override
	protected final ResultSet createResultSet() throws SQLException
	{
		pstmt = createPreparedStatement();
		return pstmt.executeQuery();
	}

	/**
	 * Closes the underlying {@link ResultSet} and {@link PreparedStatement}.
	 */
	@Override
	public void close()
	{
		super.close();
		DB.close(pstmt);
		pstmt = null;
	}
}
