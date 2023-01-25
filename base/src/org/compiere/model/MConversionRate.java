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

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.adempiere.core.domains.models.I_C_Conversion_Rate;
import org.adempiere.core.domains.models.X_C_Conversion_Rate;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;
import org.compiere.util.Trx;

/**
 *	Currency Conversion Rate Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MConversionRate.java,v 1.2 2006/07/30 00:58:18 jjanke Exp $
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 297 ] Payment Selection must be like ADempiere Document
 *		@see https://github.com/adempiere/adempiere/issues/297
 *		<a href="https://github.com/adempiere/adempiere/issues/1605">
 * 		@see FR [ 1605 ] New helper method for get Conversion Rate ID for MConversionRate Class</a>
 */
public class MConversionRate extends X_C_Conversion_Rate
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8171829790483133141L;
	
	/**	Logger						*/
	private static CLogger		s_log = CLogger.getCLogger (MConversionRate.class);
	
	/**	Cache						*/
	private static CCache<Integer, MConversionRate> s_cache	= new CCache<Integer, MConversionRate>(Table_Name, 40, 5);	//	5 minutes

	/**
	 *	Convert an amount to base Currency
	 *	@param ctx context
	 *  @param CurFrom_ID  The C_Currency_ID FROM
	 *  @param ConvDate conversion date - if null - use current date
	 *  @param C_ConversionType_ID conversion rate type - if 0 - use Default
	 *  @param Amt amount to be converted
	 * 	@param AD_Client_ID client
	 * 	@param AD_Org_ID organization
	 *  @return converted amount
	 */
	public static BigDecimal convertBase (Properties ctx,
		BigDecimal Amt, int CurFrom_ID, 
		Timestamp ConvDate, int C_ConversionType_ID, 
		int AD_Client_ID, int AD_Org_ID)
	{
		return convert (ctx, Amt, CurFrom_ID, MClient.get(ctx).getC_Currency_ID(), 
			ConvDate, C_ConversionType_ID, AD_Client_ID, AD_Org_ID);
	}	//	convertBase

	
	/**
	 *  Convert an amount with today's default rate
	 *	@param ctx context
	 *  @param CurFrom_ID  The C_Currency_ID FROM
	 *  @param CurTo_ID    The C_Currency_ID TO
	 *  @param Amt amount to be converted
	 * 	@param AD_Client_ID client
	 * 	@param AD_Org_ID organization
	 *  @return converted amount
	 */
	public static BigDecimal convert (Properties ctx,
		BigDecimal Amt, int CurFrom_ID, int CurTo_ID, 
		int AD_Client_ID, int AD_Org_ID)
	{
		return convert (ctx, Amt, CurFrom_ID, CurTo_ID, null, 0, AD_Client_ID, AD_Org_ID);
	}   //  convert

	/**
	 *	Convert an amount
	 *	@param ctx context
	 *  @param CurFrom_ID  The C_Currency_ID FROM
	 *  @param CurTo_ID    The C_Currency_ID TO
	 *  @param ConvDate conversion date - if null - use current date
	 *  @param C_ConversionType_ID conversion rate type - if 0 - use Default
	 *  @param Amt amount to be converted
	 * 	@param AD_Client_ID client
	 * 	@param AD_Org_ID organization
	 *  @return converted amount or null if no rate
	 */
	public static BigDecimal convert (Properties ctx,
		BigDecimal Amt, int CurFrom_ID, int CurTo_ID,
		Timestamp ConvDate, int C_ConversionType_ID, 
		int AD_Client_ID, int AD_Org_ID)
	{
		if (Amt == null)
			throw new IllegalArgumentException("Required parameter missing - Amt");
		if (CurFrom_ID == CurTo_ID || Amt.compareTo(Env.ZERO)==0)
			return Amt;
		//	Get Rate
		BigDecimal retValue = getRate (CurFrom_ID, CurTo_ID, 
			ConvDate, C_ConversionType_ID,
			AD_Client_ID, AD_Org_ID);
		if (retValue == null)
			return null;
			
		//	Get Amount in Currency Precision
		retValue = retValue.multiply(Amt);
		int stdPrecision = MCurrency.getStdPrecision(ctx, CurTo_ID);
		if (retValue.scale() > stdPrecision)
			retValue = retValue.setScale(stdPrecision, RoundingMode.HALF_UP);
			
		return retValue;
	}	//	convert

	/**
	 * Sets system spot conversion rate for a single day.
	 * Checks for overlaps of spot rate is made. If an overlap is found, the overlapping
	 * rate is removed.
	 * 
	 *  @param CurFrom_ISO		Currency from ISO code
	 *  @param CurTo_ISO		Currency to ISO code
	 *  @param spotDate			If null, today's date is used.
	 *  @param multiplyRate		CurFrom_ISO * MultiplyRate = amount in CurTo_ISO
	 */
	public static void setRate(String CurFrom_ISO, String CurTo_ISO, java.util.Date spotDate, 
							   BigDecimal MultiplyRate) throws Exception {
		
		String trxName = Trx.createTrxName();
		Trx trx = Trx.get(trxName, true);
		Properties ctx = Env.getCtx();
		MCurrency curFrom = MCurrency.get(ctx, CurFrom_ISO);
		if (curFrom==null) throw new Exception("Invalid currency " + CurFrom_ISO);
		MCurrency curTo = MCurrency.get(ctx, CurTo_ISO);
		if (curTo==null) throw new Exception("Invalid currency " + CurTo_ISO);
		
		java.sql.Timestamp startTs;
		if (spotDate==null) {
			spotDate = Calendar.getInstance().getTime();
		}
		Calendar spotCal = Calendar.getInstance();
		spotCal.setTime(spotDate);
		spotCal.set(Calendar.HOUR_OF_DAY, 0);
		spotCal.set(Calendar.MINUTE, 0);
		spotCal.set(Calendar.SECOND, 0);
		spotCal.set(Calendar.MILLISECOND, 0);
		startTs = new java.sql.Timestamp(spotCal.getTimeInMillis());
		final String whereClause = "C_Currency_ID=? and C_Currency_ID_To=? and ValidFrom>=? and ValidTo<=? and C_ConversionType_ID=?";
		MConversionRate rate, updateRate = null;
		List<MConversionRate> rates = new Query(ctx, I_C_Conversion_Rate.Table_Name, whereClause,trxName)
				.setParameters(curFrom.get_ID(), curTo.get_ID(), startTs, startTs, MConversionType.TYPE_SPOT)
				.list();

		if (rates.size()>0) {
			for (Iterator<MConversionRate> it = rates.iterator(); it.hasNext();) {
				rate = it.next();
				if (!rate.getValidFrom().equals(rate.getValidTo())) {
					// Remove this since it's for more than one day
					rate.deleteEx(true, trxName);
				} else {
					updateRate = rate;
				}
			}
		}
		if (updateRate==null) {
			updateRate = new MConversionRate(ctx, 0, trxName);
			updateRate.setAD_Client_ID(0);
			updateRate.setAD_Org_ID(0);
			updateRate.setC_Currency_ID(curFrom.get_ID());
			updateRate.setC_Currency_ID_To(curTo.get_ID());
			updateRate.setValidFrom(startTs);
			updateRate.setValidTo(startTs);
			updateRate.setC_ConversionType_ID(MConversionType.TYPE_SPOT);
		}
		updateRate.setMultiplyRate(MultiplyRate);
		updateRate.saveEx(trxName);
		trx.commit(true);
		trx.close();
	}
	
	/**
	 *	Get Currency Conversion Rate
	 *  @param  currencyFromId  The C_Currency_ID FROM
	 *  @param  currencyToId    The C_Currency_ID TO
	 *  @param  conversionDate    The Conversion date - if null - use current date
	 *  @param  conversionTypeId Conversion rate type - if 0 - use Default
	 * 	@param	clientId client
	 * 	@param	OrganizationId	organization
	 *  @return currency Rate or null
	 */
	public static BigDecimal getRate (int currencyFromId, int currencyToId,
		Timestamp conversionDate, int conversionTypeId, int clientId, int OrganizationId)
	{
		if (currencyFromId == currencyToId)
			return Env.ONE;
		//	Conversion Type
		int C_ConversionType_ID = conversionTypeId;
		if (C_ConversionType_ID == 0)
			C_ConversionType_ID = MConversionType.getDefault(clientId);
		//	Conversion Date
		if (conversionDate == null)
			conversionDate = new Timestamp (System.currentTimeMillis());

		//	Get Rate
		String sql = "SELECT MultiplyRate "
			+ "FROM C_Conversion_Rate "
			+ "WHERE C_Currency_ID=?"					//	#1
			+ " AND C_Currency_ID_To=?"					//	#2
			+ " AND	C_ConversionType_ID=?"				//	#3
			+ " AND	? >= ValidFrom"						//	#4
			+ " AND	? <= ValidTo"						//	#5
			+ " AND AD_Client_ID IN (0,?)"				//	#6
			+ " AND AD_Org_ID IN (0,?) "				//	#7
			+ " AND IsActive='Y' "				//	#6
			+ "ORDER BY AD_Client_ID DESC, AD_Org_ID DESC, ValidFrom DESC";
		BigDecimal retValue = DB.getSQLValueBD(null, sql, currencyFromId, currencyToId, C_ConversionType_ID, conversionDate, conversionDate, clientId, OrganizationId);			
		if (retValue == null)
			s_log.info ("getRate - not found - CurFrom=" + currencyFromId 
			  + ", CurTo=" + currencyToId
			  + ", " + conversionDate 
			  + ", Type=" + conversionTypeId + (conversionTypeId==C_ConversionType_ID ? "" : "->" + C_ConversionType_ID) 
			  + ", Client=" + clientId 
			  + ", Org=" + OrganizationId);
		return retValue;
	}	//	getRate
	
	/**
	 * Get Conversion Rate ID from Currency
	 * @param currencyFromId
	 * @param CurencyToId
	 * @param conversionDate
	 * @param conversionTypeId
	 * @param clientId
	 * @param organizationId
	 * @return
	 */
	public static int getConversionRateId(int currencyFromId, int CurencyToId, Timestamp conversionDate, int conversionTypeId, int clientId, int organizationId) {
		if (currencyFromId == CurencyToId) {
			return 0;
		}
		//	Conversion Type
		int internalConversionTypeId = conversionTypeId;
		if (internalConversionTypeId == 0) {
			internalConversionTypeId = MConversionType.getDefault(clientId);
		}
		//	Conversion Date
		if (conversionDate == null) {
			conversionDate = new Timestamp (System.currentTimeMillis());
		}
		//	Get Rate
		String sql = "SELECT C_Conversion_Rate_ID "
				+ "FROM C_Conversion_Rate "
				+ "WHERE C_Currency_ID=?"					//	#1
				+ " AND C_Currency_ID_To=?"					//	#2
				+ " AND	C_ConversionType_ID=?"				//	#3
				+ " AND	? >= ValidFrom"						//	#4
				+ " AND	? <= ValidTo"						//	#5
				+ " AND AD_Client_ID IN (0,?)"				//	#6
				+ " AND AD_Org_ID IN (0,?) "				//	#7
				+ " AND IsActive = 'Y' "					//	#8
				+ "ORDER BY AD_Client_ID DESC, AD_Org_ID DESC, ValidFrom DESC";
		//	Get
		int conversionRateId = DB.getSQLValue(null, sql, currencyFromId, CurencyToId, internalConversionTypeId, conversionDate, conversionDate, clientId, organizationId);
		//	Show Log
		if (conversionRateId == -1) {
			s_log.info ("getRate - not found - CurFrom=" + currencyFromId 
						  + ", CurTo=" + CurencyToId
						  + ", " + conversionDate 
						  + ", Type=" + conversionTypeId + (conversionTypeId==internalConversionTypeId ? "" : "->" + internalConversionTypeId) 
						  + ", Client=" + clientId 
						  + ", Org=" + organizationId);
		}
		//	Return
		return conversionRateId;
	}	//	getConversionRateId
	
	/**
	 * Get Rate from Conversion ID
	 * @param ctx
	 * @param C_Conversion_Rate_ID
	 * @return
	 */
	public static BigDecimal getRate(Properties ctx, int C_Conversion_Rate_ID) {
		MConversionRate conversion = get(ctx, C_Conversion_Rate_ID);
		//	Valid conversion
		if(conversion == null)
			return null;
		// Default Return
		return conversion.getMultiplyRate();
	}

	/**
	 * 	Get MConversionRate from Cache
	 *	@param ctx context
	 *	@param C_Conversion_Rate_ID id
	 *	@return MConversionRate or null
	 */
	public static MConversionRate get (Properties ctx, int C_Conversion_Rate_ID) {
		if (C_Conversion_Rate_ID <= 0) {
			return null;
		}
		Integer key = Integer.valueOf(C_Conversion_Rate_ID);
		MConversionRate retValue = (MConversionRate) s_cache.get (key);
		if (retValue != null) {
			return retValue;
		}
		retValue = new MConversionRate (ctx, C_Conversion_Rate_ID, null);
		if (retValue.get_ID () != 0) {
			s_cache.put (key, retValue);
		}
		return retValue;
	}	//	get
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_Conversion_Rate_ID id
	 *	@param trxName transaction
	 */
	public MConversionRate (Properties ctx, int C_Conversion_Rate_ID, String trxName)
	{
		super(ctx, C_Conversion_Rate_ID, trxName);
		if (C_Conversion_Rate_ID == 0)
		{
		//	setC_Conversion_Rate_ID (0);
		//	setC_Currency_ID (0);
		//	setC_Currency_ID_To (null);
			super.setDivideRate (Env.ZERO);
			super.setMultiplyRate (Env.ZERO);
			setValidFrom (new Timestamp(System.currentTimeMillis()));
		}
	}	//	MConversionRate

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MConversionRate (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MConversionRate

	/**
	 * 	New Constructor
	 *	@param po parent
	 *	@param C_ConversionType_ID conversion type
	 *	@param C_Currency_ID currency
	 *	@param C_Currency_ID_To currency to
	 *	@param MultiplyRate multiply rate
	 *	@param ValidFrom valid from
	 */
	public MConversionRate (PO po, 
		int C_ConversionType_ID, 
		int C_Currency_ID, int C_Currency_ID_To, 
		BigDecimal MultiplyRate, Timestamp ValidFrom)
	{
		this (po.getCtx(), 0, po.get_TrxName());
		setClientOrg(po);
		setC_ConversionType_ID (C_ConversionType_ID);
		setC_Currency_ID (C_Currency_ID);
		setC_Currency_ID_To (C_Currency_ID_To);
		//
		setMultiplyRate (MultiplyRate);
		setValidFrom(ValidFrom);
	}	//	MConversionRate

	/**
	 * 	Set Multiply Rate
	 * 	Sets also Divide Rate
	 *	@param MultiplyRate multiply rate
	 */
	public void setMultiplyRate (BigDecimal MultiplyRate)
	{
		if (MultiplyRate == null 
			|| MultiplyRate.compareTo(Env.ZERO) == 0 
			|| MultiplyRate.compareTo(Env.ONE) == 0)
		{
			super.setDivideRate(Env.ONE);
			super.setMultiplyRate(Env.ONE);
		}
		else
		{
			super.setMultiplyRate(MultiplyRate);
			super.setDivideRate(Env.ONE.divide(MultiplyRate, MathContext.DECIMAL128));
		}
	}	//	setMultiplyRate

	/**
	 *	Set Divide Rate.
	 *	Sets also Multiply Rate
	 *	@param	DivideRate divide rate
	 */
	public void setDivideRate (BigDecimal DivideRate)
	{
		if (DivideRate == null 
			|| DivideRate.compareTo(Env.ZERO) == 0 
			|| DivideRate.compareTo(Env.ONE) == 0)
		{
			super.setDivideRate(Env.ONE);
			super.setMultiplyRate(Env.ONE);
		}
		else
		{
			super.setDivideRate(DivideRate);
			super.setMultiplyRate(Env.ONE.divide(DivideRate, MathContext.DECIMAL128));
		}
	}	//	setDivideRate

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("MConversionRate[");
		sb.append(get_ID())
			.append(",Currency=").append(getC_Currency_ID())
			.append(",To=").append(getC_Currency_ID_To())
			.append(", Multiply=").append(getMultiplyRate())
			.append(",Divide=").append(getDivideRate())
			.append(", ValidFrom=").append(getValidFrom());
		sb.append("]");
		return sb.toString();
	}	//	toString

	
	/**
	 * 	Before Save.
	 * 	- Same Currency
	 * 	- Date Range Check
	 * 	- Set To date to 2056
	 *	@param newRecord new
	 *	@return true if OK to save
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		//	From - To is the same
		if (getC_Currency_ID() == getC_Currency_ID_To())
		{
			log.saveError("Error", Msg.parseTranslation(getCtx(), "@C_Currency_ID@ = @C_Currency_ID@"));
			return false;
		}
		//	Nothing to convert
		if (getMultiplyRate().compareTo(Env.ZERO) <= 0)
		{
			log.saveError("Error", Msg.parseTranslation(getCtx(), "@MultiplyRate@ <= 0"));
			return false;
		}

		//	Date Range Check
		Timestamp from = getValidFrom();
		if (getValidTo() == null)
			setValidTo (TimeUtil.getDay(2056, 1, 29));	//	 no exchange rates after my 100th birthday
		Timestamp to = getValidTo();
		
		if (to.before(from))
		{
			SimpleDateFormat df = DisplayType.getDateFormat(DisplayType.Date);
			log.saveError("Error", df.format(to) + " < " + df.format(from));
			return false;
		}
		
		return true;
	}	//	beforeSave

	/** Return the message to show when no exchange rate is found */
	public static String getErrorMessage(Properties ctx, String adMessage, int currencyFromID, int currencyToID, int convertionTypeId, Timestamp date, String trxName)
	{
		if (convertionTypeId == 0)
			convertionTypeId = MConversionType.getDefault(Env.getAD_Client_ID(ctx));
		String retValue = Msg.getMsg(ctx, adMessage,
				new Object[] {MCurrency.get(ctx, currencyFromID).getISO_Code(), MCurrency.get(ctx, currencyToID).getISO_Code(), new MConversionType(ctx, convertionTypeId, trxName).getName(), date});
		return retValue;
	}
	
}	//	MConversionRate
