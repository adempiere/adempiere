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
package org.spin.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_AppRegistration_Para
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_AD_AppRegistration_Para extends PO implements I_AD_AppRegistration_Para, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_AD_AppRegistration_Para (Properties ctx, int AD_AppRegistration_Para_ID, String trxName)
    {
      super (ctx, AD_AppRegistration_Para_ID, trxName);
      /** if (AD_AppRegistration_Para_ID == 0)
        {
			setAD_AppRegistration_ID (0);
			setAD_AppRegistration_Para_ID (0);
			setParameterName (null);
			setParameterType (null);
// C
        } */
    }

    /** Load Constructor */
    public X_AD_AppRegistration_Para (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_AppRegistration_Para[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.spin.model.I_AD_AppRegistration getAD_AppRegistration() throws RuntimeException
    {
		return (org.spin.model.I_AD_AppRegistration)MTable.get(getCtx(), org.spin.model.I_AD_AppRegistration.Table_Name)
			.getPO(getAD_AppRegistration_ID(), get_TrxName());	}

	/** Set Application Registration.
		@param AD_AppRegistration_ID 
		External Application Registration
	  */
	public void setAD_AppRegistration_ID (int AD_AppRegistration_ID)
	{
		if (AD_AppRegistration_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_AppRegistration_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_AppRegistration_ID, Integer.valueOf(AD_AppRegistration_ID));
	}

	/** Get Application Registration.
		@return External Application Registration
	  */
	public int getAD_AppRegistration_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_AppRegistration_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set App Registration Parameter.
		@param AD_AppRegistration_Para_ID 
		App Registration Parameter used for connect with External App
	  */
	public void setAD_AppRegistration_Para_ID (int AD_AppRegistration_Para_ID)
	{
		if (AD_AppRegistration_Para_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_AppRegistration_Para_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_AppRegistration_Para_ID, Integer.valueOf(AD_AppRegistration_Para_ID));
	}

	/** Get App Registration Parameter.
		@return App Registration Parameter used for connect with External App
	  */
	public int getAD_AppRegistration_Para_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_AppRegistration_Para_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.spin.model.I_AD_AppSupport_Para getAD_AppSupport_Para() throws RuntimeException
    {
		return (org.spin.model.I_AD_AppSupport_Para)MTable.get(getCtx(), org.spin.model.I_AD_AppSupport_Para.Table_Name)
			.getPO(getAD_AppSupport_Para_ID(), get_TrxName());	}

	/** Set App Support Default Parameter.
		@param AD_AppSupport_Para_ID 
		Default parameter for App Supported
	  */
	public void setAD_AppSupport_Para_ID (int AD_AppSupport_Para_ID)
	{
		if (AD_AppSupport_Para_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_AppSupport_Para_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_AppSupport_Para_ID, Integer.valueOf(AD_AppSupport_Para_ID));
	}

	/** Get App Support Default Parameter.
		@return Default parameter for App Supported
	  */
	public int getAD_AppSupport_Para_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_AppSupport_Para_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getParameterType()));
    }

	/** Set Parameter Value.
		@param ParameterValue Parameter Value	  */
	public void setParameterValue (String ParameterValue)
	{
		set_Value (COLUMNNAME_ParameterValue, ParameterValue);
	}

	/** Get Parameter Value.
		@return Parameter Value	  */
	public String getParameterValue () 
	{
		return (String)get_Value(COLUMNNAME_ParameterValue);
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
}