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
package org.compiere.wstore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MProductCategory;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 *  Price List.
 * 	ArrayList of PriceListProduct
 * 	Implementation assumes a relatively small price list (< 200)
 * 	This is the case, if the product is selected in the WebStore, 
 * 	but not if it is added via a direct link. In that case, it will
 * 	load all products(!) 
 *
 *  @author Jorg Janke
 *  @version $Id: PriceList.java,v 1.2 2006/07/30 00:53:21 jjanke Exp $
 */
public class PriceList
{
	/**
	 * 	Get Price List
	 *	@param AD_Client_ID client
	 *	@param M_PriceList_ID price list
	 *	@param searchString query search string
	 *	@param productCategory query product category string
	 *	@param allRecords all if no query
	 *	@return Price list
	 */
	public static PriceList get (Properties ctx, int AD_Client_ID, int M_PriceList_ID, 
		String searchString, String productCategory, boolean allRecords)
	{
		//	Search Parameter
		String search = searchString;
		if (search != null && (search.length() == 0 || search.equals("%")))
			search = null;
		if (search != null)
		{
			if (!search.endsWith("%"))
				search += "%";
			if (!search.startsWith("%"))
				search = "%" + search;
			search = search.toUpperCase();
		}
		int M_Product_Category_ID = 0;
		try
		{
			if (productCategory != null && productCategory.length() > 0)
				M_Product_Category_ID = Integer.parseInt(productCategory);
		}
		catch (Exception e)
		{}
		if (M_Product_Category_ID < 0)
			M_Product_Category_ID = 0;

		//	Search Price List Cache
		String key = String.valueOf(AD_Client_ID) + "_" + M_PriceList_ID;
		PriceList retValue = null;
		if (search == null && M_Product_Category_ID == 0 && allRecords)
			retValue = s_cache.get(key);

		//	create New
		if (retValue == null)
		{
			retValue = new PriceList (ctx, AD_Client_ID, M_PriceList_ID, 
				search, M_Product_Category_ID, allRecords);
			if (search == null && M_Product_Category_ID == 0 && allRecords)
				s_cache.put(key, retValue);
		}
		return retValue;
	}	//	get

	/**	Price List Cache				*/
	private static CCache<String,PriceList> s_cache
		= new CCache<String,PriceList>("PriceList", 5, 60);	// 1h Cache	
	/** Maximum Lines to be displayed	*/
	public static int				MAX_LINES = 50;

	
	/*************************************************************************
	 * 	PriceList constructor.
	 *	@param ctx context 
	 * 	@param AD_Client_ID client
	 * 	@param M_PriceList_ID optional price list
	 *	@param searchString query search string
	 *	@param M_Product_Category_ID query product category
	 *	@param allRecords all if no query
	 */
	private PriceList (Properties ctx, int AD_Client_ID, int M_PriceList_ID, 
		String searchString, int M_Product_Category_ID, boolean allRecords)
	{
		log.finer("AD_Client_ID=" + AD_Client_ID + ", M_PriceList_ID=" + M_PriceList_ID
				+ ", Search=" + searchString + ",M_Product_Category_ID=" + M_Product_Category_ID
				+ ", All=" + allRecords);
		m_ctx = ctx;
		
		//	Get Price List
		if (getM_PriceList_ID (AD_Client_ID, M_PriceList_ID) == 0)
			if (getM_PriceList_ID(AD_Client_ID, 0) == 0)
				 return;

		//	Get Price List Version
		getM_PriceList_Version_ID(m_PriceList_ID, new Timestamp(System.currentTimeMillis()));
		loadProducts (searchString, M_Product_Category_ID, allRecords);
	}	//	PriceList

	/**	Attribute Name - also in JSPs		*/
	public static final String		NAME = "priceList";
	/**	Logging						*/
	private CLogger					log = CLogger.getCLogger(getClass());

	private String 			m_name = "Not found";
	private String 			m_description;
	private String 			m_currency;
	private String			m_curSymbol;
	private String			m_AD_Language;
	private boolean 		m_taxIncluded;
	private int 			m_PriceList_ID = 0;
	private int 			m_PriceList_Version_ID = 0;
	private String			m_searchInfo = "";
	private boolean			m_notAllPrices = false;
	
