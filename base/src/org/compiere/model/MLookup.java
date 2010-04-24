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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.logging.Level;

import org.compiere.util.CLogMgt;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.KeyNamePair;
import org.compiere.util.NamePair;
import org.compiere.util.ValueNamePair;

/**
 *	An intelligent MutableComboBoxModel, which determines what can be cached.
 *  <pre>
 *      Validated   - SQL is final / not dynamic
 *      AllLoaded   - All Records are loaded
 *
 *		Get Info about Lookup
 *		-	SQL
 *		-	KeyColumn
 *		-	Zoom Target
 *  </pre>
 * 	@author 	Jorg Janke
 * 	@version 	$Id: MLookup.java,v 1.4 2006/10/07 00:58:57 jjanke Exp $
 */
public final class MLookup extends Lookup implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5784044288965615466L;

	/**
	 *  MLookup Constructor
	 *  @param info info
	 *  @param TabNo tab no
	 */
	public MLookup (MLookupInfo info, int TabNo)
	{
		super(info.DisplayType, info.WindowNo);
		m_info = info;
		log.fine(m_info.KeyColumn);

		//  load into local lookup, if already cached
		if (MLookupCache.loadFromCache (m_info, m_lookup))
			return;

		//  Don't load Search or CreatedBy/UpdatedBy
		if (m_info.DisplayType == DisplayType.Search 
			|| m_info.IsCreadedUpdatedBy)
			return;
		//  Don't load Parents/Keys
		if (m_info.IsParent || m_info.IsKey)
		{
			m_hasInactive = true;		//	creates focus listener for dynamic loading
			return;						//	required when parent needs to be selected (e.g. price from product)
		}
		//
		//m_loader = new MLoader();
	//	if (TabNo != 0)
	//		m_loader.setPriority(Thread.NORM_PRIORITY - 1);
		//m_loader.start();
		//m_loader.run();		//	test sync call
	}	//	MLookup

	/** Inactive Marker Start       */
	public static final String  INACTIVE_S = "~";
	/** Inactive Marker End         */
	public static final String  INACTIVE_E = "~";
	/** Number of max rows to load	*/
	private static final int	MAX_ROWS = 10000;
	/**	Indicator for Null			*/
	private static Integer 		MINUS_ONE = new Integer(-1);


	/** The Lookup Info Value Object        */
	private MLookupInfo         m_info = null;

	/** Storage of data  Key-NamePair	*/
	private volatile LinkedHashMap<Object,Object>	m_lookup = new LinkedHashMap<Object,Object>();
	/** The Data Loader                 */
	private MLoader				m_loader;
	//

	/** All Data loaded                 */
	private boolean             m_allLoaded = false;
	/** Inactive records exists         */
	private boolean 		    m_hasInactive = false;

	/*  Refreshing - disable cashing    */
	private boolean             m_refreshing = false;
	/** Next Read for Parent			*/
	private long				m_nextRead = 0;
	
	/**
	 *  Dispose
	 */
	public void dispose()
	{
		if (m_info != null)
			log.fine(m_info.KeyColumn + ": dispose");
		if (m_loader != null && m_loader.isAlive())
			m_loader.interrupt();
		m_loader = null;
		//
		if (m_lookup != null)
			m_lookup.clear();
		m_lookup = null;
		if (m_lookupDirect != null)
			m_lookupDirect.clear();
		m_lookupDirect = null;
		//
		m_info = null;
		//
		super.dispose();
	}   //  dispose

	/**
	 *  Wait until async Load Complete
	 */
	public void loadComplete()
	{
		if (m_loader != null && m_loader.isAlive())
		{
			try
			{
				m_loader.join();
				m_loader = null;
			}
			catch (InterruptedException ie)
			{
				log.log(Level.SEVERE, m_info.KeyColumn + ": Interrupted", ie);
			}
		}
	}   //  loadComplete

	/**
	 *	Get value (name) for key.
	 *  If not found return null;
	 *  @param key key	(Integer for Keys or String for Lists)
	 *  @return value
	 */
	public NamePair get (Object key)
	{
		if (key == null || MINUS_ONE.equals(key))	//	indicator for null
			return null;

		//auto refresh parent lookup
		if (m_info.IsParent )
		{
			if (m_nextRead > 0 && m_nextRead < System.currentTimeMillis()) 
			{
				m_lookup.clear();
				if (m_lookupDirect != null)
					m_lookupDirect.clear();				
			}			
			m_nextRead = System.currentTimeMillis() + 5000;	//	5 sec
		} 
		
		//	try cache
		NamePair retValue = (NamePair)m_lookup.get(key);
		if (retValue != null)
			return retValue;

		//	Not found and waiting for loader
		if (m_loader != null && m_loader.isAlive())
		{
			log.finer((m_info.KeyColumn==null ? "ID="+m_info.Column_ID : m_info.KeyColumn) + ": waiting for Loader");
			loadComplete();
			//	is most current
			retValue = (NamePair)m_lookup.get(key);
			if (retValue != null)
				return retValue;
		}

		//  Always check for parents - not if we SQL was validated and completely loaded
		if (!m_info.IsParent && m_info.IsValidated && m_allLoaded)
		{
			log.finer(m_info.KeyColumn + ": <NULL> - " + key // + "(" + key.getClass()
					+ "; Size=" + m_lookup.size());
		//	log.finest( m_lookup.keySet().toString(), "ContainsKey = " + m_lookup.containsKey(key));
			//  also for new values and inactive ones
			return getDirect(key, false, true);		//	cache locally    
		}

		log.finest (m_info.KeyColumn + ": " + key
				+ "; Size=" + m_lookup.size() + "; Validated=" + m_info.IsValidated
				+ "; All Loaded=" + m_allLoaded + "; HasInactive=" + m_hasInactive);
		//	never loaded
		if (!m_allLoaded 
			&& m_lookup.size() == 0 
			&& !m_info.IsCreadedUpdatedBy
			&& !m_info.IsParent
			&& getDisplayType() != DisplayType.Search)
		{
			m_loader = new MLoader();
			m_loader.run();		//	sync!
			retValue = (NamePair)m_lookup.get(key);
			if (retValue != null)
				return retValue;
		}
		//	Try to get it directly
		boolean cacheLocal = m_info.IsValidated ; 
		return getDirect(key, false, cacheLocal);	//	do NOT cache	
	}	//	get

	/**
	 *	Get Display value (name).
	 *  If not found return key embedded in inactive signs.
	 *  @param key key
	 *  @return value
	 */
	public String getDisplay (Object key)
	{
		if (key == null)
			return "";
		//
		Object display = get (key);
		if (display == null)
			return "<" + key.toString() + ">";
		return display.toString();
	}	//	getDisplay

	/**
	 *  The Lookup contains the key
	 *  @param key key
	 *  @return true if key is known
	 */
	public boolean containsKey (Object key)
	{
		//should check direct too
		if (m_lookup.containsKey(key))
			return true;
		else {
			if (m_lookup.size() > 0)
				return false;
			else
				return ( get(key) != null );			
		}
	}   //  containsKey

	/**
	 * @return  a string representation of the object.
	 */
	public String toString()
	{
		return "MLookup[" + m_info.KeyColumn + ",Column_ID=" + m_info.Column_ID
			+ ",Size=" + m_lookup.size() + ",Validated=" + isValidated()
			+ "-" + getValidation()
			+ "]";
	}	//	toString

	/**
	 * Indicates whether some other object is "equal to" this one.
	 * @param   obj   the reference object with which to compare.
	 * @return  <code>true</code> if this object is the same as the obj
	 *          argument; <code>false</code> otherwise.
	 */
	public boolean equals(Object obj)
	{
		if (obj instanceof MLookup)
		{
			MLookup ll = (MLookup)obj;
			if (ll.m_info.Column_ID == this.m_info.Column_ID)
				return true;
		}
		return false;
	}	//	equals

	/**
	 *	Return Size
	 *  @return size
	 */
	public int size()
	{
		return m_lookup.size();
	}	//	size

	/**
	 *	Is it all loaded
	 *  @return true, if all loaded
	 */
	public boolean isAllLoaded()
	{
		return m_allLoaded;
	}	//	isAllLoaded

	/**
	 *	Is the List fully Validated
	 *  @return true, if validated
	 */
	public boolean isValidated()
	{
		if (m_info == null)
			return false;
		//return m_info.IsValidated;
		return isValidated(m_info);
	}	//	isValidated

	/**
	 *  Get Validation SQL
	 *  @return Validation SQL
	 */
	public String getValidation()
	{
		return m_info.ValidationCode;
	}   //  getValidation

	/**
	 *  Get Reference Value
	 *  @return Reference Value
	 */
	public int getAD_Reference_Value_ID()
	{
		return m_info.AD_Reference_Value_ID;
	}   //  getAD_Reference_Value_ID


	/**
	 *  Has inactive elements in list
	 *  @return true, if list contains inactive values
	 */
	public boolean hasInactive()
	{
		return m_hasInactive;
	}   //  hasInactive

	/**
	 *	Return info as ArrayList containing Value/KeyNamePair
	 *  @param onlyValidated only validated
	 * 	@param loadParent get Data even for parent lookups
	 *  @return List
	 */
	private ArrayList<Object> getData (boolean onlyValidated, boolean loadParent)
	{
		if (m_loader != null && m_loader.isAlive())
		{
			log.fine((m_info.KeyColumn==null ? "ID="+m_info.Column_ID : m_info.KeyColumn) 
				+ ": waiting for Loader");
			loadComplete();
		}

		//	Never Loaded (correctly)
		if (!m_allLoaded || m_lookup.size() == 0)
			loadData (loadParent);

		//	already validation included
		//if (m_info.IsValidated)
		boolean validated = this.isValidated(m_info);
		if (validated)
			return new ArrayList<Object>(m_lookup.values());
		

		//if (!m_info.IsValidated && onlyValidated)
		if (!validated && onlyValidated)
		{
			loadData (loadParent);
			log.fine(m_info.KeyColumn + ": Validated - #" + m_lookup.size());
		}

		return new ArrayList<Object>(m_lookup.values());
	}	//	getData

	/**
	 *	Return data as Array containing Value/KeyNamePair
	 *  @param mandatory if not mandatory, an additional empty value is inserted
	 *  @param onlyValidated only validated
	 *  @param onlyActive only active
	 * 	@param temporary force load for temporary display
	 *  @return list
	 */
	public ArrayList<Object> getData (boolean mandatory, boolean onlyValidated, boolean onlyActive, boolean temporary)
	{
		//	create list
		ArrayList<Object> list = getData (onlyValidated, true);
		
		//  Remove inactive choices
		if (onlyActive && m_hasInactive)
		{
			//  list from the back
			for (int i = list.size(); i > 0; i--)
			{
				Object o = list.get(i-1);
				if (o != null)
				{
					String s = o.toString();
					if (s.startsWith(INACTIVE_S) && s.endsWith(INACTIVE_E))
						list.remove(i-1);
				}
			}
		}

		//	Add Optional (empty) selection
		if (!mandatory)
		{
			NamePair p = null;
			if (m_info.KeyColumn != null && m_info.KeyColumn.endsWith("_ID"))
				p = new KeyNamePair (-1, "");
			else
				p = new ValueNamePair ("", "");
			list.add(0, p);
		}

		return list;
	}	//	getData

	/**	Save getDirect last return value */	
	private HashMap<Object,Object>	m_lookupDirect = null;
	/**	Save last unsuccessful				*/
	private Object					m_directNullKey = null;

	/**
	 *	Get Data Direct from Table.
	 *  @param key key
	 *  @param saveInCache save in cache for r/w
	 * 	@param cacheLocal cache locally for r/o
	 *  @return value
	 */
	public NamePair getDirect (Object key, boolean saveInCache, boolean cacheLocal)
	{
		//	Nothing to query
		if (key == null || m_info.QueryDirect == null || m_info.QueryDirect.length() == 0)
			return null;
		if (key.equals(m_directNullKey))
			return null;
		//
		NamePair directValue = null;
		if (m_lookupDirect != null)		//	Lookup cache
		{
			directValue = (NamePair)m_lookupDirect.get(key);
			if (directValue != null)
				return directValue;
		}
		log.finer(m_info.KeyColumn + ": " + key 
				+ ", SaveInCache=" + saveInCache + ",Local=" + cacheLocal);
		boolean isNumber = m_info.KeyColumn.endsWith("_ID");
		try
		{
			//	SELECT Key, Value, Name FROM ...
			PreparedStatement pstmt = DB.prepareStatement(m_info.QueryDirect, null);
			if (isNumber)
				pstmt.setInt(1, Integer.parseInt(key.toString()));
			else
				pstmt.setString(1, key.toString());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				String name = rs.getString(3);
				if (isNumber)
				{
					int keyValue = rs.getInt(1);
					KeyNamePair p = new KeyNamePair(keyValue, name);
					if (saveInCache)		//	save if
						m_lookup.put(new Integer(keyValue), p);
					directValue = p;
				}
				else
				{
					String value = rs.getString(2);
					ValueNamePair p = new ValueNamePair(value, name);
					if (saveInCache)		//	save if
						m_lookup.put(value, p);
					directValue = p;
				}
				if (rs.next())
					log.log(Level.SEVERE, m_info.KeyColumn + ": Not unique (first returned) for "
						+ key + " SQL=" + m_info.QueryDirect);
			}
			else
			{
				m_directNullKey = key;
				directValue = null;
			}

			rs.close();
			pstmt.close();
			if (CLogMgt.isLevelFinest())
				log.finest(m_info.KeyColumn + ": " + directValue + " - " + m_info);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, m_info.KeyColumn + ": SQL=" + m_info.QueryDirect + "; Key=" + key, e);
			directValue = null;
		}
		//	Cache Local if not added to R/W cache
		if (cacheLocal  && !saveInCache && directValue != null)
		{
			if (m_lookupDirect == null)
				m_lookupDirect = new HashMap<Object,Object>();
			m_lookupDirect.put(key, directValue);
		}
		m_hasInactive = true;
		return directValue;
	}	//	getDirect

	/**
	 *	Get Zoom
	 *  @return Zoom AD_Window_ID
	 */
	public int getZoom()
	{
		return m_info.ZoomWindow;
	}	//	getZoom

	/**
	 *	Get Zoom
	 * 	@param query query
	 *  @return Zoom Window
	 */
	public int getZoom(MQuery query)
	{
		if (m_info.ZoomWindowPO == 0 || query == null)
			return m_info.ZoomWindow;
		//	Need to check SO/PO
		boolean isSOTrx = DB.isSOTrx(m_info.TableName, query.getWhereClause(false));
		//
		if (!isSOTrx)
			return m_info.ZoomWindowPO;
		return m_info.ZoomWindow;
	}	//	getZoom

	/**
	 *	Get Zoom Query String
	 *  @return Zoom SQL Where Clause
	 */
	public MQuery getZoomQuery()
	{
		return m_info.ZoomQuery;
	}	//	getZoom

	/**
	 *	Get underlying fully qualified Table.Column Name
	 *  @return Key Column
	 */
	public String getColumnName()
	{
		return m_info.KeyColumn;
	}	//	g2etColumnName

	/**
	 *	Refresh & return number of items read.
	 * 	Get get data of parent lookups
	 *  @return no of items read
	 */
	public int refresh ()
	{
		if (m_refreshing) return 0;
		return refresh(true);
	}	//	refresh

	/**
	 *	Refresh & return number of items read
	 * 	@param loadParent get data of parent lookups
	 *  @return no of items refresh
	 */
	public int refresh (boolean loadParent)
	{
		if (m_refreshing) return 0;
		if (!loadParent && m_info.IsParent)
			return 0;
		//  Don't load Search or CreatedBy/UpdatedBy
		if (m_info.DisplayType == DisplayType.Search 
			|| m_info.IsCreadedUpdatedBy) 
		{
			//clear direct cache
			removeAllElements();
			return 0;
		}
		
		m_refreshing = true;
		//force refresh
		m_lookup.clear();
		fillComboBox(isMandatory(), true, true, false);
		m_refreshing = false;
		return m_lookup.size();
	}	//	refresh

	/**
	 * Do the actual loading from database
	 * @param loadParent
	 * @return number of records loaded
	 */
	private int loadData(boolean loadParent)
	{
		if (!loadParent && m_info.IsParent)
			return 0;
		//  Don't load Search or CreatedBy/UpdatedBy
		if (m_info.DisplayType == DisplayType.Search 
			|| m_info.IsCreadedUpdatedBy)
			return 0;
		log.fine(m_info.KeyColumn + ": start");
		
		m_loader = new MLoader();
		m_loader.start();
		loadComplete();
		log.fine(m_info.KeyColumn + ": #" + m_lookup.size());
		
		return m_lookup.size();
	}	//	refresh
	
	/**
	 * 	Remove All cached Elements
	 *	@see org.compiere.model.Lookup#removeAllElements()
	 */
	@Override
	public void removeAllElements()
	{
		super.removeAllElements ();
		m_lookup.clear();
		if (m_lookupDirect != null)
			m_lookupDirect.clear();
	}	//	removeAllElements
	
	private boolean isValidated(MLookupInfo info)
	{
		if (info.IsValidated) return true;
		if (info.ValidationCode.length() == 0) return true;
		String validation = Env.parseContext(m_info.ctx, m_info.WindowNo, m_info.ValidationCode, false);
		if (validation.equals(info.parsedValidationCode)) return true;
		return false;
	}
	
	@Override
	public String getInfoFactoryClass() {
		return m_info.InfoFactoryClass != null ? m_info.InfoFactoryClass : "";
	}



	/**************************************************************************
	 *	MLookup Loader
	 */
	class MLoader extends Thread implements Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = -7868426685745727939L;

		/**
		 * 	MLoader Constructor
		 */
		public MLoader()
		{
			super("MLoader-" + m_info.KeyColumn);
		//	if (m_info.KeyColumn.indexOf("C_InvoiceLine_ID") != -1)
		//		log.info(m_info.KeyColumn);
		}	//	Loader
		
		private long m_startTime = System.currentTimeMillis();

		/**
		 *	Load Lookup
		 */
		public void run()
		{
			long startTime = System.currentTimeMillis();
			MLookupCache.loadStart (m_info);
			String sql = m_info.Query;

			//	not validated
			if (!m_info.IsValidated)
			{
				String validation = Env.parseContext(m_info.ctx, m_info.WindowNo, m_info.ValidationCode, false);
				m_info.parsedValidationCode = validation;
				if (validation.length() == 0 && m_info.ValidationCode.length() > 0)
				{
					log.fine(m_info.KeyColumn + ": Loader NOT Validated: " + m_info.ValidationCode);
					// Bug 1843862 - Lookups not working on Report Viewer window
					// globalqss - when called from Viewer window ignore error about unparsabe context variables
					// there is no context in report viewer windows
					if (Ini.isClient() == false || !Env.getWindow(m_info.WindowNo).getClass().getName().equals("org.compiere.print.Viewer")) {
						m_lookup.clear();
						return;
					}
				}
				else
				{					
					log.fine(m_info.KeyColumn + ": Loader Validated: " + validation);
					int posFrom = sql.lastIndexOf(" FROM ");
					boolean hasWhere = sql.indexOf(" WHERE ", posFrom) != -1;
					//
					int posOrder = sql.lastIndexOf(" ORDER BY ");
					if (posOrder != -1)
						sql = sql.substring(0, posOrder) 
							+ (hasWhere ? " AND " : " WHERE ") 
							+ validation
							+ sql.substring(posOrder);
					else
						sql += (hasWhere ? " AND " : " WHERE ") 
 
							+ validation;
					if (CLogMgt.isLevelFinest())
						log.fine(m_info.KeyColumn + ": Validation=" + validation);
				}
			}
			//	check
			if (isInterrupted())
			{
				log.log(Level.WARNING, m_info.KeyColumn + ": Loader interrupted");
				return;
			}
			//
			if (CLogMgt.isLevelFiner())
				Env.setContext(m_info.ctx, Env.WINDOW_MLOOKUP, m_info.Column_ID, m_info.KeyColumn, sql);
			if (CLogMgt.isLevelFinest())
				log.fine(m_info.KeyColumn + ": " + sql);
			
			//	Reset
			m_lookup.clear();
			boolean isNumber = m_info.KeyColumn.endsWith("_ID");
			m_hasInactive = false;
			int rows = 0;
			try
			{
				//	SELECT Key, Value, Name, IsActive FROM ...
				PreparedStatement pstmt = DB.prepareStatement(sql, null);
				ResultSet rs = pstmt.executeQuery();

				//	Get first ... rows
				m_allLoaded = true;
				while (rs.next())
				{
					if (rows++ > MAX_ROWS)
					{
						log.warning(m_info.KeyColumn + ": Loader - Too many records");
						m_allLoaded = false;
						break;
					}
					//  check for interrupted every 10 rows
					if (rows % 20 == 0 && isInterrupted())
						break;

					//  load data
					String name = rs.getString(3);
					boolean isActive = rs.getString(4).equals("Y");
					if (!isActive)
					{
						name = INACTIVE_S + name + INACTIVE_E;
						m_hasInactive = true;
					}
					if (isNumber)
					{
						int key = rs.getInt(1);
						KeyNamePair p = new KeyNamePair(key, name);
						m_lookup.put(new Integer(key), p);
					}
					else
					{
						String value = rs.getString(2);
						ValueNamePair p = new ValueNamePair(value, name);
						m_lookup.put(value, p);
					}
				//	log.fine( m_info.KeyColumn + ": " + name);
				}
				rs.close();
				pstmt.close();
			}
			catch (SQLException e)
			{
				log.log(Level.SEVERE, m_info.KeyColumn + ", " + m_info.Column_ID + " : Loader - " + sql, e);
			}
			int size = m_lookup.size();
			log.finer(m_info.KeyColumn
					+ " (" + m_info.Column_ID + "):"
				//	+ " ID=" + m_info.AD_Column_ID + " " +
					+ " - Loader complete #" + size + " - all=" + m_allLoaded
					+ " - ms=" + String.valueOf(System.currentTimeMillis()-m_startTime)
					+ " (" + String.valueOf(System.currentTimeMillis()-startTime) + ")");
		//	if (m_allLoaded)
			MLookupCache.loadEnd (m_info, m_lookup);
		}	//	run
	}	//	Loader

}	//	MLookup
