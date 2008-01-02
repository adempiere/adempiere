/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          *
 * http://www.adempiere.org                                           *
 *                                                                    *
 * Copyright (C) Trifon Trifonov.                                     *
 * Copyright (C) Contributors                                         *
 *                                                                    *
 * This program is free software;
 you can redistribute it and/or      *
 * modify it under the terms of the GNU General Public License        *
 * as published by the Free Software Foundation;
 either version 2     *
 * of the License, or (at your option) any later version.             *
 *                                                                    *
 * This program is distributed in the hope that it will be useful,    *
 * but WITHOUT ANY WARRANTY;
 without even the implied warranty of     *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       *
 * GNU General Public License for more details.                       *
 *                                                                    *
 * You should have received a copy of the GNU General Public License  *
 * along with this program;
 if not, write to the Free Software        *
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
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_Package_Imp
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1t
 */
public interface I_AD_Package_Imp 
{

    /** TableName=AD_Package_Imp */
    public static final String Table_Name = "AD_Package_Imp";

    /** AD_Table_ID=50003 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

    /** Load Meta Data */

    /** Column name AD_Package_Imp_ID */
    public static final String COLUMNNAME_AD_Package_Imp_ID = "AD_Package_Imp_ID";

	/** Set AD_Package_Imp_ID	  */
	public void setAD_Package_Imp_ID (int AD_Package_Imp_ID);

	/** Get AD_Package_Imp_ID	  */
	public int getAD_Package_Imp_ID();

    /** Column name Creator */
    public static final String COLUMNNAME_Creator = "Creator";

	/** Set Creator	  */
	public void setCreator (String Creator);

	/** Get Creator	  */
	public String getCreator();

    /** Column name CreatorContact */
    public static final String COLUMNNAME_CreatorContact = "CreatorContact";

	/** Set CreatorContact	  */
	public void setCreatorContact (String CreatorContact);

	/** Get CreatorContact	  */
	public String getCreatorContact();

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

    /** Column name EMail */
    public static final String COLUMNNAME_EMail = "EMail";

	/** Set EMail Address.
	  * Electronic Mail Address
	  */
	public void setEMail (String EMail);

	/** Get EMail Address.
	  * Electronic Mail Address
	  */
	public String getEMail();

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

    /** Column name PK_Status */
    public static final String COLUMNNAME_PK_Status = "PK_Status";

	/** Set PK_Status	  */
	public void setPK_Status (String PK_Status);

	/** Get PK_Status	  */
	public String getPK_Status();

    /** Column name PK_Version */
    public static final String COLUMNNAME_PK_Version = "PK_Version";

	/** Set Package Version	  */
	public void setPK_Version (String PK_Version);

	/** Get Package Version	  */
	public String getPK_Version();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name ReleaseNo */
    public static final String COLUMNNAME_ReleaseNo = "ReleaseNo";

	/** Set Release No.
	  * Internal Release Number
	  */
	public void setReleaseNo (String ReleaseNo);

	/** Get Release No.
	  * Internal Release Number
	  */
	public String getReleaseNo();

    /** Column name Uninstall */
    public static final String COLUMNNAME_Uninstall = "Uninstall";

	/** Set Uninstall	  */
	public void setUninstall (boolean Uninstall);

	/** Get Uninstall	  */
	public boolean isUninstall();

    /** Column name Version */
    public static final String COLUMNNAME_Version = "Version";

	/** Set Version.
	  * Version of the table definition
	  */
	public void setVersion (String Version);

	/** Get Version.
	  * Version of the table definition
	  */
	public String getVersion();
}
