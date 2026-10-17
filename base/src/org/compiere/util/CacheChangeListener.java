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
package org.compiere.util;

/**
 * 	Listener of {@link CacheMgt} for the records that were saved or deleted.
 * 	It tells the cache <b>which record</b> changed, new records included, and in which transaction,
 * 	which {@link CacheMgt#reset(String)} cannot do. Register it with
 * 	{@link CacheMgt#addChangeListener(CacheChangeListener)}.
 * 	<p>
 * 	An implementation is called in the thread that saved the record, so it must be thread safe, fast and must not
 * 	save or delete records. An exception it throws is logged and ignored, it never fails the save.
 */
public interface CacheChangeListener
{
	/**
	 * 	A record was saved (inserted or updated) or deleted successfully. The transaction may still roll back.
	 * 	@param tableName table name
	 * 	@param recordId record ID
	 * 	@param trxName transaction of the change, null if there is none
	 * 	@param newRecord true if the record was inserted
	 * 	@param deleted true if the record was deleted
	 */
	void changed (String tableName, int recordId, String trxName, boolean newRecord, boolean deleted);

}	//	CacheChangeListener
