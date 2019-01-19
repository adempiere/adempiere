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

/** Generated Model for DD_TransportUnitType
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_DD_TransportUnitType extends PO implements I_DD_TransportUnitType, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_DD_TransportUnitType (Properties ctx, int DD_TransportUnitType_ID, String trxName)
    {
      super (ctx, DD_TransportUnitType_ID, trxName);
      /** if (DD_TransportUnitType_ID == 0)
        {
			setDD_TransportUnitType_ID (0);
			setDimension_UOM_ID (0);
			setName (null);
			setTransportMode (null);
			setTransportPhysicalType (null);
			setVolume_UOM_ID (0);
			setWeight_UOM_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DD_TransportUnitType (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_DD_TransportUnitType[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Transport Unit Type.
		@param DD_TransportUnitType_ID Transport Unit Type	  */
	public void setDD_TransportUnitType_ID (int DD_TransportUnitType_ID)
	{
		if (DD_TransportUnitType_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DD_TransportUnitType_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DD_TransportUnitType_ID, Integer.valueOf(DD_TransportUnitType_ID));
	}

	/** Get Transport Unit Type.
		@return Transport Unit Type	  */
	public int getDD_TransportUnitType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_TransportUnitType_ID);
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

	public org.compiere.model.I_C_UOM getDimension_UOM() throws RuntimeException
    {
		return (org.compiere.model.I_C_UOM)MTable.get(getCtx(), org.compiere.model.I_C_UOM.Table_Name)
			.getPO(getDimension_UOM_ID(), get_TrxName());	}

	/** Set Dimension Unit of Measure.
		@param Dimension_UOM_ID Dimension Unit of Measure	  */
	public void setDimension_UOM_ID (int Dimension_UOM_ID)
	{
		if (Dimension_UOM_ID < 1) 
			set_Value (COLUMNNAME_Dimension_UOM_ID, null);
		else 
			set_Value (COLUMNNAME_Dimension_UOM_ID, Integer.valueOf(Dimension_UOM_ID));
	}

	/** Get Dimension Unit of Measure.
		@return Dimension Unit of Measure	  */
	public int getDimension_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Dimension_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Has Vapor Recovery System.
		@param HasVaporRecoverySystem 
		Indicates the existence of a vapor recovery system
	  */
	public void setHasVaporRecoverySystem (boolean HasVaporRecoverySystem)
	{
		set_Value (COLUMNNAME_HasVaporRecoverySystem, Boolean.valueOf(HasVaporRecoverySystem));
	}

	/** Get Has Vapor Recovery System.
		@return Indicates the existence of a vapor recovery system
	  */
	public boolean isHasVaporRecoverySystem () 
	{
		Object oo = get_Value(COLUMNNAME_HasVaporRecoverySystem);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** TransportMode AD_Reference_ID=53901 */
	public static final int TRANSPORTMODE_AD_Reference_ID=53901;
	/** Barge = BRG */
	public static final String TRANSPORTMODE_Barge = "BRG";
	/** Marine = MRN */
	public static final String TRANSPORTMODE_Marine = "MRN";
	/** Pipeline = PPL */
	public static final String TRANSPORTMODE_Pipeline = "PPL";
	/** Rail = RAL */
	public static final String TRANSPORTMODE_Rail = "RAL";
	/** Road = ROD */
	public static final String TRANSPORTMODE_Road = "ROD";
	/** Set Transport Mode.
		@param TransportMode 
		The modes of transport supported
	  */
	public void setTransportMode (String TransportMode)
	{

		set_Value (COLUMNNAME_TransportMode, TransportMode);
	}

	/** Get Transport Mode.
		@return The modes of transport supported
	  */
	public String getTransportMode () 
	{
		return (String)get_Value(COLUMNNAME_TransportMode);
	}

	/** TransportPhysicalType AD_Reference_ID=53902 */
	public static final int TRANSPORTPHYSICALTYPE_AD_Reference_ID=53902;
	/** Other weight-bearing units = OWB */
	public static final String TRANSPORTPHYSICALTYPE_OtherWeight_BearingUnits = "OWB";
	/** Prime mover = PRM */
	public static final String TRANSPORTPHYSICALTYPE_PrimeMover = "PRM";
	/** Single units with engine and loading capacity = SUE */
	public static final String TRANSPORTPHYSICALTYPE_SingleUnitsWithEngineAndLoadingCapacity = "SUE";
	/** Trailer = TRL */
	public static final String TRANSPORTPHYSICALTYPE_Trailer = "TRL";
	/** Set Transport Physical Type.
		@param TransportPhysicalType 
		The system divides the physical type of the transport unit into a part that carries the load, a mover, and other load bearing elements
	  */
	public void setTransportPhysicalType (String TransportPhysicalType)
	{

		set_Value (COLUMNNAME_TransportPhysicalType, TransportPhysicalType);
	}

	/** Get Transport Physical Type.
		@return The system divides the physical type of the transport unit into a part that carries the load, a mover, and other load bearing elements
	  */
	public String getTransportPhysicalType () 
	{
		return (String)get_Value(COLUMNNAME_TransportPhysicalType);
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

	public org.compiere.model.I_C_UOM getVolume_UOM() throws RuntimeException
    {
		return (org.compiere.model.I_C_UOM)MTable.get(getCtx(), org.compiere.model.I_C_UOM.Table_Name)
			.getPO(getVolume_UOM_ID(), get_TrxName());	}

	/** Set Volume Unit of Measure.
		@param Volume_UOM_ID Volume Unit of Measure	  */
	public void setVolume_UOM_ID (int Volume_UOM_ID)
	{
		if (Volume_UOM_ID < 1) 
			set_Value (COLUMNNAME_Volume_UOM_ID, null);
		else 
			set_Value (COLUMNNAME_Volume_UOM_ID, Integer.valueOf(Volume_UOM_ID));
	}

	/** Get Volume Unit of Measure.
		@return Volume Unit of Measure	  */
	public int getVolume_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Volume_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_UOM getWeight_UOM() throws RuntimeException
    {
		return (org.compiere.model.I_C_UOM)MTable.get(getCtx(), org.compiere.model.I_C_UOM.Table_Name)
			.getPO(getWeight_UOM_ID(), get_TrxName());	}

	/** Set Weight Unit of Measure.
		@param Weight_UOM_ID Weight Unit of Measure	  */
	public void setWeight_UOM_ID (int Weight_UOM_ID)
	{
		if (Weight_UOM_ID < 1) 
			set_Value (COLUMNNAME_Weight_UOM_ID, null);
		else 
			set_Value (COLUMNNAME_Weight_UOM_ID, Integer.valueOf(Weight_UOM_ID));
	}

	/** Get Weight Unit of Measure.
		@return Weight Unit of Measure	  */
	public int getWeight_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Weight_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}