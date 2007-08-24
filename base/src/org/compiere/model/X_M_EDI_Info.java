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

import java.util.*;
import java.sql.*;
import java.math.*;
import java.lang.reflect.Constructor;
import java.util.logging.Level;
import org.compiere.util.*;

/** Generated Model for M_EDI_Info
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_M_EDI_Info extends PO implements I_M_EDI_Info, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_M_EDI_Info (Properties ctx, int M_EDI_Info_ID, String trxName)
    {
      super (ctx, M_EDI_Info_ID, trxName);
      /** if (M_EDI_Info_ID == 0)        {			setInfo (null);
			setM_EDI_ID (0);
			setM_EDI_Info_ID (0);
} */
    }

    /** Load Constructor */
    public X_M_EDI_Info (Properties ctx, ResultSet rs, String trxName)
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
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID);
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_M_EDI_Info[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Info.
		@param Info 
		Information
	  */
	public void setInfo (String Info)
	{
		if (Info == null)
			throw new IllegalArgumentException ("Info is mandatory.");
		if (Info.length() > 4000)
		{
			log.warning("Length > 4000 - truncated");
			Info = Info.substring(0, 3999);
		}
		set_Value (COLUMNNAME_Info, Info);
	}

	/** Get Info.
		@return Information
	  */
	public String getInfo () 
	{
		return (String)get_Value(COLUMNNAME_Info);
	}

	public I_M_EDI getI_M_EDI() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_M_EDI.Table_Name);
        I_M_EDI result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_EDI)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_EDI_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set EDI Transaction.
		@param M_EDI_ID EDI Transaction	  */
	public void setM_EDI_ID (int M_EDI_ID)
	{
		if (M_EDI_ID < 1)
			 throw new IllegalArgumentException ("M_EDI_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_EDI_ID, Integer.valueOf(M_EDI_ID));
	}

	/** Get EDI Transaction.
@return EDI Transaction	  */
	public int getM_EDI_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_EDI_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getM_EDI_ID()));
    }

	/** Set EDI Log.
		@param M_EDI_Info_ID EDI Log	  */
	public void setM_EDI_Info_ID (int M_EDI_Info_ID)
	{
		if (M_EDI_Info_ID < 1)
			 throw new IllegalArgumentException ("M_EDI_Info_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_EDI_Info_ID, Integer.valueOf(M_EDI_Info_ID));
	}

	/** Get EDI Log.
@return EDI Log	  */
	public int getM_EDI_Info_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_EDI_Info_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}