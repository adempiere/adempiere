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
package org.compiere.model;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import org.compiere.util.*;

/**
 *	Warehouse Model
 *	
 *  @author Jorg Janke
 *  @author victor.perez@e-evolution.com
 *  @see FR [ 1966337 ] New Method to get the Transit Warehouse based in ID Org http://sourceforge.net/tracker/index.php?func=detail&aid=1966337&group_id=176962&atid=879335
 *  @version $Id: MWarehouse.java,v 1.3 2006/07/30 00:58:05 jjanke Exp $
 */
public class MWarehouse extends X_M_Warehouse
{
	/**
	 * 	Get from Cache
	 *	@param ctx context
	 *	@param M_Warehouse_ID id
	 *	@return warehouse
	 */
	public static MWarehouse get (Properties ctx, int M_Warehouse_ID)
	{
		Integer key = new Integer(M_Warehouse_ID);
		MWarehouse retValue = (MWarehouse)s_cache.get(key);
		if (retValue != null)
			return retValue;
		//
		retValue = new MWarehouse (ctx, M_Warehouse_ID, null);
		s_cache.put (key, retValue);
		return retValue;
	}	//	get

	/**
	 * 	Get Warehouses for Org
	 *	@param ctx context
	 *	@param AD_Org_ID id
	 *	@return warehouse
	 */
	public static MWarehouse[] getForOrg (Properties ctx, int AD_Org_ID)
	{
		ArrayList<MWarehouse> list = new ArrayList<MWarehouse>();
		String sql = "SELECT * FROM M_Warehouse WHERE IsActive = 'Y' AND AD_Org_ID=? ORDER BY Created";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, AD_Org_ID);
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (new MWarehouse (ctx, rs, null));
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		MWarehouse[] retValue = new MWarehouse[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	get
	
	/**
	 *  FR [ 1966337 ] 
	 * 	Get Warehouses Transit for Org
	 *	@param ctx context
	 *	@param AD_Org_ID id
	 *	@return warehouse
	 */
	public static MWarehouse[] getInTransitForOrg (Properties ctx, int AD_Org_ID)
	{
		ArrayList<MWarehouse> list = new ArrayList<MWarehouse>();
		String sql = "SELECT * FROM M_Warehouse WHERE IsActive = 'Y' AND IsInTransit = 'Y' AND AD_Org_ID=? ORDER BY Created";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, AD_Org_ID);
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (new MWarehouse (ctx, rs, null));
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		MWarehouse[] retValue = new MWarehouse[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	get
	
	/**	Cache					*/
	private static CCache<Integer,MWarehouse> s_cache = new CCache<Integer,MWarehouse>("M_Warehouse", 5);
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MWarehouse.class);
	
	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param M_Warehouse_ID id
	 *	@param trxName transaction
	 */
	public MWarehouse (Properties ctx, int M_Warehouse_ID, String trxName)
	{
		super(ctx, M_Warehouse_ID, trxName);
		if (M_Warehouse_ID == 0)
		{
		//	setValue (null);
		//	setName (null);
		//	setC_Location_ID (0);
			setSeparator ("*");	// *
		}
	}	//	MWarehouse

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MWarehouse (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MWarehouse

	/**
	 * 	Organization Constructor
	 *	@param org parent
	 */
	public MWarehouse (MOrg org)
	{
		this (org.getCtx(), 0, org.get_TrxName());
		setClientOrg(org);
		setValue (org.getValue());
		setName (org.getName());
		if (org.getInfo() != null)
			setC_Location_ID (org.getInfo().getC_Location_ID());
	}	//	MWarehouse

	/**	Warehouse Locators				*/
	private MLocator[]	m_locators = null;
	
	/**
	 * 	Get Locators
	 *	@param reload if true reload
	 *	@return array of locators
	 */
	public MLocator[] getLocators(boolean reload)
	{
		if (!reload && m_locators != null)
			return m_locators;
		//
		String sql = "SELECT * FROM M_Locator WHERE IsActive = 'Y' AND M_Warehouse_ID=? ORDER BY X,Y,Z";
		ArrayList<MLocator> list = new ArrayList<MLocator>();
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, getM_Warehouse_ID());
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add(new MLocator (getCtx(), rs, null));
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		//
		m_locators = new MLocator[list.size()];
		list.toArray (m_locators);
		return m_locators;
	}	//	getLocators
	
	/**
	 * 	Get Default Locator
	 *	@return (first) default locator
	 */
	public MLocator getDefaultLocator()
	{
		MLocator[] locators = getLocators(false);
		for (int i = 0; i < locators.length; i++)
		{
			if (locators[i].isDefault() && locators[i].isActive())
				return locators[i];
		}
		//	No Default - first one
		if (locators.length > 0)
		{
			log.warning("No default locator for " + getName());
			return locators[0];
		}
		//	No Locator - create one
		MLocator loc = new MLocator (this, "Standard");
		loc.setIsDefault(true);
		loc.save();
		log.info("Created default locator for " + getName());
		return loc;
	}	//	getLocators
	
	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (newRecord && success)
			insert_Accounting("M_Warehouse_Acct", "C_AcctSchema_Default", null);
		
		return success;
	}	//	afterSave

	/**
	 * 	Before Delete
	 *	@return true
	 */
	protected boolean beforeDelete ()
	{
		return delete_Accounting("M_Warehouse_Acct"); 
	}	//	beforeDelete

}	//	MWarehouse
