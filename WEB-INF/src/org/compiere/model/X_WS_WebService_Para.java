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

/** Generated Model for WS_WebService_Para
 *  @author Adempiere (generated) 
 *  @version Release 3.4.2s - $Id$ */
public class X_WS_WebService_Para extends PO implements I_WS_WebService_Para, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_WS_WebService_Para (Properties ctx, int WS_WebService_Para_ID, String trxName)
    {
      super (ctx, WS_WebService_Para_ID, trxName);
      /** if (WS_WebService_Para_ID == 0)
        {
			setParameterType (null);
			setWS_WebService_Para_ID (0);
			setWS_WebServiceType_ID (0);
        } */
    }

    /** Load Constructor */
    public X_WS_WebService_Para (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_WS_WebService_Para[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Constant Value.
		@param ConstantValue 
		Constant value
	  */
	public void setConstantValue (String ConstantValue)
	{

		if (ConstantValue != null && ConstantValue.length() > 60)
		{
			log.warning("Length > 60 - truncated");
			ConstantValue = ConstantValue.substring(0, 60);
		}
		set_Value (COLUMNNAME_ConstantValue, ConstantValue);
	}

	/** Get Constant Value.
		@return Constant value
	  */
	public String getConstantValue () 
	{
		return (String)get_Value(COLUMNNAME_ConstantValue);
	}

	/** Set Parameter Name.
		@param ParameterName Parameter Name	  */
	public void setParameterName (String ParameterName)
	{

		if (ParameterName != null && ParameterName.length() > 60)
		{
			log.warning("Length > 60 - truncated");
			ParameterName = ParameterName.substring(0, 60);
		}
		set_ValueNoCheck (COLUMNNAME_ParameterName, ParameterName);
	}

	/** Get Parameter Name.
		@return Parameter Name	  */
	public String getParameterName () 
	{
		return (String)get_Value(COLUMNNAME_ParameterName);
	}

	/** ParameterType AD_Reference_ID=53288 */
	public static final int PARAMETERTYPE_AD_Reference_ID=53288;
	/** Constant = C */
	public static final String PARAMETERTYPE_Constant = "C";
	/** Free = F */
	public static final String PARAMETERTYPE_Free = "F";
	/** Set Parameter Type.
		@param ParameterType Parameter Type	  */
	public void setParameterType (String ParameterType)
	{
		if (ParameterType == null) throw new IllegalArgumentException ("ParameterType is mandatory");
		if (ParameterType.equals("C") || ParameterType.equals("F")); else throw new IllegalArgumentException ("ParameterType Invalid value - " + ParameterType + " - Reference_ID=53288 - C - F");
		if (ParameterType.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			ParameterType = ParameterType.substring(0, 1);
		}
		set_Value (COLUMNNAME_ParameterType, ParameterType);
	}

	/** Get Parameter Type.
		@return Parameter Type	  */
	public String getParameterType () 
	{
		return (String)get_Value(COLUMNNAME_ParameterType);
	}

	/** Set Web Service Parameters.
		@param WS_WebService_Para_ID Web Service Parameters	  */
	public void setWS_WebService_Para_ID (int WS_WebService_Para_ID)
	{
		if (WS_WebService_Para_ID < 1)
			 throw new IllegalArgumentException ("WS_WebService_Para_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_WS_WebService_Para_ID, Integer.valueOf(WS_WebService_Para_ID));
	}

	/** Get Web Service Parameters.
		@return Web Service Parameters	  */
	public int getWS_WebService_Para_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WS_WebService_Para_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_WS_WebServiceType getWS_WebServiceType() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_WS_WebServiceType.Table_Name);
        I_WS_WebServiceType result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_WS_WebServiceType)constructor.newInstance(new Object[] {getCtx(), new Integer(getWS_WebServiceType_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Web Service Type.
		@param WS_WebServiceType_ID Web Service Type	  */
	public void setWS_WebServiceType_ID (int WS_WebServiceType_ID)
	{
		if (WS_WebServiceType_ID < 1)
			 throw new IllegalArgumentException ("WS_WebServiceType_ID is mandatory.");
		set_Value (COLUMNNAME_WS_WebServiceType_ID, Integer.valueOf(WS_WebServiceType_ID));
	}

	/** Get Web Service Type.
		@return Web Service Type	  */
	public int getWS_WebServiceType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WS_WebServiceType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}