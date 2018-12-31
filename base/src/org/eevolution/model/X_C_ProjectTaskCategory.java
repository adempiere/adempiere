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
package org.eevolution.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for C_ProjectTaskCategory
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_C_ProjectTaskCategory extends PO implements I_C_ProjectTaskCategory, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_C_ProjectTaskCategory (Properties ctx, int C_ProjectTaskCategory_ID, String trxName)
    {
      super (ctx, C_ProjectTaskCategory_ID, trxName);
      /** if (C_ProjectTaskCategory_ID == 0)
        {
			setC_ProjectTaskCategory_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_C_ProjectTaskCategory (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_ProjectTaskCategory[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Project Task Category.
		@param C_ProjectTaskCategory_ID 
		Set Category for project task
	  */
	public void setC_ProjectTaskCategory_ID (int C_ProjectTaskCategory_ID)
	{
		if (C_ProjectTaskCategory_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_ProjectTaskCategory_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_ProjectTaskCategory_ID, Integer.valueOf(C_ProjectTaskCategory_ID));
	}

	/** Get Project Task Category.
		@return Set Category for project task
	  */
	public int getC_ProjectTaskCategory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ProjectTaskCategory_ID);
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

	/** Set Default.
		@param IsDefault 
		Default value
	  */
	public void setIsDefault (boolean IsDefault)
	{
		set_Value (COLUMNNAME_IsDefault, Boolean.valueOf(IsDefault));
	}

	/** Get Default.
		@return Default value
	  */
	public boolean isDefault () 
	{
		Object oo = get_Value(COLUMNNAME_IsDefault);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	public org.eevolution.model.I_C_ProjectTaskCategory getProjectTaskCategoryParent() throws RuntimeException
    {
		return (org.eevolution.model.I_C_ProjectTaskCategory)MTable.get(getCtx(), org.eevolution.model.I_C_ProjectTaskCategory.Table_Name)
			.getPO(getProjectTaskCategoryParent_ID(), get_TrxName());	}

	/** Set Parent Task Category.
		@param ProjectTaskCategoryParent_ID 
		Parent Task Category
	  */
	public void setProjectTaskCategoryParent_ID (int ProjectTaskCategoryParent_ID)
	{
		if (ProjectTaskCategoryParent_ID < 1) 
			set_Value (COLUMNNAME_ProjectTaskCategoryParent_ID, null);
		else 
			set_Value (COLUMNNAME_ProjectTaskCategoryParent_ID, Integer.valueOf(ProjectTaskCategoryParent_ID));
	}

	/** Get Parent Task Category.
		@return Parent Task Category
	  */
	public int getProjectTaskCategoryParent_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ProjectTaskCategoryParent_ID);
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

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getValue());
    }
}