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

/** Generated Model for AD_BrowseCustom
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2 - $Id$ */
public class X_AD_BrowseCustom extends PO implements I_AD_BrowseCustom, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191120L;

    /** Standard Constructor */
    public X_AD_BrowseCustom (Properties ctx, int AD_BrowseCustom_ID, String trxName)
    {
      super (ctx, AD_BrowseCustom_ID, trxName);
      /** if (AD_BrowseCustom_ID == 0)
        {
			setAD_BrowseCustom_ID (0);
			setAD_Browse_ID (0);
			setHierarchyType (null);
// O
        } */
    }

    /** Load Constructor */
    public X_AD_BrowseCustom (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_BrowseCustom[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Browse Customization.
		@param AD_BrowseCustom_ID Browse Customization	  */
	public void setAD_BrowseCustom_ID (int AD_BrowseCustom_ID)
	{
		if (AD_BrowseCustom_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_BrowseCustom_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_BrowseCustom_ID, Integer.valueOf(AD_BrowseCustom_ID));
	}

	/** Get Browse Customization.
		@return Browse Customization	  */
	public int getAD_BrowseCustom_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_BrowseCustom_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_AD_Browse getAD_Browse() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_Browse)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_Browse.Table_Name)
			.getPO(getAD_Browse_ID(), get_TrxName());	}

	/** Set Smart Browse.
		@param AD_Browse_ID Smart Browse	  */
	public void setAD_Browse_ID (int AD_Browse_ID)
	{
		if (AD_Browse_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_Browse_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_Browse_ID, Integer.valueOf(AD_Browse_ID));
	}

	/** Get Smart Browse.
		@return Smart Browse	  */
	public int getAD_Browse_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Browse_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_AD_Process getAD_Process() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_Process)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_Process.Table_Name)
			.getPO(getAD_Process_ID(), get_TrxName());	}

	/** Set Process.
		@param AD_Process_ID 
		Process or Report
	  */
	public void setAD_Process_ID (int AD_Process_ID)
	{
		if (AD_Process_ID < 1) 
			set_Value (COLUMNNAME_AD_Process_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Process_ID, Integer.valueOf(AD_Process_ID));
	}

	/** Get Process.
		@return Process or Report
	  */
	public int getAD_Process_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Process_ID);
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

	public org.adempiere.core.domains.models.I_AD_Table getAD_Table() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_Table)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_Table.Table_Name)
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
			set_Value (COLUMNNAME_AD_Window_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Window_ID, Integer.valueOf(AD_Window_ID));
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

	/** IsCollapsibleByDefault AD_Reference_ID=319 */
	public static final int ISCOLLAPSIBLEBYDEFAULT_AD_Reference_ID=319;
	/** Yes = Y */
	public static final String ISCOLLAPSIBLEBYDEFAULT_Yes = "Y";
	/** No = N */
	public static final String ISCOLLAPSIBLEBYDEFAULT_No = "N";
	/** Set Is collapsible by default.
		@param IsCollapsibleByDefault 
		Flag to indicate if is collapsible by default
	  */
	public void setIsCollapsibleByDefault (String IsCollapsibleByDefault)
	{

		set_Value (COLUMNNAME_IsCollapsibleByDefault, IsCollapsibleByDefault);
	}

	/** Get Is collapsible by default.
		@return Flag to indicate if is collapsible by default
	  */
	public String getIsCollapsibleByDefault () 
	{
		return (String)get_Value(COLUMNNAME_IsCollapsibleByDefault);
	}

	/** IsDeleteable AD_Reference_ID=319 */
	public static final int ISDELETEABLE_AD_Reference_ID=319;
	/** Yes = Y */
	public static final String ISDELETEABLE_Yes = "Y";
	/** No = N */
	public static final String ISDELETEABLE_No = "N";
	/** Set Records deletable.
		@param IsDeleteable 
		Indicates if records can be deleted from the database
	  */
	public void setIsDeleteable (String IsDeleteable)
	{

		set_Value (COLUMNNAME_IsDeleteable, IsDeleteable);
	}

	/** Get Records deletable.
		@return Indicates if records can be deleted from the database
	  */
	public String getIsDeleteable () 
	{
		return (String)get_Value(COLUMNNAME_IsDeleteable);
	}

	/** IsExecutedQueryByDefault AD_Reference_ID=319 */
	public static final int ISEXECUTEDQUERYBYDEFAULT_AD_Reference_ID=319;
	/** Yes = Y */
	public static final String ISEXECUTEDQUERYBYDEFAULT_Yes = "Y";
	/** No = N */
	public static final String ISEXECUTEDQUERYBYDEFAULT_No = "N";
	/** Set Is executed query by default.
		@param IsExecutedQueryByDefault 
		Is executed query by default
	  */
	public void setIsExecutedQueryByDefault (String IsExecutedQueryByDefault)
	{

		set_Value (COLUMNNAME_IsExecutedQueryByDefault, IsExecutedQueryByDefault);
	}

	/** Get Is executed query by default.
		@return Is executed query by default
	  */
	public String getIsExecutedQueryByDefault () 
	{
		return (String)get_Value(COLUMNNAME_IsExecutedQueryByDefault);
	}

	/** IsSelectedByDefault AD_Reference_ID=319 */
	public static final int ISSELECTEDBYDEFAULT_AD_Reference_ID=319;
	/** Yes = Y */
	public static final String ISSELECTEDBYDEFAULT_Yes = "Y";
	/** No = N */
	public static final String ISSELECTEDBYDEFAULT_No = "N";
	/** Set Is selected by default.
		@param IsSelectedByDefault 
		Allows auto select rows of a browser
	  */
	public void setIsSelectedByDefault (String IsSelectedByDefault)
	{

		set_Value (COLUMNNAME_IsSelectedByDefault, IsSelectedByDefault);
	}

	/** Get Is selected by default.
		@return Allows auto select rows of a browser
	  */
	public String getIsSelectedByDefault () 
	{
		return (String)get_Value(COLUMNNAME_IsSelectedByDefault);
	}

	/** IsShowTotal AD_Reference_ID=319 */
	public static final int ISSHOWTOTAL_AD_Reference_ID=319;
	/** Yes = Y */
	public static final String ISSHOWTOTAL_Yes = "Y";
	/** No = N */
	public static final String ISSHOWTOTAL_No = "N";
	/** Set Show Total.
		@param IsShowTotal 
		Show totals into Smart Browser
	  */
	public void setIsShowTotal (String IsShowTotal)
	{

		set_Value (COLUMNNAME_IsShowTotal, IsShowTotal);
	}

	/** Get Show Total.
		@return Show totals into Smart Browser
	  */
	public String getIsShowTotal () 
	{
		return (String)get_Value(COLUMNNAME_IsShowTotal);
	}

	/** IsUpdateable AD_Reference_ID=319 */
	public static final int ISUPDATEABLE_AD_Reference_ID=319;
	/** Yes = Y */
	public static final String ISUPDATEABLE_Yes = "Y";
	/** No = N */
	public static final String ISUPDATEABLE_No = "N";
	/** Set Updatable.
		@param IsUpdateable 
		Determines, if the field can be updated
	  */
	public void setIsUpdateable (String IsUpdateable)
	{

		set_Value (COLUMNNAME_IsUpdateable, IsUpdateable);
	}

	/** Get Updatable.
		@return Determines, if the field can be updated
	  */
	public String getIsUpdateable () 
	{
		return (String)get_Value(COLUMNNAME_IsUpdateable);
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

	/** Set Sql WHERE.
		@param WhereClause 
		Fully qualified SQL WHERE clause
	  */
	public void setWhereClause (String WhereClause)
	{
		set_Value (COLUMNNAME_WhereClause, WhereClause);
	}

	/** Get Sql WHERE.
		@return Fully qualified SQL WHERE clause
	  */
	public String getWhereClause () 
	{
		return (String)get_Value(COLUMNNAME_WhereClause);
	}
}