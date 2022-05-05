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
package org.spin.store.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for W_CategoryAllocation
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3 - $Id$ */
public class X_W_CategoryAllocation extends PO implements I_W_CategoryAllocation, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20201102L;

    /** Standard Constructor */
    public X_W_CategoryAllocation (Properties ctx, int W_CategoryAllocation_ID, String trxName)
    {
      super (ctx, W_CategoryAllocation_ID, trxName);
      /** if (W_CategoryAllocation_ID == 0)
        {
			setM_Product_ID (0);
			setW_CategoryAllocation_ID (0);
			setW_Category_ID (0);
        } */
    }

    /** Load Constructor */
    public X_W_CategoryAllocation (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_W_CategoryAllocation[")
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

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Product_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
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

	/** Set Product Group Allocation ID.
		@param W_CategoryAllocation_ID Product Group Allocation ID	  */
	public void setW_CategoryAllocation_ID (int W_CategoryAllocation_ID)
	{
		if (W_CategoryAllocation_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_W_CategoryAllocation_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_W_CategoryAllocation_ID, Integer.valueOf(W_CategoryAllocation_ID));
	}

	/** Get Product Group Allocation ID.
		@return Product Group Allocation ID	  */
	public int getW_CategoryAllocation_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_W_CategoryAllocation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.spin.store.model.I_W_Category getW_Category() throws RuntimeException
    {
		return (org.spin.store.model.I_W_Category)MTable.get(getCtx(), org.spin.store.model.I_W_Category.Table_Name)
			.getPO(getW_Category_ID(), get_TrxName());	}

	/** Set Product Store Group.
		@param W_Category_ID Product Store Group	  */
	public void setW_Category_ID (int W_Category_ID)
	{
		if (W_Category_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_W_Category_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_W_Category_ID, Integer.valueOf(W_Category_ID));
	}

	/** Get Product Store Group.
		@return Product Store Group	  */
	public int getW_Category_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_W_Category_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}