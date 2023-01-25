/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.                                     *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net                                                  *
 * or https://github.com/adempiere/adempiere/blob/develop/license.html        *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.adempiere.core.domains.models;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;

/** Generated Model for C_PaymentMethodAllocation
 *  @author Adempiere (generated) 
 *  @version Release 3.9.4 - $Id$ */
public class X_C_PaymentMethodAllocation extends PO implements I_C_PaymentMethodAllocation, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20230102L;

    /** Standard Constructor */
    public X_C_PaymentMethodAllocation (Properties ctx, int C_PaymentMethodAllocation_ID, String trxName)
    {
      super (ctx, C_PaymentMethodAllocation_ID, trxName);
      /** if (C_PaymentMethodAllocation_ID == 0)
        {
			setC_PaymentMethod_ID (0);
			setC_PaymentMethodAllocation_ID (0);
			setW_Store_ID (0);
        } */
    }

    /** Load Constructor */
    public X_C_PaymentMethodAllocation (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_PaymentMethodAllocation[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.adempiere.core.domains.models.I_C_PaymentMethod getC_PaymentMethod() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_C_PaymentMethod)MTable.get(getCtx(), org.adempiere.core.domains.models.I_C_PaymentMethod.Table_Name)
			.getPO(getC_PaymentMethod_ID(), get_TrxName());	}

	/** Set Store Payment Method.
		@param C_PaymentMethod_ID 
		Payment Methods allowed for Store
	  */
	public void setC_PaymentMethod_ID (int C_PaymentMethod_ID)
	{
		if (C_PaymentMethod_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_PaymentMethod_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_PaymentMethod_ID, Integer.valueOf(C_PaymentMethod_ID));
	}

	/** Get Store Payment Method.
		@return Payment Methods allowed for Store
	  */
	public int getC_PaymentMethod_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_PaymentMethod_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Store Payment Method.
		@param C_PaymentMethodAllocation_ID Store Payment Method	  */
	public void setC_PaymentMethodAllocation_ID (int C_PaymentMethodAllocation_ID)
	{
		if (C_PaymentMethodAllocation_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_PaymentMethodAllocation_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_PaymentMethodAllocation_ID, Integer.valueOf(C_PaymentMethodAllocation_ID));
	}

	/** Get Store Payment Method.
		@return Store Payment Method	  */
	public int getC_PaymentMethodAllocation_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_PaymentMethodAllocation_ID);
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

	public org.adempiere.core.domains.models.I_W_Store getW_Store() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_W_Store)MTable.get(getCtx(), org.adempiere.core.domains.models.I_W_Store.Table_Name)
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