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

/** Generated Model for DD_CompartmentAssignment
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_DD_CompartmentAssignment extends PO implements I_DD_CompartmentAssignment, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_DD_CompartmentAssignment (Properties ctx, int DD_CompartmentAssignment_ID, String trxName)
    {
      super (ctx, DD_CompartmentAssignment_ID, trxName);
      /** if (DD_CompartmentAssignment_ID == 0)
        {
			setDD_CompartmentAssignment_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DD_CompartmentAssignment (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DD_CompartmentAssignment[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Compartment Assignment  ID.
		@param DD_CompartmentAssignment_ID Compartment Assignment  ID	  */
	public void setDD_CompartmentAssignment_ID (int DD_CompartmentAssignment_ID)
	{
		if (DD_CompartmentAssignment_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DD_CompartmentAssignment_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DD_CompartmentAssignment_ID, Integer.valueOf(DD_CompartmentAssignment_ID));
	}

	/** Get Compartment Assignment  ID.
		@return Compartment Assignment  ID	  */
	public int getDD_CompartmentAssignment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DD_CompartmentAssignment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_DD_Compartment getDD_Compartment() throws RuntimeException
    {
		return (org.eevolution.model.I_DD_Compartment)MTable.get(getCtx(), org.eevolution.model.I_DD_Compartment.Table_Name)
			.getPO(getDD_Compartment_ID(), get_TrxName());	}

	/** Set Transport Compartment.
		@param DD_Compartment_ID Transport Compartment	  */
	public void setDD_Compartment_ID (int DD_Compartment_ID)
	{
		if (DD_Compartment_ID < 1) 
			set_Value (COLUMNNAME_DD_Compartment_ID, null);
		else 
			set_Value (COLUMNNAME_DD_Compartment_ID, Integer.valueOf(DD_Compartment_ID));
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getDD_Compartment_ID()));
    }

	public org.eevolution.model.I_DD_TransportUnit getDD_TransportUnit() throws RuntimeException
    {
		return (org.eevolution.model.I_DD_TransportUnit)MTable.get(getCtx(), org.eevolution.model.I_DD_TransportUnit.Table_Name)
			.getPO(getDD_TransportUnit_ID(), get_TrxName());	}

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