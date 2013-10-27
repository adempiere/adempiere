 /******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) <Company or Author Name> All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or              *
 * modify it under the terms of the GNU General Public License                *
 * as published by the Free Software Foundation; either version 2             *
 * of the License, or (at your option) any later version.                     *
 * This program is distributed in the hope that it will be useful,            *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of             *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                       *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *																		      *
 * @author Teo Sarca, t.sarca@metas.ro, METAS GROUP							  *
 *  			                                                       		  *
 *****************************************************************************/
package org.adempiere.ad.service;

import org.adempiere.util.ISingletonService;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;

/**
 * Database transaction handling service
 * 
 */
public interface ITrxBL extends ISingletonService
{
	/**
	 * @see #run(String, TrxRunnable)
	 */
	void run(TrxRunnable r);

	/**
	 * Executes the runnable object. Same as calling {@link #run(String, boolean, TrxRunnable)} with manageTrx = false
	 * 
	 * @param trxName transaction name
	 * @param r runnable object
	 * @see #run(String, boolean, TrxRunnable)
	 */
	void run(String trxName, TrxRunnable r);

	/**
	 * Execute runnable object using provided transaction. If execution fails, database operations will be rolled back.
	 * <p>
	 * Example:
	 * 
	 * <pre>
	 * Trx.run(null, new {@link TrxRunnable}() {
	 *     public void run(String trxName) {
	 *         // do something using trxName
	 *     }
	 * )};
	 * </pre>
	 * 
	 * @param trxName transaction name (if {@link Trx#TRXNAME_None}, a new transaction will be created)
	 * @param manageTrx if true, the transaction will be managed by this method (i.e. when runner finishes, transaction will be commited). Also, in case transaction is managed, a trxName will be
	 *            created using given "trxName" as name prefix. If trxName is null a new transaction name will be created with prefix "TrxRun". If trxName is null, the transaction will be
	 *            automatically managed, even if the manageTrx parameter is false.
	 * @param r runnable object
	 */
	void run(String trxName, boolean manageTrx, TrxRunnable r);

	void commit(String trxName);

	/**
	 * Gets {@link ITrxListenerManager} associated with given transaction
	 * 
	 * @param trxName
	 * @return
	 */
	ITrxListenerManager getTrxListenerManager(String trxName);

}
