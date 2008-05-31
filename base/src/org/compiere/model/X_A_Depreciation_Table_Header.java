/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for A_Depreciation_Table_Header
 *  @author Adempiere (generated) 
 *  @version Release 3.5.1a - $Id$ */
public class X_A_Depreciation_Table_Header extends PO implements I_A_Depreciation_Table_Header, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_A_Depreciation_Table_Header (Properties ctx, int A_Depreciation_Table_Header_ID, String trxName)
    {
      super (ctx, A_Depreciation_Table_Header_ID, trxName);
      /** if (A_Depreciation_Table_Header_ID == 0)
        {
			setA_Depreciation_Table_Code (null);
			setA_Depreciation_Table_Header_ID (0);
			setA_Table_Rate_Type (null);
			setA_Term (null);
			setDescription (null);
			setProcessed (false);
        } */
    }

    /** Load Constructor */
    public X_A_Depreciation_Table_Header (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_A_Depreciation_Table_Header[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set A_Depreciation_Table_Code.
		@param A_Depreciation_Table_Code A_Depreciation_Table_Code	  */
	public void setA_Depreciation_Table_Code (String A_Depreciation_Table_Code)
	{
		if (A_Depreciation_Table_Code == null)
			throw new IllegalArgumentException ("A_Depreciation_Table_Code is mandatory.");

		if (A_Depreciation_Table_Code.length() > 20)
		{
			log.warning("Length > 20 - truncated");
			A_Depreciation_Table_Code = A_Depreciation_Table_Code.substring(0, 20);
		}
		set_Value (COLUMNNAME_A_Depreciation_Table_Code, A_Depreciation_Table_Code);
	}

	/** Get A_Depreciation_Table_Code.
		@return A_Depreciation_Table_Code	  */
	public String getA_Depreciation_Table_Code () 
	{
		return (String)get_Value(COLUMNNAME_A_Depreciation_Table_Code);
	}

	/** Set A_Depreciation_Table_Header_ID.
		@param A_Depreciation_Table_Header_ID A_Depreciation_Table_Header_ID	  */
	public void setA_Depreciation_Table_Header_ID (int A_Depreciation_Table_Header_ID)
	{
		if (A_Depreciation_Table_Header_ID < 1)
			 throw new IllegalArgumentException ("A_Depreciation_Table_Header_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_A_Depreciation_Table_Header_ID, Integer.valueOf(A_Depreciation_Table_Header_ID));
	}

	/** Get A_Depreciation_Table_Header_ID.
		@return A_Depreciation_Table_Header_ID	  */
	public int getA_Depreciation_Table_Header_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Depreciation_Table_Header_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getA_Depreciation_Table_Header_ID()));
    }

	/** A_Table_Rate_Type AD_Reference_ID=53255 */
	public static final int A_TABLE_RATE_TYPE_AD_Reference_ID=53255;
	/** Amount = AM */
	public static final String A_TABLE_RATE_TYPE_Amount = "AM";
	/** Rate = RT */
	public static final String A_TABLE_RATE_TYPE_Rate = "RT";
	/** Set A_Table_Rate_Type.
		@param A_Table_Rate_Type A_Table_Rate_Type	  */
	public void setA_Table_Rate_Type (String A_Table_Rate_Type)
	{
		if (A_Table_Rate_Type == null) throw new IllegalArgumentException ("A_Table_Rate_Type is mandatory");
		if (A_Table_Rate_Type.equals("AM") || A_Table_Rate_Type.equals("RT")); else throw new IllegalArgumentException ("A_Table_Rate_Type Invalid value - " + A_Table_Rate_Type + " - Reference_ID=53255 - AM - RT");
		if (A_Table_Rate_Type.length() > 2)
		{
			log.warning("Length > 2 - truncated");
			A_Table_Rate_Type = A_Table_Rate_Type.substring(0, 2);
		}
		set_Value (COLUMNNAME_A_Table_Rate_Type, A_Table_Rate_Type);
	}

	/** Get A_Table_Rate_Type.
		@return A_Table_Rate_Type	  */
	public String getA_Table_Rate_Type () 
	{
		return (String)get_Value(COLUMNNAME_A_Table_Rate_Type);
	}

	/** A_Term AD_Reference_ID=53256 */
	public static final int A_TERM_AD_Reference_ID=53256;
	/** Period = PR */
	public static final String A_TERM_Period = "PR";
	/** Yearly = YR */
	public static final String A_TERM_Yearly = "YR";
	/** Set A_Term.
		@param A_Term A_Term	  */
	public void setA_Term (String A_Term)
	{
		if (A_Term == null) throw new IllegalArgumentException ("A_Term is mandatory");
		if (A_Term.equals("PR") || A_Term.equals("YR")); else throw new IllegalArgumentException ("A_Term Invalid value - " + A_Term + " - Reference_ID=53256 - PR - YR");
		if (A_Term.length() > 2)
		{
			log.warning("Length > 2 - truncated");
			A_Term = A_Term.substring(0, 2);
		}
		set_Value (COLUMNNAME_A_Term, A_Term);
	}

	/** Get A_Term.
		@return A_Term	  */
	public String getA_Term () 
	{
		return (String)get_Value(COLUMNNAME_A_Term);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		if (Description == null)
			throw new IllegalArgumentException ("Description is mandatory.");

		if (Description.length() > 510)
		{
			log.warning("Length > 510 - truncated");
			Description = Description.substring(0, 510);
		}
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}