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

import java.awt.Point;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.DBException;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Msg;

/**
 *	Unit of Measure Conversion Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MUOMConversion.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 */
public class MUOMConversion extends X_C_UOM_Conversion
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8449239579085422641L;


	/**
	 *	Convert qty to target UOM and round.
	 *  @param ctx context
	 *  @param C_UOM_ID from UOM
	 *  @param C_UOM_To_ID to UOM
	 *  @param qty qty
	 *  @return converted qty (std precision)
	 */
	static public BigDecimal convert (Properties ctx,
		int C_UOM_ID, int C_UOM_To_ID, BigDecimal qty)
	{
		if (qty == null || qty.compareTo(Env.ZERO)==0 || C_UOM_ID == C_UOM_To_ID)
			return qty;
		BigDecimal retValue = getRate (ctx, C_UOM_ID, C_UOM_To_ID);
		if (retValue != null)
		{
			MUOM uom = MUOM.get (ctx, C_UOM_To_ID);
			if (uom != null)
				return uom.round(retValue.multiply(qty), true);
			return retValue.multiply(qty);
		}
		return null;
	}	//	convert

	/**
	 *	Get Multiplier Rate to target UOM
	 *  @param ctx context
	 *  @param C_UOM_ID from UOM
	 *  @param C_UOM_To_ID to UOM
	 *  @return multiplier
	 */
	static public BigDecimal getRate (Properties ctx,
		int C_UOM_ID, int C_UOM_To_ID)
	{
		//	nothing to do
		if (C_UOM_ID == C_UOM_To_ID)
			return Env.ONE;
		//
		Point p = new Point(C_UOM_ID, C_UOM_To_ID);
		//	get conversion
		BigDecimal retValue = getRate (ctx, p);
		return retValue;
	}	//	convert

	
	/**
	 *	Convert qty to target UOM and round.
	 *  @param ctx context
	 *  @param C_UOM_ID from UOM
	 *  @param qty qty
	 *  @return minutes - 0 if not found
	 */
	static public int convertToMinutes (Properties ctx,
		int C_UOM_ID, BigDecimal qty)
	{
		if (qty == null)
			return 0;
		int C_UOM_To_ID = MUOM.getMinute_UOM_ID(ctx);
		if (C_UOM_ID == C_UOM_To_ID)
			return qty.intValue();
		//
		BigDecimal result = convert (ctx, C_UOM_ID, C_UOM_To_ID, qty);
		if (result == null)
			return 0;
		return result.intValue();
	}	//	convert

	/**
	 * 	Calculate End Date based on start date and qty
	 *  @param ctx context
	 * 	@param startDate date
	 *  @param C_UOM_ID UOM
	 * 	@param qty qty
	 * 	@return end date
	 */
	static public Timestamp getEndDate (Properties ctx, Timestamp startDate, int C_UOM_ID, BigDecimal qty)
	{
		GregorianCalendar endDate = new GregorianCalendar();
		endDate.setTime(startDate);
		//
		int minutes = MUOMConversion.convertToMinutes (ctx, C_UOM_ID, qty);
		endDate.add(Calendar.MINUTE, minutes);
		//
		Timestamp retValue = new Timestamp(endDate.getTimeInMillis());
	//	log.config( "TimeUtil.getEndDate", "Start=" + startDate
	//		+ ", Qty=" + qty + ", End=" + retValue);
		return retValue;
	}	//	startDate
	
	/**************************************************************************
	 * 	Get Conversion Multiplier Rate, try to derive it if not found directly
	 * 	@param ctx context
	 * 	@param p Point with from(x) - to(y) C_UOM_ID
	 * 	@return conversion multiplier or null
	 */
	static private BigDecimal getRate (Properties ctx, Point p)
	{
		BigDecimal retValue = null;
		if (Ini.isClient())
		{
			if (s_conversions == null)
				createRates(ctx);
			retValue = (BigDecimal)s_conversions.get(p);
		}
		else
			retValue = getRate (p.x, p.y);
		if (retValue != null)
			return retValue;
		//	try to derive
		return deriveRate (ctx, p.x, p.y);
	}	//	getConversion

	/**
	 * 	Create Conversion Matrix (Client)
	 * 	@param ctx context
	 */
	private static void createRates (Properties ctx)
	{
		s_conversions = new CCache<Point,BigDecimal>("C_UOMConversion", 20);
		//
		String sql = MRole.getDefault(ctx, false).addAccessSQL (
			"SELECT C_UOM_ID, C_UOM_To_ID, MultiplyRate, DivideRate "
			+ "FROM C_UOM_Conversion "
			+ "WHERE IsActive='Y' AND M_Product_ID IS NULL",
			"C_UOM_Conversion", MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				Point p = new Point (rs.getInt(1), rs.getInt(2));
				BigDecimal mr = rs.getBigDecimal(3);
				BigDecimal dr = rs.getBigDecimal(4);
				if (mr != null)
					s_conversions.put(p, mr);
				//	reverse
				if (dr == null && mr != null)
					dr = Env.ONE.divide(mr, BigDecimal.ROUND_HALF_UP);
				if (dr != null)
					s_conversions.put(new Point(p.y,p.x), dr);
			}
		}
		catch (SQLException e)
		{
			s_log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
	}	//	createRatess

	/**
	 * 	Derive Standard Conversions
	 * 	@param ctx context
	 *  @param C_UOM_ID from UOM
	 *  @param C_UOM_To_ID to UOM
	 * 	@return Conversion or null
	 */
	public static BigDecimal deriveRate (Properties ctx,
		int C_UOM_ID, int C_UOM_To_ID)
	{
		if (C_UOM_ID == C_UOM_To_ID)
			return Env.ONE;
		//	get Info
		MUOM from = MUOM.get (ctx, C_UOM_ID);
		MUOM to = MUOM.get (ctx, C_UOM_To_ID);
		if (from == null || to == null)
			return null;

		//	Time - Minute
		if (from.isMinute())
		{
			if (to.isHour())
				return new BigDecimal(1.0/60.0);
			if (to.isDay())
				return new BigDecimal(1.0/1440.0);		//	24 * 60
			if (to.isWorkDay())
				return new BigDecimal(1.0/480.0);		//	8 * 60
			if (to.isWeek())
				return new BigDecimal(1.0/10080.0);		//	7 * 24 * 60
			if (to.isMonth())
				return new BigDecimal(1.0/43200.0);		//	30 * 24 * 60
			if (to.isWorkMonth())
				return new BigDecimal(1.0/9600.0);		//	4 * 5 * 8 * 60
			if (to.isYear())
				return new BigDecimal(1.0/525600.0);	//	365 * 24 * 60
		}
		//	Time - Hour
		if (from.isHour())
		{
			if (to.isMinute())
				return new BigDecimal(60.0);
			if (to.isDay())
				return new BigDecimal(1.0/24.0);
			if (to.isWorkDay())
				return new BigDecimal(1.0/8.0);
			if (to.isWeek())
				return new BigDecimal(1.0/168.0);		//	7 * 24
			if (to.isMonth())
				return new BigDecimal(1.0/720.0);		//	30 * 24
			if (to.isWorkMonth())
				return new BigDecimal(1.0/160.0);		//	4 * 5 * 8
			if (to.isYear())
				return new BigDecimal(1.0/8760.0);		//	365 * 24
		}
		//	Time - Day
		if (from.isDay())
		{
			if (to.isMinute())
				return new BigDecimal(1440.0);			//	24 * 60
			if (to.isHour())
				return new BigDecimal(24.0);
			if (to.isWorkDay())
				return new BigDecimal(3.0);				//	24 / 8
			if (to.isWeek())
				return new BigDecimal(1.0/7.0);			//	7
			if (to.isMonth())
				return new BigDecimal(1.0/30.0);		//	30
			if (to.isWorkMonth())
				return new BigDecimal(1.0/20.0);		//	4 * 5
			if (to.isYear())
				return new BigDecimal(1.0/365.0);		//	365
		}
		//	Time - WorkDay
		if (from.isWorkDay())
		{
			if (to.isMinute())
				return new BigDecimal(480.0);			//	8 * 60
			if (to.isHour())
				return new BigDecimal(8.0);				//	8
			if (to.isDay())
				return new BigDecimal(1.0/3.0);			//	24 / 8
			if (to.isWeek())
				return new BigDecimal(1.0/5);			//	5
			if (to.isMonth())
				return new BigDecimal(1.0/20.0);		//	4 * 5
			if (to.isWorkMonth())
				return new BigDecimal(1.0/20.0);		//	4 * 5
			if (to.isYear())
				return new BigDecimal(1.0/240.0);		//	4 * 5 * 12
		}
		//	Time - Week
		if (from.isWeek())
		{
			if (to.isMinute())
				return new BigDecimal(10080.0);			//	7 * 24 * 60
			if (to.isHour())
				return new BigDecimal(168.0);			//	7 * 24
			if (to.isDay())
				return new BigDecimal(7.0);
			if (to.isWorkDay())
				return new BigDecimal(5.0);
			if (to.isMonth())
				return new BigDecimal(1.0/4.0);			//	4
			if (to.isWorkMonth())
				return new BigDecimal(1.0/4.0);			//	4
			if (to.isYear())
				return new BigDecimal(1.0/50.0);		//	50
		}
		//	Time - Month
		if (from.isMonth())
		{
			if (to.isMinute())
				return new BigDecimal(43200.0);			//	30 * 24 * 60
			if (to.isHour())
				return new BigDecimal(720.0);			//	30 * 24
			if (to.isDay())
				return new BigDecimal(30.0);			//	30
			if (to.isWorkDay())
				return new BigDecimal(20.0);			//	4 * 5
			if (to.isWeek())
				return new BigDecimal(4.0);				//	4
			if (to.isWorkMonth())
				return new BigDecimal(1.5);				//	30 / 20
			if (to.isYear())
				return new BigDecimal(1.0/12.0);		//	12
		}
		//	Time - WorkMonth
		if (from.isWorkMonth())
		{
			if (to.isMinute())
				return new BigDecimal(9600.0);			//	4 * 5 * 8 * 60
			if (to.isHour())
				return new BigDecimal(160.0);			//	4 * 5 * 8
			if (to.isDay())
				return new BigDecimal(20.0);			//	4 * 5
			if (to.isWorkDay())
				return new BigDecimal(20.0);			//	4 * 5
			if (to.isWeek())
				return new BigDecimal(4.0);				//	4
			if (to.isMonth())
				return new BigDecimal(20.0/30.0);		//	20 / 30
			if (to.isYear())
				return new BigDecimal(1.0/12.0);		//	12
		}
		//	Time - Year
		if (from.isYear())
		{
			if (to.isMinute())
				return new BigDecimal(518400.0);		//	12 * 30 * 24 * 60
			if (to.isHour())
				return new BigDecimal(8640.0);			//	12 * 30 * 24
			if (to.isDay())
				return new BigDecimal(365.0);			//	365
			if (to.isWorkDay())
				return new BigDecimal(240.0);			//	12 * 4 * 5
			if (to.isWeek())
				return new BigDecimal(50.0);			//	52
			if (to.isMonth())
				return new BigDecimal(12.0);			//	12
			if (to.isWorkMonth())
				return new BigDecimal(12.0);			//	12
		}
		//
		return null;
	}	//	deriveRate

	/**************************************************************************
	 * 	Get Conversion Multiplier Rate from Server
	 *  @param C_UOM_ID from UOM
	 *  @param C_UOM_To_ID to UOM
	 * 	@return conversion multiplier or null
	 */
	public static BigDecimal getRate (int C_UOM_ID, int C_UOM_To_ID)
	{
		return convert (C_UOM_ID, C_UOM_To_ID, GETRATE, false);
	}	//	getConversion

	/**
	 *  Get Converted Qty from Server (no cache)
	 *  @param  qty             The quantity to be converted
	 *  @param  C_UOM_From_ID   The C_UOM_ID of the qty
	 *  @param  C_UOM_To_ID     The targeted UOM
	 *  @param  StdPrecision    if true, standard precision, if false costing precision
	 *  @return amount
	 *  @depreciated should not be used
	 */
	public static BigDecimal convert (int C_UOM_From_ID, int C_UOM_To_ID, 
		BigDecimal qty, boolean StdPrecision)
	{
		//  Nothing to do
		if (qty == null || qty.compareTo(Env.ZERO)==0
				|| C_UOM_From_ID == C_UOM_To_ID)
			return qty;
		//
		BigDecimal retValue = null;
		int precision = 2;
		String sql = "SELECT c.MultiplyRate, uomTo.StdPrecision, uomTo.CostingPrecision "
			+ "FROM	C_UOM_Conversion c"
			+ " INNER JOIN C_UOM uomTo ON (c.C_UOM_TO_ID=uomTo.C_UOM_ID) "
			+ "WHERE c.IsActive='Y' AND c.C_UOM_ID=? AND c.C_UOM_TO_ID=? "		//	#1/2
			+ " AND c.M_Product_ID IS NULL"
			+ " ORDER BY c.AD_Client_ID DESC, c.AD_Org_ID DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, C_UOM_From_ID);
			pstmt.setInt(2, C_UOM_To_ID);
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				retValue = rs.getBigDecimal(1);
				precision = rs.getInt(StdPrecision ? 2 : 3);
			}
		}
		catch (SQLException e)
		{
			throw new DBException(e, sql);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		if (retValue == null)
		{
			s_log.info ("NOT found - FromUOM=" + C_UOM_From_ID + ", ToUOM=" + C_UOM_To_ID);
			return null;
		}
			
		//	Just get Rate
		if (GETRATE.equals(qty))
			return retValue;
		
		//	Calculate & Scale
		retValue = retValue.multiply(qty);
		if (retValue.scale() > precision)
			retValue = retValue.setScale(precision, BigDecimal.ROUND_HALF_UP);
		return retValue;
	}   //  convert

	
	/**************************************************************************
	 *	Convert PRICE expressed in entered UoM to equivalent price in product UoM and round. <br/>
	 *  OR Convert QTY in product UOM to qty in entered UoM and round. <br/>
	 *  
	 *   eg: $6/6pk => $1/ea <br/>
	 *   OR 6 X ea => 1 X 6pk
	 *   
	 *  @param ctx context
	 *  @param M_Product_ID product
	 *  @param C_UOM_To_ID entered UOM
	 *  @param qtyPrice quantity or price
	 *  @return Product: Qty/Price (precision rounded)
	 */
	static public BigDecimal convertProductTo (Properties ctx,
		int M_Product_ID, int C_UOM_To_ID, BigDecimal qtyPrice)
	{
		if (qtyPrice == null || qtyPrice.signum() == 0 
			|| M_Product_ID == 0 || C_UOM_To_ID == 0)
			return qtyPrice;
		
		BigDecimal retValue = getProductRateTo (ctx, M_Product_ID, C_UOM_To_ID);
		if (retValue != null)
		{
			if (Env.ONE.compareTo(retValue) == 0)
				return qtyPrice;
			MUOM uom = MUOM.get (ctx, C_UOM_To_ID);
			if (uom != null)
				return uom.round(retValue.multiply(qtyPrice), true);
			return retValue.multiply(qtyPrice);
		}
		return null;
	}	//	convertProductTo

	/**
	 *	Get multiply rate to convert PRICE from price in entered UOM to price in product UOM <br/>
	 *  OR multiply rate to convert QTY from product UOM to entered UOM
	 *  @param ctx context
	 *  @param M_Product_ID product
	 *  @param C_UOM_To_ID entered UOM
	 *  @return multiplier or null
	 */
	static public BigDecimal getProductRateTo (Properties ctx,
		int M_Product_ID, int C_UOM_To_ID)
	{
		if (M_Product_ID == 0)
			return null;
		MUOMConversion[] rates = getProductConversions(ctx, M_Product_ID);
		if (rates.length == 0)
		{
			s_log.fine("None found");
			return null;
		}
		
		for (int i = 0; i < rates.length; i++)
		{
			MUOMConversion rate = rates[i];
			if (rate.getC_UOM_To_ID() == C_UOM_To_ID)
				return rate.getMultiplyRate();
		}
		s_log.fine("None applied");
		return null;
	}	//	getProductRateTo

	/**************************************************************************
	 *	Convert PRICE expressed in product UoM to equivalent price in entered UoM and round. <br/>
	 *  OR Convert QTY in entered UOM to qty in product UoM and round.  <br/>
	 *  
	 *   eg: $1/ea => $6/6pk <br/>
	 *   OR 1 X 6pk => 6 X ea
	 *   
	 *  @param ctx context
	 *  @param M_Product_ID product
	 *  @param C_UOM_To_ID entered UOM
	 *  @param qtyPrice quantity or price
	 *  @return Product: Qty/Price (precision rounded)
	 */
	static public BigDecimal convertProductFrom (Properties ctx,
		int M_Product_ID, int C_UOM_To_ID, BigDecimal qtyPrice)
	{
		//	No conversion
		if (qtyPrice == null || qtyPrice.compareTo(Env.ZERO)==0 
			|| C_UOM_To_ID == 0|| M_Product_ID == 0)
		{
			s_log.fine("No Conversion - QtyPrice=" + qtyPrice);
			return qtyPrice;
		}
		
		BigDecimal retValue = getProductRateFrom (ctx, M_Product_ID, C_UOM_To_ID);
		if (retValue != null)
		{
			if (Env.ONE.compareTo(retValue) == 0)
				return qtyPrice;
			MUOM uom = MUOM.get (ctx, C_UOM_To_ID);
			if (uom != null)
				return uom.round(retValue.multiply(qtyPrice), true);
			return retValue.multiply(qtyPrice);
		}
		s_log.fine("No Rate M_Product_ID=" + M_Product_ID);
		return null;
	}	//	convertProductFrom

	/**
	 *	Get multiply rate to convert PRICE from price in entered UOM to price in product UOM <br/>
	 *  OR multiply rate to convert QTY from product UOM to entered UOM
	 *  @param ctx context
	 *  @param M_Product_ID product
	 *  @param C_UOM_To_ID entered UOM
	 *  @return multiplier or null
	 */
	static public BigDecimal getProductRateFrom (Properties ctx,
		int M_Product_ID, int C_UOM_To_ID)
	{
		MUOMConversion[] rates = getProductConversions(ctx, M_Product_ID);
		if (rates.length == 0)
		{
			s_log.fine("getProductRateFrom - none found");
			return null;
		}
		
		for (int i = 0; i < rates.length; i++)
		{
			MUOMConversion rate = rates[i];
			if (rate.getC_UOM_To_ID() == C_UOM_To_ID)
				return rate.getDivideRate();
		}
		s_log.fine("None applied");
		return null;
	}	//	getProductRateFrom


	/**
	 * 	Get Product Conversions (cached)
	 *	@param ctx context
	 *	@param M_Product_ID product
	 *	@return array of conversions
	 */
	static public MUOMConversion[] getProductConversions (Properties ctx, int M_Product_ID)
	{
		if (M_Product_ID == 0)
			return new MUOMConversion[0];
		Integer key = new Integer (M_Product_ID);
		MUOMConversion[] result = (MUOMConversion[])s_conversionProduct.get(key);
		if (result != null)
			return result;
		
		ArrayList<MUOMConversion> list = new ArrayList<MUOMConversion>();
		//	Add default conversion
		MUOMConversion defRate = new MUOMConversion (MProduct.get(ctx, M_Product_ID));
		list.add(defRate);
		//
		final String whereClause = "M_Product_ID=?"
			+ " AND EXISTS (SELECT 1 FROM M_Product p "
				+ "WHERE C_UOM_Conversion.M_Product_ID=p.M_Product_ID AND C_UOM_Conversion.C_UOM_ID=p.C_UOM_ID)";
		List<MUOMConversion> conversions = new Query(ctx, Table_Name, whereClause, null)
		.setParameters(new Object[]{M_Product_ID})
		.setOnlyActiveRecords(true)
		.list();
		list.addAll(conversions);
		
		//	Convert & save
		result = new MUOMConversion[list.size ()];
		list.toArray (result);
		s_conversionProduct.put(key, result);
		s_log.fine("getProductConversions - M_Product_ID=" + M_Product_ID + " #" + result.length);
		return result;
	}	//	getProductConversions

	/** Static Logger					*/
	private static final CLogger s_log = CLogger.getCLogger(MUOMConversion.class);
	/**	Indicator for Rate					*/
	private static final BigDecimal GETRATE = new BigDecimal(123.456);
	/**	Conversion Map: Key=Point(from,to) Value=BigDecimal	*/
	private static CCache<Point,BigDecimal>	s_conversions = null;
	/** Product Conversion Map					*/
	private static final CCache<Integer,MUOMConversion[]>	s_conversionProduct 
		= new CCache<Integer,MUOMConversion[]>("C_UOMConversion", 20); 
	
	
	/**************************************************************************
	 * 	Default Constructor
	 *	@param ctx context
	 *	@param C_UOM_Conversion_ID id
	 *	@param trxName transaction
	 */
	public MUOMConversion (Properties ctx, int C_UOM_Conversion_ID, String trxName)
	{
		super(ctx, C_UOM_Conversion_ID, trxName);
	}	//	MUOMConversion

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MUOMConversion(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MUOMConversion

	/**
	 * 	Parent Constructor
	 *	@param parent uom parent
	 */
	public MUOMConversion (MUOM parent)
	{
		this(parent.getCtx(), 0, parent.get_TrxName());
		setClientOrg (parent);
		setC_UOM_ID (parent.getC_UOM_ID());
		setM_Product_ID(0);
		//
		setC_UOM_To_ID (parent.getC_UOM_ID());
		setMultiplyRate(Env.ONE);
		setDivideRate(Env.ONE);
	}	//	MUOMConversion

	/**
	 * 	Parent Constructor
	 *	@param parent product parent
	 */
	public MUOMConversion (MProduct parent)
	{
		this(parent.getCtx(), 0, parent.get_TrxName());
		setClientOrg (parent);
		setC_UOM_ID (parent.getC_UOM_ID());
		setM_Product_ID(parent.getM_Product_ID());
		//
		setC_UOM_To_ID (parent.getC_UOM_ID());
		setMultiplyRate(Env.ONE);
		setDivideRate(Env.ONE);
	}	//	MUOMConversion
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true if can be saved
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		//	From - To is the same
		if (getC_UOM_ID() == getC_UOM_To_ID())
		{
			log.saveError("Error", Msg.parseTranslation(getCtx(), "@C_UOM_ID@ = @C_UOM_To_ID@"));
			return false;
		}
		//	Nothing to convert
		if (getMultiplyRate().compareTo(Env.ZERO) <= 0)
		{
			log.saveError("Error", Msg.parseTranslation(getCtx(), "@MultiplyRate@ <= 0"));
			return false;
		}
		//	Enforce Product UOM
		if (MSysConfig.getBooleanValue("ProductUOMConversionUOMValidate", true))
		{
			if (getM_Product_ID() != 0 
				&& (newRecord || is_ValueChanged("M_Product_ID")))
			{
				MProduct product = MProduct.get(getCtx(), getM_Product_ID());
				if (product.getC_UOM_ID() != getC_UOM_ID())
				{
					MUOM uom = MUOM.get(getCtx(), product.getC_UOM_ID());
					log.saveError("ProductUOMConversionUOMError", uom.getName());
					return false;
				}
			}
		}

		//	The Product UoM needs to be the smallest UoM - Multiplier must be < 0; Divider must be > 0
		if (MSysConfig.getBooleanValue("ProductUOMConversionRateValidate", true))
		{
			if (getM_Product_ID() != 0 && getDivideRate().compareTo(Env.ONE) < 0)
			{
				log.saveError("ProductUOMConversionRateError", "");
				return false;
			}
		}
		
		return true;
	}	//	beforeSave
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MUOMConversion[");
		sb.append(get_ID()).append("-C_UOM_ID=").append(getC_UOM_ID())
			.append(",C_UOM_To_ID=").append(getC_UOM_To_ID())
			.append(",M_Product_ID=").append(getM_Product_ID())
			.append("-Multiply=").append(getMultiplyRate())
			.append("/Divide=").append(getDivideRate())
			.append ("]");
		return sb.toString ();
	}	//	toString
	
}	//	UOMConversion
