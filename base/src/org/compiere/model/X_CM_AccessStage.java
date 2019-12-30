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

/** Generated Model for CM_AccessStage
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2 - $Id$ */
public class X_CM_AccessStage extends PO implements I_CM_AccessStage, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191120L;

    /** Standard Constructor */
    public X_CM_AccessStage (Properties ctx, int CM_AccessStage_ID, String trxName)
    {
      super (ctx, CM_AccessStage_ID, trxName);
      /** if (CM_AccessStage_ID == 0)
        {
			setCM_AccessProfile_ID (0);
			setCM_CStage_ID (0);
        } */
    }

    /** Load Constructor */
    public X_CM_AccessStage (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_CM_AccessStage[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_CM_AccessProfile getCM_AccessProfile() throws RuntimeException
    {
		return (org.compiere.model.I_CM_AccessProfile)MTable.get(getCtx(), org.compiere.model.I_CM_AccessProfile.Table_Name)
			.getPO(getCM_AccessProfile_ID(), get_TrxName());	}

	/** Set Web Access Profile.
		@param CM_AccessProfile_ID 
		Web Access Profile
	  */
	public void setCM_AccessProfile_ID (int CM_AccessProfile_ID)
	{
		if (CM_AccessProfile_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_CM_AccessProfile_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_CM_AccessProfile_ID, Integer.valueOf(CM_AccessProfile_ID));
	}

	/** Get Web Access Profile.
		@return Web Access Profile
	  */
	public int getCM_AccessProfile_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CM_AccessProfile_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_CM_CStage getCM_CStage() throws RuntimeException
    {
		return (org.compiere.model.I_CM_CStage)MTable.get(getCtx(), org.compiere.model.I_CM_CStage.Table_Name)
			.getPO(getCM_CStage_ID(), get_TrxName());	}

	/** Set Web Container Stage.
		@param CM_CStage_ID 
		Web Container Stage contains the staging content like images, text etc.
	  */
	public void setCM_CStage_ID (int CM_CStage_ID)
	{
		if (CM_CStage_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_CM_CStage_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_CM_CStage_ID, Integer.valueOf(CM_CStage_ID));
	}

	/** Get Web Container Stage.
		@return Web Container Stage contains the staging content like images, text etc.
	  */
	public int getCM_CStage_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CM_CStage_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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