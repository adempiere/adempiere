/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software;
 you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY;
 without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program;
 if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_Package_Imp_Detail
 *  @author Adempiere (generated) 
 *  @version Release 3.5.3a
 */
public interface I_AD_Package_Imp_Detail 
{

    /** TableName=AD_Package_Imp_Detail */
    public static final String Table_Name = "AD_Package_Imp_Detail";

    /** AD_Table_ID=50004 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

    /** Load Meta Data */

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

    /** Column name Ad_Backup_ID */
    public static final String COLUMNNAME_Ad_Backup_ID = "Ad_Backup_ID";

	/** Set Ad_Backup_ID	  */
	public void setAd_Backup_ID (int Ad_Backup_ID);

	/** Get Ad_Backup_ID	  */
	public int getAd_Backup_ID();

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

    /** Column name AD_Original_ID */
    public static final String COLUMNNAME_AD_Original_ID = "AD_Original_ID";

	/** Set AD_Original_ID	  */
	public void setAD_Original_ID (int AD_Original_ID);

	/** Get AD_Original_ID	  */
	public int getAD_Original_ID();

    /** Column name AD_Package_Imp_Detail_ID */
    public static final String COLUMNNAME_AD_Package_Imp_Detail_ID = "AD_Package_Imp_Detail_ID";

	/** Set AD_Package_Imp_Detail_ID	  */
	public void setAD_Package_Imp_Detail_ID (int AD_Package_Imp_Detail_ID);

	/** Get AD_Package_Imp_Detail_ID	  */
	public int getAD_Package_Imp_Detail_ID();

    /** Column name AD_Package_Imp_ID */
    public static final String COLUMNNAME_AD_Package_Imp_ID = "AD_Package_Imp_ID";

	/** Set AD_Package_Imp_ID	  */
	public void setAD_Package_Imp_ID (int AD_Package_Imp_ID);

	/** Get AD_Package_Imp_ID	  */
	public int getAD_Package_Imp_ID();

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

    /** Column name Success */
    public static final String COLUMNNAME_Success = "Success";

	/** Set Success	  */
	public void setSuccess (String Success);

	/** Get Success	  */
	public String getSuccess();

    /** Column name TableName */
    public static final String COLUMNNAME_TableName = "TableName";

	/** Set DB Table Name.
	  * Name of the table in the database
	  */
	public void setTableName (String TableName);

	/** Get DB Table Name.
	  * Name of the table in the database
	  */
	public String getTableName();

    /** Column name Type */
    public static final String COLUMNNAME_Type = "Type";

	/** Set Type.
	  * Type of Validation (SQL, Java Script, Java Language)
	  */
	public void setType (String Type);

	/** Get Type.
	  * Type of Validation (SQL, Java Script, Java Language)
	  */
	public String getType();

    /** Column name Uninstall */
    public static final String COLUMNNAME_Uninstall = "Uninstall";

	/** Set Uninstall	  */
	public void setUninstall (boolean Uninstall);

	/** Get Uninstall	  */
	public boolean isUninstall();
}
