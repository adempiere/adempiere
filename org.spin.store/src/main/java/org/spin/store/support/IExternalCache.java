/*************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                              *
 * This program is free software; you can redistribute it and/or modify it    		 *
 * under the terms version 2 or later of the GNU General Public License as published *
 * by the Free Software Foundation. This program is distributed in the hope   		 *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 		 *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           		 *
 * See the GNU General Public License for more details.                       		 *
 * You should have received a copy of the GNU General Public License along    		 *
 * with this program; if not, write to the Free Software Foundation, Inc.,    		 *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     		 *
 * For the text or an alternative of this public license, you may reach us    		 *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved. *
 * Contributor(s): Yamel Senih www.erpya.com				  		                 *
 *************************************************************************************/
package org.spin.store.support;

import org.compiere.model.MStore;
import org.spin.store.util.support.elasticsearch.IPersistenceWrapper;

/** 
 * 	Interface as contract for list of supported external cache based on contract
 *  @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
public interface IExternalCache {
	
	/**
	 * Index Entity
	 * @param entity
	 * @throws IOException 
	 */
	public IExternalCache index(IPersistenceWrapper entity) throws Exception;
	
	/**
	 * Update Entity
	 * @param entity
	 * @throws IOException
	 */
	public IExternalCache update(IPersistenceWrapper entity) throws Exception;
	
	/**
	 * Delete Entity
	 * @param entity
	 * @throws IOException
	 */
	public IExternalCache delete(IPersistenceWrapper entity) throws Exception;
	
	/**
	 * Validate if exist a entity
	 * @param entity
	 * @return
	 * @throws IOException
	 */
	public boolean exist(IPersistenceWrapper entity) throws Exception;
	
	/**
	 * Connect with cluster
	 * @return
	 */
	public IExternalCache connect() throws Exception;
	
	/**
	 * Close connection
	 * @throws IOException 
	 */
	public IExternalCache close() throws Exception;
	
	/**
	 * Set web store
	 * @param webStore
	 */
	public void withWebStore(MStore webStore);
	
	/**
	 * Get Cache Id
	 * @return
	 */
	public int getCacheId();
}
