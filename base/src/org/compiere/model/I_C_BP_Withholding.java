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

/** Generated Interface for C_BP_Withholding
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.5.1a
 */
public interface I_C_BP_Withholding 
{

    /** TableName=C_BP_Withholding */
    public static final String Table_Name = "C_BP_Withholding";

    /** AD_Table_ID=299 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public I_C_BPartner getC_BPartner() throws Exception;

    /** Column name C_Withholding_ID */
    public static final String COLUMNNAME_C_Withholding_ID = "C_Withholding_ID";

	/** Set Withholding.
	  * Withholding type defined
	  */
	public void setC_Withholding_ID (int C_Withholding_ID);

	/** Get Withholding.
	  * Withholding type defined
	  */
	public int getC_Withholding_ID();

	public I_C_Withholding getC_Withholding() throws Exception;

    /** Column name ExemptReason */
    public static final String COLUMNNAME_ExemptReason = "ExemptReason";

	/** Set Exempt reason.
	  * Reason for not withholding
	  */
	public void setExemptReason (String ExemptReason);

	/** Get Exempt reason.
	  * Reason for not withholding
	  */
	public String getExemptReason();

    /** Column name IsMandatoryWithholding */
    public static final String COLUMNNAME_IsMandatoryWithholding = "IsMandatoryWithholding";

	/** Set Mandatory Withholding.
	  * Monies must be withheld
	  */
	public void setIsMandatoryWithholding (boolean IsMandatoryWithholding);

	/** Get Mandatory Withholding.
	  * Monies must be withheld
	  */
	public boolean isMandatoryWithholding();

    /** Column name IsTemporaryExempt */
    public static final String COLUMNNAME_IsTemporaryExempt = "IsTemporaryExempt";

	/** Set Temporary exempt.
	  * Temporarily do not withhold taxes
	  */
	public void setIsTemporaryExempt (boolean IsTemporaryExempt);

	/** Get Temporary exempt.
	  * Temporarily do not withhold taxes
	  */
	public boolean isTemporaryExempt();
}
