package org.adempiere.ad.service.impl;

import java.sql.SQLException;
import java.sql.Savepoint;

import org.adempiere.ad.service.ITrxBL;
import org.adempiere.ad.service.ITrxListenerManager;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.compiere.util.DB;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.compiere.util.TrxRunnable2;
import org.compiere.util.TrxRunnable2Wrapper;

/**
 * Standard JDBC transaction handling service
 * 
 * @author tsa
 * 
 */
public class TrxBL implements ITrxBL
{
	@Override
	public void run(TrxRunnable r)
	{
		run(null, r);
	}

	// metas: backward compatibility
	@Override
	public void run(String trxName, TrxRunnable r)
	{
		final boolean manageTrx = false;
		run(trxName, manageTrx, r);
	}

	// metas: added manageTrx parameter
	@Override
	public void run(String trxName, boolean manageTrx, TrxRunnable r)
	{
		final TrxRunnable2 runnable = r instanceof TrxRunnable2 ? (TrxRunnable2)r : new TrxRunnable2Wrapper(r);

		boolean localTrx = false;
		if (trxName == null || manageTrx)
		{
			final String trxPrefix = trxName == null ? "TrxRun" : trxName;
			trxName = Trx.createTrxName(trxPrefix, false); // mo73_03824: only create the name, don't open the trx yet
			localTrx = true;
		}
		DB.saveConstraints();
		try
		{
			if (localTrx)
			{
				// mo73_03824: now allow 'trxName' to be opened and create the trx
				// we need to allow the transaction name only if we created it here
				DB.getConstraints().addAllowedTrxNamePrefix(trxName);
				Trx.get(trxName, true);
			}
			run0(runnable, localTrx, trxName);
		}
		finally
		{
			DB.restoreConstraints();
		}
	}

	private void run0(final TrxRunnable2 runnable, boolean localTrx, String trxName)
	{
		Trx trx = Trx.get(trxName, true);
		Savepoint savepoint = null;
		try
		{
			if (!localTrx)
				savepoint = trx.setSavepoint(null);

			runnable.run(trxName);

			if (localTrx)
				trx.commit(true);
		}
		// we catch Throwable and not only Exceptions because java.lang.AssertionError is not an Exception
		catch (Throwable e)
		{
			Throwable ex = e;
			boolean rollback = true;

			// Call custom exception handler:
			try
			{
				rollback = runnable.doCatch(e);
				ex = null;
			}
			catch (Throwable e2)
			{
				ex = e2;
				rollback = true;
			}

			// Rollback transaction
			if (rollback)
			{
				if (localTrx)
				{
					trx.rollback();
				}
				else if (savepoint != null)
				{
					try
					{
						trx.rollback(savepoint);
					}
					catch (SQLException e2)
					{
						;
					}

					// metas-ts: setting 'trx' to null only if we have a non-local trx.
					// Otherwise we could not close the trx and it would be left dangling
					trx = null;
				}

				// Throw exception
				if (ex != null)
				{
					throw ex instanceof RuntimeException ? (RuntimeException)ex : new AdempiereException(ex);
				}
			}
		}
		finally
		{
			if (localTrx && trx != null)
			{
				trx.close();
				trx = null;
			}

			// Call custom finally handler (if any):
			try
			{
				runnable.doFinally();
			}
			catch (Throwable e2)
			{
				throw e2 instanceof RuntimeException ? ((RuntimeException)e2) : new AdempiereException(e2);
			}
		}
	}

	@Override
	public void commit(final String trxName)
	{
		final Trx trx = Trx.get(trxName, false);
		try
		{
			trx.commit(true);
		}
		catch (SQLException e)
		{
			throw new DBException(e);
		}
	}
	
	@Override
	public ITrxListenerManager getTrxListenerManager(final String trxName)
	{
		final Trx trx = Trx.get(trxName, false);
		return trx.getTrxListenerManager();
	}
}
