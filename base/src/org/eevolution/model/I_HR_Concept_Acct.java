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
package org.eevolution.model;

import java.math.BigDecimal;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HR_Concept_Acct
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.5.1a
 */
public interface I_HR_Concept_Acct 
{

    /** TableName=HR_Concept_Acct */
    public static final String Table_Name = "HR_Concept_Acct";

    /** AD_Table_ID=53091 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

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

	public I_C_AcctSchema getC_AcctSchema() throws Exception;

    /** Column name C_BP_Group_ID */
    public static final String COLUMNNAME_C_BP_Group_ID = "C_BP_Group_ID";

	/** Set Business Partner Group.
	  * Business Partner Group
	  */
	public void setC_BP_Group_ID (int C_BP_Group_ID);

	/** Get Business Partner Group.
	  * Business Partner Group
	  */
	public int getC_BP_Group_ID();

	public I_C_BP_Group getC_BP_Group() throws Exception;

    /** Column name HR_Concept_Acct_ID */
    public static final String COLUMNNAME_HR_Concept_Acct_ID = "HR_Concept_Acct_ID";

	/** Set HR_Concept_Acct_ID	  */
	public void setHR_Concept_Acct_ID (int HR_Concept_Acct_ID);

	/** Get HR_Concept_Acct_ID	  */
	public int getHR_Concept_Acct_ID();

    /** Column name HR_Concept_ID */
    public static final String COLUMNNAME_HR_Concept_ID = "HR_Concept_ID";

	/** Set Concept	  */
	public void setHR_Concept_ID (int HR_Concept_ID);

	/** Get Concept	  */
	public int getHR_Concept_ID();

	public org.eevolution.model.I_HR_Concept getHR_Concept() throws Exception;

    /** Column name HR_Expense_Acct */
    public static final String COLUMNNAME_HR_Expense_Acct = "HR_Expense_Acct";

	/** Set HR_Expense_Acct	  */
	public void setHR_Expense_Acct (int HR_Expense_Acct);

	/** Get HR_Expense_Acct	  */
	public int getHR_Expense_Acct();

    /** Column name HR_Revenue_Acct */
    public static final String COLUMNNAME_HR_Revenue_Acct = "HR_Revenue_Acct";

	/** Set HR_Revenue_Acct	  */
	public void setHR_Revenue_Acct (int HR_Revenue_Acct);

	/** Get HR_Revenue_Acct	  */
	public int getHR_Revenue_Acct();

    /** Column name IsBalancing */
    public static final String COLUMNNAME_IsBalancing = "IsBalancing";

	/** Set Balancing.
	  * All transactions within an element value must balance (e.g. cost centers)
	  */
	public void setIsBalancing (boolean IsBalancing);

	/** Get Balancing.
	  * All transactions within an element value must balance (e.g. cost centers)
	  */
	public boolean isBalancing();

    /** Column name User1_ID */
    public static final String COLUMNNAME_User1_ID = "User1_ID";

	/** Set User List 1.
	  * User defined list element #1
	  */
	public void setUser1_ID (int User1_ID);

	/** Get User List 1.
	  * User defined list element #1
	  */
	public int getUser1_ID();

    /** Column name User2_ID */
    public static final String COLUMNNAME_User2_ID = "User2_ID";

	/** Set User List 2.
	  * User defined list element #2
	  */
	public void setUser2_ID (int User2_ID);

	/** Get User List 2.
	  * User defined list element #2
	  */
	public int getUser2_ID();
}
