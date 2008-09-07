/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          *
 * http://www.adempiere.org                                           *
 *                                                                    *
 * Copyright (C) Trifon Trifonov.                                     *
 * Copyright (C) Contributors                                         *
 *                                                                    *
 * This program is free software, you can redistribute it and/or      *
 * modify it under the terms of the GNU General Public License        *
 * as published by the Free Software Foundation, either version 2     *
 * of the License, or (at your option) any later version.             *
 *                                                                    *
 * This program is distributed in the hope that it will be useful,    *
 * but WITHOUT ANY WARRANTY, without even the implied warranty of     *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       *
 * GNU General Public License for more details.                       *
 *                                                                    *
 * You should have received a copy of the GNU General Public License  *
 * along with this program, if not, write to the Free Software        *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         *
 * MA 02110-1301, USA.                                                *
 *                                                                    *
 * Contributors:                                                      *
 * - Trifon Trifonov (trifonnt@users.sourceforge.net)                 *
 *                                                                    *
 * Sponsors:                                                          *
 * - Company (http://www.site.com)                                    *
 **********************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_HouseKeeping
 *  @author Adempiere (generated) 
 *  @version Release 3.5.2a
 */
public interface I_AD_HouseKeeping 
{

    /** TableName=AD_HouseKeeping */
    public static final String Table_Name = "AD_HouseKeeping";

    /** AD_Table_ID=53147 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

    /** Load Meta Data */

    /** Column name AD_HouseKeeping_ID */
    public static final String COLUMNNAME_AD_HouseKeeping_ID = "AD_HouseKeeping_ID";

	/** Set House Keeping Configuration	  */
	public void setAD_HouseKeeping_ID (int AD_HouseKeeping_ID);

	/** Get House Keeping Configuration	  */
	public int getAD_HouseKeeping_ID();

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

	public I_AD_Table getAD_Table() throws Exception;

    /** Column name BackupFolder */
    public static final String COLUMNNAME_BackupFolder = "BackupFolder";

	/** Set Backup Folder.
	  * Backup Folder
	  */
	public void setBackupFolder (String BackupFolder);

	/** Get Backup Folder.
	  * Backup Folder
	  */
	public String getBackupFolder();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name Help */
    public static final String COLUMNNAME_Help = "Help";

	/** Set Comment/Help.
	  * Comment or Hint
	  */
	public void setHelp (String Help);

	/** Get Comment/Help.
	  * Comment or Hint
	  */
	public String getHelp();

    /** Column name IsExportXMLBackup */
    public static final String COLUMNNAME_IsExportXMLBackup = "IsExportXMLBackup";

	/** Set Export XML Backup	  */
	public void setIsExportXMLBackup (boolean IsExportXMLBackup);

	/** Get Export XML Backup	  */
	public boolean isExportXMLBackup();

    /** Column name IsSaveInHistoric */
    public static final String COLUMNNAME_IsSaveInHistoric = "IsSaveInHistoric";

	/** Set Save In Historic	  */
	public void setIsSaveInHistoric (boolean IsSaveInHistoric);

	/** Get Save In Historic	  */
	public boolean isSaveInHistoric();

    /** Column name LastDeleted */
    public static final String COLUMNNAME_LastDeleted = "LastDeleted";

	/** Set Last Deleted	  */
	public void setLastDeleted (int LastDeleted);

	/** Get Last Deleted	  */
	public int getLastDeleted();

    /** Column name LastRun */
    public static final String COLUMNNAME_LastRun = "LastRun";

	/** Set Last Run	  */
	public void setLastRun (Timestamp LastRun);

	/** Get Last Run	  */
	public Timestamp getLastRun();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();

    /** Column name WhereClause */
    public static final String COLUMNNAME_WhereClause = "WhereClause";

	/** Set Sql WHERE.
	  * Fully qualified SQL WHERE clause
	  */
	public void setWhereClause (String WhereClause);

	/** Get Sql WHERE.
	  * Fully qualified SQL WHERE clause
	  */
	public String getWhereClause();
}
