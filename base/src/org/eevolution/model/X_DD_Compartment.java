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

/** Generated Model for DD_Compartment
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_DD_Compartment extends PO implements I_DD_Compartment, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_DD_Compartment (Properties ctx, int DD_Compartment_ID, String trxName)
    {
      super (ctx, DD_Compartment_ID, trxName);
      /** if (DD_Compartment_ID == 0)
        {
			setDD_Compartment_ID (0);
			setDD_CompatibilityGroup_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_DD_Compartment (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DD_Compartment[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Image.
		@param AD_Image_ID 
		Image or Icon
	  */
	public void setAD_Image_ID (int AD_Image_ID)
	{
		if (AD_Image_ID < 1) 
			set_Value (COLUMNNAME_AD_Image_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Image_ID, Integer.valueOf(AD_Image_ID));
	}

	/** Get Image.
		@return Image or Icon
	  */
	public int getAD_Image_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Image_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_A_Asset getA_Asset() throws RuntimeException
    {
		return (org.compiere.model.I_A_Asset)MTable.get(getCtx(), org.compiere.model.I_A_Asset.Table_Name)
			.getPO(getA_Asset_ID(), get_TrxName());	}

	/** Set Fixed Asset.
		@param A_Asset_ID 
		Fixed Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID)
	{
		if (A_Asset_ID < 1) 
			set_Value (COLUMNNAME_A_Asset_ID, null);
		else 
			set_Value (COLUMNNAME_A_Asset_ID, Integer.valueOf(A_Asset_ID));
	}

	/** Get Fixed Asset.
		@return Fixed Asset used internally or by customers
	  */
	public int getA_Asset_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Transport Compartment.
		@param DD_Compartment_ID Transport Compartment	  */
	public void setDD_Compartment_ID (int DD_Compartment_ID)
	{
		if (DD_Compartment_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DD_Compartment_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DD_Compartment_ID, Integer.valueOf(DD_Compartment_ID));
	}

	/** Get Transport Compartment.
		@return Transport Compartment	  */
	public int getDD_Compartment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_Compartment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_DD_CompatibilityGroup getDD_CompatibilityGroup() throws RuntimeException
    {
		return (org.eevolution.model.I_DD_CompatibilityGroup)MTable.get(getCtx(), org.eevolution.model.I_DD_CompatibilityGroup.Table_Name)
			.getPO(getDD_CompatibilityGroup_ID(), get_TrxName());	}

	/** Set Transport Compatibility Group.
		@param DD_CompatibilityGroup_ID Transport Compatibility Group	  */
	public void setDD_CompatibilityGroup_ID (int DD_CompatibilityGroup_ID)
	{
		if (DD_CompatibilityGroup_ID < 1) 
			set_Value (COLUMNNAME_DD_CompatibilityGroup_ID, null);
		else 
			set_Value (COLUMNNAME_DD_CompatibilityGroup_ID, Integer.valueOf(DD_CompatibilityGroup_ID));
	}

	/** Get Transport Compatibility Group.
		@return Transport Compatibility Group	  */
	public int getDD_CompatibilityGroup_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_CompatibilityGroup_ID);
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

	/** Set Document Note.
		@param DocumentNote 
		Additional information for a Document
	  */
	public void setDocumentNote (String DocumentNote)
	{
		set_Value (COLUMNNAME_DocumentNote, DocumentNote);
	}

	/** Get Document Note.
		@return Additional information for a Document
	  */
	public String getDocumentNote () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNote);
	}

	/** Set Comment/Help.
		@param Help 
		Comment or Hint
	  */
	public void setHelp (String Help)
	{
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp () 
	{
		return (String)get_Value(COLUMNNAME_Help);
	}

	/** Set Maximum Volume.
		@param MaximumVolume Maximum Volume	  */
	public void setMaximumVolume (BigDecimal MaximumVolume)
	{
		set_Value (COLUMNNAME_MaximumVolume, MaximumVolume);
	}

	/** Get Maximum Volume.
		@return Maximum Volume	  */
	public BigDecimal getMaximumVolume () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MaximumVolume);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Minimum Volume.
		@param MinimumVolume Minimum Volume	  */
	public void setMinimumVolume (BigDecimal MinimumVolume)
	{
		set_Value (COLUMNNAME_MinimumVolume, MinimumVolume);
	}

	/** Get Minimum Volume.
		@return Minimum Volume	  */
	public BigDecimal getMinimumVolume () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MinimumVolume);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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
}