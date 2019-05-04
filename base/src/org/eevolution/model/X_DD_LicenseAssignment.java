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

/** Generated Model for DD_LicenseAssignment
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2 - $Id$ */
public class X_DD_LicenseAssignment extends PO implements I_DD_LicenseAssignment, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190501L;

    /** Standard Constructor */
    public X_DD_LicenseAssignment (Properties ctx, int DD_LicenseAssignment_ID, String trxName)
    {
      super (ctx, DD_LicenseAssignment_ID, trxName);
      /** if (DD_LicenseAssignment_ID == 0)
        {
			setDD_LicenseAssignment_ID (0);
			setDD_License_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DD_LicenseAssignment (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DD_LicenseAssignment[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.eevolution.model.I_DD_Driver getDD_Driver() throws RuntimeException
    {
		return (org.eevolution.model.I_DD_Driver)MTable.get(getCtx(), org.eevolution.model.I_DD_Driver.Table_Name)
			.getPO(getDD_Driver_ID(), get_TrxName());	}

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

	/** Set License Types Assignment.
		@param DD_LicenseAssignment_ID License Types Assignment	  */
	public void setDD_LicenseAssignment_ID (int DD_LicenseAssignment_ID)
	{
		if (DD_LicenseAssignment_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DD_LicenseAssignment_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DD_LicenseAssignment_ID, Integer.valueOf(DD_LicenseAssignment_ID));
	}

	/** Get License Types Assignment.
		@return License Types Assignment	  */
	public int getDD_LicenseAssignment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_LicenseAssignment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_DD_License getDD_License() throws RuntimeException
    {
		return (org.eevolution.model.I_DD_License)MTable.get(getCtx(), org.eevolution.model.I_DD_License.Table_Name)
			.getPO(getDD_License_ID(), get_TrxName());	}

	/** Set License.
		@param DD_License_ID License	  */
	public void setDD_License_ID (int DD_License_ID)
	{
		if (DD_License_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DD_License_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DD_License_ID, Integer.valueOf(DD_License_ID));
	}

	/** Get License.
		@return License	  */
	public int getDD_License_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_License_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_DD_Vehicle getDD_Vehicle() throws RuntimeException
    {
		return (org.eevolution.model.I_DD_Vehicle)MTable.get(getCtx(), org.eevolution.model.I_DD_Vehicle.Table_Name)
			.getPO(getDD_Vehicle_ID(), get_TrxName());	}

	/** Set Vehicle.
		@param DD_Vehicle_ID Vehicle	  */
	public void setDD_Vehicle_ID (int DD_Vehicle_ID)
	{
		if (DD_Vehicle_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DD_Vehicle_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DD_Vehicle_ID, Integer.valueOf(DD_Vehicle_ID));
	}

	/** Get Vehicle.
		@return Vehicle	  */
	public int getDD_Vehicle_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_Vehicle_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sequence.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getSeqNo()));
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