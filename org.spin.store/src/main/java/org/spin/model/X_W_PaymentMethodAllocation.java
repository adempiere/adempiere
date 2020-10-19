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
package org.spin.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for W_PaymentMethodAllocation
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3 - $Id$ */
public class X_W_PaymentMethodAllocation extends PO implements I_W_PaymentMethodAllocation, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20201019L;

    /** Standard Constructor */
    public X_W_PaymentMethodAllocation (Properties ctx, int W_PaymentMethodAllocation_ID, String trxName)
    {
      super (ctx, W_PaymentMethodAllocation_ID, trxName);
      /** if (W_PaymentMethodAllocation_ID == 0)
        {
			setW_PaymentMethodAllocation_ID (0);
			setW_PaymentMethod_ID (0);
			setW_Store_ID (0);
        } */
    }

    /** Load Constructor */
    public X_W_PaymentMethodAllocation (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_W_PaymentMethodAllocation[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Note.
		@param Note 
		Optional additional user defined information
	  */
	public void setNote (String Note)
	{
		set_Value (COLUMNNAME_Note, Note);
	}

	/** Get Note.
		@return Optional additional user defined information
	  */
	public String getNote () 
	{
		return (String)get_Value(COLUMNNAME_Note);
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

	/** Set Store Payment Method.
		@param W_PaymentMethodAllocation_ID Store Payment Method	  */
	public void setW_PaymentMethodAllocation_ID (int W_PaymentMethodAllocation_ID)
	{
		if (W_PaymentMethodAllocation_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_W_PaymentMethodAllocation_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_W_PaymentMethodAllocation_ID, Integer.valueOf(W_PaymentMethodAllocation_ID));
	}

	/** Get Store Payment Method.
		@return Store Payment Method	  */
	public int getW_PaymentMethodAllocation_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_W_PaymentMethodAllocation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.spin.model.I_W_PaymentMethod getW_PaymentMethod() throws RuntimeException
    {
		return (org.spin.model.I_W_PaymentMethod)MTable.get(getCtx(), org.spin.model.I_W_PaymentMethod.Table_Name)
			.getPO(getW_PaymentMethod_ID(), get_TrxName());	}

	/** Set Store Payment Method.
		@param W_PaymentMethod_ID 
		Payment Methods allowed for Store
	  */
	public void setW_PaymentMethod_ID (int W_PaymentMethod_ID)
	{
		if (W_PaymentMethod_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_W_PaymentMethod_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_W_PaymentMethod_ID, Integer.valueOf(W_PaymentMethod_ID));
	}

	/** Get Store Payment Method.
		@return Payment Methods allowed for Store
	  */
	public int getW_PaymentMethod_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_W_PaymentMethod_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_W_Store getW_Store() throws RuntimeException
    {
		return (org.compiere.model.I_W_Store)MTable.get(getCtx(), org.compiere.model.I_W_Store.Table_Name)
			.getPO(getW_Store_ID(), get_TrxName());	}

	/** Set Web Store.
		@param W_Store_ID 
		A Web Store of the Client
	  */
	public void setW_Store_ID (int W_Store_ID)
	{
		if (W_Store_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_W_Store_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_W_Store_ID, Integer.valueOf(W_Store_ID));
	}

	/** Get Web Store.
		@return A Web Store of the Client
	  */
	public int getW_Store_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_W_Store_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}