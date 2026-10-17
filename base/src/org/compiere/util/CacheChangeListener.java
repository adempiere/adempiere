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
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2026 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.compiere.util;

/**
 * 	Listener of {@link CacheMgt} for the records that were saved or deleted.
 * 	It tells the cache <b>which record</b> changed, new records included, and in which transaction,
 * 	which {@link CacheMgt#reset(String)} cannot do. Register it with
 * 	{@link CacheMgt#addChangeListener(CacheChangeListener)}.
 * 	<p>
 * 	An implementation is called in the thread that saved the record, so it must be thread safe, fast and must not
 * 	save or delete records. An exception it throws is logged and ignored, it never fails the save.
 *
 * @author Victor Perez, victor.perez@e-evolution.com, http://www.e-evolution.com
 */
public interface CacheChangeListener
{
	/**
	 * 	A record was saved (inserted or updated) or deleted successfully. The transaction may still roll back.
	 * 	@param tableName table name
	 * 	@param recordId record ID
	 * 	@param transactionName transaction of the change, null if there is none
	 * 	@param isNewRecord true if the record was inserted
	 * 	@param isDeleted true if the record was deleted
	 */
	void changed (String tableName, int recordId, String transactionName, boolean isNewRecord, boolean isDeleted);

}	//	CacheChangeListener
