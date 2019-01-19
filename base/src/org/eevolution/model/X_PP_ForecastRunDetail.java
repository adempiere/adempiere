/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.eevolution.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for PP_ForecastRunDetail
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_PP_ForecastRunDetail extends PO implements I_PP_ForecastRunDetail, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_PP_ForecastRunDetail (Properties ctx, int PP_ForecastRunDetail_ID, String trxName)
    {
      super (ctx, PP_ForecastRunDetail_ID, trxName);
      /** if (PP_ForecastRunDetail_ID == 0)
        {
			setPP_ForecastRunDetail_ID (0);
			setPP_ForecastRunMaster_ID (0);
			setPP_Period_ID (0);
        } */
    }

    /** Load Constructor */
    public X_PP_ForecastRunDetail (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_PP_ForecastRunDetail[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Forecast Run Detail.
		@param PP_ForecastRunDetail_ID 
		Contains the forecasting calculation results based on forecast definition.
	  */
	public void setPP_ForecastRunDetail_ID (int PP_ForecastRunDetail_ID)
	{
		if (PP_ForecastRunDetail_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PP_ForecastRunDetail_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PP_ForecastRunDetail_ID, Integer.valueOf(PP_ForecastRunDetail_ID));
	}

	/** Get Forecast Run Detail.
		@return Contains the forecasting calculation results based on forecast definition.
	  */
	public int getPP_ForecastRunDetail_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_ForecastRunDetail_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_PP_ForecastRunMaster getPP_ForecastRunMaster() throws RuntimeException
    {
		return (org.eevolution.model.I_PP_ForecastRunMaster)MTable.get(getCtx(), org.eevolution.model.I_PP_ForecastRunMaster.Table_Name)
			.getPO(getPP_ForecastRunMaster_ID(), get_TrxName());	}

	/** Set Forecast Run Master.
		@param PP_ForecastRunMaster_ID Forecast Run Master	  */
	public void setPP_ForecastRunMaster_ID (int PP_ForecastRunMaster_ID)
	{
		if (PP_ForecastRunMaster_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PP_ForecastRunMaster_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PP_ForecastRunMaster_ID, Integer.valueOf(PP_ForecastRunMaster_ID));
	}

	/** Get Forecast Run Master.
		@return Forecast Run Master	  */
	public int getPP_ForecastRunMaster_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_ForecastRunMaster_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_PP_ForecastRun getPP_ForecastRun() throws RuntimeException
    {
		return (org.eevolution.model.I_PP_ForecastRun)MTable.get(getCtx(), org.eevolution.model.I_PP_ForecastRun.Table_Name)
			.getPO(getPP_ForecastRun_ID(), get_TrxName());	}

	/** Set Forecast Run.
		@param PP_ForecastRun_ID 
		Create the forecast simulation based on the forecast definition
	  */
	public void setPP_ForecastRun_ID (int PP_ForecastRun_ID)
	{
		if (PP_ForecastRun_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PP_ForecastRun_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PP_ForecastRun_ID, Integer.valueOf(PP_ForecastRun_ID));
	}

	/** Get Forecast Run.
		@return Create the forecast simulation based on the forecast definition
	  */
	public int getPP_ForecastRun_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_ForecastRun_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_PP_Period getPP_Period() throws RuntimeException
    {
		return (org.eevolution.model.I_PP_Period)MTable.get(getCtx(), org.eevolution.model.I_PP_Period.Table_Name)
			.getPO(getPP_Period_ID(), get_TrxName());	}

	/** Set Operational Period.
		@param PP_Period_ID 
		Forecast Definition Periods.
	  */
	public void setPP_Period_ID (int PP_Period_ID)
	{
		if (PP_Period_ID < 1) 
			set_Value (COLUMNNAME_PP_Period_ID, null);
		else 
			set_Value (COLUMNNAME_PP_Period_ID, Integer.valueOf(PP_Period_ID));
	}

	/** Get Operational Period.
		@return Forecast Definition Periods.
	  */
	public int getPP_Period_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_Period_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Period No.
		@param PeriodNo 
		Unique Period Number
	  */
	public void setPeriodNo (int PeriodNo)
	{
		set_Value (COLUMNNAME_PeriodNo, Integer.valueOf(PeriodNo));
	}

	/** Get Period No.
		@return Unique Period Number
	  */
	public int getPeriodNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PeriodNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Calculated Quantity.
		@param QtyCalculated 
		Calculated Quantity
	  */
	public void setQtyCalculated (BigDecimal QtyCalculated)
	{
		set_Value (COLUMNNAME_QtyCalculated, QtyCalculated);
	}

	/** Get Calculated Quantity.
		@return Calculated Quantity
	  */
	public BigDecimal getQtyCalculated () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyCalculated);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Immutable Universally Unique Identifier.
		@param UUID 
		Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID)
	{
		set_Value (COLUMNNAME_UUID, UUID);
	}

	/** Get Immutable Universally Unique Identifier.
		@return Immutable Universally Unique Identifier
	  */
	public String getUUID () 
	{
		return (String)get_Value(COLUMNNAME_UUID);
	}
}