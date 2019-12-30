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
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_ClientShare
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2 - $Id$ */
public class X_AD_ClientShare extends PO implements I_AD_ClientShare, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191120L;

    /** Standard Constructor */
    public X_AD_ClientShare (Properties ctx, int AD_ClientShare_ID, String trxName)
    {
      super (ctx, AD_ClientShare_ID, trxName);
      /** if (AD_ClientShare_ID == 0)
        {
			setAD_ClientShare_ID (0);
			setAD_Table_ID (0);
			setName (null);
			setShareType (null);
        } */
    }

    /** Load Constructor */
    public X_AD_ClientShare (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_ClientShare[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Client Share.
		@param AD_ClientShare_ID 
		Force (not) sharing of client/org entities
	  */
	public void setAD_ClientShare_ID (int AD_ClientShare_ID)
	{
		if (AD_ClientShare_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_ClientShare_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_ClientShare_ID, Integer.valueOf(AD_ClientShare_ID));
	}

	/** Get Client Share.
		@return Force (not) sharing of client/org entities
	  */
	public int getAD_ClientShare_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_ClientShare_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_Table getAD_Table() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Table)MTable.get(getCtx(), org.compiere.model.I_AD_Table.Table_Name)
			.getPO(getAD_Table_ID(), get_TrxName());	}

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

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** ShareType AD_Reference_ID=365 */
	public static final int SHARETYPE_AD_Reference_ID=365;
	/** Client (all shared) = C */
	public static final String SHARETYPE_ClientAllShared = "C";
	/** Org (not shared) = O */
	public static final String SHARETYPE_OrgNotShared = "O";
	/** Client or Org = x */
	public static final String SHARETYPE_ClientOrOrg = "x";
	/** Set Share Type.
		@param ShareType 
		Type of sharing
	  */
	public void setShareType (String ShareType)
	{

		set_Value (COLUMNNAME_ShareType, ShareType);
	}

	/** Get Share Type.
		@return Type of sharing
	  */
	public String getShareType () 
	{
		return (String)get_Value(COLUMNNAME_ShareType);
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