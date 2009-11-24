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
 *****************************************************************************/
package org.compiere.util;

import java.util.ArrayList;

/**
 *  Adempiere Cache Management
 *
 *  @author Jorg Janke
 *  @version $Id: CacheMgt.java,v 1.2 2006/07/30 00:54:35 jjanke Exp $
 */
public class CacheMgt
{
	/**
	 * 	Get Cache Management
	 * 	@return Cache Manager
	 */
	public static synchronized CacheMgt get()
	{
		if (s_cache == null)
			s_cache = new CacheMgt();
		return s_cache;
	}	//	get

	/**	Singleton					*/
	private static CacheMgt		s_cache = null;

	/**
	 *	Private Constructor
	 */
	private CacheMgt()
	{
	}	//	CacheMgt

	/**	List of Instances				*/
	private ArrayList<CacheInterface>	m_instances = new ArrayList<CacheInterface>();
	/** List of Table Names				*/
	private ArrayList<String>	m_tableNames = new ArrayList<String>();
	/** Logger							*/
	private static CLogger		log = CLogger.getCLogger(CacheMgt.class);

	
	/**************************************************************************
	 * 	Register Cache Instance
	 *	@param instance Cache
	 *	@return true if added
	 */
	@SuppressWarnings("unchecked")
	public synchronized boolean register (CacheInterface instance)
	{
		if (instance == null)
			return false;
		if (instance instanceof CCache)
		{
			String tableName = ((CCache)instance).getName(); 
			m_tableNames.add(tableName);
		}
		return m_instances.add (instance);
	}	//	register

	/**
	 * 	Un-Register Cache Instance
	 *	@param instance Cache
	 *	@return true if removed
	 */
	public boolean unregister (CacheInterface instance)
	{
		if (instance == null)
			return false;
		boolean found = false;
		//	Could be included multiple times
		for (int i = m_instances.size()-1; i >= 0; i--)
		{
			CacheInterface stored = (CacheInterface)m_instances.get(i);
			if (instance.equals(stored))
			{
				m_instances.remove(i);
				found = true;
			}
		}
		return found;
	}	//	unregister

	/**************************************************************************
	 * 	Reset All registered Cache
	 * 	@return number of deleted cache entries
	 */
	public int reset()
	{
		int counter = 0;
		int total = 0;
		for (int i = 0; i < m_instances.size(); i++)
		{
			CacheInterface stored = (CacheInterface)m_instances.get(i);
			if (stored != null && stored.size() > 0)
			{
				log.fine(stored.toString());
				total += stored.reset();
				counter++;
			}
		}
		log.fine("#" + counter + " (" + total + ")");
		return total;
	}	//	reset

	/**
	 * 	Reset registered Cache
	 * 	@param tableName table name
	 * 	@return number of deleted cache entries
	 */
	public int reset (String tableName)
	{
		return reset (tableName, 0);
	}	//	reset
	
	/**
	 * 	Reset registered Cache
	 * 	@param tableName table name
	 * 	@param Record_ID record if applicable or 0 for all
	 * 	@return number of deleted cache entries
	 */
	@SuppressWarnings("unchecked")
	public int reset (String tableName, int Record_ID)
	{
		if (tableName == null)
			return reset();
	//	if (tableName.endsWith("Set"))
	//		tableName = tableName.substring(0, tableName.length()-3);
		if (!m_tableNames.contains(tableName))
			return 0;
		//
		int counter = 0;
		int total = 0;
		for (int i = 0; i < m_instances.size(); i++)
		{
			CacheInterface stored = (CacheInterface)m_instances.get(i);
			if (stored != null && stored instanceof CCache)
			{
				CCache cc = (CCache)stored;
				if (cc.getName().startsWith(tableName))		//	reset lines/dependent too
				{
				//	if (Record_ID == 0)
					{
						log.fine("(all) - " + stored);
						total += stored.reset();
						counter++;
					}
				}
			}
		}
		log.fine(tableName + ": #" + counter + " (" + total + ")");

		return total;
	}	//	reset
	
	/**
	 * 	Total Cached Elements
	 *	@return count
	 */
	@SuppressWarnings("unchecked")
	public int getElementCount()
	{
		int total = 0;
		for (int i = 0; i < m_instances.size(); i++)
		{
			CacheInterface stored = (CacheInterface)m_instances.get(i);
			if (stored != null && stored.size() > 0)
			{
				log.fine(stored.toString());
				if (stored instanceof CCache)
					total += ((CCache)stored).sizeNoExpire();
				else
					total += stored.size();
			}
		}
		return total;
	}	//	getElementCount
	
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("CacheMgt[");
		sb.append("Instances=")
			.append(m_instances.size())
			.append("]");
		return sb.toString ();
	}	//	toString
	
	/**
	 * 	Extended String Representation
	 *	@return info
	 */
	public String toStringX ()
	{
		StringBuffer sb = new StringBuffer ("CacheMgt[");
		sb.append("Instances=")
			.append(m_instances.size())
			.append(", Elements=")
			.append(getElementCount())
			.append("]");
		return sb.toString ();
	}	//	toString

}	//	CCache
