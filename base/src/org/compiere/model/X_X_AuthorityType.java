/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for X_AuthorityType
 *  @author Adempiere (generated) 
 *  @version 1.03 - $Id$ */
public class X_X_AuthorityType extends PO implements I_X_AuthorityType, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20150128L;

    /** Standard Constructor */
    public X_X_AuthorityType (Properties ctx, int X_AuthorityType_ID, String trxName)
    {
      super (ctx, X_AuthorityType_ID, trxName);
      /** if (X_AuthorityType_ID == 0)
        {
			setX_AuthorityType_ID (0);
        } */
    }

    /** Load Constructor */
    public X_X_AuthorityType (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_X_AuthorityType[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set AuthorityType.
		@param X_AuthorityType_ID AuthorityType	  */
	public void setX_AuthorityType_ID (int X_AuthorityType_ID)
	{
		if (X_AuthorityType_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_X_AuthorityType_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_X_AuthorityType_ID, Integer.valueOf(X_AuthorityType_ID));
	}

	/** Get AuthorityType.
		@return AuthorityType	  */
	public int getX_AuthorityType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_X_AuthorityType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}