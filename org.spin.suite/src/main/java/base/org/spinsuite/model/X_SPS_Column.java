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

/** Generated Model for SPS_Column
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS - $Id$ */
public class X_SPS_Column extends PO implements I_SPS_Column, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20140927L;

    /** Standard Constructor */
    public X_SPS_Column (Properties ctx, int SPS_Column_ID, String trxName)
    {
      super (ctx, SPS_Column_ID, trxName);
      /** if (SPS_Column_ID == 0)
        {
			setAD_Element_ID (0);
			setAD_Reference_ID (0);
			setColumnName (null);
			setEntityType (null);
// ECA01
			setIsAlwaysUpdateable (false);
// N
			setIsIdentifier (false);
// N
			setIsKey (false);
// N
			setIsMandatory (false);
// N
			setIsParent (false);
// N
			setIsSelectionColumn (false);
// N
			setIsUpdateable (true);
// Y
			setName (null);
			setSPS_Column_ID (0);
			setSPS_Table_ID (0);
        } */
    }

    /** Load Constructor */
    public X_SPS_Column (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_SPS_Column[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_Column getAD_Column() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Column)MTable.get(getCtx(), org.compiere.model.I_AD_Column.Table_Name)
			.getPO(getAD_Column_ID(), get_TrxName());	}

	/** Set Column.
		@param AD_Column_ID 
		Column in the table
	  */
	public void setAD_Column_ID (int AD_Column_ID)
	{
		if (AD_Column_ID < 1) 
			set_Value (COLUMNNAME_AD_Column_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Column_ID, Integer.valueOf(AD_Column_ID));
	}

	/** Get Column.
		@return Column in the table
	  */
	public int getAD_Column_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Column_ID);
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

	/** Set Callout.
		@param Callout 
		Fully qualified class names and method - separated by semicolons
	  */
	public void setCallout (String Callout)
	{
		set_Value (COLUMNNAME_Callout, Callout);
	}

	/** Get Callout.
		@return Fully qualified class names and method - separated by semicolons
	  */
	public String getCallout () 
	{
		return (String)get_Value(COLUMNNAME_Callout);
	}

	/** Set DB Column Name.
		@param ColumnName 
		Name of the column in the database
	  */
	public void setColumnName (String ColumnName)
	{
		set_Value (COLUMNNAME_ColumnName, ColumnName);
	}

	/** Get DB Column Name.
		@return Name of the column in the database
	  */
	public String getColumnName () 
	{
		return (String)get_Value(COLUMNNAME_ColumnName);
	}

	/** Set Column SQL.
		@param ColumnSQL 
		Virtual Column (r/o)
	  */
	public void setColumnSQL (String ColumnSQL)
	{
		set_Value (COLUMNNAME_ColumnSQL, ColumnSQL);
	}

	/** Get Column SQL.
		@return Virtual Column (r/o)
	  */
	public String getColumnSQL () 
	{
		return (String)get_Value(COLUMNNAME_ColumnSQL);
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

	/** Set Format Pattern.
		@param FormatPattern 
		The pattern used to format a number or date.
	  */
	public void setFormatPattern (String FormatPattern)
	{
		set_Value (COLUMNNAME_FormatPattern, FormatPattern);
	}

	/** Get Format Pattern.
		@return The pattern used to format a number or date.
	  */
	public String getFormatPattern () 
	{
		return (String)get_Value(COLUMNNAME_FormatPattern);
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

	/** Set Always Updateable.
		@param IsAlwaysUpdateable 
		The column is always updateable, even if the record is not active or processed
	  */
	public void setIsAlwaysUpdateable (boolean IsAlwaysUpdateable)
	{
		set_Value (COLUMNNAME_IsAlwaysUpdateable, Boolean.valueOf(IsAlwaysUpdateable));
	}

	/** Get Always Updateable.
		@return The column is always updateable, even if the record is not active or processed
	  */
	public boolean isAlwaysUpdateable () 
	{
		Object oo = get_Value(COLUMNNAME_IsAlwaysUpdateable);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** IsEncrypted AD_Reference_ID=354 */
	public static final int ISENCRYPTED_AD_Reference_ID=354;
	/** Encrypted = Y */
	public static final String ISENCRYPTED_Encrypted = "Y";
	/** Not Encrypted = N */
	public static final String ISENCRYPTED_NotEncrypted = "N";
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

	/** Set Parent link column.
		@param IsParent 
		This column is a link to the parent table (e.g. header from lines) - incl. Association key columns
	  */
	public void setIsParent (boolean IsParent)
	{
		set_Value (COLUMNNAME_IsParent, Boolean.valueOf(IsParent));
	}

	/** Get Parent link column.
		@return This column is a link to the parent table (e.g. header from lines) - incl. Association key columns
	  */
	public boolean isParent () 
	{
		Object oo = get_Value(COLUMNNAME_IsParent);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Selection Column.
		@param IsSelectionColumn 
		Is this column used for finding rows in windows
	  */
	public void setIsSelectionColumn (boolean IsSelectionColumn)
	{
		set_Value (COLUMNNAME_IsSelectionColumn, Boolean.valueOf(IsSelectionColumn));
	}

	/** Get Selection Column.
		@return Is this column used for finding rows in windows
	  */
	public boolean isSelectionColumn () 
	{
		Object oo = get_Value(COLUMNNAME_IsSelectionColumn);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Selection Seq No.
		@param SelectionSeqNo Selection Seq No	  */
	public void setSelectionSeqNo (int SelectionSeqNo)
	{
		set_Value (COLUMNNAME_SelectionSeqNo, Integer.valueOf(SelectionSeqNo));
	}

	/** Get Selection Seq No.
		@return Selection Seq No	  */
	public int getSelectionSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SelectionSeqNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Mobile Column.
		@param SPS_Column_ID Mobile Column	  */
	public void setSPS_Column_ID (int SPS_Column_ID)
	{
		if (SPS_Column_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_SPS_Column_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_SPS_Column_ID, Integer.valueOf(SPS_Column_ID));
	}

	/** Get Mobile Column.
		@return Mobile Column	  */
	public int getSPS_Column_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SPS_Column_ID);
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
			set_ValueNoCheck (COLUMNNAME_SPS_Table_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_SPS_Table_ID, Integer.valueOf(SPS_Table_ID));
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
}