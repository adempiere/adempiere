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

/** Generated Model for ASP_Process_Para
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2 - $Id$ */
public class X_ASP_Process_Para extends PO implements I_ASP_Process_Para, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191120L;

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

	public org.compiere.model.I_AD_Process_Para getAD_Process_Para() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Process_Para)MTable.get(getCtx(), org.compiere.model.I_AD_Process_Para.Table_Name)
			.getPO(getAD_Process_Para_ID(), get_TrxName());	}

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

	public org.compiere.model.I_ASP_Process getASP_Process() throws RuntimeException
    {
		return (org.compiere.model.I_ASP_Process)MTable.get(getCtx(), org.compiere.model.I_ASP_Process.Table_Name)
			.getPO(getASP_Process_ID(), get_TrxName());	}

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

		set_Value (COLUMNNAME_ASP_Status, ASP_Status);
	}

	/** Get ASP Status.
		@return ASP Status	  */
	public String getASP_Status () 
	{
		return (String)get_Value(COLUMNNAME_ASP_Status);
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