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
package org.adempiere.core.domains.models;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for FM_DunningLevel
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3 - $Id$ */
public class X_FM_DunningLevel extends PO implements I_FM_DunningLevel, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20220507L;

    /** Standard Constructor */
    public X_FM_DunningLevel (Properties ctx, int FM_DunningLevel_ID, String trxName)
    {
      super (ctx, FM_DunningLevel_ID, trxName);
      /** if (FM_DunningLevel_ID == 0)
        {
			setFM_Dunning_ID (0);
			setFM_DunningLevel_ID (0);
        } */
    }

    /** Load Constructor */
    public X_FM_DunningLevel (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FM_DunningLevel[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Days From.
		@param DaysFrom Days From	  */
	public void setDaysFrom (int DaysFrom)
	{
		set_Value (COLUMNNAME_DaysFrom, Integer.valueOf(DaysFrom));
	}

	/** Get Days From.
		@return Days From	  */
	public int getDaysFrom () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DaysFrom);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Days To.
		@param DaysTo Days To	  */
	public void setDaysTo (int DaysTo)
	{
		set_Value (COLUMNNAME_DaysTo, Integer.valueOf(DaysTo));
	}

	/** Get Days To.
		@return Days To	  */
	public int getDaysTo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DaysTo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	public org.adempiere.core.domains.models.I_FM_Dunning getFM_Dunning() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_FM_Dunning)MTable.get(getCtx(), org.adempiere.core.domains.models.I_FM_Dunning.Table_Name)
			.getPO(getFM_Dunning_ID(), get_TrxName());	}

	/** Set Financial Dunning.
		@param FM_Dunning_ID Financial Dunning	  */
	public void setFM_Dunning_ID (int FM_Dunning_ID)
	{
		if (FM_Dunning_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FM_Dunning_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FM_Dunning_ID, Integer.valueOf(FM_Dunning_ID));
	}

	/** Get Financial Dunning.
		@return Financial Dunning	  */
	public int getFM_Dunning_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_Dunning_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getFM_Dunning_ID()));
    }

	/** Set Financial Dunning Level.
		@param FM_DunningLevel_ID Financial Dunning Level	  */
	public void setFM_DunningLevel_ID (int FM_DunningLevel_ID)
	{
		if (FM_DunningLevel_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FM_DunningLevel_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FM_DunningLevel_ID, Integer.valueOf(FM_DunningLevel_ID));
	}

	/** Get Financial Dunning Level.
		@return Financial Dunning Level	  */
	public int getFM_DunningLevel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_DunningLevel_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_FM_Rate getFM_Rate() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_FM_Rate)MTable.get(getCtx(), org.adempiere.core.domains.models.I_FM_Rate.Table_Name)
			.getPO(getFM_Rate_ID(), get_TrxName());	}

	/** Set Financial Rate.
		@param FM_Rate_ID Financial Rate	  */
	public void setFM_Rate_ID (int FM_Rate_ID)
	{
		if (FM_Rate_ID < 1) 
			set_Value (COLUMNNAME_FM_Rate_ID, null);
		else 
			set_Value (COLUMNNAME_FM_Rate_ID, Integer.valueOf(FM_Rate_ID));
	}

	/** Get Financial Rate.
		@return Financial Rate	  */
	public int getFM_Rate_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_Rate_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_FM_StatusType getFM_StatusType() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_FM_StatusType)MTable.get(getCtx(), org.adempiere.core.domains.models.I_FM_StatusType.Table_Name)
			.getPO(getFM_StatusType_ID(), get_TrxName());	}

	/** Set Financial Status Type.
		@param FM_StatusType_ID Financial Status Type	  */
	public void setFM_StatusType_ID (int FM_StatusType_ID)
	{
		if (FM_StatusType_ID < 1) 
			set_Value (COLUMNNAME_FM_StatusType_ID, null);
		else 
			set_Value (COLUMNNAME_FM_StatusType_ID, Integer.valueOf(FM_StatusType_ID));
	}

	/** Get Financial Status Type.
		@return Financial Status Type	  */
	public int getFM_StatusType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_StatusType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Accrual.
		@param IsAccrual 
		Indicates if Accrual or Cash Based accounting will be used
	  */
	public void setIsAccrual (boolean IsAccrual)
	{
		set_Value (COLUMNNAME_IsAccrual, Boolean.valueOf(IsAccrual));
	}

	/** Get Accrual.
		@return Indicates if Accrual or Cash Based accounting will be used
	  */
	public boolean isAccrual () 
	{
		Object oo = get_Value(COLUMNNAME_IsAccrual);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Suspend Loan.
		@param IsSuspend Suspend Loan	  */
	public void setIsSuspend (boolean IsSuspend)
	{
		set_Value (COLUMNNAME_IsSuspend, Boolean.valueOf(IsSuspend));
	}

	/** Get Suspend Loan.
		@return Suspend Loan	  */
	public boolean isSuspend () 
	{
		Object oo = get_Value(COLUMNNAME_IsSuspend);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Provision Percentage.
		@param ProvisionPercentage Provision Percentage	  */
	public void setProvisionPercentage (BigDecimal ProvisionPercentage)
	{
		set_Value (COLUMNNAME_ProvisionPercentage, ProvisionPercentage);
	}

	/** Get Provision Percentage.
		@return Provision Percentage	  */
	public BigDecimal getProvisionPercentage () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ProvisionPercentage);
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