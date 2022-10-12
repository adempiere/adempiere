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
package org.adempiere.core.domains.models;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;

/** Generated Model for AD_WindowCustom
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2 - $Id$ */
public class X_AD_WindowCustom extends PO implements I_AD_WindowCustom, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191120L;

    /** Standard Constructor */
    public X_AD_WindowCustom (Properties ctx, int AD_WindowCustom_ID, String trxName)
    {
      super (ctx, AD_WindowCustom_ID, trxName);
      /** if (AD_WindowCustom_ID == 0)
        {
			setAD_WindowCustom_ID (0);
			setAD_Window_ID (0);
			setHierarchyType (null);
// O
        } */
    }

    /** Load Constructor */
    public X_AD_WindowCustom (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_WindowCustom[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.adempiere.core.domains.models.I_AD_ContextInfo getAD_ContextInfo() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_ContextInfo)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_ContextInfo.Table_Name)
			.getPO(getAD_ContextInfo_ID(), get_TrxName());	}

	/** Set Context Info.
		@param AD_ContextInfo_ID 
		Context Info Maintaining
	  */
	public void setAD_ContextInfo_ID (int AD_ContextInfo_ID)
	{
		if (AD_ContextInfo_ID < 1) 
			set_Value (COLUMNNAME_AD_ContextInfo_ID, null);
		else 
			set_Value (COLUMNNAME_AD_ContextInfo_ID, Integer.valueOf(AD_ContextInfo_ID));
	}

	/** Get Context Info.
		@return Context Info Maintaining
	  */
	public int getAD_ContextInfo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_ContextInfo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_AD_Role getAD_Role() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_Role)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_Role.Table_Name)
			.getPO(getAD_Role_ID(), get_TrxName());	}

	/** Set Role.
		@param AD_Role_ID 
		Responsibility Role
	  */
	public void setAD_Role_ID (int AD_Role_ID)
	{
		if (AD_Role_ID < 0) 
			set_Value (COLUMNNAME_AD_Role_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Role_ID, Integer.valueOf(AD_Role_ID));
	}

	/** Get Role.
		@return Responsibility Role
	  */
	public int getAD_Role_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Role_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_AD_User getAD_User() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_User)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_User.Table_Name)
			.getPO(getAD_User_ID(), get_TrxName());	}

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1) 
			set_Value (COLUMNNAME_AD_User_ID, null);
		else 
			set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Window Customization .
		@param AD_WindowCustom_ID Window Customization 	  */
	public void setAD_WindowCustom_ID (int AD_WindowCustom_ID)
	{
		if (AD_WindowCustom_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_WindowCustom_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_WindowCustom_ID, Integer.valueOf(AD_WindowCustom_ID));
	}

	/** Get Window Customization .
		@return Window Customization 	  */
	public int getAD_WindowCustom_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_WindowCustom_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_AD_Window getAD_Window() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_Window)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_Window.Table_Name)
			.getPO(getAD_Window_ID(), get_TrxName());	}

	/** Set Window.
		@param AD_Window_ID 
		Data entry or display window
	  */
	public void setAD_Window_ID (int AD_Window_ID)
	{
		if (AD_Window_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_Window_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_Window_ID, Integer.valueOf(AD_Window_ID));
	}

	/** Get Window.
		@return Data entry or display window
	  */
	public int getAD_Window_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Window_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_ASP_Level getASP_Level() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_ASP_Level)MTable.get(getCtx(), org.adempiere.core.domains.models.I_ASP_Level.Table_Name)
			.getPO(getASP_Level_ID(), get_TrxName());	}

	/** Set ASP Level.
		@param ASP_Level_ID ASP Level	  */
	public void setASP_Level_ID (int ASP_Level_ID)
	{
		if (ASP_Level_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_ASP_Level_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_ASP_Level_ID, Integer.valueOf(ASP_Level_ID));
	}

	/** Get ASP Level.
		@return ASP Level	  */
	public int getASP_Level_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ASP_Level_ID);
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

	/** HierarchyType AD_Reference_ID=54117 */
	public static final int HIERARCHYTYPE_AD_Reference_ID=54117;
	/** Add = A */
	public static final String HIERARCHYTYPE_Add = "A";
	/** Merge = M */
	public static final String HIERARCHYTYPE_Merge = "M";
	/** Overwrite = O */
	public static final String HIERARCHYTYPE_Overwrite = "O";
	/** Set Hierarchy Type.
		@param HierarchyType 
		Hierarchy Type (Hierarchy: Add, Merge or Overwrite)
	  */
	public void setHierarchyType (String HierarchyType)
	{

		set_Value (COLUMNNAME_HierarchyType, HierarchyType);
	}

	/** Get Hierarchy Type.
		@return Hierarchy Type (Hierarchy: Add, Merge or Overwrite)
	  */
	public String getHierarchyType () 
	{
		return (String)get_Value(COLUMNNAME_HierarchyType);
	}

	/** IsReadOnly AD_Reference_ID=319 */
	public static final int ISREADONLY_AD_Reference_ID=319;
	/** Yes = Y */
	public static final String ISREADONLY_Yes = "Y";
	/** No = N */
	public static final String ISREADONLY_No = "N";
	/** Set Read Only.
		@param IsReadOnly 
		Field is read only
	  */
	public void setIsReadOnly (String IsReadOnly)
	{

		set_Value (COLUMNNAME_IsReadOnly, IsReadOnly);
	}

	/** Get Read Only.
		@return Field is read only
	  */
	public String getIsReadOnly () 
	{
		return (String)get_Value(COLUMNNAME_IsReadOnly);
	}

	/** IsUserUpdateable AD_Reference_ID=319 */
	public static final int ISUSERUPDATEABLE_AD_Reference_ID=319;
	/** Yes = Y */
	public static final String ISUSERUPDATEABLE_Yes = "Y";
	/** No = N */
	public static final String ISUSERUPDATEABLE_No = "N";
	/** Set User updatable.
		@param IsUserUpdateable 
		The field can be updated by the user
	  */
	public void setIsUserUpdateable (String IsUserUpdateable)
	{

		set_Value (COLUMNNAME_IsUserUpdateable, IsUserUpdateable);
	}

	/** Get User updatable.
		@return The field can be updated by the user
	  */
	public String getIsUserUpdateable () 
	{
		return (String)get_Value(COLUMNNAME_IsUserUpdateable);
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

	/** WindowType AD_Reference_ID=108 */
	public static final int WINDOWTYPE_AD_Reference_ID=108;
	/** Single Record = S */
	public static final String WINDOWTYPE_SingleRecord = "S";
	/** Maintain = M */
	public static final String WINDOWTYPE_Maintain = "M";
	/** Transaction = T */
	public static final String WINDOWTYPE_Transaction = "T";
	/** Query Only = Q */
	public static final String WINDOWTYPE_QueryOnly = "Q";
	/** Set WindowType.
		@param WindowType 
		Type or classification of a Window
	  */
	public void setWindowType (String WindowType)
	{

		set_Value (COLUMNNAME_WindowType, WindowType);
	}

	/** Get WindowType.
		@return Type or classification of a Window
	  */
	public String getWindowType () 
	{
		return (String)get_Value(COLUMNNAME_WindowType);
	}
}