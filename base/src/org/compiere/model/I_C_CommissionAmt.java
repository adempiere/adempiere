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

/** Generated Interface for C_CommissionAmt
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1b
 */
public interface I_C_CommissionAmt 
{

    /** TableName=C_CommissionAmt */
    public static final String Table_Name = "C_CommissionAmt";

    /** AD_Table_ID=430 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

    /** Load Meta Data */

    /** Column name ActualQty */
    public static final String COLUMNNAME_ActualQty = "ActualQty";

	/** Set Actual Quantity.
	  * The actual quantity
	  */
	public void setActualQty (BigDecimal ActualQty);

	/** Get Actual Quantity.
	  * The actual quantity
	  */
	public BigDecimal getActualQty();

    /** Column name C_CommissionAmt_ID */
    public static final String COLUMNNAME_C_CommissionAmt_ID = "C_CommissionAmt_ID";

	/** Set Commission Amount.
	  * Generated Commission Amount 
	  */
	public void setC_CommissionAmt_ID (int C_CommissionAmt_ID);

	/** Get Commission Amount.
	  * Generated Commission Amount 
	  */
	public int getC_CommissionAmt_ID();

    /** Column name C_CommissionLine_ID */
    public static final String COLUMNNAME_C_CommissionLine_ID = "C_CommissionLine_ID";

	/** Set Commission Line.
	  * Commission Line
	  */
	public void setC_CommissionLine_ID (int C_CommissionLine_ID);

	/** Get Commission Line.
	  * Commission Line
	  */
	public int getC_CommissionLine_ID();

	public I_C_CommissionLine getC_CommissionLine() throws Exception;

    /** Column name C_CommissionRun_ID */
    public static final String COLUMNNAME_C_CommissionRun_ID = "C_CommissionRun_ID";

	/** Set Commission Run.
	  * Commission Run or Process
	  */
	public void setC_CommissionRun_ID (int C_CommissionRun_ID);

	/** Get Commission Run.
	  * Commission Run or Process
	  */
	public int getC_CommissionRun_ID();

	public I_C_CommissionRun getC_CommissionRun() throws Exception;

    /** Column name CommissionAmt */
    public static final String COLUMNNAME_CommissionAmt = "CommissionAmt";

	/** Set Commission Amount.
	  * Commission Amount
	  */
	public void setCommissionAmt (BigDecimal CommissionAmt);

	/** Get Commission Amount.
	  * Commission Amount
	  */
	public BigDecimal getCommissionAmt();

    /** Column name ConvertedAmt */
    public static final String COLUMNNAME_ConvertedAmt = "ConvertedAmt";

	/** Set Converted Amount.
	  * Converted Amount
	  */
	public void setConvertedAmt (BigDecimal ConvertedAmt);

	/** Get Converted Amount.
	  * Converted Amount
	  */
	public BigDecimal getConvertedAmt();
}
