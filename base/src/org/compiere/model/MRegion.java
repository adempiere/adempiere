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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.Adempiere;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *	Localtion Region Model (Value Object)
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: MRegion.java,v 1.3 2006/07/30 00:58:36 jjanke Exp $
 */
public final class MRegion extends X_C_Region
	implements Comparator, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1124865777747582617L;


	/**
	 * 	Load Regions (cached)
	 *	@param ctx context
	 */
	private static void loadAllRegions (Properties ctx)
	{
		s_regions = new CCache<String,MRegion>("C_Region", 100);
		String sql = "SELECT * FROM C_Region WHERE IsActive='Y'";
		try
		{
			Statement stmt = DB.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				MRegion r = new MRegion (ctx, rs, null);
				s_regions.put(String.valueOf(r.getC_Region_ID()), r);
				if (r.isDefault())
					s_default = r;
			}
			rs.close();
			stmt.close();
		}
		catch (SQLException e)
		{
			s_log.log(Level.SEVERE, sql, e);
		}
		s_log.fine(s_regions.size() + " - default=" + s_default);
	}	//	loadAllRegions

	/**
	 * 	Get Country (cached)
	 * 	@param ctx context
	 *	@param C_Region_ID ID
	 *	@return Country
	 */
	public static MRegion get (Properties ctx, int C_Region_ID)
	{
		if (s_regions == null || s_regions.size() == 0)
			loadAllRegions(ctx);
		String key = String.valueOf(C_Region_ID);
		MRegion r = (MRegion)s_regions.get(key);
		if (r != null)
			return r;
		r = new MRegion (ctx, C_Region_ID, null);
		if (r.getC_Region_ID() == C_Region_ID)
		{
			s_regions.put(key, r);
			return r;
		}
		return null;
	}	//	get

	/**
	 * 	Get Default Region
	 * 	@param ctx context
	 *	@return Region or null
	 */
	public static MRegion getDefault (Properties ctx)
	{
		if (s_regions == null || s_regions.size() == 0)
			loadAllRegions(ctx);
		return s_default;
	}	//	get

	/**
	 *	Return Regions as Array
	 * 	@param ctx context
	 *  @return MCountry Array
	 */
	@SuppressWarnings("unchecked")
	public static MRegion[] getRegions(Properties ctx)
	{
		if (s_regions == null || s_regions.size() == 0)
			loadAllRegions(ctx);
		MRegion[] retValue = new MRegion[s_regions.size()];
		s_regions.values().toArray(retValue);
		Arrays.sort(retValue, new MRegion(ctx, 0, null));
		return retValue;
	}	//	getRegions

	/**
	 *	Return Array of Regions of Country
	 * 	@param ctx context
	 *  @param C_Country_ID country
	 *  @return MRegion Array
	 */
	@SuppressWarnings("unchecked")
	public static MRegion[] getRegions (Properties ctx, int C_Country_ID)
	{
		if (s_regions == null || s_regions.size() == 0)
			loadAllRegions(ctx);
		ArrayList<MRegion> list = new ArrayList<MRegion>();
		Iterator it = s_regions.values().iterator();
		while (it.hasNext())
		{
			MRegion r = (MRegion)it.next();
			if (r.getC_Country_ID() == C_Country_ID)
				list.add(r);
		}
		//  Sort it
		MRegion[] retValue = new MRegion[list.size()];
		list.toArray(retValue);
		Arrays.sort(retValue, new MRegion(ctx, 0, null));
		return retValue;
	}	//	getRegions

	/**	Region Cache				*/
	private static CCache<String,MRegion> s_regions = null;
	/** Default Region				*/
	private static MRegion		s_default = null;
	/**	Static Logger				*/
	private static CLogger		s_log = CLogger.getCLogger (MRegion.class);

	
	/**************************************************************************
	 *	Create empty Region
	 * 	@param ctx context
	 * 	@param C_Region_ID id
	 *	@param trxName transaction
	 */
	public MRegion (Properties ctx, int C_Region_ID, String trxName)
	{
		super (ctx, C_Region_ID, trxName);
		if (C_Region_ID == 0)
		{
		}
	}   //  MRegion

	/**
	 *	Create Region from current row in ResultSet
	 * 	@param ctx context
	 *  @param rs result set
	 *	@param trxName transaction
	 */
	public MRegion (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MRegion

	/**
	 * 	Parent Constructor
	 *	@param country country
	 *	@param regionName Region Name
	 */
	public MRegion (MCountry country, String regionName)
	{
		super (country.getCtx(), 0, country.get_TrxName());
		setC_Country_ID(country.getC_Country_ID());
		setName(regionName);
	}   //  MRegion
	
	/**
	 *	Return Name
	 *  @return Name
	 */
	public String toString()
	{
		return getName();
	}   //  toString

	/**
	 *  Compare
	 *  @param o1 object 1
	 *  @param o2 object 2
	 *  @return -1,0, 1
	 */
	public int compare(Object o1, Object o2)
	{
		String s1 = o1.toString();
		if (s1 == null)
			s1 = "";
		String s2 = o2.toString();
		if (s2 == null)
			s2 = "";
		return s1.compareTo(s2);
	}	//	compare

	/**
	 * 	Test / Load
	 *	@param args
	 */
	public static void main (String[] args)
	{
		Adempiere.startup(true);
		/** To add your regions, complete the code below.
		 * 	Please make sure that the file is converted via the Java utility
		 * 	native2ascii - i.e. all seven bit code with /u0000 unicode stuff 
		 */
		int C_Country_ID = 216;		//	Japan
		MCountry country = new MCountry(Env.getCtx(), C_Country_ID, null); 
		// Hokkaido
		MRegion temp = new MRegion (country, "\u5317\u6d77\u9053");
		temp.setDescription( "\u5317\u6d77\u9053(Hokkaido)" );
		temp.save();
		// Aomori
		temp = new MRegion (country, "\u9752\u68ee\u770c");
		temp.setDescription( "\u9752\u68ee\u770c(Aomori)" );
		temp.save();
		// Iwate
		temp = new MRegion (country, "\u5ca9\u624b\u770c");
		temp.setDescription( "\u5ca9\u624b\u770c(Iwate)" );
		temp.save();
		// Miyagi
		temp = new MRegion (country, "\u5bae\u57ce\u770c");
		temp.setDescription( "\u5bae\u57ce\u770c(Miyagi)" );
		temp.save();
		// Akita
		temp = new MRegion (country, "\u79cb\u7530\u770c");
		temp.setDescription( "\u79cb\u7530\u770c(Akita)" );
		temp.save();
		// Yamagata
		temp = new MRegion (country, "\u5c71\u5f62\u770c");
		temp.setDescription( "\u5c71\u5f62\u770c(Yamagata)" );
		temp.save();
		// Fukushima
		temp = new MRegion (country, "\u798f\u5cf6\u770c");
		temp.setDescription( "\u798f\u5cf6\u770c(Fukushima)" );
		temp.save();
		// Ibaraki
		temp = new MRegion (country, "\u8328\u57ce\u770c");
		temp.setDescription( "\u8328\u57ce\u770c(Ibaraki)" );
		temp.save();
		// Gunma
		temp = new MRegion (country, "\u7fa4\u99ac\u770c");
		temp.setDescription( "\u7fa4\u99ac\u770c(Gunma)" );
		temp.save();
		// Saitama
		temp = new MRegion (country, "\u57fc\u7389\u770c");
		temp.setDescription( "\u57fc\u7389\u770c(Saitama)" );
		temp.save();
		// Chiba
		temp = new MRegion (country, "\u5343\u8449\u770c");
		temp.setDescription( "\u5343\u8449\u770c(Chiba)" );
		temp.save();
		// Tokyo
		temp = new MRegion (country, "\u6771\u4eac\u90fd");
		temp.setDescription( "\u6771\u4eac\u90fd(Tokyo)" );
		temp.save();
		// Kanagawa
		temp = new MRegion (country, "\u795e\u5948\u5ddd\u770c");
		temp.setDescription( "\u795e\u5948\u5ddd\u770c(Kanagawa)" );
		temp.save();
		// Niigata
		temp = new MRegion (country, "\u65b0\u6f5f\u770c");
		temp.setDescription( "\u65b0\u6f5f\u770c(Niigata)" );
		temp.save();
		// Toyama
		temp = new MRegion (country, "\u5bcc\u5c71\u770c");
		temp.setDescription( "\u5bcc\u5c71\u770c(Toyama)" );
		temp.save();
		// Ishikawa
		temp = new MRegion (country, "\u77f3\u5ddd\u770c");
		temp.setDescription( "\u77f3\u5ddd\u770c(Ishikawa)" );
		temp.save();
		// Fukui
		temp = new MRegion (country, "\u798f\u4e95\u770c");
		temp.setDescription( "\u798f\u4e95\u770c(Fukui)" );
		temp.save();
		// Yamanashi
		temp = new MRegion (country, "\u5c71\u68a8\u770c");
		temp.setDescription( "\u5c71\u68a8\u770c(Yamanashi)" );
		temp.save();
		// Gifu
		temp = new MRegion (country, "\u5c90\u961c\u770c");
		temp.setDescription( "\u5c90\u961c\u770c(Gifu)" );
		temp.save();
		// Shizuoka
		temp = new MRegion (country, "\u9759\u5ca1\u770c");
		temp.setDescription( "\u9759\u5ca1\u770c(Shizuoka)" );
		temp.save();
		// Aichi
		temp = new MRegion (country, "\u611b\u77e5\u770c");
		temp.setDescription( "\u611b\u77e5\u770c(Aichi)" );
		temp.save();
		// Mie
		temp = new MRegion (country, "\u4e09\u91cd\u770c");
		temp.setDescription( "\u4e09\u91cd\u770c(Mie)" );
		temp.save();
		// Siga
		temp = new MRegion (country, "\u6ecb\u8cc0\u770c");
		temp.setDescription( "\u6ecb\u8cc0\u770c(Siga)" );
		temp.save();
		// Kyoto
		temp = new MRegion (country, "\u4eac\u90fd\u5e9c");
		temp.setDescription( "\u4eac\u90fd\u5e9c(Kyoto)" );
		temp.save();
		// Osaka
		temp = new MRegion (country, "\u5927\u962a\u5e9c");
		temp.setDescription( "\u5927\u962a\u5e9c(Osaka)" );
		temp.save();
		// Hyogo
		temp = new MRegion (country, "\u5175\u5eab\u770c");
		temp.setDescription( "\u5175\u5eab\u770c(Hyogo)" );
		temp.save();
		// Nara
		temp = new MRegion (country, "\u5948\u826f\u770c");
		temp.setDescription( "\u5948\u826f\u770c(Nara)" );
		temp.save();
		// Wakayama
		temp = new MRegion (country, "\u548c\u6b4c\u5c71\u770c");
		temp.setDescription( "\u548c\u6b4c\u5c71\u770c(Wakayama)" );
		temp.save();
		// Tottori
		temp = new MRegion (country, "\u9ce5\u53d6\u770c");
		temp.setDescription( "\u9ce5\u53d6\u770c(Tottori)" );
		temp.save();
		// Shimane
		temp = new MRegion (country, "\u5cf6\u6839\u770c");
		temp.setDescription( "\u5cf6\u6839\u770c(Shimane)" );
		temp.save();
		// Okayama
		temp = new MRegion (country, "\u5ca1\u5c71\u770c");
		temp.setDescription( "\u5ca1\u5c71\u770c(Okayama)" );
		temp.save();
		// Hiroshima
		temp = new MRegion (country, "\u5e83\u5cf6\u770c");
		temp.setDescription( "\u5e83\u5cf6\u770c(Hiroshima)" );
		temp.save();
		// Yamaguchi
		temp = new MRegion (country, "\u5c71\u53e3\u770c");
		temp.setDescription( "\u5c71\u53e3\u770c(Yamaguchi)" );
		temp.save();
		// Tokushima
		temp = new MRegion (country, "\u5fb3\u5cf6\u770c");
		temp.setDescription( "\u5fb3\u5cf6\u770c(Tokushima)" );
		temp.save();
		// Kagawa
		temp = new MRegion (country, "\u9999\u5ddd\u770c");
		temp.setDescription( "\u9999\u5ddd\u770c(Kagawa)" );
		temp.save();
		// Ehime
		temp = new MRegion (country, "\u611b\u5a9b\u770c");
		temp.setDescription( "\u611b\u5a9b\u770c(Ehime)" );
		temp.save();
		// Kouchi
		temp = new MRegion (country, "\u9ad8\u77e5\u770c");
		temp.setDescription( "\u9ad8\u77e5\u770c(Kouchi)" );
		temp.save();
		// Fukuoka
		temp = new MRegion (country, "\u798f\u5ca1\u770c");
		temp.setDescription( "\u798f\u5ca1\u770c(Fukuoka)" );
		temp.save();
		// Saga
		temp = new MRegion (country, "\u4f50\u8cc0\u770c");
		temp.setDescription( "\u4f50\u8cc0\u770c(Saga)" );
		temp.save();
		// Nagasaki
		temp = new MRegion (country, "\u9577\u5d0e\u770c");
		temp.setDescription( "\u9577\u5d0e\u770c(Nagasaki)" );
		temp.save();
		// Kumamoto
		temp = new MRegion (country, "\u718a\u672c\u770c");
		temp.setDescription( "\u718a\u672c\u770c(Kumamoto)" );
		temp.save();
		// Ohita
		temp = new MRegion (country, "\u5927\u5206\u770c");
		temp.setDescription( "\u5927\u5206\u770c(Ohita)" );
		temp.save();
		// Miyasaki
		temp = new MRegion (country, "\u5bae\u5d0e\u770c");
		temp.setDescription( "\u5bae\u5d0e\u770c(Miyasaki)" );
		temp.save();
		// Kagoshima
		temp = new MRegion (country, "\u9e7f\u5150\u5cf6\u770c");
		temp.setDescription( "\u9e7f\u5150\u5cf6\u770c(Kagoshima)" );
		temp.save();
		// Okinawa
		temp = new MRegion (country, "\u6c96\u7e04\u770c");
		temp.setDescription( "\u6c96\u7e04\u770c(Okinawa)" );
		temp.save();

	}	//	main
	
}	//	MRegion
