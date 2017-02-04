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
package org.compiere.model;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.KeyNamePair;
import org.compiere.util.NamePair;

/**
 *	Warehouse Locator Lookup Model.
 * 	(Lookup Model is model.Lookup.java)
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: MLocatorLookup.java,v 1.3 2006/07/30 00:58:04 jjanke Exp $
 * 
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 				<li>BF [ 1892920 ] Locators fieldshould be ordered by Warehouse/Value
 *              <li>FR [ 2306161 ] Removed limit of 200 on max number of locators.
 */
public final class MLocatorLookup extends Lookup implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8932848190660391496L;

	/**
	 *	Constructor
	 *  @param ctx context
	 *  @param WindowNo window no
	 */
	public MLocatorLookup(Properties ctx, int WindowNo)
	{
		super (DisplayType.TableDir, WindowNo);
		m_ctx = ctx;
		//
		m_loader = new Loader();
		m_loader.start();
	}	//	MLocator

	/**	Context						*/
	private Properties          m_ctx;
	protected int				C_Locator_ID;
	private Loader				m_loader;

	/**	Only Warehouse					*/
	private int					m_only_Warehouse_ID = 0;
	/**	Only Product					*/
	private int					m_only_Product_ID = 0;

	/** Storage of data  MLookups		*/
	private volatile LinkedHashMap<Integer,MLocator> m_lookup = new LinkedHashMap<Integer,MLocator>();

	/**
	 *  Dispose
	 */
	public void dispose()
	{
		log.fine("C_Locator_ID=" + C_Locator_ID);
		if (m_loader != null)
		{
			while (m_loader.isAlive())
				m_loader.interrupt();
		}
		m_loader = null;
		if (m_lookup != null)
			m_lookup.clear();
		m_lookup = null;
		//
		super.dispose();
	}   //  dispose

	/**
	 * 	Set Warehouse restriction
	 *	@param only_Warehouse_ID warehouse
	 */
	public void setOnly_Warehouse_ID (int only_Warehouse_ID)
	{
		m_only_Warehouse_ID = only_Warehouse_ID;
	}	//	setOnly_Warehouse_ID

	/**
	 * 	Get Only Wahrehouse
	 *	@return	warehouse
	 */
	public int getOnly_Warehouse_ID()
	{
		return m_only_Warehouse_ID;
	}	//	getOnly_Warehouse_ID

	/**
	 * 	Set Product restriction
	 *	@param only_Product_ID Product
	 */
	public void setOnly_Product_ID (int only_Product_ID)
	{
		m_only_Product_ID = only_Product_ID;
	}	//	setOnly_Product_ID

	/**
	 * 	Get Only Product
	 *	@return	Product
	 */
	public int getOnly_Product_ID()
	{
		return m_only_Product_ID;
	}	//	getOnly_Product_ID

	/**
	 *  Wait until async Load Complete
	 */
	public void loadComplete()
	{
		if (m_loader != null)
		{
			try
			{
				m_loader.join();
			}
			catch (InterruptedException ie)
			{
				log.log(Level.SEVERE, "Join interrupted", ie);
			}
		}
	}   //  loadComplete

	/**
	 *	Get value
	 *  @param key key
	 *  @return value value
	 */
	public NamePair get (Object key)
	{
		if (key == null)
			return null;

		//	try cache
		MLocator loc = (MLocator) m_lookup.get(key);
		if (loc != null)
			return new KeyNamePair (loc.getM_Locator_ID(), loc.toString());

		//	Not found and waiting for loader
		if (m_loader.isAlive())
		{
			log.fine("Waiting for Loader");
			loadComplete();
			//	is most current
			loc = (MLocator) m_lookup.get(key);
		}
		if (loc != null)
			return new KeyNamePair (loc.getM_Locator_ID(), loc.toString());

		//	Try to get it directly
		return getDirect(key, true, null);
	}	//	get

	/**
	 *	Get Display value
	 *  @param value value
	 *  @return String to display
	 */
	public String getDisplay (Object value)
	{
		if (value == null)
			return "";
		//
		NamePair display = get (value);
		if (display == null)
			return "<" + value.toString() + ">";
		return display.toString();
	}	//	getDisplay

	/**
	 *  The Lookup contains the key
	 *  @param key key
	 *  @return true, if lookup contains key
	 */
	public boolean containsKey (Object key)
	{
		return m_lookup.containsKey(key);
	}   //  containsKey

	/**
	 *	Get Data Direct from Table
	 *  @param keyValue integer key value
	 *  @param saveInCache save in cache
	 *  @param trxName transaction
	 *  @return Object directly loaded
	 */
	public NamePair getDirect (Object keyValue, boolean saveInCache, String trxName)
	{
		MLocator loc = getMLocator (keyValue, trxName);
		if (loc == null)
			return null;
		//
		int key = loc.getM_Locator_ID();
		if (saveInCache)
			m_lookup.put(new Integer(key), loc);
		NamePair retValue = new KeyNamePair(key, loc.toString());
		return retValue;
	}	//	getDirect

	/**
	 *	Get Data Direct from Table
	 *  @param keyValue integer key value
	 *  @param trxName transaction
	 *  @return Object directly loaded
	 */
	public MLocator getMLocator (Object keyValue, String trxName)
	{
	//	log.fine( "MLocatorLookup.getDirect " + keyValue.getClass() + "=" + keyValue);
		int M_Locator_ID = -1;
		try
		{
			M_Locator_ID = Integer.parseInt(keyValue.toString());
		}
		catch (Exception e)
		{}
		if (M_Locator_ID == -1)
		{
			log.log(Level.SEVERE, "Invalid key=" + keyValue);
			return null;
		}
		//
		return new MLocator (m_ctx, M_Locator_ID, trxName);
	}	//	getMLocator

	/**
	 * @return  a string representation of the object.
	 */
	public String toString()
	{
		return "MLocatorLookup[Size=" + m_lookup.size() + "]";
	}	//	toString


	/**
	 * 	Is Locator with key valid (Warehouse)
	 *	@param key key
	 *	@return true if valid
	 */
	public boolean isValid (Object key)
	{
		if (key == null)
			return true;
		//	try cache
		MLocator loc = (MLocator) m_lookup.get(key);
		if (loc == null)
			loc = getMLocator(key, null);
		return isValid(loc);
	}	//	isValid

	/**
	 * 	Is Locator with key valid (Warehouse)
	 *	@param locator locator
	 *	@return true if valid
	 */
	public boolean isValid (MLocator locator)
	{
		if (locator == null || getOnly_Warehouse_ID() == 0)
			return true;
		//	Warehouse OK - Product check
		if (getOnly_Warehouse_ID() == locator.getM_Warehouse_ID())
			return locator.isCanStoreProduct(getOnly_Product_ID());
		return false;
	}	//	isValid


	/**************************************************************************
	 *	Loader
	 */
	class Loader extends Thread implements Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 3472186635409000236L;

		/**
		 * 	Loader
		 */
		public Loader()
		{
			super("MLocatorLookup");
		}	//	Loader

		/**
		 *	Load Lookup
		 */
		public void run()
		{
		//	log.config("MLocatorLookup Loader.run " + m_AD_Column_ID);
			//	Set Info	- see VLocator.actionText
			int local_only_warehouse_id = getOnly_Warehouse_ID(); // [ 1674891 ] MLocatorLookup - weird error 
			int local_only_product_id = getOnly_Product_ID();
			
			StringBuffer sql = new StringBuffer("SELECT * FROM M_Locator ")
				.append(" WHERE IsActive='Y'");
			
			if (local_only_warehouse_id != 0)
				sql.append(" AND M_Warehouse_ID=?");
			if (local_only_product_id != 0)
				sql.append(" AND (IsDefault='Y' ")	//	Default Locator
					.append("OR EXISTS (SELECT * FROM M_Product p ")	//	Product Locator
					.append("WHERE p.M_Locator_ID=M_Locator.M_Locator_ID AND p.M_Product_ID=?)")
					.append("OR EXISTS (SELECT * FROM M_Storage s ")	//	Storage Locator
					.append("WHERE s.M_Locator_ID=M_Locator.M_Locator_ID AND s.M_Product_ID=?))");
			sql.append(" ORDER BY ");
			if (local_only_warehouse_id == 0)
				sql.append("(SELECT wh.Name FROM M_Warehouse wh WHERE wh.M_Warehouse_ID=M_Locator.M_Warehouse_ID),");
			sql.append("M_Locator.Value");
			String finalSql = MRole.getDefault(m_ctx, false).addAccessSQL(
				sql.toString(), "M_Locator", MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);
			if (isInterrupted())
			{
				log.log(Level.SEVERE, "Interrupted");
				return;
			}

			//	Reset
			m_lookup.clear();
			int rows = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(finalSql, null);
				int index = 1;
				if (local_only_warehouse_id != 0)
					pstmt.setInt(index++, getOnly_Warehouse_ID());
				if (local_only_product_id != 0)
				{
					pstmt.setInt(index++, getOnly_Product_ID());
					pstmt.setInt(index++, getOnly_Product_ID());
				}
				rs = pstmt.executeQuery();
				//
				while (rs.next())
				{
					MLocator loc = new MLocator(m_ctx, rs, null);
					int M_Locator_ID = loc.getM_Locator_ID();
					m_lookup.put(new Integer(M_Locator_ID), loc);
				}
			}
			catch (SQLException e)
			{
				log.log(Level.SEVERE, finalSql, e);
			}
			finally
			{
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
			log.fine("Complete #" + m_lookup.size());
			if (m_lookup.size() == 0)
				log.finer(finalSql);
		}	//	run
	}	//	Loader

	/**
	 *	Return info as ArrayList containing Locator, waits for the loader to finish
	 *  @return Collection of lookup values
	 */
	public Collection<MLocator> getData ()
	{
		if (m_loader.isAlive())
		{
			log.fine("Waiting for Loader");
			try
			{
				m_loader.join();
			}
			catch (InterruptedException ie)
			{
				log.severe ("Join interrupted - " + ie.getMessage());
			}
		}
		return m_lookup.values();
	}	//	getData

	/**
	 *	Return data as sorted ArrayList
	 *  @param mandatory mandatory
	 *  @param onlyValidated only validated
	 *  @param onlyActive only active
	 * 	@param temporary force load for temporary display
	 *  @return ArrayList of lookup values
	 */
	public ArrayList<Object> getData (boolean mandatory, boolean onlyValidated, boolean onlyActive, boolean temporary)
	{
		//	create list
		Collection<MLocator> collection = getData();
		ArrayList<Object> list = new ArrayList<Object>(collection.size());
		Iterator<MLocator> it = collection.iterator();
		while (it.hasNext())
		{
			MLocator loc = it.next();
			if (isValid(loc))				//	only valid warehouses
				list.add(loc);
		}

		/**	Sort Data
		MLocator l = new MLocator (m_ctx, 0);
		if (!mandatory)
			list.add (l);
		Collections.sort (list, l);
		**/
		return list;
	}	//	getArray


	/**
	 *	Refresh Values
	 *  @return new size of lookup
	 */
	public int refresh()
	{
		log.fine("start");
		m_loader = new Loader();
		m_loader.start();
		try
		{
			m_loader.join();
		}
		catch (InterruptedException ie)
		{
		}
		log.info("#" + m_lookup.size());
		return m_lookup.size();
	}	//	refresh

	/**
	 *	Get underlying fully qualified Table.Column Name
	 *  @return Table.ColumnName
	 */
	public String getColumnName()
	{
		return "M_Locator.M_Locator_ID";
	}	//	getColumnName

}	//	MLocatorLookup
