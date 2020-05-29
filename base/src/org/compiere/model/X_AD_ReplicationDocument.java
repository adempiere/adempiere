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

/** Generated Model for AD_ReplicationDocument
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3 - $Id$ */
public class X_AD_ReplicationDocument extends PO implements I_AD_ReplicationDocument, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20200331L;

    /** Standard Constructor */
    public X_AD_ReplicationDocument (Properties ctx, int AD_ReplicationDocument_ID, String trxName)
    {
      super (ctx, AD_ReplicationDocument_ID, trxName);
      /** if (AD_ReplicationDocument_ID == 0)
        {
			setAD_ReplicationDocument_ID (0);
			setAD_ReplicationStrategy_ID (0);
			setAD_Table_ID (0);
			setC_DocType_ID (0);
			setReplicationType (null);
        } */
    }

    /** Load Constructor */
    public X_AD_ReplicationDocument (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_ReplicationDocument[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Replication Document.
		@param AD_ReplicationDocument_ID Replication Document	  */
	public void setAD_ReplicationDocument_ID (int AD_ReplicationDocument_ID)
	{
		if (AD_ReplicationDocument_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_ReplicationDocument_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_ReplicationDocument_ID, Integer.valueOf(AD_ReplicationDocument_ID));
	}

	/** Get Replication Document.
		@return Replication Document	  */
	public int getAD_ReplicationDocument_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_ReplicationDocument_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Replication Strategy.
		@param AD_ReplicationStrategy_ID 
		Data Replication Strategy
	  */
	public void setAD_ReplicationStrategy_ID (int AD_ReplicationStrategy_ID)
	{
		if (AD_ReplicationStrategy_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_ReplicationStrategy_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_ReplicationStrategy_ID, Integer.valueOf(AD_ReplicationStrategy_ID));
	}

	/** Get Replication Strategy.
		@return Data Replication Strategy
	  */
	public int getAD_ReplicationStrategy_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_ReplicationStrategy_ID);
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

	public org.compiere.model.I_C_DocType getC_DocType() throws RuntimeException
    {
		return (org.compiere.model.I_C_DocType)MTable.get(getCtx(), org.compiere.model.I_C_DocType.Table_Name)
			.getPO(getC_DocType_ID(), get_TrxName());	}

	/** Set Document Type.
		@param C_DocType_ID 
		Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID)
	{
		if (C_DocType_ID < 0) 
			set_Value (COLUMNNAME_C_DocType_ID, null);
		else 
			set_Value (COLUMNNAME_C_DocType_ID, Integer.valueOf(C_DocType_ID));
	}

	/** Get Document Type.
		@return Document type or rules
	  */
	public int getC_DocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocType_ID);
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

	public org.compiere.model.I_EXP_Format getEXP_Format() throws RuntimeException
    {
		return (org.compiere.model.I_EXP_Format)MTable.get(getCtx(), org.compiere.model.I_EXP_Format.Table_Name)
			.getPO(getEXP_Format_ID(), get_TrxName());	}

	/** Set Export Format.
		@param EXP_Format_ID Export Format	  */
	public void setEXP_Format_ID (int EXP_Format_ID)
	{
		if (EXP_Format_ID < 1) 
			set_Value (COLUMNNAME_EXP_Format_ID, null);
		else 
			set_Value (COLUMNNAME_EXP_Format_ID, Integer.valueOf(EXP_Format_ID));
	}

	/** Get Export Format.
		@return Export Format	  */
	public int getEXP_Format_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EXP_Format_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** ReplicationType AD_Reference_ID=126 */
	public static final int REPLICATIONTYPE_AD_Reference_ID=126;
	/** Local = L */
	public static final String REPLICATIONTYPE_Local = "L";
	/** Merge = M */
	public static final String REPLICATIONTYPE_Merge = "M";
	/** Reference = R */
	public static final String REPLICATIONTYPE_Reference = "R";
	/** Broadcast = B */
	public static final String REPLICATIONTYPE_Broadcast = "B";
	/** Set Replication Type.
		@param ReplicationType 
		Type of Data Replication
	  */
	public void setReplicationType (String ReplicationType)
	{

		set_Value (COLUMNNAME_ReplicationType, ReplicationType);
	}

	/** Get Replication Type.
		@return Type of Data Replication
	  */
	public String getReplicationType () 
	{
		return (String)get_Value(COLUMNNAME_ReplicationType);
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