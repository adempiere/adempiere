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

/** Generated Model for AD_ColumnProcessPara
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_AD_ColumnProcessPara extends PO implements I_AD_ColumnProcessPara, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_AD_ColumnProcessPara (Properties ctx, int AD_ColumnProcessPara_ID, String trxName)
    {
      super (ctx, AD_ColumnProcessPara_ID, trxName);
      /** if (AD_ColumnProcessPara_ID == 0)
        {
			setAD_ColumnProcessPara_ID (0);
        } */
    }

    /** Load Constructor */
    public X_AD_ColumnProcessPara (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_ColumnProcessPara[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Link Process Parameter.
		@param AD_ColumnProcessPara_ID Link Process Parameter	  */
	public void setAD_ColumnProcessPara_ID (int AD_ColumnProcessPara_ID)
	{
		if (AD_ColumnProcessPara_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_ColumnProcessPara_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_ColumnProcessPara_ID, Integer.valueOf(AD_ColumnProcessPara_ID));
	}

	/** Get Link Process Parameter.
		@return Link Process Parameter	  */
	public int getAD_ColumnProcessPara_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_ColumnProcessPara_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_ColumnProcess getAD_ColumnProcess() throws RuntimeException
    {
		return (org.compiere.model.I_AD_ColumnProcess)MTable.get(getCtx(), org.compiere.model.I_AD_ColumnProcess.Table_Name)
			.getPO(getAD_ColumnProcess_ID(), get_TrxName());	}

	/** Set Column Link Process.
		@param AD_ColumnProcess_ID Column Link Process	  */
	public void setAD_ColumnProcess_ID (int AD_ColumnProcess_ID)
	{
		if (AD_ColumnProcess_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_ColumnProcess_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_ColumnProcess_ID, Integer.valueOf(AD_ColumnProcess_ID));
	}

	/** Get Column Link Process.
		@return Column Link Process	  */
	public int getAD_ColumnProcess_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_ColumnProcess_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_Process_Para getAD_Process_Para() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Process_Para)MTable.get(getCtx(), org.compiere.model.I_AD_Process_Para.Table_Name)
			.getPO(getAD_Process_Para_ID(), get_TrxName());	}

	/** Set Process Parameter.
		@param AD_Process_Para_ID Process Parameter	  */
	public void setAD_Process_Para_ID (int AD_Process_Para_ID)
	{
		if (AD_Process_Para_ID < 1) 
			set_Value (COLUMNNAME_AD_Process_Para_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Process_Para_ID, Integer.valueOf(AD_Process_Para_ID));
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

	/** Set Default Logic.
		@param DefaultValue 
		Default value hierarchy, separated by ;
	  */
	public void setDefaultValue (String DefaultValue)
	{
		set_Value (COLUMNNAME_DefaultValue, DefaultValue);
	}

	/** Get Default Logic.
		@return Default value hierarchy, separated by ;
	  */
	public String getDefaultValue () 
	{
		return (String)get_Value(COLUMNNAME_DefaultValue);
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