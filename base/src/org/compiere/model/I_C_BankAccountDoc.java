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

/** Generated Interface for C_BankAccountDoc
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1b
 */
public interface I_C_BankAccountDoc 
{

    /** TableName=C_BankAccountDoc */
    public static final String Table_Name = "C_BankAccountDoc";

    /** AD_Table_ID=455 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name C_BankAccountDoc_ID */
    public static final String COLUMNNAME_C_BankAccountDoc_ID = "C_BankAccountDoc_ID";

	/** Set Bank Account Document.
	  * Checks, Transfers, etc.
	  */
	public void setC_BankAccountDoc_ID (int C_BankAccountDoc_ID);

	/** Get Bank Account Document.
	  * Checks, Transfers, etc.
	  */
	public int getC_BankAccountDoc_ID();

    /** Column name C_BankAccount_ID */
    public static final String COLUMNNAME_C_BankAccount_ID = "C_BankAccount_ID";

	/** Set Bank Account.
	  * Account at the Bank
	  */
	public void setC_BankAccount_ID (int C_BankAccount_ID);

	/** Get Bank Account.
	  * Account at the Bank
	  */
	public int getC_BankAccount_ID();

	public I_C_BankAccount getC_BankAccount() throws Exception;

    /** Column name Check_PrintFormat_ID */
    public static final String COLUMNNAME_Check_PrintFormat_ID = "Check_PrintFormat_ID";

	/** Set Check Print Format.
	  * Print Format for printing Checks
	  */
	public void setCheck_PrintFormat_ID (int Check_PrintFormat_ID);

	/** Get Check Print Format.
	  * Print Format for printing Checks
	  */
	public int getCheck_PrintFormat_ID();

    /** Column name CurrentNext */
    public static final String COLUMNNAME_CurrentNext = "CurrentNext";

	/** Set Current Next.
	  * The next number to be used
	  */
	public void setCurrentNext (int CurrentNext);

	/** Get Current Next.
	  * The next number to be used
	  */
	public int getCurrentNext();

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

    /** Column name PaymentRule */
    public static final String COLUMNNAME_PaymentRule = "PaymentRule";

	/** Set Payment Rule.
	  * How you pay the invoice
	  */
	public void setPaymentRule (String PaymentRule);

	/** Get Payment Rule.
	  * How you pay the invoice
	  */
	public String getPaymentRule();
}
