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
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for A_Asset_Class
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_A_Asset_Class extends PO implements I_A_Asset_Class, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_A_Asset_Class (Properties ctx, int A_Asset_Class_ID, String trxName)
    {
      super (ctx, A_Asset_Class_ID, trxName);
      /** if (A_Asset_Class_ID == 0)
        {
			setA_Asset_Class_ID (0);
			setA_Life_Period_Max (0);
			setA_Life_Period_Min (0);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_A_Asset_Class (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_A_Asset_Class[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Asset class.
		@param A_Asset_Class_ID Asset class	  */
	public void setA_Asset_Class_ID (int A_Asset_Class_ID)
	{
		if (A_Asset_Class_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_A_Asset_Class_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_A_Asset_Class_ID, Integer.valueOf(A_Asset_Class_ID));
	}

	/** Get Asset class.
		@return Asset class	  */
	public int getA_Asset_Class_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Class_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Life Periods 2004 (min).
		@param A_Life_Period_2004 Life Periods 2004 (min)	  */
	public void setA_Life_Period_2004 (int A_Life_Period_2004)
	{
		set_Value (COLUMNNAME_A_Life_Period_2004, Integer.valueOf(A_Life_Period_2004));
	}

	/** Get Life Periods 2004 (min).
		@return Life Periods 2004 (min)	  */
	public int getA_Life_Period_2004 () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Life_Period_2004);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Life periods (max).
		@param A_Life_Period_Max Life periods (max)	  */
	public void setA_Life_Period_Max (int A_Life_Period_Max)
	{
		set_Value (COLUMNNAME_A_Life_Period_Max, Integer.valueOf(A_Life_Period_Max));
	}

	/** Get Life periods (max).
		@return Life periods (max)	  */
	public int getA_Life_Period_Max () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Life_Period_Max);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Life periods (min).
		@param A_Life_Period_Min Life periods (min)	  */
	public void setA_Life_Period_Min (int A_Life_Period_Min)
	{
		set_Value (COLUMNNAME_A_Life_Period_Min, Integer.valueOf(A_Life_Period_Min));
	}

	/** Get Life periods (min).
		@return Life periods (min)	  */
	public int getA_Life_Period_Min () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Life_Period_Min);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Value 2004.
		@param A_Value_2004 Value 2004	  */
	public void setA_Value_2004 (String A_Value_2004)
	{
		set_Value (COLUMNNAME_A_Value_2004, A_Value_2004);
	}

	/** Get Value 2004.
		@return Value 2004	  */
	public String getA_Value_2004 () 
	{
		return (String)get_Value(COLUMNNAME_A_Value_2004);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_ValueNoCheck (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Fixed Asset Class.
		@param MFX_Clasa Fixed Asset Class	  */
	public void setMFX_Clasa (int MFX_Clasa)
	{
		set_Value (COLUMNNAME_MFX_Clasa, Integer.valueOf(MFX_Clasa));
	}

	/** Get Fixed Asset Class.
		@return Fixed Asset Class	  */
	public int getMFX_Clasa () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MFX_Clasa);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Fixed Asset Group.
		@param MFX_Grupa Fixed Asset Group	  */
	public void setMFX_Grupa (int MFX_Grupa)
	{
		set_Value (COLUMNNAME_MFX_Grupa, Integer.valueOf(MFX_Grupa));
	}

	/** Get Fixed Asset Group.
		@return Fixed Asset Group	  */
	public int getMFX_Grupa () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MFX_Grupa);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Fixed Asset Subclass.
		@param MFX_SubClasa Fixed Asset Subclass	  */
	public void setMFX_SubClasa (int MFX_SubClasa)
	{
		set_Value (COLUMNNAME_MFX_SubClasa, Integer.valueOf(MFX_SubClasa));
	}

	/** Get Fixed Asset Subclass.
		@return Fixed Asset Subclass	  */
	public int getMFX_SubClasa () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MFX_SubClasa);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Fixed Asset Subgroup.
		@param MFX_SubGrupa Fixed Asset Subgroup	  */
	public void setMFX_SubGrupa (int MFX_SubGrupa)
	{
		set_Value (COLUMNNAME_MFX_SubGrupa, Integer.valueOf(MFX_SubGrupa));
	}

	/** Get Fixed Asset Subgroup.
		@return Fixed Asset Subgroup	  */
	public int getMFX_SubGrupa () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MFX_SubGrupa);
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

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}