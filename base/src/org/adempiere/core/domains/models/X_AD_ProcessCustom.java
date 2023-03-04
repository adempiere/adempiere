/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.                                     *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net                                                  *
 * or https://github.com/adempiere/adempiere/blob/develop/license.html        *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.adempiere.core.domains.models;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;

/** Generated Model for AD_ProcessCustom
 *  @author Adempiere (generated) 
 *  @version Release 3.9.4 - $Id$ */
public class X_AD_ProcessCustom extends PO implements I_AD_ProcessCustom, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20230102L;

    /** Standard Constructor */
    public X_AD_ProcessCustom (Properties ctx, int AD_ProcessCustom_ID, String trxName)
    {
      super (ctx, AD_ProcessCustom_ID, trxName);
      /** if (AD_ProcessCustom_ID == 0)
        {
			setAD_Process_ID (0);
			setAD_ProcessCustom_ID (0);
			setHierarchyType (null);
// O
        } */
    }

    /** Load Constructor */
    public X_AD_ProcessCustom (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_ProcessCustom[")
        .append(get_ID()).append("]");
      return sb.toString();
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
			set_Value (COLUMNNAME_AD_Browse_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Browse_ID, Integer.valueOf(AD_Browse_ID));
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

	public org.adempiere.core.domains.models.I_AD_Form getAD_Form() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_Form)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_Form.Table_Name)
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

	public org.adempiere.core.domains.models.I_AD_PrintFormat getAD_PrintFormat() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_PrintFormat)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_PrintFormat.Table_Name)
			.getPO(getAD_PrintFormat_ID(), get_TrxName());	}

	/** Set Print Format.
		@param AD_PrintFormat_ID 
		Data Print Format
	  */
	public void setAD_PrintFormat_ID (int AD_PrintFormat_ID)
	{
		if (AD_PrintFormat_ID < 1) 
			set_Value (COLUMNNAME_AD_PrintFormat_ID, null);
		else 
			set_Value (COLUMNNAME_AD_PrintFormat_ID, Integer.valueOf(AD_PrintFormat_ID));
	}

	/** Get Print Format.
		@return Data Print Format
	  */
	public int getAD_PrintFormat_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_PrintFormat_ID);
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
			set_ValueNoCheck (COLUMNNAME_AD_Process_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_Process_ID, Integer.valueOf(AD_Process_ID));
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

	/** Set Process Customization.
		@param AD_ProcessCustom_ID Process Customization	  */
	public void setAD_ProcessCustom_ID (int AD_ProcessCustom_ID)
	{
		if (AD_ProcessCustom_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_ProcessCustom_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_ProcessCustom_ID, Integer.valueOf(AD_ProcessCustom_ID));
	}

	/** Get Process Customization.
		@return Process Customization	  */
	public int getAD_ProcessCustom_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_ProcessCustom_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_AD_ReportView getAD_ReportView() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_ReportView)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_ReportView.Table_Name)
			.getPO(getAD_ReportView_ID(), get_TrxName());	}

	/** Set Report View.
		@param AD_ReportView_ID 
		View used to generate this report
	  */
	public void setAD_ReportView_ID (int AD_ReportView_ID)
	{
		if (AD_ReportView_ID < 1) 
			set_Value (COLUMNNAME_AD_ReportView_ID, null);
		else 
			set_Value (COLUMNNAME_AD_ReportView_ID, Integer.valueOf(AD_ReportView_ID));
	}

	/** Get Report View.
		@return View used to generate this report
	  */
	public int getAD_ReportView_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_ReportView_ID);
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

	public org.adempiere.core.domains.models.I_AD_Workflow getAD_Workflow() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_Workflow)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_Workflow.Table_Name)
			.getPO(getAD_Workflow_ID(), get_TrxName());	}

	/** Set Workflow.
		@param AD_Workflow_ID 
		Workflow or combination of tasks
	  */
	public void setAD_Workflow_ID (int AD_Workflow_ID)
	{
		if (AD_Workflow_ID < 1) 
			set_Value (COLUMNNAME_AD_Workflow_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Workflow_ID, Integer.valueOf(AD_Workflow_ID));
	}

	/** Get Workflow.
		@return Workflow or combination of tasks
	  */
	public int getAD_Workflow_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Workflow_ID);
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

	/** IsDirectPrint AD_Reference_ID=319 */
	public static final int ISDIRECTPRINT_AD_Reference_ID=319;
	/** Yes = Y */
	public static final String ISDIRECTPRINT_Yes = "Y";
	/** No = N */
	public static final String ISDIRECTPRINT_No = "N";
	/** Set Direct print.
		@param IsDirectPrint 
		Print without dialog
	  */
	public void setIsDirectPrint (String IsDirectPrint)
	{

		set_Value (COLUMNNAME_IsDirectPrint, IsDirectPrint);
	}

	/** Get Direct print.
		@return Print without dialog
	  */
	public String getIsDirectPrint () 
	{
		return (String)get_Value(COLUMNNAME_IsDirectPrint);
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

	/** ShowHelp AD_Reference_ID=50007 */
	public static final int SHOWHELP_AD_Reference_ID=50007;
	/** Ask user (for future use) = A */
	public static final String SHOWHELP_AskUserForFutureUse = "A";
	/** Don't show help = N */
	public static final String SHOWHELP_DonTShowHelp = "N";
	/** Show Help = Y */
	public static final String SHOWHELP_ShowHelp = "Y";
	/** Run silently - Take Defaults = S */
	public static final String SHOWHELP_RunSilently_TakeDefaults = "S";
	/** Set Show Help.
		@param ShowHelp Show Help	  */
	public void setShowHelp (String ShowHelp)
	{

		set_Value (COLUMNNAME_ShowHelp, ShowHelp);
	}

	/** Get Show Help.
		@return Show Help	  */
	public String getShowHelp () 
	{
		return (String)get_Value(COLUMNNAME_ShowHelp);
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