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

    /** Generated Interface for AD_Package_Imp_Proc
     *  @author Trifon Trifonov (generated) 
     *  @version Release 3.3.0 - 2007-08-24 11:39:25.312
     */
    public interface I_AD_Package_Imp_Proc 
{

    /** TableName=AD_Package_Imp_Proc */
    public static final String Table_Name = "AD_Package_Imp_Proc";

    /** AD_Table_ID=50008 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = new BigDecimal(4);

    /** Load Meta Data */

    /** Column name AD_Override_Dict */
    public static final String COLUMNNAME_AD_Override_Dict = "AD_Override_Dict";

	/** Set Update System Maintained Application Dictionary	  */
	public void setAD_Override_Dict (boolean AD_Override_Dict);

	/** Get Update System Maintained Application Dictionary	  */
	public boolean isAD_Override_Dict();

    /** Column name AD_Package_Dir */
    public static final String COLUMNNAME_AD_Package_Dir = "AD_Package_Dir";

	/** Set Package Directory.
	  * Package directory, default to AdempiereHome/packages
	  */
	public void setAD_Package_Dir (String AD_Package_Dir);

	/** Get Package Directory.
	  * Package directory, default to AdempiereHome/packages
	  */
	public String getAD_Package_Dir();

    /** Column name AD_Package_Imp_Proc_ID */
    public static final String COLUMNNAME_AD_Package_Imp_Proc_ID = "AD_Package_Imp_Proc_ID";

	/** Set AD_Package_Imp_Proc_ID	  */
	public void setAD_Package_Imp_Proc_ID (int AD_Package_Imp_Proc_ID);

	/** Get AD_Package_Imp_Proc_ID	  */
	public int getAD_Package_Imp_Proc_ID();

    /** Column name AD_Package_Source */
    public static final String COLUMNNAME_AD_Package_Source = "AD_Package_Source";

	/** Set Package Source.
	  * Fully qualified package source file name
	  */
	public void setAD_Package_Source (String AD_Package_Source);

	/** Get Package Source.
	  * Fully qualified package source file name
	  */
	public String getAD_Package_Source();

    /** Column name AD_Package_Source_Type */
    public static final String COLUMNNAME_AD_Package_Source_Type = "AD_Package_Source_Type";

	/** Set Package Source Type.
	  * Type of package source - file, ftp, webservice etc
	  */
	public void setAD_Package_Source_Type (String AD_Package_Source_Type);

	/** Get Package Source Type.
	  * Type of package source - file, ftp, webservice etc
	  */
	public String getAD_Package_Source_Type();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();
}
