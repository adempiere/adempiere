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

    /** Generated Interface for C_Withholding_Acct
     *  @author Trifon Trifonov (generated) 
     *  @version Release 3.3.0 - 2007-08-24 11:39:46.64
     */
    public interface I_C_Withholding_Acct 
{

    /** TableName=C_Withholding_Acct */
    public static final String Table_Name = "C_Withholding_Acct";

    /** AD_Table_ID=400 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = new BigDecimal(3);

    /** Load Meta Data */

    /** Column name C_AcctSchema_ID */
    public static final String COLUMNNAME_C_AcctSchema_ID = "C_AcctSchema_ID";

	/** Set Accounting Schema.
	  * Rules for accounting
	  */
	public void setC_AcctSchema_ID (int C_AcctSchema_ID);

	/** Get Accounting Schema.
	  * Rules for accounting
	  */
	public int getC_AcctSchema_ID();

	public I_C_AcctSchema getI_C_AcctSchema() throws Exception;

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

	public I_C_Withholding getI_C_Withholding() throws Exception;

    /** Column name Withholding_Acct */
    public static final String COLUMNNAME_Withholding_Acct = "Withholding_Acct";

	/** Set Withholding.
	  * Account for Withholdings
	  */
	public void setWithholding_Acct (int Withholding_Acct);

	/** Get Withholding.
	  * Account for Withholdings
	  */
	public int getWithholding_Acct();
}
