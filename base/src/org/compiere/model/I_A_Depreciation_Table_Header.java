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

/** Generated Interface for A_Depreciation_Table_Header
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.5.1a
 */
public interface I_A_Depreciation_Table_Header 
{

    /** TableName=A_Depreciation_Table_Header */
    public static final String Table_Name = "A_Depreciation_Table_Header";

    /** AD_Table_ID=53114 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name A_Depreciation_Table_Code */
    public static final String COLUMNNAME_A_Depreciation_Table_Code = "A_Depreciation_Table_Code";

	/** Set A_Depreciation_Table_Code	  */
	public void setA_Depreciation_Table_Code (String A_Depreciation_Table_Code);

	/** Get A_Depreciation_Table_Code	  */
	public String getA_Depreciation_Table_Code();

    /** Column name A_Depreciation_Table_Header_ID */
    public static final String COLUMNNAME_A_Depreciation_Table_Header_ID = "A_Depreciation_Table_Header_ID";

	/** Set A_Depreciation_Table_Header_ID	  */
	public void setA_Depreciation_Table_Header_ID (int A_Depreciation_Table_Header_ID);

	/** Get A_Depreciation_Table_Header_ID	  */
	public int getA_Depreciation_Table_Header_ID();

    /** Column name A_Table_Rate_Type */
    public static final String COLUMNNAME_A_Table_Rate_Type = "A_Table_Rate_Type";

	/** Set A_Table_Rate_Type	  */
	public void setA_Table_Rate_Type (String A_Table_Rate_Type);

	/** Get A_Table_Rate_Type	  */
	public String getA_Table_Rate_Type();

    /** Column name A_Term */
    public static final String COLUMNNAME_A_Term = "A_Term";

	/** Set A_Term	  */
	public void setA_Term (String A_Term);

	/** Get A_Term	  */
	public String getA_Term();

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
}
