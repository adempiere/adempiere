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

/** Generated Model for AD_MigrationData
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2 - $Id$ */
public class X_AD_MigrationData extends PO implements I_AD_MigrationData, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191120L;

    /** Standard Constructor */
    public X_AD_MigrationData (Properties ctx, int AD_MigrationData_ID, String trxName)
    {
      super (ctx, AD_MigrationData_ID, trxName);
      /** if (AD_MigrationData_ID == 0)
        {
			setAD_Column_ID (0);
			setAD_MigrationData_ID (0);
			setAD_MigrationStep_ID (0);
        } */
    }

    /** Load Constructor */
    public X_AD_MigrationData (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_MigrationData[")
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

	/** Set Migration data.
		@param AD_MigrationData_ID Migration data	  */
	public void setAD_MigrationData_ID (int AD_MigrationData_ID)
	{
		if (AD_MigrationData_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_MigrationData_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_MigrationData_ID, Integer.valueOf(AD_MigrationData_ID));
	}

	/** Get Migration data.
		@return Migration data	  */
	public int getAD_MigrationData_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_MigrationData_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_MigrationStep getAD_MigrationStep() throws RuntimeException
    {
		return (org.compiere.model.I_AD_MigrationStep)MTable.get(getCtx(), org.compiere.model.I_AD_MigrationStep.Table_Name)
			.getPO(getAD_MigrationStep_ID(), get_TrxName());	}

	/** Set Migration step.
		@param AD_MigrationStep_ID 
		A single step in the migration process
	  */
	public void setAD_MigrationStep_ID (int AD_MigrationStep_ID)
	{
		if (AD_MigrationStep_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_MigrationStep_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_MigrationStep_ID, Integer.valueOf(AD_MigrationStep_ID));
	}

	/** Get Migration step.
		@return A single step in the migration process
	  */
	public int getAD_MigrationStep_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_MigrationStep_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getAD_MigrationStep_ID()));
    }

	/** Set Backup Value.
		@param BackupValue 
		The value of the column prior to migration.
	  */
	public void setBackupValue (String BackupValue)
	{
		set_Value (COLUMNNAME_BackupValue, BackupValue);
	}

	/** Get Backup Value.
		@return The value of the column prior to migration.
	  */
	public String getBackupValue () 
	{
		return (String)get_Value(COLUMNNAME_BackupValue);
	}

	/** Set Backup value null.
		@param IsBackupNull 
		The backup value is null.
	  */
	public void setIsBackupNull (boolean IsBackupNull)
	{
		set_Value (COLUMNNAME_IsBackupNull, Boolean.valueOf(IsBackupNull));
	}

	/** Get Backup value null.
		@return The backup value is null.
	  */
	public boolean isBackupNull () 
	{
		Object oo = get_Value(COLUMNNAME_IsBackupNull);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set New value null.
		@param IsNewNull 
		The new value is null.
	  */
	public void setIsNewNull (boolean IsNewNull)
	{
		set_Value (COLUMNNAME_IsNewNull, Boolean.valueOf(IsNewNull));
	}

	/** Get New value null.
		@return The new value is null.
	  */
	public boolean isNewNull () 
	{
		Object oo = get_Value(COLUMNNAME_IsNewNull);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Old value null.
		@param IsOldNull 
		The old value was null.
	  */
	public void setIsOldNull (boolean IsOldNull)
	{
		set_Value (COLUMNNAME_IsOldNull, Boolean.valueOf(IsOldNull));
	}

	/** Get Old value null.
		@return The old value was null.
	  */
	public boolean isOldNull () 
	{
		Object oo = get_Value(COLUMNNAME_IsOldNull);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set New Value.
		@param NewValue 
		New field value
	  */
	public void setNewValue (String NewValue)
	{
		set_Value (COLUMNNAME_NewValue, NewValue);
	}

	/** Get New Value.
		@return New field value
	  */
	public String getNewValue () 
	{
		return (String)get_Value(COLUMNNAME_NewValue);
	}

	/** Set Old Value.
		@param OldValue 
		The old file data
	  */
	public void setOldValue (String OldValue)
	{
		set_Value (COLUMNNAME_OldValue, OldValue);
	}

	/** Get Old Value.
		@return The old file data
	  */
	public String getOldValue () 
	{
		return (String)get_Value(COLUMNNAME_OldValue);
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