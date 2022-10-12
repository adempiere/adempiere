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

/** Generated Model for AD_FieldCustom
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3 - $Id$ */
public class X_AD_FieldCustom extends PO implements I_AD_FieldCustom, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210415L;

    /** Standard Constructor */
    public X_AD_FieldCustom (Properties ctx, int AD_FieldCustom_ID, String trxName)
    {
      super (ctx, AD_FieldCustom_ID, trxName);
      /** if (AD_FieldCustom_ID == 0)
        {
			setAD_FieldCustom_ID (0);
			setAD_Field_ID (0);
			setAD_TabCustom_ID (0);
        } */
    }

    /** Load Constructor */
    public X_AD_FieldCustom (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_FieldCustom[")
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

	/** Set Custom Field.
		@param AD_FieldCustom_ID Custom Field	  */
	public void setAD_FieldCustom_ID (int AD_FieldCustom_ID)
	{
		if (AD_FieldCustom_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_FieldCustom_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_FieldCustom_ID, Integer.valueOf(AD_FieldCustom_ID));
	}

	/** Get Custom Field.
		@return Custom Field	  */
	public int getAD_FieldCustom_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_FieldCustom_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_AD_FieldDefinition getAD_FieldDefinition() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_FieldDefinition)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_FieldDefinition.Table_Name)
			.getPO(getAD_FieldDefinition_ID(), get_TrxName());	}

	/** Set Field Definition.
		@param AD_FieldDefinition_ID Field Definition	  */
	public void setAD_FieldDefinition_ID (int AD_FieldDefinition_ID)
	{
		if (AD_FieldDefinition_ID < 1) 
			set_Value (COLUMNNAME_AD_FieldDefinition_ID, null);
		else 
			set_Value (COLUMNNAME_AD_FieldDefinition_ID, Integer.valueOf(AD_FieldDefinition_ID));
	}

	/** Get Field Definition.
		@return Field Definition	  */
	public int getAD_FieldDefinition_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_FieldDefinition_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_AD_FieldGroup getAD_FieldGroup() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_FieldGroup)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_FieldGroup.Table_Name)
			.getPO(getAD_FieldGroup_ID(), get_TrxName());	}

	/** Set Field Group.
		@param AD_FieldGroup_ID 
		Logical grouping of fields
	  */
	public void setAD_FieldGroup_ID (int AD_FieldGroup_ID)
	{
		if (AD_FieldGroup_ID < 1) 
			set_Value (COLUMNNAME_AD_FieldGroup_ID, null);
		else 
			set_Value (COLUMNNAME_AD_FieldGroup_ID, Integer.valueOf(AD_FieldGroup_ID));
	}

	/** Get Field Group.
		@return Logical grouping of fields
	  */
	public int getAD_FieldGroup_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_FieldGroup_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_AD_Field getAD_Field() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_Field)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_Field.Table_Name)
			.getPO(getAD_Field_ID(), get_TrxName());	}

	/** Set Field.
		@param AD_Field_ID 
		Field on a database table
	  */
	public void setAD_Field_ID (int AD_Field_ID)
	{
		if (AD_Field_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_Field_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_Field_ID, Integer.valueOf(AD_Field_ID));
	}

	/** Get Field.
		@return Field on a database table
	  */
	public int getAD_Field_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Field_ID);
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

	public org.adempiere.core.domains.models.I_AD_Reference getAD_Reference() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_Reference)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_Reference.Table_Name)
			.getPO(getAD_Reference_ID(), get_TrxName());	}

	/** Set Reference.
		@param AD_Reference_ID 
		System Reference and Validation
	  */
	public void setAD_Reference_ID (int AD_Reference_ID)
	{
		if (AD_Reference_ID < 1) 
			set_Value (COLUMNNAME_AD_Reference_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Reference_ID, Integer.valueOf(AD_Reference_ID));
	}

	/** Get Reference.
		@return System Reference and Validation
	  */
	public int getAD_Reference_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Reference_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_AD_Reference getAD_Reference_Value() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_Reference)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_Reference.Table_Name)
			.getPO(getAD_Reference_Value_ID(), get_TrxName());	}

	/** Set Reference Key.
		@param AD_Reference_Value_ID 
		Required to specify, if data type is Table or List
	  */
	public void setAD_Reference_Value_ID (int AD_Reference_Value_ID)
	{
		if (AD_Reference_Value_ID < 1) 
			set_Value (COLUMNNAME_AD_Reference_Value_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Reference_Value_ID, Integer.valueOf(AD_Reference_Value_ID));
	}

	/** Get Reference Key.
		@return Required to specify, if data type is Table or List
	  */
	public int getAD_Reference_Value_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Reference_Value_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_AD_TabCustom getAD_TabCustom() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_TabCustom)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_TabCustom.Table_Name)
			.getPO(getAD_TabCustom_ID(), get_TrxName());	}

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

	public org.adempiere.core.domains.models.I_AD_Val_Rule getAD_Val_Rule() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_Val_Rule)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_Val_Rule.Table_Name)
			.getPO(getAD_Val_Rule_ID(), get_TrxName());	}

	/** Set Dynamic Validation.
		@param AD_Val_Rule_ID 
		Dynamic Validation Rule
	  */
	public void setAD_Val_Rule_ID (int AD_Val_Rule_ID)
	{
		if (AD_Val_Rule_ID < 1) 
			set_Value (COLUMNNAME_AD_Val_Rule_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Val_Rule_ID, Integer.valueOf(AD_Val_Rule_ID));
	}

	/** Get Dynamic Validation.
		@return Dynamic Validation Rule
	  */
	public int getAD_Val_Rule_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Val_Rule_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Default Logic.
		@param DefaultValue 
		Default value hierarchy, separated by ;
	  */
	public void setDefaultValue (String DefaultValue)
	{
		set_Value (COLUMNNAME_DefaultValue, DefaultValue);
	}

	/** Get Default Logic.
		@return Default value hierarchy, separated by ;
	  */
	public String getDefaultValue () 
	{
		return (String)get_Value(COLUMNNAME_DefaultValue);
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

	/** Set Display Length.
		@param DisplayLength 
		Length of the display in characters
	  */
	public void setDisplayLength (int DisplayLength)
	{
		set_Value (COLUMNNAME_DisplayLength, Integer.valueOf(DisplayLength));
	}

	/** Get Display Length.
		@return Length of the display in characters
	  */
	public int getDisplayLength () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DisplayLength);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Info Factory Class.
		@param InfoFactoryClass 
		Fully qualified class name that implements the InfoFactory interface
	  */
	public void setInfoFactoryClass (String InfoFactoryClass)
	{
		set_Value (COLUMNNAME_InfoFactoryClass, InfoFactoryClass);
	}

	/** Get Info Factory Class.
		@return Fully qualified class name that implements the InfoFactory interface
	  */
	public String getInfoFactoryClass () 
	{
		return (String)get_Value(COLUMNNAME_InfoFactoryClass);
	}

	/** IsAllowCopy AD_Reference_ID=319 */
	public static final int ISALLOWCOPY_AD_Reference_ID=319;
	/** Yes = Y */
	public static final String ISALLOWCOPY_Yes = "Y";
	/** No = N */
	public static final String ISALLOWCOPY_No = "N";
	/** Set Allow Copy.
		@param IsAllowCopy 
		Defines whether the value of this field is considered in the copy of record
	  */
	public void setIsAllowCopy (String IsAllowCopy)
	{

		set_Value (COLUMNNAME_IsAllowCopy, IsAllowCopy);
	}

	/** Get Allow Copy.
		@return Defines whether the value of this field is considered in the copy of record
	  */
	public String getIsAllowCopy () 
	{
		return (String)get_Value(COLUMNNAME_IsAllowCopy);
	}

	/** Set Displayed.
		@param IsDisplayed 
		Determines, if this field is displayed
	  */
	public void setIsDisplayed (boolean IsDisplayed)
	{
		set_Value (COLUMNNAME_IsDisplayed, Boolean.valueOf(IsDisplayed));
	}

	/** Get Displayed.
		@return Determines, if this field is displayed
	  */
	public boolean isDisplayed () 
	{
		Object oo = get_Value(COLUMNNAME_IsDisplayed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Displayed in Grid.
		@param IsDisplayedGrid 
		Determines, if this field is displayed in grid view
	  */
	public void setIsDisplayedGrid (boolean IsDisplayedGrid)
	{
		set_Value (COLUMNNAME_IsDisplayedGrid, Boolean.valueOf(IsDisplayedGrid));
	}

	/** Get Displayed in Grid.
		@return Determines, if this field is displayed in grid view
	  */
	public boolean isDisplayedGrid () 
	{
		Object oo = get_Value(COLUMNNAME_IsDisplayedGrid);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** IsEmbedded AD_Reference_ID=319 */
	public static final int ISEMBEDDED_AD_Reference_ID=319;
	/** Yes = Y */
	public static final String ISEMBEDDED_Yes = "Y";
	/** No = N */
	public static final String ISEMBEDDED_No = "N";
	/** Set Is Embedded.
		@param IsEmbedded 
		When checked of include tab is embedded
	  */
	public void setIsEmbedded (String IsEmbedded)
	{

		set_Value (COLUMNNAME_IsEmbedded, IsEmbedded);
	}

	/** Get Is Embedded.
		@return When checked of include tab is embedded
	  */
	public String getIsEmbedded () 
	{
		return (String)get_Value(COLUMNNAME_IsEmbedded);
	}

	/** IsEncrypted AD_Reference_ID=319 */
	public static final int ISENCRYPTED_AD_Reference_ID=319;
	/** Yes = Y */
	public static final String ISENCRYPTED_Yes = "Y";
	/** No = N */
	public static final String ISENCRYPTED_No = "N";
	/** Set Encrypted.
		@param IsEncrypted 
		Display or Storage is encrypted
	  */
	public void setIsEncrypted (String IsEncrypted)
	{

		set_Value (COLUMNNAME_IsEncrypted, IsEncrypted);
	}

	/** Get Encrypted.
		@return Display or Storage is encrypted
	  */
	public String getIsEncrypted () 
	{
		return (String)get_Value(COLUMNNAME_IsEncrypted);
	}

	/** IsFieldOnly AD_Reference_ID=319 */
	public static final int ISFIELDONLY_AD_Reference_ID=319;
	/** Yes = Y */
	public static final String ISFIELDONLY_Yes = "Y";
	/** No = N */
	public static final String ISFIELDONLY_No = "N";
	/** Set Field Only.
		@param IsFieldOnly 
		Label is not displayed
	  */
	public void setIsFieldOnly (String IsFieldOnly)
	{

		set_Value (COLUMNNAME_IsFieldOnly, IsFieldOnly);
	}

	/** Get Field Only.
		@return Label is not displayed
	  */
	public String getIsFieldOnly () 
	{
		return (String)get_Value(COLUMNNAME_IsFieldOnly);
	}

	/** IsHeading AD_Reference_ID=319 */
	public static final int ISHEADING_AD_Reference_ID=319;
	/** Yes = Y */
	public static final String ISHEADING_Yes = "Y";
	/** No = N */
	public static final String ISHEADING_No = "N";
	/** Set Heading only.
		@param IsHeading 
		Field without Column - Only label is displayed
	  */
	public void setIsHeading (String IsHeading)
	{

		set_Value (COLUMNNAME_IsHeading, IsHeading);
	}

	/** Get Heading only.
		@return Field without Column - Only label is displayed
	  */
	public String getIsHeading () 
	{
		return (String)get_Value(COLUMNNAME_IsHeading);
	}

	/** IsMandatory AD_Reference_ID=319 */
	public static final int ISMANDATORY_AD_Reference_ID=319;
	/** Yes = Y */
	public static final String ISMANDATORY_Yes = "Y";
	/** No = N */
	public static final String ISMANDATORY_No = "N";
	/** Set Mandatory.
		@param IsMandatory 
		Data entry is required in this column
	  */
	public void setIsMandatory (String IsMandatory)
	{

		set_Value (COLUMNNAME_IsMandatory, IsMandatory);
	}

	/** Get Mandatory.
		@return Data entry is required in this column
	  */
	public String getIsMandatory () 
	{
		return (String)get_Value(COLUMNNAME_IsMandatory);
	}

	/** IsQuickEntry AD_Reference_ID=319 */
	public static final int ISQUICKENTRY_AD_Reference_ID=319;
	/** Yes = Y */
	public static final String ISQUICKENTRY_Yes = "Y";
	/** No = N */
	public static final String ISQUICKENTRY_No = "N";
	/** Set Quick Entry.
		@param IsQuickEntry 
		Display in Quick Entry Form
	  */
	public void setIsQuickEntry (String IsQuickEntry)
	{

		set_Value (COLUMNNAME_IsQuickEntry, IsQuickEntry);
	}

	/** Get Quick Entry.
		@return Display in Quick Entry Form
	  */
	public String getIsQuickEntry () 
	{
		return (String)get_Value(COLUMNNAME_IsQuickEntry);
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

	/** IsSameLine AD_Reference_ID=319 */
	public static final int ISSAMELINE_AD_Reference_ID=319;
	/** Yes = Y */
	public static final String ISSAMELINE_Yes = "Y";
	/** No = N */
	public static final String ISSAMELINE_No = "N";
	/** Set Same Line.
		@param IsSameLine 
		Displayed on same line as previous field
	  */
	public void setIsSameLine (String IsSameLine)
	{

		set_Value (COLUMNNAME_IsSameLine, IsSameLine);
	}

	/** Get Same Line.
		@return Displayed on same line as previous field
	  */
	public String getIsSameLine () 
	{
		return (String)get_Value(COLUMNNAME_IsSameLine);
	}

	/** Set Updatable.
		@param IsUpdateable 
		Determines, if the field can be updated
	  */
	public void setIsUpdateable (boolean IsUpdateable)
	{
		set_Value (COLUMNNAME_IsUpdateable, Boolean.valueOf(IsUpdateable));
	}

	/** Get Updatable.
		@return Determines, if the field can be updated
	  */
	public boolean isUpdateable () 
	{
		Object oo = get_Value(COLUMNNAME_IsUpdateable);
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

	/** ObscureType AD_Reference_ID=291 */
	public static final int OBSCURETYPE_AD_Reference_ID=291;
	/** Obscure Digits but last 4 = 904 */
	public static final String OBSCURETYPE_ObscureDigitsButLast4 = "904";
	/** Obscure Digits but first/last 4 = 944 */
	public static final String OBSCURETYPE_ObscureDigitsButFirstLast4 = "944";
	/** Obscure AlphaNumeric but first/last 4 = A44 */
	public static final String OBSCURETYPE_ObscureAlphaNumericButFirstLast4 = "A44";
	/** Obscure AlphaNumeric but last 4 = A04 */
	public static final String OBSCURETYPE_ObscureAlphaNumericButLast4 = "A04";
	/** Set Obscure.
		@param ObscureType 
		Type of obscuring the data (limiting the display)
	  */
	public void setObscureType (String ObscureType)
	{

		set_Value (COLUMNNAME_ObscureType, ObscureType);
	}

	/** Get Obscure.
		@return Type of obscuring the data (limiting the display)
	  */
	public String getObscureType () 
	{
		return (String)get_Value(COLUMNNAME_ObscureType);
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

	/** Set Grid Sequence.
		@param SeqNoGrid 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNoGrid (int SeqNoGrid)
	{
		set_Value (COLUMNNAME_SeqNoGrid, Integer.valueOf(SeqNoGrid));
	}

	/** Get Grid Sequence.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNoGrid () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNoGrid);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Record Sort No.
		@param SortNo 
		Determines in what order the records are displayed
	  */
	public void setSortNo (int SortNo)
	{
		set_Value (COLUMNNAME_SortNo, Integer.valueOf(SortNo));
	}

	/** Get Record Sort No.
		@return Determines in what order the records are displayed
	  */
	public int getSortNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SortNo);
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