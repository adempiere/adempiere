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

/** Generated Model for PA_DocumentStatus
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_PA_DocumentStatus extends PO implements I_PA_DocumentStatus, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_PA_DocumentStatus (Properties ctx, int PA_DocumentStatus_ID, String trxName)
    {
      super (ctx, PA_DocumentStatus_ID, trxName);
      /** if (PA_DocumentStatus_ID == 0)
        {
			setAD_Table_ID (0);
			setName (null);
			setName_PrintColor_ID (0);
			setName_PrintFont_ID (0);
			setNumber_PrintColor_ID (0);
			setNumber_PrintFont_ID (0);
			setPA_DocumentStatus_ID (0);
			setSeqNo (0);
        } */
    }

    /** Load Constructor */
    public X_PA_DocumentStatus (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PA_DocumentStatus[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	public org.compiere.model.I_AD_Role getAD_Role() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Role)MTable.get(getCtx(), org.compiere.model.I_AD_Role.Table_Name)
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

	public org.compiere.model.I_AD_Table getAD_Table() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Table)MTable.get(getCtx(), org.compiere.model.I_AD_Table.Table_Name)
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

	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
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

	public org.compiere.model.I_AD_Window getAD_Window() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Window)MTable.get(getCtx(), org.compiere.model.I_AD_Window.Table_Name)
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

	public org.compiere.model.I_C_Project getC_Project() throws RuntimeException
    {
		return (org.compiere.model.I_C_Project)MTable.get(getCtx(), org.compiere.model.I_C_Project.Table_Name)
			.getPO(getC_Project_ID(), get_TrxName());	}

	/** Set Project.
		@param C_Project_ID 
		Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID)
	{
		if (C_Project_ID < 1) 
			set_Value (COLUMNNAME_C_Project_ID, null);
		else 
			set_Value (COLUMNNAME_C_Project_ID, Integer.valueOf(C_Project_ID));
	}

	/** Get Project.
		@return Financial Project
	  */
	public int getC_Project_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Project_ID);
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

	public org.compiere.model.I_AD_PrintColor getName_PrintColor() throws RuntimeException
    {
		return (org.compiere.model.I_AD_PrintColor)MTable.get(getCtx(), org.compiere.model.I_AD_PrintColor.Table_Name)
			.getPO(getName_PrintColor_ID(), get_TrxName());	}

	/** Set Name Colour.
		@param Name_PrintColor_ID Name Colour	  */
	public void setName_PrintColor_ID (int Name_PrintColor_ID)
	{
		if (Name_PrintColor_ID < 1) 
			set_Value (COLUMNNAME_Name_PrintColor_ID, null);
		else 
			set_Value (COLUMNNAME_Name_PrintColor_ID, Integer.valueOf(Name_PrintColor_ID));
	}

	/** Get Name Colour.
		@return Name Colour	  */
	public int getName_PrintColor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Name_PrintColor_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_PrintFont getName_PrintFont() throws RuntimeException
    {
		return (org.compiere.model.I_AD_PrintFont)MTable.get(getCtx(), org.compiere.model.I_AD_PrintFont.Table_Name)
			.getPO(getName_PrintFont_ID(), get_TrxName());	}

	/** Set Name Font.
		@param Name_PrintFont_ID Name Font	  */
	public void setName_PrintFont_ID (int Name_PrintFont_ID)
	{
		if (Name_PrintFont_ID < 1) 
			set_Value (COLUMNNAME_Name_PrintFont_ID, null);
		else 
			set_Value (COLUMNNAME_Name_PrintFont_ID, Integer.valueOf(Name_PrintFont_ID));
	}

	/** Get Name Font.
		@return Name Font	  */
	public int getName_PrintFont_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Name_PrintFont_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_PrintColor getNumber_PrintColor() throws RuntimeException
    {
		return (org.compiere.model.I_AD_PrintColor)MTable.get(getCtx(), org.compiere.model.I_AD_PrintColor.Table_Name)
			.getPO(getNumber_PrintColor_ID(), get_TrxName());	}

	/** Set Number Colour.
		@param Number_PrintColor_ID Number Colour	  */
	public void setNumber_PrintColor_ID (int Number_PrintColor_ID)
	{
		if (Number_PrintColor_ID < 1) 
			set_Value (COLUMNNAME_Number_PrintColor_ID, null);
		else 
			set_Value (COLUMNNAME_Number_PrintColor_ID, Integer.valueOf(Number_PrintColor_ID));
	}

	/** Get Number Colour.
		@return Number Colour	  */
	public int getNumber_PrintColor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Number_PrintColor_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_PrintFont getNumber_PrintFont() throws RuntimeException
    {
		return (org.compiere.model.I_AD_PrintFont)MTable.get(getCtx(), org.compiere.model.I_AD_PrintFont.Table_Name)
			.getPO(getNumber_PrintFont_ID(), get_TrxName());	}

	/** Set Number Font.
		@param Number_PrintFont_ID Number Font	  */
	public void setNumber_PrintFont_ID (int Number_PrintFont_ID)
	{
		if (Number_PrintFont_ID < 1) 
			set_Value (COLUMNNAME_Number_PrintFont_ID, null);
		else 
			set_Value (COLUMNNAME_Number_PrintFont_ID, Integer.valueOf(Number_PrintFont_ID));
	}

	/** Get Number Font.
		@return Number Font	  */
	public int getNumber_PrintFont_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Number_PrintFont_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Document Status Indicator.
		@param PA_DocumentStatus_ID Document Status Indicator	  */
	public void setPA_DocumentStatus_ID (int PA_DocumentStatus_ID)
	{
		if (PA_DocumentStatus_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PA_DocumentStatus_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PA_DocumentStatus_ID, Integer.valueOf(PA_DocumentStatus_ID));
	}

	/** Get Document Status Indicator.
		@return Document Status Indicator	  */
	public int getPA_DocumentStatus_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PA_DocumentStatus_ID);
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