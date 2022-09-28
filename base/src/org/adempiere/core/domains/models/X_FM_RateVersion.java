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
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for FM_RateVersion
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3 - $Id$ */
public class X_FM_RateVersion extends PO implements I_FM_RateVersion, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20220507L;

    /** Standard Constructor */
    public X_FM_RateVersion (Properties ctx, int FM_RateVersion_ID, String trxName)
    {
      super (ctx, FM_RateVersion_ID, trxName);
      /** if (FM_RateVersion_ID == 0)
        {
			setFM_Rate_ID (0);
			setFM_RateVersion_ID (0);
			setName (null);
			setRate (Env.ZERO);
			setValidFrom (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_FM_RateVersion (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FM_RateVersion[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	public org.adempiere.core.domains.models.I_FM_Rate getFM_Rate() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_FM_Rate)MTable.get(getCtx(), org.adempiere.core.domains.models.I_FM_Rate.Table_Name)
			.getPO(getFM_Rate_ID(), get_TrxName());	}

	/** Set Financial Rate.
		@param FM_Rate_ID Financial Rate	  */
	public void setFM_Rate_ID (int FM_Rate_ID)
	{
		if (FM_Rate_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FM_Rate_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FM_Rate_ID, Integer.valueOf(FM_Rate_ID));
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

	/** Set Financial Rate Version.
		@param FM_RateVersion_ID Financial Rate Version	  */
	public void setFM_RateVersion_ID (int FM_RateVersion_ID)
	{
		if (FM_RateVersion_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FM_RateVersion_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FM_RateVersion_ID, Integer.valueOf(FM_RateVersion_ID));
	}

	/** Get Financial Rate Version.
		@return Financial Rate Version	  */
	public int getFM_RateVersion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_RateVersion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Rate.
		@param Rate 
		Rate or Tax or Exchange
	  */
	public void setRate (BigDecimal Rate)
	{
		set_Value (COLUMNNAME_Rate, Rate);
	}

	/** Get Rate.
		@return Rate or Tax or Exchange
	  */
	public BigDecimal getRate () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Rate);
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

	/** Set Valid from.
		@param ValidFrom 
		Valid from including this date (first day)
	  */
	public void setValidFrom (Timestamp ValidFrom)
	{
		set_Value (COLUMNNAME_ValidFrom, ValidFrom);
	}

	/** Get Valid from.
		@return Valid from including this date (first day)
	  */
	public Timestamp getValidFrom () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidFrom);
	}

	/** Set Valid to.
		@param ValidTo 
		Valid to including this date (last day)
	  */
	public void setValidTo (Timestamp ValidTo)
	{
		set_Value (COLUMNNAME_ValidTo, ValidTo);
	}

	/** Get Valid to.
		@return Valid to including this date (last day)
	  */
	public Timestamp getValidTo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidTo);
	}
}