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
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for A_Asset_Reval_Index
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.5.1a
 */
public interface I_A_Asset_Reval_Index 
{

    /** TableName=A_Asset_Reval_Index */
    public static final String Table_Name = "A_Asset_Reval_Index";

    /** AD_Table_ID=53120 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name A_Asset_Reval_Index_ID */
    public static final String COLUMNNAME_A_Asset_Reval_Index_ID = "A_Asset_Reval_Index_ID";

	/** Set A_Asset_Reval_Index_ID	  */
	public void setA_Asset_Reval_Index_ID (int A_Asset_Reval_Index_ID);

	/** Get A_Asset_Reval_Index_ID	  */
	public int getA_Asset_Reval_Index_ID();

    /** Column name A_Effective_Date */
    public static final String COLUMNNAME_A_Effective_Date = "A_Effective_Date";

	/** Set A_Effective_Date	  */
	public void setA_Effective_Date (Timestamp A_Effective_Date);

	/** Get A_Effective_Date	  */
	public Timestamp getA_Effective_Date();

    /** Column name A_Reval_Code */
    public static final String COLUMNNAME_A_Reval_Code = "A_Reval_Code";

	/** Set A_Reval_Code	  */
	public void setA_Reval_Code (String A_Reval_Code);

	/** Get A_Reval_Code	  */
	public String getA_Reval_Code();

    /** Column name A_Reval_Multiplier */
    public static final String COLUMNNAME_A_Reval_Multiplier = "A_Reval_Multiplier";

	/** Set A_Reval_Multiplier	  */
	public void setA_Reval_Multiplier (String A_Reval_Multiplier);

	/** Get A_Reval_Multiplier	  */
	public String getA_Reval_Multiplier();

    /** Column name A_Reval_Rate */
    public static final String COLUMNNAME_A_Reval_Rate = "A_Reval_Rate";

	/** Set A_Reval_Rate	  */
	public void setA_Reval_Rate (BigDecimal A_Reval_Rate);

	/** Get A_Reval_Rate	  */
	public BigDecimal getA_Reval_Rate();
}
