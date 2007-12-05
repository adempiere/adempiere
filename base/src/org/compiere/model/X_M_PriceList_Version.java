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

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for M_PriceList_Version
 *  @author Adempiere (generated) 
 *  @version Release 3.3.1b - $Id$ */
public class X_M_PriceList_Version extends PO implements I_M_PriceList_Version, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_M_PriceList_Version (Properties ctx, int M_PriceList_Version_ID, String trxName)
    {
      super (ctx, M_PriceList_Version_ID, trxName);
      /** if (M_PriceList_Version_ID == 0)
        {
			setM_DiscountSchema_ID (0);
			setM_PriceList_ID (0);
			setM_PriceList_Version_ID (0);
			setName (null);
// @#Date@
			setValidFrom (new Timestamp(System.currentTimeMillis()));
// @#Date@
        } */
    }

    /** Load Constructor */
    public X_M_PriceList_Version (Properties ctx, ResultSet rs, String trxName)
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
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID);
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_M_PriceList_Version[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	public I_M_DiscountSchema getM_DiscountSchema() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_M_DiscountSchema.Table_Name);
        I_M_DiscountSchema result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_DiscountSchema)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_DiscountSchema_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Discount Schema.
		@param M_DiscountSchema_ID 
		Schema to calculate the trade discount percentage
	  */
	public void setM_DiscountSchema_ID (int M_DiscountSchema_ID)
	{
		if (M_DiscountSchema_ID < 1)
			 throw new IllegalArgumentException ("M_DiscountSchema_ID is mandatory.");
		set_Value (COLUMNNAME_M_DiscountSchema_ID, Integer.valueOf(M_DiscountSchema_ID));
	}

	/** Get Discount Schema.
		@return Schema to calculate the trade discount percentage
	  */
	public int getM_DiscountSchema_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_DiscountSchema_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_PriceList getM_PriceList() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_M_PriceList.Table_Name);
        I_M_PriceList result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_PriceList)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_PriceList_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Price List.
		@param M_PriceList_ID 
		Unique identifier of a Price List
	  */
	public void setM_PriceList_ID (int M_PriceList_ID)
	{
		if (M_PriceList_ID < 1)
			 throw new IllegalArgumentException ("M_PriceList_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_PriceList_ID, Integer.valueOf(M_PriceList_ID));
	}

	/** Get Price List.
		@return Unique identifier of a Price List
	  */
	public int getM_PriceList_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_PriceList_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Price List Version.
		@param M_PriceList_Version_ID 
		Identifies a unique instance of a Price List
	  */
	public void setM_PriceList_Version_ID (int M_PriceList_Version_ID)
	{
		if (M_PriceList_Version_ID < 1)
			 throw new IllegalArgumentException ("M_PriceList_Version_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_PriceList_Version_ID, Integer.valueOf(M_PriceList_Version_ID));
	}

	/** Get Price List Version.
		@return Identifies a unique instance of a Price List
	  */
	public int getM_PriceList_Version_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_PriceList_Version_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** M_Pricelist_Version_Base_ID AD_Reference_ID=188 */
	public static final int M_PRICELIST_VERSION_BASE_ID_AD_Reference_ID=188;
	/** Set Base Price List.
		@param M_Pricelist_Version_Base_ID 
		Source for Price list calculations
	  */
	public void setM_Pricelist_Version_Base_ID (int M_Pricelist_Version_Base_ID)
	{
		if (M_Pricelist_Version_Base_ID <= 0) 
			set_Value (COLUMNNAME_M_Pricelist_Version_Base_ID, null);
		else 
			set_Value (COLUMNNAME_M_Pricelist_Version_Base_ID, Integer.valueOf(M_Pricelist_Version_Base_ID));
	}

	/** Get Base Price List.
		@return Source for Price list calculations
	  */
	public int getM_Pricelist_Version_Base_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Pricelist_Version_Base_ID);
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

	/** Set Create.
		@param ProcCreate Create	  */
	public void setProcCreate (String ProcCreate)
	{

		if (ProcCreate != null && ProcCreate.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			ProcCreate = ProcCreate.substring(0, 1);
		}
		set_Value (COLUMNNAME_ProcCreate, ProcCreate);
	}

	/** Get Create.
		@return Create	  */
	public String getProcCreate () 
	{
		return (String)get_Value(COLUMNNAME_ProcCreate);
	}

	/** Set Valid from.
		@param ValidFrom 
		Valid from including this date (first day)
	  */
	public void setValidFrom (Timestamp ValidFrom)
	{
		if (ValidFrom == null)
			throw new IllegalArgumentException ("ValidFrom is mandatory.");
		set_Value (COLUMNNAME_ValidFrom, ValidFrom);
	}

	/** Get Valid from.
		@return Valid from including this date (first day)
	  */
	public Timestamp getValidFrom () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidFrom);
	}
}