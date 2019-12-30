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
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_MigrationStep
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2
 */
public interface I_AD_MigrationStep 
{

    /** TableName=AD_MigrationStep */
    public static final String Table_Name = "AD_MigrationStep";

    /** AD_Table_ID=53218 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_MigrationStep_ID */
    public static final String COLUMNNAME_AD_MigrationStep_ID = "AD_MigrationStep_ID";

	/** Set Migration step.
	  * A single step in the migration process
	  */
	public void setAD_MigrationStep_ID (int AD_MigrationStep_ID);

	/** Get Migration step.
	  * A single step in the migration process
	  */
	public int getAD_MigrationStep_ID();

    /** Column name AD_Migration_ID */
    public static final String COLUMNNAME_AD_Migration_ID = "AD_Migration_ID";

	/** Set Migration.
	  * Migration change management.
	  */
	public void setAD_Migration_ID (int AD_Migration_ID);

	/** Get Migration.
	  * Migration change management.
	  */
	public int getAD_Migration_ID();

	public org.compiere.model.I_AD_Migration getAD_Migration() throws RuntimeException;

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name AD_Table_ID */
    public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";

	/** Set Table.
	  * Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID);

	/** Get Table.
	  * Database Table information
	  */
	public int getAD_Table_ID();

	public org.compiere.model.I_AD_Table getAD_Table() throws RuntimeException;

    /** Column name Action */
    public static final String COLUMNNAME_Action = "Action";

	/** Set Action.
	  * Indicates the Action to be performed
	  */
	public void setAction (String Action);

	/** Get Action.
	  * Indicates the Action to be performed
	  */
	public String getAction();

    /** Column name Apply */
    public static final String COLUMNNAME_Apply = "Apply";

	/** Set Apply.
	  * Apply migration
	  */
	public void setApply (String Apply);

	/** Get Apply.
	  * Apply migration
	  */
	public String getApply();

    /** Column name Comments */
    public static final String COLUMNNAME_Comments = "Comments";

	/** Set Comments.
	  * Comments or additional information
	  */
	public void setComments (String Comments);

	/** Get Comments.
	  * Comments or additional information
	  */
	public String getComments();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name DBType */
    public static final String COLUMNNAME_DBType = "DBType";

	/** Set DBType	  */
	public void setDBType (String DBType);

	/** Get DBType	  */
	public String getDBType();

    /** Column name ErrorMsg */
    public static final String COLUMNNAME_ErrorMsg = "ErrorMsg";

	/** Set Error Msg	  */
	public void setErrorMsg (String ErrorMsg);

	/** Get Error Msg	  */
	public String getErrorMsg();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name Parse */
    public static final String COLUMNNAME_Parse = "Parse";

	/** Set Parse Statement.
	  * Select if the SQL statement should be parsed based on terminating semi-colons.
	  */
	public void setParse (boolean Parse);

	/** Get Parse Statement.
	  * Select if the SQL statement should be parsed based on terminating semi-colons.
	  */
	public boolean isParse();

    /** Column name Record_ID */
    public static final String COLUMNNAME_Record_ID = "Record_ID";

	/** Set Record ID.
	  * Direct internal record ID
	  */
	public void setRecord_ID (int Record_ID);

	/** Get Record ID.
	  * Direct internal record ID
	  */
	public int getRecord_ID();

    /** Column name RollbackStatement */
    public static final String COLUMNNAME_RollbackStatement = "RollbackStatement";

	/** Set Rollback Statement.
	  * SQL statement to rollback the current step.
	  */
	public void setRollbackStatement (String RollbackStatement);

	/** Get Rollback Statement.
	  * SQL statement to rollback the current step.
	  */
	public String getRollbackStatement();

    /** Column name SQLStatement */
    public static final String COLUMNNAME_SQLStatement = "SQLStatement";

	/** Set SQLStatement	  */
	public void setSQLStatement (String SQLStatement);

	/** Get SQLStatement	  */
	public String getSQLStatement();

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();

    /** Column name StatusCode */
    public static final String COLUMNNAME_StatusCode = "StatusCode";

	/** Set Status Code	  */
	public void setStatusCode (String StatusCode);

	/** Get Status Code	  */
	public String getStatusCode();

    /** Column name StepType */
    public static final String COLUMNNAME_StepType = "StepType";

	/** Set Step type.
	  * Migration step type
	  */
	public void setStepType (String StepType);

	/** Get Step type.
	  * Migration step type
	  */
	public String getStepType();

    /** Column name UUID */
    public static final String COLUMNNAME_UUID = "UUID";

	/** Set Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID);

	/** Get Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public String getUUID();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();
}
