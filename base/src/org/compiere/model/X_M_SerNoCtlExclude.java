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

/** Generated Model for M_SerNoCtlExclude
 *  @author Adempiere (generated) 
 *  @version Release 3.5.3a - $Id$ */
public class X_M_SerNoCtlExclude extends PO implements I_M_SerNoCtlExclude, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20081221L;

    /** Standard Constructor */
    public X_M_SerNoCtlExclude (Properties ctx, int M_SerNoCtlExclude_ID, String trxName)
    {
      super (ctx, M_SerNoCtlExclude_ID, trxName);
      /** if (M_SerNoCtlExclude_ID == 0)
        {
			setAD_Table_ID (0);
			setIsSOTrx (false);
			setM_SerNoCtl_ID (0);
			setM_SerNoCtlExclude_ID (0);
        } */
    }

    /** Load Constructor */
    public X_M_SerNoCtlExclude (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 2 - Client 
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
      StringBuffer sb = new StringBuffer ("X_M_SerNoCtlExclude[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Table getAD_Table() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_AD_Table.Table_Name);
        I_AD_Table result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Table)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Table_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Table.
		@param AD_Table_ID 
		Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID)
	{
		if (AD_Table_ID < 1) 
			set_Value (COLUMNNAME_AD_Table_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
	}

	/** Get Table.
		@return Database Table information
	  */
	public int getAD_Table_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Table_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sales Transaction.
		@param IsSOTrx 
		This is a Sales Transaction
	  */
	public void setIsSOTrx (boolean IsSOTrx)
	{
		set_Value (COLUMNNAME_IsSOTrx, Boolean.valueOf(IsSOTrx));
	}

	/** Get Sales Transaction.
		@return This is a Sales Transaction
	  */
	public boolean isSOTrx () 
	{
		Object oo = get_Value(COLUMNNAME_IsSOTrx);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_M_SerNoCtl getM_SerNoCtl() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(I_M_SerNoCtl.Table_Name);
        I_M_SerNoCtl result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_SerNoCtl)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_SerNoCtl_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

	/** Set Serial No Control.
		@param M_SerNoCtl_ID 
		Product Serial Number Control
	  */
	public void setM_SerNoCtl_ID (int M_SerNoCtl_ID)
	{
		if (M_SerNoCtl_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_SerNoCtl_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_SerNoCtl_ID, Integer.valueOf(M_SerNoCtl_ID));
	}

	/** Get Serial No Control.
		@return Product Serial Number Control
	  */
	public int getM_SerNoCtl_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_SerNoCtl_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Exclude SerNo.
		@param M_SerNoCtlExclude_ID 
		Exclude the ability to create Serial Numbers in Attribute Sets
	  */
	public void setM_SerNoCtlExclude_ID (int M_SerNoCtlExclude_ID)
	{
		if (M_SerNoCtlExclude_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_SerNoCtlExclude_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_SerNoCtlExclude_ID, Integer.valueOf(M_SerNoCtlExclude_ID));
	}

	/** Get Exclude SerNo.
		@return Exclude the ability to create Serial Numbers in Attribute Sets
	  */
	public int getM_SerNoCtlExclude_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_SerNoCtlExclude_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}