	/**	Price Lines				*/
	private ArrayList<PriceListProduct>	m_prices = new ArrayList<PriceListProduct>();
	/** Context					*/
	private Properties					m_ctx;

	/**
	 * 	Find Price List
	 * 	@param AD_Client_ID client
	 * 	@param M_PriceList_ID optional price list
	 * 	@return M_PriceList_ID
	 */
	private int getM_PriceList_ID (int AD_Client_ID, int M_PriceList_ID)
	{
		String sql = "SELECT M_PriceList_ID, pl.Name, pl.Description, pl.IsTaxIncluded,"	//	1..4
			+ " c.ISO_Code, c.CurSymbol, cc.AD_Language  "									//	5..7
			+ "FROM M_PriceList pl"
			+ " INNER JOIN C_Currency c ON (pl.C_Currency_ID=c.C_Currency_ID)"
			   // begin globalqss 29/09/2005 -- problem with postgres port
			   // + " LEFT OUTER JOIN C_Country cc ON (c.C_Currency_ID=cc.C_Currency_ID AND ROWNUM=1) "
			   + " LEFT OUTER JOIN C_Country cc ON (c.C_Currency_ID=cc.C_Currency_ID) "
			   // end globalqss 29/09/2005
			+ "WHERE pl.IsActive='Y'"
			+ " AND pl.AD_Client_ID=?";				//	#1
		if (M_PriceList_ID != 0)
			sql += " AND pl.M_PriceList_ID=?";		//	#2
		else
			sql += " ORDER BY pl.IsDefault DESC";
		m_PriceList_ID = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_Client_ID);
			if (M_PriceList_ID != 0)
				pstmt.setInt(2, M_PriceList_ID);
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				m_PriceList_ID = rs.getInt(1);
				m_name = rs.getString(2);
				m_description = rs.getString(3);
				m_taxIncluded = "Y".equals(rs.getString(4));
				m_currency = rs.getString(5);
				m_curSymbol = rs.getString(6);
				m_AD_Language = rs.getString(7);
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "getM_PriceList_ID", e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return m_PriceList_ID;
	}	//	getM_PriceList_ID

	/**
	 * 	Get PL Version
	 * 	@param M_PriceList_ID price list
	 * 	@param day valid day
	 * 	@return M_PriceList_Version_ID
	 */
	private int getM_PriceList_Version_ID (int M_PriceList_ID, Timestamp day)
	{
		String sql = "SELECT plv.M_PriceList_Version_ID, plv.Name, plv.Description, plv.ValidFrom " 	//	1..4
			+ "FROM M_PriceList_Version plv "
			+ "WHERE plv.M_PriceList_ID=?"		//	#1
			+ " AND plv.ValidFrom <=? "			//	#2
			+ "ORDER BY plv.ValidFrom DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		m_PriceList_Version_ID = 0;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, M_PriceList_ID);
			pstmt.setTimestamp(2, day);
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				m_PriceList_Version_ID = rs.getInt(1);
				m_name = rs.getString(2);
				m_description = rs.getString(3);
			//  m_validFrom = rs.getTimestamp(4);
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "getM_PriceList_Version_ID", e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}

		return m_PriceList_Version_ID;
	}	//	getM_PriceList_Version_ID

	/**
	 * 	Load From Product Price
	 *	@param searchString query search string
	 *	@param M_Product_Category_ID query product category
	 *	@param allRecords all only true if called from BasketServlet
	 */
	private void loadProducts (String searchString, int M_Product_Category_ID, boolean allRecords)
	{
		//	Set Search String
		log.finer("loadProducts - M_PriceList_Version_ID=" + m_PriceList_Version_ID
				+ ", Search=" + searchString + ", M_Product_Category_ID=" + M_Product_Category_ID);
		m_searchInfo = "";
		if (searchString != null)
			m_searchInfo = searchString;
		if (M_Product_Category_ID != 0)
		{
			if (m_searchInfo.length() != 0)
				m_searchInfo += " - ";
			m_searchInfo += MProductCategory.get(m_ctx, M_Product_Category_ID).getName();
		}
		
		m_prices.clear();
		m_notAllPrices = false;
		//
		String sql = "SELECT p.M_Product_ID, p.Value, p.Name, p.Description, "	//	1..4
			+ "p.Help, p.DocumentNote, p.ImageURL, p.DescriptionURL, "			//	5..8
			+ "pp.PriceStd, uom.Name, uom.UOMSymbol " 							//	9..11
			+ "FROM M_ProductPrice pp "
			+ " INNER JOIN M_Product p ON (pp.M_Product_ID=p.M_Product_ID AND p.IsActive='Y' AND p.IsSold='Y')"
			+ " INNER JOIN C_UOM uom ON (p.C_UOM_ID=uom.C_UOM_ID) "
			+ "WHERE pp.M_PriceList_Version_ID=?"					//	#1
			+ " AND pp.PriceStd > 0 "
			+ " AND p.IsSelfService='Y'";
		if (searchString != null)
			sql += " AND UPPER(p.Value||p.Name||p.Description) LIKE ? ";	//	#2
		if (M_Product_Category_ID != 0)
			sql += " AND p.M_Product_Category_ID=? ";				//	#3
		if (!allRecords && searchString == null && M_Product_Category_ID == 0)
		{
			sql += " AND p.IsWebStoreFeatured='Y' ";
			m_notAllPrices = true;
		}
		sql += "ORDER BY p.M_Product_Category_ID, p.Value";
	//	log.fine("loadProducts - " + sql);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			int index = 1;
			pstmt.setInt(index++, m_PriceList_Version_ID);
			if (searchString != null)
				pstmt.setString(index++, searchString);
			if (M_Product_Category_ID != 0)
				pstmt.setInt(index++, M_Product_Category_ID);
			rs = pstmt.executeQuery();
			int no = 0;
			while (rs.next())
			{
				m_prices.add (new PriceListProduct(
						rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
						rs.getBigDecimal(9), rs.getString(10), rs.getString(11) ));
				//	if not all records limit list
				if (!allRecords && ++no > MAX_LINES)
				{
					m_notAllPrices = true;
					break;
				}
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "load", e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		log.fine("load #" + m_prices.size() + ", Search=" + m_searchInfo);
	}	//	load


	/**************************************************************************
	 * 	String Representation
	 * 	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("PriceList[");
		sb.append(m_prices.size())
			.append("]");
		return sb.toString();
	}	//	toString

	/**
	 * 	Get Count
	 * 	@return size
	 */
	public int getPriceCount()
	{
		return m_prices.size();
	}	//	getPriceCount

	/**
	 * 	No Prices
	 * 	@return true if no prices
	 */
	public boolean isNoLines()
	{
		return getPriceCount() == 0;
	}	//	getPriceCount

	/**
	 * 	Not all Prices displayed
	 * 	@return true if no prices
	 */
	public boolean isNotAllPrices()
	{
		return m_notAllPrices;
	}	//	isNotAllPrices


	/**
	 * 	Get Prices
	 * 	@return Price Array List
	 */
	public ArrayList<PriceListProduct> getPrices()
	{
		return m_prices;
	}	//	getPrices

	/**
	 * 	Get Price List for Product.
	 * 	Implementation assumes a relatively small price list (< 200)
	 *	@param M_Product_ID product
	 *	@return price list info or null
	 */
	public PriceListProduct getPriceListProduct (int M_Product_ID)
	{
		for (int i = 0; i < m_prices.size (); i++)
		{
			PriceListProduct plp = m_prices.get (i);
			if (plp.getId () == M_Product_ID)
				return plp;
		}
		return null;
	}	//	getPriceListProduct

	/**
	 * 	Get Search Info
	 *	@return search info
	 */
	public String getSearchInfo()
	{
		return m_searchInfo;
	}	//	getSearchInfo

	/*************************************************************************/

	/**
	 * 	Get Name
	 * 	@return Price List Name
	 */
	public String getName()
	{
		return m_name;
	}
	public String getDescription()
	{
		return m_description;
	}
	public String getCurrency()
	{
		return m_currency;
	}
	public String getCurSymbol()
	{
		return m_curSymbol;
	}
	public String getAD_Language()
	{
		return m_AD_Language;
	}
	public boolean isTaxIncluded()
	{
		return m_taxIncluded;
	}
	public int getPriceList_ID()
	{
		return m_PriceList_ID;
	}
	public int getPriceList_Version_ID()
	{
		return m_PriceList_Version_ID;
	}

}	//	PriceList
