/*************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                              *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, C.A.                      *
 * Contributor(s): Yamel Senih ysenih@erpya.com                                      *
 * This program is free software: you can redistribute it and/or modify              *
 * it under the terms of the GNU General Public License as published by              *
 * the Free Software Foundation, either version 3 of the License, or                 *
 * (at your option) any later version.                                               *
 * This program is distributed in the hope that it will be useful,                   *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of                    *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                     *
 * GNU General Public License for more details.                                      *
 * You should have received a copy of the GNU General Public License                 *
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.            *
 ************************************************************************************/
package org.spin.store.util.support.elasticsearch;

import java.util.Map;

/**
 * 	Contract to define persistence wrapper by entity
 * 	@author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public interface IPersistenceWrapper {
	
	/**
	 * Get Map from object
	 * @return
	 */
	public Map<String, Object> getMap();
	
	/**
	 * Get Mapping of entity using data type
	 * @return
	 */
	public Map<String, Object> getMapping();
	
	/**
	 * Get value of key for index
	 * @return
	 */
	public String getKeyValue();
	
	/**
	 * Get Catalog Name
	 * @return
	 */
	public String getCatalogName();
	
	/**
	 * Is Valid for export
	 * @return
	 */
	public boolean isValid();
	
	/**
	 * Set Web Store ID
	 * @param webStoreId
	 * @return
	 */
	public IPersistenceWrapper withWebStoreId(int webStoreId);
}
