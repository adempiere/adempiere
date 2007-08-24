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

/** Generated Model for R_ContactInterest
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_R_ContactInterest extends PO implements I_R_ContactInterest, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_R_ContactInterest (Properties ctx, int R_ContactInterest_ID, String trxName)
    {
      super (ctx, R_ContactInterest_ID, trxName);
      /** if (R_ContactInterest_ID == 0)        {			setAD_User_ID (0);
// @AD_User_ID@
			setR_InterestArea_ID (0);
} */
    }

    /** Load Constructor */
    public X_R_ContactInterest (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_R_ContactInterest[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_User getI_AD_User() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_AD_User.Table_Name);
        I_AD_User result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_User)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_User_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1)
			 throw new IllegalArgumentException ("AD_User_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getAD_User_ID()));
    }

	/** Set Opt-out Date.
		@param OptOutDate 
		Date the contact opted out
	  */
	public void setOptOutDate (Timestamp OptOutDate)
	{
		set_ValueNoCheck (COLUMNNAME_OptOutDate, OptOutDate);
	}

	/** Get Opt-out Date.
		@return Date the contact opted out
	  */
	public Timestamp getOptOutDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_OptOutDate);
	}

	public I_R_InterestArea getI_R_InterestArea() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_R_InterestArea.Table_Name);
        I_R_InterestArea result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_R_InterestArea)constructor.newInstance(new Object[] {getCtx(), new Integer(getR_InterestArea_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Interest Area.
		@param R_InterestArea_ID 
		Interest Area or Topic
	  */
	public void setR_InterestArea_ID (int R_InterestArea_ID)
	{
		if (R_InterestArea_ID < 1)
			 throw new IllegalArgumentException ("R_InterestArea_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_R_InterestArea_ID, Integer.valueOf(R_InterestArea_ID));
	}

	/** Get Interest Area.
		@return Interest Area or Topic
	  */
	public int getR_InterestArea_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_InterestArea_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Subscribe Date.
		@param SubscribeDate 
		Date the contact actively subscribed
	  */
	public void setSubscribeDate (Timestamp SubscribeDate)
	{
		set_ValueNoCheck (COLUMNNAME_SubscribeDate, SubscribeDate);
	}

	/** Get Subscribe Date.
		@return Date the contact actively subscribed
	  */
	public Timestamp getSubscribeDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_SubscribeDate);
	}
}