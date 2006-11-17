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

import java.math.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import org.compiere.util.*;

/**
 *	Price List Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MPriceList.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 */
public class MPriceList extends X_M_PriceList
{
	/**
	 * 	Get Price List (cached)
	 *	@param ctx context
	 *	@param M_PriceList_ID id
	 *	@param trxName transaction
	 *	@return PriceList
	 */
	public static MPriceList get (Properties ctx, int M_PriceList_ID, String trxName)
	{
		Integer key = new Integer (M_PriceList_ID);
		MPriceList retValue = (MPriceList)s_cache.get(key);
		if (retValue == null)
		{
			retValue = new MPriceList (ctx, M_PriceList_ID, trxName);
			s_cache.put(key, retValue);
		}
		return retValue;		
	}	//	get
	
	/**
	 * 	Get Default Price List for Client (cached)
	 *	@param ctx context
	 *	@param IsSOPriceList SO or PO
	 *	@return PriceList or null
	 */
	public static MPriceList getDefault (Properties ctx, boolean IsSOPriceList)
	{
		int AD_Client_ID = Env.getAD_Client_ID(ctx);
		MPriceList retValue = null;
		//	Search for it in cache
		Iterator it = s_cache.values().iterator();
		while (it.hasNext())
		{
			retValue = (MPriceList)it.next();
			if (retValue.isDefault() && retValue.getAD_Client_ID() == AD_Client_ID)
				return retValue;
		}
		
		/**	Get from DB **/
		retValue = null;
		String sql = "SELECT * FROM M_PriceList "
			+ "WHERE AD_Client_ID=?"
			+ " AND IsDefault='Y'"
			+ " AND IsSOPriceList='Y'"
			+ "ORDER BY M_PriceList_ID";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_Client_ID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				retValue = new MPriceList (ctx, rs, null);
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		//	Return value
		if (retValue != null)
		{
			Integer key = new Integer (retValue.getM_PriceList_ID());
			s_cache.put(key, retValue);
		}
		return retValue;
	}	//	getDefault
	
	/**
	 * 	Get Standard Currency Precision
	 *	@param ctx context 
	 *	@param M_PriceList_ID price list
	 *	@return precision
	 */
	public static int getStandardPrecision (Properties ctx, int M_PriceList_ID)
	{
		MPriceList pl = MPriceList.get(ctx, M_PriceList_ID, null);
		return pl.getStandardPrecision();
	}	//	getStandardPrecision
	
	/**
	 * 	Get Price Precision
	 *	@param ctx context 
	 *	@param M_PriceList_ID price list
	 *	@return precision
	 */
	public static int getPricePrecision (Properties ctx, int M_PriceList_ID)
	{
		MPriceList pl = MPriceList.get(ctx, M_PriceList_ID, null);
		return pl.getPricePrecisionInt();
	}	//	getPricePrecision
	
	/** Static Logger					*/
	private static CLogger 	s_log = CLogger.getCLogger(MPriceList.class);
	/** Cache of Price Lists			*/
	private static CCache<Integer,MPriceList> s_cache = new CCache<Integer,MPriceList>("M_PriceList", 5);
	
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param M_PriceList_ID id
	 *	@param trxName transaction
	 */
	public MPriceList(Properties ctx, int M_PriceList_ID, String trxName)
	{
		super(ctx, M_PriceList_ID, trxName);
		if (M_PriceList_ID == 0)
		{
			setEnforcePriceLimit (false);
			setIsDefault (false);
			setIsSOPriceList (false);
			setIsTaxIncluded (false);
			setPricePrecision (2);	// 2
		//	setName (null);
		//	setC_Currency_ID (0);
		}
	}	//	MPriceList

	/**
	 * 	Load Cosntructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MPriceList (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MPriceList

	/**	Cached PLV					*/
	private MPriceListVersion	m_plv = null;
	/** Cached Precision			*/
	private Integer				m_precision = null;

	/**
	 * 	Get Price List Version
	 *	@param valid date where PLV must be valid or today if null
	 *	@return PLV
	 */
	public MPriceListVersion getPriceListVersion (Timestamp valid)
	{
		if (valid == null)
			valid = new Timestamp (System.currentTimeMillis());
		//	Assume there is no later
		if (m_plv != null && m_plv.getValidFrom().before(valid))
			return m_plv;

		String sql = "SELECT * FROM M_PriceList_Version "
			+ "WHERE M_PriceList_ID=?"
			+ " AND TRUNC(ValidFrom)<=? AND IsActive='Y'"
			+ "ORDER BY ValidFrom DESC";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getM_PriceList_ID());
			pstmt.setTimestamp(2, valid);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				m_plv = new MPriceListVersion (getCtx(), rs, get_TrxName());
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		if (m_plv == null)
			log.warning("None found M_PriceList_ID=" 
				+ getM_PriceList_ID() + " - " + valid + " - " + sql);
		else
			log.fine(m_plv.toString());
		return m_plv;
	}	//	getPriceListVersion

	/**
	 * 	Get Standard Currency Precision
	 *	@return precision
	 */
	public int getStandardPrecision()
	{
		if (m_precision == null)
		{
			MCurrency c = MCurrency.get(getCtx(), getC_Currency_ID());
			m_precision = new Integer (c.getStdPrecision());
		}
		return m_precision.intValue();
	}	//	getStandardPrecision
	
	
	/**
	 * 	Set Price Precision
	 *	@param PricePrecision precision
	 */
	public void setPricePrecision (int PricePrecision)
	{
		setPricePrecision (new BigDecimal(PricePrecision));
	}	//	setPricePrecision
	
	
	/**
	 * 	Get Price Precision as int
	 *	@return precision - -1 for none
	 */
	public int getPricePrecisionInt ()
	{
		BigDecimal bd = getPricePrecision ();
		if (bd == null)
			return -1;
		return bd.intValue();
	}	//	getPricePrecisionInt
	
}	//	MPriceList
