package org.adempiere.util.trxConstraints.api;

import java.sql.Savepoint;

import org.adempiere.util.ISingletonService;
import org.compiere.util.Trx;

/**
 * Service contains methods to be called from {@link Trx} on certain events to check against {@link ITrxConstraints}.
 * Implementers of this service are responsible for the enforcement of the transaction constraints
 * 
 * @see org.compiere.util.DB#getConstraints()
 * @see ITrxConstraints
 */
public interface IOpenTrxBL extends ISingletonService
{
	void onCommit(Trx trx);

	void onClose(Trx trx);

	void onNewTrx(Trx trx);

	void onRollback(Trx trx);

	void onSetSavepoint(Trx trx, Savepoint savepoint);

	void onReleaseSavepoint(Trx trx, Savepoint savepoint);

	/**
	 * If Trx with the the given trxName hasn't been closed yet, this method returns the stack trace of the trx creation
	 * or last commit/rollback.
	 */
	String getCreationStackTrace(String trxName);

	void onTimeOutChange(Trx trx);
}
