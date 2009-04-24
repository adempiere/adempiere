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

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

/** Generated Model for ASP_Process_Para
 *  @author Adempiere (generated) 
 *  @version Release 3.5.3a - $Id$ */
public class X_ASP_Process_Para extends PO implements I_ASP_Process_Para, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20081221L;

    /** Standard Constructor */
    public X_ASP_Process_Para (Properties ctx, int ASP_Process_Para_ID, String trxName)
    {
      super (ctx, ASP_Process_Para_ID, trxName);
      /** if (ASP_Process_Para_ID == 0)
        {
			setASP_Status (null);
// U
        } */
    }

    /** Load Constructor */
    public X_ASP_Process_Para (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
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
      StringBuffer sb = new StringBuffer ("X_ASP_Process_Para[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Process_Para getAD_Process_Para() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Process_Para.Table_Name);
        I_AD_Process_Para result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Process_Para)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Process_Para_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Process Parameter.
		@param AD_Process_Para_ID Process Parameter	  */
	public void setAD_Process_Para_ID (int AD_Process_Para_ID)
	{
		if (AD_Process_Para_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_Process_Para_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_Process_Para_ID, Integer.valueOf(AD_Process_Para_ID));
	}

	/** Get Process Parameter.
		@return Process Parameter	  */
	public int getAD_Process_Para_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Process_Para_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_ASP_Process getASP_Process() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_ASP_Process.Table_Name);
        I_ASP_Process result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_ASP_Process)constructor.newInstance(new Object[] {getCtx(), new Integer(getASP_Process_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set ASP Process.
		@param ASP_Process_ID ASP Process	  */
	public void setASP_Process_ID (int ASP_Process_ID)
	{
		if (ASP_Process_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_ASP_Process_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_ASP_Process_ID, Integer.valueOf(ASP_Process_ID));
	}

	/** Get ASP Process.
		@return ASP Process	  */
	public int getASP_Process_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ASP_Process_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ASP Process Parameter.
		@param ASP_Process_Para_ID ASP Process Parameter	  */
	public void setASP_Process_Para_ID (int ASP_Process_Para_ID)
	{
		if (ASP_Process_Para_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_ASP_Process_Para_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_ASP_Process_Para_ID, Integer.valueOf(ASP_Process_Para_ID));
	}

	/** Get ASP Process Parameter.
		@return ASP Process Parameter	  */
	public int getASP_Process_Para_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ASP_Process_Para_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** ASP_Status AD_Reference_ID=53234 */
	public static final int ASP_STATUS_AD_Reference_ID=53234;
	/** Hide = H */
	public static final String ASP_STATUS_Hide = "H";
	/** Show = S */
	public static final String ASP_STATUS_Show = "S";
	/** Undefined = U */
	public static final String ASP_STATUS_Undefined = "U";
	/** Set ASP Status.
		@param ASP_Status ASP Status	  */
	public void setASP_Status (String ASP_Status)
	{
		if (ASP_Status == null) throw new IllegalArgumentException ("ASP_Status is mandatory");
		set_Value (COLUMNNAME_ASP_Status, ASP_Status);
	}

	/** Get ASP Status.
		@return ASP Status	  */
	public String getASP_Status () 
	{
		return (String)get_Value(COLUMNNAME_ASP_Status);
	}
}