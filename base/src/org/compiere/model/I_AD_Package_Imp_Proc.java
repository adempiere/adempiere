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

/** Generated Interface for AD_Package_Imp_Proc
 *  @author Adempiere (generated) 
 *  @version Release 3.5.3a
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
    BigDecimal accessLevel = BigDecimal.valueOf(4);

    /** Load Meta Data */

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
