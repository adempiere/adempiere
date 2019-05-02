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

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for DD_VehicleType
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2 - $Id$ */
public class X_DD_VehicleType extends PO implements I_DD_VehicleType, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190501L;

    /** Standard Constructor */
    public X_DD_VehicleType (Properties ctx, int DD_VehicleType_ID, String trxName)
    {
      super (ctx, DD_VehicleType_ID, trxName);
      /** if (DD_VehicleType_ID == 0)
        {
			setDD_VehicleType_ID (0);
			setName (null);
			setVehicleType (null);
        } */
    }

    /** Load Constructor */
    public X_DD_VehicleType (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DD_VehicleType[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Vehicle Type.
		@param DD_VehicleType_ID Vehicle Type	  */
	public void setDD_VehicleType_ID (int DD_VehicleType_ID)
	{
		if (DD_VehicleType_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DD_VehicleType_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DD_VehicleType_ID, Integer.valueOf(DD_VehicleType_ID));
	}

	/** Get Vehicle Type.
		@return Vehicle Type	  */
	public int getDD_VehicleType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_VehicleType_ID);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getValue());
    }

	/** VehicleType AD_Reference_ID=53905 */
	public static final int VEHICLETYPE_AD_Reference_ID=53905;
	/** Barges = BRG */
	public static final String VEHICLETYPE_Barges = "BRG";
	/** Pipelines = PPL */
	public static final String VEHICLETYPE_Pipelines = "PPL";
	/** Ships = SHP */
	public static final String VEHICLETYPE_Ships = "SHP";
	/** Trucks = TRC */
	public static final String VEHICLETYPE_Trucks = "TRC";
	/** Trains = TRN */
	public static final String VEHICLETYPE_Trains = "TRN";
	/** Set Vehicle Type.
		@param VehicleType 
		Vehicle Type defines the mode of transport
	  */
	public void setVehicleType (String VehicleType)
	{

		set_Value (COLUMNNAME_VehicleType, VehicleType);
	}

	/** Get Vehicle Type.
		@return Vehicle Type defines the mode of transport
	  */
	public String getVehicleType () 
	{
		return (String)get_Value(COLUMNNAME_VehicleType);
	}
}