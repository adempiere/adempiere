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

/** Generated Model for AD_MigrationStep
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_AD_MigrationStep extends PO implements I_AD_MigrationStep, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_AD_MigrationStep (Properties ctx, int AD_MigrationStep_ID, String trxName)
    {
      super (ctx, AD_MigrationStep_ID, trxName);
      /** if (AD_MigrationStep_ID == 0)
        {
			setAD_MigrationStep_ID (0);
			setAD_Migration_ID (0);
			setSeqNo (0);
// @SQL=SELECT COALESCE(MAX(SeqNo),0)+10 AS DefaultValue FROM AD_MigrationStep WHERE AD_Migration_ID=@AD_Migration_ID@
			setStepType (null);
        } */
    }

    /** Load Constructor */
    public X_AD_MigrationStep (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_MigrationStep[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

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

	public org.compiere.model.I_AD_Migration getAD_Migration() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Migration)MTable.get(getCtx(), org.compiere.model.I_AD_Migration.Table_Name)
			.getPO(getAD_Migration_ID(), get_TrxName());	}

	/** Set Migration.
		@param AD_Migration_ID 
		Migration change management.
	  */
	public void setAD_Migration_ID (int AD_Migration_ID)
	{
		if (AD_Migration_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_Migration_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_Migration_ID, Integer.valueOf(AD_Migration_ID));
	}

	/** Get Migration.
		@return Migration change management.
	  */
	public int getAD_Migration_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Migration_ID);
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

	/** Action AD_Reference_ID=53238 */
	public static final int ACTION_AD_Reference_ID=53238;
	/** Insert = I */
	public static final String ACTION_Insert = "I";
	/** Delete = D */
	public static final String ACTION_Delete = "D";
	/** Update = U */
	public static final String ACTION_Update = "U";
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

	/** Apply AD_Reference_ID=53312 */
	public static final int APPLY_AD_Reference_ID=53312;
	/** Apply = A */
	public static final String APPLY_Apply = "A";
	/** Rollback = R */
	public static final String APPLY_Rollback = "R";
	/** Set Apply.
		@param Apply 
		Apply migration
	  */
	public void setApply (String Apply)
	{

		set_Value (COLUMNNAME_Apply, Apply);
	}

	/** Get Apply.
		@return Apply migration
	  */
	public String getApply () 
	{
		return (String)get_Value(COLUMNNAME_Apply);
	}

	/** Set Comments.
		@param Comments 
		Comments or additional information
	  */
	public void setComments (String Comments)
	{
		set_Value (COLUMNNAME_Comments, Comments);
	}

	/** Get Comments.
		@return Comments or additional information
	  */
	public String getComments () 
	{
		return (String)get_Value(COLUMNNAME_Comments);
	}

	/** DBType AD_Reference_ID=50003 */
	public static final int DBTYPE_AD_Reference_ID=50003;
	/** All Database Types = ALL */
	public static final String DBTYPE_AllDatabaseTypes = "ALL";
	/** DB2 = DB2 */
	public static final String DBTYPE_DB2 = "DB2";
	/** Firebird = Firebird */
	public static final String DBTYPE_Firebird = "Firebird";
	/** MySQL = MySQL */
	public static final String DBTYPE_MySQL = "MySQL";
	/** Oracle = Oracle */
	public static final String DBTYPE_Oracle = "Oracle";
	/** Postgres = Postgres */
	public static final String DBTYPE_Postgres = "Postgres";
	/** SQL Server = SQL */
	public static final String DBTYPE_SQLServer = "SQL";
	/** Sybase = Sybase */
	public static final String DBTYPE_Sybase = "Sybase";
	/** Set DBType.
		@param DBType DBType	  */
	public void setDBType (String DBType)
	{

		set_Value (COLUMNNAME_DBType, DBType);
	}

	/** Get DBType.
		@return DBType	  */
	public String getDBType () 
	{
		return (String)get_Value(COLUMNNAME_DBType);
	}

	/** Set Error Msg.
		@param ErrorMsg Error Msg	  */
	public void setErrorMsg (String ErrorMsg)
	{
		set_ValueNoCheck (COLUMNNAME_ErrorMsg, ErrorMsg);
	}

	/** Get Error Msg.
		@return Error Msg	  */
	public String getErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_ErrorMsg);
	}

	/** Set Parse Statement.
		@param Parse 
		Select if the SQL statement should be parsed based on terminating semi-colons.
	  */
	public void setParse (boolean Parse)
	{
		set_Value (COLUMNNAME_Parse, Boolean.valueOf(Parse));
	}

	/** Get Parse Statement.
		@return Select if the SQL statement should be parsed based on terminating semi-colons.
	  */
	public boolean isParse () 
	{
		Object oo = get_Value(COLUMNNAME_Parse);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Record ID.
		@param Record_ID 
		Direct internal record ID
	  */
	public void setRecord_ID (int Record_ID)
	{
		if (Record_ID < 0) 
			set_Value (COLUMNNAME_Record_ID, null);
		else 
			set_Value (COLUMNNAME_Record_ID, Integer.valueOf(Record_ID));
	}

	/** Get Record ID.
		@return Direct internal record ID
	  */
	public int getRecord_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Record_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Rollback Statement.
		@param RollbackStatement 
		SQL statement to rollback the current step.
	  */
	public void setRollbackStatement (String RollbackStatement)
	{
		set_Value (COLUMNNAME_RollbackStatement, RollbackStatement);
	}

	/** Get Rollback Statement.
		@return SQL statement to rollback the current step.
	  */
	public String getRollbackStatement () 
	{
		return (String)get_Value(COLUMNNAME_RollbackStatement);
	}

	/** Set SQLStatement.
		@param SQLStatement SQLStatement	  */
	public void setSQLStatement (String SQLStatement)
	{
		set_Value (COLUMNNAME_SQLStatement, SQLStatement);
	}

	/** Get SQLStatement.
		@return SQLStatement	  */
	public String getSQLStatement () 
	{
		return (String)get_Value(COLUMNNAME_SQLStatement);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getSeqNo()));
    }

	/** StatusCode AD_Reference_ID=53311 */
	public static final int STATUSCODE_AD_Reference_ID=53311;
	/** Applied = A */
	public static final String STATUSCODE_Applied = "A";
	/** Unapplied = U */
	public static final String STATUSCODE_Unapplied = "U";
	/** Failed = F */
	public static final String STATUSCODE_Failed = "F";
	/** Partially applied = P */
	public static final String STATUSCODE_PartiallyApplied = "P";
	/** Set Status Code.
		@param StatusCode Status Code	  */
	public void setStatusCode (String StatusCode)
	{

		set_ValueNoCheck (COLUMNNAME_StatusCode, StatusCode);
	}

	/** Get Status Code.
		@return Status Code	  */
	public String getStatusCode () 
	{
		return (String)get_Value(COLUMNNAME_StatusCode);
	}

	/** StepType AD_Reference_ID=53313 */
	public static final int STEPTYPE_AD_Reference_ID=53313;
	/** Application Dictionary = AD */
	public static final String STEPTYPE_ApplicationDictionary = "AD";
	/** SQL Statement = SQL */
	public static final String STEPTYPE_SQLStatement = "SQL";
	/** Set Step type.
		@param StepType 
		Migration step type
	  */
	public void setStepType (String StepType)
	{

		set_ValueNoCheck (COLUMNNAME_StepType, StepType);
	}

	/** Get Step type.
		@return Migration step type
	  */
	public String getStepType () 
	{
		return (String)get_Value(COLUMNNAME_StepType);
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