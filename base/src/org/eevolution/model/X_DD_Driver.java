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

/** Generated Model for DD_Driver
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_DD_Driver extends PO implements I_DD_Driver, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_DD_Driver (Properties ctx, int DD_Driver_ID, String trxName)
    {
      super (ctx, DD_Driver_ID, trxName);
      /** if (DD_Driver_ID == 0)
        {
			setDD_Driver_ID (0);
			setDriverStatus (null);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_DD_Driver (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DD_Driver[")
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

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
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

	/** Set Create lines from.
		@param CreateFrom 
		Process which will generate a new document lines based on an existing document
	  */
	public void setCreateFrom (String CreateFrom)
	{
		set_Value (COLUMNNAME_CreateFrom, CreateFrom);
	}

	/** Get Create lines from.
		@return Process which will generate a new document lines based on an existing document
	  */
	public String getCreateFrom () 
	{
		return (String)get_Value(COLUMNNAME_CreateFrom);
	}

	/** Set Driver.
		@param DD_Driver_ID Driver	  */
	public void setDD_Driver_ID (int DD_Driver_ID)
	{
		if (DD_Driver_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DD_Driver_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DD_Driver_ID, Integer.valueOf(DD_Driver_ID));
	}

	/** Get Driver.
		@return Driver	  */
	public int getDD_Driver_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_Driver_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_DD_LicenseAssignment getDD_LicenseAssignment() throws RuntimeException
    {
		return (org.eevolution.model.I_DD_LicenseAssignment)MTable.get(getCtx(), org.eevolution.model.I_DD_LicenseAssignment.Table_Name)
			.getPO(getDD_LicenseAssignment_ID(), get_TrxName());	}

	/** Set License Types Assignment.
		@param DD_LicenseAssignment_ID License Types Assignment	  */
	public void setDD_LicenseAssignment_ID (int DD_LicenseAssignment_ID)
	{
		throw new IllegalArgumentException ("DD_LicenseAssignment_ID is virtual column");	}

	/** Get License Types Assignment.
		@return License Types Assignment	  */
	public int getDD_LicenseAssignment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_LicenseAssignment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_DD_RequirementAssignment getDD_RequirementAssignment() throws RuntimeException
    {
		return (org.eevolution.model.I_DD_RequirementAssignment)MTable.get(getCtx(), org.eevolution.model.I_DD_RequirementAssignment.Table_Name)
			.getPO(getDD_RequirementAssignment_ID(), get_TrxName());	}

	/** Set Transport Requirement Assignment.
		@param DD_RequirementAssignment_ID Transport Requirement Assignment	  */
	public void setDD_RequirementAssignment_ID (int DD_RequirementAssignment_ID)
	{
		throw new IllegalArgumentException ("DD_RequirementAssignment_ID is virtual column");	}

	/** Get Transport Requirement Assignment.
		@return Transport Requirement Assignment	  */
	public int getDD_RequirementAssignment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_RequirementAssignment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** DriverStatus AD_Reference_ID=53904 */
	public static final int DRIVERSTATUS_AD_Reference_ID=53904;
	/** Available = AVL */
	public static final String DRIVERSTATUS_Available = "AVL";
	/** Not available = NAV */
	public static final String DRIVERSTATUS_NotAvailable = "NAV";
	/** Set Driver Status.
		@param DriverStatus Driver Status	  */
	public void setDriverStatus (String DriverStatus)
	{

		set_Value (COLUMNNAME_DriverStatus, DriverStatus);
	}

	/** Get Driver Status.
		@return Driver Status	  */
	public String getDriverStatus () 
	{
		return (String)get_Value(COLUMNNAME_DriverStatus);
	}

	/** Set Generate To.
		@param GenerateTo 
		Generate To
	  */
	public void setGenerateTo (String GenerateTo)
	{
		set_Value (COLUMNNAME_GenerateTo, GenerateTo);
	}

	/** Get Generate To.
		@return Generate To
	  */
	public String getGenerateTo () 
	{
		return (String)get_Value(COLUMNNAME_GenerateTo);
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

	/** Set Name 2.
		@param Name2 
		Additional Name
	  */
	public void setName2 (String Name2)
	{
		set_Value (COLUMNNAME_Name2, Name2);
	}

	/** Get Name 2.
		@return Additional Name
	  */
	public String getName2 () 
	{
		return (String)get_Value(COLUMNNAME_Name2);
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
}