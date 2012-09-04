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
package org.adempiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_Browse_Field
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_AD_Browse_Field extends PO implements I_AD_Browse_Field, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20120903L;

    /** Standard Constructor */
    public X_AD_Browse_Field (Properties ctx, int AD_Browse_Field_ID, String trxName)
    {
      super (ctx, AD_Browse_Field_ID, trxName);
      /** if (AD_Browse_Field_ID == 0)
        {
			setAD_Browse_Field_ID (0);
			setAD_Browse_ID (0);
			setAD_Element_ID (0);
			setAD_Reference_ID (0);
			setAD_View_Column_ID (0);
			setEntityType (null);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_AD_Browse_Field (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_Browse_Field[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Browse Field.
		@param AD_Browse_Field_ID Browse Field	  */
	public void setAD_Browse_Field_ID (int AD_Browse_Field_ID)
	{
		if (AD_Browse_Field_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_Browse_Field_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_Browse_Field_ID, Integer.valueOf(AD_Browse_Field_ID));
	}

	/** Get Browse Field.
		@return Browse Field	  */
	public int getAD_Browse_Field_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Browse_Field_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.model.I_AD_Browse getAD_Browse() throws RuntimeException
    {
		return (org.adempiere.model.I_AD_Browse)MTable.get(getCtx(), org.adempiere.model.I_AD_Browse.Table_Name)
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

	public org.compiere.model.I_AD_Element getAD_Element() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Element)MTable.get(getCtx(), org.compiere.model.I_AD_Element.Table_Name)
			.getPO(getAD_Element_ID(), get_TrxName());	}

	/** Set System Element.
		@param AD_Element_ID 
		System Element enables the central maintenance of column description and help.
	  */
	public void setAD_Element_ID (int AD_Element_ID)
	{
		if (AD_Element_ID < 1) 
			set_Value (COLUMNNAME_AD_Element_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Element_ID, Integer.valueOf(AD_Element_ID));
	}

	/** Get System Element.
		@return System Element enables the central maintenance of column description and help.
	  */
	public int getAD_Element_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Element_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_Reference getAD_Reference() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Reference)MTable.get(getCtx(), org.compiere.model.I_AD_Reference.Table_Name)
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

	public org.compiere.model.I_AD_Reference getAD_Reference_Value() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Reference)MTable.get(getCtx(), org.compiere.model.I_AD_Reference.Table_Name)
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

	public org.compiere.model.I_AD_Val_Rule getAD_Val_Rule() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Val_Rule)MTable.get(getCtx(), org.compiere.model.I_AD_Val_Rule.Table_Name)
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

	public org.adempiere.model.I_AD_View_Column getAD_View_Column() throws RuntimeException
    {
		return (org.adempiere.model.I_AD_View_Column)MTable.get(getCtx(), org.adempiere.model.I_AD_View_Column.Table_Name)
			.getPO(getAD_View_Column_ID(), get_TrxName());	}

	/** Set View Column.
		@param AD_View_Column_ID 
		Column of View
	  */
	public void setAD_View_Column_ID (int AD_View_Column_ID)
	{
		if (AD_View_Column_ID < 1) 
			set_Value (COLUMNNAME_AD_View_Column_ID, null);
		else 
			set_Value (COLUMNNAME_AD_View_Column_ID, Integer.valueOf(AD_View_Column_ID));
	}

	/** Get View Column.
		@return Column of View
	  */
	public int getAD_View_Column_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_View_Column_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getAD_View_Column_ID()));
    }

	public org.adempiere.model.I_AD_View_Column getAxis_Column() throws RuntimeException
    {
		return (org.adempiere.model.I_AD_View_Column)MTable.get(getCtx(), org.adempiere.model.I_AD_View_Column.Table_Name)
			.getPO(getAxis_Column_ID(), get_TrxName());	}

	/** Set Axis Column.
		@param Axis_Column_ID 
		Axis the link column.
	  */
	public void setAxis_Column_ID (int Axis_Column_ID)
	{
		if (Axis_Column_ID < 1) 
			set_Value (COLUMNNAME_Axis_Column_ID, null);
		else 
			set_Value (COLUMNNAME_Axis_Column_ID, Integer.valueOf(Axis_Column_ID));
	}

	/** Get Axis Column.
		@return Axis the link column.
	  */
	public int getAxis_Column_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Axis_Column_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.model.I_AD_View_Column getAxis_Parent_Column() throws RuntimeException
    {
		return (org.adempiere.model.I_AD_View_Column)MTable.get(getCtx(), org.adempiere.model.I_AD_View_Column.Table_Name)
			.getPO(getAxis_Parent_Column_ID(), get_TrxName());	}

	/** Set Axis Parent Column.
		@param Axis_Parent_Column_ID 
		The link Axis column view on the parent key
	  */
	public void setAxis_Parent_Column_ID (int Axis_Parent_Column_ID)
	{
		if (Axis_Parent_Column_ID < 1) 
			set_Value (COLUMNNAME_Axis_Parent_Column_ID, null);
		else 
			set_Value (COLUMNNAME_Axis_Parent_Column_ID, Integer.valueOf(Axis_Parent_Column_ID));
	}

	/** Get Axis Parent Column.
		@return The link Axis column view on the parent key
	  */
	public int getAxis_Parent_Column_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Axis_Parent_Column_ID);
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

	/** Set Default Logic 2.
		@param DefaultValue2 
		Default value hierarchy, separated by ;
	  */
	public void setDefaultValue2 (String DefaultValue2)
	{
		set_Value (COLUMNNAME_DefaultValue2, DefaultValue2);
	}

	/** Get Default Logic 2.
		@return Default value hierarchy, separated by ;
	  */
	public String getDefaultValue2 () 
	{
		return (String)get_Value(COLUMNNAME_DefaultValue2);
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

	/** Set Length.
		@param FieldLength 
		Length of the column in the database
	  */
	public void setFieldLength (int FieldLength)
	{
		set_Value (COLUMNNAME_FieldLength, Integer.valueOf(FieldLength));
	}

	/** Get Length.
		@return Length of the column in the database
	  */
	public int getFieldLength () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FieldLength);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Identifier.
		@param IsIdentifier 
		This column is part of the record identifier
	  */
	public void setIsIdentifier (boolean IsIdentifier)
	{
		set_Value (COLUMNNAME_IsIdentifier, Boolean.valueOf(IsIdentifier));
	}

	/** Get Identifier.
		@return This column is part of the record identifier
	  */
	public boolean isIdentifier () 
	{
		Object oo = get_Value(COLUMNNAME_IsIdentifier);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Key column.
		@param IsKey 
		This column is the key in this table
	  */
	public void setIsKey (boolean IsKey)
	{
		set_Value (COLUMNNAME_IsKey, Boolean.valueOf(IsKey));
	}

	/** Get Key column.
		@return This column is the key in this table
	  */
	public boolean isKey () 
	{
		Object oo = get_Value(COLUMNNAME_IsKey);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Mandatory.
		@param IsMandatory 
		Data entry is required in this column
	  */
	public void setIsMandatory (boolean IsMandatory)
	{
		set_Value (COLUMNNAME_IsMandatory, Boolean.valueOf(IsMandatory));
	}

	/** Get Mandatory.
		@return Data entry is required in this column
	  */
	public boolean isMandatory () 
	{
		Object oo = get_Value(COLUMNNAME_IsMandatory);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Order by.
		@param IsOrderBy 
		Include in sort order
	  */
	public void setIsOrderBy (boolean IsOrderBy)
	{
		set_Value (COLUMNNAME_IsOrderBy, Boolean.valueOf(IsOrderBy));
	}

	/** Get Order by.
		@return Include in sort order
	  */
	public boolean isOrderBy () 
	{
		Object oo = get_Value(COLUMNNAME_IsOrderBy);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Query Criteria.
		@param IsQueryCriteria 
		The column is also used as a query criteria
	  */
	public void setIsQueryCriteria (boolean IsQueryCriteria)
	{
		set_Value (COLUMNNAME_IsQueryCriteria, Boolean.valueOf(IsQueryCriteria));
	}

	/** Get Query Criteria.
		@return The column is also used as a query criteria
	  */
	public boolean isQueryCriteria () 
	{
		Object oo = get_Value(COLUMNNAME_IsQueryCriteria);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Range.
		@param IsRange 
		The parameter is a range of values
	  */
	public void setIsRange (boolean IsRange)
	{
		set_Value (COLUMNNAME_IsRange, Boolean.valueOf(IsRange));
	}

	/** Get Range.
		@return The parameter is a range of values
	  */
	public boolean isRange () 
	{
		Object oo = get_Value(COLUMNNAME_IsRange);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Read Only.
		@param IsReadOnly 
		Field is read only
	  */
	public void setIsReadOnly (boolean IsReadOnly)
	{
		set_Value (COLUMNNAME_IsReadOnly, Boolean.valueOf(IsReadOnly));
	}

	/** Get Read Only.
		@return Field is read only
	  */
	public boolean isReadOnly () 
	{
		Object oo = get_Value(COLUMNNAME_IsReadOnly);
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

	/** Set Value Format.
		@param VFormat 
		Format of the value; Can contain fixed format elements, Variables: "_lLoOaAcCa09"
	  */
	public void setVFormat (String VFormat)
	{
		set_Value (COLUMNNAME_VFormat, VFormat);
	}

	/** Get Value Format.
		@return Format of the value; Can contain fixed format elements, Variables: "_lLoOaAcCa09"
	  */
	public String getVFormat () 
	{
		return (String)get_Value(COLUMNNAME_VFormat);
	}

	/** Set Max. Value.
		@param ValueMax 
		Maximum Value for a field
	  */
	public void setValueMax (String ValueMax)
	{
		set_Value (COLUMNNAME_ValueMax, ValueMax);
	}

	/** Get Max. Value.
		@return Maximum Value for a field
	  */
	public String getValueMax () 
	{
		return (String)get_Value(COLUMNNAME_ValueMax);
	}

	/** Set Min. Value.
		@param ValueMin 
		Minimum Value for a field
	  */
	public void setValueMin (String ValueMin)
	{
		set_Value (COLUMNNAME_ValueMin, ValueMin);
	}

	/** Get Min. Value.
		@return Minimum Value for a field
	  */
	public String getValueMin () 
	{
		return (String)get_Value(COLUMNNAME_ValueMin);
	}
}