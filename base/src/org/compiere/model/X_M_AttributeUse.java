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
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for M_AttributeUse
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2 - $Id$ */
public class X_M_AttributeUse extends PO implements I_M_AttributeUse, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191120L;

    /** Standard Constructor */
    public X_M_AttributeUse (Properties ctx, int M_AttributeUse_ID, String trxName)
    {
      super (ctx, M_AttributeUse_ID, trxName);
      /** if (M_AttributeUse_ID == 0)
        {
			setM_AttributeSet_ID (0);
			setM_Attribute_ID (0);
			setSeqNo (0);
// @SQL=SELECT NVL(MAX(SeqNo),0)+10 AS DefaultValue FROM M_AttributeUse WHERE M_AttributeSet_ID=@M_AttributeSet_ID@
        } */
    }

    /** Load Constructor */
    public X_M_AttributeUse (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_M_AttributeUse[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_M_AttributeSet getM_AttributeSet() throws RuntimeException
    {
		return (org.compiere.model.I_M_AttributeSet)MTable.get(getCtx(), org.compiere.model.I_M_AttributeSet.Table_Name)
			.getPO(getM_AttributeSet_ID(), get_TrxName());	}

	/** Set Attribute Set.
		@param M_AttributeSet_ID 
		Product Attribute Set
	  */
	public void setM_AttributeSet_ID (int M_AttributeSet_ID)
	{
		if (M_AttributeSet_ID < 0) 
			set_ValueNoCheck (COLUMNNAME_M_AttributeSet_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_AttributeSet_ID, Integer.valueOf(M_AttributeSet_ID));
	}

	/** Get Attribute Set.
		@return Product Attribute Set
	  */
	public int getM_AttributeSet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_AttributeSet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getM_AttributeSet_ID()));
    }

	public org.compiere.model.I_M_Attribute getM_Attribute() throws RuntimeException
    {
		return (org.compiere.model.I_M_Attribute)MTable.get(getCtx(), org.compiere.model.I_M_Attribute.Table_Name)
			.getPO(getM_Attribute_ID(), get_TrxName());	}

	/** Set Attribute.
		@param M_Attribute_ID 
		Product Attribute
	  */
	public void setM_Attribute_ID (int M_Attribute_ID)
	{
		if (M_Attribute_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Attribute_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Attribute_ID, Integer.valueOf(M_Attribute_ID));
	}

	/** Get Attribute.
		@return Product Attribute
	  */
	public int getM_Attribute_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Attribute_ID);
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