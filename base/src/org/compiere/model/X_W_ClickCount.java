/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
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
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for W_ClickCount
 *  @author Adempiere (generated) 
 *  @version Release 3.3.1t - $Id$ */
public class X_W_ClickCount extends PO implements I_W_ClickCount, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_W_ClickCount (Properties ctx, int W_ClickCount_ID, String trxName)
    {
      super (ctx, W_ClickCount_ID, trxName);
      /** if (W_ClickCount_ID == 0)
        {
			setName (null);
			setTargetURL (null);
			setW_ClickCount_ID (0);
        } */
    }

    /** Load Constructor */
    public X_W_ClickCount (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_W_ClickCount[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** C_BPartner_ID AD_Reference_ID=232 */
	public static final int C_BPARTNER_ID_AD_Reference_ID=232;
	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID <= 0) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Counter.
		@param Counter 
		Count Value
	  */
	public void setCounter (int Counter)
	{
		throw new IllegalArgumentException ("Counter is virtual column");	}

	/** Get Counter.
		@return Count Value
	  */
	public int getCounter () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Counter);
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

		if (Description != null && Description.length() > 255)
		{
			log.warning("Length > 255 - truncated");
			Description = Description.substring(0, 255);
		}
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");

		if (Name.length() > 60)
		{
			log.warning("Length > 60 - truncated");
			Name = Name.substring(0, 60);
		}
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

	/** Set Target URL.
		@param TargetURL 
		URL for the Target
	  */
	public void setTargetURL (String TargetURL)
	{
		if (TargetURL == null)
			throw new IllegalArgumentException ("TargetURL is mandatory.");

		if (TargetURL.length() > 120)
		{
			log.warning("Length > 120 - truncated");
			TargetURL = TargetURL.substring(0, 120);
		}
		set_Value (COLUMNNAME_TargetURL, TargetURL);
	}

	/** Get Target URL.
		@return URL for the Target
	  */
	public String getTargetURL () 
	{
		return (String)get_Value(COLUMNNAME_TargetURL);
	}

	/** Set Click Count.
		@param W_ClickCount_ID 
		Web Click Management
	  */
	public void setW_ClickCount_ID (int W_ClickCount_ID)
	{
		if (W_ClickCount_ID < 1)
			 throw new IllegalArgumentException ("W_ClickCount_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_W_ClickCount_ID, Integer.valueOf(W_ClickCount_ID));
	}

	/** Get Click Count.
		@return Web Click Management
	  */
	public int getW_ClickCount_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_W_ClickCount_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}