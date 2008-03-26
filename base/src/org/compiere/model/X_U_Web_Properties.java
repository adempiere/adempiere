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

/** Generated Model for U_Web_Properties
 *  @author Adempiere (generated) 
 *  @version Release 3.4.0s - $Id$ */
public class X_U_Web_Properties extends PO implements I_U_Web_Properties, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_U_Web_Properties (Properties ctx, int U_Web_Properties_ID, String trxName)
    {
      super (ctx, U_Web_Properties_ID, trxName);
      /** if (U_Web_Properties_ID == 0)
        {
			setU_Key (null);
			setU_Value (null);
			setU_Web_Properties_ID (0);
        } */
    }

    /** Load Constructor */
    public X_U_Web_Properties (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_U_Web_Properties[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Key.
		@param U_Key Key	  */
	public void setU_Key (String U_Key)
	{
		if (U_Key == null)
			throw new IllegalArgumentException ("U_Key is mandatory.");

		if (U_Key.length() > 240)
		{
			log.warning("Length > 240 - truncated");
			U_Key = U_Key.substring(0, 240);
		}
		set_Value (COLUMNNAME_U_Key, U_Key);
	}

	/** Get Key.
		@return Key	  */
	public String getU_Key () 
	{
		return (String)get_Value(COLUMNNAME_U_Key);
	}

	/** Set Value.
		@param U_Value Value	  */
	public void setU_Value (String U_Value)
	{
		if (U_Value == null)
			throw new IllegalArgumentException ("U_Value is mandatory.");

		if (U_Value.length() > 240)
		{
			log.warning("Length > 240 - truncated");
			U_Value = U_Value.substring(0, 240);
		}
		set_Value (COLUMNNAME_U_Value, U_Value);
	}

	/** Get Value.
		@return Value	  */
	public String getU_Value () 
	{
		return (String)get_Value(COLUMNNAME_U_Value);
	}

	/** Set Web Properties.
		@param U_Web_Properties_ID Web Properties	  */
	public void setU_Web_Properties_ID (int U_Web_Properties_ID)
	{
		if (U_Web_Properties_ID < 1)
			 throw new IllegalArgumentException ("U_Web_Properties_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_U_Web_Properties_ID, Integer.valueOf(U_Web_Properties_ID));
	}

	/** Get Web Properties.
		@return Web Properties	  */
	public int getU_Web_Properties_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_U_Web_Properties_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}