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

import java.util.*;
import java.sql.Timestamp;
import java.math.*;
import org.compiere.util.*;

    /** Generated Interface for AD_Package_Imp_Backup
     *  @author Trifon Trifonov (generated) 
     *  @version Release 3.3.0 - 2007-08-24 11:39:25.156
     */
    public interface I_AD_Package_Imp_Backup 
{

    /** TableName=AD_Package_Imp_Backup */
    public static final String Table_Name = "AD_Package_Imp_Backup";

    /** AD_Table_ID=50002 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = new BigDecimal(4);

    /** Load Meta Data */

    /** Column name AD_Column_ID */
    public static final String COLUMNNAME_AD_Column_ID = "AD_Column_ID";

	/** Set Column.
	  * Column in the table
	  */
	public void setAD_Column_ID (int AD_Column_ID);

	/** Get Column.
	  * Column in the table
	  */
	public int getAD_Column_ID();

    /** Column name AD_Package_Imp_Backup_ID */
    public static final String COLUMNNAME_AD_Package_Imp_Backup_ID = "AD_Package_Imp_Backup_ID";

	/** Set AD_Package_Imp_Backup_ID	  */
	public void setAD_Package_Imp_Backup_ID (int AD_Package_Imp_Backup_ID);

	/** Get AD_Package_Imp_Backup_ID	  */
	public int getAD_Package_Imp_Backup_ID();

    /** Column name AD_Package_Imp_Bck_Dir */
    public static final String COLUMNNAME_AD_Package_Imp_Bck_Dir = "AD_Package_Imp_Bck_Dir";

	/** Set AD_Package_Imp_Bck_Dir	  */
	public void setAD_Package_Imp_Bck_Dir (String AD_Package_Imp_Bck_Dir);

	/** Get AD_Package_Imp_Bck_Dir	  */
	public String getAD_Package_Imp_Bck_Dir();

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

    /** Column name AD_Package_Imp_Org_Dir */
    public static final String COLUMNNAME_AD_Package_Imp_Org_Dir = "AD_Package_Imp_Org_Dir";

	/** Set AD_Package_Imp_Org_Dir	  */
	public void setAD_Package_Imp_Org_Dir (String AD_Package_Imp_Org_Dir);

	/** Get AD_Package_Imp_Org_Dir	  */
	public String getAD_Package_Imp_Org_Dir();

    /** Column name AD_Reference_ID */
    public static final String COLUMNNAME_AD_Reference_ID = "AD_Reference_ID";

	/** Set Reference.
	  * System Reference and Validation
	  */
	public void setAD_Reference_ID (int AD_Reference_ID);

	/** Get Reference.
	  * System Reference and Validation
	  */
	public int getAD_Reference_ID();

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

    /** Column name ColValue */
    public static final String COLUMNNAME_ColValue = "ColValue";

	/** Set ColValue	  */
	public void setColValue (String ColValue);

	/** Get ColValue	  */
	public String getColValue();

    /** Column name Uninstall */
    public static final String COLUMNNAME_Uninstall = "Uninstall";

	/** Set Uninstall	  */
	public void setUninstall (boolean Uninstall);

	/** Get Uninstall	  */
	public boolean isUninstall();
}
