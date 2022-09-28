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

/** Generated Model for AD_TabCustom
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2 - $Id$ */
public class X_AD_TabCustom extends PO implements I_AD_TabCustom, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191120L;

    /** Standard Constructor */
    public X_AD_TabCustom (Properties ctx, int AD_TabCustom_ID, String trxName)
    {
      super (ctx, AD_TabCustom_ID, trxName);
      /** if (AD_TabCustom_ID == 0)
        {
			setAD_TabCustom_ID (0);
			setAD_Tab_ID (0);
			setAD_WindowCustom_ID (0);
        } */
    }

    /** Load Constructor */
    public X_AD_TabCustom (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_TabCustom[")
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

	public org.adempiere.core.domains.models.I_AD_Image getAD_Image() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_Image)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_Image.Table_Name)
			.getPO(getAD_Image_ID(), get_TrxName());	}

	/** Set Image.
		@param AD_Image_ID 
		Image or Icon
	  */
	public void setAD_Image_ID (int AD_Image_ID)
	{
		if (AD_Image_ID < 1) 
			set_Value (COLUMNNAME_AD_Image_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Image_ID, Integer.valueOf(AD_Image_ID));
	}

	/** Get Image.
		@return Image or Icon
	  */
	public int getAD_Image_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Image_ID);
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

	/** Set Custom Tab.
		@param AD_TabCustom_ID Custom Tab	  */
	public void setAD_TabCustom_ID (int AD_TabCustom_ID)
	{
		if (AD_TabCustom_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_TabCustom_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_TabCustom_ID, Integer.valueOf(AD_TabCustom_ID));
	}

	/** Get Custom Tab.
		@return Custom Tab	  */
	public int getAD_TabCustom_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_TabCustom_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_AD_Tab getAD_Tab() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_Tab)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_Tab.Table_Name)
			.getPO(getAD_Tab_ID(), get_TrxName());	}

	/** Set Tab.
		@param AD_Tab_ID 
		Tab within a Window
	  */
	public void setAD_Tab_ID (int AD_Tab_ID)
	{
		if (AD_Tab_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_Tab_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_Tab_ID, Integer.valueOf(AD_Tab_ID));
	}

	/** Get Tab.
		@return Tab within a Window
	  */
	public int getAD_Tab_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Tab_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_AD_WindowCustom getAD_WindowCustom() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_WindowCustom)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_WindowCustom.Table_Name)
			.getPO(getAD_WindowCustom_ID(), get_TrxName());	}

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

	/** Set Commit Warning.
		@param CommitWarning 
		Warning displayed when saving
	  */
	public void setCommitWarning (String CommitWarning)
	{
		set_Value (COLUMNNAME_CommitWarning, CommitWarning);
	}

	/** Get Commit Warning.
		@return Warning displayed when saving
	  */
	public String getCommitWarning () 
	{
		return (String)get_Value(COLUMNNAME_CommitWarning);
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

	/** Set Display Logic.
		@param DisplayLogic 
		If the Field is displayed, the result determines if the field is actually displayed
	  */
	public void setDisplayLogic (String DisplayLogic)
	{
		set_Value (COLUMNNAME_DisplayLogic, DisplayLogic);
	}

	/** Get Display Logic.
		@return If the Field is displayed, the result determines if the field is actually displayed
	  */
	public String getDisplayLogic () 
	{
		return (String)get_Value(COLUMNNAME_DisplayLogic);
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

	/** IsMultiRowOnly AD_Reference_ID=319 */
	public static final int ISMULTIROWONLY_AD_Reference_ID=319;
	/** Yes = Y */
	public static final String ISMULTIROWONLY_Yes = "Y";
	/** No = N */
	public static final String ISMULTIROWONLY_No = "N";
	/** Set Multi Row Only.
		@param IsMultiRowOnly 
		This applies to Multi-Row view only
	  */
	public void setIsMultiRowOnly (String IsMultiRowOnly)
	{

		set_Value (COLUMNNAME_IsMultiRowOnly, IsMultiRowOnly);
	}

	/** Get Multi Row Only.
		@return This applies to Multi-Row view only
	  */
	public String getIsMultiRowOnly () 
	{
		return (String)get_Value(COLUMNNAME_IsMultiRowOnly);
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

	/** IsSingleRow AD_Reference_ID=319 */
	public static final int ISSINGLEROW_AD_Reference_ID=319;
	/** Yes = Y */
	public static final String ISSINGLEROW_Yes = "Y";
	/** No = N */
	public static final String ISSINGLEROW_No = "N";
	/** Set Single Row Layout.
		@param IsSingleRow 
		Default for toggle between Single- and Multi-Row (Grid) Layout
	  */
	public void setIsSingleRow (String IsSingleRow)
	{

		set_Value (COLUMNNAME_IsSingleRow, IsSingleRow);
	}

	/** Get Single Row Layout.
		@return Default for toggle between Single- and Multi-Row (Grid) Layout
	  */
	public String getIsSingleRow () 
	{
		return (String)get_Value(COLUMNNAME_IsSingleRow);
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

	/** Set Read Only Logic.
		@param ReadOnlyLogic 
		Logic to determine if field is read only (applies only when field is read-write)
	  */
	public void setReadOnlyLogic (String ReadOnlyLogic)
	{
		set_Value (COLUMNNAME_ReadOnlyLogic, ReadOnlyLogic);
	}

	/** Get Read Only Logic.
		@return Logic to determine if field is read only (applies only when field is read-write)
	  */
	public String getReadOnlyLogic () 
	{
		return (String)get_Value(COLUMNNAME_ReadOnlyLogic);
	}

	/** Set Sequence.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Tab Level.
		@param TabLevel 
		Hierarchical Tab Level (0 = top)
	  */
	public void setTabLevel (int TabLevel)
	{
		set_Value (COLUMNNAME_TabLevel, Integer.valueOf(TabLevel));
	}

	/** Get Tab Level.
		@return Hierarchical Tab Level (0 = top)
	  */
	public int getTabLevel () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TabLevel);
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