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

/** Generated Model for C_ProjectType
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_C_ProjectType extends PO implements I_C_ProjectType, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_C_ProjectType (Properties ctx, int C_ProjectType_ID, String trxName)
    {
      super (ctx, C_ProjectType_ID, trxName);
      /** if (C_ProjectType_ID == 0)
        {
			setC_ProjectType_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_C_ProjectType (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_ProjectType[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.eevolution.model.I_C_ProjectStatusCategory getC_ProjectStatusCategory() throws RuntimeException
    {
		return (org.eevolution.model.I_C_ProjectStatusCategory)MTable.get(getCtx(), org.eevolution.model.I_C_ProjectStatusCategory.Table_Name)
			.getPO(getC_ProjectStatusCategory_ID(), get_TrxName());	}

	/** Set Project Status Category.
		@param C_ProjectStatusCategory_ID 
		Project Status Category
	  */
	public void setC_ProjectStatusCategory_ID (int C_ProjectStatusCategory_ID)
	{
		if (C_ProjectStatusCategory_ID < 1) 
			set_Value (COLUMNNAME_C_ProjectStatusCategory_ID, null);
		else 
			set_Value (COLUMNNAME_C_ProjectStatusCategory_ID, Integer.valueOf(C_ProjectStatusCategory_ID));
	}

	/** Get Project Status Category.
		@return Project Status Category
	  */
	public int getC_ProjectStatusCategory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ProjectStatusCategory_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Project Type.
		@param C_ProjectType_ID 
		Type of the project
	  */
	public void setC_ProjectType_ID (int C_ProjectType_ID)
	{
		if (C_ProjectType_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_ProjectType_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_ProjectType_ID, Integer.valueOf(C_ProjectType_ID));
	}

	/** Get Project Type.
		@return Type of the project
	  */
	public int getC_ProjectType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ProjectType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Copy From.
		@param CopyFrom 
		Copy From Record
	  */
	public void setCopyFrom (String CopyFrom)
	{
		set_Value (COLUMNNAME_CopyFrom, CopyFrom);
	}

	/** Get Copy From.
		@return Copy From Record
	  */
	public String getCopyFrom () 
	{
		return (String)get_Value(COLUMNNAME_CopyFrom);
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

	/** Set Comment/Help.
		@param Help 
		Comment or Hint
	  */
	public void setHelp (String Help)
	{
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp () 
	{
		return (String)get_Value(COLUMNNAME_Help);
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

	/** ProjectCategory AD_Reference_ID=288 */
	public static final int PROJECTCATEGORY_AD_Reference_ID=288;
	/** General = N */
	public static final String PROJECTCATEGORY_General = "N";
	/** Asset Project = A */
	public static final String PROJECTCATEGORY_AssetProject = "A";
	/** Work Order (Job) = W */
	public static final String PROJECTCATEGORY_WorkOrderJob = "W";
	/** Service (Charge) Project = S */
	public static final String PROJECTCATEGORY_ServiceChargeProject = "S";
	/** Set Project Category.
		@param ProjectCategory 
		Project Category
	  */
	public void setProjectCategory (String ProjectCategory)
	{

		set_ValueNoCheck (COLUMNNAME_ProjectCategory, ProjectCategory);
	}

	/** Get Project Category.
		@return Project Category
	  */
	public String getProjectCategory () 
	{
		return (String)get_Value(COLUMNNAME_ProjectCategory);
	}

	public org.compiere.model.I_R_StandardRequestType getR_StandardRequestType() throws RuntimeException
    {
		return (org.compiere.model.I_R_StandardRequestType)MTable.get(getCtx(), org.compiere.model.I_R_StandardRequestType.Table_Name)
			.getPO(getR_StandardRequestType_ID(), get_TrxName());	}

	/** Set Standard Request Type.
		@param R_StandardRequestType_ID 
		Standard Request Type
	  */
	public void setR_StandardRequestType_ID (int R_StandardRequestType_ID)
	{
		if (R_StandardRequestType_ID < 1) 
			set_Value (COLUMNNAME_R_StandardRequestType_ID, null);
		else 
			set_Value (COLUMNNAME_R_StandardRequestType_ID, Integer.valueOf(R_StandardRequestType_ID));
	}

	/** Get Standard Request Type.
		@return Standard Request Type
	  */
	public int getR_StandardRequestType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_StandardRequestType_ID);
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