/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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
 *****************************************************************************/
package org.compiere.cm.cache;

import java.util.*;

import org.compiere.util.CLogger;

/**
 *  CO CacheObject
 *  we store parts of the content in caches on the webservers to reduce DB load and speed up page deployment
 *
 *  @author Yves Sandfort
 *  @version  $Id$
 */
public class CO {

	protected static int cacheSize = 100;
	protected Hashtable cache = new Hashtable(cacheSize);
	protected Hashtable cacheUsage = new Hashtable(cacheSize);
	protected Properties ctx;

	/**	Logger			*/
	protected CLogger	log = CLogger.getCLogger(this.getClass());

	/**
	 * 	Cache Object
	 */
	public CO () {
	}
	
	/**
	 * 	set Context
	 *	@param thisCtx
	 */
	public void setCtx(Properties thisCtx) {
		ctx = thisCtx;
	}
	
	/**
	 * 	get Context
	 *	@return Context
	 */
	public Properties getCtx() {
		return ctx;
	}
	
	/**
	 * 	put
	 *	@param ID
	 *	@param thisObject
	 */
	public void put(String ID, Object thisObject) {
		cache.put(ID,thisObject);
		Long thisLong = new Long(new Date().getTime());
		cacheUsage.put(ID, thisLong);
		if (cacheUsage.size()>cacheSize-1) {
			cleanUp();
		}
	}

	/**
	 * 	remove
	 *	@param ID
	 */
	public void remove(String ID) {
		cache.remove(ID);
		cacheUsage.remove(ID);
	}
	
	/**
	 * 	getSize of current cache
	 *	@return number of cache entries
	 */
	public int getSize() {
		return cache.size();
	}

	/**
	 * 	get key enumeration
	 *	@return key enumeration
	 */
	public Enumeration getKeys() {
		return cache.keys();
	}

	private void cleanUp () {
		Vector vecKeys = new Vector(); 
		//Gets keys from hashtable 

		Enumeration myEnum = cacheUsage.elements();

		while (myEnum.hasMoreElements()) 
		{ 
			vecKeys.add(myEnum.nextElement()); 
		} 
                 
		//Sorts vector in Ascending order 
		Collections.sort(vecKeys); 
		Collections.reverse(vecKeys);
                 
		//Displays values using Key 
		for(int i=0;i<vecKeys.size();i++) 
		{
			String value = vecKeys.get(i).toString();
			String key = "";
			Enumeration keys = cacheUsage.keys();
			while (keys.hasMoreElements() && key.equals("")) {
				String thisKey = keys.nextElement().toString();
				String tempValue = cacheUsage.get(thisKey).toString(); 
				if (tempValue.equals(value)) {
					key = thisKey;
				}
			}
			// Use Maxelements -1 since i starts with 0
			if (i>cacheSize-1) {
				cache.remove(key);
				cacheUsage.remove(key);
				log.fine("Item: " + key + " from cache: " + this.getClass().getName() + " was removed.");
			}
		}
	}
	
	/**
	 * 	Update Usage value for cache optimization
	 *	@param ID
	 */
	public void use(int ID) {
		Long thisLong = new Long(new java.util.Date().getTime());
		cacheUsage.put("" + ID, thisLong);
	}
	
	/**
	 * 	Update Usage value for cache optimization
	 *	@param ID
	 */
	public void use(String ID) {
		Long thisLong = new Long(new java.util.Date().getTime());
		cacheUsage.put(ID, thisLong);
	}
	
    /**
     * 	empty complete Cache
     */
    public void empty() {
    		cache = new Hashtable(cacheSize);
		cacheUsage = new Hashtable(cacheSize);
		log.fine("Cache: " + this.getClass().getName() + " was cleared.");
    }
    
    /**
     * 	Show Cache Content
     *	@return XML String with CacheContent
     */
    public String show() {
    		StringBuffer tStrHTML = new StringBuffer();
		Enumeration thisEnum = null;
		tStrHTML.append("      <size>" + this.getSize() + "</size>\n");
		thisEnum = this.getKeys();
		while (thisEnum.hasMoreElements()) { 
			tStrHTML.append("      <item>" + thisEnum.nextElement() + "</item>\n"); 
		} 
		return tStrHTML.toString();
    }
}
