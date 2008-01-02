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

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for C_Currency_Acct
 *  @author Adempiere (generated) 
 *  @version Release 3.3.1t - $Id$ */
public class X_C_Currency_Acct extends PO implements I_C_Currency_Acct, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_C_Currency_Acct (Properties ctx, int C_Currency_Acct_ID, String trxName)
    {
      super (ctx, C_Currency_Acct_ID, trxName);
      /** if (C_Currency_Acct_ID == 0)
        {
			setC_AcctSchema_ID (0);
			setC_Currency_ID (0);
			setRealizedGain_Acct (0);
			setRealizedLoss_Acct (0);
			setUnrealizedGain_Acct (0);
			setUnrealizedLoss_Acct (0);
        } */
    }

    /** Load Constructor */
    public X_C_Currency_Acct (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_Currency_Acct[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_AcctSchema getC_AcctSchema() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_C_AcctSchema.Table_Name);
        I_C_AcctSchema result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_AcctSchema)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_AcctSchema_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Accounting Schema.
		@param C_AcctSchema_ID 
		Rules for accounting
	  */
	public void setC_AcctSchema_ID (int C_AcctSchema_ID)
	{
		if (C_AcctSchema_ID < 1)
			 throw new IllegalArgumentException ("C_AcctSchema_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_AcctSchema_ID, Integer.valueOf(C_AcctSchema_ID));
	}

	/** Get Accounting Schema.
		@return Rules for accounting
	  */
	public int getC_AcctSchema_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_AcctSchema_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Currency getC_Currency() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_C_Currency.Table_Name);
        I_C_Currency result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Currency)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Currency_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Currency.
		@param C_Currency_ID 
		The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID)
	{
		if (C_Currency_ID < 1)
			 throw new IllegalArgumentException ("C_Currency_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_Currency_ID, Integer.valueOf(C_Currency_ID));
	}

	/** Get Currency.
		@return The Currency for this record
	  */
	public int getC_Currency_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Currency_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Realized Gain Acct.
		@param RealizedGain_Acct 
		Realized Gain Account
	  */
	public void setRealizedGain_Acct (int RealizedGain_Acct)
	{
		set_Value (COLUMNNAME_RealizedGain_Acct, Integer.valueOf(RealizedGain_Acct));
	}

	/** Get Realized Gain Acct.
		@return Realized Gain Account
	  */
	public int getRealizedGain_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_RealizedGain_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Realized Loss Acct.
		@param RealizedLoss_Acct 
		Realized Loss Account
	  */
	public void setRealizedLoss_Acct (int RealizedLoss_Acct)
	{
		set_Value (COLUMNNAME_RealizedLoss_Acct, Integer.valueOf(RealizedLoss_Acct));
	}

	/** Get Realized Loss Acct.
		@return Realized Loss Account
	  */
	public int getRealizedLoss_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_RealizedLoss_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Unrealized Gain Acct.
		@param UnrealizedGain_Acct 
		Unrealized Gain Account for currency revaluation
	  */
	public void setUnrealizedGain_Acct (int UnrealizedGain_Acct)
	{
		set_Value (COLUMNNAME_UnrealizedGain_Acct, Integer.valueOf(UnrealizedGain_Acct));
	}

	/** Get Unrealized Gain Acct.
		@return Unrealized Gain Account for currency revaluation
	  */
	public int getUnrealizedGain_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UnrealizedGain_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Unrealized Loss Acct.
		@param UnrealizedLoss_Acct 
		Unrealized Loss Account for currency revaluation
	  */
	public void setUnrealizedLoss_Acct (int UnrealizedLoss_Acct)
	{
		set_Value (COLUMNNAME_UnrealizedLoss_Acct, Integer.valueOf(UnrealizedLoss_Acct));
	}

	/** Get Unrealized Loss Acct.
		@return Unrealized Loss Account for currency revaluation
	  */
	public int getUnrealizedLoss_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UnrealizedLoss_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}