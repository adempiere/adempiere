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

/** Generated Model for DD_TransportUnit
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_DD_TransportUnit extends PO implements I_DD_TransportUnit, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_DD_TransportUnit (Properties ctx, int DD_TransportUnit_ID, String trxName)
    {
      super (ctx, DD_TransportUnit_ID, trxName);
      /** if (DD_TransportUnit_ID == 0)
        {
			setDD_TransportUnitType_ID (0);
			setDD_TransportUnit_ID (0);
			setDimension_UOM_ID (0);
			setName (null);
			setTransportStatus (null);
			setVolume_UOM_ID (0);
			setWeight_UOM_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DD_TransportUnit (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DD_TransportUnit[")
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

	/** Set Number of axles.
		@param AxlesNumber Number of axles	  */
	public void setAxlesNumber (BigDecimal AxlesNumber)
	{
		set_Value (COLUMNNAME_AxlesNumber, AxlesNumber);
	}

	/** Get Number of axles.
		@return Number of axles	  */
	public BigDecimal getAxlesNumber () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AxlesNumber);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Carrier Number.
		@param CarrierNumber Carrier Number	  */
	public void setCarrierNumber (String CarrierNumber)
	{
		set_Value (COLUMNNAME_CarrierNumber, CarrierNumber);
	}

	/** Get Carrier Number.
		@return Carrier Number	  */
	public String getCarrierNumber () 
	{
		return (String)get_Value(COLUMNNAME_CarrierNumber);
	}

	/** Set Copy From.
		@param CopyFrom 
		Copy From Record
	  */
	public void setCopyFrom (String CopyFrom)
	{
		set_Value (COLUMNNAME_CopyFrom, CopyFrom);
	}

	/** Get Copy From.
		@return Copy From Record
	  */
	public String getCopyFrom () 
	{
		return (String)get_Value(COLUMNNAME_CopyFrom);
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

	public org.eevolution.model.I_DD_TransportUnitType getDD_TransportUnitType() throws RuntimeException
    {
		return (org.eevolution.model.I_DD_TransportUnitType)MTable.get(getCtx(), org.eevolution.model.I_DD_TransportUnitType.Table_Name)
			.getPO(getDD_TransportUnitType_ID(), get_TrxName());	}

	/** Set Transport Unit Type.
		@param DD_TransportUnitType_ID Transport Unit Type	  */
	public void setDD_TransportUnitType_ID (int DD_TransportUnitType_ID)
	{
		if (DD_TransportUnitType_ID < 1) 
			set_Value (COLUMNNAME_DD_TransportUnitType_ID, null);
		else 
			set_Value (COLUMNNAME_DD_TransportUnitType_ID, Integer.valueOf(DD_TransportUnitType_ID));
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

	/** Set Transport Unit.
		@param DD_TransportUnit_ID Transport Unit	  */
	public void setDD_TransportUnit_ID (int DD_TransportUnit_ID)
	{
		if (DD_TransportUnit_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DD_TransportUnit_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DD_TransportUnit_ID, Integer.valueOf(DD_TransportUnit_ID));
	}

	/** Get Transport Unit.
		@return Transport Unit	  */
	public int getDD_TransportUnit_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_TransportUnit_ID);
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

	/** Set Maximum Weight.
		@param MaximumWeight Maximum Weight	  */
	public void setMaximumWeight (BigDecimal MaximumWeight)
	{
		set_Value (COLUMNNAME_MaximumWeight, MaximumWeight);
	}

	/** Get Maximum Weight.
		@return Maximum Weight	  */
	public BigDecimal getMaximumWeight () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MaximumWeight);
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

	/** Set Minimum Weight.
		@param MinimumWeight 
		Minimum Weight of a product
	  */
	public void setMinimumWeight (BigDecimal MinimumWeight)
	{
		set_Value (COLUMNNAME_MinimumWeight, MinimumWeight);
	}

	/** Get Minimum Weight.
		@return Minimum Weight of a product
	  */
	public BigDecimal getMinimumWeight () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MinimumWeight);
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

	/** Set Transport Height.
		@param TransportHeight Transport Height	  */
	public void setTransportHeight (BigDecimal TransportHeight)
	{
		set_Value (COLUMNNAME_TransportHeight, TransportHeight);
	}

	/** Get Transport Height.
		@return Transport Height	  */
	public BigDecimal getTransportHeight () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TransportHeight);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Transport Length.
		@param TransportLength Transport Length	  */
	public void setTransportLength (BigDecimal TransportLength)
	{
		set_Value (COLUMNNAME_TransportLength, TransportLength);
	}

	/** Get Transport Length.
		@return Transport Length	  */
	public BigDecimal getTransportLength () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TransportLength);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** TransportStatus AD_Reference_ID=53903 */
	public static final int TRANSPORTSTATUS_AD_Reference_ID=53903;
	/** Available = AVL */
	public static final String TRANSPORTSTATUS_Available = "AVL";
	/** In Disuse = IDS */
	public static final String TRANSPORTSTATUS_InDisuse = "IDS";
	/** Not Available = NAV */
	public static final String TRANSPORTSTATUS_NotAvailable = "NAV";
	/** Set Transport Status.
		@param TransportStatus 
		Transport unit status
	  */
	public void setTransportStatus (String TransportStatus)
	{

		set_Value (COLUMNNAME_TransportStatus, TransportStatus);
	}

	/** Get Transport Status.
		@return Transport unit status
	  */
	public String getTransportStatus () 
	{
		return (String)get_Value(COLUMNNAME_TransportStatus);
	}

	/** Set Transport Width.
		@param TransportWidth Transport Width	  */
	public void setTransportWidth (BigDecimal TransportWidth)
	{
		set_Value (COLUMNNAME_TransportWidth, TransportWidth);
	}

	/** Get Transport Width.
		@return Transport Width	  */
	public BigDecimal getTransportWidth () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TransportWidth);
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

	/** Set Unladen Weight.
		@param UnladenWeight Unladen Weight	  */
	public void setUnladenWeight (BigDecimal UnladenWeight)
	{
		set_Value (COLUMNNAME_UnladenWeight, UnladenWeight);
	}

	/** Get Unladen Weight.
		@return Unladen Weight	  */
	public BigDecimal getUnladenWeight () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_UnladenWeight);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Volume.
		@param Volume 
		Volume of a product
	  */
	public void setVolume (BigDecimal Volume)
	{
		set_Value (COLUMNNAME_Volume, Volume);
	}

	/** Get Volume.
		@return Volume of a product
	  */
	public BigDecimal getVolume () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Volume);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Weight.
		@param Weight 
		Weight of a product
	  */
	public void setWeight (BigDecimal Weight)
	{
		set_Value (COLUMNNAME_Weight, Weight);
	}

	/** Get Weight.
		@return Weight of a product
	  */
	public BigDecimal getWeight () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Weight);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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