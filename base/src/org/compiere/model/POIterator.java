/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 * Copyright (C) 2007 Low Heng Sin hengsin@avantz.com                         * 
 * Contributor(s):                                                            *
 * __________________________________________                                 *
 *****************************************************************************/
package org.compiere.model;

import java.util.Iterator;
import java.util.List;

/**
 * 
 * Iterator implementation to fetch PO one at a time using a prefetch ID list.
 * @author Low Heng Sin
 *
 */
public class POIterator<T extends PO> implements Iterator<T> {

	private MTable table;
	private List<Object[]> idList;
	
	private int iteratorIndex = -1;
	private String trxName;
	
	private String keyWhereClause = null;

	/**
	 * @param table
	 * @param idList
	 * @param trxName
	 */
	public POIterator(MTable table, List<Object[]> idList, String trxName) {
		this.table = table;
		this.idList = idList;
		this.trxName = trxName;
	}

	/**
	 * @see java.util.Iterator#hasNext()
	 */
	public boolean hasNext() {
		return ( iteratorIndex < (idList.size() - 1));
	}

	/**
	 * @see java.util.Iterator#next()
	 */
	public T next() {
		if ( iteratorIndex < (idList.size() - 1)) {
			iteratorIndex ++;
			return get(iteratorIndex);
		} else {
			return null;
		}
	}

	/**
	 * not supported.
	 */
	public void remove() {
		throw new UnsupportedOperationException("Remove operatiotn not supported.");
	}
	
	/**
	 * @return number of records
	 */
	public int size() {
		return idList.size();
	}
	
	/**
	 * @param index
	 * @return PO or null if index is invalid
	 */
	public T get(int index) {
		if (index <= (idList.size() - 1)) {
			Object[] ids = idList.get(index);
			if (ids.length == 1 && (ids[0] instanceof Number)) {
				return (T) table.getPO( ((Number)ids[0]).intValue(), trxName);
			} else {
				if (keyWhereClause == null) {
					String[] keys = table.getKeyColumns();
					StringBuffer sqlBuffer = new StringBuffer();
					for (int i = 0; i < keys.length; i++) {
						if (i > 0)
							sqlBuffer.append(" AND ");
						sqlBuffer.append(keys[i]).append(" = ? ");
					}
					keyWhereClause = sqlBuffer.toString();
				}				
				return (T) table.getPO(keyWhereClause, ids, trxName);
			}
		} else {
			return null;
		}
	}
}
