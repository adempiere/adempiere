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
import org.compiere.util.KeyNamePair;

/** Generated Model for PP_ForecastRunMaster
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_PP_ForecastRunMaster extends PO implements I_PP_ForecastRunMaster, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_PP_ForecastRunMaster (Properties ctx, int PP_ForecastRunMaster_ID, String trxName)
    {
      super (ctx, PP_ForecastRunMaster_ID, trxName);
      /** if (PP_ForecastRunMaster_ID == 0)
        {
			setPP_ForecastRunMaster_ID (0);
        } */
    }

    /** Load Constructor */
    public X_PP_ForecastRunMaster (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PP_ForecastRunMaster[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Factor Alpha.
		@param FactorAlpha 
		Identifies an Factor Alpha
	  */
	public void setFactorAlpha (BigDecimal FactorAlpha)
	{
		set_Value (COLUMNNAME_FactorAlpha, FactorAlpha);
	}

	/** Get Factor Alpha.
		@return Identifies an Factor Alpha
	  */
	public BigDecimal getFactorAlpha () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FactorAlpha);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Factor Beta.
		@param FactorBeta 
		Identifies a Factor Beta
	  */
	public void setFactorBeta (BigDecimal FactorBeta)
	{
		set_Value (COLUMNNAME_FactorBeta, FactorBeta);
	}

	/** Get Factor Beta.
		@return Identifies a Factor Beta
	  */
	public BigDecimal getFactorBeta () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FactorBeta);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Factor Gamma.
		@param FactorGamma 
		Identifies a Factor Gamma
	  */
	public void setFactorGamma (BigDecimal FactorGamma)
	{
		set_Value (COLUMNNAME_FactorGamma, FactorGamma);
	}

	/** Get Factor Gamma.
		@return Identifies a Factor Gamma
	  */
	public BigDecimal getFactorGamma () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FactorGamma);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Factor Multiplier.
		@param FactorMultiplier 
		Identifies a Factor Multiplier
	  */
	public void setFactorMultiplier (BigDecimal FactorMultiplier)
	{
		set_Value (COLUMNNAME_FactorMultiplier, FactorMultiplier);
	}

	/** Get Factor Multiplier.
		@return Identifies a Factor Multiplier
	  */
	public BigDecimal getFactorMultiplier () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FactorMultiplier);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Factor Scale.
		@param FactorScale 
		Identifies a Factor Scale
	  */
	public void setFactorScale (BigDecimal FactorScale)
	{
		set_Value (COLUMNNAME_FactorScale, FactorScale);
	}

	/** Get Factor Scale.
		@return Identifies a Factor Scale
	  */
	public BigDecimal getFactorScale () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FactorScale);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set User Factor.
		@param FactorUser 
		Identifies a User Factor
	  */
	public void setFactorUser (BigDecimal FactorUser)
	{
		set_Value (COLUMNNAME_FactorUser, FactorUser);
	}

	/** Get User Factor.
		@return Identifies a User Factor
	  */
	public BigDecimal getFactorUser () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FactorUser);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getM_Product_ID()));
    }

	public org.compiere.model.I_M_Warehouse getM_Warehouse() throws RuntimeException
    {
		return (org.compiere.model.I_M_Warehouse)MTable.get(getCtx(), org.compiere.model.I_M_Warehouse.Table_Name)
			.getPO(getM_Warehouse_ID(), get_TrxName());	}

	/** Set Warehouse.
		@param M_Warehouse_ID 
		Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID < 0) 
			set_Value (COLUMNNAME_M_Warehouse_ID, null);
		else 
			set_Value (COLUMNNAME_M_Warehouse_ID, Integer.valueOf(M_Warehouse_ID));
	}

	/** Get Warehouse.
		@return Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Warehouse_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_PP_ForecastDefinitionLine getPP_ForecastDefinitionLine() throws RuntimeException
    {
		return (org.eevolution.model.I_PP_ForecastDefinitionLine)MTable.get(getCtx(), org.eevolution.model.I_PP_ForecastDefinitionLine.Table_Name)
			.getPO(getPP_ForecastDefinitionLine_ID(), get_TrxName());	}

	/** Set Forecast Definition Line.
		@param PP_ForecastDefinitionLine_ID Forecast Definition Line	  */
	public void setPP_ForecastDefinitionLine_ID (int PP_ForecastDefinitionLine_ID)
	{
		if (PP_ForecastDefinitionLine_ID < 1) 
			set_Value (COLUMNNAME_PP_ForecastDefinitionLine_ID, null);
		else 
			set_Value (COLUMNNAME_PP_ForecastDefinitionLine_ID, Integer.valueOf(PP_ForecastDefinitionLine_ID));
	}

	/** Get Forecast Definition Line.
		@return Forecast Definition Line	  */
	public int getPP_ForecastDefinitionLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_ForecastDefinitionLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

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
			set_Value (COLUMNNAME_PP_ForecastRun_ID, null);
		else 
			set_Value (COLUMNNAME_PP_ForecastRun_ID, Integer.valueOf(PP_ForecastRun_ID));
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