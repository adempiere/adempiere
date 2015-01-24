/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.spinsuite.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for SPS_Menu
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_SPS_Menu extends PO implements I_SPS_Menu, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20140927L;

    /** Standard Constructor */
    public X_SPS_Menu (Properties ctx, int SPS_Menu_ID, String trxName)
    {
      super (ctx, SPS_Menu_ID, trxName);
      /** if (SPS_Menu_ID == 0)
        {
			setEntityType (null);
// ECA01
			setMenuType (null);
			setName (null);
			setSPS_Menu_ID (0);
        } */
    }

    /** Load Constructor */
    public X_SPS_Menu (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_SPS_Menu[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Action AD_Reference_ID=53504 */
	public static final int ACTION_AD_Reference_ID=53504;
	/** Form = X */
	public static final String ACTION_Form = "X";
	/** Process = P */
	public static final String ACTION_Process = "P";
	/** Report = R */
	public static final String ACTION_Report = "R";
	/** Window = W */
	public static final String ACTION_Window = "W";
	/** Web Services = S */
	public static final String ACTION_WebServices = "S";
	/** Set Action.
		@param Action 
		Indicates the Action to be performed
	  */
	public void setAction (String Action)
	{

		set_Value (COLUMNNAME_Action, Action);
	}

	/** Get Action.
		@return Indicates the Action to be performed
	  */
	public String getAction () 
	{
		return (String)get_Value(COLUMNNAME_Action);
	}

	public org.spinsuite.model.I_SPS_Menu getActivityMenu() throws RuntimeException
    {
		return (org.spinsuite.model.I_SPS_Menu)MTable.get(getCtx(), org.spinsuite.model.I_SPS_Menu.Table_Name)
			.getPO(getActivityMenu_ID(), get_TrxName());	}

	/** Set Activity Menu.
		@param ActivityMenu_ID Activity Menu	  */
	public void setActivityMenu_ID (int ActivityMenu_ID)
	{
		if (ActivityMenu_ID < 1) 
			set_Value (COLUMNNAME_ActivityMenu_ID, null);
		else 
			set_Value (COLUMNNAME_ActivityMenu_ID, Integer.valueOf(ActivityMenu_ID));
	}

	/** Get Activity Menu.
		@return Activity Menu	  */
	public int getActivityMenu_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ActivityMenu_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_Form getAD_Form() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Form)MTable.get(getCtx(), org.compiere.model.I_AD_Form.Table_Name)
			.getPO(getAD_Form_ID(), get_TrxName());	}

	/** Set Special Form.
		@param AD_Form_ID 
		Special Form
	  */
	public void setAD_Form_ID (int AD_Form_ID)
	{
		if (AD_Form_ID < 1) 
			set_Value (COLUMNNAME_AD_Form_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Form_ID, Integer.valueOf(AD_Form_ID));
	}

	/** Get Special Form.
		@return Special Form
	  */
	public int getAD_Form_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Form_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_Process getAD_Process() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Process)MTable.get(getCtx(), org.compiere.model.I_AD_Process.Table_Name)
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

	/** DeploymentType AD_Reference_ID=53506 */
	public static final int DEPLOYMENTTYPE_AD_Reference_ID=53506;
	/** Direct Form = D */
	public static final String DEPLOYMENTTYPE_DirectForm = "D";
	/** List = L */
	public static final String DEPLOYMENTTYPE_List = "L";
	/** Menu with Quick Action = M */
	public static final String DEPLOYMENTTYPE_MenuWithQuickAction = "M";
	/** List with Quick Action = W */
	public static final String DEPLOYMENTTYPE_ListWithQuickAction = "W";
	/** Set Deployment Type.
		@param DeploymentType Deployment Type	  */
	public void setDeploymentType (String DeploymentType)
	{

		set_Value (COLUMNNAME_DeploymentType, DeploymentType);
	}

	/** Get Deployment Type.
		@return Deployment Type	  */
	public String getDeploymentType () 
	{
		return (String)get_Value(COLUMNNAME_DeploymentType);
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

	/** EntityType AD_Reference_ID=389 */
	public static final int ENTITYTYPE_AD_Reference_ID=389;
	/** Set Entity Type.
		@param EntityType 
		Dictionary Entity Type; Determines ownership and synchronization
	  */
	public void setEntityType (String EntityType)
	{

		set_Value (COLUMNNAME_EntityType, EntityType);
	}

	/** Get Entity Type.
		@return Dictionary Entity Type; Determines ownership and synchronization
	  */
	public String getEntityType () 
	{
		return (String)get_Value(COLUMNNAME_EntityType);
	}

	/** Set Error Image URL.
		@param ErrImgUrl Error Image URL	  */
	public void setErrImgUrl (String ErrImgUrl)
	{
		set_Value (COLUMNNAME_ErrImgUrl, ErrImgUrl);
	}

	/** Get Error Image URL.
		@return Error Image URL	  */
	public String getErrImgUrl () 
	{
		return (String)get_Value(COLUMNNAME_ErrImgUrl);
	}

	/** Set GROUP BY Clause.
		@param GroupByClause GROUP BY Clause	  */
	public void setGroupByClause (String GroupByClause)
	{
		set_Value (COLUMNNAME_GroupByClause, GroupByClause);
	}

	/** Get GROUP BY Clause.
		@return GROUP BY Clause	  */
	public String getGroupByClause () 
	{
		return (String)get_Value(COLUMNNAME_GroupByClause);
	}

	/** Set Image URL.
		@param ImageURL 
		URL of  image
	  */
	public void setImageURL (String ImageURL)
	{
		set_Value (COLUMNNAME_ImageURL, ImageURL);
	}

	/** Get Image URL.
		@return URL of  image
	  */
	public String getImageURL () 
	{
		return (String)get_Value(COLUMNNAME_ImageURL);
	}

	/** Set Centrally maintained.
		@param IsCentrallyMaintained 
		Information maintained in System Element table
	  */
	public void setIsCentrallyMaintained (boolean IsCentrallyMaintained)
	{
		set_Value (COLUMNNAME_IsCentrallyMaintained, Boolean.valueOf(IsCentrallyMaintained));
	}

	/** Get Centrally maintained.
		@return Information maintained in System Element table
	  */
	public boolean isCentrallyMaintained () 
	{
		Object oo = get_Value(COLUMNNAME_IsCentrallyMaintained);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** IsInsertRecord AD_Reference_ID=319 */
	public static final int ISINSERTRECORD_AD_Reference_ID=319;
	/** Yes = Y */
	public static final String ISINSERTRECORD_Yes = "Y";
	/** No = N */
	public static final String ISINSERTRECORD_No = "N";
	/** Set Insert Record.
		@param IsInsertRecord 
		The user can insert a new Record
	  */
	public void setIsInsertRecord (String IsInsertRecord)
	{

		set_Value (COLUMNNAME_IsInsertRecord, IsInsertRecord);
	}

	/** Get Insert Record.
		@return The user can insert a new Record
	  */
	public String getIsInsertRecord () 
	{
		return (String)get_Value(COLUMNNAME_IsInsertRecord);
	}

	/** IsReadWrite AD_Reference_ID=319 */
	public static final int ISREADWRITE_AD_Reference_ID=319;
	/** Yes = Y */
	public static final String ISREADWRITE_Yes = "Y";
	/** No = N */
	public static final String ISREADWRITE_No = "N";
	/** Set Read Write.
		@param IsReadWrite 
		Field is read / write
	  */
	public void setIsReadWrite (String IsReadWrite)
	{

		set_Value (COLUMNNAME_IsReadWrite, IsReadWrite);
	}

	/** Get Read Write.
		@return Field is read / write
	  */
	public String getIsReadWrite () 
	{
		return (String)get_Value(COLUMNNAME_IsReadWrite);
	}

	/** Set Summary Level.
		@param IsSummary 
		This is a summary entity
	  */
	public void setIsSummary (boolean IsSummary)
	{
		set_Value (COLUMNNAME_IsSummary, Boolean.valueOf(IsSummary));
	}

	/** Get Summary Level.
		@return This is a summary entity
	  */
	public boolean isSummary () 
	{
		Object oo = get_Value(COLUMNNAME_IsSummary);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** MenuType AD_Reference_ID=53672 */
	public static final int MENUTYPE_AD_Reference_ID=53672;
	/** Main Menu = M */
	public static final String MENUTYPE_MainMenu = "M";
	/** Activity Menu = A */
	public static final String MENUTYPE_ActivityMenu = "A";
	/** Quick Action Menu = Q */
	public static final String MENUTYPE_QuickActionMenu = "Q";
	/** Set Menu Type.
		@param MenuType Menu Type	  */
	public void setMenuType (String MenuType)
	{

		set_Value (COLUMNNAME_MenuType, MenuType);
	}

	/** Get Menu Type.
		@return Menu Type	  */
	public String getMenuType () 
	{
		return (String)get_Value(COLUMNNAME_MenuType);
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

	/** Set Sql ORDER BY.
		@param OrderByClause 
		Fully qualified ORDER BY clause
	  */
	public void setOrderByClause (String OrderByClause)
	{
		set_Value (COLUMNNAME_OrderByClause, OrderByClause);
	}

	/** Get Sql ORDER BY.
		@return Fully qualified ORDER BY clause
	  */
	public String getOrderByClause () 
	{
		return (String)get_Value(COLUMNNAME_OrderByClause);
	}

	public org.spinsuite.model.I_SPS_Menu getQuickActionMenu() throws RuntimeException
    {
		return (org.spinsuite.model.I_SPS_Menu)MTable.get(getCtx(), org.spinsuite.model.I_SPS_Menu.Table_Name)
			.getPO(getQuickActionMenu_ID(), get_TrxName());	}

	/** Set Quick Action Menu.
		@param QuickActionMenu_ID Quick Action Menu	  */
	public void setQuickActionMenu_ID (int QuickActionMenu_ID)
	{
		if (QuickActionMenu_ID < 1) 
			set_Value (COLUMNNAME_QuickActionMenu_ID, null);
		else 
			set_Value (COLUMNNAME_QuickActionMenu_ID, Integer.valueOf(QuickActionMenu_ID));
	}

	/** Get Quick Action Menu.
		@return Quick Action Menu	  */
	public int getQuickActionMenu_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_QuickActionMenu_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Menu Option.
		@param SPS_Menu_ID Menu Option	  */
	public void setSPS_Menu_ID (int SPS_Menu_ID)
	{
		if (SPS_Menu_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_SPS_Menu_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_SPS_Menu_ID, Integer.valueOf(SPS_Menu_ID));
	}

	/** Get Menu Option.
		@return Menu Option	  */
	public int getSPS_Menu_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SPS_Menu_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.spinsuite.model.I_SPS_SyncMenu getSPS_SyncMenu() throws RuntimeException
    {
		return (org.spinsuite.model.I_SPS_SyncMenu)MTable.get(getCtx(), org.spinsuite.model.I_SPS_SyncMenu.Table_Name)
			.getPO(getSPS_SyncMenu_ID(), get_TrxName());	}

	/** Set Sync/Option Menu.
		@param SPS_SyncMenu_ID Sync/Option Menu	  */
	public void setSPS_SyncMenu_ID (int SPS_SyncMenu_ID)
	{
		if (SPS_SyncMenu_ID < 1) 
			set_Value (COLUMNNAME_SPS_SyncMenu_ID, null);
		else 
			set_Value (COLUMNNAME_SPS_SyncMenu_ID, Integer.valueOf(SPS_SyncMenu_ID));
	}

	/** Get Sync/Option Menu.
		@return Sync/Option Menu	  */
	public int getSPS_SyncMenu_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SPS_SyncMenu_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.spinsuite.model.I_SPS_Table getSPS_Table() throws RuntimeException
    {
		return (org.spinsuite.model.I_SPS_Table)MTable.get(getCtx(), org.spinsuite.model.I_SPS_Table.Table_Name)
			.getPO(getSPS_Table_ID(), get_TrxName());	}

	/** Set Mobile Table.
		@param SPS_Table_ID Mobile Table	  */
	public void setSPS_Table_ID (int SPS_Table_ID)
	{
		if (SPS_Table_ID < 1) 
			set_Value (COLUMNNAME_SPS_Table_ID, null);
		else 
			set_Value (COLUMNNAME_SPS_Table_ID, Integer.valueOf(SPS_Table_ID));
	}

	/** Get Mobile Table.
		@return Mobile Table	  */
	public int getSPS_Table_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SPS_Table_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.spinsuite.model.I_SPS_Window getSPS_Window() throws RuntimeException
    {
		return (org.spinsuite.model.I_SPS_Window)MTable.get(getCtx(), org.spinsuite.model.I_SPS_Window.Table_Name)
			.getPO(getSPS_Window_ID(), get_TrxName());	}

	/** Set Window Mobile.
		@param SPS_Window_ID Window Mobile	  */
	public void setSPS_Window_ID (int SPS_Window_ID)
	{
		if (SPS_Window_ID < 1) 
			set_Value (COLUMNNAME_SPS_Window_ID, null);
		else 
			set_Value (COLUMNNAME_SPS_Window_ID, Integer.valueOf(SPS_Window_ID));
	}

	/** Get Window Mobile.
		@return Window Mobile	  */
	public int getSPS_Window_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SPS_Window_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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