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

/** Generated Model for WS_WebService_Para
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_WS_WebService_Para extends PO implements I_WS_WebService_Para, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_WS_WebService_Para (Properties ctx, int WS_WebService_Para_ID, String trxName)
    {
      super (ctx, WS_WebService_Para_ID, trxName);
      /** if (WS_WebService_Para_ID == 0)
        {
			setParameterType (null);
			setWS_WebServiceType_ID (0);
			setWS_WebService_Para_ID (0);
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

		set_Value (COLUMNNAME_ParameterType, ParameterType);
	}

	/** Get Parameter Type.
		@return Parameter Type	  */
	public String getParameterType () 
	{
		return (String)get_Value(COLUMNNAME_ParameterType);
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

	public org.compiere.model.I_WS_WebServiceType getWS_WebServiceType() throws RuntimeException
    {
		return (org.compiere.model.I_WS_WebServiceType)MTable.get(getCtx(), org.compiere.model.I_WS_WebServiceType.Table_Name)
			.getPO(getWS_WebServiceType_ID(), get_TrxName());	}

	/** Set Web Service Type.
		@param WS_WebServiceType_ID Web Service Type	  */
	public void setWS_WebServiceType_ID (int WS_WebServiceType_ID)
	{
		if (WS_WebServiceType_ID < 1) 
			set_Value (COLUMNNAME_WS_WebServiceType_ID, null);
		else 
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

	/** Set Web Service Parameters.
		@param WS_WebService_Para_ID Web Service Parameters	  */
	public void setWS_WebService_Para_ID (int WS_WebService_Para_ID)
	{
		if (WS_WebService_Para_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_WS_WebService_Para_ID, null);
		else 
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
